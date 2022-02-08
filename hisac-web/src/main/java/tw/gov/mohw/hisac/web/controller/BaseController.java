package tw.gov.mohw.hisac.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import tw.gov.mohw.hisac.web.SessionManager;
import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.WebNet;
import tw.gov.mohw.hisac.web.dao.MemberRoleVariable;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable.Action;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable.Status;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.ViewMenuLimit;
import tw.gov.mohw.hisac.web.filter.MyFilter;
import tw.gov.mohw.hisac.web.service.SystemLogService;
import tw.gov.mohw.hisac.web.service.MemberRoleService;
import tw.gov.mohw.hisac.web.service.MemberService;
import tw.gov.mohw.hisac.web.service.MenuService;
import tw.gov.mohw.hisac.web.service.ResourceMessageService;
import tw.gov.mohw.hisac.web.service.SystemCounterService;

public class BaseController {
	final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	
	//改成private 方便修改成利用 function取得資料
	private String baseMemberAccount = "";
	private String baseMemberName = "";
	private Long baseMemberId = null;
	private String baseOrgType = "";
	private String baseAuthType = "";
	private String baseCILevel = "";
	private Long baseOrgId = null;
	private String baseIp = "";

	protected String baseControllerName = "";
	protected String baseActionName = "";
	private String baseSubsystemName = "";
	private String baseAppName = "";
	protected String baseIpAddress = "";
	private Boolean baseActionCreate = false;
	private Boolean baseActionUpdate = false;
	private Boolean baseActionDelete = false;
	private Boolean baseActionRead = false;
	private Boolean baseActionSign = false;
	private String baseMenuJson = "";
	private String baseLanguageCountry = "";
	private MemberRoleVariable baseIsMemberAdmin;

	@Autowired
	protected SystemLogService systemLogService;

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected SystemCounterService systemCounterService;

	@Autowired
	protected ResourceMessageService resourceMessageService;

	@Autowired
	protected MenuService menuService;

	@Autowired
	protected MemberRoleService memberRoleService;

	protected MemberRoleVariable baseMemberRole;
	
	protected List<MemberRole> memberRoles;

	protected final MyFilter myFilter = new MyFilter();
	protected final Base64.Encoder encoder = Base64.getEncoder();
	protected final Base64.Decoder decoder = Base64.getDecoder();

