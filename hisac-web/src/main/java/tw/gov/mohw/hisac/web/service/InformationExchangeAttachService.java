package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.InformationExchangeAttachDAO;
import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;

/**
 * 最新消息
 */
@Service
public class InformationExchangeAttachService {
	@Autowired
	InformationExchangeAttachDAO informationExchangeAttachDAO;

	/**
	 * 新增情資附件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資附件資料
	 * @param bytes
	 *            byte
	 * @return 情資附件資料
	 */
	public InformationExchangeAttach insert(long memberId, String json, byte[] bytes) {
		try {

			JSONObject obj = new JSONObject(json);
			// long informationExchangeId = obj.isNull("InformationExchangeId")
			// == true ? null : obj.getLong("InformationExchangeId");
			String informationExchangeId = obj.isNull("InformationExchangeId") == true ? null : obj.getString("InformationExchangeId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			InformationExchangeAttach entity = new InformationExchangeAttach();
			entity.setInformationExchangeId(informationExchangeId);
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

			informationExchangeAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新情資附件資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 附件資料
	 */
	public InformationExchangeAttach update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			InformationExchangeAttach entity = informationExchangeAttachDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationExchangeAttachDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	public Boolean update(long memberId, byte[] bytes, JSONObject obj) {
		try {
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			InformationExchangeAttach entity = informationExchangeAttachDAO.get(id);

			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? 0 : obj.getLong("FileSize");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");
			Date now = new Date();

			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setFileDesc(fileDesc);
			entity.setFileHash(fileHash);

			entity.setFileContent(bytes);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationExchangeAttachDAO.update(entity);

			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	/**
	 * 刪除情資附件資料
	 * 
	 * @param id
	 *            情資資料Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			InformationExchangeAttach entity = informationExchangeAttachDAO.get(id);
			informationExchangeAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得情資附件資料
	 * 
	 * @param informationExchangeId
	 *            情資資料Id
	 * @return 情資資料
	 */
	public List<InformationExchangeAttach> getByInformationExchangeId(String informationExchangeId) {
		return informationExchangeAttachDAO.getByInfoExId(informationExchangeId);
	}

	/**
	 * 取得情資附件資料
	 * 
	 * @param id
	 *            情資資料Id
	 * @return 情資資料
	 */
	public InformationExchangeAttach get(Long id) {
		return informationExchangeAttachDAO.get(id);
	}
	/**
	 * 情資附件資料是否存在
	 * 
	 * @param id
	 *            情資資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return informationExchangeAttachDAO.get(id) != null;
	}

	/**
	 * 情資附件資料是否存在
	 * 
	 * @param informationExchangeId
	 *            情資資料Id
	 * @return data
	 */
	public List<InformationExchangeAttach> getByInfoExId(String informationExchangeId) {
		return informationExchangeAttachDAO.getByInfoExId(informationExchangeId);
	}

	/**
	 * 刪除InformationExchangeAttach
	 * 
	 * @param informationExchangeId
	 *            informationExchangeId
	 * @return 是否刪除成功
	 */
	public boolean deleteByinformationid(String informationExchangeId) {
		try {
			List<InformationExchangeAttach> informationExchangeAttachs = informationExchangeAttachDAO.getByInformationId(informationExchangeId);
			if (informationExchangeAttachs != null)
				for (InformationExchangeAttach informationExchangeAttach : informationExchangeAttachs)
					informationExchangeAttachDAO.delete(informationExchangeAttach);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

}
