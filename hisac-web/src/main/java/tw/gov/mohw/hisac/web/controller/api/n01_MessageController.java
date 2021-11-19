package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.InformationSource;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewMessagePostReleaseOrg;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.filter.MyFilter;
import tw.gov.mohw.hisac.web.domain.MessageGroup;
import tw.gov.mohw.hisac.web.domain.MessageGroupOrg;
import tw.gov.mohw.hisac.web.domain.MessagePost;
import tw.gov.mohw.hisac.web.domain.MessagePostAttach;
import tw.gov.mohw.hisac.web.domain.MessagePostRelease;
import tw.gov.mohw.hisac.web.domain.MessageRecipient;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.service.MessageService;
import tw.gov.mohw.hisac.web.service.AlertTypeService;
import tw.gov.mohw.hisac.web.service.EventTypeService;
import tw.gov.mohw.hisac.web.service.InformationSourceService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.OrgService;
import tw.gov.mohw.hisac.web.service.MessageGroupService;
import tw.gov.mohw.hisac.web.service.MessagePostService;
import tw.gov.mohw.hisac.web.service.MessageRecipientService;
import tw.gov.mohw.hisac.web.service.MessagePostAttachService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;
import tw.gov.mohw.hisac.web.service.SmsService;
import tw.gov.mohw.hisac.web.service.MessageGroupOrgService;
import tw.gov.mohw.hisac.web.service.MessagePostReleaseService;
import tw.gov.mohw.hisac.web.service.OrgSignService;
import tw.gov.mohw.hisac.web.service.NotificationService;;

/**
 * 警訊管理控制器
 */
