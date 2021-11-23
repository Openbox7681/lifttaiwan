package tw.gov.mohw.hisac.web.controller.api;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;

/**
 * 醫事機構代碼資料維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s08_HealthcareController extends BaseController {
	

	private String targetControllerName = "sys";
	private String targetActionName = "s08";

	/**
	 * 取得醫事機構代碼資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 醫事機構代碼資料
	 */
	@RequestMapping(value = "/s08/query", method = RequestMethod.POST)
	public String Query(Locale locale, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			listjson.put("total",0);
			JSONArray sn_array = new JSONArray();
			
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	

	/**
	 * 取得醫事機構代碼資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            醫事機構代碼Id
	 * @return 醫事機構代碼資料
	 */
	@RequestMapping(value = "/s08/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增醫事機構代碼資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            醫事機構代碼
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s08/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		return responseJson.toString();
	}

	/**
	 * 更新醫事機構代碼資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            醫事機構代碼
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s08/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		return responseJson.toString();
	}

	/**
	 * 刪除醫事機構代碼資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s08/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? "" : obj.getString("Code");
			
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 取得醫院層級資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param q
	 *            String
	 * @param per_page
	 *            Integer
	 * @return JSON Format String
	 */
	
	@RequestMapping(value = "/s08/getHealthLevels", method = RequestMethod.POST)
	public String getHealthLevels(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		// return responseJson.toString();
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
}