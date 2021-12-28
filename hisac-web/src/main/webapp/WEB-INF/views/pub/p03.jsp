<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p03.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/f_navbar.jsp"%>
	
	<div class="container">
	
	<section id="main_content">
	<div class="container">
	<div class="row">
	
	<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
				
	<%@ include file="../include/slidebar.jsp"%>
	
		
		<div class="col-lg-9">
                    <div class="row">
                        <div class="col-lg-12 adjust_pos">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>Outbound Top10研習機構分布</h5>
                                    </div>
                                </div>
                                
                                <div class="col-lg-12">
					                    <div class="chart">
                                                 <div id="overseas_institutions" style="height: 600px" ></div>
					                    </div>
					                  </div>
					                                
                                
                                
                                
                            </div>
                        </div>
                    </div>
                </div>
		
	
	</div>
	</div>
	</section>
		
	</div>
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>