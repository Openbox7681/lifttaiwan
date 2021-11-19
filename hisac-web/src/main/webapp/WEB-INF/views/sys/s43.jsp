<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s43.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular-bootstrap-toggle/angular-bootstrap-toggle.min.css">
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
							<label
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
							ng-click="queryData()" ng-hide="query">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="queryData()" ng-show="query">
							<i class="fa fa-spinner fa-spin"></i>
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
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="12">會員狀態統計表(截至目前)</th></tr>
						<tr colspan="12">
							<th colspan="4">會員人數</th>							
							<th colspan="4">會員機構管理者</th>
							<th colspan="4">會員機構聯絡人</th>
							<tr>
								<th style="width: 10%;" rowspan="2" valign=middle>總數</th>
								<th style="width: 10%;" rowspan="2" valign=middle>CI會員</th>
								<th style="width: 10%;" rowspan="2" valign=middle>非CI一般</th>
								<th style="width: 10%;" rowspan="2" valign=middle>非CI進階</th>							
								<th style="width: 9%;">合計</th>
								<th style="width: 7%;">使用中</th>
								<th style="width: 7%;">待啟用</th>								
								<th style="width: 7%;">已停用</th>							
								<th style="width: 9%;">合計</th>
								<th style="width: 7%;">使用中</th>
								<th style="width: 7%;">待啟用</th>								
								<th style="width: 7%;">已停用</th>
							</tr>							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-right">{{MemberCount | number}}</td>
							<td class="text-right">{{CIMemberCount | number}}</td>
							<td class="text-right">{{NonCIBasedMemberCount | number}}</td>
							<td class="text-right">{{NonCIAdvancedCount | number}}</td>
							<td class="text-right">{{ManagerCount | number}}</td>
							<td class="text-right">{{ManagerUse | number}}</td>
							<td class="text-right">{{ManagerWait | number}}</td>							
							<td class="text-right">{{ManagerDisable | number}}</td>
							<td class="text-right">{{ContactCount | number}}</td>
							<td class="text-right">{{ContactUse | number}}</td>
							<td class="text-right">{{ContactWait | number}}</td>							
							<td class="text-right">{{ContactDisable | number}}</td>
						</tr>
					</tbody>
				</table>
				<br />				
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="11">會員機構瀏覽公開資訊統計表</th></tr>
						<tr>							
							<th style="width: 5%;">序號</th>			
							<th style="width: 15%;">會員機構</th>			
							<th style="width: 10%;">會員機構等級</th>		
							<th style="width: 8%;">會員人數</th>							
							<th style="width: 8%;">會員登入數</th>	
							<th style="width: 8%;">登入次數</th>	
							<th style="width: 8%;">最新消息點擊次數</th>	
							<th style="width: 8%;">活動訊息點擊次數</th>	
							<th style="width: 8%;">資安訊息情報點擊次數</th>	
							<th style="width: 8%;">醫療設備資安情報點擊次數</th>		
							<th style="width: 14%;">申請通過日期</th>							
						</tr>
					</thead>
					<tbody>
						<tr ng-show="sumOrgReport!=null">					
							<td class="text-left" bgcolor="#fcd6c7">合計</td>
							<td class="text-center" colspan="2" bgcolor="#fcd6c7">CI會員機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumCiCount}}</span> , 非CI一般機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumNonCiBasedCount}}</span> , 非CI進階機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumNonCiAdvancedCount}}</span></td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumMemberCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumMemberSigninCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumSigninCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumNewsCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumActivityCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumAnaCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumSecbuzzerCount}}</td>		
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;"></td>		
						</tr>
						<tr ng-repeat="org in orgReport">							
							<td class="text-center">{{$index+1}}</td>
							<td class="text-left">{{org.OrgName}}</td>
							<td class="text-center">{{org.CiLevel==2?"CI":org.CiLevel==1?"非CI進階":"非CI一般"}}</td>
							<td class="text-right">{{org.MemberCount | number}}</td>
							<td class="text-right">{{org.MemberSigninCount | number}}</td>
							<td class="text-right">{{org.SigninCount | number}}</td>			
							<td class="text-right">{{org.NewsCount | number}}</td>			
							<td class="text-right">{{org.ActivityCount | number}}</td>			
							<td class="text-right">{{org.AnaCount | number}}</td>			
							<td class="text-right">{{org.SecbuzzerCount | number}}</td>	
							<td class="text-right">{{org.SignApplyTime }}</td>												
						</tr>
						<tr ng-show="sumOrgReport!=null">					
							<td class="text-center" bgcolor="#fcd6c7">合計</td>
							<td class="text-center" colspan="2" bgcolor="#fcd6c7">CI會員機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumCiCount}}</span> , 非CI一般機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumNonCiBasedCount}}</span> , 非CI進階機構數：<span style="color:#fc6a35;">{{sumOrgReport.SumNonCiAdvancedCount}}</span></td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumMemberCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumMemberSigninCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumSigninCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumNewsCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumActivityCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumAnaCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;">{{sumOrgReport.SumSecbuzzerCount}}</td>
							<td class="text-right" bgcolor="#fcd6c7" style="color:#fc6a35;"></td>			
						</tr>
					</tbody>
				</table>
				<br />	
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="4">審核中會員機構列表</th></tr>						
						<tr>
							<th style="width: 10%;">序號</th>
							<th style="width: 50%;">會員機構</th>
							<th style="width: 20%;">會員機構等級</th>	
							<th style="width: 20%;">申請日期</th>																
						</tr>												
					</thead>
					<tbody>
						<tr ng-repeat="org in orgExamineReport">
							<td class="text-center">{{$index+1}}</td>
							<td class="text-left">{{org.OrgName}}</td>
							<td class="text-center">{{org.CiLevel==2?"CI":org.CiLevel==1?"非CI進階":"非CI一般"}}</td>	
							<td class="text-right">{{org.CreateTime}}</td>				
						</tr>
						<tr ng-show="sumOrgReport!=null">					
							<td class="text-center" bgcolor="#fcd6c7">合計</td>
							<td class="text-center" colspan="3" bgcolor="#fcd6c7">CI會員機構數：<span style="color:#fc6a35;">{{sumOrgExamineReport.SumExamineCiCount}}</span> , 非CI一般機構數：<span style="color:#fc6a35;">{{sumOrgExamineReport.SumExamineNonCiBasedCount}}</span> , 非CI進階機構數：<span style="color:#fc6a35;">{{sumOrgExamineReport.SumExamineNonCiAdvancedCount}}</span></td>							
						</tr>
					</tbody>
				</table>
				<br />							
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="3">最新消息 ─點擊數前十高訊息</th></tr>
						<tr>							
							<th style="width: 10%;">排名</th>		
							<th style="width: 80%;">標題</th>							
							<th style="width: 10%;">點擊數</th>							
					</thead>
					<tbody>
						<tr ng-repeat="news in newsTop5">
							<td class="text-center"><a href="#divTop5Detail" data-toggle="modal"
								ng-click="viewTop5Detail('news',news.Id);">{{$index+1}}</a></td>
							<td class="text-left">{{news.Title}}</td>							
							<td class="text-right">{{news.Count | number}}</td>							
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="3">活動訊息 ─點擊數前十高訊息</th></tr>
						<tr>							
							<th style="width: 10%;">排名</th>		
							<th style="width: 80%;">標題</th>							
							<th style="width: 10%;">點擊數</th>								
					</thead>
					<tbody>
						<tr ng-repeat="activity in activityTop5">
							<td class="text-center"><a href="#divTop5Detail" data-toggle="modal"
								ng-click="viewTop5Detail('activity',activity.Id);">{{$index+1}}</a></td>
							<td class="text-left">{{activity.Title}}</td>							
							<td class="text-right">{{activity.Count | number}}</td>		
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="8">資安訊息情報─點擊數前十高訊息</th></tr>
						<tr>
							<th style="width: 5%;">排名</th>	
							<th style="width: 10%;">編號</th>
							<th style="width: 20%;">情報名稱</th>
							<th style="width: 15%;">發現日期</th>
							<th style="width: 15%;">事件種類</th>
							<th style="width: 20%;">發布單位</th>
							<th style="width: 9%;">點擊數</th>
							<th style="width: 6%;">下載附件數</th>
					</thead>
					<tbody>
						<tr ng-repeat="ana in anaTop5">
							<td class="text-center"><a href="#divTop5Detail" data-toggle="modal"
								ng-click="viewTop5Detail('ana',ana.Id);">{{$index+1}}</a></td>
							<td class="text-left">{{ana.PostId}}</td>
							<td class="text-left">{{ana.IncidentTitle}}</td>
							<td class="text-left">{{ana.IncidentDiscoveryTime}}</td>
							<td class="text-left">{{geEventTypeName(ana.EventTypeCode)}}</td>
							<td class="text-left">{{ana.ReporterName}}</td>
							<td class="text-right">{{ana.Count | number}}</td>
							<td class="text-right">{{ana.ReferenceCount | number}}</td>
						</tr>
					</tbody>
				</table>
				<br />
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr><th colspan="6">醫療設備資安情報 ─點擊數前十高訊息</th></tr>
						<tr>
							<th style="width: 5%;">排名</th>	
							<th style="width: 30%;">標題</th>
							<th style="width: 20%;">發現日期</th>
							<th style="width: 15%;">資料來源</th>
							<th style="width: 10%;">點擊數</th>
							<th style="width: 20%;">連結參考資料</th>							
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="sec in secTop5">
							<td class="text-center"><a href="#divTop5Detail" data-toggle="modal"
								ng-click="viewTop5Detail('sec',sec.Id);">{{$index+1}}</a></td>
							<td class="text-left">{{sec.IncidentTitle}}</td>
							<td class="text-left">{{sec.IncidentDiscoveryTime}}</td>
							<td class="text-left">{{getSourceName(sec.SourceCode)}}</td>
							<td class="text-right">{{sec.Count | number}}</td>
							<td class="text-left">{{sec.Reference}}</td>					
						</tr>
					</tbody>					
				</table>
			</div>
		</div>
	</div>

	<div id="divTop5Detail" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
				</div>
				<div class="modal-body">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr><th colspan="3">前十名</th></tr>
							<tr>								
								<th style="width: 25%;">排名</th>
								<th style="width: 50%;">會員機構</th>
								<th style="width: 25%;">點擊次數</th>	
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in allTop5Detail">														
								<td class="text-center">{{item.Rank}}</td>
								<td class="text-left">{{item.OrgName}}</td>
								<td class="text-right">{{item.Count | number}}</td>							
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button class="btn btn-danger pull-right" data-dismiss="modal">
						<i class="fas fa-fw fa-times"></i>
						<s:message code="btnClose" />
					</button>
				</div>
			</div>
		</div>
	</div>	

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>