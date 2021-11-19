<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script>
	var memberSignUp = '<s:message code="memberSignUp" />';
	var memberSignUpFail = '<s:message code="memberSignUpFail" />';
	var btnClose = '<s:message code="btnClose" />';
	var noResultsMessage = '<s:message code="globalNoResultMessage" />';
	var signToolTip = '';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sign_up.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.twzipcode.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/np-autocomplete/np-autocomplete.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/np-autocomplete/np-autocomplete.min.css" />
<body ng-app="myApp">
	<%@ include file="./include/head_navbar.jsp"%>
	<div ng-controller="getAppController" class="container">
		<div class="row">
			<div id="sign"
				class="col-xs-12 col-sm-offset-1 col-sm-10 shadow_board">
				<h4 class="form_heading form_heading_gray">
					<big><i class="fas fa-fw fa-user-circle fa-lg"></i></big>
					<s:message code="globalSignUp" />
				</h4>
				<form name="myForm">
					<div>
						<input type="text" id="orgCode" name="orgCode" ng-model="orgCode"
							ng-required="true" style="display: none;"><input
							type="text" id="orgName" name="orgName" ng-model="orgName"
							style="display: none;">
						<div class="form_group form_group_pdpl" np-autocomplete="options"
							np-input-model="orgName" ng-model="orgCode" np-auto="autoModel">
							<label for="orgCode"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="orgCode" /></label>
							<div class="form_input form_input_pdpl">
								<input type="text" id="org" name="org" ng-model="org"
									class="form-control"
									placeholder="<s:message code="orgType4Code" />/<s:message code="orgType4Name" />"
									autocomplete="off" autofocus ng-required="true">
							</div>
						</div>
						<h5 class="text-danger valid_fix_pdpl"
							ng-show="myForm.orgCode.$error.required && myForm.org.$dirty">
							<s:message code="pleaseEnter" />
							<s:message code="orgType4Code" />
							/
							<s:message code="orgType4Name" />
						</h5>
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
					
					
				<%-- 	<div>
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
							<label for="isPublicOffice"
								class="form_label form_label_search form_label_gray"><s:message
									code="isPublicOffice" /></label>
							<div class="form_input form_input_search">
								<select id="IsPublic" name="IsPublic"
									ng-model="IsPublic" class="form-control">
									<option ng-value="true"><s:message code="isPublicOfficeTrue" /></option>
									<option ng-value="false"><s:message code="isPublicOfficeFalse" /></option>
									<option value=""><s:message code="pleaseSelect" /><s:message
											code="isPublicOffice" /></option>
								</select>
							</div>
						</div>
					</div> --%>
					
					
					
					
					
					
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
							<label for="memberAccount"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberAdminAccount" /></label>
							<div class="form_input form_input_pdpl">
								<input type="text" id="memberAccount" name="memberAccount"
									ng-model="memberAccount"
									ng-model-options="{ updateOn: 'default' , allowInvalid:'true'}"
									ng-maxlength="64" ng-pattern="/^[a-zA-Z][a-zA-Z0-9]{3,63}$/"
									ng-keyup="checkMemberAccount()" class="form-control"
									onpaste="return false" oncopy="return false"
									oncut="return false" oncontextmenu="return false"
									ng-copy="$event.preventDefault()"
									ng-cut="$event.preventDefault()"
									ng-paste="$event.preventDefault()"
									placeholder="<s:message code="memberAdminAccount" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.memberAccount.$error.required && myForm.memberAccount.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberAdminAccount" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.memberAccount.$error.required && myForm.memberAccount.$invalid && myForm.memberAccount.$dirty">
									<s:message code="memberAccountFormat" />
								</h5>
								<h5 class="text-danger" ng-show="memberAccountVerifyFail">
									<s:message code="memberAccountExist" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="memberName"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberAdminName" /></label>
							<div class="form_input form_input_pdpl">
								<input type="text" id="memberName" name="memberName"
									ng-model="memberName" ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberName" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.memberName.$error.required && myForm.memberName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberAdminName" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.memberName.$error.required && myForm.memberName.$invalid && myForm.memberName.$dirty">
									<s:message code="memberNameFormat" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="memberEmail"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberAdminEmail" /></label>
							<div class="form_input form_input_pdpl">
								<input type="email" id="memberEmail" name="memberEmail"
									ng-model="memberEmail" ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off" ng-required="true" ng-disabled="true">
								<h5 class="text-danger"
									ng-show="myForm.memberEmail.$error.required && myForm.memberEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberAdminEmail" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.memberEmail.$error.required && myForm.memberEmail.$invalid && myForm.memberEmail.$dirty">
									<s:message code="memberEmailFormat" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="memberMobilePhone"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberAdminMobilePhone" /></label>
							<div class="form_input form_input_pdpl">
								<input type="text" id="memberMobilePhone"
									name="memberMobilePhone" ng-model="memberMobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="memberMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="myForm.memberMobilePhone.$invalid && myForm.memberMobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="orgBossName"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="bossName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="orgBossName" name="orgBossName" ng-model="orgBossName"
									class="form-control"
									placeholder="<s:message code="bossName" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="myForm.orgBossName.$error.required && myForm.orgBossName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="bossName" />
								</h5>
								<h5 class="text-danger"
									ng-show="myForm.orgBossName.$error.maxlength && myForm.orgBossName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="orgBossEmail"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="bossEmail" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="orgBossEmail" name="orgBossEmail"
									ng-model="orgBossEmail" class="form-control"
									ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i"
									placeholder="<s:message code="bossEmail" />"
									autocomplete="off" ng-required="true" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="myForm.orgBossEmail.$error.required && myForm.orgBossEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberEmail" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.orgBossEmail.$error.required && myForm.orgBossEmail.$invalid && myForm.orgBossEmail.$dirty">
									<s:message code="memberEmailFormat" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="orgBossMobilePhone"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="bossMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="orgBossMobilePhone"
									name="orgBossMobilePhone" ng-model="orgBossMobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="bossMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="myForm.orgBossMobilePhone.$invalid && myForm.orgBossMobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="orgPrincipalName"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="principalName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="orgPrincipalName" name="orgPrincipalName" ng-model="orgPrincipalName"
									class="form-control"
									placeholder="<s:message code="principalName" />"
									autocomplete="off" ng-maxlength="128">
								<h5 class="text-danger"
									ng-show="myForm.orgPrincipalName.$error.maxlength && myForm.orgPrincipalName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="orgPrincipalMobilePhone"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="principalMobilePhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="orgPrincipalMobilePhone"
									name="orgPrincipalMobilePhone" ng-model="orgPrincipalMobilePhone"
									ng-maxlength="10" ng-minlength="10" ng-pattern="/^09\d{8}$/"
									class="form-control"
									placeholder="<s:message code="principalMobilePhone" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8">
								<h5 class="text-danger"
									ng-show="myForm.orgPrincipalMobilePhone.$invalid && myForm.orgPrincipalMobilePhone.$dirty">
									<s:message code="memberMobilePhoneFormat" />
								</h5>
								<h5 class="text-danger">
									<s:message code="principalMobilePhoneDesc" />
								</h5>
							</div>
						</div>
					</div>
					<div class="help-block"></div>
					<div>
						<div class="form_group text-center">
							<i class="far fa-fw fa-check-square" ng-show="pdplIsAgree"></i><i
								class="far fa-fw fa-square" ng-show="!pdplIsAgree"></i><label
								for="pdplIsAgree"><s:message code="pdplIsAgree" /> </label> <a
								href="#" ng-click="pdplShow()"><s:message code="pdplShow" /></a>
						</div>
					</div>
					<div class="help-block"></div>
					<div id="divSubmit" class="btn-group btn-group-justified"
						role="group">
						<a class="btn btn_custom btn_green" type="button"
							ng-click="!myForm.$valid || memberAccountVerifyFail  || !pdplIsAgree || signup()"
							ng-disabled="!myForm.$valid || memberAccountVerifyFail  || !pdplIsAgree">
							<i class="fas fa-fw fa-user-plus"></i> <s:message
								code="loginSignUp" />
						</a>
					</div>
					<div class="help-block"></div>
					<div>
						<div class="form_group text-center">
							<a
								href="<c:out value="${pageContext.request.contextPath}" />/resources/doc/hisac_sign_up_user_guide.pdf"
								target="_doc" class="pull-right"><i class="fas fa-download"></i>
								<s:message code="globalSignUpUserGuide" /> </a> <br />&nbsp;<br />
						</div>
					</div>
					<div class="help-block"></div>
				</form>
			</div>
			<div id="pdpl" style="display: none;"
				class="col-xs-12 col-sm-offset-1 col-sm-10 shadow_board">
				<h4 class="form_heading form_heading_gray">
					<i class="fas fa-fw fa-user-circle fa-lg"></i>
					<s:message code="pdplTitle" />
				</h4>
				<div class="wall wall_pdpl">
					<s:message code="pdplWord" />
				</div>
				<div class="help-block"></div>
				<form name="myFormPdpl" class="text-center">
					<div id="divSubmit" class="btn-group btn-group-justified"
						role="group">
						<a id="pdplAgree" class="btn btn_custom btn_green" type="button"
							ng-click="pdplAgree()"> <i class="fas fa-fw fa-check"></i> <s:message
								code="pdplAgree" />
						</a><a id="pdplDisgree" class="btn btn_custom btn_pink" type="button"
							ng-click="pdplDisagree()"> <i class="fas fa-fw fa-ban"></i> <s:message
								code="pdplDisagree" />
						</a>
					</div>
				</form>
			</div>
		</div>

		<div id="verifyModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form name="verifyForm">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close" onclick="window.location.href = './'">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">電子郵件驗證</h4>
							<p>&nbsp;</p>							
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="vEmail"><s:message code="memberAdminEmail" /></label>
								<input type="email" id="vEmail" name="vEmail" ng-model="vEmail"
									ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off" ng-required="true" ng-disabled="verifyEmail">
								<h5 class="text-danger"
									ng-show="verifyForm.vEmail.$error.required && verifyForm.vEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberAdminEmail" />
								</h5>
								<h5 class="text-danger"
									ng-show="!verifyForm.vEmail.$error.required && verifyForm.vEmail.$invalid && verifyForm.vEmail.$dirty">
									<s:message code="memberEmailFormat" />
								</h5>
							</div>
							<div class="form-group" ng-show="verifyEmail">
								<label for="vCode">驗證碼</label> <input type="password"
									class="form-control" id="vCode" name="vCode" ng-model="vCode"
									placeholder="請輸入驗證碼" ng-disabled="!verifyEmail"
									ng-required="verifyEmail">
								<h5 class="text-danger"
									ng-show="verifyForm.vCode.$error.required && verifyForm.vCode.$dirty">
									請輸入驗證碼</h5>
							</div>
						</div>
						<div class="modal-footer">
							<div>
								<button type="button" class="btn btn-primary"
									ng-disabled="verifyEmail || !verifyForm.$valid"
									ng-show="!verifyEmail" ng-click="verifyMail()">發送驗證信</button>
								<button type="button" class="btn btn-primary"
									ng-disabled="!verifyEmail || !verifyForm.$valid"
									ng-show="verifyEmail" ng-click="verifyCode()">驗證</button>
							</div>
							<div>
								<br />&nbsp;<br />
								<a
									href="<c:out value="${pageContext.request.contextPath}" />/resources/doc/hisac_sign_up_user_guide.pdf"
									target="_doc"><i class="fas fa-download"></i>
									<s:message code="globalSignUpUserGuide" /> </a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>