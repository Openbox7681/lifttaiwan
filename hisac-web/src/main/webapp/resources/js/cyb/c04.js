var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {
	
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
	
}