package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.WebMessage;

@Controller
@RequestMapping(value = "/alt", produces = "application/json; charset=utf-8")
public class DirectAltController extends BaseController {

	/**
	 * Alt Index
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/alt/index";
	}

	/**
	 * Alt Index
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/alt/index";
	}

	/**
	 * 通報管理
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/a01", method = RequestMethod.GET)
	public String a01(Locale locale, Model model) {
		model.addAttribute("supportDocumentLinkTitle", WebMessage.parseMessage(resourceMessageService.getMessageValue("supportDocumentLinkTitle"), locale));
		model.addAttribute("supportDocumentLink", WebMessage.parseMessage(resourceMessageService.getMessageValue("supportDocumentLink"), locale));
		return "/alt/a01";
	}
	
	/**
	 * 通報管理報表
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/a02", method = RequestMethod.GET)
	public String a02(Locale locale, Model model) {
		return "/alt/a02";
	}
	
	
	/**
	 * 資安事件服務廠商
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/a03", method = RequestMethod.GET)
	public String a03(Locale locale, Model model) {
		return "/alt/a03";
	}
}
