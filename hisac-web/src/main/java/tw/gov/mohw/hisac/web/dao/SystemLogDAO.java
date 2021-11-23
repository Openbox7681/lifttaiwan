package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.SpSigninCountTop10;
import tw.gov.mohw.hisac.web.domain.SpSystemLogByOrgTop5;
import tw.gov.mohw.hisac.web.domain.SpWebSiteLoad;
import tw.gov.mohw.hisac.web.domain.SystemLog;

public interface SystemLogDAO {
	public void insert(SystemLog entity);
	public void update(SystemLog entity);
	public void delete(SystemLog entity);
	public SystemLog get(Long id);
	public List<SystemLog> getAll();
	public List<SystemLog> getList(JSONObject obj);
	public List<SystemLog> getListForLoginLog(JSONObject obj);
	public List<SpWebSiteLoad> getWebSiteLoad(JSONObject obj);
	public long getListSize(JSONObject obj);
	public long getListSizeForLoginLog(JSONObject obj);
	public List<SpSigninCountTop10> getSignTop10(JSONObject obj);
	public List<SpSystemLogByOrgTop5> getSpSystemLogByOrgTop5(JSONObject obj);
	public List<SpSystemLogByOrgTop5> getSpSystemLogByOrg(JSONObject obj);
}