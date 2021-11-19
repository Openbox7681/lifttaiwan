package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;

/**
 * 最新消息管理
 */
@Repository
@Transactional
public class InformationManagementDAOImpl extends BaseSessionFactory implements InformationManagementDAO {
	public void insert(InformationManagement entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(InformationManagement entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(InformationManagement entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	public InformationManagement get(Long id) {
		return getSessionFactory().getCurrentSession().get(InformationManagement.class, id);
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationManagement> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");		
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");		
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");		
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagement.class);		
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}		
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (status != 0) {			
			cr.add(Restrictions.eq("status", (long)status));
		}
		if (keyword != null) {
			cr.add(Restrictions.or(Restrictions.like("title", "%" + keyword + "%"), Restrictions.like("content", "%" + keyword + "%")));
		}
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<InformationManagement> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {			
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");		
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");		
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationManagement.class);		
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}		
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (status != 0) {
			cr.add(Restrictions.eq("status", (long)status));
		}	
		if (keyword != null) {
			cr.add(Restrictions.or(Restrictions.like("title", "%" + keyword + "%"), Restrictions.like("content", "%" + keyword + "%")));
		}
		long total = (long) cr.list().size();
		return total;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<InformationManagement> getSpList(JSONObject obj) {

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		//
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_management", InformationManagement.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);		

		if (postDateTime != null)
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).bindValue(postDateTime);
		else
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (title != null)
			call.registerParameter("Title", String.class, ParameterMode.IN).bindValue(title);
		else
			call.registerParameter("Title", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<InformationManagement> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	
	
	public long getSpListSize(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		//
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_management_size");		
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);		

		if (postDateTime != null)
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).bindValue(postDateTime);
		else
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (title != null)
			call.registerParameter("Title", String.class, ParameterMode.IN).bindValue(title);
		else
			call.registerParameter("Title", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_management_button_count", SpButtonCount.class);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);	
		
		if (postDateTime != null)
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).bindValue(postDateTime);
		else
			call.registerParameter("PostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (title != null)
			call.registerParameter("Title", String.class, ParameterMode.IN).bindValue(title);
		else
			call.registerParameter("Title", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isEnable != null)
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).bindValue(isEnable);
		else
			call.registerParameter("IsEnable", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

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
	
	
	public long getSpFormCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_management_from_count");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;
	}

}