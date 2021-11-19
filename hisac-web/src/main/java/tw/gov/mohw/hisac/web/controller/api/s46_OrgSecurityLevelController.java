package tw.gov.mohw.hisac.web.controller.api;

import java.util.Date;
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

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.SecurityLevelLog;

import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.SecurityLevelLogService;


/**
 * 會員機構審核方式管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s46_OrgSecurityLevelController extends BaseController {

	@Autowired
	private SecurityLevelLogService securityLevelLogService;
	@Autowired
	private OrgService orgService;

	private String targetControllerName = "sys";
	private String targetActionName = "s14";

	/**
	 * 取得所有會員機構資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員機構審核方式資料
	 */
	@RequestMapping(value = "/s46/org/query", method = RequestMethod.POST)
	public String OrgQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONArray sn_array = new JSONArray();
			List<Org> orgs = orgService.getList(json);
			if (orgs != null)
				for (Org org : orgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Code", org.getCode());
					sn_json.put("Name", org.getName());
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
	 * 取得所有資通安全責任等級資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員機構審核方式資料
	 */
	
	@RequestMapping(value = "/s46/year/query", method = RequestMethod.POST)
	public String YearQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			List<String> years = securityLevelLogService.getAllYears();
			if (years != null)
				for (String year : years) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Year", year);
					
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
	 * 取得會員機構資通安全責任等級log資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員機構審核方式資料
	 */
	@RequestMapping(value = "/s46/query", method = RequestMethod.POST)
	public @ResponseBody String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		// JSONObject obj = new JSONObject(json);
		// long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			String year = obj.isNull("Year") == true ? "2021" : obj.getString("Year");
			
			List<Org> orgs = orgService.getList(json);
			for (Org org : orgs) {
				SecurityLevelLog securityLevelLog= securityLevelLogService.getDataByOrgIdAndYear(org.getId(), year);
				
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", securityLevelLog.getId());
				sn_json.put("Year", securityLevelLog.getYear());
				sn_json.put("OrgId", securityLevelLog.getOrgId());
				sn_json.put("SecurityLevel", securityLevelLog.getSecurityLevel() == null ? 0 : securityLevelLog.getSecurityLevel());
				sn_json.put("Name", org.getName());

				sn_json.put("CreateTime", securityLevelLog.getCreateTime());
				
				
				
				sn_array.put(sn_json);
			}

			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(listjson.toString());
	}

	/**
	 * 新增或異動會員機構審核方式資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            會員機構審核方式資料
	 * @return 是否新增或異動成功
	 */
	@RequestMapping(value = "/s46/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj_array = new JSONObject(json);
			JSONArray json_array = obj_array.getJSONArray("OrgSecurityLevelList");
			for (int i = 0; i < json_array.length(); i++) {
				JSONObject obj = json_array.getJSONObject(i);
				Long orgId =  obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
				Long securityLevel = obj.isNull("SecurityLevel") == true ? 0 : obj.getLong("SecurityLevel");
				//判斷該機構之sercurity level是否改變，若改變則修改該機構security level
				Org org  = orgService.getDataById(orgId);
				if(org.getSecurityLevel() == null )
					org = orgService.updateSecurityLevel(getBaseMemberId(), (long) 0, orgId);	

				
				if (org.getSecurityLevel() != null ) {
					if (!securityLevel.equals(org.getSecurityLevel()))
					{
						org = orgService.updateSecurityLevel(getBaseMemberId(), securityLevel, orgId);	
						
						JSONObject securityLevelLog_obj = new JSONObject();
						Date now = new Date();
						securityLevelLog_obj.put("SecurityLevel", securityLevel);
						securityLevelLog_obj.put("OrgId", orgId);
						securityLevelLog_obj.put("Year", String.valueOf(now.getYear()+ 1900));
						securityLevelLogService.insertData(getBaseMemberId(), securityLevelLog_obj.toString());
					}	
				}
			}
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

}