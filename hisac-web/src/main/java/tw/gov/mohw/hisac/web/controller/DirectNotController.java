package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/not", produces = "application/json; charset=utf-8")
public class DirectNotController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/not/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/not/index";
	}

	/**
	 * 警訊管理 Not
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n01"
	 */
	@RequestMapping(value = "/n01", method = RequestMethod.GET)
	public String n01(Locale locale, Model model) {
		return "/not/n01";
	}

	/**
	 * 警訊會員群組轉址 NotGroup
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n02"
	 */
	@RequestMapping(value = "/n02", method = RequestMethod.GET)
	public String n02(Locale locale, Model model) {
		return "/not/n02";
	}


	/**
	 * 警訊周報表
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n03"
	 */
	@RequestMapping(value = "/n03", method = RequestMethod.GET)
	public String n03(Locale locale, Model model) {
		return "/not/n03";
	}

	/**
	 * 警訊月報表
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n04"
	 */
	@RequestMapping(value = "/n04", method = RequestMethod.GET)
	public String n04(Locale locale, Model model) {
		return "/not/n04";
	}


	/**
	 * 警訊會員群組會員機構 NotGroupMember
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n05"
	 */
	@RequestMapping(value = "/n05", method = RequestMethod.GET)
	public String n05(Locale locale, Model model) {
		return "/not/n05";
	}
	

	/**
	 * 新增修改警訊管理 Not
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/not/n01_add"
	 */
	@RequestMapping(value = "/n01_add", method = RequestMethod.GET)
	public String n01_add(Locale locale, Model model) {
		return "/not/n01_add";
	}
}
