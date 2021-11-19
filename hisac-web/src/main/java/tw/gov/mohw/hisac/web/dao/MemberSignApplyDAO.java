package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ViewMemberSignApply;

public interface MemberSignApplyDAO {
	public List<ViewMemberSignApply> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}