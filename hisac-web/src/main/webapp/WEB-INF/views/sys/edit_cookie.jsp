<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%-- <%@ include file="../include/head_index.jsp"%>
 --%>
<%@ include file="../include/head.jsp"%>

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/edit_cookie.js"></script>
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
          <h5>Cookie政策資料修改</h5>
          <div class="line_div"></div>
          <p>{{title}}</p>
          
          <input class="form-control" type="text" placeholder="輸入修改內容" id="title" name="title" ng-model = "title"
               aria-label="詳閱內容" aria-describedby="basic-addon2">
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