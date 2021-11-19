package tw.gov.mohw.hisac.web.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.Sso;

@Repository
@Transactional
public class SsoDAOImpl extends BaseSessionFactory implements SsoDAO {

	public void insert(Sso entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void delete(Sso entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Sso get(String code) {
		return getSessionFactory().getCurrentSession().get(Sso.class, code);
	}

	@SuppressWarnings("deprecation")
	public Sso getByMemberId(Long memberId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Sso.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.addOrder(Order.desc("id"));
		cr.setMaxResults(1);
		return (Sso) cr.uniqueResult();
	}
}
