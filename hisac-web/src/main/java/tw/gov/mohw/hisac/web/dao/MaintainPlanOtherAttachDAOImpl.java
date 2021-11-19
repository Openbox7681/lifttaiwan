package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanOtherAttach;

/**
 *稽核-自評表上傳
 */
@Repository
@Transactional
public class MaintainPlanOtherAttachDAOImpl extends BaseSessionFactory implements MaintainPlanOtherAttachDAO {

	public void insert(MaintainPlanOtherAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	
	public void delete(MaintainPlanOtherAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	public MaintainPlanOtherAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainPlanOtherAttach.class, id);
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanOtherAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanOtherAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanOtherAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MaintainPlanOtherAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanOtherAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanOtherAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanOtherAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanOtherAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
