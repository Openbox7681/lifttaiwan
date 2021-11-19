<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s05.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" />
<script>
	var globalSureDisableMember = '<s:message code="globalSureDisableMember" />';
</script>
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
							<label for="QueryOrgId"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberOrgName" /></label>
							<div class="form_input form_input_search">
								<select id="QueryOrgId" name="QueryOrgId" ng-model="QueryOrgId" data-live-search="true" 
									class="selectpicker form-control" ng-disabled="QueryOrgIdDisable" ng-change="changOrgId()"
									ng-options="org.Id as org.Name + '('+org.Code+')' for org in orgs">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryAccount"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAccount" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryAccount" name="QueryAccount"
									ng-model="QueryAccount" class="form-control"
									placeholder="<s:message code="memberAccount" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryName"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="<s:message code="memberName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryEmail"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberEmail" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryEmail" name="QueryEmail"
									ng-model="QueryEmail" class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QuerySpareEmail"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberSpareEmail" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QuerySpareEmail" name="QuerySpareEmail"
									ng-model="QuerySpareEmail" class="form-control"
									placeholder="<s:message code="memberSpareEmail" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryMobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryMobilePhone" name="QueryMobilePhone"
									ng-model="QueryMobilePhone" class="form-control"
									placeholder="<s:message code="memberMobilePhone" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryCityPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberCityPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryCityPhone" name="QueryCityPhone"
									ng-model="QueryCityPhone" class="form-control"
									placeholder="<s:message code="memberCityPhone" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryFaxPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberFaxPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryFaxPhone" name="QueryFaxPhone"
									ng-model="QueryFaxPhone" class="form-control"
									placeholder="<s:message code="memberFaxPhone" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryAddress"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAddress" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryAddress" name="QueryAddress"
									ng-model="QueryAddress" class="form-control"
									placeholder="<s:message code="memberAddress" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryDepartment"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberDepartment" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryDepartment" name="QueryDepartment"
									ng-model="QueryDepartment" class="form-control"
									placeholder="<s:message code="memberDepartment" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryTitle"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberPosition" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTitle" name="QueryTitle"
									ng-model="QueryTitle" class="form-control"
									placeholder="<s:message code="memberPosition" />"
									autocomplete="off">
							</div>
						</div>
					</div>

					<c:if
						test="${(isMemberAdmin==true && isAdmin==false && isHisac==false)!=true}">
						<div>
							<div class="form_group">
								<label for="QueryIsEnable"
									class="form_label form_label_search form_label_gray"><s:message
										code="isEnable" /></label>
								<div class="form_input form_input_search">
									<select id="QueryIsEnable" name="QueryIsEnable"
										ng-model="QueryIsEnable" class="form-control">
										<option value="" selected><s:message code="all" /></option>
										<option value="true"><s:message code="isEnableTrue" /></option>
										<option value="false"><s:message code="isEanbleFalse" /></option>
									</select>
								</div>
							</div>
						</div>
					</c:if>
					
						<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberStatus" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
										ng-model="QueryStatus" class="form-control">
										<option value="" selected><s:message code="all" /></option>
										<option value="memberStatus0"><s:message code="memberStatus0" /></option>
										<option value="memberStatus1"><s:message code="memberStatus1" /></option>
										<option value="memberStatus2"><s:message code="memberStatus2" /></option>
										<option value="memberStatus3"><s:message code="memberStatus3" /></option>
									</select>
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
						<c:if test="${  isAdmin}">
						<button class="btn btn_custom btn_green" type="button"
							ng-click="exportData()">	
							<i class="fa fa-spinner fa-spin" ng-show="isLoading"></i>						
							產生Excel (依照選擇條件)
						</button>
						<button class="btn btn_custom btn_blue" type="button" ng-show="isExcel"
							ng-click="downloadExcel()">							
							下載Excel
						</button>
						</c:if>
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
				<a class="btn btn_custom btn_blue pull-right" type="button"
					ng-click="openEdit(1)" ng-show="${actionCreate}"> <i
					class="fas fa-fw fa-plus-circle"></i> <s:message code="btnCreate" />
				</a>												
			</div>			
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th rowspan="2"><a href="" ng-click="setSortName('account')">
									<s:message code="memberAccount" /> <i
									ng-show="sorttype != 'account'"
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
							</a><br /> <a href="" ng-click="setSortName('title')"> <s:message
										code="memberPosition" /> <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('email')"> <s:message
										code="memberEmail" /> <i ng-show="sorttype != 'email'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'email' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'email' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a><br /> <a href="" ng-click="setSortName('spareEmail')"> <s:message
										code="memberSpareEmail" /> <i
									ng-show="sorttype != 'spareEmail'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'spareEmail' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'spareEmail' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('mobilePhone')"> <s:message
										code="memberMobilePhone" /> <i
									ng-show="sorttype != 'mobilePhone'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'mobilePhone' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'mobilePhone' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a><br /> <a href="" ng-click="setSortName('cityPhone')"> <s:message
										code="memberCityPhone" /> <i
									ng-show="sorttype != 'cityPhone'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'cityPhone' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'cityPhone' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('name')"> <s:message
										code="memberOrgName" /> <i ng-show="sorttype != 'name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a><br /> <a href="" ng-click="setSortName('department')"> <s:message
										code="memberDepartment" /> <i
									ng-show="sorttype != 'department'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'department' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'department' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><s:message code="memberStatus" /><br /><s:message code="roleName" /></th>
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.Account}}</td>
							<td>{{item.Name}}<br />{{item.Title}}
							</td>
							<td>{{item.Email}}<br />{{item.SpareEmail}}
							</td>
							<td>{{item.MobilePhone}}<br />{{item.CityPhone}}
							</td>
							<td>{{item.OrgName}}<p ng-if="item.CILevel!=''">{{item.CILevel}}</p>{{item.Department}}<br />
							</td>
							<td><span ng-if="item.Status==0"><s:message
										code="memberStatus0" /></span> <span ng-if="item.Status==1"><s:message
										code="memberStatus1" /></span> <span ng-if="item.Status==2"><s:message
										code="memberStatus2" /></span> <span ng-if="item.Status==3"><s:message
										code="memberStatus3" /></span><br />{{item.Roles| join:', '}}</td>
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary" ng-click="editData(item.Id);"
								title='<s:message code="btnEdit" />'
								ng-if="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br
								ng-if="${actionUpdate} && item.Status==2" /> <a href="#"
								class="btn btn-sm btn-info" ng-click="deleteData(item.Id);"
								ng-if="${actionDelete} && item.Status==3 && ${memberId} != item.Id"><i
									class="far fa-fw fa-trash-alt"></i> <s:message
										code="btnIsEnableFalse" /></a> 
								<a href="#"
								class="btn btn-sm btn-info" ng-click="delete(item.Id);"
								ng-if="${actionDelete} && item.Status==0 && ${memberId} != item.Id"><i
									class="far fa-fw fa-trash-alt"></i> <s:message
										code="btnDelete" /></a> 
										<a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}" />/public/api/download_sign_up_info?account={{item.Account}}"
										title='下載會員申請資料'ng-if="${actionUpdate} && !(item.Status==2 || item.Status==3)"><i	
									class="fas fa-fw fa-edit"></i> 下載會員申請資料 </a>
										
										${baseMemberId}
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
							<label for="OrgId"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberOrgName" /></label>
							<div class="form_input form_input_search">						
								<select id="OrgId" name="OrgId" ng-model="OrgId" 
								ng-disabled=!btnIns
									ng-disabled=!btnIns							
									ng-disabled="btnUpd" class="form-control" ng-required="true" 
									ng-options="org.Id as org.Name + '('+org.Code+')' for org in orgs">
									
