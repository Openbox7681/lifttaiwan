package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.NotificationAsset;



/**
 * 最新消息管理-附件
 */
@Repository
@Transactional
public class NotificationAssetDAOImpl extends BaseSessionFactory implements NotificationAssetDAO {

	public void insert(NotificationAsset entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(NotificationAsset entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(NotificationAsset entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public NotificationAsset get(Long id) {
		return getSessionFactory().getCurrentSession().get(NotificationAsset.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<NotificationAsset> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NotificationAsset.class);
		List<NotificationAsset> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<NotificationAsset> getAllByNotificationId(String notificationId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NotificationAsset.class);
		if (notificationId != null)
			cr.add(Restrictions.eq("notificationId", notificationId));
		try {
			List<NotificationAsset> list = cr.list();

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
		long notificationId = obj.isNull("NotificationId") == true ? 0 : obj.getLong("NotificationId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(NotificationAsset.class);
		cr.setProjection(Projections.rowCount());
		if (notificationId != 0)
			cr.add(Restrictions.eq("notificationId", notificationId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
