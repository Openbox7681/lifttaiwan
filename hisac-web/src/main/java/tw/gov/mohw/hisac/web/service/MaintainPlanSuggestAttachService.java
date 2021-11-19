package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanSuggestAttachDAO;

import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestAttach;

/**
 * MaintainPlanImprovementSuggestAttach服務
 */
@Service
public class MaintainPlanSuggestAttachService {
	@Autowired
	MaintainPlanSuggestAttachDAO maintainPlanSuggestAttachDAO;
	
	/**
	 * 新增MaintainPlanImprovementSuggestAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanImprovementSuggestAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanImprovementSuggestAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MaintainPlanImprovementSuggestAttach entity = new MaintainPlanImprovementSuggestAttach();
			entity.setMaintainPlanId(maintainPlanId);
			
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
			
			maintainPlanSuggestAttachDAO.insert(entity);
			
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得MaintainPlanImprovementSuggestAttach資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *           
	 *            
	 * @return MaintainPlanImprovementSuggestAttach
	 */
	public MaintainPlanImprovementSuggestAttach getByMaintainPlanId(Long maintainPlanId) {
		return maintainPlanSuggestAttachDAO.getByMaintainPlanId(maintainPlanId);
	}
	
	/**
	 * 判斷MaintainPlanImprovementSuggestRead資料是否存在
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *                   
	 * @return true or false
	 */
	
	public boolean isMaintainPlanImprovementSuggestAttachExist(Long maintainPlanId) {
		if (maintainPlanSuggestAttachDAO.getByMaintainPlanId(maintainPlanId) != null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	/**
	 * 刪除MaintainPlanAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 *            
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanId(long maintainPlanId) {
		try {											
			maintainPlanSuggestAttachDAO.deleteByMaintainPlanId(maintainPlanId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
