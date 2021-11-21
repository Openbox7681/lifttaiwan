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

import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;


/**
 * 活動訊息控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicActivityController extends BaseController {

	

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
	@RequestMapping(value = "/activity/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		obj.put("IsEnable", true);
		obj.put("IsPublic", true);
		obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
		obj.put("Status", "4");
		obj.put("sort", "sort");
		json = obj.toString();
		
		listjson.put("total", 0);
		listjson.put("datatable", sn_array);
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
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
	@RequestMapping(value = "/activity/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		
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
	@RequestMapping(value = "/activity/pic/download/{activityManagementId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("ActivityManagementId", activityManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		
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
	@RequestMapping(value = "/activity/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject obj = new JSONObject(json);
		long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
		
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
	@RequestMapping(value = "/activity/attach/download/{activityManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long activityManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("ActivityManagementId", activityManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		
	}
}
