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
import tw.gov.mohw.hisac.web.domain.ViewWosClsPaper;

@Repository
@Transactional
public class ViewWosClsDAOImpl extends BaseSessionFactory implements ViewWosClsDAO {

	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewWosClsPaper> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewWosClsPaper.class);
		List<ViewWosClsPaper> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	

	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getWosCLsDataByCondition(JSONArray classSubList ,JSONArray countryList){		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewWosClsPaper.class);	
		
		
		ProjectionList projectionList = Projections.projectionList();        

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
		
		
		Disjunction disCountry  = Restrictions.disjunction();
		for(int i=0; i<countryList.length(); i++) {
			JSONObject obj = (JSONObject) countryList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String class_sub = obj.getString("Name");
				disCountry.add(Restrictions.eq("country", class_sub));
			}	
		}
        cr.add(disCountry);
        
        
        projectionList.add(Projections.groupProperty("field"))
        	.add(Projections.countDistinct("p_id").as("c_p_id"))
        	.add(Projections.countDistinct("paper_SerialNumber").as("c_paper_SerialNumber"))
        	.add(Projections.countDistinct("paper_corID").as("c_corID"))
        	.add(Projections.sum("ac").as("s_ac"))
        ;
		
        
		cr.setProjection(projectionList);
        
		
		
		cr.addOrder(Order.desc("s_ac"));

		cr.setMaxResults(20);

		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	;


	

}
