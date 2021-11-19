package tw.gov.mohw.hisac.web.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.MaintainPlan;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainPlanDAOImpl extends BaseSessionFactory implements MaintainPlanDAO {

	public void insert(MaintainPlan entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MaintainPlan entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MaintainPlan entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	public MaintainPlan get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainPlan.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MaintainPlan> getList(JSONObject obj){
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlan.class);
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		if (status != null) {
			cr.add(Restrictions.eq("status", status));
		}
		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}	
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);		
		List<MaintainPlan> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlan.class);
		cr.setProjection(Projections.rowCount());
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		if (status != null) {
			cr.add(Restrictions.eq("status", status));
		}
		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}			
		long total = (long) cr.list().get(0);
		return total;
	}
}
