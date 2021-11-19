package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanResultAttach;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class MaintainPlanResultAttachDAOImpl extends BaseSessionFactory implements MaintainPlanResultAttachDAO {

	public void insert(MaintainPlanResultAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanResultAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanResultAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanResultAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanResultAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanResultAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
