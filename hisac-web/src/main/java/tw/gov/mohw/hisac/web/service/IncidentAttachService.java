package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.IncidentAttachDAO;
import tw.gov.mohw.hisac.web.domain.IncidentAttach;
import tw.gov.mohw.hisac.web.domain.ViewIncidentAttachMember;


/**
 * 事故管理-附件
 */
@Service
public class IncidentAttachService {
	@Autowired
	IncidentAttachDAO incidentAttachDAO;

	/**
	 * 取得所有附件資料
	 * 
	 * @return 附件資料
	 */
	public List<IncidentAttach> getAll() {
		return incidentAttachDAO.getAll();
	}

	/**
	 * 取得附件資料
	 * 
	 * @param incidentId
	 *            查詢條件
	 * @return 附件資料
	 */
	public List<ViewIncidentAttachMember> getAllByIncidentId(long incidentId) {
		try {
			return incidentAttachDAO.getAllByIncidentId(incidentId);
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
			return incidentAttachDAO.getListSize(obj);
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
	public IncidentAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long incidentId = obj.isNull("IncidentId") == true ? null : obj.getLong("IncidentId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			IncidentAttach entity = new IncidentAttach();
			entity.setIncidentId(incidentId);
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

			incidentAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
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
	public IncidentAttach update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			IncidentAttach entity = incidentAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			incidentAttachDAO.update(entity);

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除文章附件資料
	 * 
	 * @param id
	 *            附件資料Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			IncidentAttach entity = incidentAttachDAO.get(id);
			incidentAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得文章附件資料
	 * 
	 * @param id
	 *            附件資料Id
	 * @return 附件資料
	 */
	public IncidentAttach getById(Long id) {
		return incidentAttachDAO.get(id);
	}
	/**
	 * 文章附件資料是否存在
	 * 
	 * @param id
	 *            附件資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return incidentAttachDAO.get(id) != null;
	}

}
