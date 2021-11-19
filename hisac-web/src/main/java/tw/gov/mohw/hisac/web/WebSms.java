package tw.gov.mohw.hisac.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 簡訊發送
 */
public class WebSms {

	final static Logger logger = LoggerFactory.getLogger(WebSms.class);
	final static Base64.Encoder encoder = Base64.getEncoder();

	/**
	 * 寄送簡訊
	 * 
	 * @param phoneNumber
	 *            手機號碼
	 * @param message
	 *            訊息內容
	 */
	@SuppressWarnings("deprecation")
	public static void Send(String phoneNumber, String message) {
		if (WebConfig.ENABLE_SMS) {
			try {
				String regEx = "[^0-9]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(phoneNumber);
				phoneNumber = m.replaceAll("").trim();
				if (phoneNumber.equals("")) {
					return;
				}
				byte[] _byteMessage = message.getBytes(StandardCharsets.UTF_8.toString());
				String xmlRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<SmsSubmitReq>" + "<SysId>ISSDUCOA</SysId>" + "<SrcAddress>01916800061038600223</SrcAddress>" + "<DestAddress>" + phoneNumber + "</DestAddress>" + "<SmsBody>"
						+ encoder.encodeToString(_byteMessage) + "</SmsBody>" + "<LongSmsFlag>true</LongSmsFlag></SmsSubmitReq>";
				String url = "http://61.20.32.60/mpushapi/smssubmit";
				URL obj = new URL(url);
				// 遠傳SMS
				try {
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					con.setUseCaches(false);
					con.setDoInput(true);
					con.setDoOutput(true);
					xmlRequest = URLEncoder.encode(xmlRequest);
					String urlParameters = "xml=" + xmlRequest;
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
					// int responseCode = con.getResponseCode();
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					String xmlResponse = response.toString();
					int resultCodeStart = xmlResponse.indexOf("<ResultCode>") + 12;
					int resultCodeEnd = xmlResponse.indexOf("</ResultCode>");
					String resultCode = xmlResponse.substring(resultCodeStart, resultCodeEnd);
					if (resultCode.equals("00000")) {
						// Success
					} else {
						int resultTextStart = xmlResponse.indexOf("<ResultText>") + 12;
						int resultTextEnd = xmlResponse.indexOf("</ResultText>");
						String resultText = xmlResponse.substring(resultTextStart, resultTextEnd);
						logger.error("SMS Error: " + resultCode + "-" + resultText);
					}
//					if (resultCode.equals("00000") || resultCode.equals("00010")) {
//						int messageIdStart = xmlResponse.indexOf("<MessageId>") + 11;
//						int messageIdEnd = xmlResponse.indexOf("</MessageId>");
//						String messageId = xmlResponse.substring(messageIdStart, messageIdEnd);
//						logger.error("SMS MessageId: " + resultCode + "-" + messageId);
//					}
				} catch (Exception e) {
//					//e.printStackTrace();
//					logger.error(e.getMessage());
				}
			} catch (Exception e) {
//				//e.printStackTrace();
//				logger.error(e.getMessage());
			}
		} else {
		}
	}
}
