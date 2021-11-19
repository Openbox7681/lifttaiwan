package tw.gov.mohw.hisac.web.controller.api.open;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.BASE64DecodedMultipartFile;
import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.WebNet;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;
import tw.gov.mohw.hisac.web.service.InformationExchangeAttachService;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.ResourceMessageService;
import tw.gov.mohw.hisac.web.service.SystemLogService;

/**
 * 探網資料轉入API
 */
@Controller
@RequestMapping(value = "/open/api", produces = "application/json; charset=utf-8")
public class TkfcIsacController {

	@Autowired
	private InformationExchangeService informationExchangeService;

	@Autowired
	private InformationExchangeAttachService informationExchangeAttachService;

	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private ResourceMessageService resourceMessageService;

	private String baseControllerName = "TkfcAPI";
	private String baseSystemAccount = "SERVICE.SERVER";

	protected final Base64.Decoder decoder = Base64.getDecoder();
	/**
	 * 新增情資資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param model
	 *            Model
	 * @param json
	 *            情資資料
	 * @return 是否新增成功
	 */
	@RequestMapping(value = "/tkfc_isac", method = RequestMethod.POST)
	public String Create(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		String baseActionName = "Create";
		String baseIpAddress = WebNet.getIpAddr(request);
		String headersInfo = "";// WebNet.getHeadersInfo(request).toString();

		resourceMessageService.loadResource();
		JSONObject responseJson = new JSONObject();
		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("tkfcisacAllowIp"), WebNet.getIpAddr(request))) {
			JSONObject obj = new JSONObject(json);
			String incidentId = obj.isNull("IncidentId") == true ? null : obj.getString("IncidentId");
			incidentId = "tkfc-" + incidentId;
			obj.put("SourceCode", "OTH");
			obj.put("IsEnable", true);
			obj.put("Status", 1);
			obj.put("Id", incidentId);

			json = obj.toString();

			InformationExchange infor = informationExchangeService.getById(incidentId);
			if (infor != null) {
				if (infor.getStatus() == 1) {
					json = new JSONObject(obj.toString()).toString();
					InformationExchange informationExchange = informationExchangeService.update((long) 1, json);
					if (informationExchange != null) {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
						responseJson.put("success", true);
						systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
					} else {
						responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
						responseJson.put("success", false);
						systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
					}
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
				}

			} else {
				InformationExchange informationExchange = informationExchangeService.insert((long) 1, json);
				if (informationExchange != null) {
					// responseJson.put("debug-headersInfo", headersInfo);
					// responseJson.put("debug-body", json);
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
				}
			}
		} else {
			if (WebConfig.DEBUG_MODE) {
				responseJson.put("Message", "0001");
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		model.addAttribute("json", responseJson.toString());
		return "msg";
	}

	/**
	 * 上傳附件API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param model
	 *            Model
	 * @param json
	 *            上傳附件資料
	 * @return 是否成功
	 */
	@RequestMapping(value = "/tkfc_isac/attach/upload", method = RequestMethod.POST)
	public String UploadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		String baseActionName = "Upload";
		String baseIpAddress = WebNet.getIpAddr(request);
		resourceMessageService.loadResource();

		JSONObject responseJson = new JSONObject();

		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("tkfcisacAllowIp"), WebNet.getIpAddr(request))) {
			JSONObject obj = new JSONObject(json);
			String informationExchangeId = obj.isNull("InformationExchangeId") == true ? null : obj.getString("InformationExchangeId");
			informationExchangeId = "tkfc-" + informationExchangeId;

			String fileBody = obj.isNull("FileBody") == true ? null : obj.getString("FileBody");
			BASE64DecodedMultipartFile file = new BASE64DecodedMultipartFile(decoder.decode(fileBody));
			try {
				byte[] bytes = file.getBytes();

				// sn_json.put("FileName", fileName);
				// sn_json.put("FileType", fileType);
				obj.put("InformationExchangeId", informationExchangeId);
				obj.put("FileSize", file.getSize());
				obj.put("FileHash", WebCrypto.getHash(WebCrypto.HashType.SHA256, bytes.toString()));
				json = obj.toString();
				InformationExchangeAttach entity = informationExchangeAttachService.insert((long) 1, json, bytes);
				if (entity != null) {
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
				} else {
					responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
				}
			} catch (Exception e) {
				//e.printStackTrace();
				responseJson.put("msg", WebMessage.getMessage("globalUploadFileFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
			}
		} else {
			if (WebConfig.DEBUG_MODE) {
				responseJson.put("Message", "0001");
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}

		model.addAttribute("json", responseJson.toString());
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
	 * @param informationExchangeId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@RequestMapping(value = "/tkfc_isac/attach/download/{informationExchangeId}/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response, @PathVariable Long informationExchangeId, @PathVariable Long id) {
		String baseActionName = "Download";
		String baseIpAddress = WebNet.getIpAddr(request);
		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("tkfcisacAllowIp"), WebNet.getIpAddr(request))) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("InformationExchangeId", informationExchangeId);
			sn_json.put("Id", id);
			String json = sn_json.toString();

			InformationExchangeAttach informationExchangeAttach = informationExchangeAttachService.get(id);
			try {
				response.reset();
				byte[] buffer = informationExchangeAttach.getFileContent();
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(informationExchangeAttach.getFileName(), StandardCharsets.UTF_8.toString()));
				response.addHeader("Content-Length", "" + buffer.length);
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
			} catch (IOException ex) {
				//ex.printStackTrace();
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}