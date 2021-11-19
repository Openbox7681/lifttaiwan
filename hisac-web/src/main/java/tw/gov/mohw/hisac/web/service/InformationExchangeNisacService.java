package tw.gov.mohw.hisac.web.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mitre.cybox.common_2.CustomPropertiesType;
import org.mitre.cybox.common_2.DatatypeEnum;
import org.mitre.cybox.common_2.DateTimeWithPrecisionType;
import org.mitre.cybox.common_2.Layer4ProtocolType;
import org.mitre.cybox.common_2.PositiveIntegerObjectPropertyType;
import org.mitre.cybox.common_2.Property;
import org.mitre.cybox.common_2.StringObjectPropertyType;
import org.mitre.cybox.common_2.TimeType;
import org.mitre.cybox.cybox_2.ObjectType;
import org.mitre.cybox.cybox_2.Observable;
import org.mitre.cybox.cybox_2.Observables;
import org.mitre.cybox.cybox_2.RelatedObjectType;
import org.mitre.cybox.cybox_2.RelatedObjectsType;
import org.mitre.cybox.objects.Address;
import org.mitre.cybox.objects.Artifact;
import org.mitre.cybox.objects.ArtifactTypeEnum;
import org.mitre.cybox.objects.CategoryTypeEnum;
import org.mitre.cybox.objects.Custom;
import org.mitre.cybox.objects.FileObjectType;
import org.mitre.cybox.objects.NetworkConnection;
import org.mitre.cybox.objects.PackagingType;
import org.mitre.cybox.objects.Port;
import org.mitre.cybox.objects.RawArtifactType;
import org.mitre.cybox.objects.SocketAddress;
import org.mitre.data_marking.extensions.markingstructure.TLPColorEnum;
import org.mitre.data_marking.extensions.markingstructure.TLPMarkingStructureType;
import org.mitre.data_marking.marking_1.MarkingSpecificationType;
import org.mitre.data_marking.marking_1.MarkingStructureType;
import org.mitre.data_marking.marking_1.MarkingType;
import org.mitre.stix.common_1.ConfidenceType;
import org.mitre.stix.common_1.ControlledVocabularyStringType;
import org.mitre.stix.common_1.IdentityType;
import org.mitre.stix.common_1.IncidentBaseType;
import org.mitre.stix.common_1.IndicatorBaseType;
import org.mitre.stix.common_1.InformationSourceType;
import org.mitre.stix.common_1.ReferencesType;
import org.mitre.stix.common_1.RelatedExploitTargetType;
import org.mitre.stix.common_1.RelatedIncidentType;
import org.mitre.stix.common_1.RelatedObservableType;
import org.mitre.stix.common_1.RelatedTTPType;
import org.mitre.stix.common_1.StructuredTextType;
import org.mitre.stix.courseofaction_1.CourseOfAction;
import org.mitre.stix.exploittarget_1.AffectedSoftwareType;
import org.mitre.stix.exploittarget_1.ExploitTarget;
import org.mitre.stix.exploittarget_1.VulnerabilityType;
import org.mitre.stix.extensions.identity.CIQIdentity30InstanceType;
import org.mitre.stix.extensions.identity.STIXCIQIdentity30Type;
import org.mitre.stix.incident_1.COATakenType;
import org.mitre.stix.incident_1.CategoriesType;
import org.mitre.stix.incident_1.ExternalIDType;
import org.mitre.stix.incident_1.ImpactAssessmentType;
import org.mitre.stix.incident_1.Incident;
import org.mitre.stix.incident_1.LeveragedTTPsType;
import org.mitre.stix.incident_1.RelatedIncidentsType;
import org.mitre.stix.incident_1.RelatedObservablesType;
import org.mitre.stix.indicator_2.Indicator;
import org.mitre.stix.stix_1.IncidentsType;
import org.mitre.stix.stix_1.STIXHeaderType;
import org.mitre.stix.stix_1.STIXPackage;
import org.mitre.stix.ttp_1.ExploitTargetsType;
import org.mitre.stix.ttp_1.InfrastructureType;
import org.mitre.stix.ttp_1.ResourceType;
import org.mitre.stix.ttp_1.TTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import nccst.nisac.NisacException;
import nccst.nisac.NisacServices;
import oasis.names.tc.ciq.xnl._3.PartyNameType;
import oasis.names.tc.ciq.xnl._3.PartyNameType.OrganisationName;
import oasis.names.tc.ciq.xpil._3.CommunicationMediaTypeList;
import oasis.names.tc.ciq.xpil._3.ContactNumbers;
import oasis.names.tc.ciq.xpil._3.ElectronicAddressIdentifierTypeList;
import oasis.names.tc.ciq.xpil._3.ElectronicAddressIdentifiers;
import tw.gov.mohw.hisac.web.UniversalNamespaceCache;
import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;

/**
 * 情資管理
 */
@Service
public class InformationExchangeNisacService {

	@Autowired
	private SystemLogService systemLogService;

	// 系統設定
	@Autowired
	private ResourceMessageService resourceMessageService;

	// 情資服務
	@Autowired
	private InformationExchangeService informationExchangeService;

	// 情資附件服務
	@Autowired
	private InformationExchangeAttachService informationExchangeAttachService;

	// 事件類型服務
	@Autowired
	private EventTypeService eventTypeService;

	// 警訊類型服務
	@Autowired
	private AlertTypeService alertTypeService;
	
	@Autowired
	private MailService mailService;
	
	final static Logger logger = LoggerFactory.getLogger(InformationExchangeNisacService.class);

	/* 初始化參數 */
	private String keyStore = WebConfig.NISAC_KEY_STORE; // "nisacClient.jks";
															// // 憑證檔路徑
	private String keyStoreP = WebConfig.NISAC_KEY_STORE_P;// "Hisac9988776"; //
															// 憑證檔P
	private String trustStore = WebConfig.NISAC_TRUST_STORE; // "trustStore.jks";
																// // 憑證信任庫路徑
	private String trustStoreP = WebConfig.NISAC_TRUST_STORE_P; // "NccstNisac";
																// // 憑證信任庫P

	/* Discovery Service 參數 */
	// 服務網址
	private String nisacUrl = WebConfig.NISAC_URL; // "https://nisac.nccst.nat.gov.tw/nserver/rest/services/discovery";
	private String nisacId = WebConfig.NISAC_MEMBER_ID; // "h-isac"; // 帳
	private String nisacP = WebConfig.NISAC_MEMBER_P; // "Hisac9988776"; // p

	private String hisacDcn = WebConfig.NISAC_MEMBER_DCN; // "107"; // 自身之會員代碼
	private String nisacDcn = "1"; // NISAC 會員代碼
	private String nisacDcn_512 = "35"; // NISAC 會員代碼 512拋轉用DCN

	//private String path = "e:/"; // 情資檔案儲存路徑

	private String nisacInboxUrl = ""; // 取得傳送情資服務網址
	private String nisacPollUrl = ""; // 取得接收情資服務網址

	private NisacServices nisacClient = null; // nisac service client

	// 設定是否只讀取未讀情資
	// 如設定為 false，則請設定讀取日期範圍
	private boolean unReadOnly = true;
	private String readRangeBeginDate = "2018-02-01";
	private String readRangeEndDate = "2018-05-30";
//	private boolean unReadOnly = false;
//	private String readRangeBeginDate = "2017-05-01";
//	private String readRangeEndDate = "2019-06-30";
	
	protected String mailBody = "";
	protected String mailSubject = "";
	protected boolean nisacSuccess = false;

	/**
	 * 設定 NISAC 服務客戶端物件 nisacClient nisac service client
	 * 
	 */
	private void setNisacService() {
		// 取得服務
		if (nisacClient == null) {
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				File fileKeyStore = new File(classLoader.getResource(keyStore).getFile());
				keyStore = fileKeyStore.getAbsolutePath();
				File fileTrustStore = new File(classLoader.getResource(trustStore).getFile());
				trustStore = fileTrustStore.getAbsolutePath();

				nisacClient = new NisacServices(keyStore, keyStoreP, trustStore, trustStoreP);

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 服務客戶端物件成功");
				responseJson.put("success", true);
				systemLogService.insert("Nisac", "setNisacService", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			} catch (Exception e) {
				// //e.printStackTrace();

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 服務客戶端物件失敗");
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "setNisacService", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
			}
		}

	} // end of method setNisacService()

	public void testInitNisac() {
		initNisac();
	}

