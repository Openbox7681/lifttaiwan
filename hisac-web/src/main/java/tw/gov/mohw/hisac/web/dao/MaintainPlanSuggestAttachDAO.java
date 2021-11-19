package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestAttach;

public interface MaintainPlanSuggestAttachDAO {
	public void insert(MaintainPlanImprovementSuggestAttach entity);
	public void update(MaintainPlanImprovementSuggestAttach entity);	
	public MaintainPlanImprovementSuggestAttach getByMaintainPlanId(Long maintainPlanId);
	public void deleteByMaintainPlanId(long maintainPlanId);
}
