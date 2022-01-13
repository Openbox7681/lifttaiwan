package tw.gov.mohw.hisac.web.dao;

import java.util.ArrayList;
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

import tw.gov.mohw.hisac.web.domain.ViewPeoplePaper;


/**
 * 子系統服務
 */
@Repository
@Transactional
public class PeopleMainsLiftDAOImpl extends BaseSessionFactory implements PeopleMainsLiftDAO {

	public void insert(PeopleMainsLift entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(PeopleMainsLift entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(PeopleMainsLift entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public PeopleMainsLift get(Long id) {
		return getSessionFactory().getCurrentSession().get(PeopleMainsLift.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<PeopleMainsLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		List<PeopleMainsLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public PeopleMainsLift getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		cr.add(Restrictions.eq("code", code));
		return (PeopleMainsLift) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public PeopleMainsLift getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		cr.add(Restrictions.eq("name", name));
		return (PeopleMainsLift) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<PeopleMainsLift> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		Boolean number = obj.isNull("number") == true ? null : obj.getBoolean("number");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if(number) {
			cr.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("p_id")));	
		}
		
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<PeopleMainsLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getMechanism(JSONObject obj, String[] country) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		if(country != null) {
			cr.add(Restrictions.in("country", country));
		}
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("affiliations_cor_c"))
		        .add(Projections.groupProperty("affiliations_cor_e"))
		        .add(Projections.groupProperty("country"))
		        .add(Projections.countDistinct("p_id").as("number"));
		
		cr.setProjection(projectionList);
		cr.addOrder(Order.desc("number"));
		
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	//取得p3 top10 out-bound 機構資料
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getTop10Mechanism() {
		
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		
		cr.add(Restrictions.eq("inout_class", "out-bound"));
		cr.add(Restrictions.ne("affiliations_cor_e", "NULL"));
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("affiliations_cor_e"))
		        .add(Projections.groupProperty("country_name"))
		        .add(Projections.count("p_id").as("number"));
		
		cr.setProjection(projectionList);
		cr.addOrder(Order.desc("number"));
		cr.setMaxResults(10);
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getMapData() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("class_main"))
		        .add(Projections.groupProperty("country_name"))
		        .add(Projections.count("id"));
		
		cr.setProjection(projectionList);
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getFormData() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("class_main"))
		        .add(Projections.groupProperty("identify"))
		        .add(Projections.countDistinct("p_id"));
		
		cr.setProjection(projectionList);
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@SuppressWarnings({"deprecation", "unchecked"})
	//給定搜尋條件取出機構數量資料
	public List<Object[]> getPie1DataByCondition(JSONArray classSubList) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		
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
		projectionList.add(Projections.groupProperty("affiliations_cor_e"))
		        .add(Projections.countDistinct("p_id"));
		
