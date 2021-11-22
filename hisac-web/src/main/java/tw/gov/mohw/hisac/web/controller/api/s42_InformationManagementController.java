//package tw.gov.mohw.hisac.web.controller.api;
//
//import java.awt.Image;
//import java.io.BufferedOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.text.DecimalFormat;
//import java.text.MessageFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONObject;
//import org.json.JSONArray;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import tw.gov.mohw.hisac.web.WebCrypto;
//import tw.gov.mohw.hisac.web.WebDatetime;
//import tw.gov.mohw.hisac.web.WebMessage;
//import tw.gov.mohw.hisac.web.controller.BaseController;
//import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
//import tw.gov.mohw.hisac.web.domain.InformationManagement;
//import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;
//import tw.gov.mohw.hisac.web.domain.InformationManagementPic;
//import tw.gov.mohw.hisac.web.domain.Member;
//import tw.gov.mohw.hisac.web.domain.ProcessLog;
//import tw.gov.mohw.hisac.web.domain.SpButtonCount;
//import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
//import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
//import tw.gov.mohw.hisac.web.service.InformationManagementAttachService;
//import tw.gov.mohw.hisac.web.service.InformationManagementPicService;
//import tw.gov.mohw.hisac.web.service.InformationManagementService;
//import tw.gov.mohw.hisac.web.service.MailService;
//import tw.gov.mohw.hisac.web.service.ProcessLogService;
//
///**
// * 最新消息管理控制器
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s42_InformationManagementController extends BaseController {
//
//	@Autowired
//	private InformationManagementService informationManagementService;	
//	
//	@Autowired
//	private InformationManagementAttachService informationManagementAttachService;
//	
//	@Autowired
//	private InformationManagementPicService informationManagementPicService;
//	
//	@Autowired
//	private MailService mailService;	
//	@Autowired
//	private ProcessLogService processLogService;	
//
//	private String targetControllerName = "sys";
//	private String targetActionName = "s42";
//
//	/**
//	 * 取得InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return InformationManagement資料
//	 */
//	@RequestMapping(value = "/s42/query", method = RequestMethod.POST)
//	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONObject listjson = new JSONObject();
//		json = WebCrypto.getSafe(json);
//		JSONObject obj = new JSONObject(json);
//
//		// 取得 RoleId
//		if (baseMemberRole.IsAdmin == true) { // 1-SuperAdmin
//			obj.put("RoleId", 1);
//		} else if (baseMemberRole.IsHisac == true) { // 2-H-ISAC管理者
//			obj.put("RoleId", 2);
//		} else if (baseMemberRole.IsHisacContentSign == true) { // 4-H-ISAC內容審核者
//			obj.put("RoleId", 4);
//		}
//		obj.put("MemberId", getBaseMemberId());
//		json = obj.toString();
//
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//
//			JSONArray sn_array = new JSONArray();			
//			
//			List<InformationManagement> informationManagements = informationManagementService.getSpList(json);
//			listjson.put("total", informationManagementService.getSpListSize(json));
//
//			if (informationManagements != null) {
//				for (InformationManagement informationManagement : informationManagements) {
//					JSONObject sn_json = new JSONObject();
//
//					sn_json.put("Id", informationManagement.getId());									
//					sn_json.put("PostDateTime", WebDatetime.toString(informationManagement.getPostDateTime(), "yyyy-MM-dd"));
//					sn_json.put("TitleEdit", informationManagement.getTitleEdit());
//					sn_json.put("SourceNameEdit", informationManagement.getSourceNameEdit());
//					sn_json.put("SourceLinkEdit", informationManagement.getSourceLinkEdit());				
//					sn_json.put("ContentEdit", informationManagement.getContentEdit());
//					sn_json.put("Title", informationManagement.getTitle());
//					sn_json.put("SourceName", informationManagement.getSourceName());
//					sn_json.put("SourceLink", informationManagement.getSourceLink());				
//					sn_json.put("Content", informationManagement.getContent());					
//					sn_json.put("IsBreakLine", informationManagement.getIsBreakLine());				
//					sn_json.put("IsEnable", informationManagement.getIsEnable());				
//					sn_json.put("CreateId", informationManagement.getCreateId());					
//					sn_json.put("CreateTime", WebDatetime.toString(informationManagement.getCreateTime()));
//					sn_json.put("ModifyId", informationManagement.getModifyId());					
//					sn_json.put("ModifyTime", WebDatetime.toString(informationManagement.getModifyTime()));
//					sn_json.put("Status", informationManagement.getStatus());
//					sn_json.put("Remark", informationManagement.getRemark());
//
//					// 簽核流程用 - 開始
//					String currentStatus = String.valueOf(informationManagement.getStatus());
//					long createId = informationManagement.getCreateId();
//
//					// 依狀態(目前階段)及角色顯示按鍵
//					switch (currentStatus) {
//
//						case "1" : // 1-編輯中
//							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊
//
//							if (createId == getBaseMemberId()) {
//								// 3-H-ISAC內容維護者：編輯、刪除
//								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
//								sn_json.put("IsButtonDelete", true); // 是否顯示刪除按鍵
//							} else {
//								// 其他角色：無權限
//								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//								sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							}
//
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//						case "2" : // 2-撤銷中
//							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
//							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//
//							if (createId == getBaseMemberId()) {
//								sn_json.put("IsButtonUndo", true); // 是否顯示撤銷按鍵
//							} else {
//								sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							}
//
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//						case "3" : // 3-審核中
//							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
//							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//
//							if (baseMemberRole.IsHisacContentSign) {
//								sn_json.put("IsButtonReview", true); // 是否顯示審核按鍵
//							} else {
//								sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							}
//							break;
//
//						case "4" : // 4-已公告
//							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
//							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//						case "5" : // 5-已銷案
//							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
//							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//						case "6" : // 6-編輯中(退回)
//							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
//
//							if (createId == getBaseMemberId()) {
//								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
//							} else {
//								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							}
//
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//						default :
//							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊
//							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
//							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
//							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
//							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
//							break;
//
//					}
//					// 簽核流程用 - 結束
//
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
//	
//	/**
//	 * 取得InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            InformationManagement資料
//	 * @return InformationManagement資料
//	 */
//	@RequestMapping(value = "/s42/query/id", method = RequestMethod.POST)
//	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//
//			// 流程紀錄用
//			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
//
//			// 取得指定 id 之最新消息資料
//			InformationManagement informationManagement = informationManagementService.get(id);
//
//			// 流程紀錄用 - 開始
//			JSONArray messageLog_array = new JSONArray();
//			Date today = new Date();
//			List<ViewProcessLogMember> messageLogs = processLogService.getByPostId(String.valueOf(id), tableName);
//			if (messageLogs != null) {
//				for (ViewProcessLogMember messageLog : messageLogs) {
//					JSONObject messageLog_json = new JSONObject();
//					messageLog_json.put("PreStatus", messageLog.getPreStatus());
//					messageLog_json.put("Status", messageLog.getStatus());
//					messageLog_json.put("CreateTime", WebDatetime.toString(messageLog.getCreateTime()));
//					messageLog_json.put("Opinion", messageLog.getOpinion());
//					messageLog_json.put("CreateName", messageLog.getCreateName());
//					messageLog_json.put("PreName", messageLog.getPreName());
//					messageLog_json.put("Days", (today.getTime() - messageLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
//					messageLog_array.put(messageLog_json);
//				}
//			}
//			// 流程紀錄用 - 結束
//
//			JSONObject sn_json = new JSONObject();
//			sn_json.put("Id", informationManagement.getId());											
//			sn_json.put("PostDateTime", WebDatetime.toString(informationManagement.getPostDateTime(), "yyyy-MM-dd"));
//			sn_json.put("TitleEdit", informationManagement.getTitleEdit());
//			sn_json.put("SourceNameEdit", informationManagement.getSourceNameEdit());
//			sn_json.put("SourceLinkEdit", informationManagement.getSourceLinkEdit());				
//			sn_json.put("ContentEdit", informationManagement.getContentEdit());
//			sn_json.put("Title", informationManagement.getTitle());
//			sn_json.put("SourceName", informationManagement.getSourceName());
//			sn_json.put("SourceLink", informationManagement.getSourceLink());			
//			sn_json.put("Content", informationManagement.getContent());		
//			sn_json.put("IsBreakLine", informationManagement.getIsBreakLine());		
//			sn_json.put("IsEnable", informationManagement.getIsEnable());			
//			sn_json.put("CreateId", informationManagement.getCreateId());
//			sn_json.put("CreateTime", WebDatetime.toString(informationManagement.getCreateTime()));
//			sn_json.put("ModifyId", informationManagement.getModifyId());
//			sn_json.put("ModifyTime", WebDatetime.toString(informationManagement.getModifyTime()));
//			sn_json.put("Status", informationManagement.getStatus());
//			sn_json.put("Remark", informationManagement.getRemark());
//			sn_json.put("Name", informationManagement.getName());
//			sn_json.put("Email", informationManagement.getEmail());
//
//			// 流程紀錄用，將取得之流程紀錄放入 sn_json 中
//			sn_json.put("MessageLog", messageLog_array);
//
//			sn_array.put(sn_json);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", sn_array.toString());
//		return "msg";
//	}
//	
//	
//	/**
//	 * 新增InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            InformationManagement
//	 * @return 是否新增成功
//	 */
//	@RequestMapping(value = "/s42/create", method = RequestMethod.POST)
//	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			InformationManagement informationManagement = informationManagementService.insert(getBaseMemberId(), json);
//
//			// 流程紀錄用 - 開始
//			JSONObject obj = new JSONObject(json);
//			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
//
//			if (isWriteProcessLog) {
//				// 寄信				
//				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4);
//				// H-ISAC內容審核者
//				if (memberRoles != null) {
//					for (ViewMemberRoleMember memberRole : memberRoles) {
//						Member member = memberService.get(memberRole.getMemberId());
//						if (member != null && member.getIsEnable()) {
//							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Subject"), informationManagement.getTitle());
//							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Body"), member.getName(), informationManagement.getTitle());
//							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
//						}
//					}
//				}
//				processLogService.insert(getBaseMemberId(), json, String.valueOf(informationManagement.getId()));
//			}
//			// 流程紀錄用 - 結束
//
//			if (informationManagement != null) {
//
//				// 流程紀錄用 - 開始
//				responseJson.put("Id", informationManagement.getId());				
//				// 流程紀錄用 - 結束
//
//				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
//				responseJson.put("success", true);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	
//	/**
//	 * 更新InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            InformationManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s42/update", method = RequestMethod.POST)
//	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				InformationManagement informationManagement = informationManagementService.update(getBaseMemberId(), json);
//
//				// 流程紀錄用 - 開始
//				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
//
//				if (isWriteProcessLog) {
//					processLogService.insert(getBaseMemberId(), json, String.valueOf(informationManagement.getId()));
//
//					// 寄信
//					// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
//					// 主旨：H-ISAC最新消息("not.getPostId()")審核通知
//					// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！最新消息("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
//					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
//																								// H-ISAC內容審核者
//					if (memberRoles != null) {
//						for (ViewMemberRoleMember memberRole : memberRoles) {
//							Member member = memberService.get(memberRole.getMemberId());
//							if (member != null && member.getIsEnable()) {
//								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Subject"), informationManagement.getTitle());
//								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Body"), member.getName(), informationManagement.getTitle());
//								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
//							}
//						}
//					}
//				}
//				// 流程紀錄用 - 結束
//
//				if (informationManagement != null) {
//
//					// 流程紀錄用 - 開始
//					responseJson.put("Id", informationManagement.getId());					
//					// 流程紀錄用 - 結束
//
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
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	/**
//	 * 更新InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            InformationManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s42/modify", method = RequestMethod.POST)
//	public @ResponseBody String Modify(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				InformationManagement informationManagement = informationManagementService.modify(getBaseMemberId(), json);
//				if (informationManagement != null) {
//					responseJson.put("Id", informationManagement.getId());					
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
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//
//	/**
//	 * 停用InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            InformationManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s42/disable", method = RequestMethod.POST)
//	public @ResponseBody String Disable(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				informationManagementService.disable(getBaseMemberId(), id);
//			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	
//	/**
//	 * 啟用InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            InformationManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s42/enable", method = RequestMethod.POST)
//	public @ResponseBody String Enable(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				informationManagementService.enable(getBaseMemberId(), id);
//			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//
//	/**
//	 * 刪除InformationManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 是否刪除成功
//	 */
//	@RequestMapping(value = "/s42/delete", method = RequestMethod.POST)
//	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				try {
//					List<InformationManagementPic> informationManagementPics = informationManagementPicService.getAllByInformationManagementId(id);
//					if (informationManagementPics != null) {
//						for (InformationManagementPic informationManagementPic : informationManagementPics) {
//							informationManagementPicService.delete(informationManagementPic.getId());
//						}
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//				}
//				try {
//					List<InformationManagementAttach> informationManagementAttachs = informationManagementAttachService.getAllByInformationManagementId(id);
//					if (informationManagementAttachs != null) {
//						for (InformationManagementAttach informationManagementAttach : informationManagementAttachs) {
//							informationManagementAttachService.delete(informationManagementAttach.getId());
//						}
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//				}
//				if (informationManagementService.delete(id)) {
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
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	/**
//	 * 取得圖片資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return 圖片資料
//	 */
//	@RequestMapping(value = "/s42/pic/query", method = RequestMethod.POST)
//	public String QueryPic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONArray sn_array = new JSONArray();
//			
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long informationManagementId = obj.isNull("InformationManagementId") == true ? 0 : obj.getLong("InformationManagementId");
//			List<InformationManagementPic> informationManagementPics = informationManagementPicService.getAllByInformationManagementId(informationManagementId);
//			if (informationManagementPics != null) {
//				for (InformationManagementPic informationManagementPic : informationManagementPics) {
//					long size = informationManagementPic.getFileSize();
//					if (size <= 0)
//						return "0";
//					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
//					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//					String imageLink = "<img src=\"./api/p01/pic/download/" + informationManagementId + "/" + informationManagementPic.getId() + "\" title=\"" + informationManagementPic.getFileDesc() + "\" width=\"" + informationManagementPic.getImageWidth() + "\">";
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Id", informationManagementPic.getId());
//					sn_json.put("FileName", informationManagementPic.getFileName());
//					sn_json.put("FileType", informationManagementPic.getFileType());
//					sn_json.put("FileSize", fileSize);
//					sn_json.put("ImageWidth", informationManagementPic.getImageWidth());
//					sn_json.put("ImageHeight", informationManagementPic.getImageHeight());
//					sn_json.put("ImageLink", imageLink);
//					sn_json.put("FileDesc", informationManagementPic.getFileDesc());
//					sn_json.put("CreateId", informationManagementPic.getCreateId());					
//					sn_json.put("CreateTime", WebDatetime.toString(informationManagementPic.getCreateTime()));
//					sn_json.put("ModifyId", informationManagementPic.getModifyId());					
//					sn_json.put("ModifyTime", WebDatetime.toString(informationManagementPic.getModifyTime()));
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
//	 * 上傳圖檔API
//	 * 
//	 * @param file
//	 *            MultipartFile
//	 * @param id
//	 *            InformationManagementId
//	 * @param fileDesc
//	 *            檔案描述
//	 * @param model
//	 *            Model
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @return 是否成功
//	 */
//	@RequestMapping(value = "/s42/pic/upload", method = RequestMethod.POST)
//	public String UploadPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject sn_json = new JSONObject();
//			sn_json.put("InformationManagementId", id);
//			sn_json.put("FileDesc", fileDesc);
//			String json = sn_json.toString();
//			if (!file.isEmpty()) {
//				try {
//					byte[] bytes = file.getBytes();
//					sn_json.put("FileName", file.getOriginalFilename());
//					sn_json.put("FileType", file.getContentType());
//					sn_json.put("FileSize", file.getSize());
//					Image image = ImageIO.read(file.getInputStream());
//					int imageWidth = image.getWidth(null);
//					int imageHeight = image.getHeight(null);
//					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
//					sn_json.put("ImageWidth", imageWidth);
//					sn_json.put("ImageHeight", imageHeight);
//					json = sn_json.toString();
//					InformationManagement informationManagement = informationManagementService.get(id);
//					if (informationManagement != null) {
//						InformationManagementPic entity = informationManagementPicService.insert(getBaseMemberId(), json, bytes);
//						if (entity != null) {
//							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
//							responseJson.put("success", true);
//							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//						} else {
//							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//							responseJson.put("success", false);
//							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//						}
//					} else {
//						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//						responseJson.put("success", false);
//						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//					responseJson.put("success", false);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			} else {
//				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			}
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, "InformationManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", responseJson.toString());
//		return "msg";
//	}
//
//	/**
//	 * 刪除圖檔API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 是否刪除成功
//	 */
//	@RequestMapping(value = "/s42/pic/delete", method = RequestMethod.POST)
//	public @ResponseBody String DeletePic(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			if (!informationManagementPicService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				if (informationManagementPicService.delete(id)) {
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
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//
//	/**
//	 * 圖片輸出
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @param informationManagementId
//	 *            文章Id
//	 * @param id
//	 *            圖檔Id
//	 */
//	@RequestMapping(value = "/s42/pic/download/{informationManagementId}/{id}", method = RequestMethod.GET)
//	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
//		JSONObject sn_json = new JSONObject();
//		sn_json.put("InformationManagementId", informationManagementId);
//		sn_json.put("Id", id);
//		String json = sn_json.toString();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (!informationManagementService.isExist(informationManagementId)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else if (!informationManagementPicService.isExist(id)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else {
//				response.reset();
//				InformationManagementPic informationManagementPic = informationManagementPicService.getById(id);
//				try {
//					byte[] buffer = informationManagementPic.getFileContent();
//					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationManagementPic.getFileName(), StandardCharsets.UTF_8.toString()));
//					response.addHeader("Content-Length", "" + buffer.length);
//					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//					response.setContentType(informationManagementPic.getFileType());
//					toClient.write(buffer);
//					toClient.flush();
//					toClient.close();
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			}
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//	}
//
//	/**
//	 * 取得附件資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return 附件資料
//	 */
//	@RequestMapping(value = "/s42/attach/query", method = RequestMethod.POST)
//	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONArray sn_array = new JSONArray();
//			
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long informationManagementId = obj.isNull("InformationManagementId") == true ? 0 : obj.getLong("InformationManagementId");
//			List<InformationManagementAttach> informationManagementAttachs = informationManagementAttachService.getAllByInformationManagementId(informationManagementId);
//			if (informationManagementAttachs != null) {
//				for (InformationManagementAttach informationManagementAttach : informationManagementAttachs) {
//					long size = informationManagementAttach.getFileSize();
//					if (size <= 0)
//						return "0";
//					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
//					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("InformationManagementId", informationManagementAttach.getInformationManagementId());
//					sn_json.put("Id", informationManagementAttach.getId());
//					sn_json.put("FileName", informationManagementAttach.getFileName());
//					sn_json.put("FileType", informationManagementAttach.getFileType());
//					sn_json.put("FileSize", fileSize);
//					sn_json.put("FileHash", informationManagementAttach.getFileHash());
//					sn_json.put("FileDesc", informationManagementAttach.getFileDesc());
//					sn_json.put("CreateId", informationManagementAttach.getCreateId());					
//					sn_json.put("CreateTime", WebDatetime.toString(informationManagementAttach.getCreateTime()));
//					sn_json.put("ModifyId", informationManagementAttach.getModifyId());					
//					sn_json.put("ModifyTime", WebDatetime.toString(informationManagementAttach.getModifyTime()));
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
//	 * 上傳附件API
//	 * 
//	 * @param file
//	 *            MultipartFile
//	 * @param id
//	 *            InformationManagementId
//	 * @param fileDesc
//	 *            檔案描述
//	 * @param model
//	 *            Model
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @return 是否成功
//	 */
//	@RequestMapping(value = "/s42/attach/upload", method = RequestMethod.POST)
//	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject sn_json = new JSONObject();
//			sn_json.put("InformationManagementId", id);
//			sn_json.put("FileDesc", fileDesc);
//			String json = sn_json.toString();
//			if (!file.isEmpty()) {
//				try {
//					byte[] bytes = file.getBytes();
//					sn_json.put("FileName", file.getOriginalFilename());
//					sn_json.put("FileType", file.getContentType());
//					sn_json.put("FileSize", file.getSize());
//					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
//					json = sn_json.toString();
//					InformationManagement informationManagement = informationManagementService.get(id);
//					if (informationManagement != null) {
//						InformationManagementAttach entity = informationManagementAttachService.insert(getBaseMemberId(), json, bytes);
//						if (entity != null) {
//							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
//							responseJson.put("success", true);
//							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//						} else {
//							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//							responseJson.put("success", false);
//							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//						}
//					} else {
//						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//						responseJson.put("success", false);
//						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//					responseJson.put("success", false);
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			} else {
//				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			}
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, "InformationManagementId=" + id.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", responseJson.toString());
//		return "msg";
//	}
//
//	/**
//	 * 刪除附件API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            編號
//	 * @return 是否刪除成功
//	 */
//	@RequestMapping(value = "/s42/attach/delete", method = RequestMethod.POST)
//	public @ResponseBody String DeleteAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			if (!informationManagementAttachService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				if (informationManagementAttachService.delete(id)) {
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
//		return WebCrypto.getSafe(responseJson.toString());
//
//	}
//
//	/**
//	 * 附件下載
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @param informationManagementId
//	 *            文章Id
//	 * @param id
//	 *            附件Id
//	 */
//	@SuppressWarnings("deprecation")
//	@RequestMapping(value = "/s42/attach/download/{informationManagementId}/{id}", method = RequestMethod.GET)
//	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
//		JSONObject sn_json = new JSONObject();
//		sn_json.put("InformationManagementId", informationManagementId);
//		sn_json.put("Id", id);
//		String json = sn_json.toString();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (!informationManagementService.isExist(informationManagementId)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else if (!informationManagementAttachService.isExist(id)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else {
//				response.reset();
//				InformationManagementAttach informationManagementAttach = informationManagementAttachService.getById(id);
//				try {
//					byte[] buffer = informationManagementAttach.getFileContent();
//					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
//					// response.addHeader("Content-Disposition",
//					// "attachment;filename=" +
//					// URLEncoder.encode(informationManagementAttach.getFileName(),
//					// StandardCharsets.UTF_8.toString()));
//					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationManagementAttach.getFileName()));
//					response.addHeader("Content-Length", "" + buffer.length);
//					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//					response.setContentType("application/octet-stream");
//					toClient.write(buffer);
//					toClient.flush();
//					toClient.close();
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			}
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//	}
//	
//	/**
//	 * 審核情資分享資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 審核動作是否成功
//	 */
//	@RequestMapping(value = "/s42/examine", method = RequestMethod.POST)
//	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {
//
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
//			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
//			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
//			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
//			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
//			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");
//			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");			
//			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");			
//
//			// 簽核用
//			// String preStatus = obj.isNull("PreStatus") == true ? null : obj.getString("PreStatus");
//			// String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");
//
//			if (!informationManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//
//				// String oldPostId =
//				// newsManagementService.getById(String.valueOf(id)).getPostId();
//
//				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, String.valueOf(id));
//				InformationManagement informationManagement = informationManagementService.examine(getBaseMemberId(), String.valueOf(id), String.valueOf(status), title, sourceName, sourceLink, content, remark, isBreakLine, isEnable);
//
//				if (informationManagement != null && processLog != null) {
//					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//					responseJson.put("success", true);
//
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//					
//				} else {
//					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
//					responseJson.put("success", false);
//
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				}
//			}
//			// } else {
//			// json.put("msg", "PermissionDenied");
//			// json.put("success", false);
//			// }
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return WebCrypto.getSafe(responseJson.toString());
//	}
//	
//	/**
//	 * 取得情資分享button count資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件`
//	 * @return 情資分享button count資料
//	 */
//	@RequestMapping(value = "/s42/query/button/count", method = RequestMethod.POST)
//	public String QueryButtonCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			/*
//			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
//			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
//			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
//			 * 10-會員機構聯絡人 11-會員機構管理者
//			 */
//			if (baseMemberRole.IsAdmin == true) {
//				obj.put("RoleId", 1);
//			} else if (baseMemberRole.IsHisac == true) {
//				obj.put("RoleId", 2);
//			} else if (baseMemberRole.IsHisacContentSign == true) {
//				obj.put("RoleId", 4);
//			}
//
//			obj.put("MemberId", getBaseMemberId());
//			json = obj.toString();
//
//			JSONArray sn_array = new JSONArray();
//			List<SpButtonCount> spButtonCounts = informationManagementService.getSpButtonCount(json);
//			if (spButtonCounts != null)
//				for (SpButtonCount spButtonCount : spButtonCounts) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Status", spButtonCount.getStatus());
//					sn_json.put("Count", spButtonCount.getCount());
//					sn_array.put(sn_json);
//				}
//
//			listjson.put("datatable", sn_array);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//
//		model.addAttribute("json", listjson.toString());
//		return "msg";
//
//	}
//
//
//}