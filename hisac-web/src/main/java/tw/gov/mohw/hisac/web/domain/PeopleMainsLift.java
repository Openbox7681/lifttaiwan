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
@Table(name = "people_mains_lift")
public class PeopleMainsLift {
	private Long id;
	private @NotNull String p_id;
	private @NotNull String identify; 
	private @NotNull String inout_class;
	private Long year;
	private String country;
	private String conutry_name;
	private String region;
	private String class_main;
	private String class_sub;
	private String affiliations_in_cor_c;
	private String affiliations_cor_c;
	private String affiliations_cor_e;

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
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getInout_class() {
		return inout_class;
	}
	public void setInout_class(String inout_class) {
		this.inout_class = inout_class;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getConutry_name() {
		return conutry_name;
	}
	public void setConutry_name(String conutry_name) {
		this.conutry_name = conutry_name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	public String getAffiliations_in_cor_c() {
		return affiliations_in_cor_c;
	}
	public void setAffiliations_in_cor_c(String affiliations_in_cor_c) {
		this.affiliations_in_cor_c = affiliations_in_cor_c;
	}
	public String getAffiliations_cor_c() {
		return affiliations_cor_c;
	}
	public void setAffiliations_cor_c(String affiliations_cor_c) {
		this.affiliations_cor_c = affiliations_cor_c;
	}
	public String getAffiliations_cor_e() {
		return affiliations_cor_e;
	}
	public void setAffiliations_cor_e(String affiliations_cor_e) {
		this.affiliations_cor_e = affiliations_cor_e;
	}

}
