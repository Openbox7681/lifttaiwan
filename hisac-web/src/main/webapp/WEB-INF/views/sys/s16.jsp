<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s16.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.css">
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for=Sdate
								class="form_label form_label_search form_label_gray">發佈時間(起)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Sdate" name="Sdate" ng-model="Sdate"
									class="form-control"
									placeholder="發佈時間(起)"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Edate
								class="form_label form_label_search form_label_gray">發佈時間(迄)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Edate" name="Edate" ng-model="Edate"
									class="form-control"
									placeholder="發佈時間(迄)"
									autocomplete="off">
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
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="6">最新消息</th></tr>
						<tr>
							<th>總數</th>
							<th style="width: 15%;">編輯中</th>
							<th style="width: 15%;">已撤銷</th>
							<th style="width: 15%;">審核中</th>
							<th style="width: 15%;">已公告,啟用</th>
							<th style="width: 15%;">已公告,不啟用</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-center">{{newsReportTotal | number}}</td>
							<td class="text-center">{{newsReportEdit | number}}</td>
							<td class="text-center">{{newsReportDelete | number}}</td>
							<td class="text-center">{{newsReportSign | number}}</td>
							<td class="text-center">{{newsReportApply1 | number}}</td>
							<td class="text-center">{{newsReportApply0 | number}}</td>
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="6">活動訊息</th></tr>
						<tr>
							<th>總數</th>
							<th style="width: 15%;">編輯中</th>
							<th style="width: 15%;">已撤銷</th>
							<th style="width: 15%;">審核中</th>
							<th style="width: 15%;">已公告,啟用</th>
							<th style="width: 15%;">已公告,不啟用</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-center">{{activityReportTotal | number}}</td>
							<td class="text-center">{{activityReportEdit | number}}</td>
							<td class="text-center">{{activityReportDelete | number}}</td>
							<td class="text-center">{{activityReportSign | number}}</td>
							<td class="text-center">{{activityReportApply1 | number}}</td>
							<td class="text-center">{{activityReportApply0 | number}}</td>
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="6">資安訊息情報</th></tr>
						<tr>
							<th>總數</th>
							<th style="width: 15%;">編輯中</th>
							<th style="width: 15%;">已撤銷</th>
							<th style="width: 15%;">審核中</th>
							<th style="width: 15%;">已公告,啟用</th>
							<th style="width: 15%;">已公告,不啟用</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-center">{{anaReportTotal | number}}</td>
							<td class="text-center">{{anaReportEdit | number}}</td>
							<td class="text-center">{{anaReportDelete | number}}</td>
							<td class="text-center">{{anaReportSign | number}}</td>
							<td class="text-center">{{anaReportApply1 | number}}</td>
							<td class="text-center">{{anaReportApply0 | number}}</td>
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="8">情資</th></tr>
						<tr>
							<th>情資來源</th>
							<th style="width: 10%;">總數</th>
							<th style="width: 10%;">編輯中</th>
							<th style="width: 10%;">已撤銷</th>
							<th style="width: 10%;">審核中</th>
							<th style="width: 10%;">已轉公開資訊</th>
							<th style="width: 10%;">已轉警訊</th>
							<th style="width: 10%;">已轉N-ISAC</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="informationExchange in informationExchanges">
							<td class="text-center">{{informationExchange.Name}}</td>
							<td class="text-center">{{informationExchange.Total}}</td>
							<td class="text-center">{{informationExchange.Edit}}</td>
							<td class="text-center">{{informationExchange.Del}}</td>
							<td class="text-center">{{informationExchange.Sign}}</td>
							<td class="text-center">{{informationExchange.Pub}}</td>
							<td class="text-center">{{informationExchange.Alert}}</td>
							<td class="text-center">{{informationExchange.Nisac}}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th>合計</th>
							<th>{{informationExchangeTotal.Total}}</th>
							<th>{{informationExchangeTotal.Edit}}</th>
							<th>{{informationExchangeTotal.Del}}</th>
							<th>{{informationExchangeTotal.Sign}}</th>
							<th>{{informationExchangeTotal.Pub}}</th>
							<th>{{informationExchangeTotal.Alert}}</th>
							<th>{{informationExchangeTotal.Nisac}}</th>
						</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>