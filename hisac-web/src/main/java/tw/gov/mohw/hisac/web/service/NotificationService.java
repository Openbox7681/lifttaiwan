package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.NotificationDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;

import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNotification;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;

/**
 * 通報管理服務
 */
@Service
public class NotificationService {
	
	@Autowired
	NotificationDAO notificationDAO;
	@Autowired
	ProcessLogService processLogService; 
	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	MemberService memberService;
	@Autowired
	OrgService orgService;
	@Autowired
	private MailService mailService;
	@Autowired
	private MemberRoleService memberRoleService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	protected OrgSignService orgSignService;
	@Autowired
	private SmsService smsService;
	

	/**
	 * 取得所有通報資料
	 * 
	 * @return 通報資料
	 */
	public List<Notification> getAll() {
		return notificationDAO.getAll();
	}

	/**
	 * 取得通報資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	public List<SpNotification> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得通報button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得通報資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得通報資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得通報資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	public List<Notification> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得通報資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 通報資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增通報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            通報資料
	 * @return 通報資料
	 */
	public Notification insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date applyDateTime = new Date();
			String strApplyDateTime = obj.isNull("ApplyDateTime") == true ? null : obj.getString("ApplyDateTime");
			if (strApplyDateTime != null && !strApplyDateTime.equals(""))
				applyDateTime = WebDatetime.parse(strApplyDateTime);

			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String postId = ticketQueueDAO.insertPostId(tableName, false, pre, orgService.getDataById(memberService.get(memberId).getOrgId()).getCode());
			long contactorUnit = obj.isNull("ContactorUnit") == true ? 0 : obj.getLong("ContactorUnit");
			long mainUnit1 = obj.isNull("MainUnit1") == true ? 0 : obj.getLong("MainUnit1");
			long mainUnit2 = obj.isNull("MainUnit2") == true ? 0 : obj.getLong("MainUnit2");
			long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");
			String contactorTel = obj.isNull("ContactorTel") == true ? null : obj.getString("ContactorTel");
			String contactorFax = obj.isNull("ContactorFax") == true ? null : obj.getString("ContactorFax");
			String contactorEmail = obj.isNull("ContactorEmail") == true ? null : obj.getString("ContactorEmail");
			Boolean isSub = obj.isNull("IsSub") == true ? false : obj.getBoolean("IsSub");
			String isSubUnitName = obj.isNull("IsSubUnitName") == true ? null : obj.getString("IsSubUnitName");
			Date eventDateTime = new Date();
			String strEventDateTime = obj.isNull("EventDateTime") == true ? null : obj.getString("EventDateTime");
			if (strEventDateTime != null && !strEventDateTime.equals(""))
				eventDateTime = WebDatetime.parse(strEventDateTime);

			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isFinishDoSysOptRemark = obj.isNull("IsFinishDoSysOptRemark") == true ? false : obj.getBoolean("IsFinishDoSysOptRemark");
			Boolean isFinishDoEduOptRemark = obj.isNull("IsFinishDoEduOptRemark") == true ? false : obj.getBoolean("IsFinishDoEduOptRemark");
			String finishDoSysOptRemark = obj.isNull("FinishDoSysOptRemark") == true ? null : obj.getString("FinishDoSysOptRemark");
			String finishDoEduOptRemark = obj.isNull("FinishDoEduOptRemark") == true ? null : obj.getString("FinishDoEduOptRemark");
			Boolean isHandledByMyself = obj.isNull("IsHandledByMyself") == true ? false : obj.getBoolean("IsHandledByMyself");
			Long handleInformationId = obj.isNull("HandleInformationId") == true ? 0 : obj.getLong("HandleInformationId");
			
			Boolean isTest = obj.isNull("IsTest") == true ? false : obj.getBoolean("IsTest");
			
			String ipExternal = obj.isNull("IpExternal") == true ? null : obj.getString("IpExternal");
			String ipInternal = obj.isNull("IpInternal") == true ? null : obj.getString("IpInternal");
			String webUrl = obj.isNull("WebUrl") == true ? null : obj.getString("WebUrl");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");
			Boolean isGuardOpt1 = obj.isNull("IsGuardOpt1") == true ? false : obj.getBoolean("IsGuardOpt1");
			Boolean isGuardOpt2 = obj.isNull("IsGuardOpt2") == true ? false : obj.getBoolean("IsGuardOpt2");
			Boolean isGuardOpt3 = obj.isNull("IsGuardOpt3") == true ? false : obj.getBoolean("IsGuardOpt3");
			Boolean isGuardOpt4 = obj.isNull("IsGuardOpt4") == true ? false : obj.getBoolean("IsGuardOpt4");
			String isGuardOpt4Other = obj.isNull("IsGuardOpt4Other") == true ? null : obj.getString("IsGuardOpt4Other");
			int socOpt = obj.isNull("SocOpt") == true ? 0 : obj.getInt("SocOpt");
			String socOptCompany = obj.isNull("SocOptCompany") == true ? null : obj.getString("SocOptCompany");
			Boolean isIsms = obj.isNull("IsIsms") == true ? false : obj.getBoolean("IsIsms");
			String maintainCompany = obj.isNull("MaintainCompany") == true ? null : obj.getString("MaintainCompany");

			// step2
			Integer ceffectLevel = obj.isNull("CeffectLevel") == true ? null : obj.getInt("CeffectLevel");
			Integer ieffectLevel = obj.isNull("IeffectLevel") == true ? null : obj.getInt("IeffectLevel");
			Integer aeffectLevel = obj.isNull("AeffectLevel") == true ? null : obj.getInt("AeffectLevel");
			Integer effectLevel = obj.isNull("EffectLevel") == true ? null : obj.getInt("EffectLevel");
			// step3
			int eventType = obj.isNull("EventType") == true ? 0 : obj.getInt("EventType");
			Boolean isEventType1Opt1 = obj.isNull("IsEventType1Opt1") == true ? false : obj.getBoolean("IsEventType1Opt1");
			Boolean isEventType1Opt2 = obj.isNull("IsEventType1Opt2") == true ? false : obj.getBoolean("IsEventType1Opt2");
			Boolean isEventType1Opt3 = obj.isNull("IsEventType1Opt3") == true ? false : obj.getBoolean("IsEventType1Opt3");
			Boolean isEventType1Opt4 = obj.isNull("IsEventType1Opt4") == true ? false : obj.getBoolean("IsEventType1Opt4");
			Boolean isEventType1Opt5 = obj.isNull("IsEventType1Opt5") == true ? false : obj.getBoolean("IsEventType1Opt5");
			Boolean isEventType1Opt6 = obj.isNull("IsEventType1Opt6") == true ? false : obj.getBoolean("IsEventType1Opt6");
			Boolean isEventType2Opt1 = obj.isNull("IsEventType2Opt1") == true ? false : obj.getBoolean("IsEventType2Opt1");
			Boolean isEventType2Opt2 = obj.isNull("IsEventType2Opt2") == true ? false : obj.getBoolean("IsEventType2Opt2");
			Boolean isEventType2Opt3 = obj.isNull("IsEventType2Opt3") == true ? false : obj.getBoolean("IsEventType2Opt3");
			Boolean isEventType2Opt4 = obj.isNull("IsEventType2Opt4") == true ? false : obj.getBoolean("IsEventType2Opt4");
			Boolean isEventType2Opt5 = obj.isNull("IsEventType2Opt5") == true ? false : obj.getBoolean("IsEventType2Opt5");
			Boolean isEventType3Opt1 = obj.isNull("IsEventType3Opt1") == true ? false : obj.getBoolean("IsEventType3Opt1");
			Boolean isEventType3Opt2 = obj.isNull("IsEventType3Opt2") == true ? false : obj.getBoolean("IsEventType3Opt2");
			Boolean isEventType4Opt1 = obj.isNull("IsEventType4Opt1") == true ? false : obj.getBoolean("IsEventType4Opt1");
			Boolean isEventType4Opt2 = obj.isNull("IsEventType4Opt2") == true ? false : obj.getBoolean("IsEventType4Opt2");
			Boolean isEventType4Opt3 = obj.isNull("IsEventType4Opt3") == true ? false : obj.getBoolean("IsEventType4Opt3");
			Boolean isEventType4Opt4 = obj.isNull("IsEventType4Opt4") == true ? false : obj.getBoolean("IsEventType4Opt4");
			String eventType5Other = obj.isNull("EventType5Other") == true ? null : obj.getString("EventType5Other");
			String eventRemark = obj.isNull("EventRemark") == true ? null : obj.getString("EventRemark");
			Boolean isAffectOthers = obj.isNull("IsAffectOthers") == true ? false : obj.getBoolean("IsAffectOthers");
			Boolean affectOther1 = obj.optBoolean("AffectOther1", false);
			Boolean affectOther2 = obj.optBoolean("AffectOther2", false);
			Boolean affectOther3 = obj.optBoolean("AffectOther3", false);
			Boolean affectOther4 = obj.optBoolean("AffectOther4", false);
			Boolean affectOther5 = obj.optBoolean("AffectOther5", false);
			Boolean affectOther6 = obj.optBoolean("AffectOther6", false);
			Boolean affectOther7 = obj.optBoolean("AffectOther7", false);
			Boolean affectOther8 = obj.optBoolean("AffectOther8", false);
			int eventSource = obj.isNull("EventSource") == true ? 0 : obj.getInt("EventSource");
			String eventSourceNo = obj.isNull("EventSourceNo") == true ? null : obj.getString("EventSourceNo");
			String eventSourceOther = obj.optString("EventSourceOther", null);
			// step4-all
			String resDescription = obj.optString("ResDescription", null);
			Date resControlTime = new Date();
			String strResControlTime = obj.optString("ResControlTime", null);
			if (strResControlTime != null && !strResControlTime.equals(""))
				resControlTime = WebDatetime.parse(strResControlTime);
			int resResult = obj.optInt("ResResult", 0);
			// step4-1
			Boolean isRes1LogOpt1 = obj.isNull("IsRes1LogOpt1") == true ? false : obj.getBoolean("IsRes1LogOpt1");
			Boolean isRes1LogOpt2 = obj.isNull("IsRes1LogOpt2") == true ? false : obj.getBoolean("IsRes1LogOpt2");
			Boolean isRes1LogOpt5 = obj.optBoolean("IsRes1LogOpt5", false);
			Boolean isRes1LogOpt3 = obj.isNull("IsRes1LogOpt3") == true ? false : obj.getBoolean("IsRes1LogOpt3");
			Boolean isRes1LogOpt4 = obj.isNull("IsRes1LogOpt4") == true ? false : obj.getBoolean("IsRes1LogOpt4");
			int res1LogOpt1 = obj.isNull("Res1LogOpt1") == true ? 0 : obj.getInt("Res1LogOpt1");
			String res1LogOpt1Other = obj.isNull("Res1LogOpt1Other") == true ? null : obj.getString("Res1LogOpt1Other");
			int res1LogOpt2 = obj.isNull("Res1LogOpt2") == true ? 0 : obj.getInt("Res1LogOpt2");
			String res1LogOpt2Other = obj.isNull("Res1LogOpt2Other") == true ? null : obj.getString("Res1LogOpt2Other");
			int res1LogOpt5 = obj.optInt("Res1LogOpt5", 0);
			String res1LogOpt5Other = obj.optString("Res1LogOpt5Other", null);
			int res1LogOpt3Amount = obj.isNull("Res1LogOpt3Amount") == true ? 0 : obj.getInt("Res1LogOpt3Amount");
			String res1LogOpt4Remark = obj.isNull("Res1LogOpt4Remark") == true ? null : obj.getString("Res1LogOpt4Remark");
			Boolean isRes1EvaOpt1 = obj.isNull("IsRes1EvaOpt1") == true ? false : obj.getBoolean("IsRes1EvaOpt1");
			Boolean isRes1EvaOpt2 = obj.isNull("IsRes1EvaOpt2") == true ? false : obj.getBoolean("IsRes1EvaOpt2");
			Boolean isRes1EvaOpt3 = obj.isNull("IsRes1EvaOpt3") == true ? false : obj.getBoolean("IsRes1EvaOpt3");
			Boolean isRes1EvaOpt4 = obj.isNull("IsRes1EvaOpt4") == true ? false : obj.getBoolean("IsRes1EvaOpt4");
			Boolean isRes1EvaOpt5 = obj.isNull("IsRes1EvaOpt5") == true ? false : obj.getBoolean("IsRes1EvaOpt5");
			Boolean isRes1EvaOpt6 = obj.isNull("IsRes1EvaOpt6") == true ? false : obj.getBoolean("IsRes1EvaOpt6");
			Boolean isRes1EvaOpt7 = obj.isNull("IsRes1EvaOpt7") == true ? false : obj.getBoolean("IsRes1EvaOpt7");
			Boolean isRes1EvaOpt8 = obj.isNull("IsRes1EvaOpt8") == true ? false : obj.getBoolean("IsRes1EvaOpt8");
			String res1EvaOpt1Remark = obj.isNull("Res1EvaOpt1Remark") == true ? null : obj.getString("Res1EvaOpt1Remark");
			String res1EvaOpt2Remark = obj.isNull("Res1EvaOpt2Remark") == true ? null : obj.getString("Res1EvaOpt2Remark");
			String res1EvaOpt3Remark = obj.isNull("Res1EvaOpt3Remark") == true ? null : obj.getString("Res1EvaOpt3Remark");
			String res1EvaOpt4Remark = obj.isNull("Res1EvaOpt4Remark") == true ? null : obj.getString("Res1EvaOpt4Remark");
			int res1EvaOpt6Amount = obj.isNull("Res1EvaOpt6Amount") == true ? 0 : obj.getInt("Res1EvaOpt6Amount");
			String res1EvaOpt6Remark = obj.optString("Res1EvaOpt6Remark", null);
			int res1EvaOpt6Type = obj.isNull("Res1EvaOpt6Type") == true ? 0 : obj.getInt("Res1EvaOpt6Type");
			String res1EvaOpt6TypeOpt3Other = obj.isNull("Res1EvaOpt6TypeOpt3Other") == true ? null : obj.getString("Res1EvaOpt6TypeOpt3Other");
			int res1EvaOpt7Amount = obj.isNull("Res1EvaOpt7Amount") == true ? 0 : obj.getInt("Res1EvaOpt7Amount");
			String res1EvaOpt7Remark = obj.isNull("Res1EvaOpt7Remark") == true ? null : obj.getString("Res1EvaOpt7Remark");
			String res1EvaOpt8Remark = obj.isNull("Res1EvaOpt8Remark") == true ? null : obj.getString("Res1EvaOpt8Remark");

			Boolean isRes1DoOpt1 = obj.isNull("IsRes1DoOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt1");
			Boolean isRes1DoOpt2 = obj.isNull("IsRes1DoOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt2");
			Boolean isRes1DoOpt3 = obj.isNull("IsRes1DoOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt3");
			Boolean isRes1DoOpt4 = obj.isNull("IsRes1DoOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt4");
			Boolean isRes1DoOpt5 = obj.isNull("IsRes1DoOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt5");
			Boolean isRes1DoOpt6 = obj.isNull("IsRes1DoOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt6");
			Boolean isRes1DoOpt7 = obj.isNull("IsRes1DoOpt7") == true ? false : obj.getBoolean("IsRes1DoOpt7");
			Boolean isRes1DoOpt8 = obj.isNull("IsRes1DoOpt8") == true ? false : obj.getBoolean("IsRes1DoOpt8");
			Boolean isRes1DoOpt9 = obj.isNull("IsRes1DoOpt9") == true ? false : obj.getBoolean("IsRes1DoOpt9");
			Boolean isRes1DoOpt10 = obj.isNull("IsRes1DoOpt10") == true ? false : obj.getBoolean("IsRes1DoOpt10");
			Boolean isRes1DoOpt11 = obj.isNull("IsRes1DoOpt11") == true ? false : obj.getBoolean("IsRes1DoOpt11");
			Boolean isRes1DoOpt12 = obj.isNull("IsRes1DoOpt12") == true ? false : obj.getBoolean("IsRes1DoOpt12");
			int res1DoOpt1Amount = obj.isNull("Res1DoOpt1Amount") == true ? 0 : obj.getInt("Res1DoOpt1Amount");
			String res1DoOpt2Remark = obj.isNull("Res1DoOpt2Remark") == true ? null : obj.getString("Res1DoOpt2Remark");
			String res1DoOpt3Remark = obj.isNull("Res1DoOpt3Remark") == true ? null : obj.getString("Res1DoOpt3Remark");
			String res1DoOpt4Remark = obj.isNull("Res1DoOpt4Remark") == true ? null : obj.getString("Res1DoOpt4Remark");
			int res1DoOpt5Amount = obj.isNull("Res1DoOpt5Amount") == true ? 0 : obj.getInt("Res1DoOpt5Amount");
			Boolean isRes1DoOpt9EngineOpt1 = obj.isNull("IsRes1DoOpt9EngineOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt1");
			Boolean isRes1DoOpt9EngineOpt2 = obj.isNull("IsRes1DoOpt9EngineOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt2");
			Boolean isRes1DoOpt9EngineOpt3 = obj.isNull("IsRes1DoOpt9EngineOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt3");
			Boolean isRes1DoOpt9EngineOpt4 = obj.isNull("IsRes1DoOpt9EngineOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt4");
			Boolean isRes1DoOpt9EngineOpt5 = obj.isNull("IsRes1DoOpt9EngineOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt5");
			Boolean isRes1DoOpt9EngineOpt6 = obj.isNull("IsRes1DoOpt9EngineOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt6");
			String res1DoOpt9EngineOpt6Other = obj.isNull("Res1DoOpt9EngineOpt6Other") == true ? null : obj.getString("Res1DoOpt9EngineOpt6Other");

			Date res1DoOpt10Date = new Date();
			String strRes1DoOpt10Date = obj.isNull("Res1DoOpt10Date") == true ? null : obj.getString("Res1DoOpt10Date");
			if (strRes1DoOpt10Date != null && !strRes1DoOpt10Date.equals(""))
				res1DoOpt10Date = WebDatetime.parse(strRes1DoOpt10Date + " 00:00:00");

			Date res1DoOpt11Date = new Date();
			String strRes1DoOpt11Date = obj.isNull("Res1DoOpt11Date") == true ? null : obj.getString("Res1DoOpt11Date");
			if (strRes1DoOpt11Date != null && !strRes1DoOpt11Date.equals(""))
				res1DoOpt11Date = WebDatetime.parse(strRes1DoOpt11Date + " 00:00:00");

			String res1DoOpt12Remark = obj.isNull("Res1DoOpt12Remark") == true ? null : obj.getString("Res1DoOpt12Remark");

			// step4-2
			Boolean isRes2LogOpt1 = obj.isNull("IsRes2LogOpt1") == true ? false : obj.getBoolean("IsRes2LogOpt1");
			Boolean isRes2LogOpt2 = obj.isNull("IsRes2LogOpt2") == true ? false : obj.getBoolean("IsRes2LogOpt2");
			Boolean isRes2LogOpt3 = obj.isNull("IsRes2LogOpt3") == true ? false : obj.getBoolean("IsRes2LogOpt3");
			Boolean isRes2LogOpt4 = obj.isNull("IsRes2LogOpt4") == true ? false : obj.getBoolean("IsRes2LogOpt4");
			int res2LogOpt1 = obj.isNull("Res2LogOpt1") == true ? 0 : obj.getInt("Res2LogOpt1");
			String res2LogOpt1Other = obj.isNull("Res2LogOpt1Other") == true ? null : obj.getString("Res2LogOpt1Other");
			int res2LogOpt2 = obj.isNull("Res2LogOpt2") == true ? 0 : obj.getInt("Res2LogOpt2");
			String res2LogOpt2Other = obj.isNull("Res2LogOpt2Other") == true ? null : obj.getString("Res2LogOpt2Other");
			int res2LogOpt3Amount = obj.isNull("Res2LogOpt3Amount") == true ? 0 : obj.getInt("Res2LogOpt3Amount");
			String res2LogOpt4Remark = obj.isNull("Res2LogOpt4Remark") == true ? null : obj.getString("Res2LogOpt4Remark");
			Boolean isRes2EvaOpt1 = obj.isNull("IsRes2EvaOpt1") == true ? false : obj.getBoolean("IsRes2EvaOpt1");
			Boolean isRes2EvaOpt2 = obj.isNull("IsRes2EvaOpt2") == true ? false : obj.getBoolean("IsRes2EvaOpt2");
			Boolean isRes2EvaOpt3 = obj.isNull("IsRes2EvaOpt3") == true ? false : obj.getBoolean("IsRes2EvaOpt3");
			Boolean isRes2EvaOpt4 = obj.isNull("IsRes2EvaOpt4") == true ? false : obj.getBoolean("IsRes2EvaOpt4");
			Boolean isRes2EvaOpt5 = obj.isNull("IsRes2EvaOpt5") == true ? false : obj.getBoolean("IsRes2EvaOpt5");
			String res2EvaOpt1Remark = obj.isNull("Res2EvaOpt1Remark") == true ? null : obj.getString("Res2EvaOpt1Remark");
			String res2EvaOpt2Remark = obj.isNull("Res2EvaOpt2Remark") == true ? null : obj.getString("Res2EvaOpt2Remark");
			String res2EvaOpt3Remark = obj.isNull("Res2EvaOpt3Remark") == true ? null : obj.getString("Res2EvaOpt3Remark");
			String res2EvaOpt4Remark = obj.isNull("Res2EvaOpt4Remark") == true ? null : obj.getString("Res2EvaOpt4Remark");
			String res2EvaOpt5Remark = obj.isNull("Res2EvaOpt5Remark") == true ? null : obj.getString("Res2EvaOpt5Remark");
			Boolean isRes2DoOpt1 = obj.isNull("IsRes2DoOpt1") == true ? false : obj.getBoolean("IsRes2DoOpt1");
			Boolean isRes2DoOpt2 = obj.isNull("IsRes2DoOpt2") == true ? false : obj.getBoolean("IsRes2DoOpt2");
			Boolean isRes2DoOpt3 = obj.isNull("IsRes2DoOpt3") == true ? false : obj.getBoolean("IsRes2DoOpt3");
			Boolean isRes2DoOpt4 = obj.isNull("IsRes2DoOpt4") == true ? false : obj.getBoolean("IsRes2DoOpt4");
			Boolean isRes2DoOpt5 = obj.isNull("IsRes2DoOpt5") == true ? false : obj.getBoolean("IsRes2DoOpt5");
			Boolean isRes2DoOpt6 = obj.isNull("IsRes2DoOpt6") == true ? false : obj.getBoolean("IsRes2DoOpt6");
			Boolean isRes2DoOpt7 = obj.isNull("IsRes2DoOpt7") == true ? false : obj.getBoolean("IsRes2DoOpt7");
			Integer res2DoOpt1Amount = obj.optInt("Res2DoOpt1Amount", 0);
			String res2DoOpt1Remark = obj.isNull("Res2DoOpt1Remark") == true ? null : obj.getString("Res2DoOpt1Remark");
			String res2DoOpt2Remark = obj.isNull("Res2DoOpt2Remark") == true ? null : obj.getString("Res2DoOpt2Remark");
			String res2DoOpt3Remark = obj.isNull("Res2DoOpt3Remark") == true ? null : obj.getString("Res2DoOpt3Remark");

			Date res2DoOpt5Date = new Date();
			String strRes2DoOpt5Date = obj.optString("Res2DoOpt5Date", null);
			if (strRes2DoOpt5Date != null && !strRes2DoOpt5Date.equals(""))
				res2DoOpt5Date = WebDatetime.parse(strRes2DoOpt5Date + " 00:00:00");

