package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.SpOrgReport;
import tw.gov.mohw.hisac.web.domain.SpSigninCountTop10;
import tw.gov.mohw.hisac.web.domain.SpSystemLogByOrgTop5;
import tw.gov.mohw.hisac.web.domain.SpWebSiteLoad;
import tw.gov.mohw.hisac.web.domain.SystemLog;

@Repository
@Transactional
public class SystemLogDAOImpl extends BaseSessionFactory implements SystemLogDAO {

	public void insert(SystemLog entity) {
		String hashCode = WebCrypto.getHash(WebCrypto.HashType.SHA512,
				entity.getAppName() + entity.getFuncName() + entity.getInputValue() + entity.getActionName() + entity.getStatus() + entity.getIp() + entity.getCreateAccount() + entity.getCreateTime().getTime());
		entity.setHashCode(hashCode);
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(SystemLog entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(SystemLog entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public SystemLog get(Long id) {
		return getSessionFactory().getCurrentSession().get(SystemLog.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SystemLog> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemLog.class);
		List<SystemLog> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SystemLog> getList(JSONObject obj) {
		try {
			
			obj = new JSONObject(WebCrypto.getSafe(obj.toString()));

			
			
			int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
			int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
			boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
			String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
	
	
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
	
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemLog.class);
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
			if (dir == true)
				cr.addOrder(Order.desc(sort));
			else
				cr.addOrder(Order.asc(sort));
			cr.setFirstResult(start);
			if (maxRows != 0)
				cr.setMaxResults(maxRows);	
			List<SystemLog> list = cr.list();
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		}catch (Exception ex){
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SystemLog> getListForLoginLog(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		String funcName = obj.isNull("FuncName") == true ? null : obj.getString("FuncName");
		String inputValue = obj.isNull("InputValue") == true ? null : obj.getString("InputValue");
		String actionName = obj.isNull("ActionName") == true ? null : obj.getString("ActionName");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String ip = obj.isNull("Ip") == true ? null : obj.getString("Ip");		
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemLog.class);		
		if (funcName != null) {
			cr.add(Restrictions.eq("funcName", funcName));
		}
		if (inputValue != null) {			
			cr.add(Restrictions.or(Restrictions.like("inputValue", inputValue + " %"), Restrictions.eq("inputValue", inputValue)));
		}
		if (actionName != null) {
			cr.add(Restrictions.eq("actionName", actionName));
		}
		if (status != null) {
			cr.add(Restrictions.like("status", "%" + status + "%"));
		}
		if (ip != null) {
			cr.add(Restrictions.like("ip", "%" + ip + "%"));
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
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<SystemLog> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpWebSiteLoad> getWebSiteLoad(JSONObject obj) {
		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate"));
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_website_load", SpWebSiteLoad.class);

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
		List<SpWebSiteLoad> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
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
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemLog.class);
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
	
	
	@SuppressWarnings("deprecation")
	public long getListSizeForLoginLog(JSONObject obj) {				
		String funcName = obj.isNull("FuncName") == true ? null : obj.getString("FuncName");
		String inputValue = obj.isNull("InputValue") == true ? null : obj.getString("InputValue");
		String actionName = obj.isNull("ActionName") == true ? null : obj.getString("ActionName");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String ip = obj.isNull("Ip") == true ? null : obj.getString("Ip");		
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SystemLog.class);
		cr.setProjection(Projections.rowCount());
		if (funcName != null) {
			cr.add(Restrictions.eq("funcName", funcName));
		}
		if (inputValue != null) {
			cr.add(Restrictions.or(Restrictions.like("inputValue", inputValue + " %"), Restrictions.eq("inputValue", inputValue)));
		}
		if (actionName != null) {
			cr.add(Restrictions.eq("actionName", actionName));
		}
		if (status != null) {
			cr.add(Restrictions.like("status", "%" + status + "%"));
		}
		if (ip != null) {
			cr.add(Restrictions.like("ip", "%" + ip + "%"));
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
	
	@SuppressWarnings("unchecked")
	public List<SpSigninCountTop10> getSignTop10(JSONObject obj) {
		String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");						

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_signin_count_top10", SpSigninCountTop10.class);
			
		try {
			if (querySdate != null)
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(querySdate));
			else
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
			if (queryEdate != null)
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(queryEdate));
			else
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).enablePassingNulls(true);			
		}	catch (ParseException e) {
				//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpSigninCountTop10> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpSystemLogByOrgTop5> getSpSystemLogByOrgTop5(JSONObject obj) {
		String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");	
		String appName = obj.isNull("AppName") == true ? null : obj.getString("AppName");
		String funcName = obj.isNull("FuncName") == true ? null : obj.getString("FuncName");
		String actionName = obj.isNull("ActionName") == true ? null : obj.getString("ActionName");		

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_systemlog_byorg_top5", SpSystemLogByOrgTop5.class);
			
		try {
			if (querySdate != null)
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(querySdate));
			else
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
			if (queryEdate != null)
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(queryEdate));
			else
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).enablePassingNulls(true);		
			call.registerParameter("AppName", String.class, ParameterMode.IN).bindValue(appName);
			call.registerParameter("FuncName", String.class, ParameterMode.IN).bindValue(funcName);
			call.registerParameter("ActionName", String.class, ParameterMode.IN).bindValue(actionName);
		}	catch (ParseException e) {
				//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpSystemLogByOrgTop5> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpSystemLogByOrgTop5> getSpSystemLogByOrg(JSONObject obj) {
		String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");	
		String appName = obj.isNull("AppName") == true ? null : obj.getString("AppName");
		String funcName = obj.isNull("FuncName") == true ? null : obj.getString("FuncName");
		String inputValue = obj.isNull("InputValue") == true ? null : obj.getString("InputValue");				

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_systemlog_byorg", SpSystemLogByOrgTop5.class);
			
		try {
			if (querySdate != null)
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(querySdate));
			else
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
			if (queryEdate != null)
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(queryEdate));
			else
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).enablePassingNulls(true);		
			call.registerParameter("AppName", String.class, ParameterMode.IN).bindValue(appName);
			call.registerParameter("FuncName", String.class, ParameterMode.IN).bindValue(funcName);
			call.registerParameter("InputValue", String.class, ParameterMode.IN).bindValue(inputValue);
		}	catch (ParseException e) {
				//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpSystemLogByOrgTop5> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpOrgReport> getOrgReport(JSONObject obj) {
		String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");						

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_org_report", SpOrgReport.class);
			
		try {
			if (querySdate != null)
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(querySdate));
			else
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
			if (queryEdate != null)
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(queryEdate));
			else
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).enablePassingNulls(true);			
		}	catch (ParseException e) {
				//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpOrgReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}