	/**
	 * 設定 NISAC 相關參數 nisacClient nisac service client(by call setNisacService())
	 * nisacInboxUrl 傳送情資服務網址 nisacPollUrl 接收情資服務網址
	 * 
	 */
	private boolean initNisac() {

		if (nisacClient == null) {
			setNisacService();
		}

		if (nisacClient != null) {
			/* Discovery service */
			try {
				// 取得伺服器提供服務之網址
				Map<String, String> urls = nisacClient.callDiscovery(nisacUrl, nisacId, nisacP);
				nisacInboxUrl = urls.get("inboxUrl"); // 取得傳送情資服務網址
				nisacPollUrl = urls.get("pollUrl"); // 取得接收情資服務網址

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 伺服器提供服務之網址成功");
				responseJson.put("success", true);
				systemLogService.insert("Nisac", "initNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				return true;
			} catch (NisacException ne) {
				// 服務回傳之錯誤訊息，應實作相關紀錄與處理機制
				String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
				String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 伺服器提供服務之網址失敗");
				responseJson.put("errorCode", errorCode);
				responseJson.put("errorMsg", errorMsg);
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "initNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
				return false;
			} catch (Exception e) {
				// 發生其他錯誤，應實作相關紀錄與處理機制
				e.printStackTrace();

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 伺服器提供服務之網址失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "initNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
				return false;
			}
		} else {
			return false;
		}
	} // end of method initNisac()

	/**
	 * 傳送情資至 NISAC
	 * 
	 * @param id
	 *            事件編號
	 * @param category
	 *            事件類型代號
	 * @param xmlStr
	 *            情資 Stix XML
	 * 
	 * @return 成功 傳回NISAC情資編號/失敗傳回空字串
	 */
	public String toNisac(String id, String category, String xmlStr) {

		// 設定 NISAC 相關參數
		initNisac();

		// 回傳 String
		String resultStr = "";

		// XML 內容
		String content = NisacServices.removeXmlDeclaration(xmlStr);

		// 指定此筆情資之接收會員代碼 (可多筆)(目前只傳給 NISAC)
		List<String> toUnitIds ;
		
		
		if (category.equals("512")) {
			toUnitIds = Arrays.asList(nisacDcn_512);
		}else {
			// 指定此筆情資之接收會員代碼 (可多筆)(目前只傳給 NISAC)
			toUnitIds = Arrays.asList(nisacDcn);
		}

			

		try {
			// call service
			String sendStringResult[] = nisacClient.callInbox(nisacInboxUrl, nisacId, nisacP, toUnitIds, category, content);
			String statusCode = sendStringResult[0]; // 取得傳送結果代碼

			JSONObject responseJson = new JSONObject();

			String statusMsg = "";
			switch (statusCode) {
				case "0101" : // STIX情資檔案編號
					statusMsg = sendStringResult[1]; // 取得傳送結果訊息
					resultStr = statusMsg;

					// Log
					responseJson.put("msg", "傳送情資至 NISAC 成功");
					responseJson.put("IncidentId", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", true);
					systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");

					break;
				// case "0201": // 帳號密碼錯誤
				// break;
				// case "0202": // 憑證錯誤
				// break;
				// case "0203": // 權限錯誤
				// break;
				// case "0204": // 所有參數皆不能為空值
				// break;
				// case "0205": // 不允許此IP位址
				// break;
				// case "0301": // STIX格式錯誤
				// break;
				// case "0302": // 情報種類不符
				// break;
				// case "0303": // 事件類型不符
				// break;
				// case "0304": // 回饋情報內欲回報的編號不符
				// break;
				// case "0305": // 情資檔案大小超過限制
				// break;
				// case "0306": // 情資檔案重覆傳送
				// break;
				// case "0307": // 請求之情資編號貨數量有誤
				// break;
				// case "0308": // 時間區間有誤，請確認結束時間大於起始時間
				// break;

				default :
					// Log
					responseJson.put("msg", "傳送情資至 NISAC 失敗");
					responseJson.put("statusCode", statusCode);
					responseJson.put("statusMsg", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", false);
					systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

					break;
			}
		} catch (NisacException ne) {
			// 服務回傳之錯誤訊息，應實作相關紀錄與處理機制
			String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
			String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "傳送情資至 NISAC 失敗");
			responseJson.put("errorCode", errorCode);
			responseJson.put("errorMsg", errorMsg);
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

		} catch (Exception e) {
			// 發生其他錯誤，應實作相關紀錄與處理機制
			// //e.printStackTrace();

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "傳送情資至 NISAC 失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}

		// 成功 傳回NISAC情資編號/失敗傳回空字串
		return resultStr;
	}

	/**
	 * 取得 NISAC 情資編號清單
	 * 
	 * @return NISAC 情資編號清單
	 */
	public List<String> getIncidentIds() {

		/* 取得情資編號 */
		List<String> incidentIds = new ArrayList<>();

		try {
			if (unReadOnly) {
				// 取得未讀之情資編號
				incidentIds = nisacClient.callPollRecordId(nisacPollUrl, nisacId, nisacP, hisacDcn);
			} else {
				// 可設定時間區間作為條件，查詢情資編號 (欲使用時間區間查詢請解除下列第四行的註解，並註解上一行(取得未讀情資)程式碼)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startTime = sdf.parse(readRangeBeginDate); // 設定起始時間
				Date endTime = sdf.parse(readRangeEndDate); // 設定結束時間
				incidentIds = nisacClient.callPollRecordId(nisacPollUrl, nisacId, nisacP, hisacDcn, startTime, endTime);
			}

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "取得 NISAC 未讀之情資編號成功");
			responseJson.put("recordCount", incidentIds.size());
			responseJson.put("incidentIds", incidentIds.toString());
			responseJson.put("success", true);
			systemLogService.insert("Nisac", "getIncidentIds", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
			this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
		} catch (NisacException ne) {
			// 服務回傳之錯誤訊息，應實作相關紀錄與處理機制
			String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
			String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "取得 NISAC 未讀之情資編號失敗");
			responseJson.put("errorCode", errorCode);
			responseJson.put("errorMsg", errorMsg);
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "getIncidentIds", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			this.nisacSuccess = false;
		} catch (Exception e) {
			// 發生其他錯誤，應實作相關紀錄與處理機制
			// //e.printStackTrace();

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "取得 NISAC 未讀之情資編號失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "getIncidentIds", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			this.nisacSuccess = false;
		}

		// 傳回 NISAC 情資編號清單
		return incidentIds;

	} // end of method getIncidentIds()

	/**
	 * 依傳入的 NISAC 情資編號, 取得情資內容 XML
	 * 
	 * @param incidentId
	 *            NISAC 情資編號
	 * 
	 * @return 情資內容XML
	 */
	public String getStixXmlByIncidentId(String incidentId) {

		String stixContent = "";

		if (incidentId != null && !incidentId.equals("")) {

			try {
				// 取得情資內容字串
				stixContent = nisacClient.callPollContent(nisacPollUrl, nisacId, nisacP, hisacDcn, incidentId);

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 每筆情資編號之情資內容成功");
				responseJson.put("incidentId", incidentId);
				//responseJson.put("stixContent", stixContent); 
				responseJson.put("success", true);
				systemLogService.insert("Nisac", "getStixXmlByIncidentId", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				// // 將情資內容字串儲存成.xml檔案
				// (以下為使用提供之jar檔，將字串寫入.xml檔案的範例程式<以取回之情資編號作為檔名>)
				//NisacServices.saveXml(stixContent, path, incidentId); // 傳入欲儲存之字串與欲存檔案之路徑+檔名
				//
				// if ( isSuccess ) {
				// Log
//				responseJson = new JSONObject();
//				responseJson.put("msg", "取得 NISAC 每筆情資編號之情資內容成功");
//				responseJson.put("incidentId", incidentId);
//				responseJson.put("success", true);
//				systemLogService.insert("Nisac", "getStixXmlByIncidentId", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				// } else {
				// // Log
				// responseJson = new JSONObject();
				// responseJson.put("msg", "取得 NISAC 每筆情資編號之情資內容失敗, 檔案寫入錯誤");
				// responseJson.put("incidentId", incidentId);
				// responseJson.put("success", false);
				// systemLogService.insert("Nisac", "getStixXmlByIncidentId",
				// responseJson.toString(), SystemLogVariable.Action.Read,
				// SystemLogVariable.Status.Fail, "", "System");
				//
				// }

			} catch (NisacException ne) {
				// 服務回傳之錯誤訊息，應實作相關紀錄與處理機制
				String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
				String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 每筆情資編號之情資內容失敗");
				responseJson.put("errorCode", errorCode);
				responseJson.put("errorMsg", errorMsg);
				responseJson.put("incidentId", incidentId);
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "getStixXmlByIncidentId", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
			} catch (Exception e) {
				// 發生其他錯誤，應實作相關紀錄與處理機制
				// //e.printStackTrace();

				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "取得 NISAC 每筆情資編號之情資內容失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
				responseJson.put("incidentId", incidentId);
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "getStixXmlByIncidentId", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
			}
		}

		// 傳回情資內容 XML
		return stixContent;

	} // end of method getStixXmlByIncidentId()

	/**
	 * 解析 STIX XML 字串, 組成寫入資料庫所需之 JSON 格式字串
	 * 
	 * @param xmlStr
	 *            STIX XML 字串
	 * 
	 * @return 寫入資料庫所需之 JSON 格式字串
	 */
	public String parseStixXml(String xmlStr) {

		// 回傳之 JSON 格式字串
		String jsonString = "";
		
		// 情報資料 Json 物件
		JSONObject incidentJsonObj = new JSONObject();
		// 附件資料 Json 陣列
		JSONArray attachmentJsonArr = new JSONArray();

		try {
			// 進行一次編碼轉換, 確保編碼為 UTF-8
			//xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xmlStr;
			//byte[] byteText = xmlStr.getBytes(Charset.forName("UTF-8"));
			//xmlStr= new String(byteText, Charset.forName("UTF-8"));

	        // 原始 XML 字串
			String oriXmlStr = xmlStr;
	
			// for xpath
			XPathFactory xpathFactory = null;
			XPath xpath = null;
	
			// 由於參考資料區塊於 STIXPackage 讀取 XML 內容時即會報錯，於讀取前先移除參考資料區塊，單獨解析
			// 暫存參考資料欄位
			List<String> lstReference = new ArrayList<String>();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
            InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(xmlStr.getBytes()), Charset.forName(StandardCharsets.UTF_8.toString()));
			InputSource is = new InputSource(reader);
			is.setEncoding(StandardCharsets.UTF_8.toString());
			//is.setCharacterStream(new StringReader(xmlStr));
			Document xmlDoc = db.parse(is);

			xpathFactory = XPathFactory.newInstance();
			xpath = xpathFactory.newXPath();
			xpath.setNamespaceContext(new UniversalNamespaceCache(xmlDoc, true));

			XPathExpression xPathExpression = xpath.compile("//incident:Information_Source[1]/stixCommon:References[1]/stixCommon:Reference");
			NodeList nodeRef = (NodeList) xPathExpression.evaluate(xmlDoc, XPathConstants.NODESET);

			if (nodeRef != null && nodeRef.getLength() > 0) {
				for (int i = 0; i < nodeRef.getLength(); i++) {
					lstReference.add(nodeRef.item(i).getTextContent());
				}
			}

			if (lstReference.size() > 0) {
				// 移除參考資料區塊
				xPathExpression = xpath.compile("//incident:Information_Source[1]");
				Node nodeInformationSource = (Node) xPathExpression.evaluate(xmlDoc, XPathConstants.NODE);
				nodeInformationSource.getParentNode().removeChild(nodeInformationSource);

				// 重新產生 XML String
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				StreamResult result = new StreamResult(new StringWriter());
				DOMSource source = new DOMSource(xmlDoc);
				transformer.transform(source, result);
				xmlStr = result.getWriter().toString();
			}

			// xml 轉為 STIXPackage 物件
			STIXPackage stixPackage = null;
			stixPackage = STIXPackage.fromXMLString(xmlStr);

			// 驗證格式
			if (stixPackage.validate()) {

				int incidentCount = 0;
				
				int indicatorCount = 0;

				// 情報類型 ( same for all Incident )
				String stixTitle = "";
				if (stixPackage.getSTIXHeader() != null) {
					stixTitle = stixPackage.getSTIXHeader().getTitle().toString();
				}

				// 確認事件存在
				if (stixPackage.getIncidents() != null) {
					if (stixPackage.getIncidents().getIncidents() != null) {

						// 取得事件清單
						List<IncidentBaseType> incidents = stixPackage.getIncidents().getIncidents();

						// 事件數量
						incidentCount = incidents.size();

						// 開始處理每個事件 ( NISAC XML 應該只會放一則情報 )
						if (incidentCount > 0)
							incidentCount = 1; // 只取第1筆
						for (int i = 0; i < incidentCount; i++) {

							// reset json object
							incidentJsonObj = new JSONObject();

							// 原始 XML
							incidentJsonObj.put("SourceContentXml", oriXmlStr);							

							// 情報物件
							Incident incident = (Incident) incidents.get(i);
							
							if (stixTitle.equals("ANA") || stixTitle.equals("DEF") || stixTitle.equals("INT") ||
									stixTitle.equals("EWA") || stixTitle.equals("FBI") || stixTitle.equals("OTH")) {
								// 情報種類
								incidentJsonObj.put("StixTitle", stixTitle);
								// 事件主旨/情報名稱
								incidentJsonObj.put("IncidentTitle", stixPackage.getSTIXHeader().getDescriptions().get(0).getValue());
								// 內容說明/事件描述
								if (incident.getDescriptions() != null) {
									if (incident.getDescriptions().size() > 0) {
										incidentJsonObj.put("Description", incident.getDescriptions().get(0).getValue());
									}
								}
								
							}
							else {
								// 情報種類
								incidentJsonObj.put("StixTitle", "ANA");
								// 事件主旨/情報名稱
								incidentJsonObj.put("IncidentTitle", stixTitle);
								if (stixPackage.getIndicators().getIndicators() != null) {
									
									String description = "";

									// 取得惡意ip清單
									List<IndicatorBaseType> indicators = stixPackage.getIndicators().getIndicators();

									// 惡意ip數量
									indicatorCount = indicators.size();

									// 開始處理惡意ip 									
									for (int j = 0; j < indicatorCount; j++) {
										// 惡意ip物件
										Indicator indicator = (Indicator) indicators.get(j);
										description = description + "Description:" + indicator.getDescriptions().get(0).getValue() + "\n";
										Address address = (Address)indicator.getObservable().getObject().getProperties();										
										description =  description + "Address:" + address.getAddressValue().getValue() + "\n";
									}
									incidentJsonObj.put("Description", description);
								}

							}

							// 發送編號
							incidentJsonObj.put("IncidentId", incident.getId());
					
							// 事件時間處理
							if (incident.getTime() != null) {
								// 發現時間
								if (incident.getTime().getIncidentDiscovery() != null) {
									// 轉換為 DB 所需格式
									incidentJsonObj.put("IncidentDiscoveryTime", toDbDatetime(incident.getTime().getIncidentDiscovery().getValue().toString()));
								}
								// 發布時間
								if (incident.getTime().getIncidentReported() != null) {
									// 轉換為 DB 所需格式
									incidentJsonObj.put("IncidentReportedTime", toDbDatetime(incident.getTime().getIncidentReported().getValue().toString()));
								}
								// 解決時間
								if (incident.getTime().getIncidentClosed() != null) {
									// 轉換為 DB 所需格式
									incidentJsonObj.put("IncidentClosedTime", toDbDatetime(incident.getTime().getIncidentClosed().getValue().toString()));
								}
							}
							

							// 事件類型
							incidentJsonObj.put("Category", incident.getCategories().getCategories().get(0).getVocabName());

							// 發布單位
							incidentJsonObj.put("ReporterName", "NCCST");

							// 聯絡資訊 (姓名), 聯絡資訊 (電話), 聯絡資訊 (電子郵件)
//							CIQIdentity30InstanceType ciqIdentity = (CIQIdentity30InstanceType) incident.getResponders().get(0).getIdentity();
//							STIXCIQIdentity30Type ciqSpecification = ciqIdentity.getSpecification();
							incidentJsonObj.put("ResponderPartyName", "NCCST");
							incidentJsonObj.put("ResponderContactNumbers", "02-27391000");
							incidentJsonObj.put("ResponderElectronicAddressIdentifiers", "service@nccst.nat.gov.tw");

							// 影響等級
							String impact = (String) incident.getImpactAssessment().getImpactQualification().getValue();
							switch (impact) {
								case "1" :
									incidentJsonObj.put("ImpactQualification", "1");
									break;
								case "2" :
									incidentJsonObj.put("ImpactQualification", "2");
									break;
								case "3" :
									incidentJsonObj.put("ImpactQualification", "3");
									break;
								case "4" :
									incidentJsonObj.put("ImpactQualification", "4");
									break;
								default :
									incidentJsonObj.put("ImpactQualification", "1");
									break;
							}

							// 建議措施/解決辦法
							// (無法直接使用 STIXPackage 處理, 轉為 XMLDocument 處理)
							if (incident.getCOATakens() != null) {
								if (incident.getCOATakens().size() > 0) {
									if (incident.getCOATakens().get(0).getCourseOfAction() != null) {
										// COATakenType coaTakenType =
										// incident.getCOATakens().get(0);
										incidentJsonObj.put("CoaDescription", incident.getCOATakens().get(0).getCourseOfAction().toDocument().getElementsByTagName("coa:Description").item(0).getTextContent());
									}
								}
							}

							// 保密程度
							if (stixPackage.getSTIXHeader() != null) {
								incidentJsonObj.put("Confidence", ((TLPMarkingStructureType)stixPackage.getSTIXHeader().getHandling().getMarkings().get(0).getMarkingStructures().get(0)).getColor().toString());								
							}

							// 來源編號、時間
							if (incident.getRelatedIncidents() != null) {
								List<RelatedIncidentType> relatedIncidents = incident.getRelatedIncidents().getRelatedIncidents();
								int relatedIncidentCount = relatedIncidents.size();
								if (relatedIncidentCount > 0) {
									for (int j = 0; j < relatedIncidentCount; j++) {
										if (relatedIncidents.get(j).getIncident() != null) {
											// 來源編號
											incidentJsonObj.put("RelatedIncidentId", relatedIncidents.get(j).getIncident().getId());
											// 來源時間
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
											SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
											Date d;
											try {
												d = sdf.parse(relatedIncidents.get(j).getIncident().getTimestamp().toString());
												String formattedTime = output.format(d);
												incidentJsonObj.put("RelatedIncidentTimestamp", formattedTime);
											} catch (ParseException e) {
												// TODO Auto-generated catch
												// block
												// //e.printStackTrace();
											}
										}
									}
								}
							}

							// 參考資料
							if (lstReference.size() > 0) {
								// 多筆參考資料併入同一欄
								StringBuilder referenceStr = new StringBuilder();
								for (int k = 0; k < lstReference.size(); k++) {
									if (k != 0) {
										referenceStr.append("\n");
									}
									referenceStr.append(lstReference.get(k));
								}
								incidentJsonObj.put("Reference", referenceStr.toString());
							}

							// 此程式段落會有 anyURI 格式錯誤問題，前段已拆出另行處理
							// if ( incident.getInformationSource() != null ) {
							// if (
							// incident.getInformationSource().getReferences()
							// != null ) {
							// int referenceCount =
							// incident.getInformationSource().getReferences().getReferences().size();
							//
							// if ( referenceCount > 0 ) {
							// // 多筆參考資料併入同一欄
							// StringBuilder referenceStr = new StringBuilder();
							// for ( int k = 0; k < referenceCount; k++ ) {
							// if ( k != 0 ) {
							// referenceStr.append("\n");
							// }
							// referenceStr.append(incident.getInformationSource().getReferences().getReferences().get(k));
							// }
							// incidentJsonObj.put("Reference",
							// referenceStr.toString());
							// }
							// }
							// }

							// 附件 另 table
							// 受害IP ObservableIpAddress
							// C&C資訊(IP) SocketIpAddress
							// C&C資訊(通訊埠) SocketPort
							// C&C資訊(通訊埠協定) SocketProtocol
							// 中繼站資訊(IP) CustomIpAddress
							// 中繼站資訊(通訊埠) CustomPort
							// 中繼站資訊(通訊埠協定) CustomProtocol
							// 目的端資訊(IP) DestinationIpAddress
							// 目的端資訊(通訊埠) DestinationPort
							// 目的端資訊(通訊埠協定) DestinationProtocol
							if (incident.getRelatedObservables() != null) {

								if (incident.getRelatedObservables().getRelatedObservables() != null) {
									List<RelatedObservableType> relatedObservables = incident.getRelatedObservables().getRelatedObservables();

									int relatedObservablesCount = relatedObservables.size();
									StringBuilder observableIpAddressStr = new StringBuilder();
									for (int j = 0; j < relatedObservablesCount; j++) {
										Observable observable = (Observable) relatedObservables.get(j).getObservable();

										Object obj = observable.getObject().getProperties();
										Object relObj = observable.getObject().getRelatedObjects();

										if (obj instanceof FileObjectType) {
											// 附件
											// 多個附件處理為 JSON 陣列
											FileObjectType file = (FileObjectType) obj;
											JSONObject attachmentJsonObj = new JSONObject();
											attachmentJsonObj.put("FileName", file.getFileName().getValue());
											attachmentJsonObj.put("FileDesc", observable.getDescription().getValue());
											if (relObj instanceof RelatedObjectsType) {
												RelatedObjectsType rel = (RelatedObjectsType) relObj;
												if (rel.getRelatedObjects().get(0).getProperties() instanceof Artifact) {
													Artifact artifact = (Artifact) rel.getRelatedObjects().get(0).getProperties();
													attachmentJsonObj.put("FileType", artifact.getContentType());
													attachmentJsonObj.put("FileContent", artifact.getRawArtifact().getValue().toString().replaceFirst("<!\\[CDATA\\[", "").replaceFirst("\\]\\]>", ""));
												}
											}
											attachmentJsonArr.put(attachmentJsonObj);
										} else if (obj instanceof SocketAddress) {
											// C&C資訊(IP) SocketIpAddress
											// C&C資訊(通訊埠) SocketPort
											// C&C資訊(通訊埠協定) SocketProtocol
											SocketAddress socketAddress = (SocketAddress) obj;
											if (socketAddress.getIPAddress() != null) {
												if (socketAddress.getIPAddress() != null) {
													// C&C資訊(IP) SocketIpAddress
													incidentJsonObj.put("SocketIpAddress", socketAddress.getIPAddress().getAddressValue().getValue());
												}
												if (socketAddress.getPort() != null) {
													// C&C資訊(通訊埠) SocketPort
													incidentJsonObj.put("SocketPort", socketAddress.getPort().getPortValue().getValue());
													// C&C資訊(通訊埠協定)
													// SocketProtocol
													incidentJsonObj.put("SocketProtocol", socketAddress.getPort().getLayer4Protocol().getValue());
												}
											}

										} else if (obj instanceof Address) {
											// 受害IP
											// 多個受害IP, 以逗號分隔, 併入同一欄位
											Address address = (Address) obj;
											if (observableIpAddressStr.length() > 0) {
												observableIpAddressStr.append(",");
											}
											observableIpAddressStr.append(address.getAddressValue().getValue());
										} else if (obj instanceof NetworkConnection) {
											//
											NetworkConnection networkConnection = (NetworkConnection) obj;
											// 中繼站資訊(IP) CustomIpAddress
											// 中繼站資訊(通訊埠) CustomPort
											// 中繼站資訊(通訊埠協定) CustomProtocol
											List<Property> intermediatePropertys = networkConnection.getCustomProperties().getProperties();
											if (intermediatePropertys.size() > 0) {
												for (int k = 0; k < intermediatePropertys.size(); k++) {
													switch (intermediatePropertys.get(k).getName()) {
														case "IP" : // 中繼站資訊(IP)
																	// CustomIpAddress
															incidentJsonObj.put("CustomIpAddress", intermediatePropertys.get(k).getValue());
															break;
														case "Port" : // 中繼站資訊(通訊埠)
																		// CustomPort
															incidentJsonObj.put("CustomPort", intermediatePropertys.get(k).getValue());
															break;
														case "Protocol" : // 中繼站資訊(通訊埠協定)
																			// CustomProtocol
															incidentJsonObj.put("CustomProtocol", intermediatePropertys.get(k).getValue());
															break;
														default :
															// do nothing
															break;
													}
												}
											}
											// 目的端資訊(IP) DestinationIpAddress
											// 目的端資訊(通訊埠) DestinationPort
											// 目的端資訊(通訊埠協定) DestinationProtocol
											// networkConnection.getDestinationSocketAddress().getIPAddress().getAddressValue();
											if (networkConnection.getDestinationSocketAddress() != null) {
												if (networkConnection.getDestinationSocketAddress().getIPAddress() != null) {
													// 目的端資訊(IP)
													// DestinationIpAddress
													incidentJsonObj.put("DestinationIpAddress", networkConnection.getDestinationSocketAddress().getIPAddress().getAddressValue().getValue());
												}
												if (networkConnection.getDestinationSocketAddress().getPort() != null) {
													// 目的端資訊(通訊埠)
													// DestinationPort
													incidentJsonObj.put("DestinationPort", networkConnection.getDestinationSocketAddress().getPort().getPortValue().getValue());
													// 目的端資訊(通訊埠協定)
													// DestinationProtocol
													incidentJsonObj.put("DestinationProtocol", networkConnection.getDestinationSocketAddress().getPort().getLayer4Protocol().getValue());
												}
											}
										}
									}
									if (observableIpAddressStr.length() > 0) {
										// 受害IP
										incidentJsonObj.put("ObservableIpAddress", observableIpAddressStr);
									}
								}
							}

							// 手法研判 LeveragedDescription
							// 影響平台 AffectedSoftwareDescription
							// 來源IP ResourcesSourceIpAddress
							// 目標資訊(通訊埠) ResourcesDestinationPort
							// 目標資訊(通訊埠協定) ResourcesDestinationProtocol
							// 目標對象 ResourcesDestination
							// 掃描資訊(掃描引擎) ScanEngine
							// 掃描資訊(掃描版本) ScanVersion
							// 掃描資訊(掃描結果) ScanResult
							// (無法直接使用 STIXPackage 處理, 轉為 XMLDocument, XPath 處理)
							// @todo
							// 來源IP、目標資訊、掃描資訊等應均為手法研判下之資訊，且手法研判可能有多筆
							// 處理方式1: 將所有資訊併入手法研判欄位，Ex:
							// (手法研判說明...)\n
							// 影響平台:(....)\n
							// 來源IP：(....)\n
							// 目標對象：(....); 通訊埠:(....); 通訊埠協定埠:(....)\n
							// 掃描資訊:掃描引擎:(....); 掃描版本:(....); 掃描結果:()
							// 處理方式2: 增加欄位或子Table，紀錄多筆手法研判資料
							// String leveragedDescriptionStr = "";
							if (incident.getLeveragedTTPs() != null) {
								List<RelatedTTPType> leveragedTTPs = incident.getLeveragedTTPs().getLeveragedTTPs();

								int leveragedTTPCount = leveragedTTPs.size();
								if (leveragedTTPCount > 0)
									leveragedTTPCount = 1; // 只取第一筆
								for (int j = 0; j < leveragedTTPCount; j++) {
									Document doc = leveragedTTPs.get(j).toDocument();

									// 手法研判 LeveragedDescription
									try {
										// stixCommon:TTP/ttp:Description
										String exp = "//stixCommon:TTP/ttp:Description";
										xPathExpression = xpath.compile(exp);
										Node leveragedDescription = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
										if (leveragedDescription != null) {
											incidentJsonObj.put("LeveragedDescription", leveragedDescription.getTextContent()); // 手法研判
										}
									} catch (Exception e) {
										// do nothing
									}

									// 影響平台 AffectedSoftwareDescription
									try {
										// /stix:STIX_Package/stix:Incidents/stix:Incident/incident:Leveraged_TTPs/incident:Leveraged_TTP
										// /stixCommon:TTP/ttp:Exploit_Targets/ttp:Exploit_Target/stixCommon:Exploit_Target/et:Vulnerability/et:Affected_Software/et:Affected_Software/stixCommon:Observable/cybox:Description
										String exp = "//ttp:Exploit_Targets/ttp:Exploit_Target/stixCommon:Exploit_Target/et:Vulnerability/et:Affected_Software/et:Affected_Software/stixCommon:Observable/cybox:Description";
										xPathExpression = xpath.compile(exp);
										Node affectSoftware = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
										if (affectSoftware != null) {
											incidentJsonObj.put("AffectedSoftwareDescription", affectSoftware.getTextContent()); // 影響平台
										}
									} catch (Exception e) {
										// do nothing
									}

									// 來源IP ResourcesSourceIpAddress
									// 目標資訊(通訊埠) ResourcesDestinationPort
									// 目標資訊(通訊埠協定) ResourcesDestinationProtocol
									// 目標對象 ResourcesDestination
									// 掃描資訊(掃描引擎) ScanEngine
									// 掃描資訊(掃描版本) ScanVersion
									// 掃描資訊(掃描結果) ScanResult
									try {
										// 來源 ResourcesSourceIpAddress
										// stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable";
										// /cybox:Object/cybox:Properties/NetworkConnectionObj:Source_Socket_Address/SocketAddressObj:IP_Address/AddressObj:Address_Value";
										String exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable";
										xPathExpression = xpath.compile(exp);
										NodeList cyboxObservable = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
										if (cyboxObservable != null && cyboxObservable.getLength() > 0) {
											for (int k = 0; k < cyboxObservable.getLength(); k++) {
												// 來源IP ResourcesSourceIpAddress
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/NetworkConnectionObj:Source_Socket_Address/SocketAddressObj:IP_Address/AddressObj:Address_Value";
													xPathExpression = xpath.compile(exp);
													Node resourcesSourceIpAddress = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (resourcesSourceIpAddress != null) {
														incidentJsonObj.put("ResourcesSourceIpAddress", resourcesSourceIpAddress.getTextContent()); // 來源IP
													}
												} catch (Exception e) {
													// do nothing
												}

												// 目標對象 ResourcesDestination
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/NetworkConnectionObj:Destination_Socket_Address/cyboxCommon:Custom_Properties/cyboxCommon:Property";
													xPathExpression = xpath.compile(exp);
													Node resourcesDestination = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (resourcesDestination != null) {
														incidentJsonObj.put("ResourcesDestination", resourcesDestination.getTextContent()); // 目標對象
													}
												} catch (Exception e) {
													// do nothing
												}

												// 目標資訊(通訊埠)
												// ResourcesDestinationPort
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/NetworkConnectionObj:Destination_Socket_Address/SocketAddressObj:Port/PortObj:Port_Value";
													xPathExpression = xpath.compile(exp);
													Node resourcesDestinationPort = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (resourcesDestinationPort != null) {
														incidentJsonObj.put("ResourcesDestinationPort", resourcesDestinationPort.getTextContent()); // 目標資訊(通訊埠)
													}
												} catch (Exception e) {
													// do nothing
												}

												// 目標資訊(通訊埠協定)
												// ResourcesDestinationProtocol
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/NetworkConnectionObj:Destination_Socket_Address/SocketAddressObj:Port/PortObj:Layer4_Protocol";
													xPathExpression = xpath.compile(exp);
													Node resourcesDestinationProtocol = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (resourcesDestinationProtocol != null) {
														incidentJsonObj.put("ResourcesDestinationProtocol", resourcesDestinationProtocol.getTextContent()); // 目標資訊(通訊埠協定)
													}
												} catch (Exception e) {
													// do nothing
												}

												// 掃描資訊(掃描引擎) ScanEngine
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/cyboxCommon:Custom_Properties/cyboxCommon:Property[@name='Product']";
													xPathExpression = xpath.compile(exp);
													Node scanEngine = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (scanEngine != null) {
														incidentJsonObj.put("ScanEngine", scanEngine.getTextContent()); // 掃描資訊(掃描引擎)
													}
												} catch (Exception e) {
													// do nothing
												}

												// 掃描資訊(掃描版本) ScanVersion
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/cyboxCommon:Custom_Properties/cyboxCommon:Property[@name='Version']";
													xPathExpression = xpath.compile(exp);
													Node scanVersion = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (scanVersion != null) {
														incidentJsonObj.put("ScanVersion", scanVersion.getTextContent()); // 掃描資訊(掃描版本)
													}
												} catch (Exception e) {
													// do nothing
												}

												// 掃描資訊(掃描結果) ScanResult
												try {
													exp = "//stixCommon:TTP/ttp:Resources/ttp:Infrastructure/ttp:Observable_Characterization/cybox:Observable[" + (k + 1)
															+ "]/cybox:Object/cybox:Properties/cyboxCommon:Custom_Properties/cyboxCommon:Property[@name='Result']";
													xPathExpression = xpath.compile(exp);
													Node scanResult = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
													if (scanResult != null) {
														incidentJsonObj.put("ScanResult", scanResult.getTextContent()); // 掃描資訊(掃描結果)
													}
												} catch (Exception e) {
													// do nothing
												}

											}
										}
									} catch (Exception e) {
										// do nothing
									}
								}
							}

							// 其他參數
							incidentJsonObj.put("IsEnable", true); // 是否啟用
							incidentJsonObj.put("SourceCode", "2"); // 情報來源:
																	// NISAC
							incidentJsonObj.put("Status", "1"); // 狀態: 編輯中

						} // end of for each incident
					}
				} else {
					// "stixPackage.getIncidents() is null");
				}

				// "Incidents: %d%n", incidentCount);

				// 加入附件清單
				if (attachmentJsonArr.length() > 0) {
					incidentJsonObj.put("attachment", attachmentJsonArr);
				}
				// end of if (格式驗證通過)
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "解析 NISAC 情資 格式驗證成功");
				//responseJson.put("incidentId", incidentJsonObj.getString("IncidentId"));
				responseJson.put("success", true);
				//responseJson.put("parsedStixXML", incidentJsonObj.toString());
				systemLogService.insert("Nisac", "parseStixXml", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			} else {
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "解析 NISAC 情資 格式驗證失敗");
				//responseJson.put("incidentId", incidentJsonObj.getString("IncidentId"));
				responseJson.put("success", false);
				responseJson.put("parsedStixXML", incidentJsonObj.toString());
				systemLogService.insert("Nisac", "parseStixXml", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
			}
		} catch (Exception e) {
			// do nothing
			// //e.printStackTrace();
			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "解析 NISAC 情資失敗");
			//responseJson.put("incidentId", incidentJsonObj.getString("IncidentId"));
			responseJson.put("success", false);
			responseJson.put("errormsg", e.getMessage());
			responseJson.put("parsedStixXML", incidentJsonObj.toString());
			systemLogService.insert("Nisac", "parseStixXml", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			this.nisacSuccess = false;
			//logger.error(e.getMessage());
		}

		// 轉為 JSON 字串
		jsonString = incidentJsonObj.toString();

		// 傳回 JSON 格式字串
		return jsonString;

	} // end of method parseStixXml()

	/**
	 * 將解析後的 JSON 字串, 寫入情資資料庫
	 * 
	 * @param json
	 *            String
	 */
	private void toDB(String json) {

		if (json != null && !json.equals("")) {

			// 取出附件清單
			JSONObject obj = new JSONObject(json);
			JSONArray objArr = new JSONArray();
			try {
				// 取出附件清單
				objArr = obj.getJSONArray("attachment");
				// 移除附件清單
				obj.remove("attachment");
			} catch (JSONException e) {
				// do nothing
			}

			// 新增情資
			InformationExchange informationExchange = informationExchangeService.insert((long) 1, obj.toString());
			if (informationExchange != null) {
				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "接收 NISAC 情資成功");
				responseJson.put("incidentId", informationExchange.getIncidentId());
				responseJson.put("success", true);
				systemLogService.insert("Nisac", "importFromNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				// 處理附件
				if (objArr != null && objArr.length() > 0) {
					String informationExchangeId = informationExchange.getId();
					for (int i = 0; i < objArr.length(); i++) {
						JSONObject jsonAttach = objArr.getJSONObject(i);
						jsonAttach.put("InformationExchangeId", informationExchangeId);
						// 取得此附件字串以 base64 解碼後的 byte array
						byte[] bytes = Base64.getDecoder().decode(jsonAttach.getString("FileContent").getBytes(Charset.forName("UTF-8")));
						jsonAttach.remove("FileContent");
						// 檔案大小
						jsonAttach.put("FileSize", bytes.length);
						// 檔案Hash
						jsonAttach.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
						// 檔案類型
						String contentType = guessContentTypeFromByteArray(bytes);
						if (contentType == "")
							contentType = "application/octet-stream";
						jsonAttach.put("FileType", contentType);

						// file test
						// try {
						// FileUtils.writeByteArrayToFile(new
						// File("c:/STIX_Poll/"+jsonAttach.getString("FileName")),
						// bytes);
						// } catch (Exception e) {
						// // do nothing
						// }

						InformationExchangeAttach informationExchangeAttach = informationExchangeAttachService.insert((long) 0, jsonAttach.toString(), bytes);
						if (informationExchangeAttach != null) {
							// Log
							responseJson = new JSONObject();
							responseJson.put("msg", "接收 NISAC 情資成功, 新增附件成功");
							responseJson.put("incidentId", informationExchange.getIncidentId());
							responseJson.put("success", true);
							systemLogService.insert("Nisac", "importFromNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");
							this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
						} else {
							// Log
							responseJson = new JSONObject();
							responseJson.put("msg", "接收 NISAC 情資成功, 但新增附件失敗");
							responseJson.put("incidentId", informationExchange.getIncidentId());
							responseJson.put("success", false);
							systemLogService.insert("Nisac", "importFromNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
							this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
							this.nisacSuccess = false;
						}
					}
				}
			} else {
				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "接收 NISAC 情資失敗");
				responseJson.put("incidentId", obj.getString("IncidentId"));
				responseJson.put("success", false);
				systemLogService.insert("Nisac", "importFromNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
				this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
				this.nisacSuccess = false;
			}
		} else {
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "接收 NISAC 情資失敗");
			responseJson.put("incidentId", "");
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "importFromNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
			this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]" + responseJson.toString();
			this.nisacSuccess = false;
		}
	}

	/**
	 * [工具函數]: 時間格式轉換 2018-04-29T13:29:48.000+08:00 to 2018-04-29 13:29:48.000
	 * 失敗則傳回空字串
	 *
	 * @param datetimeStr
	 *            String
	 * @return 時間格式轉換
	 */
	public String toDbDatetime(String datetimeStr) {
		String formattedTime = "";
		// Ex.2018-04-29T13:29:48.000+08:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		// Ex.2018-04-29 13:29:48.000
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d;
		try {
			d = sdf.parse(datetimeStr);
			formattedTime = output.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// //e.printStackTrace();
		}

		return formattedTime;
	} // end of method toDbDatetime()

	/**
	 * [工具函數]: 時間格式轉換 2018-04-29 13:29:48.000 to 2018-04-29T13:29:48.000+08:00
	 * 失敗則傳回空字串
	 *
	 * @param datetimeStr
	 *            String
	 * @return 時間格式轉換
	 */
	public String toXMLDatetime(String datetimeStr) {
		String formattedTime = "";
		// Ex.2018-04-29T13:29:48.000+08:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		// Ex.2018-04-29 13:29:48.000
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Date d;
		try {
			d = sdf.parse(datetimeStr);
			formattedTime = output.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// //e.printStackTrace();
		}

		return formattedTime;
	} // end of method toXMLDatetime()

	/**
	 * [工具函數]: 傳入檔案 Byte 陣列, 傳回檔案類型(MimeType), 失敗則傳回空字串
	 * 
	 * @param bytes
	 *            檔案 Byte 陣列
	 * 
	 * @return String 檔案類型(MimeType)
	 */
	public String guessContentTypeFromByteArray(byte[] bytes) {
		String mimeType = "";
		try {
			InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
			mimeType = URLConnection.guessContentTypeFromStream(is);
		} catch (Exception e) {
			// do nothing
		}

		if (mimeType == null)
			mimeType = "";
		return mimeType;
	} // end of method guessContentTypeFromByteArray()
	
	
	private String optString(Object s) {
		return optString(s, "");
	}
	
	private String optString(Object s, String value) {
		return s != null && !s.equals("") ? s.toString() : value; 
	}
	
	

	/**
	 * 組合 512 STIX XML 情資
	 * 
	 * @param informationExchange
	 *            事件紀錄資料
	 * @return STIX XML
	 */
	private String gen512StixXML(InformationExchange informationExchange) {
		
		return String.join("\r\n",
				"				<stix:STIX_Package ",
				"				xmlns:AddressObj=\"http://cybox.mitre.org/objects#AddressObject-2\"",
				"				xmlns:cybox=\"http://cybox.mitre.org/cybox-2\"",
				"				xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"",
				"				xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\"",
				"				xmlns:SocketAddressObj=\"http://cybox.mitre.org/objects#SocketAddressObject-1\"",
				"				xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
				"				xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\"",
				"				xmlns:stix=\"http://stix.mitre.org/stix-1\"",
				"				xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\"",
				"				xmlns:incident=\"http://stix.mitre.org/Incident-1\"",
				"				xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"",
				"				xmlns:stixCommon=\"http://stix.mitre.org/common-1\"",
				"				xmlns:example=\"http://example.com\"",
				"				xmlns:PortObj=\"http://cybox.mitre.org/objects#PortObject-2\"",
				"				xmlns:NetworkConnectionObj=\"http://cybox.mitre.org/objects#NetworkConnectionObject-2\"",
				"				xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\"",
				"				xmlns:xlink=\"http://www.w3.org/1999/xlink\"",
				"				 id=\"example:Package-3190202e-f53e-4b8a-b674-b21bf79926ad\" version=\"1.2\">",
				"			    <stix:Incidents>",
				"			        <stix:Incident id=\"in事件單編號\" timestamp=\"結束時間\" xsi:type='incident:IncidentType'>",
				"			            <incident:Title>事件主旨</incident:Title>",
				"			            <incident:External_ID>事件單編號</incident:External_ID>",
				"			            <incident:Description>事件描述</incident:Description>",
				"			            <incident:Categories>",
				"			                <incident:Category>事件類別</incident:Category>",
				"			            </incident:Categories>",
				"			            <incident:Reporter>",
				"			                <stixCommon:Identity>",
				"			                    <stixCommon:Name>資安監控單位</stixCommon:Name>",
				"			                </stixCommon:Identity>",
				"			            </incident:Reporter>",
				"			            <incident:Victim id=\"單位代碼\" xsi:type=\"stix-ciqidentity:CIQIdentity3.0InstanceType\">",
				"			                <stixCommon:Name>單位名稱</stixCommon:Name>",
				"			                <stix-ciqidentity:Specification xmlns:stix-ciqidentity=\"http://stix.mitre.org/extensions/Identity#CIQIdentity3.0-1\">",
				"			  <xpil:OrganisationInfo xmlns:xpil=\"urn:oasis:names:tc:ciq:xpil:3\" xpil:IndustryType=\"單位所屬領域/區域\"/>",
				"			</stix-ciqidentity:Specification>",
				"			            </incident:Victim>",
				"			            <incident:Impact_Assessment>",
				"			                <incident:Impact_Qualification>影響等級</incident:Impact_Qualification>",
				"			            </incident:Impact_Assessment>",
				"			            <incident:Related_Observables>",
				"			                <incident:Related_Observable>",
				"			                    <stixCommon:Observable id=\"example:Observable-116b023d-2d87-4e71-8f48-bb29d8b72d88\">",
				"			                        <cybox:Event>",
				"			                            <cybox:Actions>",
				"			                                <cybox:Action>",
				"			                                    <cybox:Description>觸發規則</cybox:Description>",
				"			                                    <cybox:Discovery_Method>",
				"			                                        <cyboxCommon:Tool_Type>資安防護類型</cyboxCommon:Tool_Type>",
				"			                                        <cyboxCommon:Time>",
				"			                                            <cyboxCommon:Start_Time>開始時間</cyboxCommon:Start_Time>",
				"			                                            <cyboxCommon:End_Time>結束時間</cyboxCommon:End_Time>",
				"			                                        </cyboxCommon:Time>",
				"			                                        <cyboxCommon:Tools>",
				"			                                            <cyboxCommon:Tool>",
				"			                                                <cyboxCommon:Name>設備代號</cyboxCommon:Name>",
				"			                                                <cyboxCommon:Vendor>設備廠商</cyboxCommon:Vendor>",
				"			                                            </cyboxCommon:Tool>",
				"			                                        </cyboxCommon:Tools>",
				"			                                    </cybox:Discovery_Method>",
				"			                                    IP資訊",
				"			                                    <cybox:Frequency units=\"觸發事件量\"/>",
				"			                                </cybox:Action>",
				"			                            </cybox:Actions>",
				"			                        </cybox:Event>",
				"			                    </stixCommon:Observable>",
				"			                </incident:Related_Observable>",
				"			            </incident:Related_Observables>",
				"                                           INCIDENTID資訊",
				//"			            <incident:Related_Incidents>",
				//"			                <incident:Related_Incident>",
				//"			                    <stixCommon:Incident id=\"相關事件單編號\" timestamp=\"結束時間\" xsi:type='incident:IncidentType'/>",
				//"			                </incident:Related_Incident>",
				//"			            </incident:Related_Incidents>",
				"			        </stix:Incident>",
				"			    </stix:Incidents>",
				"			</stix:STIX_Package>")
							//必填欄位：事件主旨、事件描述、發現時間、解決時間
							.replace("in事件單編號", optString(informationExchange.getId()))
							.replace("事件主旨", optString(informationExchange.getIncidentTitle(), "無"))
							.replace("相關事件單編號", "事件單編號")
							.replace("事件單編號", optString(informationExchange.getPostId()).replace("TEMP-", ""))
							.replace("事件描述", optString(informationExchange.getDescription(), "無"))
							.replace("事件類別", optString(getCategoryName(informationExchange.getCategory()), "尚需調查類")) // 檢查類別:尚需調查類、入侵攻擊類、系統服務類、阻斷服務類、惡意程式類、政策規則類、掃描刺探類、政策規則類、經同意之網路攻防演練類 
							.replace("資安監控單位", "數聯資安")
							// 查OID https://oid.nat.gov.tw/OIDWeb/
							.replace("單位代碼", "2.16.886.101.90026.20002") // 新北市政府
							.replace("單位名稱", "新北市政府")
							.replace("單位所屬領域/區域", "中央及地方政府") // 目前的關鍵基礎設施包括能源、水資源、通訊傳播、交通、銀行與金融、緊急救援與醫院、中央與地方政府機關、高科技園區等八大領域
							.replace("影響等級", optString(informationExchange.getImpactQualification()))
							.replace("觸發規則", optString(informationExchange.getIncidentDescription(),informationExchange.getIncidentTitle()))
							.replace("資安防護類型", optString(informationExchange.getIncidentToolType(),"SIEM"))
							// 時間必填
							.replace("開始時間", informationExchange.getIncidentDiscoveryTime() != null ? toXMLDatetime(informationExchange.getIncidentDiscoveryTime().toString()) : " ")
							.replace("結束時間", informationExchange.getIncidentClosedTime() != null ? toXMLDatetime(informationExchange.getIncidentClosedTime().toString()) : " ")
							.replace("設備代號",  optString(informationExchange.getIncidentName(),"DeviceName"))
							.replace("設備廠商", optString(informationExchange.getIncidentVendor(),"中華資安"))
							.replace("IP資訊", genAssociatedObjects(informationExchange))
							.replace("觸發事件量", "1")
							.replace("INCIDENTID資訊", genRelatedIncidents(informationExchange))
							;
		
	}
	
	
