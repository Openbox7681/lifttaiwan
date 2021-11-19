var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {
	$scope.outList = false;
	$scope.inList = false;
	
	$scope.clearData = function() {
		$scope.QueryIP = null;
		$scope.outList = false;
		$scope.inList = false;
	}
	$scope.clearData();

	
	
	$scope.queryData = function() {
		$scope.outList = false;
		$scope.inList = false;
		$("#imgLoading").show();
		var request = {				
				IP : $scope.QueryIP
				
			};
				$http.post('./api/c04/query', request, csrf_config).then(function(response) {	
					if (response.data.IsinCC){
						$scope.outList = false;
						$scope.inList = true;
					}
					else{
						$scope.outList = true;
						$scope.inList = false;
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
			});
		};
	
}