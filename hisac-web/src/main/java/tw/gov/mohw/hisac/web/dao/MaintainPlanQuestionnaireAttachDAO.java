package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireAttach;

public interface MaintainPlanQuestionnaireAttachDAO {
	public void insert(MaintainPlanQuestionnaireAttach entity);	
	public MaintainPlanQuestionnaireAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId);
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
}
