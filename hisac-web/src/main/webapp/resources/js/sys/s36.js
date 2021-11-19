var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {
	$('input[id="EndDateTime"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    }); 
	$('input[id="StartDateTime"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
    });

		$("#StartDateTime").on("dp.change", function(e) {	       
			$scope.StartDateTime = $('#StartDateTime').val();
			if ($scope.StartDateTime > $scope.EndDateTime && $scope.EndDateTime != null) {
				$scope.EndDateTime = $scope.StartDateTime;
				$('#EndDateTime').val($scope.EndDateTime)
			}
		});
		$("#EndDateTime").on("dp.change", function(e) {	       
			$scope.EndDateTime = $('#EndDateTime').val();
			if ($scope.EndDateTime < $scope.StartDateTime && $scope.StartDateTime != null) {
				$scope.StartDateTime = $scope.EndDateTime;
				$('#StartDateTime').val($scope.StartDateTime)
			}
		});
		
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "id";
	$scope.sortreverse = true;

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
		$scope.QueryNewsManagementGroupId = null;
		$scope.QueryPostDateTime = null;
		$scope.QueryTitle = null;
		$scope.QueryIsEnable = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
//	
//
//	$scope.clearData = function() {
//		$scope.QueryId=null;
//		$scope.QueryTitle=null;
//		$scope.QueryIsEnable=null;
//	}
//	$scope.clearData();
	
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
		if ($scope.QueryTitle == "")
			$scope.QueryTitle = null;
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			Title : $scope.QueryTitle,
			IsEnable : $scope.QueryIsEnable,
		};
		//console.log("request="+JSON.stringify(request));
		$http.post('./api/s36/query', request, csrf_config).then(function(response) {
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
		$scope.Title = "";
		$scope.Content="";
		$scope.StartDateTime = "";
		$scope.EndDateTime = "";
		$scope.IsEnable = false;
		
		$scope.FileImage = null; 
		$scope.ImageDescription = "";
		
		$scope.FileAttachment = null; 
		$scope.AttachmentDescription = "";
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
		var request = {Id:id};
		$http.post('./api/s36/query/id', request, csrf_config).then(function(response) {
			//console.log("Id="+response.data[0].Id);
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.Title = response.data[0].Title;
			$scope.Content = response.data[0].Content;
			$scope.IsBreakLine = response.data[0].IsBreakLine;
			$scope.StartDateTime = response.data[0].StartDateTime;
			$scope.EndDateTime = response.data[0].EndDateTime;
			$scope.IsEnable = response.data[0].IsEnable;
			       
			$scope.queryImageData($scope.Id);
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
				Title : $scope.Title,
				Content : $scope.Content,
				IsBreakLine : $scope.IsBreakLine,
				StartDateTime : $scope.StartDateTime,
				EndDateTime : $scope.EndDateTime,
				IsEnable : $scope.IsEnable
			};

		$http.post('./api/s36/create', request, csrf_config).then(
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
			Title : $scope.Title,
			Content : $scope.Content,
			IsBreakLine : $scope.IsBreakLine,
			StartDateTime : $scope.StartDateTime,
			EndDateTime : $scope.EndDateTime,
			IsEnable : $scope.IsEnable
		};
		$http.post('./api/s36/update', request, csrf_config).then(
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
					$http.post('./api/s36/delete', request, csrf_config).then(function(response) {
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
	
		
	// Get Images Start
	$scope.queryImageData = function(id) {
		var request = {
				CommonPostId : id
		};
		$http.post('./api/s36/pic/query', request, csrf_config).then(function(response) {
			$scope.itemImages = response.data.datatable;
		}).catch(function() { }).finally(function() { });
	};
	// Get Images End
	
	// Delete Image Start
	$scope.deleteImageData = function(id) {
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
						CommonPostId : $scope.Id
					};
					$http.post('./api/s36/pic/delete', request, csrf_config).then(function(response) {
						if (response.data.success) {
							$scope.queryImageData($scope.Id);
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
	// Delete Image End
	
	// Upload Image Start
	$scope.uploadImage = function() {
		if ($scope.FileImage == null 
			|| ( $scope.FileImage.name.substr($scope.FileImage.name.lastIndexOf('.') + 1) != "jpg" 
				&& $scope.FileImage.name.substr($scope.FileImage.name.lastIndexOf('.') + 1) != "gif"
				&& $scope.FileImage.name.substr($scope.FileImage.name.lastIndexOf('.') + 1) != "png"
			)) {
			bootbox.alert({
				message : globalAcceptImageFormat,
				buttons : {
					ok : {
						label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
						className : 'btn-danger',
					}
				},
				callback: function() { }
			});
		} else {
			if ( $scope.ImageDescription == null )
				$scope.ImageDescription = ""; 
			Upload.upload({
					url : './api/s36/pic/upload',
					data : {
						file : $scope.FileImage,
						id : $scope.Id,
						FileDesc : $scope.ImageDescription
					},
					headers: header
				})
				.then(
					function(response) {
						
						//console.log("response="+JSON.stringify(response.data));
						
						if (response.data.success) {
							$scope.queryImageData($scope.Id);
							$scope.FileImage = null; 
							$scope.ImageDescription = "";
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
	// Upload Image End
	
	// Get Attachments Start
	$scope.queryAttachmentData = function(id) {
		var request = {
				CommonPostId : id
		};
		$http.post('./api/s36/attach/query', request, csrf_config).then(function(response) {
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
						CommonPostId : $scope.Id
					};
					//console.log("request=" + JSON.stringify(request));
					$http.post('./api/s36/attach/delete', request, csrf_config).then(function(response) {
						//console.log("request=" + JSON.stringify(response.data.datatable));
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
					url : './api/s36/attach/upload',
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
}