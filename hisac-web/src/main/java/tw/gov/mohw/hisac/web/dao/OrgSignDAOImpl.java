package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.SpOrgSign;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class OrgSignDAOImpl extends BaseSessionFactory implements OrgSignDAO {

	@Transactional

	public void insert(OrgSign entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(OrgSign entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(OrgSign entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public OrgSign get(Long id) {
		return getSessionFactory().getCurrentSession().get(OrgSign.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<OrgSign> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgSign.class);
		List<OrgSign> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	//
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<OrgSign> getByOrgId(Long orgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgSign.class);
		cr.add(Restrictions.eq("orgId", orgId));
		List<OrgSign> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<OrgSign> getByParentOrgId(Long parentOrgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgSign.class);
		cr.add(Restrictions.eq("parentOrgId", parentOrgId));
		List<OrgSign> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	//
	//
	// @SuppressWarnings({"deprecation", "unchecked"})
	// public List<OrgSign> getList(JSONObject obj) {
	// int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
	// int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
	// boolean dir = obj.getBoolean("dir");
	// String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
	//
	// long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
	// long messageGroupId = obj.isNull("MessageGroupId") == true ? 0 :
	// obj.getLong("MessageGroupId");
	// long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
	//
	//
	// Criteria cr =
	// getSessionFactory().getCurrentSession().createCriteria(OrgSign.class);
	// if (id != 0)
	// cr.add(Restrictions.eq("id", id));
	// if (messageGroupId != 0)
	// cr.add(Restrictions.eq("messageGroupId", messageGroupId));
	// if (orgId != 0)
	// cr.add(Restrictions.eq("orgId", orgId));
	//
	//
	// if (dir == true)
	// cr.addOrder(Order.desc(sort));
	// else
	// cr.addOrder(Order.asc(sort));
	// cr.setFirstResult(start);
	// if (maxRows != 0)
	// cr.setMaxResults(maxRows);
	// List<OrgSign> list = cr.list();
	// if (list.size() > 0) {
	// return list;
	// } else {
	// return null;
	// }
	// }
	//
	// @SuppressWarnings("deprecation")
	// public long getListSize(JSONObject obj) {
	// long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
	// long messageGroupId = obj.isNull("MessageGroupId") == true ? 0 :
	// obj.getLong("MessageGroupId");
	// long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
	//
	//
	// Criteria cr =
	// getSessionFactory().getCurrentSession().createCriteria(OrgSign.class);
	// cr.setProjection(Projections.rowCount());
	// if (id != 0)
	// cr.add(Restrictions.eq("id", id));
	// if (messageGroupId != 0)
	// cr.add(Restrictions.eq("messageGroupId", messageGroupId));
	// if (orgId != 0)
	// cr.add(Restrictions.eq("orgId", orgId));
	//
	//
	// long total = (long) cr.list().get(0);
	// return total;
	// }

	// @TsSuppressWarnings({"deprecation", "unchecked"})
	@SuppressWarnings("unchecked")
	public List<SpOrgSign> getSpList(JSONObject obj) {

		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_org_sign", SpOrgSign.class);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpOrgSign> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
