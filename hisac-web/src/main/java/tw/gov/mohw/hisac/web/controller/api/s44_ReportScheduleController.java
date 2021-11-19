package tw.gov.mohw.hisac.web.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;

import tw.gov.mohw.hisac.web.domain.MemberReportSchedule;
import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.domain.OrgReportSchedule;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostPicMember;
import tw.gov.mohw.hisac.web.domain.OrgReport;



import tw.gov.mohw.hisac.web.service.MemberReportScheduleService;
import tw.gov.mohw.hisac.web.service.MemberReportService;
import tw.gov.mohw.hisac.web.service.OrgReportScheduleService;
import tw.gov.mohw.hisac.web.service.OrgReportService;




import tw.gov.mohw.hisac.web.WebCrypto;


/**
 * 操作記錄控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s44_ReportScheduleController extends BaseController {

	private String targetControllerName = "sys";
	private String targetActionName = "s44";
	
	
	
	@Autowired
	private MemberReportScheduleService memberReportScheduleService;
	
	
	@Autowired
	private MemberReportService memberReportService;
	
	@Autowired
	private OrgReportScheduleService orgReportScheduleService;
	
	
	@Autowired
	private OrgReportService orgReportService;
	
	
	
	

	/**
	 * 取得ReportScheduleService 排程狀態資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return MemberReportSchedule資料 與 OrgReportReportSchedule 
	 * 
	 * 內容中ReportCoce 1: MemberReport 2: OrgReport
	 */
	@RequestMapping(value = "/s44/ReportSchedule/query", method = RequestMethod.POST)
	public String MemberReportScheduleQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		json = WebCrypto.getSafe(json);
		
		JSONObject obj = new JSONObject(json);
		
		Integer ReportCode = obj.isNull("ReportCode") ==true ? null : obj.getInt("ReportCode");
		
		

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
			List<MemberReportSchedule> memberReportSchedules = null;
			List<OrgReportSchedule> orgReportSchedules  = null ;

			
			if(new Integer (1).equals(ReportCode)) {
				memberReportSchedules = memberReportScheduleService.getList(json);
			}else if (new Integer (2).equals(ReportCode)) {
				orgReportSchedules = orgReportScheduleService.getList(json);
			}
			else {
				memberReportSchedules = memberReportScheduleService.getList(json);
				orgReportSchedules = orgReportScheduleService.getList(json);
			


			}
			

			if (memberReportSchedules != null) {
				for (MemberReportSchedule memberReportSchedule : memberReportSchedules) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", memberReportSchedule.getId());
					sn_json.put("Message", memberReportSchedule.getMessage());
					sn_json.put("ScheduleTime", WebDatetime.toString(memberReportSchedule.getScheduleTime()));
					sn_json.put("Status", memberReportSchedule.getStatus());
					sn_json.put("IsOldSchedule", memberReportSchedule.getIsOldSchedule());
					sn_json.put("ReportCode", 1);
					sn_json.put("ReportName", "會員統計報表");

			
					
					sn_array.put(sn_json);
				}
			}
			

			if (orgReportSchedules != null) {
				for (OrgReportSchedule orgReportSchedule : orgReportSchedules) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", orgReportSchedule.getId());
					sn_json.put("Message", orgReportSchedule.getMessage());
					sn_json.put("ScheduleTime", WebDatetime.toString(orgReportSchedule.getScheduleTime()));
					sn_json.put("Status", orgReportSchedule.getStatus());
					sn_json.put("IsOldSchedule", orgReportSchedule.getIsOldSchedule());
					sn_json.put("ReportCode", 2);
					sn_json.put("ReportName", "組織統計報表");

					sn_array.put(sn_json);
				}}
			
			
			listjson.put("total", orgReportScheduleService.getOrgReportScheduleListSize(json) + memberReportScheduleService.getMemberReportScheduleListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	
	/**
	 * 執行 memberReportScheduleService 排程狀態資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return MemberReportSchedule資料
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/s44/Report/execute", method = RequestMethod.POST)
	public String OrgReportScheduleQuery(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) throws JSONException, ParseException {
		JSONArray sn_array = new JSONArray();
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		
		Long reportCode = obj.optLong("ReportCode");
		
		String reportScheduleTime = obj.getString("ReportScheduleTime");

		JSONObject responseJson = new JSONObject();
		


		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
			
			
			if (reportCode.equals(new Long(1))) {
				
				if(memberReportService.isMemberReportExistByCreateReportTime(reportScheduleTime)) {
					responseJson.put("msg" , "排程資料已存在");
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

				}else {
					
					JSONObject response  = (JSONObject) memberReportService.schedule(json);
					if ( response.getBoolean("Success")) {
						
						//狀態為1 表示 排程執行成功 0則為失敗
						memberReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
						
						responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

					}else {
						//失敗則紀錄下log
						memberReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
						responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

					}	
				}
			}
			
			else if (reportCode.equals(new Long(2))){
				
				if(orgReportService.isOrgReportExistByCreateReportTime(reportScheduleTime)) {
					responseJson.put("msg" , "排程資料已存在");
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());

				}else {
					
					JSONObject response  = (JSONObject) orgReportService.schedule(json);
					if ( response.getBoolean("Success")) {
						//狀態為1 表示 排程執行成功 0則為失敗
						orgReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
						responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
						responseJson.put("success", true);

						
						
					}else {
						//失敗則紀錄下log
						orgReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
						responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						
					}
				}		
			}
			
			
			
			
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}
	
	/**
	 * 刪除排程資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 * @throws ParseException 
	 */
	
	@RequestMapping(value = "/s44/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) throws ParseException {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Long reportCode = obj.optLong("ReportCode");
			
			
			String reportScheduleTime = obj.isNull("ReportScheduleTime") == true ? null : obj.getString("ReportScheduleTime");
			
			Date reportSDate = new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime);
			
			Date reportEDate = WebDatetime.addDays(reportSDate, 1);
			
			
			JSONObject obj_json = new JSONObject();
			obj_json.put("QuerySdate", WebDatetime.toString(reportSDate, "yyyy-MM-dd"));
			obj_json.put("QueryEdate", WebDatetime.toString(reportEDate, "yyyy-MM-dd"));


			//刪除會員統計報表排程與紀錄 (MemberReport 和 memberReportSchedule)
			
			if(reportCode.equals(new Long(1))){
				if (!memberReportScheduleService.isExist(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					try {
						List<MemberReport> memberReports = memberReportService.getList(obj_json.toString());
						if (memberReports != null) {
							for (MemberReport memberReport : memberReports) {
								memberReportService.delete(memberReport.getId());
							}
						}
						if (memberReportScheduleService.delete(id)) {
							responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else if (reportCode.equals(new Long(2))) {
				try {
					List<OrgReport> orgReports = orgReportService.getList(obj_json.toString());
					if(orgReports != null) {
						for (OrgReport orgReport : orgReports) {
							orgReportService.delete(orgReport.getId());
						}
					}
					
					if (orgReportScheduleService.delete(id)) {
						responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
						
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			}else {
				responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			}
			return responseJson.toString();		
	}


	
	
	
	
	
	
	
}