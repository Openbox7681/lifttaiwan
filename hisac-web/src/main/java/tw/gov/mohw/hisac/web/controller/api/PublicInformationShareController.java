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

import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;

import tw.gov.mohw.hisac.web.service.MailService;
import tw.gov.mohw.hisac.web.service.ProcessLogService;

/**
 * 最新消息管理控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicInformationShareController extends BaseController {

	
	
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
		

			// 流程紀錄用 - 開始			
			Boolean isWriteProcessLog = obj.isNull("IsWriteProcessLog") == true ? false : obj.getBoolean("IsWriteProcessLog");

			
			// 流程紀錄用 - 結束

			
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
	}


}