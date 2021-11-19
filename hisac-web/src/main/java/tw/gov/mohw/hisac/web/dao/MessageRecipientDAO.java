package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MessageRecipient;

public interface MessageRecipientDAO {
	public void insert(MessageRecipient messageRecipient);
	public void update(MessageRecipient messageRecipient);
	public void delete(MessageRecipient messageRecipient);
	public MessageRecipient get(Long id);		
	public List<MessageRecipient> getAll();
	public List<MessageRecipient> getList(String json);
	public long getListSize(String json);
}
