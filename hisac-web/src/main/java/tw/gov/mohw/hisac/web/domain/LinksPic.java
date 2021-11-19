package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "links_pic", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class LinksPic {
	private Long id;
	private @NotNull Long linksId; 			// 相關連結文章Id
	private @NotNull String fileName;		// 檔案名稱
	private @NotNull String fileType;		// 檔案類型
	private @NotNull byte[] fileContent;	// 檔案內容
	private @NotNull String fileHash;		// 檔案雜湊值
	private @NotNull Long fileSize;			// 檔案大小
	private @NotNull Long imageWidth;		// 圖檔寬
	private @NotNull Long imageHeight;		// 圖檔高
	private String fileDesc;				// 檔案說明
	private @NotNull Long createId;
	private @NotNull Date createTime;
	private @NotNull Long modifyId;
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

	@Column(name = "LinksId", nullable = false)
	public Long getLinksId() {
		return linksId;
	}
	public void setLinksId(Long linksId) {
		this.linksId = linksId;
	}
	
	@Column(name = "FileName", nullable = false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FileType", nullable = false)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "FileContent", nullable = false)
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	@Column(name = "FileHash", nullable = false)
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	@Column(name = "FileSize", nullable = false)
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

	@Column(name = "FileDesc", nullable = false)
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
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
