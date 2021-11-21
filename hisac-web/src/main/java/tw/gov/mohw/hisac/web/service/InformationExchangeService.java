package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.InformationExchangeDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.AnaManagement;
import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.NewsManagement;
import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;
import tw.gov.mohw.hisac.web.domain.SpActivityManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpInformationExchange2Report;
import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.MemberService;
import tw.gov.mohw.hisac.web.service.InformationExchangeNisacService;

/**
 * 情資管理(N-ISAC)服務
 */
@Service
public class InformationExchangeService {

	@Autowired
	InformationExchangeDAO informationExchangeDAO;
	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;
	@Autowired
	MemberRoleService memberRoleService;
	
	@Autowired
	AnaManagementService anaManagementService;
	@Autowired
	NewsManagementService newsManagementService;
	@Autowired
	MessageService messageService;
	@Autowired
	InformationExchangeNisacService informationExchangeNisacService;
	@Autowired
	MessagePostAttachService messagePostAttachService;
	@Autowired
	InformationExchangeAttachService informationExchangeAttachService;
	@Autowired
	AnaManagementAttachService anaManagementAttachService;
	
	@Autowired
	NewsManagementAttachService newsManagementAttachService;
	/**
	 * 取得所有情資資料
	 * 
	 * @return 情資資料
	 */
	public List<InformationExchange> getAll() {
		return informationExchangeDAO.getAll();
	}

