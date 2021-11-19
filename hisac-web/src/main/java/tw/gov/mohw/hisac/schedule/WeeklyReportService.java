//package tw.gov.mohw.hisac.schedule;
//
//import java.text.MessageFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import tw.gov.mohw.hisac.web.service.NewsManagementService;
//import tw.gov.mohw.hisac.web.service.ResourceMessageService;
//import tw.gov.mohw.hisac.web.service.SubscribeMemberService;
//import tw.gov.mohw.hisac.web.WebConfig;
//import tw.gov.mohw.hisac.web.WebDatetime;
//import tw.gov.mohw.hisac.web.WebMessage;
//import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
//import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
//import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;
//import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;
//import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;
//import tw.gov.mohw.hisac.web.service.ActivityManagementService;
//import tw.gov.mohw.hisac.web.service.AnaManagementService;
//import tw.gov.mohw.hisac.web.service.InformationExchangeService;
//import tw.gov.mohw.hisac.web.service.MailService;
//
///**
// * 排程服務
// */
//@Component
//public class WeeklyReportService {
//	final static Logger logger = LoggerFactory.getLogger(WeeklyReportService.class);
//	
//	@Autowired
//	private NewsManagementService newsManagementService;
//	@Autowired
//	private ActivityManagementService activityManagementService;
//	@Autowired
//	private AnaManagementService anaManagementService;
//	@Autowired
//	private InformationExchangeService informationExchangeService;
//	@Autowired
//	private ResourceMessageService resourceMessageService;	
//	@Autowired
//	private MailService mailService;	
//	@Autowired
//	private SubscribeMemberService subscribeMemberService;
//
//	@Async
//	@Scheduled(cron = "0 0 7 ? * MON")
//	public void SendWeeklyReport() {
//		String news_result = "";
//		String activity_result = "";
//		String ana_result = "";
//		String secbuzzer_result = "";
//		//七天前
//		Date dt = new Date();
//		Calendar c = Calendar.getInstance(); 
//		c.setTime(dt); 
//		c.add(Calendar.DATE, -7);
//		dt = c.getTime();
//		//news
//		JSONObject news_obj = new JSONObject();
//		news_obj.put("PostType", "1");
//		news_obj.put("IsEnable", true);
//		news_obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
//		news_obj.put("Status", "4");		
//		news_obj.put("maxRows", 100);				
//		List<ViewNewsManagementMember> newsManagements = newsManagementService.getSpList(news_obj.toString());
//		int newsIndex = 1;
//		if (newsManagements != null)		
//		for (ViewNewsManagementMember newsManagement : newsManagements) {
//			if (newsManagement.getPostDateTime().after(dt)) {				
//				news_result = news_result + Integer.toString(newsIndex) + ".[" + 
//						WebDatetime.toString(newsManagement.getPostDateTime(), "yyyy-MM-dd")	+ "]" 
//						+ newsManagement.getTitle() + "<br/><br />";	
//				newsIndex++;
//			}
//		}
//		//activity
//		JSONObject activity_obj = new JSONObject();
//		activity_obj.put("IsEnable", true);
//		activity_obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
//		activity_obj.put("Status", "4");	
//		activity_obj.put("maxRows", 100);
//		List<ViewActivityManagementMember> activityManagements = activityManagementService.getSpList(activity_obj.toString());
//		int activityIndex = 1;
//		if (activityManagements != null)
//		for (ViewActivityManagementMember activityManagement : activityManagements) {
//			if (activityManagement.getPostDateTime().after(dt)) {
//				activity_result = activity_result + Integer.toString(activityIndex) + ".[" + 						
//						WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd") + "]" 
//						+ activityManagement.getTitle() + "<br/><br />";		
//				activityIndex++;
//			}
//		}
//		//ana
//		JSONObject ana_obj = new JSONObject();
//		ana_obj.put("IsEnable", true);
//		ana_obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
//		ana_obj.put("Status", "4");	
//		ana_obj.put("maxRows", 100);
//		List<ViewAnaManagementMember> anaManagements = anaManagementService.getSpList(ana_obj.toString());
//		int anaIndex = 1;
//		if (anaManagements != null)
//		for (ViewAnaManagementMember anaManagement : anaManagements) {
//			if (anaManagement.getIncidentReportedTime().after(dt)) {
//				ana_result = ana_result + Integer.toString(anaIndex) + ".[" +
//						WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd") + "]" 		
//						+ anaManagement.getIncidentTitle() + "<br/><br />";
//				anaIndex++;								
//			}
//		}
//		//secbuzzer
//		JSONObject sec_obj = new JSONObject();
//		List<ViewInformationExchangeSecbuzzerTitle> informationExchanges = informationExchangeService.getSecBuzzerList(sec_obj.toString());
//		int secbuzzerIndex = 1;
//		if (informationExchanges != null)
//		for (ViewInformationExchangeSecbuzzerTitle informationExchange : informationExchanges) {
//			if (informationExchange.getIncidentTitle()!= null && informationExchange.getIncidentReportedTime()!= null && informationExchange.getIncidentReportedTime().after(dt)) {
//				secbuzzer_result = secbuzzer_result + Integer.toString(secbuzzerIndex) + ".[" +
//						WebDatetime.toString(informationExchange.getIncidentReportedTime(), "yyyy-MM-dd") + "]" 		
//						+ informationExchange.getIncidentTitle() + "<br/><br />";	
//				secbuzzerIndex++;			
//			}
//		}
//		//寄信	
//		if (!news_result.equals("") || !activity_result.equals("") || !ana_result.equals("") || !secbuzzer_result.equals("")) {			
//			JSONArray recipientBccs =  new JSONArray();				
//			// String recipients = "hisac-cs@mohw.gov.tw";
//			String recipients = resourceMessageService.getMessageValue("globalFooterEmail");
//			List<ViewSubscribeMember> viewSubscribeMembers = subscribeMemberService.getBySubscribeName("資訊週報");
//			if (viewSubscribeMembers != null) {
//				for (ViewSubscribeMember viewSubscribeMember : viewSubscribeMembers) {				
//					recipientBccs.put(viewSubscribeMember.getEmail());		
//				}
//			}
//			String result = "";
//			if (!news_result.equals(""))	
//				result = result + "<br/><br/>最新消息：<br/><br/>" + news_result;
//			if (!activity_result.equals(""))	
//				result = result + "<br/><br/>活動訊息：<br/><br/>" + activity_result;
//			if (!ana_result.equals(""))	
//				result = result + "<br/><br/>資安訊息情報：<br/><br/>" + ana_result;
//			if (!secbuzzer_result.equals(""))	
//				result = result + "<br/><br/>醫療設備資安情報：<br/><br/>" + secbuzzer_result;				
//			String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeeklyReportSubject"), WebDatetime.toString(dt, "yyyy-MM-dd") + " ~ " + WebDatetime.toString(new Date(), "yyyy-MM-dd"));
//			String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeeklyReportBody"), result, WebConfig.WEB_SITE_URL + "sys/edit_profile");
//			mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(),recipients , null, recipientBccs, mailSubject, mailBody, null);		
//		}
//	}
//}