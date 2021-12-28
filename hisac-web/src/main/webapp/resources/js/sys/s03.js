var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
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
    
	

	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 100;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = true;

	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.Id = null;
		$scope.InputValue = null;
		$scope.Ip = null;
		$scope.FuncName = null;
		$scope.ActionName = null;
		$scope.Sdate = null;
		$scope.Edate = null;
		$scope.AppName = null;
		$scope.Status = null;
		$scope.CreateAccount = null;
		$scope.HashCode = null;
        
		//$scope.btnIns = false;
		//$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.Id == "")
			$scope.Id = null;
		if ($scope.InputValue == "")
			$scope.InputValue = null;
		if ($scope.Ip == "")
			$scope.Ip = null;
		if ($scope.FuncName == "")
			$scope.FuncName = null;
		if ($scope.ActionName == "")
			$scope.ActionName = null;
		if ($scope.Sdate == "")
			$scope.Sdate = null;
		if ($scope.Edate == "")
			$scope.Edate = null;
		if ($scope.AppName == "")
			$scope.AppName = null;
		if ($scope.Status == "")
			$scope.Status = null;
		if ($scope.CreateAccount == "")
			$scope.CreateAccount = null;
		if ($scope.HashCode == "")
			$scope.HashCode = null;
		var data = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.Id,
			AppName :  $scope.AppName,
			FuncName : $scope.FuncName,
			InputValue : $scope.InputValue,
			ActionName : $scope.ActionName,
			Status : $scope.Status,
			Ip : $scope.Ip,
			HashCode :  $scope.HashCode,
			CreateAccount : $scope.CreateAccount,
			Sdate : $scope.Sdate,
			Edate : $scope.Edate
			
		};
		$http.post('./api/s03/query', data, csrf_config).then(function(data) {
			$scope.allitems = data.data.datatable;
			$scope.total = data.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
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
	$scope.queryData();	

	$scope.sendQueryData = function() {
		if (!$scope.myForm.$valid) {
			bootbox.dialog({
				message : globalColumnFail, //"<span class='bigger-110'>請修正不符合規定的欄位</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i> OK",
						"callback" : function() {
						}
					}
				}
			});
		} else {
			$scope.currentPage = 1;
			$scope.start = 0;
			$scope.queryData();
		}
	}

	$scope.setSortName = function(sorttype) {
		$scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse
				: false;
		$scope.sorttype = sorttype;
		$scope.currentPage = 1;
		$scope.start = 0;
		$scope.queryData();
	};

	$scope.maxRowsChange = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryData();
	};
}