package tw.gov.mohw.hisac.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tw.gov.mohw.hisac.web.dao.CiLevelLogDAO;

import tw.gov.mohw.hisac.web.domain.CiLevelLog;
import tw.gov.mohw.hisac.web.domain.SecurityLevelLog;

/**
 * 資通安全責任等級歷次資料服務
 */
@Service
public class CiLevelLogService {
	@Autowired
	CiLevelLogDAO ciLevelLogDAO;

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @returnCi/非Ci歷次資料
	 */
	public List<CiLevelLog> getAll() {
		List<CiLevelLog> entitys = ciLevelLogDAO.getAll();
		return entitys;
	}

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param json
	 *            查詢資料
	 * @return Ci/非Ci歷次資料
	 */
	public List<CiLevelLog> getList(String json) {
		List<CiLevelLog> entitys = ciLevelLogDAO.getList(json);
		return entitys;
	}
	
	
	/**
	 * 取得Ci/非Ci歷次資料存在之年份
	 * 
	 * @return Ci/非Ci歷次資料年份列表
	 */
	@SuppressWarnings({ "null", "unlikely-arg-type" })
	public List<String> getAllYears() {
		List<CiLevelLog> entitys = ciLevelLogDAO.getAll();
		List<String> years = new ArrayList<String>();
		if(entitys != null) {
			for ( CiLevelLog entity :entitys ) {
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
	 * 取得Ci/非Ci歷次資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return Ci/非Ci歷次資料
	 */
	public long getListSize(String json) {
		return ciLevelLogDAO.getListSize(json);
	}

	/**
	 * 新增Ci/非Ci歷次資料
	 * 
	 * @param memberId
	 *            memberId
	 * @param json
	 *            Ci/非Ci歷次資料
	 * @return Ci/非Ci歷次資料
	 */
	public CiLevelLog insertData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String year = obj.isNull("Year") == true ? null : obj.getString("Year");
			String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
			Long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");

			CiLevelLog entity = new CiLevelLog();
			entity.setOrgId(orgId);
			entity.setYear(year);
			entity.setCiLevel(ciLevel);
			
			entity.setCreateId(memberId);
			entity.setModifyId(memberId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			ciLevelLogDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param orgId
	 *            組織類別Id
	 * @return Ci/非Ci歷次資料
	 */
	public CiLevelLog getDataByOrgId(Long orgId) {
		List<CiLevelLog> entitys = ciLevelLogDAO.getAll();
		for(CiLevelLog entity : entitys ) {
			if(entity.getOrgId().equals(orgId)){
				return entity;
			}
		}
		return null;
	}
	
	
	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param orgId
	 *            組織類別Id
	 * @param year
	 *            歷次資料年份
	 * @return Ci/非Ci歷次資料
	 */
	public CiLevelLog getDataByOrgIdAndYear(Long orgId,String year) {
		List<CiLevelLog> entitys = ciLevelLogDAO.getAll();
		for(CiLevelLog entity : entitys ) {
			if(entity.getOrgId().equals(orgId) && entity.getYear().equals(year) ){
				return entity;
			}
		}
		return null;
	}

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return ciLevelLogDAO.get(id) != null;
	}

	

	

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *           Ci/非Ci歷次資料
	 * @return Ci/非Ci歷次資料
	 */
	public CiLevelLog updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();

			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String year = obj.isNull("Year") == true ? null : obj.getString("Year");
			Long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
			String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
			
			
			CiLevelLog entity = ciLevelLogDAO.get(id);
			entity.setOrgId(orgId);
			entity.setYear(year);
			entity.setCiLevel(ciLevel);
			
			entity.setCreateId(memberId);
			entity.setModifyId(memberId);
			entity.setCreateTime(now);
			entity.setModifyTime(now);

			ciLevelLogDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得Ci/非Ci歷次資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			CiLevelLog entity = ciLevelLogDAO.get(id);
			ciLevelLogDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
