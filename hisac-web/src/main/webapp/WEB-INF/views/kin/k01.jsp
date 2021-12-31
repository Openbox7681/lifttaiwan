<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k01.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
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
                        <div class="col-lg-12">
                            <div class="row overview_chart">
                                <div class="col-lg-4 col-md-4">
                                    <div class="button_fill_gray overviewbtn" ng-click = 'showDraw(1)'>
                                        <p>臺灣各領域國際合作概況 </p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                   

                                                    <div id="strip_1" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="button_fill_gray overviewbtn" ng-click = 'showDraw(2)'>
                                        <p>臺灣主要國際合作國別分析 </p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    

                                                    <div id="strip_2" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="button_fill_gray overviewbtn" ng-click = 'showDraw(3)'>
                                        <p>各國國際合作佔比分析</p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                  

                                                    <div id="strip_3" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 adjust_pos"  ng-show = 'isShow1'>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>臺灣各領域國際合作現況</h5>
                                    </div>
                                </div>
                                
                             <!--    <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出</p>
                                    </div>
                                </div> -->
                                
                                <div class="col-lg-12">
                                    <div class= "form_outbor" id="overview_table">
                                        <table class="table caption-top">
                                           <thead class="tablecolor">
                                        	<tr>		
                                        	<th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>國家</p>
				                                </div>
				                              </th>
			
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2013-2017</p>
				                                </div>
				                              </th>
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2014-2018</p>
				                                </div>
				                              </th>
				                              
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2015-2019</p>
				                                </div>
				                              </th>
                                        	</tr>
                                        	</thead>
                              <tbody>
                            <tr ng-repeat="item in formData">
                              <td class="td_style">{{item.name}}</td>
                              <td class="td_style">{{item.year2013}}</td>
                              <td class="td_style">{{item.year2014}}</td>
                              <td class="td_style">{{item.year2015}}</td>
                            </tr>
                          </tbody>
                           </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-lg-12 adjust_pos"  ng-show = 'isShow2'>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>臺灣主要國際合作國別分析</h5>
                                    </div>
                                </div>
                                
                             <!--    <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出</p>
                                    </div>
                                </div> -->
                                
                                <div class="col-lg-12">
                                    <div class= "form_outbor" id="overview_table">
                                        <table class="table caption-top">
                                           <thead class="tablecolor">
                                        	<tr>		
                                        	<th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>國家</p>
				                                </div>
				                              </th>
			
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>無國際合作表現</p>
				                                </div>
				                              </th>
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>有國際合作表現</p>
				                                </div>
				                              </th>
				                              
				                            
                                        	</tr>
                                        	</thead>
                              <tbody>
                            <tr ng-repeat="item in formData2">
                              <td class="td_style">{{item.name}}</td>
                              <td class="td_style">{{item.number_no}}</td>
                              <td class="td_style">{{item.number_yes}}</td>
                            </tr>
                          </tbody>
                           </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="col-lg-12 adjust_pos"  ng-show = 'isShow3'>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>各國國際合作佔比分析</h5>
                                    </div>
                                </div>
                                
                             <!--    <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出</p>
                                    </div>
                                </div> -->
                                
                                <div class="col-lg-12">
                                    <div class= "form_outbor" id="overview_table">
                                        <table class="table caption-top">
                                           <thead class="tablecolor">
                                        	<tr>		
                                        	<th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>領域別</p>
				                                </div>
				                              </th>
			
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2013-2017</p>
				                                </div>
				                              </th>
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2014-2018</p>
				                                </div>
				                              </th>
				                              
				                              <th scope="col">
				                                <div class="plan_text td_style"> 
				                                  <p>2015-2019</p>
				                                </div>
				                              </th>
                                        	</tr>
                                        	</thead>
                              <tbody>
                            <tr ng-repeat="item in formData3">
                              <td class="td_style">{{item.name}}</td>
                              <td class="td_style">{{item.year2013}}</td>
                              <td class="td_style">{{item.year2014}}</td>
                              <td class="td_style">{{item.year2015}}</td>
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
		</section>
	</div>
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	



	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>