var myApp = angular.module('myApp', [ 'bw.paging', 'ngSanitize', 'ui.toggle'  ]).controller('getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

myApp.filter('BrHtml',function(){
    return function(input){
    	if (input) {
    		input = input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />');    		
    		return input.split('<br />')
    	}
    };
});

function getAppController($scope, $rootScope, $http, $window) {
	
	$scope.queryNumber = function() {
        $("#loadingActivity").fadeIn("slow");

		var request = {
			count_topname : true,
			count_p_id : true,
			count_paper_SerialNumber : true,
			paper_corId : "1"
		};
		$http.post('./common/queryNumber', request, csrf_config).then(function(response) {
			
			console.log("testfff");
			
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

	$scope.queryForm = function() {

		var request = {
			
		};
		$http.post('./common/p02/queryForm', request, csrf_config).then(function(response) {
						
			$scope.formData = response.data.formData;
			
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
	$scope.queryForm();
	
$scope.solar_employment = function(){
    	
		var request= {
		    			
		};
    	
    	$http.post('./common/queryLines', request, csrf_config).then(function(response) {
    	
			var dom = document.getElementById("solar_employment");
		    var myChart = echarts.init(dom);
		    var app = {};
		
		    var option;
		
		    option = {
		        title: {
		            text: ''
		        },
		        tooltip: {
		            trigger: 'axis',
		            	axisPointer: {
		            	      type: 'cross',
		            	      crossStyle: {
		            	        color: '#999'
		            	      }
		            	    }
		        },
		        legend: {
		            data: ['In-bound平均', 'Out-bound平均' , 'In-bound國合' , 'Out-bound國合']
		        },
		        grid: {
		            left: '3%',
		            right: '4%',
		            bottom: '3%',
		            containLabel: true
		        },
		        toolbox: {
		            feature: {
		                saveAsImage: { show: true 
		                	,title : '保存為圖片'}
		            }
		        },
		        xAxis: {
		            type: 'category',
		            data: ['2011年','2012年','2013年','2014年','2015年','2016年','2017年','2018年','2019年','2020年'],
		            axisPointer: {
		                type: 'shadow'
		              }
		        },
		        yAxis: [{
		            type: 'value',
		            name: '合作篇數',
		            min: 0,
		            max: 25000,
		            interval: 5000,
		            axisLabel: {
		              formatter: '{value} 篇'
		            }
		          }
		        ,{
		        	 type: 'value',
			            name: '平均篇數',
			            min: 0,
			            max: 0.8,
			            interval: 0.2,
			            axisLabel: {
			              formatter: '{value} 篇'

		        }
		        }
		        ],
		        series: [
		            {
		                name: 'In-bound平均發表篇數',
		                type: 'bar',
                        yAxisIndex: 1,
		                data: response.data.inNoCorlineData
		            },
		            {
		                name: 'Out-bound平均發表篇數',
		                type: 'bar',
                        yAxisIndex: 1,
		                data: response.data.outNotCorlineData
		            },
		            
		            {
                        name: 'In-bound國際合作篇數',
                        type: 'line',
                        yAxisIndex: 0,
                        data: response.data.lineData
                    }
		            
		            ,
		            {
                        name: 'Out-bound國際合作篇數',
                        type: 'line',
                        yAxisIndex: 0,
                        data: response.data.outlineData
                    }
		            
		            
		        ]
		    };
		
		    if (option && typeof option === 'object') {
		        myChart.setOption(option);
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
			$("#imgLoading").hide();
		});
    }
    
    
    $scope.solar_employment();
	
	

	
	

	
};