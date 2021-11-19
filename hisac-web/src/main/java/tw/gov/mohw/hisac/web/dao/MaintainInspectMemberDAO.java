package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;



public interface MaintainInspectMemberDAO {
	public void insert(MaintainInspectMember entity);
	public void update(MaintainInspectMember entity);
	public MaintainInspectMember getListByMaintainInspectIdAndGroupId(long maintainInspectId, long groupId);
	
	public List<Object[]> getMaintainInspectList(JSONObject obj);
	public long getMaintainInspectListSize(JSONObject obj);
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainInspectId(long maintainInspectId);
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id);
	public MaintainInspectMember get(Long id);
}
