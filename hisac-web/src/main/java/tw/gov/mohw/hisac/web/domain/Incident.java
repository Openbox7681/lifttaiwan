package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "incident", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Incident {
	
	private Long id;
	private @NotNull Date postDateTime;
	private String postId;
	private Long reporterName;
	private Long handleName;
	private Long contactorId;
	private String contactorTel;
	private String contactorFax;
	private String contactorEmail;
	private Date incidentDiscoveryTime;
	private Integer hostAmount;
	private Integer serverAmount;
	private Integer informationAmount;
	private Integer otherDeviceAmount;
	private String otherDeviceName;
	private String deviceRemark;
	private String assessDamage;
	private String assessDamageRemark;
	private Boolean isOsOpt1;
	private Boolean isOsOpt2;
	private Boolean isOsOpt3;
	private String isOsOpt3Other;
	private @NotNull Integer eventType;
	private Boolean isEventType1Opt1;
	private Boolean isEventType1Opt2;
	private Boolean isEventType1Opt3;
	private Boolean isEventType1Opt4;
	private Boolean isEventType1Opt5;
	private Boolean isEventType1Opt6;
	private Boolean isEventType2Opt1;
	private Boolean isEventType2Opt2;
	private Boolean isEventType2Opt3;
	private Boolean isEventType2Opt4;
	private Boolean isEventType2Opt5;
	private Boolean isEventType3Opt1;
	private Boolean isEventType3Opt2;
	private Boolean isEventType4Opt1;
	private Boolean isEventType4Opt2;
	private Boolean isEventType4Opt3;
	private Boolean isEventType4Opt4;
	private String eventType5Other;
	private String eventRemark;
	private Boolean isEventCause1Opt1;
	private Boolean isEventCause1Opt2;
	private Boolean isEventCause1Opt3;
	private Boolean isEventCause1Opt4;
	private Boolean isEventCause1Opt5;
	private Boolean isEventCause1Opt6;
	private String isEventCause1Opt6Other;
	private String eventEvaluation;
	private String eventProcess;
	private Boolean isSecuritySetting1Opt1;
	private Boolean isSecuritySetting1Opt2;
	private Boolean isSecuritySetting1Opt3;
	private Boolean isSecuritySetting1Opt4;
	private Boolean isSecuritySetting1Opt5;
	private Boolean isSecuritySetting1Opt6;
	private Boolean isSecuritySetting1Opt7;
	private Boolean isSecuritySetting1Opt8;
	private Boolean isSecuritySetting1Opt9;
	private Boolean isSecuritySetting1Opt10;
	private Boolean isSecuritySetting1Opt11;
	private Boolean isSecuritySetting1Opt12;
	private Boolean isSecuritySetting1Opt13;
	private String finishDoOther;
	private Date finishDateTime;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;
	private @NotNull Long status;
	private Long subStatus;
	private String notificationId;
	private Long notificationMainUnit1;
	private Long notificationMainUnit2;
	private Boolean notificationIsCC3;
	
	
	private Integer satisfactionTime;
	private Integer satisfactionProfessionalism;
	private Integer satisfactionService;
	private Integer satisfactionReport;
	private Integer satisfactionTotal;
	private String satisfactionRemark;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "PostDateTime", nullable = false)
	public Date getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(Date postDateTime) {
		this.postDateTime = postDateTime;
	}
	
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	@Column(name = "ReporterName")
	public Long getReporterName() {
		return reporterName;
	}
	public void setReporterName(Long reporterName) {
		this.reporterName = reporterName;
	}

	@Column(name = "HandleName")
	public Long getHandleName() {
		return handleName;
	}
	public void setHandleName(Long handleName) {
		this.handleName = handleName;
	}

	@Column(name = "ContactorId")
	public Long getContactorId() {
		return contactorId;
	}
	public void setContactorId(Long contactorId) {
		this.contactorId = contactorId;
	}
	
	@Column(name = "ContactorTel")
	public String getContactorTel() {
		return contactorTel;
	}
	public void setContactorTel(String contactorTel) {
		this.contactorTel = contactorTel;
	}
	
	@Column(name = "ContactorFax")
	public String getContactorFax() {
		return contactorFax;
	}
	public void setContactorFax(String contactorFax) {
		this.contactorFax = contactorFax;
	}
	
	@Column(name = "ContactorEmail")
	public String getContactorEmail() {
		return contactorEmail;
	}
	public void setContactorEmail(String contactorEmail) {
		this.contactorEmail = contactorEmail;
	}

	@Column(name = "IncidentDiscoveryTime")
	public Date getIncidentDiscoveryTime() {
		return incidentDiscoveryTime;
	}
	public void setIncidentDiscoveryTime(Date incidentDiscoveryTime) {
		this.incidentDiscoveryTime = incidentDiscoveryTime;
	}
	
	@Column(name = "HostAmount")
	public Integer getHostAmount() {
		return hostAmount;
	}
	public void setHostAmount(Integer hostAmount) {
		this.hostAmount = hostAmount;
	}
	
	@Column(name = "ServerAmount")
	public Integer getServerAmount() {
		return serverAmount;
	}
	public void setServerAmount(Integer serverAmount) {
		this.serverAmount = serverAmount;
	}
	
	@Column(name = "InformationAmount")
	public Integer getInformationAmount() {
		return informationAmount;
	}
	public void setInformationAmount(Integer informationAmount) {
		this.informationAmount = informationAmount;
	}
	@Column(name = "OtherDeviceAmount")
	public Integer getOtherDeviceAmount() {
		return otherDeviceAmount;
	}
	public void setOtherDeviceAmount(Integer otherDeviceAmount) {
		this.otherDeviceAmount = otherDeviceAmount;
	}
	@Column(name = "OtherDeviceName")
	public String getOtherDeviceName() {
		return otherDeviceName;
	}
	public void setOtherDeviceName(String otherDeviceName) {
		this.otherDeviceName = otherDeviceName;
	}
	@Column(name = "DeviceRemark")
	public String getDeviceRemark() {
		return deviceRemark;
	}
	public void setDeviceRemark(String deviceRemark) {
		this.deviceRemark = deviceRemark;
	}
	
	@Column(name = "AssessDamage")
	public String getAssessDamage() {
		return assessDamage;
	}
	public void setAssessDamage(String assessDamage) {
		this.assessDamage = assessDamage;
	}	
	@Column(name = "AssessDamageRemark")
	public String getAssessDamageRemark() {
		return assessDamageRemark;
	}
	public void setAssessDamageRemark(String assessDamageRemark) {
		this.assessDamageRemark = assessDamageRemark;
	}	
	
	@Column(name = "IsOsOpt1")
	public Boolean getIsOsOpt1() {
		return isOsOpt1;
	}
	public void setIsOsOpt1(Boolean isOsOpt1) {
		this.isOsOpt1 = isOsOpt1;
	}
	
	@Column(name = "IsOsOpt2")
	public Boolean getIsOsOpt2() {
		return isOsOpt2;
	}
	public void setIsOsOpt2(Boolean isOsOpt2) {
		this.isOsOpt2 = isOsOpt2;
	}
	
	@Column(name = "IsOsOpt3")
	public Boolean getIsOsOpt3() {
		return isOsOpt3;
	}
	public void setIsOsOpt3(Boolean isOsOpt3) {
		this.isOsOpt3 = isOsOpt3;
	}
	
	@Column(name = "IsOsOpt3Other")
	public String getIsOsOpt3Other() {
		return isOsOpt3Other;
	}
	public void setIsOsOpt3Other(String isOsOpt3Other) {
		this.isOsOpt3Other = isOsOpt3Other;
	}
	
	@Column(name = "EventType", nullable = false)
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	@Column(name = "IsEventType1Opt1")
	public Boolean getIsEventType1Opt1() {
		return isEventType1Opt1;
	}
	public void setIsEventType1Opt1(Boolean isEventType1Opt1) {
		this.isEventType1Opt1 = isEventType1Opt1;
	}
	
	@Column(name = "IsEventType1Opt2")
	public Boolean getIsEventType1Opt2() {
		return isEventType1Opt2;
	}
	public void setIsEventType1Opt2(Boolean isEventType1Opt2) {
		this.isEventType1Opt2 = isEventType1Opt2;
	}
	
	@Column(name = "IsEventType1Opt3")
	public Boolean getIsEventType1Opt3() {
		return isEventType1Opt3;
	}
	public void setIsEventType1Opt3(Boolean isEventType1Opt3) {
		this.isEventType1Opt3 = isEventType1Opt3;
	}
	
	@Column(name = "IsEventType1Opt4")
	public Boolean getIsEventType1Opt4() {
		return isEventType1Opt4;
	}
	public void setIsEventType1Opt4(Boolean isEventType1Opt4) {
		this.isEventType1Opt4 = isEventType1Opt4;
	}
	
	@Column(name = "IsEventType1Opt5")
	public Boolean getIsEventType1Opt5() {
		return isEventType1Opt5;
	}
	public void setIsEventType1Opt5(Boolean isEventType1Opt5) {
		this.isEventType1Opt5 = isEventType1Opt5;
	}
	
	@Column(name = "IsEventType1Opt6")
	public Boolean getIsEventType1Opt6() {
		return isEventType1Opt6;
	}
	public void setIsEventType1Opt6(Boolean isEventType1Opt6) {
		this.isEventType1Opt6 = isEventType1Opt6;
	}
	
	@Column(name = "IsEventType2Opt1")
	public Boolean getIsEventType2Opt1() {
		return isEventType2Opt1;
	}
	public void setIsEventType2Opt1(Boolean isEventType2Opt1) {
		this.isEventType2Opt1 = isEventType2Opt1;
	}
	
	@Column(name = "IsEventType2Opt2")
	public Boolean getIsEventType2Opt2() {
		return isEventType2Opt2;
	}
	public void setIsEventType2Opt2(Boolean isEventType2Opt2) {
		this.isEventType2Opt2 = isEventType2Opt2;
	}
	
	@Column(name = "IsEventType2Opt3")
	public Boolean getIsEventType2Opt3() {
		return isEventType2Opt3;
	}
	public void setIsEventType2Opt3(Boolean isEventType2Opt3) {
		this.isEventType2Opt3 = isEventType2Opt3;
	}
	
	@Column(name = "IsEventType2Opt4")
	public Boolean getIsEventType2Opt4() {
		return isEventType2Opt4;
	}
	public void setIsEventType2Opt4(Boolean isEventType2Opt4) {
		this.isEventType2Opt4 = isEventType2Opt4;
	}
	
	@Column(name = "IsEventType2Opt5")
	public Boolean getIsEventType2Opt5() {
		return isEventType2Opt5;
	}
	public void setIsEventType2Opt5(Boolean isEventType2Opt5) {
		this.isEventType2Opt5 = isEventType2Opt5;
	}
	
	@Column(name = "IsEventType3Opt1")
	public Boolean getIsEventType3Opt1() {
		return isEventType3Opt1;
	}
	public void setIsEventType3Opt1(Boolean isEventType3Opt1) {
		this.isEventType3Opt1 = isEventType3Opt1;
	}
	
	@Column(name = "IsEventType3Opt2")
	public Boolean getIsEventType3Opt2() {
		return isEventType3Opt2;
	}
	public void setIsEventType3Opt2(Boolean isEventType3Opt2) {
		this.isEventType3Opt2 = isEventType3Opt2;
	}

	@Column(name = "IsEventType4Opt1")
	public Boolean getIsEventType4Opt1() {
		return isEventType4Opt1;
	}
	public void setIsEventType4Opt1(Boolean isEventType4Opt1) {
		this.isEventType4Opt1 = isEventType4Opt1;
	}
	
	@Column(name = "IsEventType4Opt2")
	public Boolean getIsEventType4Opt2() {
		return isEventType4Opt2;
	}
	public void setIsEventType4Opt2(Boolean isEventType4Opt2) {
		this.isEventType4Opt2 = isEventType4Opt2;
	}
	
	@Column(name = "IsEventType4Opt3")
	public Boolean getIsEventType4Opt3() {
		return isEventType4Opt3;
	}
	public void setIsEventType4Opt3(Boolean isEventType4Opt3) {
		this.isEventType4Opt3 = isEventType4Opt3;
	}
	
	@Column(name = "IsEventType4Opt4")
	public Boolean getIsEventType4Opt4() {
		return isEventType4Opt4;
	}
	public void setIsEventType4Opt4(Boolean isEventType4Opt4) {
		this.isEventType4Opt4 = isEventType4Opt4;
	}

	@Column(name = "EventType5Other")
	public String getEventType5Other() {
		return eventType5Other;
	}
	public void setEventType5Other(String eventType5Other) {
		this.eventType5Other = eventType5Other;
	}
	
	@Column(name = "EventRemark")
	public String getEventRemark() {
		return eventRemark;
	}
	public void setEventRemark(String eventRemark) {
		this.eventRemark = eventRemark;
	}
	
	@Column(name = "IsEventCause1Opt1")
	public Boolean getIsEventCause1Opt1() {
		return isEventCause1Opt1;
	}
	public void setIsEventCause1Opt1(Boolean isEventCause1Opt1) {
		this.isEventCause1Opt1 = isEventCause1Opt1;
	}

	@Column(name = "IsEventCause1Opt2")
	public Boolean getIsEventCause1Opt2() {
		return isEventCause1Opt2;
	}
	public void setIsEventCause1Opt2(Boolean isEventCause1Opt2) {
		this.isEventCause1Opt2 = isEventCause1Opt2;
	}

	@Column(name = "IsEventCause1Opt3")
	public Boolean getIsEventCause1Opt3() {
		return isEventCause1Opt3;
	}
	public void setIsEventCause1Opt3(Boolean isEventCause1Opt3) {
		this.isEventCause1Opt3 = isEventCause1Opt3;
	}

	@Column(name = "IsEventCause1Opt4")
	public Boolean getIsEventCause1Opt4() {
		return isEventCause1Opt4;
	}
	public void setIsEventCause1Opt4(Boolean isEventCause1Opt4) {
		this.isEventCause1Opt4 = isEventCause1Opt4;
	}

	@Column(name = "IsEventCause1Opt5")
	public Boolean getIsEventCause1Opt5() {
		return isEventCause1Opt5;
	}
	public void setIsEventCause1Opt5(Boolean isEventCause1Opt5) {
		this.isEventCause1Opt5 = isEventCause1Opt5;
	}

	@Column(name = "IsEventCause1Opt6")
	public Boolean getIsEventCause1Opt6() {
		return isEventCause1Opt6;
	}
	public void setIsEventCause1Opt6(Boolean isEventCause1Opt6) {
		this.isEventCause1Opt6 = isEventCause1Opt6;
	}

	@Column(name = "IsEventCause1Opt6Other")
	public String getIsEventCause1Opt6Other() {
		return isEventCause1Opt6Other;
	}
	public void setIsEventCause1Opt6Other(String isEventCause1Opt6Other) {
		this.isEventCause1Opt6Other = isEventCause1Opt6Other;
	}
	
	@Column(name = "EventEvaluation")
	public String getEventEvaluation() {
		return eventEvaluation;
	}
	public void setEventEvaluation(String eventEvaluation) {
		this.eventEvaluation = eventEvaluation;
	}

	@Column(name = "EventProcess")
	public String getEventProcess() {
		return eventProcess;
	}
	public void setEventProcess(String eventProcess) {
		this.eventProcess = eventProcess;
	}

	@Column(name = "IsSecuritySetting1Opt1")
	public Boolean getIsSecuritySetting1Opt1() {
		return isSecuritySetting1Opt1;
	}
	public void setIsSecuritySetting1Opt1(Boolean isSecuritySetting1Opt1) {
		this.isSecuritySetting1Opt1 = isSecuritySetting1Opt1;
	}

	@Column(name = "IsSecuritySetting1Opt2")
	public Boolean getIsSecuritySetting1Opt2() {
		return isSecuritySetting1Opt2;
	}
	public void setIsSecuritySetting1Opt2(Boolean isSecuritySetting1Opt2) {
		this.isSecuritySetting1Opt2 = isSecuritySetting1Opt2;
	}

	@Column(name = "IsSecuritySetting1Opt3")
	public Boolean getIsSecuritySetting1Opt3() {
		return isSecuritySetting1Opt3;
	}
	public void setIsSecuritySetting1Opt3(Boolean isSecuritySetting1Opt3) {
		this.isSecuritySetting1Opt3 = isSecuritySetting1Opt3;
	}

	@Column(name = "IsSecuritySetting1Opt4")
	public Boolean getIsSecuritySetting1Opt4() {
		return isSecuritySetting1Opt4;
	}
	public void setIsSecuritySetting1Opt4(Boolean isSecuritySetting1Opt4) {
		this.isSecuritySetting1Opt4 = isSecuritySetting1Opt4;
	}

	@Column(name = "IsSecuritySetting1Opt5")
	public Boolean getIsSecuritySetting1Opt5() {
		return isSecuritySetting1Opt5;
	}
	public void setIsSecuritySetting1Opt5(Boolean isSecuritySetting1Opt5) {
		this.isSecuritySetting1Opt5 = isSecuritySetting1Opt5;
	}

	@Column(name = "IsSecuritySetting1Opt6")
	public Boolean getIsSecuritySetting1Opt6() {
		return isSecuritySetting1Opt6;
	}
	public void setIsSecuritySetting1Opt6(Boolean isSecuritySetting1Opt6) {
		this.isSecuritySetting1Opt6 = isSecuritySetting1Opt6;
	}

	@Column(name = "IsSecuritySetting1Opt7")
	public Boolean getIsSecuritySetting1Opt7() {
		return isSecuritySetting1Opt7;
	}
	public void setIsSecuritySetting1Opt7(Boolean isSecuritySetting1Opt7) {
		this.isSecuritySetting1Opt7 = isSecuritySetting1Opt7;
	}

	@Column(name = "IsSecuritySetting1Opt8")
	public Boolean getIsSecuritySetting1Opt8() {
		return isSecuritySetting1Opt8;
	}
	public void setIsSecuritySetting1Opt8(Boolean isSecuritySetting1Opt8) {
		this.isSecuritySetting1Opt8 = isSecuritySetting1Opt8;
	}

	@Column(name = "IsSecuritySetting1Opt9")
	public Boolean getIsSecuritySetting1Opt9() {
		return isSecuritySetting1Opt9;
	}
	public void setIsSecuritySetting1Opt9(Boolean isSecuritySetting1Opt9) {
		this.isSecuritySetting1Opt9 = isSecuritySetting1Opt9;
	}

	@Column(name = "IsSecuritySetting1Opt10")
	public Boolean getIsSecuritySetting1Opt10() {
		return isSecuritySetting1Opt10;
	}
	public void setIsSecuritySetting1Opt10(Boolean isSecuritySetting1Opt10) {
		this.isSecuritySetting1Opt10 = isSecuritySetting1Opt10;
	}

	@Column(name = "IsSecuritySetting1Opt11")
	public Boolean getIsSecuritySetting1Opt11() {
		return isSecuritySetting1Opt11;
	}
	public void setIsSecuritySetting1Opt11(Boolean isSecuritySetting1Opt11) {
		this.isSecuritySetting1Opt11 = isSecuritySetting1Opt11;
	}

	@Column(name = "IsSecuritySetting1Opt12")
	public Boolean getIsSecuritySetting1Opt12() {
		return isSecuritySetting1Opt12;
	}
	public void setIsSecuritySetting1Opt12(Boolean isSecuritySetting1Opt12) {
		this.isSecuritySetting1Opt12 = isSecuritySetting1Opt12;
	}

	@Column(name = "IsSecuritySetting1Opt13")
	public Boolean getIsSecuritySetting1Opt13() {
		return isSecuritySetting1Opt13;
	}
	public void setIsSecuritySetting1Opt13(Boolean isSecuritySetting1Opt13) {
		this.isSecuritySetting1Opt13 = isSecuritySetting1Opt13;
	}

	@Column(name = "FinishDoOther")
	public String getFinishDoOther() {
		return finishDoOther;
	}
	public void setFinishDoOther(String finishDoOther) {
		this.finishDoOther = finishDoOther;
	}
	
	@Column(name = "FinishDateTime")
	public Date getFinishDateTime() {
		return finishDateTime;
	}
	public void setFinishDateTime(Date finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	
	@Column(name = "CreateId")
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ModifyId", nullable = false)
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "ModifyTime", nullable = false)
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "Status")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "SubStatus")
	public Long getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(Long subStatus) {
		this.subStatus = subStatus;
	}

	@Column(name = "NotificationId")
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name = "NotificationMainUnit1")
	public Long getNotificationMainUnit1() {
		return notificationMainUnit1;
	}
	public void setNotificationMainUnit1(Long notificationMainUnit1) {
		this.notificationMainUnit1 = notificationMainUnit1;
	}

	@Column(name = "NotificationMainUnit2")
	public Long getNotificationMainUnit2() {
		return notificationMainUnit2;
	}
	public void setNotificationMainUnit2(Long notificationMainUnit2) {
		this.notificationMainUnit2 = notificationMainUnit2;
	}

	@Column(name = "NotificationIsCC3")
	public Boolean getNotificationIsCC3() {
		return notificationIsCC3;
	}
	public void setNotificationIsCC3(Boolean notificationIsCC3) {
		this.notificationIsCC3 = notificationIsCC3;
	}
	
	
	@Column(name = "SatisfactionTime")
	public Integer getSatisfactionTime() {
		return satisfactionTime;
	}
	public void setSatisfactionTime(Integer satisfactionTime) {
		this.satisfactionTime = satisfactionTime;
	}
	
	@Column(name = "SatisfactionProfessionalism")
	public Integer getSatisfactionProfessionalism() {
		return satisfactionProfessionalism;
	}
	public void setSatisfactionProfessionalism(Integer satisfactionProfessionalism) {
		this.satisfactionProfessionalism = satisfactionProfessionalism;
	}
	
	@Column(name = "SatisfactionService")
	public Integer getSatisfactionService() {
		return satisfactionService;
	}
	public void setSatisfactionService(Integer satisfactionService) {
		this.satisfactionService = satisfactionService;
	}
	
	@Column(name = "SatisfactionReport")
	public Integer getSatisfactionReport() {
		return satisfactionReport;
	}
	public void setSatisfactionReport(Integer satisfactionReport) {
		this.satisfactionReport = satisfactionReport;
	}
	
	@Column(name = "SatisfactionTotal")
	public Integer getSatisfactionTotal() {
		return satisfactionTotal;
	}
	public void setSatisfactionTotal(Integer satisfactionTotal) {
		this.satisfactionTotal = satisfactionTotal;
	}
	
	@Column(name = "SatisfactionRemark")
	public String getSatisfactionRemark() {
		return satisfactionRemark;
	}
	public void setSatisfactionRemark(String satisfactionRemark) {
		this.satisfactionRemark = satisfactionRemark;
	}

}
