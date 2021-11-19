package tw.gov.mohw.hisac.web.controller.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.gov.mohw.hisac.web.service.CCService;

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
/**
 * 中繼站控制器
 */
@Controller
@RequestMapping(value = "/cyb/api", produces = "application/json; charset=utf-8")
public class c04_CCController extends BaseController {

	
	@Autowired
	private CCService CCService;

	private String targetControllerName = "cyb";
	private String targetActionName = "c04";


	/**
	 * 取得是否在中繼站資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 是否
	 */
	@RequestMapping(value = "/c04/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String dnameOrIpname = obj.isNull("IP") == true ? null : obj.getString("IP");		
			listjson.put("IsinCC", CCService.getByDnameOrIpname(dnameOrIpname));
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}
}