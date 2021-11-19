<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s08.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.twzipcode.min.js"></script>
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
								class="form_label form_label_search form_label_gray"><s:message
									code="healthcareCode" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryCode" name="QueryCode"
									ng-model="QueryCode" class="form-control"
									placeholder="<s:message code="healthcareCode" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryName"
								class="form_label form_label_search form_label_gray"><s:message
									code="healthcareName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="<s:message code="healthcareName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIsCI"
								class="form_label form_label_search form_label_gray"><s:message
									code="isCI" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsCI" name="QueryIsCI"
									ng-model="QueryIsCI" class="form-control">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isCITrue" /></option>
									<option value="false"><s:message code="isCIFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryParentOrgId"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="parentOrgName" /></label>
							<div class="form_input form_input_pdpl">
								<select id="QueryParentOrgId" name="QueryParentOrgId"
									ng-model="QueryParentOrgId" class="form-control"
									ng-options="authorOrg.value as authorOrg.name for authorOrg in authorOrgs">
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="parentOrgName" /></option>
								</select>
							</div>
						</div>
					</div>
					
					<div>
					
						<div class="form_group">
							<label for="QueryHealthLevelId"
							class="form_label form_label_pdpl form_label_gray"><s:message
									code="healthLevel" /></label>
							<div class="form_input form_input_pdpl">
							
							<select id="QueryHealthLevelId" name="QueryHealthLevelId"
									ng-model="QueryHealthLevelId" class="form-control">
									
<!-- 									ng-options="healthLevel.id as healthLevel.name for healthLevel in healthLevels"
 -->									
									
									<option ng-value="1">醫學中心</option>
									<option ng-value="2">準醫學中心</option>
									<option ng-value="3">區域醫院</option>
									<option ng-value="4">地區醫院</option>
									<option ng-value="5">非醫院</option>
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="healthLevel" /></option>
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
					ng-click="openEdit()" ng-show="${actionCreate}"> <i
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
							<th><a href="" ng-click="setSortName('code')"> <s:message
										code="healthcareCode" /> <i ng-show="sorttype != 'code'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'code' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'code' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('name')"> <s:message
										code="healthcareName" /> <i ng-show="sorttype != 'name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isCI')"> <s:message
										code="isCI" /> <i
									ng-show="sorttype != 'isCI'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isCI' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isCI' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('parentOrgId')"> <s:message
										code="parentOrgName" /> <i
									ng-show="sorttype != 'parentOrgId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'parentOrgId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'parentOrgId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>


							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.Code}}</td>
							<td>{{item.Name}}</td>
							<td>{{item.IsCI == true ? '<s:message code="isCITrue" />' : '<s:message code="isCIFalse" />'}}</td>
							<td>{{item.ParentOrgName}}</td>
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary"
								ng-click="editData(item.Code);"
								title='<s:message code="btnEdit" />' ng-show="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Code);" ng-show="${actionDelete}"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
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
							<label for="Code"
								class="form_label form_label_search form_label_gray"><s:message
									code="healthcareCode" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Code" name="Code" ng-model="Code"
									class="form-control"
									placeholder="<s:message code="healthcareCode" />"
									autocomplete="off" ng-required="true" ng-maxlength="10"
									ng-pattern="/^[a-zA-Z0-9]{0,10}$/">
								<h5 class="text-danger"
									ng-show="editForm.Code.$error.required && editForm.Code.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="healthcareCode" />
								</h5>
								<h5 class="text-danger"
									ng-show="!editForm.Code.$error.required && editForm.Code.$invalid && editForm.Code.$dirty">
									<s:message code="healthcareCodeFormat" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray"><s:message
									code="healthcareName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="<s:message code="healthcareName" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="healthcareName" />
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
							<label for="IsCI"
								class="form_label form_label_search form_label_gray"><s:message
									code="isCI" /></label>
							<div class="form_input form_input_search">
								<select id="IsCI" name="IsCI"
									ng-model="IsCI" class="form-control">
									<option ng-value="true"><s:message code="isCITrue" /></option>
									<option ng-value="false"><s:message code="isCIFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="orgAddress"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="orgAddress" /></label>
							<div class="form_input form_input_pdpl">
								<div id="twzipcode" class="twzipcode"></div>
								<script>
									$("#twzipcode").twzipcode(
											{
												"zipcodeIntoDistrict" : true,
												"css" : ["city form-control",
														"town form-control"],
												"countyName" : "city",
												"districtName" : "town"
											});
								</script>
								<input type="text" id="orgAddress" name="orgAddress"
									ng-model="orgAddress" ng-maxlength="256" class="form-control"
									placeholder="<s:message code="orgType4Address" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.orgCity.$error.required && myForm.orgCity.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="orgCity" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.orgCity.$error.required && myForm.orgCity.$invalid && myForm.orgCity.$dirty">
									<s:message code="orgTownFormat" />
								</h5>
								<h5 class="text-danger"
									ng-show="myForm.orgTown.$error.required && myForm.orgTown.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="orgTown" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.orgTown.$error.required && myForm.orgTown.$invalid && myForm.orgTown.$dirty">
									<s:message code="orgTownFormat" />
								</h5>
								<h5 class="text-danger"
									ng-show="myForm.orgAddress.$error.required && myForm.orgAddress.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="orgType4Address" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.orgAddress.$error.required && myForm.orgAddress.$invalid && myForm.orgAddress.$dirty">
									<s:message code="orgAddressFormat" />
								</h5>
								<input type="text" id="orgCity" name="orgCity"
									ng-model="orgCity" ng-maxlength="10" class="form-control"
									placeholder="<s:message code="orgCity" />" autocomplete="off"
									ng-required="true" style="display: none;"> <input
									type="text" id="orgTown" name="orgTown" ng-model="orgTown"
									ng-maxlength="10" class="form-control"
									placeholder="<s:message code="orgTown" />" autocomplete="off"
									ng-required="true" style="display: none;">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="parentOrgId"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="parentOrgName" /></label>
							<div class="form_input form_input_pdpl">
								<select id="parentOrgId" name="parentOrgId"
									ng-model="parentOrgId" class="form-control"
									ng-options="authorOrg.value as authorOrg.name for authorOrg in authorOrgs">
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="parentOrgName" /></option>
								</select>
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
									ng-model = "healthLevelId" class = "form-control">
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
									ng-model="securityLevel" class="form-control">
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
							<div class="form_input form_input_search">
								<%-- <select id="IsPublic" name="IsPublic"
									ng-model="IsPublic" class="form-control">
									<option ng-value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option ng-value="false"><s:message code="isPublicOfficeFalse" /></option>
									<option ng-value="0"><s:message code="pleaseSelect" /><s:message
											code="isPublicOffice" /></option>
								</select> --%>
								
								<toggle ng-model="IsPublic" ng-init="IsPublic=false"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isPublicOfficeTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isPublicOfficeFalse" />'
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
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>