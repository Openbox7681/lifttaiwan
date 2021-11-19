package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message_post_release", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class MessagePostRelease {
	private String id;
	
	private String messageId;

	private Long orgId;
	private String replyContent;
	private Boolean isCC;
	private Boolean isReview;
	private String status;
	private Integer transferOutType;
	private String transferOutId;
	private String replyOption;
	
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
	private @NotNull Date modifyTime;

	@Id
	@Column(name = "Id", nullable = false, updatable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Column(name = "MessageId")
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	@Column(name = "ReplyContent")
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "IsCC")
	public Boolean getIsCC() {
		return isCC;
	}
	public void setIsCC(Boolean isCC) {
		this.isCC = isCC;
	}
	
	@Column(name = "IsReview")
	public Boolean getIsReview() {
		return isReview;
	}
	public void setIsReview(Boolean isReview) {
		this.isReview = isReview;
	}
	
	@Column(name = "Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "TransferOutType")
	public Integer getTransferOutType() {
		return transferOutType;
	}
	public void setTransferOutType(Integer transferOutType) {
		this.transferOutType = transferOutType;
	}
	
	@Column(name = "TransferOutId")
	public String getTransferOutId() {
		return transferOutId;
	}
	public void setTransferOutId(String transferOutId) {
		this.transferOutId = transferOutId;
	}
	
	@Column(name = "ReplyOption")
	public String getReplyOption() {
		return replyOption;
	}
	public void setReplyOption(String replyOption) {
		this.replyOption = replyOption;
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

	public void setCreateTime(Date createTime) {
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
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}