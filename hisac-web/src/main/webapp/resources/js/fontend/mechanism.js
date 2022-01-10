var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $http, $window) {

	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 5;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = false;

	$scope.maxRowsChange = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryData();
	};
	
	$scope.maxRowsChangeInit = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryDataInit();
	};
	// Paging End
	
	// Query Data Start
	$scope.queryData = function(page) {
		
		$("#init").hide();
		$("#query").show();
		
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		var country = $scope.country;
		
		if(country == '全部') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			country : country
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};

		$http.post('./query', request, csrf_config).then(function(response) {
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
	// Query Data End
	
	// Query Data Start
	$scope.queryDataInit = function(page) {
		$("#imgLoadingInit").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		var url = location.href;
		var ary = url.split("/");
		var country = ary[ary.length-1];
		country = unescape(atob(country));
		
		if(country == '過去10年中的國際優秀人才和研究成果') {
			country == '全部';
		}
		
		if(country == '全部') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			country : country
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};

		$http.post('./query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
			
			country = "#" + country
					
			$(country).attr("selected", "true");
		
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
			$("#imgLoadingInit").hide();
		});
	};
	$scope.queryDataInit();
	// Query Data End
	
	
	$scope.clearData = function() {
		$scope.country = null;
	};
	$scope.clearData();
}