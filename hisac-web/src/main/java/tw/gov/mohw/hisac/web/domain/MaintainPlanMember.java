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
@Table(name = "maintain_plan_member", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MaintainPlanMember {

	private Long id;	
	private @NotNull Long maintainPlanId;		
	private @NotNull Long groupId;
	private Long memberId;
	private @NotNull Date sdate;
	private @NotNull Date edate;
	private String status;
	private Date replyTime;	
//	private Date hospitalUploadSdate;
//	private Date hospitalUploadEdate;
//	private Date committeeUploadSdate;
//	private Date committeeUploadEdate;
//	private String inspectStatus;
//	private Long inspectMemberId;
//	private Boolean allowHospitalPatch;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MaintainPlanId", nullable = false)
	public Long getMaintainPlanId() {
		return maintainPlanId;
	}
	public void setMaintainPlanId(Long maintainPlanId) {
		this.maintainPlanId = maintainPlanId;
	}	
	
	@Column(name = "GroupId", nullable = false)
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "MemberId")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	@Column(name = "Sdate", nullable = false)
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	@Column(name = "Edate", nullable = false)
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	@Column(name = "Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ReplyTime")
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

//	@Column(name = "HospitalUploadSdate")	
//	public Date getHospitalUploadSdate() {
//		return hospitalUploadSdate;
//	}
//	public void setHospitalUploadSdate(Date hospitalUploadSdate) {
//		this.hospitalUploadSdate = hospitalUploadSdate;
//	}
//
//	@Column(name = "HospitalUploadEdate")
//	public Date getHospitalUploadEdate() {
//		return hospitalUploadEdate;
//	}
//	public void setHospitalUploadEdate(Date hospitalUploadEdate) {
//		this.hospitalUploadEdate = hospitalUploadEdate;
//	}
//
//	@Column(name = "CommitteeUploadSdate")
//	public Date getCommitteeUploadSdate() {
//		return committeeUploadSdate;
//	}
//	public void setCommitteeUploadSdate(Date committeeUploadSdate) {
//		this.committeeUploadSdate = committeeUploadSdate;
//	}
//		
//	@Column(name = "CommitteeUploadEdate")
//	public Date getCommitteeUploadEdate() {
//		return committeeUploadEdate;
//	}
//	public void setCommitteeUploadEdate(Date committeeUploadEdate) {
//		this.committeeUploadEdate = committeeUploadEdate;
//	}
//
//	@Column(name = "InspectStatus")	
//	public String getInspectStatus() {
//		return inspectStatus;
//	}
//	public void setInspectStatus(String inspectStatus) {
//		this.inspectStatus = inspectStatus;
//	}
//	
//	@Column(name = "InspectMemberId")
//	public Long getInspectMemberId() {
//		return inspectMemberId;
//	}
//	public void setInspectMemberId(Long inspectMemberId) {
//		this.inspectMemberId = inspectMemberId;
//	}
//
//	@Column(name = "AllowHospitalPatch")
//	public Boolean getAllowHospitalPatch() {
//		return allowHospitalPatch;
//	}
//	public void setAllowHospitalPatch(Boolean allowHospitalPatch) {
//		this.allowHospitalPatch = allowHospitalPatch;
//	}
}
