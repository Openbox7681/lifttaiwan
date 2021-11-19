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

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MessageDAOImpl extends BaseSessionFactory implements MessageDAO {

	public void insert(Message entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Message entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Message entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Message get(String id) {
		return getSessionFactory().getCurrentSession().get(Message.class, id);
	}

	public ViewMessageAlertEvent getById(String id) {
		return getSessionFactory().getCurrentSession().get(ViewMessageAlertEvent.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Message> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Message.class);
		List<Message> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewMessageAlertEvent> getList(JSONObject obj) {

		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "postId" : obj.getString("sort");
		String sCreateTime = obj.isNull("SCreateTime") == true ? null : obj.getString("SCreateTime");
		String eCreateTime = obj.isNull("ECreateTime") == true ? null : obj.getString("ECreateTime");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMessageAlertEvent.class);

		try {
			if (sCreateTime != null) {
				cr.add(Restrictions.ge("postDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sCreateTime)));
			}
			if (eCreateTime != null) {
				cr.add(Restrictions.le("postDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eCreateTime)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		if (status != null)
			cr.add(Restrictions.like("status", "%" + status + "%"));
		if (isReply != null)
			cr.add(Restrictions.eq("isReply", isReply));
		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("postId", "%" + keyword + "%"), Restrictions.like("subject", "%" + keyword + "%")));
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		List<ViewMessageAlertEvent> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		String sCreateTime = obj.isNull("SCreateTime") == true ? null : obj.getString("SCreateTime");
		String eCreateTime = obj.isNull("ECreateTime") == true ? null : obj.getString("ECreateTime");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMessageAlertEvent.class);
		cr.setProjection(Projections.rowCount());

		try {
			if (sCreateTime != null) {
				cr.add(Restrictions.ge("postDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sCreateTime)));
			}
			if (eCreateTime != null) {
				cr.add(Restrictions.le("postDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eCreateTime)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}

		if (status != null)
			cr.add(Restrictions.like("status", "%" + status + "%"));
		if (isReply != null)
			cr.add(Restrictions.eq("isReply", isReply));
		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("postId", "%" + keyword + "%"), Restrictions.like("subject", "%" + keyword + "%")));
		long total = (long) cr.list().get(0);
		return total;

	}

	@SuppressWarnings("unchecked")
	public List<Message> getSpList(JSONObject obj) {

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sCreateTime = obj.isNull("SCreateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SCreateTime"));
		Date eCreateTime = obj.isNull("ECreateTime") == true ? null : WebDatetime.parseEdate(obj.getString("ECreateTime"));
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message", Message.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != null)
			call.registerParameter("Status", String.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isReply != null)
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).bindValue(isReply);
		else
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (sCreateTime != null)
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).bindValue(sCreateTime);
		else
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eCreateTime != null)
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).bindValue(eCreateTime);
		else
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<Message> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	public long getSpListSize(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sCreateTime = obj.isNull("SCreateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SCreateTime"));
		Date eCreateTime = obj.isNull("ECreateTime") == true ? null : WebDatetime.parseEdate(obj.getString("ECreateTime"));
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_size");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != null)
			call.registerParameter("Status", String.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isReply != null)
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).bindValue(isReply);
		else
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (sCreateTime != null)
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).bindValue(sCreateTime);
		else
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eCreateTime != null)
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).bindValue(eCreateTime);
		else
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	public long getSpFormCount(JSONObject obj) {
		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sCreateTime = obj.isNull("SCreateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SCreateTime"));
		Date eCreateTime = obj.isNull("ECreateTime") == true ? null : WebDatetime.parseEdate(obj.getString("ECreateTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_form_count");

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != null)
			call.registerParameter("Status", String.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isReply != null)
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).bindValue(isReply);
		else
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (sCreateTime != null)
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).bindValue(sCreateTime);
		else
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eCreateTime != null)
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).bindValue(eCreateTime);
		else
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;
	}

	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sCreateTime = obj.isNull("SCreateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SCreateTime"));
		Date eCreateTime = obj.isNull("ECreateTime") == true ? null : WebDatetime.parseEdate(obj.getString("ECreateTime"));
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_button_count", SpButtonCount.class);

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != null)
			call.registerParameter("Status", String.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (isReply != null)
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).bindValue(isReply);
		else
			call.registerParameter("IsReply", Boolean.class, ParameterMode.IN).enablePassingNulls(true);

		if (sCreateTime != null)
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).bindValue(sCreateTime);
		else
			call.registerParameter("SpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eCreateTime != null)
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).bindValue(eCreateTime);
		else
			call.registerParameter("EpostDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpButtonCount> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// NativeQuery 的寫法
	// @SuppressWarnings("deprecation")
	// public long getListSize(JSONObject obj) {
	//
	//
	//
	// int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
	// int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
	// String dir = obj.getBoolean("dir")==true? "desc":"asc";
	// String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
	//
	//
	// String spostDateTime = obj.isNull("SPostDateTime") == true ? null :
	// obj.getString("SPostDateTime");
	// String epostDateTime = obj.isNull("EPostDateTime") == true ? null :
	// obj.getString("EPostDateTime");
	// String status = obj.isNull("Status") == true ? null :
	// obj.getString("Status");
	// Boolean isReply = obj.isNull("IsReply") == true ? null :
	// obj.getBoolean("IsReply");
	// String keyword = obj.isNull("Keyword") == true ? null :
	// obj.getString("Keyword");
	//
	//
	//
	// String sql ="SELECT a.*, "
	// + "b.Name AS AlertTypeName, "
	// + "c.Name AS EventTypeName, "
	// + " d.Name AS SourceName, "
	// + " e.Name AS ModifyName"
	// + " FROM message a, alert_type b ,event_type c,information_source
	// d,member e "
	// + " where a.AlertCode = b.Code "
	// + " and a.EventCode = c.Code "
	// + " and a.SourceCode = d.Code "
	// + " and a.ModifyId = e.Id"
	// + " and a.Status = Isnull(:Status, a.Status)"
	// + " and a.IsReply = Isnull(:IsReply, a.IsReply)"
	// + " and a.PostDateTime >= Isnull(:SpostDateTime, a.PostDateTime)"
	// + " and a.PostDateTime <= Isnull(:EpostDateTime, a.PostDateTime)"
	// + " and ( a.PostId = Isnull(:Keyword, a.PostId) or a.Subject =
	// Isnull(:Keyword, a.Subject) )"
	// + " ORDER BY a."+sort+" "+dir+"";
	//
	//
	// NativeQuery<ViewMessageAlertEvent> query =
	// getSessionFactory().getCurrentSession().createNativeQuery(sql,ViewMessageAlertEvent.class);
	//
	// query.setParameter("SpostDateTime", spostDateTime);
	// query.setParameter("EpostDateTime", epostDateTime);
	// query.setParameter("Status", status);
	// query.setParameter("EpostDateTime", epostDateTime);
	//
	// query.setFirstResult(start);
	// query.setMaxResults(maxRows);
	//
	// List<ViewMessageAlertEvent> list = query.getResultList();
	//
	// if (list.size() > 0) {
	// return list;
	// } else {
	// return null;
	// }
	//
	//
	//
	// }

}
