<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/survey.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divEdit" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-setting.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div id="divSurvey" class="col-xs-12 shadow_board"
				style="display: none;">
				<form name="myForm" method="post" autocomplete="off">
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveyPublic" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveyPublic01"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyPublic01" /></label><label for="surveyPublic01"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyPublic01Level5" name="SurveyPublic01Level5"
								ng-model="SurveyPublic01" ng-value="5"><label
								for="SurveyPublic01Level5" class="label_for_radio"><s:message
										code="surveyLevelP5" /></label><input type="radio"
								id="SurveyPublic01Level4" name="SurveyPublic01Level4"
								ng-model="SurveyPublic01" ng-value="4"><label
								for="SurveyPublic01Level4" class="label_for_radio"><s:message
										code="surveyLevelP4" /></label><input type="radio"
								id="SurveyPublic01Level3" name="SurveyPublic01Level3"
								ng-model="SurveyPublic01" ng-value="3"><label
								for="SurveyPublic01Level3" class="label_for_radio"><s:message
										code="surveyLevelP3" /></label><input type="radio"
								id="SurveyPublic01Level2" name="SurveyPublic01Level2"
								ng-model="SurveyPublic01" ng-value="2"><label
								for="SurveyPublic01Level2" class="label_for_radio"><s:message
										code="surveyLevelP2" /></label><input type="radio"
								id="SurveyPublic01Level1" name="SurveyPublic01Level1"
								ng-model="SurveyPublic01" ng-value="1"><label
								for="SurveyPublic01Level1" class="label_for_radio"><s:message
										code="surveyLevelP1" /></label> <input type="radio"
								id="SurveyPublic01Level0" name="SurveyPublic01Level0"
								ng-model="SurveyPublic01" ng-value="0"><label
								for="SurveyPublic01Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyPublic01==1">
						<div class="form_group">
							<label for="SurveyPublic02Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyPublic01Suggest" />
								<textarea id="SurveyPublic01Suggest" name="SurveyPublic01Suggest"
									ng-model="SurveyPublic01Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyPublic01==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyPublic02"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyPublic02" /></label><label for="surveyPublic02"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyPublic02Level5" name="SurveyPublic02Level5"
								ng-model="SurveyPublic02" ng-value="5"><label
								for="SurveyPublic02Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyPublic02Level4" name="SurveyPublic02Level4"
								ng-model="SurveyPublic02" ng-value="4"><label
								for="SurveyPublic02Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyPublic02Level3" name="SurveyPublic02Level3"
								ng-model="SurveyPublic02" ng-value="3"><label
								for="SurveyPublic02Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyPublic02Level2" name="SurveyPublic02Level2"
								ng-model="SurveyPublic02" ng-value="2"><label
								for="SurveyPublic02Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyPublic02Level1" name="SurveyPublic02Level1"
								ng-model="SurveyPublic02" ng-value="1"><label
								for="SurveyPublic02Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyPublic02Level0" name="SurveyPublic02Level0"
								ng-model="SurveyPublic02" ng-value="0"><label
								for="SurveyPublic02Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyPublic02==1">
						<div class="form_group">
							<label for="SurveyPublic02Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyPublic02Suggest" />
								<textarea id="SurveyPublic02Suggest" name="SurveyPublic02Suggest"
									ng-model="SurveyPublic02Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyPublic02==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyPublic03"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyPublic03" /></label><label for="surveyPublic03"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyPublic03Level5" name="SurveyPublic03Level5"
								ng-model="SurveyPublic03" ng-value="5"><label
								for="SurveyPublic03Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyPublic03Level4" name="SurveyPublic03Level4"
								ng-model="SurveyPublic03" ng-value="4"><label
								for="SurveyPublic03Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyPublic03Level3" name="SurveyPublic03Level3"
								ng-model="SurveyPublic03" ng-value="3"><label
								for="SurveyPublic03Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyPublic03Level2" name="SurveyPublic03Level2"
								ng-model="SurveyPublic03" ng-value="2"><label
								for="SurveyPublic03Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyPublic03Level1" name="SurveyPublic03Level1"
								ng-model="SurveyPublic03" ng-value="1"><label
								for="SurveyPublic03Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyPublic03Level0" name="SurveyPublic03Level0"
								ng-model="SurveyPublic03" ng-value="0"><label
								for="SurveyPublic03Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyPublic03==1">
						<div class="form_group">
							<label for="SurveyPublic03Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyPublic03Suggest" />
								<textarea id="SurveyPublic03Suggest" name="SurveyPublic03Suggest"
									ng-model="SurveyPublic03Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyPublic03==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveyNotify" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveyNotify01"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyNotify01" /></label><label for="surveyNotify01"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyNotify01Level5" name="SurveyNotify01Level5"
								ng-model="SurveyNotify01" ng-value="5"><label
								for="SurveyNotify01Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyNotify01Level4" name="SurveyNotify01Level4"
								ng-model="SurveyNotify01" ng-value="4"><label
								for="SurveyNotify01Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyNotify01Level3" name="SurveyNotify01Level3"
								ng-model="SurveyNotify01" ng-value="3"><label
								for="SurveyNotify01Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyNotify01Level2" name="SurveyNotify01Level2"
								ng-model="SurveyNotify01" ng-value="2"><label
								for="SurveyNotify01Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyNotify01Level1" name="SurveyNotify01Level1"
								ng-model="SurveyNotify01" ng-value="1"><label
								for="SurveyNotify01Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyNotify01Level0" name="SurveyNotify01Level0"
								ng-model="SurveyNotify01" ng-value="0"><label
								for="SurveyNotify01Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyNotify01==1">
						<div class="form_group">
							<label for="SurveyNotify01Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyNotify01Suggest" />
								<textarea id="SurveyNotify01Suggest" name="SurveyNotify01Suggest"
									ng-model="SurveyNotify01Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyNotify01==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyNotify02"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyNotify02" /></label><label for="surveyNotify02"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyNotify02Level5" name="SurveyNotify02Level5"
								ng-model="SurveyNotify02" ng-value="5"><label
								for="SurveyNotify02Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyNotify02Level4" name="SurveyNotify02Level4"
								ng-model="SurveyNotify02" ng-value="4"><label
								for="SurveyNotify02Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyNotify02Level3" name="SurveyNotify02Level3"
								ng-model="SurveyNotify02" ng-value="3"><label
								for="SurveyNotify02Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyNotify02Level2" name="SurveyNotify02Level2"
								ng-model="SurveyNotify02" ng-value="2"><label
								for="SurveyNotify02Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyNotify02Level1" name="SurveyNotify02Level1"
								ng-model="SurveyNotify02" ng-value="1"><label
								for="SurveyNotify02Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyNotify02Level0" name="SurveyNotify02Level0"
								ng-model="SurveyNotify02" ng-value="0"><label
								for="SurveyNotify02Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyNotify02==1">
						<div class="form_group">
							<label for="SurveyNotify02Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyNotify02Suggest" />
								<textarea id="SurveyNotify02Suggest" name="SurveyNotify02Suggest"
									ng-model="SurveyNotify02Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyNotify02==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyNotify03"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyNotify03" /></label><label for="surveyNotify03"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyNotify03Level5" name="SurveyNotify03Level5"
								ng-model="SurveyNotify03" ng-value="5"><label
								for="SurveyNotify03Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyNotify03Level4" name="SurveyNotify03Level4"
								ng-model="SurveyNotify03" ng-value="4"><label
								for="SurveyNotify03Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyNotify03Level3" name="SurveyNotify03Level3"
								ng-model="SurveyNotify03" ng-value="3"><label
								for="SurveyNotify03Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyNotify03Level2" name="SurveyNotify03Level2"
								ng-model="SurveyNotify03" ng-value="2"><label
								for="SurveyNotify03Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyNotify03Level1" name="SurveyNotify03Level1"
								ng-model="SurveyNotify03" ng-value="1"><label
								for="SurveyNotify03Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyNotify03Level0" name="SurveyNotify03Level0"
								ng-model="SurveyNotify03" ng-value="0"><label
								for="SurveyNotify03Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyNotify03==1">
						<div class="form_group">
							<label for="SurveyNotify03Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyNotify03Suggest" />
								<textarea id="SurveyNotify03Suggest" name="SurveyNotify03Suggest"
									ng-model="SurveyNotify03Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyNotify03==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveyAlert" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveyAlert01"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyAlert01" /></label><label for="surveyAlert01"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyAlert01Level5" name="SurveyAlert01Level5"
								ng-model="SurveyAlert01" ng-value="5"><label
								for="SurveyAlert01Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyAlert01Level4" name="SurveyAlert01Level4"
								ng-model="SurveyAlert01" ng-value="4"><label
								for="SurveyAlert01Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyAlert01Level3" name="SurveyAlert01Level3"
								ng-model="SurveyAlert01" ng-value="3"><label
								for="SurveyAlert01Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyAlert01Level2" name="SurveyAlert01Level2"
								ng-model="SurveyAlert01" ng-value="2"><label
								for="SurveyAlert01Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyAlert01Level1" name="SurveyAlert01Level1"
								ng-model="SurveyAlert01" ng-value="1"><label
								for="SurveyAlert01Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyAlert01Level0" name="SurveyAlert01Level0"
								ng-model="SurveyAlert01" ng-value="0"><label
								for="SurveyAlert01Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyAlert01==1">
						<div class="form_group">
							<label for="SurveyAlert01Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyAlert01Suggest" />
								<textarea id="SurveyAlert01Suggest" name="SurveyAlert01Suggest"
									ng-model="SurveyAlert01Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyAlert01==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyAlert02"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyAlert02" /></label><label for="surveyAlert02"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyAlert02Level5" name="SurveyAlert02Level5"
								ng-model="SurveyAlert02" ng-value="5"><label
								for="SurveyAlert02Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyAlert02Level4" name="SurveyAlert02Level4"
								ng-model="SurveyAlert02" ng-value="4"><label
								for="SurveyAlert02Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyAlert02Level3" name="SurveyAlert02Level3"
								ng-model="SurveyAlert02" ng-value="3"><label
								for="SurveyAlert02Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyAlert02Level2" name="SurveyAlert02Level2"
								ng-model="SurveyAlert02" ng-value="2"><label
								for="SurveyAlert02Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyAlert02Level1" name="SurveyAlert02Level1"
								ng-model="SurveyAlert02" ng-value="1"><label
								for="SurveyAlert02Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyAlert02Level0" name="SurveyAlert02Level0"
								ng-model="SurveyAlert02" ng-value="0"><label
								for="SurveyAlert02Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyAlert02==1">
						<div class="form_group">
							<label for="SurveyAlert02Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyAlert02Suggest" />
								<textarea id="SurveyAlert02Suggest" name="SurveyAlert02Suggest"
									ng-model="SurveyAlert02Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyAlert02==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyAlert03"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyAlert03" /></label><label for="surveyAlert03"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyAlert03Level5" name="SurveyAlert03Level5"
								ng-model="SurveyAlert03" ng-value="5"><label
								for="SurveyAlert03Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyAlert03Level4" name="SurveyAlert03Level4"
								ng-model="SurveyAlert03" ng-value="4"><label
								for="SurveyAlert03Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyAlert03Level3" name="SurveyAlert03Level3"
								ng-model="SurveyAlert03" ng-value="3"><label
								for="SurveyAlert03Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyAlert03Level2" name="SurveyAlert03Level2"
								ng-model="SurveyAlert03" ng-value="2"><label
								for="SurveyAlert03Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyAlert03Level1" name="SurveyAlert03Level1"
								ng-model="SurveyAlert03" ng-value="1"><label
								for="SurveyAlert03Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyAlert03Level0" name="SurveyAlert03Level0"
								ng-model="SurveyAlert03" ng-value="0"><label
								for="SurveyAlert03Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyAlert03==1">
						<div class="form_group">
							<label for="SurveyAlert03Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyAlert03Suggest" />
								<textarea id="SurveyAlert03Suggest" name="SurveyAlert03Suggest"
									ng-model="SurveyAlert03Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyAlert03==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveyCheck" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveyCheck01"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyCheck01" /></label><label for="surveyCheck01"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyCheck01Level5" name="SurveyCheck01Level5"
								ng-model="SurveyCheck01" ng-value="5"><label
								for="SurveyCheck01Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyCheck01Level4" name="SurveyCheck01Level4"
								ng-model="SurveyCheck01" ng-value="4"><label
								for="SurveyCheck01Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyCheck01Level3" name="SurveyCheck01Level3"
								ng-model="SurveyCheck01" ng-value="3"><label
								for="SurveyCheck01Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyCheck01Level2" name="SurveyCheck01Level2"
								ng-model="SurveyCheck01" ng-value="2"><label
								for="SurveyCheck01Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyCheck01Level1" name="SurveyCheck01Level1"
								ng-model="SurveyCheck01" ng-value="1"><label
								for="SurveyCheck01Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyCheck01Level0" name="SurveyCheck01Level0"
								ng-model="SurveyCheck01" ng-value="0"><label
								for="SurveyCheck01Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyCheck01==1">
						<div class="form_group">
							<label for="SurveyCheck01Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyCheck01Suggest" />
								<textarea id="SurveyCheck01Suggest" name="SurveyCheck01Suggest"
									ng-model="SurveyCheck01Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyCheck01==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyCheck02"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyCheck02" /></label><label for="surveyCheck02"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyCheck02Level5" name="SurveyCheck02Level5"
								ng-model="SurveyCheck02" ng-value="5"><label
								for="SurveyCheck02Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyCheck02Level4" name="SurveyCheck02Level4"
								ng-model="SurveyCheck02" ng-value="4"><label
								for="SurveyCheck02Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyCheck02Level3" name="SurveyCheck02Level3"
								ng-model="SurveyCheck02" ng-value="3"><label
								for="SurveyCheck02Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyCheck02Level2" name="SurveyCheck02Level2"
								ng-model="SurveyCheck02" ng-value="2"><label
								for="SurveyCheck02Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyCheck02Level1" name="SurveyCheck02Level1"
								ng-model="SurveyCheck02" ng-value="1"><label
								for="SurveyCheck02Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyCheck02Level0" name="SurveyCheck02Level0"
								ng-model="SurveyCheck02" ng-value="0"><label
								for="SurveyCheck02Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyCheck02==1">
						<div class="form_group">
							<label for="SurveyCheck02Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyCheck02Suggest" />
								<textarea id="SurveyCheck02Suggest" name="SurveyCheck02Suggest"
									ng-model="SurveyCheck02Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyCheck02==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group">
							<label for="surveyCheck03"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyCheck03" /></label><label for="surveyCheck03"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyCheck03Level5" name="SurveyCheck03Level5"
								ng-model="SurveyCheck03" ng-value="5"><label
								for="SurveyCheck03Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyCheck03Level4" name="SurveyCheck03Level4"
								ng-model="SurveyCheck03" ng-value="4"><label
								for="SurveyCheck03Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyCheck03Level3" name="SurveyCheck03Level3"
								ng-model="SurveyCheck03" ng-value="3"><label
								for="SurveyCheck03Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyCheck03Level2" name="SurveyCheck03Level2"
								ng-model="SurveyCheck03" ng-value="2"><label
								for="SurveyCheck03Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyCheck03Level1" name="SurveyCheck03Level1"
								ng-model="SurveyCheck03" ng-value="1"><label
								for="SurveyCheck03Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyCheck03Level0" name="SurveyCheck03Level0"
								ng-model="SurveyCheck03" ng-value="0"><label
								for="SurveyCheck03Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyCheck03==1">
						<div class="form_group">
							<label for="SurveyCheck03Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyCheck03Suggest" />
								<textarea id="SurveyCheck03Suggest" name="SurveyCheck03Suggest"
									ng-model="SurveyCheck03Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyCheck03==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveyTotal" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveyTotal01"
								class="form_label form_label_search form_label_gray"><s:message
									code="surveyTotal01" /></label><label for="surveyTotal01"
								class="input_for_radio_group input_for_radio_group_fix"><input
								type="radio" id="SurveyTotal01Level5" name="SurveyTotal01Level5"
								ng-model="SurveyTotal01" ng-value="5"><label
								for="SurveyTotal01Level5" class="label_for_radio"><s:message
										code="surveyLevel5" /></label><input type="radio"
								id="SurveyTotal01Level4" name="SurveyTotal01Level4"
								ng-model="SurveyTotal01" ng-value="4"><label
								for="SurveyTotal01Level4" class="label_for_radio"><s:message
										code="surveyLevel4" /></label><input type="radio"
								id="SurveyTotal01Level3" name="SurveyTotal01Level3"
								ng-model="SurveyTotal01" ng-value="3"><label
								for="SurveyTotal01Level3" class="label_for_radio"><s:message
										code="surveyLevel3" /></label><input type="radio"
								id="SurveyTotal01Level2" name="SurveyTotal01Level2"
								ng-model="SurveyTotal01" ng-value="2"><label
								for="SurveyTotal01Level2" class="label_for_radio"><s:message
										code="surveyLevel2" /></label><input type="radio"
								id="SurveyTotal01Level1" name="SurveyTotal01Level1"
								ng-model="SurveyTotal01" ng-value="1"><label
								for="SurveyTotal01Level1" class="label_for_radio"><s:message
										code="surveyLevel1" /></label> <input type="radio"
								id="SurveyTotal01Level0" name="SurveyTotal01Level0"
								ng-model="SurveyTotal01" ng-value="0"><label
								for="SurveyTotal01Level0" class="label_for_radio"><s:message
										code="surveyLevel0" /></label> </label>
						</div>
					</div>
					<div ng-show="SurveyTotal01==1">
						<div class="form_group">
							<label for="SurveyTotal01Suggest"
								class="form_label form_label_search hidden-xs"></label>
							<div class="form_input form_input_search">
								<s:message code="surveyTotal01Suugest" />
								<textarea id="SurveyTotal01Suggest" name="SurveyTotal01Suggest"
									ng-model="SurveyTotal01Suggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off" ng-required="SurveyTotal01==1"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					<h4 class="form_heading form_heading_fix form_heading_yellow">
						<b><s:message code="surveySuggest" /></b>
					</h4>
					<div>
						<div class="form_group">
							<label for="surveySuggest"
								class="form_label form_label_search">
								<s:message code="surveySuggest01" /></label>
							<div class="form_input form_input_search">
								<textarea id="SurveySuggest" name="SurveySuggest"
									ng-model="SurveySuggest" class="form-control"
									placeholder="<s:message code="surveySuggestDescription" />"
									rows="5" autocomplete="off"></textarea>
							</div>
						</div>
					</div>
					<div>
						<div class="form_group"></div>
					</div>
					
					
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="!myForm.$valid || updateData()"
							ng-disabled="!myForm.$valid">
							<i class="fas fa-fw fa-list-ul"></i>
							<s:message code="btnSurvey" />
						</button>
					</div>
				</form>
			</div>
			<div id="divExist" class="col-xs-12" style="display: none;">
				<br />
				<div class="alert alert-success" role="alert">
					<s:message code="globalSurveyExist" />
				</div>
			</div>
		</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>