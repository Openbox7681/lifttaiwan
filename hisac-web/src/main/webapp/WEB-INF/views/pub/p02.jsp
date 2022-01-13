<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/pub/starRatings.css">
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p02.js"></script>
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
	
		
		<div id="divQuery" class="col-lg-9 container">	
			<div class="row">
              <div class="col-lg-12 adjust_pos">
                <div class="row">
                
                <div class="col-lg-12 col-md-12 col-sm-12">
                                                <div class="index_title">
                                                    <h6>近10年各計畫學術產出表現</h6>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                <div class="chart">
                                                      <div id="solar_employment" style="height: 750px" ></div>                                               
                                                   
                                                </div>
                                            </div>
                
                
                
                  <div class="col-sm-12">
                    <div class="index_title">
                      <h5>近十年In-bound、Outbound核定人數與經費分布</h5>
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="form_chart">
                      <div class="form_outbor">
                        <table class="table caption-top table_font_size">
                          <thead class="tablecolor">
                            <tr>
                              <th scope="col"> </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2011</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2012</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2013</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2014</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2015</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2016</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2017</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2018</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2019</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>2020</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>total</p>
                                </div>
                              </th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr ng-repeat="item in formData">
                              <th scope="row" class="td_style">{{item.name}}</th>
                              <td class="td_style">{{item.eleven}}</td>
                              <td class="td_style">{{item.twelve}}</td>
                              <td class="td_style">{{item.thirteen}}</td>
                              <td class="td_style">{{item.fourteen}}</td>
                              <td class="td_style">{{item.fifteen}}</td>
                              <td class="td_style">{{item.sixteen}}</td>
                              <td class="td_style">{{item.seventeen}}</td>
                              <td class="td_style">{{item.eighteen}}</td>
                              <td class="td_style">{{item.nineteen}}</td>
                              <td class="td_style">{{item.twenty}}</td>
                              <td class="td_style">{{item.total}}</td>
                            </tr>
                          </tbody>
                        </table>
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
		
	</div>
	
		
	
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
