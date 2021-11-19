package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_message_month_report")
public class SpMessageMonthReport {
	
	private String id;
	private String alertCode;
	private int  year;
	private int  month;
	private int  count;

	@Id
	@Column(name = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	@Column(name = "AlertCode")
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	@Column(name = "Year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Column(name = "Month")
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	@Column(name = "Count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
