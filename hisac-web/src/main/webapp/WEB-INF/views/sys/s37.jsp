<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s37.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QueryId"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37Id" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryId" name="QueryId"
									ng-model="QueryId" class="form-control"
									placeholder="<s:message code="s37Id" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="QueryQAMgId"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37QAMgId" /></label>
							<div class="form_input form_input_search">
								 <select id="QueryQAMgId" name="QueryQAMgId"
									ng-model="QueryQAMgId" class="form-control col-xs-6"
									ng-options="QAManagementGroup.Id as QAManagementGroup.Id+':'+QAManagementGroup.Name  for  QAManagementGroup in qaManagementGroups">
										<option value="" selected><s:message code="all" /></option>
	                             </select>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="QueryQName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37QName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryQName" name="QueryQName"
									ng-model="QueryQName" class="form-control"
									placeholder="<s:message code="s37QName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryAName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37AName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryAName" name="QueryAName"
									ng-model="QueryAName" class="form-control"
									placeholder="<s:message code="s37AName" />"
									autocomplete="off">
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
					<div class="form_group">
							<label for="QueryIsPublic"
								class="form_label form_label_search form_label_gray">是否公開</label>
							<div class="form_input form_input_search">
								<select id="QueryIsPublic" name="QueryIsPublic"
									ng-model="QueryIsPublic" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isEnableTrue" /></option>
									<option value="false"><s:message code="isEanbleFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
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
							<th><a href="" ng-click="setSortName('id')"> <s:message
										code="s37Id" /> <i ng-show="sorttype != 'id'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'id' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'id' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('qaMgId')"> <s:message
										code="s37QAMgId" /> <i ng-show="sorttype != 'qaMgId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'qaMgId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'qaMgId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('qName')"> <s:message
										code="s37QName" /> <i ng-show="sorttype != 'qName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'qName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'qName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('aName')"> <s:message
										code="s37AName" /> <i ng-show="sorttype != 'aName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'aName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'aName' && sortreverse"
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
							<th><a href="" ng-click="setSortName('isPublic')"> <s:message
										code="isPublic" /> <i ng-show="sorttype != 'isPublic'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isPublic' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isPublic' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.Id}}</td>
							<td>{{item.QAMgName}}</td>
							<td>{{item.QName}}</td>
							<td>{{item.AName}}</td>
							<td class="text-center"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span></td>
							<td class="text-center"><span ng-show="{{item.IsPublic}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsPublic}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span></td>
						
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
							<label for="AlertCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37QAMgId" /></label>
							<div class="form_input form_input_search">
								<select id="QAMgId" name="QAMgId" ng-model="QAMgId"
									class="form-control col-xs-6"
									ng-options="QAManagementGroup.Id as QAManagementGroup.Id+':'+QAManagementGroup.Name  for  QAManagementGroup in qaManagementGroups"
									ng-required="true">
									<option value="" selected><s:message
											code="pleaseSelect" /><s:message code="s37QAMgId" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.AlertCode.$error.required && editForm.AlertCode.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="s37QAMgId" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37QName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QName" name="QName" ng-model="QName"
									class="form-control"
									placeholder="<s:message code="s37QName" />"
									autocomplete="off" ng-required="true" ng-maxlength="200" >
								<h5 class="text-danger"
									ng-show="editForm.QName.$error.required && editForm.QName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s37QName" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.QName.$error.maxlength && editForm.QName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="200" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="AName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s37AName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="AName" name="AName" ng-model="AName"
									class="form-control"
									placeholder="<s:message code="s37AName" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.AName.$error.required && editForm.AName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s37AName" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>
							<div class="form_input form_input_search">
								<toggle ng-model="IsEnable" ng-init="IsEnable=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isEnableTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isEanbleFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
							
						</div>
					</div>
					<div class="form_group">
							<label for="IsPublic"
								class="form_label form_label_search form_label_gray">是否公開</label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsPublic" ng-init="IsPublic=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isEnableTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isEanbleFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div>
					
		<div class="row" ng-show="btnUpd && itemAttachments==null">
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
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileAttachment"
							ng-model="FileAttachment" name="FileAttachment"
							ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							ngf-max-size="100MB" ngf-min-height="100">
							<button id="SelectAttachment" class="btn btn-primary">
								<i class="fas fa-fw fa-file fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s31Attachment" />
							</button>
							<span>{{FileAttachment.name}}</span>
						</div>
					</div>
				</div>
				<div>				
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="uploadAttachment()" ng-disabled="FileAttachment==null"
							ng-show="${actionCreate}">
							<i class="fas fa-fw fa-upload"></i>
							<s:message code="btnUploadAttachment" />
						</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" ng-show="btnUpd && itemAttachments!=null">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-paperclip fa-lg"></i></big><b><s:message
						code="globalAttachmentList" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div class="help-block"></div>
				<div>
					<div class="col-xs-12 col-sm-6 col-md-4"
						ng-repeat="item in itemAttachments">
						<div class="thumbnail">
							<div class="caption">
								<p>
									<s:message code="globalAttachmentDescription" />
									: {{item.FileDesc}}
								</p>
								<p>
									<s:message code="globalAttachmentName" />
									: <a
										href="./api/s37/attach/download/{{item.QAManagementId}}/{{item.Id}}"
										target="_blank">{{item.FileName}}</a>
								</p>
								<p>
									<s:message code="globalAttachmentSize" />
									: {{item.FileSize}}
								</p>
								<p>
									<s:message code="globalHashSHA256" />
									:
								</p>
								<div class="input-group">
									<input type="text" class="form-control"
										value="{{item.FileHash}}"> <span
										class="input-group-addon"
										onclick="$(this).prev().select(); document.execCommand('Copy')"><i
										class="fas fa-fw fa-copy"></i></span>
								</div>
							</div>
							<div class="caption">
								<p ng-show="${actionDelete}" class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteAttachmentData(item.Id);"
										ng-show="${actionDelete}">
										<i class="far fa-fw fa-trash-alt"></i>
										<s:message code="btnDelete" />
									</button>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="submit_bar"></div>
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