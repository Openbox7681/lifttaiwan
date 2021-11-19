package tw.gov.mohw.hisac.web.controller.api;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
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

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.CommonPost;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostMember;
import tw.gov.mohw.hisac.web.service.CommonPostService;
import tw.gov.mohw.hisac.web.domain.CommonPostAttach;
import tw.gov.mohw.hisac.web.domain.CommonPostPic;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostPicMember;
import tw.gov.mohw.hisac.web.service.CommonPostAttachService;
import tw.gov.mohw.hisac.web.service.CommonPostPicService;

/**
 * 何謂ISAC維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s36_CommonPostController extends BaseController {

	@Autowired
	private CommonPostService commonPostService;
	@Autowired
	private CommonPostAttachService commonPostAttachService;
	@Autowired
	private CommonPostPicService commonPostPicService;

	private String targetControllerName = "sys";
	private String targetActionName = "s36";

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
	 *            查詢條件
	 * @return 文章資料
	 */
	@RequestMapping(value = "/s36/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject obj = new JSONObject(json);
		// 指定 PostType 為 3: [何謂ISAC]
		obj.put("PostType", 3);
		json = obj.toString();
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			listjson.put("total", commonPostService.getListSize(json));
			List<ViewCommonPostMember> commonPosts = commonPostService.getList(json);
			if (commonPosts != null)
				for (ViewCommonPostMember commonPost : commonPosts) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", commonPost.getId());
					sn_json.put("Title", commonPost.getTitle());
					sn_json.put("Content", commonPost.getContent());
					sn_json.put("IsBreakLine", commonPost.getIsBreakLine());
					sn_json.put("StartDateTime", WebDatetime.toString(commonPost.getStartDateTime(), "yyyy-MM-dd"));
					sn_json.put("EndDateTime", WebDatetime.toString(commonPost.getEndDateTime(), "yyyy-MM-dd"));
					sn_json.put("ModifyTime", WebDatetime.toString(commonPost.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
					sn_json.put("IsEnable", commonPost.getIsEnable());
					// sn_json.put("CreateId", CommonPost.getCreateId());
					// sn_json.put("CreateName", CommonPost.getCreateName());
					// sn_json.put("CreateTime",
					// WebDatetime.toString(CommonPost.getCreateTime()));
					// sn_json.put("ModifyId", CommonPost.getModifyId());
					// sn_json.put("ModifyName", CommonPost.getModifyName());
					// sn_json.put("ModifyTime",
					// WebDatetime.toString(CommonPost.getModifyTime()));
					sn_array.put(sn_json);
				}
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

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
	@RequestMapping(value = "/s36/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			CommonPost commonPost = commonPostService.getById(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", commonPost.getId());
			sn_json.put("Title", commonPost.getTitle());
			sn_json.put("Content", commonPost.getContent());
			sn_json.put("IsBreakLine", commonPost.getIsBreakLine());
			sn_json.put("StartDateTime", WebDatetime.toString(commonPost.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(commonPost.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", commonPost.getIsEnable());
			// sn_json.put("CreateId", CommonPost.getCreateId());
			// sn_json.put("CreateTime", CommonPost.getCreateTime());
			// sn_json.put("ModifyId", CommonPost.getModifyId());
			// sn_json.put("ModifyTime", CommonPost.getModifyTime());
			sn_array.put(sn_json);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增文章資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            文章
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s36/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		// 指定 PostType 為 3: [何謂ISAC]
		JSONObject obj = new JSONObject(json);
		obj.put("PostType", 3);
		json = obj.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			CommonPost commonPost = commonPostService.insert(getBaseMemberId(), json);
			if (commonPost != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 更新文章資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            文章
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s36/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");

			if (!commonPostService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				CommonPost commonPost = commonPostService.update(getBaseMemberId(), json);
				if (commonPost != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

	/**
	 * 刪除文章資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s36/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!commonPostService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				// 刪除引用圖檔
				try {
					List<ViewCommonPostPicMember> commonPostPics = commonPostPicService.getAllByCommonPostId(id);
					if (commonPostPics != null) {
						for (ViewCommonPostPicMember commonPostPic : commonPostPics) {
							commonPostPicService.delete(commonPostPic.getId());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
				// 刪除附件
				try {
					List<ViewCommonPostAttachMember> commonPostAttachs = commonPostAttachService.getAllByCommonPostId(id);
					if (commonPostAttachs != null) {
						for (ViewCommonPostAttachMember commonPostAttach : commonPostAttachs) {
							commonPostAttachService.delete(commonPostAttach.getId());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if (commonPostService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
	@RequestMapping(value = "/s36/pic/query", method = RequestMethod.POST)
	public String QueryPic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			// 文章編號
			long commonPostId = obj.isNull("CommonPostId") == true ? 0 : obj.getLong("CommonPostId");
			List<ViewCommonPostPicMember> commonPostPics = commonPostPicService.getAllByCommonPostId(commonPostId);
			listjson.put("total", commonPostAttachService.getListSize(json));

			if (commonPostPics != null) {
				for (ViewCommonPostPicMember commonPostPic : commonPostPics) {
					// 檔案大小格式化顯示
					long size = commonPostPic.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					String imageLink = "<img src=\"./api/p09/pic/download/" + commonPostId + "/" + commonPostPic.getId() + "\" title=\"" + commonPostPic.getFileDesc() + "\" width=\"" + commonPostPic.getImageWidth() + "\">";
					// 準備各筆資料 JSON
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", commonPostPic.getId()); // 檔案Id
					sn_json.put("FileName", commonPostPic.getFileName()); // 檔案名稱
					sn_json.put("FileType", commonPostPic.getFileType()); // 檔案類型
					sn_json.put("FileSize", fileSize); // 檔案大小
					sn_json.put("ImageWidth", commonPostPic.getImageWidth()); // 圖檔寬度
					sn_json.put("ImageHeight", commonPostPic.getImageHeight()); // 圖檔高度
					sn_json.put("ImageLink", imageLink); // 引用連結
					sn_json.put("FileDesc", commonPostPic.getFileDesc()); // 檔案說明
					sn_json.put("CreateId", commonPostPic.getCreateId()); // 新增者Id
					sn_json.put("CreateName", commonPostPic.getCreateName()); // 新增者姓名
					sn_json.put("CreateTime", WebDatetime.toString(commonPostPic.getCreateTime())); // 新增時間
					sn_json.put("ModifyId", commonPostPic.getModifyId()); // 修改者Id
					sn_json.put("ModifyName", commonPostPic.getModifyName()); // 修改者姓名
					sn_json.put("ModifyTime", WebDatetime.toString(commonPostPic.getModifyTime())); // 修改時間

					// 放入 JSON 陣列
					sn_array.put(sn_json);
				}
				// 最終傳回 JSON 為 datatable: ....
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
	 * 上傳圖片
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            文章Id
	 * @param fileDesc
	 *            檔案說明
	 * @param model
	 *            Model
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s36/pic/upload", method = RequestMethod.POST)
	public @ResponseBody String UploadPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {

		// 回傳 JSON
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// 準備要新增的檔案相關欄位為 json format
			JSONObject sn_json = new JSONObject();
			sn_json.put("CommonPostId", id); // 文章Id
			sn_json.put("FileDesc", fileDesc); // 檔案說明
			String json = sn_json.toString();
			// 檔案不為空
			if (!file.isEmpty()) {
				try {
					// 檔案 byte array
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", file.getContentType()); // 檔案類型
					sn_json.put("FileSize", file.getSize()); // 檔案大小
					// 圖檔寬高
					Image image = ImageIO.read(file.getInputStream());
					int imageWidth = image.getWidth(null);
					int imageHeight = image.getHeight(null);
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString())); // 檔案Hash
					sn_json.put("ImageWidth", imageWidth);
					sn_json.put("ImageHeight", imageHeight);
					json = sn_json.toString();
					// 確認文章編號存在
					CommonPost commonPost = commonPostService.getById(id);
					if (commonPost != null) {
						// 新增
						CommonPostPic entity = commonPostPicService.insert(getBaseMemberId(), json, bytes);
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (Exception e) {
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "NewsManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		// model.addAttribute("json", responseJson.toString());
		return responseJson.toString();
	}

	/**
	 * 刪除圖片資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s36/pic/delete", method = RequestMethod.POST)
	public @ResponseBody String DeletePicById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			// long commonPostId = obj.isNull("CommonPostId") == true ? 0 :
			// obj.getLong("CommonPostId");

			// if (!commonPostService.isExist(commonPostId)) {
			// response_json.put("msg",
			// WebMessage.getMessage("globalDataNotExist", null, locale));
			// response_json.put("success", false);
			//
			// systemLogService.insert(baseControllerName, baseActionName, json,
			// SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail,
			// getBaseIpAddress(), getBaseMemberAccount());
			// } else
			//
			if (!commonPostPicService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (commonPostPicService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
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
	 * @param commomPostId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/s36/pic/download/{commomPostId}/{id}", method = RequestMethod.GET)
	public void ExportPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long commomPostId, @PathVariable Long id) {

		// 準備要新增的檔案相關欄位為 json format
		JSONObject sn_json = new JSONObject();
		sn_json.put("CommonPostId", commomPostId); // 文章Id
		sn_json.put("id", id); // 附件Id
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// JSONObject response_json = new JSONObject();
			if (!commonPostPicService.isExist(id)) {
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
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(commonPostPic.getFileName()));
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

		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

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
	@RequestMapping(value = "/s36/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long commonPostId = obj.isNull("CommonPostId") == true ? 0 : obj.getLong("CommonPostId");
			List<ViewCommonPostAttachMember> commonPostAttachs = commonPostAttachService.getAllByCommonPostId(commonPostId);
			if (commonPostAttachs != null) {
				for (ViewCommonPostAttachMember commonPostAttach : commonPostAttachs) {
					long size = commonPostAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

					// 準備各筆資料 JSON
					JSONObject sn_json = new JSONObject();
					sn_json.put("CommonPostId", commonPostAttach.getCommonPostId());
					sn_json.put("Id", commonPostAttach.getId()); // 檔案Id
					sn_json.put("FileName", commonPostAttach.getFileName()); // 檔案名稱
					sn_json.put("FileType", commonPostAttach.getFileType()); // 檔案類型
					sn_json.put("FileSize", fileSize); // 檔案大小
					sn_json.put("FileHash", commonPostAttach.getFileHash()); // 檔案驗證碼(SHA256)
					sn_json.put("FileDesc", commonPostAttach.getFileDesc()); // 檔案說明
					// sn_json.put("CreateId", commonPostAttach.getCreateId());
					// // 新增者Id
					// sn_json.put("CreateName",
					// commonPostAttach.getCreateName()); // 新增者姓名
					// sn_json.put("CreateTime",
					// WebDatetime.toString(commonPostAttach.getCreateTime()));
					// // 新增時間
					// sn_json.put("ModifyId", commonPostAttach.getModifyId());
					// // 修改者Id
					// sn_json.put("ModifyName",
					// commonPostAttach.getModifyName()); // 修改者姓名
					// sn_json.put("ModifyTime",
					// WebDatetime.toString(commonPostAttach.getModifyTime()));
					// // 修改時間

					// 放入 JSON 陣列
					sn_array.put(sn_json);
				}
				// 最終傳回 JSON 為 datatable: ....
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
	 * 上傳附件
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            文章Id
	 * @param fileDesc
	 *            檔案說明
	 * @param model
	 *            Model
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s36/attach/upload", method = RequestMethod.POST)
	public @ResponseBody String UploadAttach(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {

		// 回傳 JSON
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// 準備要新增的檔案相關欄位為 json format
			JSONObject sn_json = new JSONObject();
			sn_json.put("CommonPostId", id); // 文章Id
			sn_json.put("FileDesc", fileDesc); // 檔案說明
			String json = sn_json.toString();
			if (!file.isEmpty()) {
				try {
					// 檔案 byte array
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename()); // 檔案名稱
					sn_json.put("FileType", file.getContentType()); // 檔案類型
					sn_json.put("FileSize", file.getSize()); // 檔案大小
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString())); // 檔案Hash
					json = sn_json.toString();
					CommonPost commonPost = commonPostService.getById(id);
					if (commonPost != null) {
						CommonPostAttach entity = commonPostAttachService.insert(getBaseMemberId(), json, bytes);
						if (entity != null) {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
							responseJson.put("success", true);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
						} else {
							responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
							responseJson.put("success", false);
							systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
						}
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
					}
				} catch (Exception e) {
					//e.printStackTrace();
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, "NewsManagementId=" + id.toString(), SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}

		return responseJson.toString();
	}

	/**
	 * 刪除附件資料API
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
	@RequestMapping(value = "/s36/attach/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteAttachById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			// long commonPostId = obj.isNull("CommonPostId") == true ? 0 :
			// obj.getLong("CommonPostId");

			if (!commonPostAttachService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (commonPostAttachService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
		} else {
			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
			responseJson.put("success", false);
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
	 * @param commomPostId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/s36/attach/download/{commomPostId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long commomPostId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("CommonPostId", commomPostId); // 文章Id
		sn_json.put("id", id); // 附件Id
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			// JSONObject response_json = new JSONObject();
			if (!commonPostService.isExist(commomPostId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!commonPostAttachService.isExist(id)) {
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
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(commonPostAttach.getFileName()));
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