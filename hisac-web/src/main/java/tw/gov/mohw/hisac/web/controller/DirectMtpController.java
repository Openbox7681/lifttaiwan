package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mtp", produces = "application/json; charset=utf-8")
public class DirectMtpController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/mtp/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/mtp/index";
	}

	/**
	 * 資安研究計畫管理 Mtp
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m01"
	 */
	@RequestMapping(value = "/m01", method = RequestMethod.GET)
	public String m01(Locale locale, Model model) {
		return "/kin/k01";
	}

	/**
	 * 資安研究計畫會員群組轉址 MtpGroup
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m02"
	 */
	@RequestMapping(value = "/m02", method = RequestMethod.GET)
	public String m02(Locale locale, Model model) {
		return "/mtp/m02";
	}

	/**
	 * 資安研究計畫會員群組會員機構 NotGroupMember
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m03"
	 */
	@RequestMapping(value = "/m03", method = RequestMethod.GET)
	public String m03(Locale locale, Model model) {
		return "/mtp/m03";
	}
	
	/**
	 * 檔案上傳
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m04"
	 */
	@RequestMapping(value = "/m04", method = RequestMethod.GET)
	public String m04(Locale locale, Model model) {
		return "/mtp/m04";
	}
	
	/**
	 * 資安維護稽核管理 Mtp
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m05"
	 */
	@RequestMapping(value = "/m05", method = RequestMethod.GET)
	public String m05(Locale locale, Model model) {
		return "/mtp/m05";
	}
	/**
	 * 資安維護稽核資料上傳
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m06"
	 */
	@RequestMapping(value = "/m06", method = RequestMethod.GET)
	public String m06(Locale locale, Model model) {
		return "/mtp/m06";
	}
	/**
	 * 資安維護稽核委員資料上傳
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m07"
	 */
	@RequestMapping(value = "/m07", method = RequestMethod.GET)
	public String m07(Locale locale, Model model) {
		return "/mtp/m07";
	}
	/**
	 * 資安維護稽核委員資料上傳
	 * 
	 * @param locale
	 * 			Locale
	 * @param model
	 * 			Model
	 * @return "/mtp/m08"
	 */
	@RequestMapping(value = "/m08", method = RequestMethod.GET)
	public String m08(Locale locale, Model model) {
		return "/mtp/m08";
	}

}
