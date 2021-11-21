//package tw.gov.mohw.hisac.web.controller.api;
//import java.util.Date;
//import java.text.SimpleDateFormat;
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
//import tw.gov.mohw.hisac.web.WebCrypto;
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
//
//import tw.gov.mohw.hisac.web.domain.MemberReport;
//import tw.gov.mohw.hisac.web.domain.OrgReport;
//import tw.gov.mohw.hisac.web.domain.SpOrgReportResult;
//
//
//
//
//
//
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
//import tw.gov.mohw.hisac.web.service.OrgReportMemberLogService;
//import tw.gov.mohw.hisac.web.service.OrgService;
//import tw.gov.mohw.hisac.web.service.ReportService;
//
//import tw.gov.mohw.hisac.web.service.OrgReportService;
//import tw.gov.mohw.hisac.web.service.MemberReportService;
//
///**
// * 會員報表修正效能控制器 110.01.12
// */
//@Controller
//@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
//public class s43_MemberReportTestController extends BaseController {
//
//	private String targetControllerName = "sys";
//	private String targetActionName = "s43";
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
//	private ReportService reportService;	
//	
//	
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
//	@Autowired
//	private OrgReportService orgReportService;
//	@Autowired
//	private MemberReportService memberReportService;
//	@Autowired
//	private OrgReportMemberLogService orgReportMemberLogService;
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
//	private boolean idExists(JSONArray jsonArray, Long dataToFind){
//	    return jsonArray.toString().contains("\"Id\":"+dataToFind);
//	}
//	
//	private boolean SecIdExists(JSONArray jsonArray, String dataToFind){
//	    return jsonArray.toString().contains("\"Id\":\""+dataToFind+"\"");
//	}
//	
//	private JSONObject getValuesForGivenKey(String jsonArrayStr, Long id) {
//		JSONArray jsonArray = new JSONArray(jsonArrayStr);
//		JSONObject response  = null;
//		
//
//		for(int i=0;i<jsonArray.length();i++) {
//				
//            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
//
//            
//            if(jsonObject1.getLong("OrgId") == id ) {
//            	System.out.println("jsonObject1");
//
//                System.out.println(jsonObject1);
//            	response = jsonObject1;
//        		return response;
//
//            }
//           
//
//		}
//		return response;
//	
//	}
//	
//	/**
//	 * 取得機構資料登入人數報表資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件 條件為開始時間與結束時間
//	 * @return 機構資料登入人數報表資料
//	 */
//	@RequestMapping(value = "/s43/orgReportResult", method = RequestMethod.POST)
//	public String QueryOrgReportResult(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONArray orgReport_array = new JSONArray();
//		JSONArray orgExamineReport_array = new JSONArray();	
//		
//		JSONObject listjson = new JSONObject();
//
//
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {		
//			
//		
//			List<SpOrgReportResult> spOrgReportResults = orgReportService.getSumResult(json);
//			
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
//			
//			long sumMemberCount = 0;
//			long sumMemberSigninCount = 0;
//			long sumSigninCount = 0;
//			long sumNewsCount = 0;
//			long sumActivityCount = 0;
//			long sumAnaCount = 0;
//			long sumSecbuzzerCount = 0;
//			long sumCiCount = 0;
//			long sumNonCiBasedCount = 0;
//			long sumNonCiAdvancedCount = 0;
//			long sumExamineCiCount = 0;
//			long sumExamineNonCiBasedCount = 0;
//			long sumExamineNonCiAdvancedCount = 0;
//			
//			if (spOrgReportResults != null)
//				for (SpOrgReportResult spOrgReportResult : spOrgReportResults) {	
//					
//					JSONObject sn_json = new JSONObject();			
//					
//					
//					sn_json.put("MemberCount", spOrgReportResult.getMemberCount());
//					sn_json.put("SigninCount", spOrgReportResult.getSigninCount());
//					sn_json.put("MemberSigninCount", spOrgReportResult.getMemberSigninCount());
//					sn_json.put("NewsCount", spOrgReportResult.getNewsCount());
//					sn_json.put("ActivityCount", spOrgReportResult.getActivityCount());
//					sn_json.put("AnaCount", spOrgReportResult.getAnaCount());
//					sn_json.put("SecbuzzerCount", spOrgReportResult.getSecbuzzerCount());
//					sn_json.put("OrgName", spOrgReportResult.getName());
//					sn_json.put("CiLevel", (String) spOrgReportResult.getCiLevel());
//					sn_json.put("OrgId", spOrgReportResult.getOrgId());
//					sn_json.put("SignApplyTime",  WebDatetime.toString(spOrgReportResult.getSignApplyTime(), "yyyy-MM-dd") );
//					
//					
//					sumMemberCount =  sumMemberCount + spOrgReportResult.getMemberCount();
//					sumMemberSigninCount = sumMemberSigninCount + spOrgReportResult.getMemberSigninCount();
//					sumSigninCount = sumSigninCount + spOrgReportResult.getSigninCount();
//					sumNewsCount = sumNewsCount + spOrgReportResult.getNewsCount();
//					sumActivityCount = sumActivityCount + spOrgReportResult.getActivityCount() ;
//					sumAnaCount = sumAnaCount + spOrgReportResult.getAnaCount();
//					sumSecbuzzerCount = sumSecbuzzerCount + spOrgReportResult.getSecbuzzerCount();
//					if (spOrgReportResult.getCiLevel()==null)
//						sumNonCiBasedCount++;
//					else if (Integer.valueOf((String) spOrgReportResult.getCiLevel()) == 0)
//						sumNonCiBasedCount++;
//					else if (Integer.valueOf((String) spOrgReportResult.getCiLevel()) == 1)
//						sumNonCiAdvancedCount++;
//					else if (Integer.valueOf((String) spOrgReportResult.getCiLevel()) == 2)
//						sumCiCount++;						
//					orgReport_array.put(sn_json);	
//			}
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
//			listjson.put("orgReport", orgReport_array);
//			listjson.put("orgExamineReport", orgExamineReport_array);
//			listjson.put("sumOrgExamineReport", sumOrgExamineReport);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", WebCrypto.getSafe(listjson.toString()));
//		return "msg";
//		
//	}
//
//	
//	/**
//	 * 取得會員點擊文章資料API
//	 * 
//	 * @param locale
//	 *            Locale
//	 * @param request
//	 *            HttpServletRequest
//	 * @param model
//	 *            Model
//	 * @param json
//	 *            查詢條件
//	 * @return 會員點擊文章資料
//	 */
//	
//	@RequestMapping(value = "/s43/memberReportResult", method = RequestMethod.POST)
//	public String QueryMemberReportResult(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//		JSONObject listjson = new JSONObject();
//		JSONArray anaTop5_array = new JSONArray();
//		JSONArray secTop5_array = new JSONArray();
//		JSONArray newsTop5_array = new JSONArray();
//		JSONArray activityTop5_array = new JSONArray();	
//		
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {		
//			
//			List<MemberReport> memberReports = memberReportService.getList(json);
//			JSONObject obj = new JSONObject(json);
//			String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
//			String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
//			
//			String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
//			String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
//
//			
//			if (querySdate != null) {
//				querySdate = querySdate + "00:00:00";
//			}
//
//			if (queryEdate != null) {
//				queryEdate = querySdate + "23:59:59";
//			}
//			if (memberReports != null)
//				for (MemberReport memberReport : memberReports) {	
//					if (memberReport.getName().contains("AnaTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("AnaTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long anaManagementId = obj_name.getLong("Id");
//						if (!idExists(anaTop5_array ,anaManagementId) && anaTop5_array.length() < 10) {
//							sn_json.put("Id", obj_name.getLong("Id"));			
//							sn_json.put("PostId", obj_name.optString("PostId", "測試資料"));
//							sn_json.put("IncidentTitle", obj_name.optString("IncidentTitle", "測試資料"));
//							sn_json.put("IncidentDiscoveryTime", obj_name.optString("IncidentDiscoveryTime", "測試資料"));	
//							sn_json.put("EventTypeCode", obj_name.optString("EventTypeCode", "測試資料"));			
//							sn_json.put("ReporterName", obj_name.optString("ReporterName", "測試資料"));		
//							sn_json.put("Count", memberReport.getCount());	
//							JSONObject refCount_json = new JSONObject();
//							refCount_json.put("AppName", "p04_Ana");
//							refCount_json.put("FuncName", "DownloadAttach");							
//							refCount_json.put("InputValue", "\"AnaManagementId\":" + Long.toString(obj_name.getLong("Id")));
//							if (querySdate != null)
//								refCount_json.put("Sdate", querySdate);
//							if (queryEdate != null) 
//								refCount_json.put("Edate", queryEdate);
////							sn_json.put("ReferenceCount", systemLogService.getListSize(refCount_json.toString()));
//							
//							sn_json.put("ReferenceCount", reportService.getDownAttachListSize(refCount_json.toString()));
//
//							anaTop5_array.put(sn_json);
//						}else if ( anaTop5_array.length() < 10) {
//							for ( int n = 0; n < anaTop5_array.length(); n++) {
//								JSONObject object = anaTop5_array.getJSONObject(n);
//								if(object.get("Id").equals(anaManagementId)){
//									object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//									anaTop5_array.put(n, object);
//									break;
//								}
//							}
//						}
//					}
//					else if(memberReport.getName().contains("SecTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("SecTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						String informationExchangeId = obj_name.getString("Id");
//						if (!SecIdExists(secTop5_array ,informationExchangeId) &&  secTop5_array.length() < 10) {
//							sn_json.put("Id", obj_name.getString("Id"));			
//							sn_json.put("IncidentTitle", obj_name.optString("IncidentTitle", "測試資料"));
//							sn_json.put("IncidentDiscoveryTime", obj_name.optString("IncidentDiscoveryTime", "測試資料"));
//							sn_json.put("SourceCode", obj_name.optString("SourceCode", "測試資料"));
//							sn_json.put("Count", memberReport.getCount());	
//							sn_json.put("Reference", obj_name.optString("Reference" , "測試資料"));
//							secTop5_array.put(sn_json);	
//						}else if (secTop5_array.length() < 10) {
//								for ( int n = 0; n < secTop5_array.length(); n++) {
//									JSONObject object = secTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(informationExchangeId)){
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										secTop5_array.put(n, object);
//										break;
//									}
//								}
//							}
//					}
//					else if(memberReport.getName().contains("NewsTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("NewsTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long nnewsManagementId = obj_name.getLong("Id");
//							if (!idExists(newsTop5_array ,nnewsManagementId) && newsTop5_array.length()< 10) {
//				
//							sn_json.put("Id", obj_name.getLong("Id"));			
//							sn_json.put("Title", obj_name.optString("Title" , "無"));							
//							sn_json.put("Count", memberReport.getCount());								
//							newsTop5_array.put(sn_json);
//							}
//							else if (newsTop5_array.length()< 10){
//								for ( int n = 0; n < newsTop5_array.length(); n++) {
//									JSONObject object = newsTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(nnewsManagementId)){
//										
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										newsTop5_array.put(n, object);
//										break;
//									}
//								}		
//							}
//					}
//					else if(memberReport.getName().contains("ActivityTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("ActivityTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long activityManagementId = obj_name.getLong("Id");
//							if (!idExists(activityTop5_array, activityManagementId) && activityTop5_array.length() <10) {
//								sn_json.put("Id", obj_name.getLong("Id"));			
//								sn_json.put("Title", obj_name.optString("Title","無"));							
//								sn_json.put("Count",memberReport.getCount());								
//								activityTop5_array.put(sn_json);						
//							}else if (activityTop5_array.length() <10) {
//								for ( int n = 0; n < activityTop5_array.length(); n++) {
//									JSONObject object = activityTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(activityManagementId)){
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										activityTop5_array.put(n, object);
//										break;
//									}
//								}	
//								
//							}
//							
//						}
//					else
//						listjson.put(memberReport.getName(), memberReport.getCount());
//				
//				}
//			listjson.put("newsTop5", newsTop5_array);
//			listjson.put("activityTop5", activityTop5_array);		
//			listjson.put("anaTop5", anaTop5_array);
//			listjson.put("secTop5", secTop5_array);			
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		}else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		model.addAttribute("json", WebCrypto.getSafe(listjson.toString()));
//		return "msg";
//
//		
//	}
//
//		
//	
//
//	
//
//	
//	
//	
//	
//	
//	
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
//	@RequestMapping(value = "/s43/memberReport", method = RequestMethod.POST)
//	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
//
//		JSONObject listjson = new JSONObject();
//		JSONArray anaTop5_array = new JSONArray();
//		JSONArray secTop5_array = new JSONArray();
//		JSONArray newsTop5_array = new JSONArray();
//		JSONArray activityTop5_array = new JSONArray();		
//		JSONArray orgReport_array = new JSONArray();	
//		JSONArray orgReportMemberLog_array = new  JSONArray();	
//		JSONArray orgExamineReport_array = new JSONArray();	
//		
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {		
//			
//			List<MemberReport> memberReports = memberReportService.getList(json);
//			
//			List<Object[]> orgReports = orgReportService.getSumCount(json);
//			
//			List<Object[]> orgReportMemberLogs = orgReportMemberLogService.getMemberSigninCount(json);
//			
//			
//			List<Org> orgs = orgService.getOrgReport();
//			JSONObject obj = new JSONObject(json);
//			String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
//			String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
//			
//			String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
//			String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
//
//			
//			if (querySdate != null) {
//				querySdate = querySdate + "00:00:00";
//			}
//
//			if (queryEdate != null) {
//				queryEdate = querySdate + "23:59:59";
//			}
//			if (memberReports != null)
//				for (MemberReport memberReport : memberReports) {				
//					if (memberReport.getName().contains("AnaTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("AnaTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long anaManagementId = obj_name.getLong("Id");
//						AnaManagement anaManagement = anaManagementService.get(obj_name.getLong("Id"));	
//						if (anaManagement != null) {
//							if (!idExists(anaTop5_array ,anaManagementId)) {
//								sn_json.put("Id", anaManagement.getId());			
//								sn_json.put("PostId", anaManagement.getPostId());
//								sn_json.put("IncidentTitle", anaManagement.getIncidentTitle());
//								sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime()));	
//								sn_json.put("EventTypeCode", anaManagement.getEventTypeCode());			
//								sn_json.put("ReporterName", anaManagement.getReporterName());		
//								sn_json.put("Count", memberReport.getCount());	
//								JSONObject refCount_json = new JSONObject();
//								refCount_json.put("AppName", "p04_Ana");
//								refCount_json.put("FuncName", "DownloadAttach");							
//								refCount_json.put("InputValue", "\"AnaManagementId\":" + Long.toString(obj_name.getLong("Id")));
//								if (querySdate != null)
//									refCount_json.put("Sdate", querySdate);
//								if (queryEdate != null) 
//									refCount_json.put("Edate", queryEdate);
//								sn_json.put("ReferenceCount", systemLogService.getListSize(refCount_json.toString()));
//								anaTop5_array.put(sn_json);
//								
//							}else {
//								for ( int n = 0; n < anaTop5_array.length(); n++) {
//									JSONObject object = anaTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(anaManagementId)){
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										anaTop5_array.put(n, object);
//										break;
//									}
//								}
//							}			
//						}
//					}						
//					else if(memberReport.getName().contains("SecTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("SecTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						String informationExchangeId = obj_name.getString("Id");
//						InformationExchange informationExchange = informationExchangeService.getById(obj_name.getString("Id"));		
//						if (informationExchange != null) {
//							if (!SecIdExists(secTop5_array ,informationExchangeId)) {
//							sn_json.put("Id", informationExchange.getId());			
//							sn_json.put("IncidentTitle", informationExchange.getIncidentTitle());
//							sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(informationExchange.getIncidentDiscoveryTime()));
//							sn_json.put("SourceCode", informationExchange.getSourceCode());
//							sn_json.put("Count", memberReport.getCount());	
//							sn_json.put("Reference", informationExchange.getReference());
//							secTop5_array.put(sn_json);	
//						}else {
//								for ( int n = 0; n < secTop5_array.length(); n++) {
//									JSONObject object = secTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(informationExchangeId)){
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										secTop5_array.put(n, object);
//										break;
//									}
//								}
//							}
//						}
//					}
//					else if(memberReport.getName().contains("NewsTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("NewsTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long nnewsManagementId = obj_name.getLong("Id");
//						NewsManagement nnewsManagement = newsManagementService.get(obj_name.getLong("Id"));		
//						if (nnewsManagement != null) {
//							if (!idExists(newsTop5_array ,nnewsManagementId)) {
//				
//							sn_json.put("Id", nnewsManagement.getId());			
//							sn_json.put("Title", nnewsManagement.getTitle());							
//							sn_json.put("Count", memberReport.getCount());								
//							newsTop5_array.put(sn_json);
//							}
//							else {
//								for ( int n = 0; n < newsTop5_array.length(); n++) {
//									JSONObject object = newsTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(nnewsManagementId)){
//										
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										newsTop5_array.put(n, object);
//										break;
//									}
//								}		
//							}
//						}
//					}
//					else if(memberReport.getName().contains("ActivityTop5")) {
//						JSONObject sn_json = new JSONObject();
//						String name = memberReport.getName();
//						name = name.replace("ActivityTop5", "");
//						JSONObject obj_name = new JSONObject(name);
//						Long activityManagementId = obj_name.getLong("Id");
//						ActivityManagement activityManagement = activityManagementService.get(obj_name.getLong("Id"));		
//						if (activityManagement != null) {
//							if (!idExists(activityTop5_array, activityManagementId) ) {
//								sn_json.put("Id", activityManagement.getId());			
//								sn_json.put("Title", activityManagement.getTitle());							
//								sn_json.put("Count", memberReport.getCount());								
//								activityTop5_array.put(sn_json);						
//							}else {
//								for ( int n = 0; n < activityTop5_array.length(); n++) {
//									JSONObject object = activityTop5_array.getJSONObject(n);
//									if(object.get("Id").equals(activityManagementId)){
//										object.put("Count", (Long) object.get("Count") + memberReport.getCount());
//										activityTop5_array.put(n, object);
//										break;
//									}
//								}	
//								
//							}
//							}
//						}
//					else
//						listjson.put(memberReport.getName(), memberReport.getCount());											
//				}					
//			long sumMemberCount = 0;
//			long sumMemberSigninCount = 0;
//			long sumSigninCount = 0;
//			long sumNewsCount = 0;
//			long sumActivityCount = 0;
//			long sumAnaCount = 0;
//			long sumSecbuzzerCount = 0;
//			long sumCiCount = 0;
//			long sumNonCiBasedCount = 0;
//			long sumNonCiAdvancedCount = 0;
//			long sumExamineCiCount = 0;
//			long sumExamineNonCiBasedCount = 0;
//			long sumExamineNonCiAdvancedCount = 0;
//			
//			if(orgReportMemberLogs != null) {
//				for (Object[] orgReportMemberLog : orgReportMemberLogs) {	
//					JSONObject sn_json = new JSONObject();	
//					
//					
//					long orgId = (long) orgReportMemberLog[1];
//					
//					long memberSigninCount =  (long) orgReportMemberLog[0];
//					sn_json.put("OrgId", orgId);
//					sn_json.put("MemberSigninCount", memberSigninCount);
//					orgReportMemberLog_array.put(sn_json);
//				}
//				
//			}
//					
//			
//			if (orgReports != null)				
//				for (Object[] orgReport : orgReports) {			
//					
//					
//						JSONObject sn_json = new JSONObject();			
//						
//						
//						Double MemberCount = (Double) orgReport[0];
//						
//
//
//						sn_json.put("SigninCount", (long) orgReport[1]);
//						sn_json.put("NewsCount", (long) orgReport[2]);
//						sn_json.put("ActivityCount", (long) orgReport[3]);
//						sn_json.put("AnaCount", (long) orgReport[4]);
//						sn_json.put("SecbuzzerCount", (long) orgReport[5]);
//						sn_json.put("OrgName", (String) orgReport[7]);
//						sn_json.put("CiLevel", (String) orgReport[8]);
//						sn_json.put("MemberCount", MemberCount.longValue());
//
//						sn_json.put("SignApplyTime", WebDatetime.toString((Date) orgReport[9]));
//						Long orgId = (Long) orgReport[6];
//						
//						
////						int MemberSigninCount = orgReportService.getMemberLogCount(sdate, edate, orgId);
//						
//
//						
//						
//						
//						JSONObject orgReportMemberLog = getValuesForGivenKey(orgReportMemberLog_array.toString() , orgId);
//						
//						
//						Long MemberSigninCount = new Long(0);
//						
//						if (orgReportMemberLog != null) {
//							System.out.println(orgReportMemberLog);
//							
//							
//							
//							MemberSigninCount = orgReportMemberLog.getLong("MemberSigninCount");
//							
//						}
//						else {
//							MemberSigninCount = new Long(0);
//						}
//						
//						
//						
//						
//						sn_json.put("MemberSigninCount", MemberSigninCount);
//						
//						
////						sn_json.put("MemberSigninCount", orgReport.getMemberSigninCount());
//					
//						sumMemberCount =  sumMemberCount + MemberCount.longValue();
//						sumMemberSigninCount = sumMemberSigninCount + MemberSigninCount;
//						sumSigninCount = sumSigninCount + (long)orgReport[1];
//						sumNewsCount = sumNewsCount + (long)orgReport[2];
//						sumActivityCount = sumActivityCount + (long)orgReport[3];
//						sumAnaCount = sumAnaCount + (long)orgReport[4];
//						sumSecbuzzerCount = sumSecbuzzerCount + (long)orgReport[5];
//						if (orgReport[8]==null)
//							sumNonCiBasedCount++;
//						else if (Integer.valueOf((String) orgReport[8]) == 0)
//							sumNonCiBasedCount++;
//						else if (Integer.valueOf((String) orgReport[8]) == 1)
//							sumNonCiAdvancedCount++;
//						else if (Integer.valueOf((String) orgReport[8]) == 2)
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
//		model.addAttribute("json", WebCrypto.getSafe(listjson.toString()));
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
//	@RequestMapping(value = "/s43/getsource", method = RequestMethod.POST)
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
//	@RequestMapping(value = "/s43/geteventType", method = RequestMethod.POST)
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
//	@RequestMapping(value = "/s43/getTop5Detail", method = RequestMethod.POST)
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