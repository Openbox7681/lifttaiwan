<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script>
	var loginLogin = '<s:message code="loginLogin" />';
	var loginFail = '<s:message code="loginFail" />';
	var btnClose = '<s:message code="btnClose" />';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/index.js"></script>
<body ng-app="myApp">
	<!--[if lt IE 9]>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1 class="text-danger"><s:message code="loginNotSupportIE9" /></h1>
			</div>
			<div class="col-xs-12">
				<h3 class="text-warning"><s:message code="loginSupportBrowsers" /></h3>
			</div>
			<h5>&nbsp;</h5>
			<p class="col-xs-12 col-sm-6 col-md-3 text-center">
				<a href="https://www.google.com/chrome/"
					class="btn btn-primary btn-lg col-xs-12"> <s:message code="loginDownloadBrowser" /> Chrome</a>
			</p>
			<p class="col-xs-12 col-sm-6 col-md-3 text-center">
				<a href="https://www.mozilla.org/"
					class="btn btn-primary btn-lg btn-lg col-xs-12"> <s:message code="loginDownloadBrowser" /> Mozilla
					Firefox</a>
			</p>
			<p class="col-xs-12 col-sm-6 col-md-3 text-center">
				<a href="https://www.apple.com/tw/safari/"
					class="btn btn-primary btn-lg btn-lg col-xs-12"> <s:message code="loginDownloadBrowser" /> Safari for Mac</a>
			</p>
			<p class="col-xs-12 col-sm-6 col-md-3 text-center">
				<a
					href="https://support.microsoft.com/zh-tw/help/17621/internet-explorer-downloads"
					class="btn btn-primary btn-lg btn-lg col-xs-12"> <s:message code="loginDownloadBrowser" /> IE for Windows</a>
			</p>
		</div>
	</div>
	<![endif]-->
	<!--[if gte IE 9]><!-->
	<%@ include file="./include/head_navbar.jsp"%>
	<div ng-controller="getAppController" class="container">
		<div class="container">
			<div class="row ad">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/index-ad.jpg"
					class="img-responsive">

			</div>
			<br />
			<div id="divLogin" class="row">
				<div class="col-sm-offset-3 col-sm-6 shadow_board">
					<h4 class="form_heading form_heading_gray">
						<big><i class="fas fa-fw fa-user-circle fa-lg"></i></big>
						<s:message code="loginTitle" />
					</h4>
					<form name="myForm">
						<div>
							<div class="form_group">
								<label for="userName" class="form_label form_label_gray"><s:message
										code="loginName" /></label>
								<div class="form_input">
									<input type="text" id="userName" name="userName"
										ng-model="userName" class="form-control"
										placeholder="<s:message code="loginName" />"
										autocomplete="off" autofocus ng-required="true"
										ng-disabled="showTwoFactor">
									<h5 class="text-danger"
										ng-show="myForm.userName.$invalid && myForm.userName.$dirty">
										<s:message code="pleaseEnter" />
										<s:message code="loginName" />
									</h5>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="userCode" class="form_label form_label_gray"><s:message
										code="loginCode" /></label>
								<div class="form_input">
									<input type="password" id="userCode" name="userCode"
										ng-model="userCode" class="form-control"
										placeholder="<s:message code="loginCode" />"
										autocomplete="off" ng-required="true"
										ng-disabled="showTwoFactor">
									<h5 class="text-danger"
										ng-show="myForm.userCode.$invalid && myForm.userCode.$dirty">
										<s:message code="pleaseEnter" />
										<s:message code="loginCode" />
									</h5>
								</div>
							</div>
						</div>
						<div id="divgtpCode" ng-show="showTwoFactor"
							style="display: none;">
							<div class="form_group">
								<label for="gtpCode" class="form_label"></label>
								<div class="form_input">
									<div class="g-recaptcha"
										data-sitekey="<c:out
								value="${googleRecaptchaSiteKey}" />"></div>
								</div>
							</div>
						</div>
						<!-- 
					<div id="divotpCode" ng-show="showTwoFactor" style="display: none;">
						<div class="form_group">
							<label for="otpCode" class="form_label form_label_gray"><s:message
									code="loginOtp" /></label>
							<div class="form_input">
								<input type="password" id="otpCode" name="otpCode"
									ng-model="otpCode" class="form-control number_password"
									placeholder="<s:message code="loginOtp" />" autocomplete="off"
									pattern="[0-9]*" inputmode="numeric"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8"
									ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.otpCode.$invalid && myForm.otpCode.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="loginOtp" />
								</h5>
							</div>
						</div>
					</div>
					-->
						<div class="help-block"></div>
						<div id="divLogin" ng-hide="showTwoFactor"
							class="btn-group btn-group-justified" role="group">
							<a class="btn btn_custom btn_blue" type="button"
								ng-click="!myForm.userName.$valid || !myForm.userCode.$valid || login()"
								ng-disabled="!myForm.userName.$valid || !myForm.userCode.$valid">
								<i class="fas fa-fw fa-sign-in-alt"></i> <s:message
									code="loginSubmit" />
							</a><a href="<c:out value="${pageContext.request.contextPath}" />/forgot"
								class="btn btn_custom btn_pink"><i
								class="fas fa-fw fa-question-circle"></i> <s:message
									code="globalForgotCode" /></a> <a
								href="<c:out value="${pageContext.request.contextPath}" />/sign_up"
								class="btn btn_custom btn_green"><i
								class="fas fa-fw fa-user-circle"></i> <s:message
									code="globalSignUp" /></a>
						</div>
						<div id="divVerify" ng-show="showTwoFactor"
							class="btn-group btn-group-justified" role="group"
							style="display: none;">
							<a class="btn btn_custom btn_blue" type="button"
								ng-click="!myForm.userName.$valid || !myForm.userCode.$valid || login()"
								ng-disabled="!myForm.userName.$valid || !myForm.userCode.$valid">
								<!-- ng-click="!myForm.$valid || login()" ng-disabled="!myForm.$valid"> -->
								<i class="fas fa-fw fa-sign-in-alt"></i> <s:message
									code="loginVerify" />
							</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
	<!--<![endif]-->
</body>
</html>