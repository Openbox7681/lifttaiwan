package tw.gov.mohw.hisac.web.controller.api;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.service.NotificationService;

/**
 * 通報統計表控制器
 */
@Controller
@RequestMapping(value = "/alt/api", produces = "application/json; charset=utf-8")
public class a02_NotificationReportController extends BaseController {

	@Autowired
	private NotificationService notificationService;

	private String targetControllerName = "alt";
	private String targetActionName = "a02";
	/**
	 * 取得通報資料API
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param json
	 *            查詢條件
	 * @return 通報資料
	 */
	@RequestMapping(value = "/a02/query", method = RequestMethod.POST)
	public String Query(Locale locale, HttpServletRequest request, Model model, @RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		// 指定 PostType 為 5: [通報單狀態]
		// 1: 編輯中
		// 2: 通報審核中
		// 3: 處理中
		// 4: 通報結案審核中
		// 5: 已結案
		// 6: 已銷案

		obj.put("StatusReport", 5);
		json = obj.toString();

		JSONObject listjson = new JSONObject();
		if (menuService.getReadPermission(getBaseMemberId(), targetControllerName, targetActionName)) {
			List<Notification> notifications = notificationService.getList(json);
			listjson.put("total", notificationService.getListSize(json));

			JSONArray sn_array = new JSONArray();
			if (notifications != null)
				for (Notification notification : notifications) {
					JSONObject sn_json = new JSONObject();
					sn_json.put("Id", notification.getId());
					sn_json.put("CeffectLevel", notification.getCeffectLevel()); // 機密性
					sn_json.put("IeffectLevel", notification.getIeffectLevel()); // 完整性
					sn_json.put("AeffectLevel", notification.getAeffectLevel()); // 可用性

					// 0: 無需通報
					// 1: 1級
					// 2: 2級
					// 3: 3級
					// 4: 4級

					sn_json.put("EventType", notification.getEventType()); // 事故分類
					// 1: 網頁攻擊,2: 非法入侵,3: 阻斷服務,4: 設備異常,5: 其他

					// 1,事故分類-網頁攻擊
					sn_json.put("IsEventType1Opt1", notification.getIsEventType1Opt1()); // 網頁置換
					sn_json.put("IsEventType1Opt2", notification.getIsEventType1Opt2()); // 惡意留言
					sn_json.put("IsEventType1Opt3", notification.getIsEventType1Opt3()); // 惡意網頁
					sn_json.put("IsEventType1Opt4", notification.getIsEventType1Opt4()); // 釣魚網頁
					sn_json.put("IsEventType1Opt5", notification.getIsEventType1Opt5()); // 網頁木馬
					sn_json.put("IsEventType1Opt6", notification.getIsEventType1Opt6()); // 網站個資外洩

					// 2,事故分類-非法入侵
					sn_json.put("IsEventType2Opt1", notification.getIsEventType2Opt1()); // 系統遭入侵
					sn_json.put("IsEventType2Opt2", notification.getIsEventType2Opt2()); // 植入惡意程式
					sn_json.put("IsEventType2Opt3", notification.getIsEventType2Opt3()); // 異常連線
					sn_json.put("IsEventType2Opt4", notification.getIsEventType2Opt4()); // 發送垃圾郵件
					sn_json.put("IsEventType2Opt5", notification.getIsEventType2Opt5()); // 資料外洩

					// 3,事故分類-阻斷服務
					sn_json.put("IsEventType3Opt1", notification.getIsEventType3Opt1()); // 服務中斷
					sn_json.put("IsEventType3Opt2", notification.getIsEventType3Opt2()); // 效能降低

					// 4,事故分類-設備異常
					sn_json.put("IsEventType4Opt1", notification.getIsEventType4Opt1()); // 設備毀損
					sn_json.put("IsEventType4Opt2", notification.getIsEventType4Opt2()); // 電力異常
					sn_json.put("IsEventType4Opt3", notification.getIsEventType4Opt3()); // 網路服務中斷
					sn_json.put("IsEventType4Opt4", notification.getIsEventType4Opt4()); // 設備遺失

					// 事故發生原因
					sn_json.put("Finish1Reason", notification.getFinish5Reason()); // 網頁攻擊,1:
																					// 作業系統漏洞,2:
																					// 弱密碼,3:
																					// 應用程式漏洞,4:
																					// 網站設計不當,5:
																					// 人為疏失,6:
																					// 其他
					sn_json.put("Finish2Reason", notification.getFinish5Reason()); // 非法入侵,1:
																					// 社交工程
																					// ,2:
																					// 作業系統漏洞,3:
																					// 弱密碼,4:
																					// 應用程式漏洞,5:
																					// 網站設計不當,6:
																					// 其他
					sn_json.put("Finish4Reason", notification.getFinish5Reason()); // 設備異常,1:
																					// 設定錯誤,2:
																					// 設備毀損,3:
																					// 系統遭入侵,4:
																					// 電力供應異常,5:
																					// 其他
					sn_json.put("Finish5Reason", notification.getFinish5Reason()); // 設備遺失
																					// ,1:
																					// 社交工程,2:
																					// 作業系統漏洞,3:
																					// 弱密碼,4:
																					// 應用程式漏洞,5:
																					// 網站設計不當,6:
																					// 人為疏失
																					// 7:
																					// 設定錯誤,8:
																					// 設備毀損,9:
																					// 系統遭入侵,10:
																					// 電力供應異常,11:
																					// 其他

					sn_array.put(sn_json);
				}
			listjson.put("datatable", sn_array);
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
		} else {
			systemLogService.insert(baseControllerName, baseActionName, json, SystemLogVariable.Action.Read, SystemLogVariable.Status.Deny, getBaseIpAddress(), getBaseMemberAccount());
		}
		model.addAttribute("json", listjson.toString());
		return "msg";
	}

}