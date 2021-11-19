package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.MessageGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMessageGroupOrg;

public interface MessageGroupOrgDAO {
	public void insert(MessageGroupOrg entity);
	public void update(MessageGroupOrg entity);
	public void delete(MessageGroupOrg entity);
	public MessageGroupOrg get(Long id);
	public List<MessageGroupOrg> getAll();
	public MessageGroupOrg getByOrgId(Long orgId);
	public List<MessageGroupOrg> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<MessageGroupOrg> getByMessageGroupId(Long messageGroupId);
	
	public List<SpMessageGroupOrg> getSpList(JSONObject obj);
	

}
