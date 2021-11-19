package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainInspectDAO;
import tw.gov.mohw.hisac.web.domain.MaintainInspect;

/**
 *法尊稽核事務服務
 */
@Service
public class MaintainInspectService {
	@Autowired
	MaintainInspectDAO MaintainInspectDAO;

	/**
	 * 新增法尊稽核事務資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            法尊稽核事務資料
	 * @param isApply
	 *            Boolean
	 * @return 法尊稽核事務資料
	 */
	public MaintainInspect insert(Long memberId, String json) {
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
			
			MaintainInspect entity = new MaintainInspect();			
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

			MaintainInspectDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新法尊稽核事務資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            法尊稽核事務資料
	 * @param isApply
	 *            Boolean
	 * @return 法尊稽核事務資料
	 */
	public MaintainInspect update(long memberId, String json) {
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

			MaintainInspect entity = MaintainInspectDAO.get(id);
			
			entity.setTitle(title);
			entity.setYear(year);
			entity.setMember(member);	
			entity.setItem(item);
			entity.setSdate(sdate);
			entity.setEdate(edate);
			entity.setStatus(status);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			MaintainInspectDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 更新法尊稽核事務資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            法尊稽核事務資料
	 * @param isApply
	 *            Boolean
	 * @return 法尊稽核事務資料
	 */
	public MaintainInspect statusChange(long MaintainInspectId, String status) {
		try {			

			MaintainInspect entity = MaintainInspectDAO.get(MaintainInspectId);
						
			entity.setStatus(status);			
			MaintainInspectDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除法尊稽核事務資料
	 * 
	 * @param id
	 *            法尊稽核事務Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainInspect entity = MaintainInspectDAO.get(id);
			MaintainInspectDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 法尊稽核事務資料是否存在
	 * 
	 * @param id
	 *            法尊稽核事務資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return MaintainInspectDAO.get(id) != null;
	}
	
	/**
	 * 法尊稽核事務資料
	 * 
	 * @param id
	 *            法尊稽核事務資料Id
	 * @return 法尊稽核事務資料
	 */
	public MaintainInspect getById(Long id) {
		return MaintainInspectDAO.get(id);
	}
	
	/**
	 * 取得法尊稽核事務資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 法尊稽核事務資料
	 */
	public List<MaintainInspect> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return MaintainInspectDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得法尊稽核事務資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 法尊稽核事務資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return MaintainInspectDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
}
