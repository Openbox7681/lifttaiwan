var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
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

function getAppController($rootScope, $scope, $http, $cookieStore) {
	$('input[id="Edate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="Sdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });

		$("#Sdate").on("dp.change", function(e) {	       
			$scope.Sdate = $('#Sdate').val();
			if ($scope.Sdate > $scope.Edate && $scope.Edate != null) {
				$scope.Edate = $scope.Sdate;
				$('#Edate').val($scope.Edate)
			}
		});
		$("#Edate").on("dp.change", function(e) {	       
			$scope.Edate = $('#Edate').val();
			if ($scope.Edate < $scope.Sdate && $scope.Sdate != null) {
				$scope.Sdate = $scope.Edate;
				$('#Sdate').val($scope.Sdate)
			}
		});

    
	

	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 100;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = true;

	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.Id = null;
		$scope.QueryReportCode = null;
		$scope.Sdate = null;
		$scope.Edate = null;
		
        
		//$scope.btnIns = false;
		//$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.Id == "")
			$scope.Id = null;
		
		if ($scope.Sdate == "")
			$scope.Sdate = null;
		if ($scope.Edate == "")
			$scope.Edate = null;
		if ($scope.QueryReportCode == "")
			$scope.QueryReportCode = null;
		
		var data = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.Id,
			Sdate : $scope.Sdate,
			Edate : $scope.Edate,
			ReportCode : $scope.QueryReportCode
			
		};
		$http.post('./api/s44/ReportSchedule/query', data, csrf_config).then(function(data) {
			$scope.allitems = data.data.datatable;
			$scope.total = data.data.total;
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
	
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		
	    $scope.Sort = 0;	   	
	    $scope.ReportCode = "";
	    $scope.ReportScheduleTime=null;
	    
	    $('input[id="ReportScheduleTime"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    });
	    
	    $("#ReportScheduleTime").on("dp.change", function(e) {	       
			$scope.ReportScheduleTime = $('#ReportScheduleTime').val();
		});
	    
		$scope.ReportScheduleTime = moment().format("YYYY-MM-DD");	


		
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
	
	
	// Insert Item Start
	$scope.createData = function() {
		var request = {
			ReportScheduleTime: $scope.ReportScheduleTime,
			ReportCode : parseInt($scope.ReportCode)
			
		};
		$http.post('./api/s44/Report/execute', request, csrf_config).then(
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
	
	
	// Delete Item Start
	$scope.deleteData = function(id, reportCode, reportScheduleTime) {
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
						Id: id,
						ReportCode : parseInt(reportCode),
						ReportScheduleTime: reportScheduleTime
					};
					$http.post('./api/s44/delete', request, csrf_config).then(function(response) {
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
	
	
//	$scope.createData= function(reportCode, scheduleTime){
//		var request = {
//				ReportScheduleTime: scheduleTime,
//				ReportCode : parseInt(reportCode)
//				
//			};
//		
//		$http.post('./api/s44/Report/execute', request, csrf_config).then(
//				function(response) {
//					if (response.data.success) {
//						$scope.queryData($scope.currentPage);
//						bootbox.alert({
//							message : response.data.msg,
//							buttons : {
//								ok : {
//									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//									className : 'btn-success',
//								}
//							},
//							callback: function() {
//								$scope.closeEdit();
//							}
//						});
//					} else {
//						bootbox.alert({
//							message : response.data.msg,
//							buttons : {
//								ok : {
//									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//									className : 'btn-danger',
//								}
//							},
//							callback: function() { }
//						});
//					}
//				}).catch(function() {
//					bootbox.alert({
//						message : globalInsertDataFail,
//						buttons : {
//							ok : {
//								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//								className : 'btn-danger',
//							}
//						},
//						callback: function() { }
//					});
//				}).finally(function() { });
//		
//		
//		
//		
//	}
	
	
	
	
	
}