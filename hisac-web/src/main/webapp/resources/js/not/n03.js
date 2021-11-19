var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {
	
	
	$scope.ExporExcel=false;
	$scope.QueryDataList=false;
		
	$('input[id="QuerySdate"]').datetimepicker({		
		 format: 'YYYY-MM'	       
    });
	$("#QuerySdate").on("dp.change", function(e){	       
	     startDate = $('#QuerySdate').val();
	});
	
	$('input[id="QueryEdate"]').datetimepicker({		
		 format: 'YYYY-MM'	       
   }); 
	$("#QueryEdate").on("dp.change", function(e){	       
	     endDate = $('#QueryEdate').val();
	});
	 
	$scope.clearData = function() {
	    $scope.QuerySdate = null;
        $scope.QueryEdate = null;
        startDate=null;
        endDate=null;
        $('#QuerySdate').val("");
		$('#QueryEdate').val("");
		$('#QuerySdate').data("DateTimePicker").clear()
        $('#QueryEdate').data("DateTimePicker").clear()
        $scope.allitems=null;
        $scope.alertTypes=null;
        $scope.message_allitems =null;
        $scope.ExporExcel=false;
        $scope.QueryDataList=false;
        
	}
	
	$scope.queryData = function() {
		
		if(startDate!=null && endDate!=null)
		 {
				var s_startDate = startDate.split("-"); 
				var s_endDate = endDate.split("-"); 
				
				var Syear=s_startDate[0];
				var Smonth=s_startDate[1];
				
				var Eyear=s_endDate[0];
				var Emonth=s_endDate[1];
				
				
				
				var totleDate = [];
		 	    for (var i = Syear; i <= Eyear; i++) {
		 	    	
		 	    	 for (var x = 1; x <= 12; x++)
		 	    		 if (i==Syear && Syear<Eyear && x>=Smonth)
		 	    			 totleDate.push(i+"-"+x);
		 	    	     else if(i==Syear && x>=Smonth && Syear==Eyear && x<=Emonth && x>=Smonth)
		 	    		 	 totleDate.push(i+"-"+x);
		 	    		 else if(i>Syear && i<Eyear)
		 	    			totleDate.push(i+"-"+x);
		 	    		 else if(i==Eyear && x<=Emonth)
		 	    			totleDate.push(i+"-"+x);
		 		  }
		}
		
 	 //  console.log("totleDate="+JSON.stringify(totleDate));
		
		
		
		
		$scope.QuerySdate = startDate;
        $scope.QueryEdate = endDate;
		var request = {
			Sdate : $scope.QuerySdate,
			Edate : $scope.QueryEdate
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/n03/query', request, csrf_config).then(function(response) {
			$scope.MonthReport = response.data.datatable;
			$scope.allitems =totleDate;
			//console.log("response="+JSON.stringify(response.data.datatable));
			
			$scope.getalertType();
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
	
	$scope.getCount = function(AlertCode,yearmonth){
        var total = 0;
	    angular.forEach($scope.MonthReport, function(value, key) {
	    	if(value.AlertCode==AlertCode && value.YearMonth==yearmonth) {
   	        	total=value.Count;
   	        }
	    });
	    return total;
     }
	
	

	$scope.countTotal = function(yearmonth){
        var total = 0;
	    angular.forEach($scope.MonthReport, function(value, key) {
	    	if(value.YearMonth==yearmonth) {
   	        	total+=value.Count;
   	        }
	        
	    });
	    return total;
     }
	
	
	
	
	
	
	$scope.getalertType = function() {
		var request = {};
		$http.post('./api/n03/getalert', request, csrf_config).then(function(response) {
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
	
	$scope.getMessageData = function(alertCode,tdate) {
		$scope.QueryDataList=true;
		
		var request = {
			Tdate : tdate,
			AlertCode : alertCode	
		
		};
		$http.post('./api/n03/query/message/report/list', request, csrf_config).then(function(response) {
			$scope.message_allitems = response.data.datatable;
			
			console.log("response="+JSON.stringify(response.data.datatable));
			
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
		 
	        var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "message_month_report_"+(new Date()).toLocaleString(undefined, {
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