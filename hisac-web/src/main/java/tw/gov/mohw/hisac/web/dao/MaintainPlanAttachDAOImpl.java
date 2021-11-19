package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanAttach;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class MaintainPlanAttachDAOImpl extends BaseSessionFactory implements MaintainPlanAttachDAO {

	public void insert(MaintainPlanAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
