package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/inc", produces = "application/json; charset=utf-8")
public class DirectIncController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/inc/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/inc/index";
	}
	

	/**
	 * 事件管理系統
	 * 
	 * @param locale 
	 * 			Locale
	 * @param model 
	 * 			Model
	 * @return "/inc/i01"
	 */
	@RequestMapping(value = "/i01", method = RequestMethod.GET)
	public String i01(Locale locale, Model model) {
		return "/inc/i01";
	}
	
	
	/**
	 * 公開資訊管理
	 * 
	 * @param locale 
	 * 			Locale
	 * @param model 
	 * 			Model
	 * @return "/inc/i01"
	 */
	@RequestMapping(value = "/i02", method = RequestMethod.GET)
	public String i02(Locale locale, Model model) {
		return "/inc/i02";
	}

}
