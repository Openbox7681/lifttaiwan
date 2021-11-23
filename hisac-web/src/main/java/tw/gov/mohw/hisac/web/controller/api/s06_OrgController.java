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
import tw.gov.mohw.hisac.web.domain.SpOrgSign;



import tw.gov.mohw.hisac.web.domain.ViewParentOrg;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.OrgSignService;


import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;


/**
 * 單位基本資料管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s06_OrgController extends BaseController {

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private OrgSignService orgSignService;
	
	

	private String targetControllerName = "sys";
	private String targetActionName = "s06";

	/**
	 * 取得組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 組織資料
	 */
	@RequestMapping(value = "/s06/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			List<Org> orgs = orgService.getList(json);
			listjson.put("total", orgService.getListSize(json));
			JSONArray sn_array = new JSONArray();
			if (orgs != null) {

				for (Org org : orgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_json.put("Code", org.getCode());
					sn_json.put("OrgType", org.getOrgType());
					sn_json.put("AuthType", org.getAuthType());
					if (org.getOrgType().equals("3")) {
						if (org.getCiLevel() == null || org.getCiLevel().equals("0")) {
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel0", null, locale));
						} else if (org.getCiLevel().equals("1")) {
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel1", null, locale));
							
						} else if (org.getCiLevel().equals("2")) {
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel2", null, locale));
						}
					} else {
						sn_json.put("CILevel", "");
					}
					sn_json.put("IsApply", org.getIsApply());
					sn_json.put("Tel", org.getTel());
					sn_json.put("Fax", org.getFax());
					sn_json.put("City", org.getCity());
					sn_json.put("Town", org.getTown());
					sn_json.put("Address", org.getAddress());
					sn_json.put("IsEnable", org.getIsEnable());
					sn_json.put("CreateId", org.getCreateId());
					sn_json.put("CreateTime", org.getCreateTime());
					sn_json.put("ModifyId", org.getModifyId());
					sn_json.put("ModifyTime", org.getModifyTime());
					sn_json.put("IsPublic", org.getIsPublic());
					sn_json.put("SecurityLevel", org.getSecurityLevel());
					sn_json.put("AuthType", org.getAuthType());

					sn_json.put("HealthLevelId", org.getHealthLevelId());
					sn_json.put("ApiKeyStatus", org.getApiKeyStatus()!=null  ? org.getApiKeyStatus() : "0");
					
					sn_json.put("IsLocate", org.getIsLocate() == null ? false : org.getIsLocate());

					
					//取得上級主管資料與中央目的事業主管資料
					
					
					
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            組織Id
	 * @return 組織資料
	 */
	@RequestMapping(value = "/s06/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Org org = orgService.getDataById(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", org.getId());
			sn_json.put("Name", org.getName());
			sn_json.put("Code", org.getCode());
			sn_json.put("OrgType", org.getOrgType());
			sn_json.put("AuthType", org.getAuthType());
			sn_json.put("CILevel", org.getCiLevel() == null ? "0" : org.getCiLevel());
			sn_json.put("Tel", org.getTel());
			sn_json.put("Fax", org.getFax());
			sn_json.put("City", org.getCity());
			sn_json.put("Town", org.getTown());
			sn_json.put("Address", org.getAddress());
			sn_json.put("BossName", org.getBossName());
			sn_json.put("BossEmail", org.getBossEmail());
			sn_json.put("BossMobilePhone", org.getBossMobilePhone());
			sn_json.put("PrincipalName", org.getPrincipalName());
			sn_json.put("PrincipalMobilePhone", org.getPrincipalMobilePhone());
			sn_json.put("IsEnable", org.getIsEnable());
			sn_json.put("IsApply", org.getIsApply());
			sn_json.put("IsPublic", org.getIsPublic() == null ? false : org.getIsPublic());
			sn_json.put("SecurityLevel", org.getSecurityLevel() == null ? 0 : org.getSecurityLevel());
			sn_json.put("HealthLevelId", org.getHealthLevelId() == null ? 0 : org.getHealthLevelId());
			sn_json.put("ApiKeyStatus", org.getApiKeyStatus()!=null  ? org.getApiKeyStatus() : "0");
			
			sn_json.put("IsLocate", org.getIsLocate() == null ? false : org.getIsLocate());

			
			JSONObject org_json =  new JSONObject();
			org_json.put("orgId", org.getId());
			
			JSONArray snLog_array = new JSONArray();


			
			
			sn_json.put("SecurityLevelLogs", snLog_array);

			JSONArray snCiLog_array = new JSONArray();

			
			
			
			sn_json.put("CiLevelLogs", snCiLog_array);

			ViewParentOrg viewParentOrg = orgService.getSuperviseParentOrg(org.getId());
			if(viewParentOrg != null)
				sn_json.put("ParentOrgId", viewParentOrg.getId());
			

			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			systemLogService.insert("s06test", baseActionName, sn_json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s06/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			if (orgService.isNameExist(name)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Long orgId = obj.getLong("Id");
				Long parentOrgId =  obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
				JSONObject orgsign_obj = new JSONObject();
				orgsign_obj.put("OrgId", orgId);
				orgsign_obj.put("ParentOrgId", parentOrgId);

				
				Org org = orgService.insert(getBaseMemberId(), json);
				
				if (parentOrgId!=null ){
					orgSignService.save(getBaseMemberId(), orgsign_obj.toString());
				}
				
				if (org != null) {
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
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 更新組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否更新成功
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@RequestMapping(value = "/s06/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!orgService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Org org = orgService.update(getBaseMemberId(), json);
				
				Long orgId = obj.getLong("Id");
				Long parentOrgId =  obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
				JSONObject orgsign_obj = new JSONObject();
				orgsign_obj.put("OrgId", orgId);
				orgsign_obj.put("ParentOrgId", parentOrgId);
				
				if (parentOrgId!=null ){
					orgSignService.save(getBaseMemberId(), orgsign_obj.toString());
				}
				
				
				
				
				//針對ci 資料與 資通安全責任等級進行歷次資料更新 END
				if (org != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 刪除組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s06/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!orgService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (orgService.delete(id)) {
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
		return WebCrypto.getSafe(responseJson.toString());
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
	
	@RequestMapping(value = "/s06/getHealthLevels", method = RequestMethod.POST)
	public String getHealthLevels(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		
		// return responseJson.toString();
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	
	
	/**
	 * 取得會員機構上級機關與中央目的事業主管機關資料API authType = 2
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員機構上級機關與中央目的事業主管機關資料
	 */
	
	@RequestMapping(value = "/s06/getOrgSign", method = RequestMethod.POST)
	public @ResponseBody String getOrgSign(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		
	JSONObject responseJson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			try {
				List<Org> superviseOrgs =  orgService.getByOrgType(AuthType.Supervise.getValue());

				for (Org superviseOrg : superviseOrgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", superviseOrg.getId());
					sn_json.put("Name", superviseOrg.getName());
					sn_array.put(sn_json);
				}
				
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
				responseJson.put("datatable", sn_array);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} catch (Exception e) {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale) + "錯誤訊息為" + e.getStackTrace());
				responseJson.put("datatable", sn_array);
			}
		} else {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	
	/**
	 * 取得資通安全責任等級歷次資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            組織Id orgId
	 * @return 資通安全責任等級歷次資料
	 */
	@RequestMapping(value = "/s06/querySecurityLevelLog/id", method = RequestMethod.POST)
	public String QuerySecurityLevelLogById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	/**
	 * 取得Ci非Ci歷次資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            組織Id orgId
	 * @return Ci非Ci歷次資料API
	 */
	@RequestMapping(value = "/s06/queryCiLevelLog/id", method = RequestMethod.POST)
	public String QueryCiLevelLog(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
}