<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="${pageContext.request.contextPath}/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inc/i01.js"></script>
<script>
	var globalAcceptAttachmentFormat = '<s:message code="globalAcceptAttachmentFormat" />';
	var globalUploadFileFail = '<s:message code="globalUploadFileFail" />';
</script>
<body class="index-login" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	<section id="main_content">
	
	<div class="container">
		<div class="row">
		
		<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
        </div>
		
		
		<%@ include file="../include/slidebar.jsp"%>
	

	<div id="divQuery" class="col-lg-9">
	    <div class="row">
			<h4 class="form_heading form_heading_fix form_heading_yellow">
				<img
					src="${pageContext.request.contextPath}/resources/img/icon-speaker.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
		</div>
		<div class="row">
			<form name="queryForm">
				<div class="col-xs-12 shadow_board">
					<div>
						<div class="form_group">
							<label for="QuerySPostDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="i01PostDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group" id="datetimepicker1">
									<input type="text" id="QuerySPostDateTime"
										name="QuerySPostDateTime" ng-model="QuerySPostDateTime"
										class="form-control"
										placeholder="<s:message code="i01SPostDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group" id="datetimepicker2">
									<input type="text" id="QueryEPostDateTime"
										name="QueryEPostDateTime" ng-model="QueryEPostDateTime"
										class="form-control"
										placeholder="<s:message code="i01EPostDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<c:if test="1==2">
					<div>
						<div class="form_group">
							<label for="QueryReporterName"
								class="form_label form_label_search form_label_gray"><s:message
									code="i01ReporterName" /></label>
							<div class="form_input form_input_search">
								<select id="QueryReporterName"
									name="QueryReporterName"
									ng-model="QueryReporterName"
									class="form-control col-xs-6"
									ng-options="org.Id as org.Id+'-'+org.Name for org in org2s">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${!isEventHandlingUnitContact}">
					<div>
						<div class="form_group">
							<label for="QueryHandleName"
								class="form_label form_label_search form_label_gray"><s:message
									code="i01HandleName" /></label>
							<div class="form_input form_input_search">
								<select id="QueryHandleName"
									name="QueryHandleName"
									ng-model="QueryHandleName"
									class="form-control col-xs-6"
									ng-options="org.Id as org.Id+'-'+org.Name for org in org3s">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>
					</c:if>
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="i01Status" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">									
									<option value="" selected><s:message code="all" /></option>
									<c:if test="${isAdmin || isHisac || isApplyContact || isMemberContact || isHCERTContentSign || isEventHandlingUnitContact}">
									<option value="1" >1-
										<s:message code="i01Status1" /></option>
									<option value="2" >2-
										<s:message code="i01Status2" /></option>
									<option value="3" >3-
										<s:message code="i01Status3" /></option>
									<option value="4" >4-
										<s:message code="i01Status4" /></option>
									<option value="5" >5-
										<s:message code="i01Status5" /></option>
									</c:if>
								</select>
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
								<c:if test="${isAdmin || isHisac || isApplyContact || isMemberContact || isHCERTContentSign || isEventHandlingUnitContact}">
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('1');">
										<s:message code="i01StatusQueryBtn1" />
										<c:if test="${isAdmin || isHisac || isHCERTContentSign}">
											 (<span ng-bind="ButtonCount1"></span>)
										</c:if>
									</button>
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('2');">
										<s:message code="i01StatusQueryBtn2" />
										<c:if test="${isAdmin || isEventHandlingUnitContact}">
											 (<span ng-bind="ButtonCount2"></span>)
										</c:if>
									</button>
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('3');">
										<s:message code="i01StatusQueryBtn3" />
										<c:if test="${isAdmin || isMemberContact || isHCERTContentSign}">
											 (<span ng-bind="ButtonCount3"></span>)
										</c:if>
									</button>
									<!-- <button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('4');">
										<s:message code="i01StatusQueryBtn4" />
									</button> -->
									<button class="btn btn-primary btn_bottom_fix"
										ng-click="setStatus('5');">
										<s:message code="i01StatusQueryBtn5" />
									</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
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
			<!-- 新增 <div class="col-xs-12 col-md-6 no_padding">
				<c:if
					test="${(isAdmin || isHisac || isHCERTContentSign) && actionCreate}">
					<a class="btn btn_custom btn_blue pull-right" type="button"
						ng-click="openEdit()"> <i class="fas fa-fw fa-plus-circle"></i>
						<s:message code="btnCreate" />
					</a>
				</c:if>
			</div> -->
		</div>
		<div class="row" id = laoding1 style="display:none">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('postId')"> <s:message
										code="i01PostId" /> <i ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('postDateTime')"> <s:message
										code="i01PostDateTime" /> <i
									ng-show="sorttype != 'postDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href=""
								ng-click="setSortName('reporterIdName')"> <s:message
										code="i01ReporterIdName" /> <i
									ng-show="sorttype != 'reporterName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'reporterName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'reporterName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href=""
								ng-click="setSortName('handleIdName')"> <s:message
										code="i01HandleIdName" /> <i
									ng-show="sorttype != 'handleIdName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'handleName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'handleName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('contactorIdName')"> <s:message
										code="i01ContactorIdName" /> <i ng-show="sorttype != 'sourceName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'contactorName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'contactorName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('incidentDiscoveryTime')"> <s:message
										code="i01IncidentDiscoveryTime" /> <i
									ng-show="sorttype != 'incidentDiscoveryTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'incidentDiscoveryTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'incidentDiscoveryTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('finishDateTime')"> <s:message
										code="i01FinishDateTime" /> <i
									ng-show="sorttype != 'finishDateTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'finishDateTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'finishDateTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="i01Status" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-hide="${isHisacBoss}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><!-- item.Status = {{item.Status}}<br>
							item.SubStatus = {{item.SubStatus}}<br>
							isAdmin = {{${isAdmin}}}<br>
							isApplyContact (8.權責單位聯絡人) = {{${isApplyContact}}}<br>
							isMemberContact (10.會員機構聯絡人) = {{${isMemberContact}}}<br>
							isHCERTContentSign (17.H-CERT審核者) = {{${isHCERTContentSign}}}<br>
							isEventHandlingUnitContact (18.事件處理單位聯絡人) = {{${isEventHandlingUnitContact}}}<br> -->
							<a href="" ng-click="review(item.Id, 'view')"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i></a>
								<c:if test="${isAdmin || isHisac}">
									<a href="#my-modal"
										ng-if="item.Status == 1"
										ng-click="edit(item.Id)" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal"
										ng-if="item.Status == 2 || item.Status == 3 || item.Status == 4 || item.Status == 5"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.PostId}}</a>
								</c:if>
								<c:if test="${isMemberContact}">
									<a href="#my-modal" 
										ng-if="item.Status == 3 && item.SubStatus == 31"
										ng-click="review(item.Id, 'messageReview')" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal"
										ng-if="item.Status == 1 || item.Status == 2 || item.SubStatus == 32 || item.Status == 4 || item.Status == 5"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.PostId}}</a>
								</c:if>
								<c:if test="${isEventHandlingUnitContact}">
									<a href="#my-modal"
										ng-if="item.Status == 2 || item.Status == 4"
										ng-click="edit(item.Id)" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal" 
										ng-if="item.Status == 3 && item.SubStatus == 31"
										ng-click="review(item.Id, 'messageReview')" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal"
										ng-if="item.Status == 1 || (item.Status == 3 && item.SubStatus != 31) || item.Status == 5"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.PostId}}</a>
								</c:if>
								<c:if test="${isHCERTContentSign}">
									<a href="#my-modal"
										ng-if="item.Status == 1"
										ng-click="edit(item.Id)" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal" 
										ng-if="item.Status == 3 && item.SubStatus == 32"
										ng-click="review(item.Id, 'messageReview')" data-toggle="modal">{{item.PostId}}</a>
									<a href="#my-modal"
										ng-if="item.Status == 2 || (item.Status == 3 && item.SubStatus != 32) || item.Status == 4 || item.Status == 5"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.PostId}}</a>
								</c:if>
								<c:if test="${isApplyContact}">
									<a href="#my-modal"
										ng-click="review(item.Id, 'view')" data-toggle="modal">{{item.PostId}}</a>
								</c:if></td>
							<td>{{item.PostDateTime}}</td>
							<td>{{item.ReporterIdName}}</td>
							<td>{{item.HandleIdName}}</td>
							<td>{{item.ContactorIdName}}</td>
							<td>{{item.IncidentDiscoveryTime}}</td>
							<td>{{item.FinishDateTime}}</td>
							<td class="text-center"><span
								ng-show="{{item.Status}} == '1'"><s:message
										code="i01StatusOpt1" /></span> <span
								ng-show="{{item.Status}} == '2'"><s:message
										code="i01StatusOpt2" /></span> <span
								ng-show="{{item.Status}} == '3'"><s:message
										code="i01StatusOpt3" /></span> <span
								ng-show="{{item.Status}} == '4'"><s:message
										code="i01StatusOpt4" /></span> <span
								ng-show="{{item.Status}} == '5'"><s:message
										code="i01StatusOpt5" /></span></td>
							<td class="text-center"><a
								class="btn btn-sm btn-primary"
								title='<s:message code="btnEdit" />' ng-click="edit(item.Id);"
								ng-show="item.IsButtonEdit"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnEdit" /> </a> <a href="#"
								class="btn btn-sm btn-info"
								title='<s:message code="btnDelete" />'
								ng-click="deleteData(item.Id);" ng-show="item.IsButtonDelete"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
								<a class="btn btn-sm btn-primary"
								title='<s:message code="btnApply" />'
								ng-click="review(item.Id, 'messageReview')"
								ng-show="item.IsButtonReview"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnApply" /> </a> <a href="#"
								class="btn btn-sm btn-info"
								title='<s:message code="btnReject" />'
								ng-click="examine(item.Id, '5');" ng-show="item.IsButtonUndo"><i
									class="fas fa-fw fa-eraser"></i> <s:message code="btnReject" /></a></td>
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



	<div id="divEdit" class="col-lg-9" ng-show="btnIns || btnUpd"  style="display:none">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnIns">
				<i class="fas fa-fw fa-plus-circle fa-lg"></i><b><s:message
						code="btnCreate" /></b>
			</h4>
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnUpd">
				<i class="fas fa-fw fa-edit fa-lg"></i><b><s:message
						code="btnEdit" /></b>
			</h4>
		</div>
		<div class="row">
			<ul class="nav nav-tabs" id="tabIncidentStep">
				<li class="active"><a data-toggle="tab" href="#tabIncidentStep1"
					ng-click="gotToStep(1)" aria-expanded="true"><s:message
							code="i01IncidentStep1Title" /></a></li>
				<li class=""><a data-toggle="tab" href="#tabIncidentStep2"
					ng-click="gotToStep(2)" aria-expanded="false"><s:message
							code="i01IncidentStep2Title" /></a></li>
				<li class="" ng-show="Status == 2 || Status == 3 || Status == 4 || Status == 5"><a data-toggle="tab" href="#tabIncidentStep3"
					ng-click="gotToStep(3)" aria-expanded="false"><s:message 
							code="i01IncidentStep3Title" /></a></li>
			</ul>
		</div>
		<form name="editForm">
			<div class="tab-content tab_content">
				<div id="tabIncidentStep1" class="tab-pane active"
					ng-show="Status == null || Status == '' || Status == 1 || Status == 2 || Status == 4">
					<div class="row">
						<div class="col-xs-12">
							<div class="alert alert-dismissible alert-warning form_title_bar">
								<h3>
									<b><s:message code="i01IncidentStep1TitleDetail" /></b>
								</h3>
							</div>
							<div>
								<div class="form_group">
									<label for="PostId"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01PostId" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="PostId == '' || PostId == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="PostId != '' && PostId != null">{{PostId}}</span>
								</div>
							</div>
							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="PostDateTime"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01PostDateTime" /></label>
									<div class="form_input form_input_search"
										>
										<div class="input-group">
											<input type="text" id="PostDateTime" name="PostDateTime"
												ng-model="PostDateTime" class="form-control"
												placeholder="<s:message code="i01PostDateTime" />"
												autocomplete="off" ng-required="Status == null || Status == '' || Status == '1'"> 
										</div>
										<h5 class="text-danger"
											ng-show="editForm.PostDateTime.$error.required && editForm.PostDateTime.$dirty">
											<s:message code="pleaseEnter" />
											<s:message code="i01PostDateTime" />
										</h5>
									</div>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01PostDateTime" /></label> <span>{{PostDateTime}}</span>
								</div>
							</div>							
							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="ReporterName"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01ReporterName" /></label>
									<div class="form_input form_input_search">
										<select 
											ng-model="ReporterName" class="form-control col-xs-6"
											ng-options="org2.Id as org2.Id+'-'+org2.Name for org2 in org2s"
											ng-required="Status == null || Status == '' || Status == '1'">
											<option value="" selected><s:message
													code="pleaseSelect" />
												<s:message code="i01ReporterName" /></option>
										</select>
										<h5 class="text-danger"
											ng-show="editForm.ReporterName.$error.required && editForm.ReporterName.$dirty">
											<s:message code="pleaseSelect" />
											<s:message code="i01ReporterName" />
										</h5>
									</div>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01ReporterIdName" /></label> <span>{{ReporterIdName}}</span>
								</div>
							</div>
							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="HandleName"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01HandleName" /></label>
									<div class="form_input form_input_search">
										<select 
											ng-model="HandleName" class="form-control col-xs-6"
											ng-options="org3.Id as org3.Id+'-'+org3.Name for org3 in org3s"
											ng-change="showHandleMember()"
											ng-required="Status == null || Status == '' || Status == '1'">
											<option value="" selected><s:message
													code="pleaseSelect" />
												<s:message code="i01HandleName" /></option>
										</select>
										<h5 class="text-danger"
											ng-show="editForm.HandleName.$error.required && editForm.HandleName.$dirty">
											<s:message code="pleaseSelect" />
											<s:message code="i01HandleName" />
										</h5>
									</div>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01HandleIdName" /></label> <span>{{HandleIdName}}</span>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ContactorTel"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01ContactorIdName" /></label> <span>{{ContactorIdName}}</span>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ContactorTel"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01ContactorTel" /></label> <span>{{ContactorTel}}</span>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ContactorFax"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01ContactorFax" /></label> <span>{{ContactorFax}}</span>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ContactorEmail"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01ContactorEmail" /></label> <span>{{ContactorEmail}}</span>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="Status"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01Status" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="Status == '' || Status == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 1"><s:message
											code="i01Status1" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 2"><s:message
											code="i01Status2" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 3"><s:message
											code="i01Status3" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 4"><s:message
											code="i01Status4" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 5"><s:message
											code="i01Status5" /></span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="tabIncidentStep2" class="tab-pane" ng-show="Status == null || Status == '' || Status == 1 || Status == 2 || Status == 4">
					<div class="row">
						<div class="col-xs-12">
							<div class="alert alert-dismissible alert-warning form_title_bar">
								<h3>
									<b><s:message code="i01IncidentStep2TitleDetail" /></b>
								</h3>
							</div>
						</div>
						<div class="col-xs-12">
							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="IncidentDiscoveryTime"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IncidentDiscoveryTime" /></label>
									<div class="form_input form_input_search">
										<div class="input-group">
											<input type="text" id="IncidentDiscoveryTime" name="IncidentDiscoveryTime"
												ng-model="IncidentDiscoveryTime" class="form-control"
												placeholder="<s:message code="i01IncidentDiscoveryTime" />"
												autocomplete="off" ng-required="true"> 
										</div>
										<h5 class="text-danger"
											ng-show="editForm.IncidentDiscoveryTime.$error.required && editForm.IncidentDiscoveryTime.$dirty">
											<s:message code="pleaseEnter" />
											<s:message code="i01IncidentDiscoveryTime" />
										</h5>
									</div>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01IncidentDiscoveryTime" /></label> <span>{{IncidentDiscoveryTime}}</span>
								</div>
							</div>														

							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="EventType"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01EventType" /></label><label for="EventType1"
										class="input_for_radio_group"> <span
										class="form_input_search"> <input type="radio"
											id="EventType1" name="EventType" ng-model="EventType"
											ng-value="1"> <s:message code="i01EventType1" />
									</span> <!-- Event 1 opt Start --> <span ng-show="EventType==1">(&nbsp;<input
											type="checkbox" id="IsEventType1Opt1" name="IsEventType1Opt1"
											ng-required="EventType==1 && !IsEventType1Opt1 && !IsEventType1Opt2 && !IsEventType1Opt3 && !IsEventType1Opt4 && !IsEventType1Opt5 && !IsEventType1Opt6"
											ng-model="IsEventType1Opt1"><label
											for="IsEventType1Opt1" class="label_for_radio"><s:message
													code="i01IsEventType1Opt1" /></label></span><span ng-show="EventType==1"><input
											type="checkbox" id="IsEventType1Opt2" name="IsEventType1Opt2"
											ng-model="IsEventType1Opt2"><label
											for="IsEventType1Opt2" class="label_for_radio"><s:message
													code="i01IsEventType1Opt2" /></label> </span><span ng-show="EventType==1"><input
											type="checkbox" id="IsEventType1Opt3" name="IsEventType1Opt3"
											ng-model="IsEventType1Opt3"><label
											for="IsEventType1Opt3" class="label_for_radio"><s:message
													code="i01IsEventType1Opt3" /></label> </span><span ng-show="EventType==1"><input
											type="checkbox" id="IsEventType1Opt4" name="IsEventType1Opt4"
											ng-model="IsEventType1Opt4"><label
											for="IsEventType1Opt4" class="label_for_radio"><s:message
													code="i01IsEventType1Opt4" /></label> </span><span ng-show="EventType==1"><input
											type="checkbox" id="IsEventType1Opt5" name="IsEventType1Opt5"
											ng-model="IsEventType1Opt5"><label
											for="IsEventType1Opt5" class="label_for_radio"><s:message
													code="i01IsEventType1Opt5" /></label> </span><span ng-show="EventType==1"><input
											type="checkbox" id="IsEventType1Opt6" name="IsEventType1Opt6"
											ng-model="IsEventType1Opt6"><label
											for="IsEventType1Opt6" class="label_for_radio"><s:message
													code="i01IsEventType1Opt6" /></label> )</span> <!-- Event 1 opt End -->
										<h5 class="text-danger"
											ng-show="EventType==1 && !IsEventType1Opt1 && !IsEventType1Opt2 && !IsEventType1Opt3 && !IsEventType1Opt4 && !IsEventType1Opt5 && !IsEventType1Opt6">
											<s:message code="pleaseSelectMoreThenOne" />
										</h5></label> <label for="EventType2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="radio" id="EventType2" name="EventType" ng-model="EventType"
											ng-value="2"> <s:message code="i01EventType2" /> </span> <!-- Event 2 opt Start --> <span 
											ng-show="EventType==2">(&nbsp;<input
											type="checkbox" id="IsEventType2Opt1" name="IsEventType2Opt1"
											ng-required="EventType==2 && !IsEventType2Opt1 && !IsEventType2Opt2 && !IsEventType2Opt3 && !IsEventType2Opt4 && !IsEventType2Opt5"
											ng-model="IsEventType2Opt1"><label
											for="IsEventType2Opt1" class="label_for_radio"><s:message
													code="i01IsEventType2Opt1" /></label></span><span ng-show="EventType==2"><input
											type="checkbox" id="IsEventType2Opt2" name="IsEventType2Opt2"
											ng-model="IsEventType2Opt2"><label
											for="IsEventType2Opt2" class="label_for_radio"><s:message
													code="i01IsEventType2Opt2" /></label> </span><span ng-show="EventType==2"><input
											type="checkbox" id="IsEventType2Opt3" name="IsEventType2Opt3"
											ng-model="IsEventType2Opt3"><label
											for="IsEventType2Opt3" class="label_for_radio"><s:message
													code="i01IsEventType2Opt3" /></label> </span><span ng-show="EventType==2"><input
											type="checkbox" id="IsEventType2Opt4" name="IsEventType2Opt4"
											ng-model="IsEventType2Opt4"><label
											for="IsEventType2Opt4" class="label_for_radio"><s:message
													code="i01IsEventType2Opt4" /></label> </span><span ng-show="EventType==2"><input
											type="checkbox" id="IsEventType2Opt5" name="IsEventType2Opt5"
											ng-model="IsEventType2Opt5"><label
											for="IsEventType2Opt5" class="label_for_radio"><s:message
													code="i01IsEventType2Opt5" /></label> )</span> <!-- Event 2 opt End -->
										<h5 class="text-danger"
											ng-show="EventType==2 && !IsEventType2Opt1 && !IsEventType2Opt2 && !IsEventType2Opt3 && !IsEventType2Opt4 && !IsEventType2Opt5">
											<s:message code="pleaseSelectMoreThenOne" />
										</h5></label> <label for="EventType3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="radio"
											id="EventType3" name="EventType" ng-model="EventType"
											ng-value="3"> <s:message code="i01EventType3" /></span> <!-- Event 3 opt Start --> <span 
											ng-show="EventType==3">(&nbsp;<input
											type="checkbox" id="IsEventType3Opt1" name="IsEventType3Opt1"
											ng-required="EventType==3 && !IsEventType3Opt1 && !IsEventType3Opt2"
											ng-model="IsEventType3Opt1"><label
											for="IsEventType3Opt1" class="label_for_radio"><s:message
													code="i01IsEventType3Opt1" /></label></span><span ng-show="EventType==3"><input
											type="checkbox" id="IsEventType3Opt2" name="IsEventType3Opt2"
											ng-model="IsEventType3Opt2"><label
											for="IsEventType3Opt2" class="label_for_radio"><s:message
													code="i01IsEventType3Opt2" /></label> )</span> <!-- Event 3 opt End -->
										<h5 class="text-danger"
											ng-show="EventType==3 && !IsEventType3Opt1 && !IsEventType3Opt2">
											<s:message code="pleaseSelectMoreThenOne" />
										</h5></label> <label for="EventType4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="radio"
											id="EventType4" name="EventType" ng-model="EventType"
											ng-value="4"> <s:message code="i01EventType4" />
									</span> <!-- Event 4 opt Start --> <span ng-show="EventType==4">(&nbsp;<input
											type="checkbox" id="IsEventType4Opt1" name="IsEventType4Opt1"
											ng-required="EventType==4 && !IsEventType4Opt1 && !IsEventType4Opt2 && !IsEventType4Opt3 && !IsEventType4Opt4"
											ng-model="IsEventType4Opt1"><label
											for="IsEventType4Opt1" class="label_for_radio"><s:message
													code="i01IsEventType4Opt1" /></label></span><span ng-show="EventType==4"><input
											type="checkbox" id="IsEventType4Opt2" name="IsEventType4Opt2"
											ng-model="IsEventType4Opt2"><label
											for="IsEventType4Opt2" class="label_for_radio"><s:message
													code="i01IsEventType4Opt2" /></label> </span><span ng-show="EventType==4"><input
											type="checkbox" id="IsEventType4Opt3" name="IsEventType4Opt3"
											ng-model="IsEventType4Opt3"><label
											for="IsEventType4Opt3" class="label_for_radio"><s:message
													code="i01IsEventType4Opt3" /></label> </span><span ng-show="EventType==4"><input
											type="checkbox" id="IsEventType4Opt4" name="IsEventType4Opt4"
											ng-model="IsEventType4Opt4"><label
											for="IsEventType4Opt4" class="label_for_radio"><s:message
													code="i01IsEventType4Opt4" /></label> )</span> <!-- Event 4 opt End -->
										<h5 class="text-danger"
											ng-show="EventType==4 && !IsEventType4Opt1 && !IsEventType4Opt2 && !IsEventType4Opt3 && !IsEventType4Opt4">
											<s:message code="pleaseSelectMoreThenOne" />
										</h5></label><label for="EventType5" class="input_for_radio_group"><span
										class="form_input_search"> <input type="radio"
											id="EventType5" name="EventType" ng-model="EventType"
											ng-value="5"> <s:message code="i01EventType5" />
									</span> <!-- Event 5 opt Start --> <span ng-show="EventType==5"><input
											type="text" class="form-control form_input_half"
											id="EventType5Other" name="EventType5Other"
											ng-model="EventType5Other" ng-required="EventType==5"
											placeholder="<s:message code="i01EventType5Other" />" /></span>
										<h5 class="text-danger"
											ng-show="editForm.EventType5Other.$error.required && editForm.EventType5Other.$dirty">
											<s:message code="pleaseEnter" />
											<s:message code="i01EventType5Other" />
										</h5></label>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01EventType" /></label> <label
										class="input_for_radio_group"><span><i
											class="far fa-fw fa-dot-circle" ng-show="EventType==1"></i><i
											class="far fa-fw fa-circle" ng-show="EventType!=1"></i> <s:message
												code="i01EventType1" /> </span> <span ng-show="EventType==1">(&nbsp;
											<i class="far fa-fw fa-check-square"
											ng-show="IsEventType1Opt1"></i><i class="far fa-fw fa-square"
											ng-show="!IsEventType1Opt1"></i> <s:message
												code="i01IsEventType1Opt1" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType1Opt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType1Opt2"></i> <s:message
												code="i01IsEventType1Opt2" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType1Opt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType1Opt3"></i> <s:message
												code="i01IsEventType1Opt3" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType1Opt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType1Opt4"></i> <s:message
												code="i01IsEventType1Opt4" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType1Opt5"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType1Opt5"></i> <s:message
												code="i01IsEventType1Opt5" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType1Opt6"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType1Opt6"></i> <s:message
												code="i01IsEventType1Opt6" /> )
									</span> </label> <label class="input_for_radio_group"><span><i
											class="far fa-fw fa-dot-circle" ng-show="EventType==2"></i><i
											class="far fa-fw fa-circle" ng-show="EventType!=2"></i> <s:message
												code="i01EventType2" /> </span> <span ng-show="EventType==2">(&nbsp;
											<i class="far fa-fw fa-check-square"
											ng-show="IsEventType2Opt1"></i><i class="far fa-fw fa-square"
											ng-show="!IsEventType2Opt1"></i> <s:message
												code="i01IsEventType2Opt1" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType2Opt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType2Opt2"></i> <s:message
												code="i01IsEventType2Opt2" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType2Opt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType2Opt3"></i> <s:message
												code="i01IsEventType2Opt3" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType2Opt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType2Opt4"></i> <s:message
												code="i01IsEventType2Opt4" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType2Opt5"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType2Opt5"></i> <s:message
												code="i01IsEventType2Opt5" /> )
									</span> </label> <label class="input_for_radio_group"><span><i
											class="far fa-fw fa-dot-circle" ng-show="EventType==3"></i><i
											class="far fa-fw fa-circle" ng-show="EventType!=3"></i> <s:message
												code="i01EventType3" /> </span> <span ng-show="EventType==3">(&nbsp;
											<i class="far fa-fw fa-check-square"
											ng-show="IsEventType3Opt1"></i><i class="far fa-fw fa-square"
											ng-show="!IsEventType3Opt1"></i> <s:message
												code="i01IsEventType3Opt1" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType3Opt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType3Opt2"></i> <s:message
												code="i01IsEventType3Opt2" /> )
									</span> </label> <label class="input_for_radio_group"><span><i
											class="far fa-fw fa-dot-circle" ng-show="EventType==4"></i><i
											class="far fa-fw fa-circle" ng-show="EventType!=4"></i> <s:message
												code="i01EventType4" /> </span> <span ng-show="EventType==4">(&nbsp;
											<i class="far fa-fw fa-check-square"
											ng-show="IsEventType4Opt1"></i><i class="far fa-fw fa-square"
											ng-show="!IsEventType4Opt1"></i> <s:message
												code="i01IsEventType4Opt1" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType4Opt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType4Opt2"></i> <s:message
												code="i01IsEventType4Opt2" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType4Opt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType4Opt3"></i> <s:message
												code="i01IsEventType4Opt3" /> <i
											class="far fa-fw fa-check-square" ng-show="IsEventType4Opt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsEventType4Opt4"></i> <s:message
												code="i01IsEventType4Opt4" /> )
									</span> </label> <label class="input_for_radio_group"><span><i
											class="far fa-fw fa-dot-circle" ng-show="EventType==5"></i><i
											class="far fa-fw fa-circle" ng-show="EventType!=5"></i> <s:message
												code="i01EventType5" /> <s:message
												code="i01EventType5Other" /> {{EventType5Other}}</span> </label>
								</div>
							</div>
							<div>
								<div class="form_group"
									ng-show="Status == null || Status == '' || Status == '1'">
									<label for="EventRemark"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01EventRemark" /></label>
									<div class="form_input form_input_search">
										<textarea id="EventRemark" name="EventRemark"
											ng-model="EventRemark" class="form-control"
											placeholder="<s:message code="i01EventRemark" />" rows="5"
											autocomplete="off" ng-required="Status == null || Status == '' || Status == '1'"></textarea>
										<h5 class="text-danger"
											ng-show="editForm.EventRemark.$error.required">
											<s:message code="pleaseEnter" />
											<s:message code="i01EventRemark" />
										</h5>
									</div>
								</div>
								<div class="form_group"
									ng-if="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="i01EventRemark" /></label><span
										class="form-text form_text form_input_search">{{EventRemark}}</span>
								</div>
							</div>							
						</div>
					</div>
				</div>

				<div id="tabIncidentStep3" class="tab-pane"　ng-show="Status == '2' || Status == '3' || Status == '4' || Status == '5'">
					<div class="row">
						<div class="col-xs-12">
							<div class="alert alert-dismissible alert-warning form_title_bar">
								<h3>
									<b><s:message code="i01IncidentStep3TitleDetail" /></b>
								</h3>
							</div>
						</div>
						<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="InformationAmount" style="height:140px"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyDeviceAmount" /></label><span>資訊設備</span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="InformationAmount" name="InformationAmount"
										ng-init="InformationAmount=0" ng-model="InformationAmount"										
										ng-required="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										class="form-control"
										placeholder="資訊設備"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span><s:message
										code="notifyServerAmount" /></span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="ServerAmount"
										name="ServerAmount" ng-init="ServerAmount=0"
										ng-required="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="ServerAmount" class="form-control"
										placeholder="<s:message code="notifyServerAmount" />"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span>其他設備</span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="OtherDeviceAmount"
										name="OtherDeviceAmount" ng-init="OtherDeviceAmount=0"
										ng-required="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="OtherDeviceAmount" class="form-control"
										placeholder="其他設備"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span>其他設備名稱</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="OtherDeviceName"
										name="OtherDeviceName"  
										ng-required="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="OtherDeviceName" class="form-control"
										placeholder="其他設備名稱"
										autocomplete="off">
								</div>
								<span
									class="hidden-xs">;</span> <span>備註：</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="DeviceRemark"
										name="DeviceRemark" 
										ng-required="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="DeviceRemark" class="form-control"
										placeholder="備註"
										autocomplete="off">
								</div>												
							</div>							
						</div>
						<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
							<h5 class="text-danger"
							ng-show="(Status == '2' || Status == '4') && (InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)">
							※「受害設備數量」為必填項目，若無受害設備，請於「備註」欄位補充說明。
							</h5>
							</div>
						</div>
						
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">事件之損害/影響範圍評估
								</label>
								<span> <input type="radio"
								ng-required="(Status == '2' || Status == '4') && (AssessDamage=='' || AssessDamage==null)" 
								id="AssessDamage1" name="AssessDamage" ng-model="AssessDamage" class="ng-pristine ng-untouched ng-valid ng-not-empty" value='損害'><label for="AssessDamage1" class="label_for_radio">損害 </label>
								</span>
								<span> <input type="radio" 
								ng-required="(Status == '2' || Status == '4') && (AssessDamage=='' || AssessDamage==null)" 
								id="AssessDamage2" name="AssessDamage" ng-model="AssessDamage" class="ng-pristine ng-untouched ng-valid ng-not-empty" value='影響'><label for="AssessDamage2" class="label_for_radio">影響 </label>
								</span>
								<span> 說明：</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="AssessDamageRemark" name="AssessDamageRemark"
										ng-model="AssessDamageRemark" class="form-control"
										ng-required="(Status == '2' || Status == '4') && (AssessDamageRemark == '' || AssessDamageRemark == null)" 
										placeholder="說明"
										autocomplete="off"
										>								
								</div>																
							</div>
						</div>
						
						<div>
						<div class="form_group">
							<label class="form_label form_label_search"></label>
							<h5 class="text-danger"
							ng-show="(Status == '2' || Status == '4') && (AssessDamage=='' || AssessDamage==null)">
							<s:message code="pleaseEnter" />
							事件之損害/影響範圍評估
							</h5>	
							<h5 class="text-danger"
							ng-show="(Status == '2' || Status == '4') && (AssessDamageRemark == '' || AssessDamageRemark==null)">
							<s:message code="pleaseEnter" />
							說明
							</h5>
						</div>
						</div>
						
						<div>
								<div class="form_group"
									ng-show="(Status == '2' || Status == '4')">
									<label for="IsOs"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IsOs" /></label> <span> <input type="checkbox"
										id="IsOsOpt1" name="IsOsOpt1"
										ng-required="(Status == '2' || Status == '4') && !IsOsOpt1 && !IsOsOpt2 && !IsOsOpt3"
										ng-model="IsOsOpt1"><label for="IsOsOpt1"
										class="label_for_radio"><s:message 
												code="i01IsOsOpt1" /></label>
									</span><span> <input type="checkbox" id="IsOsOpt2"
										name="IsOsOpt2" ng-model="IsOsOpt2"><label
										for="IsOsOpt2" class="label_for_radio"><s:message
												code="i01IsOsOpt2" /> </label>
									</span><span> <input type="checkbox" id="IsOsOpt3"
										name="IsOsOpt3" ng-model="IsOsOpt3"><label
										for="IsOsOpt3" class="label_for_radio"><s:message
												code="i01IsOsOpt3" /> </label>
									</span> <span ng-show="IsOsOpt3"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IsOsOpt3Other" /> </span>
									<div class="form_input form_input_search_half" ng-show="IsOsOpt3">
										<input type="text" id="IsOsOpt3Other" name="IsOsOpt3Other"
											ng-model="IsOsOpt3Other" class="form-control"
											placeholder="<s:message code="i01IsOsOpt3Other" />"
											autocomplete="off" ng-required="IsOsOpt3">
									</div>
								</div>														
							</div>
							
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
								<h5 class="text-danger"
									ng-show="editForm.IsOsOpt3Other.$error.required && editForm.IsOsOpt3Other.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="i01IsOsOpt3Other" />
								</h5>
								<h5 class="text-danger"
									ng-show="!IsOsOpt1 && !IsOsOpt2 && !IsOsOpt3">
									<s:message code="pleaseSelectMoreThenOne" />
								</h5>
								</div>
								</div>	
							<div>
								<div class="form_group"
									ng-show="(Status == '2' || Status == '4')">
									<label for="IsEventCause"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IsEventCause" /></label> <span> <input
										type="checkbox" id="IsEventCause1Opt1"
										ng-required="(Status == '2' || Status == '4') && !IsEventCause1Opt1 && !IsEventCause1Opt2 && !IsEventCause1Opt3 && !IsEventCause1Opt4 && !IsEventCause1Opt5 && !IsEventCause1Opt6"
										name="IsEventCause1Opt1" ng-model="IsEventCause1Opt1"><label
										for="IsEventCause1Opt1" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt1" /> </label>
									</span> <span> <input type="checkbox" id="IsEventCause1Opt2"
										name="IsEventCause1Opt2" ng-model="IsEventCause1Opt2"><label
										for="IsEventCause1Opt2" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt2" /> </label>
									</span> <span> <input type="checkbox" id="IsEventCause1Opt3"
										name="IsEventCause1Opt3" ng-model="IsEventCause1Opt3"><label
										for="IsEventCause1Opt3" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt3" /> </label>
									</span> <span> <input type="checkbox" id="IsEventCause1Opt4"
										name="IsEventCause1Opt4" ng-model="IsEventCause1Opt4"><label
										for="IsEventCause1Opt4" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt4" /> </label>
									</span> <span> <input type="checkbox" id="IsEventCause1Opt5"
										name="IsEventCause1Opt5" ng-model="IsEventCause1Opt5"><label
										for="IsEventCause1Opt5" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt5" /> </label>
									</span> <span> <input type="checkbox" id="IsEventCause1Opt6"
										name="IsEventCause1Opt6" ng-model="IsEventCause1Opt6"><label
										for="IsEventCause1Opt6" class="label_for_radio"><s:message
												code="i01IsEventCause1Opt6" /> </label>
									</span> <span ng-show="IsEventCause1Opt6"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IsEventCause1Opt6Other" /> </span>
									<div class="form_input form_input_search_half"
										ng-show="IsEventCause1Opt6">
										<input type="text" id="IsEventCause1Opt6Other"
											name="IsEventCause1Opt6Other"
											ng-model="IsEventCause1Opt6Other" class="form-control"
											placeholder="<s:message code="i01IsEventCause1Opt6Other" />"
											autocomplete="off" ng-required="IsEventCause1Opt6">
									</div>
								</div>							
							</div>
						
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
								<h5 class="text-danger"
									ng-show="editForm.IsEventCause1Opt6Other.$error.required && editForm.IsEventCause1Opt6Other.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="i01IsEventCause1Opt6Other" />
								</h5>
								<h5 class="text-danger"
									ng-show="!IsEventCause1Opt1 && !IsEventCause1Opt2 && !IsEventCause1Opt3 && !IsEventCause1Opt4 && !IsEventCause1Opt5 && !IsEventCause1Opt6">
									<s:message code="pleaseSelectMoreThenOne" />
								</h5>
								</div>							
								</div>
						
							<div>
								<div class="form_group">
									<label for="EventEvaluation"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01EventEvaluation" /></label>
									<div class="form_input form_input_search">
										<textarea id="EventEvaluation" name="EventEvaluation"
											ng-model="EventEvaluation" class="form-control"
											placeholder="<s:message code="i01EventEvaluation" />"
											rows="5" autocomplete="off" ng-required="Status == '2' || Status == '4'"></textarea>									
									</div>
								</div>
							</div>
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
										<h5 class="text-danger"
											ng-show="(Status == '2' || Status == '4') && editForm.EventEvaluation.$error.required">
											<s:message code="pleaseEnter" />
											<s:message code="i01EventEvaluation" />
										</h5>
							</div></div>
							<div>
								<div class="form_group">
									<label for="EventProcess"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01EventProcess" /></label>
									<div class="form_input form_input_search">
										<textarea id="EventProcess" name="EventProcess"
											ng-model="EventProcess" class="form-control"
											placeholder="<s:message code="i01EventProcess" />" rows="5"
											autocomplete="off" ng-required="Status == '2' || Status == '4'"></textarea>										
									</div>
								</div>
							</div>
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
										<h5 class="text-danger"
											ng-show="(Status == '2' || Status == '4') && editForm.EventProcess.$error.required">
											<s:message code="pleaseEnter" />
											<s:message code="i01EventProcess" />
										</h5>
							</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsSecuritySetting"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01IsSecuritySetting" /></label>
									<label
										for="IsSecuritySetting1Opt1" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt1" name="IsSecuritySetting1Opt1"
											ng-required="(Status == '2' || Status == '4') && !IsSecuritySetting1Opt1 && !IsSecuritySetting1Opt2 && !IsSecuritySetting1Opt3 && !IsSecuritySetting1Opt4 && !IsSecuritySetting1Opt5 && !IsSecuritySetting1Opt6 && !IsSecuritySetting1Opt7 && !IsSecuritySetting1Opt8 && !IsSecuritySetting1Opt9 && !IsSecuritySetting1Opt10 && !IsSecuritySetting1Opt11 && !IsSecuritySetting1Opt12 && !IsSecuritySetting1Opt13"
											ng-model="IsSecuritySetting1Opt1"> <s:message
												code="i01IsSecuritySetting1Opt1" />
									</span></label>
									<label for="IsSecuritySetting1Opt2"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt2" name="IsSecuritySetting1Opt2"
											ng-model="IsSecuritySetting1Opt2"> <s:message
												code="i01IsSecuritySetting1Opt2" />
									</span></label>
									<label for="IsSecuritySetting1Opt3"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt3" name="IsSecuritySetting1Opt3"
											ng-model="IsSecuritySetting1Opt3"> <s:message
												code="i01IsSecuritySetting1Opt3" />
									</span></label>
									<label for="IsSecuritySetting1Opt4"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt4" name="IsSecuritySetting1Opt4"
											ng-model="IsSecuritySetting1Opt4"> <s:message
												code="i01IsSecuritySetting1Opt4" />
									</span></label>
									<label for="IsSecuritySetting1Opt5"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt5" name="IsSecuritySetting1Opt5"
											ng-model="IsSecuritySetting1Opt5"> <s:message
												code="i01IsSecuritySetting1Opt5" />
									</span></label>
									<label for="IsSecuritySetting1Opt6"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt6" name="IsSecuritySetting1Opt6"
											ng-model="IsSecuritySetting1Opt6"> <s:message
												code="i01IsSecuritySetting1Opt6" />
									</span></label>
									<label for="IsSecuritySetting1Opt7"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt7" name="IsSecuritySetting1Opt7"
											ng-model="IsSecuritySetting1Opt7"> <s:message
												code="i01IsSecuritySetting1Opt7" />
									</span></label>
									<label for="IsSecuritySetting1Opt8"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt8" name="IsSecuritySetting1Opt8"
											ng-model="IsSecuritySetting1Opt8"> <s:message
												code="i01IsSecuritySetting1Opt8" />
									</span></label>
									<label for="IsSecuritySetting1Opt9"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt9" name="IsSecuritySetting1Opt9"
											ng-model="IsSecuritySetting1Opt9"> <s:message
												code="i01IsSecuritySetting1Opt9" />
									</span></label>
									<label for="IsSecuritySetting1Opt10"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt10" name="IsSecuritySetting1Opt10"
											ng-model="IsSecuritySetting1Opt10"> <s:message
												code="i01IsSecuritySetting1Opt10" />
									</span></label>
									<label for="IsSecuritySetting1Opt11"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt11" name="IsSecuritySetting1Opt11"
											ng-model="IsSecuritySetting1Opt11"> <s:message
												code="i01IsSecuritySetting1Opt11" />
									</span></label>
									<label for="IsSecuritySetting1Opt12"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt12" name="IsSecuritySetting1Opt12"
											ng-model="IsSecuritySetting1Opt12"> <s:message
												code="i01IsSecuritySetting1Opt12" />
									</span></label>
									<label for="IsSecuritySetting1Opt13"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsSecuritySetting1Opt13" name="IsSecuritySetting1Opt13"
											ng-model="IsSecuritySetting1Opt13"> <s:message
												code="i01IsSecuritySetting1Opt13" />
									</span>								
									</label>
								</div>
							</div>
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
									<h5 class="text-danger"
										ng-show="(Status == '2' || Status == '4') && !IsSecuritySetting1Opt1 && !IsSecuritySetting1Opt2 && !IsSecuritySetting1Opt3 && !IsSecuritySetting1Opt4 && !IsSecuritySetting1Opt5 && !IsSecuritySetting1Opt6 && !IsSecuritySetting1Opt7 && !IsSecuritySetting1Opt8 && !IsSecuritySetting1Opt9 && !IsSecuritySetting1Opt10 && !IsSecuritySetting1Opt11 && !IsSecuritySetting1Opt12 && !IsSecuritySetting1Opt13">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5>
									</div>
									</div>
							<div>
								<div class="form_group">
									<label for="FinishDoOther"
										class="form_label form_label_search form_label_gray"><s:message
											code="i01FinishDoOther" /></label>
									<div class="form_input form_input_search">
										<textarea id="FinishDoOther" name="FinishDoOther"
											ng-model="FinishDoOther" class="form-control"
											placeholder="<s:message code="i01FinishDoOther" />" rows="5"
											autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="FinishDateTime"
										class="form_label form_label_search form_label_gray"><p style="font-size:10px; color:red; display:inline;">(*必填)</p><s:message
											code="i01FinishDateTime" /></label>
									<div class="form_input form_input_search">
										<div class="input-group">
											<input type="text" id="FinishDateTime" name="FinishDateTime"
												ng-model="FinishDateTime" class="form-control"
												placeholder="<s:message code="i01FinishDateTime" />"
												autocomplete="off"
												ng-required="Status == '2' || Status == '4'">
											
										</div>										
									</div>
								</div>
							</div>
							<div>
							<div class="form_group">
							<label class="form_label form_label_search"></label>
										<h5 class="text-danger"
											ng-show="(Status == '2' || Status == '4') && editForm.FinishDateTime.$error.required && editForm.FinishDateTime.$dirty">
											<s:message code="pleaseEnter" />
											<s:message code="i01FinishDateTime" />
										</h5>
										</div>
										</div>
						</div>
					</div>
				</div>
			</div>

			<div>
				<div class="form_group"></div>
			</div>
			<div class="help-block"></div>
			<div class="row tab_block text-center">
				<button ng-hide="!stepPrev" class="btn btn-primary pull-left"
					ng-click="gotToStep(step - 1)">
					<i class="fas fa-fw fa-step-backward"></i>
					<s:message code="globalStepPrev" />
				</button>
				<button ng-hide="!stepNext" class="btn btn-primary pull-right"
					ng-click="gotToStep(step + 1)">
					<s:message code="globalStepNext" />
					<i class="fas fa-fw fa-step-forward"></i>
				</button>

				<button class="btn btn_custom btn_blue"
					ng-click="createData(false, false)" ng-show="btnIns">
					<i class="fas fa-fw fa-save"></i>
					<s:message code="btnTempSave" />
				</button>
				<button class="btn btn_custom btn_blue"
					ng-click="createData(true, false)" ng-show="btnIns">
					<i class="fas fa-fw fa-save"></i>
					<s:message code="btnTempSaveAndExit" />
				</button>
				<button class="btn btn_custom btn_blue"
					ng-click="!editForm.$valid || createData(true, true)"
					ng-disabled="!editForm.$valid" ng-show="btnIns">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnSubmit" />
				</button>
				
				<button class="btn btn_custom btn_blue"
					ng-click="updateData(false, false)" ng-show="btnUpd">
					<i class="fas fa-fw fa-save"></i>
					<s:message code="btnTempSave" />
				</button>
				<button class="btn btn_custom btn_blue"
					ng-click="updateData(true, false)" ng-show="btnUpd">
					<i class="fas fa-fw fa-save"></i>
					<s:message code="btnTempSaveAndExit" />
				</button>
				<button class="btn btn_custom btn_blue"
					ng-click="!editForm.$valid || updateData(true, true)"
					ng-disabled="!editForm.$valid" ng-show="btnUpd">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnSubmit" />
				</button>
				
				<button class="btn btn_custom btn_gray" type="button"
					ng-click="closeEdit()">
					<i class="fas fa-fw fa-undo"></i>
					<s:message code="btnReturn" />
				</button>
			</div>
		</form>

		<div class="row" ng-show="btnUpd">
			<h4 class="form_heading form_heading_gray">
				<i class="fas fa-fw fa-plus-circle fa-lg"></i><b><s:message
						code="btnUploadAttachment" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div>
					<div class="form-group">
						<label for="FileAttachment"
							class="form_label form_label_search form_label_gray"><s:message
								code="i01Attachment" /></label>
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileAttachment"
							ng-model="FileAttachment" name="FileAttachment"
							ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							ngf-max-size="100MB" ngf-min-height="100">
							<button id="SelectAttachment" class="btn btn-primary">
								<i class="fas fa-fw fa-file fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="i01Attachment" />
							</button>
							<span>{{FileAttachment.name}}</span>
						</div>
					</div>
				</div>
				<div>
					<div class="form_group">
						<label for="AttachmentDescription"
							class="form_label form_label_search form_label_gray"><s:message
								code="globalAttachmentDescription" /></label>
						<div class="form_input form_input_search">
							<textarea type="text" id="AttachmentDescription"
								name="AttachmentDescription" ng-model="AttachmentDescription"
								class="form-control"
								placeholder="<s:message code="globalAttachmentDescription" />"
								rows="5" autocomplete="off"></textarea>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="uploadAttachment()" ng-disabled="FileAttachment==null"
							ng-show="${actionCreate}">
							<i class="fas fa-fw fa-upload"></i>
							<s:message code="btnUploadAttachment" />
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row" ng-show="btnUpd && itemAttachments.length > 0">
			<h4 class="form_heading form_heading_gray">
				<i class="fas fa-fw fa-paperclip fa-lg"></i><b><s:message
						code="globalAttachmentList" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div class="help-block"></div>
				<div>
					<div class="col-xs-12 col-sm-6 col-md-4"
						ng-repeat="item in itemAttachments">
						<div class="thumbnail">
							<div>
								<p>
									<s:message code="globalAttachmentDescription" />
									: {{item.FileDesc}}
								</p>
								<p>
									<s:message code="globalAttachmentName" />
									: <a
										href="./api/i01/attach/download/{{item.IncidentId}}/{{item.Id}}"
										target="_blank">{{item.FileName}}</a>
								</p>
								<p>
									<s:message code="globalAttachmentSize" />
									: {{item.FileSize}}
								</p>
								<p>
									<s:message code="globalHashSHA256" />
									:
								</p>
								<div class="input-group">
									<input type="text" class="form-control"
										value="{{item.FileHash}}"> <span
										class="input-group-addon"
										onclick="$(this).prev().select(); document.execCommand('Copy')"><i
										class="fas fa-fw fa-copy"></i></span>
								</div>
							</div>
							<div>
								<p ng-show="${actionDelete}" class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteAttachmentData(item.Id);"
										ng-show="${actionDelete}">
										<i class="far fa-fw fa-trash-alt"></i>
										<s:message code="btnDelete" />
									</button>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="submit_bar"></div>
			</div>
		</div>
	</div>

</div>
</div>
				
			
		
		
		</section>
	
	 <!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>