<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s19.js"></script>
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
							<label for="QueryName"
								class="form_label form_label_search form_label_gray">名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="名稱"
									autocomplete="off">
							</div>
						</div>
						<div class="form_group">
							<label for="QueryEmail"
								class="form_label form_label_search form_label_gray">信箱</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryEmail" name="QueryEmail"
									ng-model="QueryEmail" class="form-control"
									placeholder="信箱"
									autocomplete="off">
							</div>
						</div>
						<div class="form_group">
							<label for="QueryMobilePhone"
								class="form_label form_label_search form_label_gray">行動電話</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryMobilePhone" name="QueryMobilePhone"
									ng-model="QueryMobilePhone" class="form-control"
									placeholder="行動電話"
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
			<div class="col-xs-12 col-md-6 no_padding">
				<c:if
					test="${actionCreate}">
					<a class="btn btn_custom btn_blue pull-right" type="button"
						ng-click="openEdit()"> <i class="fas fa-fw fa-plus-circle"></i>
						<s:message code="btnCreate" />
					</a>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('name')"> 名稱
									<i ng-show="sorttype != 'name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('email')"> 信箱 <i ng-show="sorttype != 'email'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'email' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'email' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('mobilePhone')"> 行動電話 <i
									ng-show="sorttype != 'mobilePhone'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'mobilePhone' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'mobilePhone' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">						
							<td>{{item.Name}}</td>
							<td>{{item.Email}}</td>
							<td>{{item.MobilePhone}}</td>							
							<td class="text-center">
							<a class="btn btn-sm btn-primary"
								title='<s:message code="btnEdit" />' ng-click="edit(item.Id);">
								<i class="fas fa-fw fa-edit"></i>
								<s:message code="btnEdit" /> </a> 
							<a href="#" class="btn btn-sm btn-info"
								title='<s:message code="btnDelete" />' ng-click="deleteData(item.Id);">
								<i class="far fa-fw fa-trash-alt"></i> 
								<s:message code="btnDelete" /></a>
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

	<div id="divEdit" class="container" ng-show="btnIns || btnUpd">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnIns">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnCreate" /></b>
			</h4>
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnUpd">
				<big><i class="fas fa-fw fa-edit fa-lg"></i></big><b><s:message
						code="btnEdit" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="editForm">													
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray">名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="登錄機構名稱" autocomplete="off"
									ng-required="true" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									名稱
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.maxlength && editForm.Name.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Email"
								class="form_label form_label_search form_label_gray">信箱</label>
							<div class="form_input form_input_search">
								<input type="text" id="Email" name="Email" ng-model="Email"
									class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off" ng-required="true" ng-maxlength="128"
									ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i" />

								<h5 class="text-danger"
									ng-show="editForm.Email.$error.required && editForm.Email.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberEmail" />
								</h5>
								<h5 class="text-danger"
									ng-show="!editForm.Email.$error.required && editForm.Email.$invalid && editForm.Email.$dirty">
									<s:message code="memberEmailFormat" />
								</h5>
							</div>
						</div>
					</div>
				<div>
					<div>
						<div class="form_group">
							<label for="MobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="MobilePhone" ng-required="true"
									name="MobilePhone" ng-model="MobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="memberMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="editForm.MobilePhone.$invalid && editForm.MobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
							</div>
						</div>
					</div>
					</div>																
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">												
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid"
							ng-click="createData(false)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							儲存
						</button>
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid"
							ng-click="createData(true)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							儲存並離開
						</button>														
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid"
							ng-click="updateData(false)" ng-show="btnUpd">
							<i class="fas fa-fw fa-save"></i>
							儲存
						</button>
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid"
							ng-click="updateData(true)" ng-show="btnUpd">
							<i class="fas fa-fw fa-save"></i>
							儲存並離開
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

	<%@ include file="../include/footer.jsp"%>
</body>
</html>