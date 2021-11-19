var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {
	
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "createTime";
	$scope.sortreverse = true;
	
	$scope.PostId = null;
	$scope.SourceCode = "HISAC";
	$scope.IsEnable = true;
	$scope.ImpactQualification = 0;
	
	$("#divReview").hide();
	$scope.QuerySModifyTime = null;
	$scope.QueryEModifyTime = null;
	$scope.IncidentDiscoveryTime = null;
	$scope.IncidentReportedTime = null;
	$scope.IncidentClosedTime = null;
	$scope.RelatedIncidentTimestamp = null;
	

	$scope.FileDesc1 = null;
	$scope.FileData1 = null;
	$scope.FileDesc2 = null;
	$scope.FileData2 = null;
	$scope.FileDesc3 = null;
	$scope.FileData3 = null;
	
	$scope.ButtonCount1=0;
	$scope.ButtonCount2=0;
	$scope.ButtonCount3=0;
	
	
	$('input[id="QuerySModifyTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#QuerySModifyTime").on("dp.change", function(e) {
		$scope.QuerySModifyTime = $('#QuerySModifyTime').val();
    });
	$('input[id="QueryEModifyTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#QueryEModifyTime").on("dp.change", function(e) {	 
		$scope.QueryEModifyTime = $('#QueryEModifyTime').val();
    });
	
	
	
	$('input[id="IncidentDiscoveryTime"]').datetimepicker({		
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
	});
	$("#IncidentDiscoveryTime").on("dp.change", function(e) {	       
		$scope.IncidentDiscoveryTime = $('#IncidentDiscoveryTime').val();
    });
	$('input[id="IncidentReportedTime"]').datetimepicker({		
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
	});
	$("#IncidentReportedTime").on("dp.change", function(e) {	       
		$scope.IncidentReportedTime = $('#IncidentReportedTime').val();
    });
	$('input[id="IncidentClosedTime"]').datetimepicker({		
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
	});
	$("#IncidentClosedTime").on("dp.change", function(e) {	       
		$scope.IncidentClosedTime = $('#IncidentClosedTime').val();
    });
	$('input[id="RelatedIncidentTimestamp"]').datetimepicker({		
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-TW'
	});
	$("#RelatedIncidentTimestamp").on("dp.change", function(e) {	       
		$scope.RelatedIncidentTimestamp = $('#RelatedIncidentTimestamp').val();
    });
	

	$scope.setstatus = function(status) {		
		 $scope.QueryStatus = status;
		 $scope.queryData();
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
	$scope.clearData = function() {
		$scope.QuerySModifyTime = null;
		$scope.QueryEModifyTime = null
		$('#QuerySModifyTime').data("DateTimePicker").clear()
        $('#QueryEModifyTime').data("DateTimePicker").clear()
		$scope.QueryStatus = null;
		$scope.QueryReporterName = null;
		$scope.QuerySourceCode = null;
		$scope.QueryStixTitle = null;
		$scope.QueryDescription = "";
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	$scope.clearData();

	
	
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		if ($scope.QuerySModifyTime == "")
			$scope.QuerySModifyTime = null;
		if ($scope.QueryEModifyTime == "")
			$scope.QueryEModifyTime = null;
		if ($scope.QueryStatus=="")
			$scope.QueryStatus = null;
		if ($scope.QuerySourceCode == "")
			$scope.QuerySourceCode = "";
		if ($scope.QueryStixTitle == "")
			$scope.QueryStixTitle = "";
		if($scope.QueryDescription=="")
			$scope.QueryDescription = "";
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			
			SModifyTime : $scope.QuerySModifyTime ,
			EModifyTime : $scope.QueryEModifyTime ,
			Status : $scope.QueryStatus,
			SourceCode : $scope.QuerySourceCode ,
			StixTitle : $scope.QueryStixTitle,
			Description : $scope.QueryDescription,
			
		};
// console.log("request="+JSON.stringify(request));
		
		$http.post('./api/c01/query', request, csrf_config).then(function(response) {
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

	 $scope.openEdit  = function ()
	  {
		$scope.deletefile()
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.btnUpdExt = false;
		$scope.PostId = null;
		$scope.SourceCode = "HISAC";
		
		$scope.StixTitle = null;
		$scope.IncidentId = null;
		$scope.IncidentTitle = "";
		$scope.IncidentDiscoveryTime = null;
		$scope.IncidentReportedTime = null;
		$scope.IncidentClosedTime = null;
		$scope.Description = "";
		$scope.Category = null;
		$scope.ReporterName = "H-ISAC";
		$scope.ReporterNameUrl = null;
		$scope.ResponderPartyName = null;
		$scope.ResponderContactNumbers = null;
		$scope.ResponderElectronicAddressIdentifiers = null;
		$scope.ImpactQualification = null;
		$scope.CoaDescription = null;
		$scope.Confidence = null;
		$scope.Reference = null;
		$scope.ObservableAttach = null;
		$scope.ObservableIpAddress = null;
		$scope.SocketIpAddress = null;
		$scope.SocketPort = null;
		$scope.SocketProtocol = null;
		$scope.CustomIpAddress = null;
		$scope.CustomPort = null;
		$scope.CustomProtocol = null;
		$scope.DestinationIpAddress = null;
		$scope.DestinationPort = null;
		$scope.DestinationProtocol = null;
		$scope.LeveragedDescription = null;
		$scope.AffectedSoftwareDescription = null;
		$scope.ResourcesSourceIpAddress = null;
		$scope.ResourcesSourcePort = null;
		$scope.ResourcesDestinationPort = null;
		$scope.ResourcesDestinationProtocol = null;
		$scope.ResourcesDestination = null;
		$scope.ScanEngine = null;
		$scope.ScanVersion = null;
		$scope.ScanResult = null;
		$scope.RelatedIncidentId = null;
		$scope.RelatedIncidentTimestamp = null;
		$scope.Status = null;
		$scope.SourceContentXml = null;
// $scope.IsEnable = null;
		$scope.CreateId = null;
		$scope.CreateTime = null;
		$scope.ModifyId = null;
		$scope.ModifyTime = null;
		$scope.NewsManagementGroupId = null;
		$scope.NhiSourceRecord = null;
		$scope.NhiImpact = null;
		$scope.NhiRiskGrade = null;
		$scope.NhiRiskType = null;
		$scope.NhiProcess = null;
		$scope.NhiProblemIpAddress = null;
		$scope.NhiProblemPort = null;
		$scope.NhiProblemUrl = null;
		$scope.NhiProblemEquipmentOwner = null;
		$scope.NhiProblemEquipmentUse = null;
		$scope.NhiRemark = null;
		$scope.TransferInType = null;
		$scope.TransferInId = null;
		$scope.TransferOutType = null;
		$scope.TransferOutId = null;
		
		$scope.FileData1 = null;
		$scope.FileDesc1 = null;
		$scope.FileData2 = null;
		$scope.FileDesc2 = null;
		$scope.FileData3 = null;
		$scope.FileDesc3 = null;
	  }
	 
	 $scope.click_postid = function(item) {		
			if (item.IsButtonEdit)
				$scope.editData(item.Id);
			else if (item.IsButtonReview)
				$scope.extendView(item.Id, item.IsSeeLog, 'review')
			else
				$scope.extendView(item.Id, item.IsSeeLog, 'view')
		}
	 
	$scope.extendView = function(id, IsSeeLog, action){
		
		$scope.FileData1 = "";
		$scope.FileDesc1 = "";
		$scope.FileData2 = "";
		$scope.FileDesc2 = "";
		$scope.FileData3 = "";
		$scope.FileDesc3 = "";
		$scope.IsSeeLog = IsSeeLog;	
		$scope.Opinion = null
		if (action == "view")
			$scope.Review = false				
		else
			$scope.Review = true;			
		$("#divEdit").hide();
		$("#divQuery").hide();
		$("#divReview").show();
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		$http.post('./api/c01/query/id', request, csrf_config).then(function(response) {
			$scope.reviewData = null;
			$scope.reviewItem = null;
// $scope.openEdit();
// $scope.btnIns = false;
// $scope.btnUpd = true; +JSON.stringify(request)
			$scope.reviewData = response.data[0];
			$scope.reviewItem = response.data[0]['InformationExchangeAttach'];
			
			for ( var i=0;i< $scope.eventTypes.length;i++){
				var eventType = $scope.eventTypes[i];
				if($scope.reviewData.Category == eventType.Code){
					$scope.reviewData['CategoryName']=eventType.Name;
				}
			}
			
			bootbox.hideAll();
		}).catch(function() {
			bootbox.hideAll();
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
	
	$scope.sendQueryData = function() {
		$scope.currentPage = 1;
		$scope.start = 0;
		$scope.queryData();
	}

		// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divReview").hide();
		$("#divQuery").show();
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	 
	$scope.editData = function(id) {
		$scope.Opinion = null

		$scope.btnIns = true;
		$scope.btnUpd = true;
		$scope.btnUpdExt = false;
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id,			
		};		
		$http.post('./api/c01/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.InformationExchangeLog = response.data[0].InformationExchangeLog;
			$scope.cleanFile();
			
			if (response.data[0].Status!=1)
				$scope.IsSeeLog = true
			else
				$scope.IsSeeLog = false
			
			$scope.Id = response.data[0].Id;
			
			$scope.PostId = response.data[0].PostId;
			$scope.SourceCode = response.data[0].SourceCode;
			$scope.StixTitle = response.data[0].StixTitle;
			$scope.IncidentId = response.data[0].IncidentId;
			$scope.IncidentTitle = response.data[0].IncidentTitle;
			$scope.IncidentDiscoveryTime = response.data[0].IncidentDiscoveryTime;
			$scope.IncidentReportedTime = response.data[0].IncidentReportedTime;
			$scope.IncidentClosedTime = response.data[0].IncidentClosedTime;
			$scope.Description = response.data[0].Description;
			$scope.Category = response.data[0].Category;
			$scope.ReporterName = response.data[0].ReporterName;
			$scope.ReporterNameUrl = response.data[0].ReporterNameUrl;
			$scope.ResponderPartyName = response.data[0].ResponderPartyName;
			$scope.ResponderContactNumbers = response.data[0].ResponderContactNumbers;
			$scope.ResponderElectronicAddressIdentifiers = response.data[0].ResponderElectronicAddressIdentifiers;
			$scope.ImpactQualification = response.data[0].ImpactQualification;
			$scope.CoaDescription = response.data[0].CoaDescription;
			$scope.Confidence = response.data[0].Confidence;
			$scope.Reference = response.data[0].Reference;
			$scope.ObservableAttach = response.data[0].ObservableAttach;
			$scope.ObservableIpAddress = response.data[0].ObservableIpAddress;
			$scope.SocketIpAddress = response.data[0].SocketIpAddress;
			$scope.SocketPort = response.data[0].SocketPort;
			$scope.SocketProtocol = response.data[0].SocketProtocol;
			$scope.CustomIpAddress = response.data[0].CustomIpAddress;
			$scope.CustomPort = response.data[0].CustomPort;
			$scope.CustomProtocol = response.data[0].CustomProtocol;
			$scope.DestinationIpAddress = response.data[0].DestinationIpAddress;
			$scope.DestinationPort = response.data[0].DestinationPort;
			$scope.DestinationProtocol = response.data[0].DestinationProtocol;
			$scope.LeveragedDescription = response.data[0].LeveragedDescription;
			$scope.AffectedSoftwareDescription = response.data[0].AffectedSoftwareDescription;
			$scope.ResourcesSourceIpAddress = response.data[0].ResourcesSourceIpAddress;
			$scope.ResourcesSourcePort = response.data[0].ResourcesSourcePort;
			$scope.ResourcesDestinationPort = response.data[0].ResourcesDestinationPort;
			$scope.ResourcesDestinationProtocol = response.data[0].ResourcesDestinationProtocol;
			$scope.ResourcesDestination = response.data[0].ResourcesDestination;
			$scope.ScanEngine = response.data[0].ScanEngine;
			$scope.ScanVersion = response.data[0].ScanVersion;
			$scope.ScanResult = response.data[0].ScanResult;
			$scope.RelatedIncidentId = response.data[0].RelatedIncidentId;
			$scope.RelatedIncidentTimestamp = response.data[0].RelatedIncidentTimestamp;
			$scope.Status = response.data[0].Status;
			$scope.SourceContentXml = response.data[0].SourceContentXml;
// $scope.IsEnable = response.data[0].IsEnable;
			$scope.CreateId = response.data[0].CreateId;
			$scope.CreateTime = response.data[0].CreateTime;
			$scope.ModifyId = response.data[0].ModifyId;
			$scope.ModifyTime = response.data[0].ModifyTime;
			$scope.NewsManagementGroupId = response.data[0].NewsManagementGroupId;
			
			$scope.NhiSourceRecord = response.data[0].NhiSourceRecord;
			$scope.NhiImpact = response.data[0].NhiImpact;
			$scope.NhiRiskGrade = response.data[0].NhiRiskGrade;
			$scope.NhiRiskType = response.data[0].NhiRiskType;
			$scope.NhiProcess = response.data[0].NhiProcess;
			$scope.NhiProblemIpAddress = response.data[0].NhiProblemIpAddress;
			$scope.NhiProblemPort = response.data[0].NhiProblemPort;
			$scope.NhiProblemUrl = response.data[0].NhiProblemUrl;
			$scope.NhiProblemEquipmentOwner = response.data[0].NhiProblemEquipmentOwner;
			$scope.NhiProblemEquipmentUse = response.data[0].NhiProblemEquipmentUse;
			$scope.NhiRemark = response.data[0].NhiRemark;
			$scope.TransferInType = response.data[0].TransferInType;
			$scope.TransferInId = response.data[0].TransferInId;
			$scope.TransferOutType = response.data[0].TransferOutType;
			$scope.TransferOutId = response.data[0].TransferOutId;
			$scope.PreStatus = response.data[0].Status;
			$scope.HasXml = response.data[0].HasXml;
			
			for (var i=0;i<response.data[0].InformationExchangeAttach.length;i++){
				if (i==0){
					$scope.FileData1 = response.data[0].InformationExchangeAttach[0]
					$scope.FileDesc1 = response.data[0].InformationExchangeAttach[0]["FileDesc"]
					$scope.FileAttachId1 = response.data[0].InformationExchangeAttach[0]["Id"]
				}
				if (i==1){
					$scope.FileData2 = response.data[0].InformationExchangeAttach[1]
					$scope.FileDesc2 = response.data[0].InformationExchangeAttach[1]["FileDesc"]
					$scope.FileAttachId2 = response.data[0].InformationExchangeAttach[1]["Id"]
				}
				if (i==2){
					$scope.FileData3 = response.data[0].InformationExchangeAttach[2]
					$scope.FileDesc3 = response.data[0].InformationExchangeAttach[2]["FileDesc"]
					$scope.FileAttachId3 = response.data[0].InformationExchangeAttach[2]["Id"]
				}
			}		
			
			bootbox.hideAll();
		}).catch(function() {
			bootbox.hideAll();
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
		}).finally(function() { 
			if($scope.SourceCode=="HISAC"){
				$scope.btnUpdExt = true;
			}
		});
	}
	
	$scope.cleanFile=function(){
		$scope.FileDesc1 = null;
		$scope.FileDesc2 = null;
		$scope.FileDesc3 = null;
		$scope.FileData1 = null;
		$scope.FileData2= null;
		$scope.FileData3 = null;
		$scope.FileAttachId1 = null;
		$scope.FileAttachId2 = null;
		$scope.FileAttachId3 = null;
	}
	 
	 
	$scope.createData = function(exit,check) {		
		if (check){
			$scope.Status = 3;
			$scope.IsWriteProcessLog = true
		}
		else{
			$scope.Status = 1;
			$scope.IsWriteProcessLog = false
		}
				
		if ( $scope.IncidentDiscoveryTime == "" ) $scope.IncidentDiscoveryTime = null;
		if ( $scope.IncidentReportedTime == "" ) $scope.IncidentReportedTime = null;
		if ( $scope.IncidentClosedTime == "" ) $scope.IncidentClosedTime = null;
		if ( $scope.RelatedIncidentTimestamp == "" ) $scope.RelatedIncidentTimestamp = null;		
		
		var data = {
			Id : null,
			StixTitle : $scope.StixTitle,
			IncidentId : $scope.IncidentId,
			IncidentTitle : $scope.IncidentTitle,
			Description : $scope.Description,
			Category : $scope.Category,
			ReporterName : $scope.ReporterName,
			ResponderPartyName : $scope.ResponderPartyName,
			ResponderContactNumbers : $scope.ResponderContactNumbers,
			ResponderElectronicAddressIdentifiers : $scope.ResponderElectronicAddressIdentifiers,
			ImpactQualification : $scope.ImpactQualification,
			CoaDescription : $scope.CoaDescription,
			Confidence : $scope.Confidence,
			Reference : $scope.Reference,
			ObservableAttach : $scope.ObservableAttach,
			ObservableIpAddress : $scope.ObservableIpAddress,
			SocketIpAddress : $scope.SocketIpAddress,
			SocketPort : $scope.SocketPort,
			SocketProtocol : $scope.SocketProtocol,
			CustomIpAddress : $scope.CustomIpAddress,
			CustomPort : $scope.CustomPort,
			CustomProtocol : $scope.CustomProtocol,
			DestinationIpAddress : $scope.DestinationIpAddress,
			DestinationPort : $scope.DestinationPort,
			DestinationProtocol : $scope.DestinationProtocol,
			LeveragedDescription : $scope.LeveragedDescription,
			AffectedSoftwareDescription : $scope.AffectedSoftwareDescription,
			ResourcesSourceIpAddress : $scope.ResourcesSourceIpAddress,
			ResourcesSourcePort : $scope.ResourcesSourcePort,
			ResourcesDestinationPort : $scope.ResourcesDestinationPort,
			ResourcesDestinationProtocol : $scope.ResourcesDestinationProtocol,
			ResourcesDestination : $scope.ResourcesDestination,
			ScanEngine : $scope.ScanEngine,
			ScanVersion : $scope.ScanVersion,
			ScanResult : $scope.ScanResult,
			RelatedIncidentId : $scope.RelatedIncidentId,
			IncidentDiscoveryTime : $scope.IncidentDiscoveryTime,
			IncidentReportedTime : $scope.IncidentReportedTime,
			IncidentClosedTime : $scope.IncidentClosedTime,
			RelatedIncidentTimestamp : $scope.RelatedIncidentTimestamp,
			Status : $scope.Status,			
			IsEnable : $scope.IsEnable,
			SourceCode : $scope.SourceCode,
			IsWriteProcessLog : $scope.IsWriteProcessLog,
			TableName : "informationExchange",
			PreStatus : "1",
			
			
			ReporterNameUrl : $scope.ReporterNameUrl,
			NhiProcess : $scope.NhiProcess,
			NhiSourceRecord : $scope.NhiSourceRecord,
			NhiImpact : $scope.NhiImpact,
			NhiRiskGrade : $scope.NhiRiskGrade,
			NhiRiskType : $scope.NhiRiskType,
			NhiProblemIpAddress : $scope.NhiProblemIpAddress,
			NhiProblemPort : $scope.NhiProblemPort,
			NhiProblemUrl : $scope.NhiProblemUrl,
			NhiProblemEquipmentOwner : $scope.NhiProblemEquipmentOwner,
			NhiProblemEquipmentUse : $scope.NhiProblemEquipmentUse,
			NhiRemark : $scope.NhiRemark
			
// FileDesc : $scope.FileDesc,
// FileData : $scope.FileData
		};

		if ( $scope.FileDesc1 == null ) { $scope.FileDesc1 = ""; }
		if ( $scope.FileDesc2 == null ) { $scope.FileDesc2 = ""; }
		if ( $scope.FileDesc3 == null ) { $scope.FileDesc3 = ""; }
		
		Upload
		.upload({
			url : './api/c01/create/',
			data : {
				json : JSON.stringify(data),
				FileDesc1 : $scope.FileDesc1,
				FileData1 : $scope.FileData1,
				FileAttachId1 : $scope.FileAttachId1,
				FileDesc2 : $scope.FileDesc2,
				FileData2 : $scope.FileData2,
				FileAttachId2 : $scope.FileAttachId2,
				FileDesc3 : $scope.FileDesc3,
				FileData3 : $scope.FileData3,
				FileAttachId3 : $scope.FileAttachId3
			},
			headers: header
		})
		.then(
				function(response) {
					if (response.data.success) {
						$scope.queryData($scope.currentPage);
						$scope.PostId = response.data.PostId
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-success',
								}
							},
							callback: function() {
								if ($scope.pageRows == 1
										&& $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}	
								$scope.queryData($scope.currentPage);
								if (exit)
								$scope.closeEdit();
								else
								{														
								$scope.btnIns = false;
								$scope.btnUpd = true;
								$scope.PreStatus = "1"									
								$scope.Id = response.data.Id
								$scope.PostId = response.data.PostId
								}
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
						message : globalInsertDataFail,
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

	$scope.updateData = function(exit,check) {
		
		if (check){
			$scope.Status = 3;
			$scope.IsWriteProcessLog = true
		}
		else{
			$scope.Status = 1;
			$scope.IsWriteProcessLog = false
		}
		
		if ( $scope.IncidentDiscoveryTime == "" ) $scope.IncidentDiscoveryTime = null;
		if ( $scope.IncidentReportedTime == "" ) $scope.IncidentReportedTime = null;
		if ( $scope.IncidentClosedTime == "" ) $scope.IncidentClosedTime = null;
		if ( $scope.RelatedIncidentTimestamp == "" ) $scope.RelatedIncidentTimestamp = null;	
		
		var data = {
			Id : $scope.Id,
			StixTitle : $scope.StixTitle,
			IncidentId : $scope.IncidentId,
			IncidentTitle : $scope.IncidentTitle,
			Description : $scope.Description,
			Category : $scope.Category,
			ReporterName : $scope.ReporterName,
			ResponderPartyName : $scope.ResponderPartyName,
			ResponderContactNumbers : $scope.ResponderContactNumbers,
			ResponderElectronicAddressIdentifiers : $scope.ResponderElectronicAddressIdentifiers,
			ImpactQualification : $scope.ImpactQualification,
			CoaDescription : $scope.CoaDescription,
			Confidence : $scope.Confidence,
			Reference : $scope.Reference,
			ObservableAttach : $scope.ObservableAttach,
			ObservableIpAddress : $scope.ObservableIpAddress,
			SocketIpAddress : $scope.SocketIpAddress,
			SocketPort : $scope.SocketPort,
			SocketProtocol : $scope.SocketProtocol,
			CustomIpAddress : $scope.CustomIpAddress,
			CustomPort : $scope.CustomPort,
			CustomProtocol : $scope.CustomProtocol,
			DestinationIpAddress : $scope.DestinationIpAddress,
			DestinationPort : $scope.DestinationPort,
			DestinationProtocol : $scope.DestinationProtocol,
			LeveragedDescription : $scope.LeveragedDescription,
			AffectedSoftwareDescription : $scope.AffectedSoftwareDescription,
			ResourcesSourceIpAddress : $scope.ResourcesSourceIpAddress,
			ResourcesSourcePort : $scope.ResourcesSourcePort,
			ResourcesDestinationPort : $scope.ResourcesDestinationPort,
			ResourcesDestinationProtocol : $scope.ResourcesDestinationProtocol,
			ResourcesDestination : $scope.ResourcesDestination,
			ScanEngine : $scope.ScanEngine,
			ScanVersion : $scope.ScanVersion,
			ScanResult : $scope.ScanResult,
			RelatedIncidentId : $scope.RelatedIncidentId,
			IncidentDiscoveryTime : $scope.IncidentDiscoveryTime,
			IncidentReportedTime : $scope.IncidentReportedTime,
			IncidentClosedTime : $scope.IncidentClosedTime,
			RelatedIncidentTimestamp : $scope.RelatedIncidentTimestamp,
			Status : $scope.Status,
			IsWriteProcessLog : $scope.IsWriteProcessLog,
			TableName : "informationExchange",
			PreStatus : $scope.PreStatus,
			SourceCode : $scope.SourceCode,
			
			
			
			ReporterNameUrl : $scope.ReporterNameUrl,
			NhiProcess : $scope.NhiProcess,
			NhiSourceRecord : $scope.NhiSourceRecord,
			NhiImpact : $scope.NhiImpact,
			NhiRiskGrade : $scope.NhiRiskGrade,
			NhiRiskType : $scope.NhiRiskType,
			NhiProblemIpAddress : $scope.NhiProblemIpAddress,
			NhiProblemPort : $scope.NhiProblemPort,
			NhiProblemUrl : $scope.NhiProblemUrl,
			NhiProblemEquipmentOwner : $scope.NhiProblemEquipmentOwner,
			NhiProblemEquipmentUse : $scope.NhiProblemEquipmentUse,
			NhiRemark : $scope.NhiRemark
			
// Status : s,
// IsEnable : $scope.IsEnable
		};
//		
		if ( $scope.FileAttachId1 == null ) { $scope.FileAttachId1 = 0; }
		if ( $scope.FileAttachId2 == null ) { $scope.FileAttachId2 = 0; }
		if ( $scope.FileAttachId3 == null ) { $scope.FileAttachId3 = 0; }

		
		if ( $scope.FileDesc1 == null ) { $scope.FileDesc1 = ""; }
		if ( $scope.FileDesc2 == null ) { $scope.FileDesc2 = ""; }
		if ( $scope.FileDesc3 == null ) { $scope.FileDesc3 = ""; }
		$scope.UpdateFile1 = false;
		$scope.UpdateFile2 = false;
		$scope.UpdateFile3 = false;
		if ($scope.FileData1 == null) $scope.UpdateFile1 = true;
		if ($scope.FileData2 == null) $scope.UpdateFile2 = true;
		if ($scope.FileData3 == null) $scope.UpdateFile3 = true;
		
		Upload.upload({
			url : './api/c01/update',
			data : {
				json : JSON.stringify({"data" : data}),
				FileData1 : $scope.FileData1,
				FileData2 : $scope.FileData2,
				FileData3 : $scope.FileData3,
				
				FileDesc1 : $scope.FileDesc1,
				FileDesc2 : $scope.FileDesc2,
				FileDesc3 : $scope.FileDesc3,
				
				FileAttachId1 : $scope.FileAttachId1,
				FileAttachId2 : $scope.FileAttachId2,
				FileAttachId3 : $scope.FileAttachId3,
				
				UpdateFile1 : $scope.UpdateFile1,
				UpdateFile2 : $scope.UpdateFile2,
				UpdateFile3 : $scope.UpdateFile3
				
			},
			headers: header
		}).then(
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
								if ($scope.pageRows == 1
										&& $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}	
								$scope.queryData($scope.currentPage);
								if (exit)
								$scope.closeEdit();
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
	

	$scope.deleteData = function(id) {
				bootbox.confirm({
					message: globalSureDeleteItem,
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
					callback: function(result) {
						if (result) {
							var request = {
								Id: id
							};
							$http.post('./api/c01/delete/', request, csrf_config).then(function(response) {
								if (response.data.success) {
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-success',
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
									message : globalDeleteDataFail,
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
				});
			}
	
	

	$scope.deletefile = function(fileId) {
		switch(fileId){
		case 1:
			$scope.FileData1 = null;
			$scope.FileDesc1 = "";
			break;
		case 2:
			$scope.FileData2 = null;
			$scope.FileDesc2 = "";
			break;
		case 3:
			$scope.FileData3 = null;
			$scope.FileDesc3 = "";
			break;
		}
	}
	
	
	$scope.getalertType = function() {
		var data = {};
		$http.post('./api/c01/getalert', data, csrf_config).then(function(response) {
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
	$scope.getalertType();
	
	$scope.geteventType = function() {
		var data = {AlertCode:$scope.StixTitle};
		$http.post('./api/c01/getevent', data, csrf_config).then(function(response) {
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
	$scope.geteventType();
	
	$scope.geteventTypeAll = function() {
		var data = {};
		$http.post('./api/c01/getevent/all', data, csrf_config).then(function(response) {
			$scope.eventTypesAll = response.data;
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
	$scope.geteventTypeAll();
	
	
	$scope.getsource = function() {
		var data = {};
		$http.post('./api/c01/getsource', data, csrf_config).then(function(response) {
			$scope.SourceCodes = response.data;
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
	$scope.getsource();
	
	
	$scope.getEventTypeName = function(code){
        var Name = "";
	    angular.forEach($scope.eventTypesAll, function(value, key) {
	    	if(value.Code==code) {
	    		Name=value.Name;
   	        }
	    });
	    return Name;
     }
	
	$scope.getSourceName = function(code){
        var Name = "";
	    angular.forEach($scope.SourceCodes, function(value, key) {
	    	if(value.Code==code) {
	    		Name=value.Name;
   	        }
	    });
	    return Name;
     }
	
	
	
	
	
	
	$scope.UpdateAlertCode = function() {	
		$scope.geteventType();
// switch($scope.AlertCode){
// case "ANA":
// $scope.Determine_show = false;
// $scope.Determine = null;
// $scope.Description_show =false;
// $scope.Description = null;
// $scope.Contents_show = true;
// $scope.AffectPlatform_show = true;
// $scope.ImpactLevel_show = true;
// $scope.Reference_show = true;
// break
// case "DEF":
// $scope.Determine_show = false;
// $scope.Determine = null;
// $scope.Contents_show = false;
// $scope.Contents = null;
// $scope.AffectPlatform_show = false;
// $scope.AffectPlatform = null;
// $scope.ImpactLevel_show = false;
// $scope.ImpactLevel = null;
// $scope.Description_show =true;
// $scope.Reference_show = true;
// break
// case "INT":
// $scope.Contents_show = false;
// $scope.Contents = null;
// $scope.AffectPlatform_show = false;
// $scope.AffectPlatform = null;
// $scope.ImpactLevel_show = false;
// $scope.ImpactLevel = null;
// $scope.Determine_show = true;
// $scope.Description_show =true;
// $scope.Reference_show = true;
// break
// case "EWA":
// $scope.Determine_show = false;
// $scope.Determine = null;
// $scope.Contents_show = false;
// $scope.Contents = null;
// $scope.AffectPlatform_show = false;
// $scope.AffectPlatform = null;
// $scope.ImpactLevel_show = false;
// $scope.ImpactLevel = null;
// $scope.Description_show =true;
// $scope.Reference_show = true;
// break
// case "FBI":
// $scope.Reference_show = false;
// $scope.Reference = null;
// $scope.Contents_show = false;
// $scope.Contents = null;
// $scope.Determine_show = false;
// $scope.Determine = null;
// $scope.Description_show =true;
// $scope.AffectPlatform_show = true;
// $scope.ImpactLevel_show = true;
// break
// case "OTH":
// $scope.Reference_show = false;
// $scope.Reference = null;
// $scope.Contents_show = true;
// $scope.Contents = null;
// $scope.Determine_show = false;
// $scope.Determine = null;
// $scope.Description_show =false;
// $scope.AffectPlatform_show = false;
// $scope.ImpactLevel_show = false;
// break
// default:
// $scope.Reference_show = true;
// $scope.Contents_show = true;
// $scope.Determine_show = true;
// $scope.Description_show =true;
// $scope.AffectPlatform_show = true;
// $scope.ImpactLevel_show = true;
// break
// }
		};
		$scope.examine = function(id, status) {
			var action = ""
			var preStatus = ""
			switch(status){
			case 2:
				action = "撤銷";
				preStatus = 3
				break;
			case 4:
				action = "轉公開資訊";
				preStatus = 3
				break;
			case 5:
				action = "轉警訊";
				preStatus = 3
				break;
			case 6:
				action = "轉N-ISAC";
				preStatus = 3
				break;
			case 7:
				action = "撤銷";
				preStatus = 2
				break;
			case 8:
				action = "退回";
				preStatus = 3
				break;
			}
			bootbox
			.confirm(
					"確定要"+action+"此情資嗎？",
					function(result) {
						if (result) {
							var request = {
									Id:id, 
									Status:status,
									PreStatus : preStatus,
									TableName : "informationExchange",
									Pre : "HISAC-INFO",
									Opinion : $scope.Opinion									
										};
							$http
									.post('./api/c01/examine', request, csrf_config)
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
																		$scope.closeEdit();
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
													message : "<span class='bigger-110'>情資"+action+"失敗</span>",
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
	
	
	 $scope.queryButtonCount = function() {
		    if ($scope.QuerySModifyTime == "")
				$scope.QuerySModifyTime = null;
			if ($scope.QueryEModifyTime == "")
				$scope.QueryEModifyTime = null;
			if ($scope.QueryStatus=="")
				$scope.QueryStatus = null;
			if ($scope.QuerySourceCode == "")
				$scope.QuerySourceCode = "";
			if ($scope.QueryStixTitle == "")
				$scope.QueryStixTitle = "";
			if($scope.QueryDescription=="")
				$scope.QueryDescription = "";
			
			var request = {
				SModifyTime : $scope.QuerySModifyTime ,
				EModifyTime : $scope.QueryEModifyTime ,
				Status : $scope.QueryStatus,
				SourceCode : $scope.QuerySourceCode ,
				StixTitle : $scope.QueryStixTitle,
				Description : $scope.QueryDescription,
				
			};

		$http.post('./api/c01/query/button/count', request, csrf_config).then(function(response) {
			$scope.button_count_allitems = response.data.datatable;
			// console.log("response="+JSON.stringify(response.data.datatable));
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
	// $scope.queryButtonCount();
	
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
		
		$scope.ButtonCount1=$scope.getCount(1)+$scope.getCount(8);
		$scope.ButtonCount2=$scope.getCount(2);
		$scope.ButtonCount3=$scope.getCount(3);
		
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		if ($("#subsystem_cyb").length > 0) {
			var subsystem_cyb_count = 0;
			if ($("#form_nisac").length > 0) {
				$
						.ajax(
								{
									url : "../pub/api/count/cyb",
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
										$("#form_nisac")
												.html(count);
										subsystem_cyb_count = subsystem_cyb_count + count;
									} else {
										$("#form_nisac")
										.html('');
									}
								});
			}
			if (subsystem_cyb_count > 0) {
				$("#subsystem_cyb > .badge").html(
						subsystem_cyb_count);
			} else {
				$("#subsystem_cyb > .badge").html(
						'');
			}
		}
	
	
	}
	
}
	



