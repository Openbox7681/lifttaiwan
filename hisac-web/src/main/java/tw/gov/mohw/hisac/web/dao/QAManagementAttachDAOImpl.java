package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.QAManagementAttach;

/**
 * 最新消息管理-附件
 */
@Repository
@Transactional
public class QAManagementAttachDAOImpl extends BaseSessionFactory implements QAManagementAttachDAO {

	public void insert(QAManagementAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(QAManagementAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(QAManagementAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public QAManagementAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(QAManagementAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<QAManagementAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagementAttach.class);
		List<QAManagementAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<QAManagementAttach> getAllByQAManagementId(long qaManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(QAManagementAttach.class);
		if (qaManagementId != 0)
			cr.add(Restrictions.eq("managementId", qaManagementId));
		try {
			List<QAManagementAttach> list = cr.list();

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
}
