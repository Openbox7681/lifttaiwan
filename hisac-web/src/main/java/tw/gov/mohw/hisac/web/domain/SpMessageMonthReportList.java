package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_message_month_report_list")
public class SpMessageMonthReportList {
	
	private String id;
	private String postId;
	
	private String alertCode;
	private Date  postDateTime;
	private String subject;
	private String status;
	private String createName;
	private Boolean isReply;
	private String sourceName;
	
	

	@Id
	@Column(name = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
		
	@Column(name = "AlertCode")
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	@Column(name = "PostDateTime")
	public Date getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(Date postDateTime) {
		this.postDateTime = postDateTime;
	}
	@Column(name = "Subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name = "Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "CreateName")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Column(name = "IsReply")
	public Boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}
	
	@Column(name = "SourceName")
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	
	
}
