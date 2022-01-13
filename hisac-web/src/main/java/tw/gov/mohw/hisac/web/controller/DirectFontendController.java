package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value = "/institution", method = RequestMethod.GET)
	public String institution(Locale locale, Model model) {
		return "/fontend/institution";
	}
	@RequestMapping(value = "/scholar", method = RequestMethod.GET)
	public String scholar(Locale locale, Model model) {
		return "/fontend/scholar";
	}
	@RequestMapping(value = "/cooperate", method = RequestMethod.GET)
	public String cooperate(Locale locale, Model model) {
		return "/fontend/cooperate";
	}
	@RequestMapping(value = "/six", method = RequestMethod.GET)
	public String six(Locale locale, Model model) {
		return "/fontend/six";
	}
	@RequestMapping(value = "/mechanism/{country}", method = RequestMethod.GET)
	public String mechanism(@PathVariable("country") String country, Locale locale, Model model) {
		return "/fontend/mechanism";
	}
	@RequestMapping(value = "/results/{country}", method = RequestMethod.GET)
	public String results(@PathVariable("country") String country, Locale locale, Model model) {
		return "/fontend/results";
	}
}
