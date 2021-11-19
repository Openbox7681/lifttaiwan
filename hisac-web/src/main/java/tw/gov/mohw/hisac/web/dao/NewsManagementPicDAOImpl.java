package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.NewsManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementPicMember;

/**
 * 最新消息管理-上傳引用圖檔
 */
@Repository
@Transactional
public class NewsManagementPicDAOImpl extends BaseSessionFactory implements NewsManagementPicDAO {

	public void insert(NewsManagementPic entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(NewsManagementPic entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(NewsManagementPic entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public NewsManagementPic get(Long id) {
		return getSessionFactory().getCurrentSession().get(NewsManagementPic.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<NewsManagementPic> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementPic.class);
		List<NewsManagementPic> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewNewsManagementPicMember> getAllByNewsManagementId(long newsManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewNewsManagementPicMember.class);

		if (newsManagementId != 0) {
			cr.add(Restrictions.eq("newsManagementId", newsManagementId));
		}

		try {
			List<ViewNewsManagementPicMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// //e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long newsManagementId = obj.isNull("NewsManagementId") == true ? 0 : obj.getLong("NewsManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NewsManagementPic.class);
		cr.setProjection(Projections.rowCount());
		if (newsManagementId != 0)
			cr.add(Restrictions.eq("newsManagementId", newsManagementId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
