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
@Table(name = "v_message_alert_event", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewMessageAlertEvent {
	private String id;
	private String alertCode;
	
	private String eventCode;
	private String externalId;
	private Date  findDate;
	private Date  postDateTime;
	private String subject;
	private String description;
	private String suggestion;
	private String reference;
	private String determine;
	private String contents;
	private String affectPlatform;
	private String impactLevel;
	private String status;
	private String postId;
	private String sourceCode;
	private String sourceName;
	private Boolean isReply;
	private Boolean isSms;
	private Boolean isTest;
	private @NotNull Boolean isEnable;
	private String alertTypeName;
	private String eventTypeName;
	private String modifyName;
	
	private Long modifyId;
	private Date modifyTime;
	
	//簡訊內容
	private String smsFormat;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "AlertCode", nullable = false)
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	@Column(name = "EventCode", nullable = false)
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	@Column(name = "ExternalId")
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	@Column(name = "FindDate", nullable = false)
	public Date getFindDate() {
		return findDate;
	}
	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}
	@Column(name = "PostDateTime")
	public Date getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(Date postDateTime) {
		this.postDateTime = postDateTime;
	}
	@Column(name = "Subject", nullable = false)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "Suggestion")
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	@Column(name = "Reference")
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Column(name = "Determine")
	public String getDetermine() {
		return determine;
	}
	public void setDetermine(String determine) {
		this.determine = determine;
	}
	@Column(name = "Contents")
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Column(name = "AffectPlatform")
	public String getAffectPlatform() {
		return affectPlatform;
	}
	public void setAffectPlatform(String affectPlatform) {
		this.affectPlatform = affectPlatform;
	}
	@Column(name = "ImpactLevel")
	public String getImpactLevel() {
		return impactLevel;
	}
	public void setImpactLevel(String impactLevel) {
		this.impactLevel = impactLevel;
	}
	@Column(name = "Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	@Column(name = "SourceCode")
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	@Column(name = "SourceName")
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Column(name = "IsReply", nullable = false)
	public Boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}
	
	@Column(name = "IsTest")
	public Boolean getIsTest() {
		return isTest == null ? false : isTest;
	}
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	@Column(name = "IsSms")
	public Boolean getIsSms() {
		return isSms == null ? false : isSms;
	}
	public void setIsSms(Boolean isSms) {
		this.isSms = isSms;
	}
	
	@Column(name = "IsEnable", nullable = false)
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "AlertTypeName")
	public String getAlertTypeName() {
		return alertTypeName;
	}
	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}
	
	@Column(name = "EventTypeName")
	public String getEventTypeName() {
		return eventTypeName;
	}
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	
	@Column(name = "ModifyName")
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	
	@Column(name="ModifyId")
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name="ModifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "SmsFormat")
	public String getSmsFormat() {
		return smsFormat;
	}
	public void setSmsFormat(String smsFormat) {
		this.smsFormat = smsFormat;
	}
}
