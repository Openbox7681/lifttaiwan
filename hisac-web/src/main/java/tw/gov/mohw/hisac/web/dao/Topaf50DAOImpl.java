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

import tw.gov.mohw.hisac.web.domain.Topaf50;
import tw.gov.mohw.hisac.web.domain.Topres20;

@Repository
@Transactional
public class Topaf50DAOImpl extends BaseSessionFactory implements Topaf50DAO {

	public void insert(Topaf50 role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(Topaf50 role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(Topaf50 role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public Topaf50 get(Long id) {
		return (Topaf50) getSessionFactory().getCurrentSession().get(Topaf50.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Topaf50> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topaf50.class);
		List<Topaf50> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@SuppressWarnings({"deprecation", "unchecked"})

	public List<Object[]> getPie2DataByCondition(JSONArray classSubList) {
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topaf50.class);	
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
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("affiliation_e"))
		.add(Projections.countDistinct("id"));
		
		
		cr.add(Restrictions.ne("affiliation_e", "NULL"));
		
		
		cr.setProjection(projectionList);

		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
	}

	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Topaf50> getTopaf50ByCondition(JSONArray classSubList ){		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Topaf50.class);	
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
		cr.addOrder(Order.desc("tac"));

		cr.setMaxResults(20);

		List<Topaf50> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	;


	

}
