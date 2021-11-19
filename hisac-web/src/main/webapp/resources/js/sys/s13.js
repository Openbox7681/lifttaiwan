var myApp = angular.module('myApp', [ 'ngCookies' ]).controller(
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

function getAppController($rootScope, $scope, $http, $cookieStore) {
	$('input[id="Edate"]').datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
    }); 
	$('input[id="Sdate"]').datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
    });

	$("#Sdate").on("dp.change", function(e) {	       
		$scope.Sdate = $('#Sdate').val();
		if ($scope.Sdate > $scope.Edate && $scope.Edate != null) {
			$scope.Edate = $scope.Sdate;
			$('#Edate').val($scope.Edate)
		}
	});
	$("#Edate").on("dp.change", function(e) {	       
		$scope.Edate = $('#Edate').val();
		if ($scope.Edate < $scope.Sdate && $scope.Sdate != null) {
			$scope.Sdate = $scope.Edate;
			$('#Sdate').val($scope.Sdate)
		}
	});

	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.Sdate = null;
		$scope.Edate = null;
	};
	$scope.clearData();
	// Clear Query Data End
	
	$scope.queryData = function(page) {
		if ($scope.Sdate == "")
			$scope.Sdate = null;
		if ($scope.Edate == "")
			$scope.Edate = null;
		var data = {
			Sdate : $scope.Sdate,
			Edate : $scope.Edate
		};
		$http.post('./api/s13/query', data, csrf_config).then(function(data) {
			var items = data.data.datatable;
				for (var key in items) {
			        if (items[key].FuncName == "p01") {
			        	$scope.p01View = items[key].Count;
			        } else if (items[key].FuncName == "p02") {
			        	$scope.p02View = items[key].Count;
			        } else if (items[key].FuncName == "p03") {
			        	$scope.p03View = items[key].Count;
			        } else if (items[key].FuncName == "p04") {
			        	$scope.p04View = items[key].Count;
			        } else if (items[key].FuncName == "p06") {
			        	$scope.p06View = items[key].Count;
			        } else if (items[key].FuncName == "p07") {
			        	$scope.p07View = items[key].Count;
			        } else if (items[key].FuncName == "p08") {
			        	$scope.p08View = items[key].Count;
			        } else if (items[key].FuncName == "p09") {
			        	$scope.p09View = items[key].Count;
			        } else if (items[key].FuncName == "p01Post") {
			        	$scope.p01Post = items[key].Count;
			        } else if (items[key].FuncName == "p02Post") {
			        	$scope.p02Post = items[key].Count;
			        } else if (items[key].FuncName == "p03Post") {
			        	$scope.p03Post = items[key].Count;
			        } else if (items[key].FuncName == "p04Post") {
			        	$scope.p04Post = items[key].Count;
			        } else if (items[key].FuncName == "p06Post") {
			        	$scope.p06Post = items[key].Count;
			        } else if (items[key].FuncName == "p07Post") {
			        	$scope.p07Post = items[key].Count;
			        } else if (items[key].FuncName == "p08Post") {
			        	$scope.p08Post = items[key].Count;
			        } else if (items[key].FuncName == "p09Post") {
			        	$scope.p09Post = items[key].Count;
			        }
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
		});
	};
	$scope.queryData();	

	$scope.sendQueryData = function() {
		queryData();
	}
}