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
//import tw.gov.mohw.hisac.web.domain.ActivityManagement;
//import tw.gov.mohw.hisac.web.domain.ActivityManagementAttach;
//import tw.gov.mohw.hisac.web.domain.ActivityManagementPic;
//import tw.gov.mohw.hisac.web.domain.Member;
//import tw.gov.mohw.hisac.web.domain.ProcessLog;
//import tw.gov.mohw.hisac.web.domain.SpButtonCount;
//import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;
//import tw.gov.mohw.hisac.web.domain.ViewActivityManagementPicMember;
//import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
//import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
//import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
//import tw.gov.mohw.hisac.web.service.ActivityManagementService;
//import tw.gov.mohw.hisac.web.service.MailService;
//import tw.gov.mohw.hisac.web.service.ProcessLogService;
//import tw.gov.mohw.hisac.web.service.ActivityManagementAttachService;
//import tw.gov.mohw.hisac.web.service.ActivityManagementPicService;
//
///**
// * 活動訊息管理控制器
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s32_ActivityManagementController extends BaseController {
//
//	@Autowired
//	private ActivityManagementService activityManagementService;
//	@Autowired
//	private ActivityManagementAttachService activityManagementAttachService;
//	@Autowired
//	private ActivityManagementPicService activityManagementPicService;
//
//	@Autowired
//	private ProcessLogService processLogService;
//
//	@Autowired
//	private MailService mailService;
//
//	private String targetControllerName = "sys";
//	private String targetActionName = "s32";
//
//	/**
//	 * 取得ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return ActivityManagement資料
//	 */
//	@RequestMapping(value = "/s32/query", method = RequestMethod.POST)
//	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONObject listjson = new JSONObject();
//
//		/*
//		 * MemberRoleVariable.java 與「角色資料維護」 之設定 1-SuperAdmin：IsAdmin
//		 * 2-H-ISAC管理者：IsHisac 3-H-ISAC內容維護者：IsHisacContent
//		 * 4-H-ISAC內容審核者：IsHisacContentSign 5-H-ISAC警訊建立者：IsHisacInfoBuilder
//		 * 6-H-ISAC警訊審核者：IsHisacInfoSign 7-權責單位警訊審核者：IsApplySign
//		 * 8-權責單位聯絡人：IsApplyContact 9-權責單位管理者：IsApplyAdmin
//		 * 10-會員機構聯絡人：IsMemberContact 11-會員機構管理者：IsMemberAdmin
//		 * 12-HISAC通報審核者：IsHisacNotifySign 13-HISAC情資維護者：IsHisacIXContent
//		 * 14-HISAC-情資審核者：IsHisacIXContentSign 15-權責單位通報審核者：IsApplySingAdmin
//		 */
//		json = WebCrypto.getSafe(json);
//		JSONObject obj = new JSONObject(json);
//
//		// 取得 RoleId
//		if (baseMemberRole.IsAdmin == true) { // 1-SuperAdmin
//			obj.put("RoleId", 1);
//		} else if (baseMemberRole.IsHisac == true) { // 2-H-ISAC管理者
//			obj.put("RoleId", 2);
//		} else if (baseMemberRole.IsHisacContent == true) { // 3-H-ISAC內容維護者
//			obj.put("RoleId", 3);
//		} else if (baseMemberRole.IsHisacContentSign == true) { // 4-H-ISAC內容審核者
//			obj.put("RoleId", 4);
//		}
//		obj.put("MemberId", getBaseMemberId());
//		json = obj.toString();
//
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//
//			JSONArray sn_array = new JSONArray();
//			// listjson.put("total",
//			// activityManagementService.getListSize(json));
//			// List<ViewActivityManagementMember> activityManagements =
//			// activityManagementService.getList(json);
//
//			// 改用 store procedure
//			List<ViewActivityManagementMember> activityManagements = activityManagementService.getSpList(json);
//			listjson.put("total", activityManagementService.getSpListSize(json));
//
//			if (activityManagements != null) {
//				for (ViewActivityManagementMember activityManagement : activityManagements) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Id", activityManagement.getId());
//					sn_json.put("PostId", activityManagement.getPostId());
//					sn_json.put("PostType", activityManagement.getPostType());
//					sn_json.put("PostDateTime", WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd"));
//					sn_json.put("Title", activityManagement.getTitle());
//					sn_json.put("SourceName", activityManagement.getSourceName());
//					sn_json.put("SourceLink", activityManagement.getSourceLink());
//					sn_json.put("ContentType", activityManagement.getContentType());
//					sn_json.put("Content", activityManagement.getContent());
//					sn_json.put("ExternalLink", activityManagement.getExternalLink());
//					sn_json.put("IsBreakLine", activityManagement.getIsBreakLine());
//					sn_json.put("StartDateTime", WebDatetime.toString(activityManagement.getStartDateTime(), "yyyy-MM-dd"));
//					sn_json.put("EndDateTime", WebDatetime.toString(activityManagement.getEndDateTime(), "yyyy-MM-dd"));
//					sn_json.put("IsEnable", activityManagement.getIsEnable());
//					sn_json.put("IsPublic", activityManagement.getIsPublic());
//					sn_json.put("CreateId", activityManagement.getCreateId());
//					sn_json.put("CreateName", activityManagement.getCreateName());
//					sn_json.put("CreateTime", WebDatetime.toString(activityManagement.getCreateTime()));
//					sn_json.put("ModifyId", activityManagement.getModifyId());
//					sn_json.put("ModifyName", activityManagement.getModifyName());
//					sn_json.put("ModifyTime", WebDatetime.toString(activityManagement.getModifyTime()));
//					sn_json.put("Status", activityManagement.getStatus());
//					sn_json.put("Sort", activityManagement.getSort());
//
//					// 簽核流程用 - 開始
//					String currentStatus = String.valueOf(activityManagement.getStatus());
//
//					// 依狀態(目前階段)及角色顯示按鍵
//					switch (currentStatus) {
//
//						case "1" : // 1-編輯中
//							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊
//
//							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
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
//							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
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
//							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContentSign) {
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
//							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊
//
//							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
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
//	/**
//	 * 取得ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            ActivityManagement資料
//	 * @return ActivityManagement資料
//	 */
//	@RequestMapping(value = "/s32/query/id", method = RequestMethod.POST)
//	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//
//			// 流程紀錄用
//			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
//
//			// 取得指定 id 之活動訊息資料
//			ActivityManagement activityManagement = activityManagementService.get(id);
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
//			sn_json.put("Id", activityManagement.getId());
//			sn_json.put("PostId", activityManagement.getPostId());
//			sn_json.put("PostType", activityManagement.getPostType());
//			sn_json.put("PostDateTime", WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd"));
//			sn_json.put("Title", activityManagement.getTitle());
//			sn_json.put("SourceName", activityManagement.getSourceName());
//			sn_json.put("SourceLink", activityManagement.getSourceLink());
//			sn_json.put("ContentType", activityManagement.getContentType());
//			sn_json.put("Content", activityManagement.getContent());
//			sn_json.put("ExternalLink", activityManagement.getExternalLink());
//			sn_json.put("IsBreakLine", activityManagement.getIsBreakLine());
//			sn_json.put("StartDateTime", WebDatetime.toString(activityManagement.getStartDateTime(), "yyyy-MM-dd"));
//			sn_json.put("EndDateTime", WebDatetime.toString(activityManagement.getEndDateTime(), "yyyy-MM-dd"));
//			sn_json.put("IsEnable", activityManagement.getIsEnable());
//			sn_json.put("IsPublic", activityManagement.getIsPublic());
//			sn_json.put("CreateId", activityManagement.getCreateId());
//			sn_json.put("CreateTime", WebDatetime.toString(activityManagement.getCreateTime()));
//			sn_json.put("ModifyId", activityManagement.getModifyId());
//			sn_json.put("ModifyTime", WebDatetime.toString(activityManagement.getModifyTime()));
//			sn_json.put("Status", activityManagement.getStatus());
//			sn_json.put("Sort", activityManagement.getSort());
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
//	/**
//	 * 新增ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            ActivityManagement
//	 * @return 是否新增成功
//	 */
//	@RequestMapping(value = "/s32/create", method = RequestMethod.POST)
//	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			ActivityManagement activitymanagement = activityManagementService.insert(getBaseMemberId(), json, false);
//
//			// 流程紀錄用 - 開始
//			JSONObject obj = new JSONObject(json);
//			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
//
//			if (isWriteProcessLog) {
//				// 寄信
//				// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
//				// 主旨：H-ISAC活動訊息("not.getPostId()")審核通知
//				// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！活動訊息("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
//				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
//																							// H-ISAC內容審核者
//				if (memberRoles != null) {
//					for (ViewMemberRoleMember memberRole : memberRoles) {
//						Member member = memberService.get(memberRole.getMemberId());
//						if (member != null && member.getIsEnable()) {
//							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity1To3Subject"), activitymanagement.getPostId());
//							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity1To3Body"), member.getName(), activitymanagement.getPostId());
//							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
//						}
//					}
//				}
//
//				processLogService.insert(getBaseMemberId(), json, String.valueOf(activitymanagement.getId()));
//			}
//			// 流程紀錄用 - 結束
//
//			if (activitymanagement != null) {
//
//				// 流程紀錄用 - 開始
//				responseJson.put("Id", activitymanagement.getId());
//				responseJson.put("PostId", activitymanagement.getPostId());
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
//	/**
//	 * 更新ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            ActivityManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s32/update", method = RequestMethod.POST)
//	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long id = obj.getLong("Id");
//			if (!activityManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				ActivityManagement activityManagement = activityManagementService.update(getBaseMemberId(), json, false);
//
//				// 流程紀錄用 - 開始
//				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
//
//				if (isWriteProcessLog) {
//					processLogService.insert(getBaseMemberId(), json, String.valueOf(activityManagement.getId()));
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
//								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity1To3Subject"), activityManagement.getPostId());
//								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity1To3Body"), member.getName(), activityManagement.getPostId());
//								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
//							}
//						}
//					}
//				}
//				// 流程紀錄用 - 結束
//
//				if (activityManagement != null) {
//
//					// 流程紀錄用 - 開始
//					responseJson.put("Id", activityManagement.getId());
//					responseJson.put("PostId", activityManagement.getPostId());
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
//	 * 更新ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            ActivityManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s32/modify", method = RequestMethod.POST)
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
//			if (!activityManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				ActivityManagement activityManagement = activityManagementService.modify(getBaseMemberId(), json, false);
//				if (activityManagement != null) {
//					responseJson.put("Id", activityManagement.getId());
//					responseJson.put("PostId", activityManagement.getPostId());
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
//	 * 刪除ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 是否刪除成功
//	 */
//	@RequestMapping(value = "/s32/delete", method = RequestMethod.POST)
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
//			if (!activityManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				try {
//					List<ViewActivityManagementPicMember> activityManagementPics = activityManagementPicService.getAllByActivityManagementId(id);
//					if (activityManagementPics != null) {
//						for (ViewActivityManagementPicMember activityManagementPic : activityManagementPics) {
//							activityManagementPicService.delete(activityManagementPic.getId());
//						}
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//				}
//				try {
//					List<ViewActivityManagementAttachMember> activityManagementAttachs = activityManagementAttachService.getAllByActivityManagementId(id);
//					if (activityManagementAttachs != null) {
//						for (ViewActivityManagementAttachMember activityManagementAttach : activityManagementAttachs) {
//							activityManagementAttachService.delete(activityManagementAttach.getId());
//						}
//					}
//				} catch (Exception e) {
//					//e.printStackTrace();
//				}
//				if (activityManagementService.delete(id)) {
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
//	 * 停用ActivityManagement資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            ActivityManagement
//	 * @return 是否更新成功
//	 */
//	@RequestMapping(value = "/s32/disable", method = RequestMethod.POST)
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
//			if (!activityManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				activityManagementService.disable(getBaseMemberId(), id);
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
//	@RequestMapping(value = "/s32/pic/query", method = RequestMethod.POST)
//	public String QueryPic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONArray sn_array = new JSONArray();
//			
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
//			List<ViewActivityManagementPicMember> activityManagementPics = activityManagementPicService.getAllByActivityManagementId(activityManagementId);
//			if (activityManagementPics != null) {
//				for (ViewActivityManagementPicMember activityManagementPic : activityManagementPics) {
//					long size = activityManagementPic.getFileSize();
//					if (size <= 0)
//						return "0";
//					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
//					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//					String imageLink = "<img src=\"./api/p02/pic/download/" + activityManagementId + "/" + activityManagementPic.getId() + "\" title=\"" + activityManagementPic.getFileDesc() + "\" width=\"" + activityManagementPic.getImageWidth() + "\">";
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Id", activityManagementPic.getId());
//					sn_json.put("FileName", activityManagementPic.getFileName());
//					sn_json.put("FileType", activityManagementPic.getFileType());
//					sn_json.put("FileSize", fileSize);
//					sn_json.put("ImageWidth", activityManagementPic.getImageWidth());
//					sn_json.put("ImageHeight", activityManagementPic.getImageHeight());
//					sn_json.put("ImageLink", imageLink);
//					sn_json.put("FileDesc", activityManagementPic.getFileDesc());
//					sn_json.put("CreateId", activityManagementPic.getCreateId());
//					sn_json.put("CreateName", activityManagementPic.getCreateName());
//					sn_json.put("CreateTime", WebDatetime.toString(activityManagementPic.getCreateTime()));
//					sn_json.put("ModifyId", activityManagementPic.getModifyId());
//					sn_json.put("ModifyName", activityManagementPic.getModifyName());
//					sn_json.put("ModifyTime", WebDatetime.toString(activityManagementPic.getModifyTime()));
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
//	 *            ActivityManagementId
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
//	@RequestMapping(value = "/s32/pic/upload", method = RequestMethod.POST)
//	public String UploadPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject sn_json = new JSONObject();
//			sn_json.put("ActivityManagementId", id);
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
//					ActivityManagement activityManagement = activityManagementService.get(id);
//					if (activityManagement != null) {
//						ActivityManagementPic entity = activityManagementPicService.insert(getBaseMemberId(), json, bytes);
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
//			systemLogService.insert(baseControllerName, baseActionName, "ActivityManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
//	@RequestMapping(value = "/s32/pic/delete", method = RequestMethod.POST)
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
//			if (!activityManagementPicService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				if (activityManagementPicService.delete(id)) {
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
//	 * @param activityManagementId
//	 *            文章Id
//	 * @param id
//	 *            圖檔Id
//	 */
//	@RequestMapping(value = "/s32/pic/download/{activityManagementId}/{id}", method = RequestMethod.GET)
//	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
//		JSONObject sn_json = new JSONObject();
//		sn_json.put("ActivityManagementId", activityManagementId);
//		sn_json.put("Id", id);
//		String json = sn_json.toString();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (!activityManagementService.isExist(activityManagementId)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else if (!activityManagementPicService.isExist(id)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else {
//				response.reset();
//				ActivityManagementPic activityManagementPic = activityManagementPicService.getById(id);
//				try {
//					byte[] buffer = activityManagementPic.getFileContent();
//					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(activityManagementPic.getFileName(), StandardCharsets.UTF_8.toString()));
//					response.addHeader("Content-Length", "" + buffer.length);
//					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//					response.setContentType(activityManagementPic.getFileType());
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
//	@RequestMapping(value = "/s32/attach/query", method = RequestMethod.POST)
//	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONArray sn_array = new JSONArray();
//			
//			json = WebCrypto.getSafe(json);
//			JSONObject obj = new JSONObject(json);
//			long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
//			List<ViewActivityManagementAttachMember> activityManagementAttachs = activityManagementAttachService.getAllByActivityManagementId(activityManagementId);
//			if (activityManagementAttachs != null) {
//				for (ViewActivityManagementAttachMember activityManagementAttach : activityManagementAttachs) {
//					long size = activityManagementAttach.getFileSize();
//					if (size <= 0)
//						return "0";
//					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
//					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("ActivityManagementId", activityManagementAttach.getActivityManagementId());
//					sn_json.put("Id", activityManagementAttach.getId());
//					sn_json.put("FileName", activityManagementAttach.getFileName());
//					sn_json.put("FileType", activityManagementAttach.getFileType());
//					sn_json.put("FileSize", fileSize);
//					sn_json.put("FileHash", activityManagementAttach.getFileHash());
//					sn_json.put("FileDesc", activityManagementAttach.getFileDesc());
//					sn_json.put("CreateId", activityManagementAttach.getCreateId());
//					sn_json.put("CreateName", activityManagementAttach.getCreateName());
//					sn_json.put("CreateTime", WebDatetime.toString(activityManagementAttach.getCreateTime()));
//					sn_json.put("ModifyId", activityManagementAttach.getModifyId());
//					sn_json.put("ModifyName", activityManagementAttach.getModifyName());
//					sn_json.put("ModifyTime", WebDatetime.toString(activityManagementAttach.getModifyTime()));
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
//	 *            ActivityManagementId
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
//	@RequestMapping(value = "/s32/attach/upload", method = RequestMethod.POST)
//	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//		
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			JSONObject sn_json = new JSONObject();
//			sn_json.put("ActivityManagementId", id);
//			sn_json.put("FileDesc", fileDesc);
//			String json = sn_json.toString();
//
//			if (!file.isEmpty()) {
//				try {
//					byte[] bytes = file.getBytes();
//
//					sn_json.put("FileName", file.getOriginalFilename());
//					sn_json.put("FileType", file.getContentType());
//					sn_json.put("FileSize", file.getSize());
//					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
//					json = sn_json.toString();
//					ActivityManagement activityManagement = activityManagementService.get(id);
//					if (activityManagement != null) {
//						ActivityManagementAttach entity = activityManagementAttachService.insert(getBaseMemberId(), json, bytes);
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
//			systemLogService.insert(baseControllerName, baseActionName, "ActivityManagementId=" + id.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
//	@RequestMapping(value = "/s32/attach/delete", method = RequestMethod.POST)
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
//			if (!activityManagementAttachService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//				if (activityManagementAttachService.delete(id)) {
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
//	 * @param activityManagementId
//	 *            文章Id
//	 * @param id
//	 *            附件Id
//	 */
//	@SuppressWarnings("deprecation")
//	@RequestMapping(value = "/s32/attach/download/{activityManagementId}/{id}", method = RequestMethod.GET)
//	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
//		JSONObject sn_json = new JSONObject();
//		sn_json.put("ActivityManagementId", activityManagementId);
//		sn_json.put("Id", id);
//		String json = sn_json.toString();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (!activityManagementService.isExist(activityManagementId)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else if (!activityManagementAttachService.isExist(id)) {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//				try {
//					response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				} catch (IOException ex) {
//					//ex.printStackTrace();
//				}
//			} else {
//				response.reset();
//				ActivityManagementAttach activityManagementAttach = activityManagementAttachService.getById(id);
//				try {
//					byte[] buffer = activityManagementAttach.getFileContent();
//					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
//					// response.addHeader("Content-Disposition",
//					// "attachment;filename=" +
//					// URLEncoder.encode(activityManagementAttach.getFileName(),
//					// StandardCharsets.UTF_8.toString()));
//					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(activityManagementAttach.getFileName()));
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
//	 * 審核活動訊息資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param json
//	 *            編號
//	 * @return 審核動作是否成功
//	 */
//	@RequestMapping(value = "/s32/examine", method = RequestMethod.POST)
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
//
//			// 簽核用
//			// String preStatus = obj.isNull("PreStatus") == true ? null :
//			// obj.getString("PreStatus");
//			// String opinion = obj.isNull("Opinion") == true ? null :
//			// obj.getString("Opinion");
//
//			if (!activityManagementService.isExist(id)) {
//				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
//				responseJson.put("success", false);
//
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//			} else {
//
//				// String oldPostId =
//				// activityManagementService.getById(String.valueOf(id)).getPostId();
//
//				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, String.valueOf(id));
//				ActivityManagement activityManagement = activityManagementService.examine(getBaseMemberId(), String.valueOf(id), String.valueOf(status));
//
//				if (activityManagement != null && processLog != null) {
//					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//					responseJson.put("success", true);
//
//					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//
//					// 清除快取
//					activityManagementService.resetGlobalData();
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
//	 * 取得活動訊息button count資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件`
//	 * @return 活動訊息button count資料
//	 */
//	@RequestMapping(value = "/s32/query/button/count", method = RequestMethod.POST)
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
//			} else if (baseMemberRole.IsHisacContent == true) {
//				obj.put("RoleId", 3);
//			} else if (baseMemberRole.IsHisacContentSign == true) {
//				obj.put("RoleId", 4);
//			}
//
//			obj.put("MemberId", getBaseMemberId());
//			json = obj.toString();
//
//			JSONArray sn_array = new JSONArray();
//			List<SpButtonCount> spButtonCounts = activityManagementService.getSpButtonCount(json);
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
//}