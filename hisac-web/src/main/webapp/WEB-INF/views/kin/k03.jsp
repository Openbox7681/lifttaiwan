<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k03.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="notifyStatus" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
										<option value="4" selected>4-進行稽核前訪談調查</option>
										<option value="5" selected>5-已繳交稽核前訪談調查文件</option>
										<option value="6" selected>6-補件/補述</option>										
										<option value="8" selected>8-查閱稽核報告並繳交改善報告</option>
										<option value="9" selected>9-已繳交改善報告</option>																									
								</select>
							</div>
						</div>
					</div>					
					<div>
						<div class="form_group">
							<label for="QueryName"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageKeyword" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyword" name="QueryKeyword"
									ng-model="QueryKeyword" class="form-control"
									placeholder="法遵稽核計畫資料"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="clearData()">
							<i class="fas fa-fw fa-redo-alt"></i>
							<s:message code="btnReset" />
						</button>
					</div>
			</div>
			<div>
				<div class="form-group hidden-xs">
					<div class="clearfix form-actions">
						<div class="text-center">									
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('4');">
									進行稽核前訪談調查
									{{Status4Count}}
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('5');">
									已繳交稽核前訪談調查文件
								</button>						
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('8');">
									查閱稽核報告並繳交改善報告
									{{Status8Count}}
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('9');">
									已繳交改善報告
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('6');">
									補件/補述
								</button>
						</div>
					</div>
				</div>
			</div>
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
			<div class="col-xs-12 col-md-6 no_padding">
				<c:if test="${isAdmin}">
					<a class="btn btn_custom btn_blue pull-right" type="button"
						ng-click="create();"> <i class="fas fa-fw fa-plus-circle"></i>
						<s:message code="btnCreate" />
					</a>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('title')"> 法遵稽核計畫資料 <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('year')">年度 <i
									ng-show="sorttype != 'year'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'year' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'year' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>																		
							<th><a href="" ng-click="setSortName('status')"><s:message
										code="messageStatus" /><i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>		
							
							<c:if test="${  isAdmin}">
							<th><a href="" ng-click="setSortName('createTime')"><s:message
										code="globalCreateTime" /><i ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							</c:if>
							
										
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
						
							<td>							
								<a href="" ng-click="review(item.Id, ${orgId}, item.Status);"
								role="button" data-dismiss="modal"> <i
										class="fas fa-file-alt fa-lg fa-pull-left"></i>{{item.Title}}</a>															
							</td>	
														
							<td>{{item.Year}}</td>
																		
							<td>
							<span ng-if="item.Status==4">進行稽核前訪談調查</span>
							<span ng-if="item.Status==5">已繳交稽核前訪談調查文件</span>			
							<span ng-if="item.Status==8">查閱稽核報告並繳交改善報告</span>
							<span ng-if="item.Status==9">已繳交改善報告</span>											
							</td>									
							
							<c:if test="${ isMemberContact || isMemberAdmin}">
							<td class="text-center">
								<a class="btn btn-sm btn-primary"
								ng-click="editData(item.Id,${orgId});" title='稽核前訪談調查文件'
								ng-show = "item.HospitalUpload"
								ng-if="item.Status==4">
									稽核前訪談調查文件 </a>
								<a class="btn btn-sm btn-primary"
								ng-click="additionalDocuments(item.Id,${orgId},item.AllowHospitalPatch)" title='稽核前訪談調查文件'
								ng-show="item.CommitteeUpload"
								ng-if="item.AllowHospitalPatch">
									補件/補述 </a>
								<a class="btn btn-sm btn-primary"
								ng-click="improveReport(item.Id, ${orgId});" title='改善報告'
								ng-if="item.Status==8">
									改善報告</a>
							</td>							
							</c:if>														
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
	<!-- divEdit開始 -->
	<div id="divEdit" class="container">
		<div>
			<div class="form_group">
            	<h4 class="form_heading form_heading form_heading_yellow">
                 	<i class="fa fa-info-circle"><b>稽核計畫資料</b></i>
                </h4>
            </div>
            <div>
            	<div class="form_group">
            		<label class="form_label form_label_search form_label_gray">計畫名稱</label>
            		<span>{{Title}}</span>
            	</div>
            </div>
            <div>
           		<div class="form_group">
                	<label class="form_label form_label_search form_label_gray">計畫年度</label>
            		<span>{{Year}}</span>
            	</div>
  			</div>
		</div>
		
		<!-- 範例檔案下載開始 -->
		<div>	  
       		<div class="form_group">
            	<h4 class="form_heading form_heading form_heading_yellow">
                	<i class="fa fa-info-circle"><b>稽核前訪談調查格式檔案下載</b></i>
                </h4>
            </div>
            
            <div>
               	<div class="form_group">
                	<label class="form_label form_label_search form_label_gray">資通安全實地稽核項目檢核表格式</label>
                    <div class="form_input form_input_search">
                    	<a href="./api/k03/download/CheckList">資通安全實地稽核項目檢核表格式.docx</a>
                   	</div>
               	</div>
       		</div>
      		<div>
            	<div class="form_group">
                	<label class="form_label form_label_search form_label_gray">受稽機關現況調查表格式</label>
                    <div class="form_input form_input_search">
                         <a href="./api/k03/download/Questionnaire">受稽機關現況調查表格式.docx</a>
                    </div>
                </div>
          	</div>
      		<div>
            	<div class="form_group">
                	<label class="form_label form_label_search form_label_gray">技術檢測自評表表格式</label>
                    <div class="form_input form_input_search">
                         <a href="./api/k03/download/SelfEvaluation">技術檢測自評表表格式.docx</a>
                    </div>
                </div>
          	</div>
     	</div>
     	<!-- 範例檔案下載結束 -->
     	
     	<!-- 資通安全實地稽核項目檢核表上傳開始 -->
     	<div>
	     	<div>
	           	<div class="form_group">
					<h4 class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>資通安全實地稽核項目檢核表</b></i>
					</h4>
				</div>
	        </div>
	        
	         <div ng-if="FileCheckListName!=null">
		         <div>
		           <div>
		                 <label class="form_label form_label_search form_label_gray">資通安全實地稽核項目檢核表</label>
		                 <div class="form_input form_input_search">
		                  	<a href="./api/k03/attach/download/checkList/{{MaintainInspectId}}/{{OrgId}}">{{FileCheckListName}}</a>
		                 </div>
		        	</div>
		        </div>
	        </div>
	        
	        <div class="form_group"></div>
	        
			<div ng-show="IsQuestionnaire">
				<div>
		        	<div class="form-group">
		            	<label for="fileCheckList" class="form_label form_label_search form_label_gray">(必填)資通安全實地稽核項目檢核表上傳</label>
		              	<div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileCheckList"
		                	ng-model="fileCheckList" name="fileCheckList" ngf-pattern=".doc,.docx" accept=".doc,.docx"
		                    ngf-max-size="100MB" ngf-min-height="100">
		        		<button id="SelectAttachment" class="btn btn-primary" ng-click="changefileCheckList()">
		         			<i class="fas fa-fw fa-file fa-lg"></i>
		                  	<s:message code="pleaseSelect" />
		                    <s:message code="messageAttachment" />
		              	</button>
		                	<span>{{fileCheckList.name}}</span>
		                   	<button class="btn btn-danger" ng-click="deletefileCheckList()" ng-show="fileCheckList.name!=null">
		                   		<i class="far fa-fw fa-trash-alt"></i>
		                     	<s:message code="btnDelete" />
		                   	</button>
		        		</div>
	      			</div>
	      		</div>
	      	</div>
      	</div>
     	<!-- 資通安全實地稽核項目檢核表上傳結束 -->
     	<!-- 受稽機關現況調查表上傳開始 -->
  		<div>
        	<div class="form_group">
				<h4 class="form_heading form_heading form_heading_yellow">
                	<i class="fa fa-info-circle"><b>受稽機關現況調查表</b></i>
				</h4>
            </div>
        </div>
        <div ng-if="FileQuestionnaireName!=null">
            <div class="form_group">
                <label class="form_label form_label_search form_label_gray">受稽機關現況調查表</label>
                <div class="form_input form_input_search">
               		<a href="./api/k03/attach/download/questionnaire/{{MaintainInspectId}}/{{OrgId}}">{{FileQuestionnaireName}}</a>
            	</div>
            </div>
        </div>
        <div class="form_group"></div>
        <div>
            <div ng-show="IsQuestionnaire">
				<div class="form-group">
					<label for="fileQuestionnaire" class="form_label form_label_search form_label_gray">(必填)受稽機關現況調查表上傳</label>
					<div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileQuestionnaire"
						ng-model="fileQuestionnaire" name="fileQuestionnaire" ngf-pattern=".doc,.docx,.pdf" accept=".doc,.docx"
						ngf-max-size="20MB" ngf-min-height="100">
                       	<button id="SelectAttachment" class="btn btn-primary" ng-click="changefileQuestionnaire()">
                            <i class="fas fa-fw fa-file fa-lg"></i>
                            <s:message code="pleaseSelect" />
                        	<s:message code="messageAttachment" />
                        </button>
                        <span>{{fileQuestionnaire.name}}</span>
						<button class="btn btn-danger" ng-click="deletefileQuestionnaire()" ng-show="fileQuestionnaire.name!=null">
                        	<i class="far fa-fw fa-trash-alt"></i>
                           	<s:message code="btnDelete" />
                       	</button>
					</div>
				</div>
                <div class="form_group">
					<h5 class = "text-danger">檔案格式限制為pdf與doc格式，檔案上限為20MB</h5>   
            	</div>
         	</div>
        </div>
     	<!-- 受稽機關現況調查表上傳結束 -->
     	<!-- 技術檢測自評表上傳開始 -->
     	<div>
           	<div class="form_group">
				<h4 class="form_heading form_heading form_heading_yellow">
					<i class="fa fa-info-circle"><b>技術檢測自評表</b></i>
				</h4>
			</div>
        </div>
        <div ng-if="FileQuestionnaireName!=null">
            <div class="form_group">
                <label class="form_label form_label_search form_label_gray">受稽機關現況自評表</label>
                <div class="form_input form_input_search">
               		<a href="./api/k03/attach/download/selfEvaluation/{{MaintainInspectId}}/{{OrgId}}">{{FileSelfEvaluationName}}</a>
            	</div>
            </div>
        </div>
        <div class="form_group"></div>
		<div ng-show="IsQuestionnaire">
        	<div class="form-group">
            	<label for="fileSelfEvaluation" class="form_label form_label_search form_label_gray">技術檢測自評表上傳</label>
              	<div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileSelfEvaluation"
                	ng-model="fileSelfEvaluation" name="fileSelfEvaluation" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                    ngf-max-size="100MB" ngf-min-height="100">
        			<button id="SelectAttachment" class="btn btn-primary" ng-click="changefileSelfEvaluation()">
         				<i class="fas fa-fw fa-file fa-lg"></i>
                  		<s:message code="pleaseSelect" />
                    	<s:message code="messageAttachment" />
              		</button>
                	<span>{{fileSelfEvaluation.name}}</span>
                   	<button class="btn btn-danger" ng-click="deletefileSelfEvaluation()" ng-show="fileSelfEvaluation.name!=null">
                   		<i class="far fa-fw fa-trash-alt"></i>
                     	<s:message code="btnDelete" />
                   	</button>
        		</div>
      		</div>
      	</div>
     	<!-- 技術檢測自評表上傳結束 -->
     	<!-- 其他上傳開始 -->
		<div>
           	<div class="form_group">
				<h4 class="form_heading form_heading form_heading_yellow">
					<i class="fa fa-info-circle"><b>其他檔案上傳</b></i>
				</h4>
			</div>
        </div>
		<div ng-show="IsQuestionnaire || AllowHospitalPatch">
        	<div class="form-group">
            	<label for="fileOther" class="form_label form_label_search form_label_gray">其他檔案上傳</label>
              	<div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileOther"
                	ng-model="fileOther" name="fileOther" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                    ngf-max-size="100MB" ngf-min-height="100">
        			<button id="SelectAttachment" class="btn btn-primary" ng-click="changefileOther()">
         				<i class="fas fa-fw fa-file fa-lg"></i>
                  		<s:message code="pleaseSelect" />
                    	<s:message code="messageAttachment" />
              		</button>
                	<span>{{fileOther.name}}</span>
                   	<button class="btn btn-danger" ng-click="deletefileOther()" ng-show="fileOther.name!=null">
                   		<i class="far fa-fw fa-trash-alt"></i>
                     	<s:message code="btnDelete" />
                   	</button>
                   	<button class="btn btn_custom btn_blue" type="button"
						ng-disabled="!FileDescription" ng-click="tempFile(fileOther,FileDescription)">確認</button>
					<span style="color:red">請務必按下確認以便完成檔案選取</span>
        		</div>
      		</div>
			<div class="form_group" ng-show="!btnCheck">
				<label for="FileDescription"
					class="form_label form_label_search form_label_gray">檔案敘述</label>
				<div class="form_input form_input_search">
					<textarea type="text" id="FileDescription"
						name="FileDescription" ng-model="FileDescription"
						class="form-control"
						placeholder="檔案敘述"
						rows="5" autocomplete="off"></textarea>
				</div>
			</div>
			<div class="submit_bar">
				<span>&nbsp;</span>
