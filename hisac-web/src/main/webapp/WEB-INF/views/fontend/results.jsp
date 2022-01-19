<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/reset.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/5.0.2bootstrap.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/all.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/new.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/basicstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/mainstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/responsive.css">
<script src="${pageContext.request.contextPath}/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/filiter_keyword_none.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/main.js"> </script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/echarts.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/results.js"></script>

<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	<section id="banner_content">
      <div class="banner">
        <h2>研究成果</h2>
      </div>
    </section>
    <section id="main_content">
      <div class="container">
        <div class="row">
          <div class="col-md-4 offset-md-8">
            <div class="filiter_button" type="button" data-bs-toggle="modal" data-bs-target="#myModal"><span>研究成果篩選</span></div>
          </div>
          <div class="col-lg-12 none">
            <div id="filiter">
              <div class="filiter_line_dropdown">
                <h6>國家</h6>
                <div id="countysearch">
                  <div class="accordion accordion-flush" id="accordionFlushExample">
                    <div class="accordion-item">
                      <h2 class="accordion-header" id="flush-headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">全世界</button>
                      </h2>
                      <div class="accordion-collapse collapse" id="flush-collapseOne" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                          <div class="form-check">
                            <input class="form-check-input" id="台灣" type="checkbox" name="checkbox1" value="台灣">
                            <label class="form-check-label" for="country">台灣</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="日本" type="checkbox" name="checkbox1" value="日本">
                            <label class="form-check-label" for="country1">日本</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="中國" type="checkbox" name="checkbox1" value="中國">
                            <label class="form-check-label" for="country2">中國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="越南" type="checkbox" name="checkbox1" value="越南">
                            <label class="form-check-label" for="country3">越南</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="馬來西亞" type="checkbox" name="checkbox1" value="馬來西亞">
                            <label class="form-check-label" for="country4">馬來西亞</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="俄羅斯" type="checkbox" name="checkbox1" value="俄羅斯">
                            <label class="form-check-label" for="country5">俄羅斯</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="印度" type="checkbox" name="checkbox1" value="印度">
                            <label class="form-check-label" for="country6">印度</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="德國" type="checkbox" name="checkbox1" value="德國">
                            <label class="form-check-label" for="country7">德國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="法國" type="checkbox" name="checkbox1" value="法國">
                            <label class="form-check-label" for="country8">法國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="美國" type="checkbox" name="checkbox1" value="美國">
                            <label class="form-check-label" for="country9">美國</label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <h6>六大領域</h6>
                <div class="main-block">
                  <div class="main-subject">
                    <div class="row g-0">
                      <div class="col-lg-12"> 
                        <div class="main-detail" id="main-detail">
                          <div class="form-check">
                            <div class="all_area_checkbox">
                              <input class="form-check-input" id="flexCheckDefault" type="checkbox" value="">
                              <label class="form-check-label" for="flexCheckDefault">所有領域</label>
                            </div>
                          </div>
                          	<span class="su_info" data-index="a">資訊及數位相關產業</span>
                          	<span class="su_sec" data-index="b">資安卓越產業</span>
                          	<span class="su_healthy" data-index="c">臺灣精準健康戰略產業</span>
                          	<span class="su_safe" data-index="d">國防及戰略產業</span>
                          	<span class="su_power" data-index="e">綠電及再生能源產業</span>
                          	<span class="su_livelihood" data-index="f">民生及戰備產業</span>
                          	<span class="su_other" data-index="g">其他產業</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="line2"></div>
                  <div class="sub-subject"></div>
                </div>
                <div class="search_btn hright">
                  <div class="button_fill_orange btn_m">
                    <p ng-click="queryData()">送出查詢</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section id="results_content">
      <div class="container">
        <div class="title_outer">
          
        </div>
      </div>
      <div class="container">
        <div class="col-lg-12" ng-repeat="item in allitems">
          <div class="outer">
            <div class="row">
              <div class="col-md-12" >
                <h5>{{item.title}}</h5>
                <p>{{item.author}}</p>
                <div class="keybtn" ng-repeat="tag in alltags" 
                	ng-if="item.paper_SerialNumber == tag.paper_SerialNumber">
                	<span>{{tag.tag}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="col-md-12">
              <div id="filiter">
                <div class="filiter_lightbox_dropdown">
                  <h6>為 研究成果 進行塞選</h6>
                  <p class="result_lightbox">國家</p>
                  <div id="countysearch">
                    <div class="accordion accordion-flush" id="accordionFlushExample">
                      <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingOne">
                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">全世界</button>
                        </h2>
                        <div class="accordion-collapse collapse" id="flush-collapseOne" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                          <div class="accordion-body">
                          <div class="form-check">
                            <input class="form-check-input" id="台灣" type="checkbox" name="checkbox3" value="台灣">
                            <label class="form-check-label" for="country">台灣</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="日本" type="checkbox" name="checkbox3" value="日本">
                            <label class="form-check-label" for="country1">日本</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="中國" type="checkbox" name="checkbox3" value="中國">
                            <label class="form-check-label" for="country2">中國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="越南" type="checkbox" name="checkbox3" value="越南">
                            <label class="form-check-label" for="country3">越南</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="馬來西亞" type="checkbox" name="checkbox3" value="馬來西亞">
                            <label class="form-check-label" for="country4">馬來西亞</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="俄羅斯" type="checkbox" name="checkbox3" value="俄羅斯">
                            <label class="form-check-label" for="country5">俄羅斯</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="印度" type="checkbox" name="checkbox3" value="印度">
                            <label class="form-check-label" for="country6">印度</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="德國" type="checkbox" name="checkbox3" value="德國">
                            <label class="form-check-label" for="country7">德國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="法國" type="checkbox" name="checkbox3" value="法國">
                            <label class="form-check-label" for="country8">法國</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" id="美國" type="checkbox" name="checkbox3" value="美國">
                            <label class="form-check-label" for="country9">美國</label>
                          </div>
                            
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <p class="result_lightbox">六大領域</p>
                  
                  <div class="line2"></div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea1" aria-expanded="false" aria-controls="filiterarea1"><i class="fas fa-chevron-up"></i><span>資訊及數位相關產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea1">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="AI晶片" name="checkbox4">
                        <label class="form-check-label" for="filiter1"></label>AI晶片
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter2" type="checkbox" value="人工智慧" name="checkbox4">
                        <label class="form-check-label" for="filiter2"></label>人工智慧
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter3" type="checkbox" value="半導體材料" name="checkbox4">
                        <label class="form-check-label" for="filiter3"></label> 半導體材料
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter4" type="checkbox" value="半導體設備" name="checkbox4">
                        <label class="form-check-label" for="filiter4"></label>半導體設備
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter5" type="checkbox" value="行動通訊" name="checkbox4">
                        <label class="form-check-label" for="filiter5"></label>行動通訊
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter6" type="checkbox" value="系統軟體" name="checkbox4">
                        <label class="form-check-label" for="filiter6"></label>系統軟體
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter7" type="checkbox" value="物聯網(含感測器)" name="checkbox4">
                        <label class="form-check-label" for="filiter7"></label>物聯網(含感測器)
                      </div>
                       
                      <div class="form-check">
                        <input class="form-check-input" id="filiter8" type="checkbox" value="晶圓代工" name="checkbox4">
                        <label class="form-check-label" for="filiter8"></label>晶圓代工
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter9" type="checkbox" value="智慧運輸" name="checkbox4">
                        <label class="form-check-label" for="filiter9"></label>智慧運輸
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter10" type="checkbox" value="量子科技" name="checkbox4"> 
                        <label class="form-check-label" for="filiter10"></label>量子科技
                      </div>
                      
                       <div class="form-check">
                        <input class="form-check-input" id="filiter11" type="checkbox" value="資安支援" name="checkbox4">
                        <label class="form-check-label" for="filiter11"></label>資安支援
                      </div>
                      
                       <div class="form-check">
                        <input class="form-check-input" id="filiter12" type="checkbox" value="資安防護" name="checkbox4">
                        <label class="form-check-label" for="filiter12"></label>資安防護
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter13" type="checkbox" value="資料中心(含伺服器)" name="checkbox4">
                        <label class="form-check-label" for="filiter13"></label>資料中心(含伺服器)
                      </div>
                      
                       <div class="form-check">
                        <input class="form-check-input" id="filiter14" type="checkbox" value="運動科技" name="checkbox4">
                        <label class="form-check-label" for="filiter14"></label>運動科技
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter15" type="checkbox" value="零接觸生活科技" name="checkbox4">
                        <label class="form-check-label" for="filiter15"></label>零接觸生活科技
                      </div>
                      
                       <div class="form-check">
                        <input class="form-check-input" id="filiter16" type="checkbox" value="網路通訊" name="checkbox4">
                        <label class="form-check-label" for="filiter16"></label> 網路通訊
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter17" type="checkbox" value="數據科學" name="checkbox4">
                        <label class="form-check-label" for="filiter17"></label> 數據科學
                      </div>
                      
                      <div class="form-check">
                        <input class="form-check-input" id="filiter18" type="checkbox" value="衛星通訊" name="checkbox4">
                        <label class="form-check-label" for="filiter18"></label> 衛星通訊
                      </div>
                      
                       <div class="form-check">
                        <input class="form-check-input" id="filiter19" type="checkbox" value="積體電路設計" name="checkbox4">
                        <label class="form-check-label" for="filiter19"></label> 積體電路設計
                      </div>
                            
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea2" aria-expanded="false" aria-controls="filiterarea2"><i class="fas fa-chevron-up"></i><span>資安卓越產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea2">
                    <div class="card card-body">
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea3" aria-expanded="false" aria-controls="filiterarea3"><i class="fas fa-chevron-up"></i><span>臺灣精準健康戰略產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea3">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="再生醫療" name="checkbox4">
                        <label class="form-check-label" for="filiter1"></label> 再生醫療
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter2" type="checkbox" value="健康管理" name="checkbox4">
                        <label class="form-check-label" for="filiter2"></label>健康管理
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter3" type="checkbox" value="智慧醫材" name="checkbox4">
                        <label class="form-check-label" for="filiter3"></label>智慧醫材
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter4" type="checkbox" value="智慧醫療" name="checkbox4">
                        <label class="form-check-label" for="filiter4"></label>智慧醫療
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter5" type="checkbox" value="精準醫療(含遠距醫療)" name="checkbox4">
                        <label class="form-check-label" for="filiter5"></label>精準醫療(含遠距醫療)
                      </div>
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea4" aria-expanded="false" aria-controls="filiterarea4"><i class="fas fa-chevron-up"></i><span>國防及戰略產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea4">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="太空發射技術" name="checkbox4">
                        <label class="form-check-label" for="filiter1"></label> 太空發射技術
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter2" type="checkbox" value="太空檢測技術" name="checkbox4">
                        <label class="form-check-label" for="filiter2"></label> 太空檢測技術
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter3" type="checkbox" value="推進系統技術" name="checkbox4">
                        <label class="form-check-label" for="filiter3"></label> 推進系統技術
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter4" type="checkbox" value="船舶核心技術" name="checkbox4">
                        <label class="form-check-label" for="filiter4"></label>船舶核心技術
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter5" type="checkbox" value="發動機技術" name="checkbox4">
                        <label class="form-check-label" for="filiter5"></label>發動機技術
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter6" type="checkbox" value="衛星通訊"  name="checkbox4">
                        <label class="form-check-label" for="filiter6"></label>衛星通訊
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter7" type="checkbox" value="戰機關鍵技術"  name="checkbox4">
                        <label class="form-check-label" for="filiter7"></label>戰機關鍵技術
                      </div>
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea5" aria-expanded="false" aria-controls="filiterarea5"><i class="fas fa-chevron-up"></i><span>綠電及再生能源產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea5">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="太陽光電" name="checkbox4">
                        <label class="form-check-label" for="filiter1"></label>太陽光電
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter2" type="checkbox" value="水下基礎" name="checkbox4">
                        <label class="form-check-label" for="filiter2"></label>水下基礎
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter3" type="checkbox" value="風機系統" name="checkbox4">
                        <label class="form-check-label" for="filiter3"></label>風機系統
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter4" type="checkbox" value="海事工程" name="checkbox4">
                        <label class="form-check-label" for="filiter4"></label>海事工程
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter5" type="checkbox" value="電力設備" name="checkbox4">
                        <label class="form-check-label" for="filiter5"></label>電力設備
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter6" type="checkbox" value="離岸風電" name="checkbox4">
                        <label class="form-check-label" for="filiter6"></label>離岸風電
                      </div>
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea6" aria-expanded="false" aria-controls="filiterarea6"><i class="fas fa-chevron-up"></i><span>民生及戰備產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea6">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="民生物資與食品加工產業鏈" name="checkbox4">
                        <label class="form-check-label" for="filiter1"></label>民生物資與食品加工產業鏈
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter2" type="checkbox" value="再生能源" name="checkbox4">
                        <label class="form-check-label" for="filiter2"></label>再生能源
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter3" type="checkbox" value="安全庫存" name="checkbox4">
                        <label class="form-check-label" for="filiter3"></label>安全庫存
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" id="filiter4" type="checkbox" value="救災資源資料庫" name="checkbox4" >
                        <label class="form-check-label" for="filiter4"></label>救災資源資料庫
                      </div>
                      
                    </div>
                  </div>
                  <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse" data-bs-target="#filiterarea7" aria-expanded="false" aria-controls="#filiterarea7"><i class="fas fa-chevron-up"></i><span>其他產業</span></button>
                  <div class="collapse filiterlightbox_form_check" id="filiterarea7">
                    <div class="card card-body">
                      <div class="form-check">
                        <input class="form-check-input" id="filiter1" type="checkbox" value="其他" name="checkbox4" >
                        <label class="form-check-label" for="filiter1"></label>其他
                      </div>           
                    </div>
                  </div>
                  <!--saerch btn------------------------>
                  <div class="search_btn hcenter">
                    <div class="button_fill_orange btn_m" id="ad_btn_search">
                      <p ng-click="mqueryData()" data-bs-dismiss="modal" aria-label="Close"
                      
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
    
    
    
    
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>