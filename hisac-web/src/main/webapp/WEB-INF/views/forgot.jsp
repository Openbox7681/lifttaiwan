<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script>
	var loginResend = '<s:message code="loginResend" />';
	var loginResendFail = '<s:message code="loginResendFail" />';
	var btnClose = '<s:message code="btnClose" />';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/forgot.js"></script>
<body ng-app="myApp">
	<%@ include file="./include/head_navbar.jsp"%>
	<div ng-controller="getAppController" class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 shadow_board">
				<h4 class="form_heading form_heading_gray">
					<big><i class="fas fa-fw fa-user-circle fa-lg"></i></big>
					<s:message code="globalForgotCode" />
				</h4>
				<form name="myForm">
					<div>
						<div class="form_group">
							<label for="userName" class="form_label form_label_gray"><s:message
									code="loginName" /></label>
							<div class="form_input">
								<input type="text" id="userName" name="userName"
									ng-model="userName" class="form-control"
									placeholder="<s:message code="loginName" />" autocomplete="off"
									autofocus ng-required="true">
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
							<label for="userEmail" class="form_label form_label_gray"><s:message
									code="loginSignUpEmail" /></label>
							<div class="form_input">
								<input type="email" id="userEmail" name="userEmail"
									ng-model="userEmail" class="form-control"
									placeholder="<s:message code="loginSignUpEmail" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.userEmail.$invalid && myForm.userEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="loginSignUpEmail" />
								</h5>
							</div>
						</div>
					</div>
					<div class="help-block"></div>
					<div id="divSubmit" class="btn-group btn-group-justified"
						role="group">
						<a class="btn btn_custom btn_pink" type="button"
							ng-click="!myForm.$valid || resend()"
							ng-disabled="!myForm.$valid"> <i
							class="fas fa-fw fa-envelope"></i> <s:message
								code="loginResendEmail" />
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>