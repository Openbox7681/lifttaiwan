var myApp = angular.module('myApp', [ 'bw.paging', 'ngSanitize', 'ui.toggle' ]).controller('getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

myApp.filter('BrHtml',function(){
    return function(input){
    	if (input) {
    		input = input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />');    		
    		return input.split('<br />')
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
	
	$scope.queryForm = function() {

		var request = {
			
		};
		$http.post('./common/p01/queryForm', request, csrf_config).then(function(response) {
			
			$scope.formData = response.data.formData;
			
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
	$scope.queryForm();
	
$scope.mountNode = function(){
    	
    	var request= {
    			
    	};
    	
    	$http.post('./common/queryMap', request, csrf_config).then(function(response) {
			
    		var userData = response.data.mapData;
    		    	        

		
    	
    	
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
    	        
    	        
    	        
    	        $.getJSON('../resources/js/world.geo.json', function(mapData) {
    	          var chart = new G2.Chart({
    	            container: 'mountNode',
    	            forceFit: true,
    	            height: 700,
    	            padding: [50, 100],
    	            autoFit: true
    	          })
    	          chart.tooltip({
    	            showTitle: false,
    	           
    	          });
    	          // 同步度量
    	          chart.scale({
    	            longitude: {
    	              sync: true
    	            },
    	            latitude: {
    	              sync: true
    	            }
    	          });
    	          chart.axis(false);
    	          chart.legend(true);
    	        
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
    	            total: {
      	              alias: '總合'
      	            },
    	   
    	            value1: {
    	              alias: '資訊及數位相關產業'
    	            },
    	            value2: {
    	              alias: '綠電及再生能源產業'
    	            },
    	            value3: {
    	              alias: '臺灣精準健康戰略產業'
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
    	            .color('trend', ['#ffffff', '#0A61D7'])
    	            .tooltip('name*total*value1*value2*value3*value4*value5*value6')
    	            .animate({
    	              leave: {
    	                animation: 'fade-out'
    	              }
    	            });
    	          chart.render()
    	        })
    	        
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
    
    $scope.mountNode();
	
	
	
	
	
	
	
	
	
	

		

	
};