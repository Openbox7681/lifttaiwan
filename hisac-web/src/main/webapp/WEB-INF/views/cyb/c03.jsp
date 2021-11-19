<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c03.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QuerySApplyTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="informationExchangeQueryDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QuerySdate" name="QuerySdate"
										ng-model="QuerySdate" class="form-control"
										placeholder="<s:message code="informationExchangeQuerySdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QueryEdate" name="QueryEdate"
										ng-model="QueryEdate" class="form-control"
										placeholder="<s:message code="informationExchangeQueryEdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>


					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="clearData()">
							<i class="fas fa-fw fa-redo-alt"></i>
							<s:message code="btnReset" />
						</button>
					</div>
				</form>
			</div>
		</div>

		<div class="row" ng-show="ExporExcel">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
			<button class="btn btn_custom btn_heading_tool btn_blue pull-right" ng-click="exportData();">
				<i class="fas fa-fw fa-file-excel"></i><s:message
						code="globalExcel" />
			</button>
		</div>
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">

					<div ng-repeat="alertType in alertTypes">
						<div class="space-12"></div>
						<table
							class="table table-striped table-bordered table-hover table_customer table_gray">
							<thead>
								<tr>
									<th colspan="13">{{alertType.Name}}({{alertType.Code}})</th>
								</tr>

								<tr>
									<th width="4%"><span><s:message
												code="informationExchangeNo" /></span></th>
									<th width="8%"><span><s:message
												code="informationExchangeEventType" /></span></th>
									
									<th width="8%"><span><s:message
												code="informationExchangeNisacToHisac" /></span></th>									
									<th width="8%"><span><s:message
												code="informationExchangeSOC" /></span></th>			
									<th width="8%"><span>H-ISAC</span></th>			
									<th width="8%"><span>Secbuzzer</span></th>			
									<th width="8%"><span>數聯資安</span></th>		
									<th width="8%"><span>衛生福利部SOC</span></th>			
									<th width="8%"><span><s:message
												code="informationExchangeOtherToHisac" /></span></th>
									<th width="8%"><span><s:message
												code="informationExchangeToMessage" /></span></th>
									<th width="8%"><span>轉成最新消息</span></th>
									<th width="8%"><span>轉成資安訊息情報</span></th>									
									<th width="8%"><span><s:message
												code="informationExchangeHisacToNisac" /></span></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-if="item.AlertCode==alertType.Code"
									ng-repeat="item in eventTypes">
									<td>{{item.Code}}</td>
									<td>{{item.Name}}</td>

								
									<td>{{countInTotal(alertType.Code,item.Code,"2")|number:0}}</td>
									<td>{{countInTotal(alertType.Code,item.Code,"NHISOC")|number:0}}</td>									
									<td>{{countInTotal(alertType.Code,item.Code,"HISAC")|number:0}}</td>
									<td>{{countInTotal(alertType.Code,item.Code,"SEC")|number:0}}</td>
									<td>{{countInTotal(alertType.Code,item.Code,"ISSDU")|number:0}}</td>
									<td>{{countInTotal(alertType.Code,item.Code,"MOHWSOC")|number:0}}</td>
									<td>{{countInTotal(alertType.Code,item.Code,"OTH")|number:0}}</td>
									<td>{{countOutTotal(alertType.Code,item.Code,"1")|number:0}}</td>
									<td>{{countOutTotal(alertType.Code,item.Code,"2")|number:0}}</td>
									<td>{{countOutTotal(alertType.Code,item.Code,"3")|number:0}}</td>									
									<td>{{countOutTotal(alertType.Code,item.Code,"6")|number:0}}</td>
								</tr>
								<tr>
									<td>其他</td>
									<td>其他</td>		
									<td>{{countInOther(alertType.Code,"2")|number:0}}</td>
									<td>{{countInOther(alertType.Code,"NHISOC")|number:0}}</td>									
									<td>{{countInOther(alertType.Code,"HISAC")|number:0}}</td>
									<td>{{countInOther(alertType.Code,"SEC")|number:0}}</td>
									<td>{{countInOther(alertType.Code,"ISSDU")|number:0}}</td>
									<td>{{countInOther(alertType.Code,"MOHWSOC")|number:0}}</td>
									<td>{{countInOther(alertType.Code,"OTH")|number:0}}</td>
									<td>{{countOutOther(alertType.Code,"1")|number:0}}</td>
									<td>{{countOutOther(alertType.Code,"2")|number:0}}</td>
									<td>{{countOutOther(alertType.Code,"3")|number:0}}</td>									
									<td>{{countOutOther(alertType.Code,"6")|number:0}}</td>
								</tr>
								<tr>
									<th colspan="2"><s:message code="notifyHostAmount" /></th>
									<td>{{typeInTotal(alertType.Code, "2")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code, "NHISOC")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code,"HISAC")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code,"SEC")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code,"ISSDU")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code,"MOHWSOC")|number:0}}</td>
									<td>{{typeInTotal(alertType.Code, "OTH")|number:0}}</td>
									<td>{{typeOutTotal(alertType.Code, "1")|number:0}}</td>
									<td>{{typeOutTotal(alertType.Code, "2")|number:0}}</td>
									<td>{{typeOutTotal(alertType.Code, "3")|number:0}}</td>									
									<td>{{typeOutTotal(alertType.Code, "6")|number:0}}</td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>

			</div>

		</div>
	</div>



	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>