public String  getCategoryName(String category) {
		
		Map<String, Set<String>> MAPPING = new HashMap<>();

		
		Set<String> temp1 = new HashSet<>();
		temp1.add("201");
		temp1.add("202");
		temp1.add("203");
		temp1.add("204");
		temp1.add("206");
		temp1.add("301");
		temp1.add("304");
		temp1.add("307");
		temp1.add("308");
		temp1.add("309");
		temp1.add("310");
		temp1.add("311");
		temp1 = Collections.unmodifiableSet(temp1);
		MAPPING.put("入侵攻擊類", temp1);
		
		Set<String> temp2 = new HashSet<>();
		temp2.add("205");
		temp2.add("207");
		temp2.add("302");
		temp2.add("305");
		temp2.add("306");
		temp2.add("401");
		temp2.add("403");
		temp2.add("405");
		temp2.add("406");
		temp2.add("407");
		temp2.add("409");
		temp2.add("411");
		temp2 = Collections.unmodifiableSet(temp2);
		MAPPING.put("尚需調查類", temp2);
		
		if(temp1.contains(category)) {
			return "入侵攻擊類";
		}else if (temp2.contains(category))
			return "尚需調查類";
		else
			return "尚需調查類";			
	}

	
	//組合 Associated_Objects START
	
	@SuppressWarnings("null")
	private String genAssociatedObjects(InformationExchange informationExchange) {
		String Associated_Objects = "";
		


		
		String AssociatedObjectsStr = informationExchange.getAssociatedObjected();
		
		try {
			
		
		if (AssociatedObjectsStr!= null || !AssociatedObjectsStr.isEmpty()) {
		
		Associated_Objects =  Associated_Objects + "                                    <cybox:Associated_Objects>\n"; 

		
		JSONArray associated_Object_array = new JSONArray(AssociatedObjectsStr);
		
		for (Object data : associated_Object_array) {
			JSONObject associated_Object = new JSONObject(data.toString());	
			Associated_Objects = Associated_Objects + 
"										<cybox:Associated_Object id=\""  + associated_Object.optString("SOC2Id" , " ") +"\">\n"+ 
"                                            <cybox:Properties xsi:type=\"NetworkConnectionObj:NetworkConnectionObjectType\">\n" + 
"                                                <NetworkConnectionObj:Source_Socket_Address xsi:type=\"SocketAddressObj:SocketAddressObjectType\">\n" +
"                                                    <SocketAddressObj:IP_Address xsi:type=\"AddressObj:AddressObjectType\">\n" + 
"                                                        <AddressObj:Address_Value>" + associated_Object.optString("SourceIp") + "</AddressObj:Address_Value>\n" +
"                                                    </SocketAddressObj:IP_Address>\n" + 
"                                                    <SocketAddressObj:Hostname xsi:type=\"HostnameObj:HostnameObjectType\">\n" +
"                                                        <HostnameObj:Hostname_Value>" + associated_Object.optString("SourceHostName" , "")  +"</HostnameObj:Hostname_Value>\n" +  
"                                                    </SocketAddressObj:Hostname> \n" +
"                                                    <SocketAddressObj:Port xsi:type=\"PortObj:PortObjectType\">\n" + 
"                                                        <PortObj:Port_Value>" +  associated_Object.optInt("SourcePort") + "</PortObj:Port_Value>\n" +
"                                                    </SocketAddressObj:Port>\n" + 
"                                                </NetworkConnectionObj:Source_Socket_Address>\n" + 
"                                                <NetworkConnectionObj:Destination_Socket_Address xsi:type=\"SocketAddressObj:SocketAddressObjectType\">\n" +
"                                                    <SocketAddressObj:IP_Address xsi:type=\"AddressObj:AddressObjectType\">\n" +  
"                                                        <AddressObj:Address_Value>" + associated_Object.optString("DestinationIp") +  "</AddressObj:Address_Value>\n" +
"                                                    </SocketAddressObj:IP_Address>\n" + 
"                                                    <SocketAddressObj:Hostname xsi:type=\"HostnameObj:HostnameObjectType\">\n" +
"                                                        <HostnameObj:Hostname_Value>" + associated_Object.optString("DestinationHostName" , "")  +"</HostnameObj:Hostname_Value>\n" +  
"                                                    </SocketAddressObj:Hostname> \n" +
"                                                    <SocketAddressObj:Port xsi:type=\"PortObj:PortObjectType\">\n" + 
"                                                        <PortObj:Port_Value>" + associated_Object.optInt("DestinationPort") +  "</PortObj:Port_Value>\n" +
"                                                    </SocketAddressObj:Port>\n" + 
"                                                </NetworkConnectionObj:Destination_Socket_Address>\n" + 
"                                            </cybox:Properties>\n" + 
"                                        </cybox:Associated_Object>\n"; 
	}
		
		Associated_Objects =  Associated_Objects + "                                    </cybox:Associated_Objects>\n" ; 

		}else {
			Associated_Objects = "";
		}
		// System.out.println(Associated_Objects);
		return Associated_Objects;
		
		
			}catch(Exception e) {
				// e.printStackTrace();
				Associated_Objects = "";
				// System.out.println(Associated_Objects);
				return Associated_Objects;
				
				
			}
	}
	
	
	//組合related incidents START
	
		private String genRelatedIncidents(InformationExchange informationExchange) {
			String RelatedIncidents = "";
			String strRelatedIncidentIds = informationExchange.getIncidentId();
			
			if (strRelatedIncidentIds!=null ){

				
				RelatedIncidents = RelatedIncidents + "<incident:Related_Incidents> \n";
				for (String incidentId : strRelatedIncidentIds.split(",")) {
					String IncidentClosedTime  = informationExchange.getIncidentClosedTime() != null ? toXMLDatetime(informationExchange.getIncidentClosedTime().toString()) : " "; 
					
					RelatedIncidents = RelatedIncidents +
	"								<incident:Related_Incident>\n" +
	"			                    <stixCommon:Incident id=\"" + incidentId +"\" timestamp= \"" +IncidentClosedTime +"\" xsi:type='incident:IncidentType'/>\n" +
	"			                </incident:Related_Incident>\n";
					;

							
		
					
				}
				
				RelatedIncidents = RelatedIncidents+ "								</incident:Related_Incidents> \n";
				
				
				
				return RelatedIncidents;

				
			}else {
				RelatedIncidents = 
						
						"			            <incident:Related_Incidents>\n" +
						"			                <incident:Related_Incident>\n" +
						"			                    <stixCommon:Incident id=\"相關事件單編號\" timestamp=\"結束時間\" xsi:type='incident:IncidentType'/>\n" +
						"			                </incident:Related_Incident>\n" +
						"			            </incident:Related_Incidents>\n";
						
				return RelatedIncidents;

			}
		}
		
	
	
	
	

	/**
	 * 組合 STIX XML 情資
	 * 
	 * @param id
	 *            事件紀錄編號
	 * @return STIX XML
	 */
	@SuppressWarnings("serial")
	public String genStixXML(String id) {
		// 準備回傳字串
		String xmlStr = "";

		// 取得紀錄
		InformationExchange informationExchange = informationExchangeService.getById(id);

		if (informationExchange != null) {
			
			// 512資料轉換 格式轉換
			if (informationExchange.getCategory() != null) {
				
				if (informationExchange.getCategory().equals("512")) {
					String str512xml = gen512StixXML(informationExchange);
					return str512xml;
				}
				
				
			}

				
			
			
			
			
			// informationExchange

			if (informationExchange.getStixTitle() != null && !informationExchange.getStixTitle().equals("") && informationExchange.getCategory() != null && !informationExchange.getCategory().equals("")) {

				// 取得警訊名稱
				AlertType alertType = alertTypeService.getByCode(informationExchange.getStixTitle());
				String stixTitle = alertType.getName();

				// 取得事件名稱
				EventType eventType = eventTypeService.findByCode(informationExchange.getCategory());
				String category = eventType.getName();

				try {
					// Get time for now.
					XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("UTC")));

					// 影響等級
					Map<String, String> impacts = new HashMap<String, String>();
					impacts.put("1", "1");
					impacts.put("2", "2");
					impacts.put("3", "3");
					impacts.put("4", "4");

					// 連絡電話
					ContactNumbers contactNumbers = new ContactNumbers().withContactNumbers(new ContactNumbers.ContactNumber().withCommunicationMediaType(CommunicationMediaTypeList.TELEPHONE)
							.withContactNumberElements(new ContactNumbers.ContactNumber.ContactNumberElement().withValue(resourceMessageService.getMessageValue("globalFooterTelText"))));

					// E-mail
					ElectronicAddressIdentifiers electronicAddressIdentifiers = new ElectronicAddressIdentifiers()
							.withElectronicAddressIdentifiers(new ElectronicAddressIdentifiers.ElectronicAddressIdentifier().withType(ElectronicAddressIdentifierTypeList.EMAIL).withValue(resourceMessageService.getMessageValue("globalFooterEmail")));

					// 單位名稱
					PartyNameType partyName = new PartyNameType().withOrganisationNames(new OrganisationName().withNameElements(new oasis.names.tc.ciq.xnl._3.OrganisationNameType.NameElement().withValue("ISAC")));

					// CIQ specification
					STIXCIQIdentity30Type specification = new STIXCIQIdentity30Type().withPartyName(partyName).withContactNumbers(contactNumbers).withElectronicAddressIdentifiers(electronicAddressIdentifiers);

					// CIQ identity
					CIQIdentity30InstanceType identity = new CIQIdentity30InstanceType().withSpecification(specification);

					// 事件
					final Incident incident = new Incident();
					// 基本資料
					if (informationExchange.getDescription()==null || informationExchange.getDescription().equals("")) {						
						incident
						// 警訊編號 PostId
						.withId(new QName(informationExchange.getPostId())).withTimestamp(now)
						// 外部編號
						.withExternalIDs(new ExternalIDType().withValue(informationExchange.getPostId()))						
						// 事件類型 Category
						.withCategories(new CategoriesType().withCategories(new ControlledVocabularyStringType().withVocabName(informationExchange.getCategory()).withValue(category)))						
						// 影響等級 ImpactQualification
						.withImpactAssessment(new ImpactAssessmentType().withImpactQualification(new ControlledVocabularyStringType().withValue(impacts.get(informationExchange.getImpactQualification().toString()))));
					}
					else {
					incident
							// 警訊編號 PostId
							.withId(new QName(informationExchange.getPostId())).withTimestamp(now)
							// 外部編號
							.withExternalIDs(new ExternalIDType().withValue(informationExchange.getPostId()))
							// 內容說明/事件描述 Description
							.withDescriptions(new StructuredTextType().withValue(informationExchange.getDescription()))
							// 事件類型 Category
							.withCategories(new CategoriesType().withCategories(new ControlledVocabularyStringType().withVocabName(informationExchange.getCategory()).withValue(category)))						
							// 影響等級 ImpactQualification
							.withImpactAssessment(new ImpactAssessmentType().withImpactQualification(new ControlledVocabularyStringType().withValue(impacts.get(informationExchange.getImpactQualification().toString()))));
					}
					if ((informationExchange.getStixTitle().equals("EWA"))){
						incident
							// 事件主旨/情報名稱 IncidentTitle
							.withTitle(informationExchange.getIncidentTitle())
							// 發布單位 ReporterName
							.withReporter(new InformationSourceType().withIdentity(new IdentityType().withName("ISAC")))
							// 應變單位 ResponderPartyName, ResponderContactNumbers,
							// ResponderElectronicAddressIdentifiers
							.withResponders(new InformationSourceType().withIdentity(identity));
					}
					// 事件相關時間
					org.mitre.stix.incident_1.TimeType timeType = new org.mitre.stix.incident_1.TimeType();
					//是否有時間欄位
					boolean isTimeType = false;

					// 發現時間 IncidentDiscoveryTime
					if (informationExchange.getIncidentDiscoveryTime() != null) {
						
						//有時間欄位
						isTimeType = true;

						XMLGregorianCalendar incidentDiscoveryTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(toXMLDatetime(informationExchange.getIncidentDiscoveryTime().toString()));

						timeType.withIncidentDiscovery(new org.mitre.stix.common_1.DateTimeWithPrecisionType(incidentDiscoveryTime, null));
					}
					// 發送時間 IncidentReportedTime
					if (informationExchange.getIncidentReportedTime() != null) {

						//有時間欄位
						isTimeType = true;
						
						XMLGregorianCalendar incidentReportedTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(toXMLDatetime(informationExchange.getIncidentReportedTime().toString()));

						timeType.withIncidentReported(new org.mitre.stix.common_1.DateTimeWithPrecisionType(incidentReportedTime, null));
					}
					// 解決時間 IncidentClosedTime
					if (informationExchange.getIncidentClosedTime() != null) {

						//有時間欄位
						isTimeType = true;
						
						XMLGregorianCalendar incidentClosedTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(toXMLDatetime(informationExchange.getIncidentClosedTime().toString()));

						timeType.withIncidentClosed(new org.mitre.stix.common_1.DateTimeWithPrecisionType(incidentClosedTime, null));
					}
					
					//是否有時間欄位
					if (isTimeType)
						// 將事件相關時間加入事件內
						incident.withTime(timeType);

					// relatedObservables
					List<RelatedObservableType> relatedObservableTypes = new ArrayList<RelatedObservableType>();

					// 附件
					List<InformationExchangeAttach> informationExchangeAttachs = informationExchangeAttachService.getByInformationExchangeId(informationExchange.getId());
					if (informationExchangeAttachs != null) {
						int attachCounter = 1;
						for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachs) {
							// Description
							String fileDesc = "附件";
							if (informationExchangeAttachs.size() > 1) {
								fileDesc = fileDesc + attachCounter;
								attachCounter++;
							}
							// 取出副檔名作為 ContentType
							String filename = informationExchangeAttach.getFileName();
							String extname = filename.substring(filename.lastIndexOf(".") + 1);
							// 組合附件 XML 格式
							RelatedObservableType relatedObservable = new RelatedObservableType();
							relatedObservable.withObservable(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType().withValue(fileDesc))
									.withObject(new ObjectType().withProperties(new FileObjectType().withFileName(new StringObjectPropertyType().withValue(filename)))
											.withRelatedObjects(new RelatedObjectsType().withRelatedObjects(new RelatedObjectType().withProperties(new Artifact().withContentType(extname).withType(ArtifactTypeEnum.FILE).withPackaging(new PackagingType().withIsEncrypted(false))
													.withRawArtifact(new RawArtifactType().withDatatype(DatatypeEnum.STRING)
															// 附件內容 byte[] to base64
															// encode
															.withValue(Base64.getEncoder().encodeToString(informationExchangeAttach.getFileContent()))))))));
							
							/*
							
							new RelatedObjectType().withProperties(new Artifact().withContentType(extname).withType(ArtifactTypeEnum.FILE).withPackaging(new PackagingType().withIsEncrypted(false))
									.withRawArtifact(new RawArtifactType().withDatatype(DatatypeEnum.STRING)
											// 附件內容 byte[] to base64
											// encode
											.withValue(Base64.getEncoder().encodeToString(informationExchangeAttach.getFileContent()))))
							
							*/
																			
							// add to relatedObservableTypes
							relatedObservableTypes.add(relatedObservable);
						} // end of for 情資附件
					}

					// 受害IP ObservableIpAddress
					if (informationExchange.getObservableIpAddress() != null && !informationExchange.getObservableIpAddress().equals("")) {
						String ip = "0.0.0.0";
						String port = "0";
						String protocol = "None";
						if (informationExchange.getObservableIpAddress().split("/").length == 3) {
							ip = informationExchange.getObservableIpAddress().split("/")[0];
							port = informationExchange.getObservableIpAddress().split("/")[1];
							protocol = informationExchange.getObservableIpAddress().split("/")[2];
						}
						RelatedObservableType relatedObservable = new RelatedObservableType();
						relatedObservable.withObservable(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType().withValue("受害IP"))
								.withObject(new ObjectType().withProperties(new SocketAddress().withIPAddress(
										new Address().withCategory(CategoryTypeEnum.IPV_4_ADDR).withAddressValue(new StringObjectPropertyType()
										// 受害IP ObservableIpAddress
										.withValue(ip)))
										
										.withPort(new Port().withPortValue(new PositiveIntegerObjectPropertyType()
												.withValue(port)).withLayer4Protocol(new Layer4ProtocolType()
														.withValue(protocol))))));
						// add to relatedObservableTypes
						relatedObservableTypes.add(relatedObservable);
						//withCategory(CategoryTypeEnum.IPV_4_ADDR)
					}

					// for 來源、中繼、目的
					NetworkConnection networkConnection = new NetworkConnection();

					// C&C資訊(IP) SocketIpAddress
					// C&C資訊(通訊埠) SocketPort
					// C&C資訊(通訊埠協定) SocketProtocol
					if (informationExchange.getSocketIpAddress() != null && !informationExchange.getSocketIpAddress().equals("") && informationExchange.getSocketPort() != null && !informationExchange.getSocketPort().equals("")
							&& informationExchange.getSocketProtocol() != null && !informationExchange.getSocketProtocol().equals("")) {

						networkConnection.withSourceSocketAddress(new SocketAddress().withIPAddress(new Address().withCategory(CategoryTypeEnum.IPV_4_ADDR).withAddressValue(new StringObjectPropertyType()
								// C&C資訊(IP) SocketIpAddress
								.withValue(informationExchange.getSocketIpAddress()))).withPort(new Port()
										// C&C資訊(通訊埠) SocketPort
										.withPortValue(new PositiveIntegerObjectPropertyType().withValue(informationExchange.getSocketPort()))
										// C&C資訊(通訊埠協定) SocketProtocol
										.withLayer4Protocol(new Layer4ProtocolType().withValue(informationExchange.getSocketProtocol()))));
					}

					// 中繼站資訊(IP) CustomIpAddress
					// 中繼站資訊(通訊埠) CustomPort
					// 中繼站資訊(通訊埠協定) CustomProtocol
					if (informationExchange.getCustomIpAddress() != null && !informationExchange.getCustomIpAddress().equals("") && informationExchange.getCustomPort() != null && !informationExchange.getCustomPort().equals("")
							&& informationExchange.getCustomProtocol() != null && !informationExchange.getCustomProtocol().equals("")) {

						networkConnection.withCustomProperties(new CustomPropertiesType().withProperties(new Property().withName("Category").withValue("intermediate"), new Property().withName("AddressCategory").withValue("ipv4-addr")
						// 中繼站資訊(IP) CustomIpAddress
								, new Property().withName("IP").withValue(informationExchange.getCustomIpAddress())
								// 中繼站資訊(通訊埠) CustomPort
								, new Property().withName("Port").withValue(informationExchange.getCustomPort())
								// 中繼站資訊(通訊埠協定) CustomProtocol
								, new Property().withName("Protocol").withValue(informationExchange.getCustomProtocol())));
					}					

					// 若來源或中繼或目的存在
					if (networkConnection.getSourceSocketAddress() != null || networkConnection.getCustomProperties() != null || networkConnection.getDestinationSocketAddress() != null) {

						RelatedObservableType relatedObservable = new RelatedObservableType();
						relatedObservable.withObservable(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType().withValue("NetworkConnection")).withObject(new ObjectType().withProperties(networkConnection)));
						// add to relatedObservableTypes
						relatedObservableTypes.add(relatedObservable);
					}

					if (relatedObservableTypes.size() > 0) {
						// add to relatedObservablesType
						RelatedObservablesType relatedObservablesType = new RelatedObservablesType().withRelatedObservables(relatedObservableTypes);

						// add to incident
						incident.withRelatedObservables(relatedObservablesType);
					}

					// 影響平台 AffectedSoftwareDescription
					// 手法研判 LeveragedDescription
					// 來源IP ResourcesSourceIpAddress
					// 目標資訊(通訊埠) ResourcesDestinationPort
					// 目標資訊(通訊埠協定) ResourcesDestinationProtocol
					// 目標對象 ResourcesDestination
					// 掃描資訊(掃描引擎) ScanEngine
					// 掃描資訊(掃描版本) ScanVersion
					// 掃描資訊(掃描結果) ScanResult

					// for 影響平台
					TTP affectedSoftwareDescriptionTTP = null;
					if (informationExchange.getAffectedSoftwareDescription() != null && !informationExchange.getAffectedSoftwareDescription().equals("")) {

						affectedSoftwareDescriptionTTP = new TTP().withExploitTargets(new ExploitTargetsType().withExploitTargets(new RelatedExploitTargetType().withExploitTarget(new ExploitTarget().withVulnerabilities(
								new VulnerabilityType().withAffectedSoftware(new AffectedSoftwareType().withAffectedSoftwares(new RelatedObservableType().withObservable(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType()
										// 影響平台 AffectedSoftwareDescription
										.withValue(informationExchange.getAffectedSoftwareDescription())))))))));
					}

					// for 手法研判: 來源、中繼、目的
					TTP leveragedDescriptionTTP = null;
					if (informationExchange.getLeveragedDescription() != null && !informationExchange.getLeveragedDescription().equals("")) {

						// 手法研判
						leveragedDescriptionTTP = new TTP().withDescriptions(new StructuredTextType()
								// 手法研判 LeveragedDescription
								.withValue(informationExchange.getLeveragedDescription()));
					}

					// 準備 Observable List
					List<Observable> observablesTTP = new ArrayList<>();

					// 準備放置：
					// 來源IP ResourcesSourceIpAddress
					// 目標資訊(通訊埠) ResourcesDestinationPort
					// 目標資訊(通訊埠協定) ResourcesDestinationProtocol
					// 目標對象 ResourcesDestination
					NetworkConnection networkConnectionTTP = new NetworkConnection();

					// 來源IP ResourcesSourceIpAddress
					if (informationExchange.getResourcesSourceIpAddress() != null && !informationExchange.getResourcesSourceIpAddress().equals("")) {
						
						String port = "0";
						String protocol = "None";
						if (informationExchange.getResourcesSourcePort().split("/").length == 2) {							
							port = informationExchange.getResourcesSourcePort().split("/")[0];
							protocol = informationExchange.getResourcesSourcePort().split("/")[1];
						}
						
						networkConnectionTTP.withSourceSocketAddress(new SocketAddress().withIPAddress(new Address().withCategory(CategoryTypeEnum.IPV_4_ADDR).withAddressValue(new StringObjectPropertyType()
								// 來源IP ResourcesSourceIpAddress
								.withValue(informationExchange.getResourcesSourceIpAddress())))														
								.withPort(new Port().withPortValue(new PositiveIntegerObjectPropertyType()
										.withValue(port)).withLayer4Protocol(new Layer4ProtocolType()
												.withValue(protocol))));

					}

					// 目的端資訊(IP) DestinationIpAddress
					// 目的端資訊(通訊埠) DestinationPort
					// 目的端資訊(通訊埠協定) DestinationProtocol
					if (informationExchange.getDestinationIpAddress() != null && !informationExchange.getDestinationIpAddress().equals("") && informationExchange.getDestinationPort() != null && !informationExchange.getDestinationPort().equals("")
							&& informationExchange.getDestinationProtocol() != null && !informationExchange.getDestinationProtocol().equals("")) {

						networkConnectionTTP.withDestinationSocketAddress(new SocketAddress().withIPAddress(new Address().withCategory(CategoryTypeEnum.IPV_4_ADDR).withAddressValue(new StringObjectPropertyType()
								// 目的IP ResourcesSourceIpAddress
								.withValue(informationExchange.getDestinationIpAddress())))											
								.withPort(new Port().withPortValue(new PositiveIntegerObjectPropertyType()
										.withValue(informationExchange.getDestinationPort())).withLayer4Protocol(new Layer4ProtocolType()
												.withValue(informationExchange.getDestinationProtocol()))).withCustomProperties(
														new CustomPropertiesType().withProperties(
																// 目標對象 ResourcesDestination
																new Property().withName("Node").withValue(informationExchange.getResourcesDestination()))));							
					}
					
					
