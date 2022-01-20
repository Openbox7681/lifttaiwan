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
import org.springframework.web.bind.annotation.ResponseBody;

import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.ArticleDataService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.PrivacyPageService;
import tw.gov.mohw.hisac.web.service.CookiePageService;
import tw.gov.mohw.hisac.web.service.VideoDataService;
import tw.gov.mohw.hisac.web.domain.ArticleData;
import tw.gov.mohw.hisac.web.domain.PrivacyPage;
import tw.gov.mohw.hisac.web.domain.CookiePage;

import tw.gov.mohw.hisac.web.domain.VideoData;

/**
 * 表單資料維護控制器
 */
@Controller
@RequestMapping(value = "/fontend/index", produces = "application/json; charset=utf-8")
public class fontend_IndexController extends BaseController {
	
	@Autowired
	private VideoDataService videoDataService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PrivacyPageService privacyPageService;
	@Autowired
	private CookiePageService cookiePageService;
	
	
	@RequestMapping(value = "/privacyPage/query/id", method = RequestMethod.POST)
	public @ResponseBody String IsExist(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject responseData = new JSONObject();

		
		if (privacyPageService.isExist(new Long(1))) {
			
			PrivacyPage privacyPage = privacyPageService.getDataById(new Long(1));
			
			
			responseData.put("Title", privacyPage.getTitle());
			responseData.put("Item1", privacyPage.getItem1());
			responseData.put("Item2_1", privacyPage.getItem2_1());
			responseData.put("Item2_2", privacyPage.getItem2_2());
			responseData.put("Item2_3", privacyPage.getItem2_3());
			responseData.put("Item2_4", privacyPage.getItem2_4());
			responseData.put("Item2_5", privacyPage.getItem2_5());
			
			responseData.put("Item3_1", privacyPage.getItem3_1());
			responseData.put("Item3_2", privacyPage.getItem3_2());
			

			responseData.put("Item4_1", privacyPage.getItem4_1());
			responseData.put("Item4_2", privacyPage.getItem4_2());
			responseData.put("Item4_3", privacyPage.getItem4_3());
			responseData.put("Item4_4", privacyPage.getItem4_4());

			responseData.put("Item5_1", privacyPage.getItem5_1());
		
			responseJson.put("data", responseData);
	
			responseJson.put("msg", true);
			responseJson.put("success", true);
		} else {
			responseJson.put("data", responseData);

			responseJson.put("msg", false);
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
	
	@RequestMapping(value = "/cookiePage/query/id", method = RequestMethod.POST)
	public @ResponseBody String IsCookiePageExist(Locale locale, HttpServletRequest request, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject responseData = new JSONObject();

		
		if (cookiePageService.isExist(new Long(1))) {
			
			CookiePage cookiePage = cookiePageService.getDataById(new Long(1));
			
			
			responseData.put("Title", cookiePage.getTitle());
			
		
			responseJson.put("data", responseData);
	
			responseJson.put("msg", true);
			responseJson.put("success", true);
		} else {
			responseJson.put("data", responseData);

			responseJson.put("msg", false);
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

	@RequestMapping(value = "/queryVideo", method = RequestMethod.POST)
	public String queryVideo(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<VideoData> vList = videoDataService.getList(json);

			JSONArray sn_array = new JSONArray();
			JSONArray tag_array = new JSONArray();
			if (vList != null)
				for (VideoData videoData : vList) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", videoData.getId());
					sn_json.put("Title", videoData.getTitle());
					sn_json.put("Date", sdf.format(videoData.getCreateTime()));
					sn_json.put("Img", videoData.getImg());
					sn_json.put("Url", videoData.getVideo_Url());
					sn_json.put("Description", videoData.getDescription());

					sn_array.put(sn_json);
					
					if(videoData.getTag().contains("，")) {
						String[] tag = videoData.getTag().split("，");
						for(int i=0; i<tag.length; i++) {
							JSONObject tag_json = new JSONObject();
							tag_json.put("Id", videoData.getId());
							tag_json.put("Tag", "#" + tag[i]);
							
							tag_array.put(tag_json);
						}
					}
				}
			listjson.put("tagtable", tag_array);
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/queryArticle", method = RequestMethod.POST)
	public String queryArticle(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<ArticleData> aList = articleDataService.getList(json);

			JSONArray sn_array = new JSONArray();
			JSONArray tag_array = new JSONArray();
			if (aList != null)
				for (ArticleData articleData : aList) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", articleData.getId());
					sn_json.put("Title", articleData.getTitle());
					sn_json.put("Date", sdf.format(articleData.getCreateTime()));
					sn_json.put("Img", articleData.getImg());
					sn_json.put("Description", articleData.getDescription());

					sn_array.put(sn_json);
					
					if(articleData.getTag().contains("，")) {
						String[] tag = articleData.getTag().split("，");
						for(int i=0; i<tag.length; i++) {
							JSONObject tag_json = new JSONObject();
							tag_json.put("Id", articleData.getId());
							tag_json.put("Tag", "#" + tag[i]);
							
							tag_array.put(tag_json);
						}
					}
				}
			listjson.put("tagtable", tag_array);
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
	
	@RequestMapping(value = "/queryPeopleMains", method = RequestMethod.POST)
	public String queryPeopleMains(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONObject obj = new JSONObject(json);
			String country = obj.isNull("country") == true ? null : obj.getString("country");
			String[] country_token = null;
			if(country != null && country.length()>0) {
				country_token = country.split(",");
			}
			
			JSONArray computer_array = new JSONArray();
			JSONArray health_array = new JSONArray();
			JSONArray war_array = new JSONArray();
			JSONArray green_array = new JSONArray();
			JSONArray life_array = new JSONArray();
			JSONArray other_array = new JSONArray();
			
			List<Object[]> countList = peopleMainsLiftService.getPeopleNum(country_token);
			if(countList!= null) {
				if(countList.size()>0) {
					for(Object[] data : countList) {
						JSONObject sn_json = new JSONObject();
						if("資訊及數位相關產業".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							computer_array.put(sn_json);
						}else if("臺灣精準健康戰略產業".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							health_array.put(sn_json);
						}else if("國防及戰略產業".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							war_array.put(sn_json);
						}else if("綠電及再生能源產業".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							green_array.put(sn_json);
						}else if("民生及戰備產業".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							life_array.put(sn_json);
						}else if("其他".equals(data[0].toString())) {
							sn_json.put("data", data[1].toString() + ":/" + data[2].toString() + "人");
							other_array.put(sn_json);
						}
					} 
				}
			}
			listjson.put("computerData", computer_array);
			listjson.put("healthData", health_array);
			listjson.put("warData", war_array);
			listjson.put("greenData", green_array);
			listjson.put("lifeData", life_array);
			listjson.put("otherData", other_array);
			
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}