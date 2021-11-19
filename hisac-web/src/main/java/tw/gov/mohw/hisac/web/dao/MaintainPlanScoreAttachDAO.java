package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MaintainPlanScoreAttach;;

public interface MaintainPlanScoreAttachDAO {
	public void insert(MaintainPlanScoreAttach entity);	
	public void update(MaintainPlanScoreAttach entity);
	public MaintainPlanScoreAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
	public MaintainPlanScoreAttach getByMaintainPlanIdAndGroupIdAndCommitteeId(Long maintainPlanId, Long groupId,Long committeeId);
	public void deleteByMaintainPlanIdAndGroupIdAndCommitteeId(long maintainPlanId, long groupId,Long committeeId);
	public List<MaintainPlanScoreAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public MaintainPlanScoreAttach get(Long id);

}
