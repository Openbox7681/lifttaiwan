package tw.gov.mohw.hisac.web.controller.api.open;

import java.util.Base64;
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

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.WebNet;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.service.InformationExchangeService;
import tw.gov.mohw.hisac.web.service.ResourceMessageService;
import tw.gov.mohw.hisac.web.service.SystemLogService;

import tw.gov.mohw.hisac.web.service.InformationExchangeNisacService;

/**
 * 探網資料轉入API
 */
@Controller
@RequestMapping(value = "/open/api", produces = "application/json; charset=utf-8")
public class SocTwoController {

	@Autowired
	private InformationExchangeService informationExchangeService;

	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private ResourceMessageService resourceMessageService;
	
	@Autowired
	private InformationExchangeNisacService informationExchangeNisacService;

	private String baseControllerName = "SocTwoAPI";
	private String baseSystemAccount = "SERVICE.SERVER";

	protected final Base64.Decoder decoder = Base64.getDecoder();
	

	@RequestMapping(value = "/informationExchangeNisac", method = RequestMethod.POST)
	public String InformationExchangeNisac(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		
		String baseActionName = "Create";
		String headersInfo = "";// WebNet.getHeadersInfo(request).toString();
		
		resourceMessageService.loadResource();
		JSONObject responseJson = new JSONObject();
		
		try {
			informationExchangeNisacService.importFromNisac();
			System.out.println("testtt");
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
			responseJson.put("success", true);
		} catch(Exception e) {
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);
		}
		
	
		model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));
		return "msg";
	}
	
	
	/**
	 * 新增SOC2情資資料API
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
	
	@RequestMapping(value = "/soctwo", method = RequestMethod.POST)
	public String CreateSOCTWO(Locale locale, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String json) {
		String baseActionName = "Create";
		String baseIpAddress = WebNet.getIpAddr(request);
		String headersInfo = "";// WebNet.getHeadersInfo(request).toString();
		
		resourceMessageService.loadResource();
		JSONObject responseJson = new JSONObject();
		
//		if (WebNet.isAllowIp(resourceMessageService.getMessageValue("soctwoAllowIp"), WebNet.getIpAddr(request))) {
			
			JSONObject obj = new JSONObject(json);			

			obj.remove("Id");
			obj.remove("StixTitle");
			obj.remove("ReporterName");
			obj.put("ReporterName", "SOC2");
			obj.put("SourceCode", "SOC2");
			obj.put("IsEnable", true);
			obj.put("Status", 1);	
			obj.put("StixTitle", "OTH");	

			json = obj.toString();
			
			InformationExchange informationExchange = informationExchangeService.insert((long) 1, json);
			
			if (informationExchange != null) {
//				responseJson.put("debug-headersInfo", headersInfo);
//				responseJson.put("debug-body", json);
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Success, baseIpAddress, baseSystemAccount);
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
				responseJson.put("success", false);
				systemLogService.insert(baseControllerName, baseActionName, headersInfo + "---" + json, SystemLogVariable.Action.Create, SystemLogVariable.Status.Fail, baseIpAddress, baseSystemAccount);
			}
		
			
			
			
			
//
//		} else {
//			if (WebConfig.DEBUG_MODE) {
//				responseJson.put("Message", "0001");
//			} else {
//				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			}
//		}

		
		
		
		model.addAttribute("json", WebCrypto.getSafe(responseJson.toString()));
		return "msg";
		
	}
	
	
	
	
	
	

	

}