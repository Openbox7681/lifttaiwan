package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cc", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class CC {

	private Long id;
	private String dnameOrIpname;
	private Date firstDate;
	private Date lastDate;
	private Long createId;
	private Date createTime;		
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DnameOrIpname")
	public String getDnameOrIpname() {
		return dnameOrIpname;
	}
	public void setDnameOrIpname(String dnameOrIpname) {
		this.dnameOrIpname = dnameOrIpname;
	}

	@Column(name = "CreateId")
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "FirstDate")
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	@Column(name = "LastDate")
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
}