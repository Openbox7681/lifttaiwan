package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_org_report_result")
public class SpOrgReportResult {
	
	private long memberCount;
	private long signinCount;
	private long memberSigninCount;
	private long newsCount;
	private long activityCount;
	private long secbuzzerCount;
	private long anaCount;
	private Long orgId;
	private String name;
	private String ciLevel;
	private Date signApplyTime;


	@Id
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	@Column(name="MemberCount")
	public long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(long memberCount) {
		this.memberCount = memberCount;
	}
	
	@Column(name="SigninCount")
	public long getSigninCount() {
		return signinCount;
	}
	public void setSigninCount(long signinCount) {
		this.signinCount = signinCount;
	}
	
	@Column(name="MemberSigninCount")
	public long getMemberSigninCount() {
		return memberSigninCount;
	}
	public void setMemberSigninCount(long memberSigninCount) {
		this.memberSigninCount = memberSigninCount;
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
	
	@Column(name="SecbuzzerCount")
	public long getSecbuzzerCount() {
		return secbuzzerCount;
	}
	public void setSecbuzzerCount(long secbuzzerCount) {
		this.secbuzzerCount = secbuzzerCount;
	}
	
	
	@Column(name="AnaCount")
	public long getAnaCount() {
		return anaCount;
	}
	public void setAnaCount(long anaCount) {
		this.anaCount = anaCount;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "CiLevel")
	public String getCiLevel() {
		return ciLevel;
	}
	public void setCiLevel(String ciLevel) {
		this.ciLevel = ciLevel;
	}
	
	@Column(name = "SignApplyTime", updatable = false)
	public Date getSignApplyTime() {
		return signApplyTime;
	}
	public void setSignApplyTime(Date signApplyTime) {
		this.signApplyTime = signApplyTime;
	}
	
	
	
	
	
	
	
	
}