<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s13.js"></script>
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
								class="form_label form_label_search form_label_gray">上稿時間(起)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Sdate" name="Sdate" ng-model="Sdate"
									class="form-control"
									placeholder="上稿時間(起)"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Edate
								class="form_label form_label_search form_label_gray">上稿時間(迄)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Edate" name="Edate" ng-model="Edate"
									class="form-control"
									placeholder="上稿時間(迄)"
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
						<tr>
							<th>名稱</th>
							<th>上稿數量</th>
							<th>瀏覽數量</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th colspan="3">關於本系統</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>認識本系統</td>
							<td>{{p07Post | number}}</td>
							<td>{{p07View | number}}</td>
						</tr>
						<!-- 
						<tr>
							<td>組織架構</td>
							<td>{{p08Post | number}}</td>
							<td>{{p08View | number}}</td>
						</tr>
						<tr>
							<td>何謂ISAC</td>
							<td>{{p09Post | number}}</td>
							<td>{{p09View | number}}</td>
						</tr>
						 -->
					</tbody>
					<thead>
						<tr>
							<th colspan="3">公告訊息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>最新消息</td>
							<td>{{p01Post | number}}</td>
							<td>{{p01View | number}}</td>
						</tr>
						<tr>
							<td>活動訊息</td>
							<td>{{p02Post}}</td>
							<td>{{p02View}}</td>
						</tr>
						<tr>
							<td>資安訊息情報</td>
							<td>{{p04Post}}</td>
							<td>{{p04View}}</td>
						</tr>
						<tr>
							<td>醫療設備資安情報</td>
							<td>{{p06Post}}</td>
							<td>{{p06View}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>