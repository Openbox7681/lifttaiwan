<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/mtp/m01.js"></script>
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
					<c:if test="${isHisacBoss || isAdmin}">
					<div>
						<div class="form_group">
							<label for="QuerySDate"
								class="form_label form_label_search form_label_gray">建立時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="SDate" name="SDate"
										ng-model="SDate" class="form-control"
										placeholder="建立時間(起)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EDate" name="EDate"
										ng-model="EDate" class="form-control"
										placeholder="建立時間(訖)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${!isHisacBoss && !isAdmin}">
					<div>
						<div class="form_group">
							<label for="QuerySDate"
								class="form_label form_label_search form_label_gray">填寫時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="SDate" name="SDate"
										ng-model="SDate" class="form-control"
										placeholder="填寫時間(起)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EDate" name="EDate"
										ng-model="EDate" class="form-control"
										placeholder="填寫時間(訖)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					</c:if>
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="notifyStatus" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<c:if
										test="${isAdmin || isHisacBoss}">
										<option value="1" selected>1-
											編輯中</option>										
									</c:if>
									<option value="2" selected>2-
											待回覆</option>										
									<option value="3" selected>3-
											已結案</option>
									<c:if
										test="${isMemberContact || isMemberAdmin}">
										
										<option value="31" selected>31-
											進行資安維護計畫實施情形上傳</option>
										<option value="32" selected>32-
											已繳交資安維護計畫實施情形上傳</option>
										<%-- 
										<option value="5" selected>5-
											進行稽核前訪談調查</option>
										<option value="6" selected>6-
											已繳交稽核前訪談調查文件</option>										
										<option value="8" selected>8-
											查閱稽核結果並繳交改善報告</option>
										<option value="9" selected>9-
											已繳交改善報告</option>
											--%>
									</c:if>																										
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
									placeholder="資安維護計畫名稱"
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
							<c:if
								test="${isAdmin || isHisacBoss}">
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('1');">
									編輯中																							
								</button>
							</c:if>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('2');">
									待回覆
									<c:if test="${ isMemberContact || isMemberAdmin}">
									{{Status2Count}}
									</c:if>
								</button>												
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('3');">
								已結案
							</button>	
							<c:if
										test="${isMemberContact || isMemberAdmin}">
										
										<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('31');">
								進行資安維護計畫實施情形上傳
								<c:if test="${ isMemberContact || isMemberAdmin}">
								{{Status31Count}}
								</c:if>
							</button>
							
							
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('32');">
								已繳交資安維護計畫實施情形
							</button>
										
								
												
										
								<%--		
										<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('5');">
								進行稽核前訪談調查
								<c:if test="${ isMemberContact || isMemberAdmin}">
								{{Status5Count}}
								</c:if>
							</button>
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('6');">
								已繳交稽核前訪談調查文件
							</button>						
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('8');">
								查閱稽核結果並繳交改善報告
								<c:if test="${ isMemberContact || isMemberAdmin}">
								{{Status8Count}}
								</c:if>
							</button>
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('9');">
								已繳交改善報告
							</button>	
							--%>						
									</c:if>						
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
							<th><a href="" ng-click="setSortName('title')"> 資安維護計畫名稱 <i ng-show="sorttype != 'title'"
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
							<c:if test="${  isAdmin}">
							<td><a href=""
								ng-click="review(item.Id, 0, item.Status);" ng-show="item.Status == 2 || item.Status == 3 || item.Status == 4"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>								
								<a href="#my-modal" data-toggle="modal" ng-click="open_member(item)"
								ng-show="item.Status == 2 || item.Status == 3 || item.Status == 4">{{item.Title}}</a>
								<a href="" ng-click="edit(item.Id);"
								ng-show="item.Status == 1">{{item.Title}}</a>
							</td>	
							</c:if>
							<c:if test="${ isHisacBoss}">
							<td><a href=""
								ng-click="review(item.Id, 0, item.Status);" ng-show="item.Status == 2 || item.Status == 3 || item.Status == 4"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>								
								<a href="#my-modal" data-toggle="modal" ng-click="open_member(item)"
								ng-show="item.Status == 2 || item.Status == 3 || item.Status == 4">{{item.Title}}</a>
								<a href="" ng-click="review(item.Id, 0, item.Status);"
								ng-show="item.Status == 1">{{item.Title}}</a>
							</td>	
							</c:if>
							<c:if test="${ isMemberContact || isMemberAdmin}">
							<td><a 
							
								role="button"
								
								data-toggle = "tab"
								href="#tabMaintainPlan"
							
							
								ng-click="review(item.Id, ${orgId}, item.Status);"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>	
															
								<a href="" ng-click="reply(item.Id, ${orgId});" ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && ((item.Status==2 && item.IsReply) || item.Status==4)"
								>{{item.Title}}</a>
								<a href="" ng-click="uploadQuestionnaire(item.Id, ${orgId});" ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && item.Status==5"
								>{{item.Title}}</a>
								
								<a
								 href="" ng-click="submitMaintainPlanImplement(item.Id, ${orgId});" ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && item.Status==31"
								>{{item.Title}}</a>
								
																
								<a 
								
					
								href=""
								 ng-click="review(item.Id, ${orgId}, item.Status);" ng-if="!((item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && ((item.Status==2 && item.IsReply) || item.Status==4 || item.Status==5 ||item.Status==31))"
								>{{item.Title}}</a>															
							</td>	
							</c:if>												
							<td>{{item.Year}}</td>												
							<td>
							 
							<span ng-if="item.Status==1">編輯中</span>
							<span ng-if="item.Status==2">待回覆</span>
							<span ng-if="item.Status==3">已結案</span>
							<span ng-if="item.Status==31"> 進行資安維護計畫實施情形上傳</span>
							<span ng-if="item.Status==32"> 已繳交資安維護計畫實施情形</span>
							<span ng-if="item.Status==4">待回覆</span>
							<%--
							<span ng-if="item.Status==5">進行稽核前訪談調查</span>
							<span ng-if="item.Status==6">已繳交稽核前訪談調查文件</span>			
							<span ng-if="item.Status==7">已繳交稽核前訪談調查文件</span>				
							<span ng-if="item.Status==8">查閱稽核結果並繳交改善報告</span>
							<span ng-if="item.Status==9">已繳交改善報告</span>	
							--%>									
							</td>
							<c:if test="${isAdmin}">
							<td>{{item.CreateTime}}</td>
							</c:if>												
							
							
							<c:if test="${  isAdmin }">						
							<td class="text-center">
							
								<!-- 編輯資料 -->
							
							<a class="btn btn-sm btn-primary"
								ng-click="edit(item.Id);" title='<s:message code="btnEdit" />'
								ng-show="item.Status==1"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnEdit" /> </a> 
									
									
								<!-- 刪除資料 -->
									
									
									 <a href="#"
								class="btn btn-sm btn-info" ng-click="deleteData(item.Id);"
								title='<s:message code="btnDelete" />'
								ng-show="item.Status==1"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
									
								<!-- 上傳共同改善建議事項 -->
									<a 
									class="btn btn-sm btn-primary"
									
									ng-click="uploadImprovementSuggest(item.Id, ${orgId});" title='上傳共同改善建議事項'									
									>
									上傳共同改善建議事項
									</a>
								
									
									
							</td>							
							</c:if>
							<c:if test="${  isHisacBoss }">	
							<td></td>		
							</c:if>
							<c:if test="${ isMemberContact || isMemberAdmin}">
							<td class="text-center"><a class="btn btn-sm btn-primary"
								ng-click="reply(item.Id, ${orgId});" title='回覆'
								ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && ((item.Status==2 && item.IsReply) || item.Status==4)">
									回覆 </a> 
								<%--
								<a class="btn btn-sm btn-primary"
								ng-click="uploadQuestionnaire(item.Id, ${orgId});" title='稽核前訪談調查文件'
								ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && item.Status==5">
									稽核前訪談調查文件 </a>
									--%>
								<a class="btn btn-sm btn-primary"
								
								data-toggle = "tab"
								href="#tabMaintainPlan"
							
								ng-click="submitMaintainPlanImplement(item.Id, ${orgId});" title='進行資安維護計畫實施情形上傳'
								ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && item.Status==31">
									進行資安維護計畫實施情形上傳 </a>
									
									
									
								<%--	
								<a class="btn btn-sm btn-primary"
								ng-click="uploadImprovement(item.Id, ${orgId});" title='改善報告'
								ng-if="(item.MemberId==null || item.MemberId==${memberId} || item.MemberId==0) && item.Status==8">
									改善報告</a> 
									--%>
								<p ng-if="item.MemberId!=null && item.MemberId!=${memberId} && item.MemberId!=0">填寫者為：{{item.MemberName}}</p>
								
								<!-- 讀取共同改善建議事項 -->
								<%-- 	<a ng-if="item.IsSuggestAttachExist"
									class="btn btn-sm btn-primary"
									data-toggle = "tab"
									href="#tabImprovementSuggest"
									ng-click="review(item.Id, ${orgId}, item.Status);" title='讀取共同改善建議事項'									
									ng-if="item.Status==2 || item.Status ==3 || item.Status ==4 || item.Status ==7 || item.Status==8 || item.Status ==9">
									讀取共同改善建議事項
									</a>	 --%>
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

	<!-- start my-modal -->
	<div id="my-modal" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div>				
					<strong >回覆數：{{memberReplyNum}}/{{openMembers.length}}</strong>
				</div>
				<div style="text-align:center;">				
					<strong>{{title}}</strong>
				</div>
				<div style="text-align: right; padding: 20px;">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>					
					<button class="btn btn_blue btn_bottom_fix" ng-click="exportData(title);">
							<i class="fas fa-fw fa-file-excel"></i><s:message
								code="globalExcel" />
					</button>
				</div>
				<div class="modal-body">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><span><s:message code="messagePostReplyId" /></span></th>
								<th><span>機關(醫院)名稱</span></th>								
								<th><span>資安維護計畫回覆日期</span></th>
								<th><span>資安維護計畫回覆時間</span></th>	
								<th><span>狀態</span></th>	
								<th><span>是否讀取共同改善建議事項</span></th>	
								<th><span>共同改善建議事項讀取時間</span></th>	
								
									
								<th><span></span></th>																											
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in openMembers">
								<td><a href=""
									ng-click="review(Id, item.GroupId, item.Status);"
									role="button" data-dismiss="modal"> <i
										class="fas fa-file-alt fa-lg fa-pull-left"></i></a>{{$index+1}}</td>
								<td>{{item.Name}}</td>								
								<td>{{item.ReplyTime.split(' ')[0]}}</td>
								<td>{{item.ReplyTime.split(' ')[1]}}</td>
								<td>
									<span ng-if="item.Status==2">待回覆</span>
									<span ng-if="item.Status==3">已結案</span>
									<span ng-if="item.Status==31">進行資安維護計畫實施情形上傳</span>
									<span ng-if="item.Status==32">已繳交資安維護計畫實施情形</span>
									
									
									
									<span ng-if="item.Status==4">待回覆</span>
									<span ng-if="item.Status==5">進行稽核前訪談調查</span>
									<span ng-if="item.Status==6">已繳交稽核前訪談調查文件</span>	
									<span ng-if="item.Status==7">已上傳稽核評分結果</span>	
									<span ng-if="item.Status==8">已上傳稽核結果</span>
									<span ng-if="item.Status==9">已繳交改善報告</span>
								</td>
								<td>
								<span ng-if="item.IsRead">是</span> 
								<span ng-if="!item.IsRead">否</span>
								
								</td>
								<td>
								
								<span ng-if="item.IsRead">{{item.ReadTime}}</span> 
								<span ng-if="!item.IsRead"></span>
								
								
									
								</td>
								
								
								<td>
									<c:if test="${  isAdmin }">	
									<button class="btn btn-primary" ng-if="item.Status==2 || item.Status==4 " ng-click="clearMember(Id, item.GroupId)" data-dismiss="modal">清除填寫者</button>
									<button class="btn btn-primary" ng-if="item.Status==3 || item.Status==32 " ng-click="returnMember(Id, item.GroupId, item.Status)" data-dismiss="modal">退回</button>		
									
									<button class="btn btn-primary" ng-if="item.Status==3" ng-click="startImplement(Id, item.GroupId)" data-dismiss="modal">進行資安維護計畫實施情形繳交</button>
									
									
								   <!-- 	開始稽核狀態條件改為從32開始 TODO -->
								   <%--
									<button class="btn btn-primary" ng-if="item.Status==32" ng-click="startAudit(Id, item.GroupId)" data-dismiss="modal">進行稽核前訪談調查</button>
									
									<button class="btn btn-primary" ng-if="item.Status==6" ng-click="submitAuditScore(Id, item.GroupId)" data-dismiss="modal">上傳稽核評分表</button>									
									<button class="btn btn-primary" ng-if="item.Status==7" ng-click="submitAuditResult(Id, item.GroupId)" data-dismiss="modal">上傳稽核結果</button>
									
									<button class="btn btn-primary" ng-if="item.Status==9" ng-click="startImplementFromAudit(Id, item.GroupId)" data-dismiss="modal">進行資安維護計畫實施情形繳交</button>
									--%>									
									</c:if>
								</td>																														
						</tbody>
					</table>
				</div>
				
				
				<div class="modal-body" id="exporExcel" ng-show="false">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><span><s:message code="messagePostReplyId" /></span></th>
								<th><span>機關(醫院)名稱</span></th>								
								<th><span>資安維護計畫回覆日期</span></th>
								<th><span>資安維護計畫回覆時間</span></th>	
								<th><span>狀態</span></th>																																				
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in openMembers">
								<td>{{$index+1}}</td>
								<td>{{item.Name}}</td>								
								<td>{{item.ReplyTime.split(' ')[0]}}</td>
								<td>{{item.ReplyTime.split(' ')[1]}}</td>
								<td>
									<span ng-if="item.Status==2">待回覆</span>
									<span ng-if="item.Status==3">已結案</span>
									
									<span ng-if="item.Status==31">進行資安維護計畫實施情形上傳</span>
									<span ng-if="item.Status==32">已繳交資安維護計畫實施情形</span>
	
									<span ng-if="item.Status==4">待回覆</span>
									<%--
									<span ng-if="item.Status==5">進行稽核前訪談調查</span>
									<span ng-if="item.Status==6">已繳交稽核前訪談調查文件</span>					
									<span ng-if="item.Status==7">已上傳稽核評分結果</span>
									<span ng-if="item.Status==8">已上傳稽核結果</span>
									<span ng-if="item.Status==9">已繳交改善報告</span>
									--%>																	
								</td>																																						
						</tbody>
					</table>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>					
					<button class="btn btn_blue btn_bottom_fix" ng-click="exportData(title);">
							<i class="fas fa-fw fa-file-excel"></i><s:message
								code="globalExcel" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End my-modal -->

	<div id="divEdit" class="container" ng-show="createAndupdate_show">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnIns">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnCreate" /></b>
			</h4>
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnUpd">
				<big><i class="fas fa-fw fa-edit fa-lg"></i></big><b><s:message
						code="btnEdit" /></b>
			</h4>
		</div>
			<div class="row">
			<ul class="nav nav-tabs" id="tabStep">
				<li class="active"><a data-toggle="tab" href="#tabAlert"
					aria-expanded="true">資安維護計畫資料</a></li>
				<li><a data-toggle="tab" href="#tabItem" aria-expanded="true">資安維護計畫項目</a></li>
				<li class=""><a data-toggle="tab" href="#tabMember"
					aria-expanded="false">調查對象資料</a></li>
			</ul>
		</div>
		<form name="editForm">
			<div class="tab-content tab_content">
				<div id="tabAlert" class="tab-pane active">																
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray">計畫名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="Title" name="Title"
									placeholder="資安維護計畫名稱" ng-required="true"
									ng-maxlength="512" ng-model="Title" class="form-control" />
								<h5 class="text-danger"
									ng-show="editForm.Title.$error.maxlength && editForm.Title.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="512" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Year"
								class="form_label form_label_search form_label_gray">計畫年度</label>
							<div class="form_input form_input_search">
								<input type="number" id="Year" name="Year"
									placeholder="資安維護計畫年度" ng-required="true"
									ng-maxlength="4" ng-model="Year" class="form-control" />
								<h5 class="text-danger"
									ng-show="editForm.Year.$error.maxlength && editForm.Year.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="4" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SPlanDate"
								class="form_label form_label_search form_label_gray">填寫時間(起)</label>
							<div class="form_input form_input form_input_search">
								<div class="input-group">
									<input id="SPlanDate" name="SPlanDate" ng-model="SPlanDate"
										type="text" class="form-control" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
						<div>
						<div class="form_group">
							<label for="EPlanDate"
								class="form_label form_label_search form_label_gray">填寫時間(訖)</label>
							<div class="form_input form_input form_input_search">
								<div class="input-group">
									<input id="EPlanDate" name="EPlanDate" ng-model="EPlanDate"
										type="text" class="form-control" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>													
																						
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				<div id="tabItem" class="tab-pane">
					<div>
						<div class="form_group">
							<label for="Item"
								class="form_label form_label_search form_label_gray">資安維護計畫項目</label>
							<div class="form_input form_input_search">
								<button class="btn btn-primary" ng-click="addItem()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								計畫項目
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="itemList.length > 0">
								<thead>
									<tr>
										<th width="30%">項目名稱</th>										
										<th width="50%">描述</th>
										<th width="15%">型態</th>
										<th width="5%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in itemList">
										<td><input id="ItemName_{{$index+1}}"
											name="ItemName_{{$index+1}}" ng-model=data.ItemName
											required type="text" class="form-control"></td>
										<td><textarea id="ItemDesc_{{$index+1}}" rows="5"
											name="ItemDesc_{{$index+1}}" ng-model=data.ItemDesc
											required type="text" class="form-control"></textarea></td>
										<td><select id="ItemType_{{$index+1}}" name="ItemType_{{$index+1}}"
											ng-model="data.ItemType" class="form-control">											
											<option value="title" selected>大標</option>
											<option value="string" selected>文字</option>
											<option value="int" selected>數字</option>																								
											</select>
										</td>											
										<td class="text-center">											
											<a href="#my-modal_addSubItem" class="btn btn-primary" 								
								data-toggle="modal" ng-click="open_my_modal_addSubItem($index)">新增/修改
												子計畫</a>
										    <button class="btn btn-primary"
												ng-click="UpItem($index)">
												<i class="fas fa-caret-up"></i>
												上移
											</button>
											<button class="btn btn-primary"
												ng-click="DownItem($index)">
												<i class="fas fa-caret-down"></i>
												下移
											</button>
											<button class="btn btn-danger"
												ng-click="deleteItem($index)">
												<i class="fas fa-fw fa-times"></i>
												<s:message code="btnDelete" />
											</button></td>											
									</tr>
								</tbody>													
							</table>
							</div>
						</div>
					</div>	
					
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				<div id="tabMember" class="tab-pane">					
					<div>
					<label class="form_label form_label_gray" style="text-align:center;width:100%;color:red;">(功能尚未開放)調查對象條件(以下條件需全符合)</label>
					</div>
					<div>
						<div class="form_group">
							<label for="Org"
								class="form_label form_label_search form_label_gray">醫院層級</label>
							<div class="form_input form_input_search">
							<span> <label
									class="label_for_radio"><input type="checkbox" disabled ng-change="changeChooseAllOrg(isChooseAllOrg)" ng-model="isChooseAllOrg"><b
										ng-if="isChooseAllOrg" />全選</b><normal ng-if="!isChooseAllOrg">全選</normal></label></span>
								<span ng-repeat="org in orgs"> <label
									class="label_for_radio"><input type="checkbox" disabled
										ng-bind="org.Name" ng-model="org.Action"><b
										ng-if="org.Action" />{{org.Name}}</b> <normal ng-if="!org.Action" />{{org.Name}}</normal></label>
								</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Level"
								class="form_label form_label_search form_label_gray">資訊安全責任等級</label>
							<div class="form_input form_input_search">
								<span> <label
									class="label_for_radio"><input type="checkbox" disabled ng-change="changeChooseAllLevel(isChooseAllLevel)" ng-model="isChooseAllLevel"><b
										ng-if="isChooseAllLevel" />全選</b><normal ng-if="!isChooseAllLevel">全選</normal></label></span>
								<span ng-repeat="level in levels"> <label
									class="label_for_radio"><input type="checkbox" disabled
										ng-bind="level.Name" ng-model="level.Action"><b
										ng-if="level.Action" />{{level.Name}}</b> <normal ng-if="!level.Action" />{{level.Name}}</normal></label>
								</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="City"
								class="form_label form_label_search form_label_gray">縣市</label>
							<div class="form_input form_input_search">
								<span> <label
									class="label_for_radio"><input type="checkbox" disabled ng-change="changeChooseAllCity(isChooseAllCity)" ng-model="isChooseAllCity"><b
										ng-if="isChooseAllCity" />全選</b><normal ng-if="!isChooseAllCity">全選</normal></label></span>
								<span ng-repeat="city in citys"> <label
									class="label_for_radio"><input type="checkbox" disabled
										ng-bind="city.Name" ng-model="city.Action"><b
										ng-if="city.Action" />{{city.Name}}</b> <normal ng-if="!city.Action" />{{city.Name}}</normal></label>
								</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsOrgPub"
								class="form_label form_label_search form_label_gray">是否屬公務機關</label>
							<div class="form_input form_input_search">
								<span> <label
									class="label_for_radio"><input type="checkbox" disabled ng-change="changeChooseAllIsOrgPub(isChooseAllIsOrgPub)" ng-model="isChooseAllIsOrgPub"><b
										ng-if="isChooseAllIsOrgPub" />全選</b><normal ng-if="!isChooseAllIsOrgPub">全選</normal></label></span>
								<span ng-repeat="isOrgPub in isOrgPubs"> <label
									class="label_for_radio"><input type="checkbox" disabled
										ng-bind="isOrgPub.Name" ng-model="isOrgPub.Action"><b
										ng-if="isOrgPub.Action" />{{isOrgPub.Name}}</b> <normal ng-if="!isOrgPub.Action" />{{isOrgPub.Name}}</normal></label>
								</span>
							</div>
						</div>
					</div>
					<div>
					<label class="form_label form_label_gray" style="text-align:center;width:100%;color:red;">其他對象</label>
					</div>
					<div>
						<div class="form_group">
							<label for="Recipient"
								class="form_label form_label_search form_label_gray">機構名稱</label>
						</div>
						<div class="form_input form_input_search">
							<button class="btn btn-primary" ng-click="addMember()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								機構
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="memberList.length > 0">
								<thead>
									<tr>
										<th width="80%">機構</th>
										<th width="20%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in memberList">
										<td><select id="Member_{{$index+1}}"
											name="Member_{{$index+1}}" ng-model=data.MemberId
											class="form-control col-xs-6"
											ng-options="Member.Id as Member.Name for Member in data.Members"
											ng-required="true">
												<option value="" selected>
													<s:message code="pleaseSelect" />機構</option>
										</select></td>
										<td class="text-center"><button class="btn btn-danger"
												ng-click="deleteMember($index)">
												<i class="fas fa-fw fa-times"></i>
												<s:message code="btnDelete" />
											</button></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RecipientGroup"
								class="form_label form_label_search form_label_gray">機構群組</label>
						</div>
						<div class="form_input form_input_search">
							<button class="btn btn-primary" ng-click="addGroup()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								機構群組
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="groupList.length > 0">
								<thead>
									<tr>
										<th width="80%">機構群組</th>
										<th width="20%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in groupList">
										<td><select id="Group_{{$index+1}}"
											name="Group_{{$index+1}}" ng-model=data.GroupId
											class="form-control col-xs-6"
											ng-options="Group.Id as Group.Name for Group in data.Groups"
											ng-required="true">
												<option value="" selected>
													<s:message code="pleaseSelect" />機構群組</option>
										</select></td>
										<td class="text-center"><button class="btn btn-danger"
												ng-click="deleteGroup($index)">
												<i class="fas fa-fw fa-times"></i>
												<s:message code="btnDelete" />
											</button></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				<div class="row tab_block text-center">
					<button class="btn btn_custom btn_blue"
						ng-click="createData(false,false)" ng-show="btnIns">
						<i class="fas fa-fw fa-save"></i>
						<s:message code="btnTempSave" />
					</button>
					<button class="btn btn_custom btn_blue"
						ng-click="createData(true,false)" ng-show="btnIns">
						<i class="fas fa-fw fa-save"></i>
						<s:message code="btnTempSaveAndExit" />
					</button>
					<button class="btn btn_custom btn_blue"
						ng-click="!editForm.$valid || createData(true,true)"
						ng-disabled="!editForm.$valid" ng-show="btnIns">
						<i class="fas fa-fw fa-check"></i>
						<s:message code="btnSubmit" />
					</button>
					<button class="btn btn_custom btn_blue"
						ng-click="updateData(false,false)" ng-show="btnUpd">
						<i class="fas fa-fw fa-save"></i>
						<s:message code="btnTempSave" />
					</button>
					<button class="btn btn_custom btn_blue"
						ng-click="updateData(true,false)" ng-show="btnUpd">
						<i class="fas fa-fw fa-save"></i>
						<s:message code="btnTempSaveAndExit" />
					</button>
					<button class="btn btn_custom btn_blue"
						ng-click="!editForm.$valid || updateData(true,true)"
						ng-disabled="!editForm.$valid" ng-show="btnUpd">
						<i class="fas fa-fw fa-check"></i>
						<s:message code="btnSubmit" />
					</button>
					<button class="btn btn_custom btn_gray" type="button"
						ng-click="back()">
						<i class="fas fa-fw fa-undo"></i>
						<s:message code="btnReturn" />
					</button>
				</div>
			</div>
		</form>
	</div>
	
	
	<!-- start my-modal -->
	<div id="my-modal_addSubItem" class="modal fade">
	<form name="subItemForm">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					
				</div>			
				<div style="text-align:center;">				
					<strong>{{itemList[openSubItemNumber].ItemName}}</strong>
				</div>											
				<label for="IsReply"
								class="form_label form_label_search form_label_gray">子項目</label>
							<div class="form_input form_input_search">
								<button class="btn btn-primary" ng-click="addSubItem(openSubItemNumber)">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								計畫子項目
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="itemList[openSubItemNumber].ItemSubItem.length > 0">
								<thead>
									<tr>
										<th width="30%">項目名稱</th>										
										<th width="50%">描述</th>
										<th width="15%">型態</th>
										<th width="5%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in itemList[openSubItemNumber].ItemSubItem">
										<td><input id="SubItemName_{{openSubItemNumber}}_{{$index+1}}"
											name="SubItemName_{{openSubItemNumber}}_{{$index+1}}" ng-model=data.ItemName
											required type="text" class="form-control"></td>
										<td><textarea id="SubItemDesc_{{openSubItemNumber}}_{{$index+1}}" rows="5"
											name="SubItemDesc_{{openSubItemNumber}}_{{$index+1}}" ng-model=data.ItemDesc
											required type="text" class="form-control"></textarea></td>
										<td><select id="ItemType_{{$index+1}}" name="ItemType_{{$index+1}}"
											ng-model="data.ItemType" class="form-control">											
											<option value="title" selected>大標</option>
											<option value="string" selected>文字</option>
											<option value="int" selected>數字</option>																								
											</select>
										</td>											
										<td class="text-center">																						
											<button class="btn btn-primary"
												ng-click="UpSubItem($index)">
												<i class="fas fa-caret-up"></i>
												上移
											</button>
											<button class="btn btn-primary"
												ng-click="DownSubItem($index)">
												<i class="fas fa-caret-down"></i>
												下移
											</button>
											<button class="btn btn-danger"
												ng-click="deleteSubItem($index)">
												<i class="fas fa-fw fa-times"></i>
												<s:message code="btnDelete" />
											</button></td>											
									</tr>
								</tbody>													
							</table>
							</div>																					
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal" ng-disabled="!subItemForm.$valid">						
						確認
					</button>									
				</div>
			</div>
		</div>
		</form>
	</div>
	<!-- End my-modal -->

	<div class="container" ng-show="review_show">
    <div class="row">
        <ul class="nav nav-tabs" id="tabStep">
            <li id="tab1" ng-if = "IsMaintainPlanTab" class="active"><a data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true">資安維護計畫</a></li>
            
            <!-- 資通安全維護計畫實施情形頁面109.10.15新增 staus為31與32 -->
            
           
            <li id="tab31" ng-if = "IsMaintainPlanImplementTab"><a data-toggle="tab" href="#tabMaintainPlanImplement" aria-expanded="true">資安維護計畫實施情形</a></li>
            
            
            <li id="tab2" ng-if="IsQuestionnaireTab"><a data-toggle="tab" href="#tabQuestionnaire" aria-expanded="true">稽核前訪談調查文件</a></li>
            <c:if test="${isHisacBoss || isAdmin}"><li id="tab3" ng-if="IsAuditScoreTab"><a data-toggle="tab" href="#tabAuditScore" aria-expanded="true">稽核評分表</a></li></c:if>
            <c:if test="${isAdmin || isMemberAdmin}"><li id="tab4" ng-if="IsAuditResultTab"><a data-toggle="tab" href="#tabAuditResult" aria-expanded="true">稽核結果報告</a></li></c:if>
            <li id="tab5" ng-if="IsImprovementTab"><a data-toggle="tab" href="#tabImprovement" aria-expanded="true">改善報告</a></li>
            <li id="tab6" ng-if="IsImprovementSuggestTab"> <a data-toggle = "tab" href="#tabImprovementSuggest" aria-expanded="true"> 共同改善建議事項</a></li>
        </ul>
    </div>
    <form class="form-horizontal" role="form" name="myForm" novalidate>
        <div class="tab-content tab_content">
            <div id="tabMaintainPlan" class="tab-pane active">
                <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>資安維護計畫資料</b></i>
                        </h4>
                    </div>
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
                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">填寫時間(起)</label>
                        <span>{{SPlanDate}}</span>
                    </div>
                </div>
                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">填寫時間(訖)</label>
                        <span>{{EPlanDate}}</span>
                    </div>
                </div>
                <div ng-repeat="item in itemList">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">{{item.ItemName}}</label>
                        <div class="form_input form_input_search" ng-if="item.ItemType!='title'">
                            <textarea id="{{item.Id}}" name="{{item.Id}}" ng-if="item.ItemType=='string'"
                                placeholder="{{item.ItemDesc}}" ng-disabled="!IsReply" ng-model="item.Content"
                                class="form-control">
                            </textarea>
                            <input type="number" id="{{item.Id}}" name="{{item.Id}}" ng-if="item.ItemType=='int'"
                                placeholder="{{item.ItemDesc}}" ng-disabled="!IsReply" ng-model="item.Content"
                                class="form-control" />
                        </div>
                    </div>
                </div>

                <c:if test="${isHisacBoss || isAdmin}">
                    <div>
                        <div class="form_group">
                            <h4 class="form_heading form_heading form_heading_yellow">
                                <i class="fa fa-info-circle"><b>調查對象</b></i>
                            </h4>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_gray"
                                style="text-align:center;width:100%;color:red;">調查對象條件(以下條件需全符合)</label>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">醫院層級</label>
                            <div class="form_input form_input_search">
                                <span>{{ Org }}</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">資訊安全責任等級</label>
                            <div class="form_input form_input_search">
                                <span>{{ Level }}</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">縣市</label>
                            <div class="form_input form_input_search">
                                <span>{{ City }}</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">是否屬公務機關</label>
                            <div class="form_input form_input_search">
                                <span>{{ IsOrgPub }}</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_gray"
                                style="text-align:center;width:100%;color:red;">其他對象</label>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">機構名稱</label>
                            <div class="form_input form_input_search">
                                <span>{{ Member }}</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="form_group">
                            <label class="form_label form_label_search form_label_gray">機構群組</label>
                            <div class="form_input form_input_search">
                                <span>{{ Group }}</span>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div ng-if="Log!=null">
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
                                        {{ log.PreStatus==1?"編輯中":"" }}
                                        {{ log.PreStatus==2?"待回覆":""}}
                                        {{ log.PreStatus==3?"已完成":""}}
                                        {{ log.PreStatus==31?"進行資安維護計畫實施情形上傳":""}}
                                        {{ log.PreStatus==32?"已繳交資安維護計畫實施情形":""}}
                                        
                                   
                                        {{ log.PreStatus==4?"待回覆":""}}
                                        {{ log.PreStatus==5?"進行稽核前訪談調查":""}}
                                        {{ log.PreStatus==6?"已繳交稽核前訪談調查文件":""}}                                                         
                                        {{ log.PreStatus==7?"已完成稽核評分":""}}        
                                        {{ log.PreStatus==71?"已繳交稽核前訪談調查文件":""}}                                        
                                        {{ log.PreStatus==8?"查閱稽核結果並繳交改善報告":""}} 
                                        {{ log.PreStatus==9?"已繳交改善報告":""}}                                                                             
                                        <i class="fas fa-fw fa-arrow-right"></i>
                                        {{ log.Status==1?"編輯中":"" }}
                                        {{ log.Status==2?"待回覆":""}}
                                        {{ log.Status==3?"已完成":""}}
                                        {{ log.Status==31?"進行資安維護計畫實施情形上傳":""}}
                                        {{ log.Status==32?"已繳交資安維護計畫實施情形":""}}
                                        {{ log.Status==4?"待回覆":""}}
                                        {{ log.Status==5?"進行稽核前訪談調查":""}}
                                        {{ log.Status==6?"已繳交稽核前訪談調查文件":""}}                                       
                                        {{ log.Status==7?"已完成稽核評分":""}}                                        
                                        {{ log.Status==8?"查閱稽核結果並繳交改善報告":""}}  
                                        {{ log.Status==9?"已繳交改善報告":""}}                                       
                                    </td>
                                    <td>
                                        {{ log.Status==1?"編輯中":"" }}
                                        {{ log.Status==2?"待回覆":""}}
                                        {{ log.Status==3?"已完成":""}}
                                        {{ log.Status==31?"進行資安維護計畫實施情形上傳":""}}
                                        {{ log.Status==32?"已繳交資安維護計畫實施情形":""}}
                                        {{ log.Status==4?"待回覆":""}}
                                        {{ log.Status==5?"進行稽核前訪談調查":""}}
                                        {{ log.Status==6?"已繳交稽核前訪談調查文件":""}}
                                        {{ log.Status==7?"已完成稽核評分":""}} 
                                        {{ log.Status==8?"查閱稽核結果並繳交改善報告":""}} 
                                        {{ log.Status==9?"已繳交改善報告":""}}                                         
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

                <div ng-if="FileName!=null">
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>檔案下載</b></i>
                        </h4>
                    </div>
                </div>

                <div ng-if="FileName!=null && !IsReply">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">資安維護計畫word檔案</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/{{Id}}/{{OrgId}}">{{FileName}}</a>
                        </div>
                    </div>
                </div>

                <div ng-show="IsReply">
                    <div class="form-group">
                        <label for="file1" class="form_label form_label_search form_label_gray">(必填)資安維護計畫word上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="file1"
                            ng-model="file1" name="file1" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefile()">
                                <i class="fas fa-fw fa-file fa-lg"></i>
                                <s:message code="pleaseSelect" />
                                <s:message code="messageAttachment" />
                            </button>
                            <span>{{file1.name}}</span>
                            <button class="btn btn-danger" ng-click="deletefile()" ng-show="file1.name!=null">
                                <i class="far fa-fw fa-trash-alt"></i>
                                <s:message code="btnDelete" />
                            </button>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="form_group"></div>
                </div>
            </div>
            
             <!-- 資通安全維護計畫實施情形頁面109.10.15新增 staus為31與32 start-->
             <div id="tabMaintainPlanImplement" class="tab-pane">
             
             
             <div ng-if="!IsMaintainPlanImplement">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">資安維護計畫實施情形</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/implement/{{Id}}/{{OrgId}}">{{FileImplementName}}</a>
                        </div>
                    </div>
                </div>
                
                
                <div ng-show="IsMaintainPlanImplement">
                    <div class="form-group">
                        <label for="fileImplement" class="form_label form_label_search form_label_gray">(必填)資安維護計畫實施情形上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileImplement"
                            ng-model="fileImplement" name="fileImplement" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileImplement()">
                                <i class="fas fa-fw fa-file fa-lg"></i>
                                <s:message code="pleaseSelect" />
                                <s:message code="messageAttachment" />
                            </button>
                            <span>{{fileImplement.name}}</span>
                            <button class="btn btn-danger" ng-click="deletefileImplement()" ng-show="fileImplement.name!=null">
                                <i class="far fa-fw fa-trash-alt"></i>
                                <s:message code="btnDelete" />
                            </button>
                        </div>
                    </div>
                     <div class="form_group">
                    
                    <h5 class = "text-danger">
       
                    	檔案格式限制為doc格式，檔案上限為20MB
                    </h5>
                    
                    </div>
                    
                    
                </div>
                
              
             
             
             
             
            </div>
             
             
             
             <!-- 資通安全維護計畫實施情形頁面109.10.15新增 staus為31與32 end-->
             
            
            
            <%-- 
            
            <div id="tabQuestionnaire" class="tab-pane">
                  <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>稽核前訪談調查格式檔案下載</b></i>
                        </h4>
                    </div>
                </div>
                
                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">資通安全實地稽核項目檢核表格式</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/download/CheckList">資通安全實地稽核項目檢核表格式.docx</a>
                        </div>
                    </div>
                </div>
                
                 <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">受稽機關現況調查表格式</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/download/Questionnaire">受稽機關現況調查表格式.docx</a>
                        </div>
                    </div>
                </div>
                
                 <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>資通安全實地稽核項目檢核表</b></i>
                        </h4>
                    </div>
                </div>

                <div ng-if="FileCheckListName!=null && !IsQuestionnaire">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">資通安全實地稽核項目檢核表</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/checkList/{{Id}}/{{OrgId}}">{{FileCheckListName}}</a>
                        </div>
                    </div>
                </div>

                <div ng-show="IsQuestionnaire">
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
                
                  <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>受稽機關現況調查表</b></i>
                        </h4>
                    </div>
                </div>				
                <div ng-if="FileQuestionnaireName!=null && !IsQuestionnaire">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">受稽機關現況調查表</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/questionnaire/{{Id}}/{{OrgId}}">{{FileQuestionnaireName}}</a>
                        </div>
                    </div>
                </div>

                <div ng-show="IsQuestionnaire">
                    <div class="form-group">
                        <label for="fileQuestionnaire" class="form_label form_label_search form_label_gray">(必填)受稽機關現況調查表上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileQuestionnaire"
                            ng-model="fileQuestionnaire" name="fileQuestionnaire" ngf-pattern=".doc,.docx,.pdf" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
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
                    
                    <h5 class = "text-danger">
       
                    	檔案格式限制為pdf與doc格式，檔案上限為20MB
                    </h5>
                    
                    </div>
                    
                </div>
                <div>
                    <div class="form_group"></div>
                </div>
            </div>
            
            <c:if test="${isHisacBoss || isAdmin}"><div id="tabAuditScore" class="tab-pane"> 
            <div ng-show="IsAuditScore">				
				<div class="form_group">
					<label
							class="form_label form_label_search form_label_gray">稽核評分表</label>
					<div class="form_input form_input_search">
								<button class="btn btn-primary" ng-click="addAuditScore()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								評分項目
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
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
						</div>						
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
                 <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>稽核評分表檔案</b></i>
                        </h4>
                    </div>
                </div>

                <div ng-if="FileAuditScoreName!=null && !IsAuditScore">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">稽核評分表檔案</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/auditScore/{{Id}}/{{OrgId}}">{{FileAuditScoreName}}</a>
                        </div>
                    </div>
                </div>

                <div ng-show="IsAuditScore">
                    <div class="form-group">
                        <label for="fileAuditScore" class="form_label form_label_search form_label_gray">稽核評分表檔案上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileAuditScore"
                            ng-model="fileAuditScore" name="fileAuditScore" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileAuditScore()">
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
                                                  			                             
                <div>
                    <div class="form_group"></div>
                </div>
            </div>
            </c:if>
            
            
         <c:if test="${isAdmin || isMemberAdmin}">
            
            
            <div id="tabAuditResult" class="tab-pane">                                 
                 <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>稽核結果摘要</b></i>
                        </h4>
                    </div>
                </div>

                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">稽核結果摘要</label>
                        <div class="form_input form_input_search">
                             <textarea ng-disabled="!IsAuditResult" ng-model="auditResultAbstract"
                                class="form-control">
                            </textarea>
                        </div>
                    </div>
                </div>


				<div ng-if="FileAuditResultName!=null && !IsAuditResult">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">稽核結果</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/auditResult/{{Id}}/{{OrgId}}">{{FileAuditResultName}}</a>
                        </div>
                    </div>
                </div>
				
                <div ng-show="IsAuditResult">
                    <div class="form-group">
                        <label for="fileAuditResult" class="form_label form_label_search form_label_gray">稽核結果上傳</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileAuditResult"
                            ng-model="fileAuditResult" name="fileAuditResult" ngf-pattern=".doc,.docx" accept=".doc,.docx"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileAuditResult()">
                                <i class="fas fa-fw fa-file fa-lg"></i>
                                <s:message code="pleaseSelect" />
                                <s:message code="messageAttachment" />
                            </button>
                            <span>{{fileAuditResult.name}}</span>
                            <button class="btn btn-danger" ng-click="deletefileAuditResult()" ng-show="fileAuditResult.name!=null">
                                <i class="far fa-fw fa-trash-alt"></i>
                                <s:message code="btnDelete" />
                            </button>
                        </div>
                    </div>
                </div>
                                                 
                <div>
                    <div class="form_group"></div>
                </div>
            </div>
            
            </c:if>
            
            <div id="tabImprovement" class="tab-pane">                                 
                 <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>改善報告摘要</b></i>
                        </h4>
                    </div>
                </div>

                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">改善報告摘要</label>
                        <div class="form_input form_input_search">
                             <textarea ng-disabled="!IsImprovement" ng-model="improvementAbstract"
                                class="form-control">
                            </textarea>
                        </div>
                    </div>
                </div>


				<div ng-if="FileImprovementName!=null && !IsImprovement">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">改善報告</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/improvement/{{Id}}/{{OrgId}}">{{FileImprovementName}}</a>
                        </div>
                    </div>
                </div>
				
                <div ng-show="IsImprovement">
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
                                                 
                <div>
                    <div class="form_group"></div>
                </div>
            </div>
            --%>
            	<!-- Start 1.1共同改善建議事項 -->
            
            
             <div id="tabImprovementSuggest" class="tab-pane">   
             	 <div>
                    <div class="form_group">
                        <h4 class="form_heading form_heading form_heading_yellow">
                            <i class="fa fa-info-circle"><b>共同改善建議事項</b></i>
                        </h4>
                    </div>
                </div>
                
                <div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">共同改善建議事項內容</label>
                        <div class="form_input form_input_search">
                             <textarea ng-model="improvementSuggestAbstract" ng-disabled = "!IsImprovementSuggest"
                                class="form-control">
                            </textarea>
                        </div>
                    </div>
                </div>
                
                
                 <c:if test="${isMemberContact || isMemberAdmin}">
                  
                  <div ng-if="FileImprovementSuggestName!=null && !IsImprovementSuggest">
                  	<div class="form_group">
                  	
                  		<label for="isRead"
								class="form_label form_label_search form_label_gray"><s:message
						code="isRead" /></label>
						<div class="form_input form_input_search_half">
								<toggle ng-model="IsRead" ng-disabled = "IsRead"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isReadTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isReadFalse" />'
									
									ng-change = "setImprovementSuggestRead()"
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
                  	</div>    
                  </div>
                 </c:if>
                 
                 <div ng-if="FileImprovementSuggestName!=null && !IsImprovementSuggest">
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray">共同改善建議事項文件</label>
                        <div class="form_input form_input_search">
                            <a href="./api/m01/attach/download/improvementSuggest/{{Id}}/{{OrgId}}">{{FileImprovementSuggestName}}</a>
                        </div>
                    </div>
                </div>
                
                
                <div ng-show="IsImprovementSuggest">
                    <div class="form-group">
                        <label for="fileImprovementSuggest" class="form_label form_label_search form_label_gray">改善報告建議事項文件</label>
                        <div class="form_input form_input_search" style="display: inline-block" ngf-select="" id="fileImprovementSuggest"
                            ng-model="fileImprovementSuggest" name="fileImprovementSuggest" ngf-pattern=".doc,.docx,.pdf" accept=".doc,.docx,.pdf"
                            ngf-max-size="100MB" ngf-min-height="100">
                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changefileImprovementSuggest()">
                                <i class="fas fa-fw fa-file fa-lg"></i>
                                <s:message code="pleaseSelect" />
                                <s:message code="messageAttachment" />
                            </button>
                            <span>{{fileImprovementSuggest.name}}
                            
                   
                            
                            </span>
                            <button class="btn btn-danger" ng-click="deletefileImprovementSuggest()" ng-show="fileImprovementSuggest.name!=null">
                                <i class="far fa-fw fa-trash-alt"></i>
                                <s:message code="btnDelete" />
                            </button>
                        </div>
                    </div>
                </div>
                
                <div>
                    <div class="form_group"></div>
                </div>
                      
               </div>
               
               
                  <!-- Start 1.1共同改善建議事項 END -->
               
             
            
            
            
            
            <div class="row tab_block text-center">
                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="reply_submit(false,false)" ng-show="IsReply">
                    <i class="fas fa-fw fa-save"></i>
                    <s:message code="btnTempSave" />
                </button>

                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="reply_submit(true,false)" ng-show="IsReply">
                    <i class="fas fa-fw fa-save"></i>
                    <s:message code="btnTempSaveAndExit" />
                </button>

                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="reply_submit(true,true)" ng-show="IsReply"
                    ng-disabled="file1.name==null">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                
                 <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlanImplement" aria-expanded="true" ng-click="implement_submit()" ng-show="IsMaintainPlanImplement"
                    ng-disabled="fileImplement.name==null">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="questionnaire_submit()" ng-show="IsQuestionnaire"
                    ng-disabled="fileCheckList.name==null || fileQuestionnaire.name==null">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                 <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="auditScore_submit()" ng-show="IsAuditScore"
                    ng-disabled="fileAuditScore.name==null || fileAuditScore.name==null || !myForm.$valid">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="auditResult_submit()" ng-show="IsAuditResult"
                    ng-disabled="fileAuditResult.name==null || fileAuditResult.name==null || auditResultAbstract==null || auditResultAbstract==''">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                <button class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="improvement_submit()" ng-show="IsImprovement"
                    ng-disabled="fileImprovement.name==null || fileImprovement.name==null || improvementAbstract==null || improvementAbstract==''">
                    <i class="fas fa-fw fa-check"></i>
                    <s:message code="btnSubmit" />
                </button>
                
                 <button 
                 class="btn btn_custom btn_blue" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="improvementSuggest_submit()"
                  ng-show="IsImprovementSuggest"
                  ng-disabled="fileImprovementSuggest.name==null || improvementSuggestAbstract==null || improvementSuggestAbstract==''"
                   >
                    <i class="fas fa-fw fa-check"></i>
                   
                    <s:message code="btnSubmit" />
                   
                </button>
                
                
                

                <button class="btn btn_custom btn_gray" data-toggle="tab" href="#tabMaintainPlan" aria-expanded="true" ng-click="back()">
                    <i class="fas fa-fw fa-undo"></i>
                    <s:message code="btnReturn" />
                </button>
            </div>
        </div>
    </form>
</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>