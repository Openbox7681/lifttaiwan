var myApp = angular.module('myApp', [ ]).controller(
		'getAppController', getAppController);
angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

function getAppController($rootScope, $scope, $http) {
	$scope.SurveyPublic01 = 4;
	$scope.SurveyPublic01Suggest = "";
	$scope.SurveyPublic02 = 4;
	$scope.SurveyPublic02Suggest = "";
	$scope.SurveyPublic03 = 4;
	$scope.SurveyPublic03Suggest = "";
	$scope.SurveyNotify01 = 4;
	$scope.SurveyNotify01Suggest = "";
	$scope.SurveyNotify02 = 4;
	$scope.SurveyNotify02Suggest = "";
	$scope.SurveyNotify03 = 4;
	$scope.SurveyNotify03Suggest = "";
	$scope.SurveyAlert01 = 4;
	$scope.SurveyAlert01Suggest = "";
	$scope.SurveyAlert02 = 4;
	$scope.SurveyAlert02Suggest = "";
	$scope.SurveyAlert03 = 4;
	$scope.SurveyAlert03Suggest = "";
	$scope.SurveyCheck01 = 4;
	$scope.SurveyCheck01Suggest = "";
	$scope.SurveyCheck02 = 4;
	$scope.SurveyCheck02Suggest = "";
	$scope.SurveyCheck03 = 4;
	$scope.SurveyCheck03Suggest = "";
	$scope.SurveyTotal01 = 4;
	$scope.SurveyTotal01Suggest = "";
	$scope.SurveySuggest = "";
	
	$scope.isExist = function() {
		var request = { };
		$http.post('./api/survey/query/id', request, csrf_config).then(function(response) {
			if (response.data.success == true) {
				if (response.data.msg == true) {
					$("#divExist").show();
				} else {
					$("#divSurvey").show();
				}
			}
		}).catch(function() {
			$("#divSurvey").hide();
			$("#divExist").hide();
		});
	}
	$scope.isExist();
	
	$scope.updateData = function() {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
		});
		var request = {
			SurveyPublic01 : $scope.SurveyPublic01,
			SurveyPublic01Suggest : $scope.SurveyPublic01Suggest,
			SurveyPublic02 : $scope.SurveyPublic02,
			SurveyPublic02Suggest : $scope.SurveyPublic02Suggest,
			SurveyPublic03 : $scope.SurveyPublic03,
			SurveyPublic03Suggest : $scope.SurveyPublic03Suggest,
			SurveyNotify01 : $scope.SurveyNotify01,
			SurveyNotify01Suggest : $scope.SurveyNotify01Suggest,
			SurveyNotify02 : $scope.SurveyNotify02,
			SurveyNotify02Suggest : $scope.SurveyNotify02Suggest,
			SurveyNotify03 : $scope.SurveyNotify03,
			SurveyNotify03Suggest : $scope.SurveyNotify03Suggest,
			SurveyAlert01 : $scope.SurveyAlert01,
			SurveyAlert01Suggest : $scope.SurveyAlert01Suggest,
			SurveyAlert02 : $scope.SurveyAlert02,
			SurveyAlert02Suggest : $scope.SurveyAlert02Suggest,
			SurveyAlert03 : $scope.SurveyAlert03,
			SurveyAlert03Suggest : $scope.SurveyAlert03Suggest,
			SurveyCheck01 : $scope.SurveyCheck01,
			SurveyCheck01Suggest : $scope.SurveyCheck01Suggest,
			SurveyCheck02 : $scope.SurveyCheck02,
			SurveyCheck02Suggest : $scope.SurveyCheck02Suggest,
			SurveyCheck03 : $scope.SurveyCheck03,
			SurveyCheck03Suggest : $scope.SurveyCheck03Suggest,
			SurveyTotal01 : $scope.SurveyTotal01,
			SurveyTotal01Suggest : $scope.SurveyTotal01Suggest,
			SurveySuggest : $scope.SurveySuggest
		};
		$http.post('./api/survey/create', request, csrf_config).then(function(response) {
			if (response.data.success == true) {
				bootbox.hideAll();
				bootbox.alert({
					message : response.data.msg,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-primary',
						}
					},
					callback: function() {
						$("#divSurvey").hide();
						$("#divExist").show();
					}
				});
			} else {
				bootbox.hideAll();
				bootbox.alert({
					message : response.data.msg,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-danger'
						}
					},
					callback: function() {	}
				});
			}
		}).catch(function() {
			bootbox.hideAll();
			bootbox.alert({
				message : globalInsertDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() {	}
			});
		});
	}
}