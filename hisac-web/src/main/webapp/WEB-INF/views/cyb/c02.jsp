<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c02.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-user home-icon"></i> 資安資訊</li>
					<li>API管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-container-col ui-sortable">
							<div id="js-collapsible" class="widget-box transparent ui-sortable-handle">
								
							<div class="page-header">
							<h1>API列表</h1>
						    </div>	
							<table class="table table-striped table-bordered table-hover dataTable">
							<thead>
								<tr>
									<th width="10%"> <span>Id</span></th>
									<th width="25%"> <span>API名稱</span></th>
									
								</tr>
							</thead>
							<tbody>
								<tr >
									<td>1</td>
									<td><a href="#my-modal"  data-toggle="modal">N-ISAC (轉入) 開發中...</a></td>
								</tr>
								<tr >
									<td>2</td>
									<td><a href="#my-modal"  data-toggle="modal">N-ISAC (轉出) 開發中...</a></td>
								</tr>
								<tr >
									<td>3</td>
									<td><a href="#my-modal" ng-click="openedit()" data-toggle="modal">探網tkfc (由探網直接新增資料進入系統)</a></td>
									
								</tr>
								<tr >
									<td>4</td>
									<td><a href="#my-modal" ng-click="insertData()" >探網tkfc (json 測試)</a></td>
									
								</tr>
								<tr >
									<td>5</td>
									<td><a href="#my-modal" ng-click="uploadData()" >探網tkfc (file upload json 測試)</a></td>
									
								</tr>
								<tr >
									<td>6</td>
									<td><a href="../open/api/tkfc_isac/attach/download/64366535/4" target="_blank">探網tkfc (Download file 測試)</a></td>
									
								</tr>
							</tbody>
						</table>
						
						
						
						<div id="my-modal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
										<h3 class="smaller lighter blue no-margin">情資資料新增/修改</h3>
									</div>
									
									<div class="modal-body">
										<form class="form-horizontal" role="form" name="myForm"
											novalidate>
											<div class="space-4"></div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="StixTitle">
													情報種類
												</label>
												<div class="col-sm-8">
													<input type="text" id="StixTitle" name="StixTitle" ng-model="StixTitle" 
													class="col-xs-12 col-sm-6" />
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="IncidentId">
													發送編號
												</label>
												<div class="col-sm-8">
													<input type="text" id="IncidentId" name="IncidentId" ng-model="IncidentId" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="IncidentTitle">
													事件主旨/情報名稱
												</label>
												<div class="col-sm-8">
													<input type="text" id="IncidentTitle" name="IncidentTitle" ng-model="IncidentTitle" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="Description">
													內容說明/事件描述
												</label>
												<div class="col-sm-8">
													<input type="text" id="Description" name="Description" ng-model="Description" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="Category">
													事件類型
												</label>
												<div class="col-sm-8">
													<input type="text" id="Category" name="Category" ng-model="Category" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ReporterName">
													發布單位
												</label>
												<div class="col-sm-8">
													<input type="text" id="ReporterName" name="ReporterName" ng-model="ReporterName" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResponderPartyName">
													聯絡資訊 (姓名)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResponderPartyName" name="ResponderPartyName" ng-model="ResponderPartyName" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResponderContactNumbers">
													聯絡資訊 (電話)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResponderContactNumbers" name="ResponderContactNumbers" ng-model="ResponderContactNumbers" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResponderElectronicAddressIdentifiers">
													聯絡資訊 (電子郵件)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResponderElectronicAddressIdentifiers" name="ResponderElectronicAddressIdentifiers" ng-model="ResponderElectronicAddressIdentifiers" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ImpactQualification">
													影響等級
												</label>
												<div class="col-sm-8">
													<input type="text" id="ImpactQualification" name="ImpactQualification" ng-model="ImpactQualification" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="CoaDescription">
													建議措施/解決辦法
												</label>
												<div class="col-sm-8">
													<input type="text" id="CoaDescription" name="CoaDescription" ng-model="CoaDescription" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="Confidence">
													保密程度
												</label>
												<div class="col-sm-8">
													<input type="text" id="Confidence" name="Confidence" ng-model="Confidence" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="Reference">
													參考資料
												</label>
												<div class="col-sm-8">
													<input type="text" id="Reference" name="Reference" ng-model="Reference" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ObservableAttach">
													附件
												</label>
												<div class="col-sm-8">
													<input type="text" id="ObservableAttach" name="ObservableAttach" ng-model="ObservableAttach" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ObservableIpAddress">
													受害IP
												</label>
												<div class="col-sm-8">
													<input type="text" id="ObservableIpAddress" name="ObservableIpAddress" ng-model="ObservableIpAddress" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="SocketIpAddress">
													C&C資訊(IP)
												</label>
												<div class="col-sm-8">
													<input type="text" id="SocketIpAddress" name="SocketIpAddress" ng-model="SocketIpAddress" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="SocketPort">
													C&C資訊(通訊埠)
												</label>
												<div class="col-sm-8">
													<input type="text" id="SocketPort" name="SocketPort" ng-model="SocketPort" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="SocketProtocol">
													C&C資訊(通訊埠協定)
												</label>
												<div class="col-sm-8">
													<input type="text" id="SocketProtocol" name="SocketProtocol" ng-model="SocketProtocol" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="CustomIpAddress">
													中繼站資訊(IP)
												</label>
												<div class="col-sm-8">
													<input type="text" id="CustomIpAddress" name="CustomIpAddress" ng-model="CustomIpAddress" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="CustomPort">
													中繼站資訊(通訊埠)
												</label>
												<div class="col-sm-8">
													<input type="text" id="CustomPort" name="CustomPort" ng-model="CustomPort" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="CustomProtocol">
													中繼站資訊(通訊埠協定)
												</label>
												<div class="col-sm-8">
													<input type="text" id="CustomProtocol" name="CustomProtocol" ng-model="CustomProtocol" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="DestinationIpAddress">
													目的端資訊(IP)
												</label>
												<div class="col-sm-8">
													<input type="text" id="DestinationIpAddress" name="DestinationIpAddress" ng-model="DestinationIpAddress" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="DestinationPort">
													目的端資訊(通訊埠)
												</label>
												<div class="col-sm-8">
													<input type="text" id="DestinationPort" name="DestinationPort" ng-model="DestinationPort" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="DestinationProtocol">
													目的端資訊(通訊埠協定)
												</label>
												<div class="col-sm-8">
													<input type="text" id="DestinationProtocol" name="DestinationProtocol" ng-model="DestinationProtocol" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="LeveragedDescription">
													手法研判
												</label>
												<div class="col-sm-8">
													<input type="text" id="LeveragedDescription" name="LeveragedDescription" ng-model="LeveragedDescription" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="AffectedSoftwareDescription">
													影響平台
												</label>
												<div class="col-sm-8">
													<input type="text" id="AffectedSoftwareDescription" name="AffectedSoftwareDescription" ng-model="AffectedSoftwareDescription" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResourcesSourceIpAddress">
													來源IP
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResourcesSourceIpAddress" name="ResourcesSourceIpAddress" ng-model="ResourcesSourceIpAddress" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResourcesDestinationPort">
													目標資訊(通訊埠)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResourcesDestinationPort" name="ResourcesDestinationPort" ng-model="ResourcesDestinationPort" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResourcesDestinationProtocol">
													目標資訊(通訊埠協定)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResourcesDestinationProtocol" name="ResourcesDestinationProtocol" ng-model="ResourcesDestinationProtocol" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ResourcesDestination">
													目標對象
												</label>
												<div class="col-sm-8">
													<input type="text" id="ResourcesDestination" name="ResourcesDestination" ng-model="ResourcesDestination" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ScanEngine">
													掃描資訊(掃描引擎)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ScanEngine" name="ScanEngine" ng-model="ScanEngine" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ScanVersion">
													掃描資訊(掃描版本)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ScanVersion" name="ScanVersion" ng-model="ScanVersion" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="ScanResult">
													掃描資訊(掃描結果)
												</label>
												<div class="col-sm-8">
													<input type="text" id="ScanResult" name="ScanResult" ng-model="ScanResult" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="RelatedIncidentId">
													來源編號
												</label>
												<div class="col-sm-8">
													<input type="text" id="RelatedIncidentId" name="RelatedIncidentId" ng-model="RelatedIncidentId" 
													class="col-xs-12 col-sm-6"/>
												</div>
											</div>
											
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="IncidentDiscoveryTime">
													發現時間
												</label>
												<div class="col-sm-8">
													<div class="input-group col-xs-12 col-sm-6">
														<input id="IncidentDiscoveryTime" name="IncidentDiscoveryTime" ng-model="IncidentDiscoveryTime"
															type="text" class="form-control "> <span
															class="input-group-addon"> <i
															class="fa fa-clock-o bigger-130"></i>
														</span>
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="IncidentReportedTime">
													發送時間
												</label>
												<div class="col-sm-8">
													<div class="input-group col-xs-12 col-sm-6">
														<input id="IncidentReportedTime" name="IncidentReportedTime" ng-model="IncidentReportedTime"
															type="text" class="form-control "> <span
															class="input-group-addon"> <i
															class="fa fa-clock-o bigger-130"></i>
														</span>
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="IncidentClosedTime">
													解決時間
												</label>
												<div class="col-sm-8">
													<div class="input-group col-xs-12 col-sm-6">
														<input id="IncidentClosedTime" name="IncidentClosedTime" ng-model="IncidentClosedTime"
															type="text" class="form-control "> <span
															class="input-group-addon"> <i
															class="fa fa-clock-o bigger-130"></i>
														</span>
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="RelatedIncidentTimestamp">
													來源時間
												</label>
												<div class="col-sm-8">
													<div class="input-group col-xs-12 col-sm-6">
														<input id="RelatedIncidentTimestamp" name="RelatedIncidentTimestamp" ng-model="RelatedIncidentTimestamp"
															type="text" class="form-control "> <span
															class="input-group-addon"> <i
															class="fa fa-clock-o bigger-130"></i>
														</span>
													</div>
												</div>
											</div>
											
											<div class="row">
												<div class="form-group col-md-12">
													<label class="col-md-3 control-lable" for="IsEnable">是否啟用</label>
													<div class="col-md-7">
													<input id="IsEnable" type="checkbox" class="checkbox-primary" ng-model="IsEnable"	> 
													<div class="validation-msg" ng-show="myForm.IsEnable.$error.required">請選擇類別</div>
													</div>
												</div>
											</div>

											<div class="space-4"></div>
										</form>
									</div>

									<div class="modal-footer">

										<button class="btn btn-sm btn-info" 
											ng-click="createData();">
											<i class="ace-icon fa fa-check "></i>儲存
										</button>
										

										<button class="btn btn-sm btn-danger pull-right"
											data-dismiss="modal">
											<i class="ace-icon fa fa-times"></i> Close
										</button>

									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						
						
								
						
						
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>