package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.RoleForm;

/**
 * RoleForm服務
 */
@Repository
@Transactional
public class RoleFormDAOImpl extends BaseSessionFactory implements RoleFormDAO {

	public void insert(RoleForm entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(RoleForm entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(RoleForm entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public RoleForm get(Long id) {
		return getSessionFactory().getCurrentSession().get(RoleForm.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<RoleForm> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(RoleForm.class);
		List<RoleForm> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Transactional
	public void insertOruodate(RoleForm entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<RoleForm> getList(Long roleId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(RoleForm.class);
		cr.add(Restrictions.eq("roleId", roleId));
		List<RoleForm> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
