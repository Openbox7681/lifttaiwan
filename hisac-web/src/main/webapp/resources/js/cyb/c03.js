var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {
	
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
	
	$scope.cubelink = function(){
		var dom = document.getElementById("cubelink");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            series: [
                {
                    type: 'treemap',
                    data: [
                        {
                            name: 'nodeA',
                            value: 10,
                            children: [
                                {
                                    name: 'nodeAa',
                                    value: 4
                                },
                                {
                                    name: 'nodeAb',
                                    value: 6
                                }
                            ]
                        },
                        {
                            name: 'nodeB',
                            value: 20,
                            children: [
                                {
                                    name: 'nodeBa',
                                    value: 20,
                                    children: [
                                        {
                                            name: 'nodeBa1',
                                            value: 20
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
		
		
	}
	
	
	$scope.cubelink();
	
	
	
	
	
	$scope.pie1 = function(){
		var dom = document.getElementById("pie1");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: '40',
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: [
                        { value: 1048, name: 'Search Engine' },
                        { value: 735, name: 'Direct' },
                        { value: 580, name: 'Email' },
                        { value: 484, name: 'Union Ads' },
                        { value: 300, name: 'Video Ads' }
                    ]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
		
		
	}
	
	$scope.pie1();
	
	
	
	$scope.pie2 = function(){
		var dom = document.getElementById("pie2");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: '40',
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: [
                        { value: 1048, name: 'Search Engine' },
                        { value: 735, name: 'Direct' },
                        { value: 580, name: 'Email' },
                        { value: 484, name: 'Union Ads' },
                        { value: 300, name: 'Video Ads' }
                    ]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
	}
	
	$scope.pie2();
	
	

	
	 

}