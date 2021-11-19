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
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.SpInformationExchangeReport;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.service.AlertTypeService;
import tw.gov.mohw.hisac.web.service.EventTypeService;
import tw.gov.mohw.hisac.web.service.InformationExchangeReportService;

/**
 * 情資統計表控制器
 */
@Controller
@RequestMapping(value = "/cyb/api", produces = "application/json; charset=utf-8")
public class c03_InformationExchangeReportController extends BaseController {

	private String targetControllerName = "cyb";
	private String targetActionName = "c03";

	@Autowired
	private InformationExchangeReportService informationExchangeReportService;
	@Autowired
	private AlertTypeService alertTypeService;
	@Autowired
	private EventTypeService eventTypeService;

	/**
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/c03/query", method = RequestMethod.POST)
	public String QueryIn(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray datatable_in = new JSONArray();
			JSONArray datatable_out = new JSONArray();
			List<SpInformationExchangeReport> infoList = informationExchangeReportService.getSpListIn(json);
			if (infoList != null)
				for (SpInformationExchangeReport info : infoList) {					
					if (info != null && info.getCategory()!=null && !info.getCategory().equals("")) {						
						JSONObject sn_json = new JSONObject();
						sn_json.put("Code", info.getCategory());
						sn_json.put("AlertCode", info.getAlertCode());
						sn_json.put("TransferType", info.getTransferType());
						sn_json.put("Count", info.getCount());
						datatable_in.put(sn_json);
					}					
				}

			listjson.put("datatable_in", datatable_in);

			List<SpInformationExchangeReport> infoListOut = informationExchangeReportService.getSpListOut(json);
			if (infoListOut != null)
				for (SpInformationExchangeReport info : infoListOut) {
					
					if (info != null && info.getCategory()!=null && !info.getCategory().equals("")) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Code", info.getCategory());
						sn_json.put("AlertCode", info.getAlertCode());
						sn_json.put("TransferType", info.getTransferType());
						sn_json.put("Count", info.getCount());
						datatable_out.put(sn_json);
					}					
				}

			listjson.put("datatable_out", datatable_out);

			//System.out.println(listjson.toString());
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
	@RequestMapping(value = "/c03/getalert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/c03/getevent", method = RequestMethod.POST)
	public String Getevent(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<EventType> eventTypes = eventTypeService.getAll();
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