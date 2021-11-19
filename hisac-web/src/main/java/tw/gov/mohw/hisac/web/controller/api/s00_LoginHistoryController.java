package tw.gov.mohw.hisac.web.controller.api;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SystemLog;

/**
 * 登入記錄控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_LoginHistoryController extends BaseController {

//	private String targetControllerName = "sys";
//	private String targetActionName = "login_history";

	/**
	 * 取得LoginHistory資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return System_Log資料
	 */
	@RequestMapping(value = "/login_history/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();

//		if (menuService.getReadPermission(baseMemberId, targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			obj.put("FuncName", "Login");
			obj.put("ActionName", "Login");
			obj.put("InputValue", getBaseMemberAccount());
			List<SystemLog> systemlogs = systemLogService.getListForLoginLog(obj.toString());
			if (systemlogs != null)
				for (SystemLog systemLog : systemlogs) {
					JSONObject sn_json = new JSONObject();					
					String[] error = systemLog.getInputValue().split(getBaseMemberAccount());
					if (error.length > 1)			
						sn_json.put("InputValue", error[1]);
					else
						sn_json.put("InputValue", "");
					sn_json.put("Status", systemLog.getStatus());
					sn_json.put("Ip", systemLog.getIp());										
					sn_json.put("CreateTime", WebDatetime.toString(systemLog.getCreateTime()));
					sn_array.put(sn_json);
				}
			listjson.put("total", systemLogService.getListSize((obj.toString())));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}