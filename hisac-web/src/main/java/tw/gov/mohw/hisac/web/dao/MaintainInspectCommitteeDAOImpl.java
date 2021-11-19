package tw.gov.mohw.hisac.web.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainInspectCommittee;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;

/**
 * 警訊會員群組服務
 */
@Repository
@Transactional
public class MaintainInspectCommitteeDAOImpl extends BaseSessionFactory implements MaintainInspectCommitteeDAO {
	public void insert(MaintainInspectCommittee entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}
	public void update(MaintainInspectCommittee entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}
	public void delete(MaintainInspectCommittee entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	public MaintainInspectCommittee get(Long id) {
		return getSessionFactory().getCurrentSession().get(MaintainInspectCommittee.class, id);
	}
	
	public ViewMaintainInspectCommitteeMemberOrg getView(Long id) {
		return getSessionFactory().getCurrentSession().get(ViewMaintainInspectCommitteeMemberOrg.class, id);
	}
	@SuppressWarnings("deprecation")
	public List<ViewMaintainInspectCommitteeMemberOrg> getMaintainInspectCommitteeList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		
		long committeeId = obj.isNull("CommitteeId") == true ? 0 : obj.getLong("CommitteeId");
		Boolean status = obj.isNull("Status") == true ? null : obj.getBoolean("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectCommitteeMemberOrg.class);
		if (committeeId != 0) {
		cr.add(Restrictions.eq("committeeId", committeeId));
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
		List<ViewMaintainInspectCommitteeMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	public long getMaintainInspectCommitteeListSize(JSONObject obj) {
		long committeeId = obj.isNull("CommitteeId") == true ? 0 : obj.getLong("CommitteeId");
		Boolean status = obj.isNull("Status") == true ? null : obj.getBoolean("Status");
		String keyword = obj.isNull("Keyword") == true ? null : obj.getString("Keyword");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectCommitteeMemberOrg.class);
		if (committeeId != 0) {
			cr.add(Restrictions.eq("committeeId", committeeId));
		}
		if (status != null) {
				cr.add(Restrictions.eq("status", status));
		}
		
		if (keyword != null) {
			cr.add(Restrictions.like("title", "%" + keyword + "%"));
		}
		
		List<ViewMaintainInspectCommitteeMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list.size();
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ViewMaintainInspectCommitteeMemberOrg> findByMaintainInspectIdAndGroupId(Long maintainInspectId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMaintainInspectCommitteeMemberOrg.class);
		
		if (maintainInspectId != null) {
			cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		}
		if (groupId != null) {
			cr.add(Restrictions.eq("groupId", groupId));
		}
		cr.addOrder(Order.asc("committeeId"));
		
		List<ViewMaintainInspectCommitteeMemberOrg> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "deprecation"})
	public MaintainInspectCommittee getByMaintainInspectIdAndGroupIdAndCommitteeId(long maintainInspectId,long groupId, long committeeId){
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainInspectCommittee.class);
		cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		cr.add(Restrictions.eq("groupId", groupId));
		cr.add(Restrictions.eq("committeeId", committeeId));
		List<MaintainInspectCommittee> list = cr.list();
		return list.get(0);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MaintainInspectCommittee> getListByMaintainInspectIdAndGroupId(Long maintainInspectId, Long groupId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainInspectCommittee.class);
		
		if (maintainInspectId != null) {
			cr.add(Restrictions.eq("maintainInspectId", maintainInspectId));
		}
		if (groupId != null) {
			cr.add(Restrictions.eq("groupId", groupId));
		}
		cr.addOrder(Order.asc("committeeId"));
		
		List<MaintainInspectCommittee> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
