package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.dao.MessageDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 警訊管理服務
 */
@Service
public class MessageService {
	@Autowired
	MessageDAO messageDAO;
	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;

	/**
	 * 取得所有警訊資料
	 * 
	 * @return 警訊資料
	 */
	public List<Message> getAll() {
		return messageDAO.getAll();
	}
	/**
	 * 取得警訊資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	public List<Message> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得警訊資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得警訊資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	public List<ViewMessageAlertEvent> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得警訊資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增警訊資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊資料
	 * @param isApply
	 *            Boolean
	 * @return 警訊資料
	 */
	public Message insert(Long memberId, String json, Boolean isApply) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			String eventCode = obj.isNull("EventCode") == true ? null : obj.getString("EventCode");
			String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");
			String externalId = obj.isNull("ExternalId") == true ? null : obj.getString("ExternalId");
			Date findDate = new Date();
			String strfindDate = obj.isNull("FindDate") == true ? null : obj.getString("FindDate");
			if (strfindDate != null)
				findDate = new SimpleDateFormat("yyyy-MM-dd").parse(strfindDate);
			String subject = obj.isNull("Subject") == true ? null : obj.getString("Subject");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String suggestion = obj.isNull("Suggestion") == true ? null : obj.getString("Suggestion");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String determine = obj.isNull("Determine") == true ? null : obj.getString("Determine");
			String contents = obj.isNull("Contents") == true ? null : obj.getString("Contents");
			String affectPlatform = obj.isNull("AffectPlatform") == true ? null : obj.getString("AffectPlatform");
			String impactLevel = obj.isNull("ImpactLevel") == true ? null : obj.getString("ImpactLevel");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
			Boolean isSms = obj.isNull("IsSms") == true ? null : obj.getBoolean("IsSms");
			Boolean isTest = obj.isNull("IsTest") == true ? false : obj.getBoolean("IsTest");
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			int transferInType = obj.isNull("TransferInType") == true ? 0 : obj.getInt("TransferInType");
			String transferInId = obj.isNull("TransferInId") == true ? "" : obj.getString("TransferInId");
			int transferOutType = obj.isNull("TransferOutType") == true ? 0 : obj.getInt("TransferOutType");
			String transferOutId = obj.isNull("TransferOutId") == true ? "" : obj.getString("TransferOutId");
			String smsFormat = obj.isNull("SmsFormat") == true ? null : obj.getString("SmsFormat");
			
			String postId = ticketQueueDAO.insertPostId(tableName, isApply, pre, alertCode);
			Message entity = new Message();
			entity.setId(WebCrypto.generateUUID());
			entity.setPostId(postId);
			entity.setAlertCode(alertCode);
			entity.setEventCode(eventCode);
			entity.setSourceCode(sourceCode);
			entity.setExternalId(externalId);
			entity.setFindDate(findDate);
			entity.setSubject(subject);
			entity.setDescription(description);
			entity.setSuggestion(suggestion);
			entity.setReference(reference);
			entity.setDetermine(determine);
			entity.setContents(contents);
			entity.setAffectPlatform(affectPlatform);
			entity.setImpactLevel(impactLevel);
			entity.setStatus(status);
			entity.setIsEnable(isEnable);
			entity.setTransferInType(transferInType);
			entity.setTransferInId(transferInId);
			entity.setTransferOutType(transferOutType);
			entity.setTransferOutId(transferOutId);
			entity.setIsReply(isReply);
			entity.setIsSms(isSms);
			entity.setIsTest(isTest);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setSmsFormat(smsFormat);

			messageDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新警訊資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊Id
	 * @param isApply
	 *            Boolean
	 * @return 警訊資料
	 */
	public Message update(long memberId, String json, Boolean isApply) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			String eventCode = obj.isNull("EventCode") == true ? null : obj.getString("EventCode");
			String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");
			String externalId = obj.isNull("ExternalId") == true ? null : obj.getString("ExternalId");
			Date findDate = new Date();
			String strfindDate = obj.isNull("FindDate") == true ? null : obj.getString("FindDate");
			if (strfindDate != null)
				findDate = new SimpleDateFormat("yyyy-MM-dd").parse(strfindDate);
			String subject = obj.isNull("Subject") == true ? null : obj.getString("Subject");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String suggestion = obj.isNull("Suggestion") == true ? null : obj.getString("Suggestion");
			String reference = obj.isNull("Reference") == true ? null : obj.getString("Reference");
			String determine = obj.isNull("Determine") == true ? null : obj.getString("Determine");
			String contents = obj.isNull("Contents") == true ? null : obj.getString("Contents");
			String affectPlatform = obj.isNull("AffectPlatform") == true ? null : obj.getString("AffectPlatform");
			String impactLevel = obj.isNull("ImpactLevel") == true ? null : obj.getString("ImpactLevel");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Boolean isReply = obj.isNull("IsReply") == true ? null : obj.getBoolean("IsReply");
			Boolean isSms = obj.isNull("IsSms") == true ? null : obj.getBoolean("IsSms");
			Boolean isTest = obj.isNull("IsTest") == true ? false : obj.getBoolean("IsTest");
			String smsFormat = obj.isNull("SmsFormat") == true ? null : obj.getString("SmsFormat");

