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

	}
	// Switch to Edit(Update) Mode End
	
	// Insert Item Start
	// 暫存：createData(false, false)
	// 暫存並離開：createData(true, false)
	// 送出：createData(true, true)
	$scope.createData = function(exit, check) {
		
	};
	// Insert Data End

	// Update Data Start
	// 暫存：updateData(false, false)
	// 暫存並離開：updateData(true, false)
	// 送出：updateData(true, true)
	$scope.updateData = function(exit,check) {	

		
	
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