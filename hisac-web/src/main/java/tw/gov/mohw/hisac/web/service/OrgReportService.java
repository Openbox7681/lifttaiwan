
package tw.gov.mohw.hisac.web.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.OrgReportDAO;
import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.domain.OrgReport;
import tw.gov.mohw.hisac.web.domain.SpOrgReportResult;


/**
 * 會員統計報表結果排程序(機構)
 */
@Service
public class OrgReportService {
	final static Logger logger = LoggerFactory.getLogger(OrgReportService.class);

	@Autowired
	OrgReportDAO orgReportDAO;

	/**
	 * 會員統計報表排程統計(機構) 執行方法
	 * 
	 */
	public JSONObject schedule(String json) {
		
			JSONObject obj = new JSONObject(json);
			return orgReportDAO.schedule(obj);			
		
	}
	

	/**
	 * 刪除會員統計報表資料
	 * 
	 */
	
	public boolean delete(Long id) {
		try {
			OrgReport entity = orgReportDAO.get(id);
			orgReportDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 取得機構報告資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 機構報告資料
	 */
	public List<OrgReport> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgReportDAO.getList(obj);
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
			return orgReportDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 取得報告資料
	 * 
	 * @param json
	 *            查詢總合後的機構點擊資料
	 * @return 總合後的機構點擊資料
	 */
	public List<Object[]> getSumCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgReportDAO.getSumCount(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 取得報告資料
	 * 
	 * @param json
	 *            查詢總合後的機構點擊資料
	 * @return 總合後的機構點擊資料
	 */
	
	public List<SpOrgReportResult> getSumResult(String json) {
		
		try {			
			JSONObject obj = new JSONObject(json);

			return orgReportDAO.getSumResult(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	/**
	 * 取得會員登入數資料
	 * 
	 * @param json
	 *            取得org report 並計算出會員登入數
	 * @return 會員登入數資料
	 */
	public int getMemberLogCount(String sdate, String edate , Long orgId) {
		
		Set<String> memberIdSet = new HashSet<String>();
		
		List<OrgReport> orgReports = orgReportDAO.getOrgReportByOrgId(sdate , edate , orgId);
		
		
		if(orgReports!= null) {
			for (OrgReport orgreport : orgReports) {
				String memberLog = orgreport.getMemberLog().trim();
				String[] memberIds = memberLog.split(",");
				for (String memberId :memberIds ) {
					memberIdSet.add(memberId);
				}
			}
			return memberIdSet.size();
	}
		else {
			return 0;
		}
	}
	
	
	/**
	 * 利用報告產生時間判斷報表是否存在
	 * 
	 * @param reportCreateTime
	 *            報表產生時間
	 * @return 是否存在
	 */
	
	public boolean isOrgReportExistByCreateReportTime(String reportCreateTime){
		return orgReportDAO.isOrgReportExistByReportDate(reportCreateTime);
		
		
	}
	
	
	
	
	

}
