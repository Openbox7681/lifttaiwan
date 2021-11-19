package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_report")
public class MemberReport {
	
	private Long id;
	private String name;
	private Long count;
	private Date reportCreateTime;

		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="Count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	@Column(name = "ReportCreateTime", updatable = false)
	public Date getReportCreateTime() {
		return reportCreateTime;
	}
	public void setReportCreateTime(Date reportCreateTime) {
		this.reportCreateTime = reportCreateTime;
	}
	
	
	
}
