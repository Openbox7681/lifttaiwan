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
@Table(name = "org_sign", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class OrgSign {
	private Long id;
	private Long orgId;
	private Long parentOrgId;
	private Integer warningIsExamine;
	private Integer notificationIsExamine;
	private Integer notificationClosingIsExamine;
	
	
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

	@Column(name = "ParentOrgId", nullable = false)
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	
	@Column(name = "OrgId", nullable = false)
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
	@Column(name="WarningIsExamine", nullable = false)
	public Integer getWarningIsExamine() {
		return warningIsExamine;
	}
	public void setWarningIsExamine(Integer warningIsExamine) {
		this.warningIsExamine = warningIsExamine;
	}
	@Column(name="NotificationIsExamine", nullable = false)
	public Integer getNotificationIsExamine() {
		return notificationIsExamine;
	}
	public void setNotificationIsExamine(Integer notificationIsExamine) {
		this.notificationIsExamine = notificationIsExamine;
	}
	@Column(name="NotificationClosingIsExamine", nullable = false)
	public Integer getNotificationClosingIsExamine() {
		return notificationClosingIsExamine;
	}
	public void setNotificationClosingIsExamine(Integer notificationClosingIsExamine) {
		this.notificationClosingIsExamine = notificationClosingIsExamine;
	}
}
