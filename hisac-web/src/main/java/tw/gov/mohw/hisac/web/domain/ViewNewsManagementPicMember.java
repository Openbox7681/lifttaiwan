package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "v_news_management_pic_member", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class ViewNewsManagementPicMember {
	private Long id;
	private Long newsManagementId; // 最新消息文章Id
	private String fileName; // 檔案名稱
	private String fileType; // 檔案類型
	private String fileHash; // 檔案雜湊值
	private Long fileSize; // 檔案大小
	private Long imageWidth; // 檔案大小
	private Long imageHeight; // 檔案大小
	private String fileDesc; // 檔案說明
	private Long createId;
	private String createName;
	private Date createTime;
	private Long modifyId;
	private String modifyName;
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

	@Column(name = "NewsManagementId")
	public Long getNewsManagementId() {
		return newsManagementId;
	}
	public void setNewsManagementId(Long newsManagementId) {
		this.newsManagementId = newsManagementId;
	}

	@Column(name = "FileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FileType")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FileHash")
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	@Column(name = "FileSize")
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "ImageWidth", nullable = false)
	public Long getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(Long imageWidth) {
		this.imageWidth = imageWidth;
	}

	@Column(name = "ImageHeight", nullable = false)
	public Long getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(Long imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	@Column(name = "FileDesc")
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	@Column(name = "CreateId")
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

	@Column(name = "CreateTime")
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

}
