<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>


<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/edit_privacy.js"></script>


<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	
    <section id="banner_content">
      <div class="banner">
        <h2>隱私權宣告</h2>
      </div>
    </section>
    <section id="privacy_content">
      <div class="container">
        <div class="col-md-12 hcenter">
          <div class="icon_taiwan">
          	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_logo.svg">
          </div>
          <div class="explain">
            <h5>{{item1}}</h5>
          </div>
        </div>
        <div class="col-md-12">
          <h6>第一條、隱私權保護政策的適用範圍</h6>
          <div class="line_div"></div><span>{{item1}}</span>
        </div>
        <div class="col-md-12">
          <h6>第二條、資料之蒐集與使用方式</h6>
          <div class="line_div"></div>
         	<p>{{item2_1}}</p>
         	<p>{{item2_2}}</p>
         	<p>{{item2_3}}</p>
         	<p>{{item2_4}}</p>
         	<p>{{item2_5}}</p>
        </div>
        <div class="col-md-12">
          <h6>第三條、資料之管理與維護</h6>
          <div class="line_div"></div>
         	<p>{{item3_1}}</p>
         	<p>{{item3_2}}</p>
        </div>
        <div class="col-md-12">
          <h6>第四條、 資料之保護</h6>
          <div class="line_div"></div>
         	<p>{{item4_1}}</p>
         	<p>{{item4_2}}</p>
         	<p>{{item4_3}}</p>
         	<p>{{item4_4}}</p>
        </div>
        <div class="col-md-12">
          <h6>第五條、隱私權保護政策之修正</h6>
          <div class="line_div"></div><span>{{item5_1}}</span>
          <div class="divider">     </div>
        </div>
      </div>
    </section>
	<div class="footer_space"></div>
	<%@ include file="../include/footer_fondtend.jsp"%>
</body>
</html>