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
@Table(name = "v_qamg_group", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class ViewQAManagementGroup {
	private Long id;
	private Long qaMgId;
	private @NotNull String qName;
	private @NotNull String aName;
	private Boolean isEnable;
	private Boolean isPublic;
	private String qaMgName;
	private Long createId;
	private Date createTime;
	private Long modifyId;
	private Date modifyTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "QAManagementGroupId")
	public Long getQAMgId() {
		return qaMgId;
	}
	public void setQAMgId(Long qaMgId) {
		this.qaMgId = qaMgId;
	}

	@Column(name = "QName", nullable = false)
	public String getQName() {
		return qName;
	}
	public void setQName(@NotNull String name) {
		this.qName = name;
	}

	@Column(name = "AName", nullable = false)
	public String getAName() {
		return aName;
	}
	public void setAName(@NotNull String name) {
		this.aName = name;
	}

	@Column(name="IsEnable")
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	@Column(name="IsPublic")
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}


	@Column(name="CreateId")
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name="CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="ModifyId")
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name="ModifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "QAMgName")
	public String getQAMgName() {
		return qaMgName;
	}
	public void setQAMgName(String name) {
		this.qaMgName = name;
	}
}