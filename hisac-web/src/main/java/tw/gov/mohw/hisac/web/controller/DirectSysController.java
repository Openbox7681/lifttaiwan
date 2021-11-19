package tw.gov.mohw.hisac.web.controller;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.domain.Sso;
import tw.gov.mohw.hisac.web.service.ResourceMessageService;

@Controller
@RequestMapping(value = "/sys", produces = "application/json; charset=utf-8")
public class DirectSysController extends BaseController {
	@Autowired
	private ResourceMessageService resourceMessageService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "/sys/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(Locale locale, Model model) {
		return "/sys/index";
	}

	/**
	 * 清除資源檔快取
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/reload_resource", method = RequestMethod.GET)
	public String reload_resource(Locale locale, Model model) {
		long startTime = System.nanoTime();
		WebMessage.reloadMessage();
		resourceMessageService.loadResource(false);
		long endTime = System.nanoTime();
		model.addAttribute("processTime", (endTime - startTime) / 1e6);
		return "/sys/reload_resource";
	}

	/**
	 * 變更密碼
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/change_code", method = RequestMethod.GET)
	public String change_code(Locale locale, Model model) {
		return "/sys/change_code";
	}

	/**
	 * 滿意度調查
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/survey", method = RequestMethod.GET)
	public String survey(Locale locale, Model model) {
		return "/sys/survey";
	}
	
	/**
	 * ires 金鑰API申請調查
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/ires", method = RequestMethod.GET)
	public String ires(Locale locale, Model model) {
		return "/sys/ires";
	}

	
	

	/**
	 * 轉址服務
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @param system
	 *            系統名稱
	 * @return 轉址
	 */
	@RequestMapping(value = "/redirect/{system}", method = RequestMethod.GET)
	public String redirect(Locale locale, Model model, @PathVariable String system) {
		switch (system) {
			case "ires" :
				String code = WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID() + WebCrypto.generateUUID();
				Date expireTime = WebDatetime.addSeconds(null, 10);
				Sso sso = memberService.ssoCreate(code, getBaseMemberId(), expireTime);
				if (sso != null) {
					String iv = WebCrypto.getRandomCode(16);
					code = WebCrypto.aesEncrypt(resourceMessageService.getMessageValue("iresToken"), iv, code);
					if (code != null) {
						try {
							byte[] byteIv = iv.toString().getBytes(StandardCharsets.UTF_8.toString());
							iv = encoder.encodeToString(byteIv);
						} catch (Exception e) {
							iv = "";
							//e.printStackTrace();
						}
						try {
							byte[] byteCode = code.toString().getBytes(StandardCharsets.UTF_8.toString());
							code = encoder.encodeToString(byteCode);
						} catch (Exception e) {
							code = "";
							//e.printStackTrace();
						}
						if (!code.equals("") && !iv.equals("")) {
							model.asMap().clear();
							return "redirect:" + resourceMessageService.getMessageValue("iresSiteUrl") + "?g=" + iv + "&a=" + code;
						} else {
							model.asMap().clear();
							return "redirect:/pub/";
						}
					} else {
						model.asMap().clear();
						return "redirect:/pub/";
					}
				} else {
					model.asMap().clear();
					return "redirect:/pub/";
				}
			default :
				model.asMap().clear();
				return "redirect:/pub/";
		}
	}
	/**
	 * 變更個人單位資料
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/edit_org_profile", method = RequestMethod.GET)
	public String edit_org_profile(Locale locale, Model model) {
		return "/sys/edit_org_profile";
	}

	/**
	 * 變更個人資料
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
	public String edit_profile(Locale locale, Model model) {
		return "/sys/edit_profile";
	}
	
	/**
	 * 登入紀錄轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/login_history", method = RequestMethod.GET)
	public String login_history(Locale locale, Model model) {		
			return "/sys/login_history";
	}
	
	
	/**
	 * 登入紀錄轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/click_history", method = RequestMethod.GET)
	public String click_history(Locale locale, Model model) {		
			return "/sys/click_history";
	}

	/**
	 * 子系統管理轉址 Subsystem
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s01", method = RequestMethod.GET)
	public String s01(Locale locale, Model model) {
		return "/sys/s01";
	}

	/**
	 * Role管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s02", method = RequestMethod.GET)
	public String s02(Locale locale, Model model) {
		return "/sys/s02";
	}

	/**
	 * System_log管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s03", method = RequestMethod.GET)
	public String s03(Locale locale, Model model) {
		return "/sys/s03";
	}

	/**
	 * Alert Type管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s04", method = RequestMethod.GET)
	public String s04(Locale locale, Model model) {
		return "/sys/s04";
	}

	/**
	 * Member管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s05", method = RequestMethod.GET)
	public String s05(Locale locale, Model model) {
		return "/sys/s05";
	}

	/**
	 * 組織管理轉址 Org
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s06", method = RequestMethod.GET)
	public String s06(Locale locale, Model model) {
		return "/sys/s06";
	}

	/**
	 * 事件管理轉址 Org
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s07", method = RequestMethod.GET)
	public String s07(Locale locale, Model model) {
		return "/sys/s07";
	}

	/**
	 * 醫事機構代碼
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s08", method = RequestMethod.GET)
	public String s08(Locale locale, Model model) {
		return "/sys/s08";
	}

	/**
	 * 網站設定
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s09", method = RequestMethod.GET)
	public String s09(Locale locale, Model model) {
		return "/sys/s09";
	}

	/**
	 * 情資資料來源檔 information_source
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s10", method = RequestMethod.GET)
	public String s10(Locale locale, Model model) {
		return "/sys/s10";
	}

	/**
	 * 表單資料維護轉址 Form
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s11", method = RequestMethod.GET)
	public String s11(Locale locale, Model model) {
		return "/sys/s11";
	}

	/**
	 * 角色權限資料維護轉址 RoleForm
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s12", method = RequestMethod.GET)
	public String s12(Locale locale, Model model) {
		return "/sys/s12";
	}

	/**
	 * 網站統計表
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s13", method = RequestMethod.GET)
	public String s13(Locale locale, Model model) {
		return "/sys/s13";
	}

	/**
	 * 事件管理轉址 Org 會員機構審核方式管理 RoleForm
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s14", method = RequestMethod.GET)
	public String s14(Locale locale, Model model) {
		return "/sys/s14";
	}

	/**
	 * MemberSignApply管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s15", method = RequestMethod.GET)
	public String s15(Locale locale, Model model) {
		return "/sys/s15";
	}


	/**
	 * MemberSignApply管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s16", method = RequestMethod.GET)
	public String s16(Locale locale, Model model) {
		return "/sys/s16";
	}
	
	/**
	 * MemberReport管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s17", method = RequestMethod.GET)
	public String s17(Locale locale, Model model) {
		return "/sys/s17";
	}
	
	
	/**
	 * HandleInformationManagement管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s18", method = RequestMethod.GET)
	public String s18(Locale locale, Model model) {
		return "/sys/s18";
	}
	
	
	/**
	 * HandleInformationManagement管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s19", method = RequestMethod.GET)
	public String s19(Locale locale, Model model) {
		return "/sys/s19";
	}

	/**
	 * IP黑名單匯入轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s20", method = RequestMethod.GET)
	public String s20(Locale locale, Model model) {		
			return "/sys/s20";
	}

	/**
	 * 最新消息管理資料維護轉址 NewsManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s31", method = RequestMethod.GET)
	public String s31(Locale locale, Model model) {
		return "/sys/s31";
	}

	/**
	 * 最新消息管理資料維護轉址 NewsModify
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s31a", method = RequestMethod.GET)
	public String s31a(Locale locale, Model model) {
		return "/sys/s31a";
	}

	/**
	 * 最新活動管理資料維護轉址 ActivityManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s32", method = RequestMethod.GET)
	public String s32(Locale locale, Model model) {
		return "/sys/s32";
	}

	/**
	 * 最新活動管理資料維護轉址 ActivityManagement
	 * 
	 * @param locala
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s32a", method = RequestMethod.GET)
	public String s32a(Locale locale, Model model) {
		return "/sys/s32a";
	}

	/**
	 * SecBuzzer資料維護轉址 SecManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s60", method = RequestMethod.GET)
	public String s60(Locale locale, Model model) {
		return "/sys/s60";
	}

	/**
	 * 常見問題類別管理維護轉址 QAManagementGroup
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s33", method = RequestMethod.GET)
	public String s33(Locale locale, Model model) {
		return "/sys/s33";
	}

	/**
	 * 認識本系統維護轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s34", method = RequestMethod.GET)
	public String s34(Locale locale, Model model) {
		return "/sys/s34";
	}

	/**
	 * 組織架構維護轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s35", method = RequestMethod.GET)
	public String s35(Locale locale, Model model) {
		return "/sys/s35";
	}

	/**
	 * 何謂ISAC維護轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s36", method = RequestMethod.GET)
	public String s36(Locale locale, Model model) {
		return "/sys/s36";
	}

	/**
	 * 常見問題管理維護轉址 QAManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s37", method = RequestMethod.GET)
	public String s37(Locale locale, Model model) {
		return "/sys/s37";
	}

	/**
	 * 最新消息類別管理維護轉址 NewsManagementGroup
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s38", method = RequestMethod.GET)
	public String s38(Locale locale, Model model) {
		return "/sys/s38";
	}

	/**
	 * 資安資訊情報管理維護轉址 AnaManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s39", method = RequestMethod.GET)
	public String s39(Locale locale, Model model) {
		return "/sys/s39";
	}

	/**
	 * 資安資訊情報管理維護轉址 AnaManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s39a", method = RequestMethod.GET)
	public String s39a(Locale locale, Model model) {
		return "/sys/s39a";
	}

	/**
	 * 軟體弱點漏洞通告管理維護轉址 WeaknessManagement
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s40", method = RequestMethod.GET)
	public String s40(Locale locale, Model model) {
		return "/sys/s40";
	}

	/**
	 * 相關連結管理維護轉址 Links
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s41", method = RequestMethod.GET)
	public String s41(Locale locale, Model model) {
		return "/sys/s41";
	}
	
	/**
	 * 情資分享管理維護轉址 Links
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s42", method = RequestMethod.GET)
	public String s42(Locale locale, Model model) {
		return "/sys/s42";
	}
	
	/**
	 * MemberReport管理轉址 會員統計報表(test)
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s43", method = RequestMethod.GET)
	public String s43(Locale locale, Model model) {
		return "/sys/s43";
	}
	
	/**
	 * ReportSchedule管理轉址 報表排程紀錄(test)
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s44", method = RequestMethod.GET)
	public String s44(Locale locale, Model model) {
		return "/sys/s44";
	}
	
	/**
	 * ReportSchedule管理轉址 勒索專區管理轉址
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s45", method = RequestMethod.GET)
	public String s45(Locale locale, Model model) {
		return "/sys/s45";
	}
	
	/**
	 * 勒索專區異動管理資料維護轉址 ActivityManagement
	 * 
	 * @param locala
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s45a", method = RequestMethod.GET)
	public String s45a(Locale locale, Model model) {
		return "/sys/s45a";
	}
	
	/**
	 * 資通安全責任等級歷次資料資料維護轉址 ActivityManagement
	 * 
	 * @param locala
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s46", method = RequestMethod.GET)
	public String s46(Locale locale, Model model) {
		return "/sys/s46";
	}
	
	/**
	 * Ci會員等級歷次資料資料維護轉址 ActivityManagement
	 * 
	 * @param locala
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/s47", method = RequestMethod.GET)
	public String s47(Locale locale, Model model) {
		return "/sys/s47";
	}


	
	

	/**
	 * virusTotalCount
	 * 
	 * @param locale
	 *            Locale
	 * @param model
	 *            Model
	 * @return 轉址
	 */
	@RequestMapping(value = "/virusTotalCount", method = RequestMethod.GET)
	public String virusTotalCount(Locale locale, Model model) {
		JSONArray arr = new JSONArray();
				
		model.addAttribute("virusTotalCount", arr.toString());
		return "/sys/virusTotalCount";
	}

}
