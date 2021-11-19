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
@Table(name = "message_recipient", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MessageRecipient {

	private Long id;	
	private @NotNull String name;
	private @NotNull String email;	
	private @NotNull String mobilePhone;
	private Long createId;
	private @NotNull Date createTime;
	private Long modifyId;
	private @NotNull Date modifyTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "MobilePhone", nullable = false)
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(@NotNull String mobilePhone) {		
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "CreateId", nullable = false, updatable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(@NotNull Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ModifyId", nullable = false)
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "ModifyTime", nullable = false)
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(@NotNull Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
