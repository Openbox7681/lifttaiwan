<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k00.js"></script>
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
					<div>
						<div class="form_group">
							<label for="QuerySDate"
								class="form_label form_label_search form_label_gray">建立時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="SDate" name="SDate"
										ng-model="SDate" class="form-control"
										placeholder="建立時間(起)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EDate" name="EDate"
										ng-model="EDate" class="form-control"
										placeholder="建立時間(訖)"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
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
									placeholder="法遵稽核計畫資料"
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
				<c:if test="${isAdmin}">
					<a class="btn btn_custom btn_blue pull-right" type="button"
						ng-click="openEdit()"> <i class="fas fa-fw fa-plus-circle"></i>
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
							<th><a href="" ng-click="setSortName('title')"> 法遵稽核計畫資料 <i ng-show="sorttype != 'title'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'title' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'title' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('year')">年度 <i
									ng-show="sorttype != 'year'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'year' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'year' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
																										
							<th><a href="" ng-click="setSortName('createTime')"><s:message
										code="globalCreateTime" /><i ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>
							
										
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>
								{{item.Title}}															
							</td>
																		
							<td>{{item.Year}}</td>												
							
							<td>{{item.CreateTime}}</td>											
							
											
							<td class="text-center">
								<!-- 編輯資料 -->
								<a class="btn btn-sm btn-primary"
									ng-click="editData(item.Id);" title='<s:message code="btnEdit" />'
									ng-show="item.Status==0"><i class="fas fa-fw fa-edit"></i>
									<s:message code="btnEdit" /> </a> 
																<!-- 編輯資料 -->
								<a class="btn btn-sm btn-primary"
									ng-click="viewData(item.Id);"
									ng-show="item.Status==1">
									檢視 </a> 
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
	<!-- divEdit開始 -->
	<div id="divEdit" class="container">
		<div class="row" >
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
			<div class="form_group">
            	<h4 class="form_heading form_heading form_heading_yellow">
                	<i class="fa fa-info-circle"><b>法遵稽核</b></i>
                </h4>
            </div>
        <form name="editForm">																
			<div class="form_group">
				<label for="Title"
					class="form_label form_label_search form_label_gray">計畫名稱</label>
				<div class="form_input form_input_search">
					<input type="text" id="Title" name="Title"
						placeholder="法遵稽核計畫名稱" ng-required="true"
						ng-disabled= "!btnUpd && !btnIns"
						ng-maxlength="512" ng-model="Title" class="form-control" />
					<h5 class="text-danger"
						ng-show="editForm.Title.$error.maxlength && editForm.Title.$dirty">
						<s:message code="formatMaxLengthAfter" arguments="512" />
					</h5>
				</div>
			</div>
			<div class="form_group">
				<label for="Year"
						class="form_label form_label_search form_label_gray">計畫年度</label>
				<div class="form_input form_input_search">
					<input type="number" id="Year" name="Year"
								placeholder="法遵稽核計畫年度" ng-required="true"
								ng-disabled= "!btnUpd && !btnIns"
								ng-maxlength="4" ng-model="Year" class="form-control" />
					<h5 class="text-danger"
								ng-show="editForm.Year.$error.maxlength && editForm.Year.$dirty">
								<s:message code="formatMaxLengthAfter" arguments="4" />
					</h5>
				</div>
			</div>
			
			<div class="form_group">
				<label for="SPlanDate"
						class="form_label form_label_search form_label_gray">填寫時間(起)</label>
				<div class="form_input form_input form_input_search">
				<div class="input-group">
					<input id="SPlanDate" name="SPlanDate" ng-model="SPlanDate"
						ng-disabled= "!btnUpd && !btnIns"
						type="text" class="form-control" ng-required="true"> <span
						class="input-group-addon"> <i
						class="fas fa-calendar-alt"></i></span>
					</div>
				</div>
			</div>
				
				
			<div class="form_group">
				<label for="EPlanDate"
					class="form_label form_label_search form_label_gray">填寫時間(訖)</label>
				<div class="form_input form_input form_input_search">
					<div class="input-group">
						<input id="EPlanDate" name="EPlanDate" ng-model="EPlanDate"
								ng-disabled= "!btnUpd && !btnIns"
								type="text" class="form-control" ng-required="true"> <span
								class="input-group-addon"> <i
								class="fas fa-calendar-alt"></i>
						</span>
					</div>
				</div>
			</div>
																	
				<div>
					<div class="form_group"></div>
				</div>
			<div class="form_group">
            	<h4 class="form_heading form_heading form_heading_yellow">
                	<i class="fa fa-info-circle"><b>調查對象</b></i>
                </h4>
        	</div>
				<div class="form_group">
					<label for="Recipient"
					class="form_label form_label_search form_label_gray">機構名稱</label>
				</div>
				<div class="form_input form_input_search">
					<button class="btn btn-primary" ng-click="addMember()" ng-show = "btnUpd || btnIns">
						<i class="fas fa-fw fa-plus"></i>
						<s:message code="messageAddNewRecord" />
						機構
					</button>
					<table class="table table-striped table-bordered table-hover table_customer table_gray"
							ng-show="memberList.length > 0">
						<thead>
							<tr>
								<th width="80%">機構</th>
								<th width="20%"></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="data in memberList">
								<td><select id="Member_{{$index+1}}"
									name="Member_{{$index+1}}" ng-model=data.MemberId
									class="form-control col-xs-6"
									ng-options="Member.Id as Member.Name for Member in data.Members"
									ng-required="true"
									ng-disabled= "!btnUpd && !btnIns">
									<option value="" selected> <s:message code="pleaseSelect" />機構</option>
								</select></td>
								<td class="text-center"><button class="btn btn-danger"
									ng-click="deleteMember($index)"
									ng-show = "btnUpd || btnIns">
									<i class="fas fa-fw fa-times"></i>
									<s:message code="btnDelete" />
								</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			<div>
				<div class="form_group"></div>
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
						ng-click=" createData(true,true)"
						 ng-disabled="!editForm.$valid"
						 ng-show="btnIns">
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
						ng-click="updateData(true,true)"
						ng-disabled="!editForm.$valid"
						ng-show="btnUpd">
						<i class="fas fa-fw fa-check"></i>
						<s:message code="btnSubmit" />
					</button>
					<button class="btn btn_custom btn_gray" type="button"
						ng-click="back()">
						<i class="fas fa-fw fa-undo"></i>
						<s:message code="btnReturn" />
					</button>
			</div>
			</form>
	</div>
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>