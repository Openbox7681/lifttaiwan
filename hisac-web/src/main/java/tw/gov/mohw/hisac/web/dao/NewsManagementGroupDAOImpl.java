package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementGroupMember;

/**
 * 最新消息類別管理
 */
@Repository
@Transactional
public class NewsManagementGroupDAOImpl extends BaseSessionFactory implements NewsManagementGroupDAO {

	public void insert(NewsManagementGroup entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(NewsManagementGroup entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(NewsManagementGroup entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public NewsManagementGroup get(Long id) {
		return getSessionFactory().getCurrentSession().get(NewsManagementGroup.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<NewsManagementGroup> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementGroup.class);
		List<NewsManagementGroup> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public NewsManagementGroup getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementGroup.class);
		cr.add(Restrictions.eq("name", name));
		return (NewsManagementGroup) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewNewsManagementGroupMember> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewNewsManagementGroupMember.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
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
		List<ViewNewsManagementGroupMember> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<NewsManagementGroup> getList() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementGroup.class);
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.asc("id"));
		List<NewsManagementGroup> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementGroup.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));

		long total = (long) cr.list().get(0);
		return total;
	}

}
