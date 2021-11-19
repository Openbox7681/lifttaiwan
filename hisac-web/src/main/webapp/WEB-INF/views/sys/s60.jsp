<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s60.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/jquery.twzipcode.min.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="Keyword"
								class="form_label form_label_search form_label_gray">關鍵字</label>
							<div class="form_input form_input_search">
								<input type="text" id="Keyword" name="Keyword"
									ng-model="Keyword" class="form-control" placeholder="關鍵字"
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
							<th width="25%"><a href=""
								ng-click="setSortName('incidentReportedTime')"> <span>日期</span>
									<i ng-show="sorttype == 'incidentReportedTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'incidentReportedTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th width="50%"><a href=""
								ng-click="setSortName('incidentId')"> <span>標題</span> <i
									ng-show="sorttype == 'incidentId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'incidentId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('sort')"> 排序(數字越大排越上面)
							 <i ng-show="sorttype != 'sort'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'sort' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'sort' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>

						<tr ng-repeat="item in allitems">
							<td>{{item.Date}}</td>
							<td>{{item.Cve}}{{item.Title}}</td>
							<td>{{item.Sort}}</td>
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary" ng-click="editData(item.Id);"
								title='<s:message code="btnEdit" />' ng-show="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);" ng-show="${actionDelete}"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
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


	<div id="divEdit" class="container" ng-show="btnIns || btnUpd">
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
			<div class="col-xs-12 shadow_board">
				<form name="editForm">
					<div>
						<div class="form_group">
							<label for="IncidentId"
								class="form_label form_label_search form_label_gray">CVE</label>
							<div class="form_input form_input_search">
								<span>{{IncidentId}}</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IncidentTitle"
								class="form_label form_label_search form_label_gray">標題</label>
							<div class="form_input form_input_search">
								<input type="text" id="IncidentTitle" name="IncidentTitle"
									ng-model="IncidentTitle" class="form-control"
									placeholder="標題" autocomplete="off" ng-required="true">
								<h5 class="text-danger"
									ng-show="editForm.IncidentTitle.$error.required && editForm.IncidentTitle.$dirty">
									<s:message code="pleaseEnter" />
									標題
								</h5>
							</div>
						</div>
						<div class="form_group">
							<label for="Description"
								class="form_label form_label_search form_label_gray">內文</label>
							<div class="form_input form_input_search">
								<textarea id="Description" name="Description" ng-model="Description"
									ng-required="true" rows="5" autocomplete="off"
									class="form-control"></textarea>
							</div>
						</div>
						<div>
						<div class="form_group">
							<label for="Reference"
								class="form_label form_label_search form_label_gray">參考資料</label>
							<div class="form_input form_input_search">
								<span>{{Reference}}</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Sort"
								class="form_label form_label_search form_label_gray"><s:message
									code="sort" /></label>
							<div class="form_input form_input_search_half">
								<input type="number" id="Sort" name="Sort" ng-model="Sort"
									class="form-control" placeholder="<s:message code="sort" />"
									autocomplete="off" pattern="[0-9]*" inputmode="numeric" min="0"
									onkeypress="var key = window.event ? event.keyCode : event.which; return (key >= 48 && key <= 57) || key == 8"
									ng-required="true" ng-maxlength="4" />
								<h5 class="text-danger"
									ng-show="editForm.Sort.$error.maxlength && editForm.Sort.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="4" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Sort.$error.required && editForm.Sort.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="sort" />
								</h5>
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
							ng-click="!editForm.$valid || createData()"
							ng-disabled="!editForm.$valid"
							ng-show="${actionCreate} && btnIns">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnSave" />
						</button>
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!editForm.$valid || updateData()"
							ng-disabled="!editForm.$valid"
							ng-show="${actionUpdate} && btnUpd">
							<i class="fas fa-fw fa-save"></i>
							<s:message code="btnSave" />
						</button>
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="closeEdit()">
							<i class="fas fa-fw fa-undo"></i>
							<s:message code="btnReturn" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>