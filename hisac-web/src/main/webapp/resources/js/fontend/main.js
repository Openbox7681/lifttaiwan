$(document).ready(function(){
	$('#nav-icon3').click(function(){
		$(this).toggleClass('open');
		$('#stop').toggleClass('stop_scrolling');
		$('.nav_barcontent').fadeToggle('show_bar');
	});
});


var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {
			
			
			
			$scope.cubelink = function(){
		document.getElementById("top20").removeAttribute('_echarts_instance_');

		var dom = document.getElementById("top20");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
        		tooltip: {
        		   },
            series: [
                {
                    type: 'treemap',
                    data: $scope.Cube50Data
                
                
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
		
		
	}
	

	//六大領域資料 start
	
	
	$scope.IsInfoShow = false;
	
	$scope.isSupport = false;

	$scope.isSupport2 = false;

	
	$scope.InfoList = [
		{
			"Name" : "全部",
			"Flag" : $scope.infoSelectAll
		}] ;

	//是否選取全部領域 //
	$scope.infoSelectAll = true;
	
	//六大領域資料 初始化資料 
	//資訊及數位相關產業
	var InfoData = [
		{
			"Name" : "衛星通訊",
			"Flag" : false
		},
		{
			"Name" : "人工智慧",
			"Flag" : false
		},
		{	
			"Name" : "AI晶片",
			"Flag" : false
		},
		{
			"Name" : "系統軟體",
			"Flag" : false
		},
		{	
			"Name" : "半導體材料",
			"Flag" : false
		},
		{
			"Name" : "量子科技",
			"Flag" : false
		},
		{
			"Name" : "半導體設備",
			"Flag" : false
		},
		{
			"Name" : "行動通訊",
			"Flag" : false
		},
		{
			"Name" : "晶圓封測",
			"Flag" : false
		},
		{
			"Name" : "運動科技",
			"Flag" : false
		},
		{
			"Name" : "積體電路設計",
			"Flag" : false
		},
		{
			"Name" : "物聯網(含感測器)",
			"Flag" : false
		},
		{
			"Name" : "零接觸生活科技",
			"Flag" : false
		},
		{
			"Name" : "網路通訊",
			"Flag" : false
		},
		{
			"Name" : "數據科學",
			"Flag" : false
		},
		{
			"Name" : "智慧運輸",
			"Flag" : false			
		},
		{
			"Name" : "資料中心(含伺服器)",
			"Flag" : false
		},
		{
			"Name" : "資安支援",
			"Flag" : false
		},
		{
			"Name" : "晶圓代工",
			"Flag" : false
		},
		{	
			"Name" : "數位娛樂",
			"Flag" : false
		},
		{
			"Name" : "資安防護",
			"Flag" : false
		}	
	]
	//臺灣精準健康戰略產業
	var HealthyData = [
		{
		"Name" : "精準醫療(含遠距醫療)",
		"Flag" : false
		},
		{
			"Name" : "健康管理",
			"Flag" : false
		},
		{
			"Name" : "再生醫療",
			"Flag" : false
		},
		{
			"Name" : "智慧醫療",
			"Flag" : false
		},
		{
			"Name" : "智慧醫材",
			"Flag" : false
		}
	]
	
	//國防及戰略產業
	
	var SafeData = [
		{
		"Name" : "太空發射技術",
		"Flag" : false
		},
		{
			"Name" : "太空檢測技術",
			"Flag" : false
		},
		{
			"Name" : "發動機技術",
			"Flag" : false
		},
		{
			"Name" : "推進系統技術",
			"Flag" : false
		},
		{
			"Name" : "戰機關鍵技術",
			"Flag" : false
		}
	]
	//民生及戰備產業
	var LivelihoodData =[
		{
			"Name" : "再生能源",
			"Flag" : false
		},
		{
			"Name": "救災資源資料庫",
			"Flag" : false
		},
		{
			"Name" : "民生物資與食品加工產業鏈",
			"Flag" : false
		},
		{
			"Name" : "安全庫存",
			"Flag" : false
		}
		
	]
	//綠電及再生能源產業
	var PowerData = [
		{
			"Name" : "水下基礎",
			"Flag" : false 
		},
		{
			"Name" : "離岸風電",
			"Flag" : false
		},
		{
			"Name" : "太陽光電",
			"Falg" : false
		},
		{
			"Name" : "風機系統",
			"Flag" : false
		},
		{
			"Name" : "海事工程",
			"Falg" : false
		},
		{
			"Name" : "電力設備",
			"Flag" : false
		}
	]
	//其他
	var OtherData = [
		{
			"Name" : "其他",
			"Flag" : false
		}	
	]
	
	$scope.getKeyword = function(name){
		
		angular.forEach($scope.InfoList, function(item) {
			if (item.Name != name) {
				item.Flag = false;							
			}
			});	
			}
			
	$scope.changeSelectAllNone = function() {			
		angular.forEach($scope.InfoList, function(item) {			
			item.Flag = false;								
			});				
	}
	$scope.changeSelectAll = function() {			
		angular.forEach($scope.InfoList, function(item) {			
			item.Flag = true;								
			});				
	}
	//主領域選項控制送出按鈕用 預設為 0 為全部
	$scope.option = 0;
	
	
	//領域搜尋功能
	//按領域查詢 打開下拉選單功能
	$scope.openSubSubjects = function(index){
		const subSubject = $('.sub-subject');
	    const keywords = $('.keyword-block');
		$scope.IsInfoShow = true;
	   
	    if (index == 0){
	    	$scope.infoSelectAll = true;
			$scope.IsInfoShow = false;
	    	$scope.InfoList = [
	    		{
	    			"Name" : "全部",
	    			"Flag" : $scope.infoSelectAll
	    		}] ;
			$scope.option =0;
	    	$(".su_healthy").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_info").removeClass("it_color");
	    }
	    else if (index == 1 ){
				console.log("test2211");	

	    	$scope.infoSelectAll= false;
			$scope.InfoList = InfoData;
			$scope.changeSelectAllNone();
			$scope.option =1 ;
			$scope.IsInfoShow1 = true;

		    $(".su_healthy").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_info").addClass("it_color");
		}else if (index == 2){
	    	$scope.infoSelectAll= false;
			$scope.InfoList = HealthyData;
			$scope.changeSelectAllNone();
			$scope.option =2;
			$scope.IsInfoShow2 = true;

			$(".su_info").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_healthy").addClass("it_color");
		}else if (index == 3) {
	    	$scope.infoSelectAll= false;
			$scope.InfoList = SafeData;
			$scope.changeSelectAllNone();
			$scope.option =3;
			$scope.IsInfoShow3 = true;

			$(".su_info").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_healthy").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_safe").addClass("it_color");
		}else if (index == 4){
	    	$scope.infoSelectAll= false;
			$scope.InfoList = LivelihoodData;
			$scope.changeSelectAllNone();
			$scope.option =4;
			$scope.IsInfoShow4 = true;

			$(".su_info").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_healthy").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_livelihood").addClass("it_color");
		}else if (index == 5){
	    	$scope.infoSelectAll= false;
			$scope.InfoList = PowerData;
			$scope.changeSelectAllNone();
			$scope.option =5;
			$scope.IsInfoShow5 = true;

			$(".su_info").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_healthy").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_power").addClass("it_color");
		}else if (index == 6) {
	    	$scope.infoSelectAll= false;
			$scope.InfoList = OtherData;
			$scope.changeSelectAllNone()
			$scope.option =6;
			$scope.IsInfoShow6 = true;

			$(".su_info").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_healthy").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_other").addClass("it_color");	
		}
	}
	
	$scope.query =function(){
 	   console.log($scope.InfoList);
 	   console.log($scope.InfoList);

		$scope.queryForm();
 	   $scope.isSupport = true;
 	   
	}
	
	$scope.queryForm = function() {
		$("#imgLoading").show();
		$scope.isSupport = false
		

		var request = {		
				classSubList : $scope.InfoList
		};
		console.log(request);
		$http.post('./intInfo/queryTopad50Data', request, csrf_config2).then(function(response) {
			$scope.Topad50Data = response.data.datatable;	
			$scope.Cube50Data = response.data.cubetable;	
			

			$scope.cubelink();


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
	
	//六大領域資料 end
				
				
	
	$scope.query2 =function(){
		$scope.queryForm2()
		$scope.isSupport2 = true;

		
		
	}
	
	
	$scope.queryForm2 = function() {


		var request = {		
				classSubList : $scope.InfoList
		};
		console.log(request);
		$http.post('./intInfo/queryTopres20Data', request, csrf_config2).then(function(response) {
			$scope.Topres20Data = response.data.datatable;	
			$scope.Connect = response.data.connect;	

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
	
	$scope.connect = function(){
		
		 var dom = document.getElementById("connect");
         var myChart = echarts.init(dom);
         var app = {};

         var option;
         
         myChart.showLoading();
         
         myChart.hideLoading();
         
         var graph = $scope.Connect
         
         graph.nodes.forEach(function (node) {
             node.label = {
                 show: node.symbolSize > 10
             };
         });
         
         option = {
                 title: {
                     text: '',
                     subtext: '',
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
                         layout: 'circular',
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
        if (option && typeof option === 'object') {
             myChart.setOption(option);
         }
         
 	    $(".scholars_chart").addClass("show_chart");

		
       
	}
	
	//六大領域資料 end
	
	$scope.query4 =function(){
		$scope.queryForm4()

		
		
	}
	
	
	$scope.queryForm4 = function() {


		var request = {		
				classSubList : $scope.InfoList
		};
		$http.post('./intInfo/queryTopres20Data', request, csrf_config2).then(function(response) {
			$scope.Topres20Data = response.data.datatable;	
			$scope.Connect = response.data.connect;	
			
			
			$scope.connect();
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
	
	
	$scope.strip_1 = function(){
            var dom = document.getElementById("strip_1");
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
                        saveAsImage: { show: true }
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: { show: false },
                        data: ['英國', '荷蘭', '瑞士', '新加玻', '瑞典']
                        ,
                        name : "國家別"
                        ,
                        nameLocation: "middle",
                        nameTextStyle: {
                            padding: [10, 10, 10, 10]
                          }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        	 name : "學術論文合作比例"
                                 ,
                                 nameLocation: "middle",
                                 nameTextStyle: {
                                    
                                     padding: [12, 12, 12, 12]
                                   }
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
                        data: [54.79, 60.89, 70.51, 64.21 , 63.34
                        	
                        	
                        	]
                    },
                    {
                        name: '2014-2018',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [57.12, 62.56, 71.22, 66.49 , 65.1
                  
                        	]
                    },
                    {
                        name: '2015-2019',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [58.92, 64.21, 70.57, 68.68 , 66.69
                        	
                        	]
                    }
                   
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
        
        
        
        $scope.strip_2 = function(){
		var dom = document.getElementById("strip_2");
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
                data: ['無國際合作發表', '有國際合作發表']
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    saveAsImage: { show: true }
                }
            },
            xAxis: [
                {
                    type: 'category',
                    axisTick: { show: false },
                    data: ['丹麥', '以色列', '美國', '荷蘭', '瑞士' ]
                    ,
                    name : "國家別"
                        ,
                        nameLocation: "middle",
                        nameTextStyle: {
                            padding: [10, 10, 10, 10]
                          }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name : "領域科系影響力"
                        ,
                        nameLocation: "middle",
                        nameTextStyle: {
                            padding: [10, 10, 10, 10]
                          }
                }
            ],
            series: [
                {
                    name: '無國際合作發表',
                    type: 'bar',
                    barGap: 0,
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [1.13, 1.05, 0.78, 1.08, 1.13]
                },
                {
                    name: '有國際合作發表',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: [1.56, 1.87,1.79 ,1.86, 1.86]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
		
	}
	
	$scope.strip_3 = function()
	{
            var dom = document.getElementById("strip_3");
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
                distance: 12,
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
                fontSize: 12,
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
                        saveAsImage: { show: true }
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                    	name : "學術領域別",
                    	nameLocation: "middle",
                        axisTick: { show: false },
                        data: ['太空', '地球', '物理', '植物病', '數學']
                        ,
                        nameTextStyle: {
                            padding: [10, 10, 10, 10]
                          }
                    }
                ],
                yAxis: [
                    {
                    	name : "論文國際合作比例",
                        type: 'value'
                        	,nameLocation: "middle"
                        		 ,
                                 nameTextStyle: {
                                     padding: [10, 10, 10, 10]
                                   }
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
                        data: [91.58, 62.22, 45.02, 34.53, 49.28]
                    },
                    {
                        name: '2014-2018',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [92.87, 63.85, 49.75, 36.19, 54.86]
                    },
                    {
                        name: '2015-2019',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [93.41, 65.29, 55.15, 38.87, 60.87]
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
	
	
	
	


	$scope.strip_1();
	$scope.strip_2();
	$scope.strip_3();


	$scope.isShow1 = true ;
	$scope.isShow2 = false ;
	$scope.isShow3 = false ;
			
	
	
	$scope.formData = [
		{
			name : '中國',
			year2013 : 25.67,
			year2014 : 26.2,
			year2015 : 26.61
		},
		{
			name : '丹麥',
			year2013 : 65.34,
			year2014 : 65.1,
			year2015 : 66.69
		},
		{
			name : '日本',
			year2013 : 31.25,
			year2014 : 32.43,
			year2015 : 33.74
		},
		{
			name : '以色列',
			year2013 : 48.96,
			year2014 : 50.03,
			year2015 : 51.11
		},
		{
			name : '印度' ,
			year2013 : 24.35,
			year2014 : 25.25,
			year2015 : 26.23
		},
		{
			name : '南韓',
			year2013 : 30.12,
			year2014 : 30.74,
			year2015 : 31.79
		},
		{
			name : '美國',
			year2013 : 36.7,
			year2014 : 38.21,
			year2015 : 39.54	
		},
		{
			name : '英國',
			year2013 : 54.79,
			year2014 : 57.12,
			year2015 : 58.92		
		},
		{
			name : '荷蘭' ,
			year2013 : 60.89,
			year2014 : 62.56,
			year2015 : 64.21
		},
		{
			name : '新加玻',
			year2013 : 64.21,
			year2014 : 66.49,
			year2015 : 68.68
		},
		{
			name : '瑞士',
			year2013 : 70.17,
			year2014 : 71.57,
			year2015 : 72.77
		},
		{
			name : '瑞典',
			year2013 : 63.34,
			year2014 : 65.1,
			year2015 : 66.69
		},
		{
			name : '臺灣',
			year2013 : 30.59,
			year2014 : 33.18,
			year2015 : 36.09
		},
		{
			name : '德國',
			year2013 : 54.35,
			year2014 : 55.67,
			year2015 : 56.92
		},
		{
			name : '澳洲',
			year2013 : 52.79,
			year2014 : 54.99,
			year2015 : 57.17
		}
		
	]
	
	$scope.showDraw = function (option){
		if(option ==1){
			$scope.isShow1 = true;
			$scope.isShow2 = false;
			$scope.isShow3 = false;
		}else if (option == 2) {
			$scope.isShow1 = false;
			$scope.isShow2 = true;
			$scope.isShow3 = false;
		}else if(option == 3){
			$scope.isShow1 = false;
			$scope.isShow2 = false;
			$scope.isShow3 = true;
		}
		
		
	}
	
	$scope.formData2 = [
			{
				name : '美國',
				number_no : 1.13,
				number_yes : 1.56,
			},
			{
				name : '丹麥',
				number_no : 1.05,
				number_yes : 1.87,		
			},
			{
				name : '以色列',
				number_no : 0.78,
				number_yes : 1.79	
			},
			{
				name : '荷蘭',
				number_no : 1.08,
				number_yes : 1.86
			},
			{
				name : '瑞士',
				number_no : 1.13,
				number_yes : 1.86	
			},
			{
				name : '瑞典',
				number_no : 0.96,
				number_yes : 1.71	
			},
			{
				name : '澳洲',
				number_no : 1.01,
				number_yes : 1.71 
				
				
			}
		
		
		]
	$scope.formData3 = [
		{
			name : '工程學',
			year2013 : 20.31,
			year2014 : 23.47,
			year2015 : 27.54
		},
		{
			name : '分子生物學與遺傳學',
			year2013 : 37.66,
			year2014 : 39.01,
			year2015 : 39.98
		},
		{
			name : '化學',
			year2013 : 30.44,
			year2014 : 33.16,
			year2015 : 36.07
		},
		{
			name : '太空科學',
			year2013 : 91.58,
			year2014 : 92.87,
			year2015 : 93.41
		},
		{
			name : '地球與生化學',
			year2013 : 32.89,
			year2014 : 35.02,
			year2015 : 37.12
		},
		{
			name : '地球科學',
			year2013 : 62.22,
			year2014 : 63.85,
			year2015 : 65.29
		},
		{
			name : '免疫學',
			year2013 : 24.2,
			year2014 : 26.22,
			year2015 : 27.65
			
		},
		{
			name : '材料科學' ,
			year2013 : 24.94,
			year2014 : 28.39,
			year2015 : 32.55	
		},
		{
			name : '物理' ,
			year2013 : 45.02,
			year2014 : 49.75,
			year2015 : 55.15	
		},
		{
			name : '社會科學',
			year2013 : 26.56,
			year2014 : 28.75,
			year2015 : 30.82 
		},
		{
			name: '神經科學與行為學' ,
			year2013 : 32.72,
			year2014 : 33.02,
			year2015 : 35.59 	
		},
		{
			name: '植物與動物科學' ,
			year2013 : 48.11,
			year2014 : 50.25,
			year2015 : 52.29 
		},
		{
			name: '微生物學' ,
			year2013 : 34.19,
			year2014 : 38.07,
			year2015 : 40.92 
		},
		{
			name: '經濟與商管' ,
			year2013 : 32.98,
			year2014 : 35.99,
			year2015 : 40.36 	
		},
		{
			name: '農業科學' ,
			year2013 : 26.24,
			year2014 : 28.75,
			year2015 : 31.48 	
		},
		{	
			name : '電腦科學',
			year2013 : 25,
			year2014 : 27.75,
			year2015 : 31.5 		
		},
		{
			name : '精神病與心理學',
			year2013 : 34.53,
			year2014 : 36.19,
			year2015 : 38.87		
		},
		{
			name : '綜合領域',
			year2013 : 30.48,
			year2014 : 32.47,
			year2015 : 35.94
		},
		{
			name : '數學',
			year2013 : 30.48,
			year2014 : 32.47,
			year2015 : 35.94		
		},
		{
			name : '環境生態學',
			year2013 : 38.26,
			year2014 : 40.93,
			year2015 : 42.88	
			
		},
		{
			name : '臨床醫學',
			year2013 : 23.06,
			year2014 : 24.26,
			year2015 : 25.99	
		},
		{
			name : '藥理學與毒理學',
			year2013 : 26.1,
			year2014 : 28.67,
			year2015 : 29.96
			
		}
		
		
		
		]	
			
}