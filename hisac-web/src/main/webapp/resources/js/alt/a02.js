var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore,$anchorScroll, $location) {
	 $scope.ExporExcel=false;
	 
	 // Paging Start
	    $scope.start = 0;
	    $scope.currentPage = 1;
	    $scope.maxRows = 10;
	    $scope.maxPages = 0;
	    $scope.total = 0;
	    $scope.sorttype = "applyDateTime";
	    $scope.sortreverse = true;
	
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
	       
	        $scope.ExporExcel=false;
		}
	 $scope.clearData();
	
	$scope.queryData = function() {
		$scope.QuerySdate = QuerySdate;
        $scope.QueryEdate = QueryEdate;
		var request = {
			
		         // sort: $scope.sorttype,
		        // dir: $scope.sortreverse,
				Sdate : $scope.QuerySdate,
				Edate : $scope.QueryEdate
		};
		// console.log("request="+JSON.stringify(request));
		$http.post('./api/a02/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			// console.log("response="+JSON.stringify(response.data.datatable));
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

	$scope.ceffectLevelCount = function(type,level){
	  var total = 0;
	  angular.forEach($scope.allitems, function(value, key) {
		  if(type==1 && value.CeffectLevel==level) {
		        total++;
		  }else if(type==2 && value.IeffectLevel==level) {
	        	total++;
	      }else if(type==3 && value.AeffectLevel==level) {
	        	total++;
	      }
 
	  });
	  return total;
	}
	
	$scope.ceffectLevelTotalCount = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
		  	// console.log("value="+JSON.stringify(value));
		      if(type==1 && value.CeffectLevel!=null) {
		    	  total++;  
               }else if(type==2 && value.IeffectLevel!=null) {
            	   total++;  
               }else if(type==3 && value.AeffectLevel!=null) {
            	   total++;  
               }
		      
		  });
		  return total;
		}
	
	
	$scope.eventTypeCount = function(level){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
		      if(value.EventType==level) {
			        	total++;
			        }
		  });
		  return total;
		}
	
	$scope.eventTypeTotalCount = function(){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {

			        	total++;

		  });
		  return total;
		}
	// 1.網頁攻擊
	$scope.eventType1Count = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  
		      if(type==1 && value.EventType==1 && value.IsEventType1Opt1==true ) {
			        	total++;
			  }else if(type==2 && value.EventType==1 && value.IsEventType1Opt2==true ) {
			        	total++;
			  }else if(type==3 && value.EventType==1 && value.IsEventType1Opt3==true ) {
			        	total++;
			  }else if(type==4 && value.EventType==1 && value.IsEventType1Opt4==true ) {
			        	total++;
			  }else if(type==5 && value.EventType==1 && value.IsEventType1Opt5==true ) {
			        	total++;
			  }else if(type==6 && value.EventType==1 && value.IsEventType1Opt6==true ) {
			        	total++;
			  }
 
		  });
		  return total;
		}
	// 2.非法入侵
	$scope.eventType2Count = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  
		      if(type==1 && value.EventType==2 && value.IsEventType2Opt1==true ) {
			        	total++;
			  }else if(type==2 && value.EventType==2 && value.IsEventType2Opt2==true ) {
			        	total++;
			  }else if(type==3 && value.EventType==2 && value.IsEventType2Opt3==true ) {
			        	total++;
			  }else if(type==4 && value.EventType==2 && value.IsEventType2Opt4==true ) {
			        	total++;
			  }else if(type==5 && value.EventType==2 && value.IsEventType2Opt5==true ) {
			        	total++;
			  }

		  });
		  return total;
		}
	// 3.阻斷服務
	$scope.eventType3Count = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  
		      if(type==1 && value.EventType==3 && value.IsEventType3Opt1==true ) {
			        	total++;
			  }else if(type==2 && value.EventType==3 && value.IsEventType3Opt2==true ) {
			        	total++;
			  }

		  });
		  return total;
		}
	
	// 4.設備異常
	$scope.eventType4Count = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  
			  if(type==1 && value.EventType==4 && value.IsEventType4Opt1==true ) {
		        	total++;
			  }else if(type==2 && value.EventType==4 && value.IsEventType4Opt2==true ) {
			        	total++;
			  }else if(type==3 && value.EventType==4 && value.IsEventType4Opt3==true ) {
			        	total++;
			  }else if(type==4 && value.EventType==4 && value.IsEventType4Opt4==true ) {
			        	total++;
			  }

		  });
		  return total;
		}
	
	
	// 事故發生原因
	$scope.finishReasonCount = function(type,reason){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  
			  if(type==1 && value.EventType==type && value.Finish1Reason==reason) {
				   	total++;
			  }else if(type==2 && value.EventType==type && value.Finish2Reason==reason ) {
			        total++;
			  }else if(type==4 && value.EventType==type && value.Finish4Reason==reason ) {
			        total++;
			  }else if(type==5 && value.EventType==type && value.Finish5Reason==reason ) {
		        	total++;
		  }

		  });
		  return total;
		}
	
	$scope.finishReasonCountTotal = function(type){
		  var total = 0;
		  angular.forEach($scope.allitems, function(value, key) {
			  if(type==0)
					total++;
			  else if(type==1 && value.EventType==type ) {
				   	total++;
			  }else if(type==2 && value.EventType==type) {
			        total++;
			  }else if(type==4 && value.EventType==type) {
			        total++;
			  }else if(type==5 && value.EventType==type) {
		        	total++;
		  }

		  });
		  return total;
		}
	
	
	
	
	

// $scope.countTotal = function(Code){
// var total = 0;
// angular.forEach($scope.allitems, function(value, key) {
//	    	
// //console.log("value="+JSON.stringify(value));
// if(value.EventCode==Code) {
// total=value.Count;
// return ;
// }
//	        
// });
// return total;
// }
//	
// $scope.alertTypeTotal = function(AlertCode,Code){
// var total = 0;
// angular.forEach($scope.allitems, function(value, key) {
// if(value.AlertCode==AlertCode) {
// total+=value.Count;
// }
//	        
// });
// return total;
// }
//	
//	
//	
//	
//	
//	
	 $scope.exportData = function() {
		 
		// alert("excel");
		 
	        var blob = new Blob([document.getElementById('exporExcel1').innerHTML + document.getElementById('exporExcel2').innerHTML + document.getElementById('exporExcel3').innerHTML + document.getElementById('exporExcel4').innerHTML + document.getElementById('exporExcel5').innerHTML + document.getElementById('exporExcel6').innerHTML + document.getElementById('exporExcel7').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "alert_report_"+(new Date()).toLocaleString(undefined, {
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