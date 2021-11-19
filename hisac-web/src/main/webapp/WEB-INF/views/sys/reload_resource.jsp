<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/reload_resource.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>
	<div class="container">
		<h2>${processTime}</h2>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>