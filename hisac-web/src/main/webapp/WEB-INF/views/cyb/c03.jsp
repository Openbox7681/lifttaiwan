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
                        <div class="col-lg-12 adjust_pos" >
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>查詢結果</h5>
                                    </div>
                                    
                                    <div class="col-xs-12 col-md-11"  style = "padding : 3px">
                                    <div class="help-block"></div>
                              
                                    <label>
			                        <span id="imgLoading"><i
									class="fas fa-spinner fa-spin"></i> <s:message code="dataLoading" />
									</span>
									</label>
									</div>
                                    
                                    
                                </div>
                               
                                <div class="col-lg-12" ng-show="isSupport">
                                    <div class="form_chart">
                                        <div class="form_outbor">
                                            <table class="table caption-top">
                                                <thead class="tablecolor">
                                                    <tr>
														<th scope="col">排名</th>
                                                        <th scope="col">國際學術機構</th>
                                                        <th scope="col">被引用次數</th>
                                                        <th scope="col">研習人數</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
                                                	<tr ng-repeat="item in Topad50Data">
			                                            <td>{{item.Id}}</td>
			                                            <td>{{item.Affiliation_e}}</td>
			                                            <td>{{item.Tac}}</td>
														<td>{{item.Con_Num}}</td>
														
			                                        </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 analysisbtn">
                                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist" ng-show="isSupport">
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
                                                <div class="index_title title_downloadbtn" ng-show="isSupport">
                                                    <h5>被引用次數分析圖</h5>
                                                </div>
                                                <div class="chart">
                                                    <!--<img src="../img/img_cubelink.jpg">-->

                                                    <div id="cubelink" style="height: 800px"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                            aria-labelledby="pills-profile-tab">
                                            <div class="col-lg-12 col-md-12">
                                                <div class="index_title" ng-show="isSupport">
                                                    <h5>各研習機構比例</h5>
                                                </div>
                                                 <div class="col-lg-12 col-md-12">
                                                
                                                <div class="chart">
                                                    <!--<img src="../img/img_pie-2.jpg">-->

                                                    <div 
                                                		 id="pie1" style="width : 1344px ; height: 800px"></div>
                                                </div>
                                     			 </div>
                                                
                                                
                                                <div class="index_title" ng-show="isSupport">
                                                    <h5>頂尖機構佔比</h5>
                                                </div>
                                                 <div class="col-lg-12 col-md-12">

                                                <div class="chart">

                                                    <div id="pie2" style="width : 1344px ; height: 800px"></div>
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
		</div>
		</section>
		
		<div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="col-md-12">
                        <div id="filiter">
                            <div class="filiter_lightbox_dropdown">
                                <h6>按領域查詢</h6>
                                
                                <div class="line2"></div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea1" aria-expanded="false" aria-controls="filiterarea1"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(1)" class="su_info" 
                                        >資訊及數位相關產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea1">
                                    <div class="card card-body">
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow1" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>	
                                        </div>
                                    </div>
                                </div>
                                
                               
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea3" aria-expanded="false" aria-controls="filiterarea3"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(2)" class="su_healthy"
                                        >臺灣精準健康戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea3">
                                    <div class="card card-body">
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow2" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>	
                                        </div>           
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea4" aria-expanded="false" aria-controls="filiterarea4"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(3)" class="su_safe"
                                        >國防及戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea4">
                                    <div class="card card-body">
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow3" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>	
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea5" aria-expanded="false" aria-controls="filiterarea5"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(5)" class="su_power"
                                        >綠電及再生能源產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea5">
                                    <div class="card card-body">
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow5" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>	
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea6" aria-expanded="false" aria-controls="filiterarea6"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(4)" class="su_livelihood"
                                        >民生及戰備產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea6">
                                    <div class="card card-body">
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow4" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>	
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea7" aria-expanded="false"
                                    aria-controls="#filiterarea7"><i
                                        class="fas fa-chevron-up"></i><span ng-click = "openSubSubjects(6)" class="su_other"
                                        >其他產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea7">
                                    <div class="card card-body">
                                        	<div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow6" name="checkboxForm">
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
                                        <p ng-click = 'query()' data-bs-dismiss="modal" aria-label="Close"
                                        >送出查詢</p>
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
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	



	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>