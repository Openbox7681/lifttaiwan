package tw.gov.mohw.hisac.web.controller.api;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONArray;
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
import tw.gov.mohw.hisac.web.domain.RoleForm;
import tw.gov.mohw.hisac.web.domain.Role;
import tw.gov.mohw.hisac.web.domain.ViewFormSubsystem;
import tw.gov.mohw.hisac.web.service.RoleFormService;
import tw.gov.mohw.hisac.web.service.FormService;
import tw.gov.mohw.hisac.web.service.RoleService;

/**
 * 角色權限資料維護控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s12_RoleFormController extends BaseController {

	@Autowired
	private RoleFormService roleFormService;
	@Autowired
	private FormService formService;
	@Autowired
	private RoleService roleService;

	private String targetControllerName = "sys";
	private String targetActionName = "s12";

	/**
	 * 取得RoleForm資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return RoleForm資料
	 */
	@RequestMapping(value = "/s12/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray listjson = new JSONArray();
		JSONObject obj = new JSONObject(json);
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			long roleId = obj.isNull("RoleId") == true ? 0 : obj.getLong("RoleId");
			List<RoleForm> roleForms = roleFormService.getList(roleId);
			List<ViewFormSubsystem> viewFormSubsystems = formService.getFormAndSubsystem();
			if (viewFormSubsystems != null)
				for (ViewFormSubsystem viewFormSubsystem : viewFormSubsystems) {
					if (!viewFormSubsystem.getFormName().equals("separator")) {
						JSONObject form_json = new JSONObject();
						form_json.put("SubsystemName", viewFormSubsystem.getSubsystemName());
						form_json.put("FormId", viewFormSubsystem.getFormId());
						form_json.put("FormName", viewFormSubsystem.getFormName());
						form_json.put("InsertOrUpdate", false);
						if (roleForms != null) {
							for (RoleForm roleForm : roleForms) {
								if (roleForm.getFormId().equals(viewFormSubsystem.getFormId())) {
									form_json.put("Id", roleForm.getId());
									form_json.put("ActionCreate", roleForm.getActionCreate());
									form_json.put("ActionUpdate", roleForm.getActionUpdate());
									form_json.put("ActionDelete", roleForm.getActionDelete());
									form_json.put("ActionRead", roleForm.getActionRead());
									form_json.put("ActionSign", roleForm.getActionSign());
									form_json.put("CreateId", roleForm.getCreateId());
									form_json.put("CreateTime", roleForm.getCreateTime());
									form_json.put("ModifyId", roleForm.getModifyId());
									form_json.put("ModifyTime", roleForm.getModifyTime());
									break;
								}
							}
						}
						listjson.put(form_json);
					}
				}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

	/**
	 * 取得role資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            role資料
	 * @return role資料
	 */
	@RequestMapping(value = "/s12/getrole", method = RequestMethod.POST)
	public String Getrole(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONArray sn_array = new JSONArray();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<Role> roles = roleService.getAll();
			if (roles != null)
				for (Role role : roles) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", role.getId());
					sn_json.put("Name", role.getName());
					sn_array.put(sn_json);
				}
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", sn_array.toString());
		return "msg";
	}

	/**
	 * 新增或修改RoleForm資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param json
	 *            RoleForm
	 * @return 是否新增修改成功
	 */
	@RequestMapping(value = "/s12/createOrupdate", method = RequestMethod.POST)
	public @ResponseBody String Save(Locale locale, HttpServletRequest request, @RequestBody String json) {

		JSONObject responseJson = new JSONObject();
		if (menuService.getUpdatePermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
			if (token == null || token.getToken().equals(""))
				return responseJson.toString();

			String roleForm = roleFormService.insertOrupdate(getBaseMemberId(), json);
			if (roleForm != null) {
				responseJson.put("msg", WebMessage.getMessage("globalUpdateDataSuccess", null, locale));
				responseJson.put("success", true);
				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
			} else {
				responseJson.put("msg", WebMessage.getMessage("globalUpdateDataFail", null, locale));
				responseJson.put("success", false);

				systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			}
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Update, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		return responseJson.toString();
	}

}