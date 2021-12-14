<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/inc/i02.js"></script>
<body class="index-login" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	<section id="main_content">
		<div class="container">
		<div class="row">
		
		<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
        </div>
		
		
		<%@ include file="../include/slidebar.jsp"%>
	
	<div id="divQuery" class="col-lg-9">
	
		影音資訊管理
		
	
		
	</div>
	
	
					</div>
			</div>
				
			
		
		
		</section>
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>