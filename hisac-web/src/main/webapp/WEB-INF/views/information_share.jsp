<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="./include/head_index.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/information_share.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/angular-bootstrap-toggle@0.4.0/dist/angular-bootstrap-toggle.min.css">
<script src="https://cdn.jsdelivr.net/npm/angular-bootstrap-toggle@0.4.0/dist/angular-bootstrap-toggle.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/ng-file-upload/ng-file-upload-shim.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/ng-file-upload/ng-file-upload.min.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="./include/head_navbar.jsp"%>
	<div class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_red">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b>情資分享</b>
			</h4>
		</div>
	
	<div class="container">
		<div class="row" ng-show=!btnUpd>
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnCreate" /></b>
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
								class="form_label form_label_search form_label_gray">類別</label>
							<div class="form_input form_input_search">						
								<select id="Type" name="Type" ng-model="Type"							
									class="form-control" ng-required="true">
									<option value=0><s:message
											code="pleaseSelect" />類別</option>
									<option value=-1>診所</option>
									<option value=-2>廠商</option>
									<option value=-3>民眾</option>
								</select>
								<h5 class="text-danger"
									ng-show="Type=='0'">
									<s:message code="pleaseSelect" />
									類別
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray">姓名</label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="姓名" autocomplete="off"
									ng-required="true" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									姓名
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
								class="form_label form_label_search form_label_gray">聯絡方式</label>
							<div class="form_input form_input_search">
								<input type="text" id="Email" name="Email" ng-model="Email"
									class="form-control"
									placeholder="聯絡方式" autocomplete="off"
									ng-required="true" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="editForm.Email.$error.required && editForm.Email.$dirty">
									<s:message code="pleaseEnter" />
									聯絡方式
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Email.$error.maxlength && editForm.Email.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
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
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" ng-show="!btnUpd"
						ng-disabled="!(editForm.$valid && Type!='0')"
							ng-click="createData()">
							<i class="fas fa-fw fa-save"></i>
							下一步
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
							>
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
							<img src="./public/api/information_share/pic/download/{{Id}}/{{item.Id}}"
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
								<p class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteImageData(item.Id);">
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
							>
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
										href="./public/api/information_share/attach/download/{{item.InformationManagementId}}/{{item.Id}}"
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
								<p class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteAttachmentData(item.Id);">
										<i class="far fa-fw fa-trash-alt"></i>
										<s:message code="btnDelete" />
									</button>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="submit_bar">				
				</div>
			</div>
		</div>
	</div>
	<button class="btn btn_custom btn_blue" ng-show="btnUpd"
							ng-click="!editForm.$valid || updateData()"
							ng-disabled="!editForm.$valid">
							<i class="fas fa-fw fa-check"></i>
							<s:message code="btnSubmit" />
						</button>
	</div>	
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>