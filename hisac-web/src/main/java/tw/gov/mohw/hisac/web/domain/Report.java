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
@Table(name = "report", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Report {

	private Long id;
	private @NotNull String appName;
	private @NotNull String funcName;
	private @NotNull String inputValue;
	private @NotNull String actionName;
	private @NotNull String status;
	private @NotNull String ip;
	private @NotNull String hashCode;
	private @NotNull String createAccount;
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

	@Column(name = "AppName", nullable = false, updatable = false)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName.isEmpty() ? "" : appName;
	}

	@Column(name = "FuncName", nullable = false, updatable = false)
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName.isEmpty() ? "" : funcName;
	}

	@Column(name = "InputValue", nullable = false, updatable = false)
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue.isEmpty() ? "" : inputValue;
	}

	@Column(name = "ActionName", nullable = false, updatable = false)
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName.isEmpty() ? "" : actionName;
	}

	@Column(name = "Status", nullable = false, updatable = false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status.isEmpty() ? "" : status;
	}

	@Column(name = "Ip", nullable = false, updatable = false)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip.isEmpty() ? "" : ip;
	}

	@Column(name = "HashCode", nullable = false, updatable = false)
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@Column(name = "CreateAccount", nullable = false, updatable = false)
	public String getCreateAccount() {
		return createAccount;
	}
	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount.isEmpty() ? "" : createAccount;
	}

	@Column(name = "CreateTime", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(@NotNull Date createTime) {
		this.createTime = createTime;
	}
}
