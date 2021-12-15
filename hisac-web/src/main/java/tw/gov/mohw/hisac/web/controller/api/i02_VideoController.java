package tw.gov.mohw.hisac.web.controller.api;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.FormService;
import tw.gov.mohw.hisac.web.service.SubsystemService;
import tw.gov.mohw.hisac.web.service.VideoDataService;
import tw.gov.mohw.hisac.web.domain.Form;
import tw.gov.mohw.hisac.web.domain.Subsystem;
import tw.gov.mohw.hisac.web.domain.VideoData;
import tw.gov.mohw.hisac.web.domain.ViewFormName;

/**
 * 表單資料維護控制器
 */
@Controller
@RequestMapping(value = "/inc/common", produces = "application/json; charset=utf-8")
public class i02_VideoController extends BaseController {

	// public final String FUNC_NAME = "s11";
	@Autowired
	private FormService formService;
	@Autowired
	private SubsystemService subsystemService;
	@Autowired
	private VideoDataService videoDataService;

	private String targetControllerName = "inc";
	private String targetActionName = "i02";

	@RequestMapping(value = "/i02/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<VideoData> vList = videoDataService.getList(json);
			listjson.put("total", videoDataService.getListSize(json));

			JSONArray sn_array = new JSONArray();
			if (vList != null)
				for (VideoData videoData : vList) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", videoData.getId());
					sn_json.put("Title", videoData.getTitle());
					sn_json.put("Date", sdf.format(videoData.getCreateTime()));
					sn_json.put("IsEnable", videoData.getIsEnable());
					sn_json.put("IsShow", videoData.getIsShow());

					sn_array.put(sn_json);
				}
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/i02/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
//		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);

			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			VideoData videoData = videoDataService.get(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", videoData.getId());
			sn_json.put("Title", videoData.getTitle());
			sn_json.put("Video_Url", videoData.getVideo_Url());
			sn_json.put("Thumbnail_Url", videoData.getThumbnail_Url());
			sn_json.put("IsEnable", videoData.getIsEnable());
			sn_json.put("IsShow", videoData.getIsShow());
			sn_array.put(sn_json);

			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	@RequestMapping(value = "/i02/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
//		if (menuService.getCreatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			VideoData videoData = videoDataService.insert(getBaseMemberId(), json);
			if (videoData != null) {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		return responseJson.toString();
	}

	@RequestMapping(value = "/i02/update", method = RequestMethod.POST)
	public @ResponseBody String Update(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
//		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.getLong("Id");

			if (!videoDataService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				VideoData videoData = videoDataService.update(getBaseMemberId(), json);
				if (videoData != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		return responseJson.toString();
	}

	@RequestMapping(value = "/i02/delete", method = RequestMethod.POST)
	public @ResponseBody String DeleteById(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		if (token == null || token.getToken().equals(""))
			return responseJson.toString();
//		if (menuService.getDeletePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");

			if (!videoDataService.isExist(id)) {
				responseJson.put("msg", WebMessage.getMessage("globalDataNotExist", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				if (videoDataService.delete(id)) {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataSuccess", null, locale));
					responseJson.put("success", true);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalDeleteDataFail", null, locale));
					responseJson.put("success", false);

					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
			}
//		} else {
//			responseJson.put("msg", WebMessage.getMessage("globalPermissionDeny", null, locale));
//			responseJson.put("success", false);
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Delete, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		return responseJson.toString();
	}
}