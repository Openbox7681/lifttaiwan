package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.AlertTypeDAO;
import tw.gov.mohw.hisac.web.domain.AlertType;

/**
 * 警訊種類服務
 */
@Service
public class AlertTypeService {
	@Autowired
	AlertTypeDAO alertTypeDAO;

	/**
	 * 取得所有警訊種類資料
	 * 
	 * @return AlertType List
	 */
	public List<AlertType> getAll() {
		List<AlertType> entitys = alertTypeDAO.getAll();
		return entitys;
	}

	/**
	 * 取得警訊種類排序過的資料
	 * 
	 * @return AlertType List
	 */
	public List<AlertType> getList() {
		List<AlertType> entitys = alertTypeDAO.getList();
		return entitys;
	}

	/**
	 * 取得查詢條件下警訊種類資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return AlertType List
	 */
	public List<AlertType> getList(String json) {
		List<AlertType> entitys = alertTypeDAO.getList(json);
		return entitys;
	}

	/**
	 * 取得查詢條件下警訊種類筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return AlertType Rows
	 */
	public long getListSize(String json) {
		return alertTypeDAO.getListSize(json);
	}

	/**
	 * 新增警訊種類資料
	 * 
	 * @param memberId
	 *            新增者資料
	 * @param json
	 *            AlertType
	 * @return AlertType
	 */
	public AlertType insert(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			AlertType entity = new AlertType();
			entity.setCode(code);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			alertTypeDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊種類資料
	 * 
	 * @param id
	 *            流水號
	 * @return AlertType
	 */
	public AlertType get(Long id) {
		return alertTypeDAO.get(id);
	}

	/**
	 * 警訊種類是否存在
	 * 
	 * @param id
	 *            流水號
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return alertTypeDAO.get(id) != null;
	}

	/**
	 * 取得警訊種類資料
	 * 
	 * @param code
	 *            Alert Type Code
	 * @return AlertType
	 */
	public AlertType getByCode(String code) {
		AlertType alertType = alertTypeDAO.getByCode(code);
		return alertType;
	}

	/**
	 * 警訊種類是否存在
	 * 
	 * @param code
	 *            Alert Type Code
	 * @return 是否存在
	 */
	public boolean isCodeExist(String code) {
		return getByCode(code) != null;
	}

	/**
	 * 更新警訊種類資料
	 * 
	 * @param memberId
	 *            異動者資料
	 * @param json
	 *            AlertType
	 * @return AlertType
	 */
	public AlertType update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			AlertType entity = alertTypeDAO.get(id);
			entity.setCode(code);
			entity.setName(name);
			entity.setSort(sort);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			alertTypeDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除警訊種類資料
	 * 
	 * @param id
	 *            流水號
	 * @return 是否成功刪除
	 */
	public boolean delete(Long id) {
		try {
			AlertType entity = alertTypeDAO.get(id);
			alertTypeDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
