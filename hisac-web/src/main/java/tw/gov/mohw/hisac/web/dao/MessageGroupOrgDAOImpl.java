package tw.gov.mohw.hisac.web.dao;

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
import tw.gov.mohw.hisac.web.domain.MessageGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMessageGroupOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MessageGroupOrgDAOImpl extends BaseSessionFactory implements MessageGroupOrgDAO {

	@Transactional

	public void insert(MessageGroupOrg entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MessageGroupOrg entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MessageGroupOrg entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MessageGroupOrg get(Long id) {
		return getSessionFactory().getCurrentSession().get(MessageGroupOrg.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessageGroupOrg> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroupOrg.class);
		List<MessageGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public MessageGroupOrg getByOrgId(Long orgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroupOrg.class);
		cr.add(Restrictions.eq("orgId", orgId));
		return (MessageGroupOrg) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessageGroupOrg> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long messageGroupId = obj.isNull("MessageGroupId") == true ? 0 : obj.getLong("MessageGroupId");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroupOrg.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (messageGroupId != 0)
			cr.add(Restrictions.eq("messageGroupId", messageGroupId));
		if (orgId != 0)
			cr.add(Restrictions.eq("orgId", orgId));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<MessageGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long messageGroupId = obj.isNull("MessageGroupId") == true ? 0 : obj.getLong("MessageGroupId");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroupOrg.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (messageGroupId != 0)
			cr.add(Restrictions.eq("messageGroupId", messageGroupId));
		if (orgId != 0)
			cr.add(Restrictions.eq("orgId", orgId));

		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessageGroupOrg> getByMessageGroupId(Long messageGroupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroupOrg.class);
		cr.add(Restrictions.eq("messageGroupId", messageGroupId));
		List<MessageGroupOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// @TsSuppressWarnings({"deprecation", "unchecked"})
	@SuppressWarnings("unchecked")
	public List<SpMessageGroupOrg> getSpList(JSONObject obj) {

		int messageGroupId = obj.isNull("MessageGroupId") == true ? 0 : obj.getInt("MessageGroupId");
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_message_group_org_name", SpMessageGroupOrg.class);
		call.registerParameter("MessageGroupId", Integer.class, ParameterMode.IN).bindValue(messageGroupId);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMessageGroupOrg> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
