<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/news.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/index.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/main.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/map.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/echarts.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
 -->
<!--  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 -->
<!-- <script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.g2-3.5.1/dist/g2.min.js"></script>
<script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.data-set-0.10.1/dist/data-set.min.js"></script> -->

<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	<div class="container" id="mapadjust">
		<div id="bgblock">
            <section id="map">
            	<div class="map_style">
					<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/index_map.svg">                    
					<div class="d1 d"></div>
                    <div class="box country1"><span>巴拿馬</span></div>
                    <div class="s1 s max_point"></div>
                    <div class="box country4"> <span>台灣 </span></div>
                    <div class="s2 s"></div>
                    <div class="box country5"><span>德國</span></div>
                    <div class="t1 t"></div>
                    <div class="box country6"><span>馬來西亞</span></div>
                    <div class="t2 t"></div>
                    <div class="box country7"><span>中國</span></div>
                    <div class="t3 t"></div>
                    <div class="box country8"><span>法國</span></div>
                    <div class="u1 u"></div>
                    <div class="box country9"><span>印度</span></div>
                    <div class="u3 u"></div>
                    <div class="box country11"><span>俄羅斯</span></div>
                    <div class="v1 v"></div>
                    <div class="box country13"><span>越南</span></div>
                    <div class="o1 o"></div>
                    <div class="box country14"><span>美國</span></div>
                    <div class="o2 o mid_point"></div>
                    <div class="box country15"><span>日本</span></div>
                </div>
            </section>
            <section id="info_cube">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 countryinfo">
                            <div class="worldicon"></div>
                            <h4 class="change_text">過去10年中的國際優秀人才和研究成果</h4>
                            <div class="borline"></div>
                            <p>點擊上圖圓圈區域，閱覽各國合作詳細資料</p>
                        </div>
                        <div class="col-md-2 col-xs-6">
                            <div class="cube_outer">
                                <div class="inbound"></div>
                                <p>In-bound人數</p>
                                <h2 id="inbound"></h2>
                            </div>
                        </div>
                        <div class="col-md-2 col-xs-6">
                            <div class="cube_outer">
                                <div class="outbound"></div>
                                <p>Out-bound人數</p>
                                <h2 id="outbound"></h2>
                            </div>
                        </div><a class="col-md-2 col-xs-6" href="#">
                            <div class="cube_outer">
                                <div class="mechanism"></div>
                                <p>國際機構數</p>
                                <h2 id="mechanism"></h2>
                            </div>
                        </a><a class="col-md-2 col-xs-6" href="#">
                            <div class="cube_outer">
                                <div class="result"></div>
                                <p>研究成果</p>
                                <h2 id="result"></h2>
                            </div>
                        </a>
                    </div>
                </div>
            </section>
            <div class="container">
            	<section class="tabs t-tabs" id="fancyTabWidget">
                    <ul class="nav nav-tabs fancyTabs" role="tablist">
                        <li class="tab fancyTab active">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map0" id="tab0" href="#tabBody0" role="tab" aria-controls="tabBody0"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
									<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_technology.svg">                                </div>
                                <span class="hidden-xs">資訊數位相關產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map1" id="tab1" href="#tabBody1" role="tab" aria-controls="tabBody1"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_secure.svg">
                                </div>
                                <span class="hidden-xs">資安卓越產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map2" id="tab2" href="#tabBody2" role="tab" aria-controls="tabBody2"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_healthy.svg">
                                </div>
                                <span class="hidden-xs">臺灣精準健康戰略產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map3" id="tab3" href="#tabBody3" role="tab" aria-controls="tabBody3"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_safe.svg">
                                </div>
                                <span class="hidden-xs">國防及戰略產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map4" id="tab4" href="#tabBody4" role="tab" aria-controls="tabBody4"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_greenpower.svg">
                                </div>
                                <span class="hidden-xs">綠電及再生能源產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"></div>
                            </div><a class="map5" id="tab5" href="#tabBody5" role="tab" aria-controls="tabBody5"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_livelihood.svg">
                                </div>
                                <span class="hidden-xs">民生及戰備產業</span>
                            </a>
                            <div class="whiteBlock"></div>
                        </li>
                        <li class="tab fancyTab">
                            <div class="arrow-down">
                                <div class="arrow-down-inner"> </div>
                            </div><a class="map6" id="tab6" href="#tabBody6" role="tab" aria-controls="tabBody6"
                                aria-selected="true" data-toggle="tab" tabindex="0">
                                <div class="tabicon">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_other.svg">
                                </div>
                                <span class="hidden-xs">其他產業</span>
                            </a>
                            <div class="whiteBlock"> </div>
                        </li>
                    </ul>
                    <div class="tab-content fancyTabContent" id="myTabContent" aria-live="polite">
                        <div class="tab-pane fade active in" id="tabBody0" role="tabpanel" aria-labelledby="tab0"
                            aria-hidden="false" tabindex="0">
                            <div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li> <span>AI晶片：1388/人</span></li>
                                            <li> <span>人工智慧：8168/人</span></li>
                                            <li> <span>半導體材料：5981/人</span></li>
                                            <li> <span>半導體設備：725/人</span></li>
                                            <li> <span>行動通訊：552/人</span></li>
                                            <li> <span>系統軟體：4285/人</span></li>
                                            <li> <span>物聯網(含感測器)：352/人</span></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li> <span>晶圓代工：21/人</span></li>
                                            <li> <span>晶圓封測：5499/人</span></li>
                                            <li> <span>智慧運輸：24/人</span></li>
                                            <li> <span>量子科技：822/人</span></li>
                                            <li> <span>資安支援：14/人</span></li>
                                            <li> <span>資安防護：1/人</span></li>
                                            <li> <span>資料中心(含伺服器)：20/人</span></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li> <span>運動科技：1952/人</span></li>
                                            <li> <span>零接觸生活科技：115/人</span></li>
                                            <li> <span>網路通訊：33人</span></li>
                                            <li> <span>數位娛樂：4/人</span></li>
                                            <li> <span>數據科學：154/人</span></li>
                                            <li> <span>衛星通訊：970/人 </span></li>
                                            <li> <span>積體電路設計：115/人 </span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody1" role="tabpanel" aria-labelledby="tab1"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs pink">
                                        <li> <span>半導體材料：109/人</span></li>
                                        <li> <span>半導體設備：891/人</span></li>
                                        <li> <span>晶圓代工：548/人</span></li>
                                        <li> <span>晶圓封測：623/人</span></li>
                                        <li> <span>量子科技：764/人</span></li>
                                        <li> <span>零接觸生活科技：395/人</span></li>
                                    </ul>
                                </div>
                                <div class="col-md-4">
                                    <ul class="area_tabs pink">
                                        <li> <span>半導體材料：109/人</span></li>
                                        <li> <span>半導體設備：891/人</span></li>
                                        <li> <span>晶圓代工：548/人</span></li>
                                        <li> <span>晶圓封測：623/人</span></li>
                                        <li> <span>量子科技：764/人</span></li>
                                        <li> <span>零接觸生活科技：395/人</span></li>
                                    </ul>
                                </div>
                                <div class="col-md-4">
                                    <ul class="area_tabs pink">
                                        <li> <span>半導體材料：109/人</span></li>
                                        <li> <span>半導體設備：891/人</span></li>
                                        <li> <span>晶圓代工：548/人</span></li>
                                        <li> <span>晶圓封測：623/人</span></li>
                                        <li> <span>量子科技：764/人</span></li>
                                        <li> <span>零接觸生活科技：395/人 </span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody2" role="tabpanel" aria-labelledby="tab2"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs deepred">
                                        <li> <span>再生醫療：12256/人</span></li>
                                        <li> <span>健康管理：13423/人</span></li>
                                        <li> <span>智慧醫材：1794/人</span></li>
                                        <li> <span>智慧醫療：1668/人</span></li>
                                        <li> <span>精準醫療(含遠距醫療)：11103/人</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody3" role="tabpanel" aria-labelledby="tab3"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs yellow">
                                        <li> <span>太空發射技術：2007/人</span></li>
                                        <li> <span>太空檢測技術：2486/人</span></li>
                                        <li> <span>推進系統技術：14/人</span></li>
                                        <li> <span>船舶核心技術：825/人</span></li>
                                        <li> <span>發動機技術：66/人</span></li>
                                        <li> <span>衛星通訊：970/人</span></li>
                                    </ul>
                                </div>
                                <div class="col-md-4">
                                    <ul class="area_tabs yellow">
                                        <li> <span>戰機關鍵技術：1/人</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody4" role="tabpanel" aria-labelledby="tab4"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs green">
                                        <li> <span>太陽光電：10416/人</span></li>
                                        <li> <span>水下基礎：4714/人</span></li>
                                        <li> <span>風機系統：1405/人</span></li>
                                        <li> <span>海事工程：2034/人</span></li>
                                        <li> <span>電力設備：183/人</span></li>
                                        <li> <span>離岸風電：1383/人</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody5" role="tabpanel" aria-labelledby="tab5"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs lightblue">
                                        <li> <span>民生物資與食品加工鏈：11/人</span></li>
                                        <li> <span>再生能源：2219/人</span></li>
                                        <li> <span>安全庫存：11/人</span></li>
                                        <li> <span>救災資源資料庫：845/人</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody6" role="tabpanel" aria-labelledby="tab6"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                <div class="col-md-4">
                                    <ul class="area_tabs blue">
                                        <li> <span>其他：1403/人</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
		</div>
	</div>
	<section id="int_info">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title">
                        <h5>國際資訊查詢</h5>
                    </div>
                    <div class="line2"></div>
                    <div class="row">
                        <div class="col-md-6"><a class="button_fill_orange"
                                href="<c:out value="${pageContext.request.contextPath}" />/fontend/institution">
                                <span>六大領域Top20機構</span></a>
                            <div class="chart_area">
                                <!--<img class="fill" src="img/index_cube.jpg">-->
                                <div id="top20" style="width: 555px; height: 213px"></div>
                                <div class="sharebtn">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_share.svg">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6"><a class="button_fill_orange"
                                href="<c:out value="${pageContext.request.contextPath}" />/fontend/scholar">
                                <span>六大領域Top20學者</span></a>
                            <div class="chart_area">
                                <img class="table_img" src="<c:out value="${pageContext.request.contextPath}" />/resources/img/index_form.jpg">
                                <div class="sharebtn">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_share.svg">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6"><a class="button_fill_orange"
                                href="<c:out value="${pageContext.request.contextPath}" />/fontend/cooperate">
                                <span>台灣及其他國家國際合作表現</span></a>
                            <div class="chart_area">
                                <!--<img class="fill" src="img/index_bar.jpg">-->

                                <div id="country" style="width: 555px; height: 333px"></div>
                                <div class="sharebtn">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_share.svg">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6"><a class="button_fill_orange" 
                        	href="<c:out value="${pageContext.request.contextPath}" />/fontend/six">
                        	<span>六大領域國際網絡</span></a>
                            <div class="chart_area">
                                <!--<img class="fill" src="img/index_connect.jpg">-->
                                <div id="connect" style="width: 555px; height: 333px"></div>
                                <div class="sharebtn">
                                	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_share.svg">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="video_info">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title">
                        <h5>影音資訊</h5>
                    </div><a class="moreinfo" href="pages/video-info.html">
                        <p>更多資訊+</p>
                    </a>
                    <div class="line2"></div>
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-6" ng-repeat="item in allitems">
                            <div class="outer">
                                <div class="video_outer">
                                	<img src="{{item.Img}}">
                                	<a class="player" href="{{item.Url}}"></a>
                                </div>
                                <div class="video_content">
                                	<span>{{item.Date}}</span>
                                    <h6>{{item.Title}}</h6>
                                    <p>{{item.Description}}</p>
                                </div>
                                <div class="video_tag">
                                    <div class="keybtn" ng-repeat="tag in alltags" ng-if="item.Id == tag.Id">
                                    	<span>{{tag.Tag}}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="cooperate_case">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title">
                        <h5>國際合作案例</h5>
                    </div><a class="moreinfo" href="pages/int-coop.html">
                        <p>更多資訊+ </p>
                    </a>
                    <div class="line2"></div>
                    <div class="col-lg-4 col-md-4 col-sm-6" ng-repeat="item in allarticles">
                        <div class="outer">
                            <div class="video_outer"><img src="{{item.Img}}"></div>
                            <div class="video_content"><span>{{item.Date}}</span>
                                <h6>{{item.Title}}</h6>
                                <p>{{item.Description}}</p>
                            </div>
                            <div class="video_tag">
                                <div class="keybtn" ng-repeat="tag in allartTag" ng-if="item.Id == tag.Id">
                                	<span>{{tag.Tag}}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>