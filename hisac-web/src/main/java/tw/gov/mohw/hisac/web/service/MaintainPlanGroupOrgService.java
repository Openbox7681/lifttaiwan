package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MaintainPlanGroupOrgDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMaintainPlanGroupOrg;

/**
 * 資安維護計畫群組管理會員機構管理服務
 */
@Service
public class MaintainPlanGroupOrgService {
	@Autowired
	MaintainPlanGroupOrgDAO maintainPlanGroupOrgDAO;

	/**
	 * 取得資安維護計畫群組管理會員機構資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 資安維護計畫群組管理會員機構資料join org
	 */

	public List<SpMaintainPlanGroupOrg> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainPlanGroupOrgDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得資安維護計畫群組管理會員機構資料
	 * 
	 * @param maintainPlanGroupId
	 *            maintainPlanGroupId
	 * @return 資安維護計畫群組管理會員機構資料
	 */

	public List<MaintainPlanGroupOrg> getByMaintainPlanGroupId(Long maintainPlanGroupId) {
		return maintainPlanGroupOrgDAO.getByMaintainPlanGroupId(maintainPlanGroupId);
	}

	/**
	 * 刪除資安維護計畫群組管理會員機構資料
	 * 
	 * @param id
	 *            資安維護計畫群組管理會員機構Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainPlanGroupOrg entity = maintainPlanGroupOrgDAO.get(id);
			maintainPlanGroupOrgDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 新增/更新資安維護計畫群組管理會員機構資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            權限角色Id
	 * @param maintainPlanGroupId
	 *            maintainPlanGroupId
	 * @param orgId
	 *            orgId
	 * @param flag
	 *            boolean
	 */
	public void insertOrDelete(long memberId, Long id, Long maintainPlanGroupId, Long orgId, boolean flag) {
		Date now = new Date();

		if (flag == true && id == 0) {
			MaintainPlanGroupOrg entity = new MaintainPlanGroupOrg();
			entity.setId(id);
			entity.setMaintainPlanGroupId(maintainPlanGroupId);
			entity.setOrgId(orgId);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			maintainPlanGroupOrgDAO.insert(entity);
		} else if (flag == false && id != 0) {
			this.delete(id);
		}

		// messageGroupOrgDAO.insertOrUpdate(entity);
	}

	// /**
	// * 取得所有資安維護計畫群組管理會員機構資料
	// *
	// * @return 資安維護計畫群組管理會員機構資料
	// */
	// public List<MessageGroupOrg> getAll() {
	// return messageGroupOrgDAO.getAll();
	// }

	// /**
	// * 取得資安維護計畫群組管理會員機構資料
	// *
	// * @param json
	// * 查詢條件
	// * @return 資安維護計畫群組管理會員機構資料
	// */
	// public List<MessageGroupOrg> getList(String json) {
	// try {
	// JSONObject obj = new JSONObject(json);
	// return messageGroupOrgDAO.getList(obj);
	// } catch (Exception e) {
	// return null;
	// }
	// }
	// /**
	// * 取得資安維護計畫群組管理會員機構資料筆數
	// *
	// * @param json
	// * 查詢條件
	// * @return 資安維護計畫群組管理會員機構資料筆數
	// */
	// public long getListSize(String json) {
	// try {
	// JSONObject obj = new JSONObject(json);
	// return messageGroupOrgDAO.getListSize(obj);
	// } catch (Exception e) {
	// return 0;
	// }
	// }
	// /**
	// * 新增資安維護計畫群組管理會員機構資料
	// *
	// * @param name
	// * 資安維護計畫群組管理會員機構名稱
	// * @return 資安維護計畫群組管理會員機構資料
	// */
	// public MessageGroupOrg insert(Long memberId, String json) {
	// try {
	// JSONObject obj = new JSONObject(json);
	// long messageGroupId = obj.isNull("MessageGroupId") == true ? 0 :
	// obj.getLong("MessageGroupId");
	// long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
	//
	// Date now = new Date();
	// MessageGroupOrg entity = new MessageGroupOrg();
	// entity.setMessageGroupId(messageGroupId);
	// entity.setOrgId(orgId);
	// entity.setCreateId(memberId);
	// entity.setCreateTime(now);
	// entity.setModifyId(memberId);
	// entity.setModifyTime(now);
	//
	// messageGroupOrgDAO.insert(entity);
	// return entity;
	// } catch (Exception e) {
	// //e.printStackTrace();
	// return null;
	// }
	// }

	// /**
	// * 取得資安維護計畫群組管理會員機構資料
	// *
	// * @param id
	// * 資安維護計畫群組管理會員機構資料Id
	// * @return 資安維護計畫群組管理會員機構資料
	// */
	// public MessageGroupOrg getById(Long id) {
	// return messageGroupOrgDAO.get(id);
	// }
	// /**
	// * 資安維護計畫群組管理會員機構資料是否存在
	// *
	// * @param id
	// * 資安維護計畫群組管理會員機構資料Id
	// * @return 是否存在
	// */
	// public boolean isExist(Long id) {
	// return messageGroupOrgDAO.get(id) != null;
	// }
	// /**
	// * 資安維護計畫群組管理會員機構資料名稱
	// *
	// * @param name
	// * 資安維護計畫群組管理會員機構名稱
	// * @return 資安維護計畫群組管理會員機構名稱資料
	// */
	// public MessageGroupOrg findByOrgId(Long orgId) {
	// return messageGroupOrgDAO.getByOrgId(orgId);
	// }
	// /**
	// * 資安維護計畫群組管理會員機構資料Id是否存在
	// *
	// * @param memberId 編號
	// *
	// * @return 是否存在
	// */
	// public boolean isOrgIdExist(Long orgId) {
	// return findByOrgId(orgId) != null;
	// }
	//
}
