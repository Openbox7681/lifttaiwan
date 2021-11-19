package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MessageWeekReportDAO;
import tw.gov.mohw.hisac.web.domain.SpMessageWeekReport;

/**
 * 警訊週報表服務
 */
@Service
public class MessageWeekReportService {
	@Autowired
	MessageWeekReportDAO messageWeekReportDAO;

	/**
	 * 取得警訊週報表資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊月報表資料
	 */

	public List<SpMessageWeekReport> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageWeekReportDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
