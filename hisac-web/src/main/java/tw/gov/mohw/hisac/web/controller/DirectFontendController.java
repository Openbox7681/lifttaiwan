package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/fontend", produces = "application/json; charset=utf-8")
public class DirectFontendController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/fontend/index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/fontend/index";
	}
	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	public String plan(Locale locale, Model model) {
		return "/fontend/plan";
	}
	@RequestMapping(value = "/intInfo", method = RequestMethod.GET)
	public String intInfo(Locale locale, Model model) {
		return "/fontend/intInfo";
	}
	@RequestMapping(value = "/intCoop", method = RequestMethod.GET)
	public String intCoop(Locale locale, Model model) {
		return "/fontend/intCoop";
	}
	@RequestMapping(value = "/videoInfo", method = RequestMethod.GET)
	public String videoInfo(Locale locale, Model model) {
		return "/fontend/videoInfo";
	}
}
