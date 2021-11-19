package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.Healthcare;
import tw.gov.mohw.hisac.web.domain.ViewHealthcare;

/**
 * 醫事機構代碼資料
 */
@Repository
@Transactional
public class HealthcareDAOImpl extends BaseSessionFactory implements HealthcareDAO {

	public void insert(Healthcare entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Healthcare entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Healthcare entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Healthcare get(String code) {
		return getSessionFactory().getCurrentSession().get(Healthcare.class, code);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Healthcare> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Healthcare.class);
		List<Healthcare> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// @SuppressWarnings("deprecation")
	// public Healthcare getByCode(String code) {
	// Criteria cr =
	// getSessionFactory().getCurrentSession().createCriteria(Healthcare.class);
	// cr.add(Restrictions.eq("code", code));
	// return (Healthcare) cr.uniqueResult();
	// }

	@SuppressWarnings("deprecation")
	public Healthcare getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Healthcare.class);
		cr.add(Restrictions.eq("name", name));
		return (Healthcare) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewHealthcare> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isCI = obj.isNull("IsCI") == true ? null : obj.getBoolean("IsCI");
		Long parentOrgId = obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
		Long healthLevelId = obj.isNull("HealthLevelId") == true ? null :obj.getLong("HealthLevelId");
		
		

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewHealthcare.class);
		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isCI != null) {
			if (isCI) {
				cr.add(Restrictions.eq("isCI", true));
			} else {
				cr.add(Restrictions.or(Restrictions.eq("isCI", false), Restrictions.isNull("isCI")));
			}
		}
		if (parentOrgId != null)
			cr.add(Restrictions.eq("parentOrgId", parentOrgId));
		
		if(healthLevelId !=null)
			cr.add(Restrictions.eq("healthLevelId", healthLevelId));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewHealthcare> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {

		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isCI = obj.isNull("IsCI") == true ? null : obj.getBoolean("IsCI");
		Long parentOrgId = obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Healthcare.class);
		cr.setProjection(Projections.rowCount());

		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isCI != null) {
			if (isCI) {
				cr.add(Restrictions.eq("isCI", true));
			} else {
				cr.add(Restrictions.or(Restrictions.eq("isCI", false), Restrictions.isNull("isCI")));
			}
		}
		if (parentOrgId != null)
			cr.add(Restrictions.eq("parentOrgId", parentOrgId));
		
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Healthcare> getList(String queryString, int perPage) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Healthcare.class);
		cr.add(Restrictions.or(Restrictions.like("code", "%" + queryString + "%"), Restrictions.like("name", "%" + queryString + "%")));
		if (perPage > 0) {
			cr.setFirstResult(0);
			cr.setMaxResults(perPage);
		}
		List<Healthcare> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
