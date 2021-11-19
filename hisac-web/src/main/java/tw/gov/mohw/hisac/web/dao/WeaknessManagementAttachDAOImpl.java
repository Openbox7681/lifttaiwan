package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.WeaknessManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementAttachMember;

/**
 * 資安資訊情報管理-附件
 */
@Repository
@Transactional
public class WeaknessManagementAttachDAOImpl extends BaseSessionFactory implements WeaknessManagementAttachDAO {

	public void insert(WeaknessManagementAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(WeaknessManagementAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(WeaknessManagementAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public WeaknessManagementAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(WeaknessManagementAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<WeaknessManagementAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(WeaknessManagementAttach.class);
		List<WeaknessManagementAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewWeaknessManagementAttachMember> getAllByWeaknessManagementId(long weaknessManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewWeaknessManagementAttachMember.class);
		if (weaknessManagementId != 0)
			cr.add(Restrictions.eq("weaknessManagementId", weaknessManagementId));
		try {
			List<ViewWeaknessManagementAttachMember> list = cr.list();

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
		long weaknessManagementId = obj.isNull("WeaknessManagementId") == true ? 0 : obj.getLong("WeaknessManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(WeaknessManagementAttach.class);
		cr.setProjection(Projections.rowCount());
		if (weaknessManagementId != 0)
			cr.add(Restrictions.eq("weaknessManagementId", weaknessManagementId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
