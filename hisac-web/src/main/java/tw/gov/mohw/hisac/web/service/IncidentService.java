package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.IncidentDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.Incident;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewIncidentMember;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.dao.NotificationDAO;

/**
 * 事件管理服務
 */
@Service
public class IncidentService {
	@Autowired
	IncidentDAO incidentDAO;
	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRoleService memberRoleService;

	@Autowired
	private OrgService orgService;

	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private NotificationDAO notificationDAO;
	
	@Autowired
	private OrgSignService orgSignService;	
	
	

	/**
	 * 取得所有事件資料
	 * 
	 * @return 事件資料
	 */
	public List<Incident> getAll() {
		return incidentDAO.getAll();
	}

	/**
	 * 取得事件資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料
	 */
	public List<ViewIncidentMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得事件資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增事件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件資料
	 * @param isApply
	 *            是否允許
	 * @return 事件資料
	 */
	public Incident insert(Long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
            
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");                                                    
			Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");                                                          
			Long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");                                                       
			String contactorTel = obj.isNull("ContactorTel") == true ? null : obj.getString("ContactorTel");                                             
			String contactorFax = obj.isNull("ContactorFax") == true ? null : obj.getString("ContactorFax");                                             
			String contactorEmail = obj.isNull("ContactorEmail") == true ? null : obj.getString("ContactorEmail");                                       
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");            
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");  
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");                                                      
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");                                                      
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");                                                      
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");                                          
			int eventType = obj.isNull("EventType") == true ? 0 : obj.getInt("EventType");                                                               
			Boolean isEventType1Opt1 = obj.isNull("IsEventType1Opt1") == true ? false : obj.getBoolean("IsEventType1Opt1");                              
			Boolean isEventType1Opt2 = obj.isNull("IsEventType1Opt2") == true ? false : obj.getBoolean("IsEventType1Opt2");                              
			Boolean isEventType1Opt3 = obj.isNull("IsEventType1Opt3") == true ? false : obj.getBoolean("IsEventType1Opt3");                              
			Boolean isEventType1Opt4 = obj.isNull("IsEventType1Opt4") == true ? false : obj.getBoolean("IsEventType1Opt4");                              
			Boolean isEventType1Opt5 = obj.isNull("IsEventType1Opt5") == true ? false : obj.getBoolean("IsEventType1Opt5");                              
			Boolean isEventType1Opt6 = obj.isNull("IsEventType1Opt6") == true ? false : obj.getBoolean("IsEventType1Opt6");                              
			Boolean isEventType2Opt1 = obj.isNull("IsEventType2Opt1") == true ? false : obj.getBoolean("IsEventType2Opt1");                              
			Boolean isEventType2Opt2 = obj.isNull("IsEventType2Opt2") == true ? false : obj.getBoolean("IsEventType2Opt2");                              
			Boolean isEventType2Opt3 = obj.isNull("IsEventType2Opt3") == true ? false : obj.getBoolean("IsEventType2Opt3");                              
			Boolean isEventType2Opt4 = obj.isNull("IsEventType2Opt4") == true ? false : obj.getBoolean("IsEventType2Opt4");                              
			Boolean isEventType2Opt5 = obj.isNull("IsEventType2Opt5") == true ? false : obj.getBoolean("IsEventType2Opt5");                              
			Boolean isEventType3Opt1 = obj.isNull("IsEventType3Opt1") == true ? false : obj.getBoolean("IsEventType3Opt1");                              
			Boolean isEventType3Opt2 = obj.isNull("IsEventType3Opt2") == true ? false : obj.getBoolean("IsEventType3Opt2");                              
			Boolean isEventType4Opt1 = obj.isNull("IsEventType4Opt1") == true ? false : obj.getBoolean("IsEventType4Opt1");                              
			Boolean isEventType4Opt2 = obj.isNull("IsEventType4Opt2") == true ? false : obj.getBoolean("IsEventType4Opt2");                              
			Boolean isEventType4Opt3 = obj.isNull("IsEventType4Opt3") == true ? false : obj.getBoolean("IsEventType4Opt3");                              
			Boolean isEventType4Opt4 = obj.isNull("IsEventType4Opt4") == true ? false : obj.getBoolean("IsEventType4Opt4");
			String eventType5Other = obj.isNull("EventType5Other") == true ? null : obj.getString("EventType5Other");                                    
			String eventRemark = obj.isNull("EventRemark") == true ? null : obj.getString("EventRemark");                                                
			Boolean isEventCause1Opt1 = obj.isNull("IsEventCause1Opt1") == true ? false : obj.getBoolean("IsEventCause1Opt1");                           
			Boolean isEventCause1Opt2 = obj.isNull("IsEventCause1Opt2") == true ? false : obj.getBoolean("IsEventCause1Opt2");                           
			Boolean isEventCause1Opt3 = obj.isNull("IsEventCause1Opt3") == true ? false : obj.getBoolean("IsEventCause1Opt3");                           
			Boolean isEventCause1Opt4 = obj.isNull("IsEventCause1Opt4") == true ? false : obj.getBoolean("IsEventCause1Opt4");                           
			Boolean isEventCause1Opt5 = obj.isNull("IsEventCause1Opt5") == true ? false : obj.getBoolean("IsEventCause1Opt5");                           
			Boolean isEventCause1Opt6 = obj.isNull("IsEventCause1Opt6") == true ? false : obj.getBoolean("IsEventCause1Opt6");                           
			String eventEvaluation = obj.isNull("EventEvaluation") == true ? null : obj.getString("EventEvaluation");                                    
			String eventProcess = obj.isNull("EventProcess") == true ? null : obj.getString("EventProcess");                                             
			Boolean isSecuritySetting1Opt1 = obj.isNull("IsSecuritySetting1Opt1") == true ? false : obj.getBoolean("IsSecuritySetting1Opt1");            
			Boolean isSecuritySetting1Opt2 = obj.isNull("IsSecuritySetting1Opt2") == true ? false : obj.getBoolean("IsSecuritySetting1Opt2");            
			Boolean isSecuritySetting1Opt3 = obj.isNull("IsSecuritySetting1Opt3") == true ? false : obj.getBoolean("IsSecuritySetting1Opt3");            
			Boolean isSecuritySetting1Opt4 = obj.isNull("IsSecuritySetting1Opt4") == true ? false : obj.getBoolean("IsSecuritySetting1Opt4");            
			Boolean isSecuritySetting1Opt5 = obj.isNull("IsSecuritySetting1Opt5") == true ? false : obj.getBoolean("IsSecuritySetting1Opt5");            
			Boolean isSecuritySetting1Opt6 = obj.isNull("IsSecuritySetting1Opt6") == true ? false : obj.getBoolean("IsSecuritySetting1Opt6");            
			Boolean isSecuritySetting1Opt7 = obj.isNull("IsSecuritySetting1Opt7") == true ? false : obj.getBoolean("IsSecuritySetting1Opt7");            
			Boolean isSecuritySetting1Opt8 = obj.isNull("IsSecuritySetting1Opt8") == true ? false : obj.getBoolean("IsSecuritySetting1Opt8");            
			Boolean isSecuritySetting1Opt9 = obj.isNull("IsSecuritySetting1Opt9") == true ? false : obj.getBoolean("IsSecuritySetting1Opt9");            
			Boolean isSecuritySetting1Opt10 = obj.isNull("IsSecuritySetting1Opt10") == true ? false : obj.getBoolean("IsSecuritySetting1Opt10");         
			Boolean isSecuritySetting1Opt11 = obj.isNull("IsSecuritySetting1Opt11") == true ? false : obj.getBoolean("IsSecuritySetting1Opt11");         
			Boolean isSecuritySetting1Opt12 = obj.isNull("IsSecuritySetting1Opt12") == true ? false : obj.getBoolean("IsSecuritySetting1Opt12");         
			Boolean isSecuritySetting1Opt13 = obj.isNull("IsSecuritySetting1Opt13") == true ? false : obj.getBoolean("IsSecuritySetting1Opt13");         
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");                                          
			Date finishDateTime = obj.isNull("FinishDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("FinishDateTime"), "yyyy-MM-dd");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			String notificationId = obj.isNull("NotificationId") == true ? "" : obj.getString("NotificationId");			
			
			Boolean notificationIsCC3 = obj.isNull("NotificationIsCC3") == true ? false : obj.getBoolean("NotificationIsCC3");         			
			
			// 流程紀錄用 - 開始
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String postId = ticketQueueDAO.insertPostId(tableName, false, pre, "INC");
			// 流程紀錄用 - 結束

			// debug
//			System.out.println("IncidentService.java → insert() → isApply：" + isApply);
//			System.out.println("IncidentService.java → insert() → tableName：" + tableName);
//			System.out.println("IncidentService.java → insert() → pre：" + pre);
//			System.out.println("IncidentService.java → insert() → postId：" + postId);
			
			Date now = new Date();
			Incident entity = new Incident();

			// 流程紀錄用 - 開始
			if (isApply) {
				// 1-編輯中 → 2-事件處理中
				// 依 isApply 判斷是否要更新事件單編號(將暫存單號改成正式單號)
//				postId = ticketQueueDAO.updatePostId("incident", isApply, "HCERT", entity.getPostId(), "INC");
				postId = ticketQueueDAO.updatePostId("incident", isApply, "HCERT", postId, "INC");

				// debug
//				System.out.println("IncidentService.java → insert() → postId(新增即送出，改成正式編號)：" + postId);
				
			}
			// 流程紀錄用 - 結束

			entity.setPostId(postId);
			entity.setPostDateTime(postDateTime);
			entity.setReporterName(reporterName);
			entity.setHandleName(handleName);
			entity.setContactorId(contactorId);
			entity.setContactorTel(contactorTel);
			entity.setContactorFax(contactorFax);
			entity.setContactorEmail(contactorEmail);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);			
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setEventType(eventType);
			entity.setIsEventType1Opt1(isEventType1Opt1);
			entity.setIsEventType1Opt2(isEventType1Opt2);
			entity.setIsEventType1Opt3(isEventType1Opt3);
			entity.setIsEventType1Opt4(isEventType1Opt4);
			entity.setIsEventType1Opt5(isEventType1Opt5);
			entity.setIsEventType1Opt6(isEventType1Opt6);
			entity.setIsEventType2Opt1(isEventType2Opt1);
			entity.setIsEventType2Opt2(isEventType2Opt2);
			entity.setIsEventType2Opt3(isEventType2Opt3);
			entity.setIsEventType2Opt4(isEventType2Opt4);
			entity.setIsEventType2Opt5(isEventType2Opt5);
			entity.setIsEventType3Opt1(isEventType3Opt1);
			entity.setIsEventType3Opt2(isEventType3Opt2);
			entity.setIsEventType4Opt1(isEventType4Opt1);
			entity.setIsEventType4Opt2(isEventType4Opt2);
			entity.setIsEventType4Opt3(isEventType4Opt3);
			entity.setIsEventType4Opt4(isEventType4Opt4);
			entity.setEventType5Other(eventType5Other);
			entity.setEventRemark(eventRemark);
			entity.setIsEventCause1Opt1(isEventCause1Opt1);
			entity.setIsEventCause1Opt2(isEventCause1Opt2);
			entity.setIsEventCause1Opt3(isEventCause1Opt3);
			entity.setIsEventCause1Opt4(isEventCause1Opt4);
			entity.setIsEventCause1Opt5(isEventCause1Opt5);
			entity.setIsEventCause1Opt6(isEventCause1Opt6);
			entity.setEventEvaluation(eventEvaluation);
			entity.setEventProcess(eventProcess);
			entity.setIsSecuritySetting1Opt1(isSecuritySetting1Opt1);
			entity.setIsSecuritySetting1Opt2(isSecuritySetting1Opt2);
			entity.setIsSecuritySetting1Opt3(isSecuritySetting1Opt3);
			entity.setIsSecuritySetting1Opt4(isSecuritySetting1Opt4);
			entity.setIsSecuritySetting1Opt5(isSecuritySetting1Opt5);
			entity.setIsSecuritySetting1Opt6(isSecuritySetting1Opt6);
			entity.setIsSecuritySetting1Opt7(isSecuritySetting1Opt7);
			entity.setIsSecuritySetting1Opt8(isSecuritySetting1Opt8);
			entity.setIsSecuritySetting1Opt9(isSecuritySetting1Opt9);
			entity.setIsSecuritySetting1Opt10(isSecuritySetting1Opt10);
			entity.setIsSecuritySetting1Opt11(isSecuritySetting1Opt11);
			entity.setIsSecuritySetting1Opt12(isSecuritySetting1Opt12);
			entity.setIsSecuritySetting1Opt13(isSecuritySetting1Opt13);
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setNotificationId(notificationId);
			entity.setNotificationIsCC3(notificationIsCC3);

			// debug 
//			System.out.println("entity：" + entity);

			incidentDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新事件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件資料
	 * @param isApply
	 *            是否允許
	 * @return 事件資料
	 */
	public Incident update(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");                                                    
			Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");                                                          
			Long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");                                                       
			String contactorTel = obj.isNull("ContactorTel") == true ? null : obj.getString("ContactorTel");                                             
			String contactorFax = obj.isNull("ContactorFax") == true ? null : obj.getString("ContactorFax");                                             
			String contactorEmail = obj.isNull("ContactorEmail") == true ? null : obj.getString("ContactorEmail");                                       
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");    
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");  
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");                                                      
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");                                                      
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");                                                      
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");                                          
			int eventType = obj.isNull("EventType") == true ? 0 : obj.getInt("EventType");                                                               
			Boolean isEventType1Opt1 = obj.isNull("IsEventType1Opt1") == true ? false : obj.getBoolean("IsEventType1Opt1");                              
			Boolean isEventType1Opt2 = obj.isNull("IsEventType1Opt2") == true ? false : obj.getBoolean("IsEventType1Opt2");                              
			Boolean isEventType1Opt3 = obj.isNull("IsEventType1Opt3") == true ? false : obj.getBoolean("IsEventType1Opt3");                              
			Boolean isEventType1Opt4 = obj.isNull("IsEventType1Opt4") == true ? false : obj.getBoolean("IsEventType1Opt4");                              
			Boolean isEventType1Opt5 = obj.isNull("IsEventType1Opt5") == true ? false : obj.getBoolean("IsEventType1Opt5");                              
			Boolean isEventType1Opt6 = obj.isNull("IsEventType1Opt6") == true ? false : obj.getBoolean("IsEventType1Opt6");                              
			Boolean isEventType2Opt1 = obj.isNull("IsEventType2Opt1") == true ? false : obj.getBoolean("IsEventType2Opt1");                              
			Boolean isEventType2Opt2 = obj.isNull("IsEventType2Opt2") == true ? false : obj.getBoolean("IsEventType2Opt2");                              
			Boolean isEventType2Opt3 = obj.isNull("IsEventType2Opt3") == true ? false : obj.getBoolean("IsEventType2Opt3");                              
			Boolean isEventType2Opt4 = obj.isNull("IsEventType2Opt4") == true ? false : obj.getBoolean("IsEventType2Opt4");                              
			Boolean isEventType2Opt5 = obj.isNull("IsEventType2Opt5") == true ? false : obj.getBoolean("IsEventType2Opt5");                              
			Boolean isEventType3Opt1 = obj.isNull("IsEventType3Opt1") == true ? false : obj.getBoolean("IsEventType3Opt1");                              
			Boolean isEventType3Opt2 = obj.isNull("IsEventType3Opt2") == true ? false : obj.getBoolean("IsEventType3Opt2");                              
			Boolean isEventType4Opt1 = obj.isNull("IsEventType4Opt1") == true ? false : obj.getBoolean("IsEventType4Opt1");                              
			Boolean isEventType4Opt2 = obj.isNull("IsEventType4Opt2") == true ? false : obj.getBoolean("IsEventType4Opt2");                              
			Boolean isEventType4Opt3 = obj.isNull("IsEventType4Opt3") == true ? false : obj.getBoolean("IsEventType4Opt3");                              
			Boolean isEventType4Opt4 = obj.isNull("IsEventType4Opt4") == true ? false : obj.getBoolean("IsEventType4Opt4");
			String eventType5Other = obj.isNull("EventType5Other") == true ? null : obj.getString("EventType5Other");                                    
			String eventRemark = obj.isNull("EventRemark") == true ? null : obj.getString("EventRemark");                                                
			Boolean isEventCause1Opt1 = obj.isNull("IsEventCause1Opt1") == true ? false : obj.getBoolean("IsEventCause1Opt1");                           
			Boolean isEventCause1Opt2 = obj.isNull("IsEventCause1Opt2") == true ? false : obj.getBoolean("IsEventCause1Opt2");                           
			Boolean isEventCause1Opt3 = obj.isNull("IsEventCause1Opt3") == true ? false : obj.getBoolean("IsEventCause1Opt3");                           
			Boolean isEventCause1Opt4 = obj.isNull("IsEventCause1Opt4") == true ? false : obj.getBoolean("IsEventCause1Opt4");                           
			Boolean isEventCause1Opt5 = obj.isNull("IsEventCause1Opt5") == true ? false : obj.getBoolean("IsEventCause1Opt5");                           
			Boolean isEventCause1Opt6 = obj.isNull("IsEventCause1Opt6") == true ? false : obj.getBoolean("IsEventCause1Opt6");                           
			String eventEvaluation = obj.isNull("EventEvaluation") == true ? null : obj.getString("EventEvaluation");                                    
			String eventProcess = obj.isNull("EventProcess") == true ? null : obj.getString("EventProcess");                                             
			Boolean isSecuritySetting1Opt1 = obj.isNull("IsSecuritySetting1Opt1") == true ? false : obj.getBoolean("IsSecuritySetting1Opt1");            
			Boolean isSecuritySetting1Opt2 = obj.isNull("IsSecuritySetting1Opt2") == true ? false : obj.getBoolean("IsSecuritySetting1Opt2");            
			Boolean isSecuritySetting1Opt3 = obj.isNull("IsSecuritySetting1Opt3") == true ? false : obj.getBoolean("IsSecuritySetting1Opt3");            
			Boolean isSecuritySetting1Opt4 = obj.isNull("IsSecuritySetting1Opt4") == true ? false : obj.getBoolean("IsSecuritySetting1Opt4");            
			Boolean isSecuritySetting1Opt5 = obj.isNull("IsSecuritySetting1Opt5") == true ? false : obj.getBoolean("IsSecuritySetting1Opt5");            
			Boolean isSecuritySetting1Opt6 = obj.isNull("IsSecuritySetting1Opt6") == true ? false : obj.getBoolean("IsSecuritySetting1Opt6");            
			Boolean isSecuritySetting1Opt7 = obj.isNull("IsSecuritySetting1Opt7") == true ? false : obj.getBoolean("IsSecuritySetting1Opt7");            
			Boolean isSecuritySetting1Opt8 = obj.isNull("IsSecuritySetting1Opt8") == true ? false : obj.getBoolean("IsSecuritySetting1Opt8");            
			Boolean isSecuritySetting1Opt9 = obj.isNull("IsSecuritySetting1Opt9") == true ? false : obj.getBoolean("IsSecuritySetting1Opt9");            
			Boolean isSecuritySetting1Opt10 = obj.isNull("IsSecuritySetting1Opt10") == true ? false : obj.getBoolean("IsSecuritySetting1Opt10");         
			Boolean isSecuritySetting1Opt11 = obj.isNull("IsSecuritySetting1Opt11") == true ? false : obj.getBoolean("IsSecuritySetting1Opt11");         
			Boolean isSecuritySetting1Opt12 = obj.isNull("IsSecuritySetting1Opt12") == true ? false : obj.getBoolean("IsSecuritySetting1Opt12");         
			Boolean isSecuritySetting1Opt13 = obj.isNull("IsSecuritySetting1Opt13") == true ? false : obj.getBoolean("IsSecuritySetting1Opt13");         
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");                                          
			Date finishDateTime = obj.isNull("FinishDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("FinishDateTime"), "yyyy-MM-dd");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			
			Date now = new Date();
			Incident entity = incidentDAO.get(id);
			String postId = "";

			// debug
//			System.out.println("IncidentService.java → update() → isApply：" + isApply);
//			System.out.println("IncidentService.java → update() → status：" + status);
			
			// 流程紀錄用 - 開始
			// 依 isApply 判斷是否要更新事件單編號(將暫存單號改成正式單號)
			if (isApply) {
				// 1-編輯中 → 2-事件處理中
				//if (status == new Long(2)) {
				if (status == 2) {
					postId = ticketQueueDAO.updatePostId("incident", isApply, "HCERT", entity.getPostId(), "INC");
					entity.setPostId(postId);

					// debug
//					System.out.println("IncidentService.java → update() → postId(改成正式編號)：" + postId);
					
				} else {
					//postId = ticketQueueDAO.updatePostId("incident", false, "HCERT", entity.getPostId(), "INC");

					// debug
					//System.out.println("IncidentService.java → update() → postId(維持暫存編號)：" + postId);
					
				}
			} else {
				//postId = ticketQueueDAO.updatePostId("incident", isApply, "HCERT", entity.getPostId(), "INC");

				// debug
				//System.out.println("IncidentService.java → update() → postId(維持暫存編號)：" + postId);
				
			}
			// 流程紀錄用 - 結束

			entity.setPostDateTime(postDateTime);
			entity.setReporterName(reporterName);
			entity.setHandleName(handleName);
			entity.setContactorId(contactorId);
			entity.setContactorTel(contactorTel);
			entity.setContactorFax(contactorFax);
			entity.setContactorEmail(contactorEmail);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);			
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setEventType(eventType);
			entity.setIsEventType1Opt1(isEventType1Opt1);
			entity.setIsEventType1Opt2(isEventType1Opt2);
			entity.setIsEventType1Opt3(isEventType1Opt3);
			entity.setIsEventType1Opt4(isEventType1Opt4);
			entity.setIsEventType1Opt5(isEventType1Opt5);
			entity.setIsEventType1Opt6(isEventType1Opt6);
			entity.setIsEventType2Opt1(isEventType2Opt1);
			entity.setIsEventType2Opt2(isEventType2Opt2);
			entity.setIsEventType2Opt3(isEventType2Opt3);
			entity.setIsEventType2Opt4(isEventType2Opt4);
			entity.setIsEventType2Opt5(isEventType2Opt5);
			entity.setIsEventType3Opt1(isEventType3Opt1);
			entity.setIsEventType3Opt2(isEventType3Opt2);
			entity.setIsEventType4Opt1(isEventType4Opt1);
			entity.setIsEventType4Opt2(isEventType4Opt2);
			entity.setIsEventType4Opt3(isEventType4Opt3);
			entity.setIsEventType4Opt4(isEventType4Opt4);
			entity.setEventType5Other(eventType5Other);
			entity.setEventRemark(eventRemark);
			entity.setIsEventCause1Opt1(isEventCause1Opt1);
			entity.setIsEventCause1Opt2(isEventCause1Opt2);
			entity.setIsEventCause1Opt3(isEventCause1Opt3);
			entity.setIsEventCause1Opt4(isEventCause1Opt4);
			entity.setIsEventCause1Opt5(isEventCause1Opt5);
			entity.setIsEventCause1Opt6(isEventCause1Opt6);
			entity.setEventEvaluation(eventEvaluation);
			entity.setEventProcess(eventProcess);
			entity.setIsSecuritySetting1Opt1(isSecuritySetting1Opt1);
			entity.setIsSecuritySetting1Opt2(isSecuritySetting1Opt2);
			entity.setIsSecuritySetting1Opt3(isSecuritySetting1Opt3);
			entity.setIsSecuritySetting1Opt4(isSecuritySetting1Opt4);
			entity.setIsSecuritySetting1Opt5(isSecuritySetting1Opt5);
			entity.setIsSecuritySetting1Opt6(isSecuritySetting1Opt6);
			entity.setIsSecuritySetting1Opt7(isSecuritySetting1Opt7);
			entity.setIsSecuritySetting1Opt8(isSecuritySetting1Opt8);
			entity.setIsSecuritySetting1Opt9(isSecuritySetting1Opt9);
			entity.setIsSecuritySetting1Opt10(isSecuritySetting1Opt10);
			entity.setIsSecuritySetting1Opt11(isSecuritySetting1Opt11);
			entity.setIsSecuritySetting1Opt12(isSecuritySetting1Opt12);
			entity.setIsSecuritySetting1Opt13(isSecuritySetting1Opt13);
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);

			// debug 
//			System.out.println("entity：" + entity);

			incidentDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新事件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件資料
	 * @param isApply
	 *            是否允許
	 * @return 事件資料
	 */
	public Incident modify(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			Long reporterName = obj.isNull("ReporterName") == true ? 0 : obj.getLong("ReporterName");                                                    
			Long handleName = obj.isNull("HandleName") == true ? 0 : obj.getLong("HandleName");                                                          
			Long contactorId = obj.isNull("ContactorId") == true ? 0 : obj.getLong("ContactorId");                                                       
			String contactorTel = obj.isNull("ContactorTel") == true ? null : obj.getString("ContactorTel");                                             
			String contactorFax = obj.isNull("ContactorFax") == true ? null : obj.getString("ContactorFax");                                             
			String contactorEmail = obj.isNull("ContactorEmail") == true ? null : obj.getString("ContactorEmail");                                       
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			int hostAmount = obj.isNull("HostAmount") == true ? 0 : obj.getInt("HostAmount");    
			int serverAmount = obj.isNull("ServerAmount") == true ? 0 : obj.getInt("ServerAmount");  
			int informationAmount = obj.isNull("InformationAmount") == true ? 0 : obj.getInt("InformationAmount");
			int otherDeviceAmount = obj.isNull("OtherDeviceAmount") == true ? 0 : obj.getInt("OtherDeviceAmount");
			String otherDeviceName = obj.isNull("OtherDeviceName") == true ? null : obj.getString("OtherDeviceName");
			String deviceRemark = obj.isNull("DeviceRemark") == true ? null : obj.getString("DeviceRemark");
			String assessDamage = obj.isNull("AssessDamage") == true ? null : obj.getString("AssessDamage");
			String assessDamageRemark = obj.isNull("AssessDamageRemark") == true ? null : obj.getString("AssessDamageRemark");
			Boolean isOsOpt1 = obj.isNull("IsOsOpt1") == true ? false : obj.getBoolean("IsOsOpt1");                                                      
			Boolean isOsOpt2 = obj.isNull("IsOsOpt2") == true ? false : obj.getBoolean("IsOsOpt2");                                                      
			Boolean isOsOpt3 = obj.isNull("IsOsOpt3") == true ? false : obj.getBoolean("IsOsOpt3");                                                      
			String isOsOpt3Other = obj.isNull("IsOsOpt3Other") == true ? null : obj.getString("IsOsOpt3Other");                                          
			int eventType = obj.isNull("EventType") == true ? 0 : obj.getInt("EventType");                                                               
			Boolean isEventType1Opt1 = obj.isNull("IsEventType1Opt1") == true ? false : obj.getBoolean("IsEventType1Opt1");                              
			Boolean isEventType1Opt2 = obj.isNull("IsEventType1Opt2") == true ? false : obj.getBoolean("IsEventType1Opt2");                              
			Boolean isEventType1Opt3 = obj.isNull("IsEventType1Opt3") == true ? false : obj.getBoolean("IsEventType1Opt3");                              
			Boolean isEventType1Opt4 = obj.isNull("IsEventType1Opt4") == true ? false : obj.getBoolean("IsEventType1Opt4");                              
			Boolean isEventType1Opt5 = obj.isNull("IsEventType1Opt5") == true ? false : obj.getBoolean("IsEventType1Opt5");                              
			Boolean isEventType1Opt6 = obj.isNull("IsEventType1Opt6") == true ? false : obj.getBoolean("IsEventType1Opt6");                              
			Boolean isEventType2Opt1 = obj.isNull("IsEventType2Opt1") == true ? false : obj.getBoolean("IsEventType2Opt1");                              
			Boolean isEventType2Opt2 = obj.isNull("IsEventType2Opt2") == true ? false : obj.getBoolean("IsEventType2Opt2");                              
			Boolean isEventType2Opt3 = obj.isNull("IsEventType2Opt3") == true ? false : obj.getBoolean("IsEventType2Opt3");                              
			Boolean isEventType2Opt4 = obj.isNull("IsEventType2Opt4") == true ? false : obj.getBoolean("IsEventType2Opt4");                              
			Boolean isEventType2Opt5 = obj.isNull("IsEventType2Opt5") == true ? false : obj.getBoolean("IsEventType2Opt5");                              
			Boolean isEventType3Opt1 = obj.isNull("IsEventType3Opt1") == true ? false : obj.getBoolean("IsEventType3Opt1");                              
			Boolean isEventType3Opt2 = obj.isNull("IsEventType3Opt2") == true ? false : obj.getBoolean("IsEventType3Opt2");                              
			Boolean isEventType4Opt1 = obj.isNull("IsEventType4Opt1") == true ? false : obj.getBoolean("IsEventType4Opt1");                              
			Boolean isEventType4Opt2 = obj.isNull("IsEventType4Opt2") == true ? false : obj.getBoolean("IsEventType4Opt2");                              
			Boolean isEventType4Opt3 = obj.isNull("IsEventType4Opt3") == true ? false : obj.getBoolean("IsEventType4Opt3");                              
			Boolean isEventType4Opt4 = obj.isNull("IsEventType4Opt4") == true ? false : obj.getBoolean("IsEventType4Opt4");
			String eventType5Other = obj.isNull("EventType5Other") == true ? null : obj.getString("EventType5Other");                                    
			String eventRemark = obj.isNull("EventRemark") == true ? null : obj.getString("EventRemark");                                                
			Boolean isEventCause1Opt1 = obj.isNull("IsEventCause1Opt1") == true ? false : obj.getBoolean("IsEventCause1Opt1");                           
			Boolean isEventCause1Opt2 = obj.isNull("IsEventCause1Opt2") == true ? false : obj.getBoolean("IsEventCause1Opt2");                           
			Boolean isEventCause1Opt3 = obj.isNull("IsEventCause1Opt3") == true ? false : obj.getBoolean("IsEventCause1Opt3");                           
			Boolean isEventCause1Opt4 = obj.isNull("IsEventCause1Opt4") == true ? false : obj.getBoolean("IsEventCause1Opt4");                           
			Boolean isEventCause1Opt5 = obj.isNull("IsEventCause1Opt5") == true ? false : obj.getBoolean("IsEventCause1Opt5");                           
			Boolean isEventCause1Opt6 = obj.isNull("IsEventCause1Opt6") == true ? false : obj.getBoolean("IsEventCause1Opt6");                           
			String eventEvaluation = obj.isNull("EventEvaluation") == true ? null : obj.getString("EventEvaluation");                                    
			String eventProcess = obj.isNull("EventProcess") == true ? null : obj.getString("EventProcess");                                             
			Boolean isSecuritySetting1Opt1 = obj.isNull("IsSecuritySetting1Opt1") == true ? false : obj.getBoolean("IsSecuritySetting1Opt1");            
			Boolean isSecuritySetting1Opt2 = obj.isNull("IsSecuritySetting1Opt2") == true ? false : obj.getBoolean("IsSecuritySetting1Opt2");            
			Boolean isSecuritySetting1Opt3 = obj.isNull("IsSecuritySetting1Opt3") == true ? false : obj.getBoolean("IsSecuritySetting1Opt3");            
			Boolean isSecuritySetting1Opt4 = obj.isNull("IsSecuritySetting1Opt4") == true ? false : obj.getBoolean("IsSecuritySetting1Opt4");            
			Boolean isSecuritySetting1Opt5 = obj.isNull("IsSecuritySetting1Opt5") == true ? false : obj.getBoolean("IsSecuritySetting1Opt5");            
			Boolean isSecuritySetting1Opt6 = obj.isNull("IsSecuritySetting1Opt6") == true ? false : obj.getBoolean("IsSecuritySetting1Opt6");            
			Boolean isSecuritySetting1Opt7 = obj.isNull("IsSecuritySetting1Opt7") == true ? false : obj.getBoolean("IsSecuritySetting1Opt7");            
			Boolean isSecuritySetting1Opt8 = obj.isNull("IsSecuritySetting1Opt8") == true ? false : obj.getBoolean("IsSecuritySetting1Opt8");            
			Boolean isSecuritySetting1Opt9 = obj.isNull("IsSecuritySetting1Opt9") == true ? false : obj.getBoolean("IsSecuritySetting1Opt9");            
			Boolean isSecuritySetting1Opt10 = obj.isNull("IsSecuritySetting1Opt10") == true ? false : obj.getBoolean("IsSecuritySetting1Opt10");         
			Boolean isSecuritySetting1Opt11 = obj.isNull("IsSecuritySetting1Opt11") == true ? false : obj.getBoolean("IsSecuritySetting1Opt11");         
			Boolean isSecuritySetting1Opt12 = obj.isNull("IsSecuritySetting1Opt12") == true ? false : obj.getBoolean("IsSecuritySetting1Opt12");         
			Boolean isSecuritySetting1Opt13 = obj.isNull("IsSecuritySetting1Opt13") == true ? false : obj.getBoolean("IsSecuritySetting1Opt13");         
			String finishDoOther = obj.isNull("FinishDoOther") == true ? null : obj.getString("FinishDoOther");                                          
			Date finishDateTime = obj.isNull("FinishDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("FinishDateTime"), "yyyy-MM-dd");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			
			Date now = new Date();
			Incident entity = incidentDAO.get(id);

			entity.setPostDateTime(postDateTime);
			entity.setReporterName(reporterName);
			entity.setHandleName(handleName);
			entity.setContactorId(contactorId);
			entity.setContactorTel(contactorTel);
			entity.setContactorFax(contactorFax);
			entity.setContactorEmail(contactorEmail);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setHostAmount(hostAmount);
			entity.setServerAmount(serverAmount);			
			entity.setInformationAmount(informationAmount);
			entity.setOtherDeviceAmount(otherDeviceAmount);
			entity.setOtherDeviceName(otherDeviceName);
			entity.setDeviceRemark(deviceRemark);
			entity.setAssessDamage(assessDamage);
			entity.setAssessDamageRemark(assessDamageRemark);
			entity.setIsOsOpt1(isOsOpt1);
			entity.setIsOsOpt2(isOsOpt2);
			entity.setIsOsOpt3(isOsOpt3);
			entity.setIsOsOpt3Other(isOsOpt3Other);
			entity.setEventType(eventType);
			entity.setIsEventType1Opt1(isEventType1Opt1);
			entity.setIsEventType1Opt2(isEventType1Opt2);
			entity.setIsEventType1Opt3(isEventType1Opt3);
			entity.setIsEventType1Opt4(isEventType1Opt4);
			entity.setIsEventType1Opt5(isEventType1Opt5);
			entity.setIsEventType1Opt6(isEventType1Opt6);
			entity.setIsEventType2Opt1(isEventType2Opt1);
			entity.setIsEventType2Opt2(isEventType2Opt2);
			entity.setIsEventType2Opt3(isEventType2Opt3);
			entity.setIsEventType2Opt4(isEventType2Opt4);
			entity.setIsEventType2Opt5(isEventType2Opt5);
			entity.setIsEventType3Opt1(isEventType3Opt1);
			entity.setIsEventType3Opt2(isEventType3Opt2);
			entity.setIsEventType4Opt1(isEventType4Opt1);
			entity.setIsEventType4Opt2(isEventType4Opt2);
			entity.setIsEventType4Opt3(isEventType4Opt3);
			entity.setIsEventType4Opt4(isEventType4Opt4);
			entity.setEventType5Other(eventType5Other);
			entity.setEventRemark(eventRemark);
			entity.setIsEventCause1Opt1(isEventCause1Opt1);
			entity.setIsEventCause1Opt2(isEventCause1Opt2);
			entity.setIsEventCause1Opt3(isEventCause1Opt3);
			entity.setIsEventCause1Opt4(isEventCause1Opt4);
			entity.setIsEventCause1Opt5(isEventCause1Opt5);
			entity.setIsEventCause1Opt6(isEventCause1Opt6);
			entity.setEventEvaluation(eventEvaluation);
			entity.setEventProcess(eventProcess);
			entity.setIsSecuritySetting1Opt1(isSecuritySetting1Opt1);
			entity.setIsSecuritySetting1Opt2(isSecuritySetting1Opt2);
			entity.setIsSecuritySetting1Opt3(isSecuritySetting1Opt3);
			entity.setIsSecuritySetting1Opt4(isSecuritySetting1Opt4);
			entity.setIsSecuritySetting1Opt5(isSecuritySetting1Opt5);
			entity.setIsSecuritySetting1Opt6(isSecuritySetting1Opt6);
			entity.setIsSecuritySetting1Opt7(isSecuritySetting1Opt7);
			entity.setIsSecuritySetting1Opt8(isSecuritySetting1Opt8);
			entity.setIsSecuritySetting1Opt9(isSecuritySetting1Opt9);
			entity.setIsSecuritySetting1Opt10(isSecuritySetting1Opt10);
			entity.setIsSecuritySetting1Opt11(isSecuritySetting1Opt11);
			entity.setIsSecuritySetting1Opt12(isSecuritySetting1Opt12);
			entity.setIsSecuritySetting1Opt13(isSecuritySetting1Opt13);
			entity.setFinishDoOther(finishDoOther);
			entity.setFinishDateTime(finishDateTime);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			
			incidentDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 停用事件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件資料
	 * @return 事件資料
	 */
	public Incident disable(long memberId, Long id) {
		try {
			Date now = new Date();
			Incident entity = incidentDAO.get(id);

			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			
			incidentDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除事件資料
	 * 
	 * @param id
	 *            事件管理Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			Incident entity = incidentDAO.get(id);
			incidentDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 事件資料是否存在
	 * 
	 * @param id
	 *            事件資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return incidentDAO.get(id) != null;
	}

	/**
	 * 取得事件資料
	 * 
	 * @param id
	 *            事件資料Id
	 * @return 事件資料
	 */
	public Incident get(Long id) {
		return incidentDAO.get(id);
	}
	
	/**
	 * 取得事件資料
	 * 
	 * @param notificationId
	 *           notificationId
	 * @return 事件資料
	 */
	public Incident getByNotificationId(String notificationId) {
		return incidentDAO.getByNotificationId(notificationId);
	}	

	/**
	 * 取得事件資料
	 * 
	 * @param id
	 *            事件資料Id
	 * @return 事件資料
	 */
	public ViewIncidentMember getByDetail(Long id) {
		return incidentDAO.getByDetail(id);
	}

	/**
	 * 審核事件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            事件Id
	 * @param status
	 *            String
	 * @return 事件資料
	 */
	public Incident examine(long memberId, String id, String status, String json) {
		try {
			Date now = new Date();
			Incident entity = incidentDAO.get(Long.parseLong(id, 10));

			Long reporterName = entity.getReporterName();
			Long handleName = entity.getHandleName();
			Long contactorId = entity.getContactorId();
			
			// 取得事件處理單位聯絡人資料
			Member member = memberService.get(contactorId);

			// 取得 SubStatus 資料
			Incident subEntity = incidentDAO.get(Long.parseLong(id, 10));
			Long subStatus = subEntity.getSubStatus();

			// debug
//			System.out.println("==========================================================");
//			System.out.println("IncidentService.java → examine() → memberId：" + memberId);
//			System.out.println("IncidentService.java → examine() → id：" + id);
//			System.out.println("IncidentService.java → examine() → status(變更前)：" + status);
//			System.out.println("IncidentService.java → examine() → subStatus(變更前)：" + subStatus);
//			System.out.println("IncidentService.java → examine() → ReporterName(會員機構)：" + reporterName);
//			System.out.println("IncidentService.java → examine() → HandleName(事件處理單位)：" + handleName);
//			System.out.println("IncidentService.java → examine() → ContactorId(事件處理單位聯絡人)：" + contactorId);
			
			// 3-事件處理審核中 → 4-事件處理中(退回)
			if (status.equals("4")) {

				// 寄發通知信 to 17.事件處理單位聯絡人：IsEventHandlingUnitContact
				// 收件者：entity.getEmail()
				// 主旨：CERT事件處理單(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所填報之事件處理單(entity.getPostId())，經審核者審核退回，目前正等待您進行修正，請您儘快撥冗進行處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To4SubjectToIsEventHandlingUnitContact"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To4BodyToIsEventHandlingUnitContact"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

				// debug
//				System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 4-事件處理中(退回)】寄發通知信 to 17.事件處理單位聯絡人：IsEventHandlingUnitContact - MemberId(符合)=" + member.getId());
//				System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 4-事件處理中(退回)】寄發通知信 to 17.事件處理單位聯絡人：IsEventHandlingUnitContact - OrgId=" + member.getOrgId() + " - Name=" + member.getName());

				// 更新 事件處理 incident table 的 SubStatus to null
				subStatus = new Long(0);

			}

			// 3-事件處理審核中 → 5-已結案
			// 31-事件處理審核中(第一次審)-10.會員機構聯絡人
			// 32-事件處理審核中(第二次審)-17.H-CERT事件處理審核者
			else if (status.equals("5")) {

				// debug
//				System.err.println("IncidentService.java → examine() → 【3-事件處理審核中 → 4-事件處理中(退回)】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - status)=" + status);
//				System.err.println("IncidentService.java → examine() → 【3-事件處理審核中 → 4-事件處理中(退回)】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - subStatus=" + subStatus.toString());

				if (subStatus.equals(Long.parseLong("31"))) {
					
					// 寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign
					// 收件者：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getEmail()
					// 主旨：CERT事件處理單("not.getPostId()")審核通知
					// 內容：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getName()，您好！CERT事件處理單("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(17);
					
//					System.err.println("IncidentService.java → examine() → 【3-事件處理審核中 → 4-事件處理中(退回)】寄發通知信 to 18.事件處理單位聯絡人：IsEventHandlingUnitContact - memberRoles=" + memberRoles.toString());

					if (memberRoles != null) {

						// 取得 [會員機構] 欄位對應之權責單位資料
//						List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(reporterName);
//						Long parentOrgId = null;
//						
//						if (orgs != null) {
//							for (ViewOrgOrgSign org : orgs) {
//								parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)
//								break;
//							}
//						}  
//						
						// debug
//						System.out.println("IncidentService.java → examine() → parentOrgId：" + parentOrgId);
						
						for (ViewMemberRoleMember memberRole : memberRoles) {
							
							Member subMember = memberService.get(memberRole.getMemberId());

							// debug
//							System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】(第二次審核)寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
							
							// 祇寄給權責單位事件處理審核者
							//if (subMember != null && subMember.getIsEnable() && subMember.getOrgId().equals(parentOrgId)) {
							if (subMember != null && subMember.getIsEnable()) {
								
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To31SubjectToIsHCERTContentSign"), subEntity.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To31BodyToIsHCERTContentSign"), subMember.getName(), subEntity.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, mailSubject, mailBody, null);

								// debug
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】(第二次審核)寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign - MemberId(符合)=" + memberRole.getMemberId());
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】(第二次審核)寄發通知信 to 17.H-CERT審核者：IsHCERTContentSign - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());

								// 更新 事件處理 incident table 的 SubStatus 31 to 32
								status = "3";
								subStatus = new Long(32);
								
								// 符合一次，處理完成即跳出迴圈
								break;
							}
						}
					}
					
				} else if (subStatus.equals(Long.parseLong("32"))) {

					// 更新事件通報單對應欄位值
					Notification notificationEntity = notificationDAO.get(subEntity.getNotificationId());
					Date notificationNow = new Date();

					// debug
					// System.out.println("IncidentService.java → examine() → subEntity.getNotificationId() = " + subEntity.getNotificationId());

					notificationEntity.setHostAmount(subEntity.getHostAmount()); // 受害主機數量-總計
					notificationEntity.setIsOsOpt1(subEntity.getIsOsOpt1()); // 作業系統名稱、版本-選項1-1
					notificationEntity.setIsOsOpt2(subEntity.getIsOsOpt2()); // 作業系統名稱、版本-選項1-2
					notificationEntity.setIsOsOpt3(subEntity.getIsOsOpt3()); // 作業系統名稱、版本-選項1-3
					notificationEntity.setIsOsOpt3Other(subEntity.getIsOsOpt3Other()); // 作業系統名稱、版本-選項1-3-other
					notificationEntity.setIsFinish1DoSysOpt1(subEntity.getIsSecuritySetting1Opt1()); // Ⅰ. 補強系統/程式安全設定-選項1
					notificationEntity.setIsFinish1DoSysOpt2(subEntity.getIsSecuritySetting1Opt2()); // Ⅰ. 補強系統/程式安全設定-選項2
					notificationEntity.setIsFinish1DoSysOpt3(subEntity.getIsSecuritySetting1Opt3()); // Ⅰ. 補強系統/程式安全設定-選項3
					notificationEntity.setIsFinish1DoSysOpt4(subEntity.getIsSecuritySetting1Opt4()); // Ⅰ. 補強系統/程式安全設定-選項4
					notificationEntity.setIsFinish1DoSysOpt5(subEntity.getIsSecuritySetting1Opt5()); // Ⅰ. 補強系統/程式安全設定-選項5
					notificationEntity.setIsFinish1DoSysOpt6(subEntity.getIsSecuritySetting1Opt6()); // Ⅰ. 補強系統/程式安全設定-選項6
					notificationEntity.setIsFinish1DoSysOpt7(subEntity.getIsSecuritySetting1Opt7()); // Ⅰ. 補強系統/程式安全設定-選項7
					notificationEntity.setIsFinish1DoSysOpt8(subEntity.getIsSecuritySetting1Opt8()); // Ⅰ. 補強系統/程式安全設定-選項8
					notificationEntity.setIsFinish1DoSysOpt9(subEntity.getIsSecuritySetting1Opt9()); // Ⅰ. 補強系統/程式安全設定-選項9
					notificationEntity.setIsFinish1DoSysOpt10(subEntity.getIsSecuritySetting1Opt10()); // Ⅰ. 補強系統/程式安全設定-選項10
					notificationEntity.setIsFinish1DoEduOpt1(subEntity.getIsSecuritySetting1Opt11()); // Ⅱ. 資安管理與教育訓練-選項1
					notificationEntity.setIsFinish1DoEduOpt3(subEntity.getIsSecuritySetting1Opt12()); // Ⅱ. 資安管理與教育訓練-選項3
					notificationEntity.setIsFinish1DoEduOpt4(subEntity.getIsSecuritySetting1Opt13()); // Ⅱ. 資安管理與教育訓練-選項4
					notificationEntity.setFinishDoOther(subEntity.getFinishDoOther()); // 其他相關安全處置
					
					notificationEntity.setIsOsOpt1(subEntity.getIsOsOpt1());
					notificationEntity.setIsOsOpt2(subEntity.getIsOsOpt2());
					notificationEntity.setIsOsOpt3(subEntity.getIsOsOpt3());
					notificationEntity.setIsOsOpt3Other(subEntity.getIsOsOpt3Other());	
					
					notificationEntity.setHostAmount(subEntity.getHostAmount());							
					notificationEntity.setInformationAmount(subEntity.getInformationAmount());
					notificationEntity.setServerAmount(subEntity.getServerAmount());
					notificationEntity.setOtherDeviceAmount(subEntity.getOtherDeviceAmount());
					notificationEntity.setOtherDeviceName(subEntity.getOtherDeviceName());
					notificationEntity.setDeviceRemark(subEntity.getDeviceRemark());
					notificationEntity.setAssessDamage(subEntity.getAssessDamage());
					notificationEntity.setAssessDamageRemark(subEntity.getAssessDamageRemark());					
											
					notificationEntity.setModifyId(member.getId());
					notificationEntity.setModifyTime(notificationNow);
										

					//寄信給通報建立者
					
					Member createMember = memberService.get(notificationEntity.getCreateId());
					String mailSubject_createMember = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Subject"), notificationEntity.getPostId());
					String mailBody_createMember = MessageFormat.format(resourceMessageService.getMessageValue("mailNotification4To5-2Body"), createMember.getName(), notificationEntity.getPostId());
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), createMember.getEmail(), createMember.getSpareEmail(), null, mailSubject_createMember, mailBody_createMember, null);													
									
				
					
					// debug
//					System.out.println("IncidentService.java → examine() → notificationEntity(更新前) = " + notificationEntity.toString());

					if (notificationEntity.getStatus() == 4)						
						notificationDAO.update(notificationEntity);

					// debug
//					System.out.println("IncidentService.java → examine() → notificationEntity(更新後) = " + notificationEntity.toString());

					processLogService.insert(member.getId(), "", String.valueOf(notificationEntity.getId()));
					
					
					
					
					
					
					
					// 寄發通知信 to 18.事件處理單位聯絡人 ：IsEventHandlingUnitContact
					// 收件者：entity.getEmail()
					// 主旨：CERT事件處理單(entity.getPostId())結案通知
					// 內容：entity.getName()，您好！事件處理單(entity.getPostId())，經審核確認結案，特此通知，謝謝！
					String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5SubjectToIsEventHandlingUnitContact"), entity.getPostId());
					String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5BodyToIsEventHandlingUnitContact"), member.getName(), entity.getPostId());
					mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
					

					// 寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact
					// 收件者：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getEmail()
					// 主旨：CERT事件處理單("not.getPostId()")結案副知
					// 內容：memberService.get((memberRoleService.findByRoleId(10)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，經審核確認結案，特此通知，謝謝！
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(10);
					
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							
							Member subMember = memberService.get(memberRole.getMemberId());

							// debug
//							System.out.println("i01_IncidentController.java → Update() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - MemberId=" + memberRole.getMemberId());
							
							// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
							if (subMember != null && subMember.getIsEnable() && subMember.getOrgId().equals(reporterName)) {
								String subMailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5SubjectToIsMemberContact"), subEntity.getPostId());
								String subMailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5BodyToIsMemberContact"), subMember.getName(), subEntity.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, subMailSubject, subMailBody, null);

								// debug
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - MemberId(符合)=" + memberRole.getMemberId());
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 10.會員機構聯絡人：IsMemberContact - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());

								// 符合一次，處理完成即跳出迴圈
								break;
							}
						}
					}
					
					
					// 寄發通知信(副知) to15.權責單位通報審核者：IsApplySingAdmin
					// 收件者：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getEmail()
					// 主旨：CERT事件處理單("not.getPostId()")結案副知
					// 內容：memberService.get((memberRoleService.findByRoleId(15)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，經審核確認結案，特此通知，謝謝！
					List<ViewMemberRoleMember> memberRoles2 = memberRoleService.findByRoleId(15);

