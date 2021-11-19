package tw.gov.mohw.hisac.web.controller.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMailAttachment;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.WebNet;
import tw.gov.mohw.hisac.web.WebTOTPGenerator;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.MemberDAO;
import tw.gov.mohw.hisac.web.dao.OrgDAO;
import tw.gov.mohw.hisac.web.dao.OrgSignDAO;
import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;
import tw.gov.mohw.hisac.web.dao.OrgVariable.OrgType;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.ForgotTemp;
import tw.gov.mohw.hisac.web.domain.HealthLevel;
import tw.gov.mohw.hisac.web.domain.Healthcare;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MemberHistory;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.VerifyEmail;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMalwareManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;
import tw.gov.mohw.hisac.web.domain.ViewQAManagementGroup;
import tw.gov.mohw.hisac.web.service.ActivityManagementService;
import tw.gov.mohw.hisac.web.service.HealthLevelService;
import tw.gov.mohw.hisac.web.service.HealthcareService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.MalwareManagementService;
import tw.gov.mohw.hisac.web.service.NewsManagementService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.QAManagementService;


/**
 * 公開資訊控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicController extends BaseController {
	final static Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private HealthcareService healthcareService;

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private OrgDAO orgDAO;

	@Autowired
	private OrgSignDAO orgSignDAO;

	@Autowired
	private NewsManagementService newsManagementService;

	@Autowired
	private ActivityManagementService activityManagementService;

	@Autowired
	private QAManagementService qaManagementService;
	
	@Autowired
	private HealthLevelService healthLevelService;
	
	@Autowired
	private MalwareManagementService malwareManagementService;
	
	/**
	 * 重新設定Session Timeout
	 * 
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/resetTimeout", method = RequestMethod.POST)
	public @ResponseBody String resetTimeout() {
		JSONObject json = new JSONObject();
		json.put("success", true);
		return json.toString();
	}

	/**
	 * 發驗證信
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param account
	 *            帳號
	 * @param email
	 *            註冊時的電子郵件信箱
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/resend", method = RequestMethod.POST)
	public @ResponseBody String Resend(Locale locale, HttpServletRequest request, @RequestParam String account, @RequestParam String email) {
		JSONObject responseJson = new JSONObject();
		Member member = memberService.getMemberByAccount(account);
		if (member == null) {
			// 帳號不存在
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("memeberAccountNotExist", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			MemberHistory memberHistory = memberService.getMemberHistoryByMemberId(member.getId());
			if (memberHistory == null) {
				// 審核中
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("memeberAccountNotExist", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				// if (memberHistory.getErrorCount() == -1) {
				// // 已審核 等待啟用 設定密碼
				// responseJson.put("success", false);
				// responseJson.put("msg",
				// WebMessage.getMessage("memeberAccountNotExist", null,
				// locale));
				// } else if (member.getIsEnable() == false) {
				if (member.getIsEnable() == false) {
					// 已停用
					responseJson.put("success", false);
					responseJson.put("msg", WebMessage.getMessage("memeberAccountNotExist", null, locale));
					systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					// 帳號與電子郵件符合
					if (member.getEmail().equals(email)) {
						String code = WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID();
						Long memberId = member.getId();
						Date now = new Date();
						Date expireTime = WebDatetime.addMinutes(now, WebConfig.FORGOT_EXPIRE_MINUTES);
						memberService.updateForgotTemp(code, memberId, expireTime);
						String mailSubject = resourceMessageService.getMessageValue("mailResetPasswordSubject");
						String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailResetPasswordBody"), member.getName(), WebDatetime.toString(now), WebNet.getIpAddr(request), WebConfig.WEB_SITE_URL + "reset?" + code,
								WebConfig.WEB_SITE_URL);
						mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						responseJson.put("success", true);
						responseJson.put("msg", WebMessage.getMessage("loginResendSuccess", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

					} else {
						responseJson.put("success", false);
						responseJson.put("msg", WebMessage.getMessage("memeberAccountNotExist", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}
			}
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
	 * @param account
	 *            帳號
	 * @param id
	 *            驗證碼
	 * @param code
	 *            密碼
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody String Reset(Locale locale, HttpServletRequest request, @RequestParam String account, @RequestParam String id, @RequestParam String code) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		String codeLength = resourceMessageService.getMessageValue("passwordLength");
		codeLength = (codeLength==null || codeLength.length()==0) ? "12" : codeLength;
		int codeLengthCheck = Integer.parseInt(codeLength);
		
		Member member = memberService.getMemberByAccount(account);
		if (member == null) {
			// 帳號不存在
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

		} else {
			// MemberHistory memberHistory =
			// memberService.getMemberHistoryByMemberId(member.getId());
			// if (memberHistory.getErrorCount() == -1) {
			// // 已審核 等待啟用 設定密碼
			// responseJson.put("success", false);
			// responseJson.put("msg",
			// WebMessage.getMessage("memberResetCodeFail", null, locale));
			// } else if (member.getIsEnable() == false) {
			if (member.getIsEnable() == false) {
				// 已停用
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

			} else {
				// 帳號與驗證碼符合
				ForgotTemp forgotTemp = memberService.getForgotTemp(id);
				if (forgotTemp == null) {
					responseJson.put("success", false);
					responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
					systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				} else {
					if (forgotTemp.getMemberId().equals(member.getId()) && forgotTemp.getExpireTime().compareTo(new Date()) > 0) {
						final String codeStrengthPattern = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{"+codeLengthCheck+",}$";
						try {
							Base64.Decoder decoder = Base64.getDecoder();
							code = new String(decoder.decode(code), StandardCharsets.UTF_8.toString());
							if (code == null || code.isEmpty() || code.length() < codeLengthCheck || !Pattern.compile(codeStrengthPattern).matcher(code).matches()) {
								// 密碼強度不符合
								responseJson.put("success", false);
								responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
								systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

							} else {
								boolean isMemberHistoryeEffective = memberService.checkMemberHistory(member.getId(), code, WebConfig.HISTORY_TIMES, WebConfig.HISTORY_DAYS);
								if (isMemberHistoryeEffective) {
									Object[] messageArgs = new Object[2];
									messageArgs[0] = WebConfig.HISTORY_TIMES;
									messageArgs[1] = WebConfig.HISTORY_DAYS;
									responseJson.put("success", false);
									responseJson.put("msg", WebMessage.getMessage("memberHistoryeEffective", messageArgs, locale));
									systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

								} else {
									String salt = WebCrypto.generateUUID();
									String newCode = WebCrypto.getHash(WebCrypto.HashType.SHA512, code + salt);
									memberService.insertMemberHistory(member.getId(), newCode, salt, (short) 0, member.getId());
									memberService.deleteForgotTemp(id);
									responseJson.put("success", true);
									responseJson.put("msg", WebMessage.getMessage("memberResetCodeSuccess", null, locale));
									systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
								}
							}
						} catch (UnsupportedEncodingException e) {
							responseJson.put("success", false);
							responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
							systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("success", false);
						responseJson.put("msg", WebMessage.getMessage("memberResetCodeFail", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				}
			}
		}
		return responseJson.toString();
	}

	/**
	 * 檢查帳號是否存在
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
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

	/**
	 * 取得上級機關資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/getAuthorOrgs", method = RequestMethod.POST)
	public String getAuthorOrgs(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			List<Org> orgs = orgService.getList(true, OrgType.Authority, AuthType.Supervise);
			if (orgs != null) {
				for (Org org : orgs) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("value", org.getId());
					jsonObject.put("name", org.getName());
					jsonArray.put(jsonObject);
				}
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
			responseJson.put("datatable", jsonArray);
		} catch (Exception e) {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale));
			responseJson.put("datatable", jsonArray);
		}
		// return responseJson.toString();
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	
	
	/**
	 * 取得醫院層級資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param q
	 *            String
	 * @param per_page
	 *            Integer
	 * @return JSON Format String
	 */
	
	@RequestMapping(value = "/getHealthLevels", method = RequestMethod.POST)
	public String getHealthLevels(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		try {
			List<HealthLevel> healthLevels = healthLevelService.getAll();
			
			if( healthLevels!= null) {
				for (HealthLevel healthLevel : healthLevels) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", healthLevel.getId());
					jsonObject.put("name", healthLevel.getName());
					jsonArray.put(jsonObject);
				}	
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
			responseJson.put("datatable", jsonArray);
			
			
		} catch (Exception e) {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale));
			responseJson.put("datatable", jsonArray);
		}
		// return responseJson.toString();
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}


	
	
	
	
	

	/**
	 * 取得上級機關資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param q
	 *            String
	 * @param per_page
	 *            Integer
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/getHealthcares", method = RequestMethod.GET)
	public String getHealthcares(Locale locale, HttpServletRequest request, Model model, @RequestParam String q, @RequestParam Integer per_page) {
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		String queryString = "";
		if (q != null && q.length() <= 128) {
			queryString = myFilter.stripXSS(q);
		}
		int perPage = 0;
		try {
			perPage = Integer.parseInt(per_page.toString());
		} catch (Exception e) {
			perPage = 5;
		}
		try {
			queryString = myFilter.stripXSS(queryString);
			List<Healthcare> healthcares = healthcareService.getList(queryString, perPage);
			if (healthcares != null) {
				for (Healthcare healthcare : healthcares) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", healthcare.getCode());
					jsonObject.put("name", healthcare.getName());
					jsonObject.put("city", healthcare.getCity());
					jsonObject.put("town", healthcare.getTown());
					jsonObject.put("address", healthcare.getAddress());
					jsonObject.put("parentOrgId", healthcare.getParentOrgId());
					jsonObject.put("isCI", healthcare.getIsCI() == null ? false : healthcare.getIsCI());
					jsonObject.put("healthLevelId", healthcare.getHealthLevelId() == null ? 0 : healthcare.getHealthLevelId()  );
					jsonObject.put("isPublic", healthcare.getIsPublic() == null ? false : healthcare.getIsPublic());
					jsonObject.put("securityLevel", healthcare.getSecurityLevel() == null ? 0 : healthcare.getSecurityLevel());
					jsonArray.put(jsonObject);
				}
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
			responseJson.put("items", jsonArray);
		} catch (Exception e) {
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale));
			responseJson.put("items", jsonArray);
		}
		// String strResultJson = "";
		// try {
		// byte[] _byteResultJson =
		// responseJson.toString().getBytes(StandardCharsets.UTF_8.toString());
		// strResultJson = encoder.encodeToString(_byteResultJson);
		// } catch (Exception e) {
		// //e.printStackTrace();
		// }
		// model.addAttribute("json", myFilter.stripXSS(strResultJson));
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 產生驗證碼
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/genVerifyCode", method = RequestMethod.POST)
	public String genVerifyCode(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals("")) {
			model.addAttribute("json", responseJson.toString());
			return "msg";
		}
		JSONObject obj = new JSONObject(json);
		String vEmail = obj.isNull("vEmail") ? "" : obj.getString("vEmail");
		try {
			String code = WebCrypto.getRandomCode(6);
			if (!vEmail.equals("")) {
				VerifyEmail verifyEmail = memberService.updateVerifyEmail(vEmail, code);
				if (verifyEmail != null) {
					String mailSubject = resourceMessageService.getMessageValue("mailVerifySubject");
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailVerifyBody"), code.toString());
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), myFilter.filterEmail(vEmail), null, null, mailSubject, mailBody, null);
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} catch (Exception e) {
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 比對驗證碼
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
	public String verifyCode(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals("")) {
			model.addAttribute("json", responseJson.toString());
			return "msg";
		}
		JSONObject obj = new JSONObject(json);
		String vEmail = obj.isNull("vEmail") ? "" : obj.getString("vEmail");
		String vCode = obj.isNull("vCode") ? "" : obj.getString("vCode");
		try {
			if (!vEmail.equals("") && !vCode.equals("")) {
				VerifyEmail verifyEmail = memberService.getVerifyEmail(vEmail);
				if (verifyEmail.getCode().equals(vCode)) {
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} catch (Exception e) {
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/download_sign_up_info", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadSignUpInfo(Locale locale, HttpServletRequest request, @RequestParam("account") String account) throws DocumentException, IOException {
		Date now = new Date();
		String memberAccount = "";
		String memberName = "";
		String memberEmail = "";
		String memberMobilePhone = "";
		String orgCode = "";
		String orgName = "";
		String orgCity = "";
		String orgTown = "";
		String orgAddress = "";
		String orgBossName = "";
		String orgBossEmail = "";
		String orgBossMobilePhone = "";
		String orgPrincipalName = "";
		String orgPrincipalMobilePhone = "";
		String parentOrgName = "";
		String parentOrgName2 = "";
		Member member = memberService.getMemberByAccount(account);
		if (member != null) {
			MemberHistory memberHistory = memberService.getMemberHistoryByMemberId(member.getId());
			if (memberHistory == null) {
				memberAccount = member.getAccount();
				memberName = member.getName();
				memberEmail = member.getEmail();
				memberMobilePhone = member.getMobilePhone() == null ? "" : member.getMobilePhone();
				Org org = orgService.getDataById(member.getOrgId());
				orgCode = org.getCode();
				orgName = org.getName();
				orgCity = org.getCity();
				orgTown = org.getTown();
				orgAddress = org.getAddress();
				orgBossName = org.getBossName();
				orgBossEmail = org.getBossEmail();
				orgBossMobilePhone = org.getBossMobilePhone();
				orgPrincipalName = org.getPrincipalName();
				orgPrincipalMobilePhone = org.getPrincipalMobilePhone();
				ViewParentOrg parentOrg = orgService.getSuperviseParentOrg(member.getOrgId());
				if (parentOrg != null) {
					parentOrgName = parentOrg.getName();
				}
				ViewParentOrg parentOrg2 = orgService.getLocalParentOrg(member.getOrgId());
				if (parentOrg2 != null) {
					parentOrgName2 = parentOrg2.getName();
				}
			}
		}

		Document document = new Document(PageSize.A4);

		BaseFont baseFont;
		try {
			baseFont = BaseFont.createFont(WebConfig.PDF_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (Exception e) {
			baseFont = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1257, BaseFont.EMBEDDED);
		}

		Font fontTitle = new Font(baseFont, 13, Font.NORMAL);
		Font fontContent = new Font(baseFont, 11, Font.NORMAL);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		{
			Paragraph p = new Paragraph("", fontContent);
			p.add(new Chunk(new Chunk(new VerticalPositionMark())));
			p.add("機密等級:                           ");
			document.add(p);
		}
		{

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{1, 1, 1, 1});
			table.addCell(new PdfPCell(new Paragraph("制定單位", fontContent)));
			table.addCell(new PdfPCell(new Paragraph("文件名稱", fontContent)));
			table.addCell(new PdfPCell(new Paragraph("表單編號", fontContent)));
			table.addCell(new PdfPCell(new Paragraph("版次", fontContent)));

			table.addCell(new PdfPCell(new Paragraph("H-ISAC", fontContent)));
			table.addCell(new PdfPCell(new Paragraph(WebMessage.getMessage("orgSingUpData", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(new PdfPCell(new Paragraph(" ", fontContent)));
			table.addCell(new PdfPCell(new Paragraph("V 1.0", fontContent)));
			document.add(table);
		}
		{
			Paragraph p = new Paragraph("", fontContent);
			p.add(new Chunk(new Chunk(new VerticalPositionMark())));
			p.add("流水號:" + WebDatetime.toString(now, "yyyyMMddHHmmssSSS"));
			document.add(p);
		}

		Paragraph p = new Paragraph();
		p = new Paragraph(WebMessage.getMessage("orgSingUpData", null, Locale.TRADITIONAL_CHINESE), fontTitle);
		document.add(p);

		{
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{1, 1, 3});
			PdfPCell cell;

			cell = new PdfPCell(new Phrase(new Paragraph("醫事機構資訊", fontTitle)));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Code", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgCode + "                        為醫事系統核准之醫事機構", fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Name", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgName, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("機構地址", fontContent)));
			cell.setRowspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgCity", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgCity, fontContent)));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgTown", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgTown, fontContent)));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Address", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgAddress, fontContent)));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("衛生主管機關", fontTitle)));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType3SuperviseName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(parentOrgName, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType3LocalName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(parentOrgName2, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("系統管理者資訊", fontTitle)));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminAccount", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(memberAccount, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(memberName, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminEmail", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(memberEmail, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(memberMobilePhone, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgBossName, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossEmail", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgBossEmail, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgBossMobilePhone, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("principalName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgPrincipalName, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("principalMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph(orgPrincipalMobilePhone, fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			Phrase pr = new Phrase();
			pr.add(new Paragraph("請於此處加蓋機構關防", fontContent));
			pr.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n", fontContent));
			pr.add(new Paragraph("申請日期:" + WebDatetime.toString(now, "yyyy年MM月dd日"), fontContent));
			cell = new PdfPCell(pr);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("(本欄由H-ISAC填寫)", fontTitle)));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("審核人員", fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph("", fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(new Paragraph("審核日期", fontContent)));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(new Paragraph("", fontContent)));
			cell.setColspan(2);
			table.addCell(cell);

			document.add(table);
		}

		document.add(new Paragraph("備註: ", fontTitle));
		document.add(new Paragraph("1. 新會員機構於H-ISAC網站申請會員註冊，請確認申請資料，加蓋機構關防(免備文)，正本寄至衛生福利部資訊處(地址：11558 台北市南港區忠孝東路6段488號)。信封註明申請H-ISAC會員，H-ISAC將於收件後3工作天內開通帳號。", fontContent));

		Phrase footer = new Phrase("□永久保存  ■定期保存    年", fontContent);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, document.right() - 120 + document.leftMargin(), document.bottom() - 10, 0);

		document.close();
		byte[] contents = byteArrayOutputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "HisacSignUp.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}

	/**
	 * 新增會員資料
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            RequestBody
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String signUp(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals("")) {
			model.addAttribute("json", responseJson.toString());
			return "msg";
		}
		try {
			JSONObject obj = new JSONObject(URLDecoder.decode(json, StandardCharsets.UTF_8.toString()));
			String orgCode = obj.isNull("orgCode") ? "" : obj.getString("orgCode");
			String orgName = obj.isNull("orgName") ? "" : obj.getString("orgName");
			String orgCity = obj.isNull("orgCity") ? "" : obj.getString("orgCity");
			String orgTown = obj.isNull("orgTown") ? "" : obj.getString("orgTown");
			String orgAddress = obj.isNull("orgAddress") ? "" : obj.getString("orgAddress");
			String orgBossName = obj.isNull("orgBossName") ? "" : obj.getString("orgBossName");
			String orgBossEmail = obj.isNull("orgBossEmail") ? "" : obj.getString("orgBossEmail");
			String orgBossMobilePhone = obj.isNull("orgBossMobilePhone") ? "" : obj.getString("orgBossMobilePhone");
			String orgPrincipalName = obj.isNull("orgPrincipalName") ? "" : obj.getString("orgPrincipalName");
			String orgPrincipalMobilePhone = obj.isNull("orgPrincipalMobilePhone") ? "" : obj.getString("orgPrincipalMobilePhone");
			Long parentOrgId = obj.isNull("parentOrgId") ? 0 : obj.getLong("parentOrgId");
			Long healthLevelId = obj.isNull("healthLevelId") ? 0 : obj.getLong("healthLevelId");
			Long securityLevel = obj.isNull("securityLevel") ? 0 : obj.getLong("securityLevel");
			Boolean isPublic = obj.isNull("isPublic") ? false : obj.getBoolean("isPublic");
			
			Org parentOrg = orgService.getDataById(parentOrgId);
			String parentOrgName = "";
			if (parentOrg != null) {
				parentOrgName = parentOrg.getName();
			}
			String memberAccount = obj.isNull("memberAccount") ? "" : obj.getString("memberAccount");
			String memberName = obj.isNull("memberName") ? "" : obj.getString("memberName");
			String memberEmail = obj.isNull("memberEmail") ? "" : obj.getString("memberEmail");
			String memberMobilePhone = obj.isNull("memberMobilePhone") ? "" : obj.getString("memberMobilePhone");
			Date now = new Date();

			Member existMember = memberService.getMemberByAccount(memberAccount);
			if (existMember == null) {
				Org org = orgService.findByCode(orgCode);
				if (org == null) {
					// Create Org Info
					Healthcare healthcare = healthcareService.getByCode(orgCode);
					String ciLevel = "0";
					if (healthcare != null) {
						if (healthcare.getIsCI() != null) {
							if (healthcare.getIsCI()) {
								ciLevel = "2";
							} else {
								ciLevel = "0";
							}
						}
					}
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
					org.setIsApply(false);
					org.setSecurityLevel(securityLevel);
					org.setHealthLevelId(healthLevelId);
					org.setIsPublic(isPublic);
					orgDAO.insert(org);

					// Create Org Sign 權責
					if (parentOrg != null) {
						OrgSign orgSign = new OrgSign();
						orgSign.setOrgId(org.getId());
						orgSign.setParentOrgId(parentOrgId);
						orgSign.setWarningIsExamine(2);
						orgSign.setNotificationIsExamine(2);
						orgSign.setNotificationClosingIsExamine(2);
						orgSign.setCreateId((long) 1);
						orgSign.setCreateTime(now);
						orgSign.setModifyId((long) 1);
						orgSign.setModifyTime(now);
						orgSignDAO.insert(orgSign);
					}

					// Create Org Sign 地方
					{
						Org parentLocalOrg = orgService.findByCity(orgCity, OrgType.Authority, AuthType.Local);
						if (parentLocalOrg != null) {
							OrgSign orgSign = new OrgSign();
							orgSign.setOrgId(org.getId());
							orgSign.setParentOrgId(parentLocalOrg.getId());
							orgSign.setWarningIsExamine(2);
							orgSign.setNotificationIsExamine(2);
							orgSign.setNotificationClosingIsExamine(2);
							orgSign.setCreateId((long) 1);
							orgSign.setCreateTime(now);
							orgSign.setModifyId((long) 1);
							orgSign.setModifyTime(now);
							orgSignDAO.insert(orgSign);
						}
					}
				}

				// Create Member Info
				Member member = new Member();
				member.setAccount(memberAccount);
				member.setOrgId(org.getId());
				member.setName(memberName);
				member.setEmail(memberEmail);
				member.setMobilePhone(memberMobilePhone);
				member.setIsEnable(false);
				member.setEnableTime(WebDatetime.parse(WebDatetime.MAX_DATETIME));
				member.setCreateId((long) 1);
				member.setCreateTime(now);
				member.setModifyId((long) 1);
				member.setModifyTime(now);
				memberDAO.insert(member);

				// Create pdf & Send Mail
				List<WebMailAttachment> attachements = new ArrayList<WebMailAttachment>();
				WebMailAttachment attachement = new WebMailAttachment();
				Document document = new Document(PageSize.A4);

				BaseFont baseFont;
				try {
					baseFont = BaseFont.createFont(WebConfig.PDF_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				} catch (Exception e) {
					baseFont = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1257, BaseFont.EMBEDDED);
				}
				Font fontTitle = new Font(baseFont, 13, Font.NORMAL);
				Font fontContent = new Font(baseFont, 11, Font.NORMAL);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, stream);
				document.open();
				PdfContentByte cb = writer.getDirectContent();

				{
					Paragraph p = new Paragraph("", fontContent);
					p.add(new Chunk(new Chunk(new VerticalPositionMark())));
					p.add("機密等級:                           ");
					document.add(p);
				}
				{

					PdfPTable table = new PdfPTable(4);
					table.setWidthPercentage(100);
					table.setWidths(new int[]{1, 1, 1, 1});
					table.addCell(new PdfPCell(new Paragraph("制定單位", fontContent)));
					table.addCell(new PdfPCell(new Paragraph("文件名稱", fontContent)));
					table.addCell(new PdfPCell(new Paragraph("表單編號", fontContent)));
					table.addCell(new PdfPCell(new Paragraph("版次", fontContent)));

					table.addCell(new PdfPCell(new Paragraph("H-ISAC", fontContent)));
					table.addCell(new PdfPCell(new Paragraph(WebMessage.getMessage("orgSingUpData", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(new PdfPCell(new Paragraph(" ", fontContent)));
					table.addCell(new PdfPCell(new Paragraph("V 1.0", fontContent)));
					document.add(table);
				}
				{
					Paragraph p = new Paragraph("", fontContent);
					p.add(new Chunk(new Chunk(new VerticalPositionMark())));
					p.add("流水號:" + WebDatetime.toString(now, "yyyyMMddHHmmssSSS"));
					document.add(p);
				}

				Paragraph p = new Paragraph();
				p = new Paragraph(WebMessage.getMessage("orgSingUpData", null, Locale.TRADITIONAL_CHINESE), fontTitle);
				document.add(p);

				{
					PdfPTable table = new PdfPTable(3);
					table.setWidthPercentage(100);
					table.setWidths(new int[]{1, 1, 3});
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(new Paragraph("醫事機構資訊", fontTitle)));
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Code", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgCode + "                        為醫事系統核准之醫事機構", fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Name", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgName, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("機構地址", fontContent)));
					cell.setRowspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgCity", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgCity, fontContent)));
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgTown", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgTown, fontContent)));
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType4Address", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgAddress, fontContent)));
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("衛生主管機關", fontTitle)));
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType3SuperviseName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(parentOrgName, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("orgType3LocalName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					//cell = new PdfPCell(new Phrase(new Paragraph("(所在地縣、市)衛生局", fontContent)));
					String parentOrgName2 = "";
					ViewParentOrg parentOrg2 = orgService.getLocalParentOrg(org.getId());
					if (parentOrg2 != null) {
						parentOrgName2 = parentOrg2.getName();
					}
					cell = new PdfPCell(new Phrase(new Paragraph(parentOrgName2, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("系統管理者資訊", fontTitle)));
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminAccount", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(memberAccount, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(memberName, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminEmail", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(memberEmail, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("memberAdminMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(memberMobilePhone, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgBossName, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossEmail", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgBossEmail, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("bossMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgBossMobilePhone, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("principalName", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgPrincipalName, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph(WebMessage.getMessage("principalMobilePhone", null, Locale.TRADITIONAL_CHINESE), fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph(orgPrincipalMobilePhone, fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					Phrase pr = new Phrase();
					pr.add(new Paragraph("請於此處加蓋機構關防", fontContent));
					pr.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n", fontContent));
					pr.add(new Paragraph("申請日期:" + WebDatetime.toString(now, "yyyy年MM月dd日"), fontContent));
					cell = new PdfPCell(pr);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("(本欄由H-ISAC填寫)", fontTitle)));
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("審核人員", fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph("", fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(new Paragraph("審核日期", fontContent)));
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(new Paragraph("", fontContent)));
					cell.setColspan(2);
					table.addCell(cell);

					document.add(table);
				}

				document.add(new Paragraph("備註: ", fontTitle));
				document.add(new Paragraph("1. 新會員機構於H-ISAC網站申請會員註冊，請確認申請資料，加蓋機構關防(免備文)，正本寄至衛生福利部資訊處(地址：11558 台北市南港區忠孝東路6段488號)。信封註明申請H-ISAC會員，H-ISAC將於收件後3工作天內開通帳號。", fontContent));

				Phrase footer = new Phrase("□永久保存  ■定期保存    年", fontContent);
				ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, document.right() - 120 + document.leftMargin(), document.bottom() - 10, 0);

				document.close();
				attachement.setAttachmentName("HisacSignUp.pdf");
				attachement.setAttachmentBody(stream.toByteArray());
				attachements.add(attachement);
				String mailSubject = resourceMessageService.getMessageValue("mailMemberSignUpSubject");
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMemberSignUpBody"), orgName);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), myFilter.filterEmail(memberEmail), null, null, mailSubject, mailBody, attachements);
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("memberSignUpSuccess", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("memberSignUpFail", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} catch (Exception e) {
			//e.printStackTrace();
			responseJson.put("success", false);
			responseJson.put("msg", WebMessage.getMessage("memberSignUpFail", null, locale));
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 登入驗證
	 * 
	 * @param locale
	 *            Locale
	 * @param authentication
	 *            Authentication
	 * @param session
	 *            HttpSession
	 * @param request
	 *            HttpServletRequest
	 * @param account
	 *            帳號
	 * @param code
	 *            密碼
	 * @param otp
	 *            一次性密碼
	 * @param gtp
	 *            recaptcha token
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String Login(Locale locale, Authentication authentication, HttpSession session, HttpServletRequest request, @RequestParam String account, @RequestParam String code, @RequestParam String otp, @RequestParam String gtp) {
		JSONObject responseJson = new JSONObject();
		Integer otpCode = null;
		Base64.Decoder decoder = Base64.getDecoder();
		try {
			code = new String(decoder.decode(code), StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			code = "";
		}
		try {
			otpCode = Integer.parseInt(otp);
		} catch (Exception e) {
			otpCode = null;
		}
		if (!WebConfig.ENABLE_GOOGLE_RECAPTCHA && memberService.isAuth(account, code)) {
			session.invalidate();
			HttpSession newSession = request.getSession();
			if (newSession != null) {
				Member member = memberService.getMemberByAccount(account);
				Org org = orgService.getDataById(member.getOrgId());
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
				JSONObject memberInformation = new JSONObject();
				memberInformation.put("Id", member.getId());
				memberInformation.put("Name", member.getName());
				memberInformation.put("Ip", baseIpAddress);
				memberInformation.put("OrgType", org.getOrgType());
				memberInformation.put("AuthType", org.getAuthType());
				memberInformation.put("CILevel", org.getCiLevel() == null ? "0" : org.getCiLevel());
				memberInformation.put("OrgId", org.getId());
				memberInformation.put("MemberAccount", member.getAccount());
				
				updatedAuthorities.add(new SimpleGrantedAuthority(memberInformation.toString()));
				User user = new User(account, WebCrypto.getHash(WebCrypto.HashType.SHA512, code), true, true, true, true, updatedAuthorities);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), updatedAuthorities);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(newAuth);
				if (member != null && member.getIsEnable()) {
					Date now = new Date();					
					String mailSubject = resourceMessageService.getMessageValue("mailLoginSubject");
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailLoginBody"), member.getName(), WebDatetime.toString(now), baseIpAddress);
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				}
				memberService.resetErrorCount(member.getId(), member.getId());
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
				responseJson.put("url", "./pub/");
				systemCounterService.insert(baseIpAddress);
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Success, baseIpAddress, "");
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("loginFail", null, locale));
				responseJson.put("url", "./");
				systemLogService.insert(baseControllerName, baseActionName, account + " Session Exist", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
			}
		} else if (gtp.isEmpty() && memberService.isAuth(account, code)) {
			
			Member member = memberService.getMemberByAccount(account);
			memberService.resetErrorCount(member.getId(), member.getId());
			
			if (member != null && member.getIsEnable()) {
				Date now = new Date();					
				String mailSubject = resourceMessageService.getMessageValue("mailLoginSubject");
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailLoginBody"), member.getName(), WebDatetime.toString(now), baseIpAddress);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
			responseJson.put("url", "");
		} else if (!gtp.isEmpty() && memberService.isAuth(account, code, gtp, baseIpAddress)) {
			session.invalidate();
			HttpSession newSession = request.getSession();
			if (newSession != null) {
				Member member = memberService.getMemberByAccount(account);
				Org org = orgService.getDataById(member.getOrgId());
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
				JSONObject memberInformation = new JSONObject();
				memberInformation.put("Id", member.getId());
				memberInformation.put("Name", member.getName());
				memberInformation.put("Ip", baseIpAddress);
				memberInformation.put("OrgType", org.getOrgType());
				memberInformation.put("AuthType", org.getAuthType());
				memberInformation.put("CILevel", org.getCiLevel() == null ? "0" : org.getCiLevel());
				memberInformation.put("OrgId", org.getId());
				memberInformation.put("MemberAccount", member.getAccount());

				updatedAuthorities.add(new SimpleGrantedAuthority(memberInformation.toString()));
				User user = new User(account, WebCrypto.getHash(WebCrypto.HashType.SHA512, code), true, true, true, true, updatedAuthorities);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), updatedAuthorities);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(newAuth);
				memberService.resetErrorCount(member.getId(), member.getId());
				
				if (member != null && member.getIsEnable()) {
					Date now = new Date();					
					String mailSubject = resourceMessageService.getMessageValue("mailLoginSubject");
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailLoginBody"), member.getName(), WebDatetime.toString(now), baseIpAddress);
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				}
				
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
				responseJson.put("url", "./pub/");
				systemCounterService.insert(baseIpAddress);
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Success, baseIpAddress, "");
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("loginFail", null, locale));
				responseJson.put("url", "./");
				systemLogService.insert(baseControllerName, baseActionName, account + " Session Exist", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
			}
		} else if (otp.isEmpty() && memberService.isAuth(account, code)) {
			Member member = memberService.getMemberByAccount(account);
			memberService.resetErrorCount(member.getId(), member.getId());
			MemberHistory memberHistory = memberService.getMemberHistoryByMemberId(member.getId());
			Integer newOtp = null;
			try {
				WebTOTPGenerator totp = new WebTOTPGenerator();
				String salt = memberHistory.getSalt() + "," + WebCrypto.generateUUID();
				List<String> arraySalt = Arrays.asList(salt.split(","));
				salt = arraySalt.iterator().next();
				Key secretKey = new SecretKeySpec(salt.getBytes(), totp.getAlgorithm());
				newOtp = totp.generateOneTimePassword(secretKey, memberHistory.getModifyTime());
				String mailSubject = resourceMessageService.getMessageValue("mailOTPSubject");
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailOTPBody"), newOtp.toString());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			responseJson.put("success", true);
			responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
			responseJson.put("url", "");
		} else if (!otp.isEmpty() && memberService.isAuth(account, code, otpCode)) {
			session.invalidate();
			HttpSession newSession = request.getSession();
			if (newSession != null) {
				Member member = memberService.getMemberByAccount(account);
				Org org = orgService.getDataById(member.getOrgId());
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
				JSONObject memberInformation = new JSONObject();
				memberInformation.put("Id", member.getId());
				memberInformation.put("Name", member.getName());
				memberInformation.put("Ip", baseIpAddress);
				memberInformation.put("OrgType", org.getOrgType());
				memberInformation.put("AuthType", org.getAuthType());
				memberInformation.put("CILevel", org.getCiLevel() == null ? "0" : org.getCiLevel());
				memberInformation.put("OrgId", org.getId());
				memberInformation.put("MemberAccount", member.getAccount());

				updatedAuthorities.add(new SimpleGrantedAuthority(memberInformation.toString()));
				User user = new User(account, WebCrypto.getHash(WebCrypto.HashType.SHA512, code), true, true, true, true, updatedAuthorities);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), updatedAuthorities);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(newAuth);
				memberService.resetErrorCount(member.getId(), member.getId());
				if (member != null && member.getIsEnable()) {
					Date now = new Date();					
					String mailSubject = resourceMessageService.getMessageValue("mailLoginSubject");
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailLoginBody"), member.getName(), WebDatetime.toString(now), baseIpAddress);
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				}
				
				responseJson.put("success", true);
				responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
				responseJson.put("url", "./pub/");
				systemCounterService.insert(baseIpAddress);
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Success, baseIpAddress, "");
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("loginFail", null, locale));
				responseJson.put("url", "./");
				systemLogService.insert(baseControllerName, baseActionName, account + " Session Exist", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
			}
		} else {
			Member member = memberService.getMemberByAccount(account);
			if (member == null) {
				// 帳號不存在
				responseJson.put("success", false);
				responseJson.put("url", "./");
				responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
				systemLogService.insert(baseControllerName, baseActionName, account + " is not exist", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
			} else {
				MemberHistory memberHistory = memberService.getMemberHistoryByMemberId(member.getId());
				if (memberHistory == null) {
					// 審核中
					responseJson.put("success", false);
					responseJson.put("url", "");
					responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
					systemLogService.insert(baseControllerName, baseActionName, account + " is wait to apply", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
				} else {
					// if (!otp.isEmpty()) {
					// // OTP 過期或錯誤
					// responseJson.put("success", false);
					// responseJson.put("url", "");
					// responseJson.put("msg",
					// WebMessage.getMessage("loginFailOtp", null, locale));
					// } else if (memberHistory.getErrorCount() == -1) {
					if (memberHistory.getErrorCount() == -1) {
						// 已審核 等待啟用 設定密碼
						responseJson.put("success", false);
						responseJson.put("url", "");
						responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, account + " is wait to change password", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
					} else if (member.getIsEnable() == false) {
						// 已停用
						responseJson.put("success", false);
						responseJson.put("url", "");
						responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
						systemLogService.insert(baseControllerName, baseActionName, account + " was disabled to login", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
					} else if (member.getEnableTime().compareTo(new Date()) > 0) {
						// 密碼超過錯誤次數後無法登入的時間
						responseJson.put("success", false);
						responseJson.put("url", "");
						Object[] messageArgs = new Object[1];	
						messageArgs[0] = WebConfig.LOCK_MINUTES;
						responseJson.put("msg", WebMessage.getMessage("loginFailOverMaxErrorTimes", null, locale) + WebMessage.getMessage("loginTryAgainAfterMaxErrorTimes", messageArgs, locale));
						systemLogService.insert(baseControllerName, baseActionName, account + " was locked to login", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
					} else {
						// 帳號密碼錯誤
						if (memberHistory.getErrorCount() < Integer.parseInt(resourceMessageService.getMessageValue("loginMaxErrorTimes"))) {
							memberService.addErrorCount(member.getId(), member.getId());
							responseJson.put("success", false);
							responseJson.put("url", "");
							responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
							systemLogService.insert(baseControllerName, baseActionName, account + " password error", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
						} else {
							memberService.resetEnableTime(member.getId(), member.getId());
							responseJson.put("success", false);
							responseJson.put("url", "");
							responseJson.put("msg", WebMessage.getMessage("loginFailAccountOrCode", null, locale));
							systemLogService.insert(baseControllerName, baseActionName, account + " locking to login", SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, baseIpAddress, "");
						}
					}
				}
			}
		}
		return responseJson.toString();
	}

	/**
	 * ChangeUser
	 * 
	 * @param locale
	 *            Locale
	 * @param session
	 *            HttpSession
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param account
	 *            account
	 * @return JSON Format String
	 */
	@RequestMapping(value = "/change_user", method = RequestMethod.POST)
	public @ResponseBody String ChangeUser(Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String account) {
		JSONObject responseJson = new JSONObject();
		Authentication nowAuth = SecurityContextHolder.getContext().getAuthentication();
		if (!(nowAuth instanceof AnonymousAuthenticationToken)) {
			if (!account.equals("") && memberService.isAccountExist(account) && WebConfig.DEVELOPMENT_MODE) {
				session.invalidate();
				HttpSession newSession = request.getSession();
				if (newSession != null) {
					Member member = memberService.getMemberByAccount(account);
					Org org = orgService.getDataById(member.getOrgId());
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
					JSONObject memberInformation = new JSONObject();
					memberInformation.put("Id", member.getId());
					memberInformation.put("Name", member.getName());
					memberInformation.put("Ip", baseIpAddress);
					memberInformation.put("OrgType", org.getOrgType());
					memberInformation.put("AuthType", org.getAuthType());
					memberInformation.put("CILevel", org.getCiLevel() == null ? "0" : org.getCiLevel());
					memberInformation.put("OrgId", org.getId());
					memberInformation.put("MemberAccount", member.getAccount());

					updatedAuthorities.add(new SimpleGrantedAuthority(memberInformation.toString()));
					User user = new User(account, WebCrypto.getHash(WebCrypto.HashType.SHA512, WebCrypto.getRandomCode(12)), true, true, true, true, updatedAuthorities);
					Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), updatedAuthorities);
					SecurityContext securityContext = SecurityContextHolder.getContext();
					securityContext.setAuthentication(newAuth);
					responseJson.put("success", true);
					responseJson.put("msg", WebMessage.getMessage("loginSuccess", null, locale));
					responseJson.put("url", "./pub/");
					systemCounterService.insert(baseIpAddress);
					systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					return responseJson.toString();
				} else {
					responseJson.put("success", false);
					responseJson.put("msg", WebMessage.getMessage("loginFail", null, locale));
					responseJson.put("url", "./");
					systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					return responseJson.toString();
				}
			} else {
				responseJson.put("success", false);
				responseJson.put("msg", WebMessage.getMessage("loginFail", null, locale));
				responseJson.put("url", "./");
				systemLogService.insert(baseControllerName, baseActionName, account, SystemLogVariable.Action.Login, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				return responseJson.toString();
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return responseJson.toString();
	}

	/**
	 * 取得最新消息資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料
	 */
	@RequestMapping(value = "/query/news", method = RequestMethod.POST)
	public String QueryNews(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		// if (newsManagementService.getGlobalDataPublic() == null) {
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("PostType", "1");
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
		obj.put("Status", "4");
		obj.put("sort", "sort");
		json = obj.toString();
		List<ViewNewsManagementMember> newsManagements = newsManagementService.getSpList(json);
		if (newsManagements != null) {
			for (ViewNewsManagementMember newsManagement : newsManagements) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", newsManagement.getId());
				sn_json.put("Date", WebDatetime.toString(newsManagement.getPostDateTime(), "yyyy-MM-dd"));
				sn_json.put("Title", newsManagement.getTitle());
				sn_array.put(sn_json);
			}
		}
		listjson.put("total", newsManagementService.getSpListSize(json));
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
		// newsManagementService.setGlobalDataPublic(listjson.toString());
		// } else {
		// systemLogService.insert(baseControllerName, baseActionName, json,
		// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
		// baseIpAddress, baseMemberAccount);
		// model.addAttribute("json",
		// newsManagementService.getGlobalDataPublic());
		// }
		return "msg";
	}

	/**
	 * 取得活動訊息資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料
	 */
	@RequestMapping(value = "/query/activity", method = RequestMethod.POST)
	public String QueryActivity(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		// if (activityManagementService.getGlobalDataPublic() == null) {
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
		obj.put("Status", "4");
		obj.put("sort", "sort");
		json = obj.toString();
		List<ViewActivityManagementMember> activityManagements = activityManagementService.getSpList(json);
		if (activityManagements != null) {
			for (ViewActivityManagementMember activityManagement : activityManagements) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", activityManagement.getId());
				sn_json.put("Date", WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd"));
				sn_json.put("Title", activityManagement.getTitle());
				sn_array.put(sn_json);
			}
		}
		listjson.put("total", activityManagementService.getSpListSize(json));
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
		// activityManagementService.setGlobalDataPublic(listjson.toString());
		// } else {
		// systemLogService.insert(baseControllerName, baseActionName, json,
		// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
		// baseIpAddress, baseMemberAccount);
		// model.addAttribute("json",
		// activityManagementService.getGlobalDataPublic());
		// }
		return "msg";
	}
	
	/**
	 * 取得勒索專區文章資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 勒索專區文章
	 */
	
	@RequestMapping(value = "/query/malware", method = RequestMethod.POST)
	public String QueryMalware(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
		obj.put("Status", "4");
		obj.put("sort", "sort");
		json = obj.toString();
		
		List<ViewMalwareManagementMember> malwareManagements = malwareManagementService.getSpList(json);
		if (malwareManagements != null) {
			for (ViewMalwareManagementMember malwareManagement : malwareManagements) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", malwareManagement.getId());
				sn_json.put("Date", WebDatetime.toString(malwareManagement.getPostDateTime(), "yyyy-MM-dd"));
				sn_json.put("Title", malwareManagement.getTitle());
				sn_json.put("Content", malwareManagement.getContent());
				sn_array.put(sn_json);
			}
			
			
			
		}
		
		listjson.put("total", activityManagementService.getSpListSize(json));
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
		// activityManagementService.setGlobalDataPublic(listjson.toString());
		// } else {
		// systemLogService.insert(baseControllerName, baseActionName, json,
		// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
		// baseIpAddress, baseMemberAccount);
		// model.addAttribute("json",
		// activityManagementService.getGlobalDataPublic());
		// }
		return "msg";

		
		
	}

		

	
	
	/**
	 * 取得QA資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return QA資料
	 */
	@RequestMapping(value = "/query/qa", method = RequestMethod.POST)
	public String QueryQA(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		// if (newsManagementService.getGlobalDataPublic() == null) {
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		List<ViewQAManagementGroup> qaManagements = qaManagementService.getList(obj.toString());
		if (qaManagements != null) {
			for (ViewQAManagementGroup qaManagement : qaManagements) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", qaManagement.getId());
				sn_json.put("QAMgName", qaManagement.getQAMgName());
				sn_json.put("QName", qaManagement.getQName());
				sn_array.put(sn_json);
			}
		}
		listjson.put("total", qaManagementService.getListSize(obj.toString()));
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,  getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());		
		return "msg";
	}
	
	/**
	 * 改變權限API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            roleId
	 * @return 成功與否
	 */
	@RequestMapping(value = "/changeRole", method = RequestMethod.POST)
	public String ChangeRole(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();	
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();						
		JSONObject obj = new JSONObject(json);
		Long roleId = obj.isNull("RoleId") == true ? null : obj.getLong("RoleId");		
		List<MemberRole> memberRoles = memberRoleService.getAllByMemberId(getBaseMemberId());
		if (memberRoles != null)
			for (MemberRole memberRole : memberRoles) {
				if (memberRole.getRoleId() == roleId)
					memberRoleService.changeRole(getBaseMemberId(), roleId, true);				
				else if(memberRole.getIsEnable())
					memberRoleService.changeRole(getBaseMemberId(), memberRole.getRoleId(), false);
			}
		responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
		responseJson.put("success", true);	
		responseJson.put("webSiteUrl", WebConfig.WEB_SITE_URL);	
		HttpSession session = request.getSession();
		if (session.getAttribute("menuJson")!=null)
			session.removeAttribute("menuJson");
		model.addAttribute("json", responseJson.toString());		
		return "msg";
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
	@RequestMapping(value = "/getPasswordLength", method = RequestMethod.POST)
	public String GetPasswordLength(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray arrjson = new JSONArray();	
		arrjson.put(resourceMessageService.getMessageValue("passwordLength"));
		model.addAttribute("json", arrjson.toString());		
		return "msg";
	}
}
