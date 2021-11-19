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
@Table(name = "v_member_sign_apply", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewMemberSignApply {

	private Long id;
	private Long orgId;
	private @NotNull String account;
	private @NotNull String name;
	private @NotNull String email;
	private String mobilePhone;
	private String orgName;
	private String orgType;
	private @NotNull Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "Account", nullable = false)
	public String getAccount() {
		return account;
	}
	public void setAccount(@NotNull String account) {
		this.account = account;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(@NotNull String name) {
		this.name = name;
	}

	@Column(name = "Email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(@NotNull String email) {
		this.email = email;
	}

	@Column(name = "MobilePhone")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		if (mobilePhone == null) {
			mobilePhone = "";
		}
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "OrgName")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "OrgType")
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
