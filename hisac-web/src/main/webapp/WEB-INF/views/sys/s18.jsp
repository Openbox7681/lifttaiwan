<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s18.js"></script>
<script>
	var globalAcceptImageFormat = '<s:message code="globalAcceptImageFormat" />';
	var globalAcceptAttachmentFormat = '<s:message code="globalAcceptAttachmentFormat" />';
	var globalUploadFileFail = '<s:message code="globalUploadFileFail" />';
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
							<label for="QueryName"
								class="form_label form_label_search form_label_gray">登錄機構名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="登錄機構名稱"
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
							<th><a href="" ng-click="setSortName('name')"> 登錄機構名稱 
									<i ng-show="sorttype != 'name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('section')"> 服務區域 <i ng-show="sorttype != 'section'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'section' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'section' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('contactInfo')"> 聯絡資訊 <i
									ng-show="sorttype != 'contactInfo'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'contactInfo' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'contactInfo' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('remark')"> 來源依據 <i
									ng-show="sorttype != 'remark'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'remark' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'remark' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">						
							<td>{{item.Name}}</td>
							<td>{{item.Section}}</td>
							<td>{{item.ContactInfo}}</td>		
							<td>
							<div ng-bind-html="item.Remark | trustHtml"></div>
							</td>							
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
								class="form_label form_label_search form_label_gray">登錄機構名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="登錄機構名稱" autocomplete="off"
									ng-required="true" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									登錄機構名稱
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
							<label for="Section"
								class="form_label form_label_search form_label_gray">服務區域</label>
							<div class="form_input form_input_search">
								<input type="text" id="Section" name="Section" ng-model="Section"
									class="form-control"
									placeholder="服務區域" autocomplete="off"
									ng-required="true" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Section.$error.required && editForm.Section.$dirty">
									<s:message code="pleaseEnter" />
									服務區域
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Section.$error.maxlength && editForm.Section.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
				<div>
						<div class="form_group">
							<label for="ContactInfo"
								class="form_label form_label_search form_label_gray">聯絡資訊</label>
							<div class="form_input form_input_search">
								<textarea id="ContactInfo" name="ContactInfo" ng-model="ContactInfo"
									class="form-control"
									placeholder="聯絡資訊" autocomplete="off"
									ng-required="true"></textarea>
								<h5 class="text-danger"
									ng-show="editForm.ContactInfo.$error.required">
									<s:message code="pleaseEnter" />
									聯絡資訊
								</h5>							
							</div>
						</div>
					</div>	
					<div>
						<div class="form_group">
							<label for="Remark"
								class="form_label form_label_search form_label_gray">備註</label>
							<div class="form_input form_input_search">
								<input type="checkbox"						
								ng-model="Remark.Safe">	
								<div class="numberCircle">1</div>
								資訊安全能量登錄通過名單(經濟部工業局委由中華民國資訊軟體協會推動)
								<input type="checkbox"							
								ng-model="Remark.CoWork">
								<div class="numberCircle">2</div>	
								經濟部工業局共同供應契約廠商					
							</div>
						</div>
					</div>
						<div>
						<div class="form_group">
							<label for="ServiceItems"
								class="form_label form_label_search form_label_gray">服務項目</label>
							<div class="form_input form_input_search">
								<textarea id="ServiceItems" name="ServiceItems" ng-model="ServiceItems"
									class="form-control"
									placeholder="服務項目" autocomplete="off"
									ng-required="true" ></textarea>
								<h5 class="text-danger"
									ng-show="editForm.ServiceItems.$error.required">
									<s:message code="pleaseEnter" />
									服務項目
								</h5>							
							</div>
						</div>
					</div>	
					<div>
				<div class="form_group">
							<label for="ContactorId"
								class="form_label form_label_search form_label_gray"> 對應機構
							</label>
							<div class="form_input form_input_search">
								<select id="ContactorId" name="ContactorId" class="form-control"
									ng-model="ContactorId" ng-required = "true"
									ng-options="Member.Id as Member.Id+':'+Member.Name  for  Member in members">
									<option value="" selected>請選擇機構</option>
								</select>
							</div>
						</div>
						</div>
					<div class="row">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnUploadAttachment" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div>
					<div class="form-group">
						<label for="FileAttachment"
							class="form_label form_label_search form_label_gray"><s:message
								code="s31Attachment" /></label>
					<div class="form_input form_input_search" ng-show="!IsFile"
							style="display: inline-block" ngf-select="" id="FileAttachment"
							ng-model="FileAttachment" name="FileAttachment"
							ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							ngf-max-size="100MB" ngf-min-height="100">
							<button id="SelectAttachment" class="btn btn-primary">
								<i class="fas fa-fw fa-file fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s32Attachment" />
							</button>						
							<span>{{FileAttachment.name}}</span>								
						</div>											
						<a  ng-if="IsFile" class="btn btn-sm btn-primary"
										href="./api/s18/attach/download/{{Id}}"
										target="_blank"><i class="fa fa-download"></i>{{File.name}}</a>
						<button class="btn btn-danger" ng-if="IsFile"
												ng-click="changeFile()">
												<i class="fas fa-fw fa-times"></i>
												<s:message code="btnDelete" />
											</button>						
					</div>					
				</div>															
			</div>
		</div>												
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">												
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid || FileAttachment.name==null"
							ng-click="createData(false)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							儲存
						</button>
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid || FileAttachment.name==null"
							ng-click="createData(true)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							儲存並離開
						</button>														
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid || (FileAttachment.name==null && File.name==null)"
							ng-click="updateData(false)" ng-show="btnUpd">
							<i class="fas fa-fw fa-save"></i>
							儲存
						</button>
						<button class="btn btn_custom btn_blue" ng-disabled="!editForm.$valid || (FileAttachment.name==null && File.name==null)"
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