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
@Table(name="v_subscribe_member",uniqueConstraints= {@UniqueConstraint(columnNames="Id")})
public class ViewSubscribeMember {
	
	private Long id;
	private @NotNull Long subscribeId;
	private @NotNull Long memberId;	
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;
	private @NotNull String name;
	private @NotNull String email;
	private String spareEmail;
	private @NotNull String subscribeName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", nullable=false, updatable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="SubscribeId")
	public Long getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}
	@Column(name="MemberId")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	
	@Column(name = "SubscribeName", nullable = false)
	public String getSubscribeName() {
		return subscribeName;
	}
	public void setSubscribeName(@NotNull String subscribeName) {
		this.subscribeName = subscribeName;
	}
}