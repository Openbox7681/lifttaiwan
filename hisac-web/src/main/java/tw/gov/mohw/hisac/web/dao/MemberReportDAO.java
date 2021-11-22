package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MemberReport;


public interface MemberReportDAO {	
	public JSONObject schedule(JSONObject obj);	
	public List<MemberReport> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public boolean isMemberReportExistByReportDate(String reportCreateTime);
	public MemberReport get(Long id);
	public void delete(MemberReport entity);



}