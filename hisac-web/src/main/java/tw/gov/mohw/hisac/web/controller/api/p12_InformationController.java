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
import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;
import tw.gov.mohw.hisac.web.domain.InformationManagementPic;
import tw.gov.mohw.hisac.web.service.InformationManagementAttachService;
import tw.gov.mohw.hisac.web.service.InformationManagementPicService;
import tw.gov.mohw.hisac.web.service.InformationManagementService;
import tw.gov.mohw.hisac.web.service.CommentService;

/**
 * 最新消息控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p12_InformationController extends BaseController {

	@Autowired
	private InformationManagementService informationManagementService;
	
	@Autowired
	private InformationManagementAttachService informationManagementAttachService;
	
	@Autowired
	private InformationManagementPicService informationManagementPicService;
	
	
	
	@Autowired
	private CommentService commentService;

	private String targetControllerName = "pub";
	private String targetActionName = "p12";

	/**
	 * 取得InformationManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return InformationManagement資料
	 */
	@RequestMapping(value = "/p12/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);			
			obj.put("IsEnable", true);			
			obj.put("Status", 4);
			json = obj.toString();
			List<InformationManagement> informationManagements = informationManagementService.getList(json);
			if (informationManagements != null) {
				for (InformationManagement informationManagement : informationManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", informationManagement.getId());					
					sn_json.put("Date", WebDatetime.toString(informationManagement.getPostDateTime(), "yyyy-MM-dd"));
					sn_json.put("Title", informationManagement.getTitle());
					
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", informationManagementService.getListSize(json)); 
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
	 * 取得InformationManagement資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return InformationManagement資料
	 */
	@RequestMapping(value = "/p12/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			InformationManagement informationManagement = informationManagementService.get(id);
			if (informationManagement.getIsEnable() == true && informationManagement.getPostDateTime().before(new Date())) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", informationManagement.getId());						
				sn_json.put("PostDateTime", WebDatetime.toString(informationManagement.getPostDateTime(), "yyyy-MM-dd"));
				sn_json.put("Title", informationManagement.getTitle());
				sn_json.put("SourceName", informationManagement.getSourceName());
				sn_json.put("SourceLink", informationManagement.getSourceLink());				
				sn_json.put("IsBreakLine", informationManagement.getIsBreakLine());				
				sn_json.put("IsEnable", informationManagement.getIsEnable());				
				sn_json.put("CreateId", informationManagement.getCreateId());
				sn_json.put("CreateTime", WebDatetime.toString(informationManagement.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				sn_json.put("ModifyId", informationManagement.getModifyId());
				sn_json.put("ModifyTime", WebDatetime.toString(informationManagement.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
				sn_json.put("Status", informationManagement.getStatus());				
				sn_json.put("Content", informationManagement.getContent());	
				
				// Begin: Star Rating & Comments works
				sn_json.put("AvgStars", 1);
				sn_json.put("Comments", commentService.getByArticleComments((long) 12, Long.toString(id)));
				sn_json.put("IsEnableRating", true);
				
								
				// End: Star Rating & Comments works


				
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());					

				sn_array.put(sn_json);
			} else {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
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
	 * @param informationManagementId
	 *            informationManagementId Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/p12/pic/download/{informationManagementId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("InformationManagementId", informationManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!informationManagementPicService.isExist(id)) {
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
	@RequestMapping(value = "/p12/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
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
					sn_json.put("Id", informationManagementAttach.getId());
					sn_json.put("FileName", informationManagementAttach.getFileName());					
					sn_json.put("FileSize", fileSize);					
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
//	@RequestMapping(value = "/p12/starRating/insert", method = RequestMethod.POST)
//	public @ResponseBody String starRatingInsert(Locale locale, HttpServletRequest request, Model model, @RequestBody String json){
//		JSONObject responseJson = new JSONObject();
//		boolean error = false;
//		try{
//			if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
//				JSONObject obj = new JSONObject(json);
//				long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//				long star = obj.isNull("RatingStar") == true ? 0 : obj.getLong("RatingStar");
//				if (!starRatingService.isExist((long) 12, Long.toString(id), getBaseMemberId())){
//					if ( 0 != star){
//						JSONObject star_json = new JSONObject();
//						star_json.put("Type", (long) 12);
//						star_json.put("ArticleId", Long.toString(id));
//						star_json.put("Star", star);
//						starRatingService.insert(getBaseMemberId(), star_json);
//					}else{
//						throw new Exception();
//					}
//				}else{
//					throw new Exception();
//				}
//			}else{
//				throw new Exception();
//			}
//		}catch(Exception err){
//			error = true;
//		}
//
//		if(error){
//			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
//			responseJson.put("success", false);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
//		}else{
//			responseJson.put("success", true);
//
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
//		
//		return responseJson.toString();
//	}
//	
	
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
	@RequestMapping(value = "/p12/comment/insert", method = RequestMethod.POST)
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
						star_json.put("Type", (long) 12);
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
	@RequestMapping(value = "/p12/comment/delete", method = RequestMethod.POST)
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
	 * @param informationManagementId
	 *            informationManagement Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/p12/attach/download/{informationManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("InformationManagementId", informationManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
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
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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
