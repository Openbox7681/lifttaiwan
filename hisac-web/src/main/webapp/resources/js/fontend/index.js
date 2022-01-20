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
	
	$scope.queryCookie = function() {

		var request = {
			
			Id : 1
		};
		$http.post('./fontend/index/cookiePage/query/id', request, csrf_config).then(function(response) {
			
			if (response.data.success) {
				privacy = response.data.data;
				
				$scope.cookieData = privacy.Title;
		
				
				

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
	};
	
	$scope.queryCookie();
	
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
            formatter: '',
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
                data: ['2013-2017', '2014-2018', '2015-2019']
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
                    data: ['中國', '丹麥', '日本', '以色列', '印度', '南韓', '美國', 
                           '英國', '荷蘭', '新加坡', '瑞士', '瑞典', '臺灣', '德國', '澳洲']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '2013-2017',
                    type: 'bar',
                    barGap: 0,
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [25.67, 63.34, 31.25, 48.96, 24.35, 30.12, 36.7, 54.79, 
                           60.89, 64.21, 70.17, 63.34, 30.59, 54.35, 52.79]
                },
                {
                    name: '2014-2018',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [26.2, 65.1, 32.43, 50.03, 25.25, 30.74, 38.21, 57.12, 
                           62.56, 66.49, 71.52, 65.1, 33.18, 55.67, 54.99]
                },
                {
                    name: '2015-2019',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [26.61, 66.69, 33.74, 51.11, 26.23, 31.49, 39.54, 58.92,
                           64.21, 68.68, 72.77, 66.69, 36.09, 56.92, 57.17]
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
                    show: node.symbolSize > 1
                };
            });
            option = {
                title: {
                    text: '六大領域國際網路',
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
                        name: '六大領域國際網路',
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
	
	$scope.queryPeopleMains = function() {
		
		var country = $(".change_text").text();
		
		if(country == '過去10年中的國際優秀人才和研究成果') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		var request = {
			country: country
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};

		$http.post('./fontend/index/queryPeopleMains', request, csrf_config).then(function(response) {
			
			$scope.computerData = response.data.computerData;
			$scope.healthData = response.data.healthData;
			$scope.warData = response.data.warData;
			$scope.greenData = response.data.greenData;
			$scope.lifeData = response.data.lifeData;
			$scope.otherData = response.data.otherData;
			
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
	$scope.queryPeopleMains();
	
	$(document).ready(function() {
		
		  $("#inbound").text("31710");
		  $("#outbound").text("402");
		  $("#mechanism").text("31629");
		  $("#result").text("5000");

		  $(".s1").click(function() { 
			  $(".change_text").text("台灣");
			  $("#inbound").text("24456");
			  $("#outbound").text("0");
			  $("#mechanism").text("24183");
			  $("#result").text("5000");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".s2").click(function() { 
		    $(".change_text").text("德國");
		    $("#inbound").text("110");
			  $("#outbound").text("23");
			  $("#mechanism").text("132");
			  $("#result").text("29");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".t1").click(function() { 
		    $(".change_text").text("馬來西亞");
		    $("#inbound").text("202");
			  $("#outbound").text("0");
			  $("#mechanism").text("202");
			  $("#result").text("11");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".t2").click(function() { 
		    $(".change_text").text("中國");
		    $("#inbound").text("0");
			  $("#outbound").text("0");
			  $("#mechanism").text("0");
			  $("#result").text("232");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".t3").click(function() { 
		    $(".change_text").text("法國");
		    $("#inbound").text("153");
			  $("#outbound").text("6");
			  $("#mechanism").text("151");
			  $("#result").text("18");
			  $scope.queryPeopleMains();
		  });
		                     
		  $(".u1").click(function() { 
		    $(".change_text").text("印度");
		    $("#inbound").text("3016");
			  $("#outbound").text("0");
			  $("#mechanism").text("2913");
			  $("#result").text("15");
			  $scope.queryPeopleMains();
		  });
		                     
		  $(".u3").click(function() { 
		    $(".change_text").text("俄羅斯");
		    $("#inbound").text("321");
			  $("#outbound").text("0");
			  $("#mechanism").text("317");
			  $("#result").text("10");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".v1").click(function() { 
		    $(".change_text").text("越南");
		    $("#inbound").text("235");
			  $("#outbound").text("0");
			  $("#mechanism").text("233");
			  $("#result").text("0");
			  $scope.queryPeopleMains();
		  });

		  $(".o1").click(function() { 
		    $(".change_text").text("美國");
		    $("#inbound").text("618");
			  $("#outbound").text("286");
			  $("#mechanism").text("877");
			  $("#result").text("383");
			  $scope.queryPeopleMains();
		  });
		                    
		  $(".o2").click(function() { 
		    $(".change_text").text("日本");
		    $("#inbound").text("373");
			  $("#outbound").text("23");
			  $("#mechanism").text("388");
			  $("#result").text("81");
			  $scope.queryPeopleMains();
		  });
		  
		  $(".worldicon").click(function() { 
		    $(".change_text").text("過去10年中的國際優秀人才和研究成果");
		    $("#inbound").text("31710");
		    $("#outbound").text("402");
		    $("#mechanism").text("31629");
		    $("#result").text("5000");
		    $scope.queryPeopleMains();
		  });
		});
	
	$('#mechanismHref').click(function() {
	  var country = $(".change_text").text();
	  country = btoa(escape(country));
	  $window.location = "./fontend/mechanism/" + country;
	});
	
	$('#resultsHref').click(function() {
	  var country = $(".change_text").text();
	  country = btoa(escape(country));
	  $window.location = "./fontend/results/" + country;
	});
};
