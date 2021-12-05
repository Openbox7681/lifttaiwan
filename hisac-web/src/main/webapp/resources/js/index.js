var myApp = angular.module('myApp', []).controller(
		'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

function getAppController($scope, $http, $window, $timeout) {
	// 最新消息
	var items = [];
    for (var i = 0; i < 5; i++) {
        items.push({
            Date: "　",
            Title: "",
            Id: ""
        });
    }
    $scope.news = items;
    $scope.activity = items;
    $scope.qa = items;
    $scope.malware = items;
    
    $scope.queryNews = function() {
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true
        };
        $http.post('./public/api/query/news', request, csrf_config2).then(function(response) {
            $scope.news = response.data.datatable;
            $scope.newsTotal = response.data.total;
            if ($scope.newsTotal < 5) {
                for (var i = 0; i < 5 - $scope.newsTotal; i++) {
                    $scope.news.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingNews").fadeOut("slow");
        })
    }

    $scope.queryActivity = function() {
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true
        };
        $http.post('./public/api/query/activity', request, csrf_config2).then(function(response) {
            $scope.activity = response.data.datatable;
            $scope.activityTotal = response.data.total;
            if ($scope.activityTotal < 5) {
                for (var i = 0; i < 5 - $scope.activityTotal; i++) {
                    $scope.activity.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingActivity").fadeOut("slow");
        })
    }
    
    $scope.queryQA = function() {
        var request = {
            start: 0,
            maxRows: 5,
            sort: "id",
            dir: true
        };
        $http.post('./public/api/query/qa', request, csrf_config2).then(function(response) {
            $scope.qa = response.data.datatable;
            $scope.qaTotal = response.data.total;
            if ($scope.qaTotal < 5) {
                for (var i = 0; i < 5 - $scope.qaTotal; i++) {
                    $scope.qa.push({
                        QAMgName: "　",
                        QName: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingQA").fadeOut("slow");
        })
    }
    
    $scope.queryMalware = function() {
        var request = {
            start: 0,
            maxRows: 5,
            sort: "id",
            dir: true
        };
        $http.post('./public/api/query/malware', request, csrf_config2).then(function(response) {
            $scope.malware = response.data.datatable;
            $scope.malwareTotal = response.data.total;
            if ($scope.qaTotal < 5) {
                for (var i = 0; i < 5 - $scope.malwareTotal; i++) {
                    $scope.malware.push({
                    	 Date: "　",
                         Title: "",
                         Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingMalwares").fadeOut("slow");            
//    		$("#loadingMalwares").hide();


        })
    }
    
    

    $scope.queryNews();
    $scope.queryActivity();
    $scope.queryQA();
    $scope.queryMalware();
    
    // 登入
	$scope.userName = localStorage.getItem("userName");
	localStorage.removeItem("userName");
	$scope.userName = "";
	$scope.userCode = "";
	// $scope.otpCode = "";
	$scope.showTwoFactor = false;
	// angular.element("#divotpCode").removeAttr("style");
	angular.element("#divgtpCode").removeAttr("style");
	angular.element("#divVerify").removeAttr("style");
	
	
	$scope.test = function(){
		bootbox.confirm(
				{message: "這是一個確認按鈕的樣式！"
					,buttons: {confirm: {label: 'Yes',className: 'btn-success'},
					cancel: {label: 'No',className: 'btn-danger'}},
					callback: function (result) 
					{console.log('This was logged in the callback: ' + result);}})
	}
	
	$scope.login = function() {
		var grecaptcha = $("#g-recaptcha-response").val();
//		if (grecaptcha == '' && $scope.showTwoFactor) return;
//		bootbox.dialog({
//            closeButton: false,
//            message: '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + loginLogin
//        });
		
		
		console.log("testtt")
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		
		
		
		
		
		var request = 'account=' + $scope.userName + '&code=' + btoa($scope.userCode) + '&otp=' + $scope.otpCode + '&gtp=' + grecaptcha;
		$http
			.post('./public/api/login', request, csrf_config)
			.then(function(response) {
				if (response.data.success == true) {
					if (response.data.url != "") {
						$window.location = response.data.url;
					} else {
						bootbox.hideAll();
						// $scope.otpCode = "";
						$scope.showTwoFactor = true;
					}
					$timeout(function() {
						// $window.document.getElementById("otpCode").focus();
					}, 0); 
				} else {
					bootbox.hideAll();
					$scope.userName = "";
					$scope.userCode = "";
					// $scope.otpCode = "";
					$scope.showTwoFactor = false;
					bootbox.alert({
						message : response.data.msg,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-danger'
							}
						},
						callback: function() {
							$timeout(function() {
								$window.document.getElementById("userName").focus();
							}, 0); 
						}
					});
				}
			})
			.catch(function() {
				bootbox.hideAll();
				$scope.userCode = "";
				// $scope.otpCode = "";
				$scope.showTwoFactor = false;
				bootbox.alert({
					message : loginFail,
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
		localStorage.userName = $("#userName").val();
		location.reload();
	}, 1000 * 60 * 10);
});

function toggleLogin() {
	$("#divLogin").toggle();
	$("#divIndex").toggle();
	$("#btnLogin").toggle();
	$("#btnIndex").toggle();
	$("#btnLogin2").toggle();
	$("#btnIndex2").toggle();
}