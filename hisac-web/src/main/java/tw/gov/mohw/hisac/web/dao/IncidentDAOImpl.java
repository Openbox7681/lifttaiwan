package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
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

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.Incident;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewIncidentMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 事件管理
 */
@Repository
@Transactional
public class IncidentDAOImpl extends BaseSessionFactory implements IncidentDAO {

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Incident> getAll() {
		String postType = "2";
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Incident.class);
		cr.add(Restrictions.eq("postType", postType));
		List<Incident> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewIncidentMember> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");
		
		Date sPostDateTime = obj.isNull("SPostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SPostDateTime"));
		Date ePostDateTime = obj.isNull("EPostDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EPostDateTime"));
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewIncidentMember.class);

		if (sPostDateTime != null) {
			//cr.add(Restrictions.le("postDateTime", WebDatetime.parse(sPostDateTime.toString(), "yyyy-MM-dd")));
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(sPostDateTime.toString(), "yyyy-MM-dd")));
		}
		if (ePostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(ePostDateTime.toString(), "yyyy-MM-dd")));
			//cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(postDateTime.toString(), "yyyy-MM-dd")));
		}
		if (reporterName != 0) {
			cr.add(Restrictions.eq("reporterName", reporterName));
		}
		if (handleName != 0) {
			cr.add(Restrictions.eq("handleName", handleName));
		}
		if (status != 0) {
			cr.add(Restrictions.eq("status", status));
		}
		
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewIncidentMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {

		Date sPostDateTime = obj.isNull("SPostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SPostDateTime"));
		Date ePostDateTime = obj.isNull("EPostDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EPostDateTime"));
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewIncidentMember.class);
		cr.setProjection(Projections.rowCount());

		if (sPostDateTime != null) {
			//cr.add(Restrictions.le("postDateTime", WebDatetime.parse(sPostDateTime.toString(), "yyyy-MM-dd")));
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(sPostDateTime.toString(), "yyyy-MM-dd")));
		}
		if (ePostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(ePostDateTime.toString(), "yyyy-MM-dd")));
			//cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(ePostDateTime.toString(), "yyyy-MM-dd")));
		}
		if (reporterName != 0) {
			cr.add(Restrictions.eq("reporterName", reporterName));
		}
		if (handleName != 0) {
			cr.add(Restrictions.eq("handleName", handleName));
		}
		if (status != 0) {
			cr.add(Restrictions.eq("status", status));
		}
		
		long total = (long) cr.list().get(0);
		
		return total;
		
	}

	public void insert(Incident entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Incident entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Incident entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Incident get(Long id) {
		return getSessionFactory().getCurrentSession().get(Incident.class, id);
	}
	
	@SuppressWarnings("deprecation")
	public Incident getByNotificationId(String notificationId) {		
		if (notificationId == null)
			return null;
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Incident.class);
		cr.add(Restrictions.eq("notificationId", notificationId));		
		return (Incident) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public ViewIncidentMember getByDetail(Long id) {
		Long status = (long) 4;
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewIncidentMember.class);
		cr.add(Restrictions.eq("id", id));
		cr.add(Restrictions.eq("status", status));
		return (ViewIncidentMember) cr.uniqueResult();
	}

	public ViewMessageAlertEvent getById(String id) {
		return getSessionFactory().getCurrentSession().get(ViewMessageAlertEvent.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ViewIncidentMember> getSpList(JSONObject obj) {

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		int parentOrgId = obj.isNull("ParentOrgId") == true ? 0 : obj.getInt("ParentOrgId");

		Date sPostDateTime = obj.isNull("SPostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SPostDateTime"));
		Date ePostDateTime = obj.isNull("EPostDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EPostDateTime"));
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");

		// debug
//		System.out.println("==========================================================");
//		System.out.println("IncidentDAOImpl.java → getSpList() → start = " + start);
//		System.out.println("IncidentDAOImpl.java → getSpList() → maxRows = " + maxRows);
//		System.out.println("IncidentDAOImpl.java → getSpList() → dir = " + dir);
//		System.out.println("IncidentDAOImpl.java → getSpList() → sort = " + sort);
//		System.out.println("IncidentDAOImpl.java → getSpList() → roleId：" + roleId);
//		System.out.println("IncidentDAOImpl.java → getSpList() → memberId：" + memberId);
//		System.out.println("IncidentDAOImpl.java → getSpList() → parentOrgId：" + parentOrgId);
//		System.out.println("IncidentDAOImpl.java → getSpList() → sPostDateTime：" + sPostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpList() → ePostDateTime：" + ePostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpList() → reporterName：" + reporterName);
//		System.out.println("IncidentDAOImpl.java → getSpList() → handleName：" + handleName);
//		System.out.println("IncidentDAOImpl.java → getSpList() → status：" + status);
				
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_incident", ViewIncidentMember.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (parentOrgId != 0)
			call.registerParameter("ParentOrgId", Integer.class, ParameterMode.IN).bindValue(parentOrgId);
		else
			call.registerParameter("ParentOrgId", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sPostDateTime != null)
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).bindValue(sPostDateTime);
		else
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (ePostDateTime != null)
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).bindValue(ePostDateTime);
		else
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (reporterName != 0)
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).bindValue(reporterName);
		else
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (handleName != 0)
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).bindValue(handleName);
		else
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Long.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Long.class, ParameterMode.IN).enablePassingNulls(true);
		
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<ViewIncidentMember> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	public long getSpListSize(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		int parentOrgId = obj.isNull("ParentOrgId") == true ? 0 : obj.getInt("ParentOrgId");

		Date sPostDateTime = obj.isNull("SPostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SPostDateTime"));
		Date ePostDateTime = obj.isNull("EPostDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EPostDateTime"));
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");

		// debug
