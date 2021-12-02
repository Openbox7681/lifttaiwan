<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/index.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.PieceLabel.min.js"></script>
<body class="no-skin"ng-controller="getAppController" >
<%-- 	<%@ include file="../include/navbar.jsp"%>
 --%>	
 <!-- partial:index.partial.html-->
 <%@ include file="../include/f_navbar.jsp"%>
	<section id="main_content">
	<div class="container">
	<div class="row">
	<%@ include file="../include/slidebar.jsp"%>
	
	<div class="col-lg-9"> 
            <div class="row">
              <div class="col-lg-12">
                <div class="row">
                  <div class="col-lg-6 col-md-6">
                    <div class="index_chart">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="index_title">
                            <h6>六大領域國際關鍵人才分布</h6>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="chart"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_map.jpg"></div>
                        </div>
                        <div class="col-lg-12">
                          <div class="downloadbtn">
                            <div class="downicon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_download.svg"></div><a class="button_line_orange" href="pages/index_areatalents.html">
                              <p>詳細圖表</p></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  <div class="col-lg-6 col-md-6">
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="index_title">
                                                    <h6>近十年In-bound、Outbound核定人數與經費分布</h6>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    <img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_line-growth.jpg">
                                                    
<!--                                                     <div id="solar_employment" style="height: 278px"></div>
 -->                                                    
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="downloadbtn">
                                                    <div class="downicon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_download.svg"></div><a
                                                        class="button_line_orange" href="pages/index_fee.html">
                                                        <p>詳細圖表</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                 
                  
                  
                </div>
              </div>
              <div class="col-lg-12">
                <div class="row">
                  <div class="col-lg-6 col-md-6"> 
                    <div class="index_chart">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="index_title">
                            <h6>Out-bound海外主要研習機構</h6>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="chart"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_pie-1.jpg"></div>
                        </div>
                        <div class="col-lg-12">
                          <div class="downloadbtn">
                            <div class="downicon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_download.svg"></div><a class="button_line_orange" href="pages/index_outbound.html">
                              <p>詳細圖表</p></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-6 col-md-6">
                    <div class="index_chart">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="index_title">
                            <div class="icon_cube"></div>
                            <h6>千里馬計畫優勢學術領域</h6>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="chart"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_bubble-gradient.jpg"></div>
                        </div>
                        <div class="col-lg-12">
                          <div class="downloadbtn">
                            <div class="downicon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_download.svg"></div><a class="button_line_orange" href="pages/index_plan.html">
                              <p>詳細圖表</p></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>	
          
          		
	</div>
	</div>
		
		
	</section>
	
	
	
	<div class="help-block"></div>
	<div class="container-fluid banner-zone">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-3" ng-repeat="item in links">
					<a href="{{item.SourceLink}}" target="_outer"><img
						src="<c:out value="${pageContext.request.contextPath}" />/{{item.ImageLink}}"
						class="img-responsive" title="{{item.ImageTitle}}" /></a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>