package tw.gov.mohw.hisac.web.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebSms;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;

/**
 * 寄信服務
 */
@Service
public class SmsService {
	final static Logger logger = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	ResourceMessageService resourceMessageService = new ResourceMessageService();

	@Autowired
	SystemLogService systemLogService = new SystemLogService();

	/**
	 * 寄送簡訊(非同步, 同步請使用WebSms)
	 * 
	 * @param debug
	 *            除厝模式
	 * @param phoneNumber
	 *            電話號碼
	 * @param message
	 *            簡訊內容
	 */
	@Async
	public void Send(String debug, String phoneNumber, String message) {
		
		String[] mobilePhomeArray =  phoneNumber.split(",");

		for (String mobilePhome :mobilePhomeArray) {
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(mobilePhome);
			mobilePhome = m.replaceAll("").trim();
			if (mobilePhome.equals("")) {
				systemLogService.insert("Sms", "Send", mobilePhome + ":" + message, SystemLogVariable.Action.Sms, SystemLogVariable.Status.Fail, "", "");
				return;
			}
			if (WebConfig.DEBUG_MODE) {
				message = debug + message;
			}
			systemLogService.insert("Sms", "Send", mobilePhome + ":" + message, SystemLogVariable.Action.Sms, SystemLogVariable.Status.Success, "", "");
			WebSms.Send(mobilePhome, message);
			
		}

		
		
		
		
		
	}
}