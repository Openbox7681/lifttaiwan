package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MessageRecipientDAO;
import tw.gov.mohw.hisac.web.domain.MessageRecipient;

/**
 * 資安廠商管理服務
 */
@Service
public class MessageRecipientService {

	@Autowired
	MessageRecipientDAO messageRecipientDAO;		

	/**
	 * 取得所有警訊收件者
	 * 
	 * @return 警訊收件者資料
	 */
	public List<MessageRecipient> getAll() {
		return messageRecipientDAO.getAll();
	}
	
	/**
	 * 取得警訊收件者資料
	 * 
	 * @param id
	 *            查詢條件
	 * @return 資警訊收件者資料
	 */
	public MessageRecipient get(Long id) {
		try {			
			return messageRecipientDAO.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊收件者資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊收件者資料
	 */
	public List<MessageRecipient> getList(String json) {
		try {			
			return messageRecipientDAO.getList(json);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 取得警訊收件者資料數量
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊收件者資料數量
	 */
	public long getListSize(String json) {
		try {			
			return messageRecipientDAO.getListSize(json);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 新增警訊收件者資料
	 * 
	 * @param memebrId
	 *            memebrId
	 * @param json
	 *           警訊收件者資料
	 * @return 警訊收件者資料
	 */
	public MessageRecipient insert(long memebrId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String name = obj.isNull("Name") == true ? "" : obj.getString("Name");			
			String email = obj.isNull("Email") == true ? "" : obj.getString("Email");			
			String mobilePhone = obj.isNull("MobilePhone") == true ? "" : obj.getString("MobilePhone");						
			
			MessageRecipient entity = new MessageRecipient();			
			entity.setName(name);
			entity.setEmail(email);
			entity.setMobilePhone(mobilePhone);			
			entity.setCreateId(memebrId);
			entity.setCreateTime(now);
			entity.setModifyId(memebrId);
			entity.setModifyTime(now);

			messageRecipientDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 修改警訊收件者資料
	 * 
	 * @param memebrId
	 *            memebrId
	 * @param json
	 *           警訊收件者資料
	 * @return 警訊收件者資料
	 */
	public MessageRecipient update(long memebrId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? "" : obj.getString("Name");			
			String email = obj.isNull("Email") == true ? "" : obj.getString("Email");			
			String mobilePhone = obj.isNull("MobilePhone") == true ? "" : obj.getString("MobilePhone");								
			
			MessageRecipient entity = messageRecipientDAO.get(id);
			entity.setName(name);
			entity.setEmail(email);
			entity.setMobilePhone(mobilePhone);				
			entity.setModifyId(memebrId);
			entity.setModifyTime(now);

			messageRecipientDAO.update(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 刪除警訊收件者資料
	 * 
	 * @param id
	 *            Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MessageRecipient entity = messageRecipientDAO.get(id);
			messageRecipientDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 資安廠商資料是否存在
	 * 
	 * @param id
	 *            Id
	 * @return 是否
	 */
	public boolean isExist(Long id) {
		return messageRecipientDAO.get(id) != null;
	}

}
