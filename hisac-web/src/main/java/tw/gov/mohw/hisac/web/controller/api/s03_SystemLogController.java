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
import tw.gov.mohw.hisac.web.WebCrypto;


/**
 * 操作記錄控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s03_SystemLogController extends BaseController {

	private String targetControllerName = "sys";
	private String targetActionName = "s03";

	/**
	 * 取得System_Log資料API
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
	@RequestMapping(value = "/s03/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		json = WebCrypto.getSafe(json);
		

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<SystemLog> systemlogs = systemLogService.getList(json);
			if (systemlogs != null)
				for (SystemLog systemLog : systemlogs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", systemLog.getId());
					sn_json.put("AppName", systemLog.getAppName());
					sn_json.put("FuncName", systemLog.getFuncName());
					sn_json.put("InputValue", systemLog.getInputValue());
					sn_json.put("ActionName", systemLog.getActionName());
					sn_json.put("Status", systemLog.getStatus());
					sn_json.put("Ip", systemLog.getIp());
					sn_json.put("HashCode", systemLog.getHashCode());
					sn_json.put("CreateAccount", systemLog.getCreateAccount());
					sn_json.put("CreateTime", WebDatetime.toString(systemLog.getCreateTime()));
					sn_array.put(sn_json);
				}
			listjson.put("total", systemLogService.getListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}