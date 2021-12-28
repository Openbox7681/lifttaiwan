<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/custom.js"></script>
 
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c01.js"></script>

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
                                                <label>主領域</label>
                                                <div class="form-chec">
                                                    <input class="form-check-input" id="flexCheckDefault"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault">所有領域</label>
                                                    <div class="main-detail" id="main-detail"><span class="su_info"
                                                            data-index="a">資訊及數位相關產業</span><span class="su_sec"
                                                            data-index="b">資安卓越產業</span><span class="su_healthy"
                                                            data-index="c">臺灣精準健康戰略產業</span><span class="su_safe"
                                                            data-index="d">國防及戰略產業</span><span class="su_power"
                                                            data-index="e">綠電及再生能源產業</span><span class="su_livelihood"
                                                            data-index="f">民生及戰備產業</span><span class="su_other"
                                                            data-index="g">其他產業</span></div>
                                                </div>
                                            </div>
                                            <div class="line1"></div>
                                            <div class="sub-subject" id="it_subject">
                                                <label>次領域</label>
                                            </div>
                                            <div class="keyword-block">
                                                <label>關鍵字</label>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault1"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault1">關鍵字1</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault2"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault2">關鍵字2</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault3"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault3">關鍵字3</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault4"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault4">關鍵字4</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault5"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault5">關鍵字5</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault6"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault6">關鍵字6</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault7"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault7">關鍵字7</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault8"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault8">關鍵字8</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" id="flexCheckDefault9"
                                                        type="checkbox" value="">
                                                    <label class="form-check-label" for="flexCheckDefault9">關鍵字9</label>
                                                </div>
                                                <div class="more_item hcenter"><a href="#">
                                                        <p>展開更多</p>
                                                    </a></div>
                                            </div>
                                        </div>
                                        <div class="search_btn hright">
                                            <div class="button_fill_orange btn_m">
                                                <p>送出查詢</p>
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
                                        <h5>查詢結果</h5>
                                    </div>
                                </div>
                                <div class="col-sm-12 exportbtn">
                                    <div class="button_line_orange btn_s">
                                        <p>圖表匯出</p>
                                    </div>
                                    <div class="button_line_orange btn_s">
                                        <p>報表匯出 </p>
                                    </div>
                                </div>
                                <div class="col-lg-12 exportbtn">
                                    <div class="chart">
                                        <!--<img src="../img/img_highcharts.jpg">-->
                                        <div id="relation" style="height: 800px"></div>
                                    </div>
                                    <div class="button_fill_orange btn_s" type="button" data-bs-toggle="modal"
                                        data-bs-target="#suggest">
                                        <p>未來合作建議 </p>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>國家別</h5>
                                    </div>
                                    <div class="chart">
                                        <!--<img src="../img/img_bubble-chart.jpg">-->

                                        <div id="country" style="height: 500px"></div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="index_title">
                                        <h5>相關指標報表</h5>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-6">
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
	

	<%----%>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>