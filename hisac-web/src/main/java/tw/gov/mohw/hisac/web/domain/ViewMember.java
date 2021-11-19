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
@Table(name = "v_member", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewMember {
	private Long id;
	private Long orgId;
	private @NotNull String account;
	private @NotNull String name;
	private @NotNull String email;
	private String spareEmail;
	private String mobilePhone;
	private String cityPhone;
	private String faxPhone;
	private String address;
	private String department;
	private String title;
	private Boolean isEnable;
	private @NotNull Date enableTime;
	private @NotNull Short errorCount;
	private String orgName;
	private String code;
	private String orgType;
	private String authType;
	private @NotNull Date createTime;
	/*
	 * ciLevel = 0 (一般會員); ciLevel = 1 (進階會員); ciLevel = 2 (CI會員)
	 */
	private String ciLevel;

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

	@Column(name = "SpareEmail")
	public String getSpareEmail() {
		return spareEmail;
	}
	public void setSpareEmail(String spareEmail) {
		this.spareEmail = spareEmail;
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

	@Column(name = "CityPhone")
	public String getCityPhone() {
		return cityPhone;
	}
	public void setCityPhone(String cityPhone) {
		this.cityPhone = cityPhone;
	}

	@Column(name = "FaxPhone")
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	@Column(name = "Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "IsEnable", nullable = false)
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "EnableTime", nullable = false)
	public Date getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(@NotNull Date enableTime) {
		this.enableTime = enableTime;
	}

	@Column(name = "ErrorCount", nullable = false)
	public Short getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Short errorCount) {
		this.errorCount = errorCount;
	}
	
	@Column(name = "OrgName")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Column(name = "Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name = "CreateTime", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(@NotNull Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CiLevel")
	public String getCiLevel() {
		return ciLevel;
	}
	public void setCiLevel(String ciLevel) {
		this.ciLevel = ciLevel;
	}
}