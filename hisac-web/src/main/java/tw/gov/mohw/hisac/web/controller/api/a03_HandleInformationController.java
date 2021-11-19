package tw.gov.mohw.hisac.web.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.HandleInformation;
import tw.gov.mohw.hisac.web.service.HandleInformationManagementService;

/**
 * 最新消息管理控制器
 */
@Controller
@RequestMapping(value = "/alt/api", produces = "application/json; charset=utf-8")
public class a03_HandleInformationController extends BaseController {
	
	@Autowired
	private HandleInformationManagementService handleInformationManagementService;		

	private String targetControllerName = "alt";
	private String targetActionName = "a03";

	/**
	 * 取得資安廠商資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 資安廠商資料
	 */
	@RequestMapping(value = "/a03/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {

		JSONObject listjson = new JSONObject();				
		
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {

			JSONArray sn_array = new JSONArray();			
			List<HandleInformation> handleInformations = handleInformationManagementService.getList(json);
			listjson.put("total", handleInformationManagementService.getListSize(json));

			if (handleInformations != null) {
				for (HandleInformation handleInformation : handleInformations) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", handleInformation.getId());
					sn_json.put("Name", handleInformation.getName());
					sn_json.put("Section", handleInformation.getSection());			
					sn_json.put("ContactInfo", handleInformation.getContactInfo());				
					sn_json.put("ServiceItems", handleInformation.getServiceItems());	
					sn_json.put("Remark", handleInformation.getRemark());	
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
	 * @param newsManagementId
	 *            文章Id
	 * @param id
	 *            附件Id
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/a03/attach/download/{id}", method = RequestMethod.GET)
	public void DownloadAttach(Locale locale, HttpServletRequest request, HttpServletResponse response,  @PathVariable Long id) {
		JSONObject sn_json = new JSONObject();			
		String json = sn_json.toString();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {			
			HandleInformation handleInformation = handleInformationManagementService.get(id);
				try {
					byte[] buffer = handleInformation.getFileContent();
					// 下面這行被註解是因為，點擊下載連結後，會發生找不到檔案的問題，暫時改用第２行的語法
					// response.addHeader("Content-Disposition",
					// "attachment;filename=" +
					// URLEncoder.encode(newsManagementAttach.getFileName(),
					// StandardCharsets.UTF_8.toString()));
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(handleInformation.getFileName()));
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
		else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
	}
	
	
	/**
	 * 取得handle_information_remark API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            json
	 * @return handle_information_remark
	 */
	@RequestMapping(value = "/a03/query/handleInformationRemark", method = RequestMethod.POST)
	public String QueryHandleInformationRemark(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {				
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {						
						
			JSONObject responseJson = new JSONObject();
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());			
			responseJson.put("handle_information_remark", resourceMessageService.getMessageValue("handle_information_remark"));
			model.addAttribute("json",responseJson.toString());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());			
		}		
		return "msg";
	}

}