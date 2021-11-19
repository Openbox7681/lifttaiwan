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
	var today = new Date();
	var yesterday = today.setDate(today.getDate() - 1)
	$('input[id="QuerySdate"]').datetimepicker({		
		 format: 'YYYY-MM-DD',
		 maxDate : yesterday
   });
	$("#QuerySdate").on("dp.change", function(e){	       
	     startDate = $('#QuerySdate').val();
	});
	
	$('input[id="QueryEdate"]').datetimepicker({		
		 format: 'YYYY-MM-DD',
		 maxDate : yesterday
  }); 
	$("#QueryEdate").on("dp.change", function(e){	       
	     endDate = $('#QueryEdate').val();
	});
	
	
	$scope.clearData = function() {
		 $scope.QuerySdate = null;
		 $scope.QueryEdate = null;
		 startDate=null;
		 endDate=null;
		 $('#QuerySdate').val(null);
		 $('#QueryEdate').val(null);
		 $('#QuerySdate').data("DateTimePicker").clear()
		 $('#QueryEdate').data("DateTimePicker").clear()
	};
	$scope.clearData();
	// Clear Query Data End

    
	

	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "createTime";
	$scope.sortreverse = true;
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}	
		if (startDate == "")
			startDate = null;
        if (endDate == "")
        		endDate = null;
		var request = {
				start : $scope.start,
				maxRows : $scope.maxRows,
				sort : $scope.sorttype,
				dir : $scope.sortreverse,
				QuerySdate : startDate,
				QueryEdate : endDate
			};				
		$http.post('./api/click_history/query', request, csrf_config).then(function(data) {
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
	
	$scope.viewTop10Detail = function(appName, createTime){		
		$scope.allTop10Detail = null
		var data = {
				AppName:appName,
				date:createTime
		};
		
		
		$http.post('./api/click_history/getTop10Detail', data, csrf_config).then(function(data) {
			 $scope.allTop10Detail = data.data.datatable
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
}