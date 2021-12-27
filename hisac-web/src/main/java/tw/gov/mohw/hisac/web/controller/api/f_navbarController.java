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
import tw.gov.mohw.hisac.web.service.InboundPeoplePaperNoCorService;
import tw.gov.mohw.hisac.web.service.InboundPeoplePaperService;
import tw.gov.mohw.hisac.web.service.OutboundPeoplePaperNoCorService;
import tw.gov.mohw.hisac.web.service.OutboundPeoplePaperService;
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
	private InboundPeoplePaperService inboundPeoplePaperService;
	@Autowired
	private TtmaxInfoLiftService ttmaxInfoLiftService;
	@Autowired
	private InboundPeoplePaperNoCorService inboundPeoplePaperNoCorService;
	@Autowired
	private OutboundPeoplePaperService outboundPeoplePaperService;
	@Autowired
	private OutboundPeoplePaperNoCorService outboundPeoplePaperNoCorService;

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
							
							Long count = obj.getLong("total") + Long.valueOf(mapData[2].toString());
							obj.put("total", count);
							
							if("資訊及數位相關產業".equals(mapData[0].toString())) {
								obj.put("value1", mapData[2]);
							}else if("綠電及再生能源產業".equals(mapData[0].toString())) {
								obj.put("value2", mapData[2]);
							}else if("臺灣精準健康戰略產業".equals(mapData[0].toString())) {
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
					sn_json.put("total", Long.valueOf(0));

					
					
					if("資訊及數位相關產業".equals(mapData[0].toString())) {
						sn_json.put("value1", mapData[2]);
					}else if("綠電及再生能源產業".equals(mapData[0].toString())) {
						sn_json.put("value2", mapData[2]);
					}else if("臺灣精準健康戰略產業".equals(mapData[0].toString())) {
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
		
		for(int i=0; i<sn_array.length(); i++) {
			JSONObject obj = (JSONObject) sn_array.get(i);
			if((obj.getLong("total") >= 225)) {
				obj.put("trend", 5);
			}else if ( obj.getLong("total")<225 && obj.getLong("total") >= 87) {
				obj.put("trend", 4);
			}else if  ( obj.getLong("total")<87 && obj.getLong("total") >= 38) {
				obj.put("trend", 3);
			}else if  ( obj.getLong("total")<38 && obj.getLong("total") >= 16) {
				obj.put("trend", 2);
			}else if  ( obj.getLong("total")<16 && obj.getLong("total") >= 5) {
				obj.put("trend", 1);
			}else if  ( obj.getLong("total")<5 && obj.getLong("total") >= 1) {
				obj.put("trend", 0);
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
	
	@RequestMapping(value = "/queryLines", method = RequestMethod.POST)
	public String queryLines(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		
		List<Object[]> lineList = inboundPeoplePaperService.getLineData();
		if(lineList.size()>0) {
			for(Object[] lineData : lineList) {
				sn_array.put(lineData[1].toString());
			}
		}
		listjson.put("lineData", sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/p01/queryForm", method = RequestMethod.POST)
	public String queryFormP01(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject total_json = new JSONObject();
		
		total_json.put("name", "合計");
		total_json.put("open", Long.valueOf(0));
		total_json.put("pi", Long.valueOf(0));
		total_json.put("short", Long.valueOf(0));
		total_json.put("dragon", Long.valueOf(0));
		total_json.put("policy", Long.valueOf(0));
		total_json.put("horse", Long.valueOf(0));
		total_json.put("total", Long.valueOf(0));

		List<Object[]> formList = peopleMainsLiftService.getFormData();
		if(formList != null) {
			for(Object[] formData : formList) {
				if(sn_array.toString().contains(formData[0].toString())) {
					for(int i=0; i<sn_array.length(); i++) {
						JSONObject obj = (JSONObject) sn_array.get(i);
						if(obj.getString("name").equals(formData[0].toString())) {
							
							Long count = obj.getLong("total") + Long.valueOf(formData[2].toString());
							obj.put("total", count);
							
							Long total = total_json.getLong("total") + Long.valueOf(formData[2].toString());
							total_json.put("total", total);
							
							if("盤古開天".equals(formData[1].toString())) {
								obj.put("open", formData[2]);
								Long value = total_json.getLong("open") + Long.valueOf(formData[2].toString());
								total_json.put("open", value);
							}else if("國合PI".equals(formData[1].toString())) {
								obj.put("pi", formData[2]);
								Long value = total_json.getLong("pi") + Long.valueOf(formData[2].toString());
								total_json.put("pi", value);
							}else if("短期訪問學者".equals(formData[1].toString())) {
								obj.put("short", formData[2]);
								Long value = total_json.getLong("short") + Long.valueOf(formData[2].toString());
								total_json.put("short", value);
							}else if("龍門計畫主持人".equals(formData[1].toString())) {
								obj.put("dragon", formData[2]);
								Long value = total_json.getLong("dragon") + Long.valueOf(formData[2].toString());
								total_json.put("dragon", value);
							}else if("政策邀訪學者".equals(formData[1].toString())) {
								obj.put("policy", formData[2]);
								Long value = total_json.getLong("policy") + Long.valueOf(formData[2].toString());
								total_json.put("policy", value);
							}else if("千里馬申請人".equals(formData[1].toString())) {
								obj.put("horse", formData[2]);
								Long value = total_json.getLong("horse") + Long.valueOf(formData[2].toString());
								total_json.put("horse", value);
							}
						}
					}
				}else {
					JSONObject sn_json = new JSONObject();
					
					sn_json.put("name", formData[0].toString());
					sn_json.put("open", Long.valueOf(0));
					sn_json.put("pi", Long.valueOf(0));
					sn_json.put("short", Long.valueOf(0));
					sn_json.put("dragon", Long.valueOf(0));
					sn_json.put("policy", Long.valueOf(0));
					sn_json.put("horse", Long.valueOf(0));
					sn_json.put("total", Long.valueOf(formData[2].toString()));
					
					Long total = total_json.getLong("total") + Long.valueOf(formData[2].toString());
					total_json.put("total", total);
					
					if("盤古開天".equals(formData[1].toString())) {
						sn_json.put("open", formData[2]);
						Long count = total_json.getLong("open") + Long.valueOf(formData[2].toString());
						total_json.put("open", count);
					}else if("國合PI".equals(formData[1].toString())) {
						sn_json.put("pi", formData[2]);
						Long count = total_json.getLong("pi") + Long.valueOf(formData[2].toString());
						total_json.put("pi", count);
					}else if("短期訪問學者".equals(formData[1].toString())) {
						sn_json.put("short", formData[2]);
						Long count = total_json.getLong("short") + Long.valueOf(formData[2].toString());
						total_json.put("short", count);
					}else if("龍門計畫主持人".equals(formData[1].toString())) {
						sn_json.put("dragon", formData[2]);
						Long count = total_json.getLong("dragon") + Long.valueOf(formData[2].toString());
						total_json.put("dragon", count);
					}else if("政策邀訪學者".equals(formData[1].toString())) {
						sn_json.put("policy", formData[2]);
						Long count = total_json.getLong("policy") + Long.valueOf(formData[2].toString());
						total_json.put("policy", count);
					}else if("千里馬申請人".equals(formData[1].toString())) {
						sn_json.put("horse", formData[2]);
						Long count = total_json.getLong("horse") + Long.valueOf(formData[2].toString());
						total_json.put("horse", count);
					}
				
					sn_array.put(sn_json);
				}
			}
		}
		sn_array.put(total_json);
		listjson.put("formData",sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/p02/queryForm", method = RequestMethod.POST)
	public String queryFormP02(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		
		JSONObject inbound_json = new JSONObject();
		inbound_json.put("name","In bound 國際合著篇數");
		inbound_json.put("eleven", Float.valueOf(0));
		inbound_json.put("twelve", Float.valueOf(0));
		inbound_json.put("thirteen", Float.valueOf(0));
		inbound_json.put("fourteen", Float.valueOf(0));
		inbound_json.put("fifteen", Float.valueOf(0));
		inbound_json.put("sixteen", Float.valueOf(0));
		inbound_json.put("seventeen", Float.valueOf(0));
		inbound_json.put("eighteen", Float.valueOf(0));
		inbound_json.put("nineteen", Float.valueOf(0));
		inbound_json.put("twenty", Float.valueOf(0));
		inbound_json.put("total", Float.valueOf(0));
		
		Float inboundValue = Float.valueOf(0);
		
		List<Object[]> inboundList = inboundPeoplePaperService.getLineData();
		if(inboundList.size()>0) {
			for(Object[] lineData : inboundList) {
				if("100".equals(lineData[0].toString())) {
					inbound_json.put("eleven", Float.valueOf(lineData[1].toString()));
				}else if("101".equals(lineData[0].toString())) {
					inbound_json.put("twelve", Float.valueOf(lineData[1].toString()));
				}else if("102".equals(lineData[0].toString())) {
					inbound_json.put("thirteen", Float.valueOf(lineData[1].toString()));
				}else if("103".equals(lineData[0].toString())) {
					inbound_json.put("fourteen", Float.valueOf(lineData[1].toString()));
				}else if("104".equals(lineData[0].toString())) {
					inbound_json.put("fifteen", Float.valueOf(lineData[1].toString()));
				}else if("105".equals(lineData[0].toString())) {
					inbound_json.put("sixteen", Float.valueOf(lineData[1].toString()));
				}else if("106".equals(lineData[0].toString())) {
					inbound_json.put("seventeen", Float.valueOf(lineData[1].toString()));
				}else if("107".equals(lineData[0].toString())) {
					inbound_json.put("eighteen", Float.valueOf(lineData[1].toString()));
				}else if("108".equals(lineData[0].toString())) {
					inbound_json.put("nineteen", Float.valueOf(lineData[1].toString()));
				}else if("109".equals(lineData[0].toString())) {
					inbound_json.put("twenty", Float.valueOf(lineData[1].toString()));
				}
				inboundValue = inboundValue + Float.valueOf(lineData[1].toString());
			}
			inbound_json.put("total", inboundValue);
			sn_array.put(inbound_json);
		}
		
		JSONObject inNoCor_json = new JSONObject();
		inNoCor_json.put("name","In bound 平均發表篇數");
		inNoCor_json.put("eleven", Float.valueOf(0));
		inNoCor_json.put("twelve", Float.valueOf(0));
		inNoCor_json.put("thirteen", Float.valueOf(0));
		inNoCor_json.put("fourteen", Float.valueOf(0));
		inNoCor_json.put("fifteen", Float.valueOf(0));
		inNoCor_json.put("sixteen", Float.valueOf(0));
		inNoCor_json.put("seventeen", Float.valueOf(0));
		inNoCor_json.put("eighteen", Float.valueOf(0));
		inNoCor_json.put("nineteen", Float.valueOf(0));
		inNoCor_json.put("twenty", Float.valueOf(0));
		inNoCor_json.put("total", Float.valueOf(0));
		
		Float inNoCorValue = Float.valueOf(0);
		
		List<Object[]> inNoCorList = inboundPeoplePaperNoCorService.getLineData();
		if(inNoCorList.size()>0) {
			for(Object[] lineData : inNoCorList) {
				if("100".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("eleven");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("eleven", (float)(Math.round(value*100))/100);
				}else if("101".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("twelve");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("twelve", (float)(Math.round(value*100))/100);
				}else if("102".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("thirteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("thirteen", (float)(Math.round(value*100))/100);
				}else if("103".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("fourteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("fourteen", (float)(Math.round(value*100))/100);
				}else if("104".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("fifteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("fifteen", (float)(Math.round(value*100))/100);
				}else if("105".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("sixteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("sixteen", (float)(Math.round(value*100))/100);
				}else if("106".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("seventeen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("seventeen", (float)(Math.round(value*100))/100);
				}else if("107".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("eighteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("eighteen", (float)(Math.round(value*100))/100);
				}else if("108".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("nineteen");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("nineteen", (float)(Math.round(value*100))/100);
				}else if("109".equals(lineData[0].toString())) {
					Float value = inbound_json.getFloat("twenty");
					value = value / Float.valueOf(lineData[1].toString());
					inNoCor_json.put("twenty", (float)(Math.round(value*100))/100);
				}
				inNoCorValue = inNoCorValue + Float.valueOf(lineData[1].toString());
			}
			Float inNoCorTotal = inNoCorValue / inbound_json.getFloat("total");
			inNoCor_json.put("total", (float)(Math.round(inNoCorTotal*100))/100);
			sn_array.put(inNoCor_json);
		}
		
		JSONObject outbound_json = new JSONObject();
		outbound_json.put("name","Out bound 國際合著篇數");
		outbound_json.put("eleven", Float.valueOf(0));
		outbound_json.put("twelve", Float.valueOf(0));
		outbound_json.put("thirteen", Float.valueOf(0));
		outbound_json.put("fourteen", Float.valueOf(0));
		outbound_json.put("fifteen", Float.valueOf(0));
		outbound_json.put("sixteen", Float.valueOf(0));
		outbound_json.put("seventeen", Float.valueOf(0));
		outbound_json.put("eighteen", Float.valueOf(0));
		outbound_json.put("nineteen", Float.valueOf(0));
		outbound_json.put("twenty", Float.valueOf(0));
		outbound_json.put("total", Float.valueOf(0));
		
		Float outboundValue = Float.valueOf(0);
		
		List<Object[]> outboundList = outboundPeoplePaperService.getLineData();
		if(outboundList.size()>0) {
			for(Object[] lineData : outboundList) {
				if("100".equals(lineData[0].toString())) {
					outbound_json.put("eleven", Float.valueOf(lineData[1].toString()));
				}else if("101".equals(lineData[0].toString())) {
					outbound_json.put("twelve", Float.valueOf(lineData[1].toString()));
				}else if("102".equals(lineData[0].toString())) {
					outbound_json.put("thirteen", Float.valueOf(lineData[1].toString()));
				}else if("103".equals(lineData[0].toString())) {
					outbound_json.put("fourteen", Float.valueOf(lineData[1].toString()));
				}else if("104".equals(lineData[0].toString())) {
					outbound_json.put("fifteen", Float.valueOf(lineData[1].toString()));
				}else if("105".equals(lineData[0].toString())) {
					outbound_json.put("sixteen", Float.valueOf(lineData[1].toString()));
				}else if("106".equals(lineData[0].toString())) {
					outbound_json.put("seventeen", Float.valueOf(lineData[1].toString()));
				}else if("107".equals(lineData[0].toString())) {
					outbound_json.put("eighteen", Float.valueOf(lineData[1].toString()));
				}else if("108".equals(lineData[0].toString())) {
					outbound_json.put("nineteen", Float.valueOf(lineData[1].toString()));
				}else if("109".equals(lineData[0].toString())) {
					outbound_json.put("twenty", Float.valueOf(lineData[1].toString()));
				}
				outboundValue = outboundValue + Float.valueOf(lineData[1].toString());
			}
			outbound_json.put("total", outboundValue);
			sn_array.put(outbound_json);
		}
		
		JSONObject outNoCor_json = new JSONObject();
		outNoCor_json.put("name","Out bound 平均發表篇數");
		outNoCor_json.put("eleven", Float.valueOf(0));
		outNoCor_json.put("twelve", Float.valueOf(0));
		outNoCor_json.put("thirteen", Float.valueOf(0));
		outNoCor_json.put("fourteen", Float.valueOf(0));
		outNoCor_json.put("fifteen", Float.valueOf(0));
		outNoCor_json.put("sixteen", Float.valueOf(0));
		outNoCor_json.put("seventeen", Float.valueOf(0));
		outNoCor_json.put("eighteen", Float.valueOf(0));
		outNoCor_json.put("nineteen", Float.valueOf(0));
		outNoCor_json.put("twenty", Float.valueOf(0));
		outNoCor_json.put("total", Float.valueOf(0));
		
		Float outNoCorValue = Float.valueOf(0);
		
		List<Object[]> outNotCorList = outboundPeoplePaperNoCorService.getLineData();
		if(outNotCorList.size()>0) {
			for(Object[] lineData : outNotCorList) {
				if("100".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("eleven");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("eleven", (float)(Math.round(value*100))/100);
				}else if("101".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("twelve");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("twelve", (float)(Math.round(value*100))/100);
				}else if("102".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("thirteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("thirteen", (float)(Math.round(value*100))/100);
				}else if("103".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("fourteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("fourteen", (float)(Math.round(value*100))/100);
				}else if("104".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("fifteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("fifteen", (float)(Math.round(value*100))/100);
				}else if("105".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("sixteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("sixteen", (float)(Math.round(value*100))/100);
				}else if("106".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("seventeen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("seventeen", (float)(Math.round(value*100))/100);
				}else if("107".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("eighteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("eighteen", (float)(Math.round(value*100))/100);
				}else if("108".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("nineteen");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("nineteen", (float)(Math.round(value*100))/100);
				}else if("109".equals(lineData[0].toString())) {
					Float value = outbound_json.getFloat("twenty");
					value = value / Float.valueOf(lineData[1].toString());
					outNoCor_json.put("twenty", (float)(Math.round(value*100))/100);
				}
				outNoCorValue = outNoCorValue + Float.valueOf(lineData[1].toString());
			}
			Float outNoCorTotal = outNoCorValue / outbound_json.getFloat("total");
			outNoCor_json.put("total", (float)(Math.round(outNoCorTotal*100))/100);			
			sn_array.put(outNoCor_json);
		}
		
		
		listjson.put("formData", sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}