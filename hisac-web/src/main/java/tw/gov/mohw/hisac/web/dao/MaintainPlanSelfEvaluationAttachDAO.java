package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationAttach;

public interface MaintainPlanSelfEvaluationAttachDAO {
	public void insert(MaintainPlanSelfEvaluationAttach entity);	
	public MaintainPlanSelfEvaluationAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
