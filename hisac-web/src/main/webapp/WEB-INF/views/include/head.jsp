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
<title><s:message code="globalSiteName" /> - ${appName}</title>
<meta name="copyright" content="<s:message code="globalCopyright" />">
<meta name="robots" content="noindex,nofollow">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
	


	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">

<!-- =====  google font  =====-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&amp;display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Hind+Siliguri&amp;family=Noto+Sans+TC&amp;display=swap"
    rel="stylesheet">
<!-- =====  awesome icon  =====-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/style.css">

<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/responsive.css">
	
<%-- <link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/fontawesome/css/fontawesome-all.min.css"> --%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/default.css">
	
	

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
	


	
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
	
   
<script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.g2-3.5.1/dist/g2.min.js"></script>
<script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.data-set-0.10.1/dist/data-set.min.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/resources/js/echarts-5.2.2/dist/echarts.min.js"></script>


<!--[if lt IE 10]>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jsencrypt.js"></script>
<![endif]-->
<script>
	var header = JSON.parse('{"'
			+ $("meta[name='_csrf_header']").attr("content") + '":"'
			+ $("meta[name='_csrf']").attr("content") + '"}');
	var csrf_config = {
		withCredentials : true,
		crossDomain : true,
		headers : header
	};

	var sessionTimeout = 1 * ${pageContext.session.maxInactiveInterval} * 1;
	var notifyBefore = 60 * ${beforeSessionTimeoutMinutes} * 1;
	if (notifyBefore > sessionTimeout)
		notifyBefore = sessionTimeout;
	var checkConfirm = null;
	var checkAutoLogout = null;
	var showTimeoutSeconds = null;
	var timeoutSeconds = 0;

	function confirmTimeout() {
		clearInterval(checkConfirm);
		timeoutSeconds = notifyBefore;
		clearInterval(showTimeoutSeconds);
		bootbox
				.dialog({
					message : '<s:message code="globalTimeout" arguments="' + timeoutSeconds + '"/>',
					closeButton : false,
					buttons : {
						ok : {
							label : '<s:message code="globalContinue" />',
							className : 'btn-danger',
							callback : function(result) {
								$
										.ajax(
												{
													type : 'POST',
													url : '<c:out value="${pageContext.request.contextPath}" />/public/api/resetTimeout',
													data : {},
													dataType : 'json',
													headers : header
												}).done(function() {
											resetTimeout();
										});
							}
						}
					}
				});
		setTimeoutSeconds();
	}

	function setTimeoutSeconds() {
		var startTimeout = moment().add(timeoutSeconds, 'seconds');
		showTimeoutSeconds = setInterval(
				function() {
					timeoutSeconds = Math.floor(moment.duration(startTimeout.diff(moment())) / 1000);
					if (timeoutSeconds <= 0) {
						timeoutSeconds = 0;
					}
					$(".modal-body")
							.html(
									'<s:message code="globalTimeout" arguments="' + timeoutSeconds + '"/>');
				}, 500);
	}

	function autoLogout() {
		logout();
	}

	function resetTimeout() {
		clearInterval(checkConfirm);
		clearInterval(checkAutoLogout);
		checkConfirm = setInterval(confirmTimeout,
				(sessionTimeout - notifyBefore) * 1000);
		checkAutoLogout = setInterval(autoLogout, sessionTimeout * 1000);
	}

	function logout() {
		var tmpForm = document.createElement("form");
		tmpForm.action = "<c:out value="${pageContext.request.contextPath}" />/logout";
		tmpForm.method = "post";
		tmpForm.style.display = "none";

		var opt = document.createElement("input");
		opt.type = "hidden";
		opt.name = "<c:out value="${_csrf.parameterName}" />";
		opt.value = "<c:out value="${_csrf.token}" />";
		tmpForm.appendChild(opt);

		var opt = document.createElement("input");
		opt.type = "submit";
		tmpForm.appendChild(opt);

		if (document.body != null) {
			document.body.appendChild(tmpForm);
			tmpForm.submit();
			document.body.removeChild(tmpForm);
		}
	}
	
	$(document).ready(function() {
		resetTimeout();
		
		$("#gotop").click(function() {
			$("html, body").animate({ scrollTop: 0 }, 600);
		    return false;
		});
	});

	var globalReadDataFail = '<s:message code="globalReadDataFail" />';
	var btnClose = '<s:message code="btnClose" />';
	var btnSure = '<s:message code="btnSure" />';
	var globalSureDeleteItem = '<s:message code="globalSureDeleteItem" />';
	var globalDeleteDataFail = '<s:message code="globalDeleteDataFail" />';
	var globalInsertDataFail = '<s:message code="globalInsertDataFail" />';
	var globalUpdateDataFail = '<s:message code="globalUpdateDataFail" />';
	var globalApiKeySureFail  = '<s:message code="globalApiKeySureFail" />';

	var globalApiKeySure = '<s:message code="globalApiKeySure" />';


	var dataLoading = '<s:message code="dataLoading" />';
	var dataUpdating = '<s:message code="dataUpdating" />';
</script>
</head>
<!-- End Head -->
