package tw.gov.mohw.hisac.web.controller.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanOtherAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanResultAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanReviewOpinionAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanScoreAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationSampleAttach;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.service.MaintainInspectCommitteeService;
import tw.gov.mohw.hisac.web.service.MaintainInspectMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanImprovementAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanOtherAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanResultAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanReviewOpinionAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanScoreAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSelfEvaluationAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSelfEvaluationSampleAttachService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 共同維護計畫控制器
 */
@Controller
@RequestMapping(value = "/kin/api", produces = "application/json; charset=utf-8")
public class k01_MaintainInspectMemberController extends BaseController {
	private String targetControllerName = "kin";
	private String targetActionName = "k01";
	
	@Autowired
	private MaintainInspectMemberService maintainInspectMemberService;

	@Autowired
	private MaintainInspectCommitteeService maintainInspectCommitteeService;
	
	@Autowired
	private ProcessLogService processLogService;
	
	@Autowired
	private MaintainPlanCheckListAttachService maintainPlanCheckListAttachService;
	
	@Autowired
	private MaintainPlanQuestionnaireAttachService maintainPlanQuestionnaireAttachService;
	
	@Autowired
	private MaintainPlanSelfEvaluationAttachService maintainPlanSelfEvaluationAttachService;
	
	@Autowired
	private MaintainPlanOtherAttachService maintainPlanOtherAttachService;

	@Autowired
	private MaintainPlanScoreAttachService maintainPlanScoreAttachService;

	@Autowired
	private MaintainPlanResultAttachService maintainPlanResultAttachService;

	@Autowired
	private MaintainPlanImprovementAttachService maintainPlanImprovementAttachService;
	
	@Autowired
	private MaintainPlanCheckListSampleAttachService maintainPlanCheckListSampleAttachService;	
	
	@Autowired
	private MaintainPlanQuestionnaireSampleAttachService maintainPlanQuestionnaireSampleAttachService;
	
	@Autowired
	private MaintainPlanSelfEvaluationSampleAttachService maintainPlanSelfEvaluationSampleAttachService;
	
