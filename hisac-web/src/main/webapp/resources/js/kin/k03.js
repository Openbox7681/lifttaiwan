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
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			InspectStatus : $scope.QueryStatus,			
			Keyword : $scope.QueryKeyword,
			
		};
			$http.post('./api/k03/query', request, csrf_config).then(function(response) {	
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
	
	$scope.queryDataId = function(id, groupId) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id,
			GroupId:groupId
		};
		
		$http.post('./api/k03/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.Id = response.data.Id;
			$scope.Title = response.data.Title;
			$scope.Year = response.data.Year;
			$scope.MaintainInspectId = response.data.MaintainInspectId;
			$scope.OrgId = response.data.OrgId;
			$scope.allAttach = response.data.Other;
			$scope.Log =  response.data.Log;
			//檢核表
			$scope.FileCheckListName =  response.data.FileCheckListName;
			$scope.fileCheckList = {name : response.data.FileCheckListName};
			$scope.FileCheckListId = response.data.FileCheckListId;
			//調查表
			$scope.FileQuestionnaireName =  response.data.FileQuestionnaireName;
			$scope.fileQuestionnaire = {name : response.data.FileQuestionnaireName};
			$scope.FileQuestionnaireId = response.data.FileQuestionnaireId;
			//自評表
			$scope.FileSelfEvaluationName =  response.data.FileSelfEvaluationName;
			$scope.fileSelfEvaluation = {name : response.data.FileSelfEvaluationName};
			$scope.FileSelfEvaluationId = response.data.FileSelfEvaluationId;
			//稽核結果
			$scope.FileAuditResultName =  response.data.FileAuditResultName;
			$scope.fileAuditResult = {name : response.data.FileAuditResultName};
			$scope.FileAuditResultId = response.data.FileAuditResultId;
			$scope.auditResultAbstract = response.data.AuditResultAbstract
			//改善報告
			$scope.FileImprovementName =  response.data.FileImprovementName;
			$scope.fileImprovement = {name : response.data.FileImprovementName};
			$scope.FileImprovementId = response.data.FileImprovementId;
			$scope.improvementAbstract = response.data.ImprovementAbstract;
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
		//其他檔案清空
		$scope.fileOtherList = [];
		
		$scope.HospitalUpload = false;
		//補件
		$scope.AllowHospitalPatch = false;
		
		$scope.IsImproveReport = false;
		$scope.ShowImproveReport = false;
		
		$scope.IsAuditResult = false;
	};
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id, groupId) {
		$scope.IsQuestionnaire = true;
		$scope.showInspect = true;
		$scope.queryDataId(id, groupId);
	}
	// Switch to Edit(Update) Mode End
	// Switch to 補件  Mode Start
	$scope.additionalDocuments = function(id, groupId,allowHospitalPatch) {
		$scope.IsQuestionnaire = false;
		$scope.showInspect = true;
		$scope.AllowHospitalPatch = allowHospitalPatch;
		$scope.queryDataId(id, groupId);
	}
	// Switch to 補件  Mode End
	// Switch to 改善報告  Mode Start
	$scope.improveReport = function(id, groupId) {
		$scope.IsQuestionnaire = false;
		$scope.showInspect = true;
		$scope.IsImproveReport = true;
		$scope.IsAuditResult = true;
		$scope.queryDataId(id, groupId);
	}
	// Switch to 改善報告  Mode End
	$scope.fileOtherList = [];
	/**其他檔案存進list-Start**/
	$scope.tempFile = function (file,fileDescription) {
        if($scope.fileOtherList != null){
            $scope.fileOtherList.push({file,fileDescription});
            $scope.fileOther = null;
            $scope.FileDescription = null;
        }
	}
	/**其他檔案存進list-End**/
	/**其他檔案刪除list-Start**/
	$scope.removeFile = function (index) {
    	$scope.fileOtherList.splice(index, 1);
	};
	/**其他檔案刪除list-End**/
	
	/**其他檔案刪除db-Start**/
	$scope.deleteOtherFile = function (id,index) {
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
					$http.post('./api/k03/fileOtherDelete', request, csrf_config).then(function(response) {
					    	if(index != null)
					    	    $scope.allAttach.splice(index, 1);
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
	};
	/**其他檔案刪除db-End**/
	
	/**稽核前訪談調查文件(送出)**/
	$scope.questionnaire_submit = function(check) {	
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var data = {																						
				MaintainInspectId : $scope.MaintainInspectId,
				FileQuestionnaireId : $scope.FileQuestionnaireId,
				FileCheckListId : $scope.FileCheckListId,
				FileSelfEvaluationId : $scope.FileSelfEvaluationId,
				Check : check,
		};
		Upload.upload({
			url : './api/k03/questionnaire_submit',
			data : {
				json : JSON.stringify(data),
				fileQuestionnaire : $scope.fileQuestionnaire,		
				fileCheckList : $scope.fileCheckList,
				fileSelfEvaluation : $scope.fileSelfEvaluation,
				
				FileQuestionnaireDesc : '',
				FileCheckListDesc : '',
				FileSelfEvaluationDesc : '',
			},
			headers: header
		})
				.then(				
					function(response) {
							//假設List有10筆，我會跑10次呼叫api
							for(let i = 0;i < $scope.fileOtherList.length; i++){
								console.log($scope.fileOtherList[i]);
								$scope.otherFileUpload( $scope.fileOtherList[i].file, $scope.fileOtherList[i].fileDescription);
							}
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
	/**稽核前訪談調查文件(送出)-End**/
	/**其他檔案上傳**/
	$scope.otherFileUpload = function(file,desc) {	
		var data = {																						
			MaintainInspectId : $scope.MaintainInspectId
		};
		Upload.upload({
				url : './api/k03/otherFileUpload',
				data : {
					json : JSON.stringify(data),
					fileOther : file,
					FileOtherDesc : desc,
				},
				headers: header
		}).then(				
				function(response) {
					if (response.data.success) {
						
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
	/**其他檔案上傳-End**/
	/**改善報告**/
			$scope.improvement_submit = function() {
				bootbox.dialog({
					closeButton: false,
					message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
				});
				var data = {																						
						MaintainInspectId : $scope.MaintainInspectId,			
						FileImprovementId : $scope.FileImprovementId,
						GroupId : $scope.OrgId
				};
							
				Upload.upload({
					url : './api/k03/improvement_submit',
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
	/**查看-start**/
	$scope.review = function(id, groupId, status) {
		$scope.editData(id,groupId);
		if(status == 4){
			$scope.showInspect = false;
		}
		else if(status == 6){
			$scope.showInspect = true;
		}
		else if(status == 8){
			$scope.IsAuditResult = true;
		}
		else if(status == 9){
			$scope.IsAuditResult = true;
			$scope.IsImproveReport = false;
			$scope.ShowImproveReport = true;
		}
			
		$scope.IsQuestionnaire = false;
			
	};
	/**查看-end**/
	/**狀態計算-開始**/
	$scope.queryButtonCount = function() {
		
		var request = {
		};
		$http.post('./api/k03/query/button/count', request, csrf_config).then(function(response) {
			$scope.Status4Count = response.data.Status4Count;
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
	 
	 $scope.deletefileSelfEvaluation = function() {
		 $scope.fileSelfEvaluation = null
		 $scope.FileSelfEvaluationId = null
		 
	 }
	 
	 $scope.changefileSelfEvaluation = function() {		
		 $scope.FileSelfEvaluationId = null
	 }
	 
	 $scope.deletefileOther = function() {
		 $scope.fileOther.name = null
	 }
	 
	 $scope.changefileOther = function() {		
		 $scope.fileOther.name = null
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