					if (memberRoles2 != null) {

						// 取得 [會員機構] 欄位對應之權責單位資料
//						List<ViewOrgOrgSign> orgs = orgService.getOrgOrgSignById(reporterName);
//						Long parentOrgId = null;
//						
//						if (orgs != null) {
//							for (ViewOrgOrgSign org : orgs) {
//								parentOrgId = org.getParentOrgId(); // OrgType = 2 (權責單位)
//								break;
//							}
//						}  
						
						// debug
//						System.out.println("IncidentService.java → examine() → parentOrgId：" + parentOrgId);
						
						// 寄發通知信(副知)：業務管理機關
						for (ViewMemberRoleMember memberRole : memberRoles2) {
							
							Member subMember = memberService.get(memberRole.getMemberId());

							// debug
//							System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - MemberId=" + memberRole.getMemberId());

							if (subMember != null && subMember.getIsEnable() && subMember.getOrgId().equals(subEntity.getNotificationMainUnit2())) {

								String subMailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5SubjectToIsApplySingAdmin"), subEntity.getPostId());
								String subMailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5BodyToIsApplySingAdmin"), subMember.getName(), subEntity.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, subMailSubject, subMailBody, null);

								// debug
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者[業務管理機關]：IsApplySingAdmin - MemberId(符合)=" + memberRole.getMemberId());
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者[業務管理機關]：IsApplySingAdmin - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());
								
								// 符合一次，處理完成即跳出迴圈
								break;
							}
						}
						
