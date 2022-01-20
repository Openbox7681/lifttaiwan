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
import tw.gov.mohw.hisac.web.domain.PrivacyPage;

import tw.gov.mohw.hisac.web.service.PrivacyPageService;


/**
 * 問卷調查控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_PrivacyPageController extends BaseController {
	final static Logger logger = LoggerFactory.getLogger(s00_PrivacyPageController.class);

	@Autowired
	private PrivacyPageService privacyPageService;

	/**
	 * 新增或更新隱私權政策資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/privacyPage/createOrUpdate", method = RequestMethod.POST)
	public @ResponseBody String CreateOrUpdate(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		
		if (privacyPageService.isExist(new Long(1))) {
			//隱私權政策資料存在故更新
			
			privacyPageService.update(json);

			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			PrivacyPage privacyPage = privacyPageService.insert(json);
			if (privacyPage != null) {
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

	@RequestMapping(value = "/privacyPage/query/id", method = RequestMethod.POST)
	public @ResponseBody String IsExist(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject responseData = new JSONObject();

		
		if (privacyPageService.isExist(new Long(1))) {
			
			PrivacyPage privacyPage = privacyPageService.getDataById(new Long(1));
			
			
			responseData.put("Title", privacyPage.getTitle());
			responseData.put("Item1", privacyPage.getItem1());
			responseData.put("Item2_1", privacyPage.getItem2_1());
			responseData.put("Item2_2", privacyPage.getItem2_2());
			responseData.put("Item2_3", privacyPage.getItem2_3());
			responseData.put("Item2_4", privacyPage.getItem2_4());
			responseData.put("Item2_5", privacyPage.getItem2_5());
			
			responseData.put("Item3_1", privacyPage.getItem3_1());
			responseData.put("Item3_2", privacyPage.getItem3_2());
			

			responseData.put("Item4_1", privacyPage.getItem4_1());
			responseData.put("Item4_2", privacyPage.getItem4_2());
			responseData.put("Item4_3", privacyPage.getItem4_3());
			responseData.put("Item4_4", privacyPage.getItem4_4());

			responseData.put("Item5_1", privacyPage.getItem5_1());
			


			
			
			responseJson.put("data", responseData);
	
			responseJson.put("msg", true);
			responseJson.put("success", true);
		} else {
			responseJson.put("data", responseData);

			responseJson.put("msg", false);
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
}
