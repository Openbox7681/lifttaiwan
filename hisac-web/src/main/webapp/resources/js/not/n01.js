var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});


myApp.filter('trustHtmlNewline',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br style="mso-data-placement:same-cell;" />').replace(/\r/g, '<br style="mso-data-placement:same-cell;" />').replace(/\n/g, '<br style="mso-data-placement:same-cell;" />'));
    	}
    };
});


function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location, Upload) {
	// $scope.query_show = true;
	$("#divQuery").show();
	// $("#divEdit").hide();
	$scope.Id = null;
	$scope.MessagePostReleaseId = null;
	$scope.PostId = null;
	$scope.PostDateTime = null;
	$scope.AlertCode = null
	$scope.EventCode = null
	$scope.SourceCode = null
	$scope.ExternalId = null			
	$scope.Subject = null
	$scope.Description = null
	$scope.Suggestion = null
	$scope.Reference = null
	$scope.Determine = null
	$scope.Contents = null
	$scope.AffectPlatform = null
	$scope.ImpactLevel = null
	$scope.Status = null
	$scope.FindDate = null
	$scope.Opinion = null
	$scope.recipientList = [];
	$scope.replyList = [];
	$scope.MessagePostAttachId1 = 0;
	$scope.MessagePostAttachId2 = 0;
	$scope.MessagePostAttachId3 = 0;		
	$scope.ciLevels = null;
	$scope.MessagePost = null;
	
	$scope.ButtonCount1=0;
	$scope.ButtonCount2=0;
	$scope.ButtonCount3=0;
	$scope.ButtonCount5=0;
	
	
	
	
	$('input[id="EPostDateTime"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="SPostDateTime"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });

		$("#SPostDateTime").on("dp.change", function(e) {	       
			$scope.SPostDateTime = $('#SPostDateTime').val();
			if ($scope.SPostDateTime > $scope.EPostDateTime && $scope.EPostDateTime != null) {
				$scope.EPostDateTime = $scope.SPostDateTime;
				$('#EPostDateTime').val($scope.EPostDateTime)
			}
		});
		$("#EPostDateTime").on("dp.change", function(e) {	       
			$scope.EPostDateTime = $('#EPostDateTime').val();
			if ($scope.EPostDateTime < $scope.SPostDateTime && $scope.SPostDateTime != null) {
				$scope.SPostDateTime = $scope.EPostDateTime;
				$('#SPostDateTime').val($scope.SPostDateTime)
			}
		});

	
	$('#FindDate').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#FindDate").on("dp.change", function(e) {
		$scope.FindDate = $('#FindDate').val();
	});
		
	$scope.queryAlertType = function() {
			var data = {};
			$http.post('./api/n01/query/alert', data, csrf_config).then(function(response) {
				$scope.alertTypes = response.data;
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
		
		$scope.queryEventType = function() {
			var data = {AlertCode:$scope.AlertCode};
			$http.post('./api/n01/query/event', data, csrf_config).then(function(response) {
				$scope.eventTypes = response.data;
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
		
		$scope.getSource = function() {
			var data = {};
			$http.post('./api/n01/query/source', data, csrf_config).then(function(response) {
				$scope.sourceCodes = response.data;
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
		
		$scope.getMember = function() {
			var data = {};
			$http.post('./api/n01/query/member', data, csrf_config).then(function(response) {
				$scope.members = response.data;
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
		
		$scope.queryMember = function() {
			var data = {
					Name : $scope.RecipientName,
					CiLevels : $scope.ciLevels,
					RecipientGroup : $scope.RecipientGroup
			};
			$http.post('./api/n01/query/memberList', data, csrf_config).then(function(response) {				
				angular.forEach($scope.members, function(member, key) {
					if (response.data.includes(member.Id))
						member.Show = true;	
					else
						member.Show = false;	
				});				
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
		
		$scope.getGroup = function() {
			var data = {};
			$http.post('./api/n01/query/group', data, csrf_config).then(function(response) {
				$scope.groups = response.data;
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
		
		//設定CiLevel選項
		$scope.getCiLevel = function() {
			$scope.ciLevels = [
				{Action: false, Value: 2, Name: "CI會員"},
				{Action: false, Value: 1, Name: "非CI進階會員"},
				{Action: false, Value: 0, Name: "非CI一般會員"}
				]
		};
		$scope.queryAlertType();
		$scope.queryEventType();
		$scope.getSource();		
		$scope.getMember();
		$scope.getGroup();
		$scope.getCiLevel();
					
		$scope.queryRecipientOther = function() {
			var data = {};
			$http.post('./api/n01/query/recipientOther', data, csrf_config).then(function(response) {
				angular.forEach(response.data, function(recipientOther) {
					$scope.recipientList.push({
						RecipientName : recipientOther.Name,
						RecipientEmail : recipientOther.Email,
						RecipientMobilePhone : recipientOther.MobilePhone
					})
				});											
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
		
		
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "createTime";
	$scope.sortreverse = true;
	
	$scope.setStatus = function(status) {
		 $scope.QueryStatus = status;
		 $scope.queryData();
	}
	$scope.clearData = function() {
		$scope.QueryStatus = null;
        $scope.QueryIsReply = null;
        $scope.SPostDateTime = null;
        $scope.EPostDateTime = null;
        startPostDateTime = null;
        endPostDateTime = null;
        $scope.QueryKeyword = null;
        $('#EPostDateTime').val("")
		$('#SPostDateTime').val("")
		$('#EPostDateTime').data("DateTimePicker").clear()
        $('#SPostDateTime').data("DateTimePicker").clear()
		
	}
	$scope.clearData();
	
	$scope.queryData = function(page) {
		
        $("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.SPostDateTime == "")
			$scope.SPostDateTime = null;
		if ($scope.EPostDateTime == "")
			$scope.EPostDateTime = null;
		if ($scope.QueryKeyword == "")
			$scope.QueryKeyword = null;
		if ($scope.QueryIsReply == "")
			$scope.QueryIsReply = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Status : $scope.QueryStatus,
			IsReply : $scope.QueryIsReply,
			SPostDateTime : $scope.SPostDateTime,
			EPostDateTime : $scope.EPostDateTime,
			Keyword : $scope.QueryKeyword,
			
		};
			$http.post('./api/n01/query', request, csrf_config).then(function(response) {	
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

	$scope.sendQueryData = function() {
			$scope.currentPage = 1;
			$scope.start = 0;
			$scope.queryData();
	}
	$scope.open_messagePostReleaseList = function(messagePostReleaseDatas) {				
		$scope.messagePostReleaseDatas = messagePostReleaseDatas;
		$scope.messagePostReleaseReplyNum = 0
		angular.forEach($scope.messagePostReleaseDatas, function(item) {
			if (item.MessagePostReleaseReplyContent != null)
				$scope.messagePostReleaseReplyNum++
		});	
	}
	
	$scope.openTabs = function(tab) {		
		switch(tab){
		case "Alert":
			$scope.Alert = true;
			$scope.Recipient = false;
			break;
		case "Recipient":
			$scope.Alert = false;
			$scope.Recipient = true;
			break;
		
		}
	}
// $scope.changefile = function(fileId) {
// switch(fileId){
// case 1:
// file1_mmdb = false;
// break;
// case 2:
// file2_mmdb = false;
// break;
// case 3:
// file3_mmdb = false;
// break;
// }
// }
	$scope.deletefile = function(fileId) {
		switch(fileId){
		case 1:
			$scope.file1 = null;
			$scope.FileDesc1 = "";
			break;
		case 2:
			$scope.file2 = null;
			$scope.FileDesc2 = "";
			break;
		case 3:
			$scope.file3 = null;
			$scope.FileDesc3 = "";
			break;
		}
	}

	$scope.click_postid = function(item) {		
		if (item.IsButtonEdit)
			$scope.edit(item.Id)		
		else if (item.IsButtonReview)
			$scope.review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'messageReview')
		else
			$scope.review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'view')
	}
	
	$scope.review = function(id, MessagePostReleaseId, IsSeeLog, IsSeeMessagePostReleaseLog, Action) {	
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.Id = null;
		$scope.MessagePostReleaseId = MessagePostReleaseId;		
		$scope.PostId = null;
		$scope.PostDateTime = null;
		$scope.IsReply = false;
		$scope.IsSms = true;
		$scope.IsTest = false;
		$scope.AlertCode = null
		$scope.EventCode = null
		$scope.SourceCode = null
		$scope.AlertName = null
		$scope.EventName = null
		$scope.SourceName = null
		$scope.ExternalId = null			
		$scope.Subject = null
		$scope.Description = null
		$scope.Suggestion = null
		$scope.Reference = null
		$scope.Determine = null
		$scope.Contents = null
		$scope.AffectPlatform = null
		$scope.ImpactLevel = null
		$scope.Status = null
		$scope.FindDate = null
		$scope.Opinion = null
		// FindDate = null
		$scope.Member = []		
		$scope.ReplyOption = ""
		$scope.file1 = null
		$scope.file2 = null
		$scope.file3 = null
		$scope.FileDesc1 = null
		$scope.FileDesc2 = null
		$scope.FileDesc3 = null
		
		$scope.MessagePostAttachId1 = 0
		$scope.MessagePostAttachId2 = 0
		$scope.MessagePostAttachId3 = 0
		
			
		$scope.recipientList = [];
		$scope.replyList = [];
		$scope.MessageProcessLogData = [];
		$scope.MessagePostAttachData = [];		
		$scope.ciLevels = null;
		
		$scope.MessagePost = null;
		$scope.Determine_show = false;
		$scope.Description_show =false;
		$scope.Contents_show = false;
		$scope.AffectPlatform_show = false;
		$scope.ImpactLevel_show = false;
		$scope.Reference_show = false;
		$scope.btnIns = false;
		$scope.btnUpd = false;
		// $scope.query_show = false;
		$("#divQuery").hide();
		// $("#divEdit").show();
		$scope.Recipient = false;
		$scope.Alert = false;
		$scope.createAndupdate_show = false;
		$scope.IsSeeLog = IsSeeLog;
		$scope.IsSeeMessagePostReleaseLog = IsSeeMessagePostReleaseLog;	
		$scope.Action = Action;
		switch(Action){
		case "view":
			$scope.IsSeeOpinion = false;
			$scope.IsSeeReply = false;
			$scope.IsSeeIsCC = false;
			$scope.IsSeeIsReview = false;
			$scope.messagePassButton = false;
			$scope.messageBackButton = false;
			$scope.messageUndoButtonButton = false;
			$scope.messagePostReleaseReplyButton = false;
			$scope.messageReleasePostPassButton = false;
			$scope.messageReleasePostBackButton = false;
			break;
		case "messageReview":
			$scope.IsSeeOpinion = true;
			$scope.IsSeeReply = false;
			$scope.IsSeeIsCC = false;
			$scope.IsSeeIsReview = false;
			$scope.messagePassButton = true;
			$scope.messageBackButton = true;
			$scope.messageUndoButtonButton = true;
			$scope.messagePostReleaseReplyButton = false;
			$scope.messageReleasePostPassButton = false;
			$scope.messageReleasePostBackButton = false;
			break;
		case "reply":
			$scope.IsSeeOpinion = false;
			$scope.IsSeeReply = true;
			$scope.IsSeeIsCC = true;
			$scope.IsSeeIsReview = true;
			$scope.messagePassButton = false;
			$scope.messageBackButton = false;
			$scope.messageUndoButtonButton = false;
			$scope.messagePostReleaseReplyButton = true;
			$scope.messageReleasePostPassButton = false;
			$scope.messageReleasePostBackButton = false;
			break;
		case "messagePostReleaseReview":
			$scope.IsSeeOpinion = true;
			$scope.IsSeeReply = false;
			$scope.IsSeeIsCC = false;
			$scope.IsSeeIsReview = false;
			$scope.messagePassButton = false;
			$scope.messageBackButton = false;
			$scope.messageUndoButtonButton = false;
			$scope.messagePostReleaseReplyButton = false;
			$scope.messageReleasePostPassButton = true;
			$scope.messageReleasePostBackButton = true;
			break;			
		}		
		$scope.editData(id);
		$scope.review_show = true;
	}
	
	$scope.edit = function(id) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		$scope.Id = null;
		$scope.MessagePostReleaseId = null;
		$scope.PostId = null;
		$scope.PostDateTime = null;
		$scope.IsReply = false;
		$scope.IsSms = true;
		$scope.IsTest = false;
		$scope.AlertCode = null
		$scope.EventCode = null
		$scope.SourceCode = null
		$scope.AlertName = null
		$scope.EventName = null
		$scope.SourceName = null
		$scope.ExternalId = null			
		$scope.Subject = null
		$scope.Description = null
		$scope.Suggestion = null
		$scope.Reference = null
		$scope.Determine = null
		$scope.Contents = null
		$scope.AffectPlatform = null
		$scope.ImpactLevel = null
		$scope.Status = null
		$scope.FindDate = null
		$scope.Opinion = null
		// FindDate = null
		$scope.Action = null;
		$scope.Member = []	
		$scope.ReplyOption = ""
		$scope.file1 = null
		$scope.file2 = null
		$scope.file3 = null
		$scope.FileDesc1 = null
		$scope.FileDesc2 = null
		$scope.FileDesc3 = null
		
		$scope.MessagePostAttachId1 = 0
		$scope.MessagePostAttachId2 = 0
		$scope.MessagePostAttachId3 = 0
		
		
		$scope.recipientList = [];
		$scope.replyList = [];
		$scope.MessageProcessLogData = [];
		$scope.MessagePostAttachData = [];		
		$scope.ciLevels = null;	
		$scope.getCiLevel();
		
		$scope.MessagePost = null;
		
		$scope.selectSelAllorNone = false
		
		$scope.editData(id);
// $scope.Determine_show = true;
// $scope.Description_show =true;
// $scope.Contents_show = true;
// $scope.AffectPlatform_show = true;
// $scope.ImpactLevel_show = true;
// $scope.Reference_show = true;
		$scope.btnIns = false;
		$scope.btnUpd = true;
		// $scope.query_show = false;
		$("#divQuery").hide();
		// $("#divEdit").show();
		$scope.Recipient = false;
		$scope.Alert = true;
		$scope.createAndupdate_show = true;
	}
	
	$scope.editData = function(id) {		
		$scope.getMember();
		var request = {Id:id, TableName:"message", MessagePostReleaseId: $scope.MessagePostReleaseId};		
		$http.post('./api/n01/query/id', request, csrf_config).then(function(response) {				
			$scope.Id = response.data[0].Id;
			$scope.PostId = response.data[0].PostId;
			$scope.MessagePostReleaseStatus = response.data[0].MessagePostReleaseStatus;
			if ($scope.Action == "reply"){
				$scope.IsSeeIsCC = response.data[0].IsSeeIsCC;
				$scope.IsSeeIsReview = response.data[0].IsSeeIsReview;
			}
			else{
				$scope.IsSeeIsCC = false;
				$scope.IsSeeIsReview = false;
			}
			$scope.IsNeedSaleReview = response.data[0].IsNeedSaleReview;
			if (response.data[0].PostDateTime != "")
				$scope.PostDateTime = response.data[0].PostDateTime;
			else
				// $scope.PostDateTime = "警訊發布時自動帶入"
			$scope.AlertCode = response.data[0].AlertCode;
			$scope.EventCode = response.data[0].EventCode;
			$scope.SourceCode =  response.data[0].SourceCode;
			$scope.AlertName = response.data[0].AlertName;
			$scope.EventName = response.data[0].EventName;
			$scope.SourceName =  response.data[0].SourceName;
			$scope.ExternalId = response.data[0].ExternalId;
			$scope.FindDate = response.data[0].FindDate;
			// FindDate = response.data[0].FindDate;
			$scope.Subject = response.data[0].Subject;
			$scope.Description = response.data[0].Description;
			$scope.Suggestion = response.data[0].Suggestion;
			$scope.Reference = response.data[0].Reference;
			$scope.Determine = response.data[0].Determine;
			$scope.Contents = response.data[0].Contents;
			$scope.AffectPlatform = response.data[0].AffectPlatform;
			$scope.ImpactLevel = response.data[0].ImpactLevel;
			$scope.Status = response.data[0].Status;
			$scope.PreStatus = response.data[0].Status;
			$scope.IsReply = response.data[0].IsReply;
			$scope.IsSms = response.data[0].IsSms;
			$scope.IsTest = response.data[0].IsTest;
			$scope.SmsFormat = response.data[0].SmsFormat;
			$scope.MessagePost = response.data[0].MessagePost;			
			$scope.MessagePostAttachData = response.data[0].MessagePostAttach;
			$scope.MessageProcessLogData = response.data[0].MessageLog;		
			$scope.MessagePostReleaseLogData = response.data[0].MessagePostReleaseLog;
			$scope.IsReview = response.data[0].IsSeeIsReview;
			$scope.IsCC = response.data[0].IsSeeIsCC;
			for (var i=0;i<response.data[0].MessagePostAttach.length;i++)
				{
				if (i==0){
					$scope.file1 = response.data[0].MessagePostAttach[0]
					$scope.FileDesc1 = response.data[0].MessagePostAttach[0]["FileDesc"]
					$scope.MessagePostAttachId1 = response.data[0].MessagePostAttach[0]["Id"]
					}
				if (i==1){
					$scope.file2 = response.data[0].MessagePostAttach[1]
					$scope.FileDesc2 = response.data[0].MessagePostAttach[1]["FileDesc"]
					$scope.MessagePostAttachId2 = response.data[0].MessagePostAttach[1]["Id"]
					}
				if (i==2){
					$scope.file3 = response.data[0].MessagePostAttach[2]
					$scope.FileDesc3 = response.data[0].MessagePostAttach[2]["FileDesc"]
					$scope.MessagePostAttachId3 = response.data[0].MessagePostAttach[2]["Id"]
					}
				}					
			var MemberNum = 0		
			var ReplyNum = 0	
			angular.forEach($scope.MessagePost, function(item) {
				switch(item.PostType){				
				case "7":
					MemberNum++
					angular.forEach($scope.members, function(memberItem) {						
						if (memberItem.Id == item.PostContent)
							memberItem.Action = true;							
					});						
					$scope.Member.push({
						PostContentName : item.PostContentName,
						MemberName : item.MemberName
					})
					break;
				case "4":
					$scope.recipientList.push({
						RecipientName : item.PostName,
						RecipientEmail : item.PostContent,
						RecipientMobilePhone : item.PostMobilePhone
					})
					break;									
				
				case "6":
					ReplyNum++
					$scope.replyList.push({
						ReplyOption : item.PostContent						
					})
					$scope.ReplyOption = $scope.ReplyOption + ReplyNum + "." + item.PostContent + " "
					break;
					
				}
			});
			switch(response.data[0].AlertCode){
			case "ANA":
				$scope.Determine_show = false;
				$scope.Description_show =false;
				$scope.Contents_show = true;
				$scope.AffectPlatform_show = true;
				$scope.ImpactLevel_show = true;
				$scope.Reference_show = true;
				break
			case "DEF":
				$scope.Determine_show = false;
				$scope.Contents_show = false;
				$scope.AffectPlatform_show = false;
				$scope.ImpactLevel_show = false;
				$scope.Description_show =true;
				$scope.Reference_show = true;
				break
			case "INT":
				$scope.Contents_show = false;
				$scope.AffectPlatform_show = false;
				$scope.ImpactLevel_show = false;
				$scope.Determine_show = true;
				$scope.Description_show =true;
				$scope.Reference_show = true;
				break
			case "EWA":
				$scope.Determine_show = false;
				$scope.Contents_show = false;
				$scope.AffectPlatform_show = false;
				$scope.ImpactLevel_show = false;
				$scope.Description_show =true;
				$scope.Reference_show = true;
				break
			case "FBI":
				$scope.Reference_show = false;
				$scope.Contents_show = false;
				$scope.Determine_show = false;	
				$scope.Description_show =true;
				$scope.AffectPlatform_show = true;
				$scope.ImpactLevel_show = true;
				break
			case "OTH":
				$scope.Reference_show = false;
				$scope.Contents_show = true;
				$scope.Determine_show = false;	
				$scope.Description_show =false;
				$scope.AffectPlatform_show = false;
				$scope.ImpactLevel_show = false;
				break
			default:
				$scope.Reference_show = false;
				$scope.Contents_show = false;
				$scope.Determine_show = false;	
				$scope.Description_show =false;
				$scope.AffectPlatform_show = false;
				$scope.ImpactLevel_show = false;
				break
			}		
			bootbox.hideAll();
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
			bootbox.hideAll()
			});
	};
	
	$scope.updateData = function(exit,check) {	
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
		});
		if (check)
			$scope.Status = "3";
		else
			$scope.Status = "1";		
		
			$scope.IsWriteProcessLog = false
			if ($scope.Status == "3")
				$scope.IsWriteProcessLog = true
			if (!$scope.IsReply)
				$scope.replyList = [];
			var data = {
					Id : $scope.Id,
					AlertCode : $scope.AlertCode,
					EventCode : $scope.EventCode,
					SourceCode : $scope.SourceCode,
					ExternalId : $scope.ExternalId,				
					FindDate : $scope.FindDate,					
					Subject : $scope.Subject,
					Description : $scope.Description,
					Suggestion : $scope.Suggestion,
					Reference : $scope.Reference,
					Determine : $scope.Determine,
					Contents : $scope.Contents,
					AffectPlatform : $scope.AffectPlatform,
					ImpactLevel : $scope.ImpactLevel,
					Status : $scope.Status,
					PreStatus : $scope.PreStatus,
					TableName : "message",
					Pre : "HISAC-MES",
					IsReply : $scope.IsReply,					
					IsSms : $scope.IsSms,
					IsTest : $scope.IsTest,
					IsEnable : true,					
					MemberList : $scope.members,					
					RecipientList :$scope.recipientList,
					ReplyList : $scope.replyList,
					IsWriteProcessLog : $scope.IsWriteProcessLog,
					SmsFormat:$scope.SmsFormat
			};		
			if ( $scope.FileDesc1 == null ) $scope.FileDesc1 = ""; 
			if ( $scope.FileDesc2 == null ) $scope.FileDesc2 = ""; 
			if ( $scope.FileDesc3 == null ) $scope.FileDesc3 = ""; 
			$scope.UpdateFile1 = false;
			$scope.UpdateFile2 = false;
			$scope.UpdateFile3 = false;
			if ($scope.file1==null) $scope.UpdateFile1 = true;
			if ($scope.file2==null) $scope.UpdateFile2 = true;
			if ($scope.file3==null) $scope.UpdateFile3 = true;
			Upload
			.upload({
				url : './api/n01/update',
				data : {
					json : JSON.stringify(data),
					file1 : $scope.file1,
					file2 : $scope.file2,
					file3 : $scope.file3,
					FileDesc1 : $scope.FileDesc1,
					FileDesc2 : $scope.FileDesc2,
					FileDesc3 : $scope.FileDesc3,
					MessagePostAttachId1 : $scope.MessagePostAttachId1,
					MessagePostAttachId2 : $scope.MessagePostAttachId2,
					MessagePostAttachId3 : $scope.MessagePostAttachId3,
					UpdateFile1 : $scope.UpdateFile1,
					UpdateFile2 : $scope.UpdateFile2,
					UpdateFile3 : $scope.UpdateFile3
				},
				headers: header
			})
					.then(
							function(response) {
								if (response.data.success) {
									$scope.PostId = response.data.PostId
									bootbox.hideAll();
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
														if (exit)
															{
														$scope.queryData($scope.currentPage);
														// $scope.query_show =
														// true;
														$("#divQuery").show();
														// $("#divEdit").hide();
														$scope.createAndupdate_show = false;
															}
													}
												}
											}
										});
								} else {
									bootbox.hideAll();
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
								bootbox.hideAll();
								bootbox.dialog({
									message : "<span class='bigger-110'>資料更新失敗</span>",
									buttons : {
										"success" : {
											"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
											 "className": 'btn-danger',
											"callback" : function() {
											}
										}
									}
								});
							}).finally(function() {
							});
	}
	

	// Delete Item Start
// $scope.deleteData = function(id) {
// bootbox.confirm({
// message: globalSureDeleteItem,
// buttons: {
// confirm: {
// label: '<i class="fas fa-fw fa-check"></i>' + btnSure,
// className: 'btn-success'
// },
// cancel: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-default'
// }
// },
// callback: function(result) {
// if (result) {
// var request = {
// Id: id
// };
// $http.post('./api/n01/delete/', request, csrf_config).then(function(response)
// {
// if (response.data.success) {
// bootbox.alert({
// message: response.data.msg,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-success',
// }
// },
// callback: function() {
// if ($scope.pageRows == 1 && $scope.currentPage > 1) {
// $scope.currentPage = $scope.currentPage - 1;
// }
// $scope.queryData($scope.currentPage);
// }
// });
// } else {
// bootbox.alert({
// message: response.data.msg,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
// }
// }).catch(function() {
// bootbox.alert({
// message: globalDeleteDataFail,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
// }).finally(function() {});
// }
// }
// });
// }
    // Delete Item End
    
	$scope.deleteData = function(id) {
		bootbox
				.confirm(
						"確定要刪除此警訊嗎？",
						function(result) {
							if (result) {
								var request = {Id:id};
								$http
										.post('./api/n01/delete', request, csrf_config)
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
														message : "<span class='bigger-110'>資料刪除失敗</span>",
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
	

	$scope.create = function(){		
		$scope.getMember();
		$scope.PostId = null; // "新增時產生";
		$scope.PostDateTime = null;
		$scope.IsReply = false;
		$scope.IsSms = true;
		$scope.IsTest = false;
		$scope.AlertCode = null
		$scope.EventCode = null
		$scope.ExternalId = null			
		$scope.Subject = null
		$scope.Description = null
		$scope.Suggestion = null
		$scope.Reference = null
		$scope.Determine = null
		$scope.Contents = null
		$scope.AffectPlatform = null
		$scope.ImpactLevel = null
		$scope.Status = null
		$scope.SourceCode = null
		$scope.file1 = null
		$scope.file2 = null
		$scope.file3 = null
		$scope.FileDesc1 = null
		$scope.FileDesc2 = null
		$scope.FileDesc3 = null
		$scope.MessagePostAttachId1 = 0
		$scope.MessagePostAttachId2 = 0
		$scope.MessagePostAttachId3 = 0
		$scope.FindDate = moment().format("YYYY-MM-DD");
		$scope.recipientList = [];
		$scope.queryRecipientOther();
		$scope.replyList = [];		
		$scope.ciLevels = null;		
		
		$scope.selectSelAllorNone = false
		
		//簡訊內容
		$scope.SmsFormat = '(1/1(ㄧ)下午2點以前)，已寄至貴單位連絡人信箱，請儘速處理! 網站連結https://hisac.nat.gov.tw/，客服電話0809070166'	 
		
				
		$scope.getCiLevel();
		
		// $scope.query_show = false;
		$("#divQuery").hide();
		// $("#divEdit").show();
		$scope.Reference_show = false;
		$scope.Contents_show = false;
		$scope.Determine_show = false;	
		$scope.Description_show =false;
		$scope.AffectPlatform_show = false;
		$scope.ImpactLevel_show = false;
		
		
		$scope.Recipient = false;
		$scope.createAndupdate_show = true;
		$scope.Alert = true;
		
// $scope.Determine_show = true;
// $scope.Description_show =true;
// $scope.Contents_show = true;
// $scope.AffectPlatform_show = true;
// $scope.ImpactLevel_show = true;
// $scope.Reference_show = true;
		
		$scope.btnIns = true;
		$scope.btnUpd = false;
	}
	
			$scope.createData = function(exit,check) {
				bootbox.dialog({
					closeButton: false,
					message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
				});
				if (check)
					$scope.Status = "3";
				else
					$scope.Status = "1";				
					$scope.IsWriteProcessLog = false
					if ($scope.Status == "3")
						$scope.IsWriteProcessLog = true
					if (!$scope.IsReply)
						$scope.replyList = [];
					var request = {							
							AlertCode : $scope.AlertCode,
							EventCode : $scope.EventCode,
							SourceCode : $scope.SourceCode,
							ExternalId : $scope.ExternalId,				
							FindDate : $scope.FindDate,					
							Subject : $scope.Subject,
							Description : $scope.Description,
							Suggestion : $scope.Suggestion,
							Reference : $scope.Reference,
							Determine : $scope.Determine,
							Contents : $scope.Contents,
							AffectPlatform : $scope.AffectPlatform,
							ImpactLevel : $scope.ImpactLevel,
							Status : $scope.Status,
							PreStatus : "1",
							TableName : "message",
							Pre : "HISAC-MES",							
							IsReply : $scope.IsReply,								
							IsSms : $scope.IsSms,
							IsTest : $scope.IsTest,
							IsEnable : true,							
							MemberList : $scope.members,							
							RecipientList :$scope.recipientList,
							ReplyList : $scope.replyList,
							IsWriteProcessLog : $scope.IsWriteProcessLog,
							SmsFormat:$scope.SmsFormat
				   	
					};
					if ( $scope.FileDesc1 == null ) $scope.FileDesc1 = ""; 
					if ( $scope.FileDesc2 == null ) $scope.FileDesc2 = ""; 
					if ( $scope.FileDesc3 == null ) $scope.FileDesc3 = ""; 
					Upload
					.upload({
						url : './api/n01/create',
						data : {
							json : JSON.stringify(request),
							file1 : $scope.file1,
							file2 : $scope.file2,
							file3 : $scope.file3,
							FileDesc1 : $scope.FileDesc1,
							FileDesc2 : $scope.FileDesc2,
							FileDesc3 : $scope.FileDesc3,
						},
						headers: header
					})
					.then(
							function(response) {
										if (response.data.success) {											 											
											$scope.PostId = response.data.PostId
											bootbox.hideAll();
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
																$scope.queryData($scope.currentPage);
																if (exit)
																	{																					
																	// $scope.query_show
																	// = true;
																	$("#divQuery").show();
																	// $("#divEdit").hide();
																	$scope.createAndupdate_show = false;
																	}
																else
																	{														
																	$scope.btnIns = false;
																	$scope.btnUpd = true;
																	$scope.PreStatus = "1"
																	$scope.Id = response.data.Id
																	}
															}
														}
													}
												});
										} else {
											bootbox.hideAll();
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
										bootbox.hideAll();
										bootbox.dialog({
											message : "<span class='bigger-110'>資料新增失敗</span>",
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
													 "className": 'btn-danger',
													"callback" : function() {
													}
												}
											}
										});
									}).finally(function() { 
										// document.getElementById("select1").disabled
										// = false;
										// document.getElementById("file1").disabled
										// = false;
										// document.getElementById("FileDesc1").disabled
										// = false;
										// document.getElementById("select2").disabled
										// = false;
										// document.getElementById("file2").disabled
										// = false;
										// document.getElementById("FileDesc2").disabled
										// = false;
										// document.getElementById("select3").disabled
										// = false;
										// document.getElementById("file3").disabled
										// = false;
										// document.getElementById("FileDesc3").disabled
										// = false;
									});								
				};
	
	$scope.back = function() {
//		$scope.queryData();
		// $scope.query_show = true;
		$("#divQuery").show();
		// $("#divEdit").hide();
		$scope.createAndupdate_show = false;
		$scope.review_show = false;
	};	
	
	$scope.examine = function(id, status) {
		var action = ""
		var preStatus = ""
		switch(status){
		case '10':
			action = "退回";
			preStatus = "3"
			break;
		case '2':
			action = "撤銷";
			preStatus = "3"
			break;
		case '5':
			if (!$scope.IsReply)
				status = "8"
			action = "通過";
			preStatus = "3"
			break;
		case '9':
			action = "撤銷";
			preStatus = "2"
			break;
		}
		bootbox
		.confirm(
				"確定要"+action+"此警訊嗎？",
				function(result) {
					if (result) {
						bootbox.dialog({
							closeButton: false,
							message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataUpdating 
						});
						var request = {
								Id:id, 
								Status:status,
								PreStatus : preStatus,
								TableName : "message",
								Pre : "HISAC-MES",
								Opinion : $scope.Opinion,
								IsReply : $scope.IsReply
									};
						$http
								.post('./api/n01/examine', request, csrf_config)
								.then(
										function(response) {
											if (response.data.success) {
												bootbox.hideAll();
											bootbox
													.dialog({
														message : "<span class='bigger-110'>" + response.data.msg + "</span>",
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-check'></i> 確定",
																"callback" : function() {
																	$scope.queryData($scope.currentPage);
																	$scope.back()
																}
																}															
														}
													});
											} else {
												bootbox.hideAll();
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
											bootbox.hideAll();
											bootbox.dialog({
												message : "<span class='bigger-110'>警訊"+action+"失敗</span>",
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
};

$scope.examineMessageReleasePost = function(id, action) {
	switch(action){
	case 'pass':		
		action = '通過'
		break;
	case 'back':	
		action = '退回'
		break;
	}
	bootbox
	.confirm(
			"確定要"+action+"此警訊嗎？",
			function(result) {
				if (result) {					
					if (action == '退回')
						status = "51"
					if (action == '通過' && $scope.MessagePostReleaseStatus=="61" && $scope.IsNeedSaleReview)
						status = "62"
					if (action == '通過' && $scope.MessagePostReleaseStatus=="61" && !$scope.IsNeedSaleReview)
						status = "63"
					if (action == '通過' && $scope.MessagePostReleaseStatus=="62")
						status = "63"
					if (action == '通過' && $scope.MessagePostReleaseStatus=="63")
						status = "69"
					var request = {
							Id:id, 
							Status:status,
							PreStatus : $scope.MessagePostReleaseStatus,
							TableName : "messagePostRelease",
							Pre : "HISAC-MES",
							Opinion : $scope.Opinion							
								};
					$http
							.post('./api/n01/examineMessageReleasePost', request, csrf_config)
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
																$scope.queryData($scope.currentPage);
																$scope.back()
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
											message : "<span class='bigger-110'>警訊"+action+"失敗</span>",
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
};

$scope.reply = function(id) {
	var status = ""
	bootbox
	.confirm(
			"確定要回覆此警訊嗎？",
			function(result) {
				if (result) {
					if ($scope.IsReview)
						status = "61"
					else if ($scope.IsNeedSaleReview)
						status = "62"
					else
						status = "63"
					var request = {
							Id : id, 
							PreStatus : "51",
							Status : status,
							TableName : "messagePostRelease",
							Pre : "HISAC-MES",
							IsCC : $scope.IsCC,
							IsReview : $scope.IsReview,
							Reply : $scope.Reply,
							Opinion : '回覆選項：'+ $scope.ChooseReply +'  回覆內容：' + $scope.Reply,
							ReplyOption : $scope.ChooseReply
								};
					$http
							.post('./api/n01/reply', request, csrf_config)
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
																$scope.queryData($scope.currentPage);
																$scope.back()
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
											message : "<span class='bigger-110'>警訊回覆失敗</span>",
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
};		
		
	
	$scope.addRecipient = function() {
		$scope.recipientList.push({
			RecipientName : null,
			RecipientEmail : null,
			RecipientMobilePhone : null
 		})
	};
	
	$scope.addReplyOption = function() {
		$scope.replyList.push({
			ReplyOption : null			
 		})
	};		
	
	$scope.deleteRecipient = function(i) {
        $scope.recipientList.splice(i, 1);
    }
	
	$scope.deleteReplyOption = function(i) {
        $scope.replyList.splice(i, 1);
    }
	
	$scope.UpdateAlertCode = function() {	
		$scope.queryEventType();
		switch($scope.AlertCode){
		case "ANA":
			$scope.Determine_show = false;
			$scope.Determine = null;
			$scope.Description_show =false;
			$scope.Description = null;
			$scope.Contents_show = true;
			$scope.AffectPlatform_show = true;
			$scope.ImpactLevel_show = true;
			$scope.Reference_show = true;
			break
		case "DEF":
			$scope.Determine_show = false;
			$scope.Determine = null;
			$scope.Contents_show = false;
			$scope.Contents = null;
			$scope.AffectPlatform_show = false;
			$scope.AffectPlatform = null;
			$scope.ImpactLevel_show = false;
			$scope.ImpactLevel = null;
			$scope.Description_show =true;
			$scope.Reference_show = true;
			break
		case "INT":
			$scope.Contents_show = false;
			$scope.Contents = null;
			$scope.AffectPlatform_show = false;
			$scope.AffectPlatform = null;
			$scope.ImpactLevel_show = false;
			$scope.ImpactLevel = null;
			$scope.Determine_show = true;
			$scope.Description_show =true;
			$scope.Reference_show = true;
			break
		case "EWA":
			$scope.Determine_show = false;
			$scope.Determine = null;
			$scope.Contents_show = false;
			$scope.Contents = null;
			$scope.AffectPlatform_show = false;
			$scope.AffectPlatform = null;
			$scope.ImpactLevel_show = false;
			$scope.ImpactLevel = null;
			$scope.Description_show =true;
			$scope.Reference_show = true;
			break
		case "FBI":
			$scope.Reference_show = false;
			$scope.Reference = null;
			$scope.Contents_show = false;
			$scope.Contents = null;
			$scope.Determine_show = false;
			$scope.Determine = null;
			$scope.Description_show =true;
			$scope.AffectPlatform_show = true;
			$scope.ImpactLevel_show = true;
			break
		case "OTH":
			$scope.Reference_show = false;
			$scope.Reference = null;
			$scope.Contents_show = true;
			$scope.Contents = null;
			$scope.Determine_show = false;
			$scope.Determine = null;
			$scope.Description_show =false;
			$scope.AffectPlatform_show = false;
			$scope.ImpactLevel_show = false;
			break
		default:
			$scope.Reference_show = false;
			$scope.Contents_show = false;
			$scope.Determine_show = false;	
			$scope.Description_show =false;
			$scope.AffectPlatform_show = false;
			$scope.ImpactLevel_show = false;
			break
		}		
		};
		
		$scope.getSourceName = function(code){
	        var Name = "";
		    angular.forEach($scope.sourceCodes, function(value, key) {
		    	if(value.Code==code) {
		    		Name=value.Name;
	   	        }
		    });
		    return Name;
	     }	
			
	
	$scope.changeSelectAllorNone = function() {			
		angular.forEach($scope.members, function(memberItem) {			
			if (memberItem.Show)
				memberItem.Action = $scope.selectSelAllorNone;								
			});				
	}
	
	$scope.buttonToAlert = function(id) {
		bootbox
		.confirm(
				"確定要通報此警訊嗎？",
				function(result) {
					if (result) {
						var request = {Id:id};
						$http
								.post('./api/n01/toAlert', request, csrf_config)
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
												message : "<span class='bigger-110'>資料通報失敗</span>",
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
	
	
	$scope.queryButtonCount = function() {
		if ($scope.SPostDateTime == "")
			$scope.SPostDateTime = null;
		if ($scope.EPostDateTime == "")
			$scope.EPostDateTime = null;
		if ($scope.QueryKeyword == "")
			$scope.QueryKeyword = null;
		if ($scope.QueryIsReply == "")
			$scope.QueryIsReply = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		
		var request = {
			Status : $scope.QueryStatus,
			IsReply : $scope.QueryIsReply,
			SPostDateTime : $scope.SPostDateTime,
			EPostDateTime : $scope.EPostDateTime,
			Keyword : $scope.QueryKeyword,
			
		};
		$http.post('./api/n01/query/button/count', request, csrf_config).then(function(response) {
			$scope.button_count_allitems = response.data.datatable;
			//console.log("response="+JSON.stringify(response.data.datatable));
			$scope.getPageButtonCount();
		}).catch(function() {
			bootbox.alert({
				message : globalReadDataFail,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() { });
	}
	//$scope.queryButtonCount();
	
	$scope.getCount = function(Status){
        var total = 0;
	    angular.forEach($scope.button_count_allitems, function(value, key) {
	    	if(value.Status==Status ) {
   	        	total=value.Count;
   	        }
	    });
    	return total;
     }
	
	$scope.getPageButtonCount = function(){
		
		$scope.ButtonCount1=$scope.getCount(1)+$scope.getCount(10);
		$scope.ButtonCount2=$scope.getCount(2);
		$scope.ButtonCount3=$scope.getCount(3);
		$scope.ButtonCount5=$scope.getCount(5);
		
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		if ($("#subsystem_not").length > 0) {
			var subsystem_not_count = 0;
			if ($("#form_not").length > 0) {
				$
						.ajax(
								{
									url : "../pub/api/count/not",
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
										$("#form_not")
												.html(count);
										subsystem_not_count = subsystem_not_count + count;
									} else {
										$("#form_not")
										.html('');
									}
								});
			}
			if (subsystem_not_count > 0) {
				$("#subsystem_not > .badge").html(
						subsystem_not_count);
			} else {
				$("#subsystem_not > .badge").html(
						'');
			}
		}
	
	}
	
	$scope.setSortName = function(sorttype) {
		$scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse
				: false;
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
	
	
	 $scope.exportData = function(postId) {
		 
			// alert("excel");
		        var blob = new Blob([document.getElementById('exporExcel').innerHTML], {
		            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
		            endings:'native'
		        });
		        saveAs(blob, "("+ postId +")message_"+(new Date()).toLocaleString(undefined, {
			   	    day:'numeric',
			   	    month: 'numeric',
			   	    year: 'numeric',
			   	    hour: '2-digit',
			   	    minute: '2-digit',
			   	    second: '2-digit',
			   	    hour12: false 
			   	})+".xls");
		    }
}