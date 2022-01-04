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
		document.getElementById("cubelink").removeAttribute('_echarts_instance_');

		var dom = document.getElementById("cubelink");
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
	
	
//	$scope.cubelink();
	
	
	
	
	
	$scope.pie1 = function(){
		document.getElementById("pie1").removeAttribute('_echarts_instance_');
		
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
                left: 'center',
                show: false
            },
            series: [
                {
                    name: '研習機構',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    center : ['50%', '50%'],
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
                    data: $scope.Pie1table
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
		
		
	}
	
	
	
	
	
	$scope.pie2 = function(){
		document.getElementById("pie2").removeAttribute('_echarts_instance_');
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
                left: 'center',
                show: false
            },
            series: [
                {
                    name: '頂尖機構佔比',
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
                    data: $scope.Pie2table
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
	}
	
	
	
	
	
	
	//六大領域資料 start
	
	
	$scope.IsInfoShow = false;
	
	//圖表是否呈現
	$scope.isSupport = false;
	$scope.isDraw = false ;
	
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
 	   console.log($scope.InfoList);
 	   $scope.queryForm()
 	   $scope.isSupport = true;
 	   
	}
	
	$scope.queryForm = function() {
		$("#imgLoading").show();
		$scope.isSupport = false

		var request = {		
				classSubList : $scope.InfoList
		};
		console.log(request);
		$http.post('./c03/queryTopad50Data', request, csrf_config).then(function(response) {
			$scope.Topad50Data = response.data.datatable;	
			$scope.Cube50Data = response.data.cubetable;	
			$scope.Pie1table = response.data.pie1table;	
			$scope.Pie2table = response.data.pie2table;	

			
			
		 	$scope.cubelink();
		 	$scope.pie1();
		 	$scope.pie2();



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
	

	
	 

}