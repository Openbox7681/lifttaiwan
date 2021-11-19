package tw.gov.mohw.hisac.web.controller.api;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import tw.gov.mohw.hisac.web.service.CommentService;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;

/**
 * SecBuzzer控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p06_SecBuzzerController extends BaseController {

	@Autowired
	private InformationExchangeService informationExchangeService;

	
	@Autowired
	private CommentService commentService;

	private String targetControllerName = "pub";
	private String targetActionName = "p06";

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
	@RequestMapping(value = "/p06/query", method = RequestMethod.POST)
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
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", informationExchangeService.getSecBuzzerListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			Map<String, JSONObject> m = new HashMap<>();
			saveMap(m, "json", listjson);
			JSONObject _json = m.get("json");		
			model.addAttribute("json", _json.toString());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			Map<String, JSONObject> m = new HashMap<>();
			saveMap(m, "json", listjson);
			JSONObject _json = m.get("json");		
			model.addAttribute("json", _json.toString());
		}
		Map<String, JSONObject> m = new HashMap<>();
		saveMap(m, "json", listjson);
		JSONObject _json = m.get("json");		
		model.addAttribute("json", _json.toString());
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
	@RequestMapping(value = "/p06/query/id", method = RequestMethod.POST)
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
			if (!(getBaseCILevel().equals("0") && getBaseOrgType().equals("3"))) {								
				sn_json.put("Description", myFilter.stripXSS(informationExchanges.getDescription()));
				sn_json.put("IsDetail", true);				
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {							
				sn_json.put("Description", WebMessage.getMessage("orgCILevelNotAllow", null, locale));
				sn_json.put("IsDetail", false);			
			}

			// Begin: Star Rating & Comments works
			sn_json.put("AvgStars", 1);
			sn_json.put("Comments", commentService.getByArticleComments((long) 6, id));
			sn_json.put("IsEnableRating", true);
			
						
			// End: Star Rating & Comments works		

			sn_array.put(sn_json);
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		Map<String, JSONArray> m = new HashMap<>();
		saveMap(m, "json", sn_array);
		JSONArray _json = m.get("json");		
		model.addAttribute("json", _json.toString());
		return "msg";
	}

	/**
	 * 新增星數評分資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			新增資料
	 * @return 是否新增成功
	 */
//	@RequestMapping(value = "/p06/starRating/insert", method = RequestMethod.POST)
//	public @ResponseBody String starRatingInsert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
//		JSONObject responseJson = new JSONObject();
//		boolean error = false;
//		try{
//			if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//				JSONObject obj = new JSONObject(json);
//				String id = obj.isNull("Id") == true ? null : obj.getString("Id");
//				long star = obj.isNull("RatingStar") == true ? 0 : obj.getLong("RatingStar");
//				if (!starRatingService.isExist((long) 6, id, getBaseMemberId())){
//					if ( 0 != star){
//						JSONObject star_json = new JSONObject();
//						star_json.put("Type", (long) 6);
//						star_json.put("ArticleId", id);
//						star_json.put("Star", star);
//						starRatingService.insert(getBaseMemberId(), star_json);
//					}else{
//						throw new Exception();
//					}
//				}else{
//					throw new Exception();
//				}
//			}else{
//				throw new Exception();
//			}
//		}catch(Exception err){
//			error = true;
//		}
//
//		if(error){
//			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
//			responseJson.put("success", false);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//		}else{
//			responseJson.put("success", true);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		
//		return responseJson.toString();
//	}
	
	/**
	 * 新增留言資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			新增資料
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/p06/comment/insert", method = RequestMethod.POST)
	public @ResponseBody String commentInsert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
		JSONObject responseJson = new JSONObject();
		boolean error = false;
		try{
			if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
				JSONObject obj = new JSONObject(json);
				String id = obj.isNull("Id") == true ? null : obj.getString("Id");
				String comment = obj.isNull("Comment") == true ? null : obj.getString("Comment");
				boolean isHideName = obj.isNull("IsHideName") == true ? false : obj.getBoolean("IsHideName");				
				if (!comment.equals(null)){
					JSONObject star_json = new JSONObject();
					star_json.put("Type", (long) 6);
					star_json.put("ArticleId", id);						
					star_json.put("Comment", comment);
					star_json.put("IsHideName", isHideName);						
					commentService.insert(getBaseMemberId(), star_json);
					}else{
						throw new Exception();
					}				
			}else{
				throw new Exception();
			}
		}catch(Exception err){
			error = true;
		}

		if(error){
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}else{
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		
		return responseJson.toString();
	}
	
	

	/**
	 * 刪除留言資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			刪除資料
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/p06/comment/delete", method = RequestMethod.POST)
	public @ResponseBody String commentDelete(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		boolean error = false;
		try{
			if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
				JSONObject obj = new JSONObject(json);
				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");										
				commentService.delete(id);			
			}else{
				throw new Exception();
			}
		}catch(Exception err){
			error = true;
		}

		if(error){
			responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}else{
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		
		return responseJson.toString();
	}
}
