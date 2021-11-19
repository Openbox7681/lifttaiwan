<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="./include/head_index.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/paging.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/news.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="./include/head_navbar.jsp"%>
	<div class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_blue">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-news.svg" />
				<b>最新消息</b>
			</h4>
		</div>
		<div id="divQuery" style="display: none">
			<div class="row">
				<div class="col-xs-12 col-md-6 no_padding">
					<div class="help-block"></div>
					<%@ include file="./include/table_row_select.jsp"%>
				</div>
				<div class="col-xs-12 col-md-6 no_padding"></div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th class="col-xs-2"><s:message code="newsPostDateTime" /></th>
								<th class="col-xs-3"><s:message code="newsGroup" /></th>
								<th><s:message code="newsTitle" /></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in allitems">
								<td class="text-center">{{item.Date}}</td>
								<td>{{item.Group}}</td>
								<td><a href="#" ng-click="queryDetailData(item.Id)">{{item.Title}}</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<%@ include file="./include/table_row_empty.jsp"%>
				<div class="text-center">
					<paging class="pagination" page="currentPage" page-size="maxRows"
						total="total" show-prev-next="true" show-first-last="true"
						text-next-class="fas fa-step-forward"
						text-prev-class="fas fa-step-backward"
						text-first-class="fas fa-fast-backward"
						text-last-class="fas fa-fast-forward"
						paging-action="queryData(page)"></paging>
				</div>
				<a class="btn btn-default btn_custom pull-left"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>
			</div>
		</div>
		<div id="divDetail" style="display: none">
			<div class="row detail_block">
				<h3 class="detailTitle">{{detailTitle}}</h3>
				<h5 class="detailSource">
					<span><s:message code="newsSoruce" />&nbsp;:&nbsp;{{detailSourceName}}</span>
					<span class="pull-right"><s:message code="newsPostDateTime" />&nbsp;:&nbsp;{{detailPostDataTime}}</span>
				</h5>
				<div class="detailContent">
					<div ng-if="!isBreakLine" ng-bind-html="detailContent"></div>
					<div ng-if="isBreakLine" ng-bind-html="detailContent | trustHtml"></div>
				</div>
				<h5 class="detailSource" ng-if="detailSourceLink != null && detailSourceLink != ''">
					<span><s:message code="s31SourceLink" />&nbsp;:&nbsp;</span><a ng-href="{{detailSourceLink}}" target="_news">{{detailSourceLink}}</a>
				</h5>
			</div>
			<div class="row detail_block" ng-show="itemAttachments.length > 0">
				<h4>
					<i class="fas fa-fw fa-download"></i>
					<s:message code="newsAttachDownload" />
				</h4>
				<ul class="detailAttachFile">
					<li ng-repeat="item in itemAttachments"><s:message
							code="globalFileName" />&nbsp;:&nbsp;<a
						ng-href="<c:out value="${pageContext.request.contextPath}" />/public/api/news/attach/download/{{id}}/{{item.Id}}"
						target="_blank">{{item.FileName}}</a>&nbsp;(<s:message
							code="globalFileSize" />&nbsp;:&nbsp;{{item.FileSize}}&nbsp;)</li>
				</ul>
			</div>
			<div class="row detail_block">
				<a class="btn btn-default btn_custom"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>
				<button class="btn btn-default btn_custom pull-right"
					ng-click="clearData();queryData(currentPage)">
					<i class="fas fa-fw fa-undo"></i>
					<s:message code="btnReturnList" />
				</button>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>