//					// 目標資訊(通訊埠) ResourcesDestinationPort
//					// 目標資訊(通訊埠協定) ResourcesDestinationProtocol
//					// 目標對象 ResourcesDestination
//					if (informationExchange.getResourcesDestinationPort() != null && !informationExchange.getResourcesDestinationPort().equals("") && informationExchange.getResourcesDestinationProtocol() != null
//							&& !informationExchange.getResourcesDestinationProtocol().equals("") && informationExchange.getResourcesDestination() != null && !informationExchange.getResourcesDestination().equals("")) {
//
//						networkConnectionTTP.withDestinationSocketAddress(new SocketAddress().withCustomProperties(new CustomPropertiesType().withProperties(
//								// 目標對象 ResourcesDestination
//								new Property().withName("Node").withValue(informationExchange.getResourcesDestination()))).withPort(new Port()
//										// 目標資訊(通訊埠) ResourcesDestinationPort
//										.withPortValue(new PositiveIntegerObjectPropertyType().withValue(informationExchange.getResourcesDestinationPort()))
//										// 目標資訊(通訊埠協定)
//										// ResourcesDestinationProtocol
//										.withLayer4Protocol(new Layer4ProtocolType().withValue(informationExchange.getResourcesDestinationProtocol()))));
//					}

					// add Observable
					if (networkConnectionTTP.getSourceSocketAddress() != null || networkConnectionTTP.getDestinationSocketAddress() != null) {

						observablesTTP.add(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType().withValue("NetworkConnection")).withObject(new ObjectType().withProperties(networkConnectionTTP)));
					}

					// for 防毒軟體
					// 掃描資訊(掃描引擎) ScanEngine
					// 掃描資訊(掃描版本) ScanVersion
					// 掃描資訊(掃描結果) ScanResult
					if (informationExchange.getScanEngine() != null && !informationExchange.getScanEngine().equals("") && informationExchange.getScanVersion() != null && !informationExchange.getScanVersion().equals("") && informationExchange.getScanResult() != null
							&& !informationExchange.getScanResult().equals("")) {

						Custom customTTP = new Custom().withCustomProperties(new CustomPropertiesType().withProperties(
								// 掃描資訊(掃描引擎) ScanEngine
								new Property().withName("Product").withValue(informationExchange.getScanEngine())
								// 掃描資訊(掃描版本) ScanVersion
								, new Property().withName("Version").withValue(informationExchange.getScanVersion())
								// 掃描資訊(掃描結果) ScanResult
								, new Property().withName("Result").withValue(informationExchange.getScanResult())));

						// add Observable
						observablesTTP.add(new Observable().withDescription(new org.mitre.cybox.common_2.StructuredTextType().withValue("AntiVirus")).withObject(new ObjectType().withProperties(customTTP)));
					}

					if (observablesTTP.size() > 0) {
						if (leveragedDescriptionTTP == null) {
							// 手法研判
							leveragedDescriptionTTP = new TTP().withDescriptions(new StructuredTextType().withValue("手法研判"));
						}
						leveragedDescriptionTTP
								.withResources(new ResourceType().withInfrastructure(new InfrastructureType().withObservableCharacterization(new Observables().withCyboxMajorVersion("2.0").withCyboxMinorVersion("2.0").withObservables(observablesTTP))));
					}

					// 準備放入 incident 的 relatedTTPTypes
					List<RelatedTTPType> relatedTTPTypes = new ArrayList<RelatedTTPType>();

					if (affectedSoftwareDescriptionTTP != null) {
						RelatedTTPType tmp = new RelatedTTPType();
						relatedTTPTypes.add(tmp.withTTP(affectedSoftwareDescriptionTTP));
					}
					if (leveragedDescriptionTTP != null) {
						RelatedTTPType tmp = new RelatedTTPType();
						relatedTTPTypes.add(tmp.withTTP(leveragedDescriptionTTP));
					}

					// 放入 incident
					if (relatedTTPTypes.size() > 0) {
						incident.withLeveragedTTPs(new LeveragedTTPsType().withLeveragedTTPs(relatedTTPTypes));
					}

					// 建議措施/解決方案 CoaDescription
					if (informationExchange.getCoaDescription() != null && !informationExchange.getCoaDescription().equals("")) {
						incident.withCOATakens(new COATakenType().withCourseOfAction(new CourseOfAction().withTimestamp(now).withDescriptions(new StructuredTextType()
								// 建議措施/解決方案 CoaDescription
								.withValue(informationExchange.getCoaDescription()))));
					}

					// 保密程度 Confidence
