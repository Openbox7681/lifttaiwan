<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/ires.js"></script>
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
							<label for="orgName"
								class="form_label form_label_api form_label_gray col-md-6"><s:message
									code="orgName" /></label>
							<span >{{orgName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="orgCode"
								class="form_label form_label_api form_label_gray col-md-6"><s:message
									code="orgCode" /></label>
							<span class="col-md-6">{{orgCode}}</span>
					
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="agencyKey"
								class="form_label form_label_api form_label_gray col-md-6"><s:message
									code="agencyKey" /></label>
							<span class="col-md-6">{{agencyKey}}</span>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="validYears"
								class="form_label form_label_api form_label_gray" ><s:message
									code="validYears" /></label>
							<span>{{vaildYears}}</span>
					
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for=applyDate
								class="form_label form_label_api form_label_gray"><s:message
									code="applyDate" /></label>
							<span>{{applyDate}}</span>
					
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="expiryDate"
								class="form_label form_label_api form_label_gray"><s:message
									code="expiryDate" /></label>
							<span>{{expiryDate}}</span>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for="dataState"
								class="form_label form_label_api form_label_gray"><s:message
									code="dataState" /></label>
							<span>{{dataState}}</span>
						</div>
					</div>	
					
				</div>	
					
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="updateData()"
							>
							<i class="fas fa-fw fa-save"></i>
							<s:message code="apiKeyUpdate" />
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