package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.ViewInboundPeoplePaper;

/**
 * 子系統服務
 */
@Repository
@Transactional
public class InboundPeoplePaperDAOImpl extends BaseSessionFactory implements InboundPeoplePaperDAO {

	public void insert(ViewInboundPeoplePaper entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(ViewInboundPeoplePaper entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(ViewInboundPeoplePaper entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public ViewInboundPeoplePaper get(Long id) {
		return getSessionFactory().getCurrentSession().get(ViewInboundPeoplePaper.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewInboundPeoplePaper> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
		List<ViewInboundPeoplePaper> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public ViewInboundPeoplePaper getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
		cr.add(Restrictions.eq("code", code));
		return (ViewInboundPeoplePaper) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public ViewInboundPeoplePaper getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
		cr.add(Restrictions.eq("name", name));
		return (ViewInboundPeoplePaper) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewInboundPeoplePaper> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		Boolean number = obj.isNull("number") == true ? null : obj.getBoolean("number");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
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
		List<ViewInboundPeoplePaper> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");
		Boolean count_p_id = obj.isNull("count_p_id") == true ? null : obj.getBoolean("count_p_id");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
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
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getLineData() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInboundPeoplePaper.class);
		
		cr.add(Restrictions.ge("years", Long.valueOf(100)));
		cr.add(Restrictions.le("years", Long.valueOf(109)));
		
		ProjectionList projectionList = Projections.projectionList();        
		projectionList.add(Projections.groupProperty("years"))
		        .add(Projections.countDistinct("paper_SerialNumber"));
		
		cr.setProjection(projectionList);
		
		List<Object[]> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