//		System.out.println("==========================================================");
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → roleId：" + roleId);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → memberId：" + memberId);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → parentOrgId：" + parentOrgId);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → sPostDateTime：" + sPostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → ePostDateTime：" + ePostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → reporterName：" + reporterName);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → handleName：" + handleName);
//		System.out.println("IncidentDAOImpl.java → getSpListSize() → status：" + status);
				
		// ProcedureCall call =
		// getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_incident_size",
		// ViewIncidentMember.class);
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_incident_size");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (parentOrgId != 0)
			call.registerParameter("ParentOrgId", Integer.class, ParameterMode.IN).bindValue(parentOrgId);
		else
			call.registerParameter("ParentOrgId", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sPostDateTime != null)
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).bindValue(sPostDateTime);
		else
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (ePostDateTime != null)
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).bindValue(ePostDateTime);
		else
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (reporterName != 0)
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).bindValue(reporterName);
		else
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (handleName != 0)
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).bindValue(handleName);
		else
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Long.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Long.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	public long getSpFormCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		Long parentOrgId = obj.isNull("ParentOrgId") == true ? 0 : obj.getLong("ParentOrgId");
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_incident_from_count");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (parentOrgId != 0)
			call.registerParameter("ParentOrgId", Long.class, ParameterMode.IN).bindValue(parentOrgId);
		else
			call.registerParameter("ParentOrgId", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (reporterName != 0)
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).bindValue(reporterName);
		else
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (handleName != 0)
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).bindValue(handleName);
		else
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;
	}

	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		Long parentOrgId = obj.isNull("ParentOrgId") == true ? 0 : obj.getLong("ParentOrgId");

		Date sPostDateTime = obj.isNull("SPostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SPostDateTime"));
		Date ePostDateTime = obj.isNull("EPostDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EPostDateTime"));
		Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
		Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");

		// debug
//		System.out.println("==========================================================");
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → roleId：" + roleId);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → memberId：" + memberId);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → parentOrgId：" + parentOrgId);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → sPostDateTime：" + sPostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → ePostDateTime：" + ePostDateTime);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → reporterName：" + reporterName);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → handleName：" + handleName);
//		System.out.println("IncidentDAOImpl.java → getSpButtonCount() → status：" + status);
		
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_incident_button_count", SpButtonCount.class);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (parentOrgId != 0)
			call.registerParameter("ParentOrgId", Long.class, ParameterMode.IN).bindValue(parentOrgId);
		else
			call.registerParameter("ParentOrgId", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (sPostDateTime != null)
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).bindValue(sPostDateTime);
		else
			call.registerParameter("SPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (ePostDateTime != null)
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).bindValue(ePostDateTime);
		else
			call.registerParameter("EPostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (reporterName != 0)
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).bindValue(reporterName);
		else
			call.registerParameter("ReporterName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (handleName != 0)
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).bindValue(handleName);
		else
			call.registerParameter("HandleName", Long.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Long.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Long.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpButtonCount> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}