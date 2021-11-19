package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.MaintainPlan;
import tw.gov.mohw.hisac.web.domain.MaintainPlanAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroup;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroupOrg;
import tw.gov.mohw.hisac.web.domain.MaintainPlanItem;
import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanResultAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanScoreAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImplementAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestRead;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContent;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContentFilter;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.MaintainPlanOtherAttach;
import tw.gov.mohw.hisac.web.service.MaintainInspectHospitalUploadService;
import tw.gov.mohw.hisac.web.service.MaintainInspectMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanContentService;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupOrgService;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupService;
import tw.gov.mohw.hisac.web.service.MaintainPlanItemService;
import tw.gov.mohw.hisac.web.service.MaintainPlanMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanOtherAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanResultAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanScoreAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSelfEvaluationAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSelfEvaluationSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanImprovementAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSuggestAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSuggestReadService;
import tw.gov.mohw.hisac.web.service.MaintainPlanImplementAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 共同維護計畫控制器
 */
@Controller
@RequestMapping(value = "/kin/api", produces = "application/json; charset=utf-8")
public class k03_MaintainInspectHospitalUploadController extends BaseController {
	@Autowired
	private MaintainInspectHospitalUploadService maintainInspectHospitalUploadService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MaintainPlanQuestionnaireAttachService maintainPlanQuestionnaireAttachService;	
	@Autowired
	private MaintainPlanCheckListAttachService maintainPlanCheckListAttachService;	
	@Autowired
	private MaintainPlanImprovementAttachService maintainPlanImprovementAttachService;
	@Autowired
	private MaintainPlanCheckListSampleAttachService maintainPlanCheckListSampleAttachService;	
	@Autowired
	private MaintainPlanQuestionnaireSampleAttachService maintainPlanQuestionnaireSampleAttachService;	
	@Autowired
	private MaintainPlanSelfEvaluationAttachService maintainPlanSelfEvaluationAttachService;
	@Autowired
	private MaintainPlanOtherAttachService maintainPlanOtherAttachService;
	@Autowired
	private MaintainPlanResultAttachService maintainPlanResultAttachService;
	@Autowired
	private MaintainPlanSelfEvaluationSampleAttachService maintainPlanSelfEvaluationSampleAttachService;
	@Autowired
	private MaintainInspectMemberService maintainInspectMemberService;

	private String targetControllerName = "kin";
	private String targetActionName = "k03";

