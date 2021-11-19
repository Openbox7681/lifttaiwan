package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "v_information_exchange_secbuzzerTitle", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewInformationExchangeSecbuzzerTitle {

	private String id;
	private String title;
	private String postId;
	private String stixTitle;
	private String incidentId;
	private String incidentTitle;
	private Date incidentDiscoveryTime;
	private Date incidentReportedTime;
	private Date incidentClosedTime;
	private String description;
	private String category;
	private String reporterName;
	private String responderPartyName;
	private String responderContactNumbers;
	private String responderElectronicAddressIdentifiers;
	private Integer impactQualification;
	private String coaDescription;
	private String confidence;
	private String reference;
	private String observableAttach;
	private String observableIpAddress;
	private String socketIpAddress;
	private String socketPort;
	private String socketProtocol;
	private String customIpAddress;
	private String customPort;
	private String customProtocol;
	private String destinationIpAddress;
	private String destinationPort;
	private String destinationProtocol;
	private String leveragedDescription;
	private String affectedSoftwareDescription;
	private String resourcesSourceIpAddress;
	private String resourcesDestinationPort;
	private String resourcesDestinationProtocol;
	private String resourcesDestination;
	private String scanEngine;
	private String scanVersion;
	private String scanResult;
	private String relatedIncidentId;
	private Date relatedIncidentTimestamp;
	private Integer status;
	private String sourceCode;

	private @NotNull Boolean isEnable;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;

	private Integer newsManagementGroupId;
	private String nhiImpact;
	private String nhiProblemEquipmentOwner;
	private String nhiProblemEquipmentUse;
	private String nhiProblemIpAddress;
	private String nhiProblemPort;
	private String nhiProblemUrl;
	private String nhiProcess;
	private String nhiRemark;
	private String nhiRiskGrade;
	private String nhiRiskType;
	private String nhiSourceRecord;
	private String reporterNameUrl;
	private String resourcesSourcePort;
	private byte[] sourceContentXml;

	private String transferInId;
	private @NotNull Integer transferInType = 0;
	private String transferOutId;
	private @NotNull Integer transferOutType = 0;
	private String nisacIncidentId;
	private byte[] nisacSourceContentXML;
	private Long sort;

	@Id
	@Column(name = "Id", nullable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}

	@Column(name = "StixTitle")
	public String getStixTitle() {
		return stixTitle;
	}
	public void setStixTitle(String stixTitle) {
		this.stixTitle = stixTitle;
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

	@Column(name = "IncidentClosedTime")
	public Date getIncidentClosedTime() {
		return incidentClosedTime;
	}
	public void setIncidentClosedTime(Date incidentClosedTime) {
		this.incidentClosedTime = incidentClosedTime;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Integer getImpactQualification() {
		return impactQualification;
	}
	public void setImpactQualification(Integer impactQualification) {
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

	@Column(name = "ObservableAttach")
	public String getObservableAttach() {
		return observableAttach;
	}
	public void setObservableAttach(String observableAttach) {
		this.observableAttach = observableAttach;
	}

	@Column(name = "ObservableIpAddress")
	public String getObservableIpAddress() {
		return observableIpAddress;
	}
	public void setObservableIpAddress(String observableIpAddress) {
		this.observableIpAddress = observableIpAddress;
	}

	@Column(name = "SocketIpAddress")
	public String getSocketIpAddress() {
		return socketIpAddress;
	}
	public void setSocketIpAddress(String socketIpAddress) {
		this.socketIpAddress = socketIpAddress;
	}

	@Column(name = "SocketPort")
	public String getSocketPort() {
		return socketPort;
	}
	public void setSocketPort(String socketPort) {
		this.socketPort = socketPort;
	}

	@Column(name = "SocketProtocol")
	public String getSocketProtocol() {
		return socketProtocol;
	}
	public void setSocketProtocol(String socketProtocol) {
		this.socketProtocol = socketProtocol;
	}

	@Column(name = "CustomIpAddress")
	public String getCustomIpAddress() {
		return customIpAddress;
	}
	public void setCustomIpAddress(String customIpAddress) {
		this.customIpAddress = customIpAddress;
	}

	@Column(name = "CustomPort")
	public String getCustomPort() {
		return customPort;
	}
	public void setCustomPort(String customPort) {
		this.customPort = customPort;
	}

	@Column(name = "CustomProtocol")
	public String getCustomProtocol() {
		return customProtocol;
	}
	public void setCustomProtocol(String customProtocol) {
		this.customProtocol = customProtocol;
	}

	@Column(name = "DestinationIpAddress")
	public String getDestinationIpAddress() {
		return destinationIpAddress;
	}
	public void setDestinationIpAddress(String destinationIpAddress) {
		this.destinationIpAddress = destinationIpAddress;
	}

	@Column(name = "DestinationPort")
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}

	@Column(name = "DestinationProtocol")
	public String getDestinationProtocol() {
		return destinationProtocol;
	}
	public void setDestinationProtocol(String destinationProtocol) {
		this.destinationProtocol = destinationProtocol;
	}

	@Column(name = "LeveragedDescription")
	public String getLeveragedDescription() {
		return leveragedDescription;
	}
	public void setLeveragedDescription(String leveragedDescription) {
		this.leveragedDescription = leveragedDescription;
	}

	@Column(name = "AffectedSoftwareDescription")
	public String getAffectedSoftwareDescription() {
		return affectedSoftwareDescription;
	}
	public void setAffectedSoftwareDescription(String affectedSoftwareDescription) {
		this.affectedSoftwareDescription = affectedSoftwareDescription;
	}

	@Column(name = "ResourcesSourceIpAddress")
	public String getResourcesSourceIpAddress() {
		return resourcesSourceIpAddress;
	}
	public void setResourcesSourceIpAddress(String resourcesSourceIpAddress) {
		this.resourcesSourceIpAddress = resourcesSourceIpAddress;
	}

	@Column(name = "ResourcesDestinationPort")
	public String getResourcesDestinationPort() {
		return resourcesDestinationPort;
	}
	public void setResourcesDestinationPort(String resourcesDestinationPort) {
		this.resourcesDestinationPort = resourcesDestinationPort;
	}

	@Column(name = "ResourcesDestinationProtocol")
	public String getResourcesDestinationProtocol() {
		return resourcesDestinationProtocol;
	}
	public void setResourcesDestinationProtocol(String resourcesDestinationProtocol) {
		this.resourcesDestinationProtocol = resourcesDestinationProtocol;
	}

	@Column(name = "ResourcesDestination")
	public String getResourcesDestination() {
		return resourcesDestination;
	}
	public void setResourcesDestination(String resourcesDestination) {
		this.resourcesDestination = resourcesDestination;
	}

	@Column(name = "ScanEngine")
	public String getScanEngine() {
		return scanEngine;
	}
	public void setScanEngine(String scanEngine) {
		this.scanEngine = scanEngine;
	}

	@Column(name = "ScanVersion")
	public String getScanVersion() {
		return scanVersion;
	}
	public void setScanVersion(String scanVersion) {
		this.scanVersion = scanVersion;
	}

	@Column(name = "ScanResult")
	public String getScanResult() {
		return scanResult;
	}
	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}

	@Column(name = "RelatedIncidentId")
	public String getRelatedIncidentId() {
		return relatedIncidentId;
	}
	public void setRelatedIncidentId(String relatedIncidentId) {
		this.relatedIncidentId = relatedIncidentId;
	}

	@Column(name = "RelatedIncidentTimestamp")
	public Date getRelatedIncidentTimestamp() {
		return relatedIncidentTimestamp;
	}
	public void setRelatedIncidentTimestamp(Date relatedIncidentTimestamp) {
		this.relatedIncidentTimestamp = relatedIncidentTimestamp;
	}

	@Column(name = "IsEnable", nullable = false)
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "CreateId", nullable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime", nullable = false)
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "SourceCode")
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "NewsManagementGroupId")
	public Integer getNewsManagementGroupId() {
		return newsManagementGroupId;
	}
	public void setNewsManagementGroupId(Integer newsManagementGroupId) {
		this.newsManagementGroupId = newsManagementGroupId;
	}

	@Column(name = "NhiImpact")
	public String getNhiImpact() {
		return nhiImpact;
	}
	public void setNhiImpact(String nhiImpact) {
		this.nhiImpact = nhiImpact;
	}

	@Column(name = "NhiProblemEquipmentOwner")
	public String getNhiProblemEquipmentOwner() {
		return nhiProblemEquipmentOwner;
	}
	public void setNhiProblemEquipmentOwner(String nhiProblemEquipmentOwner) {
		this.nhiProblemEquipmentOwner = nhiProblemEquipmentOwner;
	}

	@Column(name = "NhiProblemEquipmentUse")
	public String getNhiProblemEquipmentUse() {
		return nhiProblemEquipmentUse;
	}
	public void setNhiProblemEquipmentUse(String nhiProblemEquipmentUse) {
		this.nhiProblemEquipmentUse = nhiProblemEquipmentUse;
	}

	@Column(name = "NhiProblemIpAddress")
	public String getNhiProblemIpAddress() {
		return nhiProblemIpAddress;
	}
	public void setNhiProblemIpAddress(String nhiProblemIpAddress) {
		this.nhiProblemIpAddress = nhiProblemIpAddress;
	}

	@Column(name = "NhiProblemPort")
	public String getNhiProblemPort() {
		return nhiProblemPort;
	}
	public void setNhiProblemPort(String nhiProblemPort) {
		this.nhiProblemPort = nhiProblemPort;
	}

	@Column(name = "NhiProblemUrl")
	public String getNhiProblemUrl() {
		return nhiProblemUrl;
	}
	public void setNhiProblemUrl(String nhiProblemUrl) {
		this.nhiProblemUrl = nhiProblemUrl;
	}

	@Column(name = "NhiProcess")
	public String getNhiProcess() {
		return nhiProcess;
	}
	public void setNhiProcess(String nhiProcess) {
		this.nhiProcess = nhiProcess;
	}

	@Column(name = "NhiRemark")
	public String getNhiRemark() {
		return nhiRemark;
	}
	public void setNhiRemark(String nhiRemark) {
		this.nhiRemark = nhiRemark;
	}

	@Column(name = "NhiRiskGrade")
	public String getNhiRiskGrade() {
		return nhiRiskGrade;
	}
	public void setNhiRiskGrade(String nhiRiskGrade) {
		this.nhiRiskGrade = nhiRiskGrade;
	}

	@Column(name = "NhiRiskType")
	public String getNhiRiskType() {
		return nhiRiskType;
	}
	public void setNhiRiskType(String nhiRiskType) {
		this.nhiRiskType = nhiRiskType;
	}

	@Column(name = "NhiSourceRecord")
	public String getNhiSourceRecord() {
		return nhiSourceRecord;
	}
	public void setNhiSourceRecord(String nhiSourceRecord) {
		this.nhiSourceRecord = nhiSourceRecord;
	}

	@Column(name = "ReporterNameUrl")
	public String getReporterNameUrl() {
		return reporterNameUrl;
	}

	@Column(name = "ResourcesSourcePort")
	public String getResourcesSourcePort() {
		return resourcesSourcePort;
	}
	public void setResourcesSourcePort(String resourcesSourcePort) {
		this.resourcesSourcePort = resourcesSourcePort;
	}
	public void setReporterNameUrl(String reporterNameUrl) {
		this.reporterNameUrl = reporterNameUrl;
	}

	@Column(name = "SourceContentXml")
	public byte[] getSourceContentXml() {
		return sourceContentXml;
	}
	public void setSourceContentXml(byte[] sourceContentXml) {
		this.sourceContentXml = sourceContentXml;
	}

	@Column(name = "TransferInId")
	public String getTransferInId() {
		return transferInId;
	}
	public void setTransferInId(String transferInId) {
		this.transferInId = transferInId;
	}

	@Column(name = "TransferInType", nullable = false)
	public Integer getTransferInType() {
		return transferInType;
	}
	public void setTransferInType(Integer transferInType) {
		this.transferInType = transferInType;
	}

	@Column(name = "TransferOutId")
	public String getTransferOutId() {
		return transferOutId;
	}
	public void setTransferOutId(String transferOutId) {
		this.transferOutId = transferOutId;
	}

	@Column(name = "TransferOutType", nullable = false)
	public Integer getTransferOutType() {
		return transferOutType;
	}
	public void setTransferOutType(Integer transferOutType) {
		this.transferOutType = transferOutType;
	}

	@Column(name = "NisacIncidentId")
	public String getNisacIncidentId() {
		return nisacIncidentId;
	}
	public void setNisacIncidentId(String nisacIncidentId) {
		this.nisacIncidentId = nisacIncidentId;
	}

	@Column(name = "NisacSourceContentXML")
	public byte[] getNisacSourceContentXML() {
		return nisacSourceContentXML;
	}
	public void setNisacSourceContentXML(byte[] nisacSourceContentXML) {
		this.nisacSourceContentXML = nisacSourceContentXML;
	}
	
	@Column(name = "Sort")
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
}