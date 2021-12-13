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
	
	$scope.queryNumber = function() {
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
		});
	};
	$scope.queryNumber();
	
    var items = [];
    for (var i = 0; i < 5; i++) {
        items.push({
            Date: "　",
            Title: "",
            Id: ""
        });
    }
    
    var ana_items=[];
    /*
    for (var i = 0; i < 12; i++) {
    		ana_items.push({
            Date: "　",
            Title: "",
            Id: ""
        });
    }
    */

    for (var i = 0; i < 5; i++) {
        ana_items.push({
        Date: "test",
        Title: "",
        Id: "1"
    });
        
}
    console.log(ana_items);
    
    var my_json = [{
    	    name : 'Canada',
    	    value1 : 42,
    	    value2 : 17
    	},
    	{
    	    name : 'Chile',
    	    value1 : 0,
    	    value2 : 11
    	}
    
    ]
    
    for (var i =0 ; i <my_json.length ; i ++){
    	my_json[i]["trend"] = my_json[i]["value1"] + my_json[i]["value2"];
    }
    

   
    
    

    
    
    
    $scope.news = items;
    $scope.activity = items;
    $scope.ana = ana_items;
    $scope.malwares = items;
    $scope.secbuzzer = items;
    $scope.inf = items;
    
    
    $scope.maxima = function(){
        var dom = document.getElementById("maxima");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        const data = [
        [
            [0.44235026836395264, 1.0833333730697632, 3562, '晶圓代工', '千里馬計畫優勢'],
            [0.36915940046310425, 1, 4801, '晶圓封測', '千里馬計畫優勢'],
            [0.3624591827392578, 0.7777777910232544, 1461, '智慧運輸', '千里馬計畫優勢'],
        ]
        
        
        
        ];
        option = {
            backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [
                {
                    offset: 0,
                    color: '#f7f8fa'
                }
//                ,
//                {
//                    offset: 1,
//                    color: '#cdd0d5'
//                }
            ]),
            title: {
                text: '',
                left: '5%',
                top: '3%'
            },
            legend: {
                right: '10%',
                top: '3%',
                data: ['千里馬計畫優勢' ]
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
            series: [
                {
                    name: '千里馬計畫優勢',
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
                                return param.data[3] +"\n" + "數量 : " + param.data[2];
                            },
                            position: 'top'
                        }
                    },
                    itemStyle: {
                        shadowBlur: 10,
                        shadowColor: 'rgba(120, 36, 50, 0.5)',
                        shadowOffsetY: 5,
                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
                            {
                                offset: 0,
                                color: 'rgb(251, 118, 123)'
                            },
                            {
                                offset: 1,
                                color: 'rgb(204, 46, 72)'
                            }
                        ])
                    }
                }
