package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_message_group_org")
public class ViewMessageGroupOrg {
	
	private Long id;
	private Long messageGroupId;
	private Long orgId;
	private String name;
	//private Long flag;

	@Id
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "OrgId")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	@Column(name = "MessageGroupId")
	public Long getMessageGroupId() {
		return messageGroupId;
	}
	public void setMessageGroupId(Long messageGroupId) {
		this.messageGroupId = messageGroupId;
	}
	
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	@Column(name="Flag")
//	public Long getFlag() {
//		return flag;
//	}
//	public void setFlag(Long flag) {
//		this.flag = flag;
//	}
	
	
}
