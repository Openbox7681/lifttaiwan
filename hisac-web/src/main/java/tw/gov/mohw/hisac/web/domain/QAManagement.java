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
@Table(name="qa_management",uniqueConstraints= {@UniqueConstraint(columnNames="Id")})
public class QAManagement {
	
	private Long id;
	private String qName;
	private String aName;
	private Long qaMgId;
	private Boolean isEnable;
	private Long createId;
	private Date createTime;
	private Long modifyId;
	private Date modifyTime;
	private Boolean isPublic;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", nullable=false, updatable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="QName")
	public String getQName() {
		return qName;
	}
	public void setQName(String name) {
		this.qName = name;
	}
	
	@Column(name="AName")
	public String getAName() {
		return aName;
	}
	public void setAName(String name) {
		this.aName = name;
	}
	
	@Column(name="QAManagementGroupId")
	public Long getQAMgId() {
		return qaMgId;
	}
	public void setQAMgId(Long qaMgId) {
		this.qaMgId = qaMgId;
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
	
}
