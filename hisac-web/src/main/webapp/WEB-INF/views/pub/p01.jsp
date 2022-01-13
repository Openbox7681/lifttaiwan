<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/pub/starRatings.css">
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p01.js"></script>
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
	
		
		
		<div id="divQuery" class="col-lg-9 col-md-9 col-sm-9 container">
		<div class="row">
		
			
		
		
		
              <div class="col-lg-12 col-md-12 col-sm-12 adjust_pos">
                <div class="row">
                
                 
                   <div class="col-lg-12 col-md-12 col-sm-12">
                           <div class="index_title">
                               <h6>六大領域國際關鍵人才分布地圖</h6>
                           </div>
                       </div>
                       <div class="col-lg-12 col-md-12 col-sm-12 ">
                           <div class="col-lg-12 chart">

                               <div class="col-lg-12" id="mountNode" style="height: 750px" ></div>
                           </div>
                       </div>
         
                
                
                
                  <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="index_title">
                      <h5>六大領域國際關鍵人才分布概況</h5>
                    </div>
                  </div>
                  <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="form_chart">
                      <div class="form_outbor">
                        <table class="table caption-top">
                          <thead class="tablecolor">
                            <tr>
                              <th scope="col"> </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>盤古開天</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>國合PI</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>短期訪問學者</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>龍門計畫主持人</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>政策邀訪學者</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style"> 
                                  <p>千里馬申請人</p>
                                </div>
                              </th>
                              <th scope="col">
                                <div class="plan_text td_style">
                                  <p>合計</p>
                                </div>
                              </th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr ng-repeat="item in formData">
                              <th scope="row" class="td_style">{{item.name}}</th>
                              <td class="td_style">{{item.open}}</td>
                              <td class="td_style">{{item.pi}}</td>
                              <td class="td_style">{{item.short}}</td>
                              <td class="td_style">{{item.dragon}}</td>
                              <td class="td_style">{{item.policy}}</td>
                              <td class="td_style">{{item.horse}}</td>
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
	
	
	

	 <!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	
		<div class="footer_space"></div>
	
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
