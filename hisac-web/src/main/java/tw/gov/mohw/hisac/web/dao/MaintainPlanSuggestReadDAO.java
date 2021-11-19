package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestRead;;


public interface MaintainPlanSuggestReadDAO {
	public void insert(MaintainPlanImprovementSuggestRead entity);	
	public void update(MaintainPlanImprovementSuggestRead entity);
	public MaintainPlanImprovementSuggestRead getByMaintainPlanIdAndOrgIdAndAttachId(Long maintainPlanId,Long orgId, Long attachId);
	public List<MaintainPlanImprovementSuggestRead> getByMaintainPlanId(Long maintainPlanId);
	public void deleteByMaintainPlanIdAndAttachId(Long maintainPlanId, Long attachId);
}
