package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SpDashboardDAO;
import tw.gov.mohw.hisac.web.domain.SpContactCountDashboard;
import tw.gov.mohw.hisac.web.domain.SpDashboard;
import tw.gov.mohw.hisac.web.domain.SpManagerCountDashboard;

/**
 * Dashboard資料維護服務
 */
@Service
public class SpDashboardService {
	@Autowired
	SpDashboardDAO spDashboardDAO;

	/**
	 * 取得DashboardInfo資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 統計資料
	 */
	public List<SpDashboard> getSpListInfo(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spDashboardDAO.getSpListInfo(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得DashboardPublic資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 統計資料
	 */
	public List<SpDashboard> getSpListPublic(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spDashboardDAO.getSpListPublic(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得DashboardMessage資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 統計資料
	 */
	public List<SpDashboard> getSpListMessage(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spDashboardDAO.getSpListMessage(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得DashboardNotification資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 統計資料
	 */
	public List<SpDashboard> getSpListNotification(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spDashboardDAO.getSpListNotification(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 取得會員管理者數資料
	 * 	
	 * @return 會員管理者數資料
	 */

	public List<SpManagerCountDashboard> getManager() {
		try {			
			return spDashboardDAO.getManager();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得會員聯絡人數資料
	 * 	
	 * @return 會員聯絡人數資料
	 */

	public List<SpContactCountDashboard> getContact() {
		try {			
			return spDashboardDAO.getContact();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 取得DashboardInformation資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 統計資料
	 */
	public List<SpDashboard> getInformation(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spDashboardDAO.getInformation(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
