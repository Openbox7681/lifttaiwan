package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationAttach;

/**
 *稽核-自評表上傳
 */
@Repository
@Transactional
public class MaintainPlanSelfEvaluationAttachDAOImpl extends BaseSessionFactory implements MaintainPlanSelfEvaluationAttachDAO {

	public void insert(MaintainPlanSelfEvaluationAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanSelfEvaluationAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanSelfEvaluationAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanSelfEvaluationAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanSelfEvaluationAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanSelfEvaluationAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
