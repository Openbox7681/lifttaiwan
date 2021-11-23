package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.WebDatetime;


/**
 * 會員報表結果
 */
@Repository
@Transactional(timeout = 36000)
public class MemberReportDAOImpl extends BaseSessionFactory implements MemberReportDAO {
	
	public void delete(MemberReport entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MemberReport get(Long id) {
		return getSessionFactory().getCurrentSession().get(MemberReport.class, id);
	}
	
	
	
	public JSONObject schedule(JSONObject obj) {
		JSONObject responseJson = new JSONObject(); 

		try {
			
		
		String reportSdate = obj.isNull("ReportScheduleTime") == true ? null : obj.getString("ReportScheduleTime");
		
		Date reportSDate = new SimpleDateFormat("yyyy-MM-dd").parse(reportSdate);
		
		Date reportEDate = WebDatetime.addDays(reportSDate, 1);
		
		
		
		
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_member_report_schedule");
		call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(reportSDate);
		
		call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(reportEDate);
		
		

		call.getOutputs();	
		
		responseJson.put("Message", "success");
		responseJson.put("Success", true);
		
		return responseJson;
			
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("Success", false);
			responseJson.put("Message", e.getStackTrace());
			
			return responseJson;
		}
	}
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<MemberReport> getList(JSONObject obj) {
				
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReport.class);		
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.lt("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<MemberReport> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public long getListSize(JSONObject obj) {
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReport.class);		
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.lt("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}		
		
		
		return cr.list().size();
	}


	
	
	
	

}