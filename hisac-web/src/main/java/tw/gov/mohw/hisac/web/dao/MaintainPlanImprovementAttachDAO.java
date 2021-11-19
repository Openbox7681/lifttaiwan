package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementAttach;

public interface MaintainPlanImprovementAttachDAO {
	public void insert(MaintainPlanImprovementAttach entity);	
	public MaintainPlanImprovementAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
