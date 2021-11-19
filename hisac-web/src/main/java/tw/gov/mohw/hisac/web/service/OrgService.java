package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.OrgDAO;
import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;
import tw.gov.mohw.hisac.web.dao.OrgVariable.OrgType;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;

/**
 * 單位基本資料管理服務
 */
@Service
public class OrgService {
	@Autowired
	OrgDAO orgDAO;

	/**
	 * 取得所有組織資料
	 * 
	 * @return 組織資料
	 */
	public List<Org> getAll() {
		return orgDAO.getAll();
	}

	/**
	 * 取得組織資料
	 * 
	 * @param orgType
	 *            orgType
	 * @return 組織資料
	 */
	public List<Org> getByOrgType(String orgType) {
		return orgDAO.getByOrgType(orgType);
	}

	/**
	 * 取得組織資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 組織資料
	 */
	public List<Org> querymember(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgDAO.querymember(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得組織資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 組織資料
	 */
	public List<Org> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得組織資料
	 * 
	 * @param isEnable
	 *            是否有效
	 * @param orgType
	 *            組織類別
	 * @return 組織資料
	 */
	public List<Org> getList(Boolean isEnable, OrgType orgType) {
		try {
			return orgDAO.getList(isEnable, orgType.getValue());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得組織資料
	 * 
	 * @param isEnable
	 *            是否有效
	 * @param orgType
	 *            組織類別
	 * @param authType
	 *            AuthType
	 * @return 組織資料
	 */
	public List<Org> getList(Boolean isEnable, OrgType orgType, AuthType authType) {
		try {
			return orgDAO.getList(isEnable, orgType.getValue(), authType.getValue());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得組織資料
	 * 
	 * @param isEnable
	 *            是否有效
	 * @param orgType
	 *            組織類別
	 * @param queryString
	 *            查詢字串
	 * @param perPage
	 *            每頁幾筆
	 * @return 組織資料
	 */
	public List<Org> getList(Boolean isEnable, OrgType orgType, String queryString, int perPage) {
		try {
			return orgDAO.getList(isEnable, orgType.getValue(), queryString, perPage);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得組織資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 組織資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * getLocalParentOrg
	 * 
	 * @param orgId
	 *            orgId
	 * @return getLocalParentOrg
	 */
	public ViewParentOrg getLocalParentOrg(Long orgId) {
		return orgDAO.getParentOrg(orgId, AuthType.Local);
	}

	/**
	 * getSuperviseParentOrg
	 * 
	 * @param orgId
	 *            orgId
	 * @return getSuperviseParentOrg
	 */
	public ViewParentOrg getSuperviseParentOrg(Long orgId) {
		try {
			return orgDAO.getParentOrg(orgId, AuthType.Supervise);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增組織資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            組織資料
	 * @return 組織資料
	 */
	public Org insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");
			String authType = obj.isNull("AuthType") == true ? null : obj.getString("AuthType");
			String ciLevel = obj.isNull("CILevel") == true ? null : obj.getString("CILevel");
			String tel = obj.isNull("Tel") == true ? null : obj.getString("Tel");
			String fax = obj.isNull("Fax") == true ? null : obj.getString("Fax");
			String city = obj.isNull("City") == true ? null : obj.getString("City");
			String town = obj.isNull("Town") == true ? null : obj.getString("Town");
			String address = obj.isNull("Address") == true ? null : obj.getString("Address");
			String bossName = obj.isNull("BossName") == true ? null : obj.getString("BossName");
			String bossEmail = obj.isNull("BossEmail") == true ? null : obj.getString("BossEmail");
			String bossMobilePhone = obj.isNull("BossMobilePhone") == true ? null : obj.getString("BossMobilePhone");
			String principalName = obj.isNull("PrincipalName") == true ? null : obj.getString("PrincipalName");
			String principalMobilePhone = obj.isNull("PrincipalMobilePhone") == true ? null : obj.getString("PrincipalMobilePhone");
			Long healthLevelId = obj.isNull("HealthLevelId") == true ? 0 : obj.getLong("HealthLevelId");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? 0 : obj.getLong("SecurityLevel");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");	
			boolean isLocate = obj.isNull("IsLocate") == true ? false : obj.getBoolean("IsLocate");


			Date now = new Date();
			Org entity = new Org();
			entity.setName(name);
			entity.setCode(code);
			entity.setOrgType(orgType);
			entity.setAuthType(authType);
			entity.setCiLevel(ciLevel);
			entity.setTel(tel);
			entity.setFax(fax);
			entity.setCity(city);
			entity.setTown(town);
			entity.setAddress(address);
			entity.setBossName(bossName);
			entity.setBossEmail(bossEmail);
			entity.setBossMobilePhone(bossMobilePhone);
			entity.setPrincipalName(principalName);
			entity.setPrincipalMobilePhone(principalMobilePhone);
			entity.setIsEnable(isEnable);
			entity.setIsApply(true);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setHealthLevelId(healthLevelId);
			entity.setSecurityLevel(securityLevel);
			entity.setIsPublic(isPublic);
			entity.setIsLocate(isLocate);

			orgDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新組織資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            組織資料
	 * @return 組織資料
	 */
	public Org update(long memberId, String json) {
		try {

			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");
			String authType = obj.isNull("AuthType") == true ? null : obj.getString("AuthType");
			String ciLevel = obj.isNull("CILevel") == true ? null : obj.getString("CILevel");
			String tel = obj.isNull("Tel") == true ? null : obj.getString("Tel");
			String fax = obj.isNull("Fax") == true ? null : obj.getString("Fax");
			String city = obj.isNull("City") == true ? null : obj.getString("City");
			String town = obj.isNull("Town") == true ? null : obj.getString("Town");
			String address = obj.isNull("Address") == true ? null : obj.getString("Address");
			String bossName = obj.isNull("BossName") == true ? null : obj.getString("BossName");
			String bossEmail = obj.isNull("BossEmail") == true ? null : obj.getString("BossEmail");
			String bossMobilePhone = obj.isNull("BossMobilePhone") == true ? null : obj.getString("BossMobilePhone");
			String principalName = obj.isNull("PrincipalName") == true ? null : obj.getString("PrincipalName");
			String principalMobilePhone = obj.isNull("PrincipalMobilePhone") == true ? null : obj.getString("PrincipalMobilePhone");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Long healthLevelId = obj.isNull("HealthLevelId") == true ? 0 : obj.getLong("HealthLevelId");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? 0 : obj.getLong("SecurityLevel");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");
			boolean isLocate = obj.isNull("IsLocate") == true ? false : obj.getBoolean("IsLocate");


			Date now = new Date();
			Org entity = orgDAO.get(id);
			// entity.setId(id);

			entity.setName(name);
			entity.setCode(code);
			entity.setOrgType(orgType);
			entity.setAuthType(authType);
			entity.setCiLevel(ciLevel);
			entity.setTel(tel);
			entity.setFax(fax);
			entity.setCity(city);
			entity.setTown(town);
			entity.setAddress(address);
			entity.setBossName(bossName);
			entity.setBossEmail(bossEmail);
			entity.setBossMobilePhone(bossMobilePhone);
			entity.setPrincipalName(principalName);
			entity.setPrincipalMobilePhone(principalMobilePhone);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setHealthLevelId(healthLevelId);
			entity.setSecurityLevel(securityLevel);
			entity.setIsPublic(isPublic);
			entity.setIsLocate(isLocate);


			// func.setCName(cName);
			orgDAO.update(entity);
			return entity;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新組織資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            組織資料
	 * @param baseOrgId
	 *            組織Id
	 * @return 組織資料
	 */
	public Org updateProfile(long memberId, String json, long baseOrgId) {
		try {

			JSONObject obj = new JSONObject(json);
			String tel = obj.isNull("Tel") == true ? null : obj.getString("Tel");
			String fax = obj.isNull("Fax") == true ? null : obj.getString("Fax");
			String city = obj.isNull("City") == true ? null : obj.getString("City");
			String town = obj.isNull("Town") == true ? null : obj.getString("Town");
			String address = obj.isNull("Address") == true ? null : obj.getString("Address");
			String bossName = obj.isNull("BossName") == true ? null : obj.getString("BossName");
			String bossEmail = obj.isNull("BossEmail") == true ? null : obj.getString("BossEmail");
			String bossMobilePhone = obj.isNull("BossMobilePhone") == true ? null : obj.getString("BossMobilePhone");
			String principalName = obj.isNull("PrincipalName") == true ? null : obj.getString("PrincipalName");
			String principalMobilePhone = obj.isNull("PrincipalMobilePhone") == true ? null : obj.getString("PrincipalMobilePhone");
			Long healthLevelId = obj.isNull("HealthLevelId") == true ? null : obj.getLong("HealthLevelId");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");
			boolean isLocate = obj.isNull("IsLocate") == true ? false : obj.getBoolean("IsLocate");

			

			Date now = new Date();
			Org entity = orgDAO.get(baseOrgId);
			entity.setTel(tel);
			entity.setFax(fax);
			entity.setCity(city);
			entity.setTown(town);
			entity.setAddress(address);
			entity.setBossName(bossName);
			entity.setBossEmail(bossEmail);
			entity.setBossMobilePhone(bossMobilePhone);
			entity.setPrincipalName(principalName);
			entity.setPrincipalMobilePhone(principalMobilePhone);
			// entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setHealthLevelId(healthLevelId);
			entity.setSecurityLevel(securityLevel);
			entity.setIsPublic(isPublic);
			entity.setIsLocate(isLocate);


			// func.setCName(cName);
			orgDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 更新組織資通安全責任等級
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            組織資料
	 * @param baseOrgId
	 *            組織Id
	 * @return 組織資料
	 */
	
	public Org updateSecurityLevel(long memberId, Long securityLevel, long baseOrgId) {
		try {
			Date now = new Date();
			Org entity = orgDAO.get(baseOrgId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setSecurityLevel(securityLevel);
			orgDAO.update(entity);

			return entity;
			
		}
		 catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}
	
	/**
	 * 更新組織Ci/非Ci資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            組織資料
	 * @param baseOrgId
	 *            組織Id
	 * @return 組織資料
	 */
	
	public Org updateCiLevel(long memberId, String ciLevel, long baseOrgId) {
		try {
			Date now = new Date();
			Org entity = orgDAO.get(baseOrgId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setCiLevel(ciLevel);
			orgDAO.update(entity);

			return entity;
			
		}
		 catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}
	
	

	
	
	
	/**
	 * 組織審核通過
	 * 	
	 * @param id
	 *            組織id
	 * @return 組織資料
	 */
	public Org apply(Long id) {
		try {						
			Org entity = orgDAO.get(id);
			entity.setIsApply(true);					
			orgDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除組織資料
	 * 
	 * @param id
	 *            組織Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			Org entity = orgDAO.get(id);
			orgDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得組織資料
	 * 
	 * @param id
	 *            組織資料Id
	 * @return 組織資料
	 */
	public Org getDataById(Long id) {
		return orgDAO.get(id);
	}

	/**
	 * 組織資料是否存在
	 * 
	 * @param id
	 *            組織資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return orgDAO.get(id) != null;
	}
	
	
	

		
	

	/**
	 * 組織資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public Org findByName(String name) {
		List<Org> entitys = orgDAO.getAll();
		for (Org entity : entitys) {
			if (entity.getName().equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * isNameExist
	 * 
	 * @param name
	 *            name
	 * @return isNameExist
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	/**
	 * 組織資料代碼是否存在
	 * 
	 * @param code
	 *            代碼
	 * @return 是否存在
	 */
	public Org findByCode(String code) {
		List<Org> entitys = orgDAO.getAll();
		for (Org entity : entitys) {
			if (entity.getCode().equalsIgnoreCase(code)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * 組織資料城市是否存在
	 * 
	 * @param city
	 *            城市
	 * @param orgType
	 *            組織類別
	 * @param authType
	 *            AuthType
	 * @return 是否存在
	 */
	public Org findByCity(String city, OrgType orgType, AuthType authType) {
		List<Org> entitys = orgDAO.getAll();
		for (Org entity : entitys) {
			if (entity.getCity().equals(city) && entity.getAuthType().equals(authType.getValue()) && entity.getOrgType().equals(orgType.getValue())) {
				return entity;
			}
		}
		return null;
	}
	

	/**
	 * 取得組織資料
	 * 以 parentOrgId 查找所屬資料
	 * 
	 * @param parentOrgId
	 * @return 組織資料
	 */
	public List<ViewOrgOrgSign> getByParentOrgId(Long parentOrgId) {
		return orgDAO.getByParentOrgId(parentOrgId);
	}

	/**
	 * 取得組織資料
	 * 以 id 查找所屬資料
	 * 
	 * @param id
	 * @return 組織資料
	 */
	public List<ViewOrgOrgSign> getOrgOrgSignById(Long id) {
		return orgDAO.getOrgOrgSignById(id);
	}
	
	
	/**
	 * 取得組織資料
	 * 找所有未審核通過機構
	 * 	
	 * @return 組織資料
	 */
	public List<Org> getOrgReport() {
		return orgDAO.getOrgReport();
	}
	
	/**
	 * 更新金鑰代碼狀態
	 * 
	 * 	
	 * @return 是否更新成功
	 */
	
	public Boolean updateApiKeyStatus(Org entity, String status) {
		try {
			orgDAO.updateApiKeyStatus(entity, status);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 更新金鑰代碼資料
	 * 
	 * 	
	 * @return 是否更新成功
	 */
	
	public Boolean updateApiKeyData(Org entity, String status, String apiKey, Date apiKeyExpiryDate) {
		try {
			orgDAO.updateApiKeyData(entity, status, apiKey, apiKeyExpiryDate);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	
	
	
	
	

}
