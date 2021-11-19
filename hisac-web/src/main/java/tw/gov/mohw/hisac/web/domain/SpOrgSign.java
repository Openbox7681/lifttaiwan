package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_org_sign")
public class SpOrgSign {
	
	private Long id;
	private Long orgId;
	private String name;
	private Long flag;
	private Integer warningIsExamine;
	private Integer notificationIsExamine;
	private Integer notificationClosingIsExamine;

	
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	

	
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="Flag")
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	@Column(name="WarningIsExamine")
	public Integer getWarningIsExamine() {
		return warningIsExamine;
	}
	public void setWarningIsExamine(Integer warningIsExamine) {
		this.warningIsExamine = warningIsExamine;
	}
	@Column(name="NotificationIsExamine")
	public Integer getNotificationIsExamine() {
		return notificationIsExamine;
	}
	public void setNotificationIsExamine(Integer notificationIsExamine) {
		this.notificationIsExamine = notificationIsExamine;
	}
	@Column(name="NotificationClosingIsExamine")
	public Integer getNotificationClosingIsExamine() {
		return notificationClosingIsExamine;
	}
	public void setNotificationClosingIsExamine(Integer notificationClosingIsExamine) {
		this.notificationClosingIsExamine = notificationClosingIsExamine;
	}
	
	
}
