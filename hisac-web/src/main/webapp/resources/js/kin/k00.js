var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload','bw.paging', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore,
		$anchorScroll, $location, Upload) {	
	
	$scope.IsMaintainPlanTab = true;
	
	
	$("#divQuery").show();
	$("#divEdit").hide();	
	
	$('input[id="EDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="SDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });
	
	$("#SDate").on("dp.change", function(e) {	       
		$scope.SDate = $('#SDate').val();
		if ($scope.SDate > $scope.EDate && $scope.EDate != null) {
			$scope.EDate = $scope.SDate;
			$('#EDate').val($scope.EDate)
		}
	});
	$("#EDate").on("dp.change", function(e) {	       
		$scope.EDate = $('#EDate').val();
		if ($scope.EDate < $scope.SDate && $scope.SDate != null) {
			$scope.SDate = $scope.EDate;
			$('#SDate').val($scope.SDate)
		}
	});
	
	$scope.getMember = function() {
		var data = {};
		$http.post('./api/k00/query/member', data, csrf_config).then(function(response) {
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
	
	$scope.getMember();
	
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "title";
	$scope.sortreverse = true;
	
	
	$scope.clearData = function() {          
        $scope.QueryKeyword = null;
        $('#EDate').val("")
		$('#SDate').val("")
		$('#EDate').data("DateTimePicker").clear()
        $('#SDate').data("DateTimePicker").clear()
        $scope.memberList = null;
	}
	$scope.clearData();
	
	$scope.memberList = [];
	
	/**查詢-開始**/
	$scope.queryData = function(page) {
		
        $("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		}
		if ($scope.SDate == "")
			$scope.SDate = null;
		if ($scope.EDate == "")
			$scope.EDate = null;
		if ($scope.QueryKeyword == "")
			$scope.QueryKeyword = null;		
		
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Sdate : $scope.SDate,
			Edate : $scope.EDate,
			Keyword : $scope.QueryKeyword,
			
		};
			$http.post('./api/k00/query', request, csrf_config).then(function(response) {	
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
	};
	/**查詢-結束**/
	$scope.queryData();
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$scope.Title = null;
		$scope.Year = null;		
		$scope.SPlanDate = null;
		$scope.EPlanDate = null;
		$scope.memberList = [];
		$('#divQuery').hide();
		$('#divEdit').show();
		$('input[id="SPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    }); 
		$('input[id="EPlanDate"]').datetimepicker({
			format: 'YYYY-MM-DD',
			locale: 'zh-TW'
	    });				
		$("#SPlanDate").on("dp.change", function(e) {	       
			$scope.SPlanDate = $('#SPlanDate').val();
			if ($scope.SPlanDate > $scope.EPlanDate && $scope.EPlanDate != null) {
				$scope.EPlanDate = $scope.SPlanDate;
				$('#EPlanDate').val($scope.EPlanDate)
			}
		});
		$("#EPlanDate").on("dp.change", function(e) {	       
			$scope.EPlanDate = $('#EPlanDate').val();
			if ($scope.EPlanDate < $scope.SPlanDate && $scope.SPlanDate != null) {
				$scope.SPlanDate = $scope.EPlanDate;
				$('#SPlanDate').val($scope.SPlanDate)
			}
		});
//		$scope.SPlanDate = moment().format("YYYY-MM-DD");	
//		$scope.EPlanDate = moment().format("YYYY-MM-DD");
		$scope.btnIns = true;
		$scope.btnUpd = false;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$('#divEdit').hide();
		$('#divQuery').show();
		$scope.IsQuestionnaire = false;
	};
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {
		$scope.openEdit();
		$scope.btnIns = false;
		$scope.btnUpd = true;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		
		$http.post('./api/k00/query/id', request, csrf_config).then(function(response) {
			$scope.Id = response.data.Id;
			$scope.Title = response.data.Title;
			$scope.Year = response.data.Year;
			$scope.memberList = response.data.MemberList;
			$scope.SPlanDate = response.data.Sdate;
			$scope.EPlanDate = response.data.Edate;
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
	// Switch to Edit(Update) Mode End
	// Switch to Edit(Update) Mode Start
	$scope.viewData = function(id) {
		$scope.openEdit();
		$scope.btnIns = false;
		$scope.btnUpd = false;
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		
		$http.post('./api/k00/query/id', request, csrf_config).then(function(response) {
			$scope.Id = response.data.Id;
			$scope.Title = response.data.Title;
			$scope.Year = response.data.Year;
			$scope.memberList = response.data.MemberList;
			$scope.SPlanDate = response.data.Sdate;
			$scope.EPlanDate = response.data.Edate;
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
	// Switch to Edit(Update) Mode End
	$scope.createData = function(exit,check) {
		
		if (check){
			$scope.Status = "1";
			$scope.IsWriteProcessLog = true	
		}
		else{
			$scope.Status = "0";				
			$scope.IsWriteProcessLog = false
		}									
		var request = {							
			Title : $scope.Title,
			Year : $scope.Year,														
			Sdate : $scope.SPlanDate,
			Edate : $scope.EPlanDate,				
			Member : {
					"orgs":$scope.orgs, 
					"levels":$scope.levels,
					"citys":$scope.citys,
					"isOrgPubs":$scope.isOrgPubs,
					"memberList":$scope.memberList,
					"groupList":$scope.groupList},							
			Status : $scope.Status,
			PreStatus : "0",
			TableName : "maintainPlanMemberInspect",							
			IsWriteProcessLog : $scope.IsWriteProcessLog
		   	
		};
			
		$http.post('./api/k00/create', request, csrf_config).then(					
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
														$scope.queryData($scope.currentPage);
														if (exit)
															{																																						
															$scope.closeEdit();
															}
														else
															{														
															$scope.btnIns = false;
															$scope.btnUpd = true;
															$scope.PreStatus = "0"
															$scope.Id = response.data.Id
															}
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
							});								
		};
		
		$scope.updateData = function(exit,check) {	
			if (check){
				$scope.Status = "1";
				$scope.IsWriteProcessLog = true	
			}
			else{
				$scope.Status = "0";				
				$scope.IsWriteProcessLog = false
			}									
			var request = {	
						Id : $scope.Id,
						Title : $scope.Title,
						Year : $scope.Year,														
						Sdate : $scope.SPlanDate,
						Edate : $scope.EPlanDate,				
						Member : {
							"orgs":$scope.orgs, 
							"levels":$scope.levels,
							"citys":$scope.citys,
							"isOrgPubs":$scope.isOrgPubs,
							"memberList":$scope.memberList,
							"groupList":$scope.groupList},						
						Status : $scope.Status,
						PreStatus : "0",
						TableName : "maintainPlanMemberInspect",						
						IsWriteProcessLog : $scope.IsWriteProcessLog
			   	
				};
				
			$http.post('./api/k00/update', request, csrf_config).then(					
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
															if (exit)
																{
															$scope.queryData($scope.currentPage);														
															$scope.closeEdit();
																}
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
								}).finally(function() { });
		}
		
		$scope.addMember = function() {
			$scope.memberList.push({
				Members : $scope.members,
				MemberId : null
			})
		};
		$scope.deleteMember = function(i) {
	        $scope.memberList.splice(i, 1);
	    }
		//memberList
		$scope.Member = "";
		var memberNum = 1;			
		angular.forEach($scope.memberList, function(member) {					
			angular.forEach(member.Members, function(memberId){					
				if (member.MemberId == memberId.Id){						
					$scope.Member = $scope.Member + memberNum + "." + memberId.Name + " "						
					memberNum++;						
				}				
			});
		});
		$scope.back = function() {		
			$scope.closeEdit();
		};
		$scope.setSortName = function(sorttype) {
			$scope.sortreverse = (sorttype !== null && $scope.sorttype === sorttype) ? !$scope.sortreverse
						: false;
			$scope.sorttype = sorttype;
			$scope.currentPage = 1;
			$scope.start = 0;
			$scope.queryData();
		};	
}