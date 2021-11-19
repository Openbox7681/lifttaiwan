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

function getAppController($rootScope, $scope, $http, $cookieStore) {

	$scope.newsReportEdit = 0;
	$scope.newsReportDelete = 0;
	$scope.newsReportSign = 0;
	$scope.newsReportApply1 = 0;
	$scope.newsReportApply0 = 0;
	$scope.newsReportTotal = 0;

	$scope.activityReportEdit = 0;
	$scope.activityReportDelete = 0;
	$scope.activityReportSign = 0;
	$scope.activityReportApply1 = 0;
	$scope.activityReportApply0 = 0;
	$scope.activityReportTotal = 0;
	
	$scope.anaReportEdit = 0;
	$scope.anaReportDelete = 0;
	$scope.anaReportSign = 0;
	$scope.anaReportApply1 = 0;
	$scope.anaReportApply0 = 0;
	$scope.anaReportTotal = 0;
	
	$scope.informationExchanges = [];
	
	$('input[id="Edate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="Sdate"]').datetimepicker({
		format: 'YYYY-MM-DD',
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
		$http.post('./api/s16/query', data, csrf_config).then(function(response) {
			var newsReport = response.data.news;
			var activityReport = response.data.activity;
			var anaReport = response.data.ana;
			$scope.informationExchanges = response.data.information_exchange;
			$scope.informationExchangeTotal = response.data.information_exchange_total[0];
			
			$scope.newsReportTotal = 0;
			for (var i = 0; i < newsReport.length; i++) {
			  if (newsReport[i].Name == "編輯中"){
				  $scope.newsReportEdit = newsReport[i].Count;
				  $scope.newsReportTotal += $scope.newsReportEdit;
			  } else if (newsReport[i].Name == "審核中"){
				  $scope.newsReportSign = newsReport[i].Count;
				  $scope.newsReportTotal += $scope.newsReportSign;
			  } else if (newsReport[i].Name == "已撤銷"){
				  $scope.newsReportDelete = newsReport[i].Count;
				  $scope.newsReportTotal += $scope.newsReportDelete;
			  } else if (newsReport[i].Name == "已公告,啟用"){
				  $scope.newsReportApply1 = newsReport[i].Count;
				  $scope.newsReportTotal += $scope.newsReportApply1;
			  } else if (newsReport[i].Name == "已公告,不啟用"){
				  $scope.newsReportApply0 = newsReport[i].Count;
				  $scope.newsReportTotal += $scope.newsReportApply0;
			  }
			}
			
			$scope.activityReportTotal = 0;
			for (var i = 0; i < activityReport.length; i++) {
			  if (activityReport[i].Name == "編輯中"){
				  $scope.activityReportEdit = activityReport[i].Count;
				  $scope.activityReportTotal += $scope.activityReportEdit;
			  } else if (activityReport[i].Name == "審核中"){
				  $scope.activityReportSign = activityReport[i].Count;
				  $scope.activityReportTotal += $scope.activityReportSign;
			  } else if (activityReport[i].Name == "已撤銷"){
				  $scope.activityReportDelete = activityReport[i].Count;
				  $scope.activityReportTotal += $scope.activityReportDelete;
			  } else if (activityReport[i].Name == "已公告,啟用"){
				  $scope.activityReportApply1 = activityReport[i].Count;
				  $scope.activityReportTotal += $scope.activityReportApply1;
			  } else if (activityReport[i].Name == "已公告,不啟用"){
				  $scope.activityReportApply0 = activityReport[i].Count;
				  $scope.activityReportTotal += $scope.activityReportApply0;
			  }
			}
			
			$scope.anaReportTotal = 0;
			for (var i = 0; i < anaReport.length; i++) {
			  if (anaReport[i].Name == "編輯中"){
				  $scope.anaReportEdit = anaReport[i].Count;
				  $scope.anaReportTotal += $scope.anaReportEdit;
			  } else if (anaReport[i].Name == "審核中"){
				  $scope.anaReportSign = anaReport[i].Count;
				  $scope.anaReportTotal += $scope.anaReportSign;
			  } else if (anaReport[i].Name == "已撤銷"){
				  $scope.anaReportDelete = anaReport[i].Count;
				  $scope.anaReportTotal += $scope.anaReportDelete;
			  } else if (anaReport[i].Name == "已公告,啟用"){
				  $scope.anaReportApply1 = anaReport[i].Count;
				  $scope.anaReportTotal += $scope.anaReportApply1;
			  } else if (anaReport[i].Name == "已公告,不啟用"){
				  $scope.anaReportApply0 = anaReport[i].Count;
				  $scope.anaReportTotal += $scope.anaReportApply0;
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

	$scope.sendQueryData = function() {
		queryData();
	}
}