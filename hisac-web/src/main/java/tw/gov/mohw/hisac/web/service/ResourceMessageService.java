package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.ResourceMessageDAO;
import tw.gov.mohw.hisac.web.domain.ResourceMessage;

/**
 * 網站設定服務
 */
@Service
public class ResourceMessageService {
	@Autowired
	ResourceMessageDAO resourceMessageDAO;

	static List<ResourceMessage> resourceMessages = null;

	public void loadResource() {
		loadResource(true);
	}

	public void loadResource(boolean fromCache) {
		if (resourceMessages == null || fromCache == false) {
			resourceMessages = resourceMessageDAO.getAll();
		}
	}

	public String getMessageValue(String messageKey) {
		for (ResourceMessage resourceMessage : resourceMessages) {
			if (resourceMessage.getMessageKey().equals(messageKey)) {
				return resourceMessage.getMessageValue();
			}
		}
		return null;
	}

	/**
	 * 取得所有網站設定資料
	 * 
	 * @return 網站設定資料
	 */
	public List<ResourceMessage> getAll() {
		return resourceMessageDAO.getAll();
	}

	/**
	 * 取得網站設定資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 網站設定資料
	 */
	public List<ResourceMessage> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return resourceMessageDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得網站設定資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 網站設定資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return resourceMessageDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增網站設定資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            網站設定名稱
	 * @return 網站設定資料
	 */
	public ResourceMessage insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String messageKey = obj.isNull("MessageKey") == true ? null : obj.getString("MessageKey");
			String messageValue = obj.isNull("MessageValue") == true ? null : obj.getString("MessageValue");

			Date now = new Date();
			ResourceMessage entity = new ResourceMessage();
			entity.setMessageKey(messageKey);
			entity.setMessageValue(messageValue);

			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			resourceMessageDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新網站設定資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            網站設定資料
	 * @return 網站設定資料
	 */
	public ResourceMessage update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String messageKey = obj.isNull("MessageKey") == true ? null : obj.getString("MessageKey");
			String messageValue = obj.isNull("MessageValue") == true ? null : obj.getString("MessageValue");

			Date now = new Date();
			ResourceMessage entity = resourceMessageDAO.get(id);
			entity.setMessageKey(messageKey);
			entity.setMessageValue(messageValue);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			resourceMessageDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除網站設定資料
	 * 
	 * @param id
	 *            網站設定Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			ResourceMessage entity = resourceMessageDAO.get(id);
			resourceMessageDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得網站設定資料
	 * 
	 * @param id
	 *            網站設定資料Id
	 * @return 網站設定資料
	 */
	public ResourceMessage getById(Long id) {
		return resourceMessageDAO.get(id);
	}
	/**
	 * 網站設定資料是否存在
	 * 
	 * @param id
	 *            網站設定資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return resourceMessageDAO.get(id) != null;
	}

	/**
	 * 網站設定資料名稱
	 * 
	 * @param messageKey
	 *            網站設定key
	 * @return 網站設定名稱資料
	 */
	public ResourceMessage findByMessageKey(String messageKey) {
		return resourceMessageDAO.getByMessageKey(messageKey);
	}
	/**
	 * 網站設定資料編號是否存在
	 * 
	 * @param messageKey
	 *            網站設定key
	 * @return 是否存在
	 */
	public boolean isMessageKeyExist(String messageKey) {
		return findByMessageKey(messageKey) != null;
	}

}
