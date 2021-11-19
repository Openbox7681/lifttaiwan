package tw.gov.mohw.hisac.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.MemberReportScheduleService;
import tw.gov.mohw.hisac.web.service.MemberReportService;
import tw.gov.mohw.hisac.web.service.OrgReportScheduleService;
import tw.gov.mohw.hisac.web.service.OrgReportService;
import tw.gov.mohw.hisac.web.service.ReportService;
import tw.gov.mohw.hisac.web.service.SystemLogService;;

/**
 * 排程服務
 */
@Component
public class ReportScheduleService {
	final static Logger logger = LoggerFactory.getLogger(ReportScheduleService.class);
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private MemberReportScheduleService memberReportScheduleService;
	
	
	@Autowired
	private MemberReportService memberReportService;
	
	@Autowired
	private OrgReportScheduleService orgReportScheduleService;
	
	
	@Autowired
	private OrgReportService orgReportService;
	
	
	@Autowired
	private SystemLogService systemLogService;
	
	
//	@Async
//	@Scheduled(cron = "0 0 7 * * ?")
//	public void OldReportExecutePerDay() throws JSONException, ParseException{
//		String memberReportScheduleTime = memberReportScheduleService.getLastScheduleTime();
//
//		String orgReportScheduleTime =  orgReportScheduleService.getLastScheduleTime();
//		
//		for ( int i =0 ; i<30 ; i ++) {
//			Date memberReportScheduleTimeDt = new SimpleDateFormat("yyyy-MM-dd").parse(memberReportScheduleTime);
//			Calendar c = Calendar.getInstance(); 
//			c.setTime(memberReportScheduleTimeDt); 
//			c.add(Calendar.DATE, +i);
//			memberReportScheduleTimeDt = c.getTime();
//			
//			JSONObject responseJson = new JSONObject();
//			
//			String reportScheduleTime = WebDatetime.toString(memberReportScheduleTimeDt, "yyyy-MM-dd");
//
//			
//			JSONObject obj = new JSONObject();
//			obj.put("ReportScheduleTime", reportScheduleTime);
//			
//			if(memberReportService.isMemberReportExistByCreateReportTime(reportScheduleTime)) {
//				responseJson.put("msg" , "排程資料已存在");
//				responseJson.put("success", true);
//			}else {
//				JSONObject response  = (JSONObject) memberReportService.schedule(obj.toString());
//				if ( response.getBoolean("Success")) {
//									
//					//狀態為1 表示 排程執行成功 0則為失敗
//					memberReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), true, response.getString("Message"));
//					responseJson.put("success", true);
//					
//					systemLogService.insert("MemberReport", "executeOld", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "system");
//
//				}else {
//					//失敗則紀錄下log
//					memberReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), true, response.getString("Message"));
//					responseJson.put("success", false);
//					
//					systemLogService.insert("MemberReport", "executeOld", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "system");
//
//				}	
//			}
//			
//			
//			Date orgReportScheduleTimeDt = new SimpleDateFormat("yyyy-MM-dd").parse(orgReportScheduleTime);
//			Calendar cd = Calendar.getInstance(); 
//			cd.setTime(orgReportScheduleTimeDt); 
//			cd.add(Calendar.DATE, +i);
//			orgReportScheduleTimeDt = c.getTime();
//			
//			
//			reportScheduleTime = WebDatetime.toString(orgReportScheduleTimeDt, "yyyy-MM-dd");
//			
//			if(orgReportService.isOrgReportExistByCreateReportTime(reportScheduleTime)) {
//				responseJson.put("msg" , "排程資料已存在");
//				responseJson.put("success", true);	
//			}else {
//				JSONObject response  = (JSONObject) orgReportService.schedule(obj.toString());
//				if ( response.getBoolean("Success")) {
//					//狀態為1 表示 排程執行成功 0則為失敗
//					orgReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), true, response.getString("Message"));
//					responseJson.put("success", true);
//					
//					systemLogService.insert("OrgReport", "executeOld", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "system");
//
//				}else {
//					
//					//失敗則紀錄下log
//					orgReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), true, response.getString("Message"));
//					responseJson.put("success", false);
//					
//					systemLogService.insert("OrgReport", "executeOld", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "system");	
//				}
//				
//			}
//		}
//	}

	

	@Async
	@Scheduled(cron = "0 0 1 * * ?")
	public void schedule() {
		reportService.schedule();
		try {
			ReportExecutePerDay();
		}catch (Exception e) {
			e.printStackTrace();			
		}
		
		
	}
	
	@Async
	@Scheduled(cron = "0 0 4 * * ?")
	public void ReportExecutePerDay() throws JSONException, ParseException{
		
		
		//一天前
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, -1);
		dt = c.getTime();
		
		JSONObject responseJson = new JSONObject();
		
		String reportScheduleTime = WebDatetime.toString(dt, "yyyy-MM-dd");

		
		JSONObject obj = new JSONObject();
		obj.put("ReportScheduleTime", reportScheduleTime);
		
		if(memberReportService.isMemberReportExistByCreateReportTime(reportScheduleTime)) {
			responseJson.put("msg" , "排程資料已存在");
			responseJson.put("success", true);
		}else {
			JSONObject response  = (JSONObject) memberReportService.schedule(obj.toString());
			if ( response.getBoolean("Success")) {
								
				//狀態為1 表示 排程執行成功 0則為失敗
				memberReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
				responseJson.put("success", true);
				
				systemLogService.insert("MemberReport", "execute", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "system");

			}else {
				//失敗則紀錄下log
				memberReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
				responseJson.put("success", false);
				
				systemLogService.insert("MemberReport", "execute", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "system");

			}	
		}
		
		if(orgReportService.isOrgReportExistByCreateReportTime(reportScheduleTime)) {
			responseJson.put("msg" , "排程資料已存在");
			responseJson.put("success", true);	
		}else {
			JSONObject response  = (JSONObject) orgReportService.schedule(obj.toString());
			if ( response.getBoolean("Success")) {
				//狀態為1 表示 排程執行成功 0則為失敗
				orgReportScheduleService.insertData(new Long(1), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
				responseJson.put("success", true);
				
				systemLogService.insert("OrgReport", "execute", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "system");

			}else {
				
				//失敗則紀錄下log
				orgReportScheduleService.insertData(new Long(0), new SimpleDateFormat("yyyy-MM-dd").parse(reportScheduleTime), false, response.getString("Message"));
				responseJson.put("success", false);
				
				systemLogService.insert("OrgReport", "execute", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "system");

				
			}
			
		}
			
	}
	
	
	
	
	
}