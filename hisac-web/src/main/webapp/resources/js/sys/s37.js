var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ui.toggle', 'ngFileUpload']).controller(
		'getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});


function getAppController($rootScope, $scope, $http, $cookieStore, $anchorScroll, $location, Upload) {
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
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
	
	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.QueryId = null;
		$scope.QueryQName=null;
		$scope.QueryAName=null;
		$scope.QueryQAMgId=null;
		$scope.QueryIsEnable = null;
		$scope.QueryIsPublic = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
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
		if ($scope.QueryId == "")
			$scope.QueryId = null;
		if ($scope.QueryQName == "")
			$scope.QueryQName = null;
		if ($scope.QueryAName == "")
			$scope.QueryAName = null;
		if ($scope.QueryQAMgId == "")
			$scope.QueryQAMgId = null;
		
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryIsPublic == "")
			$scope.QueryIsPublic = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			QName : $scope.QueryQName,
			AName : $scope.QueryAName,
			QAManagementGroupId : $scope.QueryQAMgId,
			IsEnable : $scope.QueryIsEnable,
			IsPublic : $scope.QueryIsPublic
			
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/s37/query', request, csrf_config).then(function(response) {
			
			//console.log("datatable="+JSON.stringify(response.data.datatable));
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
	$scope.queryData();
    // Query Data End
	
	// Switch to Edit(Insert) Mode Start
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = null;
		$scope.QName = "";
		$scope.AName = "";
		$scope.QAManagementGroupId =null;
		$scope.IsEnable = true;
		$scope.IsPublic = true;
		
		// 上傳附件用
		$scope.FileAttachment = null; 
		$scope.AttachmentDescription = "";
		$scope.itemAttachments = null;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
		$scope.btnIns = false;
		$scope.btnUpd = false;
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
		$http.post('./api/s37/query/id', request, csrf_config).then(function(response) {
			//console.log("Id="+response.data[0].Id);
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.QName = response.data[0].QName;
			$scope.AName = response.data[0].AName;
			$scope.QAMgId = response.data[0].QAManagementGroupId;
			$scope.IsEnable= response.data[0].IsEnable;
			$scope.IsPublic = response.data[0].IsPublic;
			// 附件清單資料
			$scope.queryAttachmentData($scope.Id);
			
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
	
	// Insert Item Start
	$scope.createData = function() {
			var request = {
				Id : $scope.Id,
				QName : $scope.QName,
				AName : $scope.AName,
				QAManagementGroupId : $scope.QAMgId,
				IsEnable : $scope.IsEnable,
				IsPublic : $scope.IsPublic
			};
			$http.post('./api/s37/create', request, csrf_config).then(
					function(response) {
						if (response.data.success) {
							$scope.queryData($scope.currentPage);
							$scope.btnIns = false;
							$scope.btnUpd = true;									
							$scope.Id = response.data.Id							
							bootbox.alert({
								message : response.data.msg,
								buttons : {
									ok : {
										label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
										className : 'btn-success',
									}
								},
								callback: function() {										
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
	// Insert Data End
	
	// Update Data Start
	$scope.updateData = function() {
		var request = {
			Id : $scope.Id,
			QName : $scope.QName,
			AName : $scope.AName,
			QAManagementGroupId : $scope.QAMgId,
			IsEnable : $scope.IsEnable,
			IsPublic : $scope.IsPublic
			
		};
		$http.post('./api/s37/update', request, csrf_config).then(
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
			// Update Data End
			

			// Delete Item Start
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
							$http.post('./api/s37/delete', request, csrf_config).then(function(response) {
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
			// Delete Item End
	
			
			// Get Attachments Start
			$scope.queryAttachmentData = function(id) {
				var request = {
					QAManagementId : id
				};
				$http.post('./api/s37/attach/query', request, csrf_config).then(function(response) {
					$scope.itemAttachments = response.data.datatable;
				}).catch(function() { }).finally(function() { });
			};
			// Get Attachments End
			
			// Delete Attachment Start
			$scope.deleteAttachmentData = function(id) {
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
								Id : id,
								QAManagementId : $scope.Id
							};
							$http.post('./api/s37/attach/delete', request, csrf_config).then(function(response) {
								if (response.data.success) {
									$scope.queryAttachmentData($scope.Id);
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
			// Delete Attachment End
			
			// Upload Attachment Start
			$scope.uploadAttachment = function() {
				if ($scope.FileAttachment == null 
					|| ( $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "jpg" 
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "gif"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "doc"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "docx"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "xls"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "xlsx" 
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "ppt"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "pptx"
						&& $scope.FileAttachment.name.substr($scope.FileAttachment.name.lastIndexOf('.') + 1) != "pdf"
					)) {
					bootbox.alert({
						message : globalAcceptAttachmentFormat,
						buttons : {
							ok : {
								label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
								className : 'btn-danger',
							}
						},
						callback: function() { }
					});
				} else {
					if ( $scope.AttachmentDescription == null )
						$scope.AttachmentDescription = ""; 
					Upload.upload({
							url : './api/s37/attach/upload',
							data : {
								file : $scope.FileAttachment,
								id : $scope.Id,
								FileDesc : $scope.AttachmentDescription
							},
							headers: header
						})
						.then(
							function(response) {
								if (response.data.success) {
									$scope.queryAttachmentData($scope.Id);
									$scope.FileAttachment = null; 
									$scope.AttachmentDescription = "";
									bootbox.alert({
										message : response.data.msg,
										buttons : {
											ok : {
												label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
												className : 'btn-success',
											}
										},
										callback: function() {
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
							});
				}
			};
			// Upload Attachment End
	
	$scope.getQAManagementGroup = function() {
		var request = {
				start : "0",
				maxRows : "0",
				sort : "id",
				dir : "false",
				Id : "0",
				Name : "",
				IsEnable : "true",
				IsPublic : "true",
			};
		
		$http.post('./api/s37/group/query', request, csrf_config).then(function(response) {
			$scope.qaManagementGroups = response.data.datatable;
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
			//$("#imgLoading").hide();
		});
	};
	$scope.getQAManagementGroup();
	
	
	
}