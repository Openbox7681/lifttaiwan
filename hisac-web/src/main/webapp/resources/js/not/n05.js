var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging' ]).controller(
		'getAppController', getAppController);


function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location) {

	$scope.allitems = [];


	
	$scope.changeMessageGroup = function() {
		$scope.selectSelAllorNone=null;
		$scope.queryData();
	}
	
	$scope.queryMessageGroupData = function() {
    	var request = {
    			start: 0,
                maxRows: 0,
                sort: "id",
                dir: false
        };
    	//console.log("request="+JSON.stringify(request));
		$http.post('./api/n05/messagegroup/query', request, csrf_config).then(function(response) {
			$scope.MessageGroupList = response.data.datatable;
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
	$scope.queryMessageGroupData();
	
	
	$scope.queryData = function(page) {

		var MessageGroupId=null;
		if($scope.QueryMessageGroup!=null)
			MessageGroupId=$scope.QueryMessageGroup.Id;
		
		var request = {
            MessageGroupId :MessageGroupId
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/n05/query', request, csrf_config).then(function(response) {
			
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
		var MessageGroupOrg=null; 
		if ($scope.allitems!= "")
			MessageGroupOrg=$scope.allitems.MessageGroupOrg;
		
		
			var request = {
				MessageGroupOrg : $scope.allitems
			};
			
			//console.log("request="+JSON.stringify(request));
			$http
					.post('./api/n05/update/', request, csrf_config)
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