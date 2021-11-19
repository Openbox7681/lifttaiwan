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
import tw.gov.mohw.hisac.web.dao.IncidentDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Incident;
import tw.gov.mohw.hisac.web.domain.IncidentAttach;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewIncidentAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewIncidentMember;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.service.IncidentService;
import tw.gov.mohw.hisac.web.service.IncidentAttachService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.MemberService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 事件管理控制器
 */
@Controller
@RequestMapping(value = "/inc/api", produces = "application/json; charset=utf-8")
public class i01_IncidentController extends BaseController {

	@Autowired
	IncidentDAO incidentDAO;
	@Autowired
	private IncidentService incidentService;
	@Autowired
	private IncidentAttachService incidentAttachService;
	
	@Autowired
	private OrgService orgService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ProcessLogService processLogService;
	
	private String targetControllerName = "inc";
	private String targetActionName = "i01";

	/**
	 * 取得Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Incident資料
	 */
	@RequestMapping(value = "/i01/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject listjson = new JSONObject();

		/*
		 * MemberRoleVariable.java 與「角色資料維護」 之設定 
		 * 1-SuperAdmin：IsAdmin
		 * 2.H-ISAC管理者：IsHisac
         * 10.會員機構聯絡人：IsMemberContact
         * 15.權責單位通報審核者：IsApplySingAdmin
         * 17.H-CERT審核者：IsHCERTContentSign
		 * 18.事件處理單位聯絡人：IsEventHandlingUnitContact
         */

		JSONObject obj = new JSONObject(json);

		// debug
//		System.out.println("==========================================================");
//		System.out.println("i01_IncidentController.java → Query() → json：" + json.toString());
//		System.out.println("i01_IncidentController.java → Query() → baseMemberAccount：" + baseMemberAccount);
//		System.out.println("i01_IncidentController.java → Query() → baseMemberName：" + baseMemberName);
//		System.out.println("i01_IncidentController.java → Query() → baseMemberId：" + baseMemberId);
//		System.out.println("i01_IncidentController.java → Query() → baseOrgType：" + baseOrgType);
//		System.out.println("i01_IncidentController.java → Query() → baseAuthType：" + baseAuthType);
//		System.out.println("i01_IncidentController.java → Query() → baseOrgId：" + baseOrgId);

		// 取得 RoleId
		if (baseMemberRole.IsAdmin == true) { // 1-SuperAdmin
			obj.put("RoleId", 1);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：1");
		} else if (baseMemberRole.IsHisac == true) { // 2.H-ISAC管理者
			obj.put("RoleId", 2);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：2");
		} else if (baseMemberRole.IsMemberContact == true) { // 10.會員機構聯絡人
			obj.put("RoleId", 10);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：10");
		} else if (baseMemberRole.IsApplySingAdmin == true) { // 15.權責單位通報審核者
			obj.put("RoleId", 15);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：15");
		} else if (baseMemberRole.IsHCERTContentSign == true) { // 17.H-CERT審核者
			obj.put("RoleId", 17);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：17");
		} else if (baseMemberRole.IsEventHandlingUnitContact == true) { // 18.事件處理單位聯絡人
			obj.put("RoleId", 18);

			// debug
//			System.out.println("i01_IncidentController.java → Query() → RoleId：18");
		}
		obj.put("MemberId", getBaseMemberId());
		
//		System.out.println("i01_IncidentController.java → Query() → 比對baseOrgType.equals(new String(\"3\"))：" + baseOrgType.equals(new String("3")));
//		System.out.println("i01_IncidentController.java → Query() → 比對baseOrgType == \"3\"：" + (baseOrgType == "3"));
//		System.out.println("i01_IncidentController.java → Query() → 比對baseOrgType.equals(\"3\")：" + (baseOrgType.equals("3")));

		if (getBaseOrgType().equals("2")) {
			obj.put("ParentOrgId", getBaseOrgId());

			// debug
//			System.out.println("i01_IncidentController.java → Query() → ParentOrgId：" + baseOrgId);
		} else if (getBaseOrgType().equals("3")) {
			obj.put("ReporterName", getBaseOrgId());

			// debug
//			System.out.println("i01_IncidentController.java → Query() → ReporterName：" + baseOrgId);
		} else if (getBaseOrgType().equals("4")) {
			obj.put("HandleName", getBaseOrgId());

			// debug
//			System.out.println("i01_IncidentController.java → Query() → HandleName：" + baseOrgId);
		}
		
		json = obj.toString();

		// debug
//		System.out.println("i01_IncidentController.java → json：" + json);

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			// listjson.put("total",
			// incidentService.getListSize(json));
			// List<ViewIncidentMember> incidents =
			// incidentService.getList(json);

			// 改用 store procedure
			List<ViewIncidentMember> incidents = incidentService.getSpList(json);
			listjson.put("total", incidentService.getSpListSize(json));

			if (incidents != null) {
				for (ViewIncidentMember incident : incidents) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", incident.getId());
					sn_json.put("PostDateTime", WebDatetime.toString(incident.getPostDateTime(), "yyyy-MM-dd"));
					sn_json.put("PostId", incident.getPostId());
					sn_json.put("ReporterName", incident.getReporterName());
					sn_json.put("ReporterIdName", incident.getReporterIdName());
					sn_json.put("HandleName", incident.getHandleName());
					sn_json.put("HandleIdName", incident.getHandleIdName());
					sn_json.put("ContactorId", incident.getContactorId());
					sn_json.put("ContactorIdName", incident.getContactorIdName());
					sn_json.put("ContactorTel", incident.getContactorTel());
					sn_json.put("ContactorFax", incident.getContactorFax());
					sn_json.put("ContactorEmail", incident.getContactorEmail());
					sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(incident.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
					sn_json.put("HostAmount", incident.getHostAmount());
					sn_json.put("ServerAmount", incident.getServerAmount());
					sn_json.put("InformationAmount", incident.getInformationAmount());
					sn_json.put("OtherDeviceAmount", incident.getOtherDeviceAmount());
					sn_json.put("OtherDeviceName", incident.getOtherDeviceName());
					sn_json.put("DeviceRemark", incident.getDeviceRemark());
					sn_json.put("AssessDamage", incident.getAssessDamage());
					sn_json.put("AssessDamageRemark", incident.getAssessDamageRemark());
					sn_json.put("IsOsOpt1", incident.getIsOsOpt1());
					sn_json.put("IsOsOpt2", incident.getIsOsOpt2());
					sn_json.put("IsOsOpt3", incident.getIsOsOpt3());
					sn_json.put("IsOsOpt3Other", incident.getIsOsOpt3Other());
					sn_json.put("EventType", incident.getEventType());
					sn_json.put("IsEventType1Opt1", incident.getIsEventType1Opt1());
					sn_json.put("IsEventType1Opt2", incident.getIsEventType1Opt2());
					sn_json.put("IsEventType1Opt3", incident.getIsEventType1Opt3());
					sn_json.put("IsEventType1Opt4", incident.getIsEventType1Opt4());
					sn_json.put("IsEventType1Opt5", incident.getIsEventType1Opt5());
					sn_json.put("IsEventType1Opt6", incident.getIsEventType1Opt6());
					sn_json.put("IsEventType2Opt1", incident.getIsEventType2Opt1());
					sn_json.put("IsEventType2Opt2", incident.getIsEventType2Opt2());
					sn_json.put("IsEventType2Opt3", incident.getIsEventType2Opt3());
					sn_json.put("IsEventType2Opt4", incident.getIsEventType2Opt4());
					sn_json.put("IsEventType2Opt5", incident.getIsEventType2Opt5());
					sn_json.put("IsEventType3Opt1", incident.getIsEventType3Opt1());
					sn_json.put("IsEventType3Opt2", incident.getIsEventType3Opt2());
					sn_json.put("IsEventType4Opt1", incident.getIsEventType4Opt1());
					sn_json.put("IsEventType4Opt2", incident.getIsEventType4Opt2());
					sn_json.put("IsEventType4Opt3", incident.getIsEventType4Opt3());
					sn_json.put("IsEventType4Opt4", incident.getIsEventType4Opt4());
					sn_json.put("EventType5Other", incident.getEventType5Other());
					sn_json.put("EventRemark", incident.getEventRemark());
					sn_json.put("IsEventCause1Opt1", incident.getIsEventCause1Opt1());
					sn_json.put("IsEventCause1Opt2", incident.getIsEventCause1Opt2());
					sn_json.put("IsEventCause1Opt3", incident.getIsEventCause1Opt3());
					sn_json.put("IsEventCause1Opt4", incident.getIsEventCause1Opt4());
					sn_json.put("IsEventCause1Opt5", incident.getIsEventCause1Opt5());
					sn_json.put("IsEventCause1Opt6", incident.getIsEventCause1Opt6());
					sn_json.put("IsEventCause1Opt6Other", incident.getIsEventCause1Opt6Other());
					sn_json.put("EventEvaluation", incident.getEventEvaluation());
					sn_json.put("EventProcess", incident.getEventProcess());
					sn_json.put("IsSecuritySetting1Opt1", incident.getIsSecuritySetting1Opt1());
					sn_json.put("IsSecuritySetting1Opt2", incident.getIsSecuritySetting1Opt2());
					sn_json.put("IsSecuritySetting1Opt3", incident.getIsSecuritySetting1Opt3());
					sn_json.put("IsSecuritySetting1Opt4", incident.getIsSecuritySetting1Opt4());
					sn_json.put("IsSecuritySetting1Opt5", incident.getIsSecuritySetting1Opt5());
					sn_json.put("IsSecuritySetting1Opt6", incident.getIsSecuritySetting1Opt6());
					sn_json.put("IsSecuritySetting1Opt7", incident.getIsSecuritySetting1Opt7());
					sn_json.put("IsSecuritySetting1Opt8", incident.getIsSecuritySetting1Opt8());
					sn_json.put("IsSecuritySetting1Opt9", incident.getIsSecuritySetting1Opt9());
					sn_json.put("IsSecuritySetting1Opt10", incident.getIsSecuritySetting1Opt10());
					sn_json.put("IsSecuritySetting1Opt11", incident.getIsSecuritySetting1Opt11());
					sn_json.put("IsSecuritySetting1Opt12", incident.getIsSecuritySetting1Opt12());
					sn_json.put("IsSecuritySetting1Opt13", incident.getIsSecuritySetting1Opt13());
					sn_json.put("FinishDoOther", incident.getFinishDoOther());
					sn_json.put("FinishDateTime", WebDatetime.toString(incident.getFinishDateTime(), "yyyy-MM-dd"));
					sn_json.put("CreateId", incident.getCreateId());
					sn_json.put("CreateName", incident.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(incident.getCreateTime()));
					sn_json.put("ModifyId", incident.getModifyId());
					sn_json.put("ModifyName", incident.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(incident.getModifyTime()));
					sn_json.put("Status", incident.getStatus());
					sn_json.put("SubStatus", incident.getSubStatus());
					
					// 提供前端判斷簽核權限用
					sn_json.put("SignLoginMemberId", getBaseMemberId());
					sn_json.put("SignLoginMemberOrgId", getBaseOrgId());

					// 簽核流程用 - 開始
					String currentStatus = String.valueOf(incident.getStatus());
					String currentSubStatus = String.valueOf(incident.getSubStatus());

					// 取得 [會員機構] 欄位對應之權責單位資料
					List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(incident.getReporterName());
					Long parentOrgId = null;
					
					if (orgs != null) {
						for (ViewOrgOrgSign org : orgs) {
							parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)

							// 提供前端判斷簽核權限用
							sn_json.put("SignReportParentOrgId", parentOrgId);

							break;
						}
					}  

					// debug
//					System.out.println("i01_IncidentController.java → currentStatus：" + currentStatus);
//					System.out.println("i01_IncidentController.java → currentSubStatus：" + currentSubStatus);

					// 依狀態(目前階段)及角色顯示按鍵
					switch (currentStatus) {

						case "1" : // 1-編輯中　←　2.H-ISAC管理者：IsHisac｜17.HCERT審核者：IsHCERTContentSign
							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊

							if (baseMemberRole.IsAdmin) {
								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
								sn_json.put("IsButtonDelete", true); // 是否顯示刪除按鍵								
							} else if (baseMemberRole.IsHisac || baseMemberRole.IsHCERTContentSign) {

								// 2.H-ISAC管理者｜17.HCERT審核者：編輯、刪除
								//if (incident.getCreateId().equals(baseMemberId)) {
									sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
									sn_json.put("IsButtonDelete", true); // 是否顯示刪除按鍵									
								//} else {
								//	sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								//	sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
								//}
							} else {
								// 其他角色：無權限
								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							}

							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "2" : // 2-事件處理中　←　18.事件處理單位聯絡人：IsEventHandlingUnitContact
							sn_json.put("IsSeeLog", false); // 是否顯示簽核資訊

							if (baseMemberRole.IsAdmin || baseMemberRole.IsEventHandlingUnitContact) {

								// 18.事件處理單位聯絡人：編輯
								// 要指定的事件處理單位聯絡人帳號才有編輯權限
								if (incident.getContactorId().equals(getBaseMemberId())) {
									sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
								} else {
									sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								}
								sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							} else {
								// 其他角色：無權限
								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							}

							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "3" : // 3-事件處理審核中 ← 10.會員機構聯絡人：IsMemberContact｜17.H-CERT審核者：IsHCERTContentSign
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							
							Boolean isButtonReview = false; // 是否顯示審核按鍵

							if (baseMemberRole.IsAdmin) {
								isButtonReview = true; // 是否顯示審核按鍵
							} else if (baseMemberRole.IsMemberContact || baseMemberRole.IsHCERTContentSign) {

								// 10.會員機構聯絡人：審核
								if (currentSubStatus.equals("31") && baseMemberRole.IsMemberContact) {
									
									List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(10);
									
									if (memberRoles != null) {
										for (ViewMemberRoleMember memberRole : memberRoles) {
											
											Member member = memberService.get(memberRole.getMemberId());

											// debug
//											System.out.println("i01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第一次審核)是否顯示審核按鍵 to 10.會員機構聯絡人：IsMemberContact - 【Id == baseMemberId】=" + (member.getId() == baseMemberId));
//											System.out.println("i01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第一次審核)是否顯示審核按鍵 to 10.會員機構聯絡人：IsMemberContact - 【member.getOrgId() == reporterName】=" + (member.getOrgId() == incident.getReporterName()));
											
											// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
											if (member != null && member.getIsEnable() && member.getOrgId().equals(incident.getReporterName()) && member.getId().equals(getBaseMemberId())) {
												
												isButtonReview = true; // 是否顯示審核按鍵

												// debug
//												System.out.println("i01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第一次審核)是否顯示審核按鍵 to 10.會員機構聯絡人：IsMemberContact - MemberId=" + memberRole.getMemberId());
//												System.out.println("i01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第一次審核)是否顯示審核按鍵 to 10.會員機構聯絡人：IsMemberContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

												// 符合一次，處理完成即跳出迴圈
												break;
											}
										}
									}
									
								// 17.H-CERT審核者：審核
								} else if (currentSubStatus.equals("32") && baseMemberRole.IsHCERTContentSign) {
									
									List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(17);
									
									if (memberRoles != null) {
										for (ViewMemberRoleMember memberRole : memberRoles) {
											
											Member member = memberService.get(memberRole.getMemberId());
											
											// 祇寄給CERT事件處理審核者
											// 26 為 member.OrgId
											//if (member != null && member.getIsEnable() && member.getOrgId().equals(new Long(26)) && member.getId().equals(baseMemberId)) {
											if (member != null && member.getIsEnable()) {
												
												isButtonReview = true; // 是否顯示審核按鍵

												// debug
//												System.out.println("01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第二次審核)是否顯示審核按鍵 to 17.H-CERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
//												System.out.println("01_IncidentController.java → Query() → 【3-事件處理審核中 → 5-已結案】(第二次審核)是否顯示審核按鍵 to 17.H-CERT審核者：IsHCERTContentSign - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

												// 符合一次，處理完成即跳出迴圈
												break;
											}
										}
									}
																		
								}
								
								if (isButtonReview) {
									sn_json.put("IsButtonReview", true); // 是否顯示審核按鍵
								} else {
									sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
								}
								
							} else {
								sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							}
							break;

						case "4" : // 4-事件處理中(退回) ← 18.事件處理單位聯絡人：IsEventHandlingUnitContact
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊

							if (baseMemberRole.IsAdmin) {
								sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
							} else if (baseMemberRole.IsEventHandlingUnitContact) {
								// 18.事件處理單位聯絡人：編輯
								if (incident.getContactorId().equals(getBaseMemberId())) {
									sn_json.put("IsButtonEdit", true); // 是否顯示編輯按鍵
								} else {
									sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
								}
								
							} else {
								// 其他角色：無權限
								sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							}

							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "5" : // 4-已結案
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
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
	 * 取得Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Incident資料
	 * @return Incident資料
	 */
	@RequestMapping(value = "/i01/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			// 流程紀錄用
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");

			// 取得指定 id 之事件資料資料
			Incident incident = incidentService.get(id);

			// 取得指定 ReporterName 之發佈單位資料
			Org getOrgForReporterName = null;
			
			if (incident.getReporterName() != null && incident.getReporterName() > 0) {
				
				getOrgForReporterName = orgService.getDataById(incident.getReporterName());
				
			}
			
			// 取得指定 HandleName 之協助處理單位資料
			Org getOrgForHandleName = null;
			
			if (incident.getHandleName() != null && incident.getHandleName() > 0) {
				
				getOrgForHandleName = orgService.getDataById(incident.getHandleName());
				
			}

			// 取得指定 ContactorId 之聯絡人姓名資料
			Member memberData = null;
			
			if (incident.getContactorId() != null && incident.getContactorId() > 0) {
				
				memberData = memberService.get(incident.getContactorId());
				
			}
			

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
			sn_json.put("Id", incident.getId());
			sn_json.put("PostDateTime", WebDatetime.toString(incident.getPostDateTime(), "yyyy-MM-dd"));
			sn_json.put("PostId", incident.getPostId());
			sn_json.put("ReporterName", incident.getReporterName());
			if (incident.getReporterName() != null && incident.getReporterName() > 0 && getOrgForReporterName != null) {
				sn_json.put("ReporterIdName", getOrgForReporterName.getName());
			} else {
				sn_json.put("ReporterIdName", "");
			}
			sn_json.put("HandleName", incident.getHandleName());
			if (incident.getHandleName() != null && incident.getHandleName() > 0 && getOrgForHandleName != null) {
				sn_json.put("HandleIdName", getOrgForHandleName.getName());
			} else {
				sn_json.put("HandleIdName", "");
			}
			sn_json.put("ContactorId", incident.getContactorId());
			if (incident.getContactorId() != null && incident.getContactorId() > 0 && memberData != null) {
				sn_json.put("ContactorIdName", memberData.getName());
				sn_json.put("ContactorTel", memberData.getCityPhone());
				sn_json.put("ContactorFax", memberData.getFaxPhone());
				sn_json.put("ContactorEmail", memberData.getEmail());
			} else {
				sn_json.put("ContactorIdName", "");
				sn_json.put("ContactorTel", "");
				sn_json.put("ContactorFax", "");
				sn_json.put("ContactorEmail", "");
			}
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(incident.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("HostAmount", incident.getHostAmount());
			sn_json.put("ServerAmount", incident.getServerAmount());
			sn_json.put("InformationAmount", incident.getInformationAmount());
			sn_json.put("OtherDeviceAmount", incident.getOtherDeviceAmount());
			sn_json.put("OtherDeviceName", incident.getOtherDeviceName());
			sn_json.put("DeviceRemark", incident.getDeviceRemark());
			sn_json.put("AssessDamage", incident.getAssessDamage());
			sn_json.put("AssessDamageRemark", incident.getAssessDamageRemark());
			sn_json.put("IsOsOpt1", incident.getIsOsOpt1());
			sn_json.put("IsOsOpt2", incident.getIsOsOpt2());
			sn_json.put("IsOsOpt3", incident.getIsOsOpt3());
			sn_json.put("IsOsOpt3Other", incident.getIsOsOpt3Other());
			sn_json.put("EventType", incident.getEventType());
			sn_json.put("IsEventType1Opt1", incident.getIsEventType1Opt1());
			sn_json.put("IsEventType1Opt2", incident.getIsEventType1Opt2());
			sn_json.put("IsEventType1Opt3", incident.getIsEventType1Opt3());
			sn_json.put("IsEventType1Opt4", incident.getIsEventType1Opt4());
			sn_json.put("IsEventType1Opt5", incident.getIsEventType1Opt5());
			sn_json.put("IsEventType1Opt6", incident.getIsEventType1Opt6());
			sn_json.put("IsEventType2Opt1", incident.getIsEventType2Opt1());
			sn_json.put("IsEventType2Opt2", incident.getIsEventType2Opt2());
			sn_json.put("IsEventType2Opt3", incident.getIsEventType2Opt3());
			sn_json.put("IsEventType2Opt4", incident.getIsEventType2Opt4());
			sn_json.put("IsEventType2Opt5", incident.getIsEventType2Opt5());
			sn_json.put("IsEventType3Opt1", incident.getIsEventType3Opt1());
			sn_json.put("IsEventType3Opt2", incident.getIsEventType3Opt2());
			sn_json.put("IsEventType4Opt1", incident.getIsEventType4Opt1());
			sn_json.put("IsEventType4Opt2", incident.getIsEventType4Opt2());
			sn_json.put("IsEventType4Opt3", incident.getIsEventType4Opt3());
			sn_json.put("IsEventType4Opt4", incident.getIsEventType4Opt4());
			sn_json.put("EventType5Other", incident.getEventType5Other());
			sn_json.put("EventRemark", incident.getEventRemark());
			sn_json.put("IsEventCause1Opt1", incident.getIsEventCause1Opt1());
			sn_json.put("IsEventCause1Opt2", incident.getIsEventCause1Opt2());
			sn_json.put("IsEventCause1Opt3", incident.getIsEventCause1Opt3());
			sn_json.put("IsEventCause1Opt4", incident.getIsEventCause1Opt4());
			sn_json.put("IsEventCause1Opt5", incident.getIsEventCause1Opt5());
			sn_json.put("IsEventCause1Opt6", incident.getIsEventCause1Opt6());
			sn_json.put("IsEventCause1Opt6Other", incident.getIsEventCause1Opt6Other());
			sn_json.put("EventEvaluation", incident.getEventEvaluation());
			sn_json.put("EventProcess", incident.getEventProcess());
			sn_json.put("IsSecuritySetting1Opt1", incident.getIsSecuritySetting1Opt1());
			sn_json.put("IsSecuritySetting1Opt2", incident.getIsSecuritySetting1Opt2());
			sn_json.put("IsSecuritySetting1Opt3", incident.getIsSecuritySetting1Opt3());
			sn_json.put("IsSecuritySetting1Opt4", incident.getIsSecuritySetting1Opt4());
			sn_json.put("IsSecuritySetting1Opt5", incident.getIsSecuritySetting1Opt5());
			sn_json.put("IsSecuritySetting1Opt6", incident.getIsSecuritySetting1Opt6());
			sn_json.put("IsSecuritySetting1Opt7", incident.getIsSecuritySetting1Opt7());
			sn_json.put("IsSecuritySetting1Opt8", incident.getIsSecuritySetting1Opt8());
			sn_json.put("IsSecuritySetting1Opt9", incident.getIsSecuritySetting1Opt9());
			sn_json.put("IsSecuritySetting1Opt10", incident.getIsSecuritySetting1Opt10());
			sn_json.put("IsSecuritySetting1Opt11", incident.getIsSecuritySetting1Opt11());
			sn_json.put("IsSecuritySetting1Opt12", incident.getIsSecuritySetting1Opt12());
			sn_json.put("IsSecuritySetting1Opt13", incident.getIsSecuritySetting1Opt13());
			sn_json.put("FinishDoOther", incident.getFinishDoOther());
			sn_json.put("FinishDateTime", WebDatetime.toString(incident.getFinishDateTime(), "yyyy-MM-dd"));
			sn_json.put("CreateId", incident.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(incident.getCreateTime()));
			sn_json.put("ModifyId", incident.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(incident.getModifyTime()));
			sn_json.put("Status", incident.getStatus());
			sn_json.put("SubStatus", incident.getSubStatus());
			
			sn_json.put("SatisfactionTime", incident.getSatisfactionTime());
			sn_json.put("SatisfactionProfessionalism", incident.getSatisfactionProfessionalism());
			sn_json.put("SatisfactionService", incident.getSatisfactionService());
			sn_json.put("SatisfactionReport", incident.getSatisfactionReport());
			sn_json.put("SatisfactionTotal", incident.getSatisfactionTotal());
			sn_json.put("SatisfactionRemark", incident.getSatisfactionRemark());

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
	 * 新增Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Incident
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/i01/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
			// 流程紀錄用 - 開始
			JSONObject obj = new JSONObject(json);
			Boolean check = obj.getBoolean("Check");
			Boolean isWriteProcessLog = obj.getBoolean("IsWriteProcessLog");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
			Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
			Long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");

			// 依 check 判斷是否要更新事件單編號(將暫存單號改成正式單號)
			Incident incident = incidentService.insert(getBaseMemberId(), json, check);
			
			// 取得 RoleId
			int roleId;
			
			if (baseMemberRole.IsAdmin) { // 1-SuperAdmin
				roleId = 1;
			} else if (baseMemberRole.IsHisac) { // 2.H-ISAC管理者
				roleId = 2;
			} else if (baseMemberRole.IsMemberContact) { // 10.會員機構聯絡人
				roleId = 10;
			} else if (baseMemberRole.IsApplySingAdmin) { // 15.權責單位通報審核者
				roleId = 15;
			} else if (baseMemberRole.IsHCERTContentSign) { // 17.H-CERT審核者
				roleId = 17;
			} else if (baseMemberRole.IsEventHandlingUnitContact) { // 18.事件處理單位聯絡人
				roleId = 18;
			} else {
				roleId = 0;
			}

			// debug
//			System.out.println("i01_IncidentController.java → Create() → baseMemberAccount：" + baseMemberAccount);
//			System.out.println("i01_IncidentController.java → Create() → baseMemberName：" + baseMemberName);
//			System.out.println("i01_IncidentController.java → Create() → baseMemberId：" + baseMemberId);
//			System.out.println("i01_IncidentController.java → Create() → baseOrgType：" + baseOrgType);
//			System.out.println("i01_IncidentController.java → Create() → baseAuthType：" + baseAuthType);
//			System.out.println("i01_IncidentController.java → Create() → baseOrgId：" + baseOrgId);
//			System.out.println("i01_IncidentController.java → Create() → roleId：" + roleId);
//			System.out.println("i01_IncidentController.java → Create() → check：" + check);
//			System.out.println("i01_IncidentController.java → Create() → isWriteProcessLog：" + isWriteProcessLog);
//			System.out.println("i01_IncidentController.java → Create() → status：" + status);
//			System.out.println("i01_IncidentController.java → Create() → PostId：" + incident.getPostId());
//			System.out.println("i01_IncidentController.java → Create() → ReporterName(會員機構)：" + reporterName);
//			System.out.println("i01_IncidentController.java → Create() → HandleName(事件處理單位)：" + handleName);
//			System.out.println("i01_IncidentController.java → Create() → ContactorId(事件處理單位聯絡人)：" + contactorId);
			

			// 處理如下階段：
			//     1-編輯中 → 2-事件處理中
			if (isWriteProcessLog) {

				// 1-編輯中 → 2-事件處理中
				if (status.equals(new Long(2))) {

					// from 1-SuperAdmin：IsAdmin
					// from 2.H-ISAC管理者：IsHisac
					// from 15.權責單位通報審核者：IsApplySingAdmin
					if (roleId == 1 || roleId == 2 || roleId == 17) {
						
						// 寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact
						// 收件者：memberService.get((memberRoleService.findByRoleId(18)).getMemberId()).getEmail()
						// 主旨：CERT事件處理單("not.getPostId()")通知
						// 內容：memberService.get((memberRoleService.findByRoleId(18)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正等待您的處理，請您儘快撥冗進行，謝謝！
						List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(18);
						
						if (memberRoles != null) {
							for (ViewMemberRoleMember memberRole : memberRoles) {
								
								Member member = memberService.get(memberRole.getMemberId());
								
								// debug
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - Id=" + member.getId());
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - 【member.getId() == contactorId】=" + (member.getId() == contactorId));
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - 【member.getId().equals(contactorId)】=" + (member.getId().equals(contactorId)));
								
								// 祇寄給紀錄中已在[事件處理單位]欄位指定之連絡人
								if (member != null && member.getIsEnable() && member.getId().equals(contactorId)) {
									
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - 【resourceMessageService.getMessageValue(\"mailIncident1To2SubjectToIsEventHandlingUnitContact\")】=" + resourceMessageService.getMessageValue("mailIncident1To2SubjectToIsEventHandlingUnitContact"));
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - 【incident.getPostId()】=" + incident.getPostId());
									
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectToIsEventHandlingUnitContact"), incident.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyToIsEventHandlingUnitContact"), member.getName(), incident.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
									
									// debug
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - MemberId=" + memberRole.getMemberId());
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());
									
									// 符合一次，處理完成即跳出迴圈
									break;
								}
							}
						}
						
						// 1.SYSTEM系統管理帳號 或 2.ISAC管理者

						
						// debug
//						System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - 【roleId == 17】=" + (roleId == 17));
						
						// 寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign
						// 收件者：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getEmail()
						// 主旨：CERT事件處理單("not.getPostId()")處理副知
						// 內容：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
						List<ViewMemberRoleMember> memberRoles1 = memberRoleService.findByRoleId(17);
							
						if (memberRoles1 != null) {

							// 取得 [會員機構] 欄位對應之權責單位資料
							List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(reporterName);
							Long parentOrgId = null;
							
							if (orgs != null) {
								for (ViewOrgOrgSign org : orgs) {
									parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)
									break;
								}
							}  
							
							for (ViewMemberRoleMember memberRole : memberRoles1) {
								
								Member member = memberService.get(memberRole.getMemberId());

								// debug
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - Id=" + member.getId());
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - 【member.getOrgId() == parentOrgId】=" + (member.getOrgId() == parentOrgId));
								
								// 祇寄給權責單位管理者
								//if (member != null && member.getIsEnable() && member.getOrgId().equals(parentOrgId)) {
								if (member != null && member.getIsEnable()) {
									
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsHCERTContentSign"), incident.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsHCERTContentSign"), member.getName(), incident.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

									// debug
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

									// 符合一次，處理完成即跳出迴圈
									break;
								}
							}
						}
						
						
						// debug
//						System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - 【roleId == 2】=" + (roleId == 2));
						
						// 寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin
						// 收件者：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getEmail()
						// 主旨：CERT事件處理單("not.getPostId()")處理副知
						// 內容：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
						List<ViewMemberRoleMember> memberRoles2 = memberRoleService.findByRoleId(15);
							
						if (memberRoles2 != null) {

							// 取得 [會員機構] 欄位對應之權責單位資料
							List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(reporterName);
							Long parentOrgId = null;
							
							if (orgs != null) {
								for (ViewOrgOrgSign org : orgs) {
									parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)
									break;
								}
							}  
							
							for (ViewMemberRoleMember memberRole : memberRoles2) {
								
								Member member = memberService.get(memberRole.getMemberId());

								// debug
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - Id=" + member.getId());
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - 【member.getOrgId() == parentOrgId】=" + (member.getOrgId() == parentOrgId));
								
								// 祇寄給權責單位管理者
								if (member != null && member.getIsEnable() && member.getOrgId().equals(parentOrgId)) {
									
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsApplySingAdmin"), incident.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsApplySingAdmin"), member.getName(), incident.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

									// debug
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - MemberId=" + memberRole.getMemberId());
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

									// 符合一次，處理完成即跳出迴圈
									break;
								}
							}
						}
					
						
						// 寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact
						// 收件者：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getEmail()
						// 主旨：CERT事件處理單("not.getPostId()")處理副知
						// 內容：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
						List<ViewMemberRoleMember> memberRoles3 = memberRoleService.findByRoleId(10);
						
						if (memberRoles3 != null) {
							for (ViewMemberRoleMember memberRole : memberRoles3) {
								
								Member member = memberService.get(memberRole.getMemberId());

								// debug
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - Id=" + member.getId());
//								System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - 【member.getOrgId() == reporterName】=" + (member.getOrgId() == reporterName));
								
								// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
								if (member != null && member.getIsEnable() && member.getOrgId().equals(reporterName)) {
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsMemberContact"), incident.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsMemberContact"), member.getName(), incident.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

									// debug
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - MemberId=" + memberRole.getMemberId());
//									System.out.println("i01_IncidentController.java → Create() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

									// 符合一次，處理完成即跳出迴圈
									break;
								}
							}
						}
						
					}
					
				}
					
				processLogService.insert(getBaseMemberId(), json, String.valueOf(incident.getId()));
			}
			// 流程紀錄用 - 結束

			if (incident != null) {

				// 流程紀錄用 - 開始
				responseJson.put("Id", incident.getId());
				responseJson.put("PostId", incident.getPostId());
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
		return responseJson.toString();
	}

	/**
	 * 更新Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Incident
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/i01/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
			// 流程紀錄用 - 開始
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			Boolean check = obj.getBoolean("Check");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");
			Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");
			Long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");
			// 流程紀錄用 - 結束

			// debug
//			System.out.println("i01_IncidentController.java → Update() → id：" + id);
//			System.out.println("i01_IncidentController.java → Update() → check：" + check);

			if (!incidentService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				// debug
//				System.out.println("i01_IncidentController.java → Update() → json：" + json);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberId：" + baseMemberId);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberRole.IsHisac(2.H-ISAC管理者)：" + baseMemberRole.IsHisac);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberRole.IsApplySingAdmin(15.權責單位通報審核者)：" + baseMemberRole.IsApplySingAdmin);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberRole.IsMemberContact(10.會員機構聯絡人)：" + baseMemberRole.IsMemberContact);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberRole.IsHCERTContentSign(17.H-CERT審核者)：" + baseMemberRole.IsHCERTContentSign);
//				System.out.println("i01_IncidentController.java → Update() → baseMemberRole.IsEventHandlingUnitContact(18.事件處理單位聯絡人)：" + baseMemberRole.IsEventHandlingUnitContact);

				// 流程紀錄用 - 開始
				// 依 check 判斷是否要更新事件單編號(將暫存單號改成正式單號)
				Incident incident = incidentService.update(getBaseMemberId(), json, check);
				
				// 取得 RoleId
				int roleId;
				
				if (baseMemberRole.IsAdmin) { // 1-SuperAdmin
					roleId = 1;
				} else if (baseMemberRole.IsHisac) { // 2.H-ISAC管理者
					roleId = 2;
				} else if (baseMemberRole.IsApplySingAdmin) { // 15.權責單位通報審核者
					roleId = 15;
				} else if (baseMemberRole.IsMemberContact) { // 10.會員機構聯絡人
					roleId = 10;
				} else if (baseMemberRole.IsHCERTContentSign) { // 17.H-CERT審核者
					roleId = 17;
				} else if (baseMemberRole.IsEventHandlingUnitContact) { // 18.事件處理單位聯絡人
					roleId = 18;
				} else {
					roleId = 0;
				}

				// debug
//				System.out.println("i01_IncidentController.java → Update() → baseMemberId：" + baseMemberId);
//				System.out.println("i01_IncidentController.java → Update() → roleId：" + roleId);
//				System.out.println("i01_IncidentController.java → Update() → ReporterName(會員機構)：" + reporterName);
//				System.out.println("i01_IncidentController.java → Update() → HandleName(事件處理單位)：" + handleName);
//				System.out.println("i01_IncidentController.java → Update() → ContactorId(事件處理單位聯絡人)：" + contactorId);
				
				// 處理如下階段：
				//     1-編輯中 → 2-事件處理中
				//     2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中
				if (isWriteProcessLog) {

					processLogService.insert(getBaseMemberId(), json, String.valueOf(incident.getId()));
					
					// 取得 [會員機構] 欄位對應之權責單位資料
//					List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(reporterName);
//					Long parentOrgId = null;
//					
//					if (orgs != null) {
//						for (ViewOrgOrgSign org : orgs) {
//							parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)
//							break;
//						}
//					}  
					
					// debug
//					System.out.println("i01_IncidentController.java → Update() → parentOrgId：" + parentOrgId);
					
					// 1-編輯中 → 2-事件處理中
					if (status.equals(new Long(2))) {
						
						// from 1-SuperAdmin：IsAdmin
						// from 2.H-ISAC管理者：IsHisac
						// from 15.權責單位通報審核者：IsApplySingAdmin
						if (roleId == 1 || roleId == 2 || roleId == 17) {
							
							// 寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact
							// 收件者：memberService.get((memberRoleService.findByRoleId(18)).getMemberId()).getEmail()
							// 主旨：CERT事件處理單("not.getPostId()")通知
							// 內容：memberService.get((memberRoleService.findByRoleId(18)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正等待您的處理，請您儘快撥冗進行，謝謝！
							List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(18);
							
							if (memberRoles != null) {
								for (ViewMemberRoleMember memberRole : memberRoles) {

									Member member = memberService.get(memberRole.getMemberId());
									
									// 祇寄給紀錄中已在[事件處理單位]欄位指定之連絡人
									if (member != null && member.getIsEnable() && member.getId().equals(contactorId)) {
										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectToIsEventHandlingUnitContact"), incident.getPostId());
										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyToIsEventHandlingUnitContact"), member.getName(), incident.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
										
										// debug
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - MemberId=" + memberRole.getMemberId());
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());
										
										// 符合一次，處理完成即跳出迴圈
										break;
									}
								}
							}

							
							// 寄發通知信(副知) to 17.HCERT審核者：IsEventHandlingUnitContact
							// 收件者：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getEmail()
							// 主旨：CERT事件處理單("not.getPostId()")處理副知
							// 內容：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
//							List<ViewMemberRoleMember> memberRoles1 = memberRoleService.findByRoleId(17);
//							
//							if (memberRoles1 != null) {
//
//								for (ViewMemberRoleMember memberRole : memberRoles1) {
//									
//									Member member = memberService.get(memberRole.getMemberId());
//
//									// 祇寄給權責單位有相同 OrgId 的相關人員
//									//if (member != null && member.getIsEnable() && member.getOrgId().equals(parentOrgId)) {
//									if (member != null && member.getIsEnable()) {
//										
//										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsHCERTContentSign"), incident.getPostId());
//										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsHCERTContentSign"), member.getName(), incident.getPostId());
//										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
//
//										// debug
////										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.HCERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
////										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 17.HCERT審核者：IsHCERTContentSign - OrgId=" + member.getOrgId() + " - Name=" + member.getName());
//
//										// 符合一次，處理完成即跳出迴圈
//										break;
//									}
//								}
//							}
							
							
							// 寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin
							// 收件者：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getEmail()
							// 主旨：CERT事件處理單("not.getPostId()")處理副知
							// 內容：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
							List<ViewMemberRoleMember> memberRoles2 = memberRoleService.findByRoleId(15);

							if (memberRoles2 != null) {

								// 寄發通知信(副知)：業務管理機關
								for (ViewMemberRoleMember memberRole : memberRoles2) {
									
									Member member = memberService.get(memberRole.getMemberId());
									
									// 祇寄給權責單位有相同 OrgId 的相關人員
									if (member != null && member.getIsEnable() && member.getOrgId().equals(incident.getNotificationMainUnit2())) {
										
										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsApplySingAdmin"), incident.getPostId());
										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsApplySingAdmin"), member.getName(), incident.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

										// debug
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者[業務管理機關]：IsApplySingAdmin - MemberId=" + memberRole.getMemberId());
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者[業務管理機關]：IsApplySingAdmin - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

										// 符合一次，處理完成即跳出迴圈
										break;
									}
								}
								
								// 判斷是否需要副知[上級機關]，再做處理
								// 寄發通知信(副知)：上級機關
								if (incident.getNotificationIsCC3() && memberRoles2 != null) {
									for (ViewMemberRoleMember memberRole : memberRoles2) {
										
										Member member = memberService.get(memberRole.getMemberId());
										
										// 祇寄給權責單位有相同 OrgId 的相關人員
										if (member != null && member.getIsEnable() && member.getOrgId().equals(incident.getNotificationMainUnit1())) {
											
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsApplySingAdmin"), incident.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsApplySingAdmin"), member.getName(), incident.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

											// debug
//											System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者[上級機關]：IsApplySingAdmin - MemberId=" + memberRole.getMemberId());
//											System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 15.權責單位通報審核者[上級機關]：IsApplySingAdmin - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

											// 符合一次，處理完成即跳出迴圈
											break;
										}
									}
								}
							}
							
							
							// 寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact
							// 收件者：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getEmail()
							// 主旨：CERT事件處理單("not.getPostId()")處理副知
							// 內容：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，正由事件處理單位處理中，特此通知，謝謝！
							List<ViewMemberRoleMember> memberRoles3 = memberRoleService.findByRoleId(10);
							
							if (memberRoles3 != null) {
								for (ViewMemberRoleMember memberRole : memberRoles3) {
									Member member = memberService.get(memberRole.getMemberId());

									// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
									if (member != null && member.getIsEnable() && member.getOrgId().equals(reporterName)) {
										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2SubjectCCIsMemberContact"), incident.getPostId());
										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident1To2BodyCCIsMemberContact"), member.getName(), incident.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

										// debug
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - MemberId=" + memberRole.getMemberId());
//										System.out.println("i01_IncidentController.java → Update() → 【1-編輯中 → 2-事件處理中】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

										// 符合一次，處理完成即跳出迴圈
										break;
									}
								}
							}
							
						}
												
					// 2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中
					} else if (status.equals(new Long(3))) {
						
						// from 18.事件處理單位聯絡人：IsEventHandlingUnitContact
						if (roleId == 18) {
							
							// 第一次審核
							
							// 寄發通知信 to 10.會員機構聯絡人：IsMemberContact
							// 收件者：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getEmail()
							// 主旨：CERT事件處理單("not.getPostId()")審核通知
							// 內容：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getName()，您好！CERT事件處理單("not.getPostId()")，正等待您的審查，請您儘快撥冗進行，謝謝！
							List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(10);
							
							if (memberRoles != null) {
								for (ViewMemberRoleMember memberRole : memberRoles) {
									
									Member member = memberService.get(memberRole.getMemberId());

									// debug
//									System.out.println("i01_IncidentController.java → Update() → 【2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中】(第一次審核)寄發通知信 to 10.會員機構聯絡人：IsMemberContact - MemberId=" + memberRole.getMemberId());
									
									// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
									if (member != null && member.getIsEnable() && member.getOrgId().equals(reporterName)) {
										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident2To3SubjectToIsMemberContact"), incident.getPostId());
										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident2To3BodyToIsMemberContact"), member.getName(), incident.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

										// debug
//										System.out.println("i01_IncidentController.java → Update() → 【2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中】(第一次審核)寄發通知信 to 10.會員機構聯絡人：IsMemberContact - MemberId(符合)=" + memberRole.getMemberId());
//										System.out.println("i01_IncidentController.java → Update() → 【2-事件處理中 | 4-事件處理中(退回) → 3-事件處理審核中】(第一次審核)寄發通知信 to 10.會員機構聯絡人：IsMemberContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

										// 更新 事件處理 incident table 的 SubStatus to 31
										Incident entity = incidentDAO.get(id);
										entity.setSubStatus((long) 31);
										incidentDAO.update(entity);
										
										// 符合一次，處理完成即跳出迴圈
										break;
									}
								}
							}
						}
					}
				}
				// 流程紀錄用 - 結束

				if (incident != null) {

					// 流程紀錄用 - 開始
					responseJson.put("Id", incident.getId());
					responseJson.put("PostId", incident.getPostId());
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
		return responseJson.toString();
	}
	
	/**
	 * 更新Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Incident
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/i01/modify", method = RequestMethod.POST)
	public @ResponseBody String Modify(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");

			if (!incidentService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Incident incident = incidentService.modify(getBaseMemberId(), json, false);
				if (incident != null) {
					responseJson.put("Id", incident.getId());
					responseJson.put("PostId", incident.getPostId());
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
	 * 刪除Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/i01/delete", method = RequestMethod.POST)
	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!incidentService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				try {
					List<ViewIncidentAttachMember> incidentAttachs = incidentAttachService.getAllByIncidentId(id);
					if (incidentAttachs != null) {
						for (ViewIncidentAttachMember incidentAttach : incidentAttachs) {
							incidentAttachService.delete(incidentAttach.getId());
						}
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				if (incidentService.delete(id)) {
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
	
	/**
	 * 停用Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Incident
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/i01/disable", method = RequestMethod.POST)
	public @ResponseBody String Disable(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!incidentService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				incidentService.disable(getBaseMemberId(), id);
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

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
	@RequestMapping(value = "/i01/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long incidentId = obj.isNull("IncidentId") == true ? 0 : obj.getLong("IncidentId");
			List<ViewIncidentAttachMember> incidentAttachs = incidentAttachService.getAllByIncidentId(incidentId);
			if (incidentAttachs != null) {
				for (ViewIncidentAttachMember incidentAttach : incidentAttachs) {
					long size = incidentAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("IncidentId", incidentAttach.getIncidentId());
					sn_json.put("Id", incidentAttach.getId());
					sn_json.put("FileName", incidentAttach.getFileName());
					sn_json.put("FileType", incidentAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", incidentAttach.getFileHash());
					sn_json.put("FileDesc", incidentAttach.getFileDesc());
					sn_json.put("CreateId", incidentAttach.getCreateId());
					sn_json.put("CreateName", incidentAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(incidentAttach.getCreateTime()));
					sn_json.put("ModifyId", incidentAttach.getModifyId());
					sn_json.put("ModifyName", incidentAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(incidentAttach.getModifyTime()));
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
	 *            IncidentId
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
	@RequestMapping(value = "/i01/attach/upload", method = RequestMethod.POST)
	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("IncidentId", id);
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
					Incident incident = incidentService.get(id);
					if (incident != null) {
						IncidentAttach entity = incidentAttachService.insert(getBaseMemberId(), json, bytes);
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
					// e.printStackTrace();
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
			systemLogService.insert(baseControllerName, baseActionName, "IncidentId=" + id.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	@RequestMapping(value = "/i01/attach/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!incidentAttachService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (incidentAttachService.delete(id)) {
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

	/**
	 * 附件下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param incidentId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/i01/attach/download/{incidentId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long incidentId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("IncidentId", incidentId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!incidentService.isExist(incidentId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					// ex.printStackTrace();
				}
			} else if (!incidentAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					// ex.printStackTrace();
				}
			} else {
				response.reset();
				IncidentAttach incidentAttach = incidentAttachService.getById(id);
				try {
					byte[] buffer = incidentAttach.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(incidentAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(incidentAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					// ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}

	/**
	 * 審核事件資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/i01/examine", method = RequestMethod.POST)
	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONObject obj = new JSONObject(json);
			
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long preStatus = obj.isNull("PreStatus") == true ? 0 : obj.getLong("PreStatus");
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");

			// debug
//			System.out.println("==========================================================");
//			System.out.println("i01_IncidentController.java → Examine() → id=" + id);
//			System.out.println("i01_IncidentController.java → Examine() → status=" + status);
//			System.out.println("i01_IncidentController.java → Examine() → preStatus=" + preStatus);
//			System.out.println("i01_IncidentController.java → Examine() → tableName=" + tableName);
//			System.out.println("i01_IncidentController.java → Examine() → pre=" + pre);
//			System.out.println("i01_IncidentController.java → Examine() → opinion=" + opinion);

			// 流程管理用
			// String preStatus = obj.isNull("PreStatus") == true ? null :
			// obj.getString("PreStatus");
			// String opinion = obj.isNull("Opinion") == true ? null :
			// obj.getString("Opinion");

			if (!incidentService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {

				// debug
//				System.out.println("i01_IncidentController.java → Examine() → json(變更前)=" + json);

				Incident incident = incidentService.examine(getBaseMemberId(), String.valueOf(id), String.valueOf(status), json);
				
				// 流程管理用：因應 3:事件處理審核中 有2個單位要做簽核，不能直接由 3-事件處理審核中 → 5-已結案，故，重組 json，讓簽核程式不會顯示錯亂的階段 - 開始
				Long modifyStatus = incident.getStatus();
				
				JSONArray sn_array = new JSONArray();
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", id);
				sn_json.put("Status", modifyStatus);
				sn_json.put("PreStatus", preStatus);
				sn_json.put("TableName", tableName);
				sn_json.put("Pre", pre);
				sn_json.put("Opinion", opinion);
				sn_array.put(sn_json);
				json = sn_array.toString().replace("[", "").replace("]", "");

				// debug
//				System.out.println("i01_IncidentController.java → Examine() → json(變更後)=" + json);

				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, String.valueOf(id));
				// 流程管理用：因應 3:事件處理審核中 有2個單位要做簽核，不能直接由 3-事件處理審核中 → 5-已結案，故，重組 json，讓簽核程式不會顯示錯亂的階段 - 結束

				if (incident != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

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
		return responseJson.toString();
	}

	/**
	 * 取得事件資料button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 事件資料button count資料
	 */
	@RequestMapping(value = "/i01/query/button/count", method = RequestMethod.POST)
	public String QueryButtonCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			
			/*
			 * 1.SuperAdmin
			 * 2.H-ISAC管理者：IsHisac
			 * 15.權責單位通報審核者：IsApplySingAdmin
			 * 10.會員機構聯絡人：IsMemberContact
			 * 17.H-CERT審核者：IsHCERTContentSign
			 * 18.事件處理單位聯絡人：IsEventHandlingUnitContact
			 */
			if (baseMemberRole.IsAdmin) {
				obj.put("RoleId", 1);

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：1");
			} else if (baseMemberRole.IsHisac) {
				obj.put("RoleId", 2);

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：2");
			} else if (baseMemberRole.IsMemberContact) {
				obj.put("RoleId", 10);

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：10");
			} else if (baseMemberRole.IsApplySingAdmin) {
				obj.put("RoleId", 15);

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：15");
			} else if (baseMemberRole.IsHCERTContentSign) {
				obj.put("RoleId", 17);
			
				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：17");
			} else if (baseMemberRole.IsEventHandlingUnitContact) {
				obj.put("RoleId", 18);

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → RoleId：18");
			}

			obj.put("MemberId", getBaseMemberId());

			// debug
