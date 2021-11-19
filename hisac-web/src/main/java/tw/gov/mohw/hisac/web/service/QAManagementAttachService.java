package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.QAManagementAttachDAO;
import tw.gov.mohw.hisac.web.domain.QAManagementAttach;

/**
 * 最新消息
 */
@Service
public class QAManagementAttachService {
	@Autowired
	QAManagementAttachDAO qaManagementAttachDAO;

	/**
	 * 取得所有文章資料
	 * 
	 * @return 文章資料
	 */
	public List<QAManagementAttach> getAll() {
		return qaManagementAttachDAO.getAll();
	}

	/**
	 * 取得文章資料
	 * 
	 * @param newsManagementId
	 *            查詢條件
	 * @return 文章資料
	 */
	public List<QAManagementAttach> getAllByQAManagementId(long qaManagementId) {
		try {
			return qaManagementAttachDAO.getAllByQAManagementId(qaManagementId);
		} catch (Exception e) {
			return null;
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
	public QAManagementAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long qaManagementId = obj.isNull("QAManagementId") == true ? null : obj.getLong("QAManagementId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			QAManagementAttach entity = new QAManagementAttach();
			entity.setManagementId(qaManagementId);
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

			qaManagementAttachDAO.insert(entity);
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
	public QAManagementAttach update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			QAManagementAttach entity = qaManagementAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			qaManagementAttachDAO.update(entity);

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
	 *            Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			QAManagementAttach entity = qaManagementAttachDAO.get(id);
			qaManagementAttachDAO.delete(entity);
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
	public QAManagementAttach getById(Long id) {
		return qaManagementAttachDAO.get(id);
	}
	/**
	 * 文章附件資料是否存在
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return qaManagementAttachDAO.get(id) != null;
	}

}
