<%@ page language="java" pageEncoding="UTF8"%>



<!-- partial:index.partial.html-->
    <header class="nav" id="top_pos">
      <div class="container">
        <div class="row">
          <div class="col-xs-12"><a class="nav_logo" href="index.html"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo_lightup.svg" alt=""></a>
            <div class="nav_turnfront">
            <div class="button_fill_orange btn_s">
            <span href="#" onclick="logout();"> <s:message code="globalLogout" /></span>
            </div>
            
              <div class="button_fill_orange btn_s"><span>回到前台</span></div>
              <div class="user"><span>Admin000</span></div>
 
            </div>
          </div>
        </div>
      </div>
    </header>
    
     <!-- scroll top-->
    <div id="top_button"><i class="fas fa-angle-up"></i></div>
    
    <section id="statistics">
      <div class="container">
        <div class="row">
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>說明文字說明文字說明文字   </p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_subsidy.svg"></div><span>32122</span>
                <h5>補助總人數</h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>說明文字說明文字說明文字   </p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_research.svg"></div><span>967</span>
                <h5>研究成果總數</h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>說明文字說明文字說明文字   </p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_cooperate.svg"></div><span>263</span>
                <h5>國際合著篇數</h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>說明文字說明文字說明文字   </p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_keypoint.svg"></div><span>173</span>
                <h5>關鍵網絡總人數</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