<!-- 																	ng-disabled="OrgIdDisable"	
 -->									
										
									<option value=""><s:message
											code="pleaseSelect" /><s:message code="memberOrgName" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.OrgId.$error.required && editForm.OrgId.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="memberOrgName" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Account"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAccount" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Account" name="Account" ng-disabled=!btnIns
									ng-model="Account" class="form-control"
									placeholder="<s:message code="memberAccount" />"
									autocomplete="off" ng-required="true" ng-maxlength="64"
									ng-pattern="/^[a-zA-Z][a-zA-Z0-9]{3,64}$/">
								<h5 class="text-danger"
									ng-show="editForm.Account.$error.required && editForm.Account.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberAccount" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Account.$error.maxlength && editForm.Account.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="64" />
								</h5>
								<h5 class="text-danger"
									ng-show="!editForm.Account.$error.required && editForm.Account.$invalid && editForm.Account.$dirty">
									<s:message code="memberAccountFormat" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="<s:message code="memberName" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberName" />
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
								class="form_label form_label_search form_label_gray"><s:message
									code="memberEmail" /></label>
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
						<div class="form_group">
							<label for="SpareEmail"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberSpareEmail" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="SpareEmail" name="SpareEmail"
									ng-model="SpareEmail" class="form-control"
							
									placeholder="<s:message code="memberSpareEmail" />"
									autocomplete="off" ng-maxlength="128">
									
									<h5 class="text-danger">
										如有多筆email請用,分隔，如example01@gamil.com,example02@gmail.com 
									</h5> 
									
									
									<%-- ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i"
									placeholder="<s:message code="memberSpareEmail" />"
									autocomplete="off" ng-maxlength="128"> --%>
								<%-- <h5 class="text-danger"
									ng-show="!editForm.SpareEmail.$error.required && editForm.SpareEmail.$invalid && editForm.SpareEmail.$dirty">
									<s:message code="memberEmailFormat" arguments="128" />
								</h5> --%>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="MobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="MobilePhone"
									name="MobilePhone" ng-model="MobilePhone"
									ng-maxlength="300" ng-minlength="10" 
									class="form-control"
									placeholder="<s:message code="memberMobilePhone" />"
									autocomplete="off"  >
									
									<h5 class="text-danger">
										如有多筆電話請用,分隔，如0912345678,0987654321
									</h5> 
									
									
