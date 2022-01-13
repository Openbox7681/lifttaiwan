package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "v_people_paper")
public class ViewPeoplePaper {
	private Long id;
	private String p_id;
	private String paper_SerialNumber;
	private String paperTitle;
	private Long publishYear;
	private String paper_corID;
	private String identify; 
	private Long years;

	
	
	private String class_main;
	private String class_sub;
	private String country;
	private String country_name;

	

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
	public Long getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(Long publishYear) {
		this.publishYear = publishYear;
	}
	public String getPaper_corID() {
		return paper_corID;
	}
	public void setPaper_corID(String paper_corID) {
		this.paper_corID = paper_corID;
	}
	public String getClass_main() {
		return class_main;
	}
	public void setClass_main(String class_main) {
		this.class_main = class_main;
	}
	
	public String getClass_sub() {
		return class_sub;
	}
	public void setClass_sub(String class_sub) {
		this.class_sub = class_sub;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public Long getYears() {
		return years;
	}
	public void setYears(Long years) {
		this.years = years;
	}

}
