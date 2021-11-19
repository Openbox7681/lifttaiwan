package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReport;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReportList;

/**
 * 警訊月報表服務
 */
@Repository
@Transactional
public class MessageMonthReportDAOImpl extends BaseSessionFactory implements MessageMonthReportDAO {

	@SuppressWarnings("unchecked")
	public List<SpMessageMonthReport> getListCount(JSONObject obj) {

		// int messageGroupId = obj.isNull("MessageGroupId") == true ? 0 :
		// obj.getInt("MessageGroupId");
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");

		sdate = sdate + "-01 01:01:01";

		if (edate != null) {
			String[] year_month = edate.split("-");
			edate = edate + "-" + WebDatetime.getLastDayOfMonth(Integer.valueOf(year_month[0]), Integer.valueOf(year_month[1])) + " 23:59:59";
		}

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_month_report", SpMessageMonthReport.class);
		try {
			call.registerParameter("Sdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdate));
			call.registerParameter("Edate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(edate));
		} catch (ParseException e) {
			//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMessageMonthReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpMessageMonthReportList> getReportList(JSONObject obj) {

		String tdate = obj.isNull("Tdate") == true ? null : obj.getString("Tdate");
		String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");

		if (tdate != null)
			tdate = tdate + "-01 01:01:01";

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_month_report_list", SpMessageMonthReportList.class);
		try {
			call.registerParameter("Tdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tdate));

		} catch (ParseException e) {
			//e.printStackTrace();
		}
		if (alertCode != null)
			call.registerParameter("AlertCode", String.class, ParameterMode.IN).bindValue(alertCode);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMessageMonthReportList> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
