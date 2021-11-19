package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.SecurityLevelLog;
import tw.gov.mohw.hisac.web.domain.ViewSecurityLevelLog;


@Repository
@Transactional
public class SecurityLevelLogDAOImpl extends BaseSessionFactory implements SecurityLevelLogDAO {

	public void insert(SecurityLevelLog role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(SecurityLevelLog role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(SecurityLevelLog role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public SecurityLevelLog get(Long id) {
		return (SecurityLevelLog) getSessionFactory().getCurrentSession().get(SecurityLevelLog.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SecurityLevelLog> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SecurityLevelLog.class);
		
		cr.addOrder(Order.desc("createTime"));

		List<SecurityLevelLog> list = cr.list();
		

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
		
		String securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getString("SecurityLevel");
		long orgId = obj.isNull("orgId") == true ? 0 : obj.getLong("orgId");


		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SecurityLevelLog.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));

		}
		if (securityLevel != null) {
			cr.add(Restrictions.eq("securityLevel", securityLevel));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SecurityLevelLog> getList(String json) {
		JSONObject obj = new JSONObject(json);

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 10 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
		long orgId = obj.isNull("orgId") == true ? 0 : obj.getLong("orgId");

		// long createId = obj.isNull("CreateId") == true ? 0 :
		// obj.getLong("CreateId");
		// long modifyId = obj.isNull("ModifyId") == true ? 0 :
		// obj.getLong("ModifyId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SecurityLevelLog.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));

		}
		if (securityLevel != null) {
			cr.add(Restrictions.eq("securityLevel", securityLevel));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<SecurityLevelLog> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewSecurityLevelLog> getListByYear(String year) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewSecurityLevelLog.class);
		cr.add(Restrictions.eq("year", year));
		cr.addOrder(Order.asc("orgId"));


		List<ViewSecurityLevelLog> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
