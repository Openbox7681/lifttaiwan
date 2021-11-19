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
@Table(name = "maintain_plan_item", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id")})
public class MaintainPlanItem {

	private Long id;	
	private @NotNull Long maintainPlanId;	
	private Long parentId;	
	private @NotNull String title;	
	private @NotNull String des;	
	private @NotNull String format;		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MaintainPlanId", nullable = false)
	public Long getMaintainPlanId() {
		return maintainPlanId;
	}
	public void setMaintainPlanId(Long maintainPlanId) {
		this.maintainPlanId = maintainPlanId;
	}
	
	@Column(name = "ParentId")
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "Title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "Des", nullable = false)
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	@Column(name = "Format", nullable = false)
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}