//                ,
//                {
//                    name: '臺灣精準健康戰略產業',
//                    data: data[1],
//                    type: 'scatter',
//                    symbolSize: function (data) {
//                        return Math.sqrt(data[2]) / 2;
//                    },
//                    emphasis: {
//                        focus: 'series',
//                        label: {
//                            show: true,
//                            formatter: function (param) {
//                                return param.data[3] +"\n" + "數量 : " + param.data[2];
//                            },
//                            position: 'top'
//                        }
//                    },
//                    itemStyle: {
//                        shadowBlur: 10,
//                        shadowColor: 'rgba(25, 100, 150, 0.5)',
//                        shadowOffsetY: 5,
//                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
//                            {
//                                offset: 0,
//                                color: 'rgb(129, 227, 238)'
//                            },
//                            {
//                                offset: 1,
//                                color: 'rgb(25, 183, 207)'
//                            }
//                        ])
//                    }
//                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    }
    
    $scope.maxima()

    
    
    
    $scope.overseas_institutions = function(){
        var dom = document.getElementById("overseas_institutions");
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
    
    $scope.overseas_institutions()
    
    
    $scope.mountNode = function(){
    	
    	/* 		console.log( ${pageContext.request.contextPath} );
    	 */        
    	 		const countryContinentData = `
    	        Afghanistan	亚洲
    	        Angola	非洲
    	        Albania	欧洲
    	        United Arab Emirates	亚洲
    	        Argentina	南美洲
    	        Armenia	亚洲
    	        French Southern and Antarctic Lands	欧洲
    	        Australia	大洋洲 
    	        Austria	欧洲
    	        Azerbaijan	亚洲
    	        Burundi	非洲
    	        Belgium	欧洲
    	        Benin	非洲
    	        Burkina Faso	非洲
    	        Bangladesh	亚洲
    	        Bulgaria	欧洲
    	        The Bahamas	北美
    	        Bosnia and Herzegovina	欧洲
    	        Belarus	欧洲
    	        Belize	北美
    	        Bermuda	南美洲
    	        Bolivia	南美洲
    	        Brazil	南美洲
    	        Brunei	亚洲
    	        Bhutan	亚洲
    	        Botswana	非洲
    	        Central African Republic	非洲
    	        Canada	北美
    	        Switzerland	欧洲
    	        Chile	南美洲
    	        China	亚洲
    	        Ivory Coast	非洲
    	        Cameroon	非洲
    	        Democratic Republic of the Congo	非洲
    	        Republic of the Congo	非洲
    	        Colombia	南美洲
    	        Costa Rica	北美
    	        Cuba	南美洲
    	        Northern Cyprus	非洲
    	        Cyprus	亚洲
    	        Czech Republic	欧洲
    	        Germany	欧洲
    	        Djibouti	非洲
    	        Denmark	欧洲
    	        Dominican Republic	南美洲
    	        Algeria	非洲
    	        Ecuador	南美洲
    	        Egypt	非洲
    	        Eritrea	非洲
    	        Spain	欧洲
    	        Estonia	欧洲
    	        Ethiopia	非洲
    	        Finland	欧洲
    	        Fiji	大洋洲 
    	        Falkland Islands	南美洲
    	        France	欧洲
    	        Gabon	非洲
    	        United Kingdom	欧洲
    	        Georgia	亚洲
    	        Ghana	非洲
    	        Guinea	非洲
    	        Gambia	非洲
    	        Guinea Bissau	非洲
    	        Equatorial Guinea	非洲
    	        Greece	欧洲
    	        Greenland	北美
    	        Guatemala	北美
    	        French Guiana	南美洲
    	        Guyana	南美洲
    	        Honduras	北美
    	        Croatia	欧洲
    	        Haiti	南美洲
    	        Hungary	欧洲
    	        Indonesia	亚洲
    	        India	亚洲
    	        Ireland	欧洲
    	        Iran	亚洲
    	        Iraq	亚洲
    	        Iceland	欧洲
    	        Israel	亚洲
    	        Italy	欧洲
    	        Jamaica	南美洲
    	        Jordan	亚洲
    	        Japan	亚洲
    	        Kazakhstan	亚洲
    	        Kenya	非洲
    	        Kyrgyzstan	亚洲
    	        Cambodia	亚洲
    	        South Korea	亚洲
    	        Kosovo	#N/A
    	        Kuwait	亚洲
    	        Laos	亚洲
    	        Lebanon	亚洲
    	        Liberia	非洲
    	        Libya	非洲
    	        Sri Lanka	亚洲
    	        Lesotho	非洲
    	        Lithuania	欧洲
    	        Luxembourg	欧洲
    	        Latvia	欧洲
    	        Morocco	非洲
    	        Moldova	欧洲
    	        Madagascar	非洲
    	        Mexico	北美
    	        Macedonia	欧洲
    	        Mali	非洲
    	        Myanmar	亚洲
    	        Montenegro	欧洲
    	        Mongolia	亚洲
    	        Mozambique	非洲
    	        Mauritania	非洲
    	        Malawi	非洲
    	        Malaysia	亚洲
    	        Namibia	非洲
    	        New Caledonia	大洋洲 
    	        Niger	非洲
    	        Nigeria	非洲
    	        Nicaragua	北美
    	        Netherlands	欧洲
    	        Norway	欧洲
    	        Nepal	亚洲
    	        New Zealand	大洋洲 
    	        Oman	亚洲
    	        Pakistan	亚洲
    	        Panama	北美
    	        Peru	南美洲
    	        Philippines	亚洲
    	        Papua New Guinea	大洋洲 
    	        Poland	欧洲
    	        Puerto Rico	南美洲
    	        North Korea	亚洲
    	        Portugal	欧洲
    	        Paraguay	南美洲
    	        Qatar	亚洲
    	        Romania	欧洲
    	        Russia	欧洲
    	        Rwanda	非洲
    	        Western Sahara	非洲
    	        Saudi Arabia	亚洲
    	        Sudan	非洲
    	        South Sudan	非洲
    	        Senegal	非洲
    	        Solomon Islands	大洋洲 
    	        Sierra Leone	非洲
    	        El Salvador	北美
    	        Somaliland	非洲
    	        Somalia	非洲
    	        Republic of Serbia	欧洲
    	        Suriname	南美洲
    	        Slovakia	欧洲
    	        Slovenia	欧洲
    	        Sweden	欧洲
    	        Swaziland	非洲
    	        Syria	非洲
    	        Chad	非洲
    	        Togo	非洲
    	        Thailand	亚洲
    	        Tajikistan	亚洲
    	        Turkmenistan	亚洲
    	        East Timor	亚洲
    	        Trinidad and Tobago	南美洲
    	        Tunisia	非洲
    	        Turkey	亚洲
    	        United Republic of Tanzania	非洲
    	        Uganda	非洲
    	        Ukraine	欧洲
    	        Uruguay	南美洲
    	        United States of America	北美
    	        Uzbekistan	亚洲
    	        Venezuela	南美洲
    	        Vietnam	亚洲
    	        Vanuatu	大洋洲 
    	        West Bank	非洲
    	        Yemen	亚洲
    	        South Africa	非洲
    	        Zambia	非洲
    	        Zimbabwe	非洲
    	        `
    	        const stageData = `
    	        亚洲	3916
    	        欧洲	9033
    	        北美	14198
    	        大洋洲	2853
    	        非洲	432
    	        南美	450
    	        `
    	        	
    	   
    	        	
    	     
    	        const countryContinentMap = Object.fromEntries(countryContinentData
    	          .split('\n')
    	          .filter(i => !!i.trim())
    	          .map(i => i.split('\t')))
    	        
    	        const stageDataMap =  Object.fromEntries(stageData
    	          .split('\n')
    	          .filter(i => !!i.trim())
    	          .map(i => i.split('\t')));
    	        
    	        var userData = [
    	          {
    	            name: 'Russia',
    	            trend: 3916,
    	            value1:1,
    	            value2:2,
    	            value3:3,
    	            value4:4,
    	            value5:5,
    	            value6:6,
    	          },
    	          {
    	            name: 'China',
    	            trend: 10000,
    	            value1:1,
    	            value4:1000,
    	            value5:5,
    	            value6:6,
    	          },
    	          
    	          {
    	              name: 'Taiwan',
    	              trend: 90330,
    	              value1:1,
    	              value2:1,
    	              value3:1,
    	              value4:100,
    	              value5:5,
    	              value6:6,
    	            },
    	          {
    	            name: 'Poland',
    	            trend: 30000,
    	            value1:1,
    	            value2:2,
    	            value3:3,
    	            value4:4,
    	            value5:5,
    	            value6:6,
    	          }
    	        ];
    	        
    	        userData = my_json;
    	        
    	        $.getJSON('../resources/js/world.geo.json', function(mapData) {
    	          var chart = new G2.Chart({
    	            container: 'mountNode',
    	            forceFit: true,
    	            width: 1000,
    	            height: 500,
    	            padding: [0, 0]
    	          })
    	          chart.tooltip({
    	            showTitle: false
    	          })
    	          // 同步度量
    	          chart.scale({
    	            longitude: {
    	              sync: true
    	            },
    	            latitude: {
    	              sync: true
    	            }
    	          })
    	          chart.axis(true)
    	          chart.legend('trend', {
    	            position: 'left'
    	          });
    	        
    	          // 绘制世界地图背景
    	          var ds = new DataSet()
    	          var worldMap = ds.createView('back').source(mapData, {
    	            type: 'GeoJSON'
    	          })
    	          var worldMapView = chart.view()
    	          worldMapView.source(worldMap)
    	          worldMapView.tooltip(false)
    	          worldMapView
    	            .polygon()
    	            .position('longitude*latitude')
    	            .style({
    	              fill: '#fff',
    	              stroke: '#ccc',
    	              lineWidth: 1
    	            })
    	        
    	          // 可视化用户数据
    	        
    	          var userDv = ds
    	            .createView()
    	            .source(userData)
    	            .transform({
    	              geoDataView: worldMap,
    	              field: 'name',
    	              type: 'geo.region',
    	              as: ['longitude', 'latitude']
    	            })
    	            .transform({
    	              type: 'map',
    	              callback: function callback(obj) {
    	        
    	                return obj
    	              }
    	            })
    	          var userView = chart.view()
    	          userView.source(userDv, {
    	            name: {
    	              alias: '國家'
    	            },
    	            trend: {
    	              alias: '加總'
    	            },
    	            value1: {
    	              alias: '資訊及數位相關產業'
    	            },
    	            value2: {
    	              alias: '綠電及再生能源產業'
    	            },
    	            value3: {
    	              alias: '台灣精準健康戰略產業'
    	            },
    	            value4: {
    	              alias: '國防及戰略產業'
    	            },
    	            value5: {
    	              alias: '民生及戰備產業'
    	            },
    	            value6: {
    	              alias: '其他'
    	            }
    	          })
    	          userView
    	            .polygon()
    	            .position('longitude*latitude')
    	            .color('trend', ['#ffffff', '#fa7805'])
    	            .tooltip('name*trend*value1*value2*value3*value4*value5*value6')
    	            .animate({
    	              leave: {
    	                animation: 'fadeOut'
    	              }
    	            })
    	          chart.render()
    	        })
    	
    	
    	
    	
    	
    }
    ;
    
    $scope.mountNode();
    
    
    
    $scope.solar_employment = function(){
    	
    	var dom = document.getElementById("solar_employment");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Installation', 'Manufacturing', 'Sales & Distribution', 'Project Development', 'Other']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['2010', '2012', '2014', '2016', '2017', '2018', '2019']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: 'Installation',
                    type: 'line',
                    stack: 'Total',
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: 'Manufacturing',
                    type: 'line',
                    stack: 'Total',
                    data: [220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name: 'Sales & Distribution',
                    type: 'line',
                    stack: 'Total',
                    data: [150, 232, 201, 154, 190, 330, 410]
                },
                {
                    name: 'Project Development',
                    type: 'line',
                    stack: 'Total',
                    data: [320, 332, 301, 334, 390, 330, 320]
                },
                {
                    name: 'Other',
                    type: 'line',
                    stack: 'Total',
                    data: [820, 932, 901, 934, 1290, 1330, 1320]
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    $scope.solar_employment();
    
    
    
    

    $scope.queryNews = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/news', request, csrf_config).then(function(response) {
            $scope.news = response.data.datatable;
            $scope.newsTotal = response.data.total;
            if ($scope.newsTotal < 5) {
                for (var i = 0; i < 5 - $scope.newsTotal; i++) {
                    $scope.news.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingNews").fadeOut("slow");
        })
    }
    
    $scope.queryMalwares = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/malwares', request, csrf_config).then(function(response) {
            $scope.malwares = response.data.datatable;
            $scope.malwaresTotal = response.data.total;
            if ($scope.newsTotal < 5) {
                for (var i = 0; i < 5 - $scope.newsTotal; i++) {
                    $scope.news.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingMalwares").fadeOut("slow");
        })
    }

    $scope.queryActivity = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/activity', request, csrf_config).then(function(response) {
            $scope.activity = response.data.datatable;
            $scope.activityTotal = response.data.total;
            if ($scope.activityTotal < 5) {
                for (var i = 0; i < 5 - $scope.activityTotal; i++) {
                    $scope.activity.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingActivity").fadeOut("slow");
        })
    }

    $scope.queryAna = function() {    	
        var request = {
            start: 0,
            //maxRows: 12,
            maxRows: 5,
            sort: "incidentReportedTime",
            dir: true,
            IncidentTitle : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/ana', request, csrf_config).then(function(response) {
            $scope.ana = response.data.datatable;
            $scope.anaTotal = response.data.total;
            /*
            if ($scope.anaTotal < 12) {
                for (var i = 0; i < 12 - $scope.anaTotal; i++) {
                    $scope.ana.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
            */
            if ($scope.anaTotal < 5) {
                for (var i = 0; i < 5 - $scope.anaTotal; i++) {
                    $scope.ana.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingAna").fadeOut("slow");
        })
    }


    $scope.querySecBuzzer = function() {    	 
        var request = {
        		keyword : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/secbuzzer', request, csrf_config).then(function(response) {
            $scope.secbuzzer = response.data.datatable;
            $scope.secbuzzerTotal = response.data.total;
            if ($scope.secbuzzerTotal < 5) {
                for (var i = 0; i < 5 - $scope.secbuzzerTotal; i++) {
                    $scope.secbuzzer.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingSecBuzzer").fadeOut("slow");
        })
    }
    
    
    $scope.queryInf = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Keyword : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/inf', request, csrf_config).then(function(response) {
            $scope.inf = response.data.datatable;
            $scope.infTotal = response.data.total;
            if ($scope.infTotal < 5) {
                for (var i = 0; i < 5 - $scope.infTotal; i++) {
                    $scope.inf.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingInf").fadeOut("slow");
        })
    }

    $scope.queryLinks = function() {
        var request = {
            start: 0,
            maxRows: 4,
            sort: "StartDateTime",
            dir: false
        };
        $http.post('./api/p00/query/links', request, csrf_config).then(function(response) {
            $scope.links = response.data.datatable;
            $scope.linksTotal = response.data.total;
        }).finally(function() {
            $("#loadingLinks").fadeOut("slow");
        })
    }

    $scope.queryData = function() {
    		$("#loadingNews").fadeIn();
   	 	$("#loadingActivity").fadeIn();
   	 	$("#loadingAna").fadeIn();
   	 	$("#loadingSecBuzzer").fadeIn();
   	 	$("#loadingMalwares").fadeIn();

    	 	$scope.queryNews();
    	    $scope.queryActivity();
    	    $scope.queryAna();    	   
    	    $scope.querySecBuzzer();
    	    $scope.queryInf();
    	    $scope.queryMalwares();
    }
    
    $scope.queryData();
    $scope.queryLinks();



    $scope.queryInfoDashboard = function() {
        var request = {};
        $http.post('./api/p00/query/info/dashboard', request, csrf_config).then(function(response) {
            $scope.allitems_info = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_info, "2"));
            data.push($scope.getCount($scope.allitems_info, "NHISOC"));
            data.push($scope.getCount($scope.allitems_info, "OTH"));
            data.push($scope.getCount($scope.allitems_info, "HISAC"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config1 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "N-ISAC",
                        "NHISOC",
                        "其他",
                        "自行新增"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '情資來源',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 19,
                		fontColor: '#369'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx1 = document.getElementById("myChart1").getContext("2d");
            var myChart1 = new Chart(ctx1, config1);
            document.getElementById('myLegend1').innerHTML = myChart1.generateLegend();
        }).catch(function() {

        }).finally(function() { });
    };

   

    $scope.queryMessageDashboard = function() {
        var request = {};
        $http.post('./api/p00/query/message/dashboard', request, csrf_config).then(function(response) {
            $scope.allitems_message = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_message, "ANA"));
            data.push($scope.getCount($scope.allitems_message, "DEF"));
            data.push($scope.getCount($scope.allitems_message, "INT"));
            data.push($scope.getCount($scope.allitems_message, "EWA"));
            data.push($scope.getCount($scope.allitems_message, "FBI"));
            data.push($scope.getCount($scope.allitems_message, "OTH"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config4 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "ANA",
                        "DEF",
                        "INT",
                        "EWA",
                        "FBI",
                        "OTH"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '總計',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 15,
                		fontColor: '#369',
                		position: 'bottom'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx4 = document.getElementById("myChart4").getContext("2d");
            var myChart4 = new Chart(ctx4, config4);
            document.getElementById('myLegend4').innerHTML = myChart4.generateLegend();
        }).catch(function() {

        }).finally(function() {});

    };
    $scope.queryMessageDashboard();



    
    $scope.getCount = function(allitems, name) {
        var count = 0;
        angular.forEach(allitems, function(value, key) {
            if (value.Name == name) {
                count = value.Count;
            }
        });
        return count;
    }
    
    $(document).ready(function() {
        Chart.pluginService.register({
            beforeDraw: function(chart) {
                if (chart.config.options.elements.center) {
                    var ctx = chart.chart.ctx;
                    var centerConfig = chart.config.options.elements.center;
                    var fontStyle = centerConfig.fontStyle || 'Arial';
                    var txt = centerConfig.text;
                    var color = centerConfig.color || '#000';
                    var sidePadding = centerConfig.sidePadding || 20;
                    var sidePaddingCalculated = (sidePadding / 100) * (chart.innerRadius * 2)
                    ctx.font = "30px " + fontStyle;
                    var stringWidth = ctx.measureText(txt).width;
                    var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;
                    var widthRatio = elementWidth / stringWidth;
                    var newFontSize = Math.floor(30 * widthRatio);
                    var elementHeight = (chart.innerRadius * 2);
                    var fontSizeToUse = Math.min(newFontSize, elementHeight);
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
                    var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
                    ctx.font = fontSizeToUse + "px " + fontStyle;
                    ctx.fillStyle = color;
                    ctx.fillText(txt, centerX, centerY);
                }
            }
        });
    })
};
