package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MaintainPlanReviewOpinionAttach;;

public interface MaintainPlanReviewOpinionAttachDAO {
	public void insert(MaintainPlanReviewOpinionAttach entity);	
	public void update(MaintainPlanReviewOpinionAttach entity);
	public MaintainPlanReviewOpinionAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
	public MaintainPlanReviewOpinionAttach getByMaintainPlanIdAndGroupIdAndCommitteeId(Long maintainPlanId, Long groupId,Long committeeId);
	public void deleteByMaintainPlanIdAndGroupIdAndCommitteeId(long maintainPlanId, long groupId,Long committeeId);
	public List<MaintainPlanReviewOpinionAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public MaintainPlanReviewOpinionAttach get(Long id);

}
