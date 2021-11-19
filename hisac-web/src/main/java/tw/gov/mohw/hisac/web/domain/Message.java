package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Message {
	private String id;
	private String postId;
	private String alertCode;
	private String eventCode;
	private String sourceCode;
	private String externalId;
	private Date findDate;
	private Date postDateTime;
	private String subject;
	private String description;
	private String suggestion;
	private String reference;
	private String determine;
	private String contents;
	private String affectPlatform;
	private String impactLevel;
	private String status;

	private Boolean isReply;
	private Boolean isSms;
	private Boolean isTest;

	private String transferInId;
	private @NotNull Integer transferInType = 0;
	private String transferOutId;
	private @NotNull Integer transferOutType = 0;

	private @NotNull Boolean isEnable;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;
	
	//簡訊內容
	private String smsFormat;

	@Id
	@Column(name = "Id", nullable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
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
	@Column(name = "SourceCode")
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
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

	@Column(name = "IsReply")
	public Boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}

	@Column(name = "IsSms")
	public Boolean getIsSms() {
		return isSms == null ? false : isSms;
	}
	public void setIsSms(Boolean isSms) {
		this.isSms = isSms;
	}
	
	@Column(name = "IsTest")
	public Boolean getIsTest() {
		return isTest == null ? false : isTest;
	}
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	@Column(name = "IsEnable", nullable = false)
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
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

	@Column(name = "CreateId", nullable = false, updatable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime", nullable = false, updatable = false)
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
	
	@Column(name = "SmsFormat")
	public String getSmsFormat() {
		return smsFormat;
	}
	public void setSmsFormat(String smsFormat) {
		this.smsFormat = smsFormat;
	}
}
