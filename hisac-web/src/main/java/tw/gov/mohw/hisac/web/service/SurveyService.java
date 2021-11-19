
package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.SurveyDAO;
import tw.gov.mohw.hisac.web.domain.Survey;

/**
 * 使用者資料服務
 */
@Service
public class SurveyService {
	final static Logger logger = LoggerFactory.getLogger(SurveyService.class);

	@Autowired
	SurveyDAO surveyDAO;

	/**
	 * 新增問卷資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public Survey insert(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			Integer surveyPublic01 = obj.isNull("SurveyPublic01") == true ? 0 : obj.getInt("SurveyPublic01");
			String surveyPublic01Suggest = obj.isNull("SurveyPublic01Suggest") == true ? "" : obj.getString("SurveyPublic01Suggest");
			Integer surveyPublic02 = obj.isNull("SurveyPublic02") == true ? 0 : obj.getInt("SurveyPublic02");
			String surveyPublic02Suggest = obj.isNull("SurveyPublic02Suggest") == true ? "" : obj.getString("SurveyPublic02Suggest");
			Integer surveyPublic03 = obj.isNull("SurveyPublic03") == true ? 0 : obj.getInt("SurveyPublic03");
			String surveyPublic03Suggest = obj.isNull("SurveyPublic03Suggest") == true ? "" : obj.getString("SurveyPublic03Suggest");

			Integer surveyNotify01 = obj.isNull("SurveyNotify01") == true ? 0 : obj.getInt("SurveyNotify01");
			String surveyNotify01Suggest = obj.isNull("SurveyNotify01Suggest") == true ? "" : obj.getString("SurveyNotify01Suggest");
			Integer surveyNotify02 = obj.isNull("SurveyNotify02") == true ? 0 : obj.getInt("SurveyNotify02");
			String surveyNotify02Suggest = obj.isNull("SurveyNotify02Suggest") == true ? "" : obj.getString("SurveyNotify02Suggest");
			Integer surveyNotify03 = obj.isNull("SurveyNotify03") == true ? 0 : obj.getInt("SurveyNotify03");
			String surveyNotify03Suggest = obj.isNull("SurveyNotify03Suggest") == true ? "" : obj.getString("SurveyNotify03Suggest");

			Integer surveyAlert01 = obj.isNull("SurveyAlert01") == true ? 0 : obj.getInt("SurveyAlert01");
			String surveyAlert01Suggest = obj.isNull("SurveyAlert01Suggest") == true ? "" : obj.getString("SurveyAlert01Suggest");
			Integer surveyAlert02 = obj.isNull("SurveyAlert02") == true ? 0 : obj.getInt("SurveyAlert02");
			String surveyAlert02Suggest = obj.isNull("SurveyAlert02Suggest") == true ? "" : obj.getString("SurveyAlert02Suggest");
			Integer surveyAlert03 = obj.isNull("SurveyAlert03") == true ? 0 : obj.getInt("SurveyAlert03");
			String surveyAlert03Suggest = obj.isNull("SurveyAlert03Suggest") == true ? "" : obj.getString("SurveyAlert03Suggest");

			Integer surveyCheck01 = obj.isNull("SurveyCheck01") == true ? 0 : obj.getInt("SurveyCheck01");
			String surveyCheck01Suggest = obj.isNull("SurveyCheck01Suggest") == true ? "" : obj.getString("SurveyCheck01Suggest");
			Integer surveyCheck02 = obj.isNull("SurveyCheck02") == true ? 0 : obj.getInt("SurveyCheck02");
			String surveyCheck02Suggest = obj.isNull("SurveyCheck02Suggest") == true ? "" : obj.getString("SurveyCheck02Suggest");
			Integer surveyCheck03 = obj.isNull("SurveyCheck03") == true ? 0 : obj.getInt("SurveyCheck03");
			String surveyCheck03Suggest = obj.isNull("SurveyCheck03Suggest") == true ? "" : obj.getString("SurveyCheck03Suggest");
			
			Integer surveyTotal01 = obj.isNull("SurveyTotal01") == true ? 0 : obj.getInt("SurveyTotal01");
			String surveyTotal01Suggest = obj.isNull("SurveyTotal01Suggest") == true ? "" : obj.getString("SurveyTotal01Suggest");
			
			String surveySuggest = obj.isNull("SurveySuggest") == true ? "" : obj.getString("SurveySuggest");

			Survey entity = new Survey();
			entity.setSurveyPublic01(surveyPublic01);
			entity.setSurveyPublic01Suggest(surveyPublic01Suggest);
			entity.setSurveyPublic02(surveyPublic02);
			entity.setSurveyPublic02Suggest(surveyPublic02Suggest);
			entity.setSurveyPublic03(surveyPublic03);
			entity.setSurveyPublic03Suggest(surveyPublic03Suggest);
			entity.setSurveyNotify01(surveyNotify01);
			entity.setSurveyNotify01Suggest(surveyNotify01Suggest);
			entity.setSurveyNotify02(surveyNotify02);
			entity.setSurveyNotify02Suggest(surveyNotify02Suggest);
			entity.setSurveyNotify03(surveyNotify03);
			entity.setSurveyNotify03Suggest(surveyNotify03Suggest);
			entity.setSurveyAlert01(surveyAlert01);
			entity.setSurveyAlert01Suggest(surveyAlert01Suggest);
			entity.setSurveyAlert02(surveyAlert02);
			entity.setSurveyAlert02Suggest(surveyAlert02Suggest);
			entity.setSurveyAlert03(surveyAlert03);
			entity.setSurveyAlert03Suggest(surveyAlert03Suggest);
			entity.setSurveyCheck01(surveyCheck01);
			entity.setSurveyCheck01Suggest(surveyCheck01Suggest);
			entity.setSurveyCheck02(surveyCheck02);
			entity.setSurveyCheck02Suggest(surveyCheck02Suggest);
			entity.setSurveyCheck03(surveyCheck03);
			entity.setSurveyTotal01Suggest(surveyTotal01Suggest);
			entity.setSurveyTotal01(surveyTotal01);
			entity.setSurveyCheck03Suggest(surveyCheck03Suggest);
			entity.setSurveySuggest(surveySuggest);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);

			surveyDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得問卷
	 * 
	 * @param name
	 *            name
	 * @return 問卷
	 */
	public Survey findByMemberId(Long memberId) {
		List<Survey> entitys = surveyDAO.getAll();
		if (entitys != null) {
			for (Survey entity : entitys) {
				if (entity.getCreateId().equals(memberId)) {
					return entity;
				}
			}
		}
		return null;
	}

	/**
	 * 問卷是否存在
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isMemberIdExist(Long memberId) {
		return findByMemberId(memberId) != null;
	}
}