package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;



public interface MaintainPlanMemberDAO {
	public void insert(MaintainPlanMember entity);
	public void update(MaintainPlanMember entity);
	public List<ViewMaintainPlanMemberOrg> getListByMaintainPlanId(long maintainPlanId);
	public List<ViewMaintainPlanMemberOrg> getList(JSONObject obj);
	public MaintainPlanMember getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId);
	public long getListSize(JSONObject obj);
	
	public List<Object[]> getMaintainInspectList(JSONObject obj);
	public long getMaintainInspectListSize(JSONObject obj);
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainPlanId(long maintainPlanId);
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id);
	public MaintainPlanMember get(Long id);
}
