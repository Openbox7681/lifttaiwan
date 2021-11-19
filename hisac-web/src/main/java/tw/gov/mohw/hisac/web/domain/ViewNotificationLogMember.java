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
@Table(name = "v_notification_log_member", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewNotificationLogMember {
	private Long id;

	private String notificationId;
	private Integer ceffectLevel;
	private Integer ieffectLevel;
	private Integer aeffectLevel;
	private Integer effectLevel;
	
	private @NotNull Long createId;
	private @NotNull Date createTime;
	
	private String createName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "NotificationId")
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	@Column(name = "CeffectLevel")
	public Integer getCeffectLevel() {
		return ceffectLevel;
	}
	public void setCeffectLevel(Integer ceffectLevel) {
		this.ceffectLevel = ceffectLevel;
	}
	@Column(name = "IeffectLevel")
	public Integer getIeffectLevel() {
		return ieffectLevel;
	}
	public void setIeffectLevel(Integer ieffectLevel) {
		this.ieffectLevel = ieffectLevel;
	}
	@Column(name = "AeffectLevel")
	public Integer getAeffectLevel() {
		return aeffectLevel;
	}
	public void setAeffectLevel(Integer aeffectLevel) {
		this.aeffectLevel = aeffectLevel;
	}
	@Column(name = "EffectLevel")
	public Integer getEffectLevel() {
		return effectLevel;
	}
	public void setEffectLevel(Integer effectLevel) {
		this.effectLevel = effectLevel;
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
	
	@Column(name = "CreateName")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
}