			int res2DoOpt6Amount = obj.isNull("Res2DoOpt6Amount") == true ? 0 : obj.getInt("Res2DoOpt6Amount");
			String res2DoOpt7Remark = obj.isNull("Res2DoOpt7Remark") == true ? null : obj.getString("Res2DoOpt7Remark");
			// step4-3
			Boolean isRes3LogOpt1 = obj.isNull("IsRes3LogOpt1") == true ? false : obj.getBoolean("IsRes3LogOpt1");
			Boolean isRes3LogOpt2 = obj.isNull("IsRes3LogOpt2") == true ? false : obj.getBoolean("IsRes3LogOpt2");
			Boolean isRes3LogOpt3 = obj.isNull("IsRes3LogOpt3") == true ? false : obj.getBoolean("IsRes3LogOpt3");
			Boolean isRes3LogOpt4 = obj.isNull("IsRes3LogOpt4") == true ? false : obj.getBoolean("IsRes3LogOpt4");
			int res3LogOpt1 = obj.isNull("Res3LogOpt1") == true ? 0 : obj.getInt("Res3LogOpt1");
			String res3LogOpt1Other = obj.isNull("Res3LogOpt1Other") == true ? null : obj.getString("Res3LogOpt1Other");
			int res3LogOpt2 = obj.isNull("Res3LogOpt2") == true ? 0 : obj.getInt("Res3LogOpt2");
			String res3LogOpt2Other = obj.isNull("Res3LogOpt2Other") == true ? null : obj.getString("Res3LogOpt2Other");
			int res3LogOpt3Amount = obj.isNull("Res3LogOpt3Amount") == true ? 0 : obj.getInt("Res3LogOpt3Amount");
			String res3LogOpt4Remark = obj.isNull("Res3LogOpt4Remark") == true ? null : obj.getString("Res3LogOpt4Remark");
			Boolean isRes3EvaOpt1 = obj.isNull("IsRes3EvaOpt1") == true ? false : obj.getBoolean("IsRes3EvaOpt1");
			Boolean isRes3EvaOpt2 = obj.isNull("IsRes3EvaOpt2") == true ? false : obj.getBoolean("IsRes3EvaOpt2");
			Boolean isRes3EvaOpt3 = obj.isNull("IsRes3EvaOpt3") == true ? false : obj.getBoolean("IsRes3EvaOpt3");
			int res3EvaOpt1Amount = obj.isNull("Res3EvaOpt1Amount") == true ? 0 : obj.getInt("Res3EvaOpt1Amount");
			String res3EvaOpt2Remark = obj.isNull("Res3EvaOpt2Remark") == true ? null : obj.getString("Res3EvaOpt2Remark");
			String res3EvaOpt3Remark = obj.isNull("Res3EvaOpt3Remark") == true ? null : obj.getString("Res3EvaOpt3Remark");
			Boolean isRes3DoOpt1 = obj.isNull("IsRes3DoOpt1") == true ? false : obj.getBoolean("IsRes3DoOpt1");
			Boolean isRes3DoOpt2 = obj.isNull("IsRes3DoOpt2") == true ? false : obj.getBoolean("IsRes3DoOpt2");
			Boolean isRes3DoOpt3 = obj.isNull("IsRes3DoOpt3") == true ? false : obj.getBoolean("IsRes3DoOpt3");
			Boolean isRes3DoOpt4 = obj.isNull("IsRes3DoOpt4") == true ? false : obj.getBoolean("IsRes3DoOpt4");
			String res3DoOpt1Remark = obj.isNull("Res3DoOpt1Remark") == true ? null : obj.getString("Res3DoOpt1Remark");
			String res3DoOpt3Remark = obj.isNull("Res3DoOpt3Remark") == true ? null : obj.getString("Res3DoOpt3Remark");
			String res3DoOpt4Remark = obj.isNull("Res3DoOpt4Remark") == true ? null : obj.getString("Res3DoOpt4Remark");
			// step4-4
			Boolean isRes4LogOpt1 = obj.isNull("IsRes4LogOpt1") == true ? false : obj.getBoolean("IsRes4LogOpt1");
			String res4LogOpt1Remark = obj.isNull("Res4LogOpt1Remark") == true ? null : obj.getString("Res4LogOpt1Remark");
			Boolean isRes4EvaOpt1 = obj.isNull("IsRes4EvaOpt1") == true ? false : obj.getBoolean("IsRes4EvaOpt1");
			Boolean isRes4EvaOpt2 = obj.isNull("IsRes4EvaOpt2") == true ? false : obj.getBoolean("IsRes4EvaOpt2");
			Boolean isRes4EvaOpt3 = obj.optBoolean("IsRes4EvaOpt3", false);
			int res4EvaOpt1 = obj.isNull("Res4EvaOpt1") == true ? 0 : obj.getInt("Res4EvaOpt1");
			int res4EvaOpt1Amount = obj.isNull("Res4EvaOpt1Amount") == true ? 0 : obj.getInt("Res4EvaOpt1Amount");
			String res4EvaOpt2Remark = obj.isNull("Res4EvaOpt2Remark") == true ? null : obj.getString("Res4EvaOpt2Remark");
			String res4EvaOpt3Remark = obj.optString("Res4EvaOpt3Remark", null);
			Boolean isRes4DoOpt1 = obj.isNull("IsRes4DoOpt1") == true ? false : obj.getBoolean("IsRes4DoOpt1");
			Boolean isRes4DoOpt2 = obj.isNull("IsRes4DoOpt2") == true ? false : obj.getBoolean("IsRes4DoOpt2");
			Boolean isRes4DoOpt4 = obj.optBoolean("IsRes4DoOpt2", false);
			Boolean isRes4DoOpt3 = obj.isNull("IsRes4DoOpt3") == true ? false : obj.getBoolean("IsRes4DoOpt3");
			String res4DoOpt3Remark = obj.isNull("Res4DoOpt3Remark") == true ? null : obj.getString("Res4DoOpt3Remark");
			// step4-5
			Boolean isRes5LogOpt1 = obj.isNull("IsRes5LogOpt1") == true ? false : obj.getBoolean("IsRes5LogOpt1");
			Boolean isRes5LogOpt2 = obj.isNull("IsRes5LogOpt2") == true ? false : obj.getBoolean("IsRes5LogOpt2");
			Boolean isRes5LogOpt3 = obj.isNull("IsRes5LogOpt3") == true ? false : obj.getBoolean("IsRes5LogOpt3");
			Boolean isRes5LogOpt4 = obj.isNull("IsRes5LogOpt4") == true ? false : obj.getBoolean("IsRes5LogOpt4");
			int res5LogOpt1 = obj.isNull("Res5LogOpt1") == true ? 0 : obj.getInt("Res5LogOpt1");
			String res5LogOpt1Other = obj.isNull("Res5LogOpt1Other") == true ? null : obj.getString("Res5LogOpt1Other");
			int res5LogOpt2 = obj.isNull("Res5LogOpt2") == true ? 0 : obj.getInt("Res5LogOpt2");
			String res5LogOpt2Other = obj.isNull("Res5LogOpt2Other") == true ? null : obj.getString("Res5LogOpt2Other");
			int res5LogOpt3Amount = obj.isNull("Res5LogOpt3Amount") == true ? 0 : obj.getInt("Res5LogOpt3Amount");
			String res5LogOpt4Remark = obj.isNull("Res5LogOpt4Remark") == true ? null : obj.getString("Res5LogOpt4Remark");
			Boolean isRes5EvaOpt1 = obj.isNull("IsRes5EvaOpt1") == true ? false : obj.getBoolean("IsRes5EvaOpt1");
			Boolean isRes5EvaOpt2 = obj.isNull("IsRes5EvaOpt2") == true ? false : obj.getBoolean("IsRes5EvaOpt2");
			Boolean isRes5EvaOpt3 = obj.isNull("IsRes5EvaOpt3") == true ? false : obj.getBoolean("IsRes5EvaOpt3");
			Boolean isRes5EvaOpt4 = obj.isNull("IsRes5EvaOpt4") == true ? false : obj.getBoolean("IsRes5EvaOpt4");
			Boolean isRes5EvaOpt5 = obj.isNull("IsRes5EvaOpt5") == true ? false : obj.getBoolean("IsRes5EvaOpt5");
			String res5EvaOpt1Remark = obj.isNull("Res5EvaOpt1Remark") == true ? null : obj.getString("Res5EvaOpt1Remark");
			String res5EvaOpt2Remark = obj.isNull("Res5EvaOpt2Remark") == true ? null : obj.getString("Res5EvaOpt2Remark");
			String res5EvaOpt3Remark = obj.isNull("Res5EvaOpt3Remark") == true ? null : obj.getString("Res5EvaOpt3Remark");
			String res5EvaOpt4Remark = obj.isNull("Res5EvaOpt4Remark") == true ? null : obj.getString("Res5EvaOpt4Remark");
			String res5EvaOpt5Remark = obj.isNull("Res5EvaOpt5Remark") == true ? null : obj.getString("Res5EvaOpt5Remark");
			Boolean isRes5DoOpt1 = obj.isNull("IsRes5DoOpt1") == true ? false : obj.getBoolean("IsRes5DoOpt1");
			Boolean isRes5DoOpt2 = obj.isNull("IsRes5DoOpt2") == true ? false : obj.getBoolean("IsRes5DoOpt2");
			Boolean isRes5DoOpt3 = obj.isNull("IsRes5DoOpt3") == true ? false : obj.getBoolean("IsRes5DoOpt3");
			Boolean isRes5DoOpt4 = obj.isNull("IsRes5DoOpt4") == true ? false : obj.getBoolean("IsRes5DoOpt4");
			Boolean isRes5DoOpt5 = obj.isNull("IsRes5DoOpt5") == true ? false : obj.getBoolean("IsRes5DoOpt5");
			Boolean isRes5DoOpt6 = obj.isNull("IsRes5DoOpt6") == true ? false : obj.getBoolean("IsRes5DoOpt6");
			Boolean isRes5DoOpt7 = obj.isNull("IsRes5DoOpt7") == true ? false : obj.getBoolean("IsRes5DoOpt7");
			Integer res5DoOpt1Amount = obj.optInt("Res5DoOpt1Amount", 0);
			String res5DoOpt1Remark = obj.isNull("Res5DoOpt1Remark") == true ? null : obj.getString("Res5DoOpt1Remark");
			String res5DoOpt2Remark = obj.isNull("Res5DoOpt2Remark") == true ? null : obj.getString("Res5DoOpt2Remark");
			String res5DoOpt3Remark = obj.isNull("Res5DoOpt3Remark") == true ? null : obj.getString("Res5DoOpt3Remark");

			Date res5DoOpt5Date = new Date();
			String strRes5DoOpt5Date = obj.isNull("Res5DoOpt5Date") == true ? null : obj.getString("Res5DoOpt5Date");
			if (strRes5DoOpt5Date != null && !strRes5DoOpt5Date.equals(""))
				res5DoOpt5Date = WebDatetime.parse(strRes5DoOpt5Date + " 00:00:00");

			int res5DoOpt6Amount = obj.isNull("Res5DoOpt6Amount") == true ? 0 : obj.getInt("Res5DoOpt6Amount");
			String res5DoOpt7Remark = obj.isNull("Res5DoOpt7Remark") == true ? null : obj.getString("Res5DoOpt7Remark");
			// step5
			Boolean isNeedSupport = obj.isNull("IsNeedSupport") == true ? false : obj.getBoolean("IsNeedSupport");
			String needSupportRemark = obj.isNull("NeedSupportRemark") == true ? null : obj.getString("NeedSupportRemark");
			// step6-1
			int finish1Reason = obj.isNull("Finish1Reason") == true ? 0 : obj.getInt("Finish1Reason");
			String finish1ReasonOther = obj.isNull("Finish1ReasonOther") == true ? null : obj.getString("Finish1ReasonOther");
			String finish1ReasonToDo = obj.optString("Finish1ReasonToDo", null);
			Boolean isFinish1DoSysOpt1 = obj.isNull("IsFinish1DoSysOpt1") == true ? false : obj.getBoolean("IsFinish1DoSysOpt1");
			Boolean isFinish1DoSysOpt2 = obj.isNull("IsFinish1DoSysOpt2") == true ? false : obj.getBoolean("IsFinish1DoSysOpt2");
			Boolean isFinish1DoSysOpt3 = obj.isNull("IsFinish1DoSysOpt3") == true ? false : obj.getBoolean("IsFinish1DoSysOpt3");
			Boolean isFinish1DoSysOpt4 = obj.isNull("IsFinish1DoSysOpt4") == true ? false : obj.getBoolean("IsFinish1DoSysOpt4");
			Boolean isFinish1DoSysOpt5 = obj.isNull("IsFinish1DoSysOpt5") == true ? false : obj.getBoolean("IsFinish1DoSysOpt5");
			Boolean isFinish1DoSysOpt6 = obj.isNull("IsFinish1DoSysOpt6") == true ? false : obj.getBoolean("IsFinish1DoSysOpt6");
			Boolean isFinish1DoSysOpt7 = obj.isNull("IsFinish1DoSysOpt7") == true ? false : obj.getBoolean("IsFinish1DoSysOpt7");
			Boolean isFinish1DoSysOpt8 = obj.isNull("IsFinish1DoSysOpt8") == true ? false : obj.getBoolean("IsFinish1DoSysOpt8");
			Boolean isFinish1DoSysOpt9 = obj.isNull("IsFinish1DoSysOpt9") == true ? false : obj.getBoolean("IsFinish1DoSysOpt9");
			Boolean isFinish1DoSysOpt10 = obj.isNull("IsFinish1DoSysOpt10") == true ? false : obj.getBoolean("IsFinish1DoSysOpt10");
			String finish1DoSysOpt3Remark = obj.isNull("Finish1DoSysOpt3Remark") == true ? null : obj.getString("Finish1DoSysOpt3Remark");
			String finish1DoSysOpt6Remark = obj.isNull("Finish1DoSysOpt6Remark") == true ? null : obj.getString("Finish1DoSysOpt6Remark");
			String finish1DoSysOpt7Remark = obj.isNull("Finish1DoSysOpt7Remark") == true ? null : obj.getString("Finish1DoSysOpt7Remark");
			Boolean isFinish1DoEduOpt1 = obj.isNull("IsFinish1DoEduOpt1") == true ? false : obj.getBoolean("IsFinish1DoEduOpt1");
			Boolean isFinish1DoEduOpt2 = obj.isNull("IsFinish1DoEduOpt2") == true ? false : obj.getBoolean("IsFinish1DoEduOpt2");
			Boolean isFinish1DoEduOpt3 = obj.isNull("IsFinish1DoEduOpt3") == true ? false : obj.getBoolean("IsFinish1DoEduOpt3");
			Boolean isFinish1DoEduOpt4 = obj.isNull("IsFinish1DoEduOpt4") == true ? false : obj.getBoolean("IsFinish1DoEduOpt4");
			// step6-2
			int finish2Reason = obj.isNull("Finish2Reason") == true ? 0 : obj.getInt("Finish2Reason");
			String finish2ReasonOther = obj.isNull("Finish2ReasonOther") == true ? null : obj.getString("Finish2ReasonOther");
			String finish2ReasonRemark = obj.isNull("Finish2ReasonRemark") == true ? null : obj.getString("Finish2ReasonRemark");
			Boolean isFinish2DoSysOpt1 = obj.isNull("IsFinish2DoSysOpt1") == true ? false : obj.getBoolean("IsFinish2DoSysOpt1");
			Boolean isFinish2DoSysOpt2 = obj.isNull("IsFinish2DoSysOpt2") == true ? false : obj.getBoolean("IsFinish2DoSysOpt2");
			Boolean isFinish2DoSysOpt3 = obj.isNull("IsFinish2DoSysOpt3") == true ? false : obj.getBoolean("IsFinish2DoSysOpt3");
			Boolean isFinish2DoSysOpt4 = obj.isNull("IsFinish2DoSysOpt4") == true ? false : obj.getBoolean("IsFinish2DoSysOpt4");
			Boolean isFinish2DoSysOpt5 = obj.isNull("IsFinish2DoSysOpt5") == true ? false : obj.getBoolean("IsFinish2DoSysOpt5");
			String finish2DoSysOpt1Remark = obj.isNull("Finish2DoSysOpt1Remark") == true ? null : obj.getString("Finish2DoSysOpt1Remark");
			Boolean isFinish2DoEduOpt1 = obj.isNull("IsFinish2DoEduOpt1") == true ? false : obj.getBoolean("IsFinish2DoEduOpt1");
			Boolean isFinish2DoEduOpt2 = obj.isNull("IsFinish2DoEduOpt2") == true ? false : obj.getBoolean("IsFinish2DoEduOpt2");
			Boolean isFinish2DoEduOpt3 = obj.isNull("IsFinish2DoEduOpt3") == true ? false : obj.getBoolean("IsFinish2DoEduOpt3");
			Boolean isFinish2DoEduOpt4 = obj.isNull("IsFinish2DoEduOpt4") == true ? false : obj.getBoolean("IsFinish2DoEduOpt4");
			// step6-3
			Boolean isFinish3DoSysOpt1 = obj.isNull("IsFinish3DoSysOpt1") == true ? false : obj.getBoolean("IsFinish3DoSysOpt1");
			Boolean isFinish3DoSysOpt2 = obj.isNull("IsFinish3DoSysOpt2") == true ? false : obj.getBoolean("IsFinish3DoSysOpt2");
			Boolean isFinish3DoSysOpt3 = obj.isNull("IsFinish3DoSysOpt3") == true ? false : obj.getBoolean("IsFinish3DoSysOpt3");
			Boolean isFinish3DoSysOpt4 = obj.isNull("IsFinish3DoSysOpt4") == true ? false : obj.getBoolean("IsFinish3DoSysOpt4");
			String finish3DoSysOpt3Remark = obj.isNull("Finish3DoSysOpt3Remark") == true ? null : obj.getString("Finish3DoSysOpt3Remark");
			String finish3DoSysOpt4Remark = obj.isNull("Finish3DoSysOpt4Remark") == true ? null : obj.getString("Finish3DoSysOpt4Remark");
			Boolean isFinish3DoEduOpt1 = obj.isNull("IsFinish3DoEduOpt1") == true ? false : obj.getBoolean("IsFinish3DoEduOpt1");
			Boolean isFinish3DoEduOpt2 = obj.isNull("IsFinish3DoEduOpt2") == true ? false : obj.getBoolean("IsFinish3DoEduOpt2");
			// step6-4
			int finish4Reason = obj.isNull("Finish4Reason") == true ? 0 : obj.getInt("Finish4Reason");
			String finish4ReasonOther = obj.isNull("Finish4ReasonOther") == true ? null : obj.getString("Finish4ReasonOther");
			String finish4ReasonRemark = obj.isNull("Finish4ReasonRemark") == true ? null : obj.getString("Finish4ReasonRemark");
			Boolean isFinish4DoSysOpt1 = obj.isNull("IsFinish4DoSysOpt1") == true ? false : obj.getBoolean("IsFinish4DoSysOpt1");
			Boolean isFinish4DoEduOpt1 = obj.isNull("IsFinish4DoEduOpt1") == true ? false : obj.getBoolean("IsFinish4DoEduOpt1");
			Boolean isFinish4DoEduOpt2 = obj.isNull("IsFinish4DoEduOpt2") == true ? false : obj.getBoolean("IsFinish4DoEduOpt2");
			Boolean isFinish4DoEduOpt3 = obj.isNull("IsFinish4DoEduOpt3") == true ? false : obj.getBoolean("IsFinish4DoEduOpt3");
			Boolean isFinish4DoEduOpt4 = obj.isNull("IsFinish4DoEduOpt4") == true ? false : obj.getBoolean("IsFinish4DoEduOpt4");
			// step6-5
			int finish5Reason = obj.isNull("Finish5Reason") == true ? 0 : obj.getInt("Finish5Reason");
			String finish5ReasonOther = obj.isNull("Finish5ReasonOther") == true ? null : obj.getString("Finish5ReasonOther");
			String finish5ReasonRemark = obj.isNull("Finish5ReasonRemark") == true ? null : obj.getString("Finish5ReasonRemark");
			Boolean isFinish5DoSysOpt1 = obj.isNull("IsFinish5DoSysOpt1") == true ? false : obj.getBoolean("IsFinish5DoSysOpt1");
			Boolean isFinish5DoSysOpt2 = obj.isNull("IsFinish5DoSysOpt2") == true ? false : obj.getBoolean("IsFinish5DoSysOpt2");
			Boolean isFinish5DoSysOpt3 = obj.isNull("IsFinish5DoSysOpt3") == true ? false : obj.getBoolean("IsFinish5DoSysOpt3");
			Boolean isFinish5DoSysOpt4 = obj.isNull("IsFinish5DoSysOpt4") == true ? false : obj.getBoolean("IsFinish5DoSysOpt4");
			String finish5DoSysOpt1Remark = obj.isNull("Finish5DoSysOpt1Remark") == true ? null : obj.getString("Finish5DoSysOpt1Remark");
			Boolean isFinish5DoEduOpt1 = obj.isNull("IsFinish5DoEduOpt1") == true ? false : obj.getBoolean("IsFinish5DoEduOpt1");
			Boolean isFinish5DoEduOpt2 = obj.isNull("IsFinish5DoEduOpt2") == true ? false : obj.getBoolean("IsFinish5DoEduOpt2");
			Boolean isFinish5DoEduOpt3 = obj.isNull("IsFinish5DoEduOpt3") == true ? false : obj.getBoolean("IsFinish5DoEduOpt3");
			Boolean isFinish5DoEduOpt4 = obj.isNull("IsFinish5DoEduOpt4") == true ? false : obj.getBoolean("IsFinish5DoEduOpt4");
			// step6-...
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");

			Date finishDateTime = new Date();
			String strFinishDateTime = obj.isNull("FinishDateTime") == true ? null : obj.getString("FinishDateTime");
			if (strFinishDateTime != null && !strFinishDateTime.equals(""))
				finishDateTime = WebDatetime.parse(strFinishDateTime);
			// other...
			int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

			Date examineDateTime = new Date();
			String strExamineDateTime = obj.isNull("ExamineDateTime") == true ? null : obj.getString("ExamineDateTime");
			if (strExamineDateTime != null && !strExamineDateTime.equals(""))
				examineDateTime = WebDatetime.parse(strExamineDateTime);

			Date realFinishDateTime = new Date();
			String strRealFinishDateTime = obj.isNull("RealFinishDateTime") == true ? null : obj.getString("RealFinishDateTime");
			if (strRealFinishDateTime != null && !strRealFinishDateTime.equals(""))
				realFinishDateTime = WebDatetime.parse(strRealFinishDateTime);

			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");
			String messagePostId = obj.isNull("MessagePostId") == true ? null : obj.getString("MessagePostId");

			Boolean isCC3 = obj.isNull("IsCC3") == true ? false : obj.getBoolean("IsCC3");
			Boolean isReview3 = obj.isNull("IsReview3") == true ? false : obj.getBoolean("IsReview3");
			
