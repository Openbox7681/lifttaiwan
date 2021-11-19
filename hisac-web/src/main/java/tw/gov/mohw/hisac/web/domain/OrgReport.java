package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="org_report")
public class OrgReport {
	
	private Long id;
	private String name;	
	private String orgType;
	private String authType;
	private String ciLevel;
	private @NotNull Date createTime;
	private Date signApplyTime;
	private long memberCount;
	private long memberSigninCount;	
	private long signinCount;
	private long newsCount;
	private long activityCount;
	private long anaCount;
	private long secbuzzerCount;
	private Date reportCreateTime;
	private String memberLog;
	private Long orgId;
	
	
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
	@Column(name = "OrgType")
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "AuthType")
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	@Column(name="MemberCount")
	public long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(long memberCount) {
		this.memberCount = memberCount;
	}
	@Column(name="MemberSigninCount")
	public long getMemberSigninCount() {
		return memberSigninCount;
	}
	public void setMemberSigninCount(long memberSigninCount) {
		this.memberSigninCount = memberSigninCount;
	}
	@Column(name="SigninCount")
	public long getSigninCount() {
		return signinCount;
	}
	public void setSigninCount(long signinCount) {
		this.signinCount = signinCount;
	}
	@Column(name="NewsCount")
	public long getNewsCount() {
		return newsCount;
	}
	public void setNewsCount(long newsCount) {
		this.newsCount = newsCount;
	}
	@Column(name="ActivityCount")
	public long getActivityCount() {
		return activityCount;
	}
	public void setActivityCount(long activityCount) {
		this.activityCount = activityCount;
	}
	@Column(name="AnaCount")
	public long getAnaCount() {
		return anaCount;
	}
	public void setAnaCount(long anaCount) {
		this.anaCount = anaCount;
	}
	@Column(name="SecbuzzerCount")
	public long getSecbuzzerCount() {
		return secbuzzerCount;
	}
	public void setSecbuzzerCount(long secbuzzerCount) {
		this.secbuzzerCount = secbuzzerCount;
	}
	@Column(name = "CiLevel")
	public String getCiLevel() {
		return ciLevel;
	}
	public void setCiLevel(String ciLevel) {
		this.ciLevel = ciLevel;
	}
	
	@Column(name = "CreateTime", updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "SignApplyTime", updatable = false)
	public Date getSignApplyTime() {
		return signApplyTime;
	}
	public void setSignApplyTime(Date signApplyTime) {
		this.signApplyTime = signApplyTime;
	}
	
	
	@Column(name = "ReportCreateTime")
	public Date getReportCreateTime() {
		return reportCreateTime;
	}
	public void setReportCreateTime(Date reportCreateTime) {
		this.reportCreateTime = reportCreateTime;
	}
	
	@Column(name = "MemberLog")
	public String getMemberLog() {
		return memberLog;
	}
	public void setMemberLog(String memberLog) {
		this.memberLog = memberLog;
	}
	
	
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	
	
	
}