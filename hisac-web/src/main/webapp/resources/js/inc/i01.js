var myApp = angular.module('myApp', [ 'ngCookies', 'bw.paging', 'ngFileUpload', 'ui.toggle', 'ngSanitize' ]).controller(
		'getAppController', getAppController);

myApp.filter('trustHtml',function($sce){
    return function(input){
    	if (input) {
    		return $sce.trustAsHtml(input.replace(/\r\n/g, '<br />').replace(/\r/g, '<br />').replace(/\n/g, '<br />'));
    	}
    };
});

function getAppController($rootScope, $scope, $http, $cookieStore, $cookies, Upload) {
	
	$scope.queryNumber = function() {
        $("#loadingActivity").fadeIn("slow");

		var request = {
			count_topname : true,
			count_p_id : true,
			count_paper_SerialNumber : true,
			paper_corId : "1"
		};
		$http.post('./common/queryNumber', request, csrf_config).then(function(response) {
			
			
			$("#peopleNum").text(response.data.peopleNum);
			$("#paperNum").text(response.data.paperNum);
			$("#paperCorNum").text(response.data.paperCorNum);
			$("#snaTopNum").text(response.data.snaTopNum);		
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
            $("#loadingActivity").fadeOut("slow");

		});
	};
	$scope.queryNumber();

	// Paging Start
	$scope.start = 0;
	$scope.currentPage = 1;
	$scope.maxRows = 5;
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
		$scope.QueryTitle = null;
		$scope.QueryDate = null;
		$scope.QueryIsEnable=null;
		$scope.QueryIsShow=null;
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
		if ($scope.QueryTitle == "")
			$scope.QueryTitle = null;	
		if ($scope.QueryDate == "")
			$scope.QueryDate = null;		
		if ($scope.QueryIsEnable == "")
			$scope.QueryIsEnable = null;
		if ($scope.QueryIsShow == "")
			$scope.QueryIsShow = null;
		
		var quertDate = null;
		const date = $scope.QueryDate;
		if($scope.QueryDate != null) {
			date.setHours(date.getHours() - date.getTimezoneOffset()/60);
			quertDate = date.toISOString();
		}
		
		var request = {
			start : $scope.start,
			maxRows : $scope.maxRows,
			sort : $scope.sorttype,
			dir : $scope.sortreverse,
			Id : $scope.QueryId,
			Title : $scope.QueryTitle,
			Date : quertDate,
			IsEnable : $scope.QueryIsEnable,
			IsShow : $scope.QueryIsShow
		};

		$http.post('./common/i01/query', request, csrf_config).then(function(response) {
			$scope.allitems = response.data.datatable;
			$scope.total = response.data.total;
			$scope.maxPages = parseInt($scope.total / $scope.maxRows);
			$scope.pageRows = $scope.total % $scope.maxRows;
			//console.log("_________"+JSON.stringify($scope.allitems))
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
		$("#div1").show();
		$scope.btnIns = true;
		$scope.btnUpd = false;
		$scope.Id = 0;
	    $scope.Title = "";
	    $scope.Description = "";
	    $scope.Author = "";
	    $scope.Tag = "";
	    $scope.Sort = null;
	    editor.txt.html("");
	    $("#img_upload_show_article").attr("src",null);
	    $("#img_upload_base_article").val(null);;
	    $("#img_upload_file_article").val(null);
	   	$scope.IsEnable = true;
	   	$scope.IsShow = true;
	}
	// Switch to Edit(Insert) Mode End
	
	// Switch to Query Mode Start
	$scope.closeEdit = function() {
		$("#divEdit").hide();
		$("#divQuery").show();
		$scope.btnIns = false;
		$scope.btnUpd = false;
		$scope.queryData($scope.currentPage);
	}
	// Switch to Query Mode End
	
	// Switch to Edit(Update) Mode Start
	$scope.editData = function(id) {
		
		var request = {
			Id : id
		};

		$http.post('./common/i01/query/id', request, csrf_config).then(function(response) {
			$scope.openEdit();
			$scope.btnIns = false;
			$scope.btnUpd = true;
			$scope.Id = response.data[0].Id;
			$scope.Title = response.data[0].Title;
			$scope.Description = response.data[0].Description;
			$scope.Author = response.data[0].Author;
			$scope.Tag = response.data[0].Tag;
			$scope.Sort = response.data[0].Sort;
			editor.txt.html(response.data[0].Content);
			$("#img_upload_show_article").attr("src",response.data[0].Img);
			$scope.IsEnable = response.data[0].IsEnable;
			$scope.IsShow = response.data[0].IsShow;
		}).catch(function() {
			
		}).finally(function() { });
	}
	// Switch to Edit(Update) Mode End
	
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
					$http.post('./common/i01/delete', request, csrf_config).then(function(response) {
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
	
	// Insert Item Start
	$scope.createData = function(status) {
		
		$("#div1").hide();
		
		var request = {
			Id : $scope.Id,
			Title : $scope.Title,
			Description : $scope.Description,
			Author : $scope.Author,
			Tag : $scope.Tag,
			Sort : $scope.Sort,
			Content : editor.txt.html(),
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow,
			Img : $("#img_upload_base_article").val(),
			Status : status
		};
		
		$http.post('./common/i01/create', request, csrf_config).then(function(response) {
			if (response.data.success) {
				if(status == 1) {
					$scope.queryData($scope.currentPage);
				}
				bootbox.alert({
					message : response.data.msg,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-success',
						}
					},
					callback: function() {
						if(status == 0) {
							$scope.Id = response.data.Id;
							$("#div1").show();
						}else {
							$scope.closeEdit();
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
	// Insert Data End
	
	// Insert Item Start
	$scope.createAndClose = function(status) {
		
		$("#div1").hide();
		
		var request = {
			Id : $scope.Id,
			Title : $scope.Title,
			Description : $scope.Description,
			Author : $scope.Author,
			Tag : $scope.Tag,
			Sort : $scope.Sort,
			Content : editor.txt.html(),
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow,
			Img : $("#img_upload_base_article").val(),
			Status : status
		};
		
		$http.post('./common/i01/create', request, csrf_config).then(function(response) {
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
	$scope.updateData = function(status) {
		
		$("#div1").hide();
		
		var request = {
			Id : $scope.Id,
			Title : $scope.Title,
			Description : $scope.Description,
			Author : $scope.Author,
			Tag : $scope.Tag,
			Sort : $scope.Sort,
			Content : editor.txt.html(),
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow,
			Img : $("#img_upload_base_article").val(),
			Status : status
		};
		$http.post('./common/i01/update', request, csrf_config).then(function(response) {
			if (response.data.success) {
				if(status == 1) {
					$scope.queryData($scope.currentPage);
				}
				bootbox.alert({
					message : response.data.msg,
					buttons : {
						ok : {
							label : '<i class="fas fa-fw fa-times"></i>' + btnClose,
							className : 'btn-success',
						}
					},
					callback: function() {
						if(status == 0) {
							$scope.Id = response.data.Id;
							$("#div1").show();
						}else {
							$scope.closeEdit();
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
	
	// Update Data Start
	$scope.updateAndClose = function(status) {
		
		$("#div1").hide();
		
		var request = {
			Id : $scope.Id,
			Title : $scope.Title,
			Description : $scope.Description,
			Author : $scope.Author,
			Tag : $scope.Tag,
			Sort : $scope.Sort,
			Content : editor.txt.html(),
			IsEnable : $scope.IsEnable,
			IsShow : $scope.IsShow,
			Img : $("#img_upload_base_article").val(),
			Status : status
		};
		$http.post('./common/i01/update', request, csrf_config).then(function(response) {
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
	
	const E = window.wangEditor;
	const editor = new E("#div1");
	editor.config.lang = "en";
	editor.i18next = window.i18next;
	editor.config.height = 500
	
	//不需要的功能
    editor.config.excludeMenus = [
    	'strikeThrough','head','backColor','fontName',
        'link','todo','indent','quote',
  		'code','splitLine'
    ]
	//全屏功能顯示
	editor.config.showFullScreen = false
	//上傳檔名
	editor.config.uploadFileName = 'file';
	//base64上傳圖片
	editor.config.uploadImgShowBase64 = true
		
	//editor.config.uploadImgMaxSize = 2 * 1024 * 1024
    
	editor.create();
	
	$("#img_upload_file_article").change(function() {		
        var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);//呼叫自帶方法進行轉換
        reader.onload = function(e) {
        $("#img_upload_show_article").attr("src", this.result);//將轉換後的編碼存入src完成預覽
        $("#img_upload_base_article").val(this.result);//將轉換後的編碼儲存到input供後臺使用
        }; 
    });
}