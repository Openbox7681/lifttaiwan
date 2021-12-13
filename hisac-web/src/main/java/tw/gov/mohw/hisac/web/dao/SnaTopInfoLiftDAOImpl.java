package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.SnaTopInfoLift;

@Repository
@Transactional
public class SnaTopInfoLiftDAOImpl extends BaseSessionFactory implements SnaTopInfoLiftDAO {

	public void insert(SnaTopInfoLift entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(SnaTopInfoLift entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(SnaTopInfoLift entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public SnaTopInfoLift get(Long id) {
		return getSessionFactory().getCurrentSession().get(SnaTopInfoLift.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SnaTopInfoLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaTopInfoLift.class);
		List<SnaTopInfoLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public SnaTopInfoLift getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaTopInfoLift.class);
		cr.add(Restrictions.eq("code", code));
		return (SnaTopInfoLift) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public SnaTopInfoLift getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaTopInfoLift.class);
		cr.add(Restrictions.eq("name", name));
		return (SnaTopInfoLift) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SnaTopInfoLift> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		Boolean number = obj.isNull("number") == true ? null : obj.getBoolean("number");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaTopInfoLift.class);
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
		List<SnaTopInfoLift> list = cr.list();
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
		Boolean count_topname = obj.isNull("count_topname") == true ? false : obj.getBoolean("count_topname");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaTopInfoLift.class);
		if(count_topname) {
			cr.setProjection(Projections.countDistinct("topname"));
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
