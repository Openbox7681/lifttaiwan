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

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s03.js"></script>

<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	
	<section id="main_content">
	<div class="container">
	<div class="row">
	<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
        </div>
	<%@ include file="../include/slidebar.jsp"%>
	
	<div id="divQuery" class="col-lg-9 container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="Id"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemid" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Id" name="Id"
									ng-model="Id" class="form-control"
									placeholder="<s:message code="systemid" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="AppName"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemAppName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="AppName" name="AppName"
									ng-model="AppName" class="form-control"
									placeholder="<s:message code="systemAppName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FuncName"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemFuncName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="FuncName" name="FuncName"
									ng-model="FuncName" class="form-control"
									placeholder="<s:message code="systemFuncName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="InputValue"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemInputValue" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="InputValue" name="InputValue"
									ng-model="InputValue" class="form-control"
									placeholder="<s:message code="systemInputValue" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ActionName"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemActionName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ActionName" name="ActionName"
									ng-model="ActionName" class="form-control"
									placeholder="<s:message code="systemActionName" />"
									autocomplete="off">
							</div>
						</div>
					</div>					
					<div>
						<div class="form_group">
							<label for="Status"
								class="form_label form_label_search form_label_gray"><s:message
									code="systemStatus" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Status" name="Status"
									ng-model="Status" class="form-control"
									placeholder="<s:message code="systemStatus" />"
									autocomplete="off">
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
							<label for="HashCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="HashCode" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="HashCode" name="HashCode"
									ng-model="HashCode" class="form-control"
									placeholder="<s:message code="HashCode" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=CreateAccount
								class="form_label form_label_search form_label_gray"><s:message
									code="systemCreateAccount" /></label>
							<div class="form_input form_input_search">
								<input type="datetime-local" id="CreateAccount" name="CreateAccount"
									ng-model="CreateAccount" class="form-control"
									placeholder="<s:message code="systemCreateAccount" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Sdate
								class="form_label form_label_search form_label_gray"><s:message
									code="systemSdate" /></label>
							<div class="form_input form_input_search" style="position: relative">
								<input type="text" id="Sdate" name="Sdate"
									ng-model="Sdate" class="form-control"
									placeholder="<s:message code="systemSdate" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Edate
								class="form_label form_label_search form_label_gray"><s:message
									code="systemEdate" /></label>
							<div class="form_input form_input_search"  style="position: relative">
								<input type="text" id="Edate" name="Edate"
									ng-model="Edate" class="form-control"
									placeholder="<s:message code="systemEdate" />"
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
							<th><a href="" ng-click="setSortName('id')"> <s:message
										code="systemid" /> <i ng-show="sorttype != 'id'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'id' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'id' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('createAccount')"> <s:message
										code="systemCreateAccount" /> <i ng-show="sorttype != 'createAccount'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createAccount' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createAccount' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th><a href="" ng-click="setSortName('ip')"> <s:message
										code="systemIp" /> <i ng-show="sorttype != 'ip'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'ip' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'ip' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('appName')"> <s:message
										code="systemAppName" /> <i ng-show="sorttype != 'appName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'appName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'appName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('funcName')"> <s:message
										code="systemFuncName" /> <i ng-show="sorttype != 'funcName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'funcName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'funcName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('actionName')"> <s:message
										code="systemActionName" /> <i ng-show="sorttype != 'actionName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'actionName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'actionName' && sortreverse"
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
							<th><a href="" ng-click="setSortName('createTime')"> <s:message
										code="CreateTime" /> <i ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
						</tr>
					</thead>
					<tbody>
								<tr ng-repeat-start="item in allitems">
									<td>
									    <div class="pull-right">
											<span ng-hide="item.InputValue == ''" ng-if="item.expanded" ng-click="item.expanded = false" title="<s:message code="systemClose" />"><a style="cursor: pointer;"><i class="ace-icon fa fa-chevron-circle-up bigger-130"></i></a></span>
											<span ng-hide="item.InputValue == ''" ng-if="!item.expanded" ng-click="item.expanded = true" title="<s:message code="systemOpen" />"><a style="cursor: pointer;"><i class="ace-icon fa fa-chevron-circle-down bigger-130"></i></a></span>
										</div> {{item.Id}}
									</td>
									<td>{{item.CreateAccount}}</td>
									<td>{{item.Ip}}</td>
									<td>{{item.AppName}}</td>
									<td>{{item.FuncName}}</td>
									<td>{{item.ActionName}}</td>
									<td>{{item.Status}}</td>
									<td>{{item.CreateTime}}</td>
								</tr>
								<tr ng-if="item.expanded" ng-repeat-end="">
									<td colspan="8" class="text-left">
										<div class="well well-sm">
											<label><s:message
									code="systemInputValue" />:</label><br>{{item.InputValue}}
										</div>
									</td>
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
	
	
	</div>
	</div>
	</section>
	
	 <!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	
	
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>