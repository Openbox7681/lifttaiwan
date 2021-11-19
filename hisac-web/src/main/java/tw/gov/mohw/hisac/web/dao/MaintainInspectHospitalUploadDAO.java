package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;



public interface MaintainInspectHospitalUploadDAO {
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectList(JSONObject obj);
	public long getMaintainInspectListSize(JSONObject obj);
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainPlanId(long maintainPlanId);
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id);
	public MaintainPlanMember get(Long id);
	public ViewMaintainInspectMemberOrg getListByMaintainInspectIdAndGroupId(long maintainPlanId, long groupId);
}
