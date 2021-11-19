package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.InformationManagementAttachDAO;
import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;
import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;

/**
 * 資訊分享-附件
 */
@Service
public class InformationManagementAttachService {
	@Autowired
	InformationManagementAttachDAO informationManagementAttachDAO;


	/**
	 * 取得資訊分享-附件資料
	 * 
	 * @param newsManagementId
	 *            查詢條件
	 * @return 文章資料-附件
	 */
	public List<InformationManagementAttach> getAllByInformationManagementId(long informationManagementId) {
		try {
			return informationManagementAttachDAO.getAllByInformationManagementId(informationManagementId);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 新增附件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            附件資料
	 * @param bytes
	 *            byte
	 * @return 附件資料
	 */
	public InformationManagementAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long informationManagementId = obj.isNull("InformationManagementId") == true ? null : obj.getLong("InformationManagementId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			InformationManagementAttach entity = new InformationManagementAttach();
			entity.setInformationManagementId(informationManagementId);
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

			informationManagementAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 刪除資訊分享-附件資料
	 * 
	 * @param id
	 *            Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			InformationManagementAttach entity = informationManagementAttachDAO.get(id);
			informationManagementAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得附件資料
	 * 
	 * @param id
	 *            資料Id
	 * @return 附件資料
	 */
	public InformationManagementAttach getById(Long id) {
		return informationManagementAttachDAO.get(id);
	}
	/**
	 * 附件資料是否存在
	 * 
	 * @param id
	 *            資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return informationManagementAttachDAO.get(id) != null;
	}

	
}
