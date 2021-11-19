package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MessageRecipient;

public class MessageRecipientDAOImpl extends BaseSessionFactory implements MessageRecipientDAO {

	@Transactional
	public void insert(MessageRecipient messageRecipient) {
		getSessionFactory().getCurrentSession().save(messageRecipient);
	}

	@Transactional
	public void update(MessageRecipient messageRecipient) {
		getSessionFactory().getCurrentSession().update(messageRecipient);
	}

	@Transactional
	public void delete(MessageRecipient messageRecipient) {
		getSessionFactory().getCurrentSession().delete(messageRecipient);
	}

	@Transactional
	public MessageRecipient get(Long id) {
		return (MessageRecipient) getSessionFactory().getCurrentSession().get(MessageRecipient.class, id);
	}		
	
	@SuppressWarnings({"unchecked", "deprecation"})
	@Transactional
	public List<MessageRecipient> getAll() {		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageRecipient.class);		
		List<MessageRecipient> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<MessageRecipient> getList(String json) {
		JSONObject obj = new JSONObject(json);
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");		
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");	
		String email = obj.isNull("Email") == true ? null : obj.getString("Email");	
		String mobilePhone = obj.isNull("MobilePhone") == true ? null : obj.getString("MobilePhone");	
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageRecipient.class);
		
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}	
		if (email != null) {
			cr.add(Restrictions.like("email", "%" + email + "%"));
		}	
		if (mobilePhone != null) {
			cr.add(Restrictions.like("mobilePhone", "%" + mobilePhone + "%"));
		}	
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<MessageRecipient> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	
	@SuppressWarnings("deprecation")
	@Transactional
	public long getListSize(String json) {
		JSONObject obj = new JSONObject(json);				
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");	
		String email = obj.isNull("Email") == true ? null : obj.getString("Email");	
		String mobilePhone = obj.isNull("MobilePhone") == true ? null : obj.getString("MobilePhone");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MessageRecipient.class);
		cr.setProjection(Projections.rowCount());		
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}	
		if (email != null) {
			cr.add(Restrictions.like("email", "%" + email + "%"));
		}	
		if (mobilePhone != null) {
			cr.add(Restrictions.like("mobilePhone", "%" + mobilePhone + "%"));
		}
		long total = (long) cr.list().get(0);
		return total;
	}
}
