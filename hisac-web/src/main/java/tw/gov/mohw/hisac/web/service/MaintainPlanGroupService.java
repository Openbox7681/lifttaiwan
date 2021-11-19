package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MaintainPlanGroupDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroup;

/**
 * 資安維護計畫群組服務
 */
@Service
public class MaintainPlanGroupService {
	@Autowired
	MaintainPlanGroupDAO maintainPlanGroupDAO;

	/**
	 * 取得所有資安維護計畫群組資料
	 * 
	 * @return 資安維護計畫群組資料
	 */
	public List<MaintainPlanGroup> getAll() {
		return maintainPlanGroupDAO.getAll();
	}

	/**
	 * 取得資安維護計畫群組資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安維護計畫群組資料
	 */
	public List<MaintainPlanGroup> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainPlanGroupDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得資安維護計畫群組資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安維護計畫群組資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainPlanGroupDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增資安維護計畫群組資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫群組資料
	 * @return 資安維護計畫群組資料
	 */
	public MaintainPlanGroup insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			MaintainPlanGroup entity = new MaintainPlanGroup();
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			maintainPlanGroupDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安維護計畫群組資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫群組Id
	 * @return 資安維護計畫群組資料
	 */
	public MaintainPlanGroup update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			MaintainPlanGroup entity = maintainPlanGroupDAO.get(id);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			maintainPlanGroupDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除資安維護計畫群組資料
	 * 
	 * @param id
	 *            資安維護計畫群組Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainPlanGroup entity = maintainPlanGroupDAO.get(id);
			maintainPlanGroupDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得資安維護計畫群組資料
	 * 
	 * @param id
	 *            資安維護計畫群組資料Id
	 * @return 資安維護計畫群組資料
	 */
	public MaintainPlanGroup getById(Long id) {
		return maintainPlanGroupDAO.get(id);
	}
	/**
	 * 資安維護計畫群組資料是否存在
	 * 
	 * @param id
	 *            資安維護計畫群組資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return maintainPlanGroupDAO.get(id) != null;
	}
	/**
	 * 資安維護計畫群組資料名稱
	 * 
	 * @param name
	 *            資安維護計畫群組名稱
	 * @return 資安維護計畫群組名稱資料
	 */
	public MaintainPlanGroup findByName(String name) {
		return maintainPlanGroupDAO.getByName(name);
	}
	/**
	 * 資安維護計畫群組資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

}
