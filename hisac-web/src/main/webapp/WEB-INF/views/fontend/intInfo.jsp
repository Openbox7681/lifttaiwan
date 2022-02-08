<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index_info.jsp"%>
<!-- =====  vendor  =====-->
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/reset.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/5.0.2bootstrap.css">
<!-- =====  google font  =====-->
<!-- <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&amp;display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Hind+Siliguri&amp;family=Noto+Sans+TC&amp;display=swap" rel="stylesheet"> -->
<!-- =====  awesome icon  =====-->
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/all.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/new.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/basicstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/mainstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/responsive.css">

<%-- <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/filiter_keyword_none.js"></script>
 --%><script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/main.js"> </script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/echarts.min.js"></script>
<body class="no-skin" id="stop" ng-controller="getAppController" >
	<%@ include file="../include/head_fontend_navbar.jsp"%>
    <section id="main_content">
        <div class="container">
        	<div class="col-sm-12 intadjust">
        		<div class="row">
        			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="pills-mechanism-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-mechanism" type="button" role="tab"
                                aria-controls="pills-mechanism" aria-selected="true">Top20機構</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-scholar-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-scholar" type="button" role="tab" aria-controls="pills-scholar"
                                aria-selected="false">Top20學者</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-cooperate-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-cooperate" type="button" role="tab"
                                aria-controls="pills-cooperate" aria-selected="false">台灣及其他國家國際合作表現</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-sixarea-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-sixarea" type="button" role="tab" aria-controls="pills-sixarea"
                                aria-selected="false">六大領域國際網絡</button>
                        </li>
                    </ul>
                    <div class="tab-content" id="pills-tabContent">
                    	<div class="tab-pane fade show active fade_style" id="pills-mechanism" role="tabpanel"
                            aria-labelledby="pills-mechanism-tab">
                            <div class="col-lg-12">
                            	<div class="row">
                            		<div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    
                                                                    
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
                                        	<input class = "form-check-input" type = "checkbox" ng-model = "item.Flag"  ng-change = "getKeyword(item.Name)">
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                                    
                                                    
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p ng-click = 'query()'
                                                        >送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart mt-1">
                                            <!--<img src="../img/img_cubelink.jpg">-->

                                            <div id="top20" style="height: 500px"></div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <div class="col-lg-12 adjust_pos" ng-show="isSupport">
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
                                            </div>
                                        </div>
                                    </div>
                                    
                            	</div>
                            </div>
                        </div>
                        <div class="tab-pane fade fade_style" id="pills-scholar" role="tabpanel"
                            aria-labelledby="pills-scholar-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    
                                                                                    
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
                                        	<input class = "form-check-input" type = "checkbox" ng-model = "item.Flag" ng-change = "getKeyword(item.Name)">
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                                    
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p ng-click = 'query2()'
                                                        >送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <div class="col-lg-12 adjust_pos" ng-show="isSupport2"> 
                                                    <div class="form_chart">
                                                        <div class="form_outbor">
                                                            <table class="table caption-top">
                                                                <thead class="tablecolor">
                                                                    <tr>
                                                                        <th scope="col">排名</th>
				                                                        <th scope="col">作者</th>
				                                                        <th scope="col">發表機構</th>
				                                                        <th scope="col">被引用次數</th>
				                                                        <th scope="col">網路連結</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                   <tr ng-repeat="item in Topres20Data">
			                                            <td>{{item.Id}}</td>
			                                            <td>{{item.FullName}}</td>
			                                            <td>{{item.Affiliation}}</td>
			                                            <td>{{item.Aac}}</td>
														<td>{{item.Con_Num}}</td>
														
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
                        <div class="tab-pane fade fade_style" id="pills-cooperate" role="tabpanel"
                            aria-labelledby="pills-cooperate-tab">
                            <div class="col-lg-12">
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
                                                <div class="chart mt-1">
                                                   

                                                    <div id="strip_1" style="width: 400px; height: 300px"></div>
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
                                            <div class="col-lg-12 mt-1">
                                                <div class="chart">
                                                    

                                                    <div id="strip_2" style=" width: 400px; height: 300px"></div>
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
                                            <div class="col-lg-12 mt-1">
                                                <div class="chart">
                                                  

                                                    <div id="strip_3" style=" width: 400px;  height: 300px"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                                
                                    <div class="col-sm-12 intadjust">
                                       
                                        <div class="tab-content" id="pills-tabContent">
                                            <div class="tab-pane fade fade_style show active" id="pills-int1" role="tabpanel"
                                                aria-labelledby="pills-int1-tab">
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                    
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
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade fade_style" id="pills-sixarea" role="tabpanel"
                            aria-labelledby="pills-sixarea-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                  
                                                                    
                                                                                    
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
                                        	<input class = "form-check-input" type = "checkbox" ng-model = "item.Flag"  ng-change = "getKeyword(item.Name)">
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                                    
                                                    
                                                    
                                                    
                                                    
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p ng-click = 'query4()'
                                                        
                                                        >送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart">
                                            <!--<img src="../img/img_bubble-chart.jpg">-->
                                        
                                            <div id="connect" style=" height: 1000px"></div>
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
    <div class="modal fade fade_style" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
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
                                <div class="form-check ad_form">
                                    <input class="form-check-input" id="filiterall" type="checkbox" value="">
                                    <label class="form-check-label" for="filiterall"></label>所有領域
                                </div>
                                <div class="line2"></div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea1" aria-expanded="false" aria-controls="filiterarea1"><i
                                        class="fas fa-chevron-up"></i><span>資訊及數位相關產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea1">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea2" aria-expanded="false" aria-controls="filiterarea2"><i
                                        class="fas fa-chevron-up"></i><span>資安卓越產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea2">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea3" aria-expanded="false" aria-controls="filiterarea3"><i
                                        class="fas fa-chevron-up"></i><span>臺灣精準健康戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea3">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea4" aria-expanded="false" aria-controls="filiterarea4"><i
                                        class="fas fa-chevron-up"></i><span>國防及戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea4">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea5" aria-expanded="false" aria-controls="filiterarea5"><i
                                        class="fas fa-chevron-up"></i><span>綠電及再生能源產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea5">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea6" aria-expanded="false" aria-controls="filiterarea6"><i
                                        class="fas fa-chevron-up"></i><span>民生及戰備產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea6">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea7" aria-expanded="false"
                                    aria-controls="#filiterarea7"><i
                                        class="fas fa-chevron-up"></i><span>其他產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea7">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <!--saerch btn------------------------>
                                <div class="search_btn hcenter">
                                    <div class="button_fill_orange btn_m" id="ad_btn_search">
                                        <p>送出查詢 </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
	
	<script type="text/javascript">
        


       
        
     

    </script>
</body>
</html>