						// 判斷是否需要副知[上級機關]，再做處理
						// 寄發通知信(副知)：上級機關
						if (subEntity.getNotificationIsCC3()) {
							
							for (ViewMemberRoleMember memberRole : memberRoles2) {

								Member subMember = memberService.get(memberRole.getMemberId());

								// debug
//								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者：IsApplySingAdmin - MemberId=" + memberRole.getMemberId());

								if (subMember != null && subMember.getIsEnable() && subMember.getOrgId().equals(subEntity.getNotificationMainUnit1())) {

									String subMailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5SubjectToIsApplySingAdmin"), subEntity.getPostId());
									String subMailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5BodyToIsApplySingAdmin"), subMember.getName(), subEntity.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, subMailSubject, subMailBody, null);

									// debug
//									System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者[上級機關]：IsApplySingAdmin - MemberId(符合)=" + memberRole.getMemberId());
//									System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 15.權責單位通報審核者[上級機關]：IsApplySingAdmin - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());
									
									// 符合一次，處理完成即跳出迴圈
									break;
								}
							}
						}
					}

					// 寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign
					// 收件者：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getEmail()
					// 主旨：CERT事件處理單("not.getPostId()")結案副知
					// 內容：memberService.get((memberRoleService.findByRoleId(17)).getMemberId()).getName()，您好！事件處理單("not.getPostId()")，經審核確認結案，特此通知，謝謝！
