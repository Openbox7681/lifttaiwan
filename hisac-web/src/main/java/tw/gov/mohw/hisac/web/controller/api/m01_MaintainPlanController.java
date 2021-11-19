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
import tw.gov.mohw.hisac.web.service.MaintainPlanAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanContentService;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupOrgService;
import tw.gov.mohw.hisac.web.service.MaintainPlanGroupService;
import tw.gov.mohw.hisac.web.service.MaintainPlanItemService;
import tw.gov.mohw.hisac.web.service.MaintainPlanMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanResultAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanScoreAttachService;
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
@RequestMapping(value = "/mtp/api", produces = "application/json; charset=utf-8")
public class m01_MaintainPlanController extends BaseController {

	@Autowired
	private OrgService orgService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MaintainPlanService maintainPlanService;
	@Autowired
	private MaintainPlanMemberService maintainPlanMemberService;
	@Autowired
	private MaintainPlanItemService maintainPlanItemService;
	@Autowired
	private MaintainPlanAttachService maintainPlanAttachService;
	@Autowired
	private MaintainPlanContentService maintainPlanContentService;	
	@Autowired
	private MaintainPlanGroupService maintainPlanGroupService;
	@Autowired
	private MaintainPlanGroupOrgService maintainPlanGroupOrgService;	
	@Autowired
	private MaintainPlanQuestionnaireAttachService maintainPlanQuestionnaireAttachService;	
	@Autowired
	private MaintainPlanCheckListAttachService maintainPlanCheckListAttachService;	
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
	private MaintainPlanSuggestAttachService maintainPlanSuggestAttachService;
	@Autowired
	private MaintainPlanSuggestReadService maintainPlanSuggestReadService;
	@Autowired
	private MaintainPlanImplementAttachService maintainPlanImplementAttachService;
	


	private String targetControllerName = "mtp";
	private String targetActionName = "m01";

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
	@RequestMapping(value = "/m01/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			if (baseMemberRole.IsAdmin || baseMemberRole.IsHisacBoss) {				
				List<MaintainPlan> maintainPlans = maintainPlanService.getList(json);
				if (maintainPlans != null)
					for (MaintainPlan maintainPlan : maintainPlans) {						
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", maintainPlan.getId());
						sn_json.put("Year", maintainPlan.getYear());
						sn_json.put("Title", maintainPlan.getTitle());
						sn_json.put("Status", maintainPlan.getStatus());
						JSONArray member_array = new JSONArray();
						if (!maintainPlan.getStatus().equals("1")) {
							List<ViewMaintainPlanMemberOrg> maintainPlanMembers = maintainPlanMemberService.getListByMaintainPlanId(maintainPlan.getId());
							MaintainPlanImprovementSuggestAttach maintainPlanImprovementSuggestAttach = maintainPlanSuggestAttachService.getByMaintainPlanId(maintainPlan.getId());
							Long attachId = (long) 0;
							if (maintainPlanImprovementSuggestAttach!=null)
								attachId = maintainPlanImprovementSuggestAttach.getId();

							if (maintainPlanMembers != null)
								for (ViewMaintainPlanMemberOrg maintainPlanMember: maintainPlanMembers) {
									JSONObject member_json = new JSONObject();											
									member_json.put("GroupId", maintainPlanMember.getGroupId());
									member_json.put("Status", maintainPlanMember.getStatus());
									member_json.put("Name", maintainPlanMember.getOrgName());								
									member_json.put("ReplyTime", WebDatetime.toString(maintainPlanMember.getReplyTime()));
									MaintainPlanImprovementSuggestRead maintainPlanImprovementSuggestRead = maintainPlanSuggestReadService.getByMaintainPlanIdAndOrgIdAndAttachId(maintainPlan.getId(), maintainPlanMember.getGroupId(), attachId);
									if(maintainPlanImprovementSuggestRead != null) {
										member_json.put("IsRead", maintainPlanImprovementSuggestRead.getIsRead());
										member_json.put("ReadTime", WebDatetime.toString(maintainPlanImprovementSuggestRead.getModifyTime()));
										}		
									else
										member_json.put("IsRead", false);

									member_array.put(member_json);
								}										
						}
						sn_json.put("IsSuggestAttachExist", maintainPlanSuggestAttachService.isMaintainPlanImprovementSuggestAttachExist(maintainPlan.getId()));
						sn_json.put("Members", member_array);
						sn_json.put("CreateTime",WebDatetime.toString(maintainPlan.getCreateTime()));
						sn_array.put(sn_json);
					}
				listjson.put("total", maintainPlanService.getListSize(json));
				listjson.put("datatable", sn_array);				
			}
			else if (baseMemberRole.IsMemberContact || baseMemberRole.IsMemberAdmin) {	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateWithoutTime = new Date();
				try {
					 dateWithoutTime = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				List<ViewMaintainPlanMemberOrg> maintainPlanMembers = maintainPlanMemberService.getList(getBaseOrgId(), json);
				if (maintainPlanMembers != null)
					for (ViewMaintainPlanMemberOrg maintainPlanMember : maintainPlanMembers) {						
						JSONObject sn_json = new JSONObject();
						Long maintainPlanId = maintainPlanMember.getMaintainPlanId();
						sn_json.put("Id", maintainPlanMember.getMaintainPlanId());
						sn_json.put("Year", maintainPlanMember.getYear());
						sn_json.put("Title", maintainPlanMember.getTitle());
						sn_json.put("Status", maintainPlanMember.getStatus());	
						sn_json.put("MemberId", maintainPlanMember.getMemberId());	
						sn_json.put("MemberName", maintainPlanMember.getMemberName());	
						sn_json.put("CreateTime",WebDatetime.toString(maintainPlanService.getById(maintainPlanId).getCreateTime()));						
						
						

						sn_json.put("IsSuggestAttachExist", maintainPlanSuggestAttachService.isMaintainPlanImprovementSuggestAttachExist(maintainPlanMember.getMaintainPlanId()));
						if (maintainPlanMember.getSdate().compareTo(dateWithoutTime)<=0 && maintainPlanMember.getEdate().compareTo(dateWithoutTime)>=0)
							sn_json.put("IsReply", true);
						else
							sn_json.put("IsReply", false);
						sn_array.put(sn_json);
					}
				listjson.put("total", maintainPlanMemberService.getListSize(getBaseOrgId(), json));
				listjson.put("datatable", sn_array);	
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		}
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";			
	}

