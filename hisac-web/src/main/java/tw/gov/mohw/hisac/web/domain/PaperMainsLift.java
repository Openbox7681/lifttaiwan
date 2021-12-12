package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paper_mains_lift")
public class PaperMainsLift {
	private Long id;
	private @NotNull String p_id;
	private @NotNull String paper_SerialNumber;
	private @NotNull String paperTitle;
	private @NotNull String publishYear;
	private @NotNull String paper_corID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getPaper_SerialNumber() {
		return paper_SerialNumber;
	}
	public void setPaper_SerialNumber(String paper_SerialNumber) {
		this.paper_SerialNumber = paper_SerialNumber;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public String getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}
	public String getPaper_corID() {
		return paper_corID;
	}
	public void setPaper_corID(String paper_corID) {
		this.paper_corID = paper_corID;
	}

}
