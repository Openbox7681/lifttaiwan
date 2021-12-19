<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/new.css">
<body class="no-skin" ng-controller="getAppController" id="stop">
	<header class="nav" id="top_pos">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
          	<a class="nav_logo" href="../fontend/index">
          	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo_lightup.svg" alt="">
          	</a>
            <!-- mobile navbar-->
            <div class="nav_ham hamshow">
              <div id="nav-icon3"><span></span><span></span><span></span><span></span></div>
            </div>
            <!-- pc navbar-->
            <div class="nav_bar none" id="acolor">
              <ul><a href="plan-info.html">
                  <li>計畫介紹 <span>|</span></li></a><a href="int-info.html">                 
                  <li>國際資訊查詢 <span>|</span></li></a><a href="int-coop.html">                 
                  <li>國際合作案例 <span>|</span></li></a><a href="video-info.html">                 
                  <li>影音資訊<span>|</span></li></a></ul>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- scroll top--><a href="#top_pos" id="top_button"><i class="fas fa-angle-up"></i></a>
    <!-- mobile navbar content-->
    <div class="nav_barcontent" id="acolor"><a href="plan-info.html">     
        <p>計畫介紹</p></a><a href="int-info.html">         
        <p>國際資訊查詢</p></a><a href="int-coop.html">        
        <p>國際合作案例</p></a><a href="video-info.html">       
        <p>影音資訊</p></a>
    </div>
    
    <section id="banner_content">
      <div class="banner">
        <h2>影音資訊</h2>
      </div>
    </section>
    <section id="videoinfo_content">
      <div class="container">
        <div class="col-lg-12">
          <div class="outer">
            <div class="row">
              <div class="col-md-3 col-sm-12">
                <div class="video_outer"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/video2.jpg" alt="">
                <a class="player" href="https://www.youtube.com/embed/oj_pWOCisJ4"></a></div>
              </div>
              <div class="col-md-7 col-sm-12 wordad">
                <h5>疫疫情當頭，餐飲科技新創，如何以「創新」盡一己之力？</h5>
                <p>flyingVest Ventures 曙光網路加速器首次舉辦線上Demo day，由旗下新創夥伴 — 錦霖生醫、鋭準生醫、潔客幫、捷思科技，發表品牌年度成果及未來展望；更邀請到宏誠創投與豐利創投的兩位貴賓蒞臨點評指教。</p>
                <p>你可以在這場活動中，認識新創企業，看見他們正在做什麼，如何寫下創業里程碑；也能帶給你商業合作資源與靈感，挖掘投資機會、延展更多事業資源、拓展商業視野。</p>
                <p>歡迎創投單位、天使投資人、企業投資單位、創業家、各大新創夥伴、對創新與創業議題有興趣的大眾共同參與。</p>
                <div class="keybtn"><span>#Accelerator</span></div>
                <div class="keybtn"><span>#Future                  </span></div>
              </div>
              <div class="col-md-2 wordad">
                <div class="releasedate"><span>2021-09-24</span></div>
                <div class="name"><span>Kay Lee</span></div>
                <div class="clickcount"><span>824,251</span></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="outer">
            <div class="row">
              <div class="col-md-3 col-sm-12">
                <div class="video_outer"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/video2.jpg" alt="">
                <a class="player" href="https://www.youtube.com/embed/oj_pWOCisJ4"></a></div>
              </div>
              <div class="col-md-7 col-sm-12 wordad">
                <h5>疫疫情當頭，餐飲科技新創，如何以「創新」盡一己之力？</h5>
                <p>flyingVest Ventures 曙光網路加速器首次舉辦線上Demo day，由旗下新創夥伴 — 錦霖生醫、鋭準生醫、潔客幫、捷思科技，發表品牌年度成果及未來展望；更邀請到宏誠創投與豐利創投的兩位貴賓蒞臨點評指教。</p>
                <p>你可以在這場活動中，認識新創企業，看見他們正在做什麼，如何寫下創業里程碑；也能帶給你商業合作資源與靈感，挖掘投資機會、延展更多事業資源、拓展商業視野。</p>
                <p>歡迎創投單位、天使投資人、企業投資單位、創業家、各大新創夥伴、對創新與創業議題有興趣的大眾共同參與。</p>
                <div class="keybtn"><span>#Accelerator</span></div>
                <div class="keybtn"><span>#Future                  </span></div>
              </div>
              <div class="col-md-2 wordad">
                <div class="releasedate"><span>2021-09-24</span></div>
                <div class="name"><span>Kay Lee</span></div>
                <div class="clickcount"><span>824,251</span></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="outer">
            <div class="row">
              <div class="col-md-3 col-sm-12">
                <div class="video_outer"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/video2.jpg" alt="">
                <a class="player" href="https://www.youtube.com/embed/oj_pWOCisJ4"></a></div>
              </div>
              <div class="col-md-7 col-sm-12 wordad">
                <h5>疫疫情當頭，餐飲科技新創，如何以「創新」盡一己之力？</h5>
                <p>flyingVest Ventures 曙光網路加速器首次舉辦線上Demo day，由旗下新創夥伴 — 錦霖生醫、鋭準生醫、潔客幫、捷思科技，發表品牌年度成果及未來展望；更邀請到宏誠創投與豐利創投的兩位貴賓蒞臨點評指教。</p>
                <p>你可以在這場活動中，認識新創企業，看見他們正在做什麼，如何寫下創業里程碑；也能帶給你商業合作資源與靈感，挖掘投資機會、延展更多事業資源、拓展商業視野。</p>
                <p>歡迎創投單位、天使投資人、企業投資單位、創業家、各大新創夥伴、對創新與創業議題有興趣的大眾共同參與。</p>
                <div class="keybtn"><span>#Accelerator</span></div>
                <div class="keybtn"><span>#Future                  </span></div>
              </div>
              <div class="col-md-2 wordad">
                <div class="releasedate"><span>2021-09-24</span></div>
                <div class="name"><span>Kay Lee</span></div>
                <div class="clickcount"><span>824,251</span></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-12 hcenter">
          <div class="linkouter"><a>
              <div class="start"></div></a><a>
              <div class="prev"></div></a>
            <div class="pagecount"><a class="link-secondary" href="#">1</a>
              <div class="hline">        </div><a class="link-secondary" href="#">2</a>
              <div class="hline"></div><a class="link-secondary" href="#">3</a>
            </div><a>
              <div class="next"></div></a><a>
              <div class="end">                                                                                                                 </div></a>
          </div>
        </div>
      </div>
    </section>
    
    <div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>

	
</body>
</html>