package tw.gov.mohw.hisac.web.controller.api.open;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebNet;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.Sso;
import tw.gov.mohw.hisac.web.service.MemberRoleService;
import tw.gov.mohw.hisac.web.service.MemberService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.ResourceMessageService;
import tw.gov.mohw.hisac.web.service.SystemLogService;

/**
 * PMO資產盤點人員資料API
 */
@Controller
@RequestMapping(value = "/open/api", produces = "application/json; charset=utf-8")
public class MemberController {
	@Autowired
	private OrgService orgService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private ResourceMessageService resourceMessageService;

	@Autowired
	private MemberRoleService memberRoleService;

	private String baseControllerName = "MemberAPI";
	private String baseSystemAccount = "SERVICE.SERVER";
	protected final Base64.Decoder decoder = Base64.getDecoder();

	@RequestMapping(value = "/member/valid", method = RequestMethod.POST)
	public String MemberValid(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		String baseActionName = "MemberValid";
		String baseIpAddress = WebNet.getIpAddr(request);
		resourceMessageService.loadResource();
		JSONObject responseJson = new JSONObject();
		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("iresAllowIp"), WebNet.getIpAddr(request))) {
			JSONObject obj = new JSONObject(json);
			String token = obj.isNull("Token") == true ? "" : obj.getString("Token");
			String code = obj.isNull("Code") == true ? "" : obj.getString("Code");
			try {
				token = new String(decoder.decode(token), StandardCharsets.UTF_8.toString());
			} catch (Exception e) {
				token = "";
				//e.printStackTrace();
			}
			try {
				code = new String(decoder.decode(code), StandardCharsets.UTF_8.toString());
			} catch (Exception e) {
				code = "";
				//e.printStackTrace();
			}
			if (!code.equals("") && !token.equals("")) {
				code = WebCrypto.aesDecrypt(resourceMessageService.getMessageValue("iresToken"), token, code);
				if (code != null) {
					Sso sso = memberService.getSso(code);
					if (sso != null && sso.getCode().equals(code) && sso.getExpireTime().after(new Date())) {
						Member member = memberService.get(sso.getMemberId());
						if (member != null) {
							Org org = orgService.getDataById(member.getOrgId());
							List<MemberRole> roles = memberRoleService.getByMemberId(member.getId());
							if (org != null || roles != null) {
								responseJson.put("isAuth", true);
								JSONObject memberInfo = new JSONObject();
								memberInfo.put("OrgNo", org.getCode());
								memberInfo.put("OrgName", org.getName());
								memberInfo.put("OrgCiLevel", org.getCiLevel());
								memberInfo.put("UserName", member.getName());
								memberInfo.put("UserID", member.getAccount());
								boolean isHisacBoss = false;
								for (MemberRole memberRole : roles) {
									if (memberRole.getRoleId() == 16) {
										isHisacBoss = true;
									}
								}
								memberInfo.put("IsHisacBoss", isHisacBoss);
								responseJson.put("MemberInfo", memberInfo);
								systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
							} else {
								systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
								responseJson.put("isAuth", false);
								responseJson.put("msg", "0001");
							}
						} else {
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
							responseJson.put("isAuth", false);
							responseJson.put("msg", "0002");
						}
					} else {
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
						responseJson.put("isAuth", false);
						responseJson.put("msg", "0003");
					}
				} else {
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
					responseJson.put("isAuth", false);
					responseJson.put("msg", "0004");
				}
			} else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
				responseJson.put("isAuth", false);
				responseJson.put("msg", "0005");
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, baseIpAddress, baseSystemAccount);
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/org/list", method = RequestMethod.POST)
	public String OrgList(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		String baseActionName = "MemberValid";
		String baseIpAddress = WebNet.getIpAddr(request);
		JSONObject responseJson = new JSONObject();
		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("iresAllowIp"), WebNet.getIpAddr(request))) {
			List<Org> orgs = orgService.getByOrgType("3");
			if (orgs != null) {
				JSONArray jsonArray = new JSONArray();
				for (Org org : orgs) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("OrgId", org.getCode());
					jsonObj.put("OrgName", org.getName());
					jsonObj.put("OrgCode", org.getCode());
					jsonObj.put("IsEnable", org.getIsEnable());
					jsonArray.put(jsonObj);
				}
				responseJson.put("Orgs", jsonArray);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
			} else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
				responseJson.put("msg", "0001");
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, baseIpAddress, baseSystemAccount);
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
}