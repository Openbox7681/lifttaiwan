package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.OrgReportMemberLog;


public interface OrgReportMemberLogDAO {	
	public OrgReportMemberLog get(Long id);
	public void delete(OrgReportMemberLog entity);
	public List<OrgReportMemberLog> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getMemberSigninCount(JSONObject obj);
}