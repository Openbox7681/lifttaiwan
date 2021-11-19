package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementAttachMember;

/**
 * 最新消息管理-附件
 */
@Repository
@Transactional
public class NewsManagementAttachDAOImpl extends BaseSessionFactory implements NewsManagementAttachDAO {

	public void insert(NewsManagementAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(NewsManagementAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(NewsManagementAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public NewsManagementAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(NewsManagementAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<NewsManagementAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementAttach.class);
		List<NewsManagementAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewNewsManagementAttachMember> getAllByNewsManagementId(long newsManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewNewsManagementAttachMember.class);
		if (newsManagementId != 0)
			cr.add(Restrictions.eq("newsManagementId", newsManagementId));
		try {
			List<ViewNewsManagementAttachMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// e.getStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long newsManagementId = obj.isNull("NewsManagementId") == true ? 0 : obj.getLong("NewsManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementAttach.class);
		cr.setProjection(Projections.rowCount());
		if (newsManagementId != 0)
			cr.add(Restrictions.eq("newsManagementId", newsManagementId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
