package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;
import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.LinksPic;
import tw.gov.mohw.hisac.web.domain.SpContactCountDashboard;
import tw.gov.mohw.hisac.web.domain.SpDashboard;
import tw.gov.mohw.hisac.web.domain.SpManagerCountDashboard;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMalwareManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewLinksMember;
import tw.gov.mohw.hisac.web.domain.ViewLinksPicMember;
import tw.gov.mohw.hisac.web.service.NewsManagementService;
import tw.gov.mohw.hisac.web.service.NotificationService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.SpDashboardService;
import tw.gov.mohw.hisac.web.service.ActivityManagementService;
import tw.gov.mohw.hisac.web.service.AnaManagementService;
import tw.gov.mohw.hisac.web.service.MalwareManagementService;

import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.InformationManagementService;
import tw.gov.mohw.hisac.web.service.WeaknessManagementService;
import tw.gov.mohw.hisac.web.service.IncidentService;
import tw.gov.mohw.hisac.web.service.MemberSignApplyService;
import tw.gov.mohw.hisac.web.service.MessageService;
import tw.gov.mohw.hisac.web.service.LinksService;
import tw.gov.mohw.hisac.web.service.MaintainPlanMemberService;
import tw.gov.mohw.hisac.web.service.LinksPicService;
import tw.gov.mohw.hisac.web.service.SystemLogService;

