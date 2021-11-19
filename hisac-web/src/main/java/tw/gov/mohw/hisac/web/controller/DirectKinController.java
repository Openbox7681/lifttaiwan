package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/kin", produces = "application/json; charset=utf-8")
public class DirectKinController extends BaseController {

	/**
	 * 法遵稽核事務 轉址 
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/"
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/kin/index";
	}

	/**
	 * 法遵稽核事務 轉址 
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/index"
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/kin/index";
	}

	/**
	 * 法遵稽核機構管理 轉址 
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/k00"
	 */
	@RequestMapping(value = "/k00", method = RequestMethod.GET)
	public String k00(Locale locale, Model model) {
		return "/kin/k00";
	}
	
	/**
	 * 法遵稽核管理 轉址 
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/k01"
	 */
	@RequestMapping(value = "/k01", method = RequestMethod.GET)
	public String k01(Locale locale, Model model) {
		return "/kin/k01";
	}

	/**
	 * 稽核前訪談調查格式檔案上傳 轉址 
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/k02"
	 */
	@RequestMapping(value = "/k02", method = RequestMethod.GET)
	public String k02(Locale locale, Model model) {
		return "/kin/k02";
	}

	/**
	 * 法遵稽核作業
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/k03"
	 */
	@RequestMapping(value = "/k03", method = RequestMethod.GET)
	public String k03(Locale locale, Model model) {
		return "/kin/k03";
	}

	/**
	 * 稽核委員作業
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/kin/k04"
	 */
	@RequestMapping(value = "/k04", method = RequestMethod.GET)
	public String k04(Locale locale, Model model) {
		return "/kin/k04";
	}
	
}
