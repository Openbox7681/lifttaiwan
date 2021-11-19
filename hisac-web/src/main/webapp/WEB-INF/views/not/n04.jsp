<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/not/n04.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QuerySApplyTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageQueryDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QuerySdate" name="QuerySdate"
										ng-model="QuerySdate" class="form-control"
										placeholder="<s:message code="messageQuerySdate" />"
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
										placeholder="<s:message code="messageQueryEdate" />"
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
									<th colspan="4">{{alertType.Name}}({{alertType.Code}})</th>
								</tr>
								<tr>
								<tr>
									<th colspan="2" width="25%"><span><s:message
												code="messageEventType" /></span></th>
									<th width="25%"><span><s:message
												code="messageEventTypeCount" /></span></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-if="item.AlertCode==alertType.Code"
									ng-repeat="item in eventTypes">
									<td>{{item.Code}}</td>
									<td>{{item.Name}}</td>
									<td>{{countTotal(item.Code)|number:0}}</td>
								</tr>
								<tr>
									<th colspan="2"><s:message code="notifyHostAmount" /></th>
									<td>{{alertTypeTotal(alertType.Code,item.Code)|number:0}}</td>
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