package tw.gov.mohw.hisac.web.dao;

import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.TicketQueue;

public interface TicketQueueDAO {
	public void insert(TicketQueue entity);
	public void update(TicketQueue entity);
	public void delete(TicketQueue entity);
	public TicketQueue get(JSONObject obj);
	public String insertPostId(String tableName, Boolean isApply, String pre, String code);
	public String updatePostId(String tableName, Boolean isApply, String pre, String prePostId, String code);
}
