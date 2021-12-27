var myApp = angular.module('myApp', []).controller('getAppController',
    getAppController);

//angular.element(document).ready(function() {
//    angular.element(window).keydown(function() {
//        if (event.keyCode == 13) {
//            event.preventDefault();
//            return false;
//        }
//    });
//});

function getAppController($scope, $http, $window) {
	
	top20();
    country();
    connect();


    function top20() {
        var dom = document.getElementById("top20");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            series: [
                {
                    type: 'treemap',
                    data: [
                        {
                            name: 'Harvard University',
                            value: 509544
                        },
                        {
                            name: 'University of California, Los Angeles',
                            value: 288695
                        },
                        {
                            name: 'University of Toronto',
                            value: 287620
                        },
                        {
                            name: 'University of Michigan',
                            value: 269772
                        },
                        {
                            name: 'University of Washington',
                            value: 247978
                        },
                        {
                            name: 'The University of Tokyo',
                            value: 247161
                        },
                        {
                            name: 'Stanford University',
                            value: 236799
                        },
                        {
                            name: 'Johns Hopkins University',
                            value: 225704
                        },
                        {
                            name: 'University of Pennsylvania',
                            value: 222160
                        },
                        {
                            name: 'University of Minnesota',
                            value: 216489
                        },
                        {
                            name: 'University of Oxford',
                            value: 211076
                        },
                        {
                            name: 'University of California, San Francisco',
                            value: 204677
                        },
                        {
                            name: 'Chinese Academy of Sciences',
                            value: 204316
                        },
                        {
                            name: 'University of Cambridge',
                            value: 199231
                        },
                        {
                            name: 'University of Pittsburgh-Pittsburgh Campus',
                            value: 190815
                        },
                        {
                            name: 'Duke University',
                            value: 190139
                        },
                        {
                            name: 'University of California, Berkeley',
                            value: 187489
                        },
                        {
                            name: 'Yale University',
                            value: 186970
                        },
                        {
                            name: 'Columbia University',
                            value: 180307
                        },
                        {
                            name: 'University of California, San Diego',
                            value: 178862
                        }
                    ]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    }

    //台灣及其他國家表現
    function country() {
        var dom = document.getElementById("country");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        const posList = [
            'left',
            'right',
            'top',
            'bottom',
            'inside',
            'insideTop',
            'insideLeft',
            'insideRight',
            'insideBottom',
            'insideTopLeft',
            'insideTopRight',
            'insideBottomLeft',
            'insideBottomRight'
        ];
        app.configParameters = {
            rotate: {
                min: -90,
                max: 90
            },
            align: {
                options: {
                    left: 'left',
                    center: 'center',
                    right: 'right'
                }
            },
            verticalAlign: {
                options: {
                    top: 'top',
                    middle: 'middle',
                    bottom: 'bottom'
                }
            },
            position: {
                options: posList.reduce(function (map, pos) {
                    map[pos] = pos;
                    return map;
                }, {})
            },
            distance: {
                min: 0,
                max: 100
            }
        };
        app.config = {
            rotate: 90,
            align: 'left',
            verticalAlign: 'middle',
            position: 'insideBottom',
            distance: 15,
            onChange: function () {
                const labelOption = {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                };
                myChart.setOption({
                    series: [
                        {
                            label: labelOption
                        },
                        {
                            label: labelOption
                        },
                        {
                            label: labelOption
                        },
                        {
                            label: labelOption
                        }
                    ]
                });
            }
        };
        const labelOption = {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {}
            }
        };
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['Forest', 'Steppe', 'Desert', 'Wetland']
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar', 'stack'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            xAxis: [
                {
                    type: 'category',
                    axisTick: { show: false },
                    data: ['2012', '2013', '2014', '2015', '2016']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: 'Forest',
                    type: 'bar',
                    barGap: 0,
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [320, 332, 301, 334, 390]
                },
                {
                    name: 'Steppe',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [220, 182, 191, 234, 290]
                },
                {
                    name: 'Desert',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [150, 232, 201, 154, 190]
                },
                {
                    name: 'Wetland',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [98, 77, 101, 99, 40]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    }


    //國際網路
    function connect() {
        var dom = document.getElementById("connect");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        myChart.showLoading();
        $.getJSON('./resources/js/connect.json', function (graph) {
            myChart.hideLoading();
            graph.nodes.forEach(function (node) {
                node.label = {
                    show: node.symbolSize > 30
                };
            });
            option = {
                title: {
                    text: 'Les Miserables',
                    subtext: 'Default layout',
                    top: 'bottom',
                    left: 'right'
                },
                tooltip: {},
                legend: [
                    {
                        // selectedMode: 'single',
                        data: graph.categories.map(function (a) {
                            return a.name;
                        })
                    }
                ],
                animationDuration: 1500,
                animationEasingUpdate: 'quinticInOut',
                series: [
                    {
                        name: 'Les Miserables',
                        type: 'graph',
                        layout: 'none',
                        data: graph.nodes,
                        links: graph.links,
                        categories: graph.categories,
                        roam: true,
                        label: {
                            position: 'right',
                            formatter: '{b}'
                        },
                        lineStyle: {
                            color: 'source',
                            curveness: 0.3
                        },
                        emphasis: {
                            focus: 'adjacency',
                            lineStyle: {
                                width: 10
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        });

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    }
	
    $scope.queryVideo = function() {
		$("#imgLoading").show();
		
		var request = {
			start : "0",
			maxRows : "6",
			sort : "createTime",
			dir : false,
			IsEnable : true,
			IsShow : true
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};


		$http.post('./fontend/index/queryVideo', request, csrf_config).then(function(response) {
			
			$scope.allitems = response.data.datatable;
			$scope.alltags = response.data.tagtable;
			
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
	$scope.queryVideo();
	
	$scope.queryArticle = function() {
		$("#imgLoading").show();
		
		var request = {
			start : "0",
			maxRows : "3",
			sort : "createTime",
			dir : false,
			IsEnable : true,
			IsShow : true
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};


		$http.post('./fontend/index/queryArticle', request, csrf_config).then(function(response) {
			
			$scope.allarticles = response.data.datatable;
			$scope.allartTag = response.data.tagtable;
			
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
	$scope.queryArticle();
};
