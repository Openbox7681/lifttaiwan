package tw.gov.mohw.hisac.web.controller.api;

import java.util.ArrayList;
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
import tw.gov.mohw.hisac.web.service.SnaInfoLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;
import tw.gov.mohw.hisac.web.service.Topaf50Service;
import tw.gov.mohw.hisac.web.service.Topres20Service;


@Controller
@RequestMapping(value = "/fontend/intInfo", produces = "application/json; charset=utf-8")
public class fontened_intInfoController extends BaseController {

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
	
	@Autowired
	private Topres20Service topres20Service;
	@Autowired
	private SnaInfoLiftService snaInfoLiftService;
	

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

		System.out.println(json);
		
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
		

		

			
		
		
		

		listjson.put("datatable",sn_array);
		listjson.put("cubetable",cube_array);



		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	
	
	@RequestMapping(value = "/queryTopres20Data", method = RequestMethod.POST)
	public String queryTopres20Data(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONObject connectjson = new JSONObject();

		
		JSONObject obj = new JSONObject(json);

		JSONArray classSubList = obj.isNull("classSubList") == true ? null : obj.getJSONArray("classSubList");
		
		JSONArray sn_array = new JSONArray();
		
		JSONArray connect_array = new JSONArray();
		
		JSONArray category_array = new JSONArray();

		JSONArray link_array = new JSONArray();

		
		List<Object[]> topres20s = topres20Service.getClassSubDataByCondition(classSubList);
		
		
		
		
		List<String> res20List = new ArrayList<String>(); 
		
		
		int rank = 1;
		int category = 0;
		
		if(topres20s != null) {
			
			for(Object[] topres20 : topres20s) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Id",rank);
				
				sn_json.put("FullName", topres20[2]);
				sn_json.put("Aac", topres20[0]);
				sn_json.put("Con_Num",   topres20[1] == null ? "無" : "有" );
				sn_json.put("Affiliation", topres20[3]);
				res20List.add(topres20[2].toString());
				
				JSONObject connect_json = new JSONObject();
				connect_json.put("name", topres20[2]);
				connect_json.put("id", topres20[2]);
				connect_json.put("symbolSize", 21);
				connect_json.put("x", -282.69568 + category);
				connect_json.put("y", 475.09113-category);
				connect_json.put("value", topres20[0]);
				connect_json.put("category", category);
				
				JSONObject category_json = new JSONObject();
				category_json.put("category", category);
				
				rank ++;	
				category ++;
				sn_array.put(sn_json);	
				connect_array.put(connect_json);
				category_array.put(category_json);
			}
		}
		
		JSONObject category_json = new JSONObject();
		category_json.put("category", category);
		
		category_array.put(category_json);

		List<Object[]> linksByNames =  snaInfoLiftService.getLinksByName(res20List);
		if(linksByNames != null) {
			for(Object[] linksByName : linksByNames) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("source", linksByName[0]);
				sn_json.put("target", linksByName[1]);
				link_array.put(sn_json);
				if ( !connect_array.toString().contains(linksByName[0].toString())) {

				
				JSONObject connect_json = new JSONObject();
				connect_json.put("name", linksByName[0]);
				connect_json.put("id", linksByName[0]);
				connect_json.put("symbolSize", 10);
				connect_json.put("x", -282.69568 + category);
				connect_json.put("y", 475.09113-category);
				connect_json.put("value", 1);
				connect_json.put("category", category);
				
				
				connect_array.put(connect_json);
				
				}
				
				
			}		
		}

			

		
		
		
		connectjson.put("nodes", connect_array);
		
		connectjson.put("categories", category_array);
		
		connectjson.put("links", link_array);

		
		listjson.put("datatable",sn_array);
		
		listjson.put("connect", connectjson);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
		
	
	
	
	
}