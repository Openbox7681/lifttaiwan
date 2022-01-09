package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sna_info_lift")
public class SnaInfoLift {
	private Long id;
	private String class_sub;
	private String name_1;
	private String name_2;
	private String paper_serial_number;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClass_sub() {
		return class_sub;
	}
	public void setClass_sub(String class_sub) {
		this.class_sub = class_sub;
	}
	public String getName_1() {
		return name_1;
	}
	public void setName_1(String name_1) {
		this.name_1 = name_1;
	}
	
	public String getName_2() {
		return name_2;
	}
	public void setName_2(String name_2) {
		this.name_2 = name_2;
	}
	
	public String getPaper_serial_number() {
		return paper_serial_number;
	}
	public void setPaper_serial_number(String paper_serial_number) {
		this.paper_serial_number = paper_serial_number;
	}
	
	
	
}
