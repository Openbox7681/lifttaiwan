package tw.gov.mohw.hisac.web.controller.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import tw.gov.mohw.hisac.web.service.MaintainPlanCheckListSampleAttachService;
import tw.gov.mohw.hisac.web.service.MaintainPlanQuestionnaireSampleAttachService;

/**
 * 中繼站匯入控制器
 */
@Controller
@RequestMapping(value = "/mtp/api", produces = "application/json; charset=utf-8")
public class m04_uploadFileontroller extends BaseController {

	
	@Autowired
	private MaintainPlanCheckListSampleAttachService maintainPlanCheckListSampleAttachService;
	@Autowired
	private MaintainPlanQuestionnaireSampleAttachService maintainPlanQuestionnaireSampleAttachService;
	
	
	private String targetControllerName = "mtp";
	private String targetActionName = "m04";

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
	
	@RequestMapping(value = "/m04/createCheckList", method = RequestMethod.POST)
	public @ResponseBody String CreateCheckList(Locale locale, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile fileData) {	
		JSONObject responseJson = new JSONObject();		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
		MaintainPlanCheckListSampleAttach insert = new MaintainPlanCheckListSampleAttach();
		try {	
			//刪除舊檔案
			boolean delete = maintainPlanCheckListSampleAttachService.deleteAll();						
			
			//新增檔案
			if (fileData != null && !fileData.isEmpty() && delete) {
				// 檔案 byte array
				byte[] bytes = fileData.getBytes();
				JSONObject sn_json = new JSONObject();				
				sn_json.put("FileDesc", ""); // 檔案說明
				sn_json.put("FileName", fileData.getOriginalFilename()); // 檔案名稱
				sn_json.put("FileType", fileData.getContentType()); // 檔案類型
				sn_json.put("FileSize", fileData.getSize()); // 檔案大小
				sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileData.toString())); // 檔案Hash
				String json = sn_json.toString();

				// 新增
				insert = maintainPlanCheckListSampleAttachService.insert(getBaseMemberId(), json, bytes);
				}								
			}catch (Exception e) {				
				insert = null;					
		}			
		
		if (insert != null) {										
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
	} else {
		systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	
	@RequestMapping(value = "/m04/createQuestionnaire", method = RequestMethod.POST)
	public @ResponseBody String CreateQuestionnaire(Locale locale, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile fileData) {	
		JSONObject responseJson = new JSONObject();		
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			MaintainPlanQuestionnaireSampleAttach insert = new MaintainPlanQuestionnaireSampleAttach();
		try {	
			//刪除舊檔案
			boolean delete = maintainPlanQuestionnaireSampleAttachService.deleteAll();						
			
			//新增檔案
			if (fileData != null && !fileData.isEmpty() && delete) {
				// 檔案 byte array
				byte[] bytes = fileData.getBytes();
				JSONObject sn_json = new JSONObject();				
				sn_json.put("FileDesc", ""); // 檔案說明
				sn_json.put("FileName", fileData.getOriginalFilename()); // 檔案名稱
				sn_json.put("FileType", fileData.getContentType()); // 檔案類型
				sn_json.put("FileSize", fileData.getSize()); // 檔案大小
				sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, fileData.toString())); // 檔案Hash
				String json = sn_json.toString();

				// 新增
				insert = maintainPlanQuestionnaireSampleAttachService.insert(getBaseMemberId(), json, bytes);
				}								
			}catch (Exception e) {				
				insert = null;					
		}			
		
		if (insert != null) {										
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
			responseJson.put("success", true);
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
	} else {
		systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
	}
	return responseJson.toString();
		
	}
}