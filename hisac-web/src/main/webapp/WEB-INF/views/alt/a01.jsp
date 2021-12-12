<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<%-- <script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/alt/a01.js"></script>
 --%>
 <body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_yellow">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-speaker.svg" />
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
									<input type="text" id="QuerySApplyDateTime"
										name="QuerySApplyDateTime" ng-model="QuerySApplyDateTime"
										class="form-control"
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
									<input type="text" id="QueryEApplyDateTime"
										name="QueryEApplyDateTime" ng-model="QueryEApplyDateTime"
										class="form-control"
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
									<option value="1" >1-
										<s:message code="notifyStatus1" /></option>
									<option value="2" >2-
										<s:message code="notifyStatus2" /></option>
									<option value="3" >3-
										<s:message code="notifyStatus3" /></option>
									<option value="4" >4-
										<s:message code="notifyStatus4" /></option>
									<option value="5" >5-
										<s:message code="notifyStatus5" /></option>
									<option value="6" >6-
										<s:message code="notifyStatus6" /></option>
									<option value="7" >7-
										<s:message code="notifyStatus7" /></option>									
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryKeyword"
								class="form_label form_label_search form_label_gray"><s:message
									code="notifyKeyword" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyword" name="QueryKeyword"
									ng-model="QueryKeyword" class="form-control"
									placeholder="<s:message code="notifyKeywordPlaceHolder" />"
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

					<div class="form-group ">
						<div class="clearfix form-actions">
							<div class="text-center">
								<c:if test="${isAdmin || isHisac || isMemberContact }">
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('1');">
										<s:message code="notifyStatus1" />
									<c:if test="${  isMemberContact}">	

									 {{ButtonCount1}}
									</c:if>
									</button>
								</c:if>
								<c:if
									test="${isAdmin || isHisac || isMemberContact || isApplySingAdmin || isHisacNotifySign}">
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('2');">
										<s:message code="notifyStatus2" />
										<c:if test="${  isMemberContact }">	
									{{ButtonCount2}}
									</c:if>
									</button>
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('3');">
										<s:message code="notifyStatus3" />
										<c:if test="${  isApplySingAdmin || isHisacNotifySign}">	
									       {{ButtonCount3}}
									    </c:if>
									</button>

									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('4');">
										<s:message code="notifyStatus4" />
										<c:if test="${  isMemberContact }">	
									        {{ButtonCount4}}
									    </c:if>
									</button>

									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('5');">
										<s:message code="notifyStatus5" />
										<c:if test="${  isApplySingAdmin || isHisacNotifySign}">	
									       {{ButtonCount5}}
									    </c:if>
									</button>
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('6');">
										<s:message code="notifyStatus6" />
									</button>
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('7');">
										<s:message code="notifyStatus7" />										
									</button>									

								</c:if>


							</div>
						</div>

					</div>


				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>
			<div class="col-xs-12 col-md-6 no_padding">
				<a class="btn btn_custom btn_blue pull-right" type="button"
					ng-click="openEdit('edit')"
					ng-show="${actionCreate} && ${isMemberContact}"> <i
					class="fas fa-fw fa-plus-circle"></i> <s:message code="btnCreate" />
				</a>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('postId')"> <s:message
										code="notifyPostId" /> <i ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('contactorUnitName')"> <s:message
										code="notifyContactorUnit" /> <i ng-show="sorttype != 'contactorUnitName'"
									class="fas fa-fw fa-sort text-muted"></i> <i
									ng-show="sorttype == 'contactorUnitName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'contactorUnitName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('eventRemark')"> <s:message
										code="notifyEventRemark" /> <i
									ng-show="sorttype != 'eventRemark'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'eventRemark' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'eventRemark' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('messagePostId')">
									<s:message code="notifySource" /> <i
									ng-show="sorttype != 'messagePostId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'messagePostId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'messagePostId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('applyDateTime')">
									<s:message code="notifyApplyDateTime" /> <i
									ng-show="sorttype != 'applyDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'applyDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'applyDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="notifyStatus" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isTest')"> 是否為演練
							 <i ng-show="sorttype != 'isTest'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isTest' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isTest' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><a href="#" ng-click="editData(item.Id,'view');"> <i
									class="fas fa-file-alt fa-lg fa-pull-left"></i> </a><a href="#" ng-click="click_postid(item);">{{item.PostId}}</a></td>
							<td class="ellipsis" style="max-width: 300px;">{{item.ContactorUnitName}}</td>
							<td class="ellipsis" style="max-width: 300px;">{{item.EventRemark}}</td>							
							<td><span
								ng-if="item.EventSource==2 || item.MessagePostId!='' && item.MessagePostId!=null"><s:message
										code="notifySourceFromMessage" /></span><span
								ng-if="item.EventSource==1 || item.MessagePostId=='' || item.MessagePostId==null"><s:message
										code="notifySourceFromNotification" /></span></td>
							<td>{{item.ApplyDateTime}}</td>
							<td><span ng-if="item.Status == 1"><s:message
										code="notifyStatus1" /></span> <span ng-if="item.Status == 2"><s:message
										code="notifyStatus2" /></span> <span ng-if="item.Status == 31"><s:message
										code="notifyStatus3" /></span> <span ng-if="item.Status == 32"><s:message
										code="notifyStatus3" /></span> <span ng-if="item.Status == 33"><s:message
										code="notifyStatus3" /></span> <span ng-if="item.Status == 4"><s:message
										code="notifyStatus4" /></span> <span ng-if="item.Status == 51"><s:message
										code="notifyStatus5" /></span> <span ng-if="item.Status == 52"><s:message
										code="notifyStatus5" /></span> <span ng-if="item.Status == 53"><s:message
										code="notifyStatus5" /></span> <span ng-if="item.Status == 6"><s:message
										code="notifyStatus6" /></span> <span ng-if="item.Status == 7"><s:message
										code="notifyStatus7" /></span> <span ng-if="item.Status == 8"><s:message
										code="notifyStatus8" /></span> <span ng-if="item.Status == 9"><s:message
										code="notifyStatus9" /></span></td>
							<td><span ng-if="item.IsTest">是</span> 
								<span ng-if="!item.IsTest">否</span></td>
							<td ng-show="${actionUpdate && actionDelete && actionSign}"
								class="text-center"><a class="btn btn-sm btn-primary"
								ng-click="editData(item.Id,'viewUpdate');"
								title='<s:message code="btnEdit" />'
								ng-show="${actionUpdate} && item.IsButtonEdit"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><a
								class="btn btn-sm btn-primary"
								ng-click="editData(item.Id,'handle');"
								title='<s:message code="btnEdit" />'
								ng-show="${actionUpdate}  && item.IsButtonHandle"><i
									class="fas fa-fw fa-edit"></i>處理</a> <a
								class="btn btn-sm btn-primary"
								ng-click="editData(item.Id,'review');"
								title='<s:message code="btnApply" />'
								ng-show="${actionSign} && item.IsButtonReview"><i
									class="fas fa-fw fa-pencil-alt"></i> <s:message code="btnApply" />
							</a>
							<c:if test="${ isHisacNotifySign}">															
							<a
								class="btn btn-sm btn-primary"
								ng-click="alertData(item.Id);"
								title='稽催'
								ng-show="${actionSign} && item.Status==4"><i
									class="fas fa-paper-plane"></i> 稽催(已過{{item.Time}}未處理)
							</a>
							</c:if>
							<a class="btn btn-sm btn-primary"
								ng-click="editData(item.Id,'review_handle');"
								title='<s:message code="btnApply" />'
								ng-show="${actionSign} && item.IsButtonHandleReview"><i
									class="fas fa-fw fa-pencil-alt"></i> <s:message code="btnApply" />
							</a> <a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);"
								title='<s:message code="btnDelete" />'
								ng-show="${actionDelete} && item.IsButtonDelete"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
								<a href="#" class="btn btn-sm btn-info"
								ng-click="examine(item.Id,'undo');"
								title='<s:message code="btnReject" />'
								ng-show="${actionDelete} && item.IsButtonUndo"><i
									class="fas fa-fw fa-eraser"></i> <s:message code="btnReject" /></a>
								<a class="btn btn-sm btn-primary"
								ng-click="buttonToInformation(item.Id);"
								title='<s:message code="btnBuildInformation" />'
								ng-show="${actionSign} && item.IsButtonToImformation"><i
									class="fas fa-fw fa-pencil-alt"></i> <s:message
										code="btnBuildInformation" /> </a> <a
								class="btn btn-sm btn-primary"
								ng-click="buttonToMessage(item.Id);"
								title='<s:message code="btnBuildMessage" />'
								ng-show="${actionSign} && item.IsButtonToMessage"><i
									class="fas fa-fw fa-pencil-alt"></i> <s:message
										code="btnBuildMessage" /> </a></td>
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
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>