package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ResourceMessage;


public interface ResourceMessageDAO {
	public void insert(ResourceMessage entity);
	public void update(ResourceMessage entity);
	public void delete(ResourceMessage entity);
	public List<ResourceMessage> getAll();
	public ResourceMessage get(Long id);
	public ResourceMessage getByMessageKey(String code);
	public List<ResourceMessage> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	
	
}
