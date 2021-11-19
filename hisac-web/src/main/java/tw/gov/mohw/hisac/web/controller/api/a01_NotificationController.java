package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.HandleInformation;
import tw.gov.mohw.hisac.web.domain.Incident;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.NotificationAsset;
import tw.gov.mohw.hisac.web.domain.NotificationLog;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNotification;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewNotificationLogMember;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.service.NotificationService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.OrgSignService;
import tw.gov.mohw.hisac.web.service.MessageService;
import tw.gov.mohw.hisac.web.service.NotificationAssetService;
import tw.gov.mohw.hisac.web.service.NotificationExchangeNcertService;
import tw.gov.mohw.hisac.web.service.NotificationLogService;
import tw.gov.mohw.hisac.web.service.MessagePostAttachService;
import tw.gov.mohw.hisac.web.service.HandleInformationManagementService;
import tw.gov.mohw.hisac.web.service.IncidentService;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.IresApiService;
import tw.gov.mohw.hisac.web.service.MailService;

/**
 * 通報管理控制器
 */
@Controller
@RequestMapping(value = "/alt/api", produces = "application/json; charset=utf-8")
public class a01_NotificationController extends BaseController {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private OrgSignService orgSignService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MessagePostAttachService messagePostAttachService;
	@Autowired
	private InformationExchangeService informationExchangeService;
	@Autowired
	private NotificationExchangeNcertService notificationExchangeNcertService;
	@Autowired
	private HandleInformationManagementService handleInformationService;	
	@Autowired
	private MailService mailService;	
	@Autowired
	private NotificationLogService notificationLogService;
	@Autowired
	private NotificationAssetService notificationAssetService;
	@Autowired
	private IncidentService incidentService;	
	@Autowired
	private IresApiService iresApiService;
	

	private String targetControllerName = "alt";
	private String targetActionName = "a01";

	@RequestMapping(value = "/a01/query/member_info", method = RequestMethod.POST)
	public String QueryMemberInfo(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long memberId = obj.isNull("Id") ? getBaseMemberId() : obj.getLong("Id");
			Member member = memberService.get(memberId);
			if (member != null) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("ContactorId", member.getId());
				sn_json.put("ContactorName", member.getName());
				sn_json.put("ContactorTel", member.getCityPhone());
				sn_json.put("ContactorFax", member.getFaxPhone());
				sn_json.put("ContactorEmail", member.getEmail());
				Org org = orgService.getDataById(member.getOrgId());
				sn_json.put("ContactorUnit", org.getId());
				sn_json.put("ContactorUnitName", org.getName());
				ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(member.getOrgId());
				if (superviseParentOrg != null) {
					sn_json.put("MainUnit1", superviseParentOrg.getId());
					sn_json.put("MainUnit1Name", superviseParentOrg.getName());
				}
				ViewParentOrg localParentOrg = orgService.getLocalParentOrg(member.getOrgId());
				if (localParentOrg != null) {
					sn_json.put("MainUnit2", localParentOrg.getId());
					sn_json.put("MainUnit2Name", localParentOrg.getName());
				}
				sn_array.put(sn_json);
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		return "msg";
	}

	/**
	 * 取得通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	@RequestMapping(value = "/a01/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();

		JSONObject obj = new JSONObject(json);
		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		// debug 用
//		System.out.println("==========================================================");
//		System.out.println("a01_NotificationController.java → Query() → json：" + json.toString());
//		System.out.println("a01_NotificationController.java → Query() → baseMemberAccount：" + baseMemberAccount);
//		System.out.println("a01_NotificationController.java → Query() → baseMemberName：" + baseMemberName);
//		System.out.println("a01_NotificationController.java → Query() → baseMemberId：" + baseMemberId);
//		System.out.println("a01_NotificationController.java → Query() → baseOrgType：" + baseOrgType);
//		System.out.println("a01_NotificationController.java → Query() → baseAuthType：" + baseAuthType);
//		System.out.println("a01_NotificationController.java → Query() → baseOrgId：" + baseOrgId);
		
		if (baseMemberRole.IsHisac == true) // 2-H-ISAC管理者
		{
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacNotifySign == true) // 12-HISAC通報審核者
		{
			obj.put("RoleId", 12);
		} else if (baseMemberRole.IsApplySingAdmin == true) // 15-權責單位通報審核者
		{
			obj.put("RoleId", 15);
		} else if (baseMemberRole.IsMemberContact == true) /* 10-會員機構聯絡人 */
		{
			obj.put("RoleId", 10);
		} else if (baseMemberRole.IsAdmin == true) /* 1-SuperAdmin */
		{
			obj.put("RoleId", 1);
		}

		obj.put("OrgId", getBaseOrgId());
		obj.put("MemberId", getBaseMemberId());
		
		json = obj.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			List<SpNotification> notifications = notificationService.getSpList(json);
			listjson.put("total", notificationService.getSpListSize(json));
			// 取得org
			Org org = orgService.getDataById(getBaseOrgId());
			JSONArray sn_array = new JSONArray();
			if (notifications != null)
				for (SpNotification notification : notifications) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", notification.getId());
					sn_json.put("ContactorUnitName", notification.getName());
					sn_json.put("PostId", notification.getPostId());
					sn_json.put("EventRemark", notification.getEventRemark());
					sn_json.put("MessagePostId", notification.getMessagePostId());
					sn_json.put("Status", notification.getStatus());
					sn_json.put("IsTest", notification.getIsTest());
					sn_json.put("ApplyDateTime", WebDatetime.toString(notification.getApplyDateTime()));									
					Integer hour = (int)(new Date().getTime() - notification.getModifyTime().getTime())/(60*60*1000);
					Integer min = (int)(new Date().getTime() - notification.getModifyTime().getTime())/(60*1000) - hour*60;
					sn_json.put("Time", hour.toString() + "時" + min.toString() + "分");					
					switch (notification.getStatus()) {
						case 1 :
							if (baseMemberRole.IsMemberContact) {
								sn_json.put("IsButtonEdit", true);
								sn_json.put("IsButtonDelete", true);
							} else {
								sn_json.put("IsButtonEdit", false);
								sn_json.put("IsButtonDelete", false);
							}

							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 2 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);

