package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.PeopleMainsLift;
import tw.gov.mohw.hisac.web.domain.Topres20;

@Repository
@Transactional
public class Topres20DAOImpl extends BaseSessionFactory implements Topres20DAO {

	public void insert(Topres20 role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(Topres20 role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(Topres20 role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public Topres20 get(Long id) {
		return (Topres20) getSessionFactory().getCurrentSession().get(Topres20.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Topres20> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topres20.class);
		List<Topres20> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getTopres20ByCondition(JSONArray classSubList){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topres20.class);
				

		if(!classSubList.toString().contains("全部")) {
			Disjunction dis = Restrictions.disjunction();
			for(int i=0; i<classSubList.length(); i++) {
				JSONObject obj = (JSONObject) classSubList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
	                dis.add(Restrictions.eq("class_sub", class_sub));
				}	
			}
	        cr.add(dis);
		}
		
		
		cr.setProjection(Projections.projectionList()
				.add(Projections.sum("aac").as("AacCount"))
				.add(Projections.sum("con_num").as("CountCon"))
				.add(Projections.groupProperty("fullname"))
				.add(Projections.groupProperty("affiliation"))
				
				);
		cr.addOrder(Order.desc("AacCount"));

		cr.setMaxResults(20);

		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getTopres20CountryByClassSub(JSONArray classSubList){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topres20.class);
		
		if(!classSubList.toString().contains("全部")) {
			Disjunction dis = Restrictions.disjunction();
			for(int i=0; i<classSubList.length(); i++) {
				JSONObject obj = (JSONObject) classSubList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
	                dis.add(Restrictions.eq("class_sub", class_sub));
				}	
			}
	        cr.add(dis);
		}
        
        cr.add(Restrictions.ne("country", "NULL"));
        
        cr.add(Restrictions.isNotNull("con_num"));

        
		cr.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("class_sub"))
				.add(Projections.groupProperty("country"))
				.add(Projections.sum("con_num"))
	
				);
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getTopres20ByClassSub(JSONArray classSubList){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topres20.class);
		
		if(!classSubList.toString().contains("全部")) {
			Disjunction dis = Restrictions.disjunction();
			for(int i=0; i<classSubList.length(); i++) {
				JSONObject obj = (JSONObject) classSubList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
	                dis.add(Restrictions.eq("class_sub", class_sub));
				}	
			}
	        cr.add(dis);
		}
        
        cr.add(Restrictions.ne("country", "NULL"));
        
        cr.add(Restrictions.isNotNull("con_num"));

        
		cr.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("class_sub"))
				.add(Projections.sum("con_num"))
	
				);
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
		
	}

		

	

}
