package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.EventType;

/**
 * 子系統服務
 */
@Repository
@Transactional
public class EventTypeDAOImpl extends BaseSessionFactory implements EventTypeDAO {

	public void insert(EventType entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(EventType entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(EventType entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public EventType get(Long id) {
		return getSessionFactory().getCurrentSession().get(EventType.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<EventType> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		List<EventType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public EventType getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		cr.add(Restrictions.eq("code", code));
		return (EventType) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public EventType getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		cr.add(Restrictions.eq("name", name));
		return (EventType) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<EventType> getByAlertCode(String alertCode) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		if (alertCode != null)
			cr.add(Restrictions.like("alertCode", "%" + alertCode + "%"));
		List<EventType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<EventType> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		// long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (alertCode != null)
			cr.add(Restrictions.like("alertCode", "%" + alertCode + "%"));
		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<EventType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<EventType> getAnaList() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		cr.add(Restrictions.eq("isEnable", true));
		cr.add(Restrictions.eq("alertCode", "ANA"));
		//cr.add(Restrictions.ne("code", "101"));
		cr.addOrder(Order.asc("id"));
		List<EventType> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(EventType.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (alertCode != null)
			cr.add(Restrictions.like("alertCode", "%" + alertCode + "%"));
		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));

		long total = (long) cr.list().get(0);
		return total;
	}
}
