package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanContent;



public interface MaintainPlanContentDAO {
	public void insert(MaintainPlanContent entity);	
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
