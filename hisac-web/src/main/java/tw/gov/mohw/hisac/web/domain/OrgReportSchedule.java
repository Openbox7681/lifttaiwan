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
@Table(name="org_report_schedule",uniqueConstraints= {@UniqueConstraint(columnNames="Id")})
public class OrgReportSchedule {
	
	//醫院層級資料表 110.01.18 JiongLun.Li
	
	private Long id;
	private Long status;
	private Date scheduleTime;
	private boolean isOldSchedule;
	private String message;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="Status", nullable = false)
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name="IsOldSchedule", nullable = false)
	public boolean getIsOldSchedule() {
		return isOldSchedule;
	}
	public void setIsOldSchedule(boolean isOldSchedule) {
		this.isOldSchedule = isOldSchedule;
	}

	@Column(name="ScheduleTime", nullable = false)
	public Date getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	
	@Column(name="Message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
