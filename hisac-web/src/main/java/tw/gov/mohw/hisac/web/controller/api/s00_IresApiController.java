//package tw.gov.mohw.hisac.web.controller.api;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.List;
//import java.util.Locale;
//import java.util.regex.Pattern;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import tw.gov.mohw.hisac.web.WebCrypto;
//import tw.gov.mohw.hisac.web.WebMessage;
//import tw.gov.mohw.hisac.web.controller.BaseController;
//import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
//import tw.gov.mohw.hisac.web.domain.Org;
//import tw.gov.mohw.hisac.web.service.OrgService;
//import tw.gov.mohw.hisac.web.service.IresApiService;
//
///**
// * 設置會員個人資料控制器
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s00_IresApiController extends BaseController {
//	final static Logger logger = LoggerFactory.getLogger(s00_IresApiController.class);
//
//	@Autowired
//	private OrgService orgService;
//	
//	@Autowired
//	private IresApiService iresApiService;
//	
//
//	// private String targetControllerName = "sys";
//	// private String targetActionName = "s00";
//
//	/**
//	 * 查詢金鑰資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            組織Id
//	 * @return org 資料與金鑰資訊
//	 */
//	@RequestMapping(value = "/ires/org/getAgencyCertificateByAgncyCode", method = RequestMethod.POST)
//	public String QueryOrg(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {				
//		JSONObject responseJson = new JSONObject();
//
//
//		Org org = orgService.getDataById(getBaseOrgId());
//		String orgCode = org.getCode();
//		JSONObject sn_json = new JSONObject(json);
//		sn_json.put("orgCode", orgCode);
//		
//		JSONObject response = new JSONObject();
//		
//
//		response =  iresApiService.getAgencyCertificateByAgncyCode(sn_json.toString());
//		
//		
//		if(response.getString("Status").equals("200")) {
//			JSONObject datatable = response.getJSONObject("msg");
//			datatable.put("orgName", org.getName());
//			datatable.put("orgCode", org.getCode());
//			
//			responseJson.put("success", true);
//			responseJson.put("data",datatable);
//			
//		}else {
//			responseJson.put("success", false);
//			responseJson.put("msg", "查無此機構金鑰，請確認機構代碼是否正確並重新申請金鑰匙");
//			JSONObject datatable = new JSONObject();
//			datatable.put("orgName", org.getName());
//			datatable.put("orgCode", org.getCode());
//			responseJson.put("data",datatable);
//
//		}
//	
//		systemLogService.insert(baseControllerName, baseActionName, getBaseOrgId().toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, baseIpAddress, getBaseMemberAccount());
//		
//		model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));
//		return "msg";
//	}
//	
//	/**
//	 * 通過/撤銷金鑰資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            組織Id
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/ires/org/changeAgencyCertificateByState", method = RequestMethod.POST)
//	public @ResponseBody String ChangeAgencyCertificateByState(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		JSONObject sn_json = new JSONObject(json);
//
//		Long orgId = sn_json.isNull("Id") ? null :sn_json.getLong("Id");
//		Org org = orgService.getDataById(orgId);
//		String orgCode = sn_json.isNull("orgCode") ? org.getCode() :sn_json.getString("orgCode");
//		String dataState = sn_json.isNull("dataState") ? null : sn_json.getString("dataState");
//		sn_json.put("orgCode", orgCode);
//		if (!orgService.isExist(getBaseOrgId())) {
//			responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, getBaseMemberAccount());
//		} else {
//			JSONObject response = iresApiService.changeAgencyCertificateByAgncyCode(sn_json.toString());
//			//審核通過成功 狀態為2
//			if (response.getString("Status").equals("200") && dataState.equals("Normal")) {
//				orgService.updateApiKeyStatus(org, "2");
//				responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//				responseJson.put("success", true);
//				responseJson.put("datatable", response.getJSONObject("msg"));
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, baseIpAddress, getBaseMemberAccount());
//			//撤銷金鑰成功	 狀態為3
//			}else if (response.getString("Status").equals("200") && dataState.equals("Delete")) {
//				orgService.updateApiKeyStatus(org, "3");
//				responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
//				responseJson.put("success", true);
//				responseJson.put("datatable", response.getJSONObject("msg"));
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, baseIpAddress, getBaseMemberAccount());
//			}else {
//				responseJson.put("msg", response.getString("msg"));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, getBaseMemberAccount());
//			}
//		}		
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//
//		
//	
//
//	
//	
//
//	/**
//	 * 申請金鑰資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            組織Id
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/ires/org/update", method = RequestMethod.POST)
//	public @ResponseBody String UpdateOrg(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString(); 
//		// long id = obj.getLong("Id");
//		Org org = orgService.getDataById(getBaseOrgId());
//		json = WebCrypto.getSafe(json);
//		JSONObject sn_json = new JSONObject(json);
//		String orgCode = sn_json.isNull("orgCode") ? org.getCode() :sn_json.getString("orgCode");
//		sn_json.put("orgCode", orgCode);
//		if (!orgService.isExist(getBaseOrgId())) {
//			responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, getBaseMemberAccount());
//		} else {
//			
//			JSONObject response = iresApiService.updateAgencyCertificateByAgncyCode(sn_json.toString());
//			if (response.getString("Status").equals("200")) {
//				Date dt = new Date();
//				
//				Calendar c = Calendar.getInstance(); 
//				c.setTime(dt); 
//				c.add(Calendar.YEAR, 3);
//				dt = c.getTime();
//
//				
//				JSONObject responseObj = response.getJSONObject("msg") ;
//				
//				String apiKey = responseObj.getString("AgencyKey");
//				
//				orgService.updateApiKeyData(org, "2", apiKey, dt);
//				
//				responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//				responseJson.put("success", true);
//				responseJson.put("datatable", response.getJSONObject("msg"));
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, baseIpAddress, getBaseMemberAccount());
//			} else {
//				responseJson.put("msg", response.getString("msg"));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, getBaseMemberAccount());
//			}
//		}
//
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	
//	
//}