	/**
	 * 取得SecBuzzer情資資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	public List<ViewInformationExchangeSecbuzzerTitle> getSecBuzzerList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSecBuzzerList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得情資資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料筆數
	 */
	public long getSecBuzzerListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSecBuzzerListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得情資資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	public List<InformationExchange> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得情資資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得情資button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得情資資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得報表
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報表
	 */
	public List<SpInformationExchange2Report> getReport(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationExchangeDAO.getReport(obj);
		} catch (Exception e) {
			return null;
		}
	}

	// /**
	// * 取得情資資料
	// *
	// * @param json
	// * 查詢條件
	// * @return 情資資料
	// */
	// public List<InformationExchange> getList(String json) {
	// try {
	// JSONObject obj = new JSONObject(json);
	// return informationExchangeDAO.getList(obj);
	// } catch (Exception e) {
	// return null;
	// }
	// }

	// /**
	// * 取得情資資料筆數
	// *
	// * @param json
	// * 查詢條件
	// * @return 情資資料筆數
	// */
	// public long getListSize(String json) {
	// try {
	// JSONObject obj = new JSONObject(json);
	// return informationExchangeDAO.getListSize(obj);
	// } catch (Exception e) {
	// return 0;
	// }
	// }

	/**
	 * 新增情資資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資資料
	 * @return 情資資料
	 */
	public InformationExchange insert(Long memberId, String json) {
		return insert(memberId, json, false);
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

	/**
	 * 新增情資資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資資料
	 * @param isApply
	 *            是否正式
	 * @return 情資資料
	 */
	public InformationExchange insert(Long memberId, String json, boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");
			String stixTitle = obj.isNull("StixTitle") == true ? "" : obj.getString("StixTitle");
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? "" : obj.getString("IncidentTitle");
			String description = obj.isNull("Description") == true ? "" : obj.getString("Description");
			String category = obj.isNull("Category") == true ? null : obj.getString("Category");
			//取代種類代碼名稱
			String categoryName = getCategoryName(category);

			String reporterName = obj.isNull("ReporterName") == true ? null : obj.getString("ReporterName");
			String responderPartyName = obj.isNull("ResponderPartyName") == true ? null : obj.getString("ResponderPartyName");
			String responderContactNumbers = obj.isNull("ResponderContactNumbers") == true ? null : obj.getString("ResponderContactNumbers");
			String responderElectronicAddressIdentifiers = obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null : obj.getString("ResponderElectronicAddressIdentifiers");
			// String impactQualification = obj.isNull("ImpactQualification") ==
			// true ? null : String.valueOf(obj.get("ImpactQualification"));
			int impactQualification = obj.isNull("ImpactQualification") == true ? 1 : obj.getInt("ImpactQualification");
			String coaDescription = obj.isNull("CoaDescription") == true ? null : obj.getString("CoaDescription");
			String confidence = obj.isNull("Confidence") == true ? null : obj.getString("Confidence");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String observableAttach = obj.isNull("ObservableAttach") == true ? null : obj.getString("ObservableAttach");
			String observableIpAddress = obj.isNull("ObservableIpAddress") == true ? null : obj.getString("ObservableIpAddress");
			String socketIpAddress = obj.isNull("SocketIpAddress") == true ? null : obj.getString("SocketIpAddress");
			String socketPort = obj.isNull("SocketPort") == true ? null : obj.getString("SocketPort");
			String socketProtocol = obj.isNull("SocketProtocol") == true ? null : obj.getString("SocketProtocol");
			String customIpAddress = obj.isNull("CustomIpAddress") == true ? null : obj.getString("CustomIpAddress");
			String customPort = obj.isNull("CustomPort") == true ? null : obj.getString("CustomPort");
			String customProtocol = obj.isNull("CustomProtocol") == true ? null : obj.getString("CustomProtocol");
			String destinationIpAddress = obj.isNull("DestinationIpAddress") == true ? null : obj.getString("DestinationIpAddress");
			String destinationPort = obj.isNull("DestinationPort") == true ? null : obj.getString("DestinationPort");
			String destinationProtocol = obj.isNull("DestinationProtocol") == true ? null : obj.getString("DestinationProtocol");
			String leveragedDescription = obj.isNull("LeveragedDescription") == true ? null : obj.getString("LeveragedDescription");
			String affectedSoftwareDescription = obj.isNull("AffectedSoftwareDescription") == true ? null : obj.getString("AffectedSoftwareDescription");
			String resourcesSourceIpAddress = obj.isNull("ResourcesSourceIpAddress") == true ? null : obj.getString("ResourcesSourceIpAddress");
			String resourcesDestinationPort = obj.isNull("ResourcesDestinationPort") == true ? null : obj.getString("ResourcesDestinationPort");
			String resourcesDestinationProtocol = obj.isNull("ResourcesDestinationProtocol") == true ? null : obj.getString("ResourcesDestinationProtocol");
			String resourcesDestination = obj.isNull("ResourcesDestination") == true ? null : obj.getString("ResourcesDestination");
			String scanEngine = obj.isNull("ScanEngine") == true ? null : obj.getString("ScanEngine");
			String scanVersion = obj.isNull("ScanVersion") == true ? null : obj.getString("ScanVersion");
			String scanResult = obj.isNull("ScanResult") == true ? null : obj.getString("ScanResult");
			String relatedIncidentId = obj.isNull("RelatedIncidentId") == true ? null : obj.getString("RelatedIncidentId");

			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"));
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : WebDatetime.parse(obj.getString("IncidentReportedTime"));
			Date incidentClosedTime = obj.isNull("IncidentClosedTime") == true ? null : WebDatetime.parse(obj.getString("IncidentClosedTime"));
			Date relatedIncidentTimestamp = obj.isNull("RelatedIncidentTimestamp") == true ? null : WebDatetime.parse(obj.getString("RelatedIncidentTimestamp"));
			
			
			//110.02.03 新增api 資料
			//防護類型
			String incidentToolType = obj.optString("IncidentToolType","無");
			//設備廠商
			String incidentVendor = obj.optString("IncidentVendor","無");
			//觸發規則
			String incidentDescription = obj.optString("IncidentDescription",stixTitle);
			//設備代號
			String incidentName = obj.optString("IncidentName","無");
			//Associated objected 欄位
			JSONArray associatedObjected = obj.isNull("AssociatedObjected") == true ? new JSONArray() : obj.getJSONArray("AssociatedObjected");
			
			

			
			
			

			int newsManagementGroupId = obj.isNull("NewsManagementGroupId") == true ? 0 : obj.getInt("NewsManagementGroupId");
			int status = obj.isNull("Status") == true ? 0 : Integer.valueOf(obj.getInt("Status"));
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");

			// 補充之變數
			String reporterNameUrl = obj.isNull("ReporterNameUrl") == true ? null : obj.getString("ReporterNameUrl");
			String resourcesSourcePort = obj.isNull("ResourcesSourcePort") == true ? null : obj.getString("ResourcesSourcePort");
			String nhiImpact = obj.isNull("NhiImpact") == true ? null : obj.getString("NhiImpact");
			String nhiProblemEquipmentOwner = obj.isNull("NhiProblemEquipmentOwner") == true ? null : obj.getString("NhiProblemEquipmentOwner");
			String nhiProblemEquipmentUse = obj.isNull("NhiProblemEquipmentUse") == true ? null : obj.getString("NhiProblemEquipmentUse");
			String nhiProblemIpAddress = obj.isNull("NhiProblemIpAddress") == true ? null : obj.getString("NhiProblemIpAddress");
			String nhiProblemPort = obj.isNull("NhiProblemPort") == true ? null : obj.getString("NhiProblemPort");
			String nhiProblemUrl = obj.isNull("NhiProblemUrl") == true ? null : obj.getString("NhiProblemUrl");
			String nhiProcess = obj.isNull("NhiProcess") == true ? null : obj.getString("NhiProcess");
			String nhiRemark = obj.isNull("NhiRemark") == true ? null : obj.getString("NhiRemark");
			String nhiRiskGrade = obj.isNull("NhiRiskGrade") == true ? null : obj.getString("NhiRiskGrade");
			String nhiRiskType = obj.isNull("NhiRiskType") == true ? null : obj.getString("NhiRiskType");
			String nhiSourceRecord = obj.isNull("NhiSourceRecord") == true ? null : obj.getString("NhiSourceRecord");
			
			
			

			// NISAC XML
			String sourceContentXml = obj.isNull("SourceContentXml") == true ? null : obj.getString("SourceContentXml");

			Date now = new Date();
			InformationExchange entity = new InformationExchange();

			// 產生 Id
			// 1. 如無情資來源(sourceCode)
			// 1.1 有情報編號 incidentId, 以 incidentId 為 Id
			// 1.2 無情報編號 incidentId, 產生隨機 Id
			// 2. 如有情資來源(sourceCode)
			// 2.1 如情資來源為 N-ISAC, 產生隨機 Id
			// 2.2 如情資來源為其他
			// 2.2.1 有情報編號 incidentId, 以 incidentId 為 Id
			// 2.2.2 無情報編號 incidentId, 產生隨機 Id
			if (sourceCode != null) {
				switch (sourceCode) {
					case "2" : // N-ISAC
						entity.setId(WebCrypto.generateUUID());
						break;
					case "OTH" : // OTH
					case "ISSDU" : // ISSDU
					case "ISSDUSOC" : // NHISOC
					case "SEC" : // SEC
					case "NHISOC" : // NHISOC
						entity.setId(id);
						break;
					default :
						if (incidentId != null)
							entity.setId(incidentId);
						else
							entity.setId(WebCrypto.generateUUID());
						break;
				}
			} else {
				if (incidentId != null)
					entity.setId(incidentId);
				else
					entity.setId(WebCrypto.generateUUID());
			}

			String postId = ticketQueueDAO.insertPostId("information_exchange", isApply, "HISAC-INFO", stixTitle);

			entity.setPostId(postId);
			entity.setSourceCode(sourceCode);

			entity.setStixTitle(stixTitle);
			entity.setIncidentId(incidentId);

			entity.setIncidentTitle(incidentTitle);
			entity.setDescription(description);
			entity.setCategory(category);

			entity.setReporterName(reporterName);
			entity.setResponderPartyName(responderPartyName);
			entity.setResponderContactNumbers(responderContactNumbers);
			entity.setResponderElectronicAddressIdentifiers(responderElectronicAddressIdentifiers);
			entity.setImpactQualification(impactQualification);
			entity.setCoaDescription(coaDescription);

			entity.setConfidence(confidence);
			entity.setReference(reference);
			entity.setObservableAttach(observableAttach);
			entity.setObservableIpAddress(observableIpAddress);
			entity.setSocketIpAddress(socketIpAddress);
			entity.setSocketPort(socketPort);
			entity.setSocketProtocol(socketProtocol);
			entity.setCustomIpAddress(customIpAddress);
			entity.setCustomPort(customPort);
			entity.setCustomProtocol(customProtocol);
			entity.setDestinationIpAddress(destinationIpAddress);
			entity.setDestinationPort(destinationPort);
			entity.setDestinationProtocol(destinationProtocol);
			entity.setLeveragedDescription(leveragedDescription);
			entity.setAffectedSoftwareDescription(affectedSoftwareDescription);
			entity.setResourcesSourceIpAddress(resourcesSourceIpAddress);
			entity.setResourcesDestinationPort(resourcesDestinationPort);
			entity.setResourcesDestinationProtocol(resourcesDestinationProtocol);
			entity.setResourcesDestination(resourcesDestination);
			entity.setScanEngine(scanEngine);
			entity.setScanVersion(scanVersion);
			entity.setScanResult(scanResult);
			entity.setRelatedIncidentId(relatedIncidentId);

			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setIncidentReportedTime(incidentReportedTime);
			entity.setIncidentClosedTime(incidentClosedTime);
			entity.setRelatedIncidentTimestamp(relatedIncidentTimestamp);

			// 補充之變數
			entity.setReporterNameUrl(reporterNameUrl);
			entity.setResourcesSourcePort(resourcesSourcePort);
			entity.setNhiImpact(nhiImpact);
			entity.setNhiProblemEquipmentOwner(nhiProblemEquipmentOwner);
			entity.setNhiProblemEquipmentUse(nhiProblemEquipmentUse);
			entity.setNhiProblemIpAddress(nhiProblemIpAddress);
			entity.setNhiProblemPort(nhiProblemPort);
			entity.setNhiProblemUrl(nhiProblemUrl);
			entity.setNhiProcess(nhiProcess);
			entity.setNhiRemark(nhiRemark);
			entity.setNhiRiskGrade(nhiRiskGrade);
			entity.setNhiRiskType(nhiRiskType);
			entity.setNhiSourceRecord(nhiSourceRecord);

			entity.setNewsManagementGroupId(newsManagementGroupId);
			if (sourceContentXml != null && sourceContentXml != "") {
				entity.setSourceContentXml(sourceContentXml.getBytes());
			}
			entity.setTransferInType(0);
			entity.setTransferOutType(0);
			entity.setStatus(status);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			
			
			entity.setIncidentToolType(incidentToolType);
			entity.setIncidentName(incidentName);
			entity.setIncidentDescription(incidentDescription);
			entity.setIncidentVendor(incidentVendor);
			entity.setAssociatedObjected(associatedObjected.toString());
			
			

			informationExchangeDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安情資資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安情資Id
	 * @return 資安情資
	 */
	public InformationExchange update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);

			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String sourceCode = obj.isNull("SourceCode") == true ? null :obj.getString("SourceCode");
			String stixTitle = obj.isNull("StixTitle") == true ? null : obj.getString("StixTitle");
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String category = obj.isNull("Category") == true ? null : obj.getString("Category");
			String reporterName = obj.isNull("ReporterName") == true ? null : obj.getString("ReporterName");
			String responderPartyName = obj.isNull("ResponderPartyName") == true ? null : obj.getString("ResponderPartyName");
			String responderContactNumbers = obj.isNull("ResponderContactNumbers") == true ? null : obj.getString("ResponderContactNumbers");
			String responderElectronicAddressIdentifiers = obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null : obj.getString("ResponderElectronicAddressIdentifiers");
			// String impactQualification = obj.isNull("ImpactQualification") ==
			// true ? null : obj.getString("ImpactQualification");
			int impactQualification = obj.isNull("ImpactQualification") == true ? 1 : obj.getInt("ImpactQualification");

			String coaDescription = obj.isNull("CoaDescription") == true ? null : obj.getString("CoaDescription");
			String confidence = obj.isNull("Confidence") == true ? null : obj.getString("Confidence");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String observableAttach = obj.isNull("ObservableAttach") == true ? null : obj.getString("ObservableAttach");
			String observableIpAddress = obj.isNull("ObservableIpAddress") == true ? null : obj.getString("ObservableIpAddress");
			String socketIpAddress = obj.isNull("SocketIpAddress") == true ? null : obj.getString("SocketIpAddress");
			String socketPort = obj.isNull("SocketPort") == true ? null : obj.getString("SocketPort");
			String socketProtocol = obj.isNull("SocketProtocol") == true ? null : obj.getString("SocketProtocol");
			String customIpAddress = obj.isNull("CustomIpAddress") == true ? null : obj.getString("CustomIpAddress");
			String customPort = obj.isNull("CustomPort") == true ? null : obj.getString("CustomPort");
			String customProtocol = obj.isNull("CustomProtocol") == true ? null : obj.getString("CustomProtocol");
			String destinationIpAddress = obj.isNull("DestinationIpAddress") == true ? null : obj.getString("DestinationIpAddress");
			String destinationPort = obj.isNull("DestinationPort") == true ? null : obj.getString("DestinationPort");
			String destinationProtocol = obj.isNull("DestinationProtocol") == true ? null : obj.getString("DestinationProtocol");
			String leveragedDescription = obj.isNull("LeveragedDescription") == true ? null : obj.getString("LeveragedDescription");
			String affectedSoftwareDescription = obj.isNull("AffectedSoftwareDescription") == true ? null : obj.getString("AffectedSoftwareDescription");
			String resourcesSourceIpAddress = obj.isNull("ResourcesSourceIpAddress") == true ? null : obj.getString("ResourcesSourceIpAddress");
			String resourcesDestinationPort = obj.isNull("ResourcesDestinationPort") == true ? null : obj.getString("ResourcesDestinationPort");
			String resourcesDestinationProtocol = obj.isNull("ResourcesDestinationProtocol") == true ? null : obj.getString("ResourcesDestinationProtocol");
			String resourcesDestination = obj.isNull("ResourcesDestination") == true ? null : obj.getString("ResourcesDestination");
			String scanEngine = obj.isNull("ScanEngine") == true ? null : obj.getString("ScanEngine");
			String scanVersion = obj.isNull("ScanVersion") == true ? null : obj.getString("ScanVersion");
			String scanResult = obj.isNull("ScanResult") == true ? null : obj.getString("ScanResult");
			String relatedIncidentId = obj.isNull("RelatedIncidentId") == true ? null : obj.getString("RelatedIncidentId");

			// String incidentDiscoveryTime =
			// obj.isNull("IncidentDiscoveryTime") == true ? null :
			// obj.getString("IncidentDiscoveryTime");
			// String incidentReportedTime = obj.isNull("IncidentReportedTime")
			// == true ? null : obj.getString("IncidentReportedTime");
			// String incidentClosedTime = obj.isNull("IncidentClosedTime") ==
			// true ? null : obj.getString("IncidentClosedTime");
			// String relatedIncidentTimestamp =
			// obj.isNull("RelatedIncidentTimestamp") == true ? null :
			// obj.getString("RelatedIncidentTimestamp");

			// 補充之變數
			String reporterNameUrl = obj.isNull("ReporterNameUrl") == true ? null : obj.getString("ReporterNameUrl");
			String resourcesSourcePort = obj.isNull("ResourcesSourcePort") == true ? null : obj.getString("ResourcesSourcePort");
			String nhiImpact = obj.isNull("NhiImpact") == true ? null : obj.getString("NhiImpact");
			String nhiProblemEquipmentOwner = obj.isNull("NhiProblemEquipmentOwner") == true ? null : obj.getString("NhiProblemEquipmentOwner");
			String nhiProblemEquipmentUse = obj.isNull("NhiProblemEquipmentUse") == true ? null : obj.getString("NhiProblemEquipmentUse");
			String nhiProblemIpAddress = obj.isNull("NhiProblemIpAddress") == true ? null : obj.getString("NhiProblemIpAddress");
			String nhiProblemPort = obj.isNull("NhiProblemPort") == true ? null : obj.getString("NhiProblemPort");
			String nhiProblemUrl = obj.isNull("NhiProblemUrl") == true ? null : obj.getString("NhiProblemUrl");
			String nhiProcess = obj.isNull("NhiProcess") == true ? null : obj.getString("NhiProcess");
			String nhiRemark = obj.isNull("NhiRemark") == true ? null : obj.getString("NhiRemark");
			String nhiRiskGrade = obj.isNull("NhiRiskGrade") == true ? null : obj.getString("NhiRiskGrade");
			String nhiRiskType = obj.isNull("NhiRiskType") == true ? null : obj.getString("NhiRiskType");
			String nhiSourceRecord = obj.isNull("NhiSourceRecord") == true ? null : obj.getString("NhiSourceRecord");

			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? null : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"));
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? null : WebDatetime.parse(obj.getString("IncidentReportedTime"));

			// String incidentReportedTime = obj.isNull("IncidentReportedTime")
			// == true ? null : obj.getString("IncidentReportedTime");
			Date incidentClosedTime = obj.isNull("IncidentClosedTime") == true ? null : WebDatetime.parse(obj.getString("IncidentClosedTime"));
			Date relatedIncidentTimestamp = obj.isNull("RelatedIncidentTimestamp") == true ? null : WebDatetime.parse(obj.getString("RelatedIncidentTimestamp"));
			int newsManagementGroupId = obj.isNull("NewsManagementGroupId") == true ? 0 : obj.getInt("NewsManagementGroupId");
			
			
			//110.02.03 新增api 資料
			//防護類型
			String incidentToolType = obj.optString("IncidentToolType","無");
			//設備廠商
			String incidentVendor = obj.optString("IncidentVendor","無");
			//觸發規則
			String incidentDescription = obj.optString("IncidentDescription",stixTitle);
			//設備代號
			String incidentName = obj.optString("IncidentName","無");
			//Associated objected 欄位
			JSONArray associatedObjected = obj.isNull("AssociatedObjected") == true ? new JSONArray() : obj.getJSONArray("AssociatedObjected");
			
			


			int status = obj.isNull("Status") == true ? 1 : obj.getInt("Status");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");

			Date now = new Date();
			// InformationExchange entity = new InformationExchange();
			InformationExchange entity = null;

			entity = informationExchangeDAO.get(id);

			String postId = ticketQueueDAO.updatePostId("information_exchange", false, "HISAC-INFO", entity.getPostId(), stixTitle);

			entity.setPostId(postId);
			entity.setSourceCode(sourceCode);
			entity.setStixTitle(stixTitle);
			entity.setIncidentId(incidentId);
			entity.setIncidentTitle(incidentTitle);
			entity.setDescription(description);
			entity.setCategory(category);
			entity.setReporterName(reporterName);
			entity.setResponderPartyName(responderPartyName);
			entity.setResponderContactNumbers(responderContactNumbers);
			entity.setResponderElectronicAddressIdentifiers(responderElectronicAddressIdentifiers);
			entity.setImpactQualification(impactQualification);
			entity.setCoaDescription(coaDescription);
			entity.setConfidence(confidence);
			entity.setReference(reference);
			entity.setObservableAttach(observableAttach);
			entity.setObservableIpAddress(observableIpAddress);
			entity.setSocketIpAddress(socketIpAddress);
			entity.setSocketPort(socketPort);
			entity.setSocketProtocol(socketProtocol);
			entity.setCustomIpAddress(customIpAddress);
			entity.setCustomPort(customPort);
			entity.setCustomProtocol(customProtocol);
			entity.setDestinationIpAddress(destinationIpAddress);
			entity.setDestinationPort(destinationPort);
			entity.setDestinationProtocol(destinationProtocol);
			entity.setLeveragedDescription(leveragedDescription);
			entity.setAffectedSoftwareDescription(affectedSoftwareDescription);
			entity.setResourcesSourceIpAddress(resourcesSourceIpAddress);
			entity.setResourcesDestinationPort(resourcesDestinationPort);
			entity.setResourcesDestinationProtocol(resourcesDestinationProtocol);
			entity.setResourcesDestination(resourcesDestination);
			entity.setScanEngine(scanEngine);
			entity.setScanVersion(scanVersion);
			entity.setScanResult(scanResult);
			entity.setRelatedIncidentId(relatedIncidentId);

			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setIncidentReportedTime(incidentReportedTime);
			entity.setIncidentClosedTime(incidentClosedTime);
			entity.setRelatedIncidentTimestamp(relatedIncidentTimestamp);

			// 補充之變數
			entity.setReporterNameUrl(reporterNameUrl);
			entity.setResourcesSourcePort(resourcesSourcePort);
			entity.setNhiImpact(nhiImpact);
			entity.setNhiProblemEquipmentOwner(nhiProblemEquipmentOwner);
			entity.setNhiProblemEquipmentUse(nhiProblemEquipmentUse);
			entity.setNhiProblemIpAddress(nhiProblemIpAddress);
			entity.setNhiProblemPort(nhiProblemPort);
			entity.setNhiProblemUrl(nhiProblemUrl);
			entity.setNhiProcess(nhiProcess);
			entity.setNhiRemark(nhiRemark);
			entity.setNhiRiskGrade(nhiRiskGrade);
			entity.setNhiRiskType(nhiRiskType);
			entity.setNhiSourceRecord(nhiSourceRecord);

			entity.setNewsManagementGroupId(newsManagementGroupId);

			entity.setTransferInType(0);
			entity.setTransferOutType(0);

			entity.setStatus(status);
			entity.setIsEnable(isEnable);
			if (entity.getCreateId() == 0 || entity.getCreateId() == 1)
				entity.setCreateId(memberId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			
			entity.setIncidentToolType(incidentToolType);
			entity.setIncidentName(incidentName);
			entity.setIncidentDescription(incidentDescription);
			entity.setIncidentVendor(incidentVendor);
			entity.setAssociatedObjected(associatedObjected.toString());

			informationExchangeDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安情資資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安情資Id
	 * @return 資安情資
	 */
	public InformationExchange modify(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);

			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");	
			Date now = new Date();
			InformationExchange entity = null;
			entity = informationExchangeDAO.get(id);
			entity.setIncidentTitle(incidentTitle);
			entity.setDescription(description);
			entity.setSort(sort);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationExchangeDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除情資資料
	 * 
	 * @param id
	 *            情資資料 Id
	 * @return 是否刪除成功
	 */
	public boolean delete(String id) {
		try {
			InformationExchange entity = informationExchangeDAO.get(id);
			informationExchangeDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得情資資料
	 * 
	 * @param id
	 *            情資資料Id
	 * @return 情資資料
	 */
	public InformationExchange getById(String id) {
		return informationExchangeDAO.get(id);
	}
	/**
	 * 情資資料是否存在
	 * 
	 * @param id
	 *            情資資料Id
	 * @return 是否存在
	 */
	public boolean isExist(String id) {
		return informationExchangeDAO.get(id) != null;
	}
	/**
	 * 情資資料名稱
	 * 
	 * @param name
	 *            情資資料名稱
	 * @return 情資資料
	 */
	public InformationExchange findByName(String name) {
		return informationExchangeDAO.getByName(name);
	}
	/**
	 * 情資資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}
	/**
	 * 審核警訊資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            警訊Id
	 * @param status
	 *            String
	 * @return 警訊資料
	 */
	public InformationExchange examine(long memberId, String id, int status, String opinion) {
		try {
			// 寄信
			Date now = new Date();
			InformationExchange entity = informationExchangeDAO.get(id);
			Member createMember = memberService.get(entity.getCreateId());
			if (status == 4 || status == 5 || status == 6) {
				String postId = ticketQueueDAO.updatePostId("information_exchange", true, "HISAC-INFO", entity.getPostId(), entity.getStixTitle());
				entity.setPostId(postId);
				if (status == 4) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(3);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To45Subject"), postId, "公開資訊");
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To45Body"), member.getName(), postId, "公開資訊");
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
					if (entity.getStixTitle().equals("ANA")) {
						// if (entity.getCategory().equals("101")) {
						// JSONObject sn_json = new JSONObject();
						// sn_json.put("TransferInId", entity.getId());
						// sn_json.put("TransferInType", 4);
						// sn_json.put("IncidentId", entity.getIncidentId());
						// sn_json.put("IncidentTitle",
						// entity.getIncidentTitle());
						// sn_json.put("IncidentDiscoveryTime",
						// entity.getIncidentDiscoveryTime());
						// sn_json.put("IncidentReportedTime",
						// entity.getIncidentReportedTime());
						// sn_json.put("Description", entity.getDescription());
						// sn_json.put("EventTypeCode", entity.getCategory());
						// sn_json.put("ReporterName",
						// entity.getReporterName());
						// sn_json.put("ResponderPartyName",
						// entity.getResponderPartyName());
						// sn_json.put("ResponderContactNumbers",
						// entity.getResponderContactNumbers());
						// sn_json.put("ResponderElectronicAddressIdentifiers",
						// entity.getResponderElectronicAddressIdentifiers());
						// sn_json.put("ImpactQualification",
						// entity.getImpactQualification());
						// sn_json.put("CoaDescription",
						// entity.getCoaDescription());
						// sn_json.put("Confidence", entity.getConfidence());
						// sn_json.put("Reference", entity.getReference());
						// sn_json.put("AffectedSoftwareDescription",
						// entity.getAffectedSoftwareDescription());
						// sn_json.put("IsEnable", true);
						// sn_json.put("Status", 1);
						// sn_json.put("TableName", "weakness_management");
						// sn_json.put("Pre", "HISAC-WEAK");
						// String json = sn_json.toString();
						// WeaknessManagement weaknessManagement =
						// weaknessManagementService.insert(memberId, json,
						// false);
						// if (weaknessManagement != null) {
						// List<InformationExchangeAttach>
						// informationExchangeAttachs =
						// informationExchangeAttachService.getByInfoExId(id);
						// if (informationExchangeAttachs != null) {
						// for (InformationExchangeAttach
						// informationExchangeAttach :
						// informationExchangeAttachs) {
						// JSONObject sn_json1 = new JSONObject();
						// sn_json1.put("WeaknessManagementId",
						// weaknessManagement.getId()); // WeaknessManagementId
						// sn_json1.put("FileDesc",
						// informationExchangeAttach.getFileDesc()); // 檔案說明
						// sn_json1.put("FileName",
						// informationExchangeAttach.getFileName()); // 檔案名稱
						// sn_json1.put("FileType",
						// informationExchangeAttach.getFileType()); // 檔案類型
						// sn_json1.put("FileSize",
						// informationExchangeAttach.getFileSize()); // 檔案大小
						// sn_json1.put("FileHash",
						// informationExchangeAttach.getFileHash()); // 檔案Hash
						// String json1 = sn_json1.toString();
						//
						// // 新增檔案
						// WeaknessManagementAttach weaknessManagementAttach =
						// weaknessManagementAttachService.insert(memberId,
						// json1, informationExchangeAttach.getFileContent());
						// if (weaknessManagementAttach == null)
						// return null;
						// }
						// } else
						// return null;
						// entity.setTransferOutType(4);
						// entity.setTransferOutId(Long.toString(weaknessManagement.getId()));
						// } else
						// return null;
						// } else {
						JSONObject sn_json = new JSONObject();
						sn_json.put("TransferInId", entity.getId());
						sn_json.put("TransferInType", 3);
						sn_json.put("IncidentId", entity.getIncidentId());
						sn_json.put("IncidentTitle", entity.getIncidentTitle());
						sn_json.put("IncidentDiscoveryTime", entity.getIncidentDiscoveryTime());
						sn_json.put("IncidentReportedTime", entity.getIncidentReportedTime());
						sn_json.put("Description", entity.getDescription());
						sn_json.put("EventTypeCode", entity.getCategory());
						sn_json.put("ReporterName", entity.getReporterName());
						sn_json.put("ResponderPartyName", entity.getResponderPartyName());
						sn_json.put("ResponderContactNumbers", entity.getResponderContactNumbers());
						sn_json.put("ResponderElectronicAddressIdentifiers", entity.getResponderElectronicAddressIdentifiers());
						sn_json.put("ImpactQualification", entity.getImpactQualification());
						sn_json.put("CoaDescription", entity.getCoaDescription());
						sn_json.put("Confidence", entity.getConfidence());
						sn_json.put("Reference", entity.getReference());
						sn_json.put("AffectedSoftwareDescription", entity.getAffectedSoftwareDescription());
						sn_json.put("IsEnable", true);
						sn_json.put("Status", 1);
						sn_json.put("TableName", "ana_management");
						sn_json.put("Pre", "HISAC");
						String json = sn_json.toString();
						AnaManagement anaManagement = anaManagementService.insert(memberId, json, false);
						if (anaManagement != null) {
							List<InformationExchangeAttach> informationExchangeAttachs = informationExchangeAttachService.getByInfoExId(id);
							if (informationExchangeAttachs != null) {
								for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachs) {
									JSONObject sn_json1 = new JSONObject();
									sn_json1.put("AnaManagementId", anaManagement.getId()); // AnaManagementId
									sn_json1.put("FileDesc", informationExchangeAttach.getFileDesc()); // 檔案說明
									sn_json1.put("FileName", informationExchangeAttach.getFileName()); // 檔案名稱
									sn_json1.put("FileType", informationExchangeAttach.getFileType()); // 檔案類型
									sn_json1.put("FileSize", informationExchangeAttach.getFileSize()); // 檔案大小
									sn_json1.put("FileHash", informationExchangeAttach.getFileHash()); // 檔案Hash
									String json1 = sn_json1.toString();

									// 新增檔案
									AnaManagementAttach anaManagementAttach = anaManagementAttachService.insert(memberId, json1, informationExchangeAttach.getFileContent());
									if (anaManagementAttach == null)
										return null;
								}
							} else
								return null;
							entity.setTransferOutType(3);
							entity.setTransferOutId(Long.toString(anaManagement.getId()));
						} else
							return null;
						// }
					} else {
						JSONObject sn_json = new JSONObject();
						sn_json.put("TransferInId", entity.getId());
						sn_json.put("TransferInType", 2);
						sn_json.put("Title", entity.getIncidentTitle());												
						sn_json.put("SourceLink", entity.getReporterNameUrl());
						String description = entity.getDescription();
						if (entity.getCoaDescription() != null && !entity.getCoaDescription().equals("")) {
							description = description + "\n\n建議措施：\n" + entity.getCoaDescription();
						}
						if (entity.getAffectedSoftwareDescription() != null && !entity.getAffectedSoftwareDescription().equals("")) {
							description = description + "\n\n影響平台：\n" + entity.getAffectedSoftwareDescription();
						}
						if (entity.getReference() != null && !entity.getReference().equals("")) {
							description = description + "\n\n參考資料：\n" + entity.getReference();
						}
						sn_json.put("Content", description);
						sn_json.put("NewsManagementGroupId", entity.getNewsManagementGroupId());
						sn_json.put("SourceName", entity.getReporterName());
						sn_json.put("PostType", 1);
						sn_json.put("ContentType", "1");
						sn_json.put("IsBreakLine", true);
						sn_json.put("IsEnable", true);
						sn_json.put("IsPublic", false);
						sn_json.put("Status", 1);
						sn_json.put("TableName", "news_management");
						sn_json.put("Pre", "HISAC");
						String json = sn_json.toString();
						NewsManagement newsManagement = newsManagementService.insert(memberId, json, false);
						if (newsManagement != null) {
							List<InformationExchangeAttach> informationExchangeAttachs = informationExchangeAttachService.getByInfoExId(id);
							if (informationExchangeAttachs != null) {
								for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachs) {
									JSONObject sn_json1 = new JSONObject();
									sn_json1.put("NewsManagement", newsManagement.getId()); // NewsManagement
									sn_json1.put("FileDesc", informationExchangeAttach.getFileDesc()); // 檔案說明
									sn_json1.put("FileName", informationExchangeAttach.getFileName()); // 檔案名稱
									sn_json1.put("FileType", informationExchangeAttach.getFileType()); // 檔案類型
									sn_json1.put("FileSize", informationExchangeAttach.getFileSize()); // 檔案大小
									sn_json1.put("FileHash", informationExchangeAttach.getFileHash()); // 檔案Hash
									String json1 = sn_json1.toString();

									// 新增檔案
									NewsManagementAttach newsManagementAttach = newsManagementAttachService.insert(memberId, json1, informationExchangeAttach.getFileContent());
									if (newsManagementAttach == null)
										return null;
								}
							} else
								return null;
							entity.setTransferOutType(2);
							entity.setTransferOutId(Long.toString(newsManagement.getId()));
						} else
							return null;
					}
				}
				if (status == 5) {
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(5);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To45Subject"), postId, "警訊單");
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To45Body"), member.getName(), postId, "警訊單");
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
					JSONObject sn_json = new JSONObject();
					sn_json.put("TransferInId", entity.getId());
					sn_json.put("TransferInType", 1);
					sn_json.put("SourceCode", entity.getSourceCode());
					sn_json.put("AlertCode", entity.getStixTitle());
					sn_json.put("ExternalId", entity.getIncidentId());
					sn_json.put("Subject", entity.getIncidentTitle());
					sn_json.put("FindDate", entity.getIncidentDiscoveryTime());
					sn_json.put("Description", entity.getDescription());
					sn_json.put("Contents", entity.getDescription());
					sn_json.put("EventCode", entity.getCategory());
					sn_json.put("ImpactLevel", Integer.toString(entity.getImpactQualification()));
					sn_json.put("Suggestion", entity.getCoaDescription());
					sn_json.put("Reference", entity.getReference());
					sn_json.put("Determine", entity.getLeveragedDescription());
					sn_json.put("AffectPlatform", entity.getAffectedSoftwareDescription());
					sn_json.put("IsEnable", true);
					sn_json.put("IsReply", false);
					sn_json.put("IsSms", false);
					sn_json.put("Status", "1");
					sn_json.put("TableName", "message");
					sn_json.put("Pre", "HISAC-MES");
					String json = sn_json.toString();
					Message message = messageService.insert(memberId, json, false);
					if (message != null) {
						int fileNum = 0;
						List<InformationExchangeAttach> informationExchangeAttachs = informationExchangeAttachService.getByInfoExId(id);
						if (informationExchangeAttachs != null) {
							for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachs) {
								fileNum++;
								JSONObject sn_json1 = new JSONObject();
								sn_json1.put("MessageId", message.getId()); // MessageId
								sn_json1.put("FileDesc", informationExchangeAttach.getFileDesc()); // 檔案說明
								sn_json1.put("FileName", informationExchangeAttach.getFileName()); // 檔案名稱
								sn_json1.put("FileType", informationExchangeAttach.getFileType()); // 檔案類型
								sn_json1.put("FileSize", informationExchangeAttach.getFileSize()); // 檔案大小
								sn_json1.put("FileHash", informationExchangeAttach.getFileHash()); // 檔案Hash
								String json1 = sn_json1.toString();

								// 新增檔案
								Boolean insert = messagePostAttachService.insert(memberId, json1, informationExchangeAttach.getFileContent());
								if (!insert)
									return null;
							}
						} else
							return null;
						// 檔案須三筆
						for (int i = fileNum; i < 3; i++) {
							JSONObject sn_json1 = new JSONObject();
							sn_json1.put("MessageId", message.getId()); // MessageId
							String json1 = sn_json1.toString();
							// 新增檔案
							if (!messagePostAttachService.insertFileDesc(memberId, json1))
								return null;
						}
						entity.setTransferOutType(1);
						entity.setTransferOutId(message.getId());
					} else
						return null;
				}
				if (status == 6) {
					String nisac_json = informationExchangeNisacService.exportToNisac(id);
					if (!nisac_json.equals("")) {
						JSONObject obj = new JSONObject(nisac_json);
						String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
						String sourceContentXML = obj.isNull("SourceContentXML") == true ? null : obj.getString("SourceContentXML");

						if (incidentId != null && !incidentId.equals("")) {
							entity.setNisacIncidentId(incidentId);
						if (sourceContentXML != null)
							entity.setNisacSourceContentXML(sourceContentXML.getBytes());
						}
						else
							return null;
					} else
						return null;
				}
			}
			if (status == 2) {
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To2Body"), createMember.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), createMember.getEmail(), createMember.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			if (status == 8) {
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To8Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation3To8Body"), createMember.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), createMember.getEmail(), createMember.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			
			if (entity.getStatus().equals(4) || entity.getStatus().equals(5) || entity.getStatus().equals(6))
				status = 10 * status + entity.getStatus();  
			
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationExchangeDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
