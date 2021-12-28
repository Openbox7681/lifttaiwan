var myApp = angular.module('myApp', [ 'bw.paging', 'ngSanitize' ]).controller('getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $rootScope, $http, $window) {
	
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
	                left: 'center',
	                width: "70%",
	                itemGap: 21
	            },
	            series: [
	                {
	                    type: 'pie',
	                    radius: ['20%', '50%'],
	                    avoidLabelOverlap: false,
	                    label: {
	                        show: false,
	                        position: 'center'
	                    },
	                    emphasis: {
	                        label: {
	                            show: false,
	                            fontSize: '30',
	                            fontWeight: 'bold'
	                        }
	                    },
	                    labelLine: {
	                        show: false
	                    },
	                    data: [
	                        { value: 119, name: 'University of California' },
	                        { value: 52, name: 'Harvard University' },
	                        { value: 49, name: 'University of Harvard' },
	                        { value: 49, name: 'University of Texas' },
	                        { value: 45, name: 'University of Washington' },
	                        { value: 39, name: 'Stanford University' },
	                        { value: 36, name: 'Georgia Institute of Technology' },
	                        { value: 30, name: 'California Institute of Technology' },
	                        { value: 29, name: 'University of Illinois' },
	                        { value : 21, name : "University of Michigan"}
	                        

	                        
	                        
	                        
	                    ]
	                }
	            ]
	        };

	        if (option && typeof option === 'object') {
	            myChart.setOption(option);
	        }

	    }
	    
	    $scope.overseas_institutions()
	
	
	
	
};