@Controller
@RequestMapping(value = "/not/api", produces = "application/json; charset=utf-8")
public class n01_MessageController extends BaseController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private AlertTypeService alertTypeService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private InformationSourceService informationSourceService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private MessageGroupService messageGroupService;
	@Autowired
	private MessagePostService messagePostService;
	@Autowired
	private MessagePostAttachService messagePostAttachService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MessageGroupOrgService messageGroupOrgService;
	@Autowired
	private MessagePostReleaseService messagePostReleaseService;
	@Autowired
	private OrgSignService orgSignService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MessageRecipientService messageRecipientService;	

	@Autowired
	private MailService mailService;

	private String targetControllerName = "not";
	private String targetActionName = "n01";

	/**
	 * 取得警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/n01/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			/*
			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
			 * 10-會員機構聯絡人 11-會員機構管理者
			 */
			if (baseMemberRole.IsAdmin == true) {
				obj.put("RoleId", 1);
			} else if (baseMemberRole.IsHisac == true) {
				obj.put("RoleId", 2);
			} else if (baseMemberRole.IsHisacInfoBuilder == true) {
				obj.put("RoleId", 5);
			} else if (baseMemberRole.IsHisacInfoSign == true) {
				obj.put("RoleId", 6);
			} else if (baseMemberRole.IsApplySign == true) {
				obj.put("RoleId", 7);
			} else if (baseMemberRole.IsApplyContact == true) {
				obj.put("RoleId", 8);
			} else if (baseMemberRole.IsMemberContact == true) {
				obj.put("RoleId", 10);
			}
			obj.put("OrgId", getBaseOrgId());
			obj.put("MemberId", getBaseMemberId());
			json = obj.toString();
			List<Message> messages = messageService.getSpList(json);
			listjson.put("total", messageService.getSpListSize(json));
			// 取得orgSign
			List<OrgSign> orgSigns = orgSignService.getByParentOrgId(getBaseOrgId());
			// 取得org
			Org org = orgService.getDataById(getBaseOrgId());
			// 取得全部org
			List<Org> orgOrgType3s = orgService.getByOrgType("3");
			JSONArray sn_array = new JSONArray();
			if (messages != null)
				for (Message not : messages) {
					JSONObject sn_json = new JSONObject();
					JSONArray messagePostRelease_array = new JSONArray();
					switch (not.getStatus()) {
						case "1" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							sn_json.put("IsSeeLog", true);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							if (baseMemberRole.IsHisacInfoBuilder) {
								sn_json.put("IsButtonEdit", true);
								sn_json.put("IsButtonDelete", true);
							} else {
								sn_json.put("IsButtonEdit", false);
								sn_json.put("IsButtonDelete", false);
							}
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							break;
						case "2" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							sn_json.put("IsSeeLog", true);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							if (baseMemberRole.IsHisacInfoBuilder)
								sn_json.put("IsButtonUndo", true);
							else
								sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							break;
						case "3" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							sn_json.put("IsSeeLog", true);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							if (baseMemberRole.IsHisacInfoSign)
								sn_json.put("IsButtonReview", true);
							else
								sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							break;
						case "5" :
						case "7" :
							int waitReply = 0;
							int waitDetermine = 0;
							int determine = 0;
							List<ViewMessagePostReleaseOrg> viewMessagePostReleaseOrgs = messagePostReleaseService.getBymessageId(not.getId());
							if (viewMessagePostReleaseOrgs != null) {
								for (ViewMessagePostReleaseOrg viewMessagePostReleaseOrg : viewMessagePostReleaseOrgs) {
									if (baseMemberRole.IsApplySign && orgSigns != null && orgOrgType3s != null) {
										for (OrgSign orgSign : orgSigns) {
											if (orgSign.getOrgId().equals(viewMessagePostReleaseOrg.getOrgId())) {
												for (Org orgOrgType3 : orgOrgType3s) {
													if (orgOrgType3.getId().equals(viewMessagePostReleaseOrg.getOrgId())) {
														// 取回覆單
														JSONObject messagePostRelease_json = new JSONObject();
														messagePostRelease_json.put("Id", not.getId());
														messagePostRelease_json.put("PostId", not.getPostId());
														messagePostRelease_json.put("IsSeeLog", false);
														messagePostRelease_json.put("IsSeeMessagePostReleaseLog", true);
														messagePostRelease_json.put("MessagePostReleaseId", viewMessagePostReleaseOrg.getId());
														messagePostRelease_json.put("MessagePostReleaseOrgName", viewMessagePostReleaseOrg.getOrgName());
														messagePostRelease_json.put("MessagePostReleaseCiLevel", viewMessagePostReleaseOrg.getCiLevel());
														messagePostRelease_json.put("MessagePostReleaseStatus", viewMessagePostReleaseOrg.getStatus());
														messagePostRelease_json.put("MessagePostReleaseReplyContent", viewMessagePostReleaseOrg.getReplyContent());
														messagePostRelease_json.put("MessagePostReleaseReplyOption", viewMessagePostReleaseOrg.getReplyOption());
														messagePostRelease_json.put("MessagePostReleaseReplyOptionTime", WebDatetime.toString(viewMessagePostReleaseOrg.getOpinionTime()));
														if (viewMessagePostReleaseOrg.getStatus().equals("51")) {
															waitReply++;
															messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
														} else if (viewMessagePostReleaseOrg.getStatus().equals("61")) {
															if (viewMessagePostReleaseOrg.getIsReview() && org.getAuthType().equals("2")) {
																waitDetermine++;
																messagePostRelease_json.put("IsButtonMessagePostReleaseReview", true);
															} else {
																waitReply++;
																messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
															}
														} else if (viewMessagePostReleaseOrg.getStatus().equals("62")) {
															if ((orgSign.getWarningIsExamine().equals(1) || orgSign.getWarningIsExamine().equals(3)) && org.getAuthType().equals("1")) {
																messagePostRelease_json.put("IsButtonMessagePostReleaseReview", true);
																waitDetermine++;
															} else {
																determine++;
																messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
															}
														} else if (viewMessagePostReleaseOrg.getStatus().equals("63")) {
															determine++;
															messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
														} else if (viewMessagePostReleaseOrg.getStatus().equals("69")) {
															determine++;
															messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
														}
														messagePostRelease_array.put(messagePostRelease_json);
														break;
													}
												}
												break;
											}
										}
									} else if ((baseMemberRole.IsHisacInfoBuilder || baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac || baseMemberRole.IsAdmin) && orgOrgType3s != null) {
										for (Org orgOrgType3 : orgOrgType3s) {
											if (orgOrgType3.getId().equals(viewMessagePostReleaseOrg.getOrgId())) {
												// 取回覆單
												JSONObject messagePostRelease_json = new JSONObject();
												messagePostRelease_json.put("Id", not.getId());
												messagePostRelease_json.put("PostId", not.getPostId());
												messagePostRelease_json.put("IsSeeLog", false);
												messagePostRelease_json.put("IsSeeMessagePostReleaseLog", true);
												messagePostRelease_json.put("MessagePostReleaseId", viewMessagePostReleaseOrg.getId());
												messagePostRelease_json.put("MessagePostReleaseOrgName", viewMessagePostReleaseOrg.getOrgName());
												messagePostRelease_json.put("MessagePostReleaseCiLevel", viewMessagePostReleaseOrg.getCiLevel());
												messagePostRelease_json.put("MessagePostReleaseStatus", viewMessagePostReleaseOrg.getStatus());
												messagePostRelease_json.put("MessagePostReleaseReplyContent", viewMessagePostReleaseOrg.getReplyContent());
												messagePostRelease_json.put("MessagePostReleaseReplyOption", viewMessagePostReleaseOrg.getReplyOption());
												messagePostRelease_json.put("MessagePostReleaseReplyOptionTime", WebDatetime.toString(viewMessagePostReleaseOrg.getOpinionTime()));
												if (viewMessagePostReleaseOrg.getStatus().equals("51")) {
													waitReply++;
													messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
												} else if (viewMessagePostReleaseOrg.getStatus().equals("61")) {
													waitReply++;
													messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
												} else if (viewMessagePostReleaseOrg.getStatus().equals("62")) {
													waitReply++;
													messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
												} else if (viewMessagePostReleaseOrg.getStatus().equals("63")) {
													waitDetermine++;
													if (baseMemberRole.IsHisacInfoSign)
														messagePostRelease_json.put("IsButtonMessagePostReleaseReview", true);
												} else if (viewMessagePostReleaseOrg.getStatus().equals("69")) {
													determine++;
													messagePostRelease_json.put("IsButtonMessagePostReleaseReview", false);
												}
												messagePostRelease_array.put(messagePostRelease_json);
												break;
											}
										}
									}
									// 是否為權責單位聯絡人
									else if (baseMemberRole.IsApplyContact && org.getOrgType().equals("2") && viewMessagePostReleaseOrg.getOrgId().equals(getBaseOrgId())) {
										sn_json.put("Id", not.getId());
										sn_json.put("PostId", not.getPostId());
										sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
										sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
										sn_json.put("SourceCode", not.getSourceCode());
										sn_json.put("Subject", not.getSubject());
										sn_json.put("Status", "4");
										sn_json.put("IsReply", not.getIsReply());
										sn_json.put("IsSms", not.getIsSms());
										sn_json.put("IsTest", not.getIsTest());
										sn_json.put("IsButtonMessagePostReleaseList", false);
										sn_json.put("IsSeeLog", false);
										sn_json.put("IsSeeMessagePostReleaseLog", false);
										sn_json.put("IsButtonEdit", false);
										sn_json.put("IsButtonDelete", false);
										sn_json.put("IsButtonUndo", false);
										sn_json.put("IsButtonReview", false);
										sn_json.put("IsButtonReply", false);
										sn_json.put("IsButtonToAlert", false);
										sn_json.put("MessagePostRelease", messagePostRelease_array);
										break;
									}
									// 是否為會員機構聯絡人
									else if (baseMemberRole.IsMemberContact && org.getOrgType().equals("3") && viewMessagePostReleaseOrg.getOrgId().equals(getBaseOrgId())) {
										List<ViewProcessLogMember> processLogs = processLogService.getByPostId(viewMessagePostReleaseOrg.getId(), "messagePostRelease");
										sn_json.put("Id", not.getId());
										sn_json.put("PostId", not.getPostId());
										sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
										sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
										sn_json.put("SourceCode", not.getSourceCode());
										sn_json.put("Subject", not.getSubject());
										sn_json.put("Status", viewMessagePostReleaseOrg.getStatus());
										sn_json.put("IsReply", not.getIsReply());
										sn_json.put("IsSms", not.getIsSms());
										sn_json.put("IsTest", not.getIsTest());
										sn_json.put("IsButtonMessagePostReleaseList", false);
										sn_json.put("IsSeeLog", false);
										sn_json.put("IsSeeMessagePostReleaseLog", true);
										sn_json.put("IsButtonEdit", false);
										sn_json.put("IsButtonDelete", false);
										sn_json.put("IsButtonUndo", false);
										sn_json.put("IsButtonReview", false);
										if (viewMessagePostReleaseOrg.getStatus().equals("51")) {
											sn_json.put("IsButtonReply", true);
											if (processLogs != null) {
												for (ViewProcessLogMember processLog : processLogs) {
													if (processLog.getPreStatus().equals(viewMessagePostReleaseOrg.getStatus()))
														sn_json.put("IsUndo", true);
													else
														sn_json.put("IsUndo", false);
												}
											}
										} else
											sn_json.put("IsButtonReply", false);
										if (viewMessagePostReleaseOrg.getTransferOutType() == 0 || viewMessagePostReleaseOrg.getTransferOutType() == null)
											sn_json.put("IsButtonToAlert", true);
										else
											sn_json.put("IsButtonToAlert", false);
										sn_json.put("MessagePostRelease", messagePostRelease_array);
										// 多傳回覆單id
										sn_json.put("MessagePostReleaseId", viewMessagePostReleaseOrg.getId());
										break;
									}
								}
							}
							// 建立者 or hisac審核者 or hisac管理者 or 是否需上級機關審核副知 or
							// 是否需業務管理機關審核副知
							if (baseMemberRole.IsHisacInfoBuilder || baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac || baseMemberRole.IsApplySign || baseMemberRole.IsAdmin) {
								sn_json.put("Id", not.getId());
								sn_json.put("PostId", not.getPostId());
								sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
								sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
								sn_json.put("SourceCode", not.getSourceCode());
								sn_json.put("Subject", not.getSubject());
								sn_json.put("Status", not.getStatus());
								sn_json.put("IsReply", not.getIsReply());
								sn_json.put("IsSms", not.getIsSms());
								sn_json.put("IsTest", not.getIsTest());
								sn_json.put("IsButtonMessagePostReleaseList", true);
								if (baseMemberRole.IsHisacInfoBuilder || baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac || baseMemberRole.IsAdmin)
									sn_json.put("IsSeeLog", true);
								else
									sn_json.put("IsSeeLog", false);
								sn_json.put("IsSeeMessagePostReleaseLog", false);
								sn_json.put("IsButtonEdit", false);
								sn_json.put("IsButtonDelete", false);
								sn_json.put("IsButtonUndo", false);
								sn_json.put("IsButtonReview", false);
								sn_json.put("IsButtonReply", false);
								sn_json.put("IsButtonToAlert", false);
								sn_json.put("MessagePostRelease", messagePostRelease_array);
								// 多傳回覆單狀態數量
								sn_json.put("WaitReply", waitReply);
								sn_json.put("WaitDetermine", waitDetermine);
								sn_json.put("Determine", determine);
								// sn_array.put(sn_json);
							}
							break;
						case "8" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							if (baseMemberRole.IsHisacInfoBuilder || baseMemberRole.IsHisacInfoSign || baseMemberRole.IsHisac || baseMemberRole.IsAdmin)
								sn_json.put("IsSeeLog", true);
							else
								sn_json.put("IsSeeLog", false);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							if (baseMemberRole.IsMemberContact
									&& (messagePostReleaseService.getBymessageIdAndOrgId(not.getId(), getBaseOrgId()).getTransferOutType() == 0 || messagePostReleaseService.getBymessageIdAndOrgId(not.getId(), getBaseOrgId()).getTransferOutType() == null))
								sn_json.put("IsButtonToAlert", true);
							else
								sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							// sn_array.put(sn_json);
							break;

						case "9" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							sn_json.put("IsSeeLog", true);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							// sn_array.put(sn_json);
							break;

						case "10" :
							sn_json.put("Id", not.getId());
							sn_json.put("PostId", not.getPostId());
							sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
							sn_json.put("CreateTime", WebDatetime.toString(not.getCreateTime()));
							sn_json.put("SourceCode", not.getSourceCode());
							sn_json.put("Subject", not.getSubject());
							sn_json.put("Status", not.getStatus());
							sn_json.put("IsReply", not.getIsReply());
							sn_json.put("IsSms", not.getIsSms());
							sn_json.put("IsTest", not.getIsTest());
							sn_json.put("IsButtonMessagePostReleaseList", false);
							sn_json.put("IsSeeLog", true);
							sn_json.put("IsSeeMessagePostReleaseLog", false);
							if (baseMemberRole.IsHisacInfoBuilder)
								sn_json.put("IsButtonEdit", true);
							else
								sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsButtonReply", false);
							sn_json.put("IsButtonToAlert", false);
							sn_json.put("MessagePostRelease", messagePostRelease_array);
							// sn_array.put(sn_json);
							break;

					}

					sn_array.put(sn_json);
				}
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";

	}

	/**
	 * 刪除警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            查詢條件
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/n01/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// if (funcRoleService.getUserFuncNameRole(FUNC_NAME,
			// getUserRoleId())
			// .getFuncDel().equals("Y") || WebConfig.DEBUG_MODE == true) {
			if (!messageService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (messagePostAttachService.deleteBymessageid(id) && messagePostService.deleteBymessageid(id) && messageService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
			// } else {
			// json.put("msg", "PermissionDenied");
			// json.put("success", false);
			// }
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 取得alertType資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            alertType資料
	 * @return alertType資料
	 */
	@RequestMapping(value = "/n01/query/alert", method = RequestMethod.POST)
	public String Getalert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<AlertType> alertTypes = alertTypeService.getList();
		if (alertTypes != null)
			for (AlertType alertType : alertTypes) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Code", alertType.getCode());
				sn_json.put("Name", alertType.getName());
				sn_array.put(sn_json);
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得eventType資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return eventType資料
	 */
	@RequestMapping(value = "/n01/query/event", method = RequestMethod.POST)
	public String Getevent(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
		MyFilter myFilter = new MyFilter();
		alertCode = myFilter.filterString(alertCode);
		List<EventType> eventTypes = eventTypeService.getByAlertCode(alertCode);
		if (eventTypes != null)
			for (EventType eventType : eventTypes) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Code", eventType.getCode());
				sn_json.put("Name", eventType.getName());
				sn_array.put(sn_json);
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得sourceName資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            sourceName資料
	 * @return sourceName資料
	 */
	@RequestMapping(value = "/n01/query/source", method = RequestMethod.POST)
	public String Getsource(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONArray sn_array = new JSONArray();
		List<InformationSource> informationSources = informationSourceService.getAll();
		if (informationSources != null)
			for (InformationSource informationSource : informationSources) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Code", informationSource.getCode());
				sn_json.put("Name", informationSource.getName());
				sn_array.put(sn_json);
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得Member資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Member資料
	 * @return Member資料
	 */
	@RequestMapping(value = "/n01/query/member", method = RequestMethod.POST)
	public String Getmember(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<Org> members = orgService.getByOrgType("3");
		if (members != null)
			for (Org member : members) {
				if (member.getIsApply()) {					
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", member.getId());
					sn_json.put("Name", member.getName());
					sn_json.put("Action", false);
					sn_json.put("Show", false);
					sn_array.put(sn_json);
				}
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}
	
	
	/**
	 * 取得Member資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Member資料
	 * @return Member資料
	 */
	@RequestMapping(value = "/n01/query/memberList", method = RequestMethod.POST)
	public String Querymember(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		JSONArray cilevel_array = new JSONArray();
		Set<Long> org_array = new HashSet<>();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		boolean isCiLevel1 = false;
		JSONArray ciLevels = obj.optJSONArray("CiLevels");		
		if (ciLevels != null)
		for (int i = 0; i < ciLevels.length(); i++) {			
			JSONObject objCiLevel = ciLevels.getJSONObject(i);
			if (objCiLevel.getBoolean("Action")) {				
				cilevel_array.put(Long.toString(objCiLevel.getLong("Value")));
				if (objCiLevel.getLong("Value") == 1)
					isCiLevel1 = true;
			}
		}
		Long recipientGroup = obj.isNull("RecipientGroup") == true ? null : obj.getLong("RecipientGroup");				
		if (recipientGroup != null) {						
		List<MessageGroupOrg> messageGroupOrgs = messageGroupOrgService.getByMessageGroupId(recipientGroup);
			for (MessageGroupOrg messageGroupOrg : messageGroupOrgs)			
				org_array.add(messageGroupOrg.getOrgId());			
		}
		obj.put("OrgType", "3");
		obj.put("IsEnable", true);
		obj.put("IsCiLevel1", isCiLevel1);
		obj.put("Cilevels", cilevel_array);
		obj.put("Orgs", org_array);
		List<Org> orgs = orgService.querymember(obj.toString());
		if (orgs != null)
			for (Org org : orgs) {
				if (org.getIsEnable()) 							
					sn_array.put(org.getId());				
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";	
	}

	/**
	 * 取得Group資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Group資料
	 * @return Group資料
	 */
	@RequestMapping(value = "/n01/query/group", method = RequestMethod.POST)
	public String Getgroup(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<MessageGroup> messageGroups = messageGroupService.getAll();
		if (messageGroups != null)
			for (MessageGroup messageGroup : messageGroups) {
				if (messageGroup.getIsEnable()) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", messageGroup.getId());
					sn_json.put("Name", messageGroup.getName());
					sn_array.put(sn_json);
				}
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}	
	
	
	/**
	 * 取得recipientOther資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            recipientOther資料
	 * @return recipientOther資料
	 */
	@RequestMapping(value = "/n01/query/recipientOther", method = RequestMethod.POST)
	public String GetrecipientOther(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		List<MessageRecipient> messageRecipients = messageRecipientService.getAll();
		if (messageRecipients != null)
			for (MessageRecipient messageRecipient : messageRecipients) {
				JSONObject sn_json = new JSONObject();				
				sn_json.put("Name", messageRecipient.getName());
				sn_json.put("Email", messageRecipient.getEmail());
				sn_json.put("MobilePhone", messageRecipient.getMobilePhone());
				sn_array.put(sn_json);
			}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            警訊Id
	 * @return 警訊資料
	 */
	@RequestMapping(value = "/n01/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		boolean isSeeIsCC = false;
		boolean isSeeIsReview = false;
		boolean isNeedSaleReview = false;
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		String messagePostReleaseId = obj.isNull("MessagePostReleaseId") == true ? null : obj.getString("MessagePostReleaseId");
		String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");

		JSONArray sn_array = new JSONArray();
		JSONObject sn_json = new JSONObject();
		JSONArray messagePost_array = new JSONArray();
		JSONArray messageLog_array = new JSONArray();
		JSONArray messagePostReleaseLog_array = new JSONArray();
		JSONArray messagePostAttach_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			ViewMessageAlertEvent not = messageService.getById(id);
			List<MessagePost> messagePosts = messagePostService.getBymessageId(id);
			
			List<Org> orgs = orgService.getByOrgType("3");
			
			JSONArray orgIds = new JSONArray();
			
			if (messagePosts != null)
				for (MessagePost messagePost : messagePosts) {
					if (messagePost.getPostType().equals("7") && orgs!=null) {																
						for (Org org : orgs)						
							if (Long.toString(org.getId()).equals(messagePost.getPostContent()))										
								orgIds.put(org.getId());
					}					
					else {
						JSONObject messagePost_json = new JSONObject();														
						messagePost_json.put("PostType", messagePost.getPostType());
						messagePost_json.put("PostContent", messagePost.getPostContent());
						messagePost_json.put("PostName", messagePost.getPostName());
						messagePost_json.put("PostMobilePhone", messagePost.getPostMobilePhone());
						messagePost_array.put(messagePost_json);		
					}														
				}					
																																																																
			if (orgIds.length() > 0) {
				Long [] orgId = new Long[orgIds.length()];
				for (int i = 0; i < orgIds.length(); ++i) {
					orgId[i] = orgIds.optLong(i);
				}
				JSONArray memberIds = new JSONArray();
				JSONArray memberIdsForRole = new JSONArray();				
				List<Member> membersByOrgId = memberService.getByOrgIds(orgId);
				for (Member member : membersByOrgId) {
					if (member.getIsEnable())
						memberIds.put(member.getId());
				}
			
				Long [] memberId = new Long[memberIds.length()];
				for (int i = 0; i < memberIds.length(); ++i) {
					memberId[i] = memberIds.optLong(i);
				}
				if (memberIds.length() > 0) {
					List<MemberRole> memberRoles = memberRoleService.getByMemberIds(memberId);
					if (memberRoles != null) {
						for (MemberRole memberRole : memberRoles) {
							if (memberRole.getRoleId() == 8 || memberRole.getRoleId() == 10) {															
								memberIdsForRole.put(memberRole.getMemberId());
							}
						}
					}
				}
				Long [] memberIdForRole = new Long[memberIdsForRole.length()];
				for (int i = 0; i < memberIdsForRole.length(); ++i) {
					memberIdForRole[i] = memberIdsForRole.optLong(i);
				}
				if (memberIdsForRole.length() > 0) {
					List<Member> membersForRole = memberService.getByIds(memberIdForRole);					
				
					if (messagePosts != null)
						for (MessagePost messagePost : messagePosts) {							
							if (messagePost.getPostType().equals("7")) {	
								JSONObject messagePost_json = new JSONObject();								
								JSONArray memberName_arr = new JSONArray();
								messagePost_json.put("PostType", messagePost.getPostType());
								messagePost_json.put("PostContent", messagePost.getPostContent());
								messagePost_json.put("PostName", messagePost.getPostName());								
								if (orgs != null)
									for (Org org : orgs) {									
										if (Long.toString(org.getId()).equals(messagePost.getPostContent())) {																					
											messagePost_json.put("PostContentName", org.getName());											
											for (Member memberForRole : membersForRole) {
												if (memberForRole.getOrgId() == org.getId())
													memberName_arr.put(memberForRole.getName());
											}
										}
									}
								messagePost_json.put("MemberName", memberName_arr);
								messagePost_array.put(messagePost_json);		
							}														
						}																		
					}
				}
			Date today = new Date();
			if (messagePostReleaseId != null) {
				List<ViewProcessLogMember> messagePostReleaseLogs = processLogService.getByPostId(messagePostReleaseId, "messagePostRelease");
				if (messagePostReleaseLogs != null) {
					for (ViewProcessLogMember messagePostReleaseLog : messagePostReleaseLogs) {
						if (!messagePostReleaseLog.getPreStatus().equals("3")) {
							JSONObject messagePostReleaseLog_json = new JSONObject();
							messagePostReleaseLog_json.put("PreStatus", messagePostReleaseLog.getPreStatus());
							messagePostReleaseLog_json.put("Status", messagePostReleaseLog.getStatus());
							messagePostReleaseLog_json.put("CreateTime", WebDatetime.toString(messagePostReleaseLog.getCreateTime()));
							messagePostReleaseLog_json.put("Opinion", messagePostReleaseLog.getOpinion());
							messagePostReleaseLog_json.put("CreateName", messagePostReleaseLog.getCreateName());
							messagePostReleaseLog_json.put("PreName", messagePostReleaseLog.getPreName());
							messagePostReleaseLog_json.put("Days", (today.getTime() - messagePostReleaseLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
							messagePostReleaseLog_array.put(messagePostReleaseLog_json);
						}
					}
				}

				MessagePostRelease messagePostRelease = messagePostReleaseService.get(messagePostReleaseId);
				if (messagePostRelease != null) {
					sn_json.put("MessagePostReleaseStatus", messagePostRelease.getStatus());
					System.out.println("1111====111");
					System.out.println(messagePostRelease.getOrgId());
					List<OrgSign> orgSigns = orgSignService.getByOrgId(messagePostRelease.getOrgId());
					System.out.println("1111====111");
					System.out.println(messagePostRelease.getOrgId());
					for (OrgSign orgSign : orgSigns) {
						if ((orgSign.getWarningIsExamine().equals(1) || orgSign.getWarningIsExamine().equals(3)) && orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("1")
								&& orgService.getDataById(orgSign.getParentOrgId()).getIsEnable())
							isNeedSaleReview = true;
						if (orgService.getDataById(orgSign.getParentOrgId()).getAuthType().equals("2") && orgService.getDataById(orgSign.getParentOrgId()).getIsEnable()) {
							if (orgSign.getWarningIsExamine().equals(1) || orgSign.getWarningIsExamine().equals(3))
								isSeeIsReview = true;
							if (orgSign.getWarningIsExamine().equals(2) || orgSign.getWarningIsExamine().equals(3))
								isSeeIsCC = true;
						}
					}
				}
			}
			List<ViewProcessLogMember> messageLogs = processLogService.getByPostId(id, tableName);
			if (messageLogs != null) {
				for (ViewProcessLogMember messageLog : messageLogs) {
					JSONObject messageLog_json = new JSONObject();
					messageLog_json.put("PreStatus", messageLog.getPreStatus());
					messageLog_json.put("Status", messageLog.getStatus());
					messageLog_json.put("CreateTime", WebDatetime.toString(messageLog.getCreateTime()));
					messageLog_json.put("Opinion", messageLog.getOpinion());
					messageLog_json.put("CreateName", messageLog.getCreateName());
					messageLog_json.put("PreName", messageLog.getPreName());
					messageLog_json.put("Days", (today.getTime() - messageLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
					messageLog_array.put(messageLog_json);
				}
			}
			List<MessagePostAttach> messagePostAttachs = messagePostAttachService.getByMessageId(id);
			if (messagePostAttachs != null) {
				long size;
				String fileSize = "";
				for (MessagePostAttach messagePostAttach : messagePostAttachs) {
					JSONObject messagePostAttach_json = new JSONObject();
					messagePostAttach_json.put("Id", messagePostAttach.getId());
					messagePostAttach_json.put("name", messagePostAttach.getFileName());
					messagePostAttach_json.put("FileDesc", messagePostAttach.getFileDesc());
					// 檔案大小格式化顯示
					if (messagePostAttach.getFileSize() == null) {
						// messagePostAttach_array.put(messagePostAttach_json);
					} else {
						size = messagePostAttach.getFileSize();
						final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
						int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
						fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

						messagePostAttach_json.put("FileType", messagePostAttach.getFileType());
						messagePostAttach_json.put("FileSize", fileSize);
						messagePostAttach_json.put("FileHash", messagePostAttach.getFileHash());
						messagePostAttach_array.put(messagePostAttach_json);
					}
				}
			}
			sn_json.put("Id", not.getId());
			sn_json.put("PostId", not.getPostId());
			sn_json.put("AlertCode", not.getAlertCode());
			sn_json.put("EventCode", not.getEventCode());
			sn_json.put("SourceCode", not.getSourceCode());
			sn_json.put("AlertName", not.getAlertTypeName());
			sn_json.put("EventName", not.getEventTypeName());
			sn_json.put("SourceName", not.getSourceName());
			sn_json.put("ExternalId", not.getExternalId());
			sn_json.put("FindDate", WebDatetime.toString(not.getFindDate()).split(" ")[0]);
			sn_json.put("PostDateTime", WebDatetime.toString(not.getPostDateTime()));
			sn_json.put("Subject", myFilter.stripXSS(not.getSubject()));
			sn_json.put("Description", myFilter.stripXSS(not.getDescription()));
			sn_json.put("Suggestion", myFilter.stripXSS(not.getSuggestion()));
			sn_json.put("Reference", myFilter.stripXSS(not.getReference()));
			sn_json.put("Determine", myFilter.stripXSS(not.getDetermine()));
			sn_json.put("Contents", myFilter.stripXSS(not.getContents()));
			sn_json.put("AffectPlatform", myFilter.stripXSS(not.getAffectPlatform()));
			sn_json.put("ImpactLevel", not.getImpactLevel());
			sn_json.put("Status", not.getStatus());
			sn_json.put("IsEnable", not.getIsEnable());
			sn_json.put("IsReply", not.getIsReply());
			sn_json.put("IsSms", not.getIsSms());
			sn_json.put("IsTest", not.getIsTest());
			sn_json.put("SmsFormat", not.getSmsFormat());
			sn_json.put("MessagePost", messagePost_array);
			sn_json.put("MessagePostAttach", messagePostAttach_array);
			sn_json.put("MessageLog", messageLog_array);
			sn_json.put("MessagePostReleaseLog", messagePostReleaseLog_array);
			sn_json.put("IsSeeIsCC", isSeeIsCC);
			sn_json.put("IsSeeIsReview", isSeeIsReview);
			sn_json.put("IsNeedSaleReview", isNeedSaleReview);
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            警訊
	 * @param file1
	 *            file1
	 * @param file2
	 *            file2
	 * @param file3
	 *            file3
	 * @param fileDesc1
	 *            FileDesc1
	 * @param fileDesc2
	 *            FileDesc2
	 * @param fileDesc3
	 *            FileDesc3
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/n01/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestParam("json") String json, @RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam("FileDesc1") String fileDesc1, @RequestParam("FileDesc2") String fileDesc2,
			@RequestParam("FileDesc3") String fileDesc3) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			Message not = messageService.insert(getBaseMemberId(), json, false);
			Boolean messagePost = messagePostService.insert(getBaseMemberId(), json, not.getId());

			if (isWriteProcessLog) {
				// 寄信
				// 收件者：memberService.get((memberRoleService.findByRoleId(6)).getMemberId()).getEmail()
				// 主旨：H-ISAC警訊單("not.getPostId()")審核通知
				// 內容：memberService.get((memberRoleService.findByRoleId(6)).getMemberId()).getName()，您好！警訊單("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
				/** @author chuyufeng ***/
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(6);
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member member = memberService.get(memberRole.getMemberId());
						if (member != null && member.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage1To3Subject"), not.getPostId());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage1To3Body"), member.getName(), not.getPostId(), not.getSubject());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}

				processLogService.insert(getBaseMemberId(), json, not.getId());
			}
			Boolean insert1 = true;
			Boolean insert2 = true;
			Boolean insert3 = true;
			Boolean error = false;

			try {
				if (file1 != null && !file1.isEmpty()) {
					// 檔案 byte array
					byte[] bytes1 = file1.getBytes();
					JSONObject sn_json1 = new JSONObject();
					sn_json1.put("MessageId", not.getId()); // MessageId
					sn_json1.put("FileDesc", fileDesc1); // 檔案說明
					sn_json1.put("FileName", file1.getOriginalFilename()); // 檔案名稱
					sn_json1.put("FileType", file1.getContentType()); // 檔案類型
					sn_json1.put("FileSize", file1.getSize()); // 檔案大小
					sn_json1.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes1.toString())); // 檔案Hash
					String json1 = sn_json1.toString();

					// 新增
					insert1 = messagePostAttachService.insert(getBaseMemberId(), json1, bytes1);

				} else {
					JSONObject sn_json1 = new JSONObject();
					sn_json1.put("MessageId", not.getId()); // MessageId
					sn_json1.put("FileDesc", fileDesc1); // 檔案說明
					String json1 = sn_json1.toString();

					// 新增
					insert1 = messagePostAttachService.insertFileDesc(getBaseMemberId(), json1);
				}

				if (file2 != null && !file2.isEmpty()) {
					// 檔案 byte array
					byte[] bytes2 = file2.getBytes();
					JSONObject sn_json2 = new JSONObject();
					sn_json2.put("MessageId", not.getId()); // MessageId
					sn_json2.put("FileDesc", fileDesc2); // 檔案說明
					sn_json2.put("FileName", file2.getOriginalFilename()); // 檔案名稱
					sn_json2.put("FileType", file2.getContentType()); // 檔案類型
					sn_json2.put("FileSize", file2.getSize()); // 檔案大小
					sn_json2.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes2.toString())); // 檔案Hash
					String json2 = sn_json2.toString();

					// 新增
					insert2 = messagePostAttachService.insert(getBaseMemberId(), json2, bytes2);
				}

				else {
					JSONObject sn_json2 = new JSONObject();
					sn_json2.put("MessageId", not.getId()); // MessageId
					sn_json2.put("FileDesc", fileDesc2); // 檔案說明
					String json2 = sn_json2.toString();

					// 新增
					insert2 = messagePostAttachService.insertFileDesc(getBaseMemberId(), json2);
				}

				if (file3 != null && !file3.isEmpty()) {
					// 檔案 byte array
					byte[] bytes3 = file3.getBytes();
					JSONObject sn_json3 = new JSONObject();
					sn_json3.put("MessageId", not.getId()); // MessageId
					sn_json3.put("FileDesc", fileDesc3); // 檔案說明
					sn_json3.put("FileName", file3.getOriginalFilename()); // 檔案名稱
					sn_json3.put("FileType", file3.getContentType()); // 檔案類型
					sn_json3.put("FileSize", file3.getSize()); // 檔案大小
					sn_json3.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes3.toString())); // 檔案Hash
					String json3 = sn_json3.toString();

					// 新增
					insert3 = messagePostAttachService.insert(getBaseMemberId(), json3, bytes3);

				} else {
					JSONObject sn_json3 = new JSONObject();
					sn_json3.put("MessageId", not.getId()); // MessageId
					sn_json3.put("FileDesc", fileDesc3); // 檔案說明
					String json3 = sn_json3.toString();

					// 新增
					insert3 = messagePostAttachService.insertFileDesc(getBaseMemberId(), json3);
				}
			} catch (Exception e) {
				error = true;
			}

			if (not != null && messagePost && !error && insert1 && insert2 && insert3) {
				responseJson.put("Id", not.getId());
				responseJson.put("PostId", not.getPostId());
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 更新警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            警訊
	 * @param file1
	 *            MultipartFile
	 * @param file2
	 *            MultipartFile
	 * @param file3
	 *            MultipartFile
	 * @param fileDesc1
	 *            String
	 * @param fileDesc2
	 *            String
	 * @param fileDesc3
	 *            String
	 * @param messagePostAttachId1
	 *            long
	 * @param messagePostAttachId2
	 *            long
	 * @param messagePostAttachId3
	 *            long
	 * @param updateFile1
	 *            Boolean
	 * @param updateFile2
	 *            Boolean
	 * @param updateFile3
	 *            Boolean
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/n01/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestParam("json") String json, @RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam(value = "FileDesc1") String fileDesc1,
			@RequestParam(value = "FileDesc2") String fileDesc2, @RequestParam(value = "FileDesc3") String fileDesc3, @RequestParam(value = "MessagePostAttachId1") long messagePostAttachId1,
			@RequestParam(value = "MessagePostAttachId2") long messagePostAttachId2, @RequestParam(value = "MessagePostAttachId3") long messagePostAttachId3, @RequestParam(value = "UpdateFile1") Boolean updateFile1,
			@RequestParam(value = "UpdateFile2") Boolean updateFile2, @RequestParam(value = "UpdateFile3") Boolean updateFile3) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		Boolean update1 = true;
		Boolean update2 = true;
		Boolean update3 = true;
		Boolean error = false;
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!messageService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Message not = messageService.update(getBaseMemberId(), json, false);
				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
				Boolean delmessagePost = messagePostService.deleteBymessageid(not.getId());
				Boolean messagePost = messagePostService.insert(getBaseMemberId(), json, not.getId());
				if (isWriteProcessLog) {
					processLogService.insert(getBaseMemberId(), json, not.getId());

					// 寄信
					// 收件者：memberService.get((memberRoleService.findByRoleId(6)).getMemberId()).getEmail()
					// 主旨：H-ISAC警訊單("not.getPostId()")審核通知
					// 內容：memberService.get((memberRoleService.findByRoleId(6)).getMemberId()).getName()，您好！警訊單("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
					/** @author chuyufeng ***/
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(6);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage1To3Subject"), not.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage1To3Body"), member.getName(), not.getPostId(), not.getSubject());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}

				try {
					if (file1 != null && !file1.isEmpty()) {
						// 檔案 byte array
						byte[] bytes1 = file1.getBytes();
						JSONObject sn_json1 = new JSONObject();
						sn_json1.put("Id", messagePostAttachId1); // MessagePostAttachId
						sn_json1.put("MessageId", not.getId()); // MessageId
						sn_json1.put("FileDesc", fileDesc1); // 檔案說明
						sn_json1.put("FileName", file1.getOriginalFilename()); // 檔案名稱
						sn_json1.put("FileType", file1.getContentType()); // 檔案類型
						sn_json1.put("FileSize", file1.getSize()); // 檔案大小
						sn_json1.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes1.toString())); // 檔案Hash
						String json1 = sn_json1.toString();
						if (messagePostAttachId1 != 0)
							// update
							update1 = messagePostAttachService.update(getBaseMemberId(), json1, bytes1);
						else
							update1 = messagePostAttachService.insert(getBaseMemberId(), json1, bytes1);
					} else if (updateFile1 && messagePostAttachId1 != 0) {						
							JSONObject sn_json1 = new JSONObject();
							sn_json1.put("Id", messagePostAttachId1); // MessagePostAttachId
							sn_json1.put("MessageId", not.getId()); // MessageId
							sn_json1.put("FileDesc", fileDesc1); // 檔案說明
							sn_json1.put("FileName", JSONObject.NULL); // 檔案名稱
							sn_json1.put("FileType", JSONObject.NULL); // 檔案類型
							sn_json1.put("FileSize", JSONObject.NULL); // 檔案大小
							sn_json1.put("FileHash", JSONObject.NULL); // 檔案Hash
							String json1 = sn_json1.toString();							
							// update
							update1 = messagePostAttachService.update(getBaseMemberId(), json1, null);						
					} else if (messagePostAttachId1 != 0) {
						JSONObject sn_json1 = new JSONObject();
						sn_json1.put("Id", messagePostAttachId1); // MessagePostAttachId						
						sn_json1.put("FileDesc", fileDesc1); // 檔案說明						
						String json1 = sn_json1.toString();							
						// update
						update1 = messagePostAttachService.updateFileDesc(getBaseMemberId(), json1);						
					}

					if (file2 != null && !file2.isEmpty()) {
						// 檔案 byte array
						byte[] bytes2 = file2.getBytes();
						JSONObject sn_json2 = new JSONObject();
						sn_json2.put("Id", messagePostAttachId2); // MessagePostAttachId
						sn_json2.put("MessageId", not.getId()); // MessageId
						sn_json2.put("FileDesc", fileDesc2); // 檔案說明
						sn_json2.put("FileName", file2.getOriginalFilename()); // 檔案名稱
						sn_json2.put("FileType", file2.getContentType()); // 檔案類型
						sn_json2.put("FileSize", file2.getSize()); // 檔案大小
						sn_json2.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes2.toString())); // 檔案Hash
						String json2 = sn_json2.toString();

						if (messagePostAttachId2 != 0)
							// update
							update2 = messagePostAttachService.update(getBaseMemberId(), json2, bytes2);
						else
							update2 = messagePostAttachService.insert(getBaseMemberId(), json2, bytes2);
					} else if (updateFile2 && messagePostAttachId2 != 0) {	
						JSONObject sn_json2 = new JSONObject();
						sn_json2.put("Id", messagePostAttachId2); // MessagePostAttachId
						sn_json2.put("MessageId", not.getId()); // MessageId
						sn_json2.put("FileDesc", fileDesc2); // 檔案說明
						sn_json2.put("FileName", JSONObject.NULL); // 檔案名稱
						sn_json2.put("FileType", JSONObject.NULL); // 檔案類型
						sn_json2.put("FileSize", JSONObject.NULL); // 檔案大小
						sn_json2.put("FileHash", JSONObject.NULL); // 檔案Hash
						String json2 = sn_json2.toString();						
						// update
						update2 = messagePostAttachService.update(getBaseMemberId(), json2, null);
					} else if (messagePostAttachId2 != 0) {
						JSONObject sn_json2 = new JSONObject();
						sn_json2.put("Id", messagePostAttachId2); // MessagePostAttachId						
						sn_json2.put("FileDesc", fileDesc2); // 檔案說明						
						String json2 = sn_json2.toString();							
						// update
						update2 = messagePostAttachService.updateFileDesc(getBaseMemberId(), json2);						
					}

					if (file3 != null && !file3.isEmpty()) {
						// 檔案 byte array
						byte[] bytes3 = file3.getBytes();
						JSONObject sn_json3 = new JSONObject();
						sn_json3.put("Id", messagePostAttachId3); // MessagePostAttachId
						sn_json3.put("MessageId", not.getId()); // MessageId
						sn_json3.put("FileDesc", fileDesc3); // 檔案說明
						sn_json3.put("FileName", file3.getOriginalFilename()); // 檔案名稱
						sn_json3.put("FileType", file3.getContentType()); // 檔案類型
						sn_json3.put("FileSize", file3.getSize()); // 檔案大小
						sn_json3.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes3.toString())); // 檔案Hash
						String json3 = sn_json3.toString();
						if (messagePostAttachId3 != 0)
							update3 = messagePostAttachService.update(getBaseMemberId(), json3, bytes3);
						else
							update3 = messagePostAttachService.insert(getBaseMemberId(), json3, bytes3);
					} else if (updateFile3 && messagePostAttachId3 != 0) {	
						JSONObject sn_json3 = new JSONObject();
						sn_json3.put("Id", messagePostAttachId3); // MessagePostAttachId
						sn_json3.put("MessageId", not.getId()); // MessageId
						sn_json3.put("FileDesc", fileDesc3); // 檔案說明
						sn_json3.put("FileName", JSONObject.NULL); // 檔案名稱
						sn_json3.put("FileType", JSONObject.NULL); // 檔案類型
						sn_json3.put("FileSize", JSONObject.NULL); // 檔案大小
						sn_json3.put("FileHash", JSONObject.NULL); // 檔案Hash
						String json3 = sn_json3.toString();					
						// update
						update3 = messagePostAttachService.update(getBaseMemberId(), json3, null);
					} else if (messagePostAttachId3 != 0) {
						JSONObject sn_json3 = new JSONObject();
						sn_json3.put("Id", messagePostAttachId3); // MessagePostAttachId						
						sn_json3.put("FileDesc", fileDesc3); // 檔案說明						
						String json3 = sn_json3.toString();							
						// update
						update3 = messagePostAttachService.updateFileDesc(getBaseMemberId(), json3);						
					}

				} catch (Exception e) {
					error = true;
				}
				if (not != null && delmessagePost && messagePost && !error && update1 && update2 && update3) {
					responseJson.put("Id", not.getId());
					responseJson.put("PostId", not.getPostId());
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}

			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 附件下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param messageId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/n01/attach/download/{messageId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable String messageId, @PathVariable Long id) {
		JSONObject response_json = new JSONObject();
		JSONObject json = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// 準備要新增的檔案相關欄位為 json format

			json.put("MessageId", messageId); // messageId
			json.put("id", id); // 附件Id
			// String json = sn_json.toString();
			if (!messageService.isExist(messageId)) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!messagePostAttachService.isExist(id)) {
				response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				response_json.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				MessagePostAttach messagePostAttach = messagePostAttachService.getById(id);
				try {
					byte[] buffer = messagePostAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(messagePostAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// return response;

	}

	/**
	 * 審核警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/n01/examine", method = RequestMethod.POST)
	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			String preStatus = obj.isNull("PreStatus") == true ? null : obj.getString("PreStatus");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");
			String messagePostReleaseStatus = null;

			if (!messageService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {				
				// String oldPostId = messageService.getById(id).getPostId();
				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, id);
				Message message = messageService.examine(getBaseMemberId(), id, status, opinion);
				List<Member> all_members = memberService.getAll();
				// mail內容
				String impactLevel = "";
				String mailContent = "";
				if (message.getImpactLevel()!=null && message.getImpactLevel().equals("1"))
					impactLevel = "低";
				else if (message.getImpactLevel()!=null && message.getImpactLevel().equals("2"))
					impactLevel = "中";
				else if (message.getImpactLevel()!=null && message.getImpactLevel().equals("3"))
					impactLevel = "高";
				else
					impactLevel = "無";

				switch (message.getAlertCode()) {
					case "ANA" :
						if (message.getAffectPlatform() != null && !message.getAffectPlatform().equals("")) {
							mailContent += "<br />影響平台:" + message.getAffectPlatform();
						}
						mailContent += "<br />影響等級:" + impactLevel;
						if (message.getReference() != null && !message.getReference().equals("")) {
							mailContent += "<br />參考資料:" + message.getReference();
						}
						//mailContent = "<br />影響平台:" + message.getAffectPlatform() + "<br />影響等級:" + impactLevel + "<br />參考資料:" + message.getReference();
						break;
					case "DEF" :
						if (message.getDescription() != null && !message.getDescription().equals("")) {
							mailContent += "<br />事件描述:" + message.getDescription();
						}
						if (message.getReference() != null && !message.getReference().equals("")) {
							mailContent += "<br />參考資料:" + message.getReference();
						}
						//mailContent = "<br />事件描述:" + message.getDescription() + "<br />參考資料:" + message.getReference();
						break;
					case "INT" :
						if (message.getDescription() != null && !message.getDescription().equals("")) {
							mailContent += "<br />事件描述:" + message.getDescription();
						}
						if (message.getDetermine() != null && !message.getDetermine().equals("")) {
							mailContent += "<br />手法研判:" + message.getDetermine();
						}
						if (message.getReference() != null && !message.getReference().equals("")) {
							mailContent += "<br />參考資料:" + message.getReference();
						}
						//mailContent = "<br />事件描述:" + message.getDescription() + "<br />手法研判:" + message.getDetermine() + "<br />參考資料:" + message.getReference();
						break;
					case "EWA" :
						if (message.getDescription() != null && !message.getDescription().equals("")) {
							mailContent += "<br />事件描述:" + message.getDescription();
						}
						if (message.getReference() != null && !message.getReference().equals("")) {
							mailContent += "<br />參考資料:" + message.getReference();
						}
						//mailContent = "<br />事件描述:" + message.getDescription() + "<br />參考資料:" + message.getReference();
						break;
					case "FBI" :
						if (message.getDescription() != null && !message.getDescription().equals("")) {
							mailContent += "<br />事件描述:" + message.getDescription();
						}
						if (message.getAffectPlatform() != null && !message.getAffectPlatform().equals("")) {
							mailContent += "<br />影響平台:" + message.getAffectPlatform();
						}
						mailContent += "<br />影響等級:" + impactLevel;
						//mailContent = "<br />事件描述:" + message.getDescription() + "<br />影響平台:" + message.getAffectPlatform() + "<br />影響等級:" + impactLevel;
						break;
				}
				if (status.equals("5") || status.equals("8")) {
					if (status.equals("5"))
						messagePostReleaseStatus = "51";
					else
						messagePostReleaseStatus = "69";
					List<MessagePost> messagePosts = messagePostService.getBymessageId(id);
					List<MessagePost> CiLevels = messagePostService.getBymessageIdAndPostType(id,"5");
					Set<String> Ci=new HashSet<String>();    
					if (CiLevels != null) {
						for (MessagePost CiLevel : CiLevels)
							Ci.add(CiLevel.getPostContent());						
					}
					ArrayList<Long> orgIds = new ArrayList<Long>();					
					JSONArray bcc = new JSONArray();
					if (messagePosts != null) {
						for (MessagePost messagePost : messagePosts) {
							switch (messagePost.getPostType()) {																
								case "7" :
									if (!orgIds.contains(Long.parseLong(messagePost.getPostContent())) && orgService.isExist(Long.parseLong(messagePost.getPostContent())))
										orgIds.add(Long.parseLong(messagePost.getPostContent()));
									break;
								case "4" :										
									bcc.put(messagePost.getPostContent());
									if (message.getIsSms()) {
										if (true && messagePost.getPostMobilePhone() != null && !messagePost.getPostMobilePhone().trim().equals("")) {
											String smsMessage = "H-ISAC資安警訊" + message.getPostId() + message.getSmsFormat();
											smsService.Send("[測試]", messagePost.getPostMobilePhone(), smsMessage);
										}
									}
									break;
							}
						}
					}
					ArrayList<Member> members = new ArrayList<Member>();
					for (Long orgId : orgIds) {
						//單位底下所有memebr
						List<Member> membersByOrgId = memberService.getByOrgId(orgId);
						if (membersByOrgId != null)
							for (Member member : membersByOrgId)
								members.add(member);
						
						JSONObject sn_json = new JSONObject();
						sn_json.put("MessageId", id);
						sn_json.put("OrgId", orgId);
						sn_json.put("Status", messagePostReleaseStatus);
						sn_json.put("PreStatus", preStatus);
						sn_json.put("TableName", "messagePostRelease");
						sn_json.put("Opinion", opinion);
						String messagePostRelease_json = sn_json.toString();
						MessagePostRelease messagePostRelease = messagePostReleaseService.insert(getBaseMemberId(), messagePostRelease_json);						
						if (messagePostRelease == null) {
							responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
							responseJson.put("success", false);

							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
							return responseJson.toString();
						}

					}										

					// 寄信
					// 收件者：警訊收件者
					// 主旨：H-ISAC警訊單(postId)
					// 內容：entity.getName()，您好！H-ISAC已發布警訊單(HISAC-ANA-2018-00001)，請查閱！警訊內容entity.getContents()
					/** @author chuyufeng **/										
					// mail內容					
					String suggestion = message.getSuggestion() == null ? "無" : message.getSuggestion();
					String subject = message.getSubject() == null ? "無" : message.getSubject();
					String contents = message.getContents() == null ? "無" : message.getContents();					
					JSONArray bcc1 = new JSONArray();
					if (members != null) {				
						for (Member member : members) {
							if (member.getIsEnable()) {
								List<MemberRole> memberRoles = memberRoleService.getByMemberId(member.getId());
								if (memberRoles != null) {
									for (MemberRole memberRole : memberRoles) {
										if (memberRole.getRoleId() == 8 || memberRole.getRoleId() == 10) {
											bcc.put(member.getEmail());
											if (message.getIsSms()) {
												if (true && member.getMobilePhone() != null && !member.getMobilePhone().trim().equals("")) {
													String smsMessage = "H-ISAC資安警訊" + message.getPostId() + message.getSmsFormat();
													smsService.Send("[測試]", member.getMobilePhone(), smsMessage);
												}
											}
											if (message.getIsReply() && memberRole.getRoleId() == 10) {
												bcc1.put(member.getEmail());										
											}
											break;
										}
									}
								}
							}
						}
					}		
//					String recipients = "hisac-cs@mohw.gov.tw";
					String recipients = resourceMessageService.getMessageValue("globalFooterEmail");
					if (bcc.length() != 0) {
						String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To4-2Subject"), message.getPostId());
						String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage3To4-2Body"), "親愛的會員", message.getPostId(), subject, suggestion,
							contents + "<br>" + mailContent);
						mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), recipients, null, bcc, mailSubject, mailBody, null);
					}
					if (bcc1.length() != 0) {
						String mailSubject1 = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-3Subject"), message.getPostId());
						String mailBody1 = MessageFormat.format(resourceMessageService.getMessageValue("mailMessage4To5-3Body"), "親愛的會員", message.getPostId(), message.getContents());
						mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), recipients, null, bcc1, mailSubject1, mailBody1, null);
					}																								
				}
				if (message != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
			// } else {
			// json.put("msg", "PermissionDenied");
			// json.put("success", false);
			// }
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 回覆警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 回覆動作是否成功
	 */
	@RequestMapping(value = "/n01/reply", method = RequestMethod.POST)
	public @ResponseBody String Reply(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		obj.put("IsReview", false);			
		obj.put("IsCC", false);
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!messagePostReleaseService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Member member = memberService.get(getBaseMemberId());
				List<OrgSign> orgSigns = orgSignService.getByOrgId(member.getOrgId());
				List<Org> orgs = orgService.getAll();
				if (orgSigns != null) {
					for (OrgSign orgSign : orgSigns) {
						if (orgs != null) {
							for (Org org : orgs)
								if (orgSign.getParentOrgId().equals(org.getId()) && org.getIsEnable()) {
									if (orgSign.getWarningIsExamine().equals(1) || orgSign.getWarningIsExamine().equals(3)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsReview", true);
									}
									if (orgSign.getWarningIsExamine().equals(2)) {
										if (org.getAuthType().equals("2"))
											obj.put("IsCC", true);
									}
								}
						}
					}
				}
				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, id);

				if (messagePostReleaseService.reply(getBaseMemberId(), obj.toString()) != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 審核警訊資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 審核動作是否成功
	 */
	@RequestMapping(value = "/n01/examineMessageReleasePost", method = RequestMethod.POST)
	public @ResponseBody String ExamineMessageReleasePost(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!messagePostReleaseService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, id);

				if (messagePostReleaseService.examineMessageReleasePost(getBaseMemberId(), json) != null && processLog != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 警訊轉通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 轉通報動作是否成功
	 */
	@RequestMapping(value = "/n01/toAlert", method = RequestMethod.POST)
	public @ResponseBody String ToAlert(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!messageService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				ViewMessageAlertEvent message = messageService.getById(id);
				ViewMessagePostReleaseOrg messagePostRelease = messagePostReleaseService.getBymessageIdAndOrgId(id, getBaseOrgId());
				JSONObject sn_json = new JSONObject();
				sn_json.put("EventSourceNo", message.getPostId());
				sn_json.put("EventRemark", message.getSubject());
				sn_json.put("TransferInId", messagePostRelease.getId());
				sn_json.put("TransferInType", 8);
				sn_json.put("MessageId", message.getId());
				sn_json.put("ContactorUnit", getBaseOrgId());
				sn_json.put("EventSource", 2);
				switch (message.getEventCode()) {
					case "201" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt1", true);
						break;
					case "202" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt2", true);
						break;
					case "203" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt3", true);
						break;
					case "204" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt4", true);
						break;
					case "206" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt5", true);
						break;
					case "207" :
						sn_json.put("EventType", 1);
						sn_json.put("IsEventType1Opt6", true);
						break;
					case "301" :
						sn_json.put("EventType", 2);
						sn_json.put("IsEventType2Opt1", true);
						break;
					case "309" :
						sn_json.put("EventType", 2);
						sn_json.put("IsEventType2Opt2", true);
						break;
					default :
						sn_json.put("EventType", 5);
						sn_json.put("EventType5Other", message.getAlertTypeName() + "(" + message.getEventTypeName() + ")");
						break;
				}
				sn_json.put("Status", 1);
				sn_json.put("TableName", "notification");
				sn_json.put("Pre", "HISAC");
				Member member = memberService.get(getBaseMemberId());
				if (member != null) {					
					sn_json.put("ContactorId", member.getId());
					sn_json.put("ContactorName", member.getName());
					sn_json.put("ContactorTel", member.getCityPhone());
					sn_json.put("ContactorFax", member.getFaxPhone());
					sn_json.put("ContactorEmail", member.getEmail());
					Org org = orgService.getDataById(member.getOrgId());
					sn_json.put("ContactorUnit", org.getId());
					sn_json.put("ContactorUnitName", org.getName());
					ViewParentOrg superviseParentOrg = orgService.getSuperviseParentOrg(member.getOrgId());
					if (superviseParentOrg != null) {
						sn_json.put("MainUnit1", superviseParentOrg.getId());
						sn_json.put("MainUnit1Name", superviseParentOrg.getName());
					}
					ViewParentOrg localParentOrg = orgService.getLocalParentOrg(member.getOrgId());
					if (localParentOrg != null) {
						sn_json.put("MainUnit2", localParentOrg.getId());
						sn_json.put("MainUnit2Name", localParentOrg.getName());
					}
				}
				String str_json = sn_json.toString();
				Notification notification = notificationService.insert(getBaseMemberId(), str_json);
				if (notification != null) {
					MessagePostRelease entity = messagePostReleaseService.updateTransferOut(getBaseMemberId(), messagePostRelease.getId(), 8, notification.getId());
					if (entity == null) {
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", false);
						return responseJson.toString();
					}
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 取得警訊button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 警訊button count資料
	 */
	@RequestMapping(value = "/n01/query/button/count", method = RequestMethod.POST)
	public String QueryMessage(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			/*
			 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者
			 * 5-H-ISAC警訊建立者 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者
			 * 14-HISAC-情資審核者 8-權責單位聯絡人 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者
			 * 10-會員機構聯絡人 11-會員機構管理者
			 */
			if (baseMemberRole.IsAdmin == true) {
				obj.put("RoleId", 1);
			} else if (baseMemberRole.IsHisac == true) {
				obj.put("RoleId", 2);
			} else if (baseMemberRole.IsHisacInfoBuilder == true) {
				obj.put("RoleId", 5);
			} else if (baseMemberRole.IsHisacInfoSign == true) {
				obj.put("RoleId", 6);
			} else if (baseMemberRole.IsApplySign == true) {
				obj.put("RoleId", 7);
			} else if (baseMemberRole.IsApplyContact == true) {
				obj.put("RoleId", 8);
			} else if (baseMemberRole.IsMemberContact == true) {
				obj.put("RoleId", 10);
			}
			obj.put("OrgId", getBaseOrgId());
			obj.put("MemberId", getBaseMemberId());
			json = obj.toString();

			JSONArray sn_array = new JSONArray();
			List<SpButtonCount> spButtonCounts = messageService.getSpButtonCount(json);
			if (spButtonCounts != null)
				for (SpButtonCount spButtonCount : spButtonCounts) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Status", spButtonCount.getStatus());
					sn_json.put("Count", spButtonCount.getCount());
					sn_array.put(sn_json);
				}

			listjson.put("datatable", sn_array);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";

	}

}