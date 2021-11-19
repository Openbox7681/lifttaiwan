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
import tw.gov.mohw.hisac.web.domain.AnaManagement;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 資安資訊情報管理
 */
@Repository
@Transactional
public class AnaManagementDAOImpl extends BaseSessionFactory implements AnaManagementDAO {

	public void insert(AnaManagement entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(AnaManagement entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(AnaManagement entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public AnaManagement get(Long id) {
		return getSessionFactory().getCurrentSession().get(AnaManagement.class, id);
	}

	@SuppressWarnings("deprecation")
	public ViewAnaManagementMember getByDetail(Long id) {
		Long status = (long) 4;
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewAnaManagementMember.class);
		cr.add(Restrictions.eq("id", id));
		cr.add(Restrictions.eq("isEnable", true));
		cr.add(Restrictions.eq("status", status));
		return (ViewAnaManagementMember) cr.uniqueResult();
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<AnaManagement> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(AnaManagement.class);
		List<AnaManagement> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewAnaManagementMember> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "incidentReportedTime" : obj.getString("sort");
		String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
		String incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : obj.getString("IncidentDiscoveryTime");
		String incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : obj.getString("IncidentReportedTime");
		String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
		Long impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getLong("ImpactQualification");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isMedical = obj.isNull("IsMedical") == true ? null : obj.getBoolean("IsMedical");
		String startDateTime = obj.isNull("StartDateTime") == true ? null : obj.getString("StartDateTime");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewAnaManagementMember.class);

		if (incidentTitle != null) {
			// cr.add(Restrictions.or(Restrictions.like("incidentId", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("incidentTitle", "%" + incidentTitle + "%"),
			// Restrictions.like("description", "%" + incidentTitle + "%"),
			// Restrictions.like("reporterName", "%" + incidentTitle + "%"),
			// Restrictions.like("responderPartyName", "%" + incidentTitle +
			// "%"),
			// Restrictions.like("responderContactNumbers", "%" + incidentTitle
			// + "%"),
			// Restrictions.like("responderElectronicAddressIdentifiers", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("confidence", "%" + incidentTitle + "%"),
			// Restrictions.like("reference", "%" + incidentTitle + "%"),
			// Restrictions.like("affectedSoftwareDescription", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("coaDescription", "%" + incidentTitle + "%")));
			cr.add(Restrictions.or(Restrictions.like("incidentId", "%" + incidentTitle + "%")));
		}
		if (incidentDiscoveryTime != null) {
			cr.add(Restrictions.eq("incidentDiscoveryTime", WebDatetime.parse(incidentDiscoveryTime, "yyyy-MM-dd")));
		}
		if (incidentReportedTime != null) {
			cr.add(Restrictions.eq("incidentReportedTime", WebDatetime.parse(incidentReportedTime, "yyyy-MM-dd")));
		}
		if (eventTypeCode != null) {
			cr.add(Restrictions.eq("eventTypeCode", eventTypeCode));
		}
		if (impactQualification != 0) {
			cr.add(Restrictions.eq("impactQualification", impactQualification));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isMedical != null) {
			cr.add(Restrictions.eq("isMedical", isMedical));
		}
		if (startDateTime != null) {
			cr.add(Restrictions.le("startDateTime", WebDatetime.parse(startDateTime, "yyyy-MM-dd")));
			cr.add(Restrictions.ge("endDateTime", WebDatetime.parse(startDateTime, "yyyy-MM-dd")));
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
		List<ViewAnaManagementMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpAnaManagementReport> getReport(JSONObject obj) {
		String startPostDateTime = obj.isNull("Sdate") == true ? null : obj.getString("Sdate") + " 00:00:00";
		String endPostDateTime = obj.isNull("Edate") == true ? null : obj.getString("Edate") + " 00:00:00";

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management_report", SpAnaManagementReport.class);

		if (startPostDateTime != null) {
			call.registerParameter("StartPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.parse(startPostDateTime, "yyyy-MM-dd 00:00:00"));
		}
		if (endPostDateTime != null) {
			call.registerParameter("EndPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.addDays(WebDatetime.parse(endPostDateTime, "yyyy-MM-dd 00:00:00"), 1));
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpAnaManagementReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
		String incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : obj.getString("IncidentDiscoveryTime");
		String incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : obj.getString("IncidentReportedTime");
		String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
		Long impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getLong("ImpactQualification");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isMedical = obj.isNull("IsMedical") == true ? null : obj.getBoolean("IsMedical");
		String startDateTime = obj.isNull("StartDateTime") == true ? null : obj.getString("StartDateTime");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewAnaManagementMember.class);
		cr.setProjection(Projections.rowCount());
		if (incidentTitle != null) {
			// cr.add(Restrictions.or(Restrictions.like("incidentId", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("incidentTitle", "%" + incidentTitle + "%"),
			// Restrictions.like("description", "%" + incidentTitle + "%"),
			// Restrictions.like("reporterName", "%" + incidentTitle + "%"),
			// Restrictions.like("responderPartyName", "%" + incidentTitle +
			// "%"),
			// Restrictions.like("responderContactNumbers", "%" + incidentTitle
			// + "%"),
			// Restrictions.like("responderElectronicAddressIdentifiers", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("confidence", "%" + incidentTitle + "%"),
			// Restrictions.like("reference", "%" + incidentTitle + "%"),
			// Restrictions.like("affectedSoftwareDescription", "%" +
			// incidentTitle + "%"),
			// Restrictions.like("coaDescription", "%" + incidentTitle + "%")));
			cr.add(Restrictions.or(Restrictions.like("incidentId", "%" + incidentTitle + "%")));
		}
		if (incidentDiscoveryTime != null) {
			cr.add(Restrictions.ge("incidentDiscoveryTime", WebDatetime.parse(incidentDiscoveryTime, "yyyy-MM-dd")));
		}
		if (incidentReportedTime != null) {
			cr.add(Restrictions.eq("incidentReportedTime", WebDatetime.parse(incidentReportedTime, "yyyy-MM-dd")));
		}
		if (eventTypeCode != null) {
			cr.add(Restrictions.eq("eventTypeCode", eventTypeCode));
		}
		if (impactQualification != 0) {
			cr.add(Restrictions.eq("impactQualification", impactQualification));
		}
		if (isMedical != null) {
			cr.add(Restrictions.eq("isMedical", isMedical));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (startDateTime != null) {
			cr.add(Restrictions.le("startDateTime", WebDatetime.parse(startDateTime, "yyyy-MM-dd")));
			cr.add(Restrictions.ge("endDateTime", WebDatetime.parse(startDateTime, "yyyy-MM-dd")));
		}
		if (status != 0) {
			cr.add(Restrictions.eq("status", status));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	public ViewMessageAlertEvent getById(String id) {
		return getSessionFactory().getCurrentSession().get(ViewMessageAlertEvent.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ViewAnaManagementMember> getSpList(JSONObject obj) {

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "incidentReportedTime" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		
		//新增搜尋區間用
		Date sReportedTime = obj.isNull("QuerySdate") == true ? null : WebDatetime.parseSdate(obj.getString("QuerySdate"));
		Date eReportedTime  = obj.isNull("QueryEdate") == true ? null : WebDatetime.parseSdate(obj.getString("QueryEdate"));

		String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
		Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentDiscoveryTime"));
		Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentReportedTime"));
		String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
		int impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getInt("ImpactQualification");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isMedical = obj.isNull("IsMedical") == true ? null : obj.getBoolean("IsMedical");
		Date startDateTime = obj.isNull("StartDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("StartDateTime")); // 公開資訊首頁查詢條件
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management", ViewAnaManagementMember.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		
		
		if (incidentTitle != null)
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).bindValue(incidentTitle);
		else
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentDiscoveryTime != null)
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).bindValue(incidentDiscoveryTime);
		else
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentReportedTime != null)
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).bindValue(incidentReportedTime);
		else
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		
		if( sReportedTime != null)
			call.registerParameter("QuerySReportedTime", Date.class, ParameterMode.IN).bindValue(sReportedTime);
		else
			call.registerParameter("QuerySReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		
		if( eReportedTime != null)
			call.registerParameter("QueryEReportedTime", Date.class, ParameterMode.IN).bindValue(eReportedTime);
		else
			call.registerParameter("QueryEReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eventTypeCode != null)
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).bindValue(eventTypeCode);
		else
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (impactQualification != 0)
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).bindValue(impactQualification);
		else
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);
		
		if (isMedical != null)
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).bindValue(isMedical);
		else
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		// 公開資訊首頁查詢條件
		if (startDateTime != null)
			call.registerParameter("StartDateTime", Date.class, ParameterMode.IN).bindValue(startDateTime);
		else
			call.registerParameter("StartDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<ViewAnaManagementMember> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	public long getSpListSize(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
		Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentDiscoveryTime"));
		Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentReportedTime"));
		String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
		int impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getInt("ImpactQualification");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isMedical = obj.isNull("IsMedical") == true ? null : obj.getBoolean("IsMedical");
		Date startDateTime = obj.isNull("StartDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("StartDateTime")); // 公開資訊首頁查詢條件
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		
		//新增搜尋區間用
		Date sReportedTime = obj.isNull("QuerySdate") == true ? null : WebDatetime.parseSdate(obj.getString("QuerySdate"));
		Date eReportedTime  = obj.isNull("QueryEdate") == true ? null : WebDatetime.parseSdate(obj.getString("QueryEdate"));

		// ProcedureCall call =
		// getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management_size",
		// ViewAnaManagementMember.class);
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management_size");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (incidentTitle != null)
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).bindValue(incidentTitle);
		else
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentDiscoveryTime != null)
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).bindValue(incidentDiscoveryTime);
		else
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentReportedTime != null)
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).bindValue(incidentReportedTime);
		else
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		
		if( sReportedTime != null)
			call.registerParameter("QuerySReportedTime", Date.class, ParameterMode.IN).bindValue(sReportedTime);
		else
			call.registerParameter("QuerySReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		
		if( eReportedTime != null)
			call.registerParameter("QueryEReportedTime", Date.class, ParameterMode.IN).bindValue(eReportedTime);
		else
			call.registerParameter("QueryEReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eventTypeCode != null)
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).bindValue(eventTypeCode);
		else
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (impactQualification != 0)
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).bindValue(impactQualification);
		else
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (isMedical != null)
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).bindValue(isMedical);
		else
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		// 公開資訊首頁查詢條件
		if (startDateTime != null)
			call.registerParameter("StartDateTime", Date.class, ParameterMode.IN).bindValue(startDateTime);
		else
			call.registerParameter("StartDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	public long getSpFormCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management_from_count");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;
	}

	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
		Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentDiscoveryTime"));
		Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : WebDatetime.parseSdate(obj.getString("IncidentReportedTime"));
		String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
		int impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getInt("ImpactQualification");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isMedical = obj.isNull("IsMedical") == true ? null : obj.getBoolean("IsMedical");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_ana_management_button_count", SpButtonCount.class);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		if (incidentTitle != null)
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).bindValue(incidentTitle);
		else
			call.registerParameter("IncidentTitle", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentDiscoveryTime != null)
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).bindValue(incidentDiscoveryTime);
		else
			call.registerParameter("IncidentDiscoveryTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (incidentReportedTime != null)
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).bindValue(incidentReportedTime);
		else
			call.registerParameter("IncidentReportedTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eventTypeCode != null)
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).bindValue(eventTypeCode);
		else
			call.registerParameter("EventTypeCode", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (impactQualification != 0)
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).bindValue(impactQualification);
		else
			call.registerParameter("ImpactQualification", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (isMedical != null)
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).bindValue(isMedical);
		else
			call.registerParameter("IsMedical", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		
		call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);

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