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
@Table(name = "maintain_inspect_committee", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MaintainInspectCommittee {
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)	
	private Long id;

	@Column(name = "MaintainInspectId", nullable = false, updatable = false)	
	private Long maintainInspectId;
	
	@Column(name = "GroupId", nullable = false)
	private Long groupId;	
	
	@Column(name = "CommitteeId", nullable = false)
	private Long committeeId;	
	
	@Column(name = "Status", nullable = false)
	private boolean status;

	@Column(name = "CreateId", nullable = false, updatable = false)
	private Long createId;	
	
	@Column(name = "CreateTime", nullable = false, updatable = false)
	private Date createTime;

	@Column(name = "ModifyId", nullable = false)
	private Long modifyId;	

	@Column(name = "ModifyTime", nullable = false)
	private Date modifyTime;
	
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
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
