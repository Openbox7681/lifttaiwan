var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {	
	
	const STATUS = {
			WATTING: 'waiting',
			SUCCESS: 'success'
		};
		$scope.log = [];		
		$scope.result = [];
		$scope.timer = null;
	
	$scope.b64DecodeUnicode = function (str) {
		return decodeURIComponent(Array.prototype.map.call(
			atob(str),
			function (c) {
				return '%'
					+ ('00' + c.charCodeAt(0).toString(16)).slice(-2)
			}).join(''))
	}
	
	const resultMap = {};
	
	$http.post('./api/c05d/detail/', {Id: location.search.substring(1)}, csrf_config).then(function(response) {		
		const result = response.data;
		const error = result.error;
		if (error != null)
			return;

		$scope.result = Object.keys(result).map(function (hash) {
			const r = {
				hash: hash,
				fileName: result[hash],
				status: STATUS.WATTING
			};
			resultMap[hash] = r;
			return r;
		});
		
		listenScanResult();
	});
	
	
	function listenScanResult() {
		if ($scope.timer)
			clearInterval($scope.timer);
		$scope.timer = setInterval(getScanResult, 1000);
	}
	
	function getScanResult() {

		let stop = true;
		const reqs = $scope.result.filter(function (r) {
			return r.status == STATUS.WATTING;
		}).map(function (r) {
			return $http.post('./api/c05d/result/', {
				hash: r.hash
			}, csrf_config);
		});

		Promise.all(reqs).then(function (reponse) {
			reponse.forEach(function (r) {
				const data = JSON.parse($scope.b64DecodeUnicode(r.data.data));
				const hash = data['Search Target'];
				if (hash.indexOf('Not in db') != -1) {
					stop = false;
					return;
				}

				resultMap[hash].status = STATUS.SUCCESS;
				resultMap[hash].res = data;

				resultMap[hash].count = 0;

			});
			$scope.$apply();
			if (stop)
				clearInterval($scope.timer);
		});

	}
	
}