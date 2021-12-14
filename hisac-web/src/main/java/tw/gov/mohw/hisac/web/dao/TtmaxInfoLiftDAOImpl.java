package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.TtmaxInfoLift;

/**
 * 子系統服務
 */
@Repository
@Transactional
public class TtmaxInfoLiftDAOImpl extends BaseSessionFactory implements TtmaxInfoLiftDAO {

	public void insert(TtmaxInfoLift entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(TtmaxInfoLift entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(TtmaxInfoLift entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public TtmaxInfoLift get(Long id) {
		return getSessionFactory().getCurrentSession().get(TtmaxInfoLift.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<TtmaxInfoLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TtmaxInfoLift.class);
		List<TtmaxInfoLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public TtmaxInfoLift getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TtmaxInfoLift.class);
		cr.add(Restrictions.eq("code", code));
		return (TtmaxInfoLift) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public TtmaxInfoLift getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TtmaxInfoLift.class);
		cr.add(Restrictions.eq("name", name));
		return (TtmaxInfoLift) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<TtmaxInfoLift> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");	
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TtmaxInfoLift.class);
		
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		
		cr.setFirstResult(start);
		if (maxRows != 0) {
			cr.setMaxResults(maxRows);
		}
		List<TtmaxInfoLift> list = cr.list();
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
		Boolean count_paper_SerialNumber = obj.isNull("count_paper_SerialNumber") == true ? null : obj.getBoolean("count_paper_SerialNumber");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TtmaxInfoLift.class);
		if(count_paper_SerialNumber) {
			cr.setProjection(Projections.countDistinct("paper_SerialNumber"));
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
