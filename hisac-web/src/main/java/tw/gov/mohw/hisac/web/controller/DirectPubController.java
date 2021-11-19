package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/pub", produces = "application/json; charset=utf-8")
public class DirectPubController extends BaseController {

	/**
	 * Home Index
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/pub/index";
	}

	/**
	 * Home Index
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/pub/index";
	}

	/**
	 * News List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p01", method = RequestMethod.GET)
	public String p01(Locale locale, Model model) {
		return "/pub/p01";
	}

	/**
	 * Activity List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p02", method = RequestMethod.GET)
	public String p02(Locale locale, Model model) {
		return "/pub/p02";
	}

	/**
	 * QA List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p03", method = RequestMethod.GET)
	public String p03(Locale locale, Model model) {
		return "/pub/p03";
	}

	/**
	 * AnaManagement List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p04", method = RequestMethod.GET)
	public String p04(Locale locale, Model model) {
		return "/pub/p04";
	}

	/**
	 * WeaknessManagement List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p05", method = RequestMethod.GET)
	public String p05(Locale locale, Model model) {
		return "/pub/p05";
	}

	/**
	 * SecBuzzer List
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p06", method = RequestMethod.GET)
	public String p06(Locale locale, Model model) {
		return "/pub/p06";
	}

	/**
	 * 關於本系統
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p07", method = RequestMethod.GET)
	public String p07(Locale locale, Model model) {
		return "/pub/p07";
	}

	/**
	 * 組織架構
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p08", method = RequestMethod.GET)
	public String p08(Locale locale, Model model) {
		return "/pub/p08";
	}

	/**
	 * 何謂 ISAC
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p09", method = RequestMethod.GET)
	public String p09(Locale locale, Model model) {
		return "/pub/p09";
	}
	
	/**
	 * 儀表板
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p11", method = RequestMethod.GET)
	public String p11(Locale locale, Model model) {
		return "/pub/p11";
	}
	
	/**
	 * 情資分享
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p12", method = RequestMethod.GET)
	public String p12(Locale locale, Model model) {
		return "/pub/p12";
	}
	
	/**
	 * 勒索專區文章分享
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/p13", method = RequestMethod.GET)
	public String p13(Locale locale, Model model) {
		return "/pub/p13";
	}
	
	
	
}
