<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p03.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_red">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-aboutus.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
		</div>
		<div id="divQuery" style="display: none">
			<div class="form_group">
							<label for="QueryKeyword"
								class="form_label form_label_search form_label_gray">關鍵字</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyword" name="QueryKeyword"
									ng-model="QueryKeyword" class="form-control col-xs-6"
									placeholder="關鍵字" />
									<button class="btn btn_custom btn_blue left pull-left" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
							</div>
						</div>
			<div class="row">
				<div class="col-xs-12 col-md-6 no_padding">
					<div class="help-block"></div>
					<%@ include file="./../include/table_row_select.jsp"%>
				</div>
				<div class="col-xs-12 col-md-6 no_padding"></div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
							    <th>
							    <a href="" ng-click="setSortName('QAMgName')"> <s:message
										code="p01QAManagementGroup" /> <i ng-show="sorttype != 'QAMgName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'QAMgName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'QAMgName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
								</a>
							    </th>
								<th>
								<a href="" ng-click="setSortName('QName')"> <s:message
										code="p01QAManagementName" /> <i ng-show="sorttype != 'QName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'QName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'QName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
								</a>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in allitems">
								<td>{{item.QAMgName}}</td>
								<td><a href="#" ng-click="queryDetailData(item.Id)">{{item.QName}}</a></td>
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
				<a class="btn btn-default btn_custom pull-left"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>
			</div>
		</div>
		<div id="divDetail" style="display: none">
			<div class="row detail_block">
				<h3 class="detailTitle">{{detailQName}}</h3>
				<h5 class="detailSource">
					<span><s:message code="p01QAManagementAName" /></span>
					
				</h5>
				<div class="detailContent">
					<div  ng-bind-html="detailAName"></div>
					
				</div>
			</div>
			
			<div class="row detail_block" ng-show="itemAttachments.length > 0">
				<h4>
					<i class="fas fa-fw fa-download"></i>
					<s:message code="activityAttachDownload" />
				</h4>
				<ul class="detailAttachFile">
					<li ng-repeat="item in itemAttachments"><s:message
							code="globalFileName" />&nbsp;:&nbsp;<a
						ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/api/p03/attach/download/{{id}}/{{item.Id}}"
						target="_blank">{{item.FileName}}</a>&nbsp;(<s:message
							code="globalFileSize" />&nbsp;:&nbsp;{{item.FileSize}}&nbsp;)</li>
				</ul>
			</div>
			
			<div class="row detail_block">
				<a class="btn btn-default btn_custom"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/">
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
	<%@ include file="../include/footer.jsp"%>
</body>
</html>