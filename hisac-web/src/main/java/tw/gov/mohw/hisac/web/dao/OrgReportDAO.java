package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.domain.OrgReport;
import tw.gov.mohw.hisac.web.domain.SpOrgReportResult;


public interface OrgReportDAO {	
	public JSONObject schedule(JSONObject obj);	
	public OrgReport get(Long id);
	public void delete(OrgReport entity);
	public List<OrgReport> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getSumCount(JSONObject obj);
	public List<SpOrgReportResult> getSumResult(JSONObject obj);
	public List<OrgReport> getOrgReportByOrgId(String sdate, String edate , Long orgId);
	public boolean isOrgReportExistByReportDate(String reportCreateTime);
}