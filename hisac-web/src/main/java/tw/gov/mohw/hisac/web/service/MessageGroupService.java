package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MessageGroupDAO;
import tw.gov.mohw.hisac.web.domain.MessageGroup;

/**
 * 警訊發佈群組服務
 */
@Service
public class MessageGroupService {
	@Autowired
	MessageGroupDAO messageGroupDAO;

	/**
	 * 取得所有警訊發佈群組資料
	 * 
	 * @return 警訊發佈群組資料
	 */
	public List<MessageGroup> getAll() {
		return messageGroupDAO.getAll();
	}

	/**
	 * 取得警訊發佈群組資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊發佈群組資料
	 */
	public List<MessageGroup> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageGroupDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得警訊發佈群組資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊發佈群組資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageGroupDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增警訊發佈群組資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊發佈群組資料
	 * @return 警訊發佈群組資料
	 */
	public MessageGroup insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			MessageGroup entity = new MessageGroup();
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			messageGroupDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新警訊發佈群組資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            警訊發佈群組Id
	 * @return 警訊發佈群組資料
	 */
	public MessageGroup update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			MessageGroup entity = messageGroupDAO.get(id);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messageGroupDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除警訊發佈群組資料
	 * 
	 * @param id
	 *            警訊發佈群組Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MessageGroup entity = messageGroupDAO.get(id);
			messageGroupDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得警訊發佈群組資料
	 * 
	 * @param id
	 *            警訊發佈群組資料Id
	 * @return 警訊發佈群組資料
	 */
	public MessageGroup getById(Long id) {
		return messageGroupDAO.get(id);
	}
	/**
	 * 警訊發佈群組資料是否存在
	 * 
	 * @param id
	 *            警訊發佈群組資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return messageGroupDAO.get(id) != null;
	}
	/**
	 * 警訊發佈群組資料名稱
	 * 
	 * @param name
	 *            警訊發佈群組名稱
	 * @return 警訊發佈群組名稱資料
	 */
	public MessageGroup findByName(String name) {
		return messageGroupDAO.getByName(name);
	}
	/**
	 * 警訊發佈群組資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

}
