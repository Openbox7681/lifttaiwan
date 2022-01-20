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
      <div id="privacy_content" class="col-lg-9">
      <div class="col-md-12 hcenter">
          <div class="icon_taiwan" >
          	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_logo.svg">
          </div>
          <div class="explain">
            <h5>{{title}}</h5>
            <input class="form-control" type="text" placeholder="輸入修改內容" id="title" name="title" ng-model = "title"
               aria-label="詳閱內容" aria-describedby="basic-addon2">
          </div>
          <div class="col-md-12">
          <h6>第一條、隱私權保護政策的適用範圍</h6>
          <div class="line_div"></div><span>{{item1}}</span>
          <textarea class="form-control" placeholder="輸入修改內容"  id="item1" name="item1" rows="2" cols="50" ng-model = "item1"></textarea>
        </div>
        <div class="col-md-12">
          <h6>第二條、資料之蒐集與使用方式</h6>
          <div class="line_div"></div>
         	<p>{{item2_1}}</p>
          	<textarea class="form-control" placeholder="輸入修改內容"  id="item2_1" name="item2_1" rows="2" cols="50" ng-model = "item2_1"></textarea>
         	<p>{{item2_2}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item2_2" name="item2_2" rows="2" cols="50" ng-model = "item2_2"></textarea>
         	<p>{{item2_3}}</p>
         	<textarea class="form-control" placeholder="輸入修改內容"  id="item2_3" name="item2_3" rows="2" cols="50" ng-model = "item2_3"></textarea>
         	<p>{{item2_4}}</p>
         	<textarea class="form-control" placeholder="輸入修改內容"  id="item2_4" name="item2_4" rows="2" cols="50"  ng-model = "item2_4"></textarea>       
         	<p>{{item2_5}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item2_5" name="item2_5" rows="2" cols="50"  ng-model = "item2_5"></textarea>

        </div>
        
        <div class="col-md-12">
        <h6>第三條、資料之管理與維護</h6>
          <div class="line_div"></div>
          <p>{{item3_1}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item3_1" name="item3_1" rows="2" cols="50" ng-model = "item3_1"></textarea>
          <p>{{item3_2}}</p>     
			<textarea class="form-control" placeholder="輸入修改內容"  id="item3_2" name="item3_2" rows="2" cols="50" ng-model = "item3_2"></textarea>
		</div>
		<div class="col-md-12">
          <h6>第四條、 資料之保護</h6>
          <div class="line_div"></div>
          <p>{{item4_1}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item4_1" name="item4_1" rows="2" cols="50" ng-model = "item4_1"></textarea>
          <p>{{item4_2}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item4_2" name="item4_2" rows="2" cols="50" ng-model = "item4_2"></textarea>
          <p>{{item4_3}}</p>
          	<textarea class="form-control" placeholder="輸入修改內容"  id="item4_3" name="item4_3" rows="2" cols="50" ng-model = "item4_3"></textarea>
          <p>{{item4_4}}</p>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item4_4" name="item4_4" rows="2" cols="50" ng-model = "item4_4"></textarea>
        </div>
        <div class="col-md-12">
          <h6>第五條、隱私權保護政策之修正</h6>
          <div class="line_div"></div><span>{{item5_1}}</span>
			<textarea class="form-control" placeholder="輸入修改內容"  id="item5_1" name="item5_1" rows="2" cols="50" ng-model = "item5_1"></textarea>   
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