package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.dao.WeaknessManagementDAO;
import tw.gov.mohw.hisac.web.domain.WeaknessManagement;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 軟體弱點漏洞通告服務
 */
@Service
public class WeaknessManagementService {
	@Autowired
	WeaknessManagementDAO weaknessManagementDAO;

	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;

	static String weaknessGlobalData = null;

	public void setGlobalData(String globalData) {
		weaknessGlobalData = globalData;
	}

	public void resetGlobalData() {
		weaknessGlobalData = null;
	}

	public String getGlobalData() {
		return weaknessGlobalData;
	}

	/**
	 * 取得所有軟體弱洞漏洞通告資料
	 * 
	 * @return 軟體弱洞漏洞通告資料
	 */
	public List<WeaknessManagement> getAll() {
		return weaknessManagementDAO.getAll();
	}

	/**
	 * 取得軟體弱洞漏洞通告資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱洞漏洞通告資料
	 */
	public List<ViewWeaknessManagementMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得軟體弱洞漏洞通告資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱洞漏洞通告資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增軟體弱洞漏洞通告資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            軟體弱洞漏洞通告資料
	 * @param isApply
	 *            是否核可
	 * @return 軟體弱洞漏洞通告資料
	 */
	public WeaknessManagement insert(Long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? "" : obj.getString("IncidentTitle");
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentReportedTime"), "yyyy-MM-dd");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String eventTypeCode = "101";
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

			// 流程紀錄用 - 開始
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String postId = ticketQueueDAO.insertPostId(tableName, isApply, pre, "WEAK");
			// 流程紀錄用 - 開始

			Date now = new Date();
			WeaknessManagement entity = new WeaknessManagement();
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
			weaknessManagementDAO.insert(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新軟體弱洞漏洞通告資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            軟體弱洞漏洞通告資料
	 * @param isApply
	 *            是否核可
	 * @return 軟體弱洞漏洞通告資料
	 */
	public WeaknessManagement update(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			String incidentTitle = obj.isNull("IncidentTitle") == true ? null : obj.getString("IncidentTitle");
			Date incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentDiscoveryTime"), "yyyy-MM-dd");
			Date incidentReportedTime = obj.isNull("IncidentReportedTime") == true ? new Date() : WebDatetime.parse(obj.getString("IncidentReportedTime"), "yyyy-MM-dd");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String eventTypeCode = "101";
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

			Date now = new Date();
			WeaknessManagement entity = weaknessManagementDAO.get(id);

			// 流程紀錄用 - 開始
			String postId = ticketQueueDAO.updatePostId("weakness_management", isApply, "HISAC", entity.getPostId(), "WEAK");
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
			weaknessManagementDAO.update(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除軟體弱洞漏洞通告資料
	 * 
	 * @param id
	 *            軟體弱洞漏洞通告Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			WeaknessManagement entity = weaknessManagementDAO.get(id);
			weaknessManagementDAO.delete(entity);
			resetGlobalData();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 軟體弱洞漏洞通告資料是否存在
	 * 
	 * @param id
	 *            軟體弱洞漏洞通告資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return weaknessManagementDAO.get(id) != null;
	}

	/**
	 * 取得軟體弱洞漏洞通告資料
	 * 
	 * @param id
	 *            軟體弱洞漏洞通告資料Id
	 * @return 軟體弱洞漏洞通告資料
	 */
	public WeaknessManagement get(Long id) {
		return weaknessManagementDAO.get(id);
	}

	/**
	 * 取得軟體弱洞漏洞通告資料
	 * 
	 * @param id
	 *            軟體弱洞漏洞通告資料Id
	 * @return 軟體弱洞漏洞通告資料
	 */
	public ViewWeaknessManagementMember getByDetail(Long id) {
		return weaknessManagementDAO.getByDetail(id);
	}

	/**
	 * 審核軟體弱點漏洞通告資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            軟體弱點漏洞通告Id
	 * @param status
	 *            String
	 * @return 軟體弱點漏洞通告資料
	 */
	public WeaknessManagement examine(long memberId, String id, String status) {
		try {
			Date now = new Date();
			WeaknessManagement entity = weaknessManagementDAO.get(Long.parseLong(id, 10));
			Member member = memberService.get(entity.getModifyId());

			// 3-審核中 → 4-已公告
			if (status.equals("4")) {

				String postId = ticketQueueDAO.updatePostId("weakness_management", true, "HISAC", entity.getPostId(), "WEAK");

				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC軟體弱點漏洞通告(postId)審核通過通知
				// 內容：entity.getName()，您好！您所新增之軟體弱點漏洞通告(entity.getPostId())，正式軟體弱點漏洞通告編號為：postId，經H-ISAC內容審核者審核通過並已發布，特此通知，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3To4Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3To4Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				entity.setPostId(postId);
			}

			// 3-審核中 → 6-編輯中(退回)
			if (status.equals("6")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC軟體弱點漏洞通告(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所新增之軟體弱點漏洞通告(entity.getPostId())，經H-ISAC內容審核者審核退回，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3To6Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3To6Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			// 3-審核中 → 2-撤銷中
			if (status.equals("2")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC軟體弱點漏洞通告(entity.getPostId())審核撤銷通知
				// 內容：entity.getName()，您好！您所新增之軟體弱點漏洞通告(entity.getPostId())，經H-ISAC內容審核者審核撤銷，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3Back2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailWeakness3Back2Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			entity.setStatus(Long.parseLong(status));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			weaknessManagementDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得軟體弱點漏洞通告資料
	 * 
	 * @param id
	 *            軟體弱點漏洞通告資料Id
	 * @return 軟體弱點漏洞通告資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return weaknessManagementDAO.getById(id);
	}

	/**
	 * 取得軟體弱點漏洞通告資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料
	 */
	public List<ViewWeaknessManagementMember> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得軟體弱點漏洞通告資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得軟體弱點漏洞通告資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得軟體弱點漏洞通告資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return weaknessManagementDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
