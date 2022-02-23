<%@ page language="java" pageEncoding="UTF8"%>
<!-- partial:index.partial.html-->



       
 <div class="col-lg-3 none">
            <div class="sidebar">
            <c:if test="${a01Action}">
            
              <a class="button_line_gray"
              
               href="<c:out value="${pageContext.request.contextPath}" />/alt/a01"
               
               >       
               <span>補助追蹤 </span>
          
               </a>
              </c:if>
               
               
              <div class="mhr"></div>
              <div class="button">
                <div class="accordion" id="accordionPanelsStayOpenExample">
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">國際網絡</button>
                    </h2>
                    <div class="accordion-collapse collapse" id="panelsStayOpen-collapseOne" aria-labelledby="panelsStayOpen-headingOne">
                      <div class="accordion-body">
                                  
                    <c:if test="${c01Action}"> 
                    <a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c01">
                      <span>技術網絡查詢</span>
                      </a>
                     </c:if>
                      <c:if test="${c02Action}"> 
                    <a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c02">
                      <span>頂尖學者</span>
                      </a>
                      </c:if>
                      <c:if test="${c03Action}"> 
                    <a href="<c:out value="${pageContext.request.contextPath}" />/cyb/c03">
                      <span>頂尖機構</span>
                      </a>
                       </c:if>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="mhr"></div>
              <c:if test="${k01Action}"> 
              <a class="button_line_gray"
              href="<c:out value="${pageContext.request.contextPath}" />/mtp/m01">              
               <span>各國國際合作概況</span>
               </a>
               </c:if>
              <div class="mhr"></div>
                    
            
              
              <div class="mhr"></div>
              <div class="button">
                <div class="accordion-item">
                  <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">系統管理</button>
                  </h2>
                  <div class="accordion-collapse collapse" id="panelsStayOpen-collapseTwo" aria-labelledby="panelsStayOpen-headingTwo">
                    <div class="accordion-body">
                     <c:if test="${s12Action}">  
                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s12">
                    <span>角色權限資料維護</span></a>
                     </c:if>
                      <c:if test="${s05Action}">  
                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s05">
                    <span>人員基本資料管理</span></a>
                    </c:if>
                     <c:if test="${s02Action}">  
                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s02">
                    <span>角色資料維護</span></a>
                    </c:if>
                    
                   
                     <c:if test="${s13Action}">
                    <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s13">
                    <span>隱私權保護政策頁面設定 </span></a>
                    </c:if>
                    
                    <c:if test="${s15Action}">
                     <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s15">
                    <span>網站cookie設定管理 </span></a>
                    
                    </c:if>
                    
                    <c:if test="${s16Action}">
                    
                     <a href="<c:out value="${pageContext.request.contextPath}" />/sys/s16">
                    <span>版權宣告設定 </span></a>
                     </c:if>
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
                    
                                        <c:if test="${i01Action}">
                    
                     <a href="<c:out value="${pageContext.request.contextPath}" />/inc/i01"><span>國際合作個案管理</span></a>
                     
                                         </c:if>
                    <c:if test="${i02Action}">
                     
                     <a href="<c:out value="${pageContext.request.contextPath}" />/inc/i02"><span>影音資訊管理</span></a>
                      </c:if>
                     
                    </div>
                  </div>
                </div>
              </div>
              <div class="mhr"></div>
                                 <c:if test="${s01Action}">
              <a class="button_line_gray" 
              href="<c:out value="${pageContext.request.contextPath}" />/sys/s01">     
              <span>子系統資料維護</span>
              </a>
                               </c:if>
              
              <div class="mhr"></div>
                                              
               <c:if test="${s11Action}">
              <a class="button_line_gray" 
              href="<c:out value="${pageContext.request.contextPath}" />/sys/s11">
              <span>表單資料維護</span>
              </a>
                </c:if>
              
              <div class="mhr"></div>
              
               <c:if test="${s03Action}">
              <a class="button_line_gray" 
              href="<c:out value="${pageContext.request.contextPath}" />/sys/s03">           
               <span>操作記錄</span></a>
			</c:if>

            </div>
          </div>


    
   
