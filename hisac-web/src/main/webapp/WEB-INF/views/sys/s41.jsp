<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s41.js"></script>
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
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QueryPostDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41PostDateTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="QueryPostDateTime"
										name="QueryPostDateTime" ng-model="QueryPostDateTime"
										class="form-control"
										placeholder="<s:message code="s41PostDateTime" />"
										autocomplete="off"> <span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryTitle"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41Title" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTitle" name="QueryTitle"
									ng-model="QueryTitle" class="form-control"
									placeholder="<s:message code="s41Title" />" autocomplete="off">
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
							<th><a href="" ng-click="setSortName('postDateTime')"> <s:message
										code="s41PostDateTime" /> <i
									ng-show="sorttype != 'postDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('title')"> <s:message
										code="s41Title" /> <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('sourceName')"> <s:message
										code="s41SourceName" /> <i ng-show="sorttype != 'sourceName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sourceName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sourceName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><s:message code="s41SourceLink" />${sourceLink}</th>
							<th><a href="" ng-click="setSortName('isEnable')"> <s:message
										code="isEnable" /> <i ng-show="sorttype != 'isEnable'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isEnable' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isEnable' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.PostDateTime}}</td>
							<td>{{item.Title}}</td>
							<td>{{item.SourceName}}</td>
							<td>{{item.SourceLink}}</td>
							<td class="text-center"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
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
							<label for="PostDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41PostDateTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="PostDateTime" name="PostDateTime"
										ng-model="PostDateTime" class="form-control"
										placeholder="<s:message code="s41PostDateTime" />"
										autocomplete="off" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
								<h5 class="text-danger"
									ng-show="editForm.PostDateTime.$error.required && editForm.PostDateTime.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s41PostDateTime" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41Title" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Title" name="Title" ng-model="Title"
									class="form-control"
									placeholder="<s:message code="s41Title" />" autocomplete="off"
									ng-required="true" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.required && editForm.Title.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s41Title" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.maxlength && editForm.Title.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SourceName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41SourceName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceName" name="SourceName"
									ng-model="SourceName" class="form-control"
									placeholder="<s:message code="s41SourceName" />"
									autocomplete="off" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.SourceName.$error.maxlength && editForm.SourceName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SourceLink"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41SourceLink" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceLink" name="SourceLink"
									ng-model="SourceLink" class="form-control"
									placeholder="<s:message code="s41SourceLink" />"
									autocomplete="off" 
									ng-pattern="/^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/">
								<h5 class="text-danger"
									ng-show="editForm.SourceLink.$invalid && editForm.SourceLink.$dirty">
									<s:message code="s41SourceLink" />
									<s:message code="formatUrlError" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RangeDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s41StartDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="StartDateTime" name="StartDateTime"
										ng-model="StartDateTime" class="form-control"
										placeholder="<s:message code="s41StartDateTime" />"
										autocomplete="off"><span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EndDateTime" name="EndDateTime"
										ng-model="EndDateTime" class="form-control"
										placeholder="<s:message code="s41EndDateTime" />"
										autocomplete="off"><span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
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
		<div class="row" ng-show="btnUpd">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnUploadImage" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div>
					<div class="form-group">
						<label for="FileImage"
							class="form_label form_label_search form_label_gray"><s:message
								code="s41Image" /></label>
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileImage"
							ng-model="FileImage" name="FileImage"
							ngf-pattern=".jpg,.gif,.png" accept="image/*"
							ngf-max-size="4096KB" ngf-min-height="100">
							<button id="SelectImage" class="btn btn-primary">
								<i class="fas fa-fw fa-images fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s41Image" />
							</button>
							<span>{{FileImage.name}}</span>
						</div>
					</div>
				</div>
				<div>
					<div class="form_group">
						<label for="ImageDescription"
							class="form_label form_label_search form_label_gray"><s:message
								code="globalImageDescription" /></label>
						<div class="form_input form_input_search">
							<textarea type="text" id="ImageDescription"
								name="ImageDescription" ng-model="ImageDescription"
								class="form-control"
								placeholder="<s:message code="globalImageDescription" />"
								rows="5" autocomplete="off"></textarea>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="uploadImage()" ng-disabled="FileImage==null"
							ng-show="${actionCreate}">
							<i class="fas fa-fw fa-upload"></i>
							<s:message code="btnUploadImage" />
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row" ng-show="btnUpd && itemImages.length > 0">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-images fa-lg"></i></big><b><s:message
						code="globalImageList" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div class="help-block"></div>
				<div>
					<div class="col-xs-12 col-sm-6 col-md-4"
						ng-repeat="item in itemImages">
						<div class="thumbnail">
							<img src="./api/s41/pic/download/{{Id}}/{{item.Id}}"
								title="{{item.FileDesc}}" class="img-responsive" />
							<div class="caption">
								<p>
									<s:message code="globalImageDescription" />
									: {{item.FileDesc}}
								</p>
								<p>
									<s:message code="globalImageName" />
									: {{item.FileName}}
								</p>
								<p>
									<s:message code="globalImageSize" />
									: {{item.FileSize}}
								</p>
								<p>
									<s:message code="globalImageLink" />
									:
								</p>
								<div class="input-group">
									<input type="text" class="form-control"
										value="{{item.ImageLink}}"> <span
										class="input-group-addon"
										onclick="$(this).prev().select(); document.execCommand('Copy')"><i
										class="fas fa-fw fa-copy"></i></span>
								</div>
							</div>
							<div class="caption">
								<p ng-show="${actionDelete}" class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteImageData(item.Id);" ng-show="${actionDelete}">
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
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>