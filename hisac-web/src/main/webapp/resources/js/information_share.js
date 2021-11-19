var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, Upload) {
	
	// Paging Start	
	$scope.RuleIsOpen = true
	$scope.btnUpd = false;
	$scope.Name = null,
	$scope.Email = null,
	$scope.TitleEdit = null
	$scope.SourceNameEdit  = null
	$scope.SourceLinkEdit  = null				
	$scope.ContentEdit  = null	
	$scope.Id = null
	$scope.itemAttachments = null
	$scope.itemImages = null
	$scope.ImageDescription = null
	$scope.AttachmentDescription = null
	$scope.FileImage = null
	$scope.FileAttachment = null
	$scope.Type = "0"
	// Paging End
	
	
	$scope.createData = function() {
	
		// 簽核用		
		$scope.Status = "1";		
			
			// 流程紀錄用 - 開始
			$scope.IsWriteProcessLog = false					
			// 流程紀錄用 - 結束										

			var request = {
				Name : $scope.Name,
				Email : $scope.Email,
				TitleEdit : $scope.TitleEdit,
				SourceNameEdit : $scope.SourceNameEdit,
				SourceLinkEdit : $scope.SourceLinkEdit,				
				ContentEdit : $scope.ContentEdit,								
				Status : $scope.Status,
				Type : $scope.Type,
				PreStatus : "1",
				TableName : "information_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
			};
			
			$http.post('./public/api/information_share/create', request, csrf_config2).then(
				function(response) {
					if (response.data.success) {		
						$scope.btnUpd = true;								
						$scope.Id = response.data.Id			
						
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
									label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
								label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
								className : 'btn-danger',
							}
						},
						callback: function() {}
					});
				}).finally(function() { });						
	};
	// Insert Data End

	// Update Data Start	
	$scope.updateData = function() {	

		// 簽核用		
		$scope.Status = "3";		
		
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
			$scope.IsWriteProcessLog = true;			
			// 流程紀錄用 - 結束
				
			
			var request = {
				Id : $scope.Id,
				Name : $scope.Name,
				Email : $scope.Email,
				TitleEdit : $scope.TitleEdit,
				SourceNameEdit : $scope.SourceNameEdit,
				SourceLinkEdit : $scope.SourceLinkEdit,				
				ContentEdit : $scope.ContentEdit,								
				Status : $scope.Status,
				Type : $scope.Type,
				PreStatus : "1",
				TableName : "information_management",
				Pre : "HISAC",
				IsWriteProcessLog : $scope.IsWriteProcessLog
				
			};
			$http.post('./public/api/information_share/update', request, csrf_config2).then(
				function(response) {
					if (response.data.success) {	
						// 離開按鍵用 - 開始							
						$scope.btnUpd = false;
						$scope.Name = null,
						$scope.Email = null,
						$scope.TitleEdit = null
						$scope.SourceNameEdit  = null
						$scope.SourceLinkEdit  = null				
						$scope.ContentEdit  = null	
						$scope.Id = null
						$scope.itemAttachments = null
						$scope.itemImages = null
						$scope.ImageDescription = null
						$scope.AttachmentDescription = null
						$scope.FileImage = null
						$scope.FileAttachment = null
						$scope.Type = "0"
						// 離開按鍵用 - 結束				
							
						bootbox.alert({
							message : response.data.msg,
							buttons : {
								ok : {
									label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
									label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
								label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
								className : 'btn-danger',
							}
						},
						callback: function() { }
					});
				}).finally(function() { });
				
			}
		
		};
		// Update Data End	

		// Get Images Start
		$scope.queryImageData = function(id) {
			var request = {
				InformationManagementId : id
			};
			$http.post('./public/api/information_share/pic/query', request, csrf_config2).then(function(response) {
				$scope.itemImages = response.data.datatable;
			}).catch(function() { }).finally(function() { });
		};
		// Get Images End
		
		// Delete Image Start
		$scope.deleteImageData = function(id) {			
			bootbox.confirm({
				message: '確定要刪除此資料嗎？',
				buttons: {
					confirm: {
						label : '<i class="fas fa-fw fa-check"></i>' + '確定',
						className : 'btn-success'
					},
					cancel: {
						label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
						className : 'btn-default'
					}
				},
				callback: function(result) {
					if (result) {
						var request = {
							Id : id,
							NewsManagementId : $scope.Id
						};
						$http.post('./public/api/information_share/pic/delete', request, csrf_config2).then(function(response) {
							if (response.data.success) {
								$scope.queryImageData($scope.Id);
								bootbox.alert({
									message : response.data.msg,
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
										label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
							label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
							className : 'btn-danger',
						}
					},
					callback: function() { }
				});
			} else {
				if ( $scope.ImageDescription == null )
					$scope.ImageDescription = ""; 
				Upload.upload({
						url : './public/api/information_share/pic/upload',
						data : {
							file : $scope.FileImage,
							id : $scope.Id,
							FileDesc : $scope.ImageDescription,
							Type : $scope.Type
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
			$http.post('./public/api/information_share/attach/query', request, csrf_config2).then(function(response) {
				$scope.itemAttachments = response.data.datatable;
			}).catch(function() { }).finally(function() { });
		};
		// Get Attachments End
		
		// Delete Attachment Start
		$scope.deleteAttachmentData = function(id) {
			bootbox.confirm({
				message: '確定要刪除此資料嗎？',
				buttons: {
					confirm: {
						label : '<i class="fas fa-fw fa-check"></i>' + '確定',
						className : 'btn-success'
					},
					cancel: {
						label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
						className : 'btn-default'
					}
				},
				callback: function(result) {
					if (result) {
						var request = {
							Id : id,
							NewsManagementId : $scope.Id
						};
						$http.post('./public/api/information_share/attach/delete', request, csrf_config2).then(function(response) {
							if (response.data.success) {
								$scope.queryAttachmentData($scope.Id);
								bootbox.alert({
									message : response.data.msg,
									buttons : {
										ok : {
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
										label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
							label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
							className : 'btn-danger',
						}
					},
					callback: function() { }
				});
			} else {
				if ( $scope.AttachmentDescription == null )
					$scope.AttachmentDescription = ""; 
				Upload.upload({
						url : './public/api/information_share/attach/upload',
						data : {
							file : $scope.FileAttachment,
							id : $scope.Id,
							FileDesc : $scope.AttachmentDescription,
							Type : $scope.Type
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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
											label : '<i class="fas fa-fw fa-times"></i>' + '關閉',
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

		//openOrCloseRule
		$scope.changeRuleIsOpen = function() {	
			$scope.RuleIsOpen = !$scope.RuleIsOpen
		}
		
		
}


$(document).ready(function() {
	$("#btnLogin").hide();
	$("#btnIndex").show();
	$("#btnLogin2").hide();
	$("#btnIndex2").show();
});

function toggleLogin() {
	location.href = "./";
}