var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
			return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />').replace(/\.\/api\/p02\/pic\/download\//g, './api/s32/pic/download/'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {

	// 流程紀錄用
	$("#divReview").hide();
	
	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 10;
	$scope.maxPages = 0;
	$scope.total = 0;
	$scope.sorttype = "postDateTime";
	$scope.sortreverse = true;
	$scope.btnIns = false;
	$scope.btnUpd = false;
	
	// 顯示流程簽核紀錄畫面
	$scope.IsSeeLog = true;
	// 顯示簽核畫面
	$scope.IsSeeOpinion = false;

	// 在狀態按鍵名稱後顯示目前筆數
	$scope.ButtonCount1=0;
	$scope.ButtonCount2=0;
	$scope.ButtonCount3=0;
	
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
	
	// 狀態階段按鍵即時查詢用
	$scope.setStatus = function(status) {
		 $scope.QueryStatus = status;
		 $scope.queryData();
	}
	
	// Clear Query Data Start
	$scope.clearData = function() {
		$scope.QueryPostDateTime = null;
		$scope.QueryTitle = null;
		$scope.QueryIsEnable = null;
		$scope.QueryIsPublic = null;
		$scope.QueryStatus = null;
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
		if ($scope.QueryIsPublic == "")
			$scope.QueryIsPublic = null;
		if ($scope.QueryStatus == "")
			$scope.QueryStatus = null;
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			PostDateTime : $scope.QueryPostDateTime,
			Title : $scope.QueryTitle,
			IsEnable : $scope.QueryIsEnable,
			IsPublic : $scope.QueryIsPublic,
			Status : $scope.QueryStatus
		};

		$http.post('./api/s32/query', request, csrf_config).then(function(response) {
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
	
	// 瀏覽及簽核用
	$scope.review = function(id, Action) {	
		
// console.log("review() → id=" + id + "；Action=" + Action);
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		$scope.Id = null;
		$scope.PostId = null;
		$scope.PostDateTime = null;
		$scope.Title = null;
		$scope.SourceName = null;
		$scope.SourceLink = null;
		$scope.ContentType = null;
		$scope.Content = null;
		$scope.ExternalLink = null;
		$scope.IsBreakLine = false;
		$scope.StartDateTime = null;			
		$scope.EndDateTime = null;
		$scope.IsEnable = false;
		$scope.IsPublic = false;
		$scope.Status = null;
		$scope.Sort = null;
		
		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.Action = Action;
		
// console.log("review() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" +
// $scope.btnUpd);
		
		switch(Action) {
			
			case "view": // 瀏覽頁面
				$("#divReview").show();
				$scope.IsSeeOpinion = false; // 顯示簽核畫面
				$scope.messagePassButton = false; // 是否顯示通過按鍵
				$scope.messageBackButton = false; // 是否顯示退回按鍵
				$scope.messageUndoButtonButton = false; // 是否顯示撤銷按鍵
			break;
			
			case "messageReview": // 審核頁面
				$("#divReview").show();
				$scope.IsSeeOpinion = true; // 顯示簽核畫面
				$scope.messagePassButton = true; // 是否顯示通過按鍵
				$scope.messageBackButton = true; // 是否顯示退回按鍵
				$scope.messageUndoButtonButton = true; // 是否顯示撤銷按鍵
			break;
			
		}

		$scope.editData(id);
		
// console.log("review() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd=" +
// $scope.btnUpd);

	}

	$scope.edit = function(id) {

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});

		$scope.Id = null;
		$scope.PostId = null;
		$scope.PostDateTime = null;
		$scope.Title = null;
		$scope.SourceName = null;
		$scope.SourceLink = null;
		$scope.ContentType = null;
		$scope.Content = null;
		$scope.ExternalLink = null;
		$scope.IsBreakLine = false;
		$scope.StartDateTime = null;			
		$scope.EndDateTime = null;
		$scope.IsEnable = false;
		$scope.IsPublic = false;
		$scope.Status = null;
		$scope.Sort = null;
		
		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.editData(id);

		$scope.btnIns = false;
		$scope.btnUpd = true;
		$("#divQuery").hide();

	}
	
	$scope.disable = function(deleteId) {
		bootbox.confirm({
		    message: "確定要停用嗎?",
		    buttons: {
		        confirm: {
		            label: '是',
		            className: 'btn-success'
		        },
		        cancel: {
		            label: '否',
		            className: 'btn-danger'
		        }
		    },
		    callback: function (result) {
		        if (result) {
		        	var request = { Id: deleteId };
		        	$http.post('./api/s32/disable', request, csrf_config)
					.then(function() {
						$scope.queryData($scope.currentPage);
					}).finally(function() {
						bootbox.hideAll();;
					});
		        }
		    }
		});
	}
	
	// Switch to Edit(Insert) Mode Start
	// 新增、編輯、瀏覽用
	$scope.openEdit = function() {
		$("#divEdit").show();
		$("#divQuery").hide();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = null;
		$scope.PostId = "";
		$scope.PostType = "2";
		$scope.PostDateTime = moment().format("YYYY-MM-DD");
		$scope.Title = "";
		$scope.SourceName = "";
		$scope.SourceLink = "";
		$scope.ContentType = "";
		$scope.Content = "";
		$scope.ExternalLink = "";
		$scope.IsBreakLine = true;
		$scope.StartDateTime = "";
		$scope.EndDateTime = "";
		$scope.IsEnable = false;
		$scope.IsPublic = false;
		$scope.Status = "";
		$scope.Sort = 0;
		
		// 簽核用
		$scope.PreStatus = "";
		$scope.Opinion = "";
		
		// 上傳圖檔用
		$scope.FileImage = null; 
		$scope.ImageDescription = "";
		$scope.itemImages = null;
		
		// 上傳附件用
		$scope.FileAttachment = null; 
		$scope.AttachmentDescription = "";
		$scope.itemAttachments = null;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divQuery").show();
		$("#divEdit").hide();
		$("#divReview").hide();
		$scope.btnIns = false;
		$scope.btnUpd = false;
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	// 編輯、瀏覽用
	$scope.editData = function(id) {
		
// console.log("editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd="
// + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
		
		var request = {
			Id : id,
			TableName : "activity_management"
		};
		
		$http.post('./api/s32/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			
			switch ($scope.Action) {
				case "view": // 瀏覽
				case "messageReview": // 審核
					$scope.Action = null;
					$scope.btnIns = false;
					$scope.btnUpd = false;
				break;
				
				default:
					$scope.btnIns = false;
					$scope.btnUpd = true;
					$("#divReview").hide();
				break;
			
			}

// console.log("editData() → $scope.btnIns=" + $scope.btnIns + "；$scope.btnUpd="
// + $scope.btnUpd + "；$scope.Action = " + $scope.Action);
		
			$scope.Id = response.data[0].Id;
			$scope.PostId = response.data[0].PostId;
			$scope.PostType = response.data[0].PostType;
			$scope.PostDateTime = response.data[0].PostDateTime;
			$scope.Title = response.data[0].Title;
			$scope.SourceName = response.data[0].SourceName;
			$scope.SourceLink = response.data[0].SourceLink;
			$scope.ContentType = response.data[0].ContentType;
			$scope.Content = response.data[0].Content;
			$scope.ExternalLink = response.data[0].ExternalLink;
			$scope.IsBreakLine = response.data[0].IsBreakLine;
			$scope.StartDateTime = response.data[0].StartDateTime;
			$scope.EndDateTime = response.data[0].EndDateTime;
			$scope.IsEnable = response.data[0].IsEnable;
			$scope.IsPublic = response.data[0].IsPublic;
			$scope.Status = response.data[0].Status;
			$scope.Sort = response.data[0].Sort;
			
			// 流程紀錄用
			$scope.PreStatus = response.data[0].Status;
			$scope.MessageProcessLogData = response.data[0].MessageLog;

			// 圖檔清單資料
			$scope.queryImageData($scope.Id);
			
			// 附件清單資料
			$scope.queryAttachmentData($scope.Id);
			
			bootbox.hideAll();
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
		}).finally(function() {
			bootbox.hideAll();
		});
	}
	// Switch to Edit(Update) Mode End

	// Update Data Start
	// 暫存：updateData(false, false)
	// 暫存並離開：updateData(true, false)
	// 送出：updateData(true, true)
	$scope.updateData = function(exit,check) {
		if (!$scope.editForm.$valid && check) {
			
			bootbox.dialog({
				message : "<span class='bigger-110'>請修正不符合規定的欄位</span>",
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i> 確定",
						"callback" : function() {
						}
					}
				}
			});
		
		} else {
		
			// 流程紀錄用 - 開始
			$scope.IsWriteProcessLog = false
			// 流程紀錄用 - 結束
				
			if ($scope.StartDateTime == "")
				$scope.StartDateTime = null;
			if ($scope.EndDateTime == "")
				$scope.EndDateTime = null;
			
			switch($scope.ContentType) {
				case "1":
					$scope.ExternalLink = "";
					break;
				case "2":
					$scope.Content = "";
					$scope.IsBreakLine = false;
					break;
			}
			
			var request = {
				Id : $scope.Id,
				PostType : "2",
				PostDateTime : $scope.PostDateTime,
				Title : $scope.Title,
				SourceName : $scope.SourceName,
				SourceLink : $scope.SourceLink,
				ContentType : $scope.ContentType,
				Content : $scope.Content,
				ExternalLink : $scope.ExternalLink,
				IsBreakLine : $scope.IsBreakLine,
				StartDateTime : $scope.StartDateTime,
				EndDateTime : $scope.EndDateTime,
				IsEnable : $scope.IsEnable,
				IsPublic : $scope.IsPublic,
				Status : $scope.Status,
				Sort : $scope.Sort,
				
				PreStatus : $scope.PreStatus,
				TableName : "activity_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
			};
			$http.post('./api/s32/modify', request, csrf_config).then(
				function(response) {
					if (response.data.success) {
						
						$scope.PostId = response.data.PostId;
						
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
									className : 'btn-success',
								}
							},
							callback: function() {
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
				
			}
		
		};
		// Update Data End
		
		// Delete Data Start
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
						$http.post('./api/s32/delete', request, csrf_config).then(function(response) {
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
		// Delete Data End
		
		// Get Images Start
		$scope.queryImageData = function(id) {
			var request = {
				ActivityManagementId : id
			};
			$http.post('./api/s32/pic/query', request, csrf_config).then(function(response) {
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
							ActivityManagementId : $scope.Id
						};
						$http.post('./api/s32/pic/delete', request, csrf_config).then(function(response) {
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
						url : './api/s32/pic/upload',
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
		
		// Get Attachments Start
		$scope.queryAttachmentData = function(id) {
			var request = {
				ActivityManagementId : id
			};
			$http.post('./api/s32/attach/query', request, csrf_config).then(function(response) {
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
							ActivityManagementId : $scope.Id
						};
						$http.post('./api/s32/attach/delete', request, csrf_config).then(function(response) {
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
						url : './api/s32/attach/upload',
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