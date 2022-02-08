<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s12.js"></script>

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
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>

			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						&nbsp;
						<div class="form_group">
							<label for="QueryCode"
								class="form_label form_label_search form_label_gray"> <s:message
									code="s12QueryRole" />
							</label>
							<div class="form_input form_input_search">
								<select id="RoleId" name="RoleId" class="form-control"
									ng-model="RoleId" ng-change="changeRole()"
									ng-options="Role.Id as Role.Id+':'+Role.Name  for  Role in roles">
									<option value="" selected><s:message code="s12Select" /></option>
								</select>
							</div>
						</div>
					</div>
					&nbsp;
					<div class="pull-right">
						<div class="space-4"></div>
						<div class="space-4"></div>
						<div class="space-4"></div>
						<button class="btn btn-sm btn-info" ng-hide="btnUpd"
							ng-disabled="!showInsert" ng-click="btnUpdate();">
							<i class="ace-icon fa fa-check "></i>
							<s:message code="btnSave" />
						</button>
						<div class="space-4"></div>
					</div>
				</form>
			</div>
		</div>
		<%-- 
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
			<div class="col-xs-12 col-md-6 no_padding">
				<a class="btn btn_custom btn_blue pull-right" type="button"
					ng-click="openEdit()" ng-show="${actionCreate}"> <i
					class="fas fa-fw fa-plus-circle"></i> <s:message code="btnCreate" />
				</a>
			</div>
		</div> --%>
		<div class="row">
			<div class="input-group pull-right">
				<i class="fas fa-fw fa-search input-add"></i><input type="text"
					ng-model="search" />
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th width="25%"><s:message code="s12Name" /></th>
							<th width="10%"><label for="selectReadAllorNone"><input
									id="selectReadAllorNone"
									ng-disabled="disableSelectReadAllorNone" type="checkbox"
									class="" ng-model="selectReadAllorNone"
									ng-click="changeSelectAllorNone(1)" ng-true-value="true"
									ng-false-value="false"> <s:message
										code="s12ReadAllorNone" /></label></th>
						<%-- 	<th width="10%"><label for="selectInsAllorNone"><input
									id="selectInsAllorNone" ng-disabled="disableSelectInsAllorNone"
									type="checkbox" class="" ng-model="selectInsAllorNone"
									ng-click="changeSelectAllorNone(2)" ng-true-value="true"
									ng-false-value="false"> <s:message
										code="s12InsAllorNone" /></label></th>
							<th width="10%"><label for="selectUpdAllorNone"><input
									id="selectUpdAllorNone" ng-disabled="disableSelectUpdAllorNone"
									type="checkbox" class="" ng-model="selectUpdAllorNone"
									ng-click="changeSelectAllorNone(3)" ng-true-value="true"
									ng-false-value="false"> <s:message
										code="s12UpdAllorNone" /></label></th>
							<th width="10%"><label for="selectDelAllorNone"><input
									id="selectDelAllorNone" ng-disabled="disableSelectDelAllorNone"
									type="checkbox" class="" ng-model="selectDelAllorNone"
									ng-click="changeSelectAllorNone(4)" ng-true-value="true"
									ng-false-value="false"> <s:message
										code="s12DelAllorNone" /></label></th> --%>
							
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="form in RoleForm | filter:search">
							<td>{{form.FormName}}</td>
							<td class="text-center"><label
								for="selectRead_{{form.FormId}}"><input
									id="selectRead_{{form.FormId}}"
									ng-disabled="disableSelectRead_{{form.FormId}}" ng-
									type="checkbox" class="checkbox-primary"
									ng-model="form.ActionRead" ng-click="changeSelect(form.FormId)"
									ng-true-value="true" ng-false-value="false"> </label></td>
						<!-- 	<td class="text-center"><label
								for="selectIns_{{form.FormId}}"><input
									id="selectIns_{{form.FormId}}"
									ng-disabled="disableSelectIns_{{form.FormId}}" ng-
									type="checkbox" class="checkbox-primary"
									ng-model="form.ActionCreate"
									ng-click="changeSelect(form.FormId)" ng-true-value="true"
									ng-false-value="false"> </label></td>
							<td class="text-center"><label
								for="selectUpd_{{form.FormId}}"><input
									id="selectUpd_{{form.FormId}}"
									ng-disabled="disableSelectUpd_{{form.FormId}}" ng-
									type="checkbox" class="checkbox-primary"
									ng-model="form.ActionUpdate"
									ng-click="changeSelect(form.FormId)" ng-true-value="true"
									ng-false-value="false"> </label></td>
							<td class="text-center"><label
								for="selectDel_{{form.FormId}}"><input
									id="selectDel_{{form.FormId}}"
									ng-disabled="disableSelectDel_{{form.FormId}}" ng-
									type="checkbox" class="checkbox-primary"
									ng-model="form.ActionDelete"
									ng-click="changeSelect(form.FormId)" ng-true-value="true"
									ng-false-value="false"> </label></td> -->
							
						</tr>
					</tbody>
				</table>
			</div>
			<%-- <%@ include file="./../include/table_row_empty.jsp"%>
			<div class="text-center">
				<paging class="pagination" page="currentPage" page-size="maxRows"
					total="total" show-prev-next="true" show-first-last="true"
					text-next-class="fas fa-step-forward"
					text-prev-class="fas fa-step-backward"
					text-first-class="fas fa-fast-backward"
					text-last-class="fas fa-fast-forward"
					paging-action="queryData(page)"></paging>
			</div> --%>
		</div>
	</div>
	
	
	
	
		
				
	</div>	
	</div>
	</section>
	
	
	
	
	

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>