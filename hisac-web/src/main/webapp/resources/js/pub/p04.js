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
	
	
	 $scope.maxima = function(){
	    	
	        var dom = document.getElementById("maxima");
	        var myChart = echarts.init(dom);
	        var app = {};

	        var option;
	        
	        var request= {
	    			
	    	};
	        
	        $http.post('./common/queryAllPoints', request, csrf_config).then(function(response) {
				
	    

		        const data = [response.data.pointInfoData, response.data.pointGreenData, response.data.pointAccData , response.data.pointSafeData, response.data.pointWarData];
		        
		        option = {
		            backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [
		                {
		                    offset: 0,
		                    color: '#f7f8fa'
		                }
		                ,
		                {
		                    offset: 1,
		                    color: '#f7f8fa'
		                }
		            ]),
		            title: {
		                text: '',
		                left: '5%',
		                top: '3%'
		            },
		            legend: {
		                right: '10%',
		                top: '3%',
		                data: ['資訊及數位相關產業', '綠電及再生能源產業' , '臺灣精準健康戰略產業' , '國防及戰略產業' , '民生及戰備產業' ]
		            },
		            grid: {
		                left: '8%',
		                top: '10%'
		            },
		            xAxis: {
		                splitLine: {
		                    lineStyle: {
		                        type: 'dashed'
		                    }
		                }
		            },
		            yAxis: {
		                splitLine: {
		                    lineStyle: {
		                        type: 'dashed'
		                    }
		                },
		                scale: true
		            },
		            toolbox: {
		                feature: {
		                    saveAsImage: {
		                    	show : true
		                    	,title : '保存為圖片'
		                    	
		                    	
		                    },
		                },
		            },
		            series: [
		                {
		                    name: '資訊及數位相關產業',
		                    data: data[0],
		                    type: 'scatter',
		                    symbolSize: function (data) {
		                        return Math.sqrt(data[2]) / 2;
		                    },
		                    emphasis: {
		                        focus: 'series',
		                        label: {
		                            show: true,
		                            formatter: function (param) {
		                                return param.data[3] +", " + param.data[2];
		                            },
		                            position: 'top'
		                        }
		                    },
		                    itemStyle: {
		                        shadowBlur: 10,
		                        shadowColor: 'rgba(250, 120, 5, 0.5)',
		                        shadowOffsetY: 5,
		                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
		                            {
		                                offset: 0,
		                                color: 'rgb(250, 120, 5)'
		                            },
		                            {
		                                offset: 1,
		                                color: 'rgb(250, 120, 5)'
		                            }
		                        ])
		                    }
		                }
		                ,
		                {
		                    name: '綠電及再生能源產業',
		                    data: data[1],
		                    type: 'scatter',
		                    symbolSize: function (data) {
		                        return Math.sqrt(data[2]) / 2;
		                    },
		                    emphasis: {
		                        focus: 'series',
		                        label: {
		                            show: true,
		                            formatter: function (param) {
		                                return param.data[3] +", " + param.data[2];
		                            },
		                            position: 'top'
		                        }
		                    },
		                    itemStyle: {
		                        shadowBlur: 10,
		                        shadowColor: 'rgba(99, 206, 203, 0.5)',
		                        shadowOffsetY: 5,
		                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
		                            {
		                                offset: 0,
		                                color: 'rgb(99, 206, 203)'
		                            },
		                            {
		                                offset: 1,
		                                color: 'rgb(99, 206, 203)'
		                            }
		                        ])
		                    }
		                }
		                ,
		                
		                {
		                    name: '臺灣精準健康戰略產業',
		                    data: data[2],
		                    type: 'scatter',
		                    symbolSize: function (data) {
		                        return Math.sqrt(data[2]) / 2;
		                    },
		                    emphasis: {
		                        focus: 'series',
		                        label: {
		                            show: true,
		                            formatter: function (param) {
		                                return param.data[3] +", " + param.data[2];
		                            },
		                            position: 'top'
		                        }
		                    },
		                    itemStyle: {
		                        shadowBlur: 10,
		                        shadowColor: 'rgba(255, 95, 95, 0.5)',
		                        shadowOffsetY: 5,
		                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
		                            {
		                                offset: 0,
		                                color: 'rgb(255, 95, 95)'
		                            },
		                            {
		                                offset: 1,
		                                color: 'rgb(255, 95, 95)'
		                            }
		                        ])
		                    }
		                },
		                {
		                    name: '國防及戰略產業',
		                    data: data[3],
		                    type: 'scatter',
		                    symbolSize: function (data) {
		                        return Math.sqrt(data[2]) / 2;
		                    },
		                    emphasis: {
		                        focus: 'series',
		                        label: {
		                            show: true,
		                            formatter: function (param) {
		                                return param.data[3] +", " + param.data[2];
		                            },
		                            position: 'top'
		                        }
		                    },
		                    itemStyle: {
		                        shadowBlur: 10,
		                        shadowColor: 'rgba(255, 191, 97, 0.5)',
		                        shadowOffsetY: 5,
		                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
		                            {
		                                offset: 0,
		                                color: 'rgb(255, 191, 97)'
		                            },
		                            {
		                                offset: 1,
		                                color: 'rgb(255, 191, 97)'
		                            }
		                        ])
		                    }
		                }
		                ,
		                {
		                    name: '民生及戰備產業',
		                    data: data[3],
		                    type: 'scatter',
		                    symbolSize: function (data) {
		                        return Math.sqrt(data[2]) / 2;
		                    },
		                    emphasis: {
		                        focus: 'series',
		                        label: {
		                            show: true,
		                            formatter: function (param) {
		                                return param.data[3] +", " + param.data[2];
		                            },
		                            position: 'top'
		                        }
		                    },
		                    itemStyle: {
		                        shadowBlur: 10,
		                        shadowColor: 'rgba(121, 174, 221, 0.5)',
		                        shadowOffsetY: 5,
		                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
		                            {
		                                offset: 0,
		                                color: 'rgb(121, 174, 221)'
		                            },
		                            {
		                                offset: 1,
		                                color: 'rgb(121, 174, 221)'
		                            }
		                        ])
		                    }
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
	            $("#loadingActivity").fadeOut("slow");

			});
	    }
	    
	    $scope.maxima()
	
	
	
	
	
};