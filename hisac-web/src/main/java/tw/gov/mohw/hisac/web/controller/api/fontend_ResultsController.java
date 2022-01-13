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
import tw.gov.mohw.hisac.web.dao.PaperFullnameLiftDAO;
import tw.gov.mohw.hisac.web.dao.PaperKeywordLiftDAO;
import tw.gov.mohw.hisac.web.dao.PaperMainsLiftDAO;
import tw.gov.mohw.hisac.web.dao.PeopleMainsLiftDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.ArticleDataService;
import tw.gov.mohw.hisac.web.service.PaperMainsLiftService;
import tw.gov.mohw.hisac.web.service.PeopleMainsLiftService;
import tw.gov.mohw.hisac.web.service.VideoDataService;
import tw.gov.mohw.hisac.web.domain.ArticleData;
import tw.gov.mohw.hisac.web.domain.PaperFullnameLift;
import tw.gov.mohw.hisac.web.domain.PaperKeywordLift;
import tw.gov.mohw.hisac.web.domain.PaperMainsLift;
import tw.gov.mohw.hisac.web.domain.PeopleMainsLift;
import tw.gov.mohw.hisac.web.domain.VideoData;

/**
 * 表單資料維護控制器
 */
@Controller
@RequestMapping(value = "/fontend/results", produces = "application/json; charset=utf-8")
public class fontend_ResultsController extends BaseController {

	@Autowired
	private PeopleMainsLiftService peopleMainsLiftService;
	@Autowired
	private PeopleMainsLiftDAO peopleMainsLiftDAO;
	@Autowired
	private PaperMainsLiftDAO paperMainsLiftDAO;
	@Autowired
	private PaperFullnameLiftDAO paperFullnameLiftDAO;
	@Autowired
	private PaperKeywordLiftDAO paperKeywordLiftDAO;
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String queryPeopleMains(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject listjson = new JSONObject();
		//if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			JSONArray sn_array = new JSONArray();
			JSONArray tag_array = new JSONArray();
			JSONObject obj = new JSONObject(json);
			String country = obj.isNull("country") == true ? null : obj.getString("country");
			String classSub = obj.isNull("classSub") == true ? null : obj.getString("classSub");
			String[] country_token = null;
			String[] class_token = null;
			if(country != null && country.length()>0) {
				country_token = country.split(",");
			}
			if(classSub != null && classSub.length()>0) {
				class_token = classSub.split(",");
			}
			
			List<PeopleMainsLift> peopleList = peopleMainsLiftService.getResults(json,country_token,class_token);
			if(peopleList!= null && peopleList.size()>0) {
				for(PeopleMainsLift people : peopleList) {
					JSONObject paper_json = new JSONObject();
					paper_json.put("start", 0);
					paper_json.put("maxRows", 3);
					paper_json.put("p_id", people.getP_id());
					
					List<PaperMainsLift> paperList = paperMainsLiftDAO.getList(paper_json);
					if(paperList != null && paperList.size()>0) {
						for(PaperMainsLift paper : paperList) {
							JSONObject sn_json = new JSONObject();
							JSONObject data_json = new JSONObject();
							data_json.put("paper_SerialNumber", paper.getPaper_SerialNumber());
							sn_json.put("paper_SerialNumber", paper.getPaper_SerialNumber());
							sn_json.put("title", paper.getPaperTitle());
							
							List<PaperFullnameLift> nameList = paperFullnameLiftDAO.getList(data_json);
							if(nameList != null && nameList.size()>0) {
								String author = "";
								for(int i=0; i<nameList.size(); i++) {
									PaperFullnameLift name = (PaperFullnameLift) nameList.get(i);
									if(i==0) {
										author = name.getFullName();
									}else {
										author = author + "," +name.getFullName();
									}
								}
								sn_json.put("author", author);
							}
							
							List<PaperKeywordLift> keyList = paperKeywordLiftDAO.getList(data_json);
							if(keyList != null && keyList.size()>0) {
								
								for(PaperKeywordLift key : keyList) {
									JSONObject tag_json = new JSONObject();
									tag_json.put("tag", "#" + key.getKeyword());
									tag_json.put("paper_SerialNumber", key.getPaper_SerialNumber());
									tag_array.put(tag_json);
								}
							}
							sn_array.put(sn_json);
						}
					}
				}
			}
//			JSONObject size = new JSONObject(json);
//			size.put("maxRows", Long.valueOf(0));
//			size.put("start", Long.valueOf(0));
//			listjson.put("total", peopleMainsLiftDAO.getMechanism(size,country_token).size());
			listjson.put("datatable", sn_array);
			listjson.put("tagtable", tag_array);
			
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
//		} else {
//			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
//		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}
}