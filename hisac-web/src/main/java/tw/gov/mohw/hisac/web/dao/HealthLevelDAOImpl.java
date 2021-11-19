package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.HealthLevel;;

@Repository
@Transactional
public class HealthLevelDAOImpl extends BaseSessionFactory implements HealthLevelDAO {

	public void insert(HealthLevel role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(HealthLevel role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(HealthLevel role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public HealthLevel get(Long id) {
		return (HealthLevel) getSessionFactory().getCurrentSession().get(HealthLevel.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<HealthLevel> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HealthLevel.class);
		List<HealthLevel> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(String json) {
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HealthLevel.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<HealthLevel> getList(String json) {
		JSONObject obj = new JSONObject(json);

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 10 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		// long createId = obj.isNull("CreateId") == true ? 0 :
		// obj.getLong("CreateId");
		// long modifyId = obj.isNull("ModifyId") == true ? 0 :
		// obj.getLong("ModifyId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HealthLevel.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<HealthLevel> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
