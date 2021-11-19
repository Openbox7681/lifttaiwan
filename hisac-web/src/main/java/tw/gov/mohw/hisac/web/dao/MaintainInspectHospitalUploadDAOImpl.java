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
public class MaintainInspectHospitalUploadDAOImpl extends BaseSessionFactory implements MaintainInspectHospitalUploadDAO {

	@SuppressWarnings("deprecation")
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long groupId = obj.isNull("GoupId") == true ? 0 : obj.getLong("GoupId");
		String inspectStatus = obj.isNull("InspectStatus") == true ? null : obj.getString("InspectStatus");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("groupId", groupId));
		
		cr.add(Restrictions.or(Restrictions.in("inspectStatus", Arrays.asList("4", "5","8","9")),
			   Restrictions.and(Restrictions.eq("inspectStatus", "6"),Restrictions.eq("allowHospitalPatch", true))));
		if (inspectStatus != null) {
				cr.add(Restrictions.eq("inspectStatus", inspectStatus));
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
		List<ViewMaintainInspectMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public long getMaintainInspectListSize(JSONObject obj) {
		long groupId = obj.isNull("GoupId") == true ? 0 : obj.getLong("GoupId");
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
		String inspectStatus = obj.isNull("InspectStatus") == true ? null : obj.getString("InspectStatus");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("groupId", groupId));
		
		cr.add(Restrictions.or(Restrictions.in("inspectStatus", Arrays.asList("4", "5","8","9")),
			   Restrictions.and(Restrictions.eq("inspectStatus", "6"),Restrictions.eq("allowHospitalPatch", true))));
		if (inspectStatus != null) {
				cr.add(Restrictions.eq("inspectStatus", inspectStatus));
		}
		
		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}	

		List<ViewMaintainInspectMemberOrg> list = cr.list();
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

	@SuppressWarnings({ "deprecation"})
	public ViewMaintainInspectMemberOrg getListByMaintainInspectIdAndGroupId(long maintainInspectId, long groupId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectMemberOrg.class);
		cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		cr.add(Restrictions.eq("groupId", groupId));
		List<ViewMaintainInspectMemberOrg> list = cr.list();
		return list.get(0);
	}
}
