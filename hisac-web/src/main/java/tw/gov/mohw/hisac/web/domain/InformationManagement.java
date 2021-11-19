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
@Table(name = "information_management", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class InformationManagement {

	private Long id;	
	private @NotNull Date postDateTime;
	private @NotNull String title;
	private String sourceName;
	private String sourceLink;	
	private String content;	
	private Boolean isBreakLine;
	private Boolean isEnable;	
	private Long createId;
	private Date createTime;
	private Long modifyId;
	private Date modifyTime;
	private Long status;	
	private @NotNull String titleEdit;
	private String sourceNameEdit;
	private String sourceLinkEdit;	
	private String contentEdit;
	private String remark;
	private String name;
	private String email;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	

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

	@Column(name = "Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "IsBreakLine")
	public Boolean getIsBreakLine() {
		return isBreakLine;
	}
	public void setIsBreakLine(Boolean isBreakLine) {
		this.isBreakLine = isBreakLine;
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

	@Column(name = "Status")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	@Column(name = "TitleEdit", nullable = false)
	public String getTitleEdit() {
		return titleEdit;
	}
	public void setTitleEdit(@NotNull String titleEdit) {
		this.titleEdit = titleEdit;
	}

	@Column(name = "SourceNameEdit")
	public String getSourceNameEdit() {
		return sourceNameEdit;
	}
	public void setSourceNameEdit(String sourceNameEdit) {
		this.sourceNameEdit = sourceNameEdit;
	}

	@Column(name = "SourceLinkEdit")
	public String getSourceLinkEdit() {
		return sourceLinkEdit;
	}
	public void setSourceLinkEdit(String sourceLinkEdit) {
		this.sourceLinkEdit = sourceLinkEdit;
	}

	@Column(name = "ContentEdit")
	public String getContentEdit() {
		return contentEdit;
	}
	public void setContentEdit(String contentEdit) {
		this.contentEdit = contentEdit;
	}
	
	@Column(name = "Remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}