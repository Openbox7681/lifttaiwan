<%@ page language="java" pageEncoding="UTF8"%>



<!-- partial:index.partial.html-->
    <header class="nav" id="top_pos">
      <div class="container">
        <div class="row">
          <div class="col-xs-12"><a class="nav_logo" 
               href="<c:out value="${pageContext.request.contextPath}" />/pub/index"
          >
          
          <img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/logo_lightup.svg" alt=""></a>
            <div class="nav_turnfront">
            <div class="button_fill_orange btn_s">
            <span href="#" onclick="logout();"> <s:message code="globalLogout" /></span>
            </div>
            
              <div class="button_fill_orange btn_s"  ><a href="../fontend" style="color:white;"><span>回到前台</span></a></div>
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
                <p>統計92~110年核定通過人次</p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_subsidy.svg">
				</div><span id="peopleNum"></span>
                <h5>
                 補助總人數
                </h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>統計92~110年受補助人WOS論文發表篇數</p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_research.svg"></div><span id="paperNum"></span>
                <h5>
                研究成果總數
                </h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>統計92~110年受補助人曾與國際學者共同發表之WOS論文篇數</p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_cooperate.svg"></div><span id="paperCorNum"></span>
                <h5>
                國際合著篇數
                </h5>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="statistics_type">
              <div class="popinfo">
                <p>計算92~110年受補助人(含關聯人才)可鏈結之Top20頂尖學者人數</p>
              </div>
              <div class="statistics_line">
                <div class="icon"><img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_keypoint.svg"></div><span id="snaTopNum"></span>
                <h5>
                關鍵網絡總人數
                </h5>
              </div>
            </div>
          </div>
          
           <div id="loadingActivity" class=" col-md-12 col-sm-12 public_loading">
					<h4>
						<i class="fas fa-circle-notch fa-spin"></i>
						<s:message code="dataLoading" />
					</h4>
		</div>
		
        </div>
        
        
        
        
      </div>
    </section>