/**
 * 公開資訊index控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p00_IndexController extends BaseController {

	@Autowired
	private NewsManagementService newsManagementService;

	@Autowired
	private ActivityManagementService activityManagementService;

	@Autowired
	private AnaManagementService anaManagementService;

	@Autowired
	private WeaknessManagementService weaknessManagementService;
	
	@Autowired
	private InformationManagementService informationManagementService;

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private MemberSignApplyService memberSignApplyService;
	
	@Autowired
	private MalwareManagementService malwareManagementService;


	@Autowired
	private MessageService messageService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private LinksService linksService;

	@Autowired
	private LinksPicService linksPicService;

	@Autowired
	private InformationExchangeService informationExchangeService;

	@Autowired
	private SpDashboardService spDashboardService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private MaintainPlanMemberService maintainPlanMemberService;	
	
	private String targetControllerName = "pub";
	private String targetActionName = "index";

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
	@RequestMapping(value = "/p00/query/news", method = RequestMethod.POST)
	public String QueryNews(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (newsManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("PostType", "1");
				obj.put("IsEnable", true);
				obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
				obj.put("Status", "4");
				obj.put("sort", "sort");
				json = obj.toString();
				List<ViewNewsManagementMember> newsManagements = newsManagementService.getSpList(json); // 改用
																										// store
																										// procedure
				if (newsManagements != null) {
					for (ViewNewsManagementMember newsManagement : newsManagements) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", newsManagement.getId());
						sn_json.put("Date", WebDatetime.toString(newsManagement.getPostDateTime(), "yyyy-MM-dd"));
						sn_json.put("Title", newsManagement.getTitle());
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", newsManagementService.getSpListSize(json)); // 改用
																					// store
																					// procedure
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
//				newsManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", newsManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
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
	@RequestMapping(value = "/p00/query/activity", method = RequestMethod.POST)
	public String QueryActivity(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (activityManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);
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
//				activityManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", activityManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
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
	 * @return 勒索專區資料
	 */
	
	@RequestMapping(value = "/p00/query/malwares", method = RequestMethod.POST)
	public String QueryMalware(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (activityManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);
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
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", activityManagementService.getSpListSize(json));
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
//				activityManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", activityManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}

		


	/**
	 * 取得資安訊息情報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料
	 */
	@RequestMapping(value = "/p00/query/ana", method = RequestMethod.POST)
	public String QueryAna(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (anaManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);
				obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
				obj.put("Status", "4");
				obj.put("sort", "sort");
				json = obj.toString();
				List<ViewAnaManagementMember> anaManagements = anaManagementService.getSpList(json);
				if (anaManagements != null) {
					for (ViewAnaManagementMember anaManagement : anaManagements) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", anaManagement.getId());
						sn_json.put("Date", WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
						sn_json.put("Title", anaManagement.getIncidentTitle());
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", anaManagementService.getSpListSize(json));
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
//				anaManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", anaManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}

	/**
	 * 取得軟體弱點漏洞通告資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料
	 */
	@RequestMapping(value = "/p00/query/weakness", method = RequestMethod.POST)
	public String QueryWeakness(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (weaknessManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);
				obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
				obj.put("Status", "4");
				json = obj.toString();
				List<ViewWeaknessManagementMember> weaknessManagements = weaknessManagementService.getList(json);
				if (weaknessManagements != null) {
					for (ViewWeaknessManagementMember weaknessManagement : weaknessManagements) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", weaknessManagement.getId());
						sn_json.put("Date", WebDatetime.toString(weaknessManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
						sn_json.put("Title", weaknessManagement.getIncidentTitle());
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", weaknessManagementService.getListSize(json));
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
//				weaknessManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", weaknessManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}

	/**
	 * 取得SecBuzzer資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return SecBuzzer資料
	 */
	@RequestMapping(value = "/p00/query/secbuzzer", method = RequestMethod.POST)
	public String QuerySecBuzzer(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("start", 0);
			obj.put("maxRows", 5);
			obj.put("dir", true);
			obj.put("sort", "sort");			
			json = obj.toString();
			List<ViewInformationExchangeSecbuzzerTitle> informationExchanges = informationExchangeService.getSecBuzzerList(json);
			if (informationExchanges != null) {
				for (ViewInformationExchangeSecbuzzerTitle informationExchange : informationExchanges) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", informationExchange.getId());
					sn_json.put("Cve", "(" + informationExchange.getIncidentId() + ")");
					sn_json.put("Date", WebDatetime.toString(informationExchange.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Title", informationExchange.getIncidentTitle());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", informationExchangeService.getSecBuzzerListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}

	/**
	 * 取得情資分享資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料
	 */
	@RequestMapping(value = "/p00/query/inf", method = RequestMethod.POST)
	public String QueryInf(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			if (newsManagementService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);			
				obj.put("Status", 4);
				json = obj.toString();
				List<InformationManagement> informationManagements = informationManagementService.getList(json);
				if (informationManagements != null) {
					for (InformationManagement informationManagement : informationManagements) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", informationManagement.getId());					
						sn_json.put("Date", WebDatetime.toString(informationManagement.getPostDateTime(), "yyyy-MM-dd"));
						sn_json.put("Title", informationManagement.getTitle());
						
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", informationManagementService.getListSize(json)); // 改用
																					// store
																					// procedure
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
//				newsManagementService.setGlobalData(listjson.toString());
//			} else {
//				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//				model.addAttribute("json", newsManagementService.getGlobalData());
//			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}
	
	/**
	 * 取得相關連結資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 相關連結資料
	 */
	@RequestMapping(value = "/p00/query/links", method = RequestMethod.POST)
	public String QueryLinks(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (linksService.getGlobalData() == null) {
				JSONArray sn_array = new JSONArray();
				JSONObject obj = new JSONObject(json);
				obj.put("IsEnable", true);
				// obj.put("StartShowDateTime", WebDatetime.toString(new Date(),
				// "yyyy-MM-dd"));
				// obj.put("Status", "4");
				json = obj.toString();
				List<ViewLinksMember> links = linksService.getList(json);
				if (links != null) {
					for (ViewLinksMember link : links) {
						JSONObject sn_json = new JSONObject();
						sn_json.put("Id", link.getId());
						sn_json.put("Date", WebDatetime.toString(link.getStartDateTime(), "yyyy-MM-dd"));
						// sn_json.put("SourceLink", "<a
						// href=\""+link.getSourceLink()+"\"
						// target=\"_outer\">");
						sn_json.put("SourceLink", link.getSourceLink());
						// sn_json.put("SourceLinkEnd", "</a>");

						List<ViewLinksPicMember> linksPics = linksPicService.getAllByLinksId(link.getId());
						if (linksPics != null) {
							for (ViewLinksPicMember linksPic : linksPics) {
								// sn_json.put("ImageLink", "<img
								// src=\"./sys/api/s41/pic/download/"+link.getId()+"/"+linksPic.getId()+"\"
								// class=\"img-responsive\" />");
								sn_json.put("ImageLink", "./pub/api/p00/pic/download/" + link.getId() + "/" + linksPic.getId());
								sn_json.put("ImageTitle", linksPic.getFileDesc());
								// break;
							}
						}
						sn_array.put(sn_json);
					}
				}
				listjson.put("total", linksService.getListSize(json));
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", listjson.toString());
				linksService.setGlobalData(listjson.toString());
			} else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				model.addAttribute("json", linksService.getGlobalData());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		return "msg";
	}

	@RequestMapping(value = "/count/member_sign", method = RequestMethod.POST)
	public String CountMemberSign(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		long resultValue = memberSignApplyService.getListSize(new JSONObject().toString());
		countJson.put("count", resultValue);
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/not", method = RequestMethod.POST)
	public String CountNot(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisac == true) {
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacInfoBuilder == true) {
			obj.put("RoleId", 5);
		} else if (baseMemberRole.IsHisacInfoSign == true) {
			obj.put("RoleId", 6);
		} else if (baseMemberRole.IsApplySign == true) {
			obj.put("RoleId", 7);
		} else if (baseMemberRole.IsApplyContact == true) {
			obj.put("RoleId", 8);
		} else if (baseMemberRole.IsMemberContact == true) {
			obj.put("RoleId", 10);
		}

		obj.put("OrgId", getBaseOrgId());
		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", messageService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/alt", method = RequestMethod.POST)
	public String CountAlt(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisac == true) {
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacNotifySign == true) {
			obj.put("RoleId", 12);
		} else if (baseMemberRole.IsApplySingAdmin == true) {
			obj.put("RoleId", 15);
		} else if (baseMemberRole.IsMemberContact == true) {
			obj.put("RoleId", 10);
		}

		obj.put("OrgId", getBaseOrgId());
		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", notificationService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/cyb", method = RequestMethod.POST)
	public String CountCyb(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisac == true) {
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacIXContent == true) {
			obj.put("RoleId", 13);
		} else if (baseMemberRole.IsHisacIXContentSign == true) {
			obj.put("RoleId", 14);
		} else if (baseMemberRole.IsMemberContact == true) {
			obj.put("RoleId", 10);
		}

		obj.put("OrgId", getBaseOrgId());
		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", informationExchangeService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/news_management", method = RequestMethod.POST)
	public String CountNewsManagement(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisacContent == true) {
			obj.put("RoleId", 3);
		} else if (baseMemberRole.IsHisacContentSign == true) {
			obj.put("RoleId", 4);
		}

		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", newsManagementService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/activity_management", method = RequestMethod.POST)
	public String CountActivityManagement(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisacContent == true) {
			obj.put("RoleId", 3);
		} else if (baseMemberRole.IsHisacContentSign == true) {
			obj.put("RoleId", 4);
		}

		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", activityManagementService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/ana_management", method = RequestMethod.POST)
	public String CountAnaManagement(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisacContent == true) {
			obj.put("RoleId", 3);
		} else if (baseMemberRole.IsHisacContentSign == true) {
			obj.put("RoleId", 4);
		}

		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", anaManagementService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/weakness_management", method = RequestMethod.POST)
	public String CountWeaknessManagement(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisacContent == true) {
			obj.put("RoleId", 3);
		} else if (baseMemberRole.IsHisacContentSign == true) {
			obj.put("RoleId", 4);
		}

		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", weaknessManagementService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/count/information_management", method = RequestMethod.POST)
	public String CountInformationManagement(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 * 
		 */

		if (baseMemberRole.IsHisacContentSign == true) {
			obj.put("RoleId", 4);
		}

		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		countJson.put("count", informationManagementService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	@RequestMapping(value = "/count/inc", method = RequestMethod.POST)
	public String CountIncident(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();

		/*
		 * 1.SuperAdmin
		 * 2.H-ISAC管理者：IsHisac
		 * 10.會員機構聯絡人：IsMemberContact
		 * 15.權責單位通報審核者：IsApplySingAdmin
		 * 17.H-CERT審核者：IsHCERTContentSign
		 * 18.事件處理單位聯絡人：IsEventHandlingUnitContact
		 */
		if (baseMemberRole.IsAdmin) {
			obj.put("RoleId", 1);

			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：1");
		} else if (baseMemberRole.IsHisac) {
			obj.put("RoleId", 2);

			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：2");
		} else if (baseMemberRole.IsMemberContact) {
			obj.put("RoleId", 10);

			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：10");
		} else if (baseMemberRole.IsApplySingAdmin) {
			obj.put("RoleId", 15);

			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：15");
		} else if (baseMemberRole.IsHCERTContentSign) {
			obj.put("RoleId", 17);
		
			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：17");
		} else if (baseMemberRole.IsEventHandlingUnitContact) {
			obj.put("RoleId", 18);

			// debug
//			System.out.println("p00_IndexController.java → CountIncident() → RoleId：18");
		}

		obj.put("MemberId", getBaseMemberId());

		// debug
//		System.out.println("p00_IndexController.java → CountIncident() → getBaseMemberId()：" + getBaseMemberId());
//		System.out.println("p00_IndexController.java → CountIncident() → getBaseOrgType()：" + getBaseOrgType());

		if (getBaseOrgType().equals("1") || getBaseOrgType().equals("2")) {
			obj.put("ParentOrgId", getBaseOrgId());

			// debug
			//System.out.println("p00_IndexController.java → CountIncident() → ParentOrgId：" + getBaseOrgId());
		} else if (getBaseOrgType().equals("3")) {
			obj.put("ReporterName", getBaseOrgId());

			// debug
			//System.out.println("p00_IndexController.java → CountIncident() → ReporterName：" + getBaseOrgId());
		} else if (getBaseOrgType().equals("4")) {
			obj.put("HandleName", getBaseOrgId());

			// debug
			//System.out.println("p00_IndexController.java → CountIncident() → HandleName：" + getBaseOrgId());
		}
		
		json = obj.toString();

		countJson.put("count", incidentService.getSpFormCount(json));
		model.addAttribute("json", countJson.toString());

		// debug
		//System.out.println("p00_IndexController.java → CountIncident() → countJson.toString()：" + countJson.toString());
		
		return "msg";
	}
	
	
	@RequestMapping(value = "/count/mtp", method = RequestMethod.POST)
	public String CountMtp(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject countJson = new JSONObject();
		JSONObject obj = new JSONObject();		
		long num = 0;
		if (baseMemberRole.IsMemberContact == true || baseMemberRole.IsMemberAdmin == true) {
			obj.put("Status", "2");
			num = num + maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			obj.put("Status", "31");
			num = num + maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			obj.put("Status", "5");
			num = num + maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
			obj.put("Status", "8");			
			num = num + maintainPlanMemberService.getListSize(getBaseOrgId(), obj.toString());
		}				

		countJson.put("count", num);
		model.addAttribute("json", countJson.toString());
		return "msg";
	}

	/**
	 * 圖片輸出
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param linksId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/p00/pic/download/{linksId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long linksId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("LinksId", linksId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!linksService.isExist(linksId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!linksPicService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				LinksPic linksPic = linksPicService.getById(id);
				try {
					byte[] buffer = linksPic.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(linksPic.getFileName(), StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(linksPic.getFileType());
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}

	/**
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/p00/query/info/dashboard", method = RequestMethod.POST)
	public String QueryInfo(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			List<SpDashboard> spDashboards = spDashboardService.getSpListInfo(json);
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	 * 取得公開資訊件數API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/p00/query/public/dashboard", method = RequestMethod.POST)
	public String QueryPublic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			List<SpDashboard> spDashboards = spDashboardService.getSpListPublic(json);
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	 * 取得公開資訊件數(Last 2 week)API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/p00/query/public/dashboard2week", method = RequestMethod.POST)
	public String QueryPublic2Week(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("Sdate", WebDatetime.toString(WebDatetime.addDays(new Date(), -14), "yyyy-MM-dd 00:00:00"));
			List<SpDashboard> spDashboards = spDashboardService.getSpListPublic(obj.toString());
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	@RequestMapping(value = "/p00/query/message/dashboard", method = RequestMethod.POST)
	public String QueryMessage(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("OrgId", getBaseOrgId());
			obj.put("OrgType", orgService.getDataById(getBaseOrgId()).getOrgType());
			List<SpDashboard> spDashboards = spDashboardService.getSpListMessage(obj.toString());
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	@RequestMapping(value = "/p00/query/message/dashboard2week", method = RequestMethod.POST)
	public String QueryMessage2Week(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("Sdate", WebDatetime.toString(WebDatetime.addDays(new Date(), -14), "yyyy-MM-dd 00:00:00"));
			obj.put("OrgId", getBaseOrgId());
			obj.put("OrgType", orgService.getDataById(getBaseOrgId()).getOrgType());
			List<SpDashboard> spDashboards = spDashboardService.getSpListMessage(obj.toString());
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	 * 取得通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	@RequestMapping(value = "/p00/query/notification/dashboard", method = RequestMethod.POST)
	public String QueryNotification(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			List<SpDashboard> spDashboards = spDashboardService.getSpListNotification(json);
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	 * 取得登入總數API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 登入總數資料
	 */
	@RequestMapping(value = "/p00/query/signincount/dashboard", method = RequestMethod.POST)
	public String QuerySigninCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			obj.put("Distinct", "inputValue");
			
			listjson.put("count", systemLogService.getListSize(obj.toString()));			

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
	
	/**
	 * 取得會員機構申請數API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員機構申請數資料
	 */
	@RequestMapping(value = "/p00/query/orgcount/dashboard", method = RequestMethod.POST)
	public String QueryOrgCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
			
			JSONArray sn_array = new JSONArray();	
			JSONObject ci_json = new JSONObject();
			JSONObject non_ci_json = new JSONObject();
			int allCounts =  (int)orgService.getListSize(json);
			JSONObject obj = new JSONObject(json);
			obj.put("CiLevel", "2");
			ci_json.put("Name", "CI會員");
			ci_json.put("Count", orgService.getListSize(obj.toString()));
			non_ci_json.put("Name", "非CI會員");
			non_ci_json.put("Count", allCounts - (int)orgService.getListSize(obj.toString()));
			sn_array.put(ci_json);
			sn_array.put(non_ci_json);
			listjson.put("datatable", sn_array);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
	
	
	/**
	 * 取得會員管理者數API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會會員管理者數資料
	 */
	@RequestMapping(value = "/p00/query/managercount/dashboard", method = RequestMethod.POST)
	public String QueryManagerCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
			int useCount = 0;
			JSONArray sn_array = new JSONArray();	
			List<SpManagerCountDashboard> spManagerCountDashboards = spDashboardService.getManager();
			if (spManagerCountDashboards != null)
				for (SpManagerCountDashboard spManagerCountDashboard : spManagerCountDashboards) {
					JSONObject sn_json = new JSONObject();
					switch(spManagerCountDashboard.getName()) {
						case "ManagerCount":
							useCount = useCount + (int)spManagerCountDashboard.getCount();							
							break;
						case "ManagerExamine":
							useCount = useCount - (int)spManagerCountDashboard.getCount();								
							break;
						case "ManagerDisable":
							useCount = useCount - (int)spManagerCountDashboard.getCount();	
							sn_json.put("Name", "已停用");
							sn_json.put("Count", spManagerCountDashboard.getCount());		
							sn_array.put(sn_json);
							break;
						case "ManagerWait":
							useCount = useCount - (int)spManagerCountDashboard.getCount();	
							sn_json.put("Name", "待啟用");
							sn_json.put("Count", spManagerCountDashboard.getCount());		
							sn_array.put(sn_json);
							break;						
						
					}										
				}
			JSONObject sn_json = new JSONObject();
			sn_json.put("Name", "使用中");
			sn_json.put("Count", useCount);		
			sn_array.put(sn_json);
			listjson.put("datatable", sn_array);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
	
	
	/**
	 * 取得會員聯絡人數API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 會員聯絡人數資料
	 */
	@RequestMapping(value = "/p00/query/contactcount/dashboard", method = RequestMethod.POST)
	public String QueryContactCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
			int useCount = 0;
			JSONArray sn_array = new JSONArray();	
			List<SpContactCountDashboard> spContactCountDashboards = spDashboardService.getContact();
			if (spContactCountDashboards != null)
				for (SpContactCountDashboard spContactCountDashboard : spContactCountDashboards) {
					JSONObject sn_json = new JSONObject();
					switch(spContactCountDashboard.getName()) {
						case "ContactCount":
							useCount = useCount + (int)spContactCountDashboard.getCount();							
							break;
						case "ContactExamine":
							useCount = useCount - (int)spContactCountDashboard.getCount();								
							break;
						case "ContactDisable":
							useCount = useCount - (int)spContactCountDashboard.getCount();	
							sn_json.put("Name", "已停用");
							sn_json.put("Count", spContactCountDashboard.getCount());		
							sn_array.put(sn_json);
							break;
						case "ContactWait":
							useCount = useCount - (int)spContactCountDashboard.getCount();	
							sn_json.put("Name", "待啟用");
							sn_json.put("Count", spContactCountDashboard.getCount());		
							sn_array.put(sn_json);
							break;						
						
					}										
				}
			JSONObject sn_json = new JSONObject();
			sn_json.put("Name", "使用中");
			sn_json.put("Count", useCount);		
			sn_array.put(sn_json);
			listjson.put("datatable", sn_array);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
	
	
	/**
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/p00/query/information/dashboard", method = RequestMethod.POST)
	public String QueryInformation(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);			
			List<SpDashboard> spDashboards = spDashboardService.getInformation(obj.toString());
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
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
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/p00/query/information/dashboard2week", method = RequestMethod.POST)
	public String QueryInformation2Week(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("Sdate", WebDatetime.toString(WebDatetime.addDays(new Date(), -14), "yyyy-MM-dd 00:00:00"));			
			List<SpDashboard> spDashboards = spDashboardService.getInformation(obj.toString());
			if (spDashboards != null)
				for (SpDashboard spDashboard : spDashboards) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Name", spDashboard.getName());
					sn_json.put("Count", spDashboard.getCount());
					sn_array.put(sn_json);
				}

			listjson.put("datatable", sn_array);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}
}