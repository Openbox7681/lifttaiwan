package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.service.InformationExchangeNisacService;

@Controller
@RequestMapping(value = "/cyb", produces = "application/json; charset=utf-8")
public class DirectCybController extends BaseController {

	@Autowired
	InformationExchangeNisacService informationExchangeNisacService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/cyb/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/cyb/index";
	}
	

	/**
	 * 資安資訊分享系統
	 * 
	 * @param locale 
	 * 			Locale
	 * @param model 
	 * 			Model
	 * @return "/cyb/c01"
	 */
	@RequestMapping(value = "/c01", method = RequestMethod.GET)
	public String c01(Locale locale, Model model) {
		return "/cyb/c01";
	}
	
	

	/**
	 * 資安資訊分享系統
	 * 
	 * @param locale 
	 * 			Locale
	 * @param model 
	 * 			Model
	 * @return "/cyb/c01a"
	 */
	@RequestMapping(value = "/c01a", method = RequestMethod.GET)
	public String c01a(Locale locale, Model model) {
		return "/cyb/c01a";
	}

	/**
	 * 資安情資統計表
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c03"
	 */
	@RequestMapping(value = "/c03", method = RequestMethod.GET)
	public String c03(Locale locale, Model model) {
		return "/cyb/c03";
	}
	
	/**
	 * IP黑名單查詢
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c04"
	 */
	@RequestMapping(value = "/c04", method = RequestMethod.GET)
	public String c04(Locale locale, Model model) {		
			return "/cyb/c04";
	}
	
	
	/**
	 * VADER
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c05"
	 */
	@RequestMapping(value = "/c05", method = RequestMethod.GET)
	public String c05(Locale locale, Model model) {		
			return "/cyb/c05";
	}
	
	/**
	 * VADER Detail
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c05d"
	 */
	@RequestMapping(value = "/c05d", method = RequestMethod.GET)
	public String c05d(Locale locale, Model model) {		
			return "/cyb/c05d";
	}
	
	
	/**
	 * virus total
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c06"
	 */
	@RequestMapping(value = "/c06", method = RequestMethod.GET)
	public String c06(Locale locale, Model model) {		
			return "/cyb/c06";
	}
	
	/**
	 * 情資 API Test
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/cyb/c02"
	 */
	@RequestMapping(value = "/c02", method = RequestMethod.GET)
	public String c02(Locale locale, Model model) {
		informationExchangeNisacService.importFromNisac();
		return "/cyb/c02";
	}
}
