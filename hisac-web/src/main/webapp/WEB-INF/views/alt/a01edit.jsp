<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html ng-app="myApp">
<div id="divEdit" class="container" ng-show="btnIns || btnUpd || handle">
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
	<b><a class="btn btn-warning" href="#my-modal"								
										data-toggle="modal">查閱資安事件處理服務廠商資訊</a></b>	
	</div>
	<div class="row">
		<ul class="nav nav-tabs" id="tabNotifyStep">
			<li class="active"><a data-toggle="tab" href="#tabNotifyStep1"
				ng-click="gotToStep(1)" aria-expanded="true"><s:message
						code="notifyStep1Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyStep2"
				ng-click="gotToStep(2)" aria-expanded="false"><s:message
						code="notifyStep2Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyStep3"
				ng-click="gotToStep(3)" aria-expanded="false"><s:message
						code="notifyStep3Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyStep4"
				ng-click="gotToStep(4)" aria-expanded="false"><s:message
						code="notifyStep4Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyStep5"
				ng-click="gotToStep(5)" aria-expanded="false"><s:message
						code="notifyStep5Title" /></a></li>
			<li class="" ng-show="Status==4 || Status==9"><a data-toggle="tab"
				href="#tabNotifyStep6" ng-click="gotToStep(6)" aria-expanded="false"><s:message
						code="notifyStep6Title" /></a></li>
		</ul>
	</div>
	<form name="editForm">
		<div class="tab-content tab_content">
			<div id="tabNotifyStep1" class="tab-pane active" ng-if="Status == 4 || Status == 9">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent0" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyPostId" /></label><span
									class="form-text form_text form_input_search_half">{{PostId}}</span>
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyApplyDateTime" /></label> <span
									class="form-text form_text form_input_search_half">{{ApplyDateTime}}</span>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep1TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent1" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorUnit" /></label> <span
									class="form-text form_text form_input_search">{{ContactorUnitName}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyMainUnit1" /></label><span
									class="form-text form_text form_input_search">{{MainUnit1Name}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyMainUnit2" /></label><span
									class="form-text form_text form_input_search">{{MainUnit2Name}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorId" /></label><span
									class="form-text form_text form_input_search">{{ContactorName}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorTel" /></label><span
									class="form-text form_text form_input_search_half">{{ContactorTel}}</span>
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorFax" /></label><span
									class="form-text form_text form_input_search_half">{{ContactorFax}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorEmail" /></label><span
									class="form-text form_text form_input_search">{{ContactorEmail}}</span>
							</div>
						</div>
						<!-- 
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsSub" /></label><span
									class="form-text form_text form_input_search" ng-if="IsSub"><s:message
										code="notifyIsSubTrue" /></span><span
									class="form-text form_text form_input_search" ng-if="!IsSub"><s:message
										code="notifyIsSubFalse" /></span>
							</div>
						</div>
						<div ng-show="IsSub">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsSubUnitName" /></label><span
									class="form-text form_text form_input_search">{{IsSubUnitName}}</span>
							</div>
						</div>
						-->
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent2" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">1.<s:message
										code="notifyEventDateTime" /></label><span
									class="form-text form_text form_input_search">{{EventDateTime}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">2.<s:message
										code="notifyDeviceName" /></label> <label
									class="form_label form_label_search_fix form_label_gray"></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyDeviceAmount" /></label><span>資訊設備{{InformationAmount}}<s:message
										code="notifyDeviceUnit" />; <s:message
										code="notifyServerAmount" />{{ServerAmount}}<s:message
										code="notifyDeviceUnit" />; 其他設備{{OtherDeviceAmount}}<s:message
										code="notifyDeviceUnit" />; 其他設備名稱：{{OtherDeviceName}}
										; 備註：{{DeviceRemark}}</span>
							</div>
						</div>	
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">事件之損害/影響範圍評估</label> 
								<span><i
									class="far fa-fw fa-dot-circle" ng-show="AssessDamage=='損害'"></i><i
									class="far fa-fw fa-circle" ng-show="AssessDamage!='損害'"></i> 損害 </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="AssessDamage=='影響'"></i><i
									class="far fa-fw fa-circle" ng-show="AssessDamage!='影響'"></i> 影響 </span>  <span									
									class="form-text form_text form_input_search">說明:{{AssessDamageRemark}}</span>
							</div>
						</div>										
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIpAddress" /></label><span
									class="form-text form_text form_input_search_half"><s:message
										code="notifyIpExternal" />{{IpExternal}}; <s:message
										code="notifyIpInternal" />{{IpInternal}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyWebUrl" /></label><span
									class="form-text form_text form_input_search">{{WebUrl}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyOsVersion" /></label> <span><i
									class="far fa-fw fa-check-square" ng-show="IsOsOpt1"></i><i
									class="far fa-fw fa-square" ng-show="!IsOsOpt1"></i> <s:message
										code="notifyIsOsOpt1" /> </span><span><i
									class="far fa-fw fa-check-square" ng-show="IsOsOpt2"></i><i
									class="far fa-fw fa-square" ng-show="!IsOsOpt2"></i> <s:message
										code="notifyIsOsOpt2" /> </span><span><i
									class="far fa-fw fa-check-square" ng-show="IsOsOpt3"></i><i
									class="far fa-fw fa-square" ng-show="!IsOsOpt3"></i> <s:message
										code="notifyIsOsOpt3" /> </span> <span ng-if="IsOsOpt3"><s:message
										code="notifyIsOsOpt3Other" />{{IsOsOpt3Other}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyGuard" /></label> <span><i
									class="far fa-fw fa-check-square" ng-show="IsGuardOpt1"></i><i
									class="far fa-fw fa-square" ng-show="!IsGuardOpt1"></i> <s:message
										code="notifyIsGuardOpt1" /> </span><span><i
									class="far fa-fw fa-check-square" ng-show="IsGuardOpt2"></i><i
									class="far fa-fw fa-square" ng-show="!IsGuardOpt2"></i> <s:message
										code="notifyIsGuardOpt2" /> </span><span><i
									class="far fa-fw fa-check-square" ng-show="IsGuardOpt3"></i><i
									class="far fa-fw fa-square" ng-show="!IsGuardOpt3"></i> <s:message
										code="notifyIsGuardOpt3" /> </span><span><i
									class="far fa-fw fa-check-square" ng-show="IsGuardOpt4"></i><i
									class="far fa-fw fa-square" ng-show="!IsGuardOpt4"></i> <s:message
										code="notifyIsGuardOpt4" /> </span> <span ng-if="IsGuardOpt4"><s:message
										code="notifyIsGuardOpt4Other" />{{IsGuardOpt4Other}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifySoc" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="SocOpt==0"></i><i
									class="far fa-fw fa-circle" ng-show="SocOpt!=0"></i> <s:message
										code="notifySocOpt0" /> </span><span><i
									class="far fa-fw fa-dot-circle" ng-show="SocOpt==1"></i><i
									class="far fa-fw fa-circle" ng-show="SocOpt!=1"></i> <s:message
										code="notifySocOpt1" /> </span><span><i
									class="far fa-fw fa-dot-circle" ng-show="SocOpt==2"></i><i
									class="far fa-fw fa-circle" ng-show="SocOpt!=2"></i> <s:message
										code="notifySocOpt2" /> <s:message code="notifySocOptCompany" />
									{{SocOptCompany}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsms" /></label><span
									class="form-text form_text form_input_search" ng-if="IsIsms"><s:message
										code="notifyIsIsmsTrue" /></span><span
									class="form-text form_text form_input_search" ng-if="!IsIsms"><s:message
										code="notifyIsIsmsFalse" /></span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyMaintainCompany" /></label><span
									class="form-text form_text form_input_search">{{MaintainCompany}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">是否為演練</label>
								<span ng-if="IsTest"
									class="form-text form_text form_input_search">是</span>
								<span ng-if="!IsTest"
									class="form-text form_text form_input_search">否</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep2" class="tab-pane" ng-if="Status == 4 || Status == 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep2TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent3" /></b>
						</h4>
					</div>
					<div class="col-xs-12">
						<h5 class="form_heading text-danger">
							<s:message code="notifyContent3DescriptionQuery" />
						</h5>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyCeffectLevel" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="CeffectLevel==4"></i><i
										class="far fa-fw fa-circle" ng-show="CeffectLevel!=4"></i> <s:message
											code="notifyCeffectLevel4" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="CeffectLevel==3"></i><i
										class="far fa-fw fa-circle" ng-show="CeffectLevel!=3"></i> <s:message
											code="notifyCeffectLevel3" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="CeffectLevel==2"></i><i
										class="far fa-fw fa-circle" ng-show="CeffectLevel!=2"></i> <s:message
											code="notifyCeffectLevel2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="CeffectLevel==1"></i><i
										class="far fa-fw fa-circle" ng-show="CeffectLevel!=1"></i> <s:message
											code="notifyCeffectLevel1" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="CeffectLevel==0"></i><i
										class="far fa-fw fa-circle" ng-show="CeffectLevel!=0"></i> <s:message
											code="notifyCeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIeffectLevel" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="IeffectLevel==4"></i><i
										class="far fa-fw fa-circle" ng-show="IeffectLevel!=4"></i> <s:message
											code="notifyIeffectLevel4" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="IeffectLevel==3"></i><i
										class="far fa-fw fa-circle" ng-show="IeffectLevel!=3"></i> <s:message
											code="notifyIeffectLevel3" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="IeffectLevel==2"></i><i
										class="far fa-fw fa-circle" ng-show="IeffectLevel!=2"></i> <s:message
											code="notifyIeffectLevel2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="IeffectLevel==1"></i><i
										class="far fa-fw fa-circle" ng-show="IeffectLevel!=1"></i> <s:message
											code="notifyIeffectLevel1" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="IeffectLevel==0"></i><i
										class="far fa-fw fa-circle" ng-show="IeffectLevel!=0"></i> <s:message
											code="notifyIeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyAeffectLevel" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="AeffectLevel==4"></i><i
										class="far fa-fw fa-circle" ng-show="AeffectLevel!=4"></i> <s:message
											code="notifyAeffectLevel4" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="AeffectLevel==3"></i><i
										class="far fa-fw fa-circle" ng-show="AeffectLevel!=3"></i> <s:message
											code="notifyAeffectLevel3" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="AeffectLevel==2"></i><i
										class="far fa-fw fa-circle" ng-show="AeffectLevel!=2"></i> <s:message
											code="notifyAeffectLevel2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="AeffectLevel==1"></i><i
										class="far fa-fw fa-circle" ng-show="AeffectLevel!=1"></i> <s:message
											code="notifyAeffectLevel1" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-dot-circle" ng-show="AeffectLevel==0"></i><i
										class="far fa-fw fa-circle" ng-show="AeffectLevel!=0"></i> <s:message
											code="notifyAeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyEffectLevel" /></label> <span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 0"><s:message
										code="notifyEffectLevel0" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 1"><s:message
										code="notifyEffectLevel1" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 2"><s:message
										code="notifyEffectLevel2" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 3"><s:message
										code="notifyEffectLevel3" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 4"><s:message
										code="notifyEffectLevel4" /> </span>
							</div>
						</div>						
						<div ng-if="NotificationLevelLog.length > 0">
				<div class="form_group">
					<h4 class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>事件影響等級紀錄</b></i>
					</h4>
				</div>
				<div class="form_group">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><s:message code="notifyCeffectLevel" /></th>
								<th><s:message code="notifyIeffectLevel" /></th>
								<th><s:message code="notifyAeffectLevel" /></th>
								<th><s:message code="notifyEffectLevel" /></th>	
								<th><s:message code="globalProcessLogFrom" /></th>							
								<th><s:message code="globalProcessLogSednTime" /></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in NotificationLevelLog">
								<td>
								<span ng-show="item.CeffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.CeffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.CeffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.CeffectLevel==3"><s:message code="notifyEffectLevel3" /></span>							
								<span ng-show="item.CeffectLevel==4"><s:message code="notifyEffectLevel4" /></span>							
								</td>
								<td>
								<span ng-show="item.IeffectLevel==0"><s:message code="notifyIeffectLevel0" /></span>
								<span ng-show="item.IeffectLevel==1"><s:message code="notifyIeffectLevel1" /></span>
								<span ng-show="item.IeffectLevel==2"><s:message code="notifyIeffectLevel2" /></span>
								<span ng-show="item.IeffectLevel==3"><s:message code="notifyIeffectLevel3" /></span>							
								<span ng-show="item.IeffectLevel==4"><s:message code="notifyIeffectLevel4" /></span>			
								</td>
								<td>
								<span ng-show="item.AeffectLevel==0"><s:message code="notifyAeffectLevel0" /></span>
								<span ng-show="item.AeffectLevel==1"><s:message code="notifyAeffectLevel1" /></span>
								<span ng-show="item.AeffectLevel==2"><s:message code="notifyAeffectLevel2" /></span>
								<span ng-show="item.AeffectLevel==3"><s:message code="notifyAeffectLevel3" /></span>
								<span ng-show="item.AeffectLevel==4"><s:message code="notifyAeffectLevel4" /></span>
								</td>
								<td>
								<span ng-show="item.EffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.EffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.EffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.EffectLevel==3"><s:message code="notifyEffectLevel3" /></span>
								<span ng-show="item.EffectLevel==4"><s:message code="notifyEffectLevel4" /></span>
								</td>
								<td>{{item.CreateName}}</td>
								<td>{{item.CreateTime}}</td>								
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_group">
					<br />&nbsp;<br />
				</div>
			</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep3" class="tab-pane" ng-if="Status == 4 || Status == 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep3TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent3" /></b>
						</h4>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyEventType" /></label> <label
									class="input_for_radio_group"><span><i
										class="far fa-fw fa-dot-circle" ng-show="EventType==1"></i><i
										class="far fa-fw fa-circle" ng-show="EventType!=1"></i> <s:message
											code="notifyEventType1" /> </span> <span ng-show="EventType==1">(&nbsp;
										<i class="far fa-fw fa-check-square"
										ng-show="IsEventType1Opt1"></i><i class="far fa-fw fa-square"
										ng-show="!IsEventType1Opt1"></i> <s:message
											code="notifyIsEventType1Opt1" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType1Opt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType1Opt2"></i> <s:message
											code="notifyIsEventType1Opt2" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType1Opt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType1Opt3"></i> <s:message
											code="notifyIsEventType1Opt3" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType1Opt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType1Opt4"></i> <s:message
											code="notifyIsEventType1Opt4" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType1Opt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType1Opt5"></i> <s:message
											code="notifyIsEventType1Opt5" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType1Opt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType1Opt6"></i> <s:message
											code="notifyIsEventType1Opt6" /> )
								</span> </label> <label class="input_for_radio_group"><span><i
										class="far fa-fw fa-dot-circle" ng-show="EventType==2"></i><i
										class="far fa-fw fa-circle" ng-show="EventType!=2"></i> <s:message
											code="notifyEventType2" /> </span> <span ng-show="EventType==2">(&nbsp;
										<i class="far fa-fw fa-check-square"
										ng-show="IsEventType2Opt1"></i><i class="far fa-fw fa-square"
										ng-show="!IsEventType2Opt1"></i> <s:message
											code="notifyIsEventType2Opt1" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType2Opt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType2Opt2"></i> <s:message
											code="notifyIsEventType2Opt2" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType2Opt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType2Opt3"></i> <s:message
											code="notifyIsEventType2Opt3" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType2Opt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType2Opt4"></i> <s:message
											code="notifyIsEventType2Opt4" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType2Opt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType2Opt5"></i> <s:message
											code="notifyIsEventType2Opt5" /> )
								</span> </label> <label class="input_for_radio_group"><span><i
										class="far fa-fw fa-dot-circle" ng-show="EventType==3"></i><i
										class="far fa-fw fa-circle" ng-show="EventType!=3"></i> <s:message
											code="notifyEventType3" /> </span> <span ng-show="EventType==3">(&nbsp;
										<i class="far fa-fw fa-check-square"
										ng-show="IsEventType3Opt1"></i><i class="far fa-fw fa-square"
										ng-show="!IsEventType3Opt1"></i> <s:message
											code="notifyIsEventType3Opt1" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType3Opt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType3Opt2"></i> <s:message
											code="notifyIsEventType3Opt2" /> )
								</span> </label> <label class="input_for_radio_group"><span><i
										class="far fa-fw fa-dot-circle" ng-show="EventType==4"></i><i
										class="far fa-fw fa-circle" ng-show="EventType!=4"></i> <s:message
											code="notifyEventType4" /> </span> <span ng-show="EventType==4">(&nbsp;
										<i class="far fa-fw fa-check-square"
										ng-show="IsEventType4Opt1"></i><i class="far fa-fw fa-square"
										ng-show="!IsEventType4Opt1"></i> <s:message
											code="notifyIsEventType4Opt1" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType4Opt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType4Opt2"></i> <s:message
											code="notifyIsEventType4Opt2" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType4Opt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType4Opt3"></i> <s:message
											code="notifyIsEventType4Opt3" /> <i
										class="far fa-fw fa-check-square" ng-show="IsEventType4Opt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsEventType4Opt4"></i> <s:message
											code="notifyIsEventType4Opt4" /> )
								</span> </label> <label class="input_for_radio_group"><span><i
										class="far fa-fw fa-dot-circle" ng-show="EventType==5"></i><i
										class="far fa-fw fa-circle" ng-show="EventType!=5"></i> <s:message
											code="notifyEventType5" /> <s:message
											code="notifyEventType5Other" /> {{EventType5Other}}</span> </label>
							</div>
						</div>
						<div ng-if="NotificationLevelLog.length > 0">
				<div class="form_group">
					<h4 class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>事件影響等級紀錄</b></i>
					</h4>
				</div>
				<div class="form_group">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><s:message code="notifyCeffectLevel" /></th>
								<th><s:message code="notifyIeffectLevel" /></th>
								<th><s:message code="notifyAeffectLevel" /></th>
								<th><s:message code="notifyEffectLevel" /></th>	
								<th><s:message code="globalProcessLogFrom" /></th>							
								<th><s:message code="globalProcessLogSednTime" /></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in NotificationLevelLog">
								<td>
								<span ng-show="item.CeffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.CeffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.CeffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.CeffectLevel==3"><s:message code="notifyEffectLevel3" /></span>							
								<span ng-show="item.CeffectLevel==4"><s:message code="notifyEffectLevel4" /></span>							
								</td>
								<td>
								<span ng-show="item.IeffectLevel==0"><s:message code="notifyIeffectLevel0" /></span>
								<span ng-show="item.IeffectLevel==1"><s:message code="notifyIeffectLevel1" /></span>
								<span ng-show="item.IeffectLevel==2"><s:message code="notifyIeffectLevel2" /></span>
								<span ng-show="item.IeffectLevel==3"><s:message code="notifyIeffectLevel3" /></span>							
								<span ng-show="item.IeffectLevel==4"><s:message code="notifyIeffectLevel4" /></span>			
								</td>
								<td>
								<span ng-show="item.AeffectLevel==0"><s:message code="notifyAeffectLevel0" /></span>
								<span ng-show="item.AeffectLevel==1"><s:message code="notifyAeffectLevel1" /></span>
								<span ng-show="item.AeffectLevel==2"><s:message code="notifyAeffectLevel2" /></span>
								<span ng-show="item.AeffectLevel==3"><s:message code="notifyAeffectLevel3" /></span>
								<span ng-show="item.AeffectLevel==4"><s:message code="notifyAeffectLevel4" /></span>
								</td>
								<td>
								<span ng-show="item.EffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.EffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.EffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.EffectLevel==3"><s:message code="notifyEffectLevel3" /></span>
								<span ng-show="item.EffectLevel==4"><s:message code="notifyEffectLevel4" /></span>
								</td>
								<td>{{item.CreateName}}</td>
								<td>{{item.CreateTime}}</td>								
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_group">
					<br />&nbsp;<br />
				</div>
			</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyEventRemark" /></label><span
									class="form-text form_text form_input_search">{{EventRemark}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsAffectOthers" /></label><span
									class="form-text form_text form_input_search"
									ng-if="IsAffectOthers"><s:message
										code="notifyIsAffectOthersTrue" /></span><span
									class="form-text form_text form_input_search"
									ng-if="!IsAffectOthers"><s:message
										code="notifyIsAffectOthersFalse" /></span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifySource" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="EventSource==1"></i><i
									class="far fa-fw fa-circle" ng-show="EventSource!=1"></i> <s:message
										code="notifySourceFromNotification" /> </span><span><i
									class="far fa-fw fa-dot-circle" ng-show="EventSource==2"></i><i
									class="far fa-fw fa-circle" ng-show="EventSource!=2"></i> <s:message
										code="notifySourceFromMessage" /> <s:message
										code="notifySourceFromMessagePostId" /> {{EventSourceNo}}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep4" class="tab-pane" ng-if="Status == 4 || Status == 9">
				<div class="row" ng-show="EventType==1">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType1" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1LogOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1LogOpt1"></i> <s:message
											code="notifyIsRes1LogOpt1" />
								</span> <span>(&nbsp;<label class="label_for_radio"
										ng-if="Res1LogOpt1==1"><s:message
												code="notifyRes1LogOpt11" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt1==2"><s:message
												code="notifyRes1LogOpt12" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt1==3"><s:message
												code="notifyRes1LogOpt13" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt1==4"><s:message
												code="notifyRes1LogOpt14" />{{Res1LogOpt1Other}}</label>)
								</span> <!-- Res 1 opt 1 End --> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1LogOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1LogOpt2"></i> <s:message
											code="notifyIsRes1LogOpt2" />
								</span> <!-- Res 1 opt 2 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-if="Res1LogOpt2==1"><s:message
												code="notifyRes1LogOpt21" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt2==2"><s:message
												code="notifyRes1LogOpt22" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt2==3"><s:message
												code="notifyRes1LogOpt23" /></label><label class="label_for_radio"
										ng-if="Res1LogOpt2==4"><s:message
												code="notifyRes1LogOpt24" />{{Res1LogOpt2Other}}</label>)
								</span> <!-- Res 1 opt 2 End --></label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1LogOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1LogOpt3"></i> <s:message
											code="notifyIsRes1LogOpt3" />{{Res1LogOpt3Amount}}<s:message
											code="notifyIsRes1LogOpt3Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1LogOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1LogOpt4"></i> <s:message
											code="notifyIsRes1LogOpt4" />{{Res1LogOpt4Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt1"></i> <s:message
											code="notifyIsRes1EvaOpt1" />{{Res1EvaOpt1Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt2"></i> <s:message
											code="notifyIsRes1EvaOpt2" />{{Res1EvaOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt3"></i> <s:message
											code="notifyIsRes1EvaOpt3" />{{Res1EvaOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt4"></i> <s:message
											code="notifyIsRes1EvaOpt4" />{{Res1EvaOpt4Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt5"></i> <s:message
											code="notifyIsRes1EvaOpt5" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt6"></i> <s:message
											code="notifyIsRes1EvaOpt6" />{{Res1EvaOpt6Amount}}<s:message
											code="notifyIsRes1EvaOpt6Unit" />
								</span> <!-- Res 2 opt 6 Start --> <span><label
										class="label_for_radio" ng-show="Res1EvaOpt6Type1==1"><s:message
												code="notifyRes1EvaOpt6Type1" /></label><label
										class="label_for_radio" ng-show="Res1EvaOpt6Type1==2"><s:message
												code="notifyRes1EvaOpt6Type2" /></label><label
										class="label_for_radio" ng-show="Res1EvaOpt6Type1==3"><s:message
												code="notifyRes1EvaOpt6Type3" />{{Res1EvaOpt6TypeOpt3Other}}</label></span>
									<!-- Res 2 opt 6 End --></label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt7"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt7"></i> <s:message
											code="notifyIsRes1EvaOpt7" />{{Res1EvaOpt7Amount}}<s:message
											code="notifyIsRes1EvaOpt7Unit" />{{Res1EvaOpt7Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1EvaOpt8"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1EvaOpt8"></i> <s:message
											code="notifyIsRes1EvaOpt8" />{{Res1EvaOpt8Remark}}
								</span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt1"></i> <s:message
											code="notifyIsRes1DoOpt1" />{{Res1DoOpt1Amount}}<s:message
											code="notifyIsRes1DoOpt1Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt2"></i> <s:message
											code="notifyIsRes1DoOpt2" />{{Res1DoOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt3"></i> <s:message
											code="notifyIsRes1DoOpt3" />{{Res1DoOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt4"></i> <s:message
											code="notifyIsRes1DoOpt4" />{{Res1DoOpt4Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt5"></i> <s:message
											code="notifyIsRes1DoOpt5" />{{Res1DoOpt5Amount}}<s:message
											code="notifyIsRes1DoOpt5Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt6"></i> <s:message
											code="notifyIsRes1DoOpt6" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt7"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt7"></i> <s:message
											code="notifyIsRes1DoOpt7" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt8"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt8"></i> <s:message
											code="notifyIsRes1DoOpt8" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt9"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9"></i> <s:message
											code="notifyIsRes1DoOpt9" />
								</span> <!-- Res 3 opt 9 Start --> <span>(&nbsp;<i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt1"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt1" /></label><i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt2"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt2" /></label><i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt3"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt3" /></label><i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt4"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt4" /></label><i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt5"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt5" /></label><i
										class="far fa-fw fa-check-square"
										ng-show="IsRes1DoOpt9EngineOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt9EngineOpt6"></i><label
										class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt6" /></label>{{Res1DoOpt9EngineOpt6Other}})
								</span> <!-- Res 3 opt 9 End --> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt10"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt10"></i> <s:message
											code="notifyIsRes1DoOpt10" />{{Res1DoOpt10Date}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt11"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt11"></i> <s:message
											code="notifyIsRes1DoOpt11" />{{Res1DoOpt11Date}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes1DoOpt12"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes1DoOpt12"></i> <s:message
											code="notifyIsRes1DoOpt12" />{{Res1DoOpt12Remark}}
								</span> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==2">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType2" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2LogOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2LogOpt1"></i> <s:message
											code="notifyIsRes2LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res2LogOpt1==1"><s:message
												code="notifyRes2LogOpt11" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt1==2"><s:message
												code="notifyRes2LogOpt12" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt1==3"><s:message
												code="notifyRes2LogOpt13" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt1==4"><s:message
												code="notifyRes2LogOpt14" />{{Res2LogOpt1Other}}</label>)
								</span> <!-- Res 2 opt 1 End --> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2LogOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2LogOpt2"></i> <s:message
											code="notifyIsRes2LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res2LogOpt2==1"><s:message
												code="notifyRes2LogOpt21" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt2==2"><s:message
												code="notifyRes2LogOpt22" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt2==3"><s:message
												code="notifyRes2LogOpt23" /></label><label class="label_for_radio"
										ng-show="Res2LogOpt2==4"><s:message
												code="notifyRes2LogOpt24" />{{Res2LogOpt2Other}}</label>)
								</span> <!-- Res 2 opt 2 End --></label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2LogOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2LogOpt3"></i> <s:message
											code="notifyIsRes2LogOpt3" />{{Res2LogOpt3Amount}}<s:message
											code="notifyIsRes2LogOpt3Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2LogOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2LogOpt4"></i> <s:message
											code="notifyIsRes2LogOpt4" />{{Res2LogOpt4Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2EvaOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2EvaOpt1"></i> <s:message
											code="notifyIsRes2EvaOpt1" />{{Res2EvaOpt1Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2EvaOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2EvaOpt2"></i> <s:message
											code="notifyIsRes2EvaOpt2" />{{Res2EvaOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2EvaOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2EvaOpt3"></i> <s:message
											code="notifyIsRes2EvaOpt3" />{{Res2EvaOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2EvaOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2EvaOpt4"></i> <s:message
											code="notifyIsRes2EvaOpt4" />{{Res2EvaOpt4Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2EvaOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2EvaOpt5"></i> <s:message
											code="notifyIsRes2EvaOpt5" />{{Res2EvaOpt5Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt1"></i> <s:message
											code="notifyIsRes2DoOpt1" />{{Res2DoOpt1Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt2"></i> <s:message
											code="notifyIsRes2DoOpt2" />{{Res2DoOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt3"></i> <s:message
											code="notifyIsRes2DoOpt3" />{{Res2DoOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt4"></i> <s:message
											code="notifyIsRes2DoOpt4" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt5"></i> <s:message
											code="notifyIsRes2DoOpt5" /></span></label> <label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt6"></i> <s:message
											code="notifyIsRes2DoOpt6" />{{Res2DoOpt6Amount}}<s:message
											code="notifyIsRes2DoOpt6Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes2DoOpt7"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes2DoOpt7"></i> <s:message
											code="notifyIsRes2DoOpt7" />{{Res2DoOpt7Remark}}
								</span> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==3">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType3" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3LogOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3LogOpt1"></i> <s:message
											code="notifyIsRes3LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res3LogOpt1==1"><s:message
												code="notifyRes3LogOpt11" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt1==2"><s:message
												code="notifyRes3LogOpt12" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt1==3"><s:message
												code="notifyRes3LogOpt13" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt1==4"><s:message
												code="notifyRes3LogOpt14" />{{Res3LogOpt1Other}}</label>)
								</span> <!-- Res 2 opt 1 End --> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3LogOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3LogOpt2"></i> <s:message
											code="notifyIsRes3LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res3LogOpt2==1"><s:message
												code="notifyRes3LogOpt21" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt2==2"><s:message
												code="notifyRes3LogOpt22" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt2==3"><s:message
												code="notifyRes3LogOpt23" /></label><label class="label_for_radio"
										ng-show="Res3LogOpt2==4"><s:message
												code="notifyRes3LogOpt24" />{{Res3LogOpt2Other}}</label>)
								</span> <!-- Res 2 opt 2 End --></label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3LogOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3LogOpt3"></i> <s:message
											code="notifyIsRes3LogOpt3" />{{Res3LogOpt3Amount}}<s:message
											code="notifyIsRes3LogOpt3Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3LogOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3LogOpt4"></i> <s:message
											code="notifyIsRes3LogOpt4" />{{Res3LogOpt4Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3EvaOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3EvaOpt1"></i> <s:message
											code="notifyIsRes3EvaOpt1" />{{Res3EvaOpt1Amount}}<s:message
											code="notifyIsRes3EvaOpt1Unit" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3EvaOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3EvaOpt2"></i> <s:message
											code="notifyIsRes3EvaOpt2" />{{Res3EvaOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3EvaOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3EvaOpt3"></i> <s:message
											code="notifyIsRes3EvaOpt3" />{{Res3EvaOpt3Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label> <label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3DoOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3DoOpt1"></i> <s:message
											code="notifyIsRes3DoOpt1" />{{Res3DoOpt1Remark}}
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3DoOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3DoOpt2"></i> <s:message
											code="notifyIsRes3DoOpt2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3DoOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3DoOpt3"></i> <s:message
											code="notifyIsRes3DoOpt3" />{{Res3DoOpt3Remark}}
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes3DoOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes3DoOpt4"></i> <s:message
											code="notifyIsRes3DoOpt4" />{{Res3DoOpt4Remark}}
								</span> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==4">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType4" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label> <label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4LogOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4LogOpt1"></i> <s:message
											code="notifyIsRes4LogOpt1" />{{Res4LogOpt1Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4EvaOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4EvaOpt1"></i> <s:message
											code="notifyIsRes4EvaOpt1" />
								</span> <!-- Res 2 opt 6 Start --> <span><label
										class="label_for_radio" ng-show="Res4EvaOpt1==1"><s:message
												code="notifyRes4EvaOpt1Type1" /></label><label
										class="label_for_radio" ng-show="Res4EvaOpt1==2"><s:message
												code="notifyRes4EvaOpt1Type2" /></label><label
										class="label_for_radio" ng-show="Res4EvaOpt1==3"><s:message
												code="notifyRes4EvaOpt1Type3" /></label><label
										class="label_for_radio" ng-show="Res4EvaOpt1==4"><s:message
												code="notifyRes4EvaOpt1Type4" />{{Res4EvaOpt1Amount}}</label>% </span> <!-- Res 2 opt 6 End --></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4EvaOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4EvaOpt2"></i> <s:message
											code="notifyIsRes4EvaOpt2" />{{Res4EvaOpt2Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4DoOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4DoOpt1"></i> <s:message
											code="notifyIsRes4DoOpt1" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4DoOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4DoOpt2"></i> <s:message
											code="notifyIsRes4DoOpt2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes4DoOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes4DoOpt3"></i> <s:message
											code="notifyIsRes4DoOpt3" />{{Res4DoOpt3Remark}}
								</span> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==5">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType5" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5LogOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5LogOpt1"></i> <s:message
											code="notifyIsRes5LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res5LogOpt1==1"><s:message
												code="notifyRes5LogOpt11" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt1==2"><s:message
												code="notifyRes5LogOpt12" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt1==3"><s:message
												code="notifyRes5LogOpt13" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt1==4"><s:message
												code="notifyRes5LogOpt14" />{{Res5LogOpt1Other}}</label>)
								</span> <!-- Res 2 opt 1 End --> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5LogOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5LogOpt2"></i> <s:message
											code="notifyIsRes5LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<label
										class="label_for_radio" ng-show="Res5LogOpt2==1"><s:message
												code="notifyRes5LogOpt21" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt2==2"><s:message
												code="notifyRes5LogOpt22" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt2==3"><s:message
												code="notifyRes5LogOpt23" /></label><label class="label_for_radio"
										ng-show="Res5LogOpt2==4"><s:message
												code="notifyRes5LogOpt24" />{{Res5LogOpt2Other}}</label>)
								</span> <!-- Res 2 opt 2 End --></label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5LogOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5LogOpt3"></i> <s:message
											code="notifyIsRes5LogOpt3" />{{Res5LogOpt3Amount}}<s:message
											code="notifyIsRes5LogOpt3Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5LogOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5LogOpt4"></i> <s:message
											code="notifyIsRes5LogOpt4" />{{Res5LogOpt4Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5EvaOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5EvaOpt1"></i> <s:message
											code="notifyIsRes5EvaOpt1" />{{Res5EvaOpt1Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5EvaOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5EvaOpt2"></i> <s:message
											code="notifyIsRes5EvaOpt2" />{{Res5EvaOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5EvaOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5EvaOpt3"></i> <s:message
											code="notifyIsRes5EvaOpt3" />{{Res5EvaOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5EvaOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5EvaOpt4"></i> <s:message
											code="notifyIsRes5EvaOpt4" />{{Res5EvaOpt4Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5EvaOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5EvaOpt5"></i> <s:message
											code="notifyIsRes5EvaOpt5" />{{Res5EvaOpt5Remark}}
								</span> </label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt1"></i> <s:message
											code="notifyIsRes5DoOpt1" />{{Res5DoOpt1Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt2"></i> <s:message
											code="notifyIsRes5DoOpt2" />{{Res5DoOpt2Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt3"></i> <s:message
											code="notifyIsRes5DoOpt3" />{{Res5DoOpt3Remark}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt4"></i> <s:message
											code="notifyIsRes5DoOpt4" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt5"></i> <s:message
											code="notifyIsRes5DoOpt5" />{{Res5DoOpt5Date}}
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt6"></i> <s:message
											code="notifyIsRes5DoOpt6" />{{Res5DoOpt6Amount}}<s:message
											code="notifyIsRes5DoOpt6Unit" />
								</span> </label> <label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsRes5DoOpt7"></i><i
										class="far fa-fw fa-square" ng-show="!IsRes5DoOpt7"></i> <s:message
											code="notifyIsRes5DoOpt7" />{{Res5DoOpt7Remark}}
								</span> </label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep5" class="tab-pane" ng-if="Status == 4 || Status == 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep5TitleDetail" /></b>
							</h3>
						</div>
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent4" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsNeedSupport" /></label><span
									class="form-text form_text form_input_search"
									ng-if="IsNeedSupport"><s:message
										code="notifyIsNeedSupportTrue" /></span><span
									class="form-text form_text form_input_search"
									ng-if="!IsNeedSupport"><s:message
										code="notifyIsNeedSupportFalse" /></span>
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">說明</label><span
									class="form-text form_text form_input_search">{{SupportNotes}}</span>
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">查閱資安事件處理服務廠商資訊</label>
													&nbsp; <a class="btn btn-warning" href="#my-modal"								
										data-toggle="modal">查閱資安事件處理服務廠商資訊</a>									
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyNeedSupportRemark" /></label><span
									class="form-text form_text form_input_search">{{NeedSupportRemark}}</span>
							</div>
						</div>						
					</div>
				</div>
			</div>




			<div id="tabNotifyStep1" class="tab-pane active"
				ng-show="Status != 4 && Status != 9">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent0" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label for="PostId"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyPostId" /></label><span
									class="form-text form_text form_input_search_half text-danger"
									ng-show="btnIns"><s:message code="globalAutoGenerate" /></span><span
									class="form-text form_text form_input_search_half"
									ng-show="btnUpd">{{PostId}}</span> <label for="ApplyDateTime"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyApplyDateTime" /></label>
								<div class="form_input form_input_search_half">
									<input type="text" id="ApplyDateTime" name="ApplyDateTime"
										ng-model="ApplyDateTime" class="form-control"
										placeholder="<s:message code="notifyApplyDateTime" />"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep1TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent1" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorUnit" /></label> <span
									class="form-text form_text form_input_search">{{ContactorUnitName}}</span><span
									style="display: none;">{{ContactorUnit}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyMainUnit1" /></label><span
									class="form-text form_text form_input_search">{{MainUnit1Name}}</span><span
									style="display: none;">{{MainUnit1}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyMainUnit2" /></label><span
									class="form-text form_text form_input_search">{{MainUnit2Name}}</span><span
									style="display: none;">{{MainUnit2}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorId" /></label> <span
									class="form-text form_text form_input_search">{{ContactorName}}</span><span
									style="display: none;">{{ContactorId}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="ContactorTel"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorTel" /></label>
								<div class="form_input form_input_search_half">
									<input type="text" id="ContactorTel" name="ContactorTel"
										ng-model="ContactorTel" class="form-control"
										placeholder="<s:message code="notifyContactorTel" />"
										autocomplete="off" ng-maxlength="32" ng-required="true"
										ng-pattern="/^0\d{1,2}-\d{6,10}/" />
									<h5 class="text-danger"
										ng-show="editForm.ContactorTel.$error.maxlength && editForm.ContactorTel.$dirty">
										<s:message code="formatMaxLengthAfter" arguments="32" />
									</h5>
									<h5 class="text-danger"
										ng-show="editForm.ContactorTel.$invalid && editForm.ContactorTel.$dirty">
										<s:message code="phoneFormatErr" />
									</h5>
								</div>
								<label for="ContactorFax"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorFax" /></label>
								<div class="form_input form_input_search_half">
									<input type="text" id="ContactorFax" name="ContactorFax"
										ng-model="ContactorFax" class="form-control"
										placeholder="<s:message code="notifyContactorFax" />"
										autocomplete="off" ng-maxlength="32"
										ng-pattern="/^0\d{1,2}-\d{6,10}/" />
									<h5 class="text-danger"
										ng-show="editForm.ContactorFax.$error.maxlength && editForm.ContactorFax.$dirty">
										<s:message code="formatMaxLengthAfter" arguments="32" />
									</h5>
									<h5 class="text-danger"
										ng-show="editForm.ContactorFax.$invalid && editForm.ContactorFax.$dirty">
										<s:message code="phoneFormatErr" />
									</h5>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="ContactorEmail"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyContactorEmail" /></label>
								<div class="form_input form_input_search">
									<input type="text" id="ContactorEmail" name="ContactorEmail"
										ng-model="ContactorEmail" class="form-control"
										placeholder="<s:message code="notifyContactorEmail" />"
										autocomplete="off" ng-maxlength="64" ng-required="true"
										ng-pattern="/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i" />
									<h5 class="text-danger"
										ng-show="editForm.ContactorEmail.$error.required && editForm.ContactorEmail.$dirty">
										<s:message code="pleaseEnter" />
										<s:message code="notifyContactorEmail" />
									</h5>
									<h5 class="text-danger"
										ng-show="!editForm.ContactorEmail.$error.required && editForm.ContactorEmail.$invalid && editForm.ContactorEmail.$dirty">
										<s:message code="notifyEmailFormat" arguments="64" />
									</h5>
								</div>
							</div>
						</div>
						<!-- 
						<div>
							<div class="form_group">
								<label for="IsSub"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsSub" /></label>
								<div class="form_input form_input_search">
									<toggle ng-model="IsSub" ng-init="IsSub=false"
										on='<i class="far fa-fw fa-check-circle"></i><s:message code="notifyIsSubTrue" />'
										off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="notifyIsSubFalse" />'
										onstyle="btn-success" offstyle="btn-danger"></toggle>
								</div>
							</div>
						</div>
						<div ng-show="IsSub">
							<div class="form_group">
								<label for="IsSubUnitName"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsSubUnitName" /></label>
								<div class="form_input form_input_search">
									<input type="text" id="IsSubUnitName" name="IsSubUnitName"
										ng-model="IsSubUnitName" class="form-control"
										placeholder="<s:message code="notifyIsSubUnitName" />"
										autocomplete="off" ng-maxlength="128">
									<h5 class="text-danger"
										ng-show="editForm.IsSubUnitName.$error.maxlength && editForm.IsSubUnitName.$dirty">
										<s:message code="formatMaxLengthAfter" arguments="128" />
									</h5>
								</div>
							</div>
						</div>
						-->
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent2" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label for="EventDateTime"
									class="form_label form_label_search form_label_gray">1.<s:message
										code="notifyEventDateTime" /></label>
								<div class="form_input form_input form_input_search">
									<div class="input-group">
										<input type="text" id="EventDateTime" name="EventDateTime"
											ng-model="EventDateTime" class="form-control"
											placeholder="<s:message code="notifyEventDateTime" />"
											autocomplete="off" ng-required="true"><span
											class="input-group-addon"> <i
											class="fas fa-calendar-alt"></i>
										</span>
									</div>
									<h5 class="text-danger"
										ng-show="editForm.EventDateTime.$error.required && editForm.EventDateTime.$dirty">
										<s:message code="pleaseEnter" />
										<s:message code="notifyEventDateTime" />
									</h5>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="DeviceName"
									class="form_label form_label_search form_label_gray">2.<s:message
										code="notifyDeviceName" /></label> <label
									class="form_label form_label_search_fix form_label_gray"></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="InformationAmount" style="height:140px"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyDeviceAmount" /></label><span>資訊設備</span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="InformationAmount" name="InformationAmount"
										ng-init="InformationAmount=0" ng-model="InformationAmount"										
										ng-required="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										class="form-control"
										placeholder="資訊設備"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span><s:message
										code="notifyServerAmount" /></span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="ServerAmount"
										name="ServerAmount" ng-init="ServerAmount=0"
										ng-required="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="ServerAmount" class="form-control"
										placeholder="<s:message code="notifyServerAmount" />"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span>其他設備</span>
								<div class="form_input form_input_search_half">
									<input type="number" min="0" id="OtherDeviceAmount"
										name="OtherDeviceAmount" ng-init="OtherDeviceAmount=0"
										ng-required="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="OtherDeviceAmount" class="form-control"
										placeholder="其他設備"
										autocomplete="off">
								</div>
								<span><s:message code="notifyDeviceUnit" /></span> <span
									class="hidden-xs">;</span> <span>其他設備名稱</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="OtherDeviceName"
										name="OtherDeviceName"  
										ng-required="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="OtherDeviceName" class="form-control"
										placeholder="其他設備名稱"
										autocomplete="off">
								</div>
								<span
									class="hidden-xs">;</span> <span>備註：</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="DeviceRemark"
										name="DeviceRemark" 
										ng-required="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)"
										ng-model="DeviceRemark" class="form-control"
										placeholder="備註"
										autocomplete="off">
								</div>
								<h5 class="text-danger"
								ng-show="(InformationAmount=='' || InformationAmount==null) && (ServerAmount=='' || ServerAmount==null) && (OtherDeviceAmount=='' || OtherDeviceAmount==null) && (OtherDeviceName=='' || OtherDeviceName==null) && (DeviceRemark=='' || DeviceRemark==null)">
								※「受害設備數量」為必填項目，若無受害設備，請於「備註」欄位補充說明。
								</h5>							
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">事件之損害/影響範圍評估
								</label>
								<span> <input type="radio"
								ng-required="AssessDamage=='' || AssessDamage==null" 
								id="AssessDamage1" name="AssessDamage" ng-model="AssessDamage" class="ng-pristine ng-untouched ng-valid ng-not-empty" value='損害'><label for="AssessDamage1" class="label_for_radio">損害 </label>
								</span>
								<span> <input type="radio" 
								ng-required="AssessDamage=='' || AssessDamage==null" 
								id="AssessDamage2" name="AssessDamage" ng-model="AssessDamage" class="ng-pristine ng-untouched ng-valid ng-not-empty" value='影響'><label for="AssessDamage2" class="label_for_radio">影響 </label>
								</span>
								<span> 說明：</span>
								<div class="form_input form_input_search_half">
									<input type="text" id="AssessDamageRemark" name="AssessDamageRemark"
										ng-model="AssessDamageRemark" class="form-control"
										ng-required="AssessDamageRemark == '' || AssessDamageRemark == null " 
										placeholder="說明"
										autocomplete="off"
										>								
								</div>	
								<h5 class="text-danger"
								ng-show="AssessDamage=='' || AssessDamage==null">
								<s:message code="pleaseEnter" />
								事件之損害/影響範圍評估
								</h5>	
								<h5 class="text-danger"
								ng-show="AssessDamageRemark == '' || AssessDamageRemark==null">
								<s:message code="pleaseEnter" />
								說明
								</h5>							
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IpAddress"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIpAddress" /></label><span><s:message
										code="notifyIpExternal" /></span>
								<div class="form_input form_input_search_half">
									<input type="text" id="IpExternal" name="IpExternal"
										ng-model="IpExternal" class="form-control"
										placeholder="<s:message code="notifyIpExternal" />"
										autocomplete="off"
										ng-pattern="/^((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))*$/">
									<h5 class="text-danger"
										ng-show="editForm.IpExternal.$invalid && editForm.IpExternal.$dirty">
										<s:message code="notifyIpAddress" />
										<s:message code="notifyFormat" />
									</h5>
								</div>
								<span class="hidden-xs">;</span> <span><s:message
										code="notifyIpInternal" /></span>
								<div class="form_input form_input_search_half">
									<input type="text" id="IpInternal" name="IpInternal"
										ng-model="IpInternal" class="form-control"
										placeholder="<s:message code="notifyIpInternal" />"
										autocomplete="off"
										ng-pattern="/^((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))*$/">
									<h5 class="text-danger"
										ng-show="editForm.IpInternal.$invalid && editForm.IpInternal.$dirty">
										<s:message code="notifyIpAddress" />
										<s:message code="notifyFormat" />
									</h5>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="WebUrl"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyWebUrl" /></label>
								<div class="form_input form_input_search">
									<input type="text" id="WebUrl" name="WebUrl" ng-model="WebUrl"
										class="form-control"
										placeholder="<s:message code="notifyWebUrl" />"
										autocomplete="off" ng-maxlength="128"
										ng-pattern="/^(?:(https?|http):\/\/)?((?:[a-zA-Z0-9.\-]+\.)+(?:[a-zA-Z0-9]))((?:/[\w+=%&.~\-]*)*)\??([\w+=%&.~\/-]*)$/">
									<h5 class="text-danger"
										ng-show="editForm.WebUrl.$invalid && editForm.WebUrl.$dirty">
										<s:message code="notifyWebUrlFormat" />
									</h5>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="OsVersion"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyOsVersion" /></label> <span> <input
									type="checkbox" id="IsOsOpt1" name="IsOsOpt1"
									ng-required="!IsOsOpt1 && !IsOsOpt2 &&!IsOsOpt3"
									ng-model="IsOsOpt1"><label for="IsOsOpt1"
									class="label_for_radio"><s:message
											code="notifyIsOsOpt1" /> </label>
								</span><span> <input type="checkbox" id="IsOsOpt2"
									name="IsOsOpt2" ng-model="IsOsOpt2"><label
									for="IsOsOpt2" class="label_for_radio"><s:message
											code="notifyIsOsOpt2" /> </label>
								</span><span> <input type="checkbox" id="IsOsOpt3"
									name="IsOsOpt3" ng-model="IsOsOpt3"><label
									for="IsOsOpt3" class="label_for_radio"><s:message
											code="notifyIsOsOpt3" /> </label>
								</span> <span ng-show="IsOsOpt3"><s:message
										code="notifyIsOsOpt3Other" /> </span>
								<div class="form_input form_input_search_half"
									ng-show="IsOsOpt3">
									<input type="text" id="IsOsOpt3Other" name="IsOsOpt3Other"
										ng-model="IsOsOpt3Other" class="form-control"
										placeholder="<s:message code="notifyIsOsOpt3Other" />"
										autocomplete="off" ng-required="IsOsOpt3">
								</div>
							</div>
							<h5 class="text-danger"
								ng-show="editForm.IsOsOpt3Other.$error.required && editForm.IsOsOpt3Other.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifyIsOsOpt3Other" />
							</h5>
							<h5 class="text-danger"
								ng-show="!IsOsOpt1 && !IsOsOpt2 && !IsOsOpt3">
								<s:message code="pleaseSelectMoreThenOne" />
							</h5>
						</div>
						<div>
							<div class="form_group">
								<label for="Guard"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyGuard" /></label> <span> <input type="checkbox"
									id="IsGuardOpt1" name="IsGuardOpt1"
									ng-required="!IsGuardOpt1 && !IsGuardOpt2 && !IsGuardOpt3 && !IsGuardOpt4"
									ng-model="IsGuardOpt1"><label for="IsGuardOpt1"
									class="label_for_radio"><s:message
											code="notifyIsGuardOpt1" /> </label>
								</span><span> <input type="checkbox" id="IsGuardOpt2"
									name="IsGuardOpt2" ng-model="IsGuardOpt2"><label
									for="IsGuardOpt2" class="label_for_radio"><s:message
											code="notifyIsGuardOpt2" /> </label>
								</span><span> <input type="checkbox" id="IsGuardOpt3"
									name="IsGuardOpt3" ng-model="IsGuardOpt3"><label
									for="IsGuardOpt3" class="label_for_radio"><s:message
											code="notifyIsGuardOpt3" /> </label>
								</span><span> <input type="checkbox" id="IsGuardOpt4"
									name="IsGuardOpt4" ng-model="IsGuardOpt4"><label
									for="IsGuardOpt4" class="label_for_radio"><s:message
											code="notifyIsGuardOpt4" /> </label>
								</span> <span ng-show="IsGuardOpt4"><s:message
										code="notifyIsGuardOpt4Other" /> </span>
								<div class="form_input form_input_search_half"
									ng-show="IsGuardOpt4">
									<input type="text" id="IsGuardOpt4Other"
										name="IsGuardOpt4Other" ng-model="IsGuardOpt4Other"
										class="form-control"
										placeholder="<s:message code="notifyIsGuardOpt4Other" />"
										autocomplete="off" ng-required="IsGuardOpt4">
								</div>
								<h5 class="text-danger"
								ng-show="editForm.IsGuardOpt4Other.$error.required && editForm.IsGuardOpt4Other.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifyIsGuardOpt4Other" />
							</h5>
							<h5 class="text-danger"
								ng-show="!IsGuardOpt1 && !IsGuardOpt2 && !IsGuardOpt3 && !IsGuardOpt4">
								<s:message code="pleaseSelectMoreThenOne" />
							</h5>
							</div>						
						</div>
						<div>
							<div class="form_group">
								<label for="Soc"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifySoc" /></label> <span> <input type="radio"
									id="SocOpt0" name="SocOpt" ng-model="SocOpt" ng-value="0"><label
									for="SocOpt0" class="label_for_radio"><s:message
											code="notifySocOpt0" /> </label>
								</span> <span> <input type="radio" id="SocOpt1" name="SocOpt"
									ng-model="SocOpt" ng-value="1"><label for="SocOpt1"
									class="label_for_radio"><s:message code="notifySocOpt1" />
								</label>
								</span> <span> <input type="radio" id="SocOpt2" name="SocOpt"
									ng-model="SocOpt" ng-value="2"><label for="SocOpt2"
									class="label_for_radio"><s:message code="notifySocOpt2" />
								</label>
								</span>
								<div class="form_input form_input_search_half"
									ng-show="SocOpt==2">
									<input type="text" id="SocOptCompany" name="SocOptCompany"
										ng-model="SocOptCompany" class="form-control"
										placeholder="<s:message code="notifySocOptCompany" />"
										autocomplete="off" ng-required="SocOpt==2">
								</div>
								<h5 class="text-danger"
								ng-show="editForm.SocOptCompany.$error.required && editForm.SocOptCompany.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifySocOptCompany" />
							</h5>
							</div>							
						</div>
						<div>
							<div class="form_group">
								<label for="IsIsms"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsms" /></label>
								<div class="form_input form_input_search">
									<toggle ng-model="IsIsms" ng-init="IsIsms=false"
										on='<i class="far fa-fw fa-check-circle"></i><s:message code="notifyIsIsmsTrue" />'
										off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="notifyIsIsmsFalse" />'
										onstyle="btn-success" offstyle="btn-danger"></toggle>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="MaintainCompany"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyMaintainCompany" /></label>
								<div class="form_input form_input_search">
									<input type="text" id="MaintainCompany" name="MaintainCompany"
										ng-model="MaintainCompany" class="form-control"
										placeholder="<s:message code="notifyMaintainCompany" />"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsTest"
									class="form_label form_label_search form_label_gray">是否為演練</label>
								<div class="form_input form_input_search">
									<toggle ng-model="IsTest"
										ng-init="IsTest=false"
										on='<i class="far fa-fw fa-check-circle"></i>是'
										off='<i class="fas fa-fw fa-minus-circle"></i>否'
										offstyle="btn-success" onstyle="btn-danger"></toggle>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep2" class="tab-pane active"
				ng-show="Status != 4 && Status != 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep2TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent3" /></b>
						</h4>
					</div>
					<div class="col-xs-12">
						<h5 class="form_heading text-danger">
							<s:message code="notifyContent3Description" />
						</h5>
						<div>
							<div class="form_group">
								<label for="CeffectLevel"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyCeffectLevel" /></label><label for="CeffectLevel4"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "CeffectLevel==null"
										id="CeffectLevel4" name="CeffectLevel" ng-model="CeffectLevel"
										ng-value="4" ng-click="change_effect()"> <s:message
											code="notifyCeffectLevel4" />
								</span></label><label for="CeffectLevel3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"  ng-required = "CeffectLevel==null"
										id="CeffectLevel3" name="CeffectLevel" ng-model="CeffectLevel"
										ng-value="3" ng-click="change_effect()"> <s:message
											code="notifyCeffectLevel3" />
								</span> </label><label for="CeffectLevel2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"  ng-required = "CeffectLevel==null"
										id="CeffectLevel2" name="CeffectLevel" ng-model="CeffectLevel"
										ng-value="2" ng-click="change_effect()"> <s:message
											code="notifyCeffectLevel2" />
								</span> </label><label for="CeffectLevel1" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"  ng-required = "CeffectLevel==null"
										id="CeffectLevel1" name="CeffectLevel" ng-model="CeffectLevel"
										ng-value="1" ng-click="change_effect()"> <s:message
											code="notifyCeffectLevel1" />
								</span> </label><label for="CeffectLevel0" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"  ng-required = "CeffectLevel==null"
										id="CeffectLevel0" name="CeffectLevel" ng-model="CeffectLevel"
										ng-value="0" ng-click="change_effect()"> <s:message
											code="notifyCeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IeffectLevel"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIeffectLevel" /></label><label for="IeffectLevel4"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"  ng-required = "IeffectLevel==null"
										id="IeffectLevel4" name="IeffectLevel" ng-model="IeffectLevel"
										ng-value="4" ng-click="change_effect()"> <s:message
											code="notifyIeffectLevel4" />
								</span></label><label for="IeffectLevel3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "IeffectLevel==null"
										id="IeffectLevel3" name="IeffectLevel" ng-model="IeffectLevel"
										ng-value="3" ng-click="change_effect()"> <s:message
											code="notifyIeffectLevel3" />
								</span> </label><label for="IeffectLevel2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "IeffectLevel==null"
										id="IeffectLevel2" name="IeffectLevel" ng-model="IeffectLevel"
										ng-value="2" ng-click="change_effect()"> <s:message
											code="notifyIeffectLevel2" />
								</span> </label><label for="IeffectLevel1" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "IeffectLevel==null"
										id="IeffectLevel1" name="IeffectLevel" ng-model="IeffectLevel"
										ng-value="1" ng-click="change_effect()"> <s:message
											code="notifyIeffectLevel1" />
								</span> </label><label for="IeffectLevel0" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "IeffectLevel==null"
										id="IeffectLevel0" name="IeffectLevel" ng-model="IeffectLevel"
										ng-value="0" ng-click="change_effect()"> <s:message
											code="notifyIeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="AeffectLevel"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyAeffectLevel" /></label><label for="AeffectLevel4"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "AeffectLevel==null"
										id="AeffectLevel4" name="AeffectLevel" ng-model="AeffectLevel"
										ng-value="4" ng-click="change_effect()"> <s:message
											code="notifyAeffectLevel4" />
								</span></label><label for="AeffectLevel3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "AeffectLevel==null"
										id="AeffectLevel3" name="AeffectLevel" ng-model="AeffectLevel"
										ng-value="3" ng-click="change_effect()"> <s:message
											code="notifyAeffectLevel3" />
								</span> </label><label for="AeffectLevel2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "AeffectLevel==null"
										id="AeffectLevel2" name="AeffectLevel" ng-model="AeffectLevel"
										ng-value="2" ng-click="change_effect()"> <s:message
											code="notifyAeffectLevel2" />
								</span> </label><label for="AeffectLevel1" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "AeffectLevel==null"
										id="AeffectLevel1" name="AeffectLevel" ng-model="AeffectLevel"
										ng-value="1" ng-click="change_effect()"> <s:message
											code="notifyAeffectLevel1" />
								</span> </label><label for="AeffectLevel0" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio" ng-required = "AeffectLevel==null"
										id="AeffectLevel0" name="AeffectLevel" ng-model="AeffectLevel"
										ng-value="0" ng-click="change_effect()"> <s:message
											code="notifyAeffectLevel0" /></span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="EffectLevel"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyEffectLevel" /></label> <span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 0"><s:message
										code="notifyEffectLevel0" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 1"><s:message
										code="notifyEffectLevel1" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 2"><s:message
										code="notifyEffectLevel2" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 3"><s:message
										code="notifyEffectLevel3" /> </span><span
									class="form-text form_text form_input_search"
									ng-show="EffectLevel == 4"><s:message
										code="notifyEffectLevel4" /> </span>
							</div>
						</div>
						<div ng-if="NotificationLevelLog.length > 0">
				<div class="form_group">
					<h4 class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>事件影響等級紀錄</b></i>
					</h4>
				</div>
				<div class="form_group">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><s:message code="notifyCeffectLevel" /></th>
								<th><s:message code="notifyIeffectLevel" /></th>
								<th><s:message code="notifyAeffectLevel" /></th>
								<th><s:message code="notifyEffectLevel" /></th>	
								<th><s:message code="globalProcessLogFrom" /></th>							
								<th><s:message code="globalProcessLogSednTime" /></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in NotificationLevelLog">
								<td>
								<span ng-show="item.CeffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.CeffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.CeffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.CeffectLevel==3"><s:message code="notifyEffectLevel3" /></span>							
								<span ng-show="item.CeffectLevel==4"><s:message code="notifyEffectLevel4" /></span>							
								</td>
								<td>
								<span ng-show="item.IeffectLevel==0"><s:message code="notifyIeffectLevel0" /></span>
								<span ng-show="item.IeffectLevel==1"><s:message code="notifyIeffectLevel1" /></span>
								<span ng-show="item.IeffectLevel==2"><s:message code="notifyIeffectLevel2" /></span>
								<span ng-show="item.IeffectLevel==3"><s:message code="notifyIeffectLevel3" /></span>							
								<span ng-show="item.IeffectLevel==4"><s:message code="notifyIeffectLevel4" /></span>			
								</td>
								<td>
								<span ng-show="item.AeffectLevel==0"><s:message code="notifyAeffectLevel0" /></span>
								<span ng-show="item.AeffectLevel==1"><s:message code="notifyAeffectLevel1" /></span>
								<span ng-show="item.AeffectLevel==2"><s:message code="notifyAeffectLevel2" /></span>
								<span ng-show="item.AeffectLevel==3"><s:message code="notifyAeffectLevel3" /></span>
								<span ng-show="item.AeffectLevel==4"><s:message code="notifyAeffectLevel4" /></span>
								</td>
								<td>
								<span ng-show="item.EffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.EffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.EffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.EffectLevel==3"><s:message code="notifyEffectLevel3" /></span>
								<span ng-show="item.EffectLevel==4"><s:message code="notifyEffectLevel4" /></span>
								</td>
								<td>{{item.CreateName}}</td>
								<td>{{item.CreateTime}}</td>								
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_group">
					<br />&nbsp;<br />
				</div>
			</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep3" class="tab-pane active"
				ng-show="Status != 4 && Status != 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep3TitleDetail" /></b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent3" /></b>
						</h4>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="EventType"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyEventType" /></label><label for="EventType1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"
										id="EventType1" name="EventType" ng-model="EventType"
										ng-value="1"> <s:message code="notifyEventType1" />
								</span> <!-- Event 1 opt Start --> <span ng-show="EventType==1">(&nbsp;<input
										type="checkbox" id="IsEventType1Opt1" name="IsEventType1Opt1"
										ng-required="EventType==1 && !IsEventType1Opt1 && !IsEventType1Opt2 && !IsEventType1Opt3 && !IsEventType1Opt4 && !IsEventType1Opt5 && !IsEventType1Opt6"
										ng-model="IsEventType1Opt1"><label
										for="IsEventType1Opt1" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt1" /></label></span><span
									ng-show="EventType==1"><input type="checkbox"
										id="IsEventType1Opt2" name="IsEventType1Opt2"
										ng-model="IsEventType1Opt2"><label
										for="IsEventType1Opt2" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt2" /></label> </span><span
									ng-show="EventType==1"><input type="checkbox"
										id="IsEventType1Opt3" name="IsEventType1Opt3"
										ng-model="IsEventType1Opt3"><label
										for="IsEventType1Opt3" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt3" /></label> </span><span
									ng-show="EventType==1"><input type="checkbox"
										id="IsEventType1Opt4" name="IsEventType1Opt4"
										ng-model="IsEventType1Opt4"><label
										for="IsEventType1Opt4" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt4" /></label> </span><span
									ng-show="EventType==1"><input type="checkbox"
										id="IsEventType1Opt5" name="IsEventType1Opt5"
										ng-model="IsEventType1Opt5"><label
										for="IsEventType1Opt5" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt5" /></label> </span><span
									ng-show="EventType==1"><input type="checkbox"
										id="IsEventType1Opt6" name="IsEventType1Opt6"
										ng-model="IsEventType1Opt6"><label
										for="IsEventType1Opt6" class="label_for_radio"><s:message
												code="notifyIsEventType1Opt6" /></label> )</span> <!-- Event 1 opt End -->
									<h5 class="text-danger"
										ng-show="EventType==1 && !IsEventType1Opt1 && !IsEventType1Opt2 && !IsEventType1Opt3 && !IsEventType1Opt4 && !IsEventType1Opt5 && !IsEventType1Opt6">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label> <label for="EventType2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"
										id="EventType2" name="EventType" ng-model="EventType"
										ng-value="2"> <s:message code="notifyEventType2" />
								</span> <!-- Event 2 opt Start --> <span ng-show="EventType==2">(&nbsp;<input
										type="checkbox" id="IsEventType2Opt1" name="IsEventType2Opt1"
										ng-required="EventType==2 && !IsEventType2Opt1 && !IsEventType2Opt2 && !IsEventType2Opt3 && !IsEventType2Opt4 && !IsEventType2Opt5"
										ng-model="IsEventType2Opt1"><label
										for="IsEventType2Opt1" class="label_for_radio"><s:message
												code="notifyIsEventType2Opt1" /></label></span><span
									ng-show="EventType==2"><input type="checkbox"
										id="IsEventType2Opt2" name="IsEventType2Opt2"
										ng-model="IsEventType2Opt2"><label
										for="IsEventType2Opt2" class="label_for_radio"><s:message
												code="notifyIsEventType2Opt2" /></label> </span><span
									ng-show="EventType==2"><input type="checkbox"
										id="IsEventType2Opt3" name="IsEventType2Opt3"
										ng-model="IsEventType2Opt3"><label
										for="IsEventType2Opt3" class="label_for_radio"><s:message
												code="notifyIsEventType2Opt3" /></label> </span><span
									ng-show="EventType==2"><input type="checkbox"
										id="IsEventType2Opt4" name="IsEventType2Opt4"
										ng-model="IsEventType2Opt4"><label
										for="IsEventType2Opt4" class="label_for_radio"><s:message
												code="notifyIsEventType2Opt4" /></label> </span><span
									ng-show="EventType==2"><input type="checkbox"
										id="IsEventType2Opt5" name="IsEventType2Opt5"
										ng-model="IsEventType2Opt5"><label
										for="IsEventType2Opt5" class="label_for_radio"><s:message
												code="notifyIsEventType2Opt5" /></label> )</span> <!-- Event 2 opt End -->
									<h5 class="text-danger"
										ng-show="EventType==2 && !IsEventType2Opt1 && !IsEventType2Opt2 && !IsEventType2Opt3 && !IsEventType2Opt4 && !IsEventType2Opt5">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label> <label for="EventType3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"
										id="EventType3" name="EventType" ng-model="EventType"
										ng-value="3"> <s:message code="notifyEventType3" />
								</span> <!-- Event 3 opt Start --> <span ng-show="EventType==3">(&nbsp;<input
										type="checkbox" id="IsEventType3Opt1" name="IsEventType3Opt1"
										ng-required="EventType==3 && !IsEventType3Opt1 && !IsEventType3Opt2"
										ng-model="IsEventType3Opt1"><label
										for="IsEventType3Opt1" class="label_for_radio"><s:message
												code="notifyIsEventType3Opt1" /></label></span><span
									ng-show="EventType==3"><input type="checkbox"
										id="IsEventType3Opt2" name="IsEventType3Opt2"
										ng-model="IsEventType3Opt2"><label
										for="IsEventType3Opt2" class="label_for_radio"><s:message
												code="notifyIsEventType3Opt2" /></label> )</span> <!-- Event 3 opt End -->
									<h5 class="text-danger"
										ng-show="EventType==3 && !IsEventType3Opt1 && !IsEventType3Opt2">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label> <label for="EventType4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"
										id="EventType4" name="EventType" ng-model="EventType"
										ng-value="4"> <s:message code="notifyEventType4" />
								</span> <!-- Event 2 opt Start --> <span ng-show="EventType==4">(&nbsp;<input
										type="checkbox" id="IsEventType4Opt1" name="IsEventType4Opt1"
										ng-required="EventType==4 && !IsEventType4Opt1 && !IsEventType4Opt2 && !IsEventType4Opt3 && !IsEventType4Opt4"
										ng-model="IsEventType4Opt1"><label
										for="IsEventType4Opt1" class="label_for_radio"><s:message
												code="notifyIsEventType4Opt1" /></label></span><span
									ng-show="EventType==4"><input type="checkbox"
										id="IsEventType4Opt2" name="IsEventType4Opt2"
										ng-model="IsEventType4Opt2"><label
										for="IsEventType4Opt2" class="label_for_radio"><s:message
												code="notifyIsEventType4Opt2" /></label> </span><span
									ng-show="EventType==4"><input type="checkbox"
										id="IsEventType4Opt3" name="IsEventType4Opt3"
										ng-model="IsEventType4Opt3"><label
										for="IsEventType4Opt3" class="label_for_radio"><s:message
												code="notifyIsEventType4Opt3" /></label> </span><span
									ng-show="EventType==4"><input type="checkbox"
										id="IsEventType4Opt4" name="IsEventType4Opt4"
										ng-model="IsEventType4Opt4"><label
										for="IsEventType4Opt4" class="label_for_radio"><s:message
												code="notifyIsEventType4Opt4" /></label> )</span> <!-- Event 4 opt End -->
									<h5 class="text-danger"
										ng-show="EventType==4 && !IsEventType4Opt1 && !IsEventType4Opt2 && !IsEventType4Opt3 && !IsEventType4Opt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label><label for="EventType5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="radio"
										id="EventType5" name="EventType" ng-model="EventType"
										ng-value="5"> <s:message code="notifyEventType5" />
								</span><input type="text" class="form-control form_input_half"
									id="EventType5Other" name="EventType5Other"
									ng-model="EventType5Other" ng-show="EventType==5"
									ng-required="EventType==5"
									placeholder="<s:message code="notifyEventType5Other" />" />
									<h5 class="text-danger"
										ng-show="editForm.EventType5Other.$error.required && editForm.EventType5Other.$dirty">
										<s:message code="pleaseEnter" />
										<s:message code="notifyEventType5Other" />
									</h5></label>
							</div>
						</div>
						<div ng-if="NotificationLevelLog.length > 0">
				<div class="form_group">
					<h4 class="form_heading form_heading form_heading_yellow">
						<i class="fa fa-info-circle"><b>事件影響等級紀錄</b></i>
					</h4>
				</div>
				<div class="form_group">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th><s:message code="notifyCeffectLevel" /></th>
								<th><s:message code="notifyIeffectLevel" /></th>
								<th><s:message code="notifyAeffectLevel" /></th>
								<th><s:message code="notifyEffectLevel" /></th>	
								<th><s:message code="globalProcessLogFrom" /></th>							
								<th><s:message code="globalProcessLogSednTime" /></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in NotificationLevelLog">
								<td>
								<span ng-show="item.CeffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.CeffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.CeffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.CeffectLevel==3"><s:message code="notifyEffectLevel3" /></span>							
								<span ng-show="item.CeffectLevel==4"><s:message code="notifyEffectLevel4" /></span>							
								</td>
								<td>
								<span ng-show="item.IeffectLevel==0"><s:message code="notifyIeffectLevel0" /></span>
								<span ng-show="item.IeffectLevel==1"><s:message code="notifyIeffectLevel1" /></span>
								<span ng-show="item.IeffectLevel==2"><s:message code="notifyIeffectLevel2" /></span>
								<span ng-show="item.IeffectLevel==3"><s:message code="notifyIeffectLevel3" /></span>							
								<span ng-show="item.IeffectLevel==4"><s:message code="notifyIeffectLevel4" /></span>			
								</td>
								<td>
								<span ng-show="item.AeffectLevel==0"><s:message code="notifyAeffectLevel0" /></span>
								<span ng-show="item.AeffectLevel==1"><s:message code="notifyAeffectLevel1" /></span>
								<span ng-show="item.AeffectLevel==2"><s:message code="notifyAeffectLevel2" /></span>
								<span ng-show="item.AeffectLevel==3"><s:message code="notifyAeffectLevel3" /></span>
								<span ng-show="item.AeffectLevel==4"><s:message code="notifyAeffectLevel4" /></span>
								</td>
								<td>
								<span ng-show="item.EffectLevel==0"><s:message code="notifyEffectLevel0" /></span>
								<span ng-show="item.EffectLevel==1"><s:message code="notifyEffectLevel1" /></span>
								<span ng-show="item.EffectLevel==2"><s:message code="notifyEffectLevel2" /></span>
								<span ng-show="item.EffectLevel==3"><s:message code="notifyEffectLevel3" /></span>
								<span ng-show="item.EffectLevel==4"><s:message code="notifyEffectLevel4" /></span>
								</td>
								<td>{{item.CreateName}}</td>
								<td>{{item.CreateTime}}</td>								
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_group">
					<br />&nbsp;<br />
				</div>
			</div>
						<div>
							<div class="form_group">
								<label for="EventRemark"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyEventRemark" /></label>
								<div class="form_input form_input_search">
									<textarea id="EventRemark" name="EventRemark"
										ng-model="EventRemark" class="form-control"
										placeholder="<s:message code="notifyEventRemark" />" rows="5"
										autocomplete="off"></textarea>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsAffectOthers"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsAffectOthers" /></label>
								<div class="form_input form_input_search">
									<toggle ng-model="IsAffectOthers"
										ng-init="IsAffectOthers=false"
										on='<i class="far fa-fw fa-check-circle"></i><s:message code="notifyIsAffectOthersTrue" />'
										off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="notifyIsAffectOthersFalse" />'
										onstyle="btn-success" offstyle="btn-danger"></toggle>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="EventSource"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifySource" /></label> <span> <input type="radio"
									id="EventSource1" name="EventSource" ng-model="EventSource"
									ng-value="1" ng-click="EventSourceNo=''"><label
									for="EventSource1" class="label_for_radio"><s:message
											code="notifySourceFromNotification" /> </label>
								</span> <span> <input type="radio" id="EventSource2"
									name="EventSource" ng-model="EventSource" ng-value="2"><label
									for="EventSource2" class="label_for_radio"><s:message
											code="notifySourceFromMessage" /> </label>
								</span>
								<div class="form_input form_input_search_half"
									ng-show="EventSource==2">
									<input type="text" id="EventSourceNo" name="EventSourceNo"
										ng-model="EventSourceNo" class="form-control"
										placeholder="<s:message code="notifySourceFromMessagePostId" />"
										autocomplete="off" ng-maxlength="128"
										ng-required="EventSource==2">
								</div>
							</div>
							<h5 class="text-danger"
								ng-show="editForm.EventSourceNo.$error.required && editForm.EventSourceNo.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifySourceFromMessagePostId" />
							</h5>
							<h5 class="text-danger"
								ng-show="editForm.EventSourceNo.$error.maxlength && editForm.EventSourceNo.$dirty">
								<s:message code="formatMaxLengthAfter" arguments="128" />
							</h5>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep4" class="tab-pane active"
				ng-show="Status != 4 && Status != 9">
				<div class="row" ng-show="EventType==1">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType1" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle1"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label for="IsRes1LogOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1LogOpt1" name="IsRes1LogOpt1"
										ng-required="EventType==1 && !IsRes1LogOpt1 && !IsRes1LogOpt2 && !IsRes1LogOpt3 && !IsRes1LogOpt4"
										ng-model="IsRes1LogOpt1" ng-value="1"> <s:message
											code="notifyIsRes1LogOpt1" />
								</span> <!-- Res 1 opt 1 Start --> <span>(&nbsp;<input
										type="radio" id="Res1LogOpt11" name="Res1LogOpt11"
										ng-model="Res1LogOpt1" ng-value="1"><label
										for="Res1LogOpt11" class="label_for_radio"><s:message
												code="notifyRes1LogOpt11" /></label><input type="radio"
										id="Res1LogOpt12" name="Res1LogOpt12" ng-model="Res1LogOpt1"
										ng-value="2"><label for="Res1LogOpt12"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt12" /></label><input type="radio"
										id="Res1LogOpt13" name="Res1LogOpt13" ng-model="Res1LogOpt1"
										ng-value="3"><label for="Res1LogOpt13"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt13" /></label><input type="radio"
										id="Res1LogOpt14" name="Res1LogOpt14" ng-model="Res1LogOpt1"
										ng-value="4"><label for="Res1LogOpt14"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt14" /></label><input type="text"
										id="Res1LogOpt1Other" name="Res1LogOpt1Other"
										ng-model="Res1LogOpt1Other"
										ng-required="EventType==1 && IsRes1LogOpt1 && Res1LogOpt1==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1LogOpt1Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res1LogOpt1Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1LogOpt1Other" />
								</span> <!-- Res 1 opt 1 End --> </label> <label for="IsRes1LogOpt2"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1LogOpt2" name="IsRes1LogOpt2"
										ng-model="IsRes1LogOpt2" ng-value="2"> <s:message
											code="notifyIsRes1LogOpt2" />
								</span> <!-- Res 1 opt 2 Start --> <span>(&nbsp;<input
										type="radio" id="Res1LogOpt21" name="Res1LogOpt21"
										ng-model="Res1LogOpt2" ng-value="1"><label
										for="Res1LogOpt21" class="label_for_radio"><s:message
												code="notifyRes1LogOpt21" /></label><input type="radio"
										id="Res1LogOpt22" name="Res1LogOpt22" ng-model="Res1LogOpt2"
										ng-value="2"><label for="Res1LogOpt22"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt22" /></label><input type="radio"
										id="Res1LogOpt23" name="Res1LogOpt23" ng-model="Res1LogOpt2"
										ng-value="3"><label for="Res1LogOpt23"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt23" /></label><input type="radio"
										id="Res1LogOpt24" name="Res1LogOpt24" ng-model="Res1LogOpt2"
										ng-value="4"><label for="Res1LogOpt24"
										class="label_for_radio"><s:message
												code="notifyRes1LogOpt24" /></label><input type="text"
										id="Res1LogOpt2Other" name="Res1LogOpt2Other"
										ng-model="Res1LogOpt2Other"
										ng-required="EventType==1 && IsRes1LogOpt2 && Res1LogOpt2==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1LogOpt2Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res1LogOpt2Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1LogOpt2Other" />
								</span> <!-- Res 1 opt 2 End --></label> <label for="IsRes1LogOpt3"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1LogOpt3" name="IsRes1LogOpt3"
										ng-model="IsRes1LogOpt3" ng-value="3"> <s:message
											code="notifyIsRes1LogOpt3" /><input type="number" min="0"
										id="Res1LogOpt3Amount" name="Res1LogOpt3Amount"
										ng-model="Res1LogOpt3Amount"
										ng-required="EventType==1 && IsRes1LogOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1LogOpt3Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes1LogOpt3Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res1LogOpt3Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1LogOpt3Amount" />
								</span></label> <label for="IsRes1LogOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1LogOpt4" name="IsRes1LogOpt4"
										ng-model="IsRes1LogOpt4" ng-value="4"> <s:message
											code="notifyIsRes1LogOpt4" /><input type="text"
										id="Res1LogOpt4Remark" name="Res1LogOpt4Remark"
										ng-model="Res1LogOpt4Remark"
										ng-required="EventType==1 && IsRes1LogOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1LogOpt4Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res1LogOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1LogOpt3Amount" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==1 && !IsRes1LogOpt1 && !IsRes1LogOpt2 && !IsRes1LogOpt3 && !IsRes1LogOpt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle2"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label for="IsRes1EvaOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt1" name="IsRes1EvaOpt1"
										ng-required="EventType==1 && !IsRes1EvaOpt1 && !IsRes1EvaOpt2 && !IsRes1EvaOpt3 && !IsRes1EvaOpt4 && !IsRes1EvaOpt5 && !IsRes1EvaOpt6 && !IsRes1EvaOpt7 && !IsRes1EvaOpt8"
										ng-model="IsRes1EvaOpt1" ng-value="1"> <s:message
											code="notifyIsRes1EvaOpt1" /><input type="text"
										id="Res1EvaOpt1Remark" name="Res1EvaOpt1Remark"
										ng-model="Res1EvaOpt1Remark"
										ng-required="EventType==1 && IsRes1EvaOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1EvaOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt1Remark" />
								</span></label> <label for="IsRes1EvaOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt2" name="IsRes1EvaOpt2"
										ng-model="IsRes1EvaOpt2" ng-value="2"> <s:message
											code="notifyIsRes1EvaOpt2" /><input type="text"
										id="Res1EvaOpt2Remark" name="Res1EvaOpt2Remark"
										ng-model="Res1EvaOpt2Remark"
										ng-required="EventType==1 && IsRes1EvaOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1EvaOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt2Remark" />
								</span></label> <label for="IsRes1EvaOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt3" name="IsRes1EvaOpt3"
										ng-model="IsRes1EvaOpt3" ng-value="3"> <s:message
											code="notifyIsRes1EvaOpt3" /><input type="text"
										id="Res1EvaOpt3Remark" name="Res1EvaOpt3Remark"
										ng-model="Res1EvaOpt3Remark"
										ng-required="EventType==1 && IsRes1EvaOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1EvaOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt3Remark" />
								</span></label> <label for="IsRes1EvaOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt4" name="IsRes1EvaOpt4"
										ng-model="IsRes1EvaOpt4" ng-value="4"> <s:message
											code="notifyIsRes1EvaOpt4" /><input type="text"
										id="Res1EvaOpt4Remark" name="Res1EvaOpt4Remark"
										ng-model="Res1EvaOpt4Remark"
										ng-required="EventType==1 && IsRes1EvaOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt4Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1EvaOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt4Remark" />
								</span></label> <label for="IsRes1EvaOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt5" name="IsRes1EvaOpt5"
										ng-model="IsRes1EvaOpt5" ng-value="5"> <s:message
											code="notifyIsRes1EvaOpt5" />
								</span> </label> <label for="IsRes1EvaOpt6" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt6" name="IsRes1EvaOpt6"
										ng-model="IsRes1EvaOpt6" ng-value="6"> <s:message
											code="notifyIsRes1EvaOpt6" /><input type="number" min="0"
										id="Res1EvaOpt6Amount" name="Res1EvaOpt6Amount"
										ng-model="Res1EvaOpt6Amount"
										ng-required="EventType==1 && IsRes1EvaOpt6"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt6Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes1EvaOpt6Unit" />
								</span> <!-- Res 2 opt 6 Start --> <span><input type="radio"
										id="Res1EvaOpt6Type1" name="Res1EvaOpt6Type1"
										ng-model="Res1EvaOpt6Type" ng-value="1"><label
										for="Res1EvaOpt6Type1" class="label_for_radio"><s:message
												code="notifyRes1EvaOpt6Type1" /></label><input type="radio"
										id="Res1EvaOpt6Type2" name="Res1EvaOpt6Type2"
										ng-model="Res1EvaOpt6Type" ng-value="2"><label
										for="Res1EvaOpt6Type2" class="label_for_radio"><s:message
												code="notifyRes1EvaOpt6Type2" /></label><input type="radio"
										id="Res1EvaOpt6Type3" name="Res1EvaOpt6Type3"
										ng-model="Res1EvaOpt6Type" ng-value="3"><label
										for="Res1EvaOpt6Type3" class="label_for_radio"><s:message
												code="notifyRes1EvaOpt6Type3" /></label><input type="text"
										id="Res1EvaOpt6TypeOpt3Other" name="Res1EvaOpt6TypeOpt3Other"
										ng-model="Res1EvaOpt6TypeOpt3Other"
										ng-required="EventType==1 && IsRes1EvaOpt6 && Res1EvaOpt6Type==3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt6TypeOpt3Other" />"
										autocomplete="off"> </span> <span class="text-danger"
									ng-show="editForm.Res1EvaOpt6Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt6Amount" />
								</span><span class="text-danger"
									ng-show="editForm.Res1EvaOpt6TypeOpt3Other.$error.required">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt6TypeOpt3Other" />
								</span> <!-- Res 2 opt 6 End --></label> <label for="IsRes1EvaOpt7"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt7" name="IsRes1EvaOpt7"
										ng-model="IsRes1EvaOpt7" ng-value="7"> <s:message
											code="notifyIsRes1EvaOpt7" /><input type="number" min="0"
										id="Res1EvaOpt7Amount" name="Res1EvaOpt7Amount"
										ng-model="Res1EvaOpt7Amount"
										ng-required="EventType==1 && IsRes1EvaOpt7"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt7Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes1EvaOpt7Unit" /><input type="text"
										id="Res1EvaOpt7Remark" name="Res1EvaOpt7Remark"
										ng-model="Res1EvaOpt7Remark"
										ng-required="EventType==1 && IsRes1EvaOpt7"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt7Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res1EvaOpt7Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt7Amount" />
								</span><span class="text-danger"
									ng-show="editForm.Res1EvaOpt7Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt7Remark" />
								</span></label> <label for="IsRes1EvaOpt8" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1EvaOpt8" name="IsRes1EvaOpt8"
										ng-model="IsRes1EvaOpt8" ng-value="8"> <s:message
											code="notifyIsRes1EvaOpt8" /><input type="text"
										id="Res1EvaOpt8Remark" name="Res1EvaOpt8Remark"
										ng-model="Res1EvaOpt8Remark"
										ng-required="EventType==1 && IsRes1EvaOpt8"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1EvaOpt8Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res1EvaOpt8Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1EvaOpt8Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==1 && !IsRes1EvaOpt1 && !IsRes1EvaOpt2 && !IsRes1EvaOpt3 && !IsRes1EvaOpt4 && !IsRes1EvaOpt5 && !IsRes1EvaOpt6 && !IsRes1EvaOpt7 && !IsRes1EvaOpt8">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle3"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label for="IsRes1DoOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt1" name="IsRes1DoOpt1" ng-model="IsRes1DoOpt1"
										ng-required="EventType==1 && !IsRes1DoOpt1 && !IsRes1DoOpt2 && !IsRes1DoOpt3 && !IsRes1DoOpt4 && !IsRes1DoOpt5 && !IsRes1DoOpt6 && !IsRes1DoOpt7 && !IsRes1DoOpt8 && !IsRes1DoOpt9 && !IsRes1DoOpt10 && !IsRes1DoOpt11 && !IsRes1DoOpt12"
										ng-value="1"> <s:message code="notifyIsRes1DoOpt1" /><input
										type="number" id="Res1DoOpt1Amount" name="Res1DoOpt1Amount"
										ng-model="Res1DoOpt1Amount"
										ng-required="EventType==1 && IsRes1DoOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt1Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes1DoOpt1Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt1Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt1Amount" />
								</span></label> <label for="IsRes1DoOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt2" name="IsRes1DoOpt2" ng-model="IsRes1DoOpt2"
										ng-value="2"> <s:message code="notifyIsRes1DoOpt2" /><input
										type="text" id="Res1DoOpt2Remark" name="Res1DoOpt2Remark"
										ng-model="Res1DoOpt2Remark"
										ng-required="EventType==1 && IsRes1DoOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt2Remark" />
								</span></label> <label for="IsRes1DoOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt3" name="IsRes1DoOpt3" ng-model="IsRes1DoOpt3"
										ng-value="3"> <s:message code="notifyIsRes1DoOpt3" /><input
										type="text" id="Res1DoOpt3Remark" name="Res1DoOpt3Remark"
										ng-model="Res1DoOpt3Remark"
										ng-required="EventType==1 && IsRes1DoOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt3Remark" />
								</span></label> <label for="IsRes1DoOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt4" name="IsRes1DoOpt4" ng-model="IsRes1DoOpt4"
										ng-value="4"> <s:message code="notifyIsRes1DoOpt4" /><input
										type="text" id="Res1DoOpt4Remark" name="Res1DoOpt4Remark"
										ng-model="Res1DoOpt4Remark"
										ng-required="EventType==1 && IsRes1DoOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt4Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt4Remark" />
								</span></label> <label for="IsRes1DoOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt5" name="IsRes1DoOpt5" ng-model="IsRes1DoOpt5"
										ng-value="5"> <s:message code="notifyIsRes1DoOpt5" /><input
										type="number" id="Res1DoOpt5Amount" name="Res1DoOpt5Amount"
										ng-model="Res1DoOpt5Amount"
										ng-required="EventType==1 && IsRes1DoOpt5"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt5Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes1DoOpt5Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt5Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt5Amount" />
								</span></label> <label for="IsRes1DoOpt6" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt6" name="IsRes1DoOpt6" ng-model="IsRes1DoOpt6"
										ng-value="6"> <s:message code="notifyIsRes1DoOpt6" />
								</span> </label> <label for="IsRes1DoOpt7" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt7" name="IsRes1DoOpt7" ng-model="IsRes1DoOpt7"
										ng-value="7"> <s:message code="notifyIsRes1DoOpt7" />
								</span> </label> <label for="IsRes1DoOpt8" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt8" name="IsRes1DoOpt8" ng-model="IsRes1DoOpt8"
										ng-value="8"> <s:message code="notifyIsRes1DoOpt8" />
								</span> </label> <label for="IsRes1DoOpt9" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt9" name="IsRes1DoOpt9" ng-model="IsRes1DoOpt9"
										ng-value="9"> <s:message code="notifyIsRes1DoOpt9" />
								</span> <!-- Res 3 opt 9 Start --> <span>(&nbsp;<input
										type="checkbox" id="IsRes1DoOpt9EngineOpt1"
										name="IsRes1DoOpt9EngineOpt1"
										ng-required="EventType==1 && IsRes1DoOpt9 && !IsRes1DoOpt9EngineOpt1 && !IsRes1DoOpt9EngineOpt2 && !IsRes1DoOpt9EngineOpt3 && !IsRes1DoOpt9EngineOpt4 && !IsRes1DoOpt9EngineOpt5 && !IsRes1DoOpt9EngineOpt6"
										ng-model="IsRes1DoOpt9EngineOpt1" ng-value="1"><label
										for="IsRes1DoOpt9EngineOpt1" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt1" /></label><input
										type="checkbox" id="IsRes1DoOpt9EngineOpt2"
										name="IsRes1DoOpt9EngineOpt2"
										ng-model="IsRes1DoOpt9EngineOpt2" ng-value="2"><label
										for="IsRes1DoOpt9EngineOpt2" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt2" /></label><input
										type="checkbox" id="IsRes1DoOpt9EngineOpt3"
										name="IsRes1DoOpt9EngineOpt3"
										ng-model="IsRes1DoOpt9EngineOpt3" ng-value="3"><label
										for="IsRes1DoOpt9EngineOpt3" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt3" /></label><input
										type="checkbox" id="IsRes1DoOpt9EngineOpt4"
										name="IsRes1DoOpt9EngineOpt4"
										ng-model="IsRes1DoOpt9EngineOpt4" ng-value="4"><label
										for="IsRes1DoOpt9EngineOpt4" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt4" /></label><input
										type="checkbox" id="IsRes1DoOpt9EngineOpt5"
										name="IsRes1DoOpt9EngineOpt5"
										ng-model="IsRes1DoOpt9EngineOpt5" ng-value="5"><label
										for="IsRes1DoOpt9EngineOpt5" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt5" /></label><input
										type="checkbox" id="IsRes1DoOpt9EngineOpt6"
										name="IsRes1DoOpt9EngineOpt6"
										ng-model="IsRes1DoOpt9EngineOpt6" ng-value="6"><label
										for="IsRes1DoOpt9EngineOpt6" class="label_for_radio"><s:message
												code="notifyIsRes1DoOpt9EngineOpt6" /></label><input type="text"
										id="Res1DoOpt9EngineOpt6Other"
										name="Res1DoOpt9EngineOpt6Other"
										ng-model="Res1DoOpt9EngineOpt6Other"
										ng-required="EventType==1 && IsRes1DoOpt9 && IsRes1DoOpt9EngineOpt6"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt9EngineOpt6Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="EventType==1 && IsRes1DoOpt9 && !IsRes1DoOpt9EngineOpt1 && !IsRes1DoOpt9EngineOpt2 && !IsRes1DoOpt9EngineOpt3 && !IsRes1DoOpt9EngineOpt4 && !IsRes1DoOpt9EngineOpt5 && !IsRes1DoOpt9EngineOpt6">
										<s:message code="pleaseSelectMoreThenOne" />
								</span><span class="text-danger"
									ng-show="editForm.Res1DoOpt9EngineOpt6Other.$error.required">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt9EngineOpt6Other" />
								</span> <!-- Res 3 opt 9 End --> </label> <label for="IsRes1DoOpt10"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt10" name="IsRes1DoOpt10"
										ng-model="IsRes1DoOpt10" ng-value="10"> <s:message
											code="notifyIsRes1DoOpt10" />
										<div class="input-group">
											<input type="text" id="Res1DoOpt10Date"
												name="Res1DoOpt10Date" ng-model="Res1DoOpt10Date"
												ng-required="EventType==1 && IsRes1DoOpt10"
												class="form-control"
												placeholder="<s:message code="notifyRes1DoOpt10Date" />"
												autocomplete="off"><span class="input-group-addon">
												<i class="fas fa-calendar-alt"></i>
											</span>
										</div>
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt10Date.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt10Date" />
								</span></label> <label for="IsRes1DoOpt11" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt11" name="IsRes1DoOpt11"
										ng-model="IsRes1DoOpt11" ng-value="11"> <s:message
											code="notifyIsRes1DoOpt11" />
										<div class="input-group">
											<input type="text" id="Res1DoOpt11Date"
												name="Res1DoOpt11Date" ng-model="Res1DoOpt11Date"
												ng-required="EventType==1 && IsRes1DoOpt11"
												class="form-control"
												placeholder="<s:message code="notifyRes1DoOpt11Date" />"
												autocomplete="off"><span class="input-group-addon">
												<i class="fas fa-calendar-alt"></i>
											</span>
										</div>
								</span> <span class="text-danger"
									ng-show="editForm.Res1DoOpt11Date.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt11Date" />
								</span></label> <label for="IsRes1DoOpt12" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes1DoOpt12" name="IsRes1DoOpt12"
										ng-model="IsRes1DoOpt12" ng-value="12"> <s:message
											code="notifyIsRes1DoOpt12" /><input type="text"
										id="Res1DoOpt12Remark" name="Res1DoOpt12Remark"
										ng-model="Res1DoOpt12Remark"
										ng-required="EventType==1 && IsRes1DoOpt12"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes1DoOpt12Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res1DoOpt12Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes1DoOpt12Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==1 && !IsRes1DoOpt1 && !IsRes1DoOpt2 && !IsRes1DoOpt3 && !IsRes1DoOpt4 && !IsRes1DoOpt5 && !IsRes1DoOpt6 && !IsRes1DoOpt7 && !IsRes1DoOpt8 && !IsRes1DoOpt9 && !IsRes1DoOpt10 && !IsRes1DoOpt11 && !IsRes1DoOpt12">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==2">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType2" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle1"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label for="IsRes2LogOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2LogOpt1" name="IsRes2LogOpt1"
										ng-required="EventType==2 && !IsRes2LogOpt1 && !IsRes2LogOpt2 && !IsRes2LogOpt3 && !IsRes2LogOpt4"
										ng-model="IsRes2LogOpt1" ng-value="1"> <s:message
											code="notifyIsRes2LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<input
										type="radio" id="Res2LogOpt11" name="Res2LogOpt11"
										ng-model="Res2LogOpt1" ng-value="1"><label
										for="Res2LogOpt11" class="label_for_radio"><s:message
												code="notifyRes2LogOpt11" /></label><input type="radio"
										id="Res2LogOpt12" name="Res2LogOpt12" ng-model="Res2LogOpt1"
										ng-value="2"><label for="Res2LogOpt12"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt12" /></label><input type="radio"
										id="Res2LogOpt13" name="Res2LogOpt13" ng-model="Res2LogOpt1"
										ng-value="3"><label for="Res2LogOpt13"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt13" /></label><input type="radio"
										id="Res2LogOpt14" name="Res2LogOpt14" ng-model="Res2LogOpt1"
										ng-value="4"><label for="Res2LogOpt14"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt14" /></label><input type="text"
										id="Res2LogOpt1Other" name="Res2LogOpt1Other"
										ng-model="Res2LogOpt1Other"
										ng-required="EventType==2 && IsRes2LogOpt1 && Res2LogOpt1==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2LogOpt1Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res2LogOpt1Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2LogOpt1Other" />
								</span> <!-- Res 2 opt 1 End --> </label> <label for="IsRes2LogOpt2"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2LogOpt2" name="IsRes2LogOpt2"
										ng-model="IsRes2LogOpt2" ng-value="2"> <s:message
											code="notifyIsRes2LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<input
										type="radio" id="Res2LogOpt21" name="Res2LogOpt21"
										ng-model="Res2LogOpt2" ng-value="1"><label
										for="Res2LogOpt21" class="label_for_radio"><s:message
												code="notifyRes2LogOpt21" /></label><input type="radio"
										id="Res2LogOpt22" name="Res2LogOpt22" ng-model="Res2LogOpt2"
										ng-value="2"><label for="Res2LogOpt22"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt22" /></label><input type="radio"
										id="Res2LogOpt23" name="Res2LogOpt23" ng-model="Res2LogOpt2"
										ng-value="3"><label for="Res2LogOpt23"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt23" /></label><input type="radio"
										id="Res2LogOpt24" name="Res2LogOpt24" ng-model="Res2LogOpt2"
										ng-value="4"><label for="Res2LogOpt24"
										class="label_for_radio"><s:message
												code="notifyRes2LogOpt24" /></label><input type="text"
										id="Res2LogOpt2Other" name="Res2LogOpt2Other"
										ng-model="Res2LogOpt2Other"
										ng-required="EventType==2 && IsRes2LogOpt2 && Res2LogOpt2==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2LogOpt2Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res2LogOpt2Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2LogOpt2Other" />
								</span> <!-- Res 2 opt 2 End --></label> <label for="IsRes2LogOpt3"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2LogOpt3" name="IsRes2LogOpt3"
										ng-model="IsRes2LogOpt3" ng-value="3"> <s:message
											code="notifyIsRes2LogOpt3" /><input type="number" min="0"
										id="Res2LogOpt3Amount" name="Res2LogOpt3Amount"
										ng-model="Res2LogOpt3Amount"
										ng-required="EventType==2 && IsRes2LogOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2LogOpt3Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes2LogOpt3Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res2LogOpt3Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2LogOpt3Amount" />
								</span></label> <label for="IsRes2LogOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2LogOpt4" name="IsRes2LogOpt4"
										ng-model="IsRes2LogOpt4" ng-value="4"> <s:message
											code="notifyIsRes2LogOpt4" /><input type="text"
										id="Res2LogOpt4Remark" name="Res2LogOpt4Remark"
										ng-model="Res2LogOpt4Remark"
										ng-required="EventType==2 && IsRes2LogOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2LogOpt4Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res2LogOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2LogOpt4Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==2 && !IsRes2LogOpt1 && !IsRes2LogOpt2 && !IsRes2LogOpt3 && !IsRes2LogOpt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle2"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label for="IsRes2EvaOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2EvaOpt1" name="IsRes2EvaOpt1"
										ng-required="EventType==2 && !IsRes2EvaOpt1 && !IsRes2EvaOpt2 && !IsRes2EvaOpt3 && !IsRes2EvaOpt4 && !IsRes2EvaOpt5"
										ng-model="IsRes2EvaOpt1" ng-value="1"> <s:message
											code="notifyIsRes2EvaOpt1" /><input type="text"
										id="Res2EvaOpt1Remark" name="Res2EvaOpt1Remark"
										ng-model="Res2EvaOpt1Remark"
										ng-required="EventType==2 && IsRes2EvaOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2EvaOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2EvaOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2EvaOpt1Remark" />
								</span></label> <label for="IsRes2EvaOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2EvaOpt2" name="IsRes2EvaOpt2"
										ng-model="IsRes2EvaOpt2" ng-value="2"> <s:message
											code="notifyIsRes2EvaOpt2" /><input type="text"
										id="Res2EvaOpt2Remark" name="Res2EvaOpt2Remark"
										ng-model="Res2EvaOpt2Remark"
										ng-required="EventType==2 && IsRes2EvaOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2EvaOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2EvaOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2EvaOpt2Remark" />
								</span></label> <label for="IsRes2EvaOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2EvaOpt3" name="IsRes2EvaOpt3"
										ng-model="IsRes2EvaOpt3" ng-value="3"> <s:message
											code="notifyIsRes2EvaOpt3" /><input type="text"
										id="Res2EvaOpt3Remark" name="Res2EvaOpt3Remark"
										ng-model="Res2EvaOpt3Remark"
										ng-required="EventType==2 && IsRes2EvaOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2EvaOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2EvaOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2EvaOpt3Remark" />
								</span></label> <label for="IsRes2EvaOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2EvaOpt4" name="IsRes2EvaOpt4"
										ng-model="IsRes2EvaOpt4" ng-value="4"> <s:message
											code="notifyIsRes2EvaOpt4" /><input type="text"
										id="Res2EvaOpt4Remark" name="Res2EvaOpt4Remark"
										ng-model="Res2EvaOpt4Remark"
										ng-required="EventType==2 && IsRes2EvaOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2EvaOpt4Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2EvaOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2EvaOpt4Remark" />
								</span></label> <label for="IsRes2EvaOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2EvaOpt5" name="IsRes2EvaOpt5"
										ng-model="IsRes2EvaOpt5" ng-value="5"> <s:message
											code="notifyIsRes2EvaOpt5" /><input type="text"
										id="Res2EvaOpt5Remark" name="Res2EvaOpt5Remark"
										ng-model="Res2EvaOpt5Remark"
										ng-required="EventType==2 && IsRes2EvaOpt5"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2EvaOpt5Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res2EvaOpt5Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2EvaOpt5Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==2 && !IsRes2EvaOpt1 && !IsRes2EvaOpt2 && !IsRes2EvaOpt3 && !IsRes2EvaOpt4 && !IsRes2EvaOpt5">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle3"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label for="IsRes2DoOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt1" name="IsRes2DoOpt1"
										ng-required="EventType==2 && !IsRes2DoOpt1 && !IsRes2DoOpt2 && !IsRes2DoOpt3 && !IsRes2DoOpt4 && !IsRes2DoOpt5 && !IsRes2DoOpt6 && !IsRes2DoOpt7"
										ng-model="IsRes2DoOpt1" ng-value="1"> <s:message
											code="notifyIsRes2DoOpt1" /><input type="text"
										id="Res2DoOpt1Remark" name="Res2DoOpt1Remark"
										ng-model="Res2DoOpt1Remark"
										ng-required="EventType==2 && IsRes2DoOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2DoOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2DoOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2DoOpt1Remark" />
								</span></label> <label for="IsRes2DoOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt2" name="IsRes2DoOpt2" ng-model="IsRes2DoOpt2"
										ng-value="2"> <s:message code="notifyIsRes2DoOpt2" /><input
										type="text" id="Res2DoOpt2Remark" name="Res2DoOpt2Remark"
										ng-model="Res2DoOpt2Remark"
										ng-required="EventType==2 && IsRes2DoOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2DoOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2DoOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2DoOpt2Remark" />
								</span></label> <label for="IsRes2DoOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt3" name="IsRes2DoOpt3" ng-model="IsRes2DoOpt3"
										ng-value="3"> <s:message code="notifyIsRes2DoOpt3" /><input
										type="text" id="Res2DoOpt3Remark" name="Res2DoOpt3Remark"
										ng-model="Res2DoOpt3Remark"
										ng-required="EventType==2 && IsRes2DoOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2DoOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res2DoOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2DoOpt3Remark" />
								</span></label> <label for="IsRes2DoOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt4" name="IsRes2DoOpt4" ng-model="IsRes2DoOpt4"
										ng-value="4"> <s:message code="notifyIsRes2DoOpt4" />
								</span> </label> <label for="IsRes2DoOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt5" name="IsRes2DoOpt5" ng-model="IsRes2DoOpt5"
										ng-value="5"> <s:message code="notifyIsRes2DoOpt5" /></span></label>
								<label for="IsRes2DoOpt6" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt6" name="IsRes2DoOpt6" ng-model="IsRes2DoOpt6"
										ng-value="6"> <s:message code="notifyIsRes2DoOpt6" /><input
										type="number" id="Res2DoOpt6Amount" name="Res2DoOpt6Amount"
										ng-model="Res2DoOpt6Amount"
										ng-required="EventType==2 && IsRes2DoOpt6"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2DoOpt6Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes2DoOpt6Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res2DoOpt6Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2DoOpt6Amount" />
								</span></label> <label for="IsRes2DoOpt7" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes2DoOpt7" name="IsRes2DoOpt7" ng-model="IsRes2DoOpt7"
										ng-value="7"> <s:message code="notifyIsRes2DoOpt7" /><input
										type="text" id="Res2DoOpt7Remark" name="Res2DoOpt7Remark"
										ng-model="Res2DoOpt7Remark"
										ng-required="EventType==2 && IsRes2DoOpt7"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes2DoOpt7Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res2DoOpt7Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes2DoOpt7Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==2 && !IsRes2DoOpt1 && !IsRes2DoOpt2 && !IsRes2DoOpt3 && !IsRes2DoOpt4 && !IsRes2DoOpt5 && !IsRes2DoOpt6 && !IsRes2DoOpt7">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==3">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType3" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle1"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label for="IsRes3LogOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3LogOpt1" name="IsRes3LogOpt1"
										ng-required="EventType==3 && !IsRes3LogOpt1 && !IsRes3LogOpt2 && !IsRes3LogOpt3 && !IsRes3LogOpt4"
										ng-model="IsRes3LogOpt1" ng-value="1"> <s:message
											code="notifyIsRes3LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<input
										type="radio" id="Res3LogOpt11" name="Res3LogOpt11"
										ng-model="Res3LogOpt1" ng-value="1"><label
										for="Res3LogOpt11" class="label_for_radio"><s:message
												code="notifyRes3LogOpt11" /></label><input type="radio"
										id="Res3LogOpt12" name="Res3LogOpt12" ng-model="Res3LogOpt1"
										ng-value="2"><label for="Res3LogOpt12"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt12" /></label><input type="radio"
										id="Res3LogOpt13" name="Res3LogOpt13" ng-model="Res3LogOpt1"
										ng-value="3"><label for="Res3LogOpt13"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt13" /></label><input type="radio"
										id="Res3LogOpt14" name="Res3LogOpt14" ng-model="Res3LogOpt1"
										ng-value="4"><label for="Res3LogOpt14"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt14" /></label><input type="text"
										id="Res3LogOpt1Other" name="Res3LogOpt1Other"
										ng-model="Res3LogOpt1Other"
										ng-required="EventType==3 && IsRes3LogOpt1 && Res3LogOpt1==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3LogOpt1Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res3LogOpt1Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3LogOpt1Other" />
								</span> <!-- Res 2 opt 1 End --> </label> <label for="IsRes3LogOpt2"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3LogOpt2" name="IsRes3LogOpt2"
										ng-model="IsRes3LogOpt2" ng-value="2"> <s:message
											code="notifyIsRes3LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<input
										type="radio" id="Res3LogOpt21" name="Res3LogOpt21"
										ng-model="Res3LogOpt2" ng-value="1"><label
										for="Res3LogOpt21" class="label_for_radio"><s:message
												code="notifyRes3LogOpt21" /></label><input type="radio"
										id="Res3LogOpt22" name="Res3LogOpt22" ng-model="Res3LogOpt2"
										ng-value="2"><label for="Res3LogOpt22"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt22" /></label><input type="radio"
										id="Res3LogOpt23" name="Res3LogOpt23" ng-model="Res3LogOpt2"
										ng-value="3"><label for="Res3LogOpt23"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt23" /></label><input type="radio"
										id="Res3LogOpt24" name="Res3LogOpt24" ng-model="Res3LogOpt2"
										ng-value="4"><label for="Res3LogOpt24"
										class="label_for_radio"><s:message
												code="notifyRes3LogOpt24" /></label><input type="text"
										id="Res3LogOpt2Other" name="Res3LogOpt2Other"
										ng-model="Res3LogOpt2Other"
										ng-required="EventType==3 && IsRes3LogOpt2 && Res3LogOpt2==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3LogOpt2Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res3LogOpt2Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3LogOpt2Other" />
								</span> <!-- Res 2 opt 2 End --></label> <label for="IsRes3LogOpt3"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3LogOpt3" name="IsRes3LogOpt3"
										ng-model="IsRes3LogOpt3" ng-value="3"> <s:message
											code="notifyIsRes3LogOpt3" /><input type="number" min="0"
										id="Res3LogOpt3Amount" name="Res3LogOpt3Amount"
										ng-model="Res3LogOpt3Amount"
										ng-required="EventType==3 && IsRes3LogOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3LogOpt3Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes3LogOpt3Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res3LogOpt3Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3LogOpt3Amount" />
								</span></label> <label for="IsRes3LogOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3LogOpt4" name="IsRes3LogOpt4"
										ng-model="IsRes3LogOpt4" ng-value="4"> <s:message
											code="notifyIsRes3LogOpt4" /><input type="text"
										id="Res3LogOpt4Remark" name="Res3LogOpt4Remark"
										ng-model="Res3LogOpt4Remark"
										ng-required="EventType==3 && IsRes3LogOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3LogOpt4Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res3LogOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3LogOpt4Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==3 && !IsRes3LogOpt1 && !IsRes3LogOpt2 && !IsRes3LogOpt3 && !IsRes3LogOpt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle2"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label for="IsRes3EvaOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3EvaOpt1" name="IsRes3EvaOpt1"
										ng-required="EventType==3 && !IsRes3EvaOpt1 && !IsRes3EvaOpt2 && !IsRes3EvaOpt3"
										ng-model="IsRes3EvaOpt1" ng-value="1"> <s:message
											code="notifyIsRes3EvaOpt1" /><input type="number" min="0"
										id="Res3EvaOpt1Amount" name="Res3EvaOpt1Amount"
										ng-model="Res3EvaOpt1Amount"
										ng-required="EventType==3 && IsRes3EvaOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3EvaOpt1Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes3EvaOpt1Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res3EvaOpt1Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyIsRes3EvaOpt1Unit" />
								</span></label><label for="IsRes3EvaOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3EvaOpt2" name="IsRes3EvaOpt2"
										ng-model="IsRes3EvaOpt2" ng-value="2"> <s:message
											code="notifyIsRes3EvaOpt2" /><input type="text"
										id="Res3EvaOpt2Remark" name="Res3EvaOpt2Remark"
										ng-model="Res3EvaOpt2Remark"
										ng-required="EventType==3 && IsRes3EvaOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3EvaOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res3EvaOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3EvaOpt2Remark" />
								</span></label> <label for="IsRes3EvaOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3EvaOpt3" name="IsRes3EvaOpt3"
										ng-model="IsRes3EvaOpt3" ng-value="3"> <s:message
											code="notifyIsRes3EvaOpt3" /><input type="text"
										id="Res3EvaOpt3Remark" name="Res3EvaOpt3Remark"
										ng-model="Res3EvaOpt3Remark"
										ng-required="EventType==3 && IsRes3EvaOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3EvaOpt3Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res3EvaOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3EvaOpt3Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==3 && !IsRes3EvaOpt1 && !IsRes3EvaOpt2 && !IsRes3EvaOpt3">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle3"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label> <label for="IsRes3DoOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3DoOpt1" name="IsRes3DoOpt1"
										ng-required="EventType==3 && !IsRes3DoOpt1 && !IsRes3DoOpt2 && !IsRes3DoOpt3 && !IsRes3DoOpt4"
										ng-model="IsRes3DoOpt1" ng-value="1"> <s:message
											code="notifyIsRes3DoOpt1" /><input type="text"
										id="Res3DoOpt1Remark" name="Res3DoOpt1Remark"
										ng-model="Res3DoOpt1Remark"
										ng-required="EventType==3 && IsRes3DoOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3DoOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res3DoOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3DoOpt1Remark" />
								</span></label><label for="IsRes3DoOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3DoOpt2" name="IsRes3DoOpt2" ng-model="IsRes3DoOpt2"
										ng-value="2"> <s:message code="notifyIsRes3DoOpt2" />
								</span> </label><label for="IsRes3DoOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3DoOpt3" name="IsRes3DoOpt3" ng-model="IsRes3DoOpt3"
										ng-value="3"> <s:message code="notifyIsRes3DoOpt3" /><input
										type="text" id="Res3DoOpt3Remark" name="Res3DoOpt3Remark"
										ng-model="Res3DoOpt3Remark"
										ng-required="EventType==3 && IsRes3DoOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3DoOpt3Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res3DoOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3DoOpt3Remark" />
								</span> </label><label for="IsRes3DoOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes3DoOpt4" name="IsRes3DoOpt4" ng-model="IsRes3DoOpt4"
										ng-value="4"> <s:message code="notifyIsRes3DoOpt4" /><input
										type="text" id="Res3DoOpt4Remark" name="Res3DoOpt4Remark"
										ng-model="Res3DoOpt4Remark"
										ng-required="EventType==3 && IsRes3DoOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes3DoOpt4Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res3DoOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes3DoOpt4Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==3 && !IsRes3DoOpt1 && !IsRes3DoOpt2 && !IsRes3DoOpt3 && !IsRes3DoOpt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==4">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType4" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle1"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label> <label for="IsRes4LogOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4LogOpt1" name="IsRes4LogOpt1"
										ng-required="EventType==4 && !IsRes4LogOpt1"
										ng-model="IsRes4LogOpt1" ng-value="1"> <s:message
											code="notifyIsRes4LogOpt1" /><input type="text"
										id="Res4LogOpt1Remark" name="Res4LogOpt1Remark"
										ng-model="Res4LogOpt1Remark"
										ng-required="EventType==4 && IsRes4LogOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes4LogOpt1Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res4LogOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes4LogOpt1Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==4 && !IsRes4LogOpt1">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle2"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label for="IsRes4EvaOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4EvaOpt1" name="IsRes4EvaOpt1"
										ng-required="EventType==4 && !IsRes4EvaOpt1 && !IsRes4EvaOpt2"
										ng-model="IsRes4EvaOpt1" ng-value="1"> <s:message
											code="notifyIsRes4EvaOpt1" />
								</span> <!-- Res 2 opt 6 Start --> <span><input type="radio"
										id="Res4EvaOpt1Type1" name="Res4EvaOpt1Type1"
										ng-model="Res4EvaOpt1" ng-value="1"><label
										for="Res4EvaOpt1Type1" class="label_for_radio"><s:message
												code="notifyRes4EvaOpt1Type1" /></label><input type="radio"
										id="Res4EvaOpt1Type2" name="Res4EvaOpt1Type2"
										ng-model="Res4EvaOpt1" ng-value="2"><label
										for="Res4EvaOpt1Type2" class="label_for_radio"><s:message
												code="notifyRes4EvaOpt1Type2" /></label><input type="radio"
										id="Res4EvaOpt1Type3" name="Res4EvaOpt1Type3"
										ng-model="Res4EvaOpt1" ng-value="3"><label
										for="Res4EvaOpt1Type3" class="label_for_radio"><s:message
												code="notifyRes4EvaOpt1Type3" /></label><input type="radio"
										id="Res4EvaOpt1Type4" name="Res4EvaOpt1Type4"
										ng-model="Res4EvaOpt1" ng-value="4"><label
										for="Res4EvaOpt1Type4" class="label_for_radio"><s:message
												code="notifyRes4EvaOpt1Type4" /></label><input type="number"
										min="0" max="100" id="Res4EvaOpt1Amount"
										name="Res4EvaOpt1Amount" ng-model="Res4EvaOpt1Amount"
										ng-required="EventType==4 && IsRes4EvaOpt1 && Res4EvaOpt1==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes4EvaOpt1Amount" />"
										autocomplete="off">% </span> <span class="text-danger"
									ng-show="editForm.Res4EvaOpt1Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes4EvaOpt1Amount" />
								</span> <!-- Res 2 opt 6 End --></label><label for="IsRes4EvaOpt2"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4EvaOpt2" name="IsRes4EvaOpt2"
										ng-model="IsRes4EvaOpt2" ng-value="2"> <s:message
											code="notifyIsRes4EvaOpt2" /><input type="text"
										id="Res4EvaOpt2Remark" name="Res4EvaOpt2Remark"
										ng-model="Res4EvaOpt2Remark"
										ng-required="EventType==4 && IsRes4EvaOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes4EvaOpt2Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res4EvaOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes4EvaOpt2Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==4 && !IsRes4EvaOpt1 && !IsRes4EvaOpt2">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle3"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label for="IsRes4DoOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4DoOpt1" name="IsRes4DoOpt1"
										ng-required="EventType==4 && !IsRes4DoOpt1 && !IsRes4DoOpt2 && !IsRes4DoOpt3"
										ng-model="IsRes4DoOpt1" ng-value="1"> <s:message
											code="notifyIsRes4DoOpt1" />
								</span> </label><label for="IsRes4DoOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4DoOpt2" name="IsRes4DoOpt2" ng-model="IsRes4DoOpt2"
										ng-value="2"> <s:message code="notifyIsRes4DoOpt2" />
								</span> </label><label for="IsRes4DoOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes4DoOpt3" name="IsRes4DoOpt3" ng-model="IsRes4DoOpt3"
										ng-value="3"> <s:message code="notifyIsRes4DoOpt3" /><input
										type="text" id="Res4DoOpt3Remark" name="Res4DoOpt3Remark"
										ng-model="Res4DoOpt3Remark"
										ng-required="EventType==4 && IsRes4DoOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes4DoOpt3Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res4DoOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes4DoOpt3Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==4 && !IsRes4DoOpt1 && !IsRes4DoOpt2 && !IsRes4DoOpt3">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==5">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep4TitleDetail" />(<s:message
										code="notifyEventType5" />)</b>
							</h3>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle1"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle1" /></label><label for="IsRes5LogOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5LogOpt1" name="IsRes5LogOpt1"
										ng-required="EventType==5 && !IsRes5LogOpt1 && !IsRes5LogOpt2 && !IsRes5LogOpt3 && !IsRes5LogOpt4"
										ng-model="IsRes5LogOpt1" ng-value="1"> <s:message
											code="notifyIsRes5LogOpt1" />
								</span> <!-- Res 2 opt 1 Start --> <span>(&nbsp;<input
										type="radio" id="Res5LogOpt11" name="Res5LogOpt11"
										ng-model="Res5LogOpt1" ng-value="1"><label
										for="Res5LogOpt11" class="label_for_radio"><s:message
												code="notifyRes5LogOpt11" /></label><input type="radio"
										id="Res5LogOpt12" name="Res5LogOpt12" ng-model="Res5LogOpt1"
										ng-value="2"><label for="Res5LogOpt12"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt12" /></label><input type="radio"
										id="Res5LogOpt13" name="Res5LogOpt13" ng-model="Res5LogOpt1"
										ng-value="3"><label for="Res5LogOpt13"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt13" /></label><input type="radio"
										id="Res5LogOpt14" name="Res5LogOpt14" ng-model="Res5LogOpt1"
										ng-value="4"><label for="Res5LogOpt14"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt14" /></label><input type="text"
										id="Res5LogOpt1Other" name="Res5LogOpt1Other"
										ng-model="Res5LogOpt1Other"
										ng-required="EventType==5 && IsRes5LogOpt1 && Res5LogOpt1==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5LogOpt1Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res5LogOpt1Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5LogOpt1Other" />
								</span> <!-- Res 2 opt 1 End --> </label> <label for="IsRes5LogOpt2"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5LogOpt2" name="IsRes5LogOpt2"
										ng-model="IsRes5LogOpt2" ng-value="2"> <s:message
											code="notifyIsRes5LogOpt2" />
								</span> <!-- Res 2 opt 2 Start --> <span>(&nbsp;<input
										type="radio" id="Res5LogOpt21" name="Res5LogOpt21"
										ng-model="Res5LogOpt2" ng-value="1"><label
										for="Res5LogOpt21" class="label_for_radio"><s:message
												code="notifyRes5LogOpt21" /></label><input type="radio"
										id="Res5LogOpt22" name="Res5LogOpt22" ng-model="Res5LogOpt2"
										ng-value="2"><label for="Res5LogOpt22"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt22" /></label><input type="radio"
										id="Res5LogOpt23" name="Res5LogOpt23" ng-model="Res5LogOpt2"
										ng-value="3"><label for="Res5LogOpt23"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt23" /></label><input type="radio"
										id="Res5LogOpt24" name="Res5LogOpt24" ng-model="Res5LogOpt2"
										ng-value="4"><label for="Res5LogOpt24"
										class="label_for_radio"><s:message
												code="notifyRes5LogOpt24" /></label><input type="text"
										id="Res5LogOpt2Other" name="Res5LogOpt2Other"
										ng-model="Res5LogOpt2Other"
										ng-required="EventType==5 && IsRes5LogOpt2 && Res5LogOpt2==4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5LogOpt2Other" />"
										autocomplete="off">)
								</span> <span class="text-danger"
									ng-show="editForm.Res5LogOpt2Other.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5LogOpt2Other" />
								</span> <!-- Res 2 opt 2 End --></label> <label for="IsRes5LogOpt3"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5LogOpt3" name="IsRes5LogOpt3"
										ng-model="IsRes5LogOpt3" ng-value="3"> <s:message
											code="notifyIsRes5LogOpt3" /><input type="number" min="0"
										id="Res5LogOpt3Amount" name="Res5LogOpt3Amount"
										ng-model="Res5LogOpt3Amount"
										ng-required="EventType==5 && IsRes5LogOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5LogOpt3Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes5LogOpt3Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res5LogOpt3Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5LogOpt3Amount" />
								</span></label> <label for="IsRes5LogOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5LogOpt4" name="IsRes5LogOpt4"
										ng-model="IsRes5LogOpt4" ng-value="4"> <s:message
											code="notifyIsRes5LogOpt4" /><input type="text"
										id="Res5LogOpt4Remark" name="Res5LogOpt4Remark"
										ng-model="Res5LogOpt4Remark"
										ng-required="EventType==5 && IsRes5LogOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5LogOpt4Remark" />"
										autocomplete="off">
								</span><span class="text-danger"
									ng-show="editForm.Res5LogOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5LogOpt4Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==5 && !IsRes5LogOpt1 && !IsRes5LogOpt2 && !IsRes5LogOpt3 && !IsRes5LogOpt4">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle2"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle2" /></label><label for="IsRes5EvaOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5EvaOpt1" name="IsRes5EvaOpt1"
										ng-required="EventType==5 && !IsRes5EvaOpt1 && !IsRes5EvaOpt2 && !IsRes5EvaOpt3 && !IsRes5EvaOpt4 && !IsRes5EvaOpt5"
										ng-model="IsRes5EvaOpt1" ng-value="1"> <s:message
											code="notifyIsRes5EvaOpt1" /><input type="text"
										id="Res5EvaOpt1Remark" name="Res5EvaOpt1Remark"
										ng-required="EventType==5 && IsRes5EvaOpt1"
										ng-model="Res5EvaOpt1Remark"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5EvaOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5EvaOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5EvaOpt1Remark" />
								</span></label> <label for="IsRes5EvaOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5EvaOpt2" name="IsRes5EvaOpt2"
										ng-model="IsRes5EvaOpt2" ng-value="2"> <s:message
											code="notifyIsRes5EvaOpt2" /><input type="text"
										id="Res5EvaOpt2Remark" name="Res5EvaOpt2Remark"
										ng-model="Res5EvaOpt2Remark"
										ng-required="EventType==5 && IsRes5EvaOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5EvaOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5EvaOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5EvaOpt2Remark" />
								</span></label> <label for="IsRes5EvaOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5EvaOpt3" name="IsRes5EvaOpt3"
										ng-model="IsRes5EvaOpt3" ng-value="3"> <s:message
											code="notifyIsRes5EvaOpt3" /><input type="text"
										id="Res5EvaOpt3Remark" name="Res5EvaOpt3Remark"
										ng-model="Res5EvaOpt3Remark"
										ng-required="EventType==5 && IsRes5EvaOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5EvaOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5EvaOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5EvaOpt3Remark" />
								</span></label> <label for="IsRes5EvaOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5EvaOpt4" name="IsRes5EvaOpt4"
										ng-model="IsRes5EvaOpt4" ng-value="4"> <s:message
											code="notifyIsRes5EvaOpt4" /><input type="text"
										id="Res5EvaOpt4Remark" name="Res5EvaOpt4Remark"
										ng-model="Res5EvaOpt4Remark"
										ng-required="EventType==5 && IsRes5EvaOpt4"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5EvaOpt4Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5EvaOpt4Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5EvaOpt4Remark" />
								</span></label> <label for="IsRes5EvaOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5EvaOpt5" name="IsRes5EvaOpt5"
										ng-model="IsRes5EvaOpt5" ng-value="5"> <s:message
											code="notifyIsRes5EvaOpt5" /><input type="text"
										id="Res5EvaOpt5Remark" name="Res5EvaOpt5Remark"
										ng-model="Res5EvaOpt5Remark"
										ng-required="EventType==5 && IsRes5EvaOpt5"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5EvaOpt5Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res5EvaOpt5Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5EvaOpt5Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==5 && !IsRes5EvaOpt1 && !IsRes5EvaOpt2 && !IsRes5EvaOpt3 && !IsRes5EvaOpt4 && !IsRes5EvaOpt5">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsResTitle3"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsResTitle3" /></label><label for="IsRes5DoOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt1" name="IsRes5DoOpt1"
										ng-required="EventType==5 && !IsRes5DoOpt1 && !IsRes5DoOpt2 && !IsRes5DoOpt3 && !IsRes5DoOpt4 && !IsRes5DoOpt5 && !IsRes5DoOpt6 && !IsRes5DoOpt7"
										ng-model="IsRes5DoOpt1" ng-value="1"> <s:message
											code="notifyIsRes5DoOpt1" /><input type="text"
										id="Res5DoOpt1Remark" name="Res5DoOpt1Remark"
										ng-model="Res5DoOpt1Remark"
										ng-required="EventType==5 && IsRes5DoOpt1"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5DoOpt1Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5DoOpt1Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt1Remark" />
								</span></label> <label for="IsRes5DoOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt2" name="IsRes5DoOpt2" ng-model="IsRes5DoOpt2"
										ng-value="2"> <s:message code="notifyIsRes5DoOpt2" /><input
										type="text" id="Res5DoOpt2Remark" name="Res5DoOpt2Remark"
										ng-model="Res5DoOpt2Remark"
										ng-required="EventType==5 && IsRes5DoOpt2"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5DoOpt2Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5DoOpt2Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt2Remark" />
								</span></label> <label for="IsRes5DoOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt3" name="IsRes5DoOpt3" ng-model="IsRes5DoOpt3"
										ng-value="3"> <s:message code="notifyIsRes5DoOpt3" /><input
										type="text" id="Res5DoOpt3Remark" name="Res5DoOpt3Remark"
										ng-model="Res5DoOpt3Remark"
										ng-required="EventType==5 && IsRes5DoOpt3"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5DoOpt3Remark" />"
										autocomplete="off">
								</span> <span class="text-danger"
									ng-show="editForm.Res5DoOpt3Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt3Remark" />
								</span></label> <label for="IsRes5DoOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt4" name="IsRes5DoOpt4" ng-model="IsRes5DoOpt4"
										ng-value="4"> <s:message code="notifyIsRes5DoOpt4" />
								</span> </label> <label for="IsRes5DoOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt5" name="IsRes5DoOpt5" ng-model="IsRes5DoOpt5"
										ng-value="5"> <s:message code="notifyIsRes5DoOpt5" />
										<div class="input-group">
											<input type="text" id="Res5DoOpt5Date" name="Res5DoOpt5Date"
												ng-model="Res5DoOpt5Date"
												ng-required="EventType==5 && IsRes5DoOpt5"
												class="form-control"
												placeholder="<s:message code="notifyRes5DoOpt5Date" />"
												autocomplete="off"><span class="input-group-addon">
												<i class="fas fa-calendar-alt"></i>
											</span>
										</div>
								</span> <span class="text-danger"
									ng-show="editForm.Res5DoOpt5Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt5Remark" />
								</span></label> <label for="IsRes5DoOpt6" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt6" name="IsRes5DoOpt6" ng-model="IsRes5DoOpt6"
										ng-value="6"> <s:message code="notifyIsRes5DoOpt6" /><input
										type="number" id="Res5DoOpt6Amount" name="Res5DoOpt6Amount"
										ng-model="Res5DoOpt6Amount"
										ng-required="EventType==5 && IsRes5DoOpt6"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5DoOpt6Amount" />"
										autocomplete="off"> <s:message
											code="notifyIsRes5DoOpt6Unit" />
								</span> <span class="text-danger"
									ng-show="editForm.Res5DoOpt6Amount.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt6Amount" />
								</span></label> <label for="IsRes5DoOpt7" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsRes5DoOpt7" name="IsRes5DoOpt7" ng-model="IsRes5DoOpt7"
										ng-value="7"> <s:message code="notifyIsRes5DoOpt7" /><input
										type="text" id="Res5DoOpt7Remark" name="Res5DoOpt7Remark"
										ng-model="Res5DoOpt7Remark"
										ng-required="EventType==5 && IsRes5DoOpt7"
										class="form-control form_input_half"
										placeholder="<s:message
													code="notifyRes5DoOpt7Remark" />"
										autocomplete="off"></span><span class="text-danger"
									ng-show="editForm.Res5DoOpt7Remark.$error.required"> <s:message
											code="pleaseEnter" /> <s:message
											code="notifyRes5DoOpt7Remark" />
								</span>
									<h5 class="text-danger"
										ng-show="EventType==5 && !IsRes5DoOpt1 && !IsRes5DoOpt2 && !IsRes5DoOpt3 && !IsRes5DoOpt4 && !IsRes5DoOpt5 && !IsRes5DoOpt6 && !IsRes5DoOpt7">
										<s:message code="pleaseSelectMoreThenOne" />
									</h5></label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="tabNotifyStep5" class="tab-pane active"
				ng-show="Status != 4 && Status != 9">
				<div class="row">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep5TitleDetail" /></b>
							</h3>
						</div>
						<h4 class="form_heading form_heading_fix form_heading_yellow">
							<b><s:message code="notifyContent4" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label for="IsNeedSupport"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsNeedSupport" /></label>
								<div class="form_input form_input_search">
									<toggle ng-model="IsNeedSupport" ng-init="IsNeedSupport=false"
										on='<i class="far fa-fw fa-check-circle"></i><s:message code="notifyIsNeedSupportTrue" />'
										off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="notifyIsNeedSupportFalse" />'
										onstyle="btn-success" offstyle="btn-danger"></toggle>
								</div>
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">說明</label><span
									class="form-text form_text form_input_search">{{SupportNotes}}</span>
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray">查閱資安事件處理服務廠商資訊</label>
													&nbsp; <a class="btn btn-warning" href="#my-modal"								
										data-toggle="modal">查閱資安事件處理服務廠商資訊</a>									
							</div>
						</div>
						<div ng-show="IsNeedSupport">
							<div class="form_group">
								<label for="NeedSupportRemark"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyNeedSupportRemark" /></label>
								<div class="form_input form_input_search">
									<textarea id="NeedSupportRemark" name="NeedSupportRemark"
										ng-model="NeedSupportRemark" class="form-control"
										placeholder="<s:message code="notifyNeedSupportRemark" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
						</div>					
					</div>
				</div>
			</div>
			<div id="tabNotifyStep6" class="tab-pane active" ng-show="Status==4 || Status==9">
				<div class="row" ng-show="EventType==1">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep6TitleDetail" />(<s:message
										code="notifyEventType1" />)</b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="Finish1Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span> <input
									type="radio" id="Finish1Reason1" name="Finish1Reason"
									ng-model="Finish1Reason" ng-value="1"><label
									for="Finish1Reason1" class="label_for_radio"><s:message
											code="notifyFinish1Reason1" /> </label>
								</span> <span> <input type="radio" id="Finish1Reason2"
									name="Finish1Reason" ng-model="Finish1Reason" ng-value="2"><label
									for="Finish1Reason2" class="label_for_radio"><s:message
											code="notifyFinish1Reason2" /> </label>
								</span><span> <input type="radio" id="Finish1Reason3"
									name="Finish1Reason" ng-model="Finish1Reason" ng-value="3"><label
									for="Finish1Reason3" class="label_for_radio"><s:message
											code="notifyFinish1Reason3" /> </label>
								</span><span> <input type="radio" id="Finish1Reason4"
									name="Finish1Reason" ng-model="Finish1Reason" ng-value="4"><label
									for="Finish1Reason4" class="label_for_radio"><s:message
											code="notifyFinish1Reason4" /> </label>
								</span><span> <input type="radio" id="Finish1Reason5"
									name="Finish1Reason" ng-model="Finish1Reason" ng-value="5"><label
									for="Finish1Reason5" class="label_for_radio"><s:message
											code="notifyFinish1Reason5" /> </label>
								</span><span> <input type="radio" id="Finish1Reason6"
									name="Finish1Reason" ng-model="Finish1Reason" ng-value="6"><label
									for="Finish1Reason6" class="label_for_radio"><s:message
											code="notifyFinish1Reason6" /> </label>
								</span>
								<div class="form_input" ng-show="Finish1Reason==6">
									<textarea id="Finish1ReasonOther" name="Finish1ReasonOther"
										ng-model="Finish1ReasonOther" class="form-control"
										ng-required="Finish1Reason==6 && EventType==1"
										placeholder="<s:message code="notifyFinish1ReasonOther" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<h5 class="text-danger"
								ng-show="editForm.Finish1ReasonOther.$error.required && editForm.Finish1ReasonOther.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifyFinish1ReasonOther" />
							</h5>
						</div>
						<div>
							<div class="form_group">
								<label for="FinishDo"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDo" /></label> <label
									class="form_label form_label_search_fix form_label_gray"></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsFinishDoSys"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsFinishDoSys" /></label><label for="IsFinish1DoSysOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt1" name="IsFinish1DoSysOpt1"
										ng-required="Status==4 && EventType==1 && !IsFinish1DoSysOpt1 &&!IsFinish1DoSysOpt2 &&!IsFinish1DoSysOpt3 &&!IsFinish1DoSysOpt4 &&!IsFinish1DoSysOpt5 &&!IsFinish1DoSysOpt6 &&!IsFinish1DoSysOpt7 &&!IsFinish1DoSysOpt8 &&!IsFinish1DoSysOpt9 &&!IsFinish1DoSysOpt10 &&!IsFinishDoSysOptRemark"
										ng-model="IsFinish1DoSysOpt1" ng-value="1"> <s:message
											code="notifyIsFinish1DoSysOpt1" />
								</span></label><label for="IsFinish1DoSysOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt2" name="IsFinish1DoSysOpt2"
										ng-model="IsFinish1DoSysOpt2" ng-value="2"> <s:message
											code="notifyIsFinish1DoSysOpt2" />
								</span></label><label for="IsFinish1DoSysOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt3" name="IsFinish1DoSysOpt3"
										ng-model="IsFinish1DoSysOpt3" ng-value="3"> <s:message
											code="notifyIsFinish1DoSysOpt3" />
								</span><span class="text-danger"
									ng-show="editForm.Finish1DoSysOpt3Remark.$error.required && editForm.Finish1DoSysOpt3Remark.$dirty">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyFinish1DoSysOpt3Remark" />
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinish1DoSysOpt3">
										<textarea id="Finish1DoSysOpt3Remark"
											name="Finish1DoSysOpt3Remark"
											ng-required="EventType==1 && IsFinish1DoSysOpt3"
											ng-model="Finish1DoSysOpt3Remark" class="form-control"
											placeholder="<s:message code="notifyFinish1DoSysOpt3Remark" />"
											rows="5" autocomplete="off"></textarea>
									</div> </label><label for="IsFinish1DoSysOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt4" name="IsFinish1DoSysOpt4"
										ng-model="IsFinish1DoSysOpt4" ng-value="4"> <s:message
											code="notifyIsFinish1DoSysOpt4" />
								</span></label><label for="IsFinish1DoSysOpt5" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt5" name="IsFinish1DoSysOpt5"
										ng-model="IsFinish1DoSysOpt5" ng-value="5"> <s:message
											code="notifyIsFinish1DoSysOpt5" />
								</span></label><label for="IsFinish1DoSysOpt6" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt6" name="IsFinish1DoSysOpt6"
										ng-model="IsFinish1DoSysOpt6" ng-value="6"> <s:message
											code="notifyIsFinish1DoSysOpt6" />
								</span><span class="text-danger"
									ng-show="editForm.Finish1DoSysOpt6Remark.$error.required && editForm.Finish1DoSysOpt6Remark.$dirty">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyFinish1DoSysOpt6Remark" />
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinish1DoSysOpt6">
										<textarea id="Finish1DoSysOpt6Remark"
											name="Finish1DoSysOpt6Remark"
											ng-required="EventType==1 && IsFinish1DoSysOpt6"
											ng-model="Finish1DoSysOpt6Remark" class="form-control"
											placeholder="<s:message code="notifyFinish1DoSysOpt6Remark" />"
											rows="5" autocomplete="off"></textarea>
									</div> </label><label for="IsFinish1DoSysOpt7" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt7" name="IsFinish1DoSysOpt7"
										ng-model="IsFinish1DoSysOpt7" ng-value="7"> <s:message
											code="notifyIsFinish1DoSysOpt7" />
								</span><span class="text-danger"
									ng-show="editForm.Finish1DoSysOpt7Remark.$error.required && editForm.Finish1DoSysOpt7Remark.$dirty">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyFinish1DoSysOpt7Remark" />
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinish1DoSysOpt7">
										<textarea id="Finish1DoSysOpt7Remark"
											name="Finish1DoSysOpt7Remark"
											ng-required="EventType==1 && IsFinish1DoSysOpt7"
											ng-model="Finish1DoSysOpt7Remark" class="form-control"
											placeholder="<s:message code="notifyFinish1DoSysOpt7Remark" />"
											rows="5" autocomplete="off"></textarea>
									</div> </label><label for="IsFinish1DoSysOpt8" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt8" name="IsFinish1DoSysOpt8"
										ng-model="IsFinish1DoSysOpt8" ng-value="8"> <s:message
											code="notifyIsFinish1DoSysOpt8" />
								</span></label><label for="IsFinish1DoSysOpt9" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt9" name="IsFinish1DoSysOpt9"
										ng-model="IsFinish1DoSysOpt9" ng-value="9"> <s:message
											code="notifyIsFinish1DoSysOpt9" />
								</span></label><label for="IsFinish1DoSysOpt10" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoSysOpt10" name="IsFinish1DoSysOpt10"
										ng-model="IsFinish1DoSysOpt10" ng-value="10"> <s:message
											code="notifyIsFinish1DoSysOpt10" />
								</span></label>
								
								<label for="IsFinishDoSysOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoSysOptRemark" name="IsFinishDoSysOptRemark"
										ng-model="IsFinishDoSysOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoSysOptRemark.$error.required && editForm.FinishDoSysOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoSysOptRemark">
										<textarea id="FinishDoSysOptRemark"
											name="FinishDoSysOptRemark"
											ng-required="EventType==1 && IsFinishDoSysOptRemark"
											ng-model="FinishDoSysOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
								
								<label class="input_for_radio_group"> <span
									class="text-danger"
									ng-show="!IsFinish1DoSysOpt1 &&!IsFinish1DoSysOpt2 &&!IsFinish1DoSysOpt3 &&!IsFinish1DoSysOpt4 &&!IsFinish1DoSysOpt5 &&!IsFinish1DoSysOpt6 &&!IsFinish1DoSysOpt7 &&!IsFinish1DoSysOpt8 &&!IsFinish1DoSysOpt9 &&!IsFinish1DoSysOpt10 && !IsFinishDoSysOptRemark">
										<s:message code="pleaseSelectMoreThenOne" />
								</span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="IsFinishDoEdu"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsFinishDoEdu" /></label><label for="IsFinish1DoEduOpt1"
									class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoEduOpt1" name="IsFinish1DoEduOpt1"
										ng-required="Status==4 && EventType==1 && !IsFinish1DoEduOpt1 && !IsFinish1DoEduOpt2 && !IsFinish1DoEduOpt3 && !IsFinish1DoEduOpt4 && !IsFinishDoEduOptRemark"
										ng-model="IsFinish1DoEduOpt1" ng-value="1"> <s:message
											code="notifyIsFinish1DoEduOpt1" />
								</span></label><label for="IsFinish1DoEduOpt2" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoEduOpt2" name="IsFinish1DoEduOpt2"
										ng-model="IsFinish1DoEduOpt2" ng-value="2"> <s:message
											code="notifyIsFinish1DoEduOpt2" />
								</span> </label><label for="IsFinish1DoEduOpt3" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoEduOpt3" name="IsFinish1DoEduOpt3"
										ng-model="IsFinish1DoEduOpt3" ng-value="3"> <s:message
											code="notifyIsFinish1DoEduOpt3" />
								</span> </label><label for="IsFinish1DoEduOpt4" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinish1DoEduOpt4" name="IsFinish1DoEduOpt4"
										ng-model="IsFinish1DoEduOpt4" ng-value="4"> <s:message
											code="notifyIsFinish1DoEduOpt4" />
								</span> </label> 
								
								<label for="IsFinishDoEduOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoEduOptRemark" name="IsFinishDoEduOptRemark"
										ng-model="IsFinishDoEduOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoEduOptRemark.$error.required && editForm.FinishDoEduOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoEduOptRemark">
										<textarea id="FinishDoEduOptRemark"
											name="FinishDoEduOptRemark"
											ng-required="EventType==1 && IsFinishDoEduOptRemark"
											ng-model="FinishDoEduOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
								
								<label class="input_for_radio_group"> <span
									class="text-danger"
									ng-show="!IsFinish1DoEduOpt1 && !IsFinish1DoEduOpt2 && !IsFinish1DoEduOpt3 && !IsFinish1DoEduOpt4 && !IsFinishDoEduOptRemark">
										<s:message code="pleaseSelectMoreThenOne" />
								</span></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==2">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep6TitleDetail" />(<s:message
										code="notifyEventType2" />)</b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="Finish2Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span> <input
									type="radio" id="Finish2Reason1" name="Finish2Reason"
									ng-model="Finish2Reason" ng-value="1"><label
									for="Finish2Reason1" class="label_for_radio"><s:message
											code="notifyFinish2Reason1" /> </label>
								</span> <span> <input type="radio" id="Finish2Reason2"
									name="Finish2Reason" ng-model="Finish2Reason" ng-value="2"><label
									for="Finish2Reason2" class="label_for_radio"><s:message
											code="notifyFinish2Reason2" /> </label>
								</span><span> <input type="radio" id="Finish2Reason3"
									name="Finish2Reason" ng-model="Finish2Reason" ng-value="3"><label
									for="Finish2Reason3" class="label_for_radio"><s:message
											code="notifyFinish2Reason3" /> </label>
								</span><span> <input type="radio" id="Finish2Reason4"
									name="Finish2Reason" ng-model="Finish2Reason" ng-value="4"><label
									for="Finish2Reason4" class="label_for_radio"><s:message
											code="notifyFinish2Reason4" /> </label>
								</span><span> <input type="radio" id="Finish2Reason5"
									name="Finish2Reason" ng-model="Finish2Reason" ng-value="5"><label
									for="Finish2Reason5" class="label_for_radio"><s:message
											code="notifyFinish2Reason5" /> </label>
								</span><span> <input type="radio" id="Finish2Reason6"
									name="Finish2Reason" ng-model="Finish2Reason" ng-value="6"><label
									for="Finish2Reason6" class="label_for_radio"><s:message
											code="notifyFinish2Reason6" /> </label>
								</span>
								<div class="form_input" ng-show="Finish2Reason==6">
									<textarea id="Finish2ReasonOther" name="Finish2ReasonOther"
										ng-model="Finish2ReasonOther" class="form-control"
										ng-required="Finish2Reason==6 && EventType==2"
										placeholder="<s:message code="notifyFinish2ReasonOther" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<h5 class="text-danger"
								ng-show="editForm.Finish2ReasonOther.$error.required && editForm.Finish2ReasonOther.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifyFinish2ReasonOther" />
							</h5>
						</div>
						<div>
							<div class="form_group">
								<label for="Finish2Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish2ReasonRemark" /></label>
								<div class="form_input form_input_search">
									<textarea id="Finish2ReasonRemark" name="Finish2ReasonRemark"
										ng-model="Finish2ReasonRemark" class="form-control"
										placeholder="<s:message code="notifyFinish2ReasonRemark" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="FinishDo"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoSys"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label for="IsFinish2DoSysOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoSysOpt1" name="IsFinish2DoSysOpt1"
											ng-required="Status==4 && EventType==2 && !IsFinish2DoSysOpt1 &&!IsFinish2DoSysOpt2 &&!IsFinish2DoSysOpt3 &&!IsFinish2DoSysOpt4 &&!IsFinish2DoSysOpt5 &&!IsFinishDoSysOptRemark"
											ng-model="IsFinish2DoSysOpt1" ng-value="1"> <s:message
												code="notifyIsFinish2DoSysOpt1" />
									</span><span class="text-danger"
										ng-show="editForm.Finish2DoSysOpt1Remark.$error.required && editForm.Finish2DoSysOpt1Remark.$dirty">
											<s:message code="pleaseEnter" /> <s:message
												code="notifyFinish2DoSysOpt1Remark" />
									</span>
										<div class="form_input form_input_fluid"
											ng-show="IsFinish2DoSysOpt1">
											<textarea id="Finish2DoSysOpt1Remark"
												name="Finish2DoSysOpt1Remark"
												ng-required="EventType==2 && IsFinish2DoSysOpt1"
												ng-model="Finish2DoSysOpt1Remark" class="form-control"
												placeholder="<s:message code="notifyFinish2DoSysOpt1Remark" />"
												rows="5" autocomplete="off"></textarea>
										</div> </label><label for="IsFinish2DoSysOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoSysOpt2" name="IsFinish2DoSysOpt2"
											ng-model="IsFinish2DoSysOpt2" ng-value="2"> <s:message
												code="notifyIsFinish2DoSysOpt2" />
									</span></label><label for="IsFinish2DoSysOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoSysOpt3" name="IsFinish2DoSysOpt3"
											ng-model="IsFinish2DoSysOpt3" ng-value="3"> <s:message
												code="notifyIsFinish2DoSysOpt3" />
									</span></label><label for="IsFinish2DoSysOpt4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoSysOpt4" name="IsFinish2DoSysOpt4"
											ng-model="IsFinish2DoSysOpt4" ng-value="4"> <s:message
												code="notifyIsFinish2DoSysOpt4" />
									</span></label><label for="IsFinish2DoSysOpt5" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoSysOpt5" name="IsFinish2DoSysOpt5"
											ng-model="IsFinish2DoSysOpt5" ng-value="5"> <s:message
												code="notifyIsFinish2DoSysOpt5" />
									</span></label>
									
									<label for="IsFinishDoSysOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoSysOptRemark" name="IsFinishDoSysOptRemark"
										ng-model="IsFinishDoSysOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoSysOptRemark.$error.required && editForm.FinishDoSysOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoSysOptRemark">
										<textarea id="FinishDoSysOptRemark"
											name="FinishDoSysOptRemark"
											ng-required="EventType==2 && IsFinishDoSysOptRemark"
											ng-model="FinishDoSysOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									 <label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish2DoSysOpt1 &&!IsFinish2DoSysOpt2 &&!IsFinish2DoSysOpt3 &&!IsFinish2DoSysOpt4 &&!IsFinish2DoSysOpt5 && !IsFinishDoSysOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoEdu"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label for="IsFinish2DoEduOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoEduOpt1" name="IsFinish2DoEduOpt1"
											ng-required="Status==4 && EventType==2 && !IsFinish2DoEduOpt1 && !IsFinish2DoEduOpt2 && !IsFinish2DoEduOpt3 && !IsFinish2DoEduOpt4 && !IsFinishDoEduOptRemark"
											ng-model="IsFinish2DoEduOpt1" ng-value="1"> <s:message
												code="notifyIsFinish2DoEduOpt1" />
									</span></label><label for="IsFinish2DoEduOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoEduOpt2" name="IsFinish2DoEduOpt2"
											ng-model="IsFinish2DoEduOpt2" ng-value="2"> <s:message
												code="notifyIsFinish2DoEduOpt2" />
									</span> </label><label for="IsFinish2DoEduOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoEduOpt3" name="IsFinish2DoEduOpt3"
											ng-model="IsFinish2DoEduOpt3" ng-value="3"> <s:message
												code="notifyIsFinish2DoEduOpt3" />
									</span> </label><label for="IsFinish2DoEduOpt4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish2DoEduOpt4" name="IsFinish2DoEduOpt4"
											ng-model="IsFinish2DoEduOpt4" ng-value="4"> <s:message
												code="notifyIsFinish2DoEduOpt4" />
									</span> </label> 
									
									<label for="IsFinishDoEduOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoEduOptRemark" name="IsFinishDoEduOptRemark"
										ng-model="IsFinishDoEduOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoEduOptRemark.$error.required && editForm.FinishDoEduOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoEduOptRemark">
										<textarea id="FinishDoEduOptRemark"
											name="FinishDoEduOptRemark"
											ng-required="EventType==2 && IsFinishDoEduOptRemark"
											ng-model="FinishDoEduOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									<label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish2DoEduOpt1 && !IsFinish2DoEduOpt2 && !IsFinish2DoEduOpt3 && !IsFinish2DoEduOpt4 && !IsFinishDoEduOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==3">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep6TitleDetail" />(<s:message
										code="notifyEventType3" />)</b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<div>
							<div>
								<div class="form_group">
									<label for="FinishDo"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoSys"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label> <label
										for="IsFinish3DoSysOpt1" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoSysOpt1" name="IsFinish3DoSysOpt1"
											ng-required="Status==4 && EventType==3 && !IsFinish3DoSysOpt1 &&!IsFinish3DoSysOpt2 &&!IsFinish3DoSysOpt3 &&!IsFinish3DoSysOpt4 &&!IsFinishDoSysOptRemark"
											ng-model="IsFinish3DoSysOpt1" ng-value="1"> <s:message
												code="notifyIsFinish3DoSysOpt1" />
									</span></label> <label for="IsFinish3DoSysOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoSysOpt2" name="IsFinish3DoSysOpt2"
											ng-model="IsFinish3DoSysOpt2" ng-value="2"> <s:message
												code="notifyIsFinish3DoSysOpt2" />
									</span></label> <label for="IsFinish3DoSysOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoSysOpt3" name="IsFinish3DoSysOpt3"
											ng-model="IsFinish3DoSysOpt3" ng-value="3"> <s:message
												code="notifyIsFinish3DoSysOpt3" />
									</span><span class="text-danger"
										ng-show="editForm.Finish3DoSysOpt3Remark.$error.required && editForm.Finish3DoSysOpt3Remark.$dirty">
											<s:message code="pleaseEnter" /> <s:message
												code="notifyFinish3DoSysOpt3Remark" />
									</span>
										<div class="form_input form_input_fluid"
											ng-show="IsFinish3DoSysOpt3">
											<textarea id="Finish3DoSysOpt3Remark"
												name="Finish3DoSysOpt3Remark"
												ng-required="EventType==3 && IsFinish3DoSysOpt3"
												ng-model="Finish3DoSysOpt3Remark" class="form-control"
												placeholder="<s:message code="notifyFinish3DoSysOpt3Remark" />"
												rows="5" autocomplete="off"></textarea>
										</div> </label> <label for="IsFinish3DoSysOpt4"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoSysOpt4" name="IsFinish3DoSysOpt4"
											ng-model="IsFinish3DoSysOpt4" ng-value="4"> <s:message
												code="notifyIsFinish3DoSysOpt4" />
									</span><span class="text-danger"
										ng-show="editForm.Finish3DoSysOpt4Remark.$error.required && editForm.Finish3DoSysOpt4Remark.$dirty">
											<s:message code="pleaseEnter" /> <s:message
												code="notifyFinish3DoSysOpt4Remark" />
									</span>
										<div class="form_input form_input_fluid"
											ng-show="IsFinish3DoSysOpt4">
											<textarea id="Finish3DoSysOpt1Remark"
												name="Finish3DoSysOpt4Remark"
												ng-required="EventType==3 && IsFinish3DoSysOpt4"
												ng-model="Finish3DoSysOpt4Remark" class="form-control"
												placeholder="<s:message code="notifyFinish3DoSysOpt4Remark" />"
												rows="5" autocomplete="off"></textarea>
										</div> </label> 
										
										<label for="IsFinishDoSysOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoSysOptRemark" name="IsFinishDoSysOptRemark"
										ng-model="IsFinishDoSysOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoSysOptRemark.$error.required && editForm.FinishDoSysOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoSysOptRemark">
										<textarea id="FinishDoSysOptRemark"
											name="FinishDoSysOptRemark"
											ng-required="EventType==3 && IsFinishDoSysOptRemark"
											ng-model="FinishDoSysOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
										
										<label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish3DoSysOpt1 &&!IsFinish3DoSysOpt2 &&!IsFinish3DoSysOpt3 &&!IsFinish3DoSysOpt4 && !IsFinishDoSysOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoEdu"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label for="IsFinish3DoEduOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoEduOpt1" name="IsFinish3DoEduOpt1"
											ng-required="Status==4 && EventType==3 && !IsFinish3DoEduOpt1 && !IsFinish3DoEduOpt2 && !IsFinishDoEduOptRemark"
											ng-model="IsFinish3DoEduOpt1" ng-value="1"> <s:message
												code="notifyIsFinish3DoEduOpt1" />
									</span></label><label for="IsFinish3DoEduOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish3DoEduOpt2" name="IsFinish3DoEduOpt2"
											ng-model="IsFinish3DoEduOpt2" ng-value="2"> <s:message
												code="notifyIsFinish3DoEduOpt2" />
									</span> </label> 
									
									<label for="IsFinishDoEduOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoEduOptRemark" name="IsFinishDoEduOptRemark"
										ng-model="IsFinishDoEduOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoEduOptRemark.$error.required && editForm.FinishDoEduOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoEduOptRemark">
										<textarea id="FinishDoEduOptRemark"
											name="FinishDoEduOptRemark"
											ng-required="EventType==3 && IsFinishDoEduOptRemark"
											ng-model="FinishDoEduOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									<label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish3DoEduOpt1 && !IsFinish3DoEduOpt2 && !IsFinishDoEduOptRemark"> <s:message
												code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==4">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep6TitleDetail" />(<s:message
										code="notifyEventType4" />)</b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="Finish4Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span> <input
									type="radio" id="Finish4Reason1" name="Finish4Reason"
									ng-model="Finish4Reason" ng-value="1"><label
									for="Finish4Reason1" class="label_for_radio"><s:message
											code="notifyFinish4Reason1" /> </label>
								</span> <span> <input type="radio" id="Finish4Reason2"
									name="Finish4Reason" ng-model="Finish4Reason" ng-value="2"><label
									for="Finish4Reason2" class="label_for_radio"><s:message
											code="notifyFinish4Reason2" /> </label>
								</span><span> <input type="radio" id="Finish4Reason3"
									name="Finish4Reason" ng-model="Finish4Reason" ng-value="3"><label
									for="Finish4Reason3" class="label_for_radio"><s:message
											code="notifyFinish4Reason3" /> </label>
								</span><span> <input type="radio" id="Finish4Reason4"
									name="Finish4Reason" ng-model="Finish4Reason" ng-value="4"><label
									for="Finish4Reason4" class="label_for_radio"><s:message
											code="notifyFinish4Reason4" /> </label>
								</span><span> <input type="radio" id="Finish4Reason5"
									name="Finish4Reason" ng-model="Finish4Reason" ng-value="5"><label
									for="Finish4Reason5" class="label_for_radio"><s:message
											code="notifyFinish4Reason5" /> </label>
								</span>
								<div class="form_input" ng-show="Finish4Reason==5">
									<textarea id="Finish4ReasonOther" name="Finish4ReasonOther"
										ng-model="Finish4ReasonOther" class="form-control"
										ng-required="Finish4Reason==5 && EventType==4"
										placeholder="<s:message code="notifyFinish4ReasonOther" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<h5 class="text-danger"
								ng-show="editForm.Finish4ReasonOther.$error.required && editForm.Finish4ReasonOther.$dirty">
								<s:message code="pleaseEnter" />
								<s:message code="notifyFinish4ReasonOther" />
							</h5>
						</div>
						<div>
							<div class="form_group">
								<label for="Finish4Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish4ReasonRemark" /></label>
								<div class="form_input form_input_search">
									<textarea id="Finish4ReasonRemark" name="Finish4ReasonRemark"
										ng-model="Finish4ReasonRemark" class="form-control"
										placeholder="<s:message code="notifyFinish4ReasonRemark" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="FinishDo"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoSys"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label for="IsFinish4DoSysOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish4DoSysOpt1" name="IsFinish4DoSysOpt1"
											ng-required="Status==4 && EventType==4 && !IsFinish4DoSysOpt1 &&!IsFinishDoSysOptRemark" 
											ng-model="IsFinish4DoSysOpt1" ng-value="1"> <s:message
												code="notifyIsFinish4DoSysOpt1" />
									</span></label> 
									
									<label for="IsFinishDoSysOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoSysOptRemark" name="IsFinishDoSysOptRemark"
										ng-model="IsFinishDoSysOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoSysOptRemark.$error.required && editForm.FinishDoSysOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoSysOptRemark">
										<textarea id="FinishDoSysOptRemark"
											name="FinishDoSysOptRemark"
											ng-required="EventType==4 && IsFinishDoSysOptRemark"
											ng-model="FinishDoSysOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									<label class="input_for_radio_group"> <span
										class="text-danger" ng-show="!IsFinish4DoSysOpt1  && !IsFinishDoSysOptRemark"> <s:message
												code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoEdu"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label for="IsFinish4DoEduOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish4DoEduOpt1" name="IsFinish4DoEduOpt1"
											ng-required="Status==4 && EventType==4 && !IsFinish4DoEduOpt1 && !IsFinish4DoEduOpt2 && !IsFinish4DoEduOpt3 && !IsFinish4DoEduOpt4 && !IsFinishDoEduOptRemark"
											ng-model="IsFinish4DoEduOpt1" ng-value="1"> <s:message
												code="notifyIsFinish4DoEduOpt1" />
									</span></label><label for="IsFinish4DoEduOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish4DoEduOpt2" name="IsFinish4DoEduOpt2"
											ng-model="IsFinish4DoEduOpt2" ng-value="2"> <s:message
												code="notifyIsFinish4DoEduOpt2" />
									</span> </label><label for="IsFinish4DoEduOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish4DoEduOpt3" name="IsFinish4DoEduOpt3"
											ng-model="IsFinish4DoEduOpt3" ng-value="3"> <s:message
												code="notifyIsFinish4DoEduOpt3" />
									</span> </label><label for="IsFinish4DoEduOpt4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish4DoEduOpt4" name="IsFinish4DoEduOpt4"
											ng-model="IsFinish4DoEduOpt4" ng-value="4"> <s:message
												code="notifyIsFinish4DoEduOpt4" />
									</span> </label> 
									
									<label for="IsFinishDoEduOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoEduOptRemark" name="IsFinishDoEduOptRemark"
										ng-model="IsFinishDoEduOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoEduOptRemark.$error.required && editForm.FinishDoEduOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoEduOptRemark">
										<textarea id="FinishDoEduOptRemark"
											name="FinishDoEduOptRemark"
											ng-required="EventType==4 && IsFinishDoEduOptRemark"
											ng-model="FinishDoEduOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									<label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish4DoEduOpt1 && !IsFinish4DoEduOpt2 && !IsFinish4DoEduOpt3 && !IsFinish4DoEduOpt4 && !IsFinishDoEduOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" ng-show="EventType==5">
					<div class="col-xs-12">
						<div class="alert alert-dismissible alert-warning form_title_bar">
							<h3>
								<b><s:message code="notifyStep6TitleDetail" />(<s:message
										code="notifyEventType5" />)</b>
							</h3>
						</div>
					</div>
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="Finish5Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label>
								<div class="form_input form_input_search">
									<span> <input type="radio" id="Finish5Reason1"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="1"><label
										for="Finish5Reason1" class="label_for_radio"><s:message
												code="notifyFinish5Reason1" /> </label>
									</span> <span> <input type="radio" id="Finish5Reason2"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="2"><label
										for="Finish5Reason2" class="label_for_radio"><s:message
												code="notifyFinish5Reason2" /> </label>
									</span><span> <input type="radio" id="Finish5Reason3"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="3"><label
										for="Finish5Reason3" class="label_for_radio"><s:message
												code="notifyFinish5Reason3" /> </label>
									</span><span> <input type="radio" id="Finish5Reason4"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="4"><label
										for="Finish5Reason4" class="label_for_radio"><s:message
												code="notifyFinish5Reason4" /> </label>
									</span><span> <input type="radio" id="Finish5Reason5"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="5"><label
										for="Finish5Reason5" class="label_for_radio"><s:message
												code="notifyFinish5Reason5" /> </label>
									</span><span> <input type="radio" id="Finish5Reason6"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="6"><label
										for="Finish5Reason6" class="label_for_radio"><s:message
												code="notifyFinish5Reason6" /> </label>
									</span> <span> <input type="radio" id="Finish5Reason7"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="7"><label
										for="Finish5Reason7" class="label_for_radio"><s:message
												code="notifyFinish5Reason7" /> </label>
									</span> <span> <input type="radio" id="Finish5Reason8"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="8"><label
										for="Finish5Reason8" class="label_for_radio"><s:message
												code="notifyFinish5Reason8" /> </label>
									</span><span> <input type="radio" id="Finish5Reason3"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="9"><label
										for="Finish5Reason9" class="label_for_radio"><s:message
												code="notifyFinish5Reason9" /> </label>
									</span><span> <input type="radio" id="Finish5Reason10"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="10"><label
										for="Finish5Reason10" class="label_for_radio"><s:message
												code="notifyFinish5Reason10" /> </label>
									</span><span> <input type="radio" id="Finish5Reason11"
										name="Finish5Reason" ng-model="Finish5Reason" ng-value="11"><label
										for="Finish5Reason11" class="label_for_radio"><s:message
												code="notifyFinish5Reason11" /> </label>
									</span> <span class="text-danger"
										ng-show="editForm.Finish5ReasonOther.$error.required && editForm.Finish5ReasonOther.$dirty">
										<s:message code="pleaseEnter" /> <s:message
											code="notifyFinish5ReasonOther" />
									</span>
									<div class="form_input" ng-show="Finish5Reason==11">
										<textarea id="Finish5ReasonOther" name="Finish5ReasonOther"
											ng-model="Finish5ReasonOther" class="form-control"
											ng-required="Finish5Reason==11 && EventType==5"
											placeholder="<s:message code="notifyFinish5ReasonOther" />"
											rows="5" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label for="Finish5Reason"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish5ReasonRemark" /></label>
								<div class="form_input form_input_search">
									<textarea id="Finish5ReasonRemark" name="Finish5ReasonRemark"
										ng-model="Finish5ReasonRemark" class="form-control"
										placeholder="<s:message code="notifyFinish5ReasonRemark" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="FinishDo"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoSys"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label for="IsFinish5DoSysOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoSysOpt1" name="IsFinish5DoSysOpt1"
											ng-required="Status==4 && EventType==5 && !IsFinish5DoSysOpt1 &&!IsFinish5DoSysOpt2 &&!IsFinish5DoSysOpt3 &&!IsFinish5DoSysOpt4 &&!IsFinishDoSysOptRemark" 
											ng-model="IsFinish5DoSysOpt1" ng-value="1"> <s:message
												code="notifyIsFinish5DoSysOpt1" />
									</span><span class="text-danger"
										ng-show="editForm.Finish5DoSysOpt1Remark.$error.required && editForm.Finish5DoSysOpt1Remark.$dirty">
											<s:message code="pleaseEnter" /> <s:message
												code="notifyFinish5DoSysOpt1Remark" />
									</span>
										<div class="form_input form_input_fluid"
											ng-show="IsFinish5DoSysOpt1">
											<textarea id="Finish5DoSysOpt1Remark"
												name="Finish5DoSysOpt1Remark"
												ng-required="EventType==5 && IsFinish5DoSysOpt1"
												ng-model="Finish5DoSysOpt1Remark" class="form-control"
												placeholder="<s:message code="notifyFinish5DoSysOpt1Remark" />"
												rows="5" autocomplete="off"></textarea>
										</div> </label><label for="IsFinish5DoSysOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoSysOpt2" name="IsFinish5DoSysOpt2"
											ng-model="IsFinish5DoSysOpt2" ng-value="2"> <s:message
												code="notifyIsFinish5DoSysOpt2" />
									</span></label><label for="IsFinish5DoSysOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoSysOpt3" name="IsFinish5DoSysOpt3"
											ng-model="IsFinish5DoSysOpt3" ng-value="3"> <s:message
												code="notifyIsFinish5DoSysOpt3" />
									</span></label><label for="IsFinish5DoSysOpt4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoSysOpt4" name="IsFinish5DoSysOpt4"
											ng-model="IsFinish5DoSysOpt4" ng-value="4"> <s:message
												code="notifyIsFinish5DoSysOpt4" />
									</span></label>
									
									<label for="IsFinishDoSysOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoSysOptRemark" name="IsFinishDoSysOptRemark"
										ng-model="IsFinishDoSysOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoSysOptRemark.$error.required && editForm.FinishDoSysOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoSysOptRemark">
										<textarea id="FinishDoSysOptRemark"
											name="FinishDoSysOptRemark"
											ng-required="EventType==5 && IsFinishDoSysOptRemark"
											ng-model="FinishDoSysOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label> 
									
									
									 <label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish5DoSysOpt1 &&!IsFinish5DoSysOpt2 &&!IsFinish5DoSysOpt3 &&!IsFinish5DoSysOpt4 && !IsFinishDoSysOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label for="IsFinishDoEdu"
										class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label for="IsFinish5DoEduOpt1"
										class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoEduOpt1" name="IsFinish5DoEduOpt1"
											ng-required="Status==4 && EventType==5 && !IsFinish5DoEduOpt1 && !IsFinish5DoEduOpt2 && !IsFinish5DoEduOpt3 && !IsFinish5DoEduOpt4  && !IsFinishDoEduOptRemark"
											ng-model="IsFinish5DoEduOpt1" ng-value="1"> <s:message
												code="notifyIsFinish5DoEduOpt1" />
									</span></label><label for="IsFinish5DoEduOpt2" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoEduOpt2" name="IsFinish5DoEduOpt2"
											ng-model="IsFinish5DoEduOpt2" ng-value="2"> <s:message
												code="notifyIsFinish5DoEduOpt2" />
									</span> </label><label for="IsFinish5DoEduOpt3" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoEduOpt3" name="IsFinish5DoEduOpt3"
											ng-model="IsFinish5DoEduOpt3" ng-value="3"> <s:message
												code="notifyIsFinish5DoEduOpt3" />
									</span> </label><label for="IsFinish5DoEduOpt4" class="input_for_radio_group"><span
										class="form_input_search"> <input type="checkbox"
											id="IsFinish5DoEduOpt4" name="IsFinish5DoEduOpt4"
											ng-model="IsFinish5DoEduOpt4" ng-value="4"> <s:message
												code="notifyIsFinish5DoEduOpt4" />
									</span> </label>
									
									<label for="IsFinishDoEduOptRemark" class="input_for_radio_group"><span
									class="form_input_search"> <input type="checkbox"
										id="IsFinishDoEduOptRemark" name="IsFinishDoEduOptRemark"
										ng-model="IsFinishDoEduOptRemark"> 備註
								</span><span class="text-danger"
									ng-show="editForm.FinishDoEduOptRemark.$error.required && editForm.FinishDoEduOptRemark.$dirty">
										<s:message code="pleaseEnter" /> 備註
								</span>
									<div class="form_input form_input_fluid"
										ng-show="IsFinishDoEduOptRemark">
										<textarea id="FinishDoEduOptRemark"
											name="FinishDoEduOptRemark"
											ng-required="EventType==5 && IsFinishDoEduOptRemark"
											ng-model="FinishDoEduOptRemark" class="form-control"
											placeholder="備註"
											rows="5" autocomplete="off"></textarea>
									</div> </label>
									 
									<label class="input_for_radio_group"> <span
										class="text-danger"
										ng-show="!IsFinish5DoEduOpt1 && !IsFinish5DoEduOpt2 && !IsFinish5DoEduOpt3 && !IsFinish5DoEduOpt4 && !IsFinishDoEduOptRemark">
											<s:message code="pleaseSelectMoreThenOne" />
									</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label for="FinishDoOther"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDoOther" /></label>
								<div class="form_input form_input_search">
									<textarea id="FinishDoOther" name="FinishDoOther"
										ng-model="FinishDoOther" class="form-control"
										placeholder="<s:message
											code="notifyFinishDoOtherPlaceholder" />"
										rows="5" autocomplete="off"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_yellow">
							<b><s:message code="notifyContent5" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label for="FinishDateTime"
									class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDateTime" /></label>
								<div class="form_input form_input_search">
									<div class="input-group">
										<input type="text" id="FinishDateTime" name="FinishDateTime"
											ng-model="FinishDateTime" class="form-control"
											placeholder="<s:message code="notifyFinishDateTime" />"
											autocomplete="off"><span class="input-group-addon">
											<i class="fas fa-calendar-alt"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>			
		</div>
		<div ng-show="IsSeeOpinion">
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
					<textarea id="Opinion" name="Opinion" ng-model="$parent.Opinion"
						rows="5" autocomplete="off" class="form-control"></textarea>
				</div>
			</div>
		</div>
		<div ng-if="NotificationLog.length > 0">
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
						<tr ng-repeat="item in NotificationLog">
							<td><span ng-if="item.PreStatus==1">編輯中</span> <span
								ng-if="item.PreStatus==2">撤銷中</span> <span
								ng-if="item.PreStatus==31">上級機關通報審核</span><span
								ng-if="item.PreStatus==32">業務管理機關通報審核</span><span
								ng-if="item.PreStatus==33">H-ISAC通報審核者通報審核</span><span
								ng-if="item.PreStatus==4">處理中</span> <span
								ng-if="item.PreStatus==51">上級機關處理審核</span><span
								ng-if="item.PreStatus==52">業務管理機關處理審核中</span><span
								ng-if="item.PreStatus==53">H-ISAC通報審核者處理審核中</span> <span
								ng-if="item.PreStatus==6">已結案</span> <span
								ng-if="item.PreStatus==7">已銷案</span> <span
								ng-if="item.PreStatus==8">退回補述</span> <span
								ng-if="item.PreStatus==9">退回補述</span><span><i
									class="fas fa-fw fa-arrow-right"></i></span> <span
								ng-if="item.Status==1"> 編輯中</span> <span ng-if="item.Status==2">撤銷中</span>
								<span ng-if="item.Status==31">上級機關通報審核</span><span
								ng-if="item.Status==32">業務管理機關通報審核</span><span
								ng-if="item.Status==33">H-ISAC通報審核者通報審核</span><span
								ng-if="item.Status==4">處理中</span> <span ng-if="item.Status==51">上級機關處理審核</span><span
								ng-if="item.Status==52">業務管理機關處理審核中</span><span
								ng-if="item.Status==53">H-ISAC通報審核者處理審核中</span> <span
								ng-if="item.Status==6">已結案</span> <span ng-if="item.Status==7">已銷案</span>
								<span ng-if="item.Status==8">退回補述</span> <span
								ng-if="item.Status==9">退回補述</span></td>
							<td><span ng-if="item.Status==1">編輯中</span> <span
								ng-if="item.Status==2">撤銷中</span> <span ng-if="item.Status==31">上級機關通報審核</span><span
								ng-if="item.Status==32">業務管理機關通報審核</span><span
								ng-if="item.Status==33">H-ISAC通報審核者通報審核</span> <span
								ng-if="item.Status==4">處理中</span> <span ng-if="item.Status==51">上級機關處理審核</span><span
								ng-if="item.Status==52">業務管理機關處理審核中</span><span
								ng-if="item.Status==53">H-ISAC通報審核者處理審核中</span> <span
								ng-if="item.Status==6">已結案</span> <span ng-if="item.Status==7">已銷案</span>
								<span ng-if="item.Status==8">退回補述</span> <span
								ng-if="item.Status==9">退回補述</span></td>
							<td>{{item.CreateName}}</td>
							<td>{{item.CreateTime}}</td>
							<td>{{item.Opinion}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="form_group">
				<br />&nbsp;<br />
			</div>
		</div>
		<div>			
			<div class="row tab_block text-center">
				<button ng-disabled="!stepPrev" class="btn btn-primary pull-left"
					ng-click="gotToStep(step - 1)">
					<i class="fas fa-fw fa-step-backward"></i>
					<s:message code="globalStepPrev" />
				</button>
				<button ng-disabled="!stepNext" class="btn btn-primary pull-right"
					ng-click="gotToStep(step + 1)">
					<s:message code="globalStepNext" />
					<i class="fas fa-fw fa-step-forward"></i>
				</button>
				<div class="row help-block visible-xs"></div>
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
				<button class="btn btn_custom btn_blue" ng-show="btnIns"
					ng-click="createData(true,true)" ng-disabled="!editForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnSubmit" />
				</button>
				<div class="row help-block visible-xs"></div>
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
				<button class="btn btn_custom btn_blue" ng-show="btnUpd"
					ng-click="updateData(true,true)" ng-disabled="!editForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnSubmit" />
				</button>
				<button class="btn btn_custom btn_blue" ng-show="handle && IsHandledByMyself && !HandleInformationDisable"
					ng-click="chooseHandleInformation(Id)" ng-disabled="HandleInformationId==null">
					<i class="fas fa-fw fa-check"></i>
					確定支援廠商
				</button>
				<button class="btn btn_custom btn_blue" ng-show="handle"
					ng-click="examine(Id,'review')" ng-disabled="!editForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnSubmit" />
				</button>
				<button class="btn btn_custom btn_gray" type="button"
					ng-click="closeEdit()">
					<i class="fas fa-fw fa-undo"></i>
					<s:message code="btnReturn" />
				</button>
			</div>
		</div>
	</form>
</div>