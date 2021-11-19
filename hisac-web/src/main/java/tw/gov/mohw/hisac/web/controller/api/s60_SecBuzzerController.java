package tw.gov.mohw.hisac.web.controller.api;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;

/**
 * SecBuzzer控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s60_SecBuzzerController extends BaseController {

	@Autowired
	private InformationExchangeService informationExchangeService;

	private String targetControllerName = "sys";
	private String targetActionName = "s60";

	/**
	 * 取得SecBuzzer資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return SecBuzzer資料
	 */
	@RequestMapping(value = "/s60/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			json = obj.toString();
			List<ViewInformationExchangeSecbuzzerTitle> informationExchanges = informationExchangeService.getSecBuzzerList(json);
			if (informationExchanges != null) {
				for (ViewInformationExchangeSecbuzzerTitle informationExchange : informationExchanges) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", informationExchange.getId());
					sn_json.put("Cve", "(" + informationExchange.getIncidentId() + ")");
					sn_json.put("Date", WebDatetime.toString(informationExchange.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Title", informationExchange.getIncidentTitle());
					sn_json.put("Sort", informationExchange.getSort());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", informationExchangeService.getSecBuzzerListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得SecBuzzer資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return SecBuzzer資料
	 */
	@RequestMapping(value = "/s60/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			InformationExchange informationExchanges = informationExchangeService.getById(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", informationExchanges.getId());
			sn_json.put("IncidentId", informationExchanges.getIncidentId());
			sn_json.put("IncidentTitle", informationExchanges.getIncidentTitle());
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(informationExchanges.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("IncidentReportedTime", WebDatetime.toString(informationExchanges.getIncidentReportedTime(), "yyyy-MM-dd"));
			sn_json.put("Description", myFilter.stripXSS(informationExchanges.getDescription()));
			sn_json.put("ReporterNameUrl", informationExchanges.getReporterNameUrl());
			sn_json.put("ReporterName", informationExchanges.getReporterName());
			sn_json.put("ResponderPartyName", informationExchanges.getResponderPartyName());
			sn_json.put("ResponderContactNumbers", informationExchanges.getResponderContactNumbers());
			sn_json.put("ResponderElectronicAddressIdentifiers", informationExchanges.getResponderElectronicAddressIdentifiers());
			sn_json.put("ImpactQualification", informationExchanges.getImpactQualification());
			double impactQualification = 0;
			impactQualification = ((double) informationExchanges.getImpactQualification()) / 10;
			DecimalFormat df = new DecimalFormat("0.0");
			sn_json.put("ImpactQualification", df.format(impactQualification));
			sn_json.put("CoaDescription", informationExchanges.getCoaDescription());
			sn_json.put("Confidence", informationExchanges.getConfidence());
			sn_json.put("Reference", informationExchanges.getReference());
			sn_json.put("AffectedSoftwareDescription", informationExchanges.getAffectedSoftwareDescription());
			sn_json.put("Status", informationExchanges.getStatus());
			sn_json.put("Sort", informationExchanges.getSort());
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 更新SecBuzzer資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            警訊發佈群組
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s60/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			if (!informationExchangeService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				InformationExchange informationExchange = informationExchangeService.modify(getBaseMemberId(), json);
				if (informationExchange != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		return responseJson.toString();
	}
	
	/**
	 * 刪除SecBuzzer資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s60/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			if (!informationExchangeService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (informationExchangeService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
}
