package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.QAManagement;
import tw.gov.mohw.hisac.web.domain.ViewQAManagementGroup;

@Repository
@Transactional
public class QAManagementDAOImpl extends BaseSessionFactory implements QAManagementDAO {

	public void insert(QAManagement entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(QAManagement entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(QAManagement entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public QAManagement get(Long id) {
		return getSessionFactory().getCurrentSession().get(QAManagement.class, id);
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<QAManagement> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagement.class);
		List<QAManagement> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<QAManagement> getAllMember() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagement.class);
		cr.add(Restrictions.eq("qaMgId", "1"));
		cr.add(Restrictions.eq("isEnable", true));
		List<QAManagement> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagement.class);
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (qaMgId != null && !qaMgId.isEmpty()) {
			cr.add(Restrictions.eq("qaMgId", qaMgId));
		}
		List<ViewQAManagementGroup> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId, String queryString, int perPage) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagement.class);
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (qaMgId != null && !qaMgId.isEmpty()) {
			cr.add(Restrictions.eq("qaMgId", qaMgId));
		}
		cr.add(Restrictions.or(Restrictions.like("code", "%" + queryString + "%"), Restrictions.like("name", "%" + queryString + "%")));
		if (perPage > 0) {
			cr.setFirstResult(0);
			cr.setMaxResults(perPage);
		}
		List<ViewQAManagementGroup> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewQAManagementGroup> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String qName = obj.isNull("QName") == true ? null : obj.getString("QName");
		String aName = obj.isNull("AName") == true ? null : obj.getString("AName");
		long qaMgId = obj.isNull("QAManagementGroupId") == true ? 0 : obj.getLong("QAManagementGroupId");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewQAManagementGroup.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (qName != null)
			cr.add(Restrictions.like("QName", "%" + qName + "%"));
		if (aName != null)
			cr.add(Restrictions.like("AName", "%" + aName + "%"));
		if (qaMgId != 0)
			cr.add(Restrictions.eq("QAMgId", qaMgId));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		if (isPublic != null)
			cr.add(Restrictions.eq("isPublic", isPublic));
		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("QName", "%" + keyword + "%"), Restrictions.like("AName", "%" + keyword + "%")));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		if (start != 0)
			cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);

		List<ViewQAManagementGroup> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		// String name = obj.isNull("Name") == true ? null :
		// obj.getString("Name");
		// Boolean isEnable = obj.isNull("IsEnable") == true ? null :
		// obj.getBoolean("IsEnable");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		String qaMgId = obj.isNull("qaMgId") == true ? null : obj.getString("qaMgId");

		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");		
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagement.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (qaMgId != null)
			cr.add(Restrictions.eq("qaMgId", qaMgId));

		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		if (isPublic != null)
			cr.add(Restrictions.eq("isPublic", isPublic));

		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("QName", "%" + keyword + "%"), Restrictions.like("AName", "%" + keyword + "%")));

		long total = (long) cr.list().get(0);
		return total;

	}

}
