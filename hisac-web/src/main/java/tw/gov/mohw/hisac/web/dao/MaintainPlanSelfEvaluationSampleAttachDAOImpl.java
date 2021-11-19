package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationSampleAttach;

/**
 * 認識本系統-文章附件維護
 */
@Repository
@Transactional
public class MaintainPlanSelfEvaluationSampleAttachDAOImpl extends BaseSessionFactory implements MaintainPlanSelfEvaluationSampleAttachDAO {

	public void insert(MaintainPlanSelfEvaluationSampleAttach entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public MaintainPlanSelfEvaluationSampleAttach get() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanSelfEvaluationSampleAttach.class);				
		List<MaintainPlanSelfEvaluationSampleAttach> list = cr.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void deleteAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MaintainPlanSelfEvaluationSampleAttach.class);		
		List<MaintainPlanSelfEvaluationSampleAttach> list = cr.list();
		for (int i=0; i<list.size(); i++)
			getSessionFactory().getCurrentSession().delete(list.get(i));
	}
}
