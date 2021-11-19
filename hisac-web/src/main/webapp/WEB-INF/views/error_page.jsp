<%@ page pageEncoding="UTF8"%>
<%@ page session="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="./include/ga.jsp"%>
<title><s:message code="globalSiteName" /></title>
<link rel="shortcut icon" href="/hisac-web/resources/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon" href="/hisac-web/resources/favicon.ico"
	type="image/vnd.microsoft.icon" />
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/fontawesome/css/fontawesome-all.min.css">
<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);

body {
	font-family: "Noto Sans TC", "Source Sans Pro", Calibri, Candara, Arial,
		sans-serif !important;
	margin: 0;
	height: 100%;
	background-color: #333;
	color: #fff;
	text-align: center;
	text-shadow: 0 1px 3px rgba(0, 0, 0, .5);
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.42857143;
}

.site-wrapper {
	display: table;
	width: 100%;
	height: 100%;
	min-height: 100%;
	-webkit-box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
	box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
}

.fixed-bottom {
	position: fixed;
	height: 30px;
	bottom: 0;
	width: 100%;
	margin: 0 aut0;
	text-align: center;
}

.site-inner {
	vertical-align: middle;
	display: table-cell;
}

h1 {
	font-family: "Noto Sans TC", "Source Sans Pro", Calibri, Candara, Arial,
		sans-serif !important;
	font-size: 36px;
}

h2 {
	font-family: "Noto Sans TC", "Source Sans Pro", Calibri, Candara, Arial,
		sans-serif !important;
	margin-bottom: 20px;
	font-size: 24px;
	font-weight: 300;
	line-height: 1.4;
}

.button {
	font-family: "Noto Sans TC", "Source Sans Pro", Calibri, Candara, Arial,
		sans-serif !important;
	position: relative;
	border: none;
	font-size: 20px;
	color: #fff;
	padding: 10px 30px;
	margin: 20px 5px;
	text-align: center;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
	text-decoration: none;
	overflow: hidden;
	cursor: pointer;
	display: inline-block;
}

.button-green {
	background-color: #4caf50;
}

.button-blue {
	background-color: #3a5fcd;
}

.button:hover {
	opacity: 0.8;
	filter: alpha(opacity = 80);
}
</style>
</head>
<body>
	<div class="site-wrapper">
		<div class="site-inner">
			<h1>
				<c:out value="${errorMsg}" />
			</h1>
			<h2>
				<c:out value="${errorDescription}" />
			</h2>
			<a href="<c:out value="${pageContext.request.contextPath}" />/"
				class="button button-green"><i class="fas fa-fw fa-home"></i> <s:message
					code="globalGoToHomepage" /></a> <a
				href="<c:out value="${pageContext.request.contextPath}" />/contact_us"
				class="button button-blue"><i class="fas fa-fw fa-comment-alt"></i>
				<s:message code="globalContactUs" /></a>
		</div>

	</div>
</body>
</html>