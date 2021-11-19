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
import tw.gov.mohw.hisac.web.dao.AnaManagementDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.AnaManagement;
import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.NewsManagement;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.domain.MalwareManagement;
import tw.gov.mohw.hisac.web.domain.MalwareManagementAttach;
import tw.gov.mohw.hisac.web.domain.MalwareManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewMalwareManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMalwareManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewMalwareManagementPicMember;
import tw.gov.mohw.hisac.web.service.AnaManagementService;
import tw.gov.mohw.hisac.web.service.EventTypeService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;
import tw.gov.mohw.hisac.web.service.AnaManagementAttachService;
import tw.gov.mohw.hisac.web.service.MalwareManagementService;
import tw.gov.mohw.hisac.web.service.MalwareManagementAttachService;
import tw.gov.mohw.hisac.web.service.MalwareManagementPicService;




/**
 * 資安訊息情報管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s39_AnaManagementController extends BaseController {

	@Autowired
	private AnaManagementService anaManagementService;
	@Autowired
	private AnaManagementAttachService anaManagementAttachService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	AnaManagementDAO anaManagementDAO;

	@Autowired
	private ProcessLogService processLogService;
	
	@Autowired
	private MalwareManagementService malwareManagementService;
	@Autowired
	private MalwareManagementAttachService malwareManagementAttachService;
	@Autowired
	private MalwareManagementPicService malwareManagementPicService;

	@Autowired
	private MailService mailService;

	private String targetControllerName = "sys";
	private String targetActionName = "s39";

	/**
	 * 取得AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return AnaManagement資料
	 */
	@RequestMapping(value = "/s39/query", method = RequestMethod.POST)
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
			// listjson.put("total", anaManagementService.getListSize(json));
			// List<ViewAnaManagementMember> anaManagements =
			// anaManagementService.getList(json);

			// 改用 store procedure
			List<ViewAnaManagementMember> anaManagements = anaManagementService.getSpList(json);
			listjson.put("total", anaManagementService.getSpListSize(json));

			if (anaManagements != null) {
				for (ViewAnaManagementMember anaManagement : anaManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", anaManagement.getId());
					sn_json.put("PostId", anaManagement.getPostId());
					sn_json.put("IncidentId", anaManagement.getIncidentId());
					sn_json.put("IncidentTitle", anaManagement.getIncidentTitle());
					sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
					sn_json.put("IncidentReportedTime", WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Description", anaManagement.getDescription());
					sn_json.put("EventTypeCode", anaManagement.getEventTypeCode());
					sn_json.put("EventTypeName", anaManagement.getEventTypeName());
					sn_json.put("ReporterName", anaManagement.getReporterName());
					sn_json.put("ResponderPartyName", anaManagement.getResponderPartyName());
					sn_json.put("ResponderContactNumbers", anaManagement.getResponderContactNumbers());
					sn_json.put("ResponderElectronicAddressIdentifiers", anaManagement.getResponderElectronicAddressIdentifiers());
					sn_json.put("ImpactQualification", anaManagement.getImpactQualification());
					sn_json.put("CoaDescription", anaManagement.getCoaDescription());
					sn_json.put("Confidence", anaManagement.getConfidence());
					sn_json.put("Reference", anaManagement.getReference());
					sn_json.put("AffectedSoftwareDescription", anaManagement.getAffectedSoftwareDescription());
					sn_json.put("StartDateTime", WebDatetime.toString(anaManagement.getStartDateTime(), "yyyy-MM-dd"));
					sn_json.put("EndDateTime", WebDatetime.toString(anaManagement.getEndDateTime(), "yyyy-MM-dd"));
					sn_json.put("IsEnable", anaManagement.getIsEnable());
					sn_json.put("CreateId", anaManagement.getCreateId());
					sn_json.put("CreateName", anaManagement.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(anaManagement.getCreateTime()));
					sn_json.put("ModifyId", anaManagement.getModifyId());
					sn_json.put("ModifyName", anaManagement.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(anaManagement.getModifyTime()));
					sn_json.put("Status", anaManagement.getStatus());
					sn_json.put("IsMedical", anaManagement.getIsMedical());
					sn_json.put("Sort", anaManagement.getSort());
					sn_json.put("IsMalware", anaManagement.getIsMalware());


					// 簽核流程用 - 開始
					String currentStatus = String.valueOf(anaManagement.getStatus());

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
							sn_json.put("IsButtonMalware", false); // 是否顯示轉勒索專區案件

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
							sn_json.put("IsButtonMalware", false); // 是否顯示轉勒索專區案件

							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							break;

						case "3" : // 3-審核中
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonMalware", false); // 是否顯示轉勒索專區案件


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
							sn_json.put("IsButtonMalware", true); // 是否顯示轉勒索專區案件

							break;

						case "5" : // 5-已銷案
							sn_json.put("IsSeeLog", true); // 是否顯示簽核資訊
							sn_json.put("IsButtonEdit", false); // 是否顯示編輯按鍵
							sn_json.put("IsButtonDelete", false); // 是否顯示刪除按鍵
							sn_json.put("IsButtonUndo", false); // 是否顯示撤銷按鍵
							sn_json.put("IsButtonReview", false); // 是否顯示審核按鍵
							sn_json.put("IsButtonMalware", false); // 是否顯示轉勒索專區案件

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
	 * 取得AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            AnaManagement資料
	 * @return AnaManagement資料
	 */
	@RequestMapping(value = "/s39/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			// 流程紀錄用
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");

			// 取得指定 id 之資安訊息情報資料
			AnaManagement anaManagement = anaManagementService.get(id);

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
			sn_json.put("Id", anaManagement.getId());
			sn_json.put("PostId", anaManagement.getPostId());
			sn_json.put("IncidentId", anaManagement.getIncidentId());
			sn_json.put("IncidentTitle", anaManagement.getIncidentTitle());
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("IncidentReportedTime", WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
			sn_json.put("Description", anaManagement.getDescription());
			sn_json.put("EventTypeCode", anaManagement.getEventTypeCode());
			if (anaManagement.getEventTypeCode() != null) {
				EventType eventType = eventTypeService.findByCode(anaManagement.getEventTypeCode());
				sn_json.put("EventTypeCodeName", eventType.getName());
			}
			sn_json.put("ReporterName", anaManagement.getReporterName());
			sn_json.put("ResponderPartyName", anaManagement.getResponderPartyName());
			sn_json.put("ResponderContactNumbers", anaManagement.getResponderContactNumbers());
			sn_json.put("ResponderElectronicAddressIdentifiers", anaManagement.getResponderElectronicAddressIdentifiers());
			sn_json.put("ImpactQualification", anaManagement.getImpactQualification());
			sn_json.put("CoaDescription", anaManagement.getCoaDescription());
			sn_json.put("Confidence", anaManagement.getConfidence());
			sn_json.put("Reference", anaManagement.getReference());
			sn_json.put("AffectedSoftwareDescription", anaManagement.getAffectedSoftwareDescription());
			sn_json.put("StartDateTime", WebDatetime.toString(anaManagement.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(anaManagement.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", anaManagement.getIsEnable());
			sn_json.put("CreateId", anaManagement.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(anaManagement.getCreateTime()));
			sn_json.put("ModifyId", anaManagement.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(anaManagement.getModifyTime()));
			sn_json.put("Status", anaManagement.getStatus());
			sn_json.put("IsMedical", anaManagement.getIsMedical());
			sn_json.put("Sort", anaManagement.getSort());

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
	 * 利用AnaManagement Id 新增MalwareManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            AnaManagement資料
	 * @return 是否新增成功
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/s39/createMalware/id", method = RequestMethod.POST)
	public String CreateMalwareById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		Date now = new Date();

		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

		// 取得指定 id 之資安訊息情報資料
		AnaManagement anaManagement = anaManagementService.get(id);
		
		//將資安訊息情報資料封裝成json 已便於寫入Malwaremanagement 資料庫
		JSONObject sn_json = new JSONObject();
		sn_json.put("IncidentId", anaManagement.getIncidentId());
		sn_json.put("Title", anaManagement.getIncidentTitle());
		sn_json.put("Content", anaManagement.getDescription());
		sn_json.put("ContentType", "1");
		sn_json.put("EventTypeCode", anaManagement.getEventTypeCode());
		if (anaManagement.getEventTypeCode() != null) {
			EventType eventType = eventTypeService.findByCode(anaManagement.getEventTypeCode());
			sn_json.put("EventTypeCodeName", eventType.getName());
		}
		
		sn_json.put("PostDateTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
		
		
		sn_json.put("Status", 4 );
		sn_json.put("ReporterName", anaManagement.getReporterName());
		sn_json.put("ResponderPartyName", anaManagement.getResponderPartyName());
		sn_json.put("ResponderContactNumbers", anaManagement.getResponderContactNumbers());
		sn_json.put("ResponderElectronicAddressIdentifiers", anaManagement.getResponderElectronicAddressIdentifiers());
		sn_json.put("ImpactQualification", anaManagement.getImpactQualification());
		sn_json.put("CoaDescription", anaManagement.getCoaDescription());
		sn_json.put("Confidence", anaManagement.getConfidence());
		sn_json.put("Reference", anaManagement.getReference());
		sn_json.put("AffectedSoftwareDescription", anaManagement.getAffectedSoftwareDescription());
		sn_json.put("StartDateTime", WebDatetime.toString(anaManagement.getStartDateTime(), "yyyy-MM-dd"));
		sn_json.put("EndDateTime", WebDatetime.toString(anaManagement.getEndDateTime(), "yyyy-MM-dd"));
		sn_json.put("IsEnable", true);
		
		sn_json.put("IsPublic", true);

		
		sn_json.put("Sort", anaManagement.getSort());
		sn_json.put("TableName", "malware_management");
		sn_json.put("Pre", "HISAC");

		
		MalwareManagement malwaremanagement = malwareManagementService.insert(getBaseMemberId(), sn_json.toString(), false);
		
		
		
		long malwaremanagementId = malwaremanagement.getId();
		
		if (!malwareManagementService.isExist(malwaremanagementId)) {
			responseJson.put("msg", WebMessage.getMessage("globalDataInsertFail", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());		
		}else {
			try {
//				System.out.println(anaManagement.getId());
				//利用anaManagementId 取出 ViewAnaManagementAttachMember 物件進行附件檔案取得並複製寫入 MalwareManagementAttach
				List<AnaManagementAttach> anaManagementAttachMembers = anaManagementAttachService.getAllAttachByAnaManagementId(id);
				
//				System.out.println(anaManagementAttachMembers);

				if (anaManagementAttachMembers != null ) {
					for(AnaManagementAttach  anaManagementAttach : anaManagementAttachMembers) {
						JSONObject attach_json = new JSONObject();
						attach_json.put("MalwareManagementId", malwaremanagement.getId()) ;
						attach_json.put("FileDesc", anaManagementAttach.getFileDesc());
						attach_json.put("FileName", anaManagementAttach.getFileName());
						attach_json.put("FileType", anaManagementAttach.getFileType());
						attach_json.put("FileSize", anaManagementAttach.getFileSize());
						attach_json.put("FileHash", anaManagementAttach.getFileHash());
						byte[] bytes = anaManagementAttach.getFileContent();
						MalwareManagementAttach entity = malwareManagementAttachService.insert(getBaseMemberId(), attach_json.toString(), bytes);	
					}
				}			
			}
			catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		
		if (malwaremanagement != null) {
			
			anaManagement.setIsMalware(true);
			anaManagement.setModifyId(getBaseMemberId());
			anaManagement.setModifyTime(now);
			anaManagementDAO.update(anaManagement);

			
			
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		
		}else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		

		

		model.addAttribute("json", responseJson.toString());

		
		return "msg";
	}

	/**
	 * 新增AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            AnaManagement
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s39/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			AnaManagement anamanagement = anaManagementService.insert(getBaseMemberId(), json, false);

			// 流程紀錄用 - 開始
			JSONObject obj = new JSONObject(json);
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (isWriteProcessLog) {
				// 寄信
				// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
				// 主旨：H-ISAC資安訊息情報("not.getPostId()")審核通知
				// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！資安訊息情報("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
																							// H-ISAC內容審核者
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member member = memberService.get(memberRole.getMemberId());
						if (member != null && member.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailAna1To3Subject"), anamanagement.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailAna1To3Body"), member.getName(), anamanagement.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}

				processLogService.insert(getBaseMemberId(), json, String.valueOf(anamanagement.getId()));
			}
			// 流程紀錄用 - 結束

			if (anamanagement != null) {

				// 流程紀錄用 - 開始
				responseJson.put("Id", anamanagement.getId());
				responseJson.put("PostId", anamanagement.getPostId());
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
	 * 更新AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            AnaManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s39/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!anaManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				AnaManagement anaManagement = anaManagementService.update(getBaseMemberId(), json, false);

				// 流程紀錄用 - 開始
				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

				if (isWriteProcessLog) {
					processLogService.insert(getBaseMemberId(), json, String.valueOf(anaManagement.getId()));

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
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailAna1To3Subject"), anaManagement.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailAna1To3Body"), member.getName(), anaManagement.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}
				// 流程紀錄用 - 結束

				if (anaManagement != null) {

					// 流程紀錄用 - 開始
					responseJson.put("Id", anaManagement.getId());
					responseJson.put("PostId", anaManagement.getPostId());
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
	 * 更新NewsManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            NewsManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s39/modify", method = RequestMethod.POST)
	public @ResponseBody String Modify(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");

			if (!anaManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				AnaManagement anaManagement = anaManagementService.modify(getBaseMemberId(), json, false);
				if (anaManagement != null) {
					responseJson.put("Id", anaManagement.getId());
					responseJson.put("PostId", anaManagement.getPostId());
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
	 * 停用AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            AnaManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s39/disable", method = RequestMethod.POST)
	public @ResponseBody String Disable(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!anaManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				anaManagementService.disable(getBaseMemberId(), id);
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 刪除AnaManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s39/delete", method = RequestMethod.POST)
	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!anaManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				// 同時刪除上傳附件資料
				try {
					List<ViewAnaManagementAttachMember> anaManagementAttachs = anaManagementAttachService.getAllByAnaManagementId(id);
					if (anaManagementAttachs != null) {
						for (ViewAnaManagementAttachMember anaManagementAttach : anaManagementAttachs) {
							anaManagementAttachService.delete(anaManagementAttach.getId());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if (anaManagementService.delete(id)) {
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
	 * 取得EventType資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return EventType資料
	 */
	@RequestMapping(value = "/s39/query/eventType", method = RequestMethod.POST)
	public String QueryEventType(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<EventType> eventTypes = eventTypeService.getAnaList();
			if (eventTypes != null) {
				for (EventType eventType : eventTypes) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", eventType.getId());
					sn_json.put("Code", eventType.getCode());
					sn_json.put("Name", eventType.getName());
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
	@RequestMapping(value = "/s39/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long anaManagementId = obj.isNull("AnaManagementId") == true ? 0 : obj.getLong("AnaManagementId");
			List<ViewAnaManagementAttachMember> anaManagementAttachs = anaManagementAttachService.getAllByAnaManagementId(anaManagementId);
			if (anaManagementAttachs != null) {
				for (ViewAnaManagementAttachMember anaManagementAttach : anaManagementAttachs) {
					long size = anaManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("AnaManagementId", anaManagementAttach.getAnaManagementId());
					sn_json.put("Id", anaManagementAttach.getId());
					sn_json.put("FileName", anaManagementAttach.getFileName());
					sn_json.put("FileType", anaManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", anaManagementAttach.getFileHash());
					sn_json.put("FileDesc", anaManagementAttach.getFileDesc());
					sn_json.put("CreateId", anaManagementAttach.getCreateId());
					sn_json.put("CreateName", anaManagementAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(anaManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", anaManagementAttach.getModifyId());
					sn_json.put("ModifyName", anaManagementAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(anaManagementAttach.getModifyTime()));
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
	 *            AnaManagementId
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
	@RequestMapping(value = "/s39/attach/upload", method = RequestMethod.POST)
	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("AnaManagementId", id);
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
					AnaManagement anaManagement = anaManagementService.get(id);
					if (anaManagement != null) {
						AnaManagementAttach entity = anaManagementAttachService.insert(getBaseMemberId(), json, bytes);
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
			systemLogService.insert(baseControllerName, baseActionName, "AnaManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	@RequestMapping(value = "/s39/attach/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!anaManagementAttachService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (anaManagementAttachService.delete(id)) {
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
	 * @param anaManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/s39/attach/download/{anaManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long anaManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("AnaManagementId", anaManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!anaManagementService.isExist(anaManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!anaManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				AnaManagementAttach anaManagementAttach = anaManagementAttachService.getById(id);
				try {
					byte[] buffer = anaManagementAttach.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(anaManagementAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(anaManagementAttach.getFileName()));
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
	 * 審核資安訊息情報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/s39/examine", method = RequestMethod.POST)
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

			if (!anaManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {

				// String oldPostId =
				// anaManagementService.getById(String.valueOf(id)).getPostId();

				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, String.valueOf(id));
				AnaManagement anaManagement = anaManagementService.examine(getBaseMemberId(), String.valueOf(id), String.valueOf(status));

				if (anaManagement != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

					// 清除快取
					anaManagementService.resetGlobalData();
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
	 * 取得資安訊息情報button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 資安訊息情報button count資料
	 */
	@RequestMapping(value = "/s39/query/button/count", method = RequestMethod.POST)
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
			List<SpButtonCount> spButtonCounts = anaManagementService.getSpButtonCount(json);
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