package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebMail;
import tw.gov.mohw.hisac.web.WebMailAttachment;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;

/**
 * 寄信服務
 */
@Service
public class MailService {
	final static Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	SystemLogService systemLogService = new SystemLogService();

	@Autowired
	ResourceMessageService resourceMessageService = new ResourceMessageService();

	/**
	 * 寄送電子郵件(非同步, 同步請使用WebMail)
	 * 
	 * @param debug
	 *            DEBUG模式下在Subject會附加的字串
	 * @param recipients
	 *            接收者電子郵件信箱
	 * @param recipientCcs
	 *            副本接收者電子郵件信箱
	 * @param recipientBccs
	 *            密件副本接收者電子郵件信箱
	 * @param mailSubject
	 *            電子郵件標題
	 * @param mailBody
	 *            電子郵件內文
	 * @param attachements
	 *            電子郵件附件
	 */
	@Async
	public void Send(String debug, String recipients, String recipientCcs, JSONArray recipientBccs, String mailSubject, String mailBody, List<WebMailAttachment> attachements) {
		String fromText = WebMessage.getMessage("globalSiteName", null, Locale.TRADITIONAL_CHINESE);
		String mailContext = "from: " + fromText + ";recipients: " + recipients + ";recipientCcs: " + recipients + ";recipientBccs: " + recipientBccs + ";mailSubject: " + mailSubject + ";mailBody: " + mailBody;
		mailBody = mailBody + "<br /><br />" + MessageFormat.format(resourceMessageService.getMessageValue("mailFooter"), WebConfig.WEB_SITE_URL);
		systemLogService.insert("Mail", "Send", mailContext, SystemLogVariable.Action.SendMail, SystemLogVariable.Status.Success, "", "");
		if (WebConfig.DEBUG_MODE) {
			mailSubject = mailSubject + "[" + debug + "]";
		}
		WebMail.Send(fromText, recipients, recipientCcs, recipientBccs, mailSubject, mailBody, attachements);
	}

	/**
	 * 寄送電子郵件(非同步, 同步請使用WebMail)
	 * 
	 * @param debug
	 *            DEBUG模式下在Subject會附加的字串
	 * @param fromText
	 *            寄送者顯示名稱
	 * @param recipients
	 *            接收者電子郵件信箱
	 * @param recipientCcs
	 *            副本接收者電子郵件信箱
	 * @param recipientBccs
	 *            密件副本接收者電子郵件信箱
	 * @param mailSubject
	 *            電子郵件標題
	 * @param mailBody
	 *            電子郵件內文
	 * @param attachements
	 *            電子郵件附件
	 */
	@Async
	public void Send(String debug, String fromText, String recipients, String recipientCcs, JSONArray recipientBccs, String mailSubject, String mailBody, List<WebMailAttachment> attachements) {
		String mailContext = "from: " + fromText + ";recipients: " + recipients + ";recipientCcs: " + recipients + ";recipientBccs: " + recipientBccs + ";mailSubject: " + mailSubject + ";mailBody: " + mailBody;
		mailBody = mailBody + "<br /><br />" + MessageFormat.format(resourceMessageService.getMessageValue("mailFooter"), WebConfig.WEB_SITE_URL);
		systemLogService.insert("Mail", "Send", mailContext, SystemLogVariable.Action.SendMail, SystemLogVariable.Status.Success, "", "");
		if (WebConfig.DEBUG_MODE) {
			mailSubject = mailSubject + "[" + debug + "]";
		}
		WebMail.Send(fromText, recipients, recipientCcs, recipientBccs, mailSubject, mailBody, attachements);
	}
}