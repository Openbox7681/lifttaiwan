<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
 <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/alt/a01.js"></script>
 
 
 
 <body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	
	<div class="container">
	
	<section id="main_content">
	<div class="container">
	<div class="row">
	
	<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
				
	<%@ include file="../include/slidebar.jsp"%>
	
	
	
	<div id="divQuery" class="col-lg-9 container">
				<div class="row">    
				      <div class="col-lg-12 none">
				      	<div id="filiter">		
				     			<div class="filiter_line_dropdown" id="subsidy_date">
				     			 <h6>查詢年份(民國)</h6>           
				      			    <div class="row g-0">
                                        <div class="col-sm-3">
                                            <div class="choose_item">
                                                <div class="input-group mb-2">
                                                    <input class="form-control" type="number" placeholder="- 日期 -"
                                                        aria-label="- 日期 -" aria-describedby="basic-addon2" ng-model = "StartYear"
                                                        oninput= "if(value.length>3)value=value.slice(0,3)"                                                       
                                                        >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <div class="dateline datemiddle"></div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="choose_item">
                                                <div class="input-group mb-2">
                                                    <input class="form-control" type="number" placeholder="- 日期 -"
                                                        aria-label="- 日期 -" aria-describedby="basic-addon2"  ng-model = "EndYear"
                                                        oninput= "if(value.length>3)value=value.slice(0,3)"
                                                        >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				      		<h6>按領域查詢</h6>
				      		
				      		<div class="main-block">
                                        <div class="main-subject">
                                            <div class="row g-0">
                                                <div class="col-lg-12">
                                                    <div class="main-detail" id="main-detail">
                                                        <div class="form-check">
                                                            <div class="all_area_checkbox">
                                                                <input class="form-check-input" id="flexCheckDefault"
                                                                    type="checkbox" value="" ng-click= "openSubSubjects(0)" ng-model = "infoSelectAll">
                                                                <label class="form-check-label"
                                                                    for="flexCheckDefault">所有領域 </label>
                                                            </div>
                                                        </div>
                                                        <span class="su_info" data-index="a" ng-click = "openSubSubjects(1)">資訊及數位相關產業</span>            
                                                        <span class="su_healthy" data-index="c" ng-click = "openSubSubjects(2)">臺灣精準健康戰略產業</span>
                                                        <span class="su_safe" data-index="d" ng-click = "openSubSubjects(3)">國防及戰略產業</span>
                                                        <span class="su_livelihood" data-index="f" ng-click = "openSubSubjects(4)">民生及戰備產業</span>
                                                        <span class="su_power" data-index="e" ng-click = "openSubSubjects(5)">綠電及再生能源產業</span>
                                                        <span class="su_other" data-index="g" ng-click = "openSubSubjects(6)">其他產業</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="line2"></div>
                                        <div class="sub-subject"></div>
                                        
                                        <div class = "form-check" ng-repeat = "item in InfoList" ng-show = "IsInfoShow" name="checkboxForm">
                                        	<input class = "form-check-input"
                                        	type = "checkbox" ng-model = "item.Flag" >
                                        	<label class = "form-check-label">
                                        		{{item.Name}}
                                        	</label>
                                        	
                                        </div>
                                            
                                    </div>
                                    
                                      <h6>按國家查詢</h6>
                                      
                            <div id="countysearch">
                            
                            	 <div class="accordion accordion-flush" id="accordionFlushExample">
                            	
                                         <div class="accordion-item">
                                                <h2 class="accordion-header" id="flush-headingOne">
                                                    <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
                                                        aria-expanded="false"
                                                        aria-controls="flush-collapseOne">全世界</button>
                                                </h2>
                                                <div class="accordion-collapse collapse" id="flush-collapseOne"
                                                    aria-labelledby="flush-headingOne"
                                                    data-bs-parent="#accordionFlushExample">
                                                    <div class="accordion-body">
                                                        <div class="form-check"  ng-repeat = "item in CountryList">
                                                            <input class="form-check-input" id="country" type="checkbox"
                                                            ng-model = "item.Flag"
                                                                value="">
                                                            <label class="form-check-label" for="country">
                                                            
                                                            {{item.Name}}
                                                            
                                                            </label>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>                     
                                                              
                                      </div>
                            
                                   </div>
                                    
                                    
                                  
                                    
                                  <div class="subsidy check_outer">
                                        <p>統計表名稱</p>
                                        <div class="form-check form-check-inline" id="calldata1" data-index="0">
                                            <input class="form-check-input" id="inlineRadio1" type="radio"
                                                name="inlineRadioOptions" 
                                                ng-value = 1
                                                ng-model = "inlineRadioOptions"
                                                >
                                            <label class="form-check-label" for="inlineRadio1">補助資料分析</label>
                                        </div>
                                        <div class="form-check form-check-inline" id="calldata2" data-index="1">
                                            <input class="form-check-input" id="inlineRadio2" type="radio"
                                                name="inlineRadioOptions" 
                                                ng-value = 2
                                                ng-model = "inlineRadioOptions"
                                                
                                                >
                                            <label class="form-check-label" for="inlineRadio2">成果分析(論文/GRB等)</label>
                                        </div>
                                        <div class="form-check form-check-inline" id="calldata3" data-index="2">
                                            <input class="form-check-input" id="inlineRadio3" type="radio"
                                                name="inlineRadioOptions" 
                                                ng-value = 3
                                                ng-model = "inlineRadioOptions"
                                                
                                                >
                                            <label class="form-check-label" for="inlineRadio3">國際網絡表現</label>
                                        </div>
                                    </div>
                                    
                             <div class="search_btn hright">
                                        <div class="button_fill_orange btn_m">
                                            <p ng-click = 'query()' ng-disabled = "value.length ==0"
                                            >送出查詢</p>
                                        </div>
                                    </div>
				      		
				      		
				      		
				      		</div>
				      
				      	</div>
				      	 </div>
				
				        <div class="col-lg-12 adjust_pos">
				              <div class="row">
				                 <div class="col-sm-12">
                                    <div class="index_title title_style">
                                        <h5>查詢結果</h5>
                                    </div>
                                </div>
                                
                                 <div class="col-sm-12 exportbtn" id="adjust_subsidybtn" ng-show = "isSupport" >
                                 	<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="pills-home-tab"
                                                    data-bs-toggle="pill" data-bs-target="#pills-home" type="button"
                                                    role="tab" aria-controls="pills-home"
                                                    aria-selected="true"
                                                    ng-click = "supportSwitch(1)"
                                                    >
                                                    補助人數(依領域)
                                                    
                                                    </button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill"
                                                    data-bs-target="#pills-profile" type="button" role="tab"
                                                    aria-controls="pills-profile"
                                                    aria-selected="false"
                                                ng-click = "supportSwitch(2)"
                                                    
                                                    >補助人數(依領域及國家)</button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link" id="pills-top-tab" data-bs-toggle="pill"
                                                    data-bs-target="#pills-top" type="button" role="tab"
                                                    aria-controls="pills-top"
                                                    aria-selected="false"
                                                  ng-click = "supportSwitch(3)"
                                                    
                                                    
                                                    >國內機構分析(依in+out分開呈現)Top10</button>
                                            </li>
                                        </ul>
                                        
                                  <div class="col-sm-12 exportbtn"  ng-show= "isSupport1" >
                                 	補助人數(依領域)
                                 	
                                 	<table class="table table-striped">
                                 		<thead>
                            
                                        <tr>
                                            <th>主領域</th>
                                            <th>次領域</th>
                                            
                                            <th>盤古開天</th>
                                            <th>國合PI</th>
                                            <th>龍門計畫主持人</th>
                                            <th>千里馬申請人</th>
                                            <th>龍門計畫主持人</th>
                                            <th>政策邀訪學者</th>
                                            <th>合計</th>
                                        </tr>
                                        
                                        </thead>
                                        
                                       <tbody>
                                        
                                        
                                        <tr ng-repeat="item in formData">
                                            <td>{{item.classMain}}</td>
                                            <td>{{item.classSub}}</td>
                                            <td>{{item.open}}</td>
                                            <td>{{item.pi}}</td>
                                            <td>{{item.dragon}}</td>
                                            <td>{{item.short}}</td>
                                            <td>{{item.horse}}</td>
                                            <td>{{item.policy}}</td>
                                            <td>{{item.total}}</td>

                                        </tr>
                                        
                                       
                                        
                                       
                                       </tbody>
                                        
                                    </table>
                                    
                                  
                                 	
                                 </div>
                                 
                                 
                                 
                                 
                                 
                                 <div class="col-sm-12 exportbtn" ng-show = "isSupport2" >
                                    補助人數(依領域及國家)
                                    <table class="table table-striped">
                                         <thead>
                                        <tr>
                                            <th>國家別</th>
                                            <th>盤古開天</th>
                                            <th>國合PI</th>
                                            <th>龍門計畫主持人</th>
                                            <th>千里馬申請人</th>
                                            <th>龍門計畫主持人</th>
                                            <th>政策邀訪學者</th>
                                            <th>合計</th>
                                        </tr>
                                          </thead>
                                         <tbody>
                                        
                                        <tr ng-repeat="item in CountryData">
                                            <td>{{item.name}}</td>
                                            <td>{{item.open}}</td>
                                            <td>{{item.pi}}</td>
                                            <td>{{item.dragon}}</td>
                                            <td>{{item.short}}</td>
                                            <td>{{item.horse}}</td>
                                            <td>{{item.policy}}</td>
                                            <td>{{item.total}}</td>
                                        </tr>
                                          </tbody>
                                        
                                        
                                      
                                    </table>
                                    
                                    
                                </div>
                                
                                 <div class="col-sm-12"  >
                                    <div class="col-sm-12"
                                       id="a1" style="height: 600px; width: 100%">
                                     </div>
                                     
                                    
                                    
                                    <!--補助資料分析 A-1補助人數(依領域) 堆積柱狀圖-->
                                </div>
                                 
                                
                              <!--   
                                <div class="col-sm-12 exportbtn" ng-show = "isSupport3">
                                    <div id="bars" style="height: 300px; width: 600px"></div>
                                </div> -->
                                 
                                      
                                  
                                
                                 </div>
                                 
								<div class="col-sm-12 exportbtn" id="adjust_subsidybtn" ng-show = "isResult">
									<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				                        <li class="nav-item" role="presentation">
				                          <button class="nav-link active" id="pills-results1-tab" data-bs-toggle="pill" data-bs-target="#pills-results1"
				                           type="button" role="tab" aria-controls="pills-results1" aria-selected="true"
				                           >
				                           歷年各項成果追蹤</button>
				                        </li>
				                        <li class="nav-item" role="presentation">
				                          <button class="nav-link" id="pills-results2-tab" data-bs-toggle="pill" data-bs-target="#pills-results2"
				                           type="button" role="tab" aria-controls="pills-results2" aria-selected="false"
				                           >
				                           各領域之研究能量分析(論文數TOP20)</button>
				                        </li>
				                      </ul>
                                </div>
                                
							<div class="col-sm-12 exportbtn" ng-show = "isResult1">
								成果分析<br />
                                    B-1歷年各項成果追蹤
							
							     <table class="table table-striped">
							     <tr>
                                            <th>項目</th>
                                           <th>盤古開天</th>
                                            <th>國合PI</th>
                                            <th>龍門計畫主持人</th>
                                            <th>千里馬申請人</th>
                                            <th>龍門計畫主持人</th>
                                            <th>政策邀訪學者</th>
                                            <th>合計</th>
                                        </tr>
                                        
                                       <tr ng-repeat="item in B1Data">
                                            <td>{{item.name}}</td>
                                            <td>{{item.open}}</td>
                                            <td>{{item.pi}}</td>
                                            <td>{{item.dragon}}</td>
                                            <td>{{item.short}}</td>
                                            <td>{{item.horse}}</td>
                                            <td>{{item.policy}}</td>
                                            <td>{{item.total}}</td>
                                        </tr>
							     
							     
								</table>
							
                                
                                </div>
                                 
                                
                                
                                
                                 
                                
                                
                                
				                  
				                  </div>
				        
				        	
				        
				        </div>
				
				
				</div>

	
		
	
	
	
	
		
	</div>
	
	
	</div>
	</div>
	</section>
		
	</div>
	
	
	
	
	<!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	
		<div class="footer_space"></div>
	
	<%@ include file="../include/footer.jsp"%>
</body>
</html>