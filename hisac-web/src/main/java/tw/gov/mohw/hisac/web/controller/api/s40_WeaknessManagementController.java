package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.WeaknessManagement;
import tw.gov.mohw.hisac.web.domain.WeaknessManagementAttach;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementMember;
import tw.gov.mohw.hisac.web.service.WeaknessManagementService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;
import tw.gov.mohw.hisac.web.service.WeaknessManagementAttachService;

/**
 * 軟體弱洞漏洞通告管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s40_WeaknessManagementController extends BaseController {

	@Autowired
	private WeaknessManagementService weaknessManagementService;
	@Autowired
	private WeaknessManagementAttachService weaknessManagementAttachService;

	@Autowired
	private ProcessLogService processLogService;

	@Autowired
	private MailService mailService;

	private String targetControllerName = "sys";
	private String targetActionName = "s40";

	/**
	 * 取得WeaknessManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return WeaknessManagement資料
	 */
	@RequestMapping(value = "/s40/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject listjson = new JSONObject();

		/*
		 * MemberRoleVariable.java 與「角色資料維護」 之設定 1-SuperAdmin：IsAdmin
		 * 2-H-ISAC管理者：IsHisac 3-H-ISAC內容維護者：IsHisacContent
		 * 4-H-ISAC內容審核者：IsHisacContentSign 5-H-ISAC警訊建立者：IsHisacInfoBuilder
		 * 6-H-ISAC警訊審核者：IsHisacInfoSign 7-權責單位警訊審核者：IsApplySign
		 * 8-權責單位聯絡人：IsApplyContact 9-權責單位管理者：IsApplyAdmin
		 * 10-會員機構聯絡人：IsMemberContact 11-會員機構管理者：IsMemberAdmin
		 * 12-HISAC通報審核者：IsHisacNotifySign 13-HISAC情資維護者：IsHisacIXContent
		 * 14-HISAC-情資審核者：IsHisacIXContentSign 15-權責單位通報審核者：IsApplySingAdmin
		 */
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);

		// 取得 RoleId
		if (baseMemberRole.IsAdmin == true) { // 1-SuperAdmin
			obj.put("RoleId", 1);
		} else if (baseMemberRole.IsHisac == true) { // 2-H-ISAC管理者
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacContent == true) { // 3-H-ISAC內容維護者
			obj.put("RoleId", 3);
		} else if (baseMemberRole.IsHisacContentSign == true) { // 4-H-ISAC內容審核者
			obj.put("RoleId", 4);
		}
		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			// listjson.put("total",
			// weaknessManagementService.getListSize(json));
			// List<ViewWeaknessManagementMember> weaknessManagements =
			// weaknessManagementService.getList(json);

			// 改用 store procedure
			List<ViewWeaknessManagementMember> weaknessManagements = weaknessManagementService.getSpList(json);
			listjson.put("total", weaknessManagementService.getSpListSize(json));

			if (weaknessManagements != null) {
				for (ViewWeaknessManagementMember weaknessManagement : weaknessManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", weaknessManagement.getId());
					sn_json.put("PostId", weaknessManagement.getPostId());
					sn_json.put("IncidentId", weaknessManagement.getIncidentId());
					sn_json.put("IncidentTitle", weaknessManagement.getIncidentTitle());
					sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(weaknessManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
					sn_json.put("IncidentReportedTime", WebDatetime.toString(weaknessManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Description", weaknessManagement.getDescription());
					sn_json.put("EventTypeCode", weaknessManagement.getEventTypeCode());
					sn_json.put("EventTypeName", weaknessManagement.getEventTypeName());
					sn_json.put("ReporterName", weaknessManagement.getReporterName());
					sn_json.put("ResponderPartyName", weaknessManagement.getResponderPartyName());
					sn_json.put("ResponderContactNumbers", weaknessManagement.getResponderContactNumbers());
					sn_json.put("ResponderElectronicAddressIdentifiers", weaknessManagement.getResponderElectronicAddressIdentifiers());
					sn_json.put("ImpactQualification", weaknessManagement.getImpactQualification());
					sn_json.put("CoaDescription", weaknessManagement.getCoaDescription());
					sn_json.put("Confidence", weaknessManagement.getConfidence());
					sn_json.put("Reference", weaknessManagement.getReference());
					sn_json.put("AffectedSoftwareDescription", weaknessManagement.getAffectedSoftwareDescription());
					sn_json.put("StartDateTime", WebDatetime.toString(weaknessManagement.getStartDateTime(), "yyyy-MM-dd"));
					sn_json.put("EndDateTime", WebDatetime.toString(weaknessManagement.getEndDateTime(), "yyyy-MM-dd"));
					sn_json.put("IsEnable", weaknessManagement.getIsEnable());
					sn_json.put("CreateId", weaknessManagement.getCreateId());
					sn_json.put("CreateName", weaknessManagement.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(weaknessManagement.getCreateTime()));
					sn_json.put("ModifyId", weaknessManagement.getModifyId());
					sn_json.put("ModifyName", weaknessManagement.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(weaknessManagement.getModifyTime()));
					sn_json.put("Status", weaknessManagement.getStatus());

					// 簽核流程用 - 開始
					String currentStatus = String.valueOf(weaknessManagement.getStatus());

					// 依狀態(目前階段)及角色顯示按鍵
					switch (currentStatus) {

						case "1" : // 1-編輯中
							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊

							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
								// 3-H-ISAC內容維護者：編輯、刪除
								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
								sn_json.put("IsButtonDelete", true); // 是否顯示刪除按鍵
							} else {
								// 其他角色：無權限
								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							}

							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "2" : // 2-撤銷中
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵

							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
								sn_json.put("IsButtonUndo", true); // 是否顯示撤銷按鍵
							} else {
								sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							}

							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "3" : // 3-審核中
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵

							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContentSign) {
								sn_json.put("IsButtonReview", true); // 是否顯示審核按鍵
							} else {
								sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							}
							break;

						case "4" : // 4-已公告
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "5" : // 5-已銷案
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "6" : // 6-編輯中(退回)
							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊

							if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac || baseMemberRole.IsHisacContent) {
								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
							} else {
								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							}

							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						default :
							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

					}
					// 簽核流程用 - 結束

					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得WeaknessManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            WeaknessManagement資料
	 * @return WeaknessManagement資料
	 */
	@RequestMapping(value = "/s40/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			// 流程紀錄用
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");

			// 取得指定 id 之軟體弱點漏洞通告資料
			WeaknessManagement weaknessManagement = weaknessManagementService.get(id);

			// 流程紀錄用 - 開始
			JSONArray messageLog_array = new JSONArray();
			Date today = new Date();
			List<ViewProcessLogMember> messageLogs = processLogService.getByPostId(String.valueOf(id), tableName);
			if (messageLogs != null) {
				for (ViewProcessLogMember messageLog : messageLogs) {
					JSONObject messageLog_json = new JSONObject();
					messageLog_json.put("PreStatus", messageLog.getPreStatus());
					messageLog_json.put("Status", messageLog.getStatus());
					messageLog_json.put("CreateTime", WebDatetime.toString(messageLog.getCreateTime()));
					messageLog_json.put("Opinion", messageLog.getOpinion());
					messageLog_json.put("CreateName", messageLog.getCreateName());
					messageLog_json.put("PreName", messageLog.getPreName());
					messageLog_json.put("Days", (today.getTime() - messageLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
					messageLog_array.put(messageLog_json);
				}
			}
			// 流程紀錄用 - 結束

			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", weaknessManagement.getId());
			sn_json.put("PostId", weaknessManagement.getPostId());
			sn_json.put("IncidentId", weaknessManagement.getIncidentId());
			sn_json.put("IncidentTitle", weaknessManagement.getIncidentTitle());
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(weaknessManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("IncidentReportedTime", WebDatetime.toString(weaknessManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
			sn_json.put("Description", weaknessManagement.getDescription());
			sn_json.put("EventTypeCode", weaknessManagement.getEventTypeCode());
			sn_json.put("ReporterName", weaknessManagement.getReporterName());
			sn_json.put("ResponderPartyName", weaknessManagement.getResponderPartyName());
			sn_json.put("ResponderContactNumbers", weaknessManagement.getResponderContactNumbers());
			sn_json.put("ResponderElectronicAddressIdentifiers", weaknessManagement.getResponderElectronicAddressIdentifiers());
			sn_json.put("ImpactQualification", weaknessManagement.getImpactQualification());
			sn_json.put("CoaDescription", weaknessManagement.getCoaDescription());
			sn_json.put("Confidence", weaknessManagement.getConfidence());
			sn_json.put("Reference", weaknessManagement.getReference());
			sn_json.put("AffectedSoftwareDescription", weaknessManagement.getAffectedSoftwareDescription());
			sn_json.put("StartDateTime", WebDatetime.toString(weaknessManagement.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(weaknessManagement.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", weaknessManagement.getIsEnable());
			sn_json.put("CreateId", weaknessManagement.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(weaknessManagement.getCreateTime()));
			sn_json.put("ModifyId", weaknessManagement.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(weaknessManagement.getModifyTime()));
			sn_json.put("Status", weaknessManagement.getStatus());

			// 流程紀錄用，將取得之流程紀錄放入 sn_json 中
			sn_json.put("MessageLog", messageLog_array);

			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增WeaknessManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            WeaknessManagement
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s40/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			WeaknessManagement weaknessmanagement = weaknessManagementService.insert(getBaseMemberId(), json, false);

			// 流程紀錄用 - 開始
			JSONObject obj = new JSONObject(json);
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (isWriteProcessLog) {
				// 寄信
				// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
				// 主旨：H-ISAC軟體弱洞漏洞通告("not.getPostId()")審核通知
				// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！軟體弱洞漏洞通告("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
																							// H-ISAC內容審核者
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member member = memberService.get(memberRole.getMemberId());
						if (member != null && member.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness1To3Subject"), weaknessmanagement.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness1To3Body"), member.getName(), weaknessmanagement.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}

				processLogService.insert(getBaseMemberId(), json, String.valueOf(weaknessmanagement.getId()));
			}
			// 流程紀錄用 - 結束

			if (weaknessmanagement != null) {

				// 流程紀錄用 - 開始
				responseJson.put("Id", weaknessmanagement.getId());
				responseJson.put("PostId", weaknessmanagement.getPostId());
				// 流程紀錄用 - 結束

				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 更新WeaknessManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            WeaknessManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s40/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!weaknessManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				WeaknessManagement weaknessManagement = weaknessManagementService.update(getBaseMemberId(), json, false);

				// 流程紀錄用 - 開始
				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

				if (isWriteProcessLog) {
					processLogService.insert(getBaseMemberId(), json, String.valueOf(weaknessManagement.getId()));

					// 寄信
					// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
					// 主旨：H-ISAC最新消息("not.getPostId()")審核通知
					// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！最新消息("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
																								// H-ISAC內容審核者
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness1To3Subject"), weaknessManagement.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness1To3Body"), member.getName(), weaknessManagement.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}
				// 流程紀錄用 - 結束

				if (weaknessManagement != null) {

					// 流程紀錄用 - 開始
					responseJson.put("Id", weaknessManagement.getId());
					responseJson.put("PostId", weaknessManagement.getPostId());
					// 流程紀錄用 - 結束

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
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 刪除WeaknessManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s40/delete", method = RequestMethod.POST)
	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!weaknessManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				// 同時刪除上傳附件資料
				try {
					List<ViewWeaknessManagementAttachMember> weaknessManagementAttachs = weaknessManagementAttachService.getAllByWeaknessManagementId(id);
					if (weaknessManagementAttachs != null) {
						for (ViewWeaknessManagementAttachMember weaknessManagementAttach : weaknessManagementAttachs) {
							weaknessManagementAttachService.delete(weaknessManagementAttach.getId());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if (weaknessManagementService.delete(id)) {
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
		return WebCrypto.getSafe(responseJson.toString());
	}

	// /**
	// * 取得EventType資料
	// *
	// * @param locale
	// * Locale
	// * @param request
	// * HttpServletRequest
	// * @param model
	// * Model
	// * @param json
	// * 查詢條件
	// * @return EventType資料
	// */
	// @RequestMapping(value = "/s40/query/eventType", method =
	// RequestMethod.POST)
	// public String QueryEventType(Locale locale, HttpServletRequest request,
	// Model model, @RequestBody String json) {
	// JSONArray sn_array = new JSONArray();
	// if (menuService.getReadPermission(getBaseMemberId(), targetControllerName,
	// targetActionName)) {
	// List<EventType> eventTypes = eventTypeService.getAnaList();
	// if (eventTypes != null) {
	// for (EventType eventType : eventTypes) {
	// JSONObject sn_json = new JSONObject();
	// sn_json.put("Id", eventType.getId());
	// sn_json.put("Code", eventType.getCode());
	// sn_json.put("Name", eventType.getName());
	// sn_array.put(sn_json);
	// }
	// systemLogService.insert(baseControllerName, baseActionName, json,
	// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
	// getBaseIpAddress(), getBaseMemberAccount());
	// }
	// } else {
	// systemLogService.insert(baseControllerName, baseActionName, json,
	// SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny,
	// getBaseIpAddress(), getBaseMemberAccount());
	// }
	// model.addAttribute("json", sn_array.toString());
	// return "msg";
	// }

	/**
	 * 取得附件資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 附件資料
	 */
	@RequestMapping(value = "/s40/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long weaknessManagementId = obj.isNull("WeaknessManagementId") == true ? 0 : obj.getLong("WeaknessManagementId");
			List<ViewWeaknessManagementAttachMember> weaknessManagementAttachs = weaknessManagementAttachService.getAllByWeaknessManagementId(weaknessManagementId);
			if (weaknessManagementAttachs != null) {
				for (ViewWeaknessManagementAttachMember weaknessManagementAttach : weaknessManagementAttachs) {
					long size = weaknessManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("WeaknessManagementId", weaknessManagementAttach.getWeaknessManagementId());
					sn_json.put("Id", weaknessManagementAttach.getId());
					sn_json.put("FileName", weaknessManagementAttach.getFileName());
					sn_json.put("FileType", weaknessManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", weaknessManagementAttach.getFileHash());
					sn_json.put("FileDesc", weaknessManagementAttach.getFileDesc());
					sn_json.put("CreateId", weaknessManagementAttach.getCreateId());
					sn_json.put("CreateName", weaknessManagementAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(weaknessManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", weaknessManagementAttach.getModifyId());
					sn_json.put("ModifyName", weaknessManagementAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(weaknessManagementAttach.getModifyTime()));
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 上傳附件API
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            WeaknessManagementId
	 * @param fileDesc
	 *            檔案描述
	 * @param model
	 *            Model
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @return 是否成功
	 */
	@RequestMapping(value = "/s40/attach/upload", method = RequestMethod.POST)
	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("WeaknessManagementId", id);
			sn_json.put("FileDesc", fileDesc);
			String json = sn_json.toString();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename());
					sn_json.put("FileType", file.getContentType());
					sn_json.put("FileSize", file.getSize());
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
					json = sn_json.toString();
					WeaknessManagement weaknessManagement = weaknessManagementService.get(id);
					if (weaknessManagement != null) {
						WeaknessManagementAttach entity = weaknessManagementAttachService.insert(getBaseMemberId(), json, bytes);
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (Exception e) {
					//e.printStackTrace();
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "WeaknessManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 刪除附件API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s40/attach/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!weaknessManagementAttachService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (weaknessManagementAttachService.delete(id)) {
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
		return WebCrypto.getSafe(responseJson.toString());

	}

	/**
	 * 附件下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param weaknessManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/s40/attach/download/{weaknessManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long weaknessManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("WeaknessManagementId", weaknessManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!weaknessManagementService.isExist(weaknessManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!weaknessManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				WeaknessManagementAttach weaknessManagementAttach = weaknessManagementAttachService.getById(id);
				try {
					byte[] buffer = weaknessManagementAttach.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(weaknessManagementAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(weaknessManagementAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}

	/**
	 * 審核軟體弱洞漏洞通告資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/s40/examine", method = RequestMethod.POST)
	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");

			// 簽核用
			// String preStatus = obj.isNull("PreStatus") == true ? null :
			// obj.getString("PreStatus");
			// String opinion = obj.isNull("Opinion") == true ? null :
			// obj.getString("Opinion");

			if (!weaknessManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {

				// String oldPostId =
				// weaknessManagementService.getById(String.valueOf(id)).getPostId();

				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, String.valueOf(id));
				WeaknessManagement weaknessManagement = weaknessManagementService.examine(getBaseMemberId(), String.valueOf(id), String.valueOf(status));

				if (weaknessManagement != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

					// 清除快取
					weaknessManagementService.resetGlobalData();
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
			// } else {
			// json.put("msg", "PermissionDenied");
			// json.put("success", false);
			// }
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 取得軟體弱點漏洞通告button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 軟體弱點漏洞通告button count資料
	 */
	@RequestMapping(value = "/s40/query/button/count", method = RequestMethod.POST)
	public String QueryButtonCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			/*
			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
			 * 10-會員機構聯絡人 11-會員機構管理者
			 */
			if (baseMemberRole.IsAdmin == true) {
				obj.put("RoleId", 1);
			} else if (baseMemberRole.IsHisac == true) {
				obj.put("RoleId", 2);
			} else if (baseMemberRole.IsHisacContent == true) {
				obj.put("RoleId", 3);
			} else if (baseMemberRole.IsHisacContentSign == true) {
				obj.put("RoleId", 4);
			}

			obj.put("MemberId", getBaseMemberId());
			json = obj.toString();

			JSONArray sn_array = new JSONArray();
			List<SpButtonCount> spButtonCounts = weaknessManagementService.getSpButtonCount(json);
			if (spButtonCounts != null)
				for (SpButtonCount spButtonCount : spButtonCounts) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Status", spButtonCount.getStatus());
					sn_json.put("Count", spButtonCount.getCount());
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

}