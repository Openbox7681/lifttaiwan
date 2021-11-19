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
@Table(name = "system_counter", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class SystemCounter {

	private Long id;
	private @NotNull String ip;
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

	@Column(name = "Ip", nullable = false, updatable = false)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip.isEmpty() ? "" : ip;
	}

	@Column(name = "CreateTime", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(@NotNull Date createTime) {
		this.createTime = createTime;
	}
}
