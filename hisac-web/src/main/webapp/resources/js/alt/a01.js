var myApp = angular.module('myApp', ['ngCookies', 'bw.paging', 'ui.toggle']).controller(
    'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($scope, $http, $cookieStore, $cookies, $anchorScroll, $location) {
	
	
	
	$scope.queryNumber = function() {
        $("#loadingActivity").fadeIn("slow");

		var request = {
			count_topname : true,
			count_p_id : true,
			count_paper_SerialNumber : true,
			paper_corId : "1"
		};
		$http.post('./api/a01/queryNumber', request, csrf_config).then(function(response) {
			
			
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
	
	
	
	
	// Paging Start
    $scope.start = 0;
    $scope.currentPage = 1;
    $scope.maxRows = 10;
    $scope.maxPages = 0;
    $scope.total = 0;
    $scope.sorttype = "applyDateTime";
    $scope.sortreverse = true;
    
    $scope.ButtonCount1=0;
	$scope.ButtonCount2=0;
	$scope.ButtonCount3=0;
	$scope.ButtonCount4=0;
	$scope.ButtonCount7=0;
	
	$scope.HandleInformationDisable = false;
	
	$scope.otAddDataList = [];
		
	
	

    $('input[id="QueryEApplyDateTime"]').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $('input[id="QuerySApplyDateTime"]').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });

    $("#QuerySApplyDateTime").on("dp.change", function(e) {
        $scope.QuerySApplyDateTime = $('#QuerySApplyDateTime').val();
        if ($scope.QuerySApplyDateTime > $scope.QueryEApplyDateTime && $scope.QueryEApplyDateTime != null) {
            $scope.QueryEApplyDateTime = $scope.QuerySApplyDateTime;
            $('#QueryEApplyDateTime').val($scope.QueryEApplyDateTime)
        }
    });
    $("#QueryEApplyDateTime").on("dp.change", function(e) {
        $scope.QueryEApplyDateTime = $('#QueryEApplyDateTime').val();
        if ($scope.QueryEApplyDateTime < $scope.QuerySApplyDateTime && $scope.QuerySApplyDateTime != null) {
            $scope.QuerySApplyDateTime = $scope.QueryEApplyDateTime;
            $('#QuerySApplyDateTime').val($scope.QuerySApplyDateTime)
        }
    });

    $("#ApplyDateTime").datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-TW'
    });
    $("#ApplyDateTime").on("dp.change", function(e) {
        $scope.ApplyDateTime = $("#ApplyDateTime").val();
    });

    $("#EventDateTime").datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-TW'
    });
    $("#EventDateTime").on("dp.change", function(e) {
        $scope.EventDateTime = $("#EventDateTime").val();
    });

    $("#ResControlTime").datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-TW'
    });
    $("#ResControlTime").on("dp.change", function(e) {
        $scope.ResControlTime = $("#ResControlTime").val();
    });

    $("#Res1DoOpt10Date").datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $("#Res1DoOpt10Date").on("dp.change", function(e) {
        $scope.Res1DoOpt10Date = $("#Res1DoOpt10Date").val();
    });

    $("#Res2DoOpt5Date").datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $("#Res2DoOpt5Date").on("dp.change", function(e) {
        $scope.Res2DoOpt5Date = $("#Res2DoOpt5Date").val();
    });
    
    $("#Res1DoOpt11Date").datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $("#Res1DoOpt11Date").on("dp.change", function(e) {
        $scope.Res1DoOpt11Date = $("#Res1DoOpt11Date").val();
    });

    $("#Res5DoOpt5Date").datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'zh-TW'
    });
    $("#Res5DoOpt5Date").on("dp.change", function(e) {
        $scope.Res5DoOpt5Date = $("#Res5DoOpt5Date").val();
    });

    $("#FinishDateTime").datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-TW'
    });
    $("#FinishDateTime").on("dp.change", function(e) {
        $scope.FinishDateTime = $("#FinishDateTime").val();
    });

    
	
    
    $scope.setSortName = function(sorttype) {
        $scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse :
            false;
        $scope.sorttype = sorttype;
        $scope.currentPage = 1;
        $scope.start = 0;
        $scope.queryData();
    };

    $scope.maxRowsChange = function() {
        $scope.start = 0;
        $scope.currentPage = 1;
        $scope.queryData();
    };
    // Paging End

    // Clear Query Data Start
    $scope.clearData = function() {
        $scope.QueryId = null;
        $scope.QuerySApplyDateTime = null;
        $scope.QueryEApplyDateTime = null;
        $scope.QueryStatus = null;
        $scope.QueryKeyword = null;
        $scope.btnIns = false;
        $scope.btnUpd = false;
        $("#QuerySApplyDateTime").val("");
        $("#QueryEApplyDateTime").val("");
        $('#QuerySApplyDateTime').data("DateTimePicker").clear()
        $('#QueryEApplyDateTime').data("DateTimePicker").clear()

    };
    $scope.clearData();
    // Clear Query Data End

    // Query Data Start
    $scope.queryData = function(page) {
       
    };
    $scope.queryData();
    // Query Data End


	
    
    
   
}

$(document).ready(function() {
    $('#tabNotifyStep2').removeClass('active');
    $('#tabNotifyStep3').removeClass('active');
    $('#tabNotifyStep4').removeClass('active');
    $('#tabNotifyStep5').removeClass('active');
    $('#tabNotifyStep6').removeClass('active');
})