package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MessagePostAttach;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class MessagePostAttachDAOImpl extends BaseSessionFactory implements MessagePostAttachDAO {

	public void insert(MessagePostAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MessagePostAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MessagePostAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MessagePostAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(MessagePostAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessagePostAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePostAttach.class);
		List<MessagePostAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessagePostAttach> getByMessageId(String messageId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePostAttach.class);
		if (messageId != null)
			cr.add(Restrictions.eq("messageId", messageId));
		List<MessagePostAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getByMessageIdSize(String messageId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePostAttach.class);
		cr.setProjection(Projections.rowCount());
		if (messageId != null)
			cr.add(Restrictions.eq("messageId", messageId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
