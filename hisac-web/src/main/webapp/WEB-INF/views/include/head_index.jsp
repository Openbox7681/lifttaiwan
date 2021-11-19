<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Start Head -->
<head>
<%@ include file="ga.jsp"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<title><s:message code="globalSiteName" /></title>
<meta name="copyright" content="<s:message code="globalCopyright" />">
<meta name="robots" content="noindex,nofollow">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="refresh" content="600" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta name="_csrf" content="<c:out value="${_csrf.token}" />" />
<meta name="_csrf_header" content="<c:out value="${_csrf.headerName}" />" />
<link rel="shortcut icon"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/favicon.ico"
	type="image/vnd.microsoft.icon" />
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap/css/bootstrap-theme-cosmo.min.css" />
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/default.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/fontawesome/css/fontawesome-all.min.css">
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/html5shiv.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/respond.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootbox.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-cookies.min.js"></script>
<c:if test="${enableGoogleRecaptcha}">
	<script src='https://www.google.com/recaptcha/api.js'></script>
</c:if>
<!--[if lt IE 10]>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jsencrypt.js"></script>
<![endif]-->
<script>
	var header = JSON
			.parse('{"Content-Type" : "application/x-www-form-urlencoded;charset=utf-8;","'
					+ $("meta[name='_csrf_header']").attr("content")
					+ '":"'
					+ $("meta[name='_csrf']").attr("content") + '", "Cache-Control": "no-cache"}');
	var csrf_config = {
		withCredentials : true,
		crossDomain : true,
		headers : header
	};
	var header2 = JSON.parse('{"'
			+ $("meta[name='_csrf_header']").attr("content") + '":"'
			+ $("meta[name='_csrf']").attr("content") + '"}');
	var csrf_config2 = {
		withCredentials : true,
		crossDomain : true,
		headers : header2
	};

	$(document).ready(function() {
		$("#gotop").click(function() {
			$("html, body").animate({
				scrollTop : 0
			}, 600);
			return false;
		});
	});

	var dataLoading = '<s:message code="dataLoading" />';
	var globalReadDataFail = '<s:message code="globalReadDataFail" />';
	var btnClose = '<s:message code="btnClose" />';
</script>
</head>
<!-- End Head -->
