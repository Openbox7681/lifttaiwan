package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

//共同維護計畫檔案上傳
@Entity
@Table(name = "maintain_plan_improvement_suggest_read", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MaintainPlanImprovementSuggestRead {

	private Long id;	
	private @NotNull Long maintainPlanId;	
	private @NotNull Long attachId;
	private @NotNull Boolean isRead;
	private Long createId;
	private @NotNull Date createTime;
	private Long modifyId;
	private @NotNull Date modifyTime;	
	private Long orgId;


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
	
	@Column(name = "AttachId" , nullable = false)
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
		
	}
	
	@Column(name = "isRead", nullable = false)
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
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
	
	@Column(name = "OrgId", nullable = false)
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


}
