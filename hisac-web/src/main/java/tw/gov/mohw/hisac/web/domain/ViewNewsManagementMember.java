package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="v_news_management_member")
public class ViewNewsManagementMember {

	private Long id;
	private String postType;
	private Long newsManagementGroupId;
	private String newsManagementGroupName;
	private Date postDateTime;
	private String title;
	private String sourceName;
	private String sourceLink;
	private String contentType;
	private String content;
	private String externalLink;
	private Boolean isBreakLine;
	private Date startDateTime;
	private Date endDateTime;
	private Boolean isEnable;
	private Boolean isPublic;
	private Long createId;
	private String createName;
	private Date createTime;
	private Long modifyId;
	private String modifyName;
	private Date modifyTime;
	private Long status;
	private String postId;
	
	private Long sort;

	private  String transferInId;
	private  @NotNull Integer transferInType=0;
	private  String transferOutId;
	private  @NotNull Integer transferOutType=0;
	
	@Id
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "PostType")
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}

	@Column(name = "NewsManagementGroupId")
	public Long getNewsManagementGroupId() {
		return newsManagementGroupId;
	}
	public void setNewsManagementGroupId(Long newsManagementGroupId) {
		this.newsManagementGroupId = newsManagementGroupId;
	}
	
	@Column(name = "NewsManagementGroupName")
	public String getNewsManagementGroupName() {
		return newsManagementGroupName;
	}
	public void setNewsManagementGroupName(String newsManagementGroupName) {
		this.newsManagementGroupName = newsManagementGroupName;
	}

	@Column(name = "PostDateTime")
	public Date getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(Date postDateTime) {
		this.postDateTime = postDateTime;
	}
	
	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SourceName")
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name = "SourceLink")
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	@Column(name = "ContentType")
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ExternalLink")
	public String getExternalLink() {
		return externalLink;
	}
	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	@Column(name = "IsBreakLine", nullable = false)
	public Boolean getIsBreakLine() {
		return isBreakLine;
	}
	public void setIsBreakLine(Boolean isBreakLine) {
		this.isBreakLine = isBreakLine;
	}

	@Column(name = "StartDateTime")
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	@Column(name = "EndDateTime")
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Column(name = "IsEnable")
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	@Column(name = "IsPublic")
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Column(name = "CreateId", updatable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateName")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "CreateTime", updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ModifyId")
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "ModifyName")
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	@Column(name = "ModifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "Status")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "Sort")
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}

	@Column(name = "TransferInType", nullable = false)
	public Integer getTransferInType() {
		return transferInType;
	}
	public void setTransferInType(Integer transferInType) {
		this.transferInType = transferInType;
	}

	@Column(name = "TransferInId")
	public String getTransferInId() {
		return transferInId;
	}
	public void setTransferInId(String transferInId) {
		this.transferInId = transferInId;
	}

	@Column(name = "TransferOutType", nullable = false)
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

}
