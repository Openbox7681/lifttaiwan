package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.CiLevelLog;;


@Repository
@Transactional
public class CiLevelLogDAOImpl extends BaseSessionFactory implements CiLevelLogDAO {

	public void insert(CiLevelLog role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(CiLevelLog role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(CiLevelLog role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public CiLevelLog get(Long id) {
		return (CiLevelLog) getSessionFactory().getCurrentSession().get(CiLevelLog.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<CiLevelLog> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CiLevelLog.class);
		cr.addOrder(Order.desc("createTime"));
		List<CiLevelLog> list = cr.list();
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
		
		String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
		long orgId = obj.isNull("orgId") == true ? 0 : obj.getLong("orgId");


		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CiLevelLog.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));

		}
		if (ciLevel != null) {
			cr.add(Restrictions.like("ciLevel", "%" + ciLevel + "%"));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<CiLevelLog> getList(String json) {
		JSONObject obj = new JSONObject(json);

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 10 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
		long orgId = obj.isNull("orgId") == true ? 0 : obj.getLong("orgId");

		// long createId = obj.isNull("CreateId") == true ? 0 :
		// obj.getLong("CreateId");
		// long modifyId = obj.isNull("ModifyId") == true ? 0 :
		// obj.getLong("ModifyId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CiLevelLog.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));

		}
		if (ciLevel != null) {
			cr.add(Restrictions.like("ciLevel", "%" + ciLevel + "%"));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<CiLevelLog> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
