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
@Table(name="role_form",uniqueConstraints= {@UniqueConstraint(columnNames="Id")})
public class RoleForm {
	
	private Long id;
	private @NotNull Long roleId;
	private @NotNull Long formId;
	private @NotNull Boolean actionCreate;
	private @NotNull Boolean actionUpdate;
	private @NotNull Boolean actionDelete;
	private @NotNull Boolean actionRead;
	private @NotNull Boolean actionSign;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", nullable=false, updatable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "RoleId", nullable = false, updatable=false)
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "FormId", nullable = false, updatable=false)
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	
	@Column(name = "ActionCreate", nullable = false)
	public Boolean getActionCreate() {
		return actionCreate;
	}
	public void setActionCreate(Boolean actionCreate) {
		this.actionCreate = actionCreate;
	}
	
	@Column(name = "ActionUpdate", nullable = false)
	public Boolean getActionUpdate() {
		return actionUpdate;
	}
	public void setActionUpdate(Boolean actionUpdate) {
		this.actionUpdate = actionUpdate;
	}
	
	@Column(name = "ActionDelete", nullable = false)
	public Boolean getActionDelete() {
		return actionDelete;
	}
	public void setActionDelete(Boolean actionDelete) {
		this.actionDelete = actionDelete;
	}
	
	@Column(name = "ActionRead", nullable = false)
	public Boolean getActionRead() {
		return actionRead;
	}
	public void setActionRead(Boolean actionRead) {
		this.actionRead = actionRead;
	}
	
	@Column(name = "ActionSign", nullable = false)
	public Boolean getActionSign() {
		return actionSign;
	}
	public void setActionSign(Boolean actionSign) {
		this.actionSign = actionSign;
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
	
}
