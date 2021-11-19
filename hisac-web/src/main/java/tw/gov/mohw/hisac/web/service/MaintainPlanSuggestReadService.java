package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanSuggestReadDAO;


import tw.gov.mohw.hisac.web.domain.MaintainPlanImprovementSuggestRead;

/**
 * MaintainPlanImprovementSuggestAttach服務
 */
@Service
public class MaintainPlanSuggestReadService {
	@Autowired
	MaintainPlanSuggestReadDAO maintainPlanSuggestReadDAO;	
	
	
	/**
	 * 新增MaintainPlanImprovementSuggestRead資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MaintainPlanImprovementSuggestRead資料
	 * @return 是否成功
	 */
	public MaintainPlanImprovementSuggestRead insert(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? null : obj.getLong("MaintainPlanId");
			long attachId = obj.isNull("AttachId") == true ? null : obj.getLong("AttachId");
			boolean isRead = obj.isNull("IsRead") == true ? false : obj.getBoolean("IsRead");
			long orgId = obj.isNull("OrgId") == true ? null : obj.getLong("OrgId");
			Date now = new Date();
			MaintainPlanImprovementSuggestRead entity = new MaintainPlanImprovementSuggestRead();
			entity.setMaintainPlanId(maintainPlanId);
			entity.setAttachId(attachId);
			entity.setOrgId(orgId);
			entity.setIsRead(isRead);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			
			maintainPlanSuggestReadDAO.insert(entity);
			
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setReadTrue(MaintainPlanImprovementSuggestRead entity) {
		entity.setIsRead(true);
		maintainPlanSuggestReadDAO.update(entity);
		
	}
	
	

	/**
	 * 取得MaintainPlanImprovementSuggestRead資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 * @param org id
	 * 				Org Id
	 * @param attach id
	 * 				Attach Id
	 *                   
	 * @return MaintainPlanImprovementSuggestRead
	 */
	
	public MaintainPlanImprovementSuggestRead getByMaintainPlanIdAndOrgIdAndAttachId(long maintainPlanId , long orgId, long attachId) {
		return maintainPlanSuggestReadDAO.getByMaintainPlanIdAndOrgIdAndAttachId(maintainPlanId, orgId, attachId);
	}
	
	/**
	 * 取得MaintainPlanImprovementSuggestRead List資料
	 * 
	 * @param id
	 *            MaintainPlan Id
	 *                   
	 * @return List<MaintainPlanImprovementSuggestRead>
	 */
	
	public List<MaintainPlanImprovementSuggestRead> getByMaintainPlanId(long maintainPlanId){
		return maintainPlanSuggestReadDAO.getByMaintainPlanId(maintainPlanId);	
	}
	
	
	
	
	
	
	/**
	 * 刪除MaintainPlanAttach
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 *            
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanId(long maintainPlanId , long attachId) {
		try {											
			maintainPlanSuggestReadDAO.deleteByMaintainPlanIdAndAttachId(maintainPlanId, attachId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
