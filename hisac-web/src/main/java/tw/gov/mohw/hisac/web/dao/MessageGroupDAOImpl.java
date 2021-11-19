package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MessageGroup;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MessageGroupDAOImpl extends BaseSessionFactory implements MessageGroupDAO {

	public void insert(MessageGroup entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MessageGroup entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MessageGroup entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MessageGroup get(Long id) {
		return getSessionFactory().getCurrentSession().get(MessageGroup.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessageGroup> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroup.class);
		List<MessageGroup> list = cr.list();
		cr.addOrder(Order.desc("name"));
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public MessageGroup getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroup.class);
		cr.add(Restrictions.eq("name", name));
		return (MessageGroup) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MessageGroup> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroup.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<MessageGroup> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageGroup.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));

		long total = (long) cr.list().get(0);
		return total;
	}

}
