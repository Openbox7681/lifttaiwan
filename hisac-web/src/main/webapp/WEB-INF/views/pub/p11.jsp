<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p11.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.PieceLabel.min.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>
	<div class="container">
		<div class="row row_bottom">
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>會員機構申請數</b>
					</h4>
					<canvas id="myChart5" height="240px"></canvas>
					<div id="myLegend5" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>會員管理者數</b>
					</h4>
					<canvas id="myChart6" height="240px">></canvas>
					<div id="myLegend6" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>會員聯絡人數</b>
					</h4>
					<canvas id="myChart7" height="240px">></canvas>
					<div id="myLegend7" class="chart_legend"></div>
				</div>				
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>本日登入{{SigninCountToday}}人</b>						
					</h4>										
					<h4 class="text-center">
						<b>近兩週登入{{SigninCount2week}}人</b>						
					</h4>										
				</div>											
			</div>
			<div class="row row_bottom">
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>公開資訊</b>
					</h4>
					<canvas id="myChart1" height="240px"></canvas>
					<div id="myLegend1" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>公開資訊</b>
					</h4>
					<canvas id="myChart2" height="240px">></canvas>
					<div id="myLegend2" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>警訊</b>
					</h4>
					<canvas id="myChart3" height="240px">></canvas>
					<div id="myLegend3" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>警訊</b>
					</h4>
					<canvas id="myChart4" height="240px">></canvas>
					<div id="myLegend4" class="chart_legend"></div>
				</div>							
			</div>
			<div class="row row_bottom">
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>資安資訊</b>
					</h4>
					<canvas id="myChart8" height="240px"></canvas>
					<div id="myLegend8" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>資安資訊</b>
					</h4>
					<canvas id="myChart9" height="240px">></canvas>
					<div id="myLegend9" class="chart_legend"></div>
				</div>														
			</div>
			<div class="row detail_block">
				<a class="btn btn-default btn_custom"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>				
			</div>	
		</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>