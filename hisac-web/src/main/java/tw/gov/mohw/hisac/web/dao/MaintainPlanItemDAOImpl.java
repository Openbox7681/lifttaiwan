package tw.gov.mohw.hisac.web.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.MaintainPlanItem;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContent;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContentFilter;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainPlanItemDAOImpl extends BaseSessionFactory implements MaintainPlanItemDAO {

	public void insert(MaintainPlanItem entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewMaintainPlanItemContent> getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainPlanItemContent.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<ViewMaintainPlanItemContent> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewMaintainPlanItemContentFilter> getListByMaintainPlanId(long maintainPlanId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainPlanItemContentFilter.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));				
		List<ViewMaintainPlanItemContentFilter> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
