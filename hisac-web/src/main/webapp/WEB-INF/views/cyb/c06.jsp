<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c06.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
				<div>
					<div class="well well-sm">
						<h5 >
                            <b>請上傳檔案供系統掃描</b>
                        </h5>
                        <h6>
                            提供使用者判斷檔案存在的風險，以百分比表示，愈高代表風險愈大
                        </h6>
					</div>					
				</div>
			
		<div class="row">
		
		     <div class="form-group">
		     <label class="form_label form_label_search form_label_gray">上傳檔案</label>
		     
		
			<div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="file"
				ng-model="file" name="file"
				ngf-pattern=".zip" accept=".zip" ngf-max-size="100MB"
				
				
				 >			
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇檔案
				</button>
				<span>{{file.name}}</span>
			</div>						
			
			
			 </div>
			 
			  <h5 class = "text-danger text-center">
       
                    	檔案格式限制為zip格式，並且必須加密，檔案上限為10MB
                    </h5>
			
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">上傳檔案壓縮密碼</label>
                        <div class="form_input form_input_search">
                             <textarea ng-model="filePassword" 
                                class="form-control">
                            </textarea>
                        </div>
                    </div>
                    
                       <div class="form_group row tab_block text-center">
                    
                    <button class="btn btn-success  " ng-click="importFile()"
						ng-disabled = "file.name==null || filePassword==null || filePassword==''" >	
							上傳檔案
						</button>
						
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="clearData()">
							<i class="fas fa-fw fa-redo-alt"></i>
							<s:message code="btnReset" />
						</button>
			
			              </div>
			
		</div>

			<div class="col-xs-12 shadow_board">
				<form name="queryForm">													
				</form>
			</div>
			<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>

		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>											
							<th>建立時間</th>							
							<th>檔名</th>														
							<th>md5</th>
							<th>風險值</th>							
							<th>動作</th>
							<th>上傳帳號</th>
						</tr>
					</thead>
					<tbody>
								<tr ng-repeat="item in allitems">									
									<td>{{item.CreateTime}}</td>
									<td>{{item.FileName}}</td>																											
									<td>{{item.Md5}}</td>
									<td>{{item.Risk}}</td>
									<td><a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
							</td>
								<td>{{item.MemberAccoubt}}</td>
								</tr>								
					</tbody>
				</table>
			</div>
			<%@ include file="./../include/table_row_empty.jsp"%>
			<div class="text-center">
				<paging class="pagination" page="currentPage" page-size="maxRows"
					total="total" show-prev-next="true" show-first-last="true"
					text-next-class="fas fa-step-forward"
					text-prev-class="fas fa-step-backward"
					text-first-class="fas fa-fast-backward"
					text-last-class="fas fa-fast-forward"
					paging-action="queryData(page)"></paging>
			</div>
		</div>
	</div>
		</div>

		
	</div>	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
