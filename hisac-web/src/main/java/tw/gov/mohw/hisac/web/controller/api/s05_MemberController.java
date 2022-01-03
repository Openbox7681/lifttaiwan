package tw.gov.mohw.hisac.web.controller.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.dao.MemberDAO;
import tw.gov.mohw.hisac.web.dao.MemberRoleDAO;
import tw.gov.mohw.hisac.web.dao.OrgDAO;
import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;
import tw.gov.mohw.hisac.web.dao.OrgVariable.OrgType;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.SpMemberRoleName;

import tw.gov.mohw.hisac.web.domain.ViewMember;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.MemberRoleService;
import tw.gov.mohw.hisac.web.service.OrgService;

import tw.gov.mohw.hisac.web.service.IresApiService;


/**
 * 人員基本資料管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s05_MemberController extends BaseController {

	@Autowired
	private OrgService orgService;

	@Autowired
	private MemberRoleService memberRoleService;

	@Autowired
	private MailService mailService;

	@Autowired
	private IresApiService iresApiService;
	
	@Autowired
	private OrgDAO orgDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberRoleDAO memberRoleDAO;

	private String targetControllerName = "sys";
	private String targetActionName = "s05";

	/**
	 * 取得人員基本資料資料API
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
	@RequestMapping(value = "/s05/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (baseMemberRole.IsAdmin) {
			obj.put("RoleId", 1);
			json = obj.toString();
		} else if (baseMemberRole.IsHisac) {
			obj.put("RoleId", 2);
			json = obj.toString();
		} else if (baseMemberRole.IsApplyAdmin) {
			obj.put("RoleId", 9);
			obj.put("baseOrgId", getBaseOrgId());
			json = obj.toString();
		} else if (baseMemberRole.IsMemberAdmin) {
			obj.put("RoleId", 11);
			obj.put("baseOrgId", getBaseOrgId());
			json = obj.toString();
		} else {
		}
		// if (baseMemberRole.IsMemberAdmin == true &&
		// baseMemberRole.IsAdmin == false && baseMemberRole.IsHisac ==
		// false) {
		// obj.put("RoleId", 11); // 11.會員機構管理者
		// obj.put("getBaseOrgId()", getBaseOrgId());
		// json = obj.toString();
		// }
	
		
		
		
		obj = new JSONObject(json);
		String onjstr = WebCrypto.getSafe(obj.toString());
		
		List<ViewMember> members = memberService.getList(onjstr);
		long size = memberService.getListSize(onjstr);
		listjson.put("total", size);
		JSONArray sn_array = new JSONArray();
		if (members != null) {
			for (ViewMember member : members) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", member.getId());
				sn_json.put("Account", member.getAccount());
				sn_json.put("Name", member.getName());
				sn_json.put("Email", member.getEmail());
				sn_json.put("SpareEmail", member.getSpareEmail());
				sn_json.put("MobilePhone", member.getMobilePhone());
				sn_json.put("CityPhone", member.getCityPhone());
				sn_json.put("FaxPhone", member.getFaxPhone());
				sn_json.put("Address", member.getAddress());
				sn_json.put("Department", member.getDepartment());
				sn_json.put("Title", member.getTitle());
				sn_json.put("IsEnable", member.getIsEnable());
				sn_json.put("EnableTime", member.getEnableTime());
				sn_json.put("ErrorCount", member.getErrorCount());
				sn_json.put("OrgName", member.getOrgName());
				sn_json.put("Code", member.getCode());
				sn_json.put("OrgType", member.getOrgType());
				sn_json.put("AuthType", member.getAuthType());
				if (member.getOrgType().equals("3")) {
					if (member.getCiLevel() == null || member.getCiLevel().equals("0")) {
						sn_json.put("CILevel", WebMessage.getMessage("orgCILevel0", null, locale));
					} else if (member.getCiLevel().equals("1")) {
						sn_json.put("CILevel", WebMessage.getMessage("orgCILevel1", null, locale));
						
					} else if (member.getCiLevel().equals("2")) {
						sn_json.put("CILevel", WebMessage.getMessage("orgCILevel2", null, locale));
					}
				} else {
					sn_json.put("CILevel", "");
				}
				if (member.getErrorCount() == null) {
					sn_json.put("Status", 0); // 審核中(查看,無動作)
				} else if (member.getErrorCount() == -1) {
					sn_json.put("Status", 1); // 等待啟用 設定密碼(查看,無動作)
				} else if (member.getIsEnable() == false) {
					sn_json.put("Status", 2); // 已停用(查看,啟用)
				} else {
					sn_json.put("Status", 3); // 正常狀態(查看,停用,編輯)
				}
				JSONArray sn_role_array = new JSONArray();
				JSONObject memberObj = new JSONObject();
				memberObj.put("Id", member.getId());
				List<SpMemberRoleName> roles = memberRoleService.getMemberRoleList(memberObj.toString());
				if (roles != null) {
					for (SpMemberRoleName role : roles) {
						if (role.getFlag() != 0)
							sn_role_array.put(role.getName());
					}
				}
				sn_json.put("Roles", sn_role_array);
				sn_array.put(sn_json);
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		}
		listjson.put("datatable", sn_array);

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
	
	

	/**
	 * 取得人員基本資料資料API
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
	@RequestMapping(value = "/s05/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		json = WebCrypto.getSafe(json);
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Member member = memberService.get(id);
			JSONObject sn_json = new JSONObject();
			long orgId = member.getOrgId();
			Org org = orgService.getDataById(orgId);
			
			sn_json.put("Id", member.getId());
			sn_json.put("OrgId", member.getOrgId());
			sn_json.put("OrgName", org.getName());
			sn_json.put("IsPublic", org.getIsPublic() == null ? false : org.getIsPublic());
			sn_json.put("SecurityLevel", org.getSecurityLevel() == null ? 0 : org.getSecurityLevel());
			sn_json.put("HealthLevelId", org.getHealthLevelId() == null ? 0 : org.getHealthLevelId());
			
			sn_json.put("Account", member.getAccount());
			sn_json.put("Name", member.getName());
			sn_json.put("Email", member.getEmail());
			sn_json.put("SpareEmail", member.getSpareEmail());
			sn_json.put("MobilePhone", member.getMobilePhone());
			sn_json.put("CityPhone", member.getCityPhone());
			sn_json.put("FaxPhone", member.getFaxPhone());
			sn_json.put("Address", member.getAddress());
			sn_json.put("Department", member.getDepartment());
			sn_json.put("Title", member.getTitle());
			sn_json.put("IsEnable", member.getIsEnable());
			sn_json.put("EnableTime", member.getEnableTime());
			sn_json.put("CreateId", member.getCreateId());
			sn_json.put("CreateTime", member.getCreateTime());
			sn_json.put("ModifyId", member.getModifyId());
			sn_json.put("ModifyTime", member.getModifyTime());
			
			
			
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增人員基本資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Member
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s05/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals("")) {
			return responseJson.toString();
		}
		try {
			String orgCode = obj.getString("account");
			String orgName = obj.getString("name");
			String orgCity = "";
			String orgTown = "";
			String orgAddress = "";
			String orgBossName = "";
			String orgBossEmail = "";
			String orgBossMobilePhone = "";
			String orgPrincipalName = "";
			String orgPrincipalMobilePhone = "";
			Long healthLevelId = Long.valueOf(0);
			Long securityLevel = Long.valueOf(0);
			Boolean isPublic = false;
			
			String memberAccount = obj.getString("account");
			String memberName = obj.getString("name");
			String memberEmail = obj.getString("email");
			String memberMobilePhone = "";
			Boolean isEnable = obj.getBoolean("isEnable");
			Date now = new Date();

			Member existMember = memberService.getMemberByAccount(memberAccount);
			if (existMember == null) {
				Org org = orgService.findByCode(orgCode);
				if (org == null) {
					// Create Org Info
					String ciLevel = "0";
					
					org = new Org();
					org.setCode(orgCode);
					org.setOrgType(OrgType.Member.getValue());
					org.setAuthType(AuthType.None.getValue());
					org.setCiLevel(ciLevel);
					org.setName(orgName);
					org.setCity(orgCity);
					org.setTown(orgTown);
					org.setAddress(orgAddress);
					org.setBossName(orgBossName);
					org.setBossEmail(orgBossEmail);
					org.setBossMobilePhone(orgBossMobilePhone);
					org.setPrincipalName(orgPrincipalName);
					org.setPrincipalMobilePhone(orgPrincipalMobilePhone);
					org.setIsEnable(true);
					org.setCreateId((long) 1);
					org.setCreateTime(now);
					org.setModifyId((long) 1);
					org.setModifyTime(now);
					org.setSecurityLevel(securityLevel);
					org.setHealthLevelId(healthLevelId);
					org.setIsPublic(isPublic);
					org.setSort(Long.valueOf(1));
					org.setIsApply(true);
					orgDAO.insert(org);
				}

				// Create Member Info
				Member member = new Member();
				member.setAccount(memberAccount);
				member.setOrgId(org.getId());
				member.setName(memberName);
				member.setEmail(memberEmail);
				member.setMobilePhone(memberMobilePhone);
				member.setIsEnable(isEnable);
				member.setEnableTime(now);
				member.setCreateId((long) 1);
				member.setCreateTime(now);
				member.setModifyId((long) 1);
				member.setModifyTime(now);
				memberDAO.insert(member);
				
				MemberRole memberRole = new MemberRole();
				memberRole.setMemberId(member.getId());
				memberRole.setRoleId(Long.valueOf(1));
				memberRole.setIsEnable(true);
				memberRole.setCreateId((long) 1);
				memberRole.setCreateTime(now);
				memberRole.setModifyId((long) 1);
				memberRole.setModifyTime(now);
				memberRoleDAO.insert(memberRole);
				
				try {
					Base64.Decoder decoder = Base64.getDecoder();
					String code = new String(decoder.decode(obj.getString("code")), StandardCharsets.UTF_8.toString());
					if (code == null || code.isEmpty()) {
						// 密碼強度不符合
						responseJson.put("success", false);
						responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						String salt = WebCrypto.generateUUID();
						String newCode = WebCrypto.getHash(WebCrypto.HashType.SHA512, code + salt);
						memberService.insertMemberHistory(member.getId(), newCode, salt, (short) 0, member.getId());
						responseJson.put("success", true);
						responseJson.put("msg", WebMessage.getMessage("memberResetCodeSuccess", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (UnsupportedEncodingException e) {
					responseJson.put("success", false);
					responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
					systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("memberSignUpFail", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, "signUp", SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} catch (Exception e) {
			//e.printStackTrace();
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("memberSignUpFail", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, "signUp", SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 更新人員基本資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            Member
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s05/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		json = WebCrypto.getSafe(json);
		
		if (token == null || token.getToken().equals("")) {
			return responseJson.toString();
		}
		
		long id = obj.getLong("id");
		String memberAccount = obj.isNull("account") == true ? null : obj.getString("account");
		String memberName = obj.isNull("name") == true ? null : obj.getString("name");
		String memberEmail = obj.isNull("email") == true ? null : obj.getString("email");
		Boolean isEnable = obj.isNull("isEnable") == true ? false : obj.getBoolean("isEnable");
		Date now = new Date();
		
		Member member = memberService.get(id);
		if(member.getAccount().equals(memberAccount)) {
			member.setName(memberName);
			member.setEmail(memberEmail);
			member.setIsEnable(isEnable);
			member.setModifyId((long) 1);
			member.setModifyTime(now);
			memberDAO.update(member);
		}
		
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String code = new String(decoder.decode(obj.getString("code")), StandardCharsets.UTF_8.toString());
			if (code == null || code.isEmpty()) {
				// 密碼強度不符合
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				String salt = WebCrypto.generateUUID();
				String newCode = WebCrypto.getHash(WebCrypto.HashType.SHA512, code + salt);
				memberService.insertMemberHistory(member.getId(), newCode, salt, (short) 0, member.getId());
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("memberResetCodeSuccess", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} catch (UnsupportedEncodingException e) {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, memberAccount, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		return responseJson.toString();
	}
	
	
	/**
	 * 停用/啟用金鑰資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            orgCode 與 stats (Delete為停用 , Normal 為啟用)
	 * @return 狀態是否改變成功
	 */
	@RequestMapping(value = "/s05/ires/api/changeAgencyCertificateByState", method = RequestMethod.POST)
	public @ResponseBody String ChangeAgencyCertificateByState(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
		json = WebCrypto.getSafe(json);
		
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			
			if (!memberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
			
			else {
				Member member = memberService.setDisable(getBaseMemberId(), id);
				JSONObject response = iresApiService.getAgencyCertificateByAgncyCode(json);
				
				
				if (response.getString("Status").equals("200")) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					responseJson.put("message" , response.getJSONObject("msg"));
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					responseJson.put("message" , response.getString("msg"));
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		}else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
		
	}
	
	
	

	/**
	 * 刪除人員基本資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s05/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
		json = WebCrypto.getSafe(json);

		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!memberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Member member = memberService.setDisable(getBaseMemberId(), id);
				if (member != null) {
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
	 * 刪除人員基本資料資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s05/realDelete", method = RequestMethod.POST)
	public @ResponseBody String RealDeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		json = WebCrypto.getSafe(json);

		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!memberService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {				
				if (memberService.delete(id)) {
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
	 * 取得單位機關名稱資料API
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
	@RequestMapping(value = "/s05/getorg", method = RequestMethod.POST)
	public String Getorg(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		json = WebCrypto.getSafe(json);

		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<Org> orgs = null;
			if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac) {
				orgs = orgService.getAll();
			} else {
				JSONObject obj = new JSONObject(json);
				obj.put("Id", getBaseOrgId());
				json = obj.toString();
				orgs = orgService.getList(json);
			}
			// if (baseMemberRole.IsMemberAdmin == true &&
			// baseMemberRole.IsAdmin == false && baseMemberRole.IsHisac ==
			// false)// 11.會員機構管理者
			// {
			// JSONObject obj = new JSONObject(json);
			// obj.put("Id", getBaseOrgId());
			// json = obj.toString();
			// orgs = orgService.getList(json);
			//
			// } else
			// orgs = orgService.getAll();
			if (orgs != null)
				for (Org org : orgs) {
					if (baseMemberRole.IsAdmin) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", org.getId());
						sn_json.put("Code", org.getCode());
						sn_json.put("Name", org.getName());						
						if (org.getCiLevel()==null)
							sn_json.put("CILevel", "");
						else if (org.getCiLevel().equals("0"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel0", null, locale));
						else if (org.getCiLevel().equals("1"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel1", null, locale));								
						else if (org.getCiLevel().equals("2"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel2", null, locale));						
						else
							sn_json.put("CILevel", "");					
						sn_array.put(sn_json);
					} else if (baseMemberRole.IsHisac) {
						JSONObject sn_json = new JSONObject();
						if (org.getId() != 1) {
							sn_json.put("Id", org.getId());
							sn_json.put("Code", org.getCode());
							sn_json.put("Name", org.getName());
							if (org.getCiLevel()==null)
								sn_json.put("CILevel", "");
							else if (org.getCiLevel().equals("0"))
								sn_json.put("CILevel", WebMessage.getMessage("orgCILevel0", null, locale));
							else if (org.getCiLevel().equals("1"))
								sn_json.put("CILevel", WebMessage.getMessage("orgCILevel1", null, locale));								
							else if (org.getCiLevel().equals("2"))
								sn_json.put("CILevel", WebMessage.getMessage("orgCILevel2", null, locale));						
							else
								sn_json.put("CILevel", "");
							sn_array.put(sn_json);
						}
					} else {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", org.getId());
						sn_json.put("Code", org.getCode());
						sn_json.put("Name", org.getName());
						if (org.getCiLevel()==null)
							sn_json.put("CILevel", "");
						else if (org.getCiLevel().equals("0"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel0", null, locale));
						else if (org.getCiLevel().equals("1"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel1", null, locale));								
						else if (org.getCiLevel().equals("2"))
							sn_json.put("CILevel", WebMessage.getMessage("orgCILevel2", null, locale));						
						else
							sn_json.put("CILevel", "");
						sn_array.put(sn_json);
					}
				}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得會員權限資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員權限資料
	 */
	@RequestMapping(value = "/s05/member/role/query", method = RequestMethod.POST)
	public String QueryMemberRole(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		json = WebCrypto.getSafe(json);

		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			if (baseMemberRole.IsAdmin || baseMemberRole.IsHisac) {
				List<SpMemberRoleName> spMemberRoleNames = memberRoleService.getMemberRoleList(json);
				for (SpMemberRoleName spMemberRoleName : spMemberRoleNames) {
					if (baseMemberRole.IsAdmin || (baseMemberRole.IsHisac && spMemberRoleName.getRoleId() != 1)) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", spMemberRoleName.getId());
						sn_json.put("RoleId", spMemberRoleName.getRoleId());
						sn_json.put("Name", spMemberRoleName.getName());
						sn_json.put("Flag", spMemberRoleName.getFlag() == 0 ? false : true);
						sn_array.put(sn_json);
					}
				}
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
	 * 取得會員訂閱資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員訂閱資料
	 */
	@RequestMapping(value = "/s05/member/subscribe/query", method = RequestMethod.POST)
	public String QueryMemberSubscribe(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		json = WebCrypto.getSafe(json);

		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/s05/checkAccount", method = RequestMethod.POST)
	public @ResponseBody String checkAccount(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		try {
			JSONObject obj = new JSONObject(URLDecoder.decode(json, StandardCharsets.UTF_8.toString()));
			String memberAccount = obj.isNull("memberAccount") ? "" : obj.getString("memberAccount");
			Member member = memberService.getMemberByAccount(memberAccount);
			if (member == null) {
				responseJson.put("success", true);
			} else {
				responseJson.put("success", false);
			}
		} catch (Exception e) {
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
}