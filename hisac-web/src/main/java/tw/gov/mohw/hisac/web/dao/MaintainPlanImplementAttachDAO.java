package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImplementAttach;

public interface MaintainPlanImplementAttachDAO {
	public void insert(MaintainPlanImplementAttach entity);	
	public MaintainPlanImplementAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
