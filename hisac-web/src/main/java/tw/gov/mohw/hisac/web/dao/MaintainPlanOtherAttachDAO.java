package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MaintainPlanOtherAttach;

public interface MaintainPlanOtherAttachDAO {
	public void insert(MaintainPlanOtherAttach entity);	
	public MaintainPlanOtherAttach get(Long id);
	public void delete(MaintainPlanOtherAttach entity);
	public MaintainPlanOtherAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public List<MaintainPlanOtherAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
