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

myApp.filter('join', function () {
    return function join(array, separator, prop) {
        if (!Array.isArray(array)) {
            return array; // if not array return original - can also throw error
        }

        return (!angular.isUndefined(prop) ? array.map(function (item) {
            return item[prop];
        }) : array).join(separator);
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, $timeout) {
	
	$scope.queryNumber = function() {
        $("#loadingActivity").fadeIn("slow");

		var request = {
			count_topname : true,
			count_p_id : true,
			count_paper_SerialNumber : true,
			paper_corId : "1"
		};
		$http.post('./common/queryNumber', request, csrf_config).then(function(response) {
			
			
			$("#peopleNum").text(response.data.peopleNum);
			$("#paperNum").text(response.data.paperNum);
			$("#paperCorNum").text(response.data.paperCorNum);
			$("#snaTopNum").text(response.data.snaTopNum);		
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
            $("#loadingActivity").fadeOut("slow");

		});
	};
	$scope.queryNumber();
	
	
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = false;
	$scope.isExcel = false;

	$scope.getorg = function() {
		var data = {
			sort : "name",
            dir: false
		};
		$http.post('./api/s05/getorg', data, csrf_config).then(function(response) {
			$scope.orgs = response.data;
			if (response.data.length == 1) {
				$scope.QueryOrgId = response.data[0].Id;
				$scope.QueryOrgIdDisable = true;
				$scope.OrgId = response.data[0].Id;
				$scope.CILevel = response.data[0].CILevel;
				$scope.OrgIdDisable = true;
			} else {
				$scope.QueryOrgIdDisable = false;
				$scope.OrgIdDisable = false;
			}
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
			$timeout(function() {				 
				 $('.selectpicker').selectpicker('refresh');				
			});
		});
	};
	$scope.getorg();
	
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

	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.QueryId = null;
		$scope.QueryOrgId = null;
		$scope.QueryAccount = null;
		$scope.QueryName = null;
		$scope.QueryEmail = null;
		$scope.QuerySpareEmail = null;
		$scope.QueryMobilePhone = null;
		$scope.QueryCityPhone = null;
		$scope.QueryFaxPhone = null;
		$scope.QueryAddress = null;
		$scope.QueryDepartment = null;
		$scope.QueryTitle = null;
		$scope.QueryIsEnable = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
	// Query Data Start
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		if ($scope.QueryId == "")
			$scope.QueryId = null;
		if ($scope.QueryOrgId == "")
			$scope.QueryOrgId = null;
		if ($scope.QueryAccount == "")
			$scope.QueryAccount = null;
		if ($scope.QueryName == "")
			$scope.QueryName = null;
		if ($scope.QueryEmail == "")
			$scope.QueryEmail = null;
		if ($scope.QuerySpareEmail == "")
			$scope.QuerySpareEmail = null;
		if ($scope.QueryMobilePhone == "")
			$scope.QueryMobilePhone = null;
		if ($scope.QueryCityPhone == "")
			$scope.QueryCityPhone = null;
		if ($scope.QueryFaxPhone == "")
			$scope.QueryFaxPhone = null;
		if ($scope.QueryAddress == "")
			$scope.QueryAddress = null;
		if ($scope.QueryDepartment == "")
			$scope.QueryDepartment = null;
		if ($scope.QueryTitle == "")
			$scope.QueryTitle = null;
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			OrgId :  $scope.QueryOrgId,
			Account : $scope.QueryAccount,
			Name : $scope.QueryName,
			Email : $scope.QueryEmail,
			SpareEmail : $scope.QuerySpareEmail,
			MobilePhone :  $scope.QueryMobilePhone,
			CityPhone : $scope.QueryCityPhone,
			FaxPhone : $scope.QueryFaxPhone,
			Address : $scope.QueryAddress,
			Department : $scope.QueryDepartment,
			Title : $scope.QueryTitle,
			IsEnable : $scope.QueryIsEnable,
			Status : $scope.QueryStatus
		};

		$http.post('./api/s05/query', request, csrf_config).then(function(response) {
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

	$scope.queryMemberRoleData = function(id) {
		
		var request = {
			Id : id
		};
		// console.log("request="+JSON.stringify(request));
		$http.post('./api/s05/member/role/query', request, csrf_config).then(function(response) {			
			$scope.memberRoleData = response.data.datatable;
			// console.log("response="+JSON.stringify(response.data.datatable));
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
	
$scope.querySubscribeData = function(id) {
		
		var request = {
			Id : id
		};
		// console.log("request="+JSON.stringify(request));
		$http.post('./api/s05/member/subscribe/query', request, csrf_config).then(function(response) {			
			$scope.SubscribeData = response.data.datatable;			
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
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function(isCreate) {		
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		if ($scope.orgs.length == 1) {
		} else {
			$scope.OrgId = null;
		}
		$scope.Account = null;
		$scope.Name = null;
		$scope.Email = null;
		$scope.SpareEmail = null;
		$scope.MobilePhone = null;
		$scope.CityPhone = null;
		$scope.FaxPhone = null;
		$scope.Address = null;
		$scope.Department = null;
		$scope.Title = null;
		$scope.IsEnable = false;
		$scope.IsPublic=false;
		$scope.healthLevelId=0;
		$scope.securityLevel=0;
		if(isCreate==1){
			$scope.queryMemberRoleData(0);
			$scope.querySubscribeData(0);
		}
	
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		
		angular.forEach($scope.allitems, function(value, key) {			
			 if (value.Id == id) {				 
				 $scope.CILevel = value.CILevel				
			 }
		});			
		$http.post('./api/s05/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit(0);
			$scope.btnIns = false;
			$scope.btnUpd = true;
			
			$scope.Id = response.data[0].Id;
			$scope.OrgId = response.data[0].OrgId;
			$scope.Account = response.data[0].Account;
			$scope.Name = response.data[0].Name;
			$scope.Email = response.data[0].Email;
			$scope.SpareEmail = response.data[0].SpareEmail;
			$scope.MobilePhone = response.data[0].MobilePhone;
			$scope.CityPhone = response.data[0].CityPhone;
			$scope.FaxPhone = response.data[0].FaxPhone;
			$scope.Address = response.data[0].Address;
			$scope.Department = response.data[0].Department;
			$scope.Title = response.data[0].Title;
			$scope.securityLevel = response.data[0].SecurityLevel;
			$scope.healthLevelId = response.data[0].HealthLevelId;
			$scope.IsPublic = response.data[0].IsPublic;
			$scope.IsEnable = response.data[0].IsEnable;
			
			
			$scope.queryMemberRoleData($scope.Id);
			$scope.querySubscribeData($scope.Id);

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
	
	
	// Get HealthLevel Start 
//	$scope.getHealthLevels = function(){
//		var request = {
//				
//		};
//		$http
//		.post('./api/s05/getHealthLevels', request, csrf_config)
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
	
	// Get HealthLevel End
	
	// Delete Item Start
	$scope.deleteData = function(id) {
		bootbox.confirm({
			message: globalSureDisableMember,
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
					$http.post('./api/s05/delete', request, csrf_config).then(function(response) {
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
	/* Delete Item End */
	
	// Delete Item Start
	$scope.delete = function(id) {
		bootbox.confirm({
			message: '確定要刪除此帳號嗎？',
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
					$http.post('./api/s05/realDelete', request, csrf_config).then(function(response) {
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
	/* Delete Item End */
	
	/* Insert Item Start */
	$scope.createData = function() {
		if ($scope.Enable == "")
			$scope.IsEnable = null;
		var request = {
			OrgId : $scope.OrgId,
			Account : $scope.Account,
			Name : $scope.Name,
			Email : $scope.Email,
			SpareEmail : $scope.SpareEmail,
			MobilePhone : $scope.MobilePhone,
			CityPhone : $scope.CityPhone,
			FaxPhone : $scope.FaxPhone,
			Address : $scope.Address,
			Department : $scope.Department,
			Title : $scope.Title,
			IsEnable : $scope.IsEnable,
			MemberRoleData: $scope.memberRoleData,
			SubscribeData: $scope.SubscribeData
		};
		$http.post('./api/s05/create', request, csrf_config).then(
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
	/* Insert Data End */

	/* Update Data Start */
	$scope.updateData = function() {
		if ($scope.Enable == "")
			$scope.IsEnable = null;
		
		var request = {
			Id : $scope.Id,
			OrgId : $scope.OrgId,
			Account : $scope.Account,
			Name : $scope.Name,
			Email : $scope.Email,
			SpareEmail : $scope.SpareEmail,
			MobilePhone : $scope.MobilePhone,
			CityPhone : $scope.CityPhone,
			FaxPhone : $scope.FaxPhone,
			Address : $scope.Address,
			Department : $scope.Department,
			Title : $scope.Title,
			IsEnable : $scope.IsEnable,
			MemberRoleData: $scope.memberRoleData,
			SubscribeData: $scope.SubscribeData
		};
		// console.log("request="+JSON.stringify(request));
		$http.post('./api/s05/update', request, csrf_config).then(
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
		};
		
		
		
		$scope.exportData = function() {
			$scope.isLoading = true
			if ($scope.QueryId == "")
				$scope.QueryId = null;
			if ($scope.QueryOrgId == "")
				$scope.QueryOrgId = null;
			if ($scope.QueryAccount == "")
				$scope.QueryAccount = null;
			if ($scope.QueryName == "")
				$scope.QueryName = null;
			if ($scope.QueryEmail == "")
				$scope.QueryEmail = null;
			if ($scope.QuerySpareEmail == "")
				$scope.QuerySpareEmail = null;
			if ($scope.QueryMobilePhone == "")
				$scope.QueryMobilePhone = null;
			if ($scope.QueryCityPhone == "")
				$scope.QueryCityPhone = null;
			if ($scope.QueryFaxPhone == "")
				$scope.QueryFaxPhone = null;
			if ($scope.QueryAddress == "")
				$scope.QueryAddress = null;
			if ($scope.QueryDepartment == "")
				$scope.QueryDepartment = null;
			if ($scope.QueryTitle == "")
				$scope.QueryTitle = null;
			if ($scope.QueryIsEnable == "")
				$scope.QueryIsEnable = null;
			if ($scope.QueryStatus == "")
				$scope.QueryStatus = null;
			
			var request = {											
				Id : $scope.QueryId,
				OrgId :  $scope.QueryOrgId,
				Account : $scope.QueryAccount,
				Name : $scope.QueryName,
				Email : $scope.QueryEmail,
				SpareEmail : $scope.QuerySpareEmail,
				MobilePhone :  $scope.QueryMobilePhone,
				CityPhone : $scope.QueryCityPhone,
				FaxPhone : $scope.QueryFaxPhone,
				Address : $scope.QueryAddress,
				Department : $scope.QueryDepartment,
				Title : $scope.QueryTitle,
				IsEnable : $scope.QueryIsEnable,
				Status : $scope.QueryStatus
			};

			$http.post('./api/s05/query', request, csrf_config).then(function(response) {
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
		        saveAs(blob, "member_"+(new Date()).toLocaleString(undefined, {
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
