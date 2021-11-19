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
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SpMessageWeekReport;
import tw.gov.mohw.hisac.web.filter.MyFilter;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.service.AlertTypeService;
import tw.gov.mohw.hisac.web.service.EventTypeService;
import tw.gov.mohw.hisac.web.service.MessageWeekReportService;

/**
 * 警訊週報表控制器
 */
@Controller
@RequestMapping(value = "/not/api", produces = "application/json; charset=utf-8")
public class n04_MessageWeekReportController extends BaseController {

	private String targetControllerName = "not";
	private String targetActionName = "n04";

	@Autowired
	private MessageWeekReportService messageWeekReportService;
	@Autowired
	private AlertTypeService alertTypeService;
	@Autowired
	private EventTypeService eventTypeService;

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
	 * @return 警訊會員群組會員機構資料
	 */
	@RequestMapping(value = "/n04/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();

			List<SpMessageWeekReport> spMessageWeekReports = messageWeekReportService.getSpList(json);
			if (spMessageWeekReports != null)
				for (SpMessageWeekReport spMessageWeekReport : spMessageWeekReports) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("EventCode", spMessageWeekReport.getEventCode());
					sn_json.put("AlertCode", spMessageWeekReport.getAlertCode());
					sn_json.put("Count", spMessageWeekReport.getCount());

					sn_array.put(sn_json);

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

	/**
	 * 取得alertType資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            alertType資料
	 * @return alertType資料
	 */
	@RequestMapping(value = "/n04/getalert", method = RequestMethod.POST)
	public String Getalert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONArray sn_array = new JSONArray();

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<AlertType> alertTypes = alertTypeService.getList();
			if (alertTypes != null)
				for (AlertType alertType : alertTypes) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", alertType.getCode());
					sn_json.put("Name", alertType.getName());
					sn_array.put(sn_json);
				}

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得eventType資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            eventType資料
	 * @return eventType資料
	 */
	@RequestMapping(value = "/n04/getevent", method = RequestMethod.POST)
	public String Getevent(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		MyFilter myFilter = new MyFilter();
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			alertCode = myFilter.filterString(alertCode);
			List<EventType> eventTypes = eventTypeService.getByAlertCode(alertCode);
			if (eventTypes != null)
				for (EventType eventType : eventTypes) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", eventType.getCode());
					sn_json.put("Name", eventType.getName());
					sn_json.put("AlertCode", eventType.getAlertCode());
					sn_array.put(sn_json);
				}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

}