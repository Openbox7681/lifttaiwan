package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.domain.OrgReport;
import tw.gov.mohw.hisac.web.domain.OrgReportSchedule;
import tw.gov.mohw.hisac.web.domain.SpMessageWeekReport;
import tw.gov.mohw.hisac.web.domain.SpOrgReportResult;



/**
 * 機構報表結果
 */
@Repository
@Transactional(timeout = 36000)
public class OrgReportDAOImpl extends BaseSessionFactory implements OrgReportDAO {
	
	
	public OrgReport get(Long id) {
		return (OrgReport) getSessionFactory().getCurrentSession().get(OrgReport.class, id);
	}
	
	public void delete(OrgReport entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<SpOrgReportResult> getSumResult(JSONObject obj) {
		
		
			
		
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		try {
			Date reportSDate;
			Date reportEDate;
			if (sdate!= null) {
				 reportSDate = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			}else {
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, 2010);
				cal.set(Calendar.MONTH, 1);
				cal.set(Calendar.DATE, 1);
				
				Date dt = cal.getTime();
				
				reportSDate =  dt;
				
			}
			if (edate!= null) {
				reportEDate = new SimpleDateFormat("yyyy-MM-dd").parse(edate);
			}
			else {
				reportEDate = new Date();
			}
			ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_org_report_result", SpOrgReportResult.class );
			call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(reportSDate);
			
			call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(reportEDate);
			
			

			ProcedureOutputs procedureOutputs = call.getOutputs();
			ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
			
			List<SpOrgReportResult> list = resultSetOutput.getResultList();

			
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}

		}
		 catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated method stub
			return null;
			
		}
	

		
		
	}

	
	@SuppressWarnings({"unchecked", "deprecation"})
	public JSONObject schedule(JSONObject obj) {
		
		JSONObject responseJson = new JSONObject(); 

		try {
		
			
			String reportSdate = obj.isNull("ReportScheduleTime") == true ? null : obj.getString("ReportScheduleTime");
			Date reportSDate = new SimpleDateFormat("yyyy-MM-dd").parse(reportSdate);
			
			Date reportEDate = WebDatetime.addDays(reportSDate, 1);
		
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_org_report_schedule");
		call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(reportSDate);
		
		call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(reportEDate);
		
		

		call.getOutputs();	
		
		responseJson.put("Message", "success");
		responseJson.put("Success", true);
		
		return responseJson;
			
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("Success", false);
			responseJson.put("Message", e.getStackTrace().toString());
			
			return responseJson;
		}
		
		
	}
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<OrgReport> getList(JSONObject obj) {
				
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReport.class);		
		
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
		
		List<OrgReport> list = cr.list();

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
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReport.class);		
		
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
		
		
		return cr.list().size();
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Object[]> getSumCount(JSONObject obj) {
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReport.class);	
		
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
		
		
		cr.setProjection(Projections.projectionList()
				.add(Projections.avg("memberCount").as("MemberCount"))
				.add(Projections.sum("signinCount").as("SigninCount"))
				.add(Projections.sum("newsCount").as("NewsCount"))
				.add(Projections.sum("activityCount").as("ActivityCount"))
				.add(Projections.sum("anaCount").as("AnaCount"))
				.add(Projections.sum("secbuzzerCount").as("SecbuzzerCount"))
				.add(Projections.groupProperty("orgId"))
				.add(Projections.groupProperty("name"))
				.add(Projections.groupProperty("ciLevel"))
				.add(Projections.groupProperty("signApplyTime"))
				);		
		
		return cr.list();	
		
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<OrgReport> getOrgReportByOrgId(String sdate, String edate , Long orgId) {
		
		
	
		
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReport.class);		

		cr.add(Restrictions.isNotNull("memberLog"));
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		if (orgId != null) {
			cr.add(Restrictions.eq("orgId", orgId));
		}
		
		List<OrgReport> list = cr.list();

		
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
		
		
	}


	@SuppressWarnings({"unchecked", "deprecation"})
	public boolean isOrgReportExistByReportDate(String reportCreateTime) {
		
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReport.class);		
		try {
		cr.add(Restrictions.eq("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(reportCreateTime)));
		}catch (ParseException e) {
			//e.printStackTrace();
		}
		
		List<OrgReport> list = cr.list();


		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}



	
	
	

}