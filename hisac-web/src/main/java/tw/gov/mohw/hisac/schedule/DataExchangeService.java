//package tw.gov.mohw.hisac.schedule;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import tw.gov.mohw.hisac.web.service.InformationExchangeNisacService;
//
///**
// * 排程服務
// */
//@Component
//public class DataExchangeService {
//	final static Logger logger = LoggerFactory.getLogger(DataExchangeService.class);
//	
//	@Autowired
//	private InformationExchangeNisacService informationExchangeNisacService;
//	
//	@Async
//	@Scheduled(cron = "0 0 8 * * ?")
//	public void exportToNISAC() {
//		// TODO: Call Function: Export Data to N-ISAC
//	}
//
//	@Async
//	@Scheduled(cron = "0 0 8,12,15 * * ?")
////	@Scheduled(fixedRate = 1 * 60 * 1000)	// 5 * 60 秒執行一次
//
//	public void importFromNISAC() {
//		System.out.println("testscheduke");
//		informationExchangeNisacService.importFromNisac();
//	}
//	
//	@Async
//	@Scheduled(cron = "0 0 6 * * ?")
//	public void reminderToUser () {
//		// TODO: Call Function: Reminder to User Sign or Reply
//	}
//}