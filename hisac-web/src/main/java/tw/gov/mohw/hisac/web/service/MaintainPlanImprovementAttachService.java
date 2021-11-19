package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanImprovementAttachDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementAttach;;

/**
 * MessagePostAttach服務
 */
@Service
public class MaintainPlanImprovementAttachService {
	@Autowired
	MaintainPlanImprovementAttachDAO maintainPlanImprovementAttachDAO;
	
	/**
	 * 新增MaintainPlanImprovementAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanImprovementAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanImprovementAttach insert(long memberId, String json, long orgId, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MaintainPlanImprovementAttach entity = new MaintainPlanImprovementAttach();
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

			maintainPlanImprovementAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得MaintainPlanImprovementAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanImprovementAttach
	 */
	public MaintainPlanImprovementAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		return maintainPlanImprovementAttachDAO.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
	}
	
	
	/**
	 * 刪除MaintainPlanImprovementAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 * @param groupId
	 *            groupId	
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		try {											
			maintainPlanImprovementAttachDAO.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

}
