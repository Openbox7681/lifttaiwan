package tw.gov.mohw.hisac.web.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.service.ReportService;

/**
 * 登入記錄控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_ClickHistoryController extends BaseController {

	@Autowired
	private ReportService reportService;
	
	
	
//	private String targetControllerName = "sys";
//	private String targetActionName = "login_history";

	/**
	 * 取得ClickHistory資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return ClickHistory資料
	 */
	@RequestMapping(value = "/click_history/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();

//		if (menuService.getReadPermission(baseMemberId, targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);			
			obj.put("CreateAccount", getBaseMemberAccount());
			List<Object[]> reports = reportService.getList(obj.toString());
			if (reports != null) {					
				for (Object[] report : reports) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("AppName", report[0]);
					sn_json.put("CreateTime", WebDatetime.toString((Date)report[1]));
					sn_json.put("Count", report[2]);					
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", reportService.getListSize((obj.toString())));
			listjson.put("datatable", sn_array);
			Map<String, JSONObject> m = new HashMap<>();
			saveMap(m, "json", listjson);
			JSONObject _json = m.get("json");		
			model.addAttribute("json", _json.toString());
			return "msg";	
	}
	
	
	/**
	 * 取得ClickHistory資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return ClickHistory資料
	 */
	@RequestMapping(value = "/click_history/getTop10Detail", method = RequestMethod.POST)
	public String GetTop10Detail(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();

//		if (menuService.getReadPermission(baseMemberId, targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);			
			String appName = obj.isNull("AppName") == true ? null : obj.getString("AppName");
			obj.put("CreateAccount", getBaseMemberAccount());
			List<Object[]> reports = reportService.getTop10Detail(obj.toString());
			int rank=1;
			if (reports != null) {					
				for (Object[] report : reports) {
					JSONObject sn_json = new JSONObject();	
					JSONObject urlObj = new JSONObject(report[0].toString());
					switch (appName) {
					}											
					sn_json.put("Rank", rank);											
					sn_json.put("Count", report[1]);
					sn_json.put("AppName", appName);
					sn_array.put(sn_json);
					rank++;
				}
			}			
			listjson.put("datatable", sn_array);
			Map<String, JSONObject> m = new HashMap<>();
			saveMap(m, "json", listjson);
			JSONObject _json = m.get("json");		
			model.addAttribute("json", _json.toString());
			return "msg";	
	}
}