	@Autowired
	private MaintainPlanReviewOpinionAttachService maintainPlanReviewOpinionAttachService;
	/**
	 * 取得警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/k01/query", method = RequestMethod.POST)
	public @ResponseBody String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			maintainInspectMemberService
			listjson.put("total", maintainInspectMemberService.getMaintainInspectListSize(json));
			List<JSONObject> maintainPlanMembers = maintainInspectMemberService.getMaintainInspectList(json);
			if (maintainPlanMembers != null) {
				JSONArray sn_array = new JSONArray();
				for (JSONObject maintainPlanMember : maintainPlanMembers) {
					sn_array.put(maintainPlanMember);
				}
				listjson.put("datatable", sn_array);
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(listjson.toString());			
	}

	/**
	 * 取得警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/k01/queryHospital", method = RequestMethod.POST)
	public @ResponseBody String QueryHospital(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long maintainInspectId = obj.isNull("MaintainInspectId") == true ? 0 : obj.getLong("MaintainInspectId");
			List<ViewMaintainInspectMemberOrg> maintainInspectMembers =
					maintainInspectMemberService.getMaintainInspectListByMaintainInspectId(maintainInspectId);
			if (maintainInspectMembers != null) {
				JSONArray sn_array = new JSONArray();
				for (ViewMaintainInspectMemberOrg maintainInspectMember : maintainInspectMembers) {
					JSONObject sn_json = new JSONObject();
					
					sn_json.put("Id", maintainInspectMember.getId());
					sn_json.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
					sn_json.put("GroupId", maintainInspectMember.getGroupId());
					sn_json.put("OrgName", maintainInspectMember.getOrgName());

					sn_json.put("HospitalUploadSdate", WebDatetime.toString(
							maintainInspectMember.getHospitalUploadSdate(), "yyyy-MM-dd"));
					sn_json.put("HospitalUploadEdate", WebDatetime.toString(
							maintainInspectMember.getHospitalUploadEdate(), "yyyy-MM-dd"));
					sn_json.put("CommitteeUploadSdate", WebDatetime.toString(
							maintainInspectMember.getCommitteeUploadSdate(), "yyyy-MM-dd"));
					sn_json.put("CommitteeUploadEdate", WebDatetime.toString(
							maintainInspectMember.getCommitteeUploadEdate(), "yyyy-MM-dd"));

					sn_json.put("InspectStatus", maintainInspectMember.getInspectStatus());
					sn_json.put("InspectMemberId", maintainInspectMember.getInspectMemberId());
					sn_json.put("InspectMemberName", maintainInspectMember.getInspectMemberName());
					sn_json.put("AllowHospitalPatch", maintainInspectMember.getAllowHospitalPatch());
					sn_json.put("Title", maintainInspectMember.getTitle());
					sn_json.put("Year", maintainInspectMember.getYear());
					sn_json.put("Status", maintainInspectMember.getStatus());
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(listjson.toString());			
	}

	/**
	 * 取得警訊資料API
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
	@RequestMapping(value = "/k01/queryHospital/id", method = RequestMethod.POST)
	public @ResponseBody String QueryHospitalById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {		
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		
		JSONObject sn_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			ViewMaintainInspectMemberOrg maintainInspectMember = maintainInspectMemberService.findMaintainInspectById(id);
			if(maintainInspectMember!=null) {
				sn_json.put("Id", maintainInspectMember.getId());
				sn_json.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
				sn_json.put("GroupId", maintainInspectMember.getGroupId());
				sn_json.put("OrgName", maintainInspectMember.getOrgName());

				sn_json.put("HospitalUploadSdate", WebDatetime.toString(
						maintainInspectMember.getHospitalUploadSdate(), "yyyy-MM-dd"));
				sn_json.put("HospitalUploadEdate", WebDatetime.toString(
						maintainInspectMember.getHospitalUploadEdate(), "yyyy-MM-dd"));
				sn_json.put("CommitteeUploadSdate", WebDatetime.toString(
						maintainInspectMember.getCommitteeUploadSdate(), "yyyy-MM-dd"));
				sn_json.put("CommitteeUploadEdate", WebDatetime.toString(
						maintainInspectMember.getCommitteeUploadEdate(), "yyyy-MM-dd"));
				
				sn_json.put("InspectStatus", maintainInspectMember.getInspectStatus());
				sn_json.put("InspectMemberId", maintainInspectMember.getInspectMemberId());
				sn_json.put("InspectMemberName", maintainInspectMember.getInspectMemberName());
				sn_json.put("AllowHospitalPatch", maintainInspectMember.getAllowHospitalPatch());
				sn_json.put("Title", maintainInspectMember.getTitle());
				sn_json.put("Year", maintainInspectMember.getYear());
				sn_json.put("Status", maintainInspectMember.getStatus());

				Long maintainInspectId = maintainInspectMember.getMaintainInspectId();
				Long groupId = maintainInspectMember.getGroupId();
				
				String inspectStatus = maintainInspectMember.getInspectStatus();
				//取得稽核委員名單
				JSONArray committeeList =  new JSONArray();
				List<ViewMaintainInspectCommitteeMemberOrg> maintainInspectCommittees =
						maintainInspectCommitteeService.findByMaintainInspectIdAndGroupId(maintainInspectId, groupId);
				if(maintainInspectCommittees!=null && !maintainInspectCommittees.isEmpty()) {
					for(ViewMaintainInspectCommitteeMemberOrg maintainInspectCommittee : maintainInspectCommittees) {
						JSONObject committee = new JSONObject();
						committee.put("CommitteeMemberId", maintainInspectCommittee.getCommitteeId());
						committee.put("Name", maintainInspectCommittee.getCommitteeMemberName());
						committeeList.put(committee);
					}
				}
				sn_json.put("CommitteeList", committeeList);
				//取得範例稽核前訪談調查⽂件start
				MaintainPlanCheckListSampleAttach maintainPlanCheckListSampleAttach = maintainPlanCheckListSampleAttachService.get();
				if(maintainPlanCheckListSampleAttach!=null) {
					JSONObject maintainPlanCheckListSampleAttachJson = new JSONObject();
					maintainPlanCheckListSampleAttachJson.put("FileName", maintainPlanCheckListSampleAttach.getFileName());
					maintainPlanCheckListSampleAttachJson.put("FileSize", maintainPlanCheckListSampleAttach.getFileSize());
					maintainPlanCheckListSampleAttachJson.put("FileTime",
							WebDatetime.toString(maintainPlanCheckListSampleAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						
					JSONObject checkListSampleAttachLink = new JSONObject();
					checkListSampleAttachLink.put("Type", "CheckListSampleAttach");
					String checkListAttachLinkCode = Base64.getEncoder().encodeToString(checkListSampleAttachLink.toString().getBytes());
					maintainPlanCheckListSampleAttachJson.put("FileLink", checkListAttachLinkCode);
					sn_json.put("CheckListSampleAttach", maintainPlanCheckListSampleAttachJson);						
				}
					
				MaintainPlanQuestionnaireSampleAttach maintainPlanQuestionnaireSampleAttach = maintainPlanQuestionnaireSampleAttachService.get();
				if(maintainPlanQuestionnaireSampleAttach!=null) {
					JSONObject maintainPlanQuestionnaireSampleAttachJson = new JSONObject();
					maintainPlanQuestionnaireSampleAttachJson.put("FileName", maintainPlanQuestionnaireSampleAttach.getFileName());
					maintainPlanQuestionnaireSampleAttachJson.put("FileSize", maintainPlanQuestionnaireSampleAttach.getFileSize());
					maintainPlanQuestionnaireSampleAttachJson.put("FileTime",
							WebDatetime.toString(maintainPlanQuestionnaireSampleAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						
					JSONObject questionnaireSampleAttachLink = new JSONObject();
					questionnaireSampleAttachLink.put("Type", "QuestionnaireSampleAttach");
					String checkListAttachLinkCode = Base64.getEncoder().encodeToString(questionnaireSampleAttachLink.toString().getBytes());
					maintainPlanQuestionnaireSampleAttachJson.put("FileLink", checkListAttachLinkCode);
					sn_json.put("QuestionnaireSampleAttach", maintainPlanQuestionnaireSampleAttachJson);						
				}
					
				MaintainPlanSelfEvaluationSampleAttach maintainPlanSelfEvaluationSampleAttach = maintainPlanSelfEvaluationSampleAttachService.get();
				if(maintainPlanSelfEvaluationSampleAttach!=null) {
					JSONObject maintainPlanSelfEvaluationSampleAttachJson = new JSONObject();
					maintainPlanSelfEvaluationSampleAttachJson.put("FileName", maintainPlanSelfEvaluationSampleAttach.getFileName());
					maintainPlanSelfEvaluationSampleAttachJson.put("FileSize", maintainPlanSelfEvaluationSampleAttach.getFileSize());
					maintainPlanSelfEvaluationSampleAttachJson.put("FileTime",
							WebDatetime.toString(maintainPlanSelfEvaluationSampleAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						
					JSONObject selfEvaluationSampleAttachLink = new JSONObject();
					selfEvaluationSampleAttachLink.put("Type", "SelfEvaluationSampleAttach");
					String selfEvaluationSampleAttachLinkCode = Base64.getEncoder().encodeToString(selfEvaluationSampleAttachLink.toString().getBytes());
					maintainPlanSelfEvaluationSampleAttachJson.put("FileLink", selfEvaluationSampleAttachLinkCode);
					sn_json.put("SelfEvaluationSampleAttach", maintainPlanSelfEvaluationSampleAttachJson);						
				}
				
				//取得醫院上傳的稽核前訪談調查⽂件start
				if("5".equals(inspectStatus) || "6".equals(inspectStatus) ||
						"7".equals(inspectStatus) || "8".equals(inspectStatus) || "9".equals(inspectStatus)) {
					//醫院上傳：檢核表
					MaintainPlanCheckListAttach maintainPlanCheckListAttach =
							maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(maintainPlanCheckListAttach!=null) {
						JSONObject maintainPlanCheckListAttachJson = new JSONObject();
						maintainPlanCheckListAttachJson.put("FileName", maintainPlanCheckListAttach.getFileName());
						maintainPlanCheckListAttachJson.put("FileSize", maintainPlanCheckListAttach.getFileSize());
						maintainPlanCheckListAttachJson.put("FileDesc", maintainPlanCheckListAttach.getFileDesc());
						maintainPlanCheckListAttachJson.put("FileTime",
								WebDatetime.toString(maintainPlanCheckListAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						
						JSONObject checkListAttachLink = new JSONObject();
						checkListAttachLink.put("Type", "CheckListAttach");
						checkListAttachLink.put("MaintainInspectId", maintainInspectId);
						checkListAttachLink.put("GroupId", groupId);
						String checkListAttachLinkCode = Base64.getEncoder().encodeToString(checkListAttachLink.toString().getBytes());
						maintainPlanCheckListAttachJson.put("FileLink", checkListAttachLinkCode);
						sn_json.put("CheckListAttach", maintainPlanCheckListAttachJson);						
					}

					//醫院上傳：調查表
					MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach =
							maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(maintainPlanQuestionnaireAttach!=null) {
						JSONObject maintainPlanQuestionnaireAttachJson = new JSONObject();
						maintainPlanQuestionnaireAttachJson.put("FileName", maintainPlanQuestionnaireAttach.getFileName());
						maintainPlanQuestionnaireAttachJson.put("FileSize", maintainPlanQuestionnaireAttach.getFileSize());
						maintainPlanQuestionnaireAttachJson.put("FileDesc", maintainPlanQuestionnaireAttach.getFileDesc());
						maintainPlanQuestionnaireAttachJson.put("FileTime",
								WebDatetime.toString(maintainPlanQuestionnaireAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						
						JSONObject questionnaireAttachLink = new JSONObject();
						questionnaireAttachLink.put("Type", "QuestionnaireAttach");
						questionnaireAttachLink.put("MaintainInspectId", maintainInspectId);
						questionnaireAttachLink.put("GroupId", groupId);
						String questionnaireAttachLinkCode = Base64.getEncoder().encodeToString(questionnaireAttachLink.toString().getBytes());
						maintainPlanQuestionnaireAttachJson.put("FileLink", questionnaireAttachLinkCode);
						sn_json.put("QuestionnaireAttach", maintainPlanQuestionnaireAttachJson);
					}
					
					//醫院上傳：自評表
					MaintainPlanSelfEvaluationAttach maintainPlanSelfEvaluationAttach =
							maintainPlanSelfEvaluationAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(maintainPlanSelfEvaluationAttach!=null) {
						JSONObject maintainPlanSelfEvaluationAttachJson = new JSONObject();
						maintainPlanSelfEvaluationAttachJson.put("FileName", maintainPlanSelfEvaluationAttach.getFileName());
						maintainPlanSelfEvaluationAttachJson.put("FileSize", maintainPlanSelfEvaluationAttach.getFileSize());
						maintainPlanSelfEvaluationAttachJson.put("FileDesc", maintainPlanSelfEvaluationAttach.getFileDesc());
						maintainPlanSelfEvaluationAttachJson.put("FileTime",
								WebDatetime.toString(maintainPlanSelfEvaluationAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

						JSONObject selfEvaluationAttachLink = new JSONObject();
						selfEvaluationAttachLink.put("Type", "SelfEvaluationAttach");
						selfEvaluationAttachLink.put("MaintainInspectId", maintainInspectId);
						selfEvaluationAttachLink.put("GroupId", groupId);
						String selfEvaluationAttachLinkCode = Base64.getEncoder().encodeToString(selfEvaluationAttachLink.toString().getBytes());
						maintainPlanSelfEvaluationAttachJson.put("FileLink", selfEvaluationAttachLinkCode);
						sn_json.put("SelfEvaluationAttach", maintainPlanSelfEvaluationAttachJson);
					}

					//醫院上傳：其他
					List<MaintainPlanOtherAttach> maintainPlanOtherAttachs =
							maintainPlanOtherAttachService.getListByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(maintainPlanOtherAttachs!=null && !maintainPlanOtherAttachs.isEmpty()) {
						JSONArray maintainPlanOtherAttachArray = new JSONArray();

						for(MaintainPlanOtherAttach maintainPlanOtherAttach : maintainPlanOtherAttachs) {
							JSONObject maintainPlanOtherAttachJson = new JSONObject();
							maintainPlanOtherAttachJson.put("FileName", maintainPlanOtherAttach.getFileName());
							maintainPlanOtherAttachJson.put("FileSize", maintainPlanOtherAttach.getFileSize());
							maintainPlanOtherAttachJson.put("FileDesc", maintainPlanOtherAttach.getFileDesc());
							maintainPlanOtherAttachJson.put("FileTime",
									WebDatetime.toString(maintainPlanOtherAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

							JSONObject otherAttachLink = new JSONObject();
							otherAttachLink.put("Type", "OtherAttach");
							otherAttachLink.put("MaintainInspectId", maintainInspectId);
							otherAttachLink.put("GroupId", groupId);
							otherAttachLink.put("FileId", maintainPlanOtherAttach.getId());
							String otherAttachLinkCode = Base64.getEncoder().encodeToString(otherAttachLink.toString().getBytes());
							maintainPlanOtherAttachJson.put("FileLink", otherAttachLinkCode);
							
							maintainPlanOtherAttachArray.put(maintainPlanOtherAttachJson);
						}
						sn_json.put("OtherAttach", maintainPlanOtherAttachArray);
					}
				}
				//取得醫院上傳的稽核前訪談調查⽂件 end

				//取得稽核委員上傳的稽核評分表 start
				if("7".equals(inspectStatus) || "8".equals(inspectStatus) || "9".equals(inspectStatus)) {
					List<ViewMaintainInspectCommitteeMemberOrg> viewMaintainInspectCommitteeMemberOrgs = 
							maintainInspectCommitteeService.findByMaintainInspectIdAndGroupId(maintainInspectId, groupId);
					if(viewMaintainInspectCommitteeMemberOrgs!=null && !viewMaintainInspectCommitteeMemberOrgs.isEmpty()) {
						JSONArray maintainPlanScoreAttachArray = new JSONArray();
						JSONArray maintainPlanReviewOpinionAttachArray = new JSONArray();
						for(ViewMaintainInspectCommitteeMemberOrg viewMaintainInspectCommitteeMemberOrg : viewMaintainInspectCommitteeMemberOrgs) {
							JSONObject maintainPlanScoreAttachJson = new JSONObject();
							maintainPlanScoreAttachJson.put("FileName", viewMaintainInspectCommitteeMemberOrg.getFileName());
							maintainPlanScoreAttachJson.put("FileSize", viewMaintainInspectCommitteeMemberOrg.getFileSize());
							maintainPlanScoreAttachJson.put("FileReviewOpinionName", viewMaintainInspectCommitteeMemberOrg.getReviewOpinionFileName());
							maintainPlanScoreAttachJson.put("FileReviewOpinionSize", viewMaintainInspectCommitteeMemberOrg.getReviewOpinionFileSize());
							String fileDesc = viewMaintainInspectCommitteeMemberOrg.getFileDesc();
							if(fileDesc!=null && fileDesc.length()!=0) {
								maintainPlanScoreAttachJson.put("FileDesc", new JSONArray(fileDesc));
							}
							Long committeeId = viewMaintainInspectCommitteeMemberOrg.getCommitteeId();
							maintainPlanScoreAttachJson.put("CommitteeId", committeeId);
							maintainPlanScoreAttachJson.put("CommitteeName", viewMaintainInspectCommitteeMemberOrg.getCommitteeMemberName());
							JSONObject scoreAttachLink = new JSONObject();
							scoreAttachLink.put("Type", "ScoreAttach");
							scoreAttachLink.put("MaintainInspectId", maintainInspectId);
							scoreAttachLink.put("GroupId", groupId);
							scoreAttachLink.put("CommitteeId", committeeId);
							scoreAttachLink.put("FileId", viewMaintainInspectCommitteeMemberOrg.getFileId());
							String scoreAttachLinkCode = Base64.getEncoder().encodeToString(scoreAttachLink.toString().getBytes());
							maintainPlanScoreAttachJson.put("FileLink", scoreAttachLinkCode);
							
							JSONObject reviewOpinionAttachLink = new JSONObject();
							reviewOpinionAttachLink.put("Type", "ReviewOpinionAttach");
							reviewOpinionAttachLink.put("MaintainInspectId", maintainInspectId);
							reviewOpinionAttachLink.put("GroupId", groupId);
							reviewOpinionAttachLink.put("CommitteeId", committeeId);
							reviewOpinionAttachLink.put("FileId", viewMaintainInspectCommitteeMemberOrg.getReviewOpinionFileId());
							String reviewOpinionAttachLinkCode = Base64.getEncoder().encodeToString(reviewOpinionAttachLink.toString().getBytes());
							maintainPlanScoreAttachJson.put("FileReviewOpinionLink", reviewOpinionAttachLinkCode);				
							
							maintainPlanScoreAttachArray.put(maintainPlanScoreAttachJson);
							
						}
						sn_json.put("ScoreAttach", maintainPlanScoreAttachArray);
//						sn_json.put("ReviewOpinionAttach", maintainPlanReviewOpinionAttachArray);
					}
					

					
					
					
//					List<MaintainPlanScoreAttach> maintainPlanScoreAttachs =
//							maintainPlanScoreAttachService.getListByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
//					if(maintainPlanScoreAttachs!=null && !maintainPlanScoreAttachs.isEmpty()) {
//						JSONArray maintainPlanScoreAttachArray = new JSONArray();
//						for(MaintainPlanScoreAttach maintainPlanScoreAttach : maintainPlanScoreAttachs) {
//							JSONObject maintainPlanScoreAttachJson = new JSONObject();
//							maintainPlanScoreAttachJson.put("FileName", maintainPlanScoreAttach.getFileName());
//							maintainPlanScoreAttachJson.put("FileSize", maintainPlanScoreAttach.getFileSize());
//							String fileDesc = maintainPlanScoreAttach.getFileDesc();
//							if(fileDesc!=null && fileDesc.length()!=0) {
//								maintainPlanScoreAttachJson.put("FileDesc", new JSONArray(fileDesc));
//							}
//							maintainPlanScoreAttachJson.put("FileTime",
//									WebDatetime.toString(maintainPlanScoreAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
//
//							Long committeeId = maintainPlanScoreAttach.getCommitteeId();
//							maintainPlanScoreAttachJson.put("CommitteeId", committeeId);
//							if(committeeList!=null && committeeList.length()!=0) {
//								for(int i=0; i<committeeList.length(); i++) {
//									JSONObject committeeObj = committeeList.getJSONObject(i);
//									long committeeMemberId = committeeObj.isNull("CommitteeMemberId") == true ? 0 : committeeObj.getLong("CommitteeMemberId");
//									if(committeeMemberId==committeeId) {
//										String name = committeeObj.isNull("Name") == true ? null : committeeObj.getString("Name");
//										maintainPlanScoreAttachJson.put("CommitteeName", name);
//										break;
//									}
//								}
//							}
//
//							JSONObject scoreAttachLink = new JSONObject();
//							scoreAttachLink.put("Type", "ScoreAttach");
//							scoreAttachLink.put("MaintainInspectId", maintainInspectId);
//							scoreAttachLink.put("GroupId", groupId);
//							scoreAttachLink.put("CommitteeId", committeeId);
//							scoreAttachLink.put("FileId", maintainPlanScoreAttach.getId());
//							String scoreAttachLinkCode = Base64.getEncoder().encodeToString(scoreAttachLink.toString().getBytes());
//							maintainPlanScoreAttachJson.put("FileLink", scoreAttachLinkCode);
//							
//							maintainPlanScoreAttachArray.put(maintainPlanScoreAttachJson);
//						}
//						sn_json.put("ScoreAttach", maintainPlanScoreAttachArray);
//					}
				}
				//取得稽核委員上傳的稽核評分表 end

				//取得PMO上傳的稽核結果 start
				if("7".equals(inspectStatus) || "8".equals(inspectStatus) || "9".equals(inspectStatus)) {
					MaintainPlanResultAttach maintainPlanResultAttach =
							maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if (maintainPlanResultAttach != null) {
						JSONObject maintainPlanResultAttachJson = new JSONObject();
						maintainPlanResultAttachJson.put("FileId", maintainPlanResultAttach.getId());
						maintainPlanResultAttachJson.put("FileName", maintainPlanResultAttach.getFileName());
						maintainPlanResultAttachJson.put("FileSize", maintainPlanResultAttach.getFileSize());
						maintainPlanResultAttachJson.put("FileDesc", maintainPlanResultAttach.getFileDesc());
						maintainPlanResultAttachJson.put("FileTime",
								WebDatetime.toString(maintainPlanResultAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

						JSONObject resultAttachLink = new JSONObject();
						resultAttachLink.put("Type", "ResultAttach");
						resultAttachLink.put("MaintainInspectId", maintainInspectId);
						resultAttachLink.put("GroupId", groupId);
						String resultAttachLinkCode = Base64.getEncoder().encodeToString(resultAttachLink.toString().getBytes());
						maintainPlanResultAttachJson.put("FileLink", resultAttachLinkCode);
						sn_json.put("ResultAttach", maintainPlanResultAttachJson);
					}
				}
				//取得PMO上傳的稽核結果 end

				//取得醫院上傳的改善報告 start
				if("9".equals(inspectStatus)) {
					MaintainPlanImprovementAttach maintainPlanImprovementAttach =
							maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if (maintainPlanImprovementAttach != null) {
						JSONObject maintainPlanImprovementAttachJson = new JSONObject();
						maintainPlanImprovementAttachJson.put("FileId", maintainPlanImprovementAttach.getId());
						maintainPlanImprovementAttachJson.put("FileName", maintainPlanImprovementAttach.getFileName());
						maintainPlanImprovementAttachJson.put("FileSize", maintainPlanImprovementAttach.getFileSize());
						maintainPlanImprovementAttachJson.put("FileDesc", maintainPlanImprovementAttach.getFileDesc());
						maintainPlanImprovementAttachJson.put("FileTime",
								WebDatetime.toString(maintainPlanImprovementAttach.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

						JSONObject improvementAttachLink = new JSONObject();
						improvementAttachLink.put("Type", "ImprovementAttach");
						improvementAttachLink.put("MaintainInspectId", maintainInspectId);
						improvementAttachLink.put("GroupId", groupId);
						String improvementAttachLinkCode = Base64.getEncoder().encodeToString(improvementAttachLink.toString().getBytes());
						maintainPlanImprovementAttachJson.put("FileLink", improvementAttachLinkCode);
						sn_json.put("ImprovementAttach", maintainPlanImprovementAttachJson);
					}
				}
				//取得醫院上傳的改善報告 end

				//取得ProcessLog start
				List<ViewProcessLogMember> processLogs = processLogService.getByPostId(maintainInspectMember.getId()+"", "maintainInspectMember");
				if(processLogs!=null && !processLogs.isEmpty()) {
					JSONArray logArray = new JSONArray();
					for(ViewProcessLogMember processLog : processLogs) {
						JSONObject logJson = new JSONObject();
						logJson.put("PreStatus", processLog.getPreStatus());
						logJson.put("Status", processLog.getStatus());
						logJson.put("CreateName", processLog.getCreateName());
						logJson.put("PreName", processLog.getPreName());
						logJson.put("CreateTime",
								WebDatetime.toString(processLog.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
						logArray.put(logJson);
					}
					sn_json.put("ProcessLog", logArray);
				}
				//取得ProcessLog end
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(sn_json.toString());			
	}

	/**
	 * 取得警訊資料API
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
	@RequestMapping(value = "/k01/queryCommittees", method = RequestMethod.POST)
	public @ResponseBody String QueryCommittees(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {		
		JSONObject listjson = new JSONObject();

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<ViewMemberRoleMember> memberRoleMembers = memberRoleService.findByRoleId(20);
			
			JSONArray sn_array = new JSONArray();
			if(memberRoleMembers!=null && !memberRoleMembers.isEmpty()) {
				for(ViewMemberRoleMember memberRoleMember : memberRoleMembers) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("MemberId", memberRoleMember.getMemberId());
					sn_json.put("Name", memberRoleMember.getName());
					sn_array.put(sn_json);
				}
				listjson.put("committees", sn_array);
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(listjson.toString());			
	}

	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/updateHospital", method = RequestMethod.POST)
	public @ResponseBody String UpdateHospital(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
			else{
				//權限不是管理員無法將狀態3->4
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember =
							maintainInspectMemberService.updateMaintainInspectMember(getBaseMemberId(), json);
					if (maintainInspectMember != null) {
						if (isWriteProcessLog) {
							JSONObject processLog = new JSONObject();
							processLog.put("TableName", "maintainInspectMember");
							processLog.put("Opinion", "");
							processLog.put("PreStatus", "3");
							processLog.put("Status", "4");
							processLogService.insertInspect(getBaseMemberId(), processLog.toString(), maintainInspectMember.getId()+"");
						}
	
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	//稽核前訪談調查⽂件下載
	@RequestMapping(value = "/k01/downloadAttach/{link}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void DownloadAttach(Locale locale, HttpServletResponse response, @PathVariable(name="link") String link) {
		String json = new String(Base64.getDecoder().decode(WebCrypto.getSafe(link)));
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			System.out.println("obj="+obj);

			String type = obj.isNull("Type") == true ? null : obj.getString("Type");
			Long maintainInspectId = obj.isNull("MaintainInspectId") == true ? null : obj.getLong("MaintainInspectId");
			Long groupId = obj.isNull("GroupId") == true ? null : obj.getLong("GroupId");
			Long fileId = obj.isNull("FileId") == true ? null : obj.getLong("FileId");

			String fileName = null;
			byte[] fileContent = null; 
			if(type != null) {
				if("CheckListSampleAttach".equals(type)) {
					MaintainPlanCheckListSampleAttach checkListSampleAttach =
							maintainPlanCheckListSampleAttachService.get();
					if(checkListSampleAttach!=null) {
						fileName = checkListSampleAttach.getFileName();
						fileContent = checkListSampleAttach.getFileContent();
					}
				}
				
				if("QuestionnaireSampleAttach".equals(type)) {
					MaintainPlanQuestionnaireSampleAttach questionnaireSampleAttach =
							maintainPlanQuestionnaireSampleAttachService.get();
					if(questionnaireSampleAttach!=null) {
						fileName = questionnaireSampleAttach.getFileName();
						fileContent = questionnaireSampleAttach.getFileContent();
					}
				}
				
				if("SelfEvaluationSampleAttach".equals(type)) {
					MaintainPlanSelfEvaluationSampleAttach selfEvaluationSampleAttach =
							maintainPlanSelfEvaluationSampleAttachService.get();
					if(selfEvaluationSampleAttach!=null) {
						fileName = selfEvaluationSampleAttach.getFileName();
						fileContent = selfEvaluationSampleAttach.getFileContent();
					}
				} 
			}
			
			
			if(type!=null && maintainInspectId!=null && groupId!=null) {
				if("CheckListAttach".equals(type)) {
					MaintainPlanCheckListAttach checkListAttach =
							maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(checkListAttach!=null) {
						fileName = checkListAttach.getFileName();
						fileContent = checkListAttach.getFileContent();
					}
				} else if("QuestionnaireAttach".equals(type)) {
					MaintainPlanQuestionnaireAttach questionnaireAttach =
							maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(questionnaireAttach!=null) {
						fileName = questionnaireAttach.getFileName();
						fileContent = questionnaireAttach.getFileContent();
					}
				} else if("SelfEvaluationAttach".equals(type)) {
					MaintainPlanSelfEvaluationAttach selfEvaluationAttach =
							maintainPlanSelfEvaluationAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(selfEvaluationAttach!=null) {
						fileName = selfEvaluationAttach.getFileName();
						fileContent = selfEvaluationAttach.getFileContent();
					}
				}else if("OtherAttach".equals(type) && fileId!=null) {
					MaintainPlanOtherAttach otherAttach = maintainPlanOtherAttachService.getById(fileId);
					if(otherAttach!=null) {
						fileName = otherAttach.getFileName();
						fileContent = otherAttach.getFileContent();
					}
				} else if("ScoreAttach".equals(type)) {
					MaintainPlanScoreAttach scoreAttach = maintainPlanScoreAttachService.getById(fileId);
					if(scoreAttach!=null) {
						fileName = scoreAttach.getFileName();
						fileContent = scoreAttach.getFileContent();
					}
				} else if("ResultAttach".equals(type)) {
					MaintainPlanResultAttach resultAttach =
							maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(resultAttach!=null) {
						fileName = resultAttach.getFileName();
						fileContent = resultAttach.getFileContent();
					}
				} else if("ImprovementAttach".equals(type)) {
					MaintainPlanImprovementAttach improvementAttach =
							maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if(improvementAttach!=null) {
						fileName = improvementAttach.getFileName();
						fileContent = improvementAttach.getFileContent();
					}
				} else if("ReviewOpinionAttach".equals(type)) {
					MaintainPlanReviewOpinionAttach reviewOpinionAttach = maintainPlanReviewOpinionAttachService.getById(fileId);
					if(reviewOpinionAttach!=null) {
						fileName = reviewOpinionAttach.getFileName();
						fileContent = reviewOpinionAttach.getFileContent();
					}
				}
			}

			if(fileContent!=null && fileContent.length!=0) {
				try {
					String name = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=\""+name+"\"");

					OutputStream outputStream = response.getOutputStream();
					outputStream.write(fileContent);
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/committeeHospital", method = RequestMethod.POST)
	public @ResponseBody String CommitteeListHospital(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				//權限不是管理員無法將狀態5->6
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember =
							maintainInspectMemberService.updateMaintainInspectMember(getBaseMemberId(), json);
					if (maintainInspectMember != null) {
						Long maintainInspectId = maintainInspectMember.getMaintainInspectId();
						Long groupId = maintainInspectMember.getGroupId();
						JSONArray committeeList = obj.isNull("CommitteeList") == true ? null : obj.getJSONArray("CommitteeList");
						maintainInspectCommitteeService.deleteAndCreateCommittee(
								committeeList, maintainInspectId, groupId, getBaseMemberId());
						if (isWriteProcessLog) {
							if(committeeList!=null && committeeList.length()!=0) {
								JSONObject processLog = new JSONObject();
								processLog.put("TableName", "maintainInspectMember");
								processLog.put("Opinion", "");
								processLog.put("PreStatus", "5");
								processLog.put("Status", "6");
								processLogService.insertInspect(getBaseMemberId(), processLog.toString(), maintainInspectMember.getId()+"");
							}
						}
						
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}
				else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/toggleAllowHospitalPatch", method = RequestMethod.POST)
	public @ResponseBody String ToggleAllowHospitalPatch(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");

			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				MaintainInspectMember maintainInspectMember =
						maintainInspectMemberService.switchAllowHospitalPatchMaintainInspectMember(getBaseMemberId(), json);
				if (maintainInspectMember != null) {
					responseJson.put("Id", maintainInspectMember.getId());
					responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	@RequestMapping(value = "/k01/auditResultFileUpload", method = RequestMethod.POST)
	public @ResponseBody String AuditResultFileUpload(Locale locale, HttpServletRequest request,
			@RequestParam("json") String json,
			@RequestParam(value = "AuditResultFile", required = false) MultipartFile auditResultFile) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Long maintainInspectId = obj.isNull("MaintainInspectId") == true ? null : obj.getLong("MaintainInspectId");
				Long groupId = obj.isNull("GroupId") == true ? null : obj.getLong("GroupId");
				Long auditResultFileId = obj.isNull("AuditResultFileId") == true ? null : obj.getLong("AuditResultFileId");
				String auditResultDesc = obj.isNull("AuditResultDesc") == true ? null : obj.getString("AuditResultDesc");
				String inspectStatus = obj.isNull("InspectStatus") == true ? null : obj.getString("InspectStatus");

				MaintainPlanResultAttach insert = null;
 				try {
					//刪除舊檔案
					MaintainPlanResultAttach maintainPlanResultAttach =
							maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
					if (maintainPlanResultAttach != null && maintainPlanResultAttach.getId()!=null &&
							(auditResultFileId==null || maintainPlanResultAttach.getId().longValue()!=auditResultFileId.longValue()))	{
						maintainPlanResultAttachService.deleteByMaintainPlanIdAndGroupId(maintainInspectId, groupId);
						maintainPlanResultAttach=null;
					}
					//新增檔案
					if (auditResultFile!=null && !auditResultFile.isEmpty() && maintainPlanResultAttach==null) {
						JSONObject file = new JSONObject();
						file.put("MaintainPlanId", maintainInspectId); 
						file.put("FileDesc", auditResultDesc);							//檔案說明
						file.put("FileName", auditResultFile.getOriginalFilename());	//檔案名稱
						file.put("FileType", auditResultFile.getContentType());			//檔案類型
						file.put("FileSize", auditResultFile.getSize());				//檔案大小
						file.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, auditResultFile.toString())); // 檔案Hash
						byte[] bytes = auditResultFile.getBytes();						// 檔案 byte array
						insert = maintainPlanResultAttachService.insert(getBaseMemberId(), file.toString(), groupId, bytes);
					}
				} catch (Exception e) {
					e.printStackTrace();
					insert = null;
				}

 				if (insert != null || auditResultFileId != null) {
	 				MaintainInspectMember maintainInspectMember =
	 							maintainInspectMemberService.submitMaintainInspectResult(maintainInspectId, groupId, inspectStatus, getBaseMemberId());
	 				if(maintainInspectMember!=null) {
	 					if (isWriteProcessLog) {
		 					JSONObject processLog = new JSONObject();
		 					processLog.put("TableName", "maintainInspectMember");
		 					processLog.put("Opinion", "");
		 					processLog.put("PreStatus", "7");
		 					processLog.put("Status", "8");
		 					processLogService.insertInspect(getBaseMemberId(), processLog.toString(), maintainInspectMember.getId()+"");
	 					}
	 					responseJson.put("Id", maintainInspectMember.getId());
	 					responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
	 	 				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
	 	 				responseJson.put("success", true);
	 	 				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create,
	 	 							SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
	 				} else {
	 					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
	 	 				responseJson.put("success", false);
	 	 				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create,
	 	 				SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
	 				}
 				} else {
 					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
 					responseJson.put("success", false);
 					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create,
 							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
 				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	//==================================================//
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToImprovementUpload", method = RequestMethod.POST)
	public @ResponseBody String ReturnToImprovementUpload(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToImprovementUpload(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "9");
						processLog.put("Status", "8");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
						
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToAuditResultUpload", method = RequestMethod.POST)
	public @ResponseBody String ReturnToAuditResultUpload(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToAuditResultUpload(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "8");
						processLog.put("Status", "7");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
						
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToAuditScoreUpload", method = RequestMethod.POST)
	public @ResponseBody String ReturnToAuditScoreUpload(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToAuditScoreUpload(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "7");
						processLog.put("Status", "6");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
						
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToCommitteeList", method = RequestMethod.POST)
	public @ResponseBody String ReturnToCommitteeList(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToCommitteeList(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "6");
						processLog.put("Status", "5");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
						
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToHospital", method = RequestMethod.POST)
	public @ResponseBody String ReturnToHospital(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToHospital(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "5");
						processLog.put("Status", "4");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
	
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	
	
	
	
	
	
	
	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安研究計畫
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/k01/returnToEditData", method = RequestMethod.POST)
	public @ResponseBody String ReturnToEditData(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			if (!maintainInspectMemberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMaintainInspectMemberOrg viewMaintainInspectMemberOrg = maintainInspectMemberService.findMaintainInspectById(id);
				Long maintainInspectId = viewMaintainInspectMemberOrg.getMaintainInspectId();
				Long groupId = viewMaintainInspectMemberOrg.getGroupId();
				if(baseMemberRole.IsAdmin) {
					MaintainInspectMember maintainInspectMember = 
							maintainInspectMemberService.returnToEditData(maintainInspectId, groupId, getBaseMemberId());
					if (maintainInspectMember != null) {
						JSONObject processLog = new JSONObject();
						processLog.put("TableName", "maintainInspectMember");
						processLog.put("Opinion", "");
						processLog.put("PreStatus", "4");
						processLog.put("Status", "3");
						processLogService.insertInspect(getBaseMemberId(), processLog.toString(), id+"");
	
						responseJson.put("Id", maintainInspectMember.getId());
						responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
								SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
}