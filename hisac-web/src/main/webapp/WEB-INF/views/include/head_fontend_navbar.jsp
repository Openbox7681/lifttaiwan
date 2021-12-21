<%@ page language="java" pageEncoding="UTF8"%>
<header class="nav" id="top_pos">
      <div class="container">
        <div class="row">
          <div class="col-md-12"><a class="nav_logo" href="<c:out value="${pageContext.request.contextPath}" />/fontend/"><img
         src="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo_lightup.svg"
          alt=""></a>
            <!-- mobile navbar-->
            <div class="nav_ham hamshow">
              <div id="nav-icon3"><span></span><span></span><span></span><span></span></div>
            </div>
            <!-- pc navbar-->
            <div class="nav_bar none" id="acolor">
              <ul>
              	<a href="<c:out value="${pageContext.request.contextPath}" />/fontend/plan">
                	<li>計畫介紹 <span>|</span></li>
                </a>
              	<a href="<c:out value="${pageContext.request.contextPath}" />/fontend/intInfo">
                	<li>國際資訊查詢 <span>|</span></li>
                </a>
              	<a href="<c:out value="${pageContext.request.contextPath}" />/fontend/intCoop">
                	<li>國際合作案例 <span>|</span></li>
                </a>
              	<a href="<c:out value="${pageContext.request.contextPath}" />/fontend/videoInfo">
                	<li>影音資訊<span>|</span></li>
                </a>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </header>

      <!-- scroll top--><a href="#top_pos" id="top_button"><i class="fas fa-angle-up"></i></a>
    <!-- mobile navbar content-->
    <div class="nav_barcontent" id="acolor"><a href="pages/plan-info.html">     
        <p>計畫介紹</p></a><a href="pages/int-info.html">         
        <p>國際資訊查詢</p></a><a href="pages/int-coop.html">        
        <p>國際合作案例</p></a><a href="pages/video-info.html">       
        <p>影音資訊</p></a><a href="pages/contact.html">        
        <p>聯絡我們</p></a></div>