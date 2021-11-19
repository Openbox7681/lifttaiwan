<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/alt/a03.js"></script>
<script>
	var globalAcceptImageFormat = '<s:message code="globalAcceptImageFormat" />';
	var globalAcceptAttachmentFormat = '<s:message code="globalAcceptAttachmentFormat" />';
	var globalUploadFileFail = '<s:message code="globalUploadFileFail" />';
</script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>

	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
				<div ng-bind-html="handle_information_remark | trustHtml"></div>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QueryName"
								class="form_label form_label_search form_label_gray">登錄機構名稱 </label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="登錄機構名稱 "
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
							<th><a href="" ng-click="setSortName('name')"> 登錄機構名稱 
									<i ng-show="sorttype != 'name'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'name' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'name' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('section')"> 服務區域 <i ng-show="sorttype != 'section'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'section' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'section' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('serviceItems')"> 服務項目 <i
									ng-show="sorttype != 'serviceItems'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'serviceItems' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'serviceItems' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('contactInfo')"> 聯絡資訊 <i
									ng-show="sorttype != 'contactInfo'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'contactInfo' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'contactInfo' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>	
							<th><a href="" ng-click="setSortName('remark')"> 來源依據 <i
									ng-show="sorttype != 'remark'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'remark' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'remark' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>																													
							<th>檔案下載</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">						
							<td>{{item.Name}}</td>
							<td>{{item.Section}}</td>
							<td><div ng-bind-html="item.ServiceItems | trustHtml"></div></td>								
							<td><div ng-bind-html="item.ContactInfo | trustHtml"></div></td>
							<td><div ng-bind-html="item.Remark | trustHtml"></div></td>	
							<td><a
										href="./api/a03/attach/download/{{item.Id}}"
										target="_blank">服務項目說明</a>
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

	<%@ include file="../include/footer.jsp"%>
</body>
</html>