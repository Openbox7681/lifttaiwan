package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestAttach;


/**
 * 資安維護計畫子系統-共同維護計畫摘要附加檔案處理
 */
@Repository
@Transactional
public class MaintainPlanSuggestAttachDAOImpl extends BaseSessionFactory implements MaintainPlanSuggestAttachDAO{
	

	public void insert(MaintainPlanImprovementSuggestAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);		
	}
	
	public void update(MaintainPlanImprovementSuggestAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanImprovementSuggestAttach getByMaintainPlanId(Long maintainPlanId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanImprovementSuggestAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<MaintainPlanImprovementSuggestAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanId(long maintainPlanId) {
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanImprovementSuggestAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<MaintainPlanImprovementSuggestAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));		
	}

	
		
	
}