	/**
	 * 取得資安維護稽核作業資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 資安維護稽核作業
	 */
	@RequestMapping(value = "/k03/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			
			JSONArray sn_array = new JSONArray();
			List<ViewMaintainInspectMemberOrg> maintainInspectMembers = maintainInspectHospitalUploadService.getList(getBaseOrgId(), json);
			Date todayDate = new Date();
			if (maintainInspectMembers != null)
				for (ViewMaintainInspectMemberOrg maintainInspectMember : maintainInspectMembers) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", maintainInspectMember.getMaintainInspectId());
					sn_json.put("Year", maintainInspectMember.getYear());
					sn_json.put("Title", maintainInspectMember.getTitle());
					sn_json.put("Status", maintainInspectMember.getInspectStatus());
					String hospitaSdate =  WebDatetime.toString(maintainInspectMember.getHospitalUploadSdate(), "yyyy-MM-dd");
					String hospitaEdate =  WebDatetime.toString(maintainInspectMember.getHospitalUploadEdate(), "yyyy-MM-dd");
					if(todayDate.after(WebDatetime.parseSdate(hospitaSdate)) && todayDate.before(WebDatetime.parseEdate(hospitaEdate))) {
						sn_json.put("HospitalUpload", true);
					}else {
						sn_json.put("HospitalUpload", false);
					}
					String committeeSdate =  WebDatetime.toString(maintainInspectMember.getCommitteeUploadSdate(), "yyyy-MM-dd") ;
					String committeeEdate =  WebDatetime.toString(maintainInspectMember.getCommitteeUploadEdate(), "yyyy-MM-dd") ;
					if(maintainInspectMember.getCommitteeUploadSdate() != null) {
						if(todayDate.after(WebDatetime.parseSdate(committeeSdate)) && todayDate.before(WebDatetime.parseEdate(committeeEdate))) {
							sn_json.put("CommitteeUpload", true);
						}else {
							sn_json.put("CommitteeUpload", false);
						}
					}
					sn_json.put("AllowHospitalPatch", maintainInspectMember.getAllowHospitalPatch());
					sn_array.put(sn_json);
				}
			responseJson.put("total", maintainInspectHospitalUploadService.getListSize(getBaseOrgId(), json));
			responseJson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		}else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";			
	}
	
	/**
	 * 取得資安維護稽核作業資料詳細資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            警訊Id
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/k03/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");	
		JSONObject responseJson = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			ViewMaintainInspectMemberOrg maintainInspectMember = maintainInspectHospitalUploadService.getIdByMaintainInspectIdAndGroupId(id,groupId);
			//log
			JSONArray log_array = new JSONArray();
			List<ViewProcessLogMember> maintainInspect_processLogs = processLogService.getByPostId(String.valueOf(maintainInspectMember.getId()), "maintainInspectMember");
			if (maintainInspect_processLogs != null) {
				for (ViewProcessLogMember processLog : maintainInspect_processLogs) {
					JSONObject log_json = new JSONObject();
					if((processLog.getPreStatus().equals("4") && processLog.getStatus().equals("5")) || (processLog.getPreStatus().equals("8") && processLog.getStatus().equals("9"))) 
					{
						log_json.put("PreStatus", processLog.getPreStatus());
						log_json.put("Status", processLog.getStatus());
						log_json.put("CreateTime", WebDatetime.toString(processLog.getCreateTime()));							
						log_json.put("CreateName", processLog.getCreateName());
						log_json.put("PreName", processLog.getPreName());		
						log_array.put(log_json);
					}
				}
				responseJson.put("Log",log_array);
			}
			responseJson.put("Id", maintainInspectMember.getId());
			
			responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
			responseJson.put("Title", maintainInspectMember.getTitle());
			
			responseJson.put("Year", maintainInspectMember.getYear());
			//醫院稽核時間
			responseJson.put("HospitalUploadSdate", maintainInspectMember.getHospitalUploadSdate());
			//醫院截止時間
			responseJson.put("HospitalUploadEdate", maintainInspectMember.getHospitalUploadEdate());
			responseJson.put("OrgId",getBaseOrgId());
			if (groupId !=(long)0 && maintainInspectMember!=null && maintainInspectMember.getInspectMemberId() != null) {
				//file
				//資通安全實地稽核項目檢核表
				MaintainPlanCheckListAttach maintainPlanCheckListAttach = maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanCheckListAttach != null) {
					responseJson.put("FileCheckListName", maintainPlanCheckListAttach.getFileName());
					responseJson.put("FileCheckListId", maintainPlanCheckListAttach.getId());												
				}
				//受稽機關現況調查表
				MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach = maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanQuestionnaireAttach != null) {
					responseJson.put("FileQuestionnaireName", maintainPlanQuestionnaireAttach.getFileName());
					responseJson.put("FileQuestionnaireId", maintainPlanQuestionnaireAttach.getId());												
				}
				//技術檢測自評表
				MaintainPlanSelfEvaluationAttach maintainPlanSelfEvaluationAttach = maintainPlanSelfEvaluationAttachService.getByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanSelfEvaluationAttach != null) {
					responseJson.put("FileSelfEvaluationName", maintainPlanSelfEvaluationAttach.getFileName());
					responseJson.put("FileSelfEvaluationId", maintainPlanSelfEvaluationAttach.getId());												
				}
				
				MaintainPlanResultAttach maintainPlanResultAttach = maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanResultAttach != null) {
					responseJson.put("FileAuditResultName", maintainPlanResultAttach.getFileName());
					responseJson.put("FileAuditResultId", maintainPlanResultAttach.getId());		
					responseJson.put("AuditResultAbstract", maintainPlanResultAttach.getFileDesc());
				}
				
				MaintainPlanImprovementAttach maintainPlanImprovementAttach = maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanImprovementAttach != null) {
					responseJson.put("FileImprovementName", maintainPlanImprovementAttach.getFileName());
					responseJson.put("FileImprovementId", maintainPlanImprovementAttach.getId());	
					responseJson.put("ImprovementAbstract", maintainPlanImprovementAttach.getFileDesc());
				}
				
				//其他檔案
				List<MaintainPlanOtherAttach> maintainPlanOtherAttachs = maintainPlanOtherAttachService.getListByMaintainPlanIdAndGroupId(maintainInspectMember.getMaintainInspectId(), groupId);
				if (maintainPlanOtherAttachs != null) {
					JSONArray sn_array = new JSONArray();
					for(MaintainPlanOtherAttach maintainPlanOtherAttach:maintainPlanOtherAttachs) {
						JSONObject fileJson = new JSONObject();	
						fileJson.put("FileOtherName", maintainPlanOtherAttach.getFileName());
						fileJson.put("FileOtherDesc", maintainPlanOtherAttach.getFileDesc());
						fileJson.put("FileOtherId", maintainPlanOtherAttach.getId());
						sn_array.put(fileJson);
					}
					responseJson.put("Other",sn_array);
				}
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	
	/**
	 * 資安維護稽核作業資料上傳API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 資安維護稽核作業
	 */
	@RequestMapping(value = "/k03/questionnaire_submit", method = RequestMethod.POST)
	public @ResponseBody String QuestionnaireSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileQuestionnaire", required = false) MultipartFile fileQuestionnaire,			
			@RequestParam("FileQuestionnaireDesc") String fileQuestionnaireDesc,
			@RequestParam(value = "fileCheckList", required = false) MultipartFile fileCheckList,			
			@RequestParam("FileCheckListDesc") String fileCheckListDesc,
			@RequestParam(value = "fileSelfEvaluation", required = false) MultipartFile fileSelfEvaluation,
			@RequestParam("FileSelfEvaluationDesc") String fileSelfEvaluationDesc
			) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			//早期稽核跟資安管理是放在一起的所以欄位變數會看到maintainPlan
			Long maintainPlanId = obj.isNull("MaintainInspectId") == true ? null : obj.getLong("MaintainInspectId");			
			Long fileQuestionnaireId = obj.isNull("FileQuestionnaireId") == true ? null : obj.getLong("FileQuestionnaireId");
			Long fileCheckListId = obj.isNull("FileCheckListId") == true ? null : obj.getLong("FileCheckListId");
			Long fileSelfEvaluationId = obj.isNull("FileSelfEvaluationId") == true ? null : obj.getLong("FileSelfEvaluationId");
			Boolean check = obj.isNull("Check") == true ? false : obj.getBoolean("Check");
			MaintainPlanQuestionnaireAttach insert1 = null;
			MaintainPlanCheckListAttach insert2 = null;
			MaintainPlanSelfEvaluationAttach insert3 = null;
			try {
				
				//刪除舊檔案
				MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach = maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanQuestionnaireAttach != null && !maintainPlanQuestionnaireAttach.getId().equals(fileQuestionnaireId)){
					maintainPlanQuestionnaireAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanQuestionnaireAttach=null;
				}
				MaintainPlanCheckListAttach maintainPlanCheckListAttach = maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanCheckListAttach != null && !maintainPlanCheckListAttach.getId().equals(fileCheckListId))	{			
					maintainPlanCheckListAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanCheckListAttach=null;
				}
				
				MaintainPlanSelfEvaluationAttach maintainPlanSelfEvaluationAttach = maintainPlanSelfEvaluationAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanSelfEvaluationAttach != null && !maintainPlanSelfEvaluationAttach.getId().equals(fileSelfEvaluationId))	{			
					maintainPlanSelfEvaluationAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanSelfEvaluationAttach=null;
				}
				
				if(check) {
					MaintainInspectMember maintainInspectMember = maintainInspectMemberService.questionnaire_submit(maintainPlanId, getBaseOrgId());
					JSONObject maintainPlanMember_obj = new JSONObject();						
					maintainPlanMember_obj.put("PreStatus", "4");
					maintainPlanMember_obj.put("Status", "5");	
					maintainPlanMember_obj.put("TableName", "maintainInspectMember");
					processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainInspectMember.getId().toString());											
				}
				
				//新增檔案
				if (fileQuestionnaire != null && !fileQuestionnaire.isEmpty() && maintainPlanQuestionnaireAttach==null) {
					// 檔案 byte array
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileQuestionnaireDesc); // 檔案說明
					sn_json.put("FileName", fileQuestionnaire.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileQuestionnaire.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileQuestionnaire.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileQuestionnaire.toString())); // 檔案Hash
					byte[] bytes = fileQuestionnaire.getBytes();
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanQuestionnaireAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes);
					}
				
				if (fileCheckList != null && !fileCheckList.isEmpty() && maintainPlanCheckListAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileCheckList.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileCheckListDesc); // 檔案說明
					sn_json.put("FileName", fileCheckList.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileCheckList.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileCheckList.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileCheckList.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert2 = maintainPlanCheckListAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes);
					}
				if (fileSelfEvaluation != null && !fileSelfEvaluation.isEmpty() && maintainPlanSelfEvaluationAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileSelfEvaluation.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileSelfEvaluationDesc); // 檔案說明
					sn_json.put("FileName", fileSelfEvaluation.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileSelfEvaluation.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileSelfEvaluation.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileSelfEvaluation.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert3 = maintainPlanSelfEvaluationAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes);
					}
				}catch (Exception e) {
					System.out.println(e);
					insert1 = null;
					insert2 = null;
					insert3 = null;
			}			
			
			if (insert1 != null || insert2 != null || (fileQuestionnaireId != null &&  fileCheckListId != null)) {										
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
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	/**
	 * 其他檔案上傳API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 檔案
	 */
	@RequestMapping(value = "/k03/otherFileUpload", method = RequestMethod.POST)
	public @ResponseBody String OtherFileUpload(Locale locale, HttpServletRequest request,@RequestParam("json") String json,
			@RequestParam(value = "fileOther", required = false) MultipartFile fileOther,			
			@RequestParam("FileOtherDesc") String fileOtherDesc) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			long maintainPlanId = obj.isNull("MaintainInspectId") == true ? 0 : obj.getLong("MaintainInspectId");	
			MaintainPlanOtherAttach insert1 = new MaintainPlanOtherAttach();
			try {								
				//新增檔案
				if (fileOther != null && !fileOther.isEmpty()) {
					// 檔案 byte array
					byte[] bytes = fileOther.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileOtherDesc); // 檔案說明
					sn_json.put("FileName", fileOther.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileOther.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileOther.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileOther.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanOtherAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes);
					}
				}catch (Exception e) {				
					insert1 = null;
			}			
			
			if (insert1 != null) {										
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
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	 /**
	 * 刪除其他檔案資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *          其他檔案id
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/k03/fileOtherDelete", method = RequestMethod.POST)
	public String DeleteByFileId(Locale locale, HttpServletRequest request,Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") ? 0 : obj.getLong("Id");
		if (menuService.getDeletePermission(getBaseMemberId(),targetControllerName, targetActionName)) {
				json = WebCrypto.getSafe(json);
				MaintainPlanOtherAttach maintainPlanOtherAttach = maintainPlanOtherAttachService.getById(id);
				if (maintainPlanOtherAttach != null) {
					if(maintainPlanOtherAttachService.delete(id)) {
						responseJson.put("msg", WebMessage.getMessage(
								"globalDeleteDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					}
					responseJson.put("msg", WebMessage.getMessage(
							"globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
						responseJson.put("msg", WebMessage.getMessage(
								"globalDeleteDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName,
								baseActionName, json,
								SystemLogVariable.Action.Delete,
								SystemLogVariable.Status.Fail,
								getBaseIpAddress(), getBaseMemberAccount());
				}
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	@RequestMapping(value = "/k03/improvement_submit", method = RequestMethod.POST)
	public @ResponseBody String ImprovementSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileImprovement", required = false) MultipartFile fileImprovement,			
			@RequestParam("FileImprovementDesc") String fileImprovementDesc) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainInspectId") == true ? 0 : obj.getLong("MaintainInspectId");			
			long fileImprovementId = obj.isNull("FileImprovementId") == true ? 0 : obj.getLong("FileImprovementId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			MaintainPlanImprovementAttach insert1 = new MaintainPlanImprovementAttach();
			try {	
				//刪除舊檔案
				MaintainPlanImprovementAttach maintainPlanImprovementAttach = maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
				if (maintainPlanImprovementAttach != null && !maintainPlanImprovementAttach.getId().equals(fileImprovementId))	{			
					maintainPlanImprovementAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
					maintainPlanImprovementAttach=null;
				}
				MaintainInspectMember maintainInspectMember = maintainInspectMemberService.improvement_submit(maintainPlanId, groupId);
				JSONObject maintainPlanMember_obj = new JSONObject();						
				maintainPlanMember_obj.put("PreStatus", "8");
				maintainPlanMember_obj.put("Status", "9");	
				maintainPlanMember_obj.put("TableName", "maintainInspectMember");								
				processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainInspectMember.getId().toString());											
				
				//新增檔案
				if (fileImprovement != null && !fileImprovement.isEmpty() && maintainPlanImprovementAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileImprovement.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileImprovementDesc); // 檔案說明
					sn_json.put("FileName", fileImprovement.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileImprovement.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileImprovement.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileImprovement.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanImprovementAttachService.insert(getBaseMemberId(), json1, groupId, bytes);
					}								
				}catch (Exception e) {				
					insert1 = null;					
			}			
			
			if (insert1 != null) {										
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
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/improvement/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadImprovementAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanImprovementAttach maintainPlanImprovementAttach = maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanImprovementAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanImprovementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanImprovementAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	/**
	 * 取得警訊button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 警訊button count資料
	 */
	@RequestMapping(value = "/k03/query/button/count", method = RequestMethod.POST)
	public String QueryMessage(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);

			JSONObject obj = new JSONObject(json);
			obj.put("InspectStatus", "4");
			long num5 = maintainInspectHospitalUploadService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status4Count", num5);
			obj.put("InspectStatus", "8");
			long num8 = maintainInspectHospitalUploadService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status8Count", num8);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	/**
	 * 附件下載 CheckList
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/download/CheckList", method = RequestMethod.GET)
	public void DownloadCheckList(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanCheckListSampleAttach maintainPlanCheckListSampleAttach = maintainPlanCheckListSampleAttachService.get();
			if (maintainPlanCheckListSampleAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanCheckListSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanCheckListSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,  getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny,  getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
	/**
	 * 附件下載 Questionnaire
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse	
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/download/Questionnaire", method = RequestMethod.GET)
	public void DownloadQuestionnaire(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanQuestionnaireSampleAttach maintainPlanQuestionnaireSampleAttach = maintainPlanQuestionnaireSampleAttachService.get();
			if (maintainPlanQuestionnaireSampleAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanQuestionnaireSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanQuestionnaireSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	/**
	 * 附件下載 SelfEvaluation
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse	
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/download/SelfEvaluation", method = RequestMethod.GET)
	public void DownloadSelfEvaluation(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanSelfEvaluationSampleAttach maintainPlanSelfEvaluationSampleAttach = maintainPlanSelfEvaluationSampleAttachService.get();
			if (maintainPlanSelfEvaluationSampleAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanSelfEvaluationSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanSelfEvaluationSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

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
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/checkList/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadCheckListAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanCheckListAttach maintainPlanCheckListAttach = maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanCheckListAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanCheckListAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanCheckListAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
	/**
	 * 附件下載 questionnaire
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/questionnaire/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadQuestionnaireAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach = maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanQuestionnaireAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanQuestionnaireAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanQuestionnaireAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
	/**
	 * 附件下載 selfEvaluation
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/selfEvaluation/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadSelfEvaluationAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanSelfEvaluationAttach maintainPlanSelfEvaluationAttach = maintainPlanSelfEvaluationAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanSelfEvaluationAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanSelfEvaluationAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanSelfEvaluationAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
	/**
	 * 附件下載 selfEvaluation
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/other/{Id}", method = RequestMethod.GET)
	public void DownloadOtherAttach(Locale locale, HttpServletRequest request, HttpServletResponse response,@PathVariable Long Id) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanOtherAttach maintainPlanOtherAttach = maintainPlanOtherAttachService.getById(Id);
			if (maintainPlanOtherAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanOtherAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanOtherAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
	/**
	 * 附件下載 Result
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k03/attach/download/auditResult/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadResultAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanResultAttach maintainPlanResultAttach = maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanResultAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanResultAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanResultAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}
	
}