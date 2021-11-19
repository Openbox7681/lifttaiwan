package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanAttach;

public interface MaintainPlanAttachDAO {
	public void insert(MaintainPlanAttach entity);	
	public MaintainPlanAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
