package tw.gov.mohw.hisac.web.service;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.json.JSONObject;
import org.mitre.cybox.common_2.AnyURIObjectPropertyType;
import org.mitre.cybox.common_2.HashListType;
import org.mitre.cybox.common_2.HashType;
import org.mitre.cybox.common_2.PlatformSpecificationType;
import org.mitre.cybox.common_2.SimpleHashValueType;
import org.mitre.cybox.common_2.StringObjectPropertyType;
import org.mitre.cybox.cybox_2.ObjectType;
import org.mitre.cybox.cybox_2.Observable;
import org.mitre.cybox.cybox_2.Observables;
import org.mitre.cybox.default_vocabularies_2.HashNameVocab10;
import org.mitre.cybox.objects.Address;
import org.mitre.cybox.objects.CategoryTypeEnum;
import org.mitre.cybox.objects.Device;
import org.mitre.cybox.objects.FileObjectType;
import org.mitre.cybox.objects.FilePathType;
import org.mitre.cybox.objects.OSType;
import org.mitre.cybox.objects.Product;
import org.mitre.cybox.objects.SystemObjectType;
import org.mitre.cybox.objects.URIObjectType;
import org.mitre.cybox.objects.URITypeEnum;
import org.mitre.stix.common_1.ConfidenceType;
import org.mitre.stix.common_1.ControlledVocabularyStringType;
import org.mitre.stix.common_1.DateTimePrecisionEnum;
import org.mitre.stix.common_1.DateTimeWithPrecisionType;
import org.mitre.stix.common_1.IdentityType;
import org.mitre.stix.common_1.InformationSourceType;
import org.mitre.stix.common_1.RelatedCourseOfActionType;
import org.mitre.stix.common_1.RelatedExploitTargetType;
import org.mitre.stix.common_1.RelatedIncidentType;
import org.mitre.stix.common_1.RelatedTTPType;
import org.mitre.stix.common_1.StatementType;
import org.mitre.stix.common_1.StructuredTextType;
import org.mitre.stix.common_1.TTPBaseType;
import org.mitre.stix.courseofaction_1.CourseOfAction;
import org.mitre.stix.courseofaction_1.RelatedCOAsType;
import org.mitre.stix.default_vocabularies_1.COAStageVocab10;
import org.mitre.stix.exploittarget_1.ExploitTarget;
import org.mitre.stix.extensions.identity.CIQIdentity30InstanceType;
import org.mitre.stix.extensions.identity.STIXCIQIdentity30Type;
import org.mitre.stix.incident_1.AffectedAssetType;
import org.mitre.stix.incident_1.AffectedAssetsType;
import org.mitre.stix.incident_1.AssetTypeType;
import org.mitre.stix.incident_1.COARequestedType;
import org.mitre.stix.incident_1.COATakenType;
import org.mitre.stix.incident_1.COATimeType;
import org.mitre.stix.incident_1.CategoriesType;
import org.mitre.stix.incident_1.ExternalIDType;
import org.mitre.stix.incident_1.ImpactAssessmentType;
import org.mitre.stix.incident_1.Incident;
import org.mitre.stix.incident_1.LeveragedTTPsType;
import org.mitre.stix.incident_1.RelatedIncidentsType;
import org.mitre.stix.incident_1.TimeType;
import org.mitre.stix.stix_1.IncidentsType;
import org.mitre.stix.stix_1.STIXPackage;
import org.mitre.stix.stix_1.TTPsType;
import org.mitre.stix.ttp_1.ExploitTargetsType;
import org.mitre.stix.ttp_1.InfrastructureType;
import org.mitre.stix.ttp_1.ResourceType;
import org.mitre.stix.ttp_1.TTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nccst.ncert.NcertException;
import nccst.ncert.NcertServices;
import oasis.names.tc.ciq.xnl._3.PartyNameType;
import oasis.names.tc.ciq.xnl._3.PartyNameType.PersonName;
import oasis.names.tc.ciq.xnl._3.PersonNameType.NameElement;
import oasis.names.tc.ciq.xpil._3.ContactNumberElementList;
import oasis.names.tc.ciq.xpil._3.ContactNumbers;
import oasis.names.tc.ciq.xpil._3.ContactNumbers.ContactNumber;
import oasis.names.tc.ciq.xpil._3.ContactNumbers.ContactNumber.ContactNumberElement;
import oasis.names.tc.ciq.xpil._3.ElectronicAddressIdentifiers;
import oasis.names.tc.ciq.xpil._3.ElectronicAddressIdentifiers.ElectronicAddressIdentifier;
import oasis.names.tc.ciq.xpil._3.OrganisationInfo;
import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.ResourceMessageDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ResourceMessage;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;

/**
 * 事件管理
 */
@Service
public class NotificationExchangeNcertService {
	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrgService orgService;

	@Autowired
	private ResourceMessageDAO resourceMessageDao;

	
	final static Logger logger = LoggerFactory.getLogger(InformationExchangeNisacService.class);

	private NcertServices ncertClient = null; // 服務客戶端物件
	private String keyStore = WebConfig.NCERT_KEY_STORE; // 憑證檔路徑 ncertClient
	private String keyStoreP = WebConfig.NCERT_KEY_STORE_P; // 憑證檔P
	private String trustStore = WebConfig.NCERT_TRUST_STORE; // 憑證信任庫路徑
																// ncertClientTruststore
	private String trustStoreP = WebConfig.NCERT_TRUST_STORE_P; // 憑證信任庫P
	private String ncertUrl = WebConfig.NCERT_URL; // NCERT服務網址
	private String ncertInboxUrl = WebConfig.NCERT_INBOX_URL; // 傳送通報服務網址
	//private String ncertInboxUrl = ""; // 取得傳送通報服務網址
	private String ncertId = WebConfig.NCERT_MEMBER_ID; // 通報網站帳戶
	private String ncertP = WebConfig.NCERT_MEMBER_P; // 通報網站P

	@PostConstruct
	public void initializeNcertMemberIdAndCode() {
		ResourceMessage messageSourceId = resourceMessageDao.getByMessageKey("ncertMemberId");
		if(messageSourceId!=null) {
			ncertId = messageSourceId.getMessageValue();
		}
		
		ResourceMessage messageSourceCode = resourceMessageDao.getByMessageKey("ncertMemberCode");
		if(messageSourceCode!=null) {
			ncertP = messageSourceCode.getMessageValue();
		}
	}
	
	protected String mailBody = "";
	protected String mailSubject = "";
	protected boolean ncertSuccess = false;
	
	
	// 測試用
	protected int testGotoStep = 1; // 1: 初報; 2: 初報-編輯; 3: 續報; 4: 1,2結報; 5: 3,4結報
	//protected String testCertId = "715"; // 回傳之 CertId
	protected String testStep3VocabName = "3"; // 資安事件是否完成損害控制或復原, 1: 否; 2: 完成損害控制; 3: 完成損害控制與復原


	private String stixVersion = "1.2";

	/*
	 * 通報單狀態
	 */
	public interface NCert {
		public static final String StatusFirst = "1"; // 初報
		public static final String StatusReEdit = "2"; // 初報編輯
		public static final String StatusReSend = "3"; // 續報
		public static final String StatusClose12 = "4"; // 1/2級事件結報
		public static final String StatusClose34 = "5"; // 3/4級事件結報
//		public static final String StatusClose34Multiple = "6"; // 3/4級事件結報(多個受害設備類型)
	}

