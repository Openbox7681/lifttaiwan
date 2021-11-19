package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class ProcessLogDAOImpl extends BaseSessionFactory implements ProcessLogDAO {

	public void insert(ProcessLog entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(ProcessLog entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(ProcessLog entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public ProcessLog get(String id) {
		return getSessionFactory().getCurrentSession().get(ProcessLog.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ProcessLog> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ProcessLog.class);
		List<ProcessLog> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewProcessLogMember> getByPostId(String postId, String tableName) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewProcessLogMember.class);
		cr.add(Restrictions.eq("postId", postId));
		cr.add(Restrictions.eq("tableName", tableName));
		cr.addOrder(Order.desc("createTime"));
		List<ViewProcessLogMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
