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
	
	
	
	$scope.queryPrivacy = function() {

		var request = {
			
			Id : 1
		};
		$http.post('./fontend/index/copyright/query/id', request, csrf_config).then(function(response) {
			
			if (response.data.success) {
				privacy = response.data.data;
				
				$scope.title = privacy.Title;
				$scope.item1_1 = privacy.Item1_1;
				$scope.item1_2 = privacy.Item1_2;
				$scope.item1_3 = privacy.Item1_3;

				
				

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
	
	$scope.queryPrivacy();
	
	
	

	
	

	
	
	

	
	
	
	
}