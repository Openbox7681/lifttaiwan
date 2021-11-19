package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.CommonPost;
import tw.gov.mohw.hisac.web.service.CommonPostService;
import tw.gov.mohw.hisac.web.domain.CommonPostAttach;
import tw.gov.mohw.hisac.web.domain.CommonPostPic;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostAttachMember;
import tw.gov.mohw.hisac.web.service.CommonPostAttachService;
import tw.gov.mohw.hisac.web.service.CommonPostPicService;

/**
 * 認識本系統控制器
 */
@Controller
@RequestMapping(value = "/public/api", produces = "application/json; charset=utf-8")
public class PublicAboutController extends BaseController {

	@Autowired
	private CommonPostService commonPostService;

	@Autowired
	private CommonPostAttachService commonPostAttachService;

	@Autowired
	private CommonPostPicService commonPostPicService;

	/**
	 * 取得文章資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return 文章資料
	 */
	@RequestMapping(value = "/about/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		// if (funcRoleService.getUserFuncNameRole(FUNC_NAME, getUserRoleId())
		// .getFuncSel().equals("Y") || WebConfig.DEBUG_MODE == true) {
		CommonPost commonPost = commonPostService.findForPub((long) 1);
		JSONObject sn_json = new JSONObject();
		sn_json.put("Id", commonPost.getId());
		sn_json.put("Title", commonPost.getTitle());
		sn_json.put("Content", commonPost.getContent().replace("./api/p07/pic/download/", "./public/api/about/pic/download/"));
		sn_json.put("IsBreakLine", commonPost.getIsBreakLine());
		sn_json.put("StartDateTime", WebDatetime.toString(commonPost.getStartDateTime()));
		sn_json.put("EndDateTime", WebDatetime.toString(commonPost.getEndDateTime()));
		sn_json.put("IsEnable", commonPost.getIsEnable());
		// sn_json.put("CreateId", CommonPost.getCreateId());
		// sn_json.put("CreateTime", CommonPost.getCreateTime());
		// sn_json.put("ModifyId", CommonPost.getModifyId());
		// sn_json.put("ModifyTime", CommonPost.getModifyTime());
		sn_array.put(sn_json);
		// }
		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 取得文章附件資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 文章附件資料
	 */
	@RequestMapping(value = "/about/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject obj = new JSONObject(json);

		// 文章編號
		long commonPostId = obj.isNull("CommonPostId") == true ? 0 : obj.getLong("CommonPostId");

		JSONObject listjson = new JSONObject();
		List<ViewCommonPostAttachMember> commonPostAttachs = commonPostAttachService.getAllByCommonPostId(commonPostId);
		listjson.put("total", commonPostAttachService.getListSize(json));
		// 另一種寫法,但是是小寫
		// listjson.put("datatable", CommonPosts);

		// 準備 JSON 陣列
		JSONArray sn_array = new JSONArray();
		if (commonPostAttachs != null) {
			long size;
			String fileSize = "";
			for (ViewCommonPostAttachMember commonPostAttach : commonPostAttachs) {

				// 檔案大小格式化顯示
				size = commonPostAttach.getFileSize();
				if (size <= 0)
					return "0";
				final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
				int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
				fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

				// 準備各筆資料 JSON
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id", commonPostAttach.getId()); // 檔案Id
				sn_json.put("FileName", commonPostAttach.getFileName()); // 檔案名稱
				sn_json.put("FileType", commonPostAttach.getFileType()); // 檔案類型
				sn_json.put("FileSize", fileSize); // 檔案大小
				sn_json.put("FileHash", commonPostAttach.getFileHash()); // 檔案驗證碼(SHA256)
				sn_json.put("FileDesc", commonPostAttach.getFileDesc()); // 檔案說明
				sn_json.put("CreateId", commonPostAttach.getCreateId()); // 新增者Id
				sn_json.put("CreateName", commonPostAttach.getCreateName()); // 新增者姓名
				sn_json.put("CreateTime", WebDatetime.toString(commonPostAttach.getCreateTime())); // 新增時間
				sn_json.put("ModifyId", commonPostAttach.getModifyId()); // 修改者Id
				sn_json.put("ModifyName", commonPostAttach.getModifyName()); // 修改者姓名
				sn_json.put("ModifyTime", WebDatetime.toString(commonPostAttach.getModifyTime())); // 修改時間

				// 放入 JSON 陣列
				sn_array.put(sn_json);
			}
		}
		// 最終傳回 JSON 為 datatable: ....
		listjson.put("datatable", sn_array);

		systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		model.addAttribute("json", listjson.toString());
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
	 * @param commomPostId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/about/pic/download/{commomPostId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long commomPostId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("CommomPostId", commomPostId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (!commonPostService.isExist(commomPostId)) {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else if (!commonPostPicService.isExist(id)) {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else {
			response.reset();
			CommonPostPic commonPostPic = commonPostPicService.getById(id);
			try {
				byte[] buffer = commonPostPic.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(commonPostPic.getFileName(), StandardCharsets.UTF_8.toString()));
				response.addHeader("Content-Length", "" + buffer.length);
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType(commonPostPic.getFileType());
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
	 * 附件下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param commomPostId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/about/attach/download/{commomPostId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long commomPostId, @PathVariable Long id) {

		// 準備要新增的檔案相關欄位為 json format
		JSONObject sn_json = new JSONObject();
		sn_json.put("CommonPostId", commomPostId); // 文章Id
		sn_json.put("id", id); // 附件Id
		String json = sn_json.toString();
		JSONObject response_json = new JSONObject();
		if (!commonPostService.isExist(commomPostId)) {
			response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
			response_json.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else if (!commonPostAttachService.isExist(id)) {
			response_json.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
			response_json.put("success", false);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());

			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				//ex.printStackTrace();
			}
		} else {
			response.reset();
			CommonPostAttach commonPostAttach = commonPostAttachService.getById(id);
			try {
				byte[] buffer = commonPostAttach.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(commonPostAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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