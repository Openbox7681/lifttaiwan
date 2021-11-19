package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.AnaManagementDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.AnaManagement;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;

/**
 * 資安訊息情報服務
 */
@Service
public class AnaManagementService {
	@Autowired
	AnaManagementDAO anaManagementDAO;

	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private SubscribeMemberService subscribeMemberService;	
	
	static String anaGlobalData = null;

	public void setGlobalData(String globalData) {
		anaGlobalData = globalData;
	}

	public void resetGlobalData() {
		anaGlobalData = null;
	}

	public String getGlobalData() {
		return anaGlobalData;
	}

	/**
	 * 取得所有資安訊息情報資料
	 * 
	 * @return 資安訊息情報資料
	 */
	public List<AnaManagement> getAll() {
		return anaManagementDAO.getAll();
	}

	/**
	 * 取得資安訊息情報資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料
	 */
	public List<ViewAnaManagementMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得報表
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報表
	 */
	public List<SpAnaManagementReport> getReport(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getReport(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得資安訊息情報資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增資安訊息情報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安訊息情報資料
	 * @param isApply
	 *            是否允許
	 * @return 資安訊息情報資料
	 */
	public AnaManagement insert(Long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? "" : obj.getString("IncidentTitle");
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentReportedTime"), "yyyy-MM-dd");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String eventTypeCode = obj.isNull("EventTypeCode") == true ? "" : obj.getString("EventTypeCode");
			String reporterName = obj.isNull("ReporterName") == true ? "" : obj.getString("ReporterName");
			String responderPartyName = obj.isNull("ResponderPartyName") == true ? null : obj.getString("ResponderPartyName");
			String responderContactNumbers = obj.isNull("ResponderContactNumbers") == true ? null : obj.getString("ResponderContactNumbers");
			String responderElectronicAddressIdentifiers = obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null : obj.getString("ResponderElectronicAddressIdentifiers");
			Long impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getLong("ImpactQualification");
			String coaDescription = obj.isNull("CoaDescription") == true ? null : obj.getString("CoaDescription");
			String confidence = obj.isNull("Confidence") == true ? null : obj.getString("Confidence");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String affectedSoftwareDescription = obj.isNull("AffectedSoftwareDescription") == true ? null : obj.getString("AffectedSoftwareDescription");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Boolean isMedical = obj.isNull("IsMedical") == true ? false : obj.getBoolean("IsMedical");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");	

			// 流程紀錄用 - 開始
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String postId = ticketQueueDAO.insertPostId(tableName, isApply, pre, "ANA");
			// 流程紀錄用 - 開始

			Date now = new Date();
			AnaManagement entity = new AnaManagement();
			entity.setPostId(postId);
			entity.setIncidentId(incidentId);
			entity.setIncidentTitle(incidentTitle);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setIncidentReportedTime(incidentReportedTime);
			entity.setDescription(description);
			entity.setEventTypeCode(eventTypeCode);
			entity.setReporterName(reporterName);
			entity.setResponderPartyName(responderPartyName);
			entity.setResponderContactNumbers(responderContactNumbers);
			entity.setResponderElectronicAddressIdentifiers(responderElectronicAddressIdentifiers);
			entity.setImpactQualification(impactQualification);
			entity.setCoaDescription(coaDescription);
			entity.setConfidence(confidence);
			entity.setReference(reference);
			entity.setAffectedSoftwareDescription(affectedSoftwareDescription);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setIsMedical(isMedical);
			entity.setSort(sort);
			entity.setIsMalware(false);
			anaManagementDAO.insert(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安訊息情報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安訊息情報Id
	 * @param isApply
	 *            是否允許
	 * @return 資安訊息情報資料
	 */
	public AnaManagement update(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentReportedTime"), "yyyy-MM-dd");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
			String reporterName = obj.isNull("ReporterName") == true ? null : obj.getString("ReporterName");
			String responderPartyName = obj.isNull("ResponderPartyName") == true ? null : obj.getString("ResponderPartyName");
			String responderContactNumbers = obj.isNull("ResponderContactNumbers") == true ? null : obj.getString("ResponderContactNumbers");
			String responderElectronicAddressIdentifiers = obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null : obj.getString("ResponderElectronicAddressIdentifiers");
			Long impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getLong("ImpactQualification");
			String coaDescription = obj.isNull("CoaDescription") == true ? null : obj.getString("CoaDescription");
			String confidence = obj.isNull("Confidence") == true ? null : obj.getString("Confidence");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String affectedSoftwareDescription = obj.isNull("AffectedSoftwareDescription") == true ? null : obj.getString("AffectedSoftwareDescription");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Boolean isMedical = obj.isNull("IsMedical") == true ? false : obj.getBoolean("IsMedical");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");		
			
			Date now = new Date();
			AnaManagement entity = anaManagementDAO.get(id);

			// 流程紀錄用 - 開始
			String postId = ticketQueueDAO.updatePostId("ana_management", isApply, "HISAC", entity.getPostId(), "ANA");
			// 流程紀錄用 - 結束

			entity.setPostId(postId);
			entity.setIncidentId(incidentId);
			entity.setIncidentTitle(incidentTitle);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setIncidentReportedTime(incidentReportedTime);
			entity.setDescription(description);
			entity.setEventTypeCode(eventTypeCode);
			entity.setReporterName(reporterName);
			entity.setResponderPartyName(responderPartyName);
			entity.setResponderContactNumbers(responderContactNumbers);
			entity.setResponderElectronicAddressIdentifiers(responderElectronicAddressIdentifiers);
			entity.setImpactQualification(impactQualification);
			entity.setCoaDescription(coaDescription);
			entity.setConfidence(confidence);
			entity.setReference(reference);
			entity.setAffectedSoftwareDescription(affectedSoftwareDescription);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setIsMedical(isMedical);
			entity.setSort(sort);
			anaManagementDAO.update(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 異動資安訊息情報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安訊息情報Id
	 * @param isApply
	 *            是否允許
	 * @return 資安訊息情報資料
	 */
	public AnaManagement modify(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentReportedTime"), "yyyy-MM-dd");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String eventTypeCode = obj.isNull("EventTypeCode") == true ? null : obj.getString("EventTypeCode");
			String reporterName = obj.isNull("ReporterName") == true ? null : obj.getString("ReporterName");
			String responderPartyName = obj.isNull("ResponderPartyName") == true ? null : obj.getString("ResponderPartyName");
			String responderContactNumbers = obj.isNull("ResponderContactNumbers") == true ? null : obj.getString("ResponderContactNumbers");
			String responderElectronicAddressIdentifiers = obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null : obj.getString("ResponderElectronicAddressIdentifiers");
			Long impactQualification = obj.isNull("ImpactQualification") == true ? 0 : obj.getLong("ImpactQualification");
			String coaDescription = obj.isNull("CoaDescription") == true ? null : obj.getString("CoaDescription");
			String confidence = obj.isNull("Confidence") == true ? null : obj.getString("Confidence");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String affectedSoftwareDescription = obj.isNull("AffectedSoftwareDescription") == true ? null : obj.getString("AffectedSoftwareDescription");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Boolean isMedical = obj.isNull("IsMedical") == true ? false : obj.getBoolean("IsMedical");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");		

			Date now = new Date();
			AnaManagement entity = anaManagementDAO.get(id);

			entity.setIncidentId(incidentId);
			entity.setIncidentTitle(incidentTitle);
			entity.setIncidentDiscoveryTime(incidentDiscoveryTime);
			entity.setIncidentReportedTime(incidentReportedTime);
			entity.setDescription(description);
			entity.setEventTypeCode(eventTypeCode);
			entity.setReporterName(reporterName);
			entity.setResponderPartyName(responderPartyName);
			entity.setResponderContactNumbers(responderContactNumbers);
			entity.setResponderElectronicAddressIdentifiers(responderElectronicAddressIdentifiers);
			entity.setImpactQualification(impactQualification);
			entity.setCoaDescription(coaDescription);
			entity.setConfidence(confidence);
			entity.setReference(reference);
			entity.setAffectedSoftwareDescription(affectedSoftwareDescription);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setIsMedical(isMedical);
			entity.setSort(sort);
			anaManagementDAO.update(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 停用資安訊息情報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @return 最新消息資料
	 */
	public AnaManagement disable(long memberId, Long id) {
		try {
			Date now = new Date();
			AnaManagement entity = anaManagementDAO.get(id);
			entity.setIsEnable(false);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			anaManagementDAO.update(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除資安訊息情報資料
	 * 
	 * @param id
	 *            資安訊息情報Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			AnaManagement entity = anaManagementDAO.get(id);
			anaManagementDAO.delete(entity);
			resetGlobalData();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 資安訊息情報資料是否存在
	 * 
	 * @param id
	 *            資安訊息情報資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return anaManagementDAO.get(id) != null;
	}

	/**
	 * 取得資安訊息情報資料
	 * 
	 * @param id
	 *            資安訊息情報資料Id
	 * @return 資安訊息情報資料
	 */
	public AnaManagement get(Long id) {
		return anaManagementDAO.get(id);
	}

	/**
	 * 取得資安訊息情報資料
	 * 
	 * @param id
	 *            資安訊息情報資料Id
	 * @return 資安訊息情報資料
	 */
	public ViewAnaManagementMember getByDetail(Long id) {
		return anaManagementDAO.getByDetail(id);
	}

	/**
	 * 審核資安訊息情報資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            資安訊息情報Id
	 * @param status
	 *            String
	 * @return 資安訊息情報資料
	 */
	public AnaManagement examine(long memberId, String id, String status) {
		try {
			Date now = new Date();
			AnaManagement entity = anaManagementDAO.get(Long.parseLong(id, 10));
			Member member = memberService.get(entity.getModifyId());

			// 3-審核中 → 4-已公告
			if (status.equals("4")) {

				String postId = ticketQueueDAO.updatePostId("ana_management", true, "HISAC", entity.getPostId(), "ANA");

				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC資安訊息情報(postId)審核通過通知
				// 內容：entity.getName()，您好！您所新增之資安訊息情報(entity.getPostId())，正式資安訊息情報編號為：postId，經H-ISAC內容審核者審核通過並已發布，特此通知，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3To4Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3To4Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

				// 寄信給訂閱者
				String messageValue_SubscribeSubject = resourceMessageService.getMessageValue("mailSubscribeSubject");
				String messageValue_SubscribeBody = resourceMessageService.getMessageValue("mailSubscribeBody");
				String eventTypeName = "資安訊息情報";
				JSONArray recipientBccs =  new JSONArray();				
				// String recipients = "hisac-cs@mohw.gov.tw";
				String recipients = resourceMessageService.getMessageValue("globalFooterEmail");
				List<ViewSubscribeMember> viewSubscribeMembers = null;								
				viewSubscribeMembers = subscribeMemberService.getBySubscribeName(eventTypeName);
				if (viewSubscribeMembers != null) {
					for (ViewSubscribeMember viewSubscribeMember : viewSubscribeMembers) {
						recipientBccs.put(viewSubscribeMember.getEmail());										
					}
				}									
				String mailSubject_Subscribe = messageValue_SubscribeSubject;
				String mailBody_Subscribe = MessageFormat.format(messageValue_SubscribeBody, eventTypeName,entity.getModifyTime(), entity.getIncidentTitle(), entity.getDescription());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(),recipients , null, recipientBccs, mailSubject_Subscribe, mailBody_Subscribe, null);												
				entity.setPostId(postId);
			}

			// 3-審核中 → 6-編輯中(退回)
			if (status.equals("6")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC資安訊息情報(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所新增之資安訊息情報(entity.getPostId())，經H-ISAC內容審核者審核退回，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3To6Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3To6Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			// 3-審核中 → 2-撤銷中
			if (status.equals("2")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC資安訊息情報(entity.getPostId())審核撤銷通知
				// 內容：entity.getName()，您好！您所新增之資安訊息情報(entity.getPostId())，經H-ISAC內容審核者審核撤銷，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3Back2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailAna3Back2Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			entity.setStatus(Long.parseLong(status));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			anaManagementDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得資安訊息情報資料
	 * 
	 * @param id
	 *            資安訊息情報資料Id
	 * @return 資安訊息情報資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return anaManagementDAO.getById(id);
	}

	/**
	 * 取得資安訊息情報資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料
	 */
	public List<ViewAnaManagementMember> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得資安訊息情報資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得資安訊息情報資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得資安訊息情報資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安訊息情報資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
