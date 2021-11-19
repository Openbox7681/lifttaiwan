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
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroup;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.SpMaintainPlanGroupOrg;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupOrgService;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupService;
import tw.gov.mohw.hisac.web.service.OrgService;

/**
 * 警訊發佈群組管理會員機構管理控制器
 */
@Controller
@RequestMapping(value = "/mtp/api", produces = "application/json; charset=utf-8")
public class m03_MaintainPlanGroupOrgController extends BaseController {

	@Autowired
	private MaintainPlanGroupService maintainPlanGroupService;
	@Autowired
	private MaintainPlanGroupOrgService maintainPlanGroupOrgService;
	@Autowired
	private OrgService orgService;

	private String targetControllerName = "mtp";
	private String targetActionName = "m03";

	/**
	 * 取得警訊發佈群組管理會員機構資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊發佈群組管理會員機構資料
	 */
	@RequestMapping(value = "/m03/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long maintainPlanGroupId = obj.isNull("MaintainPlanGroupId") == true ? 0 : obj.getLong("MaintainPlanGroupId");
			JSONArray sn_array = new JSONArray();
			List<SpMaintainPlanGroupOrg> spMaintainPlanGroupOrgs = maintainPlanGroupOrgService.getSpList(json);
			for (SpMaintainPlanGroupOrg spMaintainPlanGroupOrg : spMaintainPlanGroupOrgs) {
				Org org = orgService.getDataById(spMaintainPlanGroupOrg.getOrgId());
				if (org.getIsEnable()) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", spMaintainPlanGroupOrg.getId());
					sn_json.put("OrgId", spMaintainPlanGroupOrg.getOrgId());
					sn_json.put("Name", spMaintainPlanGroupOrg.getName());
					sn_json.put("MaintainPlanGroupId", maintainPlanGroupId);
					sn_json.put("Flag", spMaintainPlanGroupOrg.getFlag() == 0 ? false : true);
					sn_array.put(sn_json);
				}
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
	 * 取得警訊會員群組資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊會員群組資料
	 */
	@RequestMapping(value = "/m03/maintainPlangroup/query", method = RequestMethod.POST)
	public String MaintainPlanGroupQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<MaintainPlanGroup> maintainPlanGroups = maintainPlanGroupService.getList(json);
			listjson.put("total", maintainPlanGroupService.getListSize(json));

			JSONArray sn_array = new JSONArray();
			if (maintainPlanGroups != null)
				for (MaintainPlanGroup maintainPlanGroup : maintainPlanGroups) {
					if (maintainPlanGroup.getIsEnable()) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", maintainPlanGroup.getId());
						sn_json.put("Name", maintainPlanGroup.getName());
						sn_array.put(sn_json);
					}
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
	 * 新增或異動警訊會員群組資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            警訊會員群組資料
	 * @return 是否新增或異動成功
	 */
	@RequestMapping(value = "/m03/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONObject obj_array = new JSONObject(json);
			JSONArray json_array = obj_array.getJSONArray("MaintainPlanGroupOrg");
			for (int i = 0; i < json_array.length(); i++) {
				JSONObject obj = json_array.getJSONObject(i);
				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
				long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
				long maintainPlanGroupId = obj.isNull("MaintainPlanGroupId") == true ? 0 : obj.getLong("MaintainPlanGroupId");
				boolean flag = obj.isNull("Flag") == true ? false : obj.getBoolean("Flag");
				maintainPlanGroupOrgService.insertOrDelete(getBaseMemberId(), id, maintainPlanGroupId, orgId, flag);
			}
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		return responseJson.toString();
	}

}