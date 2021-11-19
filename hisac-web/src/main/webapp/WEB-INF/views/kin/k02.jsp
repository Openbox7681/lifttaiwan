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
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k02.js"></script>
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
				<b>
					資通安全實地稽核項目檢核表格式
				</b>
			</h4>
		</div>
		<div class="row">
			<div style="display: inline-block" ngf-select="" id="fileCheckList"
				ng-model="fileCheckList" name="fileCheckList" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                ngf-max-size="100MB" ngf-min-height="100">
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇資通安全實地稽核項目檢核表格式上傳
				</button>
				<span>{{fileCheckList.name}}</span>
			</div>
			<button class="btn btn-success" ng-click="importFileCheckList()"
				ng-show="fileCheckList.name!=null">
				 匯入資通安全實地稽核項目檢核表格式
			</button>
		</div>
		<div ng-show="IsCheckListExist" class="row">
			<a href="./api/k02/download/CheckList">下載資通安全實地稽核項目檢核表格式.docx</a>
		</div>
		<div>
			<div class="form_group"></div>
		</div>
		<br>								
		<div class="help-block"></div>		
		
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<b>受稽機關現況調查表格式</b>
			</h4>
		</div>
		<div class="row">
			<div style="display: inline-block" ngf-select="" id="fileQuestionnaire"
				ng-model="fileQuestionnaire" name="fileQuestionnaire" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                ngf-max-size="100MB" ngf-min-height="100">
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇受稽機關現況調查表格式上傳
				</button>
				<span>{{fileQuestionnaire.name}}</span>
			</div>
			<button class="btn btn-success" ng-click="importFileQuestionnaire()"
				ng-show="fileQuestionnaire.name!=null">
				匯入受稽機關現況調查表格式
			</button>
		</div>
		<div ng-show="IsQuestionnaireExist" class="row">
			<a href="./api/k02/download/Questionnaire">下載受稽機關現況調查表格式.docx</a>
		</div>
		<div>
			<div class="form_group"></div>
		</div>
		<br>								
		<div class="help-block"></div>		

		
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<b>受稽機關技術檢測自評表</b>
			</h4>
		</div>
		<div class="row">
			<div style="display: inline-block" ngf-select="" id="fileSelfEvaluation"
				ng-model="fileSelfEvaluation" name="fileSelfEvaluation" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                ngf-max-size="100MB" ngf-min-height="100">
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇受稽機關技術檢測自評表格式上傳
				</button>
				<span>{{fileSelfEvaluation.name}}</span>
			</div>
			<button class="btn btn-success" ng-click="importFileSelfEvaluation()"
					ng-show="fileSelfEvaluation.name!=null">
					匯入受稽機關技術檢測自評表格式
			</button>
		</div>
		<div ng-show="IsSelfEvaluationExist" class="row">
			<a href="./api/k02/download/SelfEvaluation">下載技術檢測自評表表格式.docx</a>
		</div>
		<div>
			<div class="form_group"></div>
		</div>
		<br>								
		<div class="help-block"></div>	



	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>