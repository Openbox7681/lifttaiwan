package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paper_keyword_cls_lift")
public class PaperKeywordClsLift {
	private Long id;
	private String paper_SerialNumber;
	private String keyword;
	private String class_sub;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaper_SerialNumber() {
		return paper_SerialNumber;
	}
	public void setPaper_SerialNumber(String paper_SerialNumber) {
		this.paper_SerialNumber = paper_SerialNumber;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getClass_sub() {
		return class_sub;
	}
	public void setClass_sub(String class_sub) {
		this.class_sub = class_sub;
	}
	
	

}
