package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.MessagePostRelease;
import tw.gov.mohw.hisac.web.domain.ViewMessagePostReleaseOrg;

public interface MessagePostReleaseDAO {
	public void insert(MessagePostRelease entity);
	public void update(MessagePostRelease entity);
	public void delete(MessagePostRelease entity);
	public MessagePostRelease get(String id);
	public List<ViewMessagePostReleaseOrg> getAll();
	public List<ViewMessagePostReleaseOrg> getBymessageId(String messageId);
	public ViewMessagePostReleaseOrg getBymessageIdAndOrgId(String messageId, long orgId);	
}
