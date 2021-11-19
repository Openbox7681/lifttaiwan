package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.LinksPic;
import tw.gov.mohw.hisac.web.domain.ViewLinksPicMember;

/**
 * 相關連結管理-上傳引用圖檔
 */
@Repository
@Transactional
public class LinksPicDAOImpl extends BaseSessionFactory implements LinksPicDAO {

	public void insert(LinksPic entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(LinksPic entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(LinksPic entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public LinksPic get(Long id) {
		return getSessionFactory().getCurrentSession().get(LinksPic.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<LinksPic> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(LinksPic.class);
		List<LinksPic> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewLinksPicMember> getAllByLinksId(long linksId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewLinksPicMember.class);

		if (linksId != 0) {
			cr.add(Restrictions.eq("linksId", linksId));
		}

		try {
			List<ViewLinksPicMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long linksId = obj.isNull("LinksId") == true ? 0 : obj.getLong("LinksId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(LinksPic.class);
		cr.setProjection(Projections.rowCount());
		if (linksId != 0)
			cr.add(Restrictions.eq("linksId", linksId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
