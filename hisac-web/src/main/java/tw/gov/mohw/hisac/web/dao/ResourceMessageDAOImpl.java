package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.ResourceMessage;

/**
 * 資料庫資源檔服務
 */
@Repository
@Transactional
public class ResourceMessageDAOImpl extends BaseSessionFactory implements ResourceMessageDAO {

	public void insert(ResourceMessage entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(ResourceMessage entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(ResourceMessage entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public ResourceMessage get(Long id) {
		return getSessionFactory().getCurrentSession().get(ResourceMessage.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ResourceMessage> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ResourceMessage.class);
		List<ResourceMessage> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public ResourceMessage getByMessageKey(String messageKey) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ResourceMessage.class);
		cr.add(Restrictions.eq("messageKey", messageKey));
		return (ResourceMessage) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ResourceMessage> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String messageKey = obj.isNull("MessageKey") == true ? null : obj.getString("MessageKey");
		String messageValue = obj.isNull("MessageValue") == true ? null : obj.getString("MessageValue");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ResourceMessage.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (messageKey != null)
			cr.add(Restrictions.like("messageKey", "%" + messageKey + "%"));
		if (messageValue != null)
			cr.add(Restrictions.like("messageValue", "%" + messageValue + "%"));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ResourceMessage> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String messageKey = obj.isNull("MessageKey") == true ? null : obj.getString("MessageKey");
		String messageValue = obj.isNull("MessageValue") == true ? null : obj.getString("MessageValue");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ResourceMessage.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (messageKey != null)
			cr.add(Restrictions.like("messageKey", "%" + messageKey + "%"));
		if (messageValue != null)
			cr.add(Restrictions.like("messageValue", "%" + messageValue + "%"));

		long total = (long) cr.list().get(0);
		return total;
	}

}
