package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.Survey;

@Repository
@Transactional
public class SurveyDAOImpl extends BaseSessionFactory implements SurveyDAO {

	public void insert(Survey survey) {
		getSessionFactory().getCurrentSession().save(survey);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Survey> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Survey.class);
		List<Survey> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
