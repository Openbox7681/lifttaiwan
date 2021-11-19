package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanResultAttach;;

public interface MaintainPlanResultAttachDAO {
	public void insert(MaintainPlanResultAttach entity);	
	public MaintainPlanResultAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
