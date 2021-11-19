package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.dao.MessagePostReleaseDAO;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MessagePostRelease;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewMessagePostReleaseOrg;

/**
 * MessagePost服務
 */
@Service
public class MessagePostReleaseService {
	@Autowired
	MessagePostReleaseDAO messagePostReleaseDAO;
	@Autowired
	MessageService messageService;
	@Autowired
	MessagePostService messagePostService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;
	@Autowired	
	MemberService memberService;
	@Autowired
	MemberRoleService memberRoleService;
	@Autowired
	OrgSignService orgSignService;
	@Autowired
	OrgService orgService;
		
	/**
	 * 取得所有MessagePostRelease
	 * 
	 * @return MessagePostRelease
	 */
	public List<ViewMessagePostReleaseOrg> getAll() {
		return messagePostReleaseDAO.getAll();
	}

	/**
	 * 取得MessagePostRelease
	 * 
	 * @param messageId
	 *            messageId
	 * @return MessagePostRelease
	 */
	public List<ViewMessagePostReleaseOrg> getBymessageId(String messageId) {
		return messagePostReleaseDAO.getBymessageId(messageId);
	}

	/**
	 * 取得MessagePostRelease
	 * 
	 * @param messageId
	 *            messageId
	 * @param orgId
	 *            orgId
	 * @return MessagePostRelease
	 */
	public ViewMessagePostReleaseOrg getBymessageIdAndOrgId(String messageId, long orgId) {
		return messagePostReleaseDAO.getBymessageIdAndOrgId(messageId, orgId);
	}

