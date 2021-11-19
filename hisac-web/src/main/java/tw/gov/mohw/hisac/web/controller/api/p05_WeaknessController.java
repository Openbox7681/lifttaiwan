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
import tw.gov.mohw.hisac.web.domain.WeaknessManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementAttachMember;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementMember;
import tw.gov.mohw.hisac.web.service.WeaknessManagementService;
import tw.gov.mohw.hisac.web.service.WeaknessManagementAttachService;

/**
 * 軟體弱點漏洞通告控制器
 */
@Controller
@RequestMapping(value = "/pub/api", produces = "application/json; charset=utf-8")
public class p05_WeaknessController extends BaseController {

	@Autowired
	private WeaknessManagementService weaknessManagementService;

	@Autowired
	private WeaknessManagementAttachService weaknessManagementAttachService;

	private String targetControllerName = "pub";
	private String targetActionName = "p05";

	/**
	 * 取得軟體弱點漏洞通告資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 軟體弱點漏洞通告資料
	 */
	@RequestMapping(value = "/p05/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			obj.put("IsEnable", true);
			obj.put("StartDateTime", WebDatetime.toString(new Date(), "yyyy-MM-dd"));
			obj.put("Status", "4");
			json = obj.toString();
			List<ViewWeaknessManagementMember> weaknessManagements = weaknessManagementService.getSpList(json); // 改用
																												// store
																												// procedure
			if (weaknessManagements != null) {
				for (ViewWeaknessManagementMember weaknessManagement : weaknessManagements) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", weaknessManagement.getId());
					sn_json.put("Date", WebDatetime.toString(weaknessManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
					sn_json.put("Title", weaknessManagement.getIncidentTitle());
					sn_array.put(sn_json);
				}
			}
			listjson.put("total", weaknessManagementService.getSpListSize(json)); // 改用
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
	 * 取得軟體弱點漏洞通告資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            Id
	 * @return 軟體弱點漏洞通告資料
	 */
	@RequestMapping(value = "/p05/query/id", method = RequestMethod.POST)
	public String QueryById(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			ViewWeaknessManagementMember weaknessManagement = weaknessManagementService.getByDetail(id);
			JSONObject sn_json = new JSONObject();
			sn_json.put("Id", weaknessManagement.getId());
			sn_json.put("IncidentId", weaknessManagement.getIncidentId());
			sn_json.put("IncidentTitle", weaknessManagement.getIncidentTitle());
			sn_json.put("IncidentDiscoveryTime", WebDatetime.toString(weaknessManagement.getIncidentDiscoveryTime(), "yyyy-MM-dd"));
			sn_json.put("IncidentReportedTime", WebDatetime.toString(weaknessManagement.getIncidentReportedTime(), "yyyy-MM-dd"));
			sn_json.put("Description", weaknessManagement.getDescription());
			sn_json.put("EventTypeName", weaknessManagement.getEventTypeName());
			sn_json.put("ReporterName", weaknessManagement.getReporterName());
			sn_json.put("ResponderPartyName", weaknessManagement.getResponderPartyName());
			sn_json.put("ResponderContactNumbers", weaknessManagement.getResponderContactNumbers());
			sn_json.put("ResponderElectronicAddressIdentifiers", weaknessManagement.getResponderElectronicAddressIdentifiers());
			sn_json.put("ImpactQualification", weaknessManagement.getImpactQualification());
			sn_json.put("CoaDescription", weaknessManagement.getCoaDescription());
			sn_json.put("Confidence", weaknessManagement.getConfidence());
			sn_json.put("Reference", weaknessManagement.getReference());
			sn_json.put("AffectedSoftwareDescription", weaknessManagement.getAffectedSoftwareDescription());
			sn_json.put("Status", weaknessManagement.getStatus());
			sn_array.put(sn_json);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
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
	@RequestMapping(value = "/p05/attach/query", method = RequestMethod.POST)
	public String QueryAttach(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			long weaknessManagementId = obj.isNull("WeaknessManagementId") == true ? 0 : obj.getLong("WeaknessManagementId");
			List<ViewWeaknessManagementAttachMember> weaknessManagementAttachs = weaknessManagementAttachService.getAllByWeaknessManagementId(weaknessManagementId);
			if (weaknessManagementAttachs != null) {
				for (ViewWeaknessManagementAttachMember weaknessManagementAttach : weaknessManagementAttachs) {
					long size = weaknessManagementAttach.getFileSize();
					if (size <= 0)
						return "0";
					final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
					int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
					JSONObject sn_json = new JSONObject();
					sn_json.put("WeaknessManagementId", weaknessManagementAttach.getWeaknessManagementId());
					sn_json.put("Id", weaknessManagementAttach.getId());
					sn_json.put("FileName", weaknessManagementAttach.getFileName());
					sn_json.put("FileType", weaknessManagementAttach.getFileType());
					sn_json.put("FileSize", fileSize);
					sn_json.put("FileHash", weaknessManagementAttach.getFileHash());
					sn_json.put("FileDesc", weaknessManagementAttach.getFileDesc());
					sn_json.put("CreateId", weaknessManagementAttach.getCreateId());
					sn_json.put("CreateName", weaknessManagementAttach.getCreateName());
					sn_json.put("CreateTime", WebDatetime.toString(weaknessManagementAttach.getCreateTime()));
					sn_json.put("ModifyId", weaknessManagementAttach.getModifyId());
					sn_json.put("ModifyName", weaknessManagementAttach.getModifyName());
					sn_json.put("ModifyTime", WebDatetime.toString(weaknessManagementAttach.getModifyTime()));
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
	 * 附件下載
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param weaknessManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/p05/attach/download/{weaknessManagementId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long weaknessManagementId, @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();
		sn_json.put("WeaknessManagementId", weaknessManagementId);
		sn_json.put("Id", id);
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			if (!weaknessManagementAttachService.isExist(id)) {
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException ex) {
					//ex.printStackTrace();
				}
			} else {
				response.reset();
				WeaknessManagementAttach weaknessManagementAttach = weaknessManagementAttachService.getById(id);
				try {
					byte[] buffer = weaknessManagementAttach.getFileContent();
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(weaknessManagementAttach.getFileName(), StandardCharsets.UTF_8.toString()));
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
