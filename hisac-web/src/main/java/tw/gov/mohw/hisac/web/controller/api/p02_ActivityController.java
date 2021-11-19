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
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.ActivityManagement;
import tw.gov.mohw.hisac.web.domain.ActivityManagementAttach;
import tw.gov.mohw.hisac.web.domain.ActivityManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.service.ActivityManagementService;
import tw.gov.mohw.hisac.web.service.CommentService;
import tw.gov.mohw.hisac.web.service.ActivityManagementAttachService;
import tw.gov.mohw.hisac.web.service.ActivityManagementPicService;

/**
 * 活動訊息控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p02_ActivityController extends BaseController {

	@Autowired
	private ActivityManagementService activityManagementService;

	@Autowired
	private ActivityManagementAttachService activityManagementAttachService;

	@Autowired
	private ActivityManagementPicService activityManagementPicService;

	
	
	@Autowired
	private CommentService commentService;

	private String targetControllerName = "pub";
	private String targetActionName = "p02";

	/**
	 * 取得活動訊息資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料
	 */
	@RequestMapping(value = "/p02/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("IsEnable", true);
			obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
			obj.put("Status", "4");
			obj.put("sort", "sort");
			json = obj.toString();
			List<ViewActivityManagementMember> activityManagements = activityManagementService.getSpList(json);
			if (activityManagements != null) {
				for (ViewActivityManagementMember activityManagement : activityManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", activityManagement.getId());
					sn_json.put("Date", WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd"));
					sn_json.put("StartDateTime", WebDatetime.toString(activityManagement.getStartDateTime(), "yyyy-MM-dd"));
					sn_json.put("EndDateTime", WebDatetime.toString(activityManagement.getEndDateTime(), "yyyy-MM-dd"));
					sn_json.put("Title", activityManagement.getTitle());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", activityManagementService.getSpListSize(json));
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
			model.addAttribute("json", listjson.toString());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得活動訊息資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return 活動訊息資料
	 */
	@RequestMapping(value = "/p02/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			ActivityManagement activityManagement = activityManagementService.get(id);

			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", activityManagement.getId());
			sn_json.put("PostType", activityManagement.getPostType());
			sn_json.put("PostDateTime", WebDatetime.toString(activityManagement.getPostDateTime(), "yyyy-MM-dd"));
			sn_json.put("Title", activityManagement.getTitle());
			sn_json.put("SourceName", activityManagement.getSourceName());
			sn_json.put("SourceLink", activityManagement.getSourceLink());
			sn_json.put("ContentType", activityManagement.getContentType());			
			sn_json.put("ExternalLink", activityManagement.getExternalLink());
			sn_json.put("IsBreakLine", activityManagement.getIsBreakLine());
			sn_json.put("StartDateTime", WebDatetime.toString(activityManagement.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(activityManagement.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", activityManagement.getIsEnable());
			sn_json.put("CreateId", activityManagement.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(activityManagement.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			sn_json.put("ModifyId", activityManagement.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(activityManagement.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
			sn_json.put("Status", activityManagement.getStatus());			
			sn_json.put("Content", activityManagement.getContent());
			sn_json.put("IsDetail", true);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			
			// Begin: Star Rating & Comments works
			sn_json.put("AvgStars", 1);
			sn_json.put("Comments", commentService.getByArticleComments((long) 2, Long.toString(id)));
			
			sn_json.put("IsEnableRating", true);
			
						
			// End: Star Rating & Comments works			

			sn_array.put(sn_json);			
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	 * @param activityManagementId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/p02/pic/download/{activityManagementId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("ActivityManagementId", activityManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!activityManagementPicService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				ActivityManagementPic activityManagementPic = activityManagementPicService.getById(id);
				try {
					byte[] buffer = activityManagementPic.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(activityManagementPic.getFileName(), StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(activityManagementPic.getFileType());
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} catch (IOException ex) {
					//ex.printStackTrace();
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	@RequestMapping(value = "/p02/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
			List<ViewActivityManagementAttachMember> activityManagementAttachs = activityManagementAttachService.getAllByActivityManagementId(activityManagementId);
			if (activityManagementAttachs != null) {
				for (ViewActivityManagementAttachMember activityManagementAttach : activityManagementAttachs) {
					long size = activityManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("ActivityManagementId", activityManagementAttach.getActivityManagementId());
					sn_json.put("Id", activityManagementAttach.getId());
					sn_json.put("FileName", activityManagementAttach.getFileName());
					sn_json.put("FileType", activityManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", activityManagementAttach.getFileHash());
					sn_json.put("FileDesc", activityManagementAttach.getFileDesc());
					sn_json.put("CreateId", activityManagementAttach.getCreateId());
					sn_json.put("CreateName", activityManagementAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(activityManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", activityManagementAttach.getModifyId());
					sn_json.put("ModifyName", activityManagementAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(activityManagementAttach.getModifyTime()));
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
	 * 新增星數評分資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			新增資料
	 * @return 是否新增成功
	 */
	
	
	/**
	 * 新增留言資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			新增資料
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/p02/comment/insert", method = RequestMethod.POST)
	public @ResponseBody String commentInsert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
		JSONObject responseJson = new JSONObject();
		boolean error = false;
		try{
			if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
				JSONObject obj = new JSONObject(json);
				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
				String comment = obj.isNull("Comment") == true ? null : obj.getString("Comment");
				boolean isHideName = obj.isNull("IsHideName") == true ? false : obj.getBoolean("IsHideName");				
				if (!comment.equals(null)){
					JSONObject star_json = new JSONObject();
					star_json.put("Type", (long) 2);
					star_json.put("ArticleId", Long.toString(id));
					star_json.put("Comment", comment);
					star_json.put("IsHideName", isHideName);						
					commentService.insert(getBaseMemberId(), star_json);
					}else{
						throw new Exception();
					}				
			}else{
				throw new Exception();
			}
		}catch(Exception err){
			error = true;
		}

		if(error){
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}else{
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		
		return responseJson.toString();
	}
	
	

	/**
	 * 刪除留言資料API
	 * 
	 * @param locale
	 * 			Locale
	 * @param request
	 * 			HttpServletRequest
	 * @param model
	 * 			Model
	 * @param json
	 * 			刪除資料
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/p02/comment/delete", method = RequestMethod.POST)
	public @ResponseBody String commentDelete(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		boolean error = false;
		try{
			if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
				JSONObject obj = new JSONObject(json);
				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");										
				commentService.delete(id);			
			}else{
				throw new Exception();
			}
		}catch(Exception err){
			error = true;
		}

		if(error){
			responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
			responseJson.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
		}else{
			responseJson.put("success", true);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
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
	 * @param activityManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/p02/attach/download/{activityManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("ActivityManagementId", activityManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!activityManagementService.isExist(activityManagementId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!activityManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				ActivityManagementAttach activityManagementAttach = activityManagementAttachService.getById(id);
				try {
					byte[] buffer = activityManagementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(activityManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}
}
