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
		// console.log("response="+JSON.stringify(request));

		$http.post('./api/c03/query', request, csrf_config).then(function(response) {
			console.log("response="+JSON.stringify(response.data));
			$scope.allitems_in = response.data.datatable_in;
			$scope.allitems_out = response.data.datatable_out;
			$scope.getalertType();
			$scope.geteventType();
			$scope.ExporExcel=true;
			
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

	
	
	
	
	$scope.getalertType = function() {
		var request = {};
		$http.post('./api/c03/getalert', request, csrf_config).then(function(response) {
			$scope.alertTypes = response.data;
			
// console.log("response="+JSON.stringify(response.data));
			
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
	
	
	$scope.geteventType = function() {
		var request = {};
		$http.post('./api/c03/getevent', request, csrf_config).then(function(response) {
			$scope.eventTypes = response.data;
			// console.log("response="+JSON.stringify(response.data));
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
	
	
	$scope.countInTotal = function(AlertCode, Code, type){
        var total = 0;
	    angular.forEach($scope.allitems_in, function(value, key) {
	        if(value.AlertCode == AlertCode && value.Code==Code && value.TransferType==type) {
	   	        	total=value.Count;
   	        }
	        
	        
	    });
	    return total;
     }
	
	
	$scope.countInOther = function(AlertCode, type){
        var total = 0;
	    angular.forEach($scope.allitems_in, function(value, key) {
	        if(value.AlertCode == AlertCode && value.TransferType==type){
	   	        	total=value.Count;
	   	        	angular.forEach($scope.eventTypes, function(value_e, key_e) {
	   	        		if (value_e.Code==value.Code)
	   	        			total=total- value.Count;
	   	        	});      
	        }
	    });
	    return total;
     }
	
	$scope.typeInTotal = function(alertCode,type){
        var total = 0;
	    angular.forEach($scope.allitems_in, function(value, key) {
        if(value.AlertCode==alertCode && value.TransferType==type) {
	        	total=total+value.Count;
           }
        });
	    return total;
	}
	
	$scope.countOutTotal = function(AlertCode, Code, type){
        var total = 0;
	    angular.forEach($scope.allitems_out, function(value, key) {
	        if(value.AlertCode == AlertCode && value.Code==Code && value.TransferType==type) {
	   	        	total=value.Count;
   	        }
	        
	        
	    });
	    return total;
     }
	
	$scope.countOutOther = function(AlertCode, type){
        var total = 0;
	    angular.forEach($scope.allitems_out, function(value, key) {
	        if(value.AlertCode == AlertCode && value.TransferType==type){
	   	        	total=value.Count;
	   	        	angular.forEach($scope.eventTypes, function(value_e, key_e) {
	   	        		if (value_e.Code==value.Code)
	   	        			total=total- value.Count;
	   	        	});      
	        }
	    });
	    return total;
     }
	
	$scope.typeOutTotal = function(alertCode,type){
        var total = 0;
	    angular.forEach($scope.allitems_out, function(value, key) {
        if(value.AlertCode==alertCode && value.TransferType==type) {
	        	total=total+value.Count;
           }
        });
	    return total;
	}
	
	
	
	 $scope.exportData = function() {
	        var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "informationExchange_report_"+(new Date()).toLocaleString(undefined, {
		   	    day:'numeric',
		   	    month: 'numeric',
		   	    year: 'numeric',
		   	    hour: '2-digit',
		   	    minute: '2-digit',
		   	    second: '2-digit',
		   	    hour12: false 
		   	})+".xls");
	 }}