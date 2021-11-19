package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.MessagePost;

public interface MessagePostDAO {
	public void insert(MessagePost entity);
	public void update(MessagePost entity);
	public void delete(MessagePost entity);
	public MessagePost get(String id);
	public List<MessagePost> getAll();
	public List<MessagePost> getBymessageId(String messageId);
	public List<MessagePost> getBymessageIdAndPostType(String messageId, String postType);
}
