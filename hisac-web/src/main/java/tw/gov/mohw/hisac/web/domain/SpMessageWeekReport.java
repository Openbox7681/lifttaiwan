package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_message_week_report")
public class SpMessageWeekReport {
	
	private String eventCode;
	private String alertCode;
	
	private int  count;

	@Id
	@Column(name = "EventCode")
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	@Column(name = "AlertCode")
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	
	@Column(name = "Count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
