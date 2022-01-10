var myApp = angular.module('myApp', ['ngCookies', 'bw.paging', 'ui.toggle']).controller(
    'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $http, $cookieStore, $cookies, $anchorScroll, $location) {
	
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
	
	
	
	$scope.relation = function(){
		
		document.getElementById("relation").removeAttribute('_echarts_instance_');

		var dom = document.getElementById("relation");
        var myChart = echarts.init(dom);
        var app = {};

        var option;


        myChart.showLoading();
        
        
        myChart.hideLoading();
        
        var graph = $scope.Connect
        
        graph.nodes.forEach(function (node) {
            node.label = {
                show: node.symbolSize > 0
            };
        });
        
        option = {
                title: {
                    text: '國際網絡關係圖',
                    subtext: '國際網絡關係圖',
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
                        name: '',
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
	}
	
	
	
	
	$scope.country = function(){
		var dom = document.getElementById("country");
        var myChart = echarts.init(dom);
        var app = {};

        var option;        
        
            
       	var dataWrap = {
            		  "seriesData": [
            			    {
            			      "id": "總和",
            			      "value": 6229,
            			      "depth": 0,
            			      "index": 7,
            			      "name": "總和"
            			    },
            			    {
            			      "id": "總和.人工智慧",
            			      "value": 6229,
            			      "depth": 1,
            			      "index": 8,
            			      "name": "人工智慧"
            			    },
            			    {
            			      "id": "總和.人工智慧.china",
            			      "value": 942,
            			      "depth": 2,
            			      "index": 0,
            			      "name": "china"
            			    },
            			    {
            			      "id": "總和.人工智慧.taiwan",
            			      "value": 634,
            			      "depth": 2,
            			      "index": 1,
            			      "name": "taiwan"
            			    },
            			    {
            			      "id": "總和.人工智慧.japan",
            			      "value": 567,
            			      "depth": 2,
            			      "index": 2,
            			      "name": "japan"
            			    },
            			    {
            			      "id": "總和.資料中心",
            			      "value": 300,
            			      "depth": 1,
            			      "index": 3,
            			      "name": "資料中心"
            			    },
            			    {
            			      "id": "總和.資料中心.china",
            			      "value": 100,
            			      "depth": 2,
            			      "index": 4,
            			      "name": "china"
            			    },
            			    {
            			      "id": "總和.資料中心.taiwan",
            			      "value": 100,
            			      "depth": 2,
            			      "index": 5,
            			      "name": "taiwan"
            			    },
            			    {
            			      "id": "總和.資料中心.japan",
            			      "value": 100,
            			      "depth": 2,
            			      "index": 6,
            			      "name": "japan"
            			    }
            			  ],
            			  "maxDepth": 2
            			}
            
            
        initChart(dataWrap.seriesData, dataWrap.maxDepth);
        
        function prepareData(rawData) {
            const seriesData = [];
            let maxDepth = 0;
            function convert(source, basePath, depth) {
                if (source == null) {
                    return;
                }
                if (maxDepth > 5) {
                    return;
                }
                maxDepth = Math.max(depth, maxDepth);
                seriesData.push({
                    id: basePath,
                    value: source.$count,
                    depth: depth,
                    index: seriesData.length,
                });
                for (var key in source) {
                    if (source.hasOwnProperty(key) && !key.match(/^\$/)) {
                        var path = basePath + '.' + key;
                        convert(source[key], path, depth + 1);
                    }
                }
            }
            convert(rawData, '智慧及數位相關產業', 0);
            return {
                seriesData: seriesData,
                maxDepth: maxDepth
            };
        }
        function initChart(seriesData, maxDepth) {
            var displayRoot = stratify();
            function stratify() {
                return d3
                    .stratify()
                    .parentId(function (d) {
                        return d.id.substring(0, d.id.lastIndexOf('.'));
                    })(seriesData)
                    .sum(function (d) {
                        return d.value || 0;
                    })
                    .sort(function (a, b) {
                        return b.value - a.value;
                    });
            }
            function overallLayout(params, api) {
                var context = params.context;
                d3
                    .pack()
                    .size([api.getWidth() - 2, api.getHeight() - 2])
                    .padding(3)(displayRoot);
                context.nodes = {};
                displayRoot.descendants().forEach(function (node, index) {
                    context.nodes[node.id] = node;
                });
            }
            function renderItem(params, api) {
                var context = params.context;
                // Only do that layout once in each time `setOption` called.
                if (!context.layout) {
                    context.layout = true;
                    overallLayout(params, api);
                }
                var nodePath = api.value('id');
                var node = context.nodes[nodePath];
                if (!node) {
                    // Reder nothing.
                    return;
                }
                var isLeaf = !node.children || !node.children.length;
                var focus = new Uint32Array(
                    node.descendants().map(function (node) {
                        return node.data.index;
                    })
                );
                var nodeName = isLeaf
                    ? nodePath
                        .slice(nodePath.lastIndexOf('.') + 1)
                        .split(/(?=[A-Z][^A-Z])/g)
                        .join('\n')
                    : '';
                var z2 = api.value('depth') * 2;
                return {
                    type: 'circle',
                    focus: focus,
                    shape: {
                        cx: node.x,
                        cy: node.y,
                        r: node.r
                    },
                    transition: ['shape'],
                    z2: z2,
                    textContent: {
                        type: 'text',
                        style: {
                            // transition: isLeaf ? 'fontSize' : null,
                            text: nodeName,
                            fontFamily: 'Arial',
                            width: node.r * 1.3,
                            overflow: 'truncate',
                            fontSize: node.r / 3
                        },
                        emphasis: {
                            style: {
                                overflow: null,
                                fontSize: Math.max(node.r / 3, 12)
                            }
                        }
                    },
                    textConfig: {
                        position: 'inside'
                    },
                    style: {
                        fill: api.visual('color')
                    },
                    emphasis: {
                        style: {
                            fontFamily: 'Arial',
                            fontSize: 12,
                            shadowBlur: 20,
                            shadowOffsetX: 3,
                            shadowOffsetY: 5,
                            shadowColor: 'rgba(0,0,0,0.3)'
                        }
                    }
                };
            }

            option = {
            		
                dataset: {
                    source: seriesData
                },
                tooltip: {},
                visualMap: [
                    {
                        show: false,
                        min: 0,
                        max: maxDepth,
                        dimension: 'depth',
                        inRange: {
                            color: ['#FFFFFF' ,'#FFA500']
                        }
                    }
                ],
                hoverLayerThreshold: Infinity,
                series: {
                    type: 'custom',
                    renderItem: renderItem,
                    progressive: 0,
                    coordinateSystem: 'none',
                    encode: {
                        tooltip: 'value',
                        itemName: 'name'
                    }
                }
            };
            myChart.setOption(option);
            myChart.on('click', { seriesIndex: 0 }, function (params) {
                drillDown(params.data.id);
            });
            function drillDown(targetNodeId) {
                displayRoot = stratify();
                if (targetNodeId != null) {
                    displayRoot = displayRoot.descendants().find(function (node) {
                        return node.data.id === targetNodeId;
                    });
                }
                // A trick to prevent d3-hierarchy from visiting parents in this algorithm.
                displayRoot.parent = null;
                myChart.setOption({
                    dataset: {
                        source: seriesData
                    }
                });
            }
            // Reset: click on the blank area.
            myChart.getZr().on('click', function (event) {
                if (!event.target) {
                    drillDown();
                }
            });
        }

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
	}
	
	$scope.country();
	
	
	$scope.IsInfoShow = false;
	
	$scope.IsKeywordShow = false;
	
	$scope.queryNumber();
	
	$scope.StartYear = 100;
	$scope.EndYear = 110;
	
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
	
	
	$scope.changeSelectAllNone = function() {			
		angular.forEach($scope.InfoList, function(item) {			
			item.Flag = false;								
			});	
		angular.forEach($scope.keywordList, function(item) {			
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
	    	$scope.changeSelectAllNone();
			$scope.option =0;
	    	$(".su_healthy").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_other").removeClass("it_color");
		    $(".su_info").removeClass("it_color");
	    }
	    else if (index == 1 ){
	    	$scope.infoSelectAll= false;
			$scope.InfoList = InfoData;
			$scope.changeSelectAllNone();
			$scope.option =1 ;
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
			$(".su_info").removeClass("it_color");
		    $(".su_safe").removeClass("it_color");
		    $(".su_healthy").removeClass("it_color");
		    $(".su_livelihood").removeClass("it_color");
		    $(".su_power").removeClass("it_color");
		    $(".su_other").addClass("it_color");	
		}
	}
	$scope.query =function(){
		
		var choose = false ;
		$scope.keywordList.forEach(function (node) {
			
			if (node.Flag){
				console.log(node.Flag);
				choose = node.Flag
			}			
		})
		
		if(choose){
			console.log($scope.InfoList);
			$scope.queryForm();
		}else {
			alert('請輸入關鍵字')
		}
	}
	
	
	$scope.queryForm = function() {
		$("#imgLoading").show();


		var request = {		
				classSubList : $scope.InfoList,
				keywordList : $scope.keywordList
		};
		$http.post('./c01/drawNetwork', request, csrf_config).then(function(response) {
			
			$scope.Connect = response.data.datatable;	
			$scope.relation();
			

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
	
	
	//取得關鍵字
	$scope.getKeyword = function(){
		
		
		
		$("#imgLoading").show();


		var request = {		
				classSubList : $scope.InfoList
		};
		console.log(request);
		$http.post('./c01/queryKeywordData', request, csrf_config).then(function(response) {
			$scope.keywordList = response.data.datatable;	
			
			$scope.IsKeywordShow = true;
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
		
		console.log($scope.InfoList);
	}

		
	
	
	
	
	
}
	



