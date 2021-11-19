var myApp = angular.module('myApp', [ 'bw.paging', 'ngSanitize', 'ui.toggle'  ]).controller('getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

myApp.filter('BrHtml',function(){
    return function(input){
    	if (input) {
    		input = input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />');    		
    		return input.split('<br />')
    	}
    };
});

function getAppController($scope, $rootScope, $http, $window) {
	var id = window.location.search.substring(1);
	$scope.id = "";
	
	// Paging Start
	$scope.RuleIsOpen = true
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "sort";
	$scope.sortreverse = true;
	$scope.isBreakLine = true;
	$scope.isHideName = false;

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
	// Paging End
	
	$scope.clearData = function() {
		$scope.id = "";
		$rootScope.detailTitle = "";
		$scope.detailSourceName = "";
		$scope.detailPostDataTime  = "";
		$scope.detailContent = "";
		$scope.isEnableRating = "";
		$scope.avgStars = "";
		$scope.ratingStar = "";
		$scope.stars = "";
				
		$scope.comments = "";
		$scope.commentData = "";		
		
		$("#divQuery").show();
		$("#divDetail").hide();
		$("#ratingWindow").modal("hide");
		$("#commentWindow").modal("hide");
	}

	// Query Data Start
	$scope.queryData = function(page) {
		$("#divQuery").show();
		$("#divDetail").hide();
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}		
		var request = {
				start : $scope.start,
				maxRows : $scope.maxRows,
				sort : $scope.sorttype,
				dir : true,
				keyword : $scope.QueryKeyword
			};
		$http.post('./api/p06/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
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
	}
	// Query Data End

	// Query Detail Data Start
	$scope.queryDetailData = function(id) {
		$scope.commentData = "";
		$("#divQuery").hide();
		$("#divDetail").hide();
		$("#ratingWindow").modal("hide");
		$("#commentWindow").modal("hide");
		$scope.id = id;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : $scope.id
		};

		$http.post('./api/p06/query/id', request, csrf_config).then(function(response) {
			$rootScope.detailTitle = response.data[0].IncidentTitle;
			$scope.detailIncidentId = response.data[0].IncidentId;
			$scope.detailIncidentDiscoveryTime = response.data[0].IncidentDiscoveryTime;
			$scope.detailEventTypeName = response.data[0].EventTypeName;
			$scope.detailSourceName = response.data[0].Reference;
			$scope.detailPostDataTime = response.data[0].IncidentReportedTime;
			$scope.detailContent = response.data[0].Description;
			$scope.detailReporterName = response.data[0].ReporterName;
			$scope.detailReporterNameUrl = response.data[0].ReporterNameUrl;
			$scope.detailResponderPartyName = response.data[0].ResponderPartyName;
			$scope.detailResponderContactNumbers = response.data[0].ResponderContactNumbers;
			$scope.detailResponderElectronicAddressIdentifiers = response.data[0].ResponderElectronicAddressIdentifiers;
			$scope.detailImpactQualification = response.data[0].ImpactQualification;
			$scope.detailCoaDescription = response.data[0].CoaDescription;
			$scope.detailConfidence = response.data[0].Confidence;
			$scope.detailReference = response.data[0].Reference;
			$scope.detailReferences = $scope.detailReference.split(";");
			$scope.detailAffectedSoftwareDescription = response.data[0].AffectedSoftwareDescription;
			$scope.isDetail =  response.data[0].IsDetail;

			// Star Rating works Start
			$scope.isEnableRating = response.data[0].IsEnableRating;
			$scope.avgStars = response.data[0].AvgStars.toFixed(1);
			if(!$scope.isEnableRating){
				$scope.stars = response.data[0].Stars;
			}else{
				$scope.stars = 0;
			}
			
			var integerPart = parseInt($scope.avgStars, 10);
			var floatPart = $scope.avgStars - integerPart;
			for(var i=0; i<5; ++i){
				document.getElementsByClassName("star")[i].className = "star";
			}
			for(var i=0; i<integerPart; ++i){
				document.getElementsByClassName("star")[i].className = "star on";
			}
			if(floatPart >= 0.5){
				document.getElementsByClassName("star")[(integerPart)].className = "star half";
			}
			// Star Rating works End

			// Star Comments works Start			
			$scope.comments = response.data[0].Comments;					

			// Star Comments works End

			
			bootbox.hideAll();
			if ($scope.isDetail == false) {
				bootbox.alert({
					message : $scope.detailContent,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-danger'
						}
					},
					callback: function() {
						location.href="p06"
					}
				});
			}
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
				callback: function() {
					location.href="p06"
				}
			});
		}).finally(function() {
			$("#divDetail").show();
		});
	}
	// Query Detail Data End

	// Insert Star rating Start
	$scope.insertStarRating = function(){
		var request = {
			Id : $scope.id,
			RatingStar: $scope.ratingStar
		};
		$http.post('./api/p06/starRating/insert', request, csrf_config).then(
			function(response){
				if (response.data.success){
					$scope.queryDetailData($scope.id);
				}else{
					bootbox.dialog(
						{
							message: "<span class='bigger-110'>" + response.data.msg + "</span>",
							buttons: {
								"success": {
									"label": "<i class='ace-icon fa fa-close'></i> 關閉",
									"className": 'btn-warning',
									"callback": function () {}
								}
							}
						}
					);
				}
			}
		).catch(
			function(){
					bootbox.dialog(
					{
						message: "<span class='bigger-110'>資料新增失敗</span>",
						buttons: {
							"success": {
								"label": "<i class='ace-icon fa fa-close'></i> 關閉",
								"className": 'btn-danger',
								"callback": function () {}
							}
						}
					}
				);
			}
		).finally(function(){});
	}
	// Insert Star rating End
	
	// Insert Comment Start
	$scope.insertComment = function(){
		var request = {
			Id : $scope.id,
			Comment: $scope.commentData,
			IsHideName : $scope.isHideName
		};
		$http.post('./api/p06/comment/insert', request, csrf_config).then(
			function(response){
				if (response.data.success){
					$scope.queryDetailData($scope.id);
				}else{
					bootbox.dialog(
						{
							message: "<span class='bigger-110'>" + response.data.msg + "</span>",
							buttons: {
								"success": {
									"label": "<i class='ace-icon fa fa-close'></i> 關閉",
									"className": 'btn-warning',
									"callback": function () {}
								}
							}
						}
					);
				}
			}
		).catch(
			function(){
					bootbox.dialog(
					{
						message: "<span class='bigger-110'>資料新增失敗</span>",
						buttons: {
							"success": {
								"label": "<i class='ace-icon fa fa-close'></i> 關閉",
								"className": 'btn-danger',
								"callback": function () {}
							}
						}
					}
				);
			}
		).finally(function(){});
	}
	// Insert Comment End
	

	//openOrCloseRule
	$scope.changeRuleIsOpen = function() {	
		$scope.RuleIsOpen = !$scope.RuleIsOpen
	}
	
	// Delete Comment Start
	
	$scope.CancelReply = function(id){
		bootbox.confirm("確定要刪除此話題嗎？",
				function(result) {
						if (result) {
		var request = {
				Id : id	
			};
		$http.post('./api/p06/comment/delete', request, csrf_config).then(
				function(response){
					if (response.data.success){
						$scope.queryDetailData($scope.id);
					}else{
						bootbox.dialog(
							{
								message: "<span class='bigger-110'>" + response.data.msg + "</span>",
								buttons: {
									"success": {
										"label": "<i class='ace-icon fa fa-close'></i> 關閉",
										"className": 'btn-warning',
										"callback": function () {}
									}
								}
							}
						);
					}
				}
			).catch(
				function(){
						bootbox.dialog(
						{
							message: "<span class='bigger-110'>資料刪除失敗</span>",
							buttons: {
								"success": {
									"label": "<i class='ace-icon fa fa-close'></i> 關閉",
									"className": 'btn-danger',
									"callback": function () {}
								}
							}
						}
					);
				}
			).finally(function(){});
						}
		});		
		}
	
	// Delete Comment End
	

	if (id) {
		$scope.queryDetailData(id);
	} else {
		$scope.clearData();
		$scope.queryData();
	}
};