		cr.add(Restrictions.ne("affiliations_cor_e", "NULL"));
		
		
		cr.setProjection(projectionList);
		
			
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
		
	}

	
	
	
	
	
	public List<Object[]> getAllCountry(){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("country"))
		.add(Projections.countDistinct("p_id"));
		
		
		cr.setProjection(projectionList);
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	};
	
	//輔助追蹤查詢領域與國家
	@SuppressWarnings({"deprecation", "unchecked"})

	public List<Object[]> getCountryDataByCondition(Long startYear , Long endYear , JSONArray classSubList , JSONArray countryList
			,int classMainOption){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		cr.add(Restrictions.ge("years", startYear));
		
		cr.add(Restrictions.le("years", endYear));
		
		
		Disjunction disCountry  = Restrictions.disjunction();
		for(int i=0; i<countryList.length(); i++) {
			JSONObject obj = (JSONObject) countryList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String class_sub = obj.getString("Name");
				disCountry.add(Restrictions.eq("country", class_sub));
			}	
		}
        cr.add(disCountry);
		
		
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
		switch (classMainOption) {
			case 1:
				cr.add(Restrictions.eq("class_main", "資訊及數位相關產業"));
				break;
			case 2:
				cr.add(Restrictions.eq("class_main", "臺灣精準健康戰略產業"));
				break;
			case 3 : 
				cr.add(Restrictions.eq("class_main", "國防及戰略產業"));
				break;
			case 4 :
				cr.add(Restrictions.eq("class_main", "民生及戰備產業"));
				break;
			case 5 :
				cr.add(Restrictions.eq("class_main", "綠電及再生能源產業"));
				break;
			case 6:
				cr.add(Restrictions.eq("class_main", "其他"));
				break;
			
		}
		
		projectionList.add(Projections.groupProperty("country"))
		.add(Projections.groupProperty("identify"))
        .add(Projections.count("country"));
		
		cr.setProjection(projectionList);

		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		
		
		
	}
	//B1成果分析-總輔助人數
		@SuppressWarnings({"deprecation", "unchecked"})
		public List<Object[]> getDataByCondition( JSONArray classSubList , JSONArray countryList) {
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
			
			ProjectionList projectionList = Projections.projectionList();        
			
			
			
			Disjunction disCountry  = Restrictions.disjunction();
			for(int i=0; i<countryList.length(); i++) {
				JSONObject obj = (JSONObject) countryList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
					disCountry.add(Restrictions.eq("country", class_sub));
				}	
			}
	        cr.add(disCountry);
			
			
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
			
			projectionList.add(Projections.count("id"))
			.add(Projections.groupProperty("identify"));
			
			cr.setProjection(projectionList);

			
			List<Object[]> list = cr.list();
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		}
		
	

	
	//B1成果分析-論文發表總數
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getPeoplePaperByCondition( JSONArray classSubList , JSONArray countryList) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewPeoplePaper.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		
		
		
		Disjunction disCountry  = Restrictions.disjunction();
		for(int i=0; i<countryList.length(); i++) {
			JSONObject obj = (JSONObject) countryList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String class_sub = obj.getString("Name");
				disCountry.add(Restrictions.eq("country", class_sub));
			}	
		}
        cr.add(disCountry);
		
		
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
		
		projectionList.add(Projections.count("id")).
			add(Projections.groupProperty("identify"));
		
		cr.setProjection(projectionList);

		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	//B1成果分析-2016-2019論文發表總數
		@SuppressWarnings({"deprecation", "unchecked"})
		public List<Object[]> getPeoplePaperByCondition2016_2019( JSONArray classSubList , JSONArray countryList) {
				Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewPeoplePaper.class);
				
				ProjectionList projectionList = Projections.projectionList();        
				
				
				
				Disjunction disCountry  = Restrictions.disjunction();
				for(int i=0; i<countryList.length(); i++) {
					JSONObject obj = (JSONObject) countryList.get(i);
					if (obj.getBoolean("Flag") == true) {
						String class_sub = obj.getString("Name");
						disCountry.add(Restrictions.eq("country", class_sub));
					}	
				}
		        cr.add(disCountry);
				
				
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
				
				
			    ArrayList<Long> arrInt = new ArrayList<Long>();
			    arrInt.add((long) 2016);
			    arrInt.add((long) 2018);
			    arrInt.add((long) 2019);


				cr.add(Restrictions.in("publishYear",  arrInt));
				
				projectionList.add(Projections.count("id")).
				add(Projections.groupProperty("identify"));
				
				cr.setProjection(projectionList);

				
				List<Object[]> list = cr.list();
				if (list.size() > 0) {
					return list;
				} else {
					return null;
				}
			}
		
		//B1成果分析-輔助前
		@SuppressWarnings({"deprecation", "unchecked"})
		public List<Object[]> getPeoplePaperByConditionBefore( JSONArray classSubList , JSONArray countryList) {
				Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewPeoplePaper.class);
				
				ProjectionList projectionList = Projections.projectionList();        
				
				
				
				Disjunction disCountry  = Restrictions.disjunction();
				for(int i=0; i<countryList.length(); i++) {
					JSONObject obj = (JSONObject) countryList.get(i);
					if (obj.getBoolean("Flag") == true) {
						String class_sub = obj.getString("Name");
						disCountry.add(Restrictions.eq("country", class_sub));
					}	
				}
		        cr.add(disCountry);
				
				
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
				
				
			  


				cr.add(Restrictions.geProperty("years", "publishYear"));
				
				
				
				projectionList.add(Projections.count("id")).
				add(Projections.groupProperty("identify"));
				
				cr.setProjection(projectionList);

				
				List<Object[]> list = cr.list();
				if (list.size() > 0) {
					return list;
				} else {
					return null;
				}
			}
		
		//B1成果分析-輔助後
		@SuppressWarnings({"deprecation", "unchecked"})
		public List<Object[]> getPeoplePaperByConditionAfter( JSONArray classSubList , JSONArray countryList) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewPeoplePaper.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		
		
		
		Disjunction disCountry  = Restrictions.disjunction();
		for(int i=0; i<countryList.length(); i++) {
			JSONObject obj = (JSONObject) countryList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String class_sub = obj.getString("Name");
				disCountry.add(Restrictions.eq("country", class_sub));
			}	
		}
        cr.add(disCountry);
		
		
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
		
		

		cr.add(Restrictions.gtProperty("publishYear", "years"));
		
		projectionList.add(Projections.count("id")).
		add(Projections.groupProperty("identify"));
		
		cr.setProjection(projectionList);

		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
		//B1成果分析-國際合作篇數

		public List<Object[]> getPeoplePaperByConditionCor( JSONArray classSubList , JSONArray countryList){
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewPeoplePaper.class);
			
			ProjectionList projectionList = Projections.projectionList();        
			
			
			
			Disjunction disCountry  = Restrictions.disjunction();
			for(int i=0; i<countryList.length(); i++) {
				JSONObject obj = (JSONObject) countryList.get(i);
				if (obj.getBoolean("Flag") == true) {
					String class_sub = obj.getString("Name");
					disCountry.add(Restrictions.eq("country", class_sub));
				}	
			}
	        cr.add(disCountry);
			
			
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
			
			

			cr.add(Restrictions.eq("paper_corID", "1"));
			
			projectionList.add(Projections.count("id")).
			add(Projections.groupProperty("identify"));
			
			cr.setProjection(projectionList);

			
			List<Object[]> list = cr.list();
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		}





