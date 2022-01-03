<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c02.js"></script>
<%-- <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/filter_keyword_none.js"></script>
 --%>

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
                            <div class="row"  >
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
                                <div class="col-sm-12 scholars_btn" >
                                    <div class="button_fill_orange" ng-click = "connect()">
                                        <p>網絡分析圖</p>
                                    </div>
                                </div>
                                <div class="col-sm-12 scholars_chart">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="index_title">
                                                <h5>網路分析圖</h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="scholar_img">
                                                <div id="connect" style="width: 555px; height: 333px"></div>
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