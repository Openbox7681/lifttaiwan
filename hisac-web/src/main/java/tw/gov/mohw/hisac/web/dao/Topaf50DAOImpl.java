package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.Topaf50;

@Repository
@Transactional
public class Topaf50DAOImpl extends BaseSessionFactory implements Topaf50DAO {

	public void insert(Topaf50 role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(Topaf50 role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(Topaf50 role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public Topaf50 get(Long id) {
		return (Topaf50) getSessionFactory().getCurrentSession().get(Topaf50.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Topaf50> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topaf50.class);
		List<Topaf50> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	

}
