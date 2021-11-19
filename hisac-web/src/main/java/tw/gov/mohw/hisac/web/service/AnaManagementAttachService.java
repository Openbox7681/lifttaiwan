package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.AnaManagementAttachDAO;
import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementAttachMember;

/**
 * 資安資訊情報
 */
@Service
public class AnaManagementAttachService {
	@Autowired
	AnaManagementAttachDAO anaManagementAttachDAO;

	/**
	 * 取得所有文章資料
	 * 
	 * @return 文章資料
	 */
	public List<AnaManagementAttach> getAll() {
		return anaManagementAttachDAO.getAll();
	}

	/**
	 * 取得文章資料
	 * 
	 * @param anaManagementId
	 *            查詢條件
	 * @return 文章資料
	 */
	public List<ViewAnaManagementAttachMember> getAllByAnaManagementId(long anaManagementId) {
		try {
			return anaManagementAttachDAO.getAllByAnaManagementId(anaManagementId);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得原始文章附件資料
	 * 
	 * @param anaManagementId
	 *            查詢條件
	 * @return 原始文章附件資料
	 */
	public List<AnaManagementAttach> getAllAttachByAnaManagementId(long anaManagementId) {
		try {
			return anaManagementAttachDAO.getAllAttachByAnaManagementId(anaManagementId);
		} catch (Exception e) {
			return null;
		}
	}

		
	
	
	
	
	
	
	/**
	 * 取得資安資訊情報文章筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安資訊情報文章筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return anaManagementAttachDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增資安資訊情報文章
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安資訊情報文章
	 * @param bytes
	 *            byte
	 * @return 資安資訊情報文章
	 */
	public AnaManagementAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long anaManagementId = obj.isNull("AnaManagementId") == true ? null : obj.getLong("AnaManagementId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			AnaManagementAttach entity = new AnaManagementAttach();
			entity.setAnaManagementId(anaManagementId);
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

			anaManagementAttachDAO.insert(entity);
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
	public AnaManagementAttach update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			AnaManagementAttach entity = anaManagementAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			anaManagementAttachDAO.update(entity);

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
			AnaManagementAttach entity = anaManagementAttachDAO.get(id);
			anaManagementAttachDAO.delete(entity);
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
	public AnaManagementAttach getById(Long id) {
		return anaManagementAttachDAO.get(id);
	}
	/**
	 * 文章附件資料是否存在
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return anaManagementAttachDAO.get(id) != null;
	}

}
