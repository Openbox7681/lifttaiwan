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
		
	}
	
	$scope.connect();
	
	

	
}