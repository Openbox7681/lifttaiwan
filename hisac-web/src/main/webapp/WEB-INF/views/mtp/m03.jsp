<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/mtp/m03.js"></script>
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
							<label for="QueryCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageGroup" /></label>
							<div class="form_input form_input_search">
								<select ng-model="QueryMaintainPlanGroup"
									ng-change="changeMaintainPlanGroup()"
									ng-options="MaintainPlanGroup as MaintainPlanGroup.Id+':'+MaintainPlanGroup.Name for MaintainPlanGroup in MaintainPlanGroupList"
									class="form-control col-xs-6">
									<option value=""><s:message code="messageSelectGroup" /></option>
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
							<th width="10%"><input id="selectSelAllorNone"
								type="checkbox" class="checkbox" ng-model="selectSelAllorNone"
								ng-click="changeSelectAllorNone()"> <label
								for="selectSelAllorNone">select all</label></th>
							<th width="25%"><a href="" ng-click="setSortName('name')">
									<span><s:message code="n05Name" /></span> <i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><input id="selectSel_{{item.OrgId}}" type="checkbox"
								class="checkbox-primary" ng-model="item.Flag"></td>
							<td>{{item.Name}}</td>
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