			Message entity = messageDAO.get(id);
			String postId = ticketQueueDAO.updatePostId("message", isApply, "HISAC-MES", entity.getPostId(), alertCode);

			entity.setPostId(postId);
			entity.setAlertCode(alertCode);
			entity.setEventCode(eventCode);
			entity.setSourceCode(sourceCode);
			entity.setExternalId(externalId);
			entity.setFindDate(findDate);
			entity.setSubject(subject);
			entity.setDescription(description);
			entity.setSuggestion(suggestion);
			entity.setReference(reference);
			entity.setDetermine(determine);
			entity.setContents(contents);
			entity.setAffectPlatform(affectPlatform);
			entity.setImpactLevel(impactLevel);
			entity.setStatus(status);
			entity.setIsEnable(isEnable);
			entity.setIsReply(isReply);
			entity.setIsSms(isSms);
			entity.setIsTest(isTest);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setSmsFormat(smsFormat);
			messageDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除警訊資料
	 * 
	 * @param id
	 *            警訊Id
	 * @return 是否刪除成功
	 */
	public boolean delete(String id) {
		try {
			Message entity = messageDAO.get(id);
			messageDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
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
	public Message examine(long memberId, String id, String status, String opinion) {
		try {
			Date now = new Date();
			Message entity = messageDAO.get(id);
			Member member = memberService.get(entity.getModifyId());
			if (status.equals("5") || status.equals("8") && member.getIsEnable()) {

				String postId = ticketQueueDAO.updatePostId("message", true, "HISAC-MES", entity.getPostId(), entity.getAlertCode());

				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC警訊單(postId)審核通過通知
				// 內容：entity.getName()，您好！您所新增之警訊單(entity.getPostId())，正式警訊編號為：postId，經H-ISAC警訊審核者審核通過並已發布，特此通知，謝謝！
				/** @author chuyufeng **/
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To4-1Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To4-1Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

				entity.setPostId(postId);
				entity.setPostDateTime(now);
			}
			if (status.equals("2") && member.getIsEnable()) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC警訊單(entity.getPostId())審核撤銷通知
				// 內容：entity.getName()，您好！您所新增之警訊單(entity.getPostId())，經H-ISAC警訊審核者審核撤銷，請您儘快撥冗進行後續處理，謝謝！
				/** @author chuyufeng **/
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3Back2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3Back2Body"), member.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}
			if (status.equals("10") && member.getIsEnable()) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC警訊單(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所新增之警訊單(entity.getPostId())，經H-ISAC警訊審核者審核退回，請您儘快撥冗進行後續處理，謝謝！
				/** @author chuyufeng **/
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To10Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To10Body"), member.getName(), entity.getPostId(), opinion);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messageDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 取得警訊資料
	 * 
	 * @param id
	 *            警訊資料Id
	 * @return 警訊資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return messageDAO.getById(id);
	}
	/**
	 * 警訊資料是否存在
	 * 
	 * @param id
	 *            警訊資料Id
	 * @return 是否存在
	 */
	public boolean isExist(String id) {
		return messageDAO.get(id) != null;
	}
	// /**
	// * 警訊查詢總比數
	// *
	// * @param baseMemberRole
	// * baseMemberId
	// *
	// * @return 警訊查詢總比數
	// */
	// public int total(String json, long baseMemberId, MemberRoleVariable
	// baseMemberRole) {
	// int total = 0;
	// List<ViewMessageAlertEvent> nots = getList(json);
	// // 取得全部MessagePostRelease
	// List<ViewMessagePostReleaseOrg> viewMessagePostReleaseOrgs =
	// messagePostReleaseService.getAll();
	// // 取得全部 orgSign
	// List<OrgSign> orgSigns = orgSignService.getAll();
	// // 取得登入者的member資料
	// Member member = memberService.get(baseMemberId);
	// if (nots != null) {
	// for (ViewMessageAlertEvent not : nots) {
	// boolean isNeedAuthReview = false;
	// boolean isNeedSaleReview = false;
	// boolean isRecipient = false;
	// switch (not.getStatus()) {
	// case "1":
	// // 建立者 or hisac管理者
	// if (not.getModifyId().equals(baseMemberId) || baseMemberRole.IsHisac)
	// total++;
	// break;
	// case "2":
	// case "3":
	// case "9":
	// case "10":
	// // 建立者 or hisac審核者 or hisac管理者
	// if (not.getModifyId().equals(baseMemberId) ||
	// baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac)
	// total++;
	// break;
	// case "5":
	// case "7":
	// if (viewMessagePostReleaseOrgs != null) {
	// for (ViewMessagePostReleaseOrg viewMessagePostReleaseOrg :
	// viewMessagePostReleaseOrgs) {
	// // 是否為收件者(會員機構聯絡人或權責單位聯絡人)
	// if (viewMessagePostReleaseOrg.getMessageId().equals(not.getId())
	// && member.getOrgId().equals(viewMessagePostReleaseOrg.getOrgId())
	// && ((baseMemberRole.IsApplyContact
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getOrgType().equals("2")
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable())
	// || ((baseMemberRole.IsMemberContact
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getOrgType().equals("3")
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable()))))
	// {
	// isRecipient = true;
	// break;
	// }
	// // 是否需上級機關審核副知
	// if (baseMemberRole.IsApplySign
	// && (viewMessagePostReleaseOrg.getIsCC() ||
	// viewMessagePostReleaseOrg.getIsReview())
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable()
	// && orgSigns != null) {
	// for (OrgSign orgSign : orgSigns) {
	// if (orgSign.getOrgId().equals(viewMessagePostReleaseOrg.getOrgId())
	// && orgSign.getParentOrgId().equals(member.getOrgId())
	// &&
	// orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("2"))
	// {
	// isNeedAuthReview = true;
	// break;
	// }
	// }
	// }
	// // 是否需業務管理機關審核副知
	// if (baseMemberRole.IsApplySign
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable()
	// && orgSigns != null) {
	// for (OrgSign orgSign : orgSigns) {
	// if (orgSign.getOrgId().equals(viewMessagePostReleaseOrg.getOrgId())
	// && orgSign.getParentOrgId().equals(member.getOrgId())
	// &&
	// orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("1"))
	// {
	// isNeedSaleReview = true;
	// break;
	// }
	// }
	// }
	// }
	// }
	// // 建立者 or hisac審核者 or hisac管理者 or 收件者(會員機構聯絡人或權責單位聯絡人) or 是否需上級機關審核副知
	// or 是否需業務管理機關審核副知
	// if (not.getModifyId().equals(baseMemberId) ||
	// baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac || isRecipient
	// || isNeedAuthReview || isNeedSaleReview)
	// total++;
	// break;
	// case "8":
	// if (viewMessagePostReleaseOrgs != null) {
	// // 是否為收件者(會員機構聯絡人或權責單位聯絡人)
	// for (ViewMessagePostReleaseOrg viewMessagePostReleaseOrg :
	// viewMessagePostReleaseOrgs) {
	// if (viewMessagePostReleaseOrg.getMessageId().equals(not.getId())
	// && member.getOrgId().equals(viewMessagePostReleaseOrg.getOrgId())
	// && ((baseMemberRole.IsApplyContact
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getOrgType().equals("2")
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable())
	// || ((baseMemberRole.IsMemberContact
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getOrgType().equals("3")
	// &&
	// orgService.getDataById(viewMessagePostReleaseOrg.getOrgId()).getIsEnable()))))
	// {
	// isRecipient = true;
	// break;
	// }
	// }
	// }
	// // 建立者 or hisac審核者 or hisac管理者 or 收件者(會員機構聯絡人或權責單位聯絡人)
	// if (not.getModifyId().equals(baseMemberId) ||
	// baseMemberRole.IsHisacContentSign || baseMemberRole.IsHisac ||
	// isRecipient)
	// total++;
	// break;
	// }
	// }
	// }
	// return total;
	// }

}
