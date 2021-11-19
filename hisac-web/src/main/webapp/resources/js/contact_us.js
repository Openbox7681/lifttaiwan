var myApp = angular.module('myApp', []).controller('getAppController',
		getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

function getAppController($scope, $http, $window, $timeout) {

}

$(document).ready(function() {
	$("#btnLogin").hide();
	$("#btnIndex").show();
	$("#btnLogin2").hide();
	$("#btnIndex2").show();
});
function toggleLogin() {
	location.href = "./";
}