<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p08.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_red">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-aboutus.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
		</div>
		<div id="divDetail">
			<div class="row detail_block">
				<div class="detailContent">
					<div ng-bind-html="detailContent | trustHtml"></div>
				</div>
			</div>
		</div>
		<div class="row detail_block" ng-show="itemAttachments.length > 0">
			<h4>
				<i class="fas fa-fw fa-download"></i>
				<s:message code="newsAttachDownload" />
			</h4>
			<ul class="detailAttachFile">
				<li ng-repeat="item in itemAttachments"><s:message
						code="globalFileName" />&nbsp;:&nbsp;<a
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/api/p07/attach/download/{{id}}/{{item.Id}}"
					target="_blank">{{item.FileName}}</a>&nbsp;(<s:message
						code="globalFileSize" />&nbsp;:&nbsp;{{item.FileSize}}&nbsp;)</li>
			</ul>
		</div>
	</div>
	<div class="footer_space"></div>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>