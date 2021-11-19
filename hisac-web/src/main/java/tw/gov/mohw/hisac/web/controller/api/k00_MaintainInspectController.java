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
import tw.gov.mohw.hisac.web.domain.MaintainInspect;
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
import tw.gov.mohw.hisac.web.service.MaintainInspectMemberService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanResultAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanScoreAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanImprovementAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSuggestAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSuggestReadService;
import tw.gov.mohw.hisac.web.service.MaintainPlanImplementAttachService;
import tw.gov.mohw.hisac.web.service.MaintainInspectService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 共同維護計畫控制器
 */
@Controller
@RequestMapping(value = "/kin/api", produces = "application/json; charset=utf-8")
public class k00_MaintainInspectController extends BaseController {

	@Autowired
	private OrgService orgService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MaintainInspectService maintainInspectService;
	@Autowired
	private MaintainInspectMemberService maintainInspectMemberService;
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
	private MaintainPlanSuggestAttachService maintainPlanSuggestAttachService;
	@Autowired
	private MaintainPlanSuggestReadService maintainPlanSuggestReadService;
	@Autowired
	private MaintainPlanImplementAttachService maintainPlanImplementAttachService;
	


	private String targetControllerName = "kin";
	private String targetActionName = "k00";

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
	@RequestMapping(value = "/k00/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			List<MaintainInspect> maintainInspects = maintainInspectService.getList(json);
			if (maintainInspects != null)
			for (MaintainInspect maintainPlan : maintainInspects) {						
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", maintainPlan.getId());
				sn_json.put("Year", maintainPlan.getYear());
				sn_json.put("Title", maintainPlan.getTitle());
				sn_json.put("Status", maintainPlan.getStatus());
				sn_json.put("CreateTime",WebDatetime.toString(maintainPlan.getCreateTime()));
				sn_array.put(sn_json);
			}
			listjson.put("total", maintainInspectService.getListSize(json));
			listjson.put("datatable", sn_array);				
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		}
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
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
	@RequestMapping(value = "/k00/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {		
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");	
		
		JSONObject sn_json = new JSONObject();		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainInspect maintainInspect = maintainInspectService.getById(id);
			sn_json.put("Id", maintainInspect.getId());
			sn_json.put("Year", maintainInspect.getYear());
			sn_json.put("Title", maintainInspect.getTitle());
			sn_json.put("Sdate", WebDatetime.toString(maintainInspect.getSdate()).split(" ")[0]);
			sn_json.put("Edate", WebDatetime.toString(maintainInspect.getEdate()).split(" ")[0]);
			JSONObject members = new JSONObject(maintainInspect.getMember());
			sn_json.put("MemberList", members.optJSONArray("memberList"));
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
	@RequestMapping(value = "/k00/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			JSONObject members = obj.optJSONObject("Member");	
			MaintainInspect maintainInspect = maintainInspectService.insert(getBaseMemberId(), json);			

			if (isWriteProcessLog) {				
				JSONObject objMaintainInspectMember = new JSONObject();				
				JSONArray memberLists = members.optJSONArray("memberList");
				ArrayList<Long> groupids = new ArrayList<Long>();
				for (int m=0; m< memberLists.length(); m++) {
					JSONObject memberList = memberLists.getJSONObject(m);
					if (!groupids.contains(memberList.getLong("MemberId"))) {
						System.out.println(maintainInspect.getId());
						objMaintainInspectMember.put("MaintainInspectId", maintainInspect.getId());
						objMaintainInspectMember.put("Groupid", memberList.getLong("MemberId"));
						groupids.add(memberList.getLong("MemberId"));
						objMaintainInspectMember.put("InspectStatus", maintainInspect.getStatus());
						MaintainInspectMember maintainInspectMember = maintainInspectMemberService.insert(objMaintainInspectMember.toString());
						JSONObject maintainPlanMember_obj = new JSONObject();						
						maintainPlanMember_obj.put("PreStatus", "0");
						maintainPlanMember_obj.put("Status", "1");	
						maintainPlanMember_obj.put("TableName", "maintainPlanMemberInspect");
						processLogService.insert(getBaseMemberId(), maintainPlanMember_obj.toString(), maintainInspectMember.getId().toString());
					}
				}
			}							

			if (maintainInspect != null) {
				responseJson.put("Id", maintainInspect.getId());				
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
		return responseJson.toString();
	}
	@RequestMapping(value = "/k00/query/member", method = RequestMethod.POST)
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
	@RequestMapping(value = "/k00/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
		Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
		JSONObject members = obj.optJSONObject("Member");	
		JSONArray items = obj.optJSONArray("Item");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!maintainInspectService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				MaintainInspect maintainInspect = maintainInspectService.update(getBaseMemberId(), json);				
				if (isWriteProcessLog) {				
					JSONObject objMaintainInspectMember = new JSONObject();				
					JSONArray memberLists = members.optJSONArray("memberList");
					JSONArray groupLists = members.optJSONArray("groupList");
					ArrayList<Long> groupids = new ArrayList<Long>();
					for (int m=0; m< memberLists.length(); m++) {						
						JSONObject memberList = memberLists.getJSONObject(m);
						if (!groupids.contains(memberList.getLong("MemberId"))) {
							objMaintainInspectMember.put("MaintainInspectId", maintainInspect.getId());
							objMaintainInspectMember.put("Sdate", WebDatetime.toString(maintainInspect.getSdate()));
							objMaintainInspectMember.put("Edate", WebDatetime.toString(maintainInspect.getEdate()));
							objMaintainInspectMember.put("Groupid", memberList.getLong("MemberId"));	
							groupids.add(memberList.getLong("MemberId"));
							objMaintainInspectMember.put("InspectStatus", maintainInspect.getStatus());
							MaintainInspectMember maintainInspectMember = maintainInspectMemberService.insert(objMaintainInspectMember.toString());
						}
					}
					processLogService.insert(getBaseMemberId(), json, maintainInspect.getId().toString());
				}	

				if (maintainInspect != null) {
					responseJson.put("Id", maintainInspect.getId());				
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
		return responseJson.toString();
	}
}