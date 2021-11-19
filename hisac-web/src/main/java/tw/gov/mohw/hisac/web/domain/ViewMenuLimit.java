package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "v_menu_limit", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewMenuLimit {
	private String id;
	private Long subsytemId;
	private String subsystemName;
	private String iconStyle;
	private Long formId;
	private String formName;
	private String formCode;
	private String subsystemCode;
	private String controllerName;
	private String actionName;
	private Boolean isExternalLink;
	private Boolean actionCreate;
	private Boolean actionUpdate;
	private Boolean actionDelete;
	private Boolean actionRead;
	private Boolean actionSign;
	private Long memberId;
	private Long subsystemSort;
	private Long formSort;
	private Boolean subsystemIsShow;
	private Boolean formIsShow;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SubsytemId")
	public Long getSubsytemId() {
		return subsytemId;
	}
	public void setSubsytemId(Long subsytemId) {
		this.subsytemId = subsytemId;
	}

	@Column(name = "SubsystemName")
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	@Column(name = "IconStyle")
	public String getIconStyle() {
		return iconStyle;
	}
	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
	}

	@Column(name = "FormId")
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}

	@Column(name = "FormName")
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Column(name = "FormCode")
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	@Column(name = "SubsystemCode")
	public String getSubsystemCode() {
		return subsystemCode;
	}
	public void setSubsystemCode(String subsystemCode) {
		this.subsystemCode = subsystemCode;
	}

	@Column(name = "ControllerName")
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	@Column(name = "ActionName")
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "IsExternalLink")
	public Boolean getIsExternalLink() {
		return isExternalLink;
	}
	public void setIsExternalLink(Boolean isExternalLink) {
		this.isExternalLink = isExternalLink;
	}

	@Column(name = "ActionCreate")
	public Boolean getActionCreate() {
		return actionCreate;
	}
	public void setActionCreate(Boolean actionCreate) {
		this.actionCreate = actionCreate;
	}

	@Column(name = "ActionUpdate")
	public Boolean getActionUpdate() {
		return actionUpdate;
	}
	public void setActionUpdate(Boolean actionUpdate) {
		this.actionUpdate = actionUpdate;
	}

	@Column(name = "ActionDelete")
	public Boolean getActionDelete() {
		return actionDelete;
	}
	public void setActionDelete(Boolean actionDelete) {
		this.actionDelete = actionDelete;
	}

	@Column(name = "ActionRead")
	public Boolean getActionRead() {
		return actionRead;
	}
	public void setActionRead(Boolean actionRead) {
		this.actionRead = actionRead;
	}

	@Column(name = "ActionSign")
	public Boolean getActionSign() {
		return actionSign;
	}
	public void setActionSign(Boolean actionSign) {
		this.actionSign = actionSign;
	}

	@Column(name = "MemberId")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Column(name = "SubsystemSort")
	public Long getSubsystemSort() {
		return subsystemSort;
	}
	public void setSubsystemSort(Long subsystemSort) {
		this.subsystemSort = subsystemSort;
	}

	@Column(name = "FormSort")
	public Long getFormSort() {
		return formSort;
	}
	public void setFormSort(Long formSort) {
		this.formSort = formSort;
	}

	@Column(name = "FormIsShow")
	public Boolean getFormIsShow() {
		return formIsShow;
	}
	public void setFormIsShow(Boolean formIsShow) {
		this.formIsShow = formIsShow;
	}

	@Column(name = "SubsystemIsShow")
	public Boolean getSubsystemIsShow() {
		return subsystemIsShow;
	}
	public void setSubsystemIsShow(Boolean subsystemIsShow) {
		this.subsystemIsShow = subsystemIsShow;
	}
}
