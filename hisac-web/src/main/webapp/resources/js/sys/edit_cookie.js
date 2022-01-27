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
	
	$scope.queryPrivacy = function() {

		var request = {
			
			Id : 1
		};
		$http.post('./api/cookiePage/query/id', request, csrf_config).then(function(response) {
			
			if (response.data.success) {
				privacy = response.data.data;
				
				$scope.title = privacy.Title;
				
				
				

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
				

				
			};
		
		$http.post('./api/cookiePage/createOrUpdate', request, csrf_config).then(function(response) {
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