package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MaintainPlanItem;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContent;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContentFilter;



public interface MaintainPlanItemDAO {
	public void insert(MaintainPlanItem entity);
	public List<ViewMaintainPlanItemContent> getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
	public List<ViewMaintainPlanItemContentFilter> getListByMaintainPlanId(long maintainPlanId);	
}
