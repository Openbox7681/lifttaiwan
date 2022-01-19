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
	
	<div class="col-md-6">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
     
     <div class="col-md-6">
                    <div class="sidebar_button sidebar_filiter_btn" type="button" data-bs-toggle="modal"
                        data-bs-target="#myModal"><span>篩選查詢</span></div>
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
                                                    
                                                        <!-- <div class="form-check">
                                                            <div class="all_area_checkbox">
                                                                <input class="form-check-input" id="flexCheckDefault"
                                                                    type="checkbox" value="" ng-click= "openSubSubjects(0)" ng-model = "infoSelectAll">
                                                                <label class="form-check-label"
                                                                    for="flexCheckDefault">所有領域 </label>
                                                            </div>
                                                        </div> -->
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
                                        	type = "checkbox" ng-model = "item.Flag" ng-change = "getKeyword(item.Name)">
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
	     </div>
	    </div>
	</section>
	<div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="it_adjust">
                    <div class="choose_item" id="it_search">
                        <div class="input-group mb-2">
                            <input class="form-control" type="text" placeholder="輸入英文關鍵字" aria-label="輸入英文關鍵字"
                                aria-describedby="basic-addon2">
                        </div>
                        <h5>主領域</h5>
                       
                        <div class="line2"> </div>
                        <div class="it_filiter_first">
                            <div class="su_info">
                                <p ng-click = "openSubSubjects(1)">資訊及數位相關產業</p>
                            </div>
                           
                            <div class="su_healthy">
                                <p ng-click = "openSubSubjects(2)">臺灣精準健康戰略產業</p>
                            </div>
                            <div class="su_safe">
                                <p ng-click = "openSubSubjects(3)">國防及戰略產業</p>
                            </div>
                            <div class="su_power">
                                <p ng-click = "openSubSubjects(5)">綠電及再生能源產業</p>
                            </div>
                            <div class="su_livelihood">
                                <p ng-click = "openSubSubjects(4)">民生及戰備產業</p>
                            </div>
                            <div class="su_other">
                                <p ng-click = "openSubSubjects(6)">其他</p>
                            </div>
                        </div>
                        <div class="it_filiter_subouter" ng-show = "IsInfoShow">
                            <div class="it_filiter_sub1" >
                                <h5>次領域</h5>
                                <div class="line2"> </div>
                                
                                <div class = "form-check" ng-repeat = "item in InfoList"  name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" ng-change = "getKeyword(item.Name)">
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                            </div>
                        </div>
                        <div class="it_filiter_keyouter" ng-show = "IsKeywordShow">
                            <div class="it_filiter_showkeyword1">
                                <h5>關鍵字</h5>
                                <div class="line2"> </div>
                                
                                <div class="sub-subject"></div>
                                        
                                        <div class = "form-check" ng-repeat = "item in keywordList"  name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                            </div>
                        </div>
                        
                        <!--saerch btn------------------------>
                                <div class="search_btn hcenter">
                                    <div class="button_fill_orange btn_m" id="ad_btn_search">
                                        <p ng-click = 'query()' data-bs-dismiss="modal" aria-label="Close">送出查詢</p>
                                    </div>
                                </div>
                        
                        
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
	
	
	
	
	</div>
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	

	<%----%>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>