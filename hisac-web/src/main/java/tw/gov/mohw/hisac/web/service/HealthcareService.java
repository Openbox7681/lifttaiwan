package tw.gov.mohw.hisac.web.service;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.HealthcareDAO;
import tw.gov.mohw.hisac.web.domain.Healthcare;
import tw.gov.mohw.hisac.web.domain.ViewHealthcare;

/**
 * 醫事機構代碼服務
 */
@Service
public class HealthcareService {
	@Autowired
	HealthcareDAO healthcareDAO;

	/**
	 * 取得所有醫事機構代碼資料
	 * 
	 * @return 醫事機構代碼資料
	 */
	public List<Healthcare> getAll() {
		return healthcareDAO.getAll();
	}

	/**
	 * 取得醫事機構代碼資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 醫事機構代碼資料
	 */
	public List<ViewHealthcare> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return healthcareDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得醫療機構資料
	 * 
	 * @param queryString
	 *            查詢字串
	 * @param perPage
	 *            每頁幾筆
	 * @return 醫療機構資料
	 */
	public List<Healthcare> getList(String queryString, int perPage) {
		try {
			return healthcareDAO.getList(queryString, perPage);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得醫事機構代碼資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 醫事機構代碼資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return healthcareDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增醫事機構代碼資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            醫事機構代碼資料
	 * @return 醫事機構代碼資料
	 */
	public Healthcare insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String city = obj.isNull("City") == true ? null : obj.getString("City");
			String town = obj.isNull("Town") == true ? null : obj.getString("Town");
			String address = obj.isNull("Address") == true ? null : obj.getString("Address");
			boolean isCI = obj.isNull("IsCI") == true ? false : obj.getBoolean("IsCI");
			Long parentOrgId = obj.isNull("ParentOrgId") ? null : obj.getLong("ParentOrgId");
			Long healthLevelId = obj.isNull("HealthLevelId") ? 0 : obj.getLong("HealthLevelId");
			Long securityLevel = obj.isNull("SecurityLevel") ? 0 : obj.getLong("SecurityLevel");
			boolean isPublic = obj.isNull("IsPublic") ? false : obj.getBoolean("IsPublic");
			

			Healthcare entity = new Healthcare();
			entity.setCode(code);
			entity.setName(name);
			entity.setCity(city);
			entity.setTown(town);
			entity.setAddress(address);
			entity.setIsCI(isCI);
			entity.setParentOrgId(parentOrgId);
			entity.setHealthLevelId(healthLevelId);
			entity.setSecurityLevel(securityLevel);
			entity.setIsPublic(isPublic);

			healthcareDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新醫事機構代碼資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            醫事機構代碼Id
	 * @return 醫事機構代碼資料
	 */
	public Healthcare update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);

			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String city = obj.isNull("City") == true ? null : obj.getString("City");
			String town = obj.isNull("Town") == true ? null : obj.getString("Town");
			String address = obj.isNull("Address") == true ? null : obj.getString("Address");
			boolean isCI = obj.isNull("IsCI") == true ? false : obj.getBoolean("IsCI");
			Long parentOrgId = obj.isNull("ParentOrgId") ? null : obj.getLong("ParentOrgId");
			Long healthLevelId = obj.isNull("HealthLevelId") == true ? 0 : obj.getLong("HealthLevelId");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? 0 : obj.getLong("SecurityLevel");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");

			Healthcare entity = healthcareDAO.get(code);
			entity.setName(name);
			entity.setCity(city);
			entity.setTown(town);
			entity.setAddress(address);
			entity.setIsCI(isCI);
			entity.setParentOrgId(parentOrgId);
			entity.setHealthLevelId(healthLevelId);
			entity.setSecurityLevel(securityLevel);
			entity.setIsPublic(isPublic);
			healthcareDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除醫事機構代碼資料
	 * 
	 * @param code
	 *            醫事機構代碼Id
	 * @return 是否刪除成功
	 */
	public boolean delete(String code) {
		try {
			Healthcare entity = healthcareDAO.get(code);
			healthcareDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 醫事機構代碼資料是否存在
	 * 
	 * @param code
	 *            醫事機構代碼資料Id
	 * @return 是否存在
	 */
	public boolean isExist(String code) {
		return healthcareDAO.get(code) != null;
	}
	/**
	 * 醫事機構代碼資料名稱
	 * 
	 * @param name
	 *            醫事機構代碼名稱
	 * @return 醫事機構代碼名稱資料
	 */
	public Healthcare findByName(String name) {
		return healthcareDAO.getByName(name);
	}
	/**
	 * 醫事機構代碼資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	/**
	 * 取得醫事機構代碼資料
	 * 
	 * @param code
	 *            醫事機構代碼資料code
	 * @return 醫事機構代碼資料
	 */
	public Healthcare getByCode(String code) {
		return healthcareDAO.get(code);
	}

}
