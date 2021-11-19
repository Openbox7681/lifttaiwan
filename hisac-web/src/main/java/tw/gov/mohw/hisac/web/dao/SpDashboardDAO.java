package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.SpContactCountDashboard;
import tw.gov.mohw.hisac.web.domain.SpDashboard;
import tw.gov.mohw.hisac.web.domain.SpManagerCountDashboard;



public interface SpDashboardDAO {
	
	public List<SpDashboard> getSpListInfo(JSONObject obj);
	public List<SpDashboard> getSpListPublic(JSONObject obj);
	public List<SpDashboard> getSpListMessage(JSONObject obj);
	public List<SpDashboard> getSpListNotification(JSONObject obj);
	public List<SpManagerCountDashboard> getManager();
	public List<SpContactCountDashboard> getContact();
	public List<SpDashboard> getInformation(JSONObject obj);

}