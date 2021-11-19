<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s46.js"></script>
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
							<label for="QueryCode"
								class="form_label form_label_search form_label_gray"> 年份
							</label>
							<div class="form_input form_input_search">
								<select ng-model="QueryYear" ng-change="changeOrg()"
									ng-options="year.Year as year.Year for year in YearList"
									class="form-control col-xs-6">
								</select>

							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar" ng-show="${actionUpdate && actionDelete}">
						<button class="btn btn_custom btn-info" ng-click="updateData();">
							<i class="ace-icon fa fa-check "></i>
							設定為本年度資料
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
					<div class="col-xs-12 col-md-6 no_padding">
		
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
			
						</div>
						
						
						<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>
					<label><span id="imgLoading"><i
							class="fas fa-spinner fa-spin"></i> <s:message code="dataLoading" />
					</span></label>			
					
					</div>
			
			
			
		</div>
		
		

		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th width="15%"><s:message code="orgSignOrgId" /></th>
							<th width="15%"><span>年份</span></th>
							<th width="25%"><span><s:message
										code="orgSignOrgName" /></span></th>
							<th width="45%"><span>資通安全責任等級資料</span></th>
							
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in OrgSecurityLevelList">
							<td>{{item.OrgId}}</td>
							
							<td>{{item.Year}}</td>
							
							<td>{{item.Name}}</td>

							

							<td>
							<span class = "form_input_search">
							<input type="radio"
								ng-model="item.SecurityLevel" ng-value=1
								> 
								A
								</span>
								<span class = "form_input_search">
									<input type="radio"
								ng-model="item.SecurityLevel" ng-value=2
								>
								B
								</span>
								
								<span class = "form_input_search">
									<input type="radio"
								ng-model="item.SecurityLevel" ng-value=3
								> 
								C
								</span>
								<span class = "form_input_search">
								<input type="radio"
								ng-model="item.SecurityLevel" ng-value=4
								> 
								D
								</span>
																<span class = "form_input_search">
								
								<input type="radio"
								ng-model="item.SecurityLevel" ng-value=5
								> 
								E
																</span>
							<span class = "form_input_search">
			
								
								<input type="radio"
								ng-model="item.SecurityLevel" ng-value=0
								> 
								尚未設定
								</span>
								
								
								</td>
							
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
