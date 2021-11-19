package tw.gov.mohw.hisac.web.dao;


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

import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainInspectMemberDAOImpl extends BaseSessionFactory implements MaintainInspectMemberDAO {

	public void insert(MaintainInspectMember entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	
	public void update(MaintainInspectMember entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public MaintainInspectMember getListByMaintainInspectIdAndGroupId(long maintainInspectId, long groupId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainInspectMember.class);
		cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<MaintainInspectMember> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public MaintainInspectMember get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainInspectMember.class, id);
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

	@SuppressWarnings("deprecation")
	public List<Object[]> getMaintainInspectList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");		
		String year = obj.isNull("Year") == true ? null : obj.getString("Year");

		ProjectionList projectionList = Projections.projectionList()
				.add(Projections.groupProperty("maintainInspectId"))
				.add(Projections.groupProperty("title"))
				.add(Projections.groupProperty("year"))
				.add(Projections.groupProperty("status"))
				.add(Projections.rowCount(), "count");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.setProjection(projectionList);
		cr.add(Restrictions.in("status", Arrays.asList("1")));

		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}
		if (year != null) {
			cr.add(Restrictions.like("year",  "%" + year + "%"));
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
		
		ProjectionList projectionList = Projections.projectionList()
				.add(Projections.groupProperty("maintainInspectId"))
				.add(Projections.groupProperty("title"))
				.add(Projections.groupProperty("year"))
				.add(Projections.groupProperty("status"))
				.add(Projections.countDistinct("maintainInspectId"));
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.setProjection(projectionList);
		cr.add(Restrictions.in("status", Arrays.asList("1")));

		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}
		if (year != null) {
			cr.add(Restrictions.like("year",  "%" + year + "%"));
		}
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list.size();
		} else {
			return 0;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainInspectId(long maintainInspectId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		List<ViewMaintainInspectMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
