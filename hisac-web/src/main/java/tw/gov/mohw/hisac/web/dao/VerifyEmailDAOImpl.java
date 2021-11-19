package tw.gov.mohw.hisac.web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.VerifyEmail;

@Repository
@Transactional
public class VerifyEmailDAOImpl extends BaseSessionFactory implements VerifyEmailDAO {

	public void insert(VerifyEmail entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void delete(VerifyEmail entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public VerifyEmail get(String email) {
		return getSessionFactory().getCurrentSession().get(VerifyEmail.class, email);
	}
}