//			System.out.println("i01_IncidentController.java → QueryButtonCount() → baseMemberId：" + baseMemberId);
			
			if (getBaseOrgType().equals("1") || getBaseOrgType().equals("2")) {
				obj.put("ParentOrgId", getBaseOrgId());

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → ParentOrgId：" + baseOrgId);
			} else if (getBaseOrgType().equals("3")) {
				obj.put("ReporterName", getBaseOrgId());

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → ReporterName：" + baseOrgId);
			} else if (getBaseOrgType().equals("4")) {
				obj.put("HandleName", getBaseOrgId());

				// debug
//				System.out.println("i01_IncidentController.java → QueryButtonCount() → HandleName：" + baseOrgId);
			}
			
			json = obj.toString();

			// debug
//			System.out.println("i01_IncidentController.java → QueryButtonCount() → json：" + json);
			
			JSONArray sn_array = new JSONArray();
			List<SpButtonCount> spButtonCounts = incidentService.getSpButtonCount(json);
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

	/**
	 * 取得Org 資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Org 資料
	 */
	@RequestMapping(value = "/i01/query/org", method = RequestMethod.POST)
	public String QueryOrg(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<Org> orgs = orgService.getByOrgType("2");
			if (orgs != null) {
				for (Org org : orgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_array.put(sn_json);
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得Org 會員機構資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Org 會員機構資料
	 */
	@RequestMapping(value = "/i01/query/orgauthorityunit", method = RequestMethod.POST)
	public String QueryOrgAuthorityUnit(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			// debug
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseMemberAccount：" + baseMemberAccount);
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseMemberName：" + baseMemberName);
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseMemberId：" + baseMemberId);
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseOrgType：" + baseOrgType);
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseAuthType：" + baseAuthType);
//			System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → baseOrgId：" + baseOrgId);

			/*
			 * 1.SuperAdmin
			 * 2.H-ISAC管理者：IsHisac
			 * 15.權責單位通報審核者：IsApplySingAdmin
			 * 10.會員機構聯絡人：IsMemberContact
			 * 17.H-CERT審核者：IsHCERTContentSign
			 * 18.事件處理單位聯絡人：IsEventHandlingUnitContact
			 */
			List<Org> orgs  = null;
			List<ViewOrgOrgSign> orgs2 = null;
			
			if (baseMemberRole.IsAdmin) {
				
				orgs = orgService.getByOrgType("3");
				
				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：1");
			} else if (baseMemberRole.IsHisac) {

				orgs = orgService.getByOrgType("3");
				
				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：2");
			} else if (baseMemberRole.IsMemberContact) {

				orgs2 = orgService.getByParentOrgId(getBaseOrgId());
				
				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：10");
			} else if (baseMemberRole.IsApplySingAdmin) {
				
				orgs2 = orgService.getByParentOrgId(getBaseOrgId());
				
				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：15");
			} else if (baseMemberRole.IsHCERTContentSign) {

				//orgs2 = orgService.getByParentOrgId(baseOrgId);
				orgs = orgService.getByOrgType("3");
				
				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：17");
			} else if (baseMemberRole.IsEventHandlingUnitContact) {

				// debug
//				System.out.println("i01_IncidentController.java → QueryOrgAuthorityUnit() → RoleId：18");
			}

			if (orgs != null) {
				for (Org org : orgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_array.put(sn_json);
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else if (orgs2 != null) {
				for (ViewOrgOrgSign org : orgs2) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_array.put(sn_json);
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}  
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得Org 事件處理單位資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Org 事件處理單位資料
	 */
	@RequestMapping(value = "/i01/query/orgmemberunit", method = RequestMethod.POST)
	public String QueryOrgMemberUnit(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<Org> orgs = orgService.getByOrgType("4");
			if (orgs != null) {
				for (Org org : orgs) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_array.put(sn_json);
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得Member 事件處理單位聯絡人資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Member 事件處理單位聯絡人資料
	 */
	@RequestMapping(value = "/i01/query/member", method = RequestMethod.POST)
	public String QueryMember(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();

		// debug
//		System.out.println("i01_IncidentController.java → QueryMember() → baseMemberId：" + baseMemberId);
		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONObject obj = new JSONObject(json);
			long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

			// debug
//			System.out.println("i01_IncidentController.java → QueryMember() → orgId：" + orgId);
			
//			List<Member> members = memberService.getAll();
			List<Member> members = memberService.getByOrgId(orgId);
			
			if (members != null) {
				for (Member member : members) {
					JSONObject sn_json = new JSONObject();

					// debug
//					System.out.println("i01_IncidentController.java → QueryMember() → Id：" + member.getId());
//					System.out.println("i01_IncidentController.java → QueryMember() → Name：" + member.getName());
//					System.out.println("i01_IncidentController.java → QueryMember() → Account：" + member.getAccount());
//					System.out.println("i01_IncidentController.java → QueryMember() → CityPhone：" + member.getCityPhone());
//					System.out.println("i01_IncidentController.java → QueryMember() → FaxPhone：" + member.getFaxPhone());
//					System.out.println("i01_IncidentController.java → QueryMember() → Email：" + member.getEmail());
					if (member.getIsEnable()) {
						sn_json.put("Id", member.getId());
						sn_json.put("Name", member.getName());
						sn_json.put("Account", member.getAccount());
						sn_json.put("CityPhone", member.getCityPhone());
						sn_json.put("FaxPhone", member.getFaxPhone());
						sn_json.put("Email", member.getEmail());
						sn_array.put(sn_json);
						// 祇取1筆資料
						break;
					}
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得 incident SubStatus 資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return incident SubStatus 資料
	 */
	@RequestMapping(value = "/i01/query/substatus", method = RequestMethod.POST)
	public String QuerySubStatus(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();

		// debug
//		System.out.println("i01_IncidentController.java → QuerySubStatus() → baseMemberId：" + baseMemberId);
		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			// debug
//			System.out.println("i01_IncidentController.java → QueryMember() → id：" + id);
			
			Incident incident = incidentService.get(id);
			
			if (incident != null) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("SubStatus", incident.getSubStatus());
				sn_array.put(sn_json);

				// debug
//				System.out.println("i01_IncidentController.java → QuerySubStatus() → SubStatus：" + incident.getSubStatus());
				
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}
	
	
	/**
	 * 取得Incident資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Incident資料
	 * @return Incident資料
	 */
	@RequestMapping(value = "/i01/query/notificationId", method = RequestMethod.POST)
	public String QueryByNotificationId(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject sn_obj = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String notificationId = obj.isNull("NotificationId") == true ? null : obj.getString("NotificationId");

			
			// 取得指定 id 之事件資料資料
			Incident incident = incidentService.getByNotificationId(notificationId);
			sn_obj.put("Id", incident.getId());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_obj.toString());
		return "msg";
	}

}