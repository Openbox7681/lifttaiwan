//package tw.gov.mohw.hisac.web.controller.api;
//
//import java.util.List;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.JSONObject;
//import org.json.JSONArray;
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
//import tw.gov.mohw.hisac.web.WebMessage;
//import tw.gov.mohw.hisac.web.controller.BaseController;
//import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
//import tw.gov.mohw.hisac.web.domain.MessageRecipient;
//import tw.gov.mohw.hisac.web.service.MessageRecipientService;
//
///**
// * 最新消息管理控制器
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s19_MessageRecipientManagementController extends BaseController {
//	
//	@Autowired
//	private MessageRecipientService messageRecipientService;	
//
//	private String targetControllerName = "sys";
//	private String targetActionName = "s19";
//
//	/**
//	 * 取得警訊寄送人資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return 警訊寄送人資料
//	 */
//	@RequestMapping(value = "/s19/query", method = RequestMethod.POST)
//	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONObject listjson = new JSONObject();				
//		
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//
//			JSONArray sn_array = new JSONArray();			
//			List< MessageRecipient>  messageRecipients = messageRecipientService.getList(json);
//			listjson.put("total", messageRecipientService.getListSize(json));
//
//			if (messageRecipients != null) {
//				for (MessageRecipient messageRecipient : messageRecipients) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Id", messageRecipient.getId());
//					sn_json.put("Name", messageRecipient.getName());
//					sn_json.put("Email", messageRecipient.getEmail());			
//					sn_json.put("MobilePhone", messageRecipient.getMobilePhone());									
//					sn_array.put(sn_json);
//				}
//				listjson.put("datatable", sn_array);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//			}
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", listjson.toString());
//		return "msg";
//	}
//
//	/**
//	 * 取得警訊寄送人資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            警訊寄送人資料
//	 * @return 警訊寄送人資料
//	 */
//	@RequestMapping(value = "/s19/query/id", method = RequestMethod.POST)
//	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();		
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");			
//
//			// 取得指定 id 之警訊寄送人
//			MessageRecipient messageRecipient = messageRecipientService.get(id);
//						
//			listjson.put("Id", messageRecipient.getId());
//			listjson.put("Name", messageRecipient.getName());
//			listjson.put("Email", messageRecipient.getEmail());			
//			listjson.put("MobilePhone", messageRecipient.getMobilePhone());									
//			
//			
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", listjson.toString());
//		return "msg";
//	}
//	
//	
//	/**
//	 * 新增API
//	 * 	
//	 * @param json
//	 *            json	
//	 * @param model
//	 *            Model
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @return 是否成功
//	 */
//	@RequestMapping(value = "/s19/create", method = RequestMethod.POST)
//	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
//			MessageRecipient messageRecipient = messageRecipientService.insert(getBaseMemberId(), json);
//			if (messageRecipient != null) {
//				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
//				responseJson.put("success", true);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			}
//		}
//		else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return responseJson.toString();
//	}
//	
//	
//	/**
//	 * 修改API
//	 * 	
//	 * @param json
//	 *            json	
//	 * @param model
//	 *            Model
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @return 是否成功
//	 */
//	@RequestMapping(value = "/s19/update", method = RequestMethod.POST)
//	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//			if (!messageRecipientService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				MessageRecipient messageRecipient = messageRecipientService.update(getBaseMemberId(), json);
//				if (messageRecipient != null) {
//					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//					responseJson.put("success", true);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				} else {
//					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
//					responseJson.put("success", false);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//
//		return responseJson.toString();
//	}
//	
//	
//	/**
//	 * 刪除警訊寄送人資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 是否刪除成功
//	 */
//	@RequestMapping(value = "/s19/delete", method = RequestMethod.POST)
//	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			if (!messageRecipientService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				if (messageRecipientService.delete(id)) {
//					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
//					responseJson.put("success", true);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				} else {
//					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
//					responseJson.put("success", false);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return responseJson.toString();
//	}
//}