package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tw.gov.mohw.hisac.web.domain.SnaInfoLift;


@Repository
@Transactional
public class SnaInfoLiftDAOImpl extends BaseSessionFactory implements SnaInfoLiftDAO {

	public void insert(SnaInfoLift entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(SnaInfoLift entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(SnaInfoLift entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public SnaInfoLift get(Long id) {
		return getSessionFactory().getCurrentSession().get(SnaInfoLift.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SnaInfoLift> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaInfoLift.class);
		List<SnaInfoLift> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Object[]> getLinksByName(List<String> name){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(SnaInfoLift.class);
		
		ProjectionList projectionList = Projections.projectionList();        


		
		cr.add(Restrictions.in("name_1", name));
		
		cr.add(Restrictions.in("name_2", name));

		cr.add(Restrictions.neProperty("name_2", "name_1"));
		
		projectionList.add(Projections.groupProperty("name_1"))
		.add(Projections.groupProperty("name_2"));
		
		cr.setProjection(projectionList);
		
		
		
		
		List<Object[] > list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	

	
}
