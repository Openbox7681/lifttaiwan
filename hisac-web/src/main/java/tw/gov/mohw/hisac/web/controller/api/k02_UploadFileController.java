package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.MaintainPlanCheckListSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireSampleAttach;
import tw.gov.mohw.hisac.web.domain.MaintainPlanSelfEvaluationSampleAttach;
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanSelfEvaluationSampleAttachService;

/**
 * 中繼站匯入控制器
 */
@Controller
@RequestMapping(value = "/kin/api", produces = "application/json; charset=utf-8")
public class k02_UploadFileController extends BaseController {
	@Autowired
	private MaintainPlanCheckListSampleAttachService maintainPlanCheckListSampleAttachService;
	
	@Autowired
	private MaintainPlanQuestionnaireSampleAttachService maintainPlanQuestionnaireSampleAttachService;

	@Autowired
	private MaintainPlanSelfEvaluationSampleAttachService maintainPlanSelfEvaluationSampleAttachService;

	private String targetControllerName = "kin";
	private String targetActionName = "k02";
	/**
	 * 檔案上傳
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param fileData
	 *            MultipartFile
	 * @param fileDesc
	 * 
	 * @return 檔案上傳
	 */

	@RequestMapping(value = "/k02/createCheckList", method = RequestMethod.POST)
	public @ResponseBody String CreateCheckList(Locale locale, HttpServletRequest request,
				@RequestParam(value = "file", required = false) MultipartFile fileData) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanCheckListSampleAttach insert = new MaintainPlanCheckListSampleAttach();
			try {
				// 刪除舊檔案
				boolean delete = maintainPlanCheckListSampleAttachService.deleteAll();
				// 新增檔案
				if (fileData != null && !fileData.isEmpty() && delete) {
					// 檔案 byte array
					byte[] bytes = fileData.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("FileDesc", ""); // 檔案說明
					sn_json.put("FileName", fileData.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileData.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileData.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(
							WebCrypto.HashType.SHA256, fileData.toString())); // 檔案Hash
					// 新增
					insert = maintainPlanCheckListSampleAttachService.insert(getBaseMemberId(), sn_json.toString(), bytes);
				}
			} catch (Exception e) {
				insert = null;
			}

			if (insert != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "",	SystemLogVariable.Action.Create,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 檔案上傳
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param fileData
	 *            MultipartFile
	 * @param fileDesc
	 * 
	 * @return 檔案上傳
	 */

	@RequestMapping(value = "/k02/createQuestionnaire", method = RequestMethod.POST)
	public @ResponseBody String CreateQuestionnaire(Locale locale, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile fileData) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanQuestionnaireSampleAttach insert = new MaintainPlanQuestionnaireSampleAttach();
			try {
				// 刪除舊檔案
				boolean delete = maintainPlanQuestionnaireSampleAttachService.deleteAll();
				// 新增檔案
				if (fileData != null && !fileData.isEmpty() && delete) {
					// 檔案 byte array
					byte[] bytes = fileData.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("FileDesc", ""); // 檔案說明
					sn_json.put("FileName", fileData.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileData.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileData.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(
							WebCrypto.HashType.SHA256, fileData.toString())); // 檔案Hash
					// 新增
					insert = maintainPlanQuestionnaireSampleAttachService.insert(getBaseMemberId(), sn_json.toString(), bytes);
				}
			} catch (Exception e) {
				insert = null;
			}

			if (insert != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, "",	SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	/**
	 * 檔案上傳
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param fileData
	 *            MultipartFile
	 * @param fileDesc
	 * 
	 * @return 檔案上傳
	 */

	@RequestMapping(value = "/k02/createSelfEvaluation", method = RequestMethod.POST)
	public @ResponseBody String CreateSelfEvaluation(Locale locale, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile fileData) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			MaintainPlanSelfEvaluationSampleAttach insert = new MaintainPlanSelfEvaluationSampleAttach();
			try {
				// 刪除舊檔案
				boolean delete = maintainPlanSelfEvaluationSampleAttachService.deleteAll();
				// 新增檔案
				if (fileData != null && !fileData.isEmpty() && delete) {
					// 檔案 byte array
					byte[] bytes = fileData.getBytes();
					JSONObject sn_json = new JSONObject();
					sn_json.put("FileDesc", ""); // 檔案說明
					sn_json.put("FileName", fileData.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", fileData.getContentType()); // 檔案類型
					sn_json.put("FileSize", fileData.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(
							WebCrypto.HashType.SHA256, fileData.toString())); // 檔案Hash
					// 新增
					insert = maintainPlanSelfEvaluationSampleAttachService.insert(getBaseMemberId(), sn_json.toString(), bytes);
				}
			} catch (Exception e) {
				insert = null;
			}

			if (insert != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, "",	SystemLogVariable.Action.Create,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	@RequestMapping(value = "/k02/checkCheckListQuestionnaireSelfEvaluation", method = RequestMethod.POST)
	public @ResponseBody String CheckCheckListQuestionnaireSelfEvaluation(Locale locale,
			HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			
			try {
				if(maintainPlanCheckListSampleAttachService.get()!=null) {
					responseJson.put("IsCheckListExist", true);
				} else {
					responseJson.put("IsCheckListExist", false);
				}
				if(maintainPlanQuestionnaireSampleAttachService.get()!=null) {
					responseJson.put("IsQuestionnaireExist", true);
				} else {
					responseJson.put("IsQuestionnaireExist", false);
				}
				if(maintainPlanSelfEvaluationSampleAttachService.get()!=null) {
					responseJson.put("IsSelfEvaluationExist", true);
				} else {
					responseJson.put("IsSelfEvaluationExist", false);
				}
				responseJson.put("msg", WebMessage.getMessage("globalReadDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, "",	SystemLogVariable.Action.Read,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} catch (Exception e) {
				responseJson.put("msg", WebMessage.getMessage("globalReadDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, "",	SystemLogVariable.Action.Read,
						SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read,
					SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}
	
	
	
	
	
	/**
	 * 附件下載 CheckList
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k02/download/CheckList", method = RequestMethod.GET)
	public void DownloadCheckList(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanCheckListSampleAttach maintainPlanCheckListSampleAttach = maintainPlanCheckListSampleAttachService.get();
			if (maintainPlanCheckListSampleAttach == null) {
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanCheckListSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanCheckListSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,  getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail,  getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny,  getBaseIpAddress(), getBaseMemberAccount());
		}
	}
	
	/**
	 * 附件下載 Questionnaire
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse	
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k02/download/Questionnaire", method = RequestMethod.GET)
	public void DownloadQuestionnaire(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanQuestionnaireSampleAttach maintainPlanQuestionnaireSampleAttach = maintainPlanQuestionnaireSampleAttachService.get();
			if (maintainPlanQuestionnaireSampleAttach == null) {
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanQuestionnaireSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanQuestionnaireSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}
	/**
	 * 附件下載 SelfEvaluation
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse	
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/k02/download/SelfEvaluation", method = RequestMethod.GET)
	public void DownloadSelfEvaluation(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanSelfEvaluationSampleAttach maintainPlanSelfEvaluationSampleAttach = maintainPlanSelfEvaluationSampleAttachService.get();
			if (maintainPlanSelfEvaluationSampleAttach == null) {
				systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();				
				try {
					byte[] buffer = maintainPlanSelfEvaluationSampleAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(maintainPlanSelfEvaluationSampleAttach.getFileName()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}	

}