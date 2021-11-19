package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.HealthLevelDAO;
import tw.gov.mohw.hisac.web.domain.HealthLevel;
/**
 * 醫院層級資料維護服務
 */
@Service
public class HealthLevelService {
	@Autowired
	HealthLevelDAO healthLevelDAO;

	/**
	 * 取得醫院層級資料
	 * 
	 * @return 醫院層級資料
	 */
	public List<HealthLevel> getAll() {
		List<HealthLevel> entitys = healthLevelDAO.getAll();
		return entitys;
	}

	/**
	 * 取得醫院層級資料
	 * 
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public List<HealthLevel> getList(String json) {
		List<HealthLevel> entitys = healthLevelDAO.getList(json);
		return entitys;
	}

	/**
	 * 取得醫院層級資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 醫院層級資料筆數
	 */
	public long getRoleListSize(String json) {
		return healthLevelDAO.getListSize(json);
	}

	/**
	 * 新增醫院層級資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            醫院層級資料
	 * @return 醫院層級資料
	 */
	public HealthLevel insertData(long roleId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			

			HealthLevel entity = new HealthLevel();
			entity.setName(name);
			entity.setCreateId(roleId);
			entity.setModifyId(roleId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			healthLevelDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得醫院層級資料
	 * 
	 * @param id
	 *            id
	 * @return 醫院層級資料
	 */
	public HealthLevel getDataById(Long id) {
		return healthLevelDAO.get(id);
	}

	/**
	 * 醫院層級資料
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return healthLevelDAO.get(id) != null;
	}

	/**
	 * 取得醫院層級名稱
	 * 
	 * @param name
	 *            name
	 * @return 醫院層級名稱
	 */
	public HealthLevel findByName(String name) {
		List<HealthLevel> entitys = healthLevelDAO.getAll();
		for (HealthLevel entity : entitys) {
			if (entity.getName().equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * 醫院層級名稱是否存在
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	/**
	 * 更新醫院層級資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public HealthLevel updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");

			HealthLevel entity = healthLevelDAO.get(id);
			entity.setName(name);
			entity.setModifyId(memberId);

			healthLevelDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除醫院層級資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			HealthLevel entity = healthLevelDAO.get(id);
			healthLevelDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
