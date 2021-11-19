package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.InformationManagementPic;

/**
 * 最新消息管理-上傳引用圖檔
 */
@Repository
@Transactional
public class InformationManagementPicDAOImpl extends BaseSessionFactory implements InformationManagementPicDAO {

	public void insert(InformationManagementPic entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(InformationManagementPic entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(InformationManagementPic entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public InformationManagementPic get(Long id) {
		return getSessionFactory().getCurrentSession().get(InformationManagementPic.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationManagementPic> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagementPic.class);
		List<InformationManagementPic> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationManagementPic> getAllByInformationManagementId(long informationManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagementPic.class);

		if (informationManagementId != 0) {
			cr.add(Restrictions.eq("informationManagementId", informationManagementId));
		}

		try {
			List<InformationManagementPic> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// //e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long informationManagementId = obj.isNull("InformationManagementId") == true ? 0 : obj.getLong("InformationManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagementPic.class);
		cr.setProjection(Projections.rowCount());
		if (informationManagementId != 0)
			cr.add(Restrictions.eq("informationManagementId", informationManagementId));
		long total = (long) cr.list().get(0);
		return total;
	}
}
