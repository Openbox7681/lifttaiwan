package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.PaperMainsLift;

/**
 * 子系統服務
 */
@Repository
@Transactional
public class PaperMainsLiftDAOImpl extends BaseSessionFactory implements PaperMainsLiftDAO {

	public void insert(PaperMainsLift entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(PaperMainsLift entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(PaperMainsLift entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public PaperMainsLift get(Long id) {
		return getSessionFactory().getCurrentSession().get(PaperMainsLift.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<PaperMainsLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperMainsLift.class);
		List<PaperMainsLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public PaperMainsLift getByCode(String code) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperMainsLift.class);
		cr.add(Restrictions.eq("code", code));
		return (PaperMainsLift) cr.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	public PaperMainsLift getByName(String name) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperMainsLift.class);
		cr.add(Restrictions.eq("name", name));
		return (PaperMainsLift) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<PaperMainsLift> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		Boolean number = obj.isNull("number") == true ? false : obj.getBoolean("number");
		String p_id = obj.isNull("p_id") == true ? null : obj.getString("p_id");
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperMainsLift.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if(p_id != null && p_id.length()>0) {
			cr.add(Restrictions.eq("p_id", p_id));
		}
		if(number) {
			cr.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("p_id")));	
		}
		
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<PaperMainsLift> list = cr.list();
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
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(PaperMainsLift.class);
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
