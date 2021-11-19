package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tw.gov.mohw.hisac.web.service.NotificationExchangeNcertService;
import tw.gov.mohw.hisac.web.service.NotificationExchangeNcertService.NCert;
import tw.gov.mohw.hisac.web.service.SmsService;

@Controller
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class DirectIndexController extends BaseController {
	@Autowired
	SmsService smsService;
	@Autowired
	NotificationExchangeNcertService notificationExchangeNcertService;

	/**
	 * 首頁登入
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Login(Locale locale, Model model) {
		//notificationExchangeNcertService.test();
		
		// 介接測試用
		//notificationExchangeNcertService.exportToNcert("9b2a312dfc1e4439a4c8aaa968e1bb1c");
		
		//1、2級結報
		//notificationExchangeNcertService.exportToNcert("9cb5974083e04227860cf40b38dedca0");
		// eb36760ffc144bd4822b7093e8714eaa // 3-4
		// eca918f965234141ab3dcbb853312412 // 1-2
		
		
		//3、4級結報
		//notificationExchangeNcertService.exportToNcert("dd2e77e3a45d46fcb159c40c34e5d7a4");
		
		return "index";
	}

	/**
	 * 首頁登入
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "index";
	}

	/**
	 * 聯絡我們
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/contact_us", method = RequestMethod.GET)
	public String ContactUs(Locale locale, Model model) {
		return "contact_us";
	}

	/**
	 * 關於我們
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String About(Locale locale, Model model) {
		return "about";
	}

	/**
	 * 忘記密碼
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String ForgotCode(Locale locale, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/pub/";
		}
		return "forgot";
	}

	/**
	 * 忘記密碼
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String ResetCode(Locale locale, HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/pub/";
		}
		// String code = request.getQueryString();
		// if (code == null || code.isEmpty() || code.length() != 128) {
		// return "redirect:/pub/";
		// }
		// code = myFilter.filterString(code);
		// model.addAttribute("code", code);
		return "reset";
	}

	/**
	 * 會員申請
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String SignUp(Locale locale, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/pub/";
		}
		return "sign_up";
	}

	/**
	 * 最新消息
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String News(Locale locale, Model model) {
		return "news";
	}

	/**
	 * 活動訊息
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String Activity(Locale locale, Model model) {
		return "activity";
	}
	
	/**
	 * QA
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/qa", method = RequestMethod.GET)
	public String QA(Locale locale, Model model) {
		return "qa";
	}

	
	/**
	 * QA
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/malware", method = RequestMethod.GET)
	public String Malware(Locale locale, Model model) {
		return "malware";
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
	@RequestMapping(value = "/information_share", method = RequestMethod.GET)
	public String InformationShare(Locale locale, Model model) {
		return "information_share";
	}
	/**
	 * 權限不足
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return Url
	 */
	@RequestMapping(value = "/forbidden", method = RequestMethod.GET)
	public String Forbidden(Locale locale, Model model) {
		throw new ForbiddenException();
	}

	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	private class ForbiddenException extends RuntimeException {
	}

	/**
	 * 權限不足
	 * 
	 * @param ra
	 *            RedirectAttributes
	 * @return Url
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String AccesssDenied(RedirectAttributes ra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/pub/";
		}
		return "index";
	}

	/**
	 * 獲取網頁錯誤頁面
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 錯誤頁面
	 */
	@RequestMapping(value = "/error_page", method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest request) {
		ModelAndView errorPage = new ModelAndView("error_page");
		String errorMsg = "";
		String errorDescription = "";
		int httpErrorCode = getErrorCode(request);

		switch (httpErrorCode) {
			case 400 : {
				errorMsg = "Http Error Code: 400. Bad Request";
				errorDescription = "伺服器收到無效語法，無法理解請求。";
				break;
			}
			case 401 : {
				errorMsg = "Http Error Code: 401. Unauthorized";
				errorDescription = "需要授權以回應請求。";
				break;
			}
			case 403 : {
				errorMsg = "Http Error Code: 403. Forbidden";
				errorDescription = "用戶端並無訪問權限。";
				break;
			}
			case 404 : {
				errorMsg = "Http Error Code: 404. Resource Not Found";
				errorDescription = "伺服器找不到請求的資源。";
				break;
			}
			case 405 : {
				errorMsg = "Http Error Code: 405. Method Not Allowed";
				errorDescription = "請求方法被禁用或不可用。";
				break;
			}
			case 500 : {
				errorMsg = "Http Error Code: 500. Internal Server Error";
				errorDescription = "服務內部發生錯誤。";
				break;
			}
		}
		errorPage.addObject("errorMsg", errorMsg);
		errorPage.addObject("errorDescription", errorDescription);
		return errorPage;
	}
}
