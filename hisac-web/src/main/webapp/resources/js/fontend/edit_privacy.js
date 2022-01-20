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
		$http.post('./fontend/index/privacyPage/query/id', request, csrf_config).then(function(response) {
			
			if (response.data.success) {
				privacy = response.data.data;
				
				$scope.title = privacy.Title;
				$scope.item1 = privacy.Item1;
				$scope.item2_1 = privacy.Item2_1;
				$scope.item2_2 = privacy.Item2_2;
				$scope.item2_3 = privacy.Item2_3;
				$scope.item2_4 = privacy.Item2_4;
				$scope.item2_5 = privacy.Item2_5;
				
				$scope.item3_1 = privacy.Item3_1;
				$scope.item3_2 = privacy.Item3_2;
				
				$scope.item4_1 = privacy.Item4_1;
				$scope.item4_2 = privacy.Item4_2;
				$scope.item4_3 = privacy.Item4_3;
				$scope.item4_4 = privacy.Item4_4;

				$scope.item5_1 = privacy.Item5_1;
				
				

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
	
	

	
	$scope.createOrUpdateData = function() {
		
		var request = {
				Id : 1,
				Title : $scope.title,
				Item1 : $scope.item1 ,
				Item2_1 : $scope.item2_1 ,
				Item2_2 : $scope.item2_2 ,
				Item2_3 : $scope.item2_3 ,
				Item2_4 : $scope.item2_4 ,
				Item2_5 : $scope.item2_5 ,
				Item3_1 : $scope.item3_1 ,
				Item3_2 : $scope.item3_2 ,
				Item4_1 : $scope.item4_1 ,
				Item4_2 : $scope.item4_2 ,
				Item4_3 : $scope.item4_3 ,
				Item4_4 : $scope.item4_4 ,
				Item5_1 : $scope.item5_1 


				
			};
		
		$http.post('./fontend/index/privacyPage/createOrUpdate', request, csrf_config).then(function(response) {
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
						$scope.queryPrivacy();

					}
				});

				
			}else {
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

	
	
	

	
	
	
	
}