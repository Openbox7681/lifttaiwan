<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%-- <%@ include file="../include/head_index.jsp"%>
 --%>
<%@ include file="../include/head.jsp"%>

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/edit_privacy.js"></script>
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
          <p>為利各界廣為利用網站資料，科技部網站上刊載之所有資料與素材，其得受著作權保護之範圍，以無償、非專屬，得再授權之方式提供公眾使用，使用者得不限時間及地域，重製、改作、編輯、公開傳輸或為其他方式之利用，開發各種產品或服務（簡稱加值衍生物），此一授權行為不會嗣後撤回，使用者亦無須取得本機關之書面或其他方式授權；然使用時，應註明出處。   </p>
        </div>
        
        <div class="col-md-12">
          <h5>相關事項說明</h5>
          <div class="line_div"></div>
          <p>
          (一)本授權範圍僅及於著作權保護之範圍，不及於其他智慧財產權利，包括但不限於專利、商標、及機關標誌之提供。  
          </p>
          <p>(二)當事人自行公開或依法令公開之個人資料是否得被蒐集、處理及利用，使用者須自行依照個人資料保護法之相關規定，規劃並執行法律要求之相應措施。</p>
          <p>(三)部分的影音、圖像、樂譜、專人專案撰文或其他著作，經機關特別聲明須經同意方可使用者。         </p>
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