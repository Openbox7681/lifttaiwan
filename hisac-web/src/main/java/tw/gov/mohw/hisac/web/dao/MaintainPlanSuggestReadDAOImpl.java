package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestRead;




/**
 * 資安維護計畫子系統-共同維護計畫是否讀取資料記錄
 */
@Repository
@Transactional
public class MaintainPlanSuggestReadDAOImpl extends BaseSessionFactory implements MaintainPlanSuggestReadDAO{
	

	public void insert(MaintainPlanImprovementSuggestRead entity) {
		getSessionFactory().getCurrentSession().save(entity);		
	}
	
	public void update(MaintainPlanImprovementSuggestRead entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanImprovementSuggestRead getByMaintainPlanIdAndOrgIdAndAttachId(Long maintainPlanId, Long orgId,
			Long attachId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanImprovementSuggestRead.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("orgId", orgId));
		cr.add(Restrictions.eq("attachId", attachId));
		
		List<MaintainPlanImprovementSuggestRead> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MaintainPlanImprovementSuggestRead> getByMaintainPlanId(Long maintainPlanId) {
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanImprovementSuggestRead.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<MaintainPlanImprovementSuggestRead> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public void deleteByMaintainPlanIdAndAttachId(Long maintainPlanId, Long attachId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanImprovementSuggestRead.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<MaintainPlanImprovementSuggestRead> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));	
	}

	
	
		
	
}
