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
import tw.gov.mohw.hisac.web.domain.NewsManagement;
import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;
import tw.gov.mohw.hisac.web.domain.NewsManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;
import tw.gov.mohw.hisac.web.service.NewsManagementService;
import tw.gov.mohw.hisac.web.service.NewsManagementAttachService;
import tw.gov.mohw.hisac.web.service.NewsManagementPicService;

/**
 * 最新消息控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicNewsController extends BaseController {

	@Autowired
	private NewsManagementService newsManagementService;

	@Autowired
	private NewsManagementAttachService newsManagementAttachService;

	@Autowired
	private NewsManagementPicService newsManagementPicService;

	/**
	 * 取得NewsManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return NewsManagement資料
	 */
	@RequestMapping(value = "/news/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("PostType", "1");
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
		obj.put("Status", "4");
		obj.put("sort", "sort");
		json = obj.toString();
		List<ViewNewsManagementMember> newsManagements = newsManagementService.getSpList(json); // 改用
																								// store
																								// procedure
		if (newsManagements != null) {
			for (ViewNewsManagementMember newsManagement : newsManagements) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", newsManagement.getId());
				sn_json.put("Group", newsManagement.getNewsManagementGroupName());
				sn_json.put("Date", WebDatetime.toString(newsManagement.getPostDateTime(), "yyyy-MM-dd"));
				sn_json.put("Title", newsManagement.getTitle());
				sn_array.put(sn_json);
			}
		}
		listjson.put("total", newsManagementService.getSpListSize(json)); // 改用
																			// store
																			// procedure
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得NewsManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return NewsManagement資料
	 */
	@RequestMapping(value = "/news/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		NewsManagement newsManagement = newsManagementService.get(id);
		if (newsManagement.getIsEnable() == true && newsManagement.getIsPublic() == true && newsManagement.getStatus() == 4) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", newsManagement.getId());
			sn_json.put("PostType", newsManagement.getPostType());
			sn_json.put("NewsManagementGroupId", newsManagement.getNewsManagementGroupId());
			sn_json.put("PostDateTime", WebDatetime.toString(newsManagement.getPostDateTime(), "yyyy-MM-dd"));
			sn_json.put("Title", newsManagement.getTitle());
			sn_json.put("SourceName", newsManagement.getSourceName());
			sn_json.put("SourceLink", newsManagement.getSourceLink());
			sn_json.put("ContentType", newsManagement.getContentType());
			sn_json.put("Content", newsManagement.getContent().replace("./api/p01/pic/download/", "./public/api/news/pic/download/"));
			sn_json.put("ExternalLink", newsManagement.getExternalLink());
			sn_json.put("IsBreakLine", newsManagement.getIsBreakLine());
			sn_json.put("StartDateTime", WebDatetime.toString(newsManagement.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(newsManagement.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", newsManagement.getIsEnable());
			sn_json.put("CreateId", newsManagement.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(newsManagement.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			sn_json.put("ModifyId", newsManagement.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(newsManagement.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
			sn_json.put("Status", newsManagement.getStatus());
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
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
	 * @param newsManagementId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/news/pic/download/{newsManagementId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long newsManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("NewsManagementId", newsManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (!newsManagementPicService.isExist(id)) {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else {
			response.reset();
			NewsManagementPic newsManagementPic = newsManagementPicService.getById(id);
			try {
				byte[] buffer = newsManagementPic.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(newsManagementPic.getFileName(), StandardCharsets.UTF_8.toString()));
				response.addHeader("Content-Length", "" + buffer.length);
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType(newsManagementPic.getFileType());
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
	@RequestMapping(value = "/news/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		long newsManagementId = obj.isNull("NewsManagementId") == true ? 0 : obj.getLong("NewsManagementId");
		List<ViewNewsManagementAttachMember> newsManagementAttachs = newsManagementAttachService.getAllByNewsManagementId(newsManagementId);
		if (newsManagementAttachs != null) {
			for (ViewNewsManagementAttachMember newsManagementAttach : newsManagementAttachs) {
				long size = newsManagementAttach.getFileSize();
				if (size <= 0)
					return "0";
				final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
				int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
				String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
				JSONObject sn_json = new JSONObject();
				sn_json.put("NewsManagementId", newsManagementAttach.getNewsManagementId());
				sn_json.put("Id", newsManagementAttach.getId());
				sn_json.put("FileName", newsManagementAttach.getFileName());
				sn_json.put("FileType", newsManagementAttach.getFileType());
				sn_json.put("FileSize", fileSize);
				sn_json.put("FileHash", newsManagementAttach.getFileHash());
				sn_json.put("FileDesc", newsManagementAttach.getFileDesc());
				sn_json.put("CreateId", newsManagementAttach.getCreateId());
				sn_json.put("CreateName", newsManagementAttach.getCreateName());
				sn_json.put("CreateTime", WebDatetime.toString(newsManagementAttach.getCreateTime()));
				sn_json.put("ModifyId", newsManagementAttach.getModifyId());
				sn_json.put("ModifyName", newsManagementAttach.getModifyName());
				sn_json.put("ModifyTime", WebDatetime.toString(newsManagementAttach.getModifyTime()));
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
	 * @param newsManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/news/attach/download/{newsManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long newsManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("NewsManagementId", newsManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (!newsManagementService.isExist(newsManagementId)) {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else if (!newsManagementAttachService.isExist(id)) {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else {
			response.reset();
			NewsManagementAttach newsManagementAttach = newsManagementAttachService.getById(id);
			try {
				byte[] buffer = newsManagementAttach.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(newsManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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
