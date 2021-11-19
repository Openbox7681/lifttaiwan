var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, $cookies, Upload) {

	// 流程紀錄用
	$("#divReview").hide();
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "postDateTime";
	$scope.sortreverse = true;
	$scope.btnIns = false;
	$scope.btnUpd = false;
	
	$scope.ContactorTel = '';
	$scope.ContactorFax = '';
	$scope.ContactorEmail = '';
	
	// 顯示流程畫面
	$scope.IsSeeLog = true;
	// 顯示簽核畫面
	$scope.IsSeeOpinion = false;

	// 在狀態按鍵名稱後顯示目前筆數
	$scope.ButtonCount1=0;
	$scope.ButtonCount2=0;
	$scope.ButtonCount3=0;
	
	$scope.setSortName = function(sorttype) {
		$scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse
				: false;
		$scope.sorttype = sorttype;
		$scope.currentPage = 1;
		$scope.start = 0;
		$scope.queryData();
	};

	$scope.maxRowsChange = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryData();
	};
	// Paging End

	// 狀態階段按鍵即時查詢用
	$scope.setStatus = function(status) {
		 $scope.QueryStatus = status;
		 $scope.queryData();
	}

	// Clear Query Data End		

//	$("#QuerySPostDateTime").datetimepicker({		
//		format: 'YYYY-MM-DD',
//		locale: 'zh-TW'
//	});
//	$("#QuerySPostDateTime").on("dp.change", function(e) {	       
//		$scope.QuerySPostDateTime = $("#QuerySPostDateTime").val();
//	});
//	
//	$("#QueryEPostDateTime").datetimepicker({		
//		format: 'YYYY-MM-DD',
//		locale: 'zh-TW'
//	});
//	$("#QueryEPostDateTime").on("dp.change", function(e) {	       
//		$scope.QueryEPostDateTime = $("#QueryEPostDateTime").val();
//	});
	
	$('div[id="datetimepicker1"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$('div[id="datetimepicker2"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	
	$("#datetimepicker1").on("dp.change", function(e) {	       
		$scope.QuerySPostDateTime = $("#QuerySPostDateTime").val();
	});
	
	$("#datetimepicker2").on("dp.change", function(e) {	       
		$scope.QueryEPostDateTime = $("#QueryEPostDateTime").val();
	});

    $("#PostDateTime").datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $("#PostDateTime").on("dp.change", function(e) {
        $scope.PostDateTime = $("#PostDateTime").val();
    });
		
	$("#IncidentDiscoveryTime").datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#IncidentDiscoveryTime").on("dp.change", function(e) {	       
		$scope.IncidentDiscoveryTime = $("#IncidentDiscoveryTime").val();
	});

	$("#FinishDateTime").datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#FinishDateTime").on("dp.change", function(e) {	       
		$scope.FinishDateTime = $("#FinishDateTime").val();
	});

	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.QuerySPostDateTime = null;
		$scope.QueryEPostDateTime = null;
		$scope.QueryReporterName = null;
		$scope.QueryHandleName = null;
		$scope.QueryStatus = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
		$('#datetimepicker1').data("DateTimePicker").clear()
		$('#datetimepicker2').data("DateTimePicker").clear()
	};
	$scope.clearData();
	
//	$scope.QuerySPostDateTime = moment().subtract(1,'months').format('YYYY-MM-DD');
//	$('#QuerySPostDateTime').val( moment().subtract(1,'months').format('YYYY-MM-DD'));
//	$scope.QueryEPostDateTime = moment().format('YYYY-MM-DD');
//	$('#QueryEPostDateTime').val( moment().format('YYYY-MM-DD'));
	
	// Query Data Start
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}else {
            $scope.start = 0;
            $scope.currentPage = 1;
        }
		if ($scope.QuerySPostDateTime == "")
			$scope.QuerySPostDateTime = null;
		if ($scope.QueryEPostDateTime == "")
			$scope.QueryEPostDateTime = null;
		if ($scope.QueryReporterName == "")
			$scope.QueryReporterName = null;
		if ($scope.QueryHandleName == "")
			$scope.QueryHandleName = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			SPostDateTime : $scope.QuerySPostDateTime,
			EPostDateTime : $scope.QueryEPostDateTime,
			ReporterName : $scope.QueryReporterName,
			HandleName : $scope.QueryHandleName,
			Status : $scope.QueryStatus
		};

		$http.post('./api/i01/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
			
			// 統計各狀態目前筆數
			$scope.queryButtonCount();
			
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		}).finally(function() {
			$("#imgLoading").hide();
		});
	};
	$scope.queryData();
	// Query Data End

	// 瀏覽及簽核用
	$scope.review = function(id, Action) {	
		
		// debug
//		console.log("i01.js → review() → id=" + id + "；Action=" + Action);

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});

		// 重設上一頁、下一頁
	    $scope.queryStep = 1;
	    $scope.queryStepPrev = false;
	    $scope.queryStepNext = true;
	    $scope.gotToQueryStep($scope.queryStep);
	    
		$scope.Id = null;
		$scope.PostDateTime = null;
		$scope.PostId = null;
		$scope.ReporterName = 0;
		$scope.HandleName = 0;
		$scope.ContactorId = 0;
		$scope.ContactorTel = null;
		$scope.ContactorFax = null;
		$scope.ContactorEmail = null;
		$scope.IncidentDiscoveryTime = null;
		$scope.HostAmount = 0;
		$scope.ServerAmount = 0;
        $scope.InformationAmount = 0;
        $scope.OtherDeviceAmount = 0;
        $scope.OtherDeviceName = "";
        $scope.DeviceRemark = "";
        $scope.AssessDamage = "";
        $scope.AssessDamageRemark = "";
		$scope.IsOsOpt1 = false;
		$scope.IsOsOpt2 = false;
		$scope.IsOsOpt3 = false;
		$scope.IsOsOpt3Other = null;
		$scope.EventType = 1;
		$scope.IsEventType1Opt1 = false;
		$scope.IsEventType1Opt2 = false;
		$scope.IsEventType1Opt3 = false;
		$scope.IsEventType1Opt4 = false;
		$scope.IsEventType1Opt5 = false;
		$scope.IsEventType1Opt6 = false;
		$scope.IsEventType2Opt1 = false;
		$scope.IsEventType2Opt2 = false;
		$scope.IsEventType2Opt3 = false;
		$scope.IsEventType2Opt4 = false;
		$scope.IsEventType2Opt5 = false;
		$scope.IsEventType3Opt1 = false;
		$scope.IsEventType3Opt2 = false;
		$scope.IsEventType4Opt1 = false;
		$scope.IsEventType4Opt2 = false;
		$scope.IsEventType4Opt3 = false;
		$scope.IsEventType4Opt4 = false;
		$scope.EventType5Other = null;
		$scope.EventRemark = null;
		$scope.IsEventCause1Opt1 = false;
		$scope.IsEventCause1Opt2 = false;
		$scope.IsEventCause1Opt3 = false;
		$scope.IsEventCause1Opt4 = false;
		$scope.IsEventCause1Opt5 = false;
		$scope.IsEventCause1Opt6 = false;
		$scope.IsEventCause1Opt6Other = null;
		$scope.EventEvaluation = null;
		$scope.EventProcess = null;
		$scope.IsSecuritySetting1Opt1 = false;
		$scope.IsSecuritySetting1Opt2 = false;
		$scope.IsSecuritySetting1Opt3 = false;
		$scope.IsSecuritySetting1Opt4 = false;
		$scope.IsSecuritySetting1Opt5 = false;
		$scope.IsSecuritySetting1Opt6 = false;
		$scope.IsSecuritySetting1Opt7 = false;
		$scope.IsSecuritySetting1Opt8 = false;
		$scope.IsSecuritySetting1Opt9 = false;
		$scope.IsSecuritySetting1Opt10 = false;
		$scope.IsSecuritySetting1Opt11 = false;
		$scope.IsSecuritySetting1Opt12 = false;
		$scope.IsSecuritySetting1Opt13 = false;
		$scope.FinishDoOther = null;
		$scope.FinishDateTime = null;
		$scope.Status = null;

		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.Action = Action;

		$scope.editData(id);
		
