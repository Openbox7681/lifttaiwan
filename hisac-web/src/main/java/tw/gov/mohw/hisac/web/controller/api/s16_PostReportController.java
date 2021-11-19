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
import tw.gov.mohw.hisac.web.service.ActivityManagementService;
import tw.gov.mohw.hisac.web.service.AnaManagementService;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.NewsManagementService;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SpActivityManagementReport;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpInformationExchange2Report;
import tw.gov.mohw.hisac.web.domain.SpNewsManagementReport;

/**
 * 角色資料維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s16_PostReportController extends BaseController {
	@Autowired
	private NewsManagementService newsManagementService;
	@Autowired
	private ActivityManagementService activityManagementService;
	@Autowired
	private AnaManagementService anaManagementService;
	@Autowired
	private InformationExchangeService informationExchangeService;

	private String targetControllerName = "sys";
	private String targetActionName = "s16";

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
	@RequestMapping(value = "/s16/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<SpNewsManagementReport> spNewsManagementReports = newsManagementService.getReport(json);
			List<SpActivityManagementReport> spActivityManagementReports = activityManagementService.getReport(json);
			List<SpAnaManagementReport> spAnaManagementReports = anaManagementService.getReport(json);
			List<SpInformationExchange2Report> spInformationExchangeReports = informationExchangeService.getReport(json);
			{
				JSONArray sn_array = new JSONArray();
				if (spNewsManagementReports != null) {
					for (SpNewsManagementReport spNewsManagementReport : spNewsManagementReports) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Name", spNewsManagementReport.getName());
						sn_json.put("Count", spNewsManagementReport.getCount());
						sn_array.put(sn_json);
					}
				}
				listjson.put("news", sn_array);
			}
			{
				JSONArray sn_array = new JSONArray();
				if (spActivityManagementReports != null) {
					for (SpActivityManagementReport spActivityManagementReport : spActivityManagementReports) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Name", spActivityManagementReport.getName());
						sn_json.put("Count", spActivityManagementReport.getCount());
						sn_array.put(sn_json);
					}
				}
				listjson.put("activity", sn_array);
			}
			{
				JSONArray sn_array = new JSONArray();
				if (spAnaManagementReports != null) {
					for (SpAnaManagementReport spAnaManagementReport : spAnaManagementReports) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Name", spAnaManagementReport.getName());
						sn_json.put("Count", spAnaManagementReport.getCount());
						sn_array.put(sn_json);
					}
				}
				listjson.put("ana", sn_array);
			}
			{
				JSONArray sn_array = new JSONArray();
				JSONArray sn_total = new JSONArray();
				Long EditTotal = 0L;
				Long DelTotal = 0L;
				Long SignTotal = 0L;
				Long PubTotal = 0L;
				Long AlertTotal = 0L;
				Long NisacTotal = 0L;
				Long AllTotal = 0L;
				if (spInformationExchangeReports != null) {
					for (SpInformationExchange2Report spInformationExchangeReport : spInformationExchangeReports) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("SourceCode", spInformationExchangeReport.getSourceCode());
						sn_json.put("Name", spInformationExchangeReport.getName());
						sn_json.put("Edit", spInformationExchangeReport.getEdit());
						sn_json.put("Del", spInformationExchangeReport.getDel());
						sn_json.put("Sign", spInformationExchangeReport.getSign());
						sn_json.put("Pub", spInformationExchangeReport.getPub());
						sn_json.put("Alert", spInformationExchangeReport.getAlert());
						sn_json.put("Nisac", spInformationExchangeReport.getNisac());
						sn_json.put("Total", spInformationExchangeReport.getTotal());
						EditTotal += spInformationExchangeReport.getEdit();
						DelTotal += spInformationExchangeReport.getDel();
						SignTotal += spInformationExchangeReport.getSign();
						PubTotal += spInformationExchangeReport.getPub();
						AlertTotal += spInformationExchangeReport.getAlert();
						NisacTotal += spInformationExchangeReport.getNisac();
						AllTotal += spInformationExchangeReport.getTotal();
						sn_array.put(sn_json);
					}
				}
				listjson.put("information_exchange", sn_array);
				{
					JSONObject sn_json = new JSONObject();
					sn_json.put("SourceCode", "0");
					sn_json.put("Name", "Total");
					sn_json.put("Edit", EditTotal);
					sn_json.put("Del", DelTotal);
					sn_json.put("Sign", SignTotal);
					sn_json.put("Pub", PubTotal);
					sn_json.put("Alert", AlertTotal);
					sn_json.put("Nisac", NisacTotal);
					sn_json.put("Total", AllTotal);
					sn_total.put(sn_json);
				}
				listjson.put("information_exchange_total", sn_total);
			}
			model.addAttribute("json", listjson.toString());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}
