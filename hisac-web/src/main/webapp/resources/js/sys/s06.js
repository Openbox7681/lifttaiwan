var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ui.toggle' ]).controller(
		'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

myApp.directive('integerOnly', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$validators.integerOnly = function(modelValue, viewValue) {
				if (ctrl.$isEmpty(viewValue)) {
					return true;
				}
				var pattern = /^[0-9]+$/;
				if (pattern.test(viewValue)) {
					return true;
				} else {
					return false;
				}
			};
		}
	};
});


function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location) {

	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 5;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = false;
	$scope.isExcel = false;

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
	
	$scope.changeOrgType = function() {
		$scope.authType0 = false;
		$scope.authType1 = false;
		$scope.authType2 = false;
		if ($scope.OrgType == '0' || $scope.OrgType == '1' || $scope.OrgType == '3') {
			$scope.AuthType = '0';
			$scope.authType0 = true;
			$scope.authType1 = false;
			$scope.authType2 = false;
		} else if ($scope.OrgType == '2') {
			$scope.AuthType = '1';
			$scope.authType0 = false;
			$scope.authType1 = true;
			$scope.authType2 = true;
		} else if ($scope.OrgType == '4') {
			$scope.AuthType = '2';
			$scope.authType0 = false;
			$scope.authType1 = true;
			$scope.authType2 = true;
		}
		
	}
	$scope.changeOrgType();
	
	$scope.clearData = function() {
		$scope.QueryId=null;
		$scope.QueryName=null;
		$scope.QueryCode=null;
		$scope.QueryOrgType=null;
		$scope.QueryAuthType=null;
		$scope.QueryCILevel=null;
		$scope.QueryTel=null;
		$scope.QueryFax=null;
		$scope.QueryCity=null;
		$scope.QueryTown=null;
		$scope.QueryAddress=null;
		$scope.QueryIsEnable=null;
		$scope.QueryIsApply=null;
		$scope.QueryHealthLevelId = null;
		$scope.QuerySecurityLevel = null;
		$scope.QueryIsPublic=null;
		$scope.QueryIsLocate=null;



		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	$scope.clearData();
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.QueryId == "")
			$scope.QueryId = null;
		if ($scope.QueryName == "")
			$scope.QueryName = null;
		if ($scope.QueryCode == "")
			$scope.QueryCode = null;
		if ($scope.QueryOrgType == "")
			$scope.QueryOrgType = null;
		if ($scope.QueryAuthType == "")
			$scope.QueryAuthType = null;
		if ($scope.QueryCILevel == "")
			$scope.QueryCILevel = null;
		if ($scope.QueryTel == "")
			$scope.QueryTel = null;
		if ($scope.QueryFax == "")
			$scope.QueryFax = null;
		if ($scope.QueryCity == "")
			$scope.QueryCity = null;
		if ($scope.QueryTown == "")
			$scope.QueryTown = null;
		if ($scope.QueryAddress == "")
			$scope.QueryAddress = null;
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryIsApply == "")
			$scope.QueryIsApply = null;
		if ($scope.QueryHealthLevelId =="")
			$scope.QueryHealthLevelId = null;
		if ($scope.QuerySecurityLevel == "")
			$scope.QuerySecurityLevel = null;
		if ($scope.QueryIsPublic == "")
			$scope.QueryIsPublic = null;
		if ($scope.QueryLocate == "")
			$scope.QueryLocate = null;
				
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			Name : $scope.QueryName,
			Code : $scope.QueryCode,
			OrgType : $scope.QueryOrgType,
			AuthType : $scope.QueryAuthType,
			CiLevel : $scope.QueryCILevel,
			Tel : $scope.QueryTel,
			Fax : $scope.QueryFax,
			City : $scope.QueryCity,
			Town : $scope.QueryTown,
			Address : $scope.QueryAddress,
			IsEnable : $scope.QueryIsEnable,
			IsApply : $scope.QueryIsApply,
			IsPublic : $scope.QueryIsPublic,
			HealthLevelId : $scope.QueryHealthLevelId,
			SecurityLevel : $scope.SecurityLevel,
			IsLocate : $scope.QueryIsLocate,

			
		};
		$http.post('./api/s06/query', request, csrf_config).then(function(response) {
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

	// Switch to Edit(Insert) Mode Start
	 $scope.openEdit  = function () {
		 $("#divEdit").show();
		 $("#divQuery").hide();
		 
		 $scope.btnIns = true;
		 $scope.btnUpd = false;

		 $scope.Id = null;
		 $scope.Name = null;
		 $scope.Code= null;
		 $scope.OrgType= '0';
		 $scope.AuthType= '0';
		 $scope.CILevel= '0';
		 $scope.Tel= null;
		 $scope.Fax= null;
		 $scope.City= null;
		 $scope.Town= null;
		 $scope.Address= null;
		 $scope.BossName= null;
		 $scope.BossEmail= null;
		 $scope.BossMobilePhone= null;
		 $scope.PrincipalName= null;
		 $scope.PrincipalMobilePhone= null;
		 $scope.IsEnable= false;
		 $scope.IsPublic=false;
		 $scope.healthLevelId= 0 ;
		 $scope.securityLevel= 0 ;
		 $scope.parentOrgId = null;
		 $scope.CiLevelLog = null ;
		 $scope.SecurityLevelLog = null ;
		 $scope.IsLocate= false;


		 
		 
		 $("#twzipcode").twzipcode('reset');
	 }

	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	
	$scope.sendQueryData = function() {
			$scope.currentPage = 1;
			$scope.start = 0;
			$scope.queryData();
	}
	
	//Get ParentOrg 
	$scope.getParentOrg =function(){
		var request = {
				
		};
		$http
		.post('./api/s06/getOrgSign', request, csrf_config)
		.then(function(response) {
			if (response.data.success == true) {
				$scope.parentOrgs = response.data.datatable;
			} else {
				$scope.parentOrgs = [];
			}		
		}).catch(function() {
			$scope.parentOrgs = [];
		});
		
		
	}
	
	$scope.getParentOrg();
	
	//Get CiLevelLogOrg  
	$scope.getCiLevelLog =function(orgId){
		$scope.CiLevelLog = null ;
		var request = {
				orgId : orgId
		};
		$http
		.post('./api/s06/queryCiLevelLog/id', request, csrf_config)
		.then(function(response) {
				$scope.SecurityLevelLog = response.data.datatable;
				console.log($scope.SecurityLevelLog);

				
		}).catch(function() {
			$scope.SecurityLevelLog = [];
		});
		
		
	}
	
	
	//Get SecurityLevelLogOrg  
	$scope.getSecurityLevelLog =function(orgId){
		$scope.SecurityLevelLog = null ;
		var request = {
				orgId : orgId
		};
		$http
		.post('./api/s06/querySecurityLevelLog/id', request, csrf_config)
		.then(function(response) {

				$scope.CiLevelLog = response.data.datatable;
				console.log($scope.CiLevelLog);
		
		}).catch(function() {
			$scope.CiLevelLog = [];
		});
		
		
	}
	
	
	
	
	
	// Get HealthLevel Start 
//	$scope.getHealthLevels = function(){
//		var request = {
//				
//		};
//		$http
//		.post('./api/s06/getHealthLevels', request, csrf_config)
//		.then(function(response) {
//			if (response.data.success == true) {
//				$scope.healthLevels = response.data.datatable;
//			} else {
//				$scope.healthLevels = [];
//			}		
//		}).catch(function() {
//			$scope.healthLevels = [];
//		});
//	}
//	
//	$scope.getHealthLevels();
//	
	// Get HealthLevel End

	$scope.editData = function(id) {
		
		

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		
		$http.post('./api/s06/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;

			$scope.Id = response.data[0].Id;
			$scope.Name = response.data[0].Name;
			$scope.Code= response.data[0].Code;
			$scope.OrgType= response.data[0].OrgType;
			$scope.changeOrgType();
			$scope.AuthType= response.data[0].AuthType;
			$scope.CILevel= response.data[0].CILevel;
			$scope.Tel= response.data[0].Tel;
			$scope.Fax= response.data[0].Fax;
			$scope.City= response.data[0].City;
			$scope.Town= response.data[0].Town;
			$scope.Address= response.data[0].Address;
			$scope.BossName= response.data[0].BossName;
			$scope.BossEmail= response.data[0].BossEmail;
			$scope.BossMobilePhone= response.data[0].BossMobilePhone;
			$scope.PrincipalName= response.data[0].PrincipalName;
			$scope.PrincipalMobilePhone= response.data[0].PrincipalMobilePhone;
			$scope.IsEnable= response.data[0].IsEnable;
			$scope.IsPublic = response.data[0].IsPublic;
			$scope.securityLevel = response.data[0].SecurityLevel;
			$scope.healthLevelId = response.data[0].HealthLevelId;
			$scope.IsLocate= response.data[0].IsLocate;
			

			$scope.parentOrgId = response.data[0].ParentOrgId;
			$scope.SecurityLevelLogs = response.data[0].SecurityLevelLogs;
			$scope.CiLevelLogs = response.data[0].CiLevelLogs;

			
			
			


			$('#twzipcode').twzipcode('destroy');
			$('#twzipcode').twzipcode({
				"zipcodeIntoDistrict" : true,
				"css" : ["city form-control",
 					"town form-control"],
					"countyName" : "city",
					"districtName" : "town",	
			    "countySel": $scope.City,
			    "districtSel": $scope.Town
			});
			
			$(".city").change(function() {
				$("#orgCity").val($(this).val());
				$("#orgTown").val($(".town").val());
				$("#orgCity").trigger('input');
				$("#orgCity").trigger('change');
				$("#orgTown").trigger('input');
				$("#orgTown").trigger('change');
			});
			$(".town").change(function() {
				$("#orgTown").val($(this).val());
				$("#orgTown").trigger('input');
				$("#orgTown").trigger('change');
			});
			
			bootbox.hideAll();
		})
		
//		.catch(function() {
//			bootbox.hideAll();
//			bootbox.alert({
//				message : globalReadDataFail,
//				buttons : {
//					ok : {
//						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//						className : 'btn-danger'
//					}
//				},
//				callback: function() { }
//			});
//		}).finally(function() { });
	}

	$scope.createData = function() {
		if ($scope.OrgType != '3') {
			$scope.CILevel = null;
		}
		var request = {
			Id : $scope.Id,
			Name : $scope.Name,
			Code : $scope.Code,
			OrgType : $scope.OrgType,
			AuthType : $scope.AuthType,
			CILevel : $scope.CILevel,
			Tel : $scope.Tel,
			Fax : $scope.Fax,
			City : $scope.City,
			Town : $scope.Town,
			Address : $scope.Address,
			BossName : $scope.BossName,
			BossEmail : $scope.BossEmail,
			BossMobilePhone : $scope.BossMobilePhone,
			PrincipalName : $scope.PrincipalName,
			PrincipalMobilePhone : $scope.PrincipalMobilePhone,
			IsEnable : $scope.IsEnable,
			HealthLevelId : $scope.healthLevelId,
			SecurityLevel : $scope.securityLevel,
			IsPublic : $scope.IsPublic,
			ParentOrgId : $scope.parentOrgId,
			IsLocate : $scope.IsLocate,

			
		};
		$http.post('./api/s06/create', request, csrf_config).then(
			function(response) {
				if (response.data.success) {
					$scope.queryData($scope.currentPage);
					bootbox.alert({
						message : response.data.msg,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-success',
							}
						},
						callback: function() {
							$scope.closeEdit();
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
	};

	$scope.updateData = function() {
		if ($scope.OrgType != '3') {
			$scope.CILevel = null;
		}
		var request = {
				Id : $scope.Id,
				Name : $scope.Name,
				Code : $scope.Code,
				OrgType : $scope.OrgType,
				AuthType : $scope.AuthType,
				CILevel : $scope.CILevel,
				Tel : $scope.Tel,
				Fax : $scope.Fax,
				City : $scope.City,
				Town : $scope.Town,
				Address : $scope.Address,
				BossName : $scope.BossName,
				BossEmail : $scope.BossEmail,
				BossMobilePhone : $scope.BossMobilePhone,
				PrincipalName : $scope.PrincipalName,
				PrincipalMobilePhone : $scope.PrincipalMobilePhone,
				IsEnable : $scope.IsEnable,
				HealthLevelId : $scope.healthLevelId,
				SecurityLevel : $scope.securityLevel,
				IsPublic : $scope.IsPublic,
				ParentOrgId : $scope.parentOrgId,
				IsLocate : $scope.IsLocate,


				
		};
//		console.log("datatable="+JSON.stringify(request));
		$http.post('./api/s06/update', request, csrf_config).then(
			function(response) {
				if (response.data.success) {
					$scope.queryData($scope.currentPage);
					bootbox.alert({
						message : response.data.msg,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-success',
							}
						},
						callback: function() {
							$scope.closeEdit();
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
	
	
	$scope.deleteApiKey = function(id){
		bootbox.confirm({
			message: '確定要停用此機構金鑰嗎?',
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
							Id: id,
							dataState : 'Delete'
						};
					
					$http.post('./api/ires/org/changeAgencyCertificateByState', request, csrf_config).then(function(response) {
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
	
	$scope.passApiKey = function(id){
		bootbox.confirm({
			message: "確定要通過此金鑰機構嗎？",
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
							Id: id,
							dataState : 'Normal'
						};
					
					$http.post('./api/ires/org/changeAgencyCertificateByState', request, csrf_config).then(function(response) {
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
					$http.post('./api/s06/delete', request, csrf_config).then(function(response) {
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

$scope.exportData = function() {
	$scope.isLoading = true
	if ($scope.QueryId == "")
		$scope.QueryId = null;
	if ($scope.QueryName == "")
		$scope.QueryName = null;
	if ($scope.QueryCode == "")
		$scope.QueryCode = null;
	if ($scope.QueryOrgType == "")
		$scope.QueryOrgType = null;
	if ($scope.QueryAuthType == "")
		$scope.QueryAuthType = null;
	if ($scope.QueryCILevel == "")
		$scope.QueryCILevel = null;
	if ($scope.QueryTel == "")
		$scope.QueryTel = null;
	if ($scope.QueryFax == "")
		$scope.QueryFax = null;
	if ($scope.QueryCity == "")
		$scope.QueryCity = null;
	if ($scope.QueryTown == "")
		$scope.QueryTown = null;
	if ($scope.QueryAddress == "")
		$scope.QueryAddress = null;
	if ($scope.QueryIsEnable == "")
		$scope.QueryIsEnable = null;
	if ($scope.QueryIsApply == "")
		$scope.QueryIsApply = null;
	if ($scope.QueryIsLocate == "")
		$scope.QueryIsLocate = null;
			
	
	var request = {		
		Id : $scope.QueryId,
		Name : $scope.QueryName,
		Code : $scope.QueryCode,
		OrgType : $scope.QueryOrgType,
		AuthType : $scope.QueryAuthType,
		CiLevel : $scope.QueryCILevel,
		Tel : $scope.QueryTel,
		Fax : $scope.QueryFax,
		City : $scope.QueryCity,
		Town : $scope.QueryTown,
		Address : $scope.QueryAddress,
		IsEnable : $scope.QueryIsEnable,
		IsApply : $scope.QueryIsApply,
		IsPublic : $scope.QueryIsPublic,
		HealthLevelId : $scope.QueryHealthLevelId,
		SecurityLevel : $scope.SecurityLevel,
		IsLocate : $scope.QueryIsLocate,

		
		
	};
	$http.post('./api/s06/query', request, csrf_config).then(function(response) {
		$scope.allExcelItems = response.data.datatable;										
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
		$scope.isLoading = false
		$scope.isExcel = true;
	});							
    }


$scope.downloadExcel = function() {
	 var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "org_"+(new Date()).toLocaleString(undefined, {
	   	    day:'numeric',
	   	    month: 'numeric',
	   	    year: 'numeric',
	   	    hour: '2-digit',
	   	    minute: '2-digit',
	   	    second: '2-digit',
	   	    hour12: false 
	   	})+".xls");
}
}
$(document).ready(function() {
	$(".city").change(function() {
		$("#orgCity").val($(this).val());
		$("#orgTown").val($(".town").val());
		$("#orgCity").trigger('input');
		$("#orgCity").trigger('change');
		$("#orgTown").trigger('input');
		$("#orgTown").trigger('change');
	});
	$(".town").change(function() {
		$("#orgTown").val($(this).val());
		$("#orgTown").trigger('input');
		$("#orgTown").trigger('change');
	});
});

