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

    
	$scope.queryHandleInformationRemark = function() {					
		var request = {				
		};

		$http.post('./api/a01/query/handleInformationRemark', request, csrf_config).then(function(response) {
			$scope.handle_information_remark = response.data.handle_information_remark;						
			
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
	$scope.queryHandleInformationRemark();
    
    $scope.getSupportNotes = function() {
        var data = {};
        $http.post('./api/a01/query/supportNotes', data, csrf_config).then(function(response) {           
        		$scope.SupportNotes = response.data.supportNotes;
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
        }).finally(function() {});    		
    }  
    
    $scope.getSupportNotes();
    
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
        $("#imgLoading").show();
        if (page) {
            $scope.start = (page - 1) * $scope.maxRows
        } else {
            $scope.start = 0;
        }
        if ($scope.QuerySApplyDateTime == "")
            $scope.QuerySApplyDateTime = null;
        if ($scope.QueryEApplyDateTime == "")
            $scope.QueryEApplyDateTime = null;
        if ($scope.QueryStatus == "")
            $scope.QueryStatus = null;
        if ($scope.QueryKeyword == "")
            $scope.QueryKeyword = null;

        var request = {
            start: $scope.start,
            maxRows: $scope.maxRows,
            sort: $scope.sorttype,
            dir: $scope.sortreverse,
            SApplyDateTime: $scope.QuerySApplyDateTime,
            EApplyDateTime: $scope.QueryEApplyDateTime,
            Status: $scope.QueryStatus,
            Keyword: $scope.QueryKeyword
        };

        $http.post('./api/a01/query', request, csrf_config).then(function(response) {
            $scope.allitems = response.data.datatable;
            $scope.total = response.data.total;
            $scope.maxPages = parseInt($scope.total / $scope.maxRows);
            $scope.pageRows = $scope.total % $scope.maxRows;
            if ($scope.pageRows != 0)
                $scope.maxPages++;
            $scope.returnTotal = true;
            $scope.queryButtonCount();
        }).catch(function() {
            bootbox.alert({
                message: globalReadDataFail,
                buttons: {
                    ok: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-danger',
                    }
                },
                callback: function() {}
            });
        }).finally(function() {
            $("#imgLoading").hide();
        });
    };
    $scope.queryData();
    // Query Data End

// HandleInformation Start
$scope.seeHandleInformation = function() {
    var data = {};
    $http.post('./api/a01/query/handleInformation', data, csrf_config).then(function(response) {
        $scope.HandleInformation = response.data;
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
    }).finally(function() {});    		
}    	

// HandleInformation End
	
	$scope.seeHandleInformation();
    
    
    // Get EffectValue from C/I/A Effect Max Start
    $scope.change_effect = function() {
        $scope.EffectLevel = window.Math.max($scope.CeffectLevel, window.Math.max($scope.IeffectLevel, $scope.AeffectLevel));
    }
    // Get EffectValue from C/I/A Effect Max End

    $scope.queryMemberInfo = function() {
        var request = {};
        $http.post('./api/a01/query/member_info', request, csrf_config).then(function(response) {
            $scope.ContactorTel = response.data[0].ContactorTel;
            $scope.ContactorFax = response.data[0].ContactorFax;
            $scope.ContactorEmail = response.data[0].ContactorEmail;
            $scope.ContactorName = response.data[0].ContactorName;
            $scope.ContactorId = response.data[0].ContactorId;
            $scope.ContactorUnit = response.data[0].ContactorUnit;
            $scope.ContactorUnitName = response.data[0].ContactorUnitName;
            $scope.MainUnit1 = response.data[0].MainUnit1;
            $scope.MainUnit1Name = response.data[0].MainUnit1Name;
            $scope.MainUnit2 = response.data[0].MainUnit2;
            $scope.MainUnit2Name = response.data[0].MainUnit2Name;
        }).catch(function() {}).finally(function() {});
    }

//    $scope.queryMemberInfoById = function(memberId) {
//        var request2 = {
//            Id: memberId
//        };
//        $http.post('./api/a01/query/member_info', request2, csrf_config).then(function(response2) {
//            // $scope.ContactorTel = response2.data[0].ContactorTel;
//            // $scope.ContactorFax = response2.data[0].ContactorFax;
//            // $scope.ContactorEmail = response2.data[0].ContactorEmail;
//            $scope.ContactorName = response2.data[0].ContactorName;
//            $scope.ContactorId = response2.data[0].ContactorId;
//            $scope.ContactorUnit = response2.data[0].ContactorUnit;
//            $scope.ContactorUnitName = response2.data[0].ContactorUnitName;
//            $scope.MainUnit1 = response2.data[0].MainUnit1;
//            $scope.MainUnit1Name = response2.data[0].MainUnit1Name;
//            $scope.MainUnit2 = response2.data[0].MainUnit2;
//            $scope.MainUnit2Name = response2.data[0].MainUnit2Name;
//        }).catch(function() {}).finally(function() {});
//    }

    // Start click_postId
    $scope.click_postid = function(item) {		
		if (item.IsButtonEdit)
			$scope.editData(item.Id,'viewUpdate');
		else if (item.IsButtonHandle)
			$scope.editData(item.Id,'handle');
		else if (item.IsButtonReview)
			$scope.editData(item.Id,'review');
		else if (item.IsButtonHandleReview)
			$scope.editData(item.Id,'review_handle');
		else
			$scope.editData(item.Id,'view');
	}
    // End click_postId
    
    // Switch to Edit(Insert) Mode Start
    $scope.openEdit = function(action) {
        switch (action) {
            case 'edit':
            		$scope.queryMemberInfo();
                var request = {};
                $http.post('./api/a01/IsCCorReview', request, csrf_config).then(function(response) {
                    $scope.IsSeeIsCC3 = response.data[0].IsSeeIsCC3;
                    $scope.IsSeeIsReview3 = false;
                    $scope.IsSeeIsCC5 = response.data[0].IsSeeIsCC5;
                    $scope.IsSeeIsReview5 = false;
                    $scope.IsNeedSaleCC3 = response.data[0].IsNeedSaleCC3;
                    $scope.IsNeedSaleReview3 = response.data[0].IsNeedSaleReview3;


                }).catch(function() {
                    bootbox.alert({
                        message: globalReadDataFail,
                        buttons: {
                            ok: {
                                label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                className: 'btn-danger',
                            }
                        },
                        callback: function() {}
                    });
                }).finally(function() {});
                $scope.btnIns = true;
                $scope.btnUpd = false;
                $scope.handle = false;
                $scope.View_Show = false;
                $scope.IsSeeOpinion = false;
                $("#divEdit").show();
                $("#divQuery").hide();
                break;
            case 'view':            	 	
                $scope.View_Show = true;
                $scope.IsSeeOpinion = false;
                $scope.btnIns = false;
                $scope.btnUpd = false;
                $scope.handle = false;
                $scope.reject = false;   
                $scope.pass = false;   
                $scope.back = false;   
                $("#divEdit").hide();
                $("#divQuery").hide();
                break;
            case 'viewUpdate':
                $scope.btnIns = false;
                $scope.btnUpd = true;
                $scope.handle = false;
                $scope.View_Show = false;
                $scope.IsSeeOpinion = false;
                $("#divEdit").show();
                $("#divQuery").hide();
                break;
            case 'review':
                $scope.View_Show = true;
                $scope.IsSeeOpinion = true;
                $scope.btnIns = false;
                $scope.btnUpd = false;
                $scope.handle = false;
                $scope.reject = true;
                $scope.pass = true;   
                $scope.back = true;  
                $("#divEdit").hide();
                $("#divQuery").hide();
                break;
            case 'review_handle':
                $scope.View_Show = true;
                $scope.IsSeeOpinion = true;
                $scope.btnIns = false;
                $scope.btnUpd = false;
                $scope.handle = false;
                $scope.reject = false;
                $scope.pass = true;   
                $scope.back = true; 
                $("#divEdit").hide();
                $("#divQuery").hide();
                break;
            case 'handle':
                $scope.handle = true;
                $scope.View_Show = false;
                $scope.IsSeeOpinion = false;
                $scope.btnIns = false;
                $scope.btnUpd = false;
                $("#divEdit").show();
                $("#divQuery").hide();
                break;
        }
        $('.nav-tabs a:first').tab('show')
        $('.nav-tabs a:first').tab('show')
        $("html, body").animate({
            scrollTop: 0
        }, 0);
        $scope.Status = 1;
        $scope.NotificationLog = [];
        $scope.NotificationLevelLog = [];
        
        $scope.otAddDataList = [];

        $scope.Id = null;
        $scope.ApplyDateTime = moment().format("YYYY-MM-DD HH:mm:ss");
        $scope.PostId = "";

        $scope.ContactorUnit = null;
        $scope.MainUnit1 = null;
        $scope.MainUnit2 = null;
        $scope.ContactorId = null;
        $scope.ContactorTel = "";
        $scope.ContactorFax = "";
        $scope.ContactorEmail = "";
//        $scope.queryMemberInfo();

        $scope.IsSub = false;
        $scope.IsSubUnitName = "";
        $scope.EventDateTime = moment().format("YYYY-MM-DD HH:mm:ss");
        $scope.HostAmount = 0;
        $scope.ServerAmount = 0;
        $scope.InformationAmount = 0;
        $scope.OtherDeviceAmount = 0;
        $scope.OtherDeviceName = "";
        $scope.DeviceRemark = "";
        $scope.AssessDamage = "";
        $scope.AssessDamageRemark = "";
        $scope.IsFinishDoSysOptRemark = false;
        $scope.IsFinishDoEduOptRemark = false;
        $scope.FinishDoSysOptRemark = "";
        $scope.FinishDoEduOptRemark = "";
        $scope.IpExternal = "";
        $scope.IpInternal = "";
        $scope.WebUrl = "";
        $scope.IsOsOpt1 = false;
        $scope.IsOsOpt2 = false;
        $scope.IsOsOpt3 = false;
        $scope.IsOsOpt3Other = "";
        $scope.IsGuardOpt1 = false;
        $scope.IsGuardOpt2 = false;
        $scope.IsGuardOpt3 = false;
        $scope.IsGuardOpt4 = false;
        $scope.IsGuardOpt4Other = "";
        $scope.SocOpt = 0;
        $scope.SocOptCompany = "";
        $scope.IsIsms = false;
        $scope.MaintainCompany = "";
        $scope.IsTest = false;
        $scope.CeffectLevel = null;
        $scope.IeffectLevel = null;
        $scope.AeffectLevel = null;
        $scope.EffectLevel = null;
        $scope.change_effect();
        $scope.EventType = 1;
        $scope.IsEventType1Opt1 = false;
        $scope.IsEventType1Opt2 = false;
        $scope.IsEventType1Opt3 = false;
        $scope.IsEventType1Opt4 = false;
        $scope.IsEventType1Opt5 = false;
        $scope.IsEventType1Opt6 = false;
        $scope.IsEventType2Opt1 = false;
        $scope.IsEventType2Opt2 = false;
        $scope.IsEventType2Opt3 = false;
        $scope.IsEventType2Opt4 = false;
        $scope.IsEventType2Opt5 = false;
        $scope.IsEventType3Opt1 = false;
        $scope.IsEventType3Opt2 = false;
        $scope.IsEventType4Opt1 = false;
        $scope.IsEventType4Opt2 = false;
        $scope.IsEventType4Opt3 = false;
        $scope.IsEventType4Opt4 = false;
        $scope.EventType5Other = "";
        $scope.EventRemark = "";
        $scope.IsAffectOthers = false;
        $scope.AffectOther1 = false;
        $scope.AffectOther2 = false;
        $scope.AffectOther3 = false;
        $scope.AffectOther4 = false;
        $scope.AffectOther5 = false;
        $scope.AffectOther6 = false;
        $scope.AffectOther7 = false;
        $scope.AffectOther8 = false;
        $scope.EventSource = 1;
        $scope.EventSourceNo = "";
        $scope.EventSourceOther = "";

        // Res All
        $scope.ResResult = 1;
        $scope.ResDescription = "";
        $scope.ResControlTime = moment().format("YYYY-MM-DD HH:mm:ss");
        
        // Res 1
        $scope.IsRes1LogOpt1 = false;
        $scope.Res1LogOpt1 = 1;
        $scope.Res1LogOpt1Other = "";
        $scope.IsRes1LogOpt2 = false;
        $scope.Res1LogOpt2 = 1;
        $scope.Res1LogOpt2Other = "";
        $scope.IsRes1LogOpt5 = false;
        $scope.Res1LogOpt5 = 1;
        $scope.Res1LogOpt5Other = "";
        $scope.IsRes1LogOpt3 = false;
        $scope.Res1LogOpt3Amount = 0;
        $scope.IsRes1LogOpt4 = false;
        $scope.Res1LogOpt4Remark = "";
        $scope.IsRes1EvaOpt1 = false;
        $scope.Res1EvaOpt1Remark = "";
        $scope.IsRes1EvaOpt2 = false;
        $scope.Res1EvaOpt2Remark = "";
        $scope.IsRes1EvaOpt3 = false;
        $scope.Res1EvaOpt3Remark = "";
        $scope.IsRes1EvaOpt4 = false;
        $scope.Res1EvaOpt4Remark = "";
        $scope.IsRes1EvaOpt5 = false;
        $scope.IsRes1EvaOpt6 = false;
        $scope.Res1EvaOpt6Type = 1;
        $scope.Res1EvaOpt6Amount = 0;
        $scope.Res1EvaOpt6Remark = "";
        $scope.Res1EvaOpt6TypeOpt3Other = "";
        $scope.IsRes1EvaOpt7 = false;
        $scope.Res1EvaOpt7Amount = 0;
        $scope.Res1EvaOpt7Remark = "";
        $scope.IsRes1EvaOpt8 = false;
        $scope.Res1EvaOpt8Remark = "";
        $scope.IsRes1DoOpt1 = false;
        $scope.Res1DoOpt1Amount = 0;
        $scope.IsRes1DoOpt2 = false;
        $scope.Res1DoOpt2Remark = "";
        $scope.IsRes1DoOpt3 = false;
        $scope.Res1DoOpt3Remark = "";
        $scope.IsRes1DoOpt4 = false;
        $scope.Res1DoOpt4Remark = "";
        $scope.IsRes1DoOpt5 = false;
        $scope.Res1DoOpt5Amount = 0;
        $scope.IsRes1DoOpt6 = false;
        $scope.IsRes1DoOpt7 = false;
        $scope.IsRes1DoOpt8 = false;
        $scope.IsRes1DoOpt9 = false;
        $scope.IsRes1DoOpt9EngineOpt1 = false;
        $scope.IsRes1DoOpt9EngineOpt2 = false;
        $scope.IsRes1DoOpt9EngineOpt3 = false;
        $scope.IsRes1DoOpt9EngineOpt4 = false;
        $scope.IsRes1DoOpt9EngineOpt5 = false;
        $scope.IsRes1DoOpt9EngineOpt6 = false;
        $scope.Res1DoOpt9EngineOpt6Other = "";
        $scope.IsRes1DoOpt10 = false;
        $scope.Res1DoOpt10Date = "";
        $scope.IsRes1DoOpt11 = false;
        $scope.Res1DoOpt11Date = "";
        $scope.IsRes1DoOpt12 = false;
        $scope.Res1DoOpt12Remark = "";

        // Res 2
        $scope.IsRes2LogOpt1 = false;
        $scope.Res2LogOpt1 = 1;
        $scope.Res2LogOpt1Other = "";
        $scope.IsRes2LogOpt2 = false;
        $scope.Res2LogOpt2 = 1;
        $scope.Res2LogOpt2Other = "";
        $scope.IsRes2LogOpt3 = false;
        $scope.Res2LogOpt3Amount = 0;
        $scope.IsRes2LogOpt4 = false;
        $scope.Res2LogOpt4Remark = "";
        $scope.IsRes2EvaOpt1 = false;
        $scope.Res2EvaOpt1Remark = "";
        $scope.IsRes2EvaOpt2 = false;
        $scope.Res2EvaOpt2Remark = "";
        $scope.IsRes2EvaOpt3 = false;
        $scope.Res2EvaOpt3Remark = "";
        $scope.IsRes2EvaOpt4 = false;
        $scope.Res2EvaOpt4Remark = "";
        $scope.IsRes2EvaOpt5 = false;
        $scope.Res2EvaOpt5Remark = "";
        $scope.IsRes2DoOpt1 = false;
        $scope.Res2DoOpt1Amount = 0;
        $scope.Res2DoOpt1Remark = "";
        $scope.IsRes2DoOpt2 = false;
        $scope.Res2DoOpt2Remark = "";
        $scope.IsRes2DoOpt3 = false;
        $scope.Res2DoOpt3Remark = "";
        $scope.IsRes2DoOpt4 = false;
        $scope.IsRes2DoOpt5 = false;
        $scope.Res2DoOpt5Date = "";
        $scope.IsRes2DoOpt6 = false;
        $scope.Res2DoOpt6Amount = 0;
        $scope.IsRes2DoOpt7 = false;
        $scope.Res2DoOpt7Remark = "";
        
        //ot資料查詢與已加入資料初始化
        $scope.otAddDataList = [];
        $scope.otData = [];
        
        

        // Res 3
        $scope.IsRes3LogOpt1 = false;
        $scope.Res3LogOpt1 = 1;
        $scope.Res3LogOpt1Other = "";
        $scope.IsRes3LogOpt2 = false;
        $scope.Res3LogOpt2 = 1;
        $scope.Res3LogOpt2Other = "";
        $scope.IsRes3LogOpt3 = false;
        $scope.Res3LogOpt3Amount = 0;
        $scope.IsRes3LogOpt4 = false;
        $scope.Res3LogOpt4Remark = "";
        $scope.IsRes3EvaOpt1 = false;
        $scope.Res3EvaOpt1Amount = 0;
        $scope.IsRes3EvaOpt2 = false;
        $scope.Res3EvaOpt2Remark = "";
        $scope.IsRes3EvaOpt3 = false;
        $scope.Res3EvaOpt3Remark = "";
        $scope.IsRes3DoOpt1 = false;
        $scope.Res3DoOpt1Remark = "";
        $scope.IsRes3DoOpt2 = false;
        $scope.IsRes3DoOpt3 = false;
        $scope.Res3DoOpt3Remark = "";
        $scope.IsRes3DoOpt4 = false;
        $scope.Res3DoOpt4Remark = "";

        // Res 4
        $scope.IsRes4LogOpt1 = false
        $scope.Res4LogOpt1 = 1;
        $scope.Res4LogOpt1Remark = "";
        $scope.IsRes4EvaOpt1 = false;
        $scope.Res4EvaOpt1 = 1;
        $scope.Res4EvaOpt1Amount = 0;
        $scope.IsRes4EvaOpt2 = false;
        $scope.Res4EvaOpt2Remark = "";
        $scope.IsRes4EvaOpt3 = false;
        $scope.Res4EvaOpt3Remark = "";
        $scope.IsRes4DoOpt1 = false;
        $scope.IsRes4DoOpt2 = false;
        $scope.IsRes4DoOpt4 = false;
        $scope.IsRes4DoOpt3 = false;
        $scope.Res4DoOpt3Remark = "";

        // Res 5
        $scope.IsRes5LogOpt1 = false;
        $scope.Res5LogOpt1 = 1;
        $scope.Res5LogOpt1Other = "";
        $scope.IsRes5LogOpt2 = false;
        $scope.Res5LogOpt2 = 1;
        $scope.Res5LogOpt2Other = "";
        $scope.IsRes5LogOpt3 = false;
        $scope.Res5LogOpt3Amount = 0;
        $scope.IsRes5LogOpt4 = false;
        $scope.Res5LogOpt4Remark = "";
        $scope.IsRes5EvaOpt1 = false;
        $scope.Res5EvaOpt1Remark = "";
        $scope.IsRes5EvaOpt2 = false;
        $scope.Res5EvaOpt2Remark = "";
        $scope.IsRes5EvaOpt3 = false;
        $scope.Res5EvaOpt3Remark = "";
        $scope.IsRes5EvaOpt4 = false;
        $scope.Res5EvaOpt4Remark = "";
        $scope.IsRes5EvaOpt5 = false;
        $scope.Res5EvaOpt5Remark = "";
        $scope.IsRes5DoOpt1 = false;
        $scope.Res5DoOpt1Amount = 0;
        $scope.Res5DoOpt1Remark = "";
        $scope.IsRes5DoOpt2 = false;
        $scope.Res5DoOpt2Remark = "";
        $scope.IsRes5DoOpt3 = false;
        $scope.Res5DoOpt3Remark = "";
        $scope.IsRes5DoOpt4 = false;
        $scope.IsRes5DoOpt5 = false;
        $scope.Res5DoOpt5Date = "";
        $scope.IsRes5DoOpt6 = false;
        $scope.Res5DoOpt6Amount = 0;
        $scope.IsRes5DoOpt7 = false;
        $scope.Res5DoOpt7Remark = "";

        $scope.IsNeedSupport = false;
        $scope.NeedSupportRemark = "";
        
        $scope.IsHandledByMyself = true;
        $scope.HandleInformationId = null;
        $scope.HandleCompany ="";

        // Finish 1
        $scope.Finish1Reason = 1;
        $scope.Finish1ReasonOther = "";
        $scope.Finish1ReasonToDo = "";
        $scope.IsFinish1DoSysOpt1 = false;
        $scope.IsFinish1DoSysOpt2 = false;
        $scope.IsFinish1DoSysOpt3 = false;
        $scope.Finish1DoSysOpt3Remark = "";
        $scope.IsFinish1DoSysOpt4 = false;
        $scope.IsFinish1DoSysOpt5 = false;
        $scope.IsFinish1DoSysOpt6 = false;
        $scope.Finish1DoSysOpt6Remark = "";
        $scope.IsFinish1DoSysOpt7 = false;
        $scope.Finish1DoSysOpt7Remark = "";
        $scope.IsFinish1DoSysOpt8 = false;
        $scope.IsFinish1DoSysOpt9 = false;
        $scope.IsFinish1DoSysOpt10 = false;
        $scope.IsFinish1DoEduOpt1 = false;
        $scope.IsFinish1DoEduOpt2 = false;
        $scope.IsFinish1DoEduOpt3 = false;
        $scope.IsFinish1DoEduOpt4 = false;

        // Finish 2
        $scope.Finish2Reason = 1;
        $scope.Finish2ReasonOther = "";
        $scope.Finish2ReasonRemark = "";
        $scope.IsFinish2DoSysOpt1 = false;
        $scope.Finish2DoSysOpt1Remark = "";
        $scope.IsFinish2DoSysOpt2 = false;
        $scope.IsFinish2DoSysOpt3 = false;
        $scope.IsFinish2DoSysOpt4 = false;
        $scope.IsFinish2DoSysOpt5 = false;
        $scope.IsFinish2DoEduOpt1 = false;
        $scope.IsFinish2DoEduOpt2 = false;
        $scope.IsFinish2DoEduOpt3 = false;
        $scope.IsFinish2DoEduOpt4 = false;

        // Finish 3
        $scope.IsFinish3DoSysOpt1 = false;
        $scope.IsFinish3DoSysOpt2 = false;
        $scope.IsFinish3DoSysOpt3 = false;
        $scope.Finish3DoSysOpt3Remark = "";
        $scope.IsFinish3DoSysOpt4 = false;
        $scope.Finish3DoSysOpt4Remark = "";
        $scope.IsFinish3DoSysOpt5 = false;
        $scope.IsFinish3DoEduOpt1 = false;
        $scope.IsFinish3DoEduOpt2 = false;
        $scope.IsFinish3DoEduOpt3 = false;
        $scope.IsFinish3DoEduOpt4 = false;

        // Finish 4
        $scope.Finish4Reason = 1;
        $scope.Finish4ReasonOther = "";
        $scope.Finish4ReasonRemark = "";
        $scope.IsFinish4DoSysOpt1 = false;
        $scope.IsFinish4DoEduOpt1 = false;
        $scope.IsFinish4DoEduOpt2 = false;
        $scope.IsFinish4DoEduOpt3 = false;
        $scope.IsFinish4DoEduOpt4 = false;

        // Finish 4
        $scope.Finish5Reason = 1;
        $scope.Finish5ReasonOther = "";
        $scope.Finish5ReasonRemark = "";
        $scope.IsFinish5DoSysOpt1 = false;
        $scope.Finish5DoSysOpt1Remark = "";
        $scope.IsFinish5DoSysOpt2 = false;
        $scope.IsFinish5DoSysOpt3 = false;
        $scope.IsFinish5DoSysOpt4 = false;
        $scope.IsFinish5DoEduOpt1 = false;
        $scope.IsFinish5DoEduOpt2 = false;
        $scope.IsFinish5DoEduOpt3 = false;
        $scope.IsFinish5DoEduOpt4 = false;

        $scope.FinishDoOther = "";
        $scope.FinishDateTime = "";

        $scope.ExamineDatetime = "";
        $scope.RealFinishDateTime = "";

        // $scope.Opinion = null;
    }
    // Switch to Edit(Insert) Mode End

    // Switch to Query Mode Start
    $scope.closeEdit = function() {
        $("#divEdit").hide();
        $("#divReview").hide();
        $("#divQuery").show();
        $scope.btnIns = false;
        $scope.btnUpd = false;
        $scope.View_Show = false;
        $scope.IsSeeOpinion = false;
        $scope.handle = false;
    }
    // Switch to Query Mode End

    // Switch to Edit(Update) Mode Start
    $scope.editData = function(id, action) {
    		$scope.HandleInformationDisable = false;
        bootbox.dialog({
            closeButton: false,
            message: '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading
        });
        var request = {
            Id: id,
            TableName: "notification"
        };
        
        $http.post('./api/a01/query/id', request, csrf_config).then(function(response) {
        		$scope.ContactorName = response.data[0].ContactorName;                             
            $scope.ContactorUnitName = response.data[0].ContactorUnitName;                 
            $scope.MainUnit1Name = response.data[0].MainUnit1Name;           
            $scope.MainUnit2Name = response.data[0].MainUnit2Name;
            $scope.Status = response.data[0].Status;
            $scope.Id = response.data[0].Id;
            $scope.ApplyDateTime = moment(response.data[0].ApplyDateTime).format("YYYY-MM-DD HH:mm:ss");
            $scope.PostId = response.data[0].PostId;
            $scope.ContactorUnit = response.data[0].ContactorUnit;
            $scope.MainUnit1 = response.data[0].MainUnit1;
            $scope.MainUnit2 = response.data[0].MainUnit2;
            $scope.ContactorId = response.data[0].ContactorId;
            $scope.ContactorTel = response.data[0].ContactorTel;
            $scope.ContactorFax = response.data[0].ContactorFax;
            $scope.ContactorEmail = response.data[0].ContactorEmail;
            $scope.IsSub = response.data[0].IsSub;
            $scope.IsSubUnitName = response.data[0].IsSubUnitName;
            $scope.EventDateTime = moment(response.data[0].EventDateTime).format("YYYY-MM-DD HH:mm:ss");
            $scope.HostAmount = response.data[0].HostAmount;
            $scope.ServerAmount = response.data[0].ServerAmount;
            $scope.InformationAmount = response.data[0].InformationAmount;
            $scope.OtherDeviceAmount = response.data[0].OtherDeviceAmount;
            
            console.log(response.data[0].notificationAsset);
            $scope.otAddDataList = response.data[0].notificationAsset;
            
            
            if (response.data[0].OtherDeviceName == ""  || response.data[0].OtherDeviceName == null)
            		$scope.OtherDeviceName = "無" 
            else
            		$scope.OtherDeviceName = response.data[0].OtherDeviceName;
            if (response.data[0].DeviceRemark == "" || response.data[0].DeviceRemark == null)
        			$scope.DeviceRemark = "無" 
        		else
        			$scope.DeviceRemark = response.data[0].DeviceRemark;
                        
            	$scope.AssessDamage = response.data[0].AssessDamage
            		
            if (response.data[0].AssessDamageRemark == ""  || response.data[0].AssessDamageRemark == null)
    				$scope.AssessDamageRemark = "無" 
    			else
    				$scope.AssessDamageRemark = response.data[0].AssessDamageRemark; 
            	
            $scope.IsFinishDoSysOptRemark = response.data[0].IsFinishDoSysOptRemark;
            $scope.IsFinishDoEduOptRemark = response.data[0].IsFinishDoEduOptRemark;
            $scope.FinishDoSysOptRemark = response.data[0].FinishDoSysOptRemark;
            $scope.FinishDoEduOptRemark = response.data[0].FinishDoEduOptRemark;
            
            $scope.IpExternal = response.data[0].IpExternal;
            $scope.IpInternal = response.data[0].IpInternal;
            $scope.WebUrl = response.data[0].WebUrl;
            $scope.IsOsOpt1 = response.data[0].IsOsOpt1;
            $scope.IsOsOpt2 = response.data[0].IsOsOpt2;
            $scope.IsOsOpt3 = response.data[0].IsOsOpt3;
            $scope.IsOsOpt3Other = response.data[0].IsOsOpt3Other;
            $scope.IsGuardOpt1 = response.data[0].IsGuardOpt1;
            $scope.IsGuardOpt2 = response.data[0].IsGuardOpt2;
            $scope.IsGuardOpt3 = response.data[0].IsGuardOpt3;
            $scope.IsGuardOpt4 = response.data[0].IsGuardOpt4;
            $scope.IsGuardOpt4Other = response.data[0].IsGuardOpt4Other;
            $scope.SocOpt = response.data[0].SocOpt;
            $scope.SocOptCompany = response.data[0].SocOptCompany;
            $scope.IsIsms = response.data[0].IsIsms;
            $scope.MaintainCompany = response.data[0].MaintainCompany;
            $scope.IsTest = response.data[0].IsTest;
            $scope.CeffectLevel = response.data[0].CeffectLevel;
            $scope.IeffectLevel = response.data[0].IeffectLevel;
            $scope.AeffectLevel = response.data[0].AeffectLevel;
            $scope.change_effect();
            $scope.EventType = response.data[0].EventType;
            $scope.IsEventType1Opt1 = response.data[0].IsEventType1Opt1;
            $scope.IsEventType1Opt2 = response.data[0].IsEventType1Opt2;
            $scope.IsEventType1Opt3 = response.data[0].IsEventType1Opt3;
            $scope.IsEventType1Opt4 = response.data[0].IsEventType1Opt4;
            $scope.IsEventType1Opt5 = response.data[0].IsEventType1Opt5;
            $scope.IsEventType1Opt6 = response.data[0].IsEventType1Opt6;
            $scope.IsEventType2Opt1 = response.data[0].IsEventType2Opt1;
            $scope.IsEventType2Opt2 = response.data[0].IsEventType2Opt2;
            $scope.IsEventType2Opt3 = response.data[0].IsEventType2Opt3;
            $scope.IsEventType2Opt4 = response.data[0].IsEventType2Opt4;
            $scope.IsEventType2Opt5 = response.data[0].IsEventType2Opt5;
            $scope.IsEventType3Opt1 = response.data[0].IsEventType3Opt1;
            $scope.IsEventType3Opt2 = response.data[0].IsEventType3Opt2;
            $scope.IsEventType4Opt1 = response.data[0].IsEventType4Opt1;
            $scope.IsEventType4Opt2 = response.data[0].IsEventType4Opt2;
            $scope.IsEventType4Opt3 = response.data[0].IsEventType4Opt3;
            $scope.IsEventType4Opt4 = response.data[0].IsEventType4Opt4;
            $scope.EventType5Other = response.data[0].EventType5Other;
            $scope.EventRemark = response.data[0].EventRemark;
            $scope.IsAffectOthers = response.data[0].IsAffectOthers;
            $scope.AffectOther1 = response.data[0].AffectOther1;
            $scope.AffectOther2 = response.data[0].AffectOther2;
            $scope.AffectOther3 = response.data[0].AffectOther3;
            $scope.AffectOther4 = response.data[0].AffectOther4;
            $scope.AffectOther5 = response.data[0].AffectOther5;
            $scope.AffectOther6 = response.data[0].AffectOther6;
            $scope.AffectOther7 = response.data[0].AffectOther7;
            $scope.AffectOther8 = response.data[0].AffectOther8;
            $scope.EventSource = response.data[0].EventSource;
            $scope.EventSourceNo = response.data[0].EventSourceNo;
            $scope.EventSourceOther = response.data[0].EventSourceOther;

            // Res All
            $scope.ResResult = response.data[0].ResResult;
            $scope.ResDescription = response.data[0].ResDescription;
            $scope.ResControlTime = moment(response.data[0].ResControlTime).format("YYYY-MM-DD HH:mm:ss");
            
            // Res 1
            $scope.IsRes1LogOpt1 = response.data[0].IsRes1LogOpt1;
            $scope.Res1LogOpt1 = response.data[0].Res1LogOpt1;
            $scope.Res1LogOpt1Other = response.data[0].Res1LogOpt1Other;
            $scope.IsRes1LogOpt2 = response.data[0].IsRes1LogOpt2;
            $scope.Res1LogOpt2 = response.data[0].Res1LogOpt2;
            $scope.Res1LogOpt2Other = response.data[0].Res1LogOpt2Other;
            $scope.IsRes1LogOpt5 = response.data[0].IsRes1LogOpt5;
            $scope.Res1LogOpt5 = response.data[0].Res1LogOpt5;
            $scope.Res1LogOpt5Other = response.data[0].Res1LogOpt5Other;
            $scope.IsRes1LogOpt3 = response.data[0].IsRes1LogOpt3;
            $scope.Res1LogOpt3Amount = response.data[0].Res1LogOpt3Amount;
            $scope.IsRes1LogOpt4 = response.data[0].IsRes1LogOpt4;
            $scope.Res1LogOpt4Remark = response.data[0].Res1LogOpt4Remark;
            $scope.IsRes1EvaOpt1 = response.data[0].IsRes1EvaOpt1;
            $scope.Res1EvaOpt1Remark = response.data[0].Res1EvaOpt1Remark;
            $scope.IsRes1EvaOpt2 = response.data[0].IsRes1EvaOpt2;
            $scope.Res1EvaOpt2Remark = response.data[0].Res1EvaOpt2Remark;
            $scope.IsRes1EvaOpt3 = response.data[0].IsRes1EvaOpt3;
            $scope.Res1EvaOpt3Remark = response.data[0].Res1EvaOpt3Remark;
            $scope.IsRes1EvaOpt4 = response.data[0].IsRes1EvaOpt4;
            $scope.Res1EvaOpt4Remark = response.data[0].Res1EvaOpt4Remark;
            $scope.IsRes1EvaOpt5 = response.data[0].IsRes1EvaOpt5;
            $scope.IsRes1EvaOpt6 = response.data[0].IsRes1EvaOpt6;
            $scope.Res1EvaOpt6Type = response.data[0].Res1EvaOpt6Type;
            $scope.Res1EvaOpt6Amount = response.data[0].Res1EvaOpt6Amount;
            $scope.Res1EvaOpt6Remark = response.data[0].Res1EvaOpt6Remark;
            $scope.Res1EvaOpt6TypeOpt3Other = response.data[0].Res1EvaOpt6TypeOpt3Other;
            $scope.IsRes1EvaOpt7 = response.data[0].IsRes1EvaOpt7;
            $scope.Res1EvaOpt7Amount = response.data[0].Res1EvaOpt7Amount;
            $scope.Res1EvaOpt7Remark = response.data[0].Res1EvaOpt7Remark;
            $scope.IsRes1EvaOpt8 = response.data[0].IsRes1EvaOpt8;
            $scope.Res1EvaOpt8Remark = response.data[0].Res1EvaOpt8Remark;
            $scope.IsRes1DoOpt1 = response.data[0].IsRes1DoOpt1;
            $scope.Res1DoOpt1Amount = response.data[0].Res1DoOpt1Amount;
            $scope.IsRes1DoOpt2 = response.data[0].IsRes1DoOpt2;
            $scope.Res1DoOpt2Remark = response.data[0].Res1DoOpt2Remark;
            $scope.IsRes1DoOpt3 = response.data[0].IsRes1DoOpt3;
            $scope.Res1DoOpt3Remark = response.data[0].Res1DoOpt3Remark;
            $scope.IsRes1DoOpt4 = response.data[0].IsRes1DoOpt4;
            $scope.Res1DoOpt4Remark = response.data[0].Res1DoOpt4Remark;
            $scope.IsRes1DoOpt5 = response.data[0].IsRes1DoOpt5;
            $scope.Res1DoOpt5Amount = response.data[0].Res1DoOpt5Amount;
            $scope.IsRes1DoOpt6 = response.data[0].IsRes1DoOpt6;
            $scope.IsRes1DoOpt7 = response.data[0].IsRes1DoOpt7;
            $scope.IsRes1DoOpt8 = response.data[0].IsRes1DoOpt8;
            $scope.IsRes1DoOpt9 = response.data[0].IsRes1DoOpt9;
            $scope.IsRes1DoOpt9EngineOpt1 = response.data[0].IsRes1DoOpt9EngineOpt1;
            $scope.IsRes1DoOpt9EngineOpt2 = response.data[0].IsRes1DoOpt9EngineOpt2;
            $scope.IsRes1DoOpt9EngineOpt3 = response.data[0].IsRes1DoOpt9EngineOpt3;
            $scope.IsRes1DoOpt9EngineOpt4 = response.data[0].IsRes1DoOpt9EngineOpt4;
            $scope.IsRes1DoOpt9EngineOpt5 = response.data[0].IsRes1DoOpt9EngineOpt5;
            $scope.IsRes1DoOpt9EngineOpt6 = response.data[0].IsRes1DoOpt9EngineOpt6;
            $scope.Res1DoOpt9EngineOpt6Other = response.data[0].Res1DoOpt9EngineOpt6Other;
            $scope.IsRes1DoOpt10 = response.data[0].IsRes1DoOpt10;
            $scope.Res1DoOpt10Date = moment(response.data[0].Res1DoOpt10Date).format("YYYY-MM-DD");
            $scope.IsRes1DoOpt11 = response.data[0].IsRes1DoOpt11;
            $scope.Res1DoOpt11Date = moment(response.data[0].Res1DoOpt11Date).format("YYYY-MM-DD");
            $scope.IsRes1DoOpt12 = response.data[0].IsRes1DoOpt12;
            $scope.Res1DoOpt12Remark = response.data[0].Res1DoOpt12Remark;

            // Res 2
            $scope.IsRes2LogOpt1 = response.data[0].IsRes2LogOpt1;
            $scope.Res2LogOpt1 = response.data[0].Res2LogOpt1;
            $scope.Res2LogOpt1Other = response.data[0].Res2LogOpt1Other;
            $scope.IsRes2LogOpt2 = response.data[0].IsRes2LogOpt2;
            $scope.Res2LogOpt2 = response.data[0].Res2LogOpt2;
            $scope.Res2LogOpt2Other = response.data[0].Res2LogOpt2Other;
            $scope.IsRes2LogOpt3 = response.data[0].IsRes2LogOpt3;
            $scope.Res2LogOpt3Amount = response.data[0].Res2LogOpt3Amount;
            $scope.IsRes2LogOpt4 = response.data[0].IsRes2LogOpt4;
            $scope.Res2LogOpt4Remark = response.data[0].Res2LogOpt4Remark;
            $scope.IsRes2EvaOpt1 = response.data[0].IsRes2EvaOpt1;
            $scope.Res2EvaOpt1Remark = response.data[0].Res2EvaOpt1Remark;
            $scope.IsRes2EvaOpt2 = response.data[0].IsRes2EvaOpt2;
            $scope.Res2EvaOpt2Remark = response.data[0].Res2EvaOpt2Remark;
            $scope.IsRes2EvaOpt3 = response.data[0].IsRes2EvaOpt3;
            $scope.Res2EvaOpt3Remark = response.data[0].Res2EvaOpt3Remark;
            $scope.IsRes2EvaOpt4 = response.data[0].IsRes2EvaOpt4;
            $scope.Res2EvaOpt4Remark = response.data[0].Res2EvaOpt4Remark;
            $scope.IsRes2EvaOpt5 = response.data[0].IsRes2EvaOpt5;
            $scope.Res2EvaOpt5Remark = response.data[0].Res2EvaOpt5Remark;
            $scope.IsRes2DoOpt1 = response.data[0].IsRes2DoOpt1;
            $scope.Res2DoOpt1Amount = response.data[0].Res2DoOpt1Amount;
            $scope.Res2DoOpt1Remark = response.data[0].Res2DoOpt1Remark;
            $scope.IsRes2DoOpt2 = response.data[0].IsRes2DoOpt2;
            $scope.Res2DoOpt2Remark = response.data[0].Res2DoOpt2Remark;
            $scope.IsRes2DoOpt3 = response.data[0].IsRes2DoOpt3;
            $scope.Res2DoOpt3Remark = response.data[0].Res2DoOpt3Remark;
            $scope.IsRes2DoOpt4 = response.data[0].IsRes2DoOpt4;
            $scope.IsRes2DoOpt5 = response.data[0].IsRes2DoOpt5;
            $scope.Res2DoOpt5Date = response.data[0].Res2DoOpt5Date;
            $scope.IsRes2DoOpt6 = response.data[0].IsRes2DoOpt6;
            $scope.Res2DoOpt6Amount = response.data[0].Res2DoOpt6Amount;
            $scope.IsRes2DoOpt7 = response.data[0].IsRes2DoOpt7;
            $scope.Res2DoOpt7Remark = response.data[0].Res2DoOpt7Remark;

            // Res 3
            $scope.IsRes3LogOpt1 = response.data[0].IsRes3LogOpt1;
            $scope.Res3LogOpt1 = response.data[0].Res3LogOpt1;
            $scope.Res3LogOpt1Other = response.data[0].Res3LogOpt1Other;
            $scope.IsRes3LogOpt2 = response.data[0].IsRes3LogOpt2;
            $scope.Res3LogOpt2 = response.data[0].Res3LogOpt2;
            $scope.Res3LogOpt2Other = response.data[0].Res3LogOpt2Other;
            $scope.IsRes3LogOpt3 = response.data[0].IsRes3LogOpt3;
            $scope.Res3LogOpt3Amount = response.data[0].Res3LogOpt3Amount;
            $scope.IsRes3LogOpt4 = response.data[0].IsRes3LogOpt4;
            $scope.Res3LogOpt4Remark = response.data[0].Res3LogOpt4Remark;
            $scope.IsRes3EvaOpt1 = response.data[0].IsRes3EvaOpt1;
            $scope.Res3EvaOpt1Amount = response.data[0].Res3EvaOpt1Amount;
            $scope.IsRes3EvaOpt2 = response.data[0].IsRes3EvaOpt2;
            $scope.Res3EvaOpt2Remark = response.data[0].Res3EvaOpt2Remark;
            $scope.IsRes3EvaOpt3 = response.data[0].IsRes3EvaOpt3;
            $scope.Res3EvaOpt3Remark = response.data[0].Res3EvaOpt3Remark;
            $scope.IsRes3DoOpt1 = response.data[0].IsRes3DoOpt1;
            $scope.Res3DoOpt1Remark = response.data[0].Res3DoOpt1Remark;
            $scope.IsRes3DoOpt2 = response.data[0].IsRes3DoOpt2;
            $scope.IsRes3DoOpt3 = response.data[0].IsRes3DoOpt3;
            $scope.Res3DoOpt3Remark = response.data[0].Res3DoOpt3Remark;
            $scope.IsRes3DoOpt4 = response.data[0].IsRes3DoOpt4;
            $scope.Res3DoOpt4Remark = response.data[0].Res3DoOpt4Remark;

            // Res 4
            $scope.IsRes4LogOpt1 = response.data[0].IsRes4LogOpt1;
            $scope.Res4LogOpt1 = response.data[0].Res4LogOpt1;
            $scope.Res4LogOpt1Remark = response.data[0].Res4LogOpt1Remark;
            $scope.IsRes4EvaOpt1 = response.data[0].IsRes4EvaOpt1;
            $scope.Res4EvaOpt1 = response.data[0].Res4EvaOpt1;
            $scope.Res4EvaOpt1Amount = response.data[0].Res4EvaOpt1Amount;
            $scope.IsRes4EvaOpt2 = response.data[0].IsRes4EvaOpt2;
            $scope.Res4EvaOpt2Remark = response.data[0].Res4EvaOpt2Remark;
            $scope.IsRes4EvaOpt3 = response.data[0].IsRes4EvaOpt3;
            $scope.Res4EvaOpt3Remark = response.data[0].Res4EvaOpt3Remark;
            $scope.IsRes4DoOpt1 = response.data[0].IsRes4DoOpt1;
            $scope.IsRes4DoOpt2 = response.data[0].IsRes4DoOpt2;
            $scope.IsRes4DoOpt4 = response.data[0].IsRes4DoOpt4;
            $scope.IsRes4DoOpt3 = response.data[0].IsRes4DoOpt3;
            $scope.Res4DoOpt3Remark = response.data[0].Res4DoOpt3Remark;

            // Res 5
            $scope.IsRes5LogOpt1 = response.data[0].IsRes5LogOpt1;
            $scope.Res5LogOpt1 = response.data[0].Res5LogOpt1;
            $scope.Res5LogOpt1Other = response.data[0].Res5LogOpt1Other;
            $scope.IsRes5LogOpt2 = response.data[0].IsRes5LogOpt2;
            $scope.Res5LogOpt2 = response.data[0].Res5LogOpt2;
            $scope.Res5LogOpt2Other = response.data[0].Res5LogOpt2Other;
            $scope.IsRes5LogOpt3 = response.data[0].IsRes5LogOpt3;
            $scope.Res5LogOpt3Amount = response.data[0].Res5LogOpt3Amount;
            $scope.IsRes5LogOpt4 = response.data[0].IsRes5LogOpt4;
            $scope.Res5LogOpt4Remark = response.data[0].Res5LogOpt4Remark;
            $scope.IsRes5EvaOpt1 = response.data[0].IsRes5EvaOpt1;
            $scope.Res5EvaOpt1Remark = response.data[0].Res5EvaOpt1Remark;
            $scope.IsRes5EvaOpt2 = response.data[0].IsRes5EvaOpt2;
            $scope.Res5EvaOpt2Remark = response.data[0].Res5EvaOpt2Remark;
            $scope.IsRes5EvaOpt3 = response.data[0].IsRes5EvaOpt3;
            $scope.Res5EvaOpt3Remark = response.data[0].Res5EvaOpt3Remark;
            $scope.IsRes5EvaOpt4 = response.data[0].IsRes5EvaOpt4;
            $scope.Res5EvaOpt4Remark = response.data[0].Res5EvaOpt4Remark;
            $scope.IsRes5EvaOpt5 = response.data[0].IsRes5EvaOpt5;
            $scope.Res5EvaOpt5Remark = response.data[0].Res5EvaOpt5Remark;
            $scope.IsRes5DoOpt1 = response.data[0].IsRes5DoOpt1;
            $scope.Res5DoOpt1Amount = response.data[0].Res5DoOpt1Amount;
            $scope.Res5DoOpt1Remark = response.data[0].Res5DoOpt1Remark;
            $scope.IsRes5DoOpt2 = response.data[0].IsRes5DoOpt2;
            $scope.Res5DoOpt2Remark = response.data[0].Res5DoOpt2Remark;
            $scope.IsRes5DoOpt3 = response.data[0].IsRes5DoOpt3;
            $scope.Res5DoOpt3Remark = response.data[0].Res5DoOpt3Remark;
            $scope.IsRes5DoOpt4 = response.data[0].IsRes5DoOpt4;
            $scope.IsRes5DoOpt5 = response.data[0].IsRes5DoOpt5;
            $scope.Res5DoOpt5Date = moment(response.data[0].Res5DoOpt5Date).format("YYYY-MM-DD");
            $scope.IsRes5DoOpt6 = response.data[0].IsRes5DoOpt6;
            $scope.Res5DoOpt6Amount = response.data[0].Res5DoOpt6Amount;
            $scope.IsRes5DoOpt7 = response.data[0].IsRes5DoOpt7;
            $scope.Res5DoOpt7Remark = response.data[0].Res5DoOpt7Remark;

            $scope.IsNeedSupport = response.data[0].IsNeedSupport;
            $scope.NeedSupportRemark = response.data[0].NeedSupportRemark;
            
            $scope.IsHandledByMyself = response.data[0].IsHandledByMyself; 
                        
            if (response.data[0].HandleInformationId==0 || response.data[0].HandleInformationId==null){            		
            		$scope.HandleInformationId = null            		
            }
            	else{
            		$scope.HandleInformationId = response.data[0].HandleInformationId
            		$scope.HandleInformationDisable = true;
            	}
            	$scope.HandleCompany = ""
            angular.forEach($scope.HandleInformation, function(value, key) {                   
            		if (value.Id == $scope.HandleInformationId)
                		$scope.HandleCompany = value.Name;                              
            });                        
            // Finish 1
            $scope.Finish1Reason = response.data[0].Finish1Reason;
            $scope.Finish1ReasonOther = response.data[0].Finish1ReasonOther;
            $scope.Finish1ReasonToDo = response.data[0].Finish1ReasonToDo;
            $scope.IsFinish1DoSysOpt1 = response.data[0].IsFinish1DoSysOpt1;
            $scope.IsFinish1DoSysOpt2 = response.data[0].IsFinish1DoSysOpt2;
            $scope.IsFinish1DoSysOpt3 = response.data[0].IsFinish1DoSysOpt3;
            $scope.Finish1DoSysOpt3Remark = response.data[0].Finish1DoSysOpt3Remark;
            $scope.IsFinish1DoSysOpt4 = response.data[0].IsFinish1DoSysOpt4;
            $scope.IsFinish1DoSysOpt5 = response.data[0].IsFinish1DoSysOpt5;
            $scope.IsFinish1DoSysOpt6 = response.data[0].IsFinish1DoSysOpt6;
            $scope.Finish1DoSysOpt6Remark = response.data[0].Finish1DoSysOpt6Remark;
            $scope.IsFinish1DoSysOpt7 = response.data[0].IsFinish1DoSysOpt7;
            $scope.Finish1DoSysOpt7Remark = response.data[0].Finish1DoSysOpt7Remark;
            $scope.IsFinish1DoSysOpt8 = response.data[0].IsFinish1DoSysOpt8;
            $scope.IsFinish1DoSysOpt9 = response.data[0].IsFinish1DoSysOpt9;
            $scope.IsFinish1DoSysOpt10 = response.data[0].IsFinish1DoSysOpt10;
            $scope.IsFinish1DoEduOpt1 = response.data[0].IsFinish1DoEduOpt1;
            $scope.IsFinish1DoEduOpt2 = response.data[0].IsFinish1DoEduOpt2;
            $scope.IsFinish1DoEduOpt3 = response.data[0].IsFinish1DoEduOpt3;
            $scope.IsFinish1DoEduOpt4 = response.data[0].IsFinish1DoEduOpt4;

            // Finish 2
            $scope.Finish2Reason = response.data[0].Finish2Reason;
            $scope.Finish2ReasonOther = response.data[0].Finish2ReasonOther;
            $scope.Finish2ReasonRemark = response.data[0].Finish2ReasonRemark;
            $scope.IsFinish2DoSysOpt1 = response.data[0].IsFinish2DoSysOpt1;
            $scope.Finish2DoSysOpt1Remark = response.data[0].Finish2DoSysOpt1Remark;
            $scope.IsFinish2DoSysOpt2 = response.data[0].IsFinish2DoSysOpt2;
            $scope.IsFinish2DoSysOpt3 = response.data[0].IsFinish2DoSysOpt3;
            $scope.IsFinish2DoSysOpt4 = response.data[0].IsFinish2DoSysOpt4;
            $scope.IsFinish2DoSysOpt5 = response.data[0].IsFinish2DoSysOpt5;
            $scope.IsFinish2DoEduOpt1 = response.data[0].IsFinish2DoEduOpt1;
            $scope.IsFinish2DoEduOpt2 = response.data[0].IsFinish2DoEduOpt2;
            $scope.IsFinish2DoEduOpt3 = response.data[0].IsFinish2DoEduOpt3;
            $scope.IsFinish2DoEduOpt4 = response.data[0].IsFinish2DoEduOpt4;

            // Finish 3
            $scope.IsFinish3DoSysOpt1 = response.data[0].IsFinish3DoSysOpt1;
            $scope.IsFinish3DoSysOpt2 = response.data[0].IsFinish3DoSysOpt2;
            $scope.IsFinish3DoSysOpt3 = response.data[0].IsFinish3DoSysOpt3;
            $scope.Finish3DoSysOpt3Remark = response.data[0].Finish3DoSysOpt3Remark;
            $scope.IsFinish3DoSysOpt4 = response.data[0].IsFinish3DoSysOpt4;
            $scope.Finish3DoSysOpt4Remark = response.data[0].Finish3DoSysOpt4Remark;
            $scope.IsFinish3DoSysOpt5 = response.data[0].IsFinish3DoSysOpt5;
            $scope.IsFinish3DoEduOpt1 = response.data[0].IsFinish3DoEduOpt1;
            $scope.IsFinish3DoEduOpt2 = response.data[0].IsFinish3DoEduOpt2;
            $scope.IsFinish3DoEduOpt3 = response.data[0].IsFinish3DoEduOpt3;
            $scope.IsFinish3DoEduOpt4 = response.data[0].IsFinish3DoEduOpt4;

            // Finish 4
            $scope.Finish4Reason = response.data[0].Finish4Reason;
            $scope.Finish4ReasonOther = response.data[0].Finish4ReasonOther;
            $scope.Finish4ReasonRemark = response.data[0].Finish4ReasonRemark;
            $scope.IsFinish4DoSysOpt1 = response.data[0].IsFinish4DoSysOpt1;
            $scope.IsFinish4DoEduOpt1 = response.data[0].IsFinish4DoEduOpt1;
            $scope.IsFinish4DoEduOpt2 = response.data[0].IsFinish4DoEduOpt2;
            $scope.IsFinish4DoEduOpt3 = response.data[0].IsFinish4DoEduOpt3;
            $scope.IsFinish4DoEduOpt4 = response.data[0].IsFinish4DoEduOpt4;

            // Finish 4
            $scope.Finish5Reason = response.data[0].Finish5Reason;
            $scope.Finish5ReasonOther = response.data[0].Finish5ReasonOther;
            $scope.Finish5ReasonRemark = response.data[0].Finish5ReasonRemark;
            $scope.IsFinish5DoSysOpt1 = response.data[0].IsFinish5DoSysOpt1;
            $scope.Finish5DoSysOpt1Remark = response.data[0].Finish5DoSysOpt1Remark;
            $scope.IsFinish5DoSysOpt2 = response.data[0].IsFinish5DoSysOpt2;
            $scope.IsFinish5DoSysOpt3 = response.data[0].IsFinish5DoSysOpt3;
            $scope.IsFinish5DoSysOpt4 = response.data[0].IsFinish5DoSysOpt4;
            $scope.IsFinish5DoEduOpt1 = response.data[0].IsFinish5DoEduOpt1;
            $scope.IsFinish5DoEduOpt2 = response.data[0].IsFinish5DoEduOpt2;
            $scope.IsFinish5DoEduOpt3 = response.data[0].IsFinish5DoEduOpt3;
            $scope.IsFinish5DoEduOpt4 = response.data[0].IsFinish5DoEduOpt4;

            $scope.FinishDoOther = response.data[0].FinishDoOther;
            $scope.FinishDateTime = moment(response.data[0].FinishDateTime).format("YYYY-MM-DD HH:mm:ss");

            $scope.ExamineDatetime = response.data[0].ExamineDatetime;
            $scope.RealFinishDateTime = response.data[0].RealFinishDateTime;

            $scope.IsCC3 = response.data[0].IsCC3;
            $scope.IsReview3 = response.data[0].IsReview3;
            $scope.IsCC5 = response.data[0].IsCC5;
            $scope.IsReview5 = response.data[0].IsReview5;

            if (action != 'view'){
            		$scope.IsSeeIsCC3 = response.data[0].IsSeeIsCC3;
            		$scope.IsSeeIsReview3 = false;
            		$scope.IsSeeIsCC5 = response.data[0].IsSeeIsCC5;
            		$scope.IsSeeIsReview5 = false;
            }
            else{
            		$scope.IsSeeIsCC3 = true
                $scope.IsSeeIsReview3 = false
                $scope.IsSeeIsCC5 = $scope.IsSeeIsCC3
                $scope.IsSeeIsReview5 = $scope.IsSeeIsReview3
            }
            $scope.IsNeedSaleCC3 = response.data[0].IsNeedSaleCC3;
            $scope.IsNeedSaleReview3 = response.data[0].IsNeedSaleReview3;
            $scope.IsNeedSaleCC5 = response.data[0].IsNeedSaleCC5;
            $scope.IsNeedSaleReview5 = response.data[0].IsNeedSaleReview5;
            $scope.PreStatus = response.data[0].Status;
            $scope.NotificationLog = response.data[0].NotificationLog;
            $scope.NotificationLevelLog = response.data[0].NotificationLevelLog;
            $scope.CreateId = response.data[0].CreateId;
            $scope.CreateTime = response.data[0].CreateTime;
            $scope.ModifyId = response.data[0].ModifyId;
            $scope.ModifyTime = response.data[0].ModifyTime;
            
            
            

            // $scope.Opinion = null
            bootbox.hideAll();
        }).catch(function() {
            bootbox.hideAll();
            bootbox.alert({
                message: globalReadDataFail,
                buttons: {
                    ok: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-danger'
                    }
                },
                callback: function() {}
            });
        }).finally(function() {
            //$scope.queryMemberInfoById($scope.ContactorId);
        });
        $scope.openEdit(action);
    }
    // Switch to Edit(Update) Mode End

    // Insert Item Start
    $scope.createData = function(exit, check) {
    	bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
		});
        $scope.IsWriteProcessLog = false
        if (check) {
            if ($scope.IsReview3)
                $scope.Status = 31;
            else if ($scope.IsNeedSaleReview3)
                $scope.Status = 32;
            else
                $scope.Status = 33;
            $scope.IsWriteProcessLog = true
        } else
            $scope.Status = 1;
       
        var request = {
            IsCC3: $scope.IsCC3,
            //建立時依照cc3來改變cc5
            IsCC5: $scope.IsCC3,            
            IsReview3: $scope.IsReview3,
             //建立時依照Review3來改變Review5
            IsReview5: $scope.IsReview3,
            Status: $scope.Status,
            PreStatus: 1,
            TableName: "notification",
            Pre: "HISAC",
            IsWriteProcessLog: $scope.IsWriteProcessLog,
            ApplyDateTime: $scope.ApplyDateTime,
            PostId: $scope.PostId,
            ContactorUnit: $scope.ContactorUnit,
            MainUnit1: $scope.MainUnit1,
            MainUnit2: $scope.MainUnit2,
            ContactorId: $scope.ContactorId,
            ContactorTel: $scope.ContactorTel,
            ContactorFax: $scope.ContactorFax,
            ContactorEmail: $scope.ContactorEmail,
            IsSub: $scope.IsSub,
            IsSubUnitName: $scope.IsSubUnitName,
            EventDateTime: $scope.EventDateTime,
            HostAmount: $scope.ServerAmount + $scope.InformationAmount + $scope.OtherDeviceAmount,
            ServerAmount: $scope.ServerAmount,
            InformationAmount : $scope.InformationAmount,
            OtherDeviceAmount : $scope.OtherDeviceAmount,
            OtherDeviceName : $scope.OtherDeviceName,
            DeviceRemark : $scope.DeviceRemark,
            AssessDamage : $scope.AssessDamage,
            AssessDamageRemark : $scope.AssessDamageRemark,
            IsFinishDoSysOptRemark : $scope.IsFinishDoSysOptRemark,
            IsFinishDoEduOptRemark : $scope.IsFinishDoEduOptRemark,
            FinishDoSysOptRemark : $scope.FinishDoSysOptRemark,
        	    FinishDoEduOptRemark : $scope.FinishDoEduOptRemark,
            IpExternal: $scope.IpExternal,
            IpInternal: $scope.IpInternal,
            WebUrl: $scope.WebUrl,
            IsOsOpt1: $scope.IsOsOpt1,
            IsOsOpt2: $scope.IsOsOpt2,
            IsOsOpt3: $scope.IsOsOpt3,
            IsOsOpt3Other: $scope.IsOsOpt3Other,
            IsGuardOpt1: $scope.IsGuardOpt1,
            IsGuardOpt2: $scope.IsGuardOpt2,
            IsGuardOpt3: $scope.IsGuardOpt3,
            IsGuardOpt4: $scope.IsGuardOpt4,
            IsGuardOpt4Other: $scope.IsGuardOpt4Other,
            SocOpt: $scope.SocOpt,
            SocOptCompany: $scope.SocOptCompany,
            IsIsms: $scope.IsIsms,
            MaintainCompany: $scope.MaintainCompany,
            IsTest : $scope.IsTest,
            CeffectLevel: $scope.CeffectLevel,
            IeffectLevel: $scope.IeffectLevel,
            AeffectLevel: $scope.AeffectLevel,
            EffectLevel: $scope.EffectLevel,
            EventType: $scope.EventType,
            IsEventType1Opt1: $scope.IsEventType1Opt1,
            IsEventType1Opt2: $scope.IsEventType1Opt2,
            IsEventType1Opt3: $scope.IsEventType1Opt3,
            IsEventType1Opt4: $scope.IsEventType1Opt4,
            IsEventType1Opt5: $scope.IsEventType1Opt5,
            IsEventType1Opt6: $scope.IsEventType1Opt6,
            IsEventType2Opt1: $scope.IsEventType2Opt1,
            IsEventType2Opt2: $scope.IsEventType2Opt2,
            IsEventType2Opt3: $scope.IsEventType2Opt3,
            IsEventType2Opt4: $scope.IsEventType2Opt4,
            IsEventType2Opt5: $scope.IsEventType2Opt5,
            IsEventType3Opt1: $scope.IsEventType3Opt1,
            IsEventType3Opt2: $scope.IsEventType3Opt2,
            IsEventType4Opt1: $scope.IsEventType4Opt1,
            IsEventType4Opt2: $scope.IsEventType4Opt2,
            IsEventType4Opt3: $scope.IsEventType4Opt3,
            IsEventType4Opt4: $scope.IsEventType4Opt4,
            EventType5Other: $scope.EventType5Other,
            EventRemark: $scope.EventRemark,
            IsAffectOthers: $scope.IsAffectOthers,
            AffectOther1: $scope.AffectOther1,
            AffectOther2: $scope.AffectOther2,
            AffectOther3: $scope.AffectOther3,
            AffectOther4: $scope.AffectOther4,
            AffectOther5: $scope.AffectOther5,
            AffectOther6: $scope.AffectOther6,
            AffectOther7: $scope.AffectOther7,
            AffectOther8: $scope.AffectOther8,
            EventSource: $scope.EventSource,
            EventSourceNo: $scope.EventSourceNo,
            EventSourceOther: $scope.EventSourceOther,
            
            //OT資料新增
            
            OtAddDataList: $scope.otAddDataList,



            // Res All
            ResResult : $scope.ResResult,
            ResDescription : $scope.ResDescription,
            ResControlTime : $scope.ResControlTime,
            
            // Res 1
            IsRes1LogOpt1: $scope.IsRes1LogOpt1,
            Res1LogOpt1: $scope.Res1LogOpt1,
            Res1LogOpt1Other: $scope.Res1LogOpt1Other,
            IsRes1LogOpt2: $scope.IsRes1LogOpt2,
            Res1LogOpt2: $scope.Res1LogOpt2,
            Res1LogOpt2Other: $scope.Res1LogOpt2Other,
            IsRes1LogOpt5: $scope.IsRes1LogOpt5,
            Res1LogOpt5: $scope.Res1LogOpt5,
            Res1LogOpt5Other: $scope.Res1LogOpt5Other,
            IsRes1LogOpt3: $scope.IsRes1LogOpt3,
            Res1LogOpt3Amount: $scope.Res1LogOpt3Amount,
            IsRes1LogOpt4: $scope.IsRes1LogOpt4,
            Res1LogOpt4Remark: $scope.Res1LogOpt4Remark,
            IsRes1EvaOpt1: $scope.IsRes1EvaOpt1,
            Res1EvaOpt1Remark: $scope.Res1EvaOpt1Remark,
            IsRes1EvaOpt2: $scope.IsRes1EvaOpt2,
            Res1EvaOpt2Remark: $scope.Res1EvaOpt2Remark,
            IsRes1EvaOpt3: $scope.IsRes1EvaOpt3,
            Res1EvaOpt3Remark: $scope.Res1EvaOpt3Remark,
            IsRes1EvaOpt4: $scope.IsRes1EvaOpt4,
            Res1EvaOpt4Remark: $scope.Res1EvaOpt4Remark,
            IsRes1EvaOpt5: $scope.IsRes1EvaOpt5,
            IsRes1EvaOpt6: $scope.IsRes1EvaOpt6,
            Res1EvaOpt6Type: $scope.Res1EvaOpt6Type,
            Res1EvaOpt6Amount: $scope.Res1EvaOpt6Amount,
            Res1EvaOpt6Remark: $scope.Res1EvaOpt6Remark,
            Res1EvaOpt6TypeOpt3Other: $scope.Res1EvaOpt6TypeOpt3Other,
            IsRes1EvaOpt7: $scope.IsRes1EvaOpt7,
            Res1EvaOpt7Amount: $scope.Res1EvaOpt7Amount,
            Res1EvaOpt7Remark: $scope.Res1EvaOpt7Remark,
            IsRes1EvaOpt8: $scope.IsRes1EvaOpt8,
            Res1EvaOpt8Remark: $scope.Res1EvaOpt8Remark,
            IsRes1DoOpt1: $scope.IsRes1DoOpt1,
            Res1DoOpt1Amount: $scope.Res1DoOpt1Amount,
            IsRes1DoOpt2: $scope.IsRes1DoOpt2,
            Res1DoOpt2Remark: $scope.Res1DoOpt2Remark,
            IsRes1DoOpt3: $scope.IsRes1DoOpt3,
            Res1DoOpt3Remark: $scope.Res1DoOpt3Remark,
            IsRes1DoOpt4: $scope.IsRes1DoOpt4,
            Res1DoOpt4Remark: $scope.Res1DoOpt4Remark,
            IsRes1DoOpt5: $scope.IsRes1DoOpt5,
            Res1DoOpt5Amount: $scope.Res1DoOpt5Amount,
            IsRes1DoOpt6: $scope.IsRes1DoOpt6,
            IsRes1DoOpt7: $scope.IsRes1DoOpt7,
            IsRes1DoOpt8: $scope.IsRes1DoOpt8,
            IsRes1DoOpt9: $scope.IsRes1DoOpt9,
            IsRes1DoOpt9EngineOpt1: $scope.IsRes1DoOpt9EngineOpt1,
            IsRes1DoOpt9EngineOpt2: $scope.IsRes1DoOpt9EngineOpt2,
            IsRes1DoOpt9EngineOpt3: $scope.IsRes1DoOpt9EngineOpt3,
            IsRes1DoOpt9EngineOpt4: $scope.IsRes1DoOpt9EngineOpt4,
            IsRes1DoOpt9EngineOpt5: $scope.IsRes1DoOpt9EngineOpt5,
            IsRes1DoOpt9EngineOpt6: $scope.IsRes1DoOpt9EngineOpt6,
            Res1DoOpt9EngineOpt6Other: $scope.Res1DoOpt9EngineOpt6Other,
            IsRes1DoOpt10: $scope.IsRes1DoOpt10,
            Res1DoOpt10Date: $scope.Res1DoOpt10Date,
            IsRes1DoOpt11: $scope.IsRes1DoOpt11,
            Res1DoOpt11Date: $scope.Res1DoOpt11Date,
            IsRes1DoOpt12: $scope.IsRes1DoOpt12,
            Res1DoOpt12Remark: $scope.Res1DoOpt12Remark,

            // Res 2
            IsRes2LogOpt1: $scope.IsRes2LogOpt1,
            Res2LogOpt1: $scope.Res2LogOpt1,
            Res2LogOpt1Other: $scope.Res2LogOpt1Other,
            IsRes2LogOpt2: $scope.IsRes2LogOpt2,
            Res2LogOpt2: $scope.Res2LogOpt2,
            Res2LogOpt2Other: $scope.Res2LogOpt2Other,
            IsRes2LogOpt3: $scope.IsRes2LogOpt3,
            Res2LogOpt3Amount: $scope.Res2LogOpt3Amount,
            IsRes2LogOpt4: $scope.IsRes2LogOpt4,
            Res2LogOpt4Remark: $scope.Res2LogOpt4Remark,
            IsRes2EvaOpt1: $scope.IsRes2EvaOpt1,
            Res2EvaOpt1Remark: $scope.Res2EvaOpt1Remark,
            IsRes2EvaOpt2: $scope.IsRes2EvaOpt2,
            Res2EvaOpt2Remark: $scope.Res2EvaOpt2Remark,
            IsRes2EvaOpt3: $scope.IsRes2EvaOpt3,
            Res2EvaOpt3Remark: $scope.Res2EvaOpt3Remark,
            IsRes2EvaOpt4: $scope.IsRes2EvaOpt4,
            Res2EvaOpt4Remark: $scope.Res2EvaOpt4Remark,
            IsRes2EvaOpt5: $scope.IsRes2EvaOpt5,
            Res2EvaOpt5Remark: $scope.Res2EvaOpt5Remark,
            IsRes2DoOpt1: $scope.IsRes2DoOpt1,
            Res2DoOpt1Amount: $scope.Res2DoOpt1Amount,
            Res2DoOpt1Remark: $scope.Res2DoOpt1Remark,
            IsRes2DoOpt2: $scope.IsRes2DoOpt2,
            Res2DoOpt2Remark: $scope.Res2DoOpt2Remark,
            IsRes2DoOpt3: $scope.IsRes2DoOpt3,
            Res2DoOpt3Remark: $scope.Res2DoOpt3Remark,
            IsRes2DoOpt4: $scope.IsRes2DoOpt4,
            IsRes2DoOpt5: $scope.IsRes2DoOpt5,
            Res2DoOpt5Date: $scope.Res2DoOpt5Date,
            IsRes2DoOpt6: $scope.IsRes2DoOpt6,
            Res2DoOpt6Amount: $scope.Res2DoOpt6Amount,
            IsRes2DoOpt7: $scope.IsRes2DoOpt7,
            Res2DoOpt7Remark: $scope.Res2DoOpt7Remark,
            
            

            // Res 3
            IsRes3LogOpt1: $scope.IsRes3LogOpt1,
            Res3LogOpt1: $scope.Res3LogOpt1,
            Res3LogOpt1Other: $scope.Res3LogOpt1Other,
            IsRes3LogOpt2: $scope.IsRes3LogOpt2,
            Res3LogOpt2: $scope.Res3LogOpt2,
            Res3LogOpt2Other: $scope.Res3LogOpt2Other,
            IsRes3LogOpt3: $scope.IsRes3LogOpt3,
            Res3LogOpt3Amount: $scope.Res3LogOpt3Amount,
            IsRes3LogOpt4: $scope.IsRes3LogOpt4,
            Res3LogOpt4Remark: $scope.Res3LogOpt4Remark,
            IsRes3EvaOpt1: $scope.IsRes3EvaOpt1,
            Res3EvaOpt1Amount: $scope.Res3EvaOpt1Amount,
            IsRes3EvaOpt2: $scope.IsRes3EvaOpt2,
            Res3EvaOpt2Remark: $scope.Res3EvaOpt2Remark,
            IsRes3EvaOpt3: $scope.IsRes3EvaOpt3,
            Res3EvaOpt3Remark: $scope.Res3EvaOpt3Remark,
            IsRes3DoOpt1: $scope.IsRes3DoOpt1,
            Res3DoOpt1Remark: $scope.Res3DoOpt1Remark,
            IsRes3DoOpt2: $scope.IsRes3DoOpt2,
            IsRes3DoOpt3: $scope.IsRes3DoOpt3,
            Res3DoOpt3Remark: $scope.Res3DoOpt3Remark,
            IsRes3DoOpt4: $scope.IsRes3DoOpt4,
            Res3DoOpt4Remark: $scope.Res3DoOpt4Remark,

            // Res 4
            IsRes4LogOpt1: $scope.IsRes4LogOpt1,
            Res4LogOpt1: $scope.Res4LogOpt1,
            Res4LogOpt1Remark: $scope.Res4LogOpt1Remark,
            IsRes4EvaOpt1: $scope.IsRes4EvaOpt1,
            Res4EvaOpt1: $scope.Res4EvaOpt1,
            Res4EvaOpt1Amount: $scope.Res4EvaOpt1Amount,
            IsRes4EvaOpt2: $scope.IsRes4EvaOpt2,
            Res4EvaOpt2Remark: $scope.Res4EvaOpt2Remark,
            IsRes4EvaOpt3: $scope.IsRes4EvaOpt3,
            Res4EvaOpt3Remark: $scope.Res4EvaOpt3Remark,
            IsRes4DoOpt1: $scope.IsRes4DoOpt1,
            IsRes4DoOpt2: $scope.IsRes4DoOpt2,
            IsRes4DoOpt4: $scope.IsRes4DoOpt4,
            IsRes4DoOpt3: $scope.IsRes4DoOpt3,
            Res4DoOpt3Remark: $scope.Res4DoOpt3Remark,

            // Res 5
            IsRes5LogOpt1: $scope.IsRes5LogOpt1,
            Res5LogOpt1: $scope.Res5LogOpt1,
            Res5LogOpt1Other: $scope.Res5LogOpt1Other,
            IsRes5LogOpt2: $scope.IsRes5LogOpt2,
            Res5LogOpt2: $scope.Res5LogOpt2,
            Res5LogOpt2Other: $scope.Res5LogOpt2Other,
            IsRes5LogOpt3: $scope.IsRes5LogOpt3,
            Res5LogOpt3Amount: $scope.Res5LogOpt3Amount,
            IsRes5LogOpt4: $scope.IsRes5LogOpt4,
            Res5LogOpt4Remark: $scope.Res5LogOpt4Remark,
            IsRes5EvaOpt1: $scope.IsRes5EvaOpt1,
            Res5EvaOpt1Remark: $scope.Res5EvaOpt1Remark,
            IsRes5EvaOpt2: $scope.IsRes5EvaOpt2,
            Res5EvaOpt2Remark: $scope.Res5EvaOpt2Remark,
            IsRes5EvaOpt3: $scope.IsRes5EvaOpt3,
            Res5EvaOpt3Remark: $scope.Res5EvaOpt3Remark,
            IsRes5EvaOpt4: $scope.IsRes5EvaOpt4,
            Res5EvaOpt4Remark: $scope.Res5EvaOpt4Remark,
            IsRes5EvaOpt5: $scope.IsRes5EvaOpt5,
            Res5EvaOpt5Remark: $scope.Res5EvaOpt5Remark,
            IsRes5DoOpt1: $scope.IsRes5DoOpt1,
            Res5DoOpt1Amount: $scope.Res5DoOpt1Amount,
            Res5DoOpt1Remark: $scope.Res5DoOpt1Remark,
            IsRes5DoOpt2: $scope.IsRes5DoOpt2,
            Res5DoOpt2Remark: $scope.Res5DoOpt2Remark,
            IsRes5DoOpt3: $scope.IsRes5DoOpt3,
            Res5DoOpt3Remark: $scope.Res5DoOpt3Remark,
            IsRes5DoOpt4: $scope.IsRes5DoOpt4,
            IsRes5DoOpt5: $scope.IsRes5DoOpt5,
            Res5DoOpt5Date: $scope.Res5DoOpt5Date,
            IsRes5DoOpt6: $scope.IsRes5DoOpt6,
            Res5DoOpt6Amount: $scope.Res5DoOpt6Amount,
            IsRes5DoOpt7: $scope.IsRes5DoOpt7,
            Res5DoOpt7Remark: $scope.Res5DoOpt7Remark,

            IsNeedSupport: $scope.IsNeedSupport,
            NeedSupportRemark: $scope.NeedSupportRemark,
            
            IsHandledByMyself : $scope.IsHandledByMyself,
            HandleInformationId : $scope.HandleInformationId,

            // Finish 1
            Finish1Reason: $scope.Finish1Reason,
            Finish1ReasonOther: $scope.Finish1ReasonOther,
            Finish1ReasonToDo: $scope.Finish1ReasonToDo,
            IsFinish1DoSysOpt1: $scope.IsFinish1DoSysOpt1,
            IsFinish1DoSysOpt2: $scope.IsFinish1DoSysOpt2,
            IsFinish1DoSysOpt3: $scope.IsFinish1DoSysOpt3,
            Finish1DoSysOpt3Remark: $scope.Finish1DoSysOpt3Remark,
            IsFinish1DoSysOpt4: $scope.IsFinish1DoSysOpt4,
            IsFinish1DoSysOpt5: $scope.IsFinish1DoSysOpt5,
            IsFinish1DoSysOpt6: $scope.IsFinish1DoSysOpt6,
            Finish1DoSysOpt6Remark: $scope.Finish1DoSysOpt6Remark,
            IsFinish1DoSysOpt7: $scope.IsFinish1DoSysOpt7,
            Finish1DoSysOpt7Remark: $scope.Finish1DoSysOpt7Remark,
            IsFinish1DoSysOpt8: $scope.IsFinish1DoSysOpt8,
            IsFinish1DoSysOpt9: $scope.IsFinish1DoSysOpt9,
            IsFinish1DoSysOpt10: $scope.IsFinish1DoSysOpt10,
            IsFinish1DoEduOpt1: $scope.IsFinish1DoEduOpt1,
            IsFinish1DoEduOpt2: $scope.IsFinish1DoEduOpt2,
            IsFinish1DoEduOpt3: $scope.IsFinish1DoEduOpt3,
            IsFinish1DoEduOpt4: $scope.IsFinish1DoEduOpt4,

            // Finish 2
            Finish2Reason: $scope.Finish2Reason,
            Finish2ReasonOther: $scope.Finish2ReasonOther,
            Finish2ReasonRemark: $scope.Finish2ReasonRemark,
            IsFinish2DoSysOpt1: $scope.IsFinish2DoSysOpt1,
            Finish2DoSysOpt1Remark: $scope.Finish2DoSysOpt1Remark,
            IsFinish2DoSysOpt2: $scope.IsFinish2DoSysOpt2,
            IsFinish2DoSysOpt3: $scope.IsFinish2DoSysOpt3,
            IsFinish2DoSysOpt4: $scope.IsFinish2DoSysOpt4,
            IsFinish2DoSysOpt5: $scope.IsFinish2DoSysOpt5,
            IsFinish2DoEduOpt1: $scope.IsFinish2DoEduOpt1,
            IsFinish2DoEduOpt2: $scope.IsFinish2DoEduOpt2,
            IsFinish2DoEduOpt3: $scope.IsFinish2DoEduOpt3,
            IsFinish2DoEduOpt4: $scope.IsFinish2DoEduOpt4,

            // Finish 3
            IsFinish3DoSysOpt1: $scope.IsFinish3DoSysOpt1,
            IsFinish3DoSysOpt2: $scope.IsFinish3DoSysOpt2,
            IsFinish3DoSysOpt3: $scope.IsFinish3DoSysOpt3,
            Finish3DoSysOpt3Remark: $scope.Finish3DoSysOpt3Remark,
            IsFinish3DoSysOpt4: $scope.IsFinish3DoSysOpt4,
            Finish3DoSysOpt4Remark: $scope.Finish3DoSysOpt4Remark,
            IsFinish3DoSysOpt5: $scope.IsFinish3DoSysOpt5,
            IsFinish3DoEduOpt1: $scope.IsFinish3DoEduOpt1,
            IsFinish3DoEduOpt2: $scope.IsFinish3DoEduOpt2,
            IsFinish3DoEduOpt3: $scope.IsFinish3DoEduOpt3,
            IsFinish3DoEduOpt4: $scope.IsFinish3DoEduOpt4,

            // Finish 4
            Finish4Reason: $scope.Finish4Reason,
            Finish4ReasonOther: $scope.Finish4ReasonOther,
            Finish4ReasonRemark: $scope.Finish4ReasonRemark,
            IsFinish4DoSysOpt1: $scope.IsFinish4DoSysOpt1,
            IsFinish4DoEduOpt1: $scope.IsFinish4DoEduOpt1,
            IsFinish4DoEduOpt2: $scope.IsFinish4DoEduOpt2,
            IsFinish4DoEduOpt3: $scope.IsFinish4DoEduOpt3,
            IsFinish4DoEduOpt4: $scope.IsFinish4DoEduOpt4,

            // Finish 4
            Finish5Reason: $scope.Finish5Reason,
            Finish5ReasonOther: $scope.Finish5ReasonOther,
            Finish5ReasonRemark: $scope.Finish5ReasonRemark,
            IsFinish5DoSysOpt1: $scope.IsFinish5DoSysOpt1,
            Finish5DoSysOpt1Remark: $scope.Finish5DoSysOpt1Remark,
            IsFinish5DoSysOpt2: $scope.IsFinish5DoSysOpt2,
            IsFinish5DoSysOpt3: $scope.IsFinish5DoSysOpt3,
            IsFinish5DoSysOpt4: $scope.IsFinish5DoSysOpt4,
            IsFinish5DoEduOpt1: $scope.IsFinish5DoEduOpt1,
            IsFinish5DoEduOpt2: $scope.IsFinish5DoEduOpt2,
            IsFinish5DoEduOpt3: $scope.IsFinish5DoEduOpt3,
            IsFinish5DoEduOpt4: $scope.IsFinish5DoEduOpt4,

            FinishDoOther: $scope.FinishDoOther,
            FinishDateTime: $scope.FinishDateTime,

            ExamineDatetime: $scope.ExamineDatetime,
            RealFinishDateTime: $scope.RealFinishDateTime
        }

        $http.post('./api/a01/create/', request, csrf_config).then(
            function(response) {
            	bootbox.hideAll();
                if (response.data.success) {
                	$scope.PostId = response.data.PostId
                    $scope.queryData($scope.currentPage);
                	$scope.btnIns = false;
					$scope.btnUpd = true;
					$scope.PreStatus = 1;
					$scope.Id = response.data.Id;
                    bootbox.alert({
                        message: response.data.msg,
                        buttons: {
                            ok: {
                                label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                className: 'btn-success',
                            }
                        },
                        callback: function() {
                        	if (exit)
							{																					
                        		$scope.closeEdit();
							}
						else
							{			
								
							}                            
                        }
                    });
                } else {
                    bootbox.alert({
                        message: response.data.msg,
                        buttons: {
                            ok: {
                                label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                className: 'btn-danger',
                            }
                        },
                        callback: function() {}
                    });
                }
            }).catch(function() {
        		bootbox.hideAll();
            bootbox.alert({
                message: globalInsertDataFail,
                buttons: {
                    ok: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-danger',
                    }
                },
                callback: function() {}
            });
        }).finally(function() {});
    };
    // Insert Data End

    // Update Data Start
    $scope.updateData = function(exit, check) {
    	bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
		});
        $scope.IsWriteProcessLog = false
        if (check) {
            if ($scope.IsReview3)
                $scope.Status = 31;
            else if ($scope.IsNeedSaleReview3)
                $scope.Status = 32;
            else
                $scope.Status = 33;
            $scope.IsWriteProcessLog = true
        } else
            $scope.Status = 1;
        var request = {
            IsCC3: $scope.IsCC3,
            //建立時依照cc3來改變cc5
            IsCC5: $scope.IsCC3,            
            IsReview3: $scope.IsReview3,
             //建立時依照Review3來改變Review5
            IsReview5: $scope.IsReview3,
            Status: $scope.Status,
            PreStatus: $scope.PreStatus,
            TableName: "notification",
            Pre: "HISAC",
            IsWriteProcessLog: $scope.IsWriteProcessLog,
            Id: $scope.Id,
            ApplyDateTime: $scope.ApplyDateTime,
            PostId: $scope.PostId,
            ContactorUnit: $scope.ContactorUnit,
            MainUnit1: $scope.MainUnit1,
            MainUnit2: $scope.MainUnit2,
            ContactorId: $scope.ContactorId,
            ContactorTel: $scope.ContactorTel,
            ContactorFax: $scope.ContactorFax,
            ContactorEmail: $scope.ContactorEmail,
            IsSub: $scope.IsSub,
            IsSubUnitName: $scope.IsSubUnitName,
            EventDateTime: $scope.EventDateTime,
            HostAmount: $scope.ServerAmount + $scope.InformationAmount + $scope.OtherDeviceAmount,
            ServerAmount: $scope.ServerAmount,
            InformationAmount : $scope.InformationAmount,
            OtherDeviceAmount : $scope.OtherDeviceAmount,
            OtherDeviceName : $scope.OtherDeviceName,
            DeviceRemark : $scope.DeviceRemark,
            AssessDamage : $scope.AssessDamage,
            AssessDamageRemark : $scope.AssessDamageRemark,
            IsFinishDoSysOptRemark : $scope.IsFinishDoSysOptRemark,
            IsFinishDoEduOptRemark : $scope.IsFinishDoEduOptRemark,
            FinishDoSysOptRemark : $scope.FinishDoSysOptRemark,
        	    FinishDoEduOptRemark : $scope.FinishDoEduOptRemark,
            IpExternal: $scope.IpExternal,
            IpInternal: $scope.IpInternal,
            WebUrl: $scope.WebUrl,
            IsOsOpt1: $scope.IsOsOpt1,
            IsOsOpt2: $scope.IsOsOpt2,
            IsOsOpt3: $scope.IsOsOpt3,
            IsOsOpt3Other: $scope.IsOsOpt3Other,
            IsGuardOpt1: $scope.IsGuardOpt1,
            IsGuardOpt2: $scope.IsGuardOpt2,
            IsGuardOpt3: $scope.IsGuardOpt3,
            IsGuardOpt4: $scope.IsGuardOpt4,
            IsGuardOpt4Other: $scope.IsGuardOpt4Other,
            SocOpt: $scope.SocOpt,
            SocOptCompany: $scope.SocOptCompany,
            IsIsms: $scope.IsIsms,
            MaintainCompany: $scope.MaintainCompany,
            IsTest: $scope.IsTest,
            CeffectLevel: $scope.CeffectLevel,
            IeffectLevel: $scope.IeffectLevel,
            AeffectLevel: $scope.AeffectLevel,
            EffectLevel: $scope.EffectLevel,
            EventType: $scope.EventType,
            IsEventType1Opt1: $scope.IsEventType1Opt1,
            IsEventType1Opt2: $scope.IsEventType1Opt2,
            IsEventType1Opt3: $scope.IsEventType1Opt3,
            IsEventType1Opt4: $scope.IsEventType1Opt4,
            IsEventType1Opt5: $scope.IsEventType1Opt5,
            IsEventType1Opt6: $scope.IsEventType1Opt6,
            IsEventType2Opt1: $scope.IsEventType2Opt1,
            IsEventType2Opt2: $scope.IsEventType2Opt2,
            IsEventType2Opt3: $scope.IsEventType2Opt3,
            IsEventType2Opt4: $scope.IsEventType2Opt4,
            IsEventType2Opt5: $scope.IsEventType2Opt5,
            IsEventType3Opt1: $scope.IsEventType3Opt1,
            IsEventType3Opt2: $scope.IsEventType3Opt2,
            IsEventType4Opt1: $scope.IsEventType4Opt1,
            IsEventType4Opt2: $scope.IsEventType4Opt2,
            IsEventType4Opt3: $scope.IsEventType4Opt3,
            IsEventType4Opt4: $scope.IsEventType4Opt4,
            EventType5Other: $scope.EventType5Other,
            EventRemark: $scope.EventRemark,
            IsAffectOthers: $scope.IsAffectOthers,
            AffectOther1: $scope.AffectOther1,
            AffectOther2: $scope.AffectOther2,
            AffectOther3: $scope.AffectOther3,
            AffectOther4: $scope.AffectOther4,
            AffectOther5: $scope.AffectOther5,
            AffectOther6: $scope.AffectOther6,
            AffectOther7: $scope.AffectOther7,
            AffectOther8: $scope.AffectOther8,
            EventSource: $scope.EventSource,
            EventSourceNo: $scope.EventSourceNo,
            EventSourceOther: $scope.EventSourceOther,
            
            //OT資料更新
            
            OtAddDataList: $scope.otAddDataList,

            
            
            
            // Res All
            ResResult : $scope.ResResult,
            ResDescription : $scope.ResDescription,
            ResControlTime : $scope.ResControlTime,
            
            // Res 1
            IsRes1LogOpt1: $scope.IsRes1LogOpt1,
            Res1LogOpt1: $scope.Res1LogOpt1,
            Res1LogOpt1Other: $scope.Res1LogOpt1Other,
            IsRes1LogOpt2: $scope.IsRes1LogOpt2,
            Res1LogOpt2: $scope.Res1LogOpt2,
            Res1LogOpt2Other: $scope.Res1LogOpt2Other,
            IsRes1LogOpt5: $scope.IsRes1LogOpt5,
            Res1LogOpt5: $scope.Res1LogOpt5,
            Res1LogOpt5Other: $scope.Res1LogOpt5Other,
            IsRes1LogOpt3: $scope.IsRes1LogOpt3,
            Res1LogOpt3Amount: $scope.Res1LogOpt3Amount,
            IsRes1LogOpt4: $scope.IsRes1LogOpt4,
            Res1LogOpt4Remark: $scope.Res1LogOpt4Remark,
            IsRes1EvaOpt1: $scope.IsRes1EvaOpt1,
            Res1EvaOpt1Remark: $scope.Res1EvaOpt1Remark,
            IsRes1EvaOpt2: $scope.IsRes1EvaOpt2,
            Res1EvaOpt2Remark: $scope.Res1EvaOpt2Remark,
            IsRes1EvaOpt3: $scope.IsRes1EvaOpt3,
            Res1EvaOpt3Remark: $scope.Res1EvaOpt3Remark,
            IsRes1EvaOpt4: $scope.IsRes1EvaOpt4,
            Res1EvaOpt4Remark: $scope.Res1EvaOpt4Remark,
            IsRes1EvaOpt5: $scope.IsRes1EvaOpt5,
            IsRes1EvaOpt6: $scope.IsRes1EvaOpt6,
            Res1EvaOpt6Type: $scope.Res1EvaOpt6Type,
            Res1EvaOpt6Amount: $scope.Res1EvaOpt6Amount,
            Res1EvaOpt6Remark: $scope.Res1EvaOpt6Remark,
            Res1EvaOpt6TypeOpt3Other: $scope.Res1EvaOpt6TypeOpt3Other,
            IsRes1EvaOpt7: $scope.IsRes1EvaOpt7,
            Res1EvaOpt7Amount: $scope.Res1EvaOpt7Amount,
            Res1EvaOpt7Remark: $scope.Res1EvaOpt7Remark,
            IsRes1EvaOpt8: $scope.IsRes1EvaOpt8,
            Res1EvaOpt8Remark: $scope.Res1EvaOpt8Remark,
            IsRes1DoOpt1: $scope.IsRes1DoOpt1,
            Res1DoOpt1Amount: $scope.Res1DoOpt1Amount,
            IsRes1DoOpt2: $scope.IsRes1DoOpt2,
            Res1DoOpt2Remark: $scope.Res1DoOpt2Remark,
            IsRes1DoOpt3: $scope.IsRes1DoOpt3,
            Res1DoOpt3Remark: $scope.Res1DoOpt3Remark,
            IsRes1DoOpt4: $scope.IsRes1DoOpt4,
            Res1DoOpt4Remark: $scope.Res1DoOpt4Remark,
            IsRes1DoOpt5: $scope.IsRes1DoOpt5,
            Res1DoOpt5Amount: $scope.Res1DoOpt5Amount,
            IsRes1DoOpt6: $scope.IsRes1DoOpt6,
            IsRes1DoOpt7: $scope.IsRes1DoOpt7,
            IsRes1DoOpt8: $scope.IsRes1DoOpt8,
            IsRes1DoOpt9: $scope.IsRes1DoOpt9,
            IsRes1DoOpt9EngineOpt1: $scope.IsRes1DoOpt9EngineOpt1,
            IsRes1DoOpt9EngineOpt2: $scope.IsRes1DoOpt9EngineOpt2,
            IsRes1DoOpt9EngineOpt3: $scope.IsRes1DoOpt9EngineOpt3,
            IsRes1DoOpt9EngineOpt4: $scope.IsRes1DoOpt9EngineOpt4,
            IsRes1DoOpt9EngineOpt5: $scope.IsRes1DoOpt9EngineOpt5,
            IsRes1DoOpt9EngineOpt6: $scope.IsRes1DoOpt9EngineOpt6,
            Res1DoOpt9EngineOpt6Other: $scope.Res1DoOpt9EngineOpt6Other,
            IsRes1DoOpt10: $scope.IsRes1DoOpt10,
            Res1DoOpt10Date: $scope.Res1DoOpt10Date,
            IsRes1DoOpt11: $scope.IsRes1DoOpt11,
            Res1DoOpt11Date: $scope.Res1DoOpt11Date,
            IsRes1DoOpt12: $scope.IsRes1DoOpt12,
            Res1DoOpt12Remark: $scope.Res1DoOpt12Remark,

            // Res 2
            IsRes2LogOpt1: $scope.IsRes2LogOpt1,
            Res2LogOpt1: $scope.Res2LogOpt1,
            Res2LogOpt1Other: $scope.Res2LogOpt1Other,
            IsRes2LogOpt2: $scope.IsRes2LogOpt2,
            Res2LogOpt2: $scope.Res2LogOpt2,
            Res2LogOpt2Other: $scope.Res2LogOpt2Other,
            IsRes2LogOpt3: $scope.IsRes2LogOpt3,
            Res2LogOpt3Amount: $scope.Res2LogOpt3Amount,
            IsRes2LogOpt4: $scope.IsRes2LogOpt4,
            Res2LogOpt4Remark: $scope.Res2LogOpt4Remark,
            IsRes2EvaOpt1: $scope.IsRes2EvaOpt1,
            Res2EvaOpt1Remark: $scope.Res2EvaOpt1Remark,
            IsRes2EvaOpt2: $scope.IsRes2EvaOpt2,
            Res2EvaOpt2Remark: $scope.Res2EvaOpt2Remark,
            IsRes2EvaOpt3: $scope.IsRes2EvaOpt3,
            Res2EvaOpt3Remark: $scope.Res2EvaOpt3Remark,
            IsRes2EvaOpt4: $scope.IsRes2EvaOpt4,
            Res2EvaOpt4Remark: $scope.Res2EvaOpt4Remark,
            IsRes2EvaOpt5: $scope.IsRes2EvaOpt5,
            Res2EvaOpt5Remark: $scope.Res2EvaOpt5Remark,
            IsRes2DoOpt1: $scope.IsRes2DoOpt1,
            Res2DoOpt1Amount: $scope.Res2DoOpt1Amount,
            Res2DoOpt1Remark: $scope.Res2DoOpt1Remark,
            IsRes2DoOpt2: $scope.IsRes2DoOpt2,
            Res2DoOpt2Remark: $scope.Res2DoOpt2Remark,
            IsRes2DoOpt3: $scope.IsRes2DoOpt3,
            Res2DoOpt3Remark: $scope.Res2DoOpt3Remark,
            IsRes2DoOpt4: $scope.IsRes2DoOpt4,
            IsRes2DoOpt5: $scope.IsRes2DoOpt5,
            Res2DoOpt5Date: $scope.Res2DoOpt5Date,
            IsRes2DoOpt6: $scope.IsRes2DoOpt6,
            Res2DoOpt6Amount: $scope.Res2DoOpt6Amount,
            IsRes2DoOpt7: $scope.IsRes2DoOpt7,
            Res2DoOpt7Remark: $scope.Res2DoOpt7Remark,

            // Res 3
            IsRes3LogOpt1: $scope.IsRes3LogOpt1,
            Res3LogOpt1: $scope.Res3LogOpt1,
            Res3LogOpt1Other: $scope.Res3LogOpt1Other,
            IsRes3LogOpt2: $scope.IsRes3LogOpt2,
            Res3LogOpt2: $scope.Res3LogOpt2,
            Res3LogOpt2Other: $scope.Res3LogOpt2Other,
            IsRes3LogOpt3: $scope.IsRes3LogOpt3,
            Res3LogOpt3Amount: $scope.Res3LogOpt3Amount,
            IsRes3LogOpt4: $scope.IsRes3LogOpt4,
            Res3LogOpt4Remark: $scope.Res3LogOpt4Remark,
            IsRes3EvaOpt1: $scope.IsRes3EvaOpt1,
            Res3EvaOpt1Amount: $scope.Res3EvaOpt1Amount,
            IsRes3EvaOpt2: $scope.IsRes3EvaOpt2,
            Res3EvaOpt2Remark: $scope.Res3EvaOpt2Remark,
            IsRes3EvaOpt3: $scope.IsRes3EvaOpt3,
            Res3EvaOpt3Remark: $scope.Res3EvaOpt3Remark,
            IsRes3DoOpt1: $scope.IsRes3DoOpt1,
            Res3DoOpt1Remark: $scope.Res3DoOpt1Remark,
            IsRes3DoOpt2: $scope.IsRes3DoOpt2,
            IsRes3DoOpt3: $scope.IsRes3DoOpt3,
            Res3DoOpt3Remark: $scope.Res3DoOpt3Remark,
            IsRes3DoOpt4: $scope.IsRes3DoOpt4,
            Res3DoOpt4Remark: $scope.Res3DoOpt4Remark,

            // Res 4
            IsRes4LogOpt1: $scope.IsRes4LogOpt1,
            Res4LogOpt1: $scope.Res4LogOpt1,
            Res4LogOpt1Remark: $scope.Res4LogOpt1Remark,
            IsRes4EvaOpt1: $scope.IsRes4EvaOpt1,
            Res4EvaOpt1: $scope.Res4EvaOpt1,
            Res4EvaOpt1Amount: $scope.Res4EvaOpt1Amount,
            IsRes4EvaOpt2: $scope.IsRes4EvaOpt2,
            Res4EvaOpt2Remark: $scope.Res4EvaOpt2Remark,
            IsRes4EvaOpt3: $scope.IsRes4EvaOpt3,
            Res4EvaOpt3Remark: $scope.Res4EvaOpt3Remark,
            IsRes4DoOpt1: $scope.IsRes4DoOpt1,
            IsRes4DoOpt2: $scope.IsRes4DoOpt2,
            IsRes4DoOpt4: $scope.IsRes4DoOpt4,
            IsRes4DoOpt3: $scope.IsRes4DoOpt3,
            Res4DoOpt3Remark: $scope.Res4DoOpt3Remark,

            // Res 5
            IsRes5LogOpt1: $scope.IsRes5LogOpt1,
            Res5LogOpt1: $scope.Res5LogOpt1,
            Res5LogOpt1Other: $scope.Res5LogOpt1Other,
            IsRes5LogOpt2: $scope.IsRes5LogOpt2,
            Res5LogOpt2: $scope.Res5LogOpt2,
            Res5LogOpt2Other: $scope.Res5LogOpt2Other,
            IsRes5LogOpt3: $scope.IsRes5LogOpt3,
            Res5LogOpt3Amount: $scope.Res5LogOpt3Amount,
            IsRes5LogOpt4: $scope.IsRes5LogOpt4,
            Res5LogOpt4Remark: $scope.Res5LogOpt4Remark,
            IsRes5EvaOpt1: $scope.IsRes5EvaOpt1,
            Res5EvaOpt1Remark: $scope.Res5EvaOpt1Remark,
            IsRes5EvaOpt2: $scope.IsRes5EvaOpt2,
            Res5EvaOpt2Remark: $scope.Res5EvaOpt2Remark,
            IsRes5EvaOpt3: $scope.IsRes5EvaOpt3,
            Res5EvaOpt3Remark: $scope.Res5EvaOpt3Remark,
            IsRes5EvaOpt4: $scope.IsRes5EvaOpt4,
            Res5EvaOpt4Remark: $scope.Res5EvaOpt4Remark,
            IsRes5EvaOpt5: $scope.IsRes5EvaOpt5,
            Res5EvaOpt5Remark: $scope.Res5EvaOpt5Remark,
            IsRes5DoOpt1: $scope.IsRes5DoOpt1,
            Res5DoOpt1Amount: $scope.Res5DoOpt1Amount,
            Res5DoOpt1Remark: $scope.Res5DoOpt1Remark,
            IsRes5DoOpt2: $scope.IsRes5DoOpt2,
            Res5DoOpt2Remark: $scope.Res5DoOpt2Remark,
            IsRes5DoOpt3: $scope.IsRes5DoOpt3,
            Res5DoOpt3Remark: $scope.Res5DoOpt3Remark,
            IsRes5DoOpt4: $scope.IsRes5DoOpt4,
            IsRes5DoOpt5: $scope.IsRes5DoOpt5,
            Res5DoOpt5Date: $scope.Res5DoOpt5Date,
            IsRes5DoOpt6: $scope.IsRes5DoOpt6,
            Res5DoOpt6Amount: $scope.Res5DoOpt6Amount,
            IsRes5DoOpt7: $scope.IsRes5DoOpt7,
            Res5DoOpt7Remark: $scope.Res5DoOpt7Remark,

            IsNeedSupport: $scope.IsNeedSupport,
            NeedSupportRemark: $scope.NeedSupportRemark,
            
            IsHandledByMyself: $scope.IsHandledByMyself,
            HandleInformationId : $scope.HandleInformationId,

            // Finish 1
            Finish1Reason: $scope.Finish1Reason,
            Finish1ReasonOther: $scope.Finish1ReasonOther,
            Finish1ReasonToDo: $scope.Finish1ReasonToDo,
            IsFinish1DoSysOpt1: $scope.IsFinish1DoSysOpt1,
            IsFinish1DoSysOpt2: $scope.IsFinish1DoSysOpt2,
            IsFinish1DoSysOpt3: $scope.IsFinish1DoSysOpt3,
            Finish1DoSysOpt3Remark: $scope.Finish1DoSysOpt3Remark,
            IsFinish1DoSysOpt4: $scope.IsFinish1DoSysOpt4,
            IsFinish1DoSysOpt5: $scope.IsFinish1DoSysOpt5,
            IsFinish1DoSysOpt6: $scope.IsFinish1DoSysOpt6,
            Finish1DoSysOpt6Remark: $scope.Finish1DoSysOpt6Remark,
            IsFinish1DoSysOpt7: $scope.IsFinish1DoSysOpt7,
            Finish1DoSysOpt7Remark: $scope.Finish1DoSysOpt7Remark,
            IsFinish1DoSysOpt8: $scope.IsFinish1DoSysOpt8,
            IsFinish1DoSysOpt9: $scope.IsFinish1DoSysOpt9,
            IsFinish1DoSysOpt10: $scope.IsFinish1DoSysOpt10,
            IsFinish1DoEduOpt1: $scope.IsFinish1DoEduOpt1,
            IsFinish1DoEduOpt2: $scope.IsFinish1DoEduOpt2,
            IsFinish1DoEduOpt3: $scope.IsFinish1DoEduOpt3,
            IsFinish1DoEduOpt4: $scope.IsFinish1DoEduOpt4,

            // Finish 2
            Finish2Reason: $scope.Finish2Reason,
            Finish2ReasonOther: $scope.Finish2ReasonOther,
            Finish2ReasonRemark: $scope.Finish2ReasonRemark,
            IsFinish2DoSysOpt1: $scope.IsFinish2DoSysOpt1,
            Finish2DoSysOpt1Remark: $scope.Finish2DoSysOpt1Remark,
            IsFinish2DoSysOpt2: $scope.IsFinish2DoSysOpt2,
            IsFinish2DoSysOpt3: $scope.IsFinish2DoSysOpt3,
            IsFinish2DoSysOpt4: $scope.IsFinish2DoSysOpt4,
            IsFinish2DoSysOpt5: $scope.IsFinish2DoSysOpt5,
            IsFinish2DoEduOpt1: $scope.IsFinish2DoEduOpt1,
            IsFinish2DoEduOpt2: $scope.IsFinish2DoEduOpt2,
            IsFinish2DoEduOpt3: $scope.IsFinish2DoEduOpt3,
            IsFinish2DoEduOpt4: $scope.IsFinish2DoEduOpt4,

            // Finish 3
            IsFinish3DoSysOpt1: $scope.IsFinish3DoSysOpt1,
            IsFinish3DoSysOpt2: $scope.IsFinish3DoSysOpt2,
            IsFinish3DoSysOpt3: $scope.IsFinish3DoSysOpt3,
            Finish3DoSysOpt3Remark: $scope.Finish3DoSysOpt3Remark,
            IsFinish3DoSysOpt4: $scope.IsFinish3DoSysOpt4,
            Finish3DoSysOpt4Remark: $scope.Finish3DoSysOpt4Remark,
            IsFinish3DoSysOpt5: $scope.IsFinish3DoSysOpt5,
            IsFinish3DoEduOpt1: $scope.IsFinish3DoEduOpt1,
            IsFinish3DoEduOpt2: $scope.IsFinish3DoEduOpt2,
            IsFinish3DoEduOpt3: $scope.IsFinish3DoEduOpt3,
            IsFinish3DoEduOpt4: $scope.IsFinish3DoEduOpt4,

            // Finish 4
            Finish4Reason: $scope.Finish4Reason,
            Finish4ReasonOther: $scope.Finish4ReasonOther,
            Finish4ReasonRemark: $scope.Finish4ReasonRemark,
            IsFinish4DoSysOpt1: $scope.IsFinish4DoSysOpt1,
            IsFinish4DoEduOpt1: $scope.IsFinish4DoEduOpt1,
            IsFinish4DoEduOpt2: $scope.IsFinish4DoEduOpt2,
            IsFinish4DoEduOpt3: $scope.IsFinish4DoEduOpt3,
            IsFinish4DoEduOpt4: $scope.IsFinish4DoEduOpt4,

            // Finish 4
            Finish5Reason: $scope.Finish5Reason,
            Finish5ReasonOther: $scope.Finish5ReasonOther,
            Finish5ReasonRemark: $scope.Finish5ReasonRemark,
            IsFinish5DoSysOpt1: $scope.IsFinish5DoSysOpt1,
            Finish5DoSysOpt1Remark: $scope.Finish5DoSysOpt1Remark,
            IsFinish5DoSysOpt2: $scope.IsFinish5DoSysOpt2,
            IsFinish5DoSysOpt3: $scope.IsFinish5DoSysOpt3,
            IsFinish5DoSysOpt4: $scope.IsFinish5DoSysOpt4,
            IsFinish5DoEduOpt1: $scope.IsFinish5DoEduOpt1,
            IsFinish5DoEduOpt2: $scope.IsFinish5DoEduOpt2,
            IsFinish5DoEduOpt3: $scope.IsFinish5DoEduOpt3,
            IsFinish5DoEduOpt4: $scope.IsFinish5DoEduOpt4,

            FinishDoOther: $scope.FinishDoOther,
            FinishDateTime: $scope.FinishDateTime,

            ExamineDatetime: $scope.ExamineDatetime,
            RealFinishDateTime: $scope.RealFinishDateTime
        }
        $http.post('./api/a01/update/', request, csrf_config).then(
            function(response) {
        		bootbox.hideAll();
                if (response.data.success) {
                		$scope.PostId = response.data.PostId
                    $scope.queryData($scope.currentPage);
                    bootbox.alert({
                        message: response.data.msg,
                        buttons: {
                            ok: {
                                label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                className: 'btn-success',
                            }
                        },
                        callback: function() {
                        	if (exit)							
                        		 $scope.closeEdit();							                      
                        }
                    });
                } else {
                    bootbox.alert({
                        message: response.data.msg,
                        buttons: {
                            ok: {
                                label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                className: 'btn-danger',
                            }
                        },
                        callback: function() {}
                    });
                }
            }).catch(function() {
        		bootbox.hideAll();
            bootbox.alert({
                message: globalUpdateDataFail,
                buttons: {
                    ok: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-danger',
                    }
                },
                callback: function() {}
            });
        }).finally(function() {});
    };
    // Update Data End

    // Delete Item Start
    $scope.deleteData = function(id) {
        bootbox.confirm({
            message: globalSureDeleteItem,
            buttons: {
                confirm: {
                    label: '<i class="fas fa-fw fa-check"></i>' + btnSure,
                    className: 'btn-success'
                },
                cancel: {
                    label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                    className: 'btn-default'
                }
            },
            callback: function(result) {
                if (result) {
                    var request = {
                        Id: id
                    };
                    $http.post('./api/a01/delete/', request, csrf_config).then(function(response) {
                        if (response.data.success) {
                            bootbox.alert({
                                message: response.data.msg,
                                buttons: {
                                    ok: {
                                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                        className: 'btn-success',
                                    }
                                },
                                callback: function() {
                                    if ($scope.pageRows == 1 && $scope.currentPage > 1) {
                                        $scope.currentPage = $scope.currentPage - 1;
                                    }
                                    $scope.queryData($scope.currentPage);
                                }
                            });
                        } else {
                            bootbox.alert({
                                message: response.data.msg,
                                buttons: {
                                    ok: {
                                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                        className: 'btn-danger',
                                    }
                                },
                                callback: function() {}
                            });
                        }
                    }).catch(function() {
                        bootbox.alert({
                            message: globalDeleteDataFail,
                            buttons: {
                                ok: {
                                    label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                    className: 'btn-danger',
                                }
                            },
                            callback: function() {}
                        });
                    }).finally(function() {});
                }
            }
        });
    }
    // Delete Item End
    // Examine Start
    $scope.examine = function(id, action) {
    	switch (action) {
            case 'pass':
                action = '通過'
                break;
            case 'back':
                action = '退回補述'
                break;
            case 'undo':
                action = '撤銷'
                break;
            case 'review':
                action = '送出'
                break;
        }
        bootbox
            .confirm(
                "確定要" + action + "此通報嗎？",
                function(result) {
                    if (result) {
                        if (action == '退回補述' && ($scope.PreStatus == 31 || $scope.PreStatus == 32 || $scope.PreStatus == 33))
                            status = 8
                        else if (action == '退回補述' && ($scope.PreStatus == 51 || $scope.PreStatus == 52 || $scope.PreStatus == 53))
                            status = 9
                        else if (action == '撤銷' && ($scope.PreStatus == 31 || $scope.PreStatus == 32 || $scope.PreStatus == 33))
                            status = 2
                        else if (action == '撤銷' && !($scope.PreStatus == 31 || $scope.PreStatus == 32 || $scope.PreStatus == 33)) {
                            status = 7
                            $scope.PreStatus = 2
                        } else if (action == '通過' && $scope.IsNeedSaleReview3 && $scope.PreStatus == 31)
                            status = 32
                        else if (action == '通過' && !$scope.IsNeedSaleReview3 && $scope.PreStatus == 31)
                            status = 33
                        else if (action == '通過' && ($scope.PreStatus == 32))
                            status = 33
                        else if (action == '通過' && $scope.PreStatus == 33)
                            status = 4
                        else if (action == '送出' && ($scope.PreStatus == 4 || $scope.PreStatus == 9) && $scope.IsReview5)
                            status = 51
                        else if (action == '送出' && ($scope.PreStatus == 4 || $scope.PreStatus == 9) && !$scope.IsReview5 && $scope.IsNeedSaleReview5)
                            status = 52
                        else if (action == '送出' && ($scope.PreStatus == 4 || $scope.PreStatus == 9) && !$scope.IsReview5 && !$scope.IsNeedSaleReview5)
                            status = 53
                        else if (action == '通過' && $scope.IsNeedSaleReview5 && $scope.PreStatus == 51)
                            status = 52
                        else if (action == '通過' && !$scope.IsNeedSaleReview5 && $scope.PreStatus == 51)
                            status = 53
                        else if (action == '通過' && ($scope.PreStatus == 52))
                            status = 53
                        else if (action == '通過' && ($scope.PreStatus == 53))
                            status = 6
                        var request = {                      
                            IsCC5: $scope.IsCC5,                            
                            IsReview5: $scope.IsReview5,
                            
                            // 6-common
                            HostAmount: $scope.ServerAmount + $scope.InformationAmount + $scope.OtherDeviceAmount,
                            ServerAmount: $scope.ServerAmount,
                            InformationAmount : $scope.InformationAmount,
                            OtherDeviceAmount : $scope.OtherDeviceAmount,
                            OtherDeviceName : $scope.OtherDeviceName,
                            DeviceRemark : $scope.DeviceRemark,
                            AssessDamage : $scope.AssessDamage,
                            AssessDamageRemark : $scope.AssessDamageRemark,
                            IsFinishDoSysOptRemark : $scope.IsFinishDoSysOptRemark,
                            IsFinishDoEduOptRemark : $scope.IsFinishDoEduOptRemark,
                            FinishDoSysOptRemark : $scope.FinishDoSysOptRemark,
                        	    FinishDoEduOptRemark : $scope.FinishDoEduOptRemark,
                            IpExternal: $scope.IpExternal,
                            IpInternal: $scope.IpInternal,
                            WebUrl: $scope.WebUrl,
                            IsOsOpt1: $scope.IsOsOpt1,
                            IsOsOpt2: $scope.IsOsOpt2,
                            IsOsOpt3: $scope.IsOsOpt3,
                            IsOsOpt3Other: $scope.IsOsOpt3Other,
                            IsGuardOpt1: $scope.IsGuardOpt1,
                            IsGuardOpt2: $scope.IsGuardOpt2,
                            IsGuardOpt3: $scope.IsGuardOpt3,
                            IsGuardOpt4: $scope.IsGuardOpt4,
                            IsGuardOpt4Other: $scope.IsGuardOpt4Other,
                            IsIsms: $scope.IsIsms,
                            
                            // Res All
                            ResResult : $scope.ResResult,
                            ResDescription : $scope.ResDescription,
                            ResControlTime : $scope.ResControlTime,
                            
                            // Res 1
                            IsRes1LogOpt1: $scope.IsRes1LogOpt1,
                            Res1LogOpt1: $scope.Res1LogOpt1,
                            Res1LogOpt1Other: $scope.Res1LogOpt1Other,
                            IsRes1LogOpt2: $scope.IsRes1LogOpt2,
                            Res1LogOpt2: $scope.Res1LogOpt2,
                            Res1LogOpt2Other: $scope.Res1LogOpt2Other,
                            IsRes1LogOpt5: $scope.IsRes1LogOpt5,
                            Res1LogOpt5: $scope.Res1LogOpt5,
                            Res1LogOpt5Other: $scope.Res1LogOpt5Other,
                            IsRes1LogOpt3: $scope.IsRes1LogOpt3,
                            Res1LogOpt3Amount: $scope.Res1LogOpt3Amount,
                            IsRes1LogOpt4: $scope.IsRes1LogOpt4,
                            Res1LogOpt4Remark: $scope.Res1LogOpt4Remark,
                            IsRes1EvaOpt1: $scope.IsRes1EvaOpt1,
                            Res1EvaOpt1Remark: $scope.Res1EvaOpt1Remark,
                            IsRes1EvaOpt2: $scope.IsRes1EvaOpt2,
                            Res1EvaOpt2Remark: $scope.Res1EvaOpt2Remark,
                            IsRes1EvaOpt3: $scope.IsRes1EvaOpt3,
                            Res1EvaOpt3Remark: $scope.Res1EvaOpt3Remark,
                            IsRes1EvaOpt4: $scope.IsRes1EvaOpt4,
                            Res1EvaOpt4Remark: $scope.Res1EvaOpt4Remark,
                            IsRes1EvaOpt5: $scope.IsRes1EvaOpt5,
                            IsRes1EvaOpt6: $scope.IsRes1EvaOpt6,
                            Res1EvaOpt6Type: $scope.Res1EvaOpt6Type,
                            Res1EvaOpt6Amount: $scope.Res1EvaOpt6Amount,
                            Res1EvaOpt6Remark: $scope.Res1EvaOpt6Remark,
                            Res1EvaOpt6TypeOpt3Other: $scope.Res1EvaOpt6TypeOpt3Other,
                            IsRes1EvaOpt7: $scope.IsRes1EvaOpt7,
                            Res1EvaOpt7Amount: $scope.Res1EvaOpt7Amount,
                            Res1EvaOpt7Remark: $scope.Res1EvaOpt7Remark,
                            IsRes1EvaOpt8: $scope.IsRes1EvaOpt8,
                            Res1EvaOpt8Remark: $scope.Res1EvaOpt8Remark,
                            IsRes1DoOpt1: $scope.IsRes1DoOpt1,
                            Res1DoOpt1Amount: $scope.Res1DoOpt1Amount,
                            IsRes1DoOpt2: $scope.IsRes1DoOpt2,
                            Res1DoOpt2Remark: $scope.Res1DoOpt2Remark,
                            IsRes1DoOpt3: $scope.IsRes1DoOpt3,
                            Res1DoOpt3Remark: $scope.Res1DoOpt3Remark,
                            IsRes1DoOpt4: $scope.IsRes1DoOpt4,
                            Res1DoOpt4Remark: $scope.Res1DoOpt4Remark,
                            IsRes1DoOpt5: $scope.IsRes1DoOpt5,
                            Res1DoOpt5Amount: $scope.Res1DoOpt5Amount,
                            IsRes1DoOpt6: $scope.IsRes1DoOpt6,
                            IsRes1DoOpt7: $scope.IsRes1DoOpt7,
                            IsRes1DoOpt8: $scope.IsRes1DoOpt8,
                            IsRes1DoOpt9: $scope.IsRes1DoOpt9,
                            IsRes1DoOpt9EngineOpt1: $scope.IsRes1DoOpt9EngineOpt1,
                            IsRes1DoOpt9EngineOpt2: $scope.IsRes1DoOpt9EngineOpt2,
                            IsRes1DoOpt9EngineOpt3: $scope.IsRes1DoOpt9EngineOpt3,
                            IsRes1DoOpt9EngineOpt4: $scope.IsRes1DoOpt9EngineOpt4,
                            IsRes1DoOpt9EngineOpt5: $scope.IsRes1DoOpt9EngineOpt5,
                            IsRes1DoOpt9EngineOpt6: $scope.IsRes1DoOpt9EngineOpt6,
                            Res1DoOpt9EngineOpt6Other: $scope.Res1DoOpt9EngineOpt6Other,
                            IsRes1DoOpt10: $scope.IsRes1DoOpt10,
                            Res1DoOpt10Date: $scope.Res1DoOpt10Date,
                            IsRes1DoOpt11: $scope.IsRes1DoOpt11,
                            Res1DoOpt11Date: $scope.Res1DoOpt11Date,
                            IsRes1DoOpt12: $scope.IsRes1DoOpt12,
                            Res1DoOpt12Remark: $scope.Res1DoOpt12Remark,

                            // Res 2
                            IsRes2LogOpt1: $scope.IsRes2LogOpt1,
                            Res2LogOpt1: $scope.Res2LogOpt1,
                            Res2LogOpt1Other: $scope.Res2LogOpt1Other,
                            IsRes2LogOpt2: $scope.IsRes2LogOpt2,
                            Res2LogOpt2: $scope.Res2LogOpt2,
                            Res2LogOpt2Other: $scope.Res2LogOpt2Other,
                            IsRes2LogOpt3: $scope.IsRes2LogOpt3,
                            Res2LogOpt3Amount: $scope.Res2LogOpt3Amount,
                            IsRes2LogOpt4: $scope.IsRes2LogOpt4,
                            Res2LogOpt4Remark: $scope.Res2LogOpt4Remark,
                            IsRes2EvaOpt1: $scope.IsRes2EvaOpt1,
                            Res2EvaOpt1Remark: $scope.Res2EvaOpt1Remark,
                            IsRes2EvaOpt2: $scope.IsRes2EvaOpt2,
                            Res2EvaOpt2Remark: $scope.Res2EvaOpt2Remark,
                            IsRes2EvaOpt3: $scope.IsRes2EvaOpt3,
                            Res2EvaOpt3Remark: $scope.Res2EvaOpt3Remark,
                            IsRes2EvaOpt4: $scope.IsRes2EvaOpt4,
                            Res2EvaOpt4Remark: $scope.Res2EvaOpt4Remark,
                            IsRes2EvaOpt5: $scope.IsRes2EvaOpt5,
                            Res2EvaOpt5Remark: $scope.Res2EvaOpt5Remark,
                            IsRes2DoOpt1: $scope.IsRes2DoOpt1,
                            Res2DoOpt1Amount: $scope.Res2DoOpt1Amount,
                            Res2DoOpt1Remark: $scope.Res2DoOpt1Remark,
                            IsRes2DoOpt2: $scope.IsRes2DoOpt2,
                            Res2DoOpt2Remark: $scope.Res2DoOpt2Remark,
                            IsRes2DoOpt3: $scope.IsRes2DoOpt3,
                            Res2DoOpt3Remark: $scope.Res2DoOpt3Remark,
                            IsRes2DoOpt4: $scope.IsRes2DoOpt4,
                            IsRes2DoOpt5: $scope.IsRes2DoOpt5,
                            Res2DoOpt5Date: $scope.Res2DoOpt5Date,
                            IsRes2DoOpt6: $scope.IsRes2DoOpt6,
                            Res2DoOpt6Amount: $scope.Res2DoOpt6Amount,
                            IsRes2DoOpt7: $scope.IsRes2DoOpt7,
                            Res2DoOpt7Remark: $scope.Res2DoOpt7Remark,

                            // Res 3
                            IsRes3LogOpt1: $scope.IsRes3LogOpt1,
                            Res3LogOpt1: $scope.Res3LogOpt1,
                            Res3LogOpt1Other: $scope.Res3LogOpt1Other,
                            IsRes3LogOpt2: $scope.IsRes3LogOpt2,
                            Res3LogOpt2: $scope.Res3LogOpt2,
                            Res3LogOpt2Other: $scope.Res3LogOpt2Other,
                            IsRes3LogOpt3: $scope.IsRes3LogOpt3,
                            Res3LogOpt3Amount: $scope.Res3LogOpt3Amount,
                            IsRes3LogOpt4: $scope.IsRes3LogOpt4,
                            Res3LogOpt4Remark: $scope.Res3LogOpt4Remark,
                            IsRes3EvaOpt1: $scope.IsRes3EvaOpt1,
                            Res3EvaOpt1Amount: $scope.Res3EvaOpt1Amount,
                            IsRes3EvaOpt2: $scope.IsRes3EvaOpt2,
                            Res3EvaOpt2Remark: $scope.Res3EvaOpt2Remark,
                            IsRes3EvaOpt3: $scope.IsRes3EvaOpt3,
                            Res3EvaOpt3Remark: $scope.Res3EvaOpt3Remark,
                            IsRes3DoOpt1: $scope.IsRes3DoOpt1,
                            Res3DoOpt1Remark: $scope.Res3DoOpt1Remark,
                            IsRes3DoOpt2: $scope.IsRes3DoOpt2,
                            IsRes3DoOpt3: $scope.IsRes3DoOpt3,
                            Res3DoOpt3Remark: $scope.Res3DoOpt3Remark,
                            IsRes3DoOpt4: $scope.IsRes3DoOpt4,
                            Res3DoOpt4Remark: $scope.Res3DoOpt4Remark,

                            // Res 4
                            IsRes4LogOpt1: $scope.IsRes4LogOpt1,
                            Res4LogOpt1: $scope.Res4LogOpt1,
                            Res4LogOpt1Remark: $scope.Res4LogOpt1Remark,
                            IsRes4EvaOpt1: $scope.IsRes4EvaOpt1,
                            Res4EvaOpt1: $scope.Res4EvaOpt1,
                            Res4EvaOpt1Amount: $scope.Res4EvaOpt1Amount,
                            IsRes4EvaOpt2: $scope.IsRes4EvaOpt2,
                            Res4EvaOpt2Remark: $scope.Res4EvaOpt2Remark,
                            IsRes4EvaOpt3: $scope.IsRes4EvaOpt3,
                            Res4EvaOpt3Remark: $scope.Res4EvaOpt3Remark,
                            IsRes4DoOpt1: $scope.IsRes4DoOpt1,
                            IsRes4DoOpt2: $scope.IsRes4DoOpt2,
                            IsRes4DoOpt4: $scope.IsRes4DoOpt4,
                            IsRes4DoOpt3: $scope.IsRes4DoOpt3,
                            Res4DoOpt3Remark: $scope.Res4DoOpt3Remark,

                            // Res 5
                            IsRes5LogOpt1: $scope.IsRes5LogOpt1,
                            Res5LogOpt1: $scope.Res5LogOpt1,
                            Res5LogOpt1Other: $scope.Res5LogOpt1Other,
                            IsRes5LogOpt2: $scope.IsRes5LogOpt2,
                            Res5LogOpt2: $scope.Res5LogOpt2,
                            Res5LogOpt2Other: $scope.Res5LogOpt2Other,
                            IsRes5LogOpt3: $scope.IsRes5LogOpt3,
                            Res5LogOpt3Amount: $scope.Res5LogOpt3Amount,
                            IsRes5LogOpt4: $scope.IsRes5LogOpt4,
                            Res5LogOpt4Remark: $scope.Res5LogOpt4Remark,
                            IsRes5EvaOpt1: $scope.IsRes5EvaOpt1,
                            Res5EvaOpt1Remark: $scope.Res5EvaOpt1Remark,
                            IsRes5EvaOpt2: $scope.IsRes5EvaOpt2,
                            Res5EvaOpt2Remark: $scope.Res5EvaOpt2Remark,
                            IsRes5EvaOpt3: $scope.IsRes5EvaOpt3,
                            Res5EvaOpt3Remark: $scope.Res5EvaOpt3Remark,
                            IsRes5EvaOpt4: $scope.IsRes5EvaOpt4,
                            Res5EvaOpt4Remark: $scope.Res5EvaOpt4Remark,
                            IsRes5EvaOpt5: $scope.IsRes5EvaOpt5,
                            Res5EvaOpt5Remark: $scope.Res5EvaOpt5Remark,
                            IsRes5DoOpt1: $scope.IsRes5DoOpt1,
                            Res5DoOpt1Amount: $scope.Res5DoOpt1Amount,
                            Res5DoOpt1Remark: $scope.Res5DoOpt1Remark,
                            IsRes5DoOpt2: $scope.IsRes5DoOpt2,
                            Res5DoOpt2Remark: $scope.Res5DoOpt2Remark,
                            IsRes5DoOpt3: $scope.IsRes5DoOpt3,
                            Res5DoOpt3Remark: $scope.Res5DoOpt3Remark,
                            IsRes5DoOpt4: $scope.IsRes5DoOpt4,
                            IsRes5DoOpt5: $scope.IsRes5DoOpt5,
                            Res5DoOpt5Date: $scope.Res5DoOpt5Date,
                            IsRes5DoOpt6: $scope.IsRes5DoOpt6,
                            Res5DoOpt6Amount: $scope.Res5DoOpt6Amount,
                            IsRes5DoOpt7: $scope.IsRes5DoOpt7,
                            Res5DoOpt7Remark: $scope.Res5DoOpt7Remark,

                            // 108/6/18 by bowwow added
                            IsNeedSupport: $scope.IsNeedSupport,
                            NeedSupportRemark: $scope.NeedSupportRemark,
                            
                            IsHandledByMyself: $scope.IsHandledByMyself,
                            HandleInformationId : $scope.HandleInformationId,

                            // Finish 1
                            Finish1Reason: $scope.Finish1Reason,
                            Finish1ReasonOther: $scope.Finish1ReasonOther,
                            Finish1ReasonToDo: $scope.Finish1ReasonToDo,
                            IsFinish1DoSysOpt1: $scope.IsFinish1DoSysOpt1,
                            IsFinish1DoSysOpt2: $scope.IsFinish1DoSysOpt2,
                            IsFinish1DoSysOpt3: $scope.IsFinish1DoSysOpt3,
                            Finish1DoSysOpt3Remark: $scope.Finish1DoSysOpt3Remark,
                            IsFinish1DoSysOpt4: $scope.IsFinish1DoSysOpt4,
                            IsFinish1DoSysOpt5: $scope.IsFinish1DoSysOpt5,
                            IsFinish1DoSysOpt6: $scope.IsFinish1DoSysOpt6,
                            Finish1DoSysOpt6Remark: $scope.Finish1DoSysOpt6Remark,
                            IsFinish1DoSysOpt7: $scope.IsFinish1DoSysOpt7,
                            Finish1DoSysOpt7Remark: $scope.Finish1DoSysOpt7Remark,
                            IsFinish1DoSysOpt8: $scope.IsFinish1DoSysOpt8,
                            IsFinish1DoSysOpt9: $scope.IsFinish1DoSysOpt9,
                            IsFinish1DoSysOpt10: $scope.IsFinish1DoSysOpt10,
                            IsFinish1DoEduOpt1: $scope.IsFinish1DoEduOpt1,
                            IsFinish1DoEduOpt2: $scope.IsFinish1DoEduOpt2,
                            IsFinish1DoEduOpt3: $scope.IsFinish1DoEduOpt3,
                            IsFinish1DoEduOpt4: $scope.IsFinish1DoEduOpt4,

                            // Finish 2
                            Finish2Reason: $scope.Finish2Reason,
                            Finish2ReasonOther: $scope.Finish2ReasonOther,
                            Finish2ReasonRemark: $scope.Finish2ReasonRemark,
                            IsFinish2DoSysOpt1: $scope.IsFinish2DoSysOpt1,
                            Finish2DoSysOpt1Remark: $scope.Finish2DoSysOpt1Remark,
                            IsFinish2DoSysOpt2: $scope.IsFinish2DoSysOpt2,
                            IsFinish2DoSysOpt3: $scope.IsFinish2DoSysOpt3,
                            IsFinish2DoSysOpt4: $scope.IsFinish2DoSysOpt4,
                            IsFinish2DoSysOpt5: $scope.IsFinish2DoSysOpt5,
                            IsFinish2DoEduOpt1: $scope.IsFinish2DoEduOpt1,
                            IsFinish2DoEduOpt2: $scope.IsFinish2DoEduOpt2,
                            IsFinish2DoEduOpt3: $scope.IsFinish2DoEduOpt3,
                            IsFinish2DoEduOpt4: $scope.IsFinish2DoEduOpt4,

                            // Finish 3
                            IsFinish3DoSysOpt1: $scope.IsFinish3DoSysOpt1,
                            IsFinish3DoSysOpt2: $scope.IsFinish3DoSysOpt2,
                            IsFinish3DoSysOpt3: $scope.IsFinish3DoSysOpt3,
                            Finish3DoSysOpt3Remark: $scope.Finish3DoSysOpt3Remark,
                            IsFinish3DoSysOpt4: $scope.IsFinish3DoSysOpt4,
                            Finish3DoSysOpt4Remark: $scope.Finish3DoSysOpt4Remark,
                            IsFinish3DoSysOpt5: $scope.IsFinish3DoSysOpt5,
                            IsFinish3DoEduOpt1: $scope.IsFinish3DoEduOpt1,
                            IsFinish3DoEduOpt2: $scope.IsFinish3DoEduOpt2,
                            IsFinish3DoEduOpt3: $scope.IsFinish3DoEduOpt3,
                            IsFinish3DoEduOpt4: $scope.IsFinish3DoEduOpt4,

                            // Finish 4
                            Finish4Reason: $scope.Finish4Reason,
                            Finish4ReasonOther: $scope.Finish4ReasonOther,
                            Finish4ReasonRemark: $scope.Finish4ReasonRemark,
                            IsFinish4DoSysOpt1: $scope.IsFinish4DoSysOpt1,
                            IsFinish4DoEduOpt1: $scope.IsFinish4DoEduOpt1,
                            IsFinish4DoEduOpt2: $scope.IsFinish4DoEduOpt2,
                            IsFinish4DoEduOpt3: $scope.IsFinish4DoEduOpt3,
                            IsFinish4DoEduOpt4: $scope.IsFinish4DoEduOpt4,

                            // Finish 4
                            Finish5Reason: $scope.Finish5Reason,
                            Finish5ReasonOther: $scope.Finish5ReasonOther,
                            Finish5ReasonRemark: $scope.Finish5ReasonRemark,
                            IsFinish5DoSysOpt1: $scope.IsFinish5DoSysOpt1,
                            Finish5DoSysOpt1Remark: $scope.Finish5DoSysOpt1Remark,
                            IsFinish5DoSysOpt2: $scope.IsFinish5DoSysOpt2,
                            IsFinish5DoSysOpt3: $scope.IsFinish5DoSysOpt3,
                            IsFinish5DoSysOpt4: $scope.IsFinish5DoSysOpt4,
                            IsFinish5DoEduOpt1: $scope.IsFinish5DoEduOpt1,
                            IsFinish5DoEduOpt2: $scope.IsFinish5DoEduOpt2,
                            IsFinish5DoEduOpt3: $scope.IsFinish5DoEduOpt3,
                            IsFinish5DoEduOpt4: $scope.IsFinish5DoEduOpt4,

                            // EffectLevel 
                            CeffectLevel: $scope.CeffectLevel,
                            IeffectLevel: $scope.IeffectLevel,
                            AeffectLevel: $scope.AeffectLevel,
                            EffectLevel: $scope.EffectLevel,
                            
                            FinishDoOther: $scope.FinishDoOther,
                            FinishDateTime: $scope.FinishDateTime,

                            ExamineDatetime: $scope.ExamineDatetime,
                            RealFinishDateTime: $scope.RealFinishDateTime,
                            Id: id,
                            Status: status,
                            PreStatus: $scope.PreStatus,
                            TableName: "notification",
                            Pre: "HISAC",
                            Opinion: $scope.Opinion
                        };
                        bootbox.dialog({
                			closeButton: false,
                			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
                		});
                        $http
                            .post('./api/a01/examine', request, csrf_config)
                            .then(
                                function(response) {
                                    bootbox.hideAll();
                                    if (response.data.success) {
                                        bootbox
                                            .dialog({
                                                message: "<span class='bigger-110'>" + response.data.msg + "</span>",
                                                buttons: {
                                                    "success": {
                                                        "label": "<i class='ace-icon fa fa-check'></i> 確定",
                                                        "callback": function() {
                                                            $scope.queryData($scope.currentPage);
                                                            $scope.closeEdit()
                                                        }
                                                    }
                                                }
                                            });
                                    } else {
                                        bootbox.dialog({
                                            message: "<span class='bigger-110'>" + response.data.msg + "</span>",
                                            buttons: {
                                                "success": {
                                                    "label": "<i class='ace-icon fa fa-close'></i> 關閉",
                                                    "className": 'btn-warning',
                                                    "callback": function() {}
                                                }
                                            }
                                        });
                                    }
                                }).catch(function() {
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>通報" + action + "失敗</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='ace-icon fa fa-close'></i> 關閉",
                                            "className": 'btn-danger',
                                            "callback": function() {

                                            }
                                        }
                                    }
                                });
                            }).finally(function() {});
                    }
                });
    };
    // Examine End

    // Start change CC or Review
    $scope.changeIsCC = function() {
        if ($scope.IsCC3)
            $scope.IsReview3 = false;
        if ($scope.IsCC5)
            $scope.IsReview5 = false;
    };

    $scope.changeIsReview = function() {
        if ($scope.IsReview3)
            $scope.IsCC3 = false;
        if ($scope.IsReview5)
            $scope.IsCC5 = false;
    };
    // change CC or Review End

    // Step Change Start
    $scope.step = 1;
    $scope.stepPrev = false;
    $scope.stepNext = true;
    $scope.gotToStep = function(step) {
        $scope.stepPrev = true;
        $scope.stepNext = true;
        if ($scope.Status == 4 || $scope.Status == 9) {
            switch (step) {
                case 0:
                case 1:
                    $scope.step = 1;
                    $scope.stepPrev = false;
                    $('#tabNotifyStep a[href="#tabNotifyStep1"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 2:
                    $scope.step = 2;
                    $('#tabNotifyStep a[href="#tabNotifyStep2"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 3:
                    $scope.step = 3;
                    $('#tabNotifyStep a[href="#tabNotifyStep3"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 4:
                    $scope.step = 4;
                    $('#tabNotifyStep a[href="#tabNotifyStep4"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 5:
                    $scope.step = 5;
                    $('#tabNotifyStep a[href="#tabNotifyStep5"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                default:
                    $scope.step = 6;
                    $scope.stepNext = false;
                    $('#tabNotifyStep a[href="#tabNotifyStep6"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
            }
        } else {
            switch (step) {
                case 0:
                case 1:
                    $scope.step = 1;
                    $scope.stepPrev = false;
                    $('#tabNotifyStep a[href="#tabNotifyStep1"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 2:
                    $scope.step = 2;
                    $('#tabNotifyStep a[href="#tabNotifyStep2"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                case 3:
                    $scope.step = 3;
                    $('#tabNotifyStep a[href="#tabNotifyStep3"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
                    break;
                default:
                    $scope.step = 4;
                    $scope.stepNext = false;
                    $('#tabNotifyStep a[href="#tabNotifyStep4"]').tab('show');
                    $("html, body").animate({
                        scrollTop: $('#tabNotifyStep').offset().top
                    }, 0);
            }
        }
    };
    // Step Change End

    // Query Step Change Start
    $scope.queryStep = 1;
    $scope.queryStepPrev = false;
    $scope.queryStepNext = true;
    $scope.gotToQueryStep = function(queryStep) {
        $scope.queryStepPrev = true;
        $scope.queryStepNext = true;
        if ($scope.Status == 4 || $scope.Status == 9) {
	        switch (queryStep) {
	            case 0:
	            case 1:
	                $scope.queryStep = 1;
	                $scope.queryStepPrev = false;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep1"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 2:
	                $scope.queryStep = 2;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep2"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 3:
	                $scope.queryStep = 3;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep3"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 4:
	                $scope.queryStep = 4;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep4"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 5:
	                $scope.queryStep = 5;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep5"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);k
	                break;
	            default:
	                $scope.queryStep = 6;
	                $scope.queryStepNext = false;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep6"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	        }
        } else {
        	switch (queryStep) {
	            case 0:
	            case 1:
	                $scope.queryStep = 1;
	                $scope.queryStepPrev = false;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep1"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 2:
	                $scope.queryStep = 2;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep2"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            case 3:
	                $scope.queryStep = 3;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep3"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	                break;
	            default:
	                $scope.queryStep = 4;
	                $scope.queryStepNext = false;
	                $('#tabNotifyQueryStep a[href="#tabNotifyQueryStep4"]').tab('show');
	                $("html, body").animate({
	                    scrollTop: $('#tabNotifyQueryStep').offset().top
	                }, 0);
	        }
        }
    };
    // Query Step Change End


    
    $scope.setStatus = function(status) {		
		 $scope.QueryStatus = status;
		 $scope.queryData();
	}
    
    $scope.queryButtonCount = function() {
        if ($scope.QuerySApplyDateTime == "")
            $scope.QuerySApplyDateTime = null;
        if ($scope.QueryEApplyDateTime == "")
            $scope.QueryEApplyDateTime = null;
        if ($scope.QueryStatus == "")
            $scope.QueryStatus = null;
        if ($scope.QueryKeyword == "")
            $scope.QueryKeyword = null;

        var request = {
            SApplyDateTime: $scope.QuerySApplyDateTime,
            EApplyDateTime: $scope.QueryEApplyDateTime,
            Status: $scope.QueryStatus,
            Keyword: $scope.QueryKeyword
        };

        $http.post('./api/a01/query/button/count', request, csrf_config).then(function(response) {
            $scope.button_count_allitems = response.data.datatable;
           // console.log("response=" +
			// JSON.stringify(response.data.datatable));
            $scope.getPageButtonCount();
            
        }).catch(function() {
            bootbox.alert({
                message: globalReadDataFail,
                buttons: {
                    ok: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-danger'
                    }
                },
                callback: function() {}
            });
        }).finally(function() {});
    }
   // $scope.queryButtonCount();

    $scope.getCount = function(Status) {
        var total = 0;
        angular.forEach($scope.button_count_allitems, function(value, key) {
            if (value.Status == Status) {
                total = value.Count;
            }
            if ((value.Status == 31 || value.Status == 32 || value.Status == 33) && Status == 3) {
            	total += value.Count;
            }
            if ((value.Status == 51 || value.Status == 52 || value.Status == 53) && Status == 5) {
            	total += value.Count;
            }
        });

       return total;
    }
    
    
    $scope.changeSelectAllorNone = function() {			
		angular.forEach($scope.otData, function(otItem) {			
			otItem.Action = $scope.selectSelAllorNone;								
			});				
	}
    
    //從api取得之ot資料加入填寫單
    
    
    
    $scope.addOtData = function() {    	
    	
    	console.log($scope.otData);
	    angular.forEach($scope.otData, function(otItem) {
	    	if (otItem.Action){
			$scope.otAddDataList.push({
				AssetName : otItem.AssetName,
				Brand : otItem.Brand,
				Model : otItem.Model,
				OperatingSystemVersion : otItem.OperatingSystemVersion,
				AssetClass : $scope.abnormalAssetClass
				
			})
	    }
		});	
	    
	    //清空選項
	    
	    angular.forEach($scope.otData, function(otItem) {			
			otItem.Action = false;								
			});	
    }
    
    
    //刪除已新增otAddDataList 之資料
    $scope.deleteOtAddDataList = function(i) {
    	console.log($scope.otAddDataList);
        $scope.otAddDataList.splice(i, 1);
    }
    
    
    //取得OT資料
    
    $scope.queryOTdata = function() {
		var data = {
				AssetClass : $scope.abnormalAssetClass,
				BrandKeyword : $scope.abnormalAssetKeyword,
		};
		$http.post('./api/a01/ires/getListAssetsRiskOTForBulletin', data, csrf_config).then(function(response) {
			$scope.otData = response.data.datatable;

			
						
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
		}).finally(function() {});
	};
    
$scope.getPageButtonCount = function(){
		
		$scope.ButtonCount1=$scope.getCount(1)+$scope.getCount(8);
		$scope.ButtonCount2=$scope.getCount(2);
		$scope.ButtonCount3=$scope.getCount(3);
		$scope.ButtonCount4=$scope.getCount(4)+$scope.getCount(9);
		$scope.ButtonCount5=$scope.getCount(5)
		$scope.ButtonCount7=$scope.getCount(7);
		
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		if ($("#subsystem_alt").length > 0) {
			var subsystem_alt_count = 0;
			if ($("#form_alert_management").length > 0) {
				$
						.ajax(
								{
									url : "../pub/api/count/alt",
									method : "POST",
									data : request,
									headers : header,
									datatype : "json",
									async : false
								})
						.done(
								function(response) {
									var count = response.count;
									if (count > 0) {
										$(
												"#form_alert_management")
												.html(count);
										subsystem_alt_count = subsystem_alt_count + count;
									} else {
										$(
										"#form_alert_management")
										.html('');
									}
								});
			}
			if (subsystem_alt_count > 0) {
				$("#subsystem_alt > .badge").html(
						subsystem_alt_count);
			} else {
				$("#subsystem_alt > .badge").html(
						'');
			}
		}
	
	}
    
    
    
    
    

    	// buttonToMessage Start
    	$scope.buttonToMessage = function(id) {
    		bootbox
    		.confirm(
    				"確定要轉警訊此通報嗎？",
    				function(result) {
    					if (result) {
    						var request = {Id:id};
    						$http
    								.post('./api/a01/toMessage', request, csrf_config)
    								.then(
    										function(response) {
    											if (response.data.success) {
    											bootbox
    													.dialog({
    														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
    														buttons : {
    															"success" : {
    																"label" : "<i class='ace-icon fa fa-check'></i> 確定",
    																"callback" : function() {
    																		if ($scope.pageRows == 1
    																				&& $scope.currentPage > 1) {
    																			$scope.currentPage = $scope.currentPage - 1;
    																		}
    																		$scope
    																				.queryData($scope.currentPage);
    																}
    															}
    														}
    													});
    											} else {
    												bootbox.dialog({
    													message : "<span class='bigger-110'>" + response.data.msg + "</span>",
    													buttons : {
    														"success" : {
    															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
    															 "className": 'btn-warning',
    															"callback" : function() {
    															}
    														}
    													}
    												});
    											}
    										}).catch(function() {
    											bootbox.dialog({
    												message : "<span class='bigger-110'>資料轉警訊失敗</span>",
    												buttons : {
    													"success" : {
    														"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
    														 "className": 'btn-danger',
    														"callback" : function() {
    														}
    													}
    												}
    											});
    										}).finally(function() { });
    					}
    				});
    			}
    	
    	// buttonToMessage End
	
    	// buttonToInformation Start
    	$scope.buttonToInformation = function(id) {
    		bootbox
    		.confirm(
    				"確定要轉情資此通報嗎？",
    				function(result) {
    					if (result) {
    						var request = {Id:id};
    						$http
    								.post('./api/a01/toInformation', request, csrf_config)
    								.then(
    										function(response) {
    											if (response.data.success) {
    											bootbox
    													.dialog({
    														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
    														buttons : {
    															"success" : {
    																"label" : "<i class='ace-icon fa fa-check'></i> 確定",
    																"callback" : function() {
    																		if ($scope.pageRows == 1
    																				&& $scope.currentPage > 1) {
    																			$scope.currentPage = $scope.currentPage - 1;
    																		}
    																		$scope
    																				.queryData($scope.currentPage);
    																}
    															}
    														}
    													});
    											} else {
    												bootbox.dialog({
    													message : "<span class='bigger-110'>" + response.data.msg + "</span>",
    													buttons : {
    														"success" : {
    															"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
    															 "className": 'btn-warning',
    															"callback" : function() {
    															}
   														}
   													}
    												});
    											}
    										}).catch(function() {
    											bootbox.dialog({
    												message : "<span class='bigger-110'>資料轉情資失敗</span>",
   												buttons : {
   													"success" : {
    														"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
    														 "className": 'btn-danger',
    														"callback" : function() {
    														}
    													}
    												}
    											});
    										}).finally(function() { });
    					}
    				});
    			}
    	
    	// buttonToInformation End
    	     
    	
    	
    	$scope.alertData =  function(id) {
    		bootbox.confirm({
                message: "是否確定稽催",
                buttons: {
                    confirm: {
                        label: '<i class="fas fa-fw fa-check"></i>' + btnSure,
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                        className: 'btn-default'
                    }
                },
                callback: function(result) {
                    if (result) {
                        var request = {
                            Id: id
                        };
                        $http.post('./api/a01/alert/', request, csrf_config).then(function(response) {
                            if (response.data.success) {
                                bootbox.alert({
                                    message: response.data.msg,
                                    buttons: {
                                        ok: {
                                            label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                            className: 'btn-success',
                                        }
                                    },
                                    callback: function() {                                        
                                    }
                                });
                            } else {
                                bootbox.alert({
                                    message: response.data.msg,
                                    buttons: {
                                        ok: {
                                            label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                            className: 'btn-danger',
                                        }
                                    },
                                    callback: function() {}
                                });
                            }
                        }).catch(function() {
                            bootbox.alert({
                                message: globalDeleteDataFail,
                                buttons: {
                                    ok: {
                                        label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                                        className: 'btn-danger',
                                    }
                                },
                                callback: function() {}
                            });
                        }).finally(function() {});
                    }
                }
            });    
    	}
    	
     $scope.chooseHandleInformation =  function(id) {
    	 bootbox.confirm({
             message: "是否確定送出",
             buttons: {
                 confirm: {
                     label: '<i class="fas fa-fw fa-check"></i>' + btnSure,
                     className: 'btn-success'
                 },
                 cancel: {
                     label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
                     className: 'btn-default'
                 }
             },
             callback: function(result) {
                 if (result) {
                	 	bootbox.dialog({
             			closeButton: false,
             			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i> 廠商指派中'
             		});
                     var request = {
                         Id: id,
                         HandleInformationId : $scope.HandleInformationId,
                         IsHandledByMyself : $scope.IsHandledByMyself
                     };
                     $http.post('./api/a01/chooseHandleInformation/', request, csrf_config).then(function(response) {                    	
                             bootbox.hideAll();
                             if (response.data.success) {
                                 bootbox
                                     .dialog({
                                         message: "<span class='bigger-110'>" + response.data.msg + "</span>",
                                         buttons: {
                                             "success": {
                                                 "label": "<i class='ace-icon fa fa-check'></i> 確定",
                                                 "callback": function() {
                                                     $scope.queryData($scope.currentPage);
                                                     $scope.closeEdit()
                                                 }
                                             }
                                         }
                                     });
                             } else {
                                 bootbox.dialog({
                                     message: "<span class='bigger-110'>" + response.data.msg + "</span>",
                                     buttons: {
                                         "success": {
                                             "label": "<i class='ace-icon fa fa-close'></i> 關閉",
                                             "className": 'btn-warning',
                                             "callback": function() {}
                                         }
                                     }
                                 });
                             }
                         }).catch(function() {
                         bootbox.dialog({
                             message: "<span class='bigger-110'>確認支援廠商失敗</span>",
                             buttons: {
                                 "success": {
                                     "label": "<i class='ace-icon fa fa-close'></i> 關閉",
                                     "className": 'btn-danger',
                                     "callback": function() {

                                     }
                                 }
                             }
                         });
                     }).finally(function() {});
                 }
             }
         });
     }
     
 	$scope.ToIncident =  function(NotificationId) {
 		$cookies.put("NotificationId", NotificationId, {path:"/"});  		
  		window.open("../inc/i01", "_blank");	
  	}
}

$(document).ready(function() {
    $('#tabNotifyStep2').removeClass('active');
    $('#tabNotifyStep3').removeClass('active');
    $('#tabNotifyStep4').removeClass('active');
    $('#tabNotifyStep5').removeClass('active');
    $('#tabNotifyStep6').removeClass('active');
})