<!-- 									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
 -->									
									
									<%-- ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="memberMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8"> --%>
								<%-- <h5 class="text-danger"
									ng-show="editForm.MobilePhone.$invalid && editForm.MobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5> --%>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="CityPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberCityPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="CityPhone" name="CityPhone"
									ng-model="CityPhone" class="form-control"
									placeholder="<s:message code="memberCityPhone" />"
									autocomplete="off" ng-maxlength="16"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />

								<h5 class="text-danger"
									ng-show="editForm.CityPhone.$error.maxlength && editForm.CityPhone.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="16" />
								</h5>
								<h5 class="validation-msg"
									ng-show="editForm.CityPhone.$invalid && editForm.CityPhone.$dirty">
									<s:message code="phoneFormatErr" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FaxPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberFaxPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="FaxPhone" name="FaxPhone"
									ng-model="FaxPhone" class="form-control"
									placeholder="<s:message code="memberFaxPhone" />"
									autocomplete="off" ng-maxlength="16"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />

								<h5 class="validation-msg"
									ng-show="!editForm.FaxPhone.$error.required && editForm.FaxPhone.$invalid && editForm.FaxPhone.$dirty">
									<s:message code="phoneFormatErr" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.FaxPhone.$error.maxlength && editForm.FaxPhone.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="16" />
								</h5>
								
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Address"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAddress" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Address" name="Address"
									ng-model="Address" class="form-control"
									placeholder="<s:message code="memberAddress" />"
									autocomplete="off" ng-maxlength="256" />
								<h5 class="text-danger"
									ng-show="editForm.Address.$error.maxlength && editForm.Address.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="256" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Department"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberDepartment" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Department" name="Department"
									ng-model="Department" class="form-control"
									placeholder="<s:message code="memberDepartment" />"
									autocomplete="off" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Department.$error.maxlength && editForm.Department.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberPosition" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Title" name="Title" ng-model="Title"
									class="form-control"
									placeholder="<s:message code="memberPosition" />"
									autocomplete="off" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.maxlength && editForm.Title.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="healthLevelId"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="healthLevel" /></label>
							<div class="form_input form_input_pdpl">
								<select id="healthLevelId" name="healthLevel"
									ng-model = "healthLevelId" class = "form-control"
									ng-disabled=!btnIns>
