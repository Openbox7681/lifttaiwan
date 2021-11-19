package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "v_form_subsystem", uniqueConstraints = {@UniqueConstraint(columnNames = "FormId")})
public class ViewFormSubsystem {
	private Long formId;
	private String formName;
	private Long subsystemId;
	private String subsystemName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FormId")
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}

	@Column(name = "FormName")
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	@Column(name = "SubsystemId")
	public Long getSubsystemId() {
		return subsystemId;
	}
	public void setSubsystemId(Long subsystemId) {
		this.subsystemId = subsystemId;
	}

	@Column(name = "SubsystemName")
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
}