//					incident.withConfidence(new ConfidenceType().withValue(new ControlledVocabularyStringType()
//							// 保密程度 Confidence
//							.withValue(informationExchange.getConfidence())));

					// 來源編號 RelatedIncidentId
					// 來源時間 RelatedIncidentTimestamp
					if (informationExchange.getRelatedIncidentId() != null && !informationExchange.getRelatedIncidentId().equals("") && informationExchange.getRelatedIncidentTimestamp() != null) {
						// 來源時間轉換為 XML 所需格式
						// 2017-10-06 08:47:32.484 to 2017-10-06T08:47:32.484Z
						String formattedTime = "";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						Date d;
						try {
							d = sdf.parse(informationExchange.getRelatedIncidentTimestamp().toString());
							formattedTime = output.format(d);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							// //e.printStackTrace();
						}

						XMLGregorianCalendar relatedIncidentTimestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedTime);

						incident.withRelatedIncidents(new RelatedIncidentsType().withRelatedIncidents(new RelatedIncidentType().withIncident(new IncidentBaseType()
								// 來源編號 RelatedIncidentId
								.withId(new QName(informationExchange.getRelatedIncidentId()))
								// .withId(new
								// QName(informationExchange.getPostId()))
								// 來源時間 RelatedIncidentTimestamp
								.withTimestamp(relatedIncidentTimestamp))));
					}

					// 參考資料 Reference
					if (informationExchange.getReference() != null && !informationExchange.getReference().equals("")) {
						incident.withInformationSource(new InformationSourceType().withReferences(new ReferencesType()
								// 參考資料 Reference
								.withReferences(informationExchange.getReference())));
					}

					// 將事件加入事件清單
					IncidentsType Incidents = new IncidentsType(new ArrayList<IncidentBaseType>() {
						{
							// 加入事件
							add(incident);
						}
					});

					// 表頭
					STIXHeaderType header = new STIXHeaderType().withTitle(informationExchange.getStixTitle()) // 警訊類型代號
							.withDescriptions(new StructuredTextType().withValue(informationExchange.getIncidentTitle())) // 標題
							.withHandling(new MarkingType().withMarkings(new MarkingSpecificationType() //保密程度
									.withControlledStructure("//node() | //@*")
									.withMarkingStructures(new TLPMarkingStructureType()
											.withColor(TLPColorEnum.valueOf(informationExchange.getConfidence())))));
					//ANA增加Information_Source
					if (informationExchange.getStixTitle().equals("ANA") || informationExchange.getStixTitle().equals("FBI")) {
						header.withInformationSource(new InformationSourceType()
									.withIdentity(new IdentityType().withName("ISAC"))
									.withTime(new TimeType().withProducedTime(new DateTimeWithPrecisionType().withValue(now))));
					}


					STIXPackage stixPackage = new STIXPackage().withSTIXHeader(header).withIncidents(Incidents).withVersion("1.2");

					xmlStr = stixPackage.toXMLString(true);

				} catch (Exception e) {
					// do nothing
					// //e.printStackTrace();

					// Log
					JSONObject responseJson = new JSONObject();
					responseJson.put("msg", "產生 STIX XML失敗");
					responseJson.put("id", id);
					responseJson.put("success", false);
					responseJson.put("errormsg", e.getMessage());
					systemLogService.insert("Nisac", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

				}

			} else {
				// 警訊代號或事件代號不存在
				// Log
				JSONObject responseJson = new JSONObject();
				responseJson.put("msg", "產生 STIX XML失敗");
				responseJson.put("id", id);
				responseJson.put("success", false);
				responseJson.put("errormsg", "警訊代號或事件代號不存在");
				systemLogService.insert("Nisac", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

			}
		} else {
			// 紀錄不存在
			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "產生 STIX XML失敗");
			responseJson.put("id", id);
			responseJson.put("success", false);
			responseJson.put("errormsg", "情資紀錄不存在");
			systemLogService.insert("Nisac", "genStixXML", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

		}

		return xmlStr;

	} // end of genStixXML()

	/**
	 * 讀取檔案 for testing
	 * 
	 * @param path
	 *            路徑
	 * @return 檔案string
	 * @throws IOException
	 *             例外
	 */
	@SuppressWarnings("unused")
	private String readFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String sCurrentLine = "";
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		}
		return sb.toString();
	}

