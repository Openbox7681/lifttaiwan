package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;

/**
 * 最新活動管理-附件
 */
@Repository
@Transactional
public class InformationExchangeAttachDAOImpl extends BaseSessionFactory implements InformationExchangeAttachDAO {

	public void insert(InformationExchangeAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(InformationExchangeAttach entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(InformationExchangeAttach entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public InformationExchangeAttach get(Long id) {
		return getSessionFactory().getCurrentSession().get(InformationExchangeAttach.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationExchangeAttach> getByInfoExId(String informationExchangeId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationExchangeAttach.class);
		cr.add(Restrictions.eq("informationExchangeId", informationExchangeId));
		List<InformationExchangeAttach> informationExchangeAttachList = cr.list();
		return informationExchangeAttachList;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationExchangeAttach> getByInformationId(String informationExchangeId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationExchangeAttach.class);
		if (informationExchangeId != null)
			cr.add(Restrictions.eq("informationExchangeId", informationExchangeId));
		List<InformationExchangeAttach> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
