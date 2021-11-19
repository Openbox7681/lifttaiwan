package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanReviewOpinionAttach;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class MaintainPlanReviewOpinionAttachDAOImpl extends BaseSessionFactory implements MaintainPlanReviewOpinionAttachDAO {

	public void insert(MaintainPlanReviewOpinionAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	public void update(MaintainPlanReviewOpinionAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}
	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanReviewOpinionAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId,Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanReviewOpinionAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanReviewOpinionAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanReviewOpinionAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanReviewOpinionAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanReviewOpinionAttach getByMaintainPlanIdAndGroupIdAndCommitteeId(Long maintainPlanId,Long groupId,Long committeeId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanReviewOpinionAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		cr.add(Restrictions.eq("committeeId", committeeId));
		List<MaintainPlanReviewOpinionAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteByMaintainPlanIdAndGroupIdAndCommitteeId(long maintainPlanId, long groupId,Long committeeId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanReviewOpinionAttach.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));		
		cr.add(Restrictions.eq("groupId", groupId));
		cr.add(Restrictions.eq("committeeId", committeeId));
		List<MaintainPlanReviewOpinionAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MaintainPlanReviewOpinionAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanReviewOpinionAttach.class);		
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanReviewOpinionAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	public MaintainPlanReviewOpinionAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainPlanReviewOpinionAttach.class, id);
	}
}