//	/**
//	 * 讀取目錄內所有檔名
//	 * 
//	 * @param folder
//	 *            目錄
//	 * @return 清單
//	 */
//	@SuppressWarnings("unused")
//	private List<String> listFilesForFolder(final File folder) {
//		List<String> files = new ArrayList<String>();
//		for (final File fileEntry : folder.listFiles()) {
//			if (fileEntry.isDirectory()) {
//				listFilesForFolder(fileEntry);
//			} else {
//				files.add(fileEntry.getPath());
//			}
//		}
//
//		return files;
//	}

	/**
	 * 自 NISAC 接收情資
	 * 
	 */
	public void importFromNisac() {

//		 List<String> files = new ArrayList<String>();
//		 final File folder = new File("C:\\STIX_test");
//		 files = listFilesForFolder(folder);
//		
//		 int filesCount = files.size();
//		 if ( filesCount > 0 ) {
//		 for ( int i = 0; i < filesCount; i++ ) {
//		 try {
//		 String stixContent = readFile(files.get(i).toString());
//		 String json = parseStixXml(stixContent);
//		 System.out.println(json.toString());
//		 toDB(json);
//		 } catch ( Exception e ) {
//		 // do nothing
//		 }
//		 }
//		 }

		this.mailBody = "";
		this.mailSubject = "";
		this.nisacSuccess = true;
		
		this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]開始";
		
		// 設定 NISAC 相關參數
		boolean result = initNisac();

		if ( result ) {
			// 取得未讀情資編號清單
			List<String> recordIds = getIncidentIds();
	
			/* 依序取得情資內容 */
			String stixContent = "";
			String jsonString = "";
			if (recordIds.size() != 0) { // 判斷是否有情資編號
				for (String incidentId : recordIds) { // 取回每筆情資編號之情資內容
	
					// 取得情資內容 XML
					stixContent = getStixXmlByIncidentId(incidentId);
					// 解析情資內容 XML 為 JSON (寫入資料庫所需格式)
					jsonString = parseStixXml(stixContent);
					// 寫入資料庫
					toDB(jsonString);
				}
			}
		} else {
			this.nisacSuccess = false;
		}
		
		if (this.nisacSuccess == true) {
			this.mailSubject = "接收Nisac資料成功";
		} else {
			this.mailSubject = "接收Nisac資料失敗";
		}
		this.mailBody +=  "<br>[" + WebDatetime.toString(new Date()) + "]結束";
		
		String mailTo = resourceMessageService.getMessageValue("nisacMailReceiver");
		mailService.Send("測試", mailTo, "", null, this.mailSubject, this.mailBody, null);

	} // end of method importFromNisac()

	public String testToNisac(String id, String category, String filepath) {

		// 設定 NISAC 相關參數
		initNisac();

		// 回傳 String
		String resultStr = "";

		// 指定此筆情資之接收會員代碼 (可多筆)(目前只傳給 NISAC)
		List<String> toUnitIds = Arrays.asList(nisacDcn);

		try {
			// call service
			File file = new File(filepath);
			String sendStringResult[] = nisacClient.callInbox(nisacInboxUrl, nisacId, nisacP, toUnitIds, category, file);
			String statusCode = sendStringResult[0]; // 取得傳送結果代碼

			JSONObject responseJson = new JSONObject();

			String statusMsg = "";
			switch (statusCode) {
				case "0101" : // STIX情資檔案編號
					statusMsg = sendStringResult[1]; // 取得傳送結果訊息
					resultStr = statusMsg;

					// Log
					responseJson.put("msg", "傳送情資至 NISAC 成功");
					responseJson.put("IncidentId", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", true);
					systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, "", "System");

					break;
				// case "0201": // 帳號密碼錯誤
				// break;
				// case "0202": // 憑證錯誤
				// break;
				// case "0203": // 權限錯誤
				// break;
				// case "0204": // 所有參數皆不能為空值
				// break;
				// case "0205": // 不允許此IP位址
				// break;
				// case "0301": // STIX格式錯誤
				// break;
				// case "0302": // 情報種類不符
				// break;
				// case "0303": // 事件類型不符
				// break;
				// case "0304": // 回饋情報內欲回報的編號不符
				// break;
				// case "0305": // 情資檔案大小超過限制
				// break;
				// case "0306": // 情資檔案重覆傳送
				// break;
				// case "0307": // 請求之情資編號貨數量有誤
				// break;
				// case "0308": // 時間區間有誤，請確認結束時間大於起始時間
				// break;

				default :
					// Log
					responseJson.put("msg", "傳送情資至 NISAC 失敗");
					responseJson.put("statusCode", statusCode);
					responseJson.put("statusMsg", statusMsg);
					responseJson.put("id", id);
					responseJson.put("success", false);
					systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

					break;
			}
		} catch (NisacException ne) {
			// 服務回傳之錯誤訊息，應實作相關紀錄與處理機制
			String errorCode = ne.getErrorCode(); // 取得錯誤代碼作為後續處理之依據
			String errorMsg = ne.getErrorMsg(); // 取得錯誤訊息

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "傳送情資至 NISAC 失敗");
			responseJson.put("errorCode", errorCode);
			responseJson.put("errorMsg", errorMsg);
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");

		} catch (Exception e) {
			// 發生其他錯誤，應實作相關紀錄與處理機制
			//e.printStackTrace();

			// Log
			JSONObject responseJson = new JSONObject();
			responseJson.put("msg", "傳送情資至 NISAC 失敗, 可能是連線逾時或其他原因(" + e.getMessage() + ")");
			responseJson.put("id", id);
			responseJson.put("success", false);
			systemLogService.insert("Nisac", "toNisac", responseJson.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, "", "System");
		}

		// 成功 傳回NISAC情資編號/失敗傳回空字串
		return resultStr;

	}

	// public void testing() {
	//
	// List<String> ids = Arrays.asList("0afdaf306fda47178a35b43f27770f95");
	//
	// List<String> files = new ArrayList<String>();
	// final File folder = new File("C:\\STIX_test");
	// files = listFilesForFolder(folder);
	//
	// int filesCount = files.size();
	// if (filesCount > 0) {
	// for (int i = 0; i < filesCount; i++) {
	// try {
	// // String xmlStr = readFile(files.get(i).toString());
	// int pos1 = files.get(i).lastIndexOf("\\");
	// int pos2 = files.get(i).indexOf("-");
	// String iType = files.get(i).substring(pos1 + 1, pos2);
	// String incidentId = testToNisac(iType, iType, files.get(i));
	//
	// } catch (Exception e) {
	// // do nothing
	// }
	// }
	// }
	//
	// // for ( int i = 0; i < ids.size(); i++ ) {
	// // exportToNisac(ids.get(i));
	// // }
	// }

	/**
	 * 傳送情資至 NISAC
	 * 
	 * @param id
	 *            情資單 id
	 * 
	 * @return JSON字串，包含 IncidentId 及 SourceContentXML
	 */
	public String exportToNisac(String id) {

		JSONObject obj = new JSONObject();

		// for testing
		// String id = "a3ab58de1d864b158b309189ba6cf4c2"; // 手法研判 INT-0001
		// String id = "be0a4564fef84cd3af1b102e5faed2a7"; // 受害 IP INT-0002
		// String id = "96608bbde1874b75917ac56b48cdeac6"; // 中繼站IP INT-0003
		// String id = "c624904c448540cd8189122791aaaa7e"; //
		// NCCST-DEF-201802-0001
		// String id = "848d265b08154204b5ffca01de9b7c21"; //
		// NCCST-ANA-201802-0001
		// String id = "9d0d7532e6214e788c1e79ac84d557d4"; //
		// NCCST-EWA-201802-0001
		InformationExchange infoObj = informationExchangeService.getById(id);

		String xmlStr = genStixXML(id);

		String incidentId = "";

		if (!xmlStr.equals("")) {
			incidentId = toNisac(infoObj.getId(), infoObj.getCategory(), xmlStr);

			// update InformationExchange
			obj.put("IncidentId", incidentId);
			obj.put("SourceContentXML", xmlStr);
		}

		return obj.toString();
	} // end of method exportToNisac()

