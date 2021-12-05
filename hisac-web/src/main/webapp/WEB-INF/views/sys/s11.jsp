<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s11.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	
	<section id="main_content">
	<div class="container">
	<div class="row">
				
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
									code="s11Id" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryId" name="QueryId"
									ng-model="QueryId" class="form-control"
									placeholder="<s:message code="s11Id" />"
									autocomplete="off">
							</div>
						</div>
					</div>
										
					<div>
						<div class="form_group">
							<label for="SubsystemId"
								class="form_label form_label_search form_label_gray"><s:message
									code="s11subsystemName" /></label>
							<div class="form_input form_input_search">
								<select id="QuerySubsystemId" name="QuerySubsystemId"
									ng-model="QuerySubsystemId" class="form-control col-xs-6"
									ng-options="Subsystem.Id as Subsystem.Id+':'+Subsystem.Name  for  Subsystem in subsystems">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>


					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray"><s:message
									code="s11Name" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="<s:message code="s11Name" />"
									autocomplete="off">
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="QueryIsExternalLink"
								class="form_label form_label_search form_label_gray"><s:message
									code="s11IsExternalLink" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsExternalLink" name="QueryIsExternalLink"
									ng-model="QueryIsExternalLink" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="s11IsExternalLinkTrue" /></option>
									<option value="false"><s:message code="s11IsExternalLinkFalse" /></option>
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
							<label for="QueryIsShow"
								class="form_label form_label_search form_label_gray"><s:message code="isShow" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsShow" name="QueryIsShow"
									ng-model="QueryIsShow" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isShowTrue" /></option>
									<option value="false"><s:message code="isShowFalse" /></option>
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
							<th><a href="" ng-click="setSortName('Id')"> <span><s:message code="s11Id" /></span> 
									<i ng-show="sorttype != 'Id'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'Id' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'Id' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('SubsystemName')"> <s:message
										code="s11subsystemName" /> <i ng-show="sorttype != 'SubsystemName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'SubsystemName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'SubsystemName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('Name')"> <span><s:message code="s11Name" /></span> 
									<i ng-show="sorttype != 'Name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'Name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'Name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('IsExternalLink')"> <span><s:message code="s11IsExternalLink" /></span>
									<i ng-show="sorttype != 'IsExternalLink'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'IsExternalLink' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'IsExternalLink' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isEnable')"> <s:message
										code="isEnable" /> <i ng-show="sorttype != 'isEnable'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isEnable' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isEnable' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							<th><a href="" ng-click="setSortName('isShow')"> <s:message
										code="isShow" /> <i ng-show="sorttype != 'isShow'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isShow' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isShow' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							

							<th><a href="" ng-click="setSortName('sort')"> <s:message
										code="sort" /> <i ng-show="sorttype != 'sort'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sort' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sort' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							

							
							
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.Id}}</td>
							<td>{{item.SubsystemName}}</td>
							<td>{{item.Name}}</td>
							<td>{{item.IsExternalLink==true?"Y":"N"}}</td>
							<td class="text-center"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span></td>
						
							<td class="text-center"><span ng-show="{{item.IsShow}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isShowTrue" />'></i> <s:message
										code="isShowTrue" /></span> <span ng-show="{{!item.IsShow}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isShowFalse" />'></i> <s:message
										code="isShowFalse" /></span></td>

							<td class="text-right">{{item.Sort}}</td>										

							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary" ng-click="editData(item.Id);"
								title='<s:message code="btnEdit" />' ng-show="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);" ng-show="${actionDelete}"><i
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
				
				
				
				
				
				</div>
			
			</div>
	</section>
	
	
	
	
	
	
	
	
	
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
							<label for="SubsystemName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s11subsystemName" /></label>
							<div class="form_input form_input_search">
								<select id=SubsystemId name="SubsystemId"
									    ng-model="SubsystemId" class="form-control col-xs-6"
										ng-options="Subsystem.Id as Subsystem.Id+':'+Subsystem.Name  for  Subsystem in subsystems"
									    ng-required="true" >
										<option value="" selected><s:message code="s11Select" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="subsystemName" />
								</h5>
							</div>
						</div>
					</div>
														
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray"><s:message code="s11Name" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control" placeholder="<s:message code="s11Name" />" ng-maxlength="2147483647"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.maxlength && editForm.Name.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="2147483647" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
								    <s:message code="s11Name" />
								</h5>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="Code"
								class="form_label form_label_search form_label_gray"><s:message code="s11Code" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Code" name="Code" ng-model="Code"
									class="form-control" placeholder="<s:message code="s11Code" />" ng-maxlength="32" 
									autocomplete="off" ng-required="true" />
								<h5 class="text-danger"
									ng-show="editForm.Code.$error.maxlength && editForm.Code.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="32" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Code.$error.required && editForm.Code.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s11Code" />
								</h5>

							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="ControllerName"
								class="form_label form_label_search form_label_gray"><s:message code="s11ControllerName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ControllerName" name="ControllerName" ng-model="ControllerName"
									class="form-control" placeholder="<s:message code="s11ControllerName" />" ng-maxlength="64" 
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.ControllerName.$error.maxlength && editForm.ControllerName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="64" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ControllerName.$error.required && editForm.ControllerName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s11ControllerName" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ActionName"
								class="form_label form_label_search form_label_gray"><s:message code="s11ActionName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ActionName" name="ActionName" ng-model="ActionName"
									class="form-control" placeholder="<s:message code="s11ActionName" />" ng-maxlength="64" 
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.ActionName.$error.maxlength && editForm.ActionName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="64" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ActionName.$error.required && editForm.ActionName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s11ActionName" />
								</h5>
							</div>
						</div>
					</div>


					<div>
						<div class="form_group">
							<label for="IsExternalLink"
								class="form_label form_label_search form_label_gray"><s:message code="s11IsExternalLink" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsExternalLink" ng-init="IsExternalLink=true"
									on='<i class="far fa-fw fa-check-circle"></i>Y'
									off='<i class="fas fa-fw fa-minus-circle"></i>N'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>

							<label for="IsShow"
								class="form_label form_label_search form_label_gray"><s:message code="isShow" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsShow" ng-init="IsShow=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isShowTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isShowFalse" />'
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
							<label for="Sort"
								class="form_label form_label_search form_label_gray"><s:message
									code="sort" /></label>
							<div class="form_input form_input_search_half">
								<input type="number" id="Sort" name="Sort" ng-model="Sort"
									class="form-control" placeholder="<s:message code="sort" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric" min="0"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8"
									ng-required="true" ng-maxlength="4" />
								<h5 class="text-danger"
									ng-show="editForm.Sort.$error.maxlength && editForm.Sort.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="4" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Sort.$error.required && editForm.Sort.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="sort" />
								</h5>
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