package tw.gov.mohw.hisac.web.controller.api;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.Subscribe;
import tw.gov.mohw.hisac.web.domain.SubscribeMember;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.SubscribeService;
import tw.gov.mohw.hisac.web.service.SubscribeMemberService;

/**
 * 設置會員個人資料控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s00_MemberProfileController extends BaseController {
	final static Logger logger = LoggerFactory.getLogger(s00_MemberProfileController.class);

	@Autowired
	private OrgService orgService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private SubscribeMemberService subscribeMemberService;

	// private String targetControllerName = "sys";
	// private String targetActionName = "s00";

	/**
	 * 取得組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            組織Id
	 * @return 組織資料
	 */
	@RequestMapping(value = "/profile/org/query", method = RequestMethod.POST)
	public String QueryOrg(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		// if (menuService.getReadPermission(getBaseMemberId(), targetControllerName,
		// targetActionName)) {
		// JSONObject obj = new JSONObject(json);
		Org org = orgService.getDataById(getBaseOrgId());
		JSONObject sn_json = new JSONObject();
		sn_json.put("Id", org.getId());
		sn_json.put("Name", org.getName());
		sn_json.put("Code", org.getCode());
		sn_json.put("OrgType", org.getOrgType());
		sn_json.put("AuthType", org.getAuthType());
		sn_json.put("Tel", org.getTel());
		sn_json.put("Fax", org.getFax());
		sn_json.put("City", org.getCity());
		sn_json.put("Town", org.getTown());
		sn_json.put("Address", org.getAddress());
		sn_json.put("BossName", org.getBossName());
		sn_json.put("BossEmail", org.getBossEmail());
		sn_json.put("BossMobilePhone", org.getBossMobilePhone());
		sn_json.put("PrincipalName", org.getPrincipalName());
		sn_json.put("PrincipalMobilePhone", org.getPrincipalMobilePhone());
		sn_json.put("IsEnable", org.getIsEnable());

		sn_array.put(sn_json);
		systemLogService.insert(baseControllerName, baseActionName, getBaseOrgId().toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		// } else {
		// systemLogService.insert(baseControllerName, baseActionName,
		// getBaseOrgId().toString(), SystemLogVariable.Action.Read,
		// SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		// }
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 更新組織資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            組織
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/profile/org/update", method = RequestMethod.POST)
	public @ResponseBody String UpdateOrg(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		// if (menuService.getUpdatePermission(getBaseMemberId(),
		// targetControllerName, targetActionName)) {
		// JSONObject obj = new JSONObject(json);
		// long id = obj.getLong("Id");
		if (!orgService.isExist(getBaseOrgId())) {
			responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			Org org = orgService.updateProfile(getBaseMemberId(), json, getBaseOrgId());
			if (org != null) {
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
		// responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny",
		// null, locale));
		// responseJson.put("success", false);
		// systemLogService.insert(baseControllerName, baseActionName, json,
		// SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny,
		// getBaseIpAddress(), getBaseMemberAccount());
		// }

		return responseJson.toString();
	}

	/**
	 * 重設個人資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/profile/query", method = RequestMethod.POST)
	public String QueryMember(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		JSONArray subscribe_array = new JSONArray();
		// JSONObject obj = new JSONObject(json);
		
		Member member = memberService.get(getBaseMemberId());
		List<Subscribe> subscribes = subscribeService.getAll();		
		List<SubscribeMember> subscribeMembers = subscribeMemberService.getByMemberId(getBaseMemberId());
		if (subscribes!=null)
			for (Subscribe subscribe: subscribes) {
				boolean flag = false;
				JSONObject sn_json = new JSONObject();	
				sn_json.put("SubscribeId", subscribe.getId());
				sn_json.put("Name", subscribe.getName());
				if (subscribeMembers != null)
				for (SubscribeMember subscribeMember : subscribeMembers) {
					if (subscribeMember.getSubscribeId().equals(subscribe.getId())) {
						sn_json.put("Flag", true);
						flag = true;
						break;
					}
				}
				if (!flag)
					sn_json.put("Flag", false);
				subscribe_array.put(sn_json);
			}
		
		JSONObject sn_json = new JSONObject();
		sn_json.put("Name", member.getName());
		sn_json.put("Email", member.getEmail());
		sn_json.put("SpareEmail", member.getSpareEmail());
		sn_json.put("MobilePhone", member.getMobilePhone());
		sn_json.put("CityPhone", member.getCityPhone());
		sn_json.put("FaxPhone", member.getFaxPhone());
		sn_json.put("Address", member.getAddress());
		sn_json.put("Department", member.getDepartment());
		sn_json.put("Title", member.getTitle());
		sn_json.put("SubscribeData", subscribe_array);
		sn_array.put(sn_json);
		systemLogService.insert(baseControllerName, baseActionName, getBaseMemberId().toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 重設個人資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            個人資料
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	public @ResponseBody String UpdateMember(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		try {
			if (memberService.isExist(getBaseMemberId())) {
				if (memberService.updateProfile(getBaseMemberId(), json) != null) {
					List<SubscribeMember> subscribeMembers = subscribeMemberService.getByMemberId(getBaseMemberId());
					if (subscribeMembers!= null)
						for (SubscribeMember subscribeMember :subscribeMembers) {
							if (!subscribeMemberService.delete(subscribeMember.getId())) {
								responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
								responseJson.put("success", false);
								systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
								return responseJson.toString();
							}
								
						}
					JSONObject obj = new JSONObject(json);
					JSONArray json_array = obj.getJSONArray("SubscribeData");
					for (int i = 0; i < json_array.length(); i++) {
						JSONObject obj_member_subscribe = json_array.getJSONObject(i);												
						boolean flag = obj_member_subscribe.isNull("Flag") == true ? false : obj_member_subscribe.getBoolean("Flag");
						if (flag)
							if (subscribeMemberService.insert(getBaseMemberId(), getBaseMemberId(), obj_member_subscribe.toString())==null) {
								responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
								responseJson.put("success", false);
								systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
								return responseJson.toString();
							}
								
					}
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
		} catch (Exception e) {
			responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 重設密碼
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/code/update", method = RequestMethod.POST)
	public @ResponseBody String ChangeCode(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		JSONObject obj = new JSONObject(json);
		Base64.Decoder decoder = Base64.getDecoder();
		String oldCode = obj.isNull("OldCode") ? null : obj.getString("OldCode");
		String newCode = obj.isNull("NewCode") ? null : obj.getString("NewCode");
		try {
			oldCode = new String(decoder.decode(oldCode), StandardCharsets.UTF_8.toString());
			newCode = new String(decoder.decode(newCode), StandardCharsets.UTF_8.toString());
			if (memberService.isAuth(getBaseMemberAccount(), oldCode)) {
				if (newCode == null || newCode.isEmpty() || newCode.length() < 12 || !Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{12,}$").matcher(newCode).matches()) {
					// 密碼強度不符合
					responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
					responseJson.put("success", false);
				} else {
					boolean isMemberHistoryeEffective = memberService.checkMemberHistory(getBaseMemberId(), newCode, WebConfig.HISTORY_TIMES, WebConfig.HISTORY_DAYS);
					if (isMemberHistoryeEffective) {
						Object[] messageArgs = new Object[2];
						messageArgs[0] = WebConfig.HISTORY_TIMES;
						messageArgs[1] = WebConfig.HISTORY_DAYS;
						responseJson.put("msg", WebMessage.getMessage("memberHistoryeEffective", messageArgs, locale));
						responseJson.put("success", false);
					} else {
						String salt = WebCrypto.generateUUID();
						newCode = WebCrypto.getHash(WebCrypto.HashType.SHA512, newCode + salt);
						memberService.insertMemberHistory(getBaseMemberId(), newCode, salt, (short) 0, getBaseMemberId());
						responseJson.put("msg", WebMessage.getMessage("globalResetCodeSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					}
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalResetCodeFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} catch (Exception e) {
			responseJson.put("msg", WebMessage.getMessage("globalResetCodeFail", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 取得密碼長度API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model	
	 * @return 密碼長度
	 */
	@RequestMapping(value = "/code/getPasswordLength", method = RequestMethod.POST)
	public String GetPasswordLength(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray arrjson = new JSONArray();	
		arrjson.put(resourceMessageService.getMessageValue("passwordLength"));
		model.addAttribute("json", arrjson.toString());		
		return "msg";
	}
}