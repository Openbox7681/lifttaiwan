<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s06.js"></script>
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
							<label for="QueryName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Name" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="<s:message
									code="s06Name" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Code" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryCode" name="QueryCode"
									ng-model="QueryCode" class="form-control"
									placeholder="<s:message
									code="s06Code" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryTel"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Tel" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTel" name="QueryTel"
									ng-model="QueryTel" class="form-control"
									placeholder="<s:message
									code="s06Tel" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryFax"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Fax" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryFax" name="QueryFax"
									ng-model="QueryFax" class="form-control"
									placeholder="<s:message
									code="s06Fax" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryCity"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06City" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryCity" name="QueryCity"
									ng-model="QueryCity" class="form-control"
									placeholder="<s:message
									code="s06City" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryTown"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Town" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTown" name="QueryTown"
									ng-model="QueryTown" class="form-control"
									placeholder="<s:message
									code="s06Town" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryAddress"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Address" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryAddress" name="QueryAddress"
									ng-model="QueryAddress" class="form-control"
									placeholder="<s:message
									code="s06Address" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=QueryOrgType
								class="form_label form_label_search form_label_gray"><s:message
									code="s06OrgType" /></label>
							<div class="form_input form_input_search">
								<select id="QueryOrgType" name="QueryOrgType"
									ng-model="QueryOrgType" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="0"><s:message code="s06OrgTypeOpt0" /></option>
									<option value="1"><s:message code="s06OrgTypeOpt1" /></option>
									<option value="2"><s:message code="s06OrgTypeOpt2" /></option>
									<option value="3"><s:message code="s06OrgTypeOpt3" /></option>
									<option value="4"><s:message code="s06OrgTypeOpt4" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryAuthType"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06AuthType" /></label>
							<div class="form_input form_input_search">
								<select id="QueryAuthType" name="QueryAuthType"
									ng-model="QueryAuthType" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="0"><s:message code="s06AuthTypeOpt0" /></option>
									<option value="1"><s:message code="s06AuthTypeOpt1" /></option>
									<option value="2"><s:message code="s06AuthTypeOpt2" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryCILevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="orgCILevel" /></label>
							<div class="form_input form_input_search">
								<select id="QueryCILevel" name="QueryCILevel"
									ng-model="QueryCILevel" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="0"><s:message code="orgCILevel0" /></option>
									<option value="1"><s:message code="orgCILevel1" /></option>
									<option value="2"><s:message code="orgCILevel2" /></option>
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
<!-- 									ng-options="healthLevel.id as healthLevel.name for healthLevel in healthLevels">
 -->									
 										<option ng-value="1">醫學中心</option>
										<option ng-value="2">準醫學中心</option>
										<option ng-value="3">區域醫院</option>
										<option ng-value="4">地區醫院</option>
										<option ng-value="5">非醫院</option>
 
 										<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
					</div>
					
					</div>
					
					
					<div>
						<div class="form_group">
							<label for="QuerySecurityLevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="securityLevel" /></label>
							<div class="form_input form_input_search">
								<select id="QuerySecurityLevel" name="QuerySecurityLevel"
									ng-model="QuerySecurityLevel" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option ng-value="1">A</option>
									<option ng-value="2">B</option>
									<option ng-value="3">C</option>
									<option ng-value="4">D</option>
									<option ng-value="5">E</option>
								</select>
							</div>
						</div>
					</div>
					
					
					
					
					<div>
						<div class="form_group">
							<label for="QueryIsPublic"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublicOffice" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsPublic" name="QueryIsPublic"
									ng-model="QueryIsPublic" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option value="false"><s:message code="isPublicOfficeFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					
			
					<div>
						<div class="form_group">
							<label for="QueryIsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsEnable" name="QueryIsEnable"
									ng-model="QueryIsEnable" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isEnableTrue" /></option>
									<option value="false"><s:message code="isEanbleFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="QueryIsLocate"
								class="form_label form_label_search form_label_gray">是否為部立醫院</label>
							<div class="form_input form_input_search">
								<select id="QueryIsLocate" name="QueryIsLocate"
									ng-model="QueryIsLocate" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option value="false"><s:message code="isPublicOfficeFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="QueryIsApply"
								class="form_label form_label_search form_label_gray">是否審核通過</label>
							<div class="form_input form_input_search">
								<select id="QueryIsApply" name="QueryIsApply"
									ng-model="QueryIsApply" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true">是</option>
									<option value="false">否</option>
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
							<th width="25%"><a href="" ng-click="setSortName('name')">
									<span><s:message code="s06Name" /></span> <i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th width="15%"><span><s:message code="s06Code" /></span> </i></th>
							<th width="10%"><span><s:message code="s06OrgType" /></span>
								</i></th>
							<th width="15%"><span><s:message code="s06AuthType" /></span>
								</i></th>
							<!-- <th width="25%"><span><s:message code="s06Tel" /></span> </i></th> -->
							<!-- <th width="25%"><span><s:message code="s06Fax" /></span> </i></th> -->
							<th width="10%"><span><s:message code="s06City" /></span> </i></th>
							<th width="5%"><span>是否審核通過</span> </i></th>
							<!-- <th width="25%"><span><s:message code="s06Town" /></span> </i></th> -->
							<!-- <th width="25%"><span><s:message code="s06Address" /></span> </i></th> -->
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
 							<th  width="10%" > <span><s:message code="s06ApiKey"/> </span> </i> </th>
 							<th width="10%"><span>金鑰狀態</span> </i></th>
 							
 							
						</tr>
					</thead>
					<tbody>

						<tr ng-repeat="item in allitems">
							<td>{{item.Name}}</td>
							<td>{{item.Code}}</td>
							<td>{{ item.OrgType==0?"<s:message code="s06OrgTypeName0" />":""
								}} {{ item.OrgType==1?"<s:message code="s06OrgTypeName1" />":""
								}} {{ item.OrgType==2?"<s:message code="s06OrgTypeName2" />":""
								}} {{ item.OrgType==3?"<s:message code="s06OrgTypeName3" />":""
								}} {{ item.OrgType==4?"<s:message code="s06OrgTypeName4" />":""
								}}<br />{{item.CILevel}}
							</td>
							<td>{{ item.AuthType==0?"<s:message code="s06AuthTypeName0" />":""
								}} {{ item.AuthType==1?"<s:message code="s06AuthTypeName1" />":""
								}} {{ item.AuthType==2?"<s:message code="s06AuthTypeName2" />":""
								}}
							</td>
							<!-- <td>{{item.Tel}}</td> -->
							<!-- <td>{{item.Fax}}</td> -->
							<td>{{item.City}}</td>
							<td>
							<span ng-show="item.IsApply">是</span>
							<span ng-show="!item.IsApply">否</span>
							</td>
							<!-- <td>{{item.Town}}</td> -->
							<!-- <td>{{item.Address}}</td> -->
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary" ng-click="editData(item.Id);"
								title='<s:message code="btnEdit" />' ng-show="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);" ng-show="${actionDelete}"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
							</td>
							
							 <td  class="text-center">
								<a ng-show= "item.AuthType =='0'"  class="btn btn-sm btn_green" ng-click="passApiKey(item.Id);"
								title='<s:message code="btnEdit" />' ><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnKeyPass" /> </a><br />
								<a ng-show= "item.AuthType =='0'" href="#" class="btn btn-sm btn-info"
								ng-click="deleteApiKey(item.Id);" ><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnkeyDelete" /></a>
							</td>
							
							<td>
							<span ng-show="item.ApiKeyStatus =='0' && item.AuthType =='0'">尚未申請</span>
							<span ng-show="item.ApiKeyStatus =='1' || item.ApiKeyStatus =='2' && item.AuthType =='0'">已申請</span>
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
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Name" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control" ng-maxlength="50"
									placeholder="<s:message
									code="s06Name" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s06Name" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.maxlength && editForm.Name.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="50" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Code"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Code" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Code" name="Code" ng-model="Code"
									class="form-control" placeholder="<s:message code="s06Code" />"
									autocomplete="off" ng-required="true" ng-maxlength="16" />

								<h5 class="text-danger"
									ng-show="editForm.Code.$error.required && editForm.Code.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s06Code" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Code.$error.maxlength && editForm.Code.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="16" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Tel"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Tel" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Tel" name="Tel" ng-model="Tel"
									class="form-control" placeholder="<s:message code="s06Tel" />"
									autocomplete="off" ng-maxlength="15"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />
								<h5 class="text-danger"
									ng-show="editForm.Tel.$error.maxlength && editForm.Tel.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="15" />
								</h5>
								<h5 class="validation-msg"
									ng-show="editForm.Tel.$invalid && editForm.Tel.$dirty">
									<s:message code="phoneFormatErr" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Fax"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06Fax" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Fax" name="Fax" ng-model="Fax"
									class="form-control" placeholder="<s:message code="s06Fax" />"
									autocomplete="off" ng-maxlength="15"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />
								<h5 class="text-danger"
									ng-show="editForm.Fax.$error.maxlength && editForm.Fax.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="15" />
								</h5>
								<h5 class="validation-msg"
									ng-show="editForm.Fax.$invalid && editForm.Fax.$dirty">
									<s:message code="phoneFormatErr" />
								</h5>
							</div>
						</div>
					</div>
					
					
					<div>
						<div class="form_group">
							<label for="parentOrgId"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="parentOrg" /></label>
							<div class="form_input form_input_pdpl">
								<select id="parentOrgId" name="parentOrg"
									ng-disabled="AuthType != '0'"
									ng-model = "parentOrgId" class = "form-control" 
									ng-options="parentOrg.Id as parentOrg.Name for parentOrg in parentOrgs">
 								
 										
 										<option value= "" selected >
 										<s:message code="pleaseSelect" /><s:message
											code="parentOrg" />
											</option>
								</select>
							</div>	
						</div>			
					</div>
					
					

					<div>
						<div class="form_group">
							<label for="OrgType"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06OrgType" /></label>
							<div class="form_input form_input_search">
								<select id="OrgType" name="OrgType" ng-model="OrgType"
									ng-required="true" class="form-control col-xs-6"
									ng-change="changeOrgType();">
									<option value="0"><s:message code="s06OrgTypeOpt0" /></option>
									<option value="1"><s:message code="s06OrgTypeOpt1" /></option>
									<option value="2"><s:message code="s06OrgTypeOpt2" /></option>
									<option value="3"><s:message code="s06OrgTypeOpt3" /></option>
									<option value="4"><s:message code="s06OrgTypeOpt4" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.OrgType.$error.required && editForm.OrgType.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s06ChooseOne" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="AuthType"
								class="form_label form_label_search form_label_gray"><s:message
									code="s06AuthType" /></label>
							<div class="form_input form_input_search">
								<select id="AuthType" name="AuthType" ng-model="AuthType"
									ng-required="true" class="form-control col-xs-6">
									<option value="0" ng-show="authType0"><s:message
											code="s06AuthTypeOpt0" /></option>
									<option value="1" ng-show="authType1"><s:message
											code="s06AuthTypeOpt1" /></option>
									<option value="2" ng-show="authType2"><s:message
											code="s06AuthTypeOpt2" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.AuthType.$error.required && editForm.AuthType.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s06ChooseOne" />
								</h5>
							</div>
						</div>
					</div>
					<div ng-show="OrgType == 3">
						<div class="form_group">
							<label for="CILevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="orgCILevel" /></label>
							<div class="form_input form_input_search">
								<select id="CILevel" name="CILevel" ng-model="CILevel"
									class="form-control col-xs-6">
									<option value="0"><s:message code="orgCILevel0" /></option>
									<option value="1"><s:message code="orgCILevel1" /></option>
									<option value="2"><s:message code="orgCILevel2" /></option>
								</select>
							</div>
						</div>
					</div>
					<div class="form_group">
						<label class="form_label form_label_search form_label_gray"
							for="file"><s:message code="s06Address" /></label>
						<div class="form_input form_input_search">
							<label for="orgAddress" class="sr-only"> <s:message
									code="orgAddress" />
							</label>
							<div class="input-group">
								<div id="twzipcode"></div>
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
							</div>
							<div>
								<input type="text" id="orgAddress" name="orgAddress"
									ng-model="Address" ng-maxlength="256" class="form-control"
									placeholder="<s:message code="orgType4Address" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.orgCity.$error.required && editForm.orgCity.$dirty">
									<s:message code="s06CityFormat" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.orgAddress.$error.required && editForm.orgAddress.$dirty">
									<s:message code="s06AddressFormat" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.orgAddress.$error.maxlength && editForm.orgAddress.$dirty">
									<s:message code="s06AddressLengthFormat" />
								</h5>
								<input type="text" id="orgCity" name="orgCity" ng-model="City"
									ng-maxlength="10" class="form-control"
									placeholder="<s:message code="s06City" />" autocomplete="off"
									ng-required="true" style="display: none;"> <input
									type="text" id="orgTown" name="orgTown" ng-model="Town"
									ng-maxlength="10" class="form-control"
									placeholder="<s:message code="s06Town" />" autocomplete="off"
									ng-required="true" style="display: none;">
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="BossName"
								class="form_label form_label_search form_label_gray"><s:message
									code="bossName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="BossName" name="BossName"
									ng-model="BossName" class="form-control"
									placeholder="<s:message code="bossName" />" autocomplete="off"
									ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="editForm.BossName.$error.required && editForm.BossName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="bossName" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.BossName.$error.maxlength && editForm.BossName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="BossEmail"
								class="form_label form_label_search form_label_gray"><s:message
									code="bossEmail" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="BossEmail" name="BossEmail"
									ng-model="BossEmail" class="form-control"
									ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i"
									placeholder="<s:message code="bossEmail" />" autocomplete="off"
									ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="editForm.BossEmail.$error.required && editForm.BossEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberEmail" />
								</h5>
								<h5 class="text-danger"
									ng-show="!editForm.BossEmail.$error.required && editForm.BossEmail.$invalid && editForm.BossEmail.$dirty">
									<s:message code="memberEmailFormat" arguments="128" />
								</h5>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="BossMobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="bossMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="BossMobilePhone" name="BossMobilePhone"
									ng-model="BossMobilePhone" ng-maxlength="10" ng-minlength="10"
									ng-pattern="/^09\d{8}$/" class="form-control"
									placeholder="<s:message code="bossMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="editForm.BossMobilePhone.$invalid && editForm.BossMobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="PrincipalName"
								class="form_label form_label_search form_label_gray"><s:message
									code="principalName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="PrincipalName" name="PrincipalName"
									ng-model="PrincipalName" class="form-control"
									placeholder="<s:message code="principalName" />"
									autocomplete="off" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="editForm.PrincipalName.$error.maxlength && editForm.PrincipalName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="PrincipalMobilePhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="principalMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="PrincipalMobilePhone"
									name="PrincipalMobilePhone" ng-model="PrincipalMobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="principalMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="editForm.PrincipalMobilePhone.$invalid && editForm.PrincipalMobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
								<h5 class="text-danger">
									<s:message code="principalMobilePhoneDesc" />
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
									
									ng-model = "healthLevelId" class = "form-control" >
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
					
					
					<%-- <div>
						<div class="form_group">
							<label for="ciLevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="orgCILevel" /></label>
							<div class="form_input form_input_search">
								<select id="ciLevel" name="ciLevel"
									ng-model="ciLevel" class="form-control col-xs-6">
									<option value="0"><s:message code="orgCILevel0" /></option>
									<option value="1"><s:message code="orgCILevel1" /></option>
									<option value="2"><s:message code="orgCILevel2" /></option>
								</select>
							</div>
						</div>
					</div> --%>
					
					
					<div>
						<div class="form_group">
							<label for="IsPublic"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublicOffice" /></label>
							<div class="form_input form_input_search_half">
							
							
							<%-- 	<select id="IsPublic" name="IsPublic"
									ng-model="IsPublic" class="form-control"
									>
									<option ng-value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option ng-value="false"><s:message code="isPublicOfficeFalse" /></option>
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="isPublicOffice" /></option>
								</select>
								 --%>
								<toggle ng-model="IsPublic" ng-init="IsPublic=false"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isPublicOfficeTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isPublicOfficeFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
								
								
								
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="IsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>

							<div class="form_input form_input_search_half">
								<toggle ng-model="IsEnable" ng-init="IsEnable=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isEnableTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isEanbleFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					
					<div ng-show= "AuthType =='0'">
						<div class="form_group">
							<label for="IsLocate"
								class="form_label form_label_search form_label_gray">是否為部立醫院</label>

							<div class="form_input form_input_search_half">
								<toggle ng-model="IsLocate" ng-init="IsLocate=true"
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
					
					
					
					<div ng-show= "AuthType =='0'">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalSecurityLevelLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessYear" /></th>
										<th><s:message code="globalProcessLogSecurityLevel" /></th>
										<th><s:message code="globalProcessLogCreateTime" /></th>
										
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in SecurityLevelLogs">
										
										
										<td>{{item.Year}}</td>
										<td>
										
										<span ng-if="item.SecurityLevel == 1">A</span>
										<span ng-if="item.SecurityLevel == 2">B</span>
										<span ng-if="item.SecurityLevel == 3">C</span>
										<span ng-if="item.SecurityLevel == 4">D</span>
										<span ng-if="item.SecurityLevel == 5">E</span>
										
										<span ng-if="item.SecurityLevel == 0">尚未設定資通安全責任等級</span>
										
										
										
										</td>
										<td>{{item.CreateTime}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					
					<div ng-show= "AuthType =='0'">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalCiLevelLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessYear" /></th>
										<th><s:message code="globalProcessLogCiLevel" /></th>
										<th><s:message code="globalProcessLogCreateTime" /></th>
										
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in CiLevelLogs">
										
										
										<td>{{item.Year}}</td>
										<td>
										
										<span ng-if="item.CiLevel == 0"><s:message code="orgCILevel0" /></span>
										<span ng-if="item.CiLevel == 1"><s:message code="orgCILevel1" /></span>
										<span ng-if="item.CiLevel == 2"><s:message code="orgCILevel2" /></span>
										
										
										<span ng-if="item.CiLevel == 10">尚未設定會員等級</span>
										
										
										
										</td>
										<td>{{item.CreateTime}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					
					
				</form>
			</div>
		</div>
	</div>
	
	<div class="modal-body" id="exporExcel" ng-show="false">
	<table>
		<thead>
			<tr>
				<th><s:message code="s06Name" /></th>
				<th><s:message code="s06Code" /></th>
				<th><s:message code="s06OrgType" /></th>
				<th>會員等級</th>
				<th><s:message code="s06AuthType" /></th>							
				<th><s:message code="s06City" /></th>
				<th><s:message code="s06Town" /></th>
				<th><s:message code="s06Address" /></th>
				<th>是否審核通過</th>						
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="item in allExcelItems">
				<td>{{item.Name}}</td>
				<td>{{item.Code}}</td>
				<td>{{ item.OrgType==0?"<s:message code="s06OrgTypeName0" />":""}} 
					{{ item.OrgType==1?"<s:message code="s06OrgTypeName1" />":""}} 
					{{ item.OrgType==2?"<s:message code="s06OrgTypeName2" />":""}} 
					{{ item.OrgType==3?"<s:message code="s06OrgTypeName3" />":""}} 
					{{ item.OrgType==4?"<s:message code="s06OrgTypeName4" />":""}}
				</td>
				<td>
					<span ng-if="item.CILevel!=''">{{item.CILevel}}</span>
					<span ng-if="item.CILevel==''">無</span>
				</td>
				<td>{{ item.AuthType==0?"<s:message code="s06AuthTypeName0" />":""}} 
					{{ item.AuthType==1?"<s:message code="s06AuthTypeName1" />":""}} 
					{{ item.AuthType==2?"<s:message code="s06AuthTypeName2" />":""}}
				</td>							
				<td>{{item.City}}</td>
				<td>{{item.Town}}</td>
				<td>{{item.Address}}</td>
				<td>
					<span ng-if="item.IsApply">是</span>
					<span ng-if="!item.IsApply">否</span>
				</td>				
			</tr>
		</tbody>
	</table>
	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>