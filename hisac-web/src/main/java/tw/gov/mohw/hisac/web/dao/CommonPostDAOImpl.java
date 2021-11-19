package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.CommonPost;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostMember;

/**
 * 認識本系統維護
 */
@Repository
@Transactional
public class CommonPostDAOImpl extends BaseSessionFactory implements CommonPostDAO {

	public void insert(CommonPost entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(CommonPost entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(CommonPost entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public CommonPost get(Long id) {
		return getSessionFactory().getCurrentSession().get(CommonPost.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<CommonPost> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPost.class);
		// cr.add(Restrictions.eq("PostType", 1));
		List<CommonPost> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public CommonPost getByTitle(String title) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPost.class);
		cr.add(Restrictions.eq("title", title));
		return (CommonPost) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewCommonPostMember> getList(JSONObject obj) {

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		// boolean dir = obj.isNull("dir") == true ? false :
		// obj.getBoolean("dir");
		// String sort = obj.isNull("sort") == true ? "id" :
		// obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Long postType = obj.isNull("PostType") == true ? null : obj.getLong("PostType");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewCommonPostMember.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (title != null)
			cr.add(Restrictions.like("title", "%" + title + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		if (postType != null)
			cr.add(Restrictions.eq("postType", postType));

		// if (dir == true)
		// cr.addOrder(Order.desc(sort));
		// else
		// cr.addOrder(Order.asc(sort));

		cr.addOrder(Order.desc("isEnable"));
		cr.addOrder(Order.desc("modifyTime"));

		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewCommonPostMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Long postType = obj.isNull("PostType") == true ? null : obj.getLong("PostType");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewCommonPostMember.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (title != null)
			cr.add(Restrictions.like("title", "%" + title + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		if (postType != null)
			cr.add(Restrictions.eq("postType", postType));

		long total = (long) cr.list().get(0);
		return total;
	}

	/**
	 * 取得 isEnable 為 1 的紀錄
	 */
	@SuppressWarnings("deprecation")
	public CommonPost findForPub(long postType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPost.class);
		cr.add(Restrictions.eq("postType", postType));
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.desc("modifyTime"));
		cr.setFirstResult(0);
		cr.setMaxResults(1);
		return (CommonPost) cr.uniqueResult();
	}
}
