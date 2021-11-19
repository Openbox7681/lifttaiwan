package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListAttach;

public interface MaintainPlanCheckListAttachDAO {
	public void insert(MaintainPlanCheckListAttach entity);	
	public MaintainPlanCheckListAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
