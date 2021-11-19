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

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location) {
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "code";
	$scope.sortreverse = false;
	$scope.securityLevel = 1;
	
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

		$scope.QueryCode = null;
		$scope.QueryName = null;
		$scope.QueryIsCI = null;
		$scope.QueryParentOrgId = null;
		$scope.QueryHealthLevelId = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End

	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		if ($scope.QueryCode == "")
			$scope.QueryCode = null;
		if ($scope.QueryName == "")
			$scope.QueryName = null;
		if ($scope.QueryIsCI == "")
			$scope.QueryIsCI = null;
		if ($scope.QueryParentOrgId == "")
			$scope.QueryParentOrgId = null;
		if ($scope.QueryHealthLevelId == "")
			$scope.QueryHealthLevelId =null;
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Code : $scope.QueryCode,
			Name : $scope.QueryName,
			IsCI : $scope.QueryIsCI,
			ParentOrgId : $scope.QueryParentOrgId,
			HealthLevelId : $scope.HealthLevelId
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/s08/query', request, csrf_config).then(function(response) {
			//console.log("datatable="+JSON.stringify(response.data.datatable));
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
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Code = "";
		$scope.Name = "";
		$scope.IsCI = false;
		$scope.IsPublic=false;
		$scope.orgCity = "";
		$scope.orgTown = "";
		$scope.orgAddress = "";
		$scope.parentOrgId = null;
		$scope.healthLevelId=0;
		$scope.securityLevel=0;
		$("#twzipcode").twzipcode('reset');
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
	$scope.editData = function(code) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {Code:code};
		$http.post('./api/s08/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Code = response.data[0].Code;
			$scope.Name = response.data[0].Name;
			$scope.orgCity = response.data[0].City;
			$scope.orgTown = response.data[0].Town;
			$scope.orgAddress = response.data[0].Address;
			$scope.parentOrgId = response.data[0].ParentOrgId;
			$scope.healthLevelId = response.data[0].HealthLevelId;
			$scope.securityLevel=response.data[0].SecurityLevel;
			$scope.IsCI = response.data[0].IsCI;
			$scope.IsPublic = response.data[0].IsPublic;
			$('#twzipcode').twzipcode('set', {
			    'county'   : $scope.orgCity,
			    'district' : $scope.orgTown
			});
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
	
	// Insert Item Start
	$scope.createData = function() {
		var request = {
			Code : $scope.Code,
			Name : $scope.Name,
			IsCI : $scope.IsCI,
			City : $scope.orgCity,
			Town : $scope.orgTown,
			Address : $scope.orgAddress,
			ParentOrgId : $scope.parentOrgId,
			HealthLevelId : $scope.healthLevelId,
			SecurityLevel : $scope.securityLevel,
			IsPublic : $scope.IsPublic
		};
		$http.post('./api/s08/create', request, csrf_config).then(
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
	// Insert Data End
	
	// Get Parent Orgs Start


	$scope.getAuthorOrgs = function() {
		var request = {
			
		};
		$http
			.post('../public/api/getAuthorOrgs', request, csrf_config)
			.then(function(response) {
				if (response.data.success == true) {
					$scope.authorOrgs = response.data.datatable;
				} else {
					$scope.authorOrgs = [];
				}		
			}).catch(function() {
				$scope.authorOrgs = [];
			});
	}
	$scope.getAuthorOrgs();
	
	// Get Parent Orgs End

	
	// Get HealthLevel Start 
	$scope.getHealthLevels = function(){
		var request = {
				
		};
		$http
		.post('./api/s08/getHealthLevels', request, csrf_config)
		.then(function(response) {
			if (response.data.success == true) {
				$scope.healthLevels = response.data.datatable;
			} else {
				$scope.healthLevels = [];
			}		
		}).catch(function() {
			$scope.healthLevels = [];
		});
	}
	
	$scope.getHealthLevels();
	
	// Get HealthLevel End



	// Update Data Start
	$scope.updateData = function() {
		var request = {
			Code : $scope.Code,
			Name : $scope.Name,
			IsCI : $scope.IsCI,
			City : $scope.orgCity,
			Town : $scope.orgTown,
			Address : $scope.orgAddress,
			ParentOrgId : $scope.parentOrgId,
			HealthLevelId : $scope.healthLevelId,
			SecurityLevel : $scope.securityLevel,
			IsPublic : $scope.IsPublic,
			
		};
		$http.post('./api/s08/update', request, csrf_config).then(
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
	// Update Data End
	
	
	
	
	
	// Delete Item Start
	$scope.deleteData = function(code) {
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
						Code: code
					};
					$http.post('./api/s08/delete', request, csrf_config).then(function(response) {
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
}