			Boolean isCC5 = obj.isNull("IsCC5") == true ? false : obj.getBoolean("IsCC5");
			Boolean isReview5 = obj.isNull("IsReview5") == true ? false : obj.getBoolean("IsReview5");

			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (isWriteProcessLog) {													
				
				boolean isNeedSaleReview = false;
				// 寄信
				List<OrgSign> orgSigns = orgSignService.getByOrgId(memberService.get(memberId).getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member member : members) {
								if (member.getIsEnable()) {
									List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
									if (memberRoles != null) {
										for (ViewMemberRoleMember memberRole : memberRoles) {
											if (memberRole.getMemberId().equals(member.getId())) {
												Org org = orgService.getDataById(member.getOrgId());
												if (org.getAuthType().equals("2") && org.getIsEnable()) {
													if (isCC3) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Body"), member.getName(), memberService.get(memberId).getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
													}
													if (isReview3) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
														status = 31;
													}
												}
												if (!isReview3 && org.getAuthType().equals("1") && org.getIsEnable()) {
													if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
														isNeedSaleReview = true;
														status = 32;
													}
													if (orgSign.getNotificationIsExamine().equals(2)) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Body"), member.getName(), memberService.get(memberId).getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (!isNeedSaleReview && !isReview3) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(12);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
								status = 33;								
							}
						}
					}
				}
				//sms TO H-ISAC通報審核者
				List<ViewMemberRoleMember> viewMemberRoleMembers = memberRoleService.findByRoleId(12);
				String contactorUnitName = orgService.getDataById(contactorUnit).getName();				
				if (viewMemberRoleMembers != null) {					
					for (ViewMemberRoleMember viewMemberRoleMember : viewMemberRoleMembers) {
						Member memberSms = memberService.get(viewMemberRoleMember.getMemberId());
						if (true && memberSms.getMobilePhone() != null && !memberSms.getMobilePhone().trim().equals("")) {
							String smsMessage = MessageFormat.format(resourceMessageService.getMessageValue("mailNotificationSms"), contactorUnitName, postId, 
									status==2?"[撤銷中]":status==31?"上級機關[通報審核中]":status==32?"業務管理機關[通報審核中]":status==33?"HCERT[通報審核中]"
											:status==4?"會員機構[處理中]"
											:status==51?"上級機關[處理審核中]":status==52?"業務管理機關[處理審核中]":status==53?"HCERT[處理審核中]"
											:status==6?"[已結案]":status==7?"[已銷案]":status==8?"會員機構[編輯中(退回補述)]":"會員機構[處理中(退回補述)]");
							
								smsService.Send("[測試]",  memberSms.getMobilePhone(), smsMessage);
							
						}
					}
				}
			}

			Date now = new Date();
			Notification entity = new Notification();
			entity.setId(WebCrypto.generateUUID());

			// step1
			entity.setApplyDateTime(applyDateTime);
			entity.setPostId(postId);
			entity.setContactorUnit(contactorUnit);
			entity.setMainUnit1(mainUnit1);
			entity.setMainUnit2(mainUnit2);
			entity.setContactorId(contactorId);
			entity.setContactorTel(contactorTel);
			entity.setContactorFax(contactorFax);
			entity.setContactorEmail(contactorEmail);
			entity.setIsSub(isSub);
			entity.setIsSubUnitName(isSubUnitName);
			entity.setEventDateTime(eventDateTime);
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);			
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsFinishDoSysOptRemark(isFinishDoSysOptRemark);
			entity.setIsFinishDoEduOptRemark(isFinishDoEduOptRemark);
			entity.setFinishDoSysOptRemark(finishDoSysOptRemark);
			entity.setFinishDoEduOptRemark(finishDoEduOptRemark);
			entity.setIsHandledByMyself(isHandledByMyself);		
			entity.setHandleInformationId(handleInformationId);		
			entity.setIpExternal(ipExternal);
			entity.setIpInternal(ipInternal);
			entity.setWebUrl(webUrl);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setIsGuardOpt1(isGuardOpt1);
			entity.setIsGuardOpt2(isGuardOpt2);
			entity.setIsGuardOpt3(isGuardOpt3);
			entity.setIsGuardOpt4(isGuardOpt4);
			entity.setIsGuardOpt4Other(isGuardOpt4Other);
			entity.setSocOpt(socOpt);
			entity.setSocOptCompany(socOptCompany);
			entity.setIsIsms(isIsms);
			entity.setMaintainCompany(maintainCompany);
			entity.setIsTest(isTest);
			// step2
			entity.setCeffectLevel(ceffectLevel);
			entity.setIeffectLevel(ieffectLevel);
			entity.setAeffectLevel(aeffectLevel);
			entity.setEffectLevel(effectLevel);
			// step3
			entity.setEventType(eventType);
			entity.setIsEventType1Opt1(isEventType1Opt1);
			entity.setIsEventType1Opt2(isEventType1Opt2);
			entity.setIsEventType1Opt3(isEventType1Opt3);
			entity.setIsEventType1Opt4(isEventType1Opt4);
			entity.setIsEventType1Opt5(isEventType1Opt5);
			entity.setIsEventType1Opt6(isEventType1Opt6);
			entity.setIsEventType2Opt1(isEventType2Opt1);
			entity.setIsEventType2Opt2(isEventType2Opt2);
			entity.setIsEventType2Opt3(isEventType2Opt3);
			entity.setIsEventType2Opt4(isEventType2Opt4);
			entity.setIsEventType2Opt5(isEventType2Opt5);
			entity.setIsEventType3Opt1(isEventType3Opt1);
			entity.setIsEventType3Opt2(isEventType3Opt2);
			entity.setIsEventType4Opt1(isEventType4Opt1);
			entity.setIsEventType4Opt2(isEventType4Opt2);
			entity.setIsEventType4Opt3(isEventType4Opt3);
			entity.setIsEventType4Opt4(isEventType4Opt4);
			entity.setEventType5Other(eventType5Other);
			entity.setEventRemark(eventRemark);
			entity.setIsAffectOthers(isAffectOthers);
			entity.setAffectOther1(affectOther1);
			entity.setAffectOther2(affectOther2);
			entity.setAffectOther3(affectOther3);
			entity.setAffectOther4(affectOther4);
			entity.setAffectOther5(affectOther5);
			entity.setAffectOther6(affectOther6);
			entity.setAffectOther7(affectOther7);
			entity.setAffectOther8(affectOther8);
			entity.setEventSource(eventSource);
			entity.setEventSourceNo(eventSourceNo);
			entity.setEventSourceOther(eventSourceOther);
			// step4-all
			entity.setResDescription(resDescription);
			entity.setResControlTime(resControlTime);
			entity.setResResult(resResult);
			// step4-1
			entity.setIsRes1LogOpt1(isRes1LogOpt1);
			entity.setIsRes1LogOpt2(isRes1LogOpt2);
			entity.setIsRes1LogOpt5(isRes1LogOpt5);
			entity.setIsRes1LogOpt3(isRes1LogOpt3);
			entity.setIsRes1LogOpt4(isRes1LogOpt4);
			entity.setRes1LogOpt1(res1LogOpt1);
			entity.setRes1LogOpt1Other(res1LogOpt1Other);
			entity.setRes1LogOpt2(res1LogOpt2);
			entity.setRes1LogOpt2Other(res1LogOpt2Other);
			entity.setRes1LogOpt5(res1LogOpt5);
			entity.setRes1LogOpt5Other(res1LogOpt5Other);
			entity.setRes1LogOpt3Amount(res1LogOpt3Amount);
			entity.setRes1LogOpt4Remark(res1LogOpt4Remark);
			entity.setIsRes1EvaOpt1(isRes1EvaOpt1);
			entity.setIsRes1EvaOpt2(isRes1EvaOpt2);
			entity.setIsRes1EvaOpt3(isRes1EvaOpt3);
			entity.setIsRes1EvaOpt4(isRes1EvaOpt4);
			entity.setIsRes1EvaOpt5(isRes1EvaOpt5);
			entity.setIsRes1EvaOpt6(isRes1EvaOpt6);
			entity.setIsRes1EvaOpt7(isRes1EvaOpt7);
			entity.setIsRes1EvaOpt8(isRes1EvaOpt8);
			entity.setRes1EvaOpt1Remark(res1EvaOpt1Remark);
			entity.setRes1EvaOpt2Remark(res1EvaOpt2Remark);
			entity.setRes1EvaOpt3Remark(res1EvaOpt3Remark);
			entity.setRes1EvaOpt4Remark(res1EvaOpt4Remark);
			entity.setRes1EvaOpt6Amount(res1EvaOpt6Amount);
			entity.setRes1EvaOpt6Remark(res1EvaOpt6Remark);
			entity.setRes1EvaOpt6Type(res1EvaOpt6Type);
			entity.setRes1EvaOpt6TypeOpt3Other(res1EvaOpt6TypeOpt3Other);
			entity.setRes1EvaOpt7Amount(res1EvaOpt7Amount);
			entity.setRes1EvaOpt7Remark(res1EvaOpt7Remark);
			entity.setRes1EvaOpt8Remark(res1EvaOpt8Remark);
			entity.setIsRes1DoOpt1(isRes1DoOpt1);
			entity.setIsRes1DoOpt2(isRes1DoOpt2);
			entity.setIsRes1DoOpt3(isRes1DoOpt3);
			entity.setIsRes1DoOpt4(isRes1DoOpt4);
			entity.setIsRes1DoOpt5(isRes1DoOpt5);
			entity.setIsRes1DoOpt6(isRes1DoOpt6);
			entity.setIsRes1DoOpt7(isRes1DoOpt7);
			entity.setIsRes1DoOpt8(isRes1DoOpt8);
			entity.setIsRes1DoOpt9(isRes1DoOpt9);
			entity.setIsRes1DoOpt10(isRes1DoOpt10);
			entity.setIsRes1DoOpt11(isRes1DoOpt11);
			entity.setIsRes1DoOpt12(isRes1DoOpt12);
			entity.setRes1DoOpt1Amount(res1DoOpt1Amount);
			entity.setRes1DoOpt2Remark(res1DoOpt2Remark);
			entity.setRes1DoOpt3Remark(res1DoOpt3Remark);
			entity.setRes1DoOpt4Remark(res1DoOpt4Remark);
			entity.setRes1DoOpt5Amount(res1DoOpt5Amount);
			entity.setIsRes1DoOpt9EngineOpt1(isRes1DoOpt9EngineOpt1);
			entity.setIsRes1DoOpt9EngineOpt2(isRes1DoOpt9EngineOpt2);
			entity.setIsRes1DoOpt9EngineOpt3(isRes1DoOpt9EngineOpt3);
			entity.setIsRes1DoOpt9EngineOpt4(isRes1DoOpt9EngineOpt4);
			entity.setIsRes1DoOpt9EngineOpt5(isRes1DoOpt9EngineOpt5);
			entity.setIsRes1DoOpt9EngineOpt6(isRes1DoOpt9EngineOpt6);
			entity.setRes1DoOpt9EngineOpt6Other(res1DoOpt9EngineOpt6Other);
			entity.setRes1DoOpt10Date(res1DoOpt10Date);
			entity.setRes1DoOpt11Date(res1DoOpt11Date);
			entity.setRes1DoOpt12Remark(res1DoOpt12Remark);
			// step4-2
			entity.setIsRes2LogOpt1(isRes2LogOpt1);
			entity.setIsRes2LogOpt2(isRes2LogOpt2);
			entity.setIsRes2LogOpt3(isRes2LogOpt3);
			entity.setIsRes2LogOpt4(isRes2LogOpt4);
			entity.setRes2LogOpt1(res2LogOpt1);
			entity.setRes2LogOpt1Other(res2LogOpt1Other);
			entity.setRes2LogOpt2(res2LogOpt2);
			entity.setRes2LogOpt2Other(res2LogOpt2Other);
			entity.setRes2LogOpt3Amount(res2LogOpt3Amount);
			entity.setRes2LogOpt4Remark(res2LogOpt4Remark);
			entity.setIsRes2EvaOpt1(isRes2EvaOpt1);
			entity.setIsRes2EvaOpt2(isRes2EvaOpt2);
			entity.setIsRes2EvaOpt3(isRes2EvaOpt3);
			entity.setIsRes2EvaOpt4(isRes2EvaOpt4);
			entity.setIsRes2EvaOpt5(isRes2EvaOpt5);
			entity.setRes2EvaOpt1Remark(res2EvaOpt1Remark);
			entity.setRes2EvaOpt2Remark(res2EvaOpt2Remark);
			entity.setRes2EvaOpt3Remark(res2EvaOpt3Remark);
			entity.setRes2EvaOpt4Remark(res2EvaOpt4Remark);
			entity.setRes2EvaOpt5Remark(res2EvaOpt5Remark);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setIsRes2DoOpt2(isRes2DoOpt2);
			entity.setIsRes2DoOpt3(isRes2DoOpt3);
			entity.setIsRes2DoOpt4(isRes2DoOpt4);
			entity.setIsRes2DoOpt5(isRes2DoOpt5);
			entity.setIsRes2DoOpt6(isRes2DoOpt6);
			entity.setIsRes2DoOpt7(isRes2DoOpt7);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setRes2DoOpt1Amount(res2DoOpt1Amount);
			entity.setRes2DoOpt1Remark(res2DoOpt1Remark);
			entity.setRes2DoOpt2Remark(res2DoOpt2Remark);
			entity.setRes2DoOpt3Remark(res2DoOpt3Remark);
			entity.setRes2DoOpt5Date(res2DoOpt5Date);
			entity.setRes2DoOpt6Amount(res2DoOpt6Amount);
			entity.setRes2DoOpt7Remark(res2DoOpt7Remark);
			// step4-3
			entity.setIsRes3LogOpt1(isRes3LogOpt1);
			entity.setIsRes3LogOpt2(isRes3LogOpt2);
			entity.setIsRes3LogOpt3(isRes3LogOpt3);
			entity.setIsRes3LogOpt4(isRes3LogOpt4);
			entity.setRes3LogOpt1(res3LogOpt1);
			entity.setRes3LogOpt1Other(res3LogOpt1Other);
			entity.setRes3LogOpt2(res3LogOpt2);
			entity.setRes3LogOpt2Other(res3LogOpt2Other);
			entity.setRes3LogOpt3Amount(res3LogOpt3Amount);
			entity.setRes3LogOpt4Remark(res3LogOpt4Remark);
			entity.setIsRes3EvaOpt1(isRes3EvaOpt1);
			entity.setIsRes3EvaOpt2(isRes3EvaOpt2);
			entity.setIsRes3EvaOpt3(isRes3EvaOpt3);
			entity.setRes3EvaOpt1Amount(res3EvaOpt1Amount);
			entity.setRes3EvaOpt2Remark(res3EvaOpt2Remark);
			entity.setRes3EvaOpt3Remark(res3EvaOpt3Remark);
			entity.setIsRes3DoOpt1(isRes3DoOpt1);
			entity.setIsRes3DoOpt2(isRes3DoOpt2);
			entity.setIsRes3DoOpt3(isRes3DoOpt3);
			entity.setIsRes3DoOpt4(isRes3DoOpt4);
			entity.setRes3DoOpt1Remark(res3DoOpt1Remark);
			entity.setRes3DoOpt3Remark(res3DoOpt3Remark);
			entity.setRes3DoOpt4Remark(res3DoOpt4Remark);
			// step4-4
			entity.setIsRes4LogOpt1(isRes4LogOpt1);
			entity.setRes4LogOpt1Remark(res4LogOpt1Remark);
			entity.setIsRes4EvaOpt1(isRes4EvaOpt1);
			entity.setIsRes4EvaOpt2(isRes4EvaOpt2);
			entity.setIsRes4EvaOpt3(isRes4EvaOpt3);
			entity.setRes4EvaOpt1(res4EvaOpt1);
			entity.setRes4EvaOpt1Amount(res4EvaOpt1Amount);
			entity.setRes4EvaOpt2Remark(res4EvaOpt2Remark);
			entity.setRes4EvaOpt3Remark(res4EvaOpt3Remark);
			entity.setIsRes4DoOpt1(isRes4DoOpt1);
			entity.setIsRes4DoOpt2(isRes4DoOpt2);
			entity.setIsRes4DoOpt4(isRes4DoOpt4);
			entity.setIsRes4DoOpt3(isRes4DoOpt3);
			entity.setRes4DoOpt3Remark(res4DoOpt3Remark);
			// step4-5
			entity.setIsRes5LogOpt1(isRes5LogOpt1);
			entity.setIsRes5LogOpt2(isRes5LogOpt2);
			entity.setIsRes5LogOpt3(isRes5LogOpt3);
			entity.setIsRes5LogOpt4(isRes5LogOpt4);
			entity.setRes5LogOpt1(res5LogOpt1);
			entity.setRes5LogOpt1Other(res5LogOpt1Other);
			entity.setRes5LogOpt2(res5LogOpt2);
			entity.setRes5LogOpt2Other(res5LogOpt2Other);
			entity.setRes5LogOpt3Amount(res5LogOpt3Amount);
			entity.setRes5LogOpt4Remark(res5LogOpt4Remark);
			entity.setIsRes5EvaOpt1(isRes5EvaOpt1);
			entity.setIsRes5EvaOpt2(isRes5EvaOpt2);
			entity.setIsRes5EvaOpt3(isRes5EvaOpt3);
			entity.setIsRes5EvaOpt4(isRes5EvaOpt4);
			entity.setIsRes5EvaOpt5(isRes5EvaOpt5);
			entity.setRes5EvaOpt1Remark(res5EvaOpt1Remark);
			entity.setRes5EvaOpt2Remark(res5EvaOpt2Remark);
			entity.setRes5EvaOpt3Remark(res5EvaOpt3Remark);
			entity.setRes5EvaOpt4Remark(res5EvaOpt4Remark);
			entity.setRes5EvaOpt5Remark(res5EvaOpt5Remark);
			entity.setIsRes5DoOpt1(isRes5DoOpt1);
			entity.setIsRes5DoOpt2(isRes5DoOpt2);
			entity.setIsRes5DoOpt3(isRes5DoOpt3);
			entity.setIsRes5DoOpt4(isRes5DoOpt4);
			entity.setIsRes5DoOpt5(isRes5DoOpt5);
			entity.setIsRes5DoOpt6(isRes5DoOpt6);
			entity.setIsRes5DoOpt7(isRes5DoOpt7);
			entity.setRes5DoOpt1Amount(res5DoOpt1Amount);
			entity.setRes5DoOpt1Remark(res5DoOpt1Remark);
			entity.setRes5DoOpt2Remark(res5DoOpt2Remark);
			entity.setRes5DoOpt3Remark(res5DoOpt3Remark);
			entity.setRes5DoOpt5Date(res5DoOpt5Date);
			entity.setRes5DoOpt6Amount(res5DoOpt6Amount);
			entity.setRes5DoOpt7Remark(res5DoOpt7Remark);
			// step5
			entity.setIsNeedSupport(isNeedSupport);
			entity.setIsHandledByMyself(isNeedSupport);
			entity.setNeedSupportRemark(needSupportRemark);
			// step6-1
			entity.setFinish1Reason(finish1Reason);
			entity.setFinish1ReasonOther(finish1ReasonOther);
			entity.setFinish1ReasonToDo(finish1ReasonToDo);
			entity.setIsFinish1DoSysOpt1(isFinish1DoSysOpt1);
			entity.setIsFinish1DoSysOpt2(isFinish1DoSysOpt2);
			entity.setIsFinish1DoSysOpt3(isFinish1DoSysOpt3);
			entity.setIsFinish1DoSysOpt4(isFinish1DoSysOpt4);
			entity.setIsFinish1DoSysOpt5(isFinish1DoSysOpt5);
			entity.setIsFinish1DoSysOpt6(isFinish1DoSysOpt6);
			entity.setIsFinish1DoSysOpt7(isFinish1DoSysOpt7);
			entity.setIsFinish1DoSysOpt8(isFinish1DoSysOpt8);
			entity.setIsFinish1DoSysOpt9(isFinish1DoSysOpt9);
			entity.setIsFinish1DoSysOpt10(isFinish1DoSysOpt10);
			entity.setFinish1DoSysOpt3Remark(finish1DoSysOpt3Remark);
			entity.setFinish1DoSysOpt6Remark(finish1DoSysOpt6Remark);
			entity.setFinish1DoSysOpt7Remark(finish1DoSysOpt7Remark);

			entity.setIsFinish1DoEduOpt1(isFinish1DoEduOpt1);
			entity.setIsFinish1DoEduOpt2(isFinish1DoEduOpt2);
			entity.setIsFinish1DoEduOpt3(isFinish1DoEduOpt3);
			entity.setIsFinish1DoEduOpt4(isFinish1DoEduOpt4);
			// step6-2
			entity.setFinish2Reason(finish2Reason);
			entity.setFinish2ReasonOther(finish2ReasonOther);
			entity.setFinish2ReasonRemark(finish2ReasonRemark);
			entity.setIsFinish2DoSysOpt1(isFinish2DoSysOpt1);
			entity.setIsFinish2DoSysOpt2(isFinish2DoSysOpt2);
			entity.setIsFinish2DoSysOpt3(isFinish2DoSysOpt3);
			entity.setIsFinish2DoSysOpt4(isFinish2DoSysOpt4);
			entity.setIsFinish2DoSysOpt5(isFinish2DoSysOpt5);
			entity.setFinish2DoSysOpt1Remark(finish2DoSysOpt1Remark);
			entity.setIsFinish2DoEduOpt1(isFinish2DoEduOpt1);
			entity.setIsFinish2DoEduOpt2(isFinish2DoEduOpt2);
			entity.setIsFinish2DoEduOpt3(isFinish2DoEduOpt3);
			entity.setIsFinish2DoEduOpt4(isFinish2DoEduOpt4);
			// step6-3
			entity.setIsFinish3DoSysOpt1(isFinish3DoSysOpt1);
			entity.setIsFinish3DoSysOpt2(isFinish3DoSysOpt2);
			entity.setIsFinish3DoSysOpt3(isFinish3DoSysOpt3);
			entity.setIsFinish3DoSysOpt4(isFinish3DoSysOpt4);
			entity.setFinish3DoSysOpt3Remark(finish3DoSysOpt3Remark);
			entity.setFinish3DoSysOpt4Remark(finish3DoSysOpt4Remark);
			entity.setIsFinish3DoEduOpt1(isFinish3DoEduOpt1);
			entity.setIsFinish3DoEduOpt2(isFinish3DoEduOpt2);
			// step6-4
			entity.setFinish4Reason(finish4Reason);
			entity.setFinish4ReasonOther(finish4ReasonOther);
			entity.setFinish4ReasonRemark(finish4ReasonRemark);
			entity.setIsFinish4DoSysOpt1(isFinish4DoSysOpt1);
			entity.setIsFinish4DoEduOpt1(isFinish4DoEduOpt1);
			entity.setIsFinish4DoEduOpt2(isFinish4DoEduOpt2);
			entity.setIsFinish4DoEduOpt3(isFinish4DoEduOpt3);
			entity.setIsFinish4DoEduOpt4(isFinish4DoEduOpt4);
			// step6-5
			entity.setFinish5Reason(finish5Reason);
			entity.setFinish5ReasonOther(finish5ReasonOther);
			entity.setFinish5ReasonRemark(finish5ReasonRemark);
			entity.setIsFinish5DoSysOpt1(isFinish5DoSysOpt1);
			entity.setIsFinish5DoSysOpt2(isFinish5DoSysOpt2);
			entity.setIsFinish5DoSysOpt3(isFinish5DoSysOpt3);
			entity.setIsFinish5DoSysOpt4(isFinish5DoSysOpt4);
			entity.setFinish5DoSysOpt1Remark(finish5DoSysOpt1Remark);
			entity.setIsFinish5DoEduOpt1(isFinish5DoEduOpt1);
			entity.setIsFinish5DoEduOpt2(isFinish5DoEduOpt2);
			entity.setIsFinish5DoEduOpt3(isFinish5DoEduOpt3);
			entity.setIsFinish5DoEduOpt4(isFinish5DoEduOpt4);
			// step6-...
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			// other...
			entity.setStatus(status);
			entity.setExamineDateTime(examineDateTime);
			entity.setRealFinishDateTime(realFinishDateTime);
			entity.setMessageId(messageId);
			entity.setMessagePostId(messagePostId);

			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			entity.setIsCC3(isCC3);
			entity.setIsCC5(isCC5);
			entity.setIsReview3(isReview3);
			entity.setIsReview5(isReview5);
			notificationDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新通報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            通報資料
	 * @return 通報資料
	 */
	public Notification update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			Notification notification = getById(id);
			Date applyDateTime = new Date();

			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");

			String postId = ticketQueueDAO.updatePostId(tableName, false, pre, notification.getPostId(), orgService.getDataById(memberService.get(memberId).getOrgId()).getCode());
			String strApplyDateTime = obj.isNull("ApplyDateTime") == true ? null : obj.getString("ApplyDateTime");
			if (strApplyDateTime != null && !strApplyDateTime.equals(""))
				applyDateTime = WebDatetime.parse(strApplyDateTime);

			// long contactorUnit = obj.isNull("ContactorUnit") == true ? 0 :
			// obj.getLong("ContactorUnit");
			// long mainUnit1 = obj.isNull("MainUnit1") == true ? 0 :
			// obj.getLong("MainUnit1");
			// long mainUnit2 = obj.isNull("MainUnit2") == true ? 0 :
			// obj.getLong("MainUnit2");
			// long contactorId = obj.isNull("ContactorId") == true ? 0 :
			// obj.getLong("ContactorId");
			String contactorTel = obj.isNull("ContactorTel") == true ? null : obj.getString("ContactorTel");
			String contactorFax = obj.isNull("ContactorFax") == true ? null : obj.getString("ContactorFax");
			String contactorEmail = obj.isNull("ContactorEmail") == true ? null : obj.getString("ContactorEmail");
			Boolean isSub = obj.isNull("IsSub") == true ? false : obj.getBoolean("IsSub");
			String isSubUnitName = obj.isNull("IsSubUnitName") == true ? null : obj.getString("IsSubUnitName");
			Date eventDateTime = new Date();
			String strEventDateTime = obj.isNull("EventDateTime") == true ? null : obj.getString("EventDateTime");
			if (strEventDateTime != null && !strEventDateTime.equals(""))
				eventDateTime = WebDatetime.parse(strEventDateTime);

			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isFinishDoSysOptRemark = obj.isNull("IsFinishDoSysOptRemark") == true ? false : obj.getBoolean("IsFinishDoSysOptRemark");
			Boolean isFinishDoEduOptRemark = obj.isNull("IsFinishDoEduOptRemark") == true ? false : obj.getBoolean("IsFinishDoEduOptRemark");
			String finishDoSysOptRemark = obj.isNull("FinishDoSysOptRemark") == true ? null : obj.getString("FinishDoSysOptRemark");
			String finishDoEduOptRemark = obj.isNull("FinishDoEduOptRemark") == true ? null : obj.getString("FinishDoEduOptRemark");
			Boolean isHandledByMyself = obj.isNull("IsHandledByMyself") == true ? false : obj.getBoolean("IsHandledByMyself");
			Long handleInformationId = obj.isNull("HandleInformationId") == true ? 0 : obj.getLong("HandleInformationId");
			
			Boolean isTest = obj.isNull("IsTest") == true ? false : obj.getBoolean("IsTest");
			
			String ipExternal = obj.isNull("IpExternal") == true ? null : obj.getString("IpExternal");
			String ipInternal = obj.isNull("IpInternal") == true ? null : obj.getString("IpInternal");
			String webUrl = obj.isNull("WebUrl") == true ? null : obj.getString("WebUrl");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");
			Boolean isGuardOpt1 = obj.isNull("IsGuardOpt1") == true ? false : obj.getBoolean("IsGuardOpt1");
			Boolean isGuardOpt2 = obj.isNull("IsGuardOpt2") == true ? false : obj.getBoolean("IsGuardOpt2");
			Boolean isGuardOpt3 = obj.isNull("IsGuardOpt3") == true ? false : obj.getBoolean("IsGuardOpt3");
			Boolean isGuardOpt4 = obj.isNull("IsGuardOpt4") == true ? false : obj.getBoolean("IsGuardOpt4");
			String isGuardOpt4Other = obj.isNull("IsGuardOpt4Other") == true ? null : obj.getString("IsGuardOpt4Other");
			int socOpt = obj.isNull("SocOpt") == true ? 0 : obj.getInt("SocOpt");
			String socOptCompany = obj.isNull("SocOptCompany") == true ? null : obj.getString("SocOptCompany");
			Boolean isIsms = obj.isNull("IsIsms") == true ? false : obj.getBoolean("IsIsms");
			String maintainCompany = obj.isNull("MaintainCompany") == true ? null : obj.getString("MaintainCompany");

			// step2
			Integer ceffectLevel = obj.isNull("CeffectLevel") == true ? null : obj.getInt("CeffectLevel");
			Integer ieffectLevel = obj.isNull("IeffectLevel") == true ? null : obj.getInt("IeffectLevel");
			Integer aeffectLevel = obj.isNull("AeffectLevel") == true ? null : obj.getInt("AeffectLevel");
			Integer effectLevel = obj.isNull("EffectLevel") == true ? null : obj.getInt("EffectLevel");
			// step3
			int eventType = obj.isNull("EventType") == true ? 0 : obj.getInt("EventType");
			Boolean isEventType1Opt1 = obj.isNull("IsEventType1Opt1") == true ? false : obj.getBoolean("IsEventType1Opt1");
			Boolean isEventType1Opt2 = obj.isNull("IsEventType1Opt2") == true ? false : obj.getBoolean("IsEventType1Opt2");
			Boolean isEventType1Opt3 = obj.isNull("IsEventType1Opt3") == true ? false : obj.getBoolean("IsEventType1Opt3");
			Boolean isEventType1Opt4 = obj.isNull("IsEventType1Opt4") == true ? false : obj.getBoolean("IsEventType1Opt4");
			Boolean isEventType1Opt5 = obj.isNull("IsEventType1Opt5") == true ? false : obj.getBoolean("IsEventType1Opt5");
			Boolean isEventType1Opt6 = obj.isNull("IsEventType1Opt6") == true ? false : obj.getBoolean("IsEventType1Opt6");
			Boolean isEventType2Opt1 = obj.isNull("IsEventType2Opt1") == true ? false : obj.getBoolean("IsEventType2Opt1");
			Boolean isEventType2Opt2 = obj.isNull("IsEventType2Opt2") == true ? false : obj.getBoolean("IsEventType2Opt2");
			Boolean isEventType2Opt3 = obj.isNull("IsEventType2Opt3") == true ? false : obj.getBoolean("IsEventType2Opt3");
			Boolean isEventType2Opt4 = obj.isNull("IsEventType2Opt4") == true ? false : obj.getBoolean("IsEventType2Opt4");
			Boolean isEventType2Opt5 = obj.isNull("IsEventType2Opt5") == true ? false : obj.getBoolean("IsEventType2Opt5");
			Boolean isEventType3Opt1 = obj.isNull("IsEventType3Opt1") == true ? false : obj.getBoolean("IsEventType3Opt1");
			Boolean isEventType3Opt2 = obj.isNull("IsEventType3Opt2") == true ? false : obj.getBoolean("IsEventType3Opt2");
			Boolean isEventType4Opt1 = obj.isNull("IsEventType4Opt1") == true ? false : obj.getBoolean("IsEventType4Opt1");
			Boolean isEventType4Opt2 = obj.isNull("IsEventType4Opt2") == true ? false : obj.getBoolean("IsEventType4Opt2");
			Boolean isEventType4Opt3 = obj.isNull("IsEventType4Opt3") == true ? false : obj.getBoolean("IsEventType4Opt3");
			Boolean isEventType4Opt4 = obj.isNull("IsEventType4Opt4") == true ? false : obj.getBoolean("IsEventType4Opt4");
			String eventType5Other = obj.isNull("EventType5Other") == true ? null : obj.getString("EventType5Other");
			String eventRemark = obj.isNull("EventRemark") == true ? null : obj.getString("EventRemark");
			Boolean isAffectOthers = obj.isNull("IsAffectOthers") == true ? false : obj.getBoolean("IsAffectOthers");
			Boolean affectOther1 = obj.optBoolean("AffectOther1", false);
			Boolean affectOther2 = obj.optBoolean("AffectOther2", false);
			Boolean affectOther3 = obj.optBoolean("AffectOther3", false);
			Boolean affectOther4 = obj.optBoolean("AffectOther4", false);
			Boolean affectOther5 = obj.optBoolean("AffectOther5", false);
			Boolean affectOther6 = obj.optBoolean("AffectOther6", false);
			Boolean affectOther7 = obj.optBoolean("AffectOther7", false);
			Boolean affectOther8 = obj.optBoolean("AffectOther8", false);
			int eventSource = obj.isNull("EventSource") == true ? 0 : obj.getInt("EventSource");
			String eventSourceNo = obj.isNull("EventSourceNo") == true ? null : obj.getString("EventSourceNo");
			String eventSourceOther = obj.optString("EventSourceOther", null);
			// step4-all
			String resDescription = obj.optString("ResDescription", null);
			Date resControlTime = new Date();
			String strResControlTime = obj.optString("ResControlTime", null);
			if (strResControlTime != null && !strResControlTime.equals(""))
				resControlTime = WebDatetime.parse(strResControlTime);
			int resResult = obj.optInt("ResResult", 0);
			// step4-1
			Boolean isRes1LogOpt1 = obj.isNull("IsRes1LogOpt1") == true ? false : obj.getBoolean("IsRes1LogOpt1");
			Boolean isRes1LogOpt2 = obj.isNull("IsRes1LogOpt2") == true ? false : obj.getBoolean("IsRes1LogOpt2");
			Boolean isRes1LogOpt5 = obj.optBoolean("IsRes1LogOpt5", false);
			Boolean isRes1LogOpt3 = obj.isNull("IsRes1LogOpt3") == true ? false : obj.getBoolean("IsRes1LogOpt3");
			Boolean isRes1LogOpt4 = obj.isNull("IsRes1LogOpt4") == true ? false : obj.getBoolean("IsRes1LogOpt4");
			int res1LogOpt1 = obj.isNull("Res1LogOpt1") == true ? 0 : obj.getInt("Res1LogOpt1");
			String res1LogOpt1Other = obj.isNull("Res1LogOpt1Other") == true ? null : obj.getString("Res1LogOpt1Other");
			int res1LogOpt2 = obj.isNull("Res1LogOpt2") == true ? 0 : obj.getInt("Res1LogOpt2");
			String res1LogOpt2Other = obj.isNull("Res1LogOpt2Other") == true ? null : obj.getString("Res1LogOpt2Other");
			int res1LogOpt5 = obj.optInt("Res1LogOpt5", 0);
			String res1LogOpt5Other = obj.optString("Res1LogOpt5Other", null);
			int res1LogOpt3Amount = obj.isNull("Res1LogOpt3Amount") == true ? 0 : obj.getInt("Res1LogOpt3Amount");
			String res1LogOpt4Remark = obj.isNull("Res1LogOpt4Remark") == true ? null : obj.getString("Res1LogOpt4Remark");
			Boolean isRes1EvaOpt1 = obj.isNull("IsRes1EvaOpt1") == true ? false : obj.getBoolean("IsRes1EvaOpt1");
			Boolean isRes1EvaOpt2 = obj.isNull("IsRes1EvaOpt2") == true ? false : obj.getBoolean("IsRes1EvaOpt2");
			Boolean isRes1EvaOpt3 = obj.isNull("IsRes1EvaOpt3") == true ? false : obj.getBoolean("IsRes1EvaOpt3");
			Boolean isRes1EvaOpt4 = obj.isNull("IsRes1EvaOpt4") == true ? false : obj.getBoolean("IsRes1EvaOpt4");
			Boolean isRes1EvaOpt5 = obj.isNull("IsRes1EvaOpt5") == true ? false : obj.getBoolean("IsRes1EvaOpt5");
			Boolean isRes1EvaOpt6 = obj.isNull("IsRes1EvaOpt6") == true ? false : obj.getBoolean("IsRes1EvaOpt6");
			Boolean isRes1EvaOpt7 = obj.isNull("IsRes1EvaOpt7") == true ? false : obj.getBoolean("IsRes1EvaOpt7");
			Boolean isRes1EvaOpt8 = obj.isNull("IsRes1EvaOpt8") == true ? false : obj.getBoolean("IsRes1EvaOpt8");
			String res1EvaOpt1Remark = obj.isNull("Res1EvaOpt1Remark") == true ? null : obj.getString("Res1EvaOpt1Remark");
			String res1EvaOpt2Remark = obj.isNull("Res1EvaOpt2Remark") == true ? null : obj.getString("Res1EvaOpt2Remark");
			String res1EvaOpt3Remark = obj.isNull("Res1EvaOpt3Remark") == true ? null : obj.getString("Res1EvaOpt3Remark");
			String res1EvaOpt4Remark = obj.isNull("Res1EvaOpt4Remark") == true ? null : obj.getString("Res1EvaOpt4Remark");
			int res1EvaOpt6Amount = obj.isNull("Res1EvaOpt6Amount") == true ? 0 : obj.getInt("Res1EvaOpt6Amount");
			String res1EvaOpt6Remark = obj.optString("Res1EvaOpt6Remark", null);
			int res1EvaOpt6Type = obj.isNull("Res1EvaOpt6Type") == true ? 0 : obj.getInt("Res1EvaOpt6Type");
			String res1EvaOpt6TypeOpt3Other = obj.isNull("Res1EvaOpt6TypeOpt3Other") == true ? null : obj.getString("Res1EvaOpt6TypeOpt3Other");
			int res1EvaOpt7Amount = obj.isNull("Res1EvaOpt7Amount") == true ? 0 : obj.getInt("Res1EvaOpt7Amount");
			String res1EvaOpt7Remark = obj.isNull("Res1EvaOpt7Remark") == true ? null : obj.getString("Res1EvaOpt7Remark");
			String res1EvaOpt8Remark = obj.isNull("Res1EvaOpt8Remark") == true ? null : obj.getString("Res1EvaOpt8Remark");

			Boolean isRes1DoOpt1 = obj.isNull("IsRes1DoOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt1");
			Boolean isRes1DoOpt2 = obj.isNull("IsRes1DoOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt2");
			Boolean isRes1DoOpt3 = obj.isNull("IsRes1DoOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt3");
			Boolean isRes1DoOpt4 = obj.isNull("IsRes1DoOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt4");
			Boolean isRes1DoOpt5 = obj.isNull("IsRes1DoOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt5");
			Boolean isRes1DoOpt6 = obj.isNull("IsRes1DoOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt6");
			Boolean isRes1DoOpt7 = obj.isNull("IsRes1DoOpt7") == true ? false : obj.getBoolean("IsRes1DoOpt7");
			Boolean isRes1DoOpt8 = obj.isNull("IsRes1DoOpt8") == true ? false : obj.getBoolean("IsRes1DoOpt8");
			Boolean isRes1DoOpt9 = obj.isNull("IsRes1DoOpt9") == true ? false : obj.getBoolean("IsRes1DoOpt9");
			Boolean isRes1DoOpt10 = obj.isNull("IsRes1DoOpt10") == true ? false : obj.getBoolean("IsRes1DoOpt10");
			Boolean isRes1DoOpt11 = obj.isNull("IsRes1DoOpt11") == true ? false : obj.getBoolean("IsRes1DoOpt11");
			Boolean isRes1DoOpt12 = obj.isNull("IsRes1DoOpt12") == true ? false : obj.getBoolean("IsRes1DoOpt12");
			int res1DoOpt1Amount = obj.isNull("Res1DoOpt1Amount") == true ? 0 : obj.getInt("Res1DoOpt1Amount");
			String res1DoOpt2Remark = obj.isNull("Res1DoOpt2Remark") == true ? null : obj.getString("Res1DoOpt2Remark");
			String res1DoOpt3Remark = obj.isNull("Res1DoOpt3Remark") == true ? null : obj.getString("Res1DoOpt3Remark");
			String res1DoOpt4Remark = obj.isNull("Res1DoOpt4Remark") == true ? null : obj.getString("Res1DoOpt4Remark");
			int res1DoOpt5Amount = obj.isNull("Res1DoOpt5Amount") == true ? 0 : obj.getInt("Res1DoOpt5Amount");
			Boolean isRes1DoOpt9EngineOpt1 = obj.isNull("IsRes1DoOpt9EngineOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt1");
			Boolean isRes1DoOpt9EngineOpt2 = obj.isNull("IsRes1DoOpt9EngineOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt2");
			Boolean isRes1DoOpt9EngineOpt3 = obj.isNull("IsRes1DoOpt9EngineOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt3");
			Boolean isRes1DoOpt9EngineOpt4 = obj.isNull("IsRes1DoOpt9EngineOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt4");
			Boolean isRes1DoOpt9EngineOpt5 = obj.isNull("IsRes1DoOpt9EngineOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt5");
			Boolean isRes1DoOpt9EngineOpt6 = obj.isNull("IsRes1DoOpt9EngineOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt6");
			String res1DoOpt9EngineOpt6Other = obj.isNull("Res1DoOpt9EngineOpt6Other") == true ? null : obj.getString("Res1DoOpt9EngineOpt6Other");

			Date res1DoOpt10Date = new Date();
			String strRes1DoOpt10Date = obj.isNull("Res1DoOpt10Date") == true ? null : obj.getString("Res1DoOpt10Date");
			if (strRes1DoOpt10Date != null && !strRes1DoOpt10Date.equals(""))
				res1DoOpt10Date = WebDatetime.parse(strRes1DoOpt10Date + " 00:00:00");

			Date res1DoOpt11Date = new Date();
			String strRes1DoOpt11Date = obj.isNull("Res1DoOpt11Date") == true ? null : obj.getString("Res1DoOpt11Date");
			if (strRes1DoOpt11Date != null && !strRes1DoOpt11Date.equals(""))
				res1DoOpt11Date = WebDatetime.parse(strRes1DoOpt11Date + " 00:00:00");

			String res1DoOpt12Remark = obj.isNull("Res1DoOpt12Remark") == true ? null : obj.getString("Res1DoOpt12Remark");

			// step4-2
			Boolean isRes2LogOpt1 = obj.isNull("IsRes2LogOpt1") == true ? false : obj.getBoolean("IsRes2LogOpt1");
			Boolean isRes2LogOpt2 = obj.isNull("IsRes2LogOpt2") == true ? false : obj.getBoolean("IsRes2LogOpt2");
			Boolean isRes2LogOpt3 = obj.isNull("IsRes2LogOpt3") == true ? false : obj.getBoolean("IsRes2LogOpt3");
			Boolean isRes2LogOpt4 = obj.isNull("IsRes2LogOpt4") == true ? false : obj.getBoolean("IsRes2LogOpt4");
			int res2LogOpt1 = obj.isNull("Res2LogOpt1") == true ? 0 : obj.getInt("Res2LogOpt1");
			String res2LogOpt1Other = obj.isNull("Res2LogOpt1Other") == true ? null : obj.getString("Res2LogOpt1Other");
			int res2LogOpt2 = obj.isNull("Res2LogOpt2") == true ? 0 : obj.getInt("Res2LogOpt2");
			String res2LogOpt2Other = obj.isNull("Res2LogOpt2Other") == true ? null : obj.getString("Res2LogOpt2Other");
			int res2LogOpt3Amount = obj.isNull("Res2LogOpt3Amount") == true ? 0 : obj.getInt("Res2LogOpt3Amount");
			String res2LogOpt4Remark = obj.isNull("Res2LogOpt4Remark") == true ? null : obj.getString("Res2LogOpt4Remark");
			Boolean isRes2EvaOpt1 = obj.isNull("IsRes2EvaOpt1") == true ? false : obj.getBoolean("IsRes2EvaOpt1");
			Boolean isRes2EvaOpt2 = obj.isNull("IsRes2EvaOpt2") == true ? false : obj.getBoolean("IsRes2EvaOpt2");
			Boolean isRes2EvaOpt3 = obj.isNull("IsRes2EvaOpt3") == true ? false : obj.getBoolean("IsRes2EvaOpt3");
			Boolean isRes2EvaOpt4 = obj.isNull("IsRes2EvaOpt4") == true ? false : obj.getBoolean("IsRes2EvaOpt4");
			Boolean isRes2EvaOpt5 = obj.isNull("IsRes2EvaOpt5") == true ? false : obj.getBoolean("IsRes2EvaOpt5");
			String res2EvaOpt1Remark = obj.isNull("Res2EvaOpt1Remark") == true ? null : obj.getString("Res2EvaOpt1Remark");
			String res2EvaOpt2Remark = obj.isNull("Res2EvaOpt2Remark") == true ? null : obj.getString("Res2EvaOpt2Remark");
			String res2EvaOpt3Remark = obj.isNull("Res2EvaOpt3Remark") == true ? null : obj.getString("Res2EvaOpt3Remark");
			String res2EvaOpt4Remark = obj.isNull("Res2EvaOpt4Remark") == true ? null : obj.getString("Res2EvaOpt4Remark");
			String res2EvaOpt5Remark = obj.isNull("Res2EvaOpt5Remark") == true ? null : obj.getString("Res2EvaOpt5Remark");
			Boolean isRes2DoOpt1 = obj.isNull("IsRes2DoOpt1") == true ? false : obj.getBoolean("IsRes2DoOpt1");
			Boolean isRes2DoOpt2 = obj.isNull("IsRes2DoOpt2") == true ? false : obj.getBoolean("IsRes2DoOpt2");
			Boolean isRes2DoOpt3 = obj.isNull("IsRes2DoOpt3") == true ? false : obj.getBoolean("IsRes2DoOpt3");
			Boolean isRes2DoOpt4 = obj.isNull("IsRes2DoOpt4") == true ? false : obj.getBoolean("IsRes2DoOpt4");
			Boolean isRes2DoOpt5 = obj.isNull("IsRes2DoOpt5") == true ? false : obj.getBoolean("IsRes2DoOpt5");
			Boolean isRes2DoOpt6 = obj.isNull("IsRes2DoOpt6") == true ? false : obj.getBoolean("IsRes2DoOpt6");
			Boolean isRes2DoOpt7 = obj.isNull("IsRes2DoOpt7") == true ? false : obj.getBoolean("IsRes2DoOpt7");
			Integer res2DoOpt1Amount = obj.optInt("Res2DoOpt1Amount", 0);
			String res2DoOpt1Remark = obj.isNull("Res2DoOpt1Remark") == true ? null : obj.getString("Res2DoOpt1Remark");
			String res2DoOpt2Remark = obj.isNull("Res2DoOpt2Remark") == true ? null : obj.getString("Res2DoOpt2Remark");
			String res2DoOpt3Remark = obj.isNull("Res2DoOpt3Remark") == true ? null : obj.getString("Res2DoOpt3Remark");

			Date res2DoOpt5Date = new Date();
			String strRes2DoOpt5Date = obj.optString("Res2DoOpt5Date", null);
			if (strRes2DoOpt5Date != null && !strRes2DoOpt5Date.equals(""))
				res2DoOpt5Date = WebDatetime.parse(strRes2DoOpt5Date + " 00:00:00");

			int res2DoOpt6Amount = obj.isNull("Res2DoOpt6Amount") == true ? 0 : obj.getInt("Res2DoOpt6Amount");
			String res2DoOpt7Remark = obj.isNull("Res2DoOpt7Remark") == true ? null : obj.getString("Res2DoOpt7Remark");
			// step4-3
			Boolean isRes3LogOpt1 = obj.isNull("IsRes3LogOpt1") == true ? false : obj.getBoolean("IsRes3LogOpt1");
			Boolean isRes3LogOpt2 = obj.isNull("IsRes3LogOpt2") == true ? false : obj.getBoolean("IsRes3LogOpt2");
			Boolean isRes3LogOpt3 = obj.isNull("IsRes3LogOpt3") == true ? false : obj.getBoolean("IsRes3LogOpt3");
			Boolean isRes3LogOpt4 = obj.isNull("IsRes3LogOpt4") == true ? false : obj.getBoolean("IsRes3LogOpt4");
			int res3LogOpt1 = obj.isNull("Res3LogOpt1") == true ? 0 : obj.getInt("Res3LogOpt1");
			String res3LogOpt1Other = obj.isNull("Res3LogOpt1Other") == true ? null : obj.getString("Res3LogOpt1Other");
			int res3LogOpt2 = obj.isNull("Res3LogOpt2") == true ? 0 : obj.getInt("Res3LogOpt2");
			String res3LogOpt2Other = obj.isNull("Res3LogOpt2Other") == true ? null : obj.getString("Res3LogOpt2Other");
			int res3LogOpt3Amount = obj.isNull("Res3LogOpt3Amount") == true ? 0 : obj.getInt("Res3LogOpt3Amount");
			String res3LogOpt4Remark = obj.isNull("Res3LogOpt4Remark") == true ? null : obj.getString("Res3LogOpt4Remark");
			Boolean isRes3EvaOpt1 = obj.isNull("IsRes3EvaOpt1") == true ? false : obj.getBoolean("IsRes3EvaOpt1");
			Boolean isRes3EvaOpt2 = obj.isNull("IsRes3EvaOpt2") == true ? false : obj.getBoolean("IsRes3EvaOpt2");
			Boolean isRes3EvaOpt3 = obj.isNull("IsRes3EvaOpt3") == true ? false : obj.getBoolean("IsRes3EvaOpt3");
			int res3EvaOpt1Amount = obj.isNull("Res3EvaOpt1Amount") == true ? 0 : obj.getInt("Res3EvaOpt1Amount");
			String res3EvaOpt2Remark = obj.isNull("Res3EvaOpt2Remark") == true ? null : obj.getString("Res3EvaOpt2Remark");
			String res3EvaOpt3Remark = obj.isNull("Res3EvaOpt3Remark") == true ? null : obj.getString("Res3EvaOpt3Remark");
			Boolean isRes3DoOpt1 = obj.isNull("IsRes3DoOpt1") == true ? false : obj.getBoolean("IsRes3DoOpt1");
			Boolean isRes3DoOpt2 = obj.isNull("IsRes3DoOpt2") == true ? false : obj.getBoolean("IsRes3DoOpt2");
			Boolean isRes3DoOpt3 = obj.isNull("IsRes3DoOpt3") == true ? false : obj.getBoolean("IsRes3DoOpt3");
			Boolean isRes3DoOpt4 = obj.isNull("IsRes3DoOpt4") == true ? false : obj.getBoolean("IsRes3DoOpt4");
			String res3DoOpt1Remark = obj.isNull("Res3DoOpt1Remark") == true ? null : obj.getString("Res3DoOpt1Remark");
			String res3DoOpt3Remark = obj.isNull("Res3DoOpt3Remark") == true ? null : obj.getString("Res3DoOpt3Remark");
			String res3DoOpt4Remark = obj.isNull("Res3DoOpt4Remark") == true ? null : obj.getString("Res3DoOpt4Remark");
			// step4-4
			Boolean isRes4LogOpt1 = obj.isNull("IsRes4LogOpt1") == true ? false : obj.getBoolean("IsRes4LogOpt1");
			String res4LogOpt1Remark = obj.isNull("Res4LogOpt1Remark") == true ? null : obj.getString("Res4LogOpt1Remark");
			Boolean isRes4EvaOpt1 = obj.isNull("IsRes4EvaOpt1") == true ? false : obj.getBoolean("IsRes4EvaOpt1");
			Boolean isRes4EvaOpt2 = obj.isNull("IsRes4EvaOpt2") == true ? false : obj.getBoolean("IsRes4EvaOpt2");
			Boolean isRes4EvaOpt3 = obj.optBoolean("IsRes4EvaOpt3", false);
			int res4EvaOpt1 = obj.isNull("Res4EvaOpt1") == true ? 0 : obj.getInt("Res4EvaOpt1");
			int res4EvaOpt1Amount = obj.isNull("Res4EvaOpt1Amount") == true ? 0 : obj.getInt("Res4EvaOpt1Amount");
			String res4EvaOpt2Remark = obj.isNull("Res4EvaOpt2Remark") == true ? null : obj.getString("Res4EvaOpt2Remark");
			String res4EvaOpt3Remark = obj.optString("Res4EvaOpt3Remark", null);
			Boolean isRes4DoOpt1 = obj.isNull("IsRes4DoOpt1") == true ? false : obj.getBoolean("IsRes4DoOpt1");
			Boolean isRes4DoOpt2 = obj.isNull("IsRes4DoOpt2") == true ? false : obj.getBoolean("IsRes4DoOpt2");
			Boolean isRes4DoOpt4 = obj.optBoolean("IsRes4DoOpt2", false);
			Boolean isRes4DoOpt3 = obj.isNull("IsRes4DoOpt3") == true ? false : obj.getBoolean("IsRes4DoOpt3");
			String res4DoOpt3Remark = obj.isNull("Res4DoOpt3Remark") == true ? null : obj.getString("Res4DoOpt3Remark");
			// step4-5
			Boolean isRes5LogOpt1 = obj.isNull("IsRes5LogOpt1") == true ? false : obj.getBoolean("IsRes5LogOpt1");
			Boolean isRes5LogOpt2 = obj.isNull("IsRes5LogOpt2") == true ? false : obj.getBoolean("IsRes5LogOpt2");
			Boolean isRes5LogOpt3 = obj.isNull("IsRes5LogOpt3") == true ? false : obj.getBoolean("IsRes5LogOpt3");
			Boolean isRes5LogOpt4 = obj.isNull("IsRes5LogOpt4") == true ? false : obj.getBoolean("IsRes5LogOpt4");
			int res5LogOpt1 = obj.isNull("Res5LogOpt1") == true ? 0 : obj.getInt("Res5LogOpt1");
			String res5LogOpt1Other = obj.isNull("Res5LogOpt1Other") == true ? null : obj.getString("Res5LogOpt1Other");
			int res5LogOpt2 = obj.isNull("Res5LogOpt2") == true ? 0 : obj.getInt("Res5LogOpt2");
			String res5LogOpt2Other = obj.isNull("Res5LogOpt2Other") == true ? null : obj.getString("Res5LogOpt2Other");
			int res5LogOpt3Amount = obj.isNull("Res5LogOpt3Amount") == true ? 0 : obj.getInt("Res5LogOpt3Amount");
			String res5LogOpt4Remark = obj.isNull("Res5LogOpt4Remark") == true ? null : obj.getString("Res5LogOpt4Remark");
			Boolean isRes5EvaOpt1 = obj.isNull("IsRes5EvaOpt1") == true ? false : obj.getBoolean("IsRes5EvaOpt1");
			Boolean isRes5EvaOpt2 = obj.isNull("IsRes5EvaOpt2") == true ? false : obj.getBoolean("IsRes5EvaOpt2");
			Boolean isRes5EvaOpt3 = obj.isNull("IsRes5EvaOpt3") == true ? false : obj.getBoolean("IsRes5EvaOpt3");
			Boolean isRes5EvaOpt4 = obj.isNull("IsRes5EvaOpt4") == true ? false : obj.getBoolean("IsRes5EvaOpt4");
			Boolean isRes5EvaOpt5 = obj.isNull("IsRes5EvaOpt5") == true ? false : obj.getBoolean("IsRes5EvaOpt5");
			String res5EvaOpt1Remark = obj.isNull("Res5EvaOpt1Remark") == true ? null : obj.getString("Res5EvaOpt1Remark");
			String res5EvaOpt2Remark = obj.isNull("Res5EvaOpt2Remark") == true ? null : obj.getString("Res5EvaOpt2Remark");
			String res5EvaOpt3Remark = obj.isNull("Res5EvaOpt3Remark") == true ? null : obj.getString("Res5EvaOpt3Remark");
			String res5EvaOpt4Remark = obj.isNull("Res5EvaOpt4Remark") == true ? null : obj.getString("Res5EvaOpt4Remark");
			String res5EvaOpt5Remark = obj.isNull("Res5EvaOpt5Remark") == true ? null : obj.getString("Res5EvaOpt5Remark");
			Boolean isRes5DoOpt1 = obj.isNull("IsRes5DoOpt1") == true ? false : obj.getBoolean("IsRes5DoOpt1");
			Boolean isRes5DoOpt2 = obj.isNull("IsRes5DoOpt2") == true ? false : obj.getBoolean("IsRes5DoOpt2");
			Boolean isRes5DoOpt3 = obj.isNull("IsRes5DoOpt3") == true ? false : obj.getBoolean("IsRes5DoOpt3");
			Boolean isRes5DoOpt4 = obj.isNull("IsRes5DoOpt4") == true ? false : obj.getBoolean("IsRes5DoOpt4");
			Boolean isRes5DoOpt5 = obj.isNull("IsRes5DoOpt5") == true ? false : obj.getBoolean("IsRes5DoOpt5");
			Boolean isRes5DoOpt6 = obj.isNull("IsRes5DoOpt6") == true ? false : obj.getBoolean("IsRes5DoOpt6");
			Boolean isRes5DoOpt7 = obj.isNull("IsRes5DoOpt7") == true ? false : obj.getBoolean("IsRes5DoOpt7");
			Integer res5DoOpt1Amount = obj.optInt("Res5DoOpt1Amount", 0);
			String res5DoOpt1Remark = obj.isNull("Res5DoOpt1Remark") == true ? null : obj.getString("Res5DoOpt1Remark");
			String res5DoOpt2Remark = obj.isNull("Res5DoOpt2Remark") == true ? null : obj.getString("Res5DoOpt2Remark");
			String res5DoOpt3Remark = obj.isNull("Res5DoOpt3Remark") == true ? null : obj.getString("Res5DoOpt3Remark");

			Date res5DoOpt5Date = new Date();
			String strRes5DoOpt5Date = obj.isNull("Res5DoOpt5Date") == true ? null : obj.getString("Res5DoOpt5Date");
			if (strRes5DoOpt5Date != null && !strRes5DoOpt5Date.equals(""))
				res5DoOpt5Date = WebDatetime.parse(strRes5DoOpt5Date + " 00:00:00");

			int res5DoOpt6Amount = obj.isNull("Res5DoOpt6Amount") == true ? 0 : obj.getInt("Res5DoOpt6Amount");
			String res5DoOpt7Remark = obj.isNull("Res5DoOpt7Remark") == true ? null : obj.getString("Res5DoOpt7Remark");
			// step5
			Boolean isNeedSupport = obj.isNull("IsNeedSupport") == true ? false : obj.getBoolean("IsNeedSupport");
			String needSupportRemark = obj.isNull("NeedSupportRemark") == true ? null : obj.getString("NeedSupportRemark");

			// step6-1
			int finish1Reason = obj.isNull("Finish1Reason") == true ? 0 : obj.getInt("Finish1Reason");
			String finish1ReasonOther = obj.isNull("Finish1ReasonOther") == true ? null : obj.getString("Finish1ReasonOther");
			String finish1ReasonToDo = obj.optString("Finish1ReasonToDo", null);
			Boolean isFinish1DoSysOpt1 = obj.isNull("IsFinish1DoSysOpt1") == true ? false : obj.getBoolean("IsFinish1DoSysOpt1");
			Boolean isFinish1DoSysOpt2 = obj.isNull("IsFinish1DoSysOpt2") == true ? false : obj.getBoolean("IsFinish1DoSysOpt2");
			Boolean isFinish1DoSysOpt3 = obj.isNull("IsFinish1DoSysOpt3") == true ? false : obj.getBoolean("IsFinish1DoSysOpt3");
			Boolean isFinish1DoSysOpt4 = obj.isNull("IsFinish1DoSysOpt4") == true ? false : obj.getBoolean("IsFinish1DoSysOpt4");
			Boolean isFinish1DoSysOpt5 = obj.isNull("IsFinish1DoSysOpt5") == true ? false : obj.getBoolean("IsFinish1DoSysOpt5");
			Boolean isFinish1DoSysOpt6 = obj.isNull("IsFinish1DoSysOpt6") == true ? false : obj.getBoolean("IsFinish1DoSysOpt6");
			Boolean isFinish1DoSysOpt7 = obj.isNull("IsFinish1DoSysOpt7") == true ? false : obj.getBoolean("IsFinish1DoSysOpt7");
			Boolean isFinish1DoSysOpt8 = obj.isNull("IsFinish1DoSysOpt8") == true ? false : obj.getBoolean("IsFinish1DoSysOpt8");
			Boolean isFinish1DoSysOpt9 = obj.isNull("IsFinish1DoSysOpt9") == true ? false : obj.getBoolean("IsFinish1DoSysOpt9");
			Boolean isFinish1DoSysOpt10 = obj.isNull("IsFinish1DoSysOpt10") == true ? false : obj.getBoolean("IsFinish1DoSysOpt10");
			String finish1DoSysOpt3Remark = obj.isNull("Finish1DoSysOpt3Remark") == true ? null : obj.getString("Finish1DoSysOpt3Remark");
			String finish1DoSysOpt6Remark = obj.isNull("Finish1DoSysOpt6Remark") == true ? null : obj.getString("Finish1DoSysOpt6Remark");
			String finish1DoSysOpt7Remark = obj.isNull("Finish1DoSysOpt7Remark") == true ? null : obj.getString("Finish1DoSysOpt7Remark");
			Boolean isFinish1DoEduOpt1 = obj.isNull("IsFinish1DoEduOpt1") == true ? false : obj.getBoolean("IsFinish1DoEduOpt1");
			Boolean isFinish1DoEduOpt2 = obj.isNull("IsFinish1DoEduOpt2") == true ? false : obj.getBoolean("IsFinish1DoEduOpt2");
			Boolean isFinish1DoEduOpt3 = obj.isNull("IsFinish1DoEduOpt3") == true ? false : obj.getBoolean("IsFinish1DoEduOpt3");
			Boolean isFinish1DoEduOpt4 = obj.isNull("IsFinish1DoEduOpt4") == true ? false : obj.getBoolean("IsFinish1DoEduOpt4");
			// step6-2
			int finish2Reason = obj.isNull("Finish2Reason") == true ? 0 : obj.getInt("Finish2Reason");
			String finish2ReasonOther = obj.isNull("Finish2ReasonOther") == true ? null : obj.getString("Finish2ReasonOther");
			String finish2ReasonRemark = obj.isNull("Finish2ReasonRemark") == true ? null : obj.getString("Finish2ReasonRemark");
			Boolean isFinish2DoSysOpt1 = obj.isNull("IsFinish2DoSysOpt1") == true ? false : obj.getBoolean("IsFinish2DoSysOpt1");
			Boolean isFinish2DoSysOpt2 = obj.isNull("IsFinish2DoSysOpt2") == true ? false : obj.getBoolean("IsFinish2DoSysOpt2");
			Boolean isFinish2DoSysOpt3 = obj.isNull("IsFinish2DoSysOpt3") == true ? false : obj.getBoolean("IsFinish2DoSysOpt3");
			Boolean isFinish2DoSysOpt4 = obj.isNull("IsFinish2DoSysOpt4") == true ? false : obj.getBoolean("IsFinish2DoSysOpt4");
			Boolean isFinish2DoSysOpt5 = obj.isNull("IsFinish2DoSysOpt5") == true ? false : obj.getBoolean("IsFinish2DoSysOpt5");
			String finish2DoSysOpt1Remark = obj.isNull("Finish2DoSysOpt1Remark") == true ? null : obj.getString("Finish2DoSysOpt1Remark");
			Boolean isFinish2DoEduOpt1 = obj.isNull("IsFinish2DoEduOpt1") == true ? false : obj.getBoolean("IsFinish2DoEduOpt1");
			Boolean isFinish2DoEduOpt2 = obj.isNull("IsFinish2DoEduOpt2") == true ? false : obj.getBoolean("IsFinish2DoEduOpt2");
			Boolean isFinish2DoEduOpt3 = obj.isNull("IsFinish2DoEduOpt3") == true ? false : obj.getBoolean("IsFinish2DoEduOpt3");
			Boolean isFinish2DoEduOpt4 = obj.isNull("IsFinish2DoEduOpt4") == true ? false : obj.getBoolean("IsFinish2DoEduOpt4");
			// step6-3
			Boolean isFinish3DoSysOpt1 = obj.isNull("IsFinish3DoSysOpt1") == true ? false : obj.getBoolean("IsFinish3DoSysOpt1");
			Boolean isFinish3DoSysOpt2 = obj.isNull("IsFinish3DoSysOpt2") == true ? false : obj.getBoolean("IsFinish3DoSysOpt2");
			Boolean isFinish3DoSysOpt3 = obj.isNull("IsFinish3DoSysOpt3") == true ? false : obj.getBoolean("IsFinish3DoSysOpt3");
			Boolean isFinish3DoSysOpt4 = obj.isNull("IsFinish3DoSysOpt4") == true ? false : obj.getBoolean("IsFinish3DoSysOpt4");
			String finish3DoSysOpt3Remark = obj.isNull("Finish3DoSysOpt3Remark") == true ? null : obj.getString("Finish3DoSysOpt3Remark");
			String finish3DoSysOpt4Remark = obj.isNull("Finish3DoSysOpt4Remark") == true ? null : obj.getString("Finish3DoSysOpt4Remark");
			Boolean isFinish3DoEduOpt1 = obj.isNull("IsFinish3DoEduOpt1") == true ? false : obj.getBoolean("IsFinish3DoEduOpt1");
			Boolean isFinish3DoEduOpt2 = obj.isNull("IsFinish3DoEduOpt2") == true ? false : obj.getBoolean("IsFinish3DoEduOpt2");
			// step6-4
			int finish4Reason = obj.isNull("Finish4Reason") == true ? 0 : obj.getInt("Finish4Reason");
			String finish4ReasonOther = obj.isNull("Finish4ReasonOther") == true ? null : obj.getString("Finish4ReasonOther");
			String finish4ReasonRemark = obj.isNull("Finish4ReasonRemark") == true ? null : obj.getString("Finish4ReasonRemark");
			Boolean isFinish4DoSysOpt1 = obj.isNull("IsFinish4DoSysOpt1") == true ? false : obj.getBoolean("IsFinish4DoSysOpt1");
			Boolean isFinish4DoEduOpt1 = obj.isNull("IsFinish4DoEduOpt1") == true ? false : obj.getBoolean("IsFinish4DoEduOpt1");
			Boolean isFinish4DoEduOpt2 = obj.isNull("IsFinish4DoEduOpt2") == true ? false : obj.getBoolean("IsFinish4DoEduOpt2");
			Boolean isFinish4DoEduOpt3 = obj.isNull("IsFinish4DoEduOpt3") == true ? false : obj.getBoolean("IsFinish4DoEduOpt3");
			Boolean isFinish4DoEduOpt4 = obj.isNull("IsFinish4DoEduOpt4") == true ? false : obj.getBoolean("IsFinish4DoEduOpt4");
			// step6-5
			int finish5Reason = obj.isNull("Finish5Reason") == true ? 0 : obj.getInt("Finish5Reason");
			String finish5ReasonOther = obj.isNull("Finish5ReasonOther") == true ? null : obj.getString("Finish5ReasonOther");
			String finish5ReasonRemark = obj.isNull("Finish5ReasonRemark") == true ? null : obj.getString("Finish5ReasonRemark");
			Boolean isFinish5DoSysOpt1 = obj.isNull("IsFinish5DoSysOpt1") == true ? false : obj.getBoolean("IsFinish5DoSysOpt1");
			Boolean isFinish5DoSysOpt2 = obj.isNull("IsFinish5DoSysOpt2") == true ? false : obj.getBoolean("IsFinish5DoSysOpt2");
			Boolean isFinish5DoSysOpt3 = obj.isNull("IsFinish5DoSysOpt3") == true ? false : obj.getBoolean("IsFinish5DoSysOpt3");
			Boolean isFinish5DoSysOpt4 = obj.isNull("IsFinish5DoSysOpt4") == true ? false : obj.getBoolean("IsFinish5DoSysOpt4");
			String finish5DoSysOpt1Remark = obj.isNull("Finish5DoSysOpt1Remark") == true ? null : obj.getString("Finish5DoSysOpt1Remark");
			Boolean isFinish5DoEduOpt1 = obj.isNull("IsFinish5DoEduOpt1") == true ? false : obj.getBoolean("IsFinish5DoEduOpt1");
			Boolean isFinish5DoEduOpt2 = obj.isNull("IsFinish5DoEduOpt2") == true ? false : obj.getBoolean("IsFinish5DoEduOpt2");
			Boolean isFinish5DoEduOpt3 = obj.isNull("IsFinish5DoEduOpt3") == true ? false : obj.getBoolean("IsFinish5DoEduOpt3");
			Boolean isFinish5DoEduOpt4 = obj.isNull("IsFinish5DoEduOpt4") == true ? false : obj.getBoolean("IsFinish5DoEduOpt4");
			// step6-...
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");

			Date finishDateTime = new Date();
			String strFinishDateTime = obj.isNull("FinishDateTime") == true ? null : obj.getString("FinishDateTime");
			if (strFinishDateTime != null && !strFinishDateTime.equals(""))
				finishDateTime = WebDatetime.parse(strFinishDateTime);
			// other...
			int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");

			Date examineDateTime = new Date();
			String strExamineDateTime = obj.isNull("ExamineDateTime") == true ? null : obj.getString("ExamineDateTime");
			if (strExamineDateTime != null && !strExamineDateTime.equals(""))
				examineDateTime = WebDatetime.parse(strExamineDateTime);

			Date realFinishDateTime = new Date();
			String strRealFinishDateTime = obj.isNull("RealFinishDateTime") == true ? null : obj.getString("RealFinishDateTime");
			if (strRealFinishDateTime != null && !strRealFinishDateTime.equals(""))
				realFinishDateTime = WebDatetime.parse(strRealFinishDateTime);

			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");
			String messagePostId = obj.isNull("MessagePostId") == true ? null : obj.getString("MessagePostId");

			Boolean isCC3 = obj.isNull("IsCC3") == true ? false : obj.getBoolean("IsCC3");
			Boolean isReview3 = obj.isNull("IsReview3") == true ? false : obj.getBoolean("IsReview3");
			
			Boolean isCC5 = obj.isNull("IsCC5") == true ? false : obj.getBoolean("IsCC5");
			Boolean isReview5 = obj.isNull("IsReview5") == true ? false : obj.getBoolean("IsReview5");

			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			Notification entity = notificationDAO.get(id);
			
			if (isWriteProcessLog) {				
				
				boolean isNeedSaleReview = false;
				// 寄信
				List<OrgSign> orgSigns = orgSignService.getByOrgId(memberService.get(memberId).getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member member : members) {
								if (member.getIsEnable()) {
									List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
									if (memberRoles != null) {
										for (ViewMemberRoleMember memberRole : memberRoles) {
											if (memberRole.getMemberId().equals(member.getId())) {
												Org org = orgService.getDataById(member.getOrgId());
												if (org.getAuthType().equals("2") && org.getIsEnable()) {
													if (isCC3) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Body"), member.getName(), memberService.get(memberId).getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
													}
													if (isReview3) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
														status = 31;
													}
												}
												if (!isReview3 && org.getAuthType().equals("1") && org.getIsEnable()) {
													if (orgSign.getNotificationIsExamine().equals(1) || orgSign.getNotificationIsExamine().equals(3)) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
														isNeedSaleReview = true;
														status = 32;
													}
													if (orgSign.getNotificationIsExamine().equals(2)) {
														// 寄信
														String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Subject"), postId);
														String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Body"), member.getName(), memberService.get(memberId).getName(), postId);
														mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (!isNeedSaleReview && !isReview3) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(12);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), postId);
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), member.getName(), postId);
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
								status = 33;
							}
						}
					}
				}
				//sms TO H-ISAC通報審核者
				List<ViewMemberRoleMember> viewMemberRoleMembers = memberRoleService.findByRoleId(12);
				String contactorUnitName = orgService.getDataById(entity.getContactorUnit()).getName();				
				if (viewMemberRoleMembers != null) {					
					for (ViewMemberRoleMember viewMemberRoleMember : viewMemberRoleMembers) {
						Member memberSms = memberService.get(viewMemberRoleMember.getMemberId());
						if (true && memberSms.getMobilePhone() != null && !memberSms.getMobilePhone().trim().equals("")) {
							String smsMessage = MessageFormat.format(resourceMessageService.getMessageValue("mailNotificationSms"), contactorUnitName, entity.getPostId(), 
									status==2?"[撤銷中]":status==31?"上級機關[通報審核中]":status==32?"業務管理機關[通報審核中]":status==33?"HCERT[通報審核中]"
											:status==4?"會員機構[處理中]"
											:status==51?"上級機關[處理審核中]":status==52?"業務管理機關[處理審核中]":status==53?"HCERT[處理審核中]"
											:status==6?"[已結案]":status==7?"[已銷案]":status==8?"會員機構[編輯中(退回補述)]":"會員機構[處理中(退回補述)]");
							smsService.Send("[測試]", memberSms.getMobilePhone(), smsMessage);
						}
					}
				}			
			}

			Date now = new Date();			
			// step1
			entity.setApplyDateTime(applyDateTime);
			entity.setPostId(postId);
			// entity.setContactorUnit(contactorUnit);
			// entity.setMainUnit1(mainUnit1);
			// entity.setMainUnit2(mainUnit2);
			// entity.setContactorId(contactorId);
			entity.setContactorTel(contactorTel);
			entity.setContactorFax(contactorFax);
			entity.setContactorEmail(contactorEmail);
			entity.setIsSub(isSub);
			entity.setIsSubUnitName(isSubUnitName);
			entity.setEventDateTime(eventDateTime);
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsFinishDoSysOptRemark(isFinishDoSysOptRemark);
			entity.setIsFinishDoEduOptRemark(isFinishDoEduOptRemark);
			entity.setFinishDoSysOptRemark(finishDoSysOptRemark);
			entity.setFinishDoEduOptRemark(finishDoEduOptRemark);
			entity.setIsHandledByMyself(isHandledByMyself);		
			entity.setHandleInformationId(handleInformationId);					
			entity.setIpExternal(ipExternal);
			entity.setIpInternal(ipInternal);
			entity.setWebUrl(webUrl);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setIsGuardOpt1(isGuardOpt1);
			entity.setIsGuardOpt2(isGuardOpt2);
			entity.setIsGuardOpt3(isGuardOpt3);
			entity.setIsGuardOpt4(isGuardOpt4);
			entity.setIsGuardOpt4Other(isGuardOpt4Other);
			entity.setSocOpt(socOpt);
			entity.setSocOptCompany(socOptCompany);
			entity.setIsIsms(isIsms);
			entity.setMaintainCompany(maintainCompany);
			entity.setIsTest(isTest);
			// step2
			entity.setCeffectLevel(ceffectLevel);
			entity.setIeffectLevel(ieffectLevel);
			entity.setAeffectLevel(aeffectLevel);
			entity.setEffectLevel(effectLevel);
			// step3
			entity.setEventType(eventType);
			entity.setIsEventType1Opt1(isEventType1Opt1);
			entity.setIsEventType1Opt2(isEventType1Opt2);
			entity.setIsEventType1Opt3(isEventType1Opt3);
			entity.setIsEventType1Opt4(isEventType1Opt4);
			entity.setIsEventType1Opt5(isEventType1Opt5);
			entity.setIsEventType1Opt6(isEventType1Opt6);
			entity.setIsEventType2Opt1(isEventType2Opt1);
			entity.setIsEventType2Opt2(isEventType2Opt2);
			entity.setIsEventType2Opt3(isEventType2Opt3);
			entity.setIsEventType2Opt4(isEventType2Opt4);
			entity.setIsEventType2Opt5(isEventType2Opt5);
			entity.setIsEventType3Opt1(isEventType3Opt1);
			entity.setIsEventType3Opt2(isEventType3Opt2);
			entity.setIsEventType4Opt1(isEventType4Opt1);
			entity.setIsEventType4Opt2(isEventType4Opt2);
			entity.setIsEventType4Opt3(isEventType4Opt3);
			entity.setIsEventType4Opt4(isEventType4Opt4);
			entity.setEventType5Other(eventType5Other);
			entity.setEventRemark(eventRemark);
			entity.setIsAffectOthers(isAffectOthers);
			entity.setAffectOther1(affectOther1);
			entity.setAffectOther2(affectOther2);
			entity.setAffectOther3(affectOther3);
			entity.setAffectOther4(affectOther4);
			entity.setAffectOther5(affectOther5);
			entity.setAffectOther6(affectOther6);
			entity.setAffectOther7(affectOther7);
			entity.setAffectOther8(affectOther8);
			entity.setEventSource(eventSource);
			entity.setEventSourceNo(eventSourceNo);
			entity.setEventSourceOther(eventSourceOther);
			// step4-all
			entity.setResDescription(resDescription);
			entity.setResControlTime(resControlTime);
			entity.setResResult(resResult);
			// step4-1
			entity.setIsRes1LogOpt1(isRes1LogOpt1);
			entity.setIsRes1LogOpt2(isRes1LogOpt2);
			entity.setIsRes1LogOpt5(isRes1LogOpt5);
			entity.setIsRes1LogOpt3(isRes1LogOpt3);
			entity.setIsRes1LogOpt4(isRes1LogOpt4);
			entity.setRes1LogOpt1(res1LogOpt1);
			entity.setRes1LogOpt1Other(res1LogOpt1Other);
			entity.setRes1LogOpt2(res1LogOpt2);
			entity.setRes1LogOpt2Other(res1LogOpt2Other);
			entity.setRes1LogOpt5(res1LogOpt5);
			entity.setRes1LogOpt5Other(res1LogOpt5Other);
			entity.setRes1LogOpt3Amount(res1LogOpt3Amount);
			entity.setRes1LogOpt4Remark(res1LogOpt4Remark);
			entity.setIsRes1EvaOpt1(isRes1EvaOpt1);
			entity.setIsRes1EvaOpt2(isRes1EvaOpt2);
			entity.setIsRes1EvaOpt3(isRes1EvaOpt3);
			entity.setIsRes1EvaOpt4(isRes1EvaOpt4);
			entity.setIsRes1EvaOpt5(isRes1EvaOpt5);
			entity.setIsRes1EvaOpt6(isRes1EvaOpt6);
			entity.setIsRes1EvaOpt7(isRes1EvaOpt7);
			entity.setIsRes1EvaOpt8(isRes1EvaOpt8);
			entity.setRes1EvaOpt1Remark(res1EvaOpt1Remark);
			entity.setRes1EvaOpt2Remark(res1EvaOpt2Remark);
			entity.setRes1EvaOpt3Remark(res1EvaOpt3Remark);
			entity.setRes1EvaOpt4Remark(res1EvaOpt4Remark);
			entity.setRes1EvaOpt6Amount(res1EvaOpt6Amount);
			entity.setRes1EvaOpt6Remark(res1EvaOpt6Remark);
			entity.setRes1EvaOpt6Type(res1EvaOpt6Type);
			entity.setRes1EvaOpt6TypeOpt3Other(res1EvaOpt6TypeOpt3Other);
			entity.setRes1EvaOpt7Amount(res1EvaOpt7Amount);
			entity.setRes1EvaOpt7Remark(res1EvaOpt7Remark);
			entity.setRes1EvaOpt8Remark(res1EvaOpt8Remark);
			entity.setIsRes1DoOpt1(isRes1DoOpt1);
			entity.setIsRes1DoOpt2(isRes1DoOpt2);
			entity.setIsRes1DoOpt3(isRes1DoOpt3);
			entity.setIsRes1DoOpt4(isRes1DoOpt4);
			entity.setIsRes1DoOpt5(isRes1DoOpt5);
			entity.setIsRes1DoOpt6(isRes1DoOpt6);
			entity.setIsRes1DoOpt7(isRes1DoOpt7);
			entity.setIsRes1DoOpt8(isRes1DoOpt8);
			entity.setIsRes1DoOpt9(isRes1DoOpt9);
			entity.setIsRes1DoOpt10(isRes1DoOpt10);
			entity.setIsRes1DoOpt11(isRes1DoOpt11);
			entity.setIsRes1DoOpt12(isRes1DoOpt12);
			entity.setRes1DoOpt1Amount(res1DoOpt1Amount);
			entity.setRes1DoOpt2Remark(res1DoOpt2Remark);
			entity.setRes1DoOpt3Remark(res1DoOpt3Remark);
			entity.setRes1DoOpt4Remark(res1DoOpt4Remark);
			entity.setRes1DoOpt5Amount(res1DoOpt5Amount);
			entity.setIsRes1DoOpt9EngineOpt1(isRes1DoOpt9EngineOpt1);
			entity.setIsRes1DoOpt9EngineOpt2(isRes1DoOpt9EngineOpt2);
			entity.setIsRes1DoOpt9EngineOpt3(isRes1DoOpt9EngineOpt3);
			entity.setIsRes1DoOpt9EngineOpt4(isRes1DoOpt9EngineOpt4);
			entity.setIsRes1DoOpt9EngineOpt5(isRes1DoOpt9EngineOpt5);
			entity.setIsRes1DoOpt9EngineOpt6(isRes1DoOpt9EngineOpt6);
			entity.setRes1DoOpt9EngineOpt6Other(res1DoOpt9EngineOpt6Other);
			entity.setRes1DoOpt10Date(res1DoOpt10Date);
			entity.setRes1DoOpt11Date(res1DoOpt11Date);
			entity.setRes1DoOpt12Remark(res1DoOpt12Remark);
			// step4-2
			entity.setIsRes2LogOpt1(isRes2LogOpt1);
			entity.setIsRes2LogOpt2(isRes2LogOpt2);
			entity.setIsRes2LogOpt3(isRes2LogOpt3);
			entity.setIsRes2LogOpt4(isRes2LogOpt4);
			entity.setRes2LogOpt1(res2LogOpt1);
			entity.setRes2LogOpt1Other(res2LogOpt1Other);
			entity.setRes2LogOpt2(res2LogOpt2);
			entity.setRes2LogOpt2Other(res2LogOpt2Other);
			entity.setRes2LogOpt3Amount(res2LogOpt3Amount);
			entity.setRes2LogOpt4Remark(res2LogOpt4Remark);
			entity.setIsRes2EvaOpt1(isRes2EvaOpt1);
			entity.setIsRes2EvaOpt2(isRes2EvaOpt2);
			entity.setIsRes2EvaOpt3(isRes2EvaOpt3);
			entity.setIsRes2EvaOpt4(isRes2EvaOpt4);
			entity.setIsRes2EvaOpt5(isRes2EvaOpt5);
			entity.setRes2EvaOpt1Remark(res2EvaOpt1Remark);
			entity.setRes2EvaOpt2Remark(res2EvaOpt2Remark);
			entity.setRes2EvaOpt3Remark(res2EvaOpt3Remark);
			entity.setRes2EvaOpt4Remark(res2EvaOpt4Remark);
			entity.setRes2EvaOpt5Remark(res2EvaOpt5Remark);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setIsRes2DoOpt2(isRes2DoOpt2);
			entity.setIsRes2DoOpt3(isRes2DoOpt3);
			entity.setIsRes2DoOpt4(isRes2DoOpt4);
			entity.setIsRes2DoOpt5(isRes2DoOpt5);
			entity.setIsRes2DoOpt6(isRes2DoOpt6);
			entity.setIsRes2DoOpt7(isRes2DoOpt7);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setRes2DoOpt1Amount(res2DoOpt1Amount);
			entity.setRes2DoOpt1Remark(res2DoOpt1Remark);
			entity.setRes2DoOpt2Remark(res2DoOpt2Remark);
			entity.setRes2DoOpt3Remark(res2DoOpt3Remark);
			entity.setRes2DoOpt5Date(res2DoOpt5Date);
			entity.setRes2DoOpt6Amount(res2DoOpt6Amount);
			entity.setRes2DoOpt7Remark(res2DoOpt7Remark);
			// step4-3
			entity.setIsRes3LogOpt1(isRes3LogOpt1);
			entity.setIsRes3LogOpt2(isRes3LogOpt2);
			entity.setIsRes3LogOpt3(isRes3LogOpt3);
			entity.setIsRes3LogOpt4(isRes3LogOpt4);
			entity.setRes3LogOpt1(res3LogOpt1);
			entity.setRes3LogOpt1Other(res3LogOpt1Other);
			entity.setRes3LogOpt2(res3LogOpt2);
			entity.setRes3LogOpt2Other(res3LogOpt2Other);
			entity.setRes3LogOpt3Amount(res3LogOpt3Amount);
			entity.setRes3LogOpt4Remark(res3LogOpt4Remark);
			entity.setIsRes3EvaOpt1(isRes3EvaOpt1);
			entity.setIsRes3EvaOpt2(isRes3EvaOpt2);
			entity.setIsRes3EvaOpt3(isRes3EvaOpt3);
			entity.setRes3EvaOpt1Amount(res3EvaOpt1Amount);
			entity.setRes3EvaOpt2Remark(res3EvaOpt2Remark);
			entity.setRes3EvaOpt3Remark(res3EvaOpt3Remark);
			entity.setIsRes3DoOpt1(isRes3DoOpt1);
			entity.setIsRes3DoOpt2(isRes3DoOpt2);
			entity.setIsRes3DoOpt3(isRes3DoOpt3);
			entity.setIsRes3DoOpt4(isRes3DoOpt4);
			entity.setRes3DoOpt1Remark(res3DoOpt1Remark);
			entity.setRes3DoOpt3Remark(res3DoOpt3Remark);
			entity.setRes3DoOpt4Remark(res3DoOpt4Remark);
			// step4-4
			entity.setIsRes4LogOpt1(isRes4LogOpt1);
			entity.setRes4LogOpt1Remark(res4LogOpt1Remark);
			entity.setIsRes4EvaOpt1(isRes4EvaOpt1);
			entity.setIsRes4EvaOpt2(isRes4EvaOpt2);
			entity.setIsRes4EvaOpt3(isRes4EvaOpt3);
			entity.setRes4EvaOpt1(res4EvaOpt1);
			entity.setRes4EvaOpt1Amount(res4EvaOpt1Amount);
			entity.setRes4EvaOpt2Remark(res4EvaOpt2Remark);
			entity.setRes4EvaOpt3Remark(res4EvaOpt3Remark);
			entity.setIsRes4DoOpt1(isRes4DoOpt1);
			entity.setIsRes4DoOpt2(isRes4DoOpt2);
			entity.setIsRes4DoOpt4(isRes4DoOpt4);
			entity.setIsRes4DoOpt3(isRes4DoOpt3);
			entity.setRes4DoOpt3Remark(res4DoOpt3Remark);
			// step4-5
			entity.setIsRes5LogOpt1(isRes5LogOpt1);
			entity.setIsRes5LogOpt2(isRes5LogOpt2);
			entity.setIsRes5LogOpt3(isRes5LogOpt3);
			entity.setIsRes5LogOpt4(isRes5LogOpt4);
			entity.setRes5LogOpt1(res5LogOpt1);
			entity.setRes5LogOpt1Other(res5LogOpt1Other);
			entity.setRes5LogOpt2(res5LogOpt2);
			entity.setRes5LogOpt2Other(res5LogOpt2Other);
			entity.setRes5LogOpt3Amount(res5LogOpt3Amount);
			entity.setRes5LogOpt4Remark(res5LogOpt4Remark);
			entity.setIsRes5EvaOpt1(isRes5EvaOpt1);
			entity.setIsRes5EvaOpt2(isRes5EvaOpt2);
			entity.setIsRes5EvaOpt3(isRes5EvaOpt3);
			entity.setIsRes5EvaOpt4(isRes5EvaOpt4);
			entity.setIsRes5EvaOpt5(isRes5EvaOpt5);
			entity.setRes5EvaOpt1Remark(res5EvaOpt1Remark);
			entity.setRes5EvaOpt2Remark(res5EvaOpt2Remark);
			entity.setRes5EvaOpt3Remark(res5EvaOpt3Remark);
			entity.setRes5EvaOpt4Remark(res5EvaOpt4Remark);
			entity.setRes5EvaOpt5Remark(res5EvaOpt5Remark);
			entity.setIsRes5DoOpt1(isRes5DoOpt1);
			entity.setIsRes5DoOpt2(isRes5DoOpt2);
			entity.setIsRes5DoOpt3(isRes5DoOpt3);
			entity.setIsRes5DoOpt4(isRes5DoOpt4);
			entity.setIsRes5DoOpt5(isRes5DoOpt5);
			entity.setIsRes5DoOpt6(isRes5DoOpt6);
			entity.setIsRes5DoOpt7(isRes5DoOpt7);
			entity.setRes5DoOpt1Amount(res5DoOpt1Amount);
			entity.setRes5DoOpt1Remark(res5DoOpt1Remark);
			entity.setRes5DoOpt2Remark(res5DoOpt2Remark);
			entity.setRes5DoOpt3Remark(res5DoOpt3Remark);
			entity.setRes5DoOpt5Date(res5DoOpt5Date);
			entity.setRes5DoOpt6Amount(res5DoOpt6Amount);
			entity.setRes5DoOpt7Remark(res5DoOpt7Remark);
			// step5
			entity.setIsNeedSupport(isNeedSupport);
			entity.setIsHandledByMyself(isNeedSupport);
			entity.setNeedSupportRemark(needSupportRemark);			
			// step6-1
			entity.setFinish1Reason(finish1Reason);
			entity.setFinish1ReasonOther(finish1ReasonOther);
			entity.setFinish1ReasonToDo(finish1ReasonToDo);
			entity.setIsFinish1DoSysOpt1(isFinish1DoSysOpt1);
			entity.setIsFinish1DoSysOpt2(isFinish1DoSysOpt2);
			entity.setIsFinish1DoSysOpt3(isFinish1DoSysOpt3);
			entity.setIsFinish1DoSysOpt4(isFinish1DoSysOpt4);
			entity.setIsFinish1DoSysOpt5(isFinish1DoSysOpt5);
			entity.setIsFinish1DoSysOpt6(isFinish1DoSysOpt6);
			entity.setIsFinish1DoSysOpt7(isFinish1DoSysOpt7);
			entity.setIsFinish1DoSysOpt8(isFinish1DoSysOpt8);
			entity.setIsFinish1DoSysOpt9(isFinish1DoSysOpt9);
			entity.setIsFinish1DoSysOpt10(isFinish1DoSysOpt10);
			entity.setFinish1DoSysOpt3Remark(finish1DoSysOpt3Remark);
			entity.setFinish1DoSysOpt6Remark(finish1DoSysOpt6Remark);
			entity.setFinish1DoSysOpt7Remark(finish1DoSysOpt7Remark);
			entity.setIsFinish1DoEduOpt1(isFinish1DoEduOpt1);
			entity.setIsFinish1DoEduOpt2(isFinish1DoEduOpt2);
			entity.setIsFinish1DoEduOpt3(isFinish1DoEduOpt3);
			entity.setIsFinish1DoEduOpt4(isFinish1DoEduOpt4);
			// step6-2
			entity.setFinish2Reason(finish2Reason);
			entity.setFinish2ReasonOther(finish2ReasonOther);
			entity.setFinish2ReasonRemark(finish2ReasonRemark);
			entity.setIsFinish2DoSysOpt1(isFinish2DoSysOpt1);
			entity.setIsFinish2DoSysOpt2(isFinish2DoSysOpt2);
			entity.setIsFinish2DoSysOpt3(isFinish2DoSysOpt3);
			entity.setIsFinish2DoSysOpt4(isFinish2DoSysOpt4);
			entity.setIsFinish2DoSysOpt5(isFinish2DoSysOpt5);
			entity.setFinish2DoSysOpt1Remark(finish2DoSysOpt1Remark);
			entity.setIsFinish2DoEduOpt1(isFinish2DoEduOpt1);
			entity.setIsFinish2DoEduOpt2(isFinish2DoEduOpt2);
			entity.setIsFinish2DoEduOpt3(isFinish2DoEduOpt3);
			entity.setIsFinish2DoEduOpt4(isFinish2DoEduOpt4);
			// step6-3
			entity.setIsFinish3DoSysOpt1(isFinish3DoSysOpt1);
			entity.setIsFinish3DoSysOpt2(isFinish3DoSysOpt2);
			entity.setIsFinish3DoSysOpt3(isFinish3DoSysOpt3);
			entity.setIsFinish3DoSysOpt4(isFinish3DoSysOpt4);
			entity.setFinish3DoSysOpt3Remark(finish3DoSysOpt3Remark);
			entity.setFinish3DoSysOpt4Remark(finish3DoSysOpt4Remark);
			entity.setIsFinish3DoEduOpt1(isFinish3DoEduOpt1);
			entity.setIsFinish3DoEduOpt2(isFinish3DoEduOpt2);
			// step6-4
			entity.setFinish4Reason(finish4Reason);
			entity.setFinish4ReasonOther(finish4ReasonOther);
			entity.setFinish4ReasonRemark(finish4ReasonRemark);
			entity.setIsFinish4DoSysOpt1(isFinish4DoSysOpt1);
			entity.setIsFinish4DoEduOpt1(isFinish4DoEduOpt1);
			entity.setIsFinish4DoEduOpt2(isFinish4DoEduOpt2);
			entity.setIsFinish4DoEduOpt3(isFinish4DoEduOpt3);
			entity.setIsFinish4DoEduOpt4(isFinish4DoEduOpt4);
			// step6-5
			entity.setFinish5Reason(finish5Reason);
			entity.setFinish5ReasonOther(finish5ReasonOther);
			entity.setFinish5ReasonRemark(finish5ReasonRemark);
			entity.setIsFinish5DoSysOpt1(isFinish5DoSysOpt1);
			entity.setIsFinish5DoSysOpt2(isFinish5DoSysOpt2);
			entity.setIsFinish5DoSysOpt3(isFinish5DoSysOpt3);
			entity.setIsFinish5DoSysOpt4(isFinish5DoSysOpt4);
			entity.setFinish5DoSysOpt1Remark(finish5DoSysOpt1Remark);
			entity.setIsFinish5DoEduOpt1(isFinish5DoEduOpt1);
			entity.setIsFinish5DoEduOpt2(isFinish5DoEduOpt2);
			entity.setIsFinish5DoEduOpt3(isFinish5DoEduOpt3);
			entity.setIsFinish5DoEduOpt4(isFinish5DoEduOpt4);
			// step6-...
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			// other...
			entity.setStatus(status);
			entity.setExamineDateTime(examineDateTime);
			entity.setRealFinishDateTime(realFinishDateTime);
			entity.setMessageId(messageId);
			entity.setMessagePostId(messagePostId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			entity.setIsCC3(isCC3);
			entity.setIsCC5(isCC5);
			entity.setIsReview3(isReview3);
			entity.setIsReview5(isReview5);
			notificationDAO.update(entity);

			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新通報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            通報資料
	 * @return 通報資料
	 */
	public Notification updateStep3(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");

			// step2
			int ceffectLevel = obj.isNull("CeffectLevel") == true ? 0 : obj.getInt("CeffectLevel");
			int ieffectLevel = obj.isNull("IeffectLevel") == true ? 0 : obj.getInt("IeffectLevel");
			int aeffectLevel = obj.isNull("AeffectLevel") == true ? 0 : obj.getInt("AeffectLevel");
			int effectLevel = obj.isNull("EffectLevel") == true ? 0 : obj.getInt("EffectLevel");
						
			Notification entity = notificationDAO.get(id);
			
			entity.setCeffectLevel(ceffectLevel);
			entity.setIeffectLevel(ieffectLevel);
			entity.setAeffectLevel(aeffectLevel);
			entity.setEffectLevel(effectLevel);

			notificationDAO.update(entity);

			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 更新通報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            通報資料
	 * @return 通報資料
	 */
	public Notification updateStep6(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");

			// 6-common
			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isFinishDoSysOptRemark = obj.isNull("IsFinishDoSysOptRemark") == true ? false : obj.getBoolean("IsFinishDoSysOptRemark");
			Boolean isFinishDoEduOptRemark = obj.isNull("IsFinishDoEduOptRemark") == true ? false : obj.getBoolean("IsFinishDoEduOptRemark");
			String finishDoSysOptRemark = obj.isNull("FinishDoSysOptRemark") == true ? null : obj.getString("FinishDoSysOptRemark");
			String finishDoEduOptRemark = obj.isNull("FinishDoEduOptRemark") == true ? null : obj.getString("FinishDoEduOptRemark");
			Boolean isHandledByMyself = obj.isNull("IsHandledByMyself") == true ? false : obj.getBoolean("IsHandledByMyself");
			Long handleInformationId = obj.isNull("HandleInformationId") == true ? 0 : obj.getLong("HandleInformationId");
			
			Boolean isTest = obj.isNull("IsTest") == true ? false : obj.getBoolean("IsTest");
			
			String ipExternal = obj.isNull("IpExternal") == true ? null : obj.getString("IpExternal");
			String ipInternal = obj.isNull("IpInternal") == true ? null : obj.getString("IpInternal");
			String webUrl = obj.isNull("WebUrl") == true ? null : obj.getString("WebUrl");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");
			Boolean isGuardOpt1 = obj.isNull("IsGuardOpt1") == true ? false : obj.getBoolean("IsGuardOpt1");
			Boolean isGuardOpt2 = obj.isNull("IsGuardOpt2") == true ? false : obj.getBoolean("IsGuardOpt2");
			Boolean isGuardOpt3 = obj.isNull("IsGuardOpt3") == true ? false : obj.getBoolean("IsGuardOpt3");
			Boolean isGuardOpt4 = obj.isNull("IsGuardOpt4") == true ? false : obj.getBoolean("IsGuardOpt4");
			String isGuardOpt4Other = obj.isNull("IsGuardOpt4Other") == true ? null : obj.getString("IsGuardOpt4Other");
			Boolean isIsms = obj.isNull("IsIsms") == true ? false : obj.getBoolean("IsIsms");

			// step4-all
			String resDescription = obj.optString("ResDescription", null);
			Date resControlTime = new Date();
			String strResControlTime = obj.optString("ResControlTime", null);
			if (strResControlTime != null && !strResControlTime.equals(""))
				resControlTime = WebDatetime.parse(strResControlTime);
			int resResult = obj.optInt("ResResult", 0);
			// step4-1
			Boolean isRes1LogOpt1 = obj.isNull("IsRes1LogOpt1") == true ? false : obj.getBoolean("IsRes1LogOpt1");
			Boolean isRes1LogOpt2 = obj.isNull("IsRes1LogOpt2") == true ? false : obj.getBoolean("IsRes1LogOpt2");
			Boolean isRes1LogOpt5 = obj.optBoolean("IsRes1LogOpt5", false);
			Boolean isRes1LogOpt3 = obj.isNull("IsRes1LogOpt3") == true ? false : obj.getBoolean("IsRes1LogOpt3");
			Boolean isRes1LogOpt4 = obj.isNull("IsRes1LogOpt4") == true ? false : obj.getBoolean("IsRes1LogOpt4");
			int res1LogOpt1 = obj.isNull("Res1LogOpt1") == true ? 0 : obj.getInt("Res1LogOpt1");
			String res1LogOpt1Other = obj.isNull("Res1LogOpt1Other") == true ? null : obj.getString("Res1LogOpt1Other");
			int res1LogOpt2 = obj.isNull("Res1LogOpt2") == true ? 0 : obj.getInt("Res1LogOpt2");
			String res1LogOpt2Other = obj.isNull("Res1LogOpt2Other") == true ? null : obj.getString("Res1LogOpt2Other");
			int res1LogOpt5 = obj.optInt("Res1LogOpt5", 0);
			String res1LogOpt5Other = obj.optString("Res1LogOpt5Other", null);
			int res1LogOpt3Amount = obj.isNull("Res1LogOpt3Amount") == true ? 0 : obj.getInt("Res1LogOpt3Amount");
			String res1LogOpt4Remark = obj.isNull("Res1LogOpt4Remark") == true ? null : obj.getString("Res1LogOpt4Remark");
			Boolean isRes1EvaOpt1 = obj.isNull("IsRes1EvaOpt1") == true ? false : obj.getBoolean("IsRes1EvaOpt1");
			Boolean isRes1EvaOpt2 = obj.isNull("IsRes1EvaOpt2") == true ? false : obj.getBoolean("IsRes1EvaOpt2");
			Boolean isRes1EvaOpt3 = obj.isNull("IsRes1EvaOpt3") == true ? false : obj.getBoolean("IsRes1EvaOpt3");
			Boolean isRes1EvaOpt4 = obj.isNull("IsRes1EvaOpt4") == true ? false : obj.getBoolean("IsRes1EvaOpt4");
			Boolean isRes1EvaOpt5 = obj.isNull("IsRes1EvaOpt5") == true ? false : obj.getBoolean("IsRes1EvaOpt5");
			Boolean isRes1EvaOpt6 = obj.isNull("IsRes1EvaOpt6") == true ? false : obj.getBoolean("IsRes1EvaOpt6");
			Boolean isRes1EvaOpt7 = obj.isNull("IsRes1EvaOpt7") == true ? false : obj.getBoolean("IsRes1EvaOpt7");
			Boolean isRes1EvaOpt8 = obj.isNull("IsRes1EvaOpt8") == true ? false : obj.getBoolean("IsRes1EvaOpt8");
			String res1EvaOpt1Remark = obj.isNull("Res1EvaOpt1Remark") == true ? null : obj.getString("Res1EvaOpt1Remark");
			String res1EvaOpt2Remark = obj.isNull("Res1EvaOpt2Remark") == true ? null : obj.getString("Res1EvaOpt2Remark");
			String res1EvaOpt3Remark = obj.isNull("Res1EvaOpt3Remark") == true ? null : obj.getString("Res1EvaOpt3Remark");
			String res1EvaOpt4Remark = obj.isNull("Res1EvaOpt4Remark") == true ? null : obj.getString("Res1EvaOpt4Remark");
			int res1EvaOpt6Amount = obj.isNull("Res1EvaOpt6Amount") == true ? 0 : obj.getInt("Res1EvaOpt6Amount");
			String res1EvaOpt6Remark = obj.optString("Res1EvaOpt6Remark", null);
			int res1EvaOpt6Type = obj.isNull("Res1EvaOpt6Type") == true ? 0 : obj.getInt("Res1EvaOpt6Type");
			String res1EvaOpt6TypeOpt3Other = obj.isNull("Res1EvaOpt6TypeOpt3Other") == true ? null : obj.getString("Res1EvaOpt6TypeOpt3Other");
			int res1EvaOpt7Amount = obj.isNull("Res1EvaOpt7Amount") == true ? 0 : obj.getInt("Res1EvaOpt7Amount");
			String res1EvaOpt7Remark = obj.isNull("Res1EvaOpt7Remark") == true ? null : obj.getString("Res1EvaOpt7Remark");
			String res1EvaOpt8Remark = obj.isNull("Res1EvaOpt8Remark") == true ? null : obj.getString("Res1EvaOpt8Remark");

			Boolean isRes1DoOpt1 = obj.isNull("IsRes1DoOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt1");
			Boolean isRes1DoOpt2 = obj.isNull("IsRes1DoOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt2");
			Boolean isRes1DoOpt3 = obj.isNull("IsRes1DoOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt3");
			Boolean isRes1DoOpt4 = obj.isNull("IsRes1DoOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt4");
			Boolean isRes1DoOpt5 = obj.isNull("IsRes1DoOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt5");
			Boolean isRes1DoOpt6 = obj.isNull("IsRes1DoOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt6");
			Boolean isRes1DoOpt7 = obj.isNull("IsRes1DoOpt7") == true ? false : obj.getBoolean("IsRes1DoOpt7");
			Boolean isRes1DoOpt8 = obj.isNull("IsRes1DoOpt8") == true ? false : obj.getBoolean("IsRes1DoOpt8");
			Boolean isRes1DoOpt9 = obj.isNull("IsRes1DoOpt9") == true ? false : obj.getBoolean("IsRes1DoOpt9");
			Boolean isRes1DoOpt10 = obj.isNull("IsRes1DoOpt10") == true ? false : obj.getBoolean("IsRes1DoOpt10");
			Boolean isRes1DoOpt11 = obj.isNull("IsRes1DoOpt11") == true ? false : obj.getBoolean("IsRes1DoOpt11");
			Boolean isRes1DoOpt12 = obj.isNull("IsRes1DoOpt12") == true ? false : obj.getBoolean("IsRes1DoOpt12");
			int res1DoOpt1Amount = obj.isNull("Res1DoOpt1Amount") == true ? 0 : obj.getInt("Res1DoOpt1Amount");
			String res1DoOpt2Remark = obj.isNull("Res1DoOpt2Remark") == true ? null : obj.getString("Res1DoOpt2Remark");
			String res1DoOpt3Remark = obj.isNull("Res1DoOpt3Remark") == true ? null : obj.getString("Res1DoOpt3Remark");
			String res1DoOpt4Remark = obj.isNull("Res1DoOpt4Remark") == true ? null : obj.getString("Res1DoOpt4Remark");
			int res1DoOpt5Amount = obj.isNull("Res1DoOpt5Amount") == true ? 0 : obj.getInt("Res1DoOpt5Amount");
			Boolean isRes1DoOpt9EngineOpt1 = obj.isNull("IsRes1DoOpt9EngineOpt1") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt1");
			Boolean isRes1DoOpt9EngineOpt2 = obj.isNull("IsRes1DoOpt9EngineOpt2") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt2");
			Boolean isRes1DoOpt9EngineOpt3 = obj.isNull("IsRes1DoOpt9EngineOpt3") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt3");
			Boolean isRes1DoOpt9EngineOpt4 = obj.isNull("IsRes1DoOpt9EngineOpt4") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt4");
			Boolean isRes1DoOpt9EngineOpt5 = obj.isNull("IsRes1DoOpt9EngineOpt5") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt5");
			Boolean isRes1DoOpt9EngineOpt6 = obj.isNull("IsRes1DoOpt9EngineOpt6") == true ? false : obj.getBoolean("IsRes1DoOpt9EngineOpt6");
			String res1DoOpt9EngineOpt6Other = obj.isNull("Res1DoOpt9EngineOpt6Other") == true ? null : obj.getString("Res1DoOpt9EngineOpt6Other");

			Date res1DoOpt10Date = new Date();
			String strRes1DoOpt10Date = obj.isNull("Res1DoOpt10Date") == true ? null : obj.getString("Res1DoOpt10Date");
			if (strRes1DoOpt10Date != null && !strRes1DoOpt10Date.equals(""))
				res1DoOpt10Date = WebDatetime.parse(strRes1DoOpt10Date + " 00:00:00");

			Date res1DoOpt11Date = new Date();
			String strRes1DoOpt11Date = obj.isNull("Res1DoOpt11Date") == true ? null : obj.getString("Res1DoOpt11Date");
			if (strRes1DoOpt11Date != null && !strRes1DoOpt11Date.equals(""))
				res1DoOpt11Date = WebDatetime.parse(strRes1DoOpt11Date + " 00:00:00");

			String res1DoOpt12Remark = obj.isNull("Res1DoOpt12Remark") == true ? null : obj.getString("Res1DoOpt12Remark");

			// step4-2
			Boolean isRes2LogOpt1 = obj.isNull("IsRes2LogOpt1") == true ? false : obj.getBoolean("IsRes2LogOpt1");
			Boolean isRes2LogOpt2 = obj.isNull("IsRes2LogOpt2") == true ? false : obj.getBoolean("IsRes2LogOpt2");
			Boolean isRes2LogOpt3 = obj.isNull("IsRes2LogOpt3") == true ? false : obj.getBoolean("IsRes2LogOpt3");
			Boolean isRes2LogOpt4 = obj.isNull("IsRes2LogOpt4") == true ? false : obj.getBoolean("IsRes2LogOpt4");
			int res2LogOpt1 = obj.isNull("Res2LogOpt1") == true ? 0 : obj.getInt("Res2LogOpt1");
			String res2LogOpt1Other = obj.isNull("Res2LogOpt1Other") == true ? null : obj.getString("Res2LogOpt1Other");
			int res2LogOpt2 = obj.isNull("Res2LogOpt2") == true ? 0 : obj.getInt("Res2LogOpt2");
			String res2LogOpt2Other = obj.isNull("Res2LogOpt2Other") == true ? null : obj.getString("Res2LogOpt2Other");
			int res2LogOpt3Amount = obj.isNull("Res2LogOpt3Amount") == true ? 0 : obj.getInt("Res2LogOpt3Amount");
			String res2LogOpt4Remark = obj.isNull("Res2LogOpt4Remark") == true ? null : obj.getString("Res2LogOpt4Remark");
			Boolean isRes2EvaOpt1 = obj.isNull("IsRes2EvaOpt1") == true ? false : obj.getBoolean("IsRes2EvaOpt1");
			Boolean isRes2EvaOpt2 = obj.isNull("IsRes2EvaOpt2") == true ? false : obj.getBoolean("IsRes2EvaOpt2");
			Boolean isRes2EvaOpt3 = obj.isNull("IsRes2EvaOpt3") == true ? false : obj.getBoolean("IsRes2EvaOpt3");
			Boolean isRes2EvaOpt4 = obj.isNull("IsRes2EvaOpt4") == true ? false : obj.getBoolean("IsRes2EvaOpt4");
			Boolean isRes2EvaOpt5 = obj.isNull("IsRes2EvaOpt5") == true ? false : obj.getBoolean("IsRes2EvaOpt5");
			String res2EvaOpt1Remark = obj.isNull("Res2EvaOpt1Remark") == true ? null : obj.getString("Res2EvaOpt1Remark");
			String res2EvaOpt2Remark = obj.isNull("Res2EvaOpt2Remark") == true ? null : obj.getString("Res2EvaOpt2Remark");
			String res2EvaOpt3Remark = obj.isNull("Res2EvaOpt3Remark") == true ? null : obj.getString("Res2EvaOpt3Remark");
			String res2EvaOpt4Remark = obj.isNull("Res2EvaOpt4Remark") == true ? null : obj.getString("Res2EvaOpt4Remark");
			String res2EvaOpt5Remark = obj.isNull("Res2EvaOpt5Remark") == true ? null : obj.getString("Res2EvaOpt5Remark");
			Boolean isRes2DoOpt1 = obj.isNull("IsRes2DoOpt1") == true ? false : obj.getBoolean("IsRes2DoOpt1");
			Boolean isRes2DoOpt2 = obj.isNull("IsRes2DoOpt2") == true ? false : obj.getBoolean("IsRes2DoOpt2");
			Boolean isRes2DoOpt3 = obj.isNull("IsRes2DoOpt3") == true ? false : obj.getBoolean("IsRes2DoOpt3");
			Boolean isRes2DoOpt4 = obj.isNull("IsRes2DoOpt4") == true ? false : obj.getBoolean("IsRes2DoOpt4");
			Boolean isRes2DoOpt5 = obj.isNull("IsRes2DoOpt5") == true ? false : obj.getBoolean("IsRes2DoOpt5");
			Boolean isRes2DoOpt6 = obj.isNull("IsRes2DoOpt6") == true ? false : obj.getBoolean("IsRes2DoOpt6");
			Boolean isRes2DoOpt7 = obj.isNull("IsRes2DoOpt7") == true ? false : obj.getBoolean("IsRes2DoOpt7");
			Integer res2DoOpt1Amount = obj.optInt("Res2DoOpt1Amount", 0);
			String res2DoOpt1Remark = obj.isNull("Res2DoOpt1Remark") == true ? null : obj.getString("Res2DoOpt1Remark");
			String res2DoOpt2Remark = obj.isNull("Res2DoOpt2Remark") == true ? null : obj.getString("Res2DoOpt2Remark");
			String res2DoOpt3Remark = obj.isNull("Res2DoOpt3Remark") == true ? null : obj.getString("Res2DoOpt3Remark");

			Date res2DoOpt5Date = new Date();
			String strRes2DoOpt5Date = obj.optString("Res2DoOpt5Date", null);
			if (strRes2DoOpt5Date != null && !strRes2DoOpt5Date.equals(""))
				res2DoOpt5Date = WebDatetime.parse(strRes2DoOpt5Date + " 00:00:00");

			int res2DoOpt6Amount = obj.isNull("Res2DoOpt6Amount") == true ? 0 : obj.getInt("Res2DoOpt6Amount");
			String res2DoOpt7Remark = obj.isNull("Res2DoOpt7Remark") == true ? null : obj.getString("Res2DoOpt7Remark");
			// step4-3
			Boolean isRes3LogOpt1 = obj.isNull("IsRes3LogOpt1") == true ? false : obj.getBoolean("IsRes3LogOpt1");
			Boolean isRes3LogOpt2 = obj.isNull("IsRes3LogOpt2") == true ? false : obj.getBoolean("IsRes3LogOpt2");
			Boolean isRes3LogOpt3 = obj.isNull("IsRes3LogOpt3") == true ? false : obj.getBoolean("IsRes3LogOpt3");
			Boolean isRes3LogOpt4 = obj.isNull("IsRes3LogOpt4") == true ? false : obj.getBoolean("IsRes3LogOpt4");
			int res3LogOpt1 = obj.isNull("Res3LogOpt1") == true ? 0 : obj.getInt("Res3LogOpt1");
			String res3LogOpt1Other = obj.isNull("Res3LogOpt1Other") == true ? null : obj.getString("Res3LogOpt1Other");
			int res3LogOpt2 = obj.isNull("Res3LogOpt2") == true ? 0 : obj.getInt("Res3LogOpt2");
			String res3LogOpt2Other = obj.isNull("Res3LogOpt2Other") == true ? null : obj.getString("Res3LogOpt2Other");
			int res3LogOpt3Amount = obj.isNull("Res3LogOpt3Amount") == true ? 0 : obj.getInt("Res3LogOpt3Amount");
			String res3LogOpt4Remark = obj.isNull("Res3LogOpt4Remark") == true ? null : obj.getString("Res3LogOpt4Remark");
			Boolean isRes3EvaOpt1 = obj.isNull("IsRes3EvaOpt1") == true ? false : obj.getBoolean("IsRes3EvaOpt1");
			Boolean isRes3EvaOpt2 = obj.isNull("IsRes3EvaOpt2") == true ? false : obj.getBoolean("IsRes3EvaOpt2");
			Boolean isRes3EvaOpt3 = obj.isNull("IsRes3EvaOpt3") == true ? false : obj.getBoolean("IsRes3EvaOpt3");
			int res3EvaOpt1Amount = obj.isNull("Res3EvaOpt1Amount") == true ? 0 : obj.getInt("Res3EvaOpt1Amount");
			String res3EvaOpt2Remark = obj.isNull("Res3EvaOpt2Remark") == true ? null : obj.getString("Res3EvaOpt2Remark");
			String res3EvaOpt3Remark = obj.isNull("Res3EvaOpt3Remark") == true ? null : obj.getString("Res3EvaOpt3Remark");
			Boolean isRes3DoOpt1 = obj.isNull("IsRes3DoOpt1") == true ? false : obj.getBoolean("IsRes3DoOpt1");
			Boolean isRes3DoOpt2 = obj.isNull("IsRes3DoOpt2") == true ? false : obj.getBoolean("IsRes3DoOpt2");
			Boolean isRes3DoOpt3 = obj.isNull("IsRes3DoOpt3") == true ? false : obj.getBoolean("IsRes3DoOpt3");
			Boolean isRes3DoOpt4 = obj.isNull("IsRes3DoOpt4") == true ? false : obj.getBoolean("IsRes3DoOpt4");
			String res3DoOpt1Remark = obj.isNull("Res3DoOpt1Remark") == true ? null : obj.getString("Res3DoOpt1Remark");
			String res3DoOpt3Remark = obj.isNull("Res3DoOpt3Remark") == true ? null : obj.getString("Res3DoOpt3Remark");
			String res3DoOpt4Remark = obj.isNull("Res3DoOpt4Remark") == true ? null : obj.getString("Res3DoOpt4Remark");
			// step4-4
			Boolean isRes4LogOpt1 = obj.isNull("IsRes4LogOpt1") == true ? false : obj.getBoolean("IsRes4LogOpt1");
			String res4LogOpt1Remark = obj.isNull("Res4LogOpt1Remark") == true ? null : obj.getString("Res4LogOpt1Remark");
			Boolean isRes4EvaOpt1 = obj.isNull("IsRes4EvaOpt1") == true ? false : obj.getBoolean("IsRes4EvaOpt1");
			Boolean isRes4EvaOpt2 = obj.isNull("IsRes4EvaOpt2") == true ? false : obj.getBoolean("IsRes4EvaOpt2");
			Boolean isRes4EvaOpt3 = obj.optBoolean("IsRes4EvaOpt3", false);
			int res4EvaOpt1 = obj.isNull("Res4EvaOpt1") == true ? 0 : obj.getInt("Res4EvaOpt1");
			int res4EvaOpt1Amount = obj.isNull("Res4EvaOpt1Amount") == true ? 0 : obj.getInt("Res4EvaOpt1Amount");
			String res4EvaOpt2Remark = obj.isNull("Res4EvaOpt2Remark") == true ? null : obj.getString("Res4EvaOpt2Remark");
			String res4EvaOpt3Remark = obj.optString("Res4EvaOpt3Remark", null);
			Boolean isRes4DoOpt1 = obj.isNull("IsRes4DoOpt1") == true ? false : obj.getBoolean("IsRes4DoOpt1");
			Boolean isRes4DoOpt2 = obj.isNull("IsRes4DoOpt2") == true ? false : obj.getBoolean("IsRes4DoOpt2");
			Boolean isRes4DoOpt4 = obj.optBoolean("IsRes4DoOpt2", false);
			Boolean isRes4DoOpt3 = obj.isNull("IsRes4DoOpt3") == true ? false : obj.getBoolean("IsRes4DoOpt3");
			String res4DoOpt3Remark = obj.isNull("Res4DoOpt3Remark") == true ? null : obj.getString("Res4DoOpt3Remark");
			// step4-5
			Boolean isRes5LogOpt1 = obj.isNull("IsRes5LogOpt1") == true ? false : obj.getBoolean("IsRes5LogOpt1");
			Boolean isRes5LogOpt2 = obj.isNull("IsRes5LogOpt2") == true ? false : obj.getBoolean("IsRes5LogOpt2");
			Boolean isRes5LogOpt3 = obj.isNull("IsRes5LogOpt3") == true ? false : obj.getBoolean("IsRes5LogOpt3");
			Boolean isRes5LogOpt4 = obj.isNull("IsRes5LogOpt4") == true ? false : obj.getBoolean("IsRes5LogOpt4");
			int res5LogOpt1 = obj.isNull("Res5LogOpt1") == true ? 0 : obj.getInt("Res5LogOpt1");
			String res5LogOpt1Other = obj.isNull("Res5LogOpt1Other") == true ? null : obj.getString("Res5LogOpt1Other");
			int res5LogOpt2 = obj.isNull("Res5LogOpt2") == true ? 0 : obj.getInt("Res5LogOpt2");
			String res5LogOpt2Other = obj.isNull("Res5LogOpt2Other") == true ? null : obj.getString("Res5LogOpt2Other");
			int res5LogOpt3Amount = obj.isNull("Res5LogOpt3Amount") == true ? 0 : obj.getInt("Res5LogOpt3Amount");
			String res5LogOpt4Remark = obj.isNull("Res5LogOpt4Remark") == true ? null : obj.getString("Res5LogOpt4Remark");
			Boolean isRes5EvaOpt1 = obj.isNull("IsRes5EvaOpt1") == true ? false : obj.getBoolean("IsRes5EvaOpt1");
			Boolean isRes5EvaOpt2 = obj.isNull("IsRes5EvaOpt2") == true ? false : obj.getBoolean("IsRes5EvaOpt2");
			Boolean isRes5EvaOpt3 = obj.isNull("IsRes5EvaOpt3") == true ? false : obj.getBoolean("IsRes5EvaOpt3");
			Boolean isRes5EvaOpt4 = obj.isNull("IsRes5EvaOpt4") == true ? false : obj.getBoolean("IsRes5EvaOpt4");
			Boolean isRes5EvaOpt5 = obj.isNull("IsRes5EvaOpt5") == true ? false : obj.getBoolean("IsRes5EvaOpt5");
			String res5EvaOpt1Remark = obj.isNull("Res5EvaOpt1Remark") == true ? null : obj.getString("Res5EvaOpt1Remark");
			String res5EvaOpt2Remark = obj.isNull("Res5EvaOpt2Remark") == true ? null : obj.getString("Res5EvaOpt2Remark");
			String res5EvaOpt3Remark = obj.isNull("Res5EvaOpt3Remark") == true ? null : obj.getString("Res5EvaOpt3Remark");
			String res5EvaOpt4Remark = obj.isNull("Res5EvaOpt4Remark") == true ? null : obj.getString("Res5EvaOpt4Remark");
			String res5EvaOpt5Remark = obj.isNull("Res5EvaOpt5Remark") == true ? null : obj.getString("Res5EvaOpt5Remark");
			Boolean isRes5DoOpt1 = obj.isNull("IsRes5DoOpt1") == true ? false : obj.getBoolean("IsRes5DoOpt1");
			Boolean isRes5DoOpt2 = obj.isNull("IsRes5DoOpt2") == true ? false : obj.getBoolean("IsRes5DoOpt2");
			Boolean isRes5DoOpt3 = obj.isNull("IsRes5DoOpt3") == true ? false : obj.getBoolean("IsRes5DoOpt3");
			Boolean isRes5DoOpt4 = obj.isNull("IsRes5DoOpt4") == true ? false : obj.getBoolean("IsRes5DoOpt4");
			Boolean isRes5DoOpt5 = obj.isNull("IsRes5DoOpt5") == true ? false : obj.getBoolean("IsRes5DoOpt5");
			Boolean isRes5DoOpt6 = obj.isNull("IsRes5DoOpt6") == true ? false : obj.getBoolean("IsRes5DoOpt6");
			Boolean isRes5DoOpt7 = obj.isNull("IsRes5DoOpt7") == true ? false : obj.getBoolean("IsRes5DoOpt7");
			Integer res5DoOpt1Amount = obj.optInt("Res5DoOpt1Amount", 0);
			String res5DoOpt1Remark = obj.isNull("Res5DoOpt1Remark") == true ? null : obj.getString("Res5DoOpt1Remark");
			String res5DoOpt2Remark = obj.isNull("Res5DoOpt2Remark") == true ? null : obj.getString("Res5DoOpt2Remark");
			String res5DoOpt3Remark = obj.isNull("Res5DoOpt3Remark") == true ? null : obj.getString("Res5DoOpt3Remark");

			Date res5DoOpt5Date = new Date();
			String strRes5DoOpt5Date = obj.isNull("Res5DoOpt5Date") == true ? null : obj.getString("Res5DoOpt5Date");
			if (strRes5DoOpt5Date != null && !strRes5DoOpt5Date.equals(""))
				res5DoOpt5Date = WebDatetime.parse(strRes5DoOpt5Date + " 00:00:00");

			int res5DoOpt6Amount = obj.isNull("Res5DoOpt6Amount") == true ? 0 : obj.getInt("Res5DoOpt6Amount");
			String res5DoOpt7Remark = obj.isNull("Res5DoOpt7Remark") == true ? null : obj.getString("Res5DoOpt7Remark");

			// step6-1
			int finish1Reason = obj.isNull("Finish1Reason") == true ? 0 : obj.getInt("Finish1Reason");
			String finish1ReasonOther = obj.isNull("Finish1ReasonOther") == true ? null : obj.getString("Finish1ReasonOther");
			String finish1ReasonToDo = obj.optString("Finish1ReasonOther", null);
			Boolean isFinish1DoSysOpt1 = obj.isNull("IsFinish1DoSysOpt1") == true ? false : obj.getBoolean("IsFinish1DoSysOpt1");
			Boolean isFinish1DoSysOpt2 = obj.isNull("IsFinish1DoSysOpt2") == true ? false : obj.getBoolean("IsFinish1DoSysOpt2");
			Boolean isFinish1DoSysOpt3 = obj.isNull("IsFinish1DoSysOpt3") == true ? false : obj.getBoolean("IsFinish1DoSysOpt3");
			Boolean isFinish1DoSysOpt4 = obj.isNull("IsFinish1DoSysOpt4") == true ? false : obj.getBoolean("IsFinish1DoSysOpt4");
			Boolean isFinish1DoSysOpt5 = obj.isNull("IsFinish1DoSysOpt5") == true ? false : obj.getBoolean("IsFinish1DoSysOpt5");
			Boolean isFinish1DoSysOpt6 = obj.isNull("IsFinish1DoSysOpt6") == true ? false : obj.getBoolean("IsFinish1DoSysOpt6");
			Boolean isFinish1DoSysOpt7 = obj.isNull("IsFinish1DoSysOpt7") == true ? false : obj.getBoolean("IsFinish1DoSysOpt7");
			Boolean isFinish1DoSysOpt8 = obj.isNull("IsFinish1DoSysOpt8") == true ? false : obj.getBoolean("IsFinish1DoSysOpt8");
			Boolean isFinish1DoSysOpt9 = obj.isNull("IsFinish1DoSysOpt9") == true ? false : obj.getBoolean("IsFinish1DoSysOpt9");
			Boolean isFinish1DoSysOpt10 = obj.isNull("IsFinish1DoSysOpt10") == true ? false : obj.getBoolean("IsFinish1DoSysOpt10");
			String finish1DoSysOpt3Remark = obj.isNull("Finish1DoSysOpt3Remark") == true ? null : obj.getString("Finish1DoSysOpt3Remark");
			String finish1DoSysOpt6Remark = obj.isNull("Finish1DoSysOpt6Remark") == true ? null : obj.getString("Finish1DoSysOpt6Remark");
			String finish1DoSysOpt7Remark = obj.isNull("Finish1DoSysOpt7Remark") == true ? null : obj.getString("Finish1DoSysOpt7Remark");
			Boolean isFinish1DoEduOpt1 = obj.isNull("IsFinish1DoEduOpt1") == true ? false : obj.getBoolean("IsFinish1DoEduOpt1");
			Boolean isFinish1DoEduOpt2 = obj.isNull("IsFinish1DoEduOpt2") == true ? false : obj.getBoolean("IsFinish1DoEduOpt2");
			Boolean isFinish1DoEduOpt3 = obj.isNull("IsFinish1DoEduOpt3") == true ? false : obj.getBoolean("IsFinish1DoEduOpt3");
			Boolean isFinish1DoEduOpt4 = obj.isNull("IsFinish1DoEduOpt4") == true ? false : obj.getBoolean("IsFinish1DoEduOpt4");
			// step6-2
			int finish2Reason = obj.isNull("Finish2Reason") == true ? 0 : obj.getInt("Finish2Reason");
			String finish2ReasonOther = obj.isNull("Finish2ReasonOther") == true ? null : obj.getString("Finish2ReasonOther");
			String finish2ReasonRemark = obj.isNull("Finish2ReasonRemark") == true ? null : obj.getString("Finish2ReasonRemark");
			Boolean isFinish2DoSysOpt1 = obj.isNull("IsFinish2DoSysOpt1") == true ? false : obj.getBoolean("IsFinish2DoSysOpt1");
			Boolean isFinish2DoSysOpt2 = obj.isNull("IsFinish2DoSysOpt2") == true ? false : obj.getBoolean("IsFinish2DoSysOpt2");
			Boolean isFinish2DoSysOpt3 = obj.isNull("IsFinish2DoSysOpt3") == true ? false : obj.getBoolean("IsFinish2DoSysOpt3");
			Boolean isFinish2DoSysOpt4 = obj.isNull("IsFinish2DoSysOpt4") == true ? false : obj.getBoolean("IsFinish2DoSysOpt4");
			Boolean isFinish2DoSysOpt5 = obj.isNull("IsFinish2DoSysOpt5") == true ? false : obj.getBoolean("IsFinish2DoSysOpt5");
			String finish2DoSysOpt1Remark = obj.isNull("Finish2DoSysOpt1Remark") == true ? null : obj.getString("Finish2DoSysOpt1Remark");
			Boolean isFinish2DoEduOpt1 = obj.isNull("IsFinish2DoEduOpt1") == true ? false : obj.getBoolean("IsFinish2DoEduOpt1");
			Boolean isFinish2DoEduOpt2 = obj.isNull("IsFinish2DoEduOpt2") == true ? false : obj.getBoolean("IsFinish2DoEduOpt2");
			Boolean isFinish2DoEduOpt3 = obj.isNull("IsFinish2DoEduOpt3") == true ? false : obj.getBoolean("IsFinish2DoEduOpt3");
			Boolean isFinish2DoEduOpt4 = obj.isNull("IsFinish2DoEduOpt4") == true ? false : obj.getBoolean("IsFinish2DoEduOpt4");
			// step6-3
			Boolean isFinish3DoSysOpt1 = obj.isNull("IsFinish3DoSysOpt1") == true ? false : obj.getBoolean("IsFinish3DoSysOpt1");
			Boolean isFinish3DoSysOpt2 = obj.isNull("IsFinish3DoSysOpt2") == true ? false : obj.getBoolean("IsFinish3DoSysOpt2");
			Boolean isFinish3DoSysOpt3 = obj.isNull("IsFinish3DoSysOpt3") == true ? false : obj.getBoolean("IsFinish3DoSysOpt3");
			Boolean isFinish3DoSysOpt4 = obj.isNull("IsFinish3DoSysOpt4") == true ? false : obj.getBoolean("IsFinish3DoSysOpt4");
			String finish3DoSysOpt3Remark = obj.isNull("Finish3DoSysOpt3Remark") == true ? null : obj.getString("Finish3DoSysOpt3Remark");
			String finish3DoSysOpt4Remark = obj.isNull("Finish3DoSysOpt4Remark") == true ? null : obj.getString("Finish3DoSysOpt4Remark");
			Boolean isFinish3DoEduOpt1 = obj.isNull("IsFinish3DoEduOpt1") == true ? false : obj.getBoolean("IsFinish3DoEduOpt1");
			Boolean isFinish3DoEduOpt2 = obj.isNull("IsFinish3DoEduOpt2") == true ? false : obj.getBoolean("IsFinish3DoEduOpt2");
			// step6-4
			int finish4Reason = obj.isNull("Finish4Reason") == true ? 0 : obj.getInt("Finish4Reason");
			String finish4ReasonOther = obj.isNull("Finish4ReasonOther") == true ? null : obj.getString("Finish4ReasonOther");
			String finish4ReasonRemark = obj.isNull("Finish4ReasonRemark") == true ? null : obj.getString("Finish4ReasonRemark");
			Boolean isFinish4DoSysOpt1 = obj.isNull("IsFinish4DoSysOpt1") == true ? false : obj.getBoolean("IsFinish4DoSysOpt1");
			Boolean isFinish4DoEduOpt1 = obj.isNull("IsFinish4DoEduOpt1") == true ? false : obj.getBoolean("IsFinish4DoEduOpt1");
			Boolean isFinish4DoEduOpt2 = obj.isNull("IsFinish4DoEduOpt2") == true ? false : obj.getBoolean("IsFinish4DoEduOpt2");
			Boolean isFinish4DoEduOpt3 = obj.isNull("IsFinish4DoEduOpt3") == true ? false : obj.getBoolean("IsFinish4DoEduOpt3");
			Boolean isFinish4DoEduOpt4 = obj.isNull("IsFinish4DoEduOpt4") == true ? false : obj.getBoolean("IsFinish4DoEduOpt4");
			// step6-5
			int finish5Reason = obj.isNull("Finish5Reason") == true ? 0 : obj.getInt("Finish5Reason");
			String finish5ReasonOther = obj.isNull("Finish5ReasonOther") == true ? null : obj.getString("Finish5ReasonOther");
			String finish5ReasonRemark = obj.isNull("Finish5ReasonRemark") == true ? null : obj.getString("Finish5ReasonRemark");
			Boolean isFinish5DoSysOpt1 = obj.isNull("IsFinish5DoSysOpt1") == true ? false : obj.getBoolean("IsFinish5DoSysOpt1");
			Boolean isFinish5DoSysOpt2 = obj.isNull("IsFinish5DoSysOpt2") == true ? false : obj.getBoolean("IsFinish5DoSysOpt2");
			Boolean isFinish5DoSysOpt3 = obj.isNull("IsFinish5DoSysOpt3") == true ? false : obj.getBoolean("IsFinish5DoSysOpt3");
			Boolean isFinish5DoSysOpt4 = obj.isNull("IsFinish5DoSysOpt4") == true ? false : obj.getBoolean("IsFinish5DoSysOpt4");
			String finish5DoSysOpt1Remark = obj.isNull("Finish5DoSysOpt1Remark") == true ? null : obj.getString("Finish5DoSysOpt1Remark");
			Boolean isFinish5DoEduOpt1 = obj.isNull("IsFinish5DoEduOpt1") == true ? false : obj.getBoolean("IsFinish5DoEduOpt1");
			Boolean isFinish5DoEduOpt2 = obj.isNull("IsFinish5DoEduOpt2") == true ? false : obj.getBoolean("IsFinish5DoEduOpt2");
			Boolean isFinish5DoEduOpt3 = obj.isNull("IsFinish5DoEduOpt3") == true ? false : obj.getBoolean("IsFinish5DoEduOpt3");
			Boolean isFinish5DoEduOpt4 = obj.isNull("IsFinish5DoEduOpt4") == true ? false : obj.getBoolean("IsFinish5DoEduOpt4");
			// step6-...
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");

			Date finishDateTime = new Date();
			String strFinishDateTime = obj.isNull("FinishDateTime") == true ? null : obj.getString("FinishDateTime");
			if (strFinishDateTime != null && !strFinishDateTime.equals(""))
				finishDateTime = WebDatetime.parse(strFinishDateTime);

			Date examineDateTime = new Date();
			String strExamineDateTime = obj.isNull("ExamineDateTime") == true ? null : obj.getString("ExamineDateTime");
			if (strExamineDateTime != null && !strExamineDateTime.equals(""))
				examineDateTime = WebDatetime.parse(strExamineDateTime);

			Date realFinishDateTime = new Date();
			String strRealFinishDateTime = obj.isNull("RealFinishDateTime") == true ? null : obj.getString("RealFinishDateTime");
			if (strRealFinishDateTime != null && !strRealFinishDateTime.equals(""))
				realFinishDateTime = WebDatetime.parse(strRealFinishDateTime);

			Boolean isCC5 = obj.isNull("IsCC5") == true ? false : obj.getBoolean("IsCC5");
			Boolean isReview5 = obj.isNull("IsReview5") == true ? false : obj.getBoolean("IsReview5");
			Notification entity = notificationDAO.get(id);

			// 6-common
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsFinishDoSysOptRemark(isFinishDoSysOptRemark);
			entity.setIsFinishDoEduOptRemark(isFinishDoEduOptRemark);
			entity.setFinishDoSysOptRemark(finishDoSysOptRemark);
			entity.setFinishDoEduOptRemark(finishDoEduOptRemark);
			entity.setIsHandledByMyself(isHandledByMyself);	
			entity.setHandleInformationId(handleInformationId);		
			entity.setIpExternal(ipExternal);
			entity.setIpInternal(ipInternal);
			entity.setWebUrl(webUrl);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setIsGuardOpt1(isGuardOpt1);
			entity.setIsGuardOpt2(isGuardOpt2);
			entity.setIsGuardOpt3(isGuardOpt3);
			entity.setIsGuardOpt4(isGuardOpt4);
			entity.setIsGuardOpt4Other(isGuardOpt4Other);
			entity.setIsIsms(isIsms);

			// step4-all
			entity.setResDescription(resDescription);
			entity.setResControlTime(resControlTime);
			entity.setResResult(resResult);
			// step4-1
			entity.setIsRes1LogOpt1(isRes1LogOpt1);
			entity.setIsRes1LogOpt2(isRes1LogOpt2);
			entity.setIsRes1LogOpt5(isRes1LogOpt5);
			entity.setIsRes1LogOpt3(isRes1LogOpt3);
			entity.setIsRes1LogOpt4(isRes1LogOpt4);
			entity.setRes1LogOpt1(res1LogOpt1);
			entity.setRes1LogOpt1Other(res1LogOpt1Other);
			entity.setRes1LogOpt2(res1LogOpt2);
			entity.setRes1LogOpt2Other(res1LogOpt2Other);
			entity.setRes1LogOpt5(res1LogOpt5);
			entity.setRes1LogOpt5Other(res1LogOpt5Other);
			entity.setRes1LogOpt3Amount(res1LogOpt3Amount);
			entity.setRes1LogOpt4Remark(res1LogOpt4Remark);
			entity.setIsRes1EvaOpt1(isRes1EvaOpt1);
			entity.setIsRes1EvaOpt2(isRes1EvaOpt2);
			entity.setIsRes1EvaOpt3(isRes1EvaOpt3);
			entity.setIsRes1EvaOpt4(isRes1EvaOpt4);
			entity.setIsRes1EvaOpt5(isRes1EvaOpt5);
			entity.setIsRes1EvaOpt6(isRes1EvaOpt6);
			entity.setIsRes1EvaOpt7(isRes1EvaOpt7);
			entity.setIsRes1EvaOpt8(isRes1EvaOpt8);
			entity.setRes1EvaOpt1Remark(res1EvaOpt1Remark);
			entity.setRes1EvaOpt2Remark(res1EvaOpt2Remark);
			entity.setRes1EvaOpt3Remark(res1EvaOpt3Remark);
			entity.setRes1EvaOpt4Remark(res1EvaOpt4Remark);
			entity.setRes1EvaOpt6Amount(res1EvaOpt6Amount);
			entity.setRes1EvaOpt6Remark(res1EvaOpt6Remark);
			entity.setRes1EvaOpt6Type(res1EvaOpt6Type);
			entity.setRes1EvaOpt6TypeOpt3Other(res1EvaOpt6TypeOpt3Other);
			entity.setRes1EvaOpt7Amount(res1EvaOpt7Amount);
			entity.setRes1EvaOpt7Remark(res1EvaOpt7Remark);
			entity.setRes1EvaOpt8Remark(res1EvaOpt8Remark);
			entity.setIsRes1DoOpt1(isRes1DoOpt1);
			entity.setIsRes1DoOpt2(isRes1DoOpt2);
			entity.setIsRes1DoOpt3(isRes1DoOpt3);
			entity.setIsRes1DoOpt4(isRes1DoOpt4);
			entity.setIsRes1DoOpt5(isRes1DoOpt5);
			entity.setIsRes1DoOpt6(isRes1DoOpt6);
			entity.setIsRes1DoOpt7(isRes1DoOpt7);
			entity.setIsRes1DoOpt8(isRes1DoOpt8);
			entity.setIsRes1DoOpt9(isRes1DoOpt9);
			entity.setIsRes1DoOpt10(isRes1DoOpt10);
			entity.setIsRes1DoOpt11(isRes1DoOpt11);
			entity.setIsRes1DoOpt12(isRes1DoOpt12);
			entity.setRes1DoOpt1Amount(res1DoOpt1Amount);
			entity.setRes1DoOpt2Remark(res1DoOpt2Remark);
			entity.setRes1DoOpt3Remark(res1DoOpt3Remark);
			entity.setRes1DoOpt4Remark(res1DoOpt4Remark);
			entity.setRes1DoOpt5Amount(res1DoOpt5Amount);
			entity.setIsRes1DoOpt9EngineOpt1(isRes1DoOpt9EngineOpt1);
			entity.setIsRes1DoOpt9EngineOpt2(isRes1DoOpt9EngineOpt2);
			entity.setIsRes1DoOpt9EngineOpt3(isRes1DoOpt9EngineOpt3);
			entity.setIsRes1DoOpt9EngineOpt4(isRes1DoOpt9EngineOpt4);
			entity.setIsRes1DoOpt9EngineOpt5(isRes1DoOpt9EngineOpt5);
			entity.setIsRes1DoOpt9EngineOpt6(isRes1DoOpt9EngineOpt6);
			entity.setRes1DoOpt9EngineOpt6Other(res1DoOpt9EngineOpt6Other);
			entity.setRes1DoOpt10Date(res1DoOpt10Date);
			entity.setRes1DoOpt11Date(res1DoOpt11Date);
			entity.setRes1DoOpt12Remark(res1DoOpt12Remark);
			// step4-2
			entity.setIsRes2LogOpt1(isRes2LogOpt1);
			entity.setIsRes2LogOpt2(isRes2LogOpt2);
			entity.setIsRes2LogOpt3(isRes2LogOpt3);
			entity.setIsRes2LogOpt4(isRes2LogOpt4);
			entity.setRes2LogOpt1(res2LogOpt1);
			entity.setRes2LogOpt1Other(res2LogOpt1Other);
			entity.setRes2LogOpt2(res2LogOpt2);
			entity.setRes2LogOpt2Other(res2LogOpt2Other);
			entity.setRes2LogOpt3Amount(res2LogOpt3Amount);
			entity.setRes2LogOpt4Remark(res2LogOpt4Remark);
			entity.setIsRes2EvaOpt1(isRes2EvaOpt1);
			entity.setIsRes2EvaOpt2(isRes2EvaOpt2);
			entity.setIsRes2EvaOpt3(isRes2EvaOpt3);
			entity.setIsRes2EvaOpt4(isRes2EvaOpt4);
			entity.setIsRes2EvaOpt5(isRes2EvaOpt5);
			entity.setRes2EvaOpt1Remark(res2EvaOpt1Remark);
			entity.setRes2EvaOpt2Remark(res2EvaOpt2Remark);
			entity.setRes2EvaOpt3Remark(res2EvaOpt3Remark);
			entity.setRes2EvaOpt4Remark(res2EvaOpt4Remark);
			entity.setRes2EvaOpt5Remark(res2EvaOpt5Remark);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setIsRes2DoOpt2(isRes2DoOpt2);
			entity.setIsRes2DoOpt3(isRes2DoOpt3);
			entity.setIsRes2DoOpt4(isRes2DoOpt4);
			entity.setIsRes2DoOpt5(isRes2DoOpt5);
			entity.setIsRes2DoOpt6(isRes2DoOpt6);
			entity.setIsRes2DoOpt7(isRes2DoOpt7);
			entity.setIsRes2DoOpt1(isRes2DoOpt1);
			entity.setRes2DoOpt1Amount(res2DoOpt1Amount);
			entity.setRes2DoOpt1Remark(res2DoOpt1Remark);
			entity.setRes2DoOpt2Remark(res2DoOpt2Remark);
			entity.setRes2DoOpt3Remark(res2DoOpt3Remark);
			entity.setRes2DoOpt5Date(res2DoOpt5Date);
			entity.setRes2DoOpt6Amount(res2DoOpt6Amount);
			entity.setRes2DoOpt7Remark(res2DoOpt7Remark);
			// step4-3
			entity.setIsRes3LogOpt1(isRes3LogOpt1);
			entity.setIsRes3LogOpt2(isRes3LogOpt2);
			entity.setIsRes3LogOpt3(isRes3LogOpt3);
			entity.setIsRes3LogOpt4(isRes3LogOpt4);
			entity.setRes3LogOpt1(res3LogOpt1);
			entity.setRes3LogOpt1Other(res3LogOpt1Other);
			entity.setRes3LogOpt2(res3LogOpt2);
			entity.setRes3LogOpt2Other(res3LogOpt2Other);
			entity.setRes3LogOpt3Amount(res3LogOpt3Amount);
			entity.setRes3LogOpt4Remark(res3LogOpt4Remark);
			entity.setIsRes3EvaOpt1(isRes3EvaOpt1);
			entity.setIsRes3EvaOpt2(isRes3EvaOpt2);
			entity.setIsRes3EvaOpt3(isRes3EvaOpt3);
			entity.setRes3EvaOpt1Amount(res3EvaOpt1Amount);
			entity.setRes3EvaOpt2Remark(res3EvaOpt2Remark);
			entity.setRes3EvaOpt3Remark(res3EvaOpt3Remark);
			entity.setIsRes3DoOpt1(isRes3DoOpt1);
			entity.setIsRes3DoOpt2(isRes3DoOpt2);
			entity.setIsRes3DoOpt3(isRes3DoOpt3);
			entity.setIsRes3DoOpt4(isRes3DoOpt4);
			entity.setRes3DoOpt1Remark(res3DoOpt1Remark);
			entity.setRes3DoOpt3Remark(res3DoOpt3Remark);
			entity.setRes3DoOpt4Remark(res3DoOpt4Remark);
			// step4-4
			entity.setIsRes4LogOpt1(isRes4LogOpt1);
			entity.setRes4LogOpt1Remark(res4LogOpt1Remark);
			entity.setIsRes4EvaOpt1(isRes4EvaOpt1);
			entity.setIsRes4EvaOpt2(isRes4EvaOpt2);
			entity.setIsRes4EvaOpt3(isRes4EvaOpt3);
			entity.setRes4EvaOpt1(res4EvaOpt1);
			entity.setRes4EvaOpt1Amount(res4EvaOpt1Amount);
			entity.setRes4EvaOpt2Remark(res4EvaOpt2Remark);
			entity.setRes4EvaOpt3Remark(res4EvaOpt3Remark);
			entity.setIsRes4DoOpt1(isRes4DoOpt1);
			entity.setIsRes4DoOpt2(isRes4DoOpt2);
			entity.setIsRes4DoOpt4(isRes4DoOpt4);
			entity.setIsRes4DoOpt3(isRes4DoOpt3);
			entity.setRes4DoOpt3Remark(res4DoOpt3Remark);
			// step4-5
			entity.setIsRes5LogOpt1(isRes5LogOpt1);
			entity.setIsRes5LogOpt2(isRes5LogOpt2);
			entity.setIsRes5LogOpt3(isRes5LogOpt3);
			entity.setIsRes5LogOpt4(isRes5LogOpt4);
			entity.setRes5LogOpt1(res5LogOpt1);
			entity.setRes5LogOpt1Other(res5LogOpt1Other);
			entity.setRes5LogOpt2(res5LogOpt2);
			entity.setRes5LogOpt2Other(res5LogOpt2Other);
			entity.setRes5LogOpt3Amount(res5LogOpt3Amount);
			entity.setRes5LogOpt4Remark(res5LogOpt4Remark);
			entity.setIsRes5EvaOpt1(isRes5EvaOpt1);
			entity.setIsRes5EvaOpt2(isRes5EvaOpt2);
			entity.setIsRes5EvaOpt3(isRes5EvaOpt3);
			entity.setIsRes5EvaOpt4(isRes5EvaOpt4);
			entity.setIsRes5EvaOpt5(isRes5EvaOpt5);
			entity.setRes5EvaOpt1Remark(res5EvaOpt1Remark);
			entity.setRes5EvaOpt2Remark(res5EvaOpt2Remark);
			entity.setRes5EvaOpt3Remark(res5EvaOpt3Remark);
			entity.setRes5EvaOpt4Remark(res5EvaOpt4Remark);
			entity.setRes5EvaOpt5Remark(res5EvaOpt5Remark);
			entity.setIsRes5DoOpt1(isRes5DoOpt1);
			entity.setIsRes5DoOpt2(isRes5DoOpt2);
			entity.setIsRes5DoOpt3(isRes5DoOpt3);
			entity.setIsRes5DoOpt4(isRes5DoOpt4);
			entity.setIsRes5DoOpt5(isRes5DoOpt5);
			entity.setIsRes5DoOpt6(isRes5DoOpt6);
			entity.setIsRes5DoOpt7(isRes5DoOpt7);
			entity.setRes5DoOpt1Amount(res5DoOpt1Amount);
			entity.setRes5DoOpt1Remark(res5DoOpt1Remark);
			entity.setRes5DoOpt2Remark(res5DoOpt2Remark);
			entity.setRes5DoOpt3Remark(res5DoOpt3Remark);
			entity.setRes5DoOpt5Date(res5DoOpt5Date);
			entity.setRes5DoOpt6Amount(res5DoOpt6Amount);
			entity.setRes5DoOpt7Remark(res5DoOpt7Remark);

			// step6-1
			entity.setFinish1Reason(finish1Reason);
			entity.setFinish1ReasonOther(finish1ReasonOther);
			entity.setFinish1ReasonToDo(finish1ReasonToDo);
			entity.setIsFinish1DoSysOpt1(isFinish1DoSysOpt1);
			entity.setIsFinish1DoSysOpt2(isFinish1DoSysOpt2);
			entity.setIsFinish1DoSysOpt3(isFinish1DoSysOpt3);
			entity.setIsFinish1DoSysOpt4(isFinish1DoSysOpt4);
			entity.setIsFinish1DoSysOpt5(isFinish1DoSysOpt5);
			entity.setIsFinish1DoSysOpt6(isFinish1DoSysOpt6);
			entity.setIsFinish1DoSysOpt7(isFinish1DoSysOpt7);
			entity.setIsFinish1DoSysOpt8(isFinish1DoSysOpt8);
			entity.setIsFinish1DoSysOpt9(isFinish1DoSysOpt9);
			entity.setIsFinish1DoSysOpt10(isFinish1DoSysOpt10);
			entity.setFinish1DoSysOpt3Remark(finish1DoSysOpt3Remark);
			entity.setFinish1DoSysOpt6Remark(finish1DoSysOpt6Remark);
			entity.setFinish1DoSysOpt7Remark(finish1DoSysOpt7Remark);
			entity.setIsFinish1DoEduOpt1(isFinish1DoEduOpt1);
			entity.setIsFinish1DoEduOpt2(isFinish1DoEduOpt2);
			entity.setIsFinish1DoEduOpt3(isFinish1DoEduOpt3);
			entity.setIsFinish1DoEduOpt4(isFinish1DoEduOpt4);
			// step6-2
			entity.setFinish2Reason(finish2Reason);
			entity.setFinish2ReasonOther(finish2ReasonOther);
			entity.setFinish2ReasonRemark(finish2ReasonRemark);
			entity.setIsFinish2DoSysOpt1(isFinish2DoSysOpt1);
			entity.setIsFinish2DoSysOpt2(isFinish2DoSysOpt2);
			entity.setIsFinish2DoSysOpt3(isFinish2DoSysOpt3);
			entity.setIsFinish2DoSysOpt4(isFinish2DoSysOpt4);
			entity.setIsFinish2DoSysOpt5(isFinish2DoSysOpt5);
			entity.setFinish2DoSysOpt1Remark(finish2DoSysOpt1Remark);
			entity.setIsFinish2DoEduOpt1(isFinish2DoEduOpt1);
			entity.setIsFinish2DoEduOpt2(isFinish2DoEduOpt2);
			entity.setIsFinish2DoEduOpt3(isFinish2DoEduOpt3);
			entity.setIsFinish2DoEduOpt4(isFinish2DoEduOpt4);
			// step6-3
			entity.setIsFinish3DoSysOpt1(isFinish3DoSysOpt1);
			entity.setIsFinish3DoSysOpt2(isFinish3DoSysOpt2);
			entity.setIsFinish3DoSysOpt3(isFinish3DoSysOpt3);
			entity.setIsFinish3DoSysOpt4(isFinish3DoSysOpt4);
			entity.setFinish3DoSysOpt3Remark(finish3DoSysOpt3Remark);
			entity.setFinish3DoSysOpt4Remark(finish3DoSysOpt4Remark);
			entity.setIsFinish3DoEduOpt1(isFinish3DoEduOpt1);
			entity.setIsFinish3DoEduOpt2(isFinish3DoEduOpt2);
			// step6-4
			entity.setFinish4Reason(finish4Reason);
			entity.setFinish4ReasonOther(finish4ReasonOther);
			entity.setFinish4ReasonRemark(finish4ReasonRemark);
			entity.setIsFinish4DoSysOpt1(isFinish4DoSysOpt1);
			entity.setIsFinish4DoEduOpt1(isFinish4DoEduOpt1);
			entity.setIsFinish4DoEduOpt2(isFinish4DoEduOpt2);
			entity.setIsFinish4DoEduOpt3(isFinish4DoEduOpt3);
			entity.setIsFinish4DoEduOpt4(isFinish4DoEduOpt4);
			// step6-5
			entity.setFinish5Reason(finish5Reason);
			entity.setFinish5ReasonOther(finish5ReasonOther);
			entity.setFinish5ReasonRemark(finish5ReasonRemark);
			entity.setIsFinish5DoSysOpt1(isFinish5DoSysOpt1);
			entity.setIsFinish5DoSysOpt2(isFinish5DoSysOpt2);
			entity.setIsFinish5DoSysOpt3(isFinish5DoSysOpt3);
			entity.setIsFinish5DoSysOpt4(isFinish5DoSysOpt4);
			entity.setFinish5DoSysOpt1Remark(finish5DoSysOpt1Remark);
			entity.setIsFinish5DoEduOpt1(isFinish5DoEduOpt1);
			entity.setIsFinish5DoEduOpt2(isFinish5DoEduOpt2);
			entity.setIsFinish5DoEduOpt3(isFinish5DoEduOpt3);
			entity.setIsFinish5DoEduOpt4(isFinish5DoEduOpt4);
			// step6-...
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			// other...
			entity.setExamineDateTime(examineDateTime);
			entity.setRealFinishDateTime(realFinishDateTime);
			entity.setIsCC5(isCC5);
			entity.setIsReview5(isReview5);
			notificationDAO.update(entity);

			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除通報資料
	 * 
	 * @param id
	 *            通報Id
	 * @return 是否刪除成功
	 */
	public boolean delete(String id) {
		try {
			Notification entity = notificationDAO.get(id);
			notificationDAO.delete(entity);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得通報資料
	 * 
	 * @param id
	 *            通報資料Id
	 * @return 通報資料
	 */
	public Notification getById(String id) {
		return notificationDAO.get(id);
	}
	/**
	 * 通報資料是否存在
	 * 
	 * @param id
	 *            通報資料Id
	 * @return 是否存在
	 */
	public boolean isExist(String id) {
		return notificationDAO.get(id) != null;
	}

	/**
	 * 審核警訊資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            警訊Id
	 * @param status
	 *            警訊狀態
	 * @param preStatus
	 *            上次狀態
	 * @return 警訊資料
	 */
	public Notification examine(long memberId, String id, int status, int preStatus, String opinion, int ceffectLevel, int ieffectLevel, int aeffectLevel, int effectLevel) {
//		try {
			// 寄信(透過status 和 prestatus)
			Date now = new Date();
			Notification entity = notificationDAO.get(id);
			Member member = memberService.get(entity.getCreateId());
			
			// 108/6/18 added by bowwow
			Boolean isNeedSupport = entity.getIsNeedSupport();
			Boolean isCC3 = entity.getIsCC3();
			

			// debug
//			System.out.println("==========================================================");
//			System.out.println("NotificationService.java → examine() → id：" + id);
//			System.out.println("NotificationService.java → examine() → status：" + status);
//			System.out.println("NotificationService.java → examine() → preStatus：" + preStatus);
//			System.out.println("NotificationService.java → examine() → opinion：" + opinion);
//			System.out.println("NotificationService.java → examine() → isNeedSupport：" + isNeedSupport);
//			System.out.println("NotificationService.java → examine() → isCC3：" + isCC3);
			
			
			if (status == 6 && member.getIsEnable()) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To6-1Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To6-1Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member sale : members) {
								Org org = orgService.getDataById(sale.getOrgId());
								for (ViewMemberRoleMember memberRole : memberRoles) {
									if (memberRole.getMemberId().equals(sale.getId()) && sale.getIsEnable() && org.getIsEnable()) {
										// 寄信業務
										String mailSubject1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To6-2Subject"), entity.getPostId());
										String mailBody1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To6-2Body"), sale.getName(), entity.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), sale.getEmail(), sale.getSpareEmail(), null, mailSubject1, mailBody1, null);
									}
								}
							}
						}
					}
				}
			}
			if (status == 8 && member.getIsEnable()) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To8Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To8Body"), member.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			if (status == 9 && member.getIsEnable()) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To9Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification5To9Body"), member.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			
			// 4.處理中
			if (status == 4 && member.getIsEnable()) {
				String postId = ticketQueueDAO.updatePostId("message", true, "HISAC", entity.getPostId(), orgService.getDataById(member.getOrgId()).getCode());
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				
				List<ViewMemberRoleMember> applyContacts = memberRoleService.findByRoleId(8);
				List<ViewMemberRoleMember> applySingAdmins = memberRoleService.findByRoleId(15);
				List <Member> mainUnit1Members = memberService.getByOrgId(entity.getMainUnit1());			
				List <Member> mainUnit2Members = memberService.getByOrgId(entity.getMainUnit2());	
				if (mainUnit1Members != null && applySingAdmins != null)
				for (Member mainUnit1Member : mainUnit1Members) {
					for (ViewMemberRoleMember applySingAdmin : applySingAdmins)
						if (mainUnit1Member.getId() == applySingAdmin.getMemberId())
						{
							String mailSubject1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4-2Subject"), postId);
							String mailBody1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4-2Body"), mainUnit1Member.getName(), entity.getPostId(), postId);
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), mainUnit1Member.getEmail(), mainUnit1Member.getSpareEmail(), null, mailSubject1, mailBody1, null);
						}
				}
				if (mainUnit2Members != null && applyContacts != null)
				for (Member mainUnit2Member : mainUnit2Members) {
					for (ViewMemberRoleMember applyContact : applyContacts)
						if (mainUnit2Member.getId() == applyContact.getMemberId())
						{
							String mailSubject1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4-2Subject"), postId);
							String mailBody1 = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To4-2Body"), mainUnit2Member.getName(), entity.getPostId(), postId);
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), mainUnit2Member.getEmail(), mainUnit2Member.getSpareEmail(), null, mailSubject1, mailBody1, null);
						}
				}			
				
				
				entity.setPostId(postId);
				
				// debug
