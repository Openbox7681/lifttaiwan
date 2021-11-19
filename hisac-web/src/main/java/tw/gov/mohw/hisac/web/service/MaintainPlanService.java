package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlan;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

/**
 * 資安維護計畫服務
 */
@Service
public class MaintainPlanService {
	@Autowired
	MaintainPlanDAO maintainPlanDAO;

	/**
	 * 新增資安維護計畫資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫資料
	 */
	public MaintainPlan insert(Long memberId, String json) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			Integer year = obj.isNull("Year") == true ? 0 : obj.getInt("Year");
			String member = obj.isNull("Member") == true ? null : obj.optJSONObject("Member").toString();	
			String item = obj.isNull("Item") == true ? null : obj.optJSONArray("Item").toString();
			String str_sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
			Date sdate = new Date();
			if (str_sdate != null)
				sdate = new SimpleDateFormat("yyyy-MM-dd").parse(str_sdate);
			String str_edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
			Date edate = new Date();
			if (str_edate != null)
				edate = new SimpleDateFormat("yyyy-MM-dd").parse(str_edate);
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");
			
			MaintainPlan entity = new MaintainPlan();			
			entity.setTitle(title);
			entity.setYear(year);	
			entity.setMember(member);	
			entity.setItem(item);	
			entity.setSdate(sdate);
			entity.setEdate(edate);
			entity.setStatus(status);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			maintainPlanDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安維護計畫資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫資料
	 */
	public MaintainPlan update(long memberId, String json) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			Integer year = obj.isNull("Year") == true ? 0 : obj.getInt("Year");
			String member = obj.isNull("Member") == true ? null : obj.optJSONObject("Member").toString();	
			String item = obj.isNull("Item") == true ? null : obj.optJSONArray("Item").toString();
			String str_sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
			Date sdate = new Date();
			if (str_sdate != null)
				sdate = new SimpleDateFormat("yyyy-MM-dd").parse(str_sdate);
			String str_edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
			Date edate = new Date();
			if (str_edate != null)
				edate = new SimpleDateFormat("yyyy-MM-dd").parse(str_edate);
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");

			MaintainPlan entity = maintainPlanDAO.get(id);
			
			entity.setTitle(title);
			entity.setYear(year);
			entity.setMember(member);	
			entity.setItem(item);
			entity.setSdate(sdate);
			entity.setEdate(edate);
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			maintainPlanDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 更新資安維護計畫資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫資料
	 */
	public MaintainPlan statusChange(long maintainPlanId, String status) {
		try {			

			MaintainPlan entity = maintainPlanDAO.get(maintainPlanId);
						
			entity.setStatus(status);			
			maintainPlanDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除資安維護計畫資料
	 * 
	 * @param id
	 *            資安維護計畫Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainPlan entity = maintainPlanDAO.get(id);
			maintainPlanDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 資安維護計畫資料是否存在
	 * 
	 * @param id
	 *            資安維護計畫資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return maintainPlanDAO.get(id) != null;
	}
	
	/**
	 * 資安維護計畫資料
	 * 
	 * @param id
	 *            資安維護計畫資料Id
	 * @return 資安維護計畫資料
	 */
	public MaintainPlan getById(Long id) {
		return maintainPlanDAO.get(id);
	}
	
	/**
	 * 取得資安維護計畫資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安維護計畫資料
	 */
	public List<MaintainPlan> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainPlanDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得資安維護計畫資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安維護計畫資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainPlanDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
}
