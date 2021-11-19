<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s14.js"></script>
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
							<label for="QueryCode"
								class="form_label form_label_search form_label_gray"> <s:message
									code="orgSignMember" />
							</label>
							<div class="form_input form_input_search">
								<select ng-model="QueryOrgId" ng-change="changeOrg()"
									ng-options="org as org.Name + '('+org.Code+')' for org in OrgList"
									class="form-control col-xs-6">
									<option value=""><s:message code="orgSignSelectMember" /></option>
								</select>

							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar" ng-show="${actionUpdate && actionDelete}">
						<button class="btn btn_custom btn-info" ng-click="updateData();">
							<i class="ace-icon fa fa-check "></i>
							<s:message code="btnSave" />
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
							<th width="4%"><s:message code="orgSignOrgId" /></th>
							<th width="15%"><span><s:message
										code="orgSignOrgName" /></span></th>
							<th width="5%"><span><s:message code="orgSignOrgType" /></span></th>
							<th width="17%"><span><s:message
										code="orgSignNotificationIsExamine" /></span></th>
							<th width="17%"><span><s:message
										code="orgSignNotificationClosingIsExamine" /></span></th>
							<th width="17%"><span><s:message
										code="orgSignWarningIsExamine" /></span></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in OrgSignList">
							<td>{{item.ParentOrgId}}</td>
							<td>{{item.Name}}</td>

							<td><input type="checkbox" class="checkbox-primary"
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"
								ng-model="item.Flag"></td>

							<td><input type="radio"
								ng-model="item.NotificationIsExamine" ng-value=1
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignExamine" /> <input type="radio"
								ng-model="item.NotificationIsExamine" ng-value=2
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignNoExamine" /> <input type="radio"
								ng-model="item.NotificationIsExamine" ng-value=3
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignExamineAndNoExamine" /></td>
							<td><input type="radio"
								ng-model="item.NotificationClosingIsExamine" ng-value=1
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignExamine" /> <input type="radio"
								ng-model="item.NotificationClosingIsExamine" ng-value=2
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignNoExamine" /> <input type="radio"
								ng-model="item.NotificationClosingIsExamine" ng-value=3
								ng-disabled="QueryOrgId=='' || QueryOrgId==null"> <s:message
									code="orgSignExamineAndNoExamine" /></td>
							<td><input type="radio" ng-model="item.WarningIsExamine"
								ng-value=1> <s:message code="orgSignExamine" /> <input
								type="radio" ng-model="item.WarningIsExamine" ng-value=2>
								<s:message code="orgSignNoExamine" /> <input type="radio"
								ng-model="item.WarningIsExamine" ng-value=3> <s:message
									code="orgSignExamineAndNoExamine" /></td>
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