	/**
	 * 設定 NCert Client
	 * 
	 */
	private void setNcertService() {
		//System.out.println("setNcertService()");
		if (ncertClient == null) {
			JSONObject responseJson = new JSONObject();
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				File fileKeyStore = new File(classLoader.getResource(keyStore).getFile());
				keyStore = fileKeyStore.getAbsolutePath();
				File fileTrustStore = new File(classLoader.getResource(trustStore).getFile());
				trustStore = fileTrustStore.getAbsolutePath();
				ncertClient = new NcertServices(keyStore, keyStoreP, trustStore, trustStoreP);
				responseJson.put("msg", "取得 NCERT 服務客戶端物件成功");
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "setNcertService", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody += "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			} catch (Exception e) {
				responseJson.put("msg", "取得 NCERT 服務客戶端物件失敗");
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "setNcertService", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody += "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.ncertSuccess = false;
			}
			// System.out.println("setNcertService() → " + responseJson);
		}
	}

	public void test() {
		//System.out.println("test");
		//initNcert();
	}

	/**
	 * 初始化NCERT並取得NCERT服務網址
	 */
	private boolean initNcert() {
		//System.out.println("initNcert()");
		if (ncertClient == null) {
			setNcertService();
		}
		JSONObject responseJson = new JSONObject();
		boolean response = false;
		if (ncertClient != null) {
			try {
				// System.out.println("initNcert() → ncertUrl=" + ncertUrl);
				// System.out.println("initNcert() → ncertId=" + ncertId);
				// System.out.println("initNcert() → ncertP=" + ncertP);
				Map<String, String> urls = ncertClient.callDiscovery(ncertUrl, ncertId, ncertP);
				//System.out.println("initNcert() → urls=" + urls);
				ncertInboxUrl = urls.get("inboxUrl");
				//System.out.println("initNcert() → ncertInboxUrl=" + ncertInboxUrl);
				responseJson.put("msg", "取得 NCERT 伺服器提供服務之網址成功");
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "initNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody += "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				response = true;
			} catch (NcertException ne) {
				String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
				String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息
				responseJson.put("msg", "取得 NCERT 伺服器提供服務之網址失敗");
				responseJson.put("errorCode", errorCode);
				responseJson.put("errorMsg", errorMsg);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "initNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody += "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.ncertSuccess = false;
				response = false;
			} catch (Exception e) {
				//System.out.println("initNcert() → e=" + e);
				responseJson.put("msg", "取得 NCERT 伺服器提供服務之網址失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "initNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody += "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.ncertSuccess = false;
				response = false;
			}
		} else {
			response = false;
		}
		//System.out.println("initNcert() → " + responseJson);
		return response;
	}
	

	/**
	 * 組合 STIX XML 通報
	 * 
	 * @param id
	 *            通報單編號
	 * @return STIX XML
	 */
	public String genStixXML(Notification notification) {
		//System.out.println("genStixXML()");
		String xmlStr = "";
		JSONObject responseJson = new JSONObject();
		try {
			// 開始產生STIX XML
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("UTC")));
			STIXPackage stixPackage = new STIXPackage().withTimestamp(now).withVersion(stixVersion);

			String xmlPostId = notification.getPostId(); // 通報編號
			String xmlCertId = String.valueOf(notification.getCertId()); // CertId
			String xmlEffectLevel = notification.getEffectLevel().toString(); // 資安事件影響等級
			int hostAmount = notification.getHostAmount(); // 受害資訊設備數量 host 總計
			int serverAmount = notification.getServerAmount(); // 受害資訊設備數量 server 總計
			int totalAmount = hostAmount + serverAmount; // 受害資訊設備數量 host + server 總計
			String xmlTotalAmount = String.valueOf(totalAmount);
			long contactorId = notification.getContactorId(); // Member Account
			
			// AIC影響衝擊評估
			String xmlAIC = "";
			String aeffectLevel = notification.getAeffectLevel().toString(); // 可用性
			String ieffectLevel = notification.getIeffectLevel().toString(); // 完整性
			String ceffectLevel = notification.getCeffectLevel().toString(); // 機密性
			xmlAIC = aeffectLevel + ieffectLevel + ceffectLevel;  
			
			// 通報來源
			String xmlEventSource = "";
			String eventSource = notification.getEventSource().toString();
			String eventSourceNo = notification.getEventSourceNo();
			String eventSourceOther = notification.getEventSourceOther();
			switch (eventSource) {
				case "1":
					xmlEventSource = eventSource;
					break;
				case "2":
					xmlEventSource = eventSourceNo;
					break;
				case "3":
					xmlEventSource = eventSourceOther;
					break;
			}

			// 應變處置說明
			String xmlResDescription = notification.getResDescription();
			if (xmlResDescription == null || xmlResDescription.isEmpty()) {
				xmlResDescription = "無資料";
			}
			
			// 事發機關完成損害控制或復原時間
			String xmlResControlTime = notification.getResControlTime().toString();
			
			GregorianCalendar tempEventDateTime = new GregorianCalendar();
			tempEventDateTime.setTime(notification.getEventDateTime());
			XMLGregorianCalendar xmlEventDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempEventDateTime); // 事件發生時間
			
			GregorianCalendar tempApplyDateTime = new GregorianCalendar();
			tempApplyDateTime.setTime(notification.getEventDateTime());
			XMLGregorianCalendar xmlApplyDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempApplyDateTime); // 發布時間
			
			// 事發機關結報時間
			GregorianCalendar tempFinishDateTime = new GregorianCalendar();
			tempFinishDateTime.setTime(notification.getFinishDateTime());
			XMLGregorianCalendar xmlFinishDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempFinishDateTime); // 調查、處理及改善報告繳交(登錄結報)時間

			String xmlMaintainCompany = ""; // 資安維護廠商名稱
			if (notification.getMaintainCompany() != "") {
				xmlMaintainCompany = notification.getMaintainCompany();
			}
			if (xmlMaintainCompany == null || xmlMaintainCompany.isEmpty()) {
				xmlMaintainCompany = "無資料";
			}
					
			long contactorUnit = notification.getContactorUnit(); // 機關(機構)名稱
			long mainUnit1 = notification.getMainUnit1(); // 權責機關(上級機關)
			
			String xmlEventRemark = "";
			if (notification.getEventRemark() != "") {
				xmlEventRemark = notification.getEventRemark(); // 事件說明及影響範圍
			}
			
			String xmlIsAffectOthers = "3"; // 是否影響其他政府機關(構)或重要民生設施運作 1: 無影響; 2: 不確定; 3: 有影響
			if (!notification.getIsAffectOthers()) {
				xmlIsAffectOthers = "1";
			}
			
			String xmlAffectOther = ""; // 影響機關(構)/重要民生設施領域名稱
			Boolean affectOther1 = notification.getAffectOther1(); // 影響機關(構)/重要民生設施領域名稱(水資源)
			Boolean affectOther2 = notification.getAffectOther2(); // 影響機關(構)/重要民生設施領域名稱(能源)
			Boolean affectOther3 = notification.getAffectOther3(); // 影響機關(構)/重要民生設施領域名稱(通訊傳播)
			Boolean affectOther4 = notification.getAffectOther4(); // 影響機關(構)/重要民生設施領域名稱(交通)
			Boolean affectOther5 = notification.getAffectOther5(); // 影響機關(構)/重要民生設施領域名稱(銀行與金融)
			Boolean affectOther6 = notification.getAffectOther6(); // 影響機關(構)/重要民生設施領域名稱(緊急救援與醫院)
			Boolean affectOther7 = notification.getAffectOther7(); // 影響機關(構)/重要民生設施領域名稱(重要政府機關)
			Boolean affectOther8 = notification.getAffectOther8(); // 影響機關(構)/重要民生設施領域名稱(高科技園區)
			if (affectOther1) {
				xmlAffectOther = "1"; // 水資源
			}
			if (affectOther2) {
			    xmlAffectOther += "2"; // 能源
			}
			if (affectOther3) {
			    xmlAffectOther += "3"; // 通訊傳播
			}
			if (affectOther4) {
			    xmlAffectOther += "4"; // 交通
			}
			if (affectOther5) {
			    xmlAffectOther += "5"; // 銀行與金融
			}
			if (affectOther6) {
			    xmlAffectOther += "6"; // 緊急救援與醫院
			}
			if (affectOther7) {
			    xmlAffectOther += "7"; // 重要政府機關
			}
			if (affectOther8) {
			    xmlAffectOther += "8"; // 高科技園區
			}
			if (xmlAffectOther == null || xmlAffectOther.isEmpty()) {
				xmlAffectOther = "0"; // 無資料
			}
			
			String xmlIsNeedSupport = "1"; // 是否需要支援 1: 否; 2: 是
			if (notification.getIsNeedSupport()) {
				xmlIsNeedSupport = "2";
			}
			
			String xmlNeedSupportRemark = ""; // 期望支援內容
			if (notification.getNeedSupportRemark() != "") {
				xmlNeedSupportRemark = notification.getNeedSupportRemark();
			}
			if (xmlNeedSupportRemark == null || xmlNeedSupportRemark.isEmpty()) {
				xmlNeedSupportRemark = "無資料";
			}
		
			// 事件發生原因
			String xmlFinish1Reason = ""; // 事件發生原因
			Integer finish1Reason = notification.getFinish1Reason(); // 事件發生原因(作業系統漏洞)
			String finish1ReasonOther = notification.getFinish1ReasonOther(); // 事件發生原因(其他-說明)
			
			switch (finish1Reason) {
				case 1:
					xmlFinish1Reason = "6"; // 作業系統漏洞
					break;
				case 2:
					xmlFinish1Reason = "7"; // 弱密碼
					break;
				case 3:
					xmlFinish1Reason = "8"; // 應用程式漏洞
					break;
				case 4:
					xmlFinish1Reason = "9"; // 網站設計不當
					break;
				case 5:
					xmlFinish1Reason = "2"; // 人為疏失
					break;
				case 6:
					xmlFinish1Reason = "3"; // 設定錯誤
					break;
				case 7:
					xmlFinish1Reason = "系統遭入侵";
					break;
				case 8:
					xmlFinish1Reason = finish1ReasonOther;
					break;
			}

			// 攻擊手法與調查結果說明
			String xmlEventType = ""; // 事件分類與異常狀況
			String xmlEventTypeClass = ""; // 事件類型
			String tmpXmlEventType = "";
			Integer eventType = notification.getEventType(); // 事件分類與異常狀況
			String eventType5Other = notification.getEventType5Other(); // 事件分類與異常狀況(其他異常原因)
			
			switch (eventType) {
				case 1:
					xmlEventType = "網頁攻擊：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType1Opt1 = notification.getIsEventType1Opt1(); // 網頁置換
					Boolean isEventType1Opt2 = notification.getIsEventType1Opt2(); // 惡意留言
					Boolean isEventType1Opt3 = notification.getIsEventType1Opt3(); // 惡意網頁
					Boolean isEventType1Opt4 = notification.getIsEventType1Opt4(); // 釣魚網頁
					Boolean isEventType1Opt5 = notification.getIsEventType1Opt5(); // 網頁木馬
					Boolean isEventType1Opt6 = notification.getIsEventType1Opt6(); // 網站個資外洩
					if (isEventType1Opt1) {
						tmpXmlEventType += "網頁置換";
					}
					if (isEventType1Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意留言";
						} else {
							tmpXmlEventType += "惡意留言";
						}						
					}
					if (isEventType1Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意網頁";
						} else {
							tmpXmlEventType += "惡意網頁";
						}						
					}
					if (isEventType1Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "釣魚網頁";
						} else {
							tmpXmlEventType += "釣魚網頁";
						}						
					}
					if (isEventType1Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網頁木馬";
						} else {
							tmpXmlEventType += "網頁木馬";
						}						
					}
					if (isEventType1Opt6) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網站個資外洩";
						} else {
							tmpXmlEventType += "網站個資外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 2:
					xmlEventType = "非法入侵：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType2Opt1 = notification.getIsEventType2Opt1(); // 系統遭入侵
					Boolean isEventType2Opt2 = notification.getIsEventType2Opt2(); // 植入惡意程式
					Boolean isEventType2Opt3 = notification.getIsEventType2Opt3(); // 異常連線
					Boolean isEventType2Opt4 = notification.getIsEventType2Opt4(); // 發送垃圾郵件
					Boolean isEventType2Opt5 = notification.getIsEventType2Opt5(); // 資料外洩
					if (isEventType2Opt1) {
						tmpXmlEventType += "系統遭入侵";
					}
					if (isEventType2Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "植入惡意程式";
						} else {
							tmpXmlEventType += "植入惡意程式";
						}						
					}
					if (isEventType2Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "異常連線";
						} else {
							tmpXmlEventType += "異常連線";
						}						
					}
					if (isEventType2Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "發送垃圾郵件";
						} else {
							tmpXmlEventType += "發送垃圾郵件";
						}						
					}
					if (isEventType2Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "資料外洩";
						} else {
							tmpXmlEventType += "資料外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 3:
					xmlEventType = "阻斷服務(DoS/DDoS)：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType3Opt1 = notification.getIsEventType3Opt1(); // 服務中斷
					Boolean isEventType3Opt2 = notification.getIsEventType3Opt2(); // 效能降低
					if (isEventType3Opt1) {
						tmpXmlEventType += "服務中斷";
					}
					if (isEventType3Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "效能降低";
						} else {
							tmpXmlEventType += "效能降低";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 4:
					xmlEventType = "設備異常：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType4Opt1 = notification.getIsEventType4Opt1(); // 設備毀損
					Boolean isEventType4Opt2 = notification.getIsEventType4Opt2(); // 電力異常
					Boolean isEventType4Opt3 = notification.getIsEventType4Opt3(); // 網路服務中斷
					Boolean isEventType4Opt4 = notification.getIsEventType4Opt4(); // 設備遺失
					if (isEventType4Opt1) {
						tmpXmlEventType += "設備毀損";
					}
					if (isEventType4Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "電力異常";
						} else {
							tmpXmlEventType += "電力異常";
						}						
					}
					if (isEventType4Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網路服務中斷";
						} else {
							tmpXmlEventType += "網路服務中斷";
						}						
					}
					if (isEventType4Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "設備遺失";
						} else {
							tmpXmlEventType += "設備遺失";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 5:
					xmlEventType = "其他異常原因：";
					xmlEventTypeClass = eventType5Other;
					if (eventType5Other != "") {
						xmlEventType += eventType5Other;
					}
					break;
			}

			// 作業系統名稱、版本
			String xmlIsOs = ""; // 事件發生原因
			Boolean isOsOpt1 = notification.getIsOsOpt1(); // Windows系列
			Boolean isOsOpt2 = notification.getIsOsOpt2(); // Linux系列
			Boolean isOsOpt3 = notification.getIsOsOpt3(); // 其他作業平台
			String isOsOpt3Other = notification.getIsOsOpt3Other(); // 其他作業平台  - 說明
			
			if (isOsOpt1) {
				xmlIsOs = "Windows系列";
			} else if (isOsOpt2) {
				xmlIsOs = "Linux系列";
			} else if (isOsOpt3) {
				xmlIsOs = "其他作業平台：" + isOsOpt3Other;
			}
			if (xmlIsOs == null || xmlIsOs.isEmpty()) {
				xmlIsOs = "無資料";
			}
			
			// IP位址(IP Address) - IPV4
			String ipExternal = notification.getIpExternal(); // 外部
			String ipInternal = notification.getIpInternal(); // 內部
			String xmlIpv4 = "";
			
			if (ipExternal != null) {
				xmlIpv4 = ipExternal; 
			} 
			
			if (ipInternal != null) {
				if (xmlIpv4 == null || xmlIpv4.isEmpty()) {
				    xmlIpv4 = ipInternal;
				} else {
					xmlIpv4 += "|" + ipInternal;
				}
			}
		    if (xmlIpv4 == null || xmlIpv4.isEmpty()) {
		    	xmlIpv4 = "";
		    }
			
			// 網際網路位置(Web-url)
			String xmlWebUrl = notification.getWebUrl();
			if (xmlWebUrl == null || xmlWebUrl.isEmpty()) {
				xmlWebUrl = "";
			}
			
			// Ⅰ. 補強系統/程式安全設定 & Ⅱ. 資安管理與教育訓練
			String xmlIsFinishDo = "";
			String xmlIsFinishDo2 = "";

			switch (eventType) {
				case 1: // 網頁攻擊
					
					Boolean isFinish1DoSysOpt1 = notification.getIsFinish1DoSysOpt1(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish1DoSysOpt2 = notification.getIsFinish1DoSysOpt2(); // 已完成評估變更受害主機中所有帳號之密碼(含本機管理者)
					Boolean isFinish1DoSysOpt3 = notification.getIsFinish1DoSysOpt3(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)
					Boolean isFinish1DoSysOpt4 = notification.getIsFinish1DoSysOpt4(); // 關閉網路芳鄰功能
					Boolean isFinish1DoSysOpt5 = notification.getIsFinish1DoSysOpt5(); // 設定robots.txt檔，控制網站可被搜尋頁面
					Boolean isFinish1DoSysOpt6 = notification.getIsFinish1DoSysOpt6(); // 已針對所有需要特殊存取權限之網頁加強身分驗證機制
					Boolean isFinish1DoSysOpt7 = notification.getIsFinish1DoSysOpt7(); // 限制網站主機上傳之附件檔案類型
					Boolean isFinish1DoSysOpt8 = notification.getIsFinish1DoSysOpt8(); // 限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制
					Boolean isFinish1DoSysOpt9 = notification.getIsFinish1DoSysOpt9(); // 限制連線資料庫之主機IP
					Boolean isFinish1DoSysOpt10 = notification.getIsFinish1DoSysOpt10(); // 關閉 WebDAV(Web Distribution Authoring and Versioning)
					String isFinish1DoSysOpt3Remark = notification.getFinish1DoSysOpt3Remark(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)-說明
					String isFinish1DoSysOpt6Remark = notification.getFinish1DoSysOpt6Remark(); // 已針對所有需要特殊存取權限之網頁加強身分驗證機制-說明
					String isFinish1DoSysOpt7Remark = notification.getFinish1DoSysOpt7Remark(); // 限制網站主機上傳之附件檔案類型-說明
					
					Boolean IsFinish1DoEduOpt1 = notification.getIsFinish1DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish1DoEduOpt2 = notification.getIsFinish1DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish1DoEduOpt3 = notification.getIsFinish1DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish1DoEduOpt4 = notification.getIsFinish1DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish1DoSysOpt1) {
						xmlIsFinishDo = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
						xmlIsFinishDo2 = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
					}
					if (isFinish1DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";					
							xmlIsFinishDo2 = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						} else {
							xmlIsFinishDo += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						}
					}
					if (isFinish1DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
						}
					}
					if (isFinish1DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";
							xmlIsFinishDo2 = "關閉網路芳鄰功能";
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
							xmlIsFinishDo2 += "|" + "關閉網路芳鄰功能";
						}
					}
					if (isFinish1DoSysOpt5) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "設定robots.txt檔，控制網站可被搜尋頁面";
							xmlIsFinishDo2 = "設定robots.txt檔，控制網站可被搜尋頁面";
						} else {
							xmlIsFinishDo += "|" + "設定robots.txt檔，控制網站可被搜尋頁面";
							xmlIsFinishDo2 += "|" + "設定robots.txt檔，控制網站可被搜尋頁面";
						}
					}
					if (isFinish1DoSysOpt6) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
							xmlIsFinishDo2 = "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
						} else {
							xmlIsFinishDo += "|" + "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
							xmlIsFinishDo2 += "|" + "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
						}
					}
					if (isFinish1DoSysOpt7) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
							xmlIsFinishDo2 = "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
						} else {
							xmlIsFinishDo += "|" + "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
							xmlIsFinishDo2 += "|" + "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
						}
					}
					if (isFinish1DoSysOpt8) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
							xmlIsFinishDo2 = "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
						} else {
							xmlIsFinishDo += "|" + "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
							xmlIsFinishDo2 += "|" + "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
						}
					}
					if (isFinish1DoSysOpt9) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制連線資料庫之主機IP";
							xmlIsFinishDo2 = "限制連線資料庫之主機IP";
						} else {
							xmlIsFinishDo += "|" + "限制連線資料庫之主機IP";
							xmlIsFinishDo2 += "|" + "限制連線資料庫之主機IP";
						}
					}
					if (isFinish1DoSysOpt10) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉 WebDAV(Web Distribution Authoring and Versioning)";
							xmlIsFinishDo2 = "關閉 WebDAV(Web Distribution Authoring and Versioning)";
						} else {
							xmlIsFinishDo += "|" + "關閉 WebDAV(Web Distribution Authoring and Versioning)";
							xmlIsFinishDo2 += "|" + "關閉 WebDAV(Web Distribution Authoring and Versioning)";
						}
					}
					
					if (IsFinish1DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish1DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish1DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish1DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
				
				case 2: // 非法入侵

					Boolean isFinish2DoSysOpt1 = notification.getIsFinish2DoSysOpt1(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish2DoSysOpt2 = notification.getIsFinish2DoSysOpt2(); // 已完成評估變更受害主機中所有帳號之密碼(含本機管理者)
					Boolean isFinish2DoSysOpt3 = notification.getIsFinish2DoSysOpt3(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本
					Boolean isFinish2DoSysOpt4 = notification.getIsFinish2DoSysOpt4(); // 關閉郵件伺服器 Open Relay功能
					Boolean isFinish2DoSysOpt5 = notification.getIsFinish2DoSysOpt5(); // 關閉網路芳鄰功能
					String isFinish2DoSysOpt1Remark = notification.getFinish2DoSysOpt1Remark(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)-說明

					Boolean IsFinish2DoEduOpt1 = notification.getIsFinish2DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish2DoEduOpt2 = notification.getIsFinish2DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish2DoEduOpt3 = notification.getIsFinish2DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish2DoEduOpt4 = notification.getIsFinish2DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish2DoSysOpt1) {
						xmlIsFinishDo = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)" + "-" + isFinish2DoSysOpt1Remark;
						xmlIsFinishDo2 = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)" + "-" + isFinish2DoSysOpt1Remark;
					}
					if (isFinish2DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						} else {
							xmlIsFinishDo += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						}
					}
					if (isFinish2DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最版本";
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最版本";
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本";
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本";
						}
					}
					if (isFinish2DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉郵件伺服器 Open Relay功能";
							xmlIsFinishDo2 = "關閉郵件伺服器 Open Relay功能";
						} else {
							xmlIsFinishDo += "|" + "關閉郵件伺服器 Open Relay功能";
							xmlIsFinishDo2 += "|" + "關閉郵件伺服器 Open Relay功能";
						}
					}
					if (isFinish2DoSysOpt5) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";
							xmlIsFinishDo2 = "關閉網路芳鄰功能";
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
							xmlIsFinishDo2 += "|" + "關閉網路芳鄰功能";
						}
					}
					
					if (IsFinish2DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish2DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish2DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish2DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 3: // 阻斷服務(DoS/DDoS)

					Boolean isFinish3DoSysOpt1 = notification.getIsFinish3DoSysOpt1(); // 限制同時間單一 IP 連線
					Boolean isFinish3DoSysOpt2 = notification.getIsFinish3DoSysOpt2(); // DNS主機停用外部遞迴查詢
					Boolean isFinish3DoSysOpt3 = notification.getIsFinish3DoSysOpt3(); // 已完成檢視/移除主機/伺服器不必要服務功能
					Boolean isFinish3DoSysOpt4 = notification.getIsFinish3DoSysOpt4(); // 已完成檢視/更新受害主機系統與所有應用程式至最新版本
					String isFinish3DoSysOpt3Remark = notification.getFinish3DoSysOpt3Remark(); // 已完成檢視/移除主機/伺服器不必要服務功能-說明
					String isFinish3DoSysOpt4Remark = notification.getFinish3DoSysOpt4Remark(); // 已完成檢視/更新受害主機系統與所有應用程式至最新版本-說明

					Boolean IsFinish3DoEduOpt1 = notification.getIsFinish3DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish3DoEduOpt2 = notification.getIsFinish3DoEduOpt2(); // 修正內部資安防護計畫
					
					if (isFinish3DoSysOpt1) {
						xmlIsFinishDo = "限制同時間單一 IP 連線";
						xmlIsFinishDo2 = "限制同時間單一 IP 連線";
					}
					if (isFinish3DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "DNS主機停用外部遞迴查詢";
							xmlIsFinishDo2 = "DNS主機停用外部遞迴查詢";
						} else {
							xmlIsFinishDo += "|" + "DNS主機停用外部遞迴查詢";
							xmlIsFinishDo2 += "|" + "DNS主機停用外部遞迴查詢";
						}
					}
					if (isFinish3DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
							xmlIsFinishDo2 = "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
						}
					}
					if (isFinish3DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
						}
					}

					if (IsFinish3DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish3DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 4: // 設備異常

					Boolean isFinish4DoSysOpt1 = notification.getIsFinish4DoSysOpt1(); // 檢視資訊設備使用年限

					Boolean IsFinish4DoEduOpt1 = notification.getIsFinish4DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish4DoEduOpt2 = notification.getIsFinish4DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish4DoEduOpt3 = notification.getIsFinish4DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish4DoEduOpt4 = notification.getIsFinish4DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish4DoSysOpt1) {
						xmlIsFinishDo = "檢視資訊設備使用年限";
						xmlIsFinishDo2 = "檢視資訊設備使用年限";
					}

					if (IsFinish4DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish4DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish4DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish4DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 5: // 其他 異常原因

					Boolean isFinish5DoSysOpt1 = notification.getIsFinish5DoSysOpt1(); // 已更新受害主機系統與所有應用程式至最新版本
					Boolean isFinish5DoSysOpt2 = notification.getIsFinish5DoSysOpt2(); // 變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish5DoSysOpt3 = notification.getIsFinish5DoSysOpt3(); // 變更受害主機中所有帳號之密碼 (含本機管理者)
					Boolean isFinish5DoSysOpt4 = notification.getIsFinish5DoSysOpt4(); // 關閉網路芳鄰功能
					String isFinish5DoSysOpt1Remark = notification.getFinish5DoSysOpt1Remark(); // 已更新受害主機系統與所有應用程式至最版本-說明

					Boolean IsFinish5DoEduOpt1 = notification.getIsFinish5DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish5DoEduOpt2 = notification.getIsFinish5DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish5DoEduOpt3 = notification.getIsFinish5DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish5DoEduOpt4 = notification.getIsFinish5DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish5DoSysOpt1) {
						xmlIsFinishDo = "已更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish5DoSysOpt1Remark;
					}
					if (isFinish5DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";					
						} else {
							xmlIsFinishDo += "|" + "變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
						}
					}
					if (isFinish5DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "變更受害主機中所有帳號之密碼 (含本機管理者)";					
						} else {
							xmlIsFinishDo += "|" + "變更受害主機中所有帳號之密碼 (含本機管理者)";
						}
					}
					if (isFinish5DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";					
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
						}
					}

					if (IsFinish5DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish5DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish5DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish5DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
			}
			//System.out.println("eventType = " + eventType);
			//System.out.println("xmlIsFinishDo = " + xmlIsFinishDo);
			
			// 取得 通報人 所需資料
			String xmlMemberName = ""; // Member Name
			String xmlMemberPhone = ""; // Member Phone
			String xmlMemberEmail = ""; // Member Email
			
			if (contactorId > 0) {
				Member member = memberService.get(contactorId);

				xmlMemberName = member.getName(); // Member Name
				xmlMemberPhone = notification.getContactorTel();// Member Phone
				xmlMemberEmail = notification.getContactorEmail(); // Member Email
			}
			if (xmlMemberName == null || xmlMemberName.isEmpty()) {
				xmlMemberName = "無資料";
			}
			if (xmlMemberPhone == null || xmlMemberPhone.isEmpty()) {
				xmlMemberPhone = "99999999";
			}
			if (xmlMemberEmail == null || xmlMemberEmail.isEmpty()) {
				xmlMemberEmail = "無資料";
			}

			// 取得 機關(機構)名稱 所需資料
			String xmlOrgContactorUnitName = "無資料";
			
			if (contactorUnit > 0) {
				Org org1 = orgService.getDataById(contactorUnit);
				xmlOrgContactorUnitName = org1.getName();				
			}

			// 取得 main unit 1 (權責機關-上級機關) 所需資料
			String xmlOrgMainUnit1Name = "無資料";
			
			if (mainUnit1 > 0) {
				Org org2 = orgService.getDataById(mainUnit1);
				xmlOrgMainUnit1Name = org2.getName();
			}
			
			// 設定使用哪一組 xml 設定做介接之數字，提供後段程式使用
			int gotoStep = 0;
			
			if (notification.getEffectLevel() >= 1 && notification.getEffectLevel() <= 2) {
				gotoStep = 4; // 1、2結報
			} else if (notification.getEffectLevel() >= 3 && notification.getEffectLevel() <= 4) {
				gotoStep = 5; // 3、4結報
				
//				if (totalAmount > 1) {
//					gotoStep = 6; // 3、4結報(多個受害設備類型)
//				}
			
			}
			//System.out.println("genStixXML() → gotoStep = " + gotoStep);
			
			
			

			// 測試用
			gotoStep = testGotoStep;
			
			
			
			//if (notification.getStatus().equals(1)) {
			if (gotoStep == 1) {
				//System.out.println("genStixXML() → Step 1");
				
				// 初報
				
				// 先組合內層 XML
				// 通報人資料
				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
				// 上面3項屬性寫組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType1 = new CIQIdentity30InstanceType();
				_CIQIdentity30InstanceType1.withSpecification(new STIXCIQIdentity30Type()
						.withPartyName(new PartyNameType()
								.withPersonNames(new PersonName()
										.withNameElements(new NameElement()
												// 通報人姓名
												.withValue(xmlMemberName))))
						.withContactNumbers(new ContactNumbers()
								.withContactNumbers(new ContactNumber()
										.withContactNumberElements(new ContactNumberElement()
												.withType(ContactNumberElementList.LOCAL_NUMBER)
												// 通報人電話
												.withValue(xmlMemberPhone))))
						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
										// 通報人電子郵件信箱
										.withValue(xmlMemberEmail))));
				
				// 先組合內層 XML
				// 事件單內容
				TimeType _TimeType = new TimeType();
				// 事發機關發現知悉資安事件時間
				_TimeType.withIncidentDiscovery(new DateTimeWithPrecisionType(xmlEventDateTime, DateTimePrecisionEnum.SECOND))
				// 事發機關至CI領域中央目的事業主管機關通報資安事件時間
				.withIncidentReported(new DateTimeWithPrecisionType(xmlApplyDateTime, DateTimePrecisionEnum.SECOND));
				
				InformationSourceType _ResponderInformationSourceType =  new InformationSourceType();
				_ResponderInformationSourceType.withDescriptions(new StructuredTextType()
						// 資安廠商
						.withValue(xmlMaintainCompany))
				.withRoles(new ControlledVocabularyStringType()
						// 事發機關是否為關鍵基礎設施提供者編號1代表不是編號2代表是
						.withValue("2"));

				// 先組合內層 XML
				// 事發機關名稱
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// 上面1項組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType2 = new CIQIdentity30InstanceType();
				// 事發機關名稱
				_CIQIdentity30InstanceType2.withName(xmlOrgContactorUnitName)
				.withSpecification(new STIXCIQIdentity30Type()
						.withOrganisationInfo(new OrganisationInfo()
								.withIndustryType("無")));

				// 先組合內層 XML
				// 資安事件綜合評估等級
				ImpactAssessmentType _ImpactAssessmentType = new ImpactAssessmentType();
				_ImpactAssessmentType.withImpactQualification(new ControlledVocabularyStringType()
						// 資安事件綜合評估等級
						.withVocabName("資安事件綜合評估等級")
						.withValue(xmlEffectLevel));
				
				// 先組合內層 XML
				// 影響
				ConfidenceType _ConfidenceType = new ConfidenceType();
				_ConfidenceType.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 跨領域影響之領域別
						.withValue(xmlAffectOther));
				
				StatementType _StatementType = new StatementType();
				_StatementType.withTimestamp(now)
				.withValue(new ControlledVocabularyStringType()
						// 資安事件是否造成外部跨領域影響編號1代表無影響編號2代表尚無法確認編號3代表有影響
						.withValue(xmlIsAffectOthers))
				.withDescriptions(new StructuredTextType()
						// 資安事件跨領域影響說明
						.withValue("資安事件跨領域影響說明"))
				.withConfidence(_ConfidenceType);
				
				// 先組合內層 XML
				Incident _RelatedIncident = new Incident();
				_RelatedIncident.withId(new QName("incident-d1e0711f-d69d-4cac-b242-74cdbc0baf48"))
				.withTimestamp(now)
				.withTime(_TimeType)
				.withDescriptions(new StructuredTextType()
						// 事發機關事件說明與處置措施
						.withValue(xmlEventRemark))
				.withShortDescriptions(new StructuredTextType()
						// AIC影響衝擊評估
						.withValue("100"))
				.withCategories(new CategoriesType()
						.withCategories(new ControlledVocabularyStringType()
								// 事件類型
								.withVocabName(xmlEventTypeClass)))
				.withResponders(_ResponderInformationSourceType)
				.withVictims(_CIQIdentity30InstanceType2)
				.withImpactAssessment(_ImpactAssessmentType)
				.withIntendedEffects(_StatementType)
				.withDiscoveryMethods(new ControlledVocabularyStringType()
						// 通報來源
						.withValue("1"));
				
				// 通報來源
				RelatedIncidentsType _RelatedIncidentsType = new RelatedIncidentsType();
				RelatedIncidentType _RelatedIncidentType = new RelatedIncidentType();
				_RelatedIncidentType.withIncident(_RelatedIncident);
				_RelatedIncidentsType.withRelatedIncidents(_RelatedIncidentType);
				
				// CI領域之中央目的事業主管機關支援項目說明
				CourseOfAction _CourseOfAction1 = new CourseOfAction();
				_CourseOfAction1.withId(new QName("coa-b576ad95-9af4-44f7-ac99-3ef627542ab0"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// CI領域之中央目的事業主管機關支援項目說明
						.withValue(xmlNeedSupportRemark))
				.withShortDescriptions(new StructuredTextType()
						// CI領域之中央目的事業主管機關是否申請外部支援
						.withValue(xmlIsNeedSupport));

				
				// 組合主要 XML
				// 建立事件單
				Incident _Incident = new Incident();
				_Incident.withId(new QName("incident-6a364056-b4ea-4cb7-8b7e-44d6919181c4"))
				.withTimestamp(now)
				.withTime(new TimeType()
						// CI領域中央目的事業主管機關通報審核時間
						.withIncidentOpened(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
				.withDescriptions(new StructuredTextType()
						// CI中央目的事業主管機關通報審核說明
						.withValue("CI中央目的事業主管機關通報審核說明"))
				.withReporter(new InformationSourceType()
						.withIdentity(new IdentityType()
								// CI領域之中央目的事業主管機關機關名稱
								.withName(xmlOrgMainUnit1Name)))
				.withResponders(new InformationSourceType()
						.withIdentity(_CIQIdentity30InstanceType1))
				.withStatus(new ControlledVocabularyStringType()
						// 通報單狀態初報為1
						.withValue("1"))
				.withRelatedIncidents(_RelatedIncidentsType)
				.withCOARequesteds(new COARequestedType()
						.withCourseOfAction(_CourseOfAction1));

				// 加入事件
				IncidentsType _Incidents = new IncidentsType();
				_Incidents.withIncidents(_Incident);

				// 加入STIX
				stixPackage.withIncidents(_Incidents);
//				xmlStr = stixPackage.toXMLString(true);

				xmlStr = "<stix:STIX_Package xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\" \r\n" + 
				"    xmlns:incident=\"http://stix.mitre.org/Incident-1\" \r\n" + 
				"    xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" \r\n" + 
				"    xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\" \r\n" + 
				"    xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\" \r\n" + 
				"    xmlns:stixCommon=\"http://stix.mitre.org/common-1\" \r\n" + 
				"    xmlns:stix=\"http://stix.mitre.org/stix-1\" \r\n" + 
				"    xmlns:example=\"http://example.com\" \r\n" + 
				"    xmlns:xlink=\"http://www.w3.org/1999/xlink\" \r\n" + 
				"    xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" \r\n" + 
				"    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" \r\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"example:Package-bdb4d88f-0943-4e37-a2f9-34024e8e2de4\" version=\"1.2\">\r\n" + 
				"    <stix:Incidents>\r\n" + 
				"        <stix:Incident id=\"example:incident-6a364056-b4ea-4cb7-8b7e-44d6919181c4\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
				"            <incident:Time>\r\n" + 
				"                <!--CI領域中央目的事業主管機關通報審核時間-->\r\n" + 
				"                <incident:Incident_Opened precision=\"second\">" + now + "</incident:Incident_Opened>\r\n" + 
				"            </incident:Time>\r\n" + 
				"            <incident:Description>CI中央目的事業主管機關通報審核說明</incident:Description>\r\n" + 
				"            <incident:Reporter>\r\n" + 
				"                <stixCommon:Identity>\r\n" + 
				"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
				"                </stixCommon:Identity>\r\n" + 
				"            </incident:Reporter>\r\n" + 
				"            <incident:Responder>\r\n" + 
				"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
				"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
				"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
				"                            <!--CI領域中央目的事業主管機關通報人姓名-->\r\n" + 
				"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
				"                                <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
				"                            </xnl:PersonName>\r\n" + 
				"                        </xpil:PartyName>\r\n" + 
				"                        <!--CI領域中央目的事業主管機關通報人連絡電話-->\r\n" + 
				"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
				"                            <xpil:ContactNumber>\r\n" + 
				"                                <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
				"                            </xpil:ContactNumber>\r\n" + 
				"                        </xpil:ContactNumbers>\r\n" + 
				"                        <!--CI領域中央目的事業主管機關通報人電子郵件-->\r\n" + 
				"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
				"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
				"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
				"                    </stix-ciqidentity:Specification>\r\n" + 
				"                </stixCommon:Identity>\r\n" + 
				"            </incident:Responder>\r\n" + 
				"            <!--通報單狀態初報為1-->\r\n" + 
				"            <incident:Status>1</incident:Status>\r\n" + 
				"            <incident:Related_Incidents>\r\n" + 
				"                <incident:Related_Incident>\r\n" + 
				"                    <stixCommon:Incident id=\"example:incident-d1e0711f-d69d-4cac-b242-74cdbc0baf48\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
				"                        <incident:Time>\r\n" + 
				"                            <!--事發機關發現知悉資安事件時間-->\r\n" + 
				"                            <incident:Incident_Discovery precision=\"second\">" + xmlEventDateTime + "</incident:Incident_Discovery>\r\n" + 
				"                            <!--事發機關至CI領域中央目的事業主管機關通報資安事件時間-->\r\n" + 
				"                            <incident:Incident_Reported precision=\"second\">" + xmlApplyDateTime + "</incident:Incident_Reported>\r\n" + 
				"                        </incident:Time>\r\n" + 
				"                        <incident:Description>" + xmlEventRemark + "</incident:Description>\r\n" + 
				"                        <!--AIC影響衝擊評估-->\r\n" + 
				"                        <incident:Short_Description>" + xmlAIC + "</incident:Short_Description>\r\n" + 
				"                        <!--事件類型-->\r\n" + 
				"                        <incident:Categories>\r\n" + 
				"                            <incident:Category vocab_name=\"" + xmlEventTypeClass + "\"/>\r\n" + 
				"                        </incident:Categories>\r\n" + 
				"                        <incident:Responder>\r\n" + 
				"                            <!--資安廠商-->\r\n" + 
				"                            <stixCommon:Description>" + xmlMaintainCompany + "</stixCommon:Description>\r\n" + 
				"                            <!--事發機關是否為關鍵基礎設施提供者編號1代表不是編號2代表是-->\r\n" + 
				"                            <stixCommon:Role>2</stixCommon:Role>\r\n" + 
				"                        </incident:Responder>\r\n" + 
				"                        <incident:Victim xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
				"                            <stixCommon:Name>" + xmlOrgContactorUnitName + "</stixCommon:Name>\r\n" + 
				"                            <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
				"                                <xpil:OrganisationInfo xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" xpil:IndustryType=\"無\"/>\r\n" + 
				"                            </stix-ciqidentity:Specification>\r\n" + 
				"                        </incident:Victim>\r\n" + 
				"                        <incident:Impact_Assessment>\r\n" + 
				"                            <incident:Impact_Qualification vocab_name=\"資安事件綜合評估等級\">" + xmlEffectLevel + "</incident:Impact_Qualification>\r\n" + 
				"                        </incident:Impact_Assessment>\r\n" + 
				"                        <incident:Intended_Effect timestamp=\"2019-04-12T11:00:41.665815+00:00\">\r\n" + 
				"                            <!--資安事件是否造成外部跨領域影響編號1代表無影響編號2代表尚無法確認編號3代表有影響-->\r\n" + 
				"                            <stixCommon:Value>" + xmlIsAffectOthers + "</stixCommon:Value>\r\n" + 
				"                            <stixCommon:Description>資安事件跨領域影響說明</stixCommon:Description>\r\n" + 
				"                            <stixCommon:Confidence timestamp=\"2019-04-12T11:00:41.665815+00:00\">\r\n" + 
				"                                <!--跨領域影響之領域別-->\r\n" + 
				"                                <stixCommon:Description>" + xmlAffectOther + "</stixCommon:Description>\r\n" + 
				"                            </stixCommon:Confidence>\r\n" + 
				"                        </incident:Intended_Effect>\r\n" + 
				"                        <!--通報來源-->\r\n" + 
				"                        <incident:Discovery_Method>" + xmlEventSource + "</incident:Discovery_Method>\r\n" + 
				"                    </stixCommon:Incident>\r\n" + 
				"                </incident:Related_Incident>\r\n" + 
				"            </incident:Related_Incidents>\r\n" + 
				"            <incident:COA_Requested>\r\n" + 
				"                <incident:Course_Of_Action id=\"example:coa-b576ad95-9af4-44f7-ac99-3ef627542ab0\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
				"                    <coa:Description>" + xmlNeedSupportRemark + "</coa:Description>\r\n" + 
				"                    <!--CI領域之中央目的事業主管機關是否申請外部支援-->\r\n" + 
				"                    <coa:Short_Description>" + xmlIsNeedSupport + "</coa:Short_Description>\r\n" + 
				"                </incident:Course_Of_Action>\r\n" + 
				"            </incident:COA_Requested>\r\n" + 
				"        </stix:Incident>\r\n" + 
				"    </stix:Incidents>\r\n" + 
				"</stix:STIX_Package>";
				
			} else if (gotoStep == 2) {
				//System.out.println("genStixXML() → Step 2");
				
				// 初報-編輯

				// 先組合內層 XML
				// 通報人資料
				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
				// 上面3項屬性寫組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType1 = new CIQIdentity30InstanceType();
				_CIQIdentity30InstanceType1.withSpecification(new STIXCIQIdentity30Type()
						.withPartyName(new PartyNameType()
								.withPersonNames(new PersonName()
										.withNameElements(new NameElement()
												// 通報人姓名
												.withValue(xmlMemberName))))
						.withContactNumbers(new ContactNumbers()
								.withContactNumbers(new ContactNumber()
										.withContactNumberElements(new ContactNumberElement()
												.withType(ContactNumberElementList.LOCAL_NUMBER)
												// 通報人電話
												.withValue(xmlMemberPhone))))
						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
										// 通報人電子郵件信箱
										.withValue(xmlMemberEmail))));
				
				// 通報單細項資料 (第1個內層 - )
				// 先組合內層 XML
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// 上面1項屬性組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType2 = new CIQIdentity30InstanceType();
				// 事發機關名稱
				_CIQIdentity30InstanceType2.withName("事發機關名稱")
				.withSpecification(new STIXCIQIdentity30Type()
						.withOrganisationInfo(new OrganisationInfo()
								.withIndustryType("無")));
				
				// 通報單細項資料
				// 先組合內層 XML
				Incident _Incidentsub = new Incident();
				_Incidentsub.withId(new QName("incident-8a7a803d-f272-44fd-ae5d-113c22a99705"))
				.withTimestamp(now)
				.withTime(new TimeType()
						// 事發機關發現知悉資安事件時間
						.withIncidentDiscovery(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND))
						// 事發機關至CI領域中央目的事業主管機關通報資安事件時間
						.withIncidentReported(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
				.withDescriptions(new StructuredTextType()
						// 事發機關事件說明與處置措施
						.withValue("事發機關事件說明與處置措施"))
				.withShortDescriptions(new StructuredTextType()
						// AIC影響衝擊評估
						.withValue("100"))
				.withCategories(new CategoriesType()
						.withCategories(new ControlledVocabularyStringType()
								// 事件類型
								.withVocabName("1")))
				.withResponders(new InformationSourceType()
						.withDescriptions(new StructuredTextType()
								// 資安廠商
								.withValue("資安廠商"))
						.withRoles(new ControlledVocabularyStringType()
								// 事發機關是否為關鍵基礎設施提供者編號1代表不是編號2代表是
								.withValue("2")))
				.withVictims(_CIQIdentity30InstanceType2)
				.withImpactAssessment(new ImpactAssessmentType()
						.withImpactQualification(new ControlledVocabularyStringType()
								// 資安事件綜合評估等級
								.withVocabName("資安事件綜合評估等級")
								.withValue("1")))
				.withIntendedEffects(new StatementType()
						.withTimestamp(now)
						.withValue(new ControlledVocabularyStringType()
								// 資安事件是否造成外部跨領域影響編號1代表無影響編號2代表尚無法確認編號3代表有影響
								.withValue("3"))
						.withDescriptions(new StructuredTextType()
								// 資安事件跨領域影響說明
								.withValue("資安事件跨領域影響說明"))
						.withConfidence(new ConfidenceType()
								.withTimestamp(now)
								.withDescriptions(new StructuredTextType()
										// 跨領域影響之領域別
										.withValue("123"))))
				.withDiscoveryMethods(new ControlledVocabularyStringType()
						// 通報來源
						.withValue("1"));
				
				// CI領域之中央目的事業主管機關支援項目說明
				// 先組合內層 XML
				CourseOfAction _CourseOfActionType = new CourseOfAction();
				_CourseOfActionType.withId(new QName("coa-d96a85d6-0a4b-47a6-9e84-5cd6a3085363"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// CI領域之中央目的事業主管機關支援項目說明
						.withValue("CI領域之中央目的事業主管機關支援項目說明"))
				.withShortDescriptions(new StructuredTextType()
						// CI領域之中央目的事業主管機關是否申請外部支援
						.withValue("2"));
				
				// 組合主要 XML
				// 建立事件單
				Incident _Incident = new Incident();
				_Incident.withId(new QName("incident-aa2fc6f5-f78f-4901-b825-ebec5a843bd5"))
				.withTimestamp(now)
				.withExternalIDs(new ExternalIDType()
						// Ncert通報單ID編號
						.withValue(xmlPostId))
				.withTime(new TimeType()
						.withIncidentOpened(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
				.withDescriptions(new StructuredTextType()
						// CI中央目的事業主管機關通報審核說明
						.withValue("CI中央目的事業主管機關通報審核說明"))
				.withReporter(new InformationSourceType()
						.withIdentity(new IdentityType()
								// CI領域之中央目的事業主管機關機關名稱
								.withName(xmlOrgMainUnit1Name)))
				.withResponders(new InformationSourceType()
						.withIdentity(_CIQIdentity30InstanceType1))
				.withStatus(new ControlledVocabularyStringType()
						// 通報單狀態
						.withValue("1"))
				.withRelatedIncidents(new RelatedIncidentsType()
						.withRelatedIncidents(new RelatedIncidentType()
								.withIncident(_Incidentsub)))
				.withCOARequesteds(new COARequestedType()
						.withCourseOfAction(_CourseOfActionType));
				

				// 加入事件
				IncidentsType _Incidents = new IncidentsType();
				_Incidents.withIncidents(_Incident);

				// 加入STIX
				stixPackage.withIncidents(_Incidents);
				//xmlStr = stixPackage.toXMLString(true);
				
				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-f8b8c87b-9b3d-48eb-a283-4842af7606ca\" version=\"1.2\">\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-aa2fc6f5-f78f-4901-b825-ebec5a843bd5\" timestamp=\"2019-04-12T11:15:05.275460+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"             <!--Ncert通報單ID編號-->\r\n" + 
						"			<incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <incident:Time>\r\n" + 
						"			<!--CI領域中央目的事業主管機關通報審核時間-->\r\n" + 
						"                <incident:Incident_Opened precision=\"second\">2012-06-10T00:00:00</incident:Incident_Opened>\r\n" + 
						"            </incident:Time>\r\n" + 
						"            <incident:Description>CI中央目的事業主管機關通報審核說明</incident:Description>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"			<!--通報單狀態-->\r\n" + 
						"            <incident:Status>1</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-8a7a803d-f272-44fd-ae5d-113c22a99705\" timestamp=\"2019-04-12T11:15:05.275460+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <incident:Time>\r\n" + 
						"                            <!--事發機關發現知悉資安事件時間-->\r\n" + 
						"                            <incident:Incident_Discovery precision=\"second\">" + xmlEventDateTime + "</incident:Incident_Discovery>\r\n" + 
						"                            <!--事發機關至CI領域中央目的事業主管機關通報資安事件時間-->\r\n" + 
						"							<incident:Incident_Reported precision=\"second\">" + xmlApplyDateTime + "</incident:Incident_Reported>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:Description>" + xmlEventRemark + "</incident:Description>\r\n" + 
						"                        <!--AIC影響衝擊評估-->\r\n" + 
						"						<incident:Short_Description>100</incident:Short_Description>\r\n" + 
						"                        <!--事件類型-->\r\n" + 
						"						<incident:Categories>\r\n" + 
						"                            <incident:Category vocab_name=\"1\"/>\r\n" + 
						"                        </incident:Categories>\r\n" + 
						"                        <incident:Responder>\r\n" + 
						"                            <stixCommon:Description>" + xmlMaintainCompany + "</stixCommon:Description>\r\n" + 
						"                            <!--事發機關是否為關鍵基礎設施提供者編號1代表不是編號2代表是-->\r\n" + 
						"							<stixCommon:Role>2</stixCommon:Role>\r\n" + 
						"                        </incident:Responder>\r\n" + 
						"                        <incident:Victim xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                            <stixCommon:Name>" + xmlOrgContactorUnitName + "</stixCommon:Name>\r\n" + 
						"                            <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                                <xpil:OrganisationInfo xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" xpil:IndustryType=\"無\"/>\r\n" + 
						"                            </stix-ciqidentity:Specification>\r\n" + 
						"                        </incident:Victim>\r\n" + 
						"                        <incident:Impact_Assessment>\r\n" + 
						"                            <incident:Impact_Qualification vocab_name=\"資安事件綜合評估等級\">" + xmlEffectLevel + "</incident:Impact_Qualification>\r\n" + 
						"                        </incident:Impact_Assessment>\r\n" + 
						"                        <incident:Intended_Effect timestamp=\"2019-04-12T11:15:05.275460+00:00\">\r\n" + 
						"                            <!--資安事件是否造成外部跨領域影響編號1代表無影響編號2代表尚無法確認編號3代表有影響-->\r\n" + 
						"							<stixCommon:Value>" + xmlIsAffectOthers + "</stixCommon:Value>\r\n" + 
						"                            <stixCommon:Description>資安事件跨領域影響說明</stixCommon:Description>\r\n" + 
						"                            <stixCommon:Confidence timestamp=\"2019-04-12T11:15:05.275460+00:00\">\r\n" + 
						"                                <!--跨領域影響之領域別-->\r\n" + 
						"								<stixCommon:Description>" + xmlAffectOther + "</stixCommon:Description>\r\n" + 
						"                            </stixCommon:Confidence>\r\n" + 
						"                        </incident:Intended_Effect>\r\n" + 
						"						<!--通報來源-->\r\n" + 
						"                        <incident:Discovery_Method>1</incident:Discovery_Method>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"            <incident:COA_Requested>\r\n" + 
						"                <incident:Course_Of_Action id=\"example:coa-d96a85d6-0a4b-47a6-9e84-5cd6a3085363\" timestamp=\"2019-04-12T11:15:05.275460+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                    <coa:Description>" + xmlNeedSupportRemark + "</coa:Description>\r\n" + 
						"                    <!--CI領域之中央目的事業主管機關是否申請外部支援-->\r\n" + 
						"					<coa:Short_Description>" + xmlIsNeedSupport + "</coa:Short_Description>\r\n" + 
						"                </incident:Course_Of_Action>\r\n" + 
						"            </incident:COA_Requested>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";
				
			} else if (gotoStep == 3) {
				//System.out.println("genStixXML() → Step 3");
				
				// 續報

				// 先組合內層 XML
				// 通報人資料
				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
				// 上面3項屬性寫組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType1 = new CIQIdentity30InstanceType();
				_CIQIdentity30InstanceType1.withSpecification(new STIXCIQIdentity30Type()
						.withPartyName(new PartyNameType()
								.withPersonNames(new PersonName()
										.withNameElements(new NameElement()
												// 通報人姓名
												.withValue(xmlMemberName))))
						.withContactNumbers(new ContactNumbers()
								.withContactNumbers(new ContactNumber()
										.withContactNumberElements(new ContactNumberElement()
												.withType(ContactNumberElementList.LOCAL_NUMBER)
												// 通報人電話
												.withValue(xmlMemberPhone))))
						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
										// 通報人電子郵件信箱
										.withValue(xmlMemberEmail))));
				
				// 先組合內層 XML
				COAStageVocab10 _COAStageVocab101 = new COAStageVocab10();
				// 資安事件是否完成損害控制或復原
				_COAStageVocab101.withVocabName("2");
				
				CourseOfAction _CourseOfActionType1 = new CourseOfAction();
				_CourseOfActionType1.withId(new QName("coa-c65bf3a6-f5bb-4ad5-81c9-4c5ada99e129"))
				.withTimestamp(now)
				.withStage(_COAStageVocab101)
				.withDescriptions(new StructuredTextType()
						// 資安事件應變處置說明
						.withValue("資安事件應變處置說明"));
				
				// 通報單細項資料
				// 先組合內層 XML
				Incident _Incidentsub = new Incident();
				_Incidentsub.withId(new QName("incident-13d6556d-5daa-4bd2-9a54-5ce076f3f515"))
				.withTimestamp(now)
				.withTime(new TimeType()
						// 事發機關完成損害控制或復原辦理通知時間
						.withContainmentAchieved(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
				.withCOATakens(new COATakenType()
						// 事發機關完成損害控制或復原時間
						.withTime(new COATimeType().withEnd(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
						.withCourseOfAction(_CourseOfActionType1));
				
				
				// 組合主要 XML
				// 建立事件單
				Incident _Incident = new Incident();
				_Incident.withId(new QName("incident-aa2fc6f5-f78f-4901-b825-ebec5a843bd5"))
				.withTimestamp(now)
				.withExternalIDs(new ExternalIDType()
						// Ncert通報單ID編號
						.withValue(xmlPostId))
				.withReporter(new InformationSourceType()
						.withIdentity(new IdentityType()
								// CI領域之中央目的事業主管機關機關名稱
								.withName(xmlOrgMainUnit1Name)))
				.withResponders(new InformationSourceType()
						.withIdentity(_CIQIdentity30InstanceType1))
				.withStatus(new ControlledVocabularyStringType()
						// 通報單狀態
						.withValue("3"))
				.withRelatedIncidents(new RelatedIncidentsType()
						.withRelatedIncidents(new RelatedIncidentType()
								.withIncident(_Incidentsub)));

				// 加入事件
				IncidentsType _Incidents = new IncidentsType();
				_Incidents.withIncidents(_Incident);

				// 加入STIX
				stixPackage.withIncidents(_Incidents);
				//xmlStr = stixPackage.toXMLString(true);
				

				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:stixVocabs=\"http://stix.mitre.org/default_vocabularies-1\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-6f6050b4-c507-4ec2-80eb-906e07c86678\" version=\"1.2\">\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-54f727ee-740f-43f8-924f-f718729a09c6\" timestamp=\"2019-04-12T11:24:45.596026+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"            <!--Ncert通報單ID編號-->\r\n" + 
						"            <incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"            <!--通報單狀態-->\r\n" + 
						"            <incident:Status>3</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-13d6556d-5daa-4bd2-9a54-5ce076f3f515\" timestamp=\"2019-04-12T11:24:45.596026+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <!--事發機關完成損害控制或復原辦理通知時間-->\r\n" + 
						"                        <incident:Time>\r\n" + 
						"                            <incident:Containment_Achieved precision=\"second\">" + now + "</incident:Containment_Achieved>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:COA_Taken>\r\n" + 
						"                            <incident:Time>\r\n" + 
						"							<!--事發機關完成損害控制或復原時間-->\r\n" + 
						"                                <incident:End precision=\"second\">" + xmlResControlTime + "</incident:End>\r\n" + 
						"                            </incident:Time>\r\n" + 
						"                            <incident:Course_Of_Action id=\"example:coa-c65bf3a6-f5bb-4ad5-81c9-4c5ada99e129\" timestamp=\"2019-04-12T11:24:45.596026+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                <!--資安事件是否完成損害控制或復原-->\r\n" + 
						"                                <coa:Stage xsi:type=\"stixVocabs:COAStageVocab-1.0\" vocab_name=\"" + testStep3VocabName + "\"/>\r\n" + 
						"                                <!--資安事件應變處置說明-->\r\n" + 
						"                                <coa:Description>" + xmlResDescription + "</coa:Description>\r\n" + 
						"                            </incident:Course_Of_Action>\r\n" + 
						"                        </incident:COA_Taken>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";
				

			} else if (gotoStep == 4) {
				//System.out.println("genStixXML() → Step 4");
				
				// 結報1、2

				// Resources
				// 先組合內層 XML
				HashNameVocab10 _HashNameVocab10 = new HashNameVocab10();
				// 發現之惡意程式檔案Hash類別
				_HashNameVocab10.withValue("SHA256");
				
				// 先組合內層 XML
				FileObjectType _FileObjectType = new FileObjectType();
				_FileObjectType.withFileName(new StringObjectPropertyType()
						// 惡意程式檔名
						.withValue("惡意程式檔名"))
				.withFilePath(new FilePathType()
						// 惡意程式路徑
						.withValue("惡意程式路徑"))
				.withHashes(new HashListType()
						.withHashes(new HashType()
							.withType(_HashNameVocab10)
							.withSimpleHashValue(new SimpleHashValueType()
									// 發現之惡意程式檔案Hash值
									.withValue("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae"))));

				// 事件發生原因
				// 先組合內層 XML
				ExploitTarget _ExploitTarget = new ExploitTarget();
				_ExploitTarget.withId(new QName("et-febdaefb-74d1-41ca-b040-aedf6ad7a086"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 事件發生原因
						.withValue(xmlFinish1Reason));
				
				// 組合主要 XML
				TTP _TTP = new TTP();
				_TTP.withId(new QName("ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 攻擊手法與調查結果說明
						.withValue(xmlEventType))
				.withIntendedEffects(new StatementType()
						.withTimestamp(now)
						.withValue(new ControlledVocabularyStringType()
								// 損害類別
								.withValue("1"))
						.withDescriptions(new StructuredTextType()
								// 損害類別說明
								.withValue("損害類別說明")));
					
					// 再組 Resources 項下各層 XML
					_TTP.withResources(new ResourceType()
							.withInfrastructure(new InfrastructureType()
									.withObservableCharacterization(new Observables()
											.withCyboxMajorVersion("2")
											.withCyboxMinorVersion("1")
											.withCyboxUpdateVersion("0")
											.withObservables(new Observable()
													.withId(new QName("Observable-eda7af28-877f-45ec-bf8a-75db2ed19082"))
													.withObject(new ObjectType()
															.withId(new QName("File-d6d68727-c3bf-469e-bb18-ef603190d423"))
															.withProperties(_FileObjectType))))))
					.withExploitTargets(new ExploitTargetsType()
							.withExploitTargets(new RelatedExploitTargetType()
									.withExploitTarget(_ExploitTarget)));
				
				// 加入事件
				TTPsType _TTPs = new TTPsType().withTTPS(_TTP);
			
				// 加入 stixPackage
				stixPackage.withTTPs(_TTPs);
				
				
				// 先組合內層 XML
				// 通報人資料
				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
				// 上面3項屬性寫組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType2 = new CIQIdentity30InstanceType();
				_CIQIdentity30InstanceType2.withSpecification(new STIXCIQIdentity30Type()
						.withPartyName(new PartyNameType()
								.withPersonNames(new PersonName()
										.withNameElements(new NameElement()
												// 通報人姓名
												.withValue(xmlMemberName))))
						.withContactNumbers(new ContactNumbers()
								.withContactNumbers(new ContactNumber()
										.withContactNumberElements(new ContactNumberElement()
												.withType(ContactNumberElementList.LOCAL_NUMBER)
												// 通報人電話
												.withValue(xmlMemberPhone))))
						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
										// 通報人電子郵件信箱
										.withValue(xmlMemberEmail))));
				

				// 建立事件單
				Incident _Incident = new Incident();
				_Incident.withId(new QName("incident-7a12b412-7c10-4743-9715-b14a75ff6812"))
				.withTimestamp(now)
				.withExternalIDs(new ExternalIDType()
						// Ncert通報單ID編號
						.withValue(xmlPostId))
				.withReporter(new InformationSourceType()
						.withIdentity(new IdentityType()
								// CI領域之中央目的事業主管機關機關名稱
								.withName(xmlOrgMainUnit1Name)))
				.withResponders(new InformationSourceType()
						.withIdentity(_CIQIdentity30InstanceType2))
				.withStatus(new ControlledVocabularyStringType()
						// 通報單狀態
						.withValue("4"));

				
				// 通報單狀態
				// 第 1 組 Observables
				Device _DeviceObjectType1 = new Device();
				_DeviceObjectType1.withModel(new StringObjectPropertyType()
						// 受害設備型號
						.withValue("受害設備型號"));
				
				Product _ProductObjectType1 = new Product();
				_ProductObjectType1.withVendor(new StringObjectPropertyType()
						// 受害設備廠牌
						.withValue("受害設備廠牌"))
				.withDeviceDetails(_DeviceObjectType1);

				// 第 2 組 Observables
				// 先組合內層 XML
				SystemObjectType _SystemObjectType2 = new SystemObjectType();
				_SystemObjectType2.withOS(new OSType()
						.withPlatform(new PlatformSpecificationType()
								.withDescription(new org.mitre.cybox.common_2.StructuredTextType()
										// 受害設備作業系統/平台
										.withValue(xmlIsOs))));
				
				Product _ProductObjectType2 = new Product();
				_ProductObjectType2.withDeviceDetails(_SystemObjectType2);
				
				// 第 3 組 Observables
				// 先組合內層 XML
				Address _AddressObjectType3 = new Address();
				_AddressObjectType3.withAddressValue(new StringObjectPropertyType()
						// 受害設備IPv4位址
						.withValue(xmlIpv4))
				.withCategory(CategoryTypeEnum.IPV_4_ADDR);
				
				// 第 4 組 Observables
				// 先組合內層 XML
				Address _AddressObjectType4 = new Address();
				_AddressObjectType4.withAddressValue(new StringObjectPropertyType()
						// 受害設備IPv6位址
						.withValue("2001:0DB8:02de:0000:0000:0000:0000:0e13"))
				.withCategory(CategoryTypeEnum.IPV_6_ADDR);

				// 第 5 組 Observables
				// 先組合內層 XML
				URIObjectType _URIObjectType5 = new URIObjectType();
				_URIObjectType5.withValue(new AnyURIObjectPropertyType()
						// 受害設備URL
						.withValue(xmlWebUrl))
				.withType(URITypeEnum.URL);

				// 後續補強措施及成效追蹤機制
				// 先組合內層 XML
				CourseOfAction _CourseOfActionType2 = new CourseOfAction();
				_CourseOfActionType2.withId(new QName("coa-be82a374-dab5-4ac9-931c-ef943a04a0f5"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 後續補強措施及成效追蹤機制
						.withValue(xmlIsFinishDo));
				
				CourseOfAction _CourseOfActionType1 = new CourseOfAction();
				_CourseOfActionType1.withId(new QName("coa-1e775993-580f-4e5b-a62e-50877f903fd3"))
				.withTimestamp(now)
				.withRelatedCOAs(new RelatedCOAsType()
						.withRelatedCOAs(new RelatedCourseOfActionType()
								.withCourseOfAction(_CourseOfActionType2)));

				Incident _Incidentsub = new Incident();
				_Incidentsub.withId(new QName("incident-f2757543-6c5f-43ac-a449-41ad623ad798"))
				.withTimestamp(now)
				.withTime(new TimeType()
						.withIncidentClosed(new DateTimeWithPrecisionType()
								.withPrecision(DateTimePrecisionEnum.SECOND)
								// 事發機關結報時間
								.withValue(xmlFinishDateTime)));
				_Incidentsub.withAffectedAssets(new AffectedAssetsType()
						.withAffectedAssets(new AffectedAssetType()
								.withType(new AssetTypeType()
										// 受害設備數量
										.withCountAffected(xmlTotalAmount)
										// 受害設備類型
										.withValue(xmlTotalAmount))
								.withDescriptions(new StructuredTextType()
										// 受害設備說明
										.withValue("受害設備說明"))
								.withStructuredDescription(new Observables()
										.withCyboxMajorVersion("2")
										.withCyboxMinorVersion("1")
										.withCyboxUpdateVersion("0")
										.withObservables(new Observable()
												.withId(new QName("Observable-d29ca0b6-12df-4e9a-9729-afdc8dddb0ab"))
												.withObject(new ObjectType()
														.withId(new QName("Product-03795a20-c3e9-42f3-b6e4-ba1f15d90d74"))
														.withProperties(_ProductObjectType1)))
										.withObservables(new Observable()
												.withId(new QName("Observable-24fb54ae-46fd-4474-b8aa-7f31bf28c1b9"))
												.withObject(new ObjectType()
														.withId(new QName("Product-f6f8541e-2d29-474c-b526-b67bae8f1813"))
														.withProperties(_ProductObjectType2)))
										.withObservables(new Observable()
												.withId(new QName("Observable-67143c00-a4b9-4a41-bfea-7ce94629bbc0"))
												.withObject(new ObjectType()
														.withId(new QName("Address-e9a50521-d74f-4fbb-870e-b1004fa78860"))
														.withProperties(_AddressObjectType3)))
										.withObservables(new Observable()
												.withId(new QName("Observable-f0cafcb8-8d6a-40f4-938e-c178cd693f67"))
												.withObject(new ObjectType()
														.withId(new QName("Address-90ad16b5-1c2d-48a0-a0b8-c79f39318cc8"))
														.withProperties(_AddressObjectType4)))
										.withObservables(new Observable()
												.withId(new QName("Observable-a20ffcd8-df1c-47d5-8357-0561eca78895"))
												.withObject(new ObjectType()
														.withId(new QName("URI-797de1c2-5d12-4a05-96fc-be08634a4c7a"))
														.withProperties(_URIObjectType5))))))
				.withLeveragedTTPs(new LeveragedTTPsType()
						.withLeveragedTTPs(new RelatedTTPType()
								.withTTP(new TTPBaseType()
										.withIdref(new QName("ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf")))))
				.withCOATakens(new COATakenType()
						.withCourseOfAction(_CourseOfActionType1));
				
				// 組合主要 XML
				_Incident.withRelatedIncidents(new RelatedIncidentsType()
						.withRelatedIncidents(new RelatedIncidentType()
								.withIncident(_Incidentsub)));
				
				// 加入事件
				IncidentsType _Incidents = new IncidentsType();
				_Incidents.withIncidents(_Incident);
				
				// 加入 stixPackage
				stixPackage.withIncidents(_Incidents);
//				xmlStr = stixPackage.toXMLString(true);
				
				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:FileObj=\"http://cybox.mitre.org/objects#FileObject-2\"\r\n" + 
						"	xmlns:ProductObj=\"http://cybox.mitre.org/objects#ProductObject-2\"\r\n" + 
						"	xmlns:DeviceObj=\"http://cybox.mitre.org/objects#DeviceObject-2\"\r\n" + 
						"	xmlns:AddressObj=\"http://cybox.mitre.org/objects#AddressObject-2\"\r\n" + 
						"	xmlns:SystemObj=\"http://cybox.mitre.org/objects#SystemObject-2\"\r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:URIObj=\"http://cybox.mitre.org/objects#URIObject-2\"\r\n" + 
						"	xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:ttp=\"http://stix.mitre.org/TTP-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\"\r\n" + 
						"	xmlns:et=\"http://stix.mitre.org/ExploitTarget-1\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:cybox=\"http://cybox.mitre.org/cybox-2\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-2eb74fe8-af76-4656-8d5c-cba9f1ac2acd\" version=\"1.2\">\r\n" + 
						"    <stix:TTPs>\r\n" + 
						"        <stix:TTP id=\"example:ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='ttp:TTPType'>\r\n" + 
						"            <ttp:Description>攻擊手法與調查結果說明</ttp:Description>\r\n" + 
						"            <ttp:Intended_Effect timestamp=\"2019-04-12T11:31:48.697623+00:00\">\r\n" + 
						"                <!--損害類別-->\r\n" + 
						"				<stixCommon:Value>1</stixCommon:Value>\r\n" + 
						"                <stixCommon:Description>損害類別說明</stixCommon:Description>\r\n" + 
						"            </ttp:Intended_Effect>\r\n" + 
						"            <ttp:Resources>\r\n" + 
						"                <ttp:Infrastructure>\r\n" + 
						"                    <ttp:Observable_Characterization cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                        <cybox:Observable id=\"example:Observable-eda7af28-877f-45ec-bf8a-75db2ed19082\">\r\n" + 
						"                            <cybox:Object id=\"example:File-d6d68727-c3bf-469e-bb18-ef603190d423\">\r\n" + 
						"                                <cybox:Properties xsi:type=\"FileObj:FileObjectType\">\r\n" + 
						"                                    <FileObj:File_Name />\r\n" + 
						"                                    <FileObj:File_Path />\r\n" + 
						"                                    <FileObj:Hashes>\r\n" + 
						"                                        <cyboxCommon:Hash>\r\n" + 
						"										<!--發現之惡意程式檔案Hash類別-->\r\n" + 
						"                                            <cyboxCommon:Type xsi:type=\"cyboxVocabs:HashNameVocab-1.0\" />\r\n" + 
						"											<!--發現之惡意程式檔案Hash值-->\r\n" + 
						"                                            <cyboxCommon:Simple_Hash_Value />\r\n" + 
						"                                        </cyboxCommon:Hash>\r\n" + 
						"                                    </FileObj:Hashes>\r\n" + 
						"                                </cybox:Properties>\r\n" + 
						"                            </cybox:Object>\r\n" + 
						"                        </cybox:Observable>\r\n" + 
						"                    </ttp:Observable_Characterization>\r\n" + 
						"                </ttp:Infrastructure>\r\n" + 
						"            </ttp:Resources>\r\n" + 
						"            <ttp:Exploit_Targets>\r\n" + 
						"                <!--事件發生原因-->\r\n" + 
						"				<ttp:Exploit_Target>\r\n" + 
						"                    <stixCommon:Exploit_Target id=\"example:et-febdaefb-74d1-41ca-b040-aedf6ad7a086\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='et:ExploitTargetType'>\r\n" + 
						"                        <et:Description>" + xmlFinish1Reason + "</et:Description>\r\n" + 
						"                    </stixCommon:Exploit_Target>\r\n" + 
						"                </ttp:Exploit_Target>\r\n" + 
						"            </ttp:Exploit_Targets>\r\n" + 
						"        </stix:TTP>\r\n" + 
						"    </stix:TTPs>\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-7a12b412-7c10-4743-9715-b14a75ff6812\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"            <!--Ncert通報單ID編號-->\r\n" + 
						"			<incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"			<!--通報單狀態-->\r\n" + 
						"            <incident:Status>4</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-f2757543-6c5f-43ac-a449-41ad623ad798\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <incident:Time>\r\n" + 
						"						<!--事發機關結報時間-->\r\n" + 
						"                            <incident:Incident_Closed precision=\"second\">" + xmlFinishDateTime + "</incident:Incident_Closed>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:Affected_Assets>\r\n" + 
						"                            <incident:Affected_Asset>\r\n" + 
						"							<!--受害設備數量與類型-->\r\n" + 
						"                                <incident:Type count_affected=\"" + xmlTotalAmount + "\">1</incident:Type>\r\n" + 
						"                                <incident:Description>受害設備說明</incident:Description>\r\n" + 
						"                                <incident:Structured_Description cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-d29ca0b6-12df-4e9a-9729-afdc8dddb0ab\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-03795a20-c3e9-42f3-b6e4-ba1f15d90d74\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Vendor>受害設備廠牌</ProductObj:Vendor>\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"DeviceObj:DeviceObjectType\">\r\n" + 
						"                                                    <DeviceObj:Model>受害設備型號</DeviceObj:Model>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-24fb54ae-46fd-4474-b8aa-7f31bf28c1b9\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-f6f8541e-2d29-474c-b526-b67bae8f1813\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"SystemObj:SystemObjectType\">\r\n" + 
						"                                                    <SystemObj:OS>\r\n" + 
						"                                                        <SystemObj:Platform>\r\n" + 
						"                                                            <cyboxCommon:Description>" + xmlIsOs + "</cyboxCommon:Description>\r\n" + 
						"                                                        </SystemObj:Platform>\r\n" + 
						"                                                    </SystemObj:OS>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-67143c00-a4b9-4a41-bfea-7ce94629bbc0\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-e9a50521-d74f-4fbb-870e-b1004fa78860\">\r\n" + 
						"                                            <!--受害設備IPv4位址-->\r\n" + 
						"											<cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv4-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value>" + xmlIpv4 + "</AddressObj:Address_Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-f0cafcb8-8d6a-40f4-938e-c178cd693f67\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-90ad16b5-1c2d-48a0-a0b8-c79f39318cc8\">\r\n" + 
						"										<!--受害設備IPv6位址-->\r\n" + 
						"                                            <cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv6-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value />\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-a20ffcd8-df1c-47d5-8357-0561eca78895\">\r\n" + 
						"                                        <cybox:Object id=\"example:URI-797de1c2-5d12-4a05-96fc-be08634a4c7a\">\r\n" + 
						"										<!--受害設備URL-->\r\n" + 
						"                                            <cybox:Properties xsi:type=\"URIObj:URIObjectType\" type=\"URL\">\r\n" + 
						"                                                <URIObj:Value>" + xmlWebUrl + "</URIObj:Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                </incident:Structured_Description>\r\n" + 
						"                            </incident:Affected_Asset>\r\n" + 
						"                        </incident:Affected_Assets>\r\n" + 
						"                        <incident:Leveraged_TTPs>\r\n" + 
						"                            <incident:Leveraged_TTP>\r\n" + 
						"                                <stixCommon:TTP idref=\"example:ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf\" xsi:type='ttp:TTPType'/>\r\n" + 
						"                            </incident:Leveraged_TTP>\r\n" + 
						"                        </incident:Leveraged_TTPs>\r\n" + 
						"                        <incident:COA_Taken>\r\n" + 
						"                            <incident:Course_Of_Action id=\"example:coa-1e775993-580f-4e5b-a62e-50877f903fd3\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                <coa:Related_COAs>\r\n" + 
						"                                    <coa:Related_COA>\r\n" + 
						"                                        <stixCommon:Course_Of_Action id=\"example:coa-be82a374-dab5-4ac9-931c-ef943a04a0f5\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                            <coa:Description>" + xmlIsFinishDo + "</coa:Description>\r\n" + 
						"                                        </stixCommon:Course_Of_Action>\r\n" + 
						"                                    </coa:Related_COA>\r\n" + 
						"                                </coa:Related_COAs>\r\n" + 
						"                            </incident:Course_Of_Action>\r\n" + 
						"                        </incident:COA_Taken>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";

			} else if (gotoStep == 5) {
				//System.out.println("genStixXML() → Step 5");
				
				// 3、4級結報
				
				// 先組合內層 XML
				// 發現之惡意程式檔案Hash類別
				HashNameVocab10 _HashNameVocab10 = new HashNameVocab10();
				// 發現之惡意程式檔案Hash類別
				_HashNameVocab10.withValue("SHA256");
				
				// 先組合內層 XML
				// 惡意程式檔名、惡意程式路徑
				FileObjectType _FileObjectType = new FileObjectType();
				_FileObjectType.withFileName(new StringObjectPropertyType()
						// 惡意程式檔名
						.withValue("惡意程式檔名"))
				.withFilePath(new FilePathType()
						// 惡意程式路徑
						.withValue("惡意程式路徑"))
				.withHashes(new HashListType()
						.withHashes(new HashType()
								.withType(_HashNameVocab10)
								.withSimpleHashValue(new SimpleHashValueType()
										// 發現之惡意程式檔案Hash值
										.withValue("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae"))));
				
				// 先組合內層 XML
				// 事件發生原因
				ExploitTarget _ExploitTargetType = new ExploitTarget();
				_ExploitTargetType.withId(new QName("et-4b2ac3e7-c91d-47fb-bcc5-cb7b88e5b8b9"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 事件發生原因
						.withValue(xmlFinish1Reason));
				
				
				// 組合主要 XML
				TTP _TTP = new TTP();
				
				_TTP.withId(new QName("ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 攻擊手法與調查結果說明
						.withValue(xmlEventType))
				.withIntendedEffects(new StatementType()
						.withTimestamp(now)
						.withValue(new ControlledVocabularyStringType()
								// 損害類別
								.withValue("1"))
						.withDescriptions(new StructuredTextType()
								// 損害類別說明
								.withValue("損害類別說明")))
				.withResources(new ResourceType()
						.withInfrastructure(new InfrastructureType()
								.withObservableCharacterization(new Observables()
										.withCyboxMajorVersion("2")
										.withCyboxMinorVersion("1")
										.withCyboxUpdateVersion("0")
										.withObservables(new Observable()
												.withId(new QName("Observable-48c63a01-7943-4746-bcdd-27d6a03ced3b"))
												.withObject(new ObjectType()
														.withId(new QName("File-2d6d6290-f288-41ff-acdf-8e2a93cbdea2"))
														.withProperties(_FileObjectType))))))
				.withExploitTargets(new ExploitTargetsType()
						.withExploitTargets(new RelatedExploitTargetType()
								.withExploitTarget(_ExploitTargetType)));
				
				// 加入事件
				TTPsType _TTPs = new TTPsType().withTTPS(_TTP);
			
				// 加入 stixPackage
				stixPackage.withTTPs(_TTPs);
				
				
				
				// 先組合內層 XML
				// 通報人資料
				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
				// 上面3項屬性寫組合不出來
				CIQIdentity30InstanceType _CIQIdentity30InstanceType = new CIQIdentity30InstanceType();
				_CIQIdentity30InstanceType.withSpecification(new STIXCIQIdentity30Type()
						.withPartyName(new PartyNameType()
								.withPersonNames(new PersonName()
										.withNameElements(new NameElement()
												// 通報人姓名
												.withValue(xmlMemberName))))
						.withContactNumbers(new ContactNumbers()
								.withContactNumbers(new ContactNumber()
										.withContactNumberElements(new ContactNumberElement()
												.withType(ContactNumberElementList.LOCAL_NUMBER)
												// 通報人電話
												.withValue(xmlMemberPhone))))
						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
										// 通報人電子郵件信箱
										.withValue(xmlMemberEmail))));
				
				// 先組合內層 XML

				// 通報單狀態
				// 第 1 組 Observables
				Device _DeviceObjectType1 = new Device();
				_DeviceObjectType1.withModel(new StringObjectPropertyType()
						// 受害設備型號
						.withValue("受害設備型號"));
				
				Product _ProductObjectType1 = new Product();
				_ProductObjectType1.withVendor(new StringObjectPropertyType()
						// 受害設備廠牌
						.withValue("受害設備廠牌"))
				.withDeviceDetails(_DeviceObjectType1);

				// 第 2 組 Observables
				// 先組合內層 XML
				// withDescription 寫不出來
				SystemObjectType _SystemObjectType2 = new SystemObjectType();
				_SystemObjectType2.withOS(new OSType()
						.withPlatform(new PlatformSpecificationType()
								.withDescription(new org.mitre.cybox.common_2.StructuredTextType()
										// 受害設備作業系統/平台
										.withValue(xmlIsOs))));
				
				Product _ProductObjectType2 = new Product();
				_ProductObjectType2.withDeviceDetails(_SystemObjectType2);
				
				// 第 3 組 Observables
				// 先組合內層 XML
				Address _AddressObjectType3 = new Address();
				_AddressObjectType3.withAddressValue(new StringObjectPropertyType()
						// 受害設備IPv4位址
						.withValue(xmlIpv4))
				.withCategory(CategoryTypeEnum.IPV_4_ADDR);
				
				// 第 4 組 Observables
				// 先組合內層 XML
				Address _AddressObjectType4 = new Address();
				_AddressObjectType4.withAddressValue(new StringObjectPropertyType()
						// 受害設備IPv6位址
						.withValue("2001:0DB8:02de:0000:0000:0000:0000:0e13"))
				.withCategory(CategoryTypeEnum.IPV_6_ADDR);

				// 第 5 組 Observables
				// 先組合內層 XML
				URIObjectType _URIObjectType5 = new URIObjectType();
				_URIObjectType5.withValue(new AnyURIObjectPropertyType()
						// 受害設備URL
						.withValue(xmlWebUrl))
				.withType(URITypeEnum.URL);

				// 後續補強措施及成效追蹤機制
				// 先組合內層 XML
				CourseOfAction _CourseOfActionType2 = new CourseOfAction();
				_CourseOfActionType2.withId(new QName("coa-116435e6-2c76-4687-ba30-479c62e6f09d"))
				.withTimestamp(now)
				.withDescriptions(new StructuredTextType()
						// 後續補強措施及成效追蹤機制
						.withValue(xmlIsFinishDo));
				
				CourseOfAction _CourseOfActionType1 = new CourseOfAction();
				_CourseOfActionType1.withId(new QName("coa-56af899b-a33c-4936-bd0c-a2738f8b0424"))
				.withTimestamp(now)
				.withRelatedCOAs(new RelatedCOAsType()
						.withRelatedCOAs(new RelatedCourseOfActionType()
								.withCourseOfAction(_CourseOfActionType2)));

				// 先組合內層 XML
				Incident _Incidentsub = new Incident();
				
				_Incidentsub.withId(new QName("incident-e7cea37a-5257-44f8-bb60-057a27e8a9fb"))
				.withTimestamp(now)
				.withTime(new TimeType()
						// 事發機關結報時間
						.withIncidentClosed(new DateTimeWithPrecisionType(xmlFinishDateTime, DateTimePrecisionEnum.SECOND)))
				.withAffectedAssets(new AffectedAssetsType()
						.withAffectedAssets(new AffectedAssetType()
								.withType(new AssetTypeType()
										// 受害設備數量
										.withCountAffected(xmlTotalAmount)
										// 受害設備類型
										.withValue(xmlTotalAmount))
								.withDescriptions(new StructuredTextType()
										// 受害設備說明
										.withValue("受害設備說明"))
								.withStructuredDescription(new Observables()
										.withCyboxMajorVersion("2")
										.withCyboxMinorVersion("1")
										.withCyboxUpdateVersion("0")
										.withObservables(new Observable()
												.withId(new QName("Observable-80659cc7-ed6b-473e-b517-59eb93944173"))
												.withObject(new ObjectType()
														.withId(new QName("Product-96e48692-292e-4148-a6da-9178586ab4aa"))
														.withProperties(_ProductObjectType1)))
										.withObservables(new Observable()
												.withId(new QName("Observable-234aee20-3b00-4fa9-b8fc-eae3e0bb60e8"))
												.withObject(new ObjectType()
														.withId(new QName("Product-16a472e9-d644-45b6-8199-3e636a907180"))
														.withProperties(_ProductObjectType2)))
										.withObservables(new Observable()
												.withId(new QName("Observable-f1b1c899-a29c-4d1f-b498-3c6d20d334b8"))
												.withObject(new ObjectType()
														.withId(new QName("Address-50f29465-4b45-4ead-a1b3-7aeb0d7780b1"))
														.withProperties(_AddressObjectType3)))
										.withObservables(new Observable()
												.withId(new QName("Observable-cfa7a1dc-d29a-4b71-98de-552589245868"))
												.withObject(new ObjectType()
														.withId(new QName("Address-81dfd394-1369-4ca7-90aa-1376017bc1c5"))
														.withProperties(_AddressObjectType4)))
										.withObservables(new Observable()
												.withId(new QName("Observable-2187f05e-f4b3-4aa1-a6c7-6348091ef75d"))
												.withObject(new ObjectType()
														.withId(new QName("URI-03d6e0eb-b2c2-4941-b06f-cb8af25f8e1b"))
														.withProperties(_URIObjectType5))))))
				.withLeveragedTTPs(new LeveragedTTPsType()
						.withLeveragedTTPs(new RelatedTTPType()
								.withTTP(new TTPBaseType()
										.withIdref(new QName("ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f")))))
				.withCOATakens(new COATakenType()
						.withCourseOfAction(_CourseOfActionType1));
				
				// 組合主要 XML
				Incident _Incident = new Incident();
				
				_Incident.withId(new QName("incident-25006daa-3b54-4c4d-92c7-97ff19d8b128"))
				.withTimestamp(now)
				.withExternalIDs(new ExternalIDType()
						// Ncert通報單ID編號
						.withValue(xmlPostId))
				.withTime(new TimeType()
						// CI領域之中央目的事業主管機關結報審核時間
						.withIncidentClosed(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
				.withReporter(new InformationSourceType()
						.withIdentity(new IdentityType()
								// CI領域之中央目的事業主管機關機關名稱
								.withName(xmlOrgMainUnit1Name)))
				.withResponders(new InformationSourceType()
						.withIdentity(_CIQIdentity30InstanceType))
				.withStatus(new ControlledVocabularyStringType()
						// 通報單狀態
						.withValue("4"))
				.withRelatedIncidents(new RelatedIncidentsType()
						.withRelatedIncidents(new RelatedIncidentType()
								.withIncident(_Incidentsub)));
				
				// 加入事件
				IncidentsType _Incidents = new IncidentsType();
				_Incidents.withIncidents(_Incident);
				
				// 加入 stixPackage
				stixPackage.withIncidents(_Incidents);
				//xmlStr = stixPackage.toXMLString(true);

				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:SystemObj=\"http://cybox.mitre.org/objects#SystemObject-2\"\r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:FileObj=\"http://cybox.mitre.org/objects#FileObject-2\"\r\n" + 
						"	xmlns:ProductObj=\"http://cybox.mitre.org/objects#ProductObject-2\"\r\n" + 
						"	xmlns:URIObj=\"http://cybox.mitre.org/objects#URIObject-2\"\r\n" + 
						"	xmlns:DeviceObj=\"http://cybox.mitre.org/objects#DeviceObject-2\"\r\n" + 
						"	xmlns:AddressObj=\"http://cybox.mitre.org/objects#AddressObject-2\"\r\n" + 
						"	xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\"\r\n" + 
						"	xmlns:ttp=\"http://stix.mitre.org/TTP-1\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:cybox=\"http://cybox.mitre.org/cybox-2\"\r\n" + 
						"	xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\"\r\n" + 
						"	xmlns:et=\"http://stix.mitre.org/ExploitTarget-1\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-a5946bb9-e2a3-4597-8554-b00c5dd33ce6\" version=\"1.2\">\r\n" + 
						"    <stix:TTPs>\r\n" + 
						"        <stix:TTP id=\"example:ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='ttp:TTPType'>\r\n" + 
						"            <ttp:Description>" + xmlEventType + "</ttp:Description>\r\n" + 
						"            <ttp:Intended_Effect timestamp=\"2019-04-12T11:54:53.178748+00:00\">\r\n" + 
						"                <!--損害類別-->\r\n" + 
						"				<stixCommon:Value>1</stixCommon:Value>\r\n" + 
						"                <stixCommon:Description>損害類別說明</stixCommon:Description>\r\n" + 
						"            </ttp:Intended_Effect>\r\n" + 
						"            <ttp:Resources>\r\n" + 
						"                <ttp:Infrastructure>\r\n" + 
						"                    <ttp:Observable_Characterization cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                        <cybox:Observable id=\"example:Observable-48c63a01-7943-4746-bcdd-27d6a03ced3b\">\r\n" + 
						"                            <cybox:Object id=\"example:File-2d6d6290-f288-41ff-acdf-8e2a93cbdea2\">\r\n" + 
						"                                <cybox:Properties xsi:type=\"FileObj:FileObjectType\">\r\n" + 
						"                                    <FileObj:File_Name />\r\n" + 
						"                                    <FileObj:File_Path />\r\n" + 
						"                                    <FileObj:Hashes>\r\n" + 
						"                                        <cyboxCommon:Hash>\r\n" + 
						"                                            <!--發現之惡意程式檔案Hash類別-->\r\n" + 
						"											<cyboxCommon:Type xsi:type=\"cyboxVocabs:HashNameVocab-1.0\" />\r\n" + 
						"                                            <!--發現之惡意程式檔案Hash值-->\r\n" + 
						"											<cyboxCommon:Simple_Hash_Value />\r\n" + 
						"                                        </cyboxCommon:Hash>\r\n" + 
						"                                    </FileObj:Hashes>\r\n" + 
						"                                </cybox:Properties>\r\n" + 
						"                            </cybox:Object>\r\n" + 
						"                        </cybox:Observable>\r\n" + 
						"                    </ttp:Observable_Characterization>\r\n" + 
						"                </ttp:Infrastructure>\r\n" + 
						"            </ttp:Resources>\r\n" + 
						"            <ttp:Exploit_Targets>\r\n" + 
						"                <ttp:Exploit_Target>\r\n" + 
						"                    <stixCommon:Exploit_Target id=\"example:et-4b2ac3e7-c91d-47fb-bcc5-cb7b88e5b8b9\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='et:ExploitTargetType'>\r\n" + 
						"                        <!--事件發生原因-->\r\n" + 
						"						<et:Description>" + xmlFinish1Reason + "</et:Description>\r\n" + 
						"                    </stixCommon:Exploit_Target>\r\n" + 
						"                </ttp:Exploit_Target>\r\n" + 
						"            </ttp:Exploit_Targets>\r\n" + 
						"        </stix:TTP>\r\n" + 
						"    </stix:TTPs>\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-25006daa-3b54-4c4d-92c7-97ff19d8b128\" timestamp=\"2019-04-12T11:54:53.194371+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"            <!--Ncert通報單ID編號-->\r\n" + 
						"			<incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <!--CI領域之中央目的事業主管機關結報審核時間-->\r\n" + 
						"            <incident:Time>\r\n" + 
						"                <incident:Incident_Closed precision=\"second\">" + now + "</incident:Incident_Closed>\r\n" + 
						"            </incident:Time>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"			<!--通報單狀態-->\r\n" + 
						"            <incident:Status>5</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-e7cea37a-5257-44f8-bb60-057a27e8a9fb\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <incident:Time>\r\n" + 
						"                           <!--事發機關結報時間-->\r\n" + 
						"						   <incident:Incident_Closed precision=\"second\">" + xmlFinishDateTime + "</incident:Incident_Closed>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:Affected_Assets>\r\n" + 
						"                            <incident:Affected_Asset>\r\n" + 
						"							<!--受害設備數量與類型-->\r\n" + 
						"                                <incident:Type count_affected=\"" + xmlTotalAmount + "\">1</incident:Type>\r\n" + 
						"                                <incident:Description>受害設備說明</incident:Description>\r\n" + 
						"                                <incident:Structured_Description cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-80659cc7-ed6b-473e-b517-59eb93944173\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-96e48692-292e-4148-a6da-9178586ab4aa\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Vendor>受害設備廠牌</ProductObj:Vendor>\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"DeviceObj:DeviceObjectType\">\r\n" + 
						"                                                    <DeviceObj:Model>受害設備型號</DeviceObj:Model>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-234aee20-3b00-4fa9-b8fc-eae3e0bb60e8\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-16a472e9-d644-45b6-8199-3e636a907180\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"SystemObj:SystemObjectType\">\r\n" + 
						"                                                    <SystemObj:OS>\r\n" + 
						"                                                        <SystemObj:Platform>\r\n" + 
						"                                                            <cyboxCommon:Description>" + xmlIsOs + "</cyboxCommon:Description>\r\n" + 
						"                                                        </SystemObj:Platform>\r\n" + 
						"                                                    </SystemObj:OS>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-f1b1c899-a29c-4d1f-b498-3c6d20d334b8\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-50f29465-4b45-4ead-a1b3-7aeb0d7780b1\">\r\n" + 
						"                                            <!--受害設備IPv4位址-->\r\n" + 
						"											<cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv4-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value>" + xmlIpv4 + "</AddressObj:Address_Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-cfa7a1dc-d29a-4b71-98de-552589245868\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-81dfd394-1369-4ca7-90aa-1376017bc1c5\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv6-addr\">\r\n" + 
						"                                                <!--受害設備IPv6位址-->\r\n" + 
						"												<AddressObj:Address_Value />\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-2187f05e-f4b3-4aa1-a6c7-6348091ef75d\">\r\n" + 
						"                                        <cybox:Object id=\"example:URI-03d6e0eb-b2c2-4941-b06f-cb8af25f8e1b\">\r\n" + 
						"                                            <!--受害設備URL-->\r\n" + 
						"											<cybox:Properties xsi:type=\"URIObj:URIObjectType\" type=\"URL\">\r\n" + 
						"                                                <URIObj:Value>" + xmlWebUrl + "</URIObj:Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                </incident:Structured_Description>\r\n" + 
						"                            </incident:Affected_Asset>\r\n" + 
						"                        </incident:Affected_Assets>\r\n" + 
						"                        <incident:Leveraged_TTPs>\r\n" + 
						"                            <incident:Leveraged_TTP>\r\n" + 
						"                                <stixCommon:TTP idref=\"example:ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f\" xsi:type='ttp:TTPType'/>\r\n" + 
						"                            </incident:Leveraged_TTP>\r\n" + 
						"                        </incident:Leveraged_TTPs>\r\n" + 
						"                        <incident:COA_Taken>\r\n" + 
						"                            <incident:Course_Of_Action id=\"example:coa-116435e6-2c76-4687-ba30-479c62e6f09d\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                <coa:Related_COAs>\r\n" + 
						"                                    <coa:Related_COA>\r\n" + 
						"                                        <stixCommon:Course_Of_Action id=\"example:coa-56af899b-a33c-4936-bd0c-a2738f8b0424\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                            <coa:Description>" + xmlIsFinishDo + "</coa:Description>\r\n" + 
						"                                        </stixCommon:Course_Of_Action>\r\n" + 
						"                                    </coa:Related_COA>\r\n" + 
						"                                </coa:Related_COAs>\r\n" + 
						"                            </incident:Course_Of_Action>\r\n" + 
						"                        </incident:COA_Taken>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";
				
			} 
//			else if (gotoStep == 6) {
//				System.out.println("genStixXML() → Step 6");
//				
//				// 3、4級結報(多個受害設備類型)
//
//				// 先組合內層 XML
//				// 發現之惡意程式檔案Hash類別
//				HashNameVocab10 _HashNameVocab10 = new HashNameVocab10();
//				// 發現之惡意程式檔案Hash類別
//				_HashNameVocab10.withValue("SHA256");
//				
//				// 先組合內層 XML
//				// 惡意程式檔名、惡意程式路徑
//				FileObjectType _FileObjectType = new FileObjectType();
//				_FileObjectType.withFileName(new StringObjectPropertyType()
//						// 惡意程式檔名
//						.withValue("惡意程式檔名"))
//				.withFilePath(new FilePathType()
//						// 惡意程式路徑
//						.withValue("惡意程式路徑"))
//				.withHashes(new HashListType()
//						.withHashes(new HashType()
//								.withType(_HashNameVocab10)
//								.withSimpleHashValue(new SimpleHashValueType()
//										// 發現之惡意程式檔案Hash值
//										.withValue("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae"))));
//				
//				// 先組合內層 XML
//				// 事件發生原因
//				ExploitTarget _ExploitTargetType = new ExploitTarget();
//				_ExploitTargetType.withId(new QName("et-4b2ac3e7-c91d-47fb-bcc5-cb7b88e5b8b9"))
//				.withTimestamp(now)
//				.withDescriptions(new StructuredTextType()
//						// 事件發生原因
//						.withValue(xmlFinish1Reason));
//				
//				
//				// 組合主要 XML
//				TTP _TTP = new TTP();
//				_TTP.withId(new QName("ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f"))
//				.withTimestamp(now)
//				.withDescriptions(new StructuredTextType()
//						// 攻擊手法與調查結果說明
//						.withValue(xmlEventType))
//				.withIntendedEffects(new StatementType()
//						.withTimestamp(now)
//						.withValue(new ControlledVocabularyStringType()
//								// 損害類別
//								.withValue("1"))
//						.withDescriptions(new StructuredTextType()
//								// 損害類別說明
//								.withValue("損害類別說明")))
//				.withResources(new ResourceType()
//						.withInfrastructure(new InfrastructureType()
//								.withObservableCharacterization(new Observables()
//										.withCyboxMajorVersion("2")
//										.withCyboxMinorVersion("1")
//										.withCyboxUpdateVersion("0")
//										.withObservables(new Observable()
//												.withId(new QName("Observable-48c63a01-7943-4746-bcdd-27d6a03ced3b"))
//												.withObject(new ObjectType()
//														.withId(new QName("File-2d6d6290-f288-41ff-acdf-8e2a93cbdea2"))
//														.withProperties(_FileObjectType))))))
//				.withExploitTargets(new ExploitTargetsType()
//						.withExploitTargets(new RelatedExploitTargetType()
//								.withExploitTarget(_ExploitTargetType)));
//				
//				// 加入事件
//				TTPsType _TTPs = new TTPsType().withTTPS(_TTP);
//			
//				// 加入 stixPackage
//				stixPackage.withTTPs(_TTPs);
//				
//				
//				
//				// 先組合內層 XML
//				// 通報人資料
//				// xmlns:stix-ciqidentity="http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1"
//				// xmlns:xpil="urn:oasis:names:tc:ciq:xpil:3"
//				// xmlns:xnl="urn:oasis:names:tc:ciq:xnl:3"
//				// 上面3項屬性寫組合不出來
//				CIQIdentity30InstanceType _CIQIdentity30InstanceType = new CIQIdentity30InstanceType();
//				_CIQIdentity30InstanceType.withSpecification(new STIXCIQIdentity30Type()
//						.withPartyName(new PartyNameType()
//								.withPersonNames(new PersonName()
//										.withNameElements(new NameElement()
//												// 通報人姓名
//												.withValue(xmlMemberName))))
//						.withContactNumbers(new ContactNumbers()
//								.withContactNumbers(new ContactNumber()
//										.withContactNumberElements(new ContactNumberElement()
//												.withType(ContactNumberElementList.LOCAL_NUMBER)
//												// 通報人電話
//												.withValue(xmlMemberPhone))))
//						.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers()
//								.withElectronicAddressIdentifiers(new ElectronicAddressIdentifier()
//										// 通報人電子郵件信箱
//										.withValue(xmlMemberEmail))));
//				
//				// 先組合內層 XML
//
//				// 通報單狀態
//				// 第 1-1 組 Observables
//				Device _DeviceObjectType11 = new Device();
//				_DeviceObjectType11.withModel(new StringObjectPropertyType()
//						// 設備型號
//						.withValue("E6500|PORTEGE"));
//				
//				Product _ProductObjectType11 = new Product();
//				_ProductObjectType11.withVendor(new StringObjectPropertyType()
//						// 設備廠牌
//						.withValue("A廠牌|B廠牌|C廠牌"))
//				.withDeviceDetails(_DeviceObjectType11);
//
//				// 第 1-2 組 Observables
//				// 先組合內層 XML
//				// withDescription 寫不出來
//				SystemObjectType _SystemObjectType12 = new SystemObjectType();
//				_SystemObjectType12.withOS(new OSType()
//						.withPlatform(new PlatformSpecificationType()
//								.withDescription(new org.mitre.cybox.common_2.StructuredTextType()
//										// 受害設備作業系統/平台
//										.withValue(xmlIsOs))));
//				
//				Product _ProductObjectType12 = new Product();
//				_ProductObjectType12.withDeviceDetails(_SystemObjectType12);
//				
//				// 第 1-3 組 Observables
//				// 先組合內層 XML
//				Address _AddressObjectType13 = new Address();
//				_AddressObjectType13.withAddressValue(new StringObjectPropertyType()
//						// 受害設備IPv4位址
//						.withValue(xmlIpv4))
//				.withCategory(CategoryTypeEnum.IPV_4_ADDR);
//				
//				// 第 1-4 組 Observables
//				// 先組合內層 XML
//				Address _AddressObjectType14 = new Address();
//				_AddressObjectType14.withAddressValue(new StringObjectPropertyType()
//						// 受害設備IPv6位址
//						.withValue(""))
//				.withCategory(CategoryTypeEnum.IPV_6_ADDR);
//
//				// 第 1-5 組 Observables
//				// 先組合內層 XML
//				URIObjectType _URIObjectType15 = new URIObjectType();
//				_URIObjectType15.withValue(new AnyURIObjectPropertyType()
//						// 受害設備URL
//						.withValue(xmlWebUrl))
//				.withType(URITypeEnum.URL);
//
//				// 第 2-1 組 Observables
//				Device _DeviceObjectType21 = new Device();
//				_DeviceObjectType21.withModel(new StringObjectPropertyType()
//						// 設備型號
//						.withValue("E6500|PORTEGE"));
//				
//				Product _ProductObjectType21 = new Product();
//				_ProductObjectType21.withVendor(new StringObjectPropertyType()
//						// 設備廠牌
//						.withValue("A廠牌|B廠牌|C廠牌"))
//				.withDeviceDetails(_DeviceObjectType21);
//
//				// 第 2-2 組 Observables
//				// 先組合內層 XML
//				// withDescription 寫不出來
//				SystemObjectType _SystemObjectType22 = new SystemObjectType();
//				_SystemObjectType22.withOS(new OSType()
//						.withPlatform(new PlatformSpecificationType()
//								.withDescription(new org.mitre.cybox.common_2.StructuredTextType()
//										// 受害設備作業系統/平台
//										.withValue(xmlIsOs))));
//				
//				Product _ProductObjectType22 = new Product();
//				_ProductObjectType22.withDeviceDetails(_SystemObjectType22);
//				
//				// 第 2-3 組 Observables
//				// 先組合內層 XML
//				Address _AddressObjectType23 = new Address();
//				_AddressObjectType23.withAddressValue(new StringObjectPropertyType()
//						// 受害設備IPv4位址
//						.withValue(xmlIpv4))
//				.withCategory(CategoryTypeEnum.IPV_4_ADDR);
//				
//				// 第 2-4 組 Observables
//				// 先組合內層 XML
//				Address _AddressObjectType24 = new Address();
//				_AddressObjectType24.withAddressValue(new StringObjectPropertyType()
//						// 受害設備IPv6位址
//						.withValue("2001:0DB8:02de:0000:0000:0000:0000:0e13"))
//				.withCategory(CategoryTypeEnum.IPV_6_ADDR);
//
//				// 第 2-5 組 Observables
//				// 先組合內層 XML
//				URIObjectType _URIObjectType25 = new URIObjectType();
//				_URIObjectType25.withValue(new AnyURIObjectPropertyType()
//						// 受害設備URL
//						.withValue(xmlWebUrl))
//				.withType(URITypeEnum.URL);
//
//				// 後續補強措施及成效追蹤機制
//				// 先組合內層 XML
//				CourseOfAction _CourseOfActionType2 = new CourseOfAction();
//				_CourseOfActionType2.withId(new QName("coa-0e4a0cdb-b621-418c-a1ee-c246ba31173f"))
//				.withTimestamp(now)
//				.withDescriptions(new StructuredTextType()
//						// 後續補強措施及成效追蹤機制
//						.withValue(xmlIsFinishDo));
//				
//				CourseOfAction _CourseOfActionType1 = new CourseOfAction();
//				_CourseOfActionType1.withId(new QName("coa-14dea24c-a763-45ed-820b-c8a077cb8718"))
//				.withTimestamp(now)
//				.withRelatedCOAs(new RelatedCOAsType()
//						.withRelatedCOAs(new RelatedCourseOfActionType()
//								.withCourseOfAction(_CourseOfActionType2)));
//
//				// 先組合內層 XML
//				Incident _Incidentsub = new Incident();
//				_Incidentsub.withId(new QName("incident-937deb0d-a3e6-453f-919b-d5ce4f5c33e3"))
//				.withTimestamp(now)
//				.withTime(new TimeType()
//						// 事發機關結報時間
//						.withIncidentClosed(new DateTimeWithPrecisionType(xmlFinishDateTime, DateTimePrecisionEnum.SECOND)))
//				.withAffectedAssets(new AffectedAssetsType()
//						.withAffectedAssets(new AffectedAssetType()
//								.withType(new AssetTypeType()
//										// 受害設備數量
//										.withCountAffected(xmlTotalAmount)
//										// 受害設備類型
//										.withValue(xmlTotalAmount))
//								.withDescriptions(new StructuredTextType()
//										// 受害設備說明
//										.withValue("受害設備說明"))
//								.withStructuredDescription(new Observables()
//										.withCyboxMajorVersion("2")
//										.withCyboxMinorVersion("1")
//										.withCyboxUpdateVersion("0")
//										.withObservables(new Observable()
//												.withId(new QName("Observable-f4bc95ab-3797-4d6d-8a1e-ffc76bf7bacb"))
//												.withObject(new ObjectType()
//														.withId(new QName("Product-794878d6-8486-45ed-b6f2-594d9fa7c1fd"))
//														.withProperties(_ProductObjectType11)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-a7f05423-7b11-4a94-b827-576ac4835f55"))
//												.withObject(new ObjectType()
//														.withId(new QName("Product-20166f45-3634-45e6-ab7b-b8ee5205cfa7"))
//														.withProperties(_ProductObjectType12)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-5ee8be76-15c8-4e69-8b1b-f1f42b796530"))
//												.withObject(new ObjectType()
//														.withId(new QName("Address-06dca102-c277-49ab-9e4f-7dcae0149889"))
//														.withProperties(_AddressObjectType13)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-034ea800-a8ff-400a-a28f-e0b1dcf2f57a"))
//												.withObject(new ObjectType()
//														.withId(new QName("Address-de876ddb-4246-4208-a7ca-6d8b7bd93c9b"))
//														.withProperties(_AddressObjectType14)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-7f6d9b96-597b-4c52-93cc-2267b56d9cfb"))
//												.withObject(new ObjectType()
//														.withId(new QName("URI-92a19dbf-5e38-4f13-8d4a-0f6d8fb69fb6"))
//														.withProperties(_URIObjectType15)))))
//						.withAffectedAssets(new AffectedAssetType()
//								.withType(new AssetTypeType()
//										// 受害設備數量
//										.withCountAffected(xmlTotalAmount)                                                         
//										// 受害設備類型
//										.withValue(xmlTotalAmount))
//								.withDescriptions(new StructuredTextType()
//										// 受害設備說明
//										.withValue("受害設備說明"))
//								.withStructuredDescription(new Observables()
//										.withCyboxMajorVersion("2")
//										.withCyboxMinorVersion("1")
//										.withCyboxUpdateVersion("0")                                                      
//										.withObservables(new Observable()
//												.withId(new QName("Observable-6f65b398-d5f3-4cfd-857f-a8bf91e58fc7"))     
//												.withObject(new ObjectType()                                              
//														.withId(new QName("Product-b3913ee1-aa01-4869-9be8-a3ab9b918038"))
//														.withProperties(_ProductObjectType21)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-43f12f56-a571-4904-8097-02b4a26f122a"))     
//												.withObject(new ObjectType()                                              
//														.withId(new QName("Product-8fe158d5-72cd-4b4c-91c9-836f3c288895"))
//														.withProperties(_ProductObjectType22)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-a02c864f-2691-46bd-b90e-544ebf5b5459"))     
//												.withObject(new ObjectType()                                              
//														.withId(new QName("Address-bf4575f0-e836-425f-a3fa-274090805cd4"))
//														.withProperties(_AddressObjectType23)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-9fcb1996-d2c4-4f81-830c-149141e4deaf"))     
//												.withObject(new ObjectType()                                              
//														.withId(new QName("Address-0a4a79aa-1289-4b7b-9c8f-dfabd5166db3"))
//														.withProperties(_AddressObjectType24)))
//										.withObservables(new Observable()
//												.withId(new QName("Observable-03296ad6-de55-45d5-a3b5-6b7dcf2a82f3"))
//												.withObject(new ObjectType()
//														.withId(new QName("URI-b9380ef7-0a0b-4393-836e-263c5898433b"))
//														.withProperties(_URIObjectType25))))))
//				.withLeveragedTTPs(new LeveragedTTPsType()
//						.withLeveragedTTPs(new RelatedTTPType()
//								.withTTP(new TTPBaseType()
//										.withIdref(new QName("ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f")))))
//				.withCOATakens(new COATakenType()
//						.withCourseOfAction(_CourseOfActionType1));
//				
//				// 組合主要 XML
//				Incident _Incident = new Incident();
//				_Incident.withId(new QName("incident-25006daa-3b54-4c4d-92c7-97ff19d8b128"))
//				.withTimestamp(now)
//				.withExternalIDs(new ExternalIDType()
//						// Ncert通報單ID編號
//						.withValue(xmlPostId))
//				.withTime(new TimeType()
//						// CI領域之中央目的事業主管機關結報審核時間
//						.withIncidentClosed(new DateTimeWithPrecisionType(now, DateTimePrecisionEnum.SECOND)))
//				.withReporter(new InformationSourceType()
//						.withIdentity(new IdentityType()
//								// CI領域之中央目的事業主管機關機關名稱
//								.withName(xmlOrgMainUnit1Name)))
//				.withResponders(new InformationSourceType()
//						.withIdentity(_CIQIdentity30InstanceType))
//				.withStatus(new ControlledVocabularyStringType()
//						// 通報單狀態
//						.withValue("5"))
//				.withRelatedIncidents(new RelatedIncidentsType()
//						.withRelatedIncidents(new RelatedIncidentType()
//								.withIncident(_Incidentsub)));
//				
//				// 加入事件
//				IncidentsType _Incidents = new IncidentsType();
//				_Incidents.withIncidents(_Incident);
//				
//				// 加入 stixPackage
//				stixPackage.withIncidents(_Incidents);
//				xmlStr = stixPackage.toXMLString(true);
//				
//			}

			//xmlStr = stixPackage.toXMLString(true);
			//System.out.println(xmlStr);
//			System.out.println(NcertServices.removeXmlDeclaration(xmlStr));
			// 結束產生STIX XML
			if (stixPackage.validate()) {
				//xmlStr = stixPackage.toXMLString(true);
				responseJson.put("msg", "產生 STIX XML 成功");
				responseJson.put("id", notification.getId());
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			} else {
				responseJson.put("msg", "產生 STIX XML 失敗");
				responseJson.put("id", notification.getId());
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
		} catch (Exception e) {
			//System.out.println(e.toString());
			responseJson.put("msg", "產生 STIX XML 失敗, 原因(" + e.getMessage() + ")");
			responseJson.put("id", notification.getId());
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}
		//System.out.println("genStixXML() → responseJson=" + responseJson);
		return xmlStr;
	}
	
	/**
	 * 組合 STIX XML 通報 (初報)
	 * 
	 * @param id
	 *            通報單編號
	 * @return STIX XML
	 */
	public String genStixXMLStep1(Notification notification, String opinion) {

		//System.out.println("genStixXMLStep1()");
		String xmlStr = "";
		JSONObject responseJson = new JSONObject();

		try {

			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("UTC")));

			String xmlOpinion = opinion; // 審查結果說明
			if (xmlOpinion == null || xmlOpinion.isEmpty()) {
				xmlOpinion = "無說明";
			}
			
			String xmlEffectLevel = notification.getEffectLevel().toString(); // 資安事件影響等級
			long contactorId = notification.getContactorId(); // Member Account

			// AIC影響衝擊評估
			String xmlAIC = "";
			String aeffectLevel = notification.getAeffectLevel().toString(); // 可用性
			String ieffectLevel = notification.getIeffectLevel().toString(); // 完整性
			String ceffectLevel = notification.getCeffectLevel().toString(); // 機密性
			xmlAIC = aeffectLevel + ieffectLevel + ceffectLevel;  

			// 通報來源
			String xmlEventSource = "";
			String eventSource = notification.getEventSource().toString();
			String eventSourceNo = notification.getEventSourceNo();
			String eventSourceOther = notification.getEventSourceOther();
			switch (eventSource) {
				case "1":
					xmlEventSource = eventSource;
					break;
				case "2":
					xmlEventSource = eventSourceNo;
					break;
				case "3":
					xmlEventSource = eventSourceOther;
					break;
			}

			GregorianCalendar tempEventDateTime = new GregorianCalendar();
			tempEventDateTime.setTime(notification.getEventDateTime());
			XMLGregorianCalendar xmlEventDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempEventDateTime); // 事件發生時間
			
			GregorianCalendar tempApplyDateTime = new GregorianCalendar();
			tempApplyDateTime.setTime(notification.getEventDateTime());
			XMLGregorianCalendar xmlApplyDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempApplyDateTime); // 發布時間

			String xmlMaintainCompany = ""; // 資安維護廠商名稱
			if (notification.getMaintainCompany() != "") {
				xmlMaintainCompany = notification.getMaintainCompany();
			}
			if (xmlMaintainCompany == null || xmlMaintainCompany.isEmpty()) {
				xmlMaintainCompany = "無資料";
			}
			
			long contactorUnit = notification.getContactorUnit(); // 機關(機構)名稱
			long mainUnit1 = notification.getMainUnit1(); // 權責機關(上級機關)
			
			String xmlEventRemark = "";
			if (notification.getEventRemark() != "") {
				xmlEventRemark = notification.getEventRemark(); // 事件說明及影響範圍
			}
			
			String xmlIsAffectOthers = "3"; // 是否影響其他政府機關(構)或重要民生設施運作 1: 無影響; 2: 不確定; 3: 有影響
			if (!notification.getIsAffectOthers()) {
				xmlIsAffectOthers = "1";
			}

			// 攻擊手法與調查結果說明
			String xmlEventType = ""; // 事件分類與異常狀況
			String xmlEventTypeClass = ""; // 事件類型
			String tmpXmlEventType = "";
			Integer eventType = notification.getEventType(); // 事件分類與異常狀況
			String eventType5Other = notification.getEventType5Other(); // 事件分類與異常狀況(其他異常原因)
			
			switch (eventType) {
				case 1:
					xmlEventTypeClass = eventType.toString(); 
					xmlEventType = "網頁攻擊：";
					Boolean isEventType1Opt1 = notification.getIsEventType1Opt1(); // 網頁置換
					Boolean isEventType1Opt2 = notification.getIsEventType1Opt2(); // 惡意留言
					Boolean isEventType1Opt3 = notification.getIsEventType1Opt3(); // 惡意網頁
					Boolean isEventType1Opt4 = notification.getIsEventType1Opt4(); // 釣魚網頁
					Boolean isEventType1Opt5 = notification.getIsEventType1Opt5(); // 網頁木馬
					Boolean isEventType1Opt6 = notification.getIsEventType1Opt6(); // 網站個資外洩
					if (isEventType1Opt1) {
						tmpXmlEventType += "網頁置換";
					}
					if (isEventType1Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意留言";
						} else {
							tmpXmlEventType += "惡意留言";
						}						
					}
					if (isEventType1Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意網頁";
						} else {
							tmpXmlEventType += "惡意網頁";
						}						
					}
					if (isEventType1Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "釣魚網頁";
						} else {
							tmpXmlEventType += "釣魚網頁";
						}						
					}
					if (isEventType1Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網頁木馬";
						} else {
							tmpXmlEventType += "網頁木馬";
						}						
					}
					if (isEventType1Opt6) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網站個資外洩";
						} else {
							tmpXmlEventType += "網站個資外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 2:
					xmlEventType = "非法入侵：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType2Opt1 = notification.getIsEventType2Opt1(); // 系統遭入侵
					Boolean isEventType2Opt2 = notification.getIsEventType2Opt2(); // 植入惡意程式
					Boolean isEventType2Opt3 = notification.getIsEventType2Opt3(); // 異常連線
					Boolean isEventType2Opt4 = notification.getIsEventType2Opt4(); // 發送垃圾郵件
					Boolean isEventType2Opt5 = notification.getIsEventType2Opt5(); // 資料外洩
					if (isEventType2Opt1) {
						tmpXmlEventType += "系統遭入侵";
					}
					if (isEventType2Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "植入惡意程式";
						} else {
							tmpXmlEventType += "植入惡意程式";
						}						
					}
					if (isEventType2Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "異常連線";
						} else {
							tmpXmlEventType += "異常連線";
						}						
					}
					if (isEventType2Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "發送垃圾郵件";
						} else {
							tmpXmlEventType += "發送垃圾郵件";
						}						
					}
					if (isEventType2Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "資料外洩";
						} else {
							tmpXmlEventType += "資料外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 3:
					xmlEventType = "阻斷服務(DoS/DDoS)：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType3Opt1 = notification.getIsEventType3Opt1(); // 服務中斷
					Boolean isEventType3Opt2 = notification.getIsEventType3Opt2(); // 效能降低
					if (isEventType3Opt1) {
						tmpXmlEventType += "服務中斷";
					}
					if (isEventType3Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "效能降低";
						} else {
							tmpXmlEventType += "效能降低";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 4:
					xmlEventType = "設備異常：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType4Opt1 = notification.getIsEventType4Opt1(); // 設備毀損
					Boolean isEventType4Opt2 = notification.getIsEventType4Opt2(); // 電力異常
					Boolean isEventType4Opt3 = notification.getIsEventType4Opt3(); // 網路服務中斷
					Boolean isEventType4Opt4 = notification.getIsEventType4Opt4(); // 設備遺失
					if (isEventType4Opt1) {
						tmpXmlEventType += "設備毀損";
					}
					if (isEventType4Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "電力異常";
						} else {
							tmpXmlEventType += "電力異常";
						}						
					}
					if (isEventType4Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網路服務中斷";
						} else {
							tmpXmlEventType += "網路服務中斷";
						}						
					}
					if (isEventType4Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "設備遺失";
						} else {
							tmpXmlEventType += "設備遺失";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 5:
					xmlEventType = "其他異常原因：";
					xmlEventTypeClass = eventType5Other;
					if (eventType5Other != "") {
						xmlEventType += eventType5Other;
					}
					break;
			}

			String xmlAffectOther = ""; // 影響機關(構)/重要民生設施領域名稱
			Boolean affectOther1 = notification.getAffectOther1(); // 影響機關(構)/重要民生設施領域名稱(水資源)
			Boolean affectOther2 = notification.getAffectOther2(); // 影響機關(構)/重要民生設施領域名稱(能源)
			Boolean affectOther3 = notification.getAffectOther3(); // 影響機關(構)/重要民生設施領域名稱(通訊傳播)
			Boolean affectOther4 = notification.getAffectOther4(); // 影響機關(構)/重要民生設施領域名稱(交通)
			Boolean affectOther5 = notification.getAffectOther5(); // 影響機關(構)/重要民生設施領域名稱(銀行與金融)
			Boolean affectOther6 = notification.getAffectOther6(); // 影響機關(構)/重要民生設施領域名稱(緊急救援與醫院)
			Boolean affectOther7 = notification.getAffectOther7(); // 影響機關(構)/重要民生設施領域名稱(重要政府機關)
			Boolean affectOther8 = notification.getAffectOther8(); // 影響機關(構)/重要民生設施領域名稱(高科技園區)
			if (affectOther1) {
				xmlAffectOther = "1"; // 水資源
			}
			if (affectOther2) {
			    xmlAffectOther += "2"; // 能源
			}
			if (affectOther3) {
			    xmlAffectOther += "3"; // 通訊傳播
			}
			if (affectOther4) {
			    xmlAffectOther += "4"; // 交通
			}
			if (affectOther5) {
			    xmlAffectOther += "5"; // 銀行與金融
			}
			if (affectOther6) {
			    xmlAffectOther += "6"; // 緊急救援與醫院
			}
			if (affectOther7) {
			    xmlAffectOther += "7"; // 重要政府機關
			}
			if (affectOther8) {
			    xmlAffectOther += "8"; // 高科技園區
			}
			if (xmlAffectOther == null || xmlAffectOther.isEmpty()) {
				xmlAffectOther = "0"; // 無資料
			}
						
			String xmlIsNeedSupport = "1"; // 是否需要支援 1: 否; 2: 是
			if (notification.getIsNeedSupport()) {
				xmlIsNeedSupport = "2";
			}
			
			String xmlNeedSupportRemark = ""; // 期望支援內容
			if (notification.getNeedSupportRemark() != "") {
				xmlNeedSupportRemark = notification.getNeedSupportRemark();
			}
			if (xmlNeedSupportRemark == null || xmlNeedSupportRemark.isEmpty()) {
				xmlNeedSupportRemark = "無資料";
			}
			
			// 取得 通報人 所需資料
			String xmlMemberName = ""; // Member Name
			String xmlMemberPhone = ""; // Member Phone
			String xmlMemberEmail = ""; // Member Email
			
			if (contactorId > 0) {
				Member member = memberService.get(contactorId);

				xmlMemberName = member.getName(); // Member Name
				xmlMemberPhone = notification.getContactorTel();// Member Phone
				xmlMemberEmail = notification.getContactorEmail(); // Member Email
			}
			if (xmlMemberName == null || xmlMemberName.isEmpty()) {
				xmlMemberName = "無資料";
			}
			if (xmlMemberPhone == null || xmlMemberPhone.isEmpty()) {
				xmlMemberPhone = "99999999";
			}
			if (xmlMemberEmail == null || xmlMemberEmail.isEmpty()) {
				xmlMemberEmail = "無資料";
			}

			// 取得 機關(機構)名稱 所需資料
			String xmlOrgContactorUnitName = "無資料";
			
			if (contactorUnit > 0) {
				Org org1 = orgService.getDataById(contactorUnit);
				xmlOrgContactorUnitName = org1.getName();				
			}

			// 取得 main unit 1 (權責機關-上級機關) 所需資料
			String xmlOrgMainUnit1Name = "";
			
			if (mainUnit1 > 0) {
				Org org2 = orgService.getDataById(mainUnit1);
				xmlOrgMainUnit1Name = org2.getName();
			}else {
				Member memberInfo = memberService.get(notification.getContactorId());
				ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(memberInfo.getOrgId());
				xmlOrgMainUnit1Name = superviseParentOrg.getName();
			}
			
			if (xmlOrgMainUnit1Name == null || xmlOrgMainUnit1Name.isEmpty()) {
				xmlOrgMainUnit1Name = "查無CI領域之中央目的事業主管機關機關名稱";
			}
			

			//System.out.println("genStixXMLStep1() → Step 1");
			
			xmlStr = "<stix:STIX_Package xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\" \r\n" + 
			"    xmlns:incident=\"http://stix.mitre.org/Incident-1\" \r\n" + 
			"    xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" \r\n" + 
			"    xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\" \r\n" + 
			"    xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\" \r\n" + 
			"    xmlns:stixCommon=\"http://stix.mitre.org/common-1\" \r\n" + 
			"    xmlns:stix=\"http://stix.mitre.org/stix-1\" \r\n" + 
			"    xmlns:example=\"http://example.com\" \r\n" + 
			"    xmlns:xlink=\"http://www.w3.org/1999/xlink\" \r\n" + 
			"    xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" \r\n" + 
			"    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" \r\n" + 
			"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"example:Package-bdb4d88f-0943-4e37-a2f9-34024e8e2de4\" version=\"1.2\">\r\n" + 
			"    <stix:Incidents>\r\n" + 
			"        <stix:Incident id=\"example:incident-6a364056-b4ea-4cb7-8b7e-44d6919181c4\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
			"            <incident:Time>\r\n" + 
			"                <!--CI領域中央目的事業主管機關通報審核時間-->\r\n" + 
			"                <incident:Incident_Opened precision=\"second\">" + now + "</incident:Incident_Opened>\r\n" + 
			"            </incident:Time>\r\n" + 
			"            <incident:Description>" + xmlOpinion + "</incident:Description>\r\n" + 
			"            <incident:Reporter>\r\n" + 
			"                <stixCommon:Identity>\r\n" + 
			"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
			"                </stixCommon:Identity>\r\n" + 
			"            </incident:Reporter>\r\n" + 
			"            <incident:Responder>\r\n" + 
			"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
			"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
			"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
			"                            <!--CI領域中央目的事業主管機關通報人姓名-->\r\n" + 
			"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
			"                                <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
			"                            </xnl:PersonName>\r\n" + 
			"                        </xpil:PartyName>\r\n" + 
			"                        <!--CI領域中央目的事業主管機關通報人連絡電話-->\r\n" + 
			"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
			"                            <xpil:ContactNumber>\r\n" + 
			"                                <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
			"                            </xpil:ContactNumber>\r\n" + 
			"                        </xpil:ContactNumbers>\r\n" + 
			"                        <!--CI領域中央目的事業主管機關通報人電子郵件-->\r\n" + 
			"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
			"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
			"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
			"                    </stix-ciqidentity:Specification>\r\n" + 
			"                </stixCommon:Identity>\r\n" + 
			"            </incident:Responder>\r\n" + 
			"            <!--通報單狀態初報為1-->\r\n" + 
			"            <incident:Status>1</incident:Status>\r\n" + 
			"            <incident:Related_Incidents>\r\n" + 
			"                <incident:Related_Incident>\r\n" + 
			"                    <stixCommon:Incident id=\"example:incident-d1e0711f-d69d-4cac-b242-74cdbc0baf48\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
			"                        <incident:Time>\r\n" + 
			"                            <!--事發機關發現知悉資安事件時間-->\r\n" + 
			"                            <incident:Incident_Discovery precision=\"second\">" + xmlEventDateTime + "</incident:Incident_Discovery>\r\n" + 
			"                            <!--事發機關至CI領域中央目的事業主管機關通報資安事件時間-->\r\n" + 
			"                            <incident:Incident_Reported precision=\"second\">" + xmlApplyDateTime + "</incident:Incident_Reported>\r\n" + 
			"                        </incident:Time>\r\n" + 
			"                        <incident:Description>" + xmlEventRemark + "</incident:Description>\r\n" + 
			"                        <!--AIC影響衝擊評估-->\r\n" + 
			"                        <incident:Short_Description>" + xmlAIC + "</incident:Short_Description>\r\n" + 
			"                        <!--事件類型-->\r\n" + 
			"                        <incident:Categories>\r\n" + 
			"                            <incident:Category vocab_name=\"" + xmlEventTypeClass + "\"/>\r\n" + 
			"                        </incident:Categories>\r\n" + 
			"                        <incident:Responder>\r\n" + 
			"                            <!--資安廠商-->\r\n" + 
			"                            <stixCommon:Description>" + xmlMaintainCompany + "</stixCommon:Description>\r\n" + 
			"                            <!--事發機關是否為關鍵基礎設施提供者編號1代表不是編號2代表是-->\r\n" + 
			"                            <stixCommon:Role>2</stixCommon:Role>\r\n" + 
			"                        </incident:Responder>\r\n" + 
			"                        <incident:Victim xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
			"                            <stixCommon:Name>" + xmlOrgContactorUnitName + "</stixCommon:Name>\r\n" + 
			"                            <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
			"                                <xpil:OrganisationInfo xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" xpil:IndustryType=\"無\"/>\r\n" + 
			"                            </stix-ciqidentity:Specification>\r\n" + 
			"                        </incident:Victim>\r\n" + 
			"                        <incident:Impact_Assessment>\r\n" + 
			"                            <incident:Impact_Qualification vocab_name=\"資安事件綜合評估等級\">" + xmlEffectLevel + "</incident:Impact_Qualification>\r\n" + 
			"                        </incident:Impact_Assessment>\r\n" + 
			"                        <incident:Intended_Effect timestamp=\"2019-04-12T11:00:41.665815+00:00\">\r\n" + 
			"                            <!--資安事件是否造成外部跨領域影響編號1代表無影響編號2代表尚無法確認編號3代表有影響-->\r\n" + 
			"                            <stixCommon:Value>" + xmlIsAffectOthers + "</stixCommon:Value>\r\n" + 
			"                            <stixCommon:Description>資安事件跨領域影響說明</stixCommon:Description>\r\n" + 
			"                            <stixCommon:Confidence timestamp=\"2019-04-12T11:00:41.665815+00:00\">\r\n" + 
			"                                <!--跨領域影響之領域別-->\r\n" + 
			"                                <stixCommon:Description>" + xmlAffectOther + "</stixCommon:Description>\r\n" + 
			"                            </stixCommon:Confidence>\r\n" + 
			"                        </incident:Intended_Effect>\r\n" + 
			"                        <!--通報來源-->\r\n" + 
			"                        <incident:Discovery_Method>" + xmlEventSource + "</incident:Discovery_Method>\r\n" + 
			"                    </stixCommon:Incident>\r\n" + 
			"                </incident:Related_Incident>\r\n" + 
			"            </incident:Related_Incidents>\r\n" + 
			"            <incident:COA_Requested>\r\n" + 
			"                <incident:Course_Of_Action id=\"example:coa-b576ad95-9af4-44f7-ac99-3ef627542ab0\" timestamp=\"2019-04-12T11:00:41.665815+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
			"                    <coa:Description>" + xmlNeedSupportRemark + "</coa:Description>\r\n" + 
			"                    <!--CI領域之中央目的事業主管機關是否申請外部支援-->\r\n" + 
			"                    <coa:Short_Description>" + xmlIsNeedSupport + "</coa:Short_Description>\r\n" + 
			"                </incident:Course_Of_Action>\r\n" + 
			"            </incident:COA_Requested>\r\n" + 
			"        </stix:Incident>\r\n" + 
			"    </stix:Incidents>\r\n" + 
			"</stix:STIX_Package>";
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.toString());
			responseJson.put("msg", "產生 STIX XML 失敗, 原因(" + e.getMessage() + ")");
			responseJson.put("id", notification.getId());
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "genStixXMLStep1", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}
		System.out.println("XMLSTEP1" + xmlStr);
