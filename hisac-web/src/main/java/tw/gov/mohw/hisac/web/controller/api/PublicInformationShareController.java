package tw.gov.mohw.hisac.web.controller.api;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
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
import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;
import tw.gov.mohw.hisac.web.domain.InformationManagementPic;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.service.InformationManagementAttachService;
import tw.gov.mohw.hisac.web.service.InformationManagementPicService;
import tw.gov.mohw.hisac.web.service.InformationManagementService;
import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 最新消息管理控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicInformationShareController extends BaseController {

	@Autowired
	private InformationManagementService informationManagementService;	
	
	@Autowired
	private InformationManagementAttachService informationManagementAttachService;
	
	@Autowired
	private InformationManagementPicService informationManagementPicService;
	
	@Autowired
	private MailService mailService;	
	@Autowired
	private ProcessLogService processLogService;	
	
	/**
	 * 新增InformationManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            InformationManagement
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/information_share/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		
		
			JSONObject obj = new JSONObject(json);			
			Long type = obj.isNull("Type") == true ? 0 : obj.getLong("Type");	
		
			InformationManagement informationManagement = informationManagementService.insert(type, json);

			// 流程紀錄用 - 開始			
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			if (isWriteProcessLog) {
				// 寄信				
				List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4);
				// H-ISAC內容審核者
				if (memberRoles != null) {
					for (ViewMemberRoleMember memberRole : memberRoles) {
						Member member = memberService.get(memberRole.getMemberId());
						if (member != null && member.getIsEnable()) {
							String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Subject"), informationManagement.getTitle());
							String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Body"), member.getName(), informationManagement.getTitle());
							mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
						}
					}
				}
				processLogService.insert(type, json, String.valueOf(informationManagement.getId()));
			}
			// 流程紀錄用 - 結束

			if (informationManagement != null) {

				// 流程紀錄用 - 開始
				responseJson.put("Id", informationManagement.getId());				
				// 流程紀錄用 - 結束

				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		return responseJson.toString();
	}
	
	
	/**
	 * 更新InformationManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            InformationManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/information_share/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");			
			Long type = obj.isNull("Type") == true ? 0 : obj.getLong("Type");	

			if (!informationManagementService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				InformationManagement informationManagement = informationManagementService.update(type, json);

				// 流程紀錄用 - 開始
				Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

				if (isWriteProcessLog) {
					processLogService.insert(type, json, String.valueOf(informationManagement.getId()));

					// 寄信
					// 收件者：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getEmail()
					// 主旨：H-ISAC最新消息("not.getPostId()")審核通知
					// 內容：memberService.get((memberRoleService.findByRoleId(4)).getMemberId()).getName()，您好！最新消息("not.getPostId()")，正等待您的審查，請您儘快撥冗進行處理，謝謝！
					List<ViewMemberRoleMember> memberRoles = memberRoleService.findByRoleId(4); // 4
																								// H-ISAC內容審核者
					if (memberRoles != null) {
						for (ViewMemberRoleMember memberRole : memberRoles) {
							Member member = memberService.get(memberRole.getMemberId());
							if (member != null && member.getIsEnable()) {
								String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Subject"), informationManagement.getTitle());
								String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement1To3Body"), member.getName(), informationManagement.getTitle());
								mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
							}
						}
					}
				}
				// 流程紀錄用 - 結束

				if (informationManagement != null) {

					// 流程紀錄用 - 開始
					responseJson.put("Id", informationManagement.getId());					
					// 流程紀錄用 - 結束

					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}		
		return responseJson.toString();
	}
	
	/**
	 * 取得圖片資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 圖片資料
	 */
	@RequestMapping(value = "/information_share/pic/query", method = RequestMethod.POST)
	public String QueryPic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();		
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long informationManagementId = obj.isNull("InformationManagementId") == true ? 0 : obj.getLong("InformationManagementId");
			List<InformationManagementPic> informationManagementPics = informationManagementPicService.getAllByInformationManagementId(informationManagementId);
			if (informationManagementPics != null) {
				for (InformationManagementPic informationManagementPic : informationManagementPics) {
					long size = informationManagementPic.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					String imageLink = "<img src=\"./api/p01/pic/download/" + informationManagementId + "/" + informationManagementPic.getId() + "\" title=\"" + informationManagementPic.getFileDesc() + "\" width=\"" + informationManagementPic.getImageWidth() + "\">";
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", informationManagementPic.getId());
					sn_json.put("FileName", informationManagementPic.getFileName());
					sn_json.put("FileType", informationManagementPic.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("ImageWidth", informationManagementPic.getImageWidth());
					sn_json.put("ImageHeight", informationManagementPic.getImageHeight());
					sn_json.put("ImageLink", imageLink);
					sn_json.put("FileDesc", informationManagementPic.getFileDesc());
					sn_json.put("CreateId", informationManagementPic.getCreateId());					
					sn_json.put("CreateTime", WebDatetime.toString(informationManagementPic.getCreateTime()));
					sn_json.put("ModifyId", informationManagementPic.getModifyId());					
					sn_json.put("ModifyTime", WebDatetime.toString(informationManagementPic.getModifyTime()));
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 上傳圖檔API
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            InformationManagementId
	 * @param fileDesc
	 *            檔案描述
	 * @param model
	 *            Model
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @return 是否成功
	 */
	@RequestMapping(value = "/information_share/pic/upload", method = RequestMethod.POST)
	public String UploadPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, @RequestParam("Type") Long type,  Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();		
			JSONObject sn_json = new JSONObject();
			sn_json.put("InformationManagementId", id);
			sn_json.put("FileDesc", fileDesc);
			String json = sn_json.toString();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename());
					sn_json.put("FileType", file.getContentType());
					sn_json.put("FileSize", file.getSize());
					Image image = ImageIO.read(file.getInputStream());
					int imageWidth = image.getWidth(null);
					int imageHeight = image.getHeight(null);
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
					sn_json.put("ImageWidth", imageWidth);
					sn_json.put("ImageHeight", imageHeight);
					json = sn_json.toString();
					InformationManagement informationManagement = informationManagementService.get(id);
					if (informationManagement != null) {
						InformationManagementPic entity = informationManagementPicService.insert(type, json, bytes);
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (Exception e) {
					//e.printStackTrace();
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}		
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 刪除圖檔API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/information_share/pic/delete", method = RequestMethod.POST)
	public @ResponseBody String DeletePic(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();		
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!informationManagementPicService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (informationManagementPicService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}		
		return responseJson.toString();
	}

	/**
	 * 圖片輸出
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param informationManagementId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/information_share/pic/download/{informationManagementId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("InformationManagementId", informationManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();		
			if (!informationManagementService.isExist(informationManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!informationManagementPicService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				InformationManagementPic informationManagementPic = informationManagementPicService.getById(id);
				try {
					byte[] buffer = informationManagementPic.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationManagementPic.getFileName(), StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(informationManagementPic.getFileType());
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}		
	}

	/**
	 * 取得附件資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 附件資料
	 */
	@RequestMapping(value = "/information_share/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();		
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long informationManagementId = obj.isNull("InformationManagementId") == true ? 0 : obj.getLong("InformationManagementId");
			List<InformationManagementAttach> informationManagementAttachs = informationManagementAttachService.getAllByInformationManagementId(informationManagementId);
			if (informationManagementAttachs != null) {
				for (InformationManagementAttach informationManagementAttach : informationManagementAttachs) {
					long size = informationManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("InformationManagementId", informationManagementAttach.getInformationManagementId());
					sn_json.put("Id", informationManagementAttach.getId());
					sn_json.put("FileName", informationManagementAttach.getFileName());
					sn_json.put("FileType", informationManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", informationManagementAttach.getFileHash());
					sn_json.put("FileDesc", informationManagementAttach.getFileDesc());
					sn_json.put("CreateId", informationManagementAttach.getCreateId());					
					sn_json.put("CreateTime", WebDatetime.toString(informationManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", informationManagementAttach.getModifyId());					
					sn_json.put("ModifyTime", WebDatetime.toString(informationManagementAttach.getModifyTime()));
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 上傳附件API
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            InformationManagementId
	 * @param fileDesc
	 *            檔案描述
	 * @param model
	 *            Model
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @return 是否成功
	 */
	@RequestMapping(value = "/information_share/attach/upload", method = RequestMethod.POST)
	public String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, @RequestParam("Type") Long type, Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();		
			JSONObject sn_json = new JSONObject();
			sn_json.put("InformationManagementId", id);
			sn_json.put("FileDesc", fileDesc);
			String json = sn_json.toString();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename());
					sn_json.put("FileType", file.getContentType());
					sn_json.put("FileSize", file.getSize());
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
					json = sn_json.toString();
					InformationManagement informationManagement = informationManagementService.get(id);
					if (informationManagement != null) {
						InformationManagementAttach entity = informationManagementAttachService.insert(type, json, bytes);
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (Exception e) {
					//e.printStackTrace();
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}	
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 刪除附件API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/information_share/attach/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();		
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!informationManagementAttachService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (informationManagementAttachService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}		
		return responseJson.toString();

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
	 * @param informationManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/information_share/attach/download/{informationManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("InformationManagementId", informationManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();		
			if (!informationManagementService.isExist(informationManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!informationManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				InformationManagementAttach informationManagementAttach = informationManagementAttachService.getById(id);
				try {
					byte[] buffer = informationManagementAttach.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(informationManagementAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationManagementAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}	
	}


}