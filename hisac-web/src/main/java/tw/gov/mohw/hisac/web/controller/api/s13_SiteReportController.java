package tw.gov.mohw.hisac.web.controller.api;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.service.SystemLogService;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SpWebSiteLoad;

/**
 * 角色資料維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s13_SiteReportController extends BaseController {
	@Autowired
	private SystemLogService systemLogService;

	private String targetControllerName = "sys";
	private String targetActionName = "s13";

	/**
	 * 取得網站資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 依照條件查詢角色資料
	 */
	@RequestMapping(value = "/s13/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<SpWebSiteLoad> spWebSiteLoads = systemLogService.getWebSiteLoad(json);
			JSONArray sn_array = new JSONArray();
			if (spWebSiteLoads != null) {
				for (SpWebSiteLoad spWebSiteLoad : spWebSiteLoads) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("FuncName", spWebSiteLoad.getFuncName());
					sn_json.put("Count", spWebSiteLoad.getCount());
					sn_array.put(sn_json);
				}
			}
			listjson.put("datatable", sn_array);
			model.addAttribute("json", listjson.toString());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}
