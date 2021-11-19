package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import tw.gov.mohw.hisac.web.domain.Subscribe;

@Repository
@Transactional
public class SubscribeDAOImpl extends BaseSessionFactory implements SubscribeDAO {

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<Subscribe> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Subscribe.class);
		List<Subscribe> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}