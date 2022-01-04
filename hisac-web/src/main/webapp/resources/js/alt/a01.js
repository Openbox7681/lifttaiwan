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
	
	//輔助資料分析 成果分析 國際網格表現 現職分析
	//選項按鈕
	$scope.isSupport = false;
	$scope.isResult = false;
	$scope.isInter = false;
	$scope.isAnalysis = false
	
	$scope.inlineRadioOptions =1;
	
	$scope.queryNumber = function() {
        $("#loadingActivity").fadeIn("slow");
        	
        let currMainSubject;
        let currSubSubject;
       
        
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
	
	
	$scope.getCountry = function(){
		var request = {
				
		};
		$http.post('./common/getAllCountry', request, csrf_config).then(function(response) {
			$scope.CountryList =response.data.Data
			
			
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
	
	$scope.getCountry();
	
	
	
	$scope.IsInfoShow = false;
	
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
	
	//輔助資料呈現畫面切換
	$scope.supportSwitch = function(index){
		if(index ==1){
			$scope.isSupport1 = true;
			$scope.isSupport2 = false;
			$scope.isSupport3 = false;
			$scope.drawPlan();
		}else if (index == 2) {
			$scope.isSupport1 = false;
			$scope.isSupport2 = true;
			$scope.isSupport3 = false;
			$scope.drawPlanCountry();
		}else if(index == 3){
			$scope.isSupport1 = false;
			$scope.isSupport2 = false;
			$scope.isSupport3 = true;
		}
	}
	
	$scope.query =function(){
		console.log($scope.inlineRadioOptions)
		if ($scope.inlineRadioOptions == 1 ){
			$scope.isSupport = true;
			$scope.isResult = false;
			$scope.isInter = false;
			$scope.isAnalysis = false;
			
			$scope.isSupport1 = true;
			$scope.isSupport2 = false;
			$scope.isSupport3 = false;

			
			$scope.queryForm();
			
			$scope.queryCountry();
		}else if ($scope.inlineRadioOptions == 2){
			$scope.isSupport = false;
			$scope.isResult = true;
			$scope.isInter = false;
			$scope.isAnalysis = false;
		}else if ($scope.inlineRadioOptions == 3){
			$scope.isSupport = false;
			$scope.isResult = false;
			$scope.isInter = true;
			$scope.isAnalysis = false;
		}else if ($scope.inlineRadioOptions == 4){
			$scope.isSupport = false;
			$scope.isResult = false;
			$scope.isInter = false;
			$scope.isAnalysis = true;
		}
		
		
	}
	
	$scope.drawPlan = function(){
		document.getElementById("a1").removeAttribute('_echarts_instance_');
		
		var dom = document.getElementById("a1");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {},
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%'
            },
            xAxis: [
                {
                    type: 'category',
                    data:  $scope.ClassDataDrawPlan
                    ,
                    show :false

                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                
                {
                    name: '盤古開天',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.OpenDataDrawPlan 
                },
                {
                    name: '國合PI',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.piDataDrawPlan
                
                },
                {
                    name: '短期訪問學者',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.shortDataDrawPlan
                },
                {
                    name: '龍門計畫主持人',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.dragonDataDrawPlan 
                },
                {
                    name: '政策邀訪學者',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.policyDataDrawPlan 
                },
                {
                    name: '千里馬申請人',
                    type: 'bar',
                    stack: 'Ad',
                    emphasis: {
                        focus: 'series'
                    },
                    data: $scope.horseDataDrawPlan
                }
               
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
	}
	
	
	
	$scope.drawPlanCountry = function(){
		console.log("test")
		
		document.getElementById("a1").removeAttribute('_echarts_instance_');
		
		var dom = document.getElementById("a1");
        var myChart = echarts.init(dom);
        var app = {};

        var option;

        option= {
            title: {
                text: 'A-2補助人數(依領域及國家)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
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
                data: $scope.CountryDataDraw 
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '盤古開天',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.OpenData
                },
                {
                    name: '國合PI',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.piDataDraw 
                },
                {
                    name: '短期訪問學者',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.piDataDraw 
                },
                {
                    name: '龍門計畫主持人',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.dragonDataDraw
                },
                {
                    name: '政策邀訪學者',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.policyDataDraw
                },
                {
                    name: '千里馬申請人',
                    type: 'line',
                    stack: 'Total',
                    data: $scope.horseDataDraw
                }
            ]
        };

        if (option && typeof option === 'object') {
        	myChart.setOption(option,true);
        }
	}
	
	
	$scope.queryCountry = function() {
		var request = {
				startYear : $scope.StartYear,
				endYear : $scope.EndYear,
				classSubList : $scope.InfoList,
				countryList : $scope.CountryList,
				classMainOption : $scope.option
			
		};
		$http.post('./common/queryCountryData', request, csrf_config).then(function(response) {
			$scope.CountryData = response.data.formData;
			$scope.CountryDataDraw = response.data.countryData 
			$scope.OpenData = response.data.openData 
			$scope.piDataDraw = response.data.piData 
			$scope.shortDataDraw = response.data.shortData 
			$scope.dragonDataDraw = response.data.dragonData 
			$scope.policyDataDraw = response.data.policyData 
			$scope.horseDataDraw = response.data.horseData

			
			
		})

		
		
		
	}
	
	$scope.queryForm = function() {

		var request = {
				startYear : $scope.StartYear,
				endYear : $scope.EndYear,
				classSubList : $scope.InfoList,
				countryList : $scope.CountryList,
				classMainOption : $scope.option
			
		};
		$http.post('./common/queryClassSubData', request, csrf_config).then(function(response) {
			
			$scope.formData = response.data.formData;
			
			$scope.ClassDataDrawPlan = response.data.classData
			$scope.OpenDataDrawPlan = response.data.openData 
			$scope.piDataDrawPlan = response.data.piData 
			$scope.shortDataDrawPlan = response.data.shortData 
			$scope.dragonDataDrawPLan = response.data.dragonData 
			$scope.policyDataDrawPLan = response.data.policyData 
			$scope.horseDataDraw = response.data.horseData
			
			
			
			$scope.drawPlan();

	
			
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
	
	
	
	$scope.eventInitialize = function() {
		currMainSubject= '';
	    currSubSubject = ''

		
		console.log('test');
	    const detailDom = document.getElementById('main-detail').children;
	    for(var i = 0; i < detailDom.length; i++){
	        const target = detailDom[i];
	        target.onclick = () => {
	            $scope.openSubSubject(target.dataset.index);
	        };
	    }
	};
	
//	$scope.eventInitialize();
	
	

	
	
	


	
    
    
   
}

$(document).ready(function() {
    $('#tabNotifyStep2').removeClass('active');
    $('#tabNotifyStep3').removeClass('active');
    $('#tabNotifyStep4').removeClass('active');
    $('#tabNotifyStep5').removeClass('active');
    $('#tabNotifyStep6').removeClass('active');
}

)