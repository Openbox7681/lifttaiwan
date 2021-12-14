package tw.gov.mohw.hisac.web.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

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
import tw.gov.mohw.hisac.web.domain.TtmaxInfoLift;
import tw.gov.mohw.hisac.web.service.PaperCorLiftService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;
import tw.gov.mohw.hisac.web.service.TtmaxInfoLiftService;

@Controller
@RequestMapping(value = "/pub/common", produces = "application/json; charset=utf-8")
public class f_navbarController extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PaperMainsLiftService paperMainsLiftService;
	@Autowired
	private PaperCorLiftService paperCorLiftService;
	@Autowired
	private SnaTopInfoLiftService snaTopInfoLiftService;
	@Autowired
	private TtmaxInfoLiftService ttmaxInfoLiftService;

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
	
	@RequestMapping(value = "/queryMap", method = RequestMethod.POST)
	public String queryMap(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();

		List<Object[]> mapList = peopleMainsLiftService.getMapData();
		if(mapList != null) {
			for(Object[] mapData : mapList) {
				if(sn_array.toString().contains(mapData[1].toString())) {
					for(int i=0; i<sn_array.length(); i++) {
						JSONObject obj = (JSONObject) sn_array.get(i);
						if(obj.getString("name").equals(mapData[1].toString())) {
							
							Long count = obj.getLong("trend") + Long.valueOf(mapData[2].toString());
							obj.put("trend", count);
							
							if("資訊及數位相關產業".equals(mapData[0].toString())) {
								obj.put("value1", mapData[2]);
							}else if("綠電及再生能源產業".equals(mapData[0].toString())) {
								obj.put("value2", mapData[2]);
							}else if("台灣精準健康戰略產業".equals(mapData[0].toString())) {
								obj.put("value3", mapData[2]);
							}else if("國防及戰略產業".equals(mapData[0].toString())) {
								obj.put("value4", mapData[2]);
							}else if("民生及戰備產業".equals(mapData[0].toString())) {
								obj.put("value5", mapData[2]);
							}else if("其他".equals(mapData[0].toString())) {
								obj.put("value6", mapData[2]);
							}
						}
					}
				}else {
					JSONObject sn_json = new JSONObject();
					
					sn_json.put("name", mapData[1].toString());
					sn_json.put("trend", Long.valueOf(mapData[2].toString()));
					sn_json.put("value1", Long.valueOf(0));
					sn_json.put("value2", Long.valueOf(0));
					sn_json.put("value3", Long.valueOf(0));
					sn_json.put("value4", Long.valueOf(0));
					sn_json.put("value5", Long.valueOf(0));
					sn_json.put("value6", Long.valueOf(0));
					
					if("資訊及數位相關產業".equals(mapData[0].toString())) {
						sn_json.put("value1", mapData[2]);
					}else if("綠電及再生能源產業".equals(mapData[0].toString())) {
						sn_json.put("value2", mapData[2]);
					}else if("台灣精準健康戰略產業".equals(mapData[0].toString())) {
						sn_json.put("value3", mapData[2]);
					}else if("國防及戰略產業".equals(mapData[0].toString())) {
						sn_json.put("value4", mapData[2]);
					}else if("民生及戰備產業".equals(mapData[0].toString())) {
						sn_json.put("value5", mapData[2]);
					}else if("其他".equals(mapData[0].toString())) {
						sn_json.put("value6", mapData[2]);
					}
				
					sn_array.put(sn_json);
				}
			}
		}
		listjson.put("mapData",sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/queryPoints", method = RequestMethod.POST)
	public String queryPoints(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		
		List<TtmaxInfoLift> pointList = ttmaxInfoLiftService.getList(json);
		if(pointList.size()>0) {
			for(TtmaxInfoLift ttmaxInfoLift : pointList) {
				JSONArray obj_array = new JSONArray();
				obj_array.put(ttmaxInfoLift.getIndex_x());
				obj_array.put(ttmaxInfoLift.getIndex_y());
				obj_array.put(ttmaxInfoLift.getPaper_total_num());
				obj_array.put(ttmaxInfoLift.getClass_sub());
				obj_array.put("千里馬計畫優勢");
				sn_array.put(obj_array);
			}
		}
		listjson.put("pointData", sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}