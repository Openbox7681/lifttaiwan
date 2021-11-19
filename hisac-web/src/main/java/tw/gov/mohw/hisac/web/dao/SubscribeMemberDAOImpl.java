package tw.gov.mohw.hisac.web.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.SubscribeMember;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;

@Repository
@Transactional
public class SubscribeMemberDAOImpl extends BaseSessionFactory implements SubscribeMemberDAO {

	public void insert(SubscribeMember entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void delete(SubscribeMember entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	public SubscribeMember get(Long id) {
		return getSessionFactory().getCurrentSession().get(SubscribeMember.class, id);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<SubscribeMember> getByMemberId(long memberId) {		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SubscribeMember.class);
		cr.add(Restrictions.eq("memberId", memberId));		
		List<SubscribeMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewSubscribeMember> getBySubscribeName(String subscribeName) {		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewSubscribeMember.class);
		cr.add(Restrictions.eq("subscribeName", subscribeName));		
		List<ViewSubscribeMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}