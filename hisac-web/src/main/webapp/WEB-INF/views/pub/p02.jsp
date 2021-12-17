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
                  <div class="col-sm-12">
                    <div class="index_title">
                      <h5>近十年In-bound、Outbound核定人數與經費分布</h5>
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="form_chart">
                      <div class="form_outbor">
                        <table class="table caption-top">
                          <thead class="tablecolor">
                            <tr>
                              <th scope="col"> </th>
                              <th scope="col">
                                <div class="plan_text"> 
                                  <p>A計畫別</p>
                                  <p>人數(%)去除重複</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text"> 
                                  <p>B計畫別</p>
                                  <p>人數(%)去除重複               </p>
                                </div>
                              </th>
                              <th class="middle_outer" scope="col">
                                <div class="plan_text_mcenter">
                                  <p>合計</p>
                                </div>
                              </th>
                            </tr>
                          </thead>
                          <tbody class="areacolor">
                            <tr>
                              <th scope="row">領域a</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                               </td>
                            </tr>
                            <tr>
                              <th scope="row">領域b</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                            </td>
                            </tr>
                            <tr>
                              <th scope="row">領域c</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                            </td>
                            </tr>
                            <tr>
                              <th scope="row">領域d</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                             </td>
                            </tr>
                            <tr>
                              <th scope="row">領域e</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                          </td>
                            </tr>
                            <tr>
                              <th scope="row">領域f</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                             </td>
                            </tr>
                            <tr>
                              <th scope="row">領域g</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                            </td>
                            </tr>
                            <tr>
                              <th scope="row">領域g</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                             </td>
                            </tr>
                            <tr>
                              <th scope="row">領域i</th>
                              <td>99</td>
                              <td>99</td>
                              <td>                             </td>
                            </tr>
                            <tr>
                              <th scope="row">合計</th>
                              <td>99</td>
                              <td>99</td>
                              <td>26,000</td>
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
