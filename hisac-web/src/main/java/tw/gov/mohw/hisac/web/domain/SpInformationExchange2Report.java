package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_inforamtion_exchange_report")
public class SpInformationExchange2Report {
	
	private String sourceCode;
	private String name;
	private Long edit;
	private Long del;
	private Long sign;
	private Long pub;
	private Long alert;
	private Long nisac;
	private Long total;
	
	@Id
	@Column(name="SourceCode")
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="Edit")
	public Long getEdit() {
		return edit;
	}
	public void setEdit(Long edit) {
		this.edit = edit;
	}
	
	@Column(name="Del")
	public Long getDel() {
		return del;
	}
	public void setDel(Long del) {
		this.del = del;
	}
	
	@Column(name="Sign")
	public Long getSign() {
		return sign;
	}
	public void setSign(Long sign) {
		this.sign = sign;
	}
	
	@Column(name="Pub")
	public Long getPub() {
		return pub;
	}
	public void setPub(Long pub) {
		this.pub = pub;
	}
	
	@Column(name="Alert")
	public Long getAlert() {
		return alert;
	}
	public void setAlert(Long alert) {
		this.alert = alert;
	}
	
	@Column(name="Nisac")
	public Long getNisac() {
		return nisac;
	}
	public void setNisac(Long nisac) {
		this.nisac = nisac;
	}
	
	@Column(name="Total")
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
