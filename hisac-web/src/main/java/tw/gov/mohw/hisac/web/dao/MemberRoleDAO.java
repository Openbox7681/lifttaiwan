package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;
import tw.gov.mohw.hisac.web.domain.SpMemberRoleName;

public interface MemberRoleDAO {

	public void insert(MemberRole entity);
	public void update(MemberRole entity);
	public void delete(MemberRole entity);
	public MemberRole get(Long id);
	public MemberRole getByMemberIdAndRoleId(long memberId, long roleId);
	public List<MemberRole> getAll();
	public List<MemberRole> getByMemberId(long memberId);
	public List<MemberRole> getAllByMemberId(long memberId);	
	public List<MemberRole> getAllByMemberIds(Long[] memberIds);	
	public List<ViewMemberRoleMember> getByRoleId(long roleId);
	public List<SpMemberRoleName> getMemberRoleList(JSONObject obj);

}
