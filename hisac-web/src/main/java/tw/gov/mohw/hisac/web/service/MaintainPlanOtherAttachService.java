package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanOtherAttachDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanOtherAttach;

/**
 * MessagePostAttach服務
 */
@Service
public class MaintainPlanOtherAttachService {
	@Autowired
	MaintainPlanOtherAttachDAO maintainPlanOtherAttachDAO;
	
	/**
	 * 新增MaintainPlanCheckListAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanCheckListAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanOtherAttach insert(long memberId, String json, long orgId, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MaintainPlanOtherAttach entity = new MaintainPlanOtherAttach();
			entity.setMaintainPlanId(maintainPlanId);
			entity.setGroupId(orgId);
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

			maintainPlanOtherAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 取得maintainPlanOtherAttachDAO資料
	 * 
	 * @param id
	 *            
	 * @return Id
	 */
	public MaintainPlanOtherAttach getById(Long Id) {
		return maintainPlanOtherAttachDAO.get(Id);
	}
	
	/**
	 * 刪除maintainPlanOtherAttachDAO資料
	 * 
	 * @param id
	 *          maintainPlanOtherAttachDAOId
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainPlanOtherAttach entity = maintainPlanOtherAttachDAO.get(id);
			maintainPlanOtherAttachDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 取得MaintainPlanCheckListAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanCheckListAttach
	 */
	public MaintainPlanOtherAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		return maintainPlanOtherAttachDAO.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
	}
	
	/**
	 * 取得MaintainPlanCheckListAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanCheckListAttach
	 */
	public List<MaintainPlanOtherAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		return maintainPlanOtherAttachDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
	}
	
	
	/**
	 * 刪除MaintainPlanCheckListAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 * @param groupId
	 *            groupId	
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		try {											
			maintainPlanOtherAttachDAO.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

}
