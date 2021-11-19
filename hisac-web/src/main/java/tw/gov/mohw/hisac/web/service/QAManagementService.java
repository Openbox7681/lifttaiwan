package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.QAManagementDAO;
import tw.gov.mohw.hisac.web.domain.QAManagement;
import tw.gov.mohw.hisac.web.dao.QAManagementGroupDAO;
import tw.gov.mohw.hisac.web.domain.QAManagementGroup;
import tw.gov.mohw.hisac.web.domain.ViewQAManagementGroup;

/**
 * 常見問題服務
 */
@Service
public class QAManagementService {
	@Autowired
	QAManagementDAO qaManagementDAO;
	QAManagementGroupDAO qaManagementGroupDAO;

	/**
	 * 取得所有常見問題資料
	 * 
	 * @return 常見問題資料
	 */
	public List<QAManagement> getAll() {
		return qaManagementDAO.getAll();
	}

	/**
	 * 取得所有常見問題類別資料
	 * 
	 * @return 常見問題類別資料
	 */
	public List<QAManagementGroup> getAllQAManagementGroup() {
		return qaManagementGroupDAO.getAll();
	}

	/**
	 * 取得常見問題資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 常見問題資料
	 */
	public List<ViewQAManagementGroup> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return qaManagementDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得常見問題資料
	 * 
	 * @param isEnable
	 *            是否有效
	 * @param qaMgId
	 *            qaMgId
	 * @return 常見問題資料
	 */
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId) {
		try {
			return qaManagementDAO.getList(isEnable, qaMgId);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得常見問題資料
	 * 
	 * @param isEnable
	 *            是否有效
	 * @param qaMgId
	 *            常見問題類別
	 * @param queryString
	 *            查詢字串
	 * @param perPage
	 *            每頁幾筆
	 * @return 常見問題資料
	 */
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId, String queryString, int perPage) {
		try {
			return qaManagementDAO.getList(isEnable, qaMgId, queryString, perPage);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得常見問題資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 常見問題資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return qaManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增常見問題資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            常見問題資料名稱
	 * @return 常見問題資料
	 */
	public QAManagement insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String qName = obj.isNull("QName") == true ? null : obj.getString("QName");
			String aName = obj.isNull("AName") == true ? null : obj.getString("AName");
			Long qaMgId = obj.isNull("QAManagementGroupId") == true ? null : obj.getLong("QAManagementGroupId");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");

			Date now = new Date();
			QAManagement entity = new QAManagement();
			entity.setQName(qName);
			entity.setAName(aName);
			entity.setQAMgId(qaMgId);
			entity.setIsEnable(isEnable);
			entity.setIsPublic(isPublic);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			qaManagementDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新常見問題資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            常見問題資料
	 * @return 常見問題資料
	 */
	public QAManagement update(long memberId, String json) {
		try {

			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String qName = obj.isNull("QName") == true ? null : obj.getString("QName");
			String aName = obj.isNull("AName") == true ? null : obj.getString("AName");
			Long qaMgId = obj.isNull("QAManagementGroupId") == true ? null : obj.getLong("QAManagementGroupId");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isPublic = obj.isNull("IsPublic") == true ? false : obj.getBoolean("IsPublic");
			
			Date now = new Date();
			QAManagement entity = qaManagementDAO.get(id);
			// entity.setId(id);

			entity.setQName(qName);
			entity.setAName(aName);
			entity.setQAMgId(qaMgId);
			entity.setIsEnable(isEnable);
			entity.setIsPublic(isPublic);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			// func.setCName(cName);
			qaManagementDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除常見問題資料
	 * 
	 * @param id
	 *            Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			QAManagement entity = qaManagementDAO.get(id);
			qaManagementDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得常見問題資料
	 * 
	 * @param id
	 *            常見問題資料Id
	 * @return 常見問題資料
	 */
	public QAManagement getDataById(Long id) {
		return qaManagementDAO.get(id);
	}
	/**
	 * 常見問題資料是否存在
	 * 
	 * @param id
	 *            常見問題資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return qaManagementDAO.get(id) != null;
	}
	/**
	 * 常見問題資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public QAManagement findByQName(String name) {
		List<QAManagement> entitys = qaManagementDAO.getAll();
		if (entitys != null)
		for (QAManagement entity : entitys) {
			if (entity.getQName().equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}
	/**
	 * 常見問題資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public QAManagement findByAName(String name) {
		List<QAManagement> entitys = qaManagementDAO.getAll();
		for (QAManagement entity : entitys) {
			if (entity.getAName().equalsIgnoreCase(name)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * isQNameExist
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isQNameExist(String name) {
		return findByQName(name) != null;
	}

	/**
	 * isANameExist
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isANameExist(String name) {
		return findByAName(name) != null;
	}

}
