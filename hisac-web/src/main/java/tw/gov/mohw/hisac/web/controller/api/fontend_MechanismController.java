package tw.gov.mohw.hisac.web.controller.api;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.PeopleMainsLiftDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.ArticleDataService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.VideoDataService;
import tw.gov.mohw.hisac.web.domain.ArticleData;
import tw.gov.mohw.hisac.web.domain.PeopleMainsLift;
import tw.gov.mohw.hisac.web.domain.VideoData;

/**
 * 表單資料維護控制器
 */
@Controller
@RequestMapping(value = "/fontend/mechanism", produces = "application/json; charset=utf-8")
public class fontend_MechanismController extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PeopleMainsLiftDAO peopleMainsLiftDAO;
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String queryPeopleMains(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			String country = obj.isNull("country") == true ? null : obj.getString("country");
			String[] country_token = null;
			if(country != null && country.length()>0) {
				country_token = country.split(",");
			}
			
			List<Object[]> dataList = peopleMainsLiftService.getMechanism(json,country_token);
			if(dataList!= null && dataList.size()>0) {
				for(Object[] data : dataList) {
					JSONObject sn_json = new JSONObject();
					if(data[1] == null || data[1].toString().equals("NULL")) {
						if(data[0].toString().endsWith("NULL")) {
							continue;
						}
						sn_json.put("mechanism", data[0].toString());
					}else {
						sn_json.put("mechanism", data[1].toString());
					}
					sn_json.put("country", data[2].toString());
					sn_json.put("number", data[3].toString());
					sn_array.put(sn_json);
				}
			}
			JSONObject size = new JSONObject(json);
			size.put("maxRows", Long.valueOf(0));
			size.put("start", Long.valueOf(0));
			listjson.put("total", peopleMainsLiftDAO.getMechanism(size,country_token).size());
			listjson.put("datatable", sn_array);
			
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}