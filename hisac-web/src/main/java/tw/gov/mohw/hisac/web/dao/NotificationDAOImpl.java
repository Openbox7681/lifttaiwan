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

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNotification;

/**
 * 通報服務
 */
@Repository
@Transactional
public class NotificationDAOImpl extends BaseSessionFactory implements NotificationDAO {

	public void insert(Notification entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Notification entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Notification entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Notification get(String id) {
		return getSessionFactory().getCurrentSession().get(Notification.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Notification> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Notification.class);
		List<Notification> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Notification> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "applyDateTime" : obj.getString("sort");
		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parse(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parse(obj.getString("EApplyDateTime"));

		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		// 報表查詢使用
		// String sdate = obj.isNull("Sdate") == true ? null :
		// obj.getString("Sdate")+" 01:01:01";
		// String edate = obj.isNull("Edate") == true ? null :
		// obj.getString("Edate")+" 23:59:59";

		Date sdate = obj.isNull("Sdate") == true ? null : WebDatetime.parseSdate(obj.getString("Sdate"));
		Date edate = obj.isNull("Edate") == true ? null : WebDatetime.parseEdate(obj.getString("Edate"));

		int statusReport = obj.isNull("StatusReport") == true ? 0 : obj.getInt("StatusReport");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Notification.class);

		// 報表查詢使用
		if (sdate != null)
			cr.add(Restrictions.ge("applyDateTime", sdate));
		if (edate != null)
			cr.add(Restrictions.le("applyDateTime", edate));
		if (statusReport != 0)
			cr.add(Restrictions.ge("status", statusReport));

		///////////////////////////////////
		if (sApplyDateTime != null)
			cr.add(Restrictions.ge("applyDateTime", sApplyDateTime));
		if (eApplyDateTime != null)
			cr.add(Restrictions.le("applyDateTime", eApplyDateTime));

		if (status != null)
			cr.add(Restrictions.eq("status", status));
		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("postId", "%" + keyword + "%"), Restrictions.like("eventRemark", "%" + keyword + "%")));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));

		if (start != 0)
			cr.setFirstResult(start);

		if (maxRows != 0)
			cr.setMaxResults(maxRows);

		List<Notification> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parse(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parse(obj.getString("EApplyDateTime"));
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Notification.class);
		if (sApplyDateTime != null)
			cr.add(Restrictions.ge("applyDateTime", sApplyDateTime));
		if (eApplyDateTime != null)
			cr.add(Restrictions.le("applyDateTime", eApplyDateTime));
		if (status != null)
			cr.add(Restrictions.eq("status", status));
		if (keyword != null)
			cr.add(Restrictions.or(Restrictions.like("postId", "%" + keyword + "%"), Restrictions.like("eventRemark", "%" + keyword + "%")));
		cr.setProjection(Projections.rowCount());
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings("unchecked")
	public List<SpNotification> getSpList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		// String status = obj.isNull("Status") == true ? null :
		// obj.getString("Status");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");

		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EApplyDateTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_notification", SpNotification.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sApplyDateTime != null)
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).bindValue(sApplyDateTime);
		else
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eApplyDateTime != null)
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).bindValue(eApplyDateTime);
		else
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpNotification> list = resultSetOutput.getResultList();

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
		// String status = obj.isNull("Status") == true ? null :
		// obj.getString("Status");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EApplyDateTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_notification_size");

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sApplyDateTime != null)
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).bindValue(sApplyDateTime);
		else
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eApplyDateTime != null)
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).bindValue(eApplyDateTime);
		else
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	public long getSpFormCount(JSONObject obj) {
		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		// String status = obj.isNull("Status") == true ? null :
		// obj.getString("Status");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EApplyDateTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_notification_form_count");

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sApplyDateTime != null)
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).bindValue(sApplyDateTime);
		else
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eApplyDateTime != null)
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).bindValue(eApplyDateTime);
		else
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

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
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Keyword") == true ? "" : obj.getString("Keyword");
		Date sApplyDateTime = obj.isNull("SApplyDateTime") == true ? null : WebDatetime.parseSdate(obj.getString("SApplyDateTime"));
		Date eApplyDateTime = obj.isNull("EApplyDateTime") == true ? null : WebDatetime.parseEdate(obj.getString("EApplyDateTime"));
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_notification_button_count", SpButtonCount.class);

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		if (status != 0)
			call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);
		else
			call.registerParameter("Status", Integer.class, ParameterMode.IN).enablePassingNulls(true);

		if (sApplyDateTime != null)
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).bindValue(sApplyDateTime);
		else
			call.registerParameter("SApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eApplyDateTime != null)
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).bindValue(eApplyDateTime);
		else
			call.registerParameter("EApplyDateTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpButtonCount> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
