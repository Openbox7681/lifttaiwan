package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.MessagePostRelease;
import tw.gov.mohw.hisac.web.domain.ViewMessagePostReleaseOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MessagePostReleaseDAOImpl extends BaseSessionFactory implements MessagePostReleaseDAO {

	public void insert(MessagePostRelease entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MessagePostRelease entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MessagePostRelease entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MessagePostRelease get(String id) {
		return getSessionFactory().getCurrentSession().get(MessagePostRelease.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewMessagePostReleaseOrg> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMessagePostReleaseOrg.class);
		List<ViewMessagePostReleaseOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewMessagePostReleaseOrg> getBymessageId(String messageId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMessagePostReleaseOrg.class);
		cr.add(Restrictions.eq("messageId", messageId));
		List<ViewMessagePostReleaseOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings({"deprecation"})
	public ViewMessagePostReleaseOrg getBymessageIdAndOrgId(String messageId, long orgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMessagePostReleaseOrg.class);
		cr.add(Restrictions.eq("messageId", messageId));
		cr.add(Restrictions.eq("orgId", orgId));
		return (ViewMessagePostReleaseOrg) cr.uniqueResult();

	}

}
