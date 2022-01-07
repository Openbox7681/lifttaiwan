package tw.gov.mohw.hisac.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/copyPrivacy", produces = "application/json; charset=utf-8")
public class DirectCopyPrivacyController extends BaseController {

	@RequestMapping(value = "/copyright", method = RequestMethod.GET)
	public String plan(Locale locale, Model model) {
		return "/copyPrivacy/copyright";
	}
	@RequestMapping(value = "/privacy", method = RequestMethod.GET)
	public String intInfo(Locale locale, Model model) {
		return "/copyPrivacy/privacy";
	}
}
