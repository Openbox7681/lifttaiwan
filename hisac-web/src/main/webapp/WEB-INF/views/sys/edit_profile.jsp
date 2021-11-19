<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/edit_profile.js"></script>
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
				<form name="myForm">
					<div>
						<div class="form_group">
							<label for="memberName"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberName" /></label>
							<div class="form_input form_input_pdpl">
								<input type="text" id="memberName" name="memberName"
									ng-model="memberName" ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberName" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.memberName.$error.required && myForm.memberName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberName" />
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
									code="memberEmail" /></label>
							<div class="form_input form_input_pdpl">
								<input type="email" id="memberEmail" name="memberEmail"
									ng-model="memberEmail" ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="myForm.memberEmail.$error.required && myForm.memberEmail.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="memberEmail" />
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
							<label for="memberSpareEmail"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberSpareEmail" /></label>
							<div class="form_input form_input_pdpl">
								<input type="email" id="memberSpareEmail" name="memberSpareEmail"
									ng-model="memberSpareEmail" ng-maxlength="128" class="form-control"
									placeholder="<s:message code="memberSpareEmail" />"
									autocomplete="off" >
								<h5 class="text-danger"
									ng-show="myForm.memberSpareEmail.$invalid && myForm.memberSpareEmail.$dirty">
									<s:message code="memberEmailFormat" />
								</h5>
							</div>
						</div>
					</div>
					
					
					
					<div>
						<div class="form_group">
							<label for="memberMobilePhone"
								class="form_label form_label_pdpl form_label_gray"><s:message
									code="memberMobilePhone" /></label>
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
							<label for="CityPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberCityPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="memberCityPhone" name="memberCityPhone"
									ng-model="memberCityPhone" class="form-control"
									placeholder="<s:message code="memberCityPhone" />"
									autocomplete="off" ng-maxlength="16"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />

								<h5 class="text-danger"
									ng-show="myForm.memberCityPhone.$error.maxlength && myForm.memberCityPhone.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="16" />
								</h5>
								<h5 class="validation-msg"
									ng-show="myForm.memberCityPhone.$invalid && myForm.memberCityPhone.$dirty">
									<s:message code="phoneFormatErr" />
								</h5>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="FaxPhone"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberFaxPhone" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="memberFaxPhone" name="memberFaxPhone"
									ng-model="memberFaxPhone" class="form-control"
									placeholder="<s:message code="memberFaxPhone" />"
									autocomplete="off" ng-maxlength="16"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />

								<h5 class="validation-msg"
									ng-show="!myForm.memberFaxPhone.$error.required && myForm.memberFaxPhone.$invalid && myForm.memberFaxPhone.$dirty">
									<!-- ng-show="myForm.memberFaxPhone.$error.pattern && myForm.memberFaxPhone.$dirty"> -->
									<s:message code="phoneFormatErr" />
								</h5>
								<h5 class="text-danger"
									ng-show="myForm.memberFaxPhone.$error.maxlength && myForm.memberFaxPhone.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="16" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Address"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberAddress" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="memberAddress" name="memberAddress"
									ng-model="memberAddress" class="form-control"
									placeholder="<s:message code="memberAddress" />"
									autocomplete="off" ng-maxlength="256" />
								<h5 class="text-danger"
									ng-show="myForm.memberAddress.$error.maxlength && myForm.memberAddress.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="256" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Department"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberDepartment" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="memberDepartment" name="memberDepartment"
									ng-model="memberDepartment" class="form-control"
									placeholder="<s:message code="memberDepartment" />"
									autocomplete="off" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="myForm.memberDepartment.$error.maxlength && myForm.memberDepartment.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray"><s:message
									code="memberPosition" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="memberTitle" name="memberTitle" ng-model="memberTitle"
									class="form-control"
									placeholder="<s:message code="memberPosition" />"
									autocomplete="off" ng-maxlength="128" />
								<h5 class="text-danger"
									ng-show="myForm.memberTitle.$error.maxlength && myForm.memberTitle.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					
					<c:if
						test="${ciLevel==1 || ciLevel==2}">
					<div>											
						<div class="form_group">
							<label for="Subscribe"
								class="form_label form_label_search form_label_gray">訂閱</label>
							<div class="form_input form_input_search">
								<div class="checkbox" ng-repeat="item in SubscribeData">								      
									<label> <input type="checkbox" ng-model="item.Flag">
										<span class="lbl">{{item.Name}}</span>										      
									</label>								
								</div>
								<label>
									<font color="red">如需取消訂閱請不要勾選</font>
								</label>
							</div>							
						</div>
					</div>
					</c:if>
					
					
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