package tw.gov.mohw.hisac.web.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "v_inbound_people_paper")
public class ViewInboundPeoplePaper {
	private Long id;
	private String p_id;
	private String paper_SerialNumber; 
	private String paperTitle;
	private String publishYear;
	private String paper_corID;
	private Long years;

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
	public Long getYears() {
		return years;
	}
	public void setYears(Long years) {
		this.years = years;
	}
}
