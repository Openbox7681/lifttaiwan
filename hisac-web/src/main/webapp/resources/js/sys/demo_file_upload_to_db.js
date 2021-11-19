var myApp = angular.module('myApp', [ 'ngCookies', 'ngFileUpload' ])
		.controller('getAppController', getAppController);

angular.element(document).ready(function() {
	angular.element(window).keydown(function() {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

function getAppController($rootScope, $scope, $http, $filter, $rootScope,
		$cookieStore, Upload) {
	$rootScope.pageHeader = "GeoIP資料匯入";
	$scope.geoip_active = true;
	$scope.geoip_import_active = true;
	$scope.user_id = $cookieStore.get('user_id');
	$scope.func_role = $cookieStore.get('func_role');

	$scope.btnSel = $scope.func_role.geoip_import.Sel;
	$scope.btnIns = $scope.func_role.geoip_import.Ins;
	$scope.btnUpd = $scope.func_role.geoip_import.Upd;
	$scope.btnDel = $scope.func_role.geoip_import.Del;

	var data = {};
	$http.post('./api/demo_file_upload_to_db/query/1', data, csrf_config).success(function(data) {
		$scope.CityFileMtimestamp = data.CityFileMtimestamp;
		$scope.DomainFileMtimestamp = data.DomainFileMtimestamp;
		$scope.IspFileMtimestamp = data.IspFileMtimestamp;
	});

	$scope.submit = function(type) {
		if (type == "city") {
			$("#imgLoading1").show();
			document.getElementById("select1").disabled = true;
			document.getElementById("file1").disabled = true;
			document.getElementById("upload1").disabled = true;
			$scope.file1_mmdb = false;
			$scope.upload_file($scope.file1, type);
		} else if (type == "domain") {
			$("#imgLoading2").show();
			document.getElementById("select2").disabled = true;
			document.getElementById("file2").disabled = true;
			document.getElementById("upload2").disabled = true;
			$scope.file2_mmdb = false;
			$scope.upload_file($scope.file2, type);
		} else if (type == "isp") {
			$("#imgLoading3").show();
			document.getElementById("select3").disabled = true;
			document.getElementById("file3").disabled = true;
			document.getElementById("upload3").disabled = true;
			$scope.file3_mmdb = false;
			$scope.upload_file($scope.file3, type);
		}
	};

	$scope.upload_file = function(file, type) {
		if (file == null
				|| file.name.substr(file.name.lastIndexOf('.') + 1) != "mmdb"
				|| (type == "city" && file.name.substr(0, file.name.lastIndexOf('.')) != "GeoIP2-City")
				|| (type == "domain" && file.name.substr(0, file.name.lastIndexOf('.')) != "GeoIP2-Domain")
				|| (type == "isp" && file.name.substr(0, file.name.lastIndexOf('.')) != "GeoIP2-ISP")) {
			if (type == "city") {
				$scope.file1_mmdb = true;
				bootbox.dialog({
					message : "<span class='bigger-110'>檔名必須為GeoIP2-City.mmdb</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 關閉",
							"callback" : function() {
							}
						}
					}
				});
			} else if (type == "domain") {
				$scope.file2_mmdb = true;
				bootbox.dialog({
					message : "<span class='bigger-110'>檔名必須為GeoIP2-Domain.mmdb</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 關閉",
							"callback" : function() {
							}
						}
					}
				});
			} else if (type == "isp") {
				$scope.file3_mmdb = true;
				bootbox.dialog({
					message : "<span class='bigger-110'>檔名必須為GeoIP2-ISP.mmdb</span>",
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i> 關閉",
							"callback" : function() {
							}
						}
					}
				});
			}
			if (type == "city") {
				$("#imgLoading1").hide();
				document.getElementById("select1").disabled = false;
				document.getElementById("file1").disabled = false;
				document.getElementById("upload1").disabled = false;
			} else if (type == "domain") {
				$("#imgLoading2").hide();
				document.getElementById("select2").disabled = false;
				document.getElementById("file2").disabled = false;
				document.getElementById("upload2").disabled = false;
			} else if (type == "isp") {
				$("#imgLoading3").hide();
				document.getElementById("select3").disabled = false;
				document.getElementById("file3").disabled = false;
				document.getElementById("upload3").disabled = false;
			}
		} else {
			Upload
					.upload({
						url : './api/demo_file_upload_to_db/uploadFile',
						data : {
							file : file,
							type : type
						},
						headers: header
					})
					.then(
							function(resp) {
								if (type == "city") {
									$scope.file1 = null;
									$("#imgLoading1").hide();
									document.getElementById("select1").disabled = false;
									document.getElementById("file1").disabled = false;
									document.getElementById("upload1").disabled = false;
								} else if (type == "domain") {
									$scope.file2 = null;
									$("#imgLoading2").hide();
									document.getElementById("select2").disabled = false;
									document.getElementById("file2").disabled = false;
									document.getElementById("upload2").disabled = false;
								} else if (type == "isp") {
									$scope.file3 = null;
									$("#imgLoading3").hide();
									document.getElementById("select3").disabled = false;
									document.getElementById("file3").disabled = false;
									document.getElementById("upload3").disabled = false;
								}
								if (resp.data.success) {
									$scope.CityFileMtimestamp = resp.data.CityFileMtimestamp;
									$scope.DomainFileMtimestamp = resp.data.DomainFileMtimestamp;
									$scope.IspFileMtimestamp = resp.data.IspFileMtimestamp;
									var showName = "";
									if (type == "city")
										showName = "城市";
									else if (type == "domain")
										showName = "Domain";
									else if (type == "isp")
										showName = "ISP";
									bootbox
											.dialog({
												message : "<span class='bigger-110'>Geoip資料匯入 "
														+ showName
														+ " 資料成功</span>",
												buttons : {
													"success" : {
														"label" : "<i class='ace-icon fa fa-check'></i> 確定",
														"callback" : function() {
														}
													}
												}
											});
								} else {
									bootbox
											.dialog({
												message : "<span class='bigger-110'>"
														+ resp.data.msg
														+ "</span>",
												buttons : {
													"success" : {
														"label" : "<i class='ace-icon fa fa-check'></i> 關閉",
														"className" : 'btn-warning',
														"callback" : function() {
														}
													}
												}
											});
								}
							});
		}
	};
}