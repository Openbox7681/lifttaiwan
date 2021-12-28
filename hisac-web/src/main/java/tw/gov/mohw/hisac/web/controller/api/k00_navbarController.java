package tw.gov.mohw.hisac.web.controller.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value = "/kin/common", produces = "application/json; charset=utf-8")
public class k00_navbarController extends BaseController {

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
}