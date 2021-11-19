<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script>
	var globalResetCodeSuccess = '<s:message code="globalResetCodeSuccess" />';
	var globalResetCodeFail = '<s:message code="globalResetCodeFail" />';
</script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/change_code.js"></script>
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
				<form name="myForm" method="post" autocomplete="off">
					<div>
						<div class="form_group">
							<label for="oldCode" class="form_label form_label_gray"><s:message
									code="loginOldCode" /></label>
							<div class="form_input">
								<input type="password" id="oldCode" name="oldCode"
									ng-model="oldCode" class="form-control"
									placeholder="<s:message code="loginOldCode" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.oldCode.$error.required && myForm.oldCode.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="loginOldCode" />
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
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!myForm.$valid || updateData()"
							ng-disabled="!myForm.$valid">
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