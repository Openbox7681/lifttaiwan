package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.SpMemberReport;
import tw.gov.mohw.hisac.web.domain.ViewMember;

public interface MemberDAO {
	public void insert(Member entity);
	public void update(Member entity);
	public void delete(Member entity);
	public Member get(Long id);
	public List<Member> getAll();
	public List<ViewMember> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public Member getByAccount(String account);
	public List<Member> getByOrgId(Long orgId);
	public List<Member> getByOrgIds(Long[] orgIds);
	public List<Member> getByIds(Long[] ids);
	public List<SpMemberReport> getReport(JSONObject obj);
}