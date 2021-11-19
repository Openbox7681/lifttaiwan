package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;


public interface InformationManagementDAO {
	public void insert(InformationManagement entity);
	public void update(InformationManagement entity);
	public void delete(InformationManagement entity);
	public InformationManagement get(Long id);
	public List<InformationManagement> getList(JSONObject obj);	
	public long getListSize(JSONObject obj);
	public List<InformationManagement> getSpList(JSONObject obj);	
	public long getSpListSize(JSONObject obj);	
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
}