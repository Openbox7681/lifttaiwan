//package tw.gov.mohw.hisac.web.controller.api;
//import java.util.List;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import tw.gov.mohw.hisac.web.WebDatetime;
//import tw.gov.mohw.hisac.web.controller.BaseController;
//import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
//import tw.gov.mohw.hisac.web.domain.ActivityManagement;
//import tw.gov.mohw.hisac.web.domain.AnaManagement;
//import tw.gov.mohw.hisac.web.domain.EventType;
//import tw.gov.mohw.hisac.web.domain.InformationExchange;
//import tw.gov.mohw.hisac.web.domain.InformationSource;
//import tw.gov.mohw.hisac.web.domain.NewsManagement;
//import tw.gov.mohw.hisac.web.domain.Org;
//import tw.gov.mohw.hisac.web.domain.SpMemberReport;
//import tw.gov.mohw.hisac.web.domain.SpOrgReport;
//import tw.gov.mohw.hisac.web.domain.SpSigninCountTop10;
//import tw.gov.mohw.hisac.web.domain.SpSystemLogByOrgTop5;
//import tw.gov.mohw.hisac.web.service.MemberService;
//import tw.gov.mohw.hisac.web.service.ActivityManagementService;
//import tw.gov.mohw.hisac.web.service.AnaManagementService;
//import tw.gov.mohw.hisac.web.service.InformationExchangeService;
//import tw.gov.mohw.hisac.web.service.SystemLogService;
//import tw.gov.mohw.hisac.web.service.InformationSourceService;
//import tw.gov.mohw.hisac.web.service.EventTypeService;
//import tw.gov.mohw.hisac.web.service.NewsManagementService;
//import tw.gov.mohw.hisac.web.service.OrgService;
//
///**
// * 角色資料維護控制器
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s17_MemberReportController extends BaseController {
//
//	private String targetControllerName = "sys";
//	private String targetActionName = "s17";
//	
//	@Autowired
//	private MemberService memberService;
//	@Autowired
//	private AnaManagementService anaManagementService;
//	@Autowired
//	private InformationExchangeService informationExchangeService;
//	@Autowired
//	private SystemLogService systemLogService;	
//	@Autowired
//	private InformationSourceService informationSourceService;
//	@Autowired
//	private EventTypeService eventTypeService;
//	@Autowired
//	private NewsManagementService newsManagementService;	
//	@Autowired
//	private ActivityManagementService activityManagementService;	
//	@Autowired
//	private OrgService orgService;		
//	
//	
//	/**
//	 * 
//	 * 查詢json array 是否有重複的ID資料
//	 * 
//	 * @param jsonArray
//	 * @param dataToFind
//	 * @return 是或否
//	 */
//	
//	private boolean idExists(JSONArray jsonArray, String dataToFind){
//	    return jsonArray.toString().contains("\"Id\":\""+dataToFind+"\"");
//	}
//	
//	/**
//	 * 取得警訊發佈群組管理會員機構資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return 警訊發佈群組管理會員機構資料
//	 */
//	@RequestMapping(value = "/s17/memberReport", method = RequestMethod.POST)
//	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONObject listjson = new JSONObject();
//		JSONArray anaTop5_array = new JSONArray();
//		JSONArray secTop5_array = new JSONArray();
//		JSONArray newsTop5_array = new JSONArray();
//		JSONArray activityTop5_array = new JSONArray();		
//		JSONArray orgReport_array = new JSONArray();	
//		JSONArray orgExamineReport_array = new JSONArray();	
//		
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
//			List<SpMemberReport> spMemberReports = memberService.getReport(json);			
//			List<SpOrgReport> orgReports = systemLogService.getOrgReport(json);
//			List<Org> orgs = orgService.getOrgReport();
//			JSONObject obj = new JSONObject(json);
//			String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
//			String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
//			
//			if (querySdate != null) {
//				querySdate = querySdate + "00:00:00";
//			}
//
//			if (queryEdate != null) {
//				queryEdate = querySdate + "23:59:59";
//			}
//			if (spMemberReports != null)
//				for (SpMemberReport spMemberReport : spMemberReports) {				
//					if (spMemberReport.getName().contains("AnaTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = spMemberReport.getName();
//						name = name.replace("AnaTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						AnaManagement anaManagement = anaManagementService.get(obj_name.getLong("Id"));	
//						if (anaManagement != null) {
//							sn_json.put("Id", anaManagement.getId());			
//							sn_json.put("PostId", anaManagement.getPostId());
//							sn_json.put("IncidentTitle", anaManagement.getIncidentTitle());
//							sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime()));	
//							sn_json.put("EventTypeCode", anaManagement.getEventTypeCode());			
//							sn_json.put("ReporterName", anaManagement.getReporterName());		
//							sn_json.put("Count", spMemberReport.getCount());	
//							JSONObject refCount_json = new JSONObject();
//							refCount_json.put("AppName", "p04_Ana");
//							refCount_json.put("FuncName", "DownloadAttach");							
//							refCount_json.put("InputValue", "\"AnaManagementId\":" + Long.toString(obj_name.getLong("Id")));
//							if (querySdate != null)
//								refCount_json.put("Sdate", querySdate);
//							if (queryEdate != null) 
//								refCount_json.put("Edate", queryEdate);
//							sn_json.put("ReferenceCount", systemLogService.getListSize(refCount_json.toString()));
//							anaTop5_array.put(sn_json);
//						}
//					}						
//					else if(spMemberReport.getName().contains("SecTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = spMemberReport.getName();
//						name = name.replace("SecTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						InformationExchange informationExchange = informationExchangeService.getById(obj_name.getString("Id"));		
//						if (informationExchange != null) {
//							sn_json.put("Id", informationExchange.getId());			
//							sn_json.put("IncidentTitle", informationExchange.getIncidentTitle());
//							sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(informationExchange.getIncidentDiscoveryTime()));
//							sn_json.put("SourceCode", informationExchange.getSourceCode());
//							sn_json.put("Count", spMemberReport.getCount());	
//							sn_json.put("Reference", informationExchange.getReference());
//							secTop5_array.put(sn_json);						
//						}
//					}
//					else if(spMemberReport.getName().contains("NewsTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = spMemberReport.getName();
//						name = name.replace("NewsTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						NewsManagement nnewsManagement = newsManagementService.get(obj_name.getLong("Id"));		
//						if (nnewsManagement != null) {
//							sn_json.put("Id", nnewsManagement.getId());			
//							sn_json.put("Title", nnewsManagement.getTitle());							
//							sn_json.put("Count", spMemberReport.getCount());								
//							newsTop5_array.put(sn_json);						
//						}
//					}
//					else if(spMemberReport.getName().contains("ActivityTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = spMemberReport.getName();
//						name = name.replace("ActivityTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						ActivityManagement activityManagement = activityManagementService.get(obj_name.getLong("Id"));		
//						if (activityManagement != null) {
//							sn_json.put("Id", activityManagement.getId());			
//							sn_json.put("Title", activityManagement.getTitle());							
//							sn_json.put("Count", spMemberReport.getCount());								
//							activityTop5_array.put(sn_json);						
//						}
//					}
//					else
//						listjson.put(spMemberReport.getName(), spMemberReport.getCount());											
//				}					
//			int sumMemberCount = 0;
//			int sumMemberSigninCount = 0;
//			int sumSigninCount = 0;
//			int sumNewsCount = 0;
//			int sumActivityCount = 0;
//			int sumAnaCount = 0;
//			int sumSecbuzzerCount = 0;
//			int sumCiCount = 0;
//			int sumNonCiBasedCount = 0;
//			int sumNonCiAdvancedCount = 0;
//			int sumExamineCiCount = 0;
//			int sumExamineNonCiBasedCount = 0;
//			int sumExamineNonCiAdvancedCount = 0;
//			if (orgReports != null)				
//				for (SpOrgReport orgReport : orgReports) {	
//						JSONObject sn_json = new JSONObject();					
//						sn_json.put("OrgName", orgReport.getName());
//						sn_json.put("CiLevel", orgReport.getCiLevel());
//						sn_json.put("MemberCount", orgReport.getMemberCount());
//						sn_json.put("MemberSigninCount", orgReport.getMemberSigninCount());
//						sn_json.put("SigninCount", orgReport.getSigninCount());
//						sn_json.put("NewsCount", orgReport.getNewsCount());
//						sn_json.put("ActivityCount", orgReport.getActivityCount());
//						sn_json.put("AnaCount", orgReport.getAnaCount());
//						sn_json.put("SecbuzzerCount", orgReport.getSecbuzzerCount());
//						sn_json.put("SignApplyTime", WebDatetime.toString(orgReport.getSignApplyTime()));
//					
//						sumMemberCount =  sumMemberCount + (int)orgReport.getMemberCount();
//						sumMemberSigninCount = sumMemberSigninCount + (int)orgReport.getMemberSigninCount();
//						sumSigninCount = sumSigninCount + (int)orgReport.getSigninCount();
//						sumNewsCount = sumNewsCount + (int)orgReport.getNewsCount();
//						sumActivityCount = sumActivityCount + (int)orgReport.getActivityCount();
//						sumAnaCount = sumAnaCount + (int)orgReport.getAnaCount();
//						sumSecbuzzerCount = sumSecbuzzerCount + (int)orgReport.getSecbuzzerCount();
//						if (orgReport.getCiLevel()==null)
//							sumNonCiBasedCount++;
//						else if (Integer.valueOf(orgReport.getCiLevel()) == 0)
//							sumNonCiBasedCount++;
//						else if (Integer.valueOf(orgReport.getCiLevel()) == 1)
//							sumNonCiAdvancedCount++;
//						else if (Integer.valueOf(orgReport.getCiLevel()) == 2)
//							sumCiCount++;						
//						orgReport_array.put(sn_json);											
//				}
//			
//			//審核列表
//			if (orgs != null)
//			for (Org org : orgs) {
//				JSONObject sn_json = new JSONObject();					
//				sn_json.put("OrgName", org.getName());
//				sn_json.put("CiLevel", org.getCiLevel());
//				sn_json.put("CreateTime", WebDatetime.toString(org.getCreateTime()));
//				if (org.getCiLevel()==null)
//					sumExamineNonCiBasedCount++;
//				else if (Integer.valueOf(org.getCiLevel()) == 0)
//					sumExamineNonCiBasedCount++;
//				else if (Integer.valueOf(org.getCiLevel()) == 1)
//					sumExamineNonCiAdvancedCount++;
//				else if (Integer.valueOf(org.getCiLevel()) == 2)
//					sumExamineCiCount++;	
//				orgExamineReport_array.put(sn_json);
//			}
//			
//			JSONObject sumOrgReport = new JSONObject();
//			sumOrgReport.put("SumMemberCount", sumMemberCount);
//			sumOrgReport.put("SumMemberSigninCount", sumMemberSigninCount);
//			sumOrgReport.put("SumSigninCount", sumSigninCount);
//			sumOrgReport.put("SumNewsCount", sumNewsCount);
//			sumOrgReport.put("SumActivityCount", sumActivityCount);
//			sumOrgReport.put("SumAnaCount", sumAnaCount);
//			sumOrgReport.put("SumSecbuzzerCount", sumSecbuzzerCount);
//			sumOrgReport.put("SumCiCount", sumCiCount);
//			sumOrgReport.put("SumNonCiBasedCount", sumNonCiBasedCount);
//			sumOrgReport.put("SumNonCiAdvancedCount", sumNonCiAdvancedCount);	
//			JSONObject sumOrgExamineReport = new JSONObject();
//			sumOrgExamineReport.put("SumExamineCiCount", sumExamineCiCount);
//			sumOrgExamineReport.put("SumExamineNonCiBasedCount", sumExamineNonCiBasedCount);
//			sumOrgExamineReport.put("SumExamineNonCiAdvancedCount", sumExamineNonCiAdvancedCount);
//			listjson.put("sumOrgReport", sumOrgReport);
//			listjson.put("newsTop5", newsTop5_array);
//			listjson.put("activityTop5", activityTop5_array);		
//			listjson.put("anaTop5", anaTop5_array);
//			listjson.put("secTop5", secTop5_array);							
//			listjson.put("orgReport", orgReport_array);
//			listjson.put("orgExamineReport", orgExamineReport_array);
//			listjson.put("sumOrgExamineReport", sumOrgExamineReport);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", listjson.toString());
//		return "msg";
//	}
//
//	/**
//	 * 取得sourceName資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            sourceName資料
//	 * @return sourceName資料
//	 */
//	@RequestMapping(value = "/s17/getsource", method = RequestMethod.POST)
//	public String Getsource(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			List<InformationSource> informationSources = informationSourceService.getAll();
//			if (informationSources != null)
//				for (InformationSource informationSource : informationSources) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Code", informationSource.getCode());
//					sn_json.put("Name", informationSource.getName());
//					sn_array.put(sn_json);
//				}
//			model.addAttribute("json", sn_array.toString());
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return "msg";
//	}
//	
//	/**
//	 * 取得EventType資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            EventType資料
//	 * @return EventType資料
//	 */
//	@RequestMapping(value = "/s17/geteventType", method = RequestMethod.POST)
//	public String GeteventType(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			List<EventType> eventTypes = eventTypeService.getAll();
//			if (eventTypes != null)
//				for (EventType eventType : eventTypes) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Code", eventType.getCode());
//					sn_json.put("Name", eventType.getName());
//					sn_array.put(sn_json);
//				}
//			model.addAttribute("json", sn_array.toString());
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return "msg";
//	}
//	
//	
//	/**
//	 * 取得Systemlog(Top5Detail)資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            Systemlog資料
//	 * @return Systemlog資料
//	 */
//	@RequestMapping(value = "/s17/getTop5Detail", method = RequestMethod.POST)
//	public String GetTop5Detail(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//			List<SpSystemLogByOrgTop5> top5Details = systemLogService.getSpSystemLogByOrg(json);
//			int rank=1;
//			if (top5Details != null)
//				for (SpSystemLogByOrgTop5 top5Detail : top5Details) {
//					JSONObject sn_json = new JSONObject();
//					sn_json.put("Rank", rank);
//					sn_json.put("OrgName", top5Detail.getOrgName());
//					sn_json.put("Count", top5Detail.getCount());
//					sn_array.put(sn_json);
//					rank++;
//				}
//			model.addAttribute("json", sn_array.toString());
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		return "msg";
//	}
//}