package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.MessagePostAttach;

public interface MessagePostAttachDAO {
	public void insert(MessagePostAttach entity);
	public void update(MessagePostAttach entity);
	public void delete(MessagePostAttach entity);
	public MessagePostAttach get(Long id);
	public List<MessagePostAttach> getAll();
	public List<MessagePostAttach> getByMessageId(String messageId);
	public long getByMessageIdSize(String messageId);
}
