package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.EventType;



public interface EventTypeDAO {
	
	
	
	public void insert(EventType entity);
	public void update(EventType entity);
	public void delete(EventType entity);
	public EventType get(Long id);
	public List<EventType> getAll();
	public EventType getByCode(String code);
	public EventType getByName(String name);
	public List<EventType> getByAlertCode(String alertCode);
	public List<EventType> getList(JSONObject obj);
	public List<EventType> getAnaList();
	public long getListSize(JSONObject obj);
	
}
