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

import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;

/**
 * 子系統服務
 */
@Repository
@Transactional
public class PaperKeywordClsDAOImpl extends BaseSessionFactory implements PaperKeywordClsDAO {

	

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<PaperKeywordClsLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperKeywordClsLift.class);
		List<PaperKeywordClsLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	

	
	

	@SuppressWarnings({"deprecation", "unchecked"})
	//給定搜尋條件取出關鍵字資料
	public List<PaperKeywordClsLift> getDataByClassSub(JSONArray classSubList) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperKeywordClsLift.class);
		
		
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
		
		cr.setMaxResults(30);

		
		
		
			
		
		List<PaperKeywordClsLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
		
	}

	
	
	
	
	//給定搜尋條件取出關鍵字對應之論文號碼資料
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getDataByKeyword(JSONArray keywordList , JSONArray classSubList){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperKeywordClsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		
		if(!classSubList.toString().contains("全部")) {
			Disjunction dis1 = Restrictions.disjunction();
			for(int i=0; i<classSubList.length(); i++) {
				JSONObject obj = (JSONObject) classSubList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
	                dis1.add(Restrictions.eq("class_sub", class_sub));
				}	
			}
	        cr.add(dis1);
		}
		

		
		Disjunction dis = Restrictions.disjunction();
		for(int i=0; i<keywordList.length(); i++) {
			JSONObject obj = (JSONObject) keywordList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String keyword = obj.getString("Name");
                dis.add(Restrictions.eq("keyword", keyword));
			}	
		}
        cr.add(dis);
		
        projectionList.add(Projections.groupProperty("keyword"))
		.add(Projections.groupProperty("paper_SerialNumber"));
		
		cr.setProjection(projectionList);

		cr.setMaxResults(300);
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	};
	
	


	

}
