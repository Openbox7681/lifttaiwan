//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.Survey;

public interface SurveyDAO {
	public void insert(Survey survey);
	public List<Survey> getAll();
}
