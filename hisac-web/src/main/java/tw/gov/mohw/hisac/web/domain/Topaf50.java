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
@Table(name = "topaf50_new")
public class Topaf50 {

	private Long id;
	private String class_sub;
	private String affiliation_e;
	private String country;
	private @NotNull String tac;
	private @NotNull String con_num;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "Class_sub")
	public String getClass_sub() {
		return class_sub;
	}
	public void setClass_sub(String class_sub) {
		this.class_sub = class_sub;
	}

	@Column(name = "Affiliation_e")
	public String getAffiliation_e() {
		return affiliation_e;
	}
	public void setAffiliation_e(String affiliation_e) {
		this.affiliation_e = affiliation_e;
	}

	@Column(name = "Country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "Tac", nullable = false)
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}

	@Column(name = "Con_num", nullable = false)
	public String getCon_num() {
		return con_num;
	}
	public void setCon_num(String con_num) {
		this.con_num = con_num;
	}

	
}
