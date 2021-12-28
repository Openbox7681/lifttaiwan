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
                                    <div class="button_fill_gray overviewbtn">
                                        <p>臺灣各領域國際合作概況 </p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                   <img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_cooperate.jpg"
                                                    >

                                                    <div id="strip_1" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="button_fill_gray overviewbtn">
                                        <p>臺灣主要國際合作國別分析 </p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    <img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_cooperate.jpg"
                                                    >

                                                    <div id="strip_2" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="button_fill_gray overviewbtn">
                                        <p>各國國際合作佔比分析</p>
                                    </div>
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    <img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/img_cooperate.jpg"
                                                    >

                                                    <div id="strip_3" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 adjust_pos">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>臺灣各領域國際合作現況</h5>
                                    </div>
                                </div>
                                <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出</p>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="overview_table_outer" id="overview_table">
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <th>學門</th>
                                                    <th>領域</th>
                                                    <th>2011-2015</th>
                                                    <th>2015-2019</th>
                                                </tr>
                                                <tr>
                                                    <td class="table_center" rowspan="5">基礎科學</td>
                                                    <td class="table_left">太空科學</td>
                                                    <td>12.33</td>
                                                    <td>48.69</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td class="table_center" rowspan="4">基礎科學</td>
                                                    <td class="table_left">太空科學</td>
                                                    <td>12.33</td>
                                                    <td>48.69</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td class="table_center" rowspan="10">基礎科學</td>
                                                    <td class="table_left">太空科學</td>
                                                    <td>12.33</td>
                                                    <td>48.69</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td class="table_center" rowspan="3">基礎科學</td>
                                                    <td class="table_left">太空科學</td>
                                                    <td>12.33</td>
                                                    <td>48.69</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
                                                </tr>
                                                <tr>
                                                    <td>地球科學</td>
                                                    <td>59.84</td>
                                                    <td>65.29</td>
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