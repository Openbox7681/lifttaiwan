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
	$scope.sorttype = "sort";
	$scope.sortreverse = false;

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
		$scope.QueryCode = null;
		$scope.QueryName = null;
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
		if ($scope.QueryCode == "")
			$scope.QueryCode = null;
		if ($scope.QueryName == "")
			$scope.QueryName = null;
		if ($scope.QueryStixXsd == "")
			$scope.QueryStixXsd = null;
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryAlertCode == "")
			$scope.QueryAlertCode = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,

			Id : $scope.QueryId,
			Code : $scope.QueryCode,
			Name : $scope.QueryName,
			IsEnable : $scope.QueryIsEnable,
			StixXsd : $scope.QueryStixXsd,
			AlertCode: $scope.QueryAlertCode
		};

		$http.post('./api/s07/query', request, csrf_config).then(function(response) {
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
			document.getElementById("imgLoading").style.display = "none";
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
		
		$scope.Id = null;
		$scope.Code = "";
		$scope.Name = "";
		$scope.StixXsd = "";
		$scope.AlertCode = null;
		$scope.Sort = 0;
		$scope.IsEnable = true;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
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
		
		$http.post('./api/s07/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.Code = response.data[0].Code;
			$scope.Name = response.data[0].Name;
			$scope.StixXsd = response.data[0].StixXsd;
			$scope.AlertCode = response.data[0].AlertCode,
			$scope.Sort = response.data[0].Sort;
			$scope.IsEnable = response.data[0].IsEnable;
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
			Id : null,
			Code : $scope.Code,
			Name : $scope.Name,
			StixXsd : $scope.StixXsd,
			AlertCode : $scope.AlertCode,
			Sort : $scope.Sort,
			IsEnable : $scope.IsEnable
		};
		$http.post('./api/s07/create', request, csrf_config).then(
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

	// Update Data Start
	$scope.updateData = function() {
		var request = {
			Id : $scope.Id,
			Code : $scope.Code,
			Name : $scope.Name,
			StixXsd : $scope.StixXsd,
			AlertCode : $scope.AlertCode,
			Sort : $scope.Sort,
			IsEnable : $scope.IsEnable
		};
		$http.post('./api/s07/update', request, csrf_config).then(
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
						$http.post('./api/s07/delete', request, csrf_config).then(function(response) {
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
		
		// Get AlertType Start
		$scope.getAlertType = function() {
			var request = {};
			$http.post('./api/s07/query/alertType', request, csrf_config).then(function(response) {
				$scope.alertTypeCode = response.data;
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
		$scope.getAlertType();
		// Get AlertType End
}