<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s42.js"></script>
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
								class="form_label form_label_search form_label_gray">標題關鍵字</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryTitle" name="QueryTitle"
									ng-model="QueryTitle" class="form-control"
									placeholder="標題關鍵字"
									autocomplete="off">
							</div>
						</div>
					</div>				
					<div>
						<div class="form_group">
							<label for="QueryPostDateTime"
								class="form_label form_label_search form_label_gray">發布日期</label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="QueryPostDateTime"
										name="QueryPostDateTime" ng-model="QueryPostDateTime"
										class="form-control"
										placeholder="發布日期"
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
								class="form_label form_label_search form_label_gray">是否啟用</label>
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
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray">狀態</label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1">編輯中</option>
									<option value="2">撤銷中</option>
									<option value="3">審核中</option>
									<option value="4">已公告</option>
									<option value="5">已銷案</option>				
									<option value="6">編輯中(退回)</option>									
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
					<div>
						<div class="form-group hidden-xs">
							<div class="clearfix form-actions">
								<div class="text-center">
									
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('1');">
											編輯中											
												{{ButtonCount1}}											
										</button>
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('2');">
											撤銷中											
												{{ButtonCount2}}											
										</button>
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('3');">
											審核中
											<c:if test="${isHisacContentSign}">
												{{ButtonCount3}}
											</c:if>
										</button>
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('4');">
											已公告
										</button>
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('5');">
											已銷案
										</button>	
										<button class="btn btn-primary btn_bottom_fix"
											ng-click="setStatus('6');">
											編輯中(退回)
											{{ButtonCount6}}				
										</button>																												
								</div>
							</div>
						</div>
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
						ng-click="openEdit()"> <i class="fas fa-fw fa-plus-circle"></i>
						<s:message code="btnCreate" />
					</a>				
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>			
							<th><a href="" ng-click="setSortName('titleEdit')"> <s:message
										code="s31Title" />(建立時) <i ng-show="sorttype != 'titleEdit'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'titleEdit' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'titleEdit' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>			
							<th><a href="" ng-click="setSortName('title')"> <s:message
										code="s31Title" />(審核後) <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('postDateTime')"> <s:message
										code="s31PostDateTime" /> <i
									ng-show="sorttype != 'postDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postDateTime' && sortreverse"
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
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="s31Status" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-hide="${isHisacBoss}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
						<td><a href="" ng-click="review(item.Id, 'view')"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>								
									<a href="#my-modal" ng-click="review(item.Id, 'view')"
									ng-if="item.CreateId!=${memberId} && ${isAdmin || isHisac}"
										data-toggle="modal">{{item.TitleEdit}}</a>								
									<a href="#my-modal"
										ng-if="item.CreateId==${memberId} && (item.Status == 1 || item.Status == 6)"
										ng-click="edit(item.Id)" data-toggle="modal">{{item.TitleEdit}}</a>
									<a href="#my-modal"
										ng-if="item.CreateId==${memberId} && (item.Status == 2 || (${!isHisacContentSign} && item.Status == 3) || item.Status == 4 || item.Status == 5)"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.TitleEdit}}</a>								
									<a href="#my-modal" ng-if="${isHisacContentSign} && item.Status == 3"
										ng-click="review(item.Id, 'messageReview')"
										data-toggle="modal">{{item.TitleEdit}}</a>
									<a href="#my-modal"
										ng-if="item.CreateId!=${memberId} && ${isHisacContentSign} && (item.Status == 1 || item.Status == 2 || item.Status == 4 || item.Status == 5 || item.Status == 6)"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.TitleEdit}}</a>
								</td>														
							<td>{{item.Title}}</td>						
							<td>{{item.PostDateTime}}</td>							
							<td class="text-center"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span></td>
							<td class="text-center"><span
								ng-show="{{item.Status}} == '1'">編輯中</span> <span
								ng-show="{{item.Status}} == '2'">撤銷中</span> <span
								ng-show="{{item.Status}} == '3'">審核中</span> <span
								ng-show="{{item.Status}} == '4'">已公告</span> <span
								ng-show="{{item.Status}} == '5'">已銷案</span> <span
								ng-show="{{item.Status}} == '6'">編輯中(退回)</span> </td>							
							<td ng-hide="${isHisacBoss}" class="text-center"><a
								class="btn btn-sm btn-primary"
								title='<s:message code="btnEdit" />' ng-click="edit(item.Id);"
								ng-show="item.IsButtonEdit"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnEdit" /> </a> <a href="#"
								class="btn btn-sm btn-info"
								title='<s:message code="btnDelete" />'
								ng-click="deleteData(item.Id);" ng-show="item.IsButtonDelete"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
								<a class="btn btn-sm btn-primary"
								title='<s:message code="btnApply" />'
								ng-click="review(item.Id, 'messageReview')"
								ng-show="item.IsButtonReview"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnApply" /> </a> <a href="#"
								class="btn btn-sm btn-info"
								title='<s:message code="btnReject" />'
								ng-click="examine(item.Id, '5');" ng-show="item.IsButtonUndo"><i
									class="fas fa-fw fa-eraser"></i> <s:message code="btnReject" /></a>
								<c:if test="${isHisacContentSign}">
									<a href="#" class="btn btn-sm btn-info"
										ng-show="item.Status == 4 && item.IsEnable == true"
										ng-click="disable(item.Id)"
										title='<s:message code="btnIsEnableFalse" />'> <s:message
											code="btnIsEnableFalse" />
									</a>
									<a href="#" class="btn btn-sm btn-info"
										ng-show="item.Status == 4 && item.IsEnable == false"
										ng-click="enable(item.Id)"
										title='啟用'> 啟用
									</a>
								</c:if></td>
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
			<button class="btn btn_custom btn_blue pull-right" ng-click=changeRuleIsOpen()>
			<span class="glyphicon glyphicon-plus" ng-if="!RuleIsOpen"></span>
			<span class="glyphicon glyphicon-remove" ng-if="RuleIsOpen"></span>
			規範</button>	
			<div ng-if="RuleIsOpen">
			<ul>
            <li>請勿重覆張貼一樣的文章，或大意內容相同、類似的文章。</li>
            <li>張貼文章不得有違法或侵害他人權益之言論，請勿涉及謾罵、髒話穢言、侵害他人權利之言論，違者應自負法律責任。</li>
            <li>請勿提供軟體註冊碼等違反智慧財產權之資訊。</li>
            <li>禁止發表涉及他人隱私、含有個人對公眾人物之私評，且未經證實、未註明消息來源的網路八卦、不實謠言等。</li>            
            <strong>違反上述規定，網站管理者有權刪除留言，留言前請配合相關規則。</strong>
          </ul>
			</div>
			<div class="col-xs-12 shadow_board">
				<form name="editForm">												
					<div>
						<div class="form_group">
							<label for="TitleEdit"
								class="form_label form_label_search form_label_gray">標題</label>
							<div class="form_input form_input_search">
								<input type="text" id="TitleEdit" name="TitleEdit" ng-model="TitleEdit"
									class="form-control"
									placeholder="標題" autocomplete="off"
									ng-required="true" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.TitleEdit.$error.required && editForm.TitleEdit.$dirty">
									<s:message code="pleaseEnter" />
									標題
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.TitleEdit.$error.maxlength && editForm.TitleEdit.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SourceNameEdit"
								class="form_label form_label_search form_label_gray">資料來源</label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceNameEdit" name="SourceNameEdit"
									ng-model="SourceNameEdit" class="form-control"
									placeholder="資料來源"
									autocomplete="off" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.SourceNameEdit.$error.maxlength && editForm.SourceNameEdit.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SourceLinkEdit"
								class="form_label form_label_search form_label_gray">來源網址</label>
							<div class="form_input form_input_search">
								<input type="text" id="SourceLinkEdit" name="SourceLinkEdit"
									ng-model="SourceLinkEdit" class="form-control"
									placeholder="來源網址"
									autocomplete="off"
									ng-pattern="/^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/">
								<h5 class="text-danger"
									ng-show="editForm.SourceLinkEdit.$invalid && editForm.SourceLinkEdit.$dirty">
									來源網址
									<s:message code="formatUrlError" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="ContentEdit"
								class="form_label form_label_search form_label_gray">內容</label>
							<div class="form_input form_input_search">
								<textarea type="text" id="ContentEdit" name="ContentEdit"
									ng-model="ContentEdit" class="form-control"
									placeholder="內容" rows="10"
									autocomplete="off" ng-requird="true"></textarea>
							</div>							
						</div>
					</div>																		
					
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="Status"
										class="form_label form_label_search form_label_gray"><s:message
											code="s31Status" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="Status == '' || Status == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 1"><s:message
											code="s31StatusOpt1" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 2"><s:message
											code="s31StatusOpt2" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 3"><s:message
											code="s31StatusOpt3" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 4"><s:message
											code="s31StatusOpt4" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 5"><s:message
											code="s31StatusOpt5" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 6"><s:message
											code="s31StatusOpt6" /></span>
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
							ng-click="createData(false, false)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnTempSave" />
						</button>
						<button class="btn btn_custom btn_blue"
							ng-click="createData(true, false)" ng-show="btnIns">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnTempSaveAndExit" />
						</button>
						<button class="btn btn_custom btn_blue"
							ng-click="!editForm.$valid || createData(true, true)"
							ng-disabled="!editForm.$valid" ng-show="btnIns">
							<i class="fas fa-fw fa-check"></i>
							<s:message code="btnSubmit" />
						</button>

						<button class="btn btn_custom btn_blue"
							ng-click="updateData(false, false)" ng-show="btnUpd">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnTempSave" />
						</button>
						<button class="btn btn_custom btn_blue"
							ng-click="updateData(true, false)" ng-show="btnUpd">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnTempSaveAndExit" />
						</button>
						<button class="btn btn_custom btn_blue"
							ng-click="!editForm.$valid || updateData(true, true)"
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
								code="s31Image" /></label>
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileImage"
							ng-model="FileImage" name="FileImage"
							ngf-pattern=".jpg,.gif,.png" accept="'image/*'"
							ngf-max-size="4096KB" ngf-min-height="100">
							<button id="SelectImage" class="btn btn-primary">
								<i class="fas fa-fw fa-images fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s31Image" />
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
							<img src="./api/s42/pic/download/{{Id}}/{{item.Id}}"
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
										href="./api/s42/attach/download/{{item.InformationManagementId}}/{{item.Id}}"
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
					<div ng-show="CreateId<0">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">姓名</label> 
							<span>{{Name}}</span>
						</div>
					</div>	
					<div ng-show="CreateId<0">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">聯絡方式</label> 
							<span>{{Email}}</span>
						</div>
					</div>													
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31PostDateTime" /></label> <span>{{PostDateTime}}</span>
						</div>
					</div>					
					<div>					
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31Title" />(建立時)</label> <span>{{TitleEdit}}</span>																					
						</div>
					</div>												
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31SourceName" />(建立時)</label> <span>{{SourceNameEdit}}</span>							
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31SourceLink" />(建立時)</label> <span>{{SourceLinkEdit}}</span>							
						</div>
					</div>	
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31Content" />(建立時)</label> <span>
								<div ng-if="!IsBreakLine" ng-bind="ContentEdit"></div>								
								<div ng-if="IsBreakLine" >								
									<div ng-repeat = "item in (ContentEdit | trustHtml) track by $index"">																									
										{{item}} 
									</div>
								</div>
							</span>							
						</div>
					</div>		
					<div ng-show="Status==4">					
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31Title" />(審核後)</label> <span>{{Title}}</span>																					
						</div>
					</div>
					<div ng-show="Status==4">			
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31SourceName" />(審核後)</label> <span>{{SourceName}}</span>							
						</div>
					</div>
					<div ng-show="Status==4">			
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31SourceLink" />(審核後)</label> <span>{{SourceLink}}</span>							
						</div>
					</div>								
					<div ng-if="Status==4">			
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31Content" />(審核後)</label> <span>
								<div ng-if="!IsBreakLine" ng-bind="Content"></div>								
								<div ng-if="IsBreakLine" >								
									<div ng-repeat = "item in (Content | trustHtml) track by $index"">																									
										{{item}} 
									</div>
								</div>
							</span>							
						</div>
					</div>
					<div ng-show="Status==4 && ${isHisacContentSign}">			
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">備註</label> 
							<span>{{Remark}}</span>							
						</div>
					</div>	
					<div ng-show="messagePassButton">
						<div class="form_group">																
							<label for="Title"
								class="form_label form_label_search form_label_gray">標題(審核後)</label>
							<div>
								<input type="text" id="Title" name="Title" ng-model="Title"
									class="form-control form_label_search"
									placeholder="標題(審核後)" autocomplete="off"
									ng-required="true" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.required && editForm.Title.$dirty">
									<s:message code="pleaseEnter" />
									標題(審核後)
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.maxlength && editForm.Title.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>							
						</div>						
						</div>
					</div>	
					<div ng-show="messagePassButton">
						<div class="form_group">							
							<label for="SourceName"
								class="form_label form_label_search form_label_gray">資料來源(審核後)</label>
							<div>
								<input type="text" id="SourceName" name="SourceName"
									ng-model="SourceName" class="form-control form_label_search"
									placeholder="資料來源(審核後)"
									autocomplete="off" ng-maxlength="255" />
								<h5 class="text-danger"
									ng-show="editForm.SourceName.$error.maxlength && editForm.SourceName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="255" />
								</h5>
							</div>
						</div>
					</div>									
					<div ng-show="messagePassButton">
						<div class="form_group">							
							<label for="SourceLink"
								class="form_label form_label_search form_label_gray">來源網址(審核後)</label>
							<div>
								<input type="text" id="SourceLink" name="SourceLink"
									ng-model="SourceLink" class="form-control form_label_search"
									placeholder="來源網址(審核後)"
									autocomplete="off"
									ng-pattern="/^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/">
								<h5 class="text-danger"
									ng-show="editForm.SourceLink.$invalid && editForm.SourceLink.$dirty">
									來源網址(審核後)
									<s:message code="formatUrlError" />
								</h5>
							</div>
						</div>
					</div>												
					<div ng-show="messagePassButton">
						<div class="form_group">							
							<label for="Content" ng-show="messagePassButton"
								class="form_label form_label_search form_label_gray">內容(審核後)</label>
							<div ng-show="messagePassButton">
								<textarea type="text" id="Content" name="Content"
									ng-model="Content" class="form-control form_label_search"
									placeholder="內容(審核後)" rows="10"
									autocomplete="off" ng-requird="true"></textarea>
							</div>
						</div>
					</div>	
					<div ng-show="messagePassButton">
						<div class="form_group">
							<label for="IsBreakLine"
								class="form_label form_label_search form_label_gray">內容是否自動分行</label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsBreakLine" ng-init="IsBreakLine=true"
									on='<i class="far fa-fw fa-check-circle"></i>是'
									off='<i class="fas fa-fw fa-minus-circle"></i>否'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>	
						</div>
					</div>								
					<div ng-show="!messagePassButton">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label> <span ng-show="IsEnable == true"><s:message
									code="isEnableTrue" /></span> <span ng-show="IsEnable == false"><s:message
									code="isEanbleFalse" /></span>
						</div>
					</div>	
					<div ng-show="messagePassButton">
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
					<div ng-show="messagePassButton">
						<div class="form_group">							
							<label for="Remark"
								class="form_label form_label_search form_label_gray">備註</label>
							<div>
								<textarea type="text" id="Remark" name="Remark"
									ng-model="Remark" class="form-control form_label_search"
									placeholder="備註" rows="10"
									autocomplete="off" ng-requird="true"></textarea>
							</div>
						</div>
					</div>					
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s31Status" /></label> <span ng-show="Status == '1'"><s:message
									code="s31StatusOpt1" /></span> <span ng-show="Status == '2'"><s:message
									code="s31StatusOpt2" /></span> <span ng-show="Status == '3'"><s:message
									code="s31StatusOpt3" /></span> <span ng-show="Status == '4'"><s:message
									code="s31StatusOpt4" /></span> <span ng-show="Status == '5'"><s:message
									code="s31StatusOpt5" /></span> <span ng-show="Status == '6'"><s:message
									code="s31StatusOpt6" /></span>
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
											<img src="./api/s42/pic/download/{{Id}}/{{item.Id}}"
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
														href="./api/s42/attach/download/{{item.InformationManagementId}}/{{item.Id}}"
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
									<tr ng-show="CreateId<0">	
										<td>
											<span>新增情資分享</span>
											<span><i class="fas fa-fw fa-arrow-right"></i></span>
											<span>情資分享審核</span>
										</td>	
										<td>	<span>審核中</span></td>
										<td>
											<span ng-if="CreateId == -1">診所</span> 
											<span ng-if="CreateId == -2">廠商</span>
											<span ng-if="CreateId == -3">民眾</span>	
										</td>
										<td><span>{{CreateTime}}</span></td>			
										<td></td>																							
									</tr>
									<tr ng-repeat="item in MessageProcessLogData">
										<td><span ng-if="item.PreStatus == 1">新增情資分享</span> <span
											ng-if="item.PreStatus == 2">情資分享撤銷</span> <span
											ng-if="item.PreStatus == 3">情資分享審核</span> <span
											ng-if="item.PreStatus == 6">情資分享退回</span> <span><i
												class="fas fa-fw fa-arrow-right"></i></span> <span
											ng-if="item.Status == 2">情資分享撤銷</span> <span ng-if="item.Status == 3">情資分享審核</span> <span ng-if="item.Status == 5">已撤銷</span> <span ng-if="item.Status == 6">情資分享退回</span></td>
										<td><span ng-if="item.Status == 1"><s:message
													code="s31Status1" /></span> <span ng-if="item.Status == 2"><s:message
													code="s31Status2" /></span> <span ng-if="item.Status == 3"><s:message
													code="s31Status3" /></span> <span ng-if="item.Status == 4"><s:message
													code="s31Status4" /></span> <span ng-if="item.Status == 5"><s:message
													code="s31Status5" /></span> <span ng-if="item.Status == 6"><s:message
													code="s31Status6" /></span></td>
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
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '4')"
								ng-show="messagePassButton">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnPass" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '6')"
								ng-show="messageBackButton">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBack" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '2')"
								ng-show="messageUndoButtonButton">
								<i class="fas fa-fw fa-eraser"></i>
								<s:message code="btnReject" />
							</button>
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