//					List<ViewMemberRoleMember> memberRoles3 = memberRoleService.findByRoleId(17);
//					
//					if (memberRoles3 != null) {
//						for (ViewMemberRoleMember memberRole : memberRoles3) {
//							
//							Member subMember = memberService.get(memberRole.getMemberId());
//
//							// debug
////							System.out.println("i01_IncidentController.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - MemberId=" + memberRole.getMemberId());
//							
//							// 祇寄給紀錄中已在[會員機構]欄位指定之連絡人
//							//if (subMember != null && subMember.getIsEnable() && subMember.getOrgId().equals(reporterName)) {
//							if (subMember != null && subMember.getIsEnable()) {
//								String subMailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5SubjectToIsHCERTContentSign"), subEntity.getPostId());
//								String subMailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailIncident3To5BodyToIsHCERTContentSign"), subMember.getName(), subEntity.getPostId());
//								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), subMember.getEmail(), subMember.getSpareEmail(), null, subMailSubject, subMailBody, null);
//
//								// debug
////								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - MemberId(符合)=" + memberRole.getMemberId());
////								System.out.println("IncidentService.java → examine() → 【3-事件處理審核中 → 5-已結案】寄發通知信(副知) to 17.H-CERT審核者：IsHCERTContentSign - OrgId=" + subMember.getOrgId() + " - Name=" + subMember.getName());
//
//								// 符合一次，處理完成即跳出迴圈
//								break;
//							}
//						}
//					}
					
					// 更新 事件處理 incident table 的 SubStatus 33 to null
					subStatus = new Long(0);

				}

			}

			// debug
