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
@Table(name = "form", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Form {
	private Long id;
	private @NotNull Long subsystemId;
	private @NotNull String code;
	private @NotNull String name;
	private @NotNull String controllerName;
	private @NotNull String actionName;
	private @NotNull Boolean isExternalLink;
	private @NotNull Boolean isEnable;
	private @NotNull Boolean isShow;
	private @NotNull Long sort;
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SubsystemId", nullable = false)
	public Long getSubsystemId() {
		return subsystemId;
	}
	public void setSubsystemId(Long subsystemId) {
		this.subsystemId = subsystemId;
	}

	@Column(name = "Code", nullable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ControllerName", nullable = false)
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	@Column(name = "ActionName", nullable = false)
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "IsExternalLink", nullable = false)
	public Boolean getIsExternalLink() {
		return isExternalLink;
	}
	public void setIsExternalLink(Boolean isExternalLink) {
		this.isExternalLink = isExternalLink;
	}

	@Column(name = "IsEnable", nullable = false)
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "IsShow", nullable = false)
	public Boolean getIsShow() {
		return isShow;
	}
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	@Column(name = "Sort", nullable = false)
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
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
}
