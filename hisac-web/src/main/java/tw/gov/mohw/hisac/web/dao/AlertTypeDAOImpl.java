package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.AlertType;

public class AlertTypeDAOImpl extends BaseSessionFactory implements AlertTypeDAO {

	@Transactional
	public void insert(AlertType alertType) {
		getSessionFactory().getCurrentSession().save(alertType);
	}

	@Transactional
	public void update(AlertType alertType) {
		getSessionFactory().getCurrentSession().update(alertType);
	}

	@Transactional
	public void delete(AlertType alertType) {
		getSessionFactory().getCurrentSession().delete(alertType);
	}

	@Transactional
	public AlertType get(Long id) {
		return (AlertType) getSessionFactory().getCurrentSession().get(AlertType.class, id);
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public AlertType getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AlertType.class);
		cr.add(Restrictions.eq("code", code));
		return (AlertType) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<AlertType> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AlertType.class);
		List<AlertType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	
	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<AlertType> getList() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AlertType.class);
		cr.add(Restrictions.eq("isEnable", true));
		// cr.add(Restrictions.ne("Code", "101"));
		cr.addOrder(Order.asc("sort"));
		List<AlertType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<AlertType> getList(String json) {
		JSONObject obj = new JSONObject(json);
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AlertType.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (code != null) {
			cr.add(Restrictions.like("code", "%" + code + "%"));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<AlertType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public long getListSize(String json) {
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AlertType.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (code != null) {
			cr.add(Restrictions.like("code", "%" + code + "%"));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		long total = (long) cr.list().get(0);
		return total;
	}
}
