package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MessageGroupOrgDAO;
import tw.gov.mohw.hisac.web.domain.MessageGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMessageGroupOrg;

/**
 * 警訊發佈群組管理會員機構管理服務
 */
@Service
public class MessageGroupOrgService {
	@Autowired
	MessageGroupOrgDAO messageGroupOrgDAO;

	/**
	 * 取得警訊發佈群組管理會員機構資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 警訊發佈群組管理會員機構資料join org
	 */

	public List<SpMessageGroupOrg> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return messageGroupOrgDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得警訊發佈群組管理會員機構資料
	 * 
	 * @param messageGroupId
	 *            messageGroupId
	 * @return 警訊發佈群組管理會員機構資料
	 */

	public List<MessageGroupOrg> getByMessageGroupId(Long messageGroupId) {
		return messageGroupOrgDAO.getByMessageGroupId(messageGroupId);
	}

	/**
	 * 刪除警訊發佈群組管理會員機構資料
	 * 
	 * @param id
	 *            警訊發佈群組管理會員機構Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MessageGroupOrg entity = messageGroupOrgDAO.get(id);
			messageGroupOrgDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 新增/更新警訊發佈群組管理會員機構資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            權限角色Id
	 * @param messageGroupId
	 *            messageGroupId
	 * @param orgId
	 *            orgId
	 * @param flag
	 *            boolean
	 */
	public void insertOrDelete(long memberId, Long id, Long messageGroupId, Long orgId, boolean flag) {
		Date now = new Date();

		if (flag == true && id == 0) {
			MessageGroupOrg entity = new MessageGroupOrg();
			entity.setId(id);
			entity.setMessageGroupId(messageGroupId);
			entity.setOrgId(orgId);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messageGroupOrgDAO.insert(entity);
		} else if (flag == false && id != 0) {
			this.delete(id);
		}

		// messageGroupOrgDAO.insertOrUpdate(entity);
	}

	// /**
	// * 取得所有警訊發佈群組管理會員機構資料
	// *
	// * @return 警訊發佈群組管理會員機構資料
	// */
	// public List<MessageGroupOrg> getAll() {
	// return messageGroupOrgDAO.getAll();
	// }

	// /**
	// * 取得警訊發佈群組管理會員機構資料
	// *
	// * @param json
	// * 查詢條件
	// * @return 警訊發佈群組管理會員機構資料
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
	// * 取得警訊發佈群組管理會員機構資料筆數
	// *
	// * @param json
	// * 查詢條件
	// * @return 警訊發佈群組管理會員機構資料筆數
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
	// * 新增警訊發佈群組管理會員機構資料
	// *
	// * @param name
	// * 警訊發佈群組管理會員機構名稱
	// * @return 警訊發佈群組管理會員機構資料
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
	// * 取得警訊發佈群組管理會員機構資料
	// *
	// * @param id
	// * 警訊發佈群組管理會員機構資料Id
	// * @return 警訊發佈群組管理會員機構資料
	// */
	// public MessageGroupOrg getById(Long id) {
	// return messageGroupOrgDAO.get(id);
	// }
	// /**
	// * 警訊發佈群組管理會員機構資料是否存在
	// *
	// * @param id
	// * 警訊發佈群組管理會員機構資料Id
	// * @return 是否存在
	// */
	// public boolean isExist(Long id) {
	// return messageGroupOrgDAO.get(id) != null;
	// }
	// /**
	// * 警訊發佈群組管理會員機構資料名稱
	// *
	// * @param name
	// * 警訊發佈群組管理會員機構名稱
	// * @return 警訊發佈群組管理會員機構名稱資料
	// */
	// public MessageGroupOrg findByOrgId(Long orgId) {
	// return messageGroupOrgDAO.getByOrgId(orgId);
	// }
	// /**
	// * 警訊發佈群組管理會員機構資料Id是否存在
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
