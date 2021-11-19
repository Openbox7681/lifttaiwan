var myApp = angular.module('myApp', ['ngSanitize']).controller('getAppController',
		getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $http, $window) {
	$scope.queryContent = function() {
		var request = {
			start : 0,
			maxRows : 5,
			sort : "postDateTime",
			dir : true,
			IsEnable : true
		};
		$http.post('./api/p09/query/id', request, csrf_config).then(function(response) {
			$scope.id = response.data[0].Id;
			$scope.title = response.data[0].Title;
			$scope.detailContent = response.data[0].Content;
			
			// 附件
			$scope.queryDetailAttachData($scope.id);
		}).finally(function() {
		})
	}

	// Query Detail Attach Data Start
	$scope.queryDetailAttachData = function(id) {

		$scope.tabAttachmentLoad = true;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
				CommonPostId : id
		};
		$http.post('./api/p09/attach/query', request, csrf_config).then(function(response) {			
			$scope.itemAttachments = response.data.datatable;
			$scope.tabAttachmentLoad = false;
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
					location.href="p07"
				}
			});
		}).finally(function() { });
	}
	// Query Detail Attach Data End
	
	$scope.queryContent();
};