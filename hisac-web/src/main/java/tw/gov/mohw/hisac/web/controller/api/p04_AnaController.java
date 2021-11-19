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
import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;
import tw.gov.mohw.hisac.web.service.AnaManagementService;
import tw.gov.mohw.hisac.web.service.CommentService;
import tw.gov.mohw.hisac.web.service.AnaManagementAttachService;

/**
 * 資安訊息情報控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p04_AnaController extends BaseController {

	@Autowired
	private AnaManagementService anaManagementService;

	@Autowired
	private AnaManagementAttachService anaManagementAttachService;

	
	
	@Autowired
	private CommentService commentService;

	private String targetControllerName = "pub";
	private String targetActionName = "p04";

	/**
	 * 取得資安訊息情報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            json
	 * @return 資安訊息情報資料
	 */
	@RequestMapping(value = "/p04/query", method = RequestMethod.POST)
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
			List<ViewAnaManagementMember> anaManagements = anaManagementService.getSpList(json); // 改用
																									// store
																									// procedure
			if (anaManagements != null) {
				for (ViewAnaManagementMember anaManagement : anaManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", anaManagement.getId());
					sn_json.put("Date", WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Title", anaManagement.getIncidentTitle());
					sn_json.put("EventTypeName", anaManagement.getEventTypeName());
					sn_json.put("IsMedical", anaManagement.getIsMedical());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", anaManagementService.getSpListSize(json)); // 改用
																				// store
																				// procedure
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
	 * 取得資安訊息情報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return 資安訊息情報資料
	 */
	@RequestMapping(value = "/p04/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			ViewAnaManagementMember anaManagement = anaManagementService.getByDetail(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", anaManagement.getId());
			sn_json.put("IncidentId", anaManagement.getIncidentId());
			sn_json.put("IncidentTitle", anaManagement.getIncidentTitle());
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(anaManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("IncidentReportedTime", WebDatetime.toString(anaManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
			sn_json.put("EventTypeName", anaManagement.getEventTypeName());
			sn_json.put("ReporterName", anaManagement.getReporterName());
			sn_json.put("ResponderPartyName", anaManagement.getResponderPartyName());
			sn_json.put("ResponderContactNumbers", anaManagement.getResponderContactNumbers());
			sn_json.put("ResponderElectronicAddressIdentifiers", anaManagement.getResponderElectronicAddressIdentifiers());
			sn_json.put("ImpactQualification", anaManagement.getImpactQualification());
			sn_json.put("CoaDescription", anaManagement.getCoaDescription());
			sn_json.put("Confidence", anaManagement.getConfidence());
			sn_json.put("Reference", anaManagement.getReference());
			sn_json.put("AffectedSoftwareDescription", anaManagement.getAffectedSoftwareDescription());
			sn_json.put("Status", anaManagement.getStatus());
			if (!(getBaseCILevel().equals("0") && getBaseOrgType().equals("3"))) {
				sn_json.put("Description", anaManagement.getDescription());
				sn_json.put("IsDetail", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				sn_json.put("Description", WebMessage.getMessage("orgCILevelNotAllow", null, locale));
				sn_json.put("IsDetail", false);
			}

			// Begin: Star Rating & Comments works
			sn_json.put("AvgStars", 1);
			sn_json.put("Comments", commentService.getByArticleComments((long) 4, Long.toString(id)));
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
	@RequestMapping(value = "/p04/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long anaManagementId = obj.isNull("AnaManagementId") == true ? 0 : obj.getLong("AnaManagementId");
			List<ViewAnaManagementAttachMember> anaManagementAttachs = anaManagementAttachService.getAllByAnaManagementId(anaManagementId);
			if (anaManagementAttachs != null) {
				for (ViewAnaManagementAttachMember anaManagementAttach : anaManagementAttachs) {
					long size = anaManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("AnaManagementId", anaManagementAttach.getAnaManagementId());
					sn_json.put("Id", anaManagementAttach.getId());
					sn_json.put("FileName", anaManagementAttach.getFileName());
					sn_json.put("FileType", anaManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", anaManagementAttach.getFileHash());
					sn_json.put("FileDesc", anaManagementAttach.getFileDesc());
					sn_json.put("CreateId", anaManagementAttach.getCreateId());
					sn_json.put("CreateName", anaManagementAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(anaManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", anaManagementAttach.getModifyId());
					sn_json.put("ModifyName", anaManagementAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(anaManagementAttach.getModifyTime()));
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
	@RequestMapping(value = "/p04/comment/insert", method = RequestMethod.POST)
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
					star_json.put("Type", (long) 4);
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
	@RequestMapping(value = "/p04/comment/delete", method = RequestMethod.POST)
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
	 * @param anaManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/p04/attach/download/{anaManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long anaManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("AnaManagementId", anaManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!anaManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				AnaManagementAttach anaManagementAttach = anaManagementAttachService.getById(id);
				try {
					byte[] buffer = anaManagementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(anaManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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
