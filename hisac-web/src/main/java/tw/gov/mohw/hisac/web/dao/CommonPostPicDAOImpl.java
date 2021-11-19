package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.CommonPostPic;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostPicMember;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class CommonPostPicDAOImpl extends BaseSessionFactory implements CommonPostPicDAO {

	public void insert(CommonPostPic entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(CommonPostPic entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(CommonPostPic entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public CommonPostPic get(Long id) {
		return getSessionFactory().getCurrentSession().get(CommonPostPic.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<CommonPostPic> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPostPic.class);
		List<CommonPostPic> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewCommonPostPicMember> getAllByCommonPostId(long commonPostId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewCommonPostPicMember.class);
		if (commonPostId != 0)
			cr.add(Restrictions.eq("commonPostId", commonPostId));
		try {
			List<ViewCommonPostPicMember> list = cr.list();

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
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CommonPostPic.class);
		cr.setProjection(Projections.rowCount());
		if (commonPostId != 0)
			cr.add(Restrictions.eq("CommonPostId", commonPostId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
