var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $http, $window) {

	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 5;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = false;

	$scope.maxRowsChange = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryData();
	};
	
	$scope.maxRowsChangeInit = function() {
		$scope.start = 0;
		$scope.currentPage = 1;
		$scope.queryDataInit();
	};
	// Paging End
	
	// Query Data Start
	$scope.queryData = function(page) {
		
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		var checkbox1 = document.getElementsByName("checkbox1");
		var checkbox2 = document.getElementsByName("checkbox2");
		
		var checkedValue1="";
		var checkedValue2="";
        var count1 = 0;
        var count2 = 0;

		for(var i=0;i<checkbox1.length;i++){
            if(checkbox1[i].checked){
				if(count1==0){
					checkedValue1 = checkbox1[i].value;
            	}else{
            		checkedValue1 = checkedValue1 + "," + checkbox1[i].value;
				}
				count1 = count1 + 1;
            }
       }
		for(var i=0;i<checkbox2.length;i++){
            if(checkbox2[i].checked){
				if(count2==0){
					checkedValue2 = checkbox2[i].value;
            	}else{
            		checkedValue2 = checkedValue2 + "," + checkbox2[i].value;
				}
				count2 = count2 + 1;
            }
       }
		
		var country = checkedValue1;
		var classSub = checkedValue2;
		
		if(country == '全部') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		console.log(checkbox1);

		
		
		console.log(country);
		console.log(classSub);

		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			country : country,
			classSub : classSub
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};

		$http.post('./query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.alltags = response.data.tagtable;
//			$scope.total = response.data.total;
//			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
//			$scope.pageRows = $scope.total % $scope.maxRows;
			
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
		
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
	// Query Data End
	
	// Query Data Start
	$scope.mqueryData = function(page) {
		
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		var checkbox1 = document.getElementsByName("checkbox3");
		var checkbox2 = document.getElementsByName("checkbox4");
		
		var checkedValue1="";
		var checkedValue2="";
        var count1 = 0;
        var count2 = 0;

		for(var i=0;i<checkbox1.length;i++){
            if(checkbox1[i].checked){
				if(count1==0){
					checkedValue1 = checkbox1[i].value;
            	}else{
            		checkedValue1 = checkedValue1 + "," + checkbox1[i].value;
				}
				count1 = count1 + 1;
            }
       }
		for(var i=0;i<checkbox2.length;i++){
            if(checkbox2[i].checked){
				if(count2==0){
					checkedValue2 = checkbox2[i].value;
            	}else{
            		checkedValue2 = checkedValue2 + "," + checkbox2[i].value;
				}
				count2 = count2 + 1;
            }
       }
		
		var country = checkedValue1;
		var classSub = checkedValue2;
		
		if(country == '全部') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		console.log(checkbox1);

		
		
		console.log(country);
		console.log(classSub);

		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			country : country,
			classSub : classSub
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};

		$http.post('./query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.alltags = response.data.tagtable;
//			$scope.total = response.data.total;
//			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
//			$scope.pageRows = $scope.total % $scope.maxRows;
			
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
		
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
	// Query Data End
	
	
	
	
	// Query Data Start
	$scope.queryDataInit = function(page) {
		$("#imgLoadingInit").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		
		var url = location.href;
		var ary = url.split("/");
		var country = ary[ary.length-1];
		country = unescape(atob(country));
		
		if(country == '過去10年中的國際優秀人才和研究成果') {
			country = null
		}else if(country == '台灣') {
			country = '台灣,中華民國'
		}
		
		var request = {
			start : 0,
			maxRows : 5,
			country : country,
			classSub : null
		};
		
		var header = JSON.parse('{"'
				+ $("meta[name='_csrf_header']").attr("content") + '":"'
				+ $("meta[name='_csrf']").attr("content") + '"}');
		var csrf_config = {
			withCredentials : true,
			crossDomain : true,
			headers : header
		};
	
		$http.post('./query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.alltags = response.data.tagtable;
//			$scope.total = response.data.total;
//			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
//			$scope.pageRows = $scope.total % $scope.maxRows;
//			
//			if ($scope.pageRows != 0)
//				$scope.maxPages++;
//			$scope.returnTotal = true;
			
			country = "#" + country
			
			if(country == "#null") {
				$("#台灣").attr("checked", "true");
				$("#日本").attr("checked", "true");
				$("#中國").attr("checked", "true");
				$("#越南").attr("checked", "true");
				$("#馬來西亞").attr("checked", "true");
				$("#俄羅斯").attr("checked", "true");
				$("#印度").attr("checked", "true");
				$("#德國").attr("checked", "true");
				$("#法國").attr("checked", "true");
				$("#美國").attr("checked", "true");
			}else if(country == "#台灣,中華民國"){
				$("#台灣").attr("checked", "true");
			}else {
				$(country).attr("checked", "true");
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
			$("#imgLoadingInit").hide();
		});
	};
	$scope.queryDataInit();
	// Query Data End
	
	
	$scope.clearData = function() {
		$scope.country = null;
	};
	$scope.clearData();
}