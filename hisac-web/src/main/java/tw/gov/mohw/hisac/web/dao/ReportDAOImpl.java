package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.domain.Report;
import tw.gov.mohw.hisac.web.domain.SystemLog;


/**
 * 表單服務
 */
@Repository
@Transactional
public class ReportDAOImpl extends BaseSessionFactory implements ReportDAO {
	
	public void schedule() {
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_report_schedule");
		call.getOutputs();		
	}
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Object[]> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "createTime" : obj.getString("sort");		
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		String createAccount = obj.isNull("CreateAccount") == true ? null : obj.getString("CreateAccount");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Report.class);		
		cr.add(Restrictions.eq("funcName", "QueryById"));
		cr.add(Restrictions.eq("actionName", "Read"));
		if (createAccount != null) {
			cr.add(Restrictions.eq("createAccount", createAccount));
		}
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		cr.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("appName"))
				.add(Projections.groupProperty("createTime"))
				.add(Projections.rowCount(), "count"));	
		return cr.list();		
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public long getListSize(JSONObject obj) {
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		String createAccount = obj.isNull("CreateAccount") == true ? null : obj.getString("CreateAccount");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Report.class);		
		cr.add(Restrictions.eq("funcName", "QueryById"));
		cr.add(Restrictions.eq("actionName", "Read"));
		if (createAccount != null) {
			cr.add(Restrictions.eq("createAccount", createAccount));
		}
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}		
		cr.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("appName"))
				.add(Projections.groupProperty("createTime"))
				.add(Projections.rowCount(), "count"));	
		return cr.list().size();
	}
	@SuppressWarnings("deprecation")
	public long getDownAttachListSize(JSONObject obj) {
		try {
			obj = new JSONObject(WebCrypto.getSafe(obj.toString()));

			
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String appName = obj.isNull("AppName") == true ? null : obj.getString("AppName");
			String funcName = obj.isNull("FuncName") == true ? null : obj.getString("FuncName");
			String inputValue = obj.isNull("InputValue") == true ? null : obj.getString("InputValue");
			String actionName = obj.isNull("ActionName") == true ? null : obj.getString("ActionName");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			String ip = obj.isNull("Ip") == true ? null : obj.getString("Ip");
			String hashCode = obj.isNull("HashCode") == true ? null : obj.getString("HashCode");
			String createAccount = obj.isNull("CreateAccount") == true ? null : obj.getString("CreateAccount");
			String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
			String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
			String distinct = obj.isNull("Distinct") == true ? null : obj.getString("Distinct");
			
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Report.class);
			if (distinct != null)
				cr.setProjection(Projections.distinct(Projections.countDistinct(distinct)));
			else
				cr.setProjection(Projections.rowCount());
			if (id != 0) {
				cr.add(Restrictions.eq("id", id));
			}
			if (appName != null) {
				cr.add(Restrictions.like("appName", "%" + appName + "%"));
			}
			if (funcName != null) {
				cr.add(Restrictions.like("funcName", "%" + funcName + "%"));
			}
			if (inputValue != null) {
				cr.add(Restrictions.like("inputValue", "%" + inputValue + "%"));
			}
			if (actionName != null) {
				cr.add(Restrictions.like("actionName", "%" + actionName + "%"));
			}
			if (status != null) {
				cr.add(Restrictions.like("status", "%" + status + "%"));
			}
			if (ip != null) {
				cr.add(Restrictions.like("ip", "%" + ip + "%"));
			}
			if (hashCode != null) {
				cr.add(Restrictions.like("hashCode", "%" + hashCode + "%"));
			}
			if (createAccount != null) {
				cr.add(Restrictions.like("createAccount", "%" + createAccount + "%"));
			}
			try {
				if (sdate != null) {
					cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdate)));
				}
				if (edate != null) {
					cr.add(Restrictions.le("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(edate)));
				}
			} catch (ParseException e) {
				//e.printStackTrace();
			}		
			long total = (long) cr.list().get(0);
			return total;
			
		}
		catch (Exception e) {
			return 0;
		}
		
		
		
	}

		
	
	
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Object[]> getTop10Detail(JSONObject obj) {			
		String date = obj.isNull("date") == true ? null : obj.getString("date");		
		String appName = obj.isNull("AppName") == true ? null : obj.getString("AppName");
		String createAccount = obj.isNull("CreateAccount") == true ? null : obj.getString("CreateAccount");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Report.class);		
		cr.add(Restrictions.eq("funcName", "QueryById"));
		cr.add(Restrictions.eq("actionName", "Read"));
		
		if (appName != null) {
			cr.add(Restrictions.eq("appName", appName));
		}
		
		if (createAccount != null) {
			cr.add(Restrictions.eq("createAccount", createAccount));
		}
		try {
			if (date != null) {
				cr.add(Restrictions.eq("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(date)));
			}			
		} catch (ParseException e) {
			//e.printStackTrace();
		}		
		cr.addOrder(Order.desc("count"));		
		cr.setFirstResult(0);
		cr.setMaxResults(10);
		cr.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("inputValue"))				
				.add(Projections.rowCount(), "count"));	
		return cr.list();		
	}

}