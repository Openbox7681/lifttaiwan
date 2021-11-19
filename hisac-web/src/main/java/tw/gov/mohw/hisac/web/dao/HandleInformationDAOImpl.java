package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.HandleInformation;
import tw.gov.mohw.hisac.web.domain.NewsManagement;

public class HandleInformationDAOImpl extends BaseSessionFactory implements HandleInformationDAO {

	@Transactional
	public void insert(HandleInformation handleInformation) {
		getSessionFactory().getCurrentSession().save(handleInformation);
	}

	@Transactional
	public void update(HandleInformation handleInformation) {
		getSessionFactory().getCurrentSession().update(handleInformation);
	}

	@Transactional
	public void delete(HandleInformation handleInformation) {
		getSessionFactory().getCurrentSession().delete(handleInformation);
	}

	@Transactional
	public HandleInformation get(Long id) {
		return (HandleInformation) getSessionFactory().getCurrentSession().get(HandleInformation.class, id);
	}		
	
	@SuppressWarnings({"unchecked", "deprecation"})
	@Transactional
	public List<HandleInformation> getAll() {		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HandleInformation.class);		
		List<HandleInformation> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	@Transactional
	public List<HandleInformation> getList(String json) {
		JSONObject obj = new JSONObject(json);
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");		
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");	
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HandleInformation.class);
		
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}		
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<HandleInformation> list = cr.list();
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
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HandleInformation.class);
		cr.setProjection(Projections.rowCount());		
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}	
		long total = (long) cr.list().get(0);
		return total;
	}
}
