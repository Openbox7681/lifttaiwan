var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ui.toggle' ]).controller(
		'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});
function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location) {
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "createTime";
	$scope.sortreverse = false;
	
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
	
	// Query Data Start
	$scope.queryData = function(page) {
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
			dir : $scope.sortreverse
		};

		$http.post('./api/s15/query', request, csrf_config).then(function(response) {
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
	// Query Data End
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnSign = false;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
		$scope.btnSign = false;
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		var request = {
			Id : id
		};
		$http.post('./api/s15/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnSign = true;
			$scope.Id = response.data[0].Id;
			$scope.OrgId = response.data[0].OrgId;
			$scope.OrgName = response.data[0].OrgName;
			$scope.OrgSuperviseName = response.data[0].OrgSuperviseName;
			$scope.OrgLocalName = response.data[0].OrgLocalName;
			$scope.Account = response.data[0].Account;
			$scope.Name = response.data[0].Name;
			$scope.Email = response.data[0].Email;
			$scope.MobilePhone = response.data[0].MobilePhone;
			$scope.CreateTime = response.data[0].CreateTime;
			bootbox.hideAll();
		}).catch(function() {
			bootbox.hideAll();
			$scope.closeEdit();
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
	
	// Sign Data End
	$scope.signData = function() {
		var request = {
			Id : $scope.Id
		};
		$http.post('./api/s15/update', request, csrf_config).then(function(response) {
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
					callback: function() { }
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
						className : 'btn-danger'
					}
				},
				callback: function() { }
			});
		}).finally(function() {
			$scope.closeEdit();
		});
	}
	// Sign Data End
	
	$scope.queryButtonCount = function(){
		var request = {"a" : Math.random().toString(36).substring(2,15)	};
		
		if ($("#subsystem_sys").length > 0) {
			var subsystem_sys_count = 0;
			
			if ($("#form_sign_apply").length > 0) {
				$.ajax(
								{
									url : "../pub/api/count/member_sign",
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
												"#form_sign_apply")
												.html(count);
										subsystem_sys_count = subsystem_sys_count
												+ count;
									} else {
										$("#form_sign_apply")
										.html('');
									}
								});
					
			}
			
			if ($("#form_news_management").length > 0) {
				$.ajax({
					url : "../pub/api/count/news_management",
					method : "POST",
					data : request,
					headers : header,
					datatype : "json",
					async : false
				})
				.done(function(response) {
					var count = response.count;
					if (count > 0) {
						$("#form_activity_management").html(count);
						subsystem_sys_count = subsystem_sys_count + count;
					} else {
						$("#form_activity_management")
						.html('');
					}
				});
			}

			if ($("#form_activity_management").length > 0) {
				$.ajax({
					url : "../pub/api/count/activity_management",
					method : "POST",
					data : request,
					headers : header,
					datatype : "json",
					async : false
				})
				.done(function(response) {
					var count = response.count;
					if (count > 0) {
						$("#form_activity_management").html(count);
						subsystem_sys_count = subsystem_sys_count + count;
					} else {
						$("#form_activity_management")
						.html('');
					}
				});
			}

			if ($("#form_ana_management").length > 0) {
				$.ajax({
					url : "../pub/api/count/ana_management",
					method : "POST",
					data : request,
					headers : header,
					datatype : "json",
					async : false
				})
				.done(function(response) {
					var count = response.count;
					if (count > 0) {
						$("#form_ana_management").html(count);
						subsystem_sys_count = subsystem_sys_count + count;
					} else {
						$("#form_ana_management")
						.html('');
					}
				});
			}

			if ($("#form_weakness_management").length > 0) {
				$.ajax({
					url : "../pub/api/count/weakness_management",
					method : "POST",
					data : request,
					headers : header,
					datatype : "json",
					async : false
				})
				.done(function(response) {
					var count = response.count;
					if (count > 0) {
						$("#form_weakness_management").html(count);
						subsystem_sys_count = subsystem_sys_count + count;
					} else {
						$("#form_weakness_management")
						.html('');
					}
				});
			}

			if ($("#form_information_management").length > 0) {
				$.ajax({
					url : "../pub/api/count/information_management",
					method : "POST",
					data : request,
					headers : header,
					datatype : "json",
					async : false
				})
				.done(function(response) {
					var count = response.count;
					if (count > 0) {
						$("#form_information_management").html(count);
						subsystem_sys_count = subsystem_sys_count + count;
					} else {
						$("#form_information_management")
						.html('');
					}
				});
			}
			
			if (subsystem_sys_count > 0) {
				$("#subsystem_sys > .badge").html(
						subsystem_sys_count);
			} else {
				$("#subsystem_sys > .badge")
				.html('');
			}
		}
	
	}
}