package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.SubscribeMember;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;;

public interface SubscribeMemberDAO {

	public void insert(SubscribeMember entity);
	public void delete(SubscribeMember entity);
	public SubscribeMember get(Long id);
	public List<SubscribeMember> getByMemberId(long memberId);	
	public List<ViewSubscribeMember> getBySubscribeName(String subscribeName);	

}