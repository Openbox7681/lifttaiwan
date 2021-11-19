package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementAttachMember;

/**
 * 資安資訊情報管理-附件
 */
@Repository
@Transactional
public class AnaManagementAttachDAOImpl extends BaseSessionFactory implements AnaManagementAttachDAO {

	public void insert(AnaManagementAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(AnaManagementAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(AnaManagementAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public AnaManagementAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(AnaManagementAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<AnaManagementAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AnaManagementAttach.class);
		List<AnaManagementAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewAnaManagementAttachMember> getAllByAnaManagementId(long anaManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewAnaManagementAttachMember.class);
		if (anaManagementId != 0)
			cr.add(Restrictions.eq("anaManagementId", anaManagementId));
		try {
			List<ViewAnaManagementAttachMember> list = cr.list();

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
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<AnaManagementAttach> getAllAttachByAnaManagementId(long anaManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AnaManagementAttach.class);
		if (anaManagementId != 0)
			cr.add(Restrictions.eq("anaManagementId", anaManagementId));
		try {
			List<AnaManagementAttach> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			 e.getStackTrace();
			return null;
		}
	}
	
	

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long anaManagementId = obj.isNull("AnaManagementId") == true ? 0 : obj.getLong("AnaManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AnaManagementAttach.class);
		cr.setProjection(Projections.rowCount());
		if (anaManagementId != 0)
			cr.add(Restrictions.eq("anaManagementId", anaManagementId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
