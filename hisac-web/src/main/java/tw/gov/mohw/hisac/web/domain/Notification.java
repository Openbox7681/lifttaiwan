package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notification", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Notification {
	private String id;
	private Date applyDateTime;
	private String postId;
	private Long contactorUnit;
	private Long mainUnit1;
	private Long mainUnit2;
	private Long contactorId;
	private String contactorTel;
	private String contactorFax;
	private String contactorEmail;
	private Boolean isSub;
	private String isSubUnitName;
	private Date eventDateTime;
	private Integer hostAmount;
	private Integer serverAmount;
	private Integer informationAmount;
	private Integer otherDeviceAmount;
	private String otherDeviceName;
	private String deviceRemark;
	private String assessDamage;
	private String assessDamageRemark;
	private String ipExternal;
	private String ipInternal;
	private String webUrl;
	private Boolean isOsOpt1;
	private Boolean isOsOpt2;
	private Boolean isOsOpt3;
	private String isOsOpt3Other;
	private Boolean isGuardOpt1;
	private Boolean isGuardOpt2;
	private Boolean isGuardOpt3;
	private Boolean isGuardOpt4;
	private String isGuardOpt4Other;
	private Integer socOpt;
	private String socOptCompany;
	private Boolean isIsms;
	private String maintainCompany;
	private Integer ceffectLevel;
	private Integer ieffectLevel;
	private Integer aeffectLevel;
	private Integer effectLevel;
	private Integer eventType;
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
	private Boolean isAffectOthers;
	private Boolean affectOther1;
	private Boolean affectOther2;
	private Boolean affectOther3;
	private Boolean affectOther4;
	private Boolean affectOther5;
	private Boolean affectOther6;
	private Boolean affectOther7;
	private Boolean affectOther8;
	private Integer eventSource;
	private String eventSourceNo;
	private String eventSourceOther;
	private String resDescription;
	private Date resControlTime;
	private Integer resResult;
	private Boolean isRes1LogOpt1;
	private Boolean isRes1LogOpt2;
	private Boolean isRes1LogOpt5;
	private Boolean isRes1LogOpt3;
	private Boolean isRes1LogOpt4;
	private Integer res1LogOpt1;
	private String res1LogOpt1Other;
	private Integer res1LogOpt2;
	private String res1LogOpt2Other;
	private Integer res1LogOpt5;
	private String res1LogOpt5Other;
	private Integer res1LogOpt3Amount;
	private String res1LogOpt4Remark;
	private Boolean isRes1EvaOpt1;
	private Boolean isRes1EvaOpt2;
	private Boolean isRes1EvaOpt3;
	private Boolean isRes1EvaOpt4;
	private Boolean isRes1EvaOpt5;
	private Boolean isRes1EvaOpt6;
	private Boolean isRes1EvaOpt7;
	private Boolean isRes1EvaOpt8;
	private String res1EvaOpt1Remark;
	private String res1EvaOpt2Remark;
	private String res1EvaOpt3Remark;
	private String res1EvaOpt4Remark;
	private Integer res1EvaOpt6Amount;
	private String res1EvaOpt6Remark;
	private Integer res1EvaOpt6Type;
	private String res1EvaOpt6TypeOpt3Other;
	private Integer res1EvaOpt7Amount;
	private String res1EvaOpt7Remark;
	private String res1EvaOpt8Remark;
	private Boolean isRes1DoOpt1;
	private Boolean isRes1DoOpt2;
	private Boolean isRes1DoOpt3;
	private Boolean isRes1DoOpt4;
	private Boolean isRes1DoOpt5;
	private Boolean isRes1DoOpt6;
	private Boolean isRes1DoOpt7;
	private Boolean isRes1DoOpt8;
	private Boolean isRes1DoOpt9;
	private Boolean isRes1DoOpt10;
	private Boolean isRes1DoOpt11;
	private Boolean isRes1DoOpt12;
	private Integer res1DoOpt1Amount;
	private String res1DoOpt2Remark;
	private String res1DoOpt3Remark;
	private String res1DoOpt4Remark;
	private Integer res1DoOpt5Amount;
	private Boolean isRes1DoOpt9EngineOpt1;
	private Boolean isRes1DoOpt9EngineOpt2;
	private Boolean isRes1DoOpt9EngineOpt3;
	private Boolean isRes1DoOpt9EngineOpt4;
	private Boolean isRes1DoOpt9EngineOpt5;
	private Boolean isRes1DoOpt9EngineOpt6;
	private String res1DoOpt9EngineOpt6Other;
	private Date res1DoOpt10Date;
	private Date res1DoOpt11Date;
	private String res1DoOpt12Remark;
	private Boolean isRes2LogOpt1;
	private Boolean isRes2LogOpt2;
	private Boolean isRes2LogOpt3;
	private Boolean isRes2LogOpt4;
	private Integer res2LogOpt1;
	private String res2LogOpt1Other;
	private Integer res2LogOpt2;
	private String res2LogOpt2Other;
	private Integer res2LogOpt3Amount;
	private String res2LogOpt4Remark;
	private Boolean isRes2EvaOpt1;
	private Boolean isRes2EvaOpt2;
	private Boolean isRes2EvaOpt3;
	private Boolean isRes2EvaOpt4;
	private Boolean isRes2EvaOpt5;
	private String res2EvaOpt1Remark;
	private String res2EvaOpt2Remark;
	private String res2EvaOpt3Remark;
	private String res2EvaOpt4Remark;
	private String res2EvaOpt5Remark;
	private Boolean isRes2DoOpt1;
	private Boolean isRes2DoOpt2;
	private Boolean isRes2DoOpt3;
	private Boolean isRes2DoOpt4;
	private Boolean isRes2DoOpt5;
	private Boolean isRes2DoOpt6;
	private Boolean isRes2DoOpt7;
	private Integer res2DoOpt1Amount;
	private String res2DoOpt1Remark;
	private String res2DoOpt2Remark;
	private String res2DoOpt3Remark;
	private Date res2DoOpt5Date;
	private Integer res2DoOpt6Amount;
	private String res2DoOpt7Remark;
	private Boolean isRes3LogOpt1;
	private Boolean isRes3LogOpt2;
	private Boolean isRes3LogOpt3;
	private Boolean isRes3LogOpt4;
	private Integer res3LogOpt1;
	private String res3LogOpt1Other;
	private Integer res3LogOpt2;
	private String res3LogOpt2Other;
	private Integer res3LogOpt3Amount;
	private String res3LogOpt4Remark;
	private Boolean isRes3EvaOpt1;
	private Boolean isRes3EvaOpt2;
	private Boolean isRes3EvaOpt3;
	private Integer res3EvaOpt1Amount;
	private String res3EvaOpt2Remark;
	private String res3EvaOpt3Remark;
	private Boolean isRes3DoOpt1;
	private Boolean isRes3DoOpt2;
	private Boolean isRes3DoOpt3;
	private Boolean isRes3DoOpt4;
	private String res3DoOpt1Remark;
	private String res3DoOpt3Remark;
	private String res3DoOpt4Remark;
	private Boolean isRes4LogOpt1;
	private String res4LogOpt1Remark;
	private Boolean isRes4EvaOpt1;
	private Boolean isRes4EvaOpt2;
	private Boolean isRes4EvaOpt3;
	private Integer res4EvaOpt1;
	private Integer res4EvaOpt1Amount;
	private String res4EvaOpt2Remark;
	private String res4EvaOpt3Remark;
	private Boolean isRes4DoOpt1;
	private Boolean isRes4DoOpt2;
	private Boolean isRes4DoOpt4;
	private Boolean isRes4DoOpt3;
	private String res4DoOpt3Remark;
	private Boolean isRes5LogOpt1;
	private Boolean isRes5LogOpt2;
	private Boolean isRes5LogOpt3;
	private Boolean isRes5LogOpt4;
	private Integer res5LogOpt1;
	private String res5LogOpt1Other;
	private Integer res5LogOpt2;
	private String res5LogOpt2Other;
	private Integer res5LogOpt3Amount;
	private String res5LogOpt4Remark;
	private Boolean isRes5EvaOpt1;
	private Boolean isRes5EvaOpt2;
	private Boolean isRes5EvaOpt3;
	private Boolean isRes5EvaOpt4;
	private Boolean isRes5EvaOpt5;
	private String res5EvaOpt1Remark;
	private String res5EvaOpt2Remark;
	private String res5EvaOpt3Remark;
	private String res5EvaOpt4Remark;
	private String res5EvaOpt5Remark;
	private Boolean isRes5DoOpt1;
	private Boolean isRes5DoOpt2;
	private Boolean isRes5DoOpt3;
	private Boolean isRes5DoOpt4;
	private Boolean isRes5DoOpt5;
	private Boolean isRes5DoOpt6;
	private Boolean isRes5DoOpt7;
	private Integer res5DoOpt1Amount;
	private String res5DoOpt1Remark;
	private String res5DoOpt2Remark;
	private String res5DoOpt3Remark;
	private Date res5DoOpt5Date;
	private Integer res5DoOpt6Amount;
	private String res5DoOpt7Remark;
	private Boolean isNeedSupport;
	private String needSupportRemark;
	private Integer finish1Reason;
	private String finish1ReasonOther;
	private String finish1ReasonToDo;
	private Boolean isFinish1DoSysOpt1;
	private Boolean isFinish1DoSysOpt2;
	private Boolean isFinish1DoSysOpt3;
	private Boolean isFinish1DoSysOpt4;
	private Boolean isFinish1DoSysOpt5;
	private Boolean isFinish1DoSysOpt6;
	private Boolean isFinish1DoSysOpt7;
	private Boolean isFinish1DoSysOpt8;
	private Boolean isFinish1DoSysOpt9;
	private Boolean isFinish1DoSysOpt10;
	private String finish1DoSysOpt3Remark;
	private String finish1DoSysOpt6Remark;
	private String finish1DoSysOpt7Remark;
	private Boolean isFinish1DoEduOpt1;
	private Boolean isFinish1DoEduOpt2;
	private Boolean isFinish1DoEduOpt3;
	private Boolean isFinish1DoEduOpt4;
	private Integer finish2Reason;
	private String finish2ReasonOther;
	private String finish2ReasonRemark;
	private Boolean isFinish2DoSysOpt1;
	private Boolean isFinish2DoSysOpt2;
	private Boolean isFinish2DoSysOpt3;
	private Boolean isFinish2DoSysOpt4;
	private Boolean isFinish2DoSysOpt5;
	private String finish2DoSysOpt1Remark;
	private Boolean isFinish2DoEduOpt1;
	private Boolean isFinish2DoEduOpt2;
	private Boolean isFinish2DoEduOpt3;
	private Boolean isFinish2DoEduOpt4;
	private Boolean isFinish3DoSysOpt1;
	private Boolean isFinish3DoSysOpt2;
	private Boolean isFinish3DoSysOpt3;
	private Boolean isFinish3DoSysOpt4;
	private String finish3DoSysOpt3Remark;
	private String finish3DoSysOpt4Remark;
	private Boolean isFinish3DoEduOpt1;
	private Boolean isFinish3DoEduOpt2;
	private Integer finish4Reason;
	private String finish4ReasonOther;
	private String finish4ReasonRemark;
	private Boolean isFinish4DoSysOpt1;
	private Boolean isFinish4DoEduOpt1;
	private Boolean isFinish4DoEduOpt2;
	private Boolean isFinish4DoEduOpt3;
	private Boolean isFinish4DoEduOpt4;
	private Integer finish5Reason;
	private String finish5ReasonOther;
	private String finish5ReasonRemark;
	private Boolean isFinish5DoSysOpt1;
	private Boolean isFinish5DoSysOpt2;
	private Boolean isFinish5DoSysOpt3;
	private Boolean isFinish5DoSysOpt4;
	private String finish5DoSysOpt1Remark;
	private Boolean isFinish5DoEduOpt1;
	private Boolean isFinish5DoEduOpt2;
	private Boolean isFinish5DoEduOpt3;
	private Boolean isFinish5DoEduOpt4;
	private String finishDoOther;
	private Integer status;
	private Date finishDateTime;
	private Date examineDateTime;
	private Date realFinishDateTime;
	private String messageId;
	private String messagePostId;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;
	private Boolean isCC3;
	private Boolean isCC5;
	private Boolean isReview3;
	private Boolean isReview5;
	private Integer transferInType;
	private String transferInId;
	private Integer transferOutType;
	private String transferOutId;
	private Long incidentId;
	private Long certId;
	
	private Boolean isFinishDoSysOptRemark;
	private Boolean isFinishDoEduOptRemark;
	private String finishDoSysOptRemark;
	private String finishDoEduOptRemark;
	
	private Boolean isHandledByMyself;
	
	private Long handleInformationId;
	
	private Boolean isTest;

	@Id
	@Column(name = "Id", nullable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "ApplyDateTime")
	public Date getApplyDateTime() {
		return applyDateTime;
	}
	public void setApplyDateTime(Date applyDateTime) {
		this.applyDateTime = applyDateTime;
	}
	@Column(name = "EventDateTime")
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	@Column(name = "ContactorUnit")
	public Long getContactorUnit() {
		return contactorUnit;
	}
	public void setContactorUnit(Long contactorUnit) {
		this.contactorUnit = contactorUnit;
	}
	@Column(name = "MainUnit1")
	public Long getMainUnit1() {
		return mainUnit1;
	}
	public void setMainUnit1(Long mainUnit1) {
		this.mainUnit1 = mainUnit1;
	}
	@Column(name = "MainUnit2")
	public Long getMainUnit2() {
		return mainUnit2;
	}
	public void setMainUnit2(Long mainUnit2) {
		this.mainUnit2 = mainUnit2;
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
	@Column(name = "IsSub")
	public Boolean getIsSub() {
		return isSub;
	}
	public void setIsSub(Boolean isSub) {
		this.isSub = isSub;
	}
	@Column(name = "IsSubUnitName")
	public String getIsSubUnitName() {
		return isSubUnitName;
	}
	public void setIsSubUnitName(String isSubUnitName) {
		this.isSubUnitName = isSubUnitName;
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
	@Column(name = "IpExternal")
	public String getIpExternal() {
		return ipExternal;
	}
	public void setIpExternal(String ipExternal) {
		this.ipExternal = ipExternal;
	}
	@Column(name = "IpInternal")
	public String getIpInternal() {
		return ipInternal;
	}
	public void setIpInternal(String ipInternal) {
		this.ipInternal = ipInternal;
	}
	@Column(name = "WebUrl")
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
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
	@Column(name = "IsGuardOpt1")
	public Boolean getIsGuardOpt1() {
		return isGuardOpt1;
	}
	public void setIsGuardOpt1(Boolean isGuardOpt1) {
		this.isGuardOpt1 = isGuardOpt1;
	}
	@Column(name = "IsGuardOpt2")
	public Boolean getIsGuardOpt2() {
		return isGuardOpt2;
	}
	public void setIsGuardOpt2(Boolean isGuardOpt2) {
		this.isGuardOpt2 = isGuardOpt2;
	}
	@Column(name = "IsGuardOpt3")
	public Boolean getIsGuardOpt3() {
		return isGuardOpt3;
	}
	public void setIsGuardOpt3(Boolean isGuardOpt3) {
		this.isGuardOpt3 = isGuardOpt3;
	}
	@Column(name = "IsGuardOpt4")
	public Boolean getIsGuardOpt4() {
		return isGuardOpt4;
	}
	public void setIsGuardOpt4(Boolean isGuardOpt4) {
		this.isGuardOpt4 = isGuardOpt4;
	}
	@Column(name = "IsGuardOpt4Other")
	public String getIsGuardOpt4Other() {
		return isGuardOpt4Other;
	}
	public void setIsGuardOpt4Other(String isGuardOpt4Other) {
		this.isGuardOpt4Other = isGuardOpt4Other;
	}
	@Column(name = "SocOpt")
	public Integer getSocOpt() {
		return socOpt;
	}
	public void setSocOpt(Integer socOpt) {
		this.socOpt = socOpt;
	}
	@Column(name = "SocOptCompany")
	public String getSocOptCompany() {
		return socOptCompany;
	}
	public void setSocOptCompany(String socOptCompany) {
		this.socOptCompany = socOptCompany;
	}
	@Column(name = "IsIsms")
	public Boolean getIsIsms() {
		return isIsms;
	}
	public void setIsIsms(Boolean isIsms) {
		this.isIsms = isIsms;
	}
	@Column(name = "MaintainCompany")
	public String getMaintainCompany() {
		return maintainCompany;
	}
	public void setMaintainCompany(String maintainCompany) {
		this.maintainCompany = maintainCompany;
	}
	@Column(name = "CeffectLevel")
	public Integer getCeffectLevel() {
		return ceffectLevel;
	}
	public void setCeffectLevel(Integer ceffectLevel) {
		this.ceffectLevel = ceffectLevel;
	}
	@Column(name = "IeffectLevel")
	public Integer getIeffectLevel() {
		return ieffectLevel;
	}
	public void setIeffectLevel(Integer ieffectLevel) {
		this.ieffectLevel = ieffectLevel;
	}
	@Column(name = "AeffectLevel")
	public Integer getAeffectLevel() {
		return aeffectLevel;
	}
	public void setAeffectLevel(Integer aeffectLevel) {
		this.aeffectLevel = aeffectLevel;
	}
	@Column(name = "EffectLevel")
	public Integer getEffectLevel() {
		return effectLevel;
	}
	public void setEffectLevel(Integer effectLevel) {
		this.effectLevel = effectLevel;
	}
	@Column(name = "EventType")
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
	// @Column(name = "IsEventType3Opt3")
	// public Boolean getIsEventType3Opt3() {
	// return isEventType3Opt3;
	// }
	// public void setIsEventType3Opt3(Boolean isEventType3Opt3) {
	// this.isEventType3Opt3 = isEventType3Opt3;
	// }
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
	@Column(name = "IsAffectOthers")
	public Boolean getIsAffectOthers() {
		return isAffectOthers;
	}
	public void setIsAffectOthers(Boolean isAffectOthers) {
		this.isAffectOthers = isAffectOthers;
	}
	@Column(name = "AffectOther1")
	public Boolean getAffectOther1() {
		return affectOther1;
	}
	public void setAffectOther1(Boolean affectOther1) {
		this.affectOther1 = affectOther1;
	}
	@Column(name = "AffectOther2")
	public Boolean getAffectOther2() {
		return affectOther2;
	}
	public void setAffectOther2(Boolean affectOther2) {
		this.affectOther2 = affectOther2;
	}
	@Column(name = "AffectOther3")
	public Boolean getAffectOther3() {
		return affectOther3;
	}
	public void setAffectOther3(Boolean affectOther3) {
		this.affectOther3 = affectOther3;
	}
	@Column(name = "AffectOther4")
	public Boolean getAffectOther4() {
		return affectOther4;
	}
	public void setAffectOther4(Boolean affectOther4) {
		this.affectOther4 = affectOther4;
	}
	@Column(name = "AffectOther5")
	public Boolean getAffectOther5() {
		return affectOther5;
	}
	public void setAffectOther5(Boolean affectOther5) {
		this.affectOther5 = affectOther5;
	}
	@Column(name = "AffectOther6")
	public Boolean getAffectOther6() {
		return affectOther6;
	}
	public void setAffectOther6(Boolean affectOther6) {
		this.affectOther6 = affectOther6;
	}
	@Column(name = "AffectOther7")
	public Boolean getAffectOther7() {
		return affectOther7;
	}
	public void setAffectOther7(Boolean affectOther7) {
		this.affectOther7 = affectOther7;
	}
	@Column(name = "AffectOther8")
	public Boolean getAffectOther8() {
		return affectOther8;
	}
	public void setAffectOther8(Boolean affectOther8) {
		this.affectOther8 = affectOther8;
	}
	@Column(name = "EventSource")
	public Integer getEventSource() {
		return eventSource;
	}
	public void setEventSource(Integer eventSource) {
		this.eventSource = eventSource;
	}
	@Column(name = "EventSourceNo")
	public String getEventSourceNo() {
		return eventSourceNo;
	}
	public void setEventSourceNo(String eventSourceNo) {
		this.eventSourceNo = eventSourceNo;
	}
	@Column(name = "EventSourceOther")
	public String getEventSourceOther() {
		return eventSourceOther;
	}
	public void setEventSourceOther(String eventSourceOther) {
		this.eventSourceOther = eventSourceOther;
	}
	@Column(name = "ResDescription")
	public String getResDescription() {
		return resDescription;
	}
	public void setResDescription(String resDescription) {
		this.resDescription = resDescription;
	}
	@Column(name = "ResControlTime")
	public Date getResControlTime() {
		return resControlTime;
	}
	public void setResControlTime(Date resControlTime) {
		this.resControlTime = resControlTime;
	}
	@Column(name = "ResResult")
	public Integer getResResult() {
		return resResult;
	}
	public void setResResult(Integer resResult) {
		this.resResult = resResult;
	}
	@Column(name = "IsRes1LogOpt1")
	public Boolean getIsRes1LogOpt1() {
		return isRes1LogOpt1;
	}
	public void setIsRes1LogOpt1(Boolean isRes1LogOpt1) {
		this.isRes1LogOpt1 = isRes1LogOpt1;
	}
	@Column(name = "IsRes1LogOpt2")
	public Boolean getIsRes1LogOpt2() {
		return isRes1LogOpt2;
	}
	public void setIsRes1LogOpt2(Boolean isRes1LogOpt2) {
		this.isRes1LogOpt2 = isRes1LogOpt2;
	}
	@Column(name = "IsRes1LogOpt5")
	public Boolean getIsRes1LogOpt5() {
		return isRes1LogOpt5;
	}
	public void setIsRes1LogOpt5(Boolean isRes1LogOpt5) {
		this.isRes1LogOpt5 = isRes1LogOpt5;
	}
	@Column(name = "IsRes1LogOpt3")
	public Boolean getIsRes1LogOpt3() {
		return isRes1LogOpt3;
	}
	public void setIsRes1LogOpt3(Boolean isRes1LogOpt3) {
		this.isRes1LogOpt3 = isRes1LogOpt3;
	}
	@Column(name = "IsRes1LogOpt4")
	public Boolean getIsRes1LogOpt4() {
		return isRes1LogOpt4;
	}
	public void setIsRes1LogOpt4(Boolean isRes1LogOpt4) {
		this.isRes1LogOpt4 = isRes1LogOpt4;
	}
	@Column(name = "Res1LogOpt1")
	public Integer getRes1LogOpt1() {
		return res1LogOpt1;
	}
	public void setRes1LogOpt1(Integer res1LogOpt1) {
		this.res1LogOpt1 = res1LogOpt1;
	}
	@Column(name = "Res1LogOpt1Other")
	public String getRes1LogOpt1Other() {
		return res1LogOpt1Other;
	}
	public void setRes1LogOpt1Other(String res1LogOpt1Other) {
		this.res1LogOpt1Other = res1LogOpt1Other;
	}
	@Column(name = "Res1LogOpt2")
	public Integer getRes1LogOpt2() {
		return res1LogOpt2;
	}
	public void setRes1LogOpt2(Integer res1LogOpt2) {
		this.res1LogOpt2 = res1LogOpt2;
	}
	@Column(name = "Res1LogOpt2Other")
	public String getRes1LogOpt2Other() {
		return res1LogOpt2Other;
	}
	public void setRes1LogOpt2Other(String res1LogOpt2Other) {
		this.res1LogOpt2Other = res1LogOpt2Other;
	}
	@Column(name = "Res1LogOpt5")
	public Integer getRes1LogOpt5() {
		return res1LogOpt5;
	}
	public void setRes1LogOpt5(Integer res1LogOpt5) {
		this.res1LogOpt5 = res1LogOpt5;
	}
	@Column(name = "Res1LogOpt5Other")
	public String getRes1LogOpt5Other() {
		return res1LogOpt5Other;
	}
	public void setRes1LogOpt5Other(String res1LogOpt5Other) {
		this.res1LogOpt5Other = res1LogOpt5Other;
	}
	@Column(name = "Res1LogOpt3Amount")
	public Integer getRes1LogOpt3Amount() {
		return res1LogOpt3Amount;
	}
	public void setRes1LogOpt3Amount(Integer res1LogOpt3Amount) {
		this.res1LogOpt3Amount = res1LogOpt3Amount;
	}
	@Column(name = "Res1LogOpt4Remark")
	public String getRes1LogOpt4Remark() {
		return res1LogOpt4Remark;
	}
	public void setRes1LogOpt4Remark(String res1LogOpt4Remark) {
		this.res1LogOpt4Remark = res1LogOpt4Remark;
	}
	@Column(name = "IsRes1EvaOpt1")
	public Boolean getIsRes1EvaOpt1() {
		return isRes1EvaOpt1;
	}
	public void setIsRes1EvaOpt1(Boolean isRes1EvaOpt1) {
		this.isRes1EvaOpt1 = isRes1EvaOpt1;
	}
	@Column(name = "IsRes1EvaOpt2")
	public Boolean getIsRes1EvaOpt2() {
		return isRes1EvaOpt2;
	}
	public void setIsRes1EvaOpt2(Boolean isRes1EvaOpt2) {
		this.isRes1EvaOpt2 = isRes1EvaOpt2;
	}
	@Column(name = "IsRes1EvaOpt3")
	public Boolean getIsRes1EvaOpt3() {
		return isRes1EvaOpt3;
	}
	public void setIsRes1EvaOpt3(Boolean isRes1EvaOpt3) {
		this.isRes1EvaOpt3 = isRes1EvaOpt3;
	}
	@Column(name = "IsRes1EvaOpt4")
	public Boolean getIsRes1EvaOpt4() {
		return isRes1EvaOpt4;
	}
	public void setIsRes1EvaOpt4(Boolean isRes1EvaOpt4) {
		this.isRes1EvaOpt4 = isRes1EvaOpt4;
	}
	@Column(name = "IsRes1EvaOpt5")
	public Boolean getIsRes1EvaOpt5() {
		return isRes1EvaOpt5;
	}
	public void setIsRes1EvaOpt5(Boolean isRes1EvaOpt5) {
		this.isRes1EvaOpt5 = isRes1EvaOpt5;
	}
	@Column(name = "IsRes1EvaOpt6")
	public Boolean getIsRes1EvaOpt6() {
		return isRes1EvaOpt6;
	}
	public void setIsRes1EvaOpt6(Boolean isRes1EvaOpt6) {
		this.isRes1EvaOpt6 = isRes1EvaOpt6;
	}
	@Column(name = "IsRes1EvaOpt7")
	public Boolean getIsRes1EvaOpt7() {
		return isRes1EvaOpt7;
	}
	public void setIsRes1EvaOpt7(Boolean isRes1EvaOpt7) {
		this.isRes1EvaOpt7 = isRes1EvaOpt7;
	}
	@Column(name = "IsRes1EvaOpt8")
	public Boolean getIsRes1EvaOpt8() {
		return isRes1EvaOpt8;
	}
	public void setIsRes1EvaOpt8(Boolean isRes1EvaOpt8) {
		this.isRes1EvaOpt8 = isRes1EvaOpt8;
	}
	@Column(name = "Res1EvaOpt1Remark")
	public String getRes1EvaOpt1Remark() {
		return res1EvaOpt1Remark;
	}
	public void setRes1EvaOpt1Remark(String res1EvaOpt1Remark) {
		this.res1EvaOpt1Remark = res1EvaOpt1Remark;
	}
	@Column(name = "Res1EvaOpt2Remark")
	public String getRes1EvaOpt2Remark() {
		return res1EvaOpt2Remark;
	}
	public void setRes1EvaOpt2Remark(String res1EvaOpt2Remark) {
		this.res1EvaOpt2Remark = res1EvaOpt2Remark;
	}
	@Column(name = "Res1EvaOpt3Remark")
	public String getRes1EvaOpt3Remark() {
		return res1EvaOpt3Remark;
	}
	public void setRes1EvaOpt3Remark(String res1EvaOpt3Remark) {
		this.res1EvaOpt3Remark = res1EvaOpt3Remark;
	}
	@Column(name = "Res1EvaOpt4Remark")
	public String getRes1EvaOpt4Remark() {
		return res1EvaOpt4Remark;
	}
	public void setRes1EvaOpt4Remark(String res1EvaOpt4Remark) {
		this.res1EvaOpt4Remark = res1EvaOpt4Remark;
	}
	@Column(name = "Res1EvaOpt6Amount")
	public Integer getRes1EvaOpt6Amount() {
		return res1EvaOpt6Amount;
	}
	public void setRes1EvaOpt6Amount(Integer res1EvaOpt6Amount) {
		this.res1EvaOpt6Amount = res1EvaOpt6Amount;
	}
	@Column(name = "Res1EvaOpt6Remark")
	public String getRes1EvaOpt6Remark() {
		return res1EvaOpt6Remark;
	}
	public void setRes1EvaOpt6Remark(String res1EvaOpt6Remark) {
		this.res1EvaOpt6Remark = res1EvaOpt6Remark;
	}
	@Column(name = "Res1EvaOpt6Type")
	public Integer getRes1EvaOpt6Type() {
		return res1EvaOpt6Type;
	}
	public void setRes1EvaOpt6Type(Integer res1EvaOpt6Type) {
		this.res1EvaOpt6Type = res1EvaOpt6Type;
	}
	@Column(name = "Res1EvaOpt6TypeOpt3Other")
	public String getRes1EvaOpt6TypeOpt3Other() {
		return res1EvaOpt6TypeOpt3Other;
	}
	public void setRes1EvaOpt6TypeOpt3Other(String res1EvaOpt6TypeOpt3Other) {
		this.res1EvaOpt6TypeOpt3Other = res1EvaOpt6TypeOpt3Other;
	}
	@Column(name = "Res1EvaOpt7Amount")
	public Integer getRes1EvaOpt7Amount() {
		return res1EvaOpt7Amount;
	}
	public void setRes1EvaOpt7Amount(Integer res1EvaOpt7Amount) {
		this.res1EvaOpt7Amount = res1EvaOpt7Amount;
	}
	@Column(name = "Res1EvaOpt7Remark")
	public String getRes1EvaOpt7Remark() {
		return res1EvaOpt7Remark;
	}
	public void setRes1EvaOpt7Remark(String res1EvaOpt7Remark) {
		this.res1EvaOpt7Remark = res1EvaOpt7Remark;
	}
	@Column(name = "Res1EvaOpt8Remark")
	public String getRes1EvaOpt8Remark() {
		return res1EvaOpt8Remark;
	}
	public void setRes1EvaOpt8Remark(String res1EvaOpt8Remark) {
		this.res1EvaOpt8Remark = res1EvaOpt8Remark;
	}
	@Column(name = "IsRes1DoOpt1")
	public Boolean getIsRes1DoOpt1() {
		return isRes1DoOpt1;
	}
	public void setIsRes1DoOpt1(Boolean isRes1DoOpt1) {
		this.isRes1DoOpt1 = isRes1DoOpt1;
	}
	@Column(name = "IsRes1DoOpt2")
	public Boolean getIsRes1DoOpt2() {
		return isRes1DoOpt2;
	}
	public void setIsRes1DoOpt2(Boolean isRes1DoOpt2) {
		this.isRes1DoOpt2 = isRes1DoOpt2;
	}
	@Column(name = "IsRes1DoOpt3")
	public Boolean getIsRes1DoOpt3() {
		return isRes1DoOpt3;
	}
	public void setIsRes1DoOpt3(Boolean isRes1DoOpt3) {
		this.isRes1DoOpt3 = isRes1DoOpt3;
	}
	@Column(name = "IsRes1DoOpt4")
	public Boolean getIsRes1DoOpt4() {
		return isRes1DoOpt4;
	}
	public void setIsRes1DoOpt4(Boolean isRes1DoOpt4) {
		this.isRes1DoOpt4 = isRes1DoOpt4;
	}
	@Column(name = "IsRes1DoOpt5")
	public Boolean getIsRes1DoOpt5() {
		return isRes1DoOpt5;
	}
	public void setIsRes1DoOpt5(Boolean isRes1DoOpt5) {
		this.isRes1DoOpt5 = isRes1DoOpt5;
	}
	@Column(name = "IsRes1DoOpt6")
	public Boolean getIsRes1DoOpt6() {
		return isRes1DoOpt6;
	}
	public void setIsRes1DoOpt6(Boolean isRes1DoOpt6) {
		this.isRes1DoOpt6 = isRes1DoOpt6;
	}
	@Column(name = "IsRes1DoOpt7")
	public Boolean getIsRes1DoOpt7() {
		return isRes1DoOpt7;
	}
	public void setIsRes1DoOpt7(Boolean isRes1DoOpt7) {
		this.isRes1DoOpt7 = isRes1DoOpt7;
	}
	@Column(name = "IsRes1DoOpt8")
	public Boolean getIsRes1DoOpt8() {
		return isRes1DoOpt8;
	}
	public void setIsRes1DoOpt8(Boolean isRes1DoOpt8) {
		this.isRes1DoOpt8 = isRes1DoOpt8;
	}
	@Column(name = "IsRes1DoOpt9")
	public Boolean getIsRes1DoOpt9() {
		return isRes1DoOpt9;
	}
	public void setIsRes1DoOpt9(Boolean isRes1DoOpt9) {
		this.isRes1DoOpt9 = isRes1DoOpt9;
	}
	@Column(name = "IsRes1DoOpt10")
	public Boolean getIsRes1DoOpt10() {
		return isRes1DoOpt10;
	}
	public void setIsRes1DoOpt10(Boolean isRes1DoOpt10) {
		this.isRes1DoOpt10 = isRes1DoOpt10;
	}
	@Column(name = "IsRes1DoOpt11")
	public Boolean getIsRes1DoOpt11() {
		return isRes1DoOpt11;
	}
	public void setIsRes1DoOpt11(Boolean isRes1DoOpt11) {
		this.isRes1DoOpt11 = isRes1DoOpt11;
	}
	@Column(name = "IsRes1DoOpt12")
	public Boolean getIsRes1DoOpt12() {
		return isRes1DoOpt12;
	}
	public void setIsRes1DoOpt12(Boolean isRes1DoOpt12) {
		this.isRes1DoOpt12 = isRes1DoOpt12;
	}
	@Column(name = "Res1DoOpt1Amount")
	public Integer getRes1DoOpt1Amount() {
		return res1DoOpt1Amount;
	}
	public void setRes1DoOpt1Amount(Integer res1DoOpt1Amount) {
		this.res1DoOpt1Amount = res1DoOpt1Amount;
	}
	@Column(name = "Res1DoOpt2Remark")
	public String getRes1DoOpt2Remark() {
		return res1DoOpt2Remark;
	}
	public void setRes1DoOpt2Remark(String res1DoOpt2Remark) {
		this.res1DoOpt2Remark = res1DoOpt2Remark;
	}
	@Column(name = "Res1DoOpt3Remark")
	public String getRes1DoOpt3Remark() {
		return res1DoOpt3Remark;
	}
	public void setRes1DoOpt3Remark(String res1DoOpt3Remark) {
		this.res1DoOpt3Remark = res1DoOpt3Remark;
	}
	@Column(name = "Res1DoOpt4Remark")
	public String getRes1DoOpt4Remark() {
		return res1DoOpt4Remark;
	}
	public void setRes1DoOpt4Remark(String res1DoOpt4Remark) {
		this.res1DoOpt4Remark = res1DoOpt4Remark;
	}
	@Column(name = "Res1DoOpt5Amount")
	public Integer getRes1DoOpt5Amount() {
		return res1DoOpt5Amount;
	}
	public void setRes1DoOpt5Amount(Integer res1DoOpt5Amount) {
		this.res1DoOpt5Amount = res1DoOpt5Amount;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt1")
	public Boolean getIsRes1DoOpt9EngineOpt1() {
		return isRes1DoOpt9EngineOpt1;
	}
	public void setIsRes1DoOpt9EngineOpt1(Boolean isRes1DoOpt9EngineOpt1) {
		this.isRes1DoOpt9EngineOpt1 = isRes1DoOpt9EngineOpt1;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt2")
	public Boolean getIsRes1DoOpt9EngineOpt2() {
		return isRes1DoOpt9EngineOpt2;
	}
	public void setIsRes1DoOpt9EngineOpt2(Boolean isRes1DoOpt9EngineOpt2) {
		this.isRes1DoOpt9EngineOpt2 = isRes1DoOpt9EngineOpt2;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt3")
	public Boolean getIsRes1DoOpt9EngineOpt3() {
		return isRes1DoOpt9EngineOpt3;
	}
	public void setIsRes1DoOpt9EngineOpt3(Boolean isRes1DoOpt9EngineOpt3) {
		this.isRes1DoOpt9EngineOpt3 = isRes1DoOpt9EngineOpt3;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt4")
	public Boolean getIsRes1DoOpt9EngineOpt4() {
		return isRes1DoOpt9EngineOpt4;
	}
	public void setIsRes1DoOpt9EngineOpt4(Boolean isRes1DoOpt9EngineOpt4) {
		this.isRes1DoOpt9EngineOpt4 = isRes1DoOpt9EngineOpt4;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt5")
	public Boolean getIsRes1DoOpt9EngineOpt5() {
		return isRes1DoOpt9EngineOpt5;
	}
	public void setIsRes1DoOpt9EngineOpt5(Boolean isRes1DoOpt9EngineOpt5) {
		this.isRes1DoOpt9EngineOpt5 = isRes1DoOpt9EngineOpt5;
	}
	@Column(name = "IsRes1DoOpt9EngineOpt6")
	public Boolean getIsRes1DoOpt9EngineOpt6() {
		return isRes1DoOpt9EngineOpt6;
	}
	public void setIsRes1DoOpt9EngineOpt6(Boolean isRes1DoOpt9EngineOpt6) {
		this.isRes1DoOpt9EngineOpt6 = isRes1DoOpt9EngineOpt6;
	}
	@Column(name = "Res1DoOpt9EngineOpt6Other")
	public String getRes1DoOpt9EngineOpt6Other() {
		return res1DoOpt9EngineOpt6Other;
	}
	public void setRes1DoOpt9EngineOpt6Other(String res1DoOpt9EngineOpt6Other) {
		this.res1DoOpt9EngineOpt6Other = res1DoOpt9EngineOpt6Other;
	}
	@Column(name = "Res1DoOpt10Date")
	public Date getRes1DoOpt10Date() {
		return res1DoOpt10Date;
	}
	public void setRes1DoOpt10Date(Date res1DoOpt10Date) {
		this.res1DoOpt10Date = res1DoOpt10Date;
	}
	@Column(name = "Res1DoOpt11Date")
	public Date getRes1DoOpt11Date() {
		return res1DoOpt11Date;
	}
	public void setRes1DoOpt11Date(Date res1DoOpt11Date) {
		this.res1DoOpt11Date = res1DoOpt11Date;
	}
	@Column(name = "Res1DoOpt12Remark")
	public String getRes1DoOpt12Remark() {
		return res1DoOpt12Remark;
	}
	public void setRes1DoOpt12Remark(String res1DoOpt12Remark) {
		this.res1DoOpt12Remark = res1DoOpt12Remark;
	}
	@Column(name = "IsRes2LogOpt1")
	public Boolean getIsRes2LogOpt1() {
		return isRes2LogOpt1;
	}
	public void setIsRes2LogOpt1(Boolean isRes2LogOpt1) {
		this.isRes2LogOpt1 = isRes2LogOpt1;
	}
	@Column(name = "IsRes2LogOpt2")
	public Boolean getIsRes2LogOpt2() {
		return isRes2LogOpt2;
	}
	public void setIsRes2LogOpt2(Boolean isRes2LogOpt2) {
		this.isRes2LogOpt2 = isRes2LogOpt2;
	}
	@Column(name = "IsRes2LogOpt3")
	public Boolean getIsRes2LogOpt3() {
		return isRes2LogOpt3;
	}
	public void setIsRes2LogOpt3(Boolean isRes2LogOpt3) {
		this.isRes2LogOpt3 = isRes2LogOpt3;
	}
	@Column(name = "IsRes2LogOpt4")
	public Boolean getIsRes2LogOpt4() {
		return isRes2LogOpt4;
	}
	public void setIsRes2LogOpt4(Boolean isRes2LogOpt4) {
		this.isRes2LogOpt4 = isRes2LogOpt4;
	}
	@Column(name = "Res2LogOpt1")
	public Integer getRes2LogOpt1() {
		return res2LogOpt1;
	}
	public void setRes2LogOpt1(Integer res2LogOpt1) {
		this.res2LogOpt1 = res2LogOpt1;
	}
	@Column(name = "Res2LogOpt1Other")
	public String getRes2LogOpt1Other() {
		return res2LogOpt1Other;
	}
	public void setRes2LogOpt1Other(String res2LogOpt1Other) {
		this.res2LogOpt1Other = res2LogOpt1Other;
	}
	@Column(name = "Res2LogOpt2")
	public Integer getRes2LogOpt2() {
		return res2LogOpt2;
	}
	public void setRes2LogOpt2(Integer res2LogOpt2) {
		this.res2LogOpt2 = res2LogOpt2;
	}
	@Column(name = "Res2LogOpt2Other")
	public String getRes2LogOpt2Other() {
		return res2LogOpt2Other;
	}
	public void setRes2LogOpt2Other(String res2LogOpt2Other) {
		this.res2LogOpt2Other = res2LogOpt2Other;
	}
	@Column(name = "Res2LogOpt3Amount")
	public Integer getRes2LogOpt3Amount() {
		return res2LogOpt3Amount;
	}
	public void setRes2LogOpt3Amount(Integer res2LogOpt3Amount) {
		this.res2LogOpt3Amount = res2LogOpt3Amount;
	}
	@Column(name = "Res2LogOpt4Remark")
	public String getRes2LogOpt4Remark() {
		return res2LogOpt4Remark;
	}
	public void setRes2LogOpt4Remark(String res2LogOpt4Remark) {
		this.res2LogOpt4Remark = res2LogOpt4Remark;
	}
	@Column(name = "IsRes2EvaOpt1")
	public Boolean getIsRes2EvaOpt1() {
		return isRes2EvaOpt1;
	}
	public void setIsRes2EvaOpt1(Boolean isRes2EvaOpt1) {
		this.isRes2EvaOpt1 = isRes2EvaOpt1;
	}
	@Column(name = "IsRes2EvaOpt2")
	public Boolean getIsRes2EvaOpt2() {
		return isRes2EvaOpt2;
	}
	public void setIsRes2EvaOpt2(Boolean isRes2EvaOpt2) {
		this.isRes2EvaOpt2 = isRes2EvaOpt2;
	}
	@Column(name = "IsRes2EvaOpt3")
	public Boolean getIsRes2EvaOpt3() {
		return isRes2EvaOpt3;
	}
	public void setIsRes2EvaOpt3(Boolean isRes2EvaOpt3) {
		this.isRes2EvaOpt3 = isRes2EvaOpt3;
	}
	@Column(name = "IsRes2EvaOpt4")
	public Boolean getIsRes2EvaOpt4() {
		return isRes2EvaOpt4;
	}
	public void setIsRes2EvaOpt4(Boolean isRes2EvaOpt4) {
		this.isRes2EvaOpt4 = isRes2EvaOpt4;
	}
	@Column(name = "IsRes2EvaOpt5")
	public Boolean getIsRes2EvaOpt5() {
		return isRes2EvaOpt5;
	}
	public void setIsRes2EvaOpt5(Boolean isRes2EvaOpt5) {
		this.isRes2EvaOpt5 = isRes2EvaOpt5;
	}
	@Column(name = "Res2EvaOpt1Remark")
	public String getRes2EvaOpt1Remark() {
		return res2EvaOpt1Remark;
	}
	public void setRes2EvaOpt1Remark(String res2EvaOpt1Remark) {
		this.res2EvaOpt1Remark = res2EvaOpt1Remark;
	}
	@Column(name = "Res2EvaOpt2Remark")
	public String getRes2EvaOpt2Remark() {
		return res2EvaOpt2Remark;
	}
	public void setRes2EvaOpt2Remark(String res2EvaOpt2Remark) {
		this.res2EvaOpt2Remark = res2EvaOpt2Remark;
	}
	@Column(name = "Res2EvaOpt3Remark")
	public String getRes2EvaOpt3Remark() {
		return res2EvaOpt3Remark;
	}
	public void setRes2EvaOpt3Remark(String res2EvaOpt3Remark) {
		this.res2EvaOpt3Remark = res2EvaOpt3Remark;
	}
	@Column(name = "Res2EvaOpt4Remark")
	public String getRes2EvaOpt4Remark() {
		return res2EvaOpt4Remark;
	}
	public void setRes2EvaOpt4Remark(String res2EvaOpt4Remark) {
		this.res2EvaOpt4Remark = res2EvaOpt4Remark;
	}
	@Column(name = "Res2EvaOpt5Remark")
	public String getRes2EvaOpt5Remark() {
		return res2EvaOpt5Remark;
	}
	public void setRes2EvaOpt5Remark(String res2EvaOpt5Remark) {
		this.res2EvaOpt5Remark = res2EvaOpt5Remark;
	}
	@Column(name = "IsRes2DoOpt1")
	public Boolean getIsRes2DoOpt1() {
		return isRes2DoOpt1;
	}
	public void setIsRes2DoOpt1(Boolean isRes2DoOpt1) {
		this.isRes2DoOpt1 = isRes2DoOpt1;
	}
	@Column(name = "IsRes2DoOpt2")
	public Boolean getIsRes2DoOpt2() {
		return isRes2DoOpt2;
	}
	public void setIsRes2DoOpt2(Boolean isRes2DoOpt2) {
		this.isRes2DoOpt2 = isRes2DoOpt2;
	}
	@Column(name = "IsRes2DoOpt3")
	public Boolean getIsRes2DoOpt3() {
		return isRes2DoOpt3;
	}
	public void setIsRes2DoOpt3(Boolean isRes2DoOpt3) {
		this.isRes2DoOpt3 = isRes2DoOpt3;
	}
	@Column(name = "IsRes2DoOpt4")
	public Boolean getIsRes2DoOpt4() {
		return isRes2DoOpt4;
	}
	public void setIsRes2DoOpt4(Boolean isRes2DoOpt4) {
		this.isRes2DoOpt4 = isRes2DoOpt4;
	}
	@Column(name = "IsRes2DoOpt5")
	public Boolean getIsRes2DoOpt5() {
		return isRes2DoOpt5;
	}
	public void setIsRes2DoOpt5(Boolean isRes2DoOpt5) {
		this.isRes2DoOpt5 = isRes2DoOpt5;
	}
	@Column(name = "IsRes2DoOpt6")
	public Boolean getIsRes2DoOpt6() {
		return isRes2DoOpt6;
	}
	public void setIsRes2DoOpt6(Boolean isRes2DoOpt6) {
		this.isRes2DoOpt6 = isRes2DoOpt6;
	}
	@Column(name = "IsRes2DoOpt7")
	public Boolean getIsRes2DoOpt7() {
		return isRes2DoOpt7;
	}
	public void setIsRes2DoOpt7(Boolean isRes2DoOpt7) {
		this.isRes2DoOpt7 = isRes2DoOpt7;
	}
	@Column(name = "Res2DoOpt1Amount")
	public Integer getRes2DoOpt1Amount() {
		return res2DoOpt1Amount;
	}
	public void setRes2DoOpt1Amount(Integer res2DoOpt1Amount) {
		this.res2DoOpt1Amount = res2DoOpt1Amount;
	}
	@Column(name = "Res2DoOpt1Remark")
	public String getRes2DoOpt1Remark() {
		return res2DoOpt1Remark;
	}
	public void setRes2DoOpt1Remark(String res2DoOpt1Remark) {
		this.res2DoOpt1Remark = res2DoOpt1Remark;
	}
	@Column(name = "Res2DoOpt2Remark")
	public String getRes2DoOpt2Remark() {
		return res2DoOpt2Remark;
	}
	public void setRes2DoOpt2Remark(String res2DoOpt2Remark) {
		this.res2DoOpt2Remark = res2DoOpt2Remark;
	}
	@Column(name = "Res2DoOpt3Remark")
	public String getRes2DoOpt3Remark() {
		return res2DoOpt3Remark;
	}
	public void setRes2DoOpt3Remark(String res2DoOpt3Remark) {
		this.res2DoOpt3Remark = res2DoOpt3Remark;
	}
	@Column(name = "Res2DoOpt5Date")
	public Date getRes2DoOpt5Date() {
		return res2DoOpt5Date;
	}
	public void setRes2DoOpt5Date(Date res2DoOpt5Date) {
		this.res2DoOpt5Date = res2DoOpt5Date;
	}
	@Column(name = "Res2DoOpt6Amount")
	public Integer getRes2DoOpt6Amount() {
		return res2DoOpt6Amount;
	}
	public void setRes2DoOpt6Amount(Integer res2DoOpt6Amount) {
		this.res2DoOpt6Amount = res2DoOpt6Amount;
	}
	@Column(name = "Res2DoOpt7Remark")
	public String getRes2DoOpt7Remark() {
		return res2DoOpt7Remark;
	}
	public void setRes2DoOpt7Remark(String res2DoOpt7Remark) {
		this.res2DoOpt7Remark = res2DoOpt7Remark;
	}
	@Column(name = "IsRes3LogOpt1")
	public Boolean getIsRes3LogOpt1() {
		return isRes3LogOpt1;
	}
	public void setIsRes3LogOpt1(Boolean isRes3LogOpt1) {
		this.isRes3LogOpt1 = isRes3LogOpt1;
	}
	@Column(name = "IsRes3LogOpt2")
	public Boolean getIsRes3LogOpt2() {
		return isRes3LogOpt2;
	}
	public void setIsRes3LogOpt2(Boolean isRes3LogOpt2) {
		this.isRes3LogOpt2 = isRes3LogOpt2;
	}
	@Column(name = "IsRes3LogOpt3")
	public Boolean getIsRes3LogOpt3() {
		return isRes3LogOpt3;
	}
	public void setIsRes3LogOpt3(Boolean isRes3LogOpt3) {
		this.isRes3LogOpt3 = isRes3LogOpt3;
	}
	@Column(name = "IsRes3LogOpt4")
	public Boolean getIsRes3LogOpt4() {
		return isRes3LogOpt4;
	}
	public void setIsRes3LogOpt4(Boolean isRes3LogOpt4) {
		this.isRes3LogOpt4 = isRes3LogOpt4;
	}
	@Column(name = "Res3LogOpt1")
	public Integer getRes3LogOpt1() {
		return res3LogOpt1;
	}
	public void setRes3LogOpt1(Integer res3LogOpt1) {
		this.res3LogOpt1 = res3LogOpt1;
	}
	@Column(name = "Res3LogOpt1Other")
	public String getRes3LogOpt1Other() {
		return res3LogOpt1Other;
	}
	public void setRes3LogOpt1Other(String res3LogOpt1Other) {
		this.res3LogOpt1Other = res3LogOpt1Other;
	}
	@Column(name = "Res3LogOpt2")
	public Integer getRes3LogOpt2() {
		return res3LogOpt2;
	}
	public void setRes3LogOpt2(Integer res3LogOpt2) {
		this.res3LogOpt2 = res3LogOpt2;
	}
	@Column(name = "Res3LogOpt2Other")
	public String getRes3LogOpt2Other() {
		return res3LogOpt2Other;
	}
	public void setRes3LogOpt2Other(String res3LogOpt2Other) {
		this.res3LogOpt2Other = res3LogOpt2Other;
	}
	@Column(name = "Res3LogOpt3Amount")
	public Integer getRes3LogOpt3Amount() {
		return res3LogOpt3Amount;
	}
	public void setRes3LogOpt3Amount(Integer res3LogOpt3Amount) {
		this.res3LogOpt3Amount = res3LogOpt3Amount;
	}
	@Column(name = "Res3LogOpt4Remark")
	public String getRes3LogOpt4Remark() {
		return res3LogOpt4Remark;
	}
	public void setRes3LogOpt4Remark(String res3LogOpt4Remark) {
		this.res3LogOpt4Remark = res3LogOpt4Remark;
	}
	@Column(name = "IsRes3EvaOpt1")
	public Boolean getIsRes3EvaOpt1() {
		return isRes3EvaOpt1;
	}
	public void setIsRes3EvaOpt1(Boolean isRes3EvaOpt1) {
		this.isRes3EvaOpt1 = isRes3EvaOpt1;
	}
	@Column(name = "IsRes3EvaOpt2")
	public Boolean getIsRes3EvaOpt2() {
		return isRes3EvaOpt2;
	}
	public void setIsRes3EvaOpt2(Boolean isRes3EvaOpt2) {
		this.isRes3EvaOpt2 = isRes3EvaOpt2;
	}
	@Column(name = "IsRes3EvaOpt3")
	public Boolean getIsRes3EvaOpt3() {
		return isRes3EvaOpt3;
	}
	public void setIsRes3EvaOpt3(Boolean isRes3EvaOpt3) {
		this.isRes3EvaOpt3 = isRes3EvaOpt3;
	}
	@Column(name = "Res3EvaOpt1Amount")
	public Integer getRes3EvaOpt1Amount() {
		return res3EvaOpt1Amount;
	}
	public void setRes3EvaOpt1Amount(Integer res3EvaOpt1Amount) {
		this.res3EvaOpt1Amount = res3EvaOpt1Amount;
	}
	@Column(name = "Res3EvaOpt2Remark")
	public String getRes3EvaOpt2Remark() {
		return res3EvaOpt2Remark;
	}
	public void setRes3EvaOpt2Remark(String res3EvaOpt2Remark) {
		this.res3EvaOpt2Remark = res3EvaOpt2Remark;
	}
	@Column(name = "Res3EvaOpt3Remark")
	public String getRes3EvaOpt3Remark() {
		return res3EvaOpt3Remark;
	}
	public void setRes3EvaOpt3Remark(String res3EvaOpt3Remark) {
		this.res3EvaOpt3Remark = res3EvaOpt3Remark;
	}
	@Column(name = "IsRes3DoOpt1")
	public Boolean getIsRes3DoOpt1() {
		return isRes3DoOpt1;
	}
	public void setIsRes3DoOpt1(Boolean isRes3DoOpt1) {
		this.isRes3DoOpt1 = isRes3DoOpt1;
	}
	@Column(name = "IsRes3DoOpt2")
	public Boolean getIsRes3DoOpt2() {
		return isRes3DoOpt2;
	}
	public void setIsRes3DoOpt2(Boolean isRes3DoOpt2) {
		this.isRes3DoOpt2 = isRes3DoOpt2;
	}
	@Column(name = "IsRes3DoOpt3")
	public Boolean getIsRes3DoOpt3() {
		return isRes3DoOpt3;
	}
	public void setIsRes3DoOpt3(Boolean isRes3DoOpt3) {
		this.isRes3DoOpt3 = isRes3DoOpt3;
	}
	@Column(name = "IsRes3DoOpt4")
	public Boolean getIsRes3DoOpt4() {
		return isRes3DoOpt4;
	}
	public void setIsRes3DoOpt4(Boolean isRes3DoOpt4) {
		this.isRes3DoOpt4 = isRes3DoOpt4;
	}
	@Column(name = "Res3DoOpt1Remark")
	public String getRes3DoOpt1Remark() {
		return res3DoOpt1Remark;
	}
	public void setRes3DoOpt1Remark(String res3DoOpt1Remark) {
		this.res3DoOpt1Remark = res3DoOpt1Remark;
	}
	@Column(name = "Res3DoOpt3Remark")
	public String getRes3DoOpt3Remark() {
		return res3DoOpt3Remark;
	}
	public void setRes3DoOpt3Remark(String res3DoOpt3Remark) {
		this.res3DoOpt3Remark = res3DoOpt3Remark;
	}
	@Column(name = "Res3DoOpt4Remark")
	public String getRes3DoOpt4Remark() {
		return res3DoOpt4Remark;
	}
	public void setRes3DoOpt4Remark(String res3DoOpt4Remark) {
		this.res3DoOpt4Remark = res3DoOpt4Remark;
	}
	@Column(name = "IsRes4LogOpt1")
	public Boolean getIsRes4LogOpt1() {
		return isRes4LogOpt1;
	}
	public void setIsRes4LogOpt1(Boolean isRes4LogOpt1) {
		this.isRes4LogOpt1 = isRes4LogOpt1;
	}
	@Column(name = "Res4LogOpt1Remark")
	public String getRes4LogOpt1Remark() {
		return res4LogOpt1Remark;
	}
	public void setRes4LogOpt1Remark(String res4LogOpt1Remark) {
		this.res4LogOpt1Remark = res4LogOpt1Remark;
	}
	@Column(name = "IsRes4EvaOpt1")
	public Boolean getIsRes4EvaOpt1() {
		return isRes4EvaOpt1;
	}
	public void setIsRes4EvaOpt1(Boolean isRes4EvaOpt1) {
		this.isRes4EvaOpt1 = isRes4EvaOpt1;
	}
	@Column(name = "IsRes4EvaOpt2")
	public Boolean getIsRes4EvaOpt2() {
		return isRes4EvaOpt2;
	}
	public void setIsRes4EvaOpt2(Boolean isRes4EvaOpt2) {
		this.isRes4EvaOpt2 = isRes4EvaOpt2;
	}
	@Column(name = "IsRes4EvaOpt3")
	public Boolean getIsRes4EvaOpt3() {
		return isRes4EvaOpt3;
	}
	public void setIsRes4EvaOpt3(Boolean isRes4EvaOpt3) {
		this.isRes4EvaOpt3 = isRes4EvaOpt3;
	}
	@Column(name = "Res4EvaOpt1")
	public Integer getRes4EvaOpt1() {
		return res4EvaOpt1;
	}
	public void setRes4EvaOpt1(Integer res4EvaOpt1) {
		this.res4EvaOpt1 = res4EvaOpt1;
	}
	@Column(name = "Res4EvaOpt1Amount")
	public Integer getRes4EvaOpt1Amount() {
		return res4EvaOpt1Amount;
	}
	public void setRes4EvaOpt1Amount(Integer res4EvaOpt1Amount) {
		this.res4EvaOpt1Amount = res4EvaOpt1Amount;
	}
	@Column(name = "Res4EvaOpt2Remark")
	public String getRes4EvaOpt2Remark() {
		return res4EvaOpt2Remark;
	}
	public void setRes4EvaOpt2Remark(String res4EvaOpt2Remark) {
		this.res4EvaOpt2Remark = res4EvaOpt2Remark;
	}
	@Column(name = "Res4EvaOpt3Remark")
	public String getRes4EvaOpt3Remark() {
		return res4EvaOpt3Remark;
	}
	public void setRes4EvaOpt3Remark(String res4EvaOpt3Remark) {
		this.res4EvaOpt3Remark = res4EvaOpt3Remark;
	}
	@Column(name = "IsRes4DoOpt1")
	public Boolean getIsRes4DoOpt1() {
		return isRes4DoOpt1;
	}
	public void setIsRes4DoOpt1(Boolean isRes4DoOpt1) {
		this.isRes4DoOpt1 = isRes4DoOpt1;
	}
	@Column(name = "IsRes4DoOpt2")
	public Boolean getIsRes4DoOpt2() {
		return isRes4DoOpt2;
	}
	public void setIsRes4DoOpt2(Boolean isRes4DoOpt2) {
		this.isRes4DoOpt2 = isRes4DoOpt2;
	}
	@Column(name = "IsRes4DoOpt4")
	public Boolean getIsRes4DoOpt4() {
		return isRes4DoOpt4;
	}
	public void setIsRes4DoOpt4(Boolean isRes4DoOpt4) {
		this.isRes4DoOpt4 = isRes4DoOpt4;
	}
	@Column(name = "IsRes4DoOpt3")
	public Boolean getIsRes4DoOpt3() {
		return isRes4DoOpt3;
	}
	public void setIsRes4DoOpt3(Boolean isRes4DoOpt3) {
		this.isRes4DoOpt3 = isRes4DoOpt3;
	}
	@Column(name = "Res4DoOpt3Remark")
	public String getRes4DoOpt3Remark() {
		return res4DoOpt3Remark;
	}
	public void setRes4DoOpt3Remark(String res4DoOpt3Remark) {
		this.res4DoOpt3Remark = res4DoOpt3Remark;
	}
	@Column(name = "IsRes5LogOpt1")
	public Boolean getIsRes5LogOpt1() {
		return isRes5LogOpt1;
	}
	public void setIsRes5LogOpt1(Boolean isRes5LogOpt1) {
		this.isRes5LogOpt1 = isRes5LogOpt1;
	}
	@Column(name = "IsRes5LogOpt2")
	public Boolean getIsRes5LogOpt2() {
		return isRes5LogOpt2;
	}
	public void setIsRes5LogOpt2(Boolean isRes5LogOpt2) {
		this.isRes5LogOpt2 = isRes5LogOpt2;
	}
	@Column(name = "IsRes5LogOpt3")
	public Boolean getIsRes5LogOpt3() {
		return isRes5LogOpt3;
	}
	public void setIsRes5LogOpt3(Boolean isRes5LogOpt3) {
		this.isRes5LogOpt3 = isRes5LogOpt3;
	}
	@Column(name = "IsRes5LogOpt4")
	public Boolean getIsRes5LogOpt4() {
		return isRes5LogOpt4;
	}
	public void setIsRes5LogOpt4(Boolean isRes5LogOpt4) {
		this.isRes5LogOpt4 = isRes5LogOpt4;
	}
	@Column(name = "Res5LogOpt1")
	public Integer getRes5LogOpt1() {
		return res5LogOpt1;
	}
	public void setRes5LogOpt1(Integer res5LogOpt1) {
		this.res5LogOpt1 = res5LogOpt1;
	}
	@Column(name = "Res5LogOpt1Other")
	public String getRes5LogOpt1Other() {
		return res5LogOpt1Other;
	}
	public void setRes5LogOpt1Other(String res5LogOpt1Other) {
		this.res5LogOpt1Other = res5LogOpt1Other;
	}
	@Column(name = "Res5LogOpt2")
	public Integer getRes5LogOpt2() {
		return res5LogOpt2;
	}
	public void setRes5LogOpt2(Integer res5LogOpt2) {
		this.res5LogOpt2 = res5LogOpt2;
	}
	@Column(name = "Res5LogOpt2Other")
	public String getRes5LogOpt2Other() {
		return res5LogOpt2Other;
	}
	public void setRes5LogOpt2Other(String res5LogOpt2Other) {
		this.res5LogOpt2Other = res5LogOpt2Other;
	}
	@Column(name = "Res5LogOpt3Amount")
	public Integer getRes5LogOpt3Amount() {
		return res5LogOpt3Amount;
	}
	public void setRes5LogOpt3Amount(Integer res5LogOpt3Amount) {
		this.res5LogOpt3Amount = res5LogOpt3Amount;
	}
	@Column(name = "Res5LogOpt4Remark")
	public String getRes5LogOpt4Remark() {
		return res5LogOpt4Remark;
	}
	public void setRes5LogOpt4Remark(String res5LogOpt4Remark) {
		this.res5LogOpt4Remark = res5LogOpt4Remark;
	}
	@Column(name = "IsRes5EvaOpt1")
	public Boolean getIsRes5EvaOpt1() {
		return isRes5EvaOpt1;
	}
	public void setIsRes5EvaOpt1(Boolean isRes5EvaOpt1) {
		this.isRes5EvaOpt1 = isRes5EvaOpt1;
	}
	@Column(name = "IsRes5EvaOpt2")
	public Boolean getIsRes5EvaOpt2() {
		return isRes5EvaOpt2;
	}
	public void setIsRes5EvaOpt2(Boolean isRes5EvaOpt2) {
		this.isRes5EvaOpt2 = isRes5EvaOpt2;
	}
	@Column(name = "IsRes5EvaOpt3")
	public Boolean getIsRes5EvaOpt3() {
		return isRes5EvaOpt3;
	}
	public void setIsRes5EvaOpt3(Boolean isRes5EvaOpt3) {
		this.isRes5EvaOpt3 = isRes5EvaOpt3;
	}
	@Column(name = "IsRes5EvaOpt4")
	public Boolean getIsRes5EvaOpt4() {
		return isRes5EvaOpt4;
	}
	public void setIsRes5EvaOpt4(Boolean isRes5EvaOpt4) {
		this.isRes5EvaOpt4 = isRes5EvaOpt4;
	}
	@Column(name = "IsRes5EvaOpt5")
	public Boolean getIsRes5EvaOpt5() {
		return isRes5EvaOpt5;
	}
	public void setIsRes5EvaOpt5(Boolean isRes5EvaOpt5) {
		this.isRes5EvaOpt5 = isRes5EvaOpt5;
	}
	@Column(name = "Res5EvaOpt1Remark")
	public String getRes5EvaOpt1Remark() {
		return res5EvaOpt1Remark;
	}
	public void setRes5EvaOpt1Remark(String res5EvaOpt1Remark) {
		this.res5EvaOpt1Remark = res5EvaOpt1Remark;
	}
	@Column(name = "Res5EvaOpt2Remark")
	public String getRes5EvaOpt2Remark() {
		return res5EvaOpt2Remark;
	}
	public void setRes5EvaOpt2Remark(String res5EvaOpt2Remark) {
		this.res5EvaOpt2Remark = res5EvaOpt2Remark;
	}
	@Column(name = "Res5EvaOpt3Remark")
	public String getRes5EvaOpt3Remark() {
		return res5EvaOpt3Remark;
	}
	public void setRes5EvaOpt3Remark(String res5EvaOpt3Remark) {
		this.res5EvaOpt3Remark = res5EvaOpt3Remark;
	}
	@Column(name = "Res5EvaOpt4Remark")
	public String getRes5EvaOpt4Remark() {
		return res5EvaOpt4Remark;
	}
	public void setRes5EvaOpt4Remark(String res5EvaOpt4Remark) {
		this.res5EvaOpt4Remark = res5EvaOpt4Remark;
	}
	@Column(name = "Res5EvaOpt5Remark")
	public String getRes5EvaOpt5Remark() {
		return res5EvaOpt5Remark;
	}
	public void setRes5EvaOpt5Remark(String res5EvaOpt5Remark) {
		this.res5EvaOpt5Remark = res5EvaOpt5Remark;
	}
	@Column(name = "IsRes5DoOpt1")
	public Boolean getIsRes5DoOpt1() {
		return isRes5DoOpt1;
	}
	public void setIsRes5DoOpt1(Boolean isRes5DoOpt1) {
		this.isRes5DoOpt1 = isRes5DoOpt1;
	}
	@Column(name = "IsRes5DoOpt2")
	public Boolean getIsRes5DoOpt2() {
		return isRes5DoOpt2;
	}
	public void setIsRes5DoOpt2(Boolean isRes5DoOpt2) {
		this.isRes5DoOpt2 = isRes5DoOpt2;
	}
	@Column(name = "IsRes5DoOpt3")
	public Boolean getIsRes5DoOpt3() {
		return isRes5DoOpt3;
	}
	public void setIsRes5DoOpt3(Boolean isRes5DoOpt3) {
		this.isRes5DoOpt3 = isRes5DoOpt3;
	}
	@Column(name = "IsRes5DoOpt4")
	public Boolean getIsRes5DoOpt4() {
		return isRes5DoOpt4;
	}
	public void setIsRes5DoOpt4(Boolean isRes5DoOpt4) {
		this.isRes5DoOpt4 = isRes5DoOpt4;
	}
	@Column(name = "IsRes5DoOpt5")
	public Boolean getIsRes5DoOpt5() {
		return isRes5DoOpt5;
	}
	public void setIsRes5DoOpt5(Boolean isRes5DoOpt5) {
		this.isRes5DoOpt5 = isRes5DoOpt5;
	}
	@Column(name = "IsRes5DoOpt6")
	public Boolean getIsRes5DoOpt6() {
		return isRes5DoOpt6;
	}
	public void setIsRes5DoOpt6(Boolean isRes5DoOpt6) {
		this.isRes5DoOpt6 = isRes5DoOpt6;
	}
	@Column(name = "IsRes5DoOpt7")
	public Boolean getIsRes5DoOpt7() {
		return isRes5DoOpt7;
	}
	public void setIsRes5DoOpt7(Boolean isRes5DoOpt7) {
		this.isRes5DoOpt7 = isRes5DoOpt7;
	}
	@Column(name = "Res5DoOpt1Amount")
	public Integer getRes5DoOpt1Amount() {
		return res5DoOpt1Amount;
	}
	public void setRes5DoOpt1Amount(Integer res5DoOpt1Amount) {
		this.res5DoOpt1Amount = res5DoOpt1Amount;
	}
	@Column(name = "Res5DoOpt1Remark")
	public String getRes5DoOpt1Remark() {
		return res5DoOpt1Remark;
	}
	public void setRes5DoOpt1Remark(String res5DoOpt1Remark) {
		this.res5DoOpt1Remark = res5DoOpt1Remark;
	}
	@Column(name = "Res5DoOpt2Remark")
	public String getRes5DoOpt2Remark() {
		return res5DoOpt2Remark;
	}
	public void setRes5DoOpt2Remark(String res5DoOpt2Remark) {
		this.res5DoOpt2Remark = res5DoOpt2Remark;
	}
	@Column(name = "Res5DoOpt3Remark")
	public String getRes5DoOpt3Remark() {
		return res5DoOpt3Remark;
	}
	public void setRes5DoOpt3Remark(String res5DoOpt3Remark) {
		this.res5DoOpt3Remark = res5DoOpt3Remark;
	}
	@Column(name = "Res5DoOpt5Date")
	public Date getRes5DoOpt5Date() {
		return res5DoOpt5Date;
	}
	public void setRes5DoOpt5Date(Date res5DoOpt5Date) {
		this.res5DoOpt5Date = res5DoOpt5Date;
	}
	@Column(name = "Res5DoOpt6Amount")
	public Integer getRes5DoOpt6Amount() {
		return res5DoOpt6Amount;
	}
	public void setRes5DoOpt6Amount(Integer res5DoOpt6Amount) {
		this.res5DoOpt6Amount = res5DoOpt6Amount;
	}
	@Column(name = "Res5DoOpt7Remark")
	public String getRes5DoOpt7Remark() {
		return res5DoOpt7Remark;
	}
	public void setRes5DoOpt7Remark(String res5DoOpt7Remark) {
		this.res5DoOpt7Remark = res5DoOpt7Remark;
	}
	@Column(name = "IsNeedSupport")
	public Boolean getIsNeedSupport() {
		return isNeedSupport;
	}
	public void setIsNeedSupport(Boolean isNeedSupport) {
		this.isNeedSupport = isNeedSupport;
	}
	@Column(name = "NeedSupportRemark")
	public String getNeedSupportRemark() {
		return needSupportRemark;
	}
	public void setNeedSupportRemark(String needSupportRemark) {
		this.needSupportRemark = needSupportRemark;
	}
	@Column(name = "Finish1Reason")
	public Integer getFinish1Reason() {
		return finish1Reason;
	}
	public void setFinish1Reason(Integer finish1Reason) {
		this.finish1Reason = finish1Reason;
	}
	@Column(name = "Finish1ReasonOther")
	public String getFinish1ReasonOther() {
		return finish1ReasonOther;
	}
	public void setFinish1ReasonOther(String finish1ReasonOther) {
		this.finish1ReasonOther = finish1ReasonOther;
	}
	@Column(name = "Finish1ReasonToDo")
	public String getFinish1ReasonToDo() {
		return finish1ReasonToDo;
	}
	public void setFinish1ReasonToDo(String finish1ReasonToDo) {
		this.finish1ReasonToDo = finish1ReasonToDo;
	}
	@Column(name = "IsFinish1DoSysOpt1")
	public Boolean getIsFinish1DoSysOpt1() {
		return isFinish1DoSysOpt1;
	}
	public void setIsFinish1DoSysOpt1(Boolean isFinish1DoSysOpt1) {
		this.isFinish1DoSysOpt1 = isFinish1DoSysOpt1;
	}
	@Column(name = "IsFinish1DoSysOpt2")
	public Boolean getIsFinish1DoSysOpt2() {
		return isFinish1DoSysOpt2;
	}
	public void setIsFinish1DoSysOpt2(Boolean isFinish1DoSysOpt2) {
		this.isFinish1DoSysOpt2 = isFinish1DoSysOpt2;
	}
	@Column(name = "IsFinish1DoSysOpt3")
	public Boolean getIsFinish1DoSysOpt3() {
		return isFinish1DoSysOpt3;
	}
	public void setIsFinish1DoSysOpt3(Boolean isFinish1DoSysOpt3) {
		this.isFinish1DoSysOpt3 = isFinish1DoSysOpt3;
	}
	@Column(name = "IsFinish1DoSysOpt4")
	public Boolean getIsFinish1DoSysOpt4() {
		return isFinish1DoSysOpt4;
	}
	public void setIsFinish1DoSysOpt4(Boolean isFinish1DoSysOpt4) {
		this.isFinish1DoSysOpt4 = isFinish1DoSysOpt4;
	}
	@Column(name = "IsFinish1DoSysOpt5")
	public Boolean getIsFinish1DoSysOpt5() {
		return isFinish1DoSysOpt5;
	}
	public void setIsFinish1DoSysOpt5(Boolean isFinish1DoSysOpt5) {
		this.isFinish1DoSysOpt5 = isFinish1DoSysOpt5;
	}
	@Column(name = "IsFinish1DoSysOpt6")
	public Boolean getIsFinish1DoSysOpt6() {
		return isFinish1DoSysOpt6;
	}
	public void setIsFinish1DoSysOpt6(Boolean isFinish1DoSysOpt6) {
		this.isFinish1DoSysOpt6 = isFinish1DoSysOpt6;
	}
	@Column(name = "IsFinish1DoSysOpt7")
	public Boolean getIsFinish1DoSysOpt7() {
		return isFinish1DoSysOpt7;
	}
	public void setIsFinish1DoSysOpt7(Boolean isFinish1DoSysOpt7) {
		this.isFinish1DoSysOpt7 = isFinish1DoSysOpt7;
	}
	@Column(name = "IsFinish1DoSysOpt8")
	public Boolean getIsFinish1DoSysOpt8() {
		return isFinish1DoSysOpt8;
	}
	public void setIsFinish1DoSysOpt8(Boolean isFinish1DoSysOpt8) {
		this.isFinish1DoSysOpt8 = isFinish1DoSysOpt8;
	}
	@Column(name = "IsFinish1DoSysOpt9")
	public Boolean getIsFinish1DoSysOpt9() {
		return isFinish1DoSysOpt9;
	}
	public void setIsFinish1DoSysOpt9(Boolean isFinish1DoSysOpt9) {
		this.isFinish1DoSysOpt9 = isFinish1DoSysOpt9;
	}
	@Column(name = "IsFinish1DoSysOpt10")
	public Boolean getIsFinish1DoSysOpt10() {
		return isFinish1DoSysOpt10;
	}
	public void setIsFinish1DoSysOpt10(Boolean isFinish1DoSysOpt10) {
		this.isFinish1DoSysOpt10 = isFinish1DoSysOpt10;
	}
	@Column(name = "Finish1DoSysOpt3Remark")
	public String getFinish1DoSysOpt3Remark() {
		return finish1DoSysOpt3Remark;
	}
	public void setFinish1DoSysOpt3Remark(String finish1DoSysOpt3Remark) {
		this.finish1DoSysOpt3Remark = finish1DoSysOpt3Remark;
	}
	@Column(name = "Finish1DoSysOpt6Remark")
	public String getFinish1DoSysOpt6Remark() {
		return finish1DoSysOpt6Remark;
	}
	public void setFinish1DoSysOpt6Remark(String finish1DoSysOpt6Remark) {
		this.finish1DoSysOpt6Remark = finish1DoSysOpt6Remark;
	}
	@Column(name = "Finish1DoSysOpt7Remark")
	public String getFinish1DoSysOpt7Remark() {
		return finish1DoSysOpt7Remark;
	}
	public void setFinish1DoSysOpt7Remark(String finish1DoSysOpt7Remark) {
		this.finish1DoSysOpt7Remark = finish1DoSysOpt7Remark;
	}
	@Column(name = "IsFinish1DoEduOpt1")
	public Boolean getIsFinish1DoEduOpt1() {
		return isFinish1DoEduOpt1;
	}
	public void setIsFinish1DoEduOpt1(Boolean isFinish1DoEduOpt1) {
		this.isFinish1DoEduOpt1 = isFinish1DoEduOpt1;
	}
	@Column(name = "IsFinish1DoEduOpt2")
	public Boolean getIsFinish1DoEduOpt2() {
		return isFinish1DoEduOpt2;
	}
	public void setIsFinish1DoEduOpt2(Boolean isFinish1DoEduOpt2) {
		this.isFinish1DoEduOpt2 = isFinish1DoEduOpt2;
	}
	@Column(name = "IsFinish1DoEduOpt3")
	public Boolean getIsFinish1DoEduOpt3() {
		return isFinish1DoEduOpt3;
	}
	public void setIsFinish1DoEduOpt3(Boolean isFinish1DoEduOpt3) {
		this.isFinish1DoEduOpt3 = isFinish1DoEduOpt3;
	}
	@Column(name = "IsFinish1DoEduOpt4")
	public Boolean getIsFinish1DoEduOpt4() {
		return isFinish1DoEduOpt4;
	}
	public void setIsFinish1DoEduOpt4(Boolean isFinish1DoEduOpt4) {
		this.isFinish1DoEduOpt4 = isFinish1DoEduOpt4;
	}
	@Column(name = "Finish2Reason")
	public Integer getFinish2Reason() {
		return finish2Reason;
	}
	public void setFinish2Reason(Integer finish2Reason) {
		this.finish2Reason = finish2Reason;
	}
	@Column(name = "Finish2ReasonOther")
	public String getFinish2ReasonOther() {
		return finish2ReasonOther;
	}
	public void setFinish2ReasonOther(String finish2ReasonOther) {
		this.finish2ReasonOther = finish2ReasonOther;
	}
	@Column(name = "Finish2ReasonRemark")
	public String getFinish2ReasonRemark() {
		return finish2ReasonRemark;
	}
	public void setFinish2ReasonRemark(String finish2ReasonRemark) {
		this.finish2ReasonRemark = finish2ReasonRemark;
	}
	@Column(name = "IsFinish2DoSysOpt1")
	public Boolean getIsFinish2DoSysOpt1() {
		return isFinish2DoSysOpt1;
	}
	public void setIsFinish2DoSysOpt1(Boolean isFinish2DoSysOpt1) {
		this.isFinish2DoSysOpt1 = isFinish2DoSysOpt1;
	}
	@Column(name = "IsFinish2DoSysOpt2")
	public Boolean getIsFinish2DoSysOpt2() {
		return isFinish2DoSysOpt2;
	}
	public void setIsFinish2DoSysOpt2(Boolean isFinish2DoSysOpt2) {
		this.isFinish2DoSysOpt2 = isFinish2DoSysOpt2;
	}
	@Column(name = "IsFinish2DoSysOpt3")
	public Boolean getIsFinish2DoSysOpt3() {
		return isFinish2DoSysOpt3;
	}
	public void setIsFinish2DoSysOpt3(Boolean isFinish2DoSysOpt3) {
		this.isFinish2DoSysOpt3 = isFinish2DoSysOpt3;
	}
	@Column(name = "IsFinish2DoSysOpt4")
	public Boolean getIsFinish2DoSysOpt4() {
		return isFinish2DoSysOpt4;
	}
	public void setIsFinish2DoSysOpt4(Boolean isFinish2DoSysOpt4) {
		this.isFinish2DoSysOpt4 = isFinish2DoSysOpt4;
	}
	@Column(name = "IsFinish2DoSysOpt5")
	public Boolean getIsFinish2DoSysOpt5() {
		return isFinish2DoSysOpt5;
	}
	public void setIsFinish2DoSysOpt5(Boolean isFinish2DoSysOpt5) {
		this.isFinish2DoSysOpt5 = isFinish2DoSysOpt5;
	}
	@Column(name = "Finish2DoSysOpt1Remark")
	public String getFinish2DoSysOpt1Remark() {
		return finish2DoSysOpt1Remark;
	}
	public void setFinish2DoSysOpt1Remark(String finish2DoSysOpt1Remark) {
		this.finish2DoSysOpt1Remark = finish2DoSysOpt1Remark;
	}
	@Column(name = "IsFinish2DoEduOpt1")
	public Boolean getIsFinish2DoEduOpt1() {
		return isFinish2DoEduOpt1;
	}
	public void setIsFinish2DoEduOpt1(Boolean isFinish2DoEduOpt1) {
		this.isFinish2DoEduOpt1 = isFinish2DoEduOpt1;
	}
	@Column(name = "IsFinish2DoEduOpt2")
	public Boolean getIsFinish2DoEduOpt2() {
		return isFinish2DoEduOpt2;
	}
	public void setIsFinish2DoEduOpt2(Boolean isFinish2DoEduOpt2) {
		this.isFinish2DoEduOpt2 = isFinish2DoEduOpt2;
	}
	@Column(name = "IsFinish2DoEduOpt3")
	public Boolean getIsFinish2DoEduOpt3() {
		return isFinish2DoEduOpt3;
	}
	public void setIsFinish2DoEduOpt3(Boolean isFinish2DoEduOpt3) {
		this.isFinish2DoEduOpt3 = isFinish2DoEduOpt3;
	}
	@Column(name = "IsFinish2DoEduOpt4")
	public Boolean getIsFinish2DoEduOpt4() {
		return isFinish2DoEduOpt4;
	}
	public void setIsFinish2DoEduOpt4(Boolean isFinish2DoEduOpt4) {
		this.isFinish2DoEduOpt4 = isFinish2DoEduOpt4;
	}
	@Column(name = "IsFinish3DoSysOpt1")
	public Boolean getIsFinish3DoSysOpt1() {
		return isFinish3DoSysOpt1;
	}
	public void setIsFinish3DoSysOpt1(Boolean isFinish3DoSysOpt1) {
		this.isFinish3DoSysOpt1 = isFinish3DoSysOpt1;
	}
	@Column(name = "IsFinish3DoSysOpt2")
	public Boolean getIsFinish3DoSysOpt2() {
		return isFinish3DoSysOpt2;
	}
	public void setIsFinish3DoSysOpt2(Boolean isFinish3DoSysOpt2) {
		this.isFinish3DoSysOpt2 = isFinish3DoSysOpt2;
	}
	@Column(name = "IsFinish3DoSysOpt3")
	public Boolean getIsFinish3DoSysOpt3() {
		return isFinish3DoSysOpt3;
	}
	public void setIsFinish3DoSysOpt3(Boolean isFinish3DoSysOpt3) {
		this.isFinish3DoSysOpt3 = isFinish3DoSysOpt3;
	}
	@Column(name = "IsFinish3DoSysOpt4")
	public Boolean getIsFinish3DoSysOpt4() {
		return isFinish3DoSysOpt4;
	}
	public void setIsFinish3DoSysOpt4(Boolean isFinish3DoSysOpt4) {
		this.isFinish3DoSysOpt4 = isFinish3DoSysOpt4;
	}
	@Column(name = "Finish3DoSysOpt3Remark")
	public String getFinish3DoSysOpt3Remark() {
		return finish3DoSysOpt3Remark;
	}
	public void setFinish3DoSysOpt3Remark(String finish3DoSysOpt3Remark) {
		this.finish3DoSysOpt3Remark = finish3DoSysOpt3Remark;
	}
	@Column(name = "Finish3DoSysOpt4Remark")
	public String getFinish3DoSysOpt4Remark() {
		return finish3DoSysOpt4Remark;
	}
	public void setFinish3DoSysOpt4Remark(String finish3DoSysOpt4Remark) {
		this.finish3DoSysOpt4Remark = finish3DoSysOpt4Remark;
	}
	@Column(name = "IsFinish3DoEduOpt1")
	public Boolean getIsFinish3DoEduOpt1() {
		return isFinish3DoEduOpt1;
	}
	public void setIsFinish3DoEduOpt1(Boolean isFinish3DoEduOpt1) {
		this.isFinish3DoEduOpt1 = isFinish3DoEduOpt1;
	}
	@Column(name = "IsFinish3DoEduOpt2")
	public Boolean getIsFinish3DoEduOpt2() {
		return isFinish3DoEduOpt2;
	}
	public void setIsFinish3DoEduOpt2(Boolean isFinish3DoEduOpt2) {
		this.isFinish3DoEduOpt2 = isFinish3DoEduOpt2;
	}
	@Column(name = "Finish4Reason")
	public Integer getFinish4Reason() {
		return finish4Reason;
	}
	public void setFinish4Reason(Integer finish4Reason) {
		this.finish4Reason = finish4Reason;
	}
	@Column(name = "Finish4ReasonOther")
	public String getFinish4ReasonOther() {
		return finish4ReasonOther;
	}
	public void setFinish4ReasonOther(String finish4ReasonOther) {
		this.finish4ReasonOther = finish4ReasonOther;
	}
	@Column(name = "Finish4ReasonRemark")
	public String getFinish4ReasonRemark() {
		return finish4ReasonRemark;
	}
	public void setFinish4ReasonRemark(String finish4ReasonRemark) {
		this.finish4ReasonRemark = finish4ReasonRemark;
	}
	@Column(name = "IsFinish4DoSysOpt1")
	public Boolean getIsFinish4DoSysOpt1() {
		return isFinish4DoSysOpt1;
	}
	public void setIsFinish4DoSysOpt1(Boolean isFinish4DoSysOpt1) {
		this.isFinish4DoSysOpt1 = isFinish4DoSysOpt1;
	}
	@Column(name = "IsFinish4DoEduOpt1")
	public Boolean getIsFinish4DoEduOpt1() {
		return isFinish4DoEduOpt1;
	}
	public void setIsFinish4DoEduOpt1(Boolean isFinish4DoEduOpt1) {
		this.isFinish4DoEduOpt1 = isFinish4DoEduOpt1;
	}
	@Column(name = "IsFinish4DoEduOpt2")
	public Boolean getIsFinish4DoEduOpt2() {
		return isFinish4DoEduOpt2;
	}
	public void setIsFinish4DoEduOpt2(Boolean isFinish4DoEduOpt2) {
		this.isFinish4DoEduOpt2 = isFinish4DoEduOpt2;
	}
	@Column(name = "IsFinish4DoEduOpt3")
	public Boolean getIsFinish4DoEduOpt3() {
		return isFinish4DoEduOpt3;
	}
	public void setIsFinish4DoEduOpt3(Boolean isFinish4DoEduOpt3) {
		this.isFinish4DoEduOpt3 = isFinish4DoEduOpt3;
	}
	@Column(name = "IsFinish4DoEduOpt4")
	public Boolean getIsFinish4DoEduOpt4() {
		return isFinish4DoEduOpt4;
	}
	public void setIsFinish4DoEduOpt4(Boolean isFinish4DoEduOpt4) {
		this.isFinish4DoEduOpt4 = isFinish4DoEduOpt4;
	}
	@Column(name = "Finish5Reason")
	public Integer getFinish5Reason() {
		return finish5Reason;
	}
	public void setFinish5Reason(Integer finish5Reason) {
		this.finish5Reason = finish5Reason;
	}
	@Column(name = "Finish5ReasonOther")
	public String getFinish5ReasonOther() {
		return finish5ReasonOther;
	}
	public void setFinish5ReasonOther(String finish5ReasonOther) {
		this.finish5ReasonOther = finish5ReasonOther;
	}
	@Column(name = "Finish5ReasonRemark")
	public String getFinish5ReasonRemark() {
		return finish5ReasonRemark;
	}
	public void setFinish5ReasonRemark(String finish5ReasonRemark) {
		this.finish5ReasonRemark = finish5ReasonRemark;
	}
	@Column(name = "IsFinish5DoSysOpt1")
	public Boolean getIsFinish5DoSysOpt1() {
		return isFinish5DoSysOpt1;
	}
	public void setIsFinish5DoSysOpt1(Boolean isFinish5DoSysOpt1) {
		this.isFinish5DoSysOpt1 = isFinish5DoSysOpt1;
	}
	@Column(name = "IsFinish5DoSysOpt2")
	public Boolean getIsFinish5DoSysOpt2() {
		return isFinish5DoSysOpt2;
	}
	public void setIsFinish5DoSysOpt2(Boolean isFinish5DoSysOpt2) {
		this.isFinish5DoSysOpt2 = isFinish5DoSysOpt2;
	}
	@Column(name = "IsFinish5DoSysOpt3")
	public Boolean getIsFinish5DoSysOpt3() {
		return isFinish5DoSysOpt3;
	}
	public void setIsFinish5DoSysOpt3(Boolean isFinish5DoSysOpt3) {
		this.isFinish5DoSysOpt3 = isFinish5DoSysOpt3;
	}
	@Column(name = "IsFinish5DoSysOpt4")
	public Boolean getIsFinish5DoSysOpt4() {
		return isFinish5DoSysOpt4;
	}
	public void setIsFinish5DoSysOpt4(Boolean isFinish5DoSysOpt4) {
		this.isFinish5DoSysOpt4 = isFinish5DoSysOpt4;
	}
	@Column(name = "Finish5DoSysOpt1Remark")
	public String getFinish5DoSysOpt1Remark() {
		return finish5DoSysOpt1Remark;
	}
	public void setFinish5DoSysOpt1Remark(String finish5DoSysOpt1Remark) {
		this.finish5DoSysOpt1Remark = finish5DoSysOpt1Remark;
	}
	@Column(name = "IsFinish5DoEduOpt1")
	public Boolean getIsFinish5DoEduOpt1() {
		return isFinish5DoEduOpt1;
	}
	public void setIsFinish5DoEduOpt1(Boolean isFinish5DoEduOpt1) {
		this.isFinish5DoEduOpt1 = isFinish5DoEduOpt1;
	}
	@Column(name = "IsFinish5DoEduOpt2")
	public Boolean getIsFinish5DoEduOpt2() {
		return isFinish5DoEduOpt2;
	}
	public void setIsFinish5DoEduOpt2(Boolean isFinish5DoEduOpt2) {
		this.isFinish5DoEduOpt2 = isFinish5DoEduOpt2;
	}
	@Column(name = "IsFinish5DoEduOpt3")
	public Boolean getIsFinish5DoEduOpt3() {
		return isFinish5DoEduOpt3;
	}
	public void setIsFinish5DoEduOpt3(Boolean isFinish5DoEduOpt3) {
		this.isFinish5DoEduOpt3 = isFinish5DoEduOpt3;
	}
	@Column(name = "IsFinish5DoEduOpt4")
	public Boolean getIsFinish5DoEduOpt4() {
		return isFinish5DoEduOpt4;
	}
	public void setIsFinish5DoEduOpt4(Boolean isFinish5DoEduOpt4) {
		this.isFinish5DoEduOpt4 = isFinish5DoEduOpt4;
	}
	@Column(name = "FinishDoOther")
	public String getFinishDoOther() {
		return finishDoOther;
	}
	public void setFinishDoOther(String finishDoOther) {
		this.finishDoOther = finishDoOther;
	}
	@Column(name = "Status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "FinishDateTime")
	public Date getFinishDateTime() {
		return finishDateTime;
	}
	public void setFinishDateTime(Date finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	@Column(name = "ExamineDateTime")
	public Date getExamineDateTime() {
		return examineDateTime;
	}
	public void setExamineDateTime(Date examineDateTime) {
		this.examineDateTime = examineDateTime;
	}
	@Column(name = "RealFinishDateTime")
	public Date getRealFinishDateTime() {
		return realFinishDateTime;
	}
	public void setRealFinishDateTime(Date realFinishDateTime) {
		this.realFinishDateTime = realFinishDateTime;
	}
	@Column(name = "MessageId")
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	@Column(name = "MessagePostId")
	public String getMessagePostId() {
		return messagePostId;
	}
	public void setMessagePostId(String messagePostId) {
		this.messagePostId = messagePostId;
	}
	@Column(name = "IsCC3")
	public Boolean getIsCC3() {
		return isCC3;
	}
	public void setIsCC3(Boolean isCC3) {
		this.isCC3 = isCC3;
	}
	@Column(name = "IsCC5")
	public Boolean getIsCC5() {
		return isCC5;
	}
	public void setIsCC5(Boolean isCC5) {
		this.isCC5 = isCC5;
	}
	@Column(name = "IsReview3")
	public Boolean getIsReview3() {
		return isReview3;
	}
	public void setIsReview3(Boolean isReview3) {
		this.isReview3 = isReview3;
	}
	@Column(name = "IsReview5")
	public Boolean getIsReview5() {
		return isReview5;
	}
	public void setIsReview5(Boolean isReview5) {
		this.isReview5 = isReview5;
	}

	@Column(name = "TransferInType")
	public Integer getTransferInType() {
		return transferInType;
	}
	public void setTransferInType(Integer transferInType) {
		this.transferInType = transferInType;
	}
	@Column(name = "TransferInId")
	public String getTransferInId() {
		return transferInId;
	}
	public void setTransferInId(String transferInId) {
		this.transferInId = transferInId;
	}
	@Column(name = "TransferOutType")
	public Integer getTransferOutType() {
		return transferOutType;
	}
	public void setTransferOutType(Integer transferOutType) {
		this.transferOutType = transferOutType;
	}
	@Column(name = "TransferOutId")
	public String getTransferOutId() {
		return transferOutId;
	}
	public void setTransferOutId(String transferOutId) {
		this.transferOutId = transferOutId;
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

	@Column(name = "IncidentId")
	public Long getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(Long incidentId) {
		this.incidentId = incidentId;
	}

	@Column(name = "CertId")
	public Long getCertId() {
		return certId;
	}
	public void setCertId(Long certId) {
		this.certId = certId;
	}
	
	@Column(name = "IsFinishDoSysOptRemark")
	public Boolean getIsFinishDoSysOptRemark() {
		return isFinishDoSysOptRemark;
	}
	public void setIsFinishDoSysOptRemark(Boolean isFinishDoSysOptRemark) {
		this.isFinishDoSysOptRemark = isFinishDoSysOptRemark;
	}
	
	@Column(name = "IsFinishDoEduOptRemark")
	public Boolean getIsFinishDoEduOptRemark() {
		return isFinishDoEduOptRemark;
	}
	public void setIsFinishDoEduOptRemark(Boolean isFinishDoEduOptRemark) {
		this.isFinishDoEduOptRemark = isFinishDoEduOptRemark;
	}
	@Column(name = "FinishDoSysOptRemark")
	public String getFinishDoSysOptRemark() {
		return finishDoSysOptRemark;
	}
	public void setFinishDoSysOptRemark(String finishDoSysOptRemark) {
		this.finishDoSysOptRemark = finishDoSysOptRemark;
	}
	@Column(name = "FinishDoEduOptRemark")
	public String getFinishDoEduOptRemark() {
		return finishDoEduOptRemark;
	}
	public void setFinishDoEduOptRemark(String finishDoEduOptRemark) {
		this.finishDoEduOptRemark = finishDoEduOptRemark;
	}
	
	
	@Column(name = "IsHandledByMyself")
	public Boolean getIsHandledByMyself() {
		return isHandledByMyself;
	}
	public void setIsHandledByMyself(Boolean isHandledByMyself) {
		this.isHandledByMyself = isHandledByMyself;
	}
	
	public Long getHandleInformationId() {
		return handleInformationId;
	}
	public void setHandleInformationId(Long handleInformationId) {
		this.handleInformationId = handleInformationId;
	}
	
	@Column(name = "IsTest")
	public Boolean getIsTest() {
		return isTest;
	}
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}
}
