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



/**
 * 最新消息控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicQAController extends BaseController {

	

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
			
			listjson.put("total", 0);
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
	
	}
}