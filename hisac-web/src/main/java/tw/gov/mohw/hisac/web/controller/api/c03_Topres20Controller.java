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


import tw.gov.mohw.hisac.web.domain.Topaf50;




import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.service.PaperCorLiftService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;
import tw.gov.mohw.hisac.web.service.Topaf50Service;


@Controller
@RequestMapping(value = "/cyb/c03", produces = "application/json; charset=utf-8")
public class c03_Topres20Controller extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PaperMainsLiftService paperMainsLiftService;
	@Autowired
	private PaperCorLiftService paperCorLiftService;
	@Autowired
	private SnaTopInfoLiftService snaTopInfoLiftService;
	
	@Autowired
	private Topaf50Service topaf50Service;
	

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
	
	@RequestMapping(value = "/queryTopad50Data", method = RequestMethod.POST)
	public String queryTopad50Data(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();

		
		JSONObject obj = new JSONObject(json);

		JSONArray classSubList = obj.isNull("classSubList") == true ? null : obj.getJSONArray("classSubList");
		
		JSONArray sn_array = new JSONArray();
		JSONArray cube_array = new JSONArray();
		JSONArray pie1_array = new JSONArray();
		JSONArray pie2_array = new JSONArray();

		


		
		List<Topaf50> topaf50s = topaf50Service.getClassSubDataByCondition(classSubList);
		
		List<Object[]> pie1List = peopleMainsLiftService.getPie1DataByCondition(classSubList);
		
		List<Object[]> pie2List = topaf50Service.getPie2DataByCondition(classSubList);

		
		int rank = 1;
		
		if(topaf50s != null) {
			
			for(Topaf50 topaf50 : topaf50s) {
				JSONObject cube_json = new JSONObject();

				JSONObject sn_json = new JSONObject();
				sn_json.put("Id",rank);
				
				sn_json.put("Class_Sub", topaf50.getClass_sub());
				sn_json.put("Affiliation_e", topaf50.getAffiliation_e());
				sn_json.put("Tac", Long.valueOf(topaf50.getTac()));
				sn_json.put("Con_Num",   topaf50.getCon_num());
				sn_json.put("Country", topaf50.getCountry());
				
				
				cube_json.put("name", topaf50.getAffiliation_e());
				cube_json.put("value", Long.valueOf(topaf50.getTac()));
				
				rank ++;
				
				
				
				
				sn_array.put(sn_json);	
				cube_array.put(cube_json);
			
				
			}
		}
		//圓餅圖1
		if(pie1List != null) {
			for(Object[] pie1Data : pie1List) {
				
				JSONObject pie1_json = new JSONObject();
				
				pie1_json.put("value",  Long.valueOf(pie1Data[1].toString()));
				pie1_json.put("name", pie1Data[0].toString());
				pie1_array.put(pie1_json);

			}
		}
		
		//圓餅圖2
		if(pie2List != null) {
			
			JSONObject pie2_json = new JSONObject();
			pie2_json.put("value",  Long.valueOf(pie2List.size()));
			pie2_json.put("name", "頂尖機構數量");
			pie2_array.put(pie2_json);
			
			System.out.println(pie2List.size());

			JSONObject all_json = new JSONObject();
			all_json.put("value", Long.valueOf(pie1List.size() -  pie2List.size()));
			all_json.put("name", "非頂尖機構數量");
	
			pie2_array.put(all_json);
			
		}

		

			
		
		
		

		listjson.put("datatable",sn_array);
		listjson.put("cubetable",cube_array);
		listjson.put("pie1table",pie1_array);
		listjson.put("pie2table",pie2_array);



		model.addAttribute("json", listjson.toString());
		return "msg";
	}
		
	
	
	
	
}