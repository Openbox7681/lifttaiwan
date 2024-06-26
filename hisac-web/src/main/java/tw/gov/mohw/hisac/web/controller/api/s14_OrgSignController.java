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
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.SpOrgSign;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.OrgSignService;

/**
 * 會員機構審核方式管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s14_OrgSignController extends BaseController {

	@Autowired
	private OrgSignService orgSignService;
	@Autowired
	private OrgService orgService;

	private String targetControllerName = "sys";
	private String targetActionName = "s14";

	/**
	 * 取得會員機構審核方式資料API
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
	@RequestMapping(value = "/s14/org/query", method = RequestMethod.POST)
	public String OrgQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
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
	 * 取得會員機構審核方式資料API
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
	@RequestMapping(value = "/s14/query", method = RequestMethod.POST)
	public @ResponseBody String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		// JSONObject obj = new JSONObject(json);
		// long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			List<SpOrgSign> spOrgSigns = orgSignService.getSpList(json);
			for (SpOrgSign spOrgSign : spOrgSigns) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", spOrgSign.getId());
				sn_json.put("ParentOrgId", spOrgSign.getOrgId());
				sn_json.put("Name", spOrgSign.getName());
				sn_json.put("Flag", spOrgSign.getFlag() == 0 ? false : true);
				sn_json.put("WarningIsExamine", spOrgSign.getWarningIsExamine());
				sn_json.put("NotificationIsExamine", spOrgSign.getNotificationIsExamine());
				sn_json.put("NotificationClosingIsExamine", spOrgSign.getNotificationClosingIsExamine());
				sn_array.put(sn_json);
			}

			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return listjson.toString();
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
	@RequestMapping(value = "/s14/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj_array = new JSONObject(json);
			long orgId = obj_array.isNull("OrgId") == true ? 0 : obj_array.getLong("OrgId");
			JSONArray json_array = obj_array.getJSONArray("OrgSignList");
			for (int i = 0; i < json_array.length(); i++) {
				JSONObject obj = json_array.getJSONObject(i);
				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
				long parentOrgId = obj.isNull("ParentOrgId") == true ? 0 : obj.getLong("ParentOrgId");
				boolean flag = obj.isNull("Flag") == true ? false : obj.getBoolean("Flag");
				int warningIsExamine = obj.isNull("WarningIsExamine") == true ? 2 : obj.getInt("WarningIsExamine");
				int notificationIsExamine = obj.isNull("NotificationIsExamine") == true ? 2 : obj.getInt("NotificationIsExamine");
				int notificationClosingIsExamine = obj.isNull("NotificationClosingIsExamine") == true ? 2 : obj.getInt("NotificationClosingIsExamine");

				orgSignService.save(getBaseMemberId(), id, orgId, parentOrgId, flag, warningIsExamine, notificationIsExamine, notificationClosingIsExamine);
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