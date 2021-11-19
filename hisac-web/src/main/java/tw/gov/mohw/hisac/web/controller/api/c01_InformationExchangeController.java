package tw.gov.mohw.hisac.web.controller.api;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.domain.AlertType;
import tw.gov.mohw.hisac.web.domain.EventType;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;
import tw.gov.mohw.hisac.web.domain.InformationSource;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;
import tw.gov.mohw.hisac.web.filter.MyFilter;
import tw.gov.mohw.hisac.web.service.AlertTypeService;
import tw.gov.mohw.hisac.web.service.EventTypeService;
import tw.gov.mohw.hisac.web.service.InformationExchangeAttachService;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.InformationSourceService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 情資管理(N-ISAC)控制器
 */
@Controller
@RequestMapping(value = "/cyb/api", produces = "application/json; charset=utf-8")
public class c01_InformationExchangeController extends BaseController {

	@Autowired
	private InformationExchangeService informationExchangeService;
	@Autowired
	private InformationSourceService informationSourceService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private AlertTypeService alertTypeService;
	@Autowired
	private ProcessLogService processLogService;
	@Autowired
	private MailService mailService;

	@Autowired
	private InformationExchangeAttachService informationExchangeAttachService;

	private String targetControllerName = "cyb";
	private String targetActionName = "c01";
	/**
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 條件查詢之情資資料
	 */
	@RequestMapping(value = "/c01/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		
		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		/*
		 * 1-SuperAdmin 2-H-ISAC管理者 3-H-ISAC內容維護者 4-H-ISAC內容審核者 5-H-ISAC警訊建立者
		 * 6-H-ISAC警訊審核者 12-HISAC通報審核者 13-HISAC情資維護者 14-HISAC-情資審核者 8-權責單位聯絡人
		 * 9-權責單位管理者 7-權責單位警訊審核者 15-權責單位通報審核者 10-會員機構聯絡人 11-會員機構管理者
		 */
		if (baseMemberRole.IsAdmin == true) {
			obj.put("RoleId", 1);
		} else if (baseMemberRole.IsHisac == true) {
			obj.put("RoleId", 2);
		} else if (baseMemberRole.IsHisacIXContent == true) {
			obj.put("RoleId", 13);
		} else if (baseMemberRole.IsHisacIXContentSign == true) {
			obj.put("RoleId", 14);
		}
		obj.put("OrgId", getBaseOrgId());
		obj.put("MemberId", getBaseMemberId());
		json = obj.toString();

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			listjson.put("total", informationExchangeService.getSpListSize(json));
			List<InformationExchange> informationExchanges = informationExchangeService.getSpList(json);
			if (informationExchanges != null) {
				JSONArray sn_array = new JSONArray();
				for (InformationExchange informationExchange : informationExchanges) {
					JSONObject sn_json = new JSONObject();
					sn_json = packageJSON(informationExchange);
					switch (informationExchange.getStatus()) {
						case 1 :
							if (baseMemberRole.IsHisacIXContent) {
								sn_json.put("IsButtonEdit", true);
								sn_json.put("IsButtonDelete", true);
							} else {
								sn_json.put("IsButtonEdit", false);
								sn_json.put("IsButtonDelete", false);
							}

							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsSeeLog", false);
							break;
						case 2 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);

							if (baseMemberRole.IsHisacIXContent)
								sn_json.put("IsButtonUndo", true);
							else
								sn_json.put("IsButtonUndo", false);

							sn_json.put("IsButtonReview", false);
							sn_json.put("IsSeeLog", true);
							break;
						case 3 :
						case 4 :
						case 5 :
						case 6 :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);

							if (baseMemberRole.IsHisacIXContentSign)
								sn_json.put("IsButtonReview", true);
							else
								sn_json.put("IsButtonReview", false);
							sn_json.put("IsSeeLog", true);
							break;
						case 8 :
							if (baseMemberRole.IsHisacIXContent)
								sn_json.put("IsButtonEdit", true);
							else
								sn_json.put("IsButtonEdit", false);

							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsSeeLog", true);
							break;
						default :
							sn_json.put("IsButtonEdit", false);
							sn_json.put("IsButtonDelete", false);
							sn_json.put("IsButtonUndo", false);
							sn_json.put("IsButtonReview", false);
							sn_json.put("IsSeeLog", true);
							break;
					}
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 情資資料
	 */
	@RequestMapping(value = "/c01/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();

		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			InformationExchange informationExchange = informationExchangeService.getById(id);
			JSONObject sn_json = new JSONObject();
			JSONArray informationExchange_array = new JSONArray();
			sn_json = packageJSON(informationExchange);
			List<ViewProcessLogMember> informationExchangeLogs = processLogService.getByPostId(id, "informationExchange");
			Date today = new Date();
			if (informationExchangeLogs != null) {
				for (ViewProcessLogMember informationExchangeLog : informationExchangeLogs) {
					JSONObject informationExchange_json = new JSONObject();
					informationExchange_json.put("PreStatus", informationExchangeLog.getPreStatus());
					informationExchange_json.put("Status", informationExchangeLog.getStatus());
					informationExchange_json.put("CreateTime", WebDatetime.toString(informationExchangeLog.getCreateTime()));
					informationExchange_json.put("Opinion", informationExchangeLog.getOpinion());
					informationExchange_json.put("CreateName", informationExchangeLog.getCreateName());
					informationExchange_json.put("PreName", informationExchangeLog.getPreName());
					informationExchange_json.put("Days", (today.getTime() - informationExchangeLog.getCreateTime().getTime()) / (1000 * 60 * 60 * 24));
					informationExchange_array.put(informationExchange_json);
				}
			}
			JSONArray inforArray = new JSONArray();
			String informationExchangeId = informationExchange.getId();
			List<InformationExchangeAttach> informationExchangeAttachList = informationExchangeAttachService.getByInfoExId(informationExchangeId);
			for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachList) {
				JSONObject inforJS = new JSONObject();
				inforJS.put("Id", informationExchangeAttach.getId());
				inforJS.put("FileName", informationExchangeAttach.getFileName());
				inforJS.put("name", informationExchangeAttach.getFileName());
				inforJS.put("FileDesc", informationExchangeAttach.getFileDesc());
				inforJS.put("FileSize", informationExchangeAttach.getFileSize());
				inforJS.put("FileHash", informationExchangeAttach.getFileHash());
				inforJS.put("InformationExchangeId", informationExchangeAttach.getInformationExchangeId());
				inforArray.put(inforJS);
			}
			sn_json.put("InformationExchangeAttach", inforArray);

			sn_json.put("InformationExchangeLog", informationExchange_array);
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param fileData1
	 *            fileData1
	 * @param fileData2
	 *            fileData2
	 * @param fileData3
	 *            fileData3
	 * @param fileDesc1
	 *            fileDesc1
	 * @param fileDesc2
	 *            fileDesc2
	 * @param fileDesc3
	 *            fileDesc3
	 * @param json
	 *            情資資料
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/c01/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestParam(value = "FileData1", required = false) MultipartFile fileData1, @RequestParam(value = "FileData2", required = false) MultipartFile fileData2,
			@RequestParam(value = "FileData3", required = false) MultipartFile fileData3, @RequestParam(value = "FileDesc1") String fileDesc1, @RequestParam(value = "FileDesc2") String fileDesc2, @RequestParam(value = "FileDesc3") String fileDesc3,
			@RequestParam("json") String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
				json = WebCrypto.getSafe(json);
				JSONObject obj = new JSONObject(json);
				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
				InformationExchange informationExchange = informationExchangeService.insert(getBaseMemberId(), json);
				if (isWriteProcessLog) {
					if (processLogService.insert(getBaseMemberId(), json, informationExchange.getId()) == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}
					// 寄信
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(14);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation1To3Subject"), informationExchange.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation1To3Body"), member.getName(), informationExchange.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}

