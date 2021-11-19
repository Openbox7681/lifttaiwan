<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/edit_org_profile.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.twzipcode.min.js"></script>	
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divEdit" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="editForm">
					<div>
						<div class="form_group">
							<label for="Name"
								class="form_label form_label_search form_label_gray">組織名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control" ng-maxlength="50" placeholder="組織名稱"
									autocomplete="off" ng-required="true" ng-disabled="true">
								<h5 class="text-danger"
									ng-show="editForm.Name.$error.required && editForm.Name.$dirty">
									<s:message code="pleaseEnter" />
									組織名稱
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
								class="form_label form_label_search form_label_gray">醫事機構代碼</label>
							<div class="form_input form_input_search">
								<input type="text" id="Code" name="Code" ng-model="Code"
									class="form-control"
									placeholder="<s:message code="alertTypeCode" />"
									autocomplete="off" ng-required="true" ng-maxlength="16" ng-disabled="true" />
								<h5 class="text-danger"
									ng-show="editForm.Code.$error.required && editForm.Code.$dirty">
									<s:message code="pleaseEnter" />
									醫事機構代碼
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
								class="form_label form_label_search form_label_gray">公司電話號碼</label>
							<div class="form_input form_input_search">
								<input type="text" id="Tel" name="Tel" ng-model="Tel"
									class="form-control" placeholder="公司電話號碼" autocomplete="off"
									ng-maxlength="15"
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
								class="form_label form_label_search form_label_gray">傳真號碼</label>
							<div class="form_input form_input_search">
								<input type="text" id="Fax" name="Fax" ng-model="Fax"
									class="form-control" placeholder="傳真號碼" autocomplete="off"
									ng-maxlength="15"
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
					
					<div ng-show="false">
						<div class="form_group">
							<label for="OrgType"
								class="form_label form_label_search form_label_gray">類別</label>
							<div class="form_input form_input_search">
								<select id="OrgType" name="OrgType" ng-model="OrgType" ng-required="true"
									class="form-control col-xs-6" ng-disabled="true">
									<option value="0">0_admin</option>
									<option value="1">1_HISAC</option>
									<option value="2">2_權責單位</option>
									<option value="3">3_會員機構</option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.OrgType.$error.required && editForm.OrgType.$dirty">
									<s:message code="pleaseEnter" />
									務必選擇一個項目
								</h5>
							</div>
						</div>
					</div>
					<div ng-show="false">
						<div class="form_group">
							<label for="AuthType"
								class="form_label form_label_search form_label_gray">權責類型</label>
							<div class="form_input form_input_search">
								<select id="AuthType" name="AuthType" ng-model="AuthType" ng-required="true"
									class="form-control col-xs-6" ng-disabled="true">
									<option value="0">0_非權責單位</option>
									<option value="1">1_業務管理機關</option>
									<option value="2">2_上級機關</option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.AuthType.$error.required && editForm.AuthType.$dirty">
									<s:message code="pleaseEnter" />
									務必選擇一個項目
								</h5>
							</div>
						</div>
					</div>
					
					<div class="form_group">
						<label class="form_label form_label_search form_label_gray" for="file">地址</label>
						<div class="form_input form_input_search">
								<label for="orgAddress" class="sr-only">
									<s:message code="orgAddress" />
								</label>
								<div class="input-group">
									<div id="twzipcode"></div>
									<script>
										$("#twzipcode").twzipcode({
											"zipcodeIntoDistrict" : true,
											"css" : [
												"city form-control",
												"town form-control"
											],
											"countyName" : "city",
											"districtName" : "town"
										});
									</script>
									<input type="text" id="orgAddress" name="orgAddress" ng-model="Address" ng-maxlength="256" class="form-control"
										placeholder="<s:message code="orgType4Address" />" autocomplete="off" ng-required="true">
									<h5 class="text-danger" ng-show="editForm.orgCity.$error.required && editForm.orgCity.$dirty">
										縣市不得為空
									</h5>
									<h5 class="text-danger" ng-show="editForm.orgAddress.$error.required && editForm.Fax.$dirty">
										地址不得為空
									</h5>
									<h5 class="text-danger" ng-show="editForm.orgAddress.$error.maxlength && editForm.Fax.$dirty">
										地址不可超過20個字元
									</h5>
									<input type="text" id="orgCity" name="orgCity" ng-model="City" ng-maxlength="10" class="form-control"
										placeholder="<s:message code="orgCity" />" autocomplete="off" ng-required="true" style="display: none;">
									<input type="text" id="orgTown" name="orgTown" ng-model="Town" ng-maxlength="10" class="form-control"
										placeholder="<s:message code="orgTown" />" autocomplete="off" ng-required="true" style="display: none;">
								</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="BossName"
								class="form_label form_label_search form_label_gray"><s:message
									code="bossName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="BossName" name="BossName" ng-model="BossName"
									class="form-control"
									placeholder="<s:message code="bossName" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
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
									placeholder="<s:message code="bossEmail" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
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
								<input type="text" id="BossMobilePhone"
									name="BossMobilePhone" ng-model="BossMobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
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
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!editForm.$valid || updateData()"
							ng-disabled="!editForm.$valid">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnUpdate" />
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