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
public class ViewWosClsPaper {
	private Long id;
	private String class_main;
	private String class_sub;
	private String field_c;
	private String field;
	private String p_id;
	private String country;	
	private String paper_corID;
	private Long ac;
	private String paper_SerialNumber;



	
	
	
	
	
	

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
	
	public String getField_c() {
		return field_c;
	}
	public void setField_c(String field_c) {
		this.field_c = field_c;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public Long getAc() {
		return ac;
	}
	public void setAc(Long ac) {
		this.ac = ac;
	}

}
