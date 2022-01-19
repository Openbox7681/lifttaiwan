 <%@ page language="java" pageEncoding="UTF8"%>
 
 
 <!-- tablet&mobile sidebar lightbox-->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <section id="main_content">
              <div class="container">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="sidebar">
                      <div class="row">
                        <div class="col-md-6"><a class="button_line_gray" 
                         href="<c:out value="${pageContext.request.contextPath}" />/alt/a01">   
                        <span>補助追蹤 </span></a>
                          <div class="mhr"></div>
                          <div class="button">
                            <div class="accordion" id="accordionPanelsStayOpenExample">
                              <div class="accordion-item">
                                <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">國際網絡</button>
                                </h2>
                                <div class="accordion-collapse collapse" id="panelsStayOpen-collapseOne" aria-labelledby="panelsStayOpen-headingOne">
                                  <div class="accordion-body">
								<a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c01">     
								<span>技術網絡查詢</span></a>
                                  <a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c02">
                                  <span>頂尖學者</span></a>
                                  <a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c03">
                                  <span>頂尖機構</span></a></div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="mhr"></div><a class="button_line_gray" 
             				 href="<c:out value="${pageContext.request.contextPath}" />/kin/k01">              
                          <span>各國國際合作概況</span></a>
                          <div class="mhr"></div>
                        </div>
                        <div class="col-md-6"><a class="button_line_gray" 
              			href="<c:out value="${pageContext.request.contextPath}" />/cyb/c04">              
                        <span>人才活躍度</span></a>
                          <div class="mhr"></div>
                          <div class="button">
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">系統管理</button>
                              </h2>
                              <div class="accordion-collapse collapse" id="panelsStayOpen-collapseTwo" aria-labelledby="panelsStayOpen-headingTwo">
                                <div class="accordion-body">
                                
                                
                               		<a href="<c:out value="${pageContext.request.contextPath}" />/sys/s12">
				                    <span>角色權限</span></a>
				                    
				                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s05">
				                    <span>人員基本資料資料管理</span></a>
				                    
				                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s02">
				                    <span>角色資料維護</span></a>
				                    
				                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s02">
				                  
				                    <span>圖片/連結檢測</span></a>
				                    
				                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s13">
				                    <span>隱私權保護政策頁面設定 </span></a>
				                    
				                     <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s13">
				                    <span>網站cookie設定管理 </span></a>
				                    
				                     <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s13">
				                    <span>個人資源收集告知聲明設定 </span></a>
				                    
				                    
				                    </div>
                                
                                
                                
                              </div>
                            </div>
                          </div>
                          <div class="mhr"></div>
                          <div class="button">
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">公開資訊管理</button>
                              </h2>
                              <div class="accordion-collapse collapse" id="panelsStayOpen-collapseThree" aria-labelledby="panelsStayOpen-headingThree">
                                <div class="accordion-body">
                                <a href="<c:out value="${pageContext.request.contextPath}" />/inc/i01">
                                <span>國際合作個案管理</span></a>
                                <a href="<c:out value="${pageContext.request.contextPath}" />/inc/i02">
                                <span>影音資訊管理</span></a></div>
                              </div>
                            </div>
                          </div>
                          <div class="mhr"></div><a class="button_line_gray" 
              				href="<c:out value="${pageContext.request.contextPath}" />/sys/s01">     
                          <span>子系統資料維護</span></a>
                          <div class="mhr"></div><a class="button_line_gray" 
              				href="<c:out value="${pageContext.request.contextPath}" />/sys/s11">
                          <span>表單資料維護</span></a>
                          <div class="mhr"></div><a class="button_line_gray" 
              				href="<c:out value="${pageContext.request.contextPath}" />/sys/s03">           
                          <span>操作記錄</span></a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>