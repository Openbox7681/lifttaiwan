package tw.gov.mohw.hisac.web.controller.api;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MemberHistory;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ViewMemberSignApply;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.MemberRoleService;
import tw.gov.mohw.hisac.web.service.MemberSignApplyService;
import tw.gov.mohw.hisac.web.service.OrgService;


/**
 * 會員審查控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s15_MemberSignApplyController extends BaseController {

	@Autowired
	private MemberSignApplyService memberSignApplyService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private MailService mailService;

	@Autowired
	private MemberRoleService memberRoleService;
	
	

	private String targetControllerName = "sys";
	private String targetActionName = "s15";

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
	 *            查詢條件
	 * @return Member資料
	 */
	@RequestMapping(value = "/s15/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<ViewMemberSignApply> members = memberSignApplyService.getList(json);
			if (members != null) {
				for (ViewMemberSignApply member : members) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", member.getId());
					sn_json.put("OrgName", member.getOrgName());
					sn_json.put("Account", member.getAccount());
					sn_json.put("Name", member.getName());
					sn_json.put("Email", member.getEmail());
					sn_json.put("MobilePhone", member.getMobilePhone());
					sn_array.put(sn_json);
				}
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
			listjson.put("total", memberSignApplyService.getListSize(json));
			listjson.put("datatable", sn_array);
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

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
	 *            MemberId
	 * @return Member資料
	 */
	@RequestMapping(value = "/s15/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Member member = memberService.get(id);
			if (member != null) {
				Org org = orgService.getDataById(member.getOrgId());
				if (org != null) {
					ViewParentOrg orgLocal = orgService.getLocalParentOrg(member.getOrgId());
					String orgLocalName = "";
					if (orgLocal != null)
						orgLocalName = orgLocal.getName();
					ViewParentOrg orgSupervise = orgService.getSuperviseParentOrg(member.getOrgId());
					String orgSuperviseName = "";
					if (orgSupervise != null)
						orgSuperviseName = orgSupervise.getName();
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", member.getId());
					sn_json.put("OrgId", member.getOrgId());
					sn_json.put("OrgName", org.getName());
					sn_json.put("OrgLocalName", orgLocalName);
					sn_json.put("OrgSuperviseName", orgSuperviseName);
					sn_json.put("Account", member.getAccount());
					sn_json.put("Name", member.getName());
					sn_json.put("Email", member.getEmail());
					sn_json.put("MobilePhone", member.getMobilePhone());
					sn_json.put("IsEnable", member.getIsEnable());
					sn_json.put("CreateTime", WebDatetime.toString(member.getCreateTime()));
					sn_array.put(sn_json);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 啟用會員API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            條件
	 * @return 是否啟用會員成功
	 */
	@RequestMapping(value = "/s15/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long memberId = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Member member = memberService.get(memberId);
			MemberHistory memberHistory = memberService.getMemberHistoryByMemberId(memberId);
			if (member != null && memberHistory == null) {
				Org org = orgService.getDataById(member.getOrgId());
				if (org != null) {
					org = orgService.apply(member.getOrgId());
					memberService.setMemberEnable(memberId, true, getBaseMemberId());
					String salt = WebCrypto.generateUUID();
					String newCode = WebCrypto.getHash(WebCrypto.HashType.SHA512, WebCrypto.getRandomPassword() + salt);
					memberService.insertMemberHistory(memberId, newCode, salt, (short) -1, getBaseMemberId());
					String code = WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID();
					Date expireTime = WebDatetime.addDays(null, 30);
					memberService.updateForgotTemp(code, memberId, expireTime);
					memberRoleService.insert(getBaseMemberId(), member.getId(), (long) 10); //自動新增會員聯絡人角色
					memberRoleService.insert(getBaseMemberId(), member.getId(), (long) 11); //自動新增會員管理者角色
					
					String mailSubject = resourceMessageService.getMessageValue("mailSignApplyAdminSetPasswordSubject");
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailSignApplyAdminSetPasswordBody"), org.getName(), WebConfig.WEB_SITE_URL + "reset?" + code, WebConfig.WEB_SITE_URL);
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), null, null, mailSubject, mailBody, null);
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
}