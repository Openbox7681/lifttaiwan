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
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.ArticleDataService;
import tw.gov.mohw.hisac.web.service.VideoDataService;
import tw.gov.mohw.hisac.web.domain.ArticleData;
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
}