package tw.gov.mohw.hisac.web.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainPlanMemberDAOImpl extends BaseSessionFactory implements MaintainPlanMemberDAO {

	public void insert(MaintainPlanMember entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	
	public void update(MaintainPlanMember entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewMaintainPlanMemberOrg> getListByMaintainPlanId(long maintainPlanId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainPlanMemberOrg.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<ViewMaintainPlanMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public MaintainPlanMember getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanMember.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainPlanMember> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ViewMaintainPlanMemberOrg> getList(JSONObject obj){
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long groupId = obj.isNull("GoupId") == true ? 0 : obj.getLong("GoupId");
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainPlanMemberOrg.class);
		
		cr.add(Restrictions.eq("groupId", groupId));
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("sdate", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("edate", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		if (status != null) {
			if (status.equals("2"))
				cr.add(Restrictions.or(Restrictions.eq("status", "2"), Restrictions.eq("status", "4")));
			else if (status.equals("6"))
				cr.add(Restrictions.or(Restrictions.eq("status", "6"), Restrictions.eq("status", "7")));			
			else
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
		List<ViewMaintainPlanMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long groupId = obj.isNull("GoupId") == true ? 0 : obj.getLong("GoupId");
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainPlanMemberOrg.class);
		cr.setProjection(Projections.rowCount());
		cr.add(Restrictions.eq("groupId", groupId));
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("sdate", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("edate", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		if (status != null) {
			if (status.equals("2"))
				cr.add(Restrictions.or(Restrictions.eq("status", "2"), Restrictions.eq("status", "4")));
			else if (status.equals("6"))
				cr.add(Restrictions.or(Restrictions.eq("status", "6"), Restrictions.eq("status", "7")));	
			else
				cr.add(Restrictions.eq("status", status));
		}
		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}			
		long total = (long) cr.list().get(0);
		return total;
	}
	
	@SuppressWarnings("deprecation")
	public List<Object[]> getMaintainInspectList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");		
		String year = obj.isNull("Year") == true ? null : obj.getString("Year");		
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");

		ProjectionList projectionList = Projections.projectionList()
				.add(Projections.groupProperty("maintainPlanId"))
				.add(Projections.groupProperty("title"))
				.add(Projections.groupProperty("year"))
				.add(Projections.groupProperty("status"))
				.add(Projections.rowCount(), "count");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.setProjection(projectionList);
		cr.add(Restrictions.in("status", Arrays.asList("2", "3", "31", "32")));

		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}
		if (year != null) {
			cr.add(Restrictions.eq("year", year));
		}
		if (status != null) {
			cr.add(Restrictions.eq("status", status));
		}
			
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
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
	
	@SuppressWarnings("deprecation")
	public long getMaintainInspectListSize(JSONObject obj) {
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");		
		String year = obj.isNull("Year") == true ? null : obj.getString("Year");		
		String status = obj.isNull("Status") == true ? null : obj.getString("Status");
		
		ProjectionList projectionList = Projections.projectionList()
				.add(Projections.groupProperty("maintainPlanId"))
				.add(Projections.groupProperty("title"))
				.add(Projections.groupProperty("year"))
				.add(Projections.groupProperty("status"))
				.add(Projections.countDistinct("maintainPlanId"));
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.setProjection(projectionList);
		cr.add(Restrictions.in("status", Arrays.asList("2", "3", "31", "32")));

		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}
		if (year != null) {
			cr.add(Restrictions.eq("year", year));
		}
		if (status != null) {
			cr.add(Restrictions.eq("status", status));
		}

		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list.size();
		} else {
			return 0;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainPlanId(long maintainPlanId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("maintainPlanId", maintainPlanId));
		List<ViewMaintainInspectMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("id", id));
		List<ViewMaintainInspectMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public MaintainPlanMember get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainPlanMember.class, id);
	}
}
