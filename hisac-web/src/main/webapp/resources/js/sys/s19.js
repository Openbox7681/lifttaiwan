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
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = true;
	$scope.btnIns = false;
	$scope.btnUpd = false;
	
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
		$scope.QueryName = null;	
		$scope.QueryEmail = null;	
		$scope.QueryMobilePhone = null;	
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
		if ($scope.QueryName == "")
			$scope.QueryName = null;		
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Name : $scope.QueryName,
			Email : $scope.QueryEmail,		
			MobilePhone : $scope.QueryMobilePhone			
		};

		$http.post('./api/s19/query', request, csrf_config).then(function(response) {
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

	$scope.edit = function(id) {

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});

		$scope.Id = null;
		$scope.Name = null;
		$scope.Email = null;
		$scope.MobilePhone = null;		
		
	
		$scope.editData(id);

		$scope.btnIns = false;
		$scope.btnUpd = true;
		$("#divQuery").hide();
		
	}
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = null;
		$scope.Name = null;
		$scope.Email = null;
		$scope.MobilePhone = null;		
				
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divQuery").show();
		$("#divEdit").hide();	
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {

	
		var request = {
			Id : id		
		};
		
		$http.post('./api/s19/query/id', request, csrf_config).then(function(response) {
			
			$scope.openEdit();
			
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data.Id;			
			$scope.Name = response.data.Name;		
			$scope.Email =  response.data.Email;		
			$scope.MobilePhone =  response.data.MobilePhone;				

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
	$scope.createData = function(exit) {
		
		if (!$scope.editForm.$valid) {
			
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
			
			var request = {
					Name : $scope.Name,				
					Email : $scope.Email,
					MobilePhone : $scope.MobilePhone				
			};
		

			$http.post('./api/s19/create', request, csrf_config).then(function(response) {			
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

	// Update Data Start
	// 暫存：updateData(false, false)
	// 暫存並離開：updateData(true, false)
	// 送出：updateData(true, true)
	$scope.updateData = function(exit) {		

		if (!$scope.editForm.$valid) {
			
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
			
			var request = {
				Id : $scope.Id,
				Name : $scope.Name,				
				Email : $scope.Email,
				MobilePhone : $scope.MobilePhone		
				
			};

			$http.post('./api/s19/update', request, csrf_config).then(function(response) {
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
								// 暫存並離開按鍵用 - 開始
								if ($scope.pageRows == 1 && $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}
								
								$scope.queryData($scope.currentPage);
								if (exit) {									
									$scope.closeEdit();
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
					$http.post('./api/s19/delete', request, csrf_config).then(function(response) {
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
	
	
}