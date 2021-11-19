package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.Message;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;



public interface MessageDAO {
	public void insert(Message entity);
	public void update(Message entity);
	public void delete(Message entity);
	public Message get(String id);
	public List<Message> getAll();	
	public List<ViewMessageAlertEvent> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public ViewMessageAlertEvent getById(String id);
	public List<Message> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	

}