//			System.out.println("IncidentService.java → examine() → status(變更後)：" + status);
//			System.out.println("IncidentService.java → examine() → subStatus(變更後)：" + subStatus);

			JSONObject obj = new JSONObject(json);
			int satisfactionTime = obj.isNull("SatisfactionTime") == true ? 0 : obj.getInt("SatisfactionTime");             
			int satisfactionProfessionalism = obj.isNull("SatisfactionProfessionalism") == true ? 0 : obj.getInt("SatisfactionProfessionalism");             
			int satisfactionService = obj.isNull("SatisfactionService") == true ? 0 : obj.getInt("SatisfactionService");             
			int satisfactionReport = obj.isNull("SatisfactionReport") == true ? 0 : obj.getInt("SatisfactionReport");             
			int satisfactionTotal = obj.isNull("SatisfactionTotal") == true ? 0 : obj.getInt("SatisfactionTotal");             
			String satisfactionRemark = obj.isNull("SatisfactionRemark") == true ? null : obj.getString("SatisfactionRemark");      
			
			entity.setStatus(Long.parseLong(status));
			entity.setSubStatus(subStatus);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setSatisfactionTime(satisfactionTime);
			entity.setSatisfactionProfessionalism(satisfactionProfessionalism);
			entity.setSatisfactionService(satisfactionService);
			entity.setSatisfactionReport(satisfactionReport);
			entity.setSatisfactionTotal(satisfactionTotal);
			entity.setSatisfactionRemark(satisfactionRemark);			       
			incidentDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得事件資料
	 * 
	 * @param id
	 *            事件資料Id
	 * @return 事件資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return incidentDAO.getById(id);
	}

	/**
	 * 取得事件資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料
	 */
	public List<ViewIncidentMember> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得事件資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得事件資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得事件資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return incidentDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
