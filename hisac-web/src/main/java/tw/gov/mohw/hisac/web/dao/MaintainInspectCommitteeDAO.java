package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainInspectCommittee;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;

public interface MaintainInspectCommitteeDAO {
	
	public void insert(MaintainInspectCommittee entity);
	public void update(MaintainInspectCommittee entity);
	public void delete(MaintainInspectCommittee entity);
	public MaintainInspectCommittee get(Long id);
	public ViewMaintainInspectCommitteeMemberOrg getView(Long id);
	public List<ViewMaintainInspectCommitteeMemberOrg> getMaintainInspectCommitteeList(JSONObject obj);
	public long getMaintainInspectCommitteeListSize(JSONObject obj);
	public List<ViewMaintainInspectCommitteeMemberOrg> findByMaintainInspectIdAndGroupId(Long maintainInspectId, Long groupId);
	public MaintainInspectCommittee getByMaintainInspectIdAndGroupIdAndCommitteeId(long maintainInspectId,long groupId, long committeeId);
	public List<MaintainInspectCommittee> getListByMaintainInspectIdAndGroupId(Long maintainInspectId, Long groupId);
}
