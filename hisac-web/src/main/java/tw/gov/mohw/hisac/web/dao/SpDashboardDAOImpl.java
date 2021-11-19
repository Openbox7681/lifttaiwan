package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.SpContactCountDashboard;
import tw.gov.mohw.hisac.web.domain.SpDashboard;
import tw.gov.mohw.hisac.web.domain.SpManagerCountDashboard;

@Repository
@Transactional
public class SpDashboardDAOImpl extends BaseSessionFactory implements SpDashboardDAO {

	@Transactional
	@SuppressWarnings({"unchecked"})
	public List<SpDashboard> getSpListInfo(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_dashboard", SpDashboard.class);

		if (sdate != null)
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		else
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (edate != null)
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);
		else
			call.registerParameter("Edate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpDashboard> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@Transactional
	@SuppressWarnings({"unchecked"})
	public List<SpDashboard> getSpListPublic(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_public_dashboard", SpDashboard.class);

		if (sdate != null)
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		else
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (edate != null)
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);
		else
			call.registerParameter("Edate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpDashboard> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@Transactional
	@SuppressWarnings({"unchecked"})
	public List<SpDashboard> getSpListMessage(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId"); // OrgId
		String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_dashboard", SpDashboard.class);

		if (sdate != null)
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		else
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (edate != null)
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);
		else
			call.registerParameter("Edate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);

		if (orgType != null)
			call.registerParameter("OrgType", String.class, ParameterMode.IN).bindValue(orgType);
		else
			call.registerParameter("OrgType", String.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpDashboard> list = resultSetOutput.getResultList();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@Transactional
	@SuppressWarnings({"unchecked"})
	public List<SpDashboard> getSpListNotification(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_notification_dashboard", SpDashboard.class);

		if (sdate != null)
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		else
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (edate != null)
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);
		else
			call.registerParameter("Edate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpDashboard> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpManagerCountDashboard> getManager() {		

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_manager_count_dashboard", SpManagerCountDashboard.class);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpManagerCountDashboard> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SpContactCountDashboard> getContact() {		

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_contact_count_dashboard", SpContactCountDashboard.class);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpContactCountDashboard> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	@Transactional
	@SuppressWarnings({"unchecked"})
	public List<SpDashboard> getInformation(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date		

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_dashboard", SpDashboard.class);

		if (sdate != null)
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		else
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (edate != null)
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);
		else
			call.registerParameter("Edate", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpDashboard> list = resultSetOutput.getResultList();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}