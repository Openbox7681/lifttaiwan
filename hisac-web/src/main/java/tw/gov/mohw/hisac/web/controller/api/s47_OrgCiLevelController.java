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
import tw.gov.mohw.hisac.web.domain.CiLevelLog;


import tw.gov.mohw.hisac.web.service.OrgService;

import tw.gov.mohw.hisac.web.service.CiLevelLogService;




/**
 * 會員機構審核方式管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s47_OrgCiLevelController extends BaseController {

	@Autowired
	private CiLevelLogService ciLevelLogService;
	@Autowired
	private OrgService orgService;

	private String targetControllerName = "sys";
	private String targetActionName = "s47";

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
	@RequestMapping(value = "/s47/org/query", method = RequestMethod.POST)
	public String OrgQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			
			json = WebCrypto.getSafe(json);
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
	 * 取得所有Ci/非Ci歷次資料API
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
	
	@RequestMapping(value = "/s47/year/query", method = RequestMethod.POST)
	public String YearQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			List<String> years = ciLevelLogService.getAllYears();
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
	 * 取得Ci/非Ci歷次資料log API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Ci/非Ci歷次資料
	 */
	@RequestMapping(value = "/s47/query", method = RequestMethod.POST)
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
				CiLevelLog ciLevelLog= ciLevelLogService.getDataByOrgIdAndYear(org.getId(), year);
				
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", ciLevelLog.getId());
				sn_json.put("Year", ciLevelLog.getYear());
				sn_json.put("OrgId", ciLevelLog.getOrgId());
				sn_json.put("CiLevel", ciLevelLog.getCiLevel() == null ? "10" : ciLevelLog.getCiLevel());
				sn_json.put("Name", org.getName());

				sn_json.put("CreateTime", ciLevelLog.getCreateTime());
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
	 * 新增或異動Ci/非Ci歷次資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Ci/非Ci歷次資料
	 * @return 是否新增或異動成功
	 */
	@RequestMapping(value = "/s47/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj_array = new JSONObject(json);
			JSONArray json_array = obj_array.getJSONArray("OrgCiLevelList");
			for (int i = 0; i < json_array.length(); i++) {
				JSONObject obj = json_array.getJSONObject(i);
				Long orgId =  obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
				String ciLevel = obj.isNull("CiLevel") == true ? "10" : obj.getString("CiLevel");
				//判斷該機構之ci level是否改變，若改變則修改該機構ci level
				Org org  = orgService.getDataById(orgId);
				if(org.getCiLevel() == null )
					org = orgService.updateCiLevel(getBaseMemberId(), "10", orgId);	

				
				if (org.getCiLevel() != null ) {
					if (!ciLevel.equals(org.getCiLevel()))
					{
						org = orgService.updateCiLevel(getBaseMemberId(), ciLevel, orgId);	
						
						JSONObject ciLevelLog_obj = new JSONObject();
						Date now = new Date();
						ciLevelLog_obj.put("CiLevel", ciLevel);
						ciLevelLog_obj.put("OrgId", orgId);
						ciLevelLog_obj.put("Year", String.valueOf(now.getYear()+ 1900));
						ciLevelLogService.insertData(getBaseMemberId(), ciLevelLog_obj.toString());
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