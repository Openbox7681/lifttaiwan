//package tw.gov.mohw.hisac.web.domain;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "weakness_management", uniqueConstraints = {
//		@UniqueConstraint(columnNames = "Id")})
//public class WeaknessManagement {
//
//	private Long id;
//	private String incidentId;
//	private @NotNull String incidentTitle;
//	private @NotNull Date incidentDiscoveryTime;
//	private Date incidentReportedTime;
//	private String description;
//	private @NotNull String eventTypeCode;
//	private @NotNull String reporterName;
//	private String responderPartyName;
//	private String responderContactNumbers;
//	private String responderElectronicAddressIdentifiers;
//	private @NotNull Long impactQualification;
//	private String coaDescription;
//	private String confidence;
//	private String reference;
//	private String affectedSoftwareDescription;
//	private Date startDateTime;
//	private Date endDateTime;
//	private Boolean isEnable;
//	private Long createId;
//	private Date createTime;
//	private Long modifyId;
//	private Date modifyTime;
//	private Long status;
//	private String postId;
//
//	private  String transferInId;
//	private  @NotNull Integer transferInType=0;
//	private  String transferOutId;
//	private  @NotNull Integer transferOutType=0;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	@Column(name = "IncidentId")
//	public String getIncidentId() {
//		return incidentId;
//	}
//	public void setIncidentId(String incidentId) {
//		this.incidentId = incidentId;
//	}
//
//	@Column(name = "IncidentTitle", nullable = false)
//	public String getIncidentTitle() {
//		return incidentTitle;
//	}
//	public void setIncidentTitle(@NotNull String incidentTitle) {
//		this.incidentTitle = incidentTitle;
//	}
//
//	@Column(name = "IncidentDiscoveryTime", nullable = false)
//	public Date getIncidentDiscoveryTime() {
//		return incidentDiscoveryTime;
//	}
//	public void setIncidentDiscoveryTime(@NotNull Date incidentDiscoveryTime) {
//		this.incidentDiscoveryTime = incidentDiscoveryTime;
//	}
//
//	@Column(name = "IncidentReportedTime")
//	public Date getIncidentReportedTime() {
//		return incidentReportedTime;
//	}
//	public void setIncidentReportedTime(Date incidentReportedTime) {
//		this.incidentReportedTime = incidentReportedTime;
//	}
//
//	@Column(name = "Description")
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	
//	@Column(name = "EventTypeCode", nullable = false)
//	public String getEventTypeCode() {
//		return eventTypeCode;
//	}
//	public void setEventTypeCode(@NotNull String eventTypeCode) {
//		this.eventTypeCode = eventTypeCode;
//	}
//
//	@Column(name = "ReporterName", nullable = false)
//	public String getReporterName() {
//		return reporterName;
//	}
//	public void setReporterName(@NotNull String reporterName) {
//		this.reporterName = reporterName;
//	}
//
//	@Column(name = "ResponderPartyName")
//	public String getResponderPartyName() {
//		return responderPartyName;
//	}
//	public void setResponderPartyName(String responderPartyName) {
//		this.responderPartyName = responderPartyName;
//	}
//
//	@Column(name = "ResponderContactNumbers")
//	public String getResponderContactNumbers() {
//		return responderContactNumbers;
//	}
//	public void setResponderContactNumbers(String responderContactNumbers) {
//		this.responderContactNumbers = responderContactNumbers;
//	}
//
//	@Column(name = "ResponderElectronicAddressIdentifiers")
//	public String getResponderElectronicAddressIdentifiers() {
//		return responderElectronicAddressIdentifiers;
//	}
//	public void setResponderElectronicAddressIdentifiers(String responderElectronicAddressIdentifiers) {
//		this.responderElectronicAddressIdentifiers = responderElectronicAddressIdentifiers;
//	}
//
//	@Column(name = "ImpactQualification", nullable = false)
//	public Long getImpactQualification() {
//		return impactQualification;
//	}
//	public void setImpactQualification(@NotNull Long impactQualification) {
//		this.impactQualification = impactQualification;
//	}
//
//	@Column(name = "CoaDescription")
//	public String getCoaDescription() {
//		return coaDescription;
//	}
//	public void setCoaDescription(String coaDescription) {
//		this.coaDescription = coaDescription;
//	}
//
//	@Column(name = "Confidence")
//	public String getConfidence() {
//		return confidence;
//	}
//	public void setConfidence(String confidence) {
//		this.confidence = confidence;
//	}
//
//	@Column(name = "Reference")
//	public String getReference() {
//		return reference;
//	}
//	public void setReference(String reference) {
//		this.reference = reference;
//	}
//
//	@Column(name = "AffectedSoftwareDescription")
//	public String getAffectedSoftwareDescription() {
//		return affectedSoftwareDescription;
//	}
//	public void setAffectedSoftwareDescription(String affectedSoftwareDescription) {
//		this.affectedSoftwareDescription = affectedSoftwareDescription;
//	}
//
//	@Column(name = "StartDateTime")
//	public Date getStartDateTime() {
//		return startDateTime;
//	}
//	public void setStartDateTime(Date startDateTime) {
//		this.startDateTime = startDateTime;
//	}
//
//	@Column(name = "EndDateTime")
//	public Date getEndDateTime() {
//		return endDateTime;
//	}
//	public void setEndDateTime(Date endDateTime) {
//		this.endDateTime = endDateTime;
//	}
//
//	@Column(name = "IsEnable")
//	public Boolean getIsEnable() {
//		return isEnable;
//	}
//	public void setIsEnable(Boolean isEnable) {
//		this.isEnable = isEnable;
//	}
//
//	@Column(name = "CreateId", updatable = false)
//	public Long getCreateId() {
//		return createId;
//	}
//	public void setCreateId(Long createId) {
//		this.createId = createId;
//	}
//
//	@Column(name = "CreateTime", updatable = false)
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	@Column(name = "ModifyId")
//	public Long getModifyId() {
//		return modifyId;
//	}
//	public void setModifyId(Long modifyId) {
//		this.modifyId = modifyId;
//	}
//
//	@Column(name = "ModifyTime")
//	public Date getModifyTime() {
//		return modifyTime;
//	}
//	public void setModifyTime(Date modifyTime) {
//		this.modifyTime = modifyTime;
//	}
//
//	@Column(name = "Status")
//	public Long getStatus() {
//		return status;
//	}
//	public void setStatus(Long status) {
//		this.status = status;
//	}
//
//	@Column(name = "PostId")
//	public String getPostId() {
//		return postId;
//	}
//	public void setPostId(String postId) {
//		this.postId = postId;
//	}
//
//	@Column(name = "TransferInType")
//	public Integer getTransferInType() {
//		return transferInType;
//	}
//	public void setTransferInType(Integer transferInType) {
//		this.transferInType = transferInType;
//	}
//
//	@Column(name = "TransferInId")
//	public String getTransferInId() {
//		return transferInId;
//	}
//	public void setTransferInId(String transferInId) {
//		this.transferInId = transferInId;
//	}
//
//	@Column(name = "TransferOutType")
//	public Integer getTransferOutType() {
//		return transferOutType;
//	}
//	public void setTransferOutType(Integer transferOutType) {
//		this.transferOutType = transferOutType;
//	}
//
//	@Column(name = "TransferOutId")
//	public String getTransferOutId() {
//		return transferOutId;
//	}
//	public void setTransferOutId(String transferOutId) {
//		this.transferOutId = transferOutId;
//	}
//
//}