	/**
	 * The Global Attributes for View
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param model
	 *            Model
	 */
	@ModelAttribute
	public void addAttributes(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		baseControllerName = (String) session.getAttribute("controllerName");
		baseActionName = (String) session.getAttribute("actionName");
		baseLanguageCountry = WebMessage.getLanguageCountry(request.getLocale());

		String packageName = this.getClass().getPackage().getName();
		if (packageName.equals("tw.gov.mohw.hisac.web.controller")) {
			model.addAttribute("enableGoogleRecaptcha", WebConfig.ENABLE_GOOGLE_RECAPTCHA);
			model.addAttribute("googleRecaptchaSiteKey", WebConfig.GOOGLE_RECAPTCHA_SITE_KEY);
			model.addAttribute("nowUser", SessionManager.getUserCount());
			model.addAttribute("totalUser", systemCounterService.getTotal());
			resourceMessageService.loadResource();
			model.addAttribute("globalFooterDirectedBy", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterDirectedBy"), locale));
			model.addAttribute("globalFooterAddressText", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterAddressText"), locale));
			model.addAttribute("globalFooterAddressValue", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterAddressValue"), locale));
			model.addAttribute("globalFooterTelText", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterTelText"), locale));
			model.addAttribute("globalFooterTelValue", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterTelValue"), locale));
			model.addAttribute("globalFooterEmail", WebMessage.parseMessage(resourceMessageService.getMessageValue("globalFooterEmail"), locale));
			model.addAttribute("developmentMode", WebConfig.DEVELOPMENT_MODE);
			model.addAttribute("debugMode", WebConfig.DEBUG_MODE);
			model.addAttribute("languageCountry", baseLanguageCountry);

		}

		if (baseControllerName.equals("Index")) {
			return;
		}
		model.addAttribute("controllerName", baseControllerName);
		model.addAttribute("actionName", baseActionName);
		baseIpAddress = WebNet.getIpAddr(request);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				
				
//				JSONObject memberInformation = getBaseMemberInformation(request);

				baseMemberAccount = getBaseMemberAccount();
				baseMemberName = getBaseMemberName();
				baseMemberId = getBaseMemberId();
				baseOrgType = getBaseOrgType();
				baseAuthType = getBaseAuthType();
				baseCILevel = getBaseCILevel();
				baseOrgId = getBaseOrgId();
				baseIp = getBaseIpAddress();
				
				if (!(baseIp.trim().equals(baseIpAddress.trim()))) {
					try {
						Cookie cookie = new Cookie("JSESSIONID", "");
						cookie.setPath(request.getContextPath());
						cookie.setMaxAge(-1);
						response.addCookie(cookie);
						response.sendRedirect(request.getContextPath() + "/?g=" + WebCrypto.getRandomCode(8) + WebCrypto.generateUUID());
						return;
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
				baseMemberRole = memberRoleService.getMemberRoleVariable(baseMemberId);
				memberRoles = memberRoleService.getAllByMemberId(baseMemberId);
			}
			model.addAttribute("memberAccount", baseMemberAccount);
			model.addAttribute("memberName", baseMemberName);
			model.addAttribute("memberId", baseMemberId);
			model.addAttribute("orgType", baseOrgType);
			model.addAttribute("authType", baseAuthType);
			model.addAttribute("ciLevel", baseCILevel);
			model.addAttribute("orgId", baseOrgId);
			model.addAttribute("beforeSessionTimeoutMinutes", WebConfig.BEFORE_SESSION_TIMEOUT_MINUTES);
			
			
			//取得表單權限制
			//輔助追蹤權限
			ViewMenuLimit a01Action = menuService.getMenuByIdAndName(baseMemberId, "輔助追蹤");
			model.addAttribute("a01Action", a01Action.getActionRead());
			
			//技術網絡查詢
			ViewMenuLimit c01Action = menuService.getMenuByIdAndName(baseMemberId, "技術網絡查詢");
			model.addAttribute("c01Action", c01Action.getActionRead());
			//頂尖學者
			ViewMenuLimit c02Action = menuService.getMenuByIdAndName(baseMemberId, "頂尖學者");
			model.addAttribute("c02Action", c02Action.getActionRead());
			//頂尖機構
			ViewMenuLimit c03Action = menuService.getMenuByIdAndName(baseMemberId, "頂尖機構");
			model.addAttribute("c03Action", c03Action.getActionRead());
			
			//各國國際合作概況
			ViewMenuLimit k01Action = menuService.getMenuByIdAndName(baseMemberId, "各國國際合作概況");
			model.addAttribute("k01Action", k01Action.getActionRead());
			
			//人才活耀度
			ViewMenuLimit c04Action = menuService.getMenuByIdAndName(baseMemberId, "人才活耀度");
			model.addAttribute("c04Action", c04Action.getActionRead());
			
			//角色權限
			ViewMenuLimit s12Action = menuService.getMenuByIdAndName(baseMemberId, "角色權限資料維護");
			model.addAttribute("s12Action", s12Action.getActionRead());
			
			//人員基本資料管理
			ViewMenuLimit s05Action = menuService.getMenuByIdAndName(baseMemberId, "人員基本資料管理");
			model.addAttribute("s05Action", s05Action.getActionRead());
			
			//角色資料維護
			ViewMenuLimit s02Action = menuService.getMenuByIdAndName(baseMemberId, "角色資料維護");
			model.addAttribute("s02Action", s02Action.getActionRead());
			
			//隱私權保護政策頁面設定
			ViewMenuLimit s13Action = menuService.getMenuByIdAndName(baseMemberId, "隱私權保護政策頁面設定");
			model.addAttribute("s13Action", s13Action.getActionRead());

			//網站cookie設定管理
			ViewMenuLimit s15Action = menuService.getMenuByIdAndName(baseMemberId, "網站cookie設定管理");
			model.addAttribute("s15Action", s15Action.getActionRead());
			
			//版權宣告設定
			ViewMenuLimit s16Action = menuService.getMenuByIdAndName(baseMemberId, "版權宣告設定");
			model.addAttribute("s16Action", s16Action.getActionRead());

			//國際合作個案管理
			ViewMenuLimit i01Action = menuService.getMenuByIdAndName(baseMemberId, "國際合作個案管理");
			model.addAttribute("i01Action", i01Action.getActionRead());
			
			//影音資訊管理
			ViewMenuLimit i02Action = menuService.getMenuByIdAndName(baseMemberId, "影音資訊管理");
			model.addAttribute("i02Action", i02Action.getActionRead());

			//子系統資料維護
			ViewMenuLimit s01Action = menuService.getMenuByIdAndName(baseMemberId, "子系統資料維護");
			model.addAttribute("s01Action", s01Action.getActionRead());
			
			//表單資料維護
			ViewMenuLimit s11Action = menuService.getMenuByIdAndName(baseMemberId, "表單資料維護");
			model.addAttribute("s11Action", s11Action.getActionRead());
			
			//操作記錄
			ViewMenuLimit s03Action = menuService.getMenuByIdAndName(baseMemberId, "操作記錄");
			model.addAttribute("s03Action", s03Action.getActionRead());
			
		} catch (Exception e) {
			//logger.warn(e.getMessage());
		}

		if (packageName.equals("tw.gov.mohw.hisac.web.controller") && baseControllerName != null && baseActionName != null) {
			// For Controller Router
			ViewMenuLimit action = menuService.getAction(baseMemberId, baseControllerName, baseActionName);
			if (action != null) {
				baseActionCreate = action.getActionCreate();
				baseActionUpdate = action.getActionUpdate();
				baseActionDelete = action.getActionDelete();
				baseActionRead = action.getActionRead();
				baseActionSign = action.getActionSign();
			}
			if (!baseActionRead) {
				try {
					response.sendRedirect(request.getContextPath() + "/forbidden?g=" + WebCrypto.getRandomCode(8) + WebCrypto.generateUUID());
					return;
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			systemLogService.insert(baseControllerName, baseActionName, "", Action.PageLoad, Status.Success, baseIpAddress, baseMemberAccount);
			model.addAttribute("actionCreate", baseActionCreate);
			model.addAttribute("actionUpdate", baseActionUpdate);
			model.addAttribute("actionDelete", baseActionDelete);
			model.addAttribute("actionRead", baseActionRead);
			model.addAttribute("actionSign", baseActionSign);
			
			model.addAttribute("memberRoles", memberRoles);			

			/*
			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
			 * 10-會員機構聯絡人 11-會員機構管理者 16-主管機關
			 * 17.H-CERT審核者 (108/6/11 新增)
			 * 18.事件處理單位 (108/6/11 新增)
			 */

			model.addAttribute("isAdmin", baseMemberRole.IsAdmin); // 1-SuperAdmin
			model.addAttribute("isHisac", baseMemberRole.IsHisac); // 2-H-ISAC管理者
			model.addAttribute("isHisacContent", baseMemberRole.IsHisacContent); // 3-H-ISAC內容維護者
			model.addAttribute("isHisacContentSign", baseMemberRole.IsHisacContentSign); // 4-H-ISAC內容審核者
			model.addAttribute("isHisacInfoBuilder", baseMemberRole.IsHisacInfoBuilder); // 5-H-ISAC警訊建立者
			model.addAttribute("isHisacInfoSign", baseMemberRole.IsHisacInfoSign); // 6-H-ISAC警訊審核者
			model.addAttribute("isApplySign", baseMemberRole.IsApplySign); // 7-權責單位警訊審核者
			model.addAttribute("isApplyContact", baseMemberRole.IsApplyContact); // 8-權責單位聯絡人
			model.addAttribute("isApplyAdmin", baseMemberRole.IsApplyAdmin); // 9-權責單位管理者
			model.addAttribute("isMemberContact", baseMemberRole.IsMemberContact); // 10-會員機構連絡人
			model.addAttribute("isMemberAdmin", baseMemberRole.IsMemberAdmin); // 11-會員機構管理者
			model.addAttribute("isHisacNotifySign", baseMemberRole.IsHisacNotifySign); // 12-hisac通報審核者
			model.addAttribute("isHisacIXContent", baseMemberRole.IsHisacIXContent); // 13-情資維護者
			model.addAttribute("isHisacIXContentSign", baseMemberRole.IsHisacIXContentSign); // 14-情資審核者
			model.addAttribute("isApplySingAdmin", baseMemberRole.IsApplySingAdmin); // 15-權責通報審核者
			model.addAttribute("isHisacBoss", baseMemberRole.IsHisacBoss); // 16-主管機關
			model.addAttribute("isHCERTContentSign", baseMemberRole.IsHCERTContentSign); // 17.H-CERT審核者 (108/6/11 新增)
			model.addAttribute("isEventHandlingUnitContact", baseMemberRole.IsEventHandlingUnitContact); // 18.事件處理單位聯絡人 (108/6/11 新增)
			model.addAttribute("isMaintainInspectCommittee", baseMemberRole.IsMaintainInspectCommittee); // 20.資安維護計畫稽核委員 (108/6/11 新增)
			model.addAttribute("isPmo", baseMemberRole.IsPmo); // 21.資安維護計畫PMO (108/8/24 新增)
			model.addAttribute("isSystemDeveloper", baseMemberRole.IsSystemDeveloper); // 22.系統開發人員 (108/8/24 新增)
			
			baseAppName = menuService.getFormName(baseControllerName, baseActionName);
			baseSubsystemName = menuService.getSubsystemName(baseControllerName);
			model.addAttribute("appName", WebMessage.parseMessage(baseAppName, locale));
			model.addAttribute("subsystemName", WebMessage.parseMessage(baseSubsystemName, locale));			
			if (session.getAttribute("menuJson") == null) {
				List<ViewMenuLimit> viewMenuLimits = menuService.getMenu(baseMemberId);
				List<ViewMenuLimit> subsystems = viewMenuLimits.stream().filter(distinctByKey(p -> p.getSubsytemId())).collect(Collectors.toList());
				JSONObject menuJson = new JSONObject();
				JSONArray arrMenuJson = new JSONArray();
				if (subsystems != null) {
					for (ViewMenuLimit subsystem : subsystems) {						
							List<ViewMenuLimit> separator = viewMenuLimits.stream().filter(p -> p.getSubsytemId().equals(subsystem.getSubsytemId()) && !p.getFormCode().equals("separator") && !p.getFormName().equals("separator"))
									.collect(Collectors.toList());
							if (separator.size() == 0) {
								continue;
							}
							JSONObject menuJsonParent = new JSONObject();
							menuJsonParent.put("id", subsystem.getSubsytemId());
							menuJsonParent.put("iconStyle", subsystem.getIconStyle());
							menuJsonParent.put("link", WebMessage.parseMessage(subsystem.getSubsystemName(), locale));
							menuJsonParent.put("code", "subsystem_" + subsystem.getSubsystemCode());

							List<ViewMenuLimit> forms = viewMenuLimits.stream().filter(p -> p.getSubsytemId().equals(subsystem.getSubsytemId())).collect(Collectors.toList());
							if (forms != null) {
								int formSize = forms.size();
								boolean separatorCheck = false;
								int menuIndex = 0;
								for (ViewMenuLimit form : forms) {
									menuIndex++;
									if (formSize == menuIndex && form.getFormCode().equals("separator") && form.getFormName().equals("separator")) {
										continue;
									}
									if (separatorCheck == false && form.getFormCode().equals("separator") && form.getFormName().equals("separator")) {
										continue;
									} else {
										JSONObject menuJsonChildren = new JSONObject();
										if (form.getFormCode().equals("separator") && form.getFormName().equals("separator")) {
											separatorCheck = false;
											menuJsonChildren.put("id", form.getFormId());
											menuJsonChildren.put("value", "separator");
											menuJsonChildren.put("separator", true);
										} else {
											separatorCheck = true;
											menuJsonChildren.put("id", form.getFormId());
											if (form.getControllerName().isEmpty()) {
												menuJsonChildren.put("value", "/" + form.getActionName().toLowerCase());
											} else {
												menuJsonChildren.put("value", "/" + form.getControllerName().toLowerCase() + "/" + form.getActionName().toLowerCase());
											}
											if (form.getIsExternalLink()) {
												menuJsonChildren.put("target", form.getFormCode());
											}
											if (WebConfig.DEVELOPMENT_MODE) {
												menuJsonChildren.put("link", form.getActionName().toLowerCase() + "_" + WebMessage.parseMessage(form.getFormName(), locale) + "_" + form.getFormCode());
											} else {
												menuJsonChildren.put("link", WebMessage.parseMessage(form.getFormName(), locale));
											}
										}
										menuJsonChildren.put("code", "form_" + form.getFormCode());
										menuJsonParent.append("children", menuJsonChildren);
									}
								}
							}
							arrMenuJson.put(menuJsonParent);						
					}
					menuJson.put("menu", arrMenuJson);
				}
				String strMenuJson = "";
				try {
					byte[] _byteMenuJson = menuJson.toString().getBytes(StandardCharsets.UTF_8.toString());
					strMenuJson = encoder.encodeToString(_byteMenuJson);
				} catch (Exception e) {
					//e.printStackTrace();
				}
				session.setAttribute("menuJson", myFilter.stripXSS(strMenuJson));
				model.addAttribute("menuJson", myFilter.stripXSS(strMenuJson));
				System.out.println(myFilter.stripXSS(strMenuJson));
			} else {
				model.addAttribute("menuJson", myFilter.stripXSS(session.getAttribute("menuJson").toString()));
			}
		} else {
			// TODO: For Controller RESTFul API
		}
	}
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	/**
	 * The Global Catch Error Code
	 * 
	 * @param httpRequest
	 *            HttpServletRequest
	 * @return Error Code
	 */
	public int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
	
	/**
	 * Check user has logged in or not.
	 * 
	 * @return true if is logged in, otherwise return false.
	 */
	protected boolean isUserLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (null != auth && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken));
	}
	
	
	/**
	 * 取得當前登入者之使用者資訊
	 * 
	 * @param request
	 *            HttpServletRequest request
	 * @return A JSONObject of Member Information
	 */
	JSONObject getBaseMemberInformation(HttpServletRequest request) {
		if (isUserLoggedIn()) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
				UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				Iterator<?> iter = userDetail.getAuthorities().iterator();
				if (iter.hasNext()) {
					try {
						JSONObject memberInformation = new JSONObject(iter.next().toString());
						return null == request ? memberInformation
								: (WebNet.getIpAddr(request).equals(memberInformation.optString("Ip"))
										? memberInformation
										: new JSONObject());
					} catch (Exception e) {
						StringWriter errors = new StringWriter();
						// e.printStackTrace(new PrintWriter(errors));
						logger.error(errors.toString());
					}
				}
			}
		}
		return new JSONObject();
	}
	
	/**
	 * 取得當前登入者之使用者資訊
	 * 
	 * @return A JSONObject of Member Information
	 */
	JSONObject getBaseMemberInformation() {
		return getBaseMemberInformation(null);
	}
	
	
	protected String getBaseMemberAccount() {
		return getBaseMemberInformation().optString("MemberAccount", "");
	}
	
	
	protected String getBaseMemberName() {
		return getBaseMemberInformation().optString("Name", "");
	}
	
	protected Long getBaseMemberId() {
		return getBaseMemberInformation().optLong("Id", -1);
	}
	
	protected String getBaseOrgType() {
		return getBaseMemberInformation().optString("OrgType", "");
	}
	
	protected String getBaseAuthType() {
		return getBaseMemberInformation().optString("AuthType", "");
	}
	
	protected String getBaseCILevel() {
		return getBaseMemberInformation().optString("CILevel", "");
	}
	
	protected Long getBaseOrgId() {
		return getBaseMemberInformation().optLong("OrgId", -1);
	}
	
	protected String getBaseIpAddress() {
		return getBaseMemberInformation().optString("Ip", "");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Associates the specified value with the specified key in the map with which
	 * is exist and key is not null.
	 * 
	 * @param map
	 *            is not null and will to be put the key with the value into the map
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            to be associated with the specified key
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveMap(Map map, Object key, Object value) {
		if (null == map || null == key) {
			return;
		}
		map.put(key, value);
	}
}
