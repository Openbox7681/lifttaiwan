package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.Form;
import tw.gov.mohw.hisac.web.domain.ViewFormName;
import tw.gov.mohw.hisac.web.domain.ViewFormSubsystem;

/**
 * 表單服務
 */
@Repository
@Transactional
public class FormDAOImpl extends BaseSessionFactory implements FormDAO {

	public void insert(Form entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Form entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Form entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Form get(Long id) {
		return getSessionFactory().getCurrentSession().get(Form.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewFormName> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long subsystemId = obj.isNull("SubsystemId") == true ? 0 : obj.getLong("SubsystemId");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isExternalLink = obj.isNull("IsExternalLink") == true ? null : obj.getBoolean("IsExternalLink");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewFormName.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (subsystemId != 0) {
			cr.add(Restrictions.eq("subsystemId", subsystemId));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (isExternalLink != null) {
			cr.add(Restrictions.eq("isExternalLink", isExternalLink));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isShow != null) {
			cr.add(Restrictions.eq("isShow", isShow));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewFormName> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long subsystemId = obj.isNull("SubsystemId") == true ? 0 : obj.getLong("SubsystemId");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isExternalLink = obj.isNull("IsExternalLink") == true ? null : obj.getBoolean("IsExternalLink");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Form.class);
		cr.setProjection(Projections.rowCount());

		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (subsystemId != 0) {
			cr.add(Restrictions.eq("subsystemId", subsystemId));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (isExternalLink != null) {
			cr.add(Restrictions.eq("isExternalLink", isExternalLink));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isShow != null) {
			cr.add(Restrictions.eq("isShow", isShow));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Form> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Form.class);
		List<Form> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public Form getByCAName(String controllerName, String actionName) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Form.class);
		cr.add(Restrictions.eq("controllerName", controllerName).ignoreCase());
		cr.add(Restrictions.eq("actionName", actionName).ignoreCase());
		return (Form) cr.uniqueResult();
	}
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Form> getBySubsystemId(long subsystemId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Form.class);
		cr.add(Restrictions.eq("subsystemId", subsystemId));
		List<Form> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewFormSubsystem> getFormAndSubsystem() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewFormSubsystem.class);
		cr.addOrder(Order.asc("subsystemId"));
		List<ViewFormSubsystem> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
