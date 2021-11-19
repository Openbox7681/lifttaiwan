package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.persistence.ParameterMode;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMaintainPlanGroupOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainPlanGroupOrgDAOImpl extends BaseSessionFactory implements MaintainPlanGroupOrgDAO {

	@Transactional

	public void insert(MaintainPlanGroupOrg entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MaintainPlanGroupOrg entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MaintainPlanGroupOrg entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MaintainPlanGroupOrg get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainPlanGroupOrg.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MaintainPlanGroupOrg> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanGroupOrg.class);
		List<MaintainPlanGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public MaintainPlanGroupOrg getByOrgId(Long orgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanGroupOrg.class);
		cr.add(Restrictions.eq("orgId", orgId));
		return (MaintainPlanGroupOrg) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MaintainPlanGroupOrg> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long maintainPlanGroupId = obj.isNull("MaintainPlanGroupId") == true ? 0 : obj.getLong("MaintainPlanGroupId");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanGroupOrg.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (maintainPlanGroupId != 0)
			cr.add(Restrictions.eq("maintainPlanGroupId", maintainPlanGroupId));
		if (orgId != 0)
			cr.add(Restrictions.eq("orgId", orgId));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<MaintainPlanGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long maintainPlanGroupId = obj.isNull("MaintainPlanGroupId") == true ? 0 : obj.getLong("MaintainPlanGroupId");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanGroupOrg.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (maintainPlanGroupId != 0)
			cr.add(Restrictions.eq("maintainPlanGroupId", maintainPlanGroupId));
		if (orgId != 0)
			cr.add(Restrictions.eq("orgId", orgId));

		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MaintainPlanGroupOrg> getByMaintainPlanGroupId(Long maintainPlanGroupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanGroupOrg.class);
		cr.add(Restrictions.eq("maintainPlanGroupId", maintainPlanGroupId));
		List<MaintainPlanGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// @TsSuppressWarnings({"deprecation", "unchecked"})
	@SuppressWarnings("unchecked")
	public List<SpMaintainPlanGroupOrg> getSpList(JSONObject obj) {

		int maintainPlanGroupId = obj.isNull("MaintainPlanGroupId") == true ? 0 : obj.getInt("MaintainPlanGroupId");
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("sp_maintain_plan_group_org_name", SpMaintainPlanGroupOrg.class);
		call.registerParameter("MaintainPlanGroupId", Integer.class, ParameterMode.IN).bindValue(maintainPlanGroupId);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMaintainPlanGroupOrg> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
