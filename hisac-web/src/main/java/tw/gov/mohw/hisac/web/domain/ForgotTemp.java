package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "forgot_temp", uniqueConstraints = {@UniqueConstraint(columnNames = "Code")})
public class ForgotTemp {
	private @NotNull String code;
	private @NotNull Long memberId;
	private @NotNull Date expireTime;

	@Id
	@Column(name = "Code", nullable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "MemberId", nullable = false)
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Column(name = "ExpireTime", nullable = false)
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
