<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%-- <%@ include file="../include/head_index.jsp"%>
 --%>
<%@ include file="../include/head.jsp"%>

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/edit_copyright.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/mainstyle.css">
	



<body class="no-skin" ng-controller="getAppController" id="stop">
	
		<%@ include file="../include/f_navbar.jsp"%>
		    <section id="main_content">
		
		<div class="container">
	
   		<div class="row">
         
      
      <div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
				
	<%@ include file="../include/slidebar.jsp"%>
      <div id="copyright_content" class="col-lg-9">
      <div class="col-md-12 container">
          <div class="col-md-12">
          <h5>授權方式及範圍</h5>
          <div class="line_div"></div>
          <p>{{title}}</p>
           
               
       <textarea class="form-control" placeholder="輸入修改內容"  id="item1_1" name="item1_1" rows="4" cols="50" ng-model = "title"></textarea>
               
               
        </div>
        
        <div class="col-md-12">
          <h5>相關事項說明</h5>
          <div class="line_div"></div>
          <p>
          {{item1_1}}
          </p>
          <textarea class="form-control" placeholder="輸入修改內容"  id="item1_1" name="item1_1" rows="2" cols="50" ng-model = "item1_1"></textarea>
          
          <p>{{item1_2}}</p>
          <textarea class="form-control" placeholder="輸入修改內容"  id="item1_2" name="item2_1" rows="2" cols="50" ng-model = "item1_2"></textarea>
          
          <p>{{item1_3}}</p>
          <textarea class="form-control" placeholder="輸入修改內容"  id="item1_3" name="item2_1" rows="2" cols="50" ng-model = "item1_3"></textarea>
          
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
        
        
        
        
        
        
          
		    <form name="myForm" id="main_content">	
				<div class="search_btn hcenter saveicon">	
				<div class="button_fill_orange btn_m" 
                  	ng-click="createOrUpdateData()"
                  	>
                    儲存
                 </div>
                </div>
           </form>
        
        </div>   

      </div>
      
      
      
    
        	</div>
    
    
    	</div>
    	
    	    </section>
    	
    	
    	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
    
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>