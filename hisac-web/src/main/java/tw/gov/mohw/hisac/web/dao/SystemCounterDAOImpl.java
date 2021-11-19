package tw.gov.mohw.hisac.web.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.SystemCounter;

@Repository
@Transactional
public class SystemCounterDAOImpl extends BaseSessionFactory implements SystemCounterDAO {
	public void insert(SystemCounter entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings("deprecation")
	public long getTotal() {
		long result = 0;
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemCounter.class);
		result = (long) cr.setProjection(Projections.rowCount()).uniqueResult();
		return result;
	}
}
