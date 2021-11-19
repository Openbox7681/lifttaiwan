package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "v_maintain_inspect_member_org", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewMaintainInspectMemberOrg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)	
	private Long id;

	@Column(name = "MaintainInspectId", nullable = false)	
	private Long maintainInspectId;
	
	@Column(name = "GroupId", nullable = false)
	private Long groupId;	

	@Column(name = "OrgName")
	private String orgName;
	
	@Column(name = "HospitalUploadSdate")
	private Date hospitalUploadSdate;
	
	@Column(name = "HospitalUploadEdate")
	private Date hospitalUploadEdate;
	
	@Column(name = "CommitteeUploadSdate")
	private Date committeeUploadSdate;
	
	@Column(name = "CommitteeUploadEdate")
	private Date committeeUploadEdate;
	
	@Column(name = "InspectStatus")	
	private String inspectStatus;
	
	@Column(name = "InspectMemberId")
	private Long inspectMemberId;
	
	@Column(name = "InspectMemberName")
	private String inspectMemberName;
	
	@Column(name = "AllowHospitalPatch")	
	private Boolean allowHospitalPatch;

	@Column(name = "Title")
	private String title;
	
	@Column(name = "Year")
	private String year;
	
	@Column(name = "Status")	
	private String status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaintainInspectId() {
		return maintainInspectId;
	}
	public void setMaintainInspectId(Long maintainInspectId) {
		this.maintainInspectId = maintainInspectId;
	}

	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getHospitalUploadSdate() {
		return hospitalUploadSdate;
	}
	public void setHospitalUploadSdate(Date hospitalUploadSdate) {
		this.hospitalUploadSdate = hospitalUploadSdate;
	}

	public Date getHospitalUploadEdate() {
		return hospitalUploadEdate;
	}
	public void setHospitalUploadEdate(Date hospitalUploadEdate) {
		this.hospitalUploadEdate = hospitalUploadEdate;
	}

	public Date getCommitteeUploadSdate() {
		return committeeUploadSdate;
	}
	public void setCommitteeUploadSdate(Date committeeUploadSdate) {
		this.committeeUploadSdate = committeeUploadSdate;
	}

	public Date getCommitteeUploadEdate() {
		return committeeUploadEdate;
	}
	public void setCommitteeUploadEdate(Date committeeUploadEdate) {
		this.committeeUploadEdate = committeeUploadEdate;
	}

	public String getInspectStatus() {
		return inspectStatus;
	}
	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
	}

	public Long getInspectMemberId() {
		return inspectMemberId;
	}
	public void setInspectMemberId(Long inspectMemberId) {
		this.inspectMemberId = inspectMemberId;
	}

	public String getInspectMemberName() {
		return inspectMemberName;
	}
	public void setInspectMemberName(String inspectMemberName) {
		this.inspectMemberName = inspectMemberName;
	}

	public Boolean getAllowHospitalPatch() {
		return allowHospitalPatch;
	}
	public void setAllowHospitalPatch(Boolean allowHospitalPatch) {
		this.allowHospitalPatch = allowHospitalPatch;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
