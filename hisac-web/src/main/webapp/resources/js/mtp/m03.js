var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);


function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {

	$scope.allitems = [];


	
	$scope.changeMaintainPlanGroup = function() {
		$scope.selectSelAllorNone=null;
		$scope.queryData();
	}
	
	$scope.queryMaintainPlanGroupData = function() {
    	var request = {
    			start: 0,
                maxRows: 0,
                sort: "id",
                dir: false
        };
    	//console.log("request="+JSON.stringify(request));
		$http.post('./api/m03/maintainPlangroup/query', request, csrf_config).then(function(response) {
			$scope.MaintainPlanGroupList = response.data.datatable;
			//console.log("datatable="+JSON.stringify(response.data.datatable));
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
		}).finally(function() { });
	};
	$scope.queryMaintainPlanGroupData();
	
	
	$scope.queryData = function(page) {

		var MaintainPlanGroupId=null;
		if($scope.QueryMaintainPlanGroup!=null)
			MaintainPlanGroupId=$scope.QueryMaintainPlanGroup.Id;
		
		var request = {
				MaintainPlanGroupId :MaintainPlanGroupId
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/m03/query', request, csrf_config).then(function(response) {
			
			//console.log("datatable="+JSON.stringify(response.data.datatable));
			$scope.allitems = response.data.datatable;

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
		}).finally(function() { });
	};
	$scope.queryData();

	$scope.changeSelectAllorNone = function() {
		
		
		
		angular.forEach($scope.allitems, function(item) {
				item.Flag = $scope.selectSelAllorNone;
		});
	}
	
	
	$scope.updateData = function() {
		var MaintainPlanGroupOrg=null; 
		if ($scope.allitems!= "")
			MaintainPlanGroupOrg=$scope.allitems.MaintainPlanGroupOrg;
		
		
			var request = {
					MaintainPlanGroupOrg : $scope.allitems
			};
			
			//console.log("request="+JSON.stringify(request));
			$http
					.post('./api/m03/update/', request, csrf_config)
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