	/**
	 * @param memberId
	 *            人員Id
	 * @param id
	 *            Id
	 * @param transferOutType
	 *            轉換類型
	 * @param transferOutId
	 *            轉換Id
	 * @return MessagePostRelease
	 */
	public MessagePostRelease updateTransferOut(long memberId, String id, Integer transferOutType, String transferOutId) {
		try {
			MessagePostRelease entity = messagePostReleaseDAO.get(id);
			entity.setTransferOutType(transferOutType);
			entity.setTransferOutId(transferOutId);
			messagePostReleaseDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

	}

	/**
	 * 新增MessagePostRelease
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessagePostRelease
	 * @return 是否成功
	 */
	public MessagePostRelease insert(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");
			long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
			String replyContent = obj.isNull("ReplyContent") == true ? null : obj.getString("ReplyContent");
			boolean isCC = obj.isNull("IsCC") == true ? false : obj.getBoolean("IsCC");
			boolean isReview = obj.isNull("IsReview") == true ? false : obj.getBoolean("IsReview");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			int transferOutType = obj.isNull("TransferOutType") == true ? 0 : obj.getInt("TransferOutType");
			String transferOutId = obj.isNull("TransferOutId") == true ? "" : obj.getString("TransferOutId");
			
			Date now = new Date();
			MessagePostRelease entity = new MessagePostRelease();
			entity.setId(WebCrypto.generateUUID());
			entity.setMessageId(messageId);
			entity.setOrgId(orgId);
			entity.setReplyContent(replyContent);
			entity.setIsCC(isCC);
			entity.setIsReview(isReview);
			entity.setStatus(status);
			entity.setTransferOutType(transferOutType);
			entity.setTransferOutId(transferOutId);

			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			messagePostReleaseDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得警訊回覆單
	 * 
	 * @param id
	 *            警訊回覆單Id
	 * @return 警訊回覆單
	 */
	public MessagePostRelease get(String id) {
		return messagePostReleaseDAO.get(id);
	}

	/**
	 * 警訊回覆單是否存在
	 * 
	 * @param id
	 *            警訊回覆單Id
	 * @return 是否存在
	 */
	public boolean isExist(String id) {
		return messagePostReleaseDAO.get(id) != null;
	}

	/**
	 * 審核警訊回覆單資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊回覆單資料
	 * @return 警訊回覆單資料
	 */
	public MessagePostRelease reply(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			boolean isCC = obj.isNull("IsCC") == true ? false : obj.getBoolean("IsCC");
			boolean isReview = obj.isNull("IsReview") == true ? false : obj.getBoolean("IsReview");
			String reply = obj.isNull("Reply") == true ? null : obj.getString("Reply");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			String replyOption = obj.isNull("ReplyOption") == true ? null : obj.getString("ReplyOption");
			MessagePostRelease entity = messagePostReleaseDAO.get(id);

			ViewMessageAlertEvent message = messageService.getById(entity.getMessageId());
			String orgName = orgService.getDataById(entity.getOrgId()).getName();

			List<OrgSign> orgSigns = orgSignService.getByOrgId(entity.getOrgId());
			if (orgSigns != null) {
				for (OrgSign orgSign : orgSigns) {
					List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
					if (members != null) {
						for (Member member : members) {
							if (member.getIsEnable()) {
								List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(7);
								if (memberRoles != null) {
									for (ViewMemberRoleMember memberRole : memberRoles) {
										if (memberRole.getMemberId().equals(member.getId())) {
											Org org = orgService.getDataById(member.getOrgId());
											if (org.getAuthType().equals("2") && org.getIsEnable() && isCC) {
												// 寄信
												// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
												// 主旨：H-ISAC警訊單(postid)回覆副知
												// 內容：○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容如下，請參閱，謝謝！回覆內容reply
												/** @author chuyufeng ***/
												String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Subject"), orgName + "：" + message.getPostId());
												String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Body"), member.getName(), orgName, message.getPostId(), reply);
												mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
											}
											if (status.equals("61") && org.getAuthType().equals("2") && org.getIsEnable()) {
												// 寄信
												// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
												// 主旨：H-ISAC警訊單(postid)回覆審核通知
												// 內容：○○○，您好！○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容，正等待您的審查，請您儘快撥冗進行處理，謝謝！
												String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Subject"), orgName + "：" + message.getPostId());
												String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Body"), member.getName(), orgName, message.getPostId());
												mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
											}
											if (status.equals("62") && org.getAuthType().equals("1") && org.getIsEnable()) {
												// 寄信
												// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
												// 主旨：H-ISAC警訊單(postid)回覆審核通知
												// 內容：○○○，您好！○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容，正等待您的審查，請您儘快撥冗進行處理，謝謝！
												String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Subject"), orgName + "：" + message.getPostId());
												String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Body"), member.getName(), orgName, message.getPostId());
												mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
											}
											if (status.equals("63") && org.getAuthType().equals("1") && org.getIsEnable() && orgSign.getWarningIsExamine().equals(2)) {
												// 寄信
												// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
												// 主旨：H-ISAC警訊單(postid)回覆副知
												// 內容：○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容如下，請參閱，謝謝！回覆內容reply
												/** @author chuyufeng ***/
												String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Subject"), orgName + "：" + message.getPostId());
												String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Body"), member.getName(), orgName, message.getPostId(), reply);
												mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (status.equals("63")) {
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(6);
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member member = memberService.get(memberRole.getMemberId());
						if (member != null && member.getIsEnable()) {
							// 寄信
							// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
							// 主旨：H-ISAC警訊單(postid)回覆審核通知
							// 內容：○○○，您好！○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容，正等待您的審查，請您儘快撥冗進行處理，謝謝！
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Subject"), orgName + "：" + message.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Body"), member.getName(), orgName, message.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}
			}
			Date now = new Date();
			entity.setIsCC(isCC);
			entity.setIsReview(isReview);
			entity.setReplyContent(reply);
			entity.setReplyOption(replyOption);
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messagePostReleaseDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 審核警訊回覆單資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊回覆單資料
	 * @return 警訊回覆單資料
	 */
	public MessagePostRelease examineMessageReleasePost(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			String preStatus = obj.isNull("PreStatus") == true ? null : obj.getString("PreStatus");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");
			MessagePostRelease entity = messagePostReleaseDAO.get(id);

			ViewMessageAlertEvent message = messageService.getById(entity.getMessageId());
			String orgName = orgService.getDataById(entity.getOrgId()).getName();
			Date now = new Date();
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messagePostReleaseDAO.update(entity);

			if (status.equals("51")) {
				/// 寄信
				// 收件者：會員機構聯絡人
				// 主旨：H-ISAC警訊單(HISAC-ANA-2018-00001)回覆審核退回通知
				// 內容：○○○，您好！您所回覆之警訊單(HISAC-ANA-2018-00001)，經審核者審核退回，目前正等待您進行修正，請您儘快撥冗進行處理，謝謝！
				/** @author chuyufeng ***/
				List<Member> members = memberService.getByOrgId(entity.getOrgId());
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(10);
				if (members != null) {
					for (Member member : members) {
						if (member.getIsEnable()) {
							for (ViewMemberRoleMember memberRole : memberRoles) {
								if (member.getId().equals(memberRole.getMemberId())) {
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5Back4Subject"), message.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5Back4Body"), member.getName(), message.getPostId(), opinion);
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
								}
							}
						}
					}
				}
			}

			if (status.equals("62") || (preStatus.equals("61") && status.equals("63"))) {
				List<OrgSign> orgSigns = orgSignService.getByOrgId(entity.getOrgId());
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> members = memberService.getByOrgId(orgSign.getParentOrgId());
						if (members != null) {
							for (Member sale : members) {
								if (sale.getIsEnable()) {
									List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(7);
									if (memberRoles != null) {
										for (ViewMemberRoleMember memberRole : memberRoles) {
											if (memberRole.getMemberId().equals(sale.getId())) {
												Org org = orgService.getDataById(sale.getOrgId());
												if (org.getAuthType().equals("1") && org.getIsEnable() && status.equals("62")) {
													// 寄信
													// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
													// 主旨：H-ISAC警訊單(postid)回覆審核通知
													// 內容：○○○，您好！○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容，正等待您的審查，請您儘快撥冗進行處理，謝謝！
													String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Subject"), orgName + "：" + message.getPostId());
													String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Body"), sale.getName(), orgName, message.getPostId());
													mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), sale.getEmail(), sale.getSpareEmail(), null, mailSubject, mailBody, null);
												}
												if (org.getAuthType().equals("1") && org.getIsEnable() && status.equals("63")) {
													// 寄信
													// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
													// 主旨：H-ISAC警訊單(postid)回覆副知
													// 內容：○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容如下，請參閱，謝謝！回覆內容reply
													/** @author chuyufeng ***/
													String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Subject"), orgName + "：" + message.getPostId());
													String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-1Body"), sale.getName(), orgName, message.getPostId(), message.getContents());
													mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), sale.getEmail(), sale.getSpareEmail(), null, mailSubject, mailBody, null);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (status.equals("63")) {
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(6);
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member hisac = memberService.get(memberRole.getMemberId());
						if (hisac != null && hisac.getIsEnable()) {
							// 寄信
							// 收件者：警訊審查者(權責,業務,hi審)看下一個是誰
							// 主旨：H-ISAC警訊單(postid)回覆審核通知
							// 內容：○○○，您好！○○○，您好！會員機構XXX對於警訊單(HISAC-ANA-2018-00001)之回覆內容，正等待您的審查，請您儘快撥冗進行處理，謝謝！
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Subject"), orgName + "：" + message.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-2Body"), hisac.getName(), orgName, message.getPostId());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), hisac.getEmail(), hisac.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}
			}
			if (status.equals("69")) {

				// 寄信
				// 收件者：會員機構聯絡人
				// 主旨：H-ISAC警訊單(HISAC-ANA-2018-00001)回覆確認結案通知
				// 內容：○○○，您好！您所回覆之警訊單(HISAC-ANA-2018-00001)，經H-ISAC警訊審核者審核確認結案，特此通知，謝謝！
				/*** @author chuyufeng ***/
				List<Member> members = memberService.getByOrgId(entity.getOrgId());
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(10);
				List<OrgSign> orgSigns = orgSignService.getByOrgId(entity.getOrgId());
				if (members != null) {
					for (Member member : members) {
						if (member.getIsEnable()) {
							for (ViewMemberRoleMember memberRole : memberRoles) {
								if (member.getId().equals(memberRole.getMemberId())) {
									String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5To7-1Subject"), message.getPostId());
									String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5To7-1Body"), member.getName(), message.getPostId());
									mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
								}
							}
						}
					}
				}
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						List<Member> memberSaleOrAuths = memberService.getByOrgId(orgSign.getParentOrgId());
						if (memberSaleOrAuths != null) {
							for (Member saleOrAuth : memberSaleOrAuths) {
								if (saleOrAuth.getIsEnable()) {
									List<ViewMemberRoleMember> memberRoleSaleOrAuths = memberRoleService.findByRoleId(7);
									if (memberRoleSaleOrAuths != null) {
										for (ViewMemberRoleMember memberRole : memberRoleSaleOrAuths) {
											if (memberRole.getMemberId().equals(saleOrAuth.getId())) {
												Org org = orgService.getDataById(saleOrAuth.getOrgId());
												if (((org.getAuthType().equals("1")) || ((entity.getIsCC() || entity.getIsReview()) && org.getAuthType().equals("2"))) && org.getIsEnable()) {
													String mailSubject1 = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5To7-2Subject"), orgName + "：" + message.getPostId());
													String mailBody1 = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage5To7-2Body"), saleOrAuth.getName(), orgName, message.getPostId());
													mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), saleOrAuth.getEmail(), saleOrAuth.getSpareEmail(), null, mailSubject1, mailBody1,
															null);
												}
											}
										}
									}
								}
							}
						}
					}
				}

				boolean messageStatusChnage = true;
				List<ViewMessagePostReleaseOrg> messagePostReleases = messagePostReleaseDAO.getBymessageId(entity.getMessageId());
				for (ViewMessagePostReleaseOrg messagePostRelease : messagePostReleases) {
					if (!messagePostRelease.getStatus().equals("69")) {
						messageStatusChnage = false;
						break;
					}
				}
				if (messageStatusChnage) {

					if (messageService.examine(memberId, entity.getMessageId(), "7", opinion) == null)
						return null;
				}
			}
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
}
