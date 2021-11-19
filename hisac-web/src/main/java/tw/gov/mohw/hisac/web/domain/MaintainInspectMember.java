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
@Table(name = "maintain_inspect_member", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MaintainInspectMember {

	private Long id;	
	private @NotNull Long maintainInspectId;		
	private @NotNull Long groupId;
	private String inspectStatus;
	private Long inspectMemberId;
	private Date hospitalUploadSdate;
	private Date hospitalUploadEdate;
	private Date committeeUploadSdate;
	private Date committeeUploadEdate;
	private Boolean allowHospitalPatch;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MaintainInspectId", nullable = false)
	public Long getMaintainInspectId() {
		return maintainInspectId;
	}
	public void setMaintainInspectId(Long maintainInspectId) {
		this.maintainInspectId = maintainInspectId;
	}	
	
	@Column(name = "GroupId", nullable = false)
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "HospitalUploadSdate")	
	public Date getHospitalUploadSdate() {
		return hospitalUploadSdate;
	}
	public void setHospitalUploadSdate(Date hospitalUploadSdate) {
		this.hospitalUploadSdate = hospitalUploadSdate;
	}

	@Column(name = "HospitalUploadEdate")
	public Date getHospitalUploadEdate() {
		return hospitalUploadEdate;
	}
	public void setHospitalUploadEdate(Date hospitalUploadEdate) {
		this.hospitalUploadEdate = hospitalUploadEdate;
	}

	@Column(name = "CommitteeUploadSdate")
	public Date getCommitteeUploadSdate() {
		return committeeUploadSdate;
	}
	public void setCommitteeUploadSdate(Date committeeUploadSdate) {
		this.committeeUploadSdate = committeeUploadSdate;
	}
		
	@Column(name = "CommitteeUploadEdate")
	public Date getCommitteeUploadEdate() {
		return committeeUploadEdate;
	}
	public void setCommitteeUploadEdate(Date committeeUploadEdate) {
		this.committeeUploadEdate = committeeUploadEdate;
	}

	@Column(name = "InspectStatus")	
	public String getInspectStatus() {
		return inspectStatus;
	}
	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
	}
	
	@Column(name = "InspectMemberId")
	public Long getInspectMemberId() {
		return inspectMemberId;
	}
	public void setInspectMemberId(Long inspectMemberId) {
		this.inspectMemberId = inspectMemberId;
	}

	@Column(name = "AllowHospitalPatch")
	public Boolean getAllowHospitalPatch() {
		return allowHospitalPatch;
	}
	public void setAllowHospitalPatch(Boolean allowHospitalPatch) {
		this.allowHospitalPatch = allowHospitalPatch;
	}
}
