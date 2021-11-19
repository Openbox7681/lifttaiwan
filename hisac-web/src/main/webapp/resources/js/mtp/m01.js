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
	
	$scope.IsMaintainPlanTab = true;
	
	
	$("#divQuery").show();	
	
		
	$('input[id="EDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="SDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });
		
		
		$("#SDate").on("dp.change", function(e) {	       
			$scope.SDate = $('#SDate').val();
			if ($scope.SDate > $scope.EDate && $scope.EDate != null) {
				$scope.EDate = $scope.SDate;
				$('#EDate').val($scope.EDate)
			}
		});
		$("#EDate").on("dp.change", function(e) {	       
			$scope.EDate = $('#EDate').val();
			if ($scope.EDate < $scope.SDate && $scope.SDate != null) {
				$scope.SDate = $scope.EDate;
				$('#SDate').val($scope.SDate)
			}
		});					
		
		$scope.getMember = function() {
			var data = {};
			$http.post('./api/m01/query/member', data, csrf_config).then(function(response) {
				$scope.members = response.data;
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
			}).finally(function() {});
		};		
		
		$scope.getGroup = function() {
			var data = {};
			$http.post('./api/m01/query/group', data, csrf_config).then(function(response) {
				$scope.groups = response.data;
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
			}).finally(function() {});
		};	
			
		$scope.getMember();
		$scope.getGroup();
	
					
	 
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
        $scope.SDate = null;  
        $scope.EDate = null;
        $scope.QueryKeyword = null;       
        $('#EDate').val("")
		$('#SDate').val("")
		$('#EDate').data("DateTimePicker").clear()
        $('#SDate').data("DateTimePicker").clear()
		
	}
	$scope.clearData();
	
	$scope.queryData = function(page) {
		
        $("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.SDate == "")
			$scope.SDate = null;
		if ($scope.EDate == "")
			$scope.EDate = null;
		if ($scope.QueryKeyword == "")
			$scope.QueryKeyword = null;		
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Status : $scope.QueryStatus,			
			Sdate : $scope.SDate,
			Edate : $scope.EDate,
			Keyword : $scope.QueryKeyword,
			
		};
			$http.post('./api/m01/query', request, csrf_config).then(function(response) {	
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
	$scope.queryData();

	
	$scope.open_member = function(item) {		
		$scope.Id = item.Id;
		$scope.openMembers = item.Members;		
		$scope.title = item.Title;
		$scope.memberReplyNum = 0
		angular.forEach($scope.openMembers, function(item) {
			if (item.ReplyTime != "")
				$scope.memberReplyNum++							
		});	
	}
	
	$scope.open_my_modal_addSubItem = function(n) {
		$scope.openSubItemNumber = n;		
	}
	
	//查看
	$scope.review = function(id, groupId, status) {
		
		$("#tab1").removeClass("ng-scope").addClass("active");
		$("#tab2").removeClass("active").addClass("ng-scope");
		$("#tab3").removeClass("active").addClass("ng-scope");
		$("#tab31").removeClass("active").addClass("ng-scope");
		$("#tab4").removeClass("active").addClass("ng-scope");
		$("#tab5").removeClass("active").addClass("ng-scope");
		$("#tab6").removeClass("active").addClass("ng-scope");
		
		
		$scope.IsReply = false;
		
		if (status == 1 || status == 2 || status == 3 || status ==31 || status==32 || status == 4)
			$scope.IsQuestionnaireTab = false;
		else
			$scope.IsQuestionnaireTab = true;
		
		if (status == 1 || status == 2 || status == 3 || status == 4 ||status == 5 || status == 7 || status == 8 || status == 9)
			$scope.IsMaintainPlanImplementTab = false;
		else
			$scope.IsMaintainPlanImplementTab = true;
		$scope.IsImprovementSuggestTab = true;
		

		
//		if(status ==31 || status==32 ||status == 4 || status == 5 || status == 7 || status == 8 || status == 9)
//			$scope.IsImprovementSuggestTab = false; 
//		else
//			$scope.IsImprovementSuggestTab = true; 
		
		
			
		

		
		
		if (status == 7 || status == 8 || status == 9)
			$scope.IsAuditScoreTab = true;
		else
			$scope.IsAuditScoreTab = false;
		
		if (status == 8 || status == 9)
			$scope.IsAuditResultTab = true;
		else
			$scope.IsAuditResultTab = false;
		
		if (status == 9)
			$scope.IsImprovementTab = true;
		else
			$scope.IsImprovementTab = false;
		
		$scope.IsQuestionnaire = false;	
		$scope.IsMaintainPlanImplement = false;
		$scope.IsAuditScore = false;	
		$scope.IsAuditResult = false;	
		$scope.IsImprovement = false;	
		$scope.IsImprovementSuggest = false;
		
	
		
		
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;		
		
	}	
	
	//上傳稽核前訪談調查文件
	$scope.uploadQuestionnaire= function(id, groupId) {
		$scope.IsReply = false;
		$scope.IsQuestionnaireTab = true;	
		$scope.IsQuestionnaire = true;
		$scope.IsIsAuditScoreTab = false;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = false;	
		$scope.IsAuditResult = false;
		$scope.IsImprovementTab = false;	
		$scope.IsImprovement = false;	
		$scope.IsImprovementSuggestTab=true;
		$scope.IsImprovementSuggest = false;

		$scope.IsMaintainPlanImplementTab = false;
		$scope.IsMaintainPlanImplement = false;
				
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
	}
	
	//上傳共同改善建議事項 109.10.15
	$scope.uploadImprovementSuggest = function(id, groupId){
		
		
		
		
//		$scope.IsMaintainPlanTab = false;
		$scope.IsReplay =false
		$scope.IsQuestionnaireTab = false
		$scope.IsQuestionnaire = false
		$scope.IsIsAuditScoreTab = false;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = false;	
		$scope.IsAuditResult = false;
		$scope.IsImprovementTab = false;	
		$scope.IsImprovement = false;
		$scope.IsImprovementSuggestTab = true;
		$scope.IsImprovementSuggest = true;
		$scope.IsMaintainPlanImplementTab = false;
		$scope.IsMaintainPlanImplement = false;
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
		
		

		
	}
	
	
	//上傳資通安全維護計畫實施情形
	$scope.submitMaintainPlanImplement = function(id, groupId){
		
		
		
		
		
		$scope.IsReply = false;
		$scope.IsQuestionnaireTab = false;	
		$scope.IsQuestionnaire = false;
		$scope.IsAuditScoreTab = false;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = false;	
		$scope.IsAuditResult = false;
		$scope.IsImprovementTab = false;	
		$scope.IsImprovement = false;
		$scope.IsImprovementSuggestTab=true;
		$scope.IsImprovementSuggest=false;
		$scope.IsMaintainPlanImplementTab = true;
		$scope.IsMaintainPlanImplement = true;
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	//上傳稽核評分文件
	$scope.submitAuditScore= function(id, groupId) {		
		$scope.IsReply = false;
		$scope.IsQuestionnaireTab = true;	
		$scope.IsQuestionnaire = false;
		$scope.IsAuditScoreTab = true;	
		$scope.IsAuditScore = true;
		$scope.IsAuditResultTab = false;	
		$scope.IsAuditResult = false;
		$scope.IsImprovementTab = false;	
		$scope.IsImprovement = false;
		$scope.IsImprovementSuggestTab=false;
		$scope.IsImprovementSuggest = false;

		$scope.IsMaintainPlanImplementTab = false;
		$scope.IsMaintainPlanImplement = false;
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
	}
	
	//上傳稽核結果文件
	$scope.submitAuditResult= function(id, groupId) {
		$scope.IsReply = false;
		$scope.IsQuestionnaireTab = true;	
		$scope.IsQuestionnaire = false;
		$scope.IsAuditScoreTab = true;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = true;	
		$scope.IsAuditResult = true;
		$scope.IsImprovementTab = true;	
		$scope.IsImprovement = false;
		$scope.IsImprovementSuggestTab=false;
		$scope.IsImprovementSuggest = false;

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
	}
	
	//上傳改善文件
	$scope.uploadImprovement= function(id, groupId) {
		$scope.IsReply = false;
		$scope.IsQuestionnaireTab = true;	
		$scope.IsQuestionnaire = false;
		$scope.IsAuditScoreTab = true;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = true;	
		$scope.IsAuditResult = false;
		$scope.IsImprovementTab = true;	
		$scope.IsImprovement = true;
		$scope.IsImprovementSuggestTab=true;
		$scope.IsImprovementSuggest = false;

		$scope.IsMaintainPlanImplementTab = false;
		$scope.IsMaintainPlanImplement = false;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;
	}
	
	//回覆
	$scope.reply = function(id, groupId) {
		$scope.IsReply = true;
		$scope.IsQuestionnaireTab = false;	
		$scope.IsQuestionnaire = false;	
		$scope.IsMaintainPlanImplementTab = false;
		$scope.IsMaintainPlanImplement = false;
		
		$scope.IsImprovementSuggestTab=true;
		$scope.IsImprovementSuggest = false;
		
		$scope.IsIsAuditScoreTab = false;	
		$scope.IsAuditScore = false;
		$scope.IsAuditResultTab = false;	
		$scope.IsAuditResult = false;
		$scope.file1 = null;
		$scope.fileCheckList = null;
		$scope.fileQuestionnaire = null;
		$scope.fileImplement = null;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		$scope.btnIns = false;
		$scope.btnUpd = false;		
		$("#divQuery").hide();		
		$scope.createAndupdate_show = false;
		$scope.editData(id, groupId);										
		
		$scope.review_show = true;		
		
	}	
	
	//回覆(送出)
	$scope.reply_submit = function(exit,check) {	
		$("#tab1").removeClass("ng-scope").addClass("active");
		$("#tab2").removeClass("active").addClass("ng-scope");
		$("#tab3").removeClass("active").addClass("ng-scope");
		$("#tab31").removeClass("active").addClass("ng-scope");
		$("#tab4").removeClass("active").addClass("ng-scope");
		$("#tab5").removeClass("active").addClass("ng-scope");
		$("#tab6").removeClass("active").addClass("ng-scope");

		var data = {																	
					Item : $scope.itemList,
					MaintainPlanId : $scope.Id,
					Check : check,
					FileId : $scope.FileId
			};
					
		Upload.upload({
			url : './api/m01/reply',
			data : {
				json : JSON.stringify(data),
				file1 : $scope.file1,				
				FileDesc1 : ''				
			},
			headers: header
		})
				.then(				
					function(response) {
								if (response.data.success) {											 																						
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
														if (exit)
														{													
															$("#divQuery").show();																	
															$scope.review_show = false;
														}
														else
															$scope.FileId = response.data.MaintainPlanId
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
												"callback" : function() {
												}
											}
										}
									});
								}
							}).catch(function() {
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
		
		//資安維護計畫實施情形(送出)
		$scope.implement_submit = function(){
			$("#tab1").removeClass("ng-scope").addClass("active");
			$("#tab2").removeClass("active").addClass("ng-scope");
			$("#tab3").removeClass("active").addClass("ng-scope");
			$("#tab31").removeClass("active").addClass("ng-scope");
			$("#tab4").removeClass("active").addClass("ng-scope");
			$("#tab5").removeClass("active").addClass("ng-scope");
			$("#tab6").removeClass("active").addClass("ng-scope");
			var data = {
					MaintainPlanId : $scope.Id,				
					FileImplementId : $scope.FileImplementId,
			};
			
			Upload.upload({
				url : './api/m01/implement_submit',
				data : {
					json : JSON.stringify(data),
					fileImplement : $scope.fileImplement,		
					FileImplementDesc : '',
				},
				headers: header
			})
					.then(				
						function(response) {
									if (response.data.success) {											 																						
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
															$("#divQuery").show();																	
															$scope.review_show = false;
																														
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
													"callback" : function() {
													}
												}
											}
										});
									}
								}).catch(function() {
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
		
		
		
		//稽核前訪談調查文件(送出)
//		$scope.questionnaire_submit = function() {	
//			$("#tab1").removeClass("ng-scope").addClass("active");
//			$("#tab2").removeClass("active").addClass("ng-scope");
//			$("#tab3").removeClass("active").addClass("ng-scope");
//			$("#tab31").removeClass("active").addClass("ng-scope");
//			$("#tab4").removeClass("active").addClass("ng-scope");
//			$("#tab5").removeClass("active").addClass("ng-scope");
//			$("#tab6").removeClass("active").addClass("ng-scope");
//			var data = {																						
//					MaintainPlanId : $scope.Id,				
//					FileQuestionnaireId : $scope.FileQuestionnaireId,
//					FileCheckListId : $scope.FileCheckListId
//			};
//						
//			Upload.upload({
//				url : './api/m01/questionnaire_submit',
//				data : {
//					json : JSON.stringify(data),
//					fileQuestionnaire : $scope.fileQuestionnaire,		
//					fileCheckList : $scope.fileCheckList,		
//					FileQuestionnaireDesc : '',
//					FileCheckListDesc : '',
//				},
//				headers: header
//			})
//					.then(				
//						function(response) {
//									if (response.data.success) {											 																						
//										bootbox
//											.dialog({
//												message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//												buttons : {
//													"success" : {
//														"label" : "<i class='ace-icon fa fa-check'></i> 確定",
//														"callback" : function() {	
//															if ($scope.pageRows == 1
//																	&& $scope.currentPage > 1) {
//																$scope.currentPage = $scope.currentPage - 1;
//															}	
//															$scope.queryData($scope.currentPage);																																																																														
//															$("#divQuery").show();																	
//															$scope.review_show = false;
//																														
//														}
//													}
//												}
//											});
//									} else {
//										bootbox.dialog({
//											message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//											buttons : {
//												"success" : {
//													"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//													 "className": 'btn-warning',
//													"callback" : function() {
//													}
//												}
//											}
//										});
//									}
//								}).catch(function() {
//									bootbox.dialog({
//										message : "<span class='bigger-110'>資料送出失敗</span>",
//										buttons : {
//											"success" : {
//												"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//												 "className": 'btn-danger',
//												"callback" : function() {
//												}
//											}
//										}
//									});
//								}).finally(function() { 										
//								});								
//			};		
//			
//			//評分表文件(送出)
//			$scope.auditScore_submit = function() {		
//				$("#tab1").removeClass("ng-scope").addClass("active");
//				$("#tab2").removeClass("active").addClass("ng-scope");
//				$("#tab3").removeClass("active").addClass("ng-scope");
//				$("#tab31").removeClass("active").addClass("ng-scope");
//				$("#tab4").removeClass("active").addClass("ng-scope");
//				$("#tab5").removeClass("active").addClass("ng-scope");
//				$("#tab6").removeClass("active").addClass("ng-scope");
//				var data = {																						
//						MaintainPlanId : $scope.Id,				
//						FileAuditScoreId : $scope.FileAuditScoreId,
//						GroupId : $scope.OrgId,
//						auditScoreList : $scope.auditScoreList
//						
//				};
//							
//				Upload.upload({
//					url : './api/m01/auditScore_submit',
//					data : {
//						json : JSON.stringify(data),
//						fileAuditScore : $scope.fileAuditScore,								
//						FileAuditScoreDesc : ''					
//					},
//					headers: header
//				})
//						.then(				
//							function(response) {
//										if (response.data.success) {											 																						
//											bootbox
//												.dialog({
//													message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//													buttons : {
//														"success" : {
//															"label" : "<i class='ace-icon fa fa-check'></i> 確定",
//															"callback" : function() {	
//																if ($scope.pageRows == 1
//																		&& $scope.currentPage > 1) {
//																	$scope.currentPage = $scope.currentPage - 1;
//																}	
//																$scope.queryData($scope.currentPage);																																																																														
//																$("#divQuery").show();																	
//																$scope.review_show = false;
//																															
//															}
//														}
//													}
//												});
//										} else {
//											bootbox.dialog({
//												message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//												buttons : {
//													"success" : {
//														"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//														 "className": 'btn-warning',
//														"callback" : function() {
//														}
//													}
//												}
//											});
//										}
//									}).catch(function() {
//										bootbox.dialog({
//											message : "<span class='bigger-110'>資料送出失敗</span>",
//											buttons : {
//												"success" : {
//													"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//													 "className": 'btn-danger',
//													"callback" : function() {
//													}
//												}
//											}
//										});
//									}).finally(function() { 										
//									});								
//				};
//				
//	
//				//稽核結果文件(送出)
//				$scope.auditResult_submit = function() {	
//					$("#tab1").removeClass("ng-scope").addClass("active");
//					$("#tab2").removeClass("active").addClass("ng-scope");
//					$("#tab3").removeClass("active").addClass("ng-scope");
//					$("#tab31").removeClass("active").addClass("ng-scope");
//					$("#tab4").removeClass("active").addClass("ng-scope");
//					$("#tab5").removeClass("active").addClass("ng-scope");
//					$("#tab6").removeClass("active").addClass("ng-scope");
//
//					var data = {																						
//							MaintainPlanId : $scope.Id,				
//							FileAuditResultId : $scope.FileAuditResultId,
//							GroupId : $scope.OrgId
//					};
//								
//					Upload.upload({
//						url : './api/m01/auditResult_submit',
//						data : {
//							json : JSON.stringify(data),
//							fileAuditResult : $scope.fileAuditResult,								
//							FileAuditResultDesc : $scope.auditResultAbstract				
//						},
//						headers: header
//					})
//							.then(				
//								function(response) {
//											if (response.data.success) {											 																						
//												bootbox
//													.dialog({
//														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//														buttons : {
//															"success" : {
//																"label" : "<i class='ace-icon fa fa-check'></i> 確定",
//																"callback" : function() {	
//																	if ($scope.pageRows == 1
//																			&& $scope.currentPage > 1) {
//																		$scope.currentPage = $scope.currentPage - 1;
//																	}	
//																	$scope.queryData($scope.currentPage);																																																																														
//																	$("#divQuery").show();																	
//																	$scope.review_show = false;
//																																
//																}
//															}
//														}
//													});
//											} else {
//												bootbox.dialog({
//													message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//													buttons : {
//														"success" : {
//															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//															 "className": 'btn-warning',
//															"callback" : function() {
//															}
//														}
//													}
//												});
//											}
//										}).catch(function() {
//											bootbox.dialog({
//												message : "<span class='bigger-110'>資料送出失敗</span>",
//												buttons : {
//													"success" : {
//														"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//														 "className": 'btn-danger',
//														"callback" : function() {
//														}
//													}
//												}
//											});
//										}).finally(function() { 										
//										});								
//					};
					
					//修改共同改善建議事項狀態為已讀
					
					$scope.setImprovementSuggestRead = function(){
						var data = {
								MaintainPlanId : $scope.Id,				
								GroupId : $scope.OrgId	
						};
						
						
						$http.post('./api/m01/updateMaintainPlanSuggest', data, csrf_config).then(					
								function(response) {
									if (response.data.success) {								
											bootbox
													.dialog({
														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-check'></i> 確定",
																"callback" : function() {
															
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
															"callback" : function() {
															}
														}
													}
												});
											}
										}).catch(function() {
											bootbox.dialog({
												message : "<span class='bigger-110'>資料更新失敗</span>",
												buttons : {
													"success" : {
														"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
														 "className": 'btn-danger',
														"callback" : function() {
														}
													}
												}
											});
										}).finally(function() { });
						
					}
					
					//共同改善建議事項(送出)
					
					$scope.improvementSuggest_submit = function() {
						$("#tab1").removeClass("ng-scope").addClass("active");
						$("#tab2").removeClass("active").addClass("ng-scope");
						$("#tab3").removeClass("active").addClass("ng-scope");
						$("#tab31").removeClass("active").addClass("ng-scope");
						$("#tab4").removeClass("active").addClass("ng-scope");
						$("#tab5").removeClass("active").addClass("ng-scope");
						$("#tab6").removeClass("active").addClass("ng-scope");
						
						var data = {
								MaintainPlanId : $scope.Id,				
								FileImprovementSuggestId : $scope.FileImprovementSuggestId,
								GroupId : $scope.OrgId	
								
						};
						Upload.upload({
							url : './api/m01/improvementSuggest_submit',
							data : {
								json : JSON.stringify(data),
								fileImprovementSuggest : $scope.fileImprovementSuggest,								
								FileImprovementSuggestDesc : $scope.improvementSuggestAbstract				
							},
							headers: header
						})
						.then(				
								function(response) {
											if (response.data.success) {											 																						
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
																	$("#divQuery").show();																	
																	$scope.review_show = false;
																																
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
															"callback" : function() {
															}
														}
													}
												});
											}
										}).catch(function() {
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
					
					
					
					//改善報告(送出)
					$scope.improvement_submit = function() {		
						$("#tab1").removeClass("ng-scope").addClass("active");
						$("#tab2").removeClass("active").addClass("ng-scope");
						$("#tab3").removeClass("active").addClass("ng-scope");
						$("#tab31").removeClass("active").addClass("ng-scope");
						$("#tab4").removeClass("active").addClass("ng-scope");
						$("#tab5").removeClass("active").addClass("ng-scope");
						$("#tab6").removeClass("active").addClass("ng-scope");
						var data = {																						
								MaintainPlanId : $scope.Id,				
								FileImprovementId : $scope.FileImprovementId,
								GroupId : $scope.OrgId
						};
									
						Upload.upload({
							url : './api/m01/improvement_submit',
							data : {
								json : JSON.stringify(data),
								fileImprovement : $scope.fileImprovement,								
								FileImprovementDesc : $scope.improvementAbstract				
							},
							headers: header
						})
								.then(				
									function(response) {
												if (response.data.success) {											 																						
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
																		$("#divQuery").show();																	
																		$scope.review_show = false;
																																	
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
																"callback" : function() {
																}
															}
														}
													});
												}
											}).catch(function() {
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
		
	
	//編輯
	$scope.edit = function(id) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.Title = null;
		$scope.Year = null;		
		$scope.SPlanDate = null
		$scope.EPlanDate = null
		
		$('input[id="SPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    }); 
		$('input[id="EPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    });				
		$("#SPlanDate").on("dp.change", function(e) {	       
			$scope.SPlanDate = $('#SPlanDate').val();
			if ($scope.SPlanDate > $scope.EPlanDate && $scope.EPlanDate != null) {
				$scope.EPlanDate = $scope.SPlanDate;
				$('#EPlanDate').val($scope.EPlanDate)
			}
		});
		$("#EPlanDate").on("dp.change", function(e) {	       
			$scope.EPlanDate = $('#EPlanDate').val();
			if ($scope.EPlanDate < $scope.SPlanDate && $scope.SPlanDate != null) {
				$scope.SPlanDate = $scope.EPlanDate;
				$('#SPlanDate').val($scope.SPlanDate)
			}
		});		
		$scope.isChooseAllOrg = false;
		$scope.isChooseAllLevel = false;
		$scope.isChooseAllCity = false;
		$scope.isChooseAllIsOrgPub = false;
		
		$("#divQuery").hide();		

		$scope.createAndupdate_show = true;
		$scope.btnIns = false;
		$scope.btnUpd = true;
		
		$scope.editData(id, 0);		
	}
	
	$scope.editData = function(id, groupId) {		
		var request = {Id:id, GroupId:groupId};		
		$http.post('./api/m01/query/id', request, csrf_config).then(function(response) {				
			$scope.Id = id;
			$scope.Title = response.data.Title;
			$scope.Year = response.data.Year;														
			$scope.SPlanDate = response.data.Sdate;
			$scope.EPlanDate = response.data.Edate;
			$scope.itemList = response.data.Items;
			$scope.orgs = response.data.Orgs;			
			$scope.levels = response.data.Levels;
			$scope.citys = response.data.Citys;
			$scope.isOrgPubs = response.data.IsOrgPubs;
			$scope.memberList = response.data.MemberList;
			$scope.groupList = response.data.GroupList;
			//維護計畫
			$scope.FileName =  response.data.FileName;
			$scope.file1 = {name : response.data.FileName};
			$scope.FileId = response.data.FileId;
			//維護計畫實施情形
			$scope.FileImplementName = response.data.FileImplementName;
			$scope.fileImplement =  {name : response.data.FileImplementName};
			$scope.FileImplementId = response.data.FileImplementId;
			//調查表
			$scope.FileQuestionnaireName =  response.data.FileQuestionnaireName;
			$scope.fileQuestionnaire = {name : response.data.FileQuestionnaireName};
			$scope.FileQuestionnaireId = response.data.FileQuestionnaireId;
			//檢核表
			$scope.FileCheckListName =  response.data.FileCheckListName;
			$scope.fileCheckList = {name : response.data.FileCheckListName};
			$scope.FileCheckListId = response.data.FileCheckListId;
			
			//評分表
			$scope.FileAuditScoreName =  response.data.FileAuditScoreName;
			$scope.fileAuditScore = {name : response.data.FileAuditScoreName};
			$scope.FileAuditScoreId = response.data.FileAuditScoreId;
			if (null == response.data.auditScoreList)
				$scope.auditScoreList = []
			else
				$scope.auditScoreList = response.data.auditScoreList;
			//稽核結果
			$scope.FileAuditResultName =  response.data.FileAuditResultName;
			$scope.fileAuditResult = {name : response.data.FileAuditResultName};
			$scope.FileAuditResultId = response.data.FileAuditResultId;
			$scope.auditResultAbstract = response.data.AuditResultAbstract
			//改善報告
			$scope.FileImprovementName =  response.data.FileImprovementName;
			$scope.fileImprovement = {name : response.data.FileImprovementName};
			$scope.FileImprovementId = response.data.FileImprovementId;
			$scope.improvementAbstract = response.data.ImprovementAbstract
			//改善建議事項
			$scope.FileImprovementSuggestName = response.data.FileImprovementSuggestName;
			$scope.fileImprovementSuggest =  {name : response.data.FileImprovementSuggestName};
			$scope.FileImprovementSuggestId = response.data.FileImprovementSuggestId;
			$scope.improvementSuggestAbstract = response.data.ImprovementSuggestAbstract;
			$scope.IsRead = response.data.ImprovementSuggestIsRead;
			
			$scope.Log =  response.data.Log;
			$scope.OrgId =  groupId;
			
			//org
			$scope.Org = "";
			var orgNum = 1;		
			angular.forEach($scope.orgs, function(org) {									
				if (org.Action){
					$scope.Org = $scope.Org + orgNum + "." + org.Name + " "
					orgNum++;
				}
			});
			//Level
			$scope.Level = "";
			var levelNum = 1;
			angular.forEach($scope.levels, function(level) {									
				if (level.Action){
					$scope.Level = $scope.Level + levelNum + "." + level.Name + " "
					levelNum++;
				}
			});
			//City
			$scope.City = "";
			var cityNum = 1;
			angular.forEach($scope.citys, function(city) {									
				if (city.Action){
					$scope.City = $scope.City + cityNum + "." + city.Name + " "
					cityNum++;
				}
			});
			//isOrgPubs
			$scope.IsOrgPub = "";
			var isOrgPubNum = 1;
			angular.forEach($scope.isOrgPubs, function(isOrgPub) {									
				if (isOrgPub.Action){
					$scope.IsOrgPub = $scope.IsOrgPub + isOrgPubNum + "." + isOrgPub.Name + " "
					isOrgPubNum++;
				}
			});
			//memberList
			$scope.Member = "";
			var memberNum = 1;			
			angular.forEach($scope.memberList, function(member) {					
				angular.forEach(member.Members, function(memberId){					
					if (member.MemberId == memberId.Id){						
						$scope.Member = $scope.Member + memberNum + "." + memberId.Name + " "						
						memberNum++;						
					}				
				});
			});
			//groupList
			$scope.Group = "";
			var groupNum = 1;
			angular.forEach($scope.groupList, function(group) {									
				angular.forEach(group.Groups, function(groupId){
					if (group.GroupId == groupId.Id){
						$scope.Group = $scope.Group + groupNum + "." + groupId.Name + " "
						groupNum++;					
					}				
				});
			});
			
			bootbox.hideAll();
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
			bootbox.hideAll()
			});
	};
	//共同維護計畫設計為已讀(醫院方使用)
	$scope.updateMaintainPlanSuggest = function (orgId){
		var request = {	
				GroupId : orgId,
				MaintainPlanId : $scope.Id,
		};
		
		$http.post('./api/m01/updateMaintainPlanSuggest', request, csrf_config).then(					
				function(response) {
					if (response.data.success) {								
							bootbox
									.dialog({
										message : "<span class='bigger-110'>" + response.data.msg + "</span>",
										buttons : {
											"success" : {
												"label" : "<i class='ace-icon fa fa-check'></i> 確定",
												"callback" : function() {
													$scope.IsRead = true
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
											"callback" : function() {
											}
										}
									}
								});
							}
						}).catch(function() {
							bootbox.dialog({
								message : "<span class='bigger-110'>資料更新失敗</span>",
								buttons : {
									"success" : {
										"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
										 "className": 'btn-danger',
										"callback" : function() {
										}
									}
								}
							});
						}).finally(function() { });
		
		
		
		
		
	};
	
	//建立(修改)
	$scope.updateData = function(exit,check) {	
		if (check){
			$scope.Status = "2";
			$scope.IsWriteProcessLog = true	
		}
		else{
			$scope.Status = "1";				
			$scope.IsWriteProcessLog = false
		}									
		var request = {	
					Id : $scope.Id,
					Title : $scope.Title,
					Year : $scope.Year,														
					Sdate : $scope.SPlanDate,
					Edate : $scope.EPlanDate,				
					Member : {
						"orgs":$scope.orgs, 
						"levels":$scope.levels,
						"citys":$scope.citys,
						"isOrgPubs":$scope.isOrgPubs,
						"memberList":$scope.memberList,
						"groupList":$scope.groupList},					
					Item : $scope.itemList,							
					Status : $scope.Status,
					PreStatus : "1",
					TableName : "maintainPlan",							
					IsWriteProcessLog : $scope.IsWriteProcessLog
		   	
			};
			
		$http.post('./api/m01/update', request, csrf_config).then(					
					function(response) {
						if (response.data.success) {								
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
														if (exit)
															{
														$scope.queryData($scope.currentPage);														
														$("#divQuery").show();														
														$scope.createAndupdate_show = false;
															}
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
												"callback" : function() {
												}
											}
										}
									});
								}
							}).catch(function() {
								bootbox.dialog({
									message : "<span class='bigger-110'>資料更新失敗</span>",
									buttons : {
										"success" : {
											"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
											 "className": 'btn-danger',
											"callback" : function() {
											}
										}
									}
								});
							}).finally(function() { });
	}
	//建立結束(修改)


    
	$scope.deleteData = function(id) {
		bootbox
				.confirm(
						"確定要刪除此資安維護計畫嗎？",
						function(result) {
							if (result) {
								var request = {Id:id};
								$http
										.post('./api/m01/delete', request, csrf_config)
										.then(
												function(response) {
													if (response.data.success) {
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
																				$scope
																						.queryData($scope.currentPage);
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
																	"callback" : function() {
																	}
																}
															}
														});
													}
												}).catch(function() {
													bootbox.dialog({
														message : "<span class='bigger-110'>資料刪除失敗</span>",
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
																 "className": 'btn-danger',
																"callback" : function() {
																}
															}
														}
													});
												}).finally(function() { });
							}
						});
	}
	

	//建立
	$scope.create = function(){
		$scope.Title = null;
		$scope.Year = null;		
		$scope.SPlanDate = null
		$scope.EPlanDate = null
		
		$('input[id="SPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    }); 
		$('input[id="EPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    });				
		$("#SPlanDate").on("dp.change", function(e) {	       
			$scope.SPlanDate = $('#SPlanDate').val();
			if ($scope.SPlanDate > $scope.EPlanDate && $scope.EPlanDate != null) {
				$scope.EPlanDate = $scope.SPlanDate;
				$('#EPlanDate').val($scope.EPlanDate)
			}
		});
		$("#EPlanDate").on("dp.change", function(e) {	       
			$scope.EPlanDate = $('#EPlanDate').val();
			if ($scope.EPlanDate < $scope.SPlanDate && $scope.SPlanDate != null) {
				$scope.SPlanDate = $scope.EPlanDate;
				$('#SPlanDate').val($scope.SPlanDate)
			}
		});		
		$scope.isChooseAllOrg = false;
		$scope.isChooseAllLevel = false;
		$scope.isChooseAllCity = false;
		$scope.isChooseAllIsOrgPub = false;
		$scope.orgs = [{'Name':'醫學中心'}, {'Name':'準醫學中心'}, {'Name':'區域醫院'}, {'Name':'地區醫學'}, {'Name':'非醫院'}]
		$scope.levels = [
			{Action: false, Value: "A", Name: "A"},
			{Action: false, Value: "B", Name: "B"},
			{Action: false, Value: "C", Name: "C"},
			{Action: false, Value: "D", Name: "D"},
			{Action: false, Value: "E", Name: "E"}
			]
		$scope.citys = [
			{Action: false, Value: "基隆市", Name: "基隆市"},
			{Action: false, Value: "台北市", Name: "台北市"},
			{Action: false, Value: "新北市", Name: "新北市"},
			{Action: false, Value: "桃園縣", Name: "桃園縣"},
			{Action: false, Value: "新竹市", Name: "新竹市"},
			{Action: false, Value: "新竹縣", Name: "新竹縣"},
			{Action: false, Value: "苗栗縣", Name: "苗栗縣"},
			{Action: false, Value: "台中市", Name: "台中市"},
			{Action: false, Value: "彰化縣", Name: "彰化縣"},
			{Action: false, Value: "南投縣", Name: "南投縣"},
			{Action: false, Value: "雲林縣", Name: "雲林縣"},
			{Action: false, Value: "嘉義市", Name: "嘉義市"},
			{Action: false, Value: "嘉義縣", Name: "嘉義縣"},
			{Action: false, Value: "台南市", Name: "台南市"},
			{Action: false, Value: "高雄市", Name: "高雄市"},
			{Action: false, Value: "屏東縣", Name: "屏東縣"},
			{Action: false, Value: "台東縣", Name: "台東縣"},
			{Action: false, Value: "花蓮縣", Name: "花蓮縣"},
			{Action: false, Value: "宜蘭縣", Name: "宜蘭縣"},
			{Action: false, Value: "澎湖縣", Name: "澎湖縣"},
			{Action: false, Value: "金門縣", Name: "金門縣"},
			{Action: false, Value: "連江縣", Name: "連江縣"}
			]
		$scope.isOrgPubs = [				
			{Action: false, Value: true, Name: "是"},
			{Action: false, Value: false, Name: "否"}
			]
		$scope.SPlanDate = moment().format("YYYY-MM-DD");	
		$scope.EPlanDate = moment().format("YYYY-MM-DD");				
		$scope.itemList = [];
		$scope.memberList = [];
		$scope.groupList = [];		
		
		$("#divQuery").hide();		

		$scope.createAndupdate_show = true;
		$scope.btnIns = true;
		$scope.btnUpd = false;
		//建立結束
														
	}
	
	
	//建立
	$scope.addItem = function() {
		$scope.itemList.push({
			ItemName : null,
			ItemDesc : null,
			ItemType : "title",
			ItemSubItem : []
			})
	};
	
	$scope.addSubItem = function(n) {		
		$scope.itemList[n].ItemSubItem.push({
			ItemName : null,
			ItemDesc : null,
			ItemType : "title"
 		})
	};	
	
	$scope.addMember = function() {
		$scope.memberList.push({
			Members : $scope.members,
			MemberId : null
		})
	};
	
	$scope.addGroup = function() {
		$scope.groupList.push({
			Groups : $scope.groups,
			GroupId : null
		})
	};
	
	$scope.addAuditScore = function() {		
		$scope.auditScoreList.push({
			AuditScoreName : null,
			AuditScoreScore : null		
			})
	};
	
	$scope.deleteItem = function(i) {
        $scope.itemList.splice(i, 1);
    }
	
	$scope.deleteSubItem = function(i) {
        $scope.itemList[$scope.openSubItemNumber].ItemSubItem.splice(i, 1);
    }
	
	$scope.deleteMember = function(i) {
        $scope.memberList.splice(i, 1);
    }
	
	$scope.deleteGroup = function(i) {
        $scope.groupList.splice(i, 1);
    }
	
	$scope.deleteAuditScore = function(i) {
        $scope.auditScoreList.splice(i, 1);
    }
	
	$scope.UpItem = function(i) {
        if (i!=0){
        		var item = $scope.itemList[i]
        		$scope.itemList.splice(i-1, 0, item);
        		$scope.itemList.splice(i+1, 1);
        }
    }
	
	$scope.DownItem = function(i) {		
        if (i!=$scope.itemList.length-1){
        		var item = $scope.itemList[i+1]
        		$scope.itemList.splice(i, 0, item);
        		$scope.itemList.splice(i+2, 1);
        }
    }
	
	$scope.UpSubItem = function(i) {
        if (i!=0){
        		var item = $scope.itemList[$scope.openSubItemNumber].ItemSubItem[i]
        		$scope.itemList[$scope.openSubItemNumber].ItemSubItem.splice(i-1, 0, item);
        		$scope.itemList[$scope.openSubItemNumber].ItemSubItem.splice(i+1, 1);
        }
    }
	
	$scope.DownSubItem = function(i) {		
        if (i!=$scope.itemList[$scope.openSubItemNumber].ItemSubItem.length-1){
        		var item = $scope.itemList[$scope.openSubItemNumber].ItemSubItem[i+1]
        		$scope.itemList[$scope.openSubItemNumber].ItemSubItem.splice(i, 0, item);
        		$scope.itemList[$scope.openSubItemNumber].ItemSubItem.splice(i+2, 1);
        }
    }
	
	$scope.UpAuditScore = function(i) {
        if (i!=0){
        		var item = $scope.auditScoreList[i]
        		$scope.auditScoreList.splice(i-1, 0, item);
        		$scope.auditScoreList.splice(i+1, 1);
        }
    }
	
	$scope.DownAuditScore = function(i) {		
        if (i!=$scope.auditScoreList.length-1){
        		var item = $scope.auditScoreList[i+1]
        		$scope.auditScoreList.splice(i, 0, item);
        		$scope.auditScoreList.splice(i+2, 1);
        }
    }
	
	//Org 全選 功能
	$scope.changeChooseAllOrg = function(isChooseAllOrg) {
		angular.forEach($scope.orgs, function(orgItem) {									
				orgItem.Action = isChooseAllOrg;							
		});
	}
	
	//Level 全選 功能
	$scope.changeChooseAllLevel = function(isChooseAllLevel) {
		angular.forEach($scope.levels, function(level) {									
			level.Action = isChooseAllLevel;							
		});
	}
	
	//City 全選 功能
	$scope.changeChooseAllCity = function(isChooseAllCity) {
		angular.forEach($scope.citys, function(city) {									
			city.Action = isChooseAllCity;							
		});
	}
	
	//IsOrgPub 全選 功能
	$scope.changeChooseAllIsOrgPub = function(isChooseAllIsOrgPub) {
		angular.forEach($scope.isOrgPubs, function(isOrgPub) {									
			isOrgPub.Action = isChooseAllIsOrgPub;							
		});
	}
	//結束建立
	
	//建立(新增)
			$scope.createData = function(exit,check) {
				
				if (check){
					$scope.Status = "2";
					$scope.IsWriteProcessLog = true	
				}
				else{
					$scope.Status = "1";				
					$scope.IsWriteProcessLog = false
				}									
				var request = {							
							Title : $scope.Title,
							Year : $scope.Year,														
							Sdate : $scope.SPlanDate,
							Edate : $scope.EPlanDate,				
							Member : {
								"orgs":$scope.orgs, 
								"levels":$scope.levels,
								"citys":$scope.citys,
								"isOrgPubs":$scope.isOrgPubs,
								"memberList":$scope.memberList,
								"groupList":$scope.groupList},					
							Item : $scope.itemList,							
							Status : $scope.Status,
							PreStatus : "1",
							TableName : "maintainPlan",							
							IsWriteProcessLog : $scope.IsWriteProcessLog
				   	
					};
					
				$http.post('./api/m01/create', request, csrf_config).then(					
							function(response) {
										if (response.data.success) {											 																						
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
																if (exit)
																	{																																						
																	$("#divQuery").show();																	
																	$scope.createAndupdate_show = false;
																	}
																else
																	{														
																	$scope.btnIns = false;
																	$scope.btnUpd = true;
																	$scope.PreStatus = "1"
																	$scope.Id = response.data.Id
																	}
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
														"callback" : function() {
														}
													}
												}
											});
										}
									}).catch(function() {
										bootbox.dialog({
											message : "<span class='bigger-110'>資料新增失敗</span>",
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
	
	$scope.back = function() {		
		$("#tab1").removeClass("ng-scope").addClass("active");
		$("#tab2").removeClass("active").addClass("ng-scope");
		$("#tab3").removeClass("active").addClass("ng-scope");
		$("#tab31").removeClass("active").addClass("ng-scope");
		$("#tab4").removeClass("active").addClass("ng-scope");
		$("#tab5").removeClass("active").addClass("ng-scope");
		$("#tab6").removeClass("active").addClass("ng-scope");
		$("#divQuery").show();		
		$scope.createAndupdate_show = false;
		$scope.review_show = false;
	};	
	
	
	$scope.queryButtonCount = function() {
		
		var request = {
		};
		$http.post('./api/m01/query/button/count', request, csrf_config).then(function(response) {
			$scope.Status2Count = response.data.Status2Count;
			$scope.Status31Count = response.data.Status31Count;

			$scope.Status5Count = response.data.Status5Count;
			$scope.Status8Count = response.data.Status8Count;
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
	
	$scope.getCount = function(Status){
        var total = 0;
	    angular.forEach($scope.button_count_allitems, function(value, key) {
	    	if(value.Status==Status ) {
   	        	total=value.Count;
   	        }
	    });
    	return total;
     }
	
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
	
	
	 $scope.exportData = function(title) {
		 
			// alert("excel");
			 
		        var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
		            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		        });
		        saveAs(blob, "("+ title +")maintainPlan_"+(new Date()).toLocaleString(undefined, {
			   	    day:'numeric',
			   	    month: 'numeric',
			   	    year: 'numeric',
			   	    hour: '2-digit',
			   	    minute: '2-digit',
			   	    second: '2-digit',
			   	    hour12: false 
			   	})+".xls");
		    }
	 
	 $scope.deletefile = function() {
		 $scope.file1 = null
		 $scope.FileId = null
	 }
	 
	 $scope.changefile = function() {		
		 $scope.FileId = null
	 }
	 
	 $scope.deletefileQuestionnaire = function() {
		 $scope.fileQuestionnaire = null
		 $scope.FileQuestionnaireId = null
	 }
	 
	 $scope.changefileQuestionnaire = function() {		
		 $scope.FileQuestionnaireId = null
	 }
	 
	 $scope.deletefileCheckList = function() {
		 $scope.fileCheckList = null
		 $scope.FileCheckListId = null
	 }
	 
	 $scope.changefileCheckList = function() {		
		 $scope.FileCheckListId = null
	 }
	 
	 //資通安全維護計畫實施情形檔案修改start
	 
	 $scope.deletefileImplement = function() {
		 $scope.fileImplement = null
		 $scope.FileImplementId = null
	 }
	 
	 $scope.changefileImplement = function() {		
		 $scope.FileImplementId = null
	 }
	 
	 
	 //資通安全維護計畫實施情形檔案修改end

	 
	 
	 $scope.deletefileAuditScore = function() {
		 $scope.fileAuditScore = null
		 $scope.FileAuditScoreId = null
	 }
	 
	 $scope.changefileAuditScore = function() {		
		 $scope.FileAuditScoreId = null
	 }
	 
	 $scope.deletefileAuditResult = function() {
		 $scope.fileAuditResult = null
		 $scope.FileAuditResultId = null
	 }
	 
	 $scope.changefileAuditResult = function() {		
		 $scope.FileAuditResultId = null
	 }
	 
	 $scope.deletefileImprovement = function() {
		 $scope.fileImprovement = null
		 $scope.FileImprovementId = null
	 }
	 
	 $scope.changefileImprovement = function() {		
		 $scope.FileImprovementId = null
	 }
	 
	 $scope.deletefileImprovementSuggest = function(){
		 $scope.fileImprovementSuggest = null
		 $scope.FileImprovementSuggestId = null
	 }
	 
	 $scope.changefileImprovementSuggest = function(){
		 $scope.FileImprovementSuggestId = null
	 } 
	 
	 
	 $scope.clearMember = function(id, groupId) {	
		 bootbox
			.confirm(
					"確定要清空此填寫者嗎？",
					function(result) {
						if (result) {
							var request = {Id:id, GroupId: groupId};
							$http
									.post('./api/m01/clearMember', request, csrf_config)
									.then(
											function(response) {
												if (response.data.success) {
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
																			$scope
																					.queryData($scope.currentPage);
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
																"callback" : function() {
																}
															}
														}
													});
												}
											}).catch(function() {
												bootbox.dialog({
													message : "<span class='bigger-110'>資料更新失敗</span>",
													buttons : {
														"success" : {
															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
															 "className": 'btn-danger',
															"callback" : function() {
															}
														}
													}
												});
											}).finally(function() { });
						}
					});
	 }
	 
	 
	 $scope.returnMember = function(id, groupId, status) {	
		 var url =''
		 var confirmWord = ''	
		 if (status == 3){
			 url = './api/m01/returnMember'
			 confirmWord = "確定要退回此資安維護計畫嗎？"
		 }else if(status == 32){
			 url = './api/m01/returnImplement'
				 confirmWord = "確定要退回此資安維護計畫實施情形嗎？"
			 
		 }
		 else if (status==6){
			 url = './api/m01/returnAudit'
		     confirmWord = "確定要退回此稽核前訪談調查文件嗎？"
		 }
		 else if (status==9){
			 url = './api/m01/returnImprovement'
		     confirmWord = "確定要退回此改善報告嗎？"
		 }
			 
		 bootbox
			.confirm(
					confirmWord,
					function(result) {
						if (result) {							
							var request = {Id:id, GroupId: groupId};
							$http
									.post(url, request, csrf_config)
									.then(
											function(response) {
												if (response.data.success) {
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
																			$scope
																					.queryData($scope.currentPage);
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
																"callback" : function() {
																}
															}
														}
													});
												}
											}).catch(function() {
												bootbox.dialog({
													message : "<span class='bigger-110'>資料更新失敗</span>",
													buttons : {
														"success" : {
															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
															 "className": 'btn-danger',
															"callback" : function() {
															}
														}
													}
												});
											}).finally(function() { });
						}
					});
	 }
	 
	 

	 $scope.startImplementFromAudit = function(id, groupId) {	
		 bootbox
			.confirm(
					"確定要進行資安維護計畫實施情形繳交嗎？",
					function(result) {
						if (result) {
							var request = {Id:id, GroupId: groupId};
							$http
									.post('./api/m01/startImplementFromAudit', request, csrf_config)
									.then(
											function(response) {
												if (response.data.success) {
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
																			$scope
																					.queryData($scope.currentPage);
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
																"callback" : function() {
																}
															}
														}
													});
												}
											}).catch(function() {
												bootbox.dialog({
													message : "<span class='bigger-110'>資料更新失敗</span>",
													buttons : {
														"success" : {
															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
															 "className": 'btn-danger',
															"callback" : function() {
															}
														}
													}
												});
											}).finally(function() { });
						}
					});
	 }
	 
	 
	 $scope.startImplement = function(id, groupId) {	
		 bootbox
			.confirm(
					"確定要進行資安維護計畫實施情形繳交嗎？",
					function(result) {
						if (result) {
							var request = {Id:id, GroupId: groupId};
							$http
									.post('./api/m01/startImplement', request, csrf_config)
									.then(
											function(response) {
												if (response.data.success) {
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
																			$scope
																					.queryData($scope.currentPage);
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
																"callback" : function() {
																}
															}
														}
													});
												}
											}).catch(function() {
												bootbox.dialog({
													message : "<span class='bigger-110'>資料更新失敗</span>",
													buttons : {
														"success" : {
															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
															 "className": 'btn-danger',
															"callback" : function() {
															}
														}
													}
												});
											}).finally(function() { });
						}
					});
	 }
	 
	 
