var myApp = angular.module('myApp', [ ]).controller(
		'getAppController', getAppController);
angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

function getAppController($rootScope, $scope, $http) {
	$scope.quertData = function() {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {};
		$http.post('./api/ires/org/getAgencyCertificateByAgncyCode', request, csrf_config).then(function(response) {
			
			if (response.data.success == true) {
				bootbox.hideAll();

			
			//取得APIKEY資料
			$scope.orgCode = response.data.data.orgCode;
			$scope.orgName = response.data.data.orgName;
			$scope.agencyCode = response.data.data.AgencyCode;
			$scope.agencyKey = response.data.data.AgencyKey;
			$scope.vaildYears = response.data.data.VaildYears;
			$scope.applyDate = response.data.data.ApplyDate;
			$scope.expiryDate = response.data.data.ExpiryDate;
			$scope.dataState = response.data.data.DataState;
			
			console.log($scope.dataState);
			}
			
			 else {
				 $scope.orgCode = response.data.data.orgCode;
				$scope.orgName = response.data.data.orgName;
					bootbox.hideAll();
					bootbox.alert({
						message : response.data.msg,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-danger'
							}
						},
						callback: function() {	}
					});
				}

			
			
		}).catch(function() {
			bootbox.hideAll();
			bootbox.alert({
				message : response.data.msg,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() {	}
			});
		});
	}
	$scope.quertData();
	
	
	$scope.updateData =  function(){
		bootbox.confirm(
				{
					
					message: globalApiKeySure,
					buttons: {
						confirm: {
							label : '<i class="fas fa-fw fa-check"></i>' + btnSure,
							className : 'btn-success'
						},
						cancel: {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-default'
						}
					},
					callback: function(result){
						if (result){
							
							bootbox.dialog({
								closeButton: false,
								message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
							});
							
							var request = {
									orgCode : $scope.orgCode
								};
							
							$http.post('./api/ires/org/update', request, csrf_config).then(function(response) {
								if (response.data.success == true) {
									bootbox.hideAll();
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-primary',
											}
										},
										callback: function() { 
											
											$scope.quertData();
											
											
										}
									});
								} else {
									bootbox.hideAll();
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-danger'
											}
										},
										callback: function() {	}
									});
								}
							}).catch(function() {
								bootbox.hideAll();

								bootbox.alert({
									message : globalApiKeySureFail,
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
											className : 'btn-danger',
										}
									},
									callback: function() { }
								});
							}).finally(function() { });
							
							
							
							
						}
						
					}
					
				}
				
		
		
		
		)
	}
	
	
	
	
	
	
	
	
	
	
	
//	$scope.updateData = function() {
//		bootbox.dialog({
//			closeButton: false,
//			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
//		});
//		var request = {
//			orgCode : $scope.orgCode
//		};
//		$http.post('./api/ires/org/update', request, csrf_config).then(function(response) {
//			if (response.data.success == true) {
//				bootbox.hideAll();
//				bootbox.alert({
//					message : response.data.msg,
//					buttons : {
//						ok : {
//							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//							className : 'btn-primary',
//						}
//					},
//					callback: function() { 
//						
//						$scope.quertData();
//						
//						
//					}
//				});
//			} else {
//				bootbox.hideAll();
//				bootbox.alert({
//					message : response.data.msg,
//					buttons : {
//						ok : {
//							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//							className : 'btn-danger'
//						}
//					},
//					callback: function() {	}
//				});
//			}
//		}).catch(function() {
//			bootbox.hideAll();
//			bootbox.alert({
//				message : globalUpdateDataFail,
//				buttons : {
//					ok : {
//						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
//						className : 'btn-danger'
//					}
//				},
//				callback: function() {	}
//			});
//		});
//	}
}