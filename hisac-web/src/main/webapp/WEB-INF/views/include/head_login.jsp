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
	href="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo.ico"
	type="image/vnd.microsoft.icon" />
<%-- <link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap/css/bootstrap-theme-cosmo.min.css" /> --%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/default.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/fontawesome/css/fontawesome-all.min.css">
<%-- <link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/basicstyle.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/mainstyle.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/responsive.css"> --%>

<title>Lift Taiwan</title>


 <!-- =====  bootstrap  =====-->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
 <link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/bootstrap.min.css">

 <!-- =====  vendor  =====-->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"> -->
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/reset.min.css">


<!-- =====  google font  =====-->
<!-- <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&amp;display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Hind+Siliguri&amp;family=Noto+Sans+TC&amp;display=swap" rel="stylesheet"> -->




<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/basicstyle.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/mainstyle.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/map_point.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/index_style.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/responsive.css">
	
	
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/html5shiv.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/respond.min.js"></script>
<%-- <script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
	
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootbox.min.js"></script> --%>
	
	
<%-- <script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/3.6.0jquery.min.js"></script> --%>
	
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/2.1.3jquery.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/5.5.2bootbox.min.js"></script>

<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-cookies.min.js"></script>


<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.css">
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/moment.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/moment.locales.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/bootstrap-datetimepicker.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/ng-file-upload/ng-file-upload-shim.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/ng-file-upload/ng-file-upload.min.js"></script>
	
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/FileSaver.js"></script>
	
<script src="https://cdn.jsdelivr.net/npm/jquery.cookie-consent/dist/jquery.cookie-consent.min.js"></script>
 	

<%-- <script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/cookie-consent.min.js"></script> --%>
	
	
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