<!--			
				<button class="btn btn_custom btn_blue" type="button"
					ng-disabled="!FileDescription" ng-click="tempFile(fileOther,FileDescription)">確認</button>
-->
			</div>
      	</div>
      	<div class="row">
			<div class="table-responsive">
				<table	class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>

							<th>檔案名稱</th>

							<th>說明</th>

							<th>&nbsp;</th>

						</tr>
					</thead>
					<tbody>
						<!-- 已存在資料庫內 -->
						<tr ng-repeat="item in allAttach">
							<td><a href="./api/k03/attach/download/other/{{item.FileOtherId}}">{{item.FileOtherName}}</a></td>
							<td>{{item.FileOtherDesc}}</td>
							<td><div class="submit_bar"><a href="#" class="btn btn-sm btn-info"
							ng-click="deleteOtherFile(item.FileOtherId,$index);" ng-show="IsQuestionnaire"><i
							class="far fa-fw fa-trash-alt"></i> <s:message
							code="btnDelete" /></a></div></td>
						</tr>
						<!-- 不存在資料庫等待上傳 -->
						<tr ng-repeat="item in fileOtherList">
							<td>{{item.file.name}}</td>
							<td>{{item.fileDescription}}</td>
							<td><div class="submit_bar"><a href="#" class="btn btn-sm btn-info"
							ng-click="removeFile($index);"><i
							class="far fa-fw fa-trash-alt"></i> <s:message
							code="btnDelete" /></a></div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
     	<!-- 其他上傳結束 -->
     	<!-- 稽核結果開始-->
     	<div class="form_group" ng-show="IsAuditResult">
			<h4 class="form_heading form_heading form_heading_yellow">
				<i class="fa fa-info-circle"><b>稽核報告</b></i>
			</h4>
		</div>
     	<div ng-if="FileAuditResultName!=null && IsAuditResult">
           	<div class="form_group">
            	<label class="form_label form_label_search form_label_gray">稽核報告</label>
               	<div class="form_input form_input_search">
                	<a href="./api/k03/attach/download/auditResult/{{MaintainInspectId}}/{{OrgId}}">{{FileAuditResultName}}</a>
               	</div>
            </div>
        </div>
     	<!-- 稽核結果結束 -->
     	<!-- 改善報告開始 -->
           	<div class="form_group" ng-show="IsImproveReport || ShowImproveReport">
				<h4 class="form_heading form_heading form_heading_yellow">
					<i class="fa fa-info-circle"><b>改善報告</b></i>
				</h4>
			</div>
			  <div>
                    <div class="form_group" ng-show="IsImproveReport">
                        <label class="form_label form_label_search form_label_gray">改善報告摘要</label>
                        <div class="form_input form_input_search">
                             <textarea ng-disabled="!IsImproveReport" ng-model="improvementAbstract"
                                class="form-control">
                            </textarea>
                        </div>
                    </div>
                </div>


				<div ng-if="FileImprovementName!=null && ShowImproveReport">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">改善報告</label>
                        <div class="form_input form_input_search">
                            <a href="./api/k03/attach/download/improvement/{{MaintainInspectId}}/{{OrgId}}">{{FileImprovementName}}</a>
                        </div>
                    </div>
                </div>
				
                <div ng-show="IsImproveReport">
                    <div class="form-group">
                        <label for="fileImprovement" class="form_label form_label_search form_label_gray">改善報告上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileImprovement"
                            ng-model="fileImprovement" name="fileImprovement" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileImprovement()">
                                <i class="fas fa-fw fa-file fa-lg"></i>
                                <s:message code="pleaseSelect" />
                                <s:message code="messageAttachment" />
                            </button>
                            <span>{{fileImprovement.name}}</span>
                            <button class="btn btn-danger" ng-click="deletefileImprovement()" ng-show="fileImprovement.name!=null">
                                <i class="far fa-fw fa-trash-alt"></i>
                                <s:message code="btnDelete" />
                            </button>
                        </div>
                    </div>
                </div>
     	<!-- 改善報告結束 -->
     	<!-- 流程紀錄Start -->
		<div ng-if="Log!=0">
        	<div class="form_group">
            	<h4 class="form_heading form_heading form_heading_yellow">
                	<i class="fa fa-info-circle"><b>
                	<s:message code="globalProcessLog" /></b></i>
                </h4>
            </div>
			<div class="form_group">
                        <table class="table table-striped table-bordered table-hover table_customer table_gray">
                            <thead>
                                <tr>
                                    <th>
                                        <s:message code="globalProcessLogAction" />
                                    </th>
                                    <th>
                                        <s:message code="globalProcessLogStatus" />
                                    </th>
                                    <th>
                                        <s:message code="globalProcessLogSednTime" />
                                    </th>
                                     <th>
                                        <s:message code="globalProcessLogFrom" />
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="log in Log">
                                    <td>
										{{log.PreStatus==3?"設定稽核基本資料":""}}
										{{log.PreStatus==4?"進行稽核前訪談調查":""}}
										{{log.PreStatus==5?"已繳交稽核前訪談調查⽂件":""}}
										{{log.PreStatus==6?"確定稽核委員名單":""}}
										{{log.PreStatus==7?"已上傳稽核評分表":""}}
										{{log.PreStatus==8?"已上傳稽核報告":""}}
										{{log.PreStatus==9?"已繳交改善報告":""}}								
										<i class="fas fa-fw fa-arrow-right"></i>
										{{log.Status==3?"設定稽核基本資料":""}}
										{{log.Status==4?"完成稽核基本資料設定":""}}
										{{log.Status==5?"上傳稽核前訪談調查⽂件":""}}
										{{log.Status==6?"輸入稽核委員名單":""}}
										{{log.Status==7?"上傳稽核評分表":""}}
										{{log.Status==8?"上傳稽核報告":""}}
										{{log.Status==9?"繳交改善報告":""}}                                      
                                    </td>
                                    <td>
										<span ng-if="log.Status==3">設定稽核基本資料</span>
										<span ng-if="log.Status==4">進行稽核前訪談調查</span>
										<span ng-if="log.Status==5">已繳交稽核前訪談調查⽂件</span>
										<span ng-if="log.Status==6">確定稽核委員名單</span>			
										<span ng-if="log.Status==7">已上傳稽核評分表</span>				
										<span ng-if="log.Status==8">已上傳稽核報告</span>
										<span ng-if="log.Status==9">已繳交改善報告</span>	                                      
                                    </td>
                                    <td>
                                        {{ log.CreateTime}}
                                    </td>
                                    <td>
                                        {{ log.CreateName}}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
        </div>		
		<!-- 流程紀錄END -->
     	
     	<div class="form_group"></div>
     	<!-- 按鈕開始 -->
     	<div class="row tab_block text-center">
     		<!--  暫存 -->
     		 <button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="questionnaire_submit(false)" ng-show="IsQuestionnaire"
     		 ng-disabled="fileCheckList.name==null || fileQuestionnaire.name==null">
                    <i class="fas fa-fw fa-save"></i>
                    <s:message code="btnTempSave" />
             </button>
             <!-- 送出-->
        	<button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="questionnaire_submit(true)" ng-show="IsQuestionnaire"
                ng-disabled="fileCheckList.name==null || fileQuestionnaire.name==null">
                <i class="fas fa-fw fa-check"></i>
                <s:message code="btnSubmit" />
           </button>
           <!-- 改善-->
           <button class="btn btn_custom btn_blue"aria-expanded="true" ng-click="improvement_submit()" ng-show="IsImproveReport"
               ng-disabled="fileImprovement.name==null || fileImprovement.name==null || improvementAbstract==null || improvementAbstract==''">
                    <i class="fas fa-fw fa-check"></i>
                 <s:message code="btnSubmit" />
            </button>

            <!-- 補件，如果需要紀錄func要重新設計或是增加參數-->
        	<button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="questionnaire_submit(false)" ng-show="AllowHospitalPatch">
                <i class="fas fa-fw fa-check"></i>
               	補件/補述
           </button>
     		<!-- 返回 -->
     		<button class="btn btn_custom btn_gray" aria-expanded="true" ng-click="closeEdit()">
            	<i class="fas fa-fw fa-undo"></i>
                <s:message code="btnReturn" />
        	</button>
        </div>
        <!-- 按鈕結束-->
	</div>
	<!-- divEdit結束 -->
	<%@ include file="../include/footer.jsp"%>
</body>
</html>