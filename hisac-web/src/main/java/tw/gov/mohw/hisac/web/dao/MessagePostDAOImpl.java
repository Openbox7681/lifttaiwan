package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MessagePost;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MessagePostDAOImpl extends BaseSessionFactory implements MessagePostDAO {

	public void insert(MessagePost entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MessagePost entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MessagePost entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MessagePost get(String id) {
		return getSessionFactory().getCurrentSession().get(MessagePost.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessagePost> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePost.class);
		List<MessagePost> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessagePost> getBymessageId(String messageId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePost.class);
		cr.add(Restrictions.eq("messageId", messageId));
		List<MessagePost> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessagePost> getBymessageIdAndPostType(String messageId, String postType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessagePost.class);
		cr.add(Restrictions.eq("messageId", messageId));
		cr.add(Restrictions.eq("postType", postType));
		List<MessagePost> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
