package tw.gov.mohw.hisac.web.controller.api;

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


import tw.gov.mohw.hisac.web.domain.Topres20;




import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.service.PaperCorLiftService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;
import tw.gov.mohw.hisac.web.service.Topres20Service;


@Controller
@RequestMapping(value = "/cyb/c02", produces = "application/json; charset=utf-8")
public class c02_Topres20Controller extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PaperMainsLiftService paperMainsLiftService;
	@Autowired
	private PaperCorLiftService paperCorLiftService;
	@Autowired
	private SnaTopInfoLiftService snaTopInfoLiftService;
	
	@Autowired
	private Topres20Service topres20Service;
	

	@RequestMapping(value = "/queryNumber", method = RequestMethod.POST)
	public String queryNumber(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		
		listjson.put("peopleNum", peopleMainsLiftService.getListSize(json));
		listjson.put("paperNum", paperMainsLiftService.getListSize(json));
		listjson.put("paperCorNum", paperCorLiftService.getListSize(json));
		listjson.put("snaTopNum", snaTopInfoLiftService.getListSize(json));
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/queryTopres20Data", method = RequestMethod.POST)
	public String queryTopres20Data(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();

		
		JSONObject obj = new JSONObject(json);

		JSONArray classSubList = obj.isNull("classSubList") == true ? null : obj.getJSONArray("classSubList");
		
		JSONArray sn_array = new JSONArray();

		
		List<Topres20> topres20s = topres20Service.getClassSubDataByCondition(classSubList);
		
		int rank = 1;
		
		if(topres20s != null) {
			
			for(Topres20 topres20 : topres20s) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id",rank);
				
				sn_json.put("Class_Sub", topres20.getClass_sub());
				sn_json.put("FullName", topres20.getFullname());
				sn_json.put("Aac", topres20.getAac());
				sn_json.put("Con_Num",   topres20.getCon_num() == null ? "無" : "有" );
				sn_json.put("Affiliation", topres20.getAffiliation());
				sn_json.put("Country", topres20.getCountry());
				rank ++;
				
				sn_array.put(sn_json);	
			}
		}

		listjson.put("datatable",sn_array);

		
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
		
	
	
	
	
}