//		console.log("review() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" + $scope.btnUpd);
		
		switch(Action) {
			
			case "view": // 瀏覽頁面
				$("#divReview").show();
				$scope.IsSeeOpinion = false; // 顯示簽核畫面
				$scope.messagePassButton = false; // 是否顯示通過按鍵
				$scope.messageBackButton = false; // 是否顯示退回按鍵
				$scope.messageUndoButtonButton = false; // 是否顯示撤銷按鍵
			break;
			
			case "messageReview": // 審核頁面
				$("#divReview").show();
				$scope.IsSeeOpinion = true; // 顯示簽核畫面
				$scope.messagePassButton = true; // 是否顯示通過按鍵
				$scope.messageBackButton = true; // 是否顯示退回按鍵
				$scope.messageUndoButtonButton = true; // 是否顯示撤銷按鍵
			break;
			
		}

		// debug
//		console.log("review() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" + $scope.btnUpd);
		
	}

	$scope.edit = function(id) {
		
		// debug
//		console.log("i01.js → edit() → ContactorId : " + $scope.ContactorId); 
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		// 重設上一頁、下一頁
		$scope.step = 1;
	    $scope.stepPrev = false;
	    $scope.stepNext = true;
	    $scope.gotToStep($scope.step);
	    
		$scope.Id = null;
		$scope.PostDateTime = null;
		$scope.PostId = null;
		$scope.ReporterName = 0;
		$scope.HandleName = 0;
		$scope.ContactorId = 0;
		$scope.ContactorTel = null;
		$scope.ContactorFax = null;
		$scope.ContactorEmail = null;
		$scope.IncidentDiscoveryTime = null;
		$scope.HostAmount = 0;
		$scope.ServerAmount = 0;
        $scope.InformationAmount = 0;
        $scope.OtherDeviceAmount = 0;
        $scope.OtherDeviceName = "";
        $scope.DeviceRemark = "";
        $scope.AssessDamage = "";
        $scope.AssessDamageRemark = "";
		$scope.IsOsOpt1 = false;
		$scope.IsOsOpt2 = false;
		$scope.IsOsOpt3 = false;
		$scope.IsOsOpt3Other = null;
		$scope.EventType = 1;
		$scope.IsEventType1Opt1 = false;
		$scope.IsEventType1Opt2 = false;
		$scope.IsEventType1Opt3 = false;
		$scope.IsEventType1Opt4 = false;
		$scope.IsEventType1Opt5 = false;
		$scope.IsEventType1Opt6 = false;
		$scope.IsEventType2Opt1 = false;
		$scope.IsEventType2Opt2 = false;
		$scope.IsEventType2Opt3 = false;
		$scope.IsEventType2Opt4 = false;
		$scope.IsEventType2Opt5 = false;
		$scope.IsEventType3Opt1 = false;
		$scope.IsEventType3Opt2 = false;
		$scope.IsEventType4Opt1 = false;
		$scope.IsEventType4Opt2 = false;
		$scope.IsEventType4Opt3 = false;
		$scope.IsEventType4Opt4 = false;
		$scope.EventType5Other = null;
		$scope.EventRemark = null;
		$scope.IsEventCause1Opt1 = false;
		$scope.IsEventCause1Opt2 = false;
		$scope.IsEventCause1Opt3 = false;
		$scope.IsEventCause1Opt4 = false;
		$scope.IsEventCause1Opt5 = false;
		$scope.IsEventCause1Opt6 = false;
		$scope.IsEventCause1Opt6Other = null;
		$scope.EventEvaluation = null;
		$scope.EventProcess = null;
		$scope.IsSecuritySetting1Opt1 = false;
		$scope.IsSecuritySetting1Opt2 = false;
		$scope.IsSecuritySetting1Opt3 = false;
		$scope.IsSecuritySetting1Opt4 = false;
		$scope.IsSecuritySetting1Opt5 = false;
		$scope.IsSecuritySetting1Opt6 = false;
		$scope.IsSecuritySetting1Opt7 = false;
		$scope.IsSecuritySetting1Opt8 = false;
		$scope.IsSecuritySetting1Opt9 = false;
		$scope.IsSecuritySetting1Opt10 = false;
		$scope.IsSecuritySetting1Opt11 = false;
		$scope.IsSecuritySetting1Opt12 = false;
		$scope.IsSecuritySetting1Opt13 = false;
		$scope.FinishDoOther = null;
		$scope.FinishDateTime = null;
		$scope.Status = null;
		
		$scope.SatisfactionTime = null;
		$scope.SatisfactionProfessionalism = null;
		$scope.SatisfactionService = null;
		$scope.SatisfactionReport = null;
		$scope.SatisfactionTotal = null;
		$scope.SatisfactionRemark = null;

		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.editData(id);

		$scope.btnIns = false;
		$scope.btnUpd = true;
		$("#divQuery").hide();
		
	}
	
	// 簽核用
	$scope.examine = function(id, status) {

		// 重設上一頁、下一頁
		$scope.step = 1;
	    $scope.stepPrev = false;
	    $scope.stepNext = true;
	    $scope.gotToStep($scope.step);
	    
		var action = "";
		var subStatus = "";
		var preStatus = "";

		switch(status){
			case '4':
				action = "退回";
				preStatus = "3"
			break;
			case '5':
				action = "通過";
				
				if ($scope.SubStatus != "" && $scope.SubStatus != null && $scope.SubStatus != 0) {
					subStatus = $scope.SubStatus;
				}

				// debug
//				console.log("i01.js → examine() → status=" + status + "；$scope.Status=" + $scope.Status + "；$scope.SubStatus=" + $scope.SubStatus);

				preStatus = "3";
			break;
		}

		// debug
		console.log("i01.js → examine() → status=" + status + "；subStatus=" + subStatus + "；preStatus=" + preStatus + "；$scope.Status=" + $scope.Status + "；$scope.SubStatus=" + $scope.SubStatus);

		bootbox.confirm(
			"確定要" + action + "此事件資料嗎？",
			function(result) {
				if (result) {
					
					var request = {
						Id : id, 
						Status : status,
						PreStatus : preStatus,
						TableName : "incident",
						Pre : "HCERT",
						Opinion : $scope.Opinion,
						SatisfactionTime : $scope.SatisfactionTime,
						SatisfactionProfessionalism: $scope.SatisfactionProfessionalism,
						SatisfactionService : $scope.SatisfactionService,
						SatisfactionReport : $scope.SatisfactionReport,
						SatisfactionTotal : $scope.SatisfactionTotal,
						SatisfactionRemark : $scope.SatisfactionRemark
					};
					
					$http
						.post('./api/i01/examine', request, csrf_config)
						.then(
							function(response) {
								if (response.data.success) {
									bootbox.dialog({
										message : "<span class='bigger-110'>" + response.data.msg + "</span>",
										buttons : {
											"success" : {
												"label" : "<i class='ace-icon fa fa-check'></i> 確定",
												"callback" : function() {
													$scope.queryData($scope.currentPage);
													$scope.closeEdit();
												}
											}															
										}
									});
								} else {
									bootbox.dialog({
										message : "<span class='bigger-110'>" + response.data.msg + "</span>",
										buttons : {
											"success" : {
												"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
												"className": 'btn-warning',
												"callback" : function() { }
											}
										}
									});
								}
							}).catch(function() {
								bootbox.dialog({
									message : "<span class='bigger-110'>事件資料" + action + "失敗</span>",
									buttons : {
										"success" : {
											"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
											"className": 'btn-danger',
											"callback" : function() { }
										}
									}
								});
							}).finally(function() { });
				}
			});
	};
	
	$scope.disable = function(deleteId) {
		bootbox.confirm({
		    message: "確定要停用嗎?",
		    buttons: {
		        confirm: {
		            label: '是',
		            className: 'btn-success'
		        },
		        cancel: {
		            label: '否',
		            className: 'btn-danger'
		        }
		    },
		    callback: function (result) {
		        if (result) {
		        	var request = { Id: deleteId };
		        	$http.post('./api/i01/disable', request, csrf_config)
					.then(function() {
						$scope.queryData($scope.currentPage);
					}).finally(function() {
						bootbox.hideAll();
					});
		        }
		    }
		});
	}
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		
		// debug
