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
    
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>