//	
//	輔助追蹤查詢QUERY
//	
	public List<Object[]> getClassSubDataByCondition(Long startYear , Long endYear , JSONArray classSubList , JSONArray countryList, int classMainOption) {
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        
		cr.add(Restrictions.ge("years", startYear));
		
		cr.add(Restrictions.le("years", endYear));
		
		
		Disjunction disCountry  = Restrictions.disjunction();
		for(int i=0; i<countryList.length(); i++) {
			JSONObject obj = (JSONObject) countryList.get(i);
			if (obj.getBoolean("Flag") == true) {
				String class_sub = obj.getString("Name");
				disCountry.add(Restrictions.eq("country", class_sub));
			}	
		}
        cr.add(disCountry);
		
		
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
		switch (classMainOption) {
			case 1:
				cr.add(Restrictions.eq("class_main", "資訊及數位相關產業"));
				break;
			case 2:
				cr.add(Restrictions.eq("class_main", "臺灣精準健康戰略產業"));
				break;
			case 3 : 
				cr.add(Restrictions.eq("class_main", "國防及戰略產業"));
				break;
			case 4 :
				cr.add(Restrictions.eq("class_main", "民生及戰備產業"));
				break;
			case 5 :
				cr.add(Restrictions.eq("class_main", "綠電及再生能源產業"));
				break;
			case 6:
				cr.add(Restrictions.eq("class_main", "其他"));
				break;
			
		}
		


		
		projectionList.add(Projections.groupProperty("class_sub"))
		.add(Projections.groupProperty("class_main"))
		.add(Projections.groupProperty("identify"))
        .add(Projections.count("class_sub"));
		
		cr.setProjection(projectionList);
		cr.addOrder(Order.desc("class_main"));

		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	//取得各領域人數by國家
	public List<Object[]> getPeopleNum(String[] country){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		
		if(country != null) {
			cr.add(Restrictions.in("country", country));
		}
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("class_main"))
					  .add(Projections.groupProperty("class_sub"))
					  .add(Projections.countDistinct("p_id"));
		
		
		cr.setProjection(projectionList);
		cr.addOrder(Order.asc("class_sub"));
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	};
	

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");
		Boolean count_p_id = obj.isNull("count_p_id") == true ? null : obj.getBoolean("count_p_id");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PeopleMainsLift.class);
		if(count_p_id) {
			cr.setProjection(Projections.countDistinct("p_id"));
		}else {
			cr.setProjection(Projections.rowCount());
			if (id != 0)
				cr.add(Restrictions.eq("id", id));
			if (code != null)
				cr.add(Restrictions.like("code", "%" + code + "%"));
			if (name != null)
				cr.add(Restrictions.like("name", "%" + name + "%"));
			if (isEnable != null)
				cr.add(Restrictions.eq("isEnable", isEnable));
			if (isShow != null)
				cr.add(Restrictions.eq("isShow", isShow));
		}
		long total = (long) cr.list().get(0);
		return total;
	}
}
