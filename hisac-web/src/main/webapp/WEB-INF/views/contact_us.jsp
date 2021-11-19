<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/contact_us.js"></script>
<body ng-app="myApp">
	<%@ include file="./include/head_navbar.jsp"%>
	<div ng-controller="getAppController" class="container">
		<div class="row">
			<div class="col-sm-12 shadow_board">
				<h4 class="form_heading form_heading_gray">
					<big><i class="fas fa-fw fa-user-circle fa-lg"></i></big>
					<s:message code="globalContactUs" />
				</h4>
				<div>
					<h4>
						<i class="fas fa-fw fa-map-marker"></i>&nbsp;
						<s:message code="globalFooterAddress" />
						: <a
							href="https://www.google.com.tw/maps?q=<c:out value="${globalFooterAddressValue}" />"
							target="_googlemap"><c:out value="${globalFooterAddressText}" /></a>
					</h4>
					<h4>
						<i class="fas fa-fw fa-phone"></i>&nbsp;
						<s:message code="globalFooterTel" />
						: <a href="tel:<c:out value="${globalFooterTelValue}" />"
							target="_tel"><c:out value="${globalFooterTelText}" /></a>
					</h4>
					<h4>
						<i class="fas fa-fw fa-envelope"></i>&nbsp;
						<s:message code="globalFooterEmail" />
						: <a href="mailto:<c:out value="${globalFooterEmail}" />"
							target="_email"><c:out value="${globalFooterEmail}" /></a>
					</h4>
				</div>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>