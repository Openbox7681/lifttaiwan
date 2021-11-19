var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);


function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {

	$scope.allitems = [];
	
	$scope.changeOrg = function() {
		$scope.queryData();
	}
	
		
	$scope.queryOrgData = function() {
    	var request = {
    			start: 0,
                maxRows: 0,
                sort: "name",
                dir: false,
                OrgType:"3"
      
        };
    	//console.log("request="+JSON.stringify(request));
		$http.post('./api/s14/org/query', request, csrf_config).then(function(response) {
			$scope.OrgList = response.data.datatable;
			//console.log("org_datatable="+JSON.stringify(response.data.datatable));
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
	$scope.queryOrgData();
	
	
	
	$scope.queryData = function(page) {

		var OrgId=null;
		if($scope.QueryOrgId!=null)
			OrgId=$scope.QueryOrgId.Id;
		
		var request = {
				OrgId :OrgId
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/s14/query', request, csrf_config).then(function(response) {
			
			//console.log("datatable="+JSON.stringify(response.data.datatable));
			$scope.OrgSignList = response.data.datatable;

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

		var OrgId=null;
		if($scope.QueryOrgId!=null)
			OrgId=$scope.QueryOrgId.Id;
		
			var request = {
				OrgId :OrgId,	
				OrgSignList : $scope.OrgSignList
			};
			
			//console.log("111request="+JSON.stringify(request));
			$http
					.post('./api/s14/update', request, csrf_config)
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