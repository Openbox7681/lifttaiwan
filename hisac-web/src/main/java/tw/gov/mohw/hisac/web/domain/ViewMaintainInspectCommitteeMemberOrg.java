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
@Table(name = "v_maintain_inspect_committee_member_org", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewMaintainInspectCommitteeMemberOrg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)	
	private Long id;

	@Column(name = "CommitteeId", nullable = false)	
	private Long committeeId;
	
	@Column(name = "CommitteeMemberName")
	private String committeeMemberName;

	@Column(name = "Status")
	private boolean status;	
	
	@Column(name = "MaintainInspectId", nullable = false)	
	private Long maintainInspectId;
	
	@Column(name = "Title")
	private String title;

	@Column(name = "Year")
	private String year;

	@Column(name = "GroupId", nullable = false)
	private Long groupId;	
	
	@Column(name = "OrgName")
	private String orgName;

	@Column(name = "OrgCode")
	private String orgCode;

	@Column(name = "InspectStatus")	
	private String inspectStatus;
	
	@Column(name = "HospitalUploadSdate")
	private Date hospitalUploadSdate;
	
	@Column(name = "HospitalUploadEdate")
	private Date hospitalUploadEdate;
	
	@Column(name = "CommitteeUploadSdate")
	private Date committeeUploadSdate;
	
	@Column(name = "CommitteeUploadEdate")
	private Date committeeUploadEdate;

	@Column(name = "AllowHospitalPatch")	
	private Boolean allowHospitalPatch;

	@Column(name = "FileId")	
	private Long fileId;
	
	@Column(name = "FileName")
	private String fileName;
	
	@Column(name = "FileDesc")
	private String fileDesc;

	@Column(name = "FileSize")	
	private Long fileSize;
	
	@Column(name = "ReviewOpinionFileId")	
	private Long reviewOpinionFileId;
	
	@Column(name = "ReviewOpinionFileName")
	private String reviewOpinionFileName;
	
	@Column(name = "ReviewOpinionFileDesc")
	private String reviewOpinionFileDesc;

	@Column(name = "ReviewOpinionFileSize")	
	private Long reviewOpinionFileSize;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public String getCommitteeMemberName() {
		return committeeMemberName;
	}
	public void setCommitteeMemberName(String committeeMemberName) {
		this.committeeMemberName = committeeMemberName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getMaintainInspectId() {
		return maintainInspectId;
	}
	public void setMaintainInspectId(Long maintainInspectId) {
		this.maintainInspectId = maintainInspectId;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getInspectStatus() {
		return inspectStatus;
	}
	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
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
	public Boolean getAllowHospitalPatch() {
		return allowHospitalPatch;
	}
	public void setAllowHospitalPatch(Boolean allowHospitalPatch) {
		this.allowHospitalPatch = allowHospitalPatch;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Long getReviewOpinionFileId() {
		return reviewOpinionFileId;
	}
	public void setReviewOpinionFileId(Long reviewOpinionFileId) {
		this.reviewOpinionFileId = reviewOpinionFileId;
	}
	public String getReviewOpinionFileName() {
		return reviewOpinionFileName;
	}
	public void setReviewOpinionFileName(String reviewOpinionFileName) {
		this.reviewOpinionFileName = reviewOpinionFileName;
	}
	public String getReviewOpinionFileDesc() {
		return reviewOpinionFileDesc;
	}
	public void setReviewOpinionFileDesc(String reviewOpinionFileDesc) {
		this.reviewOpinionFileDesc = reviewOpinionFileDesc;
	}
	public Long getReviewOpinionFileSize() {
		return reviewOpinionFileSize;
	}
	public void setReviewOpinionFileSize(Long reviewOpinionFileSize) {
		this.reviewOpinionFileSize = reviewOpinionFileSize;
	}
}
