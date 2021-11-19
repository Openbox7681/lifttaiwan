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
import tw.gov.mohw.hisac.web.domain.HealthLevel;
import tw.gov.mohw.hisac.web.domain.Healthcare;
import tw.gov.mohw.hisac.web.domain.ViewHealthcare;
import tw.gov.mohw.hisac.web.service.HealthLevelService;
import tw.gov.mohw.hisac.web.service.HealthcareService;

/**
 * 醫事機構代碼資料維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s08_HealthcareController extends BaseController {
	@Autowired
	private HealthcareService healthcareService;
	
	@Autowired
	private HealthLevelService healthLevelService;

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
			List<ViewHealthcare> healthcares = healthcareService.getList(json);
			listjson.put("total", healthcareService.getListSize(json));
			JSONArray sn_array = new JSONArray();
			if (healthcares != null)
				for (ViewHealthcare healthcare : healthcares) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", healthcare.getCode());
					sn_json.put("Name", healthcare.getName());
					if (healthcare.getIsCI() == null || !healthcare.getIsCI()) {
						sn_json.put("IsCI", false);
					} else if (healthcare.getIsCI()) {
						sn_json.put("IsCI", true);
					} else {
						sn_json.put("IsCI", false);
					}
					sn_json.put("ParentOrgName", healthcare.getParentOrgName());
					sn_array.put(sn_json);
				}
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
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? "" : obj.getString("Code");
			Healthcare healthcare = healthcareService.getByCode(code);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Code", healthcare.getCode());
			sn_json.put("Name", healthcare.getName());
			sn_json.put("City", healthcare.getCity());
			sn_json.put("Town", healthcare.getTown());
			sn_json.put("Address", healthcare.getAddress());
			sn_json.put("ParentOrgId", healthcare.getParentOrgId());
			sn_json.put("IsCI", healthcare.getIsCI() == null ? false : healthcare.getIsCI());
			sn_json.put("IsPublic", healthcare.getIsPublic() == null ? false : healthcare.getIsPublic());
			sn_json.put("HealthLevelId", healthcare.getHealthLevelId());
			sn_json.put("SecurityLevel", healthcare.getSecurityLevel());

			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");

			if (healthcareService.isNameExist(name)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Healthcare healthcare = healthcareService.insert(getBaseMemberId(), json);
				if (healthcare != null) {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}

		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? "" : obj.getString("Code");

			if (!healthcareService.isExist(code)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Healthcare healthcare = healthcareService.update(getBaseMemberId(), json);
				if (healthcare != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
			if (!healthcareService.isExist(code)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (healthcareService.delete(code)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
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
		
		try {
			List<HealthLevel> healthLevels = healthLevelService.getAll();
			
			if( healthLevels!= null) {
				for (HealthLevel healthLevel : healthLevels) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", healthLevel.getId());
					jsonObject.put("name", healthLevel.getName());
					jsonArray.put(jsonObject);
				}	
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
			responseJson.put("datatable", jsonArray);
			systemLogService.insert(baseControllerName, baseActionName, responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

			
			
		} catch (Exception e) {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale));
			responseJson.put("datatable", jsonArray);
			systemLogService.insert(baseControllerName, baseActionName, responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

		}
		// return responseJson.toString();
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
}