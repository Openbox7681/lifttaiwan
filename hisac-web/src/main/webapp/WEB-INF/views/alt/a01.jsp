<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
 <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/alt/a01.js"></script>
 
 
 
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
	
	
	
	<div id="divQuery" class="col-lg-9 container">
				<div class="row">    
				      <div class="col-lg-12 none">
				      	<div id="filiter">		
				     			<div class="filiter_line_dropdown" id="subsidy_date">
				     			 <h6>查詢年份(民國)</h6>           
				      			    <div class="row g-0">
                                        <div class="col-sm-3">
                                            <div class="choose_item">
                                                <div class="input-group mb-2">
                                                    <input class="form-control" type="number" placeholder="- 日期 -"
                                                        aria-label="- 日期 -" aria-describedby="basic-addon2" ng-model = "StartYear"
                                                        oninput= "if(value.length>3)value=value.slice(0,3)"                                                       
                                                        >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <div class="dateline datemiddle"></div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="choose_item">
                                                <div class="input-group mb-2">
                                                    <input class="form-control" type="number" placeholder="- 日期 -"
                                                        aria-label="- 日期 -" aria-describedby="basic-addon2"  ng-model = "EndYear"
                                                        oninput= "if(value.length>3)value=value.slice(0,3)"
                                                        >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				      		<h6>按領域查詢</h6>
				      		
				      		<div class="main-block">
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
                                            <p ng-click = 'query()' ng-disabled = "value.length ==0"
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
	</section>
		
	</div>
	
	
	
	
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	
		<div class="footer_space"></div>
	
	<%@ include file="../include/footer.jsp"%>
</body>
</html>