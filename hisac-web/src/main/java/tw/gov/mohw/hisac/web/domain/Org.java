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
@Table(name = "org", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Org {

	private Long id;
	private String name;
	private String code;
	private String orgType;
	private String authType;
	private String tel;
	private String fax;
	private String city;
	private String town;
	private String address;
	private String bossName;
	private String bossEmail;
	private String bossMobilePhone;
	private String principalName;
	private String principalMobilePhone;
	private Boolean isEnable;
	private Long createId;
	private Date createTime;
	private Long modifyId;
	private Date modifyTime;
	private Long healthLevelId;
	private Long securityLevel;
	private Boolean isPublic;
	private String apiKeyStatus;
	private String apiKey;
	private Date apiKeyExpiryDate;
	private Boolean isLocate;
	
	
	
	/*
	 * ciLevel = 0 (一般會員); ciLevel = 1 (進階會員); ciLevel = 2 (CI會員)
	 */
	private String ciLevel;
	
	
	private Boolean isApply;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Column(name = "Tel")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "Fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "Town")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	@Column(name = "Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BossName")
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	@Column(name = "BossEmail")
	public String getBossEmail() {
		return bossEmail;
	}
	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}

	@Column(name = "BossMobilePhone")
	public String getBossMobilePhone() {
		return bossMobilePhone;
	}
	public void setBossMobilePhone(String bossMobilePhone) {
		if (bossMobilePhone == null) {
			bossMobilePhone = "";
		}
		this.bossMobilePhone = bossMobilePhone;
	}
	
	@Column(name = "PrincipalName")
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		if (principalName == null) {
			principalName = "";
		}
		this.principalName = principalName;
	}

	@Column(name = "PrincipalMobilePhone")
	public String getPrincipalMobilePhone() {
		return principalMobilePhone;
	}
	public void setPrincipalMobilePhone(String principalMobilePhone) {
		if (principalMobilePhone == null) {
			principalMobilePhone = "";
		}
		this.principalMobilePhone = principalMobilePhone;
	}
	
	@Column(name = "IsEnable")
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
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

	@Column(name = "ModifyId")
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "ModifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "CiLevel")
	public String getCiLevel() {
		return ciLevel;
	}
	public void setCiLevel(String ciLevel) {
		this.ciLevel = ciLevel;
	}
	
	
	@Column(name = "IsApply")
	public Boolean getIsApply() {
		return isApply;
	}
	public void setIsApply(Boolean isApply) {
		this.isApply = isApply;
	}
	
	@Column(name = "HealthLevelId")
	public Long getHealthLevelId() {
		return healthLevelId;
	}
	public void setHealthLevelId(Long healthLevelId) {
		this.healthLevelId = healthLevelId;
	}
	
	@Column(name = "SecurityLevel")
	public Long getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(Long securityLevel) {
		this.securityLevel = securityLevel;
	}
	
	@Column(name = "IsPublic")
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	@Column(name = "ApiKeyStatus")
	public String getApiKeyStatus() {
		return apiKeyStatus;
	}
	public void setApiKeyStatus(String apiKeyStatus) {
		this.apiKeyStatus = apiKeyStatus;
	}
	
	@Column(name = "ApiKey")
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Column(name = "ApiKeyExpiryDate")
	public Date getApiKeyExpiryDate() {
		return apiKeyExpiryDate;
	}
	public void setApiKeyExpiryDate(Date apiKeyExpiryDate) {
		this.apiKeyExpiryDate = apiKeyExpiryDate;
	}
	
	@Column(name = "IsLocate")
	public Boolean getIsLocate() {
		return isLocate;
	}
	public void setIsLocate(Boolean isLocate) {
		this.isLocate = isLocate;
	}

	
	
	
}
