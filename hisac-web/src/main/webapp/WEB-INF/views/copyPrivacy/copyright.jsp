<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/edit_copyright.js"></script>


<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	
    <section id="banner_content">
      <div class="banner">
        <h2>版權宣告</h2>
      </div>
    </section>
    <section id="copyright_content">
      <div class="container">
        <div class="col-md-12">
          <h5>授權方式及範圍</h5>
          <div class="line_div"></div>
          <p>{{title}}</p>
        </div>
        <div class="col-md-12">
          <h5>相關事項說明</h5>
          <div class="line_div"></div>
          <p>
          {{item1_1}}  
          </p>
          <p>{{item1_2}}</p>
          <p>{{item1_3}}</p>
        </div>
        <div class="col-md-12">
          <h5>應注意不得侵害第三人之著作人格權(包括姓名表示權及禁止不當變更權)。</h5>
        </div>
        <div class="col-md-12">
          <h5>使用本授權提供之資料與素材，不得惡意變更其相關資訊，若利用後所展示之資訊與原資料與素材不符，使用者須自負民事、刑事上之法律責任。</h5>
        </div>
        <div class="col-md-12">
          <h5>本網站之授權，並不授予使用者代表本機關建議、認可或贊同其加值衍生物之地位。</h5>
          <div class="divider">     </div>
        </div>
      </div>
    </section>
	<div class="footer_space"></div>
	<%@ include file="../include/footer_fondtend.jsp"%>
</body>
</html>