				try {
					if (fileData1 != null && !fileData1.isEmpty()) {
						byte[] bytes = fileData1.getBytes();
						JSONObject js = new JSONObject();
						js.put("InformationExchangeId", informationExchange.getId());
						js.put("FileDesc", fileDesc1);
						js.put("FileName", fileData1.getOriginalFilename());
						js.put("FileType", fileData1.getContentType());
						js.put("FileSize", fileData1.getSize());
						js.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
						informationExchangeAttachService.insert(getBaseMemberId(), js.toString(), bytes);
					}

					if (fileData2 != null && !fileData2.isEmpty()) {
						byte[] bytes2 = fileData2.getBytes();
						JSONObject js2 = new JSONObject();
						js2.put("InformationExchangeId", informationExchange.getId());
						js2.put("FileDesc", fileDesc2);
						js2.put("FileName", fileData2.getOriginalFilename());
						js2.put("FileType", fileData2.getContentType());
						js2.put("FileSize", fileData2.getSize());
						js2.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes2.toString()));
						informationExchangeAttachService.insert(getBaseMemberId(), js2.toString(), bytes2);
					}

					if (fileData3 != null && !fileData3.isEmpty()) {
						byte[] bytes3 = fileData3.getBytes();
						JSONObject js3 = new JSONObject();
						js3.put("InformationExchangeId", informationExchange.getId());
						js3.put("FileDesc", fileDesc3);
						js3.put("FileName", fileData3.getOriginalFilename());
						js3.put("FileType", fileData3.getContentType());
						js3.put("FileSize", fileData3.getSize());
						js3.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes3.toString()));
						informationExchangeAttachService.insert(getBaseMemberId(), js3.toString(), bytes3);
					}
				} catch (IOException e) {
					//e.printStackTrace();
				}

				if (informationExchange != null) {
					responseJson.put("Id", informationExchange.getId());
					responseJson.put("PostId", informationExchange.getPostId());
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 更新情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @param fileData1
	 *            上傳檔案1
	 * @param fileData2
	 *            上傳檔案2
	 * @param fileData3
	 *            上傳檔案3
	 * @param fileDesc1
	 *            檔案描述1
	 * @param fileDesc2
	 *            檔案描述2
	 * @param fileDesc3
	 *            檔案描述3
	 * @param fileAttachId1
	 *            附件Id 1
	 * @param fileAttachId2
	 *            附件Id 2
	 * @param fileAttachId3
	 *            附件Id 3
	 * @param updateFile1
	 *            檔案1
	 * @param updateFile2
	 *            檔案2
	 * @param updateFile3
	 *            檔案3
	 * @return 是否上傳成功
	 */
	@RequestMapping(value = "/c01/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, Model model, @RequestParam("json") String json, @RequestParam(value = "FileData1", required = false) MultipartFile fileData1,
			@RequestParam(value = "FileData2", required = false) MultipartFile fileData2, @RequestParam(value = "FileData3", required = false) MultipartFile fileData3, @RequestParam(value = "FileDesc1") String fileDesc1,
			@RequestParam(value = "FileDesc2") String fileDesc2, @RequestParam(value = "FileDesc3") String fileDesc3, @RequestParam(value = "FileAttachId1") long fileAttachId1, @RequestParam(value = "FileAttachId2") long fileAttachId2,
			@RequestParam(value = "FileAttachId3") long fileAttachId3, @RequestParam(value = "UpdateFile1") Boolean updateFile1, @RequestParam(value = "UpdateFile2") Boolean updateFile2, @RequestParam(value = "UpdateFile3") Boolean updateFile3) {
		JSONObject responseJson = new JSONObject();
		json = WebCrypto.getSafe(json);
		json = new JSONObject(json).getJSONObject("data").toString();
		JSONObject obj = new JSONObject(json);
		json = obj.toString();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);

		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Boolean update1 = true;
			Boolean update2 = true;
			Boolean update3 = true;
			Boolean error = false;
			// JSONObject obj = new JSONObject(json).getJSONObject("data");
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");
			if (!informationExchangeService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				InformationExchange informationExchange = informationExchangeService.update(getBaseMemberId(), json);
				if (isWriteProcessLog) {
					if (processLogService.insert(getBaseMemberId(), json, id) == null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						return responseJson.toString();
					}
					// 寄信
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(14);
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation1To3Subject"), informationExchange.getPostId());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformation1To3Body"), member.getName(), informationExchange.getPostId());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}
				if (informationExchange != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}

				/**** 檔案修改 ****/
				try {

					if ((fileData1 != null) && (fileAttachId1 != 0)) {// 檔案修改，資料重新上傳
						JSONObject js1 = new JSONObject();
						byte[] bytes1 = fileData1.getBytes();
						js1.put("Id", fileAttachId1);
						js1.put("InformationExchangeId", id);
						js1.put("FileName", fileData1.getOriginalFilename());
						js1.put("FileType", fileData1.getContentType());
						js1.put("FileSize", fileData1.getSize());
						js1.put("FileDesc", fileDesc1);
						js1.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes1.toString()));
						update1 = informationExchangeAttachService.update(getBaseMemberId(), bytes1, js1);
					} else if ((fileData1 == null) && (fileAttachId1 != 0) && !updateFile1) { // 修改說明
						JSONObject js1 = new JSONObject();
						js1.put("Id", fileAttachId1);
						js1.put("FileDesc", fileDesc1);
						update1 = informationExchangeAttachService.update(getBaseMemberId(), js1.toString()) == null ? false : true;
					} else if ((fileData1 == null) && fileDesc1.isEmpty() && updateFile1 && fileAttachId1 != 0) { // 檔案刪除
						update1 = informationExchangeAttachService.delete(fileAttachId1);
					} else if (fileAttachId1 == 0 && fileData1 != null && (informationExchangeAttachService.getByInfoExId(id).size() < 3)) { // 新增檔案
						JSONObject js1 = new JSONObject();
						byte[] bytes1 = fileData1.getBytes();
						js1.put("InformationExchangeId", informationExchange.getId());
						js1.put("FileDesc", fileDesc1);
						js1.put("FileName", fileData1.getOriginalFilename());
						js1.put("FileType", fileData1.getContentType());
						js1.put("FileSize", fileData1.getSize());
						js1.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes1.toString()));
						update1 = informationExchangeAttachService.insert(getBaseMemberId(), js1.toString(), bytes1) == null ? false : true;
					}

					if ((fileData2 != null) && (fileAttachId2 != 0)) {// 檔案修改，資料重新上傳
						JSONObject js2 = new JSONObject();
						byte[] bytes2 = fileData2.getBytes();
						js2.put("Id", fileAttachId2);
						js2.put("InformationExchangeId", id);
						js2.put("FileName", fileData2.getOriginalFilename());
						js2.put("FileType", fileData2.getContentType());
						js2.put("FileSize", fileData2.getSize());
						js2.put("FileDesc", fileDesc2);
						js2.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes2.toString()));
						update2 = informationExchangeAttachService.update(getBaseMemberId(), bytes2, js2);
					} else if ((fileData2 == null) && (fileAttachId2 != 0) && !updateFile2) { // 修改說明
						JSONObject js2 = new JSONObject();
						js2.put("Id", fileAttachId2);
						js2.put("FileDesc", fileDesc2);
						update2 = informationExchangeAttachService.update(getBaseMemberId(), js2.toString()) == null ? false : true;
					} else if ((fileData2 == null) && fileDesc2.isEmpty() && updateFile2 && fileAttachId2 != 0) { // 檔案刪除
						update2 = informationExchangeAttachService.delete(fileAttachId2);
					} else if (fileAttachId2 == 0 && fileData2 != null && (informationExchangeAttachService.getByInfoExId(id).size() < 3)) { // 新增檔案
						JSONObject js2 = new JSONObject();
						byte[] bytes2 = fileData2.getBytes();
						js2.put("InformationExchangeId", informationExchange.getId());
						js2.put("FileDesc", fileDesc2);
						js2.put("FileName", fileData2.getOriginalFilename());
						js2.put("FileType", fileData2.getContentType());
						js2.put("FileSize", fileData2.getSize());
						js2.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes2.toString()));
						update2 = informationExchangeAttachService.insert(getBaseMemberId(), js2.toString(), bytes2) == null ? false : true;
					}

					if ((fileData3 != null) && (fileAttachId3 != 0)) {// 檔案修改，資料重新上傳
						JSONObject js3 = new JSONObject();
						byte[] bytes3 = fileData3.getBytes();
						js3.put("Id", fileAttachId3);
						js3.put("InformationExchangeId", id);
						js3.put("FileName", fileData3.getOriginalFilename());
						js3.put("FileType", fileData3.getContentType());
						js3.put("FileSize", fileData3.getSize());
						js3.put("FileDesc", fileDesc3);
						js3.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes3.toString()));
						update3 = informationExchangeAttachService.update(getBaseMemberId(), bytes3, js3);
					} else if ((fileData3 == null) && (fileAttachId3 != 0) && !updateFile3) { // 修改說明
						JSONObject js3 = new JSONObject();
						js3.put("Id", fileAttachId3);
						js3.put("FileDesc", fileDesc3);
						update3 = informationExchangeAttachService.update(getBaseMemberId(), js3.toString()) == null ? false : true;
					} else if ((fileData3 == null) && fileDesc3.isEmpty() && updateFile3 && fileAttachId3 != 0) { // 檔案刪除
						update3 = informationExchangeAttachService.delete(fileAttachId3);
					} else if (fileAttachId3 == 0 && fileData3 != null && (informationExchangeAttachService.getByInfoExId(id).size() < 3)) { // 新增檔案
						JSONObject js3 = new JSONObject();
						byte[] bytes3 = fileData3.getBytes();
						js3.put("InformationExchangeId", informationExchange.getId());
						js3.put("FileDesc", fileDesc3);
						js3.put("FileName", fileData3.getOriginalFilename());
						js3.put("FileType", fileData3.getContentType());
						js3.put("FileSize", fileData3.getSize());
						js3.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes3.toString()));
						update3 = informationExchangeAttachService.insert(getBaseMemberId(), js3.toString(), bytes3) == null ? false : true;
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

				if (error != null && !error && update1 && update2 && update3) {
					// responseJson.put("Id", id);
					// responseJson.put("PostId", baseMemberId);
					responseJson.put("Id", informationExchange.getId());
					responseJson.put("PostId", informationExchange.getPostId());
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
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}
	/**
	 * 刪除情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            情資資料
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/c01/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? "" : obj.getString("Id");
			if (!informationExchangeService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (informationExchangeAttachService.deleteByinformationid(id) && informationExchangeService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success,  getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());
				}
			}

		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny,  getBaseIpAddress(), getBaseMemberAccount());
		}
		return WebCrypto.getSafe(responseJson.toString());
	}

	// /**
	// * 取alert_type，取得alert
	// code和name，作為呈現的選擇，此資料並不會存入，只是一個在選event的初階條件，方便選event
	// *
	// * @param locale
	// * Locale
	// * @param request
	// * HttpServletRequest
	// * @param model
	// * Model
	// * @param json
	// * alert
	// * @return event_type list
	// */
	// @RequestMapping(value = "/c01/getAlertTypeCodes", method =
	// RequestMethod.POST)
	// public String getAlertTypeCodes(Locale locale, HttpServletRequest
	// request, Model model,@RequestBody String json) {
	// JSONArray sn_array = new JSONArray();
	// List<AlertType> alertTypes = alertTypeService.getList();
	// for(AlertType alertType:alertTypes) {
	// JSONObject obj = new JSONObject();
	// obj.put("Code", alertType.getCode());
	// obj.put("Name", alertType.getName());
	// sn_array.put(obj);
	// }
	// model.addAttribute("json", sn_array.toString());
	// return "msg";
	//
	// }
	//
	/**
	 * 取event_type table中的code，當作是這裡面的 Category
	 * 
	 * 取得alertType資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            event_type list
	 * @return alertType資料
	 */
	@RequestMapping(value = "/c01/getalert", method = RequestMethod.POST)
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
	 *            eventType資料
	 * @return eventType資料
	 */
	@RequestMapping(value = "/c01/getevent", method = RequestMethod.POST)
	public String Getevent(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			json = WebCrypto.getSafe(json);
			JSONObject obj = new JSONObject(json);
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			MyFilter myFilter = new MyFilter();
			alertCode = myFilter.filterString(alertCode);
			List<EventType> eventTypes = eventTypeService.getByAlertCode(alertCode);
			if (eventTypes != null) {
				for (EventType eventType : eventTypes) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", eventType.getCode());
					sn_json.put("Name", eventType.getName());
					sn_array.put(sn_json);
				}
			}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	 *            eventType資料
	 * @return eventType資料
	 */
	@RequestMapping(value = "/c01/getevent/all", method = RequestMethod.POST)
	public String GeteventAll(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// JSONObject obj = new JSONObject(json);
			// MyFilter myFilter = new MyFilter();
			List<EventType> eventTypes = eventTypeService.getAll();
			if (eventTypes != null)
				for (EventType eventType : eventTypes) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", eventType.getCode());
					sn_json.put("Name", eventType.getName());
					sn_array.put(sn_json);
				}
			model.addAttribute("json", sn_array.toString());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
	@RequestMapping(value = "/c01/getsource", method = RequestMethod.POST)
	public String Getsource(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<InformationSource> informationSources = informationSourceService.getAll();
			if (informationSources != null)
				for (InformationSource informationSource : informationSources) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Code", informationSource.getCode());
					sn_json.put("Name", informationSource.getName());
					sn_array.put(sn_json);
				}
			model.addAttribute("json", sn_array.toString());
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return "msg";
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
	@RequestMapping(value = "/c01/examine", method = RequestMethod.POST)
	public @ResponseBody String Examine(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();

		json = WebCrypto.getSafe(json);
		JSONObject obj = new JSONObject(json);
		String id = obj.isNull("Id") == true ? null : obj.getString("Id");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");

		if (!informationExchangeService.isExist(id)) {
			responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			ProcessLog processLog = processLogService.insert(getBaseMemberId(), json, id);
			InformationExchange informationExchange = informationExchangeService.examine(getBaseMemberId(), id, status, opinion);
			if (informationExchange != null && processLog != null) {
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
		return WebCrypto.getSafe(responseJson.toString());
	}

	/**
	 * 情資檔案下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param infoExId
	 *            情資Id
	 * @param id
	 *            流水號Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/c01/attach/download/{infoExId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable String infoExId, @PathVariable Long id) {
		JSONObject jsLog = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			jsLog.put("IncidentId", infoExId);
			jsLog.put("information_exchange_attach id", id);
			InformationExchangeAttach informationExchangeAttach = informationExchangeAttachService.get(id);
			try {
				response.reset();
				byte[] buffer = informationExchangeAttach.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationExchangeAttach.getFileName()));
				response.addHeader("Content-Length", "" + buffer.length);
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
			systemLogService.insert(baseControllerName, baseActionName, jsLog.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, jsLog.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}

	/**
	 * 情資資料中的原始XML檔案下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param IncidentId
	 *            來源Id
	 * @param id
	 *            流水號Id(51.原始XML檔案 檔名:IncidentId.XML SourceContentXml
	 *            IncidentId:XXXX-ANA-201602-1011，id:01a68da67b9c46bba47c99b23a11de36)
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/c01/contentXml/download/{IncidentId}/{id}", method = RequestMethod.GET)
	public void DownloadContentXml(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable String IncidentId, @PathVariable String id) {
		JSONObject jsLog = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			jsLog.put("IncidentId", IncidentId);
			jsLog.put("information_exchange id", id);
			InformationExchange informationExchange = informationExchangeService.getById(id);
			try {
				response.reset();
				byte[] buffer = informationExchange.getSourceContentXml();
				String fileName = WebCrypto.generateUUID(); // informationExchange.getId().toString();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xml"));
				response.addHeader("Content-Length", "" + buffer.length);
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
			systemLogService.insert(baseControllerName, baseActionName, jsLog.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, jsLog.toString(), SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// InformationExchange informationExchange =
		// informationExchangeService.getById(contentXml);
		// InformationExchangeServic

	}

	/**
	 * 資料整理封裝
	 * 
	 * @param informationExchange
	 *            InformationExchange
	 * @return JSONObject
	 */
	private JSONObject packageJSON(InformationExchange informationExchange) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("Id", informationExchange.getId());
		sn_json.put("PostId", informationExchange.getPostId());
		sn_json.put("SourceCode", informationExchange.getSourceCode());
		sn_json.put("StixTitle", informationExchange.getStixTitle());
		sn_json.put("IncidentId", informationExchange.getIncidentId());
		sn_json.put("IncidentTitle", informationExchange.getIncidentTitle());
		sn_json.put("Description", myFilter.stripXSS(informationExchange.getDescription()));
		sn_json.put("Category", informationExchange.getCategory());
		sn_json.put("ReporterName", informationExchange.getReporterName());
		sn_json.put("ResponderPartyName", informationExchange.getResponderPartyName());
		sn_json.put("ResponderContactNumbers", informationExchange.getResponderContactNumbers());
		sn_json.put("ResponderElectronicAddressIdentifiers", informationExchange.getResponderElectronicAddressIdentifiers());
		double impactQualification = 0;
		if (informationExchange.getSourceCode() != null) {
			if (informationExchange.getSourceCode().equals("SEC")) {
				impactQualification = ((double) informationExchange.getImpactQualification()) / 10;
				DecimalFormat df = new DecimalFormat("0.0");
				sn_json.put("ImpactQualification", df.format(impactQualification));
			} else {
				sn_json.put("ImpactQualification", informationExchange.getImpactQualification());
			}
		}
		sn_json.put("CoaDescription", myFilter.stripXSS(informationExchange.getCoaDescription()));
		sn_json.put("Confidence", informationExchange.getConfidence());
		sn_json.put("Reference", myFilter.stripXSS(informationExchange.getReference()));
		sn_json.put("ObservableAttach", informationExchange.getObservableAttach());
		sn_json.put("ObservableIpAddress", informationExchange.getObservableIpAddress());
		sn_json.put("SocketIpAddress", informationExchange.getSocketIpAddress());
		sn_json.put("SocketPort", informationExchange.getSocketPort());
		sn_json.put("SocketProtocol", informationExchange.getSocketProtocol());
		sn_json.put("CustomIpAddress", informationExchange.getCustomIpAddress());
		sn_json.put("CustomPort", informationExchange.getCustomPort());
		sn_json.put("CustomProtocol", informationExchange.getCustomProtocol());
		sn_json.put("DestinationIpAddress", informationExchange.getDestinationIpAddress());
		sn_json.put("DestinationPort", informationExchange.getDestinationPort());
		sn_json.put("DestinationProtocol", informationExchange.getDestinationProtocol());
		sn_json.put("LeveragedDescription", myFilter.stripXSS(informationExchange.getLeveragedDescription()));
		sn_json.put("AffectedSoftwareDescription", myFilter.stripXSS(informationExchange.getAffectedSoftwareDescription()));
		sn_json.put("ResourcesSourceIpAddress", informationExchange.getResourcesSourceIpAddress());
		sn_json.put("ResourcesSourcePort", informationExchange.getResourcesSourcePort());
		sn_json.put("ResourcesDestinationPort", informationExchange.getResourcesDestinationPort());
		sn_json.put("ResourcesDestinationProtocol", informationExchange.getResourcesDestinationProtocol());
		sn_json.put("ResourcesDestination", informationExchange.getResourcesDestination());
		sn_json.put("HasXml", informationExchange.getSourceContentXml() == null ? false : true);
		sn_json.put("ScanEngine", informationExchange.getScanEngine());
		sn_json.put("ScanVersion", informationExchange.getScanVersion());
		sn_json.put("ScanResult", informationExchange.getScanResult());
		sn_json.put("RelatedIncidentId", informationExchange.getRelatedIncidentId());

		sn_json.put("ModifyTime", WebDatetime.toString(informationExchange.getModifyTime()));
		sn_json.put("CreateTime", WebDatetime.toString(informationExchange.getCreateTime()));
		sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(informationExchange.getIncidentDiscoveryTime()));
		sn_json.put("IncidentReportedTime", WebDatetime.toString(informationExchange.getIncidentReportedTime()));
		sn_json.put("IncidentClosedTime", WebDatetime.toString(informationExchange.getIncidentClosedTime()));
		sn_json.put("RelatedIncidentTimestamp", WebDatetime.toString(informationExchange.getRelatedIncidentTimestamp()));

		sn_json.put("Status", informationExchange.getStatus());

		sn_json.put("IsEnable", informationExchange.getIsEnable());

		sn_json.put("ReporterNameUrl", informationExchange.getReporterNameUrl());
		sn_json.put("NhiSourceRecord", informationExchange.getNhiSourceRecord());
		sn_json.put("NhiImpact", informationExchange.getNhiImpact());
		sn_json.put("NhiRiskGrade", informationExchange.getNhiRiskGrade());
		sn_json.put("NhiRiskType", informationExchange.getNhiRiskType());
		sn_json.put("NhiProcess", informationExchange.getNhiProcess());
		sn_json.put("NhiProblemIpAddress", informationExchange.getNhiProblemIpAddress());
		sn_json.put("NhiProblemPort", informationExchange.getNhiProblemPort());
		sn_json.put("NhiProblemUrl", informationExchange.getNhiProblemUrl());
		sn_json.put("NhiProblemEquipmentOwner", informationExchange.getNhiProblemEquipmentOwner());
		sn_json.put("NhiProblemEquipmentUse", informationExchange.getNhiProblemEquipmentUse());
		sn_json.put("NhiRemark", informationExchange.getNhiRemark());
		// sn_json.put("CreateId", informationExchange.getCreateId());
		// sn_json.put("CreateTime", informationExchange.getCreateTime());
		// sn_json.put("ModifyId", informationExchange.getModifyId());
		// sn_json.put("ModifyTime", informationExchange.getModifyTime());

		return sn_json;
	}

	/**
	 * 取得通報button count資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件`
	 * @return 通報button count資料
	 */
	@RequestMapping(value = "/c01/query/button/count", method = RequestMethod.POST)
	public String QueryButtonCount(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
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
			} else if (baseMemberRole.IsHisacIXContent == true) {
				obj.put("RoleId", 13);
			} else if (baseMemberRole.IsHisacIXContentSign == true) {
				obj.put("RoleId", 14);
			}

			obj.put("OrgId", getBaseOrgId());
			obj.put("MemberId", getBaseMemberId());
			json = obj.toString();

			JSONArray sn_array = new JSONArray();
			List<SpButtonCount> spButtonCounts = informationExchangeService.getSpButtonCount(json);
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
