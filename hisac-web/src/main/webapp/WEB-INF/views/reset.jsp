<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script>
	var loginReset = '<s:message code="loginReset" />';
	var loginResetFail = '<s:message code="loginResetFail" />';
	var btnClose = '<s:message code="btnClose" />';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/reset.js"></script>
<body ng-app="myApp">
	<%@ include file="./include/head_navbar.jsp"%>
	<div ng-controller="getAppController" class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 shadow_board">
				<h4 class="form_heading form_heading_gray">
					<big><i class="fas fa-fw fa-user-circle fa-lg"></i></big>
					<s:message code="globalResetCode" />
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
									autofocus ng-required="true"><input type="hidden"
									id="code" name="code" ng-model="code">
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
							<label for="newCode" class="form_label form_label_gray"><s:message
									code="loginNewCode" /></label>
							<div class="form_input">
								<input type="password" id="newCode" name="newCode"
									ng-model="newCode"
									ng-pattern="regex"
									class="form-control"
									placeholder="<s:message code="loginNewCode" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="!myForm.newCode.$error.required && myForm.newCode.$invalid && myForm.newCode.$dirty">
									密碼強度不足(長度必須至少{{passwordLength}}個字元，並且必須包含<u>英文字母</u>及<u>數字</u>及<u>非英文字母字元[ex.!、$、#、%]</u>三種)
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="newCodeAgain" class="form_label form_label_gray"><s:message
									code="loginNewCodeAgain" /></label>
							<div class="form_input">
								<input type="password" id="newCodeAgain" name="newCodeAgain"
									ng-model="newCodeAgain" class="form-control"
									placeholder="<s:message code="loginNewCodeAgain" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.newCodeAgain.$error.required && myForm.newCodeAgain.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="loginNewCodeAgain" />
								</h5>
								<h5 class="text-danger"
									ng-show="!myForm.newCodeAgain.$error.required && myForm.newCodeAgain.$dirty"
									ng-if="!(newCode === newCodeAgain)">
									<s:message code="loginNewCodeNotTheSame" />
								</h5>
							</div>
						</div>
					</div>
					<div class="help-block"></div>
					<div id="divSubmit" class="btn-group btn-group-justified"
						role="group">
						<a class="btn btn_custom btn_pink" type="button"
							ng-click="!myForm.$valid || !(newCode === newCodeAgain) || reset()"
							ng-disabled="!myForm.$valid || !(newCode === newCodeAgain)">
							<i class="fas fa-fw fa-key"></i> <s:message code="loginResetCode" />
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