//				System.out.println("NotificationService.java → examine() → 由 3.通報審核中，同意，並進入４．處理中時，在事件管理中自動新增一筆紀錄，並將關聯欄位複寫至該筆紀錄中(準備中)");
				
				// 由 3.通報審核中，同意，並進入４．處理中時，在事件管理中自動新增一筆紀錄，並將關聯欄位複寫至該筆紀錄中
				// 是否需要支援(isNeedSupport) = True 時，
				// 1. 通知【H-CERT審核者】
				// 2. 系統自動產生資安事件處理單，並帶入關聯欄位資料
				// 3.將IncidentId欄位值寫回Notification table對應欄位中
				if (isNeedSupport) {

					// debug
//					System.out.println("NotificationService.java → examine() → 由 3.通報審核中，同意，並進入４．處理中時，在事件管理中自動新增一筆紀錄，並將關聯欄位複寫至該筆紀錄中(寄信通知17.H-CERT審核者)");
					
					// 1. 通知【H-CERT審核者】
					// 寄信通知17.H-CERT審核者：IsHCERTContentSign
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(17);
					
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							
							Member subMember = memberService.get(memberRole.getMemberId());

							// debug
//							System.out.println("NotificationService.java → examine() → 【Notify → 1-編輯中】寄發通知信 to 17.HCERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
							
							// 30220 是 member.Account='hcert001' 的 member.Id
							if (subMember != null && subMember.getIsEnable()) {

								// debug
//								System.out.println("NotificationService.java → examine() → 【Notify → 1-編輯中】寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign - MemberId(符合)=" + memberRole.getMemberId());
//								System.out.println("NotificationService.java → examine() → 【Notify → 1-編輯中】寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());
//								System.out.println("NotificationService.java → examine() → 由 3.通報審核中，同意，並進入４．處理中時，在事件管理中自動新增一筆紀錄，並將關聯欄位複寫至該筆紀錄中(系統自動產生資安事件處理單，並帶入關聯欄位資料)");
								
								
								// 2. 系統自動產生資安事件處理單，並帶入關聯欄位資料
								// 產生 incident 用的 postId
								String incidentTableName = "incident";
								String incidentPre = "CERT";
								String incidentPostId = ticketQueueDAO.insertPostId(incidentTableName, false, incidentPre, "INC");
								
								String mailSubjectToIncident = MessageFormat.format(resourceMessageService.getMessageValue("mailIncidentNotifyTo1SubjectCCIsHCERTContentSign"), incidentPostId);
								String mailBodyToIncident = MessageFormat.format(resourceMessageService.getMessageValue("mailIncidentNotifyTo1BodyCCIsHCERTContentSign"), subMember.getName(), incidentPostId);
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, mailSubjectToIncident, mailBodyToIncident, null);
								
								Notification notificationEntity = notificationDAO.get(id);
								Date notificationNow = new Date();

								//notificationEntity.setModifyId(incidentEntity.getId());
								notificationEntity.setModifyId(memberRole.getMemberId());
								notificationEntity.setModifyTime(notificationNow);
								
								// debug
//								System.out.println("NotificationService.java → examine() → notificationEntity(更新前) = " + notificationEntity.toString());

								notificationDAO.update(notificationEntity);

								// debug
//								System.out.println("NotificationService.java → examine() → notificationEntity(更新後) = " + notificationEntity.toString());

								processLogService.insert(memberRole.getMemberId(), "", String.valueOf(notificationEntity.getId()));
								
								
								
								
								// 符合一次，處理完成即跳出迴圈
								break;
							}
						}
					}
				}
			}
			
			// 撤銷中
			if (status == 2 && member.getIsEnable()) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification3To2Body"), member.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			if (status == 32) {
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member sale : members) {
								Org org = orgService.getDataById(sale.getOrgId());
								for (ViewMemberRoleMember memberRole : memberRoles) {
									if (memberRole.getMemberId().equals(sale.getId()) && sale.getIsEnable() && org.getAuthType().equals("1") && org.getIsEnable()) {
										// 寄信業務
										String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), entity.getPostId());
										String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), sale.getName(), entity.getPostId());
										mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), sale.getEmail(), sale.getSpareEmail(), null, mailSubject, mailBody, null);
									}
								}
							}
						}
					}
				}
			}
			if (status == 33) {
				if (preStatus == 31) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
					List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
					if (orgSigns != null) {
						for (OrgSign orgSign : orgSigns) {
							List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
							if (members != null) {
								for (Member sale : members) {
									Org org = orgService.getDataById(sale.getOrgId());
									for (ViewMemberRoleMember memberRole : memberRoles) {
										if (memberRole.getMemberId().equals(sale.getId()) && sale.getIsEnable() && org.getAuthType().equals("1") && org.getIsEnable()) {
											// 寄信業務
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-1Body"), sale.getName(), memberService.get(entity.getCreateId()).getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), sale.getEmail(), sale.getSpareEmail(), null, mailSubject, mailBody, null);
										}
									}
								}
							}
						}
					}
				}
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(12);
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member hisac = memberService.get(memberRole.getMemberId());
						if (hisac != null && hisac.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Subject"), entity.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification1To3-2Body"), hisac.getName(), entity.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), hisac.getEmail(), hisac.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}
			}
			if (status == 51) {
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member auth : members) {
								if (auth.getIsEnable()) {
									Org org = orgService.getDataById(auth.getOrgId());
									for (ViewMemberRoleMember memberRole : memberRoles) {
										if (memberRole.getMemberId().equals(auth.getId()) && auth.getIsEnable() && org.getAuthType().equals("2") && org.getIsEnable()) {
											// 寄信權責
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Body"), auth.getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), auth.getEmail(), auth.getSpareEmail(), null, mailSubject, mailBody, null);
										}
									}
								}
							}
						}
					}
				}
			}
			if (status == 52) {
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member auth : members) {
								for (ViewMemberRoleMember memberRole : memberRoles) {
									if (auth.getIsEnable()) {
										Org org = orgService.getDataById(auth.getOrgId());
										if (memberRole.getMemberId().equals(auth.getId()) && auth.getIsEnable() && org.getAuthType().equals("1") && org.getIsEnable()) {
											// 寄信業務
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Body"), auth.getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), auth.getEmail(), auth.getSpareEmail(), null, mailSubject, mailBody, null);
										}
										if (memberRole.getMemberId().equals(auth.getId()) && (preStatus == 4 || preStatus == 9) && auth.getIsEnable() && org.getAuthType().equals("2") && org.getIsEnable()) {
											// 寄信權責
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Body"), auth.getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), auth.getEmail(), auth.getSpareEmail(), null, mailSubject, mailBody, null);
										}
									}
								}
							}
						}
					}
				}
			}
			if (status == 53) {
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				if (orgSigns != null && preStatus != 52) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(15);
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member auth : members) {
								if (auth.getIsEnable()) {
									Org org = orgService.getDataById(auth.getOrgId());
									for (ViewMemberRoleMember memberRole : memberRoles) {
										if (memberRole.getMemberId().equals(auth.getId()) && preStatus != 52 && auth.getIsEnable() && org.getAuthType().equals("1") && org.getIsEnable()) {
											// 寄信業務
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Body"), auth.getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), auth.getEmail(), auth.getSpareEmail(), null, mailSubject, mailBody, null);
										}
										if (memberRole.getMemberId().equals(auth.getId()) && (preStatus == 4 || preStatus == 9) && auth.getIsEnable() && org.getAuthType().equals("2") && org.getIsEnable()) {
											// 寄信權責
											String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Subject"), entity.getPostId());
											String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-1Body"), auth.getName(), entity.getPostId());
											mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), auth.getEmail(), auth.getSpareEmail(), null, mailSubject, mailBody, null);
										}
									}
								}
							}
						}
					}
				}
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(12);
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member hisac = memberService.get(memberRole.getMemberId());
						if (hisac != null && hisac.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Subject"), entity.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Body"), hisac.getName(), entity.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), hisac.getEmail(), hisac.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}
			}

			if (preStatus != status) {
				//sms TO H-ISAC通報審核者
				List<ViewMemberRoleMember> viewMemberRoleMembers = memberRoleService.findByRoleId(12);
				String contactorUnitName = orgService.getDataById(entity.getContactorUnit()).getName();				
				if (viewMemberRoleMembers != null) {					
					for (ViewMemberRoleMember viewMemberRoleMember : viewMemberRoleMembers) {
						Member memberSms = memberService.get(viewMemberRoleMember.getMemberId());
						if (true && memberSms.getMobilePhone() != null && !memberSms.getMobilePhone().trim().equals("")) {
							String smsMessage = MessageFormat.format(resourceMessageService.getMessageValue("mailNotificationSms"), contactorUnitName, entity.getPostId(), 
									status==2?"[撤銷中]":status==31?"上級機關[通報審核中]":status==32?"業務管理機關[通報審核中]":status==33?"HCERT[通報審核中]"
											:status==4?"會員機構[處理中]"
											:status==51?"上級機關[處理審核中]":status==52?"業務管理機關[處理審核中]":status==53?"HCERT[處理審核中]"
											:status==6?"[已結案]":status==7?"[已銷案]":status==8?"會員機構[編輯中(退回補述)]":"會員機構[處理中(退回補述)]");
							smsService.Send("[測試]", memberSms.getMobilePhone(), smsMessage);
						}
					}
				}
				
			}
			entity.setCeffectLevel(ceffectLevel);
			entity.setIeffectLevel(ieffectLevel);
			entity.setAeffectLevel(aeffectLevel);
			entity.setEffectLevel(effectLevel);
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			notificationDAO.update(entity);

			return entity;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	/**
	 * 更新Notification
	 * 
	 * @param memberId
	 *            人員編號
	 * @param id
	 *            Id
	 * @param transferOutType
	 *            轉出類型
	 * @param transferOutId
	 *            轉出Id
	 * @return Notification
	 */
	public Notification updateTransferOut(long memberId, String id, Integer transferOutType, String transferOutId) {
		try {
			Notification entity = notificationDAO.get(id);
			entity.setTransferOutType(transferOutType);
			entity.setTransferOutId(transferOutId);
			notificationDAO.update(entity);
			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

	

	/**
	 * 更新通報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            通報資料
	 * @return 通報資料
	 */
	public Notification updateCertId(String notificationId, Long certId) {
		try {
			Notification entity = notificationDAO.get(notificationId);
			
			entity.setCertId(certId);

			notificationDAO.update(entity);

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * chooseHandleInformation通報資料
	 * 
	 * @param id
	 *            通報Id
	 * @param HandleInformationId
	 *           HandleInformationId
	 * @return 通報資料
	 */
	public Notification chooseHandleInformation(String notificationId, Long handleInformationId, boolean isHandledByMyself) {
		try {
			Notification entity = notificationDAO.get(notificationId);
						
			entity.setHandleInformationId(handleInformationId);
			entity.setIsHandledByMyself(isHandledByMyself);

			notificationDAO.update(entity);

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