<!-- 									ng-options="healthLevel.id as healthLevel.name for healthLevel in healthLevels">
 -->									
									<option ng-value="1">醫學中心</option>
									<option ng-value="2">準醫學中心</option>
									<option ng-value="3">區域醫院</option>
									<option ng-value="4">地區醫院</option>
									<option ng-value="5">非醫院</option>
									<option ng-value="0"><s:message code="pleaseSelect" /><s:message
											code="healthLevel" /></option>
								</select>
							</div>	
						</div>			
					</div>
					
					
					<div>
						<div class="form_group">
							<label for="securityLevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="securityLevel" /></label>
							<div class="form_input form_input_search">
								<select id="securityLevel" name="securityLevel"
									ng-model="securityLevel" class="form-control"
									ng-disabled=!btnIns
									>
									<option ng-value="1">A</option>
									<option ng-value="2">B</option>
									<option ng-value="3">C</option>
									<option ng-value="4">D</option>
									<option ng-value="5">E</option>
									<option ng-value="0"><s:message code="pleaseSelect" /><s:message
											code="securityLevel" /></option>
									
								</select>
							</div>
						</div>
					</div>
					
					
					<div>
						<div class="form_group">
							<label for="IsPublic"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublicOffice" /></label>
							<div class="form_input form_input_search_half">
								<%-- <select id="IsPublic" name="IsPublic"
									ng-model="IsPublic" class="form-control"
									ng-disabled=!btnIns
									>
									<option ng-value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option ng-value="false"><s:message code="isPublicOfficeFalse" /></option>
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="isPublicOffice" /></option>
								</select> --%>
								
								<toggle ng-model="IsPublic" ng-init="IsPublic=false"
									ng-disabled=!btnIns
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isPublicOfficeTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isPublicOfficeFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
								
								
								
							</div>
						</div>
					</div>
					
					
					<div ng-show="memberRoleData.length > 0">
						<div class="form_group">
							<label for="Role"
								class="form_label form_label_search form_label_gray"><s:message
									code="s05Role" /></label>
							<div class="form_input form_input_search">
								<div class="checkbox" ng-repeat="item in memberRoleData">
									<label> <input type="checkbox" ng-model="item.Flag">
										<span class="lbl">{{item.Name}}</span>
									</label>
								</div>
							</div>
						</div>
					</div>					
					<div ng-if="CILevel=='CI會員' || CILevel=='進階會員'">						
						<div class="form_group">
							<label for="Subscribe"
								class="form_label form_label_search form_label_gray">訂閱</label>
							<div class="form_input form_input_search">
								<div class="checkbox" ng-repeat="item in SubscribeData">								
									<label> <input type="checkbox" ng-model="item.Flag">
										<span class="lbl">{{item.Name}}</span>										
									</label>
								</div>
							</div>
						</div>
					</div>
					<div ng-show="${memberId} != Id">
						<div class="form_group">
							<label for="IsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsEnable"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isEnableTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isEanbleFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!editForm.$valid || createData()"
							ng-disabled="!editForm.$valid"
							ng-show="${actionCreate} && btnIns">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnSave" />
						</button>
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!editForm.$valid || updateData()"
							ng-disabled="!editForm.$valid"
							ng-show="${actionUpdate} && btnUpd">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnSave" />
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
	
	<div class="modal-body" id="exporExcel" ng-show="false">
	<table>
		<thead>
			<tr>
				<th><s:message code="memberAccount" /></th>
				<th><s:message code="memberName" /></th>
				<th><s:message code="memberPosition" /></th>
				<th><s:message code="memberEmail" /></th> 
				<th>	<s:message code="memberSpareEmail" /></th>
				<th><s:message code="memberMobilePhone" /></th>
				<th><s:message code="memberCityPhone" /></th>			
				<th><s:message code="memberFaxPhone" /></th>						
				<th><s:message code="memberOrgName" /></th>
				<th>會員等級</th>
				<th><s:message code="memberDepartment" /></th>
				<th><s:message code="memberStatus" /></th>
				<th><s:message code="roleName" /></th>							
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="item in allExcelItems">
				<td>{{item.Account}}</td>
				<td>{{item.Name}}</td>
				<td>{{item.Title}}</td>							
				<td>{{item.Email}}</td>
				<td>{{item.SpareEmail}}</td>							
				<td>{{item.MobilePhone}}</td>
				<td>{{item.CityPhone}}</td>
				<td>{{item.FaxPhone}}</td>							
				<td>{{item.OrgName}}</td>
				<td>
					<span ng-if="item.CILevel!=''">{{item.CILevel}}</span>
					<span ng-if="item.CILevel==''">無</span>
				</td>
				<td>{{item.Department}}</td>
				<td>
					<span ng-if="item.Status==0"><s:message code="memberStatus0" /></span> 
					<span ng-if="item.Status==1"><s:message code="memberStatus1" /></span> 
					<span ng-if="item.Status==2"><s:message code="memberStatus2" /></span> 
					<span ng-if="item.Status==3"><s:message code="memberStatus3" /></span>
				</td>
				<td>{{item.Roles| join:', '}}</td>				
			</tr>
		</tbody>
	</table>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>