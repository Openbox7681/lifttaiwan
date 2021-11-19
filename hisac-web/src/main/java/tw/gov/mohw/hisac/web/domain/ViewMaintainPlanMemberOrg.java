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
@Table(name = "v_maintain_plan_member_org", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewMaintainPlanMemberOrg {

	private Long id;	
	private Integer year;
	private String title;
	private @NotNull Long maintainPlanId;		
	private @NotNull Long groupId;	
	private @NotNull Date sdate;
	private @NotNull Date edate;
	private String status;
	private @NotNull String orgName;
	private Long memberId;
	private String memberName;
	private Date replyTime;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "Year")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MaintainPlanId", nullable = false)
	public Long getMaintainPlanId() {
		return maintainPlanId;
	}
	public void setMaintainPlanId(Long maintainPlanId) {
		this.maintainPlanId = maintainPlanId;
	}	
	
	@Column(name = "GroupId", nullable = false)
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "Sdate", nullable = false)
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	@Column(name = "Edate", nullable = false)
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	@Column(name = "Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OrgName", nullable = false)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Column(name = "MemberId")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	@Column(name = "MemberName")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@Column(name = "ReplyTime")
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}
