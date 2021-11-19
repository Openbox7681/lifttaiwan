package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import tw.gov.mohw.hisac.web.domain.MaintainInspectCommittee;
import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.MaintainPlanScoreAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanReviewOpinionAttach;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;
import tw.gov.mohw.hisac.web.service.MaintainInspectCommitteeService;
import tw.gov.mohw.hisac.web.service.MaintainInspectCommitteeUploadService;
import tw.gov.mohw.hisac.web.service.MaintainPlanReviewOpinionAttachService;
import tw.gov.mohw.hisac.web.service.MaintainInspectMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanScoreAttachService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 共同維護計畫控制器
 */
@Controller
@RequestMapping(value = "/kin/api", produces = "application/json; charset=utf-8")
public class k04_MaintainInspectCommitteeUploadController extends BaseController {
	@Autowired
	private MaintainInspectCommitteeUploadService maintainInspectCommitteeUploadService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MaintainPlanReviewOpinionAttachService maintainPlanReviewOpinionAttachService;
	@Autowired
	private MaintainInspectMemberService maintainInspectMemberService;
	@Autowired
	private MaintainInspectCommitteeService maintainInspectCommitteeService;
	@Autowired
	private MaintainPlanScoreAttachService maintainPlanScoreAttachService;

	private String targetControllerName = "kin";
	private String targetActionName = "k04";

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
	@RequestMapping(value = "/k04/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			Date todayDate = new Date();
			if(baseMemberRole.IsPmo) {
				List<ViewMaintainInspectCommitteeMemberOrg> maintainInspectAdminMembers = maintainInspectCommitteeUploadService.getAdminList(json);
				if(maintainInspectAdminMembers != null) {
					for (ViewMaintainInspectCommitteeMemberOrg maintainInspectMember : maintainInspectAdminMembers) {						
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", maintainInspectMember.getId());
						sn_json.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						sn_json.put("CommitteeId", maintainInspectMember.getCommitteeId());
						sn_json.put("CommitteeMemberName",maintainInspectMember.getCommitteeMemberName());
						sn_json.put("CommitteeUpload", true);
						sn_json.put("Year", maintainInspectMember.getYear());
						sn_json.put("Title", maintainInspectMember.getTitle());
						sn_json.put("Status", maintainInspectMember.isStatus());
						sn_json.put("InspectStatus", maintainInspectMember.getInspectStatus());
						sn_json.put("OrgName", maintainInspectMember.getOrgName());
						sn_json.put("FileId", maintainInspectMember.getFileId());
						sn_array.put(sn_json);
					}
					responseJson.put("total", maintainInspectCommitteeUploadService.getAdminListSize(json));
				}
			}
			System.out.println(baseMemberRole.IsMaintainInspectCommittee);
			if(baseMemberRole.IsMaintainInspectCommittee) {
				List<ViewMaintainInspectCommitteeMemberOrg> maintainInspectMembers = maintainInspectCommitteeUploadService.getList(getBaseMemberId(), json);
				if (maintainInspectMembers != null) {
					for (ViewMaintainInspectCommitteeMemberOrg maintainInspectMember : maintainInspectMembers) {						
						JSONObject sn_json = new JSONObject();
						String committeeSdate =  WebDatetime.toString(maintainInspectMember.getCommitteeUploadSdate(), "yyyy-MM-dd");
						String committeeEdate =  WebDatetime.toString(maintainInspectMember.getCommitteeUploadEdate(), "yyyy-MM-dd");
						if(maintainInspectMember.getCommitteeUploadSdate() != null) {
							if(todayDate.after(WebDatetime.parseSdate(committeeSdate)) && todayDate.before(WebDatetime.parseEdate(committeeEdate))) {
								sn_json.put("CommitteeUpload", true);
							}else {
								sn_json.put("CommitteeUpload", false);
							}
						}
						//如果委員沒有在期限內評分將看不到資料
						sn_json.put("Id", maintainInspectMember.getId());
						sn_json.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
						sn_json.put("CommitteeId", maintainInspectMember.getCommitteeId());
						sn_json.put("Year", maintainInspectMember.getYear());
						sn_json.put("Title", maintainInspectMember.getTitle());
						sn_json.put("Status", maintainInspectMember.isStatus());
						sn_json.put("InspectStatus", maintainInspectMember.getInspectStatus());
						sn_json.put("OrgName", maintainInspectMember.getOrgName());
						sn_json.put("FileId", maintainInspectMember.getFileId());					
						sn_array.put(sn_json);
					}
					responseJson.put("total", maintainInspectCommitteeUploadService.getListSize(getBaseMemberId(), json));
				}
			}
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
	@RequestMapping(value = "/k04/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {		
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		JSONObject responseJson = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			ViewMaintainInspectCommitteeMemberOrg maintainInspectMember = maintainInspectCommitteeUploadService.getById(id);
			//稽核評分表預設從網站設定撈資料
			JSONArray auditScore_array = new JSONArray();
			String auditScore = resourceMessageService.getMessageValue("auditScoreList");
			//取得檔案
			if(maintainInspectMember.getFileDesc() != null) {
				responseJson.put("AuditScore", new JSONArray(maintainInspectMember.getFileDesc()));
				responseJson.put("FileName", maintainInspectMember.getFileName());
				responseJson.put("FileAuditScoreId", maintainInspectMember.getFileId());	
			}else
			{
				List<String> items = Arrays.asList(auditScore.split(","));
				if (items != null) {
				for(int i = 0;i < items.size();i++) {
						JSONObject auditScore_json = new JSONObject();
						auditScore_json.put("AuditScoreName", items.get(i));
						auditScore_array.put(auditScore_json);
					}
					responseJson.put("AuditScore",auditScore_array);
				}
			}
			
			responseJson.put("FileReviewOpinionId", maintainInspectMember.getReviewOpinionFileId());
			responseJson.put("FileReviewOpinionName", maintainInspectMember.getReviewOpinionFileName());
			
			responseJson.put("Id", maintainInspectMember.getId());
			responseJson.put("CommitteeId",maintainInspectMember.getCommitteeId());
			responseJson.put("MaintainInspectId", maintainInspectMember.getMaintainInspectId());
			responseJson.put("Title", maintainInspectMember.getTitle());
			responseJson.put("Year", maintainInspectMember.getYear());
			responseJson.put("OrgId",maintainInspectMember.getGroupId());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/k04/auditScore_submit", method = RequestMethod.POST)
	public @ResponseBody String AuditScoreSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileAuditScore", required = false) MultipartFile fileAuditScore,
			@RequestParam(value = "fileReviewOpinion", required = false) MultipartFile fileReviewOpinion) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainInspectId") == true ? 0 : obj.getLong("MaintainInspectId");			
			long fileAuditScoreId = obj.isNull("FileAuditScoreId") == true ? 0 : obj.getLong("FileAuditScoreId");
			long fileReviewOpinionId = obj.isNull("FileReviewOpinionId") == true ? 0 : obj.getLong("FileReviewOpinionId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			Boolean check = obj.isNull("Check") == true ? null : obj.getBoolean("Check");
			long committeeId = 0;
			if(baseMemberRole.IsPmo) {
				committeeId = obj.isNull("CommitteeId") == true ? 0 : obj.getLong("CommitteeId");
			}
			JSONArray auditScoreLists = obj.optJSONArray("auditScoreList");
			MaintainPlanScoreAttach insert1 = new MaintainPlanScoreAttach();
			MaintainPlanReviewOpinionAttach insert2 = new MaintainPlanReviewOpinionAttach();
			try {	
				//刪除舊檔案
				MaintainPlanScoreAttach maintainPlanScoreAttach;
				MaintainPlanReviewOpinionAttach maintainPlanReviewOpinionAttach;
				//權限
				if(baseMemberRole.IsMaintainInspectCommittee) {
					maintainPlanScoreAttach = maintainPlanScoreAttachService.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId , getBaseMemberId());
					maintainPlanReviewOpinionAttach = maintainPlanReviewOpinionAttachService.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId , getBaseMemberId());
				}else {
					maintainPlanScoreAttach = maintainPlanScoreAttachService.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId , committeeId);
					maintainPlanReviewOpinionAttach = maintainPlanReviewOpinionAttachService.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId , committeeId);
				}
				
				if (maintainPlanScoreAttach != null && !maintainPlanScoreAttach.getId().equals(fileAuditScoreId)){
					//權限
					if(baseMemberRole.IsMaintainInspectCommittee) {
						maintainPlanScoreAttachService.deleteByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,getBaseMemberId());
					}
					else {
						maintainPlanScoreAttachService.deleteByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,committeeId);
					}
					maintainPlanScoreAttach=null;
				}
				
				if (maintainPlanReviewOpinionAttach != null && !maintainPlanReviewOpinionAttach.getId().equals(fileReviewOpinionId)){
					//權限
					if(baseMemberRole.IsMaintainInspectCommittee) {
						maintainPlanReviewOpinionAttachService.deleteByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,getBaseMemberId());
					}
					else {
						maintainPlanReviewOpinionAttachService.deleteByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,committeeId);
					}
					maintainPlanReviewOpinionAttach=null;
				}
				
				if(check) {
					MaintainInspectMember maintainInspectMember = maintainInspectMemberService.score_submit(maintainPlanId, groupId);
					if(baseMemberRole.IsMaintainInspectCommittee) {
						MaintainInspectCommittee maintainInspectCommittee = maintainInspectCommitteeService.score_submit_new(maintainPlanId, groupId, getBaseMemberId());
					}else {
						MaintainInspectCommittee maintainInspectCommittee = maintainInspectCommitteeService.score_submit_new(maintainPlanId, groupId,committeeId);
					}
					JSONObject maintainPlanMember_obj = new JSONObject();						
					maintainPlanMember_obj.put("PreStatus", "6");
					maintainPlanMember_obj.put("Status", "7");	
					maintainPlanMember_obj.put("TableName", "maintainInspectMember");								
					processLogService.insertInspect(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainInspectMember.getId().toString());											
				}
				
				
				//新增檔案
				if (fileAuditScore != null && !fileAuditScore.isEmpty() && maintainPlanScoreAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileAuditScore.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId);
					if(baseMemberRole.IsMaintainInspectCommittee)
						sn_json.put("CommitteeId", getBaseMemberId());
					else
						sn_json.put("CommitteeId", committeeId);
					sn_json.put("FileDesc", auditScoreLists.toString()); // 檔案說明
					sn_json.put("FileName", fileAuditScore.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileAuditScore.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileAuditScore.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileAuditScore.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanScoreAttachService.insert(getBaseMemberId(), json1, groupId, bytes);
				}
				else {
					if(baseMemberRole.IsMaintainInspectCommittee)
						maintainPlanScoreAttachService.updataByMaintainPlanIdAndGroupIdAndCommitteeId(getBaseMemberId(), auditScoreLists.toString(),maintainPlanId, groupId , getBaseMemberId());
					else
						maintainPlanScoreAttachService.updataByMaintainPlanIdAndGroupIdAndCommitteeId(getBaseMemberId(), auditScoreLists.toString(),maintainPlanId, groupId , committeeId);
				}
				
				//新增檔案
				if (fileReviewOpinion != null && !fileReviewOpinion.isEmpty() && maintainPlanReviewOpinionAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileReviewOpinion.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId);
					if(baseMemberRole.IsMaintainInspectCommittee)
						sn_json.put("CommitteeId", getBaseMemberId());
					else
						sn_json.put("CommitteeId", committeeId);
					sn_json.put("FileDesc", ""); // 檔案說明
					sn_json.put("FileName", fileReviewOpinion.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileReviewOpinion.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileReviewOpinion.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileReviewOpinion.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert2 = maintainPlanReviewOpinionAttachService.insert(getBaseMemberId(), json1, groupId, bytes);
				}
				else {
					if(baseMemberRole.IsMaintainInspectCommittee)
						maintainPlanReviewOpinionAttachService.updataByMaintainPlanIdAndGroupIdAndCommitteeId(getBaseMemberId(), auditScoreLists.toString(),maintainPlanId, groupId , getBaseMemberId());
					else
						maintainPlanReviewOpinionAttachService.updataByMaintainPlanIdAndGroupIdAndCommitteeId(getBaseMemberId(), auditScoreLists.toString(),maintainPlanId, groupId , committeeId);
				}
				
			}catch (Exception e) {
					System.out.println(e);
					insert1 = null;
					insert2 = null;	
			}			
			
			if (insert1 != null || insert2 != null) {										
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if(fileAuditScoreId != 0) {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				}
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
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
	@RequestMapping(value = "/k04/query/button/count", method = RequestMethod.POST)
	public String QueryMessage(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			obj.put("Status", false);
			long num6;
			if(baseMemberRole.IsMaintainInspectCommittee)
				num6 = maintainInspectCommitteeUploadService.getListSize(getBaseMemberId(), obj.toString());
			else
				num6 = maintainInspectCommitteeUploadService.getAdminListSize(obj.toString());
			listjson.put("Status6Count", num6);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
}