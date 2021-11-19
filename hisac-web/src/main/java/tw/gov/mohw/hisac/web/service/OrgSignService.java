package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.OrgSignDAO;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.SpOrgSign;

/**
 * 會員機構審核方式管理服務
 */
@Service
public class OrgSignService {
	@Autowired
	OrgSignDAO orgSignDAO;

	/**
	 * 取得會員機構審核方式資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 會員機構審核方式資料join org
	 */

	public List<SpOrgSign> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return orgSignDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	新增/更新會員上級機關資料
	
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            會員上級機關資料
	 * @return 會員上級機關資料
	*/
	
	@SuppressWarnings("null")
	public OrgSign save(Long memberId, String json) {
		try {
			Date now = new Date();

			JSONObject obj = new JSONObject(json);
			Long orgId = obj.isNull("OrgId") == true ? null : obj.getLong("OrgId");
			Long parentOrgId = obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
			int notificationClosingIsExamine = obj.isNull("NotificationClosingIsExamine") == true ? 2  : obj .getInt("NotificationClosingIsExamine");
			int warningIsExamine = obj.isNull("WarningIsExamine") == true ? 2 : obj.getInt("WarningIsExamine");
			int notificationIsExamine = obj.isNull("NotificationIsExamine") == true ? 2 : obj.getInt("NotificationIsExamine");
			OrgSign entity = getByOrgIdAndParentOrgId(orgId, parentOrgId);
			
			if(entity != null) {
				
				entity.setOrgId(orgId);
				entity.setParentOrgId(parentOrgId);
				entity.setNotificationClosingIsExamine(notificationClosingIsExamine);
				entity.setNotificationIsExamine(notificationIsExamine);
				entity.setWarningIsExamine(warningIsExamine);
				entity.setCreateId(memberId);
				entity.setCreateTime(now);
				entity.setModifyId(memberId);
				entity.setModifyTime(now);
				orgSignDAO.insert(entity);			
			}else {
				entity.setOrgId(orgId);
				entity.setParentOrgId(parentOrgId);
				entity.setModifyId(memberId);
				entity.setModifyTime(now);
				orgSignDAO.update(entity);
			}

			
			
			return entity;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	
	/**
	新增會員上級機關資料
	
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            會員上級機關資料
	 * @return 會員上級機關資料
	*/
	
	
	public OrgSign insert(Long memberId, String json) {
		try {
			Date now = new Date();

			JSONObject obj = new JSONObject(json);
			Long orgId = obj.isNull("OrgId") == true ? null : obj.getLong("OrgId");
			Long parentOrgId = obj.isNull("ParentOrgId") == true ? null : obj.getLong("ParentOrgId");
			int notificationClosingIsExamine = obj.isNull("NotificationClosingIsExamine") == true ? 2  : obj .getInt("NotificationClosingIsExamine");
			int warningIsExamine = obj.isNull("WarningIsExamine") == true ? 2 : obj.getInt("WarningIsExamine");
			int notificationIsExamine = obj.isNull("NotificationIsExamine") == true ? 2 : obj.getInt("NotificationIsExamine");
			OrgSign entity = new OrgSign();

			entity.setOrgId(orgId);
			entity.setParentOrgId(parentOrgId);
			entity.setNotificationClosingIsExamine(notificationClosingIsExamine);
			entity.setNotificationIsExamine(notificationIsExamine);
			entity.setWarningIsExamine(warningIsExamine);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			orgSignDAO.insert(entity);
			
			return entity;
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增/更新會員機構審核方式資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            id
	 * @param orgId
	 *            單位編號
	 * @param parentOrgId
	 *            parentOrgId
	 * @param flag
	 *            flag
	 * @param warningIsExamine
	 *            警訊回復
	 * @param notificationIsExamine
	 *            通報
	 * @param notificationClosingIsExamine
	 *            通報處理
	 */
	public void save(long memberId, Long id, Long orgId, Long parentOrgId, boolean flag, int warningIsExamine, int notificationIsExamine, int notificationClosingIsExamine) {
		Date now = new Date();

		if (flag == true && id == 0) {
			OrgSign entity = new OrgSign();
			// entity.setId(id);
			entity.setOrgId(orgId);
			entity.setParentOrgId(parentOrgId);
			entity.setNotificationClosingIsExamine(notificationClosingIsExamine);
			entity.setNotificationIsExamine(notificationIsExamine);
			entity.setWarningIsExamine(warningIsExamine);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			orgSignDAO.insert(entity);
		} else if (flag == true && id != 0) {
			OrgSign entity = orgSignDAO.get(id);
			// entity.setId(id);
			// entity.setOrgId(orgId);
			// entity.setParentOrgId(parentOrgId);
			entity.setNotificationClosingIsExamine(notificationClosingIsExamine);
			entity.setNotificationIsExamine(notificationIsExamine);
			entity.setWarningIsExamine(warningIsExamine);
			// entity.setCreateId(memberId);
			// entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			orgSignDAO.update(entity);
		} else if (flag == false && id != 0) {
			this.delete(id);
		}

		// OrgSignDAO.insertOrUpdate(entity);
	}

	/**
	 * 刪除會員機構審核方式資料
	 * 
	 * @param id
	 *            會員機構審核方式資料Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			OrgSign entity = orgSignDAO.get(id);
			orgSignDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	// /**
	// * 取得警訊會員群組會員機構資料
	// *
	// * @param id
	// * 警訊會員群組會員機構資料Id
	// * @return 警訊會員群組會員機構資料
	// */
	// public OrgSign getById(Long id) {
	// return orgSignDAO.get(id);
	// }
	// /**
	// * 警訊會員群組會員機構資料是否存在
	// *
	// * @param id
	// * 警訊會員群組會員機構資料Id
	// * @return 是否存在
	// */
	// public boolean isExist(Long id) {
	// return orgSignDAO.get(id) != null;
	// }
	//

	/**
	 * 取得會員機構審核方式資料
	 * 
	 * @return 會員機構審核方式資料
	 */

	public List<OrgSign> getAll() {
		return orgSignDAO.getAll();
	}
	
	/**
	 * 取得會員機構審核方式資料
	 * 
	 * @param orgId
	 *            會員機構審核方式資料Id     
	 * @param parentorgId
	 *            權責單位Id
	 * @return 會員機構審核方式資料
	 */
	
	public OrgSign getByOrgIdAndParentOrgId(long orgId ,long parentorgId) {
		List<OrgSign> orgSigns = orgSignDAO.getByOrgId(orgId);
		for (OrgSign orgSign : orgSigns) {
			if(orgSign.getParentOrgId().equals(parentorgId)) {
				return orgSign;
			}
		}
		return null;
		
	}


	
	

	/**
	 * 取得會員機構審核方式資料
	 * 
	 * @param orgId
	 *            會員機構審核方式資料Id
	 * @return 會員機構審核方式資料
	 */

	public List<OrgSign> getByOrgId(long orgId) {
		return orgSignDAO.getByOrgId(orgId);
	}

	/**
	 * 取得會員機構審核方式資料
	 * 
	 * @param parentorgId
	 *            權責單位Id
	 * 
	 * @return 會員機構審核方式資料
	 */

	public List<OrgSign> getByParentOrgId(long parentorgId) {
		return orgSignDAO.getByParentOrgId(parentorgId);
	}

}
