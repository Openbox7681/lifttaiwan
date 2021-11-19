var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {
	//醫院資料上傳時間 start
	$('input[id="HospitalUploadSdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });
	$('input[id="HospitalUploadEdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });
	$("#HospitalUploadSdate").on("dp.change", function(e) {	       
		$scope.HospitalUploadSdate = $('#HospitalUploadSdate').val();
		if ($scope.HospitalUploadSdate > $scope.HospitalUploadEdate && $scope.HospitalUploadEdate != null) {
			$scope.HospitalUploadEdate = $scope.HospitalUploadSdate;
			$('#HospitalUploadEdate').val($scope.HospitalUploadEdate);
		}
	});
	$("#HospitalUploadEdate").on("dp.change", function(e) {	       
		$scope.HospitalUploadEdate = $('#HospitalUploadEdate').val();
		if ($scope.HospitalUploadEdate < $scope.HospitalUploadSdate && $scope.HospitalUploadSdate != null) {
			$scope.HospitalUploadSdate = $scope.HospitalUploadEdate;
			$('#HospitalUploadSdate').val($scope.HospitalUploadSdate);
		}
	});
	//醫院資料上傳時間 end

	//稽核委員上傳時間 start
	$('input[id="CommitteeUploadSdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });
	$('input[id="CommitteeUploadEdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$("#CommitteeUploadSdate").on("dp.change", function(e) {	       
		$scope.CommitteeUploadSdate = $('#CommitteeUploadSdate').val();
		if ($scope.CommitteeUploadSdate > $scope.CommitteeUploadEdate && $scope.CommitteeUploadEdate != null) {
			$scope.CommitteeUploadEdate = $scope.CommitteeUploadSdate;
			$('#CommitteeUploadEdate').val($scope.CommitteeUploadEdate);
		}
	});
	$("#CommitteeUploadEdate").on("dp.change", function(e) {	       
		$scope.CommitteeUploadEdate = $('#CommitteeUploadEdate').val();
		if ($scope.CommitteeUploadEdate < $scope.CommitteeUploadSdate && $scope.CommitteeUploadSdate != null) {
			$scope.CommitteeUploadSdate = $scope.CommitteeUploadEdate;
			$('#CommitteeUploadSdate').val($scope.CommitteeUploadSdate);
		}
	});
	//稽核委員上傳時間 end

	// Main Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "maintainInspectId";
	$scope.sortreverse = true;

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
	// Main Paging End
	
	// Clear Data Start
	$scope.clearData = function() {
		$("#divQuery").show();
    	$("#divQueryHospital").hide();
    	$("#divEdit").hide();
    	
        $scope.QueryKeyword = null;
        $scope.QueryYear = null;
	}
	$scope.clearData();
	// Clear Data End
	
	// Query Data Start
	$scope.queryData = function(page) {
	    $("#imgLoading").show();
	    if (page) {
	        $scope.start = (page - 1) * $scope.maxRows
	    }

	    if ($scope.QueryYear == "")
	        $scope.QueryYear = null;		
	    if ($scope.QueryKeyword == "")
	        $scope.QueryKeyword = null;		
	    
	    let request = {
	    	start : $scope.start,
	    	maxRows : $scope.maxRows,
	    	sort : $scope.sorttype,
	    	dir : $scope.sortreverse,
	    	
	    	Year: $scope.QueryYear,
	    	Keyword : $scope.QueryKeyword,
	    };
	    $http.post('./api/k01/query', request, csrf_config).then(function(response) {
//	    	console.log(response);
	        $scope.allitems = response.data.datatable;
	        $scope.total = response.data.total;
	        $scope.maxPages = parseInt($scope.total / $scope.maxRows);
	        $scope.pageRows = $scope.total % $scope.maxRows;
	        if ($scope.pageRows != 0)
	            $scope.maxPages++;
	        $scope.returnTotal = true;
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

	// Switch to Query Hospital Start
	$scope.openQueryHospital = function() {
		$("#divQueryHospital").show();
		$("#divQuery").hide();
		
		$scope.HospitalTitle = null;
		$scope.HospitalYear = null;
		$scope.HospitalStatus = null;
    	$scope.allHospitalitems = null;
	};
	$scope.closeQueryHospital = function() {
		$("#divQueryHospital").hide();
		$("#divQuery").show();
		
		$scope.HospitalTitle = null;
		$scope.HospitalYear = null;
		$scope.HospitalStatus = null;
    	$scope.allHospitalitems = null;
	};
	// Switch to Query Hospital Start
	
	// Query Hospital Data Start
	$scope.queryHospitalData = function(maintainInspectId) {
		$scope.openQueryHospital();
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		let request = {
			MaintainInspectId : maintainInspectId,
	    };
	    $http.post('./api/k01/queryHospital', request, csrf_config).then(function(response) {
//	    	console.log(response);
	    	$scope.allHospitalitems = response.data.datatable;
	    	if($scope.allHospitalitems!=null && $scope.allHospitalitems.length!=0) {
	    		$scope.HospitalTitle = $scope.allHospitalitems[0].Title;
	    		$scope.HospitalYear = $scope.allHospitalitems[0].Year;
	    		$scope.HospitalStatus = $scope.allHospitalitems[0].Status;
	    	}
	    	bootbox.hideAll();
	    }).catch(function() {
	    	bootbox.hideAll();
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
	    	
	    });
	};
	// Query Hospital Data End
	
	// Switch to Edit(Edit) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$("#divQueryHospital").hide();
		$scope.btnEdit = true;
		$scope.btnCommittee = false;
		$scope.btnAuditResult = false;
		
		$scope.Id = null;
        $scope.MaintainInspectId = null;
        $scope.GroupId = null;
        $scope.Title = "";
        $scope.Year = "";
        $scope.OrgName = "";
        $scope.InspectStatus = "";
        $scope.AllowHospitalPatch = null;
        $scope.HospitalUploadSdate = null;
        $scope.HospitalUploadEdate = null;
        $scope.CommitteeUploadSdate = null;
        $scope.CommitteeUploadEdate = null;
		$("#HospitalUploadSdate").val("");
        $("#HospitalUploadEdate").val("");
		$("#CommitteeUploadSdate").val("");
        $("#CommitteeUploadEdate").val("");
        $("#HospitalUploadSdate").data("DateTimePicker").clear();
		$("#HospitalUploadEdate").data("DateTimePicker").clear();
        $("#CommitteeUploadSdate").data("DateTimePicker").clear();
		$("#CommitteeUploadEdate").data("DateTimePicker").clear();
		$scope.CommitteeList = [];
		$scope.committees = [];
		$scope.AuditResultDesc = null;
		$scope.AuditResultFile = null;
	};
	// Switch to Edit(Edit) Mode End

	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").hide();
		$("#divQueryHospital").show();
		$scope.btnEdit = false;
		$scope.btnCommittee = false;
		$scope.btnAuditResult = false;
	};
	// Switch to Query Mode End	
		
	// Edit Data Start
	$scope.editData = function(id) {
		$scope.openEdit();

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
	    let request = {
    		Id : id,
	    };
	    $http.post('./api/k01/queryHospital/id', request, csrf_config).then(function(response) {
//	        console.log(response);
	        
	        $scope.Id = response.data.Id;
	        $scope.MaintainInspectId = response.data.MaintainInspectId;
	        $scope.GroupId = response.data.GroupId;	        
	        $scope.Title = response.data.Title;
	        $scope.Year = response.data.Year;
	        $scope.OrgName = response.data.OrgName;
	        $scope.InspectStatus = response.data.InspectStatus;
	        $scope.HospitalUploadSdate = response.data.HospitalUploadSdate;
	        if(!$scope.HospitalUploadSdate) {
				$scope.HospitalUploadSdate = moment().format("YYYY-MM-DD");	
	        }
	        $scope.HospitalUploadEdate = response.data.HospitalUploadEdate;
	        if(!$scope.HospitalUploadEdate) {
				$scope.HospitalUploadEdate = moment().format("YYYY-MM-DD");
	        }
	        $scope.CommitteeUploadSdate = response.data.CommitteeUploadSdate;
	        if(!$scope.CommitteeUploadSdate) {
				$scope.CommitteeUploadSdate = moment().format("YYYY-MM-DD");	
	        }
	        $scope.CommitteeUploadEdate = response.data.CommitteeUploadEdate;
	        if(!$scope.CommitteeUploadEdate) {
				$scope.CommitteeUploadEdate = moment().format("YYYY-MM-DD");	
	        }
	        $scope.AllowHospitalPatch = response.data.AllowHospitalPatch;
	        $scope.CommitteeList = response.data.CommitteeList;
			//稽核結果
	        $scope.ResultAttach = response.data.ResultAttach;
	        if($scope.ResultAttach) {
	        	$scope.AuditResultDesc = $scope.ResultAttach.FileDesc;
				$scope.AuditResultFile = {name : $scope.ResultAttach.FileName};
				$scope.AuditResultFileId = $scope.ResultAttach.FileId;
	        }
	        
	        bootbox.hideAll();
	    }).catch(function() {
	        bootbox.hideAll();
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
	    	$scope.queryCommittees();
	    });
	};
	// Edit Data End
	
	// Update Data Start
	$scope.updateData = function(exit, check) {
		console.log("$scope.CommitteeUploadSdate='"+$scope.CommitteeUploadSdate+"'");
		if($scope.HospitalUploadSdate=="") {
			$("#HospitalUploadSdate").val("");
	        $("#HospitalUploadSdate").data("DateTimePicker").clear();			
		}
		if($scope.HospitalUploadEdate=="") {
			$("#HospitalUploadEdate").val("");
			$("#HospitalUploadEdate").data("DateTimePicker").clear();
		}
		if($scope.CommitteeUploadSdate=="") {
			$("#CommitteeUploadSdate").val("");
			$("#CommitteeUploadSdate").data("DateTimePicker").clear();
		}
		if($scope.CommitteeUploadEdate=="") {
	        $("#CommitteeUploadEdate").val("");
			$("#CommitteeUploadEdate").data("DateTimePicker").clear();
		}
		
		if (check) {
			$scope.InspectStatus = "4";
			$scope.IsWriteProcessLog = true;
		} else {
			$scope.InspectStatus = "3";
			$scope.IsWriteProcessLog = false;
		}
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		let request = {
			Id: $scope.Id,
			HospitalUploadSdate: $scope.HospitalUploadSdate,
			HospitalUploadEdate: $scope.HospitalUploadEdate,
			CommitteeUploadSdate: $scope.CommitteeUploadSdate,
			CommitteeUploadEdate: $scope.CommitteeUploadEdate,
			InspectStatus: $scope.InspectStatus,
			CommitteeList: $scope.CommitteeList,
			TableName : "maintainInspectMember",
			IsWriteProcessLog : $scope.IsWriteProcessLog
		};
		console.log(request);
		
		$http.post('./api/k01/updateHospital', request, csrf_config).then(function(response) {
//			console.log(response);
			bootbox.hideAll();
			if (response.data.success) {											 																						
				bootbox.dialog({
					message : "<span class='bigger-110'>" + response.data.msg + "</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 確定",
							"callback" : function() {
								if (exit) {
									$scope.queryHospitalData(response.data.MaintainInspectId);
									$scope.closeEdit();
								} else {
									$scope.editData(response.data.Id);
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
			bootbox.hideAll();
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
		});
	};
	// Update Data End

	// Edit Committee Start
	$scope.editCommittee = function(id) {
		$scope.editData(id);
		$scope.btnEdit = false;
		$scope.btnCommittee = true;
		$scope.btnAuditResult = false;
		
	};
	// Edit Committee End

	//Committee Start
	$scope.addCommittee = function() {
		if($scope.Committee) {
			let dataExist = false;
			for(let i=0; i<$scope.CommitteeList.length; i++) {
				if($scope.CommitteeList[i].CommitteeMemberId == $scope.Committee.MemberId) {
					dataExist = true;
				}
			}
			if(!dataExist) {
				$scope.CommitteeList.push({
					CommitteeMemberId: $scope.Committee.MemberId,
					Name: $scope.Committee.Name,
				});
			}
		}
	};
	$scope.deleteCommittee = function(committeeMemberId) {
		for(let i=0; i<$scope.CommitteeList.length; i++) {
			if($scope.CommitteeList[i].CommitteeMemberId == committeeMemberId) {
				$scope.CommitteeList.splice(i, 1);
			}
		}
	};
	$scope.queryCommittees = function() {
		let request = {
				
	    };
	    $http.post('./api/k01/queryCommittees', request, csrf_config).then(function(response) {
//	    	console.log(response);
	    	$scope.committees = response.data.committees;
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
	    });	
	};
	//Committee End

	// Committee Data Start
	$scope.committeeData = function(exit, check) {
		if (check) {
			if($scope.CommitteeList==null || $scope.CommitteeList.length==0) {
		    	bootbox.alert({
		            message : "請輸入稽核委員資料",
		            buttons : {
		                ok : {
		                    label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
		                    className : 'btn-danger',
		                }
		            },
		            callback: function() { }
		        });
				return;
			}
		}
		
		if (check) {
			$scope.InspectStatus = "6";
			$scope.IsWriteProcessLog = true;
		} else {
			$scope.InspectStatus = "5";
			$scope.IsWriteProcessLog = false;
		}
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		let request = {
			Id: $scope.Id,
			HospitalUploadSdate: $scope.HospitalUploadSdate,
			HospitalUploadEdate: $scope.HospitalUploadEdate,
			CommitteeUploadSdate: $scope.CommitteeUploadSdate,
			CommitteeUploadEdate: $scope.CommitteeUploadEdate,
			InspectStatus: $scope.InspectStatus,
			CommitteeList: $scope.CommitteeList,
			TableName : "maintainInspectMember",
			IsWriteProcessLog : $scope.IsWriteProcessLog
		};
//		console.log(request);
		
		$http.post('./api/k01/committeeHospital', request, csrf_config).then(function(response) {
//			console.log(response);
			bootbox.hideAll();
			if (response.data.success) {											 																						
				bootbox.dialog({
					message : "<span class='bigger-110'>" + response.data.msg + "</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 確定",
							"callback" : function() {
								if (exit) {
									$scope.queryHospitalData(response.data.MaintainInspectId);
									$scope.closeEdit();
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
			bootbox.hideAll();
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
		});
	};
	// Committee Data End

	// Toggle AllowHospitalPatch Start
	$scope.toggleAllowHospitalPatch = function(id, allowHospitalPatch) {
		let toggleessage = allowHospitalPatch==false ? "確定要開放補件？" : "確定要關閉補件？";

		bootbox.confirm({
			message: toggleessage,
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					
					let request = {
						Id: id,
						AllowHospitalPatch: !allowHospitalPatch,
					};
//					console.log(request);
					$http.post('./api/k01/toggleAllowHospitalPatch', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
						if (response.data.success) {											 																						
							bootbox.dialog({
								message : "<span class='bigger-110'>" + response.data.msg + "</span>",
								buttons : {
									"success" : {
										"label" : "<i class='ace-icon fa fa-check'></i> 確定",
										"callback" : function() {
											$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
					});
				}
			}
		});
		
	};
	// Toggle AllowHospitalPatch End

	// Edit AuditResult Start
	$scope.editAuditResult = function(id) {
		$scope.editData(id);
		$scope.btnEdit = false;
		$scope.btnPatch = false;
		$scope.btnAuditResult = true;

	};
	// Edit AuditResult End
	
	// AuditResult add/remove start
	$scope.deleteAuditResultFile = function() {
		$scope.AuditResultFile = null;
		$scope.AuditResultFileId = null;
	};
	$scope.changeAuditResultFile = function() {
		$scope.AuditResultFileId = null;
	};
	// AuditResult add/remove end

	// AuditResult Data start
	$scope.auditResultData = function(exit, check) {
		if (check) {
			$scope.InspectStatus = "8";
			$scope.IsWriteProcessLog = true;
		} else {
			$scope.InspectStatus = "7";
			$scope.IsWriteProcessLog = false;
		}
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		let request = {
			Id: $scope.Id,
			AuditResultFileId: $scope.AuditResultFileId,
			AuditResultDesc: $scope.AuditResultDesc,
	        MaintainInspectId: $scope.MaintainInspectId,
	        GroupId: $scope.GroupId,
			InspectStatus: $scope.InspectStatus,
			TableName : "maintainInspectMember",
			IsWriteProcessLog : $scope.IsWriteProcessLog
		};
//		console.log(request);
		
		Upload.upload({
			url : './api/k01/auditResultFileUpload',
			data : {
				json : JSON.stringify(request),
				AuditResultFile : $scope.AuditResultFile				
			},
			headers: header
		}).then(function(response) {
//			console.log(response);
			bootbox.hideAll();
			if (response.data.success) {											 																						
				bootbox.dialog({
					message : "<span class='bigger-110'>" + response.data.msg + "</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 確定",
							"callback" : function() {
								if (exit) {
									$scope.queryHospitalData(response.data.MaintainInspectId);
									$scope.closeEdit();
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
				message : "<span class='bigger-110'>資料送出失敗</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
						"className": 'btn-danger',
						"callback" : function() {}
					}
				}
			});
		}).finally(function() {
			
		});
	};
	// AuditResult Data end
	
	// Query HospitalDetail Start
	$scope.queryHospitalDetail = function(id) {
		$scope.DetailGroupId = null;
		$scope.DetailMaintainPlanId = null;
	    $scope.DetailOrgName = null;
	    $scope.DetailInspectStatus = null;
        $scope.DetailHospitalUploadSdate = null;
        $scope.DetailHospitalUploadEdate = null;
        $scope.DetailCommitteeUploadSdate = null;
        $scope.DetailCommitteeUploadEdate = null;
        $scope.DetailAllowHospitalPatch = null;
        $scope.DetailCommitteeList = null;
        $scope.DetailCheckListSampleAttach = null;
        $scope.DetailQuestionnaireSampleAttach = null;
        $scope.DetailSelfEvaluationSampleAttach = null;
        $scope.DetailCheckListAttach = null;
        $scope.DetailQuestionnaireAttach = null;
        $scope.DetailSelfEvaluationAttach = null;
        $scope.DetailOtherAttach = null;
        $scope.DetailScoreAttach = null;
        $scope.DetailResultAttach = null;
        $scope.DetailImprovementAttach = null;
        $scope.DetailProcessLog = null;

	    let request = {
    		Id : id,
	    };		
	    $http.post('./api/k01/queryHospital/id', request, csrf_config).then(function(response) {
		    console.log(response);
		    
			$scope.DetailGroupId = response.data.GroupId;
			$scope.DetailMaintainPlanId = response.data.MaintainPlanId;
		    $scope.DetailOrgName = response.data.OrgName;
	        $scope.DetailInspectStatus = response.data.InspectStatus;
	        $scope.DetailHospitalUploadSdate = response.data.HospitalUploadSdate;
	        $scope.DetailHospitalUploadEdate = response.data.HospitalUploadEdate;
	        $scope.DetailCommitteeUploadSdate = response.data.CommitteeUploadSdate;
	        $scope.DetailCommitteeUploadEdate = response.data.CommitteeUploadEdate;
	        $scope.DetailAllowHospitalPatch = response.data.AllowHospitalPatch;
	        $scope.DetailCommitteeList = response.data.CommitteeList;
	        $scope.DetailCheckListSampleAttach = response.data.CheckListSampleAttach;
	        $scope.DetailQuestionnaireSampleAttach = response.data.QuestionnaireSampleAttach;
	        $scope.DetailSelfEvaluationSampleAttach = response.data.SelfEvaluationSampleAttach;
	        $scope.DetailCheckListAttach = response.data.CheckListAttach;
	        $scope.DetailQuestionnaireAttach = response.data.QuestionnaireAttach;
	        $scope.DetailSelfEvaluationAttach = response.data.SelfEvaluationAttach;
	        $scope.DetailOtherAttach = response.data.OtherAttach;
	        $scope.DetailScoreAttach = response.data.ScoreAttach;
	        $scope.DetailResultAttach = response.data.ResultAttach;
	        $scope.DetailImprovementAttach = response.data.ImprovementAttach;
	        $scope.DetailProcessLog = response.data.ProcessLog;
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
	    	
	    });
	};
	// Query HospitalDetail End

	// Return ImprovementUpload Start
	$scope.returnToImprovementUpload = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新上傳改善報告嗎？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToImprovementUpload', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return ImprovementUpload Start
	
	// Return AuditResultUpload Start
	$scope.returnToAuditResultUpload = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新上傳稽核結果嗎？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToAuditResultUpload', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return AuditResultUpload End

	// Return AuditScoreUpload Start
	$scope.returnToAuditScoreUpload = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新上傳稽核評分表嗎？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToAuditScoreUpload', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return AuditScoreUpload Start
		
	// Return CommitteeList Start
	$scope.returnToCommitteeList = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新輸入稽核委員名單嗎？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToCommitteeList', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return CommitteeList Start

	// Return Hospital Start
	$scope.returnToHospital = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新進行稽核前訪談調查嗎？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToHospital', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return Hospital End
	
	// Return Hospital Start
	$scope.returnToEditData = function(id) {
		bootbox.confirm({
			message: "確定要退回並重新設定稽核基本資料？",
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
					bootbox.dialog({
						closeButton: false,
						message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
					});
					let request = {
						Id: id
					};
//					console.log(request);
					
					$http.post('./api/k01/returnToEditData', request, csrf_config).then(function(response) {
//						console.log(response);
						bootbox.hideAll();
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
									$scope.queryHospitalData(response.data.MaintainInspectId);
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
						bootbox.hideAll();
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
			}
		});
	};
	// Return Hospital End
};