	/**
	 * 刪除警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/m01/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			if (!maintainPlanService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (maintainPlanService.delete(id)) {
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
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 清空此填寫者API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否清空此填寫者成功
	 */
	@RequestMapping(value = "/m01/clearMember", method = RequestMethod.POST)
	public @ResponseBody String ClearMember(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.clearMember(id, groupId);										
			if (maintainPlanMember != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}						
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	

	/**
	 * 退回API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否退回成功
	 */
	@RequestMapping(value = "/m01/returnMember", method = RequestMethod.POST)
	public @ResponseBody String ReturnMember(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlan maintainPlan = maintainPlanService.getById(id);
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.returnMember(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();						
			maintainPlanMember_obj.put("PreStatus", "3");
			maintainPlanMember_obj.put("Status", "2");	
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());
			if (maintainPlan.getStatus().equals("3")) {
				maintainPlan = maintainPlanService.statusChange(id ,"2");		
				JSONObject maintainPlan_obj = new JSONObject();						
				maintainPlan_obj.put("PreStatus", "3");
				maintainPlan_obj.put("Status", "2");	
				maintainPlan_obj.put("TableName", "maintainPlan");								
				processLogService.insert(getBaseMemberId(), maintainPlan_obj.toString(), maintainPlan.getId().toString());
			}
			if (maintainPlanMember != null && maintainPlan != null) {					
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}						
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	/**
	 * 退回實施情形上傳API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否啟動成功
	 */
	
	@RequestMapping(value = "/m01/returnImplement", method = RequestMethod.POST)
	public @ResponseBody String ReturnImplement(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.returnImplement(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();						
			maintainPlanMember_obj.put("PreStatus", "32");
			maintainPlanMember_obj.put("Status", "31");	
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());						
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	/**
	 * 退回稽核API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否啟動成功
	 */
	@RequestMapping(value = "/m01/returnAudit", method = RequestMethod.POST)
	public @ResponseBody String ReturnAudit(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.returnAudit(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();						
			maintainPlanMember_obj.put("PreStatus", "6");
			maintainPlanMember_obj.put("Status", "5");	
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());						
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 退回改善報告API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否啟動成功
	 */
	@RequestMapping(value = "/m01/returnImprovement", method = RequestMethod.POST)
	public @ResponseBody String ReturnImprovement(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.returnImprovement(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();						
			maintainPlanMember_obj.put("PreStatus", "9");
			maintainPlanMember_obj.put("Status", "8");	
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());						
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 啟動資安維護計畫實施情形繳交API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否啟動成功
	 */
	
	@RequestMapping(value = "/m01/startImplement", method = RequestMethod.POST)
	public @ResponseBody String StartImplement(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);		
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.startImplement(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();									
			maintainPlanMember_obj.put("PreStatus", "3");
			maintainPlanMember_obj.put("Status", "31");
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");	
			
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			

			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());							
		}
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	/**
	 * 啟動資安維護計畫實施情形繳交API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否啟動成功
	 */
	
	@RequestMapping(value = "/m01/startImplementFromAudit", method = RequestMethod.POST)
	public @ResponseBody String StartImplementFromAudit(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);		
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.startImplement(id, groupId);
			JSONObject maintainPlanMember_obj = new JSONObject();									
			maintainPlanMember_obj.put("PreStatus", "9");
			maintainPlanMember_obj.put("Status", "31");
			maintainPlanMember_obj.put("TableName", "maintainPlanMember");	
			
			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			

			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());							
		}
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
		
		
	}

	
	
	
	/**
	 * 啟動稽核API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件 
	 * @return 是否啟動成功
	 * 
	 * TODO 之後需要修改狀態 109.10.15 資安維護計畫實施情形開發修改部分
	 */
//	@RequestMapping(value = "/m01/startAudit", method = RequestMethod.POST)
//	public @ResponseBody String StartAudit(Locale locale, HttpServletRequest request, @RequestBody String json) {
//
//		JSONObject responseJson = new JSONObject();
//		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
//		if (token == null || token.getToken().equals(""))
//			return responseJson.toString();
//
//		json = WebCrypto.getSafe(json);
//		JSONObject obj = new JSONObject(json);
//		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
//			MaintainPlanMember maintainPlanMember = maintainPlanMemberService.startAudit(id, groupId);
//			JSONObject maintainPlanMember_obj = new JSONObject();						
//			maintainPlanMember_obj.put("PreStatus", "32");
//			maintainPlanMember_obj.put("Status", "5");	
//			maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
//			processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());			
//			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
//			responseJson.put("success", true);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());						
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return WebCrypto.getSafe(responseJson.toString());
//	}

	/**
	 * 取得Member資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Member資料
	 * @return Member資料
	 */
	@RequestMapping(value = "/m01/query/member", method = RequestMethod.POST)
	public String Getmember(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<Org> members = orgService.getByOrgType("3");
		if (members != null)
			for (Org member : members) {
				if (member.getIsEnable()) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", member.getId());
					sn_json.put("Name", member.getName());
					sn_array.put(sn_json);
				}
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得Group資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Group資料
	 * @return Group資料
	 */
	@RequestMapping(value = "/m01/query/group", method = RequestMethod.POST)
	public String Getgroup(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<MaintainPlanGroup> maintainPlanGroups = maintainPlanGroupService.getAll();
		if (maintainPlanGroups != null)
			for (MaintainPlanGroup maintainPlanGroup : maintainPlanGroups) {
				if (maintainPlanGroup.getIsEnable()) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", maintainPlanGroup.getId());
					sn_json.put("Name", maintainPlanGroup.getName());
					sn_array.put(sn_json);
				}
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得org資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            org資料
	 * @return org資料
	 */
	@RequestMapping(value = "/m01/query/org", method = RequestMethod.POST)
	public String Getorg(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<Org> orgs = orgService.getByOrgType("2");
		if (orgs != null)
			for (Org org : orgs) {
				if (org.getIsEnable()) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", org.getId());
					sn_json.put("Name", org.getName());
					sn_json.put("Action", false);
					sn_array.put(sn_json);
				}
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
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
	@RequestMapping(value = "/m01/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");	
		
		JSONObject sn_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlan maintainPlan = maintainPlanService.getById(id);	
			sn_json.put("Year", maintainPlan.getYear());
			sn_json.put("Title", maintainPlan.getTitle());
			sn_json.put("Sdate", WebDatetime.toString(maintainPlan.getSdate()).split(" ")[0]);
			sn_json.put("Edate", WebDatetime.toString(maintainPlan.getEdate()).split(" ")[0]);
			if (maintainPlan.getStatus().equals("1"))
				sn_json.put("Items", new JSONArray(maintainPlan.getItem()));
			else {
				JSONArray item_array = new JSONArray();	
				//log
				JSONArray log_array = new JSONArray();
				List<ViewProcessLogMember> maintainPlan_processLogs = processLogService.getByPostId(String.valueOf(maintainPlan.getId()), "maintainPlan");				
				MaintainPlanMember maintainPlanMember = maintainPlanMemberService.getListByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
				List<ViewProcessLogMember> maintainPlanMember_processLogs = null;
				if (maintainPlanMember != null)
					maintainPlanMember_processLogs = processLogService.getByPostId(String.valueOf(maintainPlanMember.getId()), "maintainPlanMember");
				if (maintainPlan_processLogs != null && groupId ==(long)0) {
					for (ViewProcessLogMember processLog : maintainPlan_processLogs) {
						JSONObject log_json = new JSONObject();
						log_json.put("PreStatus", processLog.getPreStatus());
						log_json.put("Status", processLog.getStatus());
						log_json.put("CreateTime", WebDatetime.toString(processLog.getCreateTime()));							
						log_json.put("CreateName", processLog.getCreateName());
						log_json.put("PreName", processLog.getPreName());		
						log_array.put(log_json);
					}
				}
				else if (maintainPlanMember_processLogs != null && groupId !=(long)0) {										
					for (ViewProcessLogMember processLog : maintainPlanMember_processLogs) {
						if (!(processLog.getStatus().equals("7") && (baseMemberRole.IsMemberContact || baseMemberRole.IsMemberAdmin))) {
							JSONObject log_json = new JSONObject();
							if (processLog.getPreStatus().equals("7") && (baseMemberRole.IsMemberContact || baseMemberRole.IsMemberAdmin))
								log_json.put("PreStatus", "71");
							else
								log_json.put("PreStatus", processLog.getPreStatus());
							log_json.put("Status", processLog.getStatus());
							log_json.put("CreateTime", WebDatetime.toString(processLog.getCreateTime()));							
							log_json.put("CreateName", processLog.getCreateName());
							log_json.put("PreName", processLog.getPreName());						
							log_array.put(log_json);
						}
					}																													
				}
				sn_json.put("Log", log_array);
				
				//109.10.13 新增共同改善建議事項檔案與狀態查詢
				
				MaintainPlanImprovementSuggestAttach maintainPlanImprovementSuggestAttach = maintainPlanSuggestAttachService.getByMaintainPlanId(maintainPlan.getId());
				if(maintainPlanImprovementSuggestAttach != null) {
					if(groupId !=(long)0 && maintainPlanMember!=null && maintainPlanMember.getMemberId() != null) {
						MaintainPlanImprovementSuggestRead maintainPlanImprovementSuggestRead = 
								maintainPlanSuggestReadService.getByMaintainPlanIdAndOrgIdAndAttachId
								(maintainPlan.getId(), groupId, maintainPlanImprovementSuggestAttach.getId());
						sn_json.put("ImprovementSuggestIsRead", maintainPlanImprovementSuggestRead.getIsRead());
					}else {
						MaintainPlanImprovementSuggestRead maintainPlanImprovementSuggestRead = maintainPlanSuggestReadService.getByMaintainPlanId(maintainPlan.getId()).get(0);
						sn_json.put("ImprovementSuggestIsRead", maintainPlanImprovementSuggestRead.getIsRead());
					}
					sn_json.put("FileImprovementSuggestName", maintainPlanImprovementSuggestAttach.getFileName());
					sn_json.put("FileImprovementSuggestId", maintainPlanImprovementSuggestAttach.getId());	
					sn_json.put("ImprovementSuggestAbstract", maintainPlanImprovementSuggestAttach.getFileDesc());
				}
				
				
				if (groupId !=(long)0 && maintainPlanMember!=null && maintainPlanMember.getMemberId() != null) {
					List<ViewMaintainPlanItemContent> maintainPlanItems = maintainPlanItemService.getListByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					//file
					MaintainPlanAttach maintainPlanAttach = maintainPlanAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanAttach != null) {
						sn_json.put("FileName", maintainPlanAttach.getFileName());
						sn_json.put("FileId", maintainPlanAttach.getId());												
					}
					
					MaintainPlanImplementAttach maintainPlanImplementAttach = maintainPlanImplementAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanImplementAttach !=null) {
						sn_json.put("FileImplementName", maintainPlanImplementAttach.getFileName());
						sn_json.put("FileImplementId", maintainPlanImplementAttach.getId());
					}
					MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach = maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanQuestionnaireAttach != null) {
						sn_json.put("FileQuestionnaireName", maintainPlanQuestionnaireAttach.getFileName());
						sn_json.put("FileQuestionnaireId", maintainPlanQuestionnaireAttach.getId());												
					}
					MaintainPlanCheckListAttach maintainPlanCheckListAttach = maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanCheckListAttach != null) {
						sn_json.put("FileCheckListName", maintainPlanCheckListAttach.getFileName());
						sn_json.put("FileCheckListId", maintainPlanCheckListAttach.getId());												
					}
					MaintainPlanScoreAttach maintainPlanScoreAttach = maintainPlanScoreAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanScoreAttach != null) {
						sn_json.put("FileAuditScoreName", maintainPlanScoreAttach.getFileName());
						sn_json.put("FileAuditScoreId", maintainPlanScoreAttach.getId());								
						sn_json.put("auditScoreList", new JSONArray(maintainPlanScoreAttach.getFileDesc()));
					}
					MaintainPlanResultAttach maintainPlanResultAttach = maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanResultAttach != null) {
						sn_json.put("FileAuditResultName", maintainPlanResultAttach.getFileName());
						sn_json.put("FileAuditResultId", maintainPlanResultAttach.getId());		
						sn_json.put("AuditResultAbstract", maintainPlanResultAttach.getFileDesc());
					}
					MaintainPlanImprovementAttach maintainPlanImprovementAttach = maintainPlanImprovementAttachService.getByMaintainPlanIdAndGroupId(maintainPlan.getId(), groupId);
					if (maintainPlanImprovementAttach != null) {
						sn_json.put("FileImprovementName", maintainPlanImprovementAttach.getFileName());
						sn_json.put("FileImprovementId", maintainPlanImprovementAttach.getId());	
						sn_json.put("ImprovementAbstract", maintainPlanImprovementAttach.getFileDesc());
					}
					
