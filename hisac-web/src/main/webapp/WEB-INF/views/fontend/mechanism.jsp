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
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/mechanism.js"></script>

<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	<section id="banner_content">
      <div class="banner">
        <h2>國際機構數</h2>
      </div>
    </section>
    <section id="main_content">
      <div class="container">
        <div class="col-sm-12 intadjust">
        <div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line subsystem_data_color">
                    
                    <h6>國家</h6>
          <div class="choose_item">
            <select id="country" name="country" aria-label="Default select example"
				ng-model="country" class="form-select">
				<option id="全部" value="">全部</option>
				<option id="台灣" value="台灣">台灣</option>
				<option id="日本" value="日本">日本</option>
				<option id="中國" value="中國">中國</option>
				<option id="越南" value="越南">越南</option>
				<option id="馬來西亞" value="馬來西亞">馬來西亞</option>
				<option id="俄羅斯" value="俄羅斯">俄羅斯</option>
				<option id="印度" value="印度">印度</option>
				<option id="德國" value="德國">德國</option>
				<option id="法國" value="法國">法國</option>
				<option id="美國" value="美國">美國</option>
            </select>
          </div>
          <div class="search_btn hcenter">
                      <div class="button_fill_orange btn_m" ng-click="queryData()">
                        <p>查詢</p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-click="clearData()"><i class="fas fa-undo-alt"></i>
                        <p>清空</p>
                      </div>
                    </div>
                    
                  </div>
                </div>
              </div>
        
          <div id="init">
          <div class="row">
			<div class="col-xs-12 col-md-11"  style = "padding : 3px">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select_init.jsp"%>
			</div>
		  </div>
              <div class="col-lg-12">
                <div class="row">              
                  <div class="col-lg-12">
                    <div class="row">
                      <div class="col-lg-12 adjust_pos">
                        <div class="form_chart">
                          <div class="form_outbor">
                            <table class="table caption-top">
                              <thead class="tablecolor">
                                <tr>
                                  <th scope="col">項次</th>
                                  <th scope="col">國家</th>
                                  <th scope="col">機構</th>
                                  <th scope="col">發表數</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr ng-repeat="item in allitems">
                                  <th scope="row">{{$index + 1}}</th>
                                  <td>{{item.country}}</td>
                                  <td>{{item.mechanism}}</td>
                                  <td>{{item.number}}</td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                          <%@ include file="./../include/table_row_empty.jsp"%>
				<div class="text-center">
					<paging class="pagination" page="currentPage" page-size="maxRows"
						total="total" show-prev-next="true" show-first-last="true"
						text-next-class="fas fa-step-forward"
						text-prev-class="fas fa-step-backward"
						text-first-class="fas fa-fast-backward"
						text-last-class="fas fa-fast-forward"
						paging-action="queryDataInit(page)"></paging>
				</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
          </div>
          </div>
          
          <div id="query" style="display:none;">
          <div class="row">
			<div class="col-xs-12 col-md-11"  style = "padding : 3px">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>
		  </div>
              <div class="col-lg-12">
                <div class="row">              
                  <div class="col-lg-12">
                    <div class="row">
                      <div class="col-lg-12 adjust_pos">
                        <div class="form_chart">
                          <div class="form_outbor">
                            <table class="table caption-top">
                              <thead class="tablecolor">
                                <tr>
                                  <th scope="col">項次</th>
                                  <th scope="col">國家</th>
                                  <th scope="col">機構</th>
                                  <th scope="col">發表數</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr ng-repeat="item in allitems">
                                  <th scope="row">{{$index + 1}}</th>
                                  <td>{{item.country}}</td>
                                  <td>{{item.mechanism}}</td>
                                  <td>{{item.number}}</td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                          <%@ include file="./../include/table_row_empty.jsp"%>
				<div class="text-center">
					<paging class="pagination" page="currentPage" page-size="maxRows"
						total="total" show-prev-next="true" show-first-last="true"
						text-next-class="fas fa-step-forward"
						text-prev-class="fas fa-step-backward"
						text-first-class="fas fa-fast-backward"
						text-last-class="fas fa-fast-forward"
						paging-action="queryData(page)"></paging>
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
	<div class="footer_space"></div>
	<%@ include file="../include/footer_fondtend.jsp"%>
</body>
</html>