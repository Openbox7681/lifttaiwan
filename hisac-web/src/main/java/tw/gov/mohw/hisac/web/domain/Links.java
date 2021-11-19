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
@Table(name = "links", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class Links {

	private Long id;
	//private @NotNull String postType;
	private @NotNull Date postDateTime;
	private @NotNull String title;
	private String sourceName;
	private String sourceLink;
	//private String contentType;
	//private String content;
	//private String externalLink;
	private Boolean isBreakLine;
	private Date startDateTime;
	private Date endDateTime;
	private Boolean isEnable;
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
	
	//@Column(name = "PostType", nullable = false)
	//public String getPostType() {
	//	return postType;
	//}
	//public void setPostType(@NotNull String postType) {
	//	this.postType = postType;
	//}

	@Column(name = "PostDateTime", nullable = false)
	public Date getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(@NotNull Date postDateTime) {
		this.postDateTime = postDateTime;
	}
	
	@Column(name = "Title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(@NotNull String title) {
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
/*
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
*/
	@Column(name = "IsBreakLine")
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

	@Column(name = "CreateId", updatable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
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

	@Column(name = "ModifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
