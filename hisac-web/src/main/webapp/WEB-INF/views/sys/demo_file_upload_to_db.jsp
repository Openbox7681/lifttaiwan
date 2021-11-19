<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/demo_file_upload_to_db.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-globe home-icon"></i> GeoIP</li>
					<li class="active">{{pageHeader}}</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-container-col ui-sortable">
							<div id="js-collapsible" class="widget-box transparent ui-sortable-handle">
								<div class="widget-header">
									<h2 id="actionTitle" class="widget-title lighter">GeoIP資料匯入</h2>
								</div>
							</div>
						</div>
						<div class="box-header with-border">
							<div class="space-4"></div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-4">
								<div class="thumbnail">
									<div class="caption">
										<h3>城市</h3>
										<p>最後更新時間 : {{CityFileMtimestamp}}</p>
										<form ng-disable="btnUpd">
											<div style="display: inline-block" ngf-select="" ng-model="file1" name="file1" ngf-pattern="'.mmdb'" accept=".mmdb" ngf-max-size="300MB" ngf-min-height="100" id="file1">
												<button id="select1" class="btn btn-sm btn-info" ng-click="file1_mmdb=false">
													<i class="ace-icon fa fa-file fa-2x"></i> 選擇檔案
												</button>
											</div>
											<button type="submit" id="upload1" ng-click="submit('city')" class="btn btn-sm btn-success">
												<i class="ace-icon fa fa-cloud-upload fa-2x"></i> 上傳
											</button>
											<div>{{file1.name}}</div>
											<div class="validation-msg" ng-show="file1_mmdb">檔名必須為GeoIP2-City.mmdb</div>
											<label class="imgLoading"> <img id="imgLoading1" src="./../resources/img/loading3.gif" alt="upload file... please wait" title="upload file... please wait" style="display: none;"></label>
										</form>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="thumbnail">
									<div class="caption">
										<h3>Domain</h3>
										<p>最後更新時間 : {{DomainFileMtimestamp}}</p>
										<form ng-disable="btnUpd">
											<div style="display: inline-block" ngf-select="" ng-model="file2" name="file2" ngf-pattern="'.mmdb'" accept=".mmdb" ngf-max-size="300MB" ngf-min-height="100" id="file2">
												<button id="select2" class="btn btn-sm btn-info" ng-click="file2_mmdb=false">
													<i class="ace-icon fa fa-file fa-2x"></i> 選擇檔案
												</button>
											</div>
											<button type="submit" id="upload2" ng-click="submit('domain')" class="btn btn-sm btn-success">
												<i class="ace-icon fa fa-cloud-upload fa-2x"></i> 上傳
											</button>
											<div>{{file2.name}}</div>
											<div class="validation-msg" ng-show="file2_mmdb">檔名必須為GeoIP2-Domain.mmdb</div>
											<label class="imgLoading"> <img id="imgLoading2" src="./../resources/img/loading3.gif" alt="upload file... please wait" title="upload file... please wait" style="display: none;"></label>
										</form>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="thumbnail">
									<div class="caption">
										<h3>ISP</h3>
										<p>最後更新時間 : {{IspFileMtimestamp}}</p>
										<form ng-disable="btnUpd">
											<div style="display: inline-block" ngf-select="" ng-model="file3" name="file3" ngf-pattern="'.mmdb'" accept=".mmdb" ngf-max-size="300MB" ngf-min-height="100" id="file3">
												<button id="select3" class="btn btn-sm btn-info" ng-click="file3_mmdb=false">
													<i class="ace-icon fa fa-file fa-2x"></i> 選擇檔案
												</button>
											</div>
											<button type="submit" id="upload3" ng-click="submit('isp')" class="btn btn-sm btn-success">
												<i class="ace-icon fa fa-cloud-upload fa-2x"></i> 上傳
											</button>
											<div>{{file3.name}}</div>
											<div class="validation-msg" ng-show="file3_mmdb">檔名必須為GeoIP2-ISP.mmdb</div>
											<label class="imgLoading"> <img id="imgLoading3" src="./../resources/img/loading3.gif" alt="upload file... please wait" title="upload file... please wait" style="display: none;"></label>
										</form>
									</div>
								</div>
							</div>
						
						
						
						
						
						
						                   <div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">Summary.xlsx</label>
												<div class="col-sm-9">
													<label ng-show="data_IsSummaryFileContent" class="col-sm-12 control-label no-padding-right text-left"><a href="./api/mail_transfer_case_query/download/summaryfile/{{data_Id}}" >下載檔案</a></label>
												</div>
											</div>
											
											
											
											
						
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>