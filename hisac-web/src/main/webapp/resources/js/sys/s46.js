var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);


function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {

	$scope.allitems = [];
	
	$scope.changeOrg = function() {
		$scope.queryData();
	}
	
	
	
		
	$scope.queryYearData = function() {


    	var request = {
    			
      
        };
    	//console.log("request="+JSON.stringify(request));
		$http.post('./api/s46/year/query', request, csrf_config).then(function(response) {
			$scope.YearList = response.data.datatable;
			console.log("YearList="+JSON.stringify(response.data.datatable));
			console.log($scope.YearList);
			$scope.QueryYear = $scope.YearList[0].Year;
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
	$scope.queryYearData();
	
	
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();


		var request = {
				Year : $scope.QueryYear
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/s46/query', request, csrf_config).then(function(response) {
			
			//console.log("datatable="+JSON.stringify(response.data.datatable));
			$scope.OrgSecurityLevelList = response.data.datatable;

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
	$scope.queryData();

	$scope.changeSelectAllorNone = function() {
		angular.forEach($scope.allitems, function(item) {
				item.Flag = $scope.selectSelAllorNone;
		});
	}
	
	
	$scope.updateData = function() {

		var Year=null;
		if($scope.QueryOrgId!=null)
			Year=$scope.QueryYear;
		
			var request = {
				Year :Year,	
				OrgSecurityLevelList : $scope.OrgSecurityLevelList
			};
			
			console.log("111request="+JSON.stringify(request));
			$http
					.post('./api/s46/update', request, csrf_config)
					.then(
							function(response) {
								if (response.data.success) {
									$scope.queryData($scope.currentPage);
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-success',
											}
										},
										callback: function() {
											
										}
									});
								} else {
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-danger',
											}
										},
										callback: function() { }
									});
								}
							}).catch(function() {
								bootbox.alert({
									message : globalUpdateDataFail,
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
											className : 'btn-danger',
										}
									},
									callback: function() { }
								});
							}).finally(function() { });
	 };
						
}