//		console.log("i01.js → openEdit() → ContactorId : " + $scope.ContactorId); 
				
		$("#divEdit").show();
		$("#divQuery").hide();

		// 重設上一頁、下一頁
		$scope.step = 1;
	    $scope.stepPrev = false;
	    $scope.stepNext = true;
	    $scope.gotToStep($scope.step);
	    
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = null;
//		$scope.PostDateTime = "";
		$scope.PostDateTime = moment().format("YYYY-MM-DD");
		$scope.PostId = "";
		$scope.ReporterName = 0;
		$scope.HandleName = 0;
		$scope.ContactorId = 0;
		$scope.ContactorTel = "";
		$scope.ContactorFax = "";
		$scope.ContactorEmail = "";
//		$scope.IncidentDiscoveryTime = "";
		$scope.IncidentDiscoveryTime = moment().format("YYYY-MM-DD");
		$scope.HostAmount = 0;
		$scope.ServerAmount = 0;
        $scope.InformationAmount = 0;
        $scope.OtherDeviceAmount = 0;
        $scope.OtherDeviceName = "";
        $scope.DeviceRemark = "";
        $scope.AssessDamage = "";
        $scope.AssessDamageRemark = "";
		$scope.IsOsOpt1 = false;
		$scope.IsOsOpt2 = false;
		$scope.IsOsOpt3 = false;
		$scope.IsOsOpt3Other = "";
		$scope.EventType = 1;
		$scope.IsEventType1Opt1 = false;
		$scope.IsEventType1Opt2 = false;
		$scope.IsEventType1Opt3 = false;
		$scope.IsEventType1Opt4 = false;
		$scope.IsEventType1Opt5 = false;
		$scope.IsEventType1Opt6 = false;
		$scope.IsEventType2Opt1 = false;
		$scope.IsEventType2Opt2 = false;
		$scope.IsEventType2Opt3 = false;
		$scope.IsEventType2Opt4 = false;
		$scope.IsEventType2Opt5 = false;
		$scope.IsEventType3Opt1 = false;
		$scope.IsEventType3Opt2 = false;
		$scope.IsEventType4Opt1 = false;
		$scope.IsEventType4Opt2 = false;
		$scope.IsEventType4Opt3 = false;
		$scope.IsEventType4Opt4 = false;
		$scope.EventType5Other = "";
		$scope.EventRemark = "";
		$scope.IsEventCause1Opt1 = false;
		$scope.IsEventCause1Opt2 = false;
		$scope.IsEventCause1Opt3 = false;
		$scope.IsEventCause1Opt4 = false;
		$scope.IsEventCause1Opt5 = false;
		$scope.IsEventCause1Opt6 = false;
		$scope.IsEventCause1Opt6Other = "";
		$scope.EventEvaluation = "";
		$scope.EventProcess = "";
		$scope.IsSecuritySetting1Opt1 = false;
		$scope.IsSecuritySetting1Opt2 = false;
		$scope.IsSecuritySetting1Opt3 = false;
		$scope.IsSecuritySetting1Opt4 = false;
		$scope.IsSecuritySetting1Opt5 = false;
		$scope.IsSecuritySetting1Opt6 = false;
		$scope.IsSecuritySetting1Opt7 = false;
		$scope.IsSecuritySetting1Opt8 = false;
		$scope.IsSecuritySetting1Opt9 = false;
		$scope.IsSecuritySetting1Opt10 = false;
		$scope.IsSecuritySetting1Opt11 = false;
		$scope.IsSecuritySetting1Opt12 = false;
		$scope.IsSecuritySetting1Opt13 = false;
		$scope.FinishDoOther = "";
		$scope.FinishDateTime = moment().format("YYYY-MM-DD");
		$scope.Status = "";
		
		// 簽核用
		$scope.PreStatus = "";
		$scope.Opinion = "";

		// 上傳附件用
		$scope.FileAttachment = null; 
		$scope.AttachmentDescription = "";
		$scope.itemAttachments = null;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divQuery").show();
		$("#divEdit").hide();
		$("#divReview").hide();
		$scope.btnIns = false;
		$scope.btnUpd = false;

		// 重設上一頁、下一頁
		$scope.step = 1;
	    $scope.stepPrev = false;
	    $scope.stepNext = true;
	    $scope.gotToStep($scope.step);
	    
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {

//		console.log("i01.js → editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
		
		var request = {
			Id : id,
			TableName : "incident"
		};
		
		$http.post('./api/i01/query/id', request, csrf_config).then(function(response) {
			
			$scope.openEdit();
			
			switch ($scope.Action) {
				case "view": // 瀏覽
				case "messageReview": // 審核
					$scope.Action = null;
					$scope.btnIns = false;
					$scope.btnUpd = false;
				break;
				
				default:
					$scope.btnIns = false;
					$scope.btnUpd = true;
					$("#divReview").hide();
				break;
			
			}

			// debug
//			console.log("i01.js → editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
//			console.log("i01.js → editData() → ContactorId=" + response.data[0].ContactorId);
		
			$scope.Id = response.data[0].Id;
			$scope.PostDateTime = moment(response.data[0].PostDateTime).format("YYYY-MM-DD");
			$scope.PostId = response.data[0].PostId;
			$scope.ReporterName = response.data[0].ReporterName;
			$scope.ReporterIdName = response.data[0].ReporterIdName;
			$scope.HandleName = response.data[0].HandleName;
			$scope.HandleIdName = response.data[0].HandleIdName;
			$scope.ContactorId = response.data[0].ContactorId;
			$scope.ContactorIdName = response.data[0].ContactorIdName;
			$scope.ContactorTel = response.data[0].ContactorTel;
			$scope.ContactorFax = response.data[0].ContactorFax;
			$scope.ContactorEmail = response.data[0].ContactorEmail;
			$scope.IncidentDiscoveryTime = moment(response.data[0].IncidentDiscoveryTime).format("YYYY-MM-DD");
			$scope.HostAmount = response.data[0].HostAmount;
			$scope.ServerAmount =response.data[0].ServerAmount;
	        $scope.InformationAmount = response.data[0].InformationAmount;
	        $scope.OtherDeviceAmount = response.data[0].OtherDeviceAmount;
	        $scope.OtherDeviceName = response.data[0].OtherDeviceName;
	        $scope.DeviceRemark = response.data[0].DeviceRemark;
	        $scope.AssessDamage = response.data[0].AssessDamage;
	        $scope.AssessDamageRemark = response.data[0].AssessDamageRemark;	       
			$scope.IsOsOpt1 = response.data[0].IsOsOpt1;
			$scope.IsOsOpt2 = response.data[0].IsOsOpt2;
			$scope.IsOsOpt3 = response.data[0].IsOsOpt3;
			$scope.IsOsOpt3Other = response.data[0].IsOsOpt3Other;
			$scope.EventType = response.data[0].EventType;
			$scope.IsEventType1Opt1 = response.data[0].IsEventType1Opt1;
			$scope.IsEventType1Opt2 = response.data[0].IsEventType1Opt2;
			$scope.IsEventType1Opt3 = response.data[0].IsEventType1Opt3;
			$scope.IsEventType1Opt4 = response.data[0].IsEventType1Opt4;
			$scope.IsEventType1Opt5 = response.data[0].IsEventType1Opt5;
			$scope.IsEventType1Opt6 = response.data[0].IsEventType1Opt6;
			$scope.IsEventType2Opt1 = response.data[0].IsEventType2Opt1;
			$scope.IsEventType2Opt2 = response.data[0].IsEventType2Opt2;
			$scope.IsEventType2Opt3 = response.data[0].IsEventType2Opt3;
			$scope.IsEventType2Opt4 = response.data[0].IsEventType2Opt4;
			$scope.IsEventType2Opt5 = response.data[0].IsEventType2Opt5;
			$scope.IsEventType3Opt1 = response.data[0].IsEventType3Opt1;
			$scope.IsEventType3Opt2 = response.data[0].IsEventType3Opt2;
			$scope.IsEventType4Opt1 = response.data[0].IsEventType4Opt1;
			$scope.IsEventType4Opt2 = response.data[0].IsEventType4Opt2;
			$scope.IsEventType4Opt3 = response.data[0].IsEventType4Opt3;
			$scope.IsEventType4Opt4 = response.data[0].IsEventType4Opt4;
			$scope.EventType5Other = response.data[0].EventType5Other;
			$scope.EventRemark = response.data[0].EventRemark;
			$scope.IsEventCause1Opt1 = response.data[0].IsEventCause1Opt1;
			$scope.IsEventCause1Opt2 = response.data[0].IsEventCause1Opt2;
			$scope.IsEventCause1Opt3 = response.data[0].IsEventCause1Opt3;
			$scope.IsEventCause1Opt4 = response.data[0].IsEventCause1Opt4;
			$scope.IsEventCause1Opt5 = response.data[0].IsEventCause1Opt5;
			$scope.IsEventCause1Opt6 = response.data[0].IsEventCause1Opt6;
			$scope.IsEventCause1Opt6Other = response.data[0].IsEventCause1Opt6Other;
			$scope.EventEvaluation = response.data[0].EventEvaluation;
			$scope.EventProcess = response.data[0].EventProcess;
			$scope.IsSecuritySetting1Opt1 = response.data[0].IsSecuritySetting1Opt1;
			$scope.IsSecuritySetting1Opt2 = response.data[0].IsSecuritySetting1Opt2;
			$scope.IsSecuritySetting1Opt3 = response.data[0].IsSecuritySetting1Opt3;
			$scope.IsSecuritySetting1Opt4 = response.data[0].IsSecuritySetting1Opt4;
			$scope.IsSecuritySetting1Opt5 = response.data[0].IsSecuritySetting1Opt5;
			$scope.IsSecuritySetting1Opt6 = response.data[0].IsSecuritySetting1Opt6;
			$scope.IsSecuritySetting1Opt7 = response.data[0].IsSecuritySetting1Opt7;
			$scope.IsSecuritySetting1Opt8 = response.data[0].IsSecuritySetting1Opt8;
			$scope.IsSecuritySetting1Opt9 = response.data[0].IsSecuritySetting1Opt9;
			$scope.IsSecuritySetting1Opt10 = response.data[0].IsSecuritySetting1Opt10;
			$scope.IsSecuritySetting1Opt11 = response.data[0].IsSecuritySetting1Opt11;
			$scope.IsSecuritySetting1Opt12 = response.data[0].IsSecuritySetting1Opt12;
			$scope.IsSecuritySetting1Opt13 = response.data[0].IsSecuritySetting1Opt13;
			$scope.FinishDoOther = response.data[0].FinishDoOther;
			$scope.FinishDateTime = moment(response.data[0].FinishDateTime).format("YYYY-MM-DD");
			$scope.Status = response.data[0].Status;
			$scope.SubStatus = response.data[0].SubStatus;
			
			$scope.SatisfactionTime = response.data[0].SatisfactionTime;
			$scope.SatisfactionProfessionalism = response.data[0].SatisfactionProfessionalism;
			$scope.SatisfactionService = response.data[0].SatisfactionService;
			$scope.SatisfactionReport = response.data[0].SatisfactionReport;
			$scope.SatisfactionTotal = response.data[0].SatisfactionTotal;
			$scope.SatisfactionRemark = response.data[0].SatisfactionRemark;
			
			// 流程紀錄用
			$scope.PreStatus = response.data[0].Status;
			$scope.MessageProcessLogData = response.data[0].MessageLog;

			// 附件清單資料
			$scope.queryAttachmentData($scope.Id);

			bootbox.hideAll();
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() {
			bootbox.hideAll();
		});
	}
	// Switch to Edit(Update) Mode End
	
	// Insert Item Start
	// 暫存：createData(false, false)
	// 暫存並離開：createData(true, false)
	// 送出：createData(true, true)
	$scope.createData = function(exit, check) {
		
		// 簽核用及流程紀錄用 - 開始
		$scope.IsWriteProcessLog = false;
		
		if (check) {
			// 送出
			// 1-編輯中 → 2-事件處理中
			$scope.Status = "2";
			$scope.IsWriteProcessLog = true;
		} else {
			// 暫存、暫存並離開
			// 無階段 → 1-編輯中
			$scope.Status = "1";
		}
		// 簽核用及流程紀錄用 - 結束

		// debug
//		console.log("i01.js → createData() → exit = " + exit + "；check=" + check + "；Status=" + $scope.Status + "；IsWriteProcessLog=" + $scope.IsWriteProcessLog);
		
		if (!$scope.editForm.$valid && check) {
			
			bootbox.dialog({
				message : "<span class='bigger-110'>請修正不符合規定的欄位</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i> 確定",
						"callback" : function() {
						}
					}
				}
			});
		
		} else {
			
			// debug
//			console.log("i01.js → createData() → ContactorId=" + $scope.ContactorId);

			var request = {
				Id : null,
				PostDateTime : $scope.PostDateTime,
				ReporterName : $scope.ReporterName,
				HandleName : $scope.HandleName,
				ContactorId : $scope.ContactorId,
				ContactorTel : $scope.ContactorTel,
				ContactorFax : $scope.ContactorFax,
				ContactorEmail : $scope.ContactorEmail,
				IncidentDiscoveryTime : $scope.IncidentDiscoveryTime,
				HostAmount : $scope.HostAmount,
				ServerAmount : $scope.ServerAmount,
				InformationAmount : $scope.InformationAmount,
				OtherDeviceAmount : $scope.OtherDeviceAmount,
				OtherDeviceName : $scope.OtherDeviceName,
				DeviceRemark : $scope.DeviceRemark,
				AssessDamage : $scope.AssessDamage,
				AssessDamageRemark : $scope.AssessDamageRemark,
				IsOsOpt1 : $scope.IsOsOpt1,
				IsOsOpt2 : $scope.IsOsOpt2,
				IsOsOpt3 : $scope.IsOsOpt3,
				IsOsOpt3Other : $scope.IsOsOpt3Other,
				EventType : $scope.EventType,
				IsEventType1Opt1 : $scope.IsEventType1Opt1,
				IsEventType1Opt2 : $scope.IsEventType1Opt2,
				IsEventType1Opt3 : $scope.IsEventType1Opt3,
				IsEventType1Opt4 : $scope.IsEventType1Opt4,
				IsEventType1Opt5 : $scope.IsEventType1Opt5,
				IsEventType1Opt6 : $scope.IsEventType1Opt6,
				IsEventType2Opt1 : $scope.IsEventType2Opt1,
				IsEventType2Opt2 : $scope.IsEventType2Opt2,
				IsEventType2Opt3 : $scope.IsEventType2Opt3,
				IsEventType2Opt4 : $scope.IsEventType2Opt4,
				IsEventType2Opt5 : $scope.IsEventType2Opt5,
				IsEventType3Opt1 : $scope.IsEventType3Opt1,
				IsEventType3Opt2 : $scope.IsEventType3Opt2,
				IsEventType4Opt1 : $scope.IsEventType4Opt1,
				IsEventType4Opt2 : $scope.IsEventType4Opt2,
				IsEventType4Opt3 : $scope.IsEventType4Opt3,
				IsEventType4Opt4 : $scope.IsEventType4Opt4,
				EventType5Other : $scope.EventType5Other,
				EventRemark : $scope.EventRemark,
				IsEventCause1Opt1 : $scope.IsEventCause1Opt1,
				IsEventCause1Opt2 : $scope.IsEventCause1Opt2,
				IsEventCause1Opt3 : $scope.IsEventCause1Opt3,
				IsEventCause1Opt4 : $scope.IsEventCause1Opt4,
				IsEventCause1Opt5 : $scope.IsEventCause1Opt5,
				IsEventCause1Opt6 : $scope.IsEventCause1Opt6,
				IsEventCause1Opt6Other : $scope.IsEventCause1Opt6Other,
				EventEvaluation : $scope.EventEvaluation,
				EventProcess : $scope.EventProcess,
				IsSecuritySetting1Opt1 : $scope.IsSecuritySetting1Opt1,
				IsSecuritySetting1Opt2 : $scope.IsSecuritySetting1Opt2,
				IsSecuritySetting1Opt3 : $scope.IsSecuritySetting1Opt3,
				IsSecuritySetting1Opt4 : $scope.IsSecuritySetting1Opt4,
				IsSecuritySetting1Opt5 : $scope.IsSecuritySetting1Opt5,
				IsSecuritySetting1Opt6 : $scope.IsSecuritySetting1Opt6,
				IsSecuritySetting1Opt7 : $scope.IsSecuritySetting1Opt7,
				IsSecuritySetting1Opt8 : $scope.IsSecuritySetting1Opt8,
				IsSecuritySetting1Opt9 : $scope.IsSecuritySetting1Opt9,
				IsSecuritySetting1Opt10 : $scope.IsSecuritySetting1Opt10,
				IsSecuritySetting1Opt11 : $scope.IsSecuritySetting1Opt11,
				IsSecuritySetting1Opt12 : $scope.IsSecuritySetting1Opt12,
				IsSecuritySetting1Opt13 : $scope.IsSecuritySetting1Opt13,
				FinishDoOther : $scope.FinishDoOther,
				FinishDateTime : $scope.FinishDateTime,
				Status : $scope.Status,
				SubStatus : $scope.SubStatus,
				
				PreStatus : "1",
				TableName : "incident",
				Pre : "HCERT",
				IsWriteProcessLog : $scope.IsWriteProcessLog,
				Check : check
			};
		
			$http.post('./api/i01/create', request, csrf_config).then(
				function(response) {
					if (response.data.success) {

						$scope.PostId = response.data.PostId
						
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-success',
								}
							},
							callback: function() {
								// 暫存並離開按鍵用 - 開始
								if ($scope.pageRows == 1 && $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}

								$scope.queryData($scope.currentPage);
								
								if (exit) {	
									$scope.closeEdit();
								} else {
									$scope.btnIns = false;
									$scope.btnUpd = true;
									$scope.PreStatus = "1"
									$scope.Id = response.data.Id
								}
								// 暫存並離開按鍵用 - 結束
							}
						});
					} else {
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-danger',
								}
							},
							callback: function() { }
						});
					}
				}).catch(function() {
					bootbox.alert({
						message : globalInsertDataFail,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-danger',
							}
						},
						callback: function() { }
					});
				}).finally(function() { });

		}
		
	};
	// Insert Data End

	// Update Data Start
	// 暫存：updateData(false, false)
	// 暫存並離開：updateData(true, false)
	// 送出：updateData(true, true)
	$scope.updateData = function(exit,check) {	

		// 簽核用及流程紀錄用 - 開始
		$scope.IsWriteProcessLog = false;
		
		if (check) {
			// 送出
			if ($scope.Status == "1") {
				// 1-編輯中 → 2-事件處理中
				$scope.Status = "2";
				$scope.IsWriteProcessLog = true;
			} else if ($scope.Status == "2" || $scope.Status == "4") {
				// 2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中
				$scope.Status = "3";
				$scope.IsWriteProcessLog = true;
			}
		} else {
			// 暫存、暫存並離開
			// 不改變Status
		}
		// 簽核用及流程紀錄用 - 結束

		// debug
//		console.log("i01.js → updateData() → exit = " + exit + "；check=" + check + "；Status=" + $scope.Status + "；IsWriteProcessLog=" + $scope.IsWriteProcessLog);
		
		if (!$scope.editForm.$valid && check) {
			
			bootbox.dialog({
				message : "<span class='bigger-110'>請修正不符合規定的欄位</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i> 確定",
						"callback" : function() {
						}
					}
				}
			});
		
		} else {
		
			// debug
//			console.log("i01.js → updateData() → ReporterName=" + $scope.ReporterName + "；HandleName=" + $scope.HandleName + "；ContactorId=" + $scope.ContactorId);
			
			var request = {
				Id : $scope.Id,
				PostDateTime : $scope.PostDateTime,
				ReporterName : $scope.ReporterName,
				HandleName : $scope.HandleName,
				ContactorId : $scope.ContactorId,
				ContactorTel : $scope.ContactorTel,
				ContactorFax : $scope.ContactorFax,
				ContactorEmail : $scope.ContactorEmail,
				IncidentDiscoveryTime : $scope.IncidentDiscoveryTime,
				HostAmount : $scope.HostAmount,
				ServerAmount : $scope.ServerAmount,
				InformationAmount : $scope.InformationAmount,
				OtherDeviceAmount : $scope.OtherDeviceAmount,
				OtherDeviceName : $scope.OtherDeviceName,
				DeviceRemark : $scope.DeviceRemark,
				AssessDamage : $scope.AssessDamage,
				AssessDamageRemark : $scope.AssessDamageRemark,
				IsOsOpt1 : $scope.IsOsOpt1,
				IsOsOpt2 : $scope.IsOsOpt2,
				IsOsOpt3 : $scope.IsOsOpt3,
				IsOsOpt3Other : $scope.IsOsOpt3Other,
				EventType : $scope.EventType,
				IsEventType1Opt1 : $scope.IsEventType1Opt1,
				IsEventType1Opt2 : $scope.IsEventType1Opt2,
				IsEventType1Opt3 : $scope.IsEventType1Opt3,
				IsEventType1Opt4 : $scope.IsEventType1Opt4,
				IsEventType1Opt5 : $scope.IsEventType1Opt5,
				IsEventType1Opt6 : $scope.IsEventType1Opt6,
				IsEventType2Opt1 : $scope.IsEventType2Opt1,
				IsEventType2Opt2 : $scope.IsEventType2Opt2,
				IsEventType2Opt3 : $scope.IsEventType2Opt3,
				IsEventType2Opt4 : $scope.IsEventType2Opt4,
				IsEventType2Opt5 : $scope.IsEventType2Opt5,
				IsEventType3Opt1 : $scope.IsEventType3Opt1,
				IsEventType3Opt2 : $scope.IsEventType3Opt2,
				IsEventType4Opt1 : $scope.IsEventType4Opt1,
				IsEventType4Opt2 : $scope.IsEventType4Opt2,
				IsEventType4Opt3 : $scope.IsEventType4Opt3,
				IsEventType4Opt4 : $scope.IsEventType4Opt4,
				EventType5Other : $scope.EventType5Other,
				EventRemark : $scope.EventRemark,
				IsEventCause1Opt1 : $scope.IsEventCause1Opt1,
				IsEventCause1Opt2 : $scope.IsEventCause1Opt2,
				IsEventCause1Opt3 : $scope.IsEventCause1Opt3,
				IsEventCause1Opt4 : $scope.IsEventCause1Opt4,
				IsEventCause1Opt5 : $scope.IsEventCause1Opt5,
				IsEventCause1Opt6 : $scope.IsEventCause1Opt6,
				IsEventCause1Opt6Other : $scope.IsEventCause1Opt6Other,
				EventEvaluation : $scope.EventEvaluation,
				EventProcess : $scope.EventProcess,
				IsSecuritySetting1Opt1 : $scope.IsSecuritySetting1Opt1,
				IsSecuritySetting1Opt2 : $scope.IsSecuritySetting1Opt2,
				IsSecuritySetting1Opt3 : $scope.IsSecuritySetting1Opt3,
				IsSecuritySetting1Opt4 : $scope.IsSecuritySetting1Opt4,
				IsSecuritySetting1Opt5 : $scope.IsSecuritySetting1Opt5,
				IsSecuritySetting1Opt6 : $scope.IsSecuritySetting1Opt6,
				IsSecuritySetting1Opt7 : $scope.IsSecuritySetting1Opt7,
				IsSecuritySetting1Opt8 : $scope.IsSecuritySetting1Opt8,
				IsSecuritySetting1Opt9 : $scope.IsSecuritySetting1Opt9,
				IsSecuritySetting1Opt10 : $scope.IsSecuritySetting1Opt10,
				IsSecuritySetting1Opt11 : $scope.IsSecuritySetting1Opt11,
				IsSecuritySetting1Opt12 : $scope.IsSecuritySetting1Opt12,
				IsSecuritySetting1Opt13 : $scope.IsSecuritySetting1Opt13,
				FinishDoOther : $scope.FinishDoOther,
				FinishDateTime : $scope.FinishDateTime,
				Status : $scope.Status,
				SubStatus : $scope.SubStatus,
				
				PreStatus : $scope.PreStatus,
				TableName : "incident",
				Pre : "HCERT",
				IsWriteProcessLog : $scope.IsWriteProcessLog,
				Check : check
			};
			$http.post('./api/i01/update', request, csrf_config).then(
				function(response) {
					if (response.data.success) {

						$scope.PostId = response.data.PostId;
						
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-success',
								}
							},
							callback: function() {
								// 暫存並離開按鍵用 - 開始
								if ($scope.pageRows == 1 && $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}
								
								if (exit) {
									$scope.queryData($scope.currentPage);
									$scope.closeEdit();
								}
								// 暫存並離開按鍵用 - 結束
							}
						});
					} else {
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-danger',
								}
							},
							callback: function() { }
						});
					}
				}).catch(function() {
					bootbox.alert({
						message : globalUpdateDataFail,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-danger',
							}
						},
						callback: function() { }
					});
				}).finally(function() { });

		}
	
	};
	// Update Data End

	// Delete Item Start
	$scope.deleteData = function(id) {
		bootbox.confirm({
			message: globalSureDeleteItem,
			buttons: {
				confirm: {
					label : '<i class="fas fa-fw fa-check"></i>' + btnSure,
					className : 'btn-success'
				},
				cancel: {
					label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
					className : 'btn-default'
				}
			},
			callback: function(result) {
				if (result) {
					var request = {
						Id: id
					};
					$http.post('./api/i01/delete', request, csrf_config).then(function(response) {
						if (response.data.success) {
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-success',
									}
								},
								callback: function() {
									if ($scope.pageRows == 1 && $scope.currentPage > 1) {
										$scope.currentPage = $scope.currentPage - 1;
									}
									$scope.queryData($scope.currentPage);
								}
							});
						} else {
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-danger',
									}
								},
								callback: function() { }
							});
						}
					}).catch(function() {
						bootbox.alert({
							message : globalDeleteDataFail,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-danger',
								}
							},
							callback: function() { }
						});
					}).finally(function() { });
				}
			}
		});
	}
	// Delete Item End
	
	// Get Attachments Start
	$scope.queryAttachmentData = function(id) {
		var request = {
			IncidentId : id
		};
		$http.post('./api/i01/attach/query', request, csrf_config).then(function(response) {
			$scope.itemAttachments = response.data.datatable;
		}).catch(function() { }).finally(function() { });
	};
	// Get Attachments End
	
	// Delete Attachment Start
	$scope.deleteAttachmentData = function(id) {
		bootbox.confirm({
			message: globalSureDeleteItem,
			buttons: {
				confirm: {
					label : '<i class="fas fa-fw fa-check"></i>' + btnSure,
					className : 'btn-success'
				},
				cancel: {
					label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
					className : 'btn-default'
				}
			},
			callback: function(result) {
				if (result) {
					var request = {
						Id : id,
						IncidentId : $scope.Id
					};
					$http.post('./api/i01/attach/delete', request, csrf_config).then(function(response) {
						if (response.data.success) {
							$scope.queryAttachmentData($scope.Id);
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-success',
									}
								},
								callback: function() {
									if ($scope.pageRows == 1 && $scope.currentPage > 1) {
										$scope.currentPage = $scope.currentPage - 1;
									}
									$scope.queryData($scope.currentPage);
								}
							});
						} else {
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-danger',
									}
								},
								callback: function() { }
							});
						}
					}).catch(function() {
						bootbox.alert({
							message : globalDeleteDataFail,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-danger',
								}
							},
							callback: function() { }
						});
					}).finally(function() { });
				}
			}
		});
	}
	// Delete Attachment End
	
	// Upload Attachment Start
	$scope.uploadAttachment = function() {
		if ($scope.FileAttachment == null 
			|| ( $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "jpg" 
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "gif"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "doc"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "docx"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "xls"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "xlsx" 
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "ppt"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "pptx"
				&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "pdf"
			)) {
			bootbox.alert({
				message : globalAcceptAttachmentFormat,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		} else {
			if ( $scope.AttachmentDescription == null )
				$scope.AttachmentDescription = ""; 
			Upload.upload({
					url : './api/i01/attach/upload',
					data : {
						file : $scope.FileAttachment,
						id : $scope.Id,
						FileDesc : $scope.AttachmentDescription
					},
					headers: header
				})
				.then(
					function(response) {
						if (response.data.success) {
							$scope.queryAttachmentData($scope.Id);
							$scope.FileAttachment = null; 
							$scope.AttachmentDescription = "";
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-success',
									}
								},
								callback: function() {
								}
							});
						} else {
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-danger',
									}
								},
								callback: function() { }
							});
						}
					});
		}
	};
	// Upload Attachment End

	
	// Get Status Count Start
	$scope.queryButtonCount = function() {
		
		if ($scope.QuerySPostDateTime == "")
			$scope.QuerySPostDateTime = null;
		if ($scope.QueryEPostDateTime == "")
			$scope.QueryEPostDateTime = null;
		if ($scope.QueryReporterName == "")
			$scope.QueryReporterName = null;
		if ($scope.QueryHandleName == "")
			$scope.QueryHandleName = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			SPostDateTime : $scope.QuerySPostDateTime,
			EPostDateTime : $scope.QueryEPostDateTime,
			QueryReporterName : $scope.QueryReporterName,
			QueryHandleName : $scope.QueryHandleName,
			Status : $scope.QueryStatus
		};

		$http.post('./api/i01/query/button/count', request, csrf_config).then(function(response) {
			$scope.button_count_allitems = response.data.datatable;

			// debug
//			console.log("response="+JSON.stringify(response.data.datatable));
			
			$scope.getPageButtonCount();
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	}
	$scope.queryButtonCount();
	// Get Status Count End
	
	
	// Show Status Count Start
	$scope.getCount = function(Status){
		var total = 0;
		angular.forEach($scope.button_count_allitems, function(value, key) {

			// debug
//			console.log("value.Status = " + value.Status);
//			console.log("Status = " + Status);
//			console.log("value.Count = " + value.Count);
			
			if(value.Status == Status ) {
				total = value.Count;
			}
		});

		return total;
    }
	// Show Status Count End

	
	$scope.getPageButtonCount = function(){
		
		$scope.ButtonCount1 = $scope.getCount(1);
		$scope.ButtonCount2 = $scope.getCount(2) + $scope.getCount(4);
		$scope.ButtonCount3 = $scope.getCount(3);

		// debug
//		console.log("ButtonCount1 = " + $scope.ButtonCount1);
//		console.log("ButtonCount2 = " + $scope.ButtonCount2);
//		console.log("ButtonCount3 = " + $scope.ButtonCount3);
		
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		// 重新 reload 功能選單 - 事件處理之待處理件數 (參考 navbar.jsp 中程式)
		if ($("#subsystem_inc").length > 0) {
			var subsystem_inc_count = 0;
			if ($("#form_inc").length > 0) {
				$
					.ajax(
						{
							url: "../pub/api/count/inc",
							method: "POST",
							data: request,
							headers: header,
							datatype: "json",
							async: false
						})
					.done(
						function (response) {
							var count = response.count;
							//alert('subsystem_inc_count = ' + subsystem_inc_count);
							//alert('count = ' + count);
							if (count > 0) {
								$("#form_inc").html(count);
								subsystem_inc_count = subsystem_inc_count + count;
								//alert('subsystem_inc_count = ' + subsystem_inc_count);
							} else {
								$("#form_inc").html('');
							}
						});
			}
			
			if (subsystem_inc_count > 0) {
				$("#subsystem_inc > .badge").html(subsystem_inc_count);
			} else {
				$("#subsystem_inc > .badge").html('');
			}
		}
		
	}

	
	// Get Org Start
//	$scope.getOrg = function() {
//		var request = {};
//		$http.post('./api/i01/query/org', request, csrf_config).then(function(response) {
//			$scope.orgs = response.data;
//		}).catch(function() {
//			bootbox.alert({
//				message : globalReadDataFail,
//				buttons : {
//					ok : {
//						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//						className : 'btn-danger',
//					}
//				},
//				callback: function() { }
//			});
//		}).finally(function() { });
//	};
//	$scope.getOrg();
	// Get Org End

	
	// Get Org Authority Unit Start
	$scope.getOrgAuthorityUnit = function() {
		var request = {};
		$http.post('./api/i01/query/orgauthorityunit', request, csrf_config).then(function(response) {
			$scope.org2s = response.data;

//			console.log("$scope.org2s : " + $scope.org2s);

		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	};
	$scope.getOrgAuthorityUnit();
	// Get Org Authority Unit End

	
	// Get Org Member Unit Start
	$scope.getOrgMemberUnit = function() {
		var request = {};
		$http.post('./api/i01/query/orgmemberunit', request, csrf_config).then(function(response) {
			$scope.org3s = response.data;
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	};
	$scope.getOrgMemberUnit();
	// Get Org Member Unit End

	
	// Get Member Start
//	$scope.getMember = function() {
//		alert($scope.HandleName);
//		var request = {};
//		
//		$http.post('./api/i01/query/member', request, csrf_config).then(function(response) {
//			$scope.members = response.data;
//		}).catch(function() {
//			bootbox.alert({
//				message : globalReadDataFail,
//				buttons : {
//					ok : {
//						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//						className : 'btn-danger',
//					}
//				},
//				callback: function() { }
//			});
//		}).finally(function() { });
//	};
//	$scope.getMember();
	// Get Member End

	// Show Handle Member Start
	$scope.showHandleMember = function() {

		var request = {
			OrgId : $scope.HandleName
		};
		
		$scope.ContactorId = '';
		$scope.ContactorIdName = '';
		$scope.ContactorTel = '';
		$scope.ContactorFax = '';
		$scope.ContactorEmail = '';
		
		$http.post('./api/i01/query/member', request, csrf_config).then(function(response) {
			$scope.members = response.data;
			
			$scope.ContactorId = $scope.members[0]['Id'];
			$scope.ContactorIdName = $scope.members[0]['Name'];
			$scope.ContactorTel = $scope.members[0]['CityPhone'];
			$scope.ContactorFax = $scope.members[0]['FaxPhone'];
			$scope.ContactorEmail = $scope.members[0]['Email'];
				
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	};
	// Show Handle Member End
	
	
	// Show Member Start
//	$scope.showMember = function() {
//		
//		// debug
////		console.log("i01.js → showMember() → ContactorId (" + $scope.members.length + ") : " + $scope.ContactorId);
//		
//		$scope.ContactorTel = '';
//		$scope.ContactorFax = '';
//		$scope.ContactorEmail = '';
//		
//		for (var iii = 0; iii < $scope.members.length; iii++) {
//			
//			// debug
////			console.log("i01.js → showMember() → Id : " + $scope.members[iii]['Id'] + "；ContactorId : " + $scope.ContactorId);
//
//			if ($scope.members[iii]['Id'] == $scope.ContactorId) {
//					
//					// debug
////					console.log("i01.js → showMember() → phone : " + $scope.members[iii]['CityPhone'] + "；ContactorFax : " + $scope.members[iii]['FaxPhone'] + "；Email : " + $scope.members[iii]['Email']);
//					
//				$scope.ContactorTel = $scope.members[iii]['CityPhone'];
//				$scope.ContactorFax = $scope.members[iii]['FaxPhone'];
//				$scope.ContactorEmail = $scope.members[iii]['Email'];
//				
//				break;
//			}
//							
//		}
//		
//	};
	// Show Member End

	// Get SubStatus Start
	$scope.getSubStatus = function() {
		var request = {};
		$scope.SubStatus = '';
		
		$http.post('./api/i01/query/substatus', request, csrf_config).then(function(response) {
			$scope.SubStatuss = response.data;
			
			if ($scope.SubStatuss != null) {
				$scope.SubStatus = SubStatuss[0]['SubStatus'];
			}
			
			// debug
//			console.log("i01.js → getSubStatus() → $scope.SubStatus : " + $scope.SubStatus);
		
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	};
	// Get SubStatus End
	
    // Step Change Start
    $scope.step = 1;
    $scope.stepPrev = false;
    $scope.stepNext = true;
    $scope.gotToStep = function(step) {
        $scope.stepPrev = true;
        $scope.stepNext = true;
        if ($scope.Status == "" || $scope.Status == 1 || $scope.Status == 2 || $scope.Status == 4 || $scope.Status == 5) {
            switch (step) {
                case 0:
                case 1:
                    $scope.step = 1;
                    $scope.stepPrev = false;
                    $('#tabIncidentStep a[href="#tabIncidentStep1"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                case 2:
                    $scope.step = 2;

                    // 依狀態控制下一頁是否不給點選
                    switch ($scope.Status) {
                    	
                    	case 2:
                    	case 3:
                    	case 4:
                    	case 5:
                    		break;
                    	default:
                    		$scope.stepNext = false;
                    		break;
                    	
                    }
                    
                    $('#tabIncidentStep a[href="#tabIncidentStep2"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                case 3:
                    $scope.step = 3;

                    // 依狀態控制下一頁是否不給點選
                    switch ($scope.Status) {
                    	
                    	case 2:
                    	case 3:
                    	case 4:
                    	case 5:
                    		$scope.stepNext = false;
                    		break;
                    	default:
                    		break;
                    	
                    }
                    
                    $('#tabIncidentStep a[href="#tabIncidentStep3"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                default:
                    break;
            }
        } else {
            switch (step) {
                case 0:
                case 1:
                    $scope.step = 1;
                    $scope.stepPrev = false;
                    $('#tabIncidentStep a[href="#tabIncidentStep1"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                case 2:
                    $scope.step = 2;

                    // 依狀態控制下一頁是否不給點選
                    switch ($scope.Status) {
                    	
                    	case 2:
                    	case 3:
                    	case 4:
                    	case 5:
                    		break;
                    	default:
                    		$scope.stepNext = false;
                    		break;
                    	
                    }
                    
                    $('#tabIncidentStep a[href="#tabIncidentStep2"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                case 3:
                    $scope.step = 3;

                    // 依狀態控制下一頁是否不給點選
                    switch ($scope.Status) {
                    	
                    	case 2:
                    	case 3:
                    	case 4:
                    	case 5:
                    		$scope.stepNext = false;
                    		break;
                    	default:
                    		break;
                    	
                    }
                    
                    $('#tabIncidentStep a[href="#tabIncidentStep3"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabIncidentStep').offset().top
                    }, 0);
                    break;
                default:
                    break;
            }
        }
    };
    // Step Change End

    // Query Step Change Start
    $scope.queryStep = 1;
    $scope.queryStepPrev = false;
    $scope.queryStepNext = true;
    $scope.gotToQueryStep = function(queryStep) {    		
        $scope.queryStepPrev = true;
        $scope.queryStepNext = true;        
        switch (queryStep) {
            case 0:
            case 1:
                $scope.queryStep = 1;
                $scope.queryStepPrev = false;
                $('#tabIncidentQueryStep a[href="#tabIncidentQueryStep1"]').tab('show');
                $("html, body").animate({
                    scrollTop: $('#tabIncidentQueryStep').offset().top
                }, 0);
                break;
            case 2:
                $scope.queryStep = 2;

                // 依狀態控制下一頁是否不給點選
                switch ($scope.Status) {
                	
                	case 2:
                	case 3:
                	case 4:
                	case 5:
                		break;
                	default:
                		$scope.queryStepNext = false;
                		break;
                	
                }
                
                $('#tabIncidentQueryStep a[href="#tabIncidentQueryStep2"]').tab('show');
                $("html, body").animate({
                    scrollTop: $('#tabIncidentQueryStep').offset().top
                }, 0);
                break;
            case 3:
                $scope.queryStep = 3;
                
                // 依狀態控制下一頁是否不給點選
                switch ($scope.Status) {
                	
                	case 2:
        			case 4:                			
        				$scope.queryStepNext = false;
        				break;
        			case 3:                  			
        			case 5:                    		                    		
        			default:
        				break;
                	
                }
                
                $('#tabIncidentQueryStep a[href="#tabIncidentQueryStep3"]').tab('show');
                $("html, body").animate({
                    scrollTop: $('#tabIncidentQueryStep').offset().top
                }, 0);
                break;            
            case 4:
                $scope.queryStep = 4;
                
                // 依狀態控制下一頁是否不給點選
                switch ($scope.Status) {
                	
                	case 2:
                	case 3:
                	case 4:
                	case 5:                		
                		$scope.queryStepNext = false;
                		break;
                	default:
                		break;
                	
                }
                
                $('#tabIncidentQueryStep a[href="#tabIncidentQueryStep4"]').tab('show');
                $("html, body").animate({
                    scrollTop: $('#tabIncidentQueryStep').offset().top
                }, 0);
                break;
            default:
                break;
        }
    };
    // Query Step Change End
    
    $scope.getByNotificationId = function() {
    	
     	var request = {NotificationId : $scope.NotificationId};	
    	
    	$http.post('./api/i01/query/notificationId', request, csrf_config).then(function(response) {
    		$scope.Id = response.data.Id;
    		if ($scope.Id != null)
    			$scope.review($scope.Id, "view")		
    	
    	}).catch(function() {
    		bootbox.alert({
    			message : globalReadDataFail,
    			buttons : {
    				ok : {
    					label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
    					className : 'btn-danger',
    				}
    			},
    			callback: function() { }
    		});
    	}).finally(function() { });
        }

    $("#laoding1").show();
    $("#divEdit").show();   
        
    $scope.NotificationId = $cookies.get("NotificationId");    
    $cookies.remove("NotificationId", {path:"/"});  	
    if ( $scope.NotificationId != null){
    		$scope.getByNotificationId()
    }
}