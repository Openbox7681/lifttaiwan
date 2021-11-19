package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanQuestionnaireSampleAttachDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanQuestionnaireSampleAttach;;

/**
 * MessagePostAttach服務
 */
@Service
public class MaintainPlanQuestionnaireSampleAttachService {
	@Autowired
	MaintainPlanQuestionnaireSampleAttachDAO maintainPlanQuestionnaireSampleAttachDAO;
	
	/**
	 * 新增MaintainPlanQuestionnaireSampleAttach資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanQuestionnaireSampleAttach資料
	 * @param bytes
	 *            byte
	 * @return 是否成功
	 */
	public MaintainPlanQuestionnaireSampleAttach insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);			
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			MaintainPlanQuestionnaireSampleAttach entity = new MaintainPlanQuestionnaireSampleAttach();			
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

			maintainPlanQuestionnaireSampleAttachDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得MaintainPlanQuestionnaireSampleAttach資料
	 * 	
	 *            
	 * @return MaintainPlanQuestionnaireSampleAttach
	 */
	public MaintainPlanQuestionnaireSampleAttach get() {
		return maintainPlanQuestionnaireSampleAttachDAO.get();
	}
	
	
	/**
	 * 刪除MaintainPlanQuestionnaireSampleAttach
	 * 	
	 * @return true or false
	 */
	public boolean deleteAll() {
		try {											
			maintainPlanQuestionnaireSampleAttachDAO.deleteAll();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

}
