package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanScoreAttachDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanScoreAttach;;

/**
 * MessagePostAttach服務
 */
@Service
public class MaintainPlanScoreAttachService {
	@Autowired
	MaintainPlanScoreAttachDAO maintainPlanScoreAttachDAO;
	
	/**
	 * 新增MaintainPlanScoreAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanScoreAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanScoreAttach insert(long memberId, String json, long orgId, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
			long committeeId = obj.isNull("CommitteeId") == true ? 0 : obj.getLong("CommitteeId");
//			long maintainPlanMemberId = obj.isNull("MaintainPlanMemberId") == true ? 0 : obj.getLong("MaintainPlanMemberId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MaintainPlanScoreAttach entity = new MaintainPlanScoreAttach();
			entity.setMaintainPlanId(maintainPlanId);
			entity.setGroupId(orgId);
			entity.setCommitteeId(committeeId);
//			entity.setMaintainPlanMemberId(maintainPlanMemberId);
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

			maintainPlanScoreAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增MaintainPlanScoreAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanScoreAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanScoreAttach updataByMaintainPlanIdAndGroupIdAndCommitteeId(long memberId, String fileDesc,Long maintainPlanId,long groupId ,Long committeeId) {
		try {
			Date now = new Date();
			MaintainPlanScoreAttach entity = maintainPlanScoreAttachDAO.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,committeeId);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			maintainPlanScoreAttachDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 取得MaintainPlanScoreAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanScoreAttach
	 */
	public MaintainPlanScoreAttach getByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		return maintainPlanScoreAttachDAO.getByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
	}
	
	
	/**
	 * 刪除MaintainPlanScoreAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 * @param groupId
	 *            groupId	
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		try {											
			maintainPlanScoreAttachDAO.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 取得MaintainPlanScoreAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanScoreAttach
	 */
	public MaintainPlanScoreAttach getByMaintainPlanIdAndGroupIdAndCommitteeId(Long maintainPlanId, Long groupId ,Long committeeId) {
		return maintainPlanScoreAttachDAO.getByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,committeeId);
	}
	
	
	/**
	 * 刪除MaintainPlanScoreAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 * @param groupId
	 *            groupId	
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanIdAndGroupIdAndCommitteeId(long maintainPlanId, long groupId ,Long committeeId) {
		try {											
			maintainPlanScoreAttachDAO.deleteByMaintainPlanIdAndGroupIdAndCommitteeId(maintainPlanId, groupId,committeeId);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * 取得MaintainPlanScoreAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *            group Id
	 *            
	 * @return MaintainPlanScoreAttach
	 */
	public List<MaintainPlanScoreAttach> getListByMaintainPlanIdAndGroupId(Long maintainPlanId, Long groupId) {
		return maintainPlanScoreAttachDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
	}

	public MaintainPlanScoreAttach getById(Long id) {
		return maintainPlanScoreAttachDAO.get(id);
	}
}
