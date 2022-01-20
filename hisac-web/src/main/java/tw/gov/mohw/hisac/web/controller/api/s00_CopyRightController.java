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
import tw.gov.mohw.hisac.web.domain.CopyRight;

import tw.gov.mohw.hisac.web.service.CopyRightService;


/**
 * 問卷調查控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_CopyRightController extends BaseController {
	final static Logger logger = LoggerFactory.getLogger(s00_CopyRightController.class);

	@Autowired
	private CopyRightService copyRightService;

	/**
	 * 新增或更新cookie資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/copyright/createOrUpdate", method = RequestMethod.POST)
	public @ResponseBody String CreateOrUpdate(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		
		if (copyRightService.isExist(new Long(1))) {
			//cookie資料故更新
			
			copyRightService.update(json);

			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			CopyRight privacyPage = copyRightService.insert(json);
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

	@RequestMapping(value = "/copyright/query/id", method = RequestMethod.POST)
	public @ResponseBody String IsExist(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject responseData = new JSONObject();

		
		if (copyRightService.isExist(new Long(1))) {
			
			CopyRight copyRight = copyRightService.getDataById(new Long(1));
			
			
			responseData.put("Title", copyRight.getTitle());
			responseData.put("Item1_1", copyRight.getItem1_1());
			responseData.put("Item1_2", copyRight.getItem1_2());
			responseData.put("Item1_3", copyRight.getItem1_3());

			
			


			
			
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
