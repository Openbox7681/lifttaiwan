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
<div id="divView" class="container" ng-if="View_Show">
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
	</div>	
	<div class="row">
		<ul class="nav nav-tabs" id="tabNotifyQueryStep">
			<li class="active"><a data-toggle="tab"
				href="#tabNotifyQueryStep1" ng-click="gotToQueryStep(1)"
				aria-expanded="true"><s:message code="notifyStep1Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyQueryStep2"
				ng-click="gotToQueryStep(2)" aria-expanded="false"><s:message
						code="notifyStep2Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyQueryStep3"
				ng-click="gotToQueryStep(3)" aria-expanded="false"><s:message
						code="notifyStep3Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyQueryStep4"
				ng-click="gotToQueryStep(4)" aria-expanded="false"><s:message
						code="notifyStep4Title" /></a></li>
			<li class=""><a data-toggle="tab" href="#tabNotifyQueryStep5"
				ng-click="gotToQueryStep(5)" aria-expanded="false"><s:message
						code="notifyStep5Title" /></a></li>
			<li class="" ng-show="Status==4 || Status==9 || Status==51 || Status==52 || Status==53 || Status==6"><a
				data-toggle="tab" href="#tabNotifyQueryStep6"
				ng-click="gotToQueryStep(6)" aria-expanded="false"><s:message
						code="notifyStep6Title" /></a></li>
		</ul>
	</div>
	<form name="viewForm">
		<div class="tab-content tab_content">
			<div id="tabNotifyQueryStep1" class="tab-pane active">
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
			<div id="tabNotifyQueryStep2" class="tab-pane">
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
			<div id="tabNotifyQueryStep3" class="tab-pane">
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
			<div id="tabNotifyQueryStep4" class="tab-pane">
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
			<div id="tabNotifyQueryStep5" class="tab-pane">
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
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyNeedSupportRemark" /></label><span
									class="form-text form_text form_input_search">{{NeedSupportRemark}}</span>
							</div>
						</div>						
					</div>
				</div>
			</div>
			<div id="tabNotifyQueryStep6" class="tab-pane"
				ng-show="Status==4 || Status==9 || Status==51 || Status==52 || Status==53 || Status==6">
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
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==1"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=1"></i> <s:message
										code="notifyFinish1Reason1" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==2"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=2"></i> <s:message
										code="notifyFinish1Reason2" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==3"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=3"></i> <s:message
										code="notifyFinish1Reason3" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==4"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=4"></i> <s:message
										code="notifyFinish1Reason4" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==5"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=5"></i> <s:message
										code="notifyFinish1Reason5" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish1Reason==6"></i><i
									class="far fa-fw fa-circle" ng-show="Finish1Reason!=6"></i> <s:message
										code="notifyFinish1Reason6" /> </span> <span
									ng-show="Finish1Reason==6"
									class="form-text form_text form_input_search">{{Finish1ReasonOther}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDo" /></label> <label
									class="form_label form_label_search_fix form_label_gray"></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsFinishDoSys" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt1"></i>
										<s:message code="notifyIsFinish1DoSysOpt1" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt2"></i>
										<s:message code="notifyIsFinish1DoSysOpt2" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt3"></i>
										<s:message code="notifyIsFinish1DoSysOpt3" />
								</span><span ng-show="IsFinish1DoSysOpt3"
									class="form-text form_text form_input_search"><br />{{Finish1DoSysOpt3Remark}}</span>
								</label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt4"></i>
										<s:message code="notifyIsFinish1DoSysOpt4" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt5"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt5"></i>
										<s:message code="notifyIsFinish1DoSysOpt5" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt6"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt6"></i>
										<s:message code="notifyIsFinish1DoSysOpt6" />
								</span><span ng-show="IsFinish1DoSysOpt6"
									class="form-text form_text form_input_search"><br />{{Finish1DoSysOpt6Remark}}</span></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt7"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt7"></i>
										<s:message code="notifyIsFinish1DoSysOpt7" />
								</span> <span ng-show="IsFinish1DoSysOpt7"
									class="form-text form_text form_input_search"><br />{{Finish1DoSysOpt7Remark}}</span>
								</label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt8"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt8"></i>
										<s:message code="notifyIsFinish1DoSysOpt8" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoSysOpt9"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt9"></i>
										<s:message code="notifyIsFinish1DoSysOpt9" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square"
										ng-show="IsFinish1DoSysOpt10"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoSysOpt10"></i>
										<s:message code="notifyIsFinish1DoSysOpt10" />
								</span></label>
								<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoSysOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoSysOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoSysOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoSysOptRemark}}</span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyIsFinishDoEdu" /></label><label
									class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoEduOpt1"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoEduOpt1"></i>
										<s:message code="notifyIsFinish1DoEduOpt1" />
								</span></label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoEduOpt2"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoEduOpt2"></i>
										<s:message code="notifyIsFinish1DoEduOpt2" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoEduOpt3"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoEduOpt3"></i>
										<s:message code="notifyIsFinish1DoEduOpt3" />
								</span> </label><label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinish1DoEduOpt4"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinish1DoEduOpt4"></i>
										<s:message code="notifyIsFinish1DoEduOpt4" />
								</span> </label>
								<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoEduOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoEduOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoEduOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoEduOptRemark}}</span></label>
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
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==1"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=1"></i> <s:message
										code="notifyFinish2Reason1" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==2"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=2"></i> <s:message
										code="notifyFinish2Reason2" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==3"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=3"></i> <s:message
										code="notifyFinish2Reason3" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==4"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=4"></i> <s:message
										code="notifyFinish2Reason4" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==5"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=5"></i> <s:message
										code="notifyFinish2Reason5" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish2Reason==6"></i><i
									class="far fa-fw fa-circle" ng-show="Finish2Reason!=6"></i> <s:message
										code="notifyFinish2Reason6" /> </span> <span
									ng-show="Finish2Reason==6"
									class="form-text form_text form_input_search">{{Finish2ReasonOther}}</span>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish2ReasonRemark" /></label> <span
									class="form-text form_text form_input_search">{{Finish2ReasonRemark}}</span>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoSysOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoSysOpt1"></i>
											<s:message code="notifyIsFinish2DoSysOpt1" />
									</span><span ng-show="IsFinish2DoSysOpt1"
										class="form-text form_text form_input_search"><br />{{Finish2DoSysOpt1Remark}}</span>
									</label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoSysOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoSysOpt2"></i>
											<s:message code="notifyIsFinish2DoSysOpt2" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoSysOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoSysOpt3"></i>
											<s:message code="notifyIsFinish2DoSysOpt3" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoSysOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoSysOpt4"></i>
											<s:message code="notifyIsFinish2DoSysOpt4" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoSysOpt5"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoSysOpt5"></i>
											<s:message code="notifyIsFinish2DoSysOpt5" />
									</span></label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoSysOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoSysOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoSysOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoSysOptRemark}}</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoEduOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoEduOpt1"></i>
											<s:message code="notifyIsFinish2DoEduOpt1" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoEduOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoEduOpt2"></i>
											<s:message code="notifyIsFinish2DoEduOpt2" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoEduOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoEduOpt3"></i>
											<s:message code="notifyIsFinish2DoEduOpt3" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish2DoEduOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish2DoEduOpt4"></i>
											<s:message code="notifyIsFinish2DoEduOpt4" />
									</span> </label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoEduOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoEduOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoEduOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoEduOptRemark}}</span></label>
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
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label> <label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoSysOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoSysOpt1"></i>
											<s:message code="notifyIsFinish3DoSysOpt1" />
									</span></label> <label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoSysOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoSysOpt2"></i>
											<s:message code="notifyIsFinish3DoSysOpt2" />
									</span></label> <label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoSysOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoSysOpt3"></i>
											<s:message code="notifyIsFinish3DoSysOpt3" />
									</span><span ng-show="IsFinish3DoSysOpt3"
										class="form-text form_text form_input_search"><br />{{Finish3DoSysOpt3Remark}}</span></label>
									<label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoSysOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoSysOpt4"></i>
											<s:message code="notifyIsFinish3DoSysOpt4" />
									</span> <span ng-show="IsFinish3DoSysOpt4"
										class="form-text form_text form_input_search"><br />{{Finish3DoSysOpt4Remark}}</span></label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoSysOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoSysOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoSysOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoSysOptRemark}}</span></label>
									<label class="input_for_radio_group"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoEduOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoEduOpt1"></i>
											<s:message code="notifyIsFinish3DoEduOpt1" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish3DoEduOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish3DoEduOpt2"></i>
											<s:message code="notifyIsFinish3DoEduOpt2" />
									</span> </label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoEduOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoEduOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoEduOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoEduOptRemark}}</span></label>
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
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish4Reason==1"></i><i
									class="far fa-fw fa-circle" ng-show="Finish4Reason!=1"></i> <s:message
										code="notifyFinish4Reason1" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish4Reason==2"></i><i
									class="far fa-fw fa-circle" ng-show="Finish4Reason!=2"></i> <s:message
										code="notifyFinish4Reason2" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish4Reason==3"></i><i
									class="far fa-fw fa-circle" ng-show="Finish4Reason!=3"></i> <s:message
										code="notifyFinish4Reason3" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish4Reason==4"></i><i
									class="far fa-fw fa-circle" ng-show="Finish4Reason!=4"></i> <s:message
										code="notifyFinish4Reason4" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish4Reason==5"></i><i
									class="far fa-fw fa-circle" ng-show="Finish4Reason!=5"></i> <s:message
										code="notifyFinish4Reason5" /> </span><label
									class="input_for_radio_group"> <span
									ng-show="Finish4Reason==5"
									class="form-text form_text form_input_search">{{Finish4ReasonOther}}</span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish4ReasonRemark" /></label> <span
									class="form-text form_text form_input_search">{{Finish4ReasonRemark}}</span>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish4DoSysOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish4DoSysOpt1"></i>
											<s:message code="notifyIsFinish4DoSysOpt1" />
									</span></label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoSysOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoSysOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoSysOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoSysOptRemark}}</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish4DoEduOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish4DoEduOpt1"></i>
											<s:message code="notifyIsFinish4DoEduOpt1" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish4DoEduOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish4DoEduOpt2"></i>
											<s:message code="notifyIsFinish4DoEduOpt2" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish4DoEduOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish4DoEduOpt3"></i>
											<s:message code="notifyIsFinish4DoEduOpt3" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish4DoEduOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish4DoEduOpt4"></i>
											<s:message code="notifyIsFinish4DoEduOpt4" />
									</span> </label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoEduOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoEduOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoEduOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoEduOptRemark}}</span></label>
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
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishReason" /></label> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==1"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=1"></i> <s:message
										code="notifyFinish5Reason1" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==2"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=2"></i> <s:message
										code="notifyFinish5Reason2" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==3"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=3"></i> <s:message
										code="notifyFinish5Reason3" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==4"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=4"></i> <s:message
										code="notifyFinish5Reason4" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==5"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=5"></i> <s:message
										code="notifyFinish5Reason5" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==6"></i><i
									class="far fa-fw fa-circle" ng-show="Finish6Reason!=6"></i> <s:message
										code="notifyFinish5Reason6" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==7"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=7"></i> <s:message
										code="notifyFinish5Reason7" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==8"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=8"></i> <s:message
										code="notifyFinish5Reason8" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==9"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=9"></i> <s:message
										code="notifyFinish5Reason9" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==10"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=10"></i> <s:message
										code="notifyFinish5Reason10" /> </span> <span><i
									class="far fa-fw fa-dot-circle" ng-show="Finish5Reason==11"></i><i
									class="far fa-fw fa-circle" ng-show="Finish5Reason!=11"></i> <s:message
										code="notifyFinish5Reason11" /> </span> <label
									class="input_for_radio_group"><span
									ng-show="Finish5Reason==11"
									class="form-text form_text form_input_search">{{Finish5ReasonOther}}</span></label>
							</div>
						</div>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinish5ReasonRemark" /></label> <span
									class="form-text form_text form_input_search">{{Finish5ReasonRemark}}</span>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyFinishDo" /></label> <label
										class="form_label form_label_search_fix form_label_gray"></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoSys" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoSysOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoSysOpt1"></i>
											<s:message code="notifyIsFinish5DoSysOpt1" />
									</span><span ng-show="IsFinish5DoSysOpt1"
										class="form-text form_text form_input_search"><br />{{Finish5DoSysOpt1Remark}}</span></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoSysOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoSysOpt2"></i>
											<s:message code="notifyIsFinish5DoSysOpt2" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoSysOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoSysOpt3"></i>
											<s:message code="notifyIsFinish5DoSysOpt3" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoSysOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoSysOpt4"></i>
											<s:message code="notifyIsFinish5DoSysOpt4" />
									</span></label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoSysOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoSysOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoSysOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoSysOptRemark}}</span></label>
								</div>
							</div>
							<div>
								<div class="form_group">
									<label class="form_label form_label_search form_label_gray"><s:message
											code="notifyIsFinishDoEdu" /></label><label
										class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoEduOpt1"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoEduOpt1"></i>
											<s:message code="notifyIsFinish5DoEduOpt1" />
									</span></label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoEduOpt2"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoEduOpt2"></i>
											<s:message code="notifyIsFinish5DoEduOpt2" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoEduOpt3"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoEduOpt3"></i>
											<s:message code="notifyIsFinish5DoEduOpt3" />
									</span> </label><label class="input_for_radio_group"><span
										class="form_input_search"> <i
											class="far fa-fw fa-check-square"
											ng-show="IsFinish5DoEduOpt4"></i><i
											class="far fa-fw fa-square" ng-show="!IsFinish5DoEduOpt4"></i>
											<s:message code="notifyIsFinish5DoEduOpt4" />
									</span> </label>
									<label class="input_for_radio_group"><span
									class="form_input_search"> <i
										class="far fa-fw fa-check-square" ng-show="IsFinishDoEduOptRemark"></i><i
										class="far fa-fw fa-square" ng-show="!IsFinishDoEduOptRemark"></i>
										備註
								</span><span ng-show="IsFinishDoEduOptRemark"
									class="form-text form_text form_input_search"><br />{{FinishDoEduOptRemark}}</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDoOther" /></label><span
									class="form-text form_text form_input_search">{{FinishDoOther}}</span>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<h4 class="form_heading form_heading_yellow">
							<b><s:message code="notifyContent5" /></b>
						</h4>
						<div>
							<div class="form_group">
								<label class="form_label form_label_search form_label_gray"><s:message
										code="notifyFinishDateTime" /></label> <span
									class="form-text form_text form_input_search_half">{{FinishDateTime}}</span>
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
							rows="5" autocomplete="off" class="form-control"
							ng-required="true && IsSeeOpinion"></textarea>
						<h5 class="text-danger" ng-show="viewForm.Opinion.$error.required">
							<s:message code="pleaseEnter" />
							<s:message code="globalProcessLogContent" />
						</h5>
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
			<div class="row tab_block text-center">
				<button ng-disabled="!queryStepPrev"
					class="btn btn-primary pull-left"
					ng-click="gotToQueryStep(queryStep - 1)">
					<i class="fas fa-fw fa-step-backward"></i>
					<s:message code="globalStepPrev" />
				</button>
				<button ng-disabled="!queryStepNext"
					class="btn btn-primary pull-right"
					ng-click="gotToQueryStep(queryStep + 1)">
					<s:message code="globalStepNext" />
					<i class="fas fa-fw fa-step-forward"></i>
				</button>
				<div class="row help-block visible-xs"></div>
				<button class="btn btn_custom btn_blue" ng-show='pass'
					ng-click="examine(Id,'pass')" ng-disabled="!viewForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnPass" />
				</button>
				<button class="btn btn_custom btn_blue"  ng-show='back'
					ng-click="examine(Id,'back')" ng-disabled="!viewForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					退回補述
				</button>
				<button class="btn btn_custom btn_blue"
					ng-click="examine(Id,'undo')" ng-show="reject" ng-disabled="!viewForm.$valid">
					<i class="fas fa-fw fa-check"></i>
					<s:message code="btnReject" />
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