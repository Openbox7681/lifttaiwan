package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.EventTypeDAO;
import tw.gov.mohw.hisac.web.domain.EventType;

/**
 * 事件種類服務
 */
@Service
public class EventTypeService {
	@Autowired
	EventTypeDAO eventTypeDAO;

	/**
	 * 取得所有事件種類資料
	 * 
	 * @return 事件種類資料
	 */
	public List<EventType> getAll() {
		return eventTypeDAO.getAll();
	}

	/**
	 * 取得事件種類資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件種類資料
	 */
	public List<EventType> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return eventTypeDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得事件種類資料
	 * 
	 * @return EventType List
	 */
	public List<EventType> getAnaList() {
		List<EventType> entitys = eventTypeDAO.getAnaList();
		return entitys;
	}
	/**
	 * 取得事件種類資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 事件種類資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return eventTypeDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增事件種類資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件種類資料
	 * @return 事件種類資料
	 */
	public EventType insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String stixXsd = obj.isNull("StixXsd") == true ? null : obj.getString("StixXsd");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			EventType entity = new EventType();
			entity.setAlertCode(alertCode);
			entity.setCode(code);
			entity.setName(name);
			entity.setStixXsd(stixXsd);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			eventTypeDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新事件種類資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            事件種類Id
	 * @return 事件種類資料
	 */
	public EventType update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String alertCode = obj.isNull("AlertCode") == true ? null : obj.getString("AlertCode");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String stixXsd = obj.isNull("StixXsd") == true ? null : obj.getString("StixXsd");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();

			EventType entity = eventTypeDAO.get(id);
			entity.setAlertCode(alertCode);
			entity.setCode(code);
			entity.setName(name);
			entity.setStixXsd(stixXsd);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			eventTypeDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除事件種類資料
	 * 
	 * @param id
	 *            事件種類Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			EventType entity = eventTypeDAO.get(id);
			eventTypeDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得事件種類資料
	 * 
	 * @param id
	 *            事件種類資料Id
	 * @return 事件種類資料
	 */
	public EventType getById(Long id) {
		return eventTypeDAO.get(id);
	}
	/**
	 * 事件種類資料是否存在
	 * 
	 * @param id
	 *            事件種類資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return eventTypeDAO.get(id) != null;
	}
	/**
	 * 事件種類資料名稱
	 * 
	 * @param name
	 *            事件種類名稱
	 * @return 事件種類名稱資料
	 */
	public EventType findByName(String name) {
		return eventTypeDAO.getByName(name);
	}
	/**
	 * 事件種類資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}
	/**
	 * 事件種類資料名稱
	 * 
	 * @param code
	 *            事件種類編號
	 * @return 事件種類名稱資料
	 */
	public EventType findByCode(String code) {
		return eventTypeDAO.getByCode(code);
	}
	/**
	 * 事件種類資料編號是否存在
	 * 
	 * @param code
	 *            編號
	 * @return 是否存在
	 */
	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}
	/**
	 * 取得事件種類資料
	 * 
	 * @param alertCode
	 *            alertCode
	 * @return 事件種類資料
	 */
	public List<EventType> getByAlertCode(String alertCode) {
		return eventTypeDAO.getByAlertCode(alertCode);
	}

}
