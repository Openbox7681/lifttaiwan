<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c01.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>

			<div class="col-xs-12 shadow_board">
				<form name="queryForm">

					<div>
						<div class="form_group">
							<label for="QuerySModifyTime"
								class="form_label form_label_search form_label_gray">修改時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QuerySModifyTime"
										name="QuerySModifyTime" ng-model="QuerySModifyTime"
										class="form-control"
										placeholder="<s:message code="informationExchangeQuerySdate" />">
									<span class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QueryEModifyTime"
										name="QueryEModifyTime" ng-model="QueryEModifyTime"
										class="form-control"
										placeholder="<s:message code="informationExchangeQueryEdate" />"
										autocomplete="off"> <span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray">狀態</label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1">1: 編輯中</option>
									<option value="2">2: 撤銷中</option>
									<option value="3">3: 審核中</option>
									<option value="4">4: 已轉公開資訊</option>
									<option value="5">5: 已轉警訊</option>
									<option value="6">6: 已轉N-ISAC</option>
									<option value="7">7: 已銷案</option>
								</select>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="QuerySourceCode"
								class="form_label form_label_search form_label_gray">資料來源</label>
							<div class="form_input form_input_search">

								<select id="QuerySourceCode" name="QuerySourceCode"
									ng-model="QuerySourceCode" class="form-control"
									ng-options="SourceCode.Code as SourceCode.Code+':'+SourceCode.Name  for  SourceCode in SourceCodes">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="QueryStixTitle"
								class="form_label form_label_search form_label_gray">情資種類</label>
							<div class="form_input form_input_search">
								<select id="QueryStixTitle" name="QueryStixTitle"
									ng-model="QueryStixTitle" class="form-control col-xs-6"
									ng-options="AlertCode.Code as AlertCode.Code+':'+AlertCode.Name  for  AlertCode in alertTypes">
									<option value="" selected><s:message code="all" />
									</option>
								</select>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group">
							<label for="QueryDescription"
								class="form_label form_label_search form_label_gray">關鍵字</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryDescription" name="QueryDescription"
									ng-model="QueryDescription" class="form-control col-xs-6"
									placeholder="關鍵字" />
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
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 shadow_board">
				<div class="form-group">
					</p>
					<div class="clearfix form-actions">
						</p>
						<div class="text-center">
							<c:if test="${isAdmin || isHisac || isHisacIXContent}">
								<button class="btn btn_custom btn-primary"
									ng-click="setstatus('1');">
									編輯中
									<c:if test="${isHisacIXContent}">
									  {{ButtonCount1}}
									</c:if>
								</button>
							</c:if>

							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('2');">
								撤銷中
								<c:if test="${isHisacIXContent}">
									{{ButtonCount2}}
								</c:if>
							</button>



							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('3');">
								審核中
								<c:if test="${isHisacIXContentSign}">
									{{ButtonCount3}}
								</c:if>
							</button>
							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('4');">已轉公開資訊</button>
							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('5');">已轉警訊</button>
							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('6');">已轉N-ISAC</button>
							<button class="btn btn_custom btn-primary"
								ng-click="setstatus('7');">已銷案</button>
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
				<c:if test="${isHisacIXContent && actionCreate}">
					<a class="btn btn_custom btn_blue pull-right" type="button"
						ng-click="openEdit()" ng-show="${actionCreate}"> <i
						class="fas fa-fw fa-plus-circle"></i> <s:message code="btnCreate" />
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
							<th><a href="" ng-click="setSortName('postId')">情資編號<i
									ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('createTime')">建立/轉入時間<i
									ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('modifyTime')">修改時間<i
									ng-show="sorttype != 'modifyTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'modifyTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'modifyTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('sourceCode')">資料來源<i
									ng-show="sorttype != 'sourceCode'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sourceCode' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sourceCode' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('stixTitle')"> 情資種類
									<i ng-show="sorttype != 'stixTitle'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'stixTitle' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'stixTitle' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('category')"> 事件類型
									<i ng-show="sorttype != 'category'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'category' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'category' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th width="25%"><a><span>主旨</span></a></th>

							<th><a href="" ng-click="setSortName('status')"> 狀態 <i
									ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>

							<!-- <th><a><span>接收者</span></a></th> -->
							<!-- <th ><a><span></span></a></th> -->



							<th ng-show="${actionUpdate && actionDelete}" class="func">功能&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">

							<td><a href="#my-modal"
								ng-click="extendView(item.Id, item.IsSeeLog, 'view');"> <i
									class="fas fa-file-alt fa-lg fa-pull-left"></i></a> <a
								href="#my-modal" ng-click="click_postid(item);">
									{{item.PostId}} </a></td>
							<td>{{item.CreateTime }}</td>
							<td>{{item.ModifyTime }}</td>
							<td>{{getSourceName(item.SourceCode)}}</td>
							<!-- <td>{{item.SourceCode}}</td> -->
							<td>{{item.StixTitle }}</td>
							<td>{{getEventTypeName(item.Category) }}</td>
							<td>{{item.IncidentTitle }}</td>
							<td>{{ item.Status==1?"編輯中":"" }} {{ item.Status==2?"撤銷中":""
								}} {{ item.Status==3?"審核中":"" }} {{ item.Status==4?"已轉公開資訊":""
								}} {{ item.Status==5?"已轉警訊":"" }} {{
								item.Status==6?"已轉N-ISAC":"" }} {{ item.Status==7?"已銷案":"" }} {{
								item.Status==8?"編輯中(退回)":"" }}</td>
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn_custom btn-primary"
								ng-click="editData(item.Id);"
								title='<s:message code="btnEdit" />'
								ng-show="${actionUpdate} && item.IsButtonEdit"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br />
								<a href="#" class="btn btn_custom btn-info"
								title='<s:message code="btnDelete" />'
								ng-click="deleteData(item.Id);"
								ng-show="(${actionDelete} && item.IsButtonDelete) || ${isAdmin}"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="extendView(item.Id, item.IsSeeLog, 'review')"
								title='<s:message code="btnApply" />'
								ng-show="${actionDelete} && item.IsButtonReview"><i
									class="fas fa-fw fa-eraser"></i> <s:message code="btnApply" /></a><br />
								<a class="btn btn_custom btn-primary"
								ng-click="examine(item.Id,7);"
								title='<s:message code="btnReject" />'
								ng-show="${actionUpdate} && item.IsButtonUndo"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnReject" />
							</a><br />
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

	<!-- start my-modal divReviewer-->

	<div id="divReview" class="container">
		<div class="row">
			<%-- 
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnIns">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnCreate" /></b>
			</h4>
			<h4 class="form_heading form_heading_fix form_heading_gray"
				ng-show="btnUpd">
				<big><i class="fas fa-fw fa-edit fa-lg"></i></big><b><s:message
						code="btnEdit" /></b>
			</h4> --%>
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>

			<!-- <ul class="nav nav-tabs" id="tabStep">
				<li class="active"><a data-toggle="tab" href="#tabAlertReview" aria-expanded="true">情資單資料</a></li>
				<li><a data-toggle="tab" href="#tabFileReview" aria-expanded="true">情資附件資料</a></li>
			</ul> -->

			<div class="col-xs-12 shadow_board">
				<form name="reviewForm">
					<div class="tab-content tab_content">
						<div id="tabAlertReview" class="tab-pane active">
							<div class="form_group">
								<label for="PostId"
									class="form_label form_label_search form_label_gray">情資編號</label>
								<span>{{ reviewData.PostId }}</span>
							</div>
							<div class="form_group">
								<label for="SourceCode"
									class="form_label form_label_search form_label_gray">發布單位</label>
								<div class="form_input form_input_search">
									<span>{{getSourceName(reviewData.SourceCode)}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="StixTitle"
									class="form_label form_label_search form_label_gray">情報(警訊)種類</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.StixTitle}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="IncidentId"
									class="form_label form_label_search form_label_gray">來源編號</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.IncidentId}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="IncidentTitle"
									class="form_label form_label_search form_label_gray">事件主旨/情報名稱</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.IncidentTitle}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="IncidentDiscoveryTime"
									class="form_label form_label_search form_label_gray">發現時間</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.IncidentDiscoveryTime}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="IncidentReportedTime"
									class="form_label form_label_search form_label_gray">發送時間</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.IncidentReportedTime}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="IncidentClosedTime"
									class="form_label form_label_search form_label_gray">解決時間</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.IncidentClosedTime}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="Description"
									class="form_label form_label_search form_label_gray">內容說明/事件描述</label>
								<div class="form_input form_input_search">
									<span>
										<div ng-bind-html="reviewData.Description | trustHtml"></div>
									</span>
								</div>
							</div>
							<div class="form_group">
								<label for="Category"
									class="form_label form_label_search form_label_gray">事件類型</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.CategoryName}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="ReporterName"
									class="form_label form_label_search form_label_gray">發布單位</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.ReporterName}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="ResponderPartyName"
									class="form_label form_label_search form_label_gray">聯絡資訊
									(姓名)</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.ResponderPartyName}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="ResponderContactNumbers"
									class="form_label form_label_search form_label_gray">聯絡資訊
									(電話)</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.ResponderContactNumbers}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="ResponderElectronicAddressIdentifiers"
									class="form_label form_label_search form_label_gray">聯絡資訊
									(電子郵件)</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.ResponderElectronicAddressIdentifiers}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="ImpactQualification"
									class="form_label form_label_search form_label_gray">影響等級</label>
								<div class="form_input form_input_search">
									<span ng-if="reviewData.ImpactQualification==1">低(Low)</span> <span
										ng-if="reviewData.ImpactQualification==2">中(Medium)</span> <span
										ng-if="reviewData.ImpactQualification==3">高(High)</span> <span
										ng-if="reviewData.ImpactQualification==4">嚴重(Critical)</span>
								</div>
							</div>
							<div class="form_group">
								<label for="CoaDescription"
									class="form_label form_label_search form_label_gray">建議措施/解決辦法</label>
								<div class="form_input form_input_search">
									<span>
										<div ng-bind-html="reviewData.CoaDescription | trustHtml"></div>
									</span>
								</div>
							</div>
							<div class="form_group">
								<label for="Confidence"
									class="form_label form_label_search form_label_gray">保密程度</label>
								<div class="form_input form_input_search">
									<span>{{reviewData.Confidence}}</span>
								</div>
							</div>
							<div class="form_group">
								<label for="Confidence"
									class="form_label form_label_search form_label_gray">參考資料</label>
								<div class="form_input form_input_search">
									<span>
										<div ng-bind-html="reviewData.Reference | trustHtml"></div>
									</span>
								</div>
							</div>
							<div class="form_group">
								<label for="LeveragedDescription"
									class="form_label form_label_search form_label_gray">手法研判</label>
								<div class="form_input form_input_search">
									<span>
										<div ng-bind-html="reviewData.LeveragedDescription | trustHtml"></div>
									</span>
								</div>
							</div>
							<div class="form_group">
								<label for="AffectedSoftwareDescription"
									class="form_label form_label_search form_label_gray">影響平台</label>
								<div class="form_input form_input_search">
									<span>
										<div ng-bind-html="reviewData.AffectedSoftwareDescription | trustHtml"></div>
									</span>
								</div>
							</div>


							<div class="form_group" ng-show="reviewData.HasXml">
								<label for="SourceContentXml"
									class="form_label form_label_search form_label_gray">原始XML檔案</label>
								<div class="form_input form_input_search">
									<span><a
										href="./api/c01/contentXml/download/{{reviewData.IncidentId}}/{{reviewData.Id}}">{{reviewData.IncidentId}}</a></span>
									<!-- <span>{{reviewData}}</span> -->
								</div>
							</div>

							<!-- <div ng-if="MessagePostAttachData.length > 0"> -->

							<div>
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
											<tr ng-repeat="item in reviewItem">

												<td ng-if="item.FileSize != null"
													style="word-wrap: break-word; word-break: break-all; white-space: normal;">
													<a
													href="./api/c01/attach/download/{{item.InformationExchangeId}}/{{item.Id}}">{{item.Id}}-{{item.FileName}}</a>
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
						</div>





					</div>

					<div>


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
										<tr ng-repeat="item in reviewData.InformationExchangeLog">
											<td><span ng-if="item.PreStatus==1">新增情資</span> <span
													ng-if="item.PreStatus==2">情資撤銷</span> <span
													ng-if="item.PreStatus==3">情資審核</span> <span
													ng-if="item.PreStatus==4">情資轉公開訊息</span><span
													ng-if="item.PreStatus==5">情資轉警訊</span><span
													ng-if="item.PreStatus==6">情資轉NISAC</span><span
													ng-if="item.PreStatus==7">情資已銷案</span><span
													ng-if="item.PreStatus==8">情資已退回</span> <span><i
														class="fas fa-fw fa-arrow-right"></i></span> <span
													ng-if="item.Status==2">情資撤銷</span> <span
													ng-if="item.Status==3">情資審核</span> <span
													ng-if="item.Status==4">情資轉公開訊息</span> <span
													ng-if="item.Status==5">情資轉警訊</span> <span
													ng-if="item.Status==6">情資轉NISAC</span> <span
													ng-if="item.Status==7">情資已銷案</span><span
													ng-if="item.Status==8">情資已退回</span></td>
												<td><span ng-if="item.Status==1">新增情資
															</span> <span ng-if="item.Status==2">情資撤銷
															</span> <span ng-if="item.Status==3">情資審核
															</span> <span ng-if="item.Status==4">情資轉公開訊息
															</span> <span ng-if="item.Status==5">情資轉警訊
															</span> <span ng-if="item.Status==6">情資轉NISAC
															</span> <span ng-if="item.Status==7">情資已銷案
															</span> <span ng-if="item.Status==8">情資已退回
															</span> </td>
												<td>{{item.CreateName}}</td>
												<td>{{item.CreateTime}}</td>
												<td>{{item.Opinion}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div ng-show="Review">
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
						<div class="submit_bar">
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examine(reviewData.Id,2)" ng-show="Review">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnReject" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examine(reviewData.Id,4)" ng-show="Review">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBuildPub" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examine(reviewData.Id,5)" ng-show="Review">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnBuildMessage" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examine(reviewData.Id,6)" ng-show="Review">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBuildNISAC" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="Opinion == null"
								ng-click="examine(reviewData.Id,8)" ng-show="Review">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnBack" />
							</button>
							<button class="btn btn_custom btn_gray" type="button"
								ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>


	<div id="divEdit" class="container" ng-show="btnIns || btnUpd">

		<!-- <div class="row">
		</div> -->

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

			<ul class="nav nav-tabs" id="tabStep">
				<li class="active"><a data-toggle="tab" href="#tabAlert"
					aria-expanded="true">情資單資料</a></li>
				<li><a data-toggle="tab" href="#tabFile" aria-expanded="true">情資附件資料</a></li>
			</ul>


			<div class="col-xs-12 shadow_board">
				<form name="editForm">
					<div class="tab-content tab_content">

						<div id="tabAlert" class="tab-pane active">

							<div>
								<div class="form_group">
									<label for="PostId"
										class="form_label form_label_search form_label_gray">情資編號</label>
									<span class="form-text form_text form_input_search text-danger"
										ng-if="PostId=='' || PostId==null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="PostId!='' && PostId!=null">{{ PostId }}</span>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="ReporterName"
										class="form_label form_label_search form_label_gray">發布單位</label>
									<div class="form_input form_input_search">
										<span>{{getSourceName(SourceCode)}}</span>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="StixTitle"
										class="form_label form_label_search form_label_gray">情報種類</label>
									<div class="form_input form_input_search">
										<select id="StixTitle" name="StixTitle" ng-model="StixTitle"
											class="form-control" ng-change="UpdateAlertCode()"
											ng-options="AlertCode.Code as AlertCode.Code+':'+AlertCode.Name  for  AlertCode in alertTypes"
											ng-required="true">
											<option value="" selected>--請選擇情報種類--</option>
											<div class="validation-msg"
												ng-show="editForm.StixTitle.$error.required">請選擇情報種類</div>
										</select>
										<h5 class="text-danger"
											ng-show="editForm.StixTitle.$error.required && editForm.StixTitle.$dirty">
											<s:message code="pleaseEnter" />
											情報種類
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="StixTitle!='' && StixTitle!=null"
								ng-hide="StixTitle=='OTH'">
								<div class="form_group">
									<label for="Category"
										class="form_label form_label_search form_label_gray">事件類型</label>
									<div class="form_input form_input_search">
										<select id="Category" name="Category" ng-model="Category"
											class="form-control"
											ng-options="EventCode.Code as EventCode.Code+':'+EventCode.Name  for  EventCode in eventTypes"
											ng-required="StixTitle!='OTH'">
											<option value="" selected>--請選擇事件類型--</option>
											<div class="validation-msg"
												ng-show="editForm.Category.$error.required">請選擇事件類型</div>
										</select>
										<h5 class="text-danger"
											ng-show="editForm.Category.$error.required && editForm.Category.$dirty">
											<s:message code="pleaseEnter" />
											事件類型
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IncidentTitle"
										class="form_label form_label_search form_label_gray">事件主旨/情報名稱</label>
									<div class="form_input form_input_search">
										<input type="text" id="IncidentTitle" name="IncidentTitle"
											ng-model="IncidentTitle" class="form-control col-xs-6"
											placeholder="事件主旨/情報名稱" />
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="Description"
										class="form_label form_label_search form_label_gray">內容說明/事件描述</label>
									<div class="form_input form_input_search">
										<textarea id="Description" name="Description"
											ng-model="Description" class="form-control"
											placeholder="內容說明/事件描述" rows="5" autocomplete="off"></textarea>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="IncidentDiscoveryTime"
										class="form_label form_label_search form_label_gray">發現時間
									</label>
									<div class="form_input form_input form_input_search">
										<div class="input-group">
											<input type="text" id="IncidentDiscoveryTime"
												name="IncidentDiscoveryTime"
												ng-model="IncidentDiscoveryTime" class="form-control"
												placeholder="發現時間" autocomplete="off"> <span
												class="input-group-addon"> <i
												class="fas fa-calendar-alt"></i>
											</span>
											<h5 class="text-danger"
												ng-show="editForm.IncidentDiscoveryTime.$error.required && editForm.IncidentDiscoveryTime.$dirty">
												<s:message code="pleaseEnter" />
												發現時間
											</h5>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for=IncidentReportedTime
										class="form_label form_label_search form_label_gray">發送時間</label>
									<div class="form_input form_input form_input_search">
										<div class="input-group">
											<input type="text" id="IncidentReportedTime"
												name="IncidentReportedTime" ng-model="IncidentReportedTime"
												class="form-control" placeholder="發送時間" autocomplete="off">
											<span class="input-group-addon"> <i
												class="fas fa-calendar-alt"></i>
											</span>
											<h5 class="text-danger"
												ng-show="editForm.IncidentReportedTime.$error.required && editForm.IncidentReportedTime.$dirty">
												<s:message code="pleaseEnter" />
												發送時間
											</h5>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for=IncidentClosedTime
										class="form_label form_label_search form_label_gray">解決時間</label>
									<div class="form_input form_input form_input_search">
										<div class="input-group">
											<input type="text" id="IncidentClosedTime"
												name="IncidentClosedTime" ng-model="IncidentClosedTime"
												class="form-control" placeholder="解決時間" autocomplete="off">
											<span class="input-group-addon"> <i
												class="fas fa-calendar-alt"></i>
											</span>
											<h5 class="text-danger"
												ng-show="editForm.IncidentClosedTime.$error.required && editForm.IncidentClosedTime.$dirty">
												<s:message code="pleaseEnter" />
												解決時間
											</h5>
										</div>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="ReporterNameUrl"
										class="form_label form_label_search form_label_gray">發布單位連結</label>
									<div class="form_input form_input_search">
										<input type="text" id="ReporterNameUrl" name="ReporterNameUrl"
											ng-model="ReporterNameUrl" class="form-control col-xs-6"
											ng-pattern="/^(?:(((H|h)(T|t)(T|t)(P|p)((S|s)?))|((H|h)(T|t)(T|t)(P|p))):\/\/)?((?:[a-zA-Z0-9.\-]+\.)+(?:[a-zA-Z0-9]))((?:/[\w+=%&.~\-]*)*)\??([\w+=%&.~\/-]*)$/"
											placeholder="發布單位連結" />
										<h5 class="text-danger"
											ng-show="!editForm.ReporterNameUrl.$error.required && editForm.ReporterNameUrl.$invalid && editForm.ReporterNameUrl.$dirty">
											<s:message code="c01ReporterNameUrlFormat" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResponderPartyName"
										class="form_label form_label_search form_label_gray">聯絡資訊
										(姓名)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResponderPartyName"
											name="ResponderPartyName" ng-model="ResponderPartyName"
											class="form-control col-xs-6" ng-maxlength="64"
											placeholder="聯絡資訊 (姓名)" />
										<h5 class="text-danger"
											ng-show="!editForm.ResponderPartyName.$error.required && editForm.ResponderPartyName.$invalid && editForm.ResponderPartyName.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResponderContactNumbers"
										class="form_label form_label_search form_label_gray">聯絡資訊
										(電話)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResponderContactNumbers"
											name="ResponderContactNumbers"
											ng-model="ResponderContactNumbers"
											class="form-control col-xs-6" ng-maxlength="64"
											placeholder="聯絡資訊 (電話)" />
										<h5 class="text-danger"
											ng-show="!editForm.ResponderContactNumbers.$error.required && editForm.ResponderContactNumbers.$invalid && editForm.ResponderContactNumbers.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResponderElectronicAddressIdentifiers"
										class="form_label form_label_search form_label_gray">聯絡資訊
										(電子郵件)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResponderElectronicAddressIdentifiers"
											name="ResponderElectronicAddressIdentifiers"
											ng-model="ResponderElectronicAddressIdentifiers"
											class="form-control col-xs-6" ng-maxlength="128"
											ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i"
											placeholder="聯絡資訊 (電子郵件)" />
										<h5 class="text-danger"
											ng-show="!editForm.ResponderElectronicAddressIdentifiers.$error.required && editForm.ResponderElectronicAddressIdentifiers.$invalid && editForm.ResponderElectronicAddressIdentifiers.$dirty">
											<s:message code="memberEmailFormat" />
										</h5>
									</div>
								</div>
							</div>


							<div>
								<div class="form_group">
									<label for="ImpactQualification"
										class="form_label form_label_search form_label_gray">
										影響等級 </label><span> <input type="radio" id="ImpactQualification4"
										name="ImpactQualification" ng-model="ImpactQualification"
										ng-value="4"> <label for="ImpactQualification4"
										class="label_for_radio">嚴重(Critical)</label>
									</span> <span> <input type="radio"
										id="ImpactQualification3" name="ImpactQualification"
										ng-model="ImpactQualification" ng-value="3"> <label
										for="ImpactQualification3" class="label_for_radio">高(High)</label>
									</span> <span> <input type="radio" id="ImpactQualification2"
										name="ImpactQualification" ng-model="ImpactQualification"
										ng-value="2"> <label for="ImpactQualification2"
										class="label_for_radio">中(Medium)</label>
									</span> <span> <input type="radio" id="ImpactQualification1"
										name="ImpactQualification" ng-model="ImpactQualification"
										ng-value="1"> <label for="ImpactQualification1"
										class="label_for_radio">低(Low)</label>
									</span>
								</div>
							</div>


							<div>
								<div class="form_group">
									<label for="CoaDescription"
										class="form_label form_label_search form_label_gray">建議措施/解決辦法</label>
									<div class="form_input form_input_search">
										<textarea id="CoaDescription" name="CoaDescription"
											ng-model="CoaDescription" class="form-control"
											placeholder="建議措施/解決辦法" rows="5" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="Confidence"
										class="form_label form_label_search form_label_gray">保密程度</label>
									<div class="form_input form_input_search">
										<input type="text" id="Confidence" name="Confidence"
											ng-model="Confidence" class="form-control col-xs-6"
											placeholder="保密程度" />
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="Reference"
										class="form_label form_label_search form_label_gray">參考資料</label>
									<div class="form_input form_input_search">
										<textarea id="Reference" name="Reference"
											ng-model="Reference" class="form-control"
											placeholder="參考資料" rows="5" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ObservableIpAddress"
										class="form_label form_label_search form_label_gray">受害IP/port/protocol</label>
									<div class="form_input form_input_search">
										<input type="text" id="ObservableIpAddress"
											name="ObservableIpAddress" ng-model="ObservableIpAddress"
											class="form-control col-xs-6" ng-maxlength="64"											
											placeholder="受害IP/port/protocol" />
										<h5 class="text-danger"
											ng-show="editForm.ObservableIpAddress.$invalid && editForm.ObservableIpAddress.$dirty">
											受害IP/port/protocol
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="SocketIpAddress"
										class="form_label form_label_search form_label_gray">C&C資訊(IP)</label>
									<div class="form_input form_input_search">
										<input type="text" id="SocketIpAddress" name="SocketIpAddress"
											ng-model="SocketIpAddress" class="form-control col-xs-6"
											ng-maxlength="64"
											ng-pattern="/^(((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\,)|((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))*$/"
											placeholder="C&C資訊(IP)" />
										<h5 class="text-danger"
											ng-show="editForm.SocketIpAddress.$invalid && editForm.SocketIpAddress.$dirty">
											C&C資訊(IP)
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="SocketPort"
										class="form_label form_label_search form_label_gray">C&C資訊(通訊埠)</label>
									<div class="form_input form_input_search">
										<input type="text" id="SocketPort" name="SocketPort"
											ng-model="SocketPort" class="form-control col-xs-6"
											ng-maxlength="64" ng-pattern="/^\-?\d*$/"
											placeholder="C&C資訊(通訊埠)" />
										<h5 class="text-danger"
											ng-show="editForm.SocketPort.$invalid || editForm.SocketPort.$error.maxlength && editForm.SocketPort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="SocketProtocol"
										class="form_label form_label_search form_label_gray">C&C資訊(通訊埠協定)</label>
									<div class="form_input form_input_search">
										<input type="text" id="SocketProtocol" name="SocketProtocol"
											ng-model="SocketProtocol" class="form-control col-xs-6"
											ng-maxlength="64" placeholder="C&C資訊(通訊埠協定)" />
										<h5 class="text-danger"
											ng-show="!editForm.SocketProtocol.$error.required && editForm.SocketProtocol.$invalid && editForm.SocketProtocol.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="CustomIpAddress"
										class="form_label form_label_search form_label_gray">中繼站資訊(IP)</label>
									<div class="form_input form_input_search">
										<input type="text" id="CustomIpAddress" name="CustomIpAddress"
											ng-model="CustomIpAddress" class="form-control col-xs-6"
											ng-maxlength="64"
											ng-pattern="/^(((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\,)|((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))*$/"
											placeholder="中繼站資訊(IP)" />
										<h5 class="text-danger"
											ng-show="editForm.CustomIpAddress.$invalid && editForm.CustomIpAddress.$dirty">
											中繼站資訊(IP)
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="CustomPort"
										class="form_label form_label_search form_label_gray">中繼站資訊(通訊埠)</label>
									<div class="form_input form_input_search">
										<input type="text" id="CustomPort" name="CustomPort"
											ng-model="CustomPort" class="form-control col-xs-6"
											ng-maxlength="64" ng-pattern="/^\-?\d*$/"
											placeholder="中繼站資訊(通訊埠)" />
										<h5 class="text-danger"
											ng-show="editForm.CustomPort.$invalid || editForm.CustomPort.$error.maxlength && editForm.CustomPort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="CustomProtocol"
										class="form_label form_label_search form_label_gray">中繼站資訊(通訊埠協定)</label>
									<div class="form_input form_input_search">
										<input type="text" id="CustomProtocol" name="CustomProtocol"
											ng-model="CustomProtocol" class="form-control col-xs-6"
											ng-maxlength="64" placeholder="中繼站資訊(通訊埠協定)" />
										<h5 class="text-danger"
											ng-show="!editForm.CustomProtocol.$error.required && editForm.CustomProtocol.$invalid && editForm.CustomProtocol.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="DestinationIpAddress"
										class="form_label form_label_search form_label_gray">目的端資訊(IP)</label>
									<div class="form_input form_input_search">
										<input type="text" id="DestinationIpAddress"
											name="DestinationIpAddress" ng-model="DestinationIpAddress"
											class="form-control col-xs-6" ng-maxlength="64"
											ng-pattern="/^(((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\,)|((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))*$/"
											placeholder="目的端資訊(IP)" />
										<h5 class="text-danger"
											ng-show="editForm.DestinationIpAddress.$invalid && editForm.DestinationIpAddress.$dirty">
											目的端資訊(IP)
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="DestinationPort"
										class="form_label form_label_search form_label_gray">目的端資訊(通訊埠)</label>
									<div class="form_input form_input_search">
										<input type="text" id="DestinationPort" name="DestinationPort"
											ng-model="DestinationPort" class="form-control col-xs-6"
											ng-maxlength="64" ng-pattern="/^\-?\d*$/"
											placeholder="目的端資訊(通訊埠)" />
										<h5 class="text-danger"
											ng-show="editForm.DestinationPort.$invalid || editForm.DestinationPort.$error.maxlength && editForm.DestinationPort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="DestinationProtocol"
										class="form_label form_label_search form_label_gray">目的端資訊(通訊埠協定)</label>
									<div class="form_input form_input_search">
										<input type="text" id="DestinationProtocol"
											name="DestinationProtocol" ng-model="DestinationProtocol"
											class="form-control col-xs-6" ng-maxlength="64"
											placeholder="目的端資訊(通訊埠協定)" />
										<h5 class="text-danger"
											ng-show="!editForm.DestinationProtocol.$error.required && editForm.DestinationProtocol.$invalid && editForm.DestinationProtocol.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="LeveragedDescription"
										class="form_label form_label_search form_label_gray">手法研判</label>
									<div class="form_input form_input_search">
										<textarea id="LeveragedDescription"
											name="LeveragedDescription" ng-model="LeveragedDescription"
											class="form-control" placeholder="手法研判" rows="5"
											autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="AffectedSoftwareDescription"
										class="form_label form_label_search form_label_gray">影響平台</label>
									<div class="form_input form_input_search">
										<textarea id="AffectedSoftwareDescription"
											name="AffectedSoftwareDescription"
											ng-model="AffectedSoftwareDescription" class="form-control"
											placeholder="影響平台" rows="5" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResourcesSourceIpAddress"
										class="form_label form_label_search form_label_gray">來源IP</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResourcesSourceIpAddress"
											name="ResourcesSourceIpAddress"
											ng-model="ResourcesSourceIpAddress"
											class="form-control col-xs-6" ng-maxlength="64"
											ng-pattern="/^(((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\,)|((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))*$/"
											placeholder="來源IP" />
										<h5 class="text-danger"
											ng-show="editForm.ResourcesSourceIpAddress.$invalid && editForm.ResourcesSourceIpAddress.$dirty">
											來源IP
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResourcesSourcePort"
										class="form_label form_label_search form_label_gray">來源PORT/protocol</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResourcesSourcePort"
											name="ResourcesSourcePort" ng-model="ResourcesSourcePort"
											class="form-control col-xs-6" ng-maxlength="64"
											placeholder="來源PORT/protocol" />
										<h5 class="text-danger"
											ng-show="editForm.ResourcesSourcePort.$invalid || editForm.ResourcesSourcePort.$error.maxlength && editForm.ResourcesSourcePort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResourcesDestinationPort"
										class="form_label form_label_search form_label_gray">目標資訊(通訊埠)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResourcesDestinationPort"
											name="ResourcesDestinationPort"
											ng-model="ResourcesDestinationPort"
											class="form-control col-xs-6" ng-maxlength="64"
											ng-pattern="/^\-?\d*$/" placeholder="目標資訊(通訊埠)" />
										<h5 class="text-danger"
											ng-show="editForm.ResourcesDestinationPort.$invalid || editForm.ResourcesDestinationPort.$error.maxlength && editForm.ResourcesDestinationPort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResourcesDestinationProtocol"
										class="form_label form_label_search form_label_gray">目標資訊(通訊埠協定)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResourcesDestinationProtocol"
											name="ResourcesDestinationProtocol"
											ng-model="ResourcesDestinationProtocol"
											class="form-control col-xs-6" ng-maxlength="64"
											placeholder="目標資訊(通訊埠協定)" />
										<h5 class="text-danger"
											ng-show="!editForm.ResourcesDestinationProtocol.$error.required && editForm.ResourcesDestinationProtocol.$invalid && editForm.ResourcesDestinationProtocol.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ResourcesDestination"
										class="form_label form_label_search form_label_gray">目標對象</label>
									<div class="form_input form_input_search">
										<input type="text" id="ResourcesDestination"
											name="ResourcesDestination" ng-model="ResourcesDestination"
											class="form-control col-xs-6" ng-maxlength="128"
											placeholder="目標對象" />
										<h5 class="text-danger"
											ng-show="!editForm.ResourcesDestination.$error.required && editForm.ResourcesDestination.$invalid && editForm.ResourcesDestination.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ScanEngine"
										class="form_label form_label_search form_label_gray">掃描資訊(掃描引擎)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ScanEngine" name="ScanEngine"
											ng-model="ScanEngine" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="掃描資訊(掃描引擎)" />
										<h5 class="text-danger"
											ng-show="!editForm.ScanEngine.$error.required && editForm.ScanEngine.$invalid && editForm.ScanEngine.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ScanVersion"
										class="form_label form_label_search form_label_gray">掃描資訊(掃描版本)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ScanVersion" name="ScanVersion"
											ng-model="ScanVersion" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="掃描資訊(掃描版本)" />
										<h5 class="text-danger"
											ng-show="!editForm.ScanVersion.$error.required && editForm.ScanVersion.$invalid && editForm.ScanVersion.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="ScanResult"
										class="form_label form_label_search form_label_gray">掃描資訊(掃描結果)</label>
									<div class="form_input form_input_search">
										<input type="text" id="ScanResult" name="ScanResult"
											ng-model="ScanResult" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="掃描資訊(掃描結果)" />
										<h5 class="text-danger"
											ng-show="!editForm.ScanResult.$error.required && editForm.ScanResult.$invalid && editForm.ScanResult.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="RelatedIncidentId"
										class="form_label form_label_search form_label_gray">來源編號</label>
									<div class="form_input form_input_search">
										<input type="text" id="RelatedIncidentId"
											name="RelatedIncidentId" ng-model="RelatedIncidentId"
											class="form-control col-xs-6" ng-maxlength="128"
											placeholder="來源編號" />
										<h5 class="text-danger"
											ng-show="!editForm.RelatedIncidentId.$error.required && editForm.RelatedIncidentId.$invalid && editForm.RelatedIncidentId.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="RelatedIncidentTimestamp"
										class="form_label form_label_search form_label_gray">來源時間</label>
									<div class="form_input form_input form_input_search">
										<div class="input-group">
											<input type="text" id="RelatedIncidentTimestamp"
												name="RelatedIncidentTimestamp"
												ng-model="RelatedIncidentTimestamp" class="form-control"
												placeholder="來源時間" autocomplete="off"> <span
												class="input-group-addon"> <i
												class="fas fa-calendar-alt"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
							<!--<div>
								<div class="form_group">
									<label for="NewsManagementGroupId"
										class="form_label form_label_search form_label_gray">最新消息類別</label>
									<div class="form_input form_input_search">
										<input type="text" id="NewsManagementGroupId"
											name="NewsManagementGroupId" ng-model="NewsManagementGroupId"
											class="form-control col-xs-6" placeholder="最新消息類別" />
									</div>
								</div>
							</div>
						-->

							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiSourceRecord"
										class="form_label form_label_search form_label_gray">原始紀錄</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiSourceRecord" name="NhiSourceRecord"
											ng-model="NhiSourceRecord" class="form-control col-xs-6"
											placeholder="原始紀錄" />
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiImpact"
										class="form_label form_label_search form_label_gray">可能影響</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiImpact" name="NhiImpact"
											ng-model="NhiImpact" class="form-control col-xs-6"
											placeholder="可能影響" />
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiRiskGrade"
										class="form_label form_label_search form_label_gray">風險等級</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiRiskGrade" name="NhiRiskGrade"
											ng-model="NhiRiskGrade" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="風險等級" />
										<h5 class="text-danger"
											ng-show="!editForm.NhiRiskGrade.$error.required && editForm.NhiRiskGrade.$invalid && editForm.NhiRiskGrade.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiRiskType"
										class="form_label form_label_search form_label_gray">風險類型</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiRiskType" name="NhiRiskType"
											ng-model="NhiRiskType" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="風險類型" />
										<h5 class="text-danger"
											ng-show="!editForm.NhiRiskType.$error.required && editForm.NhiRiskType.$invalid && editForm.NhiRiskType.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProcess"
										class="form_label form_label_search form_label_gray">目前流程</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProcess" name="NhiProcess"
											ng-model="NhiProcess" class="form-control col-xs-6"
											ng-maxlength="128" placeholder="目前流程" />
										<h5 class="text-danger"
											ng-show="!editForm.NhiProcess.$error.required && editForm.NhiProcess.$invalid && editForm.NhiProcess.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProblemIpAddress"
										class="form_label form_label_search form_label_gray">問題端資訊</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProblemIpAddress"
											name="NhiProblemIpAddress" ng-model="NhiProblemIpAddress"
											class="form-control col-xs-6" ng-maxlength="64"
											ng-pattern="/^(((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\,)|((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))*$/"
											placeholder="問題端資訊" />
										<h5 class="text-danger"
											ng-show="editForm.NhiProblemIpAddress.$invalid && editForm.NhiProblemIpAddress.$dirty">
											問題端資訊
											<s:message code="notifyFormat" />
											<s:message code="globalAnd" />
											<s:message code="formatMaxLengthAfter" arguments="64" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProblemPort"
										class="form_label form_label_search form_label_gray">問題Port</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProblemPort" name="NhiProblemPort"
											ng-model="NhiProblemPort" class="form-control col-xs-6"
											ng-maxlength="64" ng-pattern="/^\-?\d*$/"
											placeholder="問題Port" />
										<h5 class="text-danger"
											ng-show="editForm.NhiProblemPort.$invalid || editForm.NhiProblemPort.$error.maxlength && editForm.NhiProblemPort.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="64" />
											<s:message code="mustInteger" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProblemUrl"
										class="form_label form_label_search form_label_gray">問題URL</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProblemUrl" name="NhiProblemUrl"
											ng-model="NhiProblemUrl" class="form-control col-xs-6"
											ng-pattern="/^(?:(((H|h)(T|t)(T|t)(P|p)((S|s)?))|((H|h)(T|t)(T|t)(P|p))):\/\/)?((?:[a-zA-Z0-9.\-]+\.)+(?:[a-zA-Z0-9]))((?:/[\w+=%&.~\-]*)*)\??([\w+=%&.~\/-]*)$/"
											placeholder="問題URL" />
										<h5 class="text-danger"
											ng-show="!editForm.NhiProblemUrl.$error.required && editForm.NhiProblemUrl.$invalid && editForm.NhiProblemUrl.$dirty">
											<s:message code="c01NhiProblemUrlFormat" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProblemEquipmentOwner"
										class="form_label form_label_search form_label_gray">問題設備管理人</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProblemEquipmentOwner"
											name="NhiProblemEquipmentOwner"
											ng-model="NhiProblemEquipmentOwner"
											class="form-control col-xs-6" ng-maxlength="128"
											placeholder="問題設備管理人" />
										<h5 class="text-danger"
											ng-show="!editForm.NhiProblemEquipmentOwner.$error.required && editForm.NhiProblemEquipmentOwner.$invalid && editForm.NhiProblemEquipmentOwner.$dirty">
											<s:message code="formatMaxLengthAfter" arguments="128" />
										</h5>
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiProblemEquipmentUse"
										class="form_label form_label_search form_label_gray">問題設備用途</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiProblemEquipmentUse"
											name="NhiProblemEquipmentUse"
											ng-model="NhiProblemEquipmentUse"
											class="form-control col-xs-6" placeholder="問題設備用途" />
									</div>
								</div>
							</div>
							<div ng-show="btnUpdExt">
								<div class="form_group">
									<label for="NhiRemark"
										class="form_label form_label_search form_label_gray">備註</label>
									<div class="form_input form_input_search">
										<input type="text" id="NhiRemark" name="NhiRemark"
											ng-model="NhiRemark" class="form-control col-xs-6"
											placeholder="備註" />
									</div>
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
											<tr ng-repeat="item in InformationExchangeLog">
												<td><span ng-if="item.PreStatus==1">新增情資</span> <span
													ng-if="item.PreStatus==2">情資撤銷</span> <span
													ng-if="item.PreStatus==3">情資審核</span> <span
													ng-if="item.PreStatus==4">情資轉公開訊息</span><span
													ng-if="item.PreStatus==5">情資轉警訊</span><span
													ng-if="item.PreStatus==6">情資轉NISAC</span><span
													ng-if="item.PreStatus==7">情資已銷案</span><span
													ng-if="item.PreStatus==8">情資已退回</span> <span><i
														class="fas fa-fw fa-arrow-right"></i></span> <span
													ng-if="item.Status==2">情資撤銷</span> <span
													ng-if="item.Status==3">情資審核</span> <span
													ng-if="item.Status==4">情資轉公開訊息</span> <span
													ng-if="item.Status==5">情資轉警訊</span> <span
													ng-if="item.Status==6">情資轉NISAC</span> <span
													ng-if="item.Status==7">情資已銷案</span><span
													ng-if="item.Status==8">情資已退回</span></td>
												<td><span ng-if="item.Status==1">新增情資
															</span> <span ng-if="item.Status==2">情資撤銷
															</span> <span ng-if="item.Status==3">情資審核
															</span> <span ng-if="item.Status==4">情資轉公開訊息
															</span> <span ng-if="item.Status==5">情資轉警訊
															</span> <span ng-if="item.Status==6">情資轉NISAC
															</span> <span ng-if="item.Status==7">情資已銷案
															</span> <span ng-if="item.Status==8">情資已退回
															</span> </td>
												<td>{{item.CreateName}}</td>
												<td>{{item.CreateTime}}</td>
												<td>{{item.Opinion}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>

							<div>
								<div class="form_group"></div>
							</div>

						</div>

						<div id="tabFile" class="tab-pane">
							<div>
								<div class="form-group">
									<label for="FileData1"
										class="form_label form_label_search form_label_gray">
										附加檔案</label>
									<div class="form_input form_input_search"
										style="display: inline-block" ngf-select="" id="FileData1"
										ng-model="FileData1" name="FileData1"
										ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										ngf-max-size="100MB" ngf-min-height="100">
										<button id="SelectAttachment" class="btn btn-primary">
											<i class="fas fa-fw fa-file fa-lg"></i> 請選擇檔案
										</button>
										<span>{{FileData1.name}}</span>
										<button class="btn btn-danger" ng-click="deletefile(1)"
											ng-show="FileData1.name!=null">
											<i class="far fa-fw fa-trash-alt"></i>
											<s:message code="btnDelete" />
										</button>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="FileDesc1"
										class="form_label form_label_search form_label_gray">
										附件說明</label>
									<div class="form_input form_input_search">
										<textarea type="text" id="FileDesc1" name="FileDesc1"
											ng-model="FileDesc1" class="form-control" placeholder="附件說明"
											rows="5" autocomplete="off">
										</textarea>
									</div>
								</div>
							</div>

							<div>
								<div class="form-group">
									<label for="FileData2"
										class="form_label form_label_search form_label_gray">
										附加檔案</label>
									<div class="form_input form_input_search"
										style="display: inline-block" ngf-select="" id="FileData2"
										ng-model="FileData2" name="FileData2"
										ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										ngf-max-size="100MB" ngf-min-height="100">
										<button id="SelectAttachment" class="btn btn-primary">
											<i class="fas fa-fw fa-file fa-lg"></i> 請選擇檔案
										</button>
										<span>{{FileData2.name}}</span>
										<button class="btn btn-danger" ng-click="deletefile(2)"
											ng-show="FileData2.name!=null">
											<i class="far fa-fw fa-trash-alt"></i>
											<s:message code="btnDelete" />
										</button>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="FileDesc2"
										class="form_label form_label_search form_label_gray">
										附件說明</label>
									<div class="form_input form_input_search">
										<textarea type="text" id="FileDesc2" name="FileDesc2"
											ng-model="FileDesc2" class="form-control" placeholder="附件說明"
											rows="5" autocomplete="off">
										</textarea>
									</div>
								</div>
							</div>

							<div>
								<div class="form-group">
									<label for="FileData3"
										class="form_label form_label_search form_label_gray">
										附加檔案</label>
									<div class="form_input form_input_search"
										style="display: inline-block" ngf-select="" id="FileData3"
										ng-model="FileData3" name="FileData3"
										ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
										ngf-max-size="100MB" ngf-min-height="100">
										<button id="SelectAttachment" class="btn btn-primary">
											<i class="fas fa-fw fa-file fa-lg"></i> 請選擇檔案
										</button>
										<span>{{FileData3.name}}</span>
										<button class="btn btn-danger" ng-click="deletefile(3)"
											ng-show="FileData3.name!=null">
											<i class="far fa-fw fa-trash-alt"></i>
											<s:message code="btnDelete" />
										</button>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group">
									<label for="FileDesc3"
										class="form_label form_label_search form_label_gray">
										附件說明</label>
									<div class="form_input form_input_search">
										<textarea type="text" id="FileDesc3" name="FileDesc3"
											ng-model="FileDesc3" class="form-control" placeholder="附件說明"
											rows="5" autocomplete="off">
										</textarea>
									</div>
								</div>
							</div>

							<div>
								<div class="form_group"></div>
							</div>
						</div>



						<!-- <div>
							<div class="form_group"></div>
						</div> -->

						<div class="help-block"></div>
						<div class="submit_bar">
							<button class="btn btn_custom btn_blue"
								ng-click="createData(false,false)"
								ng-show="${actionCreate} && btnIns">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSave" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="createData(true,false)"
								ng-show="${actionCreate} && btnIns">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSaveAndExit" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="!editForm.$valid || createData(true,true)"
								ng-disabled="!editForm.$valid"
								ng-show="${actionCreate} && btnIns">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnSubmit" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="updateData(false,false)"
								ng-show="${actionUpdate} && btnUpd">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSave" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="updateData(true,false)"
								ng-show="${actionUpdate} && btnUpd">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSaveAndExit" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="!editForm.$valid || updateData(true,true)"
								ng-disabled="!editForm.$valid"
								ng-show="${actionUpdate} && btnUpd">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnSubmit" />
							</button>
							<button class="btn btn_custom btn_gray" type="button"
								ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>

					</div>


				</form>
			</div>
		</div>
	</div>


	<%----%>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>