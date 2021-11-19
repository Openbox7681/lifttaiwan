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

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReport;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReportList;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.service.MessageMonthReportService;
import tw.gov.mohw.hisac.web.service.AlertTypeService;

/**
 * 警訊月報表控制器
 */
@Controller
@RequestMapping(value = "/not/api", produces = "application/json; charset=utf-8")
public class n03_MessageMonthReportController extends BaseController {

	private String targetControllerName = "not";
	private String targetActionName = "n03";

	@Autowired
	private MessageMonthReportService messageMonthReportService;
	@Autowired
	private AlertTypeService alertTypeService;
	
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
	@RequestMapping(value = "/n03/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			List<SpMessageMonthReport> spMessageMonthReports = messageMonthReportService.getListCount(json);
			if (spMessageMonthReports != null)
				for (SpMessageMonthReport spMessageMonthReport : spMessageMonthReports) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("YearMonth", spMessageMonthReport.getYear() + "-" + spMessageMonthReport.getMonth());
					sn_json.put("AlertCode", spMessageMonthReport.getAlertCode());
					sn_json.put("Count", spMessageMonthReport.getCount());
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
	 * 取得警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/n03/query/message/report/list", method = RequestMethod.POST)
	public String QueryMessageReportList(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			List<SpMessageMonthReportList> spMessageMonthReportLists = messageMonthReportService.getReportList(json);

			if (spMessageMonthReportLists != null)
				for (SpMessageMonthReportList spMessageMonthReportList : spMessageMonthReportLists) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", spMessageMonthReportList.getId());
					sn_json.put("PostId", spMessageMonthReportList.getPostId());
					sn_json.put("AlertCode", spMessageMonthReportList.getAlertCode());
					sn_json.put("PostDateTime", WebDatetime.toString(spMessageMonthReportList.getPostDateTime()));
					sn_json.put("Subject", spMessageMonthReportList.getSubject());
					sn_json.put("Status", spMessageMonthReportList.getStatus());
					sn_json.put("CreateName", spMessageMonthReportList.getCreateName());
					sn_json.put("IsReply", spMessageMonthReportList.getIsReply());
					sn_json.put("SourceName", spMessageMonthReportList.getSourceName());

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
	@RequestMapping(value = "/n03/getalert", method = RequestMethod.POST)
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

	// /**
	// * 取得eventType資料API
	// *
	// * @param locale
	// * Locale
	// * @return eventType資料
	// */
	// @RequestMapping(value = "/n03/getevent", method = RequestMethod.POST)
	// public String Getevent(Locale locale, HttpServletRequest request, Model
	// model, @RequestBody String json) {
	// JSONArray sn_array = new JSONArray();
	// if (menuService.getReadPermission(getBaseMemberId(), targetControllerName,
	// targetActionName)) {
	// JSONObject obj = new JSONObject(json);
	// String alertCode = obj.isNull("AlertCode") == true ? null :
	// obj.getString("AlertCode");
	//
	// List<EventType> eventTypes = eventTypeService.getByAlertCode(alertCode);
	// if (eventTypes != null)
	// for (EventType eventType : eventTypes) {
	// JSONObject sn_json = new JSONObject();
	// sn_json.put("Code", eventType.getCode());
	// sn_json.put("Name", eventType.getName());
	// sn_json.put("AlertCode", eventType.getAlertCode());
	// sn_array.put(sn_json);
	// }
	// systemLogService.insert(baseControllerName, baseActionName, json,
	// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
	// getBaseIpAddress(), getBaseMemberAccount());
	// } else {
	// systemLogService.insert(baseControllerName, baseActionName, json,
	// SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny,
	// getBaseIpAddress(), getBaseMemberAccount());
	// }
	// model.addAttribute("json", sn_array.toString());
	// return "msg";
	// }

}