//
//		
//		System.out.println("genStixXMLStep1() → responseJson=" + responseJson);
		return xmlStr;
	}

	/**
	 * 組合 STIX XML 通報 (續報)
	 * 
	 * @param id
	 *            通報單編號
	 * @return STIX XML
	 */
	public String genStixXMLStep3(Notification notification, int curStep) {
		//System.out.println("genStixXMLStep3()");
		String xmlStr = "";
		JSONObject responseJson = new JSONObject();
		
		try {
			// 開始產生STIX XML
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("UTC")));

			String xmlCertId = String.valueOf(notification.getCertId()); // CertId
			long contactorId = notification.getContactorId(); // Member Account
			long mainUnit1 = notification.getMainUnit1(); // 權責機關(上級機關)
			
			// 應變處置說明
			String xmlResDescription = notification.getResDescription();
			if (xmlResDescription == null || xmlResDescription.isEmpty()) {
				xmlResDescription = "無資料";
			}
			
			// 事發機關完成損害控制或復原時間
			String xmlResControlTime = WebDatetime.toString(notification.getResControlTime(), "yyyy-MM-dd") + "T00:00:00";
			
			// 損害控制或復原之執行狀況(資安事件是否完成損害控制或復原)
			// curStep = 6 為已結案
			int resResult = notification.getResResult();
			String xmlResResult = "1";
			if (curStep == 6) {
				switch (resResult) {
					case 1:
						xmlResResult = "2";
						break;
					case 2:
						xmlResResult = "3";
						break;
				}
			}
			
			// 取得 通報人 所需資料
			String xmlMemberName = ""; // Member Name
			String xmlMemberPhone = ""; // Member Phone
			String xmlMemberEmail = ""; // Member Email
			
			if (contactorId > 0) {
				Member member = memberService.get(contactorId);

				xmlMemberName = member.getName(); // Member Name
				xmlMemberPhone = notification.getContactorTel();// Member Phone
				xmlMemberEmail = notification.getContactorEmail(); // Member Email
			}
			if (xmlMemberName == null || xmlMemberName.isEmpty()) {
				xmlMemberName = "無資料";
			}
			if (xmlMemberPhone == null || xmlMemberPhone.isEmpty()) {
				xmlMemberPhone = "99999999";
			}
			if (xmlMemberEmail == null || xmlMemberEmail.isEmpty()) {
				xmlMemberEmail = "無資料";
			}

			// 取得 main unit 1 (權責機關-上級機關) 所需資料
			String xmlOrgMainUnit1Name = "無資料";
			
			if (mainUnit1 > 0) {
				Org org2 = orgService.getDataById(mainUnit1);
				xmlOrgMainUnit1Name = org2.getName();
			}else {
				Member memberInfo = memberService.get(notification.getContactorId());
				ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(memberInfo.getOrgId());
				xmlOrgMainUnit1Name = superviseParentOrg.getName();
			}

			//System.out.println("genStixXMLStep3() → Step 3");
			
			xmlStr = "<stix:STIX_Package \r\n" + 
					"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
					"	xmlns:stixVocabs=\"http://stix.mitre.org/default_vocabularies-1\"\r\n" + 
					"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
					"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
					"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
					"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
					"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
					"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
					"	xmlns:example=\"http://example.com\"\r\n" + 
					"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
					"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
					"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
					"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
					"	 id=\""+notification.getPostId() +"\" version=\"1.2\">\r\n" + 
					"    <stix:Incidents>\r\n" + 
					"        <stix:Incident id=\"" + notification.getPostId() + "\" timestamp=\""+ WebDatetime.toString(notification.getModifyTime(), "yyyy-MM-dd") +"T"+WebDatetime.toString(notification.getModifyTime(), "HH:mm:ss")+".000000+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
					"            <!--Ncert通報單ID編號-->\r\n" + 
					"            <incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
					"            <incident:Reporter>\r\n" + 
					"                <stixCommon:Identity>\r\n" + 
					"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
					"                </stixCommon:Identity>\r\n" + 
					"            </incident:Reporter>\r\n" + 
					"            <incident:Responder>\r\n" + 
					"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
					"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
					"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
					"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
					"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
					"                            </xnl:PersonName>\r\n" + 
					"                        </xpil:PartyName>\r\n" + 
					"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
					"                            <xpil:ContactNumber>\r\n" + 
					"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
					"                            </xpil:ContactNumber>\r\n" + 
					"                        </xpil:ContactNumbers>\r\n" + 
					"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
					"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
					"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
					"                    </stix-ciqidentity:Specification>\r\n" + 
					"                </stixCommon:Identity>\r\n" + 
					"            </incident:Responder>\r\n" + 
					"            <!--通報單狀態-->\r\n" + 
					"            <incident:Status>3</incident:Status>\r\n" + 
					"            <incident:Related_Incidents>\r\n" + 
					"                <incident:Related_Incident>\r\n" + 
					"                    <stixCommon:Incident id=\"" + notification.getPostId() + "\" timestamp=\""+ WebDatetime.toString(notification.getModifyTime(), "yyyy-MM-dd") +"T"+WebDatetime.toString(notification.getModifyTime(), "HH:mm:ss")+".000000+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
					"                        <!--事發機關完成損害控制或復原辦理通知時間-->\r\n" + 
					"                        <incident:Time>\r\n" + 
					"                            <incident:Containment_Achieved precision=\"second\">" + now + "</incident:Containment_Achieved>\r\n" + 
					"                        </incident:Time>\r\n" + 
					"                        <incident:COA_Taken>\r\n" + 
					"                            <incident:Time>\r\n" + 
					"							<!--事發機關完成損害控制或復原時間-->\r\n" + 
					"                                <incident:End precision=\"second\">" + xmlResControlTime + "</incident:End>\r\n" + 
					"                            </incident:Time>\r\n" + 
					"                            <incident:Course_Of_Action id=\"" + notification.getPostId() + "\" timestamp=\""+ WebDatetime.toString(notification.getModifyTime(), "yyyy-MM-dd") +"T"+WebDatetime.toString(notification.getModifyTime(), "HH:mm:ss")+".000000+00:00\"  xsi:type='coa:CourseOfActionType'>\r\n" + 
					"                                <!--資安事件是否完成損害控制或復原-->\r\n" + 
					"                                <coa:Stage xsi:type=\"stixVocabs:COAStageVocab-1.0\" vocab_name=\"" + xmlResResult + "\"/>\r\n" + 
					"                                <!--資安事件應變處置說明-->\r\n" + 
					"                                <coa:Description>" + xmlResDescription + "</coa:Description>\r\n" + 
					"                            </incident:Course_Of_Action>\r\n" + 
					"                        </incident:COA_Taken>\r\n" + 
					"                    </stixCommon:Incident>\r\n" + 
					"                </incident:Related_Incident>\r\n" + 
					"            </incident:Related_Incidents>\r\n" + 
					"        </stix:Incident>\r\n" + 
					"    </stix:Incidents>\r\n" + 
					"</stix:STIX_Package>\r\n" + 
					"";
			

			
		} catch (Exception e) {
			//System.out.println(e.toString());
			responseJson.put("msg", "產生 STIX XML 失敗, 原因(" + e.getMessage() + ")");
			responseJson.put("id", notification.getId());
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "genStixXMLStep3", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}
		System.out.println("XMLSTEP3" + xmlStr);

		//System.out.println("genStixXMLStep3() → responseJson=" + responseJson);
		return xmlStr;
	}


	/**
	 * 組合 STIX XML 通報 (1、2級結報 或 3、4級結報)
	 * 
	 * @param id
	 *            通報單編號
	 * @return STIX XML
	 */
	public String genStixXMLStep4And5(Notification notification) {
		//System.out.println("genStixXMLStep4And5()");
		String xmlStr = "";
		JSONObject responseJson = new JSONObject();
		
		try {
			// 開始產生STIX XML
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("UTC")));

			String xmlCertId = String.valueOf(notification.getCertId()); // CertId
			int hostAmount = notification.getHostAmount(); // 受害資訊設備數量 host 總計
			int serverAmount = notification.getServerAmount(); // 受害資訊設備數量 server 總計
			int totalAmount = hostAmount + serverAmount; // 受害資訊設備數量 host + server 總計
			String xmlTotalAmount = String.valueOf(totalAmount);
			long contactorId = notification.getContactorId(); // Member Account
			long mainUnit1 = notification.getMainUnit1(); // 權責機關(上級機關)
			
			// 事發機關結報時間
			GregorianCalendar tempFinishDateTime = new GregorianCalendar();
			tempFinishDateTime.setTime(notification.getFinishDateTime());
			XMLGregorianCalendar xmlFinishDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(tempFinishDateTime); // 調查、處理及改善報告繳交(登錄結報)時間

			String xmlAffectOther = ""; // 影響機關(構)/重要民生設施領域名稱
			Boolean affectOther1 = notification.getAffectOther1(); // 影響機關(構)/重要民生設施領域名稱(水資源)
			Boolean affectOther2 = notification.getAffectOther2(); // 影響機關(構)/重要民生設施領域名稱(能源)
			Boolean affectOther3 = notification.getAffectOther3(); // 影響機關(構)/重要民生設施領域名稱(通訊傳播)
			Boolean affectOther4 = notification.getAffectOther4(); // 影響機關(構)/重要民生設施領域名稱(交通)
			Boolean affectOther5 = notification.getAffectOther5(); // 影響機關(構)/重要民生設施領域名稱(銀行與金融)
			Boolean affectOther6 = notification.getAffectOther6(); // 影響機關(構)/重要民生設施領域名稱(緊急救援與醫院)
			Boolean affectOther7 = notification.getAffectOther7(); // 影響機關(構)/重要民生設施領域名稱(重要政府機關)
			Boolean affectOther8 = notification.getAffectOther8(); // 影響機關(構)/重要民生設施領域名稱(高科技園區)
			if (affectOther1) {
				xmlAffectOther = "1"; // 水資源
			}
			if (affectOther2) {
			    xmlAffectOther += "2"; // 能源
			}
			if (affectOther3) {
			    xmlAffectOther += "3"; // 通訊傳播
			}
			if (affectOther4) {
			    xmlAffectOther += "4"; // 交通
			}
			if (affectOther5) {
			    xmlAffectOther += "5"; // 銀行與金融
			}
			if (affectOther6) {
			    xmlAffectOther += "6"; // 緊急救援與醫院
			}
			if (affectOther7) {
			    xmlAffectOther += "7"; // 重要政府機關
			}
			if (affectOther8) {
			    xmlAffectOther += "8"; // 高科技園區
			}
			if (xmlAffectOther == null || xmlAffectOther.isEmpty()) {
				xmlAffectOther = "0"; // 無資料
			}

			// 事件發生原因
			String xmlFinish1Reason = ""; // 事件發生原因
			Integer finish1Reason = notification.getFinish1Reason(); // 事件發生原因(作業系統漏洞)
			String finish1ReasonOther = notification.getFinish1ReasonOther(); // 事件發生原因(其他-說明)
			
			switch (finish1Reason) {
				case 1:
					xmlFinish1Reason = "6"; // 作業系統漏洞
					break;
				case 2:
					xmlFinish1Reason = "7"; // 弱密碼
					break;
				case 3:
					xmlFinish1Reason = "8"; // 應用程式漏洞
					break;
				case 4:
					xmlFinish1Reason = "9"; // 網站設計不當
					break;
				case 5:
					xmlFinish1Reason = "2"; // 人為疏失
					break;
				case 6:
					xmlFinish1Reason = "3"; // 設定錯誤
					break;
				case 7:
					xmlFinish1Reason = "系統遭入侵";
					break;
				case 8:
					xmlFinish1Reason = finish1ReasonOther;
					break;
			}

			// 攻擊手法與調查結果說明
			String xmlEventType = ""; // 事件分類與異常狀況
			String xmlEventTypeClass = ""; // 事件類型
			String tmpXmlEventType = "";
			Integer eventType = notification.getEventType(); // 事件分類與異常狀況
			String eventType5Other = notification.getEventType5Other(); // 事件分類與異常狀況(其他異常原因)
			
			switch (eventType) {
				case 1:
					xmlEventTypeClass = eventType.toString(); 
					xmlEventType = "網頁攻擊：";
					Boolean isEventType1Opt1 = notification.getIsEventType1Opt1(); // 網頁置換
					Boolean isEventType1Opt2 = notification.getIsEventType1Opt2(); // 惡意留言
					Boolean isEventType1Opt3 = notification.getIsEventType1Opt3(); // 惡意網頁
					Boolean isEventType1Opt4 = notification.getIsEventType1Opt4(); // 釣魚網頁
					Boolean isEventType1Opt5 = notification.getIsEventType1Opt5(); // 網頁木馬
					Boolean isEventType1Opt6 = notification.getIsEventType1Opt6(); // 網站個資外洩
					if (isEventType1Opt1) {
						tmpXmlEventType += "網頁置換";
					}
					if (isEventType1Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意留言";
						} else {
							tmpXmlEventType += "惡意留言";
						}						
					}
					if (isEventType1Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "惡意網頁";
						} else {
							tmpXmlEventType += "惡意網頁";
						}						
					}
					if (isEventType1Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "釣魚網頁";
						} else {
							tmpXmlEventType += "釣魚網頁";
						}						
					}
					if (isEventType1Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網頁木馬";
						} else {
							tmpXmlEventType += "網頁木馬";
						}						
					}
					if (isEventType1Opt6) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網站個資外洩";
						} else {
							tmpXmlEventType += "網站個資外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 2:
					xmlEventType = "非法入侵：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType2Opt1 = notification.getIsEventType2Opt1(); // 系統遭入侵
					Boolean isEventType2Opt2 = notification.getIsEventType2Opt2(); // 植入惡意程式
					Boolean isEventType2Opt3 = notification.getIsEventType2Opt3(); // 異常連線
					Boolean isEventType2Opt4 = notification.getIsEventType2Opt4(); // 發送垃圾郵件
					Boolean isEventType2Opt5 = notification.getIsEventType2Opt5(); // 資料外洩
					if (isEventType2Opt1) {
						tmpXmlEventType += "系統遭入侵";
					}
					if (isEventType2Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "植入惡意程式";
						} else {
							tmpXmlEventType += "植入惡意程式";
						}						
					}
					if (isEventType2Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "異常連線";
						} else {
							tmpXmlEventType += "異常連線";
						}						
					}
					if (isEventType2Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "發送垃圾郵件";
						} else {
							tmpXmlEventType += "發送垃圾郵件";
						}						
					}
					if (isEventType2Opt5) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "資料外洩";
						} else {
							tmpXmlEventType += "資料外洩";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 3:
					xmlEventType = "阻斷服務(DoS/DDoS)：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType3Opt1 = notification.getIsEventType3Opt1(); // 服務中斷
					Boolean isEventType3Opt2 = notification.getIsEventType3Opt2(); // 效能降低
					if (isEventType3Opt1) {
						tmpXmlEventType += "服務中斷";
					}
					if (isEventType3Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "效能降低";
						} else {
							tmpXmlEventType += "效能降低";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 4:
					xmlEventType = "設備異常：";
					xmlEventTypeClass = eventType.toString();
					Boolean isEventType4Opt1 = notification.getIsEventType4Opt1(); // 設備毀損
					Boolean isEventType4Opt2 = notification.getIsEventType4Opt2(); // 電力異常
					Boolean isEventType4Opt3 = notification.getIsEventType4Opt3(); // 網路服務中斷
					Boolean isEventType4Opt4 = notification.getIsEventType4Opt4(); // 設備遺失
					if (isEventType4Opt1) {
						tmpXmlEventType += "設備毀損";
					}
					if (isEventType4Opt2) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "電力異常";
						} else {
							tmpXmlEventType += "電力異常";
						}						
					}
					if (isEventType4Opt3) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "網路服務中斷";
						} else {
							tmpXmlEventType += "網路服務中斷";
						}						
					}
					if (isEventType4Opt4) {
						if (tmpXmlEventType != "") {
							tmpXmlEventType += "|" + "設備遺失";
						} else {
							tmpXmlEventType += "設備遺失";
						}						
					}
					xmlEventType += tmpXmlEventType;

					break;
				case 5:
					xmlEventType = "其他異常原因：";
					xmlEventTypeClass = eventType5Other;
					if (eventType5Other != "") {
						xmlEventType += eventType5Other;
					}
					break;
			}

			// 作業系統名稱、版本
			String xmlIsOs = ""; // 事件發生原因
			Boolean isOsOpt1 = notification.getIsOsOpt1(); // Windows系列
			Boolean isOsOpt2 = notification.getIsOsOpt2(); // Linux系列
			Boolean isOsOpt3 = notification.getIsOsOpt3(); // 其他作業平台
			String isOsOpt3Other = notification.getIsOsOpt3Other(); // 其他作業平台  - 說明
			
			if (isOsOpt1) {
				xmlIsOs = "Windows系列";
			} else if (isOsOpt2) {
				xmlIsOs = "Linux系列";
			} else if (isOsOpt3) {
				xmlIsOs = "其他作業平台：" + isOsOpt3Other;
			}
			if (xmlIsOs == null || xmlIsOs.isEmpty()) {
				xmlIsOs = "無資料";
			}
			
			// IP位址(IP Address) - IPV4
			String ipExternal = notification.getIpExternal(); // 外部
			String ipInternal = notification.getIpInternal(); // 內部
			String xmlIpv4 = "";
			
			if (ipExternal != null) {
				xmlIpv4 = ipExternal; 
			} 
			
			if (ipInternal != null) {
				if (xmlIpv4 == null || xmlIpv4.isEmpty()) {
				    xmlIpv4 = ipInternal;
				} else {
					xmlIpv4 += "|" + ipInternal;
				}
			}
		    if (xmlIpv4 == null || xmlIpv4.isEmpty()) {
		    	xmlIpv4 = "";
		    }
			
			// 網際網路位置(Web-url)
			String xmlWebUrl = notification.getWebUrl();
			if (xmlWebUrl == null || xmlWebUrl.isEmpty()) {
				xmlWebUrl = "";
			}

			// Ⅰ. 補強系統/程式安全設定 & Ⅱ. 資安管理與教育訓練
			String xmlIsFinishDo = "";
			String xmlIsFinishDo2 = "";

			switch (eventType) {
				case 1: // 網頁攻擊
					
					Boolean isFinish1DoSysOpt1 = notification.getIsFinish1DoSysOpt1(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish1DoSysOpt2 = notification.getIsFinish1DoSysOpt2(); // 已完成評估變更受害主機中所有帳號之密碼(含本機管理者)
					Boolean isFinish1DoSysOpt3 = notification.getIsFinish1DoSysOpt3(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)
					Boolean isFinish1DoSysOpt4 = notification.getIsFinish1DoSysOpt4(); // 關閉網路芳鄰功能
					Boolean isFinish1DoSysOpt5 = notification.getIsFinish1DoSysOpt5(); // 設定robots.txt檔，控制網站可被搜尋頁面
					Boolean isFinish1DoSysOpt6 = notification.getIsFinish1DoSysOpt6(); // 已針對所有需要特殊存取權限之網頁加強身分驗證機制
					Boolean isFinish1DoSysOpt7 = notification.getIsFinish1DoSysOpt7(); // 限制網站主機上傳之附件檔案類型
					Boolean isFinish1DoSysOpt8 = notification.getIsFinish1DoSysOpt8(); // 限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制
					Boolean isFinish1DoSysOpt9 = notification.getIsFinish1DoSysOpt9(); // 限制連線資料庫之主機IP
					Boolean isFinish1DoSysOpt10 = notification.getIsFinish1DoSysOpt10(); // 關閉 WebDAV(Web Distribution Authoring and Versioning)
					String isFinish1DoSysOpt3Remark = notification.getFinish1DoSysOpt3Remark(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)-說明
					String isFinish1DoSysOpt6Remark = notification.getFinish1DoSysOpt6Remark(); // 已針對所有需要特殊存取權限之網頁加強身分驗證機制-說明
					String isFinish1DoSysOpt7Remark = notification.getFinish1DoSysOpt7Remark(); // 限制網站主機上傳之附件檔案類型-說明
					
					Boolean IsFinish1DoEduOpt1 = notification.getIsFinish1DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish1DoEduOpt2 = notification.getIsFinish1DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish1DoEduOpt3 = notification.getIsFinish1DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish1DoEduOpt4 = notification.getIsFinish1DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish1DoSysOpt1) {
						xmlIsFinishDo = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
						xmlIsFinishDo2 = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
					}
					if (isFinish1DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";					
							xmlIsFinishDo2 = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						} else {
							xmlIsFinishDo += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						}
					}
					if (isFinish1DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本(包含網站編輯管理程式，如：FrontPage)" + "-" + isFinish1DoSysOpt3Remark;
						}
					}
					if (isFinish1DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";
							xmlIsFinishDo2 = "關閉網路芳鄰功能";
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
							xmlIsFinishDo2 += "|" + "關閉網路芳鄰功能";
						}
					}
					if (isFinish1DoSysOpt5) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "設定robots.txt檔，控制網站可被搜尋頁面";
							xmlIsFinishDo2 = "設定robots.txt檔，控制網站可被搜尋頁面";
						} else {
							xmlIsFinishDo += "|" + "設定robots.txt檔，控制網站可被搜尋頁面";
							xmlIsFinishDo2 += "|" + "設定robots.txt檔，控制網站可被搜尋頁面";
						}
					}
					if (isFinish1DoSysOpt6) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
							xmlIsFinishDo2 = "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
						} else {
							xmlIsFinishDo += "|" + "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
							xmlIsFinishDo2 += "|" + "已針對所有需要特殊存取權限之網頁加強身分驗證機制" + "-" + isFinish1DoSysOpt6Remark;
						}
					}
					if (isFinish1DoSysOpt7) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
							xmlIsFinishDo2 = "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
						} else {
							xmlIsFinishDo += "|" + "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
							xmlIsFinishDo2 += "|" + "限制網站主機上傳之附件檔案類型" + "-" + isFinish1DoSysOpt7Remark;
						}
					}
					if (isFinish1DoSysOpt8) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
							xmlIsFinishDo2 = "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
						} else {
							xmlIsFinishDo += "|" + "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
							xmlIsFinishDo2 += "|" + "限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制";
						}
					}
					if (isFinish1DoSysOpt9) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "限制連線資料庫之主機IP";
							xmlIsFinishDo2 = "限制連線資料庫之主機IP";
						} else {
							xmlIsFinishDo += "|" + "限制連線資料庫之主機IP";
							xmlIsFinishDo2 += "|" + "限制連線資料庫之主機IP";
						}
					}
					if (isFinish1DoSysOpt10) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉 WebDAV(Web Distribution Authoring and Versioning)";
							xmlIsFinishDo2 = "關閉 WebDAV(Web Distribution Authoring and Versioning)";
						} else {
							xmlIsFinishDo += "|" + "關閉 WebDAV(Web Distribution Authoring and Versioning)";
							xmlIsFinishDo2 += "|" + "關閉 WebDAV(Web Distribution Authoring and Versioning)";
						}
					}
					
					if (IsFinish1DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish1DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish1DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish1DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
				
				case 2: // 非法入侵

					Boolean isFinish2DoSysOpt1 = notification.getIsFinish2DoSysOpt1(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish2DoSysOpt2 = notification.getIsFinish2DoSysOpt2(); // 已完成評估變更受害主機中所有帳號之密碼(含本機管理者)
					Boolean isFinish2DoSysOpt3 = notification.getIsFinish2DoSysOpt3(); // 已完成檢視/更新受害主機系統與所有應用程式至最版本
					Boolean isFinish2DoSysOpt4 = notification.getIsFinish2DoSysOpt4(); // 關閉郵件伺服器 Open Relay功能
					Boolean isFinish2DoSysOpt5 = notification.getIsFinish2DoSysOpt5(); // 關閉網路芳鄰功能
					String isFinish2DoSysOpt1Remark = notification.getFinish2DoSysOpt1Remark(); // 已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)-說明

					Boolean IsFinish2DoEduOpt1 = notification.getIsFinish2DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish2DoEduOpt2 = notification.getIsFinish2DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish2DoEduOpt3 = notification.getIsFinish2DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish2DoEduOpt4 = notification.getIsFinish2DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish2DoSysOpt1) {
						xmlIsFinishDo = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)" + "-" + isFinish2DoSysOpt1Remark;
						xmlIsFinishDo2 = "已完成評估變更透過受害主機登入應用系統密碼之必要性(如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)" + "-" + isFinish2DoSysOpt1Remark;
					}
					if (isFinish2DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 = "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						} else {
							xmlIsFinishDo += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
							xmlIsFinishDo2 += "|" + "已完成評估變更受害主機中所有帳號之密碼(含本機管理者)";
						}
					}
					if (isFinish2DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最版本";
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最版本";
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本";
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最版本";
						}
					}
					if (isFinish2DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉郵件伺服器 Open Relay功能";
							xmlIsFinishDo2 = "關閉郵件伺服器 Open Relay功能";
						} else {
							xmlIsFinishDo += "|" + "關閉郵件伺服器 Open Relay功能";
							xmlIsFinishDo2 += "|" + "關閉郵件伺服器 Open Relay功能";
						}
					}
					if (isFinish2DoSysOpt5) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";
							xmlIsFinishDo2 = "關閉網路芳鄰功能";
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
							xmlIsFinishDo2 += "|" + "關閉網路芳鄰功能";
						}
					}
					
					if (IsFinish2DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish2DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish2DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish2DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 3: // 阻斷服務(DoS/DDoS)

					Boolean isFinish3DoSysOpt1 = notification.getIsFinish3DoSysOpt1(); // 限制同時間單一 IP 連線
					Boolean isFinish3DoSysOpt2 = notification.getIsFinish3DoSysOpt2(); // DNS主機停用外部遞迴查詢
					Boolean isFinish3DoSysOpt3 = notification.getIsFinish3DoSysOpt3(); // 已完成檢視/移除主機/伺服器不必要服務功能
					Boolean isFinish3DoSysOpt4 = notification.getIsFinish3DoSysOpt4(); // 已完成檢視/更新受害主機系統與所有應用程式至最新版本
					String isFinish3DoSysOpt3Remark = notification.getFinish3DoSysOpt3Remark(); // 已完成檢視/移除主機/伺服器不必要服務功能-說明
					String isFinish3DoSysOpt4Remark = notification.getFinish3DoSysOpt4Remark(); // 已完成檢視/更新受害主機系統與所有應用程式至最新版本-說明

					Boolean IsFinish3DoEduOpt1 = notification.getIsFinish3DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish3DoEduOpt2 = notification.getIsFinish3DoEduOpt2(); // 修正內部資安防護計畫
					
					if (isFinish3DoSysOpt1) {
						xmlIsFinishDo = "限制同時間單一 IP 連線";
						xmlIsFinishDo2 = "限制同時間單一 IP 連線";
					}
					if (isFinish3DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "DNS主機停用外部遞迴查詢";
							xmlIsFinishDo2 = "DNS主機停用外部遞迴查詢";
						} else {
							xmlIsFinishDo += "|" + "DNS主機停用外部遞迴查詢";
							xmlIsFinishDo2 += "|" + "DNS主機停用外部遞迴查詢";
						}
					}
					if (isFinish3DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
							xmlIsFinishDo2 = "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/移除主機/伺服器不必要服務功能" + "-" + isFinish3DoSysOpt3Remark;
						}
					}
					if (isFinish3DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
							xmlIsFinishDo2 = "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
						} else {
							xmlIsFinishDo += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
							xmlIsFinishDo2 += "|" + "已完成檢視/更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish3DoSysOpt4Remark;
						}
					}

					if (IsFinish3DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish3DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 4: // 設備異常

					Boolean isFinish4DoSysOpt1 = notification.getIsFinish4DoSysOpt1(); // 檢視資訊設備使用年限

					Boolean IsFinish4DoEduOpt1 = notification.getIsFinish4DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish4DoEduOpt2 = notification.getIsFinish4DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish4DoEduOpt3 = notification.getIsFinish4DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish4DoEduOpt4 = notification.getIsFinish4DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish4DoSysOpt1) {
						xmlIsFinishDo = "檢視資訊設備使用年限";
						xmlIsFinishDo2 = "檢視資訊設備使用年限";
					}

					if (IsFinish4DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish4DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish4DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish4DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
					
				case 5: // 其他 異常原因

					Boolean isFinish5DoSysOpt1 = notification.getIsFinish5DoSysOpt1(); // 已更新受害主機系統與所有應用程式至最新版本
					Boolean isFinish5DoSysOpt2 = notification.getIsFinish5DoSysOpt2(); // 變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)
					Boolean isFinish5DoSysOpt3 = notification.getIsFinish5DoSysOpt3(); // 變更受害主機中所有帳號之密碼 (含本機管理者)
					Boolean isFinish5DoSysOpt4 = notification.getIsFinish5DoSysOpt4(); // 關閉網路芳鄰功能
					String isFinish5DoSysOpt1Remark = notification.getFinish5DoSysOpt1Remark(); // 已更新受害主機系統與所有應用程式至最版本-說明

					Boolean IsFinish5DoEduOpt1 = notification.getIsFinish5DoEduOpt1(); // 重新檢視機關網路架構適切性
					Boolean IsFinish5DoEduOpt2 = notification.getIsFinish5DoEduOpt2(); // 機關內部全面性安全檢測
					Boolean IsFinish5DoEduOpt3 = notification.getIsFinish5DoEduOpt3(); // 加強內部同仁資安教育訓練
					Boolean IsFinish5DoEduOpt4 = notification.getIsFinish5DoEduOpt4(); // 修正內部資安防護計畫
					
					if (isFinish5DoSysOpt1) {
						xmlIsFinishDo = "已更新受害主機系統與所有應用程式至最新版本" + "-" + isFinish5DoSysOpt1Remark;
					}
					if (isFinish5DoSysOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";					
						} else {
							xmlIsFinishDo += "|" + "變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等)";
						}
					}
					if (isFinish5DoSysOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "變更受害主機中所有帳號之密碼 (含本機管理者)";					
						} else {
							xmlIsFinishDo += "|" + "變更受害主機中所有帳號之密碼 (含本機管理者)";
						}
					}
					if (isFinish5DoSysOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "關閉網路芳鄰功能";					
						} else {
							xmlIsFinishDo += "|" + "關閉網路芳鄰功能";
						}
					}

					if (IsFinish5DoEduOpt1) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "重新檢視機關網路架構適切性";					
						} else {
							xmlIsFinishDo += "|" + "重新檢視機關網路架構適切性";
						}
					}
					if (IsFinish5DoEduOpt2) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "機關內部全面性安全檢測";					
						} else {
							xmlIsFinishDo += "|" + "機關內部全面性安全檢測";
						}
					}
					if (IsFinish5DoEduOpt3) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "加強內部同仁資安教育訓練";					
						} else {
							xmlIsFinishDo += "|" + "加強內部同仁資安教育訓練";
						}
					}
					if (IsFinish5DoEduOpt4) {
						if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
							xmlIsFinishDo = "修正內部資安防護計畫";					
						} else {
							xmlIsFinishDo += "|" + "修正內部資安防護計畫";
						}
					}
					if (xmlIsFinishDo == null || xmlIsFinishDo.isEmpty()) {
						xmlIsFinishDo = "無資料";
					}
					break;
			}
			//System.out.println("eventType = " + eventType);
			//System.out.println("xmlIsFinishDo = " + xmlIsFinishDo);
			
			// 取得 通報人 所需資料
			String xmlMemberName = ""; // Member Name
			String xmlMemberPhone = ""; // Member Phone
			String xmlMemberEmail = ""; // Member Email
			
			if (contactorId > 0) {
				Member member = memberService.get(contactorId);

				xmlMemberName = member.getName(); // Member Name
				xmlMemberPhone = notification.getContactorTel();// Member Phone
				xmlMemberEmail = notification.getContactorEmail(); // Member Email
			}
			if (xmlMemberName == null || xmlMemberName.isEmpty()) {
				xmlMemberName = "無資料";
			}
			if (xmlMemberPhone == null || xmlMemberPhone.isEmpty()) {
				xmlMemberPhone = "99999999";
			}
			if (xmlMemberEmail == null || xmlMemberEmail.isEmpty()) {
				xmlMemberEmail = "無資料";
			}

			// 取得 main unit 1 (權責機關-上級機關) 所需資料
			String xmlOrgMainUnit1Name = "無資料";
			
			if (mainUnit1 > 0) {
				Org org2 = orgService.getDataById(mainUnit1);
				xmlOrgMainUnit1Name = org2.getName();
			}
			else {
				Member memberInfo = memberService.get(notification.getContactorId());
				ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(memberInfo.getOrgId());
				xmlOrgMainUnit1Name = superviseParentOrg.getName();
			}
					
			// 設定使用哪一組 xml 設定做介接之數字，提供後段程式使用
			int gotoStep = 0;
			
			if (notification.getEffectLevel() >= 1 && notification.getEffectLevel() <= 2) {
				gotoStep = 4; // 1、2結報
			} else if (notification.getEffectLevel() >= 3 && notification.getEffectLevel() <= 4) {
				gotoStep = 5; // 3、4結報
				
//				if (totalAmount > 1) {
//					gotoStep = 6; // 3、4結報(多個受害設備類型)
//				}
			
			}
			//System.out.println("genStixXMLStep4And5() → gotoStep = " + gotoStep);
			
			if (gotoStep == 4) {
				//System.out.println("genStixXMLStep4And5() → Step 4");
				
				// 結報1、2

				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:FileObj=\"http://cybox.mitre.org/objects#FileObject-2\"\r\n" + 
						"	xmlns:ProductObj=\"http://cybox.mitre.org/objects#ProductObject-2\"\r\n" + 
						"	xmlns:DeviceObj=\"http://cybox.mitre.org/objects#DeviceObject-2\"\r\n" + 
						"	xmlns:AddressObj=\"http://cybox.mitre.org/objects#AddressObject-2\"\r\n" + 
						"	xmlns:SystemObj=\"http://cybox.mitre.org/objects#SystemObject-2\"\r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:URIObj=\"http://cybox.mitre.org/objects#URIObject-2\"\r\n" + 
						"	xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:ttp=\"http://stix.mitre.org/TTP-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\"\r\n" + 
						"	xmlns:et=\"http://stix.mitre.org/ExploitTarget-1\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:cybox=\"http://cybox.mitre.org/cybox-2\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-2eb74fe8-af76-4656-8d5c-cba9f1ac2acd\" version=\"1.2\">\r\n" + 
						"    <stix:TTPs>\r\n" + 
						"        <stix:TTP id=\"example:ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='ttp:TTPType'>\r\n" + 
						"            <ttp:Description>攻擊手法與調查結果說明</ttp:Description>\r\n" + 
						"            <ttp:Intended_Effect timestamp=\"2019-04-12T11:31:48.697623+00:00\">\r\n" + 
						"                <!--損害類別-->\r\n" + 
						"				<stixCommon:Value>1</stixCommon:Value>\r\n" + 
						"                <stixCommon:Description>損害類別說明</stixCommon:Description>\r\n" + 
						"            </ttp:Intended_Effect>\r\n" + 
						"            <ttp:Resources>\r\n" + 
						"                <ttp:Infrastructure>\r\n" + 
						"                    <ttp:Observable_Characterization cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                        <cybox:Observable id=\"example:Observable-eda7af28-877f-45ec-bf8a-75db2ed19082\">\r\n" + 
						"                            <cybox:Object id=\"example:File-d6d68727-c3bf-469e-bb18-ef603190d423\">\r\n" + 
						"                                <cybox:Properties xsi:type=\"FileObj:FileObjectType\">\r\n" + 
						"                                    <FileObj:File_Name />\r\n" + 
						"                                    <FileObj:File_Path />\r\n" + 
						"                                    <FileObj:Hashes>\r\n" + 
						"                                        <cyboxCommon:Hash>\r\n" + 
						"										<!--發現之惡意程式檔案Hash類別-->\r\n" + 
						"                                            <cyboxCommon:Type xsi:type=\"cyboxVocabs:HashNameVocab-1.0\" />\r\n" + 
						"											<!--發現之惡意程式檔案Hash值-->\r\n" + 
						"                                            <cyboxCommon:Simple_Hash_Value />\r\n" + 
						"                                        </cyboxCommon:Hash>\r\n" + 
						"                                    </FileObj:Hashes>\r\n" + 
						"                                </cybox:Properties>\r\n" + 
						"                            </cybox:Object>\r\n" + 
						"                        </cybox:Observable>\r\n" + 
						"                    </ttp:Observable_Characterization>\r\n" + 
						"                </ttp:Infrastructure>\r\n" + 
						"            </ttp:Resources>\r\n" + 
						"            <ttp:Exploit_Targets>\r\n" + 
						"                <!--事件發生原因-->\r\n" + 
						"				<ttp:Exploit_Target>\r\n" + 
						"                    <stixCommon:Exploit_Target id=\"example:et-febdaefb-74d1-41ca-b040-aedf6ad7a086\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='et:ExploitTargetType'>\r\n" + 
						"                        <et:Description>" + xmlFinish1Reason + "</et:Description>\r\n" + 
						"                    </stixCommon:Exploit_Target>\r\n" + 
						"                </ttp:Exploit_Target>\r\n" + 
						"            </ttp:Exploit_Targets>\r\n" + 
						"        </stix:TTP>\r\n" + 
						"    </stix:TTPs>\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-7a12b412-7c10-4743-9715-b14a75ff6812\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"            <!--Ncert通報單ID編號-->\r\n" + 
						"			<incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"			<!--通報單狀態-->\r\n" + 
						"            <incident:Status>4</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-f2757543-6c5f-43ac-a449-41ad623ad798\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <incident:Time>\r\n" + 
						"						<!--事發機關結報時間-->\r\n" + 
						"                            <incident:Incident_Closed precision=\"second\">" + xmlFinishDateTime + "</incident:Incident_Closed>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:Affected_Assets>\r\n" + 
						"                            <incident:Affected_Asset>\r\n" + 
						"							<!--受害設備數量與類型-->\r\n" + 
						"                                <incident:Type count_affected=\"" + xmlTotalAmount + "\">1</incident:Type>\r\n" + 
						"                                <incident:Description>受害設備說明</incident:Description>\r\n" + 
						"                                <incident:Structured_Description cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-d29ca0b6-12df-4e9a-9729-afdc8dddb0ab\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-03795a20-c3e9-42f3-b6e4-ba1f15d90d74\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Vendor>受害設備廠牌</ProductObj:Vendor>\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"DeviceObj:DeviceObjectType\">\r\n" + 
						"                                                    <DeviceObj:Model>受害設備型號</DeviceObj:Model>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-24fb54ae-46fd-4474-b8aa-7f31bf28c1b9\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-f6f8541e-2d29-474c-b526-b67bae8f1813\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"SystemObj:SystemObjectType\">\r\n" + 
						"                                                    <SystemObj:OS>\r\n" + 
						"                                                        <SystemObj:Platform>\r\n" + 
						"                                                            <cyboxCommon:Description>" + xmlIsOs + "</cyboxCommon:Description>\r\n" + 
						"                                                        </SystemObj:Platform>\r\n" + 
						"                                                    </SystemObj:OS>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-67143c00-a4b9-4a41-bfea-7ce94629bbc0\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-e9a50521-d74f-4fbb-870e-b1004fa78860\">\r\n" + 
						"                                            <!--受害設備IPv4位址-->\r\n" + 
						"											<cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv4-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value>" + xmlIpv4 + "</AddressObj:Address_Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-f0cafcb8-8d6a-40f4-938e-c178cd693f67\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-90ad16b5-1c2d-48a0-a0b8-c79f39318cc8\">\r\n" + 
						"										<!--受害設備IPv6位址-->\r\n" + 
						"                                            <cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv6-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value />\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-a20ffcd8-df1c-47d5-8357-0561eca78895\">\r\n" + 
						"                                        <cybox:Object id=\"example:URI-797de1c2-5d12-4a05-96fc-be08634a4c7a\">\r\n" + 
						"										<!--受害設備URL-->\r\n" + 
						"                                            <cybox:Properties xsi:type=\"URIObj:URIObjectType\" type=\"URL\">\r\n" + 
						"                                                <URIObj:Value>" + xmlWebUrl + "</URIObj:Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                </incident:Structured_Description>\r\n" + 
						"                            </incident:Affected_Asset>\r\n" + 
						"                        </incident:Affected_Assets>\r\n" + 
						"                        <incident:Leveraged_TTPs>\r\n" + 
						"                            <incident:Leveraged_TTP>\r\n" + 
						"                                <stixCommon:TTP idref=\"example:ttp-931e7616-2f7c-493a-9e86-ca8ecc8bf0bf\" xsi:type='ttp:TTPType'/>\r\n" + 
						"                            </incident:Leveraged_TTP>\r\n" + 
						"                        </incident:Leveraged_TTPs>\r\n" + 
						"                        <incident:COA_Taken>\r\n" + 
						"                            <incident:Course_Of_Action id=\"example:coa-1e775993-580f-4e5b-a62e-50877f903fd3\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                <coa:Related_COAs>\r\n" + 
						"                                    <coa:Related_COA>\r\n" + 
						"                                        <stixCommon:Course_Of_Action id=\"example:coa-be82a374-dab5-4ac9-931c-ef943a04a0f5\" timestamp=\"2019-04-12T11:31:48.697623+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                            <coa:Description>" + xmlIsFinishDo + "</coa:Description>\r\n" + 
						"                                        </stixCommon:Course_Of_Action>\r\n" + 
						"                                    </coa:Related_COA>\r\n" + 
						"                                </coa:Related_COAs>\r\n" + 
						"                            </incident:Course_Of_Action>\r\n" + 
						"                        </incident:COA_Taken>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";
				
			} else if (gotoStep == 5) {
				//System.out.println("genStixXMLStep4And5() → Step 5");
				
				// 3、4級結報
				
				xmlStr = "<stix:STIX_Package \r\n" + 
						"	xmlns:SystemObj=\"http://cybox.mitre.org/objects#SystemObject-2\"\r\n" + 
						"	xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"\r\n" + 
						"	xmlns:FileObj=\"http://cybox.mitre.org/objects#FileObject-2\"\r\n" + 
						"	xmlns:ProductObj=\"http://cybox.mitre.org/objects#ProductObject-2\"\r\n" + 
						"	xmlns:URIObj=\"http://cybox.mitre.org/objects#URIObject-2\"\r\n" + 
						"	xmlns:DeviceObj=\"http://cybox.mitre.org/objects#DeviceObject-2\"\r\n" + 
						"	xmlns:AddressObj=\"http://cybox.mitre.org/objects#AddressObject-2\"\r\n" + 
						"	xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\"\r\n" + 
						"	xmlns:ttp=\"http://stix.mitre.org/TTP-1\"\r\n" + 
						"	xmlns:stix=\"http://stix.mitre.org/stix-1\"\r\n" + 
						"	xmlns:coa=\"http://stix.mitre.org/CourseOfAction-1\"\r\n" + 
						"	xmlns:stixCommon=\"http://stix.mitre.org/common-1\"\r\n" + 
						"	xmlns:cybox=\"http://cybox.mitre.org/cybox-2\"\r\n" + 
						"	xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\"\r\n" + 
						"	xmlns:et=\"http://stix.mitre.org/ExploitTarget-1\"\r\n" + 
						"	xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"\r\n" + 
						"	xmlns:incident=\"http://stix.mitre.org/Incident-1\"\r\n" + 
						"	xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\"\r\n" + 
						"	xmlns:example=\"http://example.com\"\r\n" + 
						"	xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n" + 
						"	xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\r\n" + 
						"	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
						"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
						"	 id=\"example:Package-a5946bb9-e2a3-4597-8554-b00c5dd33ce6\" version=\"1.2\">\r\n" + 
						"    <stix:TTPs>\r\n" + 
						"        <stix:TTP id=\"example:ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='ttp:TTPType'>\r\n" + 
						"            <ttp:Description>" + xmlEventType + "</ttp:Description>\r\n" + 
						"            <ttp:Intended_Effect timestamp=\"2019-04-12T11:54:53.178748+00:00\">\r\n" + 
						"                <!--損害類別-->\r\n" + 
						"				<stixCommon:Value>1</stixCommon:Value>\r\n" + 
						"                <stixCommon:Description>損害類別說明</stixCommon:Description>\r\n" + 
						"            </ttp:Intended_Effect>\r\n" + 
						"            <ttp:Resources>\r\n" + 
						"                <ttp:Infrastructure>\r\n" + 
						"                    <ttp:Observable_Characterization cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                        <cybox:Observable id=\"example:Observable-48c63a01-7943-4746-bcdd-27d6a03ced3b\">\r\n" + 
						"                            <cybox:Object id=\"example:File-2d6d6290-f288-41ff-acdf-8e2a93cbdea2\">\r\n" + 
						"                                <cybox:Properties xsi:type=\"FileObj:FileObjectType\">\r\n" + 
						"                                    <FileObj:File_Name />\r\n" + 
						"                                    <FileObj:File_Path />\r\n" + 
						"                                    <FileObj:Hashes>\r\n" + 
						"                                        <cyboxCommon:Hash>\r\n" + 
						"                                            <!--發現之惡意程式檔案Hash類別-->\r\n" + 
						"											<cyboxCommon:Type xsi:type=\"cyboxVocabs:HashNameVocab-1.0\" />\r\n" + 
						"                                            <!--發現之惡意程式檔案Hash值-->\r\n" + 
						"											<cyboxCommon:Simple_Hash_Value />\r\n" + 
						"                                        </cyboxCommon:Hash>\r\n" + 
						"                                    </FileObj:Hashes>\r\n" + 
						"                                </cybox:Properties>\r\n" + 
						"                            </cybox:Object>\r\n" + 
						"                        </cybox:Observable>\r\n" + 
						"                    </ttp:Observable_Characterization>\r\n" + 
						"                </ttp:Infrastructure>\r\n" + 
						"            </ttp:Resources>\r\n" + 
						"            <ttp:Exploit_Targets>\r\n" + 
						"                <ttp:Exploit_Target>\r\n" + 
						"                    <stixCommon:Exploit_Target id=\"example:et-4b2ac3e7-c91d-47fb-bcc5-cb7b88e5b8b9\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='et:ExploitTargetType'>\r\n" + 
						"                        <!--事件發生原因-->\r\n" + 
						"						<et:Description>" + xmlFinish1Reason + "</et:Description>\r\n" + 
						"                    </stixCommon:Exploit_Target>\r\n" + 
						"                </ttp:Exploit_Target>\r\n" + 
						"            </ttp:Exploit_Targets>\r\n" + 
						"        </stix:TTP>\r\n" + 
						"    </stix:TTPs>\r\n" + 
						"    <stix:Incidents>\r\n" + 
						"        <stix:Incident id=\"example:incident-25006daa-3b54-4c4d-92c7-97ff19d8b128\" timestamp=\"2019-04-12T11:54:53.194371+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"            <!--Ncert通報單ID編號-->\r\n" + 
						"			<incident:External_ID>" + xmlCertId + "</incident:External_ID>\r\n" + 
						"            <!--CI領域之中央目的事業主管機關結報審核時間-->\r\n" + 
						"            <incident:Time>\r\n" + 
						"                <incident:Incident_Closed precision=\"second\">" + now + "</incident:Incident_Closed>\r\n" + 
						"            </incident:Time>\r\n" + 
						"            <incident:Reporter>\r\n" + 
						"                <stixCommon:Identity>\r\n" + 
						"                    <stixCommon:Name>" + xmlOrgMainUnit1Name + "</stixCommon:Name>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Reporter>\r\n" + 
						"            <incident:Responder>\r\n" + 
						"                <stixCommon:Identity xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">\r\n" + 
						"                    <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">\r\n" + 
						"                        <xpil:PartyName xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xnl:PersonName xmlns:xnl=\"urn:oasis:names:tc:ciq:xnl:3\">\r\n" + 
						"                            <xnl:NameElement>" + xmlMemberName + "</xnl:NameElement>\r\n" + 
						"                            </xnl:PersonName>\r\n" + 
						"                        </xpil:PartyName>\r\n" + 
						"                        <xpil:ContactNumbers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ContactNumber>\r\n" + 
						"                            <xpil:ContactNumberElement xpil:Type=\"LocalNumber\">" + xmlMemberPhone + "</xpil:ContactNumberElement>\r\n" + 
						"                            </xpil:ContactNumber>\r\n" + 
						"                        </xpil:ContactNumbers>\r\n" + 
						"                        <xpil:ElectronicAddressIdentifiers xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\">\r\n" + 
						"                            <xpil:ElectronicAddressIdentifier>" + xmlMemberEmail + "</xpil:ElectronicAddressIdentifier>\r\n" + 
						"                        </xpil:ElectronicAddressIdentifiers>\r\n" + 
						"                    </stix-ciqidentity:Specification>\r\n" + 
						"                </stixCommon:Identity>\r\n" + 
						"            </incident:Responder>\r\n" + 
						"			<!--通報單狀態-->\r\n" + 
						"            <incident:Status>5</incident:Status>\r\n" + 
						"            <incident:Related_Incidents>\r\n" + 
						"                <incident:Related_Incident>\r\n" + 
						"                    <stixCommon:Incident id=\"example:incident-e7cea37a-5257-44f8-bb60-057a27e8a9fb\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='incident:IncidentType'>\r\n" + 
						"                        <incident:Time>\r\n" + 
						"                           <!--事發機關結報時間-->\r\n" + 
						"						   <incident:Incident_Closed precision=\"second\">" + xmlFinishDateTime + "</incident:Incident_Closed>\r\n" + 
						"                        </incident:Time>\r\n" + 
						"                        <incident:Affected_Assets>\r\n" + 
						"                            <incident:Affected_Asset>\r\n" + 
						"							<!--受害設備數量與類型-->\r\n" + 
						"                                <incident:Type count_affected=\"" + xmlTotalAmount + "\">1</incident:Type>\r\n" + 
						"                                <incident:Description>受害設備說明</incident:Description>\r\n" + 
						"                                <incident:Structured_Description cybox_major_version=\"2\" cybox_minor_version=\"1\" cybox_update_version=\"0\">\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-80659cc7-ed6b-473e-b517-59eb93944173\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-96e48692-292e-4148-a6da-9178586ab4aa\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Vendor>受害設備廠牌</ProductObj:Vendor>\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"DeviceObj:DeviceObjectType\">\r\n" + 
						"                                                    <DeviceObj:Model>受害設備型號</DeviceObj:Model>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-234aee20-3b00-4fa9-b8fc-eae3e0bb60e8\">\r\n" + 
						"                                        <cybox:Object id=\"example:Product-16a472e9-d644-45b6-8199-3e636a907180\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"ProductObj:ProductObjectType\">\r\n" + 
						"                                                <ProductObj:Device_Details xsi:type=\"SystemObj:SystemObjectType\">\r\n" + 
						"                                                    <SystemObj:OS>\r\n" + 
						"                                                        <SystemObj:Platform>\r\n" + 
						"                                                            <cyboxCommon:Description>" + xmlIsOs + "</cyboxCommon:Description>\r\n" + 
						"                                                        </SystemObj:Platform>\r\n" + 
						"                                                    </SystemObj:OS>\r\n" + 
						"                                                </ProductObj:Device_Details>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-f1b1c899-a29c-4d1f-b498-3c6d20d334b8\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-50f29465-4b45-4ead-a1b3-7aeb0d7780b1\">\r\n" + 
						"                                            <!--受害設備IPv4位址-->\r\n" + 
						"											<cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv4-addr\">\r\n" + 
						"                                                <AddressObj:Address_Value>" + xmlIpv4 + "</AddressObj:Address_Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-cfa7a1dc-d29a-4b71-98de-552589245868\">\r\n" + 
						"                                        <cybox:Object id=\"example:Address-81dfd394-1369-4ca7-90aa-1376017bc1c5\">\r\n" + 
						"                                            <cybox:Properties xsi:type=\"AddressObj:AddressObjectType\" category=\"ipv6-addr\">\r\n" + 
						"                                                <!--受害設備IPv6位址-->\r\n" + 
						"												<AddressObj:Address_Value />\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                    <cybox:Observable id=\"example:Observable-2187f05e-f4b3-4aa1-a6c7-6348091ef75d\">\r\n" + 
						"                                        <cybox:Object id=\"example:URI-03d6e0eb-b2c2-4941-b06f-cb8af25f8e1b\">\r\n" + 
						"                                            <!--受害設備URL-->\r\n" + 
						"											<cybox:Properties xsi:type=\"URIObj:URIObjectType\" type=\"URL\">\r\n" + 
						"                                                <URIObj:Value>" + xmlWebUrl + "</URIObj:Value>\r\n" + 
						"                                            </cybox:Properties>\r\n" + 
						"                                        </cybox:Object>\r\n" + 
						"                                    </cybox:Observable>\r\n" + 
						"                                </incident:Structured_Description>\r\n" + 
						"                            </incident:Affected_Asset>\r\n" + 
						"                        </incident:Affected_Assets>\r\n" + 
						"                        <incident:Leveraged_TTPs>\r\n" + 
						"                            <incident:Leveraged_TTP>\r\n" + 
						"                                <stixCommon:TTP idref=\"example:ttp-9d3fbba8-9f8e-4a9b-963d-39b0748d338f\" xsi:type='ttp:TTPType'/>\r\n" + 
						"                            </incident:Leveraged_TTP>\r\n" + 
						"                        </incident:Leveraged_TTPs>\r\n" + 
						"                        <incident:COA_Taken>\r\n" + 
						"                            <incident:Course_Of_Action id=\"example:coa-116435e6-2c76-4687-ba30-479c62e6f09d\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                <coa:Related_COAs>\r\n" + 
						"                                    <coa:Related_COA>\r\n" + 
						"                                        <stixCommon:Course_Of_Action id=\"example:coa-56af899b-a33c-4936-bd0c-a2738f8b0424\" timestamp=\"2019-04-12T11:54:53.178748+00:00\" xsi:type='coa:CourseOfActionType'>\r\n" + 
						"                                            <coa:Description>" + xmlIsFinishDo + "</coa:Description>\r\n" + 
						"                                        </stixCommon:Course_Of_Action>\r\n" + 
						"                                    </coa:Related_COA>\r\n" + 
						"                                </coa:Related_COAs>\r\n" + 
						"                            </incident:Course_Of_Action>\r\n" + 
						"                        </incident:COA_Taken>\r\n" + 
						"                    </stixCommon:Incident>\r\n" + 
						"                </incident:Related_Incident>\r\n" + 
						"            </incident:Related_Incidents>\r\n" + 
						"        </stix:Incident>\r\n" + 
						"    </stix:Incidents>\r\n" + 
						"</stix:STIX_Package>\r\n" + 
						"";
			} 

		} catch (Exception e) {
			//System.out.println(e.toString());
			responseJson.put("msg", "產生 STIX XML 失敗, 原因(" + e.getMessage() + ")");
			responseJson.put("id", notification.getId());
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "genStixXMLStep4And5", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}
		System.out.println("XMLSTEP45" + xmlStr);

		//System.out.println("genStixXMLStep4And5() → responseJson=" + responseJson);
		return xmlStr;
	}

	
	
	public boolean exportToNcert(String id) {
		//System.out.println("exportToNcert()");
		boolean result = false;
		Notification notification = notificationService.getById(id);
		JSONObject responseJson = new JSONObject();
		if (notification == null) {
			responseJson.put("msg", "找不到通報單資料");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "exportToNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		} else {
			int hostAmount = notification.getHostAmount(); // 受害資訊設備數量 host 總計
			int serverAmount = notification.getServerAmount(); // 受害資訊設備數量 server 總計
			int totalAmount = hostAmount + serverAmount; // 受害資訊設備數量 host + server 總計
			
			// 設定使用哪一組 xml 設定做介接之數字，提供後段程式使用
			int gotoStep = 0;
			
			if (notification.getEffectLevel() >= 1 && notification.getEffectLevel() <= 2) {
				gotoStep = 4; // 1、2結報
			} else if (notification.getEffectLevel() >= 3 && notification.getEffectLevel() <= 4) {
				gotoStep = 5; // 3、4結報
				
//				if (totalAmount > 1) {
//					gotoStep = 6; // 3、4結報(多個受害設備類型)
//				}
			
			}
			//System.out.println("exportToNcert() → gotoStep = " + gotoStep);
			
			// 測試用
			gotoStep = testGotoStep;
			
			try {
				String xmlStr = genStixXML(notification);
//				System.out.println("exportToNcert() → xmlStr = " + xmlStr);
				if (gotoStep == 1) {
					//System.out.println("exportToNcert() → Step 1");
					toNcert(notification.getId(), NCert.StatusFirst, xmlStr);
				} else if (gotoStep == 2) {
					//System.out.println("exportToNcert() → Step 2");
					toNcert(notification.getId(), NCert.StatusReEdit, xmlStr);
				} else if (gotoStep == 3) {
					//System.out.println("exportToNcert() → Step 3");
					toNcert(notification.getId(), NCert.StatusReSend, xmlStr);
				} else if (gotoStep == 4) {
					//System.out.println("exportToNcert() → Step 4");
					toNcert(notification.getId(), NCert.StatusClose12, xmlStr);
				} else if (gotoStep == 5) {
					//System.out.println("exportToNcert() → Step 5");
					toNcert(notification.getId(), NCert.StatusClose34, xmlStr);
				} 
//				else if (gotoStep == 6) {
//					System.out.println("exportToNcert() → Step 6");
//					toNcert(notification.getId(), NCert.StatusClose34Multiple, xmlStr);
//				}
				responseJson.put("msg", "通報單傳送成功");
				responseJson.put("id", id);
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "exportToNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			} catch (Exception e) {
				responseJson.put("msg", "找不到通報單資料, 原因(" + e.getMessage() + ")");
				responseJson.put("id", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "exportToNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
			//System.out.println("exportToNcert() → responseJson=" + responseJson);
		}
		return result;
	}

	public boolean exportToNcertStep1(String id, String opinion) {
		//System.out.println("exportToNcertStep1()");
		boolean result = false;
		Notification notification = notificationService.getById(id);
		JSONObject responseJson = new JSONObject();
		if (notification == null) {
			responseJson.put("msg", "找不到通報單資料");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "exportToNcertStep1", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		} else {
			try {
				String xmlStr = genStixXMLStep1(notification, opinion);

				//System.out.println("exportToNcertStep1() → Step 1");
				toNcert(notification.getId(), NCert.StatusFirst, xmlStr);
				
				responseJson.put("msg", "通報單傳送成功");
				responseJson.put("id", id);
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "exportToNcertStep1", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			} catch (Exception e) {
				responseJson.put("msg", "找不到通報單資料, 原因(" + e.getMessage() + ")");
				responseJson.put("xid", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "exportToNcertStep1", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
			//System.out.println("exportToNcertStep1() → responseJson=" + responseJson);
		}
		return result;
	}

	public boolean exportToNcertStep3(String id, int curStep) {
		//System.out.println("exportToNcertStep3()");
		boolean result = false;
		Notification notification = notificationService.getById(id);
		JSONObject responseJson = new JSONObject();
		if (notification == null) {
			responseJson.put("msg", "找不到通報單資料");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "exportToNcertStep3", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		} else {
			try {
				String xmlStr = genStixXMLStep3(notification, curStep);

				//System.out.println("exportToNcertStep3() → Step 3");
				toNcert(notification.getId(), NCert.StatusReSend, xmlStr);

				responseJson.put("msg", "通報單傳送成功");
				responseJson.put("id", id);
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "exportToNcertStep3", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			} catch (Exception e) {
				responseJson.put("msg", "找不到通報單資料, 原因(" + e.getMessage() + ")");
				responseJson.put("id", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "exportToNcertStep3", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
			//System.out.println("exportToNcertStep3() → responseJson=" + responseJson);
		}
		return result;
	}

	public boolean exportToNcertStep4And5(String id) {
		//System.out.println("exportToNcertStep4And5()");
		boolean result = false;
		Notification notification = notificationService.getById(id);
		JSONObject responseJson = new JSONObject();
		if (notification == null) {
			responseJson.put("msg", "找不到通報單資料");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Ncert", "exportToNcertStep4And5", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		} else {
			int hostAmount = notification.getHostAmount(); // 受害資訊設備數量 host 總計
			int serverAmount = notification.getServerAmount(); // 受害資訊設備數量 server 總計
			int totalAmount = hostAmount + serverAmount; // 受害資訊設備數量 host + server 總計
			
			// 設定使用哪一組 xml 設定做介接之數字，提供後段程式使用
			int gotoStep = 0;
			
			if (notification.getEffectLevel() >= 1 && notification.getEffectLevel() <= 2) {
				gotoStep = 4; // 1、2結報
			} else if (notification.getEffectLevel() >= 3 && notification.getEffectLevel() <= 4) {
				gotoStep = 5; // 3、4結報
				
//				if (totalAmount > 1) {
//					gotoStep = 6; // 3、4結報(多個受害設備類型)
//				}
			
			}
			//System.out.println("exportToNcert() → gotoStep = " + gotoStep);
			
			try {
				String xmlStr = genStixXMLStep4And5(notification);
//				System.out.println("exportToNcert() → xmlStr = " + xmlStr);
				if (gotoStep == 4) {
					//System.out.println("exportToNcert() → Step 4");
					toNcert(notification.getId(), NCert.StatusClose12, xmlStr);
				} else if (gotoStep == 5) {
					//System.out.println("exportToNcert() → Step 5");
					toNcert(notification.getId(), NCert.StatusClose34, xmlStr);
				} 
//				else if (gotoStep == 6) {
//					System.out.println("exportToNcert() → Step 6");
//					toNcert(notification.getId(), NCert.StatusClose34Multiple, xmlStr);
//				}
				responseJson.put("msg", "通報單傳送成功");
				responseJson.put("id", id);
				responseJson.put("success", true);
				systemLogService.insert("Ncert", "exportToNcertStep4And5", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			} catch (Exception e) {
				responseJson.put("msg", "找不到通報單資料, 原因(" + e.getMessage() + ")");
				responseJson.put("id", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "exportToNcertStep4And5", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
			//System.out.println("exportToNcertStep4And5() → responseJson=" + responseJson);
		}
		return result;
	}

	/**
	 * @param id
	 *            通報單編號
	 * @param status
	 *            通報單狀態 
	 *            1: 初報 
	 *            2: 初報編輯 
	 *            3: 續報 
	 *            4: 1/2級是件結報 
	 *            5: 3/4級事件結報
	 *            6: 3/4級事件結報(多個受害設備類型)
	 * @param xmlStr
	 *            Xml字串
	 * @return 成功傳回NCERT編號 / 失敗傳回空字串
	 */
	public String toNcert(String id, String status, String xmlStr) {
		//System.out.println("toNcert()");
		String resultStr = "";
		boolean initSuccess = initNcert();
		JSONObject responseJson = new JSONObject();
		if (initSuccess) {

			String content = NcertServices.removeXmlDeclaration(xmlStr);
			
			//System.out.println("toNcert() → ncertInboxUrl=" + ncertInboxUrl);
			//System.out.println("toNcert() → ncertId=" + ncertId);
			//System.out.println("toNcert() → ncertP=" + ncertP);
			//System.out.println("toNcert() → status=" + status);
			//System.out.println("toNcert() → content=" + content);
			
			try {
				// 測試機回傳網址有誤, 先寫死, 上線要註解掉
				//ncertInboxUrl="https://ncert.nccst.nat.gov.tw:8888/nserver/rest/services/inbox";
				
				String[] sendStringResult = ncertClient.callInbox(ncertInboxUrl, ncertId, ncertP, status, content);
				
				String statusCode = sendStringResult[0];
				String statusMsg = sendStringResult[1]; // NCERT 回傳之 CerId 要記錄在資料庫
				//System.out.println("toNcert() → statusCode=" + statusCode);
				//System.out.println("toNcert() → statusMsg=" + statusMsg);
				
				if (statusCode.equals("0101")) {
					resultStr = statusMsg;
					
					// 將 CertId 寫回 Notification Table 的 CertId 欄位
					notificationService.updateCertId(id, Long.valueOf(statusMsg));
					
					// 成功取得STIX通報單編號
					responseJson.put("msg", "傳送通報至 NCERT 成功");
					responseJson.put("CertId", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", true);
					systemLogService.insert("Ncert", "toNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				} else {
					// 0201 帳號密碼錯誤 / 0202 權限錯誤 / 0203 所有參數皆不能為空值
					// 0301 通報單格式錯誤 / 0302 通報類型不符
					// 0303 通報內容錯誤 / 0304 通報內容大小超過限制
					// 0401 taxii請求有誤 / 0402 使用不支援的傳輸協定 / 0403 使用不支援的taxii格式
					responseJson.put("msg", "傳送通報至 NCERT 失敗");
					responseJson.put("statusCode", statusCode);
					responseJson.put("statusMsg", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", false);
					systemLogService.insert("Ncert", "toNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				}
			} catch (NcertException ne) {
				String errorCode = ne.getErrorCode();
				String errorMsg = ne.getErrorMsg();
				responseJson.put("msg", "傳送通報至 NCERT 失敗");
				responseJson.put("errorCode", errorCode);
				responseJson.put("errorMsg", errorMsg);
				responseJson.put("id", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "toNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			} catch (Exception e) {
				//System.out.println("toNcert() → e=" + e);				
				responseJson.put("msg", "傳送通報至 NCERT 失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
				responseJson.put("id", id);
				responseJson.put("success", false);
				systemLogService.insert("Ncert", "toNcert", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			}
			//System.out.println("toNcert() → resultStr=" + resultStr);
			//System.out.println("toNcert() → responseJson=" + responseJson);
		}
		return resultStr;
	}
}
