package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.QAManagementGroupDAO;
import tw.gov.mohw.hisac.web.domain.QAManagementGroup;

/**
 * 常見問題類別服務
 */
@Service
public class QAManagementGroupService {
	@Autowired
	QAManagementGroupDAO qaManagementGroupDAO;

	/**
	 * 取得所有常見問題類別服務資料
	 * 
	 * @return 常見問題類別服務資料
	 */
	public List<QAManagementGroup> getAll() {
		return qaManagementGroupDAO.getAll();
	}

	/**
	 * 取得常見問題類別資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 常見問題類別資料
	 */
	public List<QAManagementGroup> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return qaManagementGroupDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得常見問題類別服務資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 常見問題類別服務資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return qaManagementGroupDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增常見問題類別服務資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            常見問題類別服務資料
	 * @return 常見問題類別服務資料
	 */
	public QAManagementGroup insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			QAManagementGroup entity = new QAManagementGroup();
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			qaManagementGroupDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新常見問題類別服務資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            常見問題類別服務資料
	 * @return 常見問題類別服務資料
	 */
	public QAManagementGroup update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			QAManagementGroup entity = qaManagementGroupDAO.get(id);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			qaManagementGroupDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除常見問題類別服務資料
	 * 
	 * @param id
	 *            常見問題類別服務Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			QAManagementGroup entity = qaManagementGroupDAO.get(id);
			qaManagementGroupDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得常見問題類別服務資料
	 * 
	 * @param id
	 *            常見問題類別服務資料Id
	 * @return 常見問題類別服務資料
	 */
	public QAManagementGroup getById(Long id) {
		return qaManagementGroupDAO.get(id);
	}
	/**
	 * 常見問題類別服務資料是否存在
	 * 
	 * @param id
	 *            常見問題類別服務資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return qaManagementGroupDAO.get(id) != null;
	}
	/**
	 * 常見問題類別服務資料名稱
	 * 
	 * @param name
	 *            常見問題類別服務資料名稱
	 * @return 常見問題類別服務資料
	 */
	public QAManagementGroup findByName(String name) {
		return qaManagementGroupDAO.getByName(name);
	}
	/**
	 * 常見問題類別服務資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

}
