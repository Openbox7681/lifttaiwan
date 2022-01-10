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

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;
import tw.gov.mohw.hisac.web.service.PaperCorLiftService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.SnaInfoLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;
import tw.gov.mohw.hisac.web.service.PaperKeywordClsService;



@Controller
@RequestMapping(value = "/cyb/c01", produces = "application/json; charset=utf-8")
public class c01_PaperKeywordController extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PaperMainsLiftService paperMainsLiftService;
	@Autowired
	private PaperCorLiftService paperCorLiftService;
	@Autowired
	private SnaTopInfoLiftService snaTopInfoLiftService;
	@Autowired
	private PaperKeywordClsService paperKeywordClsService;
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
	
	//根據選定的領域取得對應的30個關鍵字列表
	@RequestMapping(value = "/queryKeywordData", method = RequestMethod.POST)
	public String queryKeywordData(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		
		JSONObject listjson = new JSONObject();
		
		JSONArray sn_array = new JSONArray();
		
		JSONObject obj = new JSONObject(json);

		JSONArray classSubList = obj.isNull("classSubList") == true ? null : obj.getJSONArray("classSubList");

		List<PaperKeywordClsLift> paperKeywordClsLifts = paperKeywordClsService.getDataByClassSub(classSubList);
		
		if(paperKeywordClsLifts != null) {
			
			for(PaperKeywordClsLift paperKeywordClsLift : paperKeywordClsLifts) {
				JSONObject sn_json = new JSONObject();
				sn_json.put("Name",paperKeywordClsLift.getKeyword());
				sn_json.put("Flag", false);
				sn_array.put(sn_json);
			}	
		}

			
		
		listjson.put("datatable",sn_array);

	
		model.addAttribute("json", listjson.toString());
		return "msg";

		
	}
	
	//根據選定的領域與關鍵字畫出網路圖
		@RequestMapping(value = "/drawNetwork", method = RequestMethod.POST)
		public String drawNetwork(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
			
			JSONObject listjson = new JSONObject();
			JSONObject connectjson = new JSONObject();

			
			
			JSONObject obj = new JSONObject(json);

			JSONArray classSubList = obj.isNull("classSubList") == true ? null : obj.getJSONArray("classSubList");
			
			JSONArray keywordList = obj.isNull("keywordList") == true ? null : obj.getJSONArray("keywordList");
			
			JSONArray connect_array = new JSONArray();
			
			JSONArray category_array = new JSONArray();

			JSONArray link_array = new JSONArray();
			
			List<String> paperSerialNumberList = new ArrayList<String>(); 



			List<Object[]> paperKeywordClsLifts = paperKeywordClsService.getDataByKeyword(keywordList, classSubList);
			
			System.out.println(paperKeywordClsLifts);

			
			if(paperKeywordClsLifts != null) {
				for(Object[] paperKeywordClsLift : paperKeywordClsLifts) {
					paperSerialNumberList.add((String) paperKeywordClsLift[1]);
				}	
			}
			
			int category = 0;
			
			System.out.println(paperSerialNumberList);

			
			List<Object[]> linksByNames =  snaInfoLiftService.getLinksByPaperSerialNumberClassSub(paperSerialNumberList, classSubList);
			

			if(linksByNames != null) {
				for(Object[] linksByName : linksByNames) {
					
					
					if(  !(connect_array.toString().contains(linksByName[0].toString()) || connect_array.toString().contains(linksByName[1].toString()))) {
						
						JSONObject connect_json = new JSONObject();
						connect_json.put("name", linksByName[0]);
						connect_json.put("id", linksByName[0]);
						connect_json.put("symbolSize", 11);
						connect_json.put("x", -282.69568 + category);
						connect_json.put("y", 475.09113-category);
//						connect_json.put("value", 0);
						connect_json.put("category", 0);
						
						JSONObject category_json = new JSONObject();
						category_json.put("category", 0);
						category ++;
						connect_array.put(connect_json);
						category_array.put(category_json);
					}
					else {
						for(int i=0; i<connect_array.length(); i++) {
							JSONObject obj1 = (JSONObject) connect_array.get(i);
							if(obj1.getString("name").equals(linksByName[0].toString())) {
								int count = obj1.getInt("symbolSize") + 1;
//								int value = obj1.getInt("value") + 1;

								obj1.put("symbolSize", count);
//								obj1.put("value", value);

							}
							connect_array.put(i, obj1);


							
						}

					}
					
					JSONObject sn_json = new JSONObject();
					sn_json.put("source", linksByName[0]);
					sn_json.put("target", linksByName[1]);
					link_array.put(sn_json);
					
				}		
			}
			
			connectjson.put("nodes", connect_array);
			
			connectjson.put("categories", category_array);
			
			connectjson.put("links", link_array);
			
			

			listjson.put("datatable",connectjson);

			

		
			model.addAttribute("json", listjson.toString());
			return "msg";

			
		}
	
	
}