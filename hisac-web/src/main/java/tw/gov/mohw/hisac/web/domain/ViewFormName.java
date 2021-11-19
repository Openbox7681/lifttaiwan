package tw.gov.mohw.hisac.web.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "v_form_name", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewFormName {
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
	private String subsystemName;

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
	@Column(name = "SubsystemName", nullable = false)
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
}