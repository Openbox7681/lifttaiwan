package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="v_ana_management_member")
public class ViewAnaManagementMember {

	private Long id;
	private String incidentId;
	private String incidentTitle;
	private Date incidentDiscoveryTime;
	private Date incidentReportedTime;
	private String description;
	private String eventTypeCode;
	private String eventTypeName;
	private String reporterName;
	private String responderPartyName;
	private String responderContactNumbers;
	private String responderElectronicAddressIdentifiers;
	private Long impactQualification;
	private String coaDescription;
	private String confidence;
	private String reference;
	private String affectedSoftwareDescription;
	private Date startDateTime;
	private Date endDateTime;
	private Boolean isEnable;
	private Long createId;
	private String createName;
	private Date createTime;
	private Long modifyId;
	private String modifyName;
	private Date modifyTime;
	private Long status;
	private String postId;
	private Long sort;

	private  String transferInId;
	private  @NotNull Integer transferInType=0;
	private  String transferOutId;
	private  @NotNull Integer transferOutType=0;
	
	private Boolean isMedical;
	
	private Boolean isMalware;

	
	@Id
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "IncidentId")
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	@Column(name = "IncidentTitle")
	public String getIncidentTitle() {
		return incidentTitle;
	}
	public void setIncidentTitle(String incidentTitle) {
		this.incidentTitle = incidentTitle;
	}

	@Column(name = "IncidentDiscoveryTime")
	public Date getIncidentDiscoveryTime() {
		return incidentDiscoveryTime;
	}
	public void setIncidentDiscoveryTime(Date incidentDiscoveryTime) {
		this.incidentDiscoveryTime = incidentDiscoveryTime;
	}

	@Column(name = "IncidentReportedTime")
	public Date getIncidentReportedTime() {
		return incidentReportedTime;
	}
	public void setIncidentReportedTime(Date incidentReportedTime) {
		this.incidentReportedTime = incidentReportedTime;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "EventTypeCode")
	public String getEventTypeCode() {
		return eventTypeCode;
	}
	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	@Column(name = "EventTypeName")
	public String getEventTypeName() {
		return eventTypeName;
	}
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	@Column(name = "ReporterName")
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	@Column(name = "ResponderPartyName")
	public String getResponderPartyName() {
		return responderPartyName;
	}
	public void setResponderPartyName(String responderPartyName) {
		this.responderPartyName = responderPartyName;
	}

	@Column(name = "ResponderContactNumbers")
	public String getResponderContactNumbers() {
		return responderContactNumbers;
	}
	public void setResponderContactNumbers(String responderContactNumbers) {
		this.responderContactNumbers = responderContactNumbers;
	}

	@Column(name = "ResponderElectronicAddressIdentifiers")
	public String getResponderElectronicAddressIdentifiers() {
		return responderElectronicAddressIdentifiers;
	}
	public void setResponderElectronicAddressIdentifiers(String responderElectronicAddressIdentifiers) {
		this.responderElectronicAddressIdentifiers = responderElectronicAddressIdentifiers;
	}

	@Column(name = "ImpactQualification")
	public Long getImpactQualification() {
		return impactQualification;
	}
	public void setImpactQualification(Long impactQualification) {
		this.impactQualification = impactQualification;
	}

	@Column(name = "CoaDescription")
	public String getCoaDescription() {
		return coaDescription;
	}
	public void setCoaDescription(String coaDescription) {
		this.coaDescription = coaDescription;
	}

	@Column(name = "Confidence")
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	@Column(name = "Reference")
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name = "AffectedSoftwareDescription")
	public String getAffectedSoftwareDescription() {
		return affectedSoftwareDescription;
	}
	public void setAffectedSoftwareDescription(String affectedSoftwareDescription) {
		this.affectedSoftwareDescription = affectedSoftwareDescription;
	}

	@Column(name = "StartDateTime")
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	@Column(name = "EndDateTime")
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Column(name = "IsEnable")
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "CreateId")
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateName")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ModifyId")
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "ModifyName")
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	@Column(name = "ModifyTime")
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

	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
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
	
	@Column(name = "IsMedical")
	public Boolean getIsMedical() {
		return isMedical;
	}
	public void setIsMedical(Boolean isMedical) {
		this.isMedical = isMedical;
	}
	
	@Column(name = "IsMalware")
	public Boolean getIsMalware() {
		return isMalware;
	}
	public void setIsMalware(Boolean isMalware) {
		this.isMalware = isMalware;
	}
	
	@Column(name = "Sort")
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}

}
