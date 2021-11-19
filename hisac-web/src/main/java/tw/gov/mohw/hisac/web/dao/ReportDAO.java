package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

public interface ReportDAO {	
	public void schedule();	
	public List<Object[]> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public long getDownAttachListSize(JSONObject obj) ;
	public List<Object[]> getTop10Detail(JSONObject obj);
}