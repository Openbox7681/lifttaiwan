package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.CommonPostAttach;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostAttachMember;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class CommonPostAttachDAOImpl extends BaseSessionFactory implements CommonPostAttachDAO {

	public void insert(CommonPostAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(CommonPostAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(CommonPostAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public CommonPostAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(CommonPostAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<CommonPostAttach> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPostAttach.class);
		List<CommonPostAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewCommonPostAttachMember> getAllByCommonPostId(long commonPostId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewCommonPostAttachMember.class);
		if (commonPostId != 0)
			cr.add(Restrictions.eq("commonPostId", commonPostId));
		try {
			List<ViewCommonPostAttachMember> list = cr.list();

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
		long commonPostId = obj.isNull("CommonPostId") == true ? 0 : obj.getLong("CommonPostId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPostAttach.class);
		cr.setProjection(Projections.rowCount());
		if (commonPostId != 0)
			cr.add(Restrictions.eq("CommonPostId", commonPostId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
