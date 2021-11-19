package tw.gov.mohw.hisac.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SecurityLevelLogDAO;



import tw.gov.mohw.hisac.web.domain.SecurityLevelLog;
import tw.gov.mohw.hisac.web.domain.ViewSecurityLevelLog;


/**
 * 資通安全責任等級歷次資料服務
 */
@Service
public class SecurityLevelLogService {
	@Autowired
	SecurityLevelLogDAO securityLevelLogDAO;

	/**
	 * 取得資通安全責任等級歷次資料
	 * 
	 * @return 資通安全責任等級歷次資料
	 */
	public List<SecurityLevelLog> getAll() {
		List<SecurityLevelLog> entitys = securityLevelLogDAO.getAll();
		return entitys;
	}
	
	/**
	 * 取得資通安全責任等級歷次資料存在之年份
	 * 
	 * @return 資通安全責任等級歷次資料年份列表
	 */
	@SuppressWarnings({ "null", "unlikely-arg-type" })
	public List<String> getAllYears() {
		List<SecurityLevelLog> entitys = securityLevelLogDAO.getAll();
		List<String> years = new ArrayList<String>();
		if(entitys != null) {
			for ( SecurityLevelLog entity :entitys ) {
				if (!years.contains(entity.getYear())) {
					String year = entity.getYear();
//					System.out.println(year);
					years.add(year);
				}
				
			}
			return years;

		}
		else {
			return years;

		}
	}
	
	/**
	 * 利用年份取得資通安全責任等級歷次資料
	 * 
	 * @return 資通安全責任等級歷次資料
	 */
	@SuppressWarnings("null")
	public List<ViewSecurityLevelLog> getListByYear(String year) {
		List<ViewSecurityLevelLog> entitys = securityLevelLogDAO.getListByYear(year);
		return entitys;
	}
	
	

	/**
	 * 取得資通安全責任等級歷次資料
	 * 
	 * @param json
	 *            查詢資料
	 * @return 資通安全責任等級歷次資料
	 */
	public List<SecurityLevelLog> getList(String json) {
		List<SecurityLevelLog> entitys = securityLevelLogDAO.getList(json);
		return entitys;
	}

	/**
	 * 取得資通安全責任等級歷次資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資通安全責任等級歷次資料
	 */
	public long getListSize(String json) {
		return securityLevelLogDAO.getListSize(json);
	}

	/**
	 * 新增資通安全責任等級歷次資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            資通安全責任等級歷次資料
	 * @return 資通安全責任等級歷次資料
	 */
	public SecurityLevelLog insertData(long roleId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String year = obj.isNull("Year") == true ? null : obj.getString("Year");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
			Long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

			SecurityLevelLog entity = new SecurityLevelLog();
			entity.setOrgId(orgId);
			entity.setYear(year);
			entity.setSecurityLevel(securityLevel);
			
			entity.setCreateId(roleId);
			entity.setModifyId(roleId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			securityLevelLogDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得資通安全責任等級歷次資料
	 * 
	 * @param orgId
	 *            組織類別Id
	 * @return 資通安全責任等級歷次資料
	 */
	public SecurityLevelLog getDataByOrgId(Long orgId) {
		List<SecurityLevelLog> entitys = securityLevelLogDAO.getAll();
		for(SecurityLevelLog entity : entitys ) {
			if(entity.getOrgId().equals(orgId)){
				return entity;
			}
		}
		return null;
	}
	
	/**
	 * 取得資通安全責任等級歷次資料
	 * 
	 * @param orgId
	 *            組織類別Id
	 * @param year
	 *            歷次資料年份
	 * @return 資通安全責任等級歷次資料
	 */
	public SecurityLevelLog getDataByOrgIdAndYear(Long orgId,String year) {
		List<SecurityLevelLog> entitys = securityLevelLogDAO.getAll();
		for(SecurityLevelLog entity : entitys ) {
			if(entity.getOrgId().equals(orgId) && entity.getYear().equals(year) ){
				return entity;
			}
		}
		return null;
	}

	/**
	 * 資通安全責任等級歷次資料
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return securityLevelLogDAO.get(id) != null;
	}

	

	

	/**
	 * 更新資通安全責任等級歷次資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *           資通安全責任等級歷次資料
	 * @return 資通安全責任等級歷次資料
	 */
	public SecurityLevelLog updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();

			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String year = obj.isNull("Year") == true ? null : obj.getString("Year");
			Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
			Long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
			
			
			SecurityLevelLog entity = securityLevelLogDAO.get(id);
			entity.setOrgId(orgId);
			entity.setYear(year);
			entity.setSecurityLevel(securityLevel);
			
			entity.setCreateId(memberId);
			entity.setModifyId(memberId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			securityLevelLogDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除資通安全責任等級歷次資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			SecurityLevelLog entity = securityLevelLogDAO.get(id);
			securityLevelLogDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
