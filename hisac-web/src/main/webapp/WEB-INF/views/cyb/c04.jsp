<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c04.js"></script>
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
                    <div class="main-block">
                      <div class="main-subject">
                        <div class="row g-0">
                          <div class="col-lg-12"> 
                            <div class="main-detail" id="main-detail">
                              <div class="form-check">
                                <div class="all_area_checkbox">
                                  <input class="form-check-input" id="flexCheckDefault" type="checkbox" value="">
                                  <label class="form-check-label" for="flexCheckDefault">所有領域                           </label>
                                </div>
                              </div><span class="su_info" data-index="a">資訊及數位相關產業</span><span class="su_sec" data-index="b">資安卓越產業</span><span class="su_healthy" data-index="c">臺灣精準健康戰略產業</span><span class="su_safe" data-index="d">國防及戰略產業</span><span class="su_power" data-index="e">綠電及再生能源產業</span><span class="su_livelihood" data-index="f">民生及戰備產業</span><span class="su_other" data-index="g">其他產業</span>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="line2"></div>
                      <div class="sub-subject"></div>
                    </div>
                    <div class="search_btn hright">
                      <div class="button_fill_orange btn_m">
                        <p>送出查詢</p>
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
                      <p>報表匯出            </p>
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="index_chart"></div>
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
