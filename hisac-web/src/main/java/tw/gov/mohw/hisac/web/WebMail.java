package tw.gov.mohw.hisac.web;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 電子郵件發送
 */
public class WebMail {

	final static Logger logger = LoggerFactory.getLogger(WebMail.class);
	final static String host = WebConfig.MAIL_SMTP_HOST;
	final static String port = WebConfig.MAIL_SMTP_PORT;
	final static String auth = WebConfig.MAIL_SMTP_AUTH;;
	final static String starttls_enable = WebConfig.MAIL_SMTP_STARTTLS_ENABLE;
	final static String username = WebConfig.MAIL_USERNAME;
	final static String code = WebConfig.MAIL_CODE;

	/**
	 * 寄送電子郵件(同步, 非同步請使用MailService)
	 * 
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
	@SuppressWarnings({"deprecation"})
	public static void Send(String fromText, String recipients, String recipientCcs, JSONArray recipientBccs, String mailSubject, String mailBody, List<WebMailAttachment> attachements) {
		if (WebConfig.ENABLE_SENDMAIL) {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", auth);
			props.put("mail.smtp.port", port);
			if (port.equals("587")) {
				props.put("mail.smtp.starttls.enable", starttls_enable);
			} else if (port.equals("465")) {
				props.put("mail.smtp.ssl.enable", starttls_enable);
			}
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, code);
				}
			};
			// Session session = Session.getInstance(props, authenticator);
			Session session;
			if (Boolean.parseBoolean(auth) == true) {
				session = Session.getDefaultInstance(props, authenticator);
			} else {
				session = Session.getDefaultInstance(props);
			}
			try {
				Message message = new MimeMessage(session);
				message.setSentDate(new Date());
				message.setHeader("Content-Type", "text/html; charset=UTF-8");
				if (fromText != null && !fromText.isEmpty()) {
					message.setFrom(new InternetAddress(username, MimeUtility.encodeText(fromText, StandardCharsets.UTF_8.toString(), "B")));
				} else {
					message.setFrom(new InternetAddress(username));
				}
				if (recipients != null && !recipients.isEmpty()) {
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
				} else {
					Exception e = new Exception("No Recipients");
					logger.error(e.getMessage());
					throw new RuntimeException(e);
				}
				if (recipientCcs != null && !recipientCcs.isEmpty()) {
//					String[] recipientCcsArray = recipientCcs.split(",");
//					for( String aRecipientCcs : recipientCcsArray) {
//						message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(aRecipientCcs));
//						
//						
//					}
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientCcs));

					
//					message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientCcs));
				}
				if (recipientBccs != null) {										
					for (int i=0; i< recipientBccs.length(); i++)						
						message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(recipientBccs.getString(i)));
				}
				// message.setSubject(mailSubject); //Outlook會亂碼
				// message.setSubject(MimeUtility.encodeText(mailSubject));
				// //Outlook會亂碼
				message.setSubject(MimeUtility.encodeText(mailSubject, StandardCharsets.UTF_8.toString(), "B"));

				MimeBodyPart messageBodyPart = new MimeBodyPart();
				Multipart multipart = new MimeMultipart();

				MimeBodyPart messageBody = new MimeBodyPart();
				messageBody.setContent(mailBody, "text/html; charset=UTF-8");
				multipart.addBodyPart(messageBody);

				int attachementCount = attachements == null ? 0 : attachements.size();
				if (attachementCount > 0) {
					for (int index = 0; index < attachementCount; index++) {
						MimeBodyPart messageAttachment = new MimeBodyPart();
						ByteArrayDataSource bds = new ByteArrayDataSource(attachements.get(index).getAttachmentBody(), "application/octet-stream");
						bds.setName(attachements.get(index).getAttachmentName());
						messageAttachment.setDataHandler(new DataHandler(bds));
						messageAttachment.setFileName(attachements.get(index).getAttachmentName());
						multipart.addBodyPart(messageAttachment);
					}
				}
				messageBodyPart.setContent(multipart);

				if (WebConfig.ENABLE_DIGITAL_SIGNATURE) {
					Security.addProvider(new BouncyCastleProvider());
					try {
						KeyStore keyStore = KeyStore.getInstance("PKCS12");
						keyStore.load(WebFile.readPfxFromResource(WebConfig.PFX_FILENAME), WebConfig.PFX_CODE.toCharArray());
						Enumeration<String> e = keyStore.aliases();
						List<Certificate> certList = new ArrayList<Certificate>();
						String keyAlias = null;
						while (e.hasMoreElements() && (keyAlias == null)) {
							String alias = (String) e.nextElement();
							keyAlias = keyStore.isKeyEntry(alias) ? alias : null;
							
							Certificate cert = keyStore.getCertificate(alias);
				            if(cert != null) {
				                certList.add(cert);
				            }
						}
						if (keyAlias == null) {
							throw new Exception("Key alias Null");
						}
						
				        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, WebConfig.PFX_CODE.toCharArray());
						X509Certificate pkcs12Cert = (X509Certificate) keyStore.getCertificate(keyAlias);
						CertStore certsAndcrls = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");
						SMIMESignedGenerator signedGenerator = new SMIMESignedGenerator();
						signedGenerator.addSigner(privateKey, pkcs12Cert, SMIMESignedGenerator.DIGEST_SHA256);
						signedGenerator.addCertificatesAndCRLs(certsAndcrls);
						MimeMultipart mm = signedGenerator.generate(messageBodyPart, new BouncyCastleProvider());
						message.setContent(mm);
						// Transport.send(message);
						Transport transport = session.getTransport();
						try {
							transport.connect();
							transport.sendMessage(message, message.getAllRecipients());
						} catch (Exception eTransport) {
//							eTransport.printStackTrace();
//							logger.error(eTransport.getMessage());
						} finally {
							transport.close();
						}
					} catch (Exception e) {
//						//e.printStackTrace();
//						logger.error(e.getMessage());
					}
				} else {
					message.setContent(multipart);
					// Transport.send(message);
					Transport transport = session.getTransport();
					try {
						transport.connect();
						transport.sendMessage(message, message.getAllRecipients());
					} catch (Exception eTransport) {
//						eTransport.printStackTrace();
//						logger.error(eTransport.getMessage());
					} finally {
						transport.close();
					}
				}
			} catch (MessagingException e) {
//				//e.printStackTrace();
//				logger.error(e.getMessage());
			} catch (Exception e) {
//				//e.printStackTrace();
//				logger.error(e.getMessage());
			}
		} else {

		}
	}
}
