<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s44.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QueryReportCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="ReportCode" /></label>
							<div class="form_input form_input_search">
								<select id="QueryReportCode" name="QueryReportCode"
									ng-model="QueryReportCode" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1"><s:message code="MemberReportCode" /></option>
									<option value="2"><s:message code="OrgReportCode" /></option>
								</select>
							</div>
						</div>
					</div>
					
					<div>
						<div class="form_group">
							<label for=Sdate
								class="form_label form_label_search form_label_gray"><s:message
									code="systemSdate" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Sdate" name="Sdate"
									ng-model="Sdate" class="form-control"
									placeholder="<s:message code="systemSdate" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for=Edate
								class="form_label form_label_search form_label_gray"><s:message
									code="systemEdate" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Edate" name="Edate"
									ng-model="Edate" class="form-control"
									placeholder="<s:message code="systemEdate" />"
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
			<div class="col-xs-12 col-md-6 no_padding">
				<a class="btn btn_custom btn_blue pull-right" type="button"
					ng-click="openEdit()" ng-show="${actionCreate}"> <i
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
						
							<th><a href="" ng-click="setSortName('reportCode')"> <s:message
										code="reportCode" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'reportCode' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'reportCode' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							<th><a href="" ng-click="setSortName('message')"> <s:message
										code="reportMessage" /> <i ng-show="sorttype != 'message'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'message' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'message' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('scheduleTime')"> <s:message
										code="reportScheduleTime" /> <i ng-show="sorttype != 'scheduleTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'scheduleTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'scheduleTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							<th><a href="" ng-click="setSortName('isOldSchedule')"> <s:message
										code="reportIsOldSchedule" /> <i ng-show="sorttype != 'isOldSchedule'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isOldSchedule' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isOldSchedule' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="reportStatus" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							
							
							
							
							<th ng-show="${actionUpdate && actionDelete}" class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
						
							<td> {{item.ReportName}} </td>
						
							<td>{{item.Message}}</td>
							
							<td>{{item.ScheduleTime}}</td>
							
							<td> {{item.IsOldSchedule}} </td>
							<td> {{item.Status}} </td>
							
							
							<td ng-show="${actionUpdate && actionDelete}" class="text-center">
								<a class="btn btn-sm btn-primary" ng-click="execute(item.ReportCode , item.ScheduleTime);"
								title='<s:message code="btnExecute" />' ng-show="${actionUpdate}"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnExecute" /> </a><br />
								<a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id, item.ReportCode, item.ScheduleTime);" ng-show="${actionDelete}"><i
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
							<label for="ReportCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="reportCode" /></label>
							<div class="form_input form_input_search">
								<select id=ReportCode name="ReportCode"
									    ng-model="ReportCode" class="form-control col-xs-6"
									    ng-required="true" >
										<option value="" selected><s:message code="s44reportCode" /></option>
										<option value="1" selected> 會員統計報表</option>
										<option value="2" selected> 組織統計報表 </option>	
								</select>	
							</div>
						</div>
					</div>
					
					<div>
                    <div class="form_group">
                        <label class="form_label form_label_search form_label_gray"> 報表執行時間</label>
                        
                        
                        
                        
                        <div class="form_input form_input form_input_search">
								<div class="input-group">
									<input id="ReportScheduleTime" name="ReportScheduleTime" ng-model="ReportScheduleTime"
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