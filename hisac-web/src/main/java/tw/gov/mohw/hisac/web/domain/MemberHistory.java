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
@Table(name = "member_history", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MemberHistory {

	private Long id;
	private Long memberId;
	private @NotNull String password;
	private @NotNull String salt;
	private @NotNull Short errorCount;
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

	@Column(name = "MemberId", nullable = false)
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Column(name = "Password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(@NotNull String password) {
		this.password = password;
	}

	@Column(name = "Salt", nullable = false, updatable = false)
	public String getSalt() {
		return salt;
	}
	public void setSalt(@NotNull String salt) {
		this.salt = salt;
	}

	@Column(name = "ErrorCount", nullable = false)
	public Short getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Short errorCount) {
		this.errorCount = errorCount;
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
