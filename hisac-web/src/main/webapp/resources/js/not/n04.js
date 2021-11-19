var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {
	 $scope.ExporExcel=false;
	
	$('input[id="QuerySdate"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
   });
	 $("#QuerySdate").on("dp.change", function(e) {	       
		 QuerySdate = $('#QuerySdate').val();
	    });
	 
	 $('input[id="QueryEdate"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 

	 $("#QueryEdate").on("dp.change", function(e) {	       
		 QueryEdate = $('#QueryEdate').val();
	    });
	 

	 $scope.clearData = function() {
		    $scope.QuerySdate = null;
	        $scope.QueryEdate = null;
	        QuerySdate=null;
	        QueryEdate=null;
	        $('#QuerySdate').val("");
			$('#QueryEdate').val("");
			$('#QuerySdate').data("DateTimePicker").clear()
        	$('#QueryEdate').data("DateTimePicker").clear()
	        $scope.allitems=null;
	        $scope.alertTypes=null;
	        $scope.eventTypes =null;
	        $scope.ExporExcel=false;
		}
	 $scope.clearData();
	
	$scope.queryData = function() {
		$scope.QuerySdate = QuerySdate;
        $scope.QueryEdate = QueryEdate;
		var request = {
			Sdate : $scope.QuerySdate,
			Edate : $scope.QueryEdate
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/n04/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			//console.log("response="+JSON.stringify(response.data.datatable));
			
			$scope.getalertType();
			$scope.geteventType();
			 $scope.ExporExcel=true;
			
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	}

	$scope.countTotal = function(Code){
        var total = 0;
	    angular.forEach($scope.allitems, function(value, key) {
	    	
	    	//console.log("value="+JSON.stringify(value));
	        if(value.EventCode==Code) {
   	        	total=value.Count;
   	        	return ;
   	        }
	        
	    });
	    return total;
     }
	
	$scope.alertTypeTotal = function(AlertCode,Code){
        var total = 0;
	    angular.forEach($scope.allitems, function(value, key) {
	        if(value.AlertCode==AlertCode) {
   	        	total+=value.Count;
   	        }
	        
	    });
	    return total;
     }
	
	
	
	
	$scope.getalertType = function() {
		var request = {};
		$http.post('./api/n04/getalert', request, csrf_config).then(function(response) {
			$scope.alertTypes = response.data;
			
			//console.log("response="+JSON.stringify(response.data));
			
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	}
	
	
	$scope.geteventType = function() {
		var data = {AlertCode:$scope.QueryAlertCode};
		$http.post('./api/n04/getevent', data, csrf_config).then(function(response) {
			$scope.eventTypes = response.data;
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	}
	
	
	 $scope.exportData = function() {
		 
		// alert("excel");
		 
	        var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "message_week_report_"+(new Date()).toLocaleString(undefined, {
		   	    day:'numeric',
		   	    month: 'numeric',
		   	    year: 'numeric',
		   	    hour: '2-digit',
		   	    minute: '2-digit',
		   	    second: '2-digit',
		   	    hour12: false 
		   	})+".xls");
	    }
	
	

	
}