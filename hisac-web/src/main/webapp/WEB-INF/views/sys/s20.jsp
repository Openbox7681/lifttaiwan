<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script>
	var globalReadDataFail = '<s:message code="globalReadDataFail" />';
	var btnClose = '<s:message code="btnClose" />';
	var btnSure = '<s:message code="btnSure" />';
	var globalSureDeleteItem = '<s:message code="globalSureDeleteItem" />';
	var globalDeleteDataFail = '<s:message code="globalDeleteDataFail" />';
	var globalInsertDataFail = '<s:message code="globalInsertDataFail" />';
	var globalUpdateDataFail = '<s:message code="globalUpdateDataFail" />';
	var dataLoading = '<s:message code="dataLoading" />';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s20.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.css">
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
		</div>			
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<b>Excel上傳</b>
			</h4>
		</div>
		<div class="row">
			<div style="display: inline-block" ngf-select="" id="file"
				ng-model="file" name="file" ngf-pattern=".xls,.xlsx"
				accept=".xls,.xlsx">
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇Excel上傳
				</button>
				<span>{{file.name}}</span>
			</div>
			<button class="btn btn-success" ng-click="importExcel()"
				ng-show="file.name!=null">
				<i class="fas fa-sync fa-spin"></i> 匯入Excel
			</button>
		</div>
	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>