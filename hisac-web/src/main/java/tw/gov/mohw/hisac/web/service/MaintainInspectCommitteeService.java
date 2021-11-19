package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainInspectCommitteeDAO;
import tw.gov.mohw.hisac.web.domain.MaintainInspectCommittee;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;

/**
 * 資安維護計畫服務
 */
@Service
public class MaintainInspectCommitteeService {
	@Autowired
	MaintainInspectCommitteeDAO maintainInspectCommitteeDAO;

	public void deleteAndCreateCommittee(JSONArray committeeList, Long maintainInspectId, Long groupId, Long memberId) {
		List<ViewMaintainInspectCommitteeMemberOrg> maintainInspectCommittees =
				this.findByMaintainInspectIdAndGroupId(maintainInspectId, groupId);
		if(maintainInspectCommittees!=null && !maintainInspectCommittees.isEmpty()) {
			for(ViewMaintainInspectCommitteeMemberOrg maintainInspectCommittee : maintainInspectCommittees) {
				Long id = maintainInspectCommittee.getId();
				boolean success = this.delete(id);
			}
		}
		if(committeeList!=null && committeeList.length()!=0) {
			for(int i=0; i<committeeList.length(); i++) {
				JSONObject committee = committeeList.getJSONObject(i);
				Long committeeMemberId = committee.getLong("CommitteeMemberId");
				committee.put("CommitteeId", committeeMemberId);
				committee.put("MaintainInspectId", maintainInspectId);
				committee.put("GroupId", groupId);
	
				MaintainInspectCommittee  maintainInspectCommittee = this.insert(memberId, committee.toString());
			}
		}
	}
	
	/**
	 * 新增資安維護計畫資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫資料
	 */
	public MaintainInspectCommittee insert(Long memberId, String json) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			Long maintainInspectId = obj.isNull("MaintainInspectId") == true ? null : obj.getLong("MaintainInspectId");
			Long groupId = obj.isNull("GroupId") == true ? null : obj.getLong("GroupId");
			Long committeeId = obj.isNull("CommitteeId") == true ? null : obj.getLong("CommitteeId");

			MaintainInspectCommittee entity = new MaintainInspectCommittee();
			entity.setMaintainInspectId(maintainInspectId);
			entity.setGroupId(groupId);
			entity.setCommitteeId(committeeId);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			maintainInspectCommitteeDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新資安維護計畫資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫資料
	 */
	public MaintainInspectCommittee update(long memberId, String json) {
		try {
			Date now = new Date();
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
			Long maintainInspectId = obj.isNull("MaintainInspectId") == true ? null : obj.getLong("MaintainInspectId");
			Long groupId = obj.isNull("GroupId") == true ? null : obj.getLong("GroupId");
			Long committeeId = obj.isNull("CommitteeId") == true ? null : obj.getLong("CommitteeId");

			MaintainInspectCommittee entity = maintainInspectCommitteeDAO.get(id);
			entity.setMaintainInspectId(maintainInspectId);
			entity.setGroupId(groupId);
			entity.setCommitteeId(committeeId);

			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			maintainInspectCommitteeDAO.update(entity);
			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public List<ViewMaintainInspectCommitteeMemberOrg> findByMaintainInspectIdAndGroupId(Long maintainInspectId, Long groupId) {
		return maintainInspectCommitteeDAO.findByMaintainInspectIdAndGroupId(maintainInspectId, groupId);
	}
	
	/**
	 * 刪除資安維護計畫資料
	 * 
	 * @param id
	 *            資安維護計畫Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MaintainInspectCommittee entity = maintainInspectCommitteeDAO.get(id);
			maintainInspectCommitteeDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 資安維護計畫資料是否存在
	 * 
	 * @param id
	 *            資安維護計畫資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return maintainInspectCommitteeDAO.get(id) != null;
	}
	
	/**
	 * 資安維護計畫資料
	 * 
	 * @param id
	 *            資安維護計畫資料Id
	 * @return 資安維護計畫資料
	 */
	public MaintainInspectCommittee getById(Long id) {
		return maintainInspectCommitteeDAO.get(id);
	}
	
	/**
	 * 繳交調查表及稽核表資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectCommittee score_submit_new(long maintainInspectId, long groupId ,long committeeId) {
		try {							
						
			MaintainInspectCommittee entity = maintainInspectCommitteeDAO
					.getByMaintainInspectIdAndGroupIdAndCommitteeId(maintainInspectId, groupId ,committeeId);				
			entity.setStatus(true);
			maintainInspectCommitteeDAO.update(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}
}
