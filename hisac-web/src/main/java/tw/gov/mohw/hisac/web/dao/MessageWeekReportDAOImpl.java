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
import tw.gov.mohw.hisac.web.domain.SpMessageWeekReport;

/**
 * 警訊月報表服務
 */
@Repository
@Transactional
public class MessageWeekReportDAOImpl extends BaseSessionFactory implements MessageWeekReportDAO {

	@SuppressWarnings("unchecked")
	public List<SpMessageWeekReport> getSpList(JSONObject obj) {

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate"));
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_week_report", SpMessageWeekReport.class);

		call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(sdate);
		call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(edate);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMessageWeekReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
