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
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/click_history.js"></script>
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
							<label
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
							<th><a href="" ng-click="setSortName('appName')"> 情資種類 <i ng-show="sorttype != 'appName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'appName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'appName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th><a href="" ng-click="setSortName('count')"> 點擊次數 <i ng-show="sorttype != 'count'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'count' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'count' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th><a href="" ng-click="setSortName('createTime')"> 日期 <i ng-show="sorttype != 'createTime'"
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
									<td><a href="#divTop10Detail" data-toggle="modal"
								ng-click="viewTop10Detail(item.AppName, item.CreateTime);">{{item.AppName=="p01_News"?"最新消息":
											item.AppName=="p02_Activity"?"活動訊息":
											item.AppName=="p04_Ana"?"資安訊息情報":
											item.AppName=="p06_SecBuzzer"?"醫療設備資安情報":""}}
									</a></td>																										
									<td>{{item.Count}}</td>
									<td>{{item.CreateTime.split(' ')[0]}}</td>
								</tr>								
					</tbody>
				</table>
			</div>
				<div id="divTop10Detail" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
				</div>
				<div class="modal-body">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr><th colspan="3">前十名</th></tr>
							<tr>								
								<th style="width: 25%;">排名</th>
								<th style="width: 50%;">文章名稱</th>
								<th style="width: 25%;">點擊次數</th>	
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in allTop10Detail">														
								<td class="text-center">{{item.Rank}}</td>								
								<td class="text-left">								
									<a ng-if="item.AppName == 'p01_News'" href="<c:out value="${pageContext.request.contextPath}" />/pub/p01?{{item.Url}}">{{item.Name}}</a>																
									<a ng-if="item.AppName == 'p02_Activity'" href="<c:out value="${pageContext.request.contextPath}" />/pub/p02?{{item.Url}}">{{item.Name}}</a>								
									<a ng-if="item.AppName == 'p04_Ana'" href="<c:out value="${pageContext.request.contextPath}" />/pub/p04?{{item.Url}}">{{item.Name}}</a>								
									<a ng-if="item.AppName == 'p06_SecBuzzer'" href="<c:out value="${pageContext.request.contextPath}" />/pub/p06?{{item.Url}}">{{item.Name}}</a>								
								</td>																							
								<td class="text-right">{{item.Count | number}}</td>							
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button class="btn btn-danger pull-right" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>
				</div>
			</div>
		</div>
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