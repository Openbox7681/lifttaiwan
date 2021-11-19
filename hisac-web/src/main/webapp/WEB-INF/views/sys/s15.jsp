<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s15.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
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
							<th><a href="" ng-click="setSortName('orgName')"> <s:message
										code="memberOrgName" /> <i ng-show="sorttype != 'orgName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'orgName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'orgName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('account')"> <s:message
										code="memberAccount" /> <i ng-show="sorttype != 'account'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'account' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'account' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('memberName')"> <s:message
										code="memberName" /> <i ng-show="sorttype != 'memberName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'memberName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'memberName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-show="${actionSign}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.OrgName}}</td>
							<td>{{item.Account}}</td>
							<td>{{item.Name}}</td>
							<td ng-show="${actionSign}" class="text-center"><a
								class="btn btn-sm btn-primary" ng-click="editData(item.Id);"
								title='<s:message code="btnSignApply" />'
								ng-show="${actionSign}"><i class="fas fa-fw fa-edit"></i> <s:message
										code="btnSignApply" /> </a></td>
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
	<div id="divEdit" class="container" ng-show="btnSign">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnSign">
				<big><i class="fas fa-fw fa-edit fa-lg"></i></big><b><s:message
						code="btnEdit" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="editForm">
					<div>
						<div class="form_group">
							<label for="OrgName"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberOrgName" /></label> <span>{{OrgName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="OrgParentLocalName"
								class="form_label form_label_search form_label_gray"><s:message
									code="orgType3SuperviseName" /></label> <span>{{OrgSuperviseName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="OrgName"
								class="form_label form_label_search form_label_gray"><s:message
									code="orgType3LocalName" /></label> <span>{{OrgLocalName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Account"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAdminAccount" /></label> <span>{{Account}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAdminName" /></label> <span>{{Name}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Email"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAdminEmail" /></label> <span>{{Email}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="MobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAdminMobilePhone" /></label> <span>{{MobilePhone}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="CreateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberSignUpDatetime" /></label> <span>{{CreateTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!editForm.$valid || signData()"
							ng-disabled="!editForm.$valid" ng-show="${actionSign} && btnSign">
							<i class="fas fa-fw fa-check"></i>
							<s:message code="btnSignApplyPass" />
						</button>
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="closeEdit()">
							<i class="fas fa-fw fa-undo"></i>
							<s:message code="btnReturn" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>