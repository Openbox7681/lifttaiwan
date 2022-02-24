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
<%-- <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/main.js"></script>
 --%><script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/map.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/echarts.min.js"></script>


<%-- 

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 <script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/5.5.2bootbox.min.js"></script> --%>

<!-- <script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.g2-3.5.1/dist/g2.min.js"></script>
<script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.data-set-0.10.1/dist/data-set.min.js"></script> -->

<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	<div class="container" id="mapadjust">
		<div id="bgblock">
            <section id="map">
            	<div class="map_style">
					<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/index_map.svg">                    
                    <div class="s1 s max_point"></div>
                    <div class="box country4"> <span>台灣:24456人</span></div>
                    <div class="s2 s"></div>
                    <div class="box country5"><span>德國:133人</span></div>
                    <div class="t1 t"></div>
                    <div class="box country6"><span>馬來西亞:202人</span></div>
                    <div class="t2 t"></div>
                    <div class="box country7"><span>中國:0人</span></div>
                    <div class="t3 t"></div>
                    <div class="box country8"><span>法國:159人</span></div>
                    <div class="u1 u"></div>
                    <div class="box country9"><span>印度:3016人</span></div>
                    <div class="u3 u"></div>
                    <div class="box country11"><span>俄羅斯:321人</span></div>
                    <div class="v1 v"></div>
                    <div class="box country13"><span>越南:235人</span></div>
                    <div class="o1 o"></div>
                    <div class="box country14"><span>美國:904人</span></div>
                    <div class="o2 o mid_point"></div>
                    <div class="box country15"><span>日本:396人</span></div>
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
                        </div>
                        <a class="col-md-2 col-xs-6" id="mechanismHref">
                            <div class="cube_outer">
                                <div class="mechanism"></div>
                                <p>國際機構數</p>
                                <h2 id="mechanism"></h2>
                            </div>
                        </a>
                        <a class="col-md-2 col-xs-6" id="resultsHref">
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
                                            <li ng-repeat="item in computerData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in computerData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in computerData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
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
                                        
                                    </ul>
                                </div>
                                <div class="col-md-4">
                                    <ul class="area_tabs pink">
                                        
                                    </ul>
                                </div>
                                <div class="col-md-4">
                                    <ul class="area_tabs pink">
                                        
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody2" role="tabpanel" aria-labelledby="tab2"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in healthData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in healthData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in healthData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody3" role="tabpanel" aria-labelledby="tab3"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in warData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in warData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in warData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody4" role="tabpanel" aria-labelledby="tab4"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in greenData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in greenData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in greenData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody5" role="tabpanel" aria-labelledby="tab5"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in lifeData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in lifeData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in lifeData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                        </div>
                        <div class="tab-pane fade" id="tabBody6" role="tabpanel" aria-labelledby="tab6"
                            aria-hidden="true" tabindex="0">
                            <div class="row">
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in otherData"
                                            	ng-if="$index < 7"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in otherData"
                                            	ng-if="$index > 6 && $index <14"> 
                                            	<span>{{item.data}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="area_tabs">
                                            <li ng-repeat="item in otherData"
                                            	ng-if="$index > 13"> 
                                            	<span>{{item.data}}</span>
                                            </li>
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
                                <span>各國學術論文國際合作比例</span></a>
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
                    </div>
                    <a class="moreinfo" 
                    	href="<c:out value="${pageContext.request.contextPath}" />/fontend/videoInfo">
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
                    </div>
                    <a class="moreinfo" 
                    	href="<c:out value="${pageContext.request.contextPath}" />/fontend/intCoop">
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
	<%@ include file="../include/footer_fondtend.jsp"%>
</body>
</html>