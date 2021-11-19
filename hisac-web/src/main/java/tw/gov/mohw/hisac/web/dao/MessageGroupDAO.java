package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.MessageGroup;


public interface MessageGroupDAO {
	public void insert(MessageGroup entity);
	public void update(MessageGroup entity);
	public void delete(MessageGroup entity);
	public MessageGroup get(Long id);
	public List<MessageGroup> getAll();
	public MessageGroup getByName(String name);
	public List<MessageGroup> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}
