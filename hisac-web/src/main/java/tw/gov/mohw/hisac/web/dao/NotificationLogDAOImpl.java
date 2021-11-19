package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.NotificationLog;
import tw.gov.mohw.hisac.web.domain.ViewNotificationLogMember;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class NotificationLogDAOImpl extends BaseSessionFactory implements NotificationLogDAO {

	public void insert(NotificationLog entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewNotificationLogMember> getByNotificationId(String notificationId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewNotificationLogMember.class);
		cr.add(Restrictions.eq("notificationId", notificationId));		
		cr.addOrder(Order.desc("createTime"));
		List<ViewNotificationLogMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