//	 $scope.startAudit = function(id, groupId) {	
//		 bootbox
//			.confirm(
//					"確定要進行稽核前訪談調查嗎？",
//					function(result) {
//						if (result) {
//							var request = {Id:id, GroupId: groupId};
//							$http
//									.post('./api/m01/startAudit', request, csrf_config)
//									.then(
//											function(response) {
//												if (response.data.success) {
//												bootbox
//														.dialog({
//															message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//															buttons : {
//																"success" : {
//																	"label" : "<i class='ace-icon fa fa-check'></i> 確定",
//																	"callback" : function() {
//																			if ($scope.pageRows == 1
//																					&& $scope.currentPage > 1) {
//																				$scope.currentPage = $scope.currentPage - 1;
//																			}
//																			$scope
//																					.queryData($scope.currentPage);
//																	}
//																}
//															}
//														});
//												} else {
//													bootbox.dialog({
//														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
//														buttons : {
//															"success" : {
//																"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//																 "className": 'btn-warning',
//																"callback" : function() {
//																}
//															}
//														}
//													});
//												}
//											}).catch(function() {
//												bootbox.dialog({
//													message : "<span class='bigger-110'>資料更新失敗</span>",
//													buttons : {
//														"success" : {
//															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
//															 "className": 'btn-danger',
//															"callback" : function() {
//															}
//														}
//													}
//												});
//											}).finally(function() { });
//						}
//					});
//	 }
}