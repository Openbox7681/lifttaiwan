<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script>
	var globalReadDataFail = '<s:message code="globalReadDataFail" />';
	var btnClose = '<s:message code="btnClose" />';
	var btnSure = '<s:message code="btnSure" />';
	var globalSureDeleteItem = '<s:message code="globalSureDeleteItem" />';
	var globalDeleteDataFail = '<s:message code="globalDeleteDataFail" />';
	var globalInsertDataFail = '<s:message code="globalInsertDataFail" />';
	var globalUpdateDataFail = '<s:message code="globalUpdateDataFail" />';
	var dataLoading = '<s:message code="dataLoading" />';
</script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/login_history.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.css">
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">																											
					<div>
						<div class="form_group">
							<label for="Status"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemStatus" /></label>
							<div class="form_input form_input_search">
							<select id="Status" name="Status"
									ng-model="Status" class="form-control">
									<option value="">全部</option>
									<option value="Success">Success</option>
									<option value="Fail">Fail</option>
									<option value="Deny">Deny</option>
								</select>								
							</div>
						</div>
					</div>								
					<div>
						<div class="form_group">
							<label for="Ip"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemIp" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Ip" name="Ip"
									ng-model="Ip" class="form-control"
									placeholder="<s:message code="systemIp" />"
									autocomplete="off">
							</div>
						</div>
					</div>										
					<div>
						<div class="form_group">
							<label for=Sdate
								class="form_label form_label_search form_label_gray">登入時間(起)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Sdate" name="Sdate"
									ng-model="Sdate" class="form-control"
									placeholder="登入時間(起)"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Edate
								class="form_label form_label_search form_label_gray">登入時間(迄)</label>
							<div class="form_input form_input_search">
								<input type="text" id="Edate" name="Edate"
									ng-model="Edate" class="form-control"
									placeholder="登入時間(迄)"
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
			<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>

		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>																		
							<th><a href="" ng-click="setSortName('ip')"> <s:message
										code="systemIp" /> <i ng-show="sorttype != 'ip'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'ip' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'ip' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="systemStatus" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('inputValue')"> 錯誤原因 <i ng-show="sorttype != 'inputValue'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'inputValue' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'inputValue' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('createTime')"> 登入時間 <i ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
						</tr>
					</thead>
					<tbody>
								<tr ng-repeat="item in allitems">									
									<td>{{item.Ip}}</td>									
									<td>{{item.Status}}</td>
									<td>{{item.InputValue}}</td>			
									<td>{{item.CreateTime}}</td>
								</tr>								
					</tbody>
				</table>
			</div>
			<%@ include file="./../include/table_row_empty.jsp"%>
			<div class="text-center">
				<paging class="pagination" page="currentPage" page-size="maxRows"
					total="total" show-prev-next="true" show-first-last="true"
					text-next-class="fas fa-step-forward"
					text-prev-class="fas fa-step-backward"
					text-first-class="fas fa-fast-backward"
					text-last-class="fas fa-fast-forward"
					paging-action="queryData(page)"></paging>
			</div>
		</div>
	</div>
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>