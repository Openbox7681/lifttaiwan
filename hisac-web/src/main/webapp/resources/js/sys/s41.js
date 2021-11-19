var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle' ]).controller(
		'getAppController', getAppController);

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "postDateTime";
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
		$scope.QueryPostDateTime = null;
		$scope.QueryTitle = null;
		$scope.QueryIsEnable = null;
		$scope.btnIns = false;
		$scope.btnUpd = false;
	};
	$scope.clearData();
	// Clear Query Data End
	
	$('input[name="QueryPostDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#QueryPostDateTime").on("dp.change", function(e) {	       
		$scope.QueryPostDateTime = $('#QueryPostDateTime').val();
	});
	
	$('input[name="PostDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#PostDateTime").on("dp.change", function(e) {	       
		$scope.PostDateTime = $('#PostDateTime').val();
	});
	
	$('input[name="StartDateTime"]').datetimepicker({		
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
	
	$('input[name="EndDateTime"]').datetimepicker({		
		format: 'YYYY-MM-DD',
		locale: 'zh-TW'
	});
	$("#EndDateTime").on("dp.change", function(e) {	       
		$scope.EndDateTime = $('#EndDateTime').val();
		if ($scope.EndDateTime < $scope.StartDateTime && $scope.StartDateTime != null) {
			$scope.StartDateTime = $scope.EndDateTime;
			$('#StartDateTime').val($scope.StartDateTime)
		}
	});
	
	// Query Data Start
	$scope.queryData = function(page) {
		$("#imgLoading").show();
		if (page) {
			$scope.start = (page - 1) * $scope.maxRows
		} else {
			$scope.start = 0;
		}
		if ($scope.QueryPostDateTime == "")
			$scope.QueryPostDateTime = null;
		if ($scope.QueryTitle == "")
			$scope.QueryTitle = null;
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			PostDateTime : $scope.QueryPostDateTime,
			Title : $scope.QueryTitle,
			IsEnable : $scope.QueryIsEnable
		};

		$http.post('./api/s41/query', request, csrf_config).then(function(response) {
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
		//$scope.PostType = "1";
		$scope.PostDateTime = moment().format("YYYY-MM-DD");
		$scope.Title = "";
		$scope.SourceName = "";
		$scope.SourceLink = "";
		//$scope.ContentType = "";
		//$scope.Content = "";
		//$scope.ExternalLink = "";
		$scope.IsBreakLine = false;
		$scope.StartDateTime = "";
		$scope.EndDateTime = "";
		$scope.IsEnable = false;
		
		$scope.FileImage = null; 
		$scope.ImageDescription = "";
		
		//$scope.FileAttachment = null; 
		//$scope.AttachmentDescription = "";
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
		
		$http.post('./api/s41/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.PostDateTime = response.data[0].PostDateTime;
			$scope.Title = response.data[0].Title;
			$scope.SourceName = response.data[0].SourceName;
			$scope.SourceLink = response.data[0].SourceLink;
			//$scope.ContentType = response.data[0].ContentType;
			//$scope.Content = response.data[0].Content;
			//$scope.ExternalLink = response.data[0].ExternalLink;
			$scope.IsBreakLine = response.data[0].IsBreakLine;
			$scope.StartDateTime = response.data[0].StartDateTime;
			$scope.EndDateTime = response.data[0].EndDateTime;
			$scope.IsEnable = response.data[0].IsEnable;
			$scope.queryImageData($scope.Id);
			//$scope.queryAttachmentData($scope.Id);
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
/*
		switch($scope.ContentType) {
			case "1":
				$scope.ExternalLink = "";
				break;
			case "2":
				$scope.Content = "";
				$scope.IsBreakLine = false;
				break;
		}
*/
		if ($scope.StartDateTime == "")
			$scope.StartDateTime = null;
		if ($scope.EndDateTime == "")
			$scope.EndDateTime = null;
		
		var request = {
			Id : null,
			//PostType : "1",
			PostDateTime : $scope.PostDateTime,
			Title : $scope.Title,
			SourceName : $scope.SourceName,
			SourceLink : $scope.SourceLink,
			//ContentType : $scope.ContentType,
			//Content : $scope.Content,
			//ExternalLink : $scope.ExternalLink,
			IsBreakLine : $scope.IsBreakLine,
			StartDateTime : $scope.StartDateTime,
			EndDateTime : $scope.EndDateTime,
			IsEnable : $scope.IsEnable
		};
		
		$http.post('./api/s41/create', request, csrf_config).then(
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

		if ($scope.StartDateTime == "")
			$scope.StartDateTime = null;
		if ($scope.EndDateTime == "")
			$scope.EndDateTime = null;
/*
		switch($scope.ContentType) {
			case "1":
				$scope.ExternalLink = "";
				break;
			case "2":
				$scope.Content = "";
				$scope.IsBreakLine = false;
				break;
		}
*/
		var request = {
			Id : $scope.Id,
			//PostType : "1",
			PostDateTime : $scope.PostDateTime,
			Title : $scope.Title,
			SourceName : $scope.SourceName,
			SourceLink : $scope.SourceLink,
			//ContentType : $scope.ContentType,
			//Content : $scope.Content,
			//ExternalLink : $scope.ExternalLink,
			IsBreakLine : $scope.IsBreakLine,
			StartDateTime : $scope.StartDateTime,
			EndDateTime : $scope.EndDateTime,
			IsEnable : $scope.IsEnable
		};
		$http.post('./api/s41/update', request, csrf_config).then(
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
						$http.post('./api/s41/delete', request, csrf_config).then(function(response) {
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
				LinksId : id
			};
			$http.post('./api/s41/pic/query', request, csrf_config).then(function(response) {
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
							LinksId : $scope.Id 
						};
						$http.post('./api/s41/pic/delete', request, csrf_config).then(function(response) {
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
						url : './api/s41/pic/upload',
						data : {
							file : $scope.FileImage,
							id : $scope.Id,
							FileDesc : $scope.ImageDescription
						},
						headers: header
					})
					.then(
						function(response) {
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
		
}