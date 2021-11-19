
package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.ReportDAO;

/**
 * 會員統計報表排程統計
 */
@Service
public class ReportService {
	final static Logger logger = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	ReportDAO reportDAO;

	/**
	 * 會員統計報表排程統計
	 * 
	 */
	public void schedule() {
		try {			 
			reportDAO.schedule();			
		} catch (Exception e) {
			//e.printStackTrace();			
		}
	}
	
	/**
	 * 取得報告資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報告資料
	 */
	public List<Object[]> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return reportDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	

	/**
	 * 取得報告資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報告資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return reportDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 取得文章下載附件筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 文章下載附件筆數
	 */
	public long getDownAttachListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return reportDAO.getDownAttachListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	
	
	/**
	 * 取得報告資料
	 * 
	 * @param json
	 *            查詢前十點擊detail
	 * @return 前十點擊detail
	 */
	public List<Object[]> getTop10Detail(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return reportDAO.getTop10Detail(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
