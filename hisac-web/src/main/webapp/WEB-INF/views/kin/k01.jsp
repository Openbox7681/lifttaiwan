<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/kin/k01.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	
	<!-- 查詢法遵稽核計畫 start -->
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_pink">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<form name="queryForm">
				<div class="col-xs-12 shadow_board">
					<div>
						<div class="form_group">
							<label for="QueryKeyword" class="form_label form_label_search form_label_gray">
								<s:message code="messageKeyword" />
							</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyword" name="QueryKeyword" ng-model="QueryKeyword"
									class="form-control" placeholder="法遵稽核計畫名稱" autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryYear" class="form_label form_label_search form_label_gray">年度</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryYear" name="QueryYear" ng-model="QueryYear"
									class="form-control" placeholder="年度" autocomplete="off">
							</div>
						</div>
					</div>

					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button" ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						<button class="btn btn_custom btn_gray" type="button" ng-click="clearData()">
							<i class="fas fa-fw fa-redo-alt"></i>
							<s:message code="btnReset" />
						</button>
					</div>
				</div>
			</form>
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
				
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th><a href="" ng-click="setSortName('maintainInspectId')"> 法遵稽核計畫名稱 <i
									ng-show="sorttype != 'maintainInspectId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'maintainInspectId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'maintainInspectId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('year')">年度 <i
									ng-show="sorttype != 'year'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'year' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'year' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>																		
							<th><a href="" ng-click="setSortName('status')"><s:message
										code="messageStatus" /><i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
									class="fas fa-fw fa-caret-up"></i> </a></th>		
							<th><a href="">稽核對象數量</a></th>
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td>{{item.Title}}</td>
							<td>{{item.Year}}</td>
							<td><span ng-if="item.Status==1">設定完成</span></td>
							<td>{{item.Count}}</td>
							<td class="text-center">
								<a class="btn btn-sm btn-primary" title="查詢稽核對象"
										ng-click="queryHospitalData(item.MaintainInspectId);">
									<i class="fas fa-fw fa-search"></i>
									查詢稽核對象
								</a>
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
	<!-- 查詢法遵稽核計畫 end -->

	<!-- 查詢法遵稽核計畫的稽核對象 start -->
	<div id="divQueryHospital" class="container">
		<div>
		     <div class="form_group">
				<h4 class="form_heading form_heading_fix form_heading_gray">
					<big><i class="fa fa-info-circle"></i></big>
					<b>法遵稽核計畫資料</b>				
				</h4>
			</div>
		</div>
		<div>
		     <div class="form_group">
		         <label class="form_label form_label_search form_label_gray">計畫名稱</label>
		         <span>{{HospitalTitle}}</span>
		     </div>
		 </div>
		 <div>
		     <div class="form_group">
		         <label class="form_label form_label_search form_label_gray">計畫年度</label>
		         <span>{{HospitalYear}}</span>
		     </div>
		 </div>

		<div>
		    <div class="form_group">
		        <h4 class="form_heading form_heading form_heading_yellow">
		            <i class="fa fa-info-circle"><b>稽核對象</b></i>
		        </h4>
		    </div>
		</div>

		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>
							<th>機構名稱</th>
							<th>醫院資料上傳時間(起~訖)</th>
							<th>稽核報告上傳時間(起~訖)</th>
							<th>稽核<s:message code="messageStatus" /></th>		
							<th>是否開放補件/補述</th>
							<th class="func">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allHospitalitems">
							<td>
								<a ng-if="item.InspectStatus!=null && item.InspectStatus!=''"
										data-toggle="modal" data-target="#detailModal"
										ng-click="queryHospitalDetail(item.Id);">
									{{item.OrgName}}
								</a>
								<span ng-if="item.InspectStatus==null || item.InspectStatus==''">{{item.OrgName}}</span>
							</td>
							<td>{{item.HospitalUploadSdate}}~{{item.HospitalUploadEdate}}</td>
							<td>{{item.CommitteeUploadSdate}}~{{item.CommitteeUploadEdate}}</td>
							<td>
								<span ng-if="item.InspectStatus==3">設定稽核基本資料</span>
								<span ng-if="item.InspectStatus==4">進行稽核前訪談調查</span>
								<span ng-if="item.InspectStatus==5">已繳交稽核前訪談調查⽂件</span>
								<span ng-if="item.InspectStatus==6">確定稽核委員名單</span>
								<span ng-if="item.InspectStatus==7">已上傳稽核評分表</span>
								<span ng-if="item.InspectStatus==8">已上傳稽核報告</span>
								<span ng-if="item.InspectStatus==9">已繳交改善報告</span>
							</td>
							<td class="text-center">
								<span ng-show="item.AllowHospitalPatch">
									<i class="far fa-fw fa-check-circle text-success" title="開放"></i>開放
								</span>
								<span ng-if="item.AllowHospitalPatch!=null" ng-show="!item.AllowHospitalPatch">
									<i class="fas fa-fw fa-minus-circle text-danger" title="不開放"></i>不開放
								</span>
							</td>

							<td class="text-left">
								<c:if test="${isAdmin}">
									<a ng-if="item.InspectStatus==null || item.InspectStatus=='' || item.InspectStatus=='3'"
											class="btn btn-sm btn-primary" title="設定稽核基本資料"
											ng-click="editData(item.Id);">
										<i class="fas fa-fw fa-search"></i>
										設定稽核基本資料
									</a>
								
									<a ng-if="item.InspectStatus=='5'"
											class="btn btn-sm btn-primary" title="輸入稽核委員名單"
											ng-click="editCommittee(item.Id);">
										<i class="fas fa-fw fa-search"></i>
										輸入稽核委員名單
									</a>
									<a ng-if="item.InspectStatus=='6'"
										class="btn btn-sm btn-primary" title="開放或關閉補件/補述"
										ng-click="toggleAllowHospitalPatch(item.Id, item.AllowHospitalPatch);">
										<i class="fas fa-fw fa-search"></i>
										開放或關閉補件/補述
									</a>
								</c:if>
								
								<a ng-if="item.InspectStatus=='7'"
										class="btn btn-sm btn-primary" title="上傳稽核報告"
										ng-click="editAuditResult(item.Id);">
									<i class="fas fa-file-upload"></i>
									上傳稽核報告
								</a>


								<c:if test="${isAdmin}">
									<a ng-if="item.InspectStatus=='4'"
											class="btn btn-sm btn-primary" title="退回(重新設定稽核基本資料)"
											ng-click="returnToEditData(item.Id);">
										<i class="fas fa-fw fa-search"></i>
										退回(重新設定稽核基本資料)
									</a>
	
									<a ng-if="item.InspectStatus=='5'"
											class="btn btn-sm btn-primary" title="退回(重新進行稽核前訪談調查)"
											ng-click="returnToHospital(item.Id);">
										<i class="fas fa-fw fa-search"></i>
										退回(重新進行稽核前訪談調查)
									</a>
	
									<a ng-if="item.InspectStatus=='6'"
											class="btn btn-sm btn-primary" title="退回(重新輸入稽核委員名單)"
											ng-click="returnToCommitteeList(item.Id);">
										<i class="fas fa-fw fa-search"></i>
										退回(重新輸入稽核委員名單)
									</a>
	
									<a ng-if="item.InspectStatus=='7'"
											class="btn btn-sm btn-primary" title="退回(重新上傳稽核評分表)"
											ng-click="returnToAuditScoreUpload(item.Id);">
										<i class="fas fa-file-upload"></i>
										退回(重新上傳稽核評分表)
									</a>

									<a ng-if="item.InspectStatus=='8'"
											class="btn btn-sm btn-primary" title="退回(重新上傳稽核報告)"
											ng-click="returnToAuditResultUpload(item.Id);">
										<i class="fas fa-file-upload"></i>
										退回(重新上傳稽核報告)
									</a>

									<a ng-if="item.InspectStatus=='9'"
											class="btn btn-sm btn-primary" title="退回(重新上傳改善報告)"
											ng-click="returnToImprovementUpload(item.Id);">
										<i class="fas fa-file-upload"></i>
										退回(重新上傳改善報告)
									</a>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row tab_block text-center">
				<button class="btn btn_custom btn_gray"
					ng-click="closeQueryHospital()">
					<i class="fas fa-fw fa-undo"></i>
					<s:message code="btnReturn" />
				</button>
			</div>
		</div>
	</div>
	<!-- 查詢法遵稽核計畫的稽核對象 end -->

	<!-- 設定稽核資料、設定稽核委員、上傳稽核報告 end -->
	<div id="divEdit" class="container" ng-show="btnEdit || btnCommittee || btnAuditResult">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-edit fa-lg"></i></big>
				<b>法遵稽核計畫資料<span ng-show="btnEdit"><s:message code="btnEdit" /></span></b>
			</h4>

			<div class="col-xs-12">
				<form name="editForm">
					<div>
						<div class="form_group">
							<label for="Title"
								class="form_label form_label_search form_label_gray">計畫名稱</label>
							<div class="form_input form_input_search">
								<span>{{Title}}</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Year"
								class="form_label form_label_search form_label_gray">計畫年度</label>
							<div class="form_input form_input_search">
								<span>{{Year}}</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="OrgName"
								class="form_label form_label_search form_label_gray">機構名稱</label>
							<div class="form_input form_input_search">
								<span>{{OrgName}}</span>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="HospitalUploadDate"
								class="form_label form_label_search form_label_gray">醫院資料上傳時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="HospitalUploadSdate" name="HospitalUploadSdate"
										ng-model="HospitalUploadSdate" class="form-control" ng-required="true"
										placeholder="上傳時間(起)" ng-disabled="btnCommittee || btnAuditResult"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="HospitalUploadEdate" name="HospitalUploadEdate"
										ng-model="HospitalUploadEdate" class="form-control" ng-required="true"
										placeholder="上傳時間(訖)" ng-disabled="btnCommittee || btnAuditResult"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="CommitteeUploadDate"
								class="form_label form_label_search form_label_gray">稽核報告上傳時間</label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="CommitteeUploadSdate" name="CommitteeUploadSdate"
										ng-model="CommitteeUploadSdate" class="form-control" ng-required="true"
										placeholder="上傳時間(起)" ng-disabled="btnAuditResult"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="CommitteeUploadEdate" name="CommitteeUploadEdate"
										ng-model="CommitteeUploadEdate" class="form-control" ng-required="true"
										placeholder="上傳時間(訖)" ng-disabled="btnAuditResult"
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

					<h4 ng-show="btnCommittee || btnAuditResult"
							class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>稽核委員資料</b></i>
					</h4>
					<div ng-show="btnCommittee || btnAuditResult">
						<div class="form_group">
							<label for="Committee"
								class="form_label form_label_search form_label_gray">稽核委員</label>
							<div class="form_input form_input_half" ng-show="btnCommittee">
								<div class="input-group">
									<select id="Committee" name="Committee" ng-model="Committee"
											class="form-control"
											ng-options="committee as committee.Name for committee in committees">
										<option value="" selected>請選擇稽核委員</option>
									</select>
								</div>
							</div>
							<div class="form_input form_input_half" ng-show="btnCommittee">
								<div class="input-group">
									<button class="btn btn-primary" ng-click="addCommittee()">
										<i class="fas fa-fw fa-plus"></i>
										新增一位稽核委員
									</button>
								</div>
							</div>
							<div class="form_input form_input_search">
								<table class="table table-striped table-bordered table-hover table_customer table_gray">
									<tbody>
										<tr ng-repeat="item in CommitteeList">
											<td>{{item.Name}}
												<i class="fas fa-times" ng-click="deleteCommittee(item.CommitteeMemberId)" ng-if="btnCommittee"></i>
											</td>
										</tr>																												
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div>
						<div class="form_group"></div>
					</div>

					<div ng-show="btnAuditResult">
						<h4 class="form_heading form_heading form_heading_blue">
							<i class="fa fa-info-circle"><b>稽核報告資料</b></i>
						</h4>
						<div>
		                    <div class="form_group">
		                        <label class="form_label form_label_search form_label_gray">稽核報告摘要</label>
		                        <div class="form_input form_input_search">
									<input type="text" id="AuditResultDesc" name="AuditResultDesc"
										ng-model="AuditResultDesc" class="form-control"
										placeholder="稽核報告摘要"
										autocomplete="off">
		                        </div>
		                    </div>
		                </div>
						<div>
		                    <div class="form-group">
								<label for="AuditResultFile" class="form_label form_label_search form_label_gray">稽核報告上傳</label>
		                        <div class="form_input form_input_search" style="display: inline-block"
		                        		id="AuditResultFile" name="AuditResultFile" ng-model="AuditResultFile"
		                        		accept=".doc,.docx" ngf-select="" ngf-pattern=".doc,.docx" ngf-max-size="100MB" ngf-min-height="100">
		                            <button id="SelectAttachment" class="btn btn-primary" ng-click="changeAuditResultFile()">
		                                <i class="fas fa-fw fa-file fa-lg"></i>
		                                <s:message code="pleaseSelect" />
		                                <s:message code="messageAttachment" />
		                            </button>
		                            <span>{{AuditResultFile.name}}</span>
		                            <button class="btn btn-danger" ng-show="AuditResultFile.name!=null"
		                            		ng-click="deleteAuditResultFile()">
		                                <i class="far fa-fw fa-trash-alt"></i>
		                                <s:message code="btnDelete" />
		                            </button>
		                        </div>
		                    </div>
		                </div>
					</div>
					
					<div>
						<div class="form_group"></div>
					</div>
					<br><br><br>								
					<div class="help-block"></div>
					<!-- Button start -->
					<div class="row tab_block text-center">
						<div ng-show="btnEdit">
							<button class="btn btn_custom btn_blue"
								ng-click="updateData(false,false)">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSave" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="updateData(true,false)">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSaveAndExit" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="!editForm.$valid || updateData(true,true)"
								ng-disabled="!editForm.$valid">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnSubmit" />
							</button>
							<button class="btn btn_custom btn_gray"
								ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>
						
						<div ng-show="btnCommittee">
							<button class="btn btn_custom btn_blue"
								ng-click="committeeData(false,false)">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSave" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="committeeData(true,false)">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSaveAndExit" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="!editForm.$valid || committeeData(true,true)"
								ng-disabled="!editForm.$valid">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnSubmit" />
							</button>
							<button class="btn btn_custom btn_gray"
								ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>

						<div ng-show="btnAuditResult">
							<button class="btn btn_custom btn_blue"
								ng-click="auditResultData(false,false)"
								ng-disabled="AuditResultFile.name==null || AuditResultFile.name=='' || AuditResultDesc==null || AuditResultDesc==''">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSave" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-click="auditResultData(true,false)"
								ng-disabled="AuditResultFile.name==null || AuditResultFile.name=='' || AuditResultDesc==null || AuditResultDesc==''">
								<i class="fas fa-fw fa-save"></i>
								<s:message code="btnTempSaveAndExit" />
							</button>
							<button class="btn btn_custom btn_blue"
									ng-click="!editForm.$valid || auditResultData(true,true)"
									ng-disabled="AuditResultFile.name==null || AuditResultFile.name=='' || AuditResultDesc==null || AuditResultDesc==''">
			                    <i class="fas fa-fw fa-check"></i>
			                    <s:message code="btnSubmit" />
			                </button>							
							<button class="btn btn_custom btn_gray"
								ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>
					</div>
					<!-- Button end -->
				</form>
			</div>
		</div>
	</div>
	<!-- 設定稽核資料、設定稽核委員、上傳稽核報告 end -->

	<!-- detail modal start -->
	<div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
				aria-labelledby="detailModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" id="modalDialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div style="text-align:center;">				
						<strong>{{DetailOrgName}}</strong>
					</div>
					<button type="button" class="close" data-dismiss="modal"
							aria-label="Close" aria-hidden="true">
						&times;
					</button>
				</div>
				<div class="modal-body">
				
					<!-- 資安稽核基本資料 start -->				
					<table class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="5"><span>資安稽核基本資料</span></th>
							</tr>
							<tr>
								<th><span>醫院資料上傳時間</span></th>
								<th><span>稽核報告上傳時間</span></th>
								<th><span>稽核<s:message code="messageStatus" /></span></th>
								<th><span>是否開放補件/補述</span></th>								
								<th><span>稽核委員資料</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">
									<span>{{DetailHospitalUploadSdate}}~{{DetailHospitalUploadEdate}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCommitteeUploadSdate}}~{{DetailCommitteeUploadEdate}}</span>
								</td>
								<td>
									<span ng-if="DetailInspectStatus==3">設定稽核基本資料</span>								
									<span ng-if="DetailInspectStatus==4">進行稽核前訪談調查</span>
									<span ng-if="DetailInspectStatus==5">已繳交稽核前訪談調查⽂件</span>
									<span ng-if="DetailInspectStatus==6">確定稽核委員名單</span>			
									<span ng-if="DetailInspectStatus==7">已上傳稽核評分表</span>				
									<span ng-if="DetailInspectStatus==8">已上傳稽核報告</span>
									<span ng-if="DetailInspectStatus==9">已繳交改善報告</span>											
								</td>
								<td class="text-center">
									<span ng-show="DetailAllowHospitalPatch">
										<i class="far fa-fw fa-check-circle text-success" title="開放"></i>開放
									</span>
									<span ng-if="DetailAllowHospitalPatch!=null" ng-show="!DetailAllowHospitalPatch">
										<i class="fas fa-fw fa-minus-circle text-danger" title="不開放"></i>不開放
									</span>
								</td>
								<td class="text-center">
									<div ng-repeat="item in DetailCommitteeList">{{item.Name}}</div>
								</td>
							</tr>																												
						</tbody>
					</table>
					<!-- 資安稽核基本資料 end -->
					
					<!-- 資安稽核前訪談調查文件範本 start -->				
					<table class="table table-striped table-bordered table-hover table_customer table_gray"
							ng-show="DetailCheckListSampleAttach || DetailQuestionnaireSampleAttach || DetailSelfEvaluationSampleAttach">
						<thead>
							<tr>
								<th colspan="5"><span>資安稽核前訪談調查文件範本 </span></th>
							</tr>
							<tr>
								<th><span>檔案類型</span></th>
								<th><span>檔案名稱</span></th>
								<th><span>檔案大小</span></th>
								<th><span>上傳時間</span></th>	
								<th class="func">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-show="DetailCheckListSampleAttach">
								<td class="text-center">
									<span>檢核表</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListSampleAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListSampleAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListSampleAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailCheckListSampleAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							<tr ng-show="DetailQuestionnaireSampleAttach">
								<td class="text-center">
									<span>調查表</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireSampleAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireSampleAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireSampleAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailQuestionnaireSampleAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							
							<tr ng-show="DetailSelfEvaluationSampleAttach">
								<td class="text-center">
									<span>技術檢測自評表</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationSampleAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationSampleAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationSampleAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailSelfEvaluationSampleAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							
						</tbody>
					</table>
					<!-- 資安稽核前訪談調查文件範本  end -->
					
					
					<!-- 稽核前訪談調查⽂件 start -->
					<table class="table table-striped table-bordered table-hover table_customer table_gray"
							ng-show="DetailCheckListAttach || DetailQuestionnaireAttach || DetailSelfEvaluationAttach || DetailOtherAttach">
						<thead>
							<tr>
								<th colspan="6"><span>稽核前訪談調查⽂件</span></th>
							</tr>
							<tr>
								<th><span>檔案類型</span></th>
								<th><span>檔案名稱</span></th>
								<th><span>檔案大小</span></th>
								<th><span>上傳時間</span></th>								
								<th><span>檔案描述</span></th>
								<th class="func">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-show="DetailCheckListAttach">
								<td class="text-center">
									<span>檢核表</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailCheckListAttach.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailCheckListAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							<tr ng-show="DetailQuestionnaireAttach">
								<td class="text-center">
									<span>調查表</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailQuestionnaireAttach.FileTime}}</span>
								</td>								
								<td class="text-center">
									<span>{{DetailQuestionnaireAttach.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailQuestionnaireAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							
							<tr ng-show="DetailSelfEvaluationAttach">
								<td class="text-center">
									<span>技術檢測自評表</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailSelfEvaluationAttach.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailSelfEvaluationAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
							
							<tr ng-show="DetailOtherAttach" ng-repeat="item in DetailOtherAttach">
								<td class="text-center">
									<span>其他</span>
								</td>
								<td class="text-center">
									<span>{{item.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{item.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{item.FileTime}}</span>
								</td>
								<td class="text-center">
									<span>{{item.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{item.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>								
							</tr>
						</tbody>
					</table>
					<!-- 稽核前訪談調查⽂件 end -->

					<!-- 稽核評分表 start -->
					<table ng-show="DetailScoreAttach" class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span>稽核評分表</span></th>
							</tr>
							<tr>
								<th width="20%"><span>稽核委員</span></th>
<!--								
								<th width="30%">稽核評分項目與分數</th>
-->								
								<th width="80%"><span>檔案</span></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in DetailScoreAttach">
								<td class="text-center">
									<span>{{item.CommitteeName}}</span>
								</td>
<!--								
								<td class="text-left">
									<table ng-show="item.FileDesc" class="table table-striped table-bordered table-hover table_customer table_gray">
										<thead>
											<tr>
												<th width="80%"><span>評分項目</span></th>
												<th width="20%"><span>分數</span></th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="desc in item.FileDesc">
												<td class="text-center">
													<span>{{desc.AuditScoreName}}</span>
												</td>
												<td class="text-center">
													<span>{{desc.AuditScoreScore}}</span>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
-->								
								<td class="text-left">
									<table ng-show="item.FileDesc" class="table table-striped table-bordered table-hover table_customer table_gray">
										<thead>
											<tr>
												<th width="20%"><span>檔案類型</span></th>
												<th width="55"><span>檔案名稱</span></th>
												<th width="20%"><span>檔案大小</span></th>
												<th width="5%" class="func">&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="text-center">
													<span>稽核發現暨評分表</span>
												</td>
												<td class="text-center">
													<span>{{item.FileName}}</span>
												</td>
												<td class="text-center">
													<span>{{item.FileSize}}</span>
												</td>							
												<td class="text-center">
													<a ng-if="item.FileName != null" class="btn btn-sm btn-primary" title="下載"
															ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{item.FileLink}}">
														<i class="fas fa-fw fa-search"></i>
														下載
													</a>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<span>書審意見</span>
												</td>
												<td class="text-center">
													<span>{{item.FileReviewOpinionName}}</span>
												</td>
												<td class="text-center">
													<span>{{item.FileReviewOpinionSize}}</span>
												</td>							
												<td class="text-center">
													<a ng-if="item.FileReviewOpinionName != null"  class="btn btn-sm btn-primary" title="下載"
															ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{item.FileReviewOpinionLink}}">
														<i class="fas fa-fw fa-search"></i>
														下載
													</a>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 稽核評分表 end -->

					<!-- 稽核報告 start -->
					<table ng-show="DetailResultAttach" class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="5"><span>稽核報告</span></th>
							</tr>
							<tr>
								<th><span>檔案名稱</span></th>
								<th><span>檔案大小</span></th>
								<th><span>上傳時間</span></th>
								<th><span>檔案描述</span></th>
								<th class="func">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">
									<span>{{DetailResultAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailResultAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailResultAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailResultAttach.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailResultAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 稽核報告 end -->

					<!-- 改善報告 start -->
					<table ng-show="DetailImprovementAttach" class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="5"><span>改善報告</span></th>
							</tr>
							<tr>
								<th><span>檔案名稱</span></th>
								<th><span>檔案大小</span></th>
								<th><span>上傳時間</span></th>
								<th><span>檔案描述</span></th>
								<th class="func">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">
									<span>{{DetailImprovementAttach.FileName}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailImprovementAttach.FileSize}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailImprovementAttach.FileTime}}</span>
								</td>
								<td class="text-center">
									<span>{{DetailImprovementAttach.FileDesc}}</span>
								</td>
								<td class="text-center">
									<a class="btn btn-sm btn-primary" title="下載"
											ng-href="<c:out value="${pageContext.request.contextPath}" />/kin/api/k01/downloadAttach/{{DetailImprovementAttach.FileLink}}">
										<i class="fas fa-fw fa-search"></i>
										下載
									</a>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 改善報告 end -->

					<!-- ProcessLog start -->
					<table ng-show="DetailProcessLog" class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="4"><span>流程紀錄</span></th>
							</tr>
						    <tr>
						        <th width="40%"><s:message code="globalProcessLogAction" /></th>
								<th width="20%"><s:message code="globalProcessLogStatus" /></th>
								<th width="20%"><s:message code="globalProcessLogSednTime" /></th>
								<th width="20%"><s:message code="globalProcessLogFrom" /></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in DetailProcessLog">
								<td>
									{{item.PreStatus==3?"設定稽核基本資料":""}}
									{{item.PreStatus==4?"進行稽核前訪談調查":""}}
									{{item.PreStatus==5?"已繳交稽核前訪談調查⽂件":""}}
									{{item.PreStatus==6?"確定稽核委員名單":""}}
									{{item.PreStatus==7?"已上傳稽核評分表":""}}
									{{item.PreStatus==8?"已上傳稽核報告":""}}
									{{item.PreStatus==9?"已繳交改善報告":""}}								
									<i class="fas fa-fw fa-arrow-right"></i>
									{{item.Status==3?"設定稽核基本資料":""}}
									{{item.Status==4?"完成稽核基本資料設定":""}}
									{{item.Status==5?"上傳稽核前訪談調查⽂件":""}}
									{{item.Status==6?"輸入稽核委員名單":""}}
									{{item.Status==7?"上傳稽核評分表":""}}
									{{item.Status==8?"上傳稽核報告":""}}
									{{item.Status==9?"繳交改善報告":""}}
								</td>
								<td>	
									<span ng-if="item.Status==3">設定稽核基本資料</span>
									<span ng-if="item.Status==4">進行稽核前訪談調查</span>
									<span ng-if="item.Status==5">已繳交稽核前訪談調查⽂件</span>
									<span ng-if="item.Status==6">確定稽核委員名單</span>			
									<span ng-if="item.Status==7">已上傳稽核評分表</span>				
									<span ng-if="item.Status==8">已上傳稽核報告</span>
									<span ng-if="item.Status==9">已繳交改善報告</span>										
								</td>
								<td>{{item.CreateTime}}</td>
								<td>{{item.CreateName}}</td>
							</tr>
						</tbody>
					</table>
					<!-- ProcessLog end -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>
	<!-- detail modal start -->

	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>