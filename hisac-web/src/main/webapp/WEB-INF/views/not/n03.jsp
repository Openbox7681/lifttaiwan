<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/not/n03.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QuerySApplyTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageQueryDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QuerySdate" name="QuerySdate"
										ng-model="QuerySdate" class="form-control"
										placeholder="<s:message code="messageQuerySdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QueryEdate" name="QueryEdate"
										ng-model="QueryEdate" class="form-control"
										placeholder="<s:message code="messageQueryEdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
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

		<div class="row" ng-show="ExporExcel">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
			<button class="btn btn_custom btn_heading_tool btn_blue pull-right" ng-click="exportData();">
				<i class="fas fa-fw fa-file-excel"></i><s:message
						code="globalExcel" />
			</button>
		</div>
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th width="5%"><span><s:message code="messageMonth" /></span></th>
								<th width="5%" ng-repeat="alertType in alertTypes"><span>{{alertType.Name}}({{alertType.Code}})</span>
								</th>
								<th width="5%"><span><s:message
											code="messageMonthTotal" /></span></th>
							</tr>
						</thead>

						<tr ng-repeat="item in allitems">
							<td>{{item}}</td>
							<th width="5%" ng-repeat="alertType in alertTypes"
								class="text-right"><a href="#informant-modal"
								data-toggle="modal"
								ng-click="getMessageData(alertType.Code,item);">
									{{getCount(alertType.Code,item)|number:0}} </a></th>
							<td class="text-right">{{countTotal(item)|number:0}}</td>
						</tr>
					</table>



				</div>

			</div>

		</div>
		<div class="row" ng-show="QueryDataList">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="messageQueryList" /></b>
			</h4>

		</div>
		<div class="row" ng-show="QueryDataList">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover dataTable">
					<thead>
						<tr>
							<th width="10%">序號</th>
							<th width="10%"><s:message code="messagePostId" /></th>
							<th width="10%"><s:message code="messagePostDateTime" /></th>
							<th width="20%"><s:message code="messageSourceName" /></th>
							<th width="20%"><s:message code="messageSubject" /></th>
							<th width="20%"><s:message code="messageStatus" /></th>
							<th width="20%"><s:message code="messageIsReply" /></th>

						</tr>
					</thead>
					<tbody ng-show="tabAttachmentLoad">
						<tr>
							<td colspan="4"><label class="imgLoading"> <img
									id="imgLoading" src="./../resources/img/loading3.gif"
									alt="loading... please wait" title="loading... please wait">
							</label></td>
						</tr>
					</tbody>
					<tbody ng-show="!tabAttachmentLoad">
						<tr ng-repeat="item in message_allitems">
						    <td>{{$index + 1}}</td>
							<td>{{item.PostId}}</td>
							<td>{{item.PostDateTime}}</td>
							<td>{{item.SourceName}}<br>{{item.CreateName}}
							</td>
							<td>{{item.Subject}}</td>
							<td>{{item.Status=="11"?"<s:message code="n03Status1" />":"" }}
								{{item.Status=="21"?"<s:message code="n03Status2" />":"" }} {{item.Status=="31"?"<s:message code="n03Status3" />":"" }}
								{{item.Status=="41"?"<s:message code="n03Status4" />":"" }} {{item.Status=="51"?"<s:message code="n03Status5" />":"" }}
								{{item.Status=="61"?"<s:message code="n03Status6" />":"" }}</td>
							<td>{{item.IsReply==true?"Y":"N"}}</td>

						</tr>
					</tbody>
				</table>
			</div>

		</div>



	</div>






	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>