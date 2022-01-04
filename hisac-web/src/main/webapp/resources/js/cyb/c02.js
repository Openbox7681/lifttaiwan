var myApp = angular.module('myApp', ['ngCookies', 'bw.paging', 'ui.toggle']).controller(
    'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

//click scholars_btn show-i_top_scholars-pages
$(function() {                       
  $("#scholar_click").click(function() { 
    $(".scholars_chart").addClass("show_chart");
  });
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
	
	
	$scope.connect = function(){
		
		 var dom = document.getElementById("connect");
         var myChart = echarts.init(dom);
         var app = {};

         var option;

         var ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';

         myChart.showLoading();
         $.getJSON(ROOT_PATH + '/data/asset/data/les-miserables.json', function (graph) {
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
         
 	    $(".scholars_chart").addClass("show_chart");

		
       
	}
	
	
	//六大領域資料 start
	
	
	$scope.IsInfoShow = false;
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
 	    $(".scholars_chart").removeClass("show_chart");
		$scope.queryForm()
		$scope.isSupport = true;

		
		
	}
	
	
	$scope.queryForm = function() {
		$("#imgLoading").show();


		var request = {		
				classSubList : $scope.InfoList
		};
		console.log(request);
		$http.post('./c02/queryTopres20Data', request, csrf_config).then(function(response) {
			$scope.Topres20Data = response.data.datatable;	

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