var myApp = angular.module('myApp', ['ng-pros.directive.autocomplete']).controller(
		'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
	
	var myInput = document.getElementById('memberAccount');
	angular.element('myInput').onpaste = function(e) {
	   e.preventDefault();
	 }
});

function getAppController($scope, $http, $window, $timeout) {
	$scope.verifyEmail = false;
	$scope.checkCI = false;
	
	$scope.pdplIsAgree = false;
	
	$scope.memberAccountVerifyFail = false;
	
	$scope.orgName = "";
	$scope.orgCity = "";
	$scope.orgTown = "";
	$scope.orgAddress = "";
	$scope.parentOrgId = null;
	$scope.memberAccount = "";
	$scope.memberName = "";
	$scope.memberEmail = "";
	$scope.memberMobilePhone = "";
	$scope.orgBossName = "";
	$scope.orgBossEmail = "";
	$scope.orgBossMobilePhone = "";
	$scope.orgPrincipalName = "";
	$scope.orgPrincipalMobilePhone = "";
	$scope.healthLevelId=0;
	$scope.securityLevel=0;
	$scope.IsPublic=false;

	
	$scope.inputModel = '';
	$scope.options = {
		url: './public/api/getHealthcares',
		delay: 0,
		minlength: 2,
		nameAttr: 'name',
		dataHolder: 'items',
		limitParam: 'per_page',
		searchParam: 'q',
		highlightExactSearch: false,
		programmaticallyLoad: true,
		hasSelectionClass: '',
		onSelect: function (item) {			
			$scope.checkCI = item.isCI;
			$scope.orgAddress = item.address;
			$scope.orgCity = item.city;
			$scope.orgTown = item.town;
			$scope.parentOrgId = item.parentOrgId;
			$scope.healthLevelId=item.healthLevelId;
			$scope.securityLevel=item.securityLevel;
			$scope.IsPublic=item.isPublic;

			
			$('#twzipcode').twzipcode('set', {
			    'county'   : $scope.orgCity,
			    'district' : $scope.orgTown
			});
		},
		noResultsMessage: noResultsMessage,
		itemTemplate: '<button type="button" ng-class="getItemClasses($index)" ng-mouseenter="onItemMouseenter($index)" ng-repeat="item in searchResults" ng-click="select(item)">' +
			'<div>' +
			'<h5><strong ng-bind="item.name"></strong></h5>' +
			'<span ng-bind="item.id"></span>' +
			'</div>' +
			'</button>'
	};
	
	$scope.select = function(item) {
		console.log(item);
	}
	
	// Get HealthLevel Start 
//	$scope.getHealthLevels = function(){
//		var request = {
//				
//		};
//		$http
//		.post('./public/api/getHealthLevels', request, csrf_config)
//		.then(function(response) {
//			if (response.data.success == true) {
//				$scope.healthLevels = response.data.datatable;
//			} else {
//				$scope.healthLevels = [];
//			}		
//		}).catch(function() {
//			$scope.healthLevels = [];
//		});
//	}
//	
//	$scope.getHealthLevels();
	
	// Get HealthLevel End

	$scope.programmaticallyLoad = function() {
		$scope.autoModel = 'np-autocomplete';
	};
	
	$scope.getAuthorOrgs = function() {
		var request = {
			
		};
		$http
			.post('./public/api/getAuthorOrgs', request, csrf_config2)
			.then(function(response) {
				if (response.data.success == true) {
					$scope.authorOrgs = response.data.datatable;
				} else {
					$scope.authorOrgs = [];
				}		
			}).catch(function() {
				$scope.authorOrgs = [];
			});
	}
	$scope.getAuthorOrgs();
	
	$scope.checkMemberAccount = function() {
		$scope.memberAccountVerifyFail = false;
		if ($scope.myForm.memberAccount.$valid && $scope.myForm.memberAccount.$dirty) {
			var memberAccount = $window.document.getElementById("memberAccount").value;
			$scope.memberAccountVerifyFail = false;
			$scope.myForm.memberAccount.$valid = false;
			var request = {
				memberAccount : $scope.memberAccount
			}
			$http
				.post('./public/api/checkAccount', request, csrf_config2)
				.then(function(response) {
					if (response.data.success == true) {
						$scope.memberAccountVerifyFail = false;
						$scope.myForm.memberAccount.$valid = false;
						$scope.healthLevelId=0;
						$scope.securityLevel=0;
						$scope.IsPublic=false;
					} else {
						$scope.memberAccountVerifyFail = true;
						$scope.myForm.memberAccount.$valid = true;
					}
				}).catch(function() {
					$scope.memberAccountVerifyFail = false;
					$scope.myForm.memberAccount.$valid = false;
				});
		}
	}
	

	$scope.pdplAgree = function() {
		$scope.pdplIsAgree =  true;
		$window.document.getElementById("sign").style.display = "block";
		$window.document.getElementById("pdpl").style.display = "none";
	}
	
	$scope.pdplDisagree = function() {
		$scope.pdplIsAgree =  false;
		$window.document.getElementById("sign").style.display = "block";
		$window.document.getElementById("pdpl").style.display = "none";
	}
	
	$scope.pdplShow = function() {
		$window.document.getElementById("sign").style.display = "none";
		$window.document.getElementById("pdpl").style.display = "block";
	}
	
	$scope.verifyMail = function() {
		var request = {
				vEmail: $scope.vEmail
		}
		$http
		.post('./public/api/genVerifyCode', request, csrf_config2)
		.then(function(response) {
			$scope.verifyEmail = true;
		});
	}
	
	$scope.verifyCode = function() {
		var request = {
				vEmail: $scope.vEmail,
				vCode: $scope.vCode
		}
		$http
		.post('./public/api/verifyCode', request, csrf_config2)
		.then(function(response) {
			if (response.data.success) {
				$('#verifyModal').modal('hide');
				$scope.memberEmail = $scope.vEmail;
				bootbox.alert({
					message : '請繼續填寫會員申請資訊',
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-primary'
						}
					},
					callback: function() {
						bootbox.hideAll();
					}
				});
			} else {
				bootbox.alert({
					message : '驗證碼錯誤',
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-primary'
						}
					},
					callback: function() {
						bootbox.hideAll();
					}
				});
			}
		}).catch(function() {
			bootbox.alert({
					message : '驗證失敗',
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-primary'
						}
					},
					callback: function() {
						bootbox.hideAll();
					}
				});
		});
	}
	
	$scope.signup = function() {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + memberSignUp 
		});
		var request = {
				orgCode : $scope.orgCode,
				orgName : $scope.orgName,
				orgCity : $scope.orgCity,
				orgTown : $scope.orgTown,
				orgAddress : $scope.orgAddress,
				orgBossName : $scope.orgBossName,
				orgBossEmail : $scope.orgBossEmail,
				orgBossMobilePhone : $scope.orgBossMobilePhone,
				orgPrincipalName : $scope.orgPrincipalName,
				orgPrincipalMobilePhone : $scope.orgPrincipalMobilePhone,
				parentOrgId : $scope.parentOrgId,
				healthLevelId : $scope.healthLevelId,
				securityLevel : $scope.securityLevel,
				isPublic : $scope.IsPublic,
				memberAccount : $scope.memberAccount,
				memberName : $scope.memberName,
				memberEmail : $scope.memberEmail,
				memberMobilePhone : $scope.memberMobilePhone
				
		};
		$http
			.post('./public/api/sign_up', request, csrf_config2)
			.then(function(response) {
				if (response.data.success == true) {
					bootbox.hideAll();
					bootbox.alert({
						message : response.data.msg,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose + "並下載會員機構申請表",
								className : 'btn-primary'
							}
						},
						callback: function() {
							window.open("./public/api/download_sign_up_info?account=" + $scope.memberAccount, '_pdf');
							window.location.href = "./";
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
						}
					});
				}
			})
			.catch(function() {
				bootbox.hideAll();
				bootbox.alert({
					message : memberSignUpFail,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-danger'
						}
					}
				});
			});
	}
}

$(document).ready(function() {
	var refresh = setInterval(function() {
		location.reload();
	}, 1000 * 60 * 20);
	$(".city").change(function() {
		$("#orgCity").val($(this).val());
		$("#orgTown").val($(".town").val());
		$("#orgCity").trigger('input');
		$("#orgCity").trigger('change');
		$("#orgTown").trigger('input');
		$("#orgTown").trigger('change');
	});
	$(".town").change(function() {
		$("#orgTown").val($(this).val());
		$("#orgTown").trigger('input');
		$("#orgTown").trigger('change');
	});
	
	$("#btnLogin").hide();
	$("#btnIndex").show();
	$("#btnLogin2").hide();
	$("#btnIndex2").show();
	
	$('#verifyModal').modal({
		backdrop: 'static',
		keyboard: false
	});
});

function toggleLogin() {
	location.href = "./";
}