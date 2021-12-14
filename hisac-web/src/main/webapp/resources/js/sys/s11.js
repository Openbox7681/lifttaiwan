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
	
	
	
	
	$scope.getsubsystem = function() {
		var data = {};
		$http.post('./api/s11/getsubsystem', data, csrf_config).then(function(data) {
			$scope.subsystems = data.data;
		}).catch(function() {
			bootbox.dialog({
				message : globalReadDataFail, //"<span class='bigger-110'>資料讀取失敗</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-close'></i> " + btnClose,
						 "className": 'btn-danger',
						"callback" : function() {
						}
					}
				}
			});
		}).finally(function() { });
	};
	$scope.getsubsystem();
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 5;
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
		$scope.QuerySubsystemId = null;
		$scope.QueryName = null;
		$scope.QueryIsExternalLink = null;
		$scope.QueryIsEnable=null;
		$scope.QueryIsShow=null;
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
		if ($scope.QuerySubsystemId == "")
			$scope.QuerySubsystemId = null;
		if ($scope.QueryName == "")
			$scope.QueryName = null;	
		if ($scope.QueryIsExternalLink == "")
			$scope.QueryIsExternalLink = null;		
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryIsShow == "")
			$scope.QueryIsShow = null;
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			SubsystemId : $scope.QuerySubsystemId,
			Name : $scope.QueryName,
			IsExternalLink : $scope.QueryIsExternalLink,
			IsEnable : $scope.QueryIsEnable,
			IsShow : $scope.QueryIsShow,
			Code : $scope.Code,
			ControllerName : $scope.ControllerName,
			ActionName : $scope.ActionName,
			Sort : $scope.Sort
		};

		$http.post('./api/s11/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			//console.log("_________"+JSON.stringify($scope.allitems))
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
		$scope.Id = "";
	   	$scope.Name = "";
	   	$scope.Code = "";
	   	$scope.ControllerName = "";
	   	$scope.ActionName = "";
	    $scope.Sort = 0;	   	
	   	$scope.IsExternalLink = false;
	   	$scope.IsEnable = true;
	   	$scope.IsShow = true;
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
		
		var request = {
			Id : id
		};

		$http.post('./api/s11/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.SubsystemId = response.data[0].SubsystemId;
			$scope.SubsystemName = response.data[0].SubsystemName;
			$scope.Code = response.data[0].Code;
			$scope.Name = response.data[0].Name;
			$scope.ControllerName = response.data[0].ControllerName;
			$scope.ActionName = response.data[0].ActionName;
			$scope.Sort = response.data[0].Sort;
			$scope.IsExternalLink = response.data[0].IsExternalLink;
			$scope.IsEnable = response.data[0].IsEnable;
			$scope.IsShow = response.data[0].IsShow;
		}).catch(function() {
			
		}).finally(function() { });
	}
	// Switch to Edit(Update) Mode End

	// Insert Item Start
	$scope.createData = function() {
		var request = {
			Id : $scope.Id,
			SubsystemId : $scope.SubsystemId,
			SubsystemName : $scope.SubsystemName,
			Code : $scope.Code,
			Name : $scope.Name,
			ControllerName : $scope.ControllerName,
			ActionName : $scope.ActionName,
			Sort : $scope.Sort,
			IsExternalLink : $scope.IsExternalLink,
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow
		};
		$http.post('./api/s11/create', request, csrf_config).then(
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
			SubsystemId : $scope.SubsystemId,
			SubsystemName : $scope.SubsystemName,
			Code : $scope.Code,
			Name : $scope.Name,
			ControllerName : $scope.ControllerName,
			ActionName : $scope.ActionName,
			Sort : $scope.Sort,
			IsExternalLink : $scope.IsExternalLink,
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow
		};
		$http.post('./api/s11/update', request, csrf_config).then(
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
						$http.post('./api/s11/delete', request, csrf_config).then(function(response) {
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