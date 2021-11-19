var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function(){
    return function(input){
    	if (input) {
    		input = input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />');    		
    		return input.split('<br />')
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {

	// 流程紀錄用
	$("#divReview").hide();
	
	// Paging Start
	$scope.RuleIsOpen = true
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
			Status : $scope.QueryStatus
		};

		$http.post('./api/s42/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			if ($scope.pageRows != 0)
				$scope.maxPages++;
			$scope.returnTotal = true;
			
			// 統計各狀態目前筆數
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
	
	// 瀏覽及簽核用
	$scope.review = function(id, Action) {	
		
		
		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});
		
		$scope.Id = null;		
		$scope.PostDateTime = null;
		$scope.TitleEdit = null;
		$scope.SourceNameEdit = null;
		$scope.SourceLinkEdit = null;		
		$scope.ContentEdit = null;
		$scope.Title = null;
		$scope.SourceName = null;
		$scope.SourceLink = null;		
		$scope.Content = null;
		$scope.Remark = null;
		$scope.IsBreakLine = false;	
		$scope.IsEnable = false;	
		$scope.Status = null;
		$scope.CreateId = 0;
		$scope.CreateTime = null;
		$scope.Name = null;
		$scope.Email = null;
		
		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.Action = Action;	
		
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

	}

	$scope.edit = function(id) {

		bootbox.dialog({
			closeButton: false,
			message : '<i class="fas fa-fw fa-circle-notch fa-spin"></i>' + dataLoading 
		});

		$scope.Id = null;		
		$scope.PostDateTime = null;
		$scope.TitleEdit = null;
		$scope.SourceNameEdit = null;
		$scope.SourceLinkEdit = null;		
		$scope.ContentEdit = null;
		$scope.Title = null;
		$scope.SourceName = null;
		$scope.SourceLink = null;		
		$scope.Content = null;
		$scope.Remark = null;
		$scope.IsBreakLine = false;		
		$scope.IsEnable = false;		
		$scope.Status = null;
		$scope.CreateId = 0;
		$scope.CreateTime = null;
		$scope.Name = null;
		$scope.Email = null;
		
		// 簽核紀錄資料
		$scope.MessageProcessLogData = [];
		
		$scope.editData(id);

		$scope.btnIns = false;
		$scope.btnUpd = true;
		$("#divQuery").hide();

	}
	
	// 簽核用
	$scope.examine = function(id, status) {
		var action = ""
		var preStatus = ""
		switch(status){
			case '6':
				action = "退回";
				preStatus = "3"
			break;
			case '2':
				action = "撤銷";
				preStatus = "3"
			break;
			case '4':
				action = "通過";
				preStatus = "3"
			break;
			case '5':
				action = "撤銷";
				preStatus = "2"
			break;
		}
		
		bootbox.confirm(
			"確定要" + action + "此資訊分享嗎？",
			function(result) {
				if (result) {
					
					var request = {
						Id : id, 
						Status : status,
						PreStatus : preStatus,
						TableName : "information_management",
						Pre : "HISAC",
						Opinion : $scope.Opinion,
						Title : $scope.Title,
						SourceName : $scope.SourceName,
						SourceLink : $scope.SourceLink,
						Content : $scope.Content,
						Remark : $scope.Remark,
						IsBreakLine : $scope.IsBreakLine,				
						IsEnable : $scope.IsEnable,	
						
					};
					
					$http
						.post('./api/s42/examine', request, csrf_config)
						.then(
							function(response) {
								if (response.data.success) {
									bootbox.dialog({
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
												"callback" : function() { }
											}
										}
									});
								}
							}).catch(function() {
								bootbox.dialog({
									message : "<span class='bigger-110'>資訊分享" + action + "失敗</span>",
									buttons : {
										"success" : {
											"label" : "<i class='ace-icon fa fa-close'></i> 關閉",
											"className": 'btn-danger',
											"callback" : function() { }
										}
									}
								});
							}).finally(function() { });
				}
			});
	};
	
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
		        	$http.post('./api/s42/disable', request, csrf_config)
					.then(function() {
						$scope.queryData($scope.currentPage);
					}).finally(function() {
						bootbox.hideAll();;
					});
		        }
		    }
		});
	}
	
	
	$scope.enable = function(enableId) {
		bootbox.confirm({
		    message: "確定要啟用嗎?",
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
		        	var request = { Id: enableId };
		        	$http.post('./api/s42/enable', request, csrf_config)
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
		$scope.PostDateTime = moment().format("YYYY-MM-DD");
		$scope.TitleEdit = "";
		$scope.SourceNameEdit = "";
		$scope.SourceLinkEdit = "";		
		$scope.ContentEdit = "";	
		$scope.Title = "";	
		$scope.SourceName = "";	
		$scope.SourceLink = "";	
		$scope.Content = "";	
		$scope.Remark ="";	
		$scope.IsBreakLine = true;		
		$scope.IsEnable = false;	
		$scope.Status = "";
		$scope.CreateId = 0;
		$scope.CreateTime = null;
		$scope.Name = null;
		$scope.Email = null;
		
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
			TableName : "information_management"
		};
		
		$http.post('./api/s42/query/id', request, csrf_config).then(function(response) {
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
			$scope.PostDateTime = response.data[0].PostDateTime;
			$scope.TitleEdit = response.data[0].TitleEdit;
			$scope.SourceNameEdit = response.data[0].SourceNameEdit;
			$scope.SourceLinkEdit = response.data[0].SourceLinkEdit;			
			$scope.ContentEdit = response.data[0].ContentEdit;	
			$scope.Title = response.data[0].Title;
			$scope.SourceName = response.data[0].SourceName;
			$scope.SourceLink = response.data[0].SourceLink;			
			$scope.Content = response.data[0].Content;
			$scope.Remark = response.data[0].Remark;
			$scope.IsBreakLine = response.data[0].IsBreakLine;			
			$scope.IsEnable = response.data[0].IsEnable;		
			$scope.Status = response.data[0].Status;
			$scope.CreateId = response.data[0].CreateId;
			$scope.CreateTime = response.data[0].CreateTime;
			$scope.Name = response.data[0].Name;
			$scope.Email = response.data[0].Email;
			
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
	
	$scope.createData = function(exit, check) {
	
		// 簽核用
		if (check) {
			$scope.Status = "3";
		} else {
			$scope.Status = "1";
		}
		
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
			
			if ($scope.Status == "3") {
				$scope.IsWriteProcessLog = true;
			}
			// 流程紀錄用 - 結束										

			var request = {
				Id : null,				
				PostDateTime : $scope.PostDateTime,
				TitleEdit : $scope.TitleEdit,
				SourceNameEdit : $scope.SourceNameEdit,
				SourceLinkEdit : $scope.SourceLinkEdit,				
				ContentEdit : $scope.ContentEdit,				
				IsBreakLine : $scope.IsBreakLine,				
				IsEnable : $scope.IsEnable,				
				Status : $scope.Status,
				
				PreStatus : "1",
				TableName : "information_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
			};
			
			$http.post('./api/s42/create', request, csrf_config).then(
				function(response) {
					if (response.data.success) {
						
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
								// 暫存並離開按鍵用 - 開始
								if ($scope.pageRows == 1 && $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}

								$scope.queryData($scope.currentPage);
								
								if (exit) {	
									$scope.closeEdit();
								} else {
									$scope.btnIns = false;
									$scope.btnUpd = true;
									$scope.PreStatus = "1"
									$scope.Id = response.data.Id
								}
								// 暫存並離開按鍵用 - 結束
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
		
		}
	};
	// Insert Data End

	// Update Data Start
	// 暫存：updateData(false, false)
	// 暫存並離開：updateData(true, false)
	// 送出：updateData(true, true)
	$scope.updateData = function(exit,check) {	

		// 簽核用
		if (check) {
			$scope.Status = "3";
		}
		
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
			
			if ($scope.Status == "3") {
				$scope.IsWriteProcessLog = true;
			}
			// 流程紀錄用 - 結束
				
			
			var request = {
				Id : $scope.Id,				
				PostDateTime : $scope.PostDateTime,
				TitleEdit : $scope.TitleEdit,
				SourceNameEdit : $scope.SourceNameEdit,
				SourceLinkEdit : $scope.SourceLinkEdit,			
				ContentEdit : $scope.ContentEdit,				
				IsBreakLine : $scope.IsBreakLine,				
				IsEnable : $scope.IsEnable,			
				Status : $scope.Status,
				
				PreStatus : $scope.PreStatus,
				TableName : "information_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
			};
			$http.post('./api/s42/update', request, csrf_config).then(
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
								// 暫存並離開按鍵用 - 開始
								if ($scope.pageRows == 1 && $scope.currentPage > 1) {
									$scope.currentPage = $scope.currentPage - 1;
								}
									
								if (exit) {
									$scope.queryData($scope.currentPage);
									$scope.closeEdit();
								}
								// 暫存並離開按鍵用 - 結束
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
						$http.post('./api/s42/delete', request, csrf_config).then(function(response) {
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
				InformationManagementId : id
			};
			$http.post('./api/s42/pic/query', request, csrf_config).then(function(response) {
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
							NewsManagementId : $scope.Id
						};
						$http.post('./api/s42/pic/delete', request, csrf_config).then(function(response) {
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
						url : './api/s42/pic/upload',
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
				InformationManagementId : id
			};
			$http.post('./api/s42/attach/query', request, csrf_config).then(function(response) {
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
							NewsManagementId : $scope.Id
						};
						$http.post('./api/s42/attach/delete', request, csrf_config).then(function(response) {
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
						url : './api/s42/attach/upload',
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

		
		// Get Status Count Start
		$scope.queryButtonCount = function() {
						
			if ($scope.QueryPostDateTime == "")
				$scope.QueryPostDateTime = null;
			if ($scope.QueryTitle == "")
				$scope.QueryTitle = null;
			if ($scope.QueryIsEnable == "")
				$scope.QueryIsEnable = null;			
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
				Status : $scope.QueryStatus
			};

			$http.post('./api/s42/query/button/count', request, csrf_config).then(function(response) {
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
		$scope.queryButtonCount();
		// Get Status Count End
		
		//openOrCloseRule
		$scope.changeRuleIsOpen = function() {	
			$scope.RuleIsOpen = !$scope.RuleIsOpen
		}
		
		
		// Show Status Count Start
		$scope.getCount = function(Status){
			var total = 0;
			angular.forEach($scope.button_count_allitems, function(value, key) {
				if(value.Status==Status ) {
					total=value.Count;
				}
			});

			return total;
	    }
		// Show Status Count End
		
		
		
		$scope.getPageButtonCount = function(){
			
			$scope.ButtonCount1 = $scope.getCount(1);
			$scope.ButtonCount2 = $scope.getCount(2);
			$scope.ButtonCount3 = $scope.getCount(3);
			$scope.ButtonCount6 = $scope.getCount(6);
			
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
							$("#form_news_management").html(count);
							subsystem_sys_count = subsystem_sys_count + count;
						} else {
							$("#form_news_management")
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

//				if ($("#form_weakness_management").length > 0) {
//					$.ajax({
//						url : "../pub/api/count/weakness_management",
//						method : "POST",
//						data : request,
//						headers : header,
//						datatype : "json",
//						async : false
//					})
//					.done(function(response) {
//						var count = response.count;
//						if (count > 0) {
//							$("#form_weakness_management").html(count);
//							subsystem_sys_count = subsystem_sys_count + count;
//						} else {
//							$("#form_weakness_management")
//							.html('');
//						}
//					});
//				}
				
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