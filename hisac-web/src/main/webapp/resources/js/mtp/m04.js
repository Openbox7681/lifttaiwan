var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload']).controller(
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

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {
	$scope.importFileCheckList = function() {
		Upload
		.upload({
			url : './api/m04/createCheckList',
			data : {				
				file : $scope.fileCheckList				
			},
			headers: header
		})
		.then(
				function(response) {
							if (response.data.success) {											 																			
								bootbox.alert({
									message : '上傳成功',
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
											className : 'btn-success',
										}
									},
									callback: function() {																			
									}
								});
							} else {
								bootbox.alert({
									message : '上傳失敗',
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
								message : "<span class='bigger-110'>上傳失敗</span>",
								buttons : {
									"success" : {
										"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
										 "className": 'btn-danger',
										"callback" : function() {
										}
									}
								}
							});
						}).finally(function() { 														
						});								
	};
	
	
	$scope.importFileQuestionnaire = function() {
		Upload
		.upload({
			url : './api/m04/createQuestionnaire',
			data : {				
				file : $scope.fileQuestionnaire				
			},
			headers: header
		})
		.then(
				function(response) {
							if (response.data.success) {											 																			
								bootbox.alert({
									message : '上傳成功',
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
											className : 'btn-success',
										}
									},
									callback: function() {																			
									}
								});
							} else {
								bootbox.alert({
									message : '上傳失敗',
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
								message : "<span class='bigger-110'>上傳失敗</span>",
								buttons : {
									"success" : {
										"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
										 "className": 'btn-danger',
										"callback" : function() {
										}
									}
								}
							});
						}).finally(function() { 														
						});								
	};
}