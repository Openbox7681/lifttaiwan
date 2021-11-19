var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location, Upload) {	
	$("#divQuery").show();
	$("#divEdit").hide();	
	
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "title";
	$scope.sortreverse = true;
	
	$scope.setStatus = function(status) {
		$scope.QueryStatus = status;
		$scope.queryData();
	}
	
	$scope.clearData = function() {
		$scope.QueryStatus = null;             
        $scope.QueryKeyword = null;     
		
	}
	$scope.clearData();
	
	/**查詢-開始**/
	$scope.queryData = function(page) {
		
        $("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}

		if ($scope.QueryKeyword == "")
			$scope.QueryKeyword = null;	
		var qs = null;
		if ($scope.QueryStatus == "")
			qs = null;
		else if ($scope.QueryStatus == "1")
			qs = false;
		else if ($scope.QueryStatus == "2")
			qs = true;
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Status : qs,			
			Keyword : $scope.QueryKeyword,
			
		};
			$http.post('./api/k04/query', request, csrf_config).then(function(response) {	
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
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
	/**查詢-結束**/
	$scope.queryData();
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$('#divQuery').hide();
		$('#divEdit').show();
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$('#divEdit').hide();
		$('#divQuery').show();
		$scope.IsQuestionnaire = false;
	};
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {
		$scope.IsQuestionnaire = true;
		$scope.IsAuditScore = true;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		
		$http.post('./api/k04/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.Id = response.data.Id;
			$scope.Title = response.data.Title;
			$scope.Year = response.data.Year;
			$scope.MaintainInspectId = response.data.MaintainInspectId;
			$scope.OrgId = response.data.OrgId;
			$scope.CommitteeId = response.data.CommitteeId;
			$scope.MaintainPlanMemberId = response.data.MaintainPlanMemberId;
			
			//評分表
			$scope.FileAuditScoreName =  response.data.FileName;
			$scope.fileAuditScore = {name :response.data.FileName};
			$scope.FileAuditScoreId = response.data.FileAuditScoreId;
			//書審意見
			$scope.FileReviewOpinionName =  response.data.FileReviewOpinionName;
			$scope.fileReviewOpinion = {name :response.data.FileReviewOpinionName};
			$scope.FileReviewOpinionId = response.data.FileReviewOpinionId;
			
			$scope.auditScoreList = response.data.AuditScore;
			$scope.Log =  response.data.Log;
			bootbox.hideAll();
		}).catch(function() {
			bootbox.hideAll();
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
	// Switch to Edit(Update) Mode End
	
	/**新增評分表項目-開始**/
	$scope.addAuditScore = function() {		
		$scope.auditScoreList.push({
			AuditScoreName : null,
			AuditScoreScore : null		
			})
	};
	/**新增評分表項目-結束**/
	/**刪除評分表項目-開始**/
	$scope.deleteAuditScore = function(i) {
        $scope.auditScoreList.splice(i, 1);
    }
	/**刪除評分表項目-結束**/
	/**刪除評分表項目-開始**/
	$scope.UpAuditScore = function(i) {
        if (i!=0){
        		var item = $scope.auditScoreList[i]
        		$scope.auditScoreList.splice(i-1, 0, item);
        		$scope.auditScoreList.splice(i+1, 1);
        }
    }
	/**刪除評分表項目-結束**/
	/**上移評分表項目-開始**/
	$scope.DownAuditScore = function(i) {		
        if (i!=$scope.auditScoreList.length-1){
        		var item = $scope.auditScoreList[i+1]
        		$scope.auditScoreList.splice(i, 0, item);
        		$scope.auditScoreList.splice(i+2, 1);
        }
    }
	/**下移評分表項目-開始**/
	/**評分表文件(送出)**/
	$scope.auditScore_submit = function(check) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var data = {																						
				MaintainInspectId : $scope.MaintainInspectId,			
				FileAuditScoreId : $scope.FileAuditScoreId,
				FileReviewOpinionId : $scope.FileReviewOpinionId,
				GroupId : $scope.OrgId,
				auditScoreList : $scope.auditScoreList,
				MaintainPlanMemberId : $scope.MaintainPlanMemberId,
				CommitteeId :$scope.CommitteeId,
				Check : check
				
		};
		Upload.upload({
			url : './api/k04/auditScore_submit',
			data : {
				json : JSON.stringify(data),
				fileAuditScore : $scope.fileAuditScore,
				fileReviewOpinion :$scope.fileReviewOpinion
			},
			headers: header
		})
				.then(				
					function(response) {
								if (response.data.success) {
									bootbox.hideAll();
									bootbox
										.dialog({
											message : "<span class='bigger-110'>" + response.data.msg + "</span>",
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i> 確定",
													"callback" : function() {	
														if ($scope.pageRows == 1
																&& $scope.currentPage > 1) {
															$scope.currentPage = $scope.currentPage - 1;
														}
														$scope.queryData($scope.currentPage);																																																																														
														$scope.closeEdit();
														window.location.reload();									
													}
												}
											}
										});
								} else {
									bootbox.hideAll();
									bootbox.dialog({
										message : "<span class='bigger-110'>" + response.data.msg + "</span>",
										buttons : {
											"success" : {
												"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
												 "className": 'btn-warning',
												"callback" : function() {
												}
											}
										}
									});
								}
							}).catch(function() {
								bootbox.hideAll();
								bootbox.dialog({
									message : "<span class='bigger-110'>資料送出失敗</span>",
									buttons : {
										"success" : {
											"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
											 "className": 'btn-danger',
											"callback" : function() {
											}
										}
									}
								});
							}).finally(function() { 										
							});								
		};
	/**評分表文件(送出)-end**/
	/**狀態計算-開始**/
	$scope.queryButtonCount = function() {
		
		var request = {
		};
		$http.post('./api/k04/query/button/count', request, csrf_config).then(function(response) {
			$scope.Status6Count = response.data.Status6Count;
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
	/**按鈕計算-結束**/
	
	/**頁面數量顯示-開始**/
	$scope.getPageButtonCount = function(){				
		
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		if ($("#subsystem_mtp").length > 0) {
			var subsystem_not_count = 0;
			if ($("#form_maintainPlan").length > 0) {
				$
						.ajax(
								{
									url : "../pub/api/count/mtp",
									method : "POST",
									data : request,
									headers : header,
									datatype : "json",
									async : false
								})
						.done(
								function(response) {
									var count = response.count;
									if (count > 0) {
										$("#form_maintainPlan")
												.html(count);
										subsystem_not_count = subsystem_not_count + count;
									} else {
										$("#form_maintainPlan")
										.html('');
									}
								});
			}
			if (subsystem_not_count > 0) {
				$("#subsystem_mtp > .badge").html(
						subsystem_not_count);
			} else {
				$("#subsystem_mtp > .badge").html(
						'');
			}
		}
	}
	/**頁面數量顯示-結束**/
	 $scope.deletefileAuditScore = function() {
		 $scope.fileAuditScore = null
		 $scope.FileAuditScoreId = null
	 }
	 
	 $scope.changefileAuditScore = function() {		
		 $scope.FileAuditScoreId = null
	 }
	 
	 $scope.deletefileReviewOpinion = function() {
		 $scope.fileReviewOpinion = null
		 $scope.FileReviewOpinionId = null
	 }
	 
	 $scope.changefileReviewOpinion = function() {		
		 $scope.FileReviewOpinionId = null
	 }
	 
	$scope.setSortName = function(sorttype) {
		$scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse
						: false;
		$scope.sorttype = sorttype;
		$scope.currentPage = 1;
		$scope.start = 0;
		$scope.queryData();
	};
}