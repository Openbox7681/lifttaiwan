package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MessagePostAttachDAO;
import tw.gov.mohw.hisac.web.domain.MessagePostAttach;

/**
 * MessagePostAttach服務
 */
@Service
public class MessagePostAttachService {
	@Autowired
	MessagePostAttachDAO messagePostAttachDAO;

	/**
	 * 取得所有MessagePostAttach資料
	 * 
	 * @return MessagePostAttach
	 */
	public List<MessagePostAttach> getAll() {
		return messagePostAttachDAO.getAll();
	}

	/**
	 * 取得MessagePostAttach筆數
	 * 
	 * @param messageId
	 *            查詢條件
	 * @return MessagePostAttach筆數
	 */
	public long getListSize(String messageId) {
		try {
			return messagePostAttachDAO.getByMessageIdSize(messageId);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得MessagePostAttach資料
	 * 
	 * @param messageId
	 *            查詢條件
	 * @return MessagePostAttach
	 */
	public List<MessagePostAttach> getByMessageId(String messageId) {
		try {
			return messagePostAttachDAO.getByMessageId(messageId);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 新增MessagePostAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessagePostAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public Boolean insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MessagePostAttach entity = new MessagePostAttach();
			entity.setMessageId(messageId);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);
			//
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			messagePostAttachDAO.insert(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新MessagePostAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public Boolean update(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? 0 : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");
			Date now = new Date();

			MessagePostAttach entity = messagePostAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			if (fileSize == 0)
				entity.setFileSize(null);
			else
				entity.setFileSize(fileSize);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messagePostAttachDAO.update(entity);

			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 新增MessagePostAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessagePostAttach資料
	 * @return 是否成功
	 */
	public Boolean insertFileDesc(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");

			Date now = new Date();
			MessagePostAttach entity = new MessagePostAttach();
			entity.setMessageId(messageId);
			entity.setFileDesc(fileDesc);
			//
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			messagePostAttachDAO.insert(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新MessagePostAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 是否成功
	 */
	public Boolean updateFileDesc(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			MessagePostAttach entity = messagePostAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messagePostAttachDAO.update(entity);

			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	/**
	 * 刪除messagePostAttach資料
	 * 
	 * @param id
	 *            messagePostAttach Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MessagePostAttach entity = messagePostAttachDAO.get(id);
			messagePostAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得MessagePostAttach資料
	 * 
	 * @param id
	 *            MessagePostAttach Id
	 * @return MessagePostAttach
	 */
	public MessagePostAttach getById(Long id) {
		return messagePostAttachDAO.get(id);
	}
	/**
	 * MessagePostAttach是否存在
	 * 
	 * @param id
	 *            MessagePostAttach Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return messagePostAttachDAO.get(id) != null;
	}

	/**
	 * 刪除MessagePostAttach
	 * 
	 * @param messageId
	 *            messagePost Id
	 * @return 是否刪除成功
	 */
	public boolean deleteBymessageid(String messageId) {
		try {
			List<MessagePostAttach> messagePostAttachs = messagePostAttachDAO.getByMessageId(messageId);
			if (messagePostAttachs != null)
				for (MessagePostAttach messagePostAttach : messagePostAttachs)
					messagePostAttachDAO.delete(messagePostAttach);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

}
