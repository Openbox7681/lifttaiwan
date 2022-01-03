<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c03.js"></script>

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
                        <div class="col-lg-12 none">
                            <div id="filiter">
                                <div class="filiter_line_dropdown">
                                    <h6>按領域查詢</h6>
                                    
                                   <div class="main-block" id="i_top">
                                        <div class="main-subject">
                                            <div class="row g-0">
                                                <div class="col-lg-12">
                                                    <div class="main-detail" id="main-detail">
                                                       <div class="form-check">
                                                            <div class="all_area_checkbox">
                                                                <input class="form-check-input" id="flexCheckDefault"
                                                                    type="checkbox" value="" ng-click= "openSubSubjects(0)" ng-model = "infoSelectAll">
                                                                <label class="form-check-label"
                                                                    for="flexCheckDefault">所有領域 </label>
                                                            </div>
                                                        </div>
                                                        <span class="su_info" data-index="a" ng-click = "openSubSubjects(1)">資訊及數位相關產業</span>            
                                                        <span class="su_healthy" data-index="c" ng-click = "openSubSubjects(2)">臺灣精準健康戰略產業</span>
                                                        <span class="su_safe" data-index="d" ng-click = "openSubSubjects(3)">國防及戰略產業</span>
                                                        <span class="su_livelihood" data-index="f" ng-click = "openSubSubjects(4)">民生及戰備產業</span>
                                                        <span class="su_power" data-index="e" ng-click = "openSubSubjects(5)">綠電及再生能源產業</span>
                                                        <span class="su_other" data-index="g" ng-click = "openSubSubjects(6)">其他產業</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="line2"></div>
                                        <div class="sub-subject"></div>
                                        
                                         <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                         
                                    </div>
                                    
                                    
                                    <div class="search_btn hright">
                                        <div class="button_fill_orange btn_m">
                                            <p ng-click = 'query()'
                                            >送出查詢</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 adjust_pos">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>查詢結果</h5>
                                    </div>
                                </div>
                                <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出 </p>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form_chart">
                                        <div class="form_outbor">
                                            <table class="table caption-top">
                                                <thead class="tablecolor">
                                                    <tr>
                                                        <th scope="col">國際學術機構</th>
                                                        <th scope="col">被引用次數</th>
                                                        <th scope="col">研習人數</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">Massachusetts Institute of Technology(MIT)</th>
                                                        <td>5723</td>
                                                        <td>23</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Max Planck Society</th>
                                                        <td>3425 </td>
                                                        <td>- </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Centre National de la Recherche
                                                            Scientifique(CNRS)</th>
                                                        <td>2657</td>
                                                        <td>11 </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">United States Department of Energy (DOE)</th>
                                                        <td>L23053</td>
                                                        <td>- </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">Harvard University</th>
                                                        <td>45682</td>
                                                        <td>66 </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">National Institute of Standards
                                                            Technology(NIST)-USA</th>
                                                        <td>56889</td>
                                                        <td>45 </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">University System of Maryland</th>
                                                        <td>28453</td>
                                                        <td>- </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">California Institute of Technology</th>
                                                        <td>20835</td>
                                                        <td>34 </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">University of Maryland College Park</th>
                                                        <td>1865</td>
                                                        <td>- </td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">University of Cambridge</th>
                                                        <td>18500</td>
                                                        <td>- </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 analysisbtn">
                                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill"
                                                data-bs-target="#pills-home" type="button" role="tab"
                                                aria-controls="pills-home" aria-selected="true">被引用次數分析圖</button>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill"
                                                data-bs-target="#pills-profile" type="button" role="tab"
                                                aria-controls="pills-profile" aria-selected="false">研習人數分析圖</button>
                                        </li>
                                    </ul>
                                    <div class="tab-content" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                                            aria-labelledby="pills-home-tab">
                                            <div class="col-sm-12">
                                                <div class="index_title title_downloadbtn">
                                                    <h5>被引用次數分析圖</h5>
                                                </div>
                                                <div class="chart">
                                                    <!--<img src="../img/img_cubelink.jpg">-->

                                                    <div id="cubelink" style="height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                            aria-labelledby="pills-profile-tab">
                                            <div class="col-sm-12">
                                                <div class="index_title title_downloadbtn">
                                                    <h5>研習人數分析圖</h5>
                                                </div>
                                                <div class="chart">
                                                    <!--<img src="../img/img_pie-2.jpg">-->

                                                    <div id="pie1" style="width: 600px; height: 300px"></div>
                                                    <div id="pie2" style="width: 600px; height: 300px"></div>
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
	</div>
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	



	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>