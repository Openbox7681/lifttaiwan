//package tw.gov.mohw.hisac.web.domain;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "privacy_page")
//public class PrivacyPage {
//
//	private Long id;
//	private String title;
//	private String item1;
//	private String item2_1;
//	private String item2_2;
//	private String item2_3;
//	private String item2_4;
//	private String item2_5;
//	private String item3_1;
//	private String item3_2;
//	private String item4_1;
//	private String item4_2;
//	private String item4_3;
//	private String item4_4;
//	private String item4_5;
//	private String item5_1;
//
//
//	
//	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	@Column(name = "Title")
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	
//	@Column(name = "Item1")
//	public String getItem1() {
//		return item1;
//	}
//	public void setItem1(String item1) {
//		this.item1 = item1;
//	}
//	
//	@Column(name = "Item2_1")
//	public String getItem2_1() {
//		return item2_1;
//	}
//	public void setItem2_1(String item2_1) {
//		this.item2_1 = item2_1;
//	}
//	
//	@Column(name = "Item2_2")
//	public String getItem2_2() {
//		return item2_1;
//	}
//	public void setItem2_2(String item2_2) {
//		this.item2_2 = item2_2;
//	}
//	
//	@Column(name = "Item2_3")
//	public String getItem2_3() {
//		return item2_3;
//	}
//	public void setItem2_3(String item2_3) {
//		this.item2_3 = item2_3;
//	}
//	
//	@Column(name = "Item2_4")
//	public String getItem2_4() {
//		return item2_4;
//	}
//	public void setItem2_4(String item2_4) {
//		this.item2_4 = item2_4;
//	}
//	
//	@Column(name = "Item2_5")
//	public String getItem2_5() {
//		return item2_5;
//	}
//	public void setItem2_5(String item2_5) {
//		this.item2_5 = item2_5;
//	}
//	
//	@Column(name = "Item3_1")
//	public String getItem3_1() {
//		return item3_1;
//	}
//	public void setItem3_1(String item3_1) {
//		this.item3_1 = item3_1;
//	}
//	
//	@Column(name = "Item3_2")
//	public String getItem3_2() {
//		return item3_2;
//	}
//	public void setItem3_2(String item3_2) {
//		this.item3_2 = item3_2;
//	}
//	
//	@Column(name = "Item4_1")
//	public String getItem4_1() {
//		return item4_1;
//	}
//	public void setItem4_1(String item4_1) {
//		this.item4_1 = item4_1;
//	}
//	
//	@Column(name = "Item4_2")
//	public String getItem4_2() {
//		return item4_2;
//	}
//	public void setItem4_2(String item4_2) {
//		this.item4_2 = item4_2;
//	}
//	
//	@Column(name = "Item4_3")
//	public String getItem4_3() {
//		return item4_3;
//	}
//	public void setItem4_3(String item4_3) {
//		this.item4_3 = item4_3;
//	}
//	
//	@Column(name = "Item4_4")
//	public String getItem4_4() {
//		return item4_4;
//	}
//	public void setItem4_4(String item4_4) {
//		this.item4_4 = item4_4;
//	}
//	
//	@Column(name = "Item5_1")
//	public String getItem5_1() {
//		return item5_1;
//	}
//	public void setItem5_1(String item5_1) {
//		this.item5_1 = item5_1;
//	}
//	
//	
//	
//
//	
//}
