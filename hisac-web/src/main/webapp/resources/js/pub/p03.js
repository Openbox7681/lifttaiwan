var myApp = angular.module('myApp', [ 'bw.paging', 'ngSanitize' ]).controller('getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $rootScope, $http, $window) {
	var id = window.location.search.substring(1);
	$scope.id = 0;
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = true;
	$scope.isBreakLine = false;

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
	
	$scope.clearData = function() {
		$scope.id = 0;
		$rootScope.detailTitle = "";
		$scope.detailQName = "";
		$scope.detailAName = "";
		$("#divQuery").show();
		$("#divDetail").hide();
	}

	// Query Data Start
	$scope.queryData = function(page) {
		$("#divQuery").show();
		$("#divDetail").hide();
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		var request = {
				start : $scope.start,
				maxRows : $scope.maxRows,
				sort : $scope.sorttype,
				dir : $scope.sortreverse,
				Keyword : $scope.QueryKeyword
			};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/p03/query', request, csrf_config).then(function(response) {
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
	}
	// Query Data End

	// Query Detail Data Start
	$scope.queryDetailData = function(id) {
		$("#divQuery").hide();
		$("#divDetail").hide();
		$scope.id = id;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};

		$http.post('./api/p03/query/id', request, csrf_config).then(function(response) {
			$rootScope.detailTitle = response.data[0].Title;
			$scope.detailQName = response.data[0].QName;
			$scope.detailAName = response.data[0].AName;
			switch (response.data[0].IsBreakLine) {
				case true:
					$scope.isBreakLine = true;
					break;
				default:
					$scope.isBreakLine = false;
					break;
			}
			$scope.queryDetailAttachData(id);
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
				callback: function() {
					location.href="p03"
				}
			});
		}).finally(function() {
			$("#divDetail").show();
		});
	}
	// Query Detail Data End

	// Query Detail Attach Data Start
	$scope.queryDetailAttachData = function(id) {					
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
				QAManagementId : id
		};
		$http.post('./api/p03/attach/query', request, csrf_config).then(function(response) {			
			$scope.itemAttachments = response.data.datatable;			
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
				callback: function() {					
				}
			});
		}).finally(function() { });
	}
	// Query Detail Attach Data End
	

	if ($.isNumeric(id)) {
		$scope.id = parseInt(id);
		$scope.queryDetailData($scope.id);
	} else {
		$scope.clearData();
		$scope.queryData();
	}
};