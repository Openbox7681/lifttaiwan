package tw.gov.mohw.hisac.web.controller.api;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
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
import tw.gov.mohw.hisac.web.domain.Links;
import tw.gov.mohw.hisac.web.domain.LinksPic;
//import tw.gov.mohw.hisac.web.domain.ViewNewsManagementAttachMember;
//import tw.gov.mohw.hisac.web.domain.ViewNewsManagementPicMember;
import tw.gov.mohw.hisac.web.domain.ViewLinksPicMember;
import tw.gov.mohw.hisac.web.domain.ViewLinksMember;
import tw.gov.mohw.hisac.web.service.LinksService;
import tw.gov.mohw.hisac.web.service.LinksPicService;

/**
 * 相關連結管理控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s41_LinksController extends BaseController {

	@Autowired
	private LinksService linksService;
	@Autowired
	private LinksPicService linksPicService;

	private String targetControllerName = "sys";
	private String targetActionName = "s41";

	/**
	 * 取得Links資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return Links資料
	 */
	@RequestMapping(value = "/s41/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			listjson.put("total", linksService.getListSize(json));
			List<ViewLinksMember> links = linksService.getList(json);
			if (links != null) {
				for (ViewLinksMember link : links) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", link.getId());
					// sn_json.put("PostType", link.getPostType());
					sn_json.put("PostDateTime", WebDatetime.toString(link.getPostDateTime(), "yyyy-MM-dd"));
					sn_json.put("Title", link.getTitle());
					sn_json.put("SourceName", link.getSourceName());
					sn_json.put("SourceLink", link.getSourceLink());
					// sn_json.put("ContentType", link.getContentType());
					// sn_json.put("Content", link.getContent());
					// sn_json.put("ExternalLink", link.getExternalLink());
					sn_json.put("IsBreakLine", link.getIsBreakLine());
					sn_json.put("StartDateTime", WebDatetime.toString(link.getStartDateTime(), "yyyy-MM-dd"));
					sn_json.put("EndDateTime", WebDatetime.toString(link.getEndDateTime(), "yyyy-MM-dd"));
					sn_json.put("IsEnable", link.getIsEnable());
					sn_json.put("CreateId", link.getCreateId());
					sn_json.put("CreateName", link.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(link.getCreateTime()));
					sn_json.put("ModifyId", link.getModifyId());
					sn_json.put("ModifyName", link.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(link.getModifyTime()));
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
	 * 取得Links資料API
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
	@RequestMapping(value = "/s41/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Links link = linksService.get(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", link.getId());
			// sn_json.put("PostType", link.getPostType());
			sn_json.put("PostDateTime", WebDatetime.toString(link.getPostDateTime(), "yyyy-MM-dd"));
			sn_json.put("Title", link.getTitle());
			sn_json.put("SourceName", link.getSourceName());
			sn_json.put("SourceLink", link.getSourceLink());
			// sn_json.put("ContentType", link.getContentType());
			// sn_json.put("Content", link.getContent());
			// sn_json.put("ExternalLink", link.getExternalLink());
			sn_json.put("IsBreakLine", link.getIsBreakLine());
			sn_json.put("StartDateTime", WebDatetime.toString(link.getStartDateTime(), "yyyy-MM-dd"));
			sn_json.put("EndDateTime", WebDatetime.toString(link.getEndDateTime(), "yyyy-MM-dd"));
			sn_json.put("IsEnable", link.getIsEnable());
			sn_json.put("CreateId", link.getCreateId());
			sn_json.put("CreateTime", WebDatetime.toString(link.getCreateTime()));
			sn_json.put("ModifyId", link.getModifyId());
			sn_json.put("ModifyTime", WebDatetime.toString(link.getModifyTime()));
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增Links資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            NewsManagement
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/s41/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			Links links = linksService.insert(getBaseMemberId(), json);
			if (links != null) {
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
	 * 更新Links資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            NewsManagement
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "/s41/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");
			if (!linksService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				Links links = linksService.update(getBaseMemberId(), json);
				if (links != null) {
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
	 * 刪除Links資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            編號
	 * @return 是否刪除成功
	 */
	@RequestMapping(value = "/s41/delete", method = RequestMethod.POST)
	public @ResponseBody String Delete(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			if (!linksService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				try {
					List<ViewLinksPicMember> linksPics = linksPicService.getAllByLinksId(id);
					if (linksPics != null) {
						for (ViewLinksPicMember linksPic : linksPics) {
							linksPicService.delete(linksPic.getId());
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}

				if (linksService.delete(id)) {
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
	@RequestMapping(value = "/s41/pic/query", method = RequestMethod.POST)
	public String QueryPic(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long linksId = obj.isNull("LinksId") == true ? 0 : obj.getLong("LinksId");
			List<ViewLinksPicMember> linksPics = linksPicService.getAllByLinksId(linksId);
			if (linksPics != null) {
				for (ViewLinksPicMember linksPic : linksPics) {
					long size = linksPic.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					String imageLink = "<img src=\"./api/s41/pic/download/" + linksId + "/" + linksPic.getId() + "\" title=\"" + linksPic.getFileDesc() + "\" width=\"" + linksPic.getImageWidth() + "\">";
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", linksPic.getId());
					sn_json.put("LinksId", linksId);
					sn_json.put("FileName", linksPic.getFileName());
					sn_json.put("FileType", linksPic.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("ImageWidth", linksPic.getImageWidth());
					sn_json.put("ImageHeight", linksPic.getImageHeight());
					sn_json.put("ImageLink", imageLink);
					sn_json.put("FileDesc", linksPic.getFileDesc());
					sn_json.put("CreateId", linksPic.getCreateId());
					sn_json.put("CreateName", linksPic.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(linksPic.getCreateTime()));
					sn_json.put("ModifyId", linksPic.getModifyId());
					sn_json.put("ModifyName", linksPic.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(linksPic.getModifyTime()));
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
	 * 上傳圖檔API
	 * 
	 * @param file
	 *            MultipartFile
	 * @param id
	 *            LinksId
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
	@RequestMapping(value = "/s41/pic/upload", method = RequestMethod.POST)
	public String UploadPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("FileDesc") String fileDesc, Model model, Locale locale, HttpServletRequest request) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("LinksId", id);
			sn_json.put("FileDesc", fileDesc);
			String json = sn_json.toString();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					sn_json.put("FileName", file.getOriginalFilename());
					sn_json.put("FileType", file.getContentType());
					sn_json.put("FileSize", file.getSize());
					Image image = ImageIO.read(file.getInputStream());
					int imageWidth = image.getWidth(null);
					int imageHeight = image.getHeight(null);
					sn_json.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
					sn_json.put("ImageWidth", imageWidth);
					sn_json.put("ImageHeight", imageHeight);
					json = sn_json.toString();
					Links links = linksService.get(id);
					if (links != null) {
						LinksPic entity = linksPicService.insert(getBaseMemberId(), json, bytes);
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
	@RequestMapping(value = "/s41/pic/delete", method = RequestMethod.POST)
	public @ResponseBody String DeletePic(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			// long linksid = obj.isNull("LinksId") == true ? 0 :
			// obj.getLong("LinksId");
			if (!linksPicService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (linksPicService.delete(id)) {
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
	 * 圖片輸出
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param linksId
	 *            文章Id
	 * @param id
	 *            圖檔Id
	 */
	@RequestMapping(value = "/s41/pic/download/{linksId}/{id}", method = RequestMethod.GET)
	public void ShowPic(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long linksId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("LinksId", linksId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!linksService.isExist(linksId)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else if (!linksPicService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				LinksPic linksPic = linksPicService.getById(id);
				try {
					byte[] buffer = linksPic.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(linksPic.getFileName(), StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Length", "" + buffer.length);
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(linksPic.getFileType());
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