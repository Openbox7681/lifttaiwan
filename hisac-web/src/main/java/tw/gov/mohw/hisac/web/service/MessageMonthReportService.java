package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MessageMonthReportDAO;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReport;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReportList;

/**
 * 警訊月報表服務
 */
@Service
public class MessageMonthReportService {
	@Autowired
	MessageMonthReportDAO messageWeekReportDAO;

	/**
	 * 取得警訊月報表資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊月報表資料
	 */

	public List<SpMessageMonthReport> getListCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageWeekReportDAO.getListCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊月報表資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊月報表資料
	 */

	public List<SpMessageMonthReportList> getReportList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageWeekReportDAO.getReportList(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
