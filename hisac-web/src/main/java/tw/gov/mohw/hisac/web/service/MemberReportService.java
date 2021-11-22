
package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MemberReportDAO;
import tw.gov.mohw.hisac.web.domain.MemberReport;;



/**
 * 會員統計報表結果排程序(會員)
 */
@Service
public class MemberReportService {
	final static Logger logger = LoggerFactory.getLogger(MemberReportService.class);

	@Autowired
	MemberReportDAO memberReportDAO;

	/**
	 * 會員統計報表排程統計
	 * 
	 */
	public JSONObject schedule(String json) {
		JSONObject obj = new JSONObject(json);
			return memberReportDAO.schedule(obj);			
	
	}
	
	/**
	 * 刪除會員統計報表資料
	 * 
	 */
	
	public boolean delete(Long id) {
		try {
			MemberReport entity = memberReportDAO.get(id);
			memberReportDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	
	
	
	/**
	 * 取得會員報告資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 機構報告資料
	 */
	public List<MemberReport> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return memberReportDAO.getList(obj);
		} catch (Exception e) {
			e.printStackTrace();
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
			return memberReportDAO.getListSize(obj);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public boolean isMemberReportExistByCreateReportTime(String reportCreateTime){
		return memberReportDAO.isMemberReportExistByReportDate(reportCreateTime);
		
		
	}
	
	
	
	

}
