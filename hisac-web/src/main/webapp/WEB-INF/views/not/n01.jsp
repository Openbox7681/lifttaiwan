<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/not/n01.js"></script>
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
							<label for="QuerySApplyDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="notifyApplyDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="SPostDateTime" name="SPostDateTime"
										ng-model="SPostDateTime" class="form-control"
										placeholder="<s:message code="notifySApplyDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EPostDateTime" name="EPostDateTime"
										ng-model="EPostDateTime" class="form-control"
										placeholder="<s:message code="notifyEApplyDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
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
										test="${isAdmin || isHisac || isHisacInfoSign || isHisacInfoBuilder}">
										<option value="1" selected>1-
											<s:message code="messageStatus1" /></option>
										<option value="2" selected>2-
											<s:message code="messageStatus2" /></option>
										<option value="3" selected>3-
											<s:message code="messageStatus3" /></option>
									</c:if>
									<option value="4" selected>4-
										<s:message code="messageStatus4" /></option>
									<c:if test="${!isApplyContact}">
										<option value="5" selected>5-
											<s:message code="messageStatus5" /></option>
										<option value="6" selected>6-
											<s:message code="messageStatus6" /></option>
										<option value="7" selected>7-
											<s:message code="messageStatus7" /></option>
									</c:if>
									<c:if test="${!isApplySign}">
										<option value="8" selected>8-
											<s:message code="messageStatus8" /></option>
									</c:if>
									<c:if
										test="${isAdmin || isHisac || isHisacInfoSign || isHisacInfoBuilder}">
										<option value="9" selected>9-
											<s:message code="messageStatus9" /></option>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIsReply"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageIsReply" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsReply" name="QueryIsReply"
									ng-model="QueryIsReply" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message
											code="messageIsReplyTrue" /></option>
									<option value="false"><s:message
											code="messageIsReplyFalse" /></option>
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
									placeholder="<s:message code="messageKeywordPlaceHolder" />"
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
								test="${isAdmin || isHisac || isHisacInfoSign || isHisacInfoBuilder}">
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('1');">
									<s:message code="messageStatus1" />
									<c:if test="${ isHisacInfoBuilder}">
									{{ButtonCount1}}
									</c:if>
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('2');">
									<s:message code="messageStatus2" />
									<c:if test="${ isHisacInfoBuilder}">
									{{ButtonCount2}}
									</c:if>
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('3');">
									<s:message code="messageStatus3" />
									<c:if test="${ isHisacInfoSign}">
									{{ButtonCount3}}
									</c:if>
								</button>
							</c:if>
							<button class="btn btn-primary btn_bottom_fix"
								ng-click="setStatus('4');">
								<s:message code="messageStatus4" />
							</button>
							<c:if test="${!isApplyContact}">
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('5');">
									<s:message code="messageStatus5" />
									<c:if test="${ isMemberContact}">
									{{ButtonCount5}}
								</c:if>
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('6');">
									<s:message code="messageStatus6" />
									<c:if test="${ isHisacInfoSign || isApplySign}">
									{{ButtonCount5}}
								</c:if>
								</button>
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('7');">
									<s:message code="messageStatus7" />
								</button>
							</c:if>
							<c:if test="${!isApplySign}">
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('8');">
									<s:message code="messageStatus8" />
								</button>
							</c:if>
							<c:if
								test="${isAdmin || isHisac || isHisacInfoSign || isHisacInfoBuilder}">
								<button class="btn btn-primary btn_bottom_fix"
									ng-click="setStatus('9');">
									<s:message code="messageStatus9" />
								</button>
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
				<c:if test="${isHisacInfoBuilder && actionCreate}">
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
							<th><a href="" ng-click="setSortName('postId')"> <s:message
										code="messagePostId" /> <i ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('createTime')"><s:message
										code="messageCreateTime" /> <i
									ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							<th><a href="" ng-click="setSortName('postDateTime')"><s:message
										code="messagePostDateTime" /> <i
									ng-show="sorttype != 'postDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							<th width="15%"><a href=""
								ng-click="setSortName('sourceCode')"> <s:message
										code="messageSourceCode" /> <i
									ng-show="sorttype != 'sourceCode'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sourceCode' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sourceCode' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('subject')"> <s:message
										code="messageSubject" /> <i ng-show="sorttype != 'subject'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'subject' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'subject' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('status')"><s:message
										code="messageStatus" /><i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							<th><a href="" ng-click="setSortName('isReply')"> <s:message
										code="messageIsReply" /> <i ng-show="sorttype != 'isReply'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'isReply' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isReply' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isTest')"> 是否為演練 <i ng-show="sorttype != 'isTest'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'isTest' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isTest' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><a href=""
								ng-click="review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'view');"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>
								<a href="#my-modal"
								ng-click="open_messagePostReleaseList(item.MessagePostRelease);"
								data-toggle="modal"
								ng-show="item.Status == 5 || item.Status == 7">{{item.PostId}}</a>
								<a href="" ng-click="click_postid(item);"
								ng-show="!(item.Status == 5 || item.Status == 7)">{{item.PostId}}</a>
							</td>
							<td>{{item.CreateTime}}</td>
							<td>{{item.PostDateTime}}</td>
							<td>{{getSourceName(item.SourceCode)}}</td>
							<td class="ellipsis" style="max-width: 200px;">{{item.Subject}}</td>
							<td><span ng-if="item.Status==1"><s:message
										code="messageStatus1" /></span> <span ng-if="item.Status==2"><s:message
										code="messageStatus2" /></span> <span ng-if="item.Status==3"><s:message
										code="messageStatus3" /></span> <span ng-if="item.Status==4"><s:message
										code="messageStatus4" /></span> <span
								ng-if="item.Status==5 && ${!isApplySign && !isHisacInfoSign}">
									<s:message code="messageStatus5" />
							</span> <span
								ng-if="item.Status==5 && ${isApplySign || isHisacInfoSign}">
									待回覆({{item.WaitReply}})<br>待確認({{item.WaitDetermine}})<br>已確認({{item.Determine}})
							</span> <span ng-if="item.Status==6"><s:message
										code="messageStatus6" /></span> <span ng-if="item.Status==7"><s:message
										code="messageStatus7" /></span> <span ng-if="item.Status==8"><s:message
										code="messageStatus8" /></span> <span ng-if="item.Status==9"><s:message
										code="messageStatus9" /></span> <span ng-if="item.Status==10"><s:message
										code="messageStatus10" /></span> <span ng-if="item.Status==51"><s:message
										code="messagePostReplyStatus51" /></span><span ng-if="item.IsUndo"><s:message
										code="messageUndo" /></span><span ng-if="item.Status==61"><s:message
										code="messagePostReplyStatus61" /></span> <span
								ng-if="item.Status==62"><s:message
										code="messagePostReplyStatus62" /></span> <span
								ng-if="item.Status==63"><s:message
										code="messagePostReplyStatus63" /></span> <span
								ng-if="item.Status==69"><s:message
										code="messagePostReplyStatus69" /></span></td>
							<td class="text-center"><span ng-if="item.IsReply==true"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="messageIsReplyTrue" />'></i> <s:message
										code="messageIsReplyTrue" /></span> <span ng-if="item.IsReply!=true"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="messageIsReplyFalse" />'></i> <s:message
										code="messageIsReplyFalse" /></span></td>
							<td class="text-center"><span ng-if="item.IsTest==true"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="messageIsReplyTrue" />'></i> <s:message
										code="messageIsReplyTrue" /></span> <span ng-if="item.IsTest!=true"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="messageIsReplyFalse" />'></i> <s:message
										code="messageIsReplyFalse" /></span></td>
							<td class="text-center"><a class="btn btn-sm btn-primary"
								ng-click="edit(item.Id);" title='<s:message code="btnEdit" />'
								ng-show="item.IsButtonEdit"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnEdit" /> </a> <a
								class="btn btn-sm btn-primary"
								ng-click="review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'messageReview')"
								title='<s:message code="btnApply" />'
								ng-show="item.IsButtonReview"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnApply" /> </a> <a
								class="btn btn-sm btn-primary"
								ng-click="buttonToAlert(item.Id);"
								title='<s:message code="btnBuildNotification" />'
								ng-show="item.IsButtonToAlert"><i
									class="fas fa-fw fa-pencil-alt"></i> <s:message
										code="btnBuildNotification" /> </a> <a
								class="btn btn-sm btn-primary"
								ng-click="review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'reply');"
								title='<s:message code="btnReply" />'
								ng-show="item.IsButtonReply"><i class="fas fa-fw fa-reply"></i>
									<s:message code="btnReply" /> </a> <a href="#"
								class="btn btn-sm btn-info" ng-click="deleteData(item.Id);"
								title='<s:message code="btnDelete" />'
								ng-show="item.IsButtonDelete"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
								<a href="#" class="btn btn-sm btn-info"
								ng-click="examine(item.Id,'9');"
								title='<s:message code="btnReject" />'
								ng-show="item.IsButtonUndo"><i class="fas fa-fw fa-eraser"></i>
									<s:message code="btnReject" /></a></td>
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
					<strong >會員警訊回覆數：{{messagePostReleaseReplyNum}}/{{messagePostReleaseDatas.length}}</strong>
				</div>
				<div style="text-align:center;">				
					<strong>{{messagePostReleaseDatas[0].PostId}}</strong>
				</div>
				<div style="text-align: right; padding: 20px;">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>					
					<button class="btn btn_blue btn_bottom_fix" ng-click="exportData(messagePostReleaseDatas[0].PostId);">
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
								<th><span>會員別</span></th>
								<th><span>回覆日期</span></th>
								<th><span>回覆時間</span></th>
								<th><span>回復狀態</span></th>
								<th ng-show="false"><span>回覆選項</span></th>
								<th ng-show="false"><span>回覆內容</span></th>								
								<th ng-show="item.IsButtonMessagePostReleaseReview"><span></span></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in messagePostReleaseDatas">
								<td><a href=""
									ng-click="review(item.Id, item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'view');"
									role="button" data-dismiss="modal"> <i
										class="fas fa-file-alt fa-lg fa-pull-left"></i></a>{{$index+1}}</td>
								<td>{{item.MessagePostReleaseOrgName}}</td>
								<td>{{item.MessagePostReleaseCiLevel==2?"CI":item.MessagePostReleaseCiLevel==1?"非CI進階":"非CI一般"}}</td>
								<td>{{item.MessagePostReleaseReplyOptionTime.split(' ')[0]}}</td>
								<td>{{item.MessagePostReleaseReplyOptionTime.split(' ')[1]}}</td>
								<td><span ng-if="item.MessagePostReleaseStatus==51">警訊未回覆</span><span
									ng-if="item.MessagePostReleaseStatus==61">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==62">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==63">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==69">警訊已回覆</span></td>
								<td ng-show="item.IsButtonMessagePostReleaseReview"><a href="" class="btn btn-primary"
									ng-click="review(item.Id,item.MessagePostReleaseId, item.IsSeeLog, item.IsSeeMessagePostReleaseLog, 'messagePostReleaseReview');"
									title="<s:message
											code="messagePostReplySure" />"
									ng-show="item.IsButtonMessagePostReleaseReview"
									data-dismiss="modal"> <i class="fas fa-fw fa-check"></i> <s:message
											code="messagePostReplySure" />
								</a></td>								
						</tbody>
					</table>
				</div>
				
				
				<!-- Start  exporExcel-->
				<div class="modal-body" id="exporExcel" ng-show="false">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><span><s:message code="messagePostReplyId" /></span></th>
								<th><span>機關(醫院)名稱</span></th>
								<th><span>會員別</span></th>
								<th><span>回覆日期</span></th>
								<th><span>回覆時間</span></th>
								<th><span>回復狀態</span></th>
								<th><span>回覆選項</span></th>
								<th><span>回覆內容</span></th>																					
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in messagePostReleaseDatas">
								<td>{{$index+1}}</td>
								<td>{{item.MessagePostReleaseOrgName}}</td>
								<td>{{item.MessagePostReleaseCiLevel==2?"CI":item.MessagePostReleaseCiLevel==1?"非CI進階":"非CI一般"}}</td>
								<td>{{item.MessagePostReleaseReplyOptionTime.split(' ')[0]}}</td>
								<td>{{item.MessagePostReleaseReplyOptionTime.split(' ')[1]}}</td>
								<td><span ng-if="item.MessagePostReleaseStatus==51">警訊未回覆</span><span
									ng-if="item.MessagePostReleaseStatus==61">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==62">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==63">警訊未審核</span><span
									ng-if="item.MessagePostReleaseStatus==69">警訊已回覆</span></td>
								<td><div ng-bind-html="item.MessagePostReleaseReplyOption | trustHtml"></div></td>	
								<td><div ng-bind-html="item.MessagePostReleaseReplyContent | trustHtmlNewline"></div></td>																		
						</tbody>
					</table>
				</div>
				
				<!-- End  exporExcel-->
				
				<div class="modal-footer">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>					
					<button class="btn btn_blue btn_bottom_fix" ng-click="exportData();">
							<i class="fas fa-fw fa-file-excel"></i><s:message
								code="globalExcel" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End my-modal -->
	
	
	<!-- start my-member -->
	<div id="my-member" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>				
				<div style="text-align:center;">				
					<strong>已選擇機構</strong>
				</div>
				<div style="text-align: right; padding: 20px;">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>										
				</div>
				<div class="modal-body">
						<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>						
							<th width="10%"><label
								for="selectSelAllorNone"></label></th>
							<th width="25%"><a href="" ng-click="setSortName('name')">
									<span><s:message code="n05Name" /></span> <i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>						
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in members" ng-show="item.Action">
							<td><input id="selectSel_{{item.Id}}" type="checkbox"								
								class="checkbox-primary" ng-model="item.Action"></td>
							<td>{{item.Name}}</td>							
						</tr>
					</tbody>
				</table>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-danger btn_bottom_fix" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>									
				</div>
			</div>
		</div>
	</div>
	<!-- End my-member -->
	

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
					aria-expanded="true"><s:message code="messageTabAlert" /></a></li>
				<li><a data-toggle="tab" href="#tabFile" aria-expanded="true"><s:message
							code="messageTabFile" /></a></li>
				<li class=""><a data-toggle="tab" href="#tabRecipient"
					aria-expanded="false"><s:message code="messageTabRecipient" /></a></li>
				<li class=""><a data-toggle="tab" href="#tabRecipientOther"
					aria-expanded="false">其他收件者資料</a></li>
			</ul>
		</div>
		<form name="editForm">
			<div class="tab-content tab_content">
				<div id="tabAlert" class="tab-pane active">
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="PostId"
										class="form_label form_label_search form_label_gray"><s:message
											code="messagePostId" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="PostId=='' || PostId==null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="PostId!='' && PostId!=null">{{ PostId }}</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="AlertCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageAlertType" /></label>
							<div class="form_input form_input_search">
								<select id="AlertCode" name="AlertCode" ng-model="AlertCode"
									class="form-control" ng-change="UpdateAlertCode()"
									ng-options="AlertCode.Code as AlertCode.Code+'-'+AlertCode.Name for AlertCode in alertTypes"
									ng-required="true">
									<option value="" selected><s:message
											code="pleaseSelect" /><s:message code="messageAlertType" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.AlertCode.$error.required && editForm.AlertCode.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="messageAlertType" />
								</h5>
							</div>
						</div>
					</div>
					<div ng-show="AlertCode!='' && AlertCode!=null"
						ng-hide="AlertCode=='OTH'">
						<div class="form_group">
							<label for="EventCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageEventType" /></label>
							<div class="form_input form_input_search">
								<select id="EventCode" name="EventCode" ng-model="EventCode"
									class="form-control"
									ng-options="EventCode.Code as EventCode.Code+'-'+EventCode.Name for EventCode in eventTypes"
									ng-required="AlertCode!='OTH'">
									<option value="" selected><s:message
											code="pleaseSelect" /><s:message code="messageEventType" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.EventCode.$error.required && editForm.EventCode.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="messageEventType" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="SourceCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageSourceCode" /></label>
							<div class="form_input form_input_search">
								<select id="SourceCode" name="SourceCode" ng-model="SourceCode"
									class="form-control"
									ng-options="SourceCode.Code as SourceCode.Name for SourceCode in sourceCodes"
									ng-required="true">
									<option value="" selected><s:message
											code="pleaseSelect" /><s:message code="messageSourceCode" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.SourceCode.$error.required && editForm.SourceCode.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="messageSourceCode" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ExternalId"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageExternalId" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ExternalId" name="ExternalId"
									placeholder="<s:message code="messageExternalId" />"
									ng-maxlength="128" ng-model="ExternalId" class="form-control" />
								<h5 class="text-danger"
									ng-show="editForm.ExternalId.$error.maxlength && editForm.ExternalId.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FindDate"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageFindDate" /></label>
							<div class="form_input form_input form_input_search">
								<div class="input-group">
									<input id="FindDate" name="FindDate" ng-model="FindDate"
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
							<label for="Subject"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageSubject" /></label>
							<div class="form_input form_input_search">
								<textarea id="Subject" name="Subject" ng-model="Subject"
									placeholder="<s:message code="messageSubject" />" rows="5"
									autocomplete="off" class="form-control" ng-required="true"> </textarea>
								<h5 class="text-danger"
									ng-show="editForm.Subject.$error.required && editForm.Subject.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="messageSubject" />
								</h5>
								</h5>
							</div>
						</div>
					</div>
					<div ng-show="Description_show">
						<div class="form_group">
							<label for="Description"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageDescription" /></label>
							<div class="form_input form_input_search">
								<textarea id="Description" name="Description"
									placeholder="<s:message code="messageDescription" />"
									ng-model="Description" rows="5" autocomplete="off"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="Alert">
						<div class="form_group">
							<label for="Suggestion"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageSuggestion" /></label>
							<div class="form_input form_input_search">
								<textarea id="Suggestion" name="Suggestion"
									placeholder="<s:message code="messageSuggestion" />"
									ng-model="Suggestion" rows="5" autocomplete="off"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="Reference_show">
						<div class="form_group">
							<label for="Reference"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageReference" /></label>
							<div class="form_input form_input_search">
								<textarea id="Reference" name="Reference" ng-model="Reference"
									placeholder="<s:message code="messageReference" />" rows="5"
									autocomplete="off" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="Determine_show">
						<div class="form_group">
							<label for="Determine"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageDetermine" /></label>
							<div class="form_input form_input_search">
								<textarea id="Determine" name="Determine" ng-model="Determine"
									placeholder="<s:message code="messageDetermine" />" rows="5"
									autocomplete="off" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="Contents_show">
						<div class="form_group">
							<label for="Contents"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageContents" /></label>
							<div class="form_input form_input_search">
								<textarea id="Contents" name="Contents" ng-model="Contents"
									placeholder="<s:message code="messageContents" />" rows="5"
									autocomplete="off" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="AffectPlatform_show">
						<div class="form_group">
							<label for="AffectPlatform"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageAffectPlatform" /></label>
							<div class="form_input form_input_search">
								<textarea id="AffectPlatform" name="AffectPlatform"
									ng-model="AffectPlatform"
									placeholder="<s:message code="messageAffectPlatform" />"
									rows="5" autocomplete="off" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsReply"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageIsReply" /></label>
							<div class="form_input form_input_search">
								<toggle ng-model="IsReply" ng-init="IsReply=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="messageIsReplyTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="messageIsReplyFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div ng-show=IsReply >
						<div class="form_group">
							<label for="IsReply"
								class="form_label form_label_search form_label_gray">回覆選項</label>
							<div class="form_input form_input_search">
								<button class="btn btn-primary" ng-click="addReplyOption()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								回覆選項
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="replyList.length > 0">
								<thead>
									<tr>
										<th width="80%">回覆選項</th>										
										<th width="20%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in replyList">
										<td><input id="ReplyOption_{{$index+1}}"
											name="ReplyOption_{{$index+1}}" ng-model=data.ReplyOption
											required type="text" class="form-control"></td>										
										<td class="text-center"><button class="btn btn-danger"
												ng-click="deleteReplyOption($index)">
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
						<div class="form_group">
							<label for="IsSms"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageIsSms" /></label>
							<div class="form_input form_input_search">
								<toggle ng-model="IsSms" ng-init="IsSms=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="messageIsSmsTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="messageIsSmsFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div ng-show=IsSms >
						<div class="form_group">
							<label for="SmsFormat"
								class="form_label form_label_search form_label_gray">簡訊內容填寫</label>
							<div class="form_input form_input_search">														
								<textarea id="SmsFormat" name="SmsFormat" ng-model="SmsFormat"
									placeholder="請輸入簡訊內容" rows="5"
									autocomplete="off" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show=IsSms >
						<div class="form_group">
							<label for="SmsFormat"
								class="form_label form_label_search form_label_gray">簡訊內容</label>
							<div class="form_input form_input_search">							
								H-ISAC資安警訊<font color="red">警訊編號</font>
								{{SmsFormat}}							
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsTest"
								class="form_label form_label_search form_label_gray">是否為演練</label>
							<div class="form_input form_input_search">
								<toggle ng-model="IsTest" ng-init="IsTest=false"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="messageIsReplyTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="messageIsReplyFalse" />'
									offstyle="btn-success" onstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div ng-show="ImpactLevel_show">
						<div class="form_group">
							<label for="ImpactLevel"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageImpactLevel" /></label>
							<div class="form_input form_input_search">
								<select id="ImpactLevel" name="ImpactLevel"
									ng-model="ImpactLevel" class="form-control">
									<option value="" selected><s:message
											code="pleaseSelect" /><s:message code="messageImpactLevel" /></option>
									<option value="1">1-
										<s:message code="messageImpactLevel1" /></option>
									<option value="2">2-
										<s:message code="messageImpactLevel2" /></option>
									<option value="3">3-
										<s:message code="messageImpactLevel3" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				<div id="tabFile" class="tab-pane">
					<div>
						<div class="form-group">
							<label for="file1"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageAttachment" /></label>
							<div class="form_input form_input_search"
								style="display: inline-block" ngf-select="" id="file1"
								ng-model="file1" name="file1"
								ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								ngf-max-size="100MB" ngf-min-height="100">
								<button id="SelectAttachment" class="btn btn-primary">
									<i class="fas fa-fw fa-file fa-lg"></i>
									<s:message code="pleaseSelect" />
									<s:message code="messageAttachment" />
								</button>
								<span>{{file1.name}}</span>
								<button class="btn btn-danger" ng-click="deletefile(1)"
									ng-show="file1.name!=null">
									<i class="far fa-fw fa-trash-alt"></i>
									<s:message code="btnDelete" />
								</button>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FileDesc1"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalAttachmentDescription" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="FileDesc1" name="FileDesc1"
									ng-model="FileDesc1" class="form-control"
									placeholder="<s:message code="globalAttachmentDescription" />"
									rows="5" autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form-group">
							<label for="file2"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageAttachment" /></label>
							<div class="form_input form_input_search"
								style="display: inline-block" ngf-select="" id="file2"
								ng-model="file2" name="file2"
								ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								ngf-max-size="100MB" ngf-min-height="100">
								<button id="SelectAttachment" class="btn btn-primary">
									<i class="fas fa-fw fa-file fa-lg"></i>
									<s:message code="pleaseSelect" />
									<s:message code="messageAttachment" />
								</button>
								<span>{{file2.name}}</span>
								<button class="btn btn-danger" ng-click="deletefile(2)"
									ng-show="file2.name!=null">
									<i class="far fa-fw fa-trash-alt"></i>
									<s:message code="btnDelete" />
								</button>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FileDesc2"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalAttachmentDescription" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="FileDesc2" name="FileDesc2"
									ng-model="FileDesc2" class="form-control"
									placeholder="<s:message code="globalAttachmentDescription" />"
									rows="5" autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form-group">
							<label for="file3"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageAttachment" /></label>
							<div class="form_input form_input_search"
								style="display: inline-block" ngf-select="" id="file3"
								ng-model="file3" name="file3"
								ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
								ngf-max-size="100MB" ngf-min-height="100">
								<button id="SelectAttachment" class="btn btn-primary">
									<i class="fas fa-fw fa-file fa-lg"></i>
									<s:message code="pleaseSelect" />
									<s:message code="messageAttachment" />
								</button>
								<span>{{file3.name}}</span>
								<button class="btn btn-danger" ng-click="deletefile(3)"
									ng-show="file3.name!=null">
									<i class="far fa-fw fa-trash-alt"></i>
									<s:message code="btnDelete" />
								</button>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="FileDesc3"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalAttachmentDescription" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="FileDesc3" name="FileDesc3"
									ng-model="FileDesc3" class="form-control"
									placeholder="<s:message code="globalAttachmentDescription" />"
									rows="5" autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				<div id="tabRecipient" class="tab-pane">												
