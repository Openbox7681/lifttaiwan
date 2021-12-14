var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {

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
	
	// 顯示流程簽核紀錄畫面
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
	
	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.QueryNewsManagementGroupId = null;
		$scope.QueryPostDateTime = null;
		$scope.QueryTitle = null;
		$scope.QueryIsEnable = null;
		$scope.QueryIsPublic = null;
		$scope.QueryStatus = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
	$('input[name="QueryPostDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#QueryPostDateTime").on("dp.change", function(e) {	       
		$scope.QueryPostDateTime = $('#QueryPostDateTime').val();
	});
	
	$('input[name="PostDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#PostDateTime").on("dp.change", function(e) {	       
		$scope.PostDateTime = $('#PostDateTime').val();
	});
	
	$('input[name="StartDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#StartDateTime").on("dp.change", function(e) {	       
		$scope.StartDateTime = $('#StartDateTime').val();
		if ($scope.StartDateTime > $scope.EndDateTime && $scope.EndDateTime != null) {
			$scope.EndDateTime = $scope.StartDateTime;
			$('#EndDateTime').val($scope.EndDateTime)
		}
	});
	
	$('input[name="EndDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#EndDateTime").on("dp.change", function(e) {	       
		$scope.EndDateTime = $('#EndDateTime').val();
		if ($scope.EndDateTime < $scope.StartDateTime && $scope.StartDateTime != null) {
			$scope.StartDateTime = $scope.EndDateTime;
			$('#StartDateTime').val($scope.StartDateTime)
		}
	});
	
	
	
	

	$scope.edit = function(id) {

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});

		$scope.Id = null;
		$scope.PostId = null;
		$scope.NewsManagementGroupId = null;
		$scope.PostDateTime = null;
		$scope.Title = null;
		$scope.SourceName = null;
		$scope.SourceLink = null;
		$scope.ContentType = null;
		$scope.Content = null;
		$scope.ExternalLink = null;
		$scope.IsBreakLine = false;
		$scope.StartDateTime = null;			
		$scope.EndDateTime = null;
		$scope.IsEnable = false;
		$scope.IsPublic = false;
		$scope.Status = null;
		$scope.Sort = null;
		
		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
//		$scope.editData(id);

		$scope.btnIns = false;
		$scope.btnUpd = true;
		$("#divQuery").hide();

	}
	
	

	
	// Switch to Edit(Insert) Mode Start
	// 新增、編輯、瀏覽用
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = null;
		$scope.PostId = "";
		$scope.NewsManagementGroupId = null;
		$scope.PostType = "1";
		$scope.PostDateTime = moment().format("YYYY-MM-DD");
		$scope.Title = "";
		$scope.SourceName = "";
		$scope.SourceLink = "";
		$scope.ContentType = "";
		$scope.Content = "";
		$scope.ExternalLink = "";
		$scope.IsBreakLine = true;
		$scope.StartDateTime = "";
		$scope.EndDateTime = "";
		$scope.IsEnable = false;
		$scope.IsPublic = false;
		$scope.Status = "";
		$scope.Sort = 0;
		
		// 簽核用
		$scope.PreStatus = "";
		$scope.Opinion = "";
		
		// 上傳圖檔用
		$scope.FileImage = null; 
		$scope.ImageDescription = "";
		$scope.itemImages = null;
		
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
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	// 編輯、瀏覽用
	$scope.editData = function(id) {
		
// console.log("editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd="
// + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
		
		var request = {
			Id : id,
			TableName : "news_management"
		};
		
		$http.post('./api/s31/query/id', request, csrf_config).then(function(response) {
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

// console.log("editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd="
// + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
		
			$scope.Id = response.data[0].Id;
			$scope.PostId = response.data[0].PostId;
			$scope.NewsManagementGroupId = response.data[0].NewsManagementGroupId;
			if ($scope.NewsManagementGroupId == 0) {
				$scope.NewsManagementGroupId = null;
			}
			$scope.NewsManagementGroupName = response.data[0].NewsManagementGroupName;
			$scope.PostType = response.data[0].PostType;
			$scope.PostDateTime = response.data[0].PostDateTime;
			$scope.Title = response.data[0].Title;
			$scope.SourceName = response.data[0].SourceName;
			$scope.SourceLink = response.data[0].SourceLink;
			$scope.ContentType = response.data[0].ContentType;
			$scope.Content = response.data[0].Content;
			$scope.ExternalLink = response.data[0].ExternalLink;
			$scope.IsBreakLine = response.data[0].IsBreakLine;
			$scope.StartDateTime = response.data[0].StartDateTime;
			$scope.EndDateTime = response.data[0].EndDateTime;
			$scope.IsEnable = response.data[0].IsEnable;
			$scope.IsPublic = response.data[0].IsPublic;
			$scope.Status = response.data[0].Status;
			$scope.Sort = response.data[0].Sort;
			
			// 流程紀錄用
			$scope.PreStatus = response.data[0].Status;
			$scope.MessageProcessLogData = response.data[0].MessageLog;

			// 圖檔清單資料
			$scope.queryImageData($scope.Id);
			
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
	
		// 簽核用
		if (check) {
			$scope.Status = "3";
		} else {
			$scope.Status = "1";
		}
		
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
			
			// 流程紀錄用 - 開始
			$scope.IsWriteProcessLog = false
			
			if ($scope.Status == "3") {
				$scope.IsWriteProcessLog = true;
			}
			// 流程紀錄用 - 結束
			
			switch($scope.ContentType) {
				case "1":
					$scope.ExternalLink = "";
					// $scope.IsBreakLine = true;
					break;
				case "2":
					$scope.Content = "";
					$scope.IsBreakLine = false;
					break;
			}
			
			if ($scope.StartDateTime == "")
				$scope.StartDateTime = null;
			if ($scope.EndDateTime == "")
				$scope.EndDateTime = null;

// console.log("s31.js → createData() → $scope.IsBreakLine="
// +$scope.IsBreakLine);

			var request = {
				Id : null,
				PostType : "1",
				NewsManagementGroupId : $scope.NewsManagementGroupId,
				PostDateTime : $scope.PostDateTime,
				Title : $scope.Title,
				SourceName : $scope.SourceName,
				SourceLink : $scope.SourceLink,
				ContentType : $scope.ContentType,
				Content : $scope.Content,
				ExternalLink : $scope.ExternalLink,
				IsBreakLine : $scope.IsBreakLine,
				StartDateTime : $scope.StartDateTime,
				EndDateTime : $scope.EndDateTime,
				IsEnable : $scope.IsEnable,
				IsPublic : $scope.IsPublic,
				Status : $scope.Status,
				Sort : $scope.Sort,
				
				PreStatus : "1",
				TableName : "news_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
			};
			
			$http.post('./api/s31/create', request, csrf_config).then(
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

	
		
		
		
		
		
		

		
		
		
		
}