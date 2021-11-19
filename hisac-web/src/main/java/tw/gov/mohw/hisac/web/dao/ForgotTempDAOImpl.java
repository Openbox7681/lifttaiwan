package tw.gov.mohw.hisac.web.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.ForgotTemp;

@Repository
@Transactional
public class ForgotTempDAOImpl extends BaseSessionFactory implements ForgotTempDAO {

	public void insert(ForgotTemp entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void delete(ForgotTemp entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public ForgotTemp get(String code) {
		return getSessionFactory().getCurrentSession().get(ForgotTemp.class, code);
	}

	@SuppressWarnings("deprecation")
	public ForgotTemp getByMemberId(Long memberId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ForgotTemp.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.addOrder(Order.desc("id"));
		cr.setMaxResults(1);
		return (ForgotTemp) cr.uniqueResult();
	}
}
