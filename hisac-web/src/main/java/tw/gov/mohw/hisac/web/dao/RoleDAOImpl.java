package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.Role;

@Repository
@Transactional
public class RoleDAOImpl extends BaseSessionFactory implements RoleDAO {

	public void insert(Role role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(Role role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(Role role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public Role get(Long id) {
		return (Role) getSessionFactory().getCurrentSession().get(Role.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Role> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Role.class);
		List<Role> list = cr.list();
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

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Role.class);
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
	public List<Role> getList(String json) {
		JSONObject obj = new JSONObject(json);

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		// long createId = obj.isNull("CreateId") == true ? 0 :
		// obj.getLong("CreateId");
		// long modifyId = obj.isNull("ModifyId") == true ? 0 :
		// obj.getLong("ModifyId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Role.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
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
		List<Role> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