<!-- 					修改-->
			<div class="shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="CiLevel"
								class="form_label form_label_search form_label_gray">會員機構等級</label>
							<div class="form_input form_input_search">
								<span ng-repeat="ciLevel in ciLevels"> <label
									class="label_for_radio"><input type="checkbox"
										ng-bind="ciLevel.Name" ng-model="ciLevel.Action"><b
										ng-if="ciLevel.Action" />{{ciLevel.Name}}</b> <normal ng-if="!ciLevel.Action" />{{ciLevel.Name}}</normal></label>
								</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RecipientGroup"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageRecipientGroup" /></label>
							<div class="form_input form_input_search">
								<select id="RecipientGroup" name="RecipientGroup" ng-options="Group.Id as Group.Name for Group in groups"
									ng-model="RecipientGroup" class="form-control col-xs-6">
									<option value="" selected></option>									
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RecipientName"
								class="form_label form_label_search form_label_gray">機構名稱</label>
							<div class="form_input form_input_search">
								<input type="text" id="RecipientName" name="RecipientName"
										ng-model="RecipientName" class="form-control">
							</div>
						</div>
					</div>					
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn-primary" ng-click="queryMember()">
							<i class="ace-icon fa fa-search "></i>
							<s:message code="btnSearch" />
						</button>						
						<a href="#my-member" data-toggle="modal" class="btn btn-primary" ng-click="viewMember()">
							<i class="ace-icon fa fa-eye "></i>
							查看選擇機構
						</a>
					</div>
				</form>
			</div>
		<div>		
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>						
							<th width="10%"><input id="selectSelAllorNone"
								type="checkbox" class="checkbox" ng-model="selectSelAllorNone"
								ng-click="changeSelectAllorNone()"> <label
								for="selectSelAllorNone">全選</label></th>
							<th width="25%"><a href="" ng-click="setSortName('name')">
									<span><s:message code="n05Name" /></span> <i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>						
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in members" ng-show="item.Show">
							<td><input id="selectSel_{{item.Id}}" type="checkbox"								
								class="checkbox-primary" ng-model="item.Action"></td>
							<td>{{item.Name}}</td>							
						</tr>
					</tbody>
				</table>
			</div>
