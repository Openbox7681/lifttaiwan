package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.QAManagement;
import tw.gov.mohw.hisac.web.domain.QAManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewQAManagementGroup;
import tw.gov.mohw.hisac.web.service.QAManagementAttachService;
import tw.gov.mohw.hisac.web.service.QAManagementService;


/**
 * 最新消息控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicQAController extends BaseController {

	@Autowired
	private QAManagementService qaManagementService;
	@Autowired
	private QAManagementAttachService qaManagementAttachService;	

	/**
	 * 取得常見問題資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 常見問題資料
	 */
	@RequestMapping(value = "/qa/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();		
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("IsEnable", true);
			obj.put("IsPublic", true);
			List<ViewQAManagementGroup> qaManagements = qaManagementService.getList(obj.toString());
			if (qaManagements != null) {
				for (ViewQAManagementGroup qaManagement : qaManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", qaManagement.getId());
					sn_json.put("QAMgName", qaManagement.getQAMgName());
					sn_json.put("QName", qaManagement.getQName());
					sn_json.put("AName", qaManagement.getAName());
					// sn_json.put("IsEnable", qaManagement.getIsEnable());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", qaManagementService.getListSize(obj.toString()));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得常見問題資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return 常見問題資料
	 */
	@RequestMapping(value = "/qa/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		JSONObject sn_json = new JSONObject();
		JSONArray sn_array = new JSONArray();		
			QAManagement qaManagement = qaManagementService.getDataById(id);
			if (qaManagement.getIsPublic()) {
				sn_json.put("Id", qaManagement.getId());
				sn_json.put("QAManagementGroupId", qaManagement.getQAMgId());
				sn_json.put("QName", qaManagement.getQName());
				sn_json.put("AName", qaManagement.getAName());
				sn_json.put("IsEnable", qaManagement.getIsEnable());
			}

			sn_array.put(sn_json);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());		
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	// /**
	// * 取得NewsManagementGroup資料API
	// *
	// * @param locale
	// * Locale
	// * @param request
	// * HttpServletRequest
	// * @param json
	// * 查詢條件
	// * @return 子系統資料
	// */
	// @RequestMapping(value = "/p03/group/query", method = RequestMethod.POST)
	// public String GroupQuery(Locale locale, HttpServletRequest request, Model
	// model, @RequestBody String json) {
	//
	//
	// JSONObject listjson = new JSONObject();
	// List<ViewNewsManagementGroupMember> newsManagementGroups =
	// QAManagementGroupService.getList(json);
	//
	// JSONArray sn_array = new JSONArray();
	// if (newsManagementGroups != null)
	// for (ViewNewsManagementGroupMember newsManagementGroup :
	// newsManagementGroups) {
	// JSONObject sn_json = new JSONObject();
	// sn_json.put("Id", newsManagementGroup.getId());
	// sn_json.put("Name", newsManagementGroup.getName());
	// sn_json.put("IsEnable", newsManagementGroup.getIsEnable());
	// sn_json.put("CreateId", newsManagementGroup.getCreateId());
	// sn_json.put("CreateTime",
	// WebDatetime.toString(newsManagementGroup.getCreateTime()));
	// sn_json.put("ModifyId", newsManagementGroup.getModifyId());
	// sn_json.put("ModifyTime",
	// WebDatetime.toString(newsManagementGroup.getModifyTime()));
	// sn_array.put(sn_json);
	// }
	// listjson.put("total", QAManagementGroupService.getListSize(json));
	// listjson.put("datatable", sn_array);
	//
	//
	// systemLogService.insert(baseControllerName, baseActionName, json,
	// SystemLogVariable.Action.Read, SystemLogVariable.Status.Success,
	// getBaseIpAddress(), getBaseMemberAccount());
	// model.addAttribute("json", listjson.toString());
	// return "msg";
	// }

	
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
	@RequestMapping(value = "/qa/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();		
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long qaManagementId = obj.isNull("QAManagementId") == true ? 0 : obj.getLong("QAManagementId");
			List<QAManagementAttach> qaManagementAttachs = qaManagementAttachService.getAllByQAManagementId(qaManagementId);
			if (qaManagementAttachs != null) {
				for (QAManagementAttach qaManagementAttach : qaManagementAttachs) {
					long size = qaManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("QAManagementId", qaManagementAttach.getManagementId());
					sn_json.put("Id", qaManagementAttach.getId());
					sn_json.put("FileName", qaManagementAttach.getFileName());
					sn_json.put("FileType", qaManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", qaManagementAttach.getFileHash());
					sn_json.put("FileDesc", qaManagementAttach.getFileDesc());
					sn_json.put("CreateId", qaManagementAttach.getCreateId());					
					sn_json.put("CreateTime", WebDatetime.toString(qaManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", qaManagementAttach.getModifyId());					
					sn_json.put("ModifyTime", WebDatetime.toString(qaManagementAttach.getModifyTime()));
					sn_array.put(sn_json);
				}
				listjson.put("datatable", sn_array);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			}		
		model.addAttribute("json", listjson.toString());
		return "msg";
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
	 * @param activityManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/qa/attach/download/{qaManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long qaManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("QAManagementId", qaManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();		
			if (!qaManagementService.isExist(qaManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!qaManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				QAManagementAttach qaManagementAttach = qaManagementAttachService.getById(id);
				try {
					byte[] buffer = qaManagementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(qaManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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