					//items
					if (maintainPlanItems != null)
					for (ViewMaintainPlanItemContent maintainPlanItem : maintainPlanItems) {
						JSONObject item_json = new JSONObject();
						item_json.put("Id", maintainPlanItem.getId());
						item_json.put("MaintainPlanItemId", maintainPlanItem.getMaintainPlanItemId());
						item_json.put("MaintainPlanId", maintainPlanItem.getMaintainPlanId());						
						item_json.put("ItemType", maintainPlanItem.getFormat());
						item_json.put("ItemName", maintainPlanItem.getTitle());		
						item_json.put("ItemDesc", maintainPlanItem.getDes());
						if (maintainPlanItem.getFormat().equals("string")) {
							item_json.put("InputType", "text");
							item_json.put("Content", maintainPlanItem.getContent());
						}
						else if (maintainPlanItem.getFormat().equals("int")) {
							item_json.put("InputType", "number");
							if (maintainPlanItem.getContent() != null)
								item_json.put("Content", Integer.parseInt(maintainPlanItem.getContent()));
						}
						item_array.put(item_json);
					}
				}
				else {
					List<ViewMaintainPlanItemContentFilter> maintainPlanItems = maintainPlanItemService.getListByMaintainPlanId(maintainPlan.getId());									
					if (maintainPlanItems != null)
					for (ViewMaintainPlanItemContentFilter maintainPlanItem : maintainPlanItems) {
						JSONObject item_json = new JSONObject();
						item_json.put("Id", maintainPlanItem.getId());
						item_json.put("MaintainPlanItemId", maintainPlanItem.getMaintainPlanItemId());
						item_json.put("MaintainPlanId", maintainPlanItem.getMaintainPlanId());						
						item_json.put("ItemType", maintainPlanItem.getFormat());
						item_json.put("ItemName", maintainPlanItem.getTitle());		
						item_json.put("ItemDesc", maintainPlanItem.getDes());
						if (maintainPlanItem.getFormat().equals("string")) {
							item_json.put("InputType", "text");							
						}
						else if (maintainPlanItem.getFormat().equals("int")) {
							item_json.put("InputType", "number");							
						}
						item_array.put(item_json);
					}
				}
				sn_json.put("Items", item_array);
			}
			JSONObject members = new JSONObject(maintainPlan.getMember());
			sn_json.put("Orgs", members.optJSONArray("orgs"));
			sn_json.put("Levels", members.optJSONArray("levels"));
			sn_json.put("Citys", members.optJSONArray("citys"));
			sn_json.put("IsOrgPubs", members.optJSONArray("isOrgPubs"));
			sn_json.put("MemberList", members.optJSONArray("memberList"));
			sn_json.put("GroupList", members.optJSONArray("groupList"));
								
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_json.toString());
		return "msg";
	}

	/**
	 * 新增資安維護計畫資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            資安維護計畫
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/m01/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			JSONObject members = obj.optJSONObject("Member");	
			JSONArray items = obj.optJSONArray("Item");
			MaintainPlan maintainPlan = maintainPlanService.insert(getBaseMemberId(), json);			

			if (isWriteProcessLog) {				
				JSONObject objMaintainPlanMember = new JSONObject();				
				JSONArray memberLists = members.optJSONArray("memberList");
				JSONArray groupLists = members.optJSONArray("groupList");
				ArrayList<Long> groupids = new ArrayList<Long>();
				for (int m=0; m< memberLists.length(); m++) {
					JSONObject memberList = memberLists.getJSONObject(m);
					if (!groupids.contains(memberList.getLong("MemberId"))) {
						objMaintainPlanMember.put("MaintainPlanId", maintainPlan.getId());
						objMaintainPlanMember.put("Sdate", WebDatetime.toString(maintainPlan.getSdate()));
						objMaintainPlanMember.put("Edate", WebDatetime.toString(maintainPlan.getEdate()));
						objMaintainPlanMember.put("Groupid", memberList.getLong("MemberId"));
						groupids.add(memberList.getLong("MemberId"));
						objMaintainPlanMember.put("Status", maintainPlan.getStatus());
						MaintainPlanMember maintainPlanMember = maintainPlanMemberService.insert(objMaintainPlanMember.toString());
						JSONObject maintainPlanMember_obj = new JSONObject();						
						maintainPlanMember_obj.put("PreStatus", "1");
						maintainPlanMember_obj.put("Status", "2");	
						maintainPlanMember_obj.put("TableName", "maintainPlanMember");
						processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());
					}
				}					
				for (int g=0; g< groupLists.length(); g++) {
					JSONObject groupList = groupLists.getJSONObject(g);
					objMaintainPlanMember.put("MaintainPlanId", maintainPlan.getId());
					objMaintainPlanMember.put("Sdate", WebDatetime.toString(maintainPlan.getSdate()));
					objMaintainPlanMember.put("Edate", WebDatetime.toString(maintainPlan.getEdate()));						
					objMaintainPlanMember.put("Status", maintainPlan.getStatus());
					List<MaintainPlanGroupOrg> maintainPlanGroupOrgs = maintainPlanGroupOrgService.getByMaintainPlanGroupId(groupList.getLong("GroupId"));
					if (maintainPlanGroupOrgs != null)
					for (MaintainPlanGroupOrg maintainPlanGroupOrg: maintainPlanGroupOrgs) {
						if (!groupids.contains(maintainPlanGroupOrg.getOrgId())) {
							objMaintainPlanMember.put("Groupid", maintainPlanGroupOrg.getOrgId());
							MaintainPlanMember maintainPlanMember = maintainPlanMemberService.insert(objMaintainPlanMember.toString());							}
						}
					}
				//調查對象條件				
				for (int j=0; j < items.length(); j++) {
					JSONObject item = items.getJSONObject(j);
					JSONArray subItems = item.optJSONArray("ItemSubItem");
					item.put("MaintainPlanId", maintainPlan.getId());
					MaintainPlanItem maintainPlanItem = maintainPlanItemService.insert(item.toString());	
					for (int s=0; s< subItems.length(); s++) {
						JSONObject subItem = subItems.getJSONObject(s);
						subItem.put("MaintainPlanId", maintainPlan.getId());
						subItem.put("ParentId", maintainPlanItem.getId());
						MaintainPlanItem maintainPlanSubItem = maintainPlanItemService.insert(subItem.toString());	
					}
				}
				processLogService.insert(getBaseMemberId(), json, maintainPlan.getId().toString());
			}							

			if (maintainPlan != null) {
				responseJson.put("Id", maintainPlan.getId());				
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, baseIpAddress, getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	/**
	 * 更新共同維護計畫讀取狀態API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            讀取狀態
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/m01/updateMaintainPlanSuggest", method = RequestMethod.POST)
	public @ResponseBody String UpdateMaintainPlanSuggestIsRead(Locale locale, HttpServletRequest request, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		Long orgId = obj.isNull("GroupId") == true ? null : obj.getLong("GroupId");
		Long maintainPlanId =  obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
		
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!maintainPlanService.isExist(maintainPlanId)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
			else {
				MaintainPlanImprovementSuggestAttach maintainPlanImprovementSuggestAttach = maintainPlanSuggestAttachService.getByMaintainPlanId(maintainPlanId);
				Long attachId = maintainPlanImprovementSuggestAttach.getId();
				
				MaintainPlanImprovementSuggestRead maintainPlanImprovementSuggestRead =  maintainPlanSuggestReadService.getByMaintainPlanIdAndOrgIdAndAttachId(maintainPlanId, orgId, attachId);

				if (maintainPlanImprovementSuggestRead!= null && !maintainPlanImprovementSuggestRead.getIsRead()) {
					maintainPlanSuggestReadService.setReadTrue(maintainPlanImprovementSuggestRead);
					
				}
				
				if (maintainPlanImprovementSuggestRead != null) {
					responseJson.put("MaintainPlanId", maintainPlanImprovementSuggestRead.getMaintainPlanId());				
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}

			}
		}
		 else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			}
		return WebCrypto.getSafe(responseJson.toString());
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
	@RequestMapping(value = "/m01/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
		Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
		JSONObject members = obj.optJSONObject("Member");	
		JSONArray items = obj.optJSONArray("Item");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!maintainPlanService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				MaintainPlan maintainPlan = maintainPlanService.update(getBaseMemberId(), json);				
				if (isWriteProcessLog) {				
					JSONObject objMaintainPlanMember = new JSONObject();				
					JSONArray memberLists = members.optJSONArray("memberList");
					JSONArray groupLists = members.optJSONArray("groupList");
					ArrayList<Long> groupids = new ArrayList<Long>();
					for (int m=0; m< memberLists.length(); m++) {						
						JSONObject memberList = memberLists.getJSONObject(m);
						if (!groupids.contains(memberList.getLong("MemberId"))) {
							objMaintainPlanMember.put("MaintainPlanId", maintainPlan.getId());
							objMaintainPlanMember.put("Sdate", WebDatetime.toString(maintainPlan.getSdate()));
							objMaintainPlanMember.put("Edate", WebDatetime.toString(maintainPlan.getEdate()));
							objMaintainPlanMember.put("Groupid", memberList.getLong("MemberId"));	
							groupids.add(memberList.getLong("MemberId"));
							objMaintainPlanMember.put("Status", maintainPlan.getStatus());
							MaintainPlanMember maintainPlanMember = maintainPlanMemberService.insert(objMaintainPlanMember.toString());
						}
					}					
					for (int g=0; g< groupLists.length(); g++) {
						JSONObject groupList = groupLists.getJSONObject(g);
						objMaintainPlanMember.put("MaintainPlanId", maintainPlan.getId());
						objMaintainPlanMember.put("Sdate", WebDatetime.toString(maintainPlan.getSdate()));
						objMaintainPlanMember.put("Edate", WebDatetime.toString(maintainPlan.getEdate()));						
						objMaintainPlanMember.put("Status", maintainPlan.getStatus());
						List<MaintainPlanGroupOrg> maintainPlanGroupOrgs = maintainPlanGroupOrgService.getByMaintainPlanGroupId(groupList.getLong("GroupId"));
						if (maintainPlanGroupOrgs != null)
						for (MaintainPlanGroupOrg maintainPlanGroupOrg: maintainPlanGroupOrgs) {
							if (!groupids.contains(maintainPlanGroupOrg.getOrgId())) {
								objMaintainPlanMember.put("Groupid", maintainPlanGroupOrg.getOrgId());
								MaintainPlanMember maintainPlanMember = maintainPlanMemberService.insert(objMaintainPlanMember.toString());
								JSONObject maintainPlanMember_obj = new JSONObject();						
								maintainPlanMember_obj.put("PreStatus", "1");
								maintainPlanMember_obj.put("Status", "2");	
								maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
								processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());
								}
							}
						}
					//調查對象條件				
					for (int j=0; j < items.length(); j++) {
						JSONObject item = items.getJSONObject(j);
						JSONArray subItems = item.optJSONArray("ItemSubItem");
						item.put("MaintainPlanId", maintainPlan.getId());
						MaintainPlanItem maintainPlanItem = maintainPlanItemService.insert(item.toString());	
						for (int s=0; s< subItems.length(); s++) {
							JSONObject subItem = subItems.getJSONObject(s);
							subItem.put("MaintainPlanId", maintainPlan.getId());
							subItem.put("ParentId", maintainPlanItem.getId());
							MaintainPlanItem maintainPlanSubItem = maintainPlanItemService.insert(subItem.toString());	
						}
					}
					processLogService.insert(getBaseMemberId(), json, maintainPlan.getId().toString());
				}	

				if (maintainPlan != null) {
					responseJson.put("Id", maintainPlan.getId());				
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
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	
	
	@RequestMapping(value = "/m01/reply", method = RequestMethod.POST)
	public @ResponseBody String Reply(Locale locale, HttpServletRequest request, @RequestParam("json") String json, @RequestParam(value = "file1", required = false) MultipartFile file1,			
			@RequestParam("FileDesc1") String fileDesc1) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray items = obj.optJSONArray("Item");
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");
			boolean check = obj.isNull("Check") == true ? false : obj.getBoolean("Check");
			long fileId = obj.isNull("FileId") == true ? 0 : obj.getLong("FileId");
			MaintainPlanAttach insert = new MaintainPlanAttach();
			try {
				//刪除舊content
				maintainPlanContentService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());				
				//刪除舊檔案
				MaintainPlanAttach maintainPlanAttach = maintainPlanAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanAttach != null && !maintainPlanAttach.getId().equals(fileId))	{			
					maintainPlanAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanAttach=null;
				}
				//新增content
				for (int i=0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);					
					long maintainPlanItemId = item.isNull("MaintainPlanItemId") == true ? 0 : item.getLong("MaintainPlanItemId");
					String type = item.isNull("ItemType") == true ? "title" : item.getString("ItemType");					
					if (maintainPlanItemId !=0) {
						JSONObject content_json = new JSONObject();
						if (type.equals("int")) {
							int content_int = item.isNull("Content") == true ? 0 : item.getInt("Content");
							content_json.put("Content",	String.valueOf(content_int));
						}
						else {
							String content_str = item.isNull("Content") == true ? "" : item.getString("Content");
							content_json.put("Content", content_str);
						}						
						content_json.put("MaintainPlanId", maintainPlanId);
						content_json.put("MaintainPlanItemId", maintainPlanItemId);
						content_json.put("GroupId", getBaseOrgId());						
						maintainPlanContentService.insert(content_json.toString(), getBaseMemberId());						
					}
				}				
				if (check) {
					MaintainPlanMember maintainPlanMember = maintainPlanMemberService.reply(maintainPlanId, getBaseOrgId(), getBaseMemberId());
					JSONObject maintainPlanMember_obj = new JSONObject();						
					maintainPlanMember_obj.put("PreStatus", "2");
					maintainPlanMember_obj.put("Status", "3");	
					maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
					processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());
					List<ViewMaintainPlanMemberOrg> viewMaintainPlanMemberOrgs = maintainPlanMemberService.getListByMaintainPlanId(maintainPlanId);
					boolean isStatusChange = true;
					if (viewMaintainPlanMemberOrgs != null)
						for (ViewMaintainPlanMemberOrg viewMaintainPlanMemberOrg : viewMaintainPlanMemberOrgs) {
							if (viewMaintainPlanMemberOrg.getStatus().equals("2")) {
								isStatusChange = false;
								break;
							}
						}
					if (isStatusChange) {
						maintainPlanService.statusChange(maintainPlanId, "3");
						JSONObject process = new JSONObject();
						process.put("PreStatus", "2");
						process.put("TableName", "maintainPlan");
						process.put("Status", "3");				
						processLogService.insert(getBaseMemberId(), process.toString(), String.valueOf(maintainPlanId));
					}
				}
				else
					maintainPlanMemberService.replySave(maintainPlanId, getBaseOrgId(), getBaseMemberId());
				
				//新增檔案
				if (file1 != null && !file1.isEmpty() && maintainPlanAttach==null) {
					// 檔案 byte array
					byte[] bytes1 = file1.getBytes();
					JSONObject sn_json1 = new JSONObject();
					sn_json1.put("MaintainPlanId", maintainPlanId); 
					sn_json1.put("FileDesc", fileDesc1); // 檔案說明
					sn_json1.put("FileName", file1.getOriginalFilename()); // 檔案名稱
					sn_json1.put("FileType", file1.getContentType()); // 檔案類型
					sn_json1.put("FileSize", file1.getSize()); // 檔案大小
					sn_json1.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes1.toString())); // 檔案Hash
					String json1 = sn_json1.toString();

					// 新增
					insert = maintainPlanAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes1);
					}
				}catch (Exception e) {				
					insert = null;
			}			
			
			if (insert != null) {						
				responseJson.put("MaintainPlanId", insert.getId());
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
	
	//使用者 繳交 資安維護計畫實施情形 狀態 status 31 =>32
	
	
	@RequestMapping(value = "/m01/implement_submit", method = RequestMethod.POST)
	public @ResponseBody String ImplementSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileImplement", required = false) MultipartFile fileImplement,
			@RequestParam("FileImplementDesc") String fileImplementDesc) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
	
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {	
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
			long FileImplementId = obj.isNull("FileImplementId") == true ? 0 : obj.getLong("FileImplementId");
			MaintainPlanImplementAttach insert1 = new MaintainPlanImplementAttach();
			try {
				//刪除舊檔案
				
				MaintainPlanImplementAttach maintainPlanImplementAttach = maintainPlanImplementAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanImplementAttach != null && !maintainPlanImplementAttach.getId().equals(FileImplementId))	{			
					maintainPlanImplementAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanImplementAttach=null;
				}
				
				//新增檔案
				if (fileImplement != null && !fileImplement.isEmpty() && maintainPlanImplementAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileImplement.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileImplementDesc); // 檔案說明
					sn_json.put("FileName", fileImplement.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileImplement.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileImplement.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileImplement.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanImplementAttachService.insert(getBaseMemberId(), json1, getBaseOrgId(), bytes);
					}
				
				
				if (insert1 != null) {
					//更新狀態為已繳交資安維護計畫實施情形
					MaintainPlanMember maintainPlanMember = maintainPlanMemberService.implement_submit(maintainPlanId, getBaseOrgId(), getBaseMemberId());
					JSONObject maintainPlanMember_obj = new JSONObject();						
					maintainPlanMember_obj.put("PreStatus", "31");
					maintainPlanMember_obj.put("Status", "32");	
					maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
					processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());											
					
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				}		
				 else {
						responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
			}catch (Exception e) {				
				insert1 = null;
			}		
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
		
	}
	
	
	
	
	
	//進行稽核前訪談調查 => 已繳交稽核前訪談調查
	
	@RequestMapping(value = "/m01/questionnaire_submit", method = RequestMethod.POST)
	public @ResponseBody String QuestionnaireSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileQuestionnaire", required = false) MultipartFile fileQuestionnaire,			
			@RequestParam("FileQuestionnaireDesc") String fileQuestionnaireDesc,
			@RequestParam(value = "fileCheckList", required = false) MultipartFile fileCheckList,			
			@RequestParam("FileCheckListDesc") String fileCheckListDesc) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
			long fileQuestionnaireId = obj.isNull("FileQuestionnaireId") == true ? 0 : obj.getLong("FileQuestionnaireId");
			long fileCheckListId = obj.isNull("FileCheckListId") == true ? 0 : obj.getLong("FileCheckListId");
			MaintainPlanQuestionnaireAttach insert1 = new MaintainPlanQuestionnaireAttach();
			MaintainPlanCheckListAttach insert2 = new MaintainPlanCheckListAttach();
			try {	
				//刪除舊檔案
				MaintainPlanQuestionnaireAttach maintainPlanQuestionnaireAttach = maintainPlanQuestionnaireAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanQuestionnaireAttach != null && !maintainPlanQuestionnaireAttach.getId().equals(fileQuestionnaireId))	{			
					maintainPlanQuestionnaireAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanQuestionnaireAttach=null;
				}
				MaintainPlanCheckListAttach maintainPlanCheckListAttach = maintainPlanCheckListAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
				if (maintainPlanCheckListAttach != null && !maintainPlanCheckListAttach.getId().equals(fileCheckListId))	{			
					maintainPlanCheckListAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, getBaseOrgId());
					maintainPlanQuestionnaireAttach=null;
				}
				MaintainPlanMember maintainPlanMember = maintainPlanMemberService.questionnaire_submit(maintainPlanId, getBaseOrgId(), getBaseMemberId());
				JSONObject maintainPlanMember_obj = new JSONObject();						
				maintainPlanMember_obj.put("PreStatus", "5");
				maintainPlanMember_obj.put("Status", "6");	
				maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
				processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());											
				
				//新增檔案
				if (fileQuestionnaire != null && !fileQuestionnaire.isEmpty() && maintainPlanQuestionnaireAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileQuestionnaire.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileQuestionnaireDesc); // 檔案說明
					sn_json.put("FileName", fileQuestionnaire.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileQuestionnaire.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileQuestionnaire.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileQuestionnaire.toString())); // 檔案Hash
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
				}catch (Exception e) {				
					insert1 = null;
					insert2 = null;
			}			
			
			if (insert1 != null && insert2 != null) {										
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
	
	@RequestMapping(value = "/m01/auditScore_submit", method = RequestMethod.POST)
	public @ResponseBody String AuditScoreSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileAuditScore", required = false) MultipartFile fileAuditScore,			
			@RequestParam("FileAuditScoreDesc") String fileAuditScoreDesc) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
			long fileAuditScoreId = obj.isNull("FileAuditScoreId") == true ? 0 : obj.getLong("FileAuditScoreId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			JSONArray auditScoreLists = obj.optJSONArray("auditScoreList");
			MaintainPlanScoreAttach insert1 = new MaintainPlanScoreAttach();
			try {	
				//刪除舊檔案
				MaintainPlanScoreAttach maintainPlanScoreAttach = maintainPlanScoreAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
				if (maintainPlanScoreAttach != null && !maintainPlanScoreAttach.getId().equals(fileAuditScoreId))	{			
					maintainPlanScoreAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
					maintainPlanScoreAttach=null;
				}
				MaintainPlanMember maintainPlanMember = maintainPlanMemberService.score_submit(maintainPlanId, groupId);
				JSONObject maintainPlanMember_obj = new JSONObject();						
				maintainPlanMember_obj.put("PreStatus", "6");
				maintainPlanMember_obj.put("Status", "7");	
				maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
				processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());											
				
				//新增檔案
				if (fileAuditScore != null && !fileAuditScore.isEmpty() && maintainPlanScoreAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileAuditScore.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", auditScoreLists.toString()); // 檔案說明
					sn_json.put("FileName", fileAuditScore.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileAuditScore.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileAuditScore.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileAuditScore.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanScoreAttachService.insert(getBaseMemberId(), json1, groupId, bytes);
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
	
	@RequestMapping(value = "/m01/auditResult_submit", method = RequestMethod.POST)
	public @ResponseBody String AuditResultSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileAuditResult", required = false) MultipartFile fileAuditResult,			
			@RequestParam("FileAuditResultDesc") String fileAuditResultDesc) {		

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
			long fileAuditResultId = obj.isNull("FileAuditResultId") == true ? 0 : obj.getLong("FileAuditResultId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			MaintainPlanResultAttach insert1 = new MaintainPlanResultAttach();
			try {	
				//刪除舊檔案
				MaintainPlanResultAttach maintainPlanResultAttach = maintainPlanResultAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
				if (maintainPlanResultAttach != null && !maintainPlanResultAttach.getId().equals(fileAuditResultId))	{			
					maintainPlanResultAttachService.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
					maintainPlanResultAttach=null;
				}
				MaintainPlanMember maintainPlanMember = maintainPlanMemberService.result_submit(maintainPlanId, groupId);
				JSONObject maintainPlanMember_obj = new JSONObject();						
				maintainPlanMember_obj.put("PreStatus", "7");
				maintainPlanMember_obj.put("Status", "8");	
				maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
				processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());											
				
				//新增檔案
				if (fileAuditResult != null && !fileAuditResult.isEmpty() && maintainPlanResultAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileAuditResult.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileAuditResultDesc); // 檔案說明
					sn_json.put("FileName", fileAuditResult.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileAuditResult.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileAuditResult.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileAuditResult.toString())); // 檔案Hash
					String json1 = sn_json.toString();

					// 新增
					insert1 = maintainPlanResultAttachService.insert(getBaseMemberId(), json1, groupId, bytes);
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
	
	
	@RequestMapping(value = "/m01/improvement_submit", method = RequestMethod.POST)
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
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
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
				MaintainPlanMember maintainPlanMember = maintainPlanMemberService.improvement_submit(maintainPlanId, groupId, getBaseMemberId());
				JSONObject maintainPlanMember_obj = new JSONObject();						
				maintainPlanMember_obj.put("PreStatus", "8");
				maintainPlanMember_obj.put("Status", "9");	
				maintainPlanMember_obj.put("TableName", "maintainPlanMember");								
				processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainPlanMember.getId().toString());											
				
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
	
	/**
	 * 共同改善建議事項檔案上傳
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            檔案上傳json
	 * @param fileImprovementSuggest
	 * 			  共同改善建議事項檔案 
	 * 
	 * @param FileImprovementSuggestDesc
	 * 			  共同改善建議事項檔案描述
	 *            
	 * @param response
	 *            HttpServletResponse
	
	 * 109.10.13 共同改善建議事項
	 */
	
	
	@RequestMapping(value = "/m01/improvementSuggest_submit", method = RequestMethod.POST)
	public @ResponseBody String ImprovementSuggestSubmit(Locale locale, HttpServletRequest request, @RequestParam("json") String json, 
			@RequestParam(value = "fileImprovementSuggest", required = false) MultipartFile fileImprovementSuggest,			
			@RequestParam("FileImprovementSuggestDesc") String fileImprovementSuggestDesc) {
		
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");			
			long fileImprovementSuggestId = obj.isNull("FileImprovementSuggestId") == true ? 0 : obj.getLong("FileImprovementSuggestId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			
			MaintainPlanImprovementSuggestAttach insert1 = new MaintainPlanImprovementSuggestAttach();
			try {
				
				//刪除舊檔案
				MaintainPlanImprovementSuggestAttach maintainPlanImprovementSuggestAttach = maintainPlanSuggestAttachService.getByMaintainPlanId(maintainPlanId);
				if (maintainPlanImprovementSuggestAttach != null && !maintainPlanImprovementSuggestAttach.getId().equals(fileImprovementSuggestId))	{	
					maintainPlanSuggestAttachService.deleteByMaintainPlanId(maintainPlanId);
					maintainPlanImprovementSuggestAttach=null;
				}
				
				//新增檔案
				if (fileImprovementSuggest != null && !fileImprovementSuggest.isEmpty() && maintainPlanImprovementSuggestAttach==null) {
					// 檔案 byte array
					byte[] bytes = fileImprovementSuggest.getBytes();
					JSONObject sn_json = new JSONObject();
					
					sn_json.put("MaintainPlanId", maintainPlanId); 
					sn_json.put("FileDesc", fileImprovementSuggestDesc); // 檔案說明
					sn_json.put("FileName", fileImprovementSuggest.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileImprovementSuggest.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileImprovementSuggest.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileImprovementSuggest.toString())); // 檔案Hash
					
					String json1 = sn_json.toString();

					// 新增檔案
					insert1 = maintainPlanSuggestAttachService.insert(getBaseMemberId(), json1, bytes);
					
					// 針對多個groupId 新增檔案狀態
					List<ViewMaintainPlanMemberOrg> maintainPlanMembers = maintainPlanMemberService.getListByMaintainPlanId(maintainPlanId);
					
					if (maintainPlanMembers != null) {
						for (ViewMaintainPlanMemberOrg maintainPlanMember: maintainPlanMembers) {
							JSONObject sn_json1 = new JSONObject();
							sn_json1.put("MaintainPlanId", maintainPlanId); 
							sn_json1.put("AttachId", insert1.getId());
							sn_json1.put("IsRead", false);
							sn_json1.put("OrgId", maintainPlanMember.getGroupId());	
							String json2 = sn_json1.toString();
							maintainPlanSuggestReadService.insert(getBaseMemberId(), json2);
						}	
					}
				
				}
						
			}catch (Exception e) {
				// e.printStackTrace();
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
		}else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/m01/attach/download/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanAttach maintainPlanAttach = maintainPlanAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanAttach == null) {
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
					byte[] buffer = maintainPlanAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanAttach.getFileName()));
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
	 * 附件下載 MaintainPlanImplement
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
	@RequestMapping(value = "/m01/attach/download/implement/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadImplementAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanImplementAttach maintainPlanImplementAttach = maintainPlanImplementAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanImplementAttach == null) {
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
					byte[] buffer = maintainPlanImplementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanImplementAttach.getFileName()));
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

			
			
		}
		else {
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
	@RequestMapping(value = "/m01/attach/download/questionnaire/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/m01/attach/download/checkList/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
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
	 * 共同改善建議事項下載
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
	@RequestMapping(value = "/m01/attach/download/improvementSuggest/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadImprovementSuggestAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {		
			MaintainPlanImprovementSuggestAttach maintainPlanImprovementSuggestAttach = maintainPlanSuggestAttachService.getByMaintainPlanId(maintainPlanId);
			
			
			
			if (maintainPlanImprovementSuggestAttach == null) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					// ex.printStackTrace();
				}
			}else {
				MaintainPlanImprovementSuggestRead maintainPlanImprovementSuggestRead =  maintainPlanSuggestReadService.getByMaintainPlanIdAndOrgIdAndAttachId(WebCrypto.getSafe(maintainPlanId),WebCrypto.getSafe(groupId) , maintainPlanImprovementSuggestAttach.getId());
				if (maintainPlanImprovementSuggestRead!= null && !maintainPlanImprovementSuggestRead.getIsRead()) {
					maintainPlanSuggestReadService.setReadTrue(maintainPlanImprovementSuggestRead);
					
				}
				response.reset();	
				try {
					byte[] buffer = maintainPlanImprovementSuggestAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanImprovementSuggestAttach.getFileName()));
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
	 * 附件下載 score
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
	@RequestMapping(value = "/m01/attach/download/auditScore/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
	public void DownloadAuditScoreAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long maintainPlanId, @PathVariable Long groupId) {
		JSONObject response_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanScoreAttach maintainPlanScoreAttach = maintainPlanScoreAttachService.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			if (maintainPlanScoreAttach == null) {
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
					byte[] buffer = maintainPlanScoreAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanScoreAttach.getFileName()));
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
	@RequestMapping(value = "/m01/attach/download/auditResult/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
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
	
	
	/**
	 * 附件下載 improvement
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
	@RequestMapping(value = "/m01/attach/download/improvement/{maintainPlanId}/{groupId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/m01/download/CheckList", method = RequestMethod.GET)
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
	@RequestMapping(value = "/m01/download/Questionnaire", method = RequestMethod.GET)
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
	@RequestMapping(value = "/m01/query/button/count", method = RequestMethod.POST)
	public String QueryMessage(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			obj.put("Status", "2");
			long num2 = maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status2Count", num2);
			obj.put("Status", "31");
			long num31 = maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status31Count", num31);
			obj.put("Status", "5");
			long num5 = maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status5Count", num5);
			obj.put("Status", "8");
			long num8 = maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			listjson.put("Status8Count", num8);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}

}