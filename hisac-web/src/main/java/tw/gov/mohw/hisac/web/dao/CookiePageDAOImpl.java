package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.CookiePage;

/**
 * 隱私權聲明修改功能
 */
@Repository
@Transactional
public class CookiePageDAOImpl extends BaseSessionFactory implements CookiePageDAO {

	public void insert(CookiePage entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(CookiePage entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}


	public CookiePage get(Long id) {
		return getSessionFactory().getCurrentSession().get(CookiePage.class, id);
	}

	

	

}
