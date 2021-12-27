package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "v_ttmax_info_lift")
public class ViewTtmaxInfoLift {
	private Long id;
	private String class_sub;
	private String class_main;
	private Float index_x;
	private Float index_y;
	private Float paper_total_num;

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
	
	public String getClass_main() {
		return class_main;
	}
	public void setClass_main(String class_main) {
		this.class_main = class_main;
	}
	
	public Float getIndex_x() {
		return index_x;
	}
	public void setIndex_x(Float index_x) {
		this.index_x = index_x;
	}
	public Float getIndex_y() {
		return index_y;
	}
	public void setIndex_y(Float index_y) {
		this.index_y = index_y;
	}
	public Float getPaper_total_num() {
		return paper_total_num;
	}
	public void setPaper_total_num(Float paper_total_num) {
		this.paper_total_num = paper_total_num;
	}
}
