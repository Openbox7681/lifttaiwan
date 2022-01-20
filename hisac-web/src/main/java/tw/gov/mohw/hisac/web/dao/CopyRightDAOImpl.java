package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.CopyRight;

/**
 * 隱私權聲明修改功能
 */
@Repository
@Transactional
public class CopyRightDAOImpl extends BaseSessionFactory implements CopyRightDAO {

	public void insert(CopyRight entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(CopyRight entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}


	public CopyRight get(Long id) {
		return getSessionFactory().getCurrentSession().get(CopyRight.class, id);
	}

	

	

}
