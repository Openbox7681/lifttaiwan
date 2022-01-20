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
@Table(name = "copyright")
public class CopyRight {

	private Long id;
	private String title;
	private String item1_1;
	private String item1_2;
	private String item1_3;

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "Item1_1")
	public String getItem1_1() {
		return item1_1;
	}
	public void setItem1_1(String item1_1) {
		this.item1_1 = item1_1;
	}
	
	@Column(name = "Item1_2")
	public String getItem1_2() {
		return item1_2;
	}
	public void setItem1_2(String item1_2) {
		this.item1_2 = item1_2;
	}
	
	@Column(name = "Item1_3")
	public String getItem1_3() {
		return item1_3;
	}
	public void setItem1_3(String item1_3) {
		this.item1_3 = item1_3;
	}
	

	
}
