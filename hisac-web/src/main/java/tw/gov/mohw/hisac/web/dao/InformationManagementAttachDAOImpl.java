package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;
import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;

/**
 * 情資分享管理-附件
 */
@Repository
@Transactional
public class InformationManagementAttachDAOImpl extends BaseSessionFactory implements InformationManagementAttachDAO {

	public void insert(InformationManagementAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(InformationManagementAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(InformationManagementAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	public InformationManagementAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(InformationManagementAttach.class, id);
	}

	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationManagementAttach> getAllByInformationManagementId(long informationManagementId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagementAttach.class);
		if (informationManagementId != 0)
			cr.add(Restrictions.eq("informationManagementId", informationManagementId));
		try {
			List<InformationManagementAttach> list = cr.list();

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