<!-- 					修改-->
					
					
					<div>
						<div class="form_group"></div>
					</div>
				</div>
				
								<div id="tabRecipientOther" class="tab-pane">												
<div>
						<div class="form_group">
							<label for="RecipientEmail"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageRecipientEmail" /></label>
						</div>
						<div class="form_input form_input_search">
							<button class="btn btn-primary" ng-click="addRecipient()">
								<i class="fas fa-fw fa-plus"></i>
								<s:message code="messageAddNewRecord" />
								<s:message code="messageRecipientEmail" />
								行動電話
							</button>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray"
								ng-show="recipientList.length > 0">
								<thead>
									<tr>
										<th width="20%"><s:message code="messageRecipientName" /></th>
										<th width="40%"><s:message
												code="messageRecipientEmailAddress" /></th>
										<th width="20%">行動電話</th>
										<th width="20%"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="data in recipientList">
										<td><input id="RecipientName_{{$index+1}}"
											name="RecipientName_{{$index+1}}" ng-model=data.RecipientName
											required type="text" class="form-control"></td>
										<td><input id="RecipientEmail_{{$index+1}}"
											name="RecipientEmail_{{$index+1}}"
											ng-model=data.RecipientEmail required type="text"
											class="form-control"></td>
										<td><input id="RecipientMobilePhone_{{$index+1}}"
											name="RecipientMobilePhone_{{$index+1}}"
											ng-model=data.RecipientMobilePhone type="text"
											class="form-control"></td>
										<td class="text-center"><button class="btn btn-danger"
												ng-click="deleteRecipient($index)">
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

	<div class="container" ng-show="review_show">
		<div class="row">
			<div class="col-xs-12 shadow_board">
				<form class="form-horizontal" role="form" name="myForm" novalidate>
					<div>
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="messageTabAlert" /></b></i>
							</h4>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messagePostId" /></label> <span>{{ PostId }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messagePostDateTime" /></label> <span>{{ PostDateTime }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageAlertType" /></label> <span>{{ AlertName }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageEventType" /></label> <span>{{ EventName }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageSourceCode" /></label> <span>{{ SourceName }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageExternalId" /></label> <span>{{ ExternalId }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageFindDate" /></label> <span>{{ FindDate }}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageSubject" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Subject | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="Description_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageDescription" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Description | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageSuggestion" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Suggestion | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="Reference_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageReference" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Reference | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="Determine_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageDetermine" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Determine | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="Contents_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageContents" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="Contents | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="AffectPlatform_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageAffectPlatform" /></label>
							<div class="form_input form_input_search">
								<span>
									<div ng-bind-html="AffectPlatform | trustHtml"></div>
								</span>
							</div>
						</div>
					</div>
					<div ng-show="ImpactLevel_show">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageImpactLevel" /></label> <span ng-if="ImpactLevel=='1'"><s:message
									code="messageImpactLevel1" /></span> <span ng-if="ImpactLevel=='2'"><s:message
									code="messageImpactLevel2" /></span> <span ng-if="ImpactLevel=='3'"><s:message
									code="messageImpactLevel3" /></span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageIsReply" /></label> <span ng-if="IsReply==true"><s:message
									code="messageIsReplyTrue" /></span> <span ng-if="IsReply!=true"><s:message
									code="messageIsReplyFalse" /></span>
						</div>
					</div>
					<div ng-show="IsReply">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">回覆選項</label>
							<div class="form_input form_input_search">
								<span>{{ ReplyOption }}</span>
							</div>
						</div>
					</div>					
					<div ng-show="IsSeeLog">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageIsSms" /></label> <span ng-if="IsSms==true"><s:message
									code="messageIsSmsTrue" /></span> <span ng-if="IsSms!=true"><s:message
									code="messageIsSmsFalse" /></span>
						</div>
					</div>
					<div ng-show="IsSms && IsSeeLog">
						<div class="form_group">
							<label for="SmsFormat"
								class="form_label form_label_search form_label_gray">簡訊內容</label>
							<div class="form_input form_input_search">
								<span>H-ISAC資安警訊<font color="red">警訊編號</font>{{SmsFormat}}</span>							
							</div>
						</div>
					</div>		
					<div ng-show="IsTest">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">是否為演練</label> 
							<span ng-if="IsTest==true"><s:message
									code="messageIsSmsTrue" /></span> <span ng-if="IsTest!=true"><s:message
									code="messageIsSmsFalse" /></span>
						</div>
					</div>			
					<div ng-show="IsSeeLog">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="messageTabRecipient" /></b></i>
							</h4>
						</div>
					</div>					
					<div ng-show="IsSeeLog">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageRecipient" /></label>
							<div class="form_input form_input_search">
								<table ng-if="Member.length > 0"
									class="table table-striped table-bordered table-hover table_customer table_gray">
									<thead>
										<tr>
											<th>機構名稱</th>
											<th>收件會員</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="data in Member">
											<td>{{data.PostContentName}}</td>
											<td>
												<p ng-repeat="memberName in data.MemberName">
													{{memberName}}
												</p>												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>					
					<div ng-show="IsSeeLog">
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="messageRecipientEmail" /></label>
							<div class="form_input form_input_search">
								<table ng-if="recipientList.length > 0"
									class="table table-striped table-bordered table-hover table_customer table_gray">
									<thead>
										<tr>
											<th><s:message code="messageRecipientName" /></th>
											<th><s:message code="messageRecipientEmailAddress" /></th>
											<th>行動電話</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="data in recipientList">
											<td>{{data.RecipientName}}</td>
											<td>{{data.RecipientEmail}}</td>
											<td>{{data.RecipientMobilePhone}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div ng-if="MessagePostAttachData.length > 0">
						<div class="form_group">
							<div class="form_group">
								<h4 class="form_heading form_heading form_heading_yellow">
									<i class="fa fa-info-circle"><b><s:message
												code="messageAttachment" /></b></i>
								</h4>
							</div>
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalAttachmentName" /></th>
										<th><s:message code="globalAttachmentDescription" /></th>
										<th><s:message code="globalAttachmentSize" /></th>
										<th><s:message code="globalHashSHA256" /></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in MessagePostAttachData">
										<td ng-if="item.FileSize != null"
											style="word-wrap: break-word; word-break: break-all; white-space: normal;">
											<a href="./api/n01/attach/download/{{Id}}/{{item.Id}}">{{item.name}}</a>
										</td>
										<td ng-if="item.FileSize != null"
											style="word-wrap: break-word; word-break: break-all; white-space: normal;">{{item.FileDesc}}</td>
										<td ng-if="item.FileSize != null"
											style="word-wrap: break-word; word-break: break-all; white-space: normal;">{{item.FileSize}}</td>
										<td ng-if="item.FileSize != null"
											style="word-wrap: break-word; word-break: break-all; white-space: normal;">{{item.FileHash}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div ng-show="IsSeeLog">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessLogAction" /></th>
										<th><s:message code="globalProcessLogStatus" /></th>
										<th><s:message code="globalProcessLogFrom" /></th>
										<th><s:message code="globalProcessLogSednTime" /></th>
										<th><s:message code="globalProcessLogContent" /></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in MessageProcessLogData">
										<td><span ng-if="item.PreStatus==1"><s:message
													code="messageShowPreStatus1" /></span> <span
											ng-if="item.PreStatus==2"><s:message
													code="messageShowPreStatus2" /></span> <span
											ng-if="item.PreStatus==3"><s:message
													code="messageShowPreStatus3" /></span> <span
											ng-if="item.PreStatus==10"><s:message
													code="messageShowPreStatus10" /></span> <span><i
												class="fas fa-fw fa-arrow-right"></i></span> <span
											ng-if="item.Status==2"><s:message
													code="messageShowStatus2" /></span> <span ng-if="item.Status==3"><s:message
													code="messageShowStatus3" /></span> <span ng-if="item.Status==5"><s:message
													code="messageShowStatus5" /></span> <span ng-if="item.Status==8"><s:message
													code="messageShowStatus8" /></span> <span ng-if="item.Status==9"><s:message
													code="messageShowStatus9" /></span> <span ng-if="item.Status==10"><s:message
													code="messageShowStatus10" /></span></td>
										<td><span ng-if="item.Status==1"><s:message
													code="messageStatus1" /></span> <span ng-if="item.Status==2"><s:message
													code="messageStatus2" /></span> <span ng-if="item.Status==3"><s:message
													code="messageStatus3" /></span> <span ng-if="item.Status==4"><s:message
													code="messageStatus4" /></span> <span ng-if="item.Status==5"><s:message
													code="messageStatus5" /></span> <span ng-if="item.Status==6"><s:message
													code="messageStatus6" /></span> <span ng-if="item.Status==7"><s:message
													code="messageStatus7" /></span> <span ng-if="item.Status==8"><s:message
													code="messageStatus8" /></span> <span ng-if="item.Status==9"><s:message
													code="messageStatus9" /></span> <span ng-if="item.Status==10"><s:message
													code="messageStatus10" /></span></td>
										<td>{{item.CreateName}}</td>
										<td>{{item.CreateTime}}</td>
										<td>{{item.Opinion}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div ng-show="IsSeeMessagePostReleaseLog && MessagePostReleaseLogData.length>0">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessLogAction" /></th>
										<th><s:message code="globalProcessLogStatus" /></th>
										<th><s:message code="globalProcessLogFrom" /></th>
										<th><s:message code="globalProcessLogSednTime" /></th>
										<th><s:message code="globalProcessLogContent" /></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in MessagePostReleaseLogData">
										<td><span ng-if="item.PreStatus==3"><s:message
													code="messageShowPreStatus3" /></span> <span
											ng-if="item.PreStatus==51"><s:message
													code="messageShowPreStatus51" /></span> <span
											ng-if="item.PreStatus==61"><s:message
													code="messageShowPreStatus61" /></span> <span
											ng-if="item.PreStatus==62"><s:message
													code="messageShowPreStatus62" /></span> <span
											ng-if="item.PreStatus==63"><s:message
													code="messageShowPreStatus63" /></span> <span><i
												class="fas fa-fw fa-arrow-right"></i></span> <span
											ng-if="item.Status==51 && item.PreStatus==3"><s:message
													code="messageStatus51Pre3" /></span> <span
											ng-if="item.Status==51 && item.PreStatus!=3"><s:message
													code="messageStatus51PreNot3" /></span> <span
											ng-if="item.Status==61"><s:message
													code="messagePostReplyStatus61" /></span> <span
											ng-if="item.Status==62"><s:message
													code="messagePostReplyStatus62" /></span> <span
											ng-if="item.Status==63"><s:message
													code="messagePostReplyStatus63" /></span> <span
											ng-if="item.Status==69"><s:message
													code="messagePostReplyStatus69" /></span></td>
										<td><span ng-if="item.Status==51"><s:message
													code="messagePostReplyStatus51" /></span> <span
											ng-if="item.Status==61"><s:message
													code="messagePostReplyStatus61" /></span> <span
											ng-if="item.Status==62"><s:message
													code="messagePostReplyStatus62" /></span> <span
											ng-if="item.Status==63"><s:message
													code="messagePostReplyStatus63" /></span> <span
											ng-if="item.Status==69"><s:message
													code="messagePostReplyStatus69" /></span></td>
										<td>{{item.CreateName}}</td>
										<td>{{item.CreateTime}}</td>
										<td>{{item.Opinion}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div ng-show="IsSeeOpinion">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLogContent" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<label for="Opinion"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalProcessLogContent" /></label>
							<div class="form_input form_input_search">
								<textarea id="Opinion" name="Opinion" ng-model="Opinion"
									ng-required="true" rows="5" autocomplete="off"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div ng-show="IsSeeReply">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="messageReply" /></b></i>
							</h4>
						</div>
						<div>
							<div class="form_group">
								<label for="Reply"
									class="form_label form_label_search form_label_gray">回覆選項</label>
								<div class="form_input form_input_search">									
									<select id="ChooseReply" name="ChooseReply" ng-model="ChooseReply"
										class="form-control"
										ng-options="Reply.ReplyOption as Reply.ReplyOption for Reply in replyList"
										ng-required="true">
										<option value="" selected><s:message
											code="pleaseSelect" />回覆選項</option>
									</select>	
									<h5 class="text-danger"
									ng-show="editForm.ChooseReply.$error.required && editForm.ChooseReply.$dirty">
									<s:message code="pleaseSelect" />
									回覆選項
									</h5>
								</div>	
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="Reply"
									class="form_label form_label_search form_label_gray"><s:message
										code="messageReply" /></label>
								<div class="form_input form_input_search">
									<textarea id="Reply" name="Reply" ng-model="Reply" rows="5"
										autocomplete="off" class="form-control" ng-required="true"></textarea>
								</div>
							</div>
						</div>
					</div>									
				</form>

			</div>


			<div class="page-header">
				<div>
					<div class="clearfix form-actions">
						<div class="text-center">
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null" ng-click="examine(Id,'5')"
								ng-show="messagePassButton">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnPass" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null" ng-click="examine(Id,'10')"
								ng-show="messageBackButton">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBack" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null" ng-click="examine(Id,'2')"
								ng-show="messageUndoButtonButton">
								<i class="fas fa-fw fa-eraser"></i>
								<s:message code="btnReject" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Reply == null || ChooseReply== null"
								ng-click="reply(MessagePostReleaseId)"
								ng-show="messagePostReleaseReplyButton">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnReplySure" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examineMessageReleasePost(MessagePostReleaseId,'pass')"
								ng-show="messageReleasePostPassButton">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnPass" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examineMessageReleasePost(MessagePostReleaseId,'back')"
								ng-show="messageReleasePostBackButton">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBack" />
							</button>
							<button class="btn btn_custom btn_gray" ng-click="back()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>