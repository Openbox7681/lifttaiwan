package tw.gov.mohw.hisac.web.controller.api;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Survey;
import tw.gov.mohw.hisac.web.service.SurveyService;

/**
 * 問卷調查控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_SurveyController extends BaseController {
	final static Logger logger = LoggerFactory.getLogger(s00_SurveyController.class);

	@Autowired
	private SurveyService surveyService;

	/**
	 * 新增問卷資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/survey/create", method = RequestMethod.POST)
	public @ResponseBody String UpdateSurvey(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (surveyService.isMemberIdExist(getBaseMemberId())) {
			responseJson.put("msg", WebMessage.getMessage("globalDataExist", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			Survey survey = surveyService.insert(getBaseMemberId(), json);
			if (survey != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		}

		return responseJson.toString();
	}

	@RequestMapping(value = "/survey/query/id", method = RequestMethod.POST)
	public @ResponseBody String IsExist(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (surveyService.isMemberIdExist(getBaseMemberId())) {
			responseJson.put("msg", true);
			responseJson.put("success", true);
		} else {
			responseJson.put("msg", false);
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}
}
