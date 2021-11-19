package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.RoleDAO;
import tw.gov.mohw.hisac.web.domain.Role;
/**
 * 角色資料維護服務
 */
@Service
public class RoleService {
	@Autowired
	RoleDAO roleDAO;

	/**
	 * 取得角色資料
	 * 
	 * @return 角色資料
	 */
	public List<Role> getAll() {
		List<Role> entitys = roleDAO.getAll();
		return entitys;
	}

	/**
	 * 取得角色資料
	 * 
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public List<Role> getList(String json) {
		List<Role> entitys = roleDAO.getList(json);
		return entitys;
	}

	/**
	 * 取得角色資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 角色資料筆數
	 */
	public long getRoleListSize(String json) {
		return roleDAO.getListSize(json);
	}

	/**
	 * 新增角色資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public Role insertData(long roleId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");

			Role entity = new Role();
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setCreateId(roleId);
			entity.setModifyId(roleId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			roleDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得角色資料
	 * 
	 * @param id
	 *            id
	 * @return 角色資料
	 */
	public Role getDataById(Long id) {
		return roleDAO.get(id);
	}

	/**
	 * 角色資料是否存在
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return roleDAO.get(id) != null;
	}

	/**
	 * 取得角色管理服務系統名稱
	 * 
	 * @param name
	 *            name
	 * @return 角色管理服務系統名稱
	 */
	public Role findByName(String name) {
		List<Role> entitys = roleDAO.getAll();
		for (Role entity : entitys) {
			if (entity.getName().equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * 角色管理服務系統名稱是否存在
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	/**
	 * 更新角色資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public Role updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");

			Role entity = roleDAO.get(id);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setModifyId(memberId);

			roleDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除角色資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			Role entity = roleDAO.get(id);
			roleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
