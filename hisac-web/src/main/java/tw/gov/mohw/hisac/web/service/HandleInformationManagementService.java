package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.HandleInformationDAO;
import tw.gov.mohw.hisac.web.domain.HandleInformation;

/**
 * 資安廠商管理服務
 */
@Service
public class HandleInformationManagementService {

	@Autowired
	HandleInformationDAO handleInformationDAO;		

	/**
	 * 取得所有資安廠商資料
	 * 
	 * @return 資安廠商資料
	 */
	public List<HandleInformation> getAll() {
		return handleInformationDAO.getAll();
	}
	
	/**
	 * 取得資安廠商資料
	 * 
	 * @param id
	 *            查詢條件
	 * @return 資安廠商資料
	 */
	public HandleInformation get(Long id) {
		try {			
			return handleInformationDAO.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得資安廠商資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安廠商資料
	 */
	public List<HandleInformation> getList(String json) {
		try {			
			return handleInformationDAO.getList(json);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 取得資安廠商資料數量
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安廠商資料數量
	 */
	public long getListSize(String json) {
		try {			
			return handleInformationDAO.getListSize(json);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 新增資安廠商資料
	 * 
	 * @param memebrId
	 *            memebrId
	 * @param json
	 *           資安廠商資料
	 * @return 資安廠商資料
	 */
	public HandleInformation insert(long memebrId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			String name = obj.isNull("Name") == true ? "" : obj.getString("Name");			
			String section = obj.isNull("Section") == true ? "" : obj.getString("Section");			
			String contactInfo = obj.isNull("ContactInfo") == true ? "" : obj.getString("ContactInfo");
			String serviceItems = obj.isNull("ServiceItems") == true ? null : obj.getString("ServiceItems");
			long contactorId = obj.isNull("ContactorId") == true ? null : obj.getLong("ContactorId");			
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");
			
			HandleInformation entity = new HandleInformation();			
			entity.setName(name);
			entity.setSection(section);
			entity.setContactInfo(contactInfo);
			entity.setServiceItems(serviceItems);
			entity.setContactorId(contactorId);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);	
			entity.setCreateId(memebrId);
			entity.setCreateTime(now);
			entity.setModifyId(memebrId);
			entity.setModifyTime(now);
			entity.setRemark(remark);

			handleInformationDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 修改資安廠商資料
	 * 
	 * @param memebrId
	 *            memebrId
	 * @param json
	 *           資安廠商資料
	 * @return 資安廠商資料
	 */
	public HandleInformation update(long memebrId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? "" : obj.getString("Name");			
			String section = obj.isNull("Section") == true ? "" : obj.getString("Section");			
			String contactInfo = obj.isNull("ContactInfo") == true ? "" : obj.getString("ContactInfo");
			String serviceItems = obj.isNull("ServiceItems") == true ? null : obj.getString("ServiceItems");
			long contactorId = obj.isNull("ContactorId") == true ? null : obj.getLong("ContactorId");			
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");				
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");
			
			HandleInformation entity = handleInformationDAO.get(id);
			entity.setName(name);
			entity.setSection(section);
			entity.setContactInfo(contactInfo);	
			entity.setServiceItems(serviceItems);
			entity.setContactorId(contactorId);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);			
			entity.setModifyId(memebrId);
			entity.setModifyTime(now);
			entity.setRemark(remark);

			handleInformationDAO.update(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 修改資安廠商資料
	 * 
	 * @param memebrId
	 *            memebrId
	 * @param json
	 *           資安廠商資料
	 * @return 資安廠商資料
	 */
	public HandleInformation update(long memebrId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date now = new Date();
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? "" : obj.getString("Name");			
			String section = obj.isNull("Section") == true ? "" : obj.getString("Section");			
			String contactInfo = obj.isNull("ContactInfo") == true ? "" : obj.getString("ContactInfo");
			String serviceItems = obj.isNull("ServiceItems") == true ? null : obj.getString("ServiceItems");
			long contactorId = obj.isNull("ContactorId") == true ? null : obj.getLong("ContactorId");					
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");
			
			HandleInformation entity = handleInformationDAO.get(id);
			entity.setName(name);
			entity.setSection(section);
			entity.setContactInfo(contactInfo);
			entity.setServiceItems(serviceItems);
			entity.setContactorId(contactorId);
			entity.setModifyId(memebrId);
			entity.setModifyTime(now);
			entity.setRemark(remark);

			handleInformationDAO.update(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 刪除資安廠商資料
	 * 
	 * @param id
	 *            Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			HandleInformation entity = handleInformationDAO.get(id);
			handleInformationDAO.delete(entity);
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
		return handleInformationDAO.get(id) != null;
	}

}
