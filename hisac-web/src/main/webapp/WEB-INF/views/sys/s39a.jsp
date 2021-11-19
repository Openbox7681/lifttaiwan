<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s39a.js"></script>
<script>
	var globalAcceptAttachmentFormat = '<s:message code="globalAcceptAttachmentFormat" />';
	var globalUploadFileFail = '<s:message code="globalUploadFileFail" />';
	
	var globalSureToMalware = '<s:message code="globalSureToMalware" />';

</script>
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
							<label for="QueryKeyName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39QueryKeyName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyName" name="QueryKeyName"
									ng-model="QueryKeyName" class="form-control"
									placeholder="<s:message code="s39QueryKeyName" />"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIncidentDiscoveryTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentDiscoveryTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="QueryIncidentDiscoveryTime"
										name="QueryIncidentDiscoveryTime"
										ng-model="QueryIncidentDiscoveryTime" class="form-control"
										placeholder="<s:message code="s39IncidentDiscoveryTime" />"
										autocomplete="off"> <span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIncidentReportedTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentReportedTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="QueryIncidentReportedTime"
										name="QueryIncidentReportedTime"
										ng-model="QueryIncidentReportedTime" class="form-control"
										placeholder="<s:message code="s39IncidentReportedTime" />"
										autocomplete="off"> <span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryEventTypeCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39EventTypeCode" /></label>
							<div class="form_input form_input_search">
								<select id="QueryEventTypeCode" name="QueryEventTypeCode"
									ng-model="QueryEventTypeCode" class="form-control col-xs-6"
									ng-options="eventType.Code as eventType.Code+'-'+eventType.Name for eventType in eventTypes">
									<option value="" selected><s:message code="all" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryImpactQualification"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ImpactQualification" /></label>
							<div class="form_input form_input_search">
								<select id="QueryImpactQualification"
									name="QueryImpactQualification"
									ng-model="QueryImpactQualification"
									class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1"><s:message
											code="s39ImpactQualificationOpt1" /></option>
									<option value="2"><s:message
											code="s39ImpactQualificationOpt2" /></option>
									<option value="3"><s:message
											code="s39ImpactQualificationOpt3" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>
							<div class="form_input form_input_search">
								<select id="QueryIsEnable" name="QueryIsEnable"
									ng-model="QueryIsEnable" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true"><s:message code="isEnableTrue" /></option>
									<option value="false"><s:message code="isEanbleFalse" /></option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryIsMedical"
								class="form_label form_label_search form_label_gray">是否醫療相關</label>
							<div class="form_input form_input_search">
								<select id="QueryIsMedical" name="QueryIsMedical"
									ng-model="QueryIsMedical" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="true">是</option>
									<option value="false">否</option>
								</select>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="QueryStatus"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39Status" /></label>
							<div class="form_input form_input_search">
								<select id="QueryStatus" name="QueryStatus"
									ng-model="QueryStatus" class="form-control col-xs-6">
									<option value="" selected><s:message code="all" /></option>
									<option value="1"><s:message code="s39StatusOpt1" /></option>
									<option value="2"><s:message code="s39StatusOpt2" /></option>
									<option value="3"><s:message code="s39StatusOpt3" /></option>
									<option value="4"><s:message code="s39StatusOpt4" /></option>
									<option value="5"><s:message code="s39StatusOpt5" /></option>
									<!-- <option value="6"><s:message code="s39StatusOpt6" /></option> -->
								</select>
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
							<th><a href="" ng-click="setSortName('postId')"> <s:message
										code="s39PostId" /> <i ng-show="sorttype != 'postId'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'postId' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'postId' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('incidentTitle')">
									<s:message code="s39IncidentTitle" /> <i
									ng-show="sorttype != 'incidentTitle'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'incidentTitle' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'incidentTitle' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href=""
								ng-click="setSortName('incidentDiscoveryTime')"> <s:message
										code="s39IncidentDiscoveryTime" /> <i
									ng-show="sorttype != 'incidentDiscoveryTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'incidentDiscoveryTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'incidentDiscoveryTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('eventTypeName')">
									<s:message code="s39EventTypeName" /> <i
									ng-show="sorttype != 'eventTypeName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'eventTypeName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'eventTypeName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('reporterName')"> <s:message
										code="s39ReporterName" /> <i
									ng-show="sorttype != 'reporterName'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'reporterName' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'reporterName' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('impactQualification')">
									<s:message code="s39ImpactQualification" /> <i
									ng-show="sorttype != 'impactQualification'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'impactQualification' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'impactQualification' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isEnable')"> <s:message
										code="isEnable" /> <i ng-show="sorttype != 'isEnable'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isEnable' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isEnable' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('isMedical')">是否醫療相關<i ng-show="sorttype != 'isMedical'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'isMedical' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'isMedical' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>
							<th><a href="" ng-click="setSortName('status')"> <s:message
										code="s39Status" /> <i ng-show="sorttype != 'status'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'status' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'status' && sortreverse"
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
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in allitems">
							<td><a href="" ng-click="review(item.Id, 'view')"
								role="button"> <i class="fas fa-file-alt fa-lg fa-pull-left"></i>
									{{item.PostId}}
							</a></td>
							<td>{{item.IncidentTitle}}</td>
							<td>{{item.IncidentDiscoveryTime}}</td>
							<td>{{item.EventTypeName}}</td>
							<td>{{item.ReporterName}}</td>
							<td class="text-center"><span
								ng-show="{{item.ImpactQualification}} == '1'"><s:message
										code="s39ImpactQualificationOpt1" /></span> <span
								ng-show="{{item.ImpactQualification}} == '2'"><s:message
										code="s39ImpactQualificationOpt2" /></span> <span
								ng-show="{{item.ImpactQualification}} == '3'"><s:message
										code="s39ImpactQualificationOpt3" /></span></td>
							<td class="text-center"><span ng-show="{{item.IsEnable}}">
									<i class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" />
							</span> <span ng-show="{{!item.IsEnable}}"> <i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" />
							</span></td>
							<td class="text-center"><span ng-show="{{item.IsMedical}}">
									<i class="far fa-fw fa-check-circle text-success"
									title='是'></i>是
							</span> <span ng-show="{{!item.IsMedical}}"> <i
									class="fas fa-fw fa-minus-circle text-danger"
									title='否'></i>否
							</span></td>
							<td class="text-center"><span
								ng-show="{{item.Status}} == '1'"><s:message
										code="s39StatusOpt1" /></span> <span
								ng-show="{{item.Status}} == '2'"><s:message
										code="s39StatusOpt2" /></span> <span
								ng-show="{{item.Status}} == '3'"><s:message
										code="s39StatusOpt3" /></span> <span
								ng-show="{{item.Status}} == '4'"><s:message
										code="s39StatusOpt4" /></span> <span
								ng-show="{{item.Status}} == '5'"><s:message
										code="s39StatusOpt5" /></span> <span
								ng-show="{{item.Status}} == '6'"><s:message
										code="s39StatusOpt6" /></span></td>
							<td>{{item.Sort}}</td>
							<td class="text-center"><a class="btn btn-sm btn-primary"
								title='<s:message code="btnEdit" />' ng-click="edit(item.Id);"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnEdit" /> </a> <a
								href="#" class="btn btn-sm btn-info"
								title='<s:message code="btnDelete" />'
								ng-click="deleteData(item.Id);"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
							</td>
							
							<td class="text-center">
							
							<a class="btn btn-sm btn-primary"
							ng-show= "item.IsButtonMalware && !item.IsMalware "
								title='<s:message code="btnToMalware" />' ng-click="toMalware(item.Id);"><i
									class="fas fa-fw fa-edit"></i> <s:message code="btnToMalware" /> 
									</a> 
									
							<span ng-show="item.IsMalware">已轉至勒索專區</span>
									
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
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="PostId"
										class="form_label form_label_search form_label_gray"><s:message
											code="s39PostId" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="PostId == '' || PostId == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="PostId != '' && PostId != null">{{PostId}}</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IncidentId"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentId" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="IncidentId" name="IncidentId"
									ng-model="IncidentId" class="form-control"
									placeholder="<s:message code="s39IncidentId" />"
									autocomplete="off" ng-maxlength="128" />
								<!-- <h5 class="text-danger"
									ng-show="editForm.IncidentId.$error.required && editForm.IncidentId.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39IncidentId" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.IncidentId.$error.maxlength && editForm.IncidentId.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IncidentTitle"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentTitle" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="IncidentTitle" name="IncidentTitle"
									ng-model="IncidentTitle" class="form-control"
									placeholder="<s:message code="s39IncidentTitle" />"
									autocomplete="off" ng-required="true" />
								<h5 class="text-danger"
									ng-show="editForm.IncidentTitle.$error.required && editForm.IncidentTitle.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39IncidentTitle" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IncidentDiscoveryTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentDiscoveryTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="IncidentDiscoveryTime"
										name="IncidentDiscoveryTime" ng-model="IncidentDiscoveryTime"
										class="form-control"
										placeholder="<s:message code="s39IncidentDiscoveryTime" />"
										autocomplete="off" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
								<h5 class="text-danger"
									ng-show="editForm.IncidentDiscoveryTime.$error.required && editForm.IncidentDiscoveryTime.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39IncidentDiscoveryTime" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IncidentReportedTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentReportedTime" /></label>
							<div class="form_input form_input_search">
								<div class="input-group">
									<input type="text" id="IncidentReportedTime"
										name="IncidentReportedTime" ng-model="IncidentReportedTime"
										class="form-control"
										placeholder="<s:message code="s39IncidentReportedTime" />"
										autocomplete="off" ng-required="true"> <span
										class="input-group-addon"> <i
										class="fas fa-calendar-alt"></i>
									</span>
								</div>
								<h5 class="text-danger"
									ng-show="editForm.IncidentReportedTime.$error.required && editForm.IncidentReportedTime.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39IncidentReportedTime" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Description"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39Description" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="Description" name="Description"
									ng-model="Description" class="form-control"
									placeholder="<s:message code="s39Description" />" rows="10"
									autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="EventTypeCode"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39EventTypeCode" /></label>
							<div class="form_input form_input_search">
								<select id="EventTypeCode" name="EventTypeCode"
									ng-model="EventTypeCode" class="form-control col-xs-6"
									ng-options="eventType.Code as eventType.Code+'-'+eventType.Name for eventType in eventTypes"
									ng-required="true">
									<option value="" selected>--
										<s:message code="pleaseSelect" /><s:message
											code="s39EventTypeCode" />--
									</option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.EventTypeCode.$error.required && editForm.EventTypeCode.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="s39EventTypeCode" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ReporterName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ReporterName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ReporterName" name="ReporterName"
									ng-model="ReporterName" class="form-control"
									placeholder="<s:message code="s39ReporterName" />"
									autocomplete="off" ng-required="true" ng-maxlength="256" />
								<h5 class="text-danger"
									ng-show="editForm.ReporterName.$error.required && editForm.ReporterName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39ReporterName" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ReporterName.$error.maxlength && editForm.ReporterName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="256" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ResponderPartyName"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderPartyName" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ResponderPartyName"
									name="ResponderPartyName" ng-model="ResponderPartyName"
									class="form-control"
									placeholder="<s:message code="s39ResponderPartyName" />"
									autocomplete="off" ng-maxlength="64" />
								<!-- <h5 class="text-danger"
									ng-show="editForm.ResponderPartyName.$error.required && editForm.ResponderPartyName.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39ResponderPartyName" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ResponderPartyName.$error.maxlength && editForm.ResponderPartyName.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="64" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ResponderContactNumbers"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderContactNumbers" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ResponderContactNumbers"
									name="ResponderContactNumbers"
									ng-model="ResponderContactNumbers" class="form-control"
									placeholder="<s:message code="s39ResponderContactNumbers" />"
									autocomplete="off" ng-maxlength="64"
									ng-pattern="/^0\d{1,2}-\d{6,10}/" />
								<!-- <h5 class="text-danger"
									ng-show="editForm.ResponderContactNumbers.$error.required && editForm.ResponderContactNumbers.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39ResponderContactNumbers" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ResponderContactNumbers.$error.maxlength && editForm.ResponderContactNumbers.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="64" />
								</h5>
								<h5 class="validation-msg"
									ng-show="editForm.ResponderContactNumbers.$invalid && editForm.ResponderContactNumbers.$dirty">
									<s:message code="mustInteger" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ResponderElectronicAddressIdentifiers"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderElectronicAddressIdentifiers" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="ResponderElectronicAddressIdentifiers"
									name="ResponderElectronicAddressIdentifiers"
									ng-model="ResponderElectronicAddressIdentifiers"
									class="form-control"
									placeholder="<s:message code="s39ResponderElectronicAddressIdentifiers" />"
									autocomplete="off" ng-maxlength="128"
									ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i" />
								<!-- <h5 class="text-danger"
									ng-show="editForm.ResponderElectronicAddressIdentifiers.$error.required && editForm.ResponderElectronicAddressIdentifiers.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39ResponderElectronicAddressIdentifiers" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.ResponderElectronicAddressIdentifiers.$error.maxlength && editForm.ResponderElectronicAddressIdentifiers.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5>
								<h5 class="text-danger"
									ng-show="!editForm.ResponderElectronicAddressIdentifiers.$error.required && editForm.ResponderElectronicAddressIdentifiers.$invalid && editForm.ResponderElectronicAddressIdentifiers.$dirty">
									<s:message code="memberEmailFormat" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="ImpactQualification"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39ImpactQualification" /></label>
							<div class="form_input form_input_search">
								<select id="ImpactQualification" name="ImpactQualification"
									ng-model="ImpactQualification" class="form-control col-xs-6"
									ng-required="true">
									<option value="" ng-selected="!ImpactQualification">--
										<s:message code="pleaseSelect" /><s:message
											code="s39ImpactQualification" />--
									</option>
									<option ng-value="1" ng-selected="ImpactQualification == '1'"><s:message
											code="s39ImpactQualificationOpt1" /></option>
									<option ng-value="2" ng-selected="ImpactQualification == '2'"><s:message
											code="s39ImpactQualificationOpt2" /></option>
									<option ng-value="3" ng-selected="ImpactQualification == '3'"><s:message
											code="s39ImpactQualificationOpt3" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.ImpactQualification.$error.required && editForm.ImpactQualification.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="s39ImpactQualification" />
								</h5>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="CoaDescription"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39CoaDescription" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="CoaDescription" name="CoaDescription"
									ng-model="CoaDescription" class="form-control"
									placeholder="<s:message code="s39CoaDescription" />" rows="10"
									autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Confidence"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39Confidence" /></label>
							<div class="form_input form_input_search">
								<input type="text" id="Confidence" name="Confidence"
									ng-model="Confidence" class="form-control"
									placeholder="<s:message code="s39Confidence" />"
									autocomplete="off" ng-maxlength="128" />
								<!-- <h5 class="text-danger"
									ng-show="editForm.Confidence.$error.required && editForm.Confidence.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39Confidence" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.Confidence.$error.maxlength && editForm.Confidence.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="Reference"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39Reference" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="Reference" name="Reference"
									ng-model="Reference" class="form-control"
									placeholder="<s:message code="s39Reference" />" rows="10"
									autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="AffectedSoftwareDescription"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39AffectedSoftwareDescription" /></label>
							<div class="form_input form_input_search">
								<textarea type="text" id="AffectedSoftwareDescription" name="AffectedSoftwareDescription"
									ng-model="AffectedSoftwareDescription" class="form-control"
									placeholder="<s:message code="s39AffectedSoftwareDescription" />" rows="10"
									autocomplete="off"></textarea>
								<!-- <h5 class="text-danger"
									ng-show="editForm.AffectedSoftwareDescription.$error.required && editForm.AffectedSoftwareDescription.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="s39AffectedSoftwareDescription" />
								</h5>
								<h5 class="text-danger"
									ng-show="editForm.AffectedSoftwareDescription.$error.maxlength && editForm.AffectedSoftwareDescription.$dirty">
									<s:message code="formatMaxLengthAfter" arguments="128" />
								</h5> -->
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="RangeDateTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39StartDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="StartDateTime" name="StartDateTime"
										ng-model="StartDateTime" class="form-control"
										placeholder="<s:message code="s39StartDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="EndDateTime" name="EndDateTime"
										ng-model="EndDateTime" class="form-control"
										placeholder="<s:message code="s39EndDateTime" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsEnable"
								class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsEnable" ng-init="IsEnable=true"
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="isEnableTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="isEanbleFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="IsMedical"
								class="form_label form_label_search form_label_gray">
								是否醫療相關</label>
							<div class="form_input form_input_search_half">
								<toggle ng-model="IsMedical" ng-init="IsMedical=true"
									on='<i class="far fa-fw fa-check-circle"></i>是'
									off='<i class="fas fa-fw fa-minus-circle"></i>否'
									onstyle="btn-success" offstyle="btn-danger"></toggle>
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
					<!-- <div>
						<div class="form_group">
							<label for="Status"
								class="form_label form_label_search form_label_gray"><s:message
									code="s39Status" /></label>
							<div class="form_input form_input_search">
								<select id="Status" name="Status"
									ng-model="Status" class="form-control col-xs-6"
									ng-required="true">
									<option value="" ng-selected="!Status">--<s:message code="pleaseSelect" /><s:message code="s39Status" />--</option>
									<option ng-value="1" ng-selected="Status == '1'"><s:message code="s39StatusOpt1" /></option>
									<option ng-value="2" ng-selected="Status == '2'"><s:message code="s39StatusOpt2" /></option>
									<option ng-value="3" ng-selected="Status == '3'"><s:message code="s39StatusOpt3" /></option>
									<option ng-value="4" ng-selected="Status == '4'"><s:message code="s39StatusOpt4" /></option>
									<option ng-value="5" ng-selected="Status == '5'"><s:message code="s39StatusOpt5" /></option>
									<option ng-value="6" ng-selected="Status == '6'"><s:message code="s39StatusOpt6" /></option>
								</select>
								<h5 class="text-danger"
									ng-show="editForm.Status.$error.required && editForm.Status.$dirty">
									<s:message code="pleaseSelect" />
									<s:message code="s39Status" />
								</h5>
							</div>
						</div>
					</div> -->
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="form_group">
									<label for="Status"
										class="form_label form_label_search form_label_gray"><s:message
											code="s39Status" /></label> <span
										class="form-text form_text form_input_search text-danger"
										ng-if="Status == '' || Status == null"><s:message
											code="globalAutoGenerate" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 1"><s:message
											code="s39StatusOpt1" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 2"><s:message
											code="s39StatusOpt2" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 3"><s:message
											code="s39StatusOpt3" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 4"><s:message
											code="s39StatusOpt4" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 5"><s:message
											code="s39StatusOpt5" /></span> <span
										class="form-text form_text form_input_search"
										ng-if="Status != '' && Status != null && Status == 6"><s:message
											code="s39StatusOpt6" /></span>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue"
							ng-click="!editForm.$valid || updateData(false, false)"
							ng-disabled="!editForm.$valid" ng-show="btnUpd">
							<i class="fas fa-fw fa-check"></i>
							<s:message code="btnSubmit" />
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
		<div class="row" ng-show="btnUpd">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-plus-circle fa-lg"></i></big><b><s:message
						code="btnUploadAttachment" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div>
					<div class="form-group">
						<label for="FileAttachment"
							class="form_label form_label_search form_label_gray"><s:message
								code="s39Attachment" /></label>
						<div class="form_input form_input_search"
							style="display: inline-block" ngf-select="" id="FileAttachment"
							ng-model="FileAttachment" name="FileAttachment"
							ngf-pattern=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							accept=".jpg,.gif,.pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
							ngf-max-size="100MB" ngf-min-height="100">
							<button id="SelectAttachment" class="btn btn-primary">
								<i class="fas fa-fw fa-file fa-lg"></i>
								<s:message code="pleaseSelect" />
								<s:message code="s39Attachment" />
							</button>
							<span>{{FileAttachment.name}}</span>
						</div>
					</div>
				</div>
				<div>
					<div class="form_group">
						<label for="AttachmentDescription"
							class="form_label form_label_search form_label_gray"><s:message
								code="globalAttachmentDescription" /></label>
						<div class="form_input form_input_search">
							<textarea type="text" id="AttachmentDescription"
								name="AttachmentDescription" ng-model="AttachmentDescription"
								class="form-control"
								placeholder="<s:message code="globalAttachmentDescription" />"
								rows="5" autocomplete="off"></textarea>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="uploadAttachment()" ng-disabled="FileAttachment==null"
							ng-show="${actionCreate}">
							<i class="fas fa-fw fa-upload"></i>
							<s:message code="btnUploadAttachment" />
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row" ng-show="btnUpd && itemAttachments.length > 0">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-paperclip fa-lg"></i></big><b><s:message
						code="globalAttachmentList" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<div class="help-block"></div>
				<div>
					<div class="col-xs-12 col-sm-6 col-md-4"
						ng-repeat="item in itemAttachments">
						<div class="thumbnail">
							<div class="caption">
								<p>
									<s:message code="globalAttachmentDescription" />
									: {{item.FileDesc}}
								</p>
								<p>
									<s:message code="globalAttachmentName" />
									: <a
										href="./api/s39/attach/download/{{item.AnaManagementId}}/{{item.Id}}"
										target="_blank">{{item.FileName}}</a>
								</p>
								<p>
									<s:message code="globalAttachmentSize" />
									: {{item.FileSize}}
								</p>
								<p>
									<s:message code="globalHashSHA256" />
									:
								</p>
								<div class="input-group">
									<input type="text" class="form-control"
										value="{{item.FileHash}}"> <span
										class="input-group-addon"
										onclick="$(this).prev().select(); document.execCommand('Copy')"><i
										class="fas fa-fw fa-copy"></i></span>
								</div>
							</div>
							<div class="caption">
								<p ng-show="${actionDelete}" class="text-center">
									<button class="btn btn_custom btn_blue" type="button"
										ng-click="deleteAttachmentData(item.Id);"
										ng-show="${actionDelete}">
										<i class="far fa-fw fa-trash-alt"></i>
										<s:message code="btnDelete" />
									</button>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="submit_bar"></div>
			</div>
		</div>
	</div>

	<div id="divReview" class="container">
		<div class="row">
			<div class="col-xs-12 shadow_board">
				<form class="form-horizontal" role="form" name="myForm">

					<div>
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="btnReview" /></b></i>
							</h4>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39PostId" /></label> <span>{{PostId}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentId" /></label> <span>{{IncidentId}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentTitle" /></label> <span>{{IncidentTitle}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentDiscoveryTime" /></label> <span>{{IncidentDiscoveryTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39IncidentReportedTime" /></label> <span>{{IncidentReportedTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39Description" /></label> <span>
								<div ng-bind-html="Description | trustHtml"></div>
							</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39EventTypeCode" /></label> <span>{{EventTypeCode}}-{{EventTypeCodeName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39ReporterName" /></label> <span>{{ReporterName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderPartyName" /></label> <span>{{ResponderPartyName}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderContactNumbers" /></label> <span>{{ResponderContactNumbers}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39ResponderElectronicAddressIdentifiers" /></label> <span>{{ResponderElectronicAddressIdentifiers}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39ImpactQualification" /></label> <span
								ng-show="ImpactQualification == '1'"><s:message
									code="s39ImpactQualificationOpt1" /></span> <span
								ng-show="ImpactQualification == '2'"><s:message
									code="s39ImpactQualificationOpt2" /></span> <span
								ng-show="ImpactQualification == '3'"><s:message
									code="s39ImpactQualificationOpt3" /></span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39CoaDescription" /></label> <span>
								<div ng-bind-html="CoaDescription | trustHtml"></div>
							</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39Confidence" /></label> <span>{{Confidence}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39Reference" /></label> <span>
								<div ng-bind-html="Reference | trustHtml"></div>
							</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39AffectedSoftwareDescription" /></label> <span>
								<div ng-bind-html="AffectedSoftwareDescription | trustHtml"></div>
							</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39StartDateTime" /></label> <span>{{StartDateTime}}</span> <span><s:message
									code="globalDataFromTo" /></span> <br class="visible-xs" /> <label
								class="form_label form_label_search form_label_gray"><s:message
									code="s39EndDateTime" /></label> <span>{{EndDateTime}}</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="isEnable" /></label> <span ng-show="IsEnable == true"><s:message
									code="isEnableTrue" /></span> <span ng-show="IsEnable == false"><s:message
									code="isEanbleFalse" /></span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray">
							是否醫療相關</label> <span ng-show="IsMedical == true">是</span> <span ng-show="IsMedical == false">否</span>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label class="form_label form_label_search form_label_gray"><s:message
									code="s39Status" /></label> <span ng-show="Status == '1'"><s:message
									code="s39StatusOpt1" /></span> <span ng-show="Status == '2'"><s:message
									code="s39StatusOpt2" /></span> <span ng-show="Status == '3'"><s:message
									code="s39StatusOpt3" /></span> <span ng-show="Status == '4'"><s:message
									code="s39StatusOpt4" /></span> <span ng-show="Status == '5'"><s:message
									code="s39StatusOpt5" /></span> <span ng-show="Status == '6'"><s:message
									code="s39StatusOpt6" /></span>
						</div>
					</div>

					<div ng-if="itemAttachments.length > 0">
						<div class="form_group">
							<div class="form_group">
								<h4 class="form_heading form_heading form_heading_yellow">
									<i class="fa fa-info-circle"><b><s:message
												code="globalAttachmentList" /></b></i>
								</h4>
							</div>
							<div class="col-xs-12 shadow_board">
								<div class="help-block"></div>
								<div>
									<div class="col-xs-12 col-sm-6 col-md-4"
										ng-repeat="item in itemAttachments">
										<div class="thumbnail">
											<div class="caption">
												<p>
													<s:message code="globalAttachmentDescription" />
													: {{item.FileDesc}}
												</p>
												<p>
													<s:message code="globalAttachmentName" />
													: <a
														href="./api/s39/attach/download/{{item.NewsManagementId}}/{{item.Id}}"
														target="_blank">{{item.FileName}}</a>
												</p>
												<p>
													<s:message code="globalAttachmentSize" />
													: {{item.FileSize}}
												</p>
												<p>
													<s:message code="globalHashSHA256" />
													:
												</p>
												<div class="input-group">
													<input type="text" class="form-control"
														value="{{item.FileHash}}"> <span
														class="input-group-addon"
														onclick="$(this).prev().select(); document.execCommand('Copy')"><i
														class="fas fa-fw fa-copy"></i></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="submit_bar"></div>
							</div>
						</div>
					</div>

					<div ng-show="IsSeeLog">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLog" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<table
								class="table table-striped table-bordered table-hover table_customer table_gray">
								<thead>
									<tr>
										<th><s:message code="globalProcessLogAction" /></th>
										<th><s:message code="globalProcessLogStatus" /></th>
										<th><s:message code="globalProcessLogFrom" /></th>
										<th><s:message code="globalProcessLogSednTime" /></th>
										<th><s:message code="globalProcessLogContent" /></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in MessageProcessLogData">
										<td><span ng-if="item.PreStatus == 1"><s:message
													code="s39ShowPreStatus1" /></span> <span
											ng-if="item.PreStatus == 2"><s:message
													code="s39ShowPreStatus2" /></span> <span
											ng-if="item.PreStatus == 3"><s:message
													code="s39ShowPreStatus3" /></span> <span
											ng-if="item.PreStatus == 6"><s:message
													code="s39ShowPreStatus6" /></span> <span><i
												class="fas fa-fw fa-arrow-right"></i></span> <span
											ng-if="item.Status == 2"><s:message
													code="s39ShowStatus2" /></span> <span ng-if="item.Status == 3"><s:message
													code="s39ShowStatus3" /></span> <span ng-if="item.Status == 5"><s:message
													code="s39ShowStatus5" /></span> <span ng-if="item.Status == 6"><s:message
													code="s39ShowStatus6" /></span></td>
										<td><span ng-if="item.Status == 1"><s:message
													code="s39Status1" /></span> <span ng-if="item.Status == 2"><s:message
													code="s39Status2" /></span> <span ng-if="item.Status == 3"><s:message
													code="s39Status3" /></span> <span ng-if="item.Status == 4"><s:message
													code="s39Status4" /></span> <span ng-if="item.Status == 5"><s:message
													code="s39Status5" /></span> <span ng-if="item.Status == 6"><s:message
													code="s39Status6" /></span></td>
										<td>{{item.CreateName}}</td>
										<td>{{item.CreateTime}}</td>
										<td>{{item.Opinion}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div ng-show="IsSeeOpinion && Status == 3">
						<div class="form_group">
							<h4 class="form_heading form_heading form_heading_yellow">
								<i class="fa fa-info-circle"><b><s:message
											code="globalProcessLogContent" /></b></i>
							</h4>
						</div>
						<div class="form_group">
							<label for="Opinion"
								class="form_label form_label_search form_label_gray"><s:message
									code="globalProcessLogContent" /></label>
							<div class="form_input form_input_search">
								<textarea id="Opinion" name="Opinion" ng-model="Opinion"
									rows="5" autocomplete="off" class="form-control"
									ng-required="true"></textarea>
								<h5 class="text-danger"
									ng-show="myForm.Opinion.$error.required && myForm.Opinion.$dirty">
									<s:message code="pleaseEnter" />
									<s:message code="globalProcessLogContent" />
								</h5>
							</div>
						</div>
					</div>

				</form>
			</div>
			<div class="page-header">
				<div>
					<div class="clearfix form-actions">
						<div class="text-center">
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '4')"
								ng-show="messagePassButton">
								<i class="fas fa-fw fa-check"></i>
								<s:message code="btnPass" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '6')"
								ng-show="messageBackButton">
								<i class="fas fa-fw fa-arrow-left"></i>
								<s:message code="btnBack" />
							</button>
							<button class="btn btn_custom btn_blue"
								ng-disabled="!myForm.$valid" ng-click="examine(Id, '2')"
								ng-show="messageUndoButtonButton">
								<i class="fas fa-fw fa-eraser"></i>
								<s:message code="btnReject" />
							</button>
							<button class="btn btn_custom btn_gray" ng-click="closeEdit()">
								<i class="fas fa-fw fa-undo"></i>
								<s:message code="btnReturn" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>