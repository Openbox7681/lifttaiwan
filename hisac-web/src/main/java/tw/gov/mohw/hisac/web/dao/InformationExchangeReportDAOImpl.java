package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.SpInformationExchangeReport;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;

@Repository
@Transactional
public class InformationExchangeReportDAOImpl extends BaseSessionFactory implements InformationExchangeReportDAO {

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SpInformationExchangeReport> getSpListIn(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_report_in", SpInformationExchangeReport.class);
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
		List<SpInformationExchangeReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SpInformationExchangeReport> getSpListOut(JSONObject obj) {
		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate")); // Date
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate")); // Date

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_report_out", SpInformationExchangeReport.class);
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
		List<SpInformationExchangeReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
