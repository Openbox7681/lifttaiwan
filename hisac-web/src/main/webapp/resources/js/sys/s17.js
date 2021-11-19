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
	$scope.query = false;
	var today = new Date();
	var yesterday = today.setDate(today.getDate() - 1)
	$('input[id="QuerySdate"]').datetimepicker({		
		 format: 'YYYY-MM-DD',
		 maxDate : yesterday
   });
	$("#QuerySdate").on("dp.change", function(e){	       
	     startDate = $('#QuerySdate').val();
	});
	
	$('input[id="QueryEdate"]').datetimepicker({		
		 format: 'YYYY-MM-DD',
		 maxDate : yesterday
  }); 
	$("#QueryEdate").on("dp.change", function(e){	       
	     endDate = $('#QueryEdate').val();
	});
	
	
	$scope.clearData = function() {
		 $scope.QuerySdate = null;
		 $scope.QueryEdate = null;
		 startDate=null;
		 endDate=null;
		 $('#QuerySdate').val(null);
		 $('#QueryEdate').val(null);
		 $('#QuerySdate').data("DateTimePicker").clear()
		 $('#QueryEdate').data("DateTimePicker").clear()
	};
	$scope.clearData();
	// Clear Query Data End
	
	$scope.getsource = function() {
		var data = {};
		$http.post('./api/s17/getsource', data, csrf_config).then(function(response) {
			$scope.SourceCodes = response.data;
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
		}).finally(function() {});
	};
	$scope.getsource();
	
	$scope.geteventType = function() {
		var data = {};
		$http.post('./api/s17/geteventType', data, csrf_config).then(function(response) {
			$scope.EventTypes = response.data;
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
		}).finally(function() {});
	};
	$scope.geteventType();
	
	$scope.queryData = function() {	
		$scope.query = true;
		if (startDate == "")
			startDate = null;
        if (endDate == "")
        		endDate = null;
		var request = {
				QuerySdate : startDate,
				QueryEdate : endDate
			};
		$http.post('./api/s17/memberReport', request, csrf_config).then(function(data) {
			//會員統計
			$scope.MemberCount = data.data.MemberCount
			$scope.CIMemberCount = data.data.CIMemberCount
			$scope.NonCIAdvancedCount = data.data.NonCIAdvancedCount
			$scope.NonCIBasedMemberCount = $scope.MemberCount - $scope.CIMemberCount - $scope.NonCIAdvancedCount	
			$scope.ManagerCount = data.data.ManagerCount			
			$scope.ManagerWait = data.data.ManagerWait
			$scope.ManagerExamine = data.data.ManagerExamine
			$scope.ManagerDisable = data.data.ManagerDisable
			$scope.ManagerUse = $scope.ManagerCount - $scope.ManagerWait - $scope.ManagerExamine - $scope.ManagerDisable
			$scope.ContactCount = data.data.ContactCount			
			$scope.ContactWait = data.data.ContactWait		
			$scope.ContactExamine = data.data.ContactExamine		
			$scope.ContactDisable = data.data.ContactDisable
			$scope.ContactUse = $scope.ContactCount - $scope.ContactWait - $scope.ContactExamine - $scope.ContactDisable
			//會員點閱資安資訊情報
			$scope.NewsCount = data.data.NewsCount
			$scope.ActivityCount = data.data.ActivityCount
			$scope.AnaCount = data.data.AnaCount
			$scope.SecBuzzerCount = data.data.SecBuzzerCount
			$scope.AllClickCount = $scope.NewsCount + $scope.ActivityCount + $scope.AnaCount + $scope.SecBuzzerCount			
			//最新消息 ─點擊數前十高訊息
			$scope.newsTop5 = data.data.newsTop5
			//活動訊息 ─點擊數前十高訊息
			$scope.activityTop5 = data.data.activityTop5
			//資安訊息情報─點擊數前十高訊息
			$scope.anaTop5 = data.data.anaTop5
			//醫療設備資安情報 ─點擊數前十高訊息
			$scope.secTop5 = data.data.secTop5
			//登入次數統計-登入次數前十名
			$scope.signTop10 = data.data.signTop10
			//會員機構瀏覽公開資訊統計表
			$scope.orgReport = data.data.orgReport
			//合計會員機構瀏覽公開資訊統計表
			$scope.sumOrgReport = data.data.sumOrgReport
			//審核中單位列表
			$scope.orgExamineReport = data.data.orgExamineReport
			//合計審核中單位列表
			$scope.sumOrgExamineReport = data.data.sumOrgExamineReport
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
			$scope.query = false;
		});
	};
//	$scope.queryData();	
	
	$scope.getSourceName = function(code){
        var Name = "";
	    angular.forEach($scope.SourceCodes, function(value, key) {
	    	if(value.Code==code) {
	    		Name=value.Name;
   	        }
	    });
	    return Name;
     }
	
	$scope.geEventTypeName = function(code){
        var Name = "";
	    angular.forEach($scope.EventTypes, function(value, key) {
	    	if(value.Code==code) {
	    		Name=value.Name;
   	        }
	    });
	    return code + "-" + Name;
     }
	
	$scope.viewTop5Detail = function(name, id){		
		$scope.allTop5Detail = null
		var data = {};
		switch (name){
			case "news":
			data = {
					AppName:'p01_News',
					FuncName:'QueryById',
					InputValue:'{"Id":' + id + '}',
					QuerySdate : startDate,
					QueryEdate : endDate
			};
			break;
			case "activity":
			data = {
					AppName:'p02_Activity',
					FuncName:'QueryById',
					InputValue:'{"Id":' + id + '}',
					QuerySdate : startDate,
					QueryEdate : endDate
			};
			break;
			case "ana":
			data = {
					AppName:'p04_Ana',
					FuncName:'QueryById',
					InputValue:'{"Id":' + id + '}',
					QuerySdate : startDate,
					QueryEdate : endDate
			};
			break;
			case "sec":
			data = {
					AppName:'p06_SecBuzzer',
					FuncName:'QueryById',
					InputValue:'{"Id":"' + id + '"}',
					QuerySdate : startDate,
					QueryEdate : endDate
			};
			break;
		}
		
		
		$http.post('./api/s17/getTop5Detail', data, csrf_config).then(function(data) {
			 $scope.allTop5Detail = 	data.data
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
		
	}
	
	
}
