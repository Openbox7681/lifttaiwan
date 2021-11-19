package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.ActivityManagementAttachDAO;
import tw.gov.mohw.hisac.web.domain.ActivityManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;

/**
 * 最新消息
 */
@Service
public class ActivityManagementAttachService {
	@Autowired
	ActivityManagementAttachDAO activityManagementAttachDAO;

	/**
	 * 取得所有文章資料
	 * 
	 * @return 文章資料
	 */
	public List<ActivityManagementAttach> getAll() {
		return activityManagementAttachDAO.getAll();
	}

	/**
	 * 取得文章資料
	 * 
	 * @param activityManagementId
	 *            查詢條件
	 * @return 文章資料
	 */
	public List<ViewActivityManagementAttachMember> getAllByActivityManagementId(long activityManagementId) {
		try {
			return activityManagementAttachDAO.getAllByActivityManagementId(activityManagementId);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得文章附件資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 文章附件資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementAttachDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增文章附件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            文章附件資料
	 * @param bytes
	 *            byte
	 * @return 文章附件資料
	 */
	public ActivityManagementAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long activityManagementId = obj.isNull("ActivityManagementId") == true ? null : obj.getLong("ActivityManagementId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			ActivityManagementAttach entity = new ActivityManagementAttach();
			entity.setActivityManagementId(activityManagementId);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			activityManagementAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新文章附件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 附件資料
	 */
	public ActivityManagementAttach update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			ActivityManagementAttach entity = activityManagementAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			activityManagementAttachDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除文章附件資料
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			ActivityManagementAttach entity = activityManagementAttachDAO.get(id);
			activityManagementAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得文章附件資料
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 文章資料
	 */
	public ActivityManagementAttach getById(Long id) {
		return activityManagementAttachDAO.get(id);
	}
	/**
	 * 文章附件資料是否存在
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return activityManagementAttachDAO.get(id) != null;
	}

}
