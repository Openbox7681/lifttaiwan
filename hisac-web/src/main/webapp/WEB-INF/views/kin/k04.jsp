<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k04.js"></script>
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
								<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="notifyStatus" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
										<option value="1" selected>1-上傳稽核評分表</option>										
										<option value="2" selected>2-已上傳稽核評分表</option>																								
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
							ng-click="setStatus('1');">
								上傳稽核評分表
								{{Status6Count}}
							</button>						
							<button class="btn btn-primary btn_bottom_fix"
							ng-click="setStatus('2');">
								已上傳稽核評分表
							</button>							
						</div>
					</div>
				</div>
				</form>
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
							<th><a href="" ng-click="setSortName('orgName')">醫院名稱<i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>	
							<th><a href="" ng-click="setSortName('year')">年度 <i
									ng-show="sorttype != 'year'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'year' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'year' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							<c:if test="${isPmo}">
								<th><a href="" ng-click="setSortName('committeeMemberName')">委員 <i
										ng-show="sorttype != 'year'"
										class="fas fa-fw fa-sort text-muted"></i><i
										ng-show="sorttype == 'year' && !sortreverse"
										class="fas fa-fw fa-caret-down"></i> <i
										ng-show="sorttype == 'year' && sortreverse"
										class="fas fa-fw fa-caret-up"></i> </a></th>
							</c:if>																
							<th><a href="" ng-click="setSortName('status')"><s:message
										code="messageStatus" /><i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>		
							
							
										
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
						
							<td>{{item.Title}}</td>
							<td>{{item.OrgName}}</td>			
							<td>{{item.Year}}</td>
							<td ng-if="${isPmo}">{{item.CommitteeMemberName}}</td>										
							<td>		
							<span ng-if="item.Status == false">上傳稽核評分表</span>				
							<span ng-if="item.Status">已上傳稽核評分表</span>										
							</td>									
							
							<td class="text-center">
								<a class="btn btn-sm btn-primary"
								ng-click="editData(item.Id);" title='評分 '
								ng-if="(item.Status == false || item.InspectStatus == 6) && item.CommitteeUpload">
									評分 </a>
							</td>									
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
	<form name="editForm">
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
		
     	<!-- 稽核評分表開始 -->
     	<div ng-show="IsAuditScore" ng-if="false">
           	<div class="form_group">
				<h4 class="form_heading form_heading form_heading_yellow">
					<i class="fa fa-info-circle"><b>稽核評分表</b></i>
				</h4>
			</div>
        </div>

		<div class="form_group" ng-show="IsAuditScore" ng-if="false">
			<label class="form_label form_label_search form_label_gray">稽核評分表</label>
			<div class="form_input form_input_search">
				<button class="btn btn-primary" ng-click="addAuditScore()">
					<i class="fas fa-fw fa-plus"></i>
					<s:message code="messageAddNewRecord" />
					評分項目
				</button>
				<table class="table table-striped table-bordered table-hover table_customer table_gray"
						ng-show="auditScoreList.length > 0">
					<thead>
						<tr>
							<th width="70%">項目名稱</th>										
							<th width="20%">分數</th>										
							<th width="10%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="data in auditScoreList">
							<td><input id="AuditScoreName_{{$index+1}}"
								name="AuditScoreName_{{$index+1}}" ng-model=data.AuditScoreName
								required type="text" class="form-control"></td>
							<td><input id="AuditScoreScore_{{$index+1}}"
								name="AuditScoreScore_{{$index+1}}" ng-model=data.AuditScoreScore
								required type="number" class="form-control"></td>																					
							<td class="text-center">																						
								<button class="btn btn-primary"
									ng-click="UpAuditScore($index)">
									<i class="fas fa-caret-up"></i>
									上移
								</button>
								<button class="btn btn-primary"
									ng-click="DownAuditScore($index)">
									<i class="fas fa-caret-down"></i>
									下移
								</button>
								<button class="btn btn-danger"
									ng-click="deleteAuditScore($index)">
									<i class="fas fa-fw fa-times"></i>
									<s:message code="btnDelete" />
								</button></td>											
						</tr>
					</tbody>													
				</table>
			</div>
			<div ng-repeat="item in auditScoreList" ng-show="!IsAuditScore">
                <div class="form_group">
                    <label class="form_label form_label_search form_label_gray">{{item.AuditScoreName}}</label>
                    <div class="form_input form_input_search">                            
                        <input type="number" id="{{item.Id}}" name="{{item.Id}}"
                            ng-disabled="true" ng-model="item.AuditScoreScore"
                            class="form-control" />
                    </div>
                </div>
            </div>
		</div>
		
        <div>
            <div class="form_group">
                <h4 class="form_heading form_heading form_heading_yellow">
                    <i class="fa fa-info-circle"><b>書審意見檔案</b></i>
                </h4>
            </div>
        </div>
                 
        <div ng-show="IsAuditScore">
            <div class="form-group">
                <label for="fileReviewOpinion" class="form_label form_label_search form_label_gray">書審意見檔案上傳</label>
                <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileReviewOpinion"
                    ng-model="fileReviewOpinion" name="fileReviewOpinion" ngf-pattern=".docx" accept=".docx"
                    ngf-max-size="100MB" ngf-min-height="100">
                    <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileReviewOpinion()">
                        <i class="fas fa-fw fa-file fa-lg"></i>
                        <s:message code="pleaseSelect" />
                        <s:message code="messageAttachment" />
                    </button>
                    <span>{{fileReviewOpinion.name}}</span>
                    <button class="btn btn-danger" ng-click="deletefileReviewOpinion()" ng-show="fileReviewOpinion.name!=null">
                        <i class="far fa-fw fa-trash-alt"></i>
                        <s:message code="btnDelete" />
                    </button>
                </div>
            </div>
        </div>                                
        <div>
            <div class="form_group">
                <h4 class="form_heading form_heading form_heading_yellow">
                    <i class="fa fa-info-circle"><b>稽核發現暨評分表檔案</b></i>
                </h4>
            </div>
        </div>

        <div ng-show="IsAuditScore">
            <div class="form-group">
                <label for="fileAuditScore" class="form_label form_label_search form_label_gray">稽核發現暨評分表檔案上傳</label>
                <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileAuditScore"
                    ng-model="fileAuditScore" name="fileAuditScore" ngf-pattern=".docx" accept=".docx"
                    ngf-max-size="100MB" ngf-min-height="100">
                    <button id="SelectAttachment" class="btn btn-primary" ng-disabled="fileReviewOpinion.name == null" ng-click="changefileAuditScore()">
                        <i class="fas fa-fw fa-file fa-lg"></i>
                        <s:message code="pleaseSelect" />
                        <s:message code="messageAttachment" />
                    </button>
                    <span>{{fileAuditScore.name}}</span>
                    <button class="btn btn-danger" ng-click="deletefileAuditScore()" ng-show="fileAuditScore.name!=null">
                        <i class="far fa-fw fa-trash-alt"></i>
                        <s:message code="btnDelete" />
                    </button>
                </div>
            </div>
        </div>
     	<!-- 稽核評分表結束 -->
     	<div class="form_group"></div>
     	<!-- 按鈕開始 -->
     	<div class="row tab_block text-center"> 
     		<!--  暫存 -->
     		 <button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="auditScore_submit(false)"
     		 ng-disabled="fileReviewOpinion.name == null || !editForm.$valid">
                    <i class="fas fa-fw fa-save"></i>
                    <s:message code="btnTempSave" />
             </button>
              <!--  書審-送出 -->
     		 <button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="auditScore_submit(false)"
     		 ng-show="fileReviewOpinion.name != null && fileAuditScore.name == null" 
     		 ng-disabled="fileReviewOpinion.name == null || !editForm.$valid">
                	<i class="fas fa-fw fa-check"></i>
                	<s:message code="btnSubmit" />
             </button>
             <!--  評分-送出 -->
     		 <button class="btn btn_custom btn_blue" aria-expanded="true" ng-click="auditScore_submit(true)"
     		 ng-show="fileReviewOpinion.name != null && fileAuditScore.name != null" 
     		 ng-disabled="fileReviewOpinion.name == null || !editForm.$valid">
                	<i class="fas fa-fw fa-check"></i>
                	<s:message code="btnSubmit" />
             </button>  	
     		<!-- 返回 -->
     		<button class="btn btn_custom btn_gray" aria-expanded="true" ng-click="closeEdit()">
            	<i class="fas fa-fw fa-undo"></i>
                <s:message code="btnReturn" />
        	</button>
        </div>
        <!-- 按鈕結束-->
        </form>
	</div>
	<!-- divEdit結束 -->
	<%@ include file="../include/footer.jsp"%>
</body>
</html>