							if (baseMemberRole.IsMemberContact)
								sn_json.put("IsButtonUndo", true);
							else
								sn_json.put("IsButtonUndo", false);

							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 31 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);

							if (baseMemberRole.IsApplySingAdmin && org.getAuthType().equals("2"))
								sn_json.put("IsButtonReview", true);
							else
								sn_json.put("IsButtonReview", false);

							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 32 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);

							if (baseMemberRole.IsApplySingAdmin && org.getAuthType().equals("1"))
								sn_json.put("IsButtonReview", true);
							else
								sn_json.put("IsButtonReview", false);

							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 33 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);

							if (baseMemberRole.IsHisacNotifySign)
								sn_json.put("IsButtonReview", true);
							else
								sn_json.put("IsButtonReview", false);

							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 51 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);

							if (baseMemberRole.IsApplySingAdmin && org.getAuthType().equals("2"))
								sn_json.put("IsButtonHandleReview", true);
							else
								sn_json.put("IsButtonHandleReview", false);

							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 52 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);

							if (baseMemberRole.IsApplySingAdmin && org.getAuthType().equals("1"))
								sn_json.put("IsButtonHandleReview", true);
							else
								sn_json.put("IsButtonHandleReview", false);

							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 53 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);

							if (baseMemberRole.IsHisacNotifySign)
								sn_json.put("IsButtonHandleReview", true);
							else
								sn_json.put("IsButtonHandleReview", false);

							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 6 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							Integer transferOutType = notification.getTransferOutType();
							if (baseMemberRole.IsHisacNotifySign && (transferOutType == null || transferOutType == 0)) {
								sn_json.put("IsButtonToMessage", true);
								sn_json.put("IsButtonToImformation", true);
							} else {
								sn_json.put("IsButtonToMessage", false);
								sn_json.put("IsButtonToImformation", false);
							}
							break;
						case 4 :
						case 9 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);

							if (baseMemberRole.IsMemberContact)
								sn_json.put("IsButtonHandle", true);
							else
								sn_json.put("IsButtonHandle", false);

							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						case 8 :
							if (baseMemberRole.IsMemberContact)
								sn_json.put("IsButtonEdit", true);
							else
								sn_json.put("IsButtonEdit", false);

							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
						default :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonHandle", false);
							sn_json.put("IsButtonHandleReview", false);
							sn_json.put("IsButtonToMessage", false);
							sn_json.put("IsButtonToImformation", false);
							break;
					}
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
	 * 取得通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	@RequestMapping(value = "/a01/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		boolean isSeeIsCC3 = false;
		boolean isSeeIsReview3 = false;
		boolean isSeeIsCC5 = false;
		boolean isSeeIsReview5 = false;
		boolean isNeedSaleCC3 = false;
		boolean isNeedSaleReview3 = false;
		boolean isNeedSaleCC5 = false;
		boolean isNeedSaleReview5 = false;

		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		String tableName = obj.isNull("TableName") == true ? "" : obj.getString("TableName");
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Notification notification = notificationService.getById(id);
			Member member = memberService.get(notification.getCreateId());
			List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
			List<Org> orgs = orgService.getAll();
			if (orgSigns != null) {
				for (OrgSign orgSign : orgSigns) {
					if (orgs != null) {
						for (Org org : orgs) {
							if (orgSign.getParentOrgId().equals(org.getId()) && org.getIsEnable()) {
								if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										isSeeIsReview3 = true;
									if (org.getAuthType().equals("1"))
										isNeedSaleReview3 = true;
								}
								if (orgSign.getNotificationIsExamine().equals(2) || orgSign.getNotificationIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										isSeeIsCC3 = true;
									if (org.getAuthType().equals("1") && orgSign.getNotificationIsExamine().equals(2))
										isNeedSaleCC3 = true;
								}
								if (orgSign.getNotificationClosingIsExamine().equals(1) || orgSign.getNotificationClosingIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										isSeeIsReview5 = true;
									if (org.getAuthType().equals("1"))
										isNeedSaleReview5 = true;
								}
								if (orgSign.getNotificationClosingIsExamine().equals(2) || orgSign.getNotificationClosingIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										isSeeIsCC5 = true;
									if (org.getAuthType().equals("1") && orgSign.getNotificationIsExamine().equals(2))
										isNeedSaleCC5 = true;
								}
							}
						}
					}
				}
			}
			JSONObject sn_json = new JSONObject();
			JSONArray notification_array = new JSONArray();
			JSONArray notificationAsset_array = new JSONArray();
			sn_json.put("Id", notification.getId());
			// step1
			sn_json.put("ApplyDateTime", WebDatetime.toString(notification.getApplyDateTime()));
			sn_json.put("PostId", notification.getPostId());
			sn_json.put("ContactorUnit", notification.getContactorUnit());
			sn_json.put("MainUnit1", notification.getMainUnit1());
			sn_json.put("MainUnit2", notification.getMainUnit2());
			sn_json.put("ContactorId", notification.getContactorId());
			sn_json.put("ContactorTel", notification.getContactorTel());
			sn_json.put("ContactorFax", notification.getContactorFax());
			sn_json.put("ContactorEmail", notification.getContactorEmail());
			sn_json.put("IsSub", notification.getIsSub());
			sn_json.put("IsSubUnitName", notification.getIsSubUnitName());
			sn_json.put("EventDateTime", WebDatetime.toString(notification.getEventDateTime()));
			sn_json.put("HostAmount", notification.getHostAmount());
			sn_json.put("ServerAmount", notification.getServerAmount());
			sn_json.put("InformationAmount", notification.getInformationAmount());
			sn_json.put("OtherDeviceAmount", notification.getOtherDeviceAmount());
			sn_json.put("OtherDeviceName", notification.getOtherDeviceName());
			sn_json.put("DeviceRemark", notification.getDeviceRemark());
			sn_json.put("AssessDamage", notification.getAssessDamage());
			sn_json.put("AssessDamageRemark", notification.getAssessDamageRemark());	
			sn_json.put("IsFinishDoSysOptRemark", notification.getIsFinishDoSysOptRemark());	
			sn_json.put("IsFinishDoEduOptRemark", notification.getIsFinishDoEduOptRemark());	
			sn_json.put("FinishDoSysOptRemark", notification.getFinishDoSysOptRemark());	
			sn_json.put("FinishDoEduOptRemark", notification.getFinishDoEduOptRemark());	
			sn_json.put("IsHandledByMyself", notification.getIsHandledByMyself());	
			sn_json.put("HandleInformationId", notification.getHandleInformationId());	
			sn_json.put("IpExternal", notification.getIpExternal());
			sn_json.put("IpInternal", notification.getIpInternal());
			sn_json.put("WebUrl", notification.getWebUrl());
			sn_json.put("IsOsOpt1", notification.getIsOsOpt1());
			sn_json.put("IsOsOpt2", notification.getIsOsOpt2());
			sn_json.put("IsOsOpt3", notification.getIsOsOpt3());
			sn_json.put("IsOsOpt3Other", notification.getIsOsOpt3Other());
			sn_json.put("IsGuardOpt1", notification.getIsGuardOpt1());
			sn_json.put("IsGuardOpt2", notification.getIsGuardOpt2());
			sn_json.put("IsGuardOpt3", notification.getIsGuardOpt3());
			sn_json.put("IsGuardOpt4", notification.getIsGuardOpt4());
			sn_json.put("IsGuardOpt4Other", notification.getIsGuardOpt4Other());
			sn_json.put("SocOpt", notification.getSocOpt());
			sn_json.put("SocOptCompany", notification.getSocOptCompany());
			sn_json.put("IsIsms", notification.getIsIsms());
			sn_json.put("MaintainCompany", notification.getMaintainCompany());
			sn_json.put("IsTest", notification.getIsTest());
			// step2
			sn_json.put("CeffectLevel", notification.getCeffectLevel());
			sn_json.put("IeffectLevel", notification.getIeffectLevel());
			sn_json.put("AeffectLevel", notification.getAeffectLevel());
			// step3
			sn_json.put("EventType", notification.getEventType());
			sn_json.put("IsEventType1Opt1", notification.getIsEventType1Opt1());
			sn_json.put("IsEventType1Opt2", notification.getIsEventType1Opt2());
			sn_json.put("IsEventType1Opt3", notification.getIsEventType1Opt3());
			sn_json.put("IsEventType1Opt4", notification.getIsEventType1Opt4());
			sn_json.put("IsEventType1Opt5", notification.getIsEventType1Opt5());
			sn_json.put("IsEventType1Opt6", notification.getIsEventType1Opt6());
			sn_json.put("IsEventType2Opt1", notification.getIsEventType2Opt1());
			sn_json.put("IsEventType2Opt2", notification.getIsEventType2Opt2());
			sn_json.put("IsEventType2Opt3", notification.getIsEventType2Opt3());
			sn_json.put("IsEventType2Opt4", notification.getIsEventType2Opt4());
			sn_json.put("IsEventType2Opt5", notification.getIsEventType2Opt5());
			sn_json.put("IsEventType3Opt1", notification.getIsEventType3Opt1());
			sn_json.put("IsEventType3Opt2", notification.getIsEventType3Opt2());
			sn_json.put("IsEventType4Opt1", notification.getIsEventType4Opt1());
			sn_json.put("IsEventType4Opt2", notification.getIsEventType4Opt2());
			sn_json.put("IsEventType4Opt3", notification.getIsEventType4Opt3());
			sn_json.put("IsEventType4Opt4", notification.getIsEventType4Opt4());
			sn_json.put("EventType5Other", notification.getEventType5Other());
			sn_json.put("EventRemark", notification.getEventRemark());
			sn_json.put("IsAffectOthers", notification.getIsAffectOthers());
			sn_json.put("AffectOther1", notification.getAffectOther1());
			sn_json.put("AffectOther2", notification.getAffectOther2());
			sn_json.put("AffectOther3", notification.getAffectOther3());
			sn_json.put("AffectOther4", notification.getAffectOther4());
			sn_json.put("AffectOther5", notification.getAffectOther5());
			sn_json.put("AffectOther6", notification.getAffectOther6());
			sn_json.put("AffectOther7", notification.getAffectOther7());
			sn_json.put("AffectOther8", notification.getAffectOther8());
			sn_json.put("EventSource", notification.getEventSource());
			sn_json.put("EventSourceNo", notification.getEventSourceNo());
			sn_json.put("EventSourceOther", notification.getEventSourceOther());
			// step4-all
			sn_json.put("ResDescription", notification.getResDescription());
			sn_json.put("ResControlTime", WebDatetime.toString(notification.getResControlTime()));
			sn_json.put("ResResult", notification.getResResult());
			// step4-1
			sn_json.put("IsRes1LogOpt1", notification.getIsRes1LogOpt1());
			sn_json.put("IsRes1LogOpt2", notification.getIsRes1LogOpt2());
			sn_json.put("IsRes1LogOpt5", notification.getIsRes1LogOpt5());
			sn_json.put("IsRes1LogOpt3", notification.getIsRes1LogOpt3());
			sn_json.put("IsRes1LogOpt4", notification.getIsRes1LogOpt4());
			sn_json.put("Res1LogOpt1", notification.getRes1LogOpt1());
			sn_json.put("Res1LogOpt1Other", notification.getRes1LogOpt1Other());
			sn_json.put("Res1LogOpt2", notification.getRes1LogOpt2());
			sn_json.put("Res1LogOpt2Other", notification.getRes1LogOpt2Other());
			sn_json.put("Res1LogOpt5", notification.getRes1LogOpt5());
			sn_json.put("Res1LogOpt5Other", notification.getRes1LogOpt5Other());
			sn_json.put("Res1LogOpt3Amount", notification.getRes1LogOpt3Amount());
			sn_json.put("Res1LogOpt4Remark", notification.getRes1LogOpt4Remark());
			sn_json.put("IsRes1EvaOpt1", notification.getIsRes1EvaOpt1());
			sn_json.put("IsRes1EvaOpt2", notification.getIsRes1EvaOpt2());
			sn_json.put("IsRes1EvaOpt3", notification.getIsRes1EvaOpt3());
			sn_json.put("IsRes1EvaOpt4", notification.getIsRes1EvaOpt4());
			sn_json.put("IsRes1EvaOpt5", notification.getIsRes1EvaOpt5());
			sn_json.put("IsRes1EvaOpt6", notification.getIsRes1EvaOpt6());
			sn_json.put("IsRes1EvaOpt7", notification.getIsRes1EvaOpt7());
			sn_json.put("IsRes1EvaOpt8", notification.getIsRes1EvaOpt8());
			sn_json.put("Res1EvaOpt1Remark", notification.getRes1EvaOpt1Remark());
			sn_json.put("Res1EvaOpt2Remark", notification.getRes1EvaOpt2Remark());
			sn_json.put("Res1EvaOpt3Remark", notification.getRes1EvaOpt3Remark());
			sn_json.put("Res1EvaOpt4Remark", notification.getRes1EvaOpt4Remark());
			sn_json.put("Res1EvaOpt6Amount", notification.getRes1EvaOpt6Amount());
			sn_json.put("Res1EvaOpt6Remark", notification.getRes1EvaOpt6Remark());
			sn_json.put("Res1EvaOpt6Type", notification.getRes1EvaOpt6Type());
			sn_json.put("Res1EvaOpt6TypeOpt3Other", notification.getRes1EvaOpt6TypeOpt3Other());
			sn_json.put("Res1EvaOpt7Amount", notification.getRes1EvaOpt7Amount());
			sn_json.put("Res1EvaOpt7Remark", notification.getRes1EvaOpt7Remark());
			sn_json.put("Res1EvaOpt8Remark", notification.getRes1EvaOpt8Remark());
			sn_json.put("IsRes1DoOpt1", notification.getIsRes1DoOpt1());
			sn_json.put("IsRes1DoOpt2", notification.getIsRes1DoOpt2());
			sn_json.put("IsRes1DoOpt3", notification.getIsRes1DoOpt3());
			sn_json.put("IsRes1DoOpt4", notification.getIsRes1DoOpt4());
			sn_json.put("IsRes1DoOpt5", notification.getIsRes1DoOpt5());
			sn_json.put("IsRes1DoOpt6", notification.getIsRes1DoOpt6());
			sn_json.put("IsRes1DoOpt7", notification.getIsRes1DoOpt7());
			sn_json.put("IsRes1DoOpt8", notification.getIsRes1DoOpt8());
			sn_json.put("IsRes1DoOpt9", notification.getIsRes1DoOpt9());
			sn_json.put("IsRes1DoOpt10", notification.getIsRes1DoOpt10());
			sn_json.put("IsRes1DoOpt11", notification.getIsRes1DoOpt11());
			sn_json.put("IsRes1DoOpt12", notification.getIsRes1DoOpt12());
			sn_json.put("Res1DoOpt1Amount", notification.getRes1DoOpt1Amount());
			sn_json.put("Res1DoOpt2Remark", notification.getRes1DoOpt2Remark());
			sn_json.put("Res1DoOpt3Remark", notification.getRes1DoOpt3Remark());
			sn_json.put("Res1DoOpt4Remark", notification.getRes1DoOpt4Remark());
			sn_json.put("Res1DoOpt5Amount", notification.getRes1DoOpt5Amount());
			sn_json.put("IsRes1DoOpt9EngineOpt1", notification.getIsRes1DoOpt9EngineOpt1());
			sn_json.put("IsRes1DoOpt9EngineOpt2", notification.getIsRes1DoOpt9EngineOpt2());
			sn_json.put("IsRes1DoOpt9EngineOpt3", notification.getIsRes1DoOpt9EngineOpt3());
			sn_json.put("IsRes1DoOpt9EngineOpt4", notification.getIsRes1DoOpt9EngineOpt4());
			sn_json.put("IsRes1DoOpt9EngineOpt5", notification.getIsRes1DoOpt9EngineOpt5());
			sn_json.put("IsRes1DoOpt9EngineOpt6", notification.getIsRes1DoOpt9EngineOpt6());
			sn_json.put("Res1DoOpt9EngineOpt6Other", notification.getRes1DoOpt9EngineOpt6Other());
			sn_json.put("Res1DoOpt10Date", WebDatetime.toString(notification.getRes1DoOpt10Date()));
			sn_json.put("Res1DoOpt11Date", WebDatetime.toString(notification.getRes1DoOpt11Date()));
			sn_json.put("Res1DoOpt12Remark", notification.getRes1DoOpt12Remark());
			// step4-2
			sn_json.put("IsRes2LogOpt1", notification.getIsRes2LogOpt1());
			sn_json.put("IsRes2LogOpt2", notification.getIsRes2LogOpt2());
			sn_json.put("IsRes2LogOpt3", notification.getIsRes2LogOpt3());
			sn_json.put("IsRes2LogOpt4", notification.getIsRes2LogOpt4());
			sn_json.put("Res2LogOpt1", notification.getRes2LogOpt1());
			sn_json.put("Res2LogOpt1Other", notification.getRes2LogOpt1Other());
			sn_json.put("Res2LogOpt2", notification.getRes2LogOpt2());
			sn_json.put("Res2LogOpt2Other", notification.getRes2LogOpt2Other());
			sn_json.put("Res2LogOpt3Amount", notification.getRes2LogOpt3Amount());
			sn_json.put("Res2LogOpt4Remark", notification.getRes2LogOpt4Remark());
			sn_json.put("IsRes2EvaOpt1", notification.getIsRes2EvaOpt1());
			sn_json.put("IsRes2EvaOpt2", notification.getIsRes2EvaOpt2());
			sn_json.put("IsRes2EvaOpt3", notification.getIsRes2EvaOpt3());
			sn_json.put("IsRes2EvaOpt4", notification.getIsRes2EvaOpt4());
			sn_json.put("IsRes2EvaOpt5", notification.getIsRes2EvaOpt5());
			sn_json.put("Res2EvaOpt1Remark", notification.getRes2EvaOpt1Remark());
			sn_json.put("Res2EvaOpt2Remark", notification.getRes2EvaOpt2Remark());
			sn_json.put("Res2EvaOpt3Remark", notification.getRes2EvaOpt3Remark());
			sn_json.put("Res2EvaOpt4Remark", notification.getRes2EvaOpt4Remark());
			sn_json.put("Res2EvaOpt5Remark", notification.getRes2EvaOpt5Remark());
			sn_json.put("IsRes2DoOpt1", notification.getIsRes2DoOpt1());
			sn_json.put("IsRes2DoOpt2", notification.getIsRes2DoOpt2());
			sn_json.put("IsRes2DoOpt3", notification.getIsRes2DoOpt3());
			sn_json.put("IsRes2DoOpt4", notification.getIsRes2DoOpt4());
			sn_json.put("IsRes2DoOpt5", notification.getIsRes2DoOpt5());
			sn_json.put("IsRes2DoOpt6", notification.getIsRes2DoOpt6());
			sn_json.put("IsRes2DoOpt7", notification.getIsRes2DoOpt7());
			sn_json.put("Res2DoOpt1Amount", notification.getRes2DoOpt1Amount());
			sn_json.put("Res2DoOpt1Remark", notification.getRes2DoOpt1Remark());
			sn_json.put("Res2DoOpt2Remark", notification.getRes2DoOpt2Remark());
			sn_json.put("Res2DoOpt3Remark", notification.getRes2DoOpt3Remark());
			sn_json.put("Res2DoOpt5Date", WebDatetime.toString(notification.getRes2DoOpt5Date()));
			sn_json.put("Res2DoOpt6Amount", notification.getRes2DoOpt6Amount());
			sn_json.put("Res2DoOpt7Remark", notification.getRes2DoOpt7Remark());
			// step4-3
			sn_json.put("IsRes3LogOpt1", notification.getIsRes3LogOpt1());
			sn_json.put("IsRes3LogOpt2", notification.getIsRes3LogOpt2());
			sn_json.put("IsRes3LogOpt3", notification.getIsRes3LogOpt3());
			sn_json.put("IsRes3LogOpt4", notification.getIsRes3LogOpt4());
			sn_json.put("Res3LogOpt1", notification.getRes3LogOpt1());
			sn_json.put("Res3LogOpt1Other", notification.getRes3LogOpt1Other());
			sn_json.put("Res3LogOpt2", notification.getRes3LogOpt2());
			sn_json.put("Res3LogOpt2Other", notification.getRes3LogOpt2Other());
			sn_json.put("Res3LogOpt3Amount", notification.getRes3LogOpt3Amount());
			sn_json.put("Res3LogOpt4Remark", notification.getRes3LogOpt4Remark());
			sn_json.put("IsRes3EvaOpt1", notification.getIsRes3EvaOpt1());
			sn_json.put("IsRes3EvaOpt2", notification.getIsRes3EvaOpt2());
			sn_json.put("IsRes3EvaOpt3", notification.getIsRes3EvaOpt3());
			sn_json.put("Res3EvaOpt1Amount", notification.getRes3EvaOpt1Amount());
			sn_json.put("Res3EvaOpt2Remark", notification.getRes3EvaOpt2Remark());
			sn_json.put("Res3EvaOpt3Remark", notification.getRes3EvaOpt3Remark());
			sn_json.put("IsRes3DoOpt1", notification.getIsRes3DoOpt1());
			sn_json.put("IsRes3DoOpt2", notification.getIsRes3DoOpt2());
			sn_json.put("IsRes3DoOpt3", notification.getIsRes3DoOpt3());
			sn_json.put("IsRes3DoOpt4", notification.getIsRes3DoOpt4());
			sn_json.put("Res3DoOpt1Remark", notification.getRes3DoOpt1Remark());
			sn_json.put("Res3DoOpt3Remark", notification.getRes3DoOpt3Remark());
			sn_json.put("Res3DoOpt4Remark", notification.getRes3DoOpt4Remark());
			// step4-4
			sn_json.put("IsRes4LogOpt1", notification.getIsRes4LogOpt1());
			sn_json.put("Res4LogOpt1Remark", notification.getRes4LogOpt1Remark());
			sn_json.put("IsRes4EvaOpt1", notification.getIsRes4EvaOpt1());
			sn_json.put("IsRes4EvaOpt2", notification.getIsRes4EvaOpt2());
			sn_json.put("IsRes4EvaOpt3", notification.getIsRes4EvaOpt3());
			sn_json.put("Res4EvaOpt1", notification.getRes4EvaOpt1());
			sn_json.put("Res4EvaOpt1Amount", notification.getRes4EvaOpt1Amount());
			sn_json.put("Res4EvaOpt2Remark", notification.getRes4EvaOpt2Remark());
			sn_json.put("Res4EvaOpt3Remark", notification.getRes4EvaOpt3Remark());
			sn_json.put("IsRes4DoOpt1", notification.getIsRes4DoOpt1());
			sn_json.put("IsRes4DoOpt2", notification.getIsRes4DoOpt2());
			sn_json.put("IsRes4DoOpt4", notification.getIsRes4DoOpt4());
			sn_json.put("IsRes4DoOpt3", notification.getIsRes4DoOpt3());
			sn_json.put("Res4DoOpt3Remark", notification.getRes4DoOpt3Remark());
			// step4-5
			sn_json.put("IsRes5LogOpt1", notification.getIsRes5LogOpt1());
			sn_json.put("IsRes5LogOpt2", notification.getIsRes5LogOpt2());
			sn_json.put("IsRes5LogOpt3", notification.getIsRes5LogOpt3());
			sn_json.put("IsRes5LogOpt4", notification.getIsRes5LogOpt4());
			sn_json.put("Res5LogOpt1", notification.getRes5LogOpt1());
			sn_json.put("Res5LogOpt1Other", notification.getRes5LogOpt1Other());
			sn_json.put("Res5LogOpt2", notification.getRes5LogOpt2());
			sn_json.put("Res5LogOpt2Other", notification.getRes5LogOpt2Other());
			sn_json.put("Res5LogOpt3Amount", notification.getRes5LogOpt3Amount());
			sn_json.put("Res5LogOpt4Remark", notification.getRes5LogOpt4Remark());
			sn_json.put("IsRes5EvaOpt1", notification.getIsRes5EvaOpt1());
			sn_json.put("IsRes5EvaOpt2", notification.getIsRes5EvaOpt2());
			sn_json.put("IsRes5EvaOpt3", notification.getIsRes5EvaOpt3());
			sn_json.put("IsRes5EvaOpt4", notification.getIsRes5EvaOpt4());
			sn_json.put("IsRes5EvaOpt5", notification.getIsRes5EvaOpt5());
			sn_json.put("Res5EvaOpt1Remark", notification.getRes5EvaOpt1Remark());
			sn_json.put("Res5EvaOpt2Remark", notification.getRes5EvaOpt2Remark());
			sn_json.put("Res5EvaOpt3Remark", notification.getRes5EvaOpt3Remark());
			sn_json.put("Res5EvaOpt4Remark", notification.getRes5EvaOpt4Remark());
			sn_json.put("Res5EvaOpt5Remark", notification.getRes5EvaOpt5Remark());
			sn_json.put("IsRes5DoOpt1", notification.getIsRes5DoOpt1());
			sn_json.put("IsRes5DoOpt2", notification.getIsRes5DoOpt2());
			sn_json.put("IsRes5DoOpt3", notification.getIsRes5DoOpt3());
			sn_json.put("IsRes5DoOpt4", notification.getIsRes5DoOpt4());
			sn_json.put("IsRes5DoOpt5", notification.getIsRes5DoOpt5());
			sn_json.put("IsRes5DoOpt6", notification.getIsRes5DoOpt6());
			sn_json.put("IsRes5DoOpt7", notification.getIsRes5DoOpt7());
			sn_json.put("Res5DoOpt1Amount", notification.getRes5DoOpt1Amount());
			sn_json.put("Res5DoOpt1Remark", notification.getRes5DoOpt1Remark());
			sn_json.put("Res5DoOpt2Remark", notification.getRes5DoOpt2Remark());
			sn_json.put("Res5DoOpt3Remark", notification.getRes5DoOpt3Remark());
			sn_json.put("Res5DoOpt5Date", WebDatetime.toString(notification.getRes5DoOpt5Date()));
			sn_json.put("Res5DoOpt6Amount", notification.getRes5DoOpt6Amount());
			sn_json.put("Res5DoOpt7Remark", notification.getRes5DoOpt7Remark());
			// step5
			sn_json.put("IsNeedSupport", notification.getIsNeedSupport());
			sn_json.put("NeedSupportRemark", notification.getNeedSupportRemark());
			// step6-1
			sn_json.put("Finish1Reason", notification.getFinish1Reason());
			sn_json.put("Finish1ReasonOther", notification.getFinish1ReasonOther());
			sn_json.put("Finish1ReasonToDo", notification.getFinish1ReasonToDo());
			sn_json.put("IsFinish1DoSysOpt1", notification.getIsFinish1DoSysOpt1());
			sn_json.put("IsFinish1DoSysOpt2", notification.getIsFinish1DoSysOpt2());
			sn_json.put("IsFinish1DoSysOpt3", notification.getIsFinish1DoSysOpt3());
			sn_json.put("IsFinish1DoSysOpt4", notification.getIsFinish1DoSysOpt4());
			sn_json.put("IsFinish1DoSysOpt5", notification.getIsFinish1DoSysOpt5());
			sn_json.put("IsFinish1DoSysOpt6", notification.getIsFinish1DoSysOpt6());
			sn_json.put("IsFinish1DoSysOpt7", notification.getIsFinish1DoSysOpt7());
			sn_json.put("IsFinish1DoSysOpt8", notification.getIsFinish1DoSysOpt8());
			sn_json.put("IsFinish1DoSysOpt9", notification.getIsFinish1DoSysOpt9());
			sn_json.put("IsFinish1DoSysOpt10", notification.getIsFinish1DoSysOpt10());
			sn_json.put("Finish1DoSysOpt3Remark", notification.getFinish1DoSysOpt3Remark());
			sn_json.put("Finish1DoSysOpt6Remark", notification.getFinish1DoSysOpt6Remark());
			sn_json.put("Finish1DoSysOpt7Remark", notification.getFinish1DoSysOpt7Remark());
			sn_json.put("IsFinish1DoEduOpt1", notification.getIsFinish1DoEduOpt1());
			sn_json.put("IsFinish1DoEduOpt2", notification.getIsFinish1DoEduOpt2());
			sn_json.put("IsFinish1DoEduOpt3", notification.getIsFinish1DoEduOpt3());
			sn_json.put("IsFinish1DoEduOpt4", notification.getIsFinish1DoEduOpt4());
			// step6-2
			sn_json.put("Finish2Reason", notification.getFinish2Reason());
			sn_json.put("Finish2ReasonOther", notification.getFinish2ReasonOther());
			sn_json.put("Finish2ReasonRemark", notification.getFinish2ReasonRemark());
			sn_json.put("IsFinish2DoSysOpt1", notification.getIsFinish2DoSysOpt1());
			sn_json.put("IsFinish2DoSysOpt2", notification.getIsFinish2DoSysOpt2());
			sn_json.put("IsFinish2DoSysOpt3", notification.getIsFinish2DoSysOpt3());
			sn_json.put("IsFinish2DoSysOpt4", notification.getIsFinish2DoSysOpt4());
			sn_json.put("IsFinish2DoSysOpt5", notification.getIsFinish2DoSysOpt5());
			sn_json.put("Finish2DoSysOpt1Remark", notification.getFinish2DoSysOpt1Remark());
			sn_json.put("IsFinish2DoEduOpt1", notification.getIsFinish2DoEduOpt1());
			sn_json.put("IsFinish2DoEduOpt2", notification.getIsFinish2DoEduOpt2());
			sn_json.put("IsFinish2DoEduOpt3", notification.getIsFinish2DoEduOpt3());
			sn_json.put("IsFinish2DoEduOpt4", notification.getIsFinish2DoEduOpt4());
			// step6-3
			sn_json.put("IsFinish3DoSysOpt1", notification.getIsFinish3DoSysOpt1());
			sn_json.put("IsFinish3DoSysOpt2", notification.getIsFinish3DoSysOpt2());
			sn_json.put("IsFinish3DoSysOpt3", notification.getIsFinish3DoSysOpt3());
			sn_json.put("IsFinish3DoSysOpt4", notification.getIsFinish3DoSysOpt4());
			sn_json.put("Finish3DoSysOpt3Remark", notification.getFinish3DoSysOpt3Remark());
			sn_json.put("Finish3DoSysOpt4Remark", notification.getFinish3DoSysOpt4Remark());
			sn_json.put("IsFinish3DoEduOpt1", notification.getIsFinish3DoEduOpt1());
			sn_json.put("IsFinish3DoEduOpt2", notification.getIsFinish3DoEduOpt2());
			// step6-4
			sn_json.put("Finish4Reason", notification.getFinish4Reason());
			sn_json.put("Finish4ReasonOther", notification.getFinish4ReasonOther());
			sn_json.put("Finish4ReasonRemark", notification.getFinish4ReasonRemark());
			sn_json.put("IsFinish4DoSysOpt1", notification.getIsFinish4DoSysOpt1());
			sn_json.put("IsFinish4DoEduOpt1", notification.getIsFinish4DoEduOpt1());
			sn_json.put("IsFinish4DoEduOpt2", notification.getIsFinish4DoEduOpt2());
			sn_json.put("IsFinish4DoEduOpt3", notification.getIsFinish4DoEduOpt3());
			sn_json.put("IsFinish4DoEduOpt4", notification.getIsFinish4DoEduOpt4());
			// step6-5
			sn_json.put("Finish5Reason", notification.getFinish5Reason());
			sn_json.put("Finish5ReasonOther", notification.getFinish5ReasonOther());
			sn_json.put("Finish5ReasonRemark", notification.getFinish5ReasonRemark());
			sn_json.put("IsFinish5DoSysOpt1", notification.getIsFinish5DoSysOpt1());
			sn_json.put("IsFinish5DoSysOpt2", notification.getIsFinish5DoSysOpt2());
			sn_json.put("IsFinish5DoSysOpt3", notification.getIsFinish5DoSysOpt3());
			sn_json.put("IsFinish5DoSysOpt4", notification.getIsFinish5DoSysOpt4());
			sn_json.put("Finish5DoSysOpt1Remark", notification.getFinish5DoSysOpt1Remark());
			sn_json.put("IsFinish5DoEduOpt1", notification.getIsFinish5DoEduOpt1());
			sn_json.put("IsFinish5DoEduOpt2", notification.getIsFinish5DoEduOpt2());
			sn_json.put("IsFinish5DoEduOpt3", notification.getIsFinish5DoEduOpt3());
			sn_json.put("IsFinish5DoEduOpt4", notification.getIsFinish5DoEduOpt4());			
			// step6-...
			sn_json.put("FinishDoOther", notification.getFinishDoOther());
			sn_json.put("FinishDateTime", WebDatetime.toString(notification.getFinishDateTime()));
			// other...
			sn_json.put("Status", notification.getStatus());
			sn_json.put("ExamineDateTime", WebDatetime.toString(notification.getExamineDateTime()));
			sn_json.put("RealFinishDateTime", WebDatetime.toString(notification.getRealFinishDateTime()));
			sn_json.put("MessageId", notification.getMessageId());
			sn_json.put("PostId", notification.getPostId());
			sn_json.put("IsCC3", notification.getIsCC3());
			sn_json.put("IsReview3", notification.getIsReview3());
			sn_json.put("IsCC5", notification.getIsCC5());
			sn_json.put("IsReview5", notification.getIsReview5());

			if ((notification.getStatus() == 1 || notification.getStatus() == 8) && isSeeIsCC3)
				sn_json.put("IsSeeIsCC3", true);
			else
				sn_json.put("IsSeeIsCC3", false);
			if ((notification.getStatus() == 1 || notification.getStatus() == 8) && isSeeIsReview3)
				sn_json.put("IsSeeIsReview3", true);
			else
				sn_json.put("IsSeeIsReview3", false);
			if ((notification.getStatus() == 4 || notification.getStatus() == 9) && isSeeIsCC5)
				sn_json.put("IsSeeIsCC5", true);
			else
				sn_json.put("IsSeeIsCC5", false);
			if ((notification.getStatus() == 4 || notification.getStatus() == 9) && isSeeIsReview5)
				sn_json.put("IsSeeIsReview5", true);
			else
				sn_json.put("IsSeeIsReview5", false);
			sn_json.put("IsNeedSaleCC3", isNeedSaleCC3);
			sn_json.put("IsNeedSaleReview3", isNeedSaleReview3);
			sn_json.put("IsNeedSaleCC5", isNeedSaleCC5);
			sn_json.put("IsNeedSaleReview5", isNeedSaleReview5);
			
			//新增OT資料列表
			List<NotificationAsset> notificationAssets = notificationAssetService.getAllByNotificationId(id);
			if (notificationAssets != null) {
				for ( NotificationAsset notificationAsset : notificationAssets) {
					JSONObject notificationAsset_json = new JSONObject();
					notificationAsset_json.put("Id", notificationAsset.getId());
					notificationAsset_json.put("AssetName", notificationAsset.getAssetClassName());
					notificationAsset_json.put("Brand", notificationAsset.getBrand());
					notificationAsset_json.put("Model", notificationAsset.getModel());
					notificationAsset_json.put("OperatingSystemVersion", notificationAsset.getOperatingSystemVersion());
					notificationAsset_json.put("AssetClass", notificationAsset.getAssetClass());
					notificationAsset_array.put(notificationAsset_json);
				}
			}
			
			sn_json.put("notificationAsset", notificationAsset_array);

			
			
			// log
			List<ViewProcessLogMember> notificationLogs = processLogService.getByPostId(id, tableName);
			Date today = new Date();
			if (notificationLogs != null) {
				for (ViewProcessLogMember notificationLog : notificationLogs) {
					JSONObject notification_json = new JSONObject();
					notification_json.put("PreStatus", notificationLog.getPreStatus());
					notification_json.put("Status", notificationLog.getStatus());
					notification_json.put("CreateTime", WebDatetime.toString(notificationLog.getCreateTime()));
					notification_json.put("Opinion", notificationLog.getOpinion());
					notification_json.put("CreateName", notificationLog.getCreateName());
					notification_json.put("PreName", notificationLog.getPreName());
					notification_json.put("Days", (today.getTime() - notificationLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
					notification_array.put(notification_json);
				}
			}			
			sn_json.put("NotificationLog", notification_array);
			//通報單等級Log
			JSONArray notificationLog_array = new JSONArray();
			List<ViewNotificationLogMember> viewNotificationLogMembers = notificationLogService.getByNotificationId(id);
			if (viewNotificationLogMembers != null) {
				for (ViewNotificationLogMember viewNotificationLogMember : viewNotificationLogMembers) {
					JSONObject notificationLog_json = new JSONObject();
					notificationLog_json.put("AeffectLevel", viewNotificationLogMember.getAeffectLevel());
					notificationLog_json.put("CeffectLevel", viewNotificationLogMember.getCeffectLevel());
					notificationLog_json.put("EffectLevel", viewNotificationLogMember.getEffectLevel());
					notificationLog_json.put("IeffectLevel", viewNotificationLogMember.getIeffectLevel());
					notificationLog_json.put("CreateTime", WebDatetime.toString(viewNotificationLogMember.getCreateTime()));
					notificationLog_json.put("CreateName", viewNotificationLogMember.getCreateName());
					notificationLog_array.put(notificationLog_json);
				}
			}
			sn_json.put("NotificationLevelLog", notificationLog_array);
			// sn_json.put("", notification.get());
			// sn_json.put("", notification.get());
			// sn_json.put("", notification.get());
			//queryMemberInfoById
			Member memberInfo = memberService.get(notification.getContactorId());
			if (memberInfo != null) {								      
				sn_json.put("ContactorName", memberInfo.getName());				
				Org org = orgService.getDataById(memberInfo.getOrgId());				
				sn_json.put("ContactorUnitName", org.getName());
				ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(memberInfo.getOrgId());
				if (superviseParentOrg != null) {					      
					sn_json.put("MainUnit1Name", superviseParentOrg.getName());
				}
				ViewParentOrg localParentOrg = orgService.getLocalParentOrg(memberInfo.getOrgId());
				if (localParentOrg != null) {					      
					sn_json.put("MainUnit2Name", localParentOrg.getName());
				}				
			}

			sn_array.put(sn_json);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		return "msg";
	}

	/**
	 * 取得是否通知副知API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return 通報資料
	 */
	@RequestMapping(value = "/a01/IsCCorReview", method = RequestMethod.POST)
	public String IsCCorReview(Locale locale, HttpServletRequest request, Model model) {
		boolean isSeeIsCC = false;
		boolean isSeeIsReview = false;
		boolean isNeedSaleCC = false;
		boolean isNeedSaleReview = false;
		Member member = memberService.get(getBaseMemberId());
		List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
		if (orgSigns != null) {
			for (OrgSign orgSign : orgSigns) {
				if (orgService.getDataById(orgSign.getParentOrgId()).getIsEnable()) {
					if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
						if (orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("2"))
							isSeeIsReview = true;
						if (orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("1"))
							isNeedSaleReview = true;
					}
					if (orgSign.getNotificationIsExamine().equals(2) || orgSign.getNotificationIsExamine().equals(3)) {
						if (orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("2"))
							isSeeIsCC = true;
						if (orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("1") && orgSign.getNotificationIsExamine().equals(2))
							isNeedSaleCC = true;
					}
				}
			}
		}
		JSONObject sn_json = new JSONObject();
		JSONArray sn_array = new JSONArray();
		sn_json.put("IsSeeIsCC3", isSeeIsCC);
		sn_json.put("IsSeeIsReview3", isSeeIsReview);
		sn_json.put("IsSeeIsCC5", false);
		sn_json.put("IsSeeIsReview5", false);
		sn_json.put("IsNeedSaleCC3", isNeedSaleCC);
		sn_json.put("IsNeedSaleReview3", isNeedSaleReview);
		sn_array.put(sn_json);
		model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		return "msg";
	}

	/**
	 * 新增通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            通報
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/a01/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			try {
				byte[] byteJson = json.getBytes(StandardCharsets.UTF_8.toString());
				json = encoder.encodeToString(byteJson);
				json = myFilter.filterHtml(json);
				json = new String(decoder.decode(json), StandardCharsets.UTF_8.toString());
			} catch (Exception e) {
				json = "";
			}

			JSONObject obj = new JSONObject(json);
			obj.put("IsReview3", false);			
			obj.put("IsCC3", false);			
			obj.put("IsReview5", false);			
			obj.put("IsCC5", false);		
			
			JSONArray otAddDataList = obj.isNull("OtAddDataList") == true ? null : obj.getJSONArray("OtAddDataList"); 
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			Member member = memberService.get(getBaseMemberId());
			List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
			List<Org> orgs = orgService.getAll();
			if (orgSigns != null) {
				for (OrgSign orgSign : orgSigns) {
					if (orgs != null) {
						for (Org org : orgs) {
							if (orgSign.getParentOrgId().equals(org.getId()) && org.getIsEnable()) {
								if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										obj.put("IsReview3", true);																	
								}
								if (orgSign.getNotificationIsExamine().equals(2) || orgSign.getNotificationIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										obj.put("IsCC3", true);																			
								}
								if (orgSign.getNotificationClosingIsExamine().equals(1) || orgSign.getNotificationClosingIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										obj.put("IsReview5", true);																			
								}
								if (orgSign.getNotificationClosingIsExamine().equals(2) || orgSign.getNotificationClosingIsExamine().equals(3)) {
									if (org.getAuthType().equals("2"))
										obj.put("IsCC5", true);																				
								}
							}
						}
					}
				}
			}
			Notification notification = notificationService.insert(getBaseMemberId(), obj.toString());
			
			
			//OT資料寫入 
			
			if (otAddDataList != null) {
				for (int i = 0 ; i < otAddDataList.length(); i++) {
		            JSONObject otAddData = otAddDataList.getJSONObject(i);
		            
		            NotificationAsset notificationAsset = notificationAssetService.insert(getBaseMemberId(), otAddData.toString(), String.valueOf(notification.getId()) ) ;
		            
		            if(notificationAsset == null) {
		            	responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
		            }	
				}
				
				
			}
			
			//是否寫入log 功能 利用 isWriteProcessLog 控制
			if (isWriteProcessLog) {
				if (processLogService.insert(getBaseMemberId(), json, String.valueOf(notification.getId())) == null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					return responseJson.toString();
				}
				//通報單等級加入保留記錄功能
                JSONObject level_json = new JSONObject();
                level_json.put("CeffectLevel", notification.getCeffectLevel());
                level_json.put("IeffectLevel", notification.getIeffectLevel());
                level_json.put("AeffectLevel", notification.getAeffectLevel());
                level_json.put("EffectLevel", notification.getEffectLevel());
                level_json.put("NotificationId", notification.getId());        
                NotificationLog notificationLog = notificationLogService.insert(getBaseMemberId(), level_json.toString());
                if (notificationLog == null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					return responseJson.toString();
				}                
			}
			if (notification != null) {
				if (notification.getId() != null && !notification.getId().equals("") && notification.getId().length() <= 32) {
					String responseId = myFilter.filterHtml(notification.getId());
					responseId = myFilter.stripXSS(responseId);
					responseJson.put("Id", responseId);
				}
				if (notification.getPostId() != null && !notification.getPostId().equals("") && notification.getPostId().length() <= 32) {
					String responsePostId = myFilter.filterHtml(notification.getPostId());
					responsePostId = myFilter.stripXSS(responsePostId);
					responseJson.put("PostId", responsePostId);
				}
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		

		return  WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 更新通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            通報
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/a01/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			try {
				byte[] byteJson = json.getBytes(StandardCharsets.UTF_8.toString());
				json = encoder.encodeToString(byteJson);
				json = myFilter.filterHtml(json);
				json = new String(decoder.decode(json), StandardCharsets.UTF_8.toString());
			} catch (Exception e) {
				json = "";
			}

			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			
			JSONArray otAddDataList = obj.isNull("OtAddDataList") == true ? null : obj.getJSONArray("OtAddDataList"); 
			
			//OT資料更新 (先刪除舊資料,再重新新增關聯ot資料)
			
			List<NotificationAsset> notificationAssets = notificationAssetService.getAllByNotificationId(id);
			
			if (notificationAssets !=null) {
				for (NotificationAsset notificationAsset :notificationAssets) {
					Long notificationAssetId  = notificationAsset.getId();
					notificationAssetService.delete(notificationAssetId);
				}
			}
			
			if (otAddDataList != null) {
				for (int i = 0 ; i < otAddDataList.length(); i++) {
		            JSONObject otAddData = otAddDataList.getJSONObject(i);
		            
		            NotificationAsset notificationAsset = notificationAssetService.insert(getBaseMemberId(), otAddData.toString(), id);
		            
		            if(notificationAsset == null) {
		            	responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
		            }	
				}
				
			}
			

			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				obj.put("IsReview3", false);			
				obj.put("IsCC3", false);			
				obj.put("IsReview5", false);			
				obj.put("IsCC5", false);
				Member member = memberService.get(notificationService.getById(id).getCreateId());
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				List<Org> orgs = orgService.getAll();
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						if (orgs != null) {
							for (Org org : orgs) {
								if (orgSign.getParentOrgId().equals(org.getId()) && org.getIsEnable()) {
									if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsReview3", true);																	
									}
									if (orgSign.getNotificationIsExamine().equals(2) || orgSign.getNotificationIsExamine().equals(3)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsCC3", true);																			
									}
									if (orgSign.getNotificationClosingIsExamine().equals(1) || orgSign.getNotificationClosingIsExamine().equals(3)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsReview5", true);																			
									}
									if (orgSign.getNotificationClosingIsExamine().equals(2) || orgSign.getNotificationClosingIsExamine().equals(3)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsCC5", true);																				
									}
								}
							}
						}
					}
				}
				Notification notification = notificationService.update(getBaseMemberId(), obj.toString());
				if (isWriteProcessLog) {
					if (processLogService.insert(getBaseMemberId(), json, String.valueOf(notification.getId())) == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}
					//通報單等級加入保留記錄功能
	                JSONObject level_json = new JSONObject();
	                level_json.put("CeffectLevel", notification.getCeffectLevel());
	                level_json.put("IeffectLevel", notification.getIeffectLevel());
	                level_json.put("AeffectLevel", notification.getAeffectLevel());
	                level_json.put("EffectLevel", notification.getEffectLevel());
	                level_json.put("NotificationId", notification.getId());        
	                NotificationLog notificationLog = notificationLogService.insert(getBaseMemberId(), level_json.toString());
	                if (notificationLog == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}   
				}
				if (notification != null) {
					if (notification.getId() != null && !notification.getId().equals("") && notification.getId().length() <= 32) {
						String responseId = myFilter.filterHtml(notification.getId());
						responseId = myFilter.stripXSS(responseId);
						responseJson.put("Id", responseId);
					}
					if (notification.getPostId() != null && !notification.getPostId().equals("") && notification.getPostId().length() <= 32) {
						String responsePostId = myFilter.filterHtml(notification.getPostId());
						responsePostId = myFilter.stripXSS(responsePostId);
						responseJson.put("PostId", responsePostId);
					}
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
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return  WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 刪除通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            通報
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/a01/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");

			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (notificationService.delete(id)) {
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
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return  WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 審核通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/a01/examine", method = RequestMethod.POST)
	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
			int preStatus = obj.isNull("PreStatus") == true ? 0 : obj.getInt("PreStatus");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");
			int ceffectLevel = obj.isNull("CeffectLevel") == true ? 0 : obj.getInt("CeffectLevel");
			int ieffectLevel = obj.isNull("IeffectLevel") == true ? 0 : obj.getInt("IeffectLevel");
			int aeffectLevel = obj.isNull("AeffectLevel") == true ? 0 : obj.getInt("AeffectLevel");
			int effectLevel = obj.isNull("EffectLevel") == true ? 0 : obj.getInt("EffectLevel");
//			System.out.println("PreStatus:" + preStatus);
//			System.out.println("Status:" + status);

			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if ((preStatus == 33 && status == 4) || (preStatus == 53 && status == 6)) {
					Notification notification_update = notificationService.updateStep3(getBaseMemberId(), json);
					if (notification_update == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}
				}
 				if (preStatus == 4 || preStatus == 9) { 					
 					obj.put("IsReview5", false);			
 					obj.put("IsCC5", false);
 					Member member = memberService.get(notificationService.getById(id).getCreateId());
 					List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
 					List<Org> orgs = orgService.getAll();
 					if (orgSigns != null) {
 						for (OrgSign orgSign : orgSigns) {
 							if (orgs != null) {
 								for (Org org : orgs) {
 									if (orgSign.getParentOrgId().equals(org.getId()) && org.getIsEnable()) { 										 										
 										if (orgSign.getNotificationClosingIsExamine().equals(1) || orgSign.getNotificationClosingIsExamine().equals(3)) {
 											if (org.getAuthType().equals("2"))
 												obj.put("IsReview5", true);																			
 										}
 										if (orgSign.getNotificationClosingIsExamine().equals(2) || orgSign.getNotificationClosingIsExamine().equals(3)) {
 											if (org.getAuthType().equals("2"))
 												obj.put("IsCC5", true);																				
 										}
 									}
 								}
 							}
 						}
 					}
					Notification notification_update = notificationService.updateStep6(getBaseMemberId(), obj.toString());
					if (notification_update == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}
				}
				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, id);
				

				// debug
//				System.out.println("==========================================================");
//				System.out.println("a01_NotificationController.java → Examine() → json：" + json.toString());
//				System.out.println("a01_NotificationController.java → Examine() → baseMemberName：" + baseMemberName);
//				System.out.println("a01_NotificationController.java → Examine() → baseMemberId：" + baseMemberId);
//				System.out.println("a01_NotificationController.java → Examine() → id：" + id);
//				System.out.println("a01_NotificationController.java → Examine() → status：" + status);
//				System.out.println("a01_NotificationController.java → Examine() → preStatus：" + preStatus);
//				System.out.println("a01_NotificationController.java → Examine() → opinion：" + opinion);
				
				// 與 HCERT 串接				
				// 事故影響等級 0: 無需通報
				
				if (effectLevel > 0) {
					if (status == 4) {
						// 初報
						notificationExchangeNcertService.exportToNcertStep1(id, opinion);
					} 
					
					if (status == 6) {
						// 續報
						notificationExchangeNcertService.exportToNcertStep3(id, status);
						// 1、2級或3、4級結報
						notificationExchangeNcertService.exportToNcertStep4And5(id);
					}
				}
				//通報單等級加入保留記錄功能
				JSONObject level_json = new JSONObject();
				level_json.put("CeffectLevel", ceffectLevel);
				level_json.put("IeffectLevel", ieffectLevel);
				level_json.put("AeffectLevel", aeffectLevel);
				level_json.put("EffectLevel", effectLevel);
				level_json.put("NotificationId", id);	
				NotificationLog notificationLog = notificationLogService.insert(getBaseMemberId(), level_json.toString());
				
				Notification notification = notificationService.examine(getBaseMemberId(), id, status, preStatus, opinion, ceffectLevel, ieffectLevel, aeffectLevel, effectLevel);
				if (notification != null && notificationLog !=null && processLog != null) {
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
		return  WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 取得通報button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 通報button count資料
	 */
	@RequestMapping(value = "/a01/query/button/count", method = RequestMethod.POST)
	public String QueryButtonCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			/*
			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
			 * 10-會員機構聯絡人 11-會員機構管理者
			 */
			if (baseMemberRole.IsHisac == true) // 2-H-ISAC管理者
			{
				obj.put("RoleId", 2);
			} else if (baseMemberRole.IsHisacNotifySign == true) // 12-HISAC通報審核者
			{
				obj.put("RoleId", 12);
			} else if (baseMemberRole.IsApplySingAdmin == true) // 15-權責單位通報審核者
			{
				obj.put("RoleId", 15);
			} else if (baseMemberRole.IsMemberContact == true) /* 10-會員機構聯絡人 */
			{
				obj.put("RoleId", 10);
			} else if (baseMemberRole.IsAdmin == true) /* 1-SuperAdmin */
			{
				obj.put("RoleId", 1);
			}

			obj.put("OrgId", getBaseOrgId());
			obj.put("MemberId", getBaseMemberId());
			json = obj.toString();

			JSONArray sn_array = new JSONArray();
			List<SpButtonCount> spButtonCounts = notificationService.getSpButtonCount(json);
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

		model.addAttribute("json", WebCrypto.getSafe(listjson.toString()));
		return "msg";

	}
	/**
	 * 轉警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 轉警訊動作是否成功
	 */
	@RequestMapping(value = "/a01/toMessage", method = RequestMethod.POST)
	public @ResponseBody String ToMessage(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Notification notification = notificationService.getById(id);
				JSONObject sn_json = new JSONObject();
				String subject = "";
				sn_json.put("TransferInType", 6);
				sn_json.put("TransferInId", id);
				sn_json.put("SourceCode", "HISAC");
				sn_json.put("ExternalId", notification.getPostId());
				sn_json.put("FindDate", notification.getApplyDateTime());
				sn_json.put("Description", notification.getEventRemark());
				switch (notification.getEventType()) {
					case 1 :
						subject = "H-ISAC 網頁攻擊(";
						if (notification.getIsEventType1Opt1())
							subject = subject + " 網頁置換";
						if (notification.getIsEventType1Opt2())
							subject = subject + " 惡意留言";
						if (notification.getIsEventType1Opt3())
							subject = subject + " 惡意網頁";
						if (notification.getIsEventType1Opt4())
							subject = subject + " 釣魚網頁";
						if (notification.getIsEventType1Opt5())
							subject = subject + " 網頁木馬";
						if (notification.getIsEventType1Opt6())
							subject = subject + " 網站個資外洩";
						subject = subject + ")";
						sn_json.put("AlertCode", "DEF");
						break;
					case 2 :
						subject = "H-ISAC 非法入侵(";
						if (notification.getIsEventType2Opt1())
							subject = subject + " 系統遭入侵";
						if (notification.getIsEventType2Opt2())
							subject = subject + " 植入惡意程式";
						if (notification.getIsEventType2Opt3())
							subject = subject + " 異常連線";
						if (notification.getIsEventType2Opt4())
							subject = subject + " 發送垃圾郵件";
						if (notification.getIsEventType2Opt5())
							subject = subject + " 資料外洩";
						subject = subject + ")";
						sn_json.put("AlertCode", "INT");
						break;
					case 3 :
						subject = "H-ISAC 阻斷服務(";
						if (notification.getIsEventType3Opt1())
							subject = subject + " 服務中斷";
						if (notification.getIsEventType3Opt2())
							subject = subject + " 效能降低";
						subject = subject + ")";
						sn_json.put("AlertCode", "OTH");
						break;
					case 4 :
						subject = "H-ISAC 設備異常(";
						if (notification.getIsEventType4Opt1())
							subject = subject + " 設備毀損";
						if (notification.getIsEventType4Opt2())
							subject = subject + " 電力異常";
						if (notification.getIsEventType4Opt3())
							subject = subject + " 網路服務中斷";
						if (notification.getIsEventType4Opt4())
							subject = subject + " 設備遺失";
						subject = subject + ")";
						sn_json.put("AlertCode", "OTH");
						break;
					case 5 :
						subject = "H-ISAC 其他(" + notification.getEventType5Other() + ")";
						sn_json.put("AlertCode", "OTH");
						break;
				}
				sn_json.put("Subject", subject);
				sn_json.put("IsEnable", true);
				sn_json.put("Status", "1");
				sn_json.put("TableName", "message");
				sn_json.put("Pre", "HISAC-MES");
				String str_json = sn_json.toString();
				Message message = messageService.insert(getBaseMemberId(), str_json, false);
				if (message != null) {
					JSONObject sn_json_attach = new JSONObject();
					sn_json_attach.put("MessageId", message.getId()); // MessageId
					String json_attach = sn_json_attach.toString();
					// 新增檔案
					if (messagePostAttachService.insertFileDesc(getBaseMemberId(), json_attach) && messagePostAttachService.insertFileDesc(getBaseMemberId(), json_attach) && messagePostAttachService.insertFileDesc(getBaseMemberId(), json_attach)) {
						Notification entity = notificationService.updateTransferOut(getBaseMemberId(), id, 6, message.getId());
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
							responseJson.put("success", true);
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

						}

					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 轉情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 轉情資動作是否成功
	 */
	@RequestMapping(value = "/a01/toInformation", method = RequestMethod.POST)
	public @ResponseBody String ToInformation(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Notification notification = notificationService.getById(id);
				JSONObject sn_json = new JSONObject();
				String incidentTitle = "";
				sn_json.put("TransferInType", 7);
				sn_json.put("TransferInId", id);
				sn_json.put("SourceCode", "HISAC");
				sn_json.put("IncidentId", notification.getPostId());
				sn_json.put("IncidentDiscoveryTime", notification.getApplyDateTime());
				sn_json.put("Description", notification.getEventRemark());
				sn_json.put("ReporterName", "HISAC");
				sn_json.put("ResourcesSourceIpAddress", notification.getIpExternal());
				switch (notification.getEventType()) {
					case 1 :
						incidentTitle = "H-ISAC 網頁攻擊(";
						if (notification.getIsEventType1Opt1())
							incidentTitle = incidentTitle + " 網頁置換";
						if (notification.getIsEventType1Opt2())
							incidentTitle = incidentTitle + " 惡意留言";
						if (notification.getIsEventType1Opt3())
							incidentTitle = incidentTitle + " 惡意網頁";
						if (notification.getIsEventType1Opt4())
							incidentTitle = incidentTitle + " 釣魚網頁";
						if (notification.getIsEventType1Opt5())
							incidentTitle = incidentTitle + " 網頁木馬";
						if (notification.getIsEventType1Opt6())
							incidentTitle = incidentTitle + " 網站個資外洩";
						incidentTitle = incidentTitle + ")";
						sn_json.put("StixTitle", "DEF");
						break;
					case 2 :
						incidentTitle = "H-ISAC 非法入侵(";
						if (notification.getIsEventType2Opt1())
							incidentTitle = incidentTitle + " 系統遭入侵";
						if (notification.getIsEventType2Opt2())
							incidentTitle = incidentTitle + " 植入惡意程式";
						if (notification.getIsEventType2Opt3())
							incidentTitle = incidentTitle + " 異常連線";
						if (notification.getIsEventType2Opt4())
							incidentTitle = incidentTitle + " 發送垃圾郵件";
						if (notification.getIsEventType2Opt5())
							incidentTitle = incidentTitle + " 資料外洩";
						incidentTitle = incidentTitle + ")";
						sn_json.put("StixTitle", "INT");
						break;
					case 3 :
						incidentTitle = "H-ISAC 阻斷服務(";
						if (notification.getIsEventType3Opt1())
							incidentTitle = incidentTitle + " 服務中斷";
						if (notification.getIsEventType3Opt2())
							incidentTitle = incidentTitle + " 效能降低";
						incidentTitle = incidentTitle + ")";
						sn_json.put("StixTitle", "OTH");
						break;
					case 4 :
						incidentTitle = "H-ISAC 設備異常(";
						if (notification.getIsEventType4Opt1())
							incidentTitle = incidentTitle + " 設備毀損";
						if (notification.getIsEventType4Opt2())
							incidentTitle = incidentTitle + " 電力異常";
						if (notification.getIsEventType4Opt3())
							incidentTitle = incidentTitle + " 網路服務中斷";
						if (notification.getIsEventType4Opt4())
							incidentTitle = incidentTitle + " 設備遺失";
						incidentTitle = incidentTitle + ")";
						sn_json.put("StixTitle", "OTH");
						break;
					case 5 :
						incidentTitle = "H-ISAC 其他(" + notification.getEventType5Other() + ")";
						sn_json.put("StixTitle", "OTH");
						break;
				}
				sn_json.put("IncidentTitle", incidentTitle);
				sn_json.put("Status", 1);
				sn_json.put("TableName", "information_exchange");
				sn_json.put("Pre", "HISAC-INFO");
				String str_json = sn_json.toString();
				InformationExchange informationExchange = informationExchangeService.insert(getBaseMemberId(), str_json);
				if (informationExchange != null) {
					Notification entity = notificationService.updateTransferOut(getBaseMemberId(), id, 7, informationExchange.getId());
					if (entity != null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 取得資安廠商資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            json
	 * @return 資安廠商資料
	 */
	@RequestMapping(value = "/a01/query/handleInformation", method = RequestMethod.POST)
	public String QueryHandleInformation(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {		
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			List<HandleInformation> handleInformations = handleInformationService.getAll(); 
			if (handleInformations != null) {
				for (HandleInformation handleInformation : handleInformations) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", handleInformation.getId());		
					sn_json.put("Name", handleInformation.getName());		
					sn_json.put("Section", handleInformation.getSection());		
					sn_json.put("ContactInfo", handleInformation.getContactInfo());	
					sn_json.put("ServiceItems", handleInformation.getServiceItems());	
					sn_json.put("FileName", handleInformation.getFileName());			
					sn_json.put("Remark", handleInformation.getRemark());			
					sn_array.put(sn_json);
				}
			}
						
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		}
		model.addAttribute("json", WebCrypto.getSafe(sn_array.toString()));
		return "msg";
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
	 * @param newsManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/a01/attach/download/handleInformation/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response,  @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();			
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			HandleInformation handleInformation = handleInformationService.get(id);
				try {
					byte[] buffer = handleInformation.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(newsManagementAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(handleInformation.getFileName()));
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
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}
	
	
	
	/**
	 * alert API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 稽催是否成功
	 */
	@RequestMapping(value = "/a01/alert", method = RequestMethod.POST)
	public @ResponseBody String Alert(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getSignPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {				
				Notification notification = notificationService.getById(id);
				Member member = memberService.get(notification.getCreateId());
				Integer hour = (int)(new Date().getTime() - notification.getModifyTime().getTime())/(60*60*1000);
				Integer min = (int)(new Date().getTime() - notification.getModifyTime().getTime())/(60*1000) - hour*60;				
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotificationAlertSubject"), notification.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotificationAlertBody"), member.getName(), notification.getPostId(), hour.toString() + "小時" + min.toString() + "分鐘");
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				responseJson.put("msg", "稽催成功");
				responseJson.put("success", true);
			} 		
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * chooseHandleInformation API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否成功
	 */
	@RequestMapping(value = "/a01/chooseHandleInformation", method = RequestMethod.POST)
	public @ResponseBody String ChooseHandleInformation(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		Long handleInformationId = obj.isNull("HandleInformationId") == true ? 0 : obj.getLong("HandleInformationId");
		boolean isHandledByMyself  = obj.isNull("IsHandledByMyself") == true ? true : obj.getBoolean("IsHandledByMyself");
		if (menuService.getSignPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!notificationService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				return responseJson.toString();
			} else {				
				Notification notification = notificationService.chooseHandleInformation(id, handleInformationId, isHandledByMyself);
				
				
				//事件單
				// 流程紀錄用 - 開始	
								
				Boolean check = true;				
				Long status = (long) 2;				
				Long reporterName = notification.getContactorUnit();	
				Long handleName = handleInformationService.get(handleInformationId).getContactorId();
				List<Member> members = memberService.getByOrgId(handleName);							
				Long contactorId = members.get(0).getId();
				String contactorTel =members.get(0).getCityPhone();
				String contactorFax =members.get(0).getFaxPhone();
				String contactorEmail =members.get(0).getEmail();
				JSONObject sn_incident = new JSONObject();
				sn_incident.put("Status", 2);
				sn_incident.put("PostDateTime", new Date());
				sn_incident.put("ReporterName", reporterName);
				sn_incident.put("HandleName", handleName);
				sn_incident.put("ContactorId", contactorId);
				sn_incident.put("ContactorTel", contactorTel);
				sn_incident.put("ContactorFax", contactorFax);
				sn_incident.put("ContactorEmail", contactorEmail);
				sn_incident.put("IncidentDiscoveryTime", notification.getEventDateTime());
				sn_incident.put("HostAmount", notification.getHostAmount()); 
				sn_incident.put("IsOsOpt1", notification.getIsOsOpt1()); 
				sn_incident.put("IsOsOpt2", notification.getIsOsOpt2()); 
				sn_incident.put("IsOsOpt3", notification.getIsOsOpt3()); 
				sn_incident.put("IsOsOpt3Other", notification.getIsOsOpt3Other()); 
				sn_incident.put("EventType", notification.getEventType()); 
				sn_incident.put("IsEventType1Opt1", notification.getIsEventType1Opt1());
				sn_incident.put("IsEventType1Opt2", notification.getIsEventType1Opt2());
				sn_incident.put("IsEventType1Opt3", notification.getIsEventType1Opt3());
				sn_incident.put("IsEventType1Opt4", notification.getIsEventType1Opt4());
				sn_incident.put("IsEventType1Opt5", notification.getIsEventType1Opt5());
				sn_incident.put("IsEventType1Opt6", notification.getIsEventType1Opt6());		
				sn_incident.put("IsEventType2Opt1", notification.getIsEventType2Opt1());		
				sn_incident.put("IsEventType2Opt2", notification.getIsEventType2Opt2());		
				sn_incident.put("IsEventType2Opt3", notification.getIsEventType2Opt3());		
				sn_incident.put("IsEventType2Opt4", notification.getIsEventType2Opt4());		
				sn_incident.put("IsEventType2Opt5", notification.getIsEventType2Opt5());		
				sn_incident.put("IsEventType3Opt1", notification.getIsEventType3Opt1());		
				sn_incident.put("IsEventType3Opt2", notification.getIsEventType3Opt2());		      
				sn_incident.put("IsEventType4Opt1", notification.getIsEventType4Opt1());
				sn_incident.put("IsEventType4Opt2", notification.getIsEventType4Opt2());
				sn_incident.put("IsEventType4Opt3", notification.getIsEventType4Opt3());
				sn_incident.put("IsEventType4Opt4", notification.getIsEventType4Opt4());
				sn_incident.put("EventType5Other", notification.getEventType5Other());
				sn_incident.put("EventRemark", notification.getNeedSupportRemark());	
				
				sn_incident.put("FinishDoOther", notification.getFinishDoOther());
				sn_incident.put("FinishDateTime", notification.getFinishDateTime());
				sn_incident.put("PreStatus", "1");			
				sn_incident.put("TableName", "incident");			
				sn_incident.put("Pre", "HCERT");	
				sn_incident.put("NotificationId", notification.getId());
				sn_incident.put("NotificationIsCC3", true);
								
				
				
				// 依 check 判斷是否要更新事件單編號(將暫存單號改成正式單號)
				Incident incident = incidentService.insert(getBaseMemberId(), sn_incident.toString(), check);								
				
				// 1-編輯中 → 2-事件處理中
				if (status.equals(new Long(2))) {								
						
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
					
				processLogService.insert(getBaseMemberId(), json, String.valueOf(incident.getId()));
				
				
				if (notification != null && incident != null) {
					responseJson.put("msg", "選擇廠商成功");
					responseJson.put("success", true);
				}
				else {
					responseJson.put("msg", "選擇廠商失敗");
					responseJson.put("success", false);
				}
			} 		
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 取得supportNotes API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            json
	 * @return supportNotes
	 */
	@RequestMapping(value = "/a01/query/supportNotes", method = RequestMethod.POST)
	public String QuerySupportNotes(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {				
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
						
			JSONObject responseJson = new JSONObject();
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());			
			responseJson.put("supportNotes", resourceMessageService.getMessageValue("supportNotes"));
			
			model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));

		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());			
		}		
		return "msg";
	}
	
	
	/**
	 * 取得handle_information_remark API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            json
	 * @return handle_information_remark
	 */
	@RequestMapping(value = "/a01/query/handleInformationRemark", method = RequestMethod.POST)
	public String QueryHandleInformationRemark(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {				
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
						
			JSONObject responseJson = new JSONObject();
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());			
			responseJson.put("handle_information_remark", resourceMessageService.getMessageValue("handle_information_remark"));			
			model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());			
		}		
		return "msg";
	}
	

	/**
	 * 查詢OT資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            組織Id,組織金鑰,
	 *         
	 * @return ot 查詢資料
	 */
	
	@RequestMapping(value = "/a01/ires/getListAssetsRiskOTForBulletin", method = RequestMethod.POST)
	public String QueryOTAsset(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {	
		
		
		JSONObject sn_json = new JSONObject(json);
		
		
		String assetClass = sn_json.isNull("AssetClass") == true ? "" : sn_json.getString("AssetClass");

		
		String brand = sn_json.isNull("BrandKeyword") == true ? ""  : sn_json.getString("BrandKeyword");


		JSONObject responseJson = new JSONObject();
		Org org = orgService.getDataById(getBaseOrgId());
		String orgCode = org.getCode();
		String apiKey = org.getApiKey();
		
		
		JSONObject response = new JSONObject();


		response =  iresApiService.GetListAssetsRiskOTForBulletin(orgCode, apiKey, assetClass, brand);
		
		
		if(response.getString("Status").equals("200")) {
			
			
			JSONArray datatable = response.getJSONArray("msg");
			
			responseJson.put("success", true);
			responseJson.put("datatable",datatable);

			
		}else {
			JSONArray datatable = new JSONArray();
			responseJson.put("success", false);
			responseJson.put("msg", "查無OT資料");
			
			responseJson.put("datatable",datatable);

		}
		
		

		
		systemLogService.insert(baseControllerName, baseActionName, getBaseOrgId().toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, baseIpAddress, getBaseMemberAccount());
		
		model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));
		return "msg";
		
		
	}
	
	
	
	
	
	
	
	
	
	

}