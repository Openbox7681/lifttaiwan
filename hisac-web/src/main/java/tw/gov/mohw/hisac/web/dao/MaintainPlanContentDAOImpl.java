package tw.gov.mohw.hisac.web.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.MaintainPlanContent;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainPlanContentDAOImpl extends BaseSessionFactory implements MaintainPlanContentDAO {

	public void insert(MaintainPlanContent entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanContent.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanContent> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
