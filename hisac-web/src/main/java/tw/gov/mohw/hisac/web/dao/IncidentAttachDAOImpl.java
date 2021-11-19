package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.IncidentAttach;
import tw.gov.mohw.hisac.web.domain.ViewIncidentAttachMember;

/**
 * 事故管理-附件
 */
@Repository
@Transactional
public class IncidentAttachDAOImpl extends BaseSessionFactory implements IncidentAttachDAO {

	public void insert(IncidentAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(IncidentAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(IncidentAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public IncidentAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(IncidentAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<IncidentAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(IncidentAttach.class);
		List<IncidentAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewIncidentAttachMember> getAllByIncidentId(long incidentId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewIncidentAttachMember.class);
		if ( incidentId != 0 )
			cr.add(Restrictions.eq("incidentId", incidentId));
		try {
			List<ViewIncidentAttachMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch(Exception e) {
			//e.getStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long incidentId = obj.isNull("IncidentId") == true ? 0 : obj.getLong("IncidentId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(IncidentAttach.class);
		cr.setProjection(Projections.rowCount());
		if ( incidentId != 0 )
			cr.add(Restrictions.eq("incidentId", incidentId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
