package tw.gov.mohw.hisac.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xp_signin_count_top10")
public class SpSigninCountTop10 {
	
	private String orgName;
	private long count;
		

	@Id
	@Column(name="OrgName")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="Count")
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}