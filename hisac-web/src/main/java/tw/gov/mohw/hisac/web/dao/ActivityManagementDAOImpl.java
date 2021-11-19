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

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.ActivityManagement;
import tw.gov.mohw.hisac.web.domain.SpActivityManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 最新活動管理
 */
@Repository
@Transactional
public class ActivityManagementDAOImpl extends BaseSessionFactory implements ActivityManagementDAO {
	
	private String user = WebCrypto.getUSER();


	public void insert(ActivityManagement entity) {
		try {
			if (user.equals("admin")&& entity != null){
			getSessionFactory().getCurrentSession().save(entity);
			}
		}
		catch(Exception ex) {
		}
		
	}

	public void update(ActivityManagement entity) {
		try {
			if (user.equals("admin")&& entity != null){
				getSessionFactory().getCurrentSession().update(entity);
			}
		}
		catch(Exception ex) {
		}
	}

	public void delete(ActivityManagement entity) {
		try {
			if (user.equals("admin")&& entity != null){
				getSessionFactory().getCurrentSession().delete(entity);
			}
		}
		catch(Exception ex) {
		}
	}

	public ActivityManagement get(Long id) {
		try {
			if (user.equals("admin")&& id != null){
			return getSessionFactory().getCurrentSession().get(ActivityManagement.class, id);
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			return null;
		}
		
	}

	@SuppressWarnings("deprecation")
	public ViewActivityManagementMember getByDetail(Long id) {
		try {
			Long status = (long) 4;
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewActivityManagementMember.class);
			cr.add(Restrictions.eq("id", id));
			cr.add(Restrictions.eq("isEnable", true));
			cr.add(Restrictions.eq("status", status));
			return (ViewActivityManagementMember) cr.uniqueResult();
		}
		catch(Exception ex) {
			return null;
		}
		
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ActivityManagement> getAll() {
		
		try {
		String postType = "2";
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ActivityManagement.class);
		cr.add(Restrictions.eq("postType", postType));
		List<ActivityManagement> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		}
		catch(Exception ex) {
			return null;
		}
		
		
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewActivityManagementMember> getList(JSONObject obj) {
		
		try {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");
		String postType = "2";
		String startShowDateTime = obj.isNull("StartShowDateTime") == true ? null : obj.getString("StartShowDateTime");
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");
		String startPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String endPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		String content = obj.isNull("Content") == true ? null : obj.getString("Content");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		String startDateTime = obj.isNull("StartDateTime") == true ? null : obj.getString("StartDateTime");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewActivityManagementMember.class);
		if (postType != null) {
			cr.add(Restrictions.eq("postType", postType));
		}
		if (startShowDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}
		if (startPostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (endPostDateTime != null) {
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(endPostDateTime, "yyyy-MM-dd")));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (content != null) {
			cr.add(Restrictions.like("content", "%" + content + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isPublic != null) {
			cr.add(Restrictions.eq("isPublic", isPublic));
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
		List<ViewActivityManagementMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		}
		catch(Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpActivityManagementReport> getReport(JSONObject obj) {
		try {
		String startPostDateTime = obj.isNull("Sdate") == true ? null : obj.getString("Sdate") + " 00:00:00";
		String endPostDateTime = obj.isNull("Edate") == true ? null : obj.getString("Edate") + " 00:00:00";

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management_report", SpActivityManagementReport.class);

		if (startPostDateTime != null) {
			call.registerParameter("StartPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.parse(startPostDateTime, "yyyy-MM-dd 00:00:00"));
		}
		if (endPostDateTime != null) {
			call.registerParameter("EndPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.addDays(WebDatetime.parse(endPostDateTime, "yyyy-MM-dd 00:00:00"), 1));
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpActivityManagementReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
	}
	catch(Exception ex) {
		return null;
	}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		try {
		String postType = "2";
		String startShowDateTime = obj.isNull("StartShowDateTime") == true ? null : obj.getString("StartShowDateTime");
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");
		String startPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String endPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		String content = obj.isNull("Content") == true ? null : obj.getString("Content");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		String startDateTime = obj.isNull("StartDateTime") == true ? null : obj.getString("StartDateTime");
		Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewActivityManagementMember.class);
		cr.setProjection(Projections.rowCount());
		if (postType != null) {
			cr.add(Restrictions.eq("postType", postType));
		}
		if (startShowDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}
		if (startPostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (endPostDateTime != null) {
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(endPostDateTime, "yyyy-MM-dd")));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (content != null) {
			cr.add(Restrictions.like("content", "%" + content + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isPublic != null) {
			cr.add(Restrictions.eq("isPublic", isPublic));
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
		catch(Exception ex) {
			return 0;
		}
	}

	public ViewMessageAlertEvent getById(String id) {
		try {
		return getSessionFactory().getCurrentSession().get(ViewMessageAlertEvent.class, id);
		}
		catch(Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ViewActivityManagementMember> getSpList(JSONObject obj) {
		
		try {
			

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postDateTime" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		String postType = "2";
		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		Date startDateTime = obj.isNull("StartDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("StartDateTime")); // 公開資訊首頁查詢條件
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management", ViewActivityManagementMember.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("PostType", String.class, ParameterMode.IN).bindValue(postType);

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

		if (isPublic != null)
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).bindValue(isPublic);
		else
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

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
		List<ViewActivityManagementMember> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		}
		catch(Exception ex) {
			return null;
		}

	}

	public long getSpListSize(JSONObject obj) {
		
		try {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		String postType = "2";
		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		Date startDateTime = obj.isNull("StartDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("StartDateTime")); // 公開資訊首頁查詢條件
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		// ProcedureCall call =
		// getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management_size",
		// ViewActivityManagementMember.class);
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management_size");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("PostType", String.class, ParameterMode.IN).bindValue(postType);

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

		if (isPublic != null)
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).bindValue(isPublic);
		else
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

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
		catch(Exception ex) {
			return 0;
		}

	}

	public long getSpFormCount(JSONObject obj) {
		try {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management_from_count");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;
		
		}
		catch(Exception ex) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {
		
		try {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		String postType = "2";
		Date postDateTime = obj.isNull("PostDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("PostDateTime"));
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_activity_management_button_count", SpButtonCount.class);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("PostType", String.class, ParameterMode.IN).bindValue(postType);

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

		if (isPublic != null)
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).bindValue(isPublic);
		else
			call.registerParameter("IsPublic", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

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
		catch(Exception ex) {
			return null;
		}
	}

}