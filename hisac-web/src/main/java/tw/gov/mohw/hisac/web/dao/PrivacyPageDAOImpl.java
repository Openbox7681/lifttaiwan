package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.PrivacyPage;

/**
 * 隱私權聲明修改功能
 */
@Repository
@Transactional
public class PrivacyPageDAOImpl extends BaseSessionFactory implements PrivacyPageDAO {

	public void insert(PrivacyPage entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(PrivacyPage entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}


	public PrivacyPage get(Long id) {
		return getSessionFactory().getCurrentSession().get(PrivacyPage.class, id);
	}

	

	

}
