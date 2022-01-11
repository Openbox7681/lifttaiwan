<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>

<%-- <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/custom.js"></script>
 --%> 
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c01.js"></script>

<script src="https://cdn.jsdelivr.net/npm/d3-hierarchy@2.0.0/dist/d3-hierarchy.min.js"></script>


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
                                    <div class="choose_item" id="it_search">
                                        <div class="input-group mb-2">
                                            <input class="form-control" type="text" placeholder="輸入英文關鍵字"
                                                aria-label="輸入英文關鍵字" aria-describedby="basic-addon2">
                                        </div>
                                        <div class="main-block">
                                             <div class="main-subject">
                                            <div class="row g-0">
                                                <div class="col-lg-12">
                                                    <div class="main-detail" id="main-detail">
                                                                              <label>主領域</label>
                                                    
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
                                        <div ng-show = "IsInfoShow">
                                         <div class="line2"></div>
                                        <div class="sub-subject"></div>
                                        <label>次領域</label>
                                        <div class = "form-check" ng-repeat = "item in InfoList"  name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" ng-change = "getKeyword()">
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                        
                                        </div>
                                        
										<div ng-show = "IsKeywordShow">
                                        
                                        
                                         <div class="line2"></div>
                                        <div class="sub-subject"></div>
										<label>預設關鍵字</label>
                                        
                                        <div class = "form-check" ng-repeat = "item in keywordList"  name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                        
                                        
                                        
                                           </div>
                                        
                                        
                                        </div>
                                        <div class="search_btn hright">
                                            <div class="button_fill_orange btn_m">
                                                <p ng-click = 'query()'>送出查詢</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 adjust_pos">
                            <div class="row">
                                
                                <!-- <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出 </p>
                                    </div>
                                </div> -->
                                <div class="col-lg-12 exportbtn">
                                 <div class="index_title" ng-show = "draw">
                                        <h5>國際網路關係圖</h5>
                                    </div>
                                    <div class="chart">
                                        <!--<img src="../img/img_highcharts.jpg">-->
                                        <div id="relation" style="height: 1000px"></div>
                                    </div>
                                    <!-- <div class="button_fill_orange btn_s" type="button" data-bs-toggle="modal"
                                        data-bs-target="#suggest">
                                        <p>未來合作建議 </p>
                                    </div> -->
                                </div>
                                <div class="col-sm-12">
                                    <div class="index_title"  ng-show = "draw">
                                        <h5>國家別</h5>
                                    </div>
                                    <div class="chart">
                                        <!--<img src="../img/img_bubble-chart.jpg">-->

                                        <div id="country" style="height: 1000px"></div>
                                    </div>
                                </div><!-- 
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>相關指標報表</h5>
                                    </div>
                                </div> -->
                              <!--   <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="icon_button">
                                        <div class="inner_icon">
                                            <div class="icon_sixarea"></div>
                                        </div><span>六大領域既有國際網絡分析(關鍵網絡人數) </span>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="icon_button">
                                        <div class="inner_icon">
                                            <div class="icon_mechanism"></div>
                                        </div><span>目前補助研習機構分布表現</span>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="icon_button">
                                        <div class="inner_icon">
                                            <div class="icon_issue"></div>
                                        </div><span>科技議題或新興技術國際網絡分析 </span>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="icon_button">
                                        <div class="inner_icon">
                                            <div class="icon_subsidy_suggest"></div>
                                        </div><span>未來重點領域補助建議 </span>
                                    </div>
                                </div> -->
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
	

	<%----%>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>