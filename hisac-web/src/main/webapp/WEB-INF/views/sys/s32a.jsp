<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s32a.js"></script>
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
							<label for="QueryTitle"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32QueryTitle" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTitle" name="QueryTitle"
									ng-model="QueryTitle" class="form-control"
									placeholder="<s:message code="s32QueryTitle" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryPostDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32PostDateTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="QueryPostDateTime"
										name="QueryPostDateTime" ng-model="QueryPostDateTime"
										class="form-control"
										placeholder="<s:message code="s32PostDateTime" />"
										autocomplete="off"> <span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
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
							<label for="QueryIsPublic"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublic" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsPublic" name="QueryIsPublic"
									ng-model="QueryIsPublic" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isPublicTrue" /></option>
									<option value="false"><s:message code="isPublicFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32Status" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1"><s:message code="s32StatusOpt1" /></option>
									<option value="2"><s:message code="s32StatusOpt2" /></option>
									<option value="3"><s:message code="s32StatusOpt3" /></option>
									<option value="4"><s:message code="s32StatusOpt4" /></option>
									<option value="5"><s:message code="s32StatusOpt5" /></option>
									<!-- <option value="6"><s:message code="s32StatusOpt6" /></option> -->
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
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('postId')"> <s:message
										code="s32PostId" /> <i ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('title')"> <s:message
										code="s32Title" /> <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('postDateTime')"> <s:message
										code="s32PostDateTime" /> <i
									ng-show="sorttype != 'postDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('sourceName')"> <s:message
										code="s32SourceName" /> <i ng-show="sorttype != 'sourceName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sourceName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sourceName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isEnable')"> <s:message
										code="isEnable" /> <i ng-show="sorttype != 'isEnable'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isEnable' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isEnable' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a><br /> <a href="" ng-click="setSortName('isPublic')"> <s:message
										code="isPublic" /> <i ng-show="sorttype != 'isPublic'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isPublic' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isPublic' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="s32Status" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('sort')"> 排序(數字越大排越上面)
							 <i ng-show="sorttype != 'sort'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sort' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sort' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><a href="" ng-click="review(item.Id, 'view')"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i>
									{{item.PostId}}
							</a></td>
							<td>{{item.Title}}</td>
							<td>{{item.PostDateTime}}</td>
							<td>{{item.SourceName}}</td>
							<td class="text-center"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span><br /> <span
								ng-show="{{item.IsPublic}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isPublicTrue" />'></i> <s:message
										code="isPublicTrue" /></span> <span ng-show="{{!item.IsPublic}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isPublicFalse" />'></i> <s:message
										code="isPublicFalse" /></span></td>
							<td class="text-center"><span
								ng-show="{{item.Status}} == '1'"><s:message
										code="s32StatusOpt1" /></span> <span
								ng-show="{{item.Status}} == '2'"><s:message
										code="s32StatusOpt2" /></span> <span
								ng-show="{{item.Status}} == '3'"><s:message
										code="s32StatusOpt3" /></span> <span
								ng-show="{{item.Status}} == '4'"><s:message
										code="s32StatusOpt4" /></span> <span
								ng-show="{{item.Status}} == '5'"><s:message
										code="s32StatusOpt5" /></span> <span
								ng-show="{{item.Status}} == '6'"><s:message
										code="s32StatusOpt6" /></span></td>
							<td>{{item.Sort}}</td>
							<td class="text-center"><a class="btn btn-sm btn-primary"
								title='<s:message code="btnEdit" />' ng-click="edit(item.Id);"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a> <a
								href="#" class="btn btn-sm btn-info"
								title='<s:message code="btnDelete" />'
								ng-click="deleteData(item.Id);"><i
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
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="PostId"
										class="form_label form_label_search form_label_gray"><s:message
											code="s32PostId" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="PostId == '' || PostId == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="PostId != '' && PostId != null">{{PostId}}</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="PostDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32PostDateTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="PostDateTime" name="PostDateTime"
										ng-model="PostDateTime" class="form-control"
										placeholder="<s:message code="s32PostDateTime" />"
										autocomplete="off" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
								<h5 class="text-danger"
									ng-show="editForm.PostDateTime.$error.required && editForm.PostDateTime.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s32PostDateTime" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32Title" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Title" name="Title" ng-model="Title"
									class="form-control"
									placeholder="<s:message code="s32Title" />" autocomplete="off"
									ng-required="true" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.required && editForm.Title.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s32Title" />
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
									code="s32SourceName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceName" name="SourceName"
									ng-model="SourceName" class="form-control"
									placeholder="<s:message code="s32SourceName" />"
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
									code="s32SourceLink" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceLink" name="SourceLink"
									ng-model="SourceLink" class="form-control"
									placeholder="<s:message code="s32SourceLink" />"
									autocomplete="off"
									ng-pattern="/^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/">
								<h5 class="text-danger"
									ng-show="editForm.SourceLink.$invalid && editForm.SourceLink.$dirty">
									<s:message code="s32SourceLink" />
									<s:message code="formatUrlError" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ContentType"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32ContentType" /></label> <span> <input type="radio"
								id="ContentType1" name="ContentType" ng-model="ContentType"
								ng-value="'1'" ng-checked="ContentType=='1'"
								ng-required="!ContentType"><label for="ContentType1"
								class="label_for_radio"><s:message
										code="s32ContentType1" /></label>
							</span><span> <input type="radio" id="ContentType2"
								name="ContentType" ng-model="ContentType" ng-value="'2'"
								ng-checked="ContentType=='2'" ng-required="!ContentType"><label
								for="ContentType2" class="label_for_radio"><s:message
										code="s32ContentType2" /></label>
							</span>
						</div>
						<h5 class="text-danger"
							ng-show="editForm.ContentType.$error.required">
							<s:message code="pleaseSelect" />
							<s:message code="s32ContentType" />
						</h5>
					</div>
					<div ng-show="ContentType=='1'">
						<div class="form_group">
							<label for="Content"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32Content" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="Content" name="Content"
									ng-model="Content" class="form-control"
									placeholder="<s:message code="s32Content" />" rows="10"
									autocomplete="off" ng-requird="true"></textarea>
							</div>
							<label for="IsBreakLine"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32IsBreakLine" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsBreakLine" ng-init="IsBreakLine=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="s32IsBreakLineTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="s32IsBreakLineFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div ng-show="ContentType=='2'">
						<div class="form_group">
							<label for="ExternalLink"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32ExternalLink" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ExternalLink" name="ExternalLink"
									ng-model="ExternalLink" class="form-control"
									placeholder="<s:message code="s32ExternalLink" />"
									autocomplete="off"
									ng-pattern="/^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/">
								<h5 class="text-danger"
									ng-show="ContentType=='2' && editForm.ExternalLink.$invalid && editForm.ExternalLink.$dirty">
									<s:message code="s32ExternalLink" />
									<s:message code="formatUrlError" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RangeDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32StartDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="StartDateTime" name="StartDateTime"
										ng-model="StartDateTime" class="form-control"
										placeholder="<s:message code="s32StartDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EndDateTime" name="EndDateTime"
										ng-model="EndDateTime" class="form-control"
										placeholder="<s:message code="s32EndDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
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
						<div class="form_group">
							<label for="IsPublic"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublic" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsPublic" ng-init="IsPublic=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isPublicTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isPublicFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
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
					<!-- <div>
						<div class="form_group">
							<label for="Status"
								class="form_label form_label_search form_label_gray"><s:message
									code="s32Status" /></label>
							<div class="form_input form_input_search">
								<select id="Status" name="Status"
									ng-model="Status" class="form-control col-xs-6"
									ng-required="true">
									<option value="" ng-selected="!Status">--<s:message code="pleaseSelect" /><s:message code="s32Status" />--</option>
									<option ng-value="1" ng-selected="Status == '1'"><s:message code="s32StatusOpt1" /></option>
									<option ng-value="2" ng-selected="Status == '2'"><s:message code="s32StatusOpt2" /></option>
									<option ng-value="3" ng-selected="Status == '3'"><s:message code="s32StatusOpt3" /></option>
									<option ng-value="4" ng-selected="Status == '4'"><s:message code="s32StatusOpt4" /></option>
									<option ng-value="5" ng-selected="Status == '5'"><s:message code="s32StatusOpt5" /></option>
									<option ng-value="6" ng-selected="Status == '6'"><s:message code="s32StatusOpt6" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.Status.$error.required && editForm.Status.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="s32Status" />
								</h5>
							</div>
						</div>
					</div>-->
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="Status"
										class="form_label form_label_search form_label_gray"><s:message
											code="s32Status" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="Status == '' || Status == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 1"><s:message
											code="s32StatusOpt1" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 2"><s:message
											code="s32StatusOpt2" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 3"><s:message
											code="s32StatusOpt3" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 4"><s:message
											code="s32StatusOpt4" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 5"><s:message
											code="s32StatusOpt5" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 6"><s:message
											code="s32StatusOpt6" /></span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue"
							ng-click="!editForm.$valid || updateData(false, false)"
							ng-disabled="!editForm.$valid" ng-show="btnUpd">
							<i class="fas fa-fw fa-check"></i>
							<s:message code="btnSubmit" />
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
								code="s32Image" /></label>
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileImage"
							ng-model="FileImage" name="FileImage"
							ngf-pattern=".jpg,.gif,.png" accept="'image/*'"
							ngf-max-size="4096KB" ngf-min-height="100">
							<button id="SelectImage" class="btn btn-primary">
								<i class="fas fa-fw fa-images fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s32Image" />
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
							<img src="./api/s32/pic/download/{{Id}}/{{item.Id}}"
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
		<div class="row" ng-show="btnUpd">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnUploadAttachment" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div>
					<div class="form-group">
						<label for="FileAttachment"
							class="form_label form_label_search form_label_gray"><s:message
								code="s32Attachment" /></label>
						<div class="form_input form_input_search"
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
					</div>
				</div>
				<div>
					<div class="form_group">
						<label for="AttachmentDescription"
							class="form_label form_label_search form_label_gray"><s:message
								code="globalAttachmentDescription" /></label>
						<div class="form_input form_input_search">
							<textarea type="text" id="AttachmentDescription"
								name="AttachmentDescription" ng-model="AttachmentDescription"
								class="form-control"
								placeholder="<s:message code="globalAttachmentDescription" />"
								rows="5" autocomplete="off"></textarea>
						</div>
					</div>
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
		<div class="row" ng-show="btnUpd && itemAttachments.length > 0">
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
										href="./api/s32/attach/download/{{item.ActivityManagementId}}/{{item.Id}}"
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
	</div>

	<div id="divReview" class="container">
		<div class="row">
			<div class="col-xs-12 shadow_board">
				<form class="form-horizontal" role="form" name="myForm">

					<div>
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="btnReview" /></b></i>
							</h4>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32PostId" /></label> <span>{{PostId}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32PostDateTime" /></label> <span>{{PostDateTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32Title" /></label> <span>{{Title}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32SourceName" /></label> <span>{{SourceName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32SourceLink" /></label> <span>{{SourceLink}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32ContentType" /></label> <span ng-show="ContentType == '1'"><s:message
									code="s32ContentType1" /></span> <span ng-show="ContentType == '2'"><s:message
									code="s32ContentType2" /></span>
						</div>
					</div>
					<div ng-show="ContentType == '1'">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32Content" /></label> <span>
								<div ng-if="!IsBreakLine" ng-bind-html="Content"></div>
								<div ng-if="IsBreakLine" ng-bind-html="Content | trustHtml"></div>
							</span>
						</div>
					</div>
					<div ng-show="ContentType == '2'">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32ExternalLink" /></label> <span>{{ExternalLink}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32StartDateTime" /></label> <span>{{StartDateTime}}</span> <span><s:message
									code="globalDataFromTo" /></span> <br class="visible-xs" /> <label
								class="form_label form_label_search form_label_gray"><s:message
									code="s32EndDateTime" /></label> <span>{{EndDateTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label> <span ng-show="IsEnable == true"><s:message
									code="isEnableTrue" /></span> <span ng-show="IsEnable == false"><s:message
									code="isEanbleFalse" /></span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="isPublic" /></label> <span ng-show="IsPublic == true"><s:message
									code="isPublicTrue" /></span> <span ng-show="IsPublic == false"><s:message
									code="isPublicFalse" /></span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s32Status" /></label> <span ng-show="Status == '1'"><s:message
									code="s32StatusOpt1" /></span> <span ng-show="Status == '2'"><s:message
									code="s32StatusOpt2" /></span> <span ng-show="Status == '3'"><s:message
									code="s32StatusOpt3" /></span> <span ng-show="Status == '4'"><s:message
									code="s32StatusOpt4" /></span> <span ng-show="Status == '5'"><s:message
									code="s32StatusOpt5" /></span> <span ng-show="Status == '6'"><s:message
									code="s32StatusOpt6" /></span>
						</div>
					</div>

					<div ng-if="itemImages.length > 0">
						<div class="form_group">
							<div class="form_group">
								<h4 class="form_heading form_heading form_heading_yellow">
									<i class="fa fa-info-circle"><b><s:message
												code="globalImageList" /></b></i>
								</h4>
							</div>
							<div class="col-xs-12 shadow_board">
								<div class="help-block"></div>
								<div>
									<div class="col-xs-12 col-sm-6 col-md-4"
										ng-repeat="item in itemImages">
										<div class="thumbnail">
											<img src="./api/s32/pic/download/{{Id}}/{{item.Id}}"
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
										</div>
									</div>
								</div>
								<div class="submit_bar"></div>
							</div>
						</div>
					</div>

					<div ng-if="itemAttachments.length > 0">
						<div class="form_group">
							<div class="form_group">
								<h4 class="form_heading form_heading form_heading_yellow">
									<i class="fa fa-info-circle"><b><s:message
												code="globalAttachmentList" /></b></i>
								</h4>
							</div>
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
														href="./api/s32/attach/download/{{item.ActivityManagementId}}/{{item.Id}}"
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
										</div>
									</div>
								</div>
								<div class="submit_bar"></div>
							</div>
						</div>
					</div>

					<div ng-show="IsSeeLog">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessLogAction" /></th>
										<th><s:message code="globalProcessLogStatus" /></th>
										<th><s:message code="globalProcessLogFrom" /></th>
										<th><s:message code="globalProcessLogSednTime" /></th>
										<th><s:message code="globalProcessLogContent" /></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in MessageProcessLogData">
										<td><span ng-if="item.PreStatus == 1"><s:message
													code="s32ShowPreStatus1" /></span> <span
											ng-if="item.PreStatus == 2"><s:message
													code="s32ShowPreStatus2" /></span> <span
											ng-if="item.PreStatus == 3"><s:message
													code="s32ShowPreStatus3" /></span> <span
											ng-if="item.PreStatus == 6"><s:message
													code="s32ShowPreStatus6" /></span> <span><i
												class="fas fa-fw fa-arrow-right"></i></span> <span
											ng-if="item.Status == 2"><s:message
													code="s32ShowStatus2" /></span> <span ng-if="item.Status == 3"><s:message
													code="s32ShowStatus3" /></span> <span ng-if="item.Status == 5"><s:message
													code="s32ShowStatus5" /></span> <span ng-if="item.Status == 6"><s:message
													code="s32ShowStatus6" /></span></td>
										<td><span ng-if="item.Status == 1"><s:message
													code="s32Status1" /></span> <span ng-if="item.Status == 2"><s:message
													code="s32Status2" /></span> <span ng-if="item.Status == 3"><s:message
													code="s32Status3" /></span> <span ng-if="item.Status == 4"><s:message
													code="s32Status4" /></span> <span ng-if="item.Status == 5"><s:message
													code="s32Status5" /></span> <span ng-if="item.Status == 6"><s:message
													code="s32Status6" /></span></td>
										<td>{{item.CreateName}}</td>
										<td>{{item.CreateTime}}</td>
										<td>{{item.Opinion}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div ng-show="IsSeeOpinion && Status == 3">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLogContent" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<label for="Opinion"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalProcessLogContent" /></label>
							<div class="form_input form_input_search">
								<textarea id="Opinion" name="Opinion" ng-model="Opinion"
									rows="5" autocomplete="off" class="form-control"
									ng-required="true"></textarea>
								<h5 class="text-danger"
									ng-show="myForm.Opinion.$error.required && myForm.Opinion.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="globalProcessLogContent" />
								</h5>
							</div>
						</div>
					</div>

				</form>
			</div>
			<div class="page-header">
				<div>
					<div class="clearfix form-actions">
						<div class="text-center">
							<button class="btn btn_custom btn_gray" ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>