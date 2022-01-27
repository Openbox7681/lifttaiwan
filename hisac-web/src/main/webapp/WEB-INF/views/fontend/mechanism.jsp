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
				<option id="台灣" value="台灣">中華民國</option>
				<option id="日本" value="日本">日本</option>
				<option id="中國" value="中國">中國</option>
				<option id="越南" value="越南">越南</option>
				<option id="馬來西亞" value="馬來西亞">馬來西亞</option>
				<option id="俄羅斯" value="俄羅斯">俄羅斯</option>
				<option id="印度" value="印度">印度</option>
				<option id="德國" value="德國">德國</option>
				<option id="法國" value="法國">法國</option>
				<option id="美國" value="美國">美國</option>
				<option id="埃及" value="埃及">埃及</option>
				<option id="宏都拉斯" value="宏都拉斯">宏都拉斯</option>
				<option id="孟加拉" value="孟加拉">孟加拉</option>
				<option id="挪威" value="挪威">挪威</option>			
				<option id="葡萄牙" value="葡萄牙">葡萄牙</option>
				<option id="捷克" value="捷克">捷克</option>
				<option id="瑞士" value="瑞士">瑞士</option>
				<option id="保加利亞" value="保加利亞">保加利亞</option>
				<option id="義大利" value="義大利">義大利</option>
				<option id="亞塞拜然" value="亞塞拜然">亞塞拜然</option>
				<option id="克羅埃西亞" value="克羅埃西亞">克羅埃西亞</option>
				<option id="史瓦濟蘭" value="史瓦濟蘭">史瓦濟蘭</option>
				<option id="斯洛伐克" value="斯洛伐克">斯洛伐克</option>
				<option id="澳門" value="澳門">澳門</option>
				<option id="瑞典" value="澳門">瑞典</option>
				<option id="伊朗" value="伊朗">伊朗</option>
				<option id="委內瑞拉" value="委內瑞拉">委內瑞拉</option>
				<option id="塞浦路斯" value="塞浦路斯">塞浦路斯</option>
				<option id="立陶宛" value="立陶宛">立陶宛</option>
				<option id="蘇聯" value="蘇聯">蘇聯</option>
				<option id="西班牙" value="西班牙">西班牙</option>		
				<option id="喀麥隆" value="喀麥隆">喀麥隆</option>
				<option id="智利" value="智利">智利</option>
				<option id="大陸" value="大陸">大陸</option>
				<option id="蒙古" value="蒙古">蒙古</option>
				<option id="新加坡" value="新加坡">新加坡</option>
				<option id="西德" value="西德">西德</option>
				<option id="羅馬尼亞" value="羅馬尼亞">羅馬尼亞</option>
				<option id="亞美尼亞" value="亞美尼亞">亞美尼亞</option>
				<option id="香港" value="香港">香港</option>
				<option id="拉脫維亞" value="拉脫維亞">拉脫維亞</option>
				<option id="斯洛維尼亞" value="斯洛維尼亞">斯洛維尼亞</option>
				<option id="尼泊爾" value="尼泊爾">尼泊爾</option>
				<option id="摩爾多瓦" value="摩爾多瓦">摩爾多瓦</option>
				<option id="以色列" value="以色列">以色列</option>
				<option id="波蘭" value="波蘭">波蘭</option>
				<option id="比利時" value="比利時">比利時</option>
				<option id="沙烏地阿拉伯" value="沙烏地阿拉伯">沙烏地阿拉伯</option>
				<option id="南韓" value="南韓">南韓</option>
				<option id="印尼" value="印尼">印尼</option>
				<option id="布吉納法索" value="布吉納法索">布吉納法索</option>
				<option id="塞內加爾" value="塞內加爾">塞內加爾</option>
				<option id="喬治亞共和國" value="喬治亞共和國">喬治亞共和國</option>
				<option id="墨西哥" value="墨西哥">墨西哥</option>
				<option id="愛爾蘭" value="愛爾蘭">愛爾蘭</option>
				<option id="加拿大" value="加拿大">加拿大</option>
				<option id="巴西" value="巴西">巴西</option>
				<option id="摩洛哥" value="摩洛哥">摩洛哥</option>
				<option id="希臘" value="希臘">希臘</option>
				<option id="哥倫比亞" value="哥倫比亞">哥倫比亞</option>
				<option id="迦納" value="迦納">迦納</option>
				<option id="烏克蘭" value="烏克蘭">烏克蘭</option>
				<option id="奧地利" value="奧地利">奧地利</option>
				<option id="秘魯" value="秘魯">秘魯</option>
				<option id="南非" value="南非">南非</option>
				<option id="烏茲別克" value="烏茲別克">烏茲別克</option>
				<option id="澳大利亞" value="澳大利亞">澳大利亞</option>
				<option id="匈牙利" value="匈牙利">匈牙利</option>
				<option id="突尼西亞" value="突尼西亞">突尼西亞</option>
				<option id="瓜地馬拉" value="瓜地馬拉">瓜地馬拉</option>
				<option id="芬蘭" value="芬蘭">芬蘭</option>
				<option id="塞爾維亞" value="塞爾維亞">塞爾維亞</option>
				<option id="泰國" value="泰國">泰國</option>
				<option id="牙買加" value="牙買加">牙買加</option>
				<option id="紐西蘭" value="紐西蘭">紐西蘭</option>
				<option id="白俄羅斯" value="白俄羅斯">白俄羅斯</option>
				<option id="土耳其" value="土耳其">土耳其</option>
				<option id="衣索比亞" value="衣索比亞">衣索比亞</option>
				<option id="阿爾及利亞" value="阿爾及利亞">阿爾及利亞</option>
				<option id="伊拉克" value="伊拉克">伊拉克</option>
				<option id="菲律賓" value="菲律賓">菲律賓</option>
				<option id="巴基斯坦" value="巴基斯坦">巴基斯坦</option>
				<option id="汶萊" value="汶萊">汶萊</option>
				
				
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