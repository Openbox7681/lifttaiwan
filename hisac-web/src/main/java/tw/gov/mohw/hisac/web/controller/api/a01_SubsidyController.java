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
import tw.gov.mohw.hisac.web.service.PaperCorLiftService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.SnaTopInfoLiftService;

@Controller
@RequestMapping(value = "/alt/common", produces = "application/json; charset=utf-8")
public class a01_SubsidyController extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PaperMainsLiftService paperMainsLiftService;
	@Autowired
	private PaperCorLiftService paperCorLiftService;
	@Autowired
	private SnaTopInfoLiftService snaTopInfoLiftService;

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
	
	@RequestMapping(value = "/getC3data", method = RequestMethod.POST)
	public String getC3data(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		
		JSONObject total_json = new JSONObject();

		JSONObject obj1 = new JSONObject(json);
		
		System.out.println(obj1);
		JSONArray classSubList = obj1.isNull("classSubList") == true ? null : obj1.getJSONArray("classSubList");
		
		List<Object[]> c3datas = snaTopInfoLiftService.getC3DataByCondition(classSubList);
		
		total_json.put("name", "頂尖學者鏈結人數");
		total_json.put("open", Long.valueOf(0));
		total_json.put("pi", Long.valueOf(0));
		total_json.put("short", Long.valueOf(0));
		total_json.put("dragon", Long.valueOf(0));
		total_json.put("policy", Long.valueOf(0));
		total_json.put("horse", Long.valueOf(0));
		total_json.put("total", Long.valueOf(0));
		
		
		if(c3datas != null) {
			for(Object[] c3data : c3datas) {
				if(sn_array.toString().contains(c3data[0].toString())) {
					for(int i=0; i<sn_array.length(); i++) {
						JSONObject obj = (JSONObject) sn_array.get(i);
						if(obj.getString("name").equals(c3data[0].toString())) {
							
							Long count = obj.getLong("total") + Long.valueOf(c3data[2].toString());
							obj.put("total", count);
							
							Long total = total_json.getLong("total") + Long.valueOf(c3data[2].toString());
							total_json.put("total", total);
							
							if("盤古開天".equals(c3data[1].toString())) {
								obj.put("open", c3data[2]);
								Long value = total_json.getLong("open") + Long.valueOf(c3data[2].toString());
								total_json.put("open", value);
							}else if("國合PI".equals(c3data[1].toString())) {
								obj.put("pi", c3data[2]);
								Long value = total_json.getLong("pi") + Long.valueOf(c3data[2].toString());
								total_json.put("pi", value);
							}else if("短期訪問學者".equals(c3data[1].toString())) {
								obj.put("short", c3data[2]);
								Long value = total_json.getLong("short") + Long.valueOf(c3data[2].toString());
								total_json.put("short", value);
							}else if("龍門計畫主持人".equals(c3data[1].toString())) {
								obj.put("dragon", c3data[2]);
								Long value = total_json.getLong("dragon") + Long.valueOf(c3data[2].toString());
								total_json.put("dragon", value);
							}else if("政策邀訪學者".equals(c3data[1].toString())) {
								obj.put("policy", c3data[2]);
								Long value = total_json.getLong("policy") + Long.valueOf(c3data[2].toString());
								total_json.put("policy", value);
							}else if("千里馬申請人".equals(c3data[1].toString())) {
								obj.put("horse", c3data[2]);
								Long value = total_json.getLong("horse") + Long.valueOf(c3data[2].toString());
								total_json.put("horse", value);
							}
						}
					}			
					
				}
				else {
					JSONObject sn_json = new JSONObject();
					
					sn_json.put("name", c3data[0].toString());
					sn_json.put("open", Long.valueOf(0));
					sn_json.put("pi", Long.valueOf(0));
					sn_json.put("short", Long.valueOf(0));
					sn_json.put("dragon", Long.valueOf(0));
					sn_json.put("policy", Long.valueOf(0));
					sn_json.put("horse", Long.valueOf(0));
					sn_json.put("total", Long.valueOf(c3data[2].toString()));
					
					Long total = total_json.getLong("total") + Long.valueOf(c3data[2].toString());
					total_json.put("total", total);
					
					if("盤古開天".equals(c3data[1].toString())) {
						sn_json.put("open", c3data[2]);
						Long count = total_json.getLong("open") + Long.valueOf(c3data[2].toString());
						total_json.put("open", count);
					}else if("國合PI".equals(c3data[1].toString())) {
						sn_json.put("pi", c3data[2]);
						Long count = total_json.getLong("pi") + Long.valueOf(c3data[2].toString());
						total_json.put("pi", count);
					}else if("短期訪問學者".equals(c3data[1].toString())) {
						sn_json.put("short", c3data[2]);
						Long count = total_json.getLong("short") + Long.valueOf(c3data[2].toString());
						total_json.put("short", count);
					}else if("龍門計畫主持人".equals(c3data[1].toString())) {
						sn_json.put("dragon", c3data[2]);
						Long count = total_json.getLong("dragon") + Long.valueOf(c3data[2].toString());
						total_json.put("dragon", count);
					}else if("政策邀訪學者".equals(c3data[1].toString())) {
						sn_json.put("policy", c3data[2]);
						Long count = total_json.getLong("policy") + Long.valueOf(c3data[2].toString());
						total_json.put("policy", count);
					}else if("千里馬申請人".equals(c3data[1].toString())) {
						sn_json.put("horse", c3data[2]);
						Long count = total_json.getLong("horse") + Long.valueOf(c3data[2].toString());
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

	
	@RequestMapping(value = "/getB1data", method = RequestMethod.POST)
	public String getB1data(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		
		JSONObject obj1 = new JSONObject(json);
		JSONArray classSubList = obj1.isNull("classSubList") == true ? null : obj1.getJSONArray("classSubList");
		JSONArray countryList = obj1.isNull("countryList") == true ? null : obj1.getJSONArray("countryList");
		
		
		List<Object[]> alldatas = peopleMainsLiftService.getDataByCondition( classSubList,  countryList);
		List<Object[]> peoplePapers = peopleMainsLiftService.getPeoplePaperByCondition( classSubList,  countryList);
		List<Object[]> peoplePaper16_19s = peopleMainsLiftService.getPeoplePaperByCondition2016_2019( classSubList,  countryList);
		List<Object[]> peoplePaperBefores = peopleMainsLiftService.getPeoplePaperByConditionBefore( classSubList,  countryList);
		List<Object[]> peoplePaperAfters = peopleMainsLiftService.getPeoplePaperByConditionAfter( classSubList,  countryList);
		List<Object[]> peoplePaperCors = peopleMainsLiftService.getPeoplePaperByConditionCor( classSubList,  countryList);
		if(alldatas != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "總輔助人數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  alldata :   alldatas) {
				if("盤古開天".equals(alldata[1].toString())) {
					sn_json.put("open", alldata[0]);
				}else if("國合PI".equals(alldata[1].toString())) {
					sn_json.put("pi", alldata[0]);
				}else if("短期訪問學者".equals(alldata[1].toString())) {
					sn_json.put("short", alldata[0]);
				}else if("龍門計畫主持人".equals(alldata[1].toString())) {
					sn_json.put("dragon", alldata[0]);
				}else if("政策邀訪學者".equals(alldata[1].toString())) {
					sn_json.put("policy", alldata[0]);
				}else if("千里馬申請人".equals(alldata[1].toString())) {
					sn_json.put("horse", alldata[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}
		if(peoplePapers != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "論文發表總數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  peoplePaper :   peoplePapers) {
				if("盤古開天".equals(peoplePaper[1].toString())) {
					sn_json.put("open", peoplePaper[0]);
				}else if("國合PI".equals(peoplePaper[1].toString())) {
					sn_json.put("pi", peoplePaper[0]);
				}else if("短期訪問學者".equals(peoplePaper[1].toString())) {
					sn_json.put("short", peoplePaper[0]);
				}else if("龍門計畫主持人".equals(peoplePaper[1].toString())) {
					sn_json.put("dragon", peoplePaper[0]);
				}else if("政策邀訪學者".equals(peoplePaper[1].toString())) {
					sn_json.put("policy", peoplePaper[0]);
				}else if("千里馬申請人".equals(peoplePaper[1].toString())) {
					sn_json.put("horse", peoplePaper[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}
		
		if(peoplePaper16_19s != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "2016-2019發表篇數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  peoplePaper16_19 :   peoplePaper16_19s) {
				if("盤古開天".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("open", peoplePaper16_19[0]);
				}else if("國合PI".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("pi", peoplePaper16_19[0]);
				}else if("短期訪問學者".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("short", peoplePaper16_19[0]);
				}else if("龍門計畫主持人".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("dragon", peoplePaper16_19[0]);
				}else if("政策邀訪學者".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("policy", peoplePaper16_19[0]);
				}else if("千里馬申請人".equals(peoplePaper16_19[1].toString())) {
					sn_json.put("horse", peoplePaper16_19[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}
		
		if(peoplePaperBefores != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "輔助前論文數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  peoplePaperBefore :   peoplePaperBefores) {
				if("盤古開天".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("open", peoplePaperBefore[0]);
				}else if("國合PI".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("pi", peoplePaperBefore[0]);
				}else if("短期訪問學者".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("short", peoplePaperBefore[0]);
				}else if("龍門計畫主持人".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("dragon", peoplePaperBefore[0]);
				}else if("政策邀訪學者".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("policy", peoplePaperBefore[0]);
				}else if("千里馬申請人".equals(peoplePaperBefore[1].toString())) {
					sn_json.put("horse", peoplePaperBefore[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}
		
		if(peoplePaperAfters != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "輔助後論文數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  peoplePaperAfter :   peoplePaperAfters) {
				if("盤古開天".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("open", peoplePaperAfter[0]);
				}else if("國合PI".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("pi", peoplePaperAfter[0]);
				}else if("短期訪問學者".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("short", peoplePaperAfter[0]);
				}else if("龍門計畫主持人".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("dragon", peoplePaperAfter[0]);
				}else if("政策邀訪學者".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("policy", peoplePaperAfter[0]);
				}else if("千里馬申請人".equals(peoplePaperAfter[1].toString())) {
					sn_json.put("horse", peoplePaperAfter[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}
		
		if(peoplePaperCors != null) {
			JSONObject sn_json = new JSONObject();
			sn_json.put("name", "國際合著篇數");
			sn_json.put("total", Long.valueOf(0));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));		
			for (Object[]  peoplePaperCor :   peoplePaperCors) {
				if("盤古開天".equals(peoplePaperCor[1].toString())) {
					sn_json.put("open", peoplePaperCor[0]);
				}else if("國合PI".equals(peoplePaperCor[1].toString())) {
					sn_json.put("pi", peoplePaperCor[0]);
				}else if("短期訪問學者".equals(peoplePaperCor[1].toString())) {
					sn_json.put("short", peoplePaperCor[0]);
				}else if("龍門計畫主持人".equals(peoplePaperCor[1].toString())) {
					sn_json.put("dragon", peoplePaperCor[0]);
				}else if("政策邀訪學者".equals(peoplePaperCor[1].toString())) {
					sn_json.put("policy", peoplePaperCor[0]);
				}else if("千里馬申請人".equals(peoplePaperCor[1].toString())) {
					sn_json.put("horse", peoplePaperCor[0]);
				}	
			}
			Long total = sn_json.getLong("horse") + sn_json.getLong("policy") 
					+ sn_json.getLong("dragon") + sn_json.getLong("short")
					+ sn_json.getLong("pi") +  sn_json.getLong("open") ;
			sn_json.put("total", total);
			sn_array.put(sn_json);
		}

		


		

		
		
		
		
		
		
		listjson.put("formData",sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
		
		
	}

	
	@RequestMapping(value = "/getAllCountry", method = RequestMethod.POST)
	public String getAllCountry(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject responsejson = new JSONObject();

		JSONArray sn_array = new JSONArray();

		List<Object[]> formList = peopleMainsLiftService.getAllCountry();
		if(formList != null) {
			for(Object[] formData : formList) {
				JSONObject listjson = new JSONObject();

				String countryName = formData[0].toString();
				
				listjson.put("Name", countryName);
				
				listjson.put("Flag", false);
				sn_array.put(listjson);
			}
		}

		
		responsejson.put("Data",sn_array);

		
		model.addAttribute("json", responsejson.toString());
		return "msg";
	}
	@RequestMapping(value = "/queryCountryData", method = RequestMethod.POST)
	public String queryCountryData(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject total_json = new JSONObject();
		
		JSONObject obj1 = new JSONObject(json);
		
		Long startYear = obj1.isNull("startYear") == true ? 0 : obj1.getLong("startYear");
		Long endYear = obj1.isNull("endYear") == true ? 0 : obj1.getLong("endYear");
		
		int classMainOption = obj1.isNull("classMainOption") == true ? 0 : obj1.getInt("classMainOption");
		
		JSONArray classSubList = obj1.isNull("classSubList") == true ? null : obj1.getJSONArray("classSubList");
		JSONArray countryList = obj1.isNull("countryList") == true ? null : obj1.getJSONArray("countryList");
		
		for(int i=0; i<countryList.length(); i++) {

			JSONObject obj = (JSONObject) countryList.get(i);
			
			if (obj.getBoolean("Flag") == true) {

			
			JSONObject sn_json = new JSONObject();

			sn_json.put("name", obj.getString("Name"));
			sn_json.put("open", Long.valueOf(0));
			sn_json.put("pi", Long.valueOf(0));
			sn_json.put("short", Long.valueOf(0));
			sn_json.put("dragon", Long.valueOf(0));
			sn_json.put("policy", Long.valueOf(0));
			sn_json.put("horse", Long.valueOf(0));
			sn_json.put("total", Long.valueOf(0));
			
			sn_array.put(sn_json);
			}

			

		}

		
		
		
		total_json.put("name", "合計");
		total_json.put("open", Long.valueOf(0));
		total_json.put("pi", Long.valueOf(0));
		total_json.put("short", Long.valueOf(0));
		total_json.put("dragon", Long.valueOf(0));
		total_json.put("policy", Long.valueOf(0));
		total_json.put("horse", Long.valueOf(0));
		total_json.put("total", Long.valueOf(0));
		
		List<Object[]> formList = peopleMainsLiftService.getCountryDataByCondition(startYear, endYear, classSubList, countryList,classMainOption);
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
		
		ArrayList<String> countryData = new ArrayList<String>();
		
		ArrayList<Long> openData = new ArrayList<Long>();
		ArrayList<Long> piData = new ArrayList<Long>();
		ArrayList<Long> shortData = new ArrayList<Long>();
		ArrayList<Long> dragonData = new ArrayList<Long>();
		ArrayList<Long> policyData = new ArrayList<Long>();
		ArrayList<Long> horseData = new ArrayList<Long>();
		
		for(int i=0; i<sn_array.length(); i++) {
			
			JSONObject obj = (JSONObject) sn_array.get(i);

			if(!obj.getString("name").equals("合計")) {
				countryData.add(obj.getString("name"));
				openData.add(obj.getLong("open"));
				piData.add(obj.getLong("pi"));
				shortData.add(obj.getLong("short"));
				dragonData.add(obj.getLong("dragon"));
				policyData.add(obj.getLong("policy"));
				horseData.add(obj.getLong("horse"));
			}
		}
		
		listjson.put("countryData", countryData);
		listjson.put("piData", piData);
		listjson.put("openData", openData);
		listjson.put("shortData", shortData);
		listjson.put("dragonData", dragonData);
		listjson.put("policyData", policyData);
		listjson.put("horseData", horseData);
		
		sn_array.put(total_json);
		listjson.put("formData",sn_array);
		
		model.addAttribute("json", listjson.toString());
		return "msg";
		

		
	}

		

	
	
	@RequestMapping(value = "/queryClassSubData", method = RequestMethod.POST)
	public String queryClassSubData(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		JSONArray sn_array = new JSONArray();
		JSONObject total_json = new JSONObject();
		
		JSONObject obj1 = new JSONObject(json);
		
		Long startYear = obj1.isNull("startYear") == true ? 0 : obj1.getLong("startYear");
		Long endYear = obj1.isNull("endYear") == true ? 0 : obj1.getLong("endYear");
		
		int classMainOption = obj1.isNull("classMainOption") == true ? 0 : obj1.getInt("classMainOption");
		
		JSONArray classSubList = obj1.isNull("classSubList") == true ? null : obj1.getJSONArray("classSubList");
		JSONArray countryList = obj1.isNull("countryList") == true ? null : obj1.getJSONArray("countryList");
		
		total_json.put("classSub", "全部");
		total_json.put("classMain", "全部");
		total_json.put("open", Long.valueOf(0));
		total_json.put("pi", Long.valueOf(0));
		total_json.put("short", Long.valueOf(0));
		total_json.put("dragon", Long.valueOf(0));
		total_json.put("policy", Long.valueOf(0));
		total_json.put("horse", Long.valueOf(0));
		total_json.put("total", Long.valueOf(0));
		total_json.put("IsAll", false);
		
		

		
		List<Object[]> formList = peopleMainsLiftService.getClassSubDataByCondition(startYear, endYear, classSubList, countryList,classMainOption);
		if(formList != null) {
			for(Object[] formData : formList) {
				
				//先判對副領域是否存在
				if(sn_array.toString().contains(formData[0].toString())) {
					for(int i=0; i<sn_array.length(); i++) {
						JSONObject obj = (JSONObject) sn_array.get(i);
						if(obj.getString("classSub").equals(formData[0].toString())) {
							Long count = obj.getLong("total") + Long.valueOf(formData[3].toString());
							obj.put("total", count);
							Long total = total_json.getLong("total") + Long.valueOf(formData[3].toString());
							total_json.put("total", total);
							if("盤古開天".equals(formData[2].toString())) {
								obj.put("open", formData[3]);
								Long value = total_json.getLong("open") + Long.valueOf(formData[3].toString());
								total_json.put("open", value);
							}else if("國合PI".equals(formData[2].toString())) {
								obj.put("pi", formData[3]);
								Long value = total_json.getLong("pi") + Long.valueOf(formData[3].toString());
								total_json.put("pi", value);
							}else if("短期訪問學者".equals(formData[2].toString())) {
								obj.put("short", formData[3]);
								Long value = total_json.getLong("short") + Long.valueOf(formData[3].toString());
								total_json.put("short", value);
							}else if("龍門計畫主持人".equals(formData[2].toString())) {
								obj.put("dragon", formData[3]);
								Long value = total_json.getLong("dragon") + Long.valueOf(formData[3].toString());
								total_json.put("dragon", value);
							}else if("政策邀訪學者".equals(formData[2].toString())) {
								obj.put("policy", formData[3]);
								Long value = total_json.getLong("policy") + Long.valueOf(formData[3].toString());
								total_json.put("policy", value);
							}else if("千里馬申請人".equals(formData[2].toString())) {
								obj.put("horse", formData[3]);
								Long value = total_json.getLong("horse") + Long.valueOf(formData[3].toString());
								total_json.put("horse", value);
							}
							
						}
					}

				}//沒有存在則新增副領域初始化資料
				else {
					JSONObject sn_json = new JSONObject();

					//資料庫新增資料
					sn_json.put("classMain", formData[1].toString());
					sn_json.put("classSub", formData[0].toString());
					sn_json.put("open", Long.valueOf(0));
					sn_json.put("pi", Long.valueOf(0));
					sn_json.put("short", Long.valueOf(0));
					sn_json.put("dragon", Long.valueOf(0));
					sn_json.put("policy", Long.valueOf(0));
					sn_json.put("horse", Long.valueOf(0));
					sn_json.put("total", Long.valueOf(formData[3].toString()));
					sn_json.put("IsAll", false);

						
					//加入最下列總和資料
					
					Long total = total_json.getLong("total") + Long.valueOf(formData[3].toString());
					total_json.put("total", total);
					
					if("盤古開天".equals(formData[2].toString())) {
						sn_json.put("open", formData[3]);
						Long count = total_json.getLong("open") + Long.valueOf(formData[3].toString());
						total_json.put("open", count);
					}else if("國合PI".equals(formData[2].toString())) {
						sn_json.put("pi", formData[3]);
						Long count = total_json.getLong("pi") + Long.valueOf(formData[3].toString());
						total_json.put("pi", count);
					}else if("短期訪問學者".equals(formData[2].toString())) {
						sn_json.put("short", formData[3]);
						Long count = total_json.getLong("short") + Long.valueOf(formData[3].toString());
						total_json.put("short", count);
					}else if("龍門計畫主持人".equals(formData[2].toString())) {
						sn_json.put("dragon", formData[3]);
						Long count = total_json.getLong("dragon") + Long.valueOf(formData[3].toString());
						total_json.put("dragon", count);
					}else if("政策邀訪學者".equals(formData[2].toString())) {
						sn_json.put("policy", formData[3]);
						Long count = total_json.getLong("policy") + Long.valueOf(formData[3].toString());
						total_json.put("policy", count);
					}else if("千里馬申請人".equals(formData[2].toString())) {
						sn_json.put("horse", formData[3]);
						Long count = total_json.getLong("horse") + Long.valueOf(formData[3].toString());
						total_json.put("horse", count);
					}

					
					sn_array.put(sn_json);

				}		
				
			}	
		}
		sn_array.put(total_json);
		
		JSONObject info_json = new JSONObject();
		//資訊及數位相關產業 總合計算
		info_json.put("classMain", "資訊及數位相關產業");
		info_json.put("classSub", "全部");
		info_json.put("open", Long.valueOf(0));
		info_json.put("pi", Long.valueOf(0));
		info_json.put("short", Long.valueOf(0));
		info_json.put("dragon", Long.valueOf(0));
		info_json.put("policy", Long.valueOf(0));
		info_json.put("horse", Long.valueOf(0));
		info_json.put("total", Long.valueOf(0));
		info_json.put("IsAll", true);
		
		
		JSONObject healthy_json = new JSONObject();
		
		//臺灣精準健康戰略產業 總合計算
		healthy_json.put("classMain", "臺灣精準健康戰略產業");
		healthy_json.put("classSub", "全部");
		healthy_json.put("open", Long.valueOf(0));
		healthy_json.put("pi", Long.valueOf(0));
		healthy_json.put("short", Long.valueOf(0));
		healthy_json.put("dragon", Long.valueOf(0));
		healthy_json.put("policy", Long.valueOf(0));
		healthy_json.put("horse", Long.valueOf(0));
		healthy_json.put("total", Long.valueOf(0));
		healthy_json.put("IsAll", true);
		
		//國防及戰略產業
		
		JSONObject safe_json = new JSONObject();
		safe_json.put("classMain", "國防及戰略產業");
		safe_json.put("classSub", "全部");
		safe_json.put("open", Long.valueOf(0));
		safe_json.put("pi", Long.valueOf(0));
		safe_json.put("short", Long.valueOf(0));
		safe_json.put("dragon", Long.valueOf(0));
		safe_json.put("policy", Long.valueOf(0));
		safe_json.put("horse", Long.valueOf(0));
		safe_json.put("total", Long.valueOf(0));
		safe_json.put("IsAll", true);
		
		//民生及戰備產業

		JSONObject livelihood_json =  new JSONObject();
		livelihood_json.put("classMain", "民生及戰備產業");
		livelihood_json.put("classSub", "全部");
		livelihood_json.put("open", Long.valueOf(0));
		livelihood_json.put("pi", Long.valueOf(0));
		livelihood_json.put("short", Long.valueOf(0));
		livelihood_json.put("dragon", Long.valueOf(0));
		livelihood_json.put("policy", Long.valueOf(0));
		livelihood_json.put("horse", Long.valueOf(0));
		livelihood_json.put("total", Long.valueOf(0));
		livelihood_json.put("IsAll", true);
		
		//綠電及再生能源產業
		JSONObject power_json =  new JSONObject();
		power_json.put("classMain", "綠電及再生能源產業");
		power_json.put("classSub", "全部");
		power_json.put("open", Long.valueOf(0));
		power_json.put("pi", Long.valueOf(0));
		power_json.put("short", Long.valueOf(0));
		power_json.put("dragon", Long.valueOf(0));
		power_json.put("policy", Long.valueOf(0));
		power_json.put("horse", Long.valueOf(0));
		power_json.put("total", Long.valueOf(0));
		power_json.put("IsAll", true);
		
		//其他
		JSONObject other_json = new JSONObject();
		other_json.put("classMain", "其他");
		other_json.put("classSub", "全部");
		other_json.put("open", Long.valueOf(0));
		other_json.put("pi", Long.valueOf(0));
		other_json.put("short", Long.valueOf(0));
		other_json.put("dragon", Long.valueOf(0));
		other_json.put("policy", Long.valueOf(0));
		other_json.put("horse", Long.valueOf(0));
		other_json.put("total", Long.valueOf(0));
		other_json.put("IsAll", true);
		
		
		
		ArrayList<String> classData = new ArrayList<String>();
		
		ArrayList<Long> openData = new ArrayList<Long>();
		ArrayList<Long> piData = new ArrayList<Long>();
		ArrayList<Long> shortData = new ArrayList<Long>();
		ArrayList<Long> dragonData = new ArrayList<Long>();
		ArrayList<Long> policyData = new ArrayList<Long>();
		ArrayList<Long> horseData = new ArrayList<Long>();

		
		
		for(int i=0; i<sn_array.length(); i++) {
			
			JSONObject obj = (JSONObject) sn_array.get(i);

			if(!obj.getString("classMain").equals("全部")) {
				classData.add(obj.getString("classSub"));
				openData.add(obj.getLong("open"));
				piData.add(obj.getLong("pi"));
				shortData.add(obj.getLong("short"));
				dragonData.add(obj.getLong("dragon"));
				policyData.add(obj.getLong("policy"));
				horseData.add(obj.getLong("horse"));
			}
			
			
			
			
			
			
			if(obj.getString("classMain").equals("資訊及數位相關產業")) {
				
				Long openCount = info_json.getLong("open") + obj.getLong("open");
				Long piCount = info_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = info_json.getLong("short") + obj.getLong("short");
				Long dragonCount = info_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = info_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = info_json.getLong("horse") + obj.getLong("horse");

				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;
				
				info_json.put("open", openCount);
				info_json.put("pi", piCount);
				info_json.put("short", shortCount);
				info_json.put("dragon", dragonCount);	
				info_json.put("policy", policyCount);
				info_json.put("horse", horseCount);
				info_json.put("total", total);
			}else if (obj.getString("classMain").equals("臺灣精準健康戰略產業")){
				
				Long openCount = healthy_json.getLong("open") + obj.getLong("open");
				Long piCount = healthy_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = healthy_json.getLong("short") + obj.getLong("short");
				Long dragonCount = healthy_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = healthy_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = healthy_json.getLong("horse") + obj.getLong("horse");
				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;

				healthy_json.put("open", openCount);
				healthy_json.put("pi", piCount);
				healthy_json.put("short", shortCount);
				healthy_json.put("dragon", dragonCount);	
				healthy_json.put("policy", policyCount);
				healthy_json.put("horse", horseCount);
				healthy_json.put("total", total);
				
			}else if ( obj.getString("classMain").equals("國防及戰略產業")) {
				Long openCount = safe_json.getLong("open") + obj.getLong("open");
				Long piCount = safe_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = safe_json.getLong("short") + obj.getLong("short");
				Long dragonCount = safe_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = safe_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = safe_json.getLong("horse") + obj.getLong("horse");
				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;
				
				safe_json.put("open", openCount);
				safe_json.put("pi", piCount);
				safe_json.put("short", shortCount);
				safe_json.put("dragon", dragonCount);	
				safe_json.put("policy", policyCount);
				safe_json.put("horse", horseCount);
				safe_json.put("total", total);
			}else if ( obj.getString("classMain").equals("民生及戰備產業")) {
				
				
				Long openCount = livelihood_json.getLong("open") + obj.getLong("open");
				Long piCount = livelihood_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = livelihood_json.getLong("short") + obj.getLong("short");
				Long dragonCount = livelihood_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = livelihood_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = livelihood_json.getLong("horse") + obj.getLong("horse");
				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;
				
				livelihood_json.put("open", openCount);
				livelihood_json.put("pi", openCount);
				livelihood_json.put("short", openCount);
				livelihood_json.put("dragon", openCount);
				livelihood_json.put("policy", openCount);
				livelihood_json.put("horse", openCount);
				livelihood_json.put("total", total);

			}else if (obj.getString("classMain").equals("綠電及再生能源產業") ) {
				
				Long openCount = power_json.getLong("open") + obj.getLong("open");
				Long piCount = power_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = power_json.getLong("short") + obj.getLong("short");
				Long dragonCount = power_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = power_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = power_json.getLong("horse") + obj.getLong("horse");
				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;
				power_json.put("open", openCount);
				power_json.put("pi", openCount);
				power_json.put("short", openCount);
				power_json.put("dragon", openCount);
				power_json.put("policy", openCount);
				power_json.put("horse", openCount);
				power_json.put("total", total);	
			}
			else if (obj.getString("classMain").equals("其他")) {
				Long openCount = other_json.getLong("open") + obj.getLong("open");
				Long piCount = other_json.getLong("pi") + obj.getLong("pi");
				Long shortCount = other_json.getLong("short") + obj.getLong("short");
				Long dragonCount = other_json.getLong("dragon") + obj.getLong("dragon");
				Long policyCount = other_json.getLong("policy") + obj.getLong("policy");
				Long horseCount = other_json.getLong("horse") + obj.getLong("horse");
				Long total = openCount + piCount + shortCount + dragonCount +policyCount  +horseCount;
				other_json.put("open", openCount);
				other_json.put("pi", openCount);
				other_json.put("short", openCount);
				other_json.put("dragon", openCount);
				other_json.put("policy", openCount);
				other_json.put("horse", openCount);
				other_json.put("total", total);	
			}
		}
		
//		switch (classMainOption) {
//			case 0 :
//				sn_array.put(info_json);
//				sn_array.put(healthy_json);
//				sn_array.put(safe_json);
//				sn_array.put(livelihood_json);
//				sn_array.put(power_json);
//				sn_array.put(other_json);
//				break;
//			case 1 :
//				sn_array.put(info_json);
//				break;
//			case 2 :
//				sn_array.put(healthy_json);
//				break;
//			case 3 :
//				sn_array.put(safe_json);
//				break;
//			case 4 :
//				sn_array.put(livelihood_json);
//				break;
//			case 5 :
//				sn_array.put(power_json);
//				break;
//			case 6 :
//				sn_array.put(other_json);
//				break;
//		}
//		
		
		
		listjson.put("formData",sn_array);
		listjson.put("classData", classData);
		listjson.put("piData", piData);
		listjson.put("openData", openData);
		listjson.put("shortData", shortData);
		listjson.put("dragonData", dragonData);
		listjson.put("policyData", policyData);
		listjson.put("horseData", horseData);

		
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

			
		
		

		
		
		
		
		
	}
	
	
	
	
