
package tw.gov.mohw.hisac.web.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tw.gov.mohw.hisac.web.dao.OrgReportMemberLogDAO;


import tw.gov.mohw.hisac.web.domain.OrgReportMemberLog;;



/**
 * 會員統計報表結果排程序(機構)
 */
@Service
public class OrgReportMemberLogService {
	final static Logger logger = LoggerFactory.getLogger(OrgReportMemberLogService.class);

	@Autowired
	OrgReportMemberLogDAO orgReportMemberLogDAO;

	
	/**
	 * 刪除會員統計報表紀錄
	 * 
	 */
	
	public boolean delete(Long id) {
		try {
			OrgReportMemberLog entity = orgReportMemberLogDAO.get(id);
			orgReportMemberLogDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 取得機構報告紀錄資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 機構報告紀錄資料
	 */
	public List<OrgReportMemberLog> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgReportMemberLogDAO.getList(obj);
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
			return orgReportMemberLogDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 取得報告資料
	 * 
	 * @param json
	 *            查詢總合後的機構會員登入次數資料
	 * @return 總合後的機構會員登入次數資料
	 */
	public List<Object[]> getMemberSigninCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgReportMemberLogDAO.getMemberSigninCount(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
	
	

}
