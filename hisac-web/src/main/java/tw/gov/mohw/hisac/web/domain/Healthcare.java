package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "healthcare", uniqueConstraints = {@UniqueConstraint(columnNames = "Code")})
public class Healthcare {

	private String code;
	private String name;
	private String city;
	private String town;
	private String address;
	private Boolean isCI;
	private Long parentOrgId;
	private Long healthLevelId;
	private Long securityLevel;
	private Boolean isPublic;
	

	@Id
	@Column(name = "Code", nullable = false, updatable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="Town")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	@Column(name="Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="IsCI")
	public Boolean getIsCI() {
		return isCI;
	}
	public void setIsCI(Boolean isCI) {
		this.isCI = isCI;
	}
	
	@Column(name="ParentOrgId")
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	
	@Column(name="HealthLevelId")
	public Long getHealthLevelId() {
		return healthLevelId;
	}
	public void setHealthLevelId(Long healthLevelId) {
		this.healthLevelId = healthLevelId;
	}
	
	@Column(name="SecurityLevel")
	public Long getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(Long securityLevel) {
		this.securityLevel = securityLevel;
	}
	
	@Column(name="IsPublic")
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	
}