//	public void chkUtf8XMLFile() {  
//		List<String> files = new ArrayList<String>();
//		 final File folder = new File("C:\\STIX_test");
//		 files = listFilesForFolder(folder);
//		
//		 int filesCount = files.size();
//		 if ( filesCount > 0 ) {
//			 for ( int i = 0; i < filesCount; i++ ) {
//				 try {
//					 System.out.println(files.get(i).toString());
//					 boolean result = chkUtf8(files.get(i).toString());
//					 if ( result ) {
//						 System.out.println("OK");
//					 } else {
//						 System.out.println("Fail");
//					 }
//				 } catch ( Exception e ) {
//					 // do nothing
//				 }
//			 }
//		 }
//	}
//	
//	private boolean chkUtf8(String filename) {
//		
//		InputStream inStream = null;
//		try {
//			inStream = new FileInputStream(filename);
//	
//		    CharsetDecoder d=Charset.forName("UTF-8").newDecoder();
//		    CharBuffer out = CharBuffer.allocate(1);
//		    ByteBuffer in = ByteBuffer.allocate(10);
//		    in.clear();
//		    long offset = 0L;
//		    while (true) {
//		      int read = inStream.read();
//		      if (read != -1) {
//		        in.put((byte)read);
//		      }
//		      out.clear();
//		      in.flip();
//		      CoderResult cr = d.decode(in, out, (read == -1));
//		      if (cr.isError()) {
//		        if (read != -1) {
//		          System.out.println("Error at offset " + offset + ": " + cr);
//		          return false;
//		        } else {
//		          System.out.println("Error at end-of-file: " + cr);
//		          return false;
//		        }
//		      }
//		      if (cr.isUnderflow()) {
//		        in.position(in.limit());
//		        in.limit(in.capacity());
//		      } else {
//		        in.clear();
//		      }
//		      if (read == -1) {
//		        break;
//		      }
//		      offset += 1L;
//		    } // end of while()
//		    
//		    return true;
//
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return false;
//		} finally {
//			try {
//				inStream.close();
//			} catch (Exception e) {
//				//e.printStackTrace();
//			}
//		}
//
//	} // end of method chkUtf8()
}
