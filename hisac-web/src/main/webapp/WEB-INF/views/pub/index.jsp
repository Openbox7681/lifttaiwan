<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/index.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.PieceLabel.min.js"></script>

<body class="no-skin"ng-controller="getAppController" >



<%-- 	<%@ include file="../include/navbar.jsp"%>
 --%>	
 <!-- partial:index.partial.html-->
 <%@ include file="../include/f_navbar.jsp"%>
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
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                  <div class="index_chart">
                          <div class="row">
                              <div class="col-lg-12">
                                  <div class="index_title">
                                      <h6>六大領域國際關鍵人才分布</h6>
                                  </div>
                              </div>
                              <div class="col-lg-12 ">
                                  <div class="col-lg-12 chart">

                                      <div class="col-lg-12" id="mountNode" ></div>
                                  </div>
                              </div>
                              <div class="col-lg-12">
                                  <div class="downloadbtn">
                                      <a
                                          class="button_line_orange" href="p01">
                                          <p>詳細圖表</p>
                                      </a>
                                  </div>
                              </div>
                          </div>
                          </div>
                       </div>
                  
                  				<div class="col-lg-12 col-md-12">
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="index_title">
                                                    <h6>近10年各計畫學術產出表現</h6>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                      <div id="solar_employment" style="height: 600px" ></div>                                               
<!--                                                     <div id="solar_employment" style="height: 278px"></div>
 -->                                                    
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="downloadbtn">
                                                    <a
                                                        class="button_line_orange" href="p02">
                                                        <p>詳細圖表</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                 
                  
                  
                </div>
              </div>
              <div class="col-lg-12">
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="index_title">
                                                    <h6>Outbound Top10研習機構分布</h6>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    <!--<img src="img/img_pie-1.jpg">-->

                                                    <div id="overseas_institutions" style="height: 600px" ></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="downloadbtn">
                                                    <a
                                                        class="button_line_orange" href="pages/index_outbound.html">
                                                        <p>詳細圖表</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                
                 <div class="col-lg-12 col-md-12">
                                    <div class="index_chart">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="index_title">
                                                    <div class="icon_cube"></div>
                                                    <h6>千里馬計畫優勢學術領域</h6>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="chart">
                                                    <!--<img src="img/img_bubble-gradient.jpg">-->

                                                    <div id="maxima" style="height: 600px"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="downloadbtn">
                                                
<%--                                                     <div class="downicon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_download.svg"></div>
 --%>                                                    
                                                    <a
                                                        class="button_line_orange" href="pages/index_plan.html">
                                                        <p>詳細圖表</p>
                                                    </a>
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
	
	
	
	 <!-- tablet&mobile sidebar lightbox-->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <section id="main_content">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="sidebar">
                                        <div class="row">
                                            <div class="col-md-6"><a class="button_line_gray" href="pages/subsidy.html">
                                                    <span>補助追蹤 </span></a>
                                                <div class="mhr"></div>
                                                <div class="button">
                                                    <div class="accordion" id="accordionPanelsStayOpenExample">
                                                        <div class="accordion-item">
                                                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                                                <button class="accordion-button collapsed" type="button"
                                                                    data-bs-toggle="collapse"
                                                                    data-bs-target="#panelsStayOpen-collapseOne"
                                                                    aria-expanded="true"
                                                                    aria-controls="panelsStayOpen-collapseOne">國際網絡</button>
                                                            </h2>
                                                            <div class="accordion-collapse collapse"
                                                                id="panelsStayOpen-collapseOne"
                                                                aria-labelledby="panelsStayOpen-headingOne">
                                                                <div class="accordion-body"><a
                                                                        href="pages/i_technology.html"><span>技術網絡查詢</span></a><a
                                                                        href="pages/i_top_scholars.html"><span>頂尖學者</span></a><a
                                                                        href="pages/i_top_institutions.html"><span>頂尖機構</span></a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mhr"></div>
                                                <div class="button_line_gray"><a
                                                        href="pages/cooperate_overview.html"><span>各國國際合作概況</span></a>
                                                </div>
                                                <div class="mhr"></div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="button_line_gray"><a
                                                        href="pages/talents.html"><span>人才活躍度</span></a></div>
                                                <div class="mhr"></div>
                                                <div class="button">
                                                    <div class="accordion-item">
                                                        <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                                            <button class="accordion-button collapsed" type="button"
                                                                data-bs-toggle="collapse"
                                                                data-bs-target="#panelsStayOpen-collapseTwo"
                                                                aria-expanded="false"
                                                                aria-controls="panelsStayOpen-collapseTwo">系統管理</button>
                                                        </h2>
                                                        <div class="accordion-collapse collapse"
                                                            id="panelsStayOpen-collapseTwo"
                                                            aria-labelledby="panelsStayOpen-headingTwo">
                                                            <div class="accordion-body"><a
                                                                    href="pages/s_permissions.html"><span>角色權限</span></a><a
                                                                    href="pages/s_userdata.html"><span>後台使用者資料管理
                                                                    </span></a><a
                                                                    href="pages/s_manage_image.html"><span>圖片/連結檢測</span></a><a
                                                                    href="pages/s_settings.html"><span>網站設定 </span></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mhr"></div>
                                                <div class="button">
                                                    <div class="accordion-item">
                                                        <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                                                            <button class="accordion-button collapsed" type="button"
                                                                data-bs-toggle="collapse"
                                                                data-bs-target="#panelsStayOpen-collapseThree"
                                                                aria-expanded="false"
                                                                aria-controls="panelsStayOpen-collapseThree">公開資訊管理</button>
                                                        </h2>
                                                        <div class="accordion-collapse collapse"
                                                            id="panelsStayOpen-collapseThree"
                                                            aria-labelledby="panelsStayOpen-headingThree">
                                                            <div class="accordion-body"><a
                                                                    href="pages/m_manage_cooperate.html"><span>國際合作個案管理</span></a><a
                                                                    href="pages/m_manage_video.html"><span>影音資訊管理</span></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mhr"></div>
                                                <div class="button_line_gray"><a
                                                        href="pages/subsystem_data.html"><span>子系統資料維護</span></a></div>
                                                <div class="mhr"></div>
                                                <div class="button_line_gray"><a
                                                        href="pages/form_data.html"><span>表單資料維護</span></a></div>
                                                <div class="mhr"></div>
                                                <div class="button_line_gray"><a
                                                        href="pages/record.html"><span>操作記錄</span></a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
	
	
	
	
	<div class="help-block"></div>
	<%@ include file="../include/footer.jsp